package b.d;

/* JADX INFO: compiled from: SparseArrayCompat.java */
/* JADX INFO: loaded from: classes.dex */
public class h<E> implements Cloneable {

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private static final Object f1767b = new Object();

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    private boolean f1768c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    private int[] f1769d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    private Object[] f1770e;
    private int f;

    public h() {
        this(10);
    }

    private void d() {
        int i = this.f;
        int[] iArr = this.f1769d;
        Object[] objArr = this.f1770e;
        int i2 = 0;
        for (int i3 = 0; i3 < i; i3++) {
            Object obj = objArr[i3];
            if (obj != f1767b) {
                if (i3 != i2) {
                    iArr[i2] = iArr[i3];
                    objArr[i2] = obj;
                    objArr[i3] = null;
                }
                i2++;
            }
        }
        this.f1768c = false;
        this.f = i2;
    }

    public void a(int i, E e2) {
        int i2 = this.f;
        if (i2 != 0 && i <= this.f1769d[i2 - 1]) {
            j(i, e2);
            return;
        }
        if (this.f1768c && i2 >= this.f1769d.length) {
            d();
        }
        int i3 = this.f;
        if (i3 >= this.f1769d.length) {
            int iE = c.e(i3 + 1);
            int[] iArr = new int[iE];
            Object[] objArr = new Object[iE];
            int[] iArr2 = this.f1769d;
            System.arraycopy(iArr2, 0, iArr, 0, iArr2.length);
            Object[] objArr2 = this.f1770e;
            System.arraycopy(objArr2, 0, objArr, 0, objArr2.length);
            this.f1769d = iArr;
            this.f1770e = objArr;
        }
        this.f1769d[i3] = i;
        this.f1770e[i3] = e2;
        this.f = i3 + 1;
    }

    public void b() {
        int i = this.f;
        Object[] objArr = this.f1770e;
        for (int i2 = 0; i2 < i; i2++) {
            objArr[i2] = null;
        }
        this.f = 0;
        this.f1768c = false;
    }

    /* JADX INFO: renamed from: c, reason: merged with bridge method [inline-methods] */
    public h<E> clone() {
        try {
            h<E> hVar = (h) super.clone();
            hVar.f1769d = (int[]) this.f1769d.clone();
            hVar.f1770e = (Object[]) this.f1770e.clone();
            return hVar;
        } catch (CloneNotSupportedException e2) {
            throw new AssertionError(e2);
        }
    }

    public E e(int i) {
        return f(i, null);
    }

    public E f(int i, E e2) {
        int iA = c.a(this.f1769d, this.f, i);
        if (iA >= 0) {
            Object[] objArr = this.f1770e;
            if (objArr[iA] != f1767b) {
                return (E) objArr[iA];
            }
        }
        return e2;
    }

    public int g(int i) {
        if (this.f1768c) {
            d();
        }
        return c.a(this.f1769d, this.f, i);
    }

    public int h(E e2) {
        if (this.f1768c) {
            d();
        }
        for (int i = 0; i < this.f; i++) {
            if (this.f1770e[i] == e2) {
                return i;
            }
        }
        return -1;
    }

    public int i(int i) {
        if (this.f1768c) {
            d();
        }
        return this.f1769d[i];
    }

    public void j(int i, E e2) {
        int iA = c.a(this.f1769d, this.f, i);
        if (iA >= 0) {
            this.f1770e[iA] = e2;
            return;
        }
        int i2 = ~iA;
        int i3 = this.f;
        if (i2 < i3) {
            Object[] objArr = this.f1770e;
            if (objArr[i2] == f1767b) {
                this.f1769d[i2] = i;
                objArr[i2] = e2;
                return;
            }
        }
        if (this.f1768c && i3 >= this.f1769d.length) {
            d();
            i2 = ~c.a(this.f1769d, this.f, i);
        }
        int i4 = this.f;
        if (i4 >= this.f1769d.length) {
            int iE = c.e(i4 + 1);
            int[] iArr = new int[iE];
            Object[] objArr2 = new Object[iE];
            int[] iArr2 = this.f1769d;
            System.arraycopy(iArr2, 0, iArr, 0, iArr2.length);
            Object[] objArr3 = this.f1770e;
            System.arraycopy(objArr3, 0, objArr2, 0, objArr3.length);
            this.f1769d = iArr;
            this.f1770e = objArr2;
        }
        int i5 = this.f;
        if (i5 - i2 != 0) {
            int[] iArr3 = this.f1769d;
            int i6 = i2 + 1;
            System.arraycopy(iArr3, i2, iArr3, i6, i5 - i2);
            Object[] objArr4 = this.f1770e;
            System.arraycopy(objArr4, i2, objArr4, i6, this.f - i2);
        }
        this.f1769d[i2] = i;
        this.f1770e[i2] = e2;
        this.f++;
    }

    public void k(int i) {
        int iA = c.a(this.f1769d, this.f, i);
        if (iA >= 0) {
            Object[] objArr = this.f1770e;
            Object obj = objArr[iA];
            Object obj2 = f1767b;
            if (obj != obj2) {
                objArr[iA] = obj2;
                this.f1768c = true;
            }
        }
    }

    public int l() {
        if (this.f1768c) {
            d();
        }
        return this.f;
    }

    public E m(int i) {
        if (this.f1768c) {
            d();
        }
        return (E) this.f1770e[i];
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
        this.f1768c = false;
        if (i == 0) {
            this.f1769d = c.f1737a;
            this.f1770e = c.f1739c;
        } else {
            int iE = c.e(i);
            this.f1769d = new int[iE];
            this.f1770e = new Object[iE];
        }
    }
}
