package org.chromium.mojo.system.impl;

import J.N;
import defpackage.AbstractC0711eK;
import defpackage.C0767fK;
import defpackage.C0990jK;
import defpackage.LJ;
import defpackage.RJ;
import defpackage.TJ;
import defpackage.WJ;
import defpackage.XJ;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import org.chromium.mojo.system.ResultAnd;

/* JADX INFO: compiled from: chromium-SystemWebView.apk-default-410412053 */
/* JADX INFO: loaded from: classes.dex */
public class CoreImpl implements LJ {
    public final ThreadLocal a = new ThreadLocal();
    public final int b = N.MBIRtXF8(this, ByteBuffer.allocateDirect(8), 8);

    public CoreImpl(AbstractC0711eK abstractC0711eK) {
    }

    public static ResultAnd newNativeCreationResult(int i, int i2, int i3) {
        return new ResultAnd(i, new C0767fK(Integer.valueOf(i2), Integer.valueOf(i3)));
    }

    public static ResultAnd newReadMessageResult(int i, byte[] bArr, int[] iArr) {
        TJ tj = new TJ();
        if (i == 0) {
            tj.a = bArr;
            tj.b = iArr;
        }
        return new ResultAnd(i, tj);
    }

    public static ResultAnd newResultAndBuffer(int i, ByteBuffer byteBuffer) {
        return new ResultAnd(i, byteBuffer);
    }

    public static ResultAnd newResultAndInteger(int i, int i2) {
        return new ResultAnd(i, Integer.valueOf(i2));
    }

    public final ByteBuffer a(int i) {
        ByteBuffer byteBufferAllocateDirect = ByteBuffer.allocateDirect(i + this.b);
        int i2 = this.b;
        if (i2 != 0) {
            byteBufferAllocateDirect.position(i2);
            byteBufferAllocateDirect = byteBufferAllocateDirect.slice();
        }
        return byteBufferAllocateDirect.order(ByteOrder.nativeOrder());
    }

    public XJ b(RJ rj) {
        ResultAnd resultAnd = (ResultAnd) N.MZhgS7uU(this, null);
        if (resultAnd.a == 0) {
            return new XJ(new C0990jK(this, ((Integer) ((C0767fK) resultAnd.b).a).intValue()), new C0990jK(this, ((Integer) ((C0767fK) resultAnd.b).b).intValue()));
        }
        throw new WJ(resultAnd.a);
    }
}
