package e1;

import android.window.OnBackInvokedCallback;
import z7.InterfaceC2772a;

/* JADX INFO: loaded from: classes.dex */
public final /* synthetic */ class n implements OnBackInvokedCallback {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final /* synthetic */ int f13799a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final /* synthetic */ Object f13800b;

    public /* synthetic */ n(Object obj, int i7) {
        this.f13799a = i7;
        this.f13800b = obj;
    }

    public final void onBackInvoked() {
        int i7 = this.f13799a;
        Object obj = this.f13800b;
        switch (i7) {
            case 0:
                InterfaceC2772a interfaceC2772a = (InterfaceC2772a) obj;
                if (interfaceC2772a != null) {
                    interfaceC2772a.a();
                }
                break;
            case 1:
                InterfaceC2772a interfaceC2772a2 = (InterfaceC2772a) obj;
                A7.m.f(interfaceC2772a2, "$onBackInvoked");
                interfaceC2772a2.a();
                break;
            default:
                ((Runnable) obj).run();
                break;
        }
    }
}
