package com.amazon.digitalmusicplayback;

/* JADX INFO: loaded from: classes2.dex */
public final class StorageInitError {
    final EngineError storageError;
    final String storageLocation;

    public StorageInitError(String str, EngineError engineError) {
        this.storageLocation = str;
        this.storageError = engineError;
    }

    public String getStorageLocation() {
        return this.storageLocation;
    }

    public EngineError getStorageError() {
        return this.storageError;
    }

    public String toString() {
        return "StorageInitError{storageLocation=" + this.storageLocation + ",storageError=" + this.storageError + "}";
    }
}
