package defpackage;

import J.N;
import java.nio.ByteBuffer;
import org.chromium.mojo.system.ResultAnd;
import org.chromium.mojo.system.impl.CoreImpl;

/* JADX INFO: compiled from: DataPipeConsumerHandleImpl.java */
/* JADX INFO: loaded from: classes3.dex */
public final class y50 extends ut0 implements u50 {
    public y50(CoreImpl coreImpl, long j) {
        super(coreImpl, j);
    }

    public final ResultAnd q(ByteBuffer byteBuffer) {
        x50 x50Var = x50.c;
        CoreImpl coreImpl = this.f;
        coreImpl.getClass();
        ResultAnd resultAnd = (ResultAnd) N.MBDORBtR(coreImpl, this.e, byteBuffer, byteBuffer == null ? 0 : byteBuffer.capacity(), x50Var.a);
        int i = resultAnd.a;
        if (i != 0 && i != 17) {
            throw new ki1(resultAnd.a);
        }
        if (i == 0 && byteBuffer != null) {
            byteBuffer.limit(((Integer) resultAnd.b).intValue());
        }
        return resultAnd;
    }

    public y50(ut0 ut0Var) {
        super(ut0Var);
    }
}
