package com.amazon.music.find.service;

import com.amazon.music.find.converter.BrushV2Converter;
import com.amazon.music.find.model.SearchSuggestion;
import com.amazon.music.find.model.search.SearchResponse;
import com.amazon.music.find.model.search.SeeMoreResponse;
import com.amazon.music.find.service.ConverterService;
import com.amazon.music.platform.find.service.history.SearchHistoryItem;
import com.amazon.music.search.SpellCorrections;
import com.amazon.music.views.library.models.SimpleHorizontalTileModel;
import com.amazon.music.views.library.models.base.BaseViewModel;
import com.amazon.musicensembleservice.brush.PageResponse;
import com.google.android.gms.actions.SearchIntents;
import java.util.ArrayList;
import java.util.List;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt__CollectionsKt;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: CarModeConverterServiceBrushV2.kt */
/* JADX INFO: loaded from: classes4.dex */
@Metadata(d1 = {"\u0000B\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0002\b\u0004\u0018\u00002\u00020\u0001B\u0013\u0012\f\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003¢\u0006\u0002\u0010\u0005J\u0016\u0010\u0006\u001a\u00020\u00072\f\u0010\b\u001a\b\u0012\u0004\u0012\u00020\n0\tH\u0002J\u001a\u0010\u000b\u001a\u00020\f2\u0006\u0010\r\u001a\u00020\u000e2\b\u0010\u000f\u001a\u0004\u0018\u00010\u0010H\u0016J \u0010\u0011\u001a\u00020\f2\u0006\u0010\u0012\u001a\u00020\u00042\u0006\u0010\u0013\u001a\u00020\u00142\u0006\u0010\u0015\u001a\u00020\u0014H\u0016J\u001e\u0010\u0016\u001a\b\u0012\u0004\u0012\u00020\n0\t2\u000e\u0010\u0017\u001a\n\u0012\u0004\u0012\u00020\n\u0018\u00010\tH\u0016R\u0014\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u0018"}, d2 = {"Lcom/amazon/music/find/service/CarModeConverterServiceBrushV2;", "Lcom/amazon/music/find/service/ConverterService;", "searchResultConverter", "Lcom/amazon/music/find/converter/BrushV2Converter;", "Lcom/amazon/music/find/model/search/SearchResponse;", "(Lcom/amazon/music/find/converter/BrushV2Converter;)V", "addOrdinalsToModels", "", "models", "", "Lcom/amazon/music/views/library/models/base/BaseViewModel;", "convertEmptySearchResults", "Lcom/amazon/musicensembleservice/brush/PageResponse;", SearchIntents.EXTRA_QUERY, "", "spellCorrections", "Lcom/amazon/music/search/SpellCorrections;", "convertSearchResults", "searchResult", "showOrdinal", "", "showSpellCorrections", "processChildrenModels", "childrenModels", "DMMFindExperience_release"}, k = 1, mv = {1, 6, 0}, xi = 48)
public final class CarModeConverterServiceBrushV2 implements ConverterService {
    private final BrushV2Converter<SearchResponse> searchResultConverter;

    public CarModeConverterServiceBrushV2(BrushV2Converter<SearchResponse> searchResultConverter) {
        Intrinsics.checkNotNullParameter(searchResultConverter, "searchResultConverter");
        this.searchResultConverter = searchResultConverter;
    }

    @Override // com.amazon.music.find.service.ConverterService
    public PageResponse convertEmptySearchHistory() {
        return ConverterService.DefaultImpls.convertEmptySearchHistory(this);
    }

    @Override // com.amazon.music.find.service.ConverterService
    public PageResponse convertEmptySearchSuggestion() {
        return ConverterService.DefaultImpls.convertEmptySearchSuggestion(this);
    }

    @Override // com.amazon.music.find.service.ConverterService
    public PageResponse convertEmptySeeMoreResults(String str) {
        return ConverterService.DefaultImpls.convertEmptySeeMoreResults(this, str);
    }

    @Override // com.amazon.music.find.service.ConverterService
    public PageResponse convertSearchHistory(List<? extends SearchHistoryItem> list) {
        return ConverterService.DefaultImpls.convertSearchHistory(this, list);
    }

    @Override // com.amazon.music.find.service.ConverterService
    public PageResponse convertSearchSuggestion(List<? extends SearchSuggestion> list) {
        return ConverterService.DefaultImpls.convertSearchSuggestion(this, list);
    }

    @Override // com.amazon.music.find.service.ConverterService
    public PageResponse convertSeeMoreResults(SeeMoreResponse seeMoreResponse, boolean z, boolean z2) {
        return ConverterService.DefaultImpls.convertSeeMoreResults(this, seeMoreResponse, z, z2);
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

    /* JADX WARN: Multi-variable type inference failed */
    @Override // com.amazon.music.find.service.ConverterService
    public List<BaseViewModel> processChildrenModels(List<? extends BaseViewModel> childrenModels) {
        if (childrenModels == 0 || childrenModels.isEmpty()) {
            return CollectionsKt__CollectionsKt.emptyList();
        }
        addOrdinalsToModels(childrenModels);
        return childrenModels;
    }

    private final void addOrdinalsToModels(List<? extends BaseViewModel> models) {
        ArrayList arrayList = new ArrayList();
        for (Object obj : models) {
            if (obj instanceof SimpleHorizontalTileModel) {
                arrayList.add(obj);
            }
        }
        int i = 0;
        for (Object obj2 : arrayList) {
            int i2 = i + 1;
            if (i < 0) {
                CollectionsKt__CollectionsKt.throwIndexOverflow();
            }
            ((SimpleHorizontalTileModel) obj2).setOrdinal(Integer.valueOf(i2));
            i = i2;
        }
    }
}
