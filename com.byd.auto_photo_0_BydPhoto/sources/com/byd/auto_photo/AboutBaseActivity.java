package com.byd.auto_photo;

import android.content.pm.PackageManager;
import android.content.res.Configuration;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

/* JADX INFO: loaded from: classes.dex */
public abstract class AboutBaseActivity extends SubBaseTaskActivity implements View.OnClickListener {
    protected View r;
    protected TextView s;

    @Override // com.byd.theme.ThemeActivity
    protected void N() {
        setContentView(R.layout.about_layout);
        e0();
    }

    @Override // com.byd.theme.ThemeActivity
    protected void U(Configuration configuration) {
        N();
    }

    protected void e0() {
        try {
            ((TextView) findViewById(R.id.tv_app_version)).setText(getPackageManager().getPackageInfo(getApplication().getPackageName(), 0).versionName);
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }
        View viewFindViewById = findViewById(R.id.view_back);
        this.r = viewFindViewById;
        viewFindViewById.setOnClickListener(this);
        this.s = (TextView) findViewById(R.id.tv_icp);
    }

    public void onClick(View view) {
        if (view.getId() != R.id.view_back) {
            return;
        }
        finish();
    }

    @Override // com.byd.auto_photo.SubBaseTaskActivity, com.byd.theme.ThemeActivity, com.byd.ui.BaseActivity, androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, androidx.core.app.ComponentActivity, android.app.Activity
    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
    }
}
