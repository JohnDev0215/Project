package org.apache.commons.compress.archivers.dump;

import java.io.IOException;
import java.util.Arrays;
import org.apache.commons.compress.archivers.zip.ZipEncoding;
import org.apache.commons.compress.utils.ByteUtils;

/* JADX INFO: loaded from: classes4.dex */
class DumpArchiveUtil {
    private DumpArchiveUtil() {
    }

    public static int calculateChecksum(byte[] bArr) {
        int iConvert32 = 0;
        for (int i = 0; i < 256; i++) {
            iConvert32 += convert32(bArr, i * 4);
        }
        return DumpArchiveConstants.CHECKSUM - (iConvert32 - convert32(bArr, 28));
    }

    public static final boolean verify(byte[] bArr) {
        return convert32(bArr, 24) == 60012 && convert32(bArr, 28) == calculateChecksum(bArr);
    }

    public static final int getIno(byte[] bArr) {
        return convert32(bArr, 20);
    }

    public static final long convert64(byte[] bArr, int i) {
        return ByteUtils.fromLittleEndian(bArr, i, 8);
    }

    public static final int convert32(byte[] bArr, int i) {
        return (int) ByteUtils.fromLittleEndian(bArr, i, 4);
    }

    public static final int convert16(byte[] bArr, int i) {
        return (int) ByteUtils.fromLittleEndian(bArr, i, 2);
    }

    static String decode(ZipEncoding zipEncoding, byte[] bArr, int i, int i2) throws IOException {
        return zipEncoding.decode(Arrays.copyOfRange(bArr, i, i2 + i));
    }
}
