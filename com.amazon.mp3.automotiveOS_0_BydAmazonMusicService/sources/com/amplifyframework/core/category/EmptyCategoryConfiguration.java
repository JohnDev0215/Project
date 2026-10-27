package com.amplifyframework.core.category;

import androidx.annotation.NonNull;

/* JADX INFO: loaded from: classes5.dex */
public final class EmptyCategoryConfiguration extends CategoryConfiguration {
    private final CategoryType categoryType;

    private EmptyCategoryConfiguration(CategoryType categoryType) {
        this.categoryType = categoryType;
    }

    public static EmptyCategoryConfiguration forCategoryType(CategoryType categoryType) {
        return new EmptyCategoryConfiguration(categoryType);
    }

    @Override // com.amplifyframework.core.category.CategoryTypeable
    @NonNull
    public CategoryType getCategoryType() {
        return this.categoryType;
    }
}
