package b.a.a.k;

import java.io.IOException;
import java.lang.reflect.Type;
import java.util.concurrent.atomic.DoubleAdder;
import java.util.concurrent.atomic.LongAdder;

/* JADX INFO: compiled from: AdderSerializer.java */
/* JADX INFO: loaded from: classes.dex */
public class b implements t0 {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static final b f335a = new b();

    @Override // b.a.a.k.t0
    public void c(i0 i0Var, Object obj, Object obj2, Type type, int i) throws IOException {
        d1 d1Var = i0Var.k;
        if (obj instanceof LongAdder) {
            d1Var.y('{', "value", ((LongAdder) obj).longValue());
            d1Var.write(125);
        } else if (obj instanceof DoubleAdder) {
            d1Var.w('{', "value", ((DoubleAdder) obj).doubleValue());
            d1Var.write(125);
        }
    }
}
