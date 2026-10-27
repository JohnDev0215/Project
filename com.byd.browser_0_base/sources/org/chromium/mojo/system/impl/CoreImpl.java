package org.chromium.mojo.system.impl;

import J.N;
import defpackage.ch1;
import defpackage.dh1;
import defpackage.k00;
import defpackage.ki1;
import defpackage.pv1;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import org.chromium.base.annotations.CalledByNative;
import org.chromium.mojo.system.ResultAnd;

/* JADX INFO: loaded from: classes3.dex */
public class CoreImpl implements k00 {
    public final int a;

    public static class a {
        public static final CoreImpl a = new CoreImpl();
    }

    public static final class b extends pv1<Long, Long> {
        public b(Long l, Long l2) {
            super(l, l2);
        }
    }

    public CoreImpl() {
        new ThreadLocal();
        this.a = N.MBIRtXF8(this, ByteBuffer.allocateDirect(8), 8);
    }

    @CalledByNative
    public static ResultAnd<b> newNativeCreationResult(int i, long j, long j2) {
        return new ResultAnd<>(i, new b(Long.valueOf(j), Long.valueOf(j2)));
    }

    @CalledByNative
    public static ResultAnd<ch1.d> newReadMessageResult(int i, byte[] bArr, long[] jArr) {
        ch1.d dVar = new ch1.d();
        if (i == 0) {
            dVar.a = bArr;
            dVar.b = jArr;
        }
        return new ResultAnd<>(i, dVar);
    }

    @CalledByNative
    public static ResultAnd<ByteBuffer> newResultAndBuffer(int i, ByteBuffer byteBuffer) {
        return new ResultAnd<>(i, byteBuffer);
    }

    @CalledByNative
    public static ResultAnd<Integer> newResultAndInteger(int i, int i2) {
        return new ResultAnd<>(i, Integer.valueOf(i2));
    }

    @CalledByNative
    public static ResultAnd<Long> newResultAndLong(int i, long j) {
        return new ResultAnd<>(i, Long.valueOf(j));
    }

    public final ByteBuffer a(int i) {
        int i2 = this.a;
        ByteBuffer byteBufferAllocateDirect = ByteBuffer.allocateDirect(i + i2);
        if (i2 != 0) {
            byteBufferAllocateDirect.position(i2);
            byteBufferAllocateDirect = byteBufferAllocateDirect.slice();
        }
        return byteBufferAllocateDirect.order(ByteOrder.nativeOrder());
    }

    public final pv1<ch1, ch1> b(ch1.b bVar) {
        ByteBuffer byteBufferA;
        if (bVar != null) {
            byteBufferA = a(8);
            byteBufferA.putInt(0, 8);
            byteBufferA.putInt(4, bVar.a.a);
        } else {
            byteBufferA = null;
        }
        ResultAnd resultAnd = (ResultAnd) N.MZhgS7uU(this, byteBufferA);
        if (resultAnd.a != 0) {
            throw new ki1(resultAnd.a);
        }
        Object obj = resultAnd.b;
        return new pv1<>(new dh1(this, ((Long) ((b) obj).a).longValue()), new dh1(this, ((Long) ((b) obj).b).longValue()));
    }
}
