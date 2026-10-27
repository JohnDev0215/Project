package s;

/* JADX INFO: compiled from: LongSparseArray.java */
/* JADX INFO: loaded from: classes.dex */
public class d<E> implements Cloneable {

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public static final Object f4856b = new Object();

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public boolean f4857c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public long[] f4858d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public Object[] f4859e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public int f4860f;

    public d() {
        this(10);
    }

    public void a(long j3, E e3) {
        int i3 = this.f4860f;
        if (i3 != 0 && j3 <= this.f4858d[i3 - 1]) {
            i(j3, e3);
            return;
        }
        if (this.f4857c && i3 >= this.f4858d.length) {
            d();
        }
        int i4 = this.f4860f;
        if (i4 >= this.f4858d.length) {
            int iF = c.f(i4 + 1);
            long[] jArr = new long[iF];
            Object[] objArr = new Object[iF];
            long[] jArr2 = this.f4858d;
            System.arraycopy(jArr2, 0, jArr, 0, jArr2.length);
            Object[] objArr2 = this.f4859e;
            System.arraycopy(objArr2, 0, objArr, 0, objArr2.length);
            this.f4858d = jArr;
            this.f4859e = objArr;
        }
        this.f4858d[i4] = j3;
        this.f4859e[i4] = e3;
        this.f4860f = i4 + 1;
    }

    public void b() {
        int i3 = this.f4860f;
        Object[] objArr = this.f4859e;
        for (int i4 = 0; i4 < i3; i4++) {
            objArr[i4] = null;
        }
        this.f4860f = 0;
        this.f4857c = false;
    }

    /* JADX INFO: renamed from: c, reason: merged with bridge method [inline-methods] */
    public d<E> clone() {
        try {
            d<E> dVar = (d) super.clone();
            dVar.f4858d = (long[]) this.f4858d.clone();
            dVar.f4859e = (Object[]) this.f4859e.clone();
            return dVar;
        } catch (CloneNotSupportedException e3) {
            throw new AssertionError(e3);
        }
    }

    public final void d() {
        int i3 = this.f4860f;
        long[] jArr = this.f4858d;
        Object[] objArr = this.f4859e;
        int i4 = 0;
        for (int i5 = 0; i5 < i3; i5++) {
            Object obj = objArr[i5];
            if (obj != f4856b) {
                if (i5 != i4) {
                    jArr[i4] = jArr[i5];
                    objArr[i4] = obj;
                    objArr[i5] = null;
                }
                i4++;
            }
        }
        this.f4857c = false;
        this.f4860f = i4;
    }

    public E e(long j3) {
        return f(j3, null);
    }

    public E f(long j3, E e3) {
        int iB = c.b(this.f4858d, this.f4860f, j3);
        if (iB >= 0) {
            Object[] objArr = this.f4859e;
            if (objArr[iB] != f4856b) {
                return (E) objArr[iB];
            }
        }
        return e3;
    }

    public int g(long j3) {
        if (this.f4857c) {
            d();
        }
        return c.b(this.f4858d, this.f4860f, j3);
    }

    public long h(int i3) {
        if (this.f4857c) {
            d();
        }
        return this.f4858d[i3];
    }

    public void i(long j3, E e3) {
        int iB = c.b(this.f4858d, this.f4860f, j3);
        if (iB >= 0) {
            this.f4859e[iB] = e3;
            return;
        }
        int i3 = ~iB;
        int i4 = this.f4860f;
        if (i3 < i4) {
            Object[] objArr = this.f4859e;
            if (objArr[i3] == f4856b) {
                this.f4858d[i3] = j3;
                objArr[i3] = e3;
                return;
            }
        }
        if (this.f4857c && i4 >= this.f4858d.length) {
            d();
            i3 = ~c.b(this.f4858d, this.f4860f, j3);
        }
        int i5 = this.f4860f;
        if (i5 >= this.f4858d.length) {
            int iF = c.f(i5 + 1);
            long[] jArr = new long[iF];
            Object[] objArr2 = new Object[iF];
            long[] jArr2 = this.f4858d;
            System.arraycopy(jArr2, 0, jArr, 0, jArr2.length);
            Object[] objArr3 = this.f4859e;
            System.arraycopy(objArr3, 0, objArr2, 0, objArr3.length);
            this.f4858d = jArr;
            this.f4859e = objArr2;
        }
        int i6 = this.f4860f;
        if (i6 - i3 != 0) {
            long[] jArr3 = this.f4858d;
            int i7 = i3 + 1;
            System.arraycopy(jArr3, i3, jArr3, i7, i6 - i3);
            Object[] objArr4 = this.f4859e;
            System.arraycopy(objArr4, i3, objArr4, i7, this.f4860f - i3);
        }
        this.f4858d[i3] = j3;
        this.f4859e[i3] = e3;
        this.f4860f++;
    }

    public void j(long j3) {
        int iB = c.b(this.f4858d, this.f4860f, j3);
        if (iB >= 0) {
            Object[] objArr = this.f4859e;
            Object obj = objArr[iB];
            Object obj2 = f4856b;
            if (obj != obj2) {
                objArr[iB] = obj2;
                this.f4857c = true;
            }
        }
    }

    public void k(int i3) {
        Object[] objArr = this.f4859e;
        Object obj = objArr[i3];
        Object obj2 = f4856b;
        if (obj != obj2) {
            objArr[i3] = obj2;
            this.f4857c = true;
        }
    }

    public int l() {
        if (this.f4857c) {
            d();
        }
        return this.f4860f;
    }

    public E m(int i3) {
        if (this.f4857c) {
            d();
        }
        return (E) this.f4859e[i3];
    }

    public String toString() {
        if (l() <= 0) {
            return "{}";
        }
        StringBuilder sb = new StringBuilder(this.f4860f * 28);
        sb.append('{');
        for (int i3 = 0; i3 < this.f4860f; i3++) {
            if (i3 > 0) {
                sb.append(", ");
            }
            sb.append(h(i3));
            sb.append('=');
            E eM = m(i3);
            if (eM != this) {
                sb.append(eM);
            } else {
                sb.append("(this Map)");
            }
        }
        sb.append('}');
        return sb.toString();
    }

    public d(int i3) {
        this.f4857c = false;
        if (i3 == 0) {
            this.f4858d = c.f4854b;
            this.f4859e = c.f4855c;
        } else {
            int iF = c.f(i3);
            this.f4858d = new long[iF];
            this.f4859e = new Object[iF];
        }
    }
}
