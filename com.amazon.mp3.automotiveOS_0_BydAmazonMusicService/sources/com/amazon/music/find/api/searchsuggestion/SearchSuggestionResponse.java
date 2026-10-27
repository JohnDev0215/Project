package com.amazon.music.find.api.searchsuggestion;

import com.amazon.music.find.model.SearchSuggestion;
import java.util.List;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt__CollectionsKt;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: SearchSuggestionResponse.kt */
/* JADX INFO: loaded from: classes4.dex */
@Metadata(d1 = {"\u0000,\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\t\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0002\b\u0086\b\u0018\u00002\u00020\u0001B!\u0012\u000e\b\u0002\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003\u0012\n\b\u0002\u0010\u0005\u001a\u0004\u0018\u00010\u0006¢\u0006\u0002\u0010\u0007J\u000f\u0010\f\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003HÆ\u0003J\u000b\u0010\r\u001a\u0004\u0018\u00010\u0006HÆ\u0003J%\u0010\u000e\u001a\u00020\u00002\u000e\b\u0002\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u00032\n\b\u0002\u0010\u0005\u001a\u0004\u0018\u00010\u0006HÆ\u0001J\u0013\u0010\u000f\u001a\u00020\u00102\b\u0010\u0011\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010\u0012\u001a\u00020\u0013HÖ\u0001J\t\u0010\u0014\u001a\u00020\u0006HÖ\u0001R\u0013\u0010\u0005\u001a\u0004\u0018\u00010\u0006¢\u0006\b\n\u0000\u001a\u0004\b\b\u0010\tR\u0017\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003¢\u0006\b\n\u0000\u001a\u0004\b\n\u0010\u000b¨\u0006\u0015"}, d2 = {"Lcom/amazon/music/find/api/searchsuggestion/SearchSuggestionResponse;", "", "suggestions", "", "Lcom/amazon/music/find/model/SearchSuggestion;", "queryId", "", "(Ljava/util/List;Ljava/lang/String;)V", "getQueryId", "()Ljava/lang/String;", "getSuggestions", "()Ljava/util/List;", "component1", "component2", "copy", "equals", "", "other", "hashCode", "", "toString", "DMMFindExperience_release"}, k = 1, mv = {1, 6, 0}, xi = 48)
public final /* data */ class SearchSuggestionResponse {
    private final String queryId;
    private final List<SearchSuggestion> suggestions;

    public SearchSuggestionResponse() {
        this(null, 0 == true ? 1 : 0, 3, 0 == true ? 1 : 0);
    }

    /* JADX WARN: Multi-variable type inference failed */
    public static /* synthetic */ SearchSuggestionResponse copy$default(SearchSuggestionResponse searchSuggestionResponse, List list, String str, int i, Object obj) {
        if ((i & 1) != 0) {
            list = searchSuggestionResponse.suggestions;
        }
        if ((i & 2) != 0) {
            str = searchSuggestionResponse.queryId;
        }
        return searchSuggestionResponse.copy(list, str);
    }

    public final List<SearchSuggestion> component1() {
        return this.suggestions;
    }

    /* JADX INFO: renamed from: component2, reason: from getter */
    public final String getQueryId() {
        return this.queryId;
    }

    public final SearchSuggestionResponse copy(List<? extends SearchSuggestion> suggestions, String queryId) {
        Intrinsics.checkNotNullParameter(suggestions, "suggestions");
        return new SearchSuggestionResponse(suggestions, queryId);
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof SearchSuggestionResponse)) {
            return false;
        }
        SearchSuggestionResponse searchSuggestionResponse = (SearchSuggestionResponse) other;
        return Intrinsics.areEqual(this.suggestions, searchSuggestionResponse.suggestions) && Intrinsics.areEqual(this.queryId, searchSuggestionResponse.queryId);
    }

    public int hashCode() {
        int iHashCode = this.suggestions.hashCode() * 31;
        String str = this.queryId;
        return iHashCode + (str == null ? 0 : str.hashCode());
    }

    public String toString() {
        return "SearchSuggestionResponse(suggestions=" + this.suggestions + ", queryId=" + ((Object) this.queryId) + ')';
    }

    /* JADX WARN: Multi-variable type inference failed */
    public SearchSuggestionResponse(List<? extends SearchSuggestion> suggestions, String str) {
        Intrinsics.checkNotNullParameter(suggestions, "suggestions");
        this.suggestions = suggestions;
        this.queryId = str;
    }

    public /* synthetic */ SearchSuggestionResponse(List list, String str, int i, DefaultConstructorMarker defaultConstructorMarker) {
        this((i & 1) != 0 ? CollectionsKt__CollectionsKt.emptyList() : list, (i & 2) != 0 ? null : str);
    }

    public final List<SearchSuggestion> getSuggestions() {
        return this.suggestions;
    }

    public final String getQueryId() {
        return this.queryId;
    }
}
