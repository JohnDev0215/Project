package com.google.android.exoplayer2.audio;

import com.fasterxml.jackson.dataformat.cbor.CBORConstants;
import com.google.android.exoplayer2.Format;
import com.google.android.exoplayer2.drm.DrmInitData;
import com.google.android.exoplayer2.util.ParsableBitArray;
import com.google.android.exoplayer2.util.ParsableByteArray;
import com.visualon.OSMPUtils.voMimeTypes;
import com.visualon.OSMPUtils.voOSType;
import java.nio.ByteBuffer;

/* JADX INFO: loaded from: classes6.dex */
public final class Ac3Util {
    private static final int[] BLOCKS_PER_SYNCFRAME_BY_NUMBLKSCOD = {1, 2, 3, 6};
    private static final int[] SAMPLE_RATE_BY_FSCOD = {48000, 44100, 32000};
    private static final int[] SAMPLE_RATE_BY_FSCOD2 = {24000, 22050, 16000};
    private static final int[] CHANNEL_COUNT_BY_ACMOD = {2, 1, 2, 3, 3, 4, 4, 5};
    private static final int[] BITRATE_BY_HALF_FRMSIZECOD = {32, 40, 48, 56, 64, 80, 96, 112, 128, CBORConstants.PREFIX_TYPE_OBJECT, 192, 224, 256, 320, 384, 448, 512, 576, 640};
    private static final int[] SYNCFRAME_SIZE_WORDS_BY_HALF_FRMSIZECOD_44_1 = {69, 87, 104, 121, voOSType.VOOSMP_PID_ANALYTICS_EXPORT, 174, 208, 243, 278, 348, 417, 487, 557, 696, 835, 975, 1114, 1253, 1393};

    public static int getAc3SyncframeAudioSampleCount() {
        return 1536;
    }

    public static final class Ac3SyncFrameInfo {
        public final int channelCount;
        public final int frameSize;
        public final String mimeType;
        public final int sampleCount;
        public final int sampleRate;

        private Ac3SyncFrameInfo(String str, int i, int i2, int i3, int i4) {
            this.mimeType = str;
            this.channelCount = i;
            this.sampleRate = i2;
            this.frameSize = i3;
            this.sampleCount = i4;
        }
    }

    public static Format parseAc3AnnexFFormat(ParsableByteArray parsableByteArray, String str, String str2, DrmInitData drmInitData) {
        int i = SAMPLE_RATE_BY_FSCOD[(parsableByteArray.readUnsignedByte() & 192) >> 6];
        int unsignedByte = parsableByteArray.readUnsignedByte();
        int i2 = CHANNEL_COUNT_BY_ACMOD[(unsignedByte & 56) >> 3];
        if ((unsignedByte & 4) != 0) {
            i2++;
        }
        return Format.createAudioSampleFormat(str, voMimeTypes.VOAUDIO_AC3, null, -1, -1, i2, i, null, drmInitData, 0, str2);
    }

    public static Format parseEAc3AnnexFFormat(ParsableByteArray parsableByteArray, String str, String str2, DrmInitData drmInitData) {
        parsableByteArray.skipBytes(2);
        int i = SAMPLE_RATE_BY_FSCOD[(parsableByteArray.readUnsignedByte() & 192) >> 6];
        int unsignedByte = parsableByteArray.readUnsignedByte();
        int i2 = CHANNEL_COUNT_BY_ACMOD[(unsignedByte & 14) >> 1];
        if ((unsignedByte & 1) != 0) {
            i2++;
        }
        return Format.createAudioSampleFormat(str, voMimeTypes.VOAUDIO_E_AC3, null, -1, -1, i2, i, null, drmInitData, 0, str2);
    }

    public static Ac3SyncFrameInfo parseAc3SyncframeInfo(ParsableBitArray parsableBitArray) {
        int bits;
        int i;
        String str;
        int i2;
        int i3;
        int position = parsableBitArray.getPosition();
        parsableBitArray.skipBits(40);
        boolean z = parsableBitArray.readBits(5) == 16;
        parsableBitArray.setPosition(position);
        int i4 = 6;
        if (z) {
            parsableBitArray.skipBits(21);
            int bits2 = (parsableBitArray.readBits(11) + 1) * 2;
            int bits3 = parsableBitArray.readBits(2);
            if (bits3 == 3) {
                i = SAMPLE_RATE_BY_FSCOD2[parsableBitArray.readBits(2)];
            } else {
                i4 = BLOCKS_PER_SYNCFRAME_BY_NUMBLKSCOD[parsableBitArray.readBits(2)];
                i = SAMPLE_RATE_BY_FSCOD[bits3];
            }
            bits = parsableBitArray.readBits(3);
            str = voMimeTypes.VOAUDIO_E_AC3;
            i3 = i4 * 256;
            i2 = bits2;
        } else {
            parsableBitArray.skipBits(32);
            int bits4 = parsableBitArray.readBits(2);
            int ac3SyncframeSize = getAc3SyncframeSize(bits4, parsableBitArray.readBits(6));
            parsableBitArray.skipBits(8);
            bits = parsableBitArray.readBits(3);
            if ((bits & 1) != 0 && bits != 1) {
                parsableBitArray.skipBits(2);
            }
            if ((bits & 4) != 0) {
                parsableBitArray.skipBits(2);
            }
            if (bits == 2) {
                parsableBitArray.skipBits(2);
            }
            i = SAMPLE_RATE_BY_FSCOD[bits4];
            str = voMimeTypes.VOAUDIO_AC3;
            i2 = ac3SyncframeSize;
            i3 = 1536;
        }
        return new Ac3SyncFrameInfo(str, CHANNEL_COUNT_BY_ACMOD[bits] + (parsableBitArray.readBit() ? 1 : 0), i, i2, i3);
    }

    public static int parseAc3SyncframeSize(byte[] bArr) {
        if (bArr.length < 5) {
            return -1;
        }
        byte b = bArr[4];
        return getAc3SyncframeSize((b & 192) >> 6, b & 63);
    }

    public static int parseEAc3SyncframeAudioSampleCount(ByteBuffer byteBuffer) {
        return (((byteBuffer.get(byteBuffer.position() + 4) & 192) >> 6) != 3 ? BLOCKS_PER_SYNCFRAME_BY_NUMBLKSCOD[(byteBuffer.get(byteBuffer.position() + 4) & 48) >> 4] : 6) * 256;
    }

    private static int getAc3SyncframeSize(int i, int i2) {
        int i3 = i2 / 2;
        if (i < 0) {
            return -1;
        }
        int[] iArr = SAMPLE_RATE_BY_FSCOD;
        if (i >= iArr.length || i2 < 0) {
            return -1;
        }
        int[] iArr2 = SYNCFRAME_SIZE_WORDS_BY_HALF_FRMSIZECOD_44_1;
        if (i3 >= iArr2.length) {
            return -1;
        }
        int i4 = iArr[i];
        if (i4 == 44100) {
            return (iArr2[i3] + (i2 % 2)) * 2;
        }
        int i5 = BITRATE_BY_HALF_FRMSIZECOD[i3];
        return i4 == 32000 ? i5 * 6 : i5 * 4;
    }

    private Ac3Util() {
    }
}
