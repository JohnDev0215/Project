package defpackage;

import defpackage.lr2;
import org.chromium.chrome.browser.tab.Tab;
import org.chromium.components.browser_ui.bottomsheet.f;

/* JADX INFO: compiled from: BottomSheetManager.java */
/* JADX INFO: loaded from: classes2.dex */
public final class si implements lr2.b {
    public int a;
    public final /* synthetic */ lr2 b;
    public final /* synthetic */ ui c;

    public si(ui uiVar, lr2 lr2Var) {
        this.c = uiVar;
        this.b = lr2Var;
    }

    @Override // lr2.b
    public final void a(int i, boolean z) {
        if (this.a == i) {
            return;
        }
        this.a = i;
        ui uiVar = this.c;
        uiVar.i((Tab) uiVar.s.f, Integer.valueOf(i), null);
        if (i == 1) {
            ((f) uiVar.r).p();
        }
    }
}
