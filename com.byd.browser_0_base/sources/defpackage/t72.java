package defpackage;

import org.chromium.components.webapps.bottomsheet.PwaBottomSheetController;
import org.chromium.content_public.browser.NavigationHandle;
import org.chromium.content_public.browser.WebContents;

/* JADX INFO: compiled from: PwaBottomSheetController.java */
/* JADX INFO: loaded from: classes3.dex */
public final class t72 extends rq3 {
    public final /* synthetic */ PwaBottomSheetController f;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public t72(PwaBottomSheetController pwaBottomSheetController, WebContents webContents) {
        super(webContents);
        this.f = pwaBottomSheetController;
    }

    @Override // defpackage.rq3
    public final void didFinishNavigationInPrimaryMainFrame(NavigationHandle navigationHandle) {
        if (navigationHandle.g) {
            PwaBottomSheetController pwaBottomSheetController = this.f;
            pwaBottomSheetController.g.c(pwaBottomSheetController.i, true);
        }
    }
}
