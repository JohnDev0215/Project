package com.amazon.mls.config.internal.sushi.writer;

import com.amazon.mls.config.internal.core.logcat.LogLevel;
import com.amazon.mls.config.internal.core.logcat.LogcatProxy;
import com.amazon.mls.config.internal.core.metrics.InternalMetrics;
import com.amazon.mls.config.internal.sushi.SushiFile;
import com.amazon.music.destination.parser.ParserUtil;
import java.io.File;
import java.io.IOException;

/* JADX INFO: loaded from: classes3.dex */
public class FileWriterImpl implements FileWriter {
    private LazyFileOutputStream attachedFileOutputStream;
    private SushiFile currentFile;
    private final SushiFormatter sushiFormatter = new SushiFormatter();
    private final File writerDir;

    FileWriterImpl(File file, SushiFile sushiFile) {
        this.writerDir = file;
        this.currentFile = sushiFile;
        this.attachedFileOutputStream = new LazyFileOutputStream(sushiFile, true);
    }

    @Override // com.amazon.mls.config.internal.sushi.writer.FileWriter
    public SushiFile rotate() throws IOException {
        if (shouldCloseFile()) {
            closeCurrentFile();
            SushiFile sushiFile = this.currentFile;
            this.currentFile = null;
            return sushiFile;
        }
        if (fileExists()) {
            this.currentFile.delete();
            this.currentFile = null;
        }
        return null;
    }

    @Override // com.amazon.mls.config.internal.sushi.writer.FileWriter
    public boolean fileIsEmpty() {
        SushiFile sushiFile = this.currentFile;
        return sushiFile == null || sushiFile.getEventCount().intValue() == 0;
    }

    private boolean writeToFile(String str) {
        try {
            this.attachedFileOutputStream.write(str.getBytes(ParserUtil.UTF_8));
            this.currentFile.addToFileSize(r4.length);
            return true;
        } catch (IOException e) {
            LogcatProxy.log(LogLevel.ERROR, "Writing to file failed", e);
            InternalMetrics.logCounter("sushi_write_to_file_failed_w", 1L);
            return false;
        }
    }

    private boolean fileExists() {
        return this.currentFile != null;
    }

    private boolean shouldCloseFile() {
        SushiFile sushiFile = this.currentFile;
        return sushiFile != null && sushiFile.getEventCount().intValue() > 0 && this.currentFile.isOpen();
    }

    private void closeCurrentFile() throws IOException {
        writeToFile(this.sushiFormatter.getFileSuffix());
        this.currentFile.close();
        this.attachedFileOutputStream.close();
    }
}
