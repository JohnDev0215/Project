package com.android.providers.settings;

import android.app.backup.BackupAgentHelper;
import android.app.backup.BackupDataInput;
import android.app.backup.BackupDataOutput;
import android.app.backup.FullBackupDataOutput;
import android.database.Cursor;
import android.net.NetworkPolicy;
import android.net.NetworkPolicyManager;
import android.net.Uri;
import android.net.wifi.SoftApConfiguration;
import android.net.wifi.WifiManager;
import android.os.Build;
import android.os.ParcelFileDescriptor;
import android.os.UserHandle;
import android.provider.Settings;
import android.provider.settings.backup.DeviceSpecificSettings;
import android.provider.settings.backup.GlobalSettings;
import android.provider.settings.backup.SecureSettings;
import android.provider.settings.backup.SystemSettings;
import android.provider.settings.validators.GlobalSettingsValidators;
import android.provider.settings.validators.SecureSettingsValidators;
import android.provider.settings.validators.SystemSettingsValidators;
import android.provider.settings.validators.Validator;
import android.util.ArrayMap;
import android.util.ArraySet;
import android.util.BackupUtils;
import android.util.FeatureFlagUtils;
import android.util.Log;
import android.util.Slog;
import com.android.internal.annotations.VisibleForTesting;
import com.android.internal.util.ArrayUtils;
import com.android.internal.widget.LockPatternUtils;
import com.android.settingslib.display.DisplayDensityConfiguration;
import java.io.BufferedOutputStream;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.EOFException;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.time.DateTimeException;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.Map;
import java.util.Objects;
import java.util.Set;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.zip.CRC32;

/* JADX INFO: loaded from: classes.dex */
public class SettingsBackupAgent extends BackupAgentHelper {

    @VisibleForTesting
    static final int DEVICE_SPECIFIC_VERSION = 1;
    private int mRestoredFromSdkInt;

    @VisibleForTesting
    SettingsHelper mSettingsHelper;
    private WifiManager mWifiManager;
    private static final byte[] NULL_VALUE = new byte[0];
    private static final int[] STATE_SIZES = {0, 4, 5, 6, 7, 8, 9, 10, 11};

    @VisibleForTesting
    static final String[] PROJECTION = {"name", "value"};
    private static final ArraySet<String> RESTORE_FROM_HIGHER_SDK_INT_SUPPORTED_KEYS = new ArraySet<>(Arrays.asList("network_policies", "wifi_new_config", "system", "secure", "global"));

    @Override // android.app.backup.BackupAgent
    public void onFullBackup(FullBackupDataOutput fullBackupDataOutput) throws IOException {
    }

    @Override // android.app.backup.BackupAgent
    public void onCreate() {
        this.mSettingsHelper = new SettingsHelper(this);
        this.mWifiManager = (WifiManager) getSystemService("wifi");
        super.onCreate();
    }

    @Override // android.app.backup.BackupAgentHelper, android.app.backup.BackupAgent
    public void onBackup(ParcelFileDescriptor parcelFileDescriptor, BackupDataOutput backupDataOutput, ParcelFileDescriptor parcelFileDescriptor2) throws IOException {
        byte[] systemSettings = getSystemSettings();
        byte[] secureSettings = getSecureSettings();
        byte[] globalSettings = getGlobalSettings();
        byte[] lockSettings = getLockSettings(UserHandle.myUserId());
        byte[] localeData = this.mSettingsHelper.getLocaleData();
        byte[] softAPConfiguration = getSoftAPConfiguration();
        byte[] networkPolicies = getNetworkPolicies();
        byte[] newWifiConfigData = getNewWifiConfigData();
        byte[] deviceSpecificConfiguration = getDeviceSpecificConfiguration();
        long[] oldChecksums = readOldChecksums(parcelFileDescriptor);
        oldChecksums[0] = writeIfChanged(oldChecksums[0], "system", systemSettings, backupDataOutput);
        oldChecksums[DEVICE_SPECIFIC_VERSION] = writeIfChanged(oldChecksums[DEVICE_SPECIFIC_VERSION], "secure", secureSettings, backupDataOutput);
        oldChecksums[5] = writeIfChanged(oldChecksums[5], "global", globalSettings, backupDataOutput);
        oldChecksums[2] = writeIfChanged(oldChecksums[2], "locale", localeData, backupDataOutput);
        oldChecksums[3] = 0;
        oldChecksums[4] = 0;
        oldChecksums[6] = writeIfChanged(oldChecksums[6], "lock_settings", lockSettings, backupDataOutput);
        oldChecksums[7] = writeIfChanged(oldChecksums[7], "softap_config", softAPConfiguration, backupDataOutput);
        oldChecksums[8] = writeIfChanged(oldChecksums[8], "network_policies", networkPolicies, backupDataOutput);
        oldChecksums[9] = writeIfChanged(oldChecksums[9], "wifi_new_config", newWifiConfigData, backupDataOutput);
        oldChecksums[10] = writeIfChanged(oldChecksums[10], "device_specific_config", deviceSpecificConfiguration, backupDataOutput);
        writeNewChecksums(oldChecksums, parcelFileDescriptor2);
    }

    @Override // android.app.backup.BackupAgentHelper, android.app.backup.BackupAgent
    public void onRestore(BackupDataInput backupDataInput, int i, ParcelFileDescriptor parcelFileDescriptor) {
        throw new RuntimeException("SettingsBackupAgent has been migrated to use key exclusion");
    }

    /* JADX INFO: Thrown type has an unknown type hierarchy: android.util.BackupUtils$BadVersionException */
    /* JADX WARN: Failed to find 'out' block for switch in B:56:0x012e. Please report as an issue. */
    public void onRestore(BackupDataInput backupDataInput, long j, ParcelFileDescriptor parcelFileDescriptor, Set<String> set) throws BackupUtils.BadVersionException, IOException {
        byte[] bArr;
        byte[] bArr2;
        HashSet hashSet;
        Set<String> set2;
        Set<String> set3;
        Set<String> set4;
        int i = Build.VERSION.SDK_INT;
        boolean z = Settings.Global.getInt(getContentResolver(), "override_settings_provider_restore_any_version", 0) == DEVICE_SPECIFIC_VERSION ? DEVICE_SPECIFIC_VERSION : false;
        if (j > i && z) {
            Log.w("SettingsBackupAgent", "Ignoring restore from API" + j + " to API" + i + " due to settings flag override.");
            return;
        }
        this.mRestoredFromSdkInt = (int) j;
        HashSet<String> hashSet2 = new HashSet<>();
        Settings.System.getMovedToGlobalSettings(hashSet2);
        Settings.Secure.getMovedToGlobalSettings(hashSet2);
        Set<String> movedToSecureSettings = getMovedToSecureSettings();
        Set<String> settingsToPreserveInRestore = getSettingsToPreserveInRestore(Settings.Global.CONTENT_URI);
        Set<String> settingsToPreserveInRestore2 = getSettingsToPreserveInRestore(Settings.Secure.CONTENT_URI);
        Set<String> settingsToPreserveInRestore3 = getSettingsToPreserveInRestore(Settings.System.CONTENT_URI);
        HashSet hashSet3 = new HashSet(settingsToPreserveInRestore);
        hashSet3.addAll(settingsToPreserveInRestore2);
        hashSet3.addAll(settingsToPreserveInRestore3);
        byte[] bArr3 = null;
        byte[] bArr4 = null;
        while (backupDataInput.readNextHeader()) {
            String key = backupDataInput.getKey();
            int dataSize = backupDataInput.getDataSize();
            if (j > i && !RESTORE_FROM_HIGHER_SDK_INT_SUPPORTED_KEYS.contains(key)) {
                Log.w("SettingsBackupAgent", "Not restoring unrecognized key '" + key + "' from future version " + j);
                backupDataInput.skipEntityData();
            } else {
                switch (key) {
                    case "system":
                        bArr = bArr3;
                        bArr2 = bArr4;
                        hashSet = hashSet3;
                        set2 = settingsToPreserveInRestore3;
                        set3 = settingsToPreserveInRestore2;
                        set4 = settingsToPreserveInRestore;
                        restoreSettings(backupDataInput, Settings.System.CONTENT_URI, hashSet2, movedToSecureSettings, R.array.restore_blocked_system_settings, set, set2);
                        this.mSettingsHelper.applyAudioSettings();
                        bArr3 = bArr;
                        bArr4 = bArr2;
                        break;
                    case "secure":
                        bArr = bArr3;
                        bArr2 = bArr4;
                        hashSet = hashSet3;
                        set2 = settingsToPreserveInRestore3;
                        set3 = settingsToPreserveInRestore2;
                        set4 = settingsToPreserveInRestore;
                        restoreSettings(backupDataInput, Settings.Secure.CONTENT_URI, hashSet2, null, R.array.restore_blocked_secure_settings, set, set3);
                        bArr3 = bArr;
                        bArr4 = bArr2;
                        break;
                    case "global":
                        bArr = bArr3;
                        bArr2 = bArr4;
                        hashSet = hashSet3;
                        set2 = settingsToPreserveInRestore3;
                        set3 = settingsToPreserveInRestore2;
                        set4 = settingsToPreserveInRestore;
                        restoreSettings(backupDataInput, Settings.Global.CONTENT_URI, null, movedToSecureSettings, R.array.restore_blocked_global_settings, set, settingsToPreserveInRestore);
                        bArr3 = bArr;
                        bArr4 = bArr2;
                        break;
                    case "￭WIFI":
                        byte[] bArr5 = new byte[dataSize];
                        backupDataInput.readEntityData(bArr5, 0, dataSize);
                        bArr4 = bArr5;
                        hashSet = hashSet3;
                        set2 = settingsToPreserveInRestore3;
                        set3 = settingsToPreserveInRestore2;
                        set4 = settingsToPreserveInRestore;
                        break;
                    case "locale":
                        byte[] bArr6 = new byte[dataSize];
                        backupDataInput.readEntityData(bArr6, 0, dataSize);
                        this.mSettingsHelper.setLocaleData(bArr6, dataSize);
                        bArr = bArr3;
                        bArr2 = bArr4;
                        hashSet = hashSet3;
                        set2 = settingsToPreserveInRestore3;
                        set3 = settingsToPreserveInRestore2;
                        set4 = settingsToPreserveInRestore;
                        bArr3 = bArr;
                        bArr4 = bArr2;
                        break;
                    case "￭CONFIG_WIFI":
                        byte[] bArr7 = new byte[dataSize];
                        backupDataInput.readEntityData(bArr7, 0, dataSize);
                        bArr3 = bArr7;
                        hashSet = hashSet3;
                        set2 = settingsToPreserveInRestore3;
                        set3 = settingsToPreserveInRestore2;
                        set4 = settingsToPreserveInRestore;
                        break;
                    case "lock_settings":
                        restoreLockSettings(UserHandle.myUserId(), backupDataInput);
                        bArr = bArr3;
                        bArr2 = bArr4;
                        hashSet = hashSet3;
                        set2 = settingsToPreserveInRestore3;
                        set3 = settingsToPreserveInRestore2;
                        set4 = settingsToPreserveInRestore;
                        bArr3 = bArr;
                        bArr4 = bArr2;
                        break;
                    case "softap_config":
                        byte[] bArr8 = new byte[dataSize];
                        backupDataInput.readEntityData(bArr8, 0, dataSize);
                        restoreSoftApConfiguration(bArr8);
                        bArr = bArr3;
                        bArr2 = bArr4;
                        hashSet = hashSet3;
                        set2 = settingsToPreserveInRestore3;
                        set3 = settingsToPreserveInRestore2;
                        set4 = settingsToPreserveInRestore;
                        bArr3 = bArr;
                        bArr4 = bArr2;
                        break;
                    case "network_policies":
                        byte[] bArr9 = new byte[dataSize];
                        backupDataInput.readEntityData(bArr9, 0, dataSize);
                        restoreNetworkPolicies(bArr9);
                        bArr = bArr3;
                        bArr2 = bArr4;
                        hashSet = hashSet3;
                        set2 = settingsToPreserveInRestore3;
                        set3 = settingsToPreserveInRestore2;
                        set4 = settingsToPreserveInRestore;
                        bArr3 = bArr;
                        bArr4 = bArr2;
                        break;
                    case "wifi_new_config":
                        byte[] bArr10 = new byte[dataSize];
                        backupDataInput.readEntityData(bArr10, 0, dataSize);
                        restoreNewWifiConfigData(bArr10);
                        bArr = bArr3;
                        bArr2 = bArr4;
                        hashSet = hashSet3;
                        set2 = settingsToPreserveInRestore3;
                        set3 = settingsToPreserveInRestore2;
                        set4 = settingsToPreserveInRestore;
                        bArr3 = bArr;
                        bArr4 = bArr2;
                        break;
                    case "device_specific_config":
                        byte[] bArr11 = new byte[dataSize];
                        backupDataInput.readEntityData(bArr11, 0, dataSize);
                        restoreDeviceSpecificConfig(bArr11, R.array.restore_blocked_device_specific_settings, set, hashSet3);
                        bArr = bArr3;
                        bArr2 = bArr4;
                        hashSet = hashSet3;
                        set2 = settingsToPreserveInRestore3;
                        set3 = settingsToPreserveInRestore2;
                        set4 = settingsToPreserveInRestore;
                        bArr3 = bArr;
                        bArr4 = bArr2;
                        break;
                    default:
                        bArr = bArr3;
                        bArr2 = bArr4;
                        hashSet = hashSet3;
                        set2 = settingsToPreserveInRestore3;
                        set3 = settingsToPreserveInRestore2;
                        set4 = settingsToPreserveInRestore;
                        backupDataInput.skipEntityData();
                        bArr3 = bArr;
                        bArr4 = bArr2;
                        break;
                }
                hashSet3 = hashSet;
                settingsToPreserveInRestore3 = set2;
                settingsToPreserveInRestore2 = set3;
                settingsToPreserveInRestore = set4;
            }
        }
        byte[] bArr12 = bArr3;
        if (bArr4 != null) {
            restoreSupplicantWifiConfigData(bArr4, bArr12);
        }
    }

    /* JADX INFO: Thrown type has an unknown type hierarchy: android.util.BackupUtils$BadVersionException */
    public void onRestoreFile(ParcelFileDescriptor parcelFileDescriptor, long j, int i, String str, String str2, long j2, long j3) throws BackupUtils.BadVersionException, IOException {
        DataInputStream dataInputStream = new DataInputStream(new FileInputStream(parcelFileDescriptor.getFileDescriptor()));
        int i2 = dataInputStream.readInt();
        if (i2 <= 7) {
            HashSet<String> hashSet = new HashSet<>();
            Settings.System.getMovedToGlobalSettings(hashSet);
            Settings.Secure.getMovedToGlobalSettings(hashSet);
            Set<String> movedToSecureSettings = getMovedToSecureSettings();
            int i3 = dataInputStream.readInt();
            byte[] bArr = new byte[i3];
            dataInputStream.readFully(bArr, 0, i3);
            restoreSettings(bArr, i3, Settings.System.CONTENT_URI, hashSet, movedToSecureSettings, R.array.restore_blocked_system_settings, Collections.emptySet(), Collections.emptySet());
            int i4 = dataInputStream.readInt();
            if (i4 > i3) {
                bArr = new byte[i4];
            }
            dataInputStream.readFully(bArr, 0, i4);
            restoreSettings(bArr, i4, Settings.Secure.CONTENT_URI, hashSet, null, R.array.restore_blocked_secure_settings, Collections.emptySet(), Collections.emptySet());
            if (i2 >= 2) {
                int i5 = dataInputStream.readInt();
                if (i5 > bArr.length) {
                    bArr = new byte[i5];
                }
                dataInputStream.readFully(bArr, 0, i5);
                hashSet.clear();
                restoreSettings(bArr, i5, Settings.Global.CONTENT_URI, hashSet, movedToSecureSettings, R.array.restore_blocked_global_settings, Collections.emptySet(), Collections.emptySet());
            }
            int i6 = dataInputStream.readInt();
            if (i6 > bArr.length) {
                bArr = new byte[i6];
            }
            dataInputStream.readFully(bArr, 0, i6);
            this.mSettingsHelper.setLocaleData(bArr, i6);
            if (i2 < 6) {
                int i7 = dataInputStream.readInt();
                byte[] bArr2 = new byte[i7];
                dataInputStream.readFully(bArr2, 0, i7);
                byte[] bArr3 = new byte[dataInputStream.readInt()];
                dataInputStream.readFully(bArr3, 0, i6);
                restoreSupplicantWifiConfigData(bArr2, bArr3);
            }
            if (i2 >= 3) {
                int i8 = dataInputStream.readInt();
                if (i8 > bArr.length) {
                    bArr = new byte[i8];
                }
                if (i8 > 0) {
                    dataInputStream.readFully(bArr, 0, i8);
                    restoreLockSettings(UserHandle.myUserId(), bArr, i8);
                }
            }
            if (i2 >= 4) {
                int i9 = dataInputStream.readInt();
                if (i9 > bArr.length) {
                    bArr = new byte[i9];
                }
                if (i9 > 0) {
                    dataInputStream.readFully(bArr, 0, i9);
                    restoreSoftApConfiguration(bArr);
                }
            }
            if (i2 >= 5) {
                int i10 = dataInputStream.readInt();
                if (i10 > bArr.length) {
                    bArr = new byte[i10];
                }
                if (i10 > 0) {
                    dataInputStream.readFully(bArr, 0, i10);
                    restoreNetworkPolicies(bArr);
                }
            }
            if (i2 >= 6) {
                int i11 = dataInputStream.readInt();
                if (i11 > bArr.length) {
                    bArr = new byte[i11];
                }
                dataInputStream.readFully(bArr, 0, i11);
                restoreNewWifiConfigData(bArr);
                return;
            }
            return;
        }
        parcelFileDescriptor.close();
        throw new IOException("Invalid file schema");
    }

    private Set<String> getMovedToSecureSettings() {
        HashSet hashSet = new HashSet();
        Settings.Global.getMovedToSecureSettings(hashSet);
        Settings.System.getMovedToSecureSettings(hashSet);
        return hashSet;
    }

    private long[] readOldChecksums(ParcelFileDescriptor parcelFileDescriptor) throws IOException {
        long[] jArr = new long[11];
        DataInputStream dataInputStream = new DataInputStream(new FileInputStream(parcelFileDescriptor.getFileDescriptor()));
        try {
            int i = dataInputStream.readInt();
            if (i > 8) {
                i = 8;
            }
            for (int i2 = 0; i2 < STATE_SIZES[i]; i2 += DEVICE_SPECIFIC_VERSION) {
                jArr[i2] = dataInputStream.readLong();
            }
        } catch (EOFException unused) {
        }
        dataInputStream.close();
        return jArr;
    }

    private void writeNewChecksums(long[] jArr, ParcelFileDescriptor parcelFileDescriptor) throws IOException {
        DataOutputStream dataOutputStream = new DataOutputStream(new BufferedOutputStream(new FileOutputStream(parcelFileDescriptor.getFileDescriptor())));
        dataOutputStream.writeInt(8);
        for (int i = 0; i < 11; i += DEVICE_SPECIFIC_VERSION) {
            dataOutputStream.writeLong(jArr[i]);
        }
        dataOutputStream.close();
    }

    private long writeIfChanged(long j, String str, byte[] bArr, BackupDataOutput backupDataOutput) {
        CRC32 crc32 = new CRC32();
        crc32.update(bArr);
        long value = crc32.getValue();
        if (j == value) {
            return j;
        }
        try {
            backupDataOutput.writeEntityHeader(str, bArr.length);
            backupDataOutput.writeEntityData(bArr, bArr.length);
        } catch (IOException unused) {
        }
        return value;
    }

    private byte[] getSystemSettings() {
        Cursor cursorQuery = getContentResolver().query(Settings.System.CONTENT_URI, PROJECTION, null, null, null);
        try {
            return extractRelevantValues(cursorQuery, SystemSettings.SETTINGS_TO_BACKUP);
        } finally {
            cursorQuery.close();
        }
    }

    private byte[] getSecureSettings() {
        Cursor cursorQuery = getContentResolver().query(Settings.Secure.CONTENT_URI, PROJECTION, null, null, null);
        try {
            return extractRelevantValues(cursorQuery, SecureSettings.SETTINGS_TO_BACKUP);
        } finally {
            cursorQuery.close();
        }
    }

    private byte[] getGlobalSettings() {
        Cursor cursorQuery = getContentResolver().query(Settings.Global.CONTENT_URI, PROJECTION, null, null, null);
        try {
            return extractRelevantValues(cursorQuery, GlobalSettings.SETTINGS_TO_BACKUP);
        } finally {
            cursorQuery.close();
        }
    }

    private Set<String> getSettingsToPreserveInRestore(Uri uri) {
        if (!FeatureFlagUtils.isEnabled(getBaseContext(), "settings_do_not_restore_preserved")) {
            return Collections.emptySet();
        }
        Cursor cursorQuery = getContentResolver().query(uri, new String[]{"name", "is_preserved_in_restore"}, null, null, null);
        if (!cursorQuery.moveToFirst()) {
            Slog.i("SettingsBackupAgent", "No settings to be preserved in restore");
            return Collections.emptySet();
        }
        int columnIndex = cursorQuery.getColumnIndex("name");
        int columnIndex2 = cursorQuery.getColumnIndex("is_preserved_in_restore");
        HashSet hashSet = new HashSet();
        while (!cursorQuery.isAfterLast()) {
            if (Boolean.parseBoolean(cursorQuery.getString(columnIndex2))) {
                hashSet.add(getQualifiedKeyForSetting(cursorQuery.getString(columnIndex), uri));
            }
            cursorQuery.moveToNext();
        }
        return hashSet;
    }

    private byte[] getLockSettings(int i) {
        LockPatternUtils lockPatternUtils = new LockPatternUtils(this);
        boolean zIsOwnerInfoEnabled = lockPatternUtils.isOwnerInfoEnabled(i);
        String ownerInfo = lockPatternUtils.getOwnerInfo(i);
        lockPatternUtils.isLockPatternEnabled(i);
        boolean zIsVisiblePatternEnabled = lockPatternUtils.isVisiblePatternEnabled(i);
        boolean powerButtonInstantlyLocks = lockPatternUtils.getPowerButtonInstantlyLocks(i);
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        DataOutputStream dataOutputStream = new DataOutputStream(byteArrayOutputStream);
        try {
            dataOutputStream.writeUTF("owner_info_enabled");
            String str = "1";
            dataOutputStream.writeUTF(zIsOwnerInfoEnabled ? "1" : "0");
            if (ownerInfo != null) {
                dataOutputStream.writeUTF("owner_info");
                if (ownerInfo == null) {
                    ownerInfo = "";
                }
                dataOutputStream.writeUTF(ownerInfo);
            }
            if (lockPatternUtils.isVisiblePatternEverChosen(i)) {
                dataOutputStream.writeUTF("visible_pattern_enabled");
                dataOutputStream.writeUTF(zIsVisiblePatternEnabled ? "1" : "0");
            }
            if (lockPatternUtils.isPowerButtonInstantlyLocksEverChosen(i)) {
                dataOutputStream.writeUTF("power_button_instantly_locks");
                if (!powerButtonInstantlyLocks) {
                    str = "0";
                }
                dataOutputStream.writeUTF(str);
            }
            dataOutputStream.writeUTF("");
            dataOutputStream.flush();
        } catch (IOException unused) {
        }
        return byteArrayOutputStream.toByteArray();
    }

    private void restoreSettings(BackupDataInput backupDataInput, Uri uri, HashSet<String> hashSet, Set<String> set, int i, Set<String> set2, Set<String> set3) {
        int dataSize = backupDataInput.getDataSize();
        byte[] bArr = new byte[dataSize];
        try {
            backupDataInput.readEntityData(bArr, 0, dataSize);
            restoreSettings(bArr, dataSize, uri, hashSet, set, i, set2, set3);
        } catch (IOException unused) {
            Log.e("SettingsBackupAgent", "Couldn't read entity data");
        }
    }

    private void restoreSettings(byte[] bArr, int i, Uri uri, HashSet<String> hashSet, Set<String> set, int i2, Set<String> set2, Set<String> set3) {
        restoreSettings(bArr, 0, i, uri, hashSet, set, i2, set2, set3);
    }

    /* JADX WARN: Removed duplicated region for block: B:40:0x00f2  */
    @com.android.internal.annotations.VisibleForTesting
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    void restoreSettings(byte[] r26, int r27, int r28, android.net.Uri r29, java.util.HashSet<java.lang.String> r30, java.util.Set<java.lang.String> r31, int r32, java.util.Set<java.lang.String> r33, java.util.Set<java.lang.String> r34) {
        /*
            Method dump skipped, instruction units count: 407
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.android.providers.settings.SettingsBackupAgent.restoreSettings(byte[], int, int, android.net.Uri, java.util.HashSet, java.util.Set, int, java.util.Set, java.util.Set):void");
    }

    @VisibleForTesting
    SettingsBackupWhitelist getBackupWhitelist(Uri uri) {
        String[] strArr;
        Map<String, Validator> map;
        if (uri.equals(Settings.Secure.CONTENT_URI)) {
            strArr = (String[]) ArrayUtils.concatElements(String.class, new String[][]{SecureSettings.SETTINGS_TO_BACKUP, Settings.Secure.LEGACY_RESTORE_SETTINGS, DeviceSpecificSettings.DEVICE_SPECIFIC_SETTINGS_TO_BACKUP});
            map = SecureSettingsValidators.VALIDATORS;
        } else if (uri.equals(Settings.System.CONTENT_URI)) {
            strArr = (String[]) ArrayUtils.concatElements(String.class, new String[][]{SystemSettings.SETTINGS_TO_BACKUP, Settings.System.LEGACY_RESTORE_SETTINGS});
            map = SystemSettingsValidators.VALIDATORS;
        } else if (uri.equals(Settings.Global.CONTENT_URI)) {
            strArr = (String[]) ArrayUtils.concatElements(String.class, new String[][]{GlobalSettings.SETTINGS_TO_BACKUP, Settings.Global.LEGACY_RESTORE_SETTINGS});
            map = GlobalSettingsValidators.VALIDATORS;
        } else {
            throw new IllegalArgumentException("Unknown URI: " + uri);
        }
        return new SettingsBackupWhitelist(strArr, map);
    }

    private boolean isBlockedByDynamicList(Set<String> set, Uri uri, String str) {
        return set.contains(Uri.withAppendedPath(uri, str).toString());
    }

    @VisibleForTesting
    static String getQualifiedKeyForSetting(String str, Uri uri) {
        return Uri.withAppendedPath(uri, str).toString();
    }

    @VisibleForTesting
    protected Set<String> getBlockedSettings(int i) {
        return new HashSet(Arrays.asList(getResources().getStringArray(i)));
    }

    private boolean isValidSettingValue(String str, String str2, Map<String, Validator> map) {
        Validator validator;
        return (str == null || map == null || (validator = map.get(str)) == null || !validator.validate(str2)) ? false : true;
    }

    /* JADX WARN: Failed to restore switch over string. Please report as a decompilation issue */
    private void restoreLockSettings(int i, byte[] bArr, int i2) {
        LockPatternUtils lockPatternUtils = new LockPatternUtils(this);
        DataInputStream dataInputStream = new DataInputStream(new ByteArrayInputStream(bArr, 0, i2));
        while (true) {
            try {
                String utf = dataInputStream.readUTF();
                if (utf.length() > 0) {
                    String utf2 = dataInputStream.readUTF();
                    byte b = -1;
                    switch (utf.hashCode()) {
                        case -1849966140:
                            if (utf.equals("power_button_instantly_locks")) {
                                b = 3;
                            }
                            break;
                        case 412331973:
                            if (utf.equals("visible_pattern_enabled")) {
                                b = 2;
                            }
                            break;
                        case 529054396:
                            if (utf.equals("owner_info_enabled")) {
                                b = 0;
                            }
                            break;
                        case 556982970:
                            if (utf.equals("owner_info")) {
                                b = DEVICE_SPECIFIC_VERSION;
                            }
                            break;
                    }
                    if (b == 0) {
                        lockPatternUtils.setOwnerInfoEnabled("1".equals(utf2), i);
                    } else if (b == DEVICE_SPECIFIC_VERSION) {
                        lockPatternUtils.setOwnerInfo(utf2, i);
                    } else if (b == 2) {
                        lockPatternUtils.reportPatternWasChosen(i);
                        lockPatternUtils.setVisiblePatternEnabled("1".equals(utf2), i);
                    } else if (b == 3) {
                        lockPatternUtils.setPowerButtonInstantlyLocks("1".equals(utf2), i);
                    }
                } else {
                    dataInputStream.close();
                    return;
                }
            } catch (IOException unused) {
                return;
            }
        }
    }

    private void restoreLockSettings(int i, BackupDataInput backupDataInput) {
        int dataSize = backupDataInput.getDataSize();
        byte[] bArr = new byte[dataSize];
        try {
            backupDataInput.readEntityData(bArr, 0, dataSize);
            restoreLockSettings(i, bArr, dataSize);
        } catch (IOException unused) {
            Log.e("SettingsBackupAgent", "Couldn't read entity data");
        }
    }

    private byte[] extractRelevantValues(Cursor cursor, String[] strArr) {
        byte[] bArr = NULL_VALUE;
        if (!cursor.moveToFirst()) {
            Log.e("SettingsBackupAgent", "Couldn't read from the cursor");
            return new byte[0];
        }
        int columnIndex = cursor.getColumnIndex("name");
        int columnIndex2 = cursor.getColumnIndex("value");
        int length = strArr.length;
        byte[][] bArr2 = new byte[length * 2][];
        ArrayMap arrayMap = new ArrayMap();
        int length2 = 0;
        int i = 0;
        for (int i2 = 0; i2 < length; i2 += DEVICE_SPECIFIC_VERSION) {
            String str = strArr[i2];
            String str2 = null;
            boolean z = true;
            if (arrayMap.indexOfKey(str) < 0) {
                while (true) {
                    if (cursor.isAfterLast()) {
                        z = false;
                        break;
                    }
                    String string = cursor.getString(columnIndex);
                    String string2 = cursor.getString(columnIndex2);
                    cursor.moveToNext();
                    if (str.equals(string)) {
                        str2 = string2;
                        break;
                    }
                    arrayMap.put(string, string2);
                }
            } else {
                str2 = (String) arrayMap.remove(str);
            }
            if (z) {
                String strOnBackupValue = this.mSettingsHelper.onBackupValue(str, str2);
                byte[] bytes = str.getBytes();
                int length3 = length2 + bytes.length + 4;
                int i3 = i * 2;
                bArr2[i3] = bytes;
                byte[] bytes2 = strOnBackupValue != null ? strOnBackupValue.getBytes() : bArr;
                length2 = length3 + bytes2.length + 4;
                bArr2[i3 + DEVICE_SPECIFIC_VERSION] = bytes2;
                i += DEVICE_SPECIFIC_VERSION;
            }
        }
        byte[] bArr3 = new byte[length2];
        int i4 = i * 2;
        int iWriteInt = 0;
        for (int i5 = 0; i5 < i4; i5 += DEVICE_SPECIFIC_VERSION) {
            byte[] bArr4 = bArr2[i5];
            if (bArr4 != bArr) {
                iWriteInt = writeBytes(bArr3, writeInt(bArr3, iWriteInt, bArr4.length), bArr4);
            } else {
                iWriteInt = writeInt(bArr3, iWriteInt, -1);
            }
        }
        return bArr3;
    }

    private void restoreSupplicantWifiConfigData(byte[] bArr, byte[] bArr2) {
        this.mWifiManager.restoreSupplicantBackupData(bArr, bArr2);
    }

    private byte[] getSoftAPConfiguration() {
        return this.mWifiManager.retrieveSoftApBackupData();
    }

    private void restoreSoftApConfiguration(byte[] bArr) {
        SoftApConfiguration softApConfigurationRestoreSoftApBackupData = this.mWifiManager.restoreSoftApBackupData(bArr);
        if (softApConfigurationRestoreSoftApBackupData == null || this.mWifiManager.getSoftApConfiguration().equals(softApConfigurationRestoreSoftApBackupData)) {
            return;
        }
        Log.d("SettingsBackupAgent", "restored ap configuration requires a conversion, notify the user");
        WifiSoftApConfigChangedNotifier.notifyUserOfConfigConversion(this);
    }

    private byte[] getNetworkPolicies() {
        NetworkPolicy[] networkPolicies = ((NetworkPolicyManager) getSystemService("netpolicy")).getNetworkPolicies();
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        if (networkPolicies != null && networkPolicies.length != 0) {
            DataOutputStream dataOutputStream = new DataOutputStream(byteArrayOutputStream);
            try {
                dataOutputStream.writeInt(DEVICE_SPECIFIC_VERSION);
                dataOutputStream.writeInt(networkPolicies.length);
                int length = networkPolicies.length;
                for (int i = 0; i < length; i += DEVICE_SPECIFIC_VERSION) {
                    NetworkPolicy networkPolicy = networkPolicies[i];
                    if (networkPolicy != null && !networkPolicy.inferred) {
                        byte[] bytesForBackup = networkPolicy.getBytesForBackup();
                        dataOutputStream.writeByte(DEVICE_SPECIFIC_VERSION);
                        dataOutputStream.writeInt(bytesForBackup.length);
                        dataOutputStream.write(bytesForBackup);
                    } else {
                        dataOutputStream.writeByte(0);
                    }
                }
            } catch (IOException e) {
                Log.e("SettingsBackupAgent", "Failed to convert NetworkPolicies to byte array " + e.getMessage());
                byteArrayOutputStream.reset();
            }
        }
        return byteArrayOutputStream.toByteArray();
    }

    private byte[] getNewWifiConfigData() {
        return this.mWifiManager.retrieveBackupData();
    }

    private void restoreNewWifiConfigData(byte[] bArr) {
        this.mWifiManager.restoreBackupData(bArr);
    }

    /* JADX INFO: Thrown type has an unknown type hierarchy: android.util.BackupUtils$BadVersionException */
    private void restoreNetworkPolicies(byte[] bArr) throws BackupUtils.BadVersionException {
        NetworkPolicyManager networkPolicyManager = (NetworkPolicyManager) getSystemService("netpolicy");
        if (bArr == null || bArr.length == 0) {
            return;
        }
        DataInputStream dataInputStream = new DataInputStream(new ByteArrayInputStream(bArr));
        try {
            int i = dataInputStream.readInt();
            if (i < DEVICE_SPECIFIC_VERSION || i > DEVICE_SPECIFIC_VERSION) {
                throw new BackupUtils.BadVersionException("Unknown Backup Serialization Version");
            }
            int i2 = dataInputStream.readInt();
            NetworkPolicy[] networkPolicyArr = new NetworkPolicy[i2];
            for (int i3 = 0; i3 < i2; i3 += DEVICE_SPECIFIC_VERSION) {
                if (dataInputStream.readByte() != 0) {
                    int i4 = dataInputStream.readInt();
                    byte[] bArr2 = new byte[i4];
                    dataInputStream.read(bArr2, 0, i4);
                    networkPolicyArr[i3] = NetworkPolicy.getNetworkPolicyFromBackup(new DataInputStream(new ByteArrayInputStream(bArr2)));
                }
            }
            networkPolicyManager.setNetworkPolicies(networkPolicyArr);
        } catch (IOException | NullPointerException | BackupUtils.BadVersionException | DateTimeException e) {
            Log.e("SettingsBackupAgent", "Failed to convert byte array to NetworkPolicies " + e.getMessage());
        }
    }

    @VisibleForTesting
    byte[] getDeviceSpecificConfiguration() throws IOException {
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        try {
            writeHeader(byteArrayOutputStream);
            byteArrayOutputStream.write(getDeviceSpecificSettings());
            byte[] byteArray = byteArrayOutputStream.toByteArray();
            byteArrayOutputStream.close();
            return byteArray;
        } catch (Throwable th) {
            try {
                byteArrayOutputStream.close();
            } catch (Throwable th2) {
                th.addSuppressed(th2);
            }
            throw th;
        }
    }

    @VisibleForTesting
    void writeHeader(OutputStream outputStream) throws IOException {
        outputStream.write(toByteArray(DEVICE_SPECIFIC_VERSION));
        outputStream.write(toByteArray(Build.MANUFACTURER));
        outputStream.write(toByteArray(Build.PRODUCT));
    }

    private byte[] getDeviceSpecificSettings() {
        Cursor cursorQuery = getContentResolver().query(Settings.Secure.CONTENT_URI, PROJECTION, null, null, null);
        try {
            byte[] bArrExtractRelevantValues = extractRelevantValues(cursorQuery, DeviceSpecificSettings.DEVICE_SPECIFIC_SETTINGS_TO_BACKUP);
            if (cursorQuery != null) {
                cursorQuery.close();
            }
            return bArrExtractRelevantValues;
        } catch (Throwable th) {
            if (cursorQuery != null) {
                try {
                    cursorQuery.close();
                } catch (Throwable th2) {
                    th.addSuppressed(th2);
                }
            }
            throw th;
        }
    }

    @VisibleForTesting
    boolean restoreDeviceSpecificConfig(byte[] bArr, int i, Set<String> set, Set<String> set2) {
        AtomicInteger atomicInteger = new AtomicInteger(0);
        if (!isSourceAcceptable(bArr, atomicInteger)) {
            return false;
        }
        Integer previousDensity = getPreviousDensity();
        restoreSettings(bArr, atomicInteger.get(), bArr.length, Settings.Secure.CONTENT_URI, null, null, i, set, set2);
        updateWindowManagerIfNeeded(previousDensity);
        return true;
    }

    private void updateWindowManagerIfNeeded(Integer num) {
        try {
            int forcedDensity = getForcedDensity();
            if (num == null || num.intValue() != forcedDensity) {
                DisplayDensityConfiguration.setForcedDisplayDensity(0, forcedDensity);
            }
        } catch (Settings.SettingNotFoundException unused) {
        }
    }

    private Integer getPreviousDensity() {
        try {
            return Integer.valueOf(getForcedDensity());
        } catch (Settings.SettingNotFoundException unused) {
            return null;
        }
    }

    private int getForcedDensity() throws Settings.SettingNotFoundException {
        return Settings.Secure.getInt(getContentResolver(), "display_density_forced");
    }

    @VisibleForTesting
    boolean isSourceAcceptable(byte[] bArr, AtomicInteger atomicInteger) {
        if (readInt(bArr, atomicInteger) > DEVICE_SPECIFIC_VERSION) {
            Slog.w("SettingsBackupAgent", "Unable to restore device specific information; Backup is too new");
            return false;
        }
        String string = readString(bArr, atomicInteger);
        if (!Objects.equals(Build.MANUFACTURER, string)) {
            Log.w("SettingsBackupAgent", "Unable to restore device specific information; Manufacturer mismatch ('" + Build.MANUFACTURER + "' and '" + string + "')");
            return false;
        }
        String string2 = readString(bArr, atomicInteger);
        if (Objects.equals(Build.PRODUCT, string2)) {
            return true;
        }
        Log.w("SettingsBackupAgent", "Unable to restore device specific information; Product mismatch ('" + Build.PRODUCT + "' and '" + string2 + "')");
        return false;
    }

    @VisibleForTesting
    static byte[] toByteArray(String str) {
        if (str == null) {
            return toByteArray(-1);
        }
        byte[] bytes = str.getBytes();
        byte[] bArr = new byte[bytes.length + 4];
        writeInt(bArr, 0, bytes.length);
        writeBytes(bArr, 4, bytes);
        return bArr;
    }

    @VisibleForTesting
    static byte[] toByteArray(int i) {
        byte[] bArr = new byte[4];
        writeInt(bArr, 0, i);
        return bArr;
    }

    private String readString(byte[] bArr, AtomicInteger atomicInteger) {
        int i = readInt(bArr, atomicInteger);
        if (i == -1) {
            return null;
        }
        return new String(bArr, atomicInteger.getAndAdd(i), i);
    }

    private static int writeInt(byte[] bArr, int i, int i2) {
        bArr[i + 0] = (byte) ((i2 >> 24) & 255);
        bArr[i + DEVICE_SPECIFIC_VERSION] = (byte) ((i2 >> 16) & 255);
        bArr[i + 2] = (byte) ((i2 >> 8) & 255);
        bArr[i + 3] = (byte) ((i2 >> 0) & 255);
        return i + 4;
    }

    private static int writeBytes(byte[] bArr, int i, byte[] bArr2) {
        System.arraycopy(bArr2, 0, bArr, i, bArr2.length);
        return i + bArr2.length;
    }

    private int readInt(byte[] bArr, AtomicInteger atomicInteger) {
        return readInt(bArr, atomicInteger.getAndAdd(4));
    }

    private int readInt(byte[] bArr, int i) {
        return ((bArr[i] & 255) << 24) | ((bArr[i + DEVICE_SPECIFIC_VERSION] & 255) << 16) | ((bArr[i + 2] & 255) << 8) | ((bArr[i + 3] & 255) << 0);
    }

    @VisibleForTesting
    static class SettingsBackupWhitelist {
        final Map<String, Validator> mSettingsValidators;
        final String[] mSettingsWhitelist;

        SettingsBackupWhitelist(String[] strArr, Map<String, Validator> map) {
            this.mSettingsWhitelist = strArr;
            this.mSettingsValidators = map;
        }
    }
}
