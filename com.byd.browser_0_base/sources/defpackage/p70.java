package defpackage;

import J.N;
import android.text.TextUtils;
import java.util.regex.Pattern;
import org.chromium.chrome.browser.b;
import org.chromium.chrome.browser.tab.Tab;
import org.chromium.content_public.browser.LoadUrlParams;
import org.chromium.url.GURL;

/* JADX INFO: compiled from: DefaultCustomTabIntentHandlingStrategy.java */
/* JADX INFO: loaded from: classes.dex */
public final class p70 implements w30 {
    public final a30 a;
    public final w20 b;
    public final y30 c;
    public final p61<b40> d;

    public p70(a30 a30Var, w20 w20Var, y30 y30Var, p61<b40> p61Var) {
        this.a = a30Var;
        this.b = w20Var;
        this.c = y30Var;
        this.d = p61Var;
    }

    @Override // defpackage.w30
    public final void a(oj ojVar) {
        a30 a30Var = this.a;
        int i = a30Var.c;
        w20 w20Var = this.b;
        if (i != 4) {
            LoadUrlParams loadUrlParams = new LoadUrlParams(ojVar.K(), 0);
            String strR = q11.r(ojVar.t(), "org.chromium.chrome.browser.password_change.username");
            if (strR != null) {
                q11.t(ojVar.t(), "org.chromium.chrome.browser.password_change.username");
                N.MkPYGQUS(new GURL(loadUrlParams.a), strR);
            }
            w20Var.b(b.l(ojVar.t()), loadUrlParams);
            return;
        }
        Tab tab = a30Var.b;
        if (tab == null) {
            throw new IllegalStateException("handleInitialIntent called before Tab created");
        }
        String strK = ojVar.K();
        GURL gurl = new GURL(strK);
        boolean zK = tab.k();
        y30 y30Var = this.c;
        p61<b40> p61Var = this.d;
        if (!zK && !tab.m()) {
            p61Var.get().g1(tab, gurl);
            p61Var.get().a1(tab, gurl);
            y30Var.g1(tab, gurl);
            y30Var.a1(tab, gurl);
        }
        String str = a30Var.d;
        if (TextUtils.equals(str, strK)) {
            if (tab.k()) {
                p61Var.get().g1(tab, gurl);
                y30Var.g1(tab, gurl);
                return;
            }
            return;
        }
        LoadUrlParams loadUrlParams2 = new LoadUrlParams(strK, 0);
        if (str != null) {
            Pattern pattern = bk3.a;
            if (TextUtils.equals(str, strK) ? false : N.MMsz$c1t(str, strK)) {
                loadUrlParams2.o = true;
            }
        }
        w20Var.b(b.l(ojVar.t()), loadUrlParams2);
    }

    @Override // defpackage.w30
    public final void b(oj ojVar) {
        String strK = ojVar.K();
        if (TextUtils.isEmpty(strK)) {
            return;
        }
        LoadUrlParams loadUrlParams = new LoadUrlParams(strK, 0);
        if (ojVar.T()) {
            loadUrlParams.s = true;
        }
        this.b.b(b.l(ojVar.t()), loadUrlParams);
    }
}
