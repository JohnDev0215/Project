package b.a.a.k;

import java.io.IOException;
import java.lang.reflect.Type;

/* JADX INFO: compiled from: JSONAwareSerializer.java */
/* JADX INFO: loaded from: classes.dex */
public class e0 implements t0 {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static e0 f361a = new e0();

    @Override // b.a.a.k.t0
    public void c(i0 i0Var, Object obj, Object obj2, Type type, int i) throws IOException {
        d1 d1Var = i0Var.k;
        if (obj == null) {
            d1Var.J();
        } else {
            d1Var.write(((b.a.a.b) obj).toJSONString());
        }
    }
}
