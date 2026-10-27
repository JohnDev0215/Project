package com.amazon.music.platform.find.service.history;

import com.amazon.music.find.utils.ContentUtils;
import com.amazon.music.platform.find.service.common.SearchEntity;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: SearchHistoryEntity.kt */
/* JADX INFO: loaded from: classes4.dex */
@Metadata(bv = {}, d1 = {"\u0000*\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0007\b\u0087\b\u0018\u00002\u00020\u0001B\u0017\u0012\u0006\u0010\n\u001a\u00020\u0002\u0012\u0006\u0010\u000f\u001a\u00020\u000e¢\u0006\u0004\b\u0013\u0010\u0014J\t\u0010\u0003\u001a\u00020\u0002HÖ\u0001J\t\u0010\u0005\u001a\u00020\u0004HÖ\u0001J\u0013\u0010\t\u001a\u00020\b2\b\u0010\u0007\u001a\u0004\u0018\u00010\u0006HÖ\u0003R\u001a\u0010\n\u001a\u00020\u00028\u0016X\u0096\u0004¢\u0006\f\n\u0004\b\n\u0010\u000b\u001a\u0004\b\f\u0010\rR\u0017\u0010\u000f\u001a\u00020\u000e8\u0006¢\u0006\f\n\u0004\b\u000f\u0010\u0010\u001a\u0004\b\u0011\u0010\u0012¨\u0006\u0015"}, d2 = {"Lcom/amazon/music/platform/find/service/history/SearchHistoryEntity;", "Lcom/amazon/music/platform/find/service/history/SearchHistoryItem;", "", "toString", "", "hashCode", "", "other", "", "equals", ContentUtils.KEY_SEARCH_QUERY, "Ljava/lang/String;", "getSearchQuery", "()Ljava/lang/String;", "Lcom/amazon/music/platform/find/service/common/SearchEntity;", "searchEntity", "Lcom/amazon/music/platform/find/service/common/SearchEntity;", "getSearchEntity", "()Lcom/amazon/music/platform/find/service/common/SearchEntity;", "<init>", "(Ljava/lang/String;Lcom/amazon/music/platform/find/service/common/SearchEntity;)V", "MusicPlatformFind_release"}, k = 1, mv = {1, 7, 1})
public final /* data */ class SearchHistoryEntity extends SearchHistoryItem {
    private final SearchEntity searchEntity;
    private final String searchQuery;

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof SearchHistoryEntity)) {
            return false;
        }
        SearchHistoryEntity searchHistoryEntity = (SearchHistoryEntity) other;
        return Intrinsics.areEqual(getSearchQuery(), searchHistoryEntity.getSearchQuery()) && Intrinsics.areEqual(this.searchEntity, searchHistoryEntity.searchEntity);
    }

    public int hashCode() {
        return (getSearchQuery().hashCode() * 31) + this.searchEntity.hashCode();
    }

    public String toString() {
        return "SearchHistoryEntity(searchQuery=" + getSearchQuery() + ", searchEntity=" + this.searchEntity + ')';
    }

    @Override // com.amazon.music.platform.find.service.history.SearchHistoryItem
    public String getSearchQuery() {
        return this.searchQuery;
    }

    public final SearchEntity getSearchEntity() {
        return this.searchEntity;
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public SearchHistoryEntity(String searchQuery, SearchEntity searchEntity) {
        super(searchQuery);
        Intrinsics.checkNotNullParameter(searchQuery, "searchQuery");
        Intrinsics.checkNotNullParameter(searchEntity, "searchEntity");
        this.searchQuery = searchQuery;
        this.searchEntity = searchEntity;
    }
}
