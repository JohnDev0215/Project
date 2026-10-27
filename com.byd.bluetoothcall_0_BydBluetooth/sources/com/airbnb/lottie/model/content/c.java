package com.airbnb.lottie.model.content;

/* JADX INFO: compiled from: GradientColor.java */
/* JADX INFO: loaded from: classes.dex */
public class c {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private final float[] f2948a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private final int[] f2949b;

    public c(float[] fArr, int[] iArr) {
        this.f2948a = fArr;
        this.f2949b = iArr;
    }

    public int[] a() {
        return this.f2949b;
    }

    public float[] b() {
        return this.f2948a;
    }

    public int c() {
        return this.f2949b.length;
    }

    public void d(c cVar, c cVar2, float f) {
        if (cVar.f2949b.length == cVar2.f2949b.length) {
            for (int i = 0; i < cVar.f2949b.length; i++) {
                this.f2948a[i] = com.airbnb.lottie.u.g.j(cVar.f2948a[i], cVar2.f2948a[i], f);
                this.f2949b[i] = com.airbnb.lottie.u.b.c(f, cVar.f2949b[i], cVar2.f2949b[i]);
            }
            return;
        }
        throw new IllegalArgumentException("Cannot interpolate between gradients. Lengths vary (" + cVar.f2949b.length + " vs " + cVar2.f2949b.length + ")");
    }
}
