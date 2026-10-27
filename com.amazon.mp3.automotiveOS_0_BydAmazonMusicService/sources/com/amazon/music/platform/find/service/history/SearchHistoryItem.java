package com.amazon.music.platform.find.service.history;

import com.amazon.music.find.utils.ContentUtils;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: SearchHistoryItem.kt */
/* JADX INFO: loaded from: classes4.dex */
@Metadata(d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0004\b&\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004R\u0014\u0010\u0002\u001a\u00020\u0003X\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0005\u0010\u0006¨\u0006\u0007"}, d2 = {"Lcom/amazon/music/platform/find/service/history/SearchHistoryItem;", "", ContentUtils.KEY_SEARCH_QUERY, "", "(Ljava/lang/String;)V", "getSearchQuery", "()Ljava/lang/String;", "MusicPlatformFind_release"}, k = 1, mv = {1, 7, 1}, xi = 48)
public abstract class SearchHistoryItem {
    private final String searchQuery;

    public SearchHistoryItem(String searchQuery) {
        Intrinsics.checkNotNullParameter(searchQuery, "searchQuery");
        this.searchQuery = searchQuery;
    }

    public String getSearchQuery() {
        return this.searchQuery;
    }
}
