package defpackage;

import defpackage.h20;
import org.chromium.chrome.browser.webapps.b;
import org.chromium.chrome.browser.webapps.e;

/* JADX INFO: compiled from: R8$$SyntheticClass */
/* JADX INFO: loaded from: classes.dex */
public final /* synthetic */ class hs3 implements e.a {
    public final /* synthetic */ int a;
    public final /* synthetic */ q2 b;
    public final /* synthetic */ z61 c;

    public /* synthetic */ hs3(z61 z61Var, q2 q2Var, int i) {
        this.a = i;
        this.c = z61Var;
        this.b = q2Var;
    }

    @Override // org.chromium.chrome.browser.webapps.e.a
    public final void a(ds3 ds3Var, boolean z) {
        int i = this.a;
        q2 q2Var = this.b;
        z61 z61Var = this.c;
        switch (i) {
            case 0:
                is3 is3Var = (is3) z61Var;
                is3Var.getClass();
                if (!q2Var.d() && z && ds3Var != null) {
                    ds3Var.b.edit().putBoolean("show_disclosure", true).apply();
                    h20.b bVar = is3Var.f.h;
                    if ((bVar == null || bVar.c == 2) ? false : true) {
                        is3Var.e();
                    }
                }
                break;
            default:
                b bVar2 = (b) z61Var;
                bVar2.getClass();
                if (!q2Var.d()) {
                    cp3 cp3VarL = bVar2.f.L();
                    int i2 = cp3VarL.d;
                    int i3 = cp3VarL.i;
                    oj2.a0(i2, "WebApk.ShellApkVersion2.".concat(i3 != 0 ? i3 != 1 ? "Other" : "DevicePolicy" : "Browser"));
                    break;
                }
                break;
        }
    }
}
