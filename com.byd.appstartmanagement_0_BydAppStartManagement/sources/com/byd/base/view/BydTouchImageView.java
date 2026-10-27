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

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public static Handler f555b = new Handler(Looper.getMainLooper());

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public long f556c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public long f557d;
    public Runnable e;
    public Runnable f;
    public c g;

    public class a implements Runnable {
        public a() {
        }

        @Override // java.lang.Runnable
        public void run() {
            BydLogHelper.logD("upRunable");
            if (BydTouchImageView.this.g != null) {
                BydTouchImageView.this.g.c(BydTouchImageView.this.getId());
            }
            BydTouchImageView.f555b.postDelayed(this, 300L);
        }
    }

    public class b implements Runnable {
        public b() {
        }

        @Override // java.lang.Runnable
        public void run() {
            BydLogHelper.logD("invalidRun");
            if (BydTouchImageView.this.g != null) {
                BydTouchImageView.this.g.a(BydTouchImageView.this.getId());
            }
        }
    }

    public interface c {
        void a(int i);

        void b(int i);

        void c(int i);

        void d(int i);

        void e(int i);
    }

    public BydTouchImageView(Context context) {
        super(context);
        this.f556c = 300L;
        this.f557d = 0L;
        this.e = new a();
        this.f = new b();
    }

    @Override // android.view.View
    public boolean onTouchEvent(MotionEvent motionEvent) {
        c cVar;
        super.onTouchEvent(motionEvent);
        int action = motionEvent.getAction();
        if (action == 0) {
            f555b.removeCallbacks(this.f);
            this.f557d = System.currentTimeMillis();
            c cVar2 = this.g;
            if (cVar2 != null) {
                cVar2.e(getId());
                f555b.removeCallbacks(this.e);
                f555b.post(this.e);
                BydLogHelper.logD("MotionEvent.ACTION_DOWN : getId() " + f555b);
            }
        } else if (action != 1) {
            if (action == 2 && (cVar = this.g) != null) {
                cVar.b(getId());
                BydLogHelper.logD("MotionEvent.ACTION_MOVE : getId() " + getId());
            }
        } else if (this.g != null) {
            f555b.removeCallbacks(this.e);
            if (System.currentTimeMillis() - this.f557d >= 300) {
                this.g.d(getId());
                this.g.a(getId());
            } else {
                f555b.postDelayed(this.f, 300L);
                this.g.d(getId());
            }
            BydLogHelper.logD("MotionEvent.ACTION_UP : getId() " + getId());
        }
        return true;
    }

    public void setIvTouchListener(c cVar) {
        this.g = cVar;
    }

    public BydTouchImageView(Context context, @Nullable AttributeSet attributeSet) {
        super(context, attributeSet);
        this.f556c = 300L;
        this.f557d = 0L;
        this.e = new a();
        this.f = new b();
    }

    public BydTouchImageView(Context context, @Nullable AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        this.f556c = 300L;
        this.f557d = 0L;
        this.e = new a();
        this.f = new b();
    }
}
