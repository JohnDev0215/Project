package com.amazon.mp3.playback.harley;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.IntentFilter;
import com.amazon.identity.auth.device.api.MAPAccountManager;
import com.amazon.mp3.AmazonApplication;
import com.amazon.mp3.playback.activity.NowPlayingUtil;
import com.amazon.mp3.playback.video.VideoUtil;
import com.amazon.music.media.playback.ControlSource;
import com.amazon.music.media.playback.PlayStatus;
import com.amazon.music.media.playback.Playback;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: AudioVideoPlaybackNotificationReceiver.kt */
/* JADX INFO: loaded from: classes3.dex */
@Metadata(d1 = {"\u0000*\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0002\u0018\u00002\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0002J\u000e\u0010\u0006\u001a\u00020\u00072\u0006\u0010\b\u001a\u00020\tJ\u0016\u0010\n\u001a\u00020\u00072\u0006\u0010\b\u001a\u00020\t2\u0006\u0010\u000b\u001a\u00020\u0004J\u0018\u0010\f\u001a\u00020\u00072\u0006\u0010\b\u001a\u00020\t2\u0006\u0010\r\u001a\u00020\u000eH\u0016J\u000e\u0010\u000f\u001a\u00020\u00072\u0006\u0010\b\u001a\u00020\tR\u0016\u0010\u0003\u001a\n \u0005*\u0004\u0018\u00010\u00040\u0004X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u0010"}, d2 = {"Lcom/amazon/mp3/playback/harley/AudioVideoPlaybackNotificationReceiver;", "Landroid/content/BroadcastReceiver;", "()V", "TAG", "", "kotlin.jvm.PlatformType", "addReceiver", "", "context", "Landroid/content/Context;", "checkForVideoAndLaunch", "action", "onReceive", MAPAccountManager.KEY_INTENT, "Landroid/content/Intent;", "removeReceiver", "DigitalMusicAndroid3P_automotiveProdRelease"}, k = 1, mv = {1, 6, 0}, xi = 48)
public final class AudioVideoPlaybackNotificationReceiver extends BroadcastReceiver {
    private final String TAG = AudioVideoPlaybackNotificationReceiver.class.getSimpleName();

    public final void addReceiver(Context context) {
        Intrinsics.checkNotNullParameter(context, "context");
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction("com.amazon.music.playbacknotification.next");
        intentFilter.addAction("com.amazon.music.playbacknotification.pause");
        intentFilter.addAction("com.amazon.music.playbacknotification.play");
        intentFilter.addAction("com.amazon.music.playbacknotification.prev");
        context.getApplicationContext().registerReceiver(this, intentFilter);
    }

    /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
    /* JADX WARN: Code restructure failed: missing block: B:11:0x0027, code lost:
    
        if (r3.equals("com.amazon.music.playbacknotification.play") == false) goto L20;
     */
    /* JADX WARN: Code restructure failed: missing block: B:14:0x0030, code lost:
    
        if (r3.equals("com.amazon.music.playbacknotification.next") == false) goto L20;
     */
    /* JADX WARN: Code restructure failed: missing block: B:17:0x0039, code lost:
    
        if (r3.equals("com.amazon.music.playbacknotification.pause") == false) goto L20;
     */
    /* JADX WARN: Code restructure failed: missing block: B:8:0x001e, code lost:
    
        if (r3.equals("com.amazon.music.playbacknotification.prev") == false) goto L20;
     */
    /* JADX WARN: Failed to restore switch over string. Please report as a decompilation issue */
    @Override // android.content.BroadcastReceiver
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public void onReceive(android.content.Context r2, android.content.Intent r3) {
        /*
            r1 = this;
            java.lang.String r0 = "context"
            kotlin.jvm.internal.Intrinsics.checkNotNullParameter(r2, r0)
            java.lang.String r0 = "intent"
            kotlin.jvm.internal.Intrinsics.checkNotNullParameter(r3, r0)
            java.lang.String r3 = r3.getAction()
            if (r3 == 0) goto L40
            int r0 = r3.hashCode()
            switch(r0) {
                case -886369996: goto L33;
                case 802635765: goto L2a;
                case 802701366: goto L21;
                case 802707253: goto L18;
                default: goto L17;
            }
        L17:
            goto L40
        L18:
            java.lang.String r0 = "com.amazon.music.playbacknotification.prev"
            boolean r0 = r3.equals(r0)
            if (r0 != 0) goto L3c
            goto L40
        L21:
            java.lang.String r0 = "com.amazon.music.playbacknotification.play"
            boolean r0 = r3.equals(r0)
            if (r0 != 0) goto L3c
            goto L40
        L2a:
            java.lang.String r0 = "com.amazon.music.playbacknotification.next"
            boolean r0 = r3.equals(r0)
            if (r0 != 0) goto L3c
            goto L40
        L33:
            java.lang.String r0 = "com.amazon.music.playbacknotification.pause"
            boolean r0 = r3.equals(r0)
            if (r0 != 0) goto L3c
            goto L40
        L3c:
            r1.checkForVideoAndLaunch(r2, r3)
            goto L4b
        L40:
            java.lang.String r2 = r1.TAG
            java.lang.String r0 = "Unrecognized action received from notification playback --> "
            java.lang.String r3 = kotlin.jvm.internal.Intrinsics.stringPlus(r0, r3)
            com.amazon.mp3.util.Log.debug(r2, r3)
        L4b:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.amazon.mp3.playback.harley.AudioVideoPlaybackNotificationReceiver.onReceive(android.content.Context, android.content.Intent):void");
    }

    public final void checkForVideoAndLaunch(Context context, String action) {
        Intrinsics.checkNotNullParameter(context, "context");
        Intrinsics.checkNotNullParameter(action, "action");
        PlayStatus playStatus = Playback.getInstance().getPlaybackController(ControlSource.APP_UI).getPlayStatus();
        if (NowPlayingUtil.isVideoPlaying() && playStatus != null && !playStatus.willPlay() && AmazonApplication.isApplicationForeground() && Intrinsics.areEqual(action, "com.amazon.music.playbacknotification.play")) {
            VideoUtil.INSTANCE.launchNowPlayingActivity(context);
        }
    }

    public final void removeReceiver(Context context) {
        Intrinsics.checkNotNullParameter(context, "context");
        context.unregisterReceiver(this);
    }
}
