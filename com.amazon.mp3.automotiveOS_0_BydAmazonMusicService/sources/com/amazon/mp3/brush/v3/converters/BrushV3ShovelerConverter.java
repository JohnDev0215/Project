package com.amazon.mp3.brush.v3.converters;

import android.net.Uri;
import com.amazon.mp3.AmazonApplication;
import com.amazon.mp3.likes.LikesServiceProxy;
import com.amazon.music.page.api.model.AssetType;
import com.amazon.music.page.api.model.Button;
import com.amazon.music.page.api.model.Entity;
import com.amazon.music.page.api.model.Image;
import com.amazon.music.page.api.model.PodcastAvailableUpsells;
import com.amazon.music.page.api.model.PodcastContentTraits;
import com.amazon.music.page.api.model.PodcastEpisode;
import com.amazon.music.page.api.model.PodcastPlaybackMode;
import com.amazon.music.page.api.model.PodcastShow;
import com.amazon.music.page.api.model.RenderingType;
import com.amazon.music.page.api.model.VideoStory;
import com.amazon.music.page.api.model.Widget;
import com.amazon.music.page.api.model.WidgetContent;
import com.amazon.music.page.api.model.WidgetTag;
import com.amazon.music.views.library.converter.BaseContainerModelConverter;
import com.amazon.music.views.library.converter.BaseModelConverter;
import com.amazon.music.views.library.converter.SingleBaseModelConverter;
import com.amazon.music.views.library.metadata.PodcastMetadata;
import com.amazon.music.views.library.models.HorizontalTileModel;
import com.amazon.music.views.library.models.ShovelerModel;
import com.amazon.music.views.library.models.VerticalTileModel;
import com.amazon.music.views.library.models.VideoStoryShovelerModel;
import com.amazon.music.views.library.models.VideoStoryTileModel;
import com.amazon.music.views.library.models.base.BaseViewContainerModel;
import com.amazon.music.views.library.models.base.BaseViewModel;
import com.amazon.podcast.deeplinks.Deeplinks;
import com.amazon.podcast.views.browseHomeWidgets.PodcastEpisodeDescriptiveShovelerModel;
import com.amazon.podcast.views.browseHomeWidgets.PodcastVisualShovelerCompactModel;
import com.amazon.podcast.views.episodeRowItemsListView.EpisodeRowItemModel;
import com.amazon.podcast.views.verticalItemView.VerticalItemModel;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.collections.CollectionsKt__CollectionsKt;
import kotlin.collections.CollectionsKt__IterablesKt;
import kotlin.collections.CollectionsKt___CollectionsKt;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: BrushV3ShovelerConverter.kt */
/* JADX INFO: loaded from: classes3.dex */
@Metadata(bv = {}, d1 = {"\u0000<\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u0001B\u0007¢\u0006\u0004\b\u0015\u0010\u0016J\u0010\u0010\u0005\u001a\u00020\u00042\u0006\u0010\u0003\u001a\u00020\u0002H\u0002J\u0010\u0010\u0006\u001a\u00020\u00042\u0006\u0010\u0003\u001a\u00020\u0002H\u0002J\u0010\u0010\u0007\u001a\u00020\u00042\u0006\u0010\u0003\u001a\u00020\u0002H\u0002J\u001e\u0010\f\u001a\b\u0012\u0004\u0012\u00020\u000b0\b2\u000e\u0010\n\u001a\n\u0012\u0004\u0012\u00020\t\u0018\u00010\bH\u0002J\u001e\u0010\u000e\u001a\b\u0012\u0004\u0012\u00020\r0\b2\u000e\u0010\n\u001a\n\u0012\u0004\u0012\u00020\t\u0018\u00010\bH\u0002J*\u0010\u0013\u001a\f\u0012\u0006\b\u0001\u0012\u00020\u0010\u0018\u00010\u00122\u0006\u0010\u000f\u001a\u00020\u00022\u000e\u0010\u0011\u001a\n\u0012\u0004\u0012\u00020\u0010\u0018\u00010\bH\u0016J\u0018\u0010\u0014\u001a\n\u0012\u0004\u0012\u00020\u0010\u0018\u00010\b2\u0006\u0010\u000f\u001a\u00020\u0002H\u0016¨\u0006\u0017"}, d2 = {"Lcom/amazon/mp3/brush/v3/converters/BrushV3ShovelerConverter;", "Lcom/amazon/music/views/library/converter/BaseContainerModelConverter;", "Lcom/amazon/music/page/api/model/Widget;", "widget", "", "isPodcastEpisodeDescriptiveShoveler", "isPodcastViewSection", "isVideoStoryWidget", "", "Lcom/amazon/music/page/api/model/Entity;", "list", "Lcom/amazon/podcast/views/episodeRowItemsListView/EpisodeRowItemModel;", "convertToEpisodeRowItemModel", "Lcom/amazon/podcast/views/verticalItemView/VerticalItemModel;", "convertToVerticalItemModel", "data", "Lcom/amazon/music/views/library/models/base/BaseViewModel;", "children", "Lcom/amazon/music/views/library/models/base/BaseViewContainerModel;", "convert", "getChildrenModels", "<init>", "()V", "DigitalMusicAndroid3P_automotiveProdRelease"}, k = 1, mv = {1, 6, 0})
public final class BrushV3ShovelerConverter implements BaseContainerModelConverter<Widget> {

    /* JADX INFO: compiled from: BrushV3ShovelerConverter.kt */
    @Metadata(k = 3, mv = {1, 6, 0}, xi = 48)
    public /* synthetic */ class WhenMappings {
        public static final /* synthetic */ int[] $EnumSwitchMapping$0;

        static {
            int[] iArr = new int[RenderingType.values().length];
            iArr[RenderingType.PILL_NAVIGATOR.ordinal()] = 1;
            $EnumSwitchMapping$0 = iArr;
        }
    }

    @Override // com.amazon.music.views.library.converter.BaseContainerModelConverter
    public /* bridge */ /* synthetic */ BaseViewContainerModel convert(Widget widget, List list) {
        return convert2(widget, (List<? extends BaseViewModel>) list);
    }

    /* JADX INFO: renamed from: convert, reason: avoid collision after fix types in other method */
    public BaseViewContainerModel<? extends BaseViewModel> convert2(Widget data, List<? extends BaseViewModel> children) {
        ArrayList arrayList;
        ArrayList arrayList2;
        Intrinsics.checkNotNullParameter(data, "data");
        if (isPodcastEpisodeDescriptiveShoveler(data)) {
            List<EpisodeRowItemModel> listConvertToEpisodeRowItemModel = convertToEpisodeRowItemModel(data.getContent().getEntities());
            String blockRef = data.getBlockRef();
            BrushLayoutTypeUtils brushLayoutTypeUtils = BrushLayoutTypeUtils.INSTANCE;
            RenderingType renderingType = data.getRenderingType();
            Intrinsics.checkNotNullExpressionValue(renderingType, "data.renderingType");
            int tileLayoutType = brushLayoutTypeUtils.getTileLayoutType(renderingType);
            List<WidgetTag> tags = data.getTags();
            String title = data.getTitle();
            Button button = data.getButton();
            String title2 = button == null ? null : button.getTitle();
            Button button2 = data.getButton();
            return new PodcastEpisodeDescriptiveShovelerModel(blockRef, listConvertToEpisodeRowItemModel, tileLayoutType, tags, title, title2, button2 != null ? button2.getTarget() : null);
        }
        if (isPodcastViewSection(data)) {
            List<VerticalItemModel> listConvertToVerticalItemModel = convertToVerticalItemModel(data.getContent().getEntities());
            String blockRef2 = data.getBlockRef();
            BrushLayoutTypeUtils brushLayoutTypeUtils2 = BrushLayoutTypeUtils.INSTANCE;
            RenderingType renderingType2 = data.getRenderingType();
            Intrinsics.checkNotNullExpressionValue(renderingType2, "data.renderingType");
            int tileLayoutType2 = brushLayoutTypeUtils2.getTileLayoutType(renderingType2);
            List<WidgetTag> tags2 = data.getTags();
            String title3 = data.getTitle();
            Button button3 = data.getButton();
            String title4 = button3 == null ? null : button3.getTitle();
            Button button4 = data.getButton();
            return new PodcastVisualShovelerCompactModel(blockRef2, listConvertToVerticalItemModel, tileLayoutType2, tags2, title3, title4, button4 != null ? button4.getTarget() : null);
        }
        if (isVideoStoryWidget(data)) {
            if (children == null) {
                return null;
            }
            String blockRef3 = data.getBlockRef();
            BrushLayoutTypeUtils brushLayoutTypeUtils3 = BrushLayoutTypeUtils.INSTANCE;
            RenderingType renderingType3 = data.getRenderingType();
            Intrinsics.checkNotNullExpressionValue(renderingType3, "data.renderingType");
            int tileLayoutType3 = brushLayoutTypeUtils3.getTileLayoutType(renderingType3);
            List<WidgetTag> tags3 = data.getTags();
            if (tags3 == null) {
                arrayList2 = null;
            } else {
                ArrayList arrayList3 = new ArrayList(CollectionsKt__IterablesKt.collectionSizeOrDefault(tags3, 10));
                Iterator<T> it = tags3.iterator();
                while (it.hasNext()) {
                    arrayList3.add(((WidgetTag) it.next()).name());
                }
                arrayList2 = arrayList3;
            }
            String title5 = data.getTitle();
            String subtitle = data.getSubtitle();
            WidgetContent content = data.getContent();
            String nextToken = content == null ? null : content.getNextToken();
            Button button5 = data.getButton();
            return new VideoStoryShovelerModel(blockRef3, children, tileLayoutType3, arrayList2, title5, subtitle, null, nextToken, button5 == null ? null : button5.getTarget(), false, null, null, null, 7680, null);
        }
        if (children == null || children.isEmpty()) {
            return null;
        }
        String blockRef4 = data.getBlockRef();
        String title6 = data.getTitle();
        String subtitle2 = data.getSubtitle();
        Button button6 = data.getButton();
        String title7 = button6 == null ? null : button6.getTitle();
        Button button7 = data.getButton();
        String target = button7 == null ? null : button7.getTarget();
        BrushLayoutTypeUtils brushLayoutTypeUtils4 = BrushLayoutTypeUtils.INSTANCE;
        RenderingType renderingType4 = data.getRenderingType();
        Intrinsics.checkNotNullExpressionValue(renderingType4, "data.renderingType");
        int tileLayoutType4 = brushLayoutTypeUtils4.getTileLayoutType(renderingType4);
        List<WidgetTag> tags4 = data.getTags();
        if (tags4 == null) {
            arrayList = null;
        } else {
            ArrayList arrayList4 = new ArrayList(CollectionsKt__IterablesKt.collectionSizeOrDefault(tags4, 10));
            Iterator<T> it2 = tags4.iterator();
            while (it2.hasNext()) {
                arrayList4.add(((WidgetTag) it2.next()).name());
            }
            arrayList = arrayList4;
        }
        WidgetContent content2 = data.getContent();
        return new ShovelerModel(blockRef4, children, tileLayoutType4, arrayList, title6, subtitle2, title7, content2 == null ? null : content2.getNextToken(), target, false, null, null, null, false, null, 32256, null);
    }

    private final boolean isPodcastEpisodeDescriptiveShoveler(Widget widget) {
        List<Entity> entities;
        Entity entity;
        if (RenderingType.DESCRIPTIVE_SHOVELER == widget.getRenderingType()) {
            WidgetContent content = widget.getContent();
            PodcastEpisode podcastEpisode = null;
            if (content != null && (entities = content.getEntities()) != null && (entity = (Entity) CollectionsKt___CollectionsKt.firstOrNull((List) entities)) != null) {
                podcastEpisode = entity.getPodcastEpisode();
            }
            if (podcastEpisode != null) {
                return true;
            }
        }
        return false;
    }

    private final boolean isPodcastViewSection(Widget widget) {
        List<Entity> entities;
        Entity entity;
        WidgetContent content = widget.getContent();
        if (content == null || (entities = content.getEntities()) == null || (entity = (Entity) CollectionsKt___CollectionsKt.firstOrNull((List) entities)) == null) {
            return false;
        }
        return (entity.getPodcastEpisode() == null && entity.getPodcastShow() == null) ? false : true;
    }

    private final boolean isVideoStoryWidget(Widget widget) {
        List<Entity> entities;
        Entity entity;
        if (RenderingType.VISUAL_SHOVELER == widget.getRenderingType()) {
            WidgetContent content = widget.getContent();
            VideoStory videoStory = null;
            if (content != null && (entities = content.getEntities()) != null && (entity = (Entity) CollectionsKt___CollectionsKt.firstOrNull((List) entities)) != null) {
                videoStory = entity.getVideoStory();
            }
            if (videoStory != null) {
                return true;
            }
        }
        return false;
    }

    private final List<EpisodeRowItemModel> convertToEpisodeRowItemModel(List<? extends Entity> list) {
        ArrayList arrayList = new ArrayList();
        if (list != null) {
            ArrayList arrayList2 = new ArrayList(CollectionsKt__IterablesKt.collectionSizeOrDefault(list, 10));
            for (Entity entity : list) {
                if (entity.getPodcastEpisode() != null) {
                    PodcastEpisode podcastEpisode = entity.getPodcastEpisode();
                    String blockRef = podcastEpisode.getBlockRef();
                    Uri EMPTY = Uri.EMPTY;
                    Intrinsics.checkNotNullExpressionValue(EMPTY, "EMPTY");
                    List listEmptyList = CollectionsKt__CollectionsKt.emptyList();
                    String catalogId = podcastEpisode.getCatalogId();
                    Intrinsics.checkNotNullExpressionValue(catalogId, "entity.catalogId");
                    PodcastMetadata podcastMetadata = new PodcastMetadata(blockRef, EMPTY, listEmptyList, catalogId, "", "", "", "", "", podcastEpisode.getPodcastShowCatalogId(), null, 1024, null);
                    String blockRef2 = podcastEpisode.getBlockRef();
                    String catalogId2 = podcastEpisode.getCatalogId();
                    String podcastShowCatalogId = podcastEpisode.getPodcastShowCatalogId();
                    String title = podcastEpisode.getTitle();
                    String podcastShowTitle = podcastEpisode.getPodcastShowTitle();
                    String publishDate = podcastEpisode.getPublishDate();
                    int iIntValue = podcastEpisode.getDurationSeconds().intValue();
                    Image podcastShowImage = podcastEpisode.getPodcastShowImage();
                    String src = podcastShowImage == null ? null : podcastShowImage.getSrc();
                    PodcastContentTraits contentTraits = podcastEpisode.getContentTraits();
                    PodcastAvailableUpsells availableUpsells = podcastEpisode.getAvailableUpsells();
                    PodcastPlaybackMode playbackMode = podcastEpisode.getPlaybackMode();
                    arrayList.add(new EpisodeRowItemModel(blockRef2, podcastMetadata, 11, catalogId2, podcastShowCatalogId, title, podcastShowTitle, publishDate, iIntValue, src, contentTraits, availableUpsells, playbackMode == null ? null : playbackMode.toString(), podcastEpisode.getPodcastEpisodeVariantId(), podcastEpisode.getPodcastShowVariantId(), podcastEpisode.getAvailabilityDate()));
                }
                arrayList2.add(Unit.INSTANCE);
            }
        }
        return arrayList;
    }

    private final List<VerticalItemModel> convertToVerticalItemModel(List<? extends Entity> list) {
        ArrayList arrayList = new ArrayList();
        if (list != null) {
            ArrayList arrayList2 = new ArrayList(CollectionsKt__IterablesKt.collectionSizeOrDefault(list, 10));
            for (Entity entity : list) {
                if (entity.getPodcastEpisode() != null) {
                    PodcastEpisode podcastEpisode = entity.getPodcastEpisode();
                    String blockRef = podcastEpisode.getBlockRef();
                    Uri EMPTY = Uri.EMPTY;
                    Intrinsics.checkNotNullExpressionValue(EMPTY, "EMPTY");
                    List listEmptyList = CollectionsKt__CollectionsKt.emptyList();
                    String catalogId = podcastEpisode.getCatalogId();
                    Intrinsics.checkNotNullExpressionValue(catalogId, "entity.catalogId");
                    PodcastMetadata podcastMetadata = new PodcastMetadata(blockRef, EMPTY, listEmptyList, catalogId, "", "", "", "", "", podcastEpisode.getCatalogId(), null, 1024, null);
                    String blockRef2 = podcastEpisode.getBlockRef();
                    String catalogId2 = podcastEpisode.getCatalogId();
                    String podcastShowCatalogId = podcastEpisode.getPodcastShowCatalogId();
                    String title = podcastEpisode.getTitle();
                    String subtitle = podcastEpisode.getSubtitle();
                    long jLongValue = podcastEpisode.getDurationSeconds().longValue();
                    Image image = podcastEpisode.getImage();
                    String src = image == null ? null : image.getSrc();
                    String podcastEpisodeVariantId = podcastEpisode.getPodcastEpisodeVariantId();
                    String podcastShowVariantId = podcastEpisode.getPodcastShowVariantId();
                    PodcastAvailableUpsells availableUpsells = podcastEpisode.getAvailableUpsells();
                    PodcastPlaybackMode playbackMode = podcastEpisode.getPlaybackMode();
                    arrayList.add(new VerticalItemModel(blockRef2, podcastMetadata, 11, catalogId2, podcastShowCatalogId, title, subtitle, jLongValue, src, false, podcastEpisodeVariantId, podcastShowVariantId, availableUpsells, playbackMode == null ? null : playbackMode.toString(), podcastEpisode.getContentTraits(), podcastEpisode.getAvailabilityDate(), false));
                }
                arrayList2.add(Unit.INSTANCE);
            }
        }
        if (list != null) {
            ArrayList arrayList3 = new ArrayList(CollectionsKt__IterablesKt.collectionSizeOrDefault(list, 10));
            for (Entity entity2 : list) {
                if (entity2.getPodcastShow() != null) {
                    PodcastShow podcastShow = entity2.getPodcastShow();
                    String strPodcast = Deeplinks.podcast(podcastShow.getCatalogId(), podcastShow.getTitle());
                    String blockRef3 = podcastShow.getBlockRef();
                    Uri EMPTY2 = Uri.EMPTY;
                    Intrinsics.checkNotNullExpressionValue(EMPTY2, "EMPTY");
                    List listEmptyList2 = CollectionsKt__CollectionsKt.emptyList();
                    String catalogId3 = podcastShow.getCatalogId();
                    Intrinsics.checkNotNullExpressionValue(catalogId3, "entity.catalogId");
                    PodcastMetadata podcastMetadata2 = new PodcastMetadata(blockRef3, EMPTY2, listEmptyList2, catalogId3, "", strPodcast, "", "", "", podcastShow.getCatalogId(), null, 1024, null);
                    String blockRef4 = podcastShow.getBlockRef();
                    String catalogId4 = podcastShow.getCatalogId();
                    String title2 = podcastShow.getTitle();
                    String subtitle2 = podcastShow.getSubtitle();
                    Image image2 = podcastShow.getImage();
                    String src2 = image2 == null ? null : image2.getSrc();
                    String podcastShowVariantId2 = podcastShow.getPodcastShowVariantId();
                    PodcastAvailableUpsells availableUpsells2 = podcastShow.getAvailableUpsells();
                    PodcastPlaybackMode playbackMode2 = podcastShow.getPlaybackMode();
                    arrayList.add(new VerticalItemModel(blockRef4, podcastMetadata2, 12, "", catalogId4, title2, subtitle2, 0L, src2, false, "", podcastShowVariantId2, availableUpsells2, playbackMode2 == null ? null : playbackMode2.toString(), podcastShow.getContentTraits(), "", false));
                }
                arrayList3.add(Unit.INSTANCE);
            }
        }
        return arrayList;
    }

    @Override // com.amazon.music.views.library.converter.BaseContainerModelConverter
    public List<BaseViewModel> getChildrenModels(Widget data) {
        SingleBaseModelConverter brushV3PillConverter;
        BaseViewModel baseViewModelConvert;
        Intrinsics.checkNotNullParameter(data, "data");
        RenderingType renderingType = data.getRenderingType();
        WidgetContent content = data.getContent();
        List<Entity> entities = content == null ? null : content.getEntities();
        if (entities == null) {
            return null;
        }
        if ((renderingType == null ? -1 : WhenMappings.$EnumSwitchMapping$0[renderingType.ordinal()]) == 1) {
            brushV3PillConverter = new BrushV3PillConverter();
        } else {
            BaseModelConverter converter = BrushV3ConverterFactory.INSTANCE.getConverter(renderingType.name());
            brushV3PillConverter = converter instanceof SingleBaseModelConverter ? (SingleBaseModelConverter) converter : null;
        }
        ArrayList arrayList = new ArrayList(entities.size());
        ArrayList arrayList2 = new ArrayList();
        ArrayList arrayList3 = new ArrayList();
        if (AmazonApplication.getCapabilities().isLikesEverywhereEnabled() && (brushV3PillConverter instanceof BrushV3HorizontalTileConverter) && (!entities.isEmpty())) {
            ArrayList arrayList4 = new ArrayList();
            for (Object obj : entities) {
                if (((Entity) obj).getTrack() != null) {
                    arrayList4.add(obj);
                }
            }
            if (!arrayList4.isEmpty()) {
                LikesServiceProxy.Companion.cacheTracksFromBrushV3Entities$default(LikesServiceProxy.INSTANCE, null, arrayList4, 1, null);
            }
        }
        for (Entity entity : entities) {
            if ((brushV3PillConverter instanceof BrushV3HorizontalTileConverter) && entity.getTrack() != null) {
                Intrinsics.checkNotNullExpressionValue(entity, "entity");
                BaseViewModel baseViewModelConvert2 = ((BrushV3HorizontalTileConverter) brushV3PillConverter).convert(entity, arrayList2);
                HorizontalTileModel horizontalTileModel = baseViewModelConvert2 instanceof HorizontalTileModel ? (HorizontalTileModel) baseViewModelConvert2 : null;
                if (horizontalTileModel != null && entity.getTrack() != null) {
                    arrayList2.add(horizontalTileModel.getMetadata());
                }
                if (baseViewModelConvert2 != null) {
                    arrayList.add(baseViewModelConvert2);
                }
            } else {
                boolean z = brushV3PillConverter instanceof BrushV3VerticalTileConverter;
                if (z && entity.getTrack() != null && AssetType.VIDEO.equals(entity.getTrack().getAssetType())) {
                    Intrinsics.checkNotNullExpressionValue(entity, "entity");
                    BaseViewModel baseViewModelConvert3 = ((BrushV3VerticalTileConverter) brushV3PillConverter).convert(entity, arrayList3);
                    VerticalTileModel verticalTileModel = baseViewModelConvert3 instanceof VerticalTileModel ? (VerticalTileModel) baseViewModelConvert3 : null;
                    if (verticalTileModel != null && entity.getTrack() != null) {
                        arrayList3.add(verticalTileModel.getMetadata());
                    }
                    if (baseViewModelConvert3 != null) {
                        arrayList.add(baseViewModelConvert3);
                    }
                } else if (!z || entity.getVideoStory() == null) {
                    if (brushV3PillConverter == null) {
                        baseViewModelConvert = null;
                    } else {
                        Intrinsics.checkNotNullExpressionValue(entity, "entity");
                        baseViewModelConvert = brushV3PillConverter.convert(entity);
                    }
                    if (baseViewModelConvert != null) {
                        arrayList.add(baseViewModelConvert);
                    }
                } else {
                    Intrinsics.checkNotNullExpressionValue(entity, "entity");
                    BaseViewModel baseViewModelConvert4 = ((BrushV3VerticalTileConverter) brushV3PillConverter).convert(entity, arrayList3);
                    VideoStoryTileModel videoStoryTileModel = baseViewModelConvert4 instanceof VideoStoryTileModel ? (VideoStoryTileModel) baseViewModelConvert4 : null;
                    if (videoStoryTileModel != null && entity.getVideoStory() != null) {
                        arrayList3.add(videoStoryTileModel.getMetadata());
                    }
                    if (baseViewModelConvert4 != null) {
                        arrayList.add(baseViewModelConvert4);
                    }
                }
            }
        }
        return arrayList;
    }
}
