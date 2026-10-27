package com.amazon.avod.media.framework.memory;

import com.amazon.avod.util.DLog;
import com.amazon.avod.util.DataUnit;
import java.nio.ByteBuffer;

/* JADX INFO: loaded from: classes2.dex */
public class DoublingGrowableBuffer extends GrowableBuffer {
    public DoublingGrowableBuffer(int i) {
        super(i);
    }

    @Override // com.amazon.avod.media.framework.memory.GrowableBuffer
    public void ensureCapacity(int i) {
        byte[] bArr = this.mBuffer;
        if (bArr.length < i) {
            int length = bArr.length;
            int iMax = Math.max(length, 1);
            do {
                iMax *= 2;
            } while (iMax < i);
            byte[] bArr2 = new byte[iMax];
            System.arraycopy(this.mBuffer, 0, bArr2, 0, length);
            this.mBuffer = bArr2;
            this.mByteBuffer = ByteBuffer.wrap(bArr2);
            DataUnit dataUnit = DataUnit.BYTES;
            DLog.logf("DoublingGrowableBuffer: Increasing capacity from %s MB to %s MB, requested size %s MB", Float.valueOf(dataUnit.toMegaBytes(length)), Float.valueOf(dataUnit.toMegaBytes(this.mBuffer.length)), Float.valueOf(dataUnit.toMegaBytes(i)));
        }
    }
}
