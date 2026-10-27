package a.e;

/* JADX INFO: loaded from: classes.dex */
public class i<E> implements Cloneable {

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public static final Object f491e = new Object();

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public boolean f492a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public int[] f493b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public Object[] f494c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public int f495d;

    public i() {
        this(10);
    }

    public i(int i) {
        this.f492a = false;
        if (i == 0) {
            this.f493b = d.f456a;
            this.f494c = d.f458c;
        } else {
            int iE = d.e(i);
            this.f493b = new int[iE];
            this.f494c = new Object[iE];
        }
    }

    public void a(int i, E e2) {
        int i2 = this.f495d;
        if (i2 != 0 && i <= this.f493b[i2 - 1]) {
            g(i, e2);
            return;
        }
        if (this.f492a && this.f495d >= this.f493b.length) {
            c();
        }
        int i3 = this.f495d;
        if (i3 >= this.f493b.length) {
            int iE = d.e(i3 + 1);
            int[] iArr = new int[iE];
            Object[] objArr = new Object[iE];
            int[] iArr2 = this.f493b;
            System.arraycopy(iArr2, 0, iArr, 0, iArr2.length);
            Object[] objArr2 = this.f494c;
            System.arraycopy(objArr2, 0, objArr, 0, objArr2.length);
            this.f493b = iArr;
            this.f494c = objArr;
        }
        this.f493b[i3] = i;
        this.f494c[i3] = e2;
        this.f495d = i3 + 1;
    }

    /* JADX INFO: renamed from: b, reason: merged with bridge method [inline-methods] */
    public i<E> clone() {
        try {
            i<E> iVar = (i) super.clone();
            iVar.f493b = (int[]) this.f493b.clone();
            iVar.f494c = (Object[]) this.f494c.clone();
            return iVar;
        } catch (CloneNotSupportedException e2) {
            throw new AssertionError(e2);
        }
    }

    public final void c() {
        int i = this.f495d;
        int[] iArr = this.f493b;
        Object[] objArr = this.f494c;
        int i2 = 0;
        for (int i3 = 0; i3 < i; i3++) {
            Object obj = objArr[i3];
            if (obj != f491e) {
                if (i3 != i2) {
                    iArr[i2] = iArr[i3];
                    objArr[i2] = obj;
                    objArr[i3] = null;
                }
                i2++;
            }
        }
        this.f492a = false;
        this.f495d = i2;
    }

    public E d(int i) {
        return e(i, null);
    }

    public E e(int i, E e2) {
        int iA = d.a(this.f493b, this.f495d, i);
        if (iA >= 0) {
            Object[] objArr = this.f494c;
            if (objArr[iA] != f491e) {
                return (E) objArr[iA];
            }
        }
        return e2;
    }

    public int f(int i) {
        if (this.f492a) {
            c();
        }
        return this.f493b[i];
    }

    public void g(int i, E e2) {
        int iA = d.a(this.f493b, this.f495d, i);
        if (iA >= 0) {
            this.f494c[iA] = e2;
            return;
        }
        int i2 = ~iA;
        if (i2 < this.f495d) {
            Object[] objArr = this.f494c;
            if (objArr[i2] == f491e) {
                this.f493b[i2] = i;
                objArr[i2] = e2;
                return;
            }
        }
        if (this.f492a && this.f495d >= this.f493b.length) {
            c();
            i2 = ~d.a(this.f493b, this.f495d, i);
        }
        int i3 = this.f495d;
        if (i3 >= this.f493b.length) {
            int iE = d.e(i3 + 1);
            int[] iArr = new int[iE];
            Object[] objArr2 = new Object[iE];
            int[] iArr2 = this.f493b;
            System.arraycopy(iArr2, 0, iArr, 0, iArr2.length);
            Object[] objArr3 = this.f494c;
            System.arraycopy(objArr3, 0, objArr2, 0, objArr3.length);
            this.f493b = iArr;
            this.f494c = objArr2;
        }
        int i4 = this.f495d;
        if (i4 - i2 != 0) {
            int[] iArr3 = this.f493b;
            int i5 = i2 + 1;
            System.arraycopy(iArr3, i2, iArr3, i5, i4 - i2);
            Object[] objArr4 = this.f494c;
            System.arraycopy(objArr4, i2, objArr4, i5, this.f495d - i2);
        }
        this.f493b[i2] = i;
        this.f494c[i2] = e2;
        this.f495d++;
    }

    public void h(int i) {
        int iA = d.a(this.f493b, this.f495d, i);
        if (iA >= 0) {
            Object[] objArr = this.f494c;
            Object obj = objArr[iA];
            Object obj2 = f491e;
            if (obj != obj2) {
                objArr[iA] = obj2;
                this.f492a = true;
            }
        }
    }

    public int i() {
        if (this.f492a) {
            c();
        }
        return this.f495d;
    }

    public E j(int i) {
        if (this.f492a) {
            c();
        }
        return (E) this.f494c[i];
    }

    public String toString() {
        if (i() <= 0) {
            return "{}";
        }
        StringBuilder sb = new StringBuilder(this.f495d * 28);
        sb.append('{');
        for (int i = 0; i < this.f495d; i++) {
            if (i > 0) {
                sb.append(", ");
            }
            sb.append(f(i));
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
}
