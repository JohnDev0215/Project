package com.amazon.music.find.model.filter;

import com.amazon.music.find.utils.PageUriUtils;
import com.amazon.music.views.library.models.FilterItemModel;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.function.Predicate;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt__CollectionsKt;
import kotlin.collections.CollectionsKt__IterablesKt;
import kotlin.collections.CollectionsKt___CollectionsKt;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: SearchFilterSelections.kt */
/* JADX INFO: loaded from: classes4.dex */
@Metadata(d1 = {"\u0000L\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010!\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\u000b\n\u0002\b\u0005\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0010 \n\u0002\b\n\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\b\n\u0002\b\u0007\b\u0086\b\u0018\u0000 .2\u00020\u0001:\u0001.B\u001b\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\f\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00060\u0005¢\u0006\u0002\u0010\u0007J\u0006\u0010\u0010\u001a\u00020\u0011J\u000e\u0010\u0012\u001a\u00020\u00112\u0006\u0010\u0013\u001a\u00020\u0014J*\u0010\u0015\u001a\u00020\u000b\"\u0004\b\u0000\u0010\u00162\f\u0010\u0017\u001a\b\u0012\u0004\u0012\u0002H\u00160\u00182\f\u0010\u0019\u001a\b\u0012\u0004\u0012\u0002H\u00160\u0018H\u0002J\t\u0010\u001a\u001a\u00020\u0003HÆ\u0003J\u000f\u0010\u001b\u001a\b\u0012\u0004\u0012\u00020\u00060\u0005HÆ\u0003J\u000e\u0010\u001c\u001a\u00020\u000b2\u0006\u0010\u0013\u001a\u00020\u0014J#\u0010\u001d\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\u000e\b\u0002\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00060\u0005HÆ\u0001J\u0006\u0010\u001e\u001a\u00020\u0000J\u0013\u0010\u001f\u001a\u00020\u000b2\b\u0010 \u001a\u0004\u0018\u00010\u0001HÖ\u0003J\f\u0010!\u001a\b\u0012\u0004\u0012\u00020\u00060\u0018J\f\u0010\"\u001a\b\u0012\u0004\u0012\u00020#0\u0018J\f\u0010$\u001a\b\u0012\u0004\u0012\u00020\u00060\u0018J\u0006\u0010%\u001a\u00020\u000bJ\u0006\u0010&\u001a\u00020\u000bJ\t\u0010'\u001a\u00020(HÖ\u0001J\u0006\u0010)\u001a\u00020\u0011J\u000e\u0010*\u001a\u00020\u00112\u0006\u0010\u0013\u001a\u00020\u0014J\u0006\u0010+\u001a\u00020\u0011J\u0014\u0010,\u001a\u00020\u000b2\f\u0010\u0019\u001a\b\u0012\u0004\u0012\u00020\u00060\u0018J\t\u0010-\u001a\u00020\u0014HÖ\u0001R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\b\u0010\tR\u0011\u0010\n\u001a\u00020\u000b8F¢\u0006\u0006\u001a\u0004\b\f\u0010\rR\u0017\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00060\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u000e\u0010\u000f¨\u0006/"}, d2 = {"Lcom/amazon/music/find/model/filter/SearchFilterSelections;", "", "filterMetadata", "Lcom/amazon/music/find/model/filter/FilterMetadata;", "selections", "", "Lcom/amazon/music/find/model/filter/FilterElement;", "(Lcom/amazon/music/find/model/filter/FilterMetadata;Ljava/util/List;)V", "getFilterMetadata", "()Lcom/amazon/music/find/model/filter/FilterMetadata;", "hasActiveFilters", "", "getHasActiveFilters", "()Z", "getSelections", "()Ljava/util/List;", "addMyMusicFilter", "", "addSelection", "id", "", "compareLists", "T", PageUriUtils.SOURCE_QUERY_PARAM, "", "target", "component1", "component2", "containsSelection", "copy", "deepCopy", "equals", "other", "getAllAudioQualityFilters", "getFilterItemModels", "Lcom/amazon/music/views/library/models/FilterItemModel;", "getSelectedAudioQualityFilters", "hasAudioQualityFilters", "hasMyMusicFilter", "hashCode", "", "removeMyMusicFilter", "removeSelection", "reset", "selectionsEqual", "toString", "Companion", "DMMFindExperience_release"}, k = 1, mv = {1, 6, 0}, xi = 48)
public final /* data */ class SearchFilterSelections {

    /* JADX INFO: renamed from: Companion, reason: from kotlin metadata */
    public static final Companion INSTANCE = new Companion(null);
    private static final SearchFilterSelections EMPTY_SELECTION = new SearchFilterSelections(new FilterMetadata(CollectionsKt__CollectionsKt.emptyList()), new ArrayList());
    private final FilterMetadata filterMetadata;
    private final List<FilterElement> selections;

    /* JADX WARN: Multi-variable type inference failed */
    public static /* synthetic */ SearchFilterSelections copy$default(SearchFilterSelections searchFilterSelections, FilterMetadata filterMetadata, List list, int i, Object obj) {
        if ((i & 1) != 0) {
            filterMetadata = searchFilterSelections.filterMetadata;
        }
        if ((i & 2) != 0) {
            list = searchFilterSelections.selections;
        }
        return searchFilterSelections.copy(filterMetadata, list);
    }

    /* JADX INFO: renamed from: component1, reason: from getter */
    public final FilterMetadata getFilterMetadata() {
        return this.filterMetadata;
    }

    public final List<FilterElement> component2() {
        return this.selections;
    }

    public final SearchFilterSelections copy(FilterMetadata filterMetadata, List<FilterElement> selections) {
        Intrinsics.checkNotNullParameter(filterMetadata, "filterMetadata");
        Intrinsics.checkNotNullParameter(selections, "selections");
        return new SearchFilterSelections(filterMetadata, selections);
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof SearchFilterSelections)) {
            return false;
        }
        SearchFilterSelections searchFilterSelections = (SearchFilterSelections) other;
        return Intrinsics.areEqual(this.filterMetadata, searchFilterSelections.filterMetadata) && Intrinsics.areEqual(this.selections, searchFilterSelections.selections);
    }

    public int hashCode() {
        return (this.filterMetadata.hashCode() * 31) + this.selections.hashCode();
    }

    public String toString() {
        return "SearchFilterSelections(filterMetadata=" + this.filterMetadata + ", selections=" + this.selections + ')';
    }

    public SearchFilterSelections(FilterMetadata filterMetadata, List<FilterElement> selections) {
        Intrinsics.checkNotNullParameter(filterMetadata, "filterMetadata");
        Intrinsics.checkNotNullParameter(selections, "selections");
        this.filterMetadata = filterMetadata;
        this.selections = selections;
    }

    public final FilterMetadata getFilterMetadata() {
        return this.filterMetadata;
    }

    public final List<FilterElement> getSelections() {
        return this.selections;
    }

    /* JADX INFO: compiled from: SearchFilterSelections.kt */
    @Metadata(d1 = {"\u0000\u0014\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002R\u0011\u0010\u0003\u001a\u00020\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0005\u0010\u0006¨\u0006\u0007"}, d2 = {"Lcom/amazon/music/find/model/filter/SearchFilterSelections$Companion;", "", "()V", "EMPTY_SELECTION", "Lcom/amazon/music/find/model/filter/SearchFilterSelections;", "getEMPTY_SELECTION", "()Lcom/amazon/music/find/model/filter/SearchFilterSelections;", "DMMFindExperience_release"}, k = 1, mv = {1, 6, 0}, xi = 48)
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }

        public final SearchFilterSelections getEMPTY_SELECTION() {
            return SearchFilterSelections.EMPTY_SELECTION;
        }
    }

    public final boolean getHasActiveFilters() {
        return !this.selections.isEmpty();
    }

    public final void reset() {
        this.selections.clear();
    }

    public final List<FilterItemModel> getFilterItemModels() {
        List<FilterItemModel> allFilterItemModels = this.filterMetadata.getAllFilterItemModels();
        for (FilterItemModel filterItemModel : allFilterItemModels) {
            String browseId = filterItemModel.getBrowseId();
            filterItemModel.setSelected(browseId == null ? false : containsSelection(browseId));
        }
        return allFilterItemModels;
    }

    public final boolean hasMyMusicFilter() {
        return containsSelection(FilterMetadata.MY_MUSIC_FILTER_ID);
    }

    public final void addMyMusicFilter() {
        if (hasMyMusicFilter()) {
            return;
        }
        addSelection(FilterMetadata.MY_MUSIC_FILTER_ID);
    }

    public final void removeMyMusicFilter() {
        if (hasMyMusicFilter()) {
            removeSelection(FilterMetadata.MY_MUSIC_FILTER_ID);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: removeSelection$lambda-2, reason: not valid java name */
    public static final boolean m2037removeSelection$lambda2(String id, FilterElement it) {
        Intrinsics.checkNotNullParameter(id, "$id");
        Intrinsics.checkNotNullParameter(it, "it");
        return Intrinsics.areEqual(it.getId(), id);
    }

    public final void removeSelection(final String id) {
        Intrinsics.checkNotNullParameter(id, "id");
        this.selections.removeIf(new Predicate() { // from class: com.amazon.music.find.model.filter.SearchFilterSelections$$ExternalSyntheticLambda0
            @Override // java.util.function.Predicate
            public final boolean test(Object obj) {
                return SearchFilterSelections.m2037removeSelection$lambda2(id, (FilterElement) obj);
            }
        });
    }

    public final void addSelection(String id) {
        Intrinsics.checkNotNullParameter(id, "id");
        this.selections.add(new FilterElement(id, null, false, 6, null));
    }

    public final boolean containsSelection(String id) {
        Intrinsics.checkNotNullParameter(id, "id");
        List<FilterElement> list = this.selections;
        ArrayList arrayList = new ArrayList(CollectionsKt__IterablesKt.collectionSizeOrDefault(list, 10));
        Iterator<T> it = list.iterator();
        while (it.hasNext()) {
            arrayList.add(((FilterElement) it.next()).getId());
        }
        return arrayList.contains(id);
    }

    public final boolean selectionsEqual(List<FilterElement> target) {
        Intrinsics.checkNotNullParameter(target, "target");
        return compareLists(this.selections, target);
    }

    public final SearchFilterSelections deepCopy() {
        FilterMetadata filterMetadataDeepCopy = this.filterMetadata.deepCopy();
        List<FilterElement> list = this.selections;
        ArrayList arrayList = new ArrayList(CollectionsKt__IterablesKt.collectionSizeOrDefault(list, 10));
        Iterator<T> it = list.iterator();
        while (it.hasNext()) {
            arrayList.add(((FilterElement) it.next()).deepCopy());
        }
        return new SearchFilterSelections(filterMetadataDeepCopy, CollectionsKt___CollectionsKt.toMutableList((Collection) arrayList));
    }

    public final List<FilterElement> getSelectedAudioQualityFilters() {
        List<FilterElement> list = this.selections;
        ArrayList arrayList = new ArrayList();
        for (Object obj : list) {
            FilterElement filterElement = (FilterElement) obj;
            if (Intrinsics.areEqual(filterElement.getId(), FilterMetadata.UHD_FILTER_ID) || Intrinsics.areEqual(filterElement.getId(), FilterMetadata.SONY_360_FILTER_ID) || Intrinsics.areEqual(filterElement.getId(), FilterMetadata.DOLBY_ATMOS_FILTER_ID)) {
                arrayList.add(obj);
            }
        }
        return arrayList;
    }

    public final boolean hasAudioQualityFilters() {
        return !getSelectedAudioQualityFilters().isEmpty();
    }

    public final List<FilterElement> getAllAudioQualityFilters() {
        return this.filterMetadata.getAllAudioQualityFilters();
    }

    private final <T> boolean compareLists(List<? extends T> source, List<? extends T> target) {
        return source.containsAll(target) && target.containsAll(source);
    }
}
