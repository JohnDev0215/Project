package com.byd.auto_photo;

import android.os.Bundle;

/* JADX INFO: loaded from: classes.dex */
public class BlurActivity extends BaseBlurActivity {
    @Override // com.byd.auto_photo.BaseBlurActivity
    protected void R0() {
        this.U.setText(String.valueOf((int) ((this.f0 * 100.0f) / 25.0f)));
    }

    @Override // com.byd.auto_photo.BaseBlurActivity, com.byd.auto_photo.BaseEditActivity, com.byd.auto_photo.SuperBaseEditActivity, com.byd.auto_photo.SubBaseTaskActivity, com.byd.theme.ThemeActivity, com.byd.ui.BaseActivity, androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, androidx.core.app.ComponentActivity, android.app.Activity
    protected void onCreate(Bundle bundle) {
        this.V = true;
        super.onCreate(bundle);
    }

    @Override // com.byd.auto_photo.BaseBlurActivity, com.byd.auto_photo.BaseEditActivity, com.byd.auto_photo.SuperBaseEditActivity
    protected void t0() {
        super.t0();
        this.Q.setRectMode(1);
    }
}
