package q;

import android.widget.PopupWindow;

/* JADX INFO: renamed from: q.k, reason: case insensitive filesystem */
/* JADX INFO: loaded from: classes.dex */
public final class C2154k implements PopupWindow.OnDismissListener {
    public final /* synthetic */ C2155l l;

    public C2154k(C2155l c2155l) {
        this.l = c2155l;
    }

    @Override // android.widget.PopupWindow.OnDismissListener
    public final void onDismiss() {
        this.l.c();
    }
}
