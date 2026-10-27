package defpackage;

import defpackage.p9;
import defpackage.u9;
import java.util.LinkedList;
import org.chromium.content_public.browser.RenderFrameHost;

/* JADX INFO: compiled from: AuthenticatorImpl.java */
/* JADX INFO: loaded from: classes3.dex */
public final class r9 implements p9 {
    public final iq3 e;
    public final RenderFrameHost f;
    public boolean g;
    public p9.d h;
    public p9.a i;

    public r9(iq3 iq3Var, RenderFrameHost renderFrameHost) {
        new LinkedList();
        if (iq3Var != null) {
            this.e = iq3Var;
        } else {
            n3.R(renderFrameHost).V();
        }
        this.f = renderFrameHost;
        renderFrameHost.b();
    }

    @Override // defpackage.y11, java.io.Closeable, java.lang.AutoCloseable
    public final void close() {
        this.g = false;
        this.h = null;
        this.i = null;
    }

    @Override // defpackage.p9
    public final void e1(k72 k72Var, p9.a aVar) {
        if (this.g) {
            aVar.d(1, null, null);
            return;
        }
        this.i = aVar;
        this.g = true;
        if (this.g) {
            p9.d dVar = this.h;
            if (dVar != null) {
                dVar.d(5, null, null);
            } else {
                p9.a aVar2 = this.i;
                if (aVar2 != null) {
                    aVar2.d(5, null, null);
                }
            }
            close();
        }
    }

    @Override // defpackage.p9
    public final void i0(u9.n nVar) {
        Boolean bool = Boolean.FALSE;
        oj2.O("WebAuthentication.IsUVPlatformAuthenticatorAvailable2", false);
        nVar.c(bool);
    }

    @Override // defpackage.p9
    public final void n(h72 h72Var, p9.d dVar) {
        if (this.g) {
            dVar.d(1, null, null);
            return;
        }
        this.h = dVar;
        this.g = true;
        if (this.g) {
            p9.d dVar2 = this.h;
            if (dVar2 != null) {
                dVar2.d(5, null, null);
            } else {
                p9.a aVar = this.i;
                if (aVar != null) {
                    aVar.d(5, null, null);
                }
            }
            close();
        }
    }

    @Override // defpackage.p9
    public final void o0(u9.j jVar) {
        jVar.c(Boolean.FALSE);
    }

    @Override // defpackage.fx
    public final void q(ki1 ki1Var) {
        close();
    }

    @Override // defpackage.p9
    public final void cancel() {
    }
}
