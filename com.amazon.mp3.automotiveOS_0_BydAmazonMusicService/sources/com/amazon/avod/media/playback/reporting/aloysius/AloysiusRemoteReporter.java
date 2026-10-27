package com.amazon.avod.media.playback.reporting.aloysius;

import androidx.annotation.VisibleForTesting;
import androidx.core.app.NotificationCompat;
import com.amazon.avod.media.events.MediaEventQueue;
import com.amazon.avod.media.events.MediaEventReporter;
import com.amazon.avod.media.events.model.MediaEvent;
import com.amazon.avod.media.framework.MediaSystemSharedDependencies;
import com.amazon.avod.playback.capability.DeviceIdentity;
import com.google.common.base.Preconditions;
import javax.annotation.Nonnull;
import javax.annotation.Nullable;

/* JADX INFO: loaded from: classes2.dex */
public class AloysiusRemoteReporter implements MediaEventReporter {
    private final String mDeviceId;
    private final MediaEventQueue mMediaEventQueue;

    public enum Action {
        StartDiscovery,
        Discovered,
        EndDiscovery,
        Connect,
        Disconnect
    }

    public enum Transport {
        Local,
        Cloud
    }

    @Override // com.amazon.avod.media.events.MediaEventReporter
    public void initialize() {
    }

    @Override // com.amazon.avod.media.events.MediaEventReporter
    public void terminate() {
    }

    public AloysiusRemoteReporter(@Nonnull MediaEventQueue mediaEventQueue) {
        this(mediaEventQueue, MediaSystemSharedDependencies.getInstance().getDeviceIdentity());
    }

    @VisibleForTesting
    public AloysiusRemoteReporter(@Nonnull MediaEventQueue mediaEventQueue, @Nonnull DeviceIdentity deviceIdentity) {
        this.mMediaEventQueue = (MediaEventQueue) Preconditions.checkNotNull(mediaEventQueue, "mediaEventQueue");
        Preconditions.checkNotNull(deviceIdentity, "deviceProperties");
        this.mDeviceId = deviceIdentity.getDeviceId();
    }

    public void reportRemoteEvent(@Nonnull Action action, @Nonnull Transport transport, @Nullable String str) {
        this.mMediaEventQueue.add(new RemoteMediaEvent(action, transport, str));
    }

    static class RemoteMediaEvent implements MediaEvent {
        private Action mAction;
        private String mRemoteDeviceId;
        private Transport mTransport;

        public RemoteMediaEvent(@Nonnull Action action, @Nonnull Transport transport, @Nullable String str) {
            this.mAction = (Action) Preconditions.checkNotNull(action, "action");
            this.mTransport = (Transport) Preconditions.checkNotNull(transport, NotificationCompat.CATEGORY_TRANSPORT);
            this.mRemoteDeviceId = str;
        }

        @Nonnull
        public Action getAction() {
            return this.mAction;
        }

        @Nonnull
        public Transport getTransport() {
            return this.mTransport;
        }

        @Nullable
        public String getRemoteDeviceId() {
            return this.mRemoteDeviceId;
        }

        @Override // com.amazon.avod.media.events.model.MediaEvent
        @Nonnull
        public MediaEvent.MediaEventType getMediaEventType() {
            return MediaEvent.MediaEventType.Remote;
        }
    }
}
