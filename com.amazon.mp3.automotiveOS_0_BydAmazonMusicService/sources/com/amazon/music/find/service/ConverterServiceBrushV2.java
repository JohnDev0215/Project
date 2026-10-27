package com.amazon.music.find.service;

import com.amazon.music.find.converter.BrushV2Converter;
import com.amazon.music.find.model.SearchSuggestion;
import com.amazon.music.find.model.search.SearchResponse;
import com.amazon.music.find.model.search.SeeMoreResponse;
import com.amazon.music.find.service.ConverterService;
import com.amazon.music.platform.find.service.history.SearchHistoryItem;
import com.amazon.music.search.SpellCorrections;
import com.amazon.music.views.library.models.base.BaseViewModel;
import com.amazon.musicensembleservice.brush.PageResponse;
import com.google.android.gms.actions.SearchIntents;
import java.util.List;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: ConverterServiceBrushV2.kt */
/* JADX INFO: loaded from: classes4.dex */
@Metadata(d1 = {"\u0000N\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010 \n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0002\b\u0006\u0018\u00002\u00020\u0001B=\u0012\f\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003\u0012\f\u0010\u0005\u001a\b\u0012\u0004\u0012\u00020\u00060\u0003\u0012\f\u0010\u0007\u001a\b\u0012\u0004\u0012\u00020\b0\u0003\u0012\f\u0010\t\u001a\b\u0012\u0004\u0012\u00020\n0\u0003¢\u0006\u0002\u0010\u000bJ\b\u0010\f\u001a\u00020\rH\u0016J\u001a\u0010\u000e\u001a\u00020\r2\u0006\u0010\u000f\u001a\u00020\u00102\b\u0010\u0011\u001a\u0004\u0018\u00010\u0012H\u0016J\b\u0010\u0013\u001a\u00020\rH\u0016J\u0010\u0010\u0014\u001a\u00020\r2\u0006\u0010\u000f\u001a\u00020\u0010H\u0016J\u0016\u0010\u0015\u001a\u00020\r2\f\u0010\u0016\u001a\b\u0012\u0004\u0012\u00020\u00040\u0017H\u0016J \u0010\u0018\u001a\u00020\r2\u0006\u0010\u0019\u001a\u00020\b2\u0006\u0010\u001a\u001a\u00020\u001b2\u0006\u0010\u001c\u001a\u00020\u001bH\u0016J\u0016\u0010\u001d\u001a\u00020\r2\f\u0010\u001e\u001a\b\u0012\u0004\u0012\u00020\u00060\u0017H\u0016J \u0010\u001f\u001a\u00020\r2\u0006\u0010 \u001a\u00020\n2\u0006\u0010\u001a\u001a\u00020\u001b2\u0006\u0010\u001c\u001a\u00020\u001bH\u0016R\u0014\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003X\u0082\u0004¢\u0006\u0002\n\u0000R\u0014\u0010\u0007\u001a\b\u0012\u0004\u0012\u00020\b0\u0003X\u0082\u0004¢\u0006\u0002\n\u0000R\u0014\u0010\u0005\u001a\b\u0012\u0004\u0012\u00020\u00060\u0003X\u0082\u0004¢\u0006\u0002\n\u0000R\u0014\u0010\t\u001a\b\u0012\u0004\u0012\u00020\n0\u0003X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006!"}, d2 = {"Lcom/amazon/music/find/service/ConverterServiceBrushV2;", "Lcom/amazon/music/find/service/ConverterService;", "searchHistoryConverter", "Lcom/amazon/music/find/converter/BrushV2Converter;", "Lcom/amazon/music/platform/find/service/history/SearchHistoryItem;", "searchSuggestionConverter", "Lcom/amazon/music/find/model/SearchSuggestion;", "searchResultConverter", "Lcom/amazon/music/find/model/search/SearchResponse;", "seeMoreResultConverter", "Lcom/amazon/music/find/model/search/SeeMoreResponse;", "(Lcom/amazon/music/find/converter/BrushV2Converter;Lcom/amazon/music/find/converter/BrushV2Converter;Lcom/amazon/music/find/converter/BrushV2Converter;Lcom/amazon/music/find/converter/BrushV2Converter;)V", "convertEmptySearchHistory", "Lcom/amazon/musicensembleservice/brush/PageResponse;", "convertEmptySearchResults", SearchIntents.EXTRA_QUERY, "", "spellCorrections", "Lcom/amazon/music/search/SpellCorrections;", "convertEmptySearchSuggestion", "convertEmptySeeMoreResults", "convertSearchHistory", "searchHistoryItems", "", "convertSearchResults", "searchResult", "showOrdinal", "", "showSpellCorrections", "convertSearchSuggestion", "searchSuggestions", "convertSeeMoreResults", "seeMoreResult", "DMMFindExperience_release"}, k = 1, mv = {1, 6, 0}, xi = 48)
public final class ConverterServiceBrushV2 implements ConverterService {
    private final BrushV2Converter<SearchHistoryItem> searchHistoryConverter;
    private final BrushV2Converter<SearchResponse> searchResultConverter;
    private final BrushV2Converter<SearchSuggestion> searchSuggestionConverter;
    private final BrushV2Converter<SeeMoreResponse> seeMoreResultConverter;

    public ConverterServiceBrushV2(BrushV2Converter<SearchHistoryItem> searchHistoryConverter, BrushV2Converter<SearchSuggestion> searchSuggestionConverter, BrushV2Converter<SearchResponse> searchResultConverter, BrushV2Converter<SeeMoreResponse> seeMoreResultConverter) {
        Intrinsics.checkNotNullParameter(searchHistoryConverter, "searchHistoryConverter");
        Intrinsics.checkNotNullParameter(searchSuggestionConverter, "searchSuggestionConverter");
        Intrinsics.checkNotNullParameter(searchResultConverter, "searchResultConverter");
        Intrinsics.checkNotNullParameter(seeMoreResultConverter, "seeMoreResultConverter");
        this.searchHistoryConverter = searchHistoryConverter;
        this.searchSuggestionConverter = searchSuggestionConverter;
        this.searchResultConverter = searchResultConverter;
        this.seeMoreResultConverter = seeMoreResultConverter;
    }

    @Override // com.amazon.music.find.service.ConverterService
    public List<BaseViewModel> processChildrenModels(List<? extends BaseViewModel> list) {
        return ConverterService.DefaultImpls.processChildrenModels(this, list);
    }

    @Override // com.amazon.music.find.service.ConverterService
    public PageResponse convertSearchHistory(List<? extends SearchHistoryItem> searchHistoryItems) {
        Intrinsics.checkNotNullParameter(searchHistoryItems, "searchHistoryItems");
        return this.searchHistoryConverter.convert(searchHistoryItems);
    }

    @Override // com.amazon.music.find.service.ConverterService
    public PageResponse convertEmptySearchHistory() {
        return new PageResponse();
    }

    @Override // com.amazon.music.find.service.ConverterService
    public PageResponse convertSearchSuggestion(List<? extends SearchSuggestion> searchSuggestions) {
        Intrinsics.checkNotNullParameter(searchSuggestions, "searchSuggestions");
        return this.searchSuggestionConverter.convert(searchSuggestions);
    }

    @Override // com.amazon.music.find.service.ConverterService
    public PageResponse convertEmptySearchSuggestion() {
        return new PageResponse();
    }

    @Override // com.amazon.music.find.service.ConverterService
    public PageResponse convertEmptySearchResults(String query, SpellCorrections spellCorrections) {
        Intrinsics.checkNotNullParameter(query, "query");
        return this.searchResultConverter.convertEmptyResponse(query, spellCorrections);
    }

    @Override // com.amazon.music.find.service.ConverterService
    public PageResponse convertSearchResults(SearchResponse searchResult, boolean showOrdinal, boolean showSpellCorrections) {
        Intrinsics.checkNotNullParameter(searchResult, "searchResult");
        return this.searchResultConverter.convert(searchResult, showOrdinal, showSpellCorrections);
    }

    @Override // com.amazon.music.find.service.ConverterService
    public PageResponse convertSeeMoreResults(SeeMoreResponse seeMoreResult, boolean showOrdinal, boolean showSpellCorrections) {
        Intrinsics.checkNotNullParameter(seeMoreResult, "seeMoreResult");
        return this.seeMoreResultConverter.convert(seeMoreResult, showOrdinal, showSpellCorrections);
    }

    @Override // com.amazon.music.find.service.ConverterService
    public PageResponse convertEmptySeeMoreResults(String query) {
        Intrinsics.checkNotNullParameter(query, "query");
        return this.seeMoreResultConverter.convertEmptyResponse(query, null);
    }
}
