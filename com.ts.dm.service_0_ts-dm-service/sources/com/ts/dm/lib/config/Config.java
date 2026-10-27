package com.ts.dm.lib.config;

/* JADX INFO: loaded from: classes.dex */
public class Config {
    private int mDeviceListMaxSize;
    private boolean mEnableAndroidAuto;
    private boolean mEnableCarPlay;
    private boolean mEnableIndexOfSameDeviceName;

    /* JADX INFO: Access modifiers changed from: private */
    public void setDeviceListMaxSize(int size) {
        this.mDeviceListMaxSize = size;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void enableIndexOfSameDeviceName(boolean enableIndexOfSameDeviceName) {
        this.mEnableIndexOfSameDeviceName = enableIndexOfSameDeviceName;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void enableCarPlay(boolean enabled) {
        this.mEnableCarPlay = enabled;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void enableAndroidAuto(boolean enabled) {
        this.mEnableAndroidAuto = enabled;
    }

    public int getDeviceListMaxSize() {
        return this.mDeviceListMaxSize;
    }

    public boolean isEnableIndexOfSameDeviceName() {
        return this.mEnableIndexOfSameDeviceName;
    }

    public boolean isCarPlayEnable() {
        return this.mEnableCarPlay;
    }

    public boolean isAndroidAutoEnable() {
        return this.mEnableAndroidAuto;
    }

    private Config() {
        this.mEnableCarPlay = true;
        this.mEnableAndroidAuto = true;
    }

    public static class Builder {
        private int mDeviceListMaxSize = 8;
        private boolean mEnableIndexOfSameDeviceName = true;
        private boolean mEnableCarPlay = true;
        private boolean mEnableAndroidAuto = true;

        public Builder setDeviceListMaxSize(int size) {
            this.mDeviceListMaxSize = size;
            return this;
        }

        public Builder enableIndexOfSameDeviceName(boolean enableIndexOfSameDeviceName) {
            this.mEnableIndexOfSameDeviceName = enableIndexOfSameDeviceName;
            return this;
        }

        public Builder enableCarPlay(boolean enabled) {
            this.mEnableCarPlay = enabled;
            return this;
        }

        public Builder enableAndroidAuto(boolean enabled) {
            this.mEnableAndroidAuto = enabled;
            return this;
        }

        public Config build() {
            Config config = new Config();
            config.setDeviceListMaxSize(this.mDeviceListMaxSize);
            config.enableIndexOfSameDeviceName(this.mEnableIndexOfSameDeviceName);
            config.enableCarPlay(this.mEnableCarPlay);
            config.enableAndroidAuto(this.mEnableAndroidAuto);
            return config;
        }
    }

    public synchronized String toString() {
        return "Config{mDeviceListMaxSize='" + this.mDeviceListMaxSize + "', mEnableIndexOfSameDeviceName='" + this.mEnableIndexOfSameDeviceName + "', mEnableCarPlay='" + this.mEnableCarPlay + "', mEnableAndroidAuto='" + this.mEnableAndroidAuto + "'}";
    }
}
