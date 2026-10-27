package com.amazon.avod.playback.sye;

import android.content.Intent;
import androidx.annotation.NonNull;
import com.amazon.avod.playback.sye.listeners.NotificationHandler;
import com.amazon.avod.playback.sye.listeners.SyeErrorRetryHandler;
import com.amazon.avod.playback.sye.listeners.SyeEventForwarder;
import com.amazon.avod.qahooks.QATestFeature;
import com.amazon.avod.util.DLog;
import com.netinsight.sye.syeClient.generated.enums.SyePlayerError;
import com.netinsight.sye.syeClient.generated.enums.SyePlayerState;
import com.netinsight.sye.syeClient.notification.ISyeNotificationMessage;
import java.util.Locale;

/* JADX INFO: loaded from: classes2.dex */
public class QATriggerSyePlayer implements QATestFeature {
    private NotificationHandler mNotificationHandler;
    private SyeErrorRetryHandler mSyeErrorRetryHandler;
    private SyeEventForwarder mSyeEventForwarder;

    private static class SingletonHolder {
        private static final QATriggerSyePlayer INSTANCE = new QATriggerSyePlayer();

        private SingletonHolder() {
        }
    }

    public static QATriggerSyePlayer getInstance() {
        return SingletonHolder.INSTANCE;
    }

    private QATriggerSyePlayer() {
    }

    public void prepare(SyeEventForwarder syeEventForwarder, SyeErrorRetryHandler syeErrorRetryHandler, NotificationHandler notificationHandler) {
        this.mSyeEventForwarder = syeEventForwarder;
        this.mSyeErrorRetryHandler = syeErrorRetryHandler;
        this.mNotificationHandler = notificationHandler;
    }

    @Override // com.amazon.avod.qahooks.QATestFeature
    public void handleIntent(Intent intent) {
        String stringExtra = intent.getStringExtra("action");
        DLog.logf("Received %s intent", stringExtra);
        if (stringExtra != null) {
            String lowerCase = stringExtra.toLowerCase(Locale.US);
            lowerCase.hashCode();
            switch (lowerCase) {
                case "induce_quit":
                    if (this.mNotificationHandler != null) {
                        this.mNotificationHandler.onNotificationMessage(new ISyeNotificationMessage() { // from class: com.amazon.avod.playback.sye.QATriggerSyePlayer.1
                            @Override // com.netinsight.sye.syeClient.notification.ISyeNotificationMessage
                            @NonNull
                            public String getData() {
                                return "{\"signal\":\"quit\"}";
                            }

                            @Override // com.netinsight.sye.syeClient.notification.ISyeNotificationMessage
                            @NonNull
                            public String getMessageType() {
                                return "";
                            }

                            @Override // com.netinsight.sye.syeClient.notification.ISyeNotificationMessage
                            public long getPresentationTimeMicros() {
                                return 0L;
                            }
                        });
                        break;
                    }
                    break;
                case "induce_mid_stream_failover":
                    this.mSyeErrorRetryHandler.onErrorRetry(SyePlayerError.CommunicationTimeout, "test failed", 50000);
                    break;
                case "enable_buffer":
                    SyeEventForwarder syeEventForwarder = this.mSyeEventForwarder;
                    if (syeEventForwarder != null) {
                        syeEventForwarder.onStateChange(SyePlayerState.Playing, SyePlayerState.Stalled);
                        break;
                    }
                    break;
                case "disable_buffer":
                    SyeEventForwarder syeEventForwarder2 = this.mSyeEventForwarder;
                    if (syeEventForwarder2 != null) {
                        syeEventForwarder2.onStateChange(SyePlayerState.Stalled, SyePlayerState.Playing);
                        break;
                    }
                    break;
                default:
                    DLog.errorf("Unsupported intent action: %s", stringExtra);
                    break;
            }
        }
    }
}
