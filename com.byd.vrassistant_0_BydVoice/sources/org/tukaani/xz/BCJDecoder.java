package org.tukaani.xz;

import java.io.InputStream;
import org.tukaani.xz.simple.ARM;
import org.tukaani.xz.simple.ARMThumb;
import org.tukaani.xz.simple.IA64;
import org.tukaani.xz.simple.PowerPC;
import org.tukaani.xz.simple.SPARC;
import org.tukaani.xz.simple.X86;

/* JADX INFO: loaded from: classes4.dex */
class BCJDecoder extends BCJCoder implements FilterDecoder {
    static final /* synthetic */ boolean $assertionsDisabled = false;
    private final long filterID;
    private final int startOffset;

    BCJDecoder(long j, byte[] bArr) throws UnsupportedOptionsException {
        this.filterID = j;
        if (bArr.length == 0) {
            this.startOffset = 0;
            return;
        }
        if (bArr.length != 4) {
            throw new UnsupportedOptionsException("Unsupported BCJ filter properties");
        }
        int i = 0;
        for (int i2 = 0; i2 < 4; i2++) {
            i |= (bArr[i2] & 255) << (i2 * 8);
        }
        this.startOffset = i;
    }

    @Override // org.tukaani.xz.FilterDecoder
    public InputStream getInputStream(InputStream inputStream, ArrayCache arrayCache) {
        long j = this.filterID;
        return new SimpleInputStream(inputStream, j == 4 ? new X86(false, this.startOffset) : j == 5 ? new PowerPC(false, this.startOffset) : j == 6 ? new IA64(false, this.startOffset) : j == 7 ? new ARM(false, this.startOffset) : j == 8 ? new ARMThumb(false, this.startOffset) : j == 9 ? new SPARC(false, this.startOffset) : null);
    }

    @Override // org.tukaani.xz.FilterDecoder
    public int getMemoryUsage() {
        return SimpleInputStream.getMemoryUsage();
    }
}
