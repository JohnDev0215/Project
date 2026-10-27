package defpackage;

/* JADX INFO: renamed from: n1, reason: case insensitive filesystem */
/* JADX INFO: compiled from: chromium-SystemWebView.apk-default-410412053 */
/* JADX INFO: loaded from: classes.dex */
public class C1194n1 implements Cloneable {
    public static final Object w = new Object();
    public boolean s = false;
    public long[] t;
    public Object[] u;
    public int v;

    public C1194n1() {
        int iF = AbstractC1139m1.f(10);
        this.t = new long[iF];
        this.u = new Object[iF];
        this.v = 0;
    }

    public void a(long j, Object obj) {
        int i = this.v;
        if (i != 0 && j <= this.t[i - 1]) {
            g(j, obj);
            return;
        }
        if (this.s && this.v >= this.t.length) {
            d();
        }
        int i2 = this.v;
        if (i2 >= this.t.length) {
            int iF = AbstractC1139m1.f(i2 + 1);
            long[] jArr = new long[iF];
            Object[] objArr = new Object[iF];
            long[] jArr2 = this.t;
            System.arraycopy(jArr2, 0, jArr, 0, jArr2.length);
            Object[] objArr2 = this.u;
            System.arraycopy(objArr2, 0, objArr, 0, objArr2.length);
            this.t = jArr;
            this.u = objArr;
        }
        this.t[i2] = j;
        this.u[i2] = obj;
        this.v = i2 + 1;
    }

    public void b() {
        int i = this.v;
        Object[] objArr = this.u;
        for (int i2 = 0; i2 < i; i2++) {
            objArr[i2] = null;
        }
        this.v = 0;
        this.s = false;
    }

    /* JADX INFO: renamed from: c, reason: merged with bridge method [inline-methods] */
    public C1194n1 clone() {
        try {
            C1194n1 c1194n1 = (C1194n1) super.clone();
            c1194n1.t = (long[]) this.t.clone();
            c1194n1.u = (Object[]) this.u.clone();
            return c1194n1;
        } catch (CloneNotSupportedException e) {
            throw new AssertionError(e);
        }
    }

    public final void d() {
        int i = this.v;
        long[] jArr = this.t;
        Object[] objArr = this.u;
        int i2 = 0;
        for (int i3 = 0; i3 < i; i3++) {
            Object obj = objArr[i3];
            if (obj != w) {
                if (i3 != i2) {
                    jArr[i2] = jArr[i3];
                    objArr[i2] = obj;
                    objArr[i3] = null;
                }
                i2++;
            }
        }
        this.s = false;
        this.v = i2;
    }

    public Object e(long j) {
        return f(j, null);
    }

    public Object f(long j, Object obj) {
        int iB = AbstractC1139m1.b(this.t, this.v, j);
        if (iB >= 0) {
            Object[] objArr = this.u;
            if (objArr[iB] != w) {
                return objArr[iB];
            }
        }
        return obj;
    }

    public void g(long j, Object obj) {
        int iB = AbstractC1139m1.b(this.t, this.v, j);
        if (iB >= 0) {
            this.u[iB] = obj;
            return;
        }
        int i = ~iB;
        if (i < this.v) {
            Object[] objArr = this.u;
            if (objArr[i] == w) {
                this.t[i] = j;
                objArr[i] = obj;
                return;
            }
        }
        if (this.s && this.v >= this.t.length) {
            d();
            i = ~AbstractC1139m1.b(this.t, this.v, j);
        }
        int i2 = this.v;
        if (i2 >= this.t.length) {
            int iF = AbstractC1139m1.f(i2 + 1);
            long[] jArr = new long[iF];
            Object[] objArr2 = new Object[iF];
            long[] jArr2 = this.t;
            System.arraycopy(jArr2, 0, jArr, 0, jArr2.length);
            Object[] objArr3 = this.u;
            System.arraycopy(objArr3, 0, objArr2, 0, objArr3.length);
            this.t = jArr;
            this.u = objArr2;
        }
        int i3 = this.v;
        if (i3 - i != 0) {
            long[] jArr3 = this.t;
            int i4 = i + 1;
            System.arraycopy(jArr3, i, jArr3, i4, i3 - i);
            Object[] objArr4 = this.u;
            System.arraycopy(objArr4, i, objArr4, i4, this.v - i);
        }
        this.t[i] = j;
        this.u[i] = obj;
        this.v++;
    }

    public int h() {
        if (this.s) {
            d();
        }
        return this.v;
    }

    public Object i(int i) {
        if (this.s) {
            d();
        }
        return this.u[i];
    }

    public String toString() {
        if (h() <= 0) {
            return "{}";
        }
        StringBuilder sb = new StringBuilder(this.v * 28);
        sb.append('{');
        for (int i = 0; i < this.v; i++) {
            if (i > 0) {
                sb.append(", ");
            }
            if (this.s) {
                d();
            }
            sb.append(this.t[i]);
            sb.append('=');
            Object objI = i(i);
            if (objI != this) {
                sb.append(objI);
            } else {
                sb.append("(this Map)");
            }
        }
        sb.append('}');
        return sb.toString();
    }
}
