package b.d;

/* JADX INFO: compiled from: LongSparseArray.java */
/* JADX INFO: loaded from: classes.dex */
public class d<E> implements Cloneable {

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private static final Object f1740b = new Object();

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    private boolean f1741c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    private long[] f1742d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    private Object[] f1743e;
    private int f;

    public d() {
        this(10);
    }

    private void e() {
        int i = this.f;
        long[] jArr = this.f1742d;
        Object[] objArr = this.f1743e;
        int i2 = 0;
        for (int i3 = 0; i3 < i; i3++) {
            Object obj = objArr[i3];
            if (obj != f1740b) {
                if (i3 != i2) {
                    jArr[i2] = jArr[i3];
                    objArr[i2] = obj;
                    objArr[i3] = null;
                }
                i2++;
            }
        }
        this.f1741c = false;
        this.f = i2;
    }

    public void a(long j, E e2) {
        int i = this.f;
        if (i != 0 && j <= this.f1742d[i - 1]) {
            j(j, e2);
            return;
        }
        if (this.f1741c && i >= this.f1742d.length) {
            e();
        }
        int i2 = this.f;
        if (i2 >= this.f1742d.length) {
            int iF = c.f(i2 + 1);
            long[] jArr = new long[iF];
            Object[] objArr = new Object[iF];
            long[] jArr2 = this.f1742d;
            System.arraycopy(jArr2, 0, jArr, 0, jArr2.length);
            Object[] objArr2 = this.f1743e;
            System.arraycopy(objArr2, 0, objArr, 0, objArr2.length);
            this.f1742d = jArr;
            this.f1743e = objArr;
        }
        this.f1742d[i2] = j;
        this.f1743e[i2] = e2;
        this.f = i2 + 1;
    }

    public void b() {
        int i = this.f;
        Object[] objArr = this.f1743e;
        for (int i2 = 0; i2 < i; i2++) {
            objArr[i2] = null;
        }
        this.f = 0;
        this.f1741c = false;
    }

    /* JADX INFO: renamed from: c, reason: merged with bridge method [inline-methods] */
    public d<E> clone() {
        try {
            d<E> dVar = (d) super.clone();
            dVar.f1742d = (long[]) this.f1742d.clone();
            dVar.f1743e = (Object[]) this.f1743e.clone();
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
        int iB = c.b(this.f1742d, this.f, j);
        if (iB >= 0) {
            Object[] objArr = this.f1743e;
            if (objArr[iB] != f1740b) {
                return (E) objArr[iB];
            }
        }
        return e2;
    }

    public int h(long j) {
        if (this.f1741c) {
            e();
        }
        return c.b(this.f1742d, this.f, j);
    }

    public long i(int i) {
        if (this.f1741c) {
            e();
        }
        return this.f1742d[i];
    }

    public void j(long j, E e2) {
        int iB = c.b(this.f1742d, this.f, j);
        if (iB >= 0) {
            this.f1743e[iB] = e2;
            return;
        }
        int i = ~iB;
        int i2 = this.f;
        if (i < i2) {
            Object[] objArr = this.f1743e;
            if (objArr[i] == f1740b) {
                this.f1742d[i] = j;
                objArr[i] = e2;
                return;
            }
        }
        if (this.f1741c && i2 >= this.f1742d.length) {
            e();
            i = ~c.b(this.f1742d, this.f, j);
        }
        int i3 = this.f;
        if (i3 >= this.f1742d.length) {
            int iF = c.f(i3 + 1);
            long[] jArr = new long[iF];
            Object[] objArr2 = new Object[iF];
            long[] jArr2 = this.f1742d;
            System.arraycopy(jArr2, 0, jArr, 0, jArr2.length);
            Object[] objArr3 = this.f1743e;
            System.arraycopy(objArr3, 0, objArr2, 0, objArr3.length);
            this.f1742d = jArr;
            this.f1743e = objArr2;
        }
        int i4 = this.f;
        if (i4 - i != 0) {
            long[] jArr3 = this.f1742d;
            int i5 = i + 1;
            System.arraycopy(jArr3, i, jArr3, i5, i4 - i);
            Object[] objArr4 = this.f1743e;
            System.arraycopy(objArr4, i, objArr4, i5, this.f - i);
        }
        this.f1742d[i] = j;
        this.f1743e[i] = e2;
        this.f++;
    }

    public void k(long j) {
        int iB = c.b(this.f1742d, this.f, j);
        if (iB >= 0) {
            Object[] objArr = this.f1743e;
            Object obj = objArr[iB];
            Object obj2 = f1740b;
            if (obj != obj2) {
                objArr[iB] = obj2;
                this.f1741c = true;
            }
        }
    }

    public void l(int i) {
        Object[] objArr = this.f1743e;
        Object obj = objArr[i];
        Object obj2 = f1740b;
        if (obj != obj2) {
            objArr[i] = obj2;
            this.f1741c = true;
        }
    }

    public int m() {
        if (this.f1741c) {
            e();
        }
        return this.f;
    }

    public E n(int i) {
        if (this.f1741c) {
            e();
        }
        return (E) this.f1743e[i];
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
        this.f1741c = false;
        if (i == 0) {
            this.f1742d = c.f1738b;
            this.f1743e = c.f1739c;
        } else {
            int iF = c.f(i);
            this.f1742d = new long[iF];
            this.f1743e = new Object[iF];
        }
    }
}
