package defpackage;

/* JADX INFO: compiled from: SparseArrayCompat.java */
/* JADX INFO: loaded from: classes.dex */
public class h4<E> implements Cloneable {
    public static final Object b = new Object();
    public boolean c;
    public int[] d;
    public Object[] e;
    public int f;

    public h4() {
        this(10);
    }

    public void a(int i, E e) {
        int i2 = this.f;
        if (i2 != 0 && i <= this.d[i2 - 1]) {
            j(i, e);
            return;
        }
        if (this.c && i2 >= this.d.length) {
            d();
        }
        int i3 = this.f;
        if (i3 >= this.d.length) {
            int iE = c4.e(i3 + 1);
            int[] iArr = new int[iE];
            Object[] objArr = new Object[iE];
            int[] iArr2 = this.d;
            System.arraycopy(iArr2, 0, iArr, 0, iArr2.length);
            Object[] objArr2 = this.e;
            System.arraycopy(objArr2, 0, objArr, 0, objArr2.length);
            this.d = iArr;
            this.e = objArr;
        }
        this.d[i3] = i;
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
        this.c = false;
    }

    /* JADX INFO: renamed from: c, reason: merged with bridge method [inline-methods] */
    public h4<E> clone() {
        try {
            h4<E> h4Var = (h4) super.clone();
            h4Var.d = (int[]) this.d.clone();
            h4Var.e = (Object[]) this.e.clone();
            return h4Var;
        } catch (CloneNotSupportedException e) {
            throw new AssertionError(e);
        }
    }

    public final void d() {
        int i = this.f;
        int[] iArr = this.d;
        Object[] objArr = this.e;
        int i2 = 0;
        for (int i3 = 0; i3 < i; i3++) {
            Object obj = objArr[i3];
            if (obj != b) {
                if (i3 != i2) {
                    iArr[i2] = iArr[i3];
                    objArr[i2] = obj;
                    objArr[i3] = null;
                }
                i2++;
            }
        }
        this.c = false;
        this.f = i2;
    }

    public E e(int i) {
        return f(i, null);
    }

    public E f(int i, E e) {
        int iA = c4.a(this.d, this.f, i);
        if (iA >= 0) {
            Object[] objArr = this.e;
            if (objArr[iA] != b) {
                return (E) objArr[iA];
            }
        }
        return e;
    }

    public int g(int i) {
        if (this.c) {
            d();
        }
        return c4.a(this.d, this.f, i);
    }

    public int h(E e) {
        if (this.c) {
            d();
        }
        for (int i = 0; i < this.f; i++) {
            if (this.e[i] == e) {
                return i;
            }
        }
        return -1;
    }

    public int i(int i) {
        if (this.c) {
            d();
        }
        return this.d[i];
    }

    public void j(int i, E e) {
        int iA = c4.a(this.d, this.f, i);
        if (iA >= 0) {
            this.e[iA] = e;
            return;
        }
        int i2 = ~iA;
        int i3 = this.f;
        if (i2 < i3) {
            Object[] objArr = this.e;
            if (objArr[i2] == b) {
                this.d[i2] = i;
                objArr[i2] = e;
                return;
            }
        }
        if (this.c && i3 >= this.d.length) {
            d();
            i2 = ~c4.a(this.d, this.f, i);
        }
        int i4 = this.f;
        if (i4 >= this.d.length) {
            int iE = c4.e(i4 + 1);
            int[] iArr = new int[iE];
            Object[] objArr2 = new Object[iE];
            int[] iArr2 = this.d;
            System.arraycopy(iArr2, 0, iArr, 0, iArr2.length);
            Object[] objArr3 = this.e;
            System.arraycopy(objArr3, 0, objArr2, 0, objArr3.length);
            this.d = iArr;
            this.e = objArr2;
        }
        int i5 = this.f;
        if (i5 - i2 != 0) {
            int[] iArr3 = this.d;
            int i6 = i2 + 1;
            System.arraycopy(iArr3, i2, iArr3, i6, i5 - i2);
            Object[] objArr4 = this.e;
            System.arraycopy(objArr4, i2, objArr4, i6, this.f - i2);
        }
        this.d[i2] = i;
        this.e[i2] = e;
        this.f++;
    }

    public void k(int i) {
        int iA = c4.a(this.d, this.f, i);
        if (iA >= 0) {
            Object[] objArr = this.e;
            Object obj = objArr[iA];
            Object obj2 = b;
            if (obj != obj2) {
                objArr[iA] = obj2;
                this.c = true;
            }
        }
    }

    public int l() {
        if (this.c) {
            d();
        }
        return this.f;
    }

    public E m(int i) {
        if (this.c) {
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

    public h4(int i) {
        this.c = false;
        if (i == 0) {
            this.d = c4.a;
            this.e = c4.c;
        } else {
            int iE = c4.e(i);
            this.d = new int[iE];
            this.e = new Object[iE];
        }
    }
}
