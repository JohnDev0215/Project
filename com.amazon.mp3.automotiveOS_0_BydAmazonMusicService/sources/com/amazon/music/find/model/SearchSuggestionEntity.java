package com.amazon.music.find.model;

import com.amazon.music.platform.find.service.common.SearchEntity;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: SearchSuggestionEntity.kt */
/* JADX INFO: loaded from: classes4.dex */
@Metadata(d1 = {"\u0000.\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u000b\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\b\u0086\b\u0018\u00002\u00020\u0001B!\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\n\b\u0002\u0010\u0004\u001a\u0004\u0018\u00010\u0003\u0012\u0006\u0010\u0005\u001a\u00020\u0006¢\u0006\u0002\u0010\u0007J\t\u0010\r\u001a\u00020\u0003HÆ\u0003J\u000b\u0010\u000e\u001a\u0004\u0018\u00010\u0003HÆ\u0003J\t\u0010\u000f\u001a\u00020\u0006HÆ\u0003J)\u0010\u0010\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\n\b\u0002\u0010\u0004\u001a\u0004\u0018\u00010\u00032\b\b\u0002\u0010\u0005\u001a\u00020\u0006HÆ\u0001J\u0013\u0010\u0011\u001a\u00020\u00122\b\u0010\u0013\u001a\u0004\u0018\u00010\u0014HÖ\u0003J\t\u0010\u0015\u001a\u00020\u0016HÖ\u0001J\t\u0010\u0017\u001a\u00020\u0003HÖ\u0001R\u0016\u0010\u0004\u001a\u0004\u0018\u00010\u0003X\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\b\b\u0010\tR\u0011\u0010\u0005\u001a\u00020\u0006¢\u0006\b\n\u0000\u001a\u0004\b\n\u0010\u000bR\u0014\u0010\u0002\u001a\u00020\u0003X\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\b\f\u0010\t¨\u0006\u0018"}, d2 = {"Lcom/amazon/music/find/model/SearchSuggestionEntity;", "Lcom/amazon/music/find/model/SearchSuggestion;", "title", "", "blockRef", "searchEntity", "Lcom/amazon/music/platform/find/service/common/SearchEntity;", "(Ljava/lang/String;Ljava/lang/String;Lcom/amazon/music/platform/find/service/common/SearchEntity;)V", "getBlockRef", "()Ljava/lang/String;", "getSearchEntity", "()Lcom/amazon/music/platform/find/service/common/SearchEntity;", "getTitle", "component1", "component2", "component3", "copy", "equals", "", "other", "", "hashCode", "", "toString", "DMMFindExperience_release"}, k = 1, mv = {1, 6, 0}, xi = 48)
public final /* data */ class SearchSuggestionEntity extends SearchSuggestion {
    private final String blockRef;
    private final SearchEntity searchEntity;
    private final String title;

    public static /* synthetic */ SearchSuggestionEntity copy$default(SearchSuggestionEntity searchSuggestionEntity, String str, String str2, SearchEntity searchEntity, int i, Object obj) {
        if ((i & 1) != 0) {
            str = searchSuggestionEntity.getTitle();
        }
        if ((i & 2) != 0) {
            str2 = searchSuggestionEntity.getBlockRef();
        }
        if ((i & 4) != 0) {
            searchEntity = searchSuggestionEntity.searchEntity;
        }
        return searchSuggestionEntity.copy(str, str2, searchEntity);
    }

    public final String component1() {
        return getTitle();
    }

    public final String component2() {
        return getBlockRef();
    }

    /* JADX INFO: renamed from: component3, reason: from getter */
    public final SearchEntity getSearchEntity() {
        return this.searchEntity;
    }

    public final SearchSuggestionEntity copy(String title, String blockRef, SearchEntity searchEntity) {
        Intrinsics.checkNotNullParameter(title, "title");
        Intrinsics.checkNotNullParameter(searchEntity, "searchEntity");
        return new SearchSuggestionEntity(title, blockRef, searchEntity);
    }

    @Override // com.amazon.music.find.model.SearchSuggestion
    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof SearchSuggestionEntity)) {
            return false;
        }
        SearchSuggestionEntity searchSuggestionEntity = (SearchSuggestionEntity) other;
        return Intrinsics.areEqual(getTitle(), searchSuggestionEntity.getTitle()) && Intrinsics.areEqual(getBlockRef(), searchSuggestionEntity.getBlockRef()) && Intrinsics.areEqual(this.searchEntity, searchSuggestionEntity.searchEntity);
    }

    public int hashCode() {
        return (((getTitle().hashCode() * 31) + (getBlockRef() == null ? 0 : getBlockRef().hashCode())) * 31) + this.searchEntity.hashCode();
    }

    public String toString() {
        return "SearchSuggestionEntity(title=" + getTitle() + ", blockRef=" + ((Object) getBlockRef()) + ", searchEntity=" + this.searchEntity + ')';
    }

    public /* synthetic */ SearchSuggestionEntity(String str, String str2, SearchEntity searchEntity, int i, DefaultConstructorMarker defaultConstructorMarker) {
        this(str, (i & 2) != 0 ? null : str2, searchEntity);
    }

    @Override // com.amazon.music.find.model.SearchSuggestion
    public String getTitle() {
        return this.title;
    }

    @Override // com.amazon.music.find.model.SearchSuggestion
    public String getBlockRef() {
        return this.blockRef;
    }

    public final SearchEntity getSearchEntity() {
        return this.searchEntity;
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public SearchSuggestionEntity(String title, String str, SearchEntity searchEntity) {
        super(title, str);
        Intrinsics.checkNotNullParameter(title, "title");
        Intrinsics.checkNotNullParameter(searchEntity, "searchEntity");
        this.title = title;
        this.blockRef = str;
        this.searchEntity = searchEntity;
    }
}
