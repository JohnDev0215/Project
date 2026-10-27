package com.android.provision.base;

import android.content.Intent;
import android.os.Bundle;
import com.bydux.api.common.tools.log.BydLogHelper;

/* JADX INFO: loaded from: classes.dex */
public class BaseFullScreenActivity extends BaseActivity {
    protected void sendBroadcastToSysUI(boolean visible) {
    }

    @Override // com.android.provision.base.BaseActivity, android.app.Activity
    public void onCreate(Bundle bundle) {
        setFullScreen();
        super.onCreate(bundle);
    }

    @Override // android.app.Activity, android.view.Window.Callback
    public void onWindowFocusChanged(boolean focused) {
        super.onWindowFocusChanged(focused);
        if (focused) {
            setFullScreen();
        }
    }

    @Override // android.app.Activity
    public void onResume() {
        super.onResume();
        setFullScreen();
    }

    @Override // android.app.Activity
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        setFullScreen();
    }

    @Override // android.app.Activity
    public void onBackPressed() {
        BydLogHelper.logV();
    }
}
