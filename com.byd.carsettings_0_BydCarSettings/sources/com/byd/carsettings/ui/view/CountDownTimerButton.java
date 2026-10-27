package com.byd.carsettings.ui.view;

import android.content.Context;
import android.content.res.TypedArray;
import android.os.Handler;
import android.os.Message;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import androidx.exifinterface.media.ExifInterface;
import b.b.g.q.d.q;
import com.byd.carsettings.R;
import java.lang.ref.WeakReference;
import java.util.Timer;
import java.util.TimerTask;

/* JADX INFO: loaded from: classes2.dex */
public class CountDownTimerButton extends Button implements View.OnClickListener {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static long f3286a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public Context f3287b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public Timer f3288c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public TimerTask f3289d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public long f3290e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public String f3291f;
    public boolean g;
    public b h;
    public String i;
    public q.c j;

    public class a extends TimerTask {
        public a() {
        }

        @Override // java.util.TimerTask, java.lang.Runnable
        public void run() {
            CountDownTimerButton.this.h.sendEmptyMessage(1);
        }
    }

    public class b extends Handler {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public WeakReference<Context> f3293a;

        public b(Context context) {
            this.f3293a = new WeakReference<>(context);
        }

        @Override // android.os.Handler
        public void handleMessage(Message message) {
            super.handleMessage(message);
            StringBuilder sb = new StringBuilder();
            sb.append(CountDownTimerButton.this.f3291f);
            if (CountDownTimerButton.this.g) {
                sb.append("(");
                sb.append(CountDownTimerButton.f3286a);
                sb.append(ExifInterface.LATITUDE_SOUTH);
                sb.append(")");
            }
            CountDownTimerButton.this.setText(sb);
            CountDownTimerButton.f3286a--;
            if (CountDownTimerButton.f3286a < 0) {
                CountDownTimerButton countDownTimerButton = CountDownTimerButton.this;
                countDownTimerButton.setText(countDownTimerButton.f3291f);
                CountDownTimerButton.this.k();
                if (CountDownTimerButton.this.j != null) {
                    CountDownTimerButton.this.j.b();
                    CountDownTimerButton.i();
                }
            }
        }
    }

    public CountDownTimerButton(Context context) {
        super(context);
        this.f3290e = 5L;
        this.i = CountDownTimerButton.class.getSimpleName();
        h(context, null);
    }

    public static long getTempDuration() {
        return f3286a;
    }

    public static void i() {
        f3286a = 0L;
    }

    public final void g(AttributeSet attributeSet) {
        TypedArray typedArrayObtainStyledAttributes = getContext().obtainStyledAttributes(attributeSet, R.styleable.CountDownTimerTextView);
        this.f3290e = typedArrayObtainStyledAttributes.getInt(1, 5);
        this.f3291f = typedArrayObtainStyledAttributes.getString(2);
        this.g = typedArrayObtainStyledAttributes.getBoolean(0, true);
        typedArrayObtainStyledAttributes.recycle();
    }

    public final void h(Context context, AttributeSet attributeSet) {
        this.f3287b = context;
        this.h = new b(context);
        setOnClickListener(this);
        g(attributeSet);
        j();
    }

    public final void j() {
        if (f3286a <= 0) {
            f3286a = this.f3290e;
        }
        this.f3288c = new Timer();
        a aVar = new a();
        this.f3289d = aVar;
        this.f3288c.schedule(aVar, 0L, 1000L);
    }

    public void k() {
        TimerTask timerTask = this.f3289d;
        if (timerTask != null) {
            timerTask.cancel();
            this.f3289d = null;
        }
        Timer timer = this.f3288c;
        if (timer != null) {
            timer.cancel();
            this.f3288c = null;
        }
        b bVar = this.h;
        if (bVar != null) {
            bVar.removeMessages(1);
        }
    }

    @Override // android.view.View.OnClickListener
    public void onClick(View view) {
        Log.e(this.i, "onClick: mListener.onCancelClick()");
        q.c cVar = this.j;
        if (cVar != null) {
            cVar.a();
            i();
        }
        k();
    }

    public void setBtnBefforText(String str) {
        this.f3291f = str;
    }

    public void setDuration(long j) {
        this.f3290e = j;
    }

    public void setOnBtnCallBackListener(q.c cVar) {
        this.j = cVar;
    }

    public CountDownTimerButton(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.f3290e = 5L;
        this.i = CountDownTimerButton.class.getSimpleName();
        h(context, attributeSet);
    }
}
