package b.a;

import androidx.annotation.Nullable;

/* JADX INFO: compiled from: caradapter-bug */
/* JADX INFO: loaded from: classes.dex */
public class e<E> implements Cloneable {
    private static final Object g = new Object();

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    private boolean f1272c;
    private long[] d;
    private Object[] e;
    private int f;

    public e() {
        this(10);
    }

    private void d() {
        int i = this.f;
        long[] jArr = this.d;
        Object[] objArr = this.e;
        int i2 = 0;
        for (int i3 = 0; i3 < i; i3++) {
            Object obj = objArr[i3];
            if (obj != g) {
                if (i3 != i2) {
                    jArr[i2] = jArr[i3];
                    objArr[i2] = obj;
                    objArr[i3] = null;
                }
                i2++;
            }
        }
        this.f1272c = false;
        this.f = i2;
    }

    public void a(long j, E e) {
        int i = this.f;
        if (i != 0 && j <= this.d[i - 1]) {
            h(j, e);
            return;
        }
        if (this.f1272c && this.f >= this.d.length) {
            d();
        }
        int i2 = this.f;
        if (i2 >= this.d.length) {
            int iF = c.f(i2 + 1);
            long[] jArr = new long[iF];
            Object[] objArr = new Object[iF];
            long[] jArr2 = this.d;
            System.arraycopy(jArr2, 0, jArr, 0, jArr2.length);
            Object[] objArr2 = this.e;
            System.arraycopy(objArr2, 0, objArr, 0, objArr2.length);
            this.d = jArr;
            this.e = objArr;
        }
        this.d[i2] = j;
        this.e[i2] = e;
        this.f = i2 + 1;
    }

    public void b() {
        int i = this.f;
        Object[] objArr = this.e;
        for (int i2 = 0; i2 < i; i2++) {
            objArr[i2] = null;
        }
        this.f = 0;
        this.f1272c = false;
    }

    /* JADX INFO: renamed from: c, reason: merged with bridge method [inline-methods] */
    public e<E> clone() {
        try {
            e<E> eVar = (e) super.clone();
            eVar.d = (long[]) this.d.clone();
            eVar.e = (Object[]) this.e.clone();
            return eVar;
        } catch (CloneNotSupportedException e) {
            throw new AssertionError(e);
        }
    }

    @Nullable
    public E e(long j) {
        return f(j, null);
    }

    public E f(long j, E e) {
        int iB = c.b(this.d, this.f, j);
        if (iB >= 0) {
            Object[] objArr = this.e;
            if (objArr[iB] != g) {
                return (E) objArr[iB];
            }
        }
        return e;
    }

    public long g(int i) {
        if (this.f1272c) {
            d();
        }
        return this.d[i];
    }

    public void h(long j, E e) {
        int iB = c.b(this.d, this.f, j);
        if (iB >= 0) {
            this.e[iB] = e;
            return;
        }
        int i = ~iB;
        if (i < this.f) {
            Object[] objArr = this.e;
            if (objArr[i] == g) {
                this.d[i] = j;
                objArr[i] = e;
                return;
            }
        }
        if (this.f1272c && this.f >= this.d.length) {
            d();
            i = ~c.b(this.d, this.f, j);
        }
        int i2 = this.f;
        if (i2 >= this.d.length) {
            int iF = c.f(i2 + 1);
            long[] jArr = new long[iF];
            Object[] objArr2 = new Object[iF];
            long[] jArr2 = this.d;
            System.arraycopy(jArr2, 0, jArr, 0, jArr2.length);
            Object[] objArr3 = this.e;
            System.arraycopy(objArr3, 0, objArr2, 0, objArr3.length);
            this.d = jArr;
            this.e = objArr2;
        }
        int i3 = this.f;
        if (i3 - i != 0) {
            long[] jArr3 = this.d;
            int i4 = i + 1;
            System.arraycopy(jArr3, i, jArr3, i4, i3 - i);
            Object[] objArr4 = this.e;
            System.arraycopy(objArr4, i, objArr4, i4, this.f - i);
        }
        this.d[i] = j;
        this.e[i] = e;
        this.f++;
    }

    public void i(long j) {
        int iB = c.b(this.d, this.f, j);
        if (iB >= 0) {
            Object[] objArr = this.e;
            Object obj = objArr[iB];
            Object obj2 = g;
            if (obj != obj2) {
                objArr[iB] = obj2;
                this.f1272c = true;
            }
        }
    }

    public void j(int i) {
        Object[] objArr = this.e;
        Object obj = objArr[i];
        Object obj2 = g;
        if (obj != obj2) {
            objArr[i] = obj2;
            this.f1272c = true;
        }
    }

    public int k() {
        if (this.f1272c) {
            d();
        }
        return this.f;
    }

    public E l(int i) {
        if (this.f1272c) {
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
            sb.append(g(i));
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

    public e(int i) {
        this.f1272c = false;
        if (i == 0) {
            this.d = c.f1269b;
            this.e = c.f1270c;
        } else {
            int iF = c.f(i);
            this.d = new long[iF];
            this.e = new Object[iF];
        }
    }
}
