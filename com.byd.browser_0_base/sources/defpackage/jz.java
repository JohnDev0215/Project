package defpackage;

import org.chromium.chrome.browser.contextmenu.ContextMenuListView;
import org.chromium.content_public.browser.WebContents;

/* JADX INFO: compiled from: ContextMenuCoordinator.java */
/* JADX INFO: loaded from: classes.dex */
public final class jz {
    public WebContents a;
    public iz b;
    public mz c;
    public ContextMenuListView d;
    public lz e;
    public Runnable f;

    public jz(float f, uz uzVar) {
    }

    public final void a() {
        iz izVar = this.b;
        if (izVar != null) {
            izVar.destroy();
        }
        this.e.dismiss();
    }
}
