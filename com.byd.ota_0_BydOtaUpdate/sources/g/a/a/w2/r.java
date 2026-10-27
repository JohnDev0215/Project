package g.a.a.w2;

import g.a.a.o0;

/* JADX INFO: loaded from: classes.dex */
public class r extends g.a.a.l {

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public o0 f4140c;

    public r(o0 o0Var) {
        this.f4140c = o0Var;
    }

    public static r g(Object obj) {
        if (obj instanceof r) {
            return (r) obj;
        }
        if (obj != null) {
            return new r(o0.t(obj));
        }
        return null;
    }

    @Override // g.a.a.l, g.a.a.e
    public g.a.a.q c() {
        return this.f4140c;
    }

    public String toString() {
        StringBuilder sb;
        int i2;
        byte[] bArrP = this.f4140c.p();
        if (bArrP.length == 1) {
            sb = new StringBuilder();
            sb.append("KeyUsage: 0x");
            i2 = bArrP[0] & 255;
        } else {
            sb = new StringBuilder();
            sb.append("KeyUsage: 0x");
            i2 = (bArrP[0] & 255) | ((bArrP[1] & 255) << 8);
        }
        sb.append(Integer.toHexString(i2));
        return sb.toString();
    }
}
