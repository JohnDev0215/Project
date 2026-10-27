package defpackage;

import android.content.DialogInterface;

/* JADX INFO: compiled from: R8$$SyntheticClass */
/* JADX INFO: loaded from: classes.dex */
public final /* synthetic */ class gk implements DialogInterface.OnDismissListener {
    public final /* synthetic */ int e;
    public final /* synthetic */ Object f;

    public /* synthetic */ gk(int i, Object obj) {
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
                ikVar.e = null;
                ikVar.h = null;
                ikVar.n();
                break;
            default:
                ((hj0) obj).b = null;
                break;
        }
    }
}
