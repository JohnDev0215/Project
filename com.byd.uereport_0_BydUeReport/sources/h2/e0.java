package h2;

import java.io.IOException;
import java.lang.reflect.Type;

/* JADX INFO: compiled from: JSONAwareSerializer.java */
/* JADX INFO: loaded from: classes.dex */
public class e0 implements t0 {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static e0 f5231a = new e0();

    @Override // h2.t0
    public void c(i0 i0Var, Object obj, Object obj2, Type type, int i8) throws IOException {
        d1 d1Var = i0Var.f5241k;
        if (obj == null) {
            d1Var.Z();
        } else {
            d1Var.write(((c2.b) obj).toJSONString());
        }
    }
}
