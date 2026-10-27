package defpackage;

import J.N;
import defpackage.ch1;
import java.nio.ByteBuffer;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import org.chromium.mojo.system.ResultAnd;
import org.chromium.mojo.system.impl.CoreImpl;

/* JADX INFO: compiled from: MessagePipeHandleImpl.java */
/* JADX INFO: loaded from: classes3.dex */
public final class dh1 extends ut0 implements ch1 {
    public dh1(CoreImpl coreImpl, long j) {
        super(coreImpl, j);
    }

    @Override // defpackage.ch1
    public final ch1 P0() {
        return new dh1(this);
    }

    @Override // defpackage.ch1
    public final void l(ByteBuffer byteBuffer, List list) {
        ByteBuffer byteBuffer2;
        ch1.e eVar = ch1.e.c;
        CoreImpl coreImpl = this.f;
        coreImpl.getClass();
        if (list == null || list.isEmpty()) {
            byteBuffer2 = null;
        } else {
            ByteBuffer byteBufferA = coreImpl.a(list.size() * 8);
            Iterator it = list.iterator();
            while (it.hasNext()) {
                byteBufferA.putLong(((tt0) it.next()).s0());
            }
            byteBufferA.position(0);
            byteBuffer2 = byteBufferA;
        }
        int iMkun8eIV = N.Mkun8eIV(coreImpl, this.e, byteBuffer, byteBuffer == null ? 0 : byteBuffer.limit(), byteBuffer2, eVar.a);
        if (iMkun8eIV != 0) {
            throw new ki1(iMkun8eIV);
        }
    }

    @Override // defpackage.ch1
    public final ResultAnd u() {
        ch1.c cVar = ch1.c.c;
        CoreImpl coreImpl = this.f;
        coreImpl.getClass();
        ResultAnd resultAnd = (ResultAnd) N.MEHdfwD0(coreImpl, this.e, cVar.a);
        int i = resultAnd.a;
        if (i != 0 && i != 17) {
            throw new ki1(resultAnd.a);
        }
        ch1.d dVar = (ch1.d) resultAnd.b;
        long[] jArr = dVar.b;
        if (jArr == null || jArr.length == 0) {
            dVar.c = new ArrayList(0);
        } else {
            dVar.c = new ArrayList(jArr.length);
            for (long j : jArr) {
                dVar.c.add(new fj3(coreImpl, j));
            }
        }
        return resultAnd;
    }

    public dh1(ut0 ut0Var) {
        super(ut0Var);
    }
}
