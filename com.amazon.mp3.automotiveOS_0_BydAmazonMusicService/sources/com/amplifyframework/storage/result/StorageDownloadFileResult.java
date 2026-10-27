package com.amplifyframework.storage.result;

import androidx.annotation.NonNull;
import java.io.File;
import java.util.Objects;

/* JADX INFO: loaded from: classes5.dex */
public final class StorageDownloadFileResult {
    private final File file;

    private StorageDownloadFileResult(File file) {
        this.file = file;
    }

    @NonNull
    public File getFile() {
        return this.file;
    }

    @NonNull
    public static StorageDownloadFileResult fromFile(@NonNull File file) {
        Objects.requireNonNull(file);
        return new StorageDownloadFileResult(file);
    }
}
