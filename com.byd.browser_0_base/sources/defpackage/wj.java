package defpackage;

import java.lang.ref.WeakReference;
import java.util.WeakHashMap;
import org.chromium.base.ThreadUtils;

/* JADX INFO: compiled from: BrowserTaskExecutor.java */
/* JADX INFO: loaded from: classes3.dex */
public final class wj implements y63 {
    public static boolean b;
    public final WeakHashMap<d73, WeakReference<mn2>> a = new WeakHashMap<>();

    @Override // defpackage.y63
    public final b73 a(d73 d73Var) {
        return e(d73Var);
    }

    @Override // defpackage.y63
    public final oi2 b(d73 d73Var) {
        return e(d73Var);
    }

    @Override // defpackage.y63
    public final void c(d73 d73Var, Runnable runnable, long j) {
        e(d73Var).a(runnable, j);
    }

    @Override // defpackage.y63
    public final boolean d(d73 d73Var) {
        return e(d73Var).c();
    }

    public final mn2 e(d73 d73Var) {
        mn2 mn2Var;
        synchronized (this.a) {
            WeakReference<mn2> weakReference = this.a.get(d73Var);
            if (weakReference != null && (mn2Var = weakReference.get()) != null) {
                return mn2Var;
            }
            nn2 nn2Var = new nn2(ThreadUtils.c(), d73Var);
            this.a.put(d73Var, new WeakReference<>(nn2Var));
            return nn2Var;
        }
    }
}
