package b.a.a.k;

import java.io.IOException;
import java.lang.reflect.Type;

/* JADX INFO: compiled from: ToStringSerializer.java */
/* JADX INFO: loaded from: classes.dex */
public class f1 implements t0 {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static final f1 f366a = new f1();

    @Override // b.a.a.k.t0
    public void c(i0 i0Var, Object obj, Object obj2, Type type, int i) throws IOException {
        d1 d1Var = i0Var.k;
        if (obj == null) {
            d1Var.J();
        } else {
            d1Var.N(obj.toString());
        }
    }
}
