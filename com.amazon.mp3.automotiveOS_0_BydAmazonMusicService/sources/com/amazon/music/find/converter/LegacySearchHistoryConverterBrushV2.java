package com.amazon.music.find.converter;

import android.content.Context;
import com.amazon.music.find.R;
import com.amazon.music.find.model.FindRenderingType;
import com.amazon.music.find.providers.CacheImageProvider;
import com.amazon.music.find.providers.SearchFeaturesProvider;
import com.amazon.music.platform.find.service.history.SearchHistoryItem;
import com.amazon.musicensembleservice.brush.Block;
import com.amazon.musicensembleservice.brush.Content;
import com.amazon.musicensembleservice.brush.PageResponse;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt__CollectionsJVMKt;
import kotlin.collections.CollectionsKt__IterablesKt;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: LegacySearchHistoryConverterBrushV2.kt */
/* JADX INFO: loaded from: classes4.dex */
@Metadata(d1 = {"\u0000.\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010 \n\u0000\b\u0016\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u0001B\u001d\u0012\u0006\u0010\u0003\u001a\u00020\u0004\u0012\u0006\u0010\u0005\u001a\u00020\u0006\u0012\u0006\u0010\u0007\u001a\u00020\b¢\u0006\u0002\u0010\tJ\u0016\u0010\n\u001a\u00020\u000b2\f\u0010\f\u001a\b\u0012\u0004\u0012\u00020\u00020\rH\u0016¨\u0006\u000e"}, d2 = {"Lcom/amazon/music/find/converter/LegacySearchHistoryConverterBrushV2;", "Lcom/amazon/music/find/converter/BrushV2Converter;", "Lcom/amazon/music/platform/find/service/history/SearchHistoryItem;", "context", "Landroid/content/Context;", "cacheImageProvider", "Lcom/amazon/music/find/providers/CacheImageProvider;", "searchFeaturesProvider", "Lcom/amazon/music/find/providers/SearchFeaturesProvider;", "(Landroid/content/Context;Lcom/amazon/music/find/providers/CacheImageProvider;Lcom/amazon/music/find/providers/SearchFeaturesProvider;)V", "convert", "Lcom/amazon/musicensembleservice/brush/PageResponse;", "entities", "", "DMMFindExperience_release"}, k = 1, mv = {1, 6, 0}, xi = 48)
public class LegacySearchHistoryConverterBrushV2 extends BrushV2Converter<SearchHistoryItem> {
    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public LegacySearchHistoryConverterBrushV2(Context context, CacheImageProvider cacheImageProvider, SearchFeaturesProvider searchFeaturesProvider) {
        super(context, cacheImageProvider, searchFeaturesProvider);
        Intrinsics.checkNotNullParameter(context, "context");
        Intrinsics.checkNotNullParameter(cacheImageProvider, "cacheImageProvider");
        Intrinsics.checkNotNullParameter(searchFeaturesProvider, "searchFeaturesProvider");
    }

    @Override // com.amazon.music.find.converter.BrushV2Converter
    public PageResponse convert(List<? extends SearchHistoryItem> entities) {
        Intrinsics.checkNotNullParameter(entities, "entities");
        Block block = new Block();
        block.setTitle(getContext().getResources().getString(R.string.search_history_header));
        block.setContent(new Content());
        block.setRenderingType(FindRenderingType.COMPACT_LINK_NAVIGATOR_LEGACY.name());
        ArrayList arrayList = new ArrayList(CollectionsKt__IterablesKt.collectionSizeOrDefault(entities, 10));
        Iterator<T> it = entities.iterator();
        while (it.hasNext()) {
            arrayList.add(((SearchHistoryItem) it.next()).getSearchQuery());
        }
        block.getContent().setEntities(getNavigationEntities(arrayList));
        PageResponse pageResponse = new PageResponse();
        pageResponse.setTitle(null);
        pageResponse.setContent(new Content());
        pageResponse.getContent().setBlocks(CollectionsKt__CollectionsJVMKt.listOf(block));
        return pageResponse;
    }
}
