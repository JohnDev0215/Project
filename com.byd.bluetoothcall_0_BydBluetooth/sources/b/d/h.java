package b.d;

/* JADX INFO: compiled from: SparseArrayCompat.java */
/* JADX INFO: loaded from: classes.dex */
public class h<E> implements Cloneable {

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private static final Object f2149b = new Object();

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    private boolean f2150c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    private int[] f2151d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    private Object[] f2152e;
    private int f;

    public h() {
        this(10);
    }

    private void d() {
        int i = this.f;
        int[] iArr = this.f2151d;
        Object[] objArr = this.f2152e;
        int i2 = 0;
        for (int i3 = 0; i3 < i; i3++) {
            Object obj = objArr[i3];
            if (obj != f2149b) {
                if (i3 != i2) {
                    iArr[i2] = iArr[i3];
                    objArr[i2] = obj;
                    objArr[i3] = null;
                }
                i2++;
            }
        }
        this.f2150c = false;
        this.f = i2;
    }

    public void a(int i, E e2) {
        int i2 = this.f;
        if (i2 != 0 && i <= this.f2151d[i2 - 1]) {
            j(i, e2);
            return;
        }
        if (this.f2150c && i2 >= this.f2151d.length) {
            d();
        }
        int i3 = this.f;
        if (i3 >= this.f2151d.length) {
            int iE = c.e(i3 + 1);
            int[] iArr = new int[iE];
            Object[] objArr = new Object[iE];
            int[] iArr2 = this.f2151d;
            System.arraycopy(iArr2, 0, iArr, 0, iArr2.length);
            Object[] objArr2 = this.f2152e;
            System.arraycopy(objArr2, 0, objArr, 0, objArr2.length);
            this.f2151d = iArr;
            this.f2152e = objArr;
        }
        this.f2151d[i3] = i;
        this.f2152e[i3] = e2;
        this.f = i3 + 1;
    }

    public void b() {
        int i = this.f;
        Object[] objArr = this.f2152e;
        for (int i2 = 0; i2 < i; i2++) {
            objArr[i2] = null;
        }
        this.f = 0;
        this.f2150c = false;
    }

    /* JADX INFO: renamed from: c, reason: merged with bridge method [inline-methods] */
    public h<E> clone() {
        try {
            h<E> hVar = (h) super.clone();
            hVar.f2151d = (int[]) this.f2151d.clone();
            hVar.f2152e = (Object[]) this.f2152e.clone();
            return hVar;
        } catch (CloneNotSupportedException e2) {
            throw new AssertionError(e2);
        }
    }

    public E e(int i) {
        return f(i, null);
    }

    public E f(int i, E e2) {
        int iA = c.a(this.f2151d, this.f, i);
        if (iA >= 0) {
            Object[] objArr = this.f2152e;
            if (objArr[iA] != f2149b) {
                return (E) objArr[iA];
            }
        }
        return e2;
    }

    public int g(int i) {
        if (this.f2150c) {
            d();
        }
        return c.a(this.f2151d, this.f, i);
    }

    public int h(E e2) {
        if (this.f2150c) {
            d();
        }
        for (int i = 0; i < this.f; i++) {
            if (this.f2152e[i] == e2) {
                return i;
            }
        }
        return -1;
    }

    public int i(int i) {
        if (this.f2150c) {
            d();
        }
        return this.f2151d[i];
    }

    public void j(int i, E e2) {
        int iA = c.a(this.f2151d, this.f, i);
        if (iA >= 0) {
            this.f2152e[iA] = e2;
            return;
        }
        int i2 = ~iA;
        int i3 = this.f;
        if (i2 < i3) {
            Object[] objArr = this.f2152e;
            if (objArr[i2] == f2149b) {
                this.f2151d[i2] = i;
                objArr[i2] = e2;
                return;
            }
        }
        if (this.f2150c && i3 >= this.f2151d.length) {
            d();
            i2 = ~c.a(this.f2151d, this.f, i);
        }
        int i4 = this.f;
        if (i4 >= this.f2151d.length) {
            int iE = c.e(i4 + 1);
            int[] iArr = new int[iE];
            Object[] objArr2 = new Object[iE];
            int[] iArr2 = this.f2151d;
            System.arraycopy(iArr2, 0, iArr, 0, iArr2.length);
            Object[] objArr3 = this.f2152e;
            System.arraycopy(objArr3, 0, objArr2, 0, objArr3.length);
            this.f2151d = iArr;
            this.f2152e = objArr2;
        }
        int i5 = this.f;
        if (i5 - i2 != 0) {
            int[] iArr3 = this.f2151d;
            int i6 = i2 + 1;
            System.arraycopy(iArr3, i2, iArr3, i6, i5 - i2);
            Object[] objArr4 = this.f2152e;
            System.arraycopy(objArr4, i2, objArr4, i6, this.f - i2);
        }
        this.f2151d[i2] = i;
        this.f2152e[i2] = e2;
        this.f++;
    }

    public void k(int i) {
        int iA = c.a(this.f2151d, this.f, i);
        if (iA >= 0) {
            Object[] objArr = this.f2152e;
            Object obj = objArr[iA];
            Object obj2 = f2149b;
            if (obj != obj2) {
                objArr[iA] = obj2;
                this.f2150c = true;
            }
        }
    }

    public int l() {
        if (this.f2150c) {
            d();
        }
        return this.f;
    }

    public E m(int i) {
        if (this.f2150c) {
            d();
        }
        return (E) this.f2152e[i];
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
            sb.append(i(i));
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

    public h(int i) {
        this.f2150c = false;
        if (i == 0) {
            this.f2151d = c.f2119a;
            this.f2152e = c.f2121c;
        } else {
            int iE = c.e(i);
            this.f2151d = new int[iE];
            this.f2152e = new Object[iE];
        }
    }
}
