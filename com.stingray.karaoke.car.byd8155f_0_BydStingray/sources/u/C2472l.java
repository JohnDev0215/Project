package u;

import java.util.Arrays;
import n7.AbstractC1996k;
import v.AbstractC2530a;

/* JADX INFO: renamed from: u.l, reason: case insensitive filesystem */
/* JADX INFO: loaded from: classes.dex */
public final class C2472l implements Cloneable {
    public /* synthetic */ boolean l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public /* synthetic */ long[] f22605m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public /* synthetic */ Object[] f22606n;
    public /* synthetic */ int o;

    public C2472l(int i7) {
        if (i7 == 0) {
            this.f22605m = AbstractC2530a.f22939b;
            this.f22606n = AbstractC2530a.c;
            return;
        }
        int i10 = i7 * 8;
        int i11 = 4;
        while (true) {
            if (i11 >= 32) {
                break;
            }
            int i12 = (1 << i11) - 12;
            if (i10 <= i12) {
                i10 = i12;
                break;
            }
            i11++;
        }
        int i13 = i10 / 8;
        this.f22605m = new long[i13];
        this.f22606n = new Object[i13];
    }

    public final void a() {
        int i7 = this.o;
        Object[] objArr = this.f22606n;
        for (int i10 = 0; i10 < i7; i10++) {
            objArr[i10] = null;
        }
        this.o = 0;
        this.l = false;
    }

    public final Object b(long j3) {
        Object obj;
        int iB = AbstractC2530a.b(this.f22605m, this.o, j3);
        if (iB < 0 || (obj = this.f22606n[iB]) == AbstractC2473m.f22607a) {
            return null;
        }
        return obj;
    }

    public final int c(long j3) {
        if (this.l) {
            int i7 = this.o;
            long[] jArr = this.f22605m;
            Object[] objArr = this.f22606n;
            int i10 = 0;
            for (int i11 = 0; i11 < i7; i11++) {
                Object obj = objArr[i11];
                if (obj != AbstractC2473m.f22607a) {
                    if (i11 != i10) {
                        jArr[i10] = jArr[i11];
                        objArr[i10] = obj;
                        objArr[i11] = null;
                    }
                    i10++;
                }
            }
            this.l = false;
            this.o = i10;
        }
        return AbstractC2530a.b(this.f22605m, this.o, j3);
    }

    public final Object clone() throws CloneNotSupportedException {
        Object objClone = super.clone();
        A7.m.d(objClone, "null cannot be cast to non-null type androidx.collection.LongSparseArray<E of androidx.collection.LongSparseArray>");
        C2472l c2472l = (C2472l) objClone;
        c2472l.f22605m = (long[]) this.f22605m.clone();
        c2472l.f22606n = (Object[]) this.f22606n.clone();
        return c2472l;
    }

    public final long d(int i7) {
        if (!(i7 >= 0 && i7 < this.o)) {
            AbstractC2530a.c("Expected index to be within 0..size()-1, but was " + i7);
            throw null;
        }
        if (this.l) {
            int i10 = this.o;
            long[] jArr = this.f22605m;
            Object[] objArr = this.f22606n;
            int i11 = 0;
            for (int i12 = 0; i12 < i10; i12++) {
                Object obj = objArr[i12];
                if (obj != AbstractC2473m.f22607a) {
                    if (i12 != i11) {
                        jArr[i11] = jArr[i12];
                        objArr[i11] = obj;
                        objArr[i12] = null;
                    }
                    i11++;
                }
            }
            this.l = false;
            this.o = i11;
        }
        return this.f22605m[i7];
    }

    public final void e(long j3, Object obj) {
        int iB = AbstractC2530a.b(this.f22605m, this.o, j3);
        if (iB >= 0) {
            this.f22606n[iB] = obj;
            return;
        }
        int i7 = ~iB;
        int i10 = this.o;
        Object obj2 = AbstractC2473m.f22607a;
        if (i7 < i10) {
            Object[] objArr = this.f22606n;
            if (objArr[i7] == obj2) {
                this.f22605m[i7] = j3;
                objArr[i7] = obj;
                return;
            }
        }
        if (this.l) {
            long[] jArr = this.f22605m;
            if (i10 >= jArr.length) {
                Object[] objArr2 = this.f22606n;
                int i11 = 0;
                for (int i12 = 0; i12 < i10; i12++) {
                    Object obj3 = objArr2[i12];
                    if (obj3 != obj2) {
                        if (i12 != i11) {
                            jArr[i11] = jArr[i12];
                            objArr2[i11] = obj3;
                            objArr2[i12] = null;
                        }
                        i11++;
                    }
                }
                this.l = false;
                this.o = i11;
                i7 = ~AbstractC2530a.b(this.f22605m, i11, j3);
            }
        }
        int i13 = this.o;
        if (i13 >= this.f22605m.length) {
            int i14 = (i13 + 1) * 8;
            int i15 = 4;
            while (true) {
                if (i15 >= 32) {
                    break;
                }
                int i16 = (1 << i15) - 12;
                if (i14 <= i16) {
                    i14 = i16;
                    break;
                }
                i15++;
            }
            int i17 = i14 / 8;
            long[] jArrCopyOf = Arrays.copyOf(this.f22605m, i17);
            A7.m.e(jArrCopyOf, "copyOf(this, newSize)");
            this.f22605m = jArrCopyOf;
            Object[] objArrCopyOf = Arrays.copyOf(this.f22606n, i17);
            A7.m.e(objArrCopyOf, "copyOf(this, newSize)");
            this.f22606n = objArrCopyOf;
        }
        int i18 = this.o - i7;
        if (i18 != 0) {
            long[] jArr2 = this.f22605m;
            int i19 = i7 + 1;
            A7.m.f(jArr2, "<this>");
            System.arraycopy(jArr2, i7, jArr2, i19, i18);
            Object[] objArr3 = this.f22606n;
            AbstractC1996k.D(i19, i7, this.o, objArr3, objArr3);
        }
        this.f22605m[i7] = j3;
        this.f22606n[i7] = obj;
        this.o++;
    }

    public final void f(long j3) {
        int iB = AbstractC2530a.b(this.f22605m, this.o, j3);
        if (iB >= 0) {
            Object[] objArr = this.f22606n;
            Object obj = objArr[iB];
            Object obj2 = AbstractC2473m.f22607a;
            if (obj != obj2) {
                objArr[iB] = obj2;
                this.l = true;
            }
        }
    }

    public final int g() {
        if (this.l) {
            int i7 = this.o;
            long[] jArr = this.f22605m;
            Object[] objArr = this.f22606n;
            int i10 = 0;
            for (int i11 = 0; i11 < i7; i11++) {
                Object obj = objArr[i11];
                if (obj != AbstractC2473m.f22607a) {
                    if (i11 != i10) {
                        jArr[i10] = jArr[i11];
                        objArr[i10] = obj;
                        objArr[i11] = null;
                    }
                    i10++;
                }
            }
            this.l = false;
            this.o = i10;
        }
        return this.o;
    }

    public final Object h(int i7) {
        if (!(i7 >= 0 && i7 < this.o)) {
            AbstractC2530a.c("Expected index to be within 0..size()-1, but was " + i7);
            throw null;
        }
        if (this.l) {
            int i10 = this.o;
            long[] jArr = this.f22605m;
            Object[] objArr = this.f22606n;
            int i11 = 0;
            for (int i12 = 0; i12 < i10; i12++) {
                Object obj = objArr[i12];
                if (obj != AbstractC2473m.f22607a) {
                    if (i12 != i11) {
                        jArr[i11] = jArr[i12];
                        objArr[i11] = obj;
                        objArr[i12] = null;
                    }
                    i11++;
                }
            }
            this.l = false;
            this.o = i11;
        }
        return this.f22606n[i7];
    }

    public final String toString() {
        if (g() <= 0) {
            return "{}";
        }
        StringBuilder sb = new StringBuilder(this.o * 28);
        sb.append('{');
        int i7 = this.o;
        for (int i10 = 0; i10 < i7; i10++) {
            if (i10 > 0) {
                sb.append(", ");
            }
            sb.append(d(i10));
            sb.append('=');
            Object objH = h(i10);
            if (objH != sb) {
                sb.append(objH);
            } else {
                sb.append("(this Map)");
            }
        }
        sb.append('}');
        String string = sb.toString();
        A7.m.e(string, "StringBuilder(capacity).…builderAction).toString()");
        return string;
    }

    public /* synthetic */ C2472l(Object obj) {
        this(10);
    }
}
