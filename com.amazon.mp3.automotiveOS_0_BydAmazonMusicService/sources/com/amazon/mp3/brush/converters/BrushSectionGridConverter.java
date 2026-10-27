package com.amazon.mp3.brush.converters;

import com.amazon.mp3.AmazonApplication;
import com.amazon.mp3.brush.AllAccessPlaylistsUtils;
import com.amazon.mp3.brush.HotspotExperienceV2;
import com.amazon.mp3.likes.LikesServiceProxy;
import com.amazon.music.find.converter.SearchResultConverterBrushV2;
import com.amazon.music.views.library.converter.BaseContainerModelConverter;
import com.amazon.music.views.library.converter.BaseModelConverter;
import com.amazon.music.views.library.converter.SingleBaseModelConverter;
import com.amazon.music.views.library.models.HorizontalTileModel;
import com.amazon.music.views.library.models.SectionGridViewModel;
import com.amazon.music.views.library.models.SimpleHorizontalTileModel;
import com.amazon.music.views.library.models.VerticalTileModel;
import com.amazon.music.views.library.models.base.BaseViewContainerModel;
import com.amazon.music.views.library.models.base.BaseViewModel;
import com.amazon.music.views.library.models.base.HotspotSupportedModel;
import com.amazon.musicensembleservice.brush.Block;
import com.amazon.musicensembleservice.brush.Content;
import com.amazon.musicensembleservice.brush.Entity;
import com.amazon.musicensembleservice.brush.PodcastShow;
import com.amazon.musicensembleservice.brush.Track;
import java.util.ArrayList;
import java.util.List;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt__CollectionsKt;
import kotlin.collections.CollectionsKt___CollectionsKt;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.StringsKt__StringsKt;

/* JADX INFO: compiled from: BrushSectionGridConverter.kt */
/* JADX INFO: loaded from: classes3.dex */
@Metadata(bv = {}, d1 = {"\u00004\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010 \n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0007\b\u0016\u0018\u0000 \u00142\b\u0012\u0004\u0012\u00020\u00020\u0001:\u0001\u0014B\u0007¢\u0006\u0004\b\u0012\u0010\u0013J\u0018\u0010\u0007\u001a\u00020\u00062\u000e\u0010\u0005\u001a\n\u0012\u0004\u0012\u00020\u0004\u0018\u00010\u0003H\u0002J\u0019\u0010\n\u001a\u0004\u0018\u00010\t2\u0006\u0010\b\u001a\u00020\u0002H\u0002¢\u0006\u0004\b\n\u0010\u000bJ\"\u0010\u000f\u001a\u0004\u0018\u00010\u000e2\u0006\u0010\b\u001a\u00020\u00022\u000e\u0010\r\u001a\n\u0012\u0004\u0012\u00020\f\u0018\u00010\u0003H\u0016J\u0010\u0010\u0010\u001a\u00020\t2\u0006\u0010\b\u001a\u00020\u0002H\u0016J\u0018\u0010\u0011\u001a\n\u0012\u0004\u0012\u00020\f\u0018\u00010\u00032\u0006\u0010\b\u001a\u00020\u0002H\u0016¨\u0006\u0015"}, d2 = {"Lcom/amazon/mp3/brush/converters/BrushSectionGridConverter;", "Lcom/amazon/music/views/library/converter/BaseContainerModelConverter;", "Lcom/amazon/musicensembleservice/brush/Block;", "", "", "tags", "", "isRecentsSection", "data", "", "overrideDisplayCount", "(Lcom/amazon/musicensembleservice/brush/Block;)Ljava/lang/Integer;", "Lcom/amazon/music/views/library/models/base/BaseViewModel;", "list", "Lcom/amazon/music/views/library/models/SectionGridViewModel;", "convert", "getTileType", "getChildrenModels", "<init>", "()V", "Companion", "DigitalMusicAndroid3P_automotiveProdRelease"}, k = 1, mv = {1, 6, 0})
public class BrushSectionGridConverter implements BaseContainerModelConverter<Block> {
    @Override // com.amazon.music.views.library.converter.BaseContainerModelConverter
    public /* bridge */ /* synthetic */ BaseViewContainerModel convert(Block block, List list) {
        return convert2(block, (List<? extends BaseViewModel>) list);
    }

    /* JADX INFO: renamed from: convert, reason: avoid collision after fix types in other method */
    public SectionGridViewModel convert2(Block data, List<? extends BaseViewModel> list) {
        Intrinsics.checkNotNullParameter(data, "data");
        if ((list == null || !(!list.isEmpty())) && !isRecentsSection(data.getTags())) {
            return null;
        }
        String blockRef = data.getBlockRef();
        List<? extends BaseViewModel> listEmptyList = list == null ? CollectionsKt__CollectionsKt.emptyList() : list;
        String title = data.getTitle();
        String subtitle = data.getSubtitle();
        String moreText = isRecentsSection(data.getTags()) ? null : data.getMoreText();
        String uri = isRecentsSection(data.getTags()) ? null : data.getUri();
        int tileType = getTileType(data);
        List<String> tags = data.getTags();
        Content content = data.getContent();
        return new SectionGridViewModel(blockRef, listEmptyList, tileType, tags, title, subtitle, moreText, uri, null, content != null ? content.getNextToken() : null, false, overrideDisplayCount(data), false, null, null, null, null, 128256, null);
    }

    public int getTileType(Block data) {
        Intrinsics.checkNotNullParameter(data, "data");
        BrushLayoutTypeUtils brushLayoutTypeUtils = BrushLayoutTypeUtils.INSTANCE;
        String renderingType = data.getRenderingType();
        Intrinsics.checkNotNullExpressionValue(renderingType, "data.renderingType");
        return brushLayoutTypeUtils.getTileLayoutType(renderingType);
    }

    private final boolean isRecentsSection(List<String> tags) {
        return BrushRecentlyPlayedConverter.INSTANCE.containsRecentlyPlayedTag(tags);
    }

    private final Integer overrideDisplayCount(Block data) {
        if (data.getUri() != null) {
            String uri = data.getUri();
            Intrinsics.checkNotNullExpressionValue(uri, "data.uri");
            if (StringsKt__StringsKt.contains$default((CharSequence) uri, (CharSequence) "album-tracks", false, 2, (Object) null)) {
                return null;
            }
        }
        return SearchResultConverterBrushV2.INSTANCE.overrideDisplayCount(data);
    }

    /* JADX WARN: Can't rename method to resolve collision */
    @Override // com.amazon.music.views.library.converter.BaseContainerModelConverter
    public List<BaseViewModel> getChildrenModels(Block data) {
        BaseViewModel baseViewModelConvert;
        Intrinsics.checkNotNullParameter(data, "data");
        if (isRecentsSection(data.getTags())) {
            return CollectionsKt__CollectionsKt.emptyList();
        }
        String renderingType = data.getRenderingType();
        Content content = data.getContent();
        HorizontalTileModel horizontalTileModel = null;
        List<Entity> entities = content == null ? null : content.getEntities();
        if (entities == null) {
            return null;
        }
        if (AllAccessPlaylistsUtils.INSTANCE.isAllAccessTracksWidget(data.getTags())) {
            return BrushPlaybackContainerConverterUtils.INSTANCE.extractedPlaybackContainers(data);
        }
        ArrayList arrayList = new ArrayList(entities.size());
        BrushConverterFactory brushConverterFactory = BrushConverterFactory.INSTANCE;
        Intrinsics.checkNotNullExpressionValue(renderingType, "renderingType");
        BaseModelConverter converter = brushConverterFactory.getConverter(renderingType);
        SingleBaseModelConverter singleBaseModelConverter = converter instanceof SingleBaseModelConverter ? (SingleBaseModelConverter) converter : null;
        if (AmazonApplication.getCapabilities().isLikesEverywhereEnabled() && (singleBaseModelConverter instanceof BrushHorizontalTileConverter) && (!entities.isEmpty())) {
            ArrayList arrayList2 = new ArrayList();
            for (Object obj : entities) {
                if (obj instanceof Track) {
                    arrayList2.add(obj);
                }
            }
            if (!arrayList2.isEmpty()) {
                LikesServiceProxy.Companion.cacheTracksFromMuseBrushEntities$default(LikesServiceProxy.INSTANCE, null, arrayList2, 1, null);
            }
        }
        ArrayList arrayList3 = new ArrayList();
        int i = 0;
        for (Object obj2 : entities) {
            int i2 = i + 1;
            if (i < 0) {
                CollectionsKt__CollectionsKt.throwIndexOverflow();
            }
            Entity entity = (Entity) obj2;
            BrushPlaybackContainerConverterUtils brushPlaybackContainerConverterUtils = BrushPlaybackContainerConverterUtils.INSTANCE;
            Intrinsics.checkNotNullExpressionValue(entity, "entity");
            if (brushPlaybackContainerConverterUtils.isPlaybackContainerEligible(entity, i)) {
                SectionGridViewModel sectionGridViewModelCreateSectionGridViewModel = brushPlaybackContainerConverterUtils.createSectionGridViewModel(entity, "PLAYBACK_CONTAINER_MULTI_ITEM", true);
                BrushConverterUtils brushConverterUtils = BrushConverterUtils.INSTANCE;
                if (sectionGridViewModelCreateSectionGridViewModel == null) {
                    throw new NullPointerException("null cannot be cast to non-null type com.amazon.music.views.library.models.base.BaseViewModel");
                }
                if (brushConverterUtils.shouldShowHotSpot(sectionGridViewModelCreateSectionGridViewModel, data, entity, i)) {
                    Object objFirstOrNull = CollectionsKt___CollectionsKt.firstOrNull((List<? extends Object>) sectionGridViewModelCreateSectionGridViewModel.getModels());
                    HorizontalTileModel horizontalTileModel2 = objFirstOrNull instanceof HorizontalTileModel ? (HorizontalTileModel) objFirstOrNull : horizontalTileModel;
                    if (horizontalTileModel2 != null) {
                        HotspotExperienceV2.INSTANCE.addHotspot("hotspot_search_page_top_results_ondemand_widget", horizontalTileModel2);
                    }
                }
                arrayList.add(sectionGridViewModelCreateSectionGridViewModel);
            } else {
                if (singleBaseModelConverter instanceof BrushHorizontalTileConverter) {
                    baseViewModelConvert = ((BrushHorizontalTileConverter) singleBaseModelConverter).convert(entity, arrayList3, data.getTags());
                } else if (singleBaseModelConverter instanceof BrushVerticalTileConverter) {
                    if ((entity instanceof Track) && Intrinsics.areEqual(((Track) entity).getAssetType(), "VIDEO")) {
                        baseViewModelConvert = ((BrushVerticalTileConverter) singleBaseModelConverter).convert(entity, arrayList3);
                    } else if ((entity instanceof PodcastShow) && BrushLayoutTypeUtils.INSTANCE.isListLayoutType(renderingType) && AmazonApplication.getCapabilities().isPodcastRedhoodPlusUXEnabled()) {
                        baseViewModelConvert = ((BrushVerticalTileConverter) singleBaseModelConverter).convertPodcastShowForSeeMore((PodcastShow) entity);
                    } else {
                        baseViewModelConvert = ((BrushVerticalTileConverter) singleBaseModelConverter).convert(entity);
                    }
                } else {
                    baseViewModelConvert = singleBaseModelConverter == null ? horizontalTileModel : singleBaseModelConverter.convert(entity);
                }
                HorizontalTileModel horizontalTileModel3 = baseViewModelConvert instanceof HorizontalTileModel ? (HorizontalTileModel) baseViewModelConvert : horizontalTileModel;
                if (horizontalTileModel3 != null) {
                    if (BrushConverterUtils.INSTANCE.shouldShowHotSpot(horizontalTileModel3, data, entity, i)) {
                        HotspotExperienceV2.INSTANCE.addHotspot("hotspot_search_page_top_results_shuffle_track", (HotspotSupportedModel) baseViewModelConvert);
                    }
                    arrayList3.add(horizontalTileModel3.getMetadata());
                }
                SimpleHorizontalTileModel simpleHorizontalTileModel = baseViewModelConvert instanceof SimpleHorizontalTileModel ? (SimpleHorizontalTileModel) baseViewModelConvert : null;
                if (simpleHorizontalTileModel != null) {
                    arrayList3.add(simpleHorizontalTileModel.getMetadata());
                }
                VerticalTileModel verticalTileModel = baseViewModelConvert instanceof VerticalTileModel ? (VerticalTileModel) baseViewModelConvert : null;
                if (verticalTileModel != null && (entity instanceof Track) && Intrinsics.areEqual(((Track) entity).getAssetType(), "VIDEO")) {
                    arrayList3.add(verticalTileModel.getMetadata());
                }
                if (baseViewModelConvert != null) {
                    arrayList.add(baseViewModelConvert);
                }
            }
            i = i2;
            horizontalTileModel = null;
        }
        return arrayList;
    }
}
