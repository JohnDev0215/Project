package com.byd.mvp.mvp;

import android.content.Context;
import android.content.IntentFilter;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.FrameLayout;
import androidx.annotation.Nullable;
import b.a.d.d.e;
import com.byd.mvp.R$id;
import com.byd.mvp.R$layout;
import com.byd.mvp.R$style;
import com.bydux.api.common.tools.util.PathInterpolatorUtils;

/* JADX INFO: loaded from: classes2.dex */
public abstract class DialogActivity<P extends e> extends XActivity<P> {
    public final int n = 200;
    public ViewGroup o;
    public View p;
    public ViewGroup q;
    public NavigationBarClickReceiver r;

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

    public abstract void A(Context context);

    public void B(View view, View view2) {
        view.animate().alpha(1.0f).setDuration(200L).setInterpolator(PathInterpolatorUtils.getStandard()).start();
        view2.animate().scaleX(1.0f).scaleY(1.0f).setDuration(200L).setInterpolator(PathInterpolatorUtils.getUniformSpeed()).start();
        view2.animate().alpha(1.0f).setDuration(200L).setInterpolator(PathInterpolatorUtils.getStandard()).start();
    }

    @Override // b.a.d.d.f
    public int c() {
        return R$layout.dialog_activity_bg_container;
    }

    @Override // androidx.appcompat.app.AppCompatActivity, android.app.Activity
    public <T extends View> T findViewById(int i) {
        View view;
        T t = (T) super.findViewById(i);
        return (t != null || (view = this.p) == null) ? t : (T) view.findViewById(i);
    }

    @Override // com.byd.mvp.mvp.XActivity
    public void j(Context context) {
        ViewGroup viewGroup = (ViewGroup) findViewById(R$id.fl_dialog_activity_container);
        this.o = viewGroup;
        viewGroup.setOnTouchListener(new a());
        this.q = (ViewGroup) findViewById(R$id.dialog_activity_bg);
        if (x() > 0) {
            this.p = LayoutInflater.from(context).inflate(x(), (ViewGroup) null);
        } else {
            this.p = y();
        }
        A(context);
        FrameLayout.LayoutParams layoutParams = new FrameLayout.LayoutParams(z(context), w(context));
        View view = this.p;
        if (view != null) {
            this.o.addView(view, layoutParams);
        }
        WindowManager.LayoutParams attributes = getWindow().getAttributes();
        attributes.dimAmount = 0.0f;
        attributes.windowAnimations = R$style.base_dialog_anim;
        attributes.width = -1;
        attributes.height = -1;
        attributes.systemUiVisibility = 0;
        getWindow().setAttributes(attributes);
        setFinishOnTouchOutside(false);
        B(this.q, this.o);
        this.q.setOnClickListener(new b());
    }

    @Override // com.byd.mvp.mvp.XActivity, com.bydux.api.common.views.window.BydActivity, androidx.appcompat.app.AppCompatActivity, androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, androidx.core.app.ComponentActivity, android.app.Activity
    public void onCreate(@Nullable Bundle bundle) {
        super.onCreate(bundle);
        this.r = new NavigationBarClickReceiver(this);
        registerReceiver(this.r, new IntentFilter(NavigationBarClickReceiver.f646a));
    }

    @Override // com.byd.mvp.mvp.XActivity, com.bydux.api.common.views.window.BydActivity, androidx.appcompat.app.AppCompatActivity, androidx.fragment.app.FragmentActivity, android.app.Activity
    public void onDestroy() {
        super.onDestroy();
        unregisterReceiver(this.r);
    }

    @Override // android.app.Activity
    public void overridePendingTransition(int i, int i2) {
        super.overridePendingTransition(0, 0);
    }

    public abstract int w(Context context);

    public abstract int x();

    public View y() {
        b.a.d.b.a.e(this.f651d, "----getWindowView---" + getClass().getName());
        return null;
    }

    public abstract int z(Context context);
}
