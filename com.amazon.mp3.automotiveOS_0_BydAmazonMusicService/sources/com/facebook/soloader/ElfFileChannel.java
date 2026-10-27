package com.facebook.soloader;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

/* JADX INFO: loaded from: classes6.dex */
public class ElfFileChannel implements ElfByteChannel {
    private FileChannel mFc;
    private File mFile;
    private FileInputStream mIs;

    public ElfFileChannel(File file) throws IOException {
        this.mFile = file;
        openChannel();
    }

    public void openChannel() throws IOException {
        FileInputStream fileInputStream = new FileInputStream(this.mFile);
        this.mIs = fileInputStream;
        this.mFc = fileInputStream.getChannel();
    }

    @Override // java.nio.channels.ReadableByteChannel
    public int read(ByteBuffer byteBuffer) throws IOException {
        return this.mFc.read(byteBuffer);
    }

    @Override // com.facebook.soloader.ElfByteChannel
    public int read(ByteBuffer byteBuffer, long j) throws IOException {
        return this.mFc.read(byteBuffer, j);
    }

    @Override // java.nio.channels.WritableByteChannel
    public int write(ByteBuffer byteBuffer) throws IOException {
        return this.mFc.write(byteBuffer);
    }

    @Override // java.nio.channels.Channel, java.io.Closeable, java.lang.AutoCloseable
    public void close() throws IOException {
        this.mIs.close();
    }

    @Override // java.nio.channels.Channel
    public boolean isOpen() {
        return this.mFc.isOpen();
    }
}
