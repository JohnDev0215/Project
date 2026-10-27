package defpackage;

import android.webkit.TracingConfig;
import android.webkit.TracingController;
import java.io.OutputStream;
import java.util.concurrent.Executor;

/* JADX INFO: compiled from: chromium-SystemWebView.apk-default-410412053 */
/* JADX INFO: loaded from: classes.dex */
public class W8 extends TracingController {
    public final K8 a;

    public W8(K8 k8) {
        this.a = k8;
    }

    @Override // android.webkit.TracingController
    public boolean isTracing() {
        return this.a.c();
    }

    @Override // android.webkit.TracingController
    public void start(TracingConfig tracingConfig) {
        if (tracingConfig == null) {
            throw new IllegalArgumentException("tracingConfig cannot be null");
        }
        this.a.d(tracingConfig.getPredefinedCategories(), tracingConfig.getCustomIncludedCategories(), tracingConfig.getTracingMode());
    }

    @Override // android.webkit.TracingController
    public boolean stop(OutputStream outputStream, Executor executor) {
        return this.a.f(outputStream, executor);
    }
}
