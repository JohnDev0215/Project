package defpackage;

import android.content.Context;
import android.os.Handler;
import android.os.IBinder;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputConnection;
import java.util.concurrent.Callable;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicReference;
import org.chromium.base.task.PostTask;

/* JADX INFO: compiled from: chromium-SystemWebView.apk-default-410412053 */
/* JADX INFO: loaded from: classes.dex */
public class YB extends View {
    public final Handler s;
    public final View t;
    public final AtomicBoolean u;
    public final AtomicBoolean v;
    public final AtomicReference w;
    public final AtomicReference x;
    public final WB y;

    public YB(Context context, Handler handler, View view, WB wb) {
        super(context);
        this.u = new AtomicBoolean();
        this.v = new AtomicBoolean();
        this.w = new AtomicReference();
        this.x = new AtomicReference();
        this.s = handler;
        this.t = view;
        setFocusable(true);
        setFocusableInTouchMode(true);
        setVisibility(0);
        this.u.set(this.t.hasFocus());
        this.v.set(this.t.hasWindowFocus());
        this.w.set(this.t.getWindowToken());
        this.x.set(this.t.getRootView());
        this.y = wb;
    }

    @Override // android.view.View
    public boolean checkInputConnectionProxy(View view) {
        return this.t == view;
    }

    @Override // android.view.View
    public Handler getHandler() {
        return this.s;
    }

    @Override // android.view.View
    public View getRootView() {
        if (this.v.get()) {
            return (View) this.x.get();
        }
        return null;
    }

    @Override // android.view.View
    public IBinder getWindowToken() {
        return (IBinder) this.w.get();
    }

    @Override // android.view.View
    public boolean hasWindowFocus() {
        return this.v.get();
    }

    @Override // android.view.View
    public boolean isFocused() {
        return this.u.get();
    }

    @Override // android.view.View
    public boolean onCheckIsTextEditor() {
        return true;
    }

    @Override // android.view.View
    public InputConnection onCreateInputConnection(final EditorInfo editorInfo) {
        return (InputConnection) PostTask.c(AbstractC0538bE.d, new Callable(this, editorInfo) { // from class: XB
            public final YB a;
            public final EditorInfo b;

            {
                this.a = this;
                this.b = editorInfo;
            }

            @Override // java.util.concurrent.Callable
            public Object call() {
                YB yb = this.a;
                EditorInfo editorInfo2 = this.b;
                yb.y.f = false;
                InputConnection inputConnectionOnCreateInputConnection = yb.t.onCreateInputConnection(editorInfo2);
                yb.y.f = true;
                return inputConnectionOnCreateInputConnection;
            }
        });
    }

    @Override // android.view.View
    public void onWindowFocusChanged(boolean z) {
        super.onWindowFocusChanged(z);
    }
}
