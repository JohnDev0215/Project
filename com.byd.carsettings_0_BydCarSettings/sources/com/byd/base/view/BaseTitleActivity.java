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
import b.b.a.h.m;
import com.byd.base.R$id;
import com.byd.base.R$layout;
import com.bydux.api.common.views.window.BydActivity;

/* JADX INFO: loaded from: classes.dex */
public abstract class BaseTitleActivity extends BydActivity implements View.OnClickListener {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public TextView f2446a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public LinearLayout f2447b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public LinearLayout f2448c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public TextView f2449d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public ImageView f2450e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public TextView f2451f;
    public ImageView g;
    public FrameLayout h;

    public void b() {
        Window window = getWindow();
        window.addFlags(Integer.MIN_VALUE);
        window.clearFlags(67108864);
        window.setStatusBarColor(0);
        window.getDecorView().setSystemUiVisibility(1792);
    }

    public final ViewGroup e() {
        this.h.removeAllViewsInLayout();
        return this.h;
    }

    public abstract int f();

    public final void k() {
        this.h = (FrameLayout) findViewById(R$id.base_fl);
        findViewById(R$id.iv_back).setOnClickListener(this);
        this.f2446a = (TextView) findViewById(R$id.tv_title);
        this.f2447b = (LinearLayout) findViewById(R$id.menu1_lay);
        this.f2448c = (LinearLayout) findViewById(R$id.menu2_lay);
        this.f2449d = (TextView) findViewById(R$id.tv_menu1);
        this.f2450e = (ImageView) findViewById(R$id.iv_menu1);
        this.f2451f = (TextView) findViewById(R$id.tv_menu2);
        this.g = (ImageView) findViewById(R$id.iv_menu2);
        this.f2447b.setOnClickListener(this);
        this.f2448c.setOnClickListener(this);
        this.f2446a.setText(f());
    }

    public void l() {
        finish();
    }

    public void n() {
    }

    @Override // android.view.View.OnClickListener
    public void onClick(View view) {
        if (view.getId() == R$id.iv_back) {
            l();
        } else if (view.getId() == R$id.menu1_lay) {
            n();
        } else if (view.getId() == R$id.menu2_lay) {
            p();
        }
    }

    @Override // com.bydux.api.common.views.window.BydActivity, androidx.appcompat.app.AppCompatActivity, androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, androidx.core.app.ComponentActivity, android.app.Activity
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        b();
        super.setContentView(R$layout.base_activity);
        k();
        m.a(this, true);
    }

    @Override // com.bydux.api.common.views.window.BydActivity, androidx.appcompat.app.AppCompatActivity, androidx.fragment.app.FragmentActivity, android.app.Activity
    public void onDestroy() {
        super.onDestroy();
        m.a(this, false);
    }

    public void p() {
    }

    public void q(int i) {
        this.f2450e.setImageResource(i);
    }

    public void r(int i, int i2, int i3) {
        this.f2447b.setVisibility(i);
        this.f2450e.setVisibility(i2);
        this.f2449d.setVisibility(i3);
    }

    @Override // androidx.appcompat.app.AppCompatActivity, android.app.Activity
    public void setContentView(@LayoutRes int i) {
        LayoutInflater.from(this).inflate(i, e());
    }

    public void t(int i) {
        this.g.setImageResource(i);
    }

    public void u(int i, int i2, int i3) {
        this.f2448c.setVisibility(i);
        this.g.setVisibility(i2);
        this.f2451f.setVisibility(i3);
    }

    @Override // androidx.appcompat.app.AppCompatActivity, android.app.Activity
    public void setContentView(View view) {
        e().addView(view);
    }
}
