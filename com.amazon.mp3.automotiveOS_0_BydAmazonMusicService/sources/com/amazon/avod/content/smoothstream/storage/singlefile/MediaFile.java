package com.amazon.avod.content.smoothstream.storage.singlefile;

import com.amazon.avod.content.ContentException;
import java.io.Closeable;
import java.io.IOException;
import java.io.InputStream;
import java.nio.ByteBuffer;
import javax.annotation.Nonnull;

/* JADX INFO: loaded from: classes2.dex */
public interface MediaFile extends Closeable {
    @Nonnull
    ByteBuffer get(ByteRange byteRange) throws IOException;

    void put(InputStream inputStream, ByteRange byteRange) throws IOException, ContentException;
}
