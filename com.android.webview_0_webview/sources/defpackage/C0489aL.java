package defpackage;

import org.chromium.base.ApplicationStatus;

/* JADX INFO: renamed from: aL, reason: case insensitive filesystem */
/* JADX INFO: compiled from: chromium-SystemWebView.apk-default-410412053 */
/* JADX INFO: loaded from: classes.dex */
public class C0489aL extends RK implements InterfaceC1843ys {
    public boolean b;

    @Override // defpackage.RK
    public void c() {
        if (this.b) {
            return;
        }
        ApplicationStatus.e.g(this);
        this.b = true;
    }

    @Override // defpackage.RK
    public void d(TK tk) {
        this.a = tk;
        ApplicationStatus.e.f(this);
        int stateForApplication = ApplicationStatus.getStateForApplication();
        if (stateForApplication == 1) {
            e();
        } else if (stateForApplication == 2) {
            this.a.g();
        }
    }
}
