package defpackage;

import org.chromium.chrome.browser.tab.Tab;
import org.chromium.content_public.browser.WebContents;

/* JADX INFO: compiled from: TabReparentingParams.java */
/* JADX INFO: loaded from: classes2.dex */
public final class d33 implements e9 {
    public final Tab a;
    public final Runnable b;

    public d33(Tab tab, l10 l10Var) {
        this.a = tab;
        this.b = l10Var;
    }

    @Override // defpackage.e9
    public final WebContents c() {
        return null;
    }

    @Override // defpackage.e9
    public final Tab d() {
        return this.a;
    }
}
