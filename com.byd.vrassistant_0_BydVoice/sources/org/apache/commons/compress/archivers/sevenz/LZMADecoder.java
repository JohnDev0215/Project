package org.apache.commons.compress.archivers.sevenz;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import org.apache.commons.compress.MemoryLimitException;
import org.apache.commons.compress.utils.ByteUtils;
import org.apache.commons.compress.utils.FlushShieldFilterOutputStream;
import org.tukaani.xz.LZMA2Options;
import org.tukaani.xz.LZMAInputStream;
import org.tukaani.xz.LZMAOutputStream;

/* JADX INFO: loaded from: classes4.dex */
class LZMADecoder extends CoderBase {
    LZMADecoder() {
        super(LZMA2Options.class, Number.class);
    }

    @Override // org.apache.commons.compress.archivers.sevenz.CoderBase
    InputStream decode(String str, InputStream inputStream, long j, Coder coder, byte[] bArr, int i) throws IOException {
        if (coder.properties == null) {
            throw new IOException("Missing LZMA properties");
        }
        if (coder.properties.length < 1) {
            throw new IOException("LZMA properties too short");
        }
        byte b = coder.properties[0];
        int dictionarySize = getDictionarySize(coder);
        if (dictionarySize > 2147483632) {
            throw new IOException("Dictionary larger than 4GiB maximum size used in " + str);
        }
        int memoryUsage = LZMAInputStream.getMemoryUsage(dictionarySize, b);
        if (memoryUsage > i) {
            throw new MemoryLimitException(memoryUsage, i);
        }
        return new LZMAInputStream(inputStream, j, b, dictionarySize);
    }

    @Override // org.apache.commons.compress.archivers.sevenz.CoderBase
    OutputStream encode(OutputStream outputStream, Object obj) throws IOException {
        return new FlushShieldFilterOutputStream(new LZMAOutputStream(outputStream, getOptions(obj), false));
    }

    @Override // org.apache.commons.compress.archivers.sevenz.CoderBase
    byte[] getOptionsAsProperties(Object obj) throws IOException {
        LZMA2Options options = getOptions(obj);
        byte pb = (byte) ((((options.getPb() * 5) + options.getLp()) * 9) + options.getLc());
        int dictSize = options.getDictSize();
        byte[] bArr = new byte[5];
        bArr[0] = pb;
        ByteUtils.toLittleEndian(bArr, dictSize, 1, 4);
        return bArr;
    }

    @Override // org.apache.commons.compress.archivers.sevenz.CoderBase
    Object getOptionsFromCoder(Coder coder, InputStream inputStream) throws IOException {
        if (coder.properties == null) {
            throw new IOException("Missing LZMA properties");
        }
        if (coder.properties.length < 1) {
            throw new IOException("LZMA properties too short");
        }
        int i = coder.properties[0] & 255;
        int i2 = i / 45;
        int i3 = i - ((i2 * 9) * 5);
        int i4 = i3 / 9;
        LZMA2Options lZMA2Options = new LZMA2Options();
        lZMA2Options.setPb(i2);
        lZMA2Options.setLcLp(i3 - (i4 * 9), i4);
        lZMA2Options.setDictSize(getDictionarySize(coder));
        return lZMA2Options;
    }

    private int getDictionarySize(Coder coder) throws IllegalArgumentException {
        return (int) ByteUtils.fromLittleEndian(coder.properties, 1, 4);
    }

    private LZMA2Options getOptions(Object obj) throws IOException {
        if (obj instanceof LZMA2Options) {
            return (LZMA2Options) obj;
        }
        LZMA2Options lZMA2Options = new LZMA2Options();
        lZMA2Options.setDictSize(numberOptionOrDefault(obj));
        return lZMA2Options;
    }

    private int numberOptionOrDefault(Object obj) {
        return numberOptionOrDefault(obj, 8388608);
    }
}
