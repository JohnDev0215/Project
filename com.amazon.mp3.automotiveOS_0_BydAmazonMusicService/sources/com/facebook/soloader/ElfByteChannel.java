package com.facebook.soloader;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.ByteChannel;

/* JADX INFO: loaded from: classes6.dex */
public interface ElfByteChannel extends ByteChannel {
    int read(ByteBuffer byteBuffer, long j) throws IOException;
}
