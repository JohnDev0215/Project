package H4;

import K.L0;
import android.graphics.Path;
import x3.AbstractC2673a;

/* JADX INFO: loaded from: classes.dex */
public final class k implements m {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final t f2809a;

    public k(t tVar) {
        A7.m.f(tVar, "pixelShape");
        this.f2809a = tVar;
    }

    @Override // H4.u
    public final Path a(float f10, F4.c cVar) {
        E4.a aVar;
        Path path = new Path();
        E4.b bVarA0 = AbstractC2673a.a0(new L0(7, 7, 6));
        int i7 = 0;
        while (true) {
            aVar = E4.a.l;
            if (i7 >= 7) {
                break;
            }
            int i10 = 0;
            while (i10 < 7) {
                bVarA0.f(i7, i10, (i7 == 0 || i10 == 0 || i7 == 6 || i10 == 6) ? aVar : E4.a.f1745n);
                i10++;
            }
            i7++;
        }
        for (int i11 = 0; i11 < 7; i11++) {
            for (int i12 = 0; i12 < 7; i12++) {
                if (bVarA0.c(i11, i12) == aVar) {
                    float f11 = f10 / 7;
                    path.addPath(this.f2809a.a(f11, w0.c.W(bVarA0, i11, i12)), i11 * f11, f11 * i12);
                }
            }
        }
        return path;
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        return (obj instanceof k) && A7.m.a(this.f2809a, ((k) obj).f2809a);
    }

    public final int hashCode() {
        return this.f2809a.hashCode();
    }

    public final String toString() {
        return "AsPixelShape(pixelShape=" + this.f2809a + ')';
    }
}
