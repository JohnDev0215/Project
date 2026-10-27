package com.amplifyframework.storage.operation;

import androidx.annotation.Nullable;
import com.amplifyframework.core.async.AmplifyOperation;
import com.amplifyframework.core.category.CategoryType;

/* JADX INFO: loaded from: classes5.dex */
public abstract class StorageGetUrlOperation<R> extends AmplifyOperation<R> {
    public StorageGetUrlOperation(@Nullable R r) {
        super(CategoryType.STORAGE, r);
    }
}
