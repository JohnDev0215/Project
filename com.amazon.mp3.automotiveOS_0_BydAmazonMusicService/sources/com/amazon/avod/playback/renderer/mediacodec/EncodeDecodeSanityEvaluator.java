package com.amazon.avod.playback.renderer.mediacodec;

import android.annotation.TargetApi;
import android.media.MediaCodec;
import android.media.MediaCodecInfo;
import android.media.MediaCrypto;
import android.media.MediaFormat;
import android.view.Surface;
import com.amazon.avod.playback.PlaybackException;
import com.amazon.avod.util.DLog;
import com.fasterxml.jackson.dataformat.cbor.CBORConstants;
import com.google.common.base.Preconditions;
import java.nio.ByteBuffer;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;
import java.util.concurrent.TimeUnit;
import javax.annotation.Nonnull;

/* JADX INFO: loaded from: classes2.dex */
@TargetApi(21)
public class EncodeDecodeSanityEvaluator {
    private static final int FRAME_RATE = 15;
    private static final int IFRAME_INTERVAL = 10;
    private static final int MAX_DELTA = 8;
    private static final int NUM_FRAMES = 30;
    private static final int TEST_U = 160;
    private static final int TEST_V = 200;
    private static final int TEST_Y = 120;
    private int mLargestColorDelta;
    private String mMimeType;
    private static final Set SUPPORTED_MIME_TYPES = new HashSet(Arrays.asList("video/avc", "video/hevc"));
    private static final long TIMEOUT_USEC = TimeUnit.MILLISECONDS.toMicros(10);
    private int mWidth = -1;
    private int mHeight = -1;
    private int mBitRate = -1;

    private static boolean isRecognizedFormat(int i) {
        if (i == 39 || i == 2130706688 || i == 2135033992) {
            return true;
        }
        switch (i) {
            case 19:
            case 20:
            case 21:
                return true;
            default:
                return false;
        }
    }

    private static int selectColorFormat(@Nonnull MediaCodecInfo mediaCodecInfo, @Nonnull String str) throws PlaybackException {
        MediaCodecInfo.CodecCapabilities capabilitiesForType = mediaCodecInfo.getCapabilitiesForType(str);
        int i = 0;
        while (true) {
            int[] iArr = capabilitiesForType.colorFormats;
            if (i < iArr.length) {
                int i2 = iArr[i];
                if (isRecognizedFormat(i2)) {
                    return i2;
                }
                i++;
            } else {
                fail("couldn't find a good color format for " + mediaCodecInfo.getName() + " / " + str);
                return 0;
            }
        }
    }

    private static void fail(@Nonnull String str) throws PlaybackException {
        throw new PlaybackException(str);
    }

    private static boolean isSemiPlanarYUV(int i) {
        if (i == 39 || i == 2130706688 || i == 2135033992) {
            return true;
        }
        switch (i) {
            case 19:
            case 20:
                return false;
            case 21:
                return true;
            default:
                throw new IllegalStateException("isSemiPlanarYUV unknown colorFormat " + i);
        }
    }

    private static long computePresentationTimeInMicros(int i) {
        return ((long) i) * (TimeUnit.SECONDS.toMicros(1L) / 15);
    }

    public static boolean isMimeTypeSupported(@Nonnull String str) {
        Preconditions.checkNotNull(str, "mimeType");
        return SUPPORTED_MIME_TYPES.contains(str);
    }

    public void testEncodeDecodeVideoFromBufferToBuffer(@Nonnull String str, boolean z) throws Throwable {
        Preconditions.checkNotNull(str, "mimeType");
        Preconditions.checkState(SUPPORTED_MIME_TYPES.contains(str), String.format("mimeType %s not supported by evaluator", str));
        if (z) {
            setParameters(1920, 1088, 9000000, str);
        } else {
            setParameters(960, 576, 2000000, str);
        }
        encodeDecodeVideoFromBuffer();
    }

    private void setParameters(int i, int i2, int i3, @Nonnull String str) {
        if (i % 16 != 0 || i2 % 16 != 0) {
            DLog.warnf("width or height not multiple of 16");
        }
        this.mWidth = i;
        this.mHeight = i2;
        this.mBitRate = i3;
        this.mMimeType = str;
    }

    /* JADX WARN: Removed duplicated region for block: B:36:0x00c4 A[Catch: Exception -> 0x00c0, TRY_LEAVE, TryCatch #0 {Exception -> 0x00c0, blocks: (B:32:0x00b9, B:36:0x00c4), top: B:41:0x00b9 }] */
    /* JADX WARN: Removed duplicated region for block: B:41:0x00b9 A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    private void encodeDecodeVideoFromBuffer() throws java.lang.Throwable {
        /*
            Method dump skipped, instruction units count: 231
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.amazon.avod.playback.renderer.mediacodec.EncodeDecodeSanityEvaluator.encodeDecodeVideoFromBuffer():void");
    }

    private void doEncodeDecodeVideoFromBuffer(@Nonnull MediaCodec mediaCodec, int i, @Nonnull MediaCodec mediaCodec2) throws PlaybackException {
        MediaFormat mediaFormat;
        int i2;
        int i3;
        int i4;
        int i5;
        boolean z;
        boolean z2;
        char c;
        boolean z3;
        int iDequeueOutputBuffer;
        boolean z4;
        Object obj;
        boolean z5;
        int i6;
        int i7;
        boolean z6;
        ByteBuffer[] inputBuffers = mediaCodec.getInputBuffers();
        ByteBuffer[] outputBuffers = mediaCodec.getOutputBuffers();
        MediaCodec.BufferInfo bufferInfo = new MediaCodec.BufferInfo();
        int i8 = ((this.mWidth * this.mHeight) * 3) / 2;
        byte[] bArr = new byte[i8];
        boolean z7 = false;
        ByteBuffer[] outputBuffers2 = outputBuffers;
        int i9 = 0;
        int i10 = 0;
        int i11 = 0;
        boolean z8 = false;
        boolean z9 = false;
        boolean z10 = false;
        boolean z11 = false;
        MediaFormat outputFormat = null;
        ByteBuffer[] outputBuffers3 = null;
        ByteBuffer[] inputBuffers2 = null;
        while (!z8) {
            if (z9) {
                mediaFormat = outputFormat;
                i2 = i9;
                i3 = i10;
                i4 = i11;
                i5 = i8;
                z = z9;
            } else {
                int i12 = i8;
                int iDequeueInputBuffer = mediaCodec.dequeueInputBuffer(TIMEOUT_USEC);
                if (iDequeueInputBuffer >= 0) {
                    long jComputePresentationTimeInMicros = computePresentationTimeInMicros(i9);
                    if (i9 == 30) {
                        mediaFormat = outputFormat;
                        i7 = i9;
                        i3 = i10;
                        i4 = i11;
                        mediaCodec.queueInputBuffer(iDequeueInputBuffer, 0, 0, jComputePresentationTimeInMicros, 4);
                        z6 = true;
                        i5 = i12;
                    } else {
                        mediaFormat = outputFormat;
                        i7 = i9;
                        i3 = i10;
                        i4 = i11;
                        boolean z12 = z7;
                        generateFrame(i7, i, bArr);
                        ByteBuffer byteBuffer = inputBuffers[iDequeueInputBuffer];
                        if (byteBuffer.capacity() < i12) {
                            Object[] objArr = new Object[2];
                            objArr[z12 ? 1 : 0] = Integer.valueOf(byteBuffer.capacity());
                            objArr[1] = Integer.valueOf(i12);
                            fail(String.format("inputBuf.capacity() %s < frameData.length %s", objArr));
                        }
                        byteBuffer.clear();
                        byteBuffer.put(bArr);
                        i5 = i12;
                        mediaCodec.queueInputBuffer(iDequeueInputBuffer, 0, i12, jComputePresentationTimeInMicros, 0);
                        z6 = z9;
                    }
                    i2 = i7 + 1;
                    z = z6;
                } else {
                    mediaFormat = outputFormat;
                    i2 = i9;
                    i3 = i10;
                    i4 = i11;
                    i5 = i12;
                    z = z9;
                }
            }
            if (z10 || (iDequeueOutputBuffer = mediaCodec.dequeueOutputBuffer(bufferInfo, TIMEOUT_USEC)) == -1) {
                z2 = false;
                c = 2;
                z3 = z11;
            } else {
                if (iDequeueOutputBuffer == -3) {
                    outputBuffers2 = mediaCodec.getOutputBuffers();
                } else if (iDequeueOutputBuffer == -2) {
                    mediaCodec.getOutputFormat();
                } else if (iDequeueOutputBuffer < 0) {
                    fail("unexpected result from encoder.dequeueOutputBuffer: " + iDequeueOutputBuffer);
                } else {
                    ByteBuffer byteBuffer2 = outputBuffers2[iDequeueOutputBuffer];
                    if (byteBuffer2 == null) {
                        fail("encoderOutputBuffer " + iDequeueOutputBuffer + " was null");
                    }
                    byteBuffer2.position(bufferInfo.offset);
                    byteBuffer2.limit(bufferInfo.offset + bufferInfo.size);
                    if (z11) {
                        z4 = false;
                        obj = null;
                        z5 = z11;
                    } else {
                        if ((bufferInfo.flags & 2) == 0) {
                            fail("encoder (info.flags & MediaCodec.BUFFER_FLAG_CODEC_CONFIG) == 0");
                        }
                        MediaFormat mediaFormatCreateVideoFormat = MediaFormat.createVideoFormat(this.mMimeType, this.mWidth, this.mHeight);
                        if ((bufferInfo.flags & 2) != 0) {
                            mediaFormatCreateVideoFormat.setByteBuffer("csd-0", byteBuffer2);
                        }
                        z4 = false;
                        obj = null;
                        mediaCodec2.configure(mediaFormatCreateVideoFormat, (Surface) null, (MediaCrypto) null, 0);
                        mediaCodec2.start();
                        inputBuffers2 = mediaCodec2.getInputBuffers();
                        outputBuffers3 = mediaCodec2.getOutputBuffers();
                        z5 = true;
                    }
                    c = 2;
                    if ((bufferInfo.flags & 2) == 0) {
                        if (!z5) {
                            fail("decoder not configured");
                        }
                        int iDequeueInputBuffer2 = mediaCodec2.dequeueInputBuffer(-1L);
                        ByteBuffer byteBuffer3 = inputBuffers2[iDequeueInputBuffer2];
                        byteBuffer3.clear();
                        byteBuffer3.put(byteBuffer2);
                        z2 = z4;
                        mediaCodec2.queueInputBuffer(iDequeueInputBuffer2, 0, bufferInfo.size, bufferInfo.presentationTimeUs, bufferInfo.flags);
                        z10 = (bufferInfo.flags & 4) != 0 ? true : z2;
                        i6 = iDequeueOutputBuffer;
                    } else {
                        z2 = z4;
                        i6 = iDequeueOutputBuffer;
                    }
                    mediaCodec.releaseOutputBuffer(i6, z2);
                    z3 = z5;
                }
                z2 = false;
                c = 2;
                z3 = z11;
            }
            if (z3) {
                int iDequeueOutputBuffer2 = mediaCodec2.dequeueOutputBuffer(bufferInfo, TIMEOUT_USEC);
                if (iDequeueOutputBuffer2 != -1) {
                    if (iDequeueOutputBuffer2 == -3) {
                        outputBuffers3 = mediaCodec2.getOutputBuffers();
                    } else if (iDequeueOutputBuffer2 == -2) {
                        outputFormat = mediaCodec2.getOutputFormat();
                        i10 = i3;
                        i11 = i4;
                    } else if (iDequeueOutputBuffer2 < 0) {
                        fail("unexpected result from deocder.dequeueOutputBuffer: " + iDequeueOutputBuffer2);
                    } else {
                        ByteBuffer byteBuffer4 = outputBuffers3[iDequeueOutputBuffer2];
                        byteBuffer4.position(bufferInfo.offset);
                        byteBuffer4.limit(bufferInfo.offset + bufferInfo.size);
                        if (bufferInfo.size != 0) {
                            if (computePresentationTimeInMicros(i3) != bufferInfo.presentationTimeUs) {
                                fail("decoder wrong time stamp computePresentationTimeInMicros(checkIndex) != info.presentationTimeUs");
                            }
                            int i13 = i3;
                            i10 = i13 + 1;
                            outputFormat = mediaFormat;
                            i11 = !checkFrame(i13, outputFormat, byteBuffer4) ? i4 + 1 : i4;
                        } else {
                            outputFormat = mediaFormat;
                            i11 = i4;
                            i10 = i3;
                        }
                        z8 = z8;
                        if ((bufferInfo.flags & 4) != 0) {
                            z8 = true;
                        }
                        mediaCodec2.releaseOutputBuffer(iDequeueOutputBuffer2, z2);
                    }
                    i10 = i3;
                    outputFormat = mediaFormat;
                    i11 = i4;
                } else {
                    i10 = i3;
                    outputFormat = mediaFormat;
                    i11 = i4;
                }
            } else {
                outputFormat = mediaFormat;
                i11 = i4;
                i10 = i3;
            }
            z7 = z2;
            i9 = i2;
            i8 = i5;
            z8 = z8;
            z9 = z;
            z11 = z3;
        }
        int i14 = i10;
        int i15 = i11;
        if (i14 != 30) {
            fail("Expected 30 frames, only decoded " + i14);
        }
        if (i15 != 0) {
            fail("Found " + i15 + " bad frames");
        }
    }

    private void generateFrame(int i, int i2, byte[] bArr) {
        int i3;
        int i4 = this.mWidth / 2;
        boolean zIsSemiPlanarYUV = isSemiPlanarYUV(i2);
        int i5 = 0;
        Arrays.fill(bArr, (byte) 0);
        int i6 = i % 8;
        if (i6 < 4) {
            i3 = i6 * (this.mWidth / 4);
        } else {
            i3 = (7 - i6) * (this.mWidth / 4);
            i5 = this.mHeight / 2;
        }
        for (int i7 = ((this.mHeight / 2) + i5) - 1; i7 >= i5; i7--) {
            for (int i8 = ((this.mWidth / 4) + i3) - 1; i8 >= i3; i8--) {
                if (zIsSemiPlanarYUV) {
                    int i9 = this.mWidth;
                    bArr[(i7 * i9) + i8] = CBORConstants.BYTE_STRING_1BYTE_LEN;
                    if ((i8 & 1) == 0 && (i7 & 1) == 0) {
                        int i10 = this.mHeight;
                        int i11 = i7 * i4;
                        bArr[(i9 * i10) + i11 + i8] = -96;
                        bArr[(i9 * i10) + i11 + i8 + 1] = -56;
                    }
                } else {
                    int i12 = this.mWidth;
                    bArr[(i7 * i12) + i8] = CBORConstants.BYTE_STRING_1BYTE_LEN;
                    if ((i8 & 1) == 0 && (i7 & 1) == 0) {
                        int i13 = this.mHeight;
                        int i14 = (i7 / 2) * i4;
                        int i15 = i8 / 2;
                        bArr[(i12 * i13) + i14 + i15] = -96;
                        bArr[(i12 * i13) + ((i13 / 2) * i4) + i14 + i15] = -56;
                    }
                }
            }
        }
    }

    private boolean checkFrame(int i, MediaFormat mediaFormat, ByteBuffer byteBuffer) throws PlaybackException {
        int i2;
        int i3;
        int i4;
        int i5;
        int i6;
        int i7;
        int i8;
        int i9;
        EncodeDecodeSanityEvaluator encodeDecodeSanityEvaluator = this;
        ByteBuffer byteBuffer2 = byteBuffer;
        int integer = mediaFormat.getInteger("color-format");
        if (!isRecognizedFormat(integer)) {
            DLog.logf("unable to check frame contents for colorFormat=" + Integer.toHexString(integer));
            return true;
        }
        boolean zIsSemiPlanarYUV = isSemiPlanarYUV(integer);
        int integer2 = mediaFormat.getInteger("width");
        int integer3 = mediaFormat.getInteger("height");
        int i10 = integer2 / 2;
        int integer4 = mediaFormat.getInteger("crop-left");
        int integer5 = mediaFormat.getInteger("crop-right");
        int integer6 = mediaFormat.getInteger("crop-top");
        int i11 = (integer5 - integer4) + 1;
        int integer7 = (mediaFormat.getInteger("crop-bottom") - integer6) + 1;
        int i12 = encodeDecodeSanityEvaluator.mWidth;
        int i13 = 2;
        if (i12 != i11) {
            fail(String.format("badframe width %s != cropWidth %s", Integer.valueOf(i12), Integer.valueOf(i11)));
        }
        int i14 = encodeDecodeSanityEvaluator.mHeight;
        if (i14 != integer7) {
            fail(String.format("badframe height %s != cropHeight %s", Integer.valueOf(i14), Integer.valueOf(integer7)));
        }
        int i15 = 0;
        boolean z = false;
        while (i15 < 8) {
            if (i15 < 4) {
                int i16 = encodeDecodeSanityEvaluator.mWidth;
                i3 = encodeDecodeSanityEvaluator.mHeight / 4;
                i2 = ((i16 / 4) * i15) + (i16 / 8);
            } else {
                int i17 = encodeDecodeSanityEvaluator.mWidth;
                i2 = (i17 / 8) + ((7 - i15) * (i17 / 4));
                i3 = (encodeDecodeSanityEvaluator.mHeight * 3) / 4;
            }
            int i18 = i3 + integer6;
            int i19 = i2 + integer4;
            int iPosition = byteBuffer.position();
            if (zIsSemiPlanarYUV) {
                i4 = byteBuffer2.get((i18 * integer2) + iPosition + i19) & 255;
                int i20 = iPosition + (integer2 * integer3) + ((i18 / 2) * i13 * i10) + ((i19 / 2) * i13);
                i6 = byteBuffer2.get(i20) & 255;
                i5 = byteBuffer2.get(i20 + 1) & 255;
            } else {
                i4 = byteBuffer2.get((i18 * integer2) + iPosition + i19) & 255;
                int i21 = iPosition + (integer2 * integer3);
                int i22 = (i18 / 2) * i10;
                int i23 = i19 / 2;
                int i24 = byteBuffer2.get(i21 + i22 + i23) & 255;
                i5 = byteBuffer2.get(i21 + ((integer3 / 2) * i10) + i22 + i23) & 255;
                i6 = i24;
            }
            if (i15 == i % 8) {
                i8 = 120;
                i9 = 160;
                i7 = 200;
            } else {
                i7 = 0;
                i8 = 0;
                i9 = 0;
            }
            if (!encodeDecodeSanityEvaluator.isColorClose(i4, i8) || !encodeDecodeSanityEvaluator.isColorClose(i6, i9) || !encodeDecodeSanityEvaluator.isColorClose(i5, i7)) {
                DLog.warnf("Bad frame " + i + " (rect=" + i15 + ": yuv=" + i4 + "," + i6 + "," + i5 + " vs. expected " + i8 + "," + i9 + "," + i7 + ")");
                z = true;
            }
            i15++;
            encodeDecodeSanityEvaluator = this;
            byteBuffer2 = byteBuffer;
            i13 = 2;
        }
        return true ^ z;
    }

    private boolean isColorClose(int i, int i2) {
        int iAbs = Math.abs(i - i2);
        if (iAbs > this.mLargestColorDelta) {
            this.mLargestColorDelta = iAbs;
        }
        return iAbs <= 8;
    }
}
