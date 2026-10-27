package org.chromium.content.browser.framehost;

import J.N;
import defpackage.AbstractC0823gK;
import defpackage.C0990jK;
import defpackage.C1102lK;
import defpackage.C1543tL;
import java.util.Objects;
import org.chromium.content_public.browser.RenderFrameHost;
import org.chromium.mojo.system.impl.CoreImpl;

/* JADX INFO: compiled from: chromium-SystemWebView.apk-default-410412053 */
/* JADX INFO: loaded from: classes.dex */
public class RenderFrameHostImpl implements RenderFrameHost {
    public long a;
    public final RenderFrameHostDelegate b;

    public RenderFrameHostImpl(long j, RenderFrameHostDelegate renderFrameHostDelegate, boolean z, int i) {
        this.a = j;
        this.b = renderFrameHostDelegate;
        CoreImpl coreImpl = (CoreImpl) AbstractC0823gK.a;
        Objects.requireNonNull(coreImpl);
        new C1543tL(new C0990jK(new C1102lK(coreImpl, i)));
        this.b.f(this);
    }

    private void clearNativePtr() {
        this.a = 0L;
        this.b.z(this);
    }

    public static RenderFrameHostImpl create(long j, RenderFrameHostDelegate renderFrameHostDelegate, boolean z, int i) {
        return new RenderFrameHostImpl(j, renderFrameHostDelegate, z, i);
    }

    private long getNativePointer() {
        return this.a;
    }

    @Override // org.chromium.content_public.browser.RenderFrameHost
    public boolean a() {
        long j = this.a;
        if (j == 0) {
            return false;
        }
        return N.M6cbowZq(j, this);
    }

    @Override // org.chromium.content_public.browser.RenderFrameHost
    public boolean b() {
        return N.MhaRMzRy(this.a, this);
    }
}
