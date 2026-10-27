package s;

/* JADX INFO: compiled from: SparseArrayCompat.java */
/* JADX INFO: loaded from: classes.dex */
public class h<E> implements Cloneable {

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public static final Object f4891b = new Object();

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public boolean f4892c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public int[] f4893d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public Object[] f4894e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public int f4895f;

    public h() {
        this(10);
    }

    public void a(int i3, E e3) {
        int i4 = this.f4895f;
        if (i4 != 0 && i3 <= this.f4893d[i4 - 1]) {
            j(i3, e3);
            return;
        }
        if (this.f4892c && i4 >= this.f4893d.length) {
            d();
        }
        int i5 = this.f4895f;
        if (i5 >= this.f4893d.length) {
            int iE = c.e(i5 + 1);
            int[] iArr = new int[iE];
            Object[] objArr = new Object[iE];
            int[] iArr2 = this.f4893d;
            System.arraycopy(iArr2, 0, iArr, 0, iArr2.length);
            Object[] objArr2 = this.f4894e;
            System.arraycopy(objArr2, 0, objArr, 0, objArr2.length);
            this.f4893d = iArr;
            this.f4894e = objArr;
        }
        this.f4893d[i5] = i3;
        this.f4894e[i5] = e3;
        this.f4895f = i5 + 1;
    }

    public void b() {
        int i3 = this.f4895f;
        Object[] objArr = this.f4894e;
        for (int i4 = 0; i4 < i3; i4++) {
            objArr[i4] = null;
        }
        this.f4895f = 0;
        this.f4892c = false;
    }

    /* JADX INFO: renamed from: c, reason: merged with bridge method [inline-methods] */
    public h<E> clone() {
        try {
            h<E> hVar = (h) super.clone();
            hVar.f4893d = (int[]) this.f4893d.clone();
            hVar.f4894e = (Object[]) this.f4894e.clone();
            return hVar;
        } catch (CloneNotSupportedException e3) {
            throw new AssertionError(e3);
        }
    }

    public final void d() {
        int i3 = this.f4895f;
        int[] iArr = this.f4893d;
        Object[] objArr = this.f4894e;
        int i4 = 0;
        for (int i5 = 0; i5 < i3; i5++) {
            Object obj = objArr[i5];
            if (obj != f4891b) {
                if (i5 != i4) {
                    iArr[i4] = iArr[i5];
                    objArr[i4] = obj;
                    objArr[i5] = null;
                }
                i4++;
            }
        }
        this.f4892c = false;
        this.f4895f = i4;
    }

    public E e(int i3) {
        return f(i3, null);
    }

    public E f(int i3, E e3) {
        int iA = c.a(this.f4893d, this.f4895f, i3);
        if (iA >= 0) {
            Object[] objArr = this.f4894e;
            if (objArr[iA] != f4891b) {
                return (E) objArr[iA];
            }
        }
        return e3;
    }

    public int g(int i3) {
        if (this.f4892c) {
            d();
        }
        return c.a(this.f4893d, this.f4895f, i3);
    }

    public int h(E e3) {
        if (this.f4892c) {
            d();
        }
        for (int i3 = 0; i3 < this.f4895f; i3++) {
            if (this.f4894e[i3] == e3) {
                return i3;
            }
        }
        return -1;
    }

    public int i(int i3) {
        if (this.f4892c) {
            d();
        }
        return this.f4893d[i3];
    }

    public void j(int i3, E e3) {
        int iA = c.a(this.f4893d, this.f4895f, i3);
        if (iA >= 0) {
            this.f4894e[iA] = e3;
            return;
        }
        int i4 = ~iA;
        int i5 = this.f4895f;
        if (i4 < i5) {
            Object[] objArr = this.f4894e;
            if (objArr[i4] == f4891b) {
                this.f4893d[i4] = i3;
                objArr[i4] = e3;
                return;
            }
        }
        if (this.f4892c && i5 >= this.f4893d.length) {
            d();
            i4 = ~c.a(this.f4893d, this.f4895f, i3);
        }
        int i6 = this.f4895f;
        if (i6 >= this.f4893d.length) {
            int iE = c.e(i6 + 1);
            int[] iArr = new int[iE];
            Object[] objArr2 = new Object[iE];
            int[] iArr2 = this.f4893d;
            System.arraycopy(iArr2, 0, iArr, 0, iArr2.length);
            Object[] objArr3 = this.f4894e;
            System.arraycopy(objArr3, 0, objArr2, 0, objArr3.length);
            this.f4893d = iArr;
            this.f4894e = objArr2;
        }
        int i7 = this.f4895f;
        if (i7 - i4 != 0) {
            int[] iArr3 = this.f4893d;
            int i8 = i4 + 1;
            System.arraycopy(iArr3, i4, iArr3, i8, i7 - i4);
            Object[] objArr4 = this.f4894e;
            System.arraycopy(objArr4, i4, objArr4, i8, this.f4895f - i4);
        }
        this.f4893d[i4] = i3;
        this.f4894e[i4] = e3;
        this.f4895f++;
    }

    public void k(int i3) {
        int iA = c.a(this.f4893d, this.f4895f, i3);
        if (iA >= 0) {
            Object[] objArr = this.f4894e;
            Object obj = objArr[iA];
            Object obj2 = f4891b;
            if (obj != obj2) {
                objArr[iA] = obj2;
                this.f4892c = true;
            }
        }
    }

    public int l() {
        if (this.f4892c) {
            d();
        }
        return this.f4895f;
    }

    public E m(int i3) {
        if (this.f4892c) {
            d();
        }
        return (E) this.f4894e[i3];
    }

    public String toString() {
        if (l() <= 0) {
            return "{}";
        }
        StringBuilder sb = new StringBuilder(this.f4895f * 28);
        sb.append('{');
        for (int i3 = 0; i3 < this.f4895f; i3++) {
            if (i3 > 0) {
                sb.append(", ");
            }
            sb.append(i(i3));
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

    public h(int i3) {
        this.f4892c = false;
        if (i3 == 0) {
            this.f4893d = c.f4853a;
            this.f4894e = c.f4855c;
        } else {
            int iE = c.e(i3);
            this.f4893d = new int[iE];
            this.f4894e = new Object[iE];
        }
    }
}
