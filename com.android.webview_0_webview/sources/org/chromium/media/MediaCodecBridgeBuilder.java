package org.chromium.media;

import J.N;
import android.media.MediaCodec;
import android.media.MediaCrypto;
import android.media.MediaFormat;
import android.os.Build;
import android.view.Surface;
import defpackage.C0876hH;
import defpackage.C0987jH;
import defpackage.EH;
import defpackage.Ts;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;

/* JADX INFO: compiled from: chromium-SystemWebView.apk-default-410412053 */
/* JADX INFO: loaded from: classes.dex */
public class MediaCodecBridgeBuilder {
    public static MediaCodecBridge createAudioDecoder(String str, MediaCrypto mediaCrypto, int i, int i2, byte[] bArr, byte[] bArr2, byte[] bArr3, boolean z, boolean z2) {
        C0987jH c0987jH = new C0987jH();
        boolean z3 = true;
        try {
            Ts.d("MediaCodecBridge", "create MediaCodec audio decoder, mime %s", str);
            c0987jH = MediaCodecUtil.c(str, 0, mediaCrypto);
        } catch (Exception e) {
            Ts.a("MediaCodecBridge", "Failed to create MediaCodec audio decoder: %s", str, e);
        }
        MediaCodec mediaCodec = c0987jH.a;
        if (mediaCodec == null) {
            return null;
        }
        MediaCodecBridge mediaCodecBridge = new MediaCodecBridge(mediaCodec, c0987jH.c, z2);
        MediaFormat mediaFormatCreateAudioFormat = MediaFormat.createAudioFormat(str, i, i2);
        EH.b(mediaFormatCreateAudioFormat, new byte[][]{bArr, bArr2, bArr3});
        if (z) {
            mediaFormatCreateAudioFormat.setInteger("is-adts", 1);
        }
        try {
            mediaCodecBridge.a.configure(mediaFormatCreateAudioFormat, (Surface) null, mediaCrypto, 0);
        } catch (MediaCodec.CryptoException e2) {
            Ts.a("MediaCodecBridge", "Cannot configure the audio codec: DRM error", e2);
            z3 = false;
        } catch (IllegalArgumentException e3) {
            Ts.a("MediaCodecBridge", "Cannot configure the audio codec", e3);
            z3 = false;
        } catch (IllegalStateException e4) {
            Ts.a("MediaCodecBridge", "Cannot configure the audio codec", e4);
            z3 = false;
        } catch (Exception e5) {
            Ts.a("MediaCodecBridge", "Cannot configure the audio codec", e5);
            z3 = false;
        }
        if (!z3) {
            return null;
        }
        if (mediaCodecBridge.f()) {
            return mediaCodecBridge;
        }
        mediaCodecBridge.release();
        return null;
    }

    public static MediaCodecBridge createVideoDecoder(String str, int i, MediaCrypto mediaCrypto, int i2, int i3, Surface surface, byte[] bArr, byte[] bArr2, HdrMetadata hdrMetadata, boolean z, boolean z2) {
        int i4;
        C0987jH c0987jH = new C0987jH();
        int i5 = 3;
        try {
            Ts.d("MediaCodecBridge", "create MediaCodec video decoder, mime %s", str);
            c0987jH = MediaCodecUtil.c(str, i, mediaCrypto);
        } catch (Exception e) {
            Ts.a("MediaCodecBridge", "Failed to create MediaCodec video decoder: %s, codecType: %d", str, Integer.valueOf(i), e);
        }
        MediaCodec mediaCodec = c0987jH.a;
        if (mediaCodec == null) {
            return null;
        }
        MediaCodecBridge mediaCodecBridge = new MediaCodecBridge(mediaCodec, c0987jH.c, z2);
        byte[][] bArr3 = {bArr, bArr2};
        boolean z3 = c0987jH.b && z;
        MediaFormat mediaFormatCreateVideoFormat = MediaFormat.createVideoFormat(str, i2, i3);
        if (mediaFormatCreateVideoFormat == null) {
            mediaFormatCreateVideoFormat = null;
        } else {
            EH.b(mediaFormatCreateVideoFormat, bArr3);
            if (hdrMetadata != null) {
                synchronized (hdrMetadata.b) {
                    if (Build.VERSION.SDK_INT < 24) {
                        Ts.a("HdrMetadata", "HDR not supported before Android N", new Object[0]);
                    } else {
                        int iM6EY_9Mw = N.M6EY_9Mw(hdrMetadata.a, hdrMetadata);
                        if (iM6EY_9Mw == 1) {
                            i4 = 1;
                        } else if (iM6EY_9Mw != 9) {
                            i4 = 4;
                            if (iM6EY_9Mw != 4 && iM6EY_9Mw != 5 && iM6EY_9Mw != 6 && iM6EY_9Mw != 7) {
                                i4 = -1;
                            }
                        } else {
                            i4 = 6;
                        }
                        if (i4 != -1) {
                            mediaFormatCreateVideoFormat.setInteger("color-standard", i4);
                        }
                        int iMyx2EYmS = N.Myx2EYmS(hdrMetadata.a, hdrMetadata);
                        if (iMyx2EYmS != 1) {
                            if (iMyx2EYmS == 16) {
                                i5 = 6;
                            } else if (iMyx2EYmS == 18) {
                                i5 = 7;
                            } else if (iMyx2EYmS != 6 && iMyx2EYmS != 7) {
                                i5 = iMyx2EYmS != 8 ? -1 : 1;
                            }
                        }
                        if (i5 != -1) {
                            mediaFormatCreateVideoFormat.setInteger("color-transfer", i5);
                        }
                        int iMGKRBsYQ = N.MGKRBsYQ(hdrMetadata.a, hdrMetadata);
                        int i6 = iMGKRBsYQ != 1 ? iMGKRBsYQ != 2 ? -1 : 1 : 2;
                        if (i6 != -1) {
                            mediaFormatCreateVideoFormat.setInteger("color-range", i6);
                        }
                        ByteBuffer byteBufferWrap = ByteBuffer.wrap(new byte[25]);
                        byteBufferWrap.order(ByteOrder.LITTLE_ENDIAN);
                        byteBufferWrap.put((byte) 0);
                        byteBufferWrap.putShort((short) ((N.Mc0wiJ8$(hdrMetadata.a, hdrMetadata) * 50000.0f) + 0.5f));
                        byteBufferWrap.putShort((short) ((N.MYP0SLZ2(hdrMetadata.a, hdrMetadata) * 50000.0f) + 0.5f));
                        byteBufferWrap.putShort((short) ((N.MRQrYl4t(hdrMetadata.a, hdrMetadata) * 50000.0f) + 0.5f));
                        byteBufferWrap.putShort((short) ((N.MtgfnGGz(hdrMetadata.a, hdrMetadata) * 50000.0f) + 0.5f));
                        byteBufferWrap.putShort((short) ((N.McEG$Qxu(hdrMetadata.a, hdrMetadata) * 50000.0f) + 0.5f));
                        byteBufferWrap.putShort((short) ((N.MU6WBitK(hdrMetadata.a, hdrMetadata) * 50000.0f) + 0.5f));
                        byteBufferWrap.putShort((short) ((N.M8pzdQ$h(hdrMetadata.a, hdrMetadata) * 50000.0f) + 0.5f));
                        byteBufferWrap.putShort((short) ((N.MXbY07SE(hdrMetadata.a, hdrMetadata) * 50000.0f) + 0.5f));
                        byteBufferWrap.putShort((short) (N.MOL90CVq(hdrMetadata.a, hdrMetadata) + 0.5f));
                        byteBufferWrap.putShort((short) (N.MM07Xk18(hdrMetadata.a, hdrMetadata) + 0.5f));
                        byteBufferWrap.putShort((short) N.MMmvZ$nS(hdrMetadata.a, hdrMetadata));
                        byteBufferWrap.putShort((short) N.MzRCryEE(hdrMetadata.a, hdrMetadata));
                        byteBufferWrap.rewind();
                        mediaFormatCreateVideoFormat.setByteBuffer("hdr-static-info", byteBufferWrap);
                    }
                }
            }
            EH.a(mediaFormatCreateVideoFormat, z3);
        }
        if (!mediaCodecBridge.a(mediaFormatCreateVideoFormat, surface, mediaCrypto, 0)) {
            return null;
        }
        if (mediaCodecBridge.f()) {
            return mediaCodecBridge;
        }
        mediaCodecBridge.release();
        return null;
    }

    public static MediaCodecBridge createVideoEncoder(String str, int i, int i2, int i3, int i4, int i5, int i6) {
        C0987jH c0987jH = new C0987jH();
        int iMin = 0;
        try {
            Ts.d("MediaCodecBridge", "create MediaCodec video encoder, mime %s", str);
            C0987jH c0987jH2 = new C0987jH();
            Integer numD = MediaCodecUtil.d(str);
            if (numD != null) {
                try {
                    c0987jH2.a = MediaCodec.createEncoderByType(str);
                    c0987jH2.b = false;
                    int iIntValue = numD.intValue();
                    c0987jH2.c = (iIntValue == 0 || iIntValue == 1 || iIntValue == 2) ? 0 : (iIntValue == 3 || iIntValue == 4) ? 1 : -1;
                } catch (Exception e) {
                    Ts.a("MediaCodecUtil", "Failed to create MediaCodec: %s", str, e);
                }
            }
            c0987jH = c0987jH2;
        } catch (Exception e2) {
            Ts.a("MediaCodecBridge", "Failed to create MediaCodec video encoder: %s", str, e2);
        }
        if (c0987jH.a == null) {
            return null;
        }
        MediaCodecBridge c0876hH = str.equals("video/avc") ? new C0876hH(c0987jH.a, c0987jH.c) : new MediaCodecBridge(c0987jH.a, c0987jH.c, false);
        int i7 = c0987jH.c;
        if (i7 == 0) {
            iMin = Math.min(i4, 30);
        } else if (i7 == 1) {
            iMin = 30;
        }
        boolean z = c0987jH.b;
        MediaFormat mediaFormatCreateVideoFormat = MediaFormat.createVideoFormat(str, i, i2);
        mediaFormatCreateVideoFormat.setInteger("bitrate", i3);
        mediaFormatCreateVideoFormat.setInteger("frame-rate", iMin);
        mediaFormatCreateVideoFormat.setInteger("i-frame-interval", i5);
        mediaFormatCreateVideoFormat.setInteger("color-format", i6);
        EH.a(mediaFormatCreateVideoFormat, z);
        if (!c0876hH.a(mediaFormatCreateVideoFormat, null, null, 1)) {
            return null;
        }
        if (c0876hH.f()) {
            return c0876hH;
        }
        c0876hH.release();
        return null;
    }
}
