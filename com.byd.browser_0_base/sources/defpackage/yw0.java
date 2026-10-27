package defpackage;

import org.chromium.content_public.browser.LoadCommittedDetails;
import org.chromium.content_public.browser.WebContents;
import org.chromium.ui.base.WindowAndroid;
import org.chromium.ui.modelutil.PropertyModel;

/* JADX INFO: compiled from: ImageDescriptionsDialog.java */
/* JADX INFO: loaded from: classes.dex */
public final class yw0 extends rq3 {
    public final /* synthetic */ zw0 f;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public yw0(zw0 zw0Var, WebContents webContents) {
        super(webContents);
        this.f = zw0Var;
    }

    @Override // defpackage.rq3
    public final void destroy() {
        super.destroy();
        zw0 zw0Var = this.f;
        if (zw0Var.o == 0) {
            zw0Var.o = 11;
        }
        PropertyModel propertyModel = zw0Var.g;
        zw0Var.f.b(zw0Var.o, propertyModel);
    }

    @Override // defpackage.rq3
    public final void h(WindowAndroid windowAndroid) {
        if (windowAndroid == null) {
            zw0 zw0Var = this.f;
            zw0Var.o = 9;
            zw0Var.h.destroy();
        }
    }

    @Override // defpackage.rq3
    public final void navigationEntryCommitted(LoadCommittedDetails loadCommittedDetails) {
        zw0 zw0Var = this.f;
        zw0Var.o = 10;
        zw0Var.h.destroy();
    }

    @Override // defpackage.rq3
    public final void wasHidden() {
        zw0 zw0Var = this.f;
        zw0Var.o = 6;
        zw0Var.h.destroy();
    }
}
