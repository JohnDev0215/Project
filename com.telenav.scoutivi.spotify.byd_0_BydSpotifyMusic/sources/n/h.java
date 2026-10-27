package n;

/* JADX INFO: compiled from: SparseArrayCompat.java */
/* JADX INFO: loaded from: classes.dex */
public class h<E> implements Cloneable {

    /* JADX INFO: renamed from: j, reason: collision with root package name */
    private static final Object f8486j = new Object();

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    private boolean f8487e;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    private int[] f8488g;

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    private Object[] f8489h;

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    private int f8490i;

    public h() {
        this(10);
    }

    private void d() {
        int i7 = this.f8490i;
        int[] iArr = this.f8488g;
        Object[] objArr = this.f8489h;
        int i8 = 0;
        for (int i9 = 0; i9 < i7; i9++) {
            Object obj = objArr[i9];
            if (obj != f8486j) {
                if (i9 != i8) {
                    iArr[i8] = iArr[i9];
                    objArr[i8] = obj;
                    objArr[i9] = null;
                }
                i8++;
            }
        }
        this.f8487e = false;
        this.f8490i = i8;
    }

    public void a(int i7, E e7) {
        int i8 = this.f8490i;
        if (i8 != 0 && i7 <= this.f8488g[i8 - 1]) {
            i(i7, e7);
            return;
        }
        if (this.f8487e && i8 >= this.f8488g.length) {
            d();
        }
        int i9 = this.f8490i;
        if (i9 >= this.f8488g.length) {
            int iE = c.e(i9 + 1);
            int[] iArr = new int[iE];
            Object[] objArr = new Object[iE];
            int[] iArr2 = this.f8488g;
            System.arraycopy(iArr2, 0, iArr, 0, iArr2.length);
            Object[] objArr2 = this.f8489h;
            System.arraycopy(objArr2, 0, objArr, 0, objArr2.length);
            this.f8488g = iArr;
            this.f8489h = objArr;
        }
        this.f8488g[i9] = i7;
        this.f8489h[i9] = e7;
        this.f8490i = i9 + 1;
    }

    public void b() {
        int i7 = this.f8490i;
        Object[] objArr = this.f8489h;
        for (int i8 = 0; i8 < i7; i8++) {
            objArr[i8] = null;
        }
        this.f8490i = 0;
        this.f8487e = false;
    }

    /* JADX INFO: renamed from: c, reason: merged with bridge method [inline-methods] */
    public h<E> clone() {
        try {
            h<E> hVar = (h) super.clone();
            hVar.f8488g = (int[]) this.f8488g.clone();
            hVar.f8489h = (Object[]) this.f8489h.clone();
            return hVar;
        } catch (CloneNotSupportedException e7) {
            throw new AssertionError(e7);
        }
    }

    public E e(int i7) {
        return f(i7, null);
    }

    public E f(int i7, E e7) {
        int iA = c.a(this.f8488g, this.f8490i, i7);
        if (iA >= 0) {
            Object[] objArr = this.f8489h;
            if (objArr[iA] != f8486j) {
                return (E) objArr[iA];
            }
        }
        return e7;
    }

    public int g(E e7) {
        if (this.f8487e) {
            d();
        }
        for (int i7 = 0; i7 < this.f8490i; i7++) {
            if (this.f8489h[i7] == e7) {
                return i7;
            }
        }
        return -1;
    }

    public int h(int i7) {
        if (this.f8487e) {
            d();
        }
        return this.f8488g[i7];
    }

    public void i(int i7, E e7) {
        int iA = c.a(this.f8488g, this.f8490i, i7);
        if (iA >= 0) {
            this.f8489h[iA] = e7;
            return;
        }
        int i8 = ~iA;
        int i9 = this.f8490i;
        if (i8 < i9) {
            Object[] objArr = this.f8489h;
            if (objArr[i8] == f8486j) {
                this.f8488g[i8] = i7;
                objArr[i8] = e7;
                return;
            }
        }
        if (this.f8487e && i9 >= this.f8488g.length) {
            d();
            i8 = ~c.a(this.f8488g, this.f8490i, i7);
        }
        int i10 = this.f8490i;
        if (i10 >= this.f8488g.length) {
            int iE = c.e(i10 + 1);
            int[] iArr = new int[iE];
            Object[] objArr2 = new Object[iE];
            int[] iArr2 = this.f8488g;
            System.arraycopy(iArr2, 0, iArr, 0, iArr2.length);
            Object[] objArr3 = this.f8489h;
            System.arraycopy(objArr3, 0, objArr2, 0, objArr3.length);
            this.f8488g = iArr;
            this.f8489h = objArr2;
        }
        int i11 = this.f8490i;
        if (i11 - i8 != 0) {
            int[] iArr3 = this.f8488g;
            int i12 = i8 + 1;
            System.arraycopy(iArr3, i8, iArr3, i12, i11 - i8);
            Object[] objArr4 = this.f8489h;
            System.arraycopy(objArr4, i8, objArr4, i12, this.f8490i - i8);
        }
        this.f8488g[i8] = i7;
        this.f8489h[i8] = e7;
        this.f8490i++;
    }

    public void j(int i7) {
        Object[] objArr = this.f8489h;
        Object obj = objArr[i7];
        Object obj2 = f8486j;
        if (obj != obj2) {
            objArr[i7] = obj2;
            this.f8487e = true;
        }
    }

    public int k() {
        if (this.f8487e) {
            d();
        }
        return this.f8490i;
    }

    public E l(int i7) {
        if (this.f8487e) {
            d();
        }
        return (E) this.f8489h[i7];
    }

    public String toString() {
        if (k() <= 0) {
            return "{}";
        }
        StringBuilder sb = new StringBuilder(this.f8490i * 28);
        sb.append('{');
        for (int i7 = 0; i7 < this.f8490i; i7++) {
            if (i7 > 0) {
                sb.append(", ");
            }
            sb.append(h(i7));
            sb.append('=');
            E eL = l(i7);
            if (eL != this) {
                sb.append(eL);
            } else {
                sb.append("(this Map)");
            }
        }
        sb.append('}');
        return sb.toString();
    }

    public h(int i7) {
        this.f8487e = false;
        if (i7 == 0) {
            this.f8488g = c.f8448a;
            this.f8489h = c.f8450c;
        } else {
            int iE = c.e(i7);
            this.f8488g = new int[iE];
            this.f8489h = new Object[iE];
        }
    }
}
