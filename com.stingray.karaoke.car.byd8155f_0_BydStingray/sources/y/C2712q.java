package y;

import n0.C1877f;
import n0.InterfaceC1859I;
import n0.InterfaceC1889r;
import p0.C2071b;

/* JADX INFO: renamed from: y.q, reason: case insensitive filesystem */
/* JADX INFO: loaded from: classes.dex */
public final class C2712q {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public C1877f f23987a = null;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public InterfaceC1889r f23988b = null;
    public C2071b c = null;
    public InterfaceC1859I d = null;

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof C2712q)) {
            return false;
        }
        C2712q c2712q = (C2712q) obj;
        return A7.m.a(this.f23987a, c2712q.f23987a) && A7.m.a(this.f23988b, c2712q.f23988b) && A7.m.a(this.c, c2712q.c) && A7.m.a(this.d, c2712q.d);
    }

    public final int hashCode() {
        C1877f c1877f = this.f23987a;
        int iHashCode = (c1877f == null ? 0 : c1877f.hashCode()) * 31;
        InterfaceC1889r interfaceC1889r = this.f23988b;
        int iHashCode2 = (iHashCode + (interfaceC1889r == null ? 0 : interfaceC1889r.hashCode())) * 31;
        C2071b c2071b = this.c;
        int iHashCode3 = (iHashCode2 + (c2071b == null ? 0 : c2071b.hashCode())) * 31;
        InterfaceC1859I interfaceC1859I = this.d;
        return iHashCode3 + (interfaceC1859I != null ? interfaceC1859I.hashCode() : 0);
    }

    public final String toString() {
        return "BorderCache(imageBitmap=" + this.f23987a + ", canvas=" + this.f23988b + ", canvasDrawScope=" + this.c + ", borderPath=" + this.d + ')';
    }
}
