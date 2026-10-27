package com.byd.hvac.view.dialog;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.RelativeLayout;
import com.byd.hvac.R;
import com.byd.hvac.view.dialog.CountDownTimerTextView;

/* JADX INFO: compiled from: caradapter-bug */
/* JADX INFO: loaded from: classes.dex */
public class AcStartVehicleTipsLayout extends RelativeLayout {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public CountDownTimerTextView f1050a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public Context f1051b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public b f1052c;

    /* JADX INFO: compiled from: caradapter-bug */
    public class a implements CountDownTimerTextView.c {
        public a() {
        }

        @Override // com.byd.hvac.view.dialog.CountDownTimerTextView.c
        public void a() {
            if (AcStartVehicleTipsLayout.this.f1052c != null) {
                AcStartVehicleTipsLayout.this.f1052c.a();
            }
        }

        @Override // com.byd.hvac.view.dialog.CountDownTimerTextView.c
        public void b() {
            if (AcStartVehicleTipsLayout.this.f1052c != null) {
                AcStartVehicleTipsLayout.this.f1052c.b();
            }
        }
    }

    /* JADX INFO: compiled from: caradapter-bug */
    public interface b {
        void a();

        void b();
    }

    public AcStartVehicleTipsLayout(Context context) {
        super(context);
        this.f1051b = context;
    }

    public final void b() {
    }

    public final void c() {
        CountDownTimerTextView countDownTimerTextView = (CountDownTimerTextView) findViewById(R.id.tv_yes);
        this.f1050a = countDownTimerTextView;
        countDownTimerTextView.setOnBtnCallBackListener(new a());
    }

    @Override // android.view.ViewGroup, android.view.View
    public void onAttachedToWindow() {
        super.onAttachedToWindow();
        c();
        b();
    }

    @Override // android.view.ViewGroup, android.view.View
    public void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        CountDownTimerTextView countDownTimerTextView = this.f1050a;
        if (countDownTimerTextView != null) {
            countDownTimerTextView.k();
        }
    }

    public void setBtnCallBackListener(b bVar) {
        this.f1052c = bVar;
    }

    public AcStartVehicleTipsLayout(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.f1051b = context;
    }

    public AcStartVehicleTipsLayout(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        this.f1051b = context;
    }
}
