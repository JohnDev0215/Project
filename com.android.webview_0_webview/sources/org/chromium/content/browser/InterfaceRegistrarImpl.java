package org.chromium.content.browser;

import defpackage.AbstractC0823gK;
import defpackage.C0990jK;
import defpackage.C1102lK;
import defpackage.C1191mz;
import defpackage.C1653vL;
import defpackage.Is;
import defpackage.OD;
import java.util.Objects;
import org.chromium.content_public.browser.RenderFrameHost;
import org.chromium.content_public.browser.WebContents;
import org.chromium.mojo.system.impl.CoreImpl;

/* JADX INFO: compiled from: chromium-SystemWebView.apk-default-410412053 */
/* JADX INFO: loaded from: classes.dex */
public class InterfaceRegistrarImpl {
    public static boolean a;

    public static void a() {
        if (a) {
            return;
        }
        a = true;
        C1191mz c1191mz = new C1191mz(null);
        if (OD.b == null) {
            OD.b = new OD();
        }
        OD.b.a.add(c1191mz);
    }

    public static void createInterfaceRegistryForContext(int i) {
        a();
        CoreImpl coreImpl = (CoreImpl) AbstractC0823gK.a;
        Objects.requireNonNull(coreImpl);
        C1653vL c1653vLX = C1653vL.x(new C0990jK(new C1102lK(coreImpl, i)));
        OD od = OD.b;
        if (od == null) {
            return;
        }
        od.a(c1653vLX, Is.a);
    }

    public static void createInterfaceRegistryForRenderFrameHost(int i, RenderFrameHost renderFrameHost) {
        a();
        CoreImpl coreImpl = (CoreImpl) AbstractC0823gK.a;
        Objects.requireNonNull(coreImpl);
        C1653vL.x(new C0990jK(new C1102lK(coreImpl, i)));
    }

    public static void createInterfaceRegistryForWebContents(int i, WebContents webContents) {
        a();
        CoreImpl coreImpl = (CoreImpl) AbstractC0823gK.a;
        Objects.requireNonNull(coreImpl);
        C1653vL.x(new C0990jK(new C1102lK(coreImpl, i)));
    }
}
