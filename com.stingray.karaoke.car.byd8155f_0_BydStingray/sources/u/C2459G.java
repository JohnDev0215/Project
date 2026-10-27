package u;

import java.util.Arrays;
import n7.AbstractC1996k;
import v.AbstractC2530a;

/* JADX INFO: renamed from: u.G, reason: case insensitive filesystem */
/* JADX INFO: loaded from: classes.dex */
public final class C2459G implements Cloneable {
    public /* synthetic */ boolean l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public /* synthetic */ int[] f22588m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public /* synthetic */ Object[] f22589n;
    public /* synthetic */ int o;

    public C2459G(int i7) {
        int i10;
        int i11 = 4;
        while (true) {
            i10 = 40;
            if (i11 >= 32) {
                break;
            }
            int i12 = (1 << i11) - 12;
            if (40 <= i12) {
                i10 = i12;
                break;
            }
            i11++;
        }
        int i13 = i10 / 4;
        this.f22588m = new int[i13];
        this.f22589n = new Object[i13];
    }

    public final void a(int i7, Object obj) {
        int i10 = this.o;
        if (i10 != 0 && i7 <= this.f22588m[i10 - 1]) {
            e(i7, obj);
            return;
        }
        if (this.l && i10 >= this.f22588m.length) {
            AbstractC2473m.a(this);
        }
        int i11 = this.o;
        if (i11 >= this.f22588m.length) {
            int i12 = (i11 + 1) * 4;
            int i13 = 4;
            while (true) {
                if (i13 >= 32) {
                    break;
                }
                int i14 = (1 << i13) - 12;
                if (i12 <= i14) {
                    i12 = i14;
                    break;
                }
                i13++;
            }
            int i15 = i12 / 4;
            int[] iArrCopyOf = Arrays.copyOf(this.f22588m, i15);
            A7.m.e(iArrCopyOf, "copyOf(this, newSize)");
            this.f22588m = iArrCopyOf;
            Object[] objArrCopyOf = Arrays.copyOf(this.f22589n, i15);
            A7.m.e(objArrCopyOf, "copyOf(this, newSize)");
            this.f22589n = objArrCopyOf;
        }
        this.f22588m[i11] = i7;
        this.f22589n[i11] = obj;
        this.o = i11 + 1;
    }

    /* JADX INFO: renamed from: b, reason: merged with bridge method [inline-methods] */
    public final C2459G clone() throws CloneNotSupportedException {
        Object objClone = super.clone();
        A7.m.d(objClone, "null cannot be cast to non-null type androidx.collection.SparseArrayCompat<E of androidx.collection.SparseArrayCompat>");
        C2459G c2459g = (C2459G) objClone;
        c2459g.f22588m = (int[]) this.f22588m.clone();
        c2459g.f22589n = (Object[]) this.f22589n.clone();
        return c2459g;
    }

    public final Object c(int i7) {
        Object obj;
        int iA = AbstractC2530a.a(this.o, i7, this.f22588m);
        if (iA < 0 || (obj = this.f22589n[iA]) == AbstractC2473m.c) {
            return null;
        }
        return obj;
    }

    public final int d(int i7) {
        if (this.l) {
            AbstractC2473m.a(this);
        }
        return this.f22588m[i7];
    }

    public final void e(int i7, Object obj) {
        int iA = AbstractC2530a.a(this.o, i7, this.f22588m);
        if (iA >= 0) {
            this.f22589n[iA] = obj;
            return;
        }
        int i10 = ~iA;
        int i11 = this.o;
        if (i10 < i11) {
            Object[] objArr = this.f22589n;
            if (objArr[i10] == AbstractC2473m.c) {
                this.f22588m[i10] = i7;
                objArr[i10] = obj;
                return;
            }
        }
        if (this.l && i11 >= this.f22588m.length) {
            AbstractC2473m.a(this);
            i10 = ~AbstractC2530a.a(this.o, i7, this.f22588m);
        }
        int i12 = this.o;
        if (i12 >= this.f22588m.length) {
            int i13 = (i12 + 1) * 4;
            int i14 = 4;
            while (true) {
                if (i14 >= 32) {
                    break;
                }
                int i15 = (1 << i14) - 12;
                if (i13 <= i15) {
                    i13 = i15;
                    break;
                }
                i14++;
            }
            int i16 = i13 / 4;
            int[] iArrCopyOf = Arrays.copyOf(this.f22588m, i16);
            A7.m.e(iArrCopyOf, "copyOf(this, newSize)");
            this.f22588m = iArrCopyOf;
            Object[] objArrCopyOf = Arrays.copyOf(this.f22589n, i16);
            A7.m.e(objArrCopyOf, "copyOf(this, newSize)");
            this.f22589n = objArrCopyOf;
        }
        int i17 = this.o;
        if (i17 - i10 != 0) {
            int[] iArr = this.f22588m;
            int i18 = i10 + 1;
            AbstractC1996k.C(i18, i10, i17, iArr, iArr);
            Object[] objArr2 = this.f22589n;
            AbstractC1996k.D(i18, i10, this.o, objArr2, objArr2);
        }
        this.f22588m[i10] = i7;
        this.f22589n[i10] = obj;
        this.o++;
    }

    public final int f() {
        if (this.l) {
            AbstractC2473m.a(this);
        }
        return this.o;
    }

    public final Object g(int i7) {
        if (this.l) {
            AbstractC2473m.a(this);
        }
        return this.f22589n[i7];
    }

    public final String toString() {
        if (f() <= 0) {
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
            Object objG = g(i10);
            if (objG != this) {
                sb.append(objG);
            } else {
                sb.append("(this Map)");
            }
        }
        sb.append('}');
        String string = sb.toString();
        A7.m.e(string, "buffer.toString()");
        return string;
    }
}
