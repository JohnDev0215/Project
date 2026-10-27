package com.amazon.digitalmusicxp.types;

import com.amazon.digitalmusicxp.enums.SearchTypeEnum;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: SearchSeed.kt */
/* JADX INFO: loaded from: classes2.dex */
@Metadata(bv = {}, d1 = {"\u0000$\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u000b\b\u0086\b\u0018\u00002\u00020\u0001J\t\u0010\u0003\u001a\u00020\u0002HÖ\u0001J\t\u0010\u0005\u001a\u00020\u0004HÖ\u0001J\u0013\u0010\b\u001a\u00020\u00072\b\u0010\u0006\u001a\u0004\u0018\u00010\u0001HÖ\u0003R\u0017\u0010\n\u001a\u00020\t8\u0006¢\u0006\f\n\u0004\b\n\u0010\u000b\u001a\u0004\b\f\u0010\rR\u0017\u0010\u000e\u001a\u00020\u00028\u0006¢\u0006\f\n\u0004\b\u000e\u0010\u000f\u001a\u0004\b\u0010\u0010\u0011R\u0019\u0010\u0012\u001a\u0004\u0018\u00010\u00028\u0006¢\u0006\f\n\u0004\b\u0012\u0010\u000f\u001a\u0004\b\u0013\u0010\u0011¨\u0006\u0014"}, d2 = {"Lcom/amazon/digitalmusicxp/types/SearchSeed;", "", "", "toString", "", "hashCode", "other", "", "equals", "Lcom/amazon/digitalmusicxp/enums/SearchTypeEnum;", "searchType", "Lcom/amazon/digitalmusicxp/enums/SearchTypeEnum;", "getSearchType", "()Lcom/amazon/digitalmusicxp/enums/SearchTypeEnum;", "searchTerm", "Ljava/lang/String;", "getSearchTerm", "()Ljava/lang/String;", "pageToken", "getPageToken", "DigitalMusicCloudQueueXP_release"}, k = 1, mv = {1, 7, 1})
public final /* data */ class SearchSeed {
    private final String pageToken;
    private final String searchTerm;
    private final SearchTypeEnum searchType;

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof SearchSeed)) {
            return false;
        }
        SearchSeed searchSeed = (SearchSeed) other;
        return this.searchType == searchSeed.searchType && Intrinsics.areEqual(this.searchTerm, searchSeed.searchTerm) && Intrinsics.areEqual(this.pageToken, searchSeed.pageToken);
    }

    public int hashCode() {
        int iHashCode = ((this.searchType.hashCode() * 31) + this.searchTerm.hashCode()) * 31;
        String str = this.pageToken;
        return iHashCode + (str == null ? 0 : str.hashCode());
    }

    public String toString() {
        return "SearchSeed(searchType=" + this.searchType + ", searchTerm=" + this.searchTerm + ", pageToken=" + this.pageToken + ')';
    }
}
