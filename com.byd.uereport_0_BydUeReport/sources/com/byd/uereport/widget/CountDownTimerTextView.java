package com.byd.uereport.widget;

import android.content.Context;
import android.content.res.TypedArray;
import android.os.Handler;
import android.os.Message;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import androidx.appcompat.widget.AppCompatTextView;
import com.byd.uereport.R;
import java.lang.ref.WeakReference;
import java.util.Timer;
import java.util.TimerTask;

/* JADX INFO: loaded from: classes2.dex */
public class CountDownTimerTextView extends AppCompatTextView implements View.OnClickListener {

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    public Context f3408h;

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public Timer f3409i;

    /* JADX INFO: renamed from: j, reason: collision with root package name */
    public TimerTask f3410j;

    /* JADX INFO: renamed from: k, reason: collision with root package name */
    public long f3411k;

    /* JADX INFO: renamed from: l, reason: collision with root package name */
    public long f3412l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public String f3413m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public boolean f3414n;

    /* JADX INFO: renamed from: o, reason: collision with root package name */
    public b f3415o;

    /* JADX INFO: renamed from: p, reason: collision with root package name */
    public String f3416p;

    /* JADX INFO: renamed from: q, reason: collision with root package name */
    public c f3417q;

    public class a extends TimerTask {
        public a() {
        }

        @Override // java.util.TimerTask, java.lang.Runnable
        public void run() {
            CountDownTimerTextView.this.f3415o.sendEmptyMessage(1);
        }
    }

    public class b extends Handler {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public WeakReference<Context> f3419a;

        public b(Context context) {
            this.f3419a = new WeakReference<>(context);
        }

        @Override // android.os.Handler
        public void handleMessage(Message message) {
            super.handleMessage(message);
            StringBuilder sb = new StringBuilder();
            sb.append(CountDownTimerTextView.this.f3413m);
            if (CountDownTimerTextView.this.f3414n) {
                sb.append("(");
                sb.append(CountDownTimerTextView.this.f3412l);
                sb.append(")");
            }
            CountDownTimerTextView.this.setText(sb);
            CountDownTimerTextView.i(CountDownTimerTextView.this, 1L);
            if (CountDownTimerTextView.this.f3412l < 0) {
                CountDownTimerTextView countDownTimerTextView = CountDownTimerTextView.this;
                countDownTimerTextView.setText(countDownTimerTextView.f3413m);
                CountDownTimerTextView.this.p();
                if (CountDownTimerTextView.this.f3417q != null) {
                    CountDownTimerTextView.this.f3417q.b();
                }
            }
        }
    }

    public interface c {
        void a();

        void b();
    }

    public CountDownTimerTextView(Context context) {
        super(context);
        this.f3411k = 8L;
        this.f3416p = CountDownTimerTextView.class.getSimpleName();
        n(context, null);
    }

    public static /* synthetic */ long i(CountDownTimerTextView countDownTimerTextView, long j8) {
        long j9 = countDownTimerTextView.f3412l - j8;
        countDownTimerTextView.f3412l = j9;
        return j9;
    }

    public final void m(AttributeSet attributeSet) {
        TypedArray typedArrayObtainStyledAttributes = getContext().obtainStyledAttributes(attributeSet, R.styleable.CountDownTimerTextView);
        this.f3411k = typedArrayObtainStyledAttributes.getInt(1, 8);
        this.f3413m = typedArrayObtainStyledAttributes.getString(2);
        this.f3414n = typedArrayObtainStyledAttributes.getBoolean(0, true);
        typedArrayObtainStyledAttributes.recycle();
    }

    public final void n(Context context, AttributeSet attributeSet) {
        this.f3408h = context;
        this.f3415o = new b(context);
        setOnClickListener(this);
        m(attributeSet);
        o();
    }

    public final void o() {
        this.f3412l = this.f3411k;
        this.f3409i = new Timer();
        a aVar = new a();
        this.f3410j = aVar;
        this.f3409i.schedule(aVar, 0L, 1000L);
    }

    @Override // android.view.View.OnClickListener
    public void onClick(View view) {
        Log.d(this.f3416p, "onClick: mListener.onCancelClick()");
        c cVar = this.f3417q;
        if (cVar != null) {
            cVar.a();
        }
        p();
    }

    public final void p() {
        TimerTask timerTask = this.f3410j;
        if (timerTask != null) {
            timerTask.cancel();
            this.f3410j = null;
        }
        Timer timer = this.f3409i;
        if (timer != null) {
            timer.cancel();
            this.f3409i = null;
        }
    }

    public void setBtnBefforText(String str) {
        this.f3413m = str;
    }

    public void setDuration(long j8) {
        this.f3411k = j8;
    }

    public void setOnBtnCallBackListener(c cVar) {
        this.f3417q = cVar;
    }

    public CountDownTimerTextView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.f3411k = 8L;
        this.f3416p = CountDownTimerTextView.class.getSimpleName();
        n(context, attributeSet);
    }
}
