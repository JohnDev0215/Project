package com.amplifyframework.storage.result;

import androidx.annotation.NonNull;
import androidx.core.util.ObjectsCompat;
import java.util.Objects;

/* JADX INFO: loaded from: classes5.dex */
public final class StorageRemoveResult {
    private final String key;

    private StorageRemoveResult(String str) {
        this.key = str;
    }

    @NonNull
    public static StorageRemoveResult fromKey(@NonNull String str) {
        Objects.requireNonNull(str);
        return new StorageRemoveResult(str);
    }

    @NonNull
    public String getKey() {
        return this.key;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || StorageRemoveResult.class != obj.getClass()) {
            return false;
        }
        return ObjectsCompat.equals(this.key, ((StorageRemoveResult) obj).key);
    }

    public int hashCode() {
        String str = this.key;
        if (str != null) {
            return str.hashCode();
        }
        return 0;
    }
}
