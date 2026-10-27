package w5;

import t5.C2438c;
import v5.C2552b;
import v5.EnumC2551a;

/* JADX INFO: renamed from: w5.d, reason: case insensitive filesystem */
/* JADX INFO: loaded from: classes.dex */
public final class C2602d {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final EnumC2551a f23331a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final int f23332b;
    public final int c;
    public final int d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public final C2602d f23333e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public final int f23334f;

    public C2602d(X1.a aVar, EnumC2551a enumC2551a, int i7, int i10, int i11, C2602d c2602d, C2552b c2552b) {
        this.f23331a = enumC2551a;
        this.f23332b = i7;
        EnumC2551a enumC2551a2 = EnumC2551a.BYTE;
        int i12 = (enumC2551a == enumC2551a2 || c2602d == null) ? i10 : c2602d.c;
        this.c = i12;
        this.d = i11;
        this.f23333e = c2602d;
        boolean z6 = false;
        int iA = c2602d != null ? c2602d.f23334f : 0;
        if ((enumC2551a == enumC2551a2 && c2602d == null && i12 != 0) || (c2602d != null && i12 != c2602d.c)) {
            z6 = true;
        }
        iA = (c2602d == null || enumC2551a != c2602d.f23331a || z6) ? iA + enumC2551a.a(c2552b) + 4 : iA;
        int iOrdinal = enumC2551a.ordinal();
        if (iOrdinal != 1) {
            if (iOrdinal == 2) {
                iA += i11 != 1 ? 11 : 6;
            } else if (iOrdinal == 4) {
                iA += ((String) aVar.d).substring(i7, i11 + i7).getBytes(((C2438c) aVar.f9972e).f22469a[i10].charset()).length * 8;
                if (z6) {
                    iA += 12;
                }
            } else if (iOrdinal == 6) {
                iA += 13;
            }
        } else {
            iA += i11 != 1 ? i11 == 2 ? 7 : 10 : 4;
        }
        this.f23334f = iA;
    }
}
