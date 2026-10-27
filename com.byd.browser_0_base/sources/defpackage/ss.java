package defpackage;

import defpackage.l61;
import org.chromium.chrome.browser.ChromeTabbedActivity;
import org.chromium.chrome.browser.tab.Tab;

/* JADX INFO: compiled from: ChromeTabbedActivity.java */
/* JADX INFO: loaded from: classes.dex */
public final class ss implements l61.a {
    public final /* synthetic */ Tab e;
    public final /* synthetic */ ChromeTabbedActivity f;

    public ss(ChromeTabbedActivity chromeTabbedActivity, Tab tab) {
        this.f = chromeTabbedActivity;
        this.e = tab;
    }

    @Override // l61.a
    public final void h(int i) {
        ChromeTabbedActivity chromeTabbedActivity = this.f;
        a23 a23Var = chromeTabbedActivity.h1;
        Tab tab = this.e;
        a23Var.g(tab.isIncognito()).a(tab);
        chromeTabbedActivity.m3().f(this);
    }
}
