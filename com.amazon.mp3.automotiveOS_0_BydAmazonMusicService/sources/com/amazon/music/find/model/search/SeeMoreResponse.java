package com.amazon.music.find.model.search;

import com.amazon.music.search.SectionType;
import java.util.List;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt__CollectionsKt;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: SeeMoreResponse.kt */
/* JADX INFO: loaded from: classes4.dex */
@Metadata(d1 = {"\u00002\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\f\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0002\b\u0086\b\u0018\u00002\u00020\u0001B-\u0012\n\b\u0002\u0010\u0002\u001a\u0004\u0018\u00010\u0003\u0012\n\b\u0002\u0010\u0004\u001a\u0004\u0018\u00010\u0005\u0012\u000e\b\u0002\u0010\u0006\u001a\b\u0012\u0004\u0012\u00020\b0\u0007¢\u0006\u0002\u0010\tJ\u000b\u0010\u0010\u001a\u0004\u0018\u00010\u0003HÆ\u0003J\u000b\u0010\u0011\u001a\u0004\u0018\u00010\u0005HÆ\u0003J\u000f\u0010\u0012\u001a\b\u0012\u0004\u0012\u00020\b0\u0007HÆ\u0003J1\u0010\u0013\u001a\u00020\u00002\n\b\u0002\u0010\u0002\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010\u0004\u001a\u0004\u0018\u00010\u00052\u000e\b\u0002\u0010\u0006\u001a\b\u0012\u0004\u0012\u00020\b0\u0007HÆ\u0001J\u0013\u0010\u0014\u001a\u00020\u00152\b\u0010\u0016\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010\u0017\u001a\u00020\u0018HÖ\u0001J\t\u0010\u0019\u001a\u00020\u0005HÖ\u0001R\u0013\u0010\u0004\u001a\u0004\u0018\u00010\u0005¢\u0006\b\n\u0000\u001a\u0004\b\n\u0010\u000bR\u0017\u0010\u0006\u001a\b\u0012\u0004\u0012\u00020\b0\u0007¢\u0006\b\n\u0000\u001a\u0004\b\f\u0010\rR\u0013\u0010\u0002\u001a\u0004\u0018\u00010\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u000e\u0010\u000f¨\u0006\u001a"}, d2 = {"Lcom/amazon/music/find/model/search/SeeMoreResponse;", "", "sectionType", "Lcom/amazon/music/search/SectionType;", "pageToken", "", "searchItems", "", "Lcom/amazon/music/find/model/search/SearchItem;", "(Lcom/amazon/music/search/SectionType;Ljava/lang/String;Ljava/util/List;)V", "getPageToken", "()Ljava/lang/String;", "getSearchItems", "()Ljava/util/List;", "getSectionType", "()Lcom/amazon/music/search/SectionType;", "component1", "component2", "component3", "copy", "equals", "", "other", "hashCode", "", "toString", "DMMFindExperience_release"}, k = 1, mv = {1, 6, 0}, xi = 48)
public final /* data */ class SeeMoreResponse {
    private final String pageToken;
    private final List<SearchItem> searchItems;
    private final SectionType sectionType;

    public SeeMoreResponse() {
        this(null, null, null, 7, null);
    }

    /* JADX WARN: Multi-variable type inference failed */
    public static /* synthetic */ SeeMoreResponse copy$default(SeeMoreResponse seeMoreResponse, SectionType sectionType, String str, List list, int i, Object obj) {
        if ((i & 1) != 0) {
            sectionType = seeMoreResponse.sectionType;
        }
        if ((i & 2) != 0) {
            str = seeMoreResponse.pageToken;
        }
        if ((i & 4) != 0) {
            list = seeMoreResponse.searchItems;
        }
        return seeMoreResponse.copy(sectionType, str, list);
    }

    /* JADX INFO: renamed from: component1, reason: from getter */
    public final SectionType getSectionType() {
        return this.sectionType;
    }

    /* JADX INFO: renamed from: component2, reason: from getter */
    public final String getPageToken() {
        return this.pageToken;
    }

    public final List<SearchItem> component3() {
        return this.searchItems;
    }

    public final SeeMoreResponse copy(SectionType sectionType, String pageToken, List<? extends SearchItem> searchItems) {
        Intrinsics.checkNotNullParameter(searchItems, "searchItems");
        return new SeeMoreResponse(sectionType, pageToken, searchItems);
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof SeeMoreResponse)) {
            return false;
        }
        SeeMoreResponse seeMoreResponse = (SeeMoreResponse) other;
        return this.sectionType == seeMoreResponse.sectionType && Intrinsics.areEqual(this.pageToken, seeMoreResponse.pageToken) && Intrinsics.areEqual(this.searchItems, seeMoreResponse.searchItems);
    }

    public int hashCode() {
        SectionType sectionType = this.sectionType;
        int iHashCode = (sectionType == null ? 0 : sectionType.hashCode()) * 31;
        String str = this.pageToken;
        return ((iHashCode + (str != null ? str.hashCode() : 0)) * 31) + this.searchItems.hashCode();
    }

    public String toString() {
        return "SeeMoreResponse(sectionType=" + this.sectionType + ", pageToken=" + ((Object) this.pageToken) + ", searchItems=" + this.searchItems + ')';
    }

    /* JADX WARN: Multi-variable type inference failed */
    public SeeMoreResponse(SectionType sectionType, String str, List<? extends SearchItem> searchItems) {
        Intrinsics.checkNotNullParameter(searchItems, "searchItems");
        this.sectionType = sectionType;
        this.pageToken = str;
        this.searchItems = searchItems;
    }

    public final SectionType getSectionType() {
        return this.sectionType;
    }

    public final String getPageToken() {
        return this.pageToken;
    }

    public /* synthetic */ SeeMoreResponse(SectionType sectionType, String str, List list, int i, DefaultConstructorMarker defaultConstructorMarker) {
        this((i & 1) != 0 ? null : sectionType, (i & 2) != 0 ? null : str, (i & 4) != 0 ? CollectionsKt__CollectionsKt.emptyList() : list);
    }

    public final List<SearchItem> getSearchItems() {
        return this.searchItems;
    }
}
