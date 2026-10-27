package b.a.a.k;

import java.io.IOException;
import java.lang.reflect.Type;

/* JADX INFO: compiled from: CharacterCodec.java */
/* JADX INFO: loaded from: classes.dex */
public class r implements t0, b.a.a.j.j.t {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static final r f401a = new r();

    @Override // b.a.a.j.j.t
    public <T> T b(b.a.a.j.a aVar, Type type, Object obj) {
        Object objZ = aVar.z();
        if (objZ == null) {
            return null;
        }
        return (T) b.a.a.n.l.n(objZ);
    }

    @Override // b.a.a.k.t0
    public void c(i0 i0Var, Object obj, Object obj2, Type type, int i) throws IOException {
        d1 d1Var = i0Var.k;
        Character ch = (Character) obj;
        if (ch == null) {
            d1Var.N("");
        } else if (ch.charValue() == 0) {
            d1Var.N("\u0000");
        } else {
            d1Var.N(ch.toString());
        }
    }

    @Override // b.a.a.j.j.t
    public int e() {
        return 4;
    }
}
