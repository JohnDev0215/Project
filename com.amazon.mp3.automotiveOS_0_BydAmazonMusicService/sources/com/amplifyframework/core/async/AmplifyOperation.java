package com.amplifyframework.core.async;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import com.amplifyframework.core.category.CategoryType;

/* JADX INFO: loaded from: classes5.dex */
public abstract class AmplifyOperation<R> {
    private final CategoryType categoryType;
    private final R request;

    public abstract void start();

    protected AmplifyOperation(@NonNull CategoryType categoryType, @Nullable R r) {
        this.categoryType = categoryType;
        this.request = r;
    }

    public final CategoryType getCategoryType() {
        return this.categoryType;
    }

    public R getRequest() {
        return this.request;
    }
}
