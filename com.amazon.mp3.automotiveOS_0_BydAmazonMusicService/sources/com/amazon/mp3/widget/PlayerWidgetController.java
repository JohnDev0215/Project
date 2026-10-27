package com.amazon.mp3.widget;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import com.amazon.mp3.AmazonApplication;
import com.amazon.mp3.dialog.TryOurWidgetDialogManager;
import com.amazon.mp3.init.ForegroundLaunchInitializationTask;
import com.amazon.mp3.init.InitializationHandler;
import com.amazon.mp3.playback.playbackcontrol.ActionValidatedPlaybackController;
import com.amazon.mp3.playback.playbackcontrol.PlaybackControllerProvider;
import com.amazon.mp3.util.Log;
import com.amazon.mp3.util.health.AppHealthUtil;
import com.amazon.mp3.widget.WidgetMetrics;
import com.amazon.music.media.playback.ControlSource;
import com.amazon.music.media.playback.OnPlayStateChangedListener;
import com.amazon.music.media.playback.PlayStatus;
import com.amazon.music.media.playback.PlaybackController;

/* JADX INFO: loaded from: classes4.dex */
public class PlayerWidgetController {
    private static final String TAG = "PlayerWidgetController";
    private OnPlayStateChangedListener mPlayStateListener;
    private PlaybackController mPlaybackController;

    private PlayerWidgetController() {
        this.mPlayStateListener = new OnPlayStateChangedListener() { // from class: com.amazon.mp3.widget.PlayerWidgetController$$ExternalSyntheticLambda0
            @Override // com.amazon.music.media.playback.OnPlayStateChangedListener
            public final void onPlayStateChanged() {
                this.f$0.lambda$new$0();
            }
        };
        ActionValidatedPlaybackController controller = PlaybackControllerProvider.getController(ControlSource.WIDGET);
        this.mPlaybackController = controller;
        controller.addOnPlayStateChangedListener(this.mPlayStateListener);
    }

    private static class WidgetHolder {
        public static final PlayerWidgetController INSTANCE = new PlayerWidgetController();

        private WidgetHolder() {
        }
    }

    public static PlayerWidgetController getInstance() {
        return WidgetHolder.INSTANCE;
    }

    public void updateWidget() {
        updateWidget(AmazonApplication.getContext());
    }

    public void updateWidget(final Context context) {
        WidgetMetrics widgetMetrics = new WidgetMetrics(context);
        if (!isWidgetEnabled()) {
            widgetMetrics.checkIfShouldSendFlexEvent(WidgetMetrics.WidgetMetricsType.ENABLED, false);
            return;
        }
        new InitializationHandler(new ForegroundLaunchInitializationTask(context, new AppHealthUtil(PreferenceManager.getDefaultSharedPreferences(AmazonApplication.getContext())))).initializeOnNewThread();
        PlayerWidgetUtils.updateWidgetWithWorker(false, context);
        widgetMetrics.checkIfShouldSendFlexEvent(WidgetMetrics.WidgetMetricsType.ENABLED, true);
        Log.debug(TAG, "updateWidget called");
    }

    public void clearWidget(final Context context) {
        if (isWidgetEnabled()) {
            PlayerWidgetUtils.updateWidgetWithWorker(true, context);
        }
    }

    private SharedPreferences getSharedPrefs() {
        return AmazonApplication.getContext().getSharedPreferences(TryOurWidgetDialogManager.SHARED_PREF_NAME, 0);
    }

    private boolean isWidgetEnabled() {
        return getSharedPrefs().getBoolean(TryOurWidgetDialogManager.WIDGET_ALREADY_ENABLED, false);
    }

    public void setWidgetEnabled() {
        Log.debug(TAG, "Widget Enabled");
        setWidgetEnabldSharedPrefs(true);
    }

    public void setWidgetDisabled() {
        Log.debug(TAG, "Widget Disabled");
        setWidgetEnabldSharedPrefs(false);
    }

    private void setWidgetEnabldSharedPrefs(boolean value) {
        SharedPreferences.Editor editorEdit = getSharedPrefs().edit();
        editorEdit.putBoolean(TryOurWidgetDialogManager.WIDGET_ALREADY_ENABLED, value);
        editorEdit.apply();
    }

    public static boolean isSupported() {
        return AmazonApplication.getCapabilities().hasWidgets();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void lambda$new$0() {
        ActionValidatedPlaybackController controller = PlaybackControllerProvider.getController(ControlSource.WIDGET);
        Log.verbose(TAG, "Play State change: " + controller.getPlayStatus());
        if (controller.getPlayStatus() != PlayStatus.SEEKING) {
            updateWidget();
        }
    }
}
