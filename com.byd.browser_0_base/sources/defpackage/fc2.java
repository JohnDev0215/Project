package defpackage;

import android.view.ViewGroup;
import org.chromium.chrome.R$id;
import org.chromium.chrome.browser.profiles.Profile;

/* JADX INFO: compiled from: R8$$SyntheticClass */
/* JADX INFO: loaded from: classes2.dex */
public final /* synthetic */ class fc2 implements nw2 {
    public final /* synthetic */ int e;
    public final /* synthetic */ nc2 f;

    public /* synthetic */ fc2(nc2 nc2Var, int i) {
        this.e = i;
        this.f = nc2Var;
    }

    @Override // defpackage.nw2
    public final Object get() {
        int i = this.e;
        nc2 nc2Var = this.f;
        switch (i) {
            case 0:
                return (ViewGroup) nc2Var.g.findViewById(R$id.sheet_container);
            case 1:
                Profile profile = nc2Var.L.get();
                if (profile == null) {
                    return null;
                }
                return oj2.t(profile);
            default:
                return nc2Var.d();
        }
    }
}
