package defpackage;

import J.N;
import defpackage.jl2;
import java.nio.ByteBuffer;
import org.chromium.mojo.system.ResultAnd;
import org.chromium.mojo.system.impl.CoreImpl;

/* JADX INFO: compiled from: SharedBufferHandleImpl.java */
/* JADX INFO: loaded from: classes3.dex */
public final class kl2 extends ut0 implements jl2 {
    public kl2(ut0 ut0Var) {
        super(ut0Var);
    }

    @Override // defpackage.jl2
    public final void D(ByteBuffer byteBuffer) {
        CoreImpl coreImpl = this.f;
        coreImpl.getClass();
        int iMC_p8PYl = N.MC_p8PYl(coreImpl, byteBuffer);
        if (iMC_p8PYl != 0) {
            throw new ki1(iMC_p8PYl);
        }
    }

    @Override // defpackage.jl2
    public final ByteBuffer d(long j) {
        jl2.a aVar = jl2.a.c;
        CoreImpl coreImpl = this.f;
        coreImpl.getClass();
        ResultAnd resultAnd = (ResultAnd) N.Mm6zKFIo(coreImpl, this.e, 0L, j, aVar.a);
        if (resultAnd.a == 0) {
            return (ByteBuffer) resultAnd.b;
        }
        throw new ki1(resultAnd.a);
    }
}
