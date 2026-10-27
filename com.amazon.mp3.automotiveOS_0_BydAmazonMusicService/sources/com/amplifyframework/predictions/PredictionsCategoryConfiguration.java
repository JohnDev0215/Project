package com.amplifyframework.predictions;

import androidx.annotation.NonNull;
import com.amplifyframework.core.category.CategoryConfiguration;
import com.amplifyframework.core.category.CategoryType;

/* JADX INFO: loaded from: classes5.dex */
public final class PredictionsCategoryConfiguration extends CategoryConfiguration {
    @Override // com.amplifyframework.core.category.CategoryTypeable
    @NonNull
    public CategoryType getCategoryType() {
        return CategoryType.PREDICTIONS;
    }
}
