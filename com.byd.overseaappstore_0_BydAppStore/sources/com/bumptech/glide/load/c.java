package com.bumptech.glide.load;

import com.bumptech.glide.load.a;
import java.io.IOException;
import java.nio.ByteBuffer;
import spi.m0;

/* JADX INFO: loaded from: classes.dex */
public final class c implements a.d {
    public final /* synthetic */ ByteBuffer a;
    public final /* synthetic */ m0 b;

    public c(ByteBuffer byteBuffer, m0 m0Var) {
        this.a = byteBuffer;
        this.b = m0Var;
    }

    @Override // com.bumptech.glide.load.a.d
    public final int a(ImageHeaderParser imageHeaderParser) throws IOException {
        return imageHeaderParser.c(this.a, this.b);
    }
}
