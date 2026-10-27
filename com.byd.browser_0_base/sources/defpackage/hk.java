package defpackage;

import android.content.DialogInterface;
import org.chromium.chrome.browser.payments.ui.a;

/* JADX INFO: compiled from: R8$$SyntheticClass */
/* JADX INFO: loaded from: classes.dex */
public final /* synthetic */ class hk implements DialogInterface.OnDismissListener {
    public final /* synthetic */ int e;
    public final /* synthetic */ Object f;

    public /* synthetic */ hk(int i, Object obj) {
        this.e = i;
        this.f = obj;
    }

    @Override // android.content.DialogInterface.OnDismissListener
    public final void onDismiss(DialogInterface dialogInterface) {
        int i = this.e;
        Object obj = this.f;
        switch (i) {
            case 0:
                ik ikVar = (ik) obj;
                ikVar.f = null;
                ikVar.n();
                break;
            case 1:
                ((a) obj).a();
                break;
            default:
                ((pu1) obj).c.b(0);
                break;
        }
    }
}
