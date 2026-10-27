package U;

import E0.Y;
import T.B0;
import T.C0735d;
import java.util.Arrays;
import l3.P;

/* JADX INFO: loaded from: classes.dex */
public final class D extends com.bumptech.glide.d {

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public int f8936e;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public int f8938g;

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public int f8940i;

    /* JADX INFO: renamed from: j, reason: collision with root package name */
    public int f8941j;

    /* JADX INFO: renamed from: k, reason: collision with root package name */
    public int f8942k;
    public C[] d = new C[16];

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public int[] f8937f = new int[16];

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    public Object[] f8939h = new Object[16];

    public static final int P(D d, int i7) {
        d.getClass();
        if (i7 == 0) {
            return 0;
        }
        return (-1) >>> (32 - i7);
    }

    public final void Q() {
        this.f8936e = 0;
        this.f8938g = 0;
        Arrays.fill(this.f8939h, 0, this.f8940i, (Object) null);
        this.f8940i = 0;
    }

    public final void R(P p10, B0 b02, Y y10) {
        D d;
        int i7;
        if (T()) {
            E5.z zVar = new E5.z(this);
            do {
                d = (D) zVar.f1842e;
                C c = d.d[zVar.f1841b];
                A7.m.c(c);
                c.a(zVar, p10, b02, y10);
                int i10 = zVar.f1841b;
                if (i10 >= d.f8936e) {
                    break;
                }
                C c10 = d.d[i10];
                A7.m.c(c10);
                zVar.c += c10.f8934a;
                zVar.d += c10.f8935b;
                i7 = zVar.f1841b + 1;
                zVar.f1841b = i7;
            } while (i7 < d.f8936e);
        }
        Q();
    }

    public final boolean S() {
        return this.f8936e == 0;
    }

    public final boolean T() {
        return this.f8936e != 0;
    }

    public final C U() {
        C c = this.d[this.f8936e - 1];
        A7.m.c(c);
        return c;
    }

    public final void V(C c) {
        int i7 = c.f8934a;
        int i10 = c.f8935b;
        if (i7 == 0 && i10 == 0) {
            W(c);
            return;
        }
        C0735d.X("Cannot push " + c + " without arguments because it expects " + i7 + " ints and " + i10 + " objects.");
        throw null;
    }

    public final void W(C c) {
        this.f8941j = 0;
        this.f8942k = 0;
        int i7 = this.f8936e;
        C[] cArr = this.d;
        if (i7 == cArr.length) {
            Object[] objArrCopyOf = Arrays.copyOf(cArr, i7 + (i7 > 1024 ? 1024 : i7));
            A7.m.e(objArrCopyOf, "copyOf(this, newSize)");
            this.d = (C[]) objArrCopyOf;
        }
        int i10 = this.f8938g + c.f8934a;
        int[] iArr = this.f8937f;
        int length = iArr.length;
        if (i10 > length) {
            int i11 = length + (length > 1024 ? 1024 : length);
            if (i11 >= i10) {
                i10 = i11;
            }
            int[] iArrCopyOf = Arrays.copyOf(iArr, i10);
            A7.m.e(iArrCopyOf, "copyOf(this, newSize)");
            this.f8937f = iArrCopyOf;
        }
        int i12 = this.f8940i;
        int i13 = c.f8935b;
        int i14 = i12 + i13;
        Object[] objArr = this.f8939h;
        int length2 = objArr.length;
        if (i14 > length2) {
            int i15 = length2 + (length2 <= 1024 ? length2 : 1024);
            if (i15 >= i14) {
                i14 = i15;
            }
            Object[] objArrCopyOf2 = Arrays.copyOf(objArr, i14);
            A7.m.e(objArrCopyOf2, "copyOf(this, newSize)");
            this.f8939h = objArrCopyOf2;
        }
        C[] cArr2 = this.d;
        int i16 = this.f8936e;
        this.f8936e = i16 + 1;
        cArr2[i16] = c;
        this.f8938g += c.f8934a;
        this.f8940i += i13;
    }
}
