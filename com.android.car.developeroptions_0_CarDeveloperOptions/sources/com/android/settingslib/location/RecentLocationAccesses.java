package com.android.settingslib.location;

import android.app.AppOpsManager;
import android.content.Context;
import android.content.PermissionChecker;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.graphics.drawable.Drawable;
import android.os.UserHandle;
import android.os.UserManager;
import android.util.IconDrawableFactory;
import android.util.Log;
import java.time.Clock;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;

/* JADX INFO: loaded from: classes.dex */
public class RecentLocationAccesses {
    static final String ANDROID_SYSTEM_PACKAGE_NAME = "android";
    static final int[] LOCATION_OPS = {1, 0};
    private static final String TAG = "RecentLocationAccesses";
    private final Clock mClock;
    private final Context mContext;
    private final IconDrawableFactory mDrawableFactory;
    private final PackageManager mPackageManager;

    public RecentLocationAccesses(Context context) {
        this(context, Clock.systemDefaultZone());
    }

    RecentLocationAccesses(Context context, Clock clock) {
        this.mContext = context;
        this.mPackageManager = context.getPackageManager();
        this.mDrawableFactory = IconDrawableFactory.newInstance(context);
        this.mClock = clock;
    }

    public List<Access> getAppList() {
        PackageManager packageManager;
        List list;
        int i;
        List<UserHandle> list2;
        boolean z;
        Access accessFromOps;
        PackageManager packageManager2 = this.mContext.getPackageManager();
        List packagesForOps = ((AppOpsManager) this.mContext.getSystemService("appops")).getPackagesForOps(LOCATION_OPS);
        int size = packagesForOps != null ? packagesForOps.size() : 0;
        ArrayList arrayList = new ArrayList(size);
        long jMillis = this.mClock.millis();
        List<UserHandle> userProfiles = ((UserManager) this.mContext.getSystemService("user")).getUserProfiles();
        int i2 = 0;
        while (i2 < size) {
            AppOpsManager.PackageOps packageOps = (AppOpsManager.PackageOps) packagesForOps.get(i2);
            String packageName = packageOps.getPackageName();
            int uid = packageOps.getUid();
            UserHandle userHandleForUid = UserHandle.getUserHandleForUid(uid);
            if (userProfiles.contains(userHandleForUid)) {
                int[] iArr = LOCATION_OPS;
                int length = iArr.length;
                int i3 = 0;
                while (i3 < length) {
                    list = packagesForOps;
                    String strOpToPermission = AppOpsManager.opToPermission(iArr[i3]);
                    i = size;
                    int permissionFlags = packageManager2.getPermissionFlags(strOpToPermission, packageName, userHandleForUid);
                    packageManager = packageManager2;
                    list2 = userProfiles;
                    if (PermissionChecker.checkPermissionForPreflight(this.mContext, strOpToPermission, -1, uid, packageName) == 0) {
                        if ((permissionFlags & 256) == 0) {
                            z = false;
                            break;
                        }
                        i3++;
                        size = i;
                        packagesForOps = list;
                        packageManager2 = packageManager;
                        userProfiles = list2;
                    } else {
                        if ((permissionFlags & 512) == 0) {
                            z = false;
                            break;
                        }
                        i3++;
                        size = i;
                        packagesForOps = list;
                        packageManager2 = packageManager;
                        userProfiles = list2;
                    }
                }
                packageManager = packageManager2;
                list = packagesForOps;
                i = size;
                list2 = userProfiles;
                z = true;
                if (z && (accessFromOps = getAccessFromOps(jMillis, packageOps)) != null) {
                    arrayList.add(accessFromOps);
                }
            } else {
                packageManager = packageManager2;
                list = packagesForOps;
                i = size;
                list2 = userProfiles;
            }
            i2++;
            size = i;
            packagesForOps = list;
            packageManager2 = packageManager;
            userProfiles = list2;
        }
        return arrayList;
    }

    public List<Access> getAppListSorted() {
        List<Access> appList = getAppList();
        Collections.sort(appList, Collections.reverseOrder(new Comparator<Access>(this) { // from class: com.android.settingslib.location.RecentLocationAccesses.1
            @Override // java.util.Comparator
            public int compare(Access access, Access access2) {
                return Long.compare(access.accessFinishTime, access2.accessFinishTime);
            }
        }));
        return appList;
    }

    private Access getAccessFromOps(long j, AppOpsManager.PackageOps packageOps) {
        String packageName = packageOps.getPackageName();
        long j2 = j - 86400000;
        Iterator it = packageOps.getOps().iterator();
        long lastAccessTime = 0;
        while (it.hasNext()) {
            lastAccessTime = ((AppOpsManager.OpEntry) it.next()).getLastAccessTime(13);
        }
        if (lastAccessTime < j2) {
            return null;
        }
        int userId = UserHandle.getUserId(packageOps.getUid());
        try {
            ApplicationInfo applicationInfoAsUser = this.mPackageManager.getApplicationInfoAsUser(packageName, 128, userId);
            if (applicationInfoAsUser == null) {
                Log.w(TAG, "Null application info retrieved for package " + packageName + ", userId " + userId);
                return null;
            }
            UserHandle userHandle = new UserHandle(userId);
            Drawable badgedIcon = this.mDrawableFactory.getBadgedIcon(applicationInfoAsUser, userId);
            CharSequence applicationLabel = this.mPackageManager.getApplicationLabel(applicationInfoAsUser);
            CharSequence userBadgedLabel = this.mPackageManager.getUserBadgedLabel(applicationLabel, userHandle);
            return new Access(packageName, userHandle, badgedIcon, applicationLabel, applicationLabel.toString().contentEquals(userBadgedLabel) ? null : userBadgedLabel, lastAccessTime);
        } catch (PackageManager.NameNotFoundException unused) {
            Log.w(TAG, "package name not found for " + packageName + ", userId " + userId);
            return null;
        }
    }

    public static class Access {
        public final long accessFinishTime;
        public final CharSequence contentDescription;
        public final Drawable icon;
        public final CharSequence label;
        public final String packageName;
        public final UserHandle userHandle;

        public Access(String str, UserHandle userHandle, Drawable drawable, CharSequence charSequence, CharSequence charSequence2, long j) {
            this.packageName = str;
            this.userHandle = userHandle;
            this.icon = drawable;
            this.label = charSequence;
            this.contentDescription = charSequence2;
            this.accessFinishTime = j;
        }
    }
}
