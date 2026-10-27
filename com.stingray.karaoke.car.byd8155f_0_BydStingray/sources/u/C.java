package U;

import E0.Y;
import T.B0;
import l3.P;

/* JADX INFO: loaded from: classes.dex */
public abstract class C {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final int f8934a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final int f8935b;

    public C(int i7, int i10) {
        this.f8934a = i7;
        this.f8935b = i10;
    }

    public abstract void a(E5.z zVar, P p10, B0 b02, Y y10);

    public String b(int i7) {
        return "IntParameter(" + i7 + ')';
    }

    public String c(int i7) {
        return "ObjectParameter(" + i7 + ')';
    }

    public final String toString() {
        String strB = A7.A.f443a.b(getClass()).b();
        return strB == null ? "" : strB;
    }

    public /* synthetic */ C(int i7, int i10, int i11) {
        this((i11 & 1) != 0 ? 0 : i7, (i11 & 2) != 0 ? 0 : i10);
    }
}
