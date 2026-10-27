package defpackage;

import java.lang.reflect.InvocationHandler;
import org.chromium.support_lib_boundary.ServiceWorkerClientBoundaryInterface;
import org.chromium.support_lib_boundary.ServiceWorkerControllerBoundaryInterface;

/* JADX INFO: compiled from: chromium-SystemWebView.apk-default-410412053 */
/* JADX INFO: loaded from: classes.dex */
public class TM implements ServiceWorkerControllerBoundaryInterface {
    public C0735ep a;

    public TM(C0735ep c0735ep) {
        this.a = c0735ep;
    }

    @Override // org.chromium.support_lib_boundary.ServiceWorkerControllerBoundaryInterface
    public InvocationHandler getServiceWorkerWebSettings() {
        C0826gN.a(5);
        return JM.c(new UM(this.a.d));
    }

    @Override // org.chromium.support_lib_boundary.ServiceWorkerControllerBoundaryInterface
    public void setServiceWorkerClient(InvocationHandler invocationHandler) {
        C0826gN.a(27);
        this.a.a(new SM((ServiceWorkerClientBoundaryInterface) JM.a(ServiceWorkerClientBoundaryInterface.class, invocationHandler)));
    }
}
