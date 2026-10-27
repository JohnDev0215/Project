package org.chromium.chrome.browser.contextmenu;

import defpackage.aw;
import defpackage.jz;
import defpackage.l91;
import defpackage.vz;
import defpackage.wb;
import org.chromium.base.annotations.CalledByNative;
import org.chromium.components.embedder_support.contextmenu.ContextMenuParams;
import org.chromium.content_public.browser.WebContents;
import org.chromium.ui.base.WindowAndroid;

/* JADX INFO: loaded from: classes.dex */
public class ContextMenuHelper {
    public final WebContents a;
    public long b;
    public ContextMenuNativeDelegateImpl c;
    public vz d;
    public ContextMenuPopulatorFactory e;
    public ContextMenuParams f;
    public jz g;
    public WindowAndroid h;
    public wb i;
    public l91 j;
    public aw k;
    public long l;
    public boolean m;

    public ContextMenuHelper(long j, WebContents webContents) {
        this.b = j;
        this.a = webContents;
    }

    @CalledByNative
    public static ContextMenuHelper create(long j, WebContents webContents) {
        return new ContextMenuHelper(j, webContents);
    }

    @CalledByNative
    public final void destroy() {
        dismissContextMenu();
        ContextMenuNativeDelegateImpl contextMenuNativeDelegateImpl = this.c;
        if (contextMenuNativeDelegateImpl != null) {
            contextMenuNativeDelegateImpl.b = 0L;
        }
        ContextMenuPopulatorFactory contextMenuPopulatorFactory = this.e;
        if (contextMenuPopulatorFactory != null) {
            contextMenuPopulatorFactory.onDestroy();
        }
        this.b = 0L;
    }

    @CalledByNative
    public final void dismissContextMenu() {
        jz jzVar = this.g;
        if (jzVar != null) {
            jzVar.a();
            this.g = null;
        }
    }

    @CalledByNative
    public final void setPopulatorFactory(ContextMenuPopulatorFactory contextMenuPopulatorFactory) {
        dismissContextMenu();
        ContextMenuNativeDelegateImpl contextMenuNativeDelegateImpl = this.c;
        if (contextMenuNativeDelegateImpl != null) {
            contextMenuNativeDelegateImpl.b = 0L;
        }
        this.d = null;
        ContextMenuPopulatorFactory contextMenuPopulatorFactory2 = this.e;
        if (contextMenuPopulatorFactory2 != null) {
            contextMenuPopulatorFactory2.onDestroy();
        }
        this.e = contextMenuPopulatorFactory;
    }

    /* JADX WARN: Removed duplicated region for block: B:48:0x0139  */
    /* JADX WARN: Removed duplicated region for block: B:69:0x01b6  */
    /* JADX WARN: Removed duplicated region for block: B:72:0x0208  */
    /* JADX WARN: Removed duplicated region for block: B:76:0x021e  */
    /* JADX WARN: Removed duplicated region for block: B:83:0x0252  */
    /* JADX WARN: Removed duplicated region for block: B:88:0x02c7  */
    @org.chromium.base.annotations.CalledByNative
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final void showContextMenu(org.chromium.components.embedder_support.contextmenu.ContextMenuParams r26, org.chromium.content_public.browser.RenderFrameHost r27, android.view.View r28, float r29) {
        /*
            Method dump skipped, instruction units count: 747
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: org.chromium.chrome.browser.contextmenu.ContextMenuHelper.showContextMenu(org.chromium.components.embedder_support.contextmenu.ContextMenuParams, org.chromium.content_public.browser.RenderFrameHost, android.view.View, float):void");
    }
}
