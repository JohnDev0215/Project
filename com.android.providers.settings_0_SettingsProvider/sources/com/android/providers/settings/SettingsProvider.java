package com.android.providers.settings;

import android.app.ActivityManager;
import android.app.AppGlobals;
import android.app.backup.BackupManager;
import android.app.job.JobInfo;
import android.app.job.JobScheduler;
import android.content.BroadcastReceiver;
import android.content.ComponentName;
import android.content.ContentProvider;
import android.content.ContentValues;
import android.content.Context;
import android.content.IntentFilter;
import android.content.om.IOverlayManager;
import android.content.pm.ApplicationInfo;
import android.content.pm.IPackageManager;
import android.content.pm.PackageInfo;
import android.content.pm.Signature;
import android.content.pm.UserInfo;
import android.content.res.Resources;
import android.database.Cursor;
import android.database.MatrixCursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteQueryBuilder;
import android.hardware.camera2.utils.ArrayUtils;
import android.net.Uri;
import android.os.Binder;
import android.os.Build;
import android.os.Bundle;
import android.os.DropBoxManager;
import android.os.Environment;
import android.os.FileUtils;
import android.os.Handler;
import android.os.HandlerThread;
import android.os.IUserRestrictionsListener;
import android.os.Looper;
import android.os.Message;
import android.os.ParcelFileDescriptor;
import android.os.PersistableBundle;
import android.os.RemoteCallback;
import android.os.RemoteException;
import android.os.SELinux;
import android.os.ServiceManager;
import android.os.UserHandle;
import android.os.UserManager;
import android.provider.DeviceConfig;
import android.provider.Settings;
import android.provider.settings.validators.SystemSettingsValidators;
import android.provider.settings.validators.Validator;
import android.text.TextUtils;
import android.util.ArrayMap;
import android.util.ArraySet;
import android.util.Slog;
import android.util.SparseArray;
import android.util.SparseBooleanArray;
import android.util.proto.ProtoOutputStream;
import com.android.internal.annotations.GuardedBy;
import com.android.internal.content.PackageMonitor;
import com.android.internal.os.BackgroundThread;
import com.android.providers.settings.SettingsState;
import com.android.server.SystemConfig;
import com.google.android.collect.Sets;
import java.io.File;
import java.io.FileDescriptor;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.ByteBuffer;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Set;
import java.util.regex.Pattern;
import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;
import libcore.util.HexEncoding;

/* JADX INFO: loaded from: classes.dex */
public class SettingsProvider extends ContentProvider {
    private static final String[] ALL_COLUMNS;
    private static final Set<String> CRITICAL_GLOBAL_SETTINGS;
    private static final Set<String> CRITICAL_SECURE_SETTINGS;
    private static final String[] LEGACY_SQL_COLUMNS;
    private static final Bundle NULL_SETTING_BUNDLE;
    private static final Set<String> OVERLAY_ALLOWED_GLOBAL_INSTANT_APP_SETTINGS;
    private static final Set<String> OVERLAY_ALLOWED_SECURE_INSTANT_APP_SETTINGS;
    private static final Set<String> OVERLAY_ALLOWED_SYSTEM_INSTANT_APP_SETTINGS;
    private static final Set<String> REMOVED_LEGACY_TABLES;
    static final Set<String> sGlobalMovedToSecureSettings;
    private static final Set<String> sSecureCloneToManagedSettings;
    static final Set<String> sSecureMovedToGlobalSettings;
    public static final Map<String, String> sSystemCloneFromParentOnDependency;
    private static final Set<String> sSystemCloneToManagedSettings;
    static final Set<String> sSystemMovedToGlobalSettings;
    static final Set<String> sSystemMovedToSecureSettings;

    @GuardedBy({"mLock"})
    private RemoteCallback mConfigMonitorCallback;

    @GuardedBy({"mLock"})
    private Handler mHandler;

    @GuardedBy({"mLock"})
    private HandlerThread mHandlerThread;
    private final Object mLock = new Object();
    private volatile IPackageManager mPackageManager;

    @GuardedBy({"mLock"})
    private SettingsRegistry mSettingsRegistry;
    private volatile UserManager mUserManager;

    private static String toDumpString(String str) {
        return str != null ? str : "{null}";
    }

    static {
        ArraySet arraySet = new ArraySet();
        REMOVED_LEGACY_TABLES = arraySet;
        arraySet.add("favorites");
        REMOVED_LEGACY_TABLES.add("old_favorites");
        REMOVED_LEGACY_TABLES.add("bluetooth_devices");
        REMOVED_LEGACY_TABLES.add("bookmarks");
        REMOVED_LEGACY_TABLES.add("android_metadata");
        LEGACY_SQL_COLUMNS = new String[]{"_id", "name", "value"};
        ALL_COLUMNS = new String[]{"_id", "name", "value", "is_preserved_in_restore"};
        NULL_SETTING_BUNDLE = Bundle.forPair("value", null);
        OVERLAY_ALLOWED_GLOBAL_INSTANT_APP_SETTINGS = new ArraySet();
        OVERLAY_ALLOWED_SYSTEM_INSTANT_APP_SETTINGS = new ArraySet();
        OVERLAY_ALLOWED_SECURE_INSTANT_APP_SETTINGS = new ArraySet();
        for (String str : Resources.getSystem().getStringArray(android.R.array.common_nicknames)) {
            OVERLAY_ALLOWED_GLOBAL_INSTANT_APP_SETTINGS.add(str);
        }
        for (String str2 : Resources.getSystem().getStringArray(android.R.array.config_allowedSecureInstantAppSettings)) {
            OVERLAY_ALLOWED_SYSTEM_INSTANT_APP_SETTINGS.add(str2);
        }
        for (String str3 : Resources.getSystem().getStringArray(android.R.array.config_allowedGlobalInstantAppSettings)) {
            OVERLAY_ALLOWED_SECURE_INSTANT_APP_SETTINGS.add(str3);
        }
        ArraySet arraySet2 = new ArraySet();
        CRITICAL_GLOBAL_SETTINGS = arraySet2;
        arraySet2.add("device_provisioned");
        ArraySet arraySet3 = new ArraySet();
        CRITICAL_SECURE_SETTINGS = arraySet3;
        arraySet3.add("user_setup_complete");
        ArraySet arraySet4 = new ArraySet();
        sSecureMovedToGlobalSettings = arraySet4;
        Settings.Secure.getMovedToGlobalSettings(arraySet4);
        ArraySet arraySet5 = new ArraySet();
        sSystemMovedToGlobalSettings = arraySet5;
        Settings.System.getMovedToGlobalSettings(arraySet5);
        ArraySet arraySet6 = new ArraySet();
        sSystemMovedToSecureSettings = arraySet6;
        Settings.System.getMovedToSecureSettings(arraySet6);
        ArraySet arraySet7 = new ArraySet();
        sGlobalMovedToSecureSettings = arraySet7;
        Settings.Global.getMovedToSecureSettings(arraySet7);
        ArraySet arraySet8 = new ArraySet();
        sSecureCloneToManagedSettings = arraySet8;
        Settings.Secure.getCloneToManagedProfileSettings(arraySet8);
        ArraySet arraySet9 = new ArraySet();
        sSystemCloneToManagedSettings = arraySet9;
        Settings.System.getCloneToManagedProfileSettings(arraySet9);
        ArrayMap arrayMap = new ArrayMap();
        sSystemCloneFromParentOnDependency = arrayMap;
        Settings.System.getCloneFromParentOnValueSettings(arrayMap);
    }

    public static int makeKey(int i, int i2) {
        return SettingsState.makeKey(i, i2);
    }

    public static int getTypeFromKey(int i) {
        return SettingsState.getTypeFromKey(i);
    }

    public static int getUserIdFromKey(int i) {
        return SettingsState.getUserIdFromKey(i);
    }

    @Override // android.content.ContentProvider
    public boolean onCreate() {
        Settings.setInSystemServer();
        synchronized (this.mLock) {
            this.mUserManager = UserManager.get(getContext());
            this.mPackageManager = AppGlobals.getPackageManager();
            HandlerThread handlerThread = new HandlerThread("SettingsProvider", 10);
            this.mHandlerThread = handlerThread;
            handlerThread.start();
            this.mHandler = new Handler(this.mHandlerThread.getLooper());
            this.mSettingsRegistry = new SettingsRegistry();
        }
        this.mHandler.post(new Runnable() { // from class: com.android.providers.settings.-$$Lambda$SettingsProvider$h_zJ8TmggsGxbxfR60fhjb7Ynw4
            @Override // java.lang.Runnable
            public final void run() {
                this.f$0.lambda$onCreate$0$SettingsProvider();
            }
        });
        ServiceManager.addService("settings", new SettingsService(this));
        ServiceManager.addService("device_config", new DeviceConfigService(this));
        return true;
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: lambda$onCreate$0, reason: merged with bridge method [inline-methods] */
    public /* synthetic */ void lambda$onCreate$0$SettingsProvider() {
        registerBroadcastReceivers();
        startWatchingUserRestrictionChanges();
    }

    /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
    /* JADX WARN: Removed duplicated region for block: B:68:0x00fa  */
    @Override // android.content.ContentProvider
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public android.os.Bundle call(java.lang.String r11, java.lang.String r12, android.os.Bundle r13) {
        /*
            Method dump skipped, instruction units count: 754
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.android.providers.settings.SettingsProvider.call(java.lang.String, java.lang.String, android.os.Bundle):android.os.Bundle");
    }

    @Override // android.content.ContentProvider
    public String getType(Uri uri) {
        Arguments arguments = new Arguments(uri, null, null, true);
        if (TextUtils.isEmpty(arguments.name)) {
            return "vnd.android.cursor.dir/" + arguments.table;
        }
        return "vnd.android.cursor.item/" + arguments.table;
    }

    /* JADX WARN: Removed duplicated region for block: B:22:0x006a  */
    @Override // android.content.ContentProvider
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public android.database.Cursor query(android.net.Uri r7, java.lang.String[] r8, java.lang.String r9, java.lang.String[] r10, java.lang.String r11) {
        /*
            r6 = this;
            java.lang.StringBuilder r11 = new java.lang.StringBuilder
            r11.<init>()
            java.lang.String r0 = "query() for user: "
            r11.append(r0)
            int r0 = android.os.UserHandle.getCallingUserId()
            r11.append(r0)
            java.lang.String r11 = r11.toString()
            java.lang.String r0 = "SettingsProvider"
            android.util.Slog.v(r0, r11)
            com.android.providers.settings.SettingsProvider$Arguments r11 = new com.android.providers.settings.SettingsProvider$Arguments
            r0 = 1
            r11.<init>(r7, r9, r10, r0)
            java.lang.String[] r9 = normalizeProjection(r8)
            java.util.Set<java.lang.String> r10 = com.android.providers.settings.SettingsProvider.REMOVED_LEGACY_TABLES
            java.lang.String r1 = r11.table
            boolean r10 = r10.contains(r1)
            r1 = 0
            if (r10 == 0) goto L35
            android.database.MatrixCursor r6 = new android.database.MatrixCursor
            r6.<init>(r9, r1)
            return r6
        L35:
            java.lang.String r10 = r11.table
            r2 = -1
            int r3 = r10.hashCode()
            r4 = -1243020381(0xffffffffb5e903a3, float:-1.7360911E-6)
            r5 = 2
            if (r3 == r4) goto L61
            r1 = -906273929(0xffffffffc9fb5b77, float:-2059118.9)
            if (r3 == r1) goto L57
            r1 = -887328209(0xffffffffcb1c722f, float:-1.0252847E7)
            if (r3 == r1) goto L4d
            goto L6a
        L4d:
            java.lang.String r1 = "system"
            boolean r10 = r10.equals(r1)
            if (r10 == 0) goto L6a
            r1 = r5
            goto L6b
        L57:
            java.lang.String r1 = "secure"
            boolean r10 = r10.equals(r1)
            if (r10 == 0) goto L6a
            r1 = r0
            goto L6b
        L61:
            java.lang.String r3 = "global"
            boolean r10 = r10.equals(r3)
            if (r10 == 0) goto L6a
            goto L6b
        L6a:
            r1 = r2
        L6b:
            if (r1 == 0) goto Lb4
            if (r1 == r0) goto L9e
            if (r1 != r5) goto L87
            int r7 = android.os.UserHandle.getCallingUserId()
            java.lang.String r10 = r11.name
            if (r10 == 0) goto L82
            com.android.providers.settings.SettingsState$Setting r6 = r6.getSystemSetting(r10, r7)
            android.database.MatrixCursor r6 = packageSettingForQuery(r6, r9)
            return r6
        L82:
            android.database.Cursor r6 = r6.getAllSystemSettings(r7, r8)
            return r6
        L87:
            java.lang.IllegalArgumentException r6 = new java.lang.IllegalArgumentException
            java.lang.StringBuilder r8 = new java.lang.StringBuilder
            r8.<init>()
            java.lang.String r9 = "Invalid Uri path:"
            r8.append(r9)
            r8.append(r7)
            java.lang.String r7 = r8.toString()
            r6.<init>(r7)
            throw r6
        L9e:
            int r7 = android.os.UserHandle.getCallingUserId()
            java.lang.String r10 = r11.name
            if (r10 == 0) goto Laf
            com.android.providers.settings.SettingsState$Setting r6 = r6.getSecureSetting(r10, r7)
            android.database.MatrixCursor r6 = packageSettingForQuery(r6, r9)
            return r6
        Laf:
            android.database.Cursor r6 = r6.getAllSecureSettings(r7, r8)
            return r6
        Lb4:
            java.lang.String r7 = r11.name
            if (r7 == 0) goto Lc1
            com.android.providers.settings.SettingsState$Setting r6 = r6.getGlobalSetting(r7)
            android.database.MatrixCursor r6 = packageSettingForQuery(r6, r9)
            return r6
        Lc1:
            android.database.Cursor r6 = r6.getAllGlobalSettings(r8)
            return r6
        */
        throw new UnsupportedOperationException("Method not decompiled: com.android.providers.settings.SettingsProvider.query(android.net.Uri, java.lang.String[], java.lang.String, java.lang.String[], java.lang.String):android.database.Cursor");
    }

    private ArrayList<String> buildSettingsList(Cursor cursor) {
        ArrayList<String> arrayList = new ArrayList<>();
        while (cursor != null) {
            try {
                if (!cursor.moveToNext()) {
                    break;
                }
                arrayList.add(cursor.getString(1) + "=" + cursor.getString(2));
            } finally {
                if (cursor != null) {
                    cursor.close();
                }
            }
        }
        return arrayList;
    }

    @Override // android.content.ContentProvider
    public Uri insert(Uri uri, ContentValues contentValues) {
        Slog.v("SettingsProvider", "insert() for user: " + UserHandle.getCallingUserId() + " uri: " + uri + " values: " + contentValues);
        String validTableOrThrow = getValidTableOrThrow(uri);
        if (REMOVED_LEGACY_TABLES.contains(validTableOrThrow)) {
            return null;
        }
        String asString = contentValues.getAsString("name");
        if (!isKeyValid(asString)) {
            return null;
        }
        String asString2 = contentValues.getAsString("value");
        byte b = -1;
        int iHashCode = validTableOrThrow.hashCode();
        if (iHashCode != -1243020381) {
            if (iHashCode != -906273929) {
                if (iHashCode == -887328209 && validTableOrThrow.equals("system")) {
                    b = 2;
                }
            } else if (validTableOrThrow.equals("secure")) {
                b = 1;
            }
        } else if (validTableOrThrow.equals("global")) {
            b = 0;
        }
        if (b != 0) {
            if (b != 1) {
                if (b == 2) {
                    if (insertSystemSetting(asString, asString2, UserHandle.getCallingUserId(), false, UserHandle.getCallingPid())) {
                        return Uri.withAppendedPath(Settings.System.CONTENT_URI, asString);
                    }
                } else {
                    throw new IllegalArgumentException("Bad Uri path:" + uri);
                }
            } else if (insertSecureSetting(asString, asString2, null, false, UserHandle.getCallingUserId(), false, false, UserHandle.getCallingPid())) {
                return Uri.withAppendedPath(Settings.Secure.CONTENT_URI, asString);
            }
        } else if (insertGlobalSetting(asString, asString2, null, false, UserHandle.getCallingUserId(), false, false, UserHandle.getCallingPid())) {
            return Uri.withAppendedPath(Settings.Global.CONTENT_URI, asString);
        }
        return null;
    }

    @Override // android.content.ContentProvider
    public int bulkInsert(Uri uri, ContentValues[] contentValuesArr) {
        Slog.v("SettingsProvider", "bulkInsert() for user: " + UserHandle.getCallingUserId());
        int i = 0;
        for (ContentValues contentValues : contentValuesArr) {
            if (insert(uri, contentValues) != null) {
                i++;
            }
        }
        return i;
    }

    @Override // android.content.ContentProvider
    public int delete(Uri uri, String str, String[] strArr) {
        Slog.v("SettingsProvider", "delete() for user: " + UserHandle.getCallingUserId());
        Arguments arguments = new Arguments(uri, str, strArr, false);
        if (REMOVED_LEGACY_TABLES.contains(arguments.table) || !isKeyValid(arguments.name)) {
            return 0;
        }
        String str2 = arguments.table;
        byte b = -1;
        int iHashCode = str2.hashCode();
        if (iHashCode != -1243020381) {
            if (iHashCode != -906273929) {
                if (iHashCode == -887328209 && str2.equals("system")) {
                    b = 2;
                }
            } else if (str2.equals("secure")) {
                b = 1;
            }
        } else if (str2.equals("global")) {
            b = 0;
        }
        if (b == 0) {
            return deleteGlobalSetting(arguments.name, UserHandle.getCallingUserId(), false) ? 1 : 0;
        }
        if (b == 1) {
            return deleteSecureSetting(arguments.name, UserHandle.getCallingUserId(), false) ? 1 : 0;
        }
        if (b == 2) {
            return deleteSystemSetting(arguments.name, UserHandle.getCallingUserId()) ? 1 : 0;
        }
        throw new IllegalArgumentException("Bad Uri path:" + uri);
    }

    /* JADX WARN: Removed duplicated region for block: B:24:0x0074  */
    @Override // android.content.ContentProvider
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public int update(android.net.Uri r10, android.content.ContentValues r11, java.lang.String r12, java.lang.String[] r13) {
        /*
            r9 = this;
            java.lang.StringBuilder r0 = new java.lang.StringBuilder
            r0.<init>()
            java.lang.String r1 = "update() for user: "
            r0.append(r1)
            int r1 = android.os.UserHandle.getCallingUserId()
            r0.append(r1)
            java.lang.String r0 = r0.toString()
            java.lang.String r1 = "SettingsProvider"
            android.util.Slog.v(r1, r0)
            com.android.providers.settings.SettingsProvider$Arguments r0 = new com.android.providers.settings.SettingsProvider$Arguments
            r1 = 0
            r0.<init>(r10, r12, r13, r1)
            java.util.Set<java.lang.String> r12 = com.android.providers.settings.SettingsProvider.REMOVED_LEGACY_TABLES
            java.lang.String r13 = r0.table
            boolean r12 = r12.contains(r13)
            if (r12 == 0) goto L2b
            return r1
        L2b:
            java.lang.String r12 = "name"
            java.lang.String r12 = r11.getAsString(r12)
            boolean r12 = isKeyValid(r12)
            if (r12 != 0) goto L38
            return r1
        L38:
            java.lang.String r12 = "value"
            java.lang.String r4 = r11.getAsString(r12)
            java.lang.String r11 = r0.table
            r12 = -1
            int r13 = r11.hashCode()
            r2 = -1243020381(0xffffffffb5e903a3, float:-1.7360911E-6)
            r3 = 2
            r5 = 1
            if (r13 == r2) goto L6b
            r1 = -906273929(0xffffffffc9fb5b77, float:-2059118.9)
            if (r13 == r1) goto L61
            r1 = -887328209(0xffffffffcb1c722f, float:-1.0252847E7)
            if (r13 == r1) goto L57
            goto L74
        L57:
            java.lang.String r13 = "system"
            boolean r11 = r11.equals(r13)
            if (r11 == 0) goto L74
            r1 = r3
            goto L75
        L61:
            java.lang.String r13 = "secure"
            boolean r11 = r11.equals(r13)
            if (r11 == 0) goto L74
            r1 = r5
            goto L75
        L6b:
            java.lang.String r13 = "global"
            boolean r11 = r11.equals(r13)
            if (r11 == 0) goto L74
            goto L75
        L74:
            r1 = r12
        L75:
            if (r1 == 0) goto Lac
            if (r1 == r5) goto L9d
            if (r1 != r3) goto L86
            int r10 = android.os.UserHandle.getCallingUserId()
            java.lang.String r11 = r0.name
            boolean r9 = r9.updateSystemSetting(r11, r4, r10)
            return r9
        L86:
            java.lang.IllegalArgumentException r9 = new java.lang.IllegalArgumentException
            java.lang.StringBuilder r11 = new java.lang.StringBuilder
            r11.<init>()
            java.lang.String r12 = "Invalid Uri path:"
            r11.append(r12)
            r11.append(r10)
            java.lang.String r10 = r11.toString()
            r9.<init>(r10)
            throw r9
        L9d:
            int r7 = android.os.UserHandle.getCallingUserId()
            java.lang.String r3 = r0.name
            r5 = 0
            r6 = 0
            r8 = 0
            r2 = r9
            boolean r9 = r2.updateSecureSetting(r3, r4, r5, r6, r7, r8)
            return r9
        Lac:
            int r7 = android.os.UserHandle.getCallingUserId()
            java.lang.String r3 = r0.name
            r5 = 0
            r6 = 0
            r8 = 0
            r2 = r9
            boolean r9 = r2.updateGlobalSetting(r3, r4, r5, r6, r7, r8)
            return r9
        */
        throw new UnsupportedOperationException("Method not decompiled: com.android.providers.settings.SettingsProvider.update(android.net.Uri, android.content.ContentValues, java.lang.String, java.lang.String[]):int");
    }

    @Override // android.content.ContentProvider
    public ParcelFileDescriptor openFile(Uri uri, String str) throws FileNotFoundException {
        String str2;
        String str3;
        int iResolveOwningUserIdForSystemSettingLocked;
        int userIdFromUri = ContentProvider.getUserIdFromUri(uri, UserHandle.getCallingUserId());
        if (userIdFromUri != UserHandle.getCallingUserId()) {
            getContext().enforceCallingPermission("android.permission.INTERACT_ACROSS_USERS", "Access files from the settings of another user");
        }
        Uri uriWithoutUserId = ContentProvider.getUriWithoutUserId(uri);
        if (Settings.System.RINGTONE_CACHE_URI.equals(uriWithoutUserId)) {
            str2 = "ringtone";
            str3 = "ringtone_cache";
        } else if (Settings.System.NOTIFICATION_SOUND_CACHE_URI.equals(uriWithoutUserId)) {
            str2 = "notification_sound";
            str3 = "notification_sound_cache";
        } else {
            if (!Settings.System.ALARM_ALERT_CACHE_URI.equals(uriWithoutUserId)) {
                throw new FileNotFoundException("Direct file access no longer supported; ringtone playback is available through android.media.Ringtone");
            }
            str2 = "alarm_alert";
            str3 = "alarm_alert_cache";
        }
        synchronized (this.mLock) {
            iResolveOwningUserIdForSystemSettingLocked = resolveOwningUserIdForSystemSettingLocked(userIdFromUri, str2);
        }
        return ParcelFileDescriptor.open(new File(getRingtoneCacheDir(iResolveOwningUserIdForSystemSettingLocked), str3), ParcelFileDescriptor.parseMode(str));
    }

    private File getRingtoneCacheDir(int i) {
        File file = new File(Environment.getDataSystemDeDirectory(i), "ringtones");
        file.mkdir();
        SELinux.restorecon(file);
        return file;
    }

    void dumpProto(FileDescriptor fileDescriptor) {
        ProtoOutputStream protoOutputStream = new ProtoOutputStream(fileDescriptor);
        synchronized (this.mLock) {
            SettingsProtoDumpUtil.dumpProtoLocked(this.mSettingsRegistry, protoOutputStream);
        }
        protoOutputStream.flush();
    }

    public void dumpInternal(FileDescriptor fileDescriptor, PrintWriter printWriter, String[] strArr) {
        synchronized (this.mLock) {
            long jClearCallingIdentity = Binder.clearCallingIdentity();
            try {
                SparseBooleanArray knownUsersLocked = this.mSettingsRegistry.getKnownUsersLocked();
                int size = knownUsersLocked.size();
                for (int i = 0; i < size; i++) {
                    dumpForUserLocked(knownUsersLocked.keyAt(i), printWriter);
                }
            } finally {
                Binder.restoreCallingIdentity(jClearCallingIdentity);
            }
        }
    }

    @GuardedBy({"mLock"})
    private void dumpForUserLocked(int i, PrintWriter printWriter) {
        if (i == 0) {
            printWriter.println("CONFIG SETTINGS (user " + i + ")");
            SettingsState settingsLocked = this.mSettingsRegistry.getSettingsLocked(4, 0);
            if (settingsLocked != null) {
                dumpSettingsLocked(settingsLocked, printWriter);
                printWriter.println();
                settingsLocked.dumpHistoricalOperations(printWriter);
            }
            printWriter.println("GLOBAL SETTINGS (user " + i + ")");
            SettingsState settingsLocked2 = this.mSettingsRegistry.getSettingsLocked(0, 0);
            if (settingsLocked2 != null) {
                dumpSettingsLocked(settingsLocked2, printWriter);
                printWriter.println();
                settingsLocked2.dumpHistoricalOperations(printWriter);
            }
        }
        printWriter.println("SECURE SETTINGS (user " + i + ")");
        SettingsState settingsLocked3 = this.mSettingsRegistry.getSettingsLocked(2, i);
        if (settingsLocked3 != null) {
            dumpSettingsLocked(settingsLocked3, printWriter);
            printWriter.println();
            settingsLocked3.dumpHistoricalOperations(printWriter);
        }
        printWriter.println("SYSTEM SETTINGS (user " + i + ")");
        SettingsState settingsLocked4 = this.mSettingsRegistry.getSettingsLocked(1, i);
        if (settingsLocked4 != null) {
            dumpSettingsLocked(settingsLocked4, printWriter);
            printWriter.println();
            settingsLocked4.dumpHistoricalOperations(printWriter);
        }
    }

    private void dumpSettingsLocked(SettingsState settingsState, PrintWriter printWriter) {
        List<String> settingNamesLocked = settingsState.getSettingNamesLocked();
        int size = settingNamesLocked.size();
        for (int i = 0; i < size; i++) {
            String str = settingNamesLocked.get(i);
            SettingsState.Setting settingLocked = settingsState.getSettingLocked(str);
            printWriter.print("_id:");
            printWriter.print(toDumpString(settingLocked.getId()));
            printWriter.print(" name:");
            printWriter.print(toDumpString(str));
            if (settingLocked.getPackageName() != null) {
                printWriter.print(" pkg:");
                printWriter.print(settingLocked.getPackageName());
            }
            printWriter.print(" value:");
            printWriter.print(toDumpString(settingLocked.getValue()));
            if (settingLocked.getDefaultValue() != null) {
                printWriter.print(" default:");
                printWriter.print(settingLocked.getDefaultValue());
                printWriter.print(" defaultSystemSet:");
                printWriter.print(settingLocked.isDefaultFromSystem());
            }
            if (settingLocked.getTag() != null) {
                printWriter.print(" tag:");
                printWriter.print(settingLocked.getTag());
            }
            printWriter.println();
        }
    }

    private void registerBroadcastReceivers() {
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction("android.intent.action.USER_REMOVED");
        intentFilter.addAction("android.intent.action.USER_STOPPED");
        getContext().registerReceiver(new BroadcastReceiver() { // from class: com.android.providers.settings.SettingsProvider.1
            /* JADX WARN: Removed duplicated region for block: B:13:0x002f  */
            @Override // android.content.BroadcastReceiver
            /*
                Code decompiled incorrectly, please refer to instructions dump.
                To view partially-correct code enable 'Show inconsistent code' option in preferences
            */
            public void onReceive(android.content.Context r5, android.content.Intent r6) {
                /*
                    r4 = this;
                    java.lang.String r5 = "android.intent.extra.user_handle"
                    r0 = 0
                    int r5 = r6.getIntExtra(r5, r0)
                    java.lang.String r6 = r6.getAction()
                    int r1 = r6.hashCode()
                    r2 = -2061058799(0xffffffff8526bd11, float:-7.8400085E-36)
                    r3 = 1
                    if (r1 == r2) goto L25
                    r2 = -742246786(0xffffffffd3c2367e, float:-1.6682758E12)
                    if (r1 == r2) goto L1b
                    goto L2f
                L1b:
                    java.lang.String r1 = "android.intent.action.USER_STOPPED"
                    boolean r6 = r6.equals(r1)
                    if (r6 == 0) goto L2f
                    r6 = r3
                    goto L30
                L25:
                    java.lang.String r1 = "android.intent.action.USER_REMOVED"
                    boolean r6 = r6.equals(r1)
                    if (r6 == 0) goto L2f
                    r6 = r0
                    goto L30
                L2f:
                    r6 = -1
                L30:
                    if (r6 == 0) goto L4a
                    if (r6 == r3) goto L35
                    goto L5b
                L35:
                    com.android.providers.settings.SettingsProvider r6 = com.android.providers.settings.SettingsProvider.this
                    java.lang.Object r6 = com.android.providers.settings.SettingsProvider.access$000(r6)
                    monitor-enter(r6)
                    com.android.providers.settings.SettingsProvider r4 = com.android.providers.settings.SettingsProvider.this     // Catch: java.lang.Throwable -> L47
                    com.android.providers.settings.SettingsProvider$SettingsRegistry r4 = com.android.providers.settings.SettingsProvider.access$100(r4)     // Catch: java.lang.Throwable -> L47
                    r4.removeUserStateLocked(r5, r0)     // Catch: java.lang.Throwable -> L47
                    monitor-exit(r6)     // Catch: java.lang.Throwable -> L47
                    goto L5b
                L47:
                    r4 = move-exception
                    monitor-exit(r6)     // Catch: java.lang.Throwable -> L47
                    throw r4
                L4a:
                    com.android.providers.settings.SettingsProvider r6 = com.android.providers.settings.SettingsProvider.this
                    java.lang.Object r6 = com.android.providers.settings.SettingsProvider.access$000(r6)
                    monitor-enter(r6)
                    com.android.providers.settings.SettingsProvider r4 = com.android.providers.settings.SettingsProvider.this     // Catch: java.lang.Throwable -> L5c
                    com.android.providers.settings.SettingsProvider$SettingsRegistry r4 = com.android.providers.settings.SettingsProvider.access$100(r4)     // Catch: java.lang.Throwable -> L5c
                    r4.removeUserStateLocked(r5, r3)     // Catch: java.lang.Throwable -> L5c
                    monitor-exit(r6)     // Catch: java.lang.Throwable -> L5c
                L5b:
                    return
                L5c:
                    r4 = move-exception
                    monitor-exit(r6)     // Catch: java.lang.Throwable -> L5c
                    throw r4
                */
                throw new UnsupportedOperationException("Method not decompiled: com.android.providers.settings.SettingsProvider.AnonymousClass1.onReceive(android.content.Context, android.content.Intent):void");
            }
        }, intentFilter);
        new PackageMonitor() { // from class: com.android.providers.settings.SettingsProvider.2
            public void onPackageRemoved(String str, int i) {
                synchronized (SettingsProvider.this.mLock) {
                    SettingsProvider.this.mSettingsRegistry.removeSettingsForPackageLocked(str, UserHandle.getUserId(i));
                }
            }

            public void onUidRemoved(int i) {
                synchronized (SettingsProvider.this.mLock) {
                    SettingsProvider.this.mSettingsRegistry.onUidRemovedLocked(i);
                }
            }

            public void onPackageDataCleared(String str, int i) {
                synchronized (SettingsProvider.this.mLock) {
                    SettingsProvider.this.mSettingsRegistry.removeSettingsForPackageLocked(str, UserHandle.getUserId(i));
                }
            }
        }.register(getContext(), BackgroundThread.getHandler().getLooper(), UserHandle.ALL, true);
    }

    private void startWatchingUserRestrictionChanges() {
        this.mUserManager.addUserRestrictionsListener(new IUserRestrictionsListener.Stub() { // from class: com.android.providers.settings.SettingsProvider.3
            public void onUserRestrictionsChanged(int i, Bundle bundle, Bundle bundle2) {
                long jClearCallingIdentity;
                Set restrictionDiff = SettingsProvider.getRestrictionDiff(bundle2, bundle);
                if (restrictionDiff.contains("no_share_location")) {
                    jClearCallingIdentity = Binder.clearCallingIdentity();
                    try {
                        synchronized (SettingsProvider.this.mLock) {
                            SettingsState.Setting secureSetting = SettingsProvider.this.getSecureSetting("location_mode", i);
                            SettingsProvider.this.updateSecureSetting("location_mode", secureSetting != null ? secureSetting.getValue() : null, null, true, i, true);
                            SettingsState.Setting secureSetting2 = SettingsProvider.this.getSecureSetting("location_providers_allowed", i);
                            SettingsProvider.this.updateSecureSetting("location_providers_allowed", secureSetting2 != null ? secureSetting2.getValue() : null, null, true, i, true);
                        }
                        Binder.restoreCallingIdentity(jClearCallingIdentity);
                    } finally {
                    }
                }
                if (restrictionDiff.contains("no_install_unknown_sources") || restrictionDiff.contains("no_install_unknown_sources_globally")) {
                    jClearCallingIdentity = Binder.clearCallingIdentity();
                    try {
                        synchronized (SettingsProvider.this.mLock) {
                            SettingsState.Setting globalSetting = SettingsProvider.this.getGlobalSetting("install_non_market_apps");
                            SettingsProvider.this.updateGlobalSetting("install_non_market_apps", globalSetting != null ? globalSetting.getValue() : null, null, true, i, true);
                        }
                        Binder.restoreCallingIdentity(jClearCallingIdentity);
                    } finally {
                    }
                }
                if (restrictionDiff.contains("no_debugging_features")) {
                    jClearCallingIdentity = Binder.clearCallingIdentity();
                    try {
                        synchronized (SettingsProvider.this.mLock) {
                            SettingsState.Setting globalSetting2 = SettingsProvider.this.getGlobalSetting("adb_enabled");
                            SettingsProvider.this.updateGlobalSetting("adb_enabled", globalSetting2 != null ? globalSetting2.getValue() : null, null, true, i, true);
                            SettingsState.Setting globalSetting3 = SettingsProvider.this.getGlobalSetting("adb_wifi_enabled");
                            SettingsProvider.this.updateGlobalSetting("adb_wifi_enabled", globalSetting3 != null ? globalSetting3.getValue() : null, null, true, i, true);
                        }
                    } finally {
                    }
                }
                if (restrictionDiff.contains("ensure_verify_apps")) {
                    jClearCallingIdentity = Binder.clearCallingIdentity();
                    try {
                        synchronized (SettingsProvider.this.mLock) {
                            SettingsState.Setting globalSetting4 = SettingsProvider.this.getGlobalSetting("verifier_verify_adb_installs");
                            SettingsProvider.this.updateGlobalSetting("verifier_verify_adb_installs", globalSetting4 != null ? globalSetting4.getValue() : null, null, true, i, true);
                        }
                    } finally {
                    }
                }
                if (restrictionDiff.contains("no_config_mobile_networks")) {
                    jClearCallingIdentity = Binder.clearCallingIdentity();
                    try {
                        synchronized (SettingsProvider.this.mLock) {
                            SettingsState.Setting globalSetting5 = SettingsProvider.this.getGlobalSetting("preferred_network_mode");
                            SettingsProvider.this.updateGlobalSetting("preferred_network_mode", globalSetting5 != null ? globalSetting5.getValue() : null, null, true, i, true);
                        }
                    } finally {
                    }
                }
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static Set<String> getRestrictionDiff(Bundle bundle, Bundle bundle2) {
        ArraySet<String> arraySetNewArraySet = Sets.newArraySet();
        arraySetNewArraySet.addAll(bundle.keySet());
        arraySetNewArraySet.addAll(bundle2.keySet());
        ArraySet arraySetNewArraySet2 = Sets.newArraySet();
        for (String str : arraySetNewArraySet) {
            if (bundle.getBoolean(str) != bundle2.getBoolean(str)) {
                arraySetNewArraySet2.add(str);
            }
        }
        return arraySetNewArraySet2;
    }

    private SettingsState.Setting getConfigSetting(String str) {
        SettingsState.Setting settingLocked;
        Slog.v("SettingsProvider", "getConfigSetting(" + str + ")");
        DeviceConfig.enforceReadPermission(getContext(), str.split("/")[0]);
        synchronized (this.mLock) {
            settingLocked = this.mSettingsRegistry.getSettingLocked(4, 0, str);
        }
        return settingLocked;
    }

    private boolean insertConfigSetting(String str, String str2, boolean z) {
        Slog.v("SettingsProvider", "insertConfigSetting(" + str + ", " + str2 + ", " + z + ")");
        return mutateConfigSetting(str, str2, null, z, 1, 0);
    }

    private boolean setAllConfigSettings(String str, Map<String, String> map) {
        boolean configSettingsLocked;
        Slog.v("SettingsProvider", "setAllConfigSettings for prefix: " + str);
        enforceWritePermission("android.permission.WRITE_DEVICE_CONFIG");
        synchronized (this.mLock) {
            configSettingsLocked = this.mSettingsRegistry.setConfigSettingsLocked(makeKey(4, 0), str, map, resolveCallingPackage());
        }
        return configSettingsLocked;
    }

    private boolean deleteConfigSetting(String str) {
        Slog.v("SettingsProvider", "deleteConfigSetting(" + str + ")");
        return mutateConfigSetting(str, null, null, false, 2, 0);
    }

    private void resetConfigSetting(int i, String str) {
        Slog.v("SettingsProvider", "resetConfigSetting(" + i + ", " + str + ")");
        mutateConfigSetting(null, null, str, false, 4, i);
    }

    private boolean mutateConfigSetting(String str, String str2, String str3, boolean z, int i, int i2) {
        enforceWritePermission("android.permission.WRITE_DEVICE_CONFIG");
        synchronized (this.mLock) {
            if (i == 1) {
                return this.mSettingsRegistry.insertSettingLocked(4, 0, str, str2, null, z, true, resolveCallingPackage(), false, null, false);
            }
            if (i == 2) {
                return this.mSettingsRegistry.deleteSettingLocked(4, 0, str, false, null);
            }
            if (i != 4) {
                return false;
            }
            this.mSettingsRegistry.resetSettingsLocked(4, 0, resolveCallingPackage(), i2, null, str3);
            return true;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public HashMap<String, String> getAllConfigFlags(String str) {
        HashMap<String, String> map;
        Slog.v("SettingsProvider", "getAllConfigFlags() for " + str);
        DeviceConfig.enforceReadPermission(getContext(), str != null ? str.split("/")[0] : null);
        synchronized (this.mLock) {
            SettingsState settingsLocked = this.mSettingsRegistry.getSettingsLocked(4, 0);
            List<String> settingsNamesLocked = getSettingsNamesLocked(4, 0);
            int size = settingsNamesLocked.size();
            map = new HashMap<>(settingsNamesLocked.size());
            for (int i = 0; i < size; i++) {
                SettingsState.Setting settingLocked = settingsLocked.getSettingLocked(settingsNamesLocked.get(i));
                if (str == null || settingLocked.getName().startsWith(str)) {
                    map.put(settingLocked.getName(), settingLocked.getValue());
                }
            }
        }
        return map;
    }

    private Cursor getAllGlobalSettings(String[] strArr) {
        MatrixCursor matrixCursor;
        Slog.v("SettingsProvider", "getAllGlobalSettings()");
        synchronized (this.mLock) {
            SettingsState settingsLocked = this.mSettingsRegistry.getSettingsLocked(0, 0);
            List<String> settingsNamesLocked = getSettingsNamesLocked(0, 0);
            int size = settingsNamesLocked.size();
            matrixCursor = new MatrixCursor(normalizeProjection(strArr), size);
            for (int i = 0; i < size; i++) {
                appendSettingToCursor(matrixCursor, settingsLocked.getSettingLocked(settingsNamesLocked.get(i)));
            }
        }
        return matrixCursor;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public SettingsState.Setting getGlobalSetting(String str) {
        SettingsState.Setting settingLocked;
        Slog.v("SettingsProvider", "getGlobalSetting(" + str + ")");
        enforceSettingReadable(str, 0, UserHandle.getCallingUserId());
        synchronized (this.mLock) {
            settingLocked = this.mSettingsRegistry.getSettingLocked(0, 0, str);
        }
        return settingLocked;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public boolean updateGlobalSetting(String str, String str2, String str3, boolean z, int i, boolean z2) {
        Slog.v("SettingsProvider", "updateGlobalSetting(" + str + ", " + str2 + ", , " + str3 + ", " + z + ", " + i + ", " + z2 + ")");
        return mutateGlobalSetting(str, str2, str3, z, i, 3, z2, 0);
    }

    private boolean insertGlobalSetting(String str, String str2, String str3, boolean z, int i, boolean z2, boolean z3, int i2) {
        Slog.v("SettingsProvider", "insertGlobalSetting(" + str + ", " + str2 + ", , " + str3 + ", " + z + ", " + i + ", " + z2 + ", " + i2 + ")");
        return mutateGlobalSetting(str, str2, str3, z, i, 1, z2, 0, z3);
    }

    private boolean deleteGlobalSetting(String str, int i, boolean z) {
        Slog.v("SettingsProvider", "deleteGlobalSetting(" + str + ", " + i + ", " + z + ")");
        return mutateGlobalSetting(str, null, null, false, i, 2, z, 0);
    }

    private void resetGlobalSetting(int i, int i2, String str) {
        Slog.v("SettingsProvider", "resetGlobalSetting(" + i + ", " + i2 + ", " + str + ")");
        mutateGlobalSetting(null, null, str, false, i, 4, false, i2);
    }

    /* JADX WARN: Removed duplicated region for block: B:10:0x0015  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    private boolean isSettingRestrictedForUser(java.lang.String r3, int r4, java.lang.String r5, int r6) {
        /*
            r2 = this;
            long r0 = android.os.Binder.clearCallingIdentity()
            if (r3 == 0) goto L15
            android.os.UserManager r2 = r2.mUserManager     // Catch: java.lang.Throwable -> L10
            boolean r2 = r2.isSettingRestrictedForUser(r3, r4, r5, r6)     // Catch: java.lang.Throwable -> L10
            if (r2 == 0) goto L15
            r2 = 1
            goto L16
        L10:
            r2 = move-exception
            android.os.Binder.restoreCallingIdentity(r0)
            throw r2
        L15:
            r2 = 0
        L16:
            android.os.Binder.restoreCallingIdentity(r0)
            return r2
        */
        throw new UnsupportedOperationException("Method not decompiled: com.android.providers.settings.SettingsProvider.isSettingRestrictedForUser(java.lang.String, int, java.lang.String, int):boolean");
    }

    private boolean mutateGlobalSetting(String str, String str2, String str3, boolean z, int i, int i2, boolean z2, int i3) {
        return mutateGlobalSetting(str, str2, str3, z, i, i2, z2, i3, false);
    }

    private boolean mutateGlobalSetting(String str, String str2, String str3, boolean z, int i, int i2, boolean z2, int i3, boolean z3) {
        enforceWritePermission("android.permission.WRITE_SECURE_SETTINGS");
        if (isSettingRestrictedForUser(str, resolveCallingUserIdEnforcingPermissionsLocked(i), str2, Binder.getCallingUid())) {
            return false;
        }
        synchronized (this.mLock) {
            if (i2 == 1) {
                return this.mSettingsRegistry.insertSettingLocked(0, 0, str, str2, str3, z, getCallingPackage(), z2, CRITICAL_GLOBAL_SETTINGS, z3);
            }
            if (i2 == 2) {
                return this.mSettingsRegistry.deleteSettingLocked(0, 0, str, z2, CRITICAL_GLOBAL_SETTINGS);
            }
            if (i2 == 3) {
                return this.mSettingsRegistry.updateSettingLocked(0, 0, str, str2, str3, z, getCallingPackage(), z2, CRITICAL_GLOBAL_SETTINGS);
            }
            if (i2 != 4) {
                return false;
            }
            this.mSettingsRegistry.resetSettingsLocked(0, 0, getCallingPackage(), i3, str3);
            return true;
        }
    }

    private PackageInfo getCallingPackageInfo(int i) {
        try {
            return this.mPackageManager.getPackageInfo(getCallingPackage(), 64, i);
        } catch (RemoteException unused) {
            throw new IllegalStateException("Package " + this.getCallingPackage() + " doesn't exist");
        }
    }

    private Cursor getAllSecureSettings(int i, String[] strArr) {
        MatrixCursor matrixCursor;
        SettingsState.Setting settingLocked;
        Slog.v("SettingsProvider", "getAllSecureSettings(" + i + ")");
        int iResolveCallingUserIdEnforcingPermissionsLocked = resolveCallingUserIdEnforcingPermissionsLocked(i);
        PackageInfo callingPackageInfo = getCallingPackageInfo(resolveOwningUserIdForSecureSettingLocked(iResolveCallingUserIdEnforcingPermissionsLocked, "android_id"));
        synchronized (this.mLock) {
            List<String> settingsNamesLocked = getSettingsNamesLocked(2, iResolveCallingUserIdEnforcingPermissionsLocked);
            int size = settingsNamesLocked.size();
            matrixCursor = new MatrixCursor(normalizeProjection(strArr), size);
            for (int i2 = 0; i2 < size; i2++) {
                String str = settingsNamesLocked.get(i2);
                int iResolveOwningUserIdForSecureSettingLocked = resolveOwningUserIdForSecureSettingLocked(iResolveCallingUserIdEnforcingPermissionsLocked, str);
                if (isSecureSettingAccessible(str, iResolveCallingUserIdEnforcingPermissionsLocked, iResolveOwningUserIdForSecureSettingLocked)) {
                    if (isNewSsaidSetting(str)) {
                        settingLocked = getSsaidSettingLocked(callingPackageInfo, iResolveOwningUserIdForSecureSettingLocked);
                    } else {
                        settingLocked = this.mSettingsRegistry.getSettingLocked(2, iResolveOwningUserIdForSecureSettingLocked, str);
                    }
                    appendSettingToCursor(matrixCursor, settingLocked);
                }
            }
        }
        return matrixCursor;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public SettingsState.Setting getSecureSetting(String str, int i) {
        return getSecureSetting(str, i, false);
    }

    private SettingsState.Setting getSecureSetting(String str, int i, boolean z) {
        SettingsState.Setting settingLocked;
        SettingsState.Setting locationModeSetting;
        SettingsState.Setting ssaidSettingLocked;
        Slog.v("SettingsProvider", "getSecureSetting(" + str + ", " + i + ")");
        int iResolveCallingUserIdEnforcingPermissionsLocked = resolveCallingUserIdEnforcingPermissionsLocked(i);
        enforceSettingReadable(str, 2, UserHandle.getCallingUserId());
        int iResolveOwningUserIdForSecureSettingLocked = resolveOwningUserIdForSecureSettingLocked(iResolveCallingUserIdEnforcingPermissionsLocked, str);
        if (!isSecureSettingAccessible(str, iResolveCallingUserIdEnforcingPermissionsLocked, iResolveOwningUserIdForSecureSettingLocked)) {
            SettingsState settingsLocked = this.mSettingsRegistry.getSettingsLocked(2, iResolveOwningUserIdForSecureSettingLocked);
            if (settingsLocked != null) {
                return settingsLocked.getNullSetting();
            }
            return null;
        }
        if (isNewSsaidSetting(str)) {
            PackageInfo callingPackageInfo = getCallingPackageInfo(iResolveOwningUserIdForSecureSettingLocked);
            synchronized (this.mLock) {
                ssaidSettingLocked = getSsaidSettingLocked(callingPackageInfo, iResolveOwningUserIdForSecureSettingLocked);
            }
            return ssaidSettingLocked;
        }
        if (z && "location_mode".equals(str) && (locationModeSetting = getLocationModeSetting(iResolveOwningUserIdForSecureSettingLocked)) != null) {
            return locationModeSetting;
        }
        synchronized (this.mLock) {
            settingLocked = this.mSettingsRegistry.getSettingLocked(2, iResolveOwningUserIdForSecureSettingLocked, str);
        }
        return settingLocked;
    }

    private boolean isNewSsaidSetting(String str) {
        return "android_id".equals(str) && UserHandle.getAppId(Binder.getCallingUid()) >= 10000;
    }

    @GuardedBy({"mLock"})
    private SettingsState.Setting getSsaidSettingLocked(PackageInfo packageInfo, int i) {
        String string = Integer.toString(UserHandle.getUid(i, UserHandle.getAppId(Binder.getCallingUid())));
        Slog.v("SettingsProvider", "getSsaidSettingLocked(" + string + "," + i + ")");
        SettingsState.Setting settingLocked = this.mSettingsRegistry.getSettingLocked(3, i, string);
        long jClearCallingIdentity = Binder.clearCallingIdentity();
        try {
            try {
                String instantAppAndroidId = this.mPackageManager.getInstantAppAndroidId(packageInfo.packageName, i);
                Binder.restoreCallingIdentity(jClearCallingIdentity);
                SettingsState settingsLocked = this.mSettingsRegistry.getSettingsLocked(3, i);
                if (instantAppAndroidId != null) {
                    if (settingLocked != null && instantAppAndroidId.equals(settingLocked.getValue())) {
                        return mascaradeSsaidSetting(settingsLocked, settingLocked);
                    }
                    if (!settingsLocked.insertSettingLocked(string, instantAppAndroidId, null, true, packageInfo.packageName)) {
                        throw new IllegalStateException("Failed to update instant app android id");
                    }
                    return mascaradeSsaidSetting(settingsLocked, this.mSettingsRegistry.getSettingLocked(3, i, string));
                }
                if (settingLocked == null || settingLocked.isNull() || settingLocked.getValue() == null) {
                    return mascaradeSsaidSetting(settingsLocked, this.mSettingsRegistry.generateSsaidLocked(packageInfo, i));
                }
                return mascaradeSsaidSetting(settingsLocked, settingLocked);
            } catch (RemoteException e) {
                Slog.e("SettingsProvider", "Failed to get Instant App Android ID", e);
                Binder.restoreCallingIdentity(jClearCallingIdentity);
                return null;
            }
        } catch (Throwable th) {
            Binder.restoreCallingIdentity(jClearCallingIdentity);
            throw th;
        }
    }

    private SettingsState.Setting mascaradeSsaidSetting(SettingsState settingsState, SettingsState.Setting setting) {
        if (setting == null) {
            return null;
        }
        Objects.requireNonNull(settingsState);
        return new SettingsState.Setting(this, settingsState, setting) { // from class: com.android.providers.settings.SettingsProvider.4
            @Override // com.android.providers.settings.SettingsState.Setting
            public String getName() {
                return "android_id";
            }

            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super(setting);
                Objects.requireNonNull(settingsState);
            }

            @Override // com.android.providers.settings.SettingsState.Setting
            public int getKey() {
                return SettingsProvider.makeKey(2, SettingsProvider.getUserIdFromKey(super.getKey()));
            }
        };
    }

    private SettingsState.Setting getLocationModeSetting(int i) {
        synchronized (this.mLock) {
            if (!"1".equals(getGlobalSetting("location_global_kill_switch").getValue())) {
                return null;
            }
            SettingsState settingsLocked = this.mSettingsRegistry.getSettingsLocked(2, i);
            Objects.requireNonNull(settingsLocked);
            return new SettingsState.Setting(this, settingsLocked, "location_mode", "", "", "", "", false, "0") { // from class: com.android.providers.settings.SettingsProvider.5
                /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                {
                    super(settingsLocked, str, str, str, str, str, z, str);
                    Objects.requireNonNull(settingsLocked);
                }

                @Override // com.android.providers.settings.SettingsState.Setting
                public boolean update(String str, boolean z, String str2, String str3, boolean z2, boolean z3) {
                    Slog.wtf("SettingsProvider", "update shouldn't be called on this instance.");
                    return false;
                }
            };
        }
    }

    private boolean insertSecureSetting(String str, String str2, String str3, boolean z, int i, boolean z2, boolean z3, int i2) {
        Slog.v("SettingsProvider", "insertSecureSetting(" + str + ", " + str2 + ", , " + str3 + ", " + z + ", " + i + ", " + z2 + ", " + i2 + ")");
        return mutateSecureSetting(str, str2, str3, z, i, 1, z2, 0, z3);
    }

    private boolean deleteSecureSetting(String str, int i, boolean z) {
        Slog.v("SettingsProvider", "deleteSecureSetting(" + str + ", " + i + ", " + z + ")");
        return mutateSecureSetting(str, null, null, false, i, 2, z, 0);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public boolean updateSecureSetting(String str, String str2, String str3, boolean z, int i, boolean z2) {
        Slog.v("SettingsProvider", "updateSecureSetting(" + str + ", " + str2 + ", , " + str3 + ", " + z + ", " + i + ", " + z2 + ")");
        return mutateSecureSetting(str, str2, str3, z, i, 3, z2, 0);
    }

    private void resetSecureSetting(int i, int i2, String str) {
        Slog.v("SettingsProvider", "resetSecureSetting(" + i + ", " + i2 + ", " + str + ")");
        mutateSecureSetting(null, null, str, false, i, 4, false, i2);
    }

    private boolean mutateSecureSetting(String str, String str2, String str3, boolean z, int i, int i2, boolean z2, int i3) {
        return mutateSecureSetting(str, str2, str3, z, i, i2, z2, i3, false);
    }

    private boolean mutateSecureSetting(String str, String str2, String str3, boolean z, int i, int i2, boolean z2, int i3, boolean z3) {
        int iResolveOwningUserIdForSecureSettingLocked;
        enforceWritePermission("android.permission.WRITE_SECURE_SETTINGS");
        int iResolveCallingUserIdEnforcingPermissionsLocked = resolveCallingUserIdEnforcingPermissionsLocked(i);
        if (isSettingRestrictedForUser(str, iResolveCallingUserIdEnforcingPermissionsLocked, str2, Binder.getCallingUid()) || (iResolveOwningUserIdForSecureSettingLocked = resolveOwningUserIdForSecureSettingLocked(iResolveCallingUserIdEnforcingPermissionsLocked, str)) != iResolveCallingUserIdEnforcingPermissionsLocked) {
            return false;
        }
        synchronized (this.mLock) {
            if ("location_providers_allowed".equals(str)) {
                return updateLocationProvidersAllowedLocked(str2, str3, iResolveOwningUserIdForSecureSettingLocked, z, z2);
            }
            if (i2 == 1) {
                return this.mSettingsRegistry.insertSettingLocked(2, iResolveOwningUserIdForSecureSettingLocked, str, str2, str3, z, getCallingPackage(), z2, CRITICAL_SECURE_SETTINGS, z3);
            }
            if (i2 == 2) {
                return this.mSettingsRegistry.deleteSettingLocked(2, iResolveOwningUserIdForSecureSettingLocked, str, z2, CRITICAL_SECURE_SETTINGS);
            }
            if (i2 == 3) {
                return this.mSettingsRegistry.updateSettingLocked(2, iResolveOwningUserIdForSecureSettingLocked, str, str2, str3, z, getCallingPackage(), z2, CRITICAL_SECURE_SETTINGS);
            }
            if (i2 != 4) {
                return false;
            }
            this.mSettingsRegistry.resetSettingsLocked(2, 0, getCallingPackage(), i3, str3);
            return true;
        }
    }

    private Cursor getAllSystemSettings(int i, String[] strArr) {
        MatrixCursor matrixCursor;
        Slog.v("SettingsProvider", "getAllSecureSystem(" + i + ")");
        int iResolveCallingUserIdEnforcingPermissionsLocked = resolveCallingUserIdEnforcingPermissionsLocked(i);
        synchronized (this.mLock) {
            List<String> settingsNamesLocked = getSettingsNamesLocked(1, iResolveCallingUserIdEnforcingPermissionsLocked);
            int size = settingsNamesLocked.size();
            matrixCursor = new MatrixCursor(normalizeProjection(strArr), size);
            for (int i2 = 0; i2 < size; i2++) {
                String str = settingsNamesLocked.get(i2);
                appendSettingToCursor(matrixCursor, this.mSettingsRegistry.getSettingLocked(1, resolveOwningUserIdForSystemSettingLocked(iResolveCallingUserIdEnforcingPermissionsLocked, str), str));
            }
        }
        return matrixCursor;
    }

    private SettingsState.Setting getSystemSetting(String str, int i) {
        SettingsState.Setting settingLocked;
        Slog.v("SettingsProvider", "getSystemSetting(" + str + ", " + i + ")");
        int iResolveCallingUserIdEnforcingPermissionsLocked = resolveCallingUserIdEnforcingPermissionsLocked(i);
        enforceSettingReadable(str, 1, UserHandle.getCallingUserId());
        int iResolveOwningUserIdForSystemSettingLocked = resolveOwningUserIdForSystemSettingLocked(iResolveCallingUserIdEnforcingPermissionsLocked, str);
        synchronized (this.mLock) {
            settingLocked = this.mSettingsRegistry.getSettingLocked(1, iResolveOwningUserIdForSystemSettingLocked, str);
        }
        return settingLocked;
    }

    private boolean insertSystemSetting(String str, String str2, int i, boolean z, int i2) {
        Slog.v("SettingsProvider", "insertSystemSetting(" + str + ", " + str2 + ", " + i + ", " + i2 + ")");
        return mutateSystemSetting(str, str2, i, 1, z);
    }

    private boolean deleteSystemSetting(String str, int i) {
        Slog.v("SettingsProvider", "deleteSystemSetting(" + str + ", " + i + ")");
        return mutateSystemSetting(str, null, i, 2);
    }

    private boolean updateSystemSetting(String str, String str2, int i) {
        Slog.v("SettingsProvider", "updateSystemSetting(" + str + ", " + str2 + ", " + i + ")");
        return mutateSystemSetting(str, str2, i, 3);
    }

    private boolean mutateSystemSetting(String str, String str2, int i, int i2) {
        return mutateSystemSetting(str, str2, i, i2, false);
    }

    private boolean mutateSystemSetting(String str, String str2, int i, int i2, boolean z) {
        if (!hasWriteSecureSettingsPermission() && !Settings.checkAndNoteWriteSettingsOperation(getContext(), Binder.getCallingUid(), getCallingPackage(), true)) {
            return false;
        }
        int iResolveCallingUserIdEnforcingPermissionsLocked = resolveCallingUserIdEnforcingPermissionsLocked(i);
        if (isSettingRestrictedForUser(str, iResolveCallingUserIdEnforcingPermissionsLocked, str2, Binder.getCallingUid())) {
            return false;
        }
        enforceRestrictedSystemSettingsMutationForCallingPackage(i2, str, iResolveCallingUserIdEnforcingPermissionsLocked);
        int iResolveOwningUserIdForSystemSettingLocked = resolveOwningUserIdForSystemSettingLocked(iResolveCallingUserIdEnforcingPermissionsLocked, str);
        if (iResolveOwningUserIdForSystemSettingLocked != iResolveCallingUserIdEnforcingPermissionsLocked) {
            return false;
        }
        String str3 = null;
        if ("ringtone".equals(str)) {
            str3 = "ringtone_cache";
        } else if ("notification_sound".equals(str)) {
            str3 = "notification_sound_cache";
        } else if ("alarm_alert".equals(str)) {
            str3 = "alarm_alert_cache";
        }
        if (str3 != null) {
            new File(getRingtoneCacheDir(iResolveOwningUserIdForSystemSettingLocked), str3).delete();
        }
        synchronized (this.mLock) {
            if (i2 == 1) {
                validateSystemSettingValue(str, str2);
                return this.mSettingsRegistry.insertSettingLocked(1, iResolveOwningUserIdForSystemSettingLocked, str, str2, null, false, getCallingPackage(), false, null, z);
            }
            if (i2 == 2) {
                return this.mSettingsRegistry.deleteSettingLocked(1, iResolveOwningUserIdForSystemSettingLocked, str, false, null);
            }
            if (i2 != 3) {
                return false;
            }
            validateSystemSettingValue(str, str2);
            return this.mSettingsRegistry.updateSettingLocked(1, iResolveOwningUserIdForSystemSettingLocked, str, str2, null, false, getCallingPackage(), false, null);
        }
    }

    private boolean hasWriteSecureSettingsPermission() {
        return getContext().checkCallingOrSelfPermission("android.permission.WRITE_SECURE_SETTINGS") == 0;
    }

    private void validateSystemSettingValue(String str, String str2) {
        Validator validator = SystemSettingsValidators.VALIDATORS.get(str);
        if (validator == null || validator.validate(str2)) {
            return;
        }
        throw new IllegalArgumentException("Invalid value: " + str2 + " for setting: " + str);
    }

    private boolean isSecureSettingAccessible(String str, int i, int i2) {
        if (isLocationProvidersAllowedRestricted(str, i, i2)) {
            return false;
        }
        byte b = -1;
        if (str.hashCode() == 1713600355 && str.equals("bluetooth_address")) {
            b = 0;
        }
        return b != 0 || getContext().checkCallingOrSelfPermission("android.permission.LOCAL_MAC_ADDRESS") == 0;
    }

    private boolean isLocationProvidersAllowedRestricted(String str, int i, int i2) {
        return i != i2 && "location_providers_allowed".equals(str) && this.mUserManager.hasUserRestriction("no_share_location", new UserHandle(i));
    }

    private int resolveOwningUserIdForSecureSettingLocked(int i, String str) {
        return resolveOwningUserIdLocked(i, sSecureCloneToManagedSettings, str);
    }

    private int resolveOwningUserIdForSystemSettingLocked(int i, String str) {
        int groupParentLocked;
        if (sSystemCloneFromParentOnDependency.containsKey(str) && (groupParentLocked = getGroupParentLocked(i)) != i) {
            String str2 = sSystemCloneFromParentOnDependency.get(str);
            long jClearCallingIdentity = Binder.clearCallingIdentity();
            try {
                SettingsState.Setting secureSetting = getSecureSetting(str2, i);
                if (secureSetting != null) {
                    if (secureSetting.getValue().equals("1")) {
                        return groupParentLocked;
                    }
                }
            } finally {
                Binder.restoreCallingIdentity(jClearCallingIdentity);
            }
        }
        return resolveOwningUserIdLocked(i, sSystemCloneToManagedSettings, str);
    }

    private int resolveOwningUserIdLocked(int i, Set<String> set, String str) {
        int groupParentLocked = getGroupParentLocked(i);
        return (groupParentLocked == i || !set.contains(str)) ? i : groupParentLocked;
    }

    private void enforceRestrictedSystemSettingsMutationForCallingPackage(int i, String str, int i2) {
        int appId = UserHandle.getAppId(Binder.getCallingUid());
        if (appId == 1000 || appId == 2000 || appId == 0) {
            return;
        }
        if (i != 1) {
            if (i == 2) {
                if (Settings.System.PUBLIC_SETTINGS.contains(str) || Settings.System.PRIVATE_SETTINGS.contains(str)) {
                    throw new IllegalArgumentException("You cannot delete system defined secure settings.");
                }
                if ((getCallingPackageInfoOrThrow(i2).applicationInfo.privateFlags & 8) != 0) {
                    return;
                } else {
                    return;
                }
            }
            if (i != 3) {
                return;
            }
        }
        if (!Settings.System.PUBLIC_SETTINGS.contains(str) && (getCallingPackageInfoOrThrow(i2).applicationInfo.privateFlags & 8) != 0) {
        }
    }

    private Set<String> getInstantAppAccessibleSettings(int i) {
        if (i == 0) {
            return Settings.Global.INSTANT_APP_SETTINGS;
        }
        if (i == 1) {
            return Settings.System.INSTANT_APP_SETTINGS;
        }
        if (i == 2) {
            return Settings.Secure.INSTANT_APP_SETTINGS;
        }
        throw new IllegalArgumentException("Invalid settings type: " + i);
    }

    private Set<String> getOverlayInstantAppAccessibleSettings(int i) {
        if (i == 0) {
            return OVERLAY_ALLOWED_GLOBAL_INSTANT_APP_SETTINGS;
        }
        if (i == 1) {
            return OVERLAY_ALLOWED_SYSTEM_INSTANT_APP_SETTINGS;
        }
        if (i == 2) {
            return OVERLAY_ALLOWED_SECURE_INSTANT_APP_SETTINGS;
        }
        throw new IllegalArgumentException("Invalid settings type: " + i);
    }

    @GuardedBy({"mLock"})
    private List<String> getSettingsNamesLocked(int i, int i2) {
        return this.mSettingsRegistry.getSettingsNamesLocked(i, i2);
    }

    private void enforceSettingReadable(String str, int i, int i2) {
        if (UserHandle.getAppId(Binder.getCallingUid()) < 10000) {
            return;
        }
        ApplicationInfo callingApplicationInfoOrThrow = getCallingApplicationInfoOrThrow();
        if (!callingApplicationInfoOrThrow.isInstantApp() || getInstantAppAccessibleSettings(i).contains(str) || getOverlayInstantAppAccessibleSettings(i).contains(str)) {
            return;
        }
        Slog.w("SettingsProvider", "Instant App " + callingApplicationInfoOrThrow.packageName + " trying to access unexposed setting, this will be an error in the future.");
    }

    private ApplicationInfo getCallingApplicationInfoOrThrow() {
        ApplicationInfo applicationInfo;
        try {
            applicationInfo = this.mPackageManager.getApplicationInfo(getCallingPackage(), 0, UserHandle.getCallingUserId());
        } catch (RemoteException unused) {
            applicationInfo = null;
        }
        if (applicationInfo != null) {
            return applicationInfo;
        }
        throw new IllegalStateException("Failed to lookup info for package " + getCallingPackage());
    }

    private PackageInfo getCallingPackageInfoOrThrow(int i) {
        try {
            PackageInfo packageInfo = this.mPackageManager.getPackageInfo(getCallingPackage(), 0, i);
            if (packageInfo != null) {
                return packageInfo;
            }
        } catch (RemoteException unused) {
        }
        throw new IllegalStateException("Calling package doesn't exist");
    }

    private int getGroupParentLocked(int i) {
        if (i == 0) {
            return i;
        }
        long jClearCallingIdentity = Binder.clearCallingIdentity();
        try {
            UserInfo profileParent = this.mUserManager.getProfileParent(i);
            if (profileParent != null) {
                i = profileParent.id;
            }
            return i;
        } finally {
            Binder.restoreCallingIdentity(jClearCallingIdentity);
        }
    }

    private void enforceWritePermission(String str) {
        if (getContext().checkCallingOrSelfPermission(str) == 0) {
            return;
        }
        throw new SecurityException("Permission denial: writing to settings requires:" + str);
    }

    @GuardedBy({"mLock"})
    private boolean updateLocationProvidersAllowedLocked(String str, String str2, int i, boolean z, boolean z2) {
        SettingsState.Setting secureSetting;
        boolean z3;
        char cCharAt;
        if (TextUtils.isEmpty(str) || (secureSetting = getSecureSetting("location_providers_allowed", i)) == null) {
            return false;
        }
        String value = secureSetting.getValue();
        ArrayList arrayList = TextUtils.isEmpty(value) ? new ArrayList() : new ArrayList(Arrays.asList(value.split(",")));
        ArraySet arraySet = new ArraySet();
        arraySet.addAll(arrayList);
        for (String str3 : str.split(",")) {
            if (TextUtils.isEmpty(str3) || ((cCharAt = str3.charAt(0)) != '+' && cCharAt != '-')) {
                z3 = true;
                break;
            }
            String strSubstring = str3.substring(1);
            if (cCharAt == '+') {
                arraySet.add(strSubstring);
            } else if (cCharAt == '-') {
                arraySet.remove(strSubstring);
            }
        }
        z3 = false;
        String strJoin = TextUtils.join(",", arraySet.toArray());
        if (!z3 && !strJoin.equals(value)) {
            return this.mSettingsRegistry.insertSettingLocked(2, i, "location_providers_allowed", strJoin, str2, z, getCallingPackage(), z2, CRITICAL_SECURE_SETTINGS, false);
        }
        if (z2) {
            this.mSettingsRegistry.notifyForSettingsChange(makeKey(2, i), "location_providers_allowed");
        }
        return false;
    }

    private static int resolveCallingUserIdEnforcingPermissionsLocked(int i) {
        return i == UserHandle.getCallingUserId() ? i : ActivityManager.handleIncomingUser(Binder.getCallingPid(), Binder.getCallingUid(), i, false, true, "get/set setting for user", null);
    }

    private Bundle packageValueForCallResult(SettingsState.Setting setting, boolean z) {
        if (!z) {
            if (setting == null || setting.isNull()) {
                return NULL_SETTING_BUNDLE;
            }
            return Bundle.forPair("value", setting.getValue());
        }
        Bundle bundle = new Bundle();
        bundle.putString("value", !setting.isNull() ? setting.getValue() : null);
        this.mSettingsRegistry.mGenerationRegistry.addGenerationData(bundle, setting.getKey());
        return bundle;
    }

    private Bundle packageValuesForCallResult(HashMap<String, String> map, boolean z) {
        Bundle bundle = new Bundle();
        bundle.putSerializable("value", map);
        if (z) {
            synchronized (this.mLock) {
                this.mSettingsRegistry.mGenerationRegistry.addGenerationData(bundle, this.mSettingsRegistry.getSettingsLocked(4, 0).mKey);
            }
        }
        return bundle;
    }

    private void setMonitorCallback(RemoteCallback remoteCallback) {
        if (remoteCallback == null) {
            return;
        }
        getContext().enforceCallingOrSelfPermission("android.permission.MONITOR_DEVICE_CONFIG_ACCESS", "Permission denial: registering for config access requires: android.permission.MONITOR_DEVICE_CONFIG_ACCESS");
        synchronized (this.mLock) {
            this.mConfigMonitorCallback = remoteCallback;
        }
    }

    private void reportDeviceConfigAccess(String str) {
        if (str == null) {
            return;
        }
        String strResolveCallingPackage = resolveCallingPackage();
        String strReplace = str.replace("/", "");
        if (DeviceConfig.getPublicNamespaces().contains(strReplace)) {
            return;
        }
        synchronized (this.mLock) {
            if (this.mConfigMonitorCallback != null) {
                Bundle bundle = new Bundle();
                bundle.putString("monitor_callback_type", "access_callback");
                bundle.putString("calling_package", strResolveCallingPackage);
                bundle.putString("namespace", strReplace);
                this.mConfigMonitorCallback.sendResult(bundle);
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void reportDeviceConfigUpdate(String str) {
        if (str == null) {
            return;
        }
        String strReplace = str.replace("/", "");
        if (DeviceConfig.getPublicNamespaces().contains(strReplace)) {
            return;
        }
        synchronized (this.mLock) {
            if (this.mConfigMonitorCallback != null) {
                Bundle bundle = new Bundle();
                bundle.putString("monitor_callback_type", "namespace_updated_callback");
                bundle.putString("namespace", strReplace);
                this.mConfigMonitorCallback.sendResult(bundle);
            }
        }
    }

    private static int getRequestingUserId(Bundle bundle) {
        int callingUserId = UserHandle.getCallingUserId();
        return bundle != null ? bundle.getInt("_user", callingUserId) : callingUserId;
    }

    private boolean isTrackingGeneration(Bundle bundle) {
        return bundle != null && bundle.containsKey("_track_generation");
    }

    private static String getSettingValue(Bundle bundle) {
        if (bundle != null) {
            return bundle.getString("value");
        }
        return null;
    }

    private static String getSettingTag(Bundle bundle) {
        if (bundle != null) {
            return bundle.getString("_tag");
        }
        return null;
    }

    private static String getSettingPrefix(Bundle bundle) {
        if (bundle != null) {
            return bundle.getString("_prefix");
        }
        return null;
    }

    private static Map<String, String> getSettingFlags(Bundle bundle) {
        if (bundle != null) {
            return (HashMap) bundle.getSerializable("_flags");
        }
        return Collections.emptyMap();
    }

    private static boolean getSettingMakeDefault(Bundle bundle) {
        return bundle != null && bundle.getBoolean("_make_default");
    }

    private static boolean getSettingOverrideableByRestore(Bundle bundle) {
        return bundle != null && bundle.getBoolean("_overrideable_by_restore");
    }

    private static int getResetModeEnforcingPermission(Bundle bundle) {
        int i = bundle != null ? bundle.getInt("_reset_mode") : 0;
        if (i == 1) {
            return i;
        }
        if (i == 2) {
            if (isCallerSystemOrShellOrRootOnDebuggableBuild()) {
                return i;
            }
            throw new SecurityException("Only system, shell/root on a debuggable build can reset to untrusted defaults");
        }
        if (i == 3) {
            if (isCallerSystemOrShellOrRootOnDebuggableBuild()) {
                return i;
            }
            throw new SecurityException("Only system, shell/root on a debuggable build can reset untrusted changes");
        }
        if (i == 4) {
            if (isCallerSystemOrShellOrRootOnDebuggableBuild()) {
                return i;
            }
            throw new SecurityException("Only system, shell/root on a debuggable build can reset to trusted defaults");
        }
        throw new IllegalArgumentException("Invalid reset mode: " + i);
    }

    private static boolean isCallerSystemOrShellOrRootOnDebuggableBuild() {
        int appId = UserHandle.getAppId(Binder.getCallingUid());
        return appId == 1000 || (Build.IS_DEBUGGABLE && (appId == 2000 || appId == 0));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static String getValidTableOrThrow(Uri uri) {
        if (uri.getPathSegments().size() > 0) {
            String str = uri.getPathSegments().get(0);
            if (DatabaseHelper.isValidTable(str)) {
                return str;
            }
            throw new IllegalArgumentException("Bad root path: " + str);
        }
        throw new IllegalArgumentException("Invalid URI:" + uri);
    }

    private static MatrixCursor packageSettingForQuery(SettingsState.Setting setting, String[] strArr) {
        if (setting.isNull()) {
            return new MatrixCursor(strArr, 0);
        }
        MatrixCursor matrixCursor = new MatrixCursor(strArr, 1);
        appendSettingToCursor(matrixCursor, setting);
        return matrixCursor;
    }

    private static String[] normalizeProjection(String[] strArr) {
        if (strArr == null) {
            return ALL_COLUMNS;
        }
        for (String str : strArr) {
            if (!ArrayUtils.contains(ALL_COLUMNS, str)) {
                throw new IllegalArgumentException("Invalid column: " + str);
            }
        }
        return strArr;
    }

    /* JADX WARN: Failed to restore switch over string. Please report as a decompilation issue */
    private static void appendSettingToCursor(MatrixCursor matrixCursor, SettingsState.Setting setting) {
        if (setting == null || setting.isNull()) {
            return;
        }
        int columnCount = matrixCursor.getColumnCount();
        String[] strArr = new String[columnCount];
        for (int i = 0; i < columnCount; i++) {
            String columnName = matrixCursor.getColumnName(i);
            byte b = -1;
            switch (columnName.hashCode()) {
                case 94650:
                    if (columnName.equals("_id")) {
                        b = 0;
                    }
                    break;
                case 3373707:
                    if (columnName.equals("name")) {
                        b = 1;
                    }
                    break;
                case 111972721:
                    if (columnName.equals("value")) {
                        b = 2;
                    }
                    break;
                case 1873471600:
                    if (columnName.equals("is_preserved_in_restore")) {
                        b = 3;
                    }
                    break;
            }
            if (b == 0) {
                strArr[i] = setting.getId();
            } else if (b == 1) {
                strArr[i] = setting.getName();
            } else if (b == 2) {
                strArr[i] = setting.getValue();
            } else if (b == 3) {
                strArr[i] = String.valueOf(setting.isValuePreservedInRestore());
            }
        }
        matrixCursor.addRow(strArr);
    }

    private static boolean isKeyValid(String str) {
        return (TextUtils.isEmpty(str) || SettingsState.isBinary(str)) ? false : true;
    }

    private String resolveCallingPackage() {
        int callingUid = Binder.getCallingUid();
        return callingUid != 0 ? callingUid != 2000 ? getCallingPackage() : "com.android.shell" : "root";
    }

    private static final class Arguments {
        public final String name;
        public final String table;
        private static final Pattern WHERE_PATTERN_WITH_PARAM_NO_BRACKETS = Pattern.compile("[\\s]*name[\\s]*=[\\s]*\\?[\\s]*");
        private static final Pattern WHERE_PATTERN_WITH_PARAM_IN_BRACKETS = Pattern.compile("[\\s]*\\([\\s]*name[\\s]*=[\\s]*\\?[\\s]*\\)[\\s]*");
        private static final Pattern WHERE_PATTERN_NO_PARAM_IN_BRACKETS = Pattern.compile("[\\s]*\\([\\s]*name[\\s]*=[\\s]*['\"].*['\"][\\s]*\\)[\\s]*");
        private static final Pattern WHERE_PATTERN_NO_PARAM_NO_BRACKETS = Pattern.compile("[\\s]*name[\\s]*=[\\s]*['\"].*['\"][\\s]*");

        public Arguments(Uri uri, String str, String[] strArr, boolean z) {
            int size = uri.getPathSegments().size();
            if (size != 1) {
                if (size == 2 && str == null && strArr == null) {
                    String str2 = uri.getPathSegments().get(1);
                    this.name = str2;
                    this.table = computeTableForSetting(uri, str2);
                    return;
                }
            } else if (str != null && ((WHERE_PATTERN_WITH_PARAM_NO_BRACKETS.matcher(str).matches() || WHERE_PATTERN_WITH_PARAM_IN_BRACKETS.matcher(str).matches()) && strArr.length == 1)) {
                String str3 = strArr[0];
                this.name = str3;
                this.table = computeTableForSetting(uri, str3);
                return;
            } else if (str != null && (WHERE_PATTERN_NO_PARAM_NO_BRACKETS.matcher(str).matches() || WHERE_PATTERN_NO_PARAM_IN_BRACKETS.matcher(str).matches())) {
                String strSubstring = str.substring(Math.max(str.indexOf("'"), str.indexOf("\"")) + 1, Math.max(str.lastIndexOf("'"), str.lastIndexOf("\"")));
                this.name = strSubstring;
                this.table = computeTableForSetting(uri, strSubstring);
                return;
            } else if (z && str == null && strArr == null) {
                this.name = null;
                this.table = computeTableForSetting(uri, null);
                return;
            }
            EventLogTags.writeUnsupportedSettingsQuery(uri.toSafeString(), str, Arrays.toString(strArr));
            throw new IllegalArgumentException(String.format("Supported SQL:\n  uri content://some_table/some_property with null where and where args\n  uri content://some_table with query name=? and single name as arg\n  uri content://some_table with query name=some_name and null args\n  but got - uri:%1s, where:%2s whereArgs:%3s", uri, str, Arrays.toString(strArr)));
        }

        private static String computeTableForSetting(Uri uri, String str) {
            String validTableOrThrow = SettingsProvider.getValidTableOrThrow(uri);
            if (str == null) {
                return validTableOrThrow;
            }
            if (SettingsProvider.sSystemMovedToSecureSettings.contains(str)) {
                validTableOrThrow = "secure";
            }
            if (SettingsProvider.sSystemMovedToGlobalSettings.contains(str)) {
                validTableOrThrow = "global";
            }
            if (SettingsProvider.sSecureMovedToGlobalSettings.contains(str)) {
                validTableOrThrow = "global";
            }
            return SettingsProvider.sGlobalMovedToSecureSettings.contains(str) ? "secure" : validTableOrThrow;
        }
    }

    public void scheduleWriteFallbackFilesJob() {
        Context context = getContext();
        JobScheduler jobScheduler = (JobScheduler) context.getSystemService("jobscheduler");
        if (jobScheduler != null && jobScheduler.getPendingJob(1) == null) {
            PersistableBundle persistableBundle = new PersistableBundle();
            File settingsFile = this.mSettingsRegistry.getSettingsFile(makeKey(0, 0));
            File settingsFile2 = this.mSettingsRegistry.getSettingsFile(makeKey(1, 0));
            File settingsFile3 = this.mSettingsRegistry.getSettingsFile(makeKey(2, 0));
            File settingsFile4 = this.mSettingsRegistry.getSettingsFile(makeKey(3, 0));
            File settingsFile5 = this.mSettingsRegistry.getSettingsFile(makeKey(4, 0));
            persistableBundle.putString("global", settingsFile.getAbsolutePath());
            persistableBundle.putString("system", settingsFile2.getAbsolutePath());
            persistableBundle.putString("secure", settingsFile3.getAbsolutePath());
            persistableBundle.putString("ssaid", settingsFile4.getAbsolutePath());
            persistableBundle.putString("config", settingsFile5.getAbsolutePath());
            jobScheduler.schedule(new JobInfo.Builder(1, new ComponentName(context, (Class<?>) WriteFallbackSettingsFilesJobService.class)).setExtras(persistableBundle).setPeriodic(86400000L).setRequiresCharging(true).setPersisted(true).build());
        }
    }

    public static void writeFallBackSettingsFiles(List<String> list) {
        int size = list.size();
        for (int i = 0; i < size; i++) {
            String str = list.get(i);
            File file = new File(str);
            if (SettingsState.stateFileExists(file)) {
                try {
                    FileUtils.copy(file, new File(str + ".fallback"));
                } catch (IOException unused) {
                    Slog.w("SettingsProvider", "Failed to write fallback file for: " + str);
                }
            }
        }
    }

    final class SettingsRegistry {
        private final BackupManager mBackupManager;
        private GenerationRegistry mGenerationRegistry;
        private final Handler mHandler;
        private String mSettingsCreationBuildId;
        private final SparseArray<SettingsState> mSettingsStates = new SparseArray<>();

        private int getMaxBytesPerPackageForType(int i) {
            return (i == 0 || i == 2 || i == 3 || i == 4) ? -1 : 20000;
        }

        public SettingsRegistry() {
            this.mHandler = new MyHandler(SettingsProvider.this.getContext().getMainLooper());
            this.mGenerationRegistry = new GenerationRegistry(SettingsProvider.this.mLock);
            this.mBackupManager = new BackupManager(SettingsProvider.this.getContext());
            migrateAllLegacySettingsIfNeeded();
            syncSsaidTableOnStart();
        }

        private void generateUserKeyLocked(int i) {
            byte[] bArr = new byte[32];
            new SecureRandom().nextBytes(bArr);
            if (!getSettingsLocked(3, i).insertSettingLocked("userkey", HexEncoding.encodeToString(bArr, true), null, true, "android")) {
                throw new IllegalStateException("Ssaid settings not accessible");
            }
        }

        private byte[] getLengthPrefix(byte[] bArr) {
            return ByteBuffer.allocate(4).putInt(bArr.length).array();
        }

        public SettingsState.Setting generateSsaidLocked(PackageInfo packageInfo, int i) {
            SettingsState.Setting settingLocked = getSettingLocked(3, i, "userkey");
            if (settingLocked == null || settingLocked.isNull() || settingLocked.getValue() == null) {
                generateUserKeyLocked(i);
                settingLocked = getSettingLocked(3, i, "userkey");
                if (settingLocked == null || settingLocked.isNull() || settingLocked.getValue() == null) {
                    throw new IllegalStateException("User key not accessible");
                }
            }
            String value = settingLocked.getValue();
            if (value == null || value.length() % 2 != 0) {
                throw new IllegalStateException("User key invalid");
            }
            byte[] bArrDecode = HexEncoding.decode(value);
            if (bArrDecode.length != 16 && bArrDecode.length != 32) {
                throw new IllegalStateException("User key invalid");
            }
            try {
                Mac mac = Mac.getInstance("HmacSHA256");
                mac.init(new SecretKeySpec(bArrDecode, mac.getAlgorithm()));
                int i2 = 0;
                while (true) {
                    Signature[] signatureArr = packageInfo.signatures;
                    if (i2 >= signatureArr.length) {
                        break;
                    }
                    byte[] byteArray = signatureArr[i2].toByteArray();
                    mac.update(getLengthPrefix(byteArray), 0, 4);
                    mac.update(byteArray);
                    i2++;
                }
                String strSubstring = HexEncoding.encodeToString(mac.doFinal(), false).substring(0, 16);
                String string = Integer.toString(packageInfo.applicationInfo.uid);
                if (!getSettingsLocked(3, i).insertSettingLocked(string, strSubstring, null, true, packageInfo.packageName)) {
                    throw new IllegalStateException("Ssaid settings not accessible");
                }
                return getSettingLocked(3, i, string);
            } catch (InvalidKeyException e) {
                throw new IllegalStateException("Key is corrupted", e);
            } catch (NoSuchAlgorithmException e2) {
                throw new IllegalStateException("HmacSHA256 is not available", e2);
            }
        }

        public void syncSsaidTableOnStart() {
            synchronized (SettingsProvider.this.mLock) {
                for (UserInfo userInfo : SettingsProvider.this.mUserManager.getUsers(true)) {
                    try {
                        List list = SettingsProvider.this.mPackageManager.getInstalledPackages(8192, userInfo.id).getList();
                        HashSet hashSet = new HashSet();
                        Iterator it = list.iterator();
                        while (it.hasNext()) {
                            hashSet.add(Integer.toString(((PackageInfo) it.next()).applicationInfo.uid));
                        }
                        HashSet hashSet2 = new HashSet(getSettingsNamesLocked(3, userInfo.id));
                        hashSet2.remove("userkey");
                        hashSet2.removeAll(hashSet);
                        SettingsState settingsLocked = getSettingsLocked(3, userInfo.id);
                        Iterator it2 = hashSet2.iterator();
                        while (it2.hasNext()) {
                            settingsLocked.deleteSettingLocked((String) it2.next());
                        }
                    } catch (RemoteException unused) {
                        throw new IllegalStateException("Package manager not available");
                    }
                }
            }
        }

        public List<String> getSettingsNamesLocked(int i, int i2) {
            SettingsState settingsStatePeekSettingsStateLocked = peekSettingsStateLocked(SettingsProvider.makeKey(i, i2));
            if (settingsStatePeekSettingsStateLocked == null) {
                return new ArrayList();
            }
            return settingsStatePeekSettingsStateLocked.getSettingNamesLocked();
        }

        public SparseBooleanArray getKnownUsersLocked() {
            SparseBooleanArray sparseBooleanArray = new SparseBooleanArray();
            for (int size = this.mSettingsStates.size() - 1; size >= 0; size--) {
                sparseBooleanArray.put(SettingsProvider.getUserIdFromKey(this.mSettingsStates.keyAt(size)), true);
            }
            return sparseBooleanArray;
        }

        public SettingsState getSettingsLocked(int i, int i2) {
            return peekSettingsStateLocked(SettingsProvider.makeKey(i, i2));
        }

        public boolean ensureSettingsForUserLocked(int i) {
            if (SettingsProvider.this.mUserManager.getUserInfo(i) == null) {
                Slog.wtf("SettingsProvider", "Requested user " + i + " does not exist");
                return false;
            }
            migrateLegacySettingsForUserIfNeededLocked(i);
            if (i == 0) {
                ensureSettingsStateLocked(SettingsProvider.makeKey(4, 0));
            }
            if (i == 0) {
                ensureSettingsStateLocked(SettingsProvider.makeKey(0, 0));
            }
            ensureSettingsStateLocked(SettingsProvider.makeKey(2, i));
            ensureSecureSettingAndroidIdSetLocked(getSettingsLocked(2, i));
            ensureSettingsStateLocked(SettingsProvider.makeKey(1, i));
            ensureSettingsStateLocked(SettingsProvider.makeKey(3, i));
            new UpgradeController(i).upgradeIfNeededLocked();
            return true;
        }

        private void ensureSettingsStateLocked(int i) {
            if (this.mSettingsStates.get(i) == null) {
                this.mSettingsStates.put(i, new SettingsState(SettingsProvider.this.getContext(), SettingsProvider.this.mLock, getSettingsFile(i), i, getMaxBytesPerPackageForType(SettingsProvider.getTypeFromKey(i)), SettingsProvider.this.mHandlerThread.getLooper()));
            }
        }

        public void removeUserStateLocked(int i, boolean z) {
            final int iMakeKey = SettingsProvider.makeKey(1, i);
            SettingsState settingsState = this.mSettingsStates.get(iMakeKey);
            if (settingsState != null) {
                if (z) {
                    this.mSettingsStates.remove(iMakeKey);
                    settingsState.destroyLocked(null);
                } else {
                    settingsState.destroyLocked(new Runnable() { // from class: com.android.providers.settings.SettingsProvider.SettingsRegistry.1
                        @Override // java.lang.Runnable
                        public void run() {
                            SettingsRegistry.this.mSettingsStates.remove(iMakeKey);
                        }
                    });
                }
            }
            final int iMakeKey2 = SettingsProvider.makeKey(2, i);
            SettingsState settingsState2 = this.mSettingsStates.get(iMakeKey2);
            if (settingsState2 != null) {
                if (z) {
                    this.mSettingsStates.remove(iMakeKey2);
                    settingsState2.destroyLocked(null);
                } else {
                    settingsState2.destroyLocked(new Runnable() { // from class: com.android.providers.settings.SettingsProvider.SettingsRegistry.2
                        @Override // java.lang.Runnable
                        public void run() {
                            SettingsRegistry.this.mSettingsStates.remove(iMakeKey2);
                        }
                    });
                }
            }
            final int iMakeKey3 = SettingsProvider.makeKey(3, i);
            SettingsState settingsState3 = this.mSettingsStates.get(iMakeKey3);
            if (settingsState3 != null) {
                if (z) {
                    this.mSettingsStates.remove(iMakeKey3);
                    settingsState3.destroyLocked(null);
                } else {
                    settingsState3.destroyLocked(new Runnable() { // from class: com.android.providers.settings.SettingsProvider.SettingsRegistry.3
                        @Override // java.lang.Runnable
                        public void run() {
                            SettingsRegistry.this.mSettingsStates.remove(iMakeKey3);
                        }
                    });
                }
            }
            this.mGenerationRegistry.onUserRemoved(i);
        }

        public boolean insertSettingLocked(int i, int i2, String str, String str2, String str3, boolean z, String str4, boolean z2, Set<String> set, boolean z3) {
            return insertSettingLocked(i, i2, str, str2, str3, z, false, str4, z2, set, z3);
        }

        public boolean insertSettingLocked(int i, int i2, String str, String str2, String str3, boolean z, boolean z2, String str4, boolean z3, Set<String> set, boolean z4) {
            if (z4) {
                SettingsProvider.this.getContext().enforceCallingOrSelfPermission("android.permission.MODIFY_SETTINGS_OVERRIDEABLE_BY_RESTORE", "Caller is not allowed to modify settings overrideable by restore");
            }
            int iMakeKey = SettingsProvider.makeKey(i, i2);
            SettingsState settingsStatePeekSettingsStateLocked = peekSettingsStateLocked(iMakeKey);
            boolean zInsertSettingLocked = settingsStatePeekSettingsStateLocked != null ? settingsStatePeekSettingsStateLocked.insertSettingLocked(str, str2, str3, z, z2, str4, z4) : false;
            if (zInsertSettingLocked && set != null && set.contains(str)) {
                settingsStatePeekSettingsStateLocked.persistSyncLocked();
            }
            if (z3 || zInsertSettingLocked) {
                notifyForSettingsChange(iMakeKey, str);
            }
            return zInsertSettingLocked;
        }

        public boolean setConfigSettingsLocked(int i, String str, Map<String, String> map, String str2) {
            SettingsState settingsStatePeekSettingsStateLocked = peekSettingsStateLocked(i);
            if (settingsStatePeekSettingsStateLocked == null) {
                return true;
            }
            if (settingsStatePeekSettingsStateLocked.isNewConfigBannedLocked(str, map)) {
                return false;
            }
            settingsStatePeekSettingsStateLocked.unbanAllConfigIfBannedConfigUpdatedLocked(str);
            List<String> settingsLocked = settingsStatePeekSettingsStateLocked.setSettingsLocked(str, map, str2);
            if (settingsLocked.isEmpty()) {
                return true;
            }
            SettingsProvider.this.reportDeviceConfigUpdate(str);
            notifyForConfigSettingsChangeLocked(i, str, settingsLocked);
            return true;
        }

        public boolean deleteSettingLocked(int i, int i2, String str, boolean z, Set<String> set) {
            int iMakeKey = SettingsProvider.makeKey(i, i2);
            SettingsState settingsStatePeekSettingsStateLocked = peekSettingsStateLocked(iMakeKey);
            boolean zDeleteSettingLocked = settingsStatePeekSettingsStateLocked != null ? settingsStatePeekSettingsStateLocked.deleteSettingLocked(str) : false;
            if (zDeleteSettingLocked && set != null && set.contains(str)) {
                settingsStatePeekSettingsStateLocked.persistSyncLocked();
            }
            if (z || zDeleteSettingLocked) {
                notifyForSettingsChange(iMakeKey, str);
            }
            return zDeleteSettingLocked;
        }

        public boolean updateSettingLocked(int i, int i2, String str, String str2, String str3, boolean z, String str4, boolean z2, Set<String> set) {
            int iMakeKey = SettingsProvider.makeKey(i, i2);
            SettingsState settingsStatePeekSettingsStateLocked = peekSettingsStateLocked(iMakeKey);
            boolean zUpdateSettingLocked = settingsStatePeekSettingsStateLocked != null ? settingsStatePeekSettingsStateLocked.updateSettingLocked(str, str2, str3, z, str4) : false;
            if (zUpdateSettingLocked && set != null && set.contains(str)) {
                settingsStatePeekSettingsStateLocked.persistSyncLocked();
            }
            if (z2 || zUpdateSettingLocked) {
                notifyForSettingsChange(iMakeKey, str);
            }
            return zUpdateSettingLocked;
        }

        public SettingsState.Setting getSettingLocked(int i, int i2, String str) {
            SettingsState settingsStatePeekSettingsStateLocked = peekSettingsStateLocked(SettingsProvider.makeKey(i, i2));
            if (settingsStatePeekSettingsStateLocked == null) {
                return null;
            }
            return settingsStatePeekSettingsStateLocked.getSettingLocked(str);
        }

        public void resetSettingsLocked(int i, int i2, String str, int i3, String str2) {
            resetSettingsLocked(i, i2, str, i3, str2, null);
        }

        /* JADX WARN: Removed duplicated region for block: B:101:0x00b8 A[SYNTHETIC] */
        /* JADX WARN: Removed duplicated region for block: B:104:0x006d A[SYNTHETIC] */
        /* JADX WARN: Removed duplicated region for block: B:110:0x00ff A[SYNTHETIC] */
        /* JADX WARN: Removed duplicated region for block: B:113:0x00c4 A[SYNTHETIC] */
        /* JADX WARN: Removed duplicated region for block: B:121:0x014c A[SYNTHETIC] */
        /* JADX WARN: Removed duplicated region for block: B:125:0x010b A[SYNTHETIC] */
        /*
            Code decompiled incorrectly, please refer to instructions dump.
            To view partially-correct code enable 'Show inconsistent code' option in preferences
        */
        public void resetSettingsLocked(int r7, int r8, java.lang.String r9, int r10, java.lang.String r11, java.lang.String r12) {
            /*
                Method dump skipped, instruction units count: 337
                To view this dump change 'Code comments level' option to 'DEBUG'
            */
            throw new UnsupportedOperationException("Method not decompiled: com.android.providers.settings.SettingsProvider.SettingsRegistry.resetSettingsLocked(int, int, java.lang.String, int, java.lang.String, java.lang.String):void");
        }

        public void removeSettingsForPackageLocked(String str, int i) {
            SettingsState settingsState = this.mSettingsStates.get(SettingsProvider.makeKey(1, i));
            if (settingsState != null) {
                settingsState.removeSettingsForPackageLocked(str);
            }
        }

        public void onUidRemovedLocked(int i) {
            SettingsState settingsLocked = getSettingsLocked(3, UserHandle.getUserId(i));
            if (settingsLocked != null) {
                settingsLocked.deleteSettingLocked(Integer.toString(i));
            }
        }

        private SettingsState peekSettingsStateLocked(int i) {
            SettingsState settingsState = this.mSettingsStates.get(i);
            if (settingsState != null) {
                return settingsState;
            }
            if (ensureSettingsForUserLocked(SettingsProvider.getUserIdFromKey(i))) {
                return this.mSettingsStates.get(i);
            }
            return null;
        }

        private void migrateAllLegacySettingsIfNeeded() {
            synchronized (SettingsProvider.this.mLock) {
                if (SettingsState.stateFileExists(getSettingsFile(SettingsProvider.makeKey(0, 0)))) {
                    return;
                }
                this.mSettingsCreationBuildId = Build.ID;
                long jClearCallingIdentity = Binder.clearCallingIdentity();
                try {
                    List users = SettingsProvider.this.mUserManager.getUsers(true);
                    int size = users.size();
                    for (int i = 0; i < size; i++) {
                        int i2 = ((UserInfo) users.get(i)).id;
                        DatabaseHelper databaseHelper = new DatabaseHelper(SettingsProvider.this.getContext(), i2);
                        migrateLegacySettingsForUserLocked(databaseHelper, databaseHelper.getWritableDatabase(), i2);
                        new UpgradeController(i2).upgradeIfNeededLocked();
                        if (!SettingsProvider.this.mUserManager.isUserRunning(new UserHandle(i2))) {
                            removeUserStateLocked(i2, false);
                        }
                    }
                } finally {
                    Binder.restoreCallingIdentity(jClearCallingIdentity);
                }
            }
        }

        private void migrateLegacySettingsForUserIfNeededLocked(int i) {
            if (SettingsState.stateFileExists(getSettingsFile(SettingsProvider.makeKey(2, i)))) {
                return;
            }
            DatabaseHelper databaseHelper = new DatabaseHelper(SettingsProvider.this.getContext(), i);
            migrateLegacySettingsForUserLocked(databaseHelper, databaseHelper.getWritableDatabase(), i);
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void migrateLegacySettingsForUserLocked(DatabaseHelper databaseHelper, SQLiteDatabase sQLiteDatabase, int i) {
            int iMakeKey = SettingsProvider.makeKey(1, i);
            ensureSettingsStateLocked(iMakeKey);
            SettingsState settingsState = this.mSettingsStates.get(iMakeKey);
            migrateLegacySettingsLocked(settingsState, sQLiteDatabase, "system");
            settingsState.persistSyncLocked();
            int iMakeKey2 = SettingsProvider.makeKey(2, i);
            ensureSettingsStateLocked(iMakeKey2);
            SettingsState settingsState2 = this.mSettingsStates.get(iMakeKey2);
            migrateLegacySettingsLocked(settingsState2, sQLiteDatabase, "secure");
            ensureSecureSettingAndroidIdSetLocked(settingsState2);
            settingsState2.persistSyncLocked();
            if (i == 0) {
                int iMakeKey3 = SettingsProvider.makeKey(0, i);
                ensureSettingsStateLocked(iMakeKey3);
                SettingsState settingsState3 = this.mSettingsStates.get(iMakeKey3);
                migrateLegacySettingsLocked(settingsState3, sQLiteDatabase, "global");
                String str = this.mSettingsCreationBuildId;
                if (str != null) {
                    settingsState3.insertSettingLocked("database_creation_buildid", str, null, true, "android");
                }
                settingsState3.persistSyncLocked();
            }
            databaseHelper.dropDatabase();
        }

        private void migrateLegacySettingsLocked(SettingsState settingsState, SQLiteDatabase sQLiteDatabase, String str) {
            SQLiteQueryBuilder sQLiteQueryBuilder = new SQLiteQueryBuilder();
            sQLiteQueryBuilder.setTables(str);
            Cursor cursorQuery = sQLiteQueryBuilder.query(sQLiteDatabase, SettingsProvider.LEGACY_SQL_COLUMNS, null, null, null, null, null);
            if (cursorQuery == null) {
                return;
            }
            try {
                if (cursorQuery.moveToFirst()) {
                    int columnIndex = cursorQuery.getColumnIndex("name");
                    int columnIndex2 = cursorQuery.getColumnIndex("value");
                    settingsState.setVersionLocked(sQLiteDatabase.getVersion());
                    while (!cursorQuery.isAfterLast()) {
                        settingsState.insertSettingLocked(cursorQuery.getString(columnIndex), cursorQuery.getString(columnIndex2), null, true, "android");
                        cursorQuery.moveToNext();
                    }
                }
            } finally {
                cursorQuery.close();
            }
        }

        @GuardedBy({"secureSettings.mLock"})
        private void ensureSecureSettingAndroidIdSetLocked(SettingsState settingsState) {
            DropBoxManager dropBoxManager;
            if (settingsState.getSettingLocked("android_id").isNull()) {
                int userIdFromKey = SettingsProvider.getUserIdFromKey(settingsState.mKey);
                long jClearCallingIdentity = Binder.clearCallingIdentity();
                try {
                    UserInfo userInfo = SettingsProvider.this.mUserManager.getUserInfo(userIdFromKey);
                    if (userInfo == null) {
                        return;
                    }
                    String hexString = Long.toHexString(new SecureRandom().nextLong());
                    settingsState.insertSettingLocked("android_id", hexString, null, true, "android");
                    Slog.d("SettingsProvider", "Generated and saved new ANDROID_ID [" + hexString + "] for user " + userIdFromKey);
                    if (userInfo.isRestricted() && (dropBoxManager = (DropBoxManager) SettingsProvider.this.getContext().getSystemService("dropbox")) != null && dropBoxManager.isTagEnabled("restricted_profile_ssaid")) {
                        dropBoxManager.addText("restricted_profile_ssaid", System.currentTimeMillis() + ",restricted_profile_ssaid," + hexString + "\n");
                    }
                } finally {
                    Binder.restoreCallingIdentity(jClearCallingIdentity);
                }
            }
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void notifyForSettingsChange(int i, String str) {
            this.mGenerationRegistry.incrementGeneration(i);
            if (isGlobalSettingsKey(i) || isConfigSettingsKey(i)) {
                long jClearCallingIdentity = Binder.clearCallingIdentity();
                try {
                    if ("location_global_kill_switch".equals(str) && isGlobalSettingsKey(i)) {
                        notifyLocationChangeForRunningUsers();
                    }
                    notifySettingChangeForRunningUsers(i, str);
                } finally {
                    Binder.restoreCallingIdentity(jClearCallingIdentity);
                }
            } else {
                int userIdFromKey = SettingsProvider.getUserIdFromKey(i);
                Uri notificationUriFor = getNotificationUriFor(i, str);
                this.mHandler.obtainMessage(1, userIdFromKey, 0, notificationUriFor).sendToTarget();
                if (isSecureSettingsKey(i)) {
                    maybeNotifyProfiles(SettingsProvider.getTypeFromKey(i), userIdFromKey, notificationUriFor, str, SettingsProvider.sSecureCloneToManagedSettings);
                    maybeNotifyProfiles(1, userIdFromKey, notificationUriFor, str, SettingsProvider.sSystemCloneFromParentOnDependency.values());
                } else if (isSystemSettingsKey(i)) {
                    maybeNotifyProfiles(SettingsProvider.getTypeFromKey(i), userIdFromKey, notificationUriFor, str, SettingsProvider.sSystemCloneToManagedSettings);
                }
            }
            this.mHandler.obtainMessage(2).sendToTarget();
        }

        private void notifyForConfigSettingsChangeLocked(int i, String str, List<String> list) {
            this.mGenerationRegistry.incrementGeneration(i);
            StringBuilder sb = new StringBuilder(str);
            for (int i2 = 0; i2 < list.size(); i2++) {
                sb.append(list.get(i2).split("/")[1]);
                sb.append("/");
            }
            long jClearCallingIdentity = Binder.clearCallingIdentity();
            try {
                notifySettingChangeForRunningUsers(i, sb.toString());
                Binder.restoreCallingIdentity(jClearCallingIdentity);
                this.mHandler.obtainMessage(2).sendToTarget();
            } catch (Throwable th) {
                Binder.restoreCallingIdentity(jClearCallingIdentity);
                throw th;
            }
        }

        private void maybeNotifyProfiles(int i, int i2, Uri uri, String str, Collection<String> collection) {
            if (collection.contains(str)) {
                for (int i3 : SettingsProvider.this.mUserManager.getProfileIdsWithDisabled(i2)) {
                    if (i3 != i2) {
                        this.mGenerationRegistry.incrementGeneration(SettingsProvider.makeKey(i, i3));
                        this.mHandler.obtainMessage(1, i3, 0, uri).sendToTarget();
                    }
                }
            }
        }

        private void notifySettingChangeForRunningUsers(int i, String str) {
            Uri notificationUriFor = getNotificationUriFor(i, str);
            List users = SettingsProvider.this.mUserManager.getUsers(true);
            for (int i2 = 0; i2 < users.size(); i2++) {
                int i3 = ((UserInfo) users.get(i2)).id;
                if (SettingsProvider.this.mUserManager.isUserRunning(UserHandle.of(i3))) {
                    this.mHandler.obtainMessage(1, i3, 0, notificationUriFor).sendToTarget();
                }
            }
        }

        private void notifyLocationChangeForRunningUsers() {
            List users = SettingsProvider.this.mUserManager.getUsers(true);
            for (int i = 0; i < users.size(); i++) {
                int i2 = ((UserInfo) users.get(i)).id;
                if (SettingsProvider.this.mUserManager.isUserRunning(UserHandle.of(i2))) {
                    int iMakeKey = SettingsProvider.makeKey(2, i2);
                    this.mGenerationRegistry.incrementGeneration(iMakeKey);
                    this.mHandler.obtainMessage(1, i2, 0, getNotificationUriFor(iMakeKey, "location_mode")).sendToTarget();
                }
            }
        }

        private boolean isConfigSettingsKey(int i) {
            return SettingsProvider.getTypeFromKey(i) == 4;
        }

        private boolean isGlobalSettingsKey(int i) {
            return SettingsProvider.getTypeFromKey(i) == 0;
        }

        private boolean isSystemSettingsKey(int i) {
            return SettingsProvider.getTypeFromKey(i) == 1;
        }

        private boolean isSecureSettingsKey(int i) {
            return SettingsProvider.getTypeFromKey(i) == 2;
        }

        private boolean isSsaidSettingsKey(int i) {
            return SettingsProvider.getTypeFromKey(i) == 3;
        }

        private boolean shouldBan(int i) {
            return 4 == i && UserHandle.getAppId(Binder.getCallingUid()) != 2000;
        }

        private void banConfigurationIfNecessary(int i, String str, SettingsState settingsState) {
            if (shouldBan(i)) {
                if (str != null) {
                    settingsState.banConfigurationLocked(str, SettingsProvider.this.getAllConfigFlags(str));
                    return;
                }
                for (String str2 : settingsState.getAllConfigPrefixesLocked()) {
                    settingsState.banConfigurationLocked(str2, SettingsProvider.this.getAllConfigFlags(str2));
                }
            }
        }

        /* JADX INFO: Access modifiers changed from: private */
        public File getSettingsFile(int i) {
            if (isConfigSettingsKey(i)) {
                return new File(Environment.getUserSystemDirectory(SettingsProvider.getUserIdFromKey(i)), "settings_config.xml");
            }
            if (isGlobalSettingsKey(i)) {
                return new File(Environment.getUserSystemDirectory(SettingsProvider.getUserIdFromKey(i)), "settings_global.xml");
            }
            if (isSystemSettingsKey(i)) {
                return new File(Environment.getUserSystemDirectory(SettingsProvider.getUserIdFromKey(i)), "settings_system.xml");
            }
            if (isSecureSettingsKey(i)) {
                return new File(Environment.getUserSystemDirectory(SettingsProvider.getUserIdFromKey(i)), "settings_secure.xml");
            }
            if (isSsaidSettingsKey(i)) {
                return new File(Environment.getUserSystemDirectory(SettingsProvider.getUserIdFromKey(i)), "settings_ssaid.xml");
            }
            throw new IllegalArgumentException("Invalid settings key:" + i);
        }

        private Uri getNotificationUriFor(int i, String str) {
            if (isConfigSettingsKey(i)) {
                if (str != null) {
                    return Uri.withAppendedPath(DeviceConfig.CONTENT_URI, str);
                }
                return DeviceConfig.CONTENT_URI;
            }
            if (isGlobalSettingsKey(i)) {
                if (str != null) {
                    return Uri.withAppendedPath(Settings.Global.CONTENT_URI, str);
                }
                return Settings.Global.CONTENT_URI;
            }
            if (isSecureSettingsKey(i)) {
                if (str != null) {
                    return Uri.withAppendedPath(Settings.Secure.CONTENT_URI, str);
                }
                return Settings.Secure.CONTENT_URI;
            }
            if (isSystemSettingsKey(i)) {
                if (str != null) {
                    return Uri.withAppendedPath(Settings.System.CONTENT_URI, str);
                }
                return Settings.System.CONTENT_URI;
            }
            throw new IllegalArgumentException("Invalid settings key:" + i);
        }

        private final class MyHandler extends Handler {
            public MyHandler(Looper looper) {
                super(looper);
            }

            @Override // android.os.Handler
            public void handleMessage(Message message) {
                int i = message.what;
                if (i != 1) {
                    if (i != 2) {
                        return;
                    }
                    SettingsRegistry.this.mBackupManager.dataChanged();
                    SettingsProvider.this.scheduleWriteFallbackFilesJob();
                    return;
                }
                int i2 = message.arg1;
                Uri uri = (Uri) message.obj;
                try {
                    SettingsProvider.this.getContext().getContentResolver().notifyChange(uri, null, true, i2);
                } catch (SecurityException e) {
                    Slog.w("SettingsProvider", "Failed to notify for " + i2 + ": " + uri, e);
                }
                Slog.v("SettingsProvider", "Notifying for " + i2 + ": " + uri);
            }
        }

        private final class UpgradeController {
            private final int mUserId;

            public UpgradeController(int i) {
                this.mUserId = i;
            }

            public void upgradeIfNeededLocked() {
                SettingsState settingsLocked = SettingsRegistry.this.getSettingsLocked(2, this.mUserId);
                int versionLocked = settingsLocked.getVersionLocked();
                if (versionLocked == 191) {
                    return;
                }
                int iOnUpgradeLocked = onUpgradeLocked(this.mUserId, versionLocked, 191);
                if (iOnUpgradeLocked != 191) {
                    SettingsRegistry.this.removeUserStateLocked(this.mUserId, true);
                    DatabaseHelper databaseHelper = new DatabaseHelper(SettingsProvider.this.getContext(), this.mUserId);
                    SQLiteDatabase writableDatabase = databaseHelper.getWritableDatabase();
                    databaseHelper.recreateDatabase(writableDatabase, 191, iOnUpgradeLocked, versionLocked);
                    SettingsRegistry.this.migrateLegacySettingsForUserLocked(databaseHelper, writableDatabase, this.mUserId);
                    onUpgradeLocked(this.mUserId, versionLocked, 191);
                    getGlobalSettingsLocked().insertSettingLocked("database_downgrade_reason", "Settings rebuilt! Current version: " + iOnUpgradeLocked + " while expected: 191", null, true, "android");
                }
                int i = this.mUserId;
                if (i == 0) {
                    SettingsRegistry.this.getSettingsLocked(0, i).setVersionLocked(191);
                }
                settingsLocked.setVersionLocked(191);
                SettingsRegistry.this.getSettingsLocked(1, this.mUserId).setVersionLocked(191);
            }

            private SettingsState getGlobalSettingsLocked() {
                return SettingsRegistry.this.getSettingsLocked(0, 0);
            }

            private SettingsState getSecureSettingsLocked(int i) {
                return SettingsRegistry.this.getSettingsLocked(2, i);
            }

            private SettingsState getSsaidSettingsLocked(int i) {
                return SettingsRegistry.this.getSettingsLocked(3, i);
            }

            private SettingsState getSystemSettingsLocked(int i) {
                return SettingsRegistry.this.getSettingsLocked(1, i);
            }

            private int onUpgradeLocked(int i, int i2, int i3) {
                int integer;
                String string;
                ArraySet<ComponentName> defaultVrComponents;
                int i4 = i2;
                Slog.w("SettingsProvider", "Upgrading settings for user: " + i + " from version: " + i4 + " to version: " + i3);
                if (i4 == 118) {
                    if (i == 0) {
                        SettingsState globalSettingsLocked = getGlobalSettingsLocked();
                        globalSettingsLocked.updateSettingLocked("zen_mode", Integer.toString(0), null, true, "android");
                        globalSettingsLocked.updateSettingLocked("mode_ringer", Integer.toString(2), null, true, "android");
                    }
                    i4 = 119;
                }
                if (i4 == 119) {
                    getSecureSettingsLocked(i).insertSettingOverrideableByRestoreLocked("double_tap_to_wake", SettingsProvider.this.getContext().getResources().getBoolean(R.bool.def_double_tap_to_wake) ? "1" : "0", null, true, "android");
                    i4 = 120;
                }
                if (i4 == 120) {
                    i4 = 121;
                }
                if (i4 == 121) {
                    SettingsState secureSettingsLocked = getSecureSettingsLocked(i);
                    String string2 = SettingsProvider.this.getContext().getResources().getString(R.string.def_nfc_payment_component);
                    SettingsState.Setting settingLocked = secureSettingsLocked.getSettingLocked("nfc_payment_default_component");
                    if (string2 != null && !string2.isEmpty() && settingLocked.isNull()) {
                        secureSettingsLocked.insertSettingOverrideableByRestoreLocked("nfc_payment_default_component", string2, null, true, "android");
                    }
                    i4 = 122;
                }
                if (i4 == 122) {
                    if (i == 0) {
                        SettingsState globalSettingsLocked2 = getGlobalSettingsLocked();
                        if (globalSettingsLocked2.getSettingLocked("add_users_when_locked").isNull()) {
                            globalSettingsLocked2.insertSettingOverrideableByRestoreLocked("add_users_when_locked", SettingsProvider.this.getContext().getResources().getBoolean(R.bool.def_add_users_from_lockscreen) ? "1" : "0", null, true, "android");
                        }
                    }
                    i4 = 123;
                }
                if (i4 == 123) {
                    getGlobalSettingsLocked().insertSettingOverrideableByRestoreLocked("bluetooth_disabled_profiles", SettingsProvider.this.getContext().getResources().getString(R.string.def_bluetooth_disabled_profiles), null, true, "android");
                    i4 = 124;
                }
                if (i4 == 124) {
                    SettingsState secureSettingsLocked2 = getSecureSettingsLocked(i);
                    if (secureSettingsLocked2.getSettingLocked("show_ime_with_hard_keyboard").isNull()) {
                        secureSettingsLocked2.insertSettingOverrideableByRestoreLocked("show_ime_with_hard_keyboard", SettingsProvider.this.getContext().getResources().getBoolean(R.bool.def_show_ime_with_hard_keyboard) ? "1" : "0", null, true, "android");
                    }
                    i4 = 125;
                }
                if (i4 == 125) {
                    SettingsState secureSettingsLocked3 = getSecureSettingsLocked(i);
                    if (secureSettingsLocked3.getSettingLocked("enabled_vr_listeners").isNull() && (defaultVrComponents = SystemConfig.getInstance().getDefaultVrComponents()) != null && !defaultVrComponents.isEmpty()) {
                        StringBuilder sb = new StringBuilder();
                        boolean z = true;
                        for (ComponentName componentName : defaultVrComponents) {
                            if (!z) {
                                sb.append(':');
                            }
                            sb.append(componentName.flattenToString());
                            z = false;
                        }
                        secureSettingsLocked3.insertSettingOverrideableByRestoreLocked("enabled_vr_listeners", sb.toString(), null, true, "android");
                    }
                    i4 = 126;
                }
                if (i4 == 126) {
                    if (SettingsProvider.this.mUserManager.isManagedProfile(i)) {
                        SettingsState secureSettingsLocked4 = getSecureSettingsLocked(0);
                        SettingsState.Setting settingLocked2 = secureSettingsLocked4.getSettingLocked("lock_screen_show_notifications");
                        if (!settingLocked2.isNull()) {
                            getSecureSettingsLocked(i).insertSettingOverrideableByRestoreLocked("lock_screen_show_notifications", settingLocked2.getValue(), null, true, "android");
                        }
                        SettingsState.Setting settingLocked3 = secureSettingsLocked4.getSettingLocked("lock_screen_allow_private_notifications");
                        if (!settingLocked3.isNull()) {
                            getSecureSettingsLocked(i).insertSettingOverrideableByRestoreLocked("lock_screen_allow_private_notifications", settingLocked3.getValue(), null, true, "android");
                        }
                    }
                    i4 = 127;
                }
                if (i4 == 127) {
                    i4 = 128;
                }
                if (i4 == 128) {
                    i4 = 129;
                }
                if (i4 == 129) {
                    SettingsState secureSettingsLocked5 = getSecureSettingsLocked(i);
                    if (TextUtils.equals("500", secureSettingsLocked5.getSettingLocked("long_press_timeout").getValue())) {
                        secureSettingsLocked5.insertSettingOverrideableByRestoreLocked("long_press_timeout", String.valueOf(SettingsProvider.this.getContext().getResources().getInteger(R.integer.def_long_press_timeout_millis)), null, true, "android");
                    }
                    i4 = 130;
                }
                if (i4 == 130) {
                    SettingsState secureSettingsLocked6 = getSecureSettingsLocked(i);
                    if ("0".equals(secureSettingsLocked6.getSettingLocked("doze_enabled").getValue())) {
                        secureSettingsLocked6.insertSettingOverrideableByRestoreLocked("doze_pulse_on_pick_up", "0", null, true, "android");
                        secureSettingsLocked6.insertSettingOverrideableByRestoreLocked("doze_pulse_on_double_tap", "0", null, true, "android");
                    }
                    i4 = 131;
                }
                if (i4 == 131) {
                    SettingsState secureSettingsLocked7 = getSecureSettingsLocked(i);
                    if (TextUtils.equals(null, secureSettingsLocked7.getSettingLocked("multi_press_timeout").getValue())) {
                        secureSettingsLocked7.insertSettingOverrideableByRestoreLocked("multi_press_timeout", String.valueOf(SettingsProvider.this.getContext().getResources().getInteger(R.integer.def_multi_press_timeout_millis)), null, true, "android");
                    }
                    i4 = 132;
                }
                if (i4 == 132) {
                    getSecureSettingsLocked(i).insertSettingOverrideableByRestoreLocked("sync_parent_sounds", SettingsProvider.this.getContext().getResources().getBoolean(R.bool.def_sync_parent_sounds) ? "1" : "0", null, true, "android");
                    i4 = 133;
                }
                if (i4 == 133) {
                    SettingsState systemSettingsLocked = getSystemSettingsLocked(i);
                    if (systemSettingsLocked.getSettingLocked("end_button_behavior").isNull()) {
                        systemSettingsLocked.insertSettingOverrideableByRestoreLocked("end_button_behavior", Integer.toString(SettingsProvider.this.getContext().getResources().getInteger(R.integer.def_end_button_behavior)), null, true, "android");
                    }
                    i4 = 134;
                }
                if (i4 == 134) {
                    getSecureSettingsLocked(i).deleteSettingLocked("accessibility_display_magnification_auto_update");
                    i4 = 135;
                }
                if (i4 == 135) {
                    i4 = 136;
                }
                if (i4 == 136) {
                    try {
                        if (SettingsProvider.this.mPackageManager.isDeviceUpgrading()) {
                            SettingsState.Setting settingLocked4 = SettingsRegistry.this.getSettingLocked(2, i, "android_id");
                            if (settingLocked4 == null || settingLocked4.isNull() || settingLocked4.getValue() == null) {
                                throw new IllegalStateException("Legacy ssaid not accessible");
                            }
                            String value = settingLocked4.getValue();
                            try {
                                List<PackageInfo> list = SettingsProvider.this.mPackageManager.getInstalledPackages(8192, i).getList();
                                SettingsState ssaidSettingsLocked = getSsaidSettingsLocked(i);
                                for (PackageInfo packageInfo : list) {
                                    String string3 = Integer.toString(packageInfo.applicationInfo.uid);
                                    SettingsState.Setting settingLocked5 = ssaidSettingsLocked.getSettingLocked(string3);
                                    if (settingLocked5.isNull() || settingLocked5.getValue() == null) {
                                        ssaidSettingsLocked.insertSettingOverrideableByRestoreLocked(string3, value, null, true, packageInfo.packageName);
                                        Slog.d("SettingsProvider", "Keep the legacy ssaid for uid=" + string3);
                                    }
                                }
                            } catch (RemoteException unused) {
                                throw new IllegalStateException("Package manager not available");
                            }
                        }
                        i4 = 137;
                    } catch (RemoteException unused2) {
                        throw new IllegalStateException("Package manager not available");
                    }
                }
                if (i4 == 137) {
                    SettingsState secureSettingsLocked8 = getSecureSettingsLocked(i);
                    if (!SettingsProvider.this.mUserManager.hasUserRestriction("no_install_unknown_sources", UserHandle.of(i)) && secureSettingsLocked8.getSettingLocked("install_non_market_apps").getValue().equals("0")) {
                        secureSettingsLocked8.insertSettingOverrideableByRestoreLocked("install_non_market_apps", "1", null, true, "android");
                        secureSettingsLocked8.insertSettingOverrideableByRestoreLocked("unknown_sources_default_reversed", "1", null, true, "android");
                    }
                    i4 = 138;
                }
                if (i4 == 138) {
                    i4 = 139;
                }
                if (i4 == 139) {
                    getSecureSettingsLocked(i).updateSettingLocked("speak_password", "1", null, true, "android");
                    i4 = 140;
                }
                if (i4 == 140) {
                    i4 = 141;
                }
                if (i4 == 141) {
                    i4 = 142;
                }
                if (i4 == 142) {
                    if (i == 0) {
                        SettingsState globalSettingsLocked3 = getGlobalSettingsLocked();
                        if (globalSettingsLocked3.getSettingLocked("wifi_wakeup_enabled").isNull()) {
                            globalSettingsLocked3.insertSettingOverrideableByRestoreLocked("wifi_wakeup_enabled", SettingsProvider.this.getContext().getResources().getBoolean(R.bool.def_wifi_wakeup_enabled) ? "1" : "0", null, true, "android");
                        }
                    }
                    i4 = 143;
                }
                if (i4 == 143) {
                    SettingsState secureSettingsLocked9 = getSecureSettingsLocked(i);
                    if (secureSettingsLocked9.getSettingLocked("autofill_service").isNull() && (string = SettingsProvider.this.getContext().getResources().getString(android.R.string.common_name)) != null) {
                        Slog.d("SettingsProvider", "Setting [" + string + "] as Autofill Service for user " + i);
                        secureSettingsLocked9.insertSettingOverrideableByRestoreLocked("autofill_service", string, null, true, "android");
                    }
                    i4 = 144;
                }
                if (i4 == 144) {
                    i4 = 145;
                }
                if (i4 == 145) {
                    if (i == 0) {
                        SettingsState globalSettingsLocked4 = getGlobalSettingsLocked();
                        SettingsRegistry.this.ensureLegacyDefaultValueAndSystemSetUpdatedLocked(globalSettingsLocked4, i);
                        globalSettingsLocked4.persistSyncLocked();
                    }
                    SettingsState secureSettingsLocked10 = getSecureSettingsLocked(this.mUserId);
                    SettingsRegistry.this.ensureLegacyDefaultValueAndSystemSetUpdatedLocked(secureSettingsLocked10, i);
                    secureSettingsLocked10.persistSyncLocked();
                    SettingsState systemSettingsLocked2 = getSystemSettingsLocked(this.mUserId);
                    SettingsRegistry.this.ensureLegacyDefaultValueAndSystemSetUpdatedLocked(systemSettingsLocked2, i);
                    systemSettingsLocked2.persistSyncLocked();
                    i4 = 146;
                }
                if (i4 == 146) {
                    i4 = 147;
                }
                if (i4 == 147) {
                    if (i == 0) {
                        SettingsState globalSettingsLocked5 = getGlobalSettingsLocked();
                        if (globalSettingsLocked5.getSettingLocked("default_restrict_background_data").isNull()) {
                            globalSettingsLocked5.insertSettingOverrideableByRestoreLocked("default_restrict_background_data", SettingsProvider.this.getContext().getResources().getBoolean(R.bool.def_restrict_background_data) ? "1" : "0", null, true, "android");
                        }
                    }
                    i4 = 148;
                }
                if (i4 == 148) {
                    SettingsState secureSettingsLocked11 = getSecureSettingsLocked(i);
                    if (TextUtils.equals(null, secureSettingsLocked11.getSettingLocked("backup_manager_constants").getValue())) {
                        String string4 = SettingsProvider.this.getContext().getResources().getString(R.string.def_backup_manager_constants);
                        if (!TextUtils.isEmpty(string4)) {
                            secureSettingsLocked11.insertSettingOverrideableByRestoreLocked("backup_manager_constants", string4, null, true, "android");
                        }
                    }
                    i4 = 149;
                }
                if (i4 == 149) {
                    SettingsState globalSettingsLocked6 = getGlobalSettingsLocked();
                    if (globalSettingsLocked6.getSettingLocked("mobile_data_always_on").isNull()) {
                        globalSettingsLocked6.insertSettingOverrideableByRestoreLocked("mobile_data_always_on", SettingsProvider.this.getContext().getResources().getBoolean(R.bool.def_mobile_data_always_on) ? "1" : "0", null, true, "android");
                    }
                    i4 = 150;
                }
                if (i4 == 150) {
                    i4 = 151;
                }
                if (i4 == 151) {
                    i4 = 152;
                }
                if (i4 == 152) {
                    getGlobalSettingsLocked().deleteSettingLocked("wifi_wakeup_available");
                    i4 = 153;
                }
                if (i4 == 153) {
                    SettingsState secureSettingsLocked12 = getSecureSettingsLocked(i);
                    if (secureSettingsLocked12.getSettingLocked("notification_badging").isNull()) {
                        secureSettingsLocked12.insertSettingOverrideableByRestoreLocked("notification_badging", SettingsProvider.this.getContext().getResources().getBoolean(android.R.bool.config_cecQuerySadDtshdDisabled_default) ? "1" : "0", null, true, "android");
                    }
                    i4 = 154;
                }
                if (i4 == 154) {
                    SettingsState secureSettingsLocked13 = getSecureSettingsLocked(i);
                    if (TextUtils.equals(null, secureSettingsLocked13.getSettingLocked("backup_local_transport_parameters").getValue())) {
                        String string5 = SettingsProvider.this.getContext().getResources().getString(R.string.def_backup_local_transport_parameters);
                        if (!TextUtils.isEmpty(string5)) {
                            secureSettingsLocked13.insertSettingOverrideableByRestoreLocked("backup_local_transport_parameters", string5, null, true, "android");
                        }
                    }
                    i4 = 155;
                }
                if (i4 == 155) {
                    i4 = 156;
                }
                if (i4 == 156) {
                    SettingsState globalSettingsLocked7 = getGlobalSettingsLocked();
                    if (globalSettingsLocked7.getSettingLocked("zen_duration").isNull()) {
                        globalSettingsLocked7.insertSettingOverrideableByRestoreLocked("zen_duration", Integer.toString(SettingsProvider.this.getContext().getResources().getInteger(R.integer.def_zen_duration)), null, true, "android");
                    }
                    i4 = 157;
                }
                if (i4 == 157) {
                    SettingsState globalSettingsLocked8 = getGlobalSettingsLocked();
                    if (TextUtils.equals(null, globalSettingsLocked8.getSettingLocked("backup_agent_timeout_parameters").getValue())) {
                        String string6 = SettingsProvider.this.getContext().getResources().getString(R.string.def_backup_agent_timeout_parameters);
                        if (!TextUtils.isEmpty(string6)) {
                            globalSettingsLocked8.insertSettingOverrideableByRestoreLocked("backup_agent_timeout_parameters", string6, null, true, "android");
                        }
                    }
                    i4 = 158;
                }
                if (i4 == 158) {
                    getGlobalSettingsLocked().deleteSettingLocked("wifi_scan_background_throttle_interval_ms");
                    getGlobalSettingsLocked().deleteSettingLocked("wifi_scan_background_throttle_package_whitelist");
                    i4 = 159;
                }
                if (i4 == 159) {
                    if (SettingsProvider.this.mUserManager.isManagedProfile(i)) {
                        SettingsState secureSettingsLocked14 = getSecureSettingsLocked(i);
                        if ("0".equals(secureSettingsLocked14.getSettingLocked("lock_screen_show_notifications").getValue())) {
                            secureSettingsLocked14.insertSettingOverrideableByRestoreLocked("lock_screen_allow_private_notifications", "0", null, false, "android");
                        }
                        secureSettingsLocked14.deleteSettingLocked("lock_screen_show_notifications");
                    }
                    i4 = 160;
                }
                if (i4 == 160) {
                    SettingsState globalSettingsLocked9 = getGlobalSettingsLocked();
                    if (TextUtils.equals(null, globalSettingsLocked9.getSettingLocked("max_sound_trigger_detection_service_ops_per_day").getValue())) {
                        globalSettingsLocked9.insertSettingOverrideableByRestoreLocked("max_sound_trigger_detection_service_ops_per_day", Integer.toString(SettingsProvider.this.getContext().getResources().getInteger(R.integer.def_max_sound_trigger_detection_service_ops_per_day)), null, true, "android");
                    }
                    if (TextUtils.equals(null, globalSettingsLocked9.getSettingLocked("sound_trigger_detection_service_op_timeout").getValue())) {
                        globalSettingsLocked9.insertSettingOverrideableByRestoreLocked("sound_trigger_detection_service_op_timeout", Integer.toString(SettingsProvider.this.getContext().getResources().getInteger(R.integer.def_sound_trigger_detection_service_op_timeout)), null, true, "android");
                    }
                    i4 = 161;
                }
                if (i4 == 161) {
                    SettingsState secureSettingsLocked15 = getSecureSettingsLocked(i);
                    if (secureSettingsLocked15.getSettingLocked("volume_hush_gesture").isNull()) {
                        secureSettingsLocked15.insertSettingOverrideableByRestoreLocked("volume_hush_gesture", Integer.toString(1), null, true, "android");
                    }
                    i4 = 162;
                }
                if (i4 == 162) {
                    i4 = 163;
                }
                if (i4 == 163) {
                    SettingsState globalSettingsLocked10 = getGlobalSettingsLocked();
                    if (globalSettingsLocked10.getSettingLocked("max_sound_trigger_detection_service_ops_per_day").isDefaultFromSystem()) {
                        globalSettingsLocked10.insertSettingOverrideableByRestoreLocked("max_sound_trigger_detection_service_ops_per_day", Integer.toString(SettingsProvider.this.getContext().getResources().getInteger(R.integer.def_max_sound_trigger_detection_service_ops_per_day)), null, true, "android");
                    }
                    i4 = 164;
                }
                if (i4 == 164) {
                    i4 = 165;
                }
                if (i4 == 165) {
                    i4 = 166;
                }
                if (i4 == 166) {
                    SettingsState secureSettingsLocked16 = getSecureSettingsLocked(i);
                    if (secureSettingsLocked16.getSettingLocked("hush_gesture_used").isNull()) {
                        secureSettingsLocked16.insertSettingOverrideableByRestoreLocked("hush_gesture_used", "0", null, true, "android");
                    }
                    if (secureSettingsLocked16.getSettingLocked("manual_ringer_toggle_count").isNull()) {
                        secureSettingsLocked16.insertSettingOverrideableByRestoreLocked("manual_ringer_toggle_count", "0", null, true, "android");
                    }
                    i4 = 167;
                }
                if (i4 == 167) {
                    i4 = 168;
                }
                if (i4 == 168) {
                    SettingsState systemSettingsLocked3 = getSystemSettingsLocked(i);
                    if (systemSettingsLocked3.getSettingLocked("vibrate_when_ringing").isNull()) {
                        systemSettingsLocked3.insertSettingOverrideableByRestoreLocked("vibrate_when_ringing", SettingsProvider.this.getContext().getResources().getBoolean(R.bool.def_vibrate_when_ringing) ? "1" : "0", null, true, "android");
                    }
                    i4 = 169;
                }
                if (i4 == 169) {
                    SettingsState globalSettingsLocked11 = getGlobalSettingsLocked();
                    SettingsState.Setting settingLocked6 = globalSettingsLocked11.getSettingLocked("zen_duration");
                    SettingsState secureSettingsLocked17 = getSecureSettingsLocked(i);
                    SettingsState.Setting settingLocked7 = secureSettingsLocked17.getSettingLocked("zen_duration");
                    if (!settingLocked6.isNull()) {
                        secureSettingsLocked17.insertSettingOverrideableByRestoreLocked("zen_duration", settingLocked6.getValue(), null, false, "android");
                        globalSettingsLocked11.insertSettingOverrideableByRestoreLocked("zen_duration", null, null, true, "android");
                    } else if (settingLocked7.isNull()) {
                        secureSettingsLocked17.insertSettingOverrideableByRestoreLocked("zen_duration", Integer.toString(SettingsProvider.this.getContext().getResources().getInteger(R.integer.def_zen_duration)), null, true, "android");
                    }
                    if (secureSettingsLocked17.getSettingLocked("show_zen_settings_suggestion").isNull()) {
                        secureSettingsLocked17.insertSettingOverrideableByRestoreLocked("show_zen_settings_suggestion", "1", null, true, "android");
                    }
                    if (secureSettingsLocked17.getSettingLocked("zen_settings_updated").isNull()) {
                        secureSettingsLocked17.insertSettingOverrideableByRestoreLocked("zen_settings_updated", "0", null, true, "android");
                    }
                    if (secureSettingsLocked17.getSettingLocked("zen_settings_suggestion_viewed").isNull()) {
                        secureSettingsLocked17.insertSettingOverrideableByRestoreLocked("zen_settings_suggestion_viewed", "0", null, true, "android");
                    }
                    i4 = 170;
                }
                if (i4 == 170) {
                    SettingsState globalSettingsLocked12 = getGlobalSettingsLocked();
                    SettingsState secureSettingsLocked18 = getSecureSettingsLocked(i);
                    SettingsState.Setting settingLocked8 = globalSettingsLocked12.getSettingLocked("charging_sounds_enabled");
                    SettingsState.Setting settingLocked9 = secureSettingsLocked18.getSettingLocked("charging_sounds_enabled");
                    if (!settingLocked8.isNull()) {
                        if (settingLocked9.isNull()) {
                            secureSettingsLocked18.insertSettingOverrideableByRestoreLocked("charging_sounds_enabled", settingLocked8.getValue(), null, false, "android");
                        }
                        globalSettingsLocked12.insertSettingOverrideableByRestoreLocked("charging_sounds_enabled", null, null, true, "android");
                    } else if (settingLocked9.isNull()) {
                        secureSettingsLocked18.insertSettingOverrideableByRestoreLocked("charging_sounds_enabled", SettingsProvider.this.getContext().getResources().getBoolean(R.bool.def_charging_sounds_enabled) ? "1" : "0", null, true, "android");
                    }
                    if (secureSettingsLocked18.getSettingLocked("charging_vibration_enabled").isNull()) {
                        secureSettingsLocked18.insertSettingOverrideableByRestoreLocked("charging_vibration_enabled", SettingsProvider.this.getContext().getResources().getBoolean(R.bool.def_charging_vibration_enabled) ? "1" : "0", null, true, "android");
                    }
                    i4 = 171;
                }
                if (i4 == 171) {
                    SettingsState systemSettingsLocked4 = getSystemSettingsLocked(i);
                    SettingsState.Setting settingLocked10 = systemSettingsLocked4.getSettingLocked("mute_streams_affected");
                    if (!settingLocked10.isNull()) {
                        try {
                            int i5 = Integer.parseInt(settingLocked10.getValue());
                            if ((i5 & 1) == 0) {
                                systemSettingsLocked4.insertSettingOverrideableByRestoreLocked("mute_streams_affected", Integer.toString(i5 | 1), null, true, "android");
                            }
                        } catch (NumberFormatException e) {
                            Slog.w("Failed to parse integer value of MUTE_STREAMS_AFFECTEDsetting, removing setting", e);
                            systemSettingsLocked4.deleteSettingLocked("mute_streams_affected");
                        }
                    }
                    i4 = 172;
                }
                if (i4 == 172) {
                    SettingsState secureSettingsLocked19 = getSecureSettingsLocked(i);
                    if (secureSettingsLocked19.getSettingLocked("location_mode").isNull()) {
                        SettingsState.Setting settingLocked11 = secureSettingsLocked19.getSettingLocked("location_providers_allowed");
                        if (settingLocked11.isNull()) {
                            integer = SettingsProvider.this.getContext().getResources().getInteger(R.integer.def_location_mode);
                        } else {
                            integer = TextUtils.isEmpty(settingLocked11.getValue()) ? 0 : 3;
                        }
                        secureSettingsLocked19.insertSettingOverrideableByRestoreLocked("location_mode", Integer.toString(integer), null, true, "android");
                    }
                    i4 = 173;
                }
                if (i4 == 173) {
                    i4 = 174;
                }
                if (i4 == 174) {
                    SettingsState globalSettingsLocked13 = getGlobalSettingsLocked();
                    if (globalSettingsLocked13.getSettingLocked("apply_ramping_ringer").isNull()) {
                        globalSettingsLocked13.insertSettingOverrideableByRestoreLocked("apply_ramping_ringer", SettingsProvider.this.getContext().getResources().getBoolean(R.bool.def_apply_ramping_ringer) ? "1" : "0", null, true, "android");
                    }
                    i4 = 175;
                }
                if (i4 == 175) {
                    SettingsState systemSettingsLocked5 = getSystemSettingsLocked(i);
                    SettingsState.Setting settingLocked12 = systemSettingsLocked5.getSettingLocked("notification_vibration_intensity");
                    SettingsState.Setting settingLocked13 = systemSettingsLocked5.getSettingLocked("ring_vibration_intensity");
                    if (!settingLocked12.isNull() && settingLocked13.isNull()) {
                        systemSettingsLocked5.insertSettingOverrideableByRestoreLocked("ring_vibration_intensity", settingLocked12.getValue(), null, true, "android");
                    }
                    i4 = 176;
                }
                if (i4 == 176) {
                    SettingsState.Setting settingLocked14 = getSecureSettingsLocked(i).getSettingLocked("swipe_up_to_switch_apps_enabled");
                    if (settingLocked14 != null && !settingLocked14.isNull() && settingLocked14.getValue().equals("1")) {
                        try {
                            IOverlayManager.Stub.asInterface(ServiceManager.getService("overlay")).setEnabledExclusiveInCategory("com.android.internal.systemui.navbar.twobutton", -2);
                        } catch (RemoteException | IllegalStateException | SecurityException unused3) {
                            throw new IllegalStateException("Failed to set nav bar interaction mode overlay");
                        }
                    }
                    i4 = 177;
                }
                if (i4 == 177) {
                    SettingsState secureSettingsLocked20 = getSecureSettingsLocked(i);
                    if (secureSettingsLocked20.getSettingLocked("aware_enabled").isNull()) {
                        secureSettingsLocked20.insertSettingOverrideableByRestoreLocked("aware_enabled", SettingsProvider.this.getContext().getResources().getBoolean(R.bool.def_aware_enabled) ? "1" : "0", null, true, "android");
                    }
                    i4 = 178;
                }
                if (i4 == 178) {
                    SettingsState secureSettingsLocked21 = getSecureSettingsLocked(i);
                    if (secureSettingsLocked21.getSettingLocked("skip_gesture").isNull()) {
                        secureSettingsLocked21.insertSettingOverrideableByRestoreLocked("skip_gesture", SettingsProvider.this.getContext().getResources().getBoolean(R.bool.def_skip_gesture) ? "1" : "0", null, true, "android");
                    }
                    if (secureSettingsLocked21.getSettingLocked("silence_gesture").isNull()) {
                        secureSettingsLocked21.insertSettingOverrideableByRestoreLocked("silence_gesture", SettingsProvider.this.getContext().getResources().getBoolean(R.bool.def_silence_gesture) ? "1" : "0", null, true, "android");
                    }
                    i4 = 179;
                }
                if (i4 == 179) {
                    i4 = 180;
                }
                if (i4 == 180) {
                    SettingsState secureSettingsLocked22 = getSecureSettingsLocked(i);
                    if (secureSettingsLocked22.getSettingLocked("aware_lock_enabled").isNull()) {
                        secureSettingsLocked22.insertSettingOverrideableByRestoreLocked("aware_lock_enabled", SettingsProvider.this.getContext().getResources().getBoolean(R.bool.def_aware_lock_enabled) ? "1" : "0", null, true, "android");
                    }
                    i4 = 181;
                }
                if (i4 == 181) {
                    SettingsState systemSettingsLocked6 = getSystemSettingsLocked(i);
                    SettingsState.Setting settingLocked15 = systemSettingsLocked6.getSettingLocked("mute_streams_affected");
                    if (!settingLocked15.isNull()) {
                        try {
                            int i6 = Integer.parseInt(settingLocked15.getValue());
                            if ((i6 & 64) == 0) {
                                systemSettingsLocked6.insertSettingOverrideableByRestoreLocked("mute_streams_affected", Integer.toString(i6 | 64), null, true, "android");
                            }
                        } catch (NumberFormatException e2) {
                            Slog.w("Failed to parse integer value of MUTE_STREAMS_AFFECTEDsetting, removing setting", e2);
                            systemSettingsLocked6.deleteSettingLocked("mute_streams_affected");
                        }
                    }
                    i4 = 182;
                }
                if (i4 == 182) {
                    getSecureSettingsLocked(i).deleteSettingLocked("notification_bubbles");
                    i4 = 183;
                }
                if (i4 == 183) {
                    SettingsState globalSettingsLocked14 = getGlobalSettingsLocked();
                    String value2 = globalSettingsLocked14.getSettingLocked("wireless_charging_started_sound").getValue();
                    String value3 = globalSettingsLocked14.getSettingLocked("charging_started_sound").getValue();
                    String string7 = SettingsProvider.this.getContext().getResources().getString(R.string.def_wireless_charging_started_sound);
                    String string8 = SettingsProvider.this.getContext().getResources().getString(R.string.def_charging_started_sound);
                    if (value2 == null || TextUtils.equals(value2, string8)) {
                        if (!TextUtils.isEmpty(string7)) {
                            globalSettingsLocked14.insertSettingOverrideableByRestoreLocked("wireless_charging_started_sound", string7, null, true, "android");
                        } else if (!TextUtils.isEmpty(string8)) {
                            globalSettingsLocked14.insertSettingOverrideableByRestoreLocked("wireless_charging_started_sound", string8, null, true, "android");
                        }
                    }
                    if (value3 == null && !TextUtils.isEmpty(string8)) {
                        globalSettingsLocked14.insertSettingOverrideableByRestoreLocked("charging_started_sound", string8, null, true, "android");
                    }
                    i4 = 184;
                }
                if (i4 == 184) {
                    getGlobalSettingsLocked().insertSettingOverrideableByRestoreLocked("notification_bubbles", SettingsProvider.this.getContext().getResources().getBoolean(R.bool.def_notification_bubbles) ? "1" : "0", null, true, "android");
                    i4 = 185;
                }
                if (i4 == 185) {
                    SettingsState secureSettingsLocked23 = getSecureSettingsLocked(i);
                    if ("1".equals(secureSettingsLocked23.getSettingLocked("accessibility_display_magnification_navbar_enabled").getValue())) {
                        secureSettingsLocked23.insertSettingLocked("accessibility_button_targets", "com.android.server.accessibility.MagnificationController", null, false, "android");
                    }
                    secureSettingsLocked23.deleteSettingLocked("accessibility_display_magnification_navbar_enabled");
                    i4 = 186;
                }
                if (i4 == 186) {
                    getGlobalSettingsLocked().deleteSettingLocked("wifi_rtt_background_exec_gap_ms");
                    getGlobalSettingsLocked().deleteSettingLocked("network_recommendation_request_timeout_ms");
                    getGlobalSettingsLocked().deleteSettingLocked("wifi_suspend_optimizations_enabled");
                    getGlobalSettingsLocked().deleteSettingLocked("wifi_is_unusable_event_metrics_enabled");
                    getGlobalSettingsLocked().deleteSettingLocked("wifi_data_stall_min_tx_bad");
                    getGlobalSettingsLocked().deleteSettingLocked("wifi_data_stall_min_tx_success_without_rx");
                    getGlobalSettingsLocked().deleteSettingLocked("wifi_link_speed_metrics_enabled");
                    getGlobalSettingsLocked().deleteSettingLocked("wifi_pno_frequency_culling_enabled");
                    getGlobalSettingsLocked().deleteSettingLocked("wifi_pno_recency_sorting_enabled");
                    getGlobalSettingsLocked().deleteSettingLocked("wifi_link_probing_enabled");
                    getGlobalSettingsLocked().deleteSettingLocked("wifi_saved_state");
                    i4 = 187;
                }
                if (i4 == 187) {
                    if (i == 0) {
                        SettingsState systemSettingsLocked7 = getSystemSettingsLocked(i);
                        SettingsState.Setting settingLocked16 = systemSettingsLocked7.getSettingLocked("adaptive_sleep");
                        systemSettingsLocked7.deleteSettingLocked("adaptive_sleep");
                        getSecureSettingsLocked(i).insertSettingLocked("adaptive_sleep", settingLocked16.getValue(), null, false, "android");
                    }
                    i4 = 188;
                }
                if (i4 == 188) {
                    SettingsState secureSettingsLocked24 = getSecureSettingsLocked(i);
                    if ("0".equals(secureSettingsLocked24.getSettingLocked("accessibility_shortcut_enabled").getValue())) {
                        secureSettingsLocked24.insertSettingLocked("accessibility_shortcut_target_service", "", null, false, "android");
                    }
                    secureSettingsLocked24.deleteSettingLocked("accessibility_shortcut_enabled");
                    i4 = 189;
                }
                if (i4 == 189) {
                    SettingsState secureSettingsLocked25 = getSecureSettingsLocked(i);
                    SettingsState.Setting settingLocked17 = secureSettingsLocked25.getSettingLocked("lock_screen_show_notifications");
                    SettingsState.Setting settingLocked18 = secureSettingsLocked25.getSettingLocked("lock_screen_allow_private_notifications");
                    if ("1".equals(settingLocked17.getValue()) && "1".equals(settingLocked18.getValue())) {
                        secureSettingsLocked25.insertSettingLocked("power_menu_locked_show_content", "1", null, false, "android");
                    } else if ("0".equals(settingLocked17.getValue()) || "0".equals(settingLocked18.getValue())) {
                        secureSettingsLocked25.insertSettingLocked("power_menu_locked_show_content", "0", null, false, "android");
                    }
                    i4 = 190;
                }
                if (i4 == 190) {
                    SettingsState globalSettingsLocked15 = getGlobalSettingsLocked();
                    if (globalSettingsLocked15.getSettingLocked("hdmi_control_auto_device_off_enabled").isNull()) {
                        globalSettingsLocked15.insertSettingLocked("hdmi_control_auto_device_off_enabled", SettingsProvider.this.getContext().getResources().getBoolean(R.bool.def_hdmiControlAutoDeviceOff) ? "1" : "0", null, true, "android");
                    }
                    i4 = 191;
                }
                if (i4 == i3) {
                    return i4;
                }
                Slog.wtf("SettingsProvider", "warning: upgrading settings database to version " + i3 + " left it at " + i4 + " instead; this is probably a bug. Did you update SETTINGS_VERSION?", new Throwable());
                throw new RuntimeException("db upgrade error");
            }
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void ensureLegacyDefaultValueAndSystemSetUpdatedLocked(SettingsState settingsState, int i) {
            List<String> settingNamesLocked = settingsState.getSettingNamesLocked();
            int size = settingNamesLocked.size();
            for (int i2 = 0; i2 < size; i2++) {
                String str = settingNamesLocked.get(i2);
                SettingsState.Setting settingLocked = settingsState.getSettingLocked(str);
                int packageUid = -1;
                try {
                    packageUid = SettingsProvider.this.mPackageManager.getPackageUid(settingLocked.getPackageName(), 0, i);
                } catch (RemoteException unused) {
                }
                if (packageUid < 0) {
                    Slog.e("SettingsProvider", "Unknown package: " + settingLocked.getPackageName());
                } else {
                    try {
                        if (SettingsState.isSystemPackage(SettingsProvider.this.getContext(), settingLocked.getPackageName(), packageUid, i)) {
                            settingsState.insertSettingOverrideableByRestoreLocked(str, settingLocked.getValue(), settingLocked.getTag(), true, settingLocked.getPackageName());
                        } else if (settingLocked.getDefaultValue() != null && settingLocked.isDefaultFromSystem()) {
                            settingsState.resetSettingDefaultValueLocked(str);
                        }
                    } catch (IllegalStateException e) {
                        Slog.e("SettingsProvider", "Error upgrading setting: " + settingLocked.getName(), e);
                    }
                }
            }
        }
    }
}
