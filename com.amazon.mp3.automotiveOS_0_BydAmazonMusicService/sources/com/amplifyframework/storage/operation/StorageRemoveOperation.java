package com.amplifyframework.storage.operation;

import androidx.annotation.Nullable;
import com.amplifyframework.core.async.AmplifyOperation;
import com.amplifyframework.core.category.CategoryType;

/* JADX INFO: loaded from: classes5.dex */
public abstract class StorageRemoveOperation<R> extends AmplifyOperation<R> {
    public StorageRemoveOperation(@Nullable R r) {
        super(CategoryType.STORAGE, r);
    }
}
