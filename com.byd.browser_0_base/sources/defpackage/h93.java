package defpackage;

import android.view.KeyEvent;

/* JADX INFO: compiled from: ThreadedInputConnection.java */
/* JADX INFO: loaded from: classes3.dex */
public final class h93 implements Runnable {
    public final /* synthetic */ KeyEvent e;
    public final /* synthetic */ f93 f;

    public h93(f93 f93Var, KeyEvent keyEvent) {
        this.f = f93Var;
        this.e = keyEvent;
    }

    @Override // java.lang.Runnable
    public final void run() {
        this.f.sendKeyEvent(this.e);
    }
}
