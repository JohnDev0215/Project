package b.d;

/* JADX INFO: compiled from: SparseArrayCompat.java */
/* JADX INFO: loaded from: classes.dex */
public class h<E> implements Cloneable {

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private static final Object f1637b = new Object();

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    private boolean f1638c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    private int[] f1639d;
    private Object[] e;
    private int f;

    public h() {
        this(10);
    }

    private void d() {
        int i = this.f;
        int[] iArr = this.f1639d;
        Object[] objArr = this.e;
        int i2 = 0;
        for (int i3 = 0; i3 < i; i3++) {
            Object obj = objArr[i3];
            if (obj != f1637b) {
                if (i3 != i2) {
                    iArr[i2] = iArr[i3];
                    objArr[i2] = obj;
                    objArr[i3] = null;
                }
                i2++;
            }
        }
        this.f1638c = false;
        this.f = i2;
    }

    public void a(int i, E e) {
        int i2 = this.f;
        if (i2 != 0 && i <= this.f1639d[i2 - 1]) {
            i(i, e);
            return;
        }
        if (this.f1638c && i2 >= this.f1639d.length) {
            d();
        }
        int i3 = this.f;
        if (i3 >= this.f1639d.length) {
            int iE = c.e(i3 + 1);
            int[] iArr = new int[iE];
            Object[] objArr = new Object[iE];
            int[] iArr2 = this.f1639d;
            System.arraycopy(iArr2, 0, iArr, 0, iArr2.length);
            Object[] objArr2 = this.e;
            System.arraycopy(objArr2, 0, objArr, 0, objArr2.length);
            this.f1639d = iArr;
            this.e = objArr;
        }
        this.f1639d[i3] = i;
        this.e[i3] = e;
        this.f = i3 + 1;
    }

    public void b() {
        int i = this.f;
        Object[] objArr = this.e;
        for (int i2 = 0; i2 < i; i2++) {
            objArr[i2] = null;
        }
        this.f = 0;
        this.f1638c = false;
    }

    /* JADX INFO: renamed from: c, reason: merged with bridge method [inline-methods] */
    public h<E> clone() {
        try {
            h<E> hVar = (h) super.clone();
            hVar.f1639d = (int[]) this.f1639d.clone();
            hVar.e = (Object[]) this.e.clone();
            return hVar;
        } catch (CloneNotSupportedException e) {
            throw new AssertionError(e);
        }
    }

    public E e(int i) {
        return f(i, null);
    }

    public E f(int i, E e) {
        int iA = c.a(this.f1639d, this.f, i);
        if (iA >= 0) {
            Object[] objArr = this.e;
            if (objArr[iA] != f1637b) {
                return (E) objArr[iA];
            }
        }
        return e;
    }

    public int g(E e) {
        if (this.f1638c) {
            d();
        }
        for (int i = 0; i < this.f; i++) {
            if (this.e[i] == e) {
                return i;
            }
        }
        return -1;
    }

    public int h(int i) {
        if (this.f1638c) {
            d();
        }
        return this.f1639d[i];
    }

    public void i(int i, E e) {
        int iA = c.a(this.f1639d, this.f, i);
        if (iA >= 0) {
            this.e[iA] = e;
            return;
        }
        int i2 = ~iA;
        int i3 = this.f;
        if (i2 < i3) {
            Object[] objArr = this.e;
            if (objArr[i2] == f1637b) {
                this.f1639d[i2] = i;
                objArr[i2] = e;
                return;
            }
        }
        if (this.f1638c && i3 >= this.f1639d.length) {
            d();
            i2 = ~c.a(this.f1639d, this.f, i);
        }
        int i4 = this.f;
        if (i4 >= this.f1639d.length) {
            int iE = c.e(i4 + 1);
            int[] iArr = new int[iE];
            Object[] objArr2 = new Object[iE];
            int[] iArr2 = this.f1639d;
            System.arraycopy(iArr2, 0, iArr, 0, iArr2.length);
            Object[] objArr3 = this.e;
            System.arraycopy(objArr3, 0, objArr2, 0, objArr3.length);
            this.f1639d = iArr;
            this.e = objArr2;
        }
        int i5 = this.f;
        if (i5 - i2 != 0) {
            int[] iArr3 = this.f1639d;
            int i6 = i2 + 1;
            System.arraycopy(iArr3, i2, iArr3, i6, i5 - i2);
            Object[] objArr4 = this.e;
            System.arraycopy(objArr4, i2, objArr4, i6, this.f - i2);
        }
        this.f1639d[i2] = i;
        this.e[i2] = e;
        this.f++;
    }

    public void j(int i) {
        int iA = c.a(this.f1639d, this.f, i);
        if (iA >= 0) {
            Object[] objArr = this.e;
            Object obj = objArr[iA];
            Object obj2 = f1637b;
            if (obj != obj2) {
                objArr[iA] = obj2;
                this.f1638c = true;
            }
        }
    }

    public int k() {
        if (this.f1638c) {
            d();
        }
        return this.f;
    }

    public E l(int i) {
        if (this.f1638c) {
            d();
        }
        return (E) this.e[i];
    }

    public String toString() {
        if (k() <= 0) {
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
            E eL = l(i);
            if (eL != this) {
                sb.append(eL);
            } else {
                sb.append("(this Map)");
            }
        }
        sb.append('}');
        return sb.toString();
    }

    public h(int i) {
        this.f1638c = false;
        if (i == 0) {
            this.f1639d = c.f1612a;
            this.e = c.f1614c;
        } else {
            int iE = c.e(i);
            this.f1639d = new int[iE];
            this.e = new Object[iE];
        }
    }
}
