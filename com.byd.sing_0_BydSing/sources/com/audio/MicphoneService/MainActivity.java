package com.audio.MicphoneService;

import android.app.Activity;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import com.micphone.service.MicphoneService;
import com.micphone.utils.Config;

/* JADX INFO: loaded from: classes.dex */
public class MainActivity extends Activity {
    @Override // android.app.Activity
    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView(R.layout.activity_main);
        Intent intent = new Intent(this, (Class<?>) MicphoneService.class);
        if (!Config.isFrontService() || Build.VERSION.SDK_INT < 26) {
            getApplicationContext().startService(intent);
        } else {
            getApplicationContext().startForegroundService(intent);
        }
        finish();
    }

    @Override // android.app.Activity
    protected void onResume() {
        super.onResume();
    }
}
