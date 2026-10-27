package h2;

import java.io.IOException;
import java.lang.reflect.Type;

/* JADX INFO: compiled from: CharacterCodec.java */
/* JADX INFO: loaded from: classes.dex */
public class r implements t0, g2.t {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static final r f5304a = new r();

    @Override // g2.t
    public <T> T b(f2.a aVar, Type type, Object obj) {
        Object objN = aVar.N();
        if (objN == null) {
            return null;
        }
        return (T) l2.l.n(objN);
    }

    @Override // h2.t0
    public void c(i0 i0Var, Object obj, Object obj2, Type type, int i8) throws IOException {
        d1 d1Var = i0Var.f5241k;
        Character ch = (Character) obj;
        if (ch == null) {
            d1Var.d0("");
        } else if (ch.charValue() == 0) {
            d1Var.d0("\u0000");
        } else {
            d1Var.d0(ch.toString());
        }
    }

    @Override // g2.t
    public int e() {
        return 4;
    }
}
