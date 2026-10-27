package b.d;

/* JADX INFO: compiled from: LongSparseArray.java */
/* JADX INFO: loaded from: classes.dex */
public class d<E> implements Cloneable {

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public static final Object f1160c = new Object();

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public boolean f1161d;
    public long[] s;
    public Object[] x;
    public int y;

    public d() {
        this(10);
    }

    public void a(long j, E e2) {
        int i2 = this.y;
        if (i2 != 0 && j <= this.s[i2 - 1]) {
            j(j, e2);
            return;
        }
        if (this.f1161d && i2 >= this.s.length) {
            f();
        }
        int i3 = this.y;
        if (i3 >= this.s.length) {
            int iF = c.f(i3 + 1);
            long[] jArr = new long[iF];
            Object[] objArr = new Object[iF];
            long[] jArr2 = this.s;
            System.arraycopy(jArr2, 0, jArr, 0, jArr2.length);
            Object[] objArr2 = this.x;
            System.arraycopy(objArr2, 0, objArr, 0, objArr2.length);
            this.s = jArr;
            this.x = objArr;
        }
        this.s[i3] = j;
        this.x[i3] = e2;
        this.y = i3 + 1;
    }

    public void b() {
        int i2 = this.y;
        Object[] objArr = this.x;
        for (int i3 = 0; i3 < i2; i3++) {
            objArr[i3] = null;
        }
        this.y = 0;
        this.f1161d = false;
    }

    /* JADX INFO: renamed from: e, reason: merged with bridge method [inline-methods] */
    public d<E> clone() {
        try {
            d<E> dVar = (d) super.clone();
            dVar.s = (long[]) this.s.clone();
            dVar.x = (Object[]) this.x.clone();
            return dVar;
        } catch (CloneNotSupportedException e2) {
            throw new AssertionError(e2);
        }
    }

    public final void f() {
        int i2 = this.y;
        long[] jArr = this.s;
        Object[] objArr = this.x;
        int i3 = 0;
        for (int i4 = 0; i4 < i2; i4++) {
            Object obj = objArr[i4];
            if (obj != f1160c) {
                if (i4 != i3) {
                    jArr[i3] = jArr[i4];
                    objArr[i3] = obj;
                    objArr[i4] = null;
                }
                i3++;
            }
        }
        this.f1161d = false;
        this.y = i3;
    }

    public E g(long j) {
        return h(j, null);
    }

    public E h(long j, E e2) {
        int iB = c.b(this.s, this.y, j);
        if (iB >= 0) {
            Object[] objArr = this.x;
            if (objArr[iB] != f1160c) {
                return (E) objArr[iB];
            }
        }
        return e2;
    }

    public long i(int i2) {
        if (this.f1161d) {
            f();
        }
        return this.s[i2];
    }

    public void j(long j, E e2) {
        int iB = c.b(this.s, this.y, j);
        if (iB >= 0) {
            this.x[iB] = e2;
            return;
        }
        int i2 = ~iB;
        int i3 = this.y;
        if (i2 < i3) {
            Object[] objArr = this.x;
            if (objArr[i2] == f1160c) {
                this.s[i2] = j;
                objArr[i2] = e2;
                return;
            }
        }
        if (this.f1161d && i3 >= this.s.length) {
            f();
            i2 = ~c.b(this.s, this.y, j);
        }
        int i4 = this.y;
        if (i4 >= this.s.length) {
            int iF = c.f(i4 + 1);
            long[] jArr = new long[iF];
            Object[] objArr2 = new Object[iF];
            long[] jArr2 = this.s;
            System.arraycopy(jArr2, 0, jArr, 0, jArr2.length);
            Object[] objArr3 = this.x;
            System.arraycopy(objArr3, 0, objArr2, 0, objArr3.length);
            this.s = jArr;
            this.x = objArr2;
        }
        int i5 = this.y;
        if (i5 - i2 != 0) {
            long[] jArr3 = this.s;
            int i6 = i2 + 1;
            System.arraycopy(jArr3, i2, jArr3, i6, i5 - i2);
            Object[] objArr4 = this.x;
            System.arraycopy(objArr4, i2, objArr4, i6, this.y - i2);
        }
        this.s[i2] = j;
        this.x[i2] = e2;
        this.y++;
    }

    public void k(long j) {
        int iB = c.b(this.s, this.y, j);
        if (iB >= 0) {
            Object[] objArr = this.x;
            Object obj = objArr[iB];
            Object obj2 = f1160c;
            if (obj != obj2) {
                objArr[iB] = obj2;
                this.f1161d = true;
            }
        }
    }

    public void l(int i2) {
        Object[] objArr = this.x;
        Object obj = objArr[i2];
        Object obj2 = f1160c;
        if (obj != obj2) {
            objArr[i2] = obj2;
            this.f1161d = true;
        }
    }

    public int m() {
        if (this.f1161d) {
            f();
        }
        return this.y;
    }

    public E n(int i2) {
        if (this.f1161d) {
            f();
        }
        return (E) this.x[i2];
    }

    public String toString() {
        if (m() <= 0) {
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
            E eN = n(i2);
            if (eN != this) {
                sb.append(eN);
            } else {
                sb.append("(this Map)");
            }
        }
        sb.append('}');
        return sb.toString();
    }

    public d(int i2) {
        this.f1161d = false;
        if (i2 == 0) {
            this.s = c.f1158b;
            this.x = c.f1159c;
        } else {
            int iF = c.f(i2);
            this.s = new long[iF];
            this.x = new Object[iF];
        }
    }
}
