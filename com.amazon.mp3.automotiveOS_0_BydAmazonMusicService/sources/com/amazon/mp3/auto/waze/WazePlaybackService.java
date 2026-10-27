package com.amazon.mp3.auto.waze;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import com.amazon.identity.auth.device.api.MAPAccountManager;
import com.amazon.mp3.util.Log;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: WazePlaybackService.kt */
/* JADX INFO: loaded from: classes3.dex */
@Metadata(d1 = {"\u0000 \n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0004\u0018\u0000 \u000b2\u00020\u0001:\u0001\u000bB\u0005¢\u0006\u0002\u0010\u0002J\u0012\u0010\u0003\u001a\u0004\u0018\u00010\u00042\u0006\u0010\u0005\u001a\u00020\u0006H\u0016J \u0010\u0007\u001a\u00020\b2\u0006\u0010\u0005\u001a\u00020\u00062\u0006\u0010\t\u001a\u00020\b2\u0006\u0010\n\u001a\u00020\bH\u0016¨\u0006\f"}, d2 = {"Lcom/amazon/mp3/auto/waze/WazePlaybackService;", "Landroid/app/Service;", "()V", "onBind", "Landroid/os/IBinder;", MAPAccountManager.KEY_INTENT, "Landroid/content/Intent;", "onStartCommand", "", "flags", "startId", "Companion", "DigitalMusicAndroid3P_automotiveProdRelease"}, k = 1, mv = {1, 6, 0}, xi = 48)
public final class WazePlaybackService extends Service {
    private static final String TAG = WazePlaybackService.class.getSimpleName();

    @Override // android.app.Service
    public IBinder onBind(Intent intent) {
        Intrinsics.checkNotNullParameter(intent, "intent");
        return null;
    }

    @Override // android.app.Service
    public int onStartCommand(Intent intent, int flags, int startId) {
        Intrinsics.checkNotNullParameter(intent, "intent");
        try {
            super.onStartCommand(intent, flags, startId);
            WazeSdkConnection.INSTANCE.connectToWazeIfNeeded(this, false);
            return 3;
        } catch (NullPointerException e) {
            Log.error(TAG, "NPE occurred: waze playback service cannot be started", e);
            return 3;
        }
    }
}
