package com.byd.card.forbidden;

import android.os.Bundle;
import android.util.Log;
import com.byd.card.view.activity.ActivityCollector;

/* JADX INFO: loaded from: classes.dex */
public class ForbiddenFullActivity extends BaseForbiddenActivity {
    private static final String TAG = "ForbiddenFullActivity";

    @Override // com.byd.card.forbidden.BaseForbiddenActivity, android.app.Activity
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.d(TAG, "onCreate");
        ActivityCollector.addActivity(this, ForbiddenFullActivity.class);
    }

    @Override // com.byd.card.forbidden.BaseForbiddenActivity, android.app.Activity
    protected void onDestroy() {
        Log.d(TAG, "onDestroy");
        ActivityCollector.removeActivity(this);
        super.onDestroy();
    }

    @Override // com.byd.card.forbidden.BaseForbiddenActivity
    protected void handleQuit() {
        sendHomeKey();
    }
}
