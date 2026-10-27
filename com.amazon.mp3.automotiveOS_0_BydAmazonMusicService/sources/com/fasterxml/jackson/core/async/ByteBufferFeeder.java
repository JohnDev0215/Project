package com.fasterxml.jackson.core.async;

import java.io.IOException;
import java.nio.ByteBuffer;

/* JADX INFO: loaded from: classes6.dex */
public interface ByteBufferFeeder extends NonBlockingInputFeeder {
    void feedInput(ByteBuffer byteBuffer) throws IOException;
}
