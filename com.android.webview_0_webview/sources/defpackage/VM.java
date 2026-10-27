package defpackage;

import java.io.OutputStream;
import java.util.Collection;
import java.util.concurrent.Executor;
import org.chromium.support_lib_boundary.TracingControllerBoundaryInterface;

/* JADX INFO: compiled from: chromium-SystemWebView.apk-default-410412053 */
/* JADX INFO: loaded from: classes.dex */
public class VM implements TracingControllerBoundaryInterface {
    public final K8 a;

    public VM(K8 k8) {
        this.a = k8;
    }

    @Override // org.chromium.support_lib_boundary.TracingControllerBoundaryInterface
    public boolean isTracing() {
        C0826gN.a(29);
        return this.a.c();
    }

    @Override // org.chromium.support_lib_boundary.TracingControllerBoundaryInterface
    public void start(int i, Collection collection, int i2) {
        C0826gN.a(30);
        this.a.d(i, collection, i2);
    }

    @Override // org.chromium.support_lib_boundary.TracingControllerBoundaryInterface
    public boolean stop(OutputStream outputStream, Executor executor) {
        C0826gN.a(31);
        return this.a.f(outputStream, executor);
    }
}
