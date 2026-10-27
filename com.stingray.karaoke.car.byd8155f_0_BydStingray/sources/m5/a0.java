package m5;

import java.io.IOException;
import java.util.concurrent.atomic.AtomicBoolean;
import r5.C2297a;
import r5.C2298b;

/* JADX INFO: loaded from: classes.dex */
public class a0 extends j5.y {
    @Override // j5.y
    public final Object a(C2297a c2297a) {
        return new AtomicBoolean(c2297a.C());
    }

    @Override // j5.y
    public final void b(C2298b c2298b, Object obj) throws IOException {
        c2298b.M(((AtomicBoolean) obj).get());
    }
}
