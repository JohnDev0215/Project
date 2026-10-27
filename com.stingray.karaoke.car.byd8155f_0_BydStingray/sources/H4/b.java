package H4;

import K.L0;
import android.graphics.Path;
import java.util.Arrays;
import x3.AbstractC2673a;

/* JADX INFO: loaded from: classes.dex */
public final class b implements e {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final t f2796a;

    public b(t tVar) {
        A7.m.f(tVar, "pixelShape");
        this.f2796a = tVar;
    }

    @Override // H4.u
    public final Path a(float f10, F4.c cVar) {
        Path path = new Path();
        L0 l02 = new L0(3, 3, 6);
        for (byte[] bArr : (byte[][]) l02.o) {
            Arrays.fill(bArr, (byte) 1);
        }
        E4.b bVarA0 = AbstractC2673a.a0(l02);
        for (int i7 = 0; i7 < 3; i7++) {
            for (int i10 = 0; i10 < 3; i10++) {
                float f11 = f10 / 3;
                path.addPath(this.f2796a.a(f11, w0.c.W(bVarA0, i7, i10)), i7 * f11, f11 * i10);
            }
        }
        return path;
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        return (obj instanceof b) && A7.m.a(this.f2796a, ((b) obj).f2796a);
    }

    public final int hashCode() {
        return this.f2796a.hashCode();
    }

    public final String toString() {
        return "AsPixelShape(pixelShape=" + this.f2796a + ')';
    }
}
