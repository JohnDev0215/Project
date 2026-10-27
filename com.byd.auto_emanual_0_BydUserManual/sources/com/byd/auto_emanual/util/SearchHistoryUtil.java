package com.byd.auto_emanual.util;

import android.content.Context;
import android.text.TextUtils;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/* JADX INFO: loaded from: classes.dex */
public class SearchHistoryUtil {
    private static final String HISTORY_DIVIDER = ":";
    public static final String SEARCH_HISTORY = "search_history";
    private static List<String> searchHistoryCache = new ArrayList();

    public static void updateHistory(Context context, String value) {
        if (TextUtils.isEmpty(value)) {
            searchHistoryCache.clear();
        } else {
            searchHistoryCache.remove(value);
            searchHistoryCache.add(0, value);
        }
        StringBuilder sb = new StringBuilder();
        Iterator<String> it = searchHistoryCache.iterator();
        while (it.hasNext()) {
            sb.append(it.next()).append(HISTORY_DIVIDER);
        }
        SharedPrefUtil.save(context, SEARCH_HISTORY, sb.toString());
    }

    public static List<String> getHistory() {
        return searchHistoryCache;
    }

    public static List<String> initHistoryCache(Context context) {
        searchHistoryCache.clear();
        String[] strArrSplit = SharedPrefUtil.load(context, SEARCH_HISTORY).split(HISTORY_DIVIDER);
        if (strArrSplit == null) {
            return searchHistoryCache;
        }
        for (String str : strArrSplit) {
            if (!TextUtils.isEmpty(str)) {
                searchHistoryCache.add(str);
            }
        }
        return searchHistoryCache;
    }
}
