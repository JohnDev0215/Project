package b.a;

import androidx.annotation.Nullable;

/* JADX INFO: compiled from: caradapter-bug */
/* JADX INFO: loaded from: classes.dex */
public class h<E> implements Cloneable {
    private static final Object g = new Object();

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    private boolean f1277c;
    private int[] d;
    private Object[] e;
    private int f;

    public h() {
        this(10);
    }

    private void d() {
        int i = this.f;
        int[] iArr = this.d;
        Object[] objArr = this.e;
        int i2 = 0;
        for (int i3 = 0; i3 < i; i3++) {
            Object obj = objArr[i3];
            if (obj != g) {
                if (i3 != i2) {
                    iArr[i2] = iArr[i3];
                    objArr[i2] = obj;
                    objArr[i3] = null;
                }
                i2++;
            }
        }
        this.f1277c = false;
        this.f = i2;
    }

    public void a(int i, E e) {
        int i2 = this.f;
        if (i2 != 0 && i <= this.d[i2 - 1]) {
            h(i, e);
            return;
        }
        if (this.f1277c && this.f >= this.d.length) {
            d();
        }
        int i3 = this.f;
        if (i3 >= this.d.length) {
            int iE = c.e(i3 + 1);
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
        this.f1277c = false;
    }

    /* JADX INFO: renamed from: c, reason: merged with bridge method [inline-methods] */
    public h<E> clone() {
        try {
            h<E> hVar = (h) super.clone();
            hVar.d = (int[]) this.d.clone();
            hVar.e = (Object[]) this.e.clone();
            return hVar;
        } catch (CloneNotSupportedException e) {
            throw new AssertionError(e);
        }
    }

    @Nullable
    public E e(int i) {
        return f(i, null);
    }

    public E f(int i, E e) {
        int iA = c.a(this.d, this.f, i);
        if (iA >= 0) {
            Object[] objArr = this.e;
            if (objArr[iA] != g) {
                return (E) objArr[iA];
            }
        }
        return e;
    }

    public int g(int i) {
        if (this.f1277c) {
            d();
        }
        return this.d[i];
    }

    public void h(int i, E e) {
        int iA = c.a(this.d, this.f, i);
        if (iA >= 0) {
            this.e[iA] = e;
            return;
        }
        int i2 = ~iA;
        if (i2 < this.f) {
            Object[] objArr = this.e;
            if (objArr[i2] == g) {
                this.d[i2] = i;
                objArr[i2] = e;
                return;
            }
        }
        if (this.f1277c && this.f >= this.d.length) {
            d();
            i2 = ~c.a(this.d, this.f, i);
        }
        int i3 = this.f;
        if (i3 >= this.d.length) {
            int iE = c.e(i3 + 1);
            int[] iArr = new int[iE];
            Object[] objArr2 = new Object[iE];
            int[] iArr2 = this.d;
            System.arraycopy(iArr2, 0, iArr, 0, iArr2.length);
            Object[] objArr3 = this.e;
            System.arraycopy(objArr3, 0, objArr2, 0, objArr3.length);
            this.d = iArr;
            this.e = objArr2;
        }
        int i4 = this.f;
        if (i4 - i2 != 0) {
            int[] iArr3 = this.d;
            int i5 = i2 + 1;
            System.arraycopy(iArr3, i2, iArr3, i5, i4 - i2);
            Object[] objArr4 = this.e;
            System.arraycopy(objArr4, i2, objArr4, i5, this.f - i2);
        }
        this.d[i2] = i;
        this.e[i2] = e;
        this.f++;
    }

    public int i() {
        if (this.f1277c) {
            d();
        }
        return this.f;
    }

    public E j(int i) {
        if (this.f1277c) {
            d();
        }
        return (E) this.e[i];
    }

    public String toString() {
        if (i() <= 0) {
            return "{}";
        }
        StringBuilder sb = new StringBuilder(this.f * 28);
        sb.append('{');
        for (int i = 0; i < this.f; i++) {
            if (i > 0) {
                sb.append(", ");
            }
            sb.append(g(i));
            sb.append('=');
            E eJ = j(i);
            if (eJ != this) {
                sb.append(eJ);
            } else {
                sb.append("(this Map)");
            }
        }
        sb.append('}');
        return sb.toString();
    }

    public h(int i) {
        this.f1277c = false;
        if (i == 0) {
            this.d = c.f1268a;
            this.e = c.f1270c;
        } else {
            int iE = c.e(i);
            this.d = new int[iE];
            this.e = new Object[iE];
        }
    }
}
