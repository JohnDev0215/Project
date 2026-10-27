package com.amazon.mls.config.internal.sushi.writer;

import com.amazon.mls.config.internal.core.logcat.LogLevel;
import com.amazon.mls.config.internal.core.logcat.LogcatProxy;
import com.amazon.mls.config.internal.core.metrics.InternalMetrics;
import com.amazon.mls.config.internal.sushi.SushiFile;
import com.amazon.mls.config.internal.sushi.util.FileUtils;
import com.amazon.mls.config.internal.sushi.util.SafeStreamCloser;
import com.amazon.music.destination.parser.ParserUtil;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Locale;
import org.json.JSONException;

/* JADX INFO: loaded from: classes3.dex */
public class FileWriterLoader {
    private final SushiFormatter sushiFormatter = new SushiFormatter();
    private final File writerDir;

    public FileWriterLoader(File file) {
        this.writerDir = FileUtils.getChildPath(file, "writer");
        LogcatProxy.log("Created Sushi FileWriteLoader with parentDir '" + file.getAbsolutePath());
    }

    public FileWriter loadSushiWriter() {
        try {
            FileUtils.setupDir(this.writerDir);
            return new FileWriterImpl(this.writerDir, loadExistingFile());
        } catch (Exception e) {
            InternalMetrics.logCounter("sushi_load_writer_failed_e", 1L);
            LogcatProxy.log(LogLevel.ERROR, "Failed to load the FileWriter for " + this.writerDir.getAbsolutePath(), e);
            return new DummyFileWriter();
        }
    }

    private SushiFile loadExistingFile() {
        File fileFindWriterFile = findWriterFile();
        if (fileFindWriterFile == null) {
            LogcatProxy.logForAutomation("No previous Sushi file found");
            return null;
        }
        FileWriterNameFormatter fileWriterNameFormatterFromFileName = FileWriterNameFormatter.fromFileName(fileFindWriterFile.getName());
        if (fileWriterNameFormatterFromFileName == null) {
            LogcatProxy.log(LogLevel.ERROR, String.format(Locale.US, "Deleting file with wrong format: %s", fileFindWriterFile.getAbsolutePath()));
            FileUtils.safelyDeleteFromDisk(fileFindWriterFile);
            return null;
        }
        SushiFile sushiFileTryGetSushiFile = tryGetSushiFile(fileWriterNameFormatterFromFileName, fileFindWriterFile);
        if (sushiFileTryGetSushiFile == null) {
            LogcatProxy.log(LogLevel.ERROR, String.format(Locale.US, "Unexpected file found in file writer dir. Deleting it: %s", fileFindWriterFile.getAbsolutePath()));
            FileUtils.safelyDeleteFromDisk(fileFindWriterFile);
            return null;
        }
        LogcatProxy.logForAutomation("Loaded previous Sushi file '" + fileFindWriterFile.getAbsolutePath() + "'");
        return sushiFileTryGetSushiFile;
    }

    private boolean isOlderVersion(String str) {
        return !"v2".equals(str);
    }

    private File findWriterFile() {
        File[] fileArrListFiles = FileUtils.listFiles(this.writerDir);
        if (fileArrListFiles == null || fileArrListFiles.length <= 0) {
            return null;
        }
        if (fileArrListFiles.length > 1) {
            InternalMetrics.logCounter("sushi_write_to_file_failed_w", fileArrListFiles.length);
        }
        return fileArrListFiles[0];
    }

    private SushiFile tryGetSushiFile(FileWriterNameFormatter fileWriterNameFormatter, File file) {
        if (isOlderVersion(fileWriterNameFormatter.getFileVersion())) {
            LogcatProxy.log("Tried to load file from old version " + file.getAbsolutePath());
            return null;
        }
        try {
            return getSushiFileFromContent(new SushiFile.Builder().withFileId(fileWriterNameFormatter.getFileId()).withDestinationRegion(fileWriterNameFormatter.getRegion()).withSupportFile(file).withFileLength(file.length()), parseFile(file));
        } catch (IOException | IllegalArgumentException | SecurityException | JSONException e) {
            LogcatProxy.log(String.format("Failed loading existing file %s", file.getAbsolutePath()), e);
            return null;
        }
    }

    private String parseFile(File file) throws IOException, IllegalArgumentException {
        long length = file.length();
        if (length == 0) {
            throw new IllegalArgumentException(String.format("File doesn't exist or it is empty! Path = %s", file.getAbsolutePath()));
        }
        if (length > this.sushiFormatter.getMaxFileSizeBytes()) {
            throw new IllegalArgumentException(String.format("File size too large. Expected at most %d but found %d!", Long.valueOf(this.sushiFormatter.getMaxFileSizeBytes()), Long.valueOf(length)));
        }
        FileInputStream fileInputStream = new FileInputStream(file);
        StringBuilder sb = new StringBuilder();
        byte[] bArr = new byte[8192];
        while (true) {
            try {
                int i = fileInputStream.read(bArr);
                if (i != -1) {
                    sb.append(new String(bArr, 0, i, ParserUtil.UTF_8));
                } else {
                    SafeStreamCloser.close(fileInputStream);
                    return sb.toString();
                }
            } catch (Throwable th) {
                SafeStreamCloser.close(fileInputStream);
                throw th;
            }
        }
    }

    private SushiFile getSushiFileFromContent(SushiFile.Builder builder, String str) throws JSONException {
        boolean z;
        if (!str.startsWith(this.sushiFormatter.getFilePrefix()) || str.endsWith(this.sushiFormatter.getFileSuffix())) {
            z = false;
        } else {
            str = str + this.sushiFormatter.getFileSuffix();
            z = true;
        }
        return builder.withEventCount(this.sushiFormatter.parseSushiEntries(str).length()).withIsOpen(z).build();
    }
}
