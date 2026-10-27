package com.byd.base.view;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import c.a.a.b;
import c.a.a.c;
import com.bydux.api.common.views.window.BydActivity;

/* JADX INFO: loaded from: classes.dex */
public abstract class BaseTitleActivity extends BydActivity implements View.OnClickListener {

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public TextView f4318b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public LinearLayout f4319c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public LinearLayout f4320d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public TextView f4321e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public ImageView f4322f;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public TextView f4323g;

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    public ImageView f4324h;

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public FrameLayout f4325i;

    public final ViewGroup a() {
        this.f4325i.removeAllViewsInLayout();
        return this.f4325i;
    }

    public abstract int b();

    public final void c() {
        this.f4325i = (FrameLayout) findViewById(b.base_fl);
        findViewById(b.iv_back).setOnClickListener(this);
        this.f4318b = (TextView) findViewById(b.tv_title);
        this.f4319c = (LinearLayout) findViewById(b.menu1_lay);
        this.f4320d = (LinearLayout) findViewById(b.menu2_lay);
        this.f4321e = (TextView) findViewById(b.tv_menu1);
        this.f4322f = (ImageView) findViewById(b.iv_menu1);
        this.f4323g = (TextView) findViewById(b.tv_menu2);
        this.f4324h = (ImageView) findViewById(b.iv_menu2);
        this.f4319c.setOnClickListener(this);
        this.f4320d.setOnClickListener(this);
        this.f4318b.setText(b());
    }

    public abstract void d();

    public abstract void e();

    @Override // android.view.View.OnClickListener
    public void onClick(View view) {
        if (view.getId() == b.iv_back) {
            finish();
        } else if (view.getId() == b.menu1_lay) {
            d();
        } else if (view.getId() == b.menu2_lay) {
            e();
        }
    }

    @Override // com.bydux.api.common.views.window.BydActivity, androidx.appcompat.app.AppCompatActivity, androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, androidx.core.app.ComponentActivity, android.app.Activity
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        super.setContentView(c.base_activity);
        c();
    }

    @Override // androidx.appcompat.app.AppCompatActivity, androidx.activity.ComponentActivity, android.app.Activity
    public void setContentView(int i2) {
        LayoutInflater.from(this).inflate(i2, a());
    }

    @Override // androidx.appcompat.app.AppCompatActivity, androidx.activity.ComponentActivity, android.app.Activity
    public void setContentView(View view) {
        a().addView(view);
    }
}
