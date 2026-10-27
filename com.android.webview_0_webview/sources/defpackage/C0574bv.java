package defpackage;

import android.view.Choreographer;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.Callable;
import org.chromium.base.ThreadUtils;

/* JADX INFO: renamed from: bv, reason: case insensitive filesystem */
/* JADX INFO: compiled from: chromium-SystemWebView.apk-default-410412053 */
/* JADX INFO: loaded from: classes.dex */
public class C0574bv implements InterfaceC0964iv {
    public final Map a = new HashMap();

    @Override // defpackage.InterfaceC0964iv
    public InterfaceC0630cv a(C1242nv c1242nv) {
        return c1242nv.f ? d() : new C0685dv(c1242nv);
    }

    @Override // defpackage.InterfaceC0964iv
    public synchronized void b(C1242nv c1242nv, Runnable runnable, long j) {
        if (c1242nv.d != 0) {
            e(c1242nv).c(runnable, j);
        } else {
            InterfaceC1020jv interfaceC1020jvE = (InterfaceC1020jv) this.a.get(c1242nv);
            if (interfaceC1020jvE == null) {
                interfaceC1020jvE = e(c1242nv);
                this.a.put(c1242nv, interfaceC1020jvE);
            }
            interfaceC1020jvE.c(runnable, j);
        }
    }

    @Override // defpackage.InterfaceC0964iv
    public boolean c(C1242nv c1242nv) {
        return false;
    }

    public final synchronized Wu d() {
        return (Wu) ThreadUtils.i(new Callable() { // from class: av
            @Override // java.util.concurrent.Callable
            public Object call() {
                return new Wu(Choreographer.getInstance());
            }
        });
    }

    public InterfaceC1020jv e(C1242nv c1242nv) {
        if (c1242nv.f) {
            return d();
        }
        C1187mv c1187mv = new C1187mv(c1242nv, "TaskRunnerImpl", 0);
        C1187mv.d();
        return c1187mv;
    }
}
