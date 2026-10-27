package org.tukaani.xz;

import java.io.InputStream;

/* JADX INFO: loaded from: classes4.dex */
class LZMA2Decoder extends LZMA2Coder implements FilterDecoder {
    private int dictSize;

    LZMA2Decoder(byte[] bArr) throws UnsupportedOptionsException {
        if (bArr.length == 1) {
            byte b = bArr[0];
            if ((b & 255) <= 37) {
                this.dictSize = ((b & 1) | 2) << ((b >>> 1) + 11);
                return;
            }
        }
        throw new UnsupportedOptionsException("Unsupported LZMA2 properties");
    }

    @Override // org.tukaani.xz.FilterDecoder
    public InputStream getInputStream(InputStream inputStream, ArrayCache arrayCache) {
        return new LZMA2InputStream(inputStream, this.dictSize, null, arrayCache);
    }

    @Override // org.tukaani.xz.FilterDecoder
    public int getMemoryUsage() {
        return LZMA2InputStream.getMemoryUsage(this.dictSize);
    }
}
