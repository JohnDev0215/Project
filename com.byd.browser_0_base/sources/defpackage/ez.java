package defpackage;

import android.content.DialogInterface;
import org.chromium.components.permissions.b;

/* JADX INFO: compiled from: R8$$SyntheticClass */
/* JADX INFO: loaded from: classes.dex */
public final /* synthetic */ class ez implements DialogInterface.OnDismissListener {
    public final /* synthetic */ int e;
    public final /* synthetic */ Object f;

    public /* synthetic */ ez(int i, Object obj) {
        this.e = i;
        this.f = obj;
    }

    @Override // android.content.DialogInterface.OnDismissListener
    public final void onDismiss(DialogInterface dialogInterface) {
        int i = this.e;
        Object obj = this.f;
        switch (i) {
            case 0:
                ((jz) obj).f.run();
                break;
            default:
                ((b) obj).d.a("");
                break;
        }
    }
}
