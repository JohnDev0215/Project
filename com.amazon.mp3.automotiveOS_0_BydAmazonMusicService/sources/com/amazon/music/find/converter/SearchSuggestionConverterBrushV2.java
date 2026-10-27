package com.amazon.music.find.converter;

import android.content.Context;
import com.amazon.music.find.R;
import com.amazon.music.find.model.FindRenderingType;
import com.amazon.music.find.model.SearchSuggestion;
import com.amazon.music.find.model.SearchSuggestionEntity;
import com.amazon.music.find.model.SearchSuggestionText;
import com.amazon.music.find.model.configuration.InstantSearchSuggestionMode;
import com.amazon.music.find.providers.CacheImageProvider;
import com.amazon.music.find.providers.SearchFeaturesProvider;
import com.amazon.music.find.service.UIConfigurationService;
import com.amazon.musicensembleservice.brush.Block;
import com.amazon.musicensembleservice.brush.Content;
import com.amazon.musicensembleservice.brush.PageResponse;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Reflection;
import kotlin.reflect.KClass;

/* JADX INFO: compiled from: SearchSuggestionConverterBrushV2.kt */
/* JADX INFO: loaded from: classes4.dex */
@Metadata(d1 = {"\u00006\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010 \n\u0002\b\u0002\b\u0016\u0018\u0000 \u00102\b\u0012\u0004\u0012\u00020\u00020\u0001:\u0001\u0010B%\u0012\u0006\u0010\u0003\u001a\u00020\u0004\u0012\u0006\u0010\u0005\u001a\u00020\u0006\u0012\u0006\u0010\u0007\u001a\u00020\b\u0012\u0006\u0010\t\u001a\u00020\n¢\u0006\u0002\u0010\u000bJ\u0016\u0010\f\u001a\u00020\r2\f\u0010\u000e\u001a\b\u0012\u0004\u0012\u00020\u00020\u000fH\u0016R\u000e\u0010\t\u001a\u00020\nX\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u0011"}, d2 = {"Lcom/amazon/music/find/converter/SearchSuggestionConverterBrushV2;", "Lcom/amazon/music/find/converter/BrushV2Converter;", "Lcom/amazon/music/find/model/SearchSuggestion;", "context", "Landroid/content/Context;", "cacheImageProvider", "Lcom/amazon/music/find/providers/CacheImageProvider;", "searchFeaturesProvider", "Lcom/amazon/music/find/providers/SearchFeaturesProvider;", "uiConfigurationService", "Lcom/amazon/music/find/service/UIConfigurationService;", "(Landroid/content/Context;Lcom/amazon/music/find/providers/CacheImageProvider;Lcom/amazon/music/find/providers/SearchFeaturesProvider;Lcom/amazon/music/find/service/UIConfigurationService;)V", "convert", "Lcom/amazon/musicensembleservice/brush/PageResponse;", "entities", "", "Companion", "DMMFindExperience_release"}, k = 1, mv = {1, 6, 0}, xi = 48)
public class SearchSuggestionConverterBrushV2 extends BrushV2Converter<SearchSuggestion> {
    private static final String TITLE = "Find";
    private final UIConfigurationService uiConfigurationService;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public SearchSuggestionConverterBrushV2(Context context, CacheImageProvider cacheImageProvider, SearchFeaturesProvider searchFeaturesProvider, UIConfigurationService uiConfigurationService) {
        super(context, cacheImageProvider, searchFeaturesProvider);
        Intrinsics.checkNotNullParameter(context, "context");
        Intrinsics.checkNotNullParameter(cacheImageProvider, "cacheImageProvider");
        Intrinsics.checkNotNullParameter(searchFeaturesProvider, "searchFeaturesProvider");
        Intrinsics.checkNotNullParameter(uiConfigurationService, "uiConfigurationService");
        this.uiConfigurationService = uiConfigurationService;
    }

    @Override // com.amazon.music.find.converter.BrushV2Converter
    public PageResponse convert(List<? extends SearchSuggestion> entities) {
        String strName;
        Intrinsics.checkNotNullParameter(entities, "entities");
        LinkedHashMap linkedHashMap = new LinkedHashMap();
        for (Object obj : entities) {
            KClass orCreateKotlinClass = Reflection.getOrCreateKotlinClass(((SearchSuggestion) obj).getClass());
            Object arrayList = linkedHashMap.get(orCreateKotlinClass);
            if (arrayList == null) {
                arrayList = new ArrayList();
                linkedHashMap.put(orCreateKotlinClass, arrayList);
            }
            ((List) arrayList).add(obj);
        }
        int i = 0;
        ArrayList arrayList2 = new ArrayList(linkedHashMap.size());
        for (Map.Entry entry : linkedHashMap.entrySet()) {
            Block block = new Block();
            if (i == 0) {
                block.setTitle(getContext().getResources().getString(R.string.search_suggestions_header));
            }
            block.setContent(new Content());
            InstantSearchSuggestionMode suggestionMode = this.uiConfigurationService.getSearchSuggestionConfiguration().getInstantSearchConfig().getSuggestionMode();
            if (Intrinsics.areEqual(entry.getKey(), Reflection.getOrCreateKotlinClass(SearchSuggestionText.class))) {
                if (suggestionMode == InstantSearchSuggestionMode.DISABLED) {
                    strName = FindRenderingType.COMPACT_LINK_NAVIGATOR_LEGACY.name();
                } else {
                    strName = FindRenderingType.COMPACT_LINK_NAVIGATOR.name();
                }
                block.setRenderingType(strName);
            } else if (Intrinsics.areEqual(entry.getKey(), Reflection.getOrCreateKotlinClass(SearchSuggestionEntity.class))) {
                block.setRenderingType(FindRenderingType.COMPACT_DESCRIPTIVE_SHOWCASE.name());
            }
            block.getContent().setEntities(getMusicEntitiesForSuggestions((List) entry.getValue()));
            i++;
            arrayList2.add(block);
        }
        PageResponse pageResponse = new PageResponse();
        pageResponse.setTitle(TITLE);
        pageResponse.setContent(new Content());
        pageResponse.getContent().setBlocks(arrayList2);
        return pageResponse;
    }
}
