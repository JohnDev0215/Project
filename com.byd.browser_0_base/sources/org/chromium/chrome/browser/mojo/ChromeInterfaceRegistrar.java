package org.chromium.chrome.browser.mojo;

import defpackage.d21;
import defpackage.e21;
import defpackage.q9;
import defpackage.r01;
import defpackage.tk2;
import defpackage.u9;
import defpackage.uk2;
import defpackage.um3;
import defpackage.uz1;
import defpackage.x01;
import defpackage.xq;
import defpackage.y90;
import org.chromium.base.annotations.CalledByNative;
import org.chromium.content_public.browser.RenderFrameHost;
import org.chromium.content_public.browser.WebContents;

/* JADX INFO: loaded from: classes2.dex */
public class ChromeInterfaceRegistrar {

    public static class a implements d21<RenderFrameHost> {
        @Override // defpackage.d21
        public final void a(e21 e21Var, RenderFrameHost renderFrameHost) {
            RenderFrameHost renderFrameHost2 = renderFrameHost;
            e21Var.t(uz1.a, new xq(renderFrameHost2));
            e21Var.t(x01.a, new r01(renderFrameHost2));
            e21Var.t(u9.a, new q9(renderFrameHost2));
            e21Var.t(y90.a, new um3(1, renderFrameHost2));
        }
    }

    public static class b implements d21<WebContents> {
        @Override // defpackage.d21
        public final void a(e21 e21Var, WebContents webContents) {
            e21Var.t(uk2.a, new tk2(webContents));
        }
    }

    @CalledByNative
    public static void registerMojoInterfaces() {
        b bVar = new b();
        if (d21.a.c == null) {
            d21.a.c = new d21.a<>();
        }
        d21.a.c.a.add(bVar);
        a aVar = new a();
        if (d21.a.d == null) {
            d21.a.d = new d21.a<>();
        }
        d21.a.d.a.add(aVar);
    }
}
