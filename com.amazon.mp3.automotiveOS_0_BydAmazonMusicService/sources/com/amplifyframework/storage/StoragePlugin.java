package com.amplifyframework.storage;

import android.content.Context;
import androidx.annotation.NonNull;
import androidx.annotation.WorkerThread;
import com.amplifyframework.AmplifyException;
import com.amplifyframework.core.category.CategoryType;
import com.amplifyframework.core.plugin.Plugin;

/* JADX INFO: loaded from: classes5.dex */
public abstract class StoragePlugin<E> implements StorageCategoryBehavior, Plugin<E> {
    @Override // com.amplifyframework.core.plugin.Plugin
    @WorkerThread
    public void initialize(@NonNull Context context) throws AmplifyException {
    }

    @Override // com.amplifyframework.core.category.CategoryTypeable
    @NonNull
    public final CategoryType getCategoryType() {
        return CategoryType.STORAGE;
    }
}
