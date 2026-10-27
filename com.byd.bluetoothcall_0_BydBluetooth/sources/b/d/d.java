package b.d;

/* JADX INFO: compiled from: LongSparseArray.java */
/* JADX INFO: loaded from: classes.dex */
public class d<E> implements Cloneable {

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private static final Object f2122b = new Object();

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    private boolean f2123c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    private long[] f2124d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    private Object[] f2125e;
    private int f;

    public d() {
        this(10);
    }

    private void e() {
        int i = this.f;
        long[] jArr = this.f2124d;
        Object[] objArr = this.f2125e;
        int i2 = 0;
        for (int i3 = 0; i3 < i; i3++) {
            Object obj = objArr[i3];
            if (obj != f2122b) {
                if (i3 != i2) {
                    jArr[i2] = jArr[i3];
                    objArr[i2] = obj;
                    objArr[i3] = null;
                }
                i2++;
            }
        }
        this.f2123c = false;
        this.f = i2;
    }

    public void a(long j, E e2) {
        int i = this.f;
        if (i != 0 && j <= this.f2124d[i - 1]) {
            j(j, e2);
            return;
        }
        if (this.f2123c && i >= this.f2124d.length) {
            e();
        }
        int i2 = this.f;
        if (i2 >= this.f2124d.length) {
            int iF = c.f(i2 + 1);
            long[] jArr = new long[iF];
            Object[] objArr = new Object[iF];
            long[] jArr2 = this.f2124d;
            System.arraycopy(jArr2, 0, jArr, 0, jArr2.length);
            Object[] objArr2 = this.f2125e;
            System.arraycopy(objArr2, 0, objArr, 0, objArr2.length);
            this.f2124d = jArr;
            this.f2125e = objArr;
        }
        this.f2124d[i2] = j;
        this.f2125e[i2] = e2;
        this.f = i2 + 1;
    }

    public void b() {
        int i = this.f;
        Object[] objArr = this.f2125e;
        for (int i2 = 0; i2 < i; i2++) {
            objArr[i2] = null;
        }
        this.f = 0;
        this.f2123c = false;
    }

    /* JADX INFO: renamed from: c, reason: merged with bridge method [inline-methods] */
    public d<E> clone() {
        try {
            d<E> dVar = (d) super.clone();
            dVar.f2124d = (long[]) this.f2124d.clone();
            dVar.f2125e = (Object[]) this.f2125e.clone();
            return dVar;
        } catch (CloneNotSupportedException e2) {
            throw new AssertionError(e2);
        }
    }

    public boolean d(long j) {
        return h(j) >= 0;
    }

    public E f(long j) {
        return g(j, null);
    }

    public E g(long j, E e2) {
        int iB = c.b(this.f2124d, this.f, j);
        if (iB >= 0) {
            Object[] objArr = this.f2125e;
            if (objArr[iB] != f2122b) {
                return (E) objArr[iB];
            }
        }
        return e2;
    }

    public int h(long j) {
        if (this.f2123c) {
            e();
        }
        return c.b(this.f2124d, this.f, j);
    }

    public long i(int i) {
        if (this.f2123c) {
            e();
        }
        return this.f2124d[i];
    }

    public void j(long j, E e2) {
        int iB = c.b(this.f2124d, this.f, j);
        if (iB >= 0) {
            this.f2125e[iB] = e2;
            return;
        }
        int i = ~iB;
        int i2 = this.f;
        if (i < i2) {
            Object[] objArr = this.f2125e;
            if (objArr[i] == f2122b) {
                this.f2124d[i] = j;
                objArr[i] = e2;
                return;
            }
        }
        if (this.f2123c && i2 >= this.f2124d.length) {
            e();
            i = ~c.b(this.f2124d, this.f, j);
        }
        int i3 = this.f;
        if (i3 >= this.f2124d.length) {
            int iF = c.f(i3 + 1);
            long[] jArr = new long[iF];
            Object[] objArr2 = new Object[iF];
            long[] jArr2 = this.f2124d;
            System.arraycopy(jArr2, 0, jArr, 0, jArr2.length);
            Object[] objArr3 = this.f2125e;
            System.arraycopy(objArr3, 0, objArr2, 0, objArr3.length);
            this.f2124d = jArr;
            this.f2125e = objArr2;
        }
        int i4 = this.f;
        if (i4 - i != 0) {
            long[] jArr3 = this.f2124d;
            int i5 = i + 1;
            System.arraycopy(jArr3, i, jArr3, i5, i4 - i);
            Object[] objArr4 = this.f2125e;
            System.arraycopy(objArr4, i, objArr4, i5, this.f - i);
        }
        this.f2124d[i] = j;
        this.f2125e[i] = e2;
        this.f++;
    }

    public void k(long j) {
        int iB = c.b(this.f2124d, this.f, j);
        if (iB >= 0) {
            Object[] objArr = this.f2125e;
            Object obj = objArr[iB];
            Object obj2 = f2122b;
            if (obj != obj2) {
                objArr[iB] = obj2;
                this.f2123c = true;
            }
        }
    }

    public void l(int i) {
        Object[] objArr = this.f2125e;
        Object obj = objArr[i];
        Object obj2 = f2122b;
        if (obj != obj2) {
            objArr[i] = obj2;
            this.f2123c = true;
        }
    }

    public int m() {
        if (this.f2123c) {
            e();
        }
        return this.f;
    }

    public E n(int i) {
        if (this.f2123c) {
            e();
        }
        return (E) this.f2125e[i];
    }

    public String toString() {
        if (m() <= 0) {
            return "{}";
        }
        StringBuilder sb = new StringBuilder(this.f * 28);
        sb.append('{');
        for (int i = 0; i < this.f; i++) {
            if (i > 0) {
                sb.append(", ");
            }
            sb.append(i(i));
            sb.append('=');
            E eN = n(i);
            if (eN != this) {
                sb.append(eN);
            } else {
                sb.append("(this Map)");
            }
        }
        sb.append('}');
        return sb.toString();
    }

    public d(int i) {
        this.f2123c = false;
        if (i == 0) {
            this.f2124d = c.f2120b;
            this.f2125e = c.f2121c;
        } else {
            int iF = c.f(i);
            this.f2124d = new long[iF];
            this.f2125e = new Object[iF];
        }
    }
}
