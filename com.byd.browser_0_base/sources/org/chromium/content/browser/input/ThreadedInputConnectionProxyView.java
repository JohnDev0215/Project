package org.chromium.content.browser.input;

import android.content.Context;
import android.os.Handler;
import android.os.IBinder;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputConnection;
import defpackage.d73;
import defpackage.l93;
import defpackage.rh3;
import defpackage.uv;
import java.util.concurrent.Callable;
import java.util.concurrent.FutureTask;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicReference;
import org.chromium.base.task.PostTask;
import org.chromium.content.browser.input.ThreadedInputConnectionProxyView;

/* JADX INFO: loaded from: classes3.dex */
public class ThreadedInputConnectionProxyView extends View {
    public final Handler e;
    public final View f;
    public final AtomicBoolean g;
    public final AtomicBoolean h;
    public final AtomicReference<IBinder> i;
    public final AtomicReference<View> j;
    public final l93 k;

    public ThreadedInputConnectionProxyView(Context context, Handler handler, View view, l93 l93Var) {
        super(context);
        AtomicBoolean atomicBoolean = new AtomicBoolean();
        this.g = atomicBoolean;
        AtomicBoolean atomicBoolean2 = new AtomicBoolean();
        this.h = atomicBoolean2;
        AtomicReference<IBinder> atomicReference = new AtomicReference<>();
        this.i = atomicReference;
        AtomicReference<View> atomicReference2 = new AtomicReference<>();
        this.j = atomicReference2;
        this.e = handler;
        this.f = view;
        setFocusable(true);
        setFocusableInTouchMode(true);
        setVisibility(0);
        atomicBoolean.set(view.hasFocus());
        atomicBoolean2.set(view.hasWindowFocus());
        atomicReference.set(view.getWindowToken());
        atomicReference2.set(view.getRootView());
        this.k = l93Var;
    }

    @Override // android.view.View
    public final boolean checkInputConnectionProxy(View view) {
        return this.f == view;
    }

    @Override // android.view.View
    public Handler getHandler() {
        return this.e;
    }

    @Override // android.view.View
    public View getRootView() {
        if (this.h.get()) {
            return this.j.get();
        }
        return null;
    }

    @Override // android.view.View
    public IBinder getWindowToken() {
        return this.i.get();
    }

    @Override // android.view.View
    public final boolean hasWindowFocus() {
        return this.h.get();
    }

    @Override // android.view.View
    public final boolean isFocused() {
        return this.g.get();
    }

    @Override // android.view.View
    public final boolean onCheckIsTextEditor() {
        return true;
    }

    @Override // android.view.View
    public final InputConnection onCreateInputConnection(final EditorInfo editorInfo) {
        d73 d73Var = rh3.d;
        Callable callable = new Callable() { // from class: m93
            @Override // java.util.concurrent.Callable
            public final Object call() {
                ThreadedInputConnectionProxyView threadedInputConnectionProxyView = this.a;
                l93 l93Var = threadedInputConnectionProxyView.k;
                l93Var.f = false;
                InputConnection inputConnectionOnCreateInputConnection = threadedInputConnectionProxyView.f.onCreateInputConnection(editorInfo);
                l93Var.f = true;
                return inputConnectionOnCreateInputConnection;
            }
        };
        Object obj = PostTask.a;
        FutureTask futureTask = new FutureTask(callable);
        PostTask.d(d73Var, futureTask);
        try {
            return (InputConnection) futureTask.get();
        } catch (Exception e) {
            throw new uv(e);
        }
    }

    @Override // android.view.View
    public final void onWindowFocusChanged(boolean z) {
        super.onWindowFocusChanged(z);
    }
}
