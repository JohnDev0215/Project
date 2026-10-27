package b.d;

/* JADX INFO: compiled from: SparseArrayCompat.java */
/* JADX INFO: loaded from: classes.dex */
public class h<E> implements Cloneable {

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private static final Object f2300b = new Object();

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    private boolean f2301c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    private int[] f2302d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    private Object[] f2303e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    private int f2304f;

    public h() {
        this(10);
    }

    private void d() {
        int i = this.f2304f;
        int[] iArr = this.f2302d;
        Object[] objArr = this.f2303e;
        int i2 = 0;
        for (int i3 = 0; i3 < i; i3++) {
            Object obj = objArr[i3];
            if (obj != f2300b) {
                if (i3 != i2) {
                    iArr[i2] = iArr[i3];
                    objArr[i2] = obj;
                    objArr[i3] = null;
                }
                i2++;
            }
        }
        this.f2301c = false;
        this.f2304f = i2;
    }

    public void a(int i, E e2) {
        int i2 = this.f2304f;
        if (i2 != 0 && i <= this.f2302d[i2 - 1]) {
            k(i, e2);
            return;
        }
        if (this.f2301c && i2 >= this.f2302d.length) {
            d();
        }
        int i3 = this.f2304f;
        if (i3 >= this.f2302d.length) {
            int iE = c.e(i3 + 1);
            int[] iArr = new int[iE];
            Object[] objArr = new Object[iE];
            int[] iArr2 = this.f2302d;
            System.arraycopy(iArr2, 0, iArr, 0, iArr2.length);
            Object[] objArr2 = this.f2303e;
            System.arraycopy(objArr2, 0, objArr, 0, objArr2.length);
            this.f2302d = iArr;
            this.f2303e = objArr;
        }
        this.f2302d[i3] = i;
        this.f2303e[i3] = e2;
        this.f2304f = i3 + 1;
    }

    public void b() {
        int i = this.f2304f;
        Object[] objArr = this.f2303e;
        for (int i2 = 0; i2 < i; i2++) {
            objArr[i2] = null;
        }
        this.f2304f = 0;
        this.f2301c = false;
    }

    /* JADX INFO: renamed from: c, reason: merged with bridge method [inline-methods] */
    public h<E> clone() {
        try {
            h<E> hVar = (h) super.clone();
            hVar.f2302d = (int[]) this.f2302d.clone();
            hVar.f2303e = (Object[]) this.f2303e.clone();
            return hVar;
        } catch (CloneNotSupportedException e2) {
            throw new AssertionError(e2);
        }
    }

    public E f(int i) {
        return g(i, null);
    }

    public E g(int i, E e2) {
        int iA = c.a(this.f2302d, this.f2304f, i);
        if (iA >= 0) {
            Object[] objArr = this.f2303e;
            if (objArr[iA] != f2300b) {
                return (E) objArr[iA];
            }
        }
        return e2;
    }

    public int h(int i) {
        if (this.f2301c) {
            d();
        }
        return c.a(this.f2302d, this.f2304f, i);
    }

    public int i(E e2) {
        if (this.f2301c) {
            d();
        }
        for (int i = 0; i < this.f2304f; i++) {
            if (this.f2303e[i] == e2) {
                return i;
            }
        }
        return -1;
    }

    public int j(int i) {
        if (this.f2301c) {
            d();
        }
        return this.f2302d[i];
    }

    public void k(int i, E e2) {
        int iA = c.a(this.f2302d, this.f2304f, i);
        if (iA >= 0) {
            this.f2303e[iA] = e2;
            return;
        }
        int i2 = ~iA;
        int i3 = this.f2304f;
        if (i2 < i3) {
            Object[] objArr = this.f2303e;
            if (objArr[i2] == f2300b) {
                this.f2302d[i2] = i;
                objArr[i2] = e2;
                return;
            }
        }
        if (this.f2301c && i3 >= this.f2302d.length) {
            d();
            i2 = ~c.a(this.f2302d, this.f2304f, i);
        }
        int i4 = this.f2304f;
        if (i4 >= this.f2302d.length) {
            int iE = c.e(i4 + 1);
            int[] iArr = new int[iE];
            Object[] objArr2 = new Object[iE];
            int[] iArr2 = this.f2302d;
            System.arraycopy(iArr2, 0, iArr, 0, iArr2.length);
            Object[] objArr3 = this.f2303e;
            System.arraycopy(objArr3, 0, objArr2, 0, objArr3.length);
            this.f2302d = iArr;
            this.f2303e = objArr2;
        }
        int i5 = this.f2304f;
        if (i5 - i2 != 0) {
            int[] iArr3 = this.f2302d;
            int i6 = i2 + 1;
            System.arraycopy(iArr3, i2, iArr3, i6, i5 - i2);
            Object[] objArr4 = this.f2303e;
            System.arraycopy(objArr4, i2, objArr4, i6, this.f2304f - i2);
        }
        this.f2302d[i2] = i;
        this.f2303e[i2] = e2;
        this.f2304f++;
    }

    public void l(int i) {
        int iA = c.a(this.f2302d, this.f2304f, i);
        if (iA >= 0) {
            Object[] objArr = this.f2303e;
            Object obj = objArr[iA];
            Object obj2 = f2300b;
            if (obj != obj2) {
                objArr[iA] = obj2;
                this.f2301c = true;
            }
        }
    }

    public int m() {
        if (this.f2301c) {
            d();
        }
        return this.f2304f;
    }

    public E n(int i) {
        if (this.f2301c) {
            d();
        }
        return (E) this.f2303e[i];
    }

    public String toString() {
        if (m() <= 0) {
            return "{}";
        }
        StringBuilder sb = new StringBuilder(this.f2304f * 28);
        sb.append('{');
        for (int i = 0; i < this.f2304f; i++) {
            if (i > 0) {
                sb.append(", ");
            }
            sb.append(j(i));
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

    public h(int i) {
        this.f2301c = false;
        if (i == 0) {
            this.f2302d = c.f2262a;
            this.f2303e = c.f2264c;
        } else {
            int iE = c.e(i);
            this.f2302d = new int[iE];
            this.f2303e = new Object[iE];
        }
    }
}
