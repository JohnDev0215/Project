package b.a.a.k;

import java.io.IOException;
import java.lang.reflect.Type;

/* JADX INFO: compiled from: JSONSerializableSerializer.java */
/* JADX INFO: loaded from: classes.dex */
public class h0 implements t0 {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static h0 f368a = new h0();

    @Override // b.a.a.k.t0
    public void c(i0 i0Var, Object obj, Object obj2, Type type, int i) throws IOException {
        g0 g0Var = (g0) obj;
        if (g0Var == null) {
            i0Var.J();
        } else {
            g0Var.a(i0Var, obj2, type, i);
        }
    }
}
