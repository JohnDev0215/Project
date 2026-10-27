package N6;

import android.os.Looper;
import g1.AbstractC1274a;
import java.util.HashSet;
import java.util.Iterator;

/* JADX INFO: loaded from: classes.dex */
public final class h {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final HashSet f4785a = new HashSet();

    public final void a() {
        if (AbstractC1274a.f14702a == null) {
            AbstractC1274a.f14702a = Looper.getMainLooper().getThread();
        }
        if (Thread.currentThread() != AbstractC1274a.f14702a) {
            throw new IllegalStateException("Must be called on the Main thread.");
        }
        Iterator it = this.f4785a.iterator();
        if (it.hasNext()) {
            it.next().getClass();
            throw new ClassCastException();
        }
    }
}
