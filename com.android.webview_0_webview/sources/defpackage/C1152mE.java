package defpackage;

import java.util.Objects;
import org.chromium.base.ThreadUtils;

/* JADX INFO: renamed from: mE, reason: case insensitive filesystem */
/* JADX INFO: compiled from: chromium-SystemWebView.apk-default-410412053 */
/* JADX INFO: loaded from: classes.dex */
public class C1152mE implements InterfaceC0651dF {
    public final C1096lE s;
    public InterfaceC0595cF t;
    public C1153mF u;
    public boolean v = false;
    public boolean w = true;

    public C1152mE(C1096lE c1096lE) {
        this.s = c1096lE;
    }

    public final void A() {
        if (this.w) {
            C1096lE c1096lE = this.s;
            Objects.requireNonNull(c1096lE);
            ThreadUtils.b();
            c1096lE.b.remove(this);
            if (c1096lE.b.isEmpty()) {
                C1372qE c1372qE = c1096lE.a;
                if (c1372qE.f) {
                    Is.a.unregisterReceiver(c1372qE.c);
                    c1372qE.f = false;
                }
                c1096lE.c = false;
            }
            this.w = false;
        }
    }

    @Override // defpackage.InterfaceC0651dF
    public void E(InterfaceC0595cF interfaceC0595cF) {
        if (this.t != null) {
            Ts.a("BatteryMonitorImpl", "Overlapped call to queryNextStatus!", new Object[0]);
            A();
            return;
        }
        this.t = interfaceC0595cF;
        if (this.v) {
            ((C0930iF) interfaceC0595cF).a(this.u);
            this.t = null;
            this.v = false;
        }
    }

    @Override // defpackage.LI
    public void I(WJ wj) {
        A();
    }

    @Override // defpackage.InterfaceC0822gJ, java.io.Closeable, java.lang.AutoCloseable
    public void close() {
        A();
    }

    public void x(C1153mF c1153mF) {
        this.u = c1153mF;
        this.v = true;
        InterfaceC0595cF interfaceC0595cF = this.t;
        if (interfaceC0595cF != null) {
            ((C0930iF) interfaceC0595cF).a(c1153mF);
            this.t = null;
            this.v = false;
        }
    }
}
