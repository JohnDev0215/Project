package com.amplifyframework.storage.result;

import androidx.annotation.NonNull;
import androidx.core.util.ObjectsCompat;
import java.util.Objects;

/* JADX INFO: loaded from: classes5.dex */
public final class StorageUploadInputStreamResult extends StorageUploadResult {
    private StorageUploadInputStreamResult(String str) {
        super(str);
    }

    @NonNull
    public static StorageUploadInputStreamResult fromKey(@NonNull String str) {
        Objects.requireNonNull(str);
        return new StorageUploadInputStreamResult(str);
    }

    @Override // com.amplifyframework.storage.result.StorageUploadResult
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || StorageUploadInputStreamResult.class != obj.getClass()) {
            return false;
        }
        return ObjectsCompat.equals(super.getKey(), ((StorageUploadInputStreamResult) obj).getKey());
    }

    @Override // com.amplifyframework.storage.result.StorageUploadResult
    public int hashCode() {
        return super.hashCode();
    }
}
