package com.byd.vrsettings;

import android.app.Service;
import android.content.Intent;
import android.content.res.Configuration;
import android.os.IBinder;
import com.byd.vrsettings.utils.RLog;
import java.util.Locale;

/* JADX INFO: loaded from: classes.dex */
public class NeuVoiceSettingService extends Service {
    private static final String TAG = "NEU_Setting_VoiceService";

    @Override // android.app.Service, android.content.ComponentCallbacks
    public void onConfigurationChanged(Configuration configuration) {
        super.onConfigurationChanged(configuration);
        RLog.e(TAG, "language:" + getResources().getConfiguration().locale.getLanguage() + ",local:" + Locale.getDefault().toString() + ",country:" + getResources().getConfiguration().locale.getCountry());
    }

    @Override // android.app.Service
    public void onCreate() {
        super.onCreate();
        RLog.i(TAG, "NeuVoiceSettingService onCreate...");
    }

    @Override // android.app.Service
    public int onStartCommand(Intent intent, int i, int i2) {
        RLog.i(TAG, "onStartCommand startId=" + i2);
        return 1;
    }

    @Override // android.app.Service
    public IBinder onBind(Intent intent) {
        throw new UnsupportedOperationException("Not yet implemented");
    }

    @Override // android.app.Service
    public void onDestroy() {
        super.onDestroy();
    }
}
