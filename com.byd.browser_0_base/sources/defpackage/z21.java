package defpackage;

import android.app.Dialog;
import android.content.Context;
import org.chromium.components.permissions.b;

/* JADX INFO: compiled from: ItemChooserDialog.java */
/* JADX INFO: loaded from: classes3.dex */
public final class z21 extends Dialog {
    public final /* synthetic */ b e;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public z21(b bVar, Context context) {
        super(context);
        this.e = bVar;
    }

    @Override // android.app.Dialog, android.view.Window.Callback
    public final void onWindowFocusChanged(boolean z) {
        super.onWindowFocusChanged(z);
        b bVar = this.e;
        if (!bVar.l && !z) {
            dismiss();
        }
        bVar.l = false;
    }
}
