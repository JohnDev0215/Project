package com.amazon.music.casting.session.iot;

import com.fasterxml.jackson.databind.annotation.JsonPOJOBuilder;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: IotConnectionConfig.kt */
/* JADX INFO: loaded from: classes4.dex */
@Metadata(d1 = {"\u0000\"\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0007\n\u0002\u0010\b\n\u0002\b\u0010\u0018\u0000 \u001d2\u00020\u0001:\u0002\u001c\u001dB\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004J\b\u0010\u001b\u001a\u00020\u0006H\u0016R\u0011\u0010\u0005\u001a\u00020\u0006¢\u0006\b\n\u0000\u001a\u0004\b\u0007\u0010\bR\u0011\u0010\t\u001a\u00020\u0006¢\u0006\b\n\u0000\u001a\u0004\b\n\u0010\bR\u0011\u0010\u000b\u001a\u00020\u0006¢\u0006\b\n\u0000\u001a\u0004\b\f\u0010\bR\u0011\u0010\r\u001a\u00020\u000e¢\u0006\b\n\u0000\u001a\u0004\b\u000f\u0010\u0010R\u0011\u0010\u0011\u001a\u00020\u000e¢\u0006\b\n\u0000\u001a\u0004\b\u0012\u0010\u0010R\u0011\u0010\u0013\u001a\u00020\u000e¢\u0006\b\n\u0000\u001a\u0004\b\u0014\u0010\u0010R\u0011\u0010\u0015\u001a\u00020\u000e¢\u0006\b\n\u0000\u001a\u0004\b\u0016\u0010\u0010R\u0011\u0010\u0017\u001a\u00020\u000e¢\u0006\b\n\u0000\u001a\u0004\b\u0018\u0010\u0010R\u0011\u0010\u0019\u001a\u00020\u0006¢\u0006\b\n\u0000\u001a\u0004\b\u001a\u0010\b¨\u0006\u001e"}, d2 = {"Lcom/amazon/music/casting/session/iot/IotConnectionConfig;", "", "builder", "Lcom/amazon/music/casting/session/iot/IotConnectionConfig$Builder;", "(Lcom/amazon/music/casting/session/iot/IotConnectionConfig$Builder;)V", "clientId", "", "getClientId", "()Ljava/lang/String;", "identityPoolId", "getIdentityPoolId", "iotEndpoint", "getIotEndpoint", "maxDeviceOfflineTimeoutSeconds", "", "getMaxDeviceOfflineTimeoutSeconds", "()I", "maxReconnectIntervalSeconds", "getMaxReconnectIntervalSeconds", "maxTimeoutSeconds", "getMaxTimeoutSeconds", "minReconnectIntervalSeconds", "getMinReconnectIntervalSeconds", "reconnectAttemptsThreshold", "getReconnectAttemptsThreshold", "region", "getRegion", "toString", "Builder", "Companion", "DMMCasting_release"}, k = 1, mv = {1, 6, 0}, xi = 48)
public final class IotConnectionConfig {
    private static final String TAG = IotConnectionConfig.class.getName();
    private final String clientId;
    private final String identityPoolId;
    private final String iotEndpoint;
    private final int maxDeviceOfflineTimeoutSeconds;
    private final int maxReconnectIntervalSeconds;
    private final int maxTimeoutSeconds;
    private final int minReconnectIntervalSeconds;
    private final int reconnectAttemptsThreshold;
    private final String region;

    public IotConnectionConfig(Builder builder) {
        Intrinsics.checkNotNullParameter(builder, "builder");
        this.maxTimeoutSeconds = builder.getMaxTimeoutSeconds();
        this.maxDeviceOfflineTimeoutSeconds = builder.getMaxDeviceOfflineTimeoutSeconds();
        this.minReconnectIntervalSeconds = builder.getMinReconnectIntervalSeconds();
        this.maxReconnectIntervalSeconds = builder.getMaxReconnectIntervalSeconds();
        this.reconnectAttemptsThreshold = builder.getReconnectAttemptsThreshold();
        this.iotEndpoint = builder.getIotEndpoint();
        this.clientId = builder.getClientId();
        this.identityPoolId = builder.getIdentityPoolId();
        this.region = builder.getRegion();
    }

    public final int getMaxTimeoutSeconds() {
        return this.maxTimeoutSeconds;
    }

    public final int getMaxDeviceOfflineTimeoutSeconds() {
        return this.maxDeviceOfflineTimeoutSeconds;
    }

    public final int getMinReconnectIntervalSeconds() {
        return this.minReconnectIntervalSeconds;
    }

    public final int getMaxReconnectIntervalSeconds() {
        return this.maxReconnectIntervalSeconds;
    }

    public final int getReconnectAttemptsThreshold() {
        return this.reconnectAttemptsThreshold;
    }

    public final String getIotEndpoint() {
        return this.iotEndpoint;
    }

    public final String getIdentityPoolId() {
        return this.identityPoolId;
    }

    public final String getClientId() {
        return this.clientId;
    }

    public final String getRegion() {
        return this.region;
    }

    /* JADX INFO: compiled from: IotConnectionConfig.kt */
    @Metadata(d1 = {"\u0000 \n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0002\b\u0005\n\u0002\u0010\u000e\n\u0002\b\u0010\n\u0002\u0018\u0002\n\u0000\u0018\u00002\u00020\u0001BM\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0003\u0012\u0006\u0010\u0005\u001a\u00020\u0003\u0012\u0006\u0010\u0006\u001a\u00020\u0003\u0012\u0006\u0010\u0007\u001a\u00020\u0003\u0012\u0006\u0010\b\u001a\u00020\t\u0012\u0006\u0010\n\u001a\u00020\t\u0012\u0006\u0010\u000b\u001a\u00020\t\u0012\u0006\u0010\f\u001a\u00020\t¢\u0006\u0002\u0010\rJ\u0006\u0010\u0019\u001a\u00020\u001aR\u0011\u0010\u000b\u001a\u00020\t¢\u0006\b\n\u0000\u001a\u0004\b\u000e\u0010\u000fR\u0011\u0010\n\u001a\u00020\t¢\u0006\b\n\u0000\u001a\u0004\b\u0010\u0010\u000fR\u0011\u0010\b\u001a\u00020\t¢\u0006\b\n\u0000\u001a\u0004\b\u0011\u0010\u000fR\u0011\u0010\u0004\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0012\u0010\u0013R\u0011\u0010\u0006\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0014\u0010\u0013R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0015\u0010\u0013R\u0011\u0010\u0005\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0016\u0010\u0013R\u0011\u0010\u0007\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0017\u0010\u0013R\u0011\u0010\f\u001a\u00020\t¢\u0006\b\n\u0000\u001a\u0004\b\u0018\u0010\u000f¨\u0006\u001b"}, d2 = {"Lcom/amazon/music/casting/session/iot/IotConnectionConfig$Builder;", "", "maxTimeoutSeconds", "", "maxDeviceOfflineTimeoutSeconds", "minReconnectIntervalSeconds", "maxReconnectIntervalSeconds", "reconnectAttemptsThreshold", "iotEndpoint", "", "identityPoolId", "clientId", "region", "(IIIIILjava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;)V", "getClientId", "()Ljava/lang/String;", "getIdentityPoolId", "getIotEndpoint", "getMaxDeviceOfflineTimeoutSeconds", "()I", "getMaxReconnectIntervalSeconds", "getMaxTimeoutSeconds", "getMinReconnectIntervalSeconds", "getReconnectAttemptsThreshold", "getRegion", JsonPOJOBuilder.DEFAULT_BUILD_METHOD, "Lcom/amazon/music/casting/session/iot/IotConnectionConfig;", "DMMCasting_release"}, k = 1, mv = {1, 6, 0}, xi = 48)
    public static final class Builder {
        private final String clientId;
        private final String identityPoolId;
        private final String iotEndpoint;
        private final int maxDeviceOfflineTimeoutSeconds;
        private final int maxReconnectIntervalSeconds;
        private final int maxTimeoutSeconds;
        private final int minReconnectIntervalSeconds;
        private final int reconnectAttemptsThreshold;
        private final String region;

        public Builder(int i, int i2, int i3, int i4, int i5, String iotEndpoint, String identityPoolId, String clientId, String region) {
            Intrinsics.checkNotNullParameter(iotEndpoint, "iotEndpoint");
            Intrinsics.checkNotNullParameter(identityPoolId, "identityPoolId");
            Intrinsics.checkNotNullParameter(clientId, "clientId");
            Intrinsics.checkNotNullParameter(region, "region");
            this.maxTimeoutSeconds = i;
            this.maxDeviceOfflineTimeoutSeconds = i2;
            this.minReconnectIntervalSeconds = i3;
            this.maxReconnectIntervalSeconds = i4;
            this.reconnectAttemptsThreshold = i5;
            this.iotEndpoint = iotEndpoint;
            this.identityPoolId = identityPoolId;
            this.clientId = clientId;
            this.region = region;
        }

        public final int getMaxTimeoutSeconds() {
            return this.maxTimeoutSeconds;
        }

        public final int getMaxDeviceOfflineTimeoutSeconds() {
            return this.maxDeviceOfflineTimeoutSeconds;
        }

        public final int getMinReconnectIntervalSeconds() {
            return this.minReconnectIntervalSeconds;
        }

        public final int getMaxReconnectIntervalSeconds() {
            return this.maxReconnectIntervalSeconds;
        }

        public final int getReconnectAttemptsThreshold() {
            return this.reconnectAttemptsThreshold;
        }

        public final String getIotEndpoint() {
            return this.iotEndpoint;
        }

        public final String getIdentityPoolId() {
            return this.identityPoolId;
        }

        public final String getClientId() {
            return this.clientId;
        }

        public final String getRegion() {
            return this.region;
        }

        public final IotConnectionConfig build() {
            return new IotConnectionConfig(this);
        }
    }

    public String toString() {
        return TAG + "{maxTimeoutSeconds=" + this.maxTimeoutSeconds + ", maxDeviceOfflineTimeoutSeconds=" + this.maxDeviceOfflineTimeoutSeconds + ", minReconnectIntervalSeconds=" + this.minReconnectIntervalSeconds + ", maxReconnectIntervalSeconds=" + this.maxReconnectIntervalSeconds + ", reconnectAttemptsThreshold=" + this.reconnectAttemptsThreshold + '}';
    }
}
