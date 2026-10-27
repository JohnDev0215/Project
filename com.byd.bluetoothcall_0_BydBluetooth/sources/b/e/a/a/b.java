package b.e.a.a;

import androidx.constraintlayout.core.j.a.k;
import androidx.constraintlayout.core.j.a.m;
import androidx.constraintlayout.core.j.a.n;
import androidx.constraintlayout.motion.widget.o;

/* JADX INFO: compiled from: StopLogic.java */
/* JADX INFO: loaded from: classes.dex */
public class b extends o {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private n f2154a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private k f2155b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    private m f2156c;

    public b() {
        n nVar = new n();
        this.f2154a = nVar;
        this.f2156c = nVar;
    }

    @Override // androidx.constraintlayout.motion.widget.o
    public float a() {
        return this.f2156c.b();
    }

    public void b(float f, float f2, float f3, float f4, float f5, float f6) {
        n nVar = this.f2154a;
        this.f2156c = nVar;
        nVar.d(f, f2, f3, f4, f5, f6);
    }

    public boolean c() {
        return this.f2156c.a();
    }

    public void d(float f, float f2, float f3, float f4, float f5, float f6, float f7, int i) {
        if (this.f2155b == null) {
            this.f2155b = new k();
        }
        k kVar = this.f2155b;
        this.f2156c = kVar;
        kVar.d(f, f2, f3, f4, f5, f6, f7, i);
    }

    @Override // android.animation.TimeInterpolator
    public float getInterpolation(float f) {
        return this.f2156c.getInterpolation(f);
    }
}
