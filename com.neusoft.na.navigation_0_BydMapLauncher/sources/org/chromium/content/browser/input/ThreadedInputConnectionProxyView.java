package org.chromium.content.browser.input;

import android.content.Context;
import android.os.Handler;
import android.os.IBinder;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputConnection;
import java.util.concurrent.Callable;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicReference;
import org.chromium.base.ThreadUtils;

/* JADX INFO: loaded from: classes3.dex */
public class ThreadedInputConnectionProxyView extends View {
    private static final boolean DEBUG_LOGS = false;
    private static final String TAG = "cr_Ime";
    private final View mContainerView;
    private final AtomicBoolean mFocused;
    private final Handler mImeThreadHandler;
    private final AtomicReference<View> mRootView;
    private final AtomicBoolean mWindowFocused;
    private final AtomicReference<IBinder> mWindowToken;

    @Override // android.view.View
    public boolean onCheckIsTextEditor() {
        return true;
    }

    ThreadedInputConnectionProxyView(Context context, Handler handler, View view) {
        super(context);
        AtomicBoolean atomicBoolean = new AtomicBoolean();
        this.mFocused = atomicBoolean;
        AtomicBoolean atomicBoolean2 = new AtomicBoolean();
        this.mWindowFocused = atomicBoolean2;
        AtomicReference<IBinder> atomicReference = new AtomicReference<>();
        this.mWindowToken = atomicReference;
        AtomicReference<View> atomicReference2 = new AtomicReference<>();
        this.mRootView = atomicReference2;
        this.mImeThreadHandler = handler;
        this.mContainerView = view;
        setFocusable(true);
        setFocusableInTouchMode(true);
        setVisibility(0);
        atomicBoolean.set(view.hasFocus());
        atomicBoolean2.set(view.hasWindowFocus());
        atomicReference.set(view.getWindowToken());
        atomicReference2.set(view.getRootView());
    }

    public void onOriginalViewFocusChanged(boolean z) {
        this.mFocused.set(z);
    }

    public void onOriginalViewWindowFocusChanged(boolean z) {
        this.mWindowFocused.set(z);
    }

    public void onOriginalViewAttachedToWindow() {
        this.mWindowToken.set(this.mContainerView.getWindowToken());
        this.mRootView.set(this.mContainerView.getRootView());
    }

    public void onOriginalViewDetachedFromWindow() {
        this.mWindowToken.set(null);
        this.mRootView.set(null);
    }

    @Override // android.view.View
    public Handler getHandler() {
        return this.mImeThreadHandler;
    }

    @Override // android.view.View
    public boolean checkInputConnectionProxy(View view) {
        return this.mContainerView == view;
    }

    @Override // android.view.View
    public InputConnection onCreateInputConnection(final EditorInfo editorInfo) {
        return (InputConnection) ThreadUtils.runOnUiThreadBlockingNoException(new Callable<InputConnection>() { // from class: org.chromium.content.browser.input.ThreadedInputConnectionProxyView.1
            /* JADX WARN: Can't rename method to resolve collision */
            @Override // java.util.concurrent.Callable
            public InputConnection call() throws Exception {
                return ThreadedInputConnectionProxyView.this.mContainerView.onCreateInputConnection(editorInfo);
            }
        });
    }

    @Override // android.view.View
    public boolean hasWindowFocus() {
        return this.mWindowFocused.get();
    }

    @Override // android.view.View
    public View getRootView() {
        return this.mRootView.get();
    }

    @Override // android.view.View
    public boolean isFocused() {
        return this.mFocused.get();
    }

    @Override // android.view.View
    public IBinder getWindowToken() {
        return this.mWindowToken.get();
    }

    @Override // android.view.View
    public void onWindowFocusChanged(boolean z) {
        super.onWindowFocusChanged(z);
    }
}
