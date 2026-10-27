package com.amazon.mp3.auto.waze;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import com.amazon.identity.auth.device.api.MAPAccountManager;
import com.amazon.mp3.service.ServiceUtil;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: WazeWakeUpReceiver.kt */
/* JADX INFO: loaded from: classes3.dex */
@Metadata(d1 = {"\u0000\u001e\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\u0018\u00002\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0002J\u0018\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\bH\u0016¨\u0006\t"}, d2 = {"Lcom/amazon/mp3/auto/waze/WazeWakeUpReceiver;", "Landroid/content/BroadcastReceiver;", "()V", "onReceive", "", "context", "Landroid/content/Context;", MAPAccountManager.KEY_INTENT, "Landroid/content/Intent;", "DigitalMusicAndroid3P_automotiveProdRelease"}, k = 1, mv = {1, 6, 0}, xi = 48)
public final class WazeWakeUpReceiver extends BroadcastReceiver {
    @Override // android.content.BroadcastReceiver
    public void onReceive(Context context, Intent intent) {
        Intrinsics.checkNotNullParameter(context, "context");
        Intrinsics.checkNotNullParameter(intent, "intent");
        if (Intrinsics.areEqual("com.waze.sdk.audio.ACTION_INIT", intent.getAction())) {
            intent.setClass(context, WazePlaybackService.class);
            ServiceUtil.startService(context, intent);
        }
    }
}
