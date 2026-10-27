package com.byd.mvp.mvp;

import android.content.Context;
import android.content.IntentFilter;
import android.content.res.Resources;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.FrameLayout;
import androidx.annotation.Nullable;
import b.b.m.e.g;
import com.byd.mvp.R$color;
import com.byd.mvp.R$id;
import com.byd.mvp.R$layout;
import com.byd.mvp.R$style;
import com.bydux.api.common.tools.util.PathInterpolatorUtils;

/* JADX INFO: loaded from: classes2.dex */
public abstract class DialogActivity<P extends g> extends XActivity<P> {
    public final int p = 200;
    public ViewGroup q;
    public View r;
    public ViewGroup s;
    public NavigationBarClickReceiver t;

    public class a implements View.OnTouchListener {
        public a() {
        }

        @Override // android.view.View.OnTouchListener
        public boolean onTouch(View view, MotionEvent motionEvent) {
            return true;
        }
    }

    public class b implements View.OnClickListener {
        public b() {
        }

        @Override // android.view.View.OnClickListener
        public void onClick(View view) {
            DialogActivity.this.finish();
        }
    }

    public abstract int J(Context context);

    public abstract int K();

    public View L() {
        b.b.m.c.a.f(this.f3705c, "----getWindowView---" + getClass().getName());
        return null;
    }

    public abstract int M(Context context);

    public abstract void N(Context context);

    public void O(View view, View view2) {
        view.animate().alpha(1.0f).setDuration(200L).setInterpolator(PathInterpolatorUtils.getStandard()).start();
        view2.animate().scaleX(1.0f).scaleY(1.0f).setDuration(200L).setInterpolator(PathInterpolatorUtils.getUniformSpeed()).start();
        view2.animate().alpha(1.0f).setDuration(200L).setInterpolator(PathInterpolatorUtils.getStandard()).start();
    }

    public void P() {
        ViewGroup viewGroup = this.s;
        Resources resources = getResources();
        int i = R$color.transparent_100;
        viewGroup.setBackgroundColor(resources.getColor(i));
        getWindow().setBackgroundDrawableResource(i);
        getWindow().setDimAmount(0.0f);
    }

    @Override // androidx.appcompat.app.AppCompatActivity, android.app.Activity
    public <T extends View> T findViewById(int i) {
        View view;
        T t = (T) super.findViewById(i);
        return (t != null || (view = this.r) == null) ? t : (T) view.findViewById(i);
    }

    @Override // b.b.m.e.h
    public int getLayoutId() {
        return R$layout.dialog_activity_bg_container;
    }

    @Override // com.byd.mvp.mvp.XActivity
    public void n(Context context) {
        ViewGroup viewGroup = (ViewGroup) findViewById(R$id.fl_dialog_activity_container);
        this.q = viewGroup;
        viewGroup.setOnTouchListener(new a());
        this.s = (ViewGroup) findViewById(R$id.dialog_activity_bg);
        if (K() > 0) {
            this.r = LayoutInflater.from(context).inflate(K(), (ViewGroup) null);
        } else {
            this.r = L();
        }
        N(context);
        FrameLayout.LayoutParams layoutParams = new FrameLayout.LayoutParams(M(context), J(context));
        View view = this.r;
        if (view != null) {
            this.q.addView(view, layoutParams);
        }
        WindowManager.LayoutParams attributes = getWindow().getAttributes();
        attributes.dimAmount = 0.0f;
        attributes.windowAnimations = R$style.base_dialog_anim;
        attributes.width = -1;
        attributes.height = -1;
        attributes.systemUiVisibility = 0;
        getWindow().setAttributes(attributes);
        setFinishOnTouchOutside(false);
        O(this.s, this.q);
        this.s.setOnClickListener(new b());
    }

    @Override // com.byd.mvp.mvp.XActivity, com.bydux.api.common.views.window.BydActivity, androidx.appcompat.app.AppCompatActivity, androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, androidx.core.app.ComponentActivity, android.app.Activity
    public void onCreate(@Nullable Bundle bundle) {
        super.onCreate(bundle);
        this.t = new NavigationBarClickReceiver(this);
        registerReceiver(this.t, new IntentFilter(NavigationBarClickReceiver.f3700a));
    }

    @Override // com.byd.mvp.mvp.XActivity, com.bydux.api.common.views.window.BydActivity, androidx.appcompat.app.AppCompatActivity, androidx.fragment.app.FragmentActivity, android.app.Activity
    public void onDestroy() {
        super.onDestroy();
        unregisterReceiver(this.t);
    }

    @Override // android.app.Activity
    public void overridePendingTransition(int i, int i2) {
        super.overridePendingTransition(0, 0);
    }
}
