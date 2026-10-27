package com.android.provision.base;

import android.app.Activity;
import android.app.UiModeManager;
import android.content.Context;
import android.os.Bundle;

/* JADX INFO: loaded from: classes.dex */
public class BaseActivity extends Activity {
    protected Context mContext;

    @Override // android.app.Activity
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.mContext = this;
    }

    public void setFullScreen() {
        getWindow().getDecorView().setSystemUiVisibility(4871);
    }

    protected boolean isNightMode() {
        return ((UiModeManager) this.mContext.getSystemService(UiModeManager.class)).getNightMode() == 2;
    }
}
