package com.amazon.digitalmusicplayback;

/* JADX INFO: loaded from: classes2.dex */
public final class PlayerConfig {
    final PlayerAuthStrategy authStrategy;
    final String configPath;
    final String dataStoragePath;
    final boolean disableStreamingConcurrencyCheck;
    final DMLSConfig dmlsConfig;
    final String drmStoragePath;
    final QualitySetting otaStreamingQuality;
    final String tempStoragePath;
    final boolean threeDMode;
    final QualitySetting wifiStreamingQuality;

    public PlayerConfig(String str, DMLSConfig dMLSConfig, String str2, String str3, boolean z, String str4, PlayerAuthStrategy playerAuthStrategy, QualitySetting qualitySetting, QualitySetting qualitySetting2, boolean z2) {
        this.configPath = str;
        this.dmlsConfig = dMLSConfig;
        this.tempStoragePath = str2;
        this.drmStoragePath = str3;
        this.disableStreamingConcurrencyCheck = z;
        this.dataStoragePath = str4;
        this.authStrategy = playerAuthStrategy;
        this.wifiStreamingQuality = qualitySetting;
        this.otaStreamingQuality = qualitySetting2;
        this.threeDMode = z2;
    }

    public String getConfigPath() {
        return this.configPath;
    }

    public DMLSConfig getDmlsConfig() {
        return this.dmlsConfig;
    }

    public String getTempStoragePath() {
        return this.tempStoragePath;
    }

    public String getDrmStoragePath() {
        return this.drmStoragePath;
    }

    public boolean getDisableStreamingConcurrencyCheck() {
        return this.disableStreamingConcurrencyCheck;
    }

    public String getDataStoragePath() {
        return this.dataStoragePath;
    }

    public PlayerAuthStrategy getAuthStrategy() {
        return this.authStrategy;
    }

    public QualitySetting getWifiStreamingQuality() {
        return this.wifiStreamingQuality;
    }

    public QualitySetting getOtaStreamingQuality() {
        return this.otaStreamingQuality;
    }

    public boolean getThreeDMode() {
        return this.threeDMode;
    }

    public boolean equals(Object obj) {
        if (!(obj instanceof PlayerConfig)) {
            return false;
        }
        PlayerConfig playerConfig = (PlayerConfig) obj;
        if (!this.configPath.equals(playerConfig.configPath) || !this.dmlsConfig.equals(playerConfig.dmlsConfig) || !this.tempStoragePath.equals(playerConfig.tempStoragePath)) {
            return false;
        }
        String str = this.drmStoragePath;
        return ((str == null && playerConfig.drmStoragePath == null) || (str != null && str.equals(playerConfig.drmStoragePath))) && this.disableStreamingConcurrencyCheck == playerConfig.disableStreamingConcurrencyCheck && this.dataStoragePath.equals(playerConfig.dataStoragePath) && this.authStrategy == playerConfig.authStrategy && this.wifiStreamingQuality == playerConfig.wifiStreamingQuality && this.otaStreamingQuality == playerConfig.otaStreamingQuality && this.threeDMode == playerConfig.threeDMode;
    }

    public int hashCode() {
        int iHashCode = (((((527 + this.configPath.hashCode()) * 31) + this.dmlsConfig.hashCode()) * 31) + this.tempStoragePath.hashCode()) * 31;
        String str = this.drmStoragePath;
        return ((((((((((((iHashCode + (str == null ? 0 : str.hashCode())) * 31) + (this.disableStreamingConcurrencyCheck ? 1 : 0)) * 31) + this.dataStoragePath.hashCode()) * 31) + this.authStrategy.hashCode()) * 31) + this.wifiStreamingQuality.hashCode()) * 31) + this.otaStreamingQuality.hashCode()) * 31) + (this.threeDMode ? 1 : 0);
    }

    public String toString() {
        return "PlayerConfig{configPath=" + this.configPath + ",dmlsConfig=" + this.dmlsConfig + ",tempStoragePath=" + this.tempStoragePath + ",drmStoragePath=" + this.drmStoragePath + ",disableStreamingConcurrencyCheck=" + this.disableStreamingConcurrencyCheck + ",dataStoragePath=" + this.dataStoragePath + ",authStrategy=" + this.authStrategy + ",wifiStreamingQuality=" + this.wifiStreamingQuality + ",otaStreamingQuality=" + this.otaStreamingQuality + ",threeDMode=" + this.threeDMode + "}";
    }
}
