package com.byd.base.view;

import android.content.Context;
import android.os.Handler;
import android.os.Looper;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.widget.ImageView;
import androidx.annotation.Nullable;
import com.bydux.api.common.tools.log.BydLogHelper;

/* JADX INFO: loaded from: classes.dex */
public class BydTouchImageView extends ImageView {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static Handler f2471a = new Handler(Looper.getMainLooper());

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public long f2472b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public long f2473c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public Runnable f2474d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public Runnable f2475e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public c f2476f;

    public class a implements Runnable {
        public a() {
        }

        @Override // java.lang.Runnable
        public void run() {
            BydLogHelper.logD("upRunable");
            if (BydTouchImageView.this.f2476f != null) {
                BydTouchImageView.this.f2476f.e(BydTouchImageView.this.getId());
            }
            BydTouchImageView.f2471a.postDelayed(this, 300L);
        }
    }

    public class b implements Runnable {
        public b() {
        }

        @Override // java.lang.Runnable
        public void run() {
            BydLogHelper.logD("invalidRun");
            if (BydTouchImageView.this.f2476f != null) {
                BydTouchImageView.this.f2476f.a(BydTouchImageView.this.getId());
            }
        }
    }

    public interface c {
        void a(int i);

        void e(int i);

        void f(int i);

        void i(int i);

        void move(int i);
    }

    public BydTouchImageView(Context context) {
        super(context);
        this.f2472b = 300L;
        this.f2473c = 0L;
        this.f2474d = new a();
        this.f2475e = new b();
    }

    @Override // android.view.View
    public boolean onTouchEvent(MotionEvent motionEvent) {
        c cVar;
        super.onTouchEvent(motionEvent);
        int action = motionEvent.getAction();
        if (action == 0) {
            f2471a.removeCallbacks(this.f2475e);
            this.f2473c = System.currentTimeMillis();
            c cVar2 = this.f2476f;
            if (cVar2 != null) {
                cVar2.i(getId());
                f2471a.removeCallbacks(this.f2474d);
                f2471a.post(this.f2474d);
                BydLogHelper.logD("MotionEvent.ACTION_DOWN : getId() " + f2471a);
            }
        } else if (action != 1) {
            if (action == 2 && (cVar = this.f2476f) != null) {
                cVar.move(getId());
                BydLogHelper.logD("MotionEvent.ACTION_MOVE : getId() " + getId());
            }
        } else if (this.f2476f != null) {
            f2471a.removeCallbacks(this.f2474d);
            if (System.currentTimeMillis() - this.f2473c >= 300) {
                this.f2476f.f(getId());
                this.f2476f.a(getId());
            } else {
                f2471a.postDelayed(this.f2475e, 300L);
                this.f2476f.f(getId());
            }
            BydLogHelper.logD("MotionEvent.ACTION_UP : getId() " + getId());
        }
        return true;
    }

    public void setIvTouchListener(c cVar) {
        this.f2476f = cVar;
    }

    public BydTouchImageView(Context context, @Nullable AttributeSet attributeSet) {
        super(context, attributeSet);
        this.f2472b = 300L;
        this.f2473c = 0L;
        this.f2474d = new a();
        this.f2475e = new b();
    }

    public BydTouchImageView(Context context, @Nullable AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        this.f2472b = 300L;
        this.f2473c = 0L;
        this.f2474d = new a();
        this.f2475e = new b();
    }
}
