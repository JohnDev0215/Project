package defpackage;

import java.util.ConcurrentModificationException;
import java.util.Map;

/* JADX INFO: renamed from: v1, reason: case insensitive filesystem */
/* JADX INFO: compiled from: chromium-SystemWebView.apk-default-410412053 */
/* JADX INFO: loaded from: classes.dex */
public class C1633v1 {
    public static Object[] v;
    public static int w;
    public static Object[] x;
    public static int y;
    public int[] s;
    public Object[] t;
    public int u;

    public C1633v1() {
        this.s = AbstractC1139m1.a;
        this.t = AbstractC1139m1.c;
        this.u = 0;
    }

    public static void c(int[] iArr, Object[] objArr, int i) {
        if (iArr.length == 8) {
            synchronized (C0971j1.class) {
                if (y < 10) {
                    objArr[0] = x;
                    objArr[1] = iArr;
                    for (int i2 = (i << 1) - 1; i2 >= 2; i2--) {
                        objArr[i2] = null;
                    }
                    x = objArr;
                    y++;
                }
            }
            return;
        }
        if (iArr.length == 4) {
            synchronized (C0971j1.class) {
                if (w < 10) {
                    objArr[0] = v;
                    objArr[1] = iArr;
                    for (int i3 = (i << 1) - 1; i3 >= 2; i3--) {
                        objArr[i3] = null;
                    }
                    v = objArr;
                    w++;
                }
            }
        }
    }

    public final void a(int i) {
        if (i == 8) {
            synchronized (C0971j1.class) {
                if (x != null) {
                    Object[] objArr = x;
                    this.t = objArr;
                    x = (Object[]) objArr[0];
                    this.s = (int[]) objArr[1];
                    objArr[1] = null;
                    objArr[0] = null;
                    y--;
                    return;
                }
            }
        } else if (i == 4) {
            synchronized (C0971j1.class) {
                if (v != null) {
                    Object[] objArr2 = v;
                    this.t = objArr2;
                    v = (Object[]) objArr2[0];
                    this.s = (int[]) objArr2[1];
                    objArr2[1] = null;
                    objArr2[0] = null;
                    w--;
                    return;
                }
            }
        }
        this.s = new int[i];
        this.t = new Object[i << 1];
    }

    public void b(int i) {
        int i2 = this.u;
        int[] iArr = this.s;
        if (iArr.length < i) {
            Object[] objArr = this.t;
            a(i);
            if (this.u > 0) {
                System.arraycopy(iArr, 0, this.s, 0, i2);
                System.arraycopy(objArr, 0, this.t, 0, i2 << 1);
            }
            c(iArr, objArr, i2);
        }
        if (this.u != i2) {
            throw new ConcurrentModificationException();
        }
    }

    public void clear() {
        int i = this.u;
        if (i > 0) {
            int[] iArr = this.s;
            Object[] objArr = this.t;
            this.s = AbstractC1139m1.a;
            this.t = AbstractC1139m1.c;
            this.u = 0;
            c(iArr, objArr, i);
        }
        if (this.u > 0) {
            throw new ConcurrentModificationException();
        }
    }

    public boolean containsKey(Object obj) {
        return e(obj) >= 0;
    }

    public boolean containsValue(Object obj) {
        return g(obj) >= 0;
    }

    public int d(Object obj, int i) {
        int i2 = this.u;
        if (i2 == 0) {
            return -1;
        }
        try {
            int iA = AbstractC1139m1.a(this.s, i2, i);
            if (iA < 0 || obj.equals(this.t[iA << 1])) {
                return iA;
            }
            int i3 = iA + 1;
            while (i3 < i2 && this.s[i3] == i) {
                if (obj.equals(this.t[i3 << 1])) {
                    return i3;
                }
                i3++;
            }
            for (int i4 = iA - 1; i4 >= 0 && this.s[i4] == i; i4--) {
                if (obj.equals(this.t[i4 << 1])) {
                    return i4;
                }
            }
            return ~i3;
        } catch (ArrayIndexOutOfBoundsException unused) {
            throw new ConcurrentModificationException();
        }
    }

    public int e(Object obj) {
        return obj == null ? f() : d(obj, obj.hashCode());
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj instanceof C1633v1) {
            C1633v1 c1633v1 = (C1633v1) obj;
            if (this.u != c1633v1.u) {
                return false;
            }
            for (int i = 0; i < this.u; i++) {
                try {
                    Object objH = h(i);
                    Object objJ = j(i);
                    Object obj2 = c1633v1.get(objH);
                    if (objJ == null) {
                        if (obj2 != null || !c1633v1.containsKey(objH)) {
                            return false;
                        }
                    } else if (!objJ.equals(obj2)) {
                        return false;
                    }
                } catch (ClassCastException | NullPointerException unused) {
                    return false;
                }
            }
            return true;
        }
        if (obj instanceof Map) {
            Map map = (Map) obj;
            if (this.u != map.size()) {
                return false;
            }
            for (int i2 = 0; i2 < this.u; i2++) {
                try {
                    Object objH2 = h(i2);
                    Object objJ2 = j(i2);
                    Object obj3 = map.get(objH2);
                    if (objJ2 == null) {
                        if (obj3 != null || !map.containsKey(objH2)) {
                            return false;
                        }
                    } else if (!objJ2.equals(obj3)) {
                        return false;
                    }
                } catch (ClassCastException | NullPointerException unused2) {
                }
            }
            return true;
        }
        return false;
    }

    public int f() {
        int i = this.u;
        if (i == 0) {
            return -1;
        }
        try {
            int iA = AbstractC1139m1.a(this.s, i, 0);
            if (iA < 0 || this.t[iA << 1] == null) {
                return iA;
            }
            int i2 = iA + 1;
            while (i2 < i && this.s[i2] == 0) {
                if (this.t[i2 << 1] == null) {
                    return i2;
                }
                i2++;
            }
            for (int i3 = iA - 1; i3 >= 0 && this.s[i3] == 0; i3--) {
                if (this.t[i3 << 1] == null) {
                    return i3;
                }
            }
            return ~i2;
        } catch (ArrayIndexOutOfBoundsException unused) {
            throw new ConcurrentModificationException();
        }
    }

    public int g(Object obj) {
        int i = this.u * 2;
        Object[] objArr = this.t;
        if (obj == null) {
            for (int i2 = 1; i2 < i; i2 += 2) {
                if (objArr[i2] == null) {
                    return i2 >> 1;
                }
            }
            return -1;
        }
        for (int i3 = 1; i3 < i; i3 += 2) {
            if (obj.equals(objArr[i3])) {
                return i3 >> 1;
            }
        }
        return -1;
    }

    public Object get(Object obj) {
        int iE = e(obj);
        if (iE >= 0) {
            return this.t[(iE << 1) + 1];
        }
        return null;
    }

    public Object h(int i) {
        return this.t[i << 1];
    }

    public int hashCode() {
        int[] iArr = this.s;
        Object[] objArr = this.t;
        int i = this.u;
        int i2 = 1;
        int i3 = 0;
        int iHashCode = 0;
        while (i3 < i) {
            Object obj = objArr[i2];
            iHashCode += (obj == null ? 0 : obj.hashCode()) ^ iArr[i3];
            i3++;
            i2 += 2;
        }
        return iHashCode;
    }

    public Object i(int i) {
        Object[] objArr = this.t;
        int i2 = i << 1;
        Object obj = objArr[i2 + 1];
        int i3 = this.u;
        int i4 = 0;
        if (i3 <= 1) {
            c(this.s, objArr, i3);
            this.s = AbstractC1139m1.a;
            this.t = AbstractC1139m1.c;
        } else {
            int i5 = i3 - 1;
            int[] iArr = this.s;
            if (iArr.length <= 8 || i3 >= iArr.length / 3) {
                if (i < i5) {
                    int[] iArr2 = this.s;
                    int i6 = i + 1;
                    int i7 = i5 - i;
                    System.arraycopy(iArr2, i6, iArr2, i, i7);
                    Object[] objArr2 = this.t;
                    System.arraycopy(objArr2, i6 << 1, objArr2, i2, i7 << 1);
                }
                Object[] objArr3 = this.t;
                int i8 = i5 << 1;
                objArr3[i8] = null;
                objArr3[i8 + 1] = null;
            } else {
                int i9 = i3 > 8 ? i3 + (i3 >> 1) : 8;
                int[] iArr3 = this.s;
                Object[] objArr4 = this.t;
                a(i9);
                if (i3 != this.u) {
                    throw new ConcurrentModificationException();
                }
                if (i > 0) {
                    System.arraycopy(iArr3, 0, this.s, 0, i);
                    System.arraycopy(objArr4, 0, this.t, 0, i2);
                }
                if (i < i5) {
                    int i10 = i + 1;
                    int i11 = i5 - i;
                    System.arraycopy(iArr3, i10, this.s, i, i11);
                    System.arraycopy(objArr4, i10 << 1, this.t, i2, i11 << 1);
                }
            }
            i4 = i5;
        }
        if (i3 != this.u) {
            throw new ConcurrentModificationException();
        }
        this.u = i4;
        return obj;
    }

    public boolean isEmpty() {
        return this.u <= 0;
    }

    public Object j(int i) {
        return this.t[(i << 1) + 1];
    }

    public Object put(Object obj, Object obj2) {
        int i;
        int iD;
        int i2 = this.u;
        if (obj == null) {
            iD = f();
            i = 0;
        } else {
            int iHashCode = obj.hashCode();
            i = iHashCode;
            iD = d(obj, iHashCode);
        }
        if (iD >= 0) {
            int i3 = (iD << 1) + 1;
            Object[] objArr = this.t;
            Object obj3 = objArr[i3];
            objArr[i3] = obj2;
            return obj3;
        }
        int i4 = ~iD;
        if (i2 >= this.s.length) {
            int i5 = 4;
            if (i2 >= 8) {
                i5 = (i2 >> 1) + i2;
            } else if (i2 >= 4) {
                i5 = 8;
            }
            int[] iArr = this.s;
            Object[] objArr2 = this.t;
            a(i5);
            if (i2 != this.u) {
                throw new ConcurrentModificationException();
            }
            int[] iArr2 = this.s;
            if (iArr2.length > 0) {
                System.arraycopy(iArr, 0, iArr2, 0, iArr.length);
                System.arraycopy(objArr2, 0, this.t, 0, objArr2.length);
            }
            c(iArr, objArr2, i2);
        }
        if (i4 < i2) {
            int[] iArr3 = this.s;
            int i6 = i4 + 1;
            System.arraycopy(iArr3, i4, iArr3, i6, i2 - i4);
            Object[] objArr3 = this.t;
            System.arraycopy(objArr3, i4 << 1, objArr3, i6 << 1, (this.u - i4) << 1);
        }
        int i7 = this.u;
        if (i2 == i7) {
            int[] iArr4 = this.s;
            if (i4 < iArr4.length) {
                iArr4[i4] = i;
                Object[] objArr4 = this.t;
                int i8 = i4 << 1;
                objArr4[i8] = obj;
                objArr4[i8 + 1] = obj2;
                this.u = i7 + 1;
                return null;
            }
        }
        throw new ConcurrentModificationException();
    }

    public Object remove(Object obj) {
        int iE = e(obj);
        if (iE >= 0) {
            return i(iE);
        }
        return null;
    }

    public int size() {
        return this.u;
    }

    public String toString() {
        if (isEmpty()) {
            return "{}";
        }
        StringBuilder sb = new StringBuilder(this.u * 28);
        sb.append('{');
        for (int i = 0; i < this.u; i++) {
            if (i > 0) {
                sb.append(", ");
            }
            Object objH = h(i);
            if (objH != this) {
                sb.append(objH);
            } else {
                sb.append("(this Map)");
            }
            sb.append('=');
            Object objJ = j(i);
            if (objJ != this) {
                sb.append(objJ);
            } else {
                sb.append("(this Map)");
            }
        }
        sb.append('}');
        return sb.toString();
    }

    public C1633v1(int i) {
        if (i == 0) {
            this.s = AbstractC1139m1.a;
            this.t = AbstractC1139m1.c;
        } else {
            a(i);
        }
        this.u = 0;
    }
}
