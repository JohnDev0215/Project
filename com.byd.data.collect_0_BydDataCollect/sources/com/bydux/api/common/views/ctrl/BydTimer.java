package com.bydux.api.common.views.ctrl;

import android.content.Context;
import android.os.Handler;
import android.os.Message;
import android.util.AttributeSet;
import android.view.View;

/* JADX INFO: loaded from: classes.dex */
public class BydTimer extends View {

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private final String f3605b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    private b f3606c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    private long f3607d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    private long f3608e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    private boolean f3609f;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    private final int f3610g;

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    private Handler f3611h;

    class a extends Handler {
        a() {
        }

        @Override // android.os.Handler
        public void handleMessage(Message message) {
            if (message.what == 1000 && BydTimer.this.f3609f) {
                if (BydTimer.this.f3606c != null) {
                    BydTimer.this.f3606c.a(BydTimer.this);
                }
                BydTimer.this.f3611h.sendEmptyMessageDelayed(1000, BydTimer.this.f3607d);
            }
        }
    }

    public interface b {
        void a(View view);
    }

    public BydTimer(Context context) {
        super(context);
        this.f3605b = getClass().getSimpleName();
        this.f3606c = null;
        this.f3607d = 0L;
        this.f3608e = 0L;
        this.f3609f = false;
        this.f3610g = 1000;
        this.f3611h = new a();
    }

    public void e(long j) {
        f(j, j);
    }

    public void f(long j, long j2) {
        this.f3607d = j;
        this.f3608e = j2;
        this.f3611h.removeMessages(1000);
        this.f3609f = false;
        if (this.f3607d > 0) {
            this.f3611h.sendEmptyMessageDelayed(1000, this.f3608e);
            this.f3609f = true;
        }
    }

    public void g() {
        e(0L);
    }

    @Override // android.view.View
    protected void onAttachedToWindow() {
        super.onAttachedToWindow();
        f(this.f3607d, this.f3608e);
    }

    @Override // android.view.View
    protected void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        this.f3611h.removeMessages(1000);
        this.f3609f = false;
    }

    @Override // android.view.View
    protected void onMeasure(int i2, int i3) {
        setMeasuredDimension(0, 0);
    }

    public void setBydTimerListener(b bVar) {
        this.f3606c = bVar;
    }

    public BydTimer(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.f3605b = getClass().getSimpleName();
        this.f3606c = null;
        this.f3607d = 0L;
        this.f3608e = 0L;
        this.f3609f = false;
        this.f3610g = 1000;
        this.f3611h = new a();
    }
}
