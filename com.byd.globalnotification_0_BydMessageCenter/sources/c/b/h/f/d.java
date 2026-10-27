package c.b.h.f;

/* JADX INFO: loaded from: classes.dex */
public final class d<T> {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final float f2326a = 0.75f;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public int f2327b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public int f2328c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public int f2329d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public T[] f2330e;

    public d() {
        int iNumberOfLeadingZeros = 1 << (32 - Integer.numberOfLeadingZeros(15));
        this.f2327b = iNumberOfLeadingZeros - 1;
        this.f2329d = (int) (0.75f * iNumberOfLeadingZeros);
        this.f2330e = (T[]) new Object[iNumberOfLeadingZeros];
    }

    public static int b(int i) {
        int i2 = i * (-1640531527);
        return i2 ^ (i2 >>> 16);
    }

    public boolean a(T t) {
        T t2;
        T[] tArr = this.f2330e;
        int i = this.f2327b;
        int iB = b(t.hashCode()) & i;
        T t3 = tArr[iB];
        if (t3 != null) {
            if (t3.equals(t)) {
                return false;
            }
            do {
                iB = (iB + 1) & i;
                t2 = tArr[iB];
                if (t2 == null) {
                }
            } while (!t2.equals(t));
            return false;
        }
        tArr[iB] = t;
        int i2 = this.f2328c + 1;
        this.f2328c = i2;
        if (i2 >= this.f2329d) {
            T[] tArr2 = this.f2330e;
            int length = tArr2.length;
            int i3 = length << 1;
            int i4 = i3 - 1;
            T[] tArr3 = (T[]) new Object[i3];
            while (true) {
                int i5 = i2 - 1;
                if (i2 == 0) {
                    break;
                }
                do {
                    length--;
                } while (tArr2[length] == null);
                int iB2 = b(tArr2[length].hashCode()) & i4;
                if (tArr3[iB2] != null) {
                    do {
                        iB2 = (iB2 + 1) & i4;
                    } while (tArr3[iB2] != null);
                }
                tArr3[iB2] = tArr2[length];
                i2 = i5;
            }
            this.f2327b = i4;
            this.f2329d = (int) (i3 * this.f2326a);
            this.f2330e = tArr3;
        }
        return true;
    }

    public boolean c(int i, T[] tArr, int i2) {
        int i3;
        T t;
        this.f2328c--;
        while (true) {
            int i4 = i + 1;
            while (true) {
                i3 = i4 & i2;
                t = tArr[i3];
                if (t == null) {
                    tArr[i] = null;
                    return true;
                }
                int iB = b(t.hashCode()) & i2;
                if (i <= i3) {
                    if (i >= iB || iB > i3) {
                        break;
                    }
                    i4 = i3 + 1;
                } else if (i < iB || iB <= i3) {
                    i4 = i3 + 1;
                }
            }
            tArr[i] = t;
            i = i3;
        }
    }
}
