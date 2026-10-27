package b.e;

/* JADX INFO: compiled from: LongSparseArray.java */
/* JADX INFO: loaded from: classes.dex */
public class d<E> implements Cloneable {

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public static final Object f886b = new Object();

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public boolean f887c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public long[] f888d;
    public Object[] e;
    public int f;

    public d() {
        this(10);
    }

    public void a(long j, E e) {
        int i = this.f;
        if (i != 0 && j <= this.f888d[i - 1]) {
            i(j, e);
            return;
        }
        if (this.f887c && i >= this.f888d.length) {
            d();
        }
        int i2 = this.f;
        if (i2 >= this.f888d.length) {
            int iF = c.f(i2 + 1);
            long[] jArr = new long[iF];
            Object[] objArr = new Object[iF];
            long[] jArr2 = this.f888d;
            System.arraycopy(jArr2, 0, jArr, 0, jArr2.length);
            Object[] objArr2 = this.e;
            System.arraycopy(objArr2, 0, objArr, 0, objArr2.length);
            this.f888d = jArr;
            this.e = objArr;
        }
        this.f888d[i2] = j;
        this.e[i2] = e;
        this.f = i2 + 1;
    }

    public void b() {
        int i = this.f;
        Object[] objArr = this.e;
        for (int i2 = 0; i2 < i; i2++) {
            objArr[i2] = null;
        }
        this.f = 0;
        this.f887c = false;
    }

    /* JADX INFO: renamed from: c, reason: merged with bridge method [inline-methods] */
    public d<E> clone() {
        try {
            d<E> dVar = (d) super.clone();
            dVar.f888d = (long[]) this.f888d.clone();
            dVar.e = (Object[]) this.e.clone();
            return dVar;
        } catch (CloneNotSupportedException e) {
            throw new AssertionError(e);
        }
    }

    public final void d() {
        int i = this.f;
        long[] jArr = this.f888d;
        Object[] objArr = this.e;
        int i2 = 0;
        for (int i3 = 0; i3 < i; i3++) {
            Object obj = objArr[i3];
            if (obj != f886b) {
                if (i3 != i2) {
                    jArr[i2] = jArr[i3];
                    objArr[i2] = obj;
                    objArr[i3] = null;
                }
                i2++;
            }
        }
        this.f887c = false;
        this.f = i2;
    }

    public E e(long j) {
        return f(j, null);
    }

    public E f(long j, E e) {
        int iB = c.b(this.f888d, this.f, j);
        if (iB >= 0) {
            Object[] objArr = this.e;
            if (objArr[iB] != f886b) {
                return (E) objArr[iB];
            }
        }
        return e;
    }

    public int g(long j) {
        if (this.f887c) {
            d();
        }
        return c.b(this.f888d, this.f, j);
    }

    public long h(int i) {
        if (this.f887c) {
            d();
        }
        return this.f888d[i];
    }

    public void i(long j, E e) {
        int iB = c.b(this.f888d, this.f, j);
        if (iB >= 0) {
            this.e[iB] = e;
            return;
        }
        int i = ~iB;
        int i2 = this.f;
        if (i < i2) {
            Object[] objArr = this.e;
            if (objArr[i] == f886b) {
                this.f888d[i] = j;
                objArr[i] = e;
                return;
            }
        }
        if (this.f887c && i2 >= this.f888d.length) {
            d();
            i = ~c.b(this.f888d, this.f, j);
        }
        int i3 = this.f;
        if (i3 >= this.f888d.length) {
            int iF = c.f(i3 + 1);
            long[] jArr = new long[iF];
            Object[] objArr2 = new Object[iF];
            long[] jArr2 = this.f888d;
            System.arraycopy(jArr2, 0, jArr, 0, jArr2.length);
            Object[] objArr3 = this.e;
            System.arraycopy(objArr3, 0, objArr2, 0, objArr3.length);
            this.f888d = jArr;
            this.e = objArr2;
        }
        int i4 = this.f;
        if (i4 - i != 0) {
            long[] jArr3 = this.f888d;
            int i5 = i + 1;
            System.arraycopy(jArr3, i, jArr3, i5, i4 - i);
            Object[] objArr4 = this.e;
            System.arraycopy(objArr4, i, objArr4, i5, this.f - i);
        }
        this.f888d[i] = j;
        this.e[i] = e;
        this.f++;
    }

    public void j(long j) {
        int iB = c.b(this.f888d, this.f, j);
        if (iB >= 0) {
            Object[] objArr = this.e;
            Object obj = objArr[iB];
            Object obj2 = f886b;
            if (obj != obj2) {
                objArr[iB] = obj2;
                this.f887c = true;
            }
        }
    }

    public void k(int i) {
        Object[] objArr = this.e;
        Object obj = objArr[i];
        Object obj2 = f886b;
        if (obj != obj2) {
            objArr[i] = obj2;
            this.f887c = true;
        }
    }

    public int l() {
        if (this.f887c) {
            d();
        }
        return this.f;
    }

    public E m(int i) {
        if (this.f887c) {
            d();
        }
        return (E) this.e[i];
    }

    public String toString() {
        if (l() <= 0) {
            return "{}";
        }
        StringBuilder sb = new StringBuilder(this.f * 28);
        sb.append('{');
        for (int i = 0; i < this.f; i++) {
            if (i > 0) {
                sb.append(", ");
            }
            sb.append(h(i));
            sb.append('=');
            E eM = m(i);
            if (eM != this) {
                sb.append(eM);
            } else {
                sb.append("(this Map)");
            }
        }
        sb.append('}');
        return sb.toString();
    }

    public d(int i) {
        this.f887c = false;
        if (i == 0) {
            this.f888d = c.f884b;
            this.e = c.f885c;
        } else {
            int iF = c.f(i);
            this.f888d = new long[iF];
            this.e = new Object[iF];
        }
    }
}
