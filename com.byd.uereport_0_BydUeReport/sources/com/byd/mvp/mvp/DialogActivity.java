package com.byd.mvp.mvp;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.FrameLayout;
import com.byd.mvp.R$id;
import com.byd.mvp.R$layout;
import com.byd.mvp.R$style;
import com.bydux.api.common.tools.util.PathInterpolatorUtils;
import z3.f;

/* JADX INFO: loaded from: classes2.dex */
public abstract class DialogActivity<P extends f> extends XActivity<P> {

    /* JADX INFO: renamed from: p, reason: collision with root package name */
    public final int f2796p = 200;

    /* JADX INFO: renamed from: q, reason: collision with root package name */
    public ViewGroup f2797q;

    /* JADX INFO: renamed from: r, reason: collision with root package name */
    public View f2798r;

    /* JADX INFO: renamed from: s, reason: collision with root package name */
    public ViewGroup f2799s;

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

    @Override // com.byd.mvp.mvp.XActivity
    public void c(Context context) {
        ViewGroup viewGroup = (ViewGroup) findViewById(R$id.fl_dialog_activity_container);
        this.f2797q = viewGroup;
        viewGroup.setOnTouchListener(new a());
        this.f2799s = (ViewGroup) findViewById(R$id.dialog_activity_bg);
        if (q() > 0) {
            this.f2798r = LayoutInflater.from(context).inflate(q(), (ViewGroup) null);
        } else {
            this.f2798r = r();
        }
        t(context);
        FrameLayout.LayoutParams layoutParams = new FrameLayout.LayoutParams(s(context), p(context));
        View view = this.f2798r;
        if (view != null) {
            this.f2797q.addView(view, layoutParams);
        }
        WindowManager.LayoutParams attributes = getWindow().getAttributes();
        attributes.dimAmount = 0.0f;
        attributes.windowAnimations = R$style.base_dialog_anim;
        attributes.width = -1;
        attributes.height = -1;
        attributes.systemUiVisibility = 0;
        getWindow().setAttributes(attributes);
        setFinishOnTouchOutside(false);
        u(this.f2799s, this.f2797q);
        this.f2799s.setOnClickListener(new b());
    }

    @Override // androidx.appcompat.app.AppCompatActivity, android.app.Activity
    public <T extends View> T findViewById(int i8) {
        View view;
        T t7 = (T) super.findViewById(i8);
        return (t7 != null || (view = this.f2798r) == null) ? t7 : (T) view.findViewById(i8);
    }

    @Override // z3.g
    public int getLayoutId() {
        return R$layout.dialog_activity_bg_container;
    }

    @Override // android.app.Activity
    public void overridePendingTransition(int i8, int i9) {
        super.overridePendingTransition(0, 0);
    }

    public abstract int p(Context context);

    public abstract int q();

    public View r() {
        return null;
    }

    public abstract int s(Context context);

    public abstract void t(Context context);

    public void u(View view, View view2) {
        view.animate().alpha(1.0f).setDuration(200L).setInterpolator(PathInterpolatorUtils.getStandard()).start();
        view2.animate().scaleX(1.0f).scaleY(1.0f).setDuration(200L).setInterpolator(PathInterpolatorUtils.getUniformSpeed()).start();
        view2.animate().alpha(1.0f).setDuration(200L).setInterpolator(PathInterpolatorUtils.getStandard()).start();
    }
}
