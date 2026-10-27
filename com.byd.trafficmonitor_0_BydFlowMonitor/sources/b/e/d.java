package b.e;

/* JADX INFO: compiled from: LongSparseArray.java */
/* JADX INFO: loaded from: classes.dex */
public class d<E> implements Cloneable {

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public static final Object f1942b = new Object();

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public boolean f1943c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public long[] f1944d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public Object[] f1945e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public int f1946f;

    public d() {
        this(10);
    }

    public void a(long j, E e2) {
        int i2 = this.f1946f;
        if (i2 != 0 && j <= this.f1944d[i2 - 1]) {
            i(j, e2);
            return;
        }
        if (this.f1943c && i2 >= this.f1944d.length) {
            d();
        }
        int i3 = this.f1946f;
        if (i3 >= this.f1944d.length) {
            int iF = c.f(i3 + 1);
            long[] jArr = new long[iF];
            Object[] objArr = new Object[iF];
            long[] jArr2 = this.f1944d;
            System.arraycopy(jArr2, 0, jArr, 0, jArr2.length);
            Object[] objArr2 = this.f1945e;
            System.arraycopy(objArr2, 0, objArr, 0, objArr2.length);
            this.f1944d = jArr;
            this.f1945e = objArr;
        }
        this.f1944d[i3] = j;
        this.f1945e[i3] = e2;
        this.f1946f = i3 + 1;
    }

    public void b() {
        int i2 = this.f1946f;
        Object[] objArr = this.f1945e;
        for (int i3 = 0; i3 < i2; i3++) {
            objArr[i3] = null;
        }
        this.f1946f = 0;
        this.f1943c = false;
    }

    /* JADX INFO: renamed from: c, reason: merged with bridge method [inline-methods] */
    public d<E> clone() {
        try {
            d<E> dVar = (d) super.clone();
            dVar.f1944d = (long[]) this.f1944d.clone();
            dVar.f1945e = (Object[]) this.f1945e.clone();
            return dVar;
        } catch (CloneNotSupportedException e2) {
            throw new AssertionError(e2);
        }
    }

    public final void d() {
        int i2 = this.f1946f;
        long[] jArr = this.f1944d;
        Object[] objArr = this.f1945e;
        int i3 = 0;
        for (int i4 = 0; i4 < i2; i4++) {
            Object obj = objArr[i4];
            if (obj != f1942b) {
                if (i4 != i3) {
                    jArr[i3] = jArr[i4];
                    objArr[i3] = obj;
                    objArr[i4] = null;
                }
                i3++;
            }
        }
        this.f1943c = false;
        this.f1946f = i3;
    }

    public E e(long j) {
        return f(j, null);
    }

    public E f(long j, E e2) {
        int iB = c.b(this.f1944d, this.f1946f, j);
        if (iB >= 0) {
            Object[] objArr = this.f1945e;
            if (objArr[iB] != f1942b) {
                return (E) objArr[iB];
            }
        }
        return e2;
    }

    public int g(long j) {
        if (this.f1943c) {
            d();
        }
        return c.b(this.f1944d, this.f1946f, j);
    }

    public long h(int i2) {
        if (this.f1943c) {
            d();
        }
        return this.f1944d[i2];
    }

    public void i(long j, E e2) {
        int iB = c.b(this.f1944d, this.f1946f, j);
        if (iB >= 0) {
            this.f1945e[iB] = e2;
            return;
        }
        int i2 = ~iB;
        int i3 = this.f1946f;
        if (i2 < i3) {
            Object[] objArr = this.f1945e;
            if (objArr[i2] == f1942b) {
                this.f1944d[i2] = j;
                objArr[i2] = e2;
                return;
            }
        }
        if (this.f1943c && i3 >= this.f1944d.length) {
            d();
            i2 = ~c.b(this.f1944d, this.f1946f, j);
        }
        int i4 = this.f1946f;
        if (i4 >= this.f1944d.length) {
            int iF = c.f(i4 + 1);
            long[] jArr = new long[iF];
            Object[] objArr2 = new Object[iF];
            long[] jArr2 = this.f1944d;
            System.arraycopy(jArr2, 0, jArr, 0, jArr2.length);
            Object[] objArr3 = this.f1945e;
            System.arraycopy(objArr3, 0, objArr2, 0, objArr3.length);
            this.f1944d = jArr;
            this.f1945e = objArr2;
        }
        int i5 = this.f1946f;
        if (i5 - i2 != 0) {
            long[] jArr3 = this.f1944d;
            int i6 = i2 + 1;
            System.arraycopy(jArr3, i2, jArr3, i6, i5 - i2);
            Object[] objArr4 = this.f1945e;
            System.arraycopy(objArr4, i2, objArr4, i6, this.f1946f - i2);
        }
        this.f1944d[i2] = j;
        this.f1945e[i2] = e2;
        this.f1946f++;
    }

    public void j(long j) {
        int iB = c.b(this.f1944d, this.f1946f, j);
        if (iB >= 0) {
            Object[] objArr = this.f1945e;
            Object obj = objArr[iB];
            Object obj2 = f1942b;
            if (obj != obj2) {
                objArr[iB] = obj2;
                this.f1943c = true;
            }
        }
    }

    public void k(int i2) {
        Object[] objArr = this.f1945e;
        Object obj = objArr[i2];
        Object obj2 = f1942b;
        if (obj != obj2) {
            objArr[i2] = obj2;
            this.f1943c = true;
        }
    }

    public int l() {
        if (this.f1943c) {
            d();
        }
        return this.f1946f;
    }

    public E m(int i2) {
        if (this.f1943c) {
            d();
        }
        return (E) this.f1945e[i2];
    }

    public String toString() {
        if (l() <= 0) {
            return "{}";
        }
        StringBuilder sb = new StringBuilder(this.f1946f * 28);
        sb.append('{');
        for (int i2 = 0; i2 < this.f1946f; i2++) {
            if (i2 > 0) {
                sb.append(", ");
            }
            sb.append(h(i2));
            sb.append('=');
            E eM = m(i2);
            if (eM != this) {
                sb.append(eM);
            } else {
                sb.append("(this Map)");
            }
        }
        sb.append('}');
        return sb.toString();
    }

    public d(int i2) {
        this.f1943c = false;
        if (i2 == 0) {
            this.f1944d = c.f1940b;
            this.f1945e = c.f1941c;
        } else {
            int iF = c.f(i2);
            this.f1944d = new long[iF];
            this.f1945e = new Object[iF];
        }
    }
}
