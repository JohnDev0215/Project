package defpackage;

import java.util.HashSet;
import org.chromium.base.ThreadUtils;

/* JADX INFO: renamed from: lE, reason: case insensitive filesystem */
/* JADX INFO: compiled from: chromium-SystemWebView.apk-default-410412053 */
/* JADX INFO: loaded from: classes.dex */
public class C1096lE implements InterfaceC1488sL {
    public final C1372qE a;
    public final HashSet b = new HashSet();
    public boolean c;
    public C1153mF d;
    public final InterfaceC1317pE e;

    public C1096lE() {
        C1040kE c1040kE = new C1040kE(this);
        this.e = c1040kE;
        this.c = false;
        this.a = new C1372qE(c1040kE);
    }

    @Override // defpackage.InterfaceC1488sL
    public InterfaceC0822gJ a() {
        ThreadUtils.b();
        if (this.b.isEmpty()) {
            C1372qE c1372qE = this.a;
            if (!c1372qE.f && Is.a.registerReceiver(c1372qE.c, c1372qE.b) != null) {
                c1372qE.f = true;
            }
            if (!c1372qE.f) {
                Ts.a("BattMonitorFactory", "BatteryStatusManager failed to start.", new Object[0]);
            }
        }
        C1152mE c1152mE = new C1152mE(this);
        if (this.c) {
            c1152mE.x(this.d);
        }
        this.b.add(c1152mE);
        return c1152mE;
    }
}
