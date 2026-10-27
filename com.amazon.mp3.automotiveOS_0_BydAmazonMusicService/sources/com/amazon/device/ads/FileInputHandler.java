package com.amazon.device.ads;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.Closeable;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

/* JADX INFO: loaded from: classes2.dex */
class FileInputHandler extends FileHandler {
    private static final String LOGTAG = FileInputHandler.class.getSimpleName();
    private BufferedReader bufferedReader;
    private InputStream inputStream;
    private final MobileAdsLogger logger = new MobileAdsLoggerFactory().createMobileAdsLogger(LOGTAG);

    FileInputHandler() {
    }

    public boolean open() {
        if (this.file == null) {
            this.logger.e("A file must be set before it can be opened.");
            return false;
        }
        if (this.inputStream != null) {
            this.logger.e("The file is already open.");
            return false;
        }
        try {
            this.inputStream = new BufferedInputStream(new FileInputStream(this.file));
            this.bufferedReader = new BufferedReader(new InputStreamReader(this.inputStream));
            return true;
        } catch (Exception unused) {
            return false;
        }
    }

    @Override // java.io.Closeable, java.lang.AutoCloseable
    public void close() {
        closeCloseables();
        this.bufferedReader = null;
        this.inputStream = null;
    }

    @Override // com.amazon.device.ads.FileHandler
    protected Closeable getCloseableStream() {
        return this.inputStream;
    }

    @Override // com.amazon.device.ads.FileHandler
    protected Closeable getCloseableReaderWriter() {
        return this.bufferedReader;
    }

    private void checkReadable() {
        if (this.bufferedReader == null) {
            throw new IllegalStateException("Could not read from the file because no file has been opened yet. Please set the file, then call open() before attempting to read.");
        }
    }

    public String readLine() {
        checkReadable();
        try {
            return this.bufferedReader.readLine();
        } catch (IOException unused) {
            this.logger.e("Error reading line from file.");
            return null;
        }
    }
}
