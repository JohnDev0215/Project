package com.android.permissioncontroller.permission.service;

import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.UserHandle;
import android.permission.PermissionManager;
import android.util.Log;
import android.util.Xml;
import androidx.core.os.BuildCompat;
import com.android.permissioncontroller.Constants;
import com.android.permissioncontroller.permission.model.AppPermissionGroup;
import com.android.permissioncontroller.permission.model.AppPermissions;
import com.android.permissioncontroller.permission.model.Permission;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;
import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;
import org.xmlpull.v1.XmlSerializer;

/* JADX INFO: loaded from: classes8.dex */
public class BackupHelper {
    private static final String ATTR_IS_GRANTED = "g";
    private static final String ATTR_PACKAGE_NAME = "pkg";
    private static final String ATTR_PERMISSION_NAME = "name";
    private static final String ATTR_PLATFORM_VERSION = "version";
    private static final String ATTR_USER_FIXED = "fixed";
    private static final String ATTR_USER_SET = "set";
    private static final String ATTR_WAS_REVIEWED = "was-reviewed";
    private static final int SYSTEM_RUNTIME_GRANT_MASK = 20;
    private static final String TAG_ALL_GRANTS = "rt-grants";
    private static final String TAG_GRANT = "grant";
    private static final String TAG_PERMISSION = "perm";
    private static final String TAG_PERMISSION_BACKUP = "perm-grant-backup";
    private final Context mContext;
    private static final String LOG_TAG = BackupHelper.class.getSimpleName();
    private static final Object sLock = new Object();

    public BackupHelper(Context context, UserHandle user) {
        try {
            this.mContext = context.createPackageContextAsUser(context.getPackageName(), 0, user);
        } catch (PackageManager.NameNotFoundException e) {
            throw new IllegalStateException();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static void skipToEndOfTag(XmlPullParser parser) throws XmlPullParserException, IOException {
        int numOpenTags = 1;
        while (numOpenTags > 0) {
            int next = parser.next();
            if (next == 2) {
                numOpenTags++;
            } else if (next == 3) {
                numOpenTags--;
            }
        }
    }

    private void skipToTag(XmlPullParser parser, String tag) throws XmlPullParserException, IOException {
        int type;
        do {
            type = parser.next();
            if (type == 2) {
                if (!parser.getName().equals(tag)) {
                    skipToEndOfTag(parser);
                    return;
                }
                return;
            }
        } while (type != 1);
    }

    private ArrayList<BackupPackageState> parseFromXml(XmlPullParser parser) throws XmlPullParserException, IOException {
        int backupPlatformVersion;
        int type;
        ArrayList<BackupPackageState> pkgStates = new ArrayList<>();
        skipToTag(parser, TAG_PERMISSION_BACKUP);
        try {
            backupPlatformVersion = Integer.parseInt(parser.getAttributeValue(null, "version"));
        } catch (NumberFormatException e) {
            backupPlatformVersion = 28;
        }
        skipToTag(parser, TAG_ALL_GRANTS);
        if (parser.getEventType() != 2 && !parser.getName().equals(TAG_ALL_GRANTS)) {
            throw new XmlPullParserException("Could not find perm-grant-backup > rt-grants");
        }
        do {
            type = parser.next();
            if (type == 2) {
                String name = parser.getName();
                byte b = -1;
                if (name.hashCode() == 98615580 && name.equals(TAG_GRANT)) {
                    b = 0;
                }
                if (b == 0) {
                    try {
                        pkgStates.add(BackupPackageState.parseFromXml(parser, this.mContext, backupPlatformVersion));
                    } catch (XmlPullParserException e2) {
                        Log.e(LOG_TAG, "Could not parse permissions ", e2);
                        skipToEndOfTag(parser);
                    }
                } else {
                    Log.w(LOG_TAG, "Found unexpected tag " + parser.getName() + " during restore");
                    skipToEndOfTag(parser);
                }
            }
        } while (type != 1);
        return pkgStates;
    }

    void restoreState(XmlPullParser parser) throws XmlPullParserException, IOException {
        ArrayList<BackupPackageState> pkgStates = parseFromXml(parser);
        ArrayList<BackupPackageState> packagesToRestoreLater = new ArrayList<>();
        int numPkgStates = pkgStates.size();
        if (numPkgStates > 0) {
            for (int i = 0; i < numPkgStates; i++) {
                BackupPackageState pkgState = pkgStates.get(i);
                try {
                    PackageInfo pkgInfo = this.mContext.getPackageManager().getPackageInfo(pkgState.mPackageName, 4096);
                    pkgState.restore(this.mContext, pkgInfo);
                } catch (PackageManager.NameNotFoundException e) {
                    packagesToRestoreLater.add(pkgState);
                }
            }
        }
        synchronized (sLock) {
            writeDelayedStorePkgsLocked(packagesToRestoreLater);
        }
    }

    private static void writePkgsAsXml(XmlSerializer serializer, ArrayList<BackupPackageState> pkgs) throws IOException {
        serializer.startDocument(null, true);
        serializer.startTag(null, TAG_PERMISSION_BACKUP);
        if (BuildCompat.isAtLeastQ()) {
            Integer num = 29;
            serializer.attribute(null, "version", num.toString());
        } else {
            serializer.attribute(null, "version", Integer.valueOf(Build.VERSION.SDK_INT).toString());
        }
        serializer.startTag(null, TAG_ALL_GRANTS);
        int numPkgs = pkgs.size();
        for (int i = 0; i < numPkgs; i++) {
            BackupPackageState packageState = pkgs.get(i);
            if (packageState != null) {
                packageState.writeAsXml(serializer);
            }
        }
        serializer.endTag(null, TAG_ALL_GRANTS);
        serializer.endTag(null, TAG_PERMISSION_BACKUP);
        serializer.endDocument();
    }

    private void writeDelayedStorePkgsLocked(ArrayList<BackupPackageState> packagesToRestoreLater) {
        try {
            OutputStream delayedRestoreData = this.mContext.openFileOutput(Constants.DELAYED_RESTORE_PERMISSIONS_FILE, 0);
            try {
                XmlSerializer serializer = Xml.newSerializer();
                serializer.setOutput(delayedRestoreData, StandardCharsets.UTF_8.name());
                writePkgsAsXml(serializer, packagesToRestoreLater);
                serializer.flush();
                if (delayedRestoreData != null) {
                    delayedRestoreData.close();
                }
            } finally {
            }
        } catch (IOException e) {
            Log.e(LOG_TAG, "Could not remember which packages still need to be restored", e);
        }
    }

    void writeState(XmlSerializer serializer) throws IOException {
        List<PackageInfo> pkgs = this.mContext.getPackageManager().getInstalledPackages(4096);
        ArrayList<BackupPackageState> backupPkgs = new ArrayList<>();
        int numPkgs = pkgs.size();
        for (int i = 0; i < numPkgs; i++) {
            BackupPackageState packageState = BackupPackageState.fromAppPermissions(this.mContext, pkgs.get(i));
            if (packageState != null) {
                backupPkgs.add(packageState);
            }
        }
        writePkgsAsXml(serializer, backupPkgs);
    }

    boolean restoreDelayedState(String packageName) throws PackageManager.NameNotFoundException {
        boolean z;
        synchronized (sLock) {
            try {
                FileInputStream delayedRestoreData = this.mContext.openFileInput(Constants.DELAYED_RESTORE_PERMISSIONS_FILE);
                try {
                    XmlPullParser parser = Xml.newPullParser();
                    parser.setInput(delayedRestoreData, StandardCharsets.UTF_8.name());
                    ArrayList<BackupPackageState> packagesToRestoreLater = parseFromXml(parser);
                    if (delayedRestoreData != null) {
                        delayedRestoreData.close();
                    }
                    PackageInfo pkgInfo = null;
                    try {
                        pkgInfo = this.mContext.getPackageManager().getPackageInfo(packageName, 4096);
                    } catch (PackageManager.NameNotFoundException e) {
                        Log.e(LOG_TAG, "Could not restore delayed permissions for " + packageName, e);
                    }
                    if (pkgInfo != null) {
                        int numPkgs = packagesToRestoreLater.size();
                        int i = 0;
                        while (true) {
                            if (i >= numPkgs) {
                                break;
                            }
                            BackupPackageState pkgState = packagesToRestoreLater.get(i);
                            if (!pkgState.mPackageName.equals(packageName)) {
                                i++;
                            } else {
                                pkgState.restore(this.mContext, pkgInfo);
                                packagesToRestoreLater.remove(i);
                                writeDelayedStorePkgsLocked(packagesToRestoreLater);
                                break;
                            }
                        }
                    }
                    int numPkgs2 = packagesToRestoreLater.size();
                    z = numPkgs2 > 0;
                } catch (Throwable th) {
                    if (delayedRestoreData != null) {
                        try {
                            delayedRestoreData.close();
                        } catch (Throwable th2) {
                            th.addSuppressed(th2);
                        }
                    }
                    throw th;
                }
            } catch (IOException | XmlPullParserException e2) {
                Log.e(LOG_TAG, "Could not parse delayed permissions", e2);
                return false;
            }
        }
        return z;
    }

    private static class BackupPermissionState {
        private final boolean mIsGranted;
        private final boolean mIsUserFixed;
        private final boolean mIsUserSet;
        private final String mPermissionName;
        private final boolean mWasReviewed;

        private BackupPermissionState(String permissionName, boolean isGranted, boolean isUserSet, boolean isUserFixed, boolean wasReviewed) {
            this.mPermissionName = permissionName;
            this.mIsGranted = isGranted;
            this.mIsUserSet = isUserSet;
            this.mIsUserFixed = isUserFixed;
            this.mWasReviewed = wasReviewed;
        }

        static List<BackupPermissionState> parseFromXml(XmlPullParser parser, Context context, int backupPlatformVersion) throws XmlPullParserException {
            String str = null;
            String permName = parser.getAttributeValue(null, BackupHelper.ATTR_PERMISSION_NAME);
            if (permName == null) {
                throw new XmlPullParserException("Found perm without name");
            }
            ArrayList<String> expandedPermissions = new ArrayList<>();
            expandedPermissions.add(permName);
            List<PermissionManager.SplitPermissionInfo> splitPerms = ((PermissionManager) context.getSystemService(PermissionManager.class)).getSplitPermissions();
            int numSplitPerms = splitPerms.size();
            for (int i = 0; i < numSplitPerms; i++) {
                PermissionManager.SplitPermissionInfo splitPerm = splitPerms.get(i);
                if (backupPlatformVersion < splitPerm.getTargetSdk() && permName.equals(splitPerm.getSplitPermission())) {
                    expandedPermissions.addAll(splitPerm.getNewPermissions());
                }
            }
            ArrayList<BackupPermissionState> parsedPermissions = new ArrayList<>(expandedPermissions.size());
            int numExpandedPerms = expandedPermissions.size();
            int i2 = 0;
            while (i2 < numExpandedPerms) {
                parsedPermissions.add(new BackupPermissionState(expandedPermissions.get(i2), "true".equals(parser.getAttributeValue(str, BackupHelper.ATTR_IS_GRANTED)), "true".equals(parser.getAttributeValue(str, BackupHelper.ATTR_USER_SET)), "true".equals(parser.getAttributeValue(str, BackupHelper.ATTR_USER_FIXED)), "true".equals(parser.getAttributeValue(str, BackupHelper.ATTR_WAS_REVIEWED))));
                i2++;
                str = null;
            }
            return parsedPermissions;
        }

        private static boolean isPermGrantedIncludingAppOp(Permission perm) {
            return perm.isGranted() && (!perm.affectsAppOp() || perm.isAppOpAllowed());
        }

        private static BackupPermissionState fromPermission(Permission perm, boolean appSupportsRuntimePermissions) {
            boolean isNotInDefaultGrantState;
            boolean permissionWasReviewed;
            int grantFlags = perm.getFlags();
            if ((grantFlags & 20) != 0) {
                return null;
            }
            if (!perm.isUserSet() && perm.isGrantedByDefault()) {
                return null;
            }
            if (appSupportsRuntimePermissions) {
                isNotInDefaultGrantState = isPermGrantedIncludingAppOp(perm);
                permissionWasReviewed = false;
            } else {
                boolean isNotInDefaultGrantState2 = isPermGrantedIncludingAppOp(perm);
                isNotInDefaultGrantState = !isNotInDefaultGrantState2;
                permissionWasReviewed = !perm.isReviewRequired();
            }
            if (isNotInDefaultGrantState || perm.isUserSet() || perm.isUserFixed() || permissionWasReviewed) {
                return new BackupPermissionState(perm.getName(), isPermGrantedIncludingAppOp(perm), perm.isUserSet(), perm.isUserFixed(), permissionWasReviewed);
            }
            return null;
        }

        static ArrayList<BackupPermissionState> fromPermissionGroup(AppPermissionGroup group) {
            ArrayList<BackupPermissionState> permissionsToRestore = new ArrayList<>();
            List<Permission> perms = group.getPermissions();
            boolean appSupportsRuntimePermissions = group.getApp().applicationInfo.targetSdkVersion >= 23;
            int numPerms = perms.size();
            for (int i = 0; i < numPerms; i++) {
                BackupPermissionState permState = fromPermission(perms.get(i), appSupportsRuntimePermissions);
                if (permState != null) {
                    permissionsToRestore.add(permState);
                }
            }
            return permissionsToRestore;
        }

        void writeAsXml(XmlSerializer serializer) throws IOException {
            serializer.startTag(null, BackupHelper.TAG_PERMISSION);
            serializer.attribute(null, BackupHelper.ATTR_PERMISSION_NAME, this.mPermissionName);
            if (this.mIsGranted) {
                serializer.attribute(null, BackupHelper.ATTR_IS_GRANTED, "true");
            }
            if (this.mIsUserSet) {
                serializer.attribute(null, BackupHelper.ATTR_USER_SET, "true");
            }
            if (this.mIsUserFixed) {
                serializer.attribute(null, BackupHelper.ATTR_USER_FIXED, "true");
            }
            if (this.mWasReviewed) {
                serializer.attribute(null, BackupHelper.ATTR_WAS_REVIEWED, "true");
            }
            serializer.endTag(null, BackupHelper.TAG_PERMISSION);
        }

        void restore(AppPermissions appPerms, boolean restoreBackgroundPerms) {
            AppPermissionGroup group = appPerms.getGroupForPermission(this.mPermissionName);
            if (group == null) {
                Log.w(BackupHelper.LOG_TAG, "Could not find group for " + this.mPermissionName + " in " + appPerms.getPackageInfo().packageName);
                return;
            }
            if (restoreBackgroundPerms != group.isBackgroundGroup()) {
                return;
            }
            Permission perm = group.getPermission(this.mPermissionName);
            if (this.mWasReviewed) {
                perm.unsetReviewRequired();
            }
            if (!group.isSystemFixed() && !group.isPolicyFixed() && !perm.isUserSet()) {
                if (this.mIsGranted) {
                    group.grantRuntimePermissions(false, this.mIsUserFixed, new String[]{this.mPermissionName});
                } else {
                    group.revokeRuntimePermissions(this.mIsUserFixed, new String[]{this.mPermissionName});
                }
                perm.setUserSet(this.mIsUserSet);
            }
        }
    }

    private static class BackupPackageState {
        final String mPackageName;
        private final ArrayList<BackupPermissionState> mPermissionsToRestore;

        private BackupPackageState(String packageName, ArrayList<BackupPermissionState> permissionsToRestore) {
            this.mPackageName = packageName;
            this.mPermissionsToRestore = permissionsToRestore;
        }

        static BackupPackageState parseFromXml(XmlPullParser parser, Context context, int backupPlatformVersion) throws XmlPullParserException, IOException {
            String packageName = parser.getAttributeValue(null, BackupHelper.ATTR_PACKAGE_NAME);
            if (packageName == null) {
                throw new XmlPullParserException("Found grant without pkg");
            }
            ArrayList<BackupPermissionState> permissionsToRestore = new ArrayList<>();
            while (true) {
                int next = parser.next();
                if (next == 1) {
                    throw new XmlPullParserException("Could not parse state for " + packageName);
                }
                if (next == 2) {
                    String name = parser.getName();
                    byte b = -1;
                    if (name.hashCode() == 3437296 && name.equals(BackupHelper.TAG_PERMISSION)) {
                        b = 0;
                    }
                    if (b != 0) {
                        Log.w(BackupHelper.LOG_TAG, "Found unexpected tag " + parser.getName() + " while restoring " + packageName);
                        BackupHelper.skipToEndOfTag(parser);
                    } else {
                        try {
                            permissionsToRestore.addAll(BackupPermissionState.parseFromXml(parser, context, backupPlatformVersion));
                        } catch (XmlPullParserException e) {
                            Log.e(BackupHelper.LOG_TAG, "Could not parse permission for " + packageName, e);
                        }
                        BackupHelper.skipToEndOfTag(parser);
                    }
                } else if (next == 3) {
                    return new BackupPackageState(packageName, permissionsToRestore);
                }
            }
        }

        static BackupPackageState fromAppPermissions(Context context, PackageInfo pkgInfo) {
            AppPermissions appPerms = new AppPermissions(context, pkgInfo, false, null);
            ArrayList<BackupPermissionState> permissionsToRestore = new ArrayList<>();
            List<AppPermissionGroup> groups = appPerms.getPermissionGroups();
            int numGroups = groups.size();
            for (int groupNum = 0; groupNum < numGroups; groupNum++) {
                AppPermissionGroup group = groups.get(groupNum);
                permissionsToRestore.addAll(BackupPermissionState.fromPermissionGroup(group));
                if (group.getBackgroundPermissions() != null) {
                    permissionsToRestore.addAll(BackupPermissionState.fromPermissionGroup(group.getBackgroundPermissions()));
                }
            }
            int groupNum2 = permissionsToRestore.size();
            if (groupNum2 == 0) {
                return null;
            }
            return new BackupPackageState(pkgInfo.packageName, permissionsToRestore);
        }

        void writeAsXml(XmlSerializer serializer) throws IOException {
            if (this.mPermissionsToRestore.size() == 0) {
                return;
            }
            serializer.startTag(null, BackupHelper.TAG_GRANT);
            serializer.attribute(null, BackupHelper.ATTR_PACKAGE_NAME, this.mPackageName);
            int numPerms = this.mPermissionsToRestore.size();
            for (int i = 0; i < numPerms; i++) {
                this.mPermissionsToRestore.get(i).writeAsXml(serializer);
            }
            serializer.endTag(null, BackupHelper.TAG_GRANT);
        }

        void restore(Context context, PackageInfo pkgInfo) {
            AppPermissions appPerms = new AppPermissions(context, pkgInfo, false, true, null);
            int numPerms = this.mPermissionsToRestore.size();
            for (int i = 0; i < numPerms; i++) {
                this.mPermissionsToRestore.get(i).restore(appPerms, false);
            }
            for (int i2 = 0; i2 < numPerms; i2++) {
                this.mPermissionsToRestore.get(i2).restore(appPerms, true);
            }
            int numGroups = appPerms.getPermissionGroups().size();
            for (int i3 = 0; i3 < numGroups; i3++) {
                AppPermissionGroup group = appPerms.getPermissionGroups().get(i3);
                if (group.areRuntimePermissionsGranted()) {
                    group.setUserFixed(false);
                }
                AppPermissionGroup bgGroup = group.getBackgroundPermissions();
                if (bgGroup != null && bgGroup.areRuntimePermissionsGranted()) {
                    bgGroup.setUserFixed(false);
                }
            }
            appPerms.persistChanges(true);
        }
    }
}
