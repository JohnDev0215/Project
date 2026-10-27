package F;

import java.util.List;

/* JADX INFO: loaded from: classes.dex */
public final class q {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final int f1947a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final p[] f1948b;
    public final r c;
    public final Object d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public final int f1949e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public final int f1950f;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public final int f1951g;

    public q(int i7, p[] pVarArr, r rVar, List list, int i10) {
        this.f1947a = i7;
        this.f1948b = pVarArr;
        this.c = rVar;
        this.d = list;
        this.f1949e = i10;
        int iMax = 0;
        for (p pVar : pVarArr) {
            iMax = Math.max(iMax, pVar.o);
        }
        this.f1950f = iMax;
        int i11 = iMax + this.f1949e;
        this.f1951g = i11 >= 0 ? i11 : 0;
    }

    /* JADX WARN: Type inference failed for: r6v0, types: [java.lang.Object, java.util.List] */
    public final p[] a(int i7, int i10, int i11) {
        p[] pVarArr = this.f1948b;
        int length = pVarArr.length;
        int i12 = 0;
        int i13 = 0;
        int i14 = 0;
        while (i12 < length) {
            p pVar = pVarArr[i12];
            int i15 = i13 + 1;
            int i16 = (int) ((b) this.d.get(i13)).f1864a;
            pVar.a(i7, this.c.f1953b[i14], i10, i11, this.f1947a, i14);
            i14 += i16;
            i12++;
            i13 = i15;
        }
        return pVarArr;
    }
}
