package p1;

import java.util.ArrayList;

/* JADX INFO: loaded from: classes.dex */
public final class m {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public p f20672a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public ArrayList f20673b;

    public static long a(C2079g c2079g, long j3) {
        p pVar = c2079g.d;
        if (pVar instanceof C2083k) {
            return j3;
        }
        ArrayList arrayList = c2079g.f20668k;
        int size = arrayList.size();
        long jMin = j3;
        for (int i7 = 0; i7 < size; i7++) {
            InterfaceC2077e interfaceC2077e = (InterfaceC2077e) arrayList.get(i7);
            if (interfaceC2077e instanceof C2079g) {
                C2079g c2079g2 = (C2079g) interfaceC2077e;
                if (c2079g2.d != pVar) {
                    jMin = Math.min(jMin, a(c2079g2, ((long) c2079g2.f20663f) + j3));
                }
            }
        }
        if (c2079g != pVar.f20685i) {
            return jMin;
        }
        long j10 = pVar.j();
        C2079g c2079g3 = pVar.f20684h;
        long j11 = j3 - j10;
        return Math.min(Math.min(jMin, a(c2079g3, j11)), j11 - ((long) c2079g3.f20663f));
    }

    public static long b(C2079g c2079g, long j3) {
        p pVar = c2079g.d;
        if (pVar instanceof C2083k) {
            return j3;
        }
        ArrayList arrayList = c2079g.f20668k;
        int size = arrayList.size();
        long jMax = j3;
        for (int i7 = 0; i7 < size; i7++) {
            InterfaceC2077e interfaceC2077e = (InterfaceC2077e) arrayList.get(i7);
            if (interfaceC2077e instanceof C2079g) {
                C2079g c2079g2 = (C2079g) interfaceC2077e;
                if (c2079g2.d != pVar) {
                    jMax = Math.max(jMax, b(c2079g2, ((long) c2079g2.f20663f) + j3));
                }
            }
        }
        if (c2079g != pVar.f20684h) {
            return jMax;
        }
        long j10 = pVar.j();
        C2079g c2079g3 = pVar.f20685i;
        long j11 = j3 + j10;
        return Math.max(Math.max(jMax, b(c2079g3, j11)), j11 - ((long) c2079g3.f20663f));
    }
}
