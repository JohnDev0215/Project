package b.a.a.i.o;

import android.os.Handler;
import android.os.Looper;
import android.os.SystemClock;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.view.animation.Interpolator;
import b.a.a.i.o.d;

/* JADX INFO: compiled from: caradapter-bug */
/* JADX INFO: loaded from: classes.dex */
public class e extends d.g {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static final Handler f941a = new Handler(Looper.getMainLooper());

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public long f942b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public boolean f943c;
    public Interpolator g;
    public d.g.a h;
    public d.g.b i;
    public float j;
    public final int[] d = new int[2];
    public final float[] e = new float[2];
    public int f = 200;
    public final Runnable k = new a();

    /* JADX INFO: compiled from: caradapter-bug */
    public class a implements Runnable {
        public a() {
        }

        @Override // java.lang.Runnable
        public void run() {
            e.this.n();
        }
    }

    @Override // b.a.a.i.o.d.g
    public void a() {
        this.f943c = false;
        f941a.removeCallbacks(this.k);
        d.g.a aVar = this.h;
        if (aVar != null) {
            aVar.onAnimationCancel();
        }
    }

    @Override // b.a.a.i.o.d.g
    public float b() {
        return this.j;
    }

    @Override // b.a.a.i.o.d.g
    public int c() {
        int[] iArr = this.d;
        return b.a.a.i.o.a.a(iArr[0], iArr[1], b());
    }

    @Override // b.a.a.i.o.d.g
    public long d() {
        return this.f;
    }

    @Override // b.a.a.i.o.d.g
    public boolean e() {
        return this.f943c;
    }

    @Override // b.a.a.i.o.d.g
    public void f(int i) {
        this.f = i;
    }

    @Override // b.a.a.i.o.d.g
    public void g(float f, float f2) {
        float[] fArr = this.e;
        fArr[0] = f;
        fArr[1] = f2;
    }

    @Override // b.a.a.i.o.d.g
    public void h(int i, int i2) {
        int[] iArr = this.d;
        iArr[0] = i;
        iArr[1] = i2;
    }

    @Override // b.a.a.i.o.d.g
    public void i(Interpolator interpolator) {
        this.g = interpolator;
    }

    @Override // b.a.a.i.o.d.g
    public void j(d.g.a aVar) {
        this.h = aVar;
    }

    @Override // b.a.a.i.o.d.g
    public void k(d.g.b bVar) {
        this.i = bVar;
    }

    @Override // b.a.a.i.o.d.g
    public void l() {
        if (this.f943c) {
            return;
        }
        if (this.g == null) {
            this.g = new AccelerateDecelerateInterpolator();
        }
        this.f942b = SystemClock.uptimeMillis();
        this.f943c = true;
        d.g.a aVar = this.h;
        if (aVar != null) {
            aVar.a();
        }
        f941a.postDelayed(this.k, 10L);
    }

    public final void n() {
        if (this.f943c) {
            float fUptimeMillis = (SystemClock.uptimeMillis() - this.f942b) / this.f;
            Interpolator interpolator = this.g;
            if (interpolator != null) {
                fUptimeMillis = interpolator.getInterpolation(fUptimeMillis);
            }
            this.j = fUptimeMillis;
            d.g.b bVar = this.i;
            if (bVar != null) {
                bVar.a();
            }
            if (SystemClock.uptimeMillis() >= this.f942b + ((long) this.f)) {
                this.f943c = false;
                d.g.a aVar = this.h;
                if (aVar != null) {
                    aVar.onAnimationEnd();
                }
            }
        }
        if (this.f943c) {
            f941a.postDelayed(this.k, 10L);
        }
    }
}
