package android.webkit;

import java.io.OutputStream;
import java.util.concurrent.Executor;

/* JADX INFO: loaded from: classes3.dex */
public abstract class TracingController {
    public abstract boolean isTracing();

    public abstract void start(TracingConfig tracingConfig);

    public abstract boolean stop(OutputStream outputStream, Executor executor);

    @Deprecated
    public TracingController() {
    }

    public static TracingController getInstance() {
        return WebViewFactory.getProvider().getTracingController();
    }
}
