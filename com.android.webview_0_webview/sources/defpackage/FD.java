package defpackage;

import java.lang.ref.WeakReference;
import java.util.WeakHashMap;
import org.chromium.base.ThreadUtils;

/* JADX INFO: compiled from: chromium-SystemWebView.apk-default-410412053 */
/* JADX INFO: loaded from: classes.dex */
public class FD implements InterfaceC0964iv {
    public static boolean b;
    public final WeakHashMap a = new WeakHashMap();

    @Override // defpackage.InterfaceC0964iv
    public InterfaceC0630cv a(C1242nv c1242nv) {
        return d(c1242nv);
    }

    @Override // defpackage.InterfaceC0964iv
    public void b(C1242nv c1242nv, Runnable runnable, long j) {
        d(c1242nv).c(runnable, j);
    }

    @Override // defpackage.InterfaceC0964iv
    public boolean c(C1242nv c1242nv) {
        return d(c1242nv).b();
    }

    public InterfaceC0853gv d(C1242nv c1242nv) {
        InterfaceC0853gv interfaceC0853gv;
        synchronized (this.a) {
            WeakReference weakReference = (WeakReference) this.a.get(c1242nv);
            if (weakReference != null && (interfaceC0853gv = (InterfaceC0853gv) weakReference.get()) != null) {
                return interfaceC0853gv;
            }
            C0909hv c0909hv = new C0909hv(ThreadUtils.c(), c1242nv, false);
            this.a.put(c1242nv, new WeakReference(c0909hv));
            return c0909hv;
        }
    }
}
