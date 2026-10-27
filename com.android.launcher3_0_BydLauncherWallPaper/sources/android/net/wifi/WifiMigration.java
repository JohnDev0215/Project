package android.net.wifi;

import android.annotation.SystemApi;
import android.content.Context;
import android.os.Environment;
import android.os.Parcel;
import android.os.Parcelable;
import android.os.UserHandle;
import android.provider.Settings;
import android.util.AtomicFile;
import android.util.SparseArray;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.util.Objects;

/* JADX INFO: loaded from: classes2.dex */
@SystemApi
public final class WifiMigration {
    private static final String LEGACY_WIFI_STORE_DIRECTORY_NAME = "wifi";
    public static final int STORE_FILE_SHARED_GENERAL = 0;
    public static final int STORE_FILE_SHARED_SOFTAP = 1;
    public static final int STORE_FILE_USER_GENERAL = 2;
    public static final int STORE_FILE_USER_NETWORK_SUGGESTIONS = 3;
    private static final SparseArray<String> STORE_ID_TO_FILE_NAME = new SparseArray<String>() { // from class: android.net.wifi.WifiMigration.1
        {
            put(0, "WifiConfigStore.xml");
            put(1, "WifiConfigStoreSoftAp.xml");
            put(2, "WifiConfigStore.xml");
            put(3, "WifiConfigStoreNetworkSuggestions.xml");
        }
    };

    @Retention(RetentionPolicy.SOURCE)
    public @interface SharedStoreFileId {
    }

    @Retention(RetentionPolicy.SOURCE)
    public @interface UserStoreFileId {
    }

    private static File getLegacyWifiSharedDirectory() {
        return new File(Environment.getDataMiscDirectory(), "wifi");
    }

    private static File getLegacyWifiUserDirectory(int i) {
        return new File(Environment.getDataMiscCeDirectory(i), "wifi");
    }

    private static AtomicFile getSharedAtomicFile(int i) {
        return new AtomicFile(new File(getLegacyWifiSharedDirectory(), STORE_ID_TO_FILE_NAME.get(i)));
    }

    private static AtomicFile getUserAtomicFile(int i, int i2) {
        return new AtomicFile(new File(getLegacyWifiUserDirectory(i2), STORE_ID_TO_FILE_NAME.get(i)));
    }

    private WifiMigration() {
    }

    public static InputStream convertAndRetrieveSharedConfigStoreFile(int i) {
        if (i != 0 && i != 1) {
            throw new IllegalArgumentException("Invalid shared store file id");
        }
        try {
            return getSharedAtomicFile(i).openRead();
        } catch (FileNotFoundException unused) {
            if (i == 1) {
                return SoftApConfToXmlMigrationUtil.convert();
            }
            return null;
        }
    }

    public static void removeSharedConfigStoreFile(int i) {
        if (i != 0 && i != 1) {
            throw new IllegalArgumentException("Invalid shared store file id");
        }
        AtomicFile sharedAtomicFile = getSharedAtomicFile(i);
        if (sharedAtomicFile.exists()) {
            sharedAtomicFile.delete();
        } else if (i == 1) {
            SoftApConfToXmlMigrationUtil.remove();
        }
    }

    public static InputStream convertAndRetrieveUserConfigStoreFile(int i, UserHandle userHandle) {
        if (i != 2 && i != 3) {
            throw new IllegalArgumentException("Invalid user store file id");
        }
        Objects.requireNonNull(userHandle);
        try {
            return getUserAtomicFile(i, userHandle.getIdentifier()).openRead();
        } catch (FileNotFoundException unused) {
            return null;
        }
    }

    public static void removeUserConfigStoreFile(int i, UserHandle userHandle) {
        if (i != 2 && i != 3) {
            throw new IllegalArgumentException("Invalid user store file id");
        }
        Objects.requireNonNull(userHandle);
        AtomicFile userAtomicFile = getUserAtomicFile(i, userHandle.getIdentifier());
        if (userAtomicFile.exists()) {
            userAtomicFile.delete();
        }
    }

    public static final class SettingsMigrationData implements Parcelable {
        public static final Parcelable.Creator<SettingsMigrationData> CREATOR = new Parcelable.Creator<SettingsMigrationData>() { // from class: android.net.wifi.WifiMigration.SettingsMigrationData.1
            /* JADX WARN: Can't rename method to resolve collision */
            @Override // android.os.Parcelable.Creator
            public SettingsMigrationData createFromParcel(Parcel parcel) {
                return new SettingsMigrationData(parcel.readBoolean(), parcel.readBoolean(), parcel.readString(), parcel.readBoolean(), parcel.readBoolean(), parcel.readBoolean(), parcel.readBoolean());
            }

            /* JADX WARN: Can't rename method to resolve collision */
            @Override // android.os.Parcelable.Creator
            public SettingsMigrationData[] newArray(int i) {
                return new SettingsMigrationData[i];
            }
        };
        private final String mP2pDeviceName;
        private final boolean mP2pFactoryResetPending;
        private final boolean mScanAlwaysAvailable;
        private final boolean mScanThrottleEnabled;
        private final boolean mSoftApTimeoutEnabled;
        private final boolean mVerboseLoggingEnabled;
        private final boolean mWakeupEnabled;

        @Override // android.os.Parcelable
        public int describeContents() {
            return 0;
        }

        private SettingsMigrationData(boolean z, boolean z2, String str, boolean z3, boolean z4, boolean z5, boolean z6) {
            this.mScanAlwaysAvailable = z;
            this.mP2pFactoryResetPending = z2;
            this.mP2pDeviceName = str;
            this.mSoftApTimeoutEnabled = z3;
            this.mWakeupEnabled = z4;
            this.mScanThrottleEnabled = z5;
            this.mVerboseLoggingEnabled = z6;
        }

        @Override // android.os.Parcelable
        public void writeToParcel(Parcel parcel, int i) {
            parcel.writeBoolean(this.mScanAlwaysAvailable);
            parcel.writeBoolean(this.mP2pFactoryResetPending);
            parcel.writeString(this.mP2pDeviceName);
            parcel.writeBoolean(this.mSoftApTimeoutEnabled);
            parcel.writeBoolean(this.mWakeupEnabled);
            parcel.writeBoolean(this.mScanThrottleEnabled);
            parcel.writeBoolean(this.mVerboseLoggingEnabled);
        }

        public boolean isScanAlwaysAvailable() {
            return this.mScanAlwaysAvailable;
        }

        public boolean isP2pFactoryResetPending() {
            return this.mP2pFactoryResetPending;
        }

        public String getP2pDeviceName() {
            return this.mP2pDeviceName;
        }

        public boolean isSoftApTimeoutEnabled() {
            return this.mSoftApTimeoutEnabled;
        }

        public boolean isWakeUpEnabled() {
            return this.mWakeupEnabled;
        }

        public boolean isScanThrottleEnabled() {
            return this.mScanThrottleEnabled;
        }

        public boolean isVerboseLoggingEnabled() {
            return this.mVerboseLoggingEnabled;
        }

        public static final class Builder {
            private String mP2pDeviceName;
            private boolean mP2pFactoryResetPending;
            private boolean mScanAlwaysAvailable;
            private boolean mScanThrottleEnabled;
            private boolean mSoftApTimeoutEnabled;
            private boolean mVerboseLoggingEnabled;
            private boolean mWakeupEnabled;

            public Builder setScanAlwaysAvailable(boolean z) {
                this.mScanAlwaysAvailable = z;
                return this;
            }

            public Builder setP2pFactoryResetPending(boolean z) {
                this.mP2pFactoryResetPending = z;
                return this;
            }

            public Builder setP2pDeviceName(String str) {
                this.mP2pDeviceName = str;
                return this;
            }

            public Builder setSoftApTimeoutEnabled(boolean z) {
                this.mSoftApTimeoutEnabled = z;
                return this;
            }

            public Builder setWakeUpEnabled(boolean z) {
                this.mWakeupEnabled = z;
                return this;
            }

            public Builder setScanThrottleEnabled(boolean z) {
                this.mScanThrottleEnabled = z;
                return this;
            }

            public Builder setVerboseLoggingEnabled(boolean z) {
                this.mVerboseLoggingEnabled = z;
                return this;
            }

            public SettingsMigrationData build() {
                return new SettingsMigrationData(this.mScanAlwaysAvailable, this.mP2pFactoryResetPending, this.mP2pDeviceName, this.mSoftApTimeoutEnabled, this.mWakeupEnabled, this.mScanThrottleEnabled, this.mVerboseLoggingEnabled);
            }
        }
    }

    public static SettingsMigrationData loadFromSettings(Context context) {
        if (Settings.Global.getInt(context.getContentResolver(), Settings.Global.WIFI_MIGRATION_COMPLETED, 0) == 1) {
            return null;
        }
        SettingsMigrationData settingsMigrationDataBuild = new SettingsMigrationData.Builder().setScanAlwaysAvailable(Settings.Global.getInt(context.getContentResolver(), Settings.Global.WIFI_SCAN_ALWAYS_AVAILABLE, 0) == 1).setP2pFactoryResetPending(Settings.Global.getInt(context.getContentResolver(), Settings.Global.WIFI_P2P_PENDING_FACTORY_RESET, 0) == 1).setP2pDeviceName(Settings.Global.getString(context.getContentResolver(), Settings.Global.WIFI_P2P_DEVICE_NAME)).setSoftApTimeoutEnabled(Settings.Global.getInt(context.getContentResolver(), Settings.Global.SOFT_AP_TIMEOUT_ENABLED, 1) == 1).setWakeUpEnabled(Settings.Global.getInt(context.getContentResolver(), Settings.Global.WIFI_WAKEUP_ENABLED, 0) == 1).setScanThrottleEnabled(Settings.Global.getInt(context.getContentResolver(), Settings.Global.WIFI_SCAN_THROTTLE_ENABLED, 1) == 1).setVerboseLoggingEnabled(Settings.Global.getInt(context.getContentResolver(), Settings.Global.WIFI_VERBOSE_LOGGING_ENABLED, 0) == 1).build();
        Settings.Global.putInt(context.getContentResolver(), Settings.Global.WIFI_MIGRATION_COMPLETED, 1);
        return settingsMigrationDataBuild;
    }
}
