package defpackage;

import org.chromium.content_public.browser.LoadCommittedDetails;
import org.chromium.content_public.browser.WebContents;

/* JADX INFO: compiled from: PageZoomCoordinator.java */
/* JADX INFO: loaded from: classes2.dex */
public final class bv1 extends rq3 {
    public final /* synthetic */ cv1 f;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public bv1(cv1 cv1Var, WebContents webContents) {
        super(webContents);
        this.f = cv1Var;
    }

    @Override // defpackage.rq3
    public final void navigationEntryCommitted(LoadCommittedDetails loadCommittedDetails) {
        this.f.a();
    }

    @Override // defpackage.rq3
    public final void onWebContentsLostFocus() {
        this.f.a();
    }

    @Override // defpackage.rq3
    public final void wasHidden() {
        this.f.a();
    }
}
