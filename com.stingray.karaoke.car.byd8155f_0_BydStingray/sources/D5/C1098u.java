package d5;

import a.AbstractC0904a;
import java.io.Serializable;
import java.util.AbstractMap;
import java.util.Arrays;
import java.util.Collection;
import java.util.Map;
import java.util.Objects;
import java.util.Set;

/* JADX INFO: renamed from: d5.u, reason: case insensitive filesystem */
/* JADX INFO: loaded from: classes.dex */
public final class C1098u extends AbstractMap implements Serializable {

    /* JADX INFO: renamed from: u, reason: collision with root package name */
    public static final Object f13456u = new Object();
    public transient Object l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public transient int[] f13457m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public transient Object[] f13458n;
    public transient Object[] o;

    /* JADX INFO: renamed from: p, reason: collision with root package name */
    public transient int f13459p;

    /* JADX INFO: renamed from: q, reason: collision with root package name */
    public transient int f13460q;

    /* JADX INFO: renamed from: r, reason: collision with root package name */
    public transient C1096s f13461r;

    /* JADX INFO: renamed from: s, reason: collision with root package name */
    public transient C1096s f13462s;

    /* JADX INFO: renamed from: t, reason: collision with root package name */
    public transient C1092n f13463t;

    public static C1098u b() {
        C1098u c1098u = new C1098u();
        c1098u.f13459p = Math.min(Math.max(8, 1), 1073741823);
        return c1098u;
    }

    public final Map c() {
        Object obj = this.l;
        if (obj instanceof Map) {
            return (Map) obj;
        }
        return null;
    }

    @Override // java.util.AbstractMap, java.util.Map
    public final void clear() {
        if (g()) {
            return;
        }
        this.f13459p += 32;
        Map mapC = c();
        if (mapC != null) {
            this.f13459p = Math.min(Math.max(size(), 3), 1073741823);
            mapC.clear();
            this.l = null;
            this.f13460q = 0;
            return;
        }
        Arrays.fill(j(), 0, this.f13460q, (Object) null);
        Arrays.fill(k(), 0, this.f13460q, (Object) null);
        Object obj = this.l;
        Objects.requireNonNull(obj);
        if (obj instanceof byte[]) {
            Arrays.fill((byte[]) obj, (byte) 0);
        } else if (obj instanceof short[]) {
            Arrays.fill((short[]) obj, (short) 0);
        } else {
            Arrays.fill((int[]) obj, 0);
        }
        Arrays.fill(i(), 0, this.f13460q, 0);
        this.f13460q = 0;
    }

    @Override // java.util.AbstractMap, java.util.Map
    public final boolean containsKey(Object obj) {
        Map mapC = c();
        return mapC != null ? mapC.containsKey(obj) : e(obj) != -1;
    }

    @Override // java.util.AbstractMap, java.util.Map
    public final boolean containsValue(Object obj) {
        Map mapC = c();
        if (mapC != null) {
            return mapC.containsValue(obj);
        }
        for (int i7 = 0; i7 < this.f13460q; i7++) {
            if (AbstractC0904a.A(obj, k()[i7])) {
                return true;
            }
        }
        return false;
    }

    public final int d() {
        return (1 << (this.f13459p & 31)) - 1;
    }

    public final int e(Object obj) {
        if (g()) {
            return -1;
        }
        int iU = AbstractC1095q.u(obj);
        int iD = d();
        Object obj2 = this.l;
        Objects.requireNonNull(obj2);
        int iV = AbstractC1095q.v(iU & iD, obj2);
        if (iV == 0) {
            return -1;
        }
        int i7 = ~iD;
        int i10 = iU & i7;
        do {
            int i11 = iV - 1;
            int i12 = i()[i11];
            if ((i12 & i7) == i10 && AbstractC0904a.A(obj, j()[i11])) {
                return i11;
            }
            iV = i12 & iD;
        } while (iV != 0);
        return -1;
    }

    @Override // java.util.AbstractMap, java.util.Map
    public final Set entrySet() {
        C1096s c1096s = this.f13462s;
        if (c1096s != null) {
            return c1096s;
        }
        C1096s c1096s2 = new C1096s(this, 0);
        this.f13462s = c1096s2;
        return c1096s2;
    }

    public final void f(int i7, int i10) {
        Object obj = this.l;
        Objects.requireNonNull(obj);
        int[] iArrI = i();
        Object[] objArrJ = j();
        Object[] objArrK = k();
        int size = size();
        int i11 = size - 1;
        if (i7 >= i11) {
            objArrJ[i7] = null;
            objArrK[i7] = null;
            iArrI[i7] = 0;
            return;
        }
        Object obj2 = objArrJ[i11];
        objArrJ[i7] = obj2;
        objArrK[i7] = objArrK[i11];
        objArrJ[i11] = null;
        objArrK[i11] = null;
        iArrI[i7] = iArrI[i11];
        iArrI[i11] = 0;
        int iU = AbstractC1095q.u(obj2) & i10;
        int iV = AbstractC1095q.v(iU, obj);
        if (iV == size) {
            AbstractC1095q.w(iU, i7 + 1, obj);
            return;
        }
        while (true) {
            int i12 = iV - 1;
            int i13 = iArrI[i12];
            int i14 = i13 & i10;
            if (i14 == size) {
                iArrI[i12] = AbstractC1095q.p(i13, i7 + 1, i10);
                return;
            }
            iV = i14;
        }
    }

    public final boolean g() {
        return this.l == null;
    }

    @Override // java.util.AbstractMap, java.util.Map
    public final Object get(Object obj) {
        Map mapC = c();
        if (mapC != null) {
            return mapC.get(obj);
        }
        int iE = e(obj);
        if (iE == -1) {
            return null;
        }
        return k()[iE];
    }

    public final Object h(Object obj) {
        boolean zG = g();
        Object obj2 = f13456u;
        if (zG) {
            return obj2;
        }
        int iD = d();
        Object obj3 = this.l;
        Objects.requireNonNull(obj3);
        int iR = AbstractC1095q.r(obj, null, iD, obj3, i(), j(), null);
        if (iR == -1) {
            return obj2;
        }
        Object obj4 = k()[iR];
        f(iR, iD);
        this.f13460q--;
        this.f13459p += 32;
        return obj4;
    }

    public final int[] i() {
        int[] iArr = this.f13457m;
        Objects.requireNonNull(iArr);
        return iArr;
    }

    @Override // java.util.AbstractMap, java.util.Map
    public final boolean isEmpty() {
        return size() == 0;
    }

    public final Object[] j() {
        Object[] objArr = this.f13458n;
        Objects.requireNonNull(objArr);
        return objArr;
    }

    public final Object[] k() {
        Object[] objArr = this.o;
        Objects.requireNonNull(objArr);
        return objArr;
    }

    @Override // java.util.AbstractMap, java.util.Map
    public final Set keySet() {
        C1096s c1096s = this.f13461r;
        if (c1096s != null) {
            return c1096s;
        }
        C1096s c1096s2 = new C1096s(this, 1);
        this.f13461r = c1096s2;
        return c1096s2;
    }

    public final int l(int i7, int i10, int i11, int i12) {
        Object objG = AbstractC1095q.g(i10);
        int i13 = i10 - 1;
        if (i12 != 0) {
            AbstractC1095q.w(i11 & i13, i12 + 1, objG);
        }
        Object obj = this.l;
        Objects.requireNonNull(obj);
        int[] iArrI = i();
        for (int i14 = 0; i14 <= i7; i14++) {
            int iV = AbstractC1095q.v(i14, obj);
            while (iV != 0) {
                int i15 = iV - 1;
                int i16 = iArrI[i15];
                int i17 = ((~i7) & i16) | i14;
                int i18 = i17 & i13;
                int iV2 = AbstractC1095q.v(i18, objG);
                AbstractC1095q.w(i18, iV, objG);
                iArrI[i15] = AbstractC1095q.p(i17, iV2, i13);
                iV = i16 & i7;
            }
        }
        this.l = objG;
        this.f13459p = AbstractC1095q.p(this.f13459p, 32 - Integer.numberOfLeadingZeros(i13), 31);
        return i13;
    }

    /* JADX WARN: Removed duplicated region for block: B:43:0x00ef  */
    /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:44:0x0102 -> B:40:0x00e8). Please report as a decompilation issue!!! */
    @Override // java.util.AbstractMap, java.util.Map
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final java.lang.Object put(java.lang.Object r21, java.lang.Object r22) {
        /*
            Method dump skipped, instruction units count: 405
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: d5.C1098u.put(java.lang.Object, java.lang.Object):java.lang.Object");
    }

    @Override // java.util.AbstractMap, java.util.Map
    public final Object remove(Object obj) {
        Map mapC = c();
        if (mapC != null) {
            return mapC.remove(obj);
        }
        Object objH = h(obj);
        if (objH == f13456u) {
            return null;
        }
        return objH;
    }

    @Override // java.util.AbstractMap, java.util.Map
    public final int size() {
        Map mapC = c();
        return mapC != null ? mapC.size() : this.f13460q;
    }

    @Override // java.util.AbstractMap, java.util.Map
    public final Collection values() {
        C1092n c1092n = this.f13463t;
        if (c1092n != null) {
            return c1092n;
        }
        C1092n c1092n2 = new C1092n(1, this);
        this.f13463t = c1092n2;
        return c1092n2;
    }
}
