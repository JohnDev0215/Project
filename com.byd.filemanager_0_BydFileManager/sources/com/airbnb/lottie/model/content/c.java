package com.airbnb.lottie.model.content;

/* JADX INFO: compiled from: GradientColor.java */
/* JADX INFO: loaded from: classes.dex */
public class c {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private final float[] f2564a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private final int[] f2565b;

    public c(float[] fArr, int[] iArr) {
        this.f2564a = fArr;
        this.f2565b = iArr;
    }

    public int[] a() {
        return this.f2565b;
    }

    public float[] b() {
        return this.f2564a;
    }

    public int c() {
        return this.f2565b.length;
    }

    public void d(c cVar, c cVar2, float f) {
        if (cVar.f2565b.length == cVar2.f2565b.length) {
            for (int i = 0; i < cVar.f2565b.length; i++) {
                this.f2564a[i] = com.airbnb.lottie.u.g.j(cVar.f2564a[i], cVar2.f2564a[i], f);
                this.f2565b[i] = com.airbnb.lottie.u.b.c(f, cVar.f2565b[i], cVar2.f2565b[i]);
            }
            return;
        }
        throw new IllegalArgumentException("Cannot interpolate between gradients. Lengths vary (" + cVar.f2565b.length + " vs " + cVar2.f2565b.length + ")");
    }
}
