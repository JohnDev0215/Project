package android.view;

import android.graphics.Point;
import android.graphics.Rect;
import android.os.Handler;
import android.os.RemoteException;
import android.util.CloseGuard;
import android.view.IScrollCaptureClient;
import android.view.View;
import java.util.Objects;
import java.util.concurrent.atomic.AtomicBoolean;

/* JADX INFO: loaded from: classes3.dex */
public class ScrollCaptureClient extends IScrollCaptureClient.Stub {
    private static final int DEFAULT_TIMEOUT = 1000;
    private static final String TAG = "ScrollCaptureClient";
    private final CloseGuard mCloseGuard;
    private IScrollCaptureController mController;
    private final Handler mHandler;
    private final Point mPositionInWindow;
    private final Rect mScrollBounds;
    private ScrollCaptureTarget mSelectedTarget;
    private ScrollCaptureSession mSession;
    protected Surface mSurface;
    private DelayedAction mTimeoutAction;
    private int mTimeoutMillis = 1000;

    public ScrollCaptureClient(final ScrollCaptureTarget scrollCaptureTarget, IScrollCaptureController iScrollCaptureController) {
        Objects.requireNonNull(scrollCaptureTarget, "<selectedTarget> must non-null");
        Objects.requireNonNull(iScrollCaptureController, "<controller> must non-null");
        Rect rect = (Rect) Objects.requireNonNull(scrollCaptureTarget.getScrollBounds(), "target.getScrollBounds() must be non-null to construct a client");
        this.mSelectedTarget = scrollCaptureTarget;
        this.mHandler = scrollCaptureTarget.getContainingView().getHandler();
        this.mScrollBounds = new Rect(rect);
        this.mPositionInWindow = new Point(scrollCaptureTarget.getPositionInWindow());
        this.mController = iScrollCaptureController;
        CloseGuard closeGuard = new CloseGuard();
        this.mCloseGuard = closeGuard;
        closeGuard.open("close");
        scrollCaptureTarget.getContainingView().addOnAttachStateChangeListener(new View.OnAttachStateChangeListener() { // from class: android.view.ScrollCaptureClient.1
            @Override // android.view.View.OnAttachStateChangeListener
            public void onViewAttachedToWindow(View view) {
            }

            @Override // android.view.View.OnAttachStateChangeListener
            public void onViewDetachedFromWindow(View view) {
                scrollCaptureTarget.getContainingView().removeOnAttachStateChangeListener(this);
                ScrollCaptureClient.this.endCapture();
            }
        });
    }

    public void setTimeoutMillis(int i) {
        this.mTimeoutMillis = i;
    }

    public DelayedAction getTimeoutAction() {
        return this.mTimeoutAction;
    }

    private void checkConnected() {
        if (this.mSelectedTarget == null || this.mController == null) {
            throw new IllegalStateException("This client has been disconnected.");
        }
    }

    private void checkStarted() {
        if (this.mSession == null) {
            throw new IllegalStateException("Capture session has not been started!");
        }
    }

    @Override // android.view.IScrollCaptureClient
    public void startCapture(Surface surface) throws RemoteException {
        checkConnected();
        this.mSurface = surface;
        scheduleTimeout(this.mTimeoutMillis, new Runnable() { // from class: android.view.-$$Lambda$ScrollCaptureClient$Epfw54HmxmekAnuTbczAITVw7Dg
            @Override // java.lang.Runnable
            public final void run() {
                this.f$0.onStartCaptureTimeout();
            }
        });
        this.mSession = new ScrollCaptureSession(this.mSurface, this.mScrollBounds, this.mPositionInWindow, this);
        this.mHandler.post(new Runnable() { // from class: android.view.-$$Lambda$ScrollCaptureClient$Wx8q2o_h2xkFAIBvHcSi3uj-Sm0
            @Override // java.lang.Runnable
            public final void run() {
                this.f$0.lambda$startCapture$0$ScrollCaptureClient();
            }
        });
    }

    public /* synthetic */ void lambda$startCapture$0$ScrollCaptureClient() {
        this.mSelectedTarget.getCallback().onScrollCaptureStart(this.mSession, new Runnable() { // from class: android.view.-$$Lambda$ScrollCaptureClient$0-SRlg7x7JtQystMp7lzoCyGfOY
            @Override // java.lang.Runnable
            public final void run() {
                this.f$0.onStartCaptureCompleted();
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void onStartCaptureCompleted() {
        if (cancelTimeout()) {
            this.mHandler.post(new Runnable() { // from class: android.view.-$$Lambda$ScrollCaptureClient$9ZUY_FVuYBZWGLMdk-bSi5wu7zg
                @Override // java.lang.Runnable
                public final void run() {
                    this.f$0.lambda$onStartCaptureCompleted$1$ScrollCaptureClient();
                }
            });
        }
    }

    public /* synthetic */ void lambda$onStartCaptureCompleted$1$ScrollCaptureClient() {
        try {
            this.mController.onCaptureStarted();
        } catch (RemoteException unused) {
            doShutdown();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void onStartCaptureTimeout() {
        endCapture();
    }

    @Override // android.view.IScrollCaptureClient
    public void requestImage(final Rect rect) {
        checkConnected();
        checkStarted();
        scheduleTimeout(this.mTimeoutMillis, new Runnable() { // from class: android.view.-$$Lambda$ScrollCaptureClient$cg6Nc5a-WzAiBUwgRjGwyrOr1MQ
            @Override // java.lang.Runnable
            public final void run() {
                this.f$0.onRequestImageTimeout();
            }
        });
        this.mHandler.post(new Runnable() { // from class: android.view.-$$Lambda$ScrollCaptureClient$rf3BkY5f-J7V42O_dMJLA1rnVkM
            @Override // java.lang.Runnable
            public final void run() {
                this.f$0.lambda$requestImage$2$ScrollCaptureClient(rect);
            }
        });
    }

    public /* synthetic */ void lambda$requestImage$2$ScrollCaptureClient(Rect rect) {
        this.mSelectedTarget.getCallback().onScrollCaptureImageRequest(this.mSession, new Rect(rect));
    }

    void onRequestImageCompleted(final long j, Rect rect) {
        final Rect rect2 = new Rect(rect);
        if (cancelTimeout()) {
            this.mHandler.post(new Runnable() { // from class: android.view.-$$Lambda$ScrollCaptureClient$jB-gZzHC6CVXS20ua5gOXB2VBpE
                @Override // java.lang.Runnable
                public final void run() {
                    this.f$0.lambda$onRequestImageCompleted$3$ScrollCaptureClient(j, rect2);
                }
            });
        }
    }

    public /* synthetic */ void lambda$onRequestImageCompleted$3$ScrollCaptureClient(long j, Rect rect) {
        try {
            this.mController.onCaptureBufferSent(j, rect);
        } catch (RemoteException unused) {
            doShutdown();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void onRequestImageTimeout() {
        endCapture();
    }

    @Override // android.view.IScrollCaptureClient
    public void endCapture() {
        if (isStarted()) {
            scheduleTimeout(this.mTimeoutMillis, new Runnable() { // from class: android.view.-$$Lambda$ScrollCaptureClient$ViSKCOfqcLht-jEVL0NThLc_K9A
                @Override // java.lang.Runnable
                public final void run() {
                    this.f$0.onEndCaptureTimeout();
                }
            });
            this.mHandler.post(new Runnable() { // from class: android.view.-$$Lambda$ScrollCaptureClient$OoPpYradNVuARe3t3TP2xK3X9iI
                @Override // java.lang.Runnable
                public final void run() {
                    this.f$0.lambda$endCapture$4$ScrollCaptureClient();
                }
            });
        } else {
            disconnect();
        }
    }

    public /* synthetic */ void lambda$endCapture$4$ScrollCaptureClient() {
        this.mSelectedTarget.getCallback().onScrollCaptureEnd(new Runnable() { // from class: android.view.-$$Lambda$ScrollCaptureClient$GYYbruHV0KM9iR0K-qw9FSo1ra8
            @Override // java.lang.Runnable
            public final void run() {
                this.f$0.onEndCaptureCompleted();
            }
        });
    }

    private boolean isStarted() {
        return (this.mController == null || this.mSelectedTarget == null) ? false : true;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void onEndCaptureCompleted() {
        if (cancelTimeout()) {
            doShutdown();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void onEndCaptureTimeout() {
        doShutdown();
    }

    private void doShutdown() {
        try {
            if (this.mController != null) {
                this.mController.onConnectionClosed();
            }
        } catch (RemoteException unused) {
        } catch (Throwable th) {
            disconnect();
            throw th;
        }
        disconnect();
    }

    public void disconnect() {
        ScrollCaptureSession scrollCaptureSession = this.mSession;
        if (scrollCaptureSession != null) {
            scrollCaptureSession.disconnect();
            this.mSession = null;
        }
        this.mSelectedTarget = null;
        this.mController = null;
    }

    public String toString() {
        return "ScrollCaptureClient{, session=" + this.mSession + ", selectedTarget=" + this.mSelectedTarget + ", clientCallbacks=" + this.mController + "}";
    }

    private boolean cancelTimeout() {
        DelayedAction delayedAction = this.mTimeoutAction;
        if (delayedAction != null) {
            return delayedAction.cancel();
        }
        return false;
    }

    private void scheduleTimeout(long j, Runnable runnable) {
        DelayedAction delayedAction = this.mTimeoutAction;
        if (delayedAction != null) {
            delayedAction.cancel();
        }
        this.mTimeoutAction = new DelayedAction(this.mHandler, j, runnable);
    }

    public static class DelayedAction {
        private final Runnable mAction;
        private final Handler mHandler;
        private final AtomicBoolean mCompleted = new AtomicBoolean();
        private final Object mToken = new Object();

        public DelayedAction(Handler handler, long j, Runnable runnable) {
            this.mHandler = handler;
            this.mAction = runnable;
            handler.postDelayed(new Runnable() { // from class: android.view.-$$Lambda$ScrollCaptureClient$DelayedAction$dG-6ZyjvsGGEg-j2UjRHC5kFNv8
                @Override // java.lang.Runnable
                public final void run() {
                    this.f$0.onTimeout();
                }
            }, this.mToken, j);
        }

        /* JADX INFO: Access modifiers changed from: private */
        public boolean onTimeout() {
            if (!this.mCompleted.compareAndSet(false, true)) {
                return false;
            }
            this.mAction.run();
            return true;
        }

        public boolean timeoutNow() {
            return onTimeout();
        }

        public boolean cancel() {
            if (!this.mCompleted.compareAndSet(false, true)) {
                return false;
            }
            this.mHandler.removeCallbacksAndMessages(this.mToken);
            return true;
        }
    }
}
