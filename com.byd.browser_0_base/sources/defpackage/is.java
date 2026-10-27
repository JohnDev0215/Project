package defpackage;

import org.chromium.chrome.browser.toolbar.g;

/* JADX INFO: compiled from: R8$$SyntheticClass */
/* JADX INFO: loaded from: classes.dex */
public final /* synthetic */ class is implements nw2 {
    public final /* synthetic */ int e;
    public final /* synthetic */ g f;

    public /* synthetic */ is(g gVar, int i) {
        this.e = i;
        this.f = gVar;
    }

    @Override // defpackage.nw2
    public final Object get() {
        int i = this.e;
        g gVar = this.f;
        switch (i) {
            case 0:
                return gVar.E.i();
            default:
                nw2<Boolean> nw2Var = gVar.C0;
                return Boolean.valueOf(nw2Var != null && nw2Var.get().booleanValue());
        }
    }
}
