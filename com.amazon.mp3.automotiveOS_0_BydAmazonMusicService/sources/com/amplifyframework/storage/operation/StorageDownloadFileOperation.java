package com.amplifyframework.storage.operation;

import androidx.annotation.Nullable;
import com.amplifyframework.core.async.AmplifyOperation;
import com.amplifyframework.core.async.Cancelable;
import com.amplifyframework.core.async.Resumable;
import com.amplifyframework.core.category.CategoryType;

/* JADX INFO: loaded from: classes5.dex */
public abstract class StorageDownloadFileOperation<R> extends AmplifyOperation<R> implements Resumable, Cancelable {
    public StorageDownloadFileOperation(@Nullable R r) {
        super(CategoryType.STORAGE, r);
    }
}
