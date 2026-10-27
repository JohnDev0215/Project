package N0;

import a.AbstractC0904a;

/* JADX INFO: loaded from: classes.dex */
public abstract class v {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static final long f4416a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public static final /* synthetic */ int f4417b = 0;

    static {
        a1.n[] nVarArr = a1.m.f11041b;
        f4416a = a1.m.c;
    }

    public static final u a(u uVar, int i7, int i10, long j3, Y0.o oVar, w wVar, Y0.g gVar, int i11, int i12, Y0.p pVar) {
        long j10;
        int i13 = i7;
        int i14 = i10;
        Y0.o oVar2 = oVar;
        w wVar2 = wVar;
        Y0.g gVar2 = gVar;
        int i15 = i11;
        int i16 = i12;
        Y0.p pVar2 = pVar;
        if (Y0.i.a(i7, Integer.MIN_VALUE) || Y0.i.a(i7, uVar.f4409a)) {
            if (AbstractC0904a.L(j3)) {
                j10 = j3;
            } else {
                j10 = j3;
                if (a1.m.a(j10, uVar.c)) {
                }
            }
            if ((oVar2 == null || oVar2.equals(uVar.d)) && ((Y0.k.a(i14, Integer.MIN_VALUE) || Y0.k.a(i14, uVar.f4410b)) && ((wVar2 == null || wVar2.equals(uVar.f4411e)) && ((gVar2 == null || gVar2.equals(uVar.f4412f)) && ((i15 == 0 || i15 == uVar.f4413g) && ((Y0.d.a(i16, Integer.MIN_VALUE) || Y0.d.a(i16, uVar.f4414h)) && (pVar2 == null || pVar2.equals(uVar.f4415i)))))))) {
                return uVar;
            }
        } else {
            j10 = j3;
        }
        long j11 = AbstractC0904a.L(j3) ? uVar.c : j10;
        if (oVar2 == null) {
            oVar2 = uVar.d;
        }
        if (Y0.i.a(i7, Integer.MIN_VALUE)) {
            i13 = uVar.f4409a;
        }
        if (Y0.k.a(i14, Integer.MIN_VALUE)) {
            i14 = uVar.f4410b;
        }
        w wVar3 = uVar.f4411e;
        if (wVar3 != null && wVar2 == null) {
            wVar2 = wVar3;
        }
        if (gVar2 == null) {
            gVar2 = uVar.f4412f;
        }
        if (i15 == 0) {
            i15 = uVar.f4413g;
        }
        if (Y0.d.a(i16, Integer.MIN_VALUE)) {
            i16 = uVar.f4414h;
        }
        if (pVar2 == null) {
            pVar2 = uVar.f4415i;
        }
        return new u(i13, i14, j11, oVar2, wVar2, gVar2, i15, i16, pVar2);
    }
}
