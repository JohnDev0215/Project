package org.tukaani.xz;

import java.io.InputStream;
import org.tukaani.xz.simple.ARM;
import org.tukaani.xz.simple.ARMThumb;
import org.tukaani.xz.simple.IA64;
import org.tukaani.xz.simple.PowerPC;
import org.tukaani.xz.simple.SPARC;
import org.tukaani.xz.simple.SimpleFilter;
import org.tukaani.xz.simple.X86;

/* JADX INFO: loaded from: classes2.dex */
class BCJDecoder extends BCJCoder implements FilterDecoder {
    static final /* synthetic */ boolean $assertionsDisabled = false;
    private final long filterID;
    private final int startOffset;

    BCJDecoder(long filterID, byte[] props) throws UnsupportedOptionsException {
        this.filterID = filterID;
        if (props.length == 0) {
            this.startOffset = 0;
            return;
        }
        if (props.length == 4) {
            int n = 0;
            for (int i = 0; i < 4; i++) {
                n |= (props[i] & 255) << (i * 8);
            }
            this.startOffset = n;
            return;
        }
        throw new UnsupportedOptionsException("Unsupported BCJ filter properties");
    }

    @Override // org.tukaani.xz.FilterDecoder
    public int getMemoryUsage() {
        return SimpleInputStream.getMemoryUsage();
    }

    @Override // org.tukaani.xz.FilterDecoder
    public InputStream getInputStream(InputStream in, ArrayCache arrayCache) {
        SimpleFilter simpleFilter = null;
        long j = this.filterID;
        if (j == 4) {
            simpleFilter = new X86(false, this.startOffset);
        } else if (j == 5) {
            simpleFilter = new PowerPC(false, this.startOffset);
        } else if (j == 6) {
            simpleFilter = new IA64(false, this.startOffset);
        } else if (j == 7) {
            simpleFilter = new ARM(false, this.startOffset);
        } else if (j == 8) {
            simpleFilter = new ARMThumb(false, this.startOffset);
        } else if (j == 9) {
            simpleFilter = new SPARC(false, this.startOffset);
        }
        return new SimpleInputStream(in, simpleFilter);
    }
}
