package a.a.a.a.a;

import a.a.a.a.a.c;
import android.media.MediaCodec;
import android.media.MediaFormat;
import java.nio.ByteBuffer;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: loaded from: classes.dex */
public final class e implements c.b {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public MediaCodec.BufferInfo f15a = new MediaCodec.BufferInfo();
    public final /* synthetic */ c b;

    public e(c cVar) {
        this.b = cVar;
    }

    @Override // a.a.a.a.a.c.b
    public void run() throws InterruptedException {
        try {
            int iDequeueOutputBuffer = this.b.d.dequeueOutputBuffer(this.f15a, 0L);
            if (iDequeueOutputBuffer >= 0) {
                ByteBuffer outputBuffer = this.b.d.getOutputBuffer(iDequeueOutputBuffer);
                if (outputBuffer != null) {
                    outputBuffer.position(this.f15a.offset);
                }
                byte[] bArr = new byte[this.f15a.size];
                if (outputBuffer != null) {
                    outputBuffer.get(bArr);
                }
                this.b.d.releaseOutputBuffer(iDequeueOutputBuffer, false);
                a.b.a.a.n.c<a> cVar = this.b.f;
                a aVar = new a(b.DECODED_DATA, bArr, this.f15a.presentationTimeUs);
                synchronized (cVar) {
                    cVar.f87a.offer(aVar);
                }
            } else if (iDequeueOutputBuffer == -2) {
                MediaFormat outputFormat = this.b.d.getOutputFormat();
                Intrinsics.checkExpressionValueIsNotNull(outputFormat, "mediaCodec.outputFormat");
                c cVar2 = this.b;
                cVar2.c.a(cVar2.b, "outputFormat: " + outputFormat);
            }
            if ((this.f15a.flags & 4) == 0) {
                return;
            }
            a.b.a.a.n.c<a> cVar3 = this.b.f;
            a aVar2 = new a(b.END_OF_STREAM, null, 0L);
            synchronized (cVar3) {
                cVar3.f87a.offer(aVar2);
            }
            throw new InterruptedException();
        } catch (IllegalStateException unused) {
            c cVar4 = this.b;
            cVar4.c.e(cVar4.b, "decoder not in the executing state, shouldn't happen");
            this.b.h.set(true);
        }
    }
}
