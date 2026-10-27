package defpackage;

import org.chromium.content_public.browser.LoadCommittedDetails;
import org.chromium.content_public.browser.WebContents;

/* JADX INFO: compiled from: ContextMenuCoordinator.java */
/* JADX INFO: loaded from: classes.dex */
public final class iz extends rq3 {
    public final /* synthetic */ jz f;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public iz(jz jzVar, WebContents webContents) {
        super(webContents);
        this.f = jzVar;
    }

    @Override // defpackage.rq3
    public final void navigationEntryCommitted(LoadCommittedDetails loadCommittedDetails) {
        this.f.a();
    }
}
