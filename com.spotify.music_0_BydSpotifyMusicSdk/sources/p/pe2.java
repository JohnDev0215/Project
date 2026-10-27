package p;

import java.util.Arrays;

/* JADX INFO: loaded from: classes.dex */
public final class pe2 implements Cloneable {
    public /* synthetic */ int[] c;
    public /* synthetic */ Object[] e;
    public /* synthetic */ int k;

    public pe2() {
        int i;
        int i2 = 4;
        while (true) {
            i = 40;
            if (i2 >= 32) {
                break;
            }
            int i3 = (1 << i2) - 12;
            if (40 <= i3) {
                i = i3;
                break;
            }
            i2++;
        }
        int i4 = i / 4;
        this.c = new int[i4];
        this.e = new Object[i4];
    }

    public final void a(int i, Object obj) {
        int i2 = this.k;
        if (i2 != 0 && i <= this.c[i2 - 1]) {
            f(i, obj);
            return;
        }
        if (i2 >= this.c.length) {
            int i3 = (i2 + 1) * 4;
            int i4 = 4;
            while (true) {
                if (i4 >= 32) {
                    break;
                }
                int i5 = (1 << i4) - 12;
                if (i3 <= i5) {
                    i3 = i5;
                    break;
                }
                i4++;
            }
            int i6 = i3 / 4;
            int[] iArrCopyOf = Arrays.copyOf(this.c, i6);
            v03.i(iArrCopyOf, "copyOf(this, newSize)");
            this.c = iArrCopyOf;
            Object[] objArrCopyOf = Arrays.copyOf(this.e, i6);
            v03.i(objArrCopyOf, "copyOf(this, newSize)");
            this.e = objArrCopyOf;
        }
        this.c[i2] = i;
        this.e[i2] = obj;
        this.k = i2 + 1;
    }

    /* JADX INFO: renamed from: d, reason: merged with bridge method [inline-methods] */
    public final pe2 clone() throws CloneNotSupportedException {
        Object objClone = super.clone();
        v03.g(objClone, "null cannot be cast to non-null type androidx.collection.SparseArrayCompat<E of androidx.collection.SparseArrayCompat>");
        pe2 pe2Var = (pe2) objClone;
        pe2Var.c = (int[]) this.c.clone();
        pe2Var.e = (Object[]) this.e.clone();
        return pe2Var;
    }

    public final Object e(int i) {
        Object obj;
        Object obj2 = qe2.a;
        int i2 = zu.i(this.k, i, this.c);
        if (i2 < 0 || (obj = this.e[i2]) == qe2.a) {
            return null;
        }
        return obj;
    }

    public final void f(int i, Object obj) {
        int i2 = zu.i(this.k, i, this.c);
        if (i2 >= 0) {
            this.e[i2] = obj;
            return;
        }
        int i3 = ~i2;
        int i4 = this.k;
        if (i3 < i4) {
            Object[] objArr = this.e;
            if (objArr[i3] == qe2.a) {
                this.c[i3] = i;
                objArr[i3] = obj;
                return;
            }
        }
        if (i4 >= this.c.length) {
            int i5 = (i4 + 1) * 4;
            int i6 = 4;
            while (true) {
                if (i6 >= 32) {
                    break;
                }
                int i7 = (1 << i6) - 12;
                if (i5 <= i7) {
                    i5 = i7;
                    break;
                }
                i6++;
            }
            int i8 = i5 / 4;
            int[] iArrCopyOf = Arrays.copyOf(this.c, i8);
            v03.i(iArrCopyOf, "copyOf(this, newSize)");
            this.c = iArrCopyOf;
            Object[] objArrCopyOf = Arrays.copyOf(this.e, i8);
            v03.i(objArrCopyOf, "copyOf(this, newSize)");
            this.e = objArrCopyOf;
        }
        int i9 = this.k;
        if (i9 - i3 != 0) {
            int[] iArr = this.c;
            int i10 = i3 + 1;
            rb.j0(i10, i3, i9, iArr, iArr);
            Object[] objArr2 = this.e;
            rb.k0(i10, i3, this.k, objArr2, objArr2);
        }
        this.c[i3] = i;
        this.e[i3] = obj;
        this.k++;
    }

    public final String toString() {
        int i = this.k;
        if (i <= 0) {
            return "{}";
        }
        StringBuilder sb = new StringBuilder(i * 28);
        sb.append('{');
        int i2 = this.k;
        for (int i3 = 0; i3 < i2; i3++) {
            if (i3 > 0) {
                sb.append(", ");
            }
            sb.append(this.c[i3]);
            sb.append('=');
            Object obj = this.e[i3];
            if (obj != this) {
                sb.append(obj);
            } else {
                sb.append("(this Map)");
            }
        }
        sb.append('}');
        String string = sb.toString();
        v03.i(string, "buffer.toString()");
        return string;
    }
}
