package defpackage;

import android.content.Intent;
import org.chromium.chrome.browser.tab.Tab;

/* JADX INFO: compiled from: NewTabPageUma.java */
/* JADX INFO: loaded from: classes2.dex */
public final class um1 {
    public final y13 a;
    public final nw2<Long> b;
    public final boolean c;
    public final nw2<Intent> d;
    public a e;

    /* JADX INFO: compiled from: NewTabPageUma.java */
    public static class a implements e23 {
        @Override // defpackage.e23
        public final void i(Tab tab) {
            if (bk3.l(tab.getUrl())) {
                n3.X0("MobileNTPOpenedInNewTab");
            }
        }
    }

    public um1(y13 y13Var, nw2 nw2Var, boolean z, hs hsVar) {
        this.a = y13Var;
        this.b = nw2Var;
        this.c = z;
        this.d = hsVar;
    }

    public static void a(int i) {
        oj2.U(i, 17, "NewTabPage.ActionAndroid2");
    }
}
