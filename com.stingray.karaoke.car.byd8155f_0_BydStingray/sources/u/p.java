package U;

import E0.Y;
import H7.H;
import T.B0;
import T.C0733c;
import T.C0735d;
import java.util.ArrayList;
import l3.P;
import n7.AbstractC1996k;

/* JADX INFO: loaded from: classes.dex */
public final class p extends C {
    public static final p c = new p(1, 0, 2);

    @Override // U.C
    public final void a(E5.z zVar, P p10, B0 b02, Y y10) {
        C0733c c0733c;
        int iC;
        int iF = zVar.f(0);
        if (!(b02.f8167n == 0)) {
            C0735d.y("Cannot move a group while inserting");
            throw null;
        }
        if (!(iF >= 0)) {
            C0735d.y("Parameter offset is out of bounds");
            throw null;
        }
        if (iF == 0) {
            return;
        }
        int i7 = b02.f8172t;
        int i10 = b02.f8174v;
        int i11 = b02.f8173u;
        int i12 = i7;
        while (iF > 0) {
            i12 += b02.f8158b[(b02.p(i12) * 5) + 3];
            if (i12 > i11) {
                C0735d.y("Parameter offset is out of bounds");
                throw null;
            }
            iF--;
        }
        int i13 = b02.f8158b[(b02.p(i12) * 5) + 3];
        int iF2 = b02.f(b02.f8158b, b02.p(b02.f8172t));
        int iF3 = b02.f(b02.f8158b, b02.p(i12));
        int i14 = i12 + i13;
        int iF4 = b02.f(b02.f8158b, b02.p(i14));
        int i15 = iF4 - iF3;
        b02.s(i15, Math.max(b02.f8172t - 1, 0));
        b02.r(i13);
        int[] iArr = b02.f8158b;
        int iP = b02.p(i14) * 5;
        AbstractC1996k.C(b02.p(i7) * 5, iP, (i13 * 5) + iP, iArr, iArr);
        if (i15 > 0) {
            Object[] objArr = b02.c;
            AbstractC1996k.D(iF2, b02.g(iF3 + i15), b02.g(iF4 + i15), objArr, objArr);
        }
        int i16 = iF3 + i15;
        int i17 = i16 - iF2;
        int i18 = b02.f8165k;
        int i19 = b02.l;
        int length = b02.c.length;
        int i20 = b02.f8166m;
        int i21 = i7 + i13;
        int i22 = i7;
        while (i22 < i21) {
            int iP2 = b02.p(i22);
            int i23 = i21;
            int i24 = i17;
            iArr[(iP2 * 5) + 4] = B0.h(B0.h(b02.f(iArr, iP2) - i17, i20 < iP2 ? 0 : i18, i19, length), b02.f8165k, b02.l, b02.c.length);
            i22++;
            i21 = i23;
            i17 = i24;
            i18 = i18;
            i19 = i19;
        }
        int i25 = i14 + i13;
        int iN = b02.n();
        int iP3 = C0735d.p(b02.d, i14, iN);
        ArrayList arrayList = new ArrayList();
        if (iP3 >= 0) {
            while (iP3 < b02.d.size() && (iC = b02.c((c0733c = (C0733c) b02.d.get(iP3)))) >= i14 && iC < i25) {
                arrayList.add(c0733c);
                b02.d.remove(iP3);
            }
        }
        int i26 = i7 - i14;
        int size = arrayList.size();
        for (int i27 = 0; i27 < size; i27++) {
            C0733c c0733c2 = (C0733c) arrayList.get(i27);
            int iC2 = b02.c(c0733c2) + i26;
            if (iC2 >= b02.f8161g) {
                c0733c2.f8235a = -(iN - iC2);
            } else {
                c0733c2.f8235a = iC2;
            }
            b02.d.add(C0735d.p(b02.d, iC2, iN), c0733c2);
        }
        if (b02.B(i14, i13)) {
            C0735d.y("Unexpectedly removed anchors");
            throw null;
        }
        b02.l(i10, b02.f8173u, i7);
        if (i15 > 0) {
            b02.C(i16, i15, i14 - 1);
        }
    }

    @Override // U.C
    public final String b(int i7) {
        return H.y(i7, 0) ? "offset" : super.b(i7);
    }
}
