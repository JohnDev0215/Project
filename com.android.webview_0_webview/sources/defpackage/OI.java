package defpackage;

import J.N;
import java.nio.ByteBuffer;
import java.util.List;
import org.chromium.mojo.system.ResultAnd;
import org.chromium.mojo.system.impl.WatcherImpl;

/* JADX INFO: compiled from: chromium-SystemWebView.apk-default-410412053 */
/* JADX INFO: loaded from: classes.dex */
public class OI implements InterfaceC1101lJ, ZI {
    public final NI s;
    public final VJ t;
    public final InterfaceC0656dK u;
    public InterfaceC1101lJ v;
    public LI w;

    public OI(VJ vj) {
        InterfaceC0656dK interfaceC0656dKA = JI.a(vj);
        this.s = new NI(this, null);
        this.t = vj;
        this.u = interfaceC0656dKA;
    }

    public static ResultAnd I(VJ vj, InterfaceC1101lJ interfaceC1101lJ) {
        Boolean bool = Boolean.FALSE;
        ResultAnd resultAndY = vj.y(SJ.c);
        int i = resultAndY.a;
        if (i != 0) {
            return new ResultAnd(i, bool);
        }
        TJ tj = (TJ) resultAndY.b;
        if (interfaceC1101lJ == null) {
            return new ResultAnd(i, bool);
        }
        try {
            return new ResultAnd(resultAndY.a, Boolean.valueOf(interfaceC1101lJ.q(new C0989jJ(ByteBuffer.wrap(tj.a), tj.c))));
        } catch (RuntimeException e) {
            throw e;
        }
    }

    public final void A(WJ wj) {
        close();
        LI li = this.w;
        if (li != null) {
            try {
                li.I(wj);
            } catch (RuntimeException e) {
                throw e;
            }
        }
    }

    @Override // defpackage.ZI
    /* JADX INFO: renamed from: G, reason: merged with bridge method [inline-methods] */
    public VJ e() {
        x();
        VJ vjV = this.t.v();
        InterfaceC1101lJ interfaceC1101lJ = this.v;
        if (interfaceC1101lJ != null) {
            interfaceC1101lJ.close();
        }
        return vjV;
    }

    @Override // defpackage.InterfaceC1101lJ, java.io.Closeable, java.lang.AutoCloseable
    public void close() {
        x();
        this.t.close();
        InterfaceC1101lJ interfaceC1101lJ = this.v;
        if (interfaceC1101lJ != null) {
            this.v = null;
            interfaceC1101lJ.close();
        }
    }

    @Override // defpackage.InterfaceC1101lJ
    public boolean q(C0989jJ c0989jJ) {
        try {
            VJ vj = this.t;
            ByteBuffer byteBuffer = c0989jJ.a;
            List list = c0989jJ.b;
            UJ uj = UJ.c;
            vj.N(byteBuffer, list, UJ.c);
            return true;
        } catch (WJ e) {
            A(e);
            return false;
        }
    }

    public final void x() {
        WatcherImpl watcherImpl = (WatcherImpl) this.u;
        long j = watcherImpl.a;
        if (j != 0) {
            watcherImpl.b = null;
            N.MPTT407x(watcherImpl, j);
        }
        WatcherImpl watcherImpl2 = (WatcherImpl) this.u;
        long j2 = watcherImpl2.a;
        if (j2 == 0) {
            return;
        }
        N.Mi32vqDA(watcherImpl2, j2);
        watcherImpl2.a = 0L;
    }

    public OI(VJ vj, InterfaceC0656dK interfaceC0656dK) {
        this.s = new NI(this, null);
        this.t = vj;
        this.u = interfaceC0656dK;
    }
}
