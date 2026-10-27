package org.chromium.content.browser.input;

import android.os.Handler;
import android.os.HandlerThread;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import org.chromium.content.browser.input.ChromiumBaseInputConnection;

/* JADX INFO: loaded from: classes3.dex */
public class ThreadedInputConnectionFactory implements ChromiumBaseInputConnection.Factory {
    private static final int CHECK_REGISTER_RETRY = 1;
    private static final boolean DEBUG_LOGS = false;
    private static final String TAG = "cr_Ime";
    private CheckInvalidator mCheckInvalidator;
    private final InputMethodManagerWrapper mInputMethodManagerWrapper;
    private ThreadedInputConnectionProxyView mProxyView;
    private boolean mReentrantTriggering;
    private ThreadedInputConnection mThreadedInputConnection;
    private final Handler mHandler = createHandler();
    private final InputMethodUma mInputMethodUma = createInputMethodUma();

    private static class CheckInvalidator {
        private boolean mInvalid;

        private CheckInvalidator() {
        }

        public void invalidate() {
            ImeUtils.checkOnUiThread();
            this.mInvalid = true;
        }

        public boolean isInvalid() {
            ImeUtils.checkOnUiThread();
            return this.mInvalid;
        }
    }

    ThreadedInputConnectionFactory(InputMethodManagerWrapper inputMethodManagerWrapper) {
        this.mInputMethodManagerWrapper = inputMethodManagerWrapper;
    }

    protected Handler createHandler() {
        HandlerThread handlerThread = new HandlerThread("InputConnectionHandlerThread", 5);
        handlerThread.start();
        return new Handler(handlerThread.getLooper());
    }

    protected ThreadedInputConnectionProxyView createProxyView(Handler handler, View view) {
        return new ThreadedInputConnectionProxyView(view.getContext(), handler, view);
    }

    protected InputMethodUma createInputMethodUma() {
        return new InputMethodUma();
    }

    private boolean shouldTriggerDelayedOnCreateInputConnection() {
        for (StackTraceElement stackTraceElement : Thread.currentThread().getStackTrace()) {
            String className = stackTraceElement.getClassName();
            if (className != null && (className.contains(ThreadedInputConnectionProxyView.class.getName()) || className.contains("TestInputMethodManagerWrapper"))) {
                return false;
            }
        }
        return true;
    }

    @Override // org.chromium.content.browser.input.ChromiumBaseInputConnection.Factory
    public ThreadedInputConnection initializeAndGet(View view, ImeAdapter imeAdapter, int i, int i2, int i3, int i4, EditorInfo editorInfo) {
        ImeUtils.checkOnUiThread();
        ImeUtils.computeEditorInfo(i, i2, i3, i4, editorInfo);
        CheckInvalidator checkInvalidator = this.mCheckInvalidator;
        if (checkInvalidator != null) {
            checkInvalidator.invalidate();
        }
        if (shouldTriggerDelayedOnCreateInputConnection()) {
            triggerDelayedOnCreateInputConnection(view);
            return null;
        }
        ThreadedInputConnection threadedInputConnection = this.mThreadedInputConnection;
        if (threadedInputConnection == null) {
            this.mThreadedInputConnection = new ThreadedInputConnection(view, imeAdapter, this.mHandler);
        } else {
            threadedInputConnection.resetOnUiThread();
        }
        return this.mThreadedInputConnection;
    }

    private void triggerDelayedOnCreateInputConnection(final View view) {
        if (!this.mReentrantTriggering && view.hasFocus() && view.hasWindowFocus()) {
            this.mCheckInvalidator = new CheckInvalidator();
            if (this.mProxyView == null) {
                this.mProxyView = createProxyView(this.mHandler, view);
            }
            this.mReentrantTriggering = true;
            this.mProxyView.requestFocus();
            this.mReentrantTriggering = false;
            view.getHandler().post(new Runnable() { // from class: org.chromium.content.browser.input.ThreadedInputConnectionFactory.1
                @Override // java.lang.Runnable
                public void run() {
                    if (ThreadedInputConnectionFactory.this.mCheckInvalidator.isInvalid()) {
                        return;
                    }
                    ThreadedInputConnectionFactory.this.mProxyView.onWindowFocusChanged(true);
                    ThreadedInputConnectionFactory.this.mInputMethodManagerWrapper.isActive(view);
                    ThreadedInputConnectionFactory.this.mHandler.post(new Runnable() { // from class: org.chromium.content.browser.input.ThreadedInputConnectionFactory.1.1
                        @Override // java.lang.Runnable
                        public void run() {
                            ThreadedInputConnectionFactory.this.postCheckRegisterResultOnUiThread(view, ThreadedInputConnectionFactory.this.mCheckInvalidator, 1);
                        }
                    });
                }
            });
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void postCheckRegisterResultOnUiThread(final View view, final CheckInvalidator checkInvalidator, final int i) {
        Handler handler = view.getHandler();
        if (handler == null) {
            return;
        }
        handler.post(new Runnable() { // from class: org.chromium.content.browser.input.ThreadedInputConnectionFactory.2
            @Override // java.lang.Runnable
            public void run() {
                ThreadedInputConnectionFactory.this.checkRegisterResult(view, checkInvalidator, i);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void checkRegisterResult(View view, CheckInvalidator checkInvalidator, int i) {
        if (this.mInputMethodManagerWrapper.isActive(this.mProxyView)) {
            this.mInputMethodUma.recordProxyViewSuccess();
        } else {
            if (checkInvalidator.isInvalid()) {
                return;
            }
            if (i > 0) {
                postCheckRegisterResultOnUiThread(view, checkInvalidator, i - 1);
            } else {
                onRegisterProxyViewFailed();
            }
        }
    }

    protected void onRegisterProxyViewFailed() {
        this.mInputMethodUma.recordProxyViewFailure();
        throw new AssertionError("Failed to register proxy view");
    }

    @Override // org.chromium.content.browser.input.ChromiumBaseInputConnection.Factory
    public Handler getHandler() {
        return this.mHandler;
    }

    @Override // org.chromium.content.browser.input.ChromiumBaseInputConnection.Factory
    public void onWindowFocusChanged(boolean z) {
        CheckInvalidator checkInvalidator;
        if (!z && (checkInvalidator = this.mCheckInvalidator) != null) {
            checkInvalidator.invalidate();
        }
        ThreadedInputConnectionProxyView threadedInputConnectionProxyView = this.mProxyView;
        if (threadedInputConnectionProxyView != null) {
            threadedInputConnectionProxyView.onOriginalViewWindowFocusChanged(z);
        }
    }

    @Override // org.chromium.content.browser.input.ChromiumBaseInputConnection.Factory
    public void onViewFocusChanged(boolean z) {
        CheckInvalidator checkInvalidator;
        if (!z && (checkInvalidator = this.mCheckInvalidator) != null) {
            checkInvalidator.invalidate();
        }
        ThreadedInputConnectionProxyView threadedInputConnectionProxyView = this.mProxyView;
        if (threadedInputConnectionProxyView != null) {
            threadedInputConnectionProxyView.onOriginalViewFocusChanged(z);
        }
    }

    @Override // org.chromium.content.browser.input.ChromiumBaseInputConnection.Factory
    public void onViewAttachedToWindow() {
        ThreadedInputConnectionProxyView threadedInputConnectionProxyView = this.mProxyView;
        if (threadedInputConnectionProxyView != null) {
            threadedInputConnectionProxyView.onOriginalViewAttachedToWindow();
        }
    }

    @Override // org.chromium.content.browser.input.ChromiumBaseInputConnection.Factory
    public void onViewDetachedFromWindow() {
        CheckInvalidator checkInvalidator = this.mCheckInvalidator;
        if (checkInvalidator != null) {
            checkInvalidator.invalidate();
        }
        ThreadedInputConnectionProxyView threadedInputConnectionProxyView = this.mProxyView;
        if (threadedInputConnectionProxyView != null) {
            threadedInputConnectionProxyView.onOriginalViewDetachedFromWindow();
        }
    }
}
