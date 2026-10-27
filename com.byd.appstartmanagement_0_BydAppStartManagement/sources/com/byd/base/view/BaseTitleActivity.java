package com.byd.base.view;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.annotation.LayoutRes;
import b.a.b.c.e;
import com.byd.base.R$id;
import com.byd.base.R$layout;
import com.bydux.api.common.views.window.BydActivity;

/* JADX INFO: loaded from: classes.dex */
public abstract class BaseTitleActivity extends BydActivity implements View.OnClickListener {

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public TextView f539b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public LinearLayout f540c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public LinearLayout f541d;
    public TextView e;
    public ImageView f;
    public TextView g;
    public ImageView h;
    public FrameLayout i;

    public void g() {
        Window window = getWindow();
        window.addFlags(Integer.MIN_VALUE);
        window.clearFlags(67108864);
        window.setStatusBarColor(0);
        window.getDecorView().setSystemUiVisibility(1792);
    }

    public final ViewGroup h() {
        this.i.removeAllViewsInLayout();
        return this.i;
    }

    public abstract int i();

    public final void j() {
        this.i = (FrameLayout) findViewById(R$id.base_fl);
        findViewById(R$id.iv_back).setOnClickListener(this);
        this.f539b = (TextView) findViewById(R$id.tv_title);
        this.f540c = (LinearLayout) findViewById(R$id.menu1_lay);
        this.f541d = (LinearLayout) findViewById(R$id.menu2_lay);
        this.e = (TextView) findViewById(R$id.tv_menu1);
        this.f = (ImageView) findViewById(R$id.iv_menu1);
        this.g = (TextView) findViewById(R$id.tv_menu2);
        this.h = (ImageView) findViewById(R$id.iv_menu2);
        this.f540c.setOnClickListener(this);
        this.f541d.setOnClickListener(this);
        this.f539b.setText(i());
    }

    public void k() {
        finish();
    }

    public void l() {
    }

    public void m() {
    }

    @Override // android.view.View.OnClickListener
    public void onClick(View view) {
        if (view.getId() == R$id.iv_back) {
            k();
        } else if (view.getId() == R$id.menu1_lay) {
            l();
        } else if (view.getId() == R$id.menu2_lay) {
            m();
        }
    }

    @Override // com.bydux.api.common.views.window.BydActivity, androidx.appcompat.app.AppCompatActivity, androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, androidx.core.app.ComponentActivity, android.app.Activity
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        g();
        super.setContentView(R$layout.base_activity);
        j();
        e.a(this, true);
    }

    @Override // com.bydux.api.common.views.window.BydActivity, androidx.appcompat.app.AppCompatActivity, androidx.fragment.app.FragmentActivity, android.app.Activity
    public void onDestroy() {
        super.onDestroy();
        e.a(this, false);
    }

    @Override // androidx.appcompat.app.AppCompatActivity, android.app.Activity
    public void setContentView(@LayoutRes int i) {
        LayoutInflater.from(this).inflate(i, h());
    }

    @Override // androidx.appcompat.app.AppCompatActivity, android.app.Activity
    public void setContentView(View view) {
        h().addView(view);
    }
}
