package p;

import android.content.DialogInterface;
import android.view.KeyEvent;
import android.view.View;
import android.view.Window;

/* JADX INFO: loaded from: classes.dex */
public final class ti1 implements DialogInterface.OnKeyListener, DialogInterface.OnClickListener, DialogInterface.OnDismissListener, nj1 {
    public si1 c;
    public k6 e;
    public ab1 k;

    @Override // p.nj1
    public final void b(si1 si1Var, boolean z) {
        k6 k6Var;
        if ((z || si1Var == this.c) && (k6Var = this.e) != null) {
            k6Var.dismiss();
        }
    }

    @Override // android.content.DialogInterface.OnClickListener
    public final void onClick(DialogInterface dialogInterface, int i) {
        ab1 ab1Var = this.k;
        if (ab1Var.n == null) {
            ab1Var.n = new za1(ab1Var);
        }
        this.c.q(ab1Var.n.getItem(i), null, 0);
    }

    @Override // android.content.DialogInterface.OnDismissListener
    public final void onDismiss(DialogInterface dialogInterface) {
        this.k.b(this.c, true);
    }

    @Override // android.content.DialogInterface.OnKeyListener
    public final boolean onKey(DialogInterface dialogInterface, int i, KeyEvent keyEvent) {
        Window window;
        View decorView;
        KeyEvent.DispatcherState keyDispatcherState;
        View decorView2;
        KeyEvent.DispatcherState keyDispatcherState2;
        si1 si1Var = this.c;
        if (i == 82 || i == 4) {
            if (keyEvent.getAction() == 0 && keyEvent.getRepeatCount() == 0) {
                Window window2 = this.e.getWindow();
                if (window2 != null && (decorView2 = window2.getDecorView()) != null && (keyDispatcherState2 = decorView2.getKeyDispatcherState()) != null) {
                    keyDispatcherState2.startTracking(keyEvent, this);
                    return true;
                }
            } else if (keyEvent.getAction() == 1 && !keyEvent.isCanceled() && (window = this.e.getWindow()) != null && (decorView = window.getDecorView()) != null && (keyDispatcherState = decorView.getKeyDispatcherState()) != null && keyDispatcherState.isTracking(keyEvent)) {
                si1Var.c(true);
                dialogInterface.dismiss();
                return true;
            }
        }
        return si1Var.performShortcut(i, keyEvent, 0);
    }

    @Override // p.nj1
    public final boolean s(si1 si1Var) {
        return false;
    }
}
