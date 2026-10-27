package defpackage;

import defpackage.l61;
import org.chromium.chrome.browser.ChromeTabbedActivity;

/* JADX INFO: compiled from: ChromeTabbedActivity.java */
/* JADX INFO: loaded from: classes.dex */
public final class rs implements l61.a {
    public final /* synthetic */ ChromeTabbedActivity e;

    public rs(ChromeTabbedActivity chromeTabbedActivity) {
        this.e = chromeTabbedActivity;
    }

    @Override // l61.a
    public final void g(int i, boolean z) {
        if (i == 2 || i == 16) {
            ChromeTabbedActivity chromeTabbedActivity = this.e;
            chromeTabbedActivity.F1.get().s(chromeTabbedActivity.N, chromeTabbedActivity.B1);
            chromeTabbedActivity.m3().f(this);
        }
    }
}
