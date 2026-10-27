package com.amazon.mp3.watch;

import android.content.Intent;
import com.amazon.mp3.service.ServiceUtil;
import com.amazon.music.skyfire.models.TetheredMessageKey;
import com.google.android.gms.wearable.MessageEvent;
import com.google.android.gms.wearable.WearableListenerService;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.Charsets;

/* JADX INFO: compiled from: TetheredMessageService.kt */
/* JADX INFO: loaded from: classes4.dex */
@Metadata(d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\u0018\u00002\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0002J\u0010\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u0006H\u0016¨\u0006\u0007"}, d2 = {"Lcom/amazon/mp3/watch/TetheredMessageService;", "Lcom/google/android/gms/wearable/WearableListenerService;", "()V", "onMessageReceived", "", "messageEvent", "Lcom/google/android/gms/wearable/MessageEvent;", "DigitalMusicAndroid3P_automotiveProdRelease"}, k = 1, mv = {1, 6, 0}, xi = 48)
public final class TetheredMessageService extends WearableListenerService {
    @Override // com.google.android.gms.wearable.WearableListenerService, com.google.android.gms.wearable.MessageApi.MessageListener
    public void onMessageReceived(MessageEvent messageEvent) {
        Intrinsics.checkNotNullParameter(messageEvent, "messageEvent");
        if (Intrinsics.areEqual(messageEvent.getPath(), TetheredMessageKey.tetheredMessagePath)) {
            Intent intent = new Intent(getApplicationContext(), (Class<?>) WatchSessionService.class);
            byte[] data = messageEvent.getData();
            Intrinsics.checkNotNullExpressionValue(data, "messageEvent.data");
            intent.putExtra("message", new String(data, Charsets.UTF_8));
            intent.putExtra(TetheredMessageKey.nodeId, messageEvent.getSourceNodeId());
            intent.putExtra(TetheredMessageKey.requestId, messageEvent.getRequestId());
            ServiceUtil.startService(getApplicationContext(), intent);
            return;
        }
        super.onMessageReceived(messageEvent);
    }
}
