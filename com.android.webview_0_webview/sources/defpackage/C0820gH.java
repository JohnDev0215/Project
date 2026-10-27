package defpackage;

import android.media.MediaCodec;
import android.media.MediaFormat;
import org.chromium.media.MediaCodecBridge;

/* JADX INFO: renamed from: gH, reason: case insensitive filesystem */
/* JADX INFO: compiled from: chromium-SystemWebView.apk-default-410412053 */
/* JADX INFO: loaded from: classes.dex */
public class C0820gH extends MediaCodec.Callback {
    public MediaCodecBridge a;

    public C0820gH(MediaCodecBridge mediaCodecBridge, MediaCodecBridge mediaCodecBridge2) {
        this.a = mediaCodecBridge2;
    }

    @Override // android.media.MediaCodec.Callback
    public void onError(MediaCodec mediaCodec, MediaCodec.CodecException codecException) {
        StringBuilder sbF = Z7.f("MediaCodec.onError: ");
        sbF.append(codecException.getDiagnosticInfo());
        Ts.a("MediaCodecBridge", sbF.toString(), new Object[0]);
        this.a.d();
    }

    @Override // android.media.MediaCodec.Callback
    public void onInputBufferAvailable(MediaCodec mediaCodec, int i) {
        MediaCodecBridge mediaCodecBridge = this.a;
        synchronized (mediaCodecBridge) {
            if (mediaCodecBridge.h) {
                return;
            }
            mediaCodecBridge.k.add(new MediaCodecBridge.DequeueInputResult(0, i, null));
            mediaCodecBridge.c();
        }
    }

    @Override // android.media.MediaCodec.Callback
    public void onOutputBufferAvailable(MediaCodec mediaCodec, int i, MediaCodec.BufferInfo bufferInfo) {
        MediaCodecBridge mediaCodecBridge = this.a;
        synchronized (mediaCodecBridge) {
            if (mediaCodecBridge.h) {
                return;
            }
            mediaCodecBridge.l.add(new MediaCodecBridge.DequeueOutputResult(0, i, bufferInfo.flags, bufferInfo.offset, bufferInfo.presentationTimeUs, bufferInfo.size, null));
            mediaCodecBridge.c();
        }
    }

    @Override // android.media.MediaCodec.Callback
    public void onOutputFormatChanged(MediaCodec mediaCodec, MediaFormat mediaFormat) {
        MediaCodecBridge mediaCodecBridge = this.a;
        synchronized (mediaCodecBridge) {
            mediaCodecBridge.l.add(new MediaCodecBridge.DequeueOutputResult(3, -1, 0, 0, 0L, 0, null));
            mediaCodecBridge.e.add(new MediaCodecBridge.GetOutputFormatResult(0, mediaFormat, null));
            mediaCodecBridge.c();
        }
    }
}
