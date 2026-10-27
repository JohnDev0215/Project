package p;

import java.util.Arrays;

/* JADX INFO: loaded from: classes.dex */
public final class wd1 implements Cloneable {
    public /* synthetic */ boolean c;
    public /* synthetic */ long[] e;
    public /* synthetic */ Object[] k;
    public /* synthetic */ int l;

    public wd1() {
        int i;
        int i2 = 4;
        while (true) {
            i = 80;
            if (i2 >= 32) {
                break;
            }
            int i3 = (1 << i2) - 12;
            if (80 <= i3) {
                i = i3;
                break;
            }
            i2++;
        }
        int i4 = i / 8;
        this.e = new long[i4];
        this.k = new Object[i4];
    }

    public final void a() {
        int i = this.l;
        Object[] objArr = this.k;
        for (int i2 = 0; i2 < i; i2++) {
            objArr[i2] = null;
        }
        this.l = 0;
        this.c = false;
    }

    /* JADX INFO: renamed from: d, reason: merged with bridge method [inline-methods] */
    public final wd1 clone() throws CloneNotSupportedException {
        Object objClone = super.clone();
        v03.g(objClone, "null cannot be cast to non-null type androidx.collection.LongSparseArray<E of androidx.collection.LongSparseArray>");
        wd1 wd1Var = (wd1) objClone;
        wd1Var.e = (long[]) this.e.clone();
        wd1Var.k = (Object[]) this.k.clone();
        return wd1Var;
    }

    public final Object e(long j) {
        Object obj;
        int iJ = zu.j(this.e, this.l, j);
        if (iJ < 0 || (obj = this.k[iJ]) == xd1.a) {
            return null;
        }
        return obj;
    }

    public final long f(int i) {
        int i2;
        if (i < 0 || i >= (i2 = this.l)) {
            throw new IllegalArgumentException(u4.e("Expected index to be within 0..size()-1, but was ", i).toString());
        }
        if (this.c) {
            long[] jArr = this.e;
            Object[] objArr = this.k;
            int i3 = 0;
            for (int i4 = 0; i4 < i2; i4++) {
                Object obj = objArr[i4];
                if (obj != xd1.a) {
                    if (i4 != i3) {
                        jArr[i3] = jArr[i4];
                        objArr[i3] = obj;
                        objArr[i4] = null;
                    }
                    i3++;
                }
            }
            this.c = false;
            this.l = i3;
        }
        return this.e[i];
    }

    public final void g(long j, Object obj) {
        int iJ = zu.j(this.e, this.l, j);
        if (iJ >= 0) {
            this.k[iJ] = obj;
            return;
        }
        int i = ~iJ;
        int i2 = this.l;
        if (i < i2) {
            Object[] objArr = this.k;
            if (objArr[i] == xd1.a) {
                this.e[i] = j;
                objArr[i] = obj;
                return;
            }
        }
        if (this.c) {
            long[] jArr = this.e;
            if (i2 >= jArr.length) {
                Object[] objArr2 = this.k;
                int i3 = 0;
                for (int i4 = 0; i4 < i2; i4++) {
                    Object obj2 = objArr2[i4];
                    if (obj2 != xd1.a) {
                        if (i4 != i3) {
                            jArr[i3] = jArr[i4];
                            objArr2[i3] = obj2;
                            objArr2[i4] = null;
                        }
                        i3++;
                    }
                }
                this.c = false;
                this.l = i3;
                i = ~zu.j(this.e, i3, j);
            }
        }
        int i5 = this.l;
        if (i5 >= this.e.length) {
            int i6 = (i5 + 1) * 8;
            int i7 = 4;
            while (true) {
                if (i7 >= 32) {
                    break;
                }
                int i8 = (1 << i7) - 12;
                if (i6 <= i8) {
                    i6 = i8;
                    break;
                }
                i7++;
            }
            int i9 = i6 / 8;
            long[] jArrCopyOf = Arrays.copyOf(this.e, i9);
            v03.i(jArrCopyOf, "copyOf(this, newSize)");
            this.e = jArrCopyOf;
            Object[] objArrCopyOf = Arrays.copyOf(this.k, i9);
            v03.i(objArrCopyOf, "copyOf(this, newSize)");
            this.k = objArrCopyOf;
        }
        int i10 = this.l - i;
        if (i10 != 0) {
            long[] jArr2 = this.e;
            int i11 = i + 1;
            v03.j(jArr2, "<this>");
            System.arraycopy(jArr2, i, jArr2, i11, i10);
            Object[] objArr3 = this.k;
            rb.k0(i11, i, this.l, objArr3, objArr3);
        }
        this.e[i] = j;
        this.k[i] = obj;
        this.l++;
    }

    public final int h() {
        if (this.c) {
            int i = this.l;
            long[] jArr = this.e;
            Object[] objArr = this.k;
            int i2 = 0;
            for (int i3 = 0; i3 < i; i3++) {
                Object obj = objArr[i3];
                if (obj != xd1.a) {
                    if (i3 != i2) {
                        jArr[i2] = jArr[i3];
                        objArr[i2] = obj;
                        objArr[i3] = null;
                    }
                    i2++;
                }
            }
            this.c = false;
            this.l = i2;
        }
        return this.l;
    }

    public final Object j(int i) {
        int i2;
        if (i < 0 || i >= (i2 = this.l)) {
            throw new IllegalArgumentException(u4.e("Expected index to be within 0..size()-1, but was ", i).toString());
        }
        if (this.c) {
            long[] jArr = this.e;
            Object[] objArr = this.k;
            int i3 = 0;
            for (int i4 = 0; i4 < i2; i4++) {
                Object obj = objArr[i4];
                if (obj != xd1.a) {
                    if (i4 != i3) {
                        jArr[i3] = jArr[i4];
                        objArr[i3] = obj;
                        objArr[i4] = null;
                    }
                    i3++;
                }
            }
            this.c = false;
            this.l = i3;
        }
        return this.k[i];
    }

    public final String toString() {
        if (h() <= 0) {
            return "{}";
        }
        StringBuilder sb = new StringBuilder(this.l * 28);
        sb.append('{');
        int i = this.l;
        for (int i2 = 0; i2 < i; i2++) {
            if (i2 > 0) {
                sb.append(", ");
            }
            sb.append(f(i2));
            sb.append('=');
            Object objJ = j(i2);
            if (objJ != sb) {
                sb.append(objJ);
            } else {
                sb.append("(this Map)");
            }
        }
        sb.append('}');
        String string = sb.toString();
        v03.i(string, "StringBuilder(capacity).…builderAction).toString()");
        return string;
    }
}
