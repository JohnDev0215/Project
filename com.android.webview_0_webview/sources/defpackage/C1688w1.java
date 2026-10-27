package defpackage;

/* JADX INFO: renamed from: w1, reason: case insensitive filesystem */
/* JADX INFO: compiled from: chromium-SystemWebView.apk-default-410412053 */
/* JADX INFO: loaded from: classes.dex */
public class C1688w1 implements Cloneable {
    public static final Object w = new Object();
    public boolean s = false;
    public int[] t;
    public Object[] u;
    public int v;

    public C1688w1(int i) {
        if (i == 0) {
            this.t = AbstractC1139m1.a;
            this.u = AbstractC1139m1.c;
        } else {
            int iE = AbstractC1139m1.e(i);
            this.t = new int[iE];
            this.u = new Object[iE];
        }
        this.v = 0;
    }

    public void a(int i, Object obj) {
        int i2 = this.v;
        if (i2 != 0 && i <= this.t[i2 - 1]) {
            g(i, obj);
            return;
        }
        if (this.s && this.v >= this.t.length) {
            c();
        }
        int i3 = this.v;
        if (i3 >= this.t.length) {
            int iE = AbstractC1139m1.e(i3 + 1);
            int[] iArr = new int[iE];
            Object[] objArr = new Object[iE];
            int[] iArr2 = this.t;
            System.arraycopy(iArr2, 0, iArr, 0, iArr2.length);
            Object[] objArr2 = this.u;
            System.arraycopy(objArr2, 0, objArr, 0, objArr2.length);
            this.t = iArr;
            this.u = objArr;
        }
        this.t[i3] = i;
        this.u[i3] = obj;
        this.v = i3 + 1;
    }

    /* JADX INFO: renamed from: b, reason: merged with bridge method [inline-methods] */
    public C1688w1 clone() {
        try {
            C1688w1 c1688w1 = (C1688w1) super.clone();
            c1688w1.t = (int[]) this.t.clone();
            c1688w1.u = (Object[]) this.u.clone();
            return c1688w1;
        } catch (CloneNotSupportedException e) {
            throw new AssertionError(e);
        }
    }

    public final void c() {
        int i = this.v;
        int[] iArr = this.t;
        Object[] objArr = this.u;
        int i2 = 0;
        for (int i3 = 0; i3 < i; i3++) {
            Object obj = objArr[i3];
            if (obj != w) {
                if (i3 != i2) {
                    iArr[i2] = iArr[i3];
                    objArr[i2] = obj;
                    objArr[i3] = null;
                }
                i2++;
            }
        }
        this.s = false;
        this.v = i2;
    }

    public Object d(int i) {
        return e(i, null);
    }

    public Object e(int i, Object obj) {
        int iA = AbstractC1139m1.a(this.t, this.v, i);
        if (iA >= 0) {
            Object[] objArr = this.u;
            if (objArr[iA] != w) {
                return objArr[iA];
            }
        }
        return obj;
    }

    public int f(int i) {
        if (this.s) {
            c();
        }
        return this.t[i];
    }

    public void g(int i, Object obj) {
        int iA = AbstractC1139m1.a(this.t, this.v, i);
        if (iA >= 0) {
            this.u[iA] = obj;
            return;
        }
        int i2 = ~iA;
        if (i2 < this.v) {
            Object[] objArr = this.u;
            if (objArr[i2] == w) {
                this.t[i2] = i;
                objArr[i2] = obj;
                return;
            }
        }
        if (this.s && this.v >= this.t.length) {
            c();
            i2 = ~AbstractC1139m1.a(this.t, this.v, i);
        }
        int i3 = this.v;
        if (i3 >= this.t.length) {
            int iE = AbstractC1139m1.e(i3 + 1);
            int[] iArr = new int[iE];
            Object[] objArr2 = new Object[iE];
            int[] iArr2 = this.t;
            System.arraycopy(iArr2, 0, iArr, 0, iArr2.length);
            Object[] objArr3 = this.u;
            System.arraycopy(objArr3, 0, objArr2, 0, objArr3.length);
            this.t = iArr;
            this.u = objArr2;
        }
        int i4 = this.v;
        if (i4 - i2 != 0) {
            int[] iArr3 = this.t;
            int i5 = i2 + 1;
            System.arraycopy(iArr3, i2, iArr3, i5, i4 - i2);
            Object[] objArr4 = this.u;
            System.arraycopy(objArr4, i2, objArr4, i5, this.v - i2);
        }
        this.t[i2] = i;
        this.u[i2] = obj;
        this.v++;
    }

    public void h(int i) {
        int iA = AbstractC1139m1.a(this.t, this.v, i);
        if (iA >= 0) {
            Object[] objArr = this.u;
            Object obj = objArr[iA];
            Object obj2 = w;
            if (obj != obj2) {
                objArr[iA] = obj2;
                this.s = true;
            }
        }
    }

    public int i() {
        if (this.s) {
            c();
        }
        return this.v;
    }

    public Object j(int i) {
        if (this.s) {
            c();
        }
        return this.u[i];
    }

    public String toString() {
        if (i() <= 0) {
            return "{}";
        }
        StringBuilder sb = new StringBuilder(this.v * 28);
        sb.append('{');
        for (int i = 0; i < this.v; i++) {
            if (i > 0) {
                sb.append(", ");
            }
            sb.append(f(i));
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
}
