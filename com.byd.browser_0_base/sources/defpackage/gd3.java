package defpackage;

import android.view.View;
import android.widget.PopupWindow;

/* JADX INFO: compiled from: R8$$SyntheticClass */
/* JADX INFO: loaded from: classes.dex */
public final /* synthetic */ class gd3 implements PopupWindow.OnDismissListener {
    public final /* synthetic */ int e;
    public final /* synthetic */ xe3 f;
    public final /* synthetic */ Object g;

    public /* synthetic */ gd3(xe3 xe3Var, Object obj, int i) {
        this.e = i;
        this.f = xe3Var;
        this.g = obj;
    }

    @Override // android.widget.PopupWindow.OnDismissListener
    public final void onDismiss() {
        int i = this.e;
        xe3 xe3Var = this.f;
        Object obj = this.g;
        switch (i) {
            case 0:
                xe3Var.dismissed("IPH_DownloadSettings");
                wm3.a((View) obj);
                break;
            default:
                xe3Var.dismissed((String) obj);
                break;
        }
    }
}
