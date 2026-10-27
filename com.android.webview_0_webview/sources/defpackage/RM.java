package defpackage;

import java.util.Objects;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Executor;
import org.chromium.android_webview.AwProxyController;
import org.chromium.base.ThreadUtils;
import org.chromium.support_lib_boundary.ProxyControllerBoundaryInterface;

/* JADX INFO: compiled from: chromium-SystemWebView.apk-default-410412053 */
/* JADX INFO: loaded from: classes.dex */
public class RM implements ProxyControllerBoundaryInterface {
    public final C0070Dq a;
    public final AwProxyController b;

    public RM(C0070Dq c0070Dq, AwProxyController awProxyController) {
        this.a = c0070Dq;
        this.b = awProxyController;
    }

    public final void a(RuntimeException runtimeException) {
        if (runtimeException != null) {
            Throwable cause = runtimeException.getCause();
            if (cause instanceof ExecutionException) {
                cause = cause.getCause();
            }
            if (!(cause instanceof RuntimeException)) {
                throw runtimeException;
            }
            throw ((RuntimeException) cause);
        }
    }

    @Override // org.chromium.support_lib_boundary.ProxyControllerBoundaryInterface
    public void clearProxyOverride(final Runnable runnable, final Executor executor) {
        C0826gN.a(1);
        if (true ^ ThreadUtils.j()) {
            a((RuntimeException) this.a.d(new Callable(this, runnable, executor) { // from class: QM
                public final RM a;
                public final Runnable b;
                public final Executor c;

                {
                    this.a = this;
                    this.b = runnable;
                    this.c = executor;
                }

                @Override // java.util.concurrent.Callable
                public Object call() {
                    RM rm = this.a;
                    Runnable runnable2 = this.b;
                    Executor executor2 = this.c;
                    Objects.requireNonNull(rm);
                    try {
                        rm.b.a(runnable2, executor2);
                        return null;
                    } catch (RuntimeException e) {
                        return e;
                    }
                }
            }));
        } else {
            this.b.a(runnable, executor);
        }
    }

    @Override // org.chromium.support_lib_boundary.ProxyControllerBoundaryInterface
    public void setProxyOverride(final String[][] strArr, final String[] strArr2, final Runnable runnable, final Executor executor) {
        C0826gN.a(25);
        if (!ThreadUtils.j()) {
            a((RuntimeException) this.a.d(new Callable(this, strArr, strArr2, runnable, executor) { // from class: PM
                public final RM a;
                public final String[][] b;
                public final String[] c;
                public final Runnable d;
                public final Executor e;

                {
                    this.a = this;
                    this.b = strArr;
                    this.c = strArr2;
                    this.d = runnable;
                    this.e = executor;
                }

                @Override // java.util.concurrent.Callable
                public Object call() {
                    RM rm = this.a;
                    String[][] strArr3 = this.b;
                    String[] strArr4 = this.c;
                    Runnable runnable2 = this.d;
                    Executor executor2 = this.e;
                    Objects.requireNonNull(rm);
                    try {
                        rm.b.d(strArr3, strArr4, runnable2, executor2);
                        return null;
                    } catch (RuntimeException e) {
                        return e;
                    }
                }
            }));
        } else {
            this.b.d(strArr, strArr2, runnable, executor);
        }
    }
}
