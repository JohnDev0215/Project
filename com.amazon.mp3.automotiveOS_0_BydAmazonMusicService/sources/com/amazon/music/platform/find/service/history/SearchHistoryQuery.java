package com.amazon.music.platform.find.service.history;

import com.amazon.music.find.utils.ContentUtils;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: SearchHistoryQuery.kt */
/* JADX INFO: loaded from: classes4.dex */
@Metadata(bv = {}, d1 = {"\u0000\"\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000b\n\u0002\b\b\b\u0087\b\u0018\u00002\u00020\u0001B\u000f\u0012\u0006\u0010\n\u001a\u00020\u0002¢\u0006\u0004\b\u000e\u0010\u000fJ\t\u0010\u0003\u001a\u00020\u0002HÖ\u0001J\t\u0010\u0005\u001a\u00020\u0004HÖ\u0001J\u0013\u0010\t\u001a\u00020\b2\b\u0010\u0007\u001a\u0004\u0018\u00010\u0006HÖ\u0003R\u001a\u0010\n\u001a\u00020\u00028\u0016X\u0096\u0004¢\u0006\f\n\u0004\b\n\u0010\u000b\u001a\u0004\b\f\u0010\r¨\u0006\u0010"}, d2 = {"Lcom/amazon/music/platform/find/service/history/SearchHistoryQuery;", "Lcom/amazon/music/platform/find/service/history/SearchHistoryItem;", "", "toString", "", "hashCode", "", "other", "", "equals", ContentUtils.KEY_SEARCH_QUERY, "Ljava/lang/String;", "getSearchQuery", "()Ljava/lang/String;", "<init>", "(Ljava/lang/String;)V", "MusicPlatformFind_release"}, k = 1, mv = {1, 7, 1})
public final /* data */ class SearchHistoryQuery extends SearchHistoryItem {
    private final String searchQuery;

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        return (other instanceof SearchHistoryQuery) && Intrinsics.areEqual(getSearchQuery(), ((SearchHistoryQuery) other).getSearchQuery());
    }

    public int hashCode() {
        return getSearchQuery().hashCode();
    }

    public String toString() {
        return "SearchHistoryQuery(searchQuery=" + getSearchQuery() + ')';
    }

    @Override // com.amazon.music.platform.find.service.history.SearchHistoryItem
    public String getSearchQuery() {
        return this.searchQuery;
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public SearchHistoryQuery(String searchQuery) {
        super(searchQuery);
        Intrinsics.checkNotNullParameter(searchQuery, "searchQuery");
        this.searchQuery = searchQuery;
    }
}
