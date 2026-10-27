package defpackage;

import java.util.List;
import org.chromium.base.Callback;
import org.chromium.components.signin.identitymanager.AccountTrackerService;

/* JADX INFO: compiled from: R8$$SyntheticClass */
/* JADX INFO: loaded from: classes.dex */
public final /* synthetic */ class tg implements Callback {
    public final /* synthetic */ int e;
    public final /* synthetic */ boolean f;
    public final /* synthetic */ Object g;

    public /* synthetic */ tg(Object obj, int i, boolean z) {
        this.e = i;
        this.g = obj;
        this.f = z;
    }

    @Override // org.chromium.base.Callback
    public final void onResult(Object obj) {
        int i = this.e;
        boolean z = this.f;
        Object obj2 = this.g;
        switch (i) {
            case 0:
                vg vgVar = (vg) obj2;
                vgVar.getClass();
                if (!((Boolean) obj).booleanValue()) {
                    if (!z) {
                        vgVar.e.g(new ug(vgVar));
                    } else {
                        vgVar.a();
                    }
                    break;
                }
                break;
            default:
                ((AccountTrackerService) obj2).a((List) obj, z);
                break;
        }
    }
}
