package n;

/* JADX INFO: compiled from: LongSparseArray.java */
/* JADX INFO: loaded from: classes.dex */
public class d<E> implements Cloneable {

    /* JADX INFO: renamed from: j, reason: collision with root package name */
    private static final Object f8451j = new Object();

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    private boolean f8452e;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    private long[] f8453g;

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    private Object[] f8454h;

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    private int f8455i;

    public d() {
        this(10);
    }

    private void d() {
        int i7 = this.f8455i;
        long[] jArr = this.f8453g;
        Object[] objArr = this.f8454h;
        int i8 = 0;
        for (int i9 = 0; i9 < i7; i9++) {
            Object obj = objArr[i9];
            if (obj != f8451j) {
                if (i9 != i8) {
                    jArr[i8] = jArr[i9];
                    objArr[i8] = obj;
                    objArr[i9] = null;
                }
                i8++;
            }
        }
        this.f8452e = false;
        this.f8455i = i8;
    }

    public void a() {
        int i7 = this.f8455i;
        Object[] objArr = this.f8454h;
        for (int i8 = 0; i8 < i7; i8++) {
            objArr[i8] = null;
        }
        this.f8455i = 0;
        this.f8452e = false;
    }

    /* JADX INFO: renamed from: b, reason: merged with bridge method [inline-methods] */
    public d<E> clone() {
        try {
            d<E> dVar = (d) super.clone();
            dVar.f8453g = (long[]) this.f8453g.clone();
            dVar.f8454h = (Object[]) this.f8454h.clone();
            return dVar;
        } catch (CloneNotSupportedException e7) {
            throw new AssertionError(e7);
        }
    }

    public boolean c(long j7) {
        return g(j7) >= 0;
    }

    public E e(long j7) {
        return f(j7, null);
    }

    public E f(long j7, E e7) {
        int iB = c.b(this.f8453g, this.f8455i, j7);
        if (iB >= 0) {
            Object[] objArr = this.f8454h;
            if (objArr[iB] != f8451j) {
                return (E) objArr[iB];
            }
        }
        return e7;
    }

    public int g(long j7) {
        if (this.f8452e) {
            d();
        }
        return c.b(this.f8453g, this.f8455i, j7);
    }

    public boolean h() {
        return n() == 0;
    }

    public long i(int i7) {
        if (this.f8452e) {
            d();
        }
        return this.f8453g[i7];
    }

    public void j(long j7, E e7) {
        int iB = c.b(this.f8453g, this.f8455i, j7);
        if (iB >= 0) {
            this.f8454h[iB] = e7;
            return;
        }
        int i7 = ~iB;
        int i8 = this.f8455i;
        if (i7 < i8) {
            Object[] objArr = this.f8454h;
            if (objArr[i7] == f8451j) {
                this.f8453g[i7] = j7;
                objArr[i7] = e7;
                return;
            }
        }
        if (this.f8452e && i8 >= this.f8453g.length) {
            d();
            i7 = ~c.b(this.f8453g, this.f8455i, j7);
        }
        int i9 = this.f8455i;
        if (i9 >= this.f8453g.length) {
            int iF = c.f(i9 + 1);
            long[] jArr = new long[iF];
            Object[] objArr2 = new Object[iF];
            long[] jArr2 = this.f8453g;
            System.arraycopy(jArr2, 0, jArr, 0, jArr2.length);
            Object[] objArr3 = this.f8454h;
            System.arraycopy(objArr3, 0, objArr2, 0, objArr3.length);
            this.f8453g = jArr;
            this.f8454h = objArr2;
        }
        int i10 = this.f8455i;
        if (i10 - i7 != 0) {
            long[] jArr3 = this.f8453g;
            int i11 = i7 + 1;
            System.arraycopy(jArr3, i7, jArr3, i11, i10 - i7);
            Object[] objArr4 = this.f8454h;
            System.arraycopy(objArr4, i7, objArr4, i11, this.f8455i - i7);
        }
        this.f8453g[i7] = j7;
        this.f8454h[i7] = e7;
        this.f8455i++;
    }

    public void k(long j7) {
        int iB = c.b(this.f8453g, this.f8455i, j7);
        if (iB >= 0) {
            Object[] objArr = this.f8454h;
            Object obj = objArr[iB];
            Object obj2 = f8451j;
            if (obj != obj2) {
                objArr[iB] = obj2;
                this.f8452e = true;
            }
        }
    }

    public void l(int i7) {
        Object[] objArr = this.f8454h;
        Object obj = objArr[i7];
        Object obj2 = f8451j;
        if (obj != obj2) {
            objArr[i7] = obj2;
            this.f8452e = true;
        }
    }

    public int n() {
        if (this.f8452e) {
            d();
        }
        return this.f8455i;
    }

    public E o(int i7) {
        if (this.f8452e) {
            d();
        }
        return (E) this.f8454h[i7];
    }

    public String toString() {
        if (n() <= 0) {
            return "{}";
        }
        StringBuilder sb = new StringBuilder(this.f8455i * 28);
        sb.append('{');
        for (int i7 = 0; i7 < this.f8455i; i7++) {
            if (i7 > 0) {
                sb.append(", ");
            }
            sb.append(i(i7));
            sb.append('=');
            E eO = o(i7);
            if (eO != this) {
                sb.append(eO);
            } else {
                sb.append("(this Map)");
            }
        }
        sb.append('}');
        return sb.toString();
    }

    public d(int i7) {
        this.f8452e = false;
        if (i7 == 0) {
            this.f8453g = c.f8449b;
            this.f8454h = c.f8450c;
        } else {
            int iF = c.f(i7);
            this.f8453g = new long[iF];
            this.f8454h = new Object[iF];
        }
    }
}
