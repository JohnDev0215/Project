package defpackage;

import android.media.MediaCodec;
import android.util.SparseArray;
import java.nio.ByteBuffer;
import org.chromium.media.MediaCodecBridge;

/* JADX INFO: renamed from: hH, reason: case insensitive filesystem */
/* JADX INFO: compiled from: chromium-SystemWebView.apk-default-410412053 */
/* JADX INFO: loaded from: classes.dex */
public class C0876hH extends MediaCodecBridge {
    public SparseArray o;
    public ByteBuffer p;

    public C0876hH(MediaCodec mediaCodec, int i) {
        super(mediaCodec, i, false);
        this.o = new SparseArray();
    }

    @Override // org.chromium.media.MediaCodecBridge
    public int b(MediaCodec.BufferInfo bufferInfo, long j) {
        int iDequeueOutputBuffer = -1;
        try {
            iDequeueOutputBuffer = this.a.dequeueOutputBuffer(bufferInfo, j);
            if (iDequeueOutputBuffer >= 0) {
                if ((bufferInfo.flags & 2) != 0) {
                    ByteBuffer outputBuffer = super.getOutputBuffer(iDequeueOutputBuffer);
                    if (outputBuffer == null) {
                        throw new IllegalStateException("Got null output buffer");
                    }
                    outputBuffer.position(bufferInfo.offset);
                    outputBuffer.limit(bufferInfo.offset + bufferInfo.size);
                    ByteBuffer byteBufferAllocateDirect = ByteBuffer.allocateDirect(bufferInfo.size);
                    this.p = byteBufferAllocateDirect;
                    byteBufferAllocateDirect.put(outputBuffer);
                    StringBuilder sb = new StringBuilder();
                    int i = 0;
                    while (true) {
                        if (i >= (bufferInfo.size < 8 ? bufferInfo.size : 8)) {
                            break;
                        }
                        sb.append(Integer.toHexString(this.p.get(i) & 255));
                        sb.append(" ");
                        i++;
                    }
                    Ts.d("MediaCodecEncoder", "spsData: %s", sb.toString());
                    this.a.releaseOutputBuffer(iDequeueOutputBuffer, false);
                    iDequeueOutputBuffer = this.a.dequeueOutputBuffer(bufferInfo, j);
                }
            }
            if (iDequeueOutputBuffer >= 0) {
                ByteBuffer outputBuffer2 = super.getOutputBuffer(iDequeueOutputBuffer);
                if (outputBuffer2 == null) {
                    throw new IllegalStateException("Got null output buffer");
                }
                outputBuffer2.position(bufferInfo.offset);
                outputBuffer2.limit(bufferInfo.offset + bufferInfo.size);
                if (!((bufferInfo.flags & 1) != 0) || this.p == null) {
                    this.o.put(iDequeueOutputBuffer, outputBuffer2);
                } else {
                    this.p.capacity();
                    ByteBuffer byteBufferAllocateDirect2 = ByteBuffer.allocateDirect(this.p.capacity() + bufferInfo.size);
                    this.p.rewind();
                    byteBufferAllocateDirect2.put(this.p);
                    byteBufferAllocateDirect2.put(outputBuffer2);
                    byteBufferAllocateDirect2.rewind();
                    bufferInfo.offset = 0;
                    bufferInfo.size += this.p.capacity();
                    this.o.put(iDequeueOutputBuffer, byteBufferAllocateDirect2);
                }
            }
        } catch (IllegalStateException e) {
            Ts.a("MediaCodecEncoder", "Failed to dequeue output buffer", e);
        }
        return iDequeueOutputBuffer;
    }

    @Override // org.chromium.media.MediaCodecBridge
    public ByteBuffer getOutputBuffer(int i) {
        return (ByteBuffer) this.o.get(i);
    }

    @Override // org.chromium.media.MediaCodecBridge
    public void releaseOutputBuffer(int i, boolean z) {
        try {
            this.a.releaseOutputBuffer(i, z);
            this.o.remove(i);
        } catch (IllegalStateException e) {
            Ts.a("MediaCodecEncoder", "Failed to release output buffer", e);
        }
    }
}
