package o1;

/* JADX INFO: loaded from: classes.dex */
public abstract class j {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static final boolean[] f20387a = new boolean[3];

    /* JADX WARN: Removed duplicated region for block: B:184:0x0285  */
    /* JADX WARN: Removed duplicated region for block: B:201:0x02d9  */
    /* JADX WARN: Removed duplicated region for block: B:207:0x0303  */
    /* JADX WARN: Removed duplicated region for block: B:299:0x04d6  */
    /* JADX WARN: Removed duplicated region for block: B:400:0x069f A[ADDED_TO_REGION] */
    /* JADX WARN: Removed duplicated region for block: B:408:0x06bb  */
    /* JADX WARN: Removed duplicated region for block: B:409:0x06bd  */
    /* JADX WARN: Removed duplicated region for block: B:412:0x06c8  */
    /* JADX WARN: Removed duplicated region for block: B:413:0x06cb  */
    /* JADX WARN: Removed duplicated region for block: B:416:0x06d1  */
    /* JADX WARN: Removed duplicated region for block: B:417:0x06d4  */
    /* JADX WARN: Removed duplicated region for block: B:419:0x06d8  */
    /* JADX WARN: Removed duplicated region for block: B:424:0x06e8  */
    /* JADX WARN: Removed duplicated region for block: B:426:0x06ec A[ADDED_TO_REGION] */
    /* JADX WARN: Removed duplicated region for block: B:436:0x0707 A[ADDED_TO_REGION, REMOVE, SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:63:0x00ec  */
    /* JADX WARN: Removed duplicated region for block: B:72:0x010b  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public static void a(o1.e r36, h1.c r37, java.util.ArrayList r38, int r39) {
        /*
            Method dump skipped, instruction units count: 1813
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: o1.j.a(o1.e, h1.c, java.util.ArrayList, int):void");
    }

    public static void b(e eVar, h1.c cVar, d dVar) {
        dVar.f20308p = -1;
        dVar.f20310q = -1;
        int i7 = eVar.f20311q0[0];
        int[] iArr = dVar.f20311q0;
        if (i7 != 2 && iArr[0] == 4) {
            c cVar2 = dVar.f20265J;
            int i10 = cVar2.f20254g;
            int iR = eVar.r();
            c cVar3 = dVar.f20267L;
            int i11 = iR - cVar3.f20254g;
            cVar2.f20256i = cVar.k(cVar2);
            cVar3.f20256i = cVar.k(cVar3);
            cVar.d(cVar2.f20256i, i10);
            cVar.d(cVar3.f20256i, i11);
            dVar.f20308p = 2;
            dVar.f20281Z = i10;
            int i12 = i11 - i10;
            dVar.f20277V = i12;
            int i13 = dVar.f20286c0;
            if (i12 < i13) {
                dVar.f20277V = i13;
            }
        }
        if (eVar.f20311q0[1] == 2 || iArr[1] != 4) {
            return;
        }
        c cVar4 = dVar.f20266K;
        int i14 = cVar4.f20254g;
        int iL = eVar.l();
        c cVar5 = dVar.f20268M;
        int i15 = iL - cVar5.f20254g;
        cVar4.f20256i = cVar.k(cVar4);
        cVar5.f20256i = cVar.k(cVar5);
        cVar.d(cVar4.f20256i, i14);
        cVar.d(cVar5.f20256i, i15);
        if (dVar.f20285b0 > 0 || dVar.f20295h0 == 8) {
            c cVar6 = dVar.f20269N;
            h1.e eVarK = cVar.k(cVar6);
            cVar6.f20256i = eVarK;
            cVar.d(eVarK, dVar.f20285b0 + i14);
        }
        dVar.f20310q = 2;
        dVar.f20283a0 = i14;
        int i16 = i15 - i14;
        dVar.f20278W = i16;
        int i17 = dVar.f20287d0;
        if (i16 < i17) {
            dVar.f20278W = i17;
        }
    }

    public static final boolean c(int i7, int i10) {
        return (i7 & i10) == i10;
    }
}
