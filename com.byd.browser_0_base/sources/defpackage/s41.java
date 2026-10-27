package defpackage;

import android.view.KeyEvent;
import android.view.View;

/* JADX INFO: compiled from: KeyboardNavigationListener.java */
/* JADX INFO: loaded from: classes2.dex */
public abstract class s41 implements View.OnKeyListener {
    public abstract View a();

    public abstract View b();

    public boolean c() {
        return false;
    }

    @Override // android.view.View.OnKeyListener
    public final boolean onKey(View view, int i, KeyEvent keyEvent) {
        View viewA;
        if (i != 61 || keyEvent.getAction() != 0) {
            if (i == 66 && keyEvent.getAction() == 1) {
                return c();
            }
            return false;
        }
        if (keyEvent.hasNoModifiers()) {
            View viewB = b();
            if (viewB != null) {
                return viewB.requestFocus();
            }
            return false;
        }
        if (!keyEvent.isShiftPressed() || (viewA = a()) == null) {
            return false;
        }
        return viewA.requestFocus();
    }
}
