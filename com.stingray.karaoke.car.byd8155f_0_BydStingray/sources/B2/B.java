package B2;

import g2.C1288a;
import g2.C1289b;
import h2.AbstractC1343A;
import h2.AbstractC1346c;
import java.io.EOFException;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Collections;
import java.util.regex.Pattern;

/* JADX INFO: loaded from: classes.dex */
public final class B implements Y2.l {
    public final h2.t l;

    public B(int i7) {
        switch (i7) {
            case 1:
                this.l = new h2.t();
                break;
            default:
                this.l = new h2.t(10);
                break;
        }
    }

    public e2.K a(C0073m c0073m, C0074n c0074n) {
        h2.t tVar = this.l;
        e2.K kK0 = null;
        int i7 = 0;
        while (true) {
            try {
                c0073m.k(tVar.f15241a, 0, 10, false);
                tVar.H(0);
                if (tVar.y() != 4801587) {
                    break;
                }
                tVar.I(3);
                int iU = tVar.u();
                int i10 = iU + 10;
                if (kK0 == null) {
                    byte[] bArr = new byte[i10];
                    System.arraycopy(tVar.f15241a, 0, bArr, 0, 10);
                    c0073m.k(bArr, 10, iU, false);
                    kK0 = new P2.h(c0074n).k0(i10, bArr);
                } else {
                    c0073m.e(iU, false);
                }
                i7 += i10;
            } catch (EOFException unused) {
            }
        }
        c0073m.f590q = 0;
        c0073m.e(i7, false);
        return kK0;
    }

    @Override // Y2.l
    public void h(byte[] bArr, int i7, int i10, Y2.k kVar, h2.g gVar) {
        C1289b c1289bA;
        h2.t tVar = this.l;
        tVar.F(i10 + i7, bArr);
        tVar.H(i7);
        ArrayList arrayList = new ArrayList();
        while (tVar.a() > 0) {
            AbstractC1346c.b("Incomplete Mp4Webvtt Top Level box header found.", tVar.a() >= 8);
            int iH = tVar.h();
            if (tVar.h() == 1987343459) {
                int i11 = iH - 8;
                CharSequence charSequenceF = null;
                C1288a c1288aA = null;
                while (i11 > 0) {
                    AbstractC1346c.b("Incomplete vtt cue box header found.", i11 >= 8);
                    int iH2 = tVar.h();
                    int iH3 = tVar.h();
                    int i12 = iH2 - 8;
                    byte[] bArr2 = tVar.f15241a;
                    int i13 = tVar.f15242b;
                    int i14 = AbstractC1343A.f15195a;
                    String str = new String(bArr2, i13, i12, StandardCharsets.UTF_8);
                    tVar.I(i12);
                    i11 = (i11 - 8) - i12;
                    if (iH3 == 1937011815) {
                        g3.g gVar2 = new g3.g();
                        g3.h.e(str, gVar2);
                        c1288aA = gVar2.a();
                    } else if (iH3 == 1885436268) {
                        charSequenceF = g3.h.f(null, str.trim(), Collections.emptyList());
                    }
                }
                if (charSequenceF == null) {
                    charSequenceF = "";
                }
                if (c1288aA != null) {
                    c1288aA.f14756a = charSequenceF;
                    c1289bA = c1288aA.a();
                } else {
                    Pattern pattern = g3.h.f14844a;
                    g3.g gVar3 = new g3.g();
                    gVar3.c = charSequenceF;
                    c1289bA = gVar3.a().a();
                }
                arrayList.add(c1289bA);
            } else {
                tVar.I(iH - 8);
            }
        }
        gVar.accept(new Y2.a(arrayList, -9223372036854775807L, -9223372036854775807L));
    }

    @Override // Y2.l
    public int s() {
        return 2;
    }
}
