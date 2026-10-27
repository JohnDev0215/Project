package defpackage;

import org.chromium.chrome.browser.tab.TabImpl;
import org.chromium.content.browser.input.ImeAdapterImpl;
import org.chromium.content.browser.selection.SelectionPopupControllerImpl;
import org.chromium.content_public.browser.WebContents;

/* JADX INFO: compiled from: TabStateBrowserControlsVisibilityDelegate.java */
/* JADX INFO: loaded from: classes2.dex */
public class r33 extends lj implements kx0 {
    public final TabImpl h;
    public WebContents i;
    public boolean j;

    public r33(TabImpl tabImpl) {
        this.h = tabImpl;
        tabImpl.x(new q33(this));
        WebContents webContents = tabImpl.g;
        if (this.i != webContents) {
            this.i = webContents;
            if (webContents != null) {
                ImeAdapterImpl.b(webContents).m.add(this);
            }
        }
        u();
    }

    @Override // defpackage.kx0
    public final void q(boolean z, boolean z2) {
        u();
    }

    public int t() {
        TabImpl tabImpl = this.h;
        WebContents webContents = tabImpl.g;
        if (webContents != null && !webContents.isDestroyed()) {
            z = (!this.j) & (!tabImpl.isHidden()) & (!SelectionPopupControllerImpl.j(webContents).u) & (!(n3.o0(tabImpl.g) == 5)) & (!r4.i().equals("chrome-native")) & (tabImpl.getUrl() != null) & (!r4.i().equals("chrome")) & (!tabImpl.n) & (!tabImpl.v) & (!qo.f().c()) & g90.c().f;
        }
        return z ? 3 : 1;
    }

    public final void u() {
        r(Integer.valueOf(t()));
    }
}
