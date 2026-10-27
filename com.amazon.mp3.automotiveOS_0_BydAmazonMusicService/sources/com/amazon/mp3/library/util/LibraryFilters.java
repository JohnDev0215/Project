package com.amazon.mp3.library.util;

import android.content.Intent;
import java.util.HashMap;
import java.util.concurrent.atomic.AtomicInteger;

/* JADX INFO: loaded from: classes3.dex */
public final class LibraryFilters {
    private static HashMap<Integer, String> sFilters = new HashMap<>();
    private static AtomicInteger sNextFilterId = new AtomicInteger(0);

    private LibraryFilters() {
    }

    public static int createFilterId() {
        return sNextFilterId.incrementAndGet();
    }

    public static Intent set(int filterId, String filter) {
        sFilters.put(Integer.valueOf(filterId), filter);
        Intent intent = new Intent("com.amazon.mp3.library.ACTION_LIBRARY_FILTER_SET");
        intent.putExtra("com.amazon.mp3.EXTRA_LIBRARY_FILTER_ID", filterId);
        intent.putExtra("com.amazon.mp3.EXTRA_LIBRARY_FILTER_VALUE", filter);
        return intent;
    }

    public static String get(int filterId) {
        String str = sFilters.get(Integer.valueOf(filterId));
        return str == null ? "" : str;
    }

    public static String remove(int filterId) {
        return sFilters.remove(Integer.valueOf(filterId));
    }
}
