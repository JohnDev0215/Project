package org.chromium.content.browser.framehost;

import J.N;
import defpackage.ch1;
import defpackage.pv1;
import defpackage.y11;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import org.chromium.base.Callback;
import org.chromium.base.annotations.CalledByNative;
import org.chromium.content_public.browser.RenderFrameHost;
import org.chromium.mojo.system.impl.CoreImpl;
import org.chromium.url.GURL;
import org.chromium.url.Origin;

/* JADX INFO: loaded from: classes3.dex */
public class RenderFrameHostImpl implements RenderFrameHost {
    public long a;
    public final RenderFrameHostDelegate b;
    public final boolean c;

    public RenderFrameHostImpl(long j, RenderFrameHostDelegate renderFrameHostDelegate, boolean z) {
        this.a = j;
        this.b = renderFrameHostDelegate;
        this.c = z;
        renderFrameHostDelegate.b(this);
    }

    @CalledByNative
    public static RenderFrameHostImpl create(long j, RenderFrameHostDelegate renderFrameHostDelegate, boolean z, int i, int i2) {
        return new RenderFrameHostImpl(j, renderFrameHostDelegate, z);
    }

    @CalledByNative
    public static RenderFrameHost.WebAuthSecurityChecksResults createWebAuthSecurityChecksResults(int i, boolean z) {
        return new RenderFrameHost.WebAuthSecurityChecksResults();
    }

    @Override // org.chromium.content_public.browser.RenderFrameHost
    public final boolean a() {
        long j = this.a;
        if (j == 0) {
            return false;
        }
        return N.MtA0PSG$(j, this);
    }

    @Override // org.chromium.content_public.browser.RenderFrameHost
    public final Origin b() {
        long j = this.a;
        if (j == 0) {
            return null;
        }
        return (Origin) N.Mcdslkop(j, this);
    }

    @Override // org.chromium.content_public.browser.RenderFrameHost
    public final List<RenderFrameHost> c() {
        long j = this.a;
        if (j == 0) {
            return null;
        }
        return Collections.unmodifiableList(Arrays.asList((RenderFrameHost[]) N.MhlVMQ2e(j, this)));
    }

    @CalledByNative
    public final void clearNativePtr() {
        this.a = 0L;
        this.b.d(this);
    }

    @Override // org.chromium.content_public.browser.RenderFrameHost
    public final void d() {
        long j = this.a;
        if (j == 0) {
            return;
        }
        N.M$j92GA1(j, this);
    }

    @Override // org.chromium.content_public.browser.RenderFrameHost
    public final <I extends y11, P extends y11.c> P e(y11.b<I, P> bVar) {
        if (this.a == 0) {
            return null;
        }
        pv1<ch1, ch1> pv1VarB = CoreImpl.a.a.b(null);
        P p = (P) bVar.a(pv1VarB.a, 0);
        N.MXQk8pKb(this.a, this, bVar.f(), pv1VarB.b.P0().s0());
        return p;
    }

    @Override // org.chromium.content_public.browser.RenderFrameHost
    public final boolean f(int i) {
        long j = this.a;
        return j != 0 && N.MqDsGZSU(j, this, i);
    }

    @Override // org.chromium.content_public.browser.RenderFrameHost
    public final GURL g() {
        long j = this.a;
        if (j == 0) {
            return null;
        }
        return (GURL) N.MBg$jIAu(j, this);
    }

    @CalledByNative
    public final long getNativePointer() {
        return this.a;
    }

    @Override // org.chromium.content_public.browser.RenderFrameHost
    public final void h(Callback<GURL> callback) {
        long j = this.a;
        if (j == 0) {
            callback.onResult(null);
        } else {
            N.MUV0o0vB(j, this, callback);
        }
    }

    @Override // org.chromium.content_public.browser.RenderFrameHost
    public final boolean i() {
        long j = this.a;
        if (j == 0) {
            return false;
        }
        return N.M6cbowZq(j, this);
    }

    @Override // org.chromium.content_public.browser.RenderFrameHost
    public final boolean isIncognito() {
        return this.c;
    }

    @Override // org.chromium.content_public.browser.RenderFrameHost
    public final void j() {
        long j = this.a;
        if (j == 0) {
            return;
        }
        N.MnlCu9CQ(j, this, 241);
    }

    @Override // org.chromium.content_public.browser.RenderFrameHost
    public final boolean k() {
        return N.MdnzZzjw(this.a, this);
    }
}
