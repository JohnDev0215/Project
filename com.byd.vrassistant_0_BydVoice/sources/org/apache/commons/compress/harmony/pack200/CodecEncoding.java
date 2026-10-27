package org.apache.commons.compress.harmony.pack200;

import java.io.EOFException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Map;

/* JADX INFO: loaded from: classes4.dex */
public class CodecEncoding {
    private static final BHSDCodec[] canonicalCodec = {null, new BHSDCodec(1, 256), new BHSDCodec(1, 256, 1), new BHSDCodec(1, 256, 0, 1), new BHSDCodec(1, 256, 1, 1), new BHSDCodec(2, 256), new BHSDCodec(2, 256, 1), new BHSDCodec(2, 256, 0, 1), new BHSDCodec(2, 256, 1, 1), new BHSDCodec(3, 256), new BHSDCodec(3, 256, 1), new BHSDCodec(3, 256, 0, 1), new BHSDCodec(3, 256, 1, 1), new BHSDCodec(4, 256), new BHSDCodec(4, 256, 1), new BHSDCodec(4, 256, 0, 1), new BHSDCodec(4, 256, 1, 1), new BHSDCodec(5, 4), new BHSDCodec(5, 4, 1), new BHSDCodec(5, 4, 2), new BHSDCodec(5, 16), new BHSDCodec(5, 16, 1), new BHSDCodec(5, 16, 2), new BHSDCodec(5, 32), new BHSDCodec(5, 32, 1), new BHSDCodec(5, 32, 2), new BHSDCodec(5, 64), new BHSDCodec(5, 64, 1), new BHSDCodec(5, 64, 2), new BHSDCodec(5, 128), new BHSDCodec(5, 128, 1), new BHSDCodec(5, 128, 2), new BHSDCodec(5, 4, 0, 1), new BHSDCodec(5, 4, 1, 1), new BHSDCodec(5, 4, 2, 1), new BHSDCodec(5, 16, 0, 1), new BHSDCodec(5, 16, 1, 1), new BHSDCodec(5, 16, 2, 1), new BHSDCodec(5, 32, 0, 1), new BHSDCodec(5, 32, 1, 1), new BHSDCodec(5, 32, 2, 1), new BHSDCodec(5, 64, 0, 1), new BHSDCodec(5, 64, 1, 1), new BHSDCodec(5, 64, 2, 1), new BHSDCodec(5, 128, 0, 1), new BHSDCodec(5, 128, 1, 1), new BHSDCodec(5, 128, 2, 1), new BHSDCodec(2, 192), new BHSDCodec(2, 224), new BHSDCodec(2, 240), new BHSDCodec(2, 248), new BHSDCodec(2, 252), new BHSDCodec(2, 8, 0, 1), new BHSDCodec(2, 8, 1, 1), new BHSDCodec(2, 16, 0, 1), new BHSDCodec(2, 16, 1, 1), new BHSDCodec(2, 32, 0, 1), new BHSDCodec(2, 32, 1, 1), new BHSDCodec(2, 64, 0, 1), new BHSDCodec(2, 64, 1, 1), new BHSDCodec(2, 128, 0, 1), new BHSDCodec(2, 128, 1, 1), new BHSDCodec(2, 192, 0, 1), new BHSDCodec(2, 192, 1, 1), new BHSDCodec(2, 224, 0, 1), new BHSDCodec(2, 224, 1, 1), new BHSDCodec(2, 240, 0, 1), new BHSDCodec(2, 240, 1, 1), new BHSDCodec(2, 248, 0, 1), new BHSDCodec(2, 248, 1, 1), new BHSDCodec(3, 192), new BHSDCodec(3, 224), new BHSDCodec(3, 240), new BHSDCodec(3, 248), new BHSDCodec(3, 252), new BHSDCodec(3, 8, 0, 1), new BHSDCodec(3, 8, 1, 1), new BHSDCodec(3, 16, 0, 1), new BHSDCodec(3, 16, 1, 1), new BHSDCodec(3, 32, 0, 1), new BHSDCodec(3, 32, 1, 1), new BHSDCodec(3, 64, 0, 1), new BHSDCodec(3, 64, 1, 1), new BHSDCodec(3, 128, 0, 1), new BHSDCodec(3, 128, 1, 1), new BHSDCodec(3, 192, 0, 1), new BHSDCodec(3, 192, 1, 1), new BHSDCodec(3, 224, 0, 1), new BHSDCodec(3, 224, 1, 1), new BHSDCodec(3, 240, 0, 1), new BHSDCodec(3, 240, 1, 1), new BHSDCodec(3, 248, 0, 1), new BHSDCodec(3, 248, 1, 1), new BHSDCodec(4, 192), new BHSDCodec(4, 224), new BHSDCodec(4, 240), new BHSDCodec(4, 248), new BHSDCodec(4, 252), new BHSDCodec(4, 8, 0, 1), new BHSDCodec(4, 8, 1, 1), new BHSDCodec(4, 16, 0, 1), new BHSDCodec(4, 16, 1, 1), new BHSDCodec(4, 32, 0, 1), new BHSDCodec(4, 32, 1, 1), new BHSDCodec(4, 64, 0, 1), new BHSDCodec(4, 64, 1, 1), new BHSDCodec(4, 128, 0, 1), new BHSDCodec(4, 128, 1, 1), new BHSDCodec(4, 192, 0, 1), new BHSDCodec(4, 192, 1, 1), new BHSDCodec(4, 224, 0, 1), new BHSDCodec(4, 224, 1, 1), new BHSDCodec(4, 240, 0, 1), new BHSDCodec(4, 240, 1, 1), new BHSDCodec(4, 248, 0, 1), new BHSDCodec(4, 248, 1, 1)};
    private static Map canonicalCodecsToSpecifiers;

    public static Codec getCodec(int i, InputStream inputStream, Codec codec) throws Pack200Exception, IOException {
        BHSDCodec[] bHSDCodecArr = canonicalCodec;
        if (bHSDCodecArr.length != 116) {
            throw new Error("Canonical encodings have been incorrectly modified");
        }
        if (i < 0) {
            throw new IllegalArgumentException("Encoding cannot be less than zero");
        }
        if (i == 0) {
            return codec;
        }
        if (i <= 115) {
            return bHSDCodecArr[i];
        }
        if (i == 116) {
            int i2 = inputStream.read();
            if (i2 == -1) {
                throw new EOFException("End of buffer read whilst trying to decode codec");
            }
            int i3 = i2 & 1;
            int i4 = (i2 >> 1) & 3;
            int i5 = ((i2 >> 3) & 7) + 1;
            int i6 = inputStream.read();
            if (i6 == -1) {
                throw new EOFException("End of buffer read whilst trying to decode codec");
            }
            return new BHSDCodec(i5, i6 + 1, i4, i3);
        }
        if (i >= 117 && i <= 140) {
            int i7 = i - 117;
            int i8 = i7 & 3;
            boolean z = ((i7 >> 2) & 1) == 1;
            boolean z2 = ((i7 >> 3) & 1) == 1;
            boolean z3 = ((i7 >> 4) & 1) == 1;
            if (z2 && z3) {
                throw new Pack200Exception("ADef and BDef should never both be true");
            }
            int iPow = ((z ? inputStream.read() : 3) + 1) * ((int) Math.pow(16.0d, i8));
            Codec codec2 = z2 ? codec : getCodec(inputStream.read(), inputStream, codec);
            if (!z3) {
                codec = getCodec(inputStream.read(), inputStream, codec);
            }
            return new RunCodec(iPow, codec2, codec);
        }
        if (i < 141 || i > 188) {
            throw new Pack200Exception("Invalid codec encoding byte (" + i + ") found");
        }
        int i9 = i - 141;
        boolean z4 = (i9 & 1) == 1;
        boolean z5 = ((i9 >> 1) & 1) == 1;
        int i10 = i9 >> 2;
        boolean z6 = i10 != 0;
        int i11 = new int[]{0, 4, 8, 16, 32, 64, 128, 192, 224, 240, 248, 252}[i10];
        if (z6) {
            Codec codec3 = z4 ? codec : getCodec(inputStream.read(), inputStream, codec);
            if (!z5) {
                codec = getCodec(inputStream.read(), inputStream, codec);
            }
            return new PopulationCodec(codec3, i11, codec);
        }
        Codec codec4 = z4 ? codec : getCodec(inputStream.read(), inputStream, codec);
        Codec codec5 = getCodec(inputStream.read(), inputStream, codec);
        if (!z5) {
            codec = getCodec(inputStream.read(), inputStream, codec);
        }
        return new PopulationCodec(codec4, codec5, codec);
    }

    public static int getSpecifierForDefaultCodec(BHSDCodec bHSDCodec) {
        return getSpecifier(bHSDCodec, null)[0];
    }

    /* JADX WARN: Removed duplicated region for block: B:76:0x0135  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public static int[] getSpecifier(org.apache.commons.compress.harmony.pack200.Codec r9, org.apache.commons.compress.harmony.pack200.Codec r10) {
        /*
            Method dump skipped, instruction units count: 422
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: org.apache.commons.compress.harmony.pack200.CodecEncoding.getSpecifier(org.apache.commons.compress.harmony.pack200.Codec, org.apache.commons.compress.harmony.pack200.Codec):int[]");
    }

    public static BHSDCodec getCanonicalCodec(int i) {
        return canonicalCodec[i];
    }
}
