package com.amazon.avod.playback.sye.listeners;

import com.amazon.avod.json.JacksonCache;
import com.amazon.avod.media.playback.internal.PlaybackListenerProxy;
import com.amazon.avod.playback.sye.listeners.SyeMessage;
import com.amazon.avod.util.DLog;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.common.base.Optional;
import com.google.common.base.Preconditions;
import com.netinsight.sye.syeClient.notification.ISyeNotificationMessage;
import com.netinsight.sye.syeClient.playerListeners.INotificationListener;
import java.io.IOException;
import javax.annotation.Nonnull;

/* JADX INFO: loaded from: classes2.dex */
public class NotificationHandler implements INotificationListener {
    private final PlaybackListenerProxy mListenerProxy;
    private final ObjectMapper mObjectMapper;
    private final String mVcid;

    public NotificationHandler(@Nonnull PlaybackListenerProxy playbackListenerProxy, @Nonnull String str) {
        this(JacksonCache.OBJECT_MAPPER, playbackListenerProxy, str);
    }

    NotificationHandler(@Nonnull ObjectMapper objectMapper, @Nonnull PlaybackListenerProxy playbackListenerProxy, @Nonnull String str) {
        this.mObjectMapper = (ObjectMapper) Preconditions.checkNotNull(objectMapper, "objectMapper");
        this.mListenerProxy = (PlaybackListenerProxy) Preconditions.checkNotNull(playbackListenerProxy, "listenerProxy");
        this.mVcid = (String) Preconditions.checkNotNull(str, "vcid");
    }

    @Override // com.netinsight.sye.syeClient.playerListeners.INotificationListener
    public void onNotificationMessage(@Nonnull ISyeNotificationMessage iSyeNotificationMessage) {
        Preconditions.checkNotNull(iSyeNotificationMessage, "notificationMessage");
        String data = iSyeNotificationMessage.getData();
        try {
            SyeMessage syeMessage = (SyeMessage) this.mObjectMapper.readValue(data, SyeMessage.class);
            SyeMessage.Signal signal = syeMessage.getSignal();
            Optional<SyeMessage.Filter> filterOptional = syeMessage.getFilterOptional();
            if (filterOptional.isPresent()) {
                SyeMessage.Filter filter = filterOptional.get();
                if (filter.getVcidOptional().isPresent() && !filter.getVcidOptional().get().equals(this.mVcid)) {
                    return;
                }
            }
            if (AnonymousClass1.$SwitchMap$com$amazon$avod$playback$sye$listeners$SyeMessage$Signal[signal.ordinal()] != 1) {
                DLog.warnf("This command is new and not handled %s", new String(data));
            } else {
                this.mListenerProxy.onCompletion();
            }
        } catch (IOException e) {
            DLog.warnf("Failed to handle notification: %s with error %s", data, e.getMessage());
        }
    }

    /* JADX INFO: renamed from: com.amazon.avod.playback.sye.listeners.NotificationHandler$1, reason: invalid class name */
    static /* synthetic */ class AnonymousClass1 {
        static final /* synthetic */ int[] $SwitchMap$com$amazon$avod$playback$sye$listeners$SyeMessage$Signal;

        static {
            int[] iArr = new int[SyeMessage.Signal.values().length];
            $SwitchMap$com$amazon$avod$playback$sye$listeners$SyeMessage$Signal = iArr;
            try {
                iArr[SyeMessage.Signal.CLOSE_PLAYBACK.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                $SwitchMap$com$amazon$avod$playback$sye$listeners$SyeMessage$Signal[SyeMessage.Signal.UNKNOWN.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
        }
    }
}
