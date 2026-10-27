package h2;

import java.io.IOException;
import java.lang.reflect.Type;
import java.util.concurrent.atomic.DoubleAdder;
import java.util.concurrent.atomic.LongAdder;

/* JADX INFO: compiled from: AdderSerializer.java */
/* JADX INFO: loaded from: classes.dex */
public class b implements t0 {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static final b f5185a = new b();

    @Override // h2.t0
    public void c(i0 i0Var, Object obj, Object obj2, Type type, int i8) throws IOException {
        d1 d1Var = i0Var.f5241k;
        if (obj instanceof LongAdder) {
            d1Var.M('{', "value", ((LongAdder) obj).longValue());
            d1Var.write(125);
        } else if (obj instanceof DoubleAdder) {
            d1Var.I('{', "value", ((DoubleAdder) obj).doubleValue());
            d1Var.write(125);
        }
    }
}
