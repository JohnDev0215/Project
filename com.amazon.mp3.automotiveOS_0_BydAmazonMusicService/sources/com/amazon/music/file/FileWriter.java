package com.amazon.music.file;

import java.io.FileOutputStream;
import java.io.IOException;
import java.lang.Exception;
import java.nio.channels.FileChannel;
import java.nio.charset.Charset;

/* JADX INFO: loaded from: classes4.dex */
public class FileWriter<T, X extends Exception> extends FileAccessor {
    private FileWriterConfig mConfig;
    private FileItemTransformer<T, X> mTransformer;

    public FileWriter(FileWriterConfig fileWriterConfig, FileItemTransformer<T, X> fileItemTransformer) {
        super(fileWriterConfig);
        this.mConfig = fileWriterConfig;
        this.mTransformer = fileItemTransformer;
    }

    public void record(T t) throws Exception {
        if (t == null) {
            throw new NullPointerException("Item can not be null");
        }
        record(this.mConfig, this.mTransformer, t);
    }

    private void record(FileWriterConfig fileWriterConfig, FileItemTransformer<T, X> fileItemTransformer, T t) throws Exception {
        writeStringToFile(fileWriterConfig, fileItemTransformer.transform(t));
    }

    private void writeStringToFile(FileWriterConfig fileWriterConfig, String str) throws Throwable {
        FileChannel fileChannel;
        FileOutputStream outputStreamForWriting;
        if (str == null || str.trim().length() == 0) {
            return;
        }
        FileOutputStream fileOutputStream = null;
        FileChannel writableFileChannel = null;
        try {
            outputStreamForWriting = getOutputStreamForWriting();
        } catch (Throwable th) {
            th = th;
            fileChannel = null;
        }
        try {
            writableFileChannel = getWritableFileChannel(fileWriterConfig, outputStreamForWriting);
            writableFileChannel.lock();
            outputStreamForWriting.write(getWriteString(writableFileChannel, str, this.mConfig.getDelimiter()).getBytes(Charset.forName(this.mConfig.getCharsetName())));
            outputStreamForWriting.close();
            writableFileChannel.close();
        } catch (Throwable th2) {
            th = th2;
            fileChannel = writableFileChannel;
            fileOutputStream = outputStreamForWriting;
            if (fileOutputStream != null) {
                fileOutputStream.close();
            }
            if (fileChannel != null) {
                fileChannel.close();
            }
            throw th;
        }
    }

    private String getWriteString(FileChannel fileChannel, String str, String str2) throws IOException {
        if (fileChannel.size() <= 0) {
            return str;
        }
        return str2 + str;
    }

    private FileChannel getWritableFileChannel(FileWriterConfig fileWriterConfig, FileOutputStream fileOutputStream) throws MaxFileSizeException, IOException {
        FileChannel channel = fileOutputStream.getChannel();
        checkChannelForSize(fileWriterConfig.getMaxFileSizeBytes(), channel);
        return channel;
    }

    private void checkChannelForSize(long j, FileChannel fileChannel) throws MaxFileSizeException, IOException {
        if (j != 0 && fileChannel.size() > j) {
            throw new MaxFileSizeException(buildMaxFileSizeExceptionString(j));
        }
    }

    private FileOutputStream getOutputStreamForWriting() throws IOException {
        return new FileOutputStream(getFile(), true);
    }

    private String buildMaxFileSizeExceptionString(long j) {
        return String.format("File is too large to write to - max size: %d", Long.valueOf(j));
    }
}
