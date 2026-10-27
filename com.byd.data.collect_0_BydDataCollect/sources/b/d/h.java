package b.d;

/* JADX INFO: compiled from: SparseArrayCompat.java */
/* JADX INFO: loaded from: classes.dex */
public class h<E> implements Cloneable {

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private static final Object f2297b = new Object();

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    private boolean f2298c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    private int[] f2299d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    private Object[] f2300e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    private int f2301f;

    public h() {
        this(10);
    }

    private void f() {
        int i2 = this.f2301f;
        int[] iArr = this.f2299d;
        Object[] objArr = this.f2300e;
        int i3 = 0;
        for (int i4 = 0; i4 < i2; i4++) {
            Object obj = objArr[i4];
            if (obj != f2297b) {
                if (i4 != i3) {
                    iArr[i3] = iArr[i4];
                    objArr[i3] = obj;
                    objArr[i4] = null;
                }
                i3++;
            }
        }
        this.f2298c = false;
        this.f2301f = i3;
    }

    public void b(int i2, E e2) {
        int i3 = this.f2301f;
        if (i3 != 0 && i2 <= this.f2299d[i3 - 1]) {
            l(i2, e2);
            return;
        }
        if (this.f2298c && i3 >= this.f2299d.length) {
            f();
        }
        int i4 = this.f2301f;
        if (i4 >= this.f2299d.length) {
            int iE = c.e(i4 + 1);
            int[] iArr = new int[iE];
            Object[] objArr = new Object[iE];
            int[] iArr2 = this.f2299d;
            System.arraycopy(iArr2, 0, iArr, 0, iArr2.length);
            Object[] objArr2 = this.f2300e;
            System.arraycopy(objArr2, 0, objArr, 0, objArr2.length);
            this.f2299d = iArr;
            this.f2300e = objArr;
        }
        this.f2299d[i4] = i2;
        this.f2300e[i4] = e2;
        this.f2301f = i4 + 1;
    }

    public void c() {
        int i2 = this.f2301f;
        Object[] objArr = this.f2300e;
        for (int i3 = 0; i3 < i2; i3++) {
            objArr[i3] = null;
        }
        this.f2301f = 0;
        this.f2298c = false;
    }

    /* JADX INFO: renamed from: e, reason: merged with bridge method [inline-methods] */
    public h<E> clone() {
        try {
            h<E> hVar = (h) super.clone();
            hVar.f2299d = (int[]) this.f2299d.clone();
            hVar.f2300e = (Object[]) this.f2300e.clone();
            return hVar;
        } catch (CloneNotSupportedException e2) {
            throw new AssertionError(e2);
        }
    }

    public E g(int i2) {
        return h(i2, null);
    }

    public E h(int i2, E e2) {
        int iA = c.a(this.f2299d, this.f2301f, i2);
        if (iA >= 0) {
            Object[] objArr = this.f2300e;
            if (objArr[iA] != f2297b) {
                return (E) objArr[iA];
            }
        }
        return e2;
    }

    public int i(E e2) {
        if (this.f2298c) {
            f();
        }
        for (int i2 = 0; i2 < this.f2301f; i2++) {
            if (this.f2300e[i2] == e2) {
                return i2;
            }
        }
        return -1;
    }

    public int k(int i2) {
        if (this.f2298c) {
            f();
        }
        return this.f2299d[i2];
    }

    public void l(int i2, E e2) {
        int iA = c.a(this.f2299d, this.f2301f, i2);
        if (iA >= 0) {
            this.f2300e[iA] = e2;
            return;
        }
        int i3 = ~iA;
        int i4 = this.f2301f;
        if (i3 < i4) {
            Object[] objArr = this.f2300e;
            if (objArr[i3] == f2297b) {
                this.f2299d[i3] = i2;
                objArr[i3] = e2;
                return;
            }
        }
        if (this.f2298c && i4 >= this.f2299d.length) {
            f();
            i3 = ~c.a(this.f2299d, this.f2301f, i2);
        }
        int i5 = this.f2301f;
        if (i5 >= this.f2299d.length) {
            int iE = c.e(i5 + 1);
            int[] iArr = new int[iE];
            Object[] objArr2 = new Object[iE];
            int[] iArr2 = this.f2299d;
            System.arraycopy(iArr2, 0, iArr, 0, iArr2.length);
            Object[] objArr3 = this.f2300e;
            System.arraycopy(objArr3, 0, objArr2, 0, objArr3.length);
            this.f2299d = iArr;
            this.f2300e = objArr2;
        }
        int i6 = this.f2301f;
        if (i6 - i3 != 0) {
            int[] iArr3 = this.f2299d;
            int i7 = i3 + 1;
            System.arraycopy(iArr3, i3, iArr3, i7, i6 - i3);
            Object[] objArr4 = this.f2300e;
            System.arraycopy(objArr4, i3, objArr4, i7, this.f2301f - i3);
        }
        this.f2299d[i3] = i2;
        this.f2300e[i3] = e2;
        this.f2301f++;
    }

    public void m(int i2) {
        int iA = c.a(this.f2299d, this.f2301f, i2);
        if (iA >= 0) {
            Object[] objArr = this.f2300e;
            Object obj = objArr[iA];
            Object obj2 = f2297b;
            if (obj != obj2) {
                objArr[iA] = obj2;
                this.f2298c = true;
            }
        }
    }

    public void n(int i2) {
        Object[] objArr = this.f2300e;
        Object obj = objArr[i2];
        Object obj2 = f2297b;
        if (obj != obj2) {
            objArr[i2] = obj2;
            this.f2298c = true;
        }
    }

    public int o() {
        if (this.f2298c) {
            f();
        }
        return this.f2301f;
    }

    public E p(int i2) {
        if (this.f2298c) {
            f();
        }
        return (E) this.f2300e[i2];
    }

    public String toString() {
        if (o() <= 0) {
            return "{}";
        }
        StringBuilder sb = new StringBuilder(this.f2301f * 28);
        sb.append('{');
        for (int i2 = 0; i2 < this.f2301f; i2++) {
            if (i2 > 0) {
                sb.append(", ");
            }
            sb.append(k(i2));
            sb.append('=');
            E eP = p(i2);
            if (eP != this) {
                sb.append(eP);
            } else {
                sb.append("(this Map)");
            }
        }
        sb.append('}');
        return sb.toString();
    }

    public h(int i2) {
        this.f2298c = false;
        if (i2 == 0) {
            this.f2299d = c.f2259a;
            this.f2300e = c.f2261c;
        } else {
            int iE = c.e(i2);
            this.f2299d = new int[iE];
            this.f2300e = new Object[iE];
        }
    }
}
