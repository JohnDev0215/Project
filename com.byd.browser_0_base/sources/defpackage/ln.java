package defpackage;

import android.os.RemoteException;
import org.chromium.content.browser.ChildProcessLauncherHelperImpl;

/* JADX INFO: compiled from: R8$$SyntheticClass */
/* JADX INFO: loaded from: classes.dex */
public final /* synthetic */ class ln implements Runnable {
    public final /* synthetic */ int e = 1;
    public final /* synthetic */ int f;
    public final /* synthetic */ qn g;

    public /* synthetic */ ln(int i, qn qnVar) {
        this.f = i;
        this.g = qnVar;
    }

    @Override // java.lang.Runnable
    public final void run() {
        int i = this.e;
        qn qnVar = this.g;
        int i2 = this.f;
        switch (i) {
            case 0:
                aw0 aw0Var = qnVar.k;
                if (aw0Var != null) {
                    try {
                        aw0Var.x(i2);
                    } catch (RemoteException unused) {
                        return;
                    }
                    break;
                }
                break;
            default:
                o90 o90Var = ChildProcessLauncherHelperImpl.m;
                if (i2 != 0) {
                    if (i2 != 1) {
                        if (i2 == 2 && qnVar.g()) {
                            int i3 = qnVar.y - 1;
                            qnVar.y = i3;
                            if (i3 == 0) {
                                qnVar.u.b();
                                qnVar.m();
                            }
                        }
                        break;
                    } else if (qnVar.g()) {
                        int i4 = qnVar.z - 1;
                        qnVar.z = i4;
                        if (i4 == 0) {
                            qnVar.v.b();
                            qnVar.m();
                        }
                        break;
                    }
                }
                break;
        }
    }

    public /* synthetic */ ln(qn qnVar, int i) {
        this.g = qnVar;
        this.f = i;
    }
}
