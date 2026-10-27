package org.tukaani.xz;

import java.io.InputStream;

/* JADX INFO: loaded from: classes2.dex */
class LZMA2Decoder extends LZMA2Coder implements FilterDecoder {
    private int dictSize;

    LZMA2Decoder(byte[] props) throws UnsupportedOptionsException {
        if (props.length != 1 || (props[0] & 255) > 37) {
            throw new UnsupportedOptionsException("Unsupported LZMA2 properties");
        }
        int i = (props[0] & 1) | 2;
        this.dictSize = i;
        this.dictSize = i << ((props[0] >>> 1) + 11);
    }

    @Override // org.tukaani.xz.FilterDecoder
    public int getMemoryUsage() {
        return LZMA2InputStream.getMemoryUsage(this.dictSize);
    }

    @Override // org.tukaani.xz.FilterDecoder
    public InputStream getInputStream(InputStream in, ArrayCache arrayCache) {
        return new LZMA2InputStream(in, this.dictSize, null, arrayCache);
    }
}
