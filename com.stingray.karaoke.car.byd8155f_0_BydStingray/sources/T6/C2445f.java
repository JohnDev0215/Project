package t6;

import A7.k;
import A7.m;
import h2.AbstractC1347d;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import n7.u;
import n7.z;
import r1.C2273e;
import u6.C2527a;
import v6.C2555c;

/* JADX INFO: renamed from: t6.f, reason: case insensitive filesystem */
/* JADX INFO: loaded from: classes.dex */
public final class C2445f {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public x6.e f22483a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public int f22484b;
    public boolean c;
    public C2555c d;

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    public int f22488h;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public List f22485e = u.l;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public int f22486f = -1;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public C2527a f22487g = new C2527a();

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public int f22489i = 4;

    public final void a(int i7, ArrayList arrayList) {
        int i10;
        int i11;
        double d;
        int i12;
        double d10;
        C2445f c2445f;
        ArrayList arrayList2;
        int i13;
        C2445f c2445f2 = this;
        int i14 = i7;
        ArrayList arrayList3 = arrayList;
        if (i14 < 0) {
            return;
        }
        int i15 = 0;
        int i16 = ((u6.b) arrayList3.get(0)).f22933f.f21656g;
        x6.e eVar = c2445f2.f22483a;
        String str = "timingConfig";
        if (eVar == null) {
            m.j("timingConfig");
            throw null;
        }
        int i17 = (i16 - eVar.f23814g) - eVar.f23816i;
        int iD = ((u6.b) AbstractC1347d.e(1, arrayList3)).f22933f.d();
        x6.e eVar2 = c2445f2.f22483a;
        if (eVar2 == null) {
            m.j("timingConfig");
            throw null;
        }
        int i18 = iD + eVar2.f23815h + eVar2.f23817j;
        int size = arrayList.size();
        int iMax = size < i14 ? Math.max(size, 0) : i14;
        int size2 = arrayList.size() - 1;
        if (i14 <= 0) {
            throw new IllegalArgumentException(k.g("Step must be positive, was: ", i7, "."));
        }
        int iN = z.n(0, size2, i14);
        if (iN < 0) {
            return;
        }
        int i19 = 0;
        while (true) {
            int i20 = i19 / i14;
            x6.e eVar3 = c2445f2.f22483a;
            if (eVar3 == null) {
                m.j(str);
                throw null;
            }
            int i21 = i19 + i14;
            int size3 = arrayList.size() - i21;
            int iMax2 = size3 < i14 ? Math.max(size3, i15) : i14;
            if (i20 == 0) {
                int i22 = i15;
                while (i22 < iMax) {
                    C2273e c2273e = ((u6.b) arrayList3.get(i22)).f22933f;
                    x6.e eVar4 = c2445f2.f22483a;
                    if (eVar4 == null) {
                        m.j(str);
                        throw null;
                    }
                    int i23 = iN;
                    int i24 = (eVar4.f23818k * i22) + i17;
                    c2273e.c = i24;
                    int i25 = eVar4.f23814g;
                    if (i24 < 0) {
                        i25 += i24;
                        i13 = 0;
                        c2273e.c = 0;
                        if (i25 < 0) {
                            i25 = 1;
                        }
                    } else {
                        i13 = 0;
                    }
                    c2273e.f21653b = i25;
                    i22++;
                    i15 = i13;
                    iN = i23;
                }
            }
            int i26 = iN;
            int i27 = i15;
            if (i20 == (arrayList.size() - 1) / i14) {
                for (int i28 = i27; i28 < iMax; i28++) {
                    u6.b bVar = (u6.b) arrayList3.get(i19 + i28);
                    C2273e c2273e2 = bVar.f22933f;
                    c2273e2.f21654e = i18;
                    x6.e eVar5 = c2445f2.f22483a;
                    if (eVar5 == null) {
                        m.j(str);
                        throw null;
                    }
                    c2273e2.d = eVar5.f23815h;
                    int iD2 = c2273e2.d();
                    C2273e c2273e3 = bVar.f22933f;
                    int i29 = c2273e3.c;
                    x6.e eVar6 = c2445f2.f22483a;
                    if (eVar6 == null) {
                        m.j(str);
                        throw null;
                    }
                    int i30 = i29 + eVar6.f23814g;
                    if (iD2 < eVar6.f23815h + i30) {
                        c2273e3.f21654e = i30;
                        if (i30 < c2273e3.d()) {
                            c2273e3.f21654e = c2273e3.d();
                        }
                    }
                }
                return;
            }
            int iD3 = ((u6.b) arrayList3.get((i19 + iMax) - 1)).f22933f.d();
            x6.e eVar7 = c2445f2.f22483a;
            if (eVar7 == null) {
                m.j(str);
                throw null;
            }
            int i31 = iD3 + eVar7.f23815h;
            int i32 = ((u6.b) arrayList3.get(i21)).f22933f.f21656g;
            x6.e eVar8 = c2445f2.f22483a;
            if (eVar8 == null) {
                m.j(str);
                throw null;
            }
            int i33 = (i32 - eVar8.f23814g) - eVar8.f23816i;
            int i34 = iMax - 1;
            if (i33 < i31) {
                i11 = i19;
                i10 = i17;
                d = ((double) i34) / ((double) (i31 - i33));
                double d11 = eVar8.f23819m;
                if (d < d11) {
                    d10 = d;
                    i34 = 0;
                    d = d11;
                    i12 = i33;
                } else {
                    double d12 = eVar8.f23820n;
                    d10 = d;
                    if (d > d12) {
                        d = d12;
                    }
                    i12 = i31;
                }
            } else {
                i10 = i17;
                i11 = i19;
                d = eVar3.l;
                i12 = i33;
                d10 = d;
                i34 = 0;
            }
            String str2 = str;
            double d13 = ((double) i34) - (((double) i12) * d);
            int iMin = Math.min(iMax, iMax2);
            int i35 = 0;
            while (i35 < iMin) {
                u6.b bVar2 = (u6.b) arrayList3.get(i11 + i35);
                int i36 = iMin;
                C2273e c2273e4 = bVar2.f22933f;
                int i37 = i18;
                x6.e eVar9 = c2445f2.f22483a;
                if (eVar9 == null) {
                    m.j(str2);
                    throw null;
                }
                c2273e4.d = eVar9.f23815h;
                int iD4 = c2273e4.d();
                x6.e eVar10 = c2445f2.f22483a;
                if (eVar10 == null) {
                    m.j(str2);
                    throw null;
                }
                int i38 = iD4 + eVar10.f23815h;
                u6.b bVar3 = (u6.b) arrayList3.get(i21 + i35);
                C2273e c2273e5 = bVar3.f22933f;
                int i39 = i21;
                x6.e eVar11 = c2445f2.f22483a;
                if (eVar11 == null) {
                    m.j(str2);
                    throw null;
                }
                int i40 = eVar11.f23814g;
                c2273e5.f21653b = i40;
                int i41 = c2273e5.f21656g;
                int i42 = iMax;
                int i43 = (i41 - i40) - eVar11.f23816i;
                C2273e c2273e6 = bVar2.f22933f;
                if (i38 > i43) {
                    int iD5 = (i41 - c2273e6.d()) / 2;
                    int i44 = iD5 / 2;
                    c2273e6.d = i44;
                    C2273e c2273e7 = bVar3.f22933f;
                    c2273e7.f21653b = i44;
                    int iD6 = c2273e6.d();
                    int i45 = c2273e6.d;
                    int i46 = (c2273e7.f21656g - iD5) - c2273e7.f21653b;
                    c2273e6.f21654e = (iD6 + i45) - i45;
                    c2273e7.c = i46;
                } else {
                    double d14 = i35;
                    double d15 = d13;
                    int i47 = (int) ((d14 - d13) / d);
                    if (i47 > i43) {
                        d13 = d14 - (((double) i43) * d10);
                        i47 = (int) ((d14 - d13) / d10);
                        d = d10;
                        i38 = i43;
                    } else {
                        if (i47 >= i38) {
                            i38 = i47;
                        } else {
                            i47 = i38;
                        }
                        d13 = d15;
                    }
                    c2273e6.f21654e = i38 - eVar11.f23815h;
                    if (i38 >= i43) {
                        i43 = i38;
                    } else if (i47 < i43) {
                        i43 = i47;
                    }
                    c2273e5.c = i43;
                }
                i35++;
                c2445f2 = this;
                arrayList3 = arrayList;
                iMin = i36;
                i18 = i37;
                i21 = i39;
                iMax = i42;
            }
            double d16 = d13;
            int i48 = i18;
            int i49 = i21;
            int iMax3 = Math.max(iMax, iMax2);
            while (iMin < iMax3) {
                int i50 = (int) ((((double) iMin) - d16) / d);
                if (iMin < iMax) {
                    arrayList2 = arrayList;
                    u6.b bVar4 = (u6.b) arrayList2.get(i11 + iMin);
                    C2273e c2273e8 = bVar4.f22933f;
                    c2445f = this;
                    x6.e eVar12 = c2445f.f22483a;
                    if (eVar12 == null) {
                        m.j(str2);
                        throw null;
                    }
                    c2273e8.d = eVar12.f23815h;
                    int iD7 = c2273e8.d();
                    C2273e c2273e9 = bVar4.f22933f;
                    int i51 = c2273e9.d;
                    int i52 = iD7 + i51;
                    if (i50 >= i52) {
                        i52 = i50;
                    }
                    c2273e9.f21654e = i52 - i51;
                } else {
                    c2445f = this;
                    arrayList2 = arrayList;
                }
                if (iMin < iMax2) {
                    C2273e c2273e10 = ((u6.b) arrayList2.get(i49 + iMin)).f22933f;
                    x6.e eVar13 = c2445f.f22483a;
                    if (eVar13 == null) {
                        m.j(str2);
                        throw null;
                    }
                    int i53 = eVar13.f23814g;
                    c2273e10.f21653b = i53;
                    int i54 = (c2273e10.f21656g - i53) - eVar13.f23816i;
                    if (i50 >= i54) {
                        i50 = i54;
                    }
                    c2273e10.c = i50;
                }
                iMin++;
            }
            if (i11 == i26) {
                return;
            }
            i14 = i7;
            iN = i26;
            arrayList3 = arrayList;
            c2445f2 = this;
            iMax = iMax2;
            i17 = i10;
            str = str2;
            i18 = i48;
            i19 = i49;
            i15 = 0;
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:38:0x00ef  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final void b(t6.h r25, int r26) {
        /*
            Method dump skipped, instruction units count: 545
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: t6.C2445f.b(t6.h, int):void");
    }

    /* JADX WARN: Removed duplicated region for block: B:134:0x027b  */
    /* JADX WARN: Removed duplicated region for block: B:135:0x0290  */
    /* JADX WARN: Removed duplicated region for block: B:161:0x0308  */
    /* JADX WARN: Removed duplicated region for block: B:164:0x0312  */
    /* JADX WARN: Removed duplicated region for block: B:269:0x0562  */
    /* JADX WARN: Removed duplicated region for block: B:273:0x056b  */
    /* JADX WARN: Removed duplicated region for block: B:274:0x056e  */
    /* JADX WARN: Removed duplicated region for block: B:98:0x01f3  */
    /* JADX WARN: Removed duplicated region for block: B:99:0x01f5  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final v6.C2555c c() {
        /*
            Method dump skipped, instruction units count: 1407
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: t6.C2445f.c():v6.c");
    }

    public final String toString() {
        StringBuilder sb = new StringBuilder();
        Iterator it = this.f22487g.l.iterator();
        while (it.hasNext()) {
            sb.append(((u6.b) it.next()).toString());
            sb.append('\n');
        }
        String string = sb.toString();
        m.e(string, "toString(...)");
        return string;
    }
}
