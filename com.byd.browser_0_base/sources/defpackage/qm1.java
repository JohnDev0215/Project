package defpackage;

import defpackage.wp2;
import org.chromium.chrome.browser.ntp.NewTabPageLayout;
import org.chromium.chrome.browser.ntp.sql.CardDBHelper;

/* JADX INFO: compiled from: NewTabPageLayout.java */
/* JADX INFO: loaded from: classes2.dex */
public final class qm1 implements wp2.b {
    public final /* synthetic */ int e;
    public final /* synthetic */ im f;
    public final /* synthetic */ NewTabPageLayout g;

    public qm1(NewTabPageLayout newTabPageLayout, int i, im imVar) {
        this.g = newTabPageLayout;
        this.e = i;
        this.f = imVar;
    }

    @Override // wp2.b
    public final void j(Object obj) {
        CardDBHelper.q(this.g.f).t(this.e, this.f, false);
    }

    @Override // wp2.b
    public final void d(Object obj) {
    }
}
