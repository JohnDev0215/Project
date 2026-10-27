package E5;

import io.sentry.C1489n1;
import java.io.Closeable;
import java.util.Arrays;

/* JADX INFO: loaded from: classes.dex */
public abstract class u implements Closeable {
    public int l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public int[] f1822m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public String[] f1823n;
    public int[] o;

    public final void C(int i7) {
        int i10 = this.l;
        int[] iArr = this.f1822m;
        if (i10 == iArr.length) {
            if (i10 == 256) {
                throw new A9.k("Nesting too deep at " + k());
            }
            this.f1822m = Arrays.copyOf(iArr, iArr.length * 2);
            String[] strArr = this.f1823n;
            this.f1823n = (String[]) Arrays.copyOf(strArr, strArr.length * 2);
            int[] iArr2 = this.o;
            this.o = Arrays.copyOf(iArr2, iArr2.length * 2);
        }
        int[] iArr3 = this.f1822m;
        int i11 = this.l;
        this.l = i11 + 1;
        iArr3[i11] = i7;
    }

    public abstract int F(C1489n1 c1489n1);

    public abstract int G(C1489n1 c1489n1);

    public abstract void I();

    public abstract void K();

    public final void L(String str) throws s {
        StringBuilder sbL = A7.k.l(str, " at path ");
        sbL.append(k());
        throw new s(sbL.toString());
    }

    public abstract void b();

    public abstract void g();

    public abstract void i();

    public abstract void j();

    public final String k() {
        return M.d(this.l, this.f1822m, this.f1823n, this.o);
    }

    public abstract boolean m();

    public abstract double o();

    public abstract int q();

    public abstract void v();

    public abstract String y();

    public abstract int z();
}
