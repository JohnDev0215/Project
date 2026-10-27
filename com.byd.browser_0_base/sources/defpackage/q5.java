package defpackage;

import android.animation.ValueAnimator;
import android.os.Build;
import android.os.Looper;
import android.view.Choreographer;
import java.util.ArrayList;

/* JADX INFO: compiled from: AnimationHandler.java */
/* JADX INFO: loaded from: classes.dex */
public final class q5 {
    public static final ThreadLocal<q5> i = new ThreadLocal<>();
    public final fq0 e;
    public c h;
    public final wm2<b, Long> a = new wm2<>();
    public final ArrayList<b> b = new ArrayList<>();
    public final a c = new a();
    public final wv d = new wv(4, this);
    public boolean f = false;
    public float g = 1.0f;

    /* JADX INFO: compiled from: AnimationHandler.java */
    public class a {
        public a() {
        }
    }

    /* JADX INFO: compiled from: AnimationHandler.java */
    public interface b {
        boolean a(long j);
    }

    /* JADX INFO: compiled from: AnimationHandler.java */
    public class c {
        public r5 a;

        public c() {
        }

        /* JADX WARN: Type inference failed for: r0v1, types: [android.animation.ValueAnimator$DurationScaleChangeListener, r5] */
        public final boolean a() {
            if (this.a != null) {
                return true;
            }
            ?? r0 = new ValueAnimator.DurationScaleChangeListener() { // from class: r5
                @Override // android.animation.ValueAnimator.DurationScaleChangeListener
                public final void onChanged(float f) {
                    q5.this.g = f;
                }
            };
            this.a = r0;
            return ValueAnimator.registerDurationScaleChangeListener(r0);
        }

        public final boolean b() {
            boolean zUnregisterDurationScaleChangeListener = ValueAnimator.unregisterDurationScaleChangeListener(this.a);
            this.a = null;
            return zUnregisterDurationScaleChangeListener;
        }
    }

    /* JADX INFO: compiled from: AnimationHandler.java */
    public static final class d implements fq0 {
        public final Choreographer a = Choreographer.getInstance();
        public final Looper b = Looper.myLooper();

        public final boolean a() {
            return Thread.currentThread() == this.b.getThread();
        }

        public final void b(Runnable runnable) {
            this.a.postFrameCallback(new s5(0, runnable));
        }
    }

    public q5(d dVar) {
        this.e = dVar;
    }

    public final void a(cr2 cr2Var) {
        ArrayList<b> arrayList = this.b;
        if (arrayList.size() == 0) {
            ((d) this.e).b(this.d);
            if (Build.VERSION.SDK_INT >= 33) {
                this.g = ValueAnimator.getDurationScale();
                if (this.h == null) {
                    this.h = new c();
                }
                this.h.a();
            }
        }
        if (arrayList.contains(cr2Var)) {
            return;
        }
        arrayList.add(cr2Var);
    }
}
