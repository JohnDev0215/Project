package org.chromium.components.media_router;

import J.N;
import defpackage.ne1;
import defpackage.sm;
import defpackage.z7;
import org.chromium.base.annotations.CalledByNative;
import org.chromium.content_public.browser.WebContents;

/* JADX INFO: loaded from: classes.dex */
public class BrowserMediaRouterDialogController {
    public final long a;
    public final WebContents b;

    public BrowserMediaRouterDialogController(long j, WebContents webContents) {
        this.a = j;
        this.b = webContents;
    }

    @CalledByNative
    public static BrowserMediaRouterDialogController create(long j, WebContents webContents) {
        return new BrowserMediaRouterDialogController(j, webContents);
    }

    @CalledByNative
    public void closeDialog() {
        if (isShowingDialog()) {
            throw null;
        }
    }

    @CalledByNative
    public boolean isShowingDialog() {
        return false;
    }

    @CalledByNative
    public void openRouteChooserDialog(String[] strArr) {
        if (isShowingDialog()) {
            return;
        }
        ne1 ne1VarJ = null;
        for (String str : strArr) {
            sm smVarC = sm.c(str);
            ne1VarJ = smVarC == null ? z7.j(str) : smVarC;
            if (ne1VarJ != null) {
                break;
            }
        }
        if (ne1VarJ != null) {
            ne1VarJ.a();
        }
        N.MY1J7b0i(this.a, this);
    }

    @CalledByNative
    public void openRouteControllerDialog(String str, String str2) {
        if (isShowingDialog()) {
            return;
        }
        ne1 ne1VarC = sm.c(str);
        if (ne1VarC == null) {
            ne1VarC = z7.j(str);
        }
        if (ne1VarC != null) {
            ne1VarC.a();
        }
        N.MY1J7b0i(this.a, this);
    }

    public final void a() {
    }
}
