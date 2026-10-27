package defpackage;

import android.content.res.ColorStateList;
import org.chromium.chrome.browser.theme.ThemeColorProvider;
import org.chromium.chrome.browser.toolbar.c;

/* JADX INFO: compiled from: TabSwitcherButtonCoordinator.java */
/* JADX INFO: loaded from: classes2.dex */
public final class q43 implements ThemeColorProvider.b {
    public final /* synthetic */ c e;

    public q43(c cVar) {
        this.e = cVar;
    }

    @Override // org.chromium.chrome.browser.theme.ThemeColorProvider.b
    public final void e(int i, ColorStateList colorStateList) {
        this.e.a.p(r43.d, colorStateList);
    }
}
