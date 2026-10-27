package defpackage;

import J.N;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import org.chromium.chrome.R$id;
import org.chromium.chrome.R$style;
import org.chromium.chrome.browser.tab.TabImpl;
import org.chromium.content_public.browser.LoadUrlParams;
import org.chromium.url.GURL;

/* JADX INFO: compiled from: PageInfoAboutThisSiteController.java */
/* JADX INFO: loaded from: classes2.dex */
public final class fu1 implements yh0 {
    public final /* synthetic */ GURL a;
    public final /* synthetic */ gu1 b;

    public fu1(gu1 gu1Var, GURL gurl) {
        this.b = gu1Var;
        this.a = gurl;
    }

    @Override // defpackage.yh0
    public final void a(ViewGroup viewGroup) {
        ((TextView) viewGroup.findViewById(R$id.origin)).setVisibility(8);
        ((ImageView) viewGroup.findViewById(R$id.security_icon)).setVisibility(8);
        ((TextView) viewGroup.findViewById(R$id.title)).setTextAppearance(R$style.TextAppearance_TextLarge_Primary);
    }

    @Override // defpackage.yh0
    public final void b(GURL gurl, ji jiVar, zh0 zh0Var) {
        if (gurl.equals(this.a)) {
            return;
        }
        jiVar.a(zh0Var, true, 7);
        String strJ = gurl.j();
        gu1 gu1Var = this.b;
        gu1Var.getClass();
        new iz2(false).b(0, (TabImpl) N.MMqeq$AW(gu1Var.h), new LoadUrlParams(strJ, 0));
    }

    @Override // defpackage.yh0
    public final void c(zh0 zh0Var) {
        ((TextView) zh0Var.l.findViewById(R$id.title)).setText(this.b.e());
    }
}
