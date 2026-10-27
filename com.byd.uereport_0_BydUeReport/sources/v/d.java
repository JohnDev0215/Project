package v;

/* JADX INFO: compiled from: LongSparseArray.java */
/* JADX INFO: loaded from: classes.dex */
public class d<E> implements Cloneable {

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public static final Object f8314d = new Object();

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public boolean f8315e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public long[] f8316f;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public Object[] f8317g;

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    public int f8318h;

    public d() {
        this(10);
    }

    public void a(long j8, E e8) {
        int i8 = this.f8318h;
        if (i8 != 0 && j8 <= this.f8316f[i8 - 1]) {
            k(j8, e8);
            return;
        }
        if (this.f8315e && i8 >= this.f8316f.length) {
            e();
        }
        int i9 = this.f8318h;
        if (i9 >= this.f8316f.length) {
            int iF = c.f(i9 + 1);
            long[] jArr = new long[iF];
            Object[] objArr = new Object[iF];
            long[] jArr2 = this.f8316f;
            System.arraycopy(jArr2, 0, jArr, 0, jArr2.length);
            Object[] objArr2 = this.f8317g;
            System.arraycopy(objArr2, 0, objArr, 0, objArr2.length);
            this.f8316f = jArr;
            this.f8317g = objArr;
        }
        this.f8316f[i9] = j8;
        this.f8317g[i9] = e8;
        this.f8318h = i9 + 1;
    }

    public void b() {
        int i8 = this.f8318h;
        Object[] objArr = this.f8317g;
        for (int i9 = 0; i9 < i8; i9++) {
            objArr[i9] = null;
        }
        this.f8318h = 0;
        this.f8315e = false;
    }

    /* JADX INFO: renamed from: c, reason: merged with bridge method [inline-methods] */
    public d<E> clone() {
        try {
            d<E> dVar = (d) super.clone();
            dVar.f8316f = (long[]) this.f8316f.clone();
            dVar.f8317g = (Object[]) this.f8317g.clone();
            return dVar;
        } catch (CloneNotSupportedException e8) {
            throw new AssertionError(e8);
        }
    }

    public boolean d(long j8) {
        return h(j8) >= 0;
    }

    public final void e() {
        int i8 = this.f8318h;
        long[] jArr = this.f8316f;
        Object[] objArr = this.f8317g;
        int i9 = 0;
        for (int i10 = 0; i10 < i8; i10++) {
            Object obj = objArr[i10];
            if (obj != f8314d) {
                if (i10 != i9) {
                    jArr[i9] = jArr[i10];
                    objArr[i9] = obj;
                    objArr[i10] = null;
                }
                i9++;
            }
        }
        this.f8315e = false;
        this.f8318h = i9;
    }

    public E f(long j8) {
        return g(j8, null);
    }

    public E g(long j8, E e8) {
        int iB = c.b(this.f8316f, this.f8318h, j8);
        if (iB >= 0) {
            Object[] objArr = this.f8317g;
            if (objArr[iB] != f8314d) {
                return (E) objArr[iB];
            }
        }
        return e8;
    }

    public int h(long j8) {
        if (this.f8315e) {
            e();
        }
        return c.b(this.f8316f, this.f8318h, j8);
    }

    public boolean i() {
        return n() == 0;
    }

    public long j(int i8) {
        if (this.f8315e) {
            e();
        }
        return this.f8316f[i8];
    }

    public void k(long j8, E e8) {
        int iB = c.b(this.f8316f, this.f8318h, j8);
        if (iB >= 0) {
            this.f8317g[iB] = e8;
            return;
        }
        int i8 = ~iB;
        int i9 = this.f8318h;
        if (i8 < i9) {
            Object[] objArr = this.f8317g;
            if (objArr[i8] == f8314d) {
                this.f8316f[i8] = j8;
                objArr[i8] = e8;
                return;
            }
        }
        if (this.f8315e && i9 >= this.f8316f.length) {
            e();
            i8 = ~c.b(this.f8316f, this.f8318h, j8);
        }
        int i10 = this.f8318h;
        if (i10 >= this.f8316f.length) {
            int iF = c.f(i10 + 1);
            long[] jArr = new long[iF];
            Object[] objArr2 = new Object[iF];
            long[] jArr2 = this.f8316f;
            System.arraycopy(jArr2, 0, jArr, 0, jArr2.length);
            Object[] objArr3 = this.f8317g;
            System.arraycopy(objArr3, 0, objArr2, 0, objArr3.length);
            this.f8316f = jArr;
            this.f8317g = objArr2;
        }
        int i11 = this.f8318h;
        if (i11 - i8 != 0) {
            long[] jArr3 = this.f8316f;
            int i12 = i8 + 1;
            System.arraycopy(jArr3, i8, jArr3, i12, i11 - i8);
            Object[] objArr4 = this.f8317g;
            System.arraycopy(objArr4, i8, objArr4, i12, this.f8318h - i8);
        }
        this.f8316f[i8] = j8;
        this.f8317g[i8] = e8;
        this.f8318h++;
    }

    public void l(long j8) {
        int iB = c.b(this.f8316f, this.f8318h, j8);
        if (iB >= 0) {
            Object[] objArr = this.f8317g;
            Object obj = objArr[iB];
            Object obj2 = f8314d;
            if (obj != obj2) {
                objArr[iB] = obj2;
                this.f8315e = true;
            }
        }
    }

    public void m(int i8) {
        Object[] objArr = this.f8317g;
        Object obj = objArr[i8];
        Object obj2 = f8314d;
        if (obj != obj2) {
            objArr[i8] = obj2;
            this.f8315e = true;
        }
    }

    public int n() {
        if (this.f8315e) {
            e();
        }
        return this.f8318h;
    }

    public E o(int i8) {
        if (this.f8315e) {
            e();
        }
        return (E) this.f8317g[i8];
    }

    public String toString() {
        if (n() <= 0) {
            return "{}";
        }
        StringBuilder sb = new StringBuilder(this.f8318h * 28);
        sb.append('{');
        for (int i8 = 0; i8 < this.f8318h; i8++) {
            if (i8 > 0) {
                sb.append(", ");
            }
            sb.append(j(i8));
            sb.append('=');
            E eO = o(i8);
            if (eO != this) {
                sb.append(eO);
            } else {
                sb.append("(this Map)");
            }
        }
        sb.append('}');
        return sb.toString();
    }

    public d(int i8) {
        this.f8315e = false;
        if (i8 == 0) {
            this.f8316f = c.f8312b;
            this.f8317g = c.f8313c;
        } else {
            int iF = c.f(i8);
            this.f8316f = new long[iF];
            this.f8317g = new Object[iF];
        }
    }
}
