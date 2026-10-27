package b.d;

/* JADX INFO: compiled from: SparseArrayCompat.java */
/* JADX INFO: loaded from: classes.dex */
public class h<E> implements Cloneable {

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public static final Object f1182c = new Object();

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public boolean f1183d;
    public int[] s;
    public Object[] x;
    public int y;

    public h() {
        this(10);
    }

    public void a(int i2, E e2) {
        int i3 = this.y;
        if (i3 != 0 && i2 <= this.s[i3 - 1]) {
            j(i2, e2);
            return;
        }
        if (this.f1183d && i3 >= this.s.length) {
            f();
        }
        int i4 = this.y;
        if (i4 >= this.s.length) {
            int iE = c.e(i4 + 1);
            int[] iArr = new int[iE];
            Object[] objArr = new Object[iE];
            int[] iArr2 = this.s;
            System.arraycopy(iArr2, 0, iArr, 0, iArr2.length);
            Object[] objArr2 = this.x;
            System.arraycopy(objArr2, 0, objArr, 0, objArr2.length);
            this.s = iArr;
            this.x = objArr;
        }
        this.s[i4] = i2;
        this.x[i4] = e2;
        this.y = i4 + 1;
    }

    public void b() {
        int i2 = this.y;
        Object[] objArr = this.x;
        for (int i3 = 0; i3 < i2; i3++) {
            objArr[i3] = null;
        }
        this.y = 0;
        this.f1183d = false;
    }

    /* JADX INFO: renamed from: e, reason: merged with bridge method [inline-methods] */
    public h<E> clone() {
        try {
            h<E> hVar = (h) super.clone();
            hVar.s = (int[]) this.s.clone();
            hVar.x = (Object[]) this.x.clone();
            return hVar;
        } catch (CloneNotSupportedException e2) {
            throw new AssertionError(e2);
        }
    }

    public final void f() {
        int i2 = this.y;
        int[] iArr = this.s;
        Object[] objArr = this.x;
        int i3 = 0;
        for (int i4 = 0; i4 < i2; i4++) {
            Object obj = objArr[i4];
            if (obj != f1182c) {
                if (i4 != i3) {
                    iArr[i3] = iArr[i4];
                    objArr[i3] = obj;
                    objArr[i4] = null;
                }
                i3++;
            }
        }
        this.f1183d = false;
        this.y = i3;
    }

    public E g(int i2) {
        return h(i2, null);
    }

    public E h(int i2, E e2) {
        int iA = c.a(this.s, this.y, i2);
        if (iA >= 0) {
            Object[] objArr = this.x;
            if (objArr[iA] != f1182c) {
                return (E) objArr[iA];
            }
        }
        return e2;
    }

    public int i(int i2) {
        if (this.f1183d) {
            f();
        }
        return this.s[i2];
    }

    public void j(int i2, E e2) {
        int iA = c.a(this.s, this.y, i2);
        if (iA >= 0) {
            this.x[iA] = e2;
            return;
        }
        int i3 = ~iA;
        int i4 = this.y;
        if (i3 < i4) {
            Object[] objArr = this.x;
            if (objArr[i3] == f1182c) {
                this.s[i3] = i2;
                objArr[i3] = e2;
                return;
            }
        }
        if (this.f1183d && i4 >= this.s.length) {
            f();
            i3 = ~c.a(this.s, this.y, i2);
        }
        int i5 = this.y;
        if (i5 >= this.s.length) {
            int iE = c.e(i5 + 1);
            int[] iArr = new int[iE];
            Object[] objArr2 = new Object[iE];
            int[] iArr2 = this.s;
            System.arraycopy(iArr2, 0, iArr, 0, iArr2.length);
            Object[] objArr3 = this.x;
            System.arraycopy(objArr3, 0, objArr2, 0, objArr3.length);
            this.s = iArr;
            this.x = objArr2;
        }
        int i6 = this.y;
        if (i6 - i3 != 0) {
            int[] iArr3 = this.s;
            int i7 = i3 + 1;
            System.arraycopy(iArr3, i3, iArr3, i7, i6 - i3);
            Object[] objArr4 = this.x;
            System.arraycopy(objArr4, i3, objArr4, i7, this.y - i3);
        }
        this.s[i3] = i2;
        this.x[i3] = e2;
        this.y++;
    }

    public void k(int i2) {
        int iA = c.a(this.s, this.y, i2);
        if (iA >= 0) {
            Object[] objArr = this.x;
            Object obj = objArr[iA];
            Object obj2 = f1182c;
            if (obj != obj2) {
                objArr[iA] = obj2;
                this.f1183d = true;
            }
        }
    }

    public int l() {
        if (this.f1183d) {
            f();
        }
        return this.y;
    }

    public E m(int i2) {
        if (this.f1183d) {
            f();
        }
        return (E) this.x[i2];
    }

    public String toString() {
        if (l() <= 0) {
            return "{}";
        }
        StringBuilder sb = new StringBuilder(this.y * 28);
        sb.append('{');
        for (int i2 = 0; i2 < this.y; i2++) {
            if (i2 > 0) {
                sb.append(", ");
            }
            sb.append(i(i2));
            sb.append('=');
            E eM = m(i2);
            if (eM != this) {
                sb.append(eM);
            } else {
                sb.append("(this Map)");
            }
        }
        sb.append('}');
        return sb.toString();
    }

    public h(int i2) {
        this.f1183d = false;
        if (i2 == 0) {
            this.s = c.f1157a;
            this.x = c.f1159c;
        } else {
            int iE = c.e(i2);
            this.s = new int[iE];
            this.x = new Object[iE];
        }
    }
}
