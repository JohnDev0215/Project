package j5;

import h2.AbstractC1347d;
import java.io.IOException;
import l5.AbstractC1776d;
import r5.C2297a;
import r5.C2299c;

/* JADX WARN: Failed to restore enum class, 'enum' modifier and super class removed */
/* JADX WARN: Unknown enum class pattern. Please report as an issue! */
/* JADX INFO: loaded from: classes.dex */
public abstract class x {
    public static final t l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public static final u f17030m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public static final /* synthetic */ x[] f17031n;

    static {
        t tVar = new t();
        l = tVar;
        u uVar = new u();
        f17030m = uVar;
        f17031n = new x[]{tVar, uVar, new x() { // from class: j5.v
            public static Double b(String str, C2297a c2297a) throws C2299c {
                try {
                    Double dValueOf = Double.valueOf(str);
                    if (dValueOf.isInfinite() || dValueOf.isNaN()) {
                        if (c2297a.f21857z != 1) {
                            throw new C2299c("JSON forbids NaN and infinities: " + dValueOf + "; at path " + c2297a.q(true));
                        }
                    }
                    return dValueOf;
                } catch (NumberFormatException e10) {
                    StringBuilder sbJ = AbstractC1347d.j("Cannot parse ", str, "; at path ");
                    sbJ.append(c2297a.q(true));
                    throw new A9.k(sbJ.toString(), e10);
                }
            }

            @Override // j5.x
            public final Number a(C2297a c2297a) throws IOException {
                String strO = c2297a.O();
                if (strO.indexOf(46) >= 0) {
                    return b(strO, c2297a);
                }
                try {
                    return Long.valueOf(Long.parseLong(strO));
                } catch (NumberFormatException unused) {
                    return b(strO, c2297a);
                }
            }
        }, new x() { // from class: j5.w
            @Override // j5.x
            public final Number a(C2297a c2297a) throws IOException {
                String strO = c2297a.O();
                try {
                    return AbstractC1776d.i(strO);
                } catch (NumberFormatException e10) {
                    StringBuilder sbJ = AbstractC1347d.j("Cannot parse ", strO, "; at path ");
                    sbJ.append(c2297a.q(true));
                    throw new A9.k(sbJ.toString(), e10);
                }
            }
        }};
    }

    public static x valueOf(String str) {
        return (x) Enum.valueOf(x.class, str);
    }

    public static x[] values() {
        return (x[]) f17031n.clone();
    }

    public abstract Number a(C2297a c2297a);
}
