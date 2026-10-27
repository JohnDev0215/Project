package org.tukaani.xz.rangecoder;

import java.io.IOException;
import java.io.OutputStream;

/* JADX INFO: loaded from: classes2.dex */
public final class RangeEncoderToStream extends RangeEncoder {
    private final OutputStream out;

    public RangeEncoderToStream(OutputStream out) {
        this.out = out;
        reset();
    }

    @Override // org.tukaani.xz.rangecoder.RangeEncoder
    void writeByte(int b) throws IOException {
        this.out.write(b);
    }
}
