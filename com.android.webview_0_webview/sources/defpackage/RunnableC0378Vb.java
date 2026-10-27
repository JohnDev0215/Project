package defpackage;

import J.N;
import java.util.Objects;
import org.chromium.base.TraceEvent;

/* JADX INFO: renamed from: Vb, reason: case insensitive filesystem */
/* JADX INFO: compiled from: chromium-SystemWebView.apk-default-410412053 */
/* JADX INFO: loaded from: classes.dex */
public class RunnableC0378Vb implements Runnable {
    public final /* synthetic */ InterfaceC0499ac s;
    public final /* synthetic */ C0396Wb t;

    public RunnableC0378Vb(C0396Wb c0396Wb, InterfaceC0499ac interfaceC0499ac) {
        this.t = c0396Wb;
        this.s = interfaceC0499ac;
    }

    @Override // java.lang.Runnable
    public void run() {
        InterfaceC0499ac interfaceC0499ac = this.s;
        boolean zJ = this.t.j();
        Objects.requireNonNull((C1387qb) interfaceC0499ac);
        if (TraceEvent.u == zJ) {
            return;
        }
        TraceEvent.u = zJ;
        if (zJ) {
            N.MRN$Vid3();
        } else {
            N.MOgCa3d$();
        }
    }
}
