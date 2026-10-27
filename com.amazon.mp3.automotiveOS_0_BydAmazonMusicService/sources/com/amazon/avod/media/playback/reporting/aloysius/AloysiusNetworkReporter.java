package com.amazon.avod.media.playback.reporting.aloysius;

import androidx.annotation.VisibleForTesting;
import com.amazon.avod.connectivity.ConnectionChangeListener;
import com.amazon.avod.connectivity.DetailedNetworkInfo;
import com.amazon.avod.connectivity.NetworkConnectionManager;
import com.amazon.avod.connectivity.NetworkType;
import com.amazon.avod.media.events.AloysiusConfig;
import com.amazon.avod.media.events.MediaEventProducer;
import com.amazon.avod.media.events.MediaEventQueue;
import com.amazon.avod.media.events.model.MediaEvent;
import com.google.common.base.Preconditions;
import javax.annotation.Nonnull;

/* JADX INFO: loaded from: classes2.dex */
public class AloysiusNetworkReporter extends ConnectionChangeListener implements MediaEventProducer {
    private boolean mIsOldNetworkMediaEventSerializationEnabled;
    private final MediaEventQueue mMediaEventQueue;
    private final NetworkConnectionManager mNetworkConnectionManager;

    public AloysiusNetworkReporter(@Nonnull MediaEventQueue mediaEventQueue, @Nonnull NetworkConnectionManager networkConnectionManager) {
        this.mMediaEventQueue = (MediaEventQueue) Preconditions.checkNotNull(mediaEventQueue, "mediaEventQueue");
        this.mNetworkConnectionManager = (NetworkConnectionManager) Preconditions.checkNotNull(networkConnectionManager, "networkConnectionManager");
    }

    @Override // com.amazon.avod.media.events.MediaEventReporter
    public void initialize() {
        this.mNetworkConnectionManager.registerListener((ConnectionChangeListener) this);
        this.mIsOldNetworkMediaEventSerializationEnabled = AloysiusConfig.getInstance().isOldNetworkMediaEventSerializationEnabled();
    }

    @Override // com.amazon.avod.media.events.MediaEventReporter
    public void terminate() {
        this.mNetworkConnectionManager.unregisterListener(this);
    }

    @Override // com.amazon.avod.media.events.MediaEventProducer
    public void produce() {
        addEventToQueue(this.mNetworkConnectionManager.hasDataConnection(), this.mNetworkConnectionManager.getNetworkInfo().getNetworkType());
    }

    @Override // com.amazon.avod.connectivity.ConnectivityChangeListener
    public void onConnectionChange(DetailedNetworkInfo detailedNetworkInfo, DetailedNetworkInfo detailedNetworkInfo2) {
        addEventToQueue(!detailedNetworkInfo2.hasNoNetworkAccess(), detailedNetworkInfo2.getNetworkType());
    }

    private void addEventToQueue(boolean z, @Nonnull NetworkType networkType) {
        this.mMediaEventQueue.add(createMediaEvent(z, (NetworkType) Preconditions.checkNotNull(networkType, "networkType")));
    }

    private MediaEvent createMediaEvent(boolean z, @Nonnull NetworkType networkType) {
        NetworkMediaEvent.NetworkCategory.Type type;
        Preconditions.checkNotNull(networkType, "networkType");
        if (networkType.isEthernetType()) {
            type = NetworkMediaEvent.NetworkCategory.Type.Ethernet;
        } else if (networkType.isWifiType()) {
            type = NetworkMediaEvent.NetworkCategory.Type.WiFi;
        } else if (networkType.isWanType()) {
            type = NetworkMediaEvent.NetworkCategory.Type.Cellular;
        } else {
            type = NetworkMediaEvent.NetworkCategory.Type.Unknown;
        }
        return new NetworkMediaEvent.Builder().connected(z).networkCategory(new NetworkMediaEvent.NetworkCategory(type)).active(true).build(this.mIsOldNetworkMediaEventSerializationEnabled);
    }

    @VisibleForTesting
    static class NetworkMediaEventV2 extends NetworkMediaEvent {
        private NetworkMediaEventV2(@Nonnull NetworkMediaEvent.Builder builder) {
            super(builder);
        }

        public NetworkMediaEvent.NetworkCategory getCategory() {
            return this.mNetworkCategory;
        }

        public boolean isActive() {
            return this.mActive;
        }
    }

    @VisibleForTesting
    static class NetworkMediaEvent implements MediaEvent {
        final boolean mActive;
        final boolean mConnected;
        final NetworkCategory mNetworkCategory;

        private NetworkMediaEvent(@Nonnull Builder builder) {
            this.mConnected = ((Builder) Preconditions.checkNotNull(builder, "builder")).mConnected;
            this.mNetworkCategory = builder.mNetworkCategory;
            this.mActive = builder.mActive;
        }

        public boolean isConnected() {
            return this.mConnected;
        }

        @Override // com.amazon.avod.media.events.model.MediaEvent
        public MediaEvent.MediaEventType getMediaEventType() {
            return MediaEvent.MediaEventType.Network;
        }

        @VisibleForTesting
        static class NetworkCategory {
            private final Type mType;

            enum Type {
                WiFi,
                Cellular,
                Ethernet,
                Unknown
            }

            @Nonnull
            public Type getType() {
                return this.mType;
            }

            @VisibleForTesting
            NetworkCategory(@Nonnull Type type) {
                this.mType = (Type) Preconditions.checkNotNull(type);
            }
        }

        @VisibleForTesting
        static class Builder {
            private boolean mActive;
            private boolean mConnected;
            private NetworkCategory mNetworkCategory;

            Builder() {
            }

            public Builder connected(boolean z) {
                this.mConnected = z;
                return this;
            }

            public Builder networkCategory(@Nonnull NetworkCategory networkCategory) {
                this.mNetworkCategory = (NetworkCategory) Preconditions.checkNotNull(networkCategory, "networkCategory");
                return this;
            }

            public Builder active(boolean z) {
                this.mActive = z;
                return this;
            }

            public MediaEvent build(boolean z) {
                if (z) {
                    return new NetworkMediaEvent(this);
                }
                return new NetworkMediaEventV2(this);
            }
        }
    }
}
