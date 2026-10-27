package V1;

import android.text.Editable;
import android.text.method.KeyListener;
import android.text.method.MetaKeyKeyListener;
import android.view.KeyEvent;
import android.view.View;
import l3.P;

/* JADX INFO: loaded from: classes.dex */
public final class d implements KeyListener {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final KeyListener f9317a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final io.sentry.hints.i f9318b;

    public d(KeyListener keyListener) {
        io.sentry.hints.i iVar = new io.sentry.hints.i(11);
        this.f9317a = keyListener;
        this.f9318b = iVar;
    }

    @Override // android.text.method.KeyListener
    public final void clearMetaKeyState(View view, Editable editable, int i7) {
        this.f9317a.clearMetaKeyState(view, editable, i7);
    }

    @Override // android.text.method.KeyListener
    public final int getInputType() {
        return this.f9317a.getInputType();
    }

    @Override // android.text.method.KeyListener
    public final boolean onKeyDown(View view, Editable editable, int i7, KeyEvent keyEvent) {
        boolean z6;
        this.f9318b.getClass();
        if (i7 != 67 ? i7 != 112 ? false : P.m(editable, keyEvent, true) : P.m(editable, keyEvent, false)) {
            MetaKeyKeyListener.adjustMetaAfterKeypress(editable);
            z6 = true;
        } else {
            z6 = false;
        }
        return z6 || this.f9317a.onKeyDown(view, editable, i7, keyEvent);
    }

    @Override // android.text.method.KeyListener
    public final boolean onKeyOther(View view, Editable editable, KeyEvent keyEvent) {
        return this.f9317a.onKeyOther(view, editable, keyEvent);
    }

    @Override // android.text.method.KeyListener
    public final boolean onKeyUp(View view, Editable editable, int i7, KeyEvent keyEvent) {
        return this.f9317a.onKeyUp(view, editable, i7, keyEvent);
    }
}
