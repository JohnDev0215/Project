package v1;

/* JADX INFO: compiled from: GradientColor.java */
/* JADX INFO: loaded from: classes.dex */
public class c {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final float[] f8517a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final int[] f8518b;

    public c(float[] fArr, int[] iArr) {
        this.f8517a = fArr;
        this.f8518b = iArr;
    }

    public int[] a() {
        return this.f8518b;
    }

    public float[] b() {
        return this.f8517a;
    }

    public int c() {
        return this.f8518b.length;
    }

    public void d(c cVar, c cVar2, float f8) {
        if (cVar.f8518b.length == cVar2.f8518b.length) {
            for (int i8 = 0; i8 < cVar.f8518b.length; i8++) {
                this.f8517a[i8] = a2.g.j(cVar.f8517a[i8], cVar2.f8517a[i8], f8);
                this.f8518b[i8] = a2.b.c(f8, cVar.f8518b[i8], cVar2.f8518b[i8]);
            }
            return;
        }
        throw new IllegalArgumentException("Cannot interpolate between gradients. Lengths vary (" + cVar.f8518b.length + " vs " + cVar2.f8518b.length + ")");
    }
}
