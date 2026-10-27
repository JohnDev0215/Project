package defpackage;

import android.animation.Animator;
import android.animation.TimeInterpolator;
import android.provider.Settings;
import android.util.FloatProperty;
import android.view.animation.BaseInterpolator;
import android.view.animation.Interpolator;
import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.Iterator;
import org.chromium.base.b;
import org.chromium.ui.modelutil.PropertyModel;

/* JADX INFO: compiled from: CompositorAnimator.java */
/* JADX INFO: loaded from: classes.dex */
public final class kw extends Animator {
    public static float r = 1.0f;
    public final WeakReference<ew> e;
    public long j;
    public float k;
    public nw2<Float> l;
    public nw2<Float> m;
    public long n;
    public long o;
    public boolean q;
    public final b<Animator.AnimatorListener> f = new b<>();
    public final ArrayList<a> g = new ArrayList<>();
    public final ArrayList<a> h = new ArrayList<>();
    public int p = 3;
    public TimeInterpolator i = h21.a;

    /* JADX INFO: compiled from: CompositorAnimator.java */
    public interface a {
        void a(kw kwVar);
    }

    public kw(ew ewVar) {
        this.e = new WeakReference<>(ewVar);
        iw iwVar = new iw(0.0f);
        fw fwVar = new fw(1.0f, 1);
        this.l = iwVar;
        this.m = fwVar;
        float f = Settings.Global.getFloat(xz.a.getContentResolver(), "animator_duration_scale", r);
        r = f;
        if (f != 1.0f) {
            fa1.g("CompositorAnimator", "Settings.Global.ANIMATOR_DURATION_SCALE = %f", Float.valueOf(f));
        }
    }

    public static kw e(ew ewVar, float f, float f2, long j, a aVar) {
        kw kwVar = new kw(ewVar);
        iw iwVar = new iw(f);
        fw fwVar = new fw(f2, 1);
        kwVar.l = iwVar;
        kwVar.m = fwVar;
        if (aVar != null) {
            kwVar.g.add(aVar);
        }
        if (j < 0) {
            j = 0;
        }
        kwVar.n = j;
        return kwVar;
    }

    public static <T> kw g(ew ewVar, T t, FloatProperty<T> floatProperty, float f, float f2, long j) {
        return h(ewVar, t, floatProperty, f, f2, j, h21.a);
    }

    public static kw h(ew ewVar, final Object obj, final FloatProperty floatProperty, float f, float f2, long j, BaseInterpolator baseInterpolator) {
        kw kwVar = new kw(ewVar);
        iw iwVar = new iw(f);
        fw fwVar = new fw(f2, 1);
        kwVar.l = iwVar;
        kwVar.m = fwVar;
        if (j < 0) {
            j = 0;
        }
        kwVar.n = j;
        kwVar.g.add(new a() { // from class: jw
            @Override // kw.a
            public final void a(kw kwVar2) {
                floatProperty.setValue(obj, kwVar2.b());
            }
        });
        kwVar.i = baseInterpolator;
        return kwVar;
    }

    public static kw i(ew ewVar, PropertyModel propertyModel, PropertyModel.o oVar, float f, float f2, long j) {
        return j(ewVar, propertyModel, oVar, f, f2, j, h21.a);
    }

    public static kw j(ew ewVar, PropertyModel propertyModel, PropertyModel.o oVar, float f, final float f2, long j, Interpolator interpolator) {
        return k(ewVar, propertyModel, oVar, new fw(f, 0), new nw2() { // from class: gw
            @Override // defpackage.nw2
            public final Object get() {
                return Float.valueOf(f2);
            }
        }, j, interpolator);
    }

    public static kw k(ew ewVar, final PropertyModel propertyModel, final PropertyModel.o oVar, nw2 nw2Var, nw2 nw2Var2, long j, Interpolator interpolator) {
        kw kwVar = new kw(ewVar);
        kwVar.l = nw2Var;
        kwVar.m = nw2Var2;
        if (j < 0) {
            j = 0;
        }
        kwVar.n = j;
        kwVar.g.add(new a() { // from class: hw
            @Override // kw.a
            public final void a(kw kwVar2) {
                propertyModel.m(oVar, kwVar2.b());
            }
        });
        kwVar.i = interpolator;
        return kwVar;
    }

    @Override // android.animation.Animator
    public final void addListener(Animator.AnimatorListener animatorListener) {
        this.f.a(animatorListener);
    }

    public final float b() {
        return ((this.m.get().floatValue() - this.l.get().floatValue()) * this.k) + this.l.get().floatValue();
    }

    @Override // android.animation.Animator
    public final void cancel() {
        if (this.p == 3) {
            return;
        }
        this.p = 2;
        super.cancel();
        Iterator<Animator.AnimatorListener> it = this.f.iterator();
        while (true) {
            b.a aVar = (b.a) it;
            if (!aVar.hasNext()) {
                end();
                return;
            }
            ((Animator.AnimatorListener) aVar.next()).onAnimationCancel(this);
        }
    }

    @Override // android.animation.Animator
    public final void end() {
        if (this.p == 3) {
            return;
        }
        super.end();
        boolean z = this.p == 2;
        this.p = 3;
        if (!this.q && !z) {
            this.k = 1.0f;
            Iterator<a> it = this.g.iterator();
            while (it.hasNext()) {
                it.next().a(this);
            }
        }
        Iterator<Animator.AnimatorListener> it2 = this.f.iterator();
        while (true) {
            b.a aVar = (b.a) it2;
            if (!aVar.hasNext()) {
                return;
            } else {
                ((Animator.AnimatorListener) aVar.next()).onAnimationEnd(this);
            }
        }
    }

    @Override // android.animation.Animator
    public final long getDuration() {
        return this.n;
    }

    @Override // android.animation.Animator
    public final long getStartDelay() {
        return this.o;
    }

    @Override // android.animation.Animator
    public final boolean isRunning() {
        return this.p == 1;
    }

    @Override // android.animation.Animator
    public final void removeAllListeners() {
        this.f.clear();
        this.g.clear();
    }

    @Override // android.animation.Animator
    public final void removeListener(Animator.AnimatorListener animatorListener) {
        this.f.c(animatorListener);
    }

    @Override // android.animation.Animator
    public final Animator setDuration(long j) {
        if (j < 0) {
            j = 0;
        }
        this.n = j;
        return this;
    }

    @Override // android.animation.Animator
    public final void setInterpolator(TimeInterpolator timeInterpolator) {
        this.i = timeInterpolator;
    }

    @Override // android.animation.Animator
    public final void setStartDelay(long j) {
        if (j < 0) {
            j = 0;
        }
        this.o = j;
    }

    @Override // android.animation.Animator
    public final void start() {
        if (this.p != 3) {
            return;
        }
        super.start();
        this.p = 1;
        this.q = false;
        ew ewVar = this.e.get();
        if (ewVar != null) {
            ArrayList<kw> arrayList = ewVar.a;
            if (arrayList.size() <= 0) {
                ewVar.e = System.currentTimeMillis();
            }
            addListener(new dw(ewVar, this));
            arrayList.add(this);
            if (!ewVar.d) {
                ewVar.b.run();
                ewVar.d = true;
            }
        }
        this.j = 0L;
        Iterator<Animator.AnimatorListener> it = this.f.iterator();
        while (true) {
            b.a aVar = (b.a) it;
            if (!aVar.hasNext()) {
                return;
            } else {
                ((Animator.AnimatorListener) aVar.next()).onAnimationStart(this);
            }
        }
    }
}
