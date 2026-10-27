package com.amplifyframework.storage.result;

import androidx.annotation.NonNull;
import androidx.core.util.ObjectsCompat;
import java.util.Objects;

/* JADX INFO: loaded from: classes5.dex */
public final class StorageUploadFileResult extends StorageUploadResult {
    private StorageUploadFileResult(String str) {
        super(str);
    }

    @NonNull
    public static StorageUploadFileResult fromKey(@NonNull String str) {
        Objects.requireNonNull(str);
        return new StorageUploadFileResult(str);
    }

    @Override // com.amplifyframework.storage.result.StorageUploadResult
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || StorageUploadFileResult.class != obj.getClass()) {
            return false;
        }
        return ObjectsCompat.equals(super.getKey(), ((StorageUploadFileResult) obj).getKey());
    }

    @Override // com.amplifyframework.storage.result.StorageUploadResult
    public int hashCode() {
        return super.hashCode();
    }
}
