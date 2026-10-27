package defpackage;

import defpackage.l61;
import org.chromium.chrome.browser.tab.Tab;
import org.chromium.components.browser_ui.bottomsheet.f;

/* JADX INFO: compiled from: BottomSheetManager.java */
/* JADX INFO: loaded from: classes2.dex */
public final class ti implements l61.a {
    public int e;
    public final /* synthetic */ ui f;

    public ti(ui uiVar) {
        this.f = uiVar;
    }

    @Override // l61.a
    public final void h(int i) {
        if (this.e == i) {
            return;
        }
        this.e = i;
        ui uiVar = this.f;
        uiVar.i((Tab) uiVar.s.f, null, Integer.valueOf(i));
        if (this.e == 16) {
            ((f) uiVar.r).p();
        }
    }
}
