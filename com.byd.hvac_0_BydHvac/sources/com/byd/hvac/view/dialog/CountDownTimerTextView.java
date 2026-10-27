package com.byd.hvac.view.dialog;

import android.content.Context;
import android.content.res.TypedArray;
import android.os.Handler;
import android.os.Message;
import android.util.AttributeSet;
import android.view.View;
import androidx.annotation.NonNull;
import androidx.appcompat.widget.AppCompatTextView;
import com.byd.hvac.R;
import java.lang.ref.WeakReference;
import java.util.Timer;
import java.util.TimerTask;

/* JADX INFO: compiled from: caradapter-bug */
/* JADX INFO: loaded from: classes.dex */
public class CountDownTimerTextView extends AppCompatTextView implements View.OnClickListener {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public Context f1054a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public Timer f1055b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public TimerTask f1056c;
    public long d;
    public long e;
    public String f;
    public b g;
    public String h;
    public c i;

    /* JADX INFO: compiled from: caradapter-bug */
    public class a extends TimerTask {
        public a() {
        }

        @Override // java.util.TimerTask, java.lang.Runnable
        public void run() {
            CountDownTimerTextView.this.g.sendEmptyMessage(1);
        }
    }

    /* JADX INFO: compiled from: caradapter-bug */
    public class b extends Handler {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public WeakReference<Context> f1058a;

        public b(Context context) {
            this.f1058a = new WeakReference<>(context);
        }

        @Override // android.os.Handler
        public void handleMessage(@NonNull Message message) {
            super.handleMessage(message);
            StringBuilder sb = new StringBuilder();
            sb.append(CountDownTimerTextView.this.f);
            sb.append(" (");
            sb.append(CountDownTimerTextView.this.e);
            sb.append("s");
            sb.append(")");
            CountDownTimerTextView.this.setText(sb);
            CountDownTimerTextView.c(CountDownTimerTextView.this, 1L);
            if (CountDownTimerTextView.this.e < 0) {
                CountDownTimerTextView countDownTimerTextView = CountDownTimerTextView.this;
                countDownTimerTextView.setText(countDownTimerTextView.f);
                CountDownTimerTextView.this.j();
                if (CountDownTimerTextView.this.i != null) {
                    CountDownTimerTextView.this.i.a();
                }
            }
        }
    }

    /* JADX INFO: compiled from: caradapter-bug */
    public interface c {
        void a();

        void b();
    }

    public CountDownTimerTextView(Context context) {
        super(context);
        this.d = 8L;
        this.f = "倒计时开始";
        this.h = CountDownTimerTextView.class.getSimpleName();
        h(context, null);
    }

    public static /* synthetic */ long c(CountDownTimerTextView countDownTimerTextView, long j) {
        long j2 = countDownTimerTextView.e - j;
        countDownTimerTextView.e = j2;
        return j2;
    }

    public final void g(AttributeSet attributeSet) {
        TypedArray typedArrayObtainStyledAttributes = getContext().obtainStyledAttributes(attributeSet, R.styleable.CountDownTimerTextView);
        this.d = typedArrayObtainStyledAttributes.getInt(0, 8);
        this.f = typedArrayObtainStyledAttributes.getString(1);
        typedArrayObtainStyledAttributes.recycle();
    }

    public final void h(Context context, AttributeSet attributeSet) {
        this.f1054a = context;
        this.g = new b(context);
        setOnClickListener(this);
        g(attributeSet);
        i();
    }

    public final void i() {
        this.e = this.d;
        this.f1055b = new Timer();
        a aVar = new a();
        this.f1056c = aVar;
        this.f1055b.schedule(aVar, 0L, 1000L);
    }

    public final void j() {
        TimerTask timerTask = this.f1056c;
        if (timerTask != null) {
            timerTask.cancel();
            this.f1056c = null;
        }
        Timer timer = this.f1055b;
        if (timer != null) {
            timer.cancel();
            this.f1055b = null;
        }
    }

    public void k() {
        j();
    }

    @Override // android.view.View.OnClickListener
    public void onClick(View view) {
        this.i.b();
        j();
    }

    public void setBtnBefforText(String str) {
        this.f = str;
    }

    public void setDuration(long j) {
        this.d = j;
    }

    public void setOnBtnCallBackListener(c cVar) {
        this.i = cVar;
    }

    public CountDownTimerTextView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.d = 8L;
        this.f = "倒计时开始";
        this.h = CountDownTimerTextView.class.getSimpleName();
        h(context, attributeSet);
    }
}
