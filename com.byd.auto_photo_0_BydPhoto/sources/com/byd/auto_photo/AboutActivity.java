package com.byd.auto_photo;

import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.TextView;

/* JADX INFO: loaded from: classes.dex */
public class AboutActivity extends AboutBaseActivity {
    private OTAReceiver t;

    private void f0() {
        Window window = getWindow();
        window.addFlags(Integer.MIN_VALUE);
        window.clearFlags(67108864);
        window.setStatusBarColor(0);
        window.getDecorView().setSystemUiVisibility(1280);
    }

    @Override // com.byd.theme.ThemeActivity
    protected int K(boolean z) {
        return b.a.a.a(this) != 1 ? super.K(z) : R.style.AppThemeTS;
    }

    @Override // com.byd.auto_photo.AboutBaseActivity
    protected void e0() {
        super.e0();
        findViewById(R.id.view_title_text).setOnClickListener(this);
        if (b.a.a.a(this) == 1) {
            ((TextView) findViewById(R.id.tv_copyright)).setText(R.string.copyright_ts);
        }
    }

    @Override // com.byd.auto_photo.AboutBaseActivity, android.view.View.OnClickListener
    public void onClick(View view) {
        if (view.getId() == R.id.view_title_text) {
            super.onClick(this.r);
        } else {
            super.onClick(view);
        }
    }

    @Override // com.byd.auto_photo.AboutBaseActivity, com.byd.auto_photo.SubBaseTaskActivity, com.byd.theme.ThemeActivity, com.byd.ui.BaseActivity, androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, androidx.core.app.ComponentActivity, android.app.Activity
    protected void onCreate(Bundle bundle) {
        OTAReceiver oTAReceiver = new OTAReceiver(this);
        this.t = oTAReceiver;
        oTAReceiver.a();
        super.onCreate(bundle);
    }

    @Override // com.byd.auto_photo.SubBaseTaskActivity, com.byd.theme.ThemeActivity, com.byd.ui.BaseActivity, androidx.fragment.app.FragmentActivity, android.app.Activity
    protected void onDestroy() {
        this.t.b();
        super.onDestroy();
    }

    @Override // com.byd.theme.ThemeActivity, com.byd.ui.BaseActivity, androidx.fragment.app.FragmentActivity, android.app.Activity
    protected void onStart() {
        f0();
        super.onStart();
    }

    @Override // android.app.Activity, android.view.Window.Callback
    public void onWindowFocusChanged(boolean z) {
        super.onWindowFocusChanged(z);
        if (z) {
            f0();
        }
    }
}
