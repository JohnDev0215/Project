package org.tukaani.xz.common;

import java.io.IOException;
import java.io.OutputStream;
import java.util.zip.CRC32;

/* JADX INFO: loaded from: classes2.dex */
public class EncoderUtil extends Util {
    public static void writeCRC32(OutputStream out, byte[] buf) throws IOException {
        CRC32 crc32 = new CRC32();
        crc32.update(buf);
        long value = crc32.getValue();
        for (int i = 0; i < 4; i++) {
            out.write((byte) (value >>> (i * 8)));
        }
    }

    public static void encodeVLI(OutputStream out, long num) throws IOException {
        while (num >= 128) {
            out.write((byte) (128 | num));
            num >>>= 7;
        }
        out.write((byte) num);
    }
}
