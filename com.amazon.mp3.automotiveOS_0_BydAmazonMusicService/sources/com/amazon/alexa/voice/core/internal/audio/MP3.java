package com.amazon.alexa.voice.core.internal.audio;

import com.amazon.alexa.voice.core.Logger;
import com.fasterxml.jackson.dataformat.cbor.CBORConstants;
import com.visualon.OSMPUtils.voOSType;

/* JADX INFO: loaded from: classes2.dex */
public final class MP3 {
    private static final int[] SAMPLE_RATE_MPEG_1 = {44100, 48000, 32000, -1};
    private static final int[] SAMPLE_RATE_MPEG_2 = {22050, 24000, 16000, -1};
    private static final int[] SAMPLE_RATE_MPEG_25 = {11025, 12000, 8000, -1};
    private static final int[] BITRATE_MPEG_1_LAYER_1 = {0, 32, 64, 96, 128, CBORConstants.PREFIX_TYPE_OBJECT, 192, 224, 256, 288, 320, 352, 384, 416, 448, -1};
    private static final int[] BITRATE_MPEG_1_LAYER_2 = {0, 32, 48, 56, 64, 80, 96, 112, 128, CBORConstants.PREFIX_TYPE_OBJECT, 192, 224, 256, 320, 384, -1};
    private static final int[] BITRATE_MPEG_1_LAYER_3 = {0, 32, 40, 48, 56, 64, 80, 96, 112, 128, CBORConstants.PREFIX_TYPE_OBJECT, 192, 224, 256, 320, -1};
    private static final int[] BITRATE_MPEG_2_LAYER_1 = {0, 32, 48, 56, 64, 80, 96, 112, 128, voOSType.VOOSMP_PID_ANALYTICS_AGENT, CBORConstants.PREFIX_TYPE_OBJECT, 176, 192, 224, 256, -1};
    private static final int[] BITRATE_MPEG_2_LAYER_2_3 = {0, 8, 16, 24, 32, 40, 48, 56, 64, 80, 96, 112, 128, voOSType.VOOSMP_PID_ANALYTICS_AGENT, CBORConstants.PREFIX_TYPE_OBJECT, -1};

    private static int getPadding(int i, int i2) {
        if (i2 == 0) {
            return 0;
        }
        if (i == 3) {
            return 4;
        }
        return (i == 2 || i == 1) ? 1 : 0;
    }

    private static int getSamplesPerFrame(int i, int i2) {
        if (i2 == 3) {
            return 384;
        }
        if (i2 == 2) {
            return 1152;
        }
        if (i2 != 1) {
            return -1;
        }
        if (i == 2 || i == 0) {
            return 576;
        }
        return i == 3 ? 1152 : -1;
    }

    private MP3() {
        throw new IllegalStateException("No instances!");
    }

    public static long getDurationMillis(byte[] bArr, int i, int i2) {
        int i3;
        int i4;
        if (i2 < 10) {
            return -1L;
        }
        int i5 = i + 1;
        if (bArr[i] == 73) {
            int i6 = i5 + 1;
            if (bArr[i5] == 68) {
                int i7 = i6 + 1;
                if (bArr[i6] == 51) {
                    int i8 = i7 + 1;
                    byte b = 255;
                    int i9 = bArr[i7] & 255;
                    int i10 = i8 + 1;
                    int i11 = bArr[i8] & 255;
                    int i12 = i10 + 1;
                    int i13 = bArr[i10] & 255;
                    int i14 = 3;
                    boolean z = false;
                    int i15 = 2;
                    int i16 = 1;
                    if (i9 < 3 || (i13 & 15) != 0) {
                        Logger.debug("ID3 v2.%d.%d is not supported", Integer.valueOf(i9), Integer.valueOf(i11));
                        return -1L;
                    }
                    Logger.debug("ID3 v2.%d.%d %d bytes", Integer.valueOf(i9), Integer.valueOf(i11), Integer.valueOf(i2));
                    if ((i13 & 128) != 0) {
                        return -1L;
                    }
                    int i17 = 4;
                    if (i9 >= 4 && (i13 & 16) != 0) {
                        z = true;
                    }
                    int syncsafeInt = i12 + 4 + getSyncsafeInt(bArr, i12);
                    if (z) {
                        syncsafeInt += 10;
                    }
                    int i18 = i2 - (syncsafeInt - i);
                    if (i18 <= 0) {
                        return -1L;
                    }
                    long j = 0;
                    while (i18 > 0) {
                        int i19 = bArr[syncsafeInt] & b;
                        int i20 = syncsafeInt + i16;
                        int i21 = bArr[i20];
                        int i22 = i21 & 224;
                        int i23 = (i21 & 24) >> i14;
                        int i24 = (i21 & 6) >> i16;
                        int i25 = i20 + i16;
                        int i26 = bArr[i25];
                        int i27 = (i26 & 240) >> i17;
                        int i28 = (i26 & 12) >> i15;
                        int i29 = (i26 & i15) >> i16;
                        int i30 = i25 + i16;
                        byte b2 = bArr[i30];
                        int i31 = i30 + i16;
                        if (i19 != b || i22 != 224) {
                            return -1L;
                        }
                        int padding = getPadding(i24, i29);
                        int bitrate = getBitrate(i23, i24, i27);
                        if (bitrate == -1 || bitrate == 0) {
                            return -1L;
                        }
                        int i32 = bitrate * 1000;
                        int sampleRate = getSampleRate(i23, i28);
                        if (sampleRate == -1) {
                            return -1L;
                        }
                        int samplesPerFrame = getSamplesPerFrame(i23, i24);
                        j += (((long) samplesPerFrame) * 1000) / ((long) sampleRate);
                        if (i24 == 3) {
                            i3 = 4;
                            i4 = ((((samplesPerFrame / 8) * i32) / sampleRate) + padding) * 4;
                        } else {
                            i3 = 4;
                            if (i24 != 2 && i24 != 1) {
                                return -1L;
                            }
                            i4 = (((samplesPerFrame / 8) * i32) / sampleRate) + padding;
                        }
                        syncsafeInt = i31 + (i4 - 4);
                        i18 -= i4;
                        i14 = 3;
                        i17 = i3;
                        b = 255;
                        i15 = 2;
                        i16 = 1;
                    }
                    return j;
                }
            }
        }
        return -1L;
    }

    private static int getSampleRate(int i, int i2) {
        if (i == 3) {
            return SAMPLE_RATE_MPEG_1[i2];
        }
        if (i == 2) {
            return SAMPLE_RATE_MPEG_2[i2];
        }
        if (i == 0) {
            return SAMPLE_RATE_MPEG_25[i2];
        }
        return -1;
    }

    private static int getBitrate(int i, int i2, int i3) {
        if (i == 3) {
            if (i2 == 3) {
                return BITRATE_MPEG_1_LAYER_1[i3];
            }
            if (i2 == 2) {
                return BITRATE_MPEG_1_LAYER_2[i3];
            }
            if (i2 == 1) {
                return BITRATE_MPEG_1_LAYER_3[i3];
            }
        }
        if (i != 2 && i != 0) {
            return -1;
        }
        if (i2 == 3) {
            return BITRATE_MPEG_2_LAYER_1[i3];
        }
        if (i2 == 2 || i2 == 1) {
            return BITRATE_MPEG_2_LAYER_2_3[i3];
        }
        return -1;
    }

    private static int getSyncsafeInt(byte[] bArr, int i) {
        return (bArr[i + 3] & 127) | ((bArr[i] & 127) << 21) | ((bArr[i + 1] & 127) << 14) | ((bArr[i + 2] & 127) << 7);
    }
}
