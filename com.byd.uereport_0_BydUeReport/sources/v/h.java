package v;

/* JADX INFO: compiled from: SparseArrayCompat.java */
/* JADX INFO: loaded from: classes.dex */
public class h<E> implements Cloneable {

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public static final Object f8349d = new Object();

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public boolean f8350e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public int[] f8351f;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public Object[] f8352g;

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    public int f8353h;

    public h() {
        this(10);
    }

    public void a(int i8, E e8) {
        int i9 = this.f8353h;
        if (i9 != 0 && i8 <= this.f8351f[i9 - 1]) {
            j(i8, e8);
            return;
        }
        if (this.f8350e && i9 >= this.f8351f.length) {
            d();
        }
        int i10 = this.f8353h;
        if (i10 >= this.f8351f.length) {
            int iE = c.e(i10 + 1);
            int[] iArr = new int[iE];
            Object[] objArr = new Object[iE];
            int[] iArr2 = this.f8351f;
            System.arraycopy(iArr2, 0, iArr, 0, iArr2.length);
            Object[] objArr2 = this.f8352g;
            System.arraycopy(objArr2, 0, objArr, 0, objArr2.length);
            this.f8351f = iArr;
            this.f8352g = objArr;
        }
        this.f8351f[i10] = i8;
        this.f8352g[i10] = e8;
        this.f8353h = i10 + 1;
    }

    public void b() {
        int i8 = this.f8353h;
        Object[] objArr = this.f8352g;
        for (int i9 = 0; i9 < i8; i9++) {
            objArr[i9] = null;
        }
        this.f8353h = 0;
        this.f8350e = false;
    }

    /* JADX INFO: renamed from: c, reason: merged with bridge method [inline-methods] */
    public h<E> clone() {
        try {
            h<E> hVar = (h) super.clone();
            hVar.f8351f = (int[]) this.f8351f.clone();
            hVar.f8352g = (Object[]) this.f8352g.clone();
            return hVar;
        } catch (CloneNotSupportedException e8) {
            throw new AssertionError(e8);
        }
    }

    public final void d() {
        int i8 = this.f8353h;
        int[] iArr = this.f8351f;
        Object[] objArr = this.f8352g;
        int i9 = 0;
        for (int i10 = 0; i10 < i8; i10++) {
            Object obj = objArr[i10];
            if (obj != f8349d) {
                if (i10 != i9) {
                    iArr[i9] = iArr[i10];
                    objArr[i9] = obj;
                    objArr[i10] = null;
                }
                i9++;
            }
        }
        this.f8350e = false;
        this.f8353h = i9;
    }

    public E e(int i8) {
        return f(i8, null);
    }

    public E f(int i8, E e8) {
        int iA = c.a(this.f8351f, this.f8353h, i8);
        if (iA >= 0) {
            Object[] objArr = this.f8352g;
            if (objArr[iA] != f8349d) {
                return (E) objArr[iA];
            }
        }
        return e8;
    }

    public int g(int i8) {
        if (this.f8350e) {
            d();
        }
        return c.a(this.f8351f, this.f8353h, i8);
    }

    public int h(E e8) {
        if (this.f8350e) {
            d();
        }
        for (int i8 = 0; i8 < this.f8353h; i8++) {
            if (this.f8352g[i8] == e8) {
                return i8;
            }
        }
        return -1;
    }

    public int i(int i8) {
        if (this.f8350e) {
            d();
        }
        return this.f8351f[i8];
    }

    public void j(int i8, E e8) {
        int iA = c.a(this.f8351f, this.f8353h, i8);
        if (iA >= 0) {
            this.f8352g[iA] = e8;
            return;
        }
        int i9 = ~iA;
        int i10 = this.f8353h;
        if (i9 < i10) {
            Object[] objArr = this.f8352g;
            if (objArr[i9] == f8349d) {
                this.f8351f[i9] = i8;
                objArr[i9] = e8;
                return;
            }
        }
        if (this.f8350e && i10 >= this.f8351f.length) {
            d();
            i9 = ~c.a(this.f8351f, this.f8353h, i8);
        }
        int i11 = this.f8353h;
        if (i11 >= this.f8351f.length) {
            int iE = c.e(i11 + 1);
            int[] iArr = new int[iE];
            Object[] objArr2 = new Object[iE];
            int[] iArr2 = this.f8351f;
            System.arraycopy(iArr2, 0, iArr, 0, iArr2.length);
            Object[] objArr3 = this.f8352g;
            System.arraycopy(objArr3, 0, objArr2, 0, objArr3.length);
            this.f8351f = iArr;
            this.f8352g = objArr2;
        }
        int i12 = this.f8353h;
        if (i12 - i9 != 0) {
            int[] iArr3 = this.f8351f;
            int i13 = i9 + 1;
            System.arraycopy(iArr3, i9, iArr3, i13, i12 - i9);
            Object[] objArr4 = this.f8352g;
            System.arraycopy(objArr4, i9, objArr4, i13, this.f8353h - i9);
        }
        this.f8351f[i9] = i8;
        this.f8352g[i9] = e8;
        this.f8353h++;
    }

    public void k(int i8) {
        int iA = c.a(this.f8351f, this.f8353h, i8);
        if (iA >= 0) {
            Object[] objArr = this.f8352g;
            Object obj = objArr[iA];
            Object obj2 = f8349d;
            if (obj != obj2) {
                objArr[iA] = obj2;
                this.f8350e = true;
            }
        }
    }

    public int l() {
        if (this.f8350e) {
            d();
        }
        return this.f8353h;
    }

    public E m(int i8) {
        if (this.f8350e) {
            d();
        }
        return (E) this.f8352g[i8];
    }

    public String toString() {
        if (l() <= 0) {
            return "{}";
        }
        StringBuilder sb = new StringBuilder(this.f8353h * 28);
        sb.append('{');
        for (int i8 = 0; i8 < this.f8353h; i8++) {
            if (i8 > 0) {
                sb.append(", ");
            }
            sb.append(i(i8));
            sb.append('=');
            E eM = m(i8);
            if (eM != this) {
                sb.append(eM);
            } else {
                sb.append("(this Map)");
            }
        }
        sb.append('}');
        return sb.toString();
    }

    public h(int i8) {
        this.f8350e = false;
        if (i8 == 0) {
            this.f8351f = c.f8311a;
            this.f8352g = c.f8313c;
        } else {
            int iE = c.e(i8);
            this.f8351f = new int[iE];
            this.f8352g = new Object[iE];
        }
    }
}
