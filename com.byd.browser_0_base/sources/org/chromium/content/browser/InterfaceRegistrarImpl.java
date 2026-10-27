package org.chromium.content.browser;

import defpackage.b5;
import defpackage.d21;
import defpackage.dh1;
import defpackage.e21;
import defpackage.fj3;
import defpackage.u4;
import defpackage.v4;
import org.chromium.base.annotations.CalledByNative;
import org.chromium.content.browser.androidoverlay.AndroidOverlayProviderImpl;
import org.chromium.content_public.browser.RenderFrameHost;
import org.chromium.content_public.browser.WebContents;
import org.chromium.mojo.system.impl.CoreImpl;

/* JADX INFO: loaded from: classes3.dex */
public class InterfaceRegistrarImpl {
    public static boolean a;

    public static class a implements d21<Void> {
        @Override // defpackage.d21
        public final void a(e21 e21Var, Void r2) {
            e21Var.t(b5.a, new AndroidOverlayProviderImpl.b());
            e21Var.t(v4.a, new u4.a());
        }
    }

    public static void a() {
        if (a) {
            return;
        }
        a = true;
        a aVar = new a();
        if (d21.a.b == null) {
            d21.a.b = new d21.a<>();
        }
        d21.a.b.a.add(aVar);
    }

    @CalledByNative
    public static void createInterfaceRegistry(long j) {
        a();
        CoreImpl coreImpl = CoreImpl.a.a;
        coreImpl.getClass();
        e21 e21VarC = e21.C(new dh1(new fj3(coreImpl, j)));
        d21.a<Void> aVar = d21.a.b;
        if (aVar == null) {
            return;
        }
        aVar.a(e21VarC, null);
    }

    @CalledByNative
    public static void createInterfaceRegistryForRenderFrameHost(long j, RenderFrameHost renderFrameHost) {
        a();
        CoreImpl coreImpl = CoreImpl.a.a;
        coreImpl.getClass();
        e21 e21VarC = e21.C(new dh1(new fj3(coreImpl, j)));
        d21.a<RenderFrameHost> aVar = d21.a.d;
        if (aVar == null) {
            return;
        }
        aVar.a(e21VarC, renderFrameHost);
    }

    @CalledByNative
    public static void createInterfaceRegistryForWebContents(long j, WebContents webContents) {
        a();
        CoreImpl coreImpl = CoreImpl.a.a;
        coreImpl.getClass();
        e21 e21VarC = e21.C(new dh1(new fj3(coreImpl, j)));
        d21.a<WebContents> aVar = d21.a.c;
        if (aVar == null) {
            return;
        }
        aVar.a(e21VarC, webContents);
    }

    @CalledByNative
    public static void createInterfaceRegistryOnIOThread(long j) {
        CoreImpl coreImpl = CoreImpl.a.a;
        coreImpl.getClass();
        e21.C(new dh1(new fj3(coreImpl, j))).t(v4.a, new u4.a());
    }
}
