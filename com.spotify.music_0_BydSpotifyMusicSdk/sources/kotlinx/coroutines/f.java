package kotlinx.coroutines;

import java.io.Closeable;
import p.ry0;
import p.v03;
import p.w10;

/* JADX INFO: loaded from: classes.dex */
public abstract class f extends b implements Closeable {
    static {
        v03.j(b.e, "baseKey");
        v03.j(new ry0() { // from class: kotlinx.coroutines.ExecutorCoroutineDispatcher$Key$1
            @Override // p.ry0
            public final Object invoke(Object obj) {
                w10 w10Var = (w10) obj;
                if (w10Var instanceof f) {
                    return (f) w10Var;
                }
                return null;
            }
        }, "safeCast");
    }
}
