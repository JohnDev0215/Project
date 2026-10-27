package t;

import androidx.constraintlayout.motion.widget.o;
import p.k;
import p.m;
import p.n;

/* JADX INFO: compiled from: StopLogic.java */
/* JADX INFO: loaded from: classes.dex */
public class b extends o {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private n f9521a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private k f9522b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    private m f9523c;

    public b() {
        n nVar = new n();
        this.f9521a = nVar;
        this.f9523c = nVar;
    }

    @Override // androidx.constraintlayout.motion.widget.o
    public float a() {
        return this.f9523c.b();
    }

    public void b(float f7, float f8, float f9, float f10, float f11, float f12) {
        n nVar = this.f9521a;
        this.f9523c = nVar;
        nVar.d(f7, f8, f9, f10, f11, f12);
    }

    public boolean c() {
        return this.f9523c.a();
    }

    public void d(float f7, float f8, float f9, float f10, float f11, float f12, float f13, int i7) {
        if (this.f9522b == null) {
            this.f9522b = new k();
        }
        k kVar = this.f9522b;
        this.f9523c = kVar;
        kVar.d(f7, f8, f9, f10, f11, f12, f13, i7);
    }

    @Override // android.animation.TimeInterpolator
    public float getInterpolation(float f7) {
        return this.f9523c.getInterpolation(f7);
    }
}
