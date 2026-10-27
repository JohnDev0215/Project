package defpackage;

import org.chromium.components.page_info.PageInfoController;
import org.chromium.content_public.browser.LoadCommittedDetails;
import org.chromium.content_public.browser.WebContents;
import org.chromium.ui.base.WindowAndroid;

/* JADX INFO: compiled from: PageInfoController.java */
/* JADX INFO: loaded from: classes3.dex */
public final class ku1 extends rq3 {
    public final /* synthetic */ PageInfoController f;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public ku1(PageInfoController pageInfoController, WebContents webContents) {
        super(webContents);
        this.f = pageInfoController;
    }

    @Override // defpackage.rq3
    public final void destroy() {
        super.destroy();
        PageInfoController.a(this.f);
    }

    @Override // defpackage.rq3
    public final void h(WindowAndroid windowAndroid) {
        if (windowAndroid == null) {
            PageInfoController.a(this.f);
        }
    }

    @Override // defpackage.rq3
    public final void navigationEntryCommitted(LoadCommittedDetails loadCommittedDetails) {
        this.f.l.b.dismiss();
    }

    @Override // defpackage.rq3
    public final void wasHidden() {
        this.f.l.b.dismiss();
    }
}
