package com.byd.uereport.widget;

import android.content.Context;
import android.content.res.TypedArray;
import android.os.Handler;
import android.os.Message;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import com.byd.uereport.R;
import java.lang.ref.WeakReference;
import java.util.Timer;
import java.util.TimerTask;

/* JADX INFO: loaded from: classes2.dex */
public class CountDownTimerButton extends Button implements View.OnClickListener {

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public static volatile long f3395d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public Context f3396e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public Timer f3397f;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public TimerTask f3398g;

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    public long f3399h;

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public String f3400i;

    /* JADX INFO: renamed from: j, reason: collision with root package name */
    public boolean f3401j;

    /* JADX INFO: renamed from: k, reason: collision with root package name */
    public b f3402k;

    /* JADX INFO: renamed from: l, reason: collision with root package name */
    public String f3403l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public c f3404m;

    public class a extends TimerTask {
        public a() {
        }

        @Override // java.util.TimerTask, java.lang.Runnable
        public void run() {
            CountDownTimerButton.this.f3402k.sendEmptyMessage(1);
        }
    }

    public class b extends Handler {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public WeakReference<Context> f3406a;

        public b(Context context) {
            this.f3406a = new WeakReference<>(context);
        }

        @Override // android.os.Handler
        public void handleMessage(Message message) {
            super.handleMessage(message);
            StringBuilder sb = new StringBuilder();
            sb.append(CountDownTimerButton.this.f3400i);
            if (CountDownTimerButton.this.f3401j) {
                sb.append("(");
                sb.append(CountDownTimerButton.f3395d);
                sb.append("S");
                sb.append(")");
            }
            CountDownTimerButton.this.setText(sb);
            CountDownTimerButton.d(1L);
            if (CountDownTimerButton.f3395d < 0) {
                CountDownTimerButton countDownTimerButton = CountDownTimerButton.this;
                countDownTimerButton.setText(countDownTimerButton.f3400i);
                CountDownTimerButton.this.l();
                if (CountDownTimerButton.this.f3404m != null) {
                    CountDownTimerButton.this.f3404m.b();
                    CountDownTimerButton.j();
                }
            }
        }
    }

    public interface c {
        void a();

        void b();
    }

    public CountDownTimerButton(Context context) {
        super(context);
        this.f3399h = 5L;
        this.f3403l = CountDownTimerButton.class.getSimpleName();
        h(context, null);
    }

    public static /* synthetic */ long d(long j8) {
        long j9 = f3395d - j8;
        f3395d = j9;
        return j9;
    }

    public static void j() {
        f3395d = 0L;
    }

    public final void g(AttributeSet attributeSet) {
        TypedArray typedArrayObtainStyledAttributes = getContext().obtainStyledAttributes(attributeSet, R.styleable.CountDownTimerTextView);
        this.f3399h = typedArrayObtainStyledAttributes.getInt(1, 5);
        this.f3400i = typedArrayObtainStyledAttributes.getString(2);
        this.f3401j = typedArrayObtainStyledAttributes.getBoolean(0, true);
        typedArrayObtainStyledAttributes.recycle();
    }

    public final void h(Context context, AttributeSet attributeSet) {
        this.f3396e = context;
        this.f3402k = new b(context);
        setOnClickListener(this);
        g(attributeSet);
        k();
    }

    public void i() {
        j();
        k();
    }

    public final void k() {
        if (f3395d <= 0) {
            f3395d = this.f3399h;
        }
        this.f3397f = new Timer();
        a aVar = new a();
        this.f3398g = aVar;
        this.f3397f.schedule(aVar, 0L, 1000L);
    }

    public void l() {
        TimerTask timerTask = this.f3398g;
        if (timerTask != null) {
            timerTask.cancel();
            this.f3398g = null;
        }
        Timer timer = this.f3397f;
        if (timer != null) {
            timer.cancel();
            this.f3397f = null;
        }
        b bVar = this.f3402k;
        if (bVar != null) {
            bVar.removeMessages(1);
        }
    }

    @Override // android.view.View.OnClickListener
    public void onClick(View view) {
        Log.e(this.f3403l, "onClick: mListener.onCancelClick()");
        c cVar = this.f3404m;
        if (cVar != null) {
            cVar.a();
            j();
        }
        l();
    }

    public void setBtnBefforText(String str) {
        this.f3400i = str;
    }

    public void setDuration(long j8) {
        this.f3399h = j8;
    }

    public void setOnBtnCallBackListener(c cVar) {
        this.f3404m = cVar;
    }

    public CountDownTimerButton(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.f3399h = 5L;
        this.f3403l = CountDownTimerButton.class.getSimpleName();
        h(context, attributeSet);
    }
}
