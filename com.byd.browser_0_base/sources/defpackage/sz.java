package defpackage;

import android.view.View;

/* JADX INFO: compiled from: ContextMenuManager.java */
/* JADX INFO: loaded from: classes2.dex */
public final class sz implements View.OnAttachStateChangeListener {
    public final /* synthetic */ tz e;

    public sz(tz tzVar) {
        this.e = tzVar;
    }

    @Override // android.view.View.OnAttachStateChangeListener
    public final void onViewDetachedFromWindow(View view) {
        tz tzVar = this.e;
        if (view == tzVar.e) {
            tzVar.c.run();
            view.removeOnAttachStateChangeListener(this);
        }
    }

    @Override // android.view.View.OnAttachStateChangeListener
    public final void onViewAttachedToWindow(View view) {
    }
}
