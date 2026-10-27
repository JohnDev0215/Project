package cerence.ark.assistant.dm.data;

/* JADX INFO: loaded from: classes2.dex */
public final class DmRecoveryModeParams {
    private final String mAttribute;
    private final String mDeviceId;
    private final String mRecoveryConfigDir;
    private final String mUpdateStorageDir;
    private final String mUserId;

    private DmRecoveryModeParams(Builder builder) {
        this.mRecoveryConfigDir = builder.mRecoveryConfigDir;
        this.mUpdateStorageDir = builder.mUpdateStorageDir;
        this.mUserId = builder.mUserId;
        this.mDeviceId = builder.mDeviceId;
        this.mAttribute = builder.mAttribute;
    }

    public String getRecoveryConfigDir() {
        return this.mRecoveryConfigDir;
    }

    public String getUpdateStorageDir() {
        return this.mUpdateStorageDir;
    }

    public String getUserId() {
        return this.mUserId;
    }

    public String getDeviceId() {
        return this.mDeviceId;
    }

    public String getAttribute() {
        return this.mAttribute;
    }

    public static class Builder {
        private String mAttribute;
        private String mDeviceId;
        private String mRecoveryConfigDir;
        private String mUpdateStorageDir;
        private String mUserId;

        public Builder setRecoveryConfigDir(String str) {
            this.mRecoveryConfigDir = str;
            return this;
        }

        public Builder setUpdateStorageDir(String str) {
            this.mUpdateStorageDir = str;
            return this;
        }

        public Builder setUserId(String str) {
            this.mUserId = str;
            return this;
        }

        public Builder setAttribute(String str) {
            this.mAttribute = str;
            return this;
        }

        public Builder setDeviceId(String str) {
            this.mDeviceId = str;
            return this;
        }

        public DmRecoveryModeParams build() {
            return new DmRecoveryModeParams(this);
        }
    }
}
