package com.amazon.music.find.service;

import com.amazon.music.find.model.SearchSuggestion;
import com.amazon.music.find.model.search.SearchResponse;
import com.amazon.music.find.model.search.SeeMoreResponse;
import com.amazon.music.platform.find.service.history.SearchHistoryItem;
import com.amazon.music.search.SpellCorrections;
import com.amazon.music.views.library.models.base.BaseViewModel;
import com.amazon.musicensembleservice.brush.PageResponse;
import com.google.android.gms.actions.SearchIntents;
import java.util.List;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt__CollectionsKt;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: ConverterService.kt */
/* JADX INFO: loaded from: classes4.dex */
@Metadata(d1 = {"\u0000P\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\bf\u0018\u00002\u00020\u0001J\b\u0010\u0002\u001a\u00020\u0003H\u0016J\u001a\u0010\u0004\u001a\u00020\u00032\u0006\u0010\u0005\u001a\u00020\u00062\b\u0010\u0007\u001a\u0004\u0018\u00010\bH\u0016J\b\u0010\t\u001a\u00020\u0003H\u0016J\u0010\u0010\n\u001a\u00020\u00032\u0006\u0010\u0005\u001a\u00020\u0006H\u0016J\u0016\u0010\u000b\u001a\u00020\u00032\f\u0010\f\u001a\b\u0012\u0004\u0012\u00020\u000e0\rH\u0016J \u0010\u000f\u001a\u00020\u00032\u0006\u0010\u0010\u001a\u00020\u00112\u0006\u0010\u0012\u001a\u00020\u00132\u0006\u0010\u0014\u001a\u00020\u0013H\u0016J\u0016\u0010\u0015\u001a\u00020\u00032\f\u0010\u0016\u001a\b\u0012\u0004\u0012\u00020\u00170\rH\u0016J \u0010\u0018\u001a\u00020\u00032\u0006\u0010\u0019\u001a\u00020\u001a2\u0006\u0010\u0012\u001a\u00020\u00132\u0006\u0010\u0014\u001a\u00020\u0013H\u0016J\u001e\u0010\u001b\u001a\b\u0012\u0004\u0012\u00020\u001c0\r2\u000e\u0010\u001d\u001a\n\u0012\u0004\u0012\u00020\u001c\u0018\u00010\rH\u0016¨\u0006\u001e"}, d2 = {"Lcom/amazon/music/find/service/ConverterService;", "", "convertEmptySearchHistory", "Lcom/amazon/musicensembleservice/brush/PageResponse;", "convertEmptySearchResults", SearchIntents.EXTRA_QUERY, "", "spellCorrections", "Lcom/amazon/music/search/SpellCorrections;", "convertEmptySearchSuggestion", "convertEmptySeeMoreResults", "convertSearchHistory", "searchHistoryQueries", "", "Lcom/amazon/music/platform/find/service/history/SearchHistoryItem;", "convertSearchResults", "searchResult", "Lcom/amazon/music/find/model/search/SearchResponse;", "showOrdinal", "", "showSpellCorrections", "convertSearchSuggestion", "searchSuggestions", "Lcom/amazon/music/find/model/SearchSuggestion;", "convertSeeMoreResults", "seeMoreResult", "Lcom/amazon/music/find/model/search/SeeMoreResponse;", "processChildrenModels", "Lcom/amazon/music/views/library/models/base/BaseViewModel;", "childrenModels", "DMMFindExperience_release"}, k = 1, mv = {1, 6, 0}, xi = 48)
public interface ConverterService {
    PageResponse convertEmptySearchHistory();

    PageResponse convertEmptySearchResults(String query, SpellCorrections spellCorrections);

    PageResponse convertEmptySearchSuggestion();

    PageResponse convertEmptySeeMoreResults(String query);

    PageResponse convertSearchHistory(List<? extends SearchHistoryItem> searchHistoryQueries);

    PageResponse convertSearchResults(SearchResponse searchResult, boolean showOrdinal, boolean showSpellCorrections);

    PageResponse convertSearchSuggestion(List<? extends SearchSuggestion> searchSuggestions);

    PageResponse convertSeeMoreResults(SeeMoreResponse seeMoreResult, boolean showOrdinal, boolean showSpellCorrections);

    List<BaseViewModel> processChildrenModels(List<? extends BaseViewModel> childrenModels);

    /* JADX INFO: compiled from: ConverterService.kt */
    @Metadata(k = 3, mv = {1, 6, 0}, xi = 48)
    public static final class DefaultImpls {
        public static PageResponse convertSearchHistory(ConverterService converterService, List<? extends SearchHistoryItem> searchHistoryQueries) {
            Intrinsics.checkNotNullParameter(converterService, "this");
            Intrinsics.checkNotNullParameter(searchHistoryQueries, "searchHistoryQueries");
            return new PageResponse();
        }

        public static PageResponse convertEmptySearchHistory(ConverterService converterService) {
            Intrinsics.checkNotNullParameter(converterService, "this");
            return new PageResponse();
        }

        public static PageResponse convertSearchSuggestion(ConverterService converterService, List<? extends SearchSuggestion> searchSuggestions) {
            Intrinsics.checkNotNullParameter(converterService, "this");
            Intrinsics.checkNotNullParameter(searchSuggestions, "searchSuggestions");
            return new PageResponse();
        }

        public static PageResponse convertEmptySearchSuggestion(ConverterService converterService) {
            Intrinsics.checkNotNullParameter(converterService, "this");
            return new PageResponse();
        }

        public static PageResponse convertEmptySearchResults(ConverterService converterService, String query, SpellCorrections spellCorrections) {
            Intrinsics.checkNotNullParameter(converterService, "this");
            Intrinsics.checkNotNullParameter(query, "query");
            return new PageResponse();
        }

        public static PageResponse convertSearchResults(ConverterService converterService, SearchResponse searchResult, boolean z, boolean z2) {
            Intrinsics.checkNotNullParameter(converterService, "this");
            Intrinsics.checkNotNullParameter(searchResult, "searchResult");
            return new PageResponse();
        }

        public static PageResponse convertSeeMoreResults(ConverterService converterService, SeeMoreResponse seeMoreResult, boolean z, boolean z2) {
            Intrinsics.checkNotNullParameter(converterService, "this");
            Intrinsics.checkNotNullParameter(seeMoreResult, "seeMoreResult");
            return new PageResponse();
        }

        public static PageResponse convertEmptySeeMoreResults(ConverterService converterService, String query) {
            Intrinsics.checkNotNullParameter(converterService, "this");
            Intrinsics.checkNotNullParameter(query, "query");
            return new PageResponse();
        }

        public static List<BaseViewModel> processChildrenModels(ConverterService converterService, List<? extends BaseViewModel> list) {
            Intrinsics.checkNotNullParameter(converterService, "this");
            return CollectionsKt__CollectionsKt.emptyList();
        }
    }
}
