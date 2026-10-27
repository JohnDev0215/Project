package defpackage;

import J.N;
import java.nio.ByteBuffer;
import java.util.Objects;
import org.chromium.mojo.system.ResultAnd;
import org.chromium.mojo.system.impl.CoreImpl;

/* JADX INFO: renamed from: kK, reason: case insensitive filesystem */
/* JADX INFO: compiled from: chromium-SystemWebView.apk-default-410412053 */
/* JADX INFO: loaded from: classes.dex */
public class C1046kK extends AbstractC0935iK implements InterfaceC0488aK {
    public C1046kK(CoreImpl coreImpl, int i) {
        super(coreImpl, i);
    }

    @Override // defpackage.InterfaceC0488aK
    public ByteBuffer d(long j, long j2, ZJ zj) {
        CoreImpl coreImpl = this.t;
        Objects.requireNonNull(coreImpl);
        ResultAnd resultAnd = (ResultAnd) N.Mm6zKFIo(coreImpl, this.s, j, j2, zj.a);
        if (resultAnd.a == 0) {
            return (ByteBuffer) resultAnd.b;
        }
        throw new WJ(resultAnd.a);
    }

    public C1046kK(AbstractC0935iK abstractC0935iK) {
        super(abstractC0935iK);
    }
}
