package com.amazon.music.file;

import java.io.File;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.channels.FileChannel;
import java.nio.charset.Charset;

/* JADX INFO: loaded from: classes4.dex */
public class FileReader extends FileAccessor {
    private FileReaderConfig mConfig;

    public FileReader(FileReaderConfig fileReaderConfig) {
        super(fileReaderConfig);
        this.mConfig = fileReaderConfig;
    }

    public String readFile() throws Throwable {
        FileChannel fileChannel;
        RandomAccessFile fileForReading;
        RandomAccessFile randomAccessFile = null;
        FileChannel channel = null;
        try {
            fileForReading = getFileForReading();
        } catch (Throwable th) {
            th = th;
            fileChannel = null;
        }
        try {
            channel = fileForReading.getChannel();
            channel.lock();
            String file = readFile(fileForReading);
            fileForReading.close();
            channel.close();
            return file;
        } catch (Throwable th2) {
            th = th2;
            fileChannel = channel;
            randomAccessFile = fileForReading;
            if (randomAccessFile != null) {
                randomAccessFile.close();
            }
            if (fileChannel != null) {
                fileChannel.close();
            }
            throw th;
        }
    }

    private RandomAccessFile getFileForReading() throws EmptyFileContentsException, IOException {
        return new RandomAccessFile(getFilledFile(), "rwd");
    }

    private File getFilledFile() throws EmptyFileContentsException, IOException {
        File file = getFile();
        checkFileForContent(file);
        return file;
    }

    private void checkFileForContent(File file) throws EmptyFileContentsException {
        if (file.length() <= 0) {
            throw new EmptyFileContentsException();
        }
    }

    private String readFile(RandomAccessFile randomAccessFile) throws IOException {
        StringBuilder sb = new StringBuilder();
        byte[] bArr = new byte[8192];
        while (true) {
            int i = randomAccessFile.read(bArr);
            if (i != -1) {
                sb.append(new String(bArr, 0, i, Charset.forName(this.mConfig.getCharsetName())));
            } else {
                return sb.toString();
            }
        }
    }
}
