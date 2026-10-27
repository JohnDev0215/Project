package com.amazon.mp3.client.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.animation.AnimationUtils;
import android.widget.ProgressBar;
import com.amazon.mp3.AmazonApplication;
import com.amazon.mp3.activity.BaseActivity;
import com.amazon.mp3.activity.MusicLauncherActivity;
import com.amazon.mp3.activity.settings.SettingsUtil;
import com.amazon.mp3.automotiveOS.R;
import com.amazon.mp3.elf.ExplicitLanguageManager;
import com.amazon.mp3.ftu.FTUMetricsSingleton;
import com.amazon.mp3.service.metrics.BaseMetricsRecorder;
import com.amazon.mp3.service.metrics.UxMetricsLogger;
import com.amazon.mp3.util.ChildUserUtil;
import com.amazon.mp3.util.ConnectivityUtil;

/* JADX INFO: loaded from: classes3.dex */
public class LauncherActivity extends MusicLauncherActivity {
    public static void start(Context context) {
        Intent intent = new Intent(context, (Class<?>) LauncherActivity.class);
        intent.addFlags(32768);
        intent.addFlags(268435456);
        context.startActivity(intent);
    }

    @Override // com.amazon.mp3.activity.MusicLauncherActivity, android.app.Activity
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_launcher);
        UxMetricsLogger.registerUserAction(UxMetricsLogger.UserAction.LAUNCH_HOME);
        BaseActivity.setLowMemory(LauncherActivity.class, false);
        if (!AmazonApplication.getCapabilities().shouldCloudBeSupported()) {
            SettingsUtil.setCirrusDownloadPreference(this, false);
        }
        if (ChildUserUtil.INSTANCE.isChildUser(this) && ConnectivityUtil.hasAnyInternetConnection(this) && !AmazonApplication.getCapabilities().isExplicitLanguageFilterEnabled()) {
            new ExplicitLanguageManager(this).setExplicitFilterSetting(true, null);
        }
    }

    @Override // android.app.Activity
    public void onPostCreate(final Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
        new Thread("Record First App Open") { // from class: com.amazon.mp3.client.activity.LauncherActivity.1
            @Override // java.lang.Thread, java.lang.Runnable
            public void run() {
                BaseMetricsRecorder.getMetricsRecorder(LauncherActivity.this.getApplicationContext()).getFirstTimeUseMetrics().recordFirstAppOpen();
                FTUMetricsSingleton.get().sendFlexEventUserLaunchedApp();
            }
        }.start();
        playSpinningAnimation();
    }

    @Override // com.amazon.mp3.activity.MusicLauncherActivity, android.app.Activity
    public void onDestroy() {
        super.onDestroy();
    }

    private void playSpinningAnimation() {
        ((ProgressBar) findViewById(R.id.loading_spinner)).startAnimation(AnimationUtils.loadAnimation(getApplicationContext(), R.anim.circle_progress_bar_rotate));
    }
}
