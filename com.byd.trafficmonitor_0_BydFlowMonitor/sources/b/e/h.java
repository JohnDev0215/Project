package b.e;

/* JADX INFO: compiled from: SparseArrayCompat.java */
/* JADX INFO: loaded from: classes.dex */
public class h<E> implements Cloneable {

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public static final Object f1977b = new Object();

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public boolean f1978c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public int[] f1979d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public Object[] f1980e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public int f1981f;

    public h() {
        this(10);
    }

    public void a(int i2, E e2) {
        int i3 = this.f1981f;
        if (i3 != 0 && i2 <= this.f1979d[i3 - 1]) {
            i(i2, e2);
            return;
        }
        if (this.f1978c && i3 >= this.f1979d.length) {
            d();
        }
        int i4 = this.f1981f;
        if (i4 >= this.f1979d.length) {
            int iE = c.e(i4 + 1);
            int[] iArr = new int[iE];
            Object[] objArr = new Object[iE];
            int[] iArr2 = this.f1979d;
            System.arraycopy(iArr2, 0, iArr, 0, iArr2.length);
            Object[] objArr2 = this.f1980e;
            System.arraycopy(objArr2, 0, objArr, 0, objArr2.length);
            this.f1979d = iArr;
            this.f1980e = objArr;
        }
        this.f1979d[i4] = i2;
        this.f1980e[i4] = e2;
        this.f1981f = i4 + 1;
    }

    public void b() {
        int i2 = this.f1981f;
        Object[] objArr = this.f1980e;
        for (int i3 = 0; i3 < i2; i3++) {
            objArr[i3] = null;
        }
        this.f1981f = 0;
        this.f1978c = false;
    }

    /* JADX INFO: renamed from: c, reason: merged with bridge method [inline-methods] */
    public h<E> clone() {
        try {
            h<E> hVar = (h) super.clone();
            hVar.f1979d = (int[]) this.f1979d.clone();
            hVar.f1980e = (Object[]) this.f1980e.clone();
            return hVar;
        } catch (CloneNotSupportedException e2) {
            throw new AssertionError(e2);
        }
    }

    public final void d() {
        int i2 = this.f1981f;
        int[] iArr = this.f1979d;
        Object[] objArr = this.f1980e;
        int i3 = 0;
        for (int i4 = 0; i4 < i2; i4++) {
            Object obj = objArr[i4];
            if (obj != f1977b) {
                if (i4 != i3) {
                    iArr[i3] = iArr[i4];
                    objArr[i3] = obj;
                    objArr[i4] = null;
                }
                i3++;
            }
        }
        this.f1978c = false;
        this.f1981f = i3;
    }

    public E e(int i2) {
        return f(i2, null);
    }

    public E f(int i2, E e2) {
        int iA = c.a(this.f1979d, this.f1981f, i2);
        if (iA >= 0) {
            Object[] objArr = this.f1980e;
            if (objArr[iA] != f1977b) {
                return (E) objArr[iA];
            }
        }
        return e2;
    }

    public int g(E e2) {
        if (this.f1978c) {
            d();
        }
        for (int i2 = 0; i2 < this.f1981f; i2++) {
            if (this.f1980e[i2] == e2) {
                return i2;
            }
        }
        return -1;
    }

    public int h(int i2) {
        if (this.f1978c) {
            d();
        }
        return this.f1979d[i2];
    }

    public void i(int i2, E e2) {
        int iA = c.a(this.f1979d, this.f1981f, i2);
        if (iA >= 0) {
            this.f1980e[iA] = e2;
            return;
        }
        int i3 = ~iA;
        int i4 = this.f1981f;
        if (i3 < i4) {
            Object[] objArr = this.f1980e;
            if (objArr[i3] == f1977b) {
                this.f1979d[i3] = i2;
                objArr[i3] = e2;
                return;
            }
        }
        if (this.f1978c && i4 >= this.f1979d.length) {
            d();
            i3 = ~c.a(this.f1979d, this.f1981f, i2);
        }
        int i5 = this.f1981f;
        if (i5 >= this.f1979d.length) {
            int iE = c.e(i5 + 1);
            int[] iArr = new int[iE];
            Object[] objArr2 = new Object[iE];
            int[] iArr2 = this.f1979d;
            System.arraycopy(iArr2, 0, iArr, 0, iArr2.length);
            Object[] objArr3 = this.f1980e;
            System.arraycopy(objArr3, 0, objArr2, 0, objArr3.length);
            this.f1979d = iArr;
            this.f1980e = objArr2;
        }
        int i6 = this.f1981f;
        if (i6 - i3 != 0) {
            int[] iArr3 = this.f1979d;
            int i7 = i3 + 1;
            System.arraycopy(iArr3, i3, iArr3, i7, i6 - i3);
            Object[] objArr4 = this.f1980e;
            System.arraycopy(objArr4, i3, objArr4, i7, this.f1981f - i3);
        }
        this.f1979d[i3] = i2;
        this.f1980e[i3] = e2;
        this.f1981f++;
    }

    public int j() {
        if (this.f1978c) {
            d();
        }
        return this.f1981f;
    }

    public E k(int i2) {
        if (this.f1978c) {
            d();
        }
        return (E) this.f1980e[i2];
    }

    public String toString() {
        if (j() <= 0) {
            return "{}";
        }
        StringBuilder sb = new StringBuilder(this.f1981f * 28);
        sb.append('{');
        for (int i2 = 0; i2 < this.f1981f; i2++) {
            if (i2 > 0) {
                sb.append(", ");
            }
            sb.append(h(i2));
            sb.append('=');
            E eK = k(i2);
            if (eK != this) {
                sb.append(eK);
            } else {
                sb.append("(this Map)");
            }
        }
        sb.append('}');
        return sb.toString();
    }

    public h(int i2) {
        this.f1978c = false;
        if (i2 == 0) {
            this.f1979d = c.f1939a;
            this.f1980e = c.f1941c;
        } else {
            int iE = c.e(i2);
            this.f1979d = new int[iE];
            this.f1980e = new Object[iE];
        }
    }
}
