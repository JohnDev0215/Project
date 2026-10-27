package defpackage;

import android.media.MediaCodec;
import android.util.Log;
import android.util.SparseArray;
import java.nio.ByteBuffer;
import org.chromium.media.MediaCodecBridge;

/* JADX INFO: compiled from: MediaCodecEncoder.java */
/* JADX INFO: loaded from: classes3.dex */
public final class ad1 extends MediaCodecBridge {
    public final SparseArray<ByteBuffer> o;
    public ByteBuffer p;

    public ad1(MediaCodec mediaCodec, int i) {
        super(mediaCodec, i, false);
        this.o = new SparseArray<>();
    }

    @Override // org.chromium.media.MediaCodecBridge
    public final int b(MediaCodec.BufferInfo bufferInfo, long j) {
        ByteBuffer byteBuffer;
        int iDequeueOutputBuffer = -1;
        try {
            iDequeueOutputBuffer = this.a.dequeueOutputBuffer(bufferInfo, j);
            if (iDequeueOutputBuffer >= 0) {
                if ((bufferInfo.flags & 2) != 0) {
                    ByteBuffer outputBuffer = super.getOutputBuffer(iDequeueOutputBuffer);
                    if (outputBuffer == null) {
                        throw new IllegalStateException("Got null output buffer");
                    }
                    ByteBuffer byteBufferAllocateDirect = ByteBuffer.allocateDirect(bufferInfo.size);
                    this.p = byteBufferAllocateDirect;
                    byteBufferAllocateDirect.put(outputBuffer);
                    StringBuilder sb = new StringBuilder();
                    int i = 0;
                    while (true) {
                        int i2 = bufferInfo.size;
                        if (i2 >= 8) {
                            i2 = 8;
                        }
                        if (i >= i2) {
                            break;
                        }
                        sb.append(Integer.toHexString(this.p.get(i) & 255));
                        sb.append(" ");
                        i++;
                    }
                    fa1.g("MediaCodecEncoder", "spsData: %s", sb.toString());
                    this.a.releaseOutputBuffer(iDequeueOutputBuffer, false);
                    iDequeueOutputBuffer = this.a.dequeueOutputBuffer(bufferInfo, j);
                }
            }
            if (iDequeueOutputBuffer >= 0) {
                ByteBuffer outputBuffer2 = super.getOutputBuffer(iDequeueOutputBuffer);
                if (outputBuffer2 == null) {
                    throw new IllegalStateException("Got null output buffer");
                }
                boolean z = (bufferInfo.flags & 1) != 0;
                SparseArray<ByteBuffer> sparseArray = this.o;
                if (!z || (byteBuffer = this.p) == null) {
                    sparseArray.put(iDequeueOutputBuffer, outputBuffer2);
                } else {
                    byteBuffer.capacity();
                    ByteBuffer byteBufferAllocateDirect2 = ByteBuffer.allocateDirect(this.p.capacity() + bufferInfo.size);
                    byteBufferAllocateDirect2.put(this.p);
                    byteBufferAllocateDirect2.put(outputBuffer2);
                    bufferInfo.offset = 0;
                    bufferInfo.size += this.p.capacity();
                    sparseArray.put(iDequeueOutputBuffer, byteBufferAllocateDirect2);
                }
            }
        } catch (IllegalStateException e) {
            Log.e("cr_MediaCodecEncoder", "Failed to dequeue output buffer", e);
        }
        return iDequeueOutputBuffer;
    }

    @Override // org.chromium.media.MediaCodecBridge
    public final ByteBuffer getOutputBuffer(int i) {
        return this.o.get(i);
    }

    @Override // org.chromium.media.MediaCodecBridge
    public final void releaseOutputBuffer(int i, boolean z) {
        try {
            this.a.releaseOutputBuffer(i, z);
            this.o.remove(i);
        } catch (IllegalStateException e) {
            Log.e("cr_MediaCodecEncoder", "Failed to release output buffer", e);
        }
    }
}
