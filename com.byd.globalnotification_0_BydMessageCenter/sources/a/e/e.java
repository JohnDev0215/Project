package a.e;

/* JADX INFO: loaded from: classes.dex */
public class e<E> implements Cloneable {

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public static final Object f459e = new Object();

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public boolean f460a = false;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public long[] f461b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public Object[] f462c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public int f463d;

    public e() {
        int iF = d.f(10);
        this.f461b = new long[iF];
        this.f462c = new Object[iF];
    }

    public void a() {
        int i = this.f463d;
        Object[] objArr = this.f462c;
        for (int i2 = 0; i2 < i; i2++) {
            objArr[i2] = null;
        }
        this.f463d = 0;
        this.f460a = false;
    }

    /* JADX INFO: renamed from: b, reason: merged with bridge method [inline-methods] */
    public e<E> clone() {
        try {
            e<E> eVar = (e) super.clone();
            eVar.f461b = (long[]) this.f461b.clone();
            eVar.f462c = (Object[]) this.f462c.clone();
            return eVar;
        } catch (CloneNotSupportedException e2) {
            throw new AssertionError(e2);
        }
    }

    public final void c() {
        int i = this.f463d;
        long[] jArr = this.f461b;
        Object[] objArr = this.f462c;
        int i2 = 0;
        for (int i3 = 0; i3 < i; i3++) {
            Object obj = objArr[i3];
            if (obj != f459e) {
                if (i3 != i2) {
                    jArr[i2] = jArr[i3];
                    objArr[i2] = obj;
                    objArr[i3] = null;
                }
                i2++;
            }
        }
        this.f460a = false;
        this.f463d = i2;
    }

    public E d(long j) {
        return e(j, null);
    }

    public E e(long j, E e2) {
        int iB = d.b(this.f461b, this.f463d, j);
        if (iB >= 0) {
            Object[] objArr = this.f462c;
            if (objArr[iB] != f459e) {
                return (E) objArr[iB];
            }
        }
        return e2;
    }

    public void f(long j, E e2) {
        int iB = d.b(this.f461b, this.f463d, j);
        if (iB >= 0) {
            this.f462c[iB] = e2;
            return;
        }
        int i = ~iB;
        if (i < this.f463d) {
            Object[] objArr = this.f462c;
            if (objArr[i] == f459e) {
                this.f461b[i] = j;
                objArr[i] = e2;
                return;
            }
        }
        if (this.f460a && this.f463d >= this.f461b.length) {
            c();
            i = ~d.b(this.f461b, this.f463d, j);
        }
        int i2 = this.f463d;
        if (i2 >= this.f461b.length) {
            int iF = d.f(i2 + 1);
            long[] jArr = new long[iF];
            Object[] objArr2 = new Object[iF];
            long[] jArr2 = this.f461b;
            System.arraycopy(jArr2, 0, jArr, 0, jArr2.length);
            Object[] objArr3 = this.f462c;
            System.arraycopy(objArr3, 0, objArr2, 0, objArr3.length);
            this.f461b = jArr;
            this.f462c = objArr2;
        }
        int i3 = this.f463d;
        if (i3 - i != 0) {
            long[] jArr3 = this.f461b;
            int i4 = i + 1;
            System.arraycopy(jArr3, i, jArr3, i4, i3 - i);
            Object[] objArr4 = this.f462c;
            System.arraycopy(objArr4, i, objArr4, i4, this.f463d - i);
        }
        this.f461b[i] = j;
        this.f462c[i] = e2;
        this.f463d++;
    }

    public int g() {
        if (this.f460a) {
            c();
        }
        return this.f463d;
    }

    public E h(int i) {
        if (this.f460a) {
            c();
        }
        return (E) this.f462c[i];
    }

    public String toString() {
        if (g() <= 0) {
            return "{}";
        }
        StringBuilder sb = new StringBuilder(this.f463d * 28);
        sb.append('{');
        for (int i = 0; i < this.f463d; i++) {
            if (i > 0) {
                sb.append(", ");
            }
            if (this.f460a) {
                c();
            }
            sb.append(this.f461b[i]);
            sb.append('=');
            E eH = h(i);
            if (eH != this) {
                sb.append(eH);
            } else {
                sb.append("(this Map)");
            }
        }
        sb.append('}');
        return sb.toString();
    }
}
