package com.amazon.music.casting.session;

import com.amazon.music.casting.session.things.ClientThingShadow;

/* JADX INFO: loaded from: classes4.dex */
public class CastingSessionConfig {
    private final ClientThingShadow.State.ReportedNode.AllowedParentalControls allowedParentalControls;
    private final int maxDeviceOfflineTimeoutSeconds;
    private final int maxReconnectIntervalSeconds;
    private final int maxTimeoutSeconds;
    private final int minReconnectIntervalSeconds;
    private final int reconnectAttemptsThreshold;

    public int getMaxTimeoutSeconds() {
        return this.maxTimeoutSeconds;
    }

    public int getMaxDeviceOfflineTimeoutSeconds() {
        return this.maxDeviceOfflineTimeoutSeconds;
    }

    public int getMinReconnectIntervalSeconds() {
        return this.minReconnectIntervalSeconds;
    }

    public int getMaxReconnectIntervalSeconds() {
        return this.maxReconnectIntervalSeconds;
    }

    public int getReconnectAttemptsThreshold() {
        return this.reconnectAttemptsThreshold;
    }

    public ClientThingShadow.State.ReportedNode.AllowedParentalControls getAllowedParentalControls() {
        return this.allowedParentalControls;
    }

    private CastingSessionConfig(int i, int i2, int i3, int i4, int i5, ClientThingShadow.State.ReportedNode.AllowedParentalControls allowedParentalControls) {
        this.maxTimeoutSeconds = i;
        this.maxDeviceOfflineTimeoutSeconds = i2;
        this.minReconnectIntervalSeconds = i3;
        this.maxReconnectIntervalSeconds = i4;
        this.reconnectAttemptsThreshold = i5;
        this.allowedParentalControls = allowedParentalControls;
    }

    public static class Builder {
        private final ClientThingShadow.State.ReportedNode.AllowedParentalControls allowedParentalControls;
        private final int maxDeviceOfflineTimeoutSeconds;
        private final int maxReconnectIntervalSeconds;
        private final int maxTimeoutSeconds;
        private final int minReconnectIntervalSeconds;
        private final int reconnectAttemptsThreshold;

        public Builder(int i, int i2, int i3, int i4, int i5, ClientThingShadow.State.ReportedNode.AllowedParentalControls allowedParentalControls) {
            this.maxTimeoutSeconds = i;
            this.maxDeviceOfflineTimeoutSeconds = i2;
            this.minReconnectIntervalSeconds = i3;
            this.maxReconnectIntervalSeconds = i4;
            this.reconnectAttemptsThreshold = i5;
            this.allowedParentalControls = allowedParentalControls;
        }

        public CastingSessionConfig build() {
            return new CastingSessionConfig(this.maxTimeoutSeconds, this.maxDeviceOfflineTimeoutSeconds, this.minReconnectIntervalSeconds, this.maxReconnectIntervalSeconds, this.reconnectAttemptsThreshold, this.allowedParentalControls);
        }
    }

    public String toString() {
        return "CastingSessionConfig{maxTimeoutSeconds=" + this.maxTimeoutSeconds + ", maxDeviceOfflineTimeoutSeconds=" + this.maxDeviceOfflineTimeoutSeconds + ", minReconnectIntervalSeconds=" + this.minReconnectIntervalSeconds + ", maxReconnectIntervalSeconds=" + this.maxReconnectIntervalSeconds + ", reconnectAttemptsThreshold=" + this.reconnectAttemptsThreshold + ", allowedParentalControls=" + this.allowedParentalControls + '}';
    }
}
