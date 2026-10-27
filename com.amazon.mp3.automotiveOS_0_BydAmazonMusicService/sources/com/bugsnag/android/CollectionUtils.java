package com.bugsnag.android;

import androidx.annotation.Nullable;
import java.util.Collection;
import java.util.Iterator;

/* JADX INFO: loaded from: classes5.dex */
class CollectionUtils {
    CollectionUtils() {
    }

    static <T> boolean containsNullElements(@Nullable Collection<T> collection) {
        if (collection == null) {
            return true;
        }
        Iterator<T> it = collection.iterator();
        while (it.hasNext()) {
            if (it.next() == null) {
                return true;
            }
        }
        return false;
    }
}
