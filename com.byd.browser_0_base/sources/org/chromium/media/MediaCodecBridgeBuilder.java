package org.chromium.media;

import android.media.MediaCodec;
import android.media.MediaCrypto;
import android.media.MediaFormat;
import android.util.Log;
import android.view.Surface;
import defpackage.ad1;
import defpackage.bd1;
import defpackage.fa1;
import org.chromium.base.annotations.CalledByNative;
import org.chromium.media.MediaCodecUtil;

/* JADX INFO: loaded from: classes3.dex */
public class MediaCodecBridgeBuilder {
    @CalledByNative
    public static MediaCodecBridge createAudioDecoder(String str, MediaCrypto mediaCrypto, int i, int i2, byte[] bArr, byte[] bArr2, byte[] bArr3, boolean z, boolean z2) {
        MediaCodecUtil.a aVar = new MediaCodecUtil.a();
        boolean z3 = false;
        try {
            fa1.g("MediaCodecBridge", "create MediaCodec audio decoder, mime %s", str);
            aVar = MediaCodecUtil.b(str, 0, mediaCrypto);
        } catch (Exception e) {
            fa1.c("MediaCodecBridge", "Failed to create MediaCodec audio decoder: %s", str, e);
        }
        MediaCodec mediaCodec = aVar.a;
        if (mediaCodec == null) {
            return null;
        }
        MediaCodecBridge mediaCodecBridge = new MediaCodecBridge(mediaCodec, aVar.c, z2);
        MediaFormat mediaFormatCreateAudioFormat = MediaFormat.createAudioFormat(str, i, i2);
        bd1.c(mediaFormatCreateAudioFormat, new byte[][]{bArr, bArr2, bArr3});
        if (z) {
            mediaFormatCreateAudioFormat.setInteger("is-adts", 1);
        }
        try {
            mediaCodecBridge.a.configure(mediaFormatCreateAudioFormat, (Surface) null, mediaCrypto, 0);
            z3 = true;
        } catch (MediaCodec.CryptoException e2) {
            Log.e("cr_MediaCodecBridge", "Cannot configure the audio codec: DRM error", e2);
        } catch (IllegalArgumentException e3) {
            Log.e("cr_MediaCodecBridge", "Cannot configure the audio codec", e3);
        } catch (IllegalStateException e4) {
            Log.e("cr_MediaCodecBridge", "Cannot configure the audio codec", e4);
        } catch (Exception e5) {
            Log.e("cr_MediaCodecBridge", "Cannot configure the audio codec", e5);
        }
        if (!z3) {
            return null;
        }
        if (mediaCodecBridge.e()) {
            return mediaCodecBridge;
        }
        mediaCodecBridge.release();
        return null;
    }

    @CalledByNative
    public static MediaCodecBridge createVideoDecoder(String str, int i, MediaCrypto mediaCrypto, int i2, int i3, Surface surface, byte[] bArr, byte[] bArr2, HdrMetadata hdrMetadata, boolean z, boolean z2) {
        try {
            fa1.g("MediaCodecBridge", "create MediaCodec video decoder, mime %s", str);
            MediaCodecUtil.a aVarB = MediaCodecUtil.b(str, i, mediaCrypto);
            MediaCodec mediaCodec = aVarB.a;
            if (mediaCodec == null) {
                return null;
            }
            MediaCodecBridge mediaCodecBridge = new MediaCodecBridge(mediaCodec, aVarB.c, z2);
            if (!mediaCodecBridge.a(bd1.b(str, i2, i3, new byte[][]{bArr, bArr2}, hdrMetadata, aVarB.b && z), surface, mediaCrypto, 0)) {
                return null;
            }
            if (mediaCodecBridge.e()) {
                return mediaCodecBridge;
            }
            mediaCodecBridge.release();
            return null;
        } catch (Exception e) {
            fa1.d("MediaCodecBridge", "Failed to create MediaCodec video decoder: %s, codecType: %d", str, Integer.valueOf(i), e);
            return null;
        }
    }

    @CalledByNative
    public static MediaCodecBridge createVideoEncoder(String str, int i, int i2, int i3, int i4, int i5, int i6, int i7) {
        MediaCodecUtil.a aVar = new MediaCodecUtil.a();
        try {
            fa1.g("MediaCodecBridge", "create MediaCodec video encoder, mime %s", str);
            aVar = MediaCodecUtil.c(str);
        } catch (Exception e) {
            fa1.c("MediaCodecBridge", "Failed to create MediaCodec video encoder: %s", str, e);
        }
        if (aVar.a == null) {
            return null;
        }
        int iMin = 0;
        MediaCodecBridge ad1Var = str.equals("video/avc") ? new ad1(aVar.a, aVar.c) : new MediaCodecBridge(aVar.a, aVar.c, false);
        int i8 = aVar.c;
        if (i8 == 0) {
            iMin = Math.min(i5, 30);
        } else if (i8 == 1) {
            iMin = 30;
        }
        boolean z = aVar.b;
        MediaFormat mediaFormatCreateVideoFormat = MediaFormat.createVideoFormat(str, i, i2);
        mediaFormatCreateVideoFormat.setInteger("bitrate", i4);
        mediaFormatCreateVideoFormat.setInteger("frame-rate", iMin);
        mediaFormatCreateVideoFormat.setInteger("i-frame-interval", i6);
        mediaFormatCreateVideoFormat.setInteger("color-format", i7);
        mediaFormatCreateVideoFormat.setInteger("bitrate-mode", i3);
        bd1.a(mediaFormatCreateVideoFormat, z);
        if (!ad1Var.a(mediaFormatCreateVideoFormat, null, null, 1)) {
            return null;
        }
        if (ad1Var.e()) {
            return ad1Var;
        }
        ad1Var.release();
        return null;
    }
}
