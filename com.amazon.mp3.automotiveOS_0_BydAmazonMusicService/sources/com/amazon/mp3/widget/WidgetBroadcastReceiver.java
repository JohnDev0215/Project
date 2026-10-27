package com.amazon.mp3.widget;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import com.amazon.mp3.account.credentials.AccountCredentialUtil;
import com.amazon.mp3.client.activity.LauncherActivity;
import com.amazon.mp3.library.activity.LibraryActivityFactory;
import com.amazon.mp3.library.provider.MediaProvider;
import com.amazon.mp3.playback.playbackcontrol.ActionValidatedPlaybackController;
import com.amazon.mp3.playback.playbackcontrol.PlaybackControllerProvider;
import com.amazon.mp3.widget.WidgetMetrics;
import com.amazon.music.media.playback.ControlSource;

/* JADX INFO: loaded from: classes4.dex */
public class WidgetBroadcastReceiver extends BroadcastReceiver {
    public static final String TAG = WidgetBroadcastReceiver.class.getSimpleName();

    @Override // android.content.BroadcastReceiver
    public void onReceive(Context context, Intent intent) {
        if (AccountCredentialUtil.get(context).isSignedOut()) {
            startLauncherActivity(context);
            return;
        }
        WidgetMetrics widgetMetrics = new WidgetMetrics(context.getApplicationContext());
        ActionValidatedPlaybackController controller = PlaybackControllerProvider.getController(ControlSource.WIDGET);
        String action = intent.getAction();
        if (isPlaybackAction(action)) {
            action.hashCode();
            switch (action) {
                case "com.amazon.mp3.widget.next":
                    controller.skipNext();
                    break;
                case "com.amazon.mp3.widget.play":
                    controller.play();
                    break;
                case "com.amazon.mp3.widget.prev":
                    controller.previousOrRestart();
                    break;
                case "com.amazon.mp3.widget.pause":
                    controller.pause();
                    break;
            }
            widgetMetrics.sendWidgetMetricEvent(WidgetMetrics.WidgetClickEvent.PLAYER);
            return;
        }
        if ("open now playing".equals(action)) {
            if (controller.getCurrentMediaItem() == null) {
                startLauncherActivity(context);
            } else {
                startNowPlayingActivity(context);
            }
            widgetMetrics.sendWidgetMetricEvent(WidgetMetrics.WidgetClickEvent.NOW_PLAYING);
        }
    }

    private boolean isPlaybackAction(final String action) {
        return "com.amazon.mp3.widget.play".equals(action) || "com.amazon.mp3.widget.pause".equals(action) || "com.amazon.mp3.widget.next".equals(action) || "com.amazon.mp3.widget.prev".equals(action);
    }

    private void startLauncherActivity(final Context context) {
        context.getApplicationContext().startActivity(getLauncherIntent(context));
    }

    private void startNowPlayingActivity(final Context context) {
        Intent nowPlayingIntent = getNowPlayingIntent(context);
        nowPlayingIntent.addFlags(268435456);
        context.startActivity(nowPlayingIntent);
    }

    private Intent getLauncherIntent(final Context context) {
        Intent intent = new Intent(context, (Class<?>) LauncherActivity.class);
        intent.addFlags(268468224);
        return intent;
    }

    private Intent getNowPlayingIntent(final Context context) {
        return LibraryActivityFactory.getIntentForContentUri(context, MediaProvider.NowPlaying.CONTENT_URI);
    }
}
