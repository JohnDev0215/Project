package defpackage;

import org.chromium.chrome.browser.tasks.tab_management.d1;

/* JADX INFO: compiled from: R8$$SyntheticClass */
/* JADX INFO: loaded from: classes.dex */
public final /* synthetic */ class py0 implements l81 {
    public final /* synthetic */ int e;
    public final /* synthetic */ Object f;

    public /* synthetic */ py0(int i, Object obj) {
        this.e = i;
        this.f = obj;
    }

    @Override // defpackage.l81
    public final k81 b() {
        int i = this.e;
        Object obj = this.f;
        switch (i) {
            case 0:
                return ((qy0) obj).h;
            case 1:
                return (zd) obj;
            default:
                d1 d1Var = (d1) obj;
                d1Var.getClass();
                return d1Var;
        }
    }
}
