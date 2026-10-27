package com.amplifyframework.storage.result;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import com.amplifyframework.storage.StorageItem;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/* JADX INFO: loaded from: classes5.dex */
public final class StorageListResult {
    private final List<StorageItem> items;

    private StorageListResult(List<StorageItem> list) {
        this.items = list;
    }

    @NonNull
    public static StorageListResult fromItems(@Nullable List<StorageItem> list) {
        ArrayList arrayList = new ArrayList();
        if (list != null) {
            arrayList.addAll(list);
        }
        return new StorageListResult(Collections.unmodifiableList(arrayList));
    }

    @NonNull
    public List<StorageItem> getItems() {
        return this.items;
    }
}
