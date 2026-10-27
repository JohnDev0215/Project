package com.amazon.mp3.brush.converters;

import android.net.Uri;
import com.amazon.mp3.AmazonApplication;
import com.amazon.mp3.brush.AllAccessPlaylistsUtils;
import com.amazon.mp3.brush.HotspotExperienceV2;
import com.amazon.mp3.brush.converters.BrushPodcastEpisodeConverter;
import com.amazon.mp3.featuregate.Feature;
import com.amazon.mp3.likes.LikesServiceProxy;
import com.amazon.music.views.library.converter.BaseContainerModelConverter;
import com.amazon.music.views.library.converter.BaseModelConverter;
import com.amazon.music.views.library.converter.SingleBaseModelConverter;
import com.amazon.music.views.library.metadata.PodcastMetadata;
import com.amazon.music.views.library.models.HorizontalTileModel;
import com.amazon.music.views.library.models.PodcastShovelerModel;
import com.amazon.music.views.library.models.ShovelerModel;
import com.amazon.music.views.library.models.SimpleHorizontalTileModel;
import com.amazon.music.views.library.models.VerticalTileModel;
import com.amazon.music.views.library.models.VideoStoryShovelerModel;
import com.amazon.music.views.library.models.VideoStoryTileModel;
import com.amazon.music.views.library.models.base.BaseViewContainerModel;
import com.amazon.music.views.library.models.base.BaseViewModel;
import com.amazon.musicensembleservice.brush.Block;
import com.amazon.musicensembleservice.brush.Content;
import com.amazon.musicensembleservice.brush.Entity;
import com.amazon.musicensembleservice.brush.Image;
import com.amazon.musicensembleservice.brush.LiveStream;
import com.amazon.musicensembleservice.brush.PodcastEpisode;
import com.amazon.musicensembleservice.brush.PodcastShow;
import com.amazon.musicensembleservice.brush.Track;
import com.amazon.musicensembleservice.brush.VideoStory;
import com.amazon.podcast.deeplinks.Deeplinks;
import com.amazon.podcast.views.browseHomeWidgets.PodcastEpisodeDescriptiveShovelerModel;
import com.amazon.podcast.views.browseHomeWidgets.PodcastVisualShovelerCompactModel;
import com.amazon.podcast.views.episodeRowItemsListView.EpisodeRowItemModel;
import com.amazon.podcast.views.verticalItemView.VerticalItemModel;
import com.fasterxml.jackson.dataformat.cbor.CBORConstants;
import java.util.ArrayList;
import java.util.List;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt__CollectionsKt;
import kotlin.collections.CollectionsKt__IterablesKt;
import kotlin.collections.CollectionsKt___CollectionsKt;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: BrushShovelerConverter.kt */
/* JADX INFO: loaded from: classes3.dex */
@Metadata(bv = {}, d1 = {"\u0000>\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\b\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0006\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u0001B\u0007¢\u0006\u0004\b\u001b\u0010\u001cJ\u001e\u0010\u0007\u001a\b\u0012\u0004\u0012\u00020\u00060\u00032\u000e\u0010\u0005\u001a\n\u0012\u0004\u0012\u00020\u0004\u0018\u00010\u0003H\u0002J.\u0010\u000b\u001a\b\u0012\u0004\u0012\u00020\n0\u00032\u000e\u0010\u0005\u001a\n\u0012\u0004\u0012\u00020\u0004\u0018\u00010\u00032\u000e\u0010\t\u001a\n\u0012\u0004\u0012\u00020\b\u0018\u00010\u0003H\u0002J\u0010\u0010\u000e\u001a\u00020\r2\u0006\u0010\f\u001a\u00020\u0002H\u0002J\u0018\u0010\u000f\u001a\u00020\r2\u000e\u0010\t\u001a\n\u0012\u0004\u0012\u00020\b\u0018\u00010\u0003H\u0002J\u0018\u0010\u0010\u001a\u00020\r2\u000e\u0010\t\u001a\n\u0012\u0004\u0012\u00020\b\u0018\u00010\u0003H\u0002J\u0018\u0010\u0011\u001a\u00020\r2\u000e\u0010\t\u001a\n\u0012\u0004\u0012\u00020\b\u0018\u00010\u0003H\u0002J\u0018\u0010\u0012\u001a\u00020\r2\u000e\u0010\t\u001a\n\u0012\u0004\u0012\u00020\b\u0018\u00010\u0003H\u0002J\u0012\u0010\u0013\u001a\u00020\r2\b\u0010\f\u001a\u0004\u0018\u00010\u0002H\u0002J\u0018\u0010\u0014\u001a\u00020\r2\u000e\u0010\t\u001a\n\u0012\u0004\u0012\u00020\b\u0018\u00010\u0003H\u0002J\u0018\u0010\u0015\u001a\u00020\r2\u000e\u0010\t\u001a\n\u0012\u0004\u0012\u00020\b\u0018\u00010\u0003H\u0002J*\u0010\u0018\u001a\f\u0012\u0006\b\u0001\u0012\u00020\u0016\u0018\u00010\u00172\u0006\u0010\f\u001a\u00020\u00022\u000e\u0010\u0005\u001a\n\u0012\u0004\u0012\u00020\u0016\u0018\u00010\u0003H\u0016J\u0018\u0010\u001a\u001a\n\u0012\u0004\u0012\u00020\u0016\u0018\u00010\u00032\u0006\u0010\u0019\u001a\u00020\u0002H\u0016¨\u0006\u001d"}, d2 = {"Lcom/amazon/mp3/brush/converters/BrushShovelerConverter;", "Lcom/amazon/music/views/library/converter/BaseContainerModelConverter;", "Lcom/amazon/musicensembleservice/brush/Block;", "", "Lcom/amazon/musicensembleservice/brush/Entity;", "list", "Lcom/amazon/podcast/views/episodeRowItemsListView/EpisodeRowItemModel;", "convertToEpisodeRowItemModel", "", "tags", "Lcom/amazon/podcast/views/verticalItemView/VerticalItemModel;", "convertToVerticalItemModel", "block", "", "isPodcastEpisodeDescriptiveShoveler", "isRecentsSection", "isRecentSearchesSection", "isAllAccessPlaylistsSection", "isPodcastUserContentSection", "isVideoStoryShoveler", "isPodcastViewSection", "hasPodcastProgress", "Lcom/amazon/music/views/library/models/base/BaseViewModel;", "Lcom/amazon/music/views/library/models/base/BaseViewContainerModel;", "convert", "data", "getChildrenModels", "<init>", "()V", "DigitalMusicAndroid3P_automotiveProdRelease"}, k = 1, mv = {1, 6, 0})
public final class BrushShovelerConverter implements BaseContainerModelConverter<Block> {
    @Override // com.amazon.music.views.library.converter.BaseContainerModelConverter
    public /* bridge */ /* synthetic */ BaseViewContainerModel convert(Block block, List list) {
        return convert2(block, (List<? extends BaseViewModel>) list);
    }

    /* JADX INFO: renamed from: convert, reason: avoid collision after fix types in other method */
    public BaseViewContainerModel<? extends BaseViewModel> convert2(Block block, List<? extends BaseViewModel> list) {
        List<Entity> entities;
        int tileLayoutType;
        Intrinsics.checkNotNullParameter(block, "block");
        if (isPodcastEpisodeDescriptiveShoveler(block)) {
            List<EpisodeRowItemModel> listConvertToEpisodeRowItemModel = convertToEpisodeRowItemModel(block.getContent().getEntities());
            String blockRef = block.getBlockRef();
            BrushLayoutTypeUtils brushLayoutTypeUtils = BrushLayoutTypeUtils.INSTANCE;
            String renderingType = block.getRenderingType();
            Intrinsics.checkNotNullExpressionValue(renderingType, "block.renderingType");
            return new PodcastEpisodeDescriptiveShovelerModel(blockRef, listConvertToEpisodeRowItemModel, brushLayoutTypeUtils.getTileLayoutType(renderingType), block.getTags(), block.getTitle(), block.getMoreText(), block.getUri());
        }
        if ((isPodcastViewSection(block.getTags()) || hasPodcastProgress(block.getTags())) && !isPodcastUserContentSection(block.getTags())) {
            List<VerticalItemModel> listConvertToVerticalItemModel = convertToVerticalItemModel(block.getContent().getEntities(), block.getTags());
            String blockRef2 = block.getBlockRef();
            BrushLayoutTypeUtils brushLayoutTypeUtils2 = BrushLayoutTypeUtils.INSTANCE;
            String renderingType2 = block.getRenderingType();
            Intrinsics.checkNotNullExpressionValue(renderingType2, "block.renderingType");
            return new PodcastVisualShovelerCompactModel(blockRef2, listConvertToVerticalItemModel, brushLayoutTypeUtils2.getTileLayoutType(renderingType2), block.getTags(), block.getTitle(), block.getMoreText(), block.getUri());
        }
        if (isPodcastUserContentSection(block.getTags())) {
            List<? extends BaseViewModel> listEmptyList = isPodcastUserContentSection(block.getTags()) ? CollectionsKt__CollectionsKt.emptyList() : list;
            String blockRef3 = block.getBlockRef();
            if (listEmptyList == null) {
                listEmptyList = CollectionsKt__CollectionsKt.emptyList();
            }
            List<? extends BaseViewModel> list2 = listEmptyList;
            BrushLayoutTypeUtils brushLayoutTypeUtils3 = BrushLayoutTypeUtils.INSTANCE;
            String renderingType3 = block.getRenderingType();
            Intrinsics.checkNotNullExpressionValue(renderingType3, "block.renderingType");
            return new PodcastShovelerModel(blockRef3, list2, brushLayoutTypeUtils3.getTileLayoutType(renderingType3), block.getTags(), block.getTitle(), null, block.getMoreText(), null, block.getUri(), CBORConstants.PREFIX_TYPE_OBJECT, null);
        }
        if (list != null && (CollectionsKt___CollectionsKt.firstOrNull((List) list) instanceof VideoStoryTileModel)) {
            String blockRef4 = block.getBlockRef();
            BrushLayoutTypeUtils brushLayoutTypeUtils4 = BrushLayoutTypeUtils.INSTANCE;
            String renderingType4 = block.getRenderingType();
            Intrinsics.checkNotNullExpressionValue(renderingType4, "block.renderingType");
            int tileLayoutType2 = brushLayoutTypeUtils4.getTileLayoutType(renderingType4);
            List<String> tags = block.getTags();
            String title = block.getTitle();
            String subtitle = block.getSubtitle();
            Content content = block.getContent();
            return new VideoStoryShovelerModel(blockRef4, list, tileLayoutType2, tags, title, subtitle, null, content == null ? null : content.getNextToken(), isRecentsSection(block.getTags()) ? null : block.getUri(), false, null, null, null, 7680, null);
        }
        if (list != null && (!list.isEmpty()) && isAllAccessPlaylistsSection(block.getTags())) {
            AllAccessPlaylistsUtils allAccessPlaylistsUtils = AllAccessPlaylistsUtils.INSTANCE;
            String title2 = block.getTitle();
            Intrinsics.checkNotNullExpressionValue(title2, "block.title");
            List<String> allAccessPlaylistsStringsToHighlight = allAccessPlaylistsUtils.getAllAccessPlaylistsStringsToHighlight(title2, block.getTags());
            BrushLayoutTypeUtils brushLayoutTypeUtils5 = BrushLayoutTypeUtils.INSTANCE;
            String renderingType5 = block.getRenderingType();
            Intrinsics.checkNotNullExpressionValue(renderingType5, "block.renderingType");
            int tileLayoutType3 = brushLayoutTypeUtils5.getTileLayoutType(renderingType5);
            String blockRef5 = block.getBlockRef();
            List<String> tags2 = block.getTags();
            String title3 = block.getTitle();
            String subtitle2 = block.getSubtitle();
            String moreText = block.getMoreText();
            Content content2 = block.getContent();
            ShovelerModel shovelerModel = new ShovelerModel(blockRef5, list, tileLayoutType3, tags2, title3, subtitle2, moreText, content2 == null ? null : content2.getNextToken(), isRecentsSection(block.getTags()) ? null : block.getUri(), false, null, null, allAccessPlaylistsStringsToHighlight, false, null, 28160, null);
            HotspotExperienceV2.INSTANCE.addHotspot("hotspot-on-demand-playlists", shovelerModel);
            return shovelerModel;
        }
        if (list != null && (!list.isEmpty())) {
            Content content3 = block.getContent();
            if (((content3 == null || (entities = content3.getEntities()) == null) ? null : (Entity) CollectionsKt___CollectionsKt.first((List) entities)) instanceof LiveStream) {
                BrushLayoutTypeUtils brushLayoutTypeUtils6 = BrushLayoutTypeUtils.INSTANCE;
                String renderingType6 = block.getRenderingType();
                Intrinsics.checkNotNullExpressionValue(renderingType6, "block.renderingType");
                tileLayoutType = brushLayoutTypeUtils6.get16X9TileLayoutType(renderingType6);
            } else {
                BrushLayoutTypeUtils brushLayoutTypeUtils7 = BrushLayoutTypeUtils.INSTANCE;
                String renderingType7 = block.getRenderingType();
                Intrinsics.checkNotNullExpressionValue(renderingType7, "block.renderingType");
                tileLayoutType = brushLayoutTypeUtils7.getTileLayoutType(renderingType7);
            }
            int i = tileLayoutType;
            String moreText2 = (isRecentsSection(block.getTags()) || isVideoStoryShoveler(block)) ? null : block.getMoreText();
            String blockRef6 = block.getBlockRef();
            List<String> tags3 = block.getTags();
            String title4 = block.getTitle();
            String subtitle3 = block.getSubtitle();
            Content content4 = block.getContent();
            return new ShovelerModel(blockRef6, list, i, tags3, title4, subtitle3, moreText2, content4 == null ? null : content4.getNextToken(), isRecentsSection(block.getTags()) ? null : block.getUri(), false, null, null, null, false, null, 32256, null);
        }
        if (!isRecentSearchesSection(block.getTags())) {
            return null;
        }
        String blockRef7 = block.getBlockRef();
        List listEmptyList2 = CollectionsKt__CollectionsKt.emptyList();
        BrushLayoutTypeUtils brushLayoutTypeUtils8 = BrushLayoutTypeUtils.INSTANCE;
        String renderingType8 = block.getRenderingType();
        Intrinsics.checkNotNullExpressionValue(renderingType8, "block.renderingType");
        return new ShovelerModel(blockRef7, listEmptyList2, brushLayoutTypeUtils8.getTileLayoutType(renderingType8), block.getTags(), block.getTitle(), null, null, null, null, false, null, null, null, false, null, 32736, null);
    }

    private final List<EpisodeRowItemModel> convertToEpisodeRowItemModel(List<? extends Entity> list) {
        ArrayList arrayList = new ArrayList();
        if (list != null) {
            ArrayList<PodcastEpisode> arrayList2 = new ArrayList();
            for (Object obj : list) {
                if (obj instanceof PodcastEpisode) {
                    arrayList2.add(obj);
                }
            }
            ArrayList arrayList3 = new ArrayList(CollectionsKt__IterablesKt.collectionSizeOrDefault(arrayList2, 10));
            for (PodcastEpisode podcastEpisode : arrayList2) {
                String blockRef = podcastEpisode.getBlockRef();
                Uri EMPTY = Uri.EMPTY;
                Intrinsics.checkNotNullExpressionValue(EMPTY, "EMPTY");
                List listEmptyList = CollectionsKt__CollectionsKt.emptyList();
                String catalogId = podcastEpisode.getCatalogId();
                Intrinsics.checkNotNullExpressionValue(catalogId, "it.catalogId");
                PodcastMetadata podcastMetadata = new PodcastMetadata(blockRef, EMPTY, listEmptyList, catalogId, "", "", "", "", "", podcastEpisode.getPodcastShowCatalogId(), null, 1024, null);
                String blockRef2 = podcastEpisode.getBlockRef();
                String catalogId2 = podcastEpisode.getCatalogId();
                String podcastShowCatalogId = podcastEpisode.getPodcastShowCatalogId();
                String title = podcastEpisode.getTitle();
                String podcastShowTitle = podcastEpisode.getPodcastShowTitle();
                String publishDate = podcastEpisode.getPublishDate();
                int iLongValue = (int) podcastEpisode.getDurationSeconds().longValue();
                Image podcastShowImage = podcastEpisode.getPodcastShowImage();
                arrayList3.add(Boolean.valueOf(arrayList.add(new EpisodeRowItemModel(blockRef2, podcastMetadata, 11, catalogId2, podcastShowCatalogId, title, podcastShowTitle, publishDate, iLongValue, podcastShowImage == null ? null : podcastShowImage.getUrl(), podcastEpisode.getContentTraits(), podcastEpisode.getAvailableUpsells(), podcastEpisode.getPlaybackMode(), podcastEpisode.getPodcastEpisodeVariantId(), podcastEpisode.getPodcastShowVariantId(), podcastEpisode.getAvailabilityDate()))));
            }
        }
        return arrayList;
    }

    private final List<VerticalItemModel> convertToVerticalItemModel(List<? extends Entity> list, List<String> tags) {
        ArrayList arrayList = new ArrayList();
        if (list != null) {
            ArrayList<PodcastEpisode> arrayList2 = new ArrayList();
            for (Object obj : list) {
                if (obj instanceof PodcastEpisode) {
                    arrayList2.add(obj);
                }
            }
            ArrayList arrayList3 = new ArrayList(CollectionsKt__IterablesKt.collectionSizeOrDefault(arrayList2, 10));
            for (PodcastEpisode podcastEpisode : arrayList2) {
                String blockRef = podcastEpisode.getBlockRef();
                Uri EMPTY = Uri.EMPTY;
                Intrinsics.checkNotNullExpressionValue(EMPTY, "EMPTY");
                List listEmptyList = CollectionsKt__CollectionsKt.emptyList();
                String catalogId = podcastEpisode.getCatalogId();
                Intrinsics.checkNotNullExpressionValue(catalogId, "it.catalogId");
                PodcastMetadata podcastMetadata = new PodcastMetadata(blockRef, EMPTY, listEmptyList, catalogId, "", "", "", "", "", podcastEpisode.getCatalogId(), null, 1024, null);
                boolean zHasPodcastProgress = hasPodcastProgress(tags);
                String blockRef2 = podcastEpisode.getBlockRef();
                String catalogId2 = podcastEpisode.getCatalogId();
                String podcastShowCatalogId = podcastEpisode.getPodcastShowCatalogId();
                String title = podcastEpisode.getTitle();
                String secondaryTitle = podcastEpisode.getSecondaryTitle();
                Long durationSeconds = podcastEpisode.getDurationSeconds();
                Intrinsics.checkNotNullExpressionValue(durationSeconds, "it.durationSeconds");
                arrayList3.add(Boolean.valueOf(arrayList.add(new VerticalItemModel(blockRef2, podcastMetadata, 11, catalogId2, podcastShowCatalogId, title, secondaryTitle, durationSeconds.longValue(), podcastEpisode.getImage().getUrl(), false, podcastEpisode.getPodcastEpisodeVariantId(), podcastEpisode.getPodcastShowVariantId(), podcastEpisode.getAvailableUpsells(), podcastEpisode.getPlaybackMode(), podcastEpisode.getContentTraits(), podcastEpisode.getAvailabilityDate(), zHasPodcastProgress))));
            }
        }
        if (list != null) {
            ArrayList<PodcastShow> arrayList4 = new ArrayList();
            for (Object obj2 : list) {
                if (obj2 instanceof PodcastShow) {
                    arrayList4.add(obj2);
                }
            }
            ArrayList arrayList5 = new ArrayList(CollectionsKt__IterablesKt.collectionSizeOrDefault(arrayList4, 10));
            for (PodcastShow podcastShow : arrayList4) {
                String strPodcast = Deeplinks.podcast(podcastShow.getCatalogId(), podcastShow.getTitle());
                String blockRef3 = podcastShow.getBlockRef();
                Uri EMPTY2 = Uri.EMPTY;
                Intrinsics.checkNotNullExpressionValue(EMPTY2, "EMPTY");
                List listEmptyList2 = CollectionsKt__CollectionsKt.emptyList();
                String catalogId3 = podcastShow.getCatalogId();
                Intrinsics.checkNotNullExpressionValue(catalogId3, "it.catalogId");
                arrayList5.add(Boolean.valueOf(arrayList.add(new VerticalItemModel(podcastShow.getBlockRef(), new PodcastMetadata(blockRef3, EMPTY2, listEmptyList2, catalogId3, "", strPodcast, "", "", "", podcastShow.getCatalogId(), null, 1024, null), 12, "", podcastShow.getCatalogId(), podcastShow.getTitle(), podcastShow.getSecondaryTitle(), 0L, podcastShow.getImage().getUrl(), false, "", podcastShow.getPodcastShowVariantId(), podcastShow.getAvailableUpsells(), podcastShow.getPlaybackMode(), podcastShow.getContentTraits(), "", false))));
            }
        }
        return arrayList;
    }

    private final boolean isPodcastEpisodeDescriptiveShoveler(Block block) {
        List<Entity> entities;
        if (Intrinsics.areEqual("DESCRIPTIVE_SHOVELER", block.getRenderingType())) {
            Content content = block.getContent();
            Entity entity = null;
            if (content != null && (entities = content.getEntities()) != null) {
                entity = (Entity) CollectionsKt___CollectionsKt.first((List) entities);
            }
            if (entity instanceof PodcastEpisode) {
                return true;
            }
        }
        return false;
    }

    private final boolean isRecentsSection(List<String> tags) {
        return BrushRecentlyPlayedConverter.INSTANCE.containsRecentlyPlayedTag(tags);
    }

    private final boolean isRecentSearchesSection(List<String> tags) {
        if (tags == null) {
            return false;
        }
        return BrushRecentSearchConverter.INSTANCE.containsRecentSearchesTag(tags);
    }

    private final boolean isAllAccessPlaylistsSection(List<String> tags) {
        return tags != null && tags.contains("hotspot-on-demand-playlists");
    }

    private final boolean isPodcastUserContentSection(List<String> tags) {
        if (!BrushPodcastShowConverter.INSTANCE.containsPodcastShowTag(tags)) {
            BrushPodcastEpisodeConverter.Companion companion = BrushPodcastEpisodeConverter.INSTANCE;
            if (!companion.containsPodcastJumpBackInTag(tags) && !companion.containsPodcastSavedEpisodesTag(tags)) {
                return false;
            }
        }
        return true;
    }

    private final boolean isVideoStoryShoveler(Block block) {
        Content content;
        List<Entity> entities;
        Entity entity = null;
        if (block != null && (content = block.getContent()) != null && (entities = content.getEntities()) != null) {
            entity = (Entity) CollectionsKt___CollectionsKt.firstOrNull((List) entities);
        }
        return entity instanceof VideoStory;
    }

    private final boolean isPodcastViewSection(List<String> tags) {
        return BrushPodcastTileConverter.INSTANCE.containsPodcastViewTag(tags);
    }

    private final boolean hasPodcastProgress(List<String> tags) {
        return BrushPodcastEpisodeConverter.INSTANCE.containsPodcastEpisodeProgressTag(tags);
    }

    @Override // com.amazon.music.views.library.converter.BaseContainerModelConverter
    public List<BaseViewModel> getChildrenModels(Block data) {
        SingleBaseModelConverter brushRecentsVerticalTileConverter;
        BaseViewModel baseViewModelConvert;
        Intrinsics.checkNotNullParameter(data, "data");
        String converterKey = data.getRenderingType();
        boolean zIsPodcastViewSection = isPodcastViewSection(data.getTags());
        Content content = data.getContent();
        List<Entity> entities = content == null ? null : content.getEntities();
        if (entities == null) {
            return null;
        }
        if (zIsPodcastViewSection) {
            converterKey = "podcast-view";
        } else if (CollectionsKt___CollectionsKt.firstOrNull((List) entities) instanceof VideoStory) {
            converterKey = "videoStoryTileKey";
        }
        if (isRecentsSection(data.getTags()) && Feature.home_recently_played_from_service.isEnabled()) {
            brushRecentsVerticalTileConverter = new BrushRecentsVerticalTileConverter();
        } else {
            BrushConverterFactory brushConverterFactory = BrushConverterFactory.INSTANCE;
            Intrinsics.checkNotNullExpressionValue(converterKey, "converterKey");
            BaseModelConverter converter = brushConverterFactory.getConverter(converterKey);
            brushRecentsVerticalTileConverter = converter instanceof SingleBaseModelConverter ? (SingleBaseModelConverter) converter : null;
        }
        ArrayList arrayList = new ArrayList(entities.size());
        ArrayList arrayList2 = new ArrayList();
        ArrayList arrayList3 = new ArrayList();
        ArrayList arrayList4 = new ArrayList();
        boolean z = brushRecentsVerticalTileConverter instanceof VideoStoryTileConverter;
        if (z) {
            arrayList4.addAll(((VideoStoryTileConverter) brushRecentsVerticalTileConverter).getVideoStoriesMetadata(entities));
        }
        if (AmazonApplication.getCapabilities().isLikesEverywhereEnabled(Feature.album_detail_performance_improvements_phase1B.isEnabled(true)) && (brushRecentsVerticalTileConverter instanceof BrushHorizontalTileConverter) && (!entities.isEmpty())) {
            ArrayList arrayList5 = new ArrayList();
            for (Object obj : entities) {
                if (obj instanceof Track) {
                    arrayList5.add(obj);
                }
            }
            if (!arrayList5.isEmpty()) {
                LikesServiceProxy.Companion.cacheTracksFromMuseBrushEntities$default(LikesServiceProxy.INSTANCE, null, arrayList5, 1, null);
            }
        }
        for (Entity entity : entities) {
            if (brushRecentsVerticalTileConverter instanceof BrushHorizontalTileConverter) {
                if (entity instanceof Track) {
                    baseViewModelConvert = ((BrushHorizontalTileConverter) brushRecentsVerticalTileConverter).convert(entity, arrayList2, data.getTags());
                } else {
                    Intrinsics.checkNotNullExpressionValue(entity, "entity");
                    baseViewModelConvert = ((BrushHorizontalTileConverter) brushRecentsVerticalTileConverter).convert(entity);
                }
            } else if (brushRecentsVerticalTileConverter instanceof BrushVerticalTileConverter) {
                if ((entity instanceof Track) && Intrinsics.areEqual(((Track) entity).getAssetType(), "VIDEO")) {
                    baseViewModelConvert = ((BrushVerticalTileConverter) brushRecentsVerticalTileConverter).convert(entity, arrayList3);
                } else {
                    Intrinsics.checkNotNullExpressionValue(entity, "entity");
                    baseViewModelConvert = ((BrushVerticalTileConverter) brushRecentsVerticalTileConverter).convert(entity);
                }
            } else if (z) {
                Intrinsics.checkNotNullExpressionValue(entity, "entity");
                baseViewModelConvert = ((VideoStoryTileConverter) brushRecentsVerticalTileConverter).convert(entity, arrayList4, entities.indexOf(entity));
            } else if (brushRecentsVerticalTileConverter == null) {
                baseViewModelConvert = null;
            } else {
                Intrinsics.checkNotNullExpressionValue(entity, "entity");
                baseViewModelConvert = brushRecentsVerticalTileConverter.convert(entity);
            }
            HorizontalTileModel horizontalTileModel = baseViewModelConvert instanceof HorizontalTileModel ? (HorizontalTileModel) baseViewModelConvert : null;
            if (horizontalTileModel != null && (entity instanceof Track)) {
                arrayList2.add(horizontalTileModel.getMetadata());
            }
            SimpleHorizontalTileModel simpleHorizontalTileModel = baseViewModelConvert instanceof SimpleHorizontalTileModel ? (SimpleHorizontalTileModel) baseViewModelConvert : null;
            if (simpleHorizontalTileModel != null && (entity instanceof Track)) {
                arrayList2.add(simpleHorizontalTileModel.getMetadata());
            }
            VerticalTileModel verticalTileModel = baseViewModelConvert instanceof VerticalTileModel ? (VerticalTileModel) baseViewModelConvert : null;
            if (verticalTileModel != null && (entity instanceof Track)) {
                arrayList3.add(verticalTileModel.getMetadata());
            }
            if (baseViewModelConvert != null) {
                arrayList.add(baseViewModelConvert);
            }
        }
        return arrayList;
    }
}
