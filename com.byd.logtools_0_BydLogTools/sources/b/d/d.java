package b.d;

/* JADX INFO: compiled from: LongSparseArray.java */
/* JADX INFO: loaded from: classes.dex */
public class d<E> implements Cloneable {

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private static final Object f2265b = new Object();

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    private boolean f2266c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    private long[] f2267d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    private Object[] f2268e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    private int f2269f;

    public d() {
        this(10);
    }

    private void d() {
        int i = this.f2269f;
        long[] jArr = this.f2267d;
        Object[] objArr = this.f2268e;
        int i2 = 0;
        for (int i3 = 0; i3 < i; i3++) {
            Object obj = objArr[i3];
            if (obj != f2265b) {
                if (i3 != i2) {
                    jArr[i2] = jArr[i3];
                    objArr[i2] = obj;
                    objArr[i3] = null;
                }
                i2++;
            }
        }
        this.f2266c = false;
        this.f2269f = i2;
    }

    public void a(long j, E e2) {
        int i = this.f2269f;
        if (i != 0 && j <= this.f2267d[i - 1]) {
            j(j, e2);
            return;
        }
        if (this.f2266c && i >= this.f2267d.length) {
            d();
        }
        int i2 = this.f2269f;
        if (i2 >= this.f2267d.length) {
            int iF = c.f(i2 + 1);
            long[] jArr = new long[iF];
            Object[] objArr = new Object[iF];
            long[] jArr2 = this.f2267d;
            System.arraycopy(jArr2, 0, jArr, 0, jArr2.length);
            Object[] objArr2 = this.f2268e;
            System.arraycopy(objArr2, 0, objArr, 0, objArr2.length);
            this.f2267d = jArr;
            this.f2268e = objArr;
        }
        this.f2267d[i2] = j;
        this.f2268e[i2] = e2;
        this.f2269f = i2 + 1;
    }

    public void b() {
        int i = this.f2269f;
        Object[] objArr = this.f2268e;
        for (int i2 = 0; i2 < i; i2++) {
            objArr[i2] = null;
        }
        this.f2269f = 0;
        this.f2266c = false;
    }

    /* JADX INFO: renamed from: c, reason: merged with bridge method [inline-methods] */
    public d<E> clone() {
        try {
            d<E> dVar = (d) super.clone();
            dVar.f2267d = (long[]) this.f2267d.clone();
            dVar.f2268e = (Object[]) this.f2268e.clone();
            return dVar;
        } catch (CloneNotSupportedException e2) {
            throw new AssertionError(e2);
        }
    }

    public E f(long j) {
        return g(j, null);
    }

    public E g(long j, E e2) {
        int iB = c.b(this.f2267d, this.f2269f, j);
        if (iB >= 0) {
            Object[] objArr = this.f2268e;
            if (objArr[iB] != f2265b) {
                return (E) objArr[iB];
            }
        }
        return e2;
    }

    public int h(long j) {
        if (this.f2266c) {
            d();
        }
        return c.b(this.f2267d, this.f2269f, j);
    }

    public long i(int i) {
        if (this.f2266c) {
            d();
        }
        return this.f2267d[i];
    }

    public void j(long j, E e2) {
        int iB = c.b(this.f2267d, this.f2269f, j);
        if (iB >= 0) {
            this.f2268e[iB] = e2;
            return;
        }
        int i = ~iB;
        int i2 = this.f2269f;
        if (i < i2) {
            Object[] objArr = this.f2268e;
            if (objArr[i] == f2265b) {
                this.f2267d[i] = j;
                objArr[i] = e2;
                return;
            }
        }
        if (this.f2266c && i2 >= this.f2267d.length) {
            d();
            i = ~c.b(this.f2267d, this.f2269f, j);
        }
        int i3 = this.f2269f;
        if (i3 >= this.f2267d.length) {
            int iF = c.f(i3 + 1);
            long[] jArr = new long[iF];
            Object[] objArr2 = new Object[iF];
            long[] jArr2 = this.f2267d;
            System.arraycopy(jArr2, 0, jArr, 0, jArr2.length);
            Object[] objArr3 = this.f2268e;
            System.arraycopy(objArr3, 0, objArr2, 0, objArr3.length);
            this.f2267d = jArr;
            this.f2268e = objArr2;
        }
        int i4 = this.f2269f;
        if (i4 - i != 0) {
            long[] jArr3 = this.f2267d;
            int i5 = i + 1;
            System.arraycopy(jArr3, i, jArr3, i5, i4 - i);
            Object[] objArr4 = this.f2268e;
            System.arraycopy(objArr4, i, objArr4, i5, this.f2269f - i);
        }
        this.f2267d[i] = j;
        this.f2268e[i] = e2;
        this.f2269f++;
    }

    public void k(long j) {
        int iB = c.b(this.f2267d, this.f2269f, j);
        if (iB >= 0) {
            Object[] objArr = this.f2268e;
            Object obj = objArr[iB];
            Object obj2 = f2265b;
            if (obj != obj2) {
                objArr[iB] = obj2;
                this.f2266c = true;
            }
        }
    }

    public void l(int i) {
        Object[] objArr = this.f2268e;
        Object obj = objArr[i];
        Object obj2 = f2265b;
        if (obj != obj2) {
            objArr[i] = obj2;
            this.f2266c = true;
        }
    }

    public int m() {
        if (this.f2266c) {
            d();
        }
        return this.f2269f;
    }

    public E n(int i) {
        if (this.f2266c) {
            d();
        }
        return (E) this.f2268e[i];
    }

    public String toString() {
        if (m() <= 0) {
            return "{}";
        }
        StringBuilder sb = new StringBuilder(this.f2269f * 28);
        sb.append('{');
        for (int i = 0; i < this.f2269f; i++) {
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
        this.f2266c = false;
        if (i == 0) {
            this.f2267d = c.f2263b;
            this.f2268e = c.f2264c;
        } else {
            int iF = c.f(i);
            this.f2267d = new long[iF];
            this.f2268e = new Object[iF];
        }
    }
}
