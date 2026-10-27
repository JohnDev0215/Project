package defpackage;

import org.chromium.content_public.browser.NavigationHandle;
import org.chromium.content_public.browser.WebContents;

/* JADX INFO: compiled from: PostMessageHandler.java */
/* JADX INFO: loaded from: classes.dex */
public final class x22 extends rq3 {
    public boolean f;
    public final /* synthetic */ WebContents g;
    public final /* synthetic */ z22 h;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public x22(z22 z22Var, WebContents webContents, WebContents webContents2) {
        super(webContents);
        this.h = z22Var;
        this.g = webContents2;
    }

    @Override // defpackage.rq3
    public final void didFinishNavigationInPrimaryMainFrame(NavigationHandle navigationHandle) {
        if (this.f && navigationHandle.g && !navigationHandle.c) {
            z22 z22Var = this.h;
            if (z22Var.d != null) {
                this.g.z(this);
                z22Var.b();
                return;
            }
        }
        this.f = true;
    }

    @Override // defpackage.rq3
    public final void g(ct0 ct0Var, int i) {
        z22 z22Var = this.h;
        if (z22Var.d != null) {
            return;
        }
        z22Var.d(this.g);
    }

    @Override // defpackage.rq3
    public final void renderProcessGone() {
        this.h.b();
    }
}
