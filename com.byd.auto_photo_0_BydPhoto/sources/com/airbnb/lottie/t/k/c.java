package com.airbnb.lottie.t.k;

/* JADX INFO: loaded from: classes.dex */
public class c {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private final float[] f816a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private final int[] f817b;

    public c(float[] fArr, int[] iArr) {
        this.f816a = fArr;
        this.f817b = iArr;
    }

    public int[] a() {
        return this.f817b;
    }

    public float[] b() {
        return this.f816a;
    }

    public int c() {
        return this.f817b.length;
    }

    public void d(c cVar, c cVar2, float f) {
        if (cVar.f817b.length == cVar2.f817b.length) {
            for (int i = 0; i < cVar.f817b.length; i++) {
                this.f816a[i] = com.airbnb.lottie.w.g.j(cVar.f816a[i], cVar2.f816a[i], f);
                this.f817b[i] = com.airbnb.lottie.w.b.c(f, cVar.f817b[i], cVar2.f817b[i]);
            }
            return;
        }
        throw new IllegalArgumentException("Cannot interpolate between gradients. Lengths vary (" + cVar.f817b.length + " vs " + cVar2.f817b.length + ")");
    }
}
