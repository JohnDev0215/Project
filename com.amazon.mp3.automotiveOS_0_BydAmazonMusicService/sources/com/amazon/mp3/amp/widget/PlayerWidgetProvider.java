package com.amazon.mp3.amp.widget;

import android.appwidget.AppWidgetManager;
import android.appwidget.AppWidgetProvider;
import android.content.Context;
import com.amazon.mp3.playback.playbackcontrol.ActionValidatedPlaybackController;
import com.amazon.mp3.util.Log;
import com.amazon.mp3.widget.PlayerWidgetController;
import com.amazon.mp3.widget.PlayerWidgetUtils;

/* JADX INFO: loaded from: classes3.dex */
public class PlayerWidgetProvider extends AppWidgetProvider {
    private static final String TAG = PlayerWidgetProvider.class.getSimpleName();

    @Override // android.appwidget.AppWidgetProvider
    public void onUpdate(Context context, AppWidgetManager appWidgetManager, int[] appWidgetIds) {
        super.onUpdate(context, appWidgetManager, appWidgetIds);
        Log.debug(TAG, "Updating widget");
        ActionValidatedPlaybackController playbackController = PlayerWidgetUtils.getPlaybackController();
        PlayerWidgetUtils.enqueueDelayedWorker(context);
        PlayerWidgetUtils.setupWidgetAsync(context, playbackController, false);
    }

    @Override // android.appwidget.AppWidgetProvider
    public void onEnabled(Context context) {
        Log.debug(TAG, "Enabling widget");
        PlayerWidgetController.getInstance().setWidgetEnabled();
    }

    @Override // android.appwidget.AppWidgetProvider
    public void onDisabled(final Context context) {
        super.onDisabled(context);
        PlayerWidgetController.getInstance().setWidgetDisabled();
    }
}
