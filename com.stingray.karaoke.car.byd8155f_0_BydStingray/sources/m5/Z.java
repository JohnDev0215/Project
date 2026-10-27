package m5;

import java.io.IOException;
import java.util.concurrent.atomic.AtomicInteger;
import r5.C2297a;
import r5.C2298b;

/* JADX INFO: loaded from: classes.dex */
public class Z extends j5.y {
    @Override // j5.y
    public final Object a(C2297a c2297a) {
        try {
            return new AtomicInteger(c2297a.G());
        } catch (NumberFormatException e10) {
            throw new j5.p(e10);
        }
    }

    @Override // j5.y
    public final void b(C2298b c2298b, Object obj) throws IOException {
        c2298b.I(((AtomicInteger) obj).get());
    }
}
