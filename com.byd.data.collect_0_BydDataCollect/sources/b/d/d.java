package b.d;

/* JADX INFO: compiled from: LongSparseArray.java */
/* JADX INFO: loaded from: classes.dex */
public class d<E> implements Cloneable {

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private static final Object f2262b = new Object();

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    private boolean f2263c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    private long[] f2264d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    private Object[] f2265e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    private int f2266f;

    public d() {
        this(10);
    }

    private void f() {
        int i2 = this.f2266f;
        long[] jArr = this.f2264d;
        Object[] objArr = this.f2265e;
        int i3 = 0;
        for (int i4 = 0; i4 < i2; i4++) {
            Object obj = objArr[i4];
            if (obj != f2262b) {
                if (i4 != i3) {
                    jArr[i3] = jArr[i4];
                    objArr[i3] = obj;
                    objArr[i4] = null;
                }
                i3++;
            }
        }
        this.f2263c = false;
        this.f2266f = i3;
    }

    public void b(long j, E e2) {
        int i2 = this.f2266f;
        if (i2 != 0 && j <= this.f2264d[i2 - 1]) {
            l(j, e2);
            return;
        }
        if (this.f2263c && i2 >= this.f2264d.length) {
            f();
        }
        int i3 = this.f2266f;
        if (i3 >= this.f2264d.length) {
            int iF = c.f(i3 + 1);
            long[] jArr = new long[iF];
            Object[] objArr = new Object[iF];
            long[] jArr2 = this.f2264d;
            System.arraycopy(jArr2, 0, jArr, 0, jArr2.length);
            Object[] objArr2 = this.f2265e;
            System.arraycopy(objArr2, 0, objArr, 0, objArr2.length);
            this.f2264d = jArr;
            this.f2265e = objArr;
        }
        this.f2264d[i3] = j;
        this.f2265e[i3] = e2;
        this.f2266f = i3 + 1;
    }

    public void c() {
        int i2 = this.f2266f;
        Object[] objArr = this.f2265e;
        for (int i3 = 0; i3 < i2; i3++) {
            objArr[i3] = null;
        }
        this.f2266f = 0;
        this.f2263c = false;
    }

    /* JADX INFO: renamed from: e, reason: merged with bridge method [inline-methods] */
    public d<E> clone() {
        try {
            d<E> dVar = (d) super.clone();
            dVar.f2264d = (long[]) this.f2264d.clone();
            dVar.f2265e = (Object[]) this.f2265e.clone();
            return dVar;
        } catch (CloneNotSupportedException e2) {
            throw new AssertionError(e2);
        }
    }

    public E g(long j) {
        return h(j, null);
    }

    public E h(long j, E e2) {
        int iB = c.b(this.f2264d, this.f2266f, j);
        if (iB >= 0) {
            Object[] objArr = this.f2265e;
            if (objArr[iB] != f2262b) {
                return (E) objArr[iB];
            }
        }
        return e2;
    }

    public int i(long j) {
        if (this.f2263c) {
            f();
        }
        return c.b(this.f2264d, this.f2266f, j);
    }

    public long k(int i2) {
        if (this.f2263c) {
            f();
        }
        return this.f2264d[i2];
    }

    public void l(long j, E e2) {
        int iB = c.b(this.f2264d, this.f2266f, j);
        if (iB >= 0) {
            this.f2265e[iB] = e2;
            return;
        }
        int i2 = ~iB;
        int i3 = this.f2266f;
        if (i2 < i3) {
            Object[] objArr = this.f2265e;
            if (objArr[i2] == f2262b) {
                this.f2264d[i2] = j;
                objArr[i2] = e2;
                return;
            }
        }
        if (this.f2263c && i3 >= this.f2264d.length) {
            f();
            i2 = ~c.b(this.f2264d, this.f2266f, j);
        }
        int i4 = this.f2266f;
        if (i4 >= this.f2264d.length) {
            int iF = c.f(i4 + 1);
            long[] jArr = new long[iF];
            Object[] objArr2 = new Object[iF];
            long[] jArr2 = this.f2264d;
            System.arraycopy(jArr2, 0, jArr, 0, jArr2.length);
            Object[] objArr3 = this.f2265e;
            System.arraycopy(objArr3, 0, objArr2, 0, objArr3.length);
            this.f2264d = jArr;
            this.f2265e = objArr2;
        }
        int i5 = this.f2266f;
        if (i5 - i2 != 0) {
            long[] jArr3 = this.f2264d;
            int i6 = i2 + 1;
            System.arraycopy(jArr3, i2, jArr3, i6, i5 - i2);
            Object[] objArr4 = this.f2265e;
            System.arraycopy(objArr4, i2, objArr4, i6, this.f2266f - i2);
        }
        this.f2264d[i2] = j;
        this.f2265e[i2] = e2;
        this.f2266f++;
    }

    public void m(long j) {
        int iB = c.b(this.f2264d, this.f2266f, j);
        if (iB >= 0) {
            Object[] objArr = this.f2265e;
            Object obj = objArr[iB];
            Object obj2 = f2262b;
            if (obj != obj2) {
                objArr[iB] = obj2;
                this.f2263c = true;
            }
        }
    }

    public void n(int i2) {
        Object[] objArr = this.f2265e;
        Object obj = objArr[i2];
        Object obj2 = f2262b;
        if (obj != obj2) {
            objArr[i2] = obj2;
            this.f2263c = true;
        }
    }

    public int o() {
        if (this.f2263c) {
            f();
        }
        return this.f2266f;
    }

    public E p(int i2) {
        if (this.f2263c) {
            f();
        }
        return (E) this.f2265e[i2];
    }

    public String toString() {
        if (o() <= 0) {
            return "{}";
        }
        StringBuilder sb = new StringBuilder(this.f2266f * 28);
        sb.append('{');
        for (int i2 = 0; i2 < this.f2266f; i2++) {
            if (i2 > 0) {
                sb.append(", ");
            }
            sb.append(k(i2));
            sb.append('=');
            E eP = p(i2);
            if (eP != this) {
                sb.append(eP);
            } else {
                sb.append("(this Map)");
            }
        }
        sb.append('}');
        return sb.toString();
    }

    public d(int i2) {
        this.f2263c = false;
        if (i2 == 0) {
            this.f2264d = c.f2260b;
            this.f2265e = c.f2261c;
        } else {
            int iF = c.f(i2);
            this.f2264d = new long[iF];
            this.f2265e = new Object[iF];
        }
    }
}
