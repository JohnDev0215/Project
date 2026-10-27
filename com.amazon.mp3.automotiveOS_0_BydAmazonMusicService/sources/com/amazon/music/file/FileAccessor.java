package com.amazon.music.file;

import java.io.File;
import java.io.IOException;
import java.util.Locale;

/* JADX INFO: loaded from: classes4.dex */
public class FileAccessor {
    private FileAccessorConfig mConfig;

    public FileAccessor(FileAccessorConfig fileAccessorConfig) {
        if (fileAccessorConfig == null) {
            throw new IllegalArgumentException("Config must not be null");
        }
        this.mConfig = fileAccessorConfig;
    }

    public boolean deleteFile() throws IOException {
        return deleteFile(this.mConfig);
    }

    private boolean deleteFile(FileAccessorConfig fileAccessorConfig) throws IOException {
        return deleteFile(getFile(fileAccessorConfig));
    }

    private boolean deleteFile(File file) {
        if (file.exists()) {
            return file.delete();
        }
        return true;
    }

    public File getFile() throws IOException {
        return getFile(this.mConfig);
    }

    private File getFile(FileAccessorConfig fileAccessorConfig) throws IOException {
        File file = new File(fileAccessorConfig.getFilePath());
        createPathIfNecessary(file);
        return file;
    }

    private void createPathIfNecessary(File file) {
        if (file.exists()) {
            return;
        }
        mkdirs(file.getParentFile());
    }

    private void mkdirs(File file) {
        if (file != null && !file.exists() && !file.mkdirs()) {
            throw new RuntimeException(String.format(Locale.US, "Unable to execute mkdirs for %s", file.getAbsolutePath()));
        }
    }
}
