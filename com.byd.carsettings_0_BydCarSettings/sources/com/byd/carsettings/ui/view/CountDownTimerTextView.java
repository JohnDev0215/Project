package com.byd.carsettings.ui.view;

import android.content.Context;
import android.content.res.TypedArray;
import android.os.Handler;
import android.os.Message;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import androidx.appcompat.widget.AppCompatTextView;
import com.byd.carsettings.R;
import java.lang.ref.WeakReference;
import java.util.Timer;
import java.util.TimerTask;

/* JADX INFO: loaded from: classes2.dex */
public class CountDownTimerTextView extends AppCompatTextView implements View.OnClickListener {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public Context f3295a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public Timer f3296b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public TimerTask f3297c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public long f3298d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public long f3299e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public String f3300f;
    public boolean g;
    public b h;
    public String i;
    public c j;

    public class a extends TimerTask {
        public a() {
        }

        @Override // java.util.TimerTask, java.lang.Runnable
        public void run() {
            CountDownTimerTextView.this.h.sendEmptyMessage(1);
        }
    }

    public class b extends Handler {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public WeakReference<Context> f3302a;

        public b(Context context) {
            this.f3302a = new WeakReference<>(context);
        }

        @Override // android.os.Handler
        public void handleMessage(Message message) {
            super.handleMessage(message);
            StringBuilder sb = new StringBuilder();
            sb.append(CountDownTimerTextView.this.f3300f);
            if (CountDownTimerTextView.this.g) {
                sb.append("(");
                sb.append(CountDownTimerTextView.this.f3299e);
                sb.append(")");
            }
            CountDownTimerTextView.this.setText(sb);
            CountDownTimerTextView.this.f3299e--;
            if (CountDownTimerTextView.this.f3299e < 0) {
                CountDownTimerTextView countDownTimerTextView = CountDownTimerTextView.this;
                countDownTimerTextView.setText(countDownTimerTextView.f3300f);
                CountDownTimerTextView.this.k();
                CountDownTimerTextView.this.j.b();
            }
        }
    }

    public interface c {
        void a();

        void b();
    }

    public CountDownTimerTextView(Context context) {
        super(context);
        this.f3298d = 8L;
        this.i = CountDownTimerTextView.class.getSimpleName();
        i(context, null);
    }

    public final void h(AttributeSet attributeSet) {
        TypedArray typedArrayObtainStyledAttributes = getContext().obtainStyledAttributes(attributeSet, R.styleable.CountDownTimerTextView);
        this.f3298d = typedArrayObtainStyledAttributes.getInt(1, 8);
        this.f3300f = typedArrayObtainStyledAttributes.getString(2);
        this.g = typedArrayObtainStyledAttributes.getBoolean(0, true);
        typedArrayObtainStyledAttributes.recycle();
    }

    public final void i(Context context, AttributeSet attributeSet) {
        this.f3295a = context;
        this.h = new b(context);
        setOnClickListener(this);
        h(attributeSet);
        j();
    }

    public final void j() {
        this.f3299e = this.f3298d;
        this.f3296b = new Timer();
        a aVar = new a();
        this.f3297c = aVar;
        this.f3296b.schedule(aVar, 0L, 1000L);
    }

    public final void k() {
        TimerTask timerTask = this.f3297c;
        if (timerTask != null) {
            timerTask.cancel();
            this.f3297c = null;
        }
        Timer timer = this.f3296b;
        if (timer != null) {
            timer.cancel();
            this.f3296b = null;
        }
    }

    @Override // android.view.View.OnClickListener
    public void onClick(View view) {
        Log.d(this.i, "onClick: mListener.onCancelClick()");
        this.j.a();
        k();
    }

    public void setBtnBefforText(String str) {
        this.f3300f = str;
    }

    public void setDuration(long j) {
        this.f3298d = j;
    }

    public void setOnBtnCallBackListener(c cVar) {
        this.j = cVar;
    }

    public CountDownTimerTextView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.f3298d = 8L;
        this.i = CountDownTimerTextView.class.getSimpleName();
        i(context, attributeSet);
    }
}
