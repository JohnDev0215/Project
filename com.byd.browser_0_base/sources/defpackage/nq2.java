package defpackage;

import java.util.Arrays;
import kotlin.jvm.internal.SourceDebugExtension;

/* JADX INFO: compiled from: SparseArrayCompat.jvm.kt */
/* JADX INFO: loaded from: classes.dex */
@SourceDebugExtension({"SMAP\nSparseArrayCompat.jvm.kt\nKotlin\n*S Kotlin\n*F\n+ 1 SparseArrayCompat.jvm.kt\nandroidx/collection/SparseArrayCompat\n+ 2 SparseArrayCompat.kt\nandroidx/collection/SparseArrayCompatKt\n*L\n1#1,278:1\n274#2,9:279\n287#2,5:288\n295#2,5:293\n303#2,8:298\n319#2,9:306\n352#2,40:315\n395#2,2:355\n352#2,47:357\n402#2,3:404\n352#2,40:407\n406#2:447\n411#2,4:448\n418#2:452\n422#2,4:453\n430#2,8:457\n442#2,5:465\n450#2,4:470\n458#2,9:474\n471#2:483\n476#2:484\n458#2,9:485\n481#2,8:494\n492#2,17:502\n512#2,21:519\n*S KotlinDebug\n*F\n+ 1 SparseArrayCompat.jvm.kt\nandroidx/collection/SparseArrayCompat\n*L\n135#1:279,9\n140#1:288,5\n149#1:293,5\n157#1:298,8\n168#1:306,9\n174#1:315,40\n181#1:355,2\n181#1:357,47\n191#1:404,3\n191#1:407,40\n191#1:447\n196#1:448,4\n210#1:452\n217#1:453,4\n223#1:457,8\n229#1:465,5\n239#1:470,4\n251#1:474,9\n254#1:483\n257#1:484\n257#1:485,9\n262#1:494,8\n268#1:502,17\n276#1:519,21\n*E\n"})
public final class nq2<E> implements Cloneable {
    public /* synthetic */ int[] e;
    public /* synthetic */ Object[] f;
    public /* synthetic */ int g;

    public nq2() {
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
        this.e = new int[i4];
        this.f = new Object[i4];
    }

    public final void b(int i, E e) {
        int i2 = this.g;
        if (i2 != 0 && i <= this.e[i2 - 1]) {
            h(i, e);
            return;
        }
        if (i2 >= this.e.length) {
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
            int[] iArrCopyOf = Arrays.copyOf(this.e, i6);
            i21.d(iArrCopyOf, "copyOf(this, newSize)");
            this.e = iArrCopyOf;
            Object[] objArrCopyOf = Arrays.copyOf(this.f, i6);
            i21.d(objArrCopyOf, "copyOf(this, newSize)");
            this.f = objArrCopyOf;
        }
        this.e[i2] = i;
        this.f[i2] = e;
        this.g = i2 + 1;
    }

    /* JADX INFO: renamed from: e, reason: merged with bridge method [inline-methods] */
    public final nq2<E> clone() throws CloneNotSupportedException {
        Object objClone = super.clone();
        i21.c(objClone, "null cannot be cast to non-null type androidx.collection.SparseArrayCompat<E of androidx.collection.SparseArrayCompat>");
        nq2<E> nq2Var = (nq2) objClone;
        nq2Var.e = (int[]) this.e.clone();
        nq2Var.f = (Object[]) this.f.clone();
        return nq2Var;
    }

    public final E g(int i) {
        E e;
        Object obj = oq2.a;
        int iJ = a8.j(this.g, i, this.e);
        if (iJ < 0 || (e = (E) this.f[iJ]) == oq2.a) {
            return null;
        }
        return e;
    }

    public final void h(int i, E e) {
        int iJ = a8.j(this.g, i, this.e);
        if (iJ >= 0) {
            this.f[iJ] = e;
            return;
        }
        int i2 = ~iJ;
        int i3 = this.g;
        if (i2 < i3) {
            Object[] objArr = this.f;
            if (objArr[i2] == oq2.a) {
                this.e[i2] = i;
                objArr[i2] = e;
                return;
            }
        }
        if (i3 >= this.e.length) {
            int i4 = (i3 + 1) * 4;
            int i5 = 4;
            while (true) {
                if (i5 >= 32) {
                    break;
                }
                int i6 = (1 << i5) - 12;
                if (i4 <= i6) {
                    i4 = i6;
                    break;
                }
                i5++;
            }
            int i7 = i4 / 4;
            int[] iArrCopyOf = Arrays.copyOf(this.e, i7);
            i21.d(iArrCopyOf, "copyOf(this, newSize)");
            this.e = iArrCopyOf;
            Object[] objArrCopyOf = Arrays.copyOf(this.f, i7);
            i21.d(objArrCopyOf, "copyOf(this, newSize)");
            this.f = objArrCopyOf;
        }
        int i8 = this.g;
        if (i8 - i2 != 0) {
            int[] iArr = this.e;
            int i9 = i2 + 1;
            r8.p0(i9, i2, i8, iArr, iArr);
            Object[] objArr2 = this.f;
            r8.q0(i9, i2, this.g, objArr2, objArr2);
        }
        this.e[i2] = i;
        this.f[i2] = e;
        this.g++;
    }

    public final String toString() {
        int i = this.g;
        if (i <= 0) {
            return "{}";
        }
        StringBuilder sb = new StringBuilder(i * 28);
        sb.append('{');
        int i2 = this.g;
        for (int i3 = 0; i3 < i2; i3++) {
            if (i3 > 0) {
                sb.append(", ");
            }
            sb.append(this.e[i3]);
            sb.append('=');
            Object obj = this.f[i3];
            if (obj != this) {
                sb.append(obj);
            } else {
                sb.append("(this Map)");
            }
        }
        sb.append('}');
        String string = sb.toString();
        i21.d(string, "buffer.toString()");
        return string;
    }
}
