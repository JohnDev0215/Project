package defpackage;

import java.util.Arrays;
import kotlin.jvm.internal.SourceDebugExtension;

/* JADX INFO: compiled from: LongSparseArray.jvm.kt */
/* JADX INFO: loaded from: classes.dex */
@SourceDebugExtension({"SMAP\nLongSparseArray.jvm.kt\nKotlin\n*S Kotlin\n*F\n+ 1 LongSparseArray.jvm.kt\nandroidx/collection/LongSparseArray\n+ 2 LongSparseArray.kt\nandroidx/collection/LongSparseArrayKt\n*L\n1#1,258:1\n243#2:259\n256#2,6:260\n248#2,14:266\n267#2,8:280\n267#2,8:288\n278#2,9:296\n291#2,5:305\n299#2,8:310\n315#2,9:318\n349#2,12:327\n328#2,18:339\n363#2,26:357\n392#2,5:383\n400#2,5:388\n409#2,2:393\n328#2,18:395\n412#2:413\n416#2:414\n420#2,6:415\n328#2,18:421\n427#2:439\n432#2,6:440\n328#2,18:446\n441#2:464\n446#2,6:465\n328#2,18:471\n453#2,2:489\n458#2,2:491\n328#2,18:493\n461#2:511\n466#2,2:512\n328#2,18:514\n469#2,6:532\n479#2:538\n484#2:539\n489#2,8:540\n500#2,6:548\n328#2,18:554\n507#2,10:572\n520#2,21:582\n*S KotlinDebug\n*F\n+ 1 LongSparseArray.jvm.kt\nandroidx/collection/LongSparseArray\n*L\n97#1:259\n97#1:260,6\n104#1:266,14\n110#1:280,8\n115#1:288,8\n124#1:296,9\n129#1:305,5\n138#1:310,8\n149#1:318,9\n155#1:327,12\n155#1:339,18\n155#1:357,26\n161#1:383,5\n172#1:388,5\n177#1:393,2\n177#1:395,18\n177#1:413\n184#1:414\n196#1:415,6\n196#1:421,18\n196#1:439\n208#1:440,6\n208#1:446,18\n208#1:464\n216#1:465,6\n216#1:471,18\n216#1:489,2\n223#1:491,2\n223#1:493,18\n223#1:511\n232#1:512,2\n232#1:514,18\n232#1:532,6\n235#1:538\n238#1:539\n243#1:540,8\n249#1:548,6\n249#1:554,18\n249#1:572,10\n257#1:582,21\n*E\n"})
public final class sa1<E> implements Cloneable {
    public /* synthetic */ boolean e;
    public /* synthetic */ long[] f;
    public /* synthetic */ Object[] g;
    public /* synthetic */ int h;

    public sa1() {
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
        this.f = new long[i4];
        this.g = new Object[i4];
    }

    public final void b() {
        int i = this.h;
        Object[] objArr = this.g;
        for (int i2 = 0; i2 < i; i2++) {
            objArr[i2] = null;
        }
        this.h = 0;
        this.e = false;
    }

    /* JADX INFO: renamed from: e, reason: merged with bridge method [inline-methods] */
    public final sa1<E> clone() throws CloneNotSupportedException {
        Object objClone = super.clone();
        i21.c(objClone, "null cannot be cast to non-null type androidx.collection.LongSparseArray<E of androidx.collection.LongSparseArray>");
        sa1<E> sa1Var = (sa1) objClone;
        sa1Var.f = (long[]) this.f.clone();
        sa1Var.g = (Object[]) this.g.clone();
        return sa1Var;
    }

    public final E g(long j) {
        E e;
        int iK = a8.k(this.f, this.h, j);
        if (iK < 0 || (e = (E) this.g[iK]) == ta1.a) {
            return null;
        }
        return e;
    }

    public final Object h(long j, Long l) {
        Object obj;
        int iK = a8.k(this.f, this.h, j);
        return (iK < 0 || (obj = this.g[iK]) == ta1.a) ? l : obj;
    }

    public final int i(long j) {
        if (this.e) {
            int i = this.h;
            long[] jArr = this.f;
            Object[] objArr = this.g;
            int i2 = 0;
            for (int i3 = 0; i3 < i; i3++) {
                Object obj = objArr[i3];
                if (obj != ta1.a) {
                    if (i3 != i2) {
                        jArr[i2] = jArr[i3];
                        objArr[i2] = obj;
                        objArr[i3] = null;
                    }
                    i2++;
                }
            }
            this.e = false;
            this.h = i2;
        }
        return a8.k(this.f, this.h, j);
    }

    public final long j(int i) {
        if (!(i >= 0 && i < this.h)) {
            throw new IllegalArgumentException(gq2.e("Expected index to be within 0..size()-1, but was ", i).toString());
        }
        if (this.e) {
            int i2 = this.h;
            long[] jArr = this.f;
            Object[] objArr = this.g;
            int i3 = 0;
            for (int i4 = 0; i4 < i2; i4++) {
                Object obj = objArr[i4];
                if (obj != ta1.a) {
                    if (i4 != i3) {
                        jArr[i3] = jArr[i4];
                        objArr[i3] = obj;
                        objArr[i4] = null;
                    }
                    i3++;
                }
            }
            this.e = false;
            this.h = i3;
        }
        return this.f[i];
    }

    public final void k(long j, E e) {
        int iK = a8.k(this.f, this.h, j);
        if (iK >= 0) {
            this.g[iK] = e;
            return;
        }
        int i = ~iK;
        int i2 = this.h;
        if (i < i2) {
            Object[] objArr = this.g;
            if (objArr[i] == ta1.a) {
                this.f[i] = j;
                objArr[i] = e;
                return;
            }
        }
        if (this.e) {
            long[] jArr = this.f;
            if (i2 >= jArr.length) {
                Object[] objArr2 = this.g;
                int i3 = 0;
                for (int i4 = 0; i4 < i2; i4++) {
                    Object obj = objArr2[i4];
                    if (obj != ta1.a) {
                        if (i4 != i3) {
                            jArr[i3] = jArr[i4];
                            objArr2[i3] = obj;
                            objArr2[i4] = null;
                        }
                        i3++;
                    }
                }
                this.e = false;
                this.h = i3;
                i = ~a8.k(this.f, i3, j);
            }
        }
        int i5 = this.h;
        if (i5 >= this.f.length) {
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
            long[] jArrCopyOf = Arrays.copyOf(this.f, i9);
            i21.d(jArrCopyOf, "copyOf(this, newSize)");
            this.f = jArrCopyOf;
            Object[] objArrCopyOf = Arrays.copyOf(this.g, i9);
            i21.d(objArrCopyOf, "copyOf(this, newSize)");
            this.g = objArrCopyOf;
        }
        int i10 = this.h - i;
        if (i10 != 0) {
            long[] jArr2 = this.f;
            int i11 = i + 1;
            i21.e(jArr2, "<this>");
            System.arraycopy(jArr2, i, jArr2, i11, i10);
            Object[] objArr3 = this.g;
            r8.q0(i11, i, this.h, objArr3, objArr3);
        }
        this.f[i] = j;
        this.g[i] = e;
        this.h++;
    }

    public final void l(long j) {
        int iK = a8.k(this.f, this.h, j);
        if (iK >= 0) {
            Object[] objArr = this.g;
            Object obj = objArr[iK];
            Object obj2 = ta1.a;
            if (obj != obj2) {
                objArr[iK] = obj2;
                this.e = true;
            }
        }
    }

    public final int m() {
        if (this.e) {
            int i = this.h;
            long[] jArr = this.f;
            Object[] objArr = this.g;
            int i2 = 0;
            for (int i3 = 0; i3 < i; i3++) {
                Object obj = objArr[i3];
                if (obj != ta1.a) {
                    if (i3 != i2) {
                        jArr[i2] = jArr[i3];
                        objArr[i2] = obj;
                        objArr[i3] = null;
                    }
                    i2++;
                }
            }
            this.e = false;
            this.h = i2;
        }
        return this.h;
    }

    public final E n(int i) {
        if (!(i >= 0 && i < this.h)) {
            throw new IllegalArgumentException(gq2.e("Expected index to be within 0..size()-1, but was ", i).toString());
        }
        if (this.e) {
            int i2 = this.h;
            long[] jArr = this.f;
            Object[] objArr = this.g;
            int i3 = 0;
            for (int i4 = 0; i4 < i2; i4++) {
                Object obj = objArr[i4];
                if (obj != ta1.a) {
                    if (i4 != i3) {
                        jArr[i3] = jArr[i4];
                        objArr[i3] = obj;
                        objArr[i4] = null;
                    }
                    i3++;
                }
            }
            this.e = false;
            this.h = i3;
        }
        return (E) this.g[i];
    }

    public final String toString() {
        if (m() <= 0) {
            return "{}";
        }
        StringBuilder sb = new StringBuilder(this.h * 28);
        sb.append('{');
        int i = this.h;
        for (int i2 = 0; i2 < i; i2++) {
            if (i2 > 0) {
                sb.append(", ");
            }
            sb.append(j(i2));
            sb.append('=');
            E eN = n(i2);
            if (eN != sb) {
                sb.append(eN);
            } else {
                sb.append("(this Map)");
            }
        }
        sb.append('}');
        String string = sb.toString();
        i21.d(string, "StringBuilder(capacity).…builderAction).toString()");
        return string;
    }
}
