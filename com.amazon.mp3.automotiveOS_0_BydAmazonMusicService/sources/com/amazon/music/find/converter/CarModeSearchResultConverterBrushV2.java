package com.amazon.music.find.converter;

import android.content.Context;
import com.amazon.music.find.R;
import com.amazon.music.find.api.util.SpellCorrectionsUtil;
import com.amazon.music.find.model.FindRenderingType;
import com.amazon.music.find.model.search.SearchResponse;
import com.amazon.music.find.providers.CacheImageProvider;
import com.amazon.music.find.providers.SearchFeaturesProvider;
import com.amazon.music.find.utils.PageUriUtils;
import com.amazon.music.platform.find.common.ContentTier;
import com.amazon.music.platform.find.common.EntityType;
import com.amazon.music.search.RelatedPlaylist;
import com.amazon.music.search.SpellCorrections;
import com.amazon.musicensembleservice.brush.Block;
import com.amazon.musicensembleservice.brush.Content;
import com.amazon.musicensembleservice.brush.Entity;
import com.amazon.musicensembleservice.brush.Image;
import com.amazon.musicensembleservice.brush.MusicEntity;
import com.amazon.musicensembleservice.brush.PageResponse;
import com.amazon.musicensembleservice.brush.PodcastEpisode;
import com.amazon.musicensembleservice.brush.PodcastShow;
import com.google.android.gms.actions.SearchIntents;
import com.google.android.gms.cast.MediaTrack;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt__CollectionsJVMKt;
import kotlin.collections.CollectionsKt__IterablesKt;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: CarModeSearchResultConverterBrushV2.kt */
/* JADX INFO: loaded from: classes4.dex */
@Metadata(d1 = {"\u0000\u008a\u0001\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\u0006\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0010$\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\b\u0016\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u0001B\u001d\u0012\u0006\u0010\u0003\u001a\u00020\u0004\u0012\u0006\u0010\u0005\u001a\u00020\u0006\u0012\u0006\u0010\u0007\u001a\u00020\b¢\u0006\u0002\u0010\tJ\u001a\u0010\n\u001a\u00020\u000b2\u0006\u0010\f\u001a\u00020\u000b2\b\u0010\r\u001a\u0004\u0018\u00010\u000bH\u0014JÌ\u0001\u0010\u000e\u001a\u00020\u000f2\u0006\u0010\u0010\u001a\u00020\u000b2\b\u0010\u0011\u001a\u0004\u0018\u00010\u000b2\u0006\u0010\u0012\u001a\u00020\u00132\b\u0010\u0014\u001a\u0004\u0018\u00010\u000b2\u0006\u0010\u0015\u001a\u00020\u00162\u0006\u0010\u0017\u001a\u00020\u00162\b\u0010\u0018\u001a\u0004\u0018\u00010\u000b2\b\u0010\u0019\u001a\u0004\u0018\u00010\u000b2\b\u0010\u001a\u001a\u0004\u0018\u00010\u000b2\b\u0010\u001b\u001a\u0004\u0018\u00010\u000b2\u000e\u0010\u001c\u001a\n\u0012\u0004\u0012\u00020\u001e\u0018\u00010\u001d2\u001a\u0010\u001f\u001a\u0016\u0012\u0004\u0012\u00020\u000b\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u000b0\u001d\u0018\u00010 2\u001a\u0010!\u001a\u0016\u0012\u0004\u0012\u00020\u000b\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u000b0\u001d\u0018\u00010 2\u0014\u0010\"\u001a\u0010\u0012\u0004\u0012\u00020\u000b\u0012\u0004\u0012\u00020#\u0018\u00010 2\b\u0010$\u001a\u0004\u0018\u00010#H\u0014J|\u0010%\u001a\u00020\u000f2\u0006\u0010\u0010\u001a\u00020\u000b2\b\u0010&\u001a\u0004\u0018\u00010\u000b2\u0006\u0010\u0012\u001a\u00020\u00132\b\u0010\u0014\u001a\u0004\u0018\u00010\u000b2\u0006\u0010\u0015\u001a\u00020\u00162\u0006\u0010\u0017\u001a\u00020\u00162\b\u0010\u0019\u001a\u0004\u0018\u00010\u000b2\b\u0010'\u001a\u0004\u0018\u00010\u000b2\u000e\u0010\u001c\u001a\n\u0012\u0004\u0012\u00020\u001e\u0018\u00010\u001d2\u001a\u0010(\u001a\u0016\u0012\u0004\u0012\u00020\u000b\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u000b0\u001d\u0018\u00010 H\u0014Ja\u0010)\u001a\u0004\u0018\u00010\u000b2\u0006\u0010*\u001a\u00020+2\b\u0010,\u001a\u0004\u0018\u00010\u000b2\u0006\u0010\u0015\u001a\u00020\u00162\b\u0010-\u001a\u0004\u0018\u00010\u000b2\b\u0010.\u001a\u0004\u0018\u00010/2\b\u00100\u001a\u0004\u0018\u00010/2\u000e\u00101\u001a\n\u0012\u0004\u0012\u000202\u0018\u00010\u001d2\b\u00103\u001a\u0004\u0018\u00010/H\u0014¢\u0006\u0002\u00104J \u00105\u001a\u0002062\u0006\u00107\u001a\u00020\u00022\u0006\u0010\u0017\u001a\u00020\u00162\u0006\u00108\u001a\u00020\u0016H\u0016J\u001a\u00109\u001a\u0002062\u0006\u0010:\u001a\u00020\u000b2\b\u0010;\u001a\u0004\u0018\u00010<H\u0016J\u0012\u0010=\u001a\u0004\u0018\u00010>2\u0006\u0010?\u001a\u00020\u000fH\u0002¨\u0006@"}, d2 = {"Lcom/amazon/music/find/converter/CarModeSearchResultConverterBrushV2;", "Lcom/amazon/music/find/converter/BrushV2Converter;", "Lcom/amazon/music/find/model/search/SearchResponse;", "context", "Landroid/content/Context;", "cacheImageProvider", "Lcom/amazon/music/find/providers/CacheImageProvider;", "searchFeaturesProvider", "Lcom/amazon/music/find/providers/SearchFeaturesProvider;", "(Landroid/content/Context;Lcom/amazon/music/find/providers/CacheImageProvider;Lcom/amazon/music/find/providers/SearchFeaturesProvider;)V", "constructCompactSecondaryTitle", "", "secondaryTitle", "suffix", "constructPodcastEpisodeEntity", "Lcom/amazon/musicensembleservice/brush/Entity;", "title", "podcastShowName", "thumbnailImage", "Lcom/amazon/musicensembleservice/brush/Image;", "blockRef", "shouldSubtitleShowEntityType", "", "showOrdinal", "podcastShowId", "catalogId", "podcastEpisodeVariantId", "podcastShowVariantId", "contentTiers", "", "Lcom/amazon/music/platform/find/common/ContentTier;", "podcastEpisodeTierBenefits", "", "podcastShowTierBenefits", "availabilityDisplayDate", "Ljava/util/Date;", "publishDate", "constructPodcastShowEntity", "podcastProviderName", "variantId", "tierBenefits", "constructSecondaryTitle", PageUriUtils.ENTITY_TYPE_QUERY_PARAM, "Lcom/amazon/music/platform/find/common/EntityType;", MediaTrack.ROLE_SUBTITLE, PageUriUtils.SOURCE_QUERY_PARAM, "albumCount", "", "trackCount", "relatedPlaylists", "Lcom/amazon/music/search/RelatedPlaylist;", "position", "(Lcom/amazon/music/platform/find/common/EntityType;Ljava/lang/String;ZLjava/lang/String;Ljava/lang/Integer;Ljava/lang/Integer;Ljava/util/List;Ljava/lang/Integer;)Ljava/lang/String;", "convert", "Lcom/amazon/musicensembleservice/brush/PageResponse;", "data", "showSpellCorrections", "convertEmptyResponse", SearchIntents.EXTRA_QUERY, "spellCorrections", "Lcom/amazon/music/search/SpellCorrections;", "convertEntityToBlock", "Lcom/amazon/musicensembleservice/brush/Block;", "entity", "DMMFindExperience_release"}, k = 1, mv = {1, 6, 0}, xi = 48)
public class CarModeSearchResultConverterBrushV2 extends BrushV2Converter<SearchResponse> {

    /* JADX INFO: compiled from: CarModeSearchResultConverterBrushV2.kt */
    @Metadata(k = 3, mv = {1, 6, 0}, xi = 48)
    public /* synthetic */ class WhenMappings {
        public static final /* synthetic */ int[] $EnumSwitchMapping$0;

        static {
            int[] iArr = new int[EntityType.values().length];
            iArr[EntityType.ARTIST.ordinal()] = 1;
            $EnumSwitchMapping$0 = iArr;
        }
    }

    @Override // com.amazon.music.find.converter.BrushV2Converter
    protected String constructCompactSecondaryTitle(String secondaryTitle, String suffix) {
        Intrinsics.checkNotNullParameter(secondaryTitle, "secondaryTitle");
        return secondaryTitle;
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public CarModeSearchResultConverterBrushV2(Context context, CacheImageProvider cacheImageProvider, SearchFeaturesProvider searchFeaturesProvider) {
        super(context, cacheImageProvider, searchFeaturesProvider);
        Intrinsics.checkNotNullParameter(context, "context");
        Intrinsics.checkNotNullParameter(cacheImageProvider, "cacheImageProvider");
        Intrinsics.checkNotNullParameter(searchFeaturesProvider, "searchFeaturesProvider");
    }

    @Override // com.amazon.music.find.converter.BrushV2Converter
    public PageResponse convert(SearchResponse data, boolean showOrdinal, boolean showSpellCorrections) {
        Intrinsics.checkNotNullParameter(data, "data");
        PageResponse pageResponse = new PageResponse();
        pageResponse.setBlockRef(data.getTopHitsBlockRef());
        pageResponse.setContent(new Content());
        Content content = pageResponse.getContent();
        Block block = new Block();
        block.setRenderingType("PRESETS_SHOVELER");
        block.setContent(new Content());
        List<Entity> listConvertTopHitItems = convertTopHitItems(data.getTopHitItems(), true, true);
        Content content2 = block.getContent();
        ArrayList arrayList = new ArrayList(CollectionsKt__IterablesKt.collectionSizeOrDefault(listConvertTopHitItems, 10));
        Iterator<T> it = listConvertTopHitItems.iterator();
        while (it.hasNext()) {
            arrayList.add(convertEntityToBlock((Entity) it.next()));
        }
        content2.setBlocks(arrayList);
        content.setBlocks(CollectionsKt__CollectionsJVMKt.listOf(block));
        return pageResponse;
    }

    private final Block convertEntityToBlock(Entity entity) {
        if (!(entity instanceof MusicEntity)) {
            return null;
        }
        Block block = new Block();
        block.setRenderingType(FindRenderingType.SEARCH_SIMPLE_HORIZONTAL_TILE.name());
        MusicEntity musicEntity = (MusicEntity) entity;
        block.setTitle(musicEntity.getPrimaryTitle());
        block.setSubtitle(musicEntity.getSecondaryTitle());
        block.setContent(new Content());
        block.getContent().setEntities(CollectionsKt__CollectionsJVMKt.listOf(entity));
        block.setBlockRef(musicEntity.getBlockRef());
        return block;
    }

    @Override // com.amazon.music.find.converter.BrushV2Converter
    public PageResponse convertEmptyResponse(String query, SpellCorrections spellCorrections) {
        Intrinsics.checkNotNullParameter(query, "query");
        PageResponse pageResponse = new PageResponse();
        pageResponse.setTitle("");
        pageResponse.setContent(new Content());
        Content content = pageResponse.getContent();
        Block block = new Block();
        block.setContent(new Content());
        block.setRenderingType(FindRenderingType.LINK_TEXT_DESCRIPTION.name());
        ArrayList arrayList = new ArrayList();
        arrayList.add(getMusicEntityForNoResults(SpellCorrectionsUtil.INSTANCE.getExecutedTerm(query, spellCorrections)));
        block.getContent().setEntities(arrayList);
        content.setBlocks(CollectionsKt__CollectionsJVMKt.listOf(block));
        return pageResponse;
    }

    @Override // com.amazon.music.find.converter.BrushV2Converter
    protected Entity constructPodcastEpisodeEntity(String title, String podcastShowName, Image thumbnailImage, String blockRef, boolean shouldSubtitleShowEntityType, boolean showOrdinal, String podcastShowId, String catalogId, String podcastEpisodeVariantId, String podcastShowVariantId, List<? extends ContentTier> contentTiers, Map<String, ? extends List<String>> podcastEpisodeTierBenefits, Map<String, ? extends List<String>> podcastShowTierBenefits, Map<String, ? extends Date> availabilityDisplayDate, Date publishDate) {
        Intrinsics.checkNotNullParameter(title, "title");
        Intrinsics.checkNotNullParameter(thumbnailImage, "thumbnailImage");
        PodcastEpisode podcastEpisode = new PodcastEpisode();
        podcastEpisode.setCatalogId(catalogId);
        podcastEpisode.setTitle(title);
        podcastEpisode.setSubtitle(BrushV2Converter.constructSecondaryTitle$default(this, EntityType.PODCAST_EPISODE, podcastShowName, shouldSubtitleShowEntityType, null, null, null, null, null, 248, null));
        podcastEpisode.setPodcastShowTitle(podcastShowName);
        podcastEpisode.setImage(thumbnailImage);
        podcastEpisode.setBlockRef(blockRef);
        podcastEpisode.setPodcastShowCatalogId(podcastShowId);
        podcastEpisode.setPodcastEpisodeVariantId(podcastEpisodeVariantId);
        return podcastEpisode;
    }

    @Override // com.amazon.music.find.converter.BrushV2Converter
    protected Entity constructPodcastShowEntity(String title, String podcastProviderName, Image thumbnailImage, String blockRef, boolean shouldSubtitleShowEntityType, boolean showOrdinal, String catalogId, String variantId, List<? extends ContentTier> contentTiers, Map<String, ? extends List<String>> tierBenefits) {
        Intrinsics.checkNotNullParameter(title, "title");
        Intrinsics.checkNotNullParameter(thumbnailImage, "thumbnailImage");
        PodcastShow podcastShow = new PodcastShow();
        podcastShow.setCatalogId(catalogId);
        podcastShow.setTitle(title);
        podcastShow.setSubtitle(BrushV2Converter.constructSecondaryTitle$default(this, EntityType.PODCAST_SHOW, podcastProviderName, shouldSubtitleShowEntityType, null, null, null, null, null, 248, null));
        podcastShow.setImage(thumbnailImage);
        podcastShow.setBlockRef(blockRef);
        podcastShow.setPodcastShowVariantId(variantId);
        return podcastShow;
    }

    @Override // com.amazon.music.find.converter.BrushV2Converter
    protected String constructSecondaryTitle(EntityType entityType, String subtitle, boolean shouldSubtitleShowEntityType, String source, Integer albumCount, Integer trackCount, List<RelatedPlaylist> relatedPlaylists, Integer position) {
        Intrinsics.checkNotNullParameter(entityType, "entityType");
        if (WhenMappings.$EnumSwitchMapping$0[entityType.ordinal()] == 1) {
            return getContext().getResources().getString(R.string.artist);
        }
        return super.constructSecondaryTitle(entityType, subtitle, shouldSubtitleShowEntityType, source, albumCount, trackCount, relatedPlaylists, position);
    }
}
