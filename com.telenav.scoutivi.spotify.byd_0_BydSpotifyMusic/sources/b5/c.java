package b5;

/* JADX INFO: compiled from: OpenHashSet.java */
/* JADX INFO: loaded from: classes.dex */
public final class c<T> {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    final float f3555a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    int f3556b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    int f3557c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    int f3558d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    T[] f3559e;

    public c() {
        this(16, 0.75f);
    }

    static int c(int i7) {
        int i8 = i7 * (-1640531527);
        return i8 ^ (i8 >>> 16);
    }

    public boolean a(T t6) {
        T t7;
        T[] tArr = this.f3559e;
        int i7 = this.f3556b;
        int iC = c(t6.hashCode()) & i7;
        T t8 = tArr[iC];
        if (t8 != null) {
            if (t8.equals(t6)) {
                return false;
            }
            do {
                iC = (iC + 1) & i7;
                t7 = tArr[iC];
                if (t7 == null) {
                }
            } while (!t7.equals(t6));
            return false;
        }
        tArr[iC] = t6;
        int i8 = this.f3557c + 1;
        this.f3557c = i8;
        if (i8 >= this.f3558d) {
            d();
        }
        return true;
    }

    public Object[] b() {
        return this.f3559e;
    }

    void d() {
        T[] tArr = this.f3559e;
        int length = tArr.length;
        int i7 = length << 1;
        int i8 = i7 - 1;
        T[] tArr2 = (T[]) new Object[i7];
        int i9 = this.f3557c;
        while (true) {
            int i10 = i9 - 1;
            if (i9 == 0) {
                this.f3556b = i8;
                this.f3558d = (int) (i7 * this.f3555a);
                this.f3559e = tArr2;
                return;
            }
            do {
                length--;
            } while (tArr[length] == null);
            int iC = c(tArr[length].hashCode()) & i8;
            if (tArr2[iC] != null) {
                do {
                    iC = (iC + 1) & i8;
                } while (tArr2[iC] != null);
            }
            tArr2[iC] = tArr[length];
            i9 = i10;
        }
    }

    public boolean e(T t6) {
        T t7;
        T[] tArr = this.f3559e;
        int i7 = this.f3556b;
        int iC = c(t6.hashCode()) & i7;
        T t8 = tArr[iC];
        if (t8 == null) {
            return false;
        }
        if (t8.equals(t6)) {
            return f(iC, tArr, i7);
        }
        do {
            iC = (iC + 1) & i7;
            t7 = tArr[iC];
            if (t7 == null) {
                return false;
            }
        } while (!t7.equals(t6));
        return f(iC, tArr, i7);
    }

    boolean f(int i7, T[] tArr, int i8) {
        int i9;
        T t6;
        this.f3557c--;
        while (true) {
            int i10 = i7 + 1;
            while (true) {
                i9 = i10 & i8;
                t6 = tArr[i9];
                if (t6 == null) {
                    tArr[i7] = null;
                    return true;
                }
                int iC = c(t6.hashCode()) & i8;
                if (i7 <= i9) {
                    if (i7 >= iC || iC > i9) {
                        break;
                    }
                    i10 = i9 + 1;
                } else if (i7 < iC || iC <= i9) {
                    i10 = i9 + 1;
                }
            }
            tArr[i7] = t6;
            i7 = i9;
        }
    }

    public c(int i7, float f7) {
        this.f3555a = f7;
        int iA = d.a(i7);
        this.f3556b = iA - 1;
        this.f3558d = (int) (f7 * iA);
        this.f3559e = (T[]) new Object[iA];
    }
}
