package Z3;

import A9.k;
import E5.s;
import io.sentry.internal.debugmeta.c;
import java.io.Closeable;
import java.util.Arrays;

/* JADX INFO: loaded from: classes.dex */
public abstract class a implements Closeable {

    /* JADX INFO: renamed from: p, reason: collision with root package name */
    public static final String[] f10885p = new String[128];
    public int l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public int[] f10886m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public String[] f10887n;
    public int[] o;

    static {
        for (int i7 = 0; i7 <= 31; i7++) {
            f10885p[i7] = String.format("\\u%04x", Integer.valueOf(i7));
        }
        String[] strArr = f10885p;
        strArr[34] = "\\\"";
        strArr[92] = "\\\\";
        strArr[9] = "\\t";
        strArr[8] = "\\b";
        strArr[10] = "\\n";
        strArr[13] = "\\r";
        strArr[12] = "\\f";
    }

    public final void C(int i7) {
        int i10 = this.l;
        int[] iArr = this.f10886m;
        if (i10 == iArr.length) {
            if (i10 == 256) {
                throw new k("Nesting too deep at " + k());
            }
            this.f10886m = Arrays.copyOf(iArr, iArr.length * 2);
            String[] strArr = this.f10887n;
            this.f10887n = (String[]) Arrays.copyOf(strArr, strArr.length * 2);
            int[] iArr2 = this.o;
            this.o = Arrays.copyOf(iArr2, iArr2.length * 2);
        }
        int[] iArr3 = this.f10886m;
        int i11 = this.l;
        this.l = i11 + 1;
        iArr3[i11] = i7;
    }

    public abstract int F(c cVar);

    public abstract void G();

    public abstract void I();

    public final void K(String str) throws s {
        StringBuilder sbL = A7.k.l(str, " at path ");
        sbL.append(k());
        throw new s(sbL.toString());
    }

    public abstract void b();

    public abstract void g();

    public abstract void i();

    public abstract void j();

    public final String k() {
        int i7 = this.l;
        int[] iArr = this.f10886m;
        String[] strArr = this.f10887n;
        int[] iArr2 = this.o;
        StringBuilder sb = new StringBuilder("$");
        for (int i10 = 0; i10 < i7; i10++) {
            int i11 = iArr[i10];
            if (i11 == 1 || i11 == 2) {
                sb.append('[');
                sb.append(iArr2[i10]);
                sb.append(']');
            } else if (i11 == 3 || i11 == 4 || i11 == 5) {
                sb.append('.');
                String str = strArr[i10];
                if (str != null) {
                    sb.append(str);
                }
            }
        }
        return sb.toString();
    }

    public abstract boolean m();

    public abstract boolean o();

    public abstract double q();

    public abstract int v();

    public abstract String y();

    public abstract int z();
}
