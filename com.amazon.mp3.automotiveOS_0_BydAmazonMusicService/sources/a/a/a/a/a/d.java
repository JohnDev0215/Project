package a.a.a.a.a;

import a.a.a.a.a.c;
import android.media.MediaCodec;
import java.nio.ByteBuffer;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: loaded from: classes.dex */
public final class d implements c.b {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final /* synthetic */ c f14a;

    public d(c cVar) {
        this.f14a = cVar;
    }

    @Override // a.a.a.a.a.c.b
    public void run() {
        boolean zIsEmpty;
        a aVarPoll;
        a.b.a.a.n.c<a> cVar = this.f14a.e;
        synchronized (cVar) {
            zIsEmpty = cVar.f87a.isEmpty();
        }
        if (zIsEmpty) {
            return;
        }
        try {
            int iDequeueInputBuffer = this.f14a.d.dequeueInputBuffer(0L);
            if (iDequeueInputBuffer >= 0) {
                a.b.a.a.n.c<a> cVar2 = this.f14a.e;
                synchronized (cVar2) {
                    aVarPoll = cVar2.f87a.poll();
                }
                if (aVarPoll == null) {
                    Intrinsics.throwNpe();
                }
                a aVar = aVarPoll;
                b bVar = aVar.f8a;
                if (bVar != b.COMPRESSED_DATA) {
                    if (bVar == b.END_OF_STREAM) {
                        this.f14a.d.queueInputBuffer(iDequeueInputBuffer, 0, 0, 0L, 4);
                        return;
                    }
                    return;
                }
                ByteBuffer inputBuffer = this.f14a.d.getInputBuffer(iDequeueInputBuffer);
                int iPosition = inputBuffer != null ? inputBuffer.position() : 0;
                if (inputBuffer != null) {
                    byte[] bArr = aVar.b;
                    if (bArr == null) {
                        Intrinsics.throwNpe();
                    }
                    inputBuffer.put(bArr);
                }
                MediaCodec mediaCodec = this.f14a.d;
                byte[] bArr2 = aVar.b;
                if (bArr2 == null) {
                    Intrinsics.throwNpe();
                }
                mediaCodec.queueInputBuffer(iDequeueInputBuffer, iPosition, bArr2.length, aVar.c, 0);
            }
        } catch (IllegalStateException unused) {
            this.f14a.h.set(true);
            c cVar3 = this.f14a;
            cVar3.c.e(cVar3.b, "decoder not in the executing state, shouldn't happen");
        }
    }
}
