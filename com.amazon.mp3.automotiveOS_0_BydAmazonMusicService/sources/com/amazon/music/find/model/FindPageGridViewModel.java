package com.amazon.music.find.model;

import com.amazon.music.find.model.filter.FilterMetadata;
import com.amazon.music.views.library.models.PageGridViewModel;
import java.util.List;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: FindPageGridViewModel.kt */
/* JADX INFO: loaded from: classes4.dex */
@Metadata(d1 = {"\u0000<\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\t\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u001a\n\u0002\u0010\b\n\u0002\b\u0002\b\u0086\b\u0018\u00002\u00020\u0001BM\u0012\b\u0010\u0002\u001a\u0004\u0018\u00010\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\n\b\u0002\u0010\u0006\u001a\u0004\u0018\u00010\u0007\u0012\n\b\u0002\u0010\b\u001a\u0004\u0018\u00010\t\u0012\n\b\u0002\u0010\n\u001a\u0004\u0018\u00010\u000b\u0012\u0010\b\u0002\u0010\f\u001a\n\u0012\u0004\u0012\u00020\u000e\u0018\u00010\r¢\u0006\u0002\u0010\u000fJ\u000b\u0010\u001e\u001a\u0004\u0018\u00010\u0003HÆ\u0003J\t\u0010\u001f\u001a\u00020\u0005HÆ\u0003J\u000b\u0010 \u001a\u0004\u0018\u00010\u0007HÆ\u0003J\u000b\u0010!\u001a\u0004\u0018\u00010\tHÆ\u0003J\u0010\u0010\"\u001a\u0004\u0018\u00010\u000bHÆ\u0003¢\u0006\u0002\u0010\u0013J\u0011\u0010#\u001a\n\u0012\u0004\u0012\u00020\u000e\u0018\u00010\rHÆ\u0003JZ\u0010$\u001a\u00020\u00002\n\b\u0002\u0010\u0002\u001a\u0004\u0018\u00010\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00052\n\b\u0002\u0010\u0006\u001a\u0004\u0018\u00010\u00072\n\b\u0002\u0010\b\u001a\u0004\u0018\u00010\t2\n\b\u0002\u0010\n\u001a\u0004\u0018\u00010\u000b2\u0010\b\u0002\u0010\f\u001a\n\u0012\u0004\u0012\u00020\u000e\u0018\u00010\rHÆ\u0001¢\u0006\u0002\u0010%J\u0013\u0010&\u001a\u00020\u00052\b\u0010'\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010(\u001a\u00020)HÖ\u0001J\t\u0010*\u001a\u00020\tHÖ\u0001R\u0013\u0010\b\u001a\u0004\u0018\u00010\t¢\u0006\b\n\u0000\u001a\u0004\b\u0010\u0010\u0011R\u0015\u0010\n\u001a\u0004\u0018\u00010\u000b¢\u0006\n\n\u0002\u0010\u0014\u001a\u0004\b\u0012\u0010\u0013R\u0013\u0010\u0006\u001a\u0004\u0018\u00010\u0007¢\u0006\b\n\u0000\u001a\u0004\b\u0015\u0010\u0016R\u001a\u0010\u0004\u001a\u00020\u0005X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0004\u0010\u0017\"\u0004\b\u0018\u0010\u0019R\u0013\u0010\u0002\u001a\u0004\u0018\u00010\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u001a\u0010\u001bR\u0019\u0010\f\u001a\n\u0012\u0004\u0012\u00020\u000e\u0018\u00010\r¢\u0006\b\n\u0000\u001a\u0004\b\u001c\u0010\u001d¨\u0006+"}, d2 = {"Lcom/amazon/music/find/model/FindPageGridViewModel;", "", "pageGridViewModel", "Lcom/amazon/music/views/library/models/PageGridViewModel;", "isEmpty", "", "filterMetadata", "Lcom/amazon/music/find/model/filter/FilterMetadata;", "executedTerm", "", "fetchRequestedAt", "", "suggestions", "", "Lcom/amazon/music/find/model/SearchSuggestion;", "(Lcom/amazon/music/views/library/models/PageGridViewModel;ZLcom/amazon/music/find/model/filter/FilterMetadata;Ljava/lang/String;Ljava/lang/Long;Ljava/util/List;)V", "getExecutedTerm", "()Ljava/lang/String;", "getFetchRequestedAt", "()Ljava/lang/Long;", "Ljava/lang/Long;", "getFilterMetadata", "()Lcom/amazon/music/find/model/filter/FilterMetadata;", "()Z", "setEmpty", "(Z)V", "getPageGridViewModel", "()Lcom/amazon/music/views/library/models/PageGridViewModel;", "getSuggestions", "()Ljava/util/List;", "component1", "component2", "component3", "component4", "component5", "component6", "copy", "(Lcom/amazon/music/views/library/models/PageGridViewModel;ZLcom/amazon/music/find/model/filter/FilterMetadata;Ljava/lang/String;Ljava/lang/Long;Ljava/util/List;)Lcom/amazon/music/find/model/FindPageGridViewModel;", "equals", "other", "hashCode", "", "toString", "DMMFindExperience_release"}, k = 1, mv = {1, 6, 0}, xi = 48)
public final /* data */ class FindPageGridViewModel {
    private final String executedTerm;
    private final Long fetchRequestedAt;
    private final FilterMetadata filterMetadata;
    private boolean isEmpty;
    private final PageGridViewModel pageGridViewModel;
    private final List<SearchSuggestion> suggestions;

    /* JADX WARN: Multi-variable type inference failed */
    public static /* synthetic */ FindPageGridViewModel copy$default(FindPageGridViewModel findPageGridViewModel, PageGridViewModel pageGridViewModel, boolean z, FilterMetadata filterMetadata, String str, Long l, List list, int i, Object obj) {
        if ((i & 1) != 0) {
            pageGridViewModel = findPageGridViewModel.pageGridViewModel;
        }
        if ((i & 2) != 0) {
            z = findPageGridViewModel.isEmpty;
        }
        boolean z2 = z;
        if ((i & 4) != 0) {
            filterMetadata = findPageGridViewModel.filterMetadata;
        }
        FilterMetadata filterMetadata2 = filterMetadata;
        if ((i & 8) != 0) {
            str = findPageGridViewModel.executedTerm;
        }
        String str2 = str;
        if ((i & 16) != 0) {
            l = findPageGridViewModel.fetchRequestedAt;
        }
        Long l2 = l;
        if ((i & 32) != 0) {
            list = findPageGridViewModel.suggestions;
        }
        return findPageGridViewModel.copy(pageGridViewModel, z2, filterMetadata2, str2, l2, list);
    }

    /* JADX INFO: renamed from: component1, reason: from getter */
    public final PageGridViewModel getPageGridViewModel() {
        return this.pageGridViewModel;
    }

    /* JADX INFO: renamed from: component2, reason: from getter */
    public final boolean getIsEmpty() {
        return this.isEmpty;
    }

    /* JADX INFO: renamed from: component3, reason: from getter */
    public final FilterMetadata getFilterMetadata() {
        return this.filterMetadata;
    }

    /* JADX INFO: renamed from: component4, reason: from getter */
    public final String getExecutedTerm() {
        return this.executedTerm;
    }

    /* JADX INFO: renamed from: component5, reason: from getter */
    public final Long getFetchRequestedAt() {
        return this.fetchRequestedAt;
    }

    public final List<SearchSuggestion> component6() {
        return this.suggestions;
    }

    public final FindPageGridViewModel copy(PageGridViewModel pageGridViewModel, boolean isEmpty, FilterMetadata filterMetadata, String executedTerm, Long fetchRequestedAt, List<? extends SearchSuggestion> suggestions) {
        return new FindPageGridViewModel(pageGridViewModel, isEmpty, filterMetadata, executedTerm, fetchRequestedAt, suggestions);
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof FindPageGridViewModel)) {
            return false;
        }
        FindPageGridViewModel findPageGridViewModel = (FindPageGridViewModel) other;
        return Intrinsics.areEqual(this.pageGridViewModel, findPageGridViewModel.pageGridViewModel) && this.isEmpty == findPageGridViewModel.isEmpty && Intrinsics.areEqual(this.filterMetadata, findPageGridViewModel.filterMetadata) && Intrinsics.areEqual(this.executedTerm, findPageGridViewModel.executedTerm) && Intrinsics.areEqual(this.fetchRequestedAt, findPageGridViewModel.fetchRequestedAt) && Intrinsics.areEqual(this.suggestions, findPageGridViewModel.suggestions);
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r2v1, types: [int] */
    /* JADX WARN: Type inference failed for: r2v15 */
    /* JADX WARN: Type inference failed for: r2v16 */
    public int hashCode() {
        PageGridViewModel pageGridViewModel = this.pageGridViewModel;
        int iHashCode = (pageGridViewModel == null ? 0 : pageGridViewModel.hashCode()) * 31;
        boolean z = this.isEmpty;
        ?? r2 = z;
        if (z) {
            r2 = 1;
        }
        int i = (iHashCode + r2) * 31;
        FilterMetadata filterMetadata = this.filterMetadata;
        int iHashCode2 = (i + (filterMetadata == null ? 0 : filterMetadata.hashCode())) * 31;
        String str = this.executedTerm;
        int iHashCode3 = (iHashCode2 + (str == null ? 0 : str.hashCode())) * 31;
        Long l = this.fetchRequestedAt;
        int iHashCode4 = (iHashCode3 + (l == null ? 0 : l.hashCode())) * 31;
        List<SearchSuggestion> list = this.suggestions;
        return iHashCode4 + (list != null ? list.hashCode() : 0);
    }

    public String toString() {
        return "FindPageGridViewModel(pageGridViewModel=" + this.pageGridViewModel + ", isEmpty=" + this.isEmpty + ", filterMetadata=" + this.filterMetadata + ", executedTerm=" + ((Object) this.executedTerm) + ", fetchRequestedAt=" + this.fetchRequestedAt + ", suggestions=" + this.suggestions + ')';
    }

    /* JADX WARN: Multi-variable type inference failed */
    public FindPageGridViewModel(PageGridViewModel pageGridViewModel, boolean z, FilterMetadata filterMetadata, String str, Long l, List<? extends SearchSuggestion> list) {
        this.pageGridViewModel = pageGridViewModel;
        this.isEmpty = z;
        this.filterMetadata = filterMetadata;
        this.executedTerm = str;
        this.fetchRequestedAt = l;
        this.suggestions = list;
    }

    public /* synthetic */ FindPageGridViewModel(PageGridViewModel pageGridViewModel, boolean z, FilterMetadata filterMetadata, String str, Long l, List list, int i, DefaultConstructorMarker defaultConstructorMarker) {
        this(pageGridViewModel, z, (i & 4) != 0 ? null : filterMetadata, (i & 8) != 0 ? null : str, (i & 16) != 0 ? null : l, (i & 32) != 0 ? null : list);
    }

    public final PageGridViewModel getPageGridViewModel() {
        return this.pageGridViewModel;
    }

    public final boolean isEmpty() {
        return this.isEmpty;
    }

    public final void setEmpty(boolean z) {
        this.isEmpty = z;
    }

    public final FilterMetadata getFilterMetadata() {
        return this.filterMetadata;
    }

    public final String getExecutedTerm() {
        return this.executedTerm;
    }

    public final Long getFetchRequestedAt() {
        return this.fetchRequestedAt;
    }

    public final List<SearchSuggestion> getSuggestions() {
        return this.suggestions;
    }
}
