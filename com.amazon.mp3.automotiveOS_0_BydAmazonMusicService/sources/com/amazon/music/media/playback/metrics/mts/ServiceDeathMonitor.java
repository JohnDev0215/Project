package com.amazon.music.media.playback.metrics.mts;

import android.app.ActivityManager;
import android.content.SharedPreferences;
import android.os.Build;
import androidx.annotation.VisibleForTesting;
import com.amazon.identity.auth.device.api.MultipleAccountManager;
import com.amazon.music.media.playback.ControlSource;
import com.amazon.music.media.playback.PlayStatus;
import com.amazon.music.media.playback.Playback;
import com.amazon.music.media.playback.util.AndroidUtils;
import com.amazon.music.media.playback.util.Clock;
import com.amazon.music.media.playback.util.ObjectUtils;
import com.amazon.music.metrics.mts.event.definition.flex.FlexEvent;
import java.util.concurrent.TimeUnit;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/* JADX INFO: loaded from: classes4.dex */
public class ServiceDeathMonitor {
    private static final Logger logger = LoggerFactory.getLogger(ServiceDeathMonitor.class.getSimpleName());
    private static boolean serviceCreated;
    private long appBackgroundTime;
    private boolean appForeground;
    private boolean destroyed;
    private int playState;
    private final Playback playback;
    private String playerName;
    private final SharedPreferences prefs;
    private boolean restricted;
    private boolean serviceForeground;
    private long timeInBackground;

    private static String getPlayStateString(int i) {
        return i != 1 ? i != 2 ? "Paused" : "Playing" : "Buffering";
    }

    public ServiceDeathMonitor(Playback playback) {
        this.playback = playback;
        SharedPreferences sharedPreferencesCreateSharedPrefs = createSharedPrefs();
        this.prefs = sharedPreferencesCreateSharedPrefs;
        this.playState = sharedPreferencesCreateSharedPrefs.getInt("playState", 0);
        this.serviceForeground = sharedPreferencesCreateSharedPrefs.getBoolean("serviceForeground", false);
        this.appForeground = sharedPreferencesCreateSharedPrefs.getBoolean("appForeground", false);
        this.destroyed = sharedPreferencesCreateSharedPrefs.getBoolean("destroyed", false);
        this.playerName = sharedPreferencesCreateSharedPrefs.getString("playerName", "");
        this.appBackgroundTime = 0L;
        this.timeInBackground = sharedPreferencesCreateSharedPrefs.getLong("timeInBackground", 0L);
        this.restricted = sharedPreferencesCreateSharedPrefs.getBoolean("restricted", false) || isRestricted();
    }

    @VisibleForTesting
    SharedPreferences createSharedPrefs() {
        return this.playback.getPlaybackConfig().getContext().getSharedPreferences("ServiceDeathMonitor", 0);
    }

    public void onServiceCreate() {
        if (this.playState != 0 || this.serviceForeground) {
            onServiceDeath();
        }
        this.restricted = isRestricted();
        serviceCreated = true;
        this.serviceForeground = false;
        this.destroyed = false;
        this.appForeground = AndroidUtils.isApplicationInForeground();
        this.playState = getCurrentPlayState();
        this.appBackgroundTime = 0L;
        this.timeInBackground = 0L;
    }

    public void onServiceDestroy() {
        if (this.destroyed) {
            return;
        }
        this.destroyed = true;
        updatePrefs();
    }

    public void onServiceInForeground() {
        if (this.serviceForeground) {
            return;
        }
        this.serviceForeground = true;
        updatePrefs();
    }

    public void onServiceInBackground() {
        if (this.serviceForeground) {
            this.serviceForeground = false;
            updatePrefs();
        }
    }

    public void onAppInForeground() {
        this.appBackgroundTime = 0L;
        if (this.appForeground) {
            return;
        }
        this.appForeground = true;
        updatePrefs();
    }

    public void onAppInBackground() {
        this.appBackgroundTime = Clock.now();
        if (this.appForeground) {
            this.appForeground = false;
            updatePrefs();
        }
    }

    @VisibleForTesting
    PlayStatus getCurrentPlayStatus() {
        return this.playback.getPlaybackController(ControlSource.NONE).getPlayStatus();
    }

    private int getCurrentPlayState() {
        PlayStatus currentPlayStatus = getCurrentPlayStatus();
        if (currentPlayStatus == PlayStatus.RENDERING) {
            return 2;
        }
        return currentPlayStatus.willPlay() ? 1 : 0;
    }

    @VisibleForTesting
    String getCurrentMediaPlayerName() {
        return this.playback.getPlaybackController(ControlSource.NONE).getMediaPlayerName();
    }

    @VisibleForTesting
    boolean isRestricted() {
        ActivityManager activityManager;
        if (Build.VERSION.SDK_INT < 28 || (activityManager = (ActivityManager) this.playback.getPlaybackConfig().getContext().getSystemService(MultipleAccountManager.SessionPackageMappingType.JSON_KEY_SESSION_PACKAGE_MAPPING_REMOVE_ACTIVITY_CLASS_NAME)) == null) {
            return false;
        }
        return activityManager.isBackgroundRestricted();
    }

    public void onPlayStateChanged() {
        int i = this.playState;
        String str = this.playerName;
        this.playState = getCurrentPlayState();
        String currentMediaPlayerName = getCurrentMediaPlayerName();
        this.playerName = currentMediaPlayerName;
        if (i == this.playState && ObjectUtils.equal(str, currentMediaPlayerName)) {
            return;
        }
        updatePrefs();
    }

    private void updatePrefs() {
        long jNow = 0;
        if (this.destroyed && this.appBackgroundTime != 0) {
            jNow = Clock.now() - this.appBackgroundTime;
        }
        if (isRestricted()) {
            this.restricted = true;
        }
        SharedPreferences.Editor editorEdit = this.prefs.edit();
        editorEdit.putBoolean("serviceForeground", this.serviceForeground);
        editorEdit.putBoolean("appForeground", this.appForeground);
        editorEdit.putInt("playState", this.playState);
        editorEdit.putLong("timeInBackground", jNow);
        editorEdit.putBoolean("destroyed", this.destroyed);
        editorEdit.putBoolean("restricted", this.restricted);
        editorEdit.putString("playerName", this.playerName);
        editorEdit.apply();
    }

    private void onServiceDeath() {
        sendFlexEvent(FlexEvent.builder("ServiceDeath").withFlexStr1(this.serviceForeground ? "ForegroundService" : "BackgroundService").withFlexStr2(this.appForeground ? "ForegroundApp" : "BackgroundApp").withFlexStr3(getPlayStateString(this.playState)).withFlexStr4(this.playerName).withFlexNum1(Float.valueOf(TimeUnit.MILLISECONDS.toSeconds(this.timeInBackground))).withFlexNum2(Float.valueOf(this.destroyed ? 1.0f : 0.0f)).withFlexNum3(Float.valueOf(serviceCreated ? 0.0f : 1.0f)).withFlexNum4(Float.valueOf(this.restricted ? 1.0f : 0.0f)).build());
    }

    @VisibleForTesting
    void sendFlexEvent(FlexEvent flexEvent) {
        logger.warn("sending flexEvent for service death: " + flexEvent.getEventAttributes());
        this.playback.getPlaybackConfig().getMTSMetricsSender().sendMetricsEvent(flexEvent);
    }
}
