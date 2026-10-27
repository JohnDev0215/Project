package com.amazon.music.find.converter;

import android.content.Context;
import com.amazon.music.destination.parser.ParserUtil;
import com.amazon.music.find.R;
import com.amazon.music.find.api.util.SpellCorrectionsUtil;
import com.amazon.music.find.metrics.SearchMetricsService;
import com.amazon.music.find.model.FindRenderingType;
import com.amazon.music.find.model.search.SearchResponse;
import com.amazon.music.find.providers.CacheImageProvider;
import com.amazon.music.find.providers.SearchFeaturesProvider;
import com.amazon.music.find.utils.EntityTypeUtils;
import com.amazon.music.platform.find.common.EntityType;
import com.amazon.music.search.SectionType;
import com.amazon.music.search.SpellCorrections;
import com.amazon.musicensembleservice.brush.Block;
import com.amazon.musicensembleservice.brush.Content;
import com.amazon.musicensembleservice.brush.PageResponse;
import com.google.android.gms.actions.SearchIntents;
import java.util.ArrayList;
import java.util.List;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt__CollectionsJVMKt;
import kotlin.collections.CollectionsKt__CollectionsKt;
import kotlin.collections.CollectionsKt__IterablesKt;
import kotlin.collections.CollectionsKt___CollectionsKt;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.StringsKt__StringsKt;

/* JADX INFO: compiled from: SearchResultConverterBrushV2.kt */
/* JADX INFO: loaded from: classes4.dex */
@Metadata(d1 = {"\u0000F\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\b\u0016\u0018\u0000 \u00172\b\u0012\u0004\u0012\u00020\u00020\u0001:\u0001\u0017B%\u0012\u0006\u0010\u0003\u001a\u00020\u0004\u0012\u0006\u0010\u0005\u001a\u00020\u0006\u0012\u0006\u0010\u0007\u001a\u00020\b\u0012\u0006\u0010\t\u001a\u00020\n¢\u0006\u0002\u0010\u000bJ \u0010\f\u001a\u00020\r2\u0006\u0010\u000e\u001a\u00020\u00022\u0006\u0010\u000f\u001a\u00020\u00102\u0006\u0010\u0011\u001a\u00020\u0010H\u0016J\u001a\u0010\u0012\u001a\u00020\r2\u0006\u0010\u0013\u001a\u00020\u00142\b\u0010\u0015\u001a\u0004\u0018\u00010\u0016H\u0016R\u000e\u0010\t\u001a\u00020\nX\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u0018"}, d2 = {"Lcom/amazon/music/find/converter/SearchResultConverterBrushV2;", "Lcom/amazon/music/find/converter/BrushV2Converter;", "Lcom/amazon/music/find/model/search/SearchResponse;", "context", "Landroid/content/Context;", "cacheImageProvider", "Lcom/amazon/music/find/providers/CacheImageProvider;", "searchFeaturesProvider", "Lcom/amazon/music/find/providers/SearchFeaturesProvider;", "searchMetricsService", "Lcom/amazon/music/find/metrics/SearchMetricsService;", "(Landroid/content/Context;Lcom/amazon/music/find/providers/CacheImageProvider;Lcom/amazon/music/find/providers/SearchFeaturesProvider;Lcom/amazon/music/find/metrics/SearchMetricsService;)V", "convert", "Lcom/amazon/musicensembleservice/brush/PageResponse;", "data", "showOrdinal", "", "showSpellCorrections", "convertEmptyResponse", SearchIntents.EXTRA_QUERY, "", "spellCorrections", "Lcom/amazon/music/search/SpellCorrections;", "Companion", "DMMFindExperience_release"}, k = 1, mv = {1, 6, 0}, xi = 48)
public class SearchResultConverterBrushV2 extends BrushV2Converter<SearchResponse> {
    private static final String CUSTOM_COUNT_TAG = "customCount_";

    /* JADX INFO: renamed from: Companion, reason: from kotlin metadata */
    public static final Companion INSTANCE = new Companion(null);
    private static final String TOP_RESULTS_SECTION_TAG = "hotspot_search_top_results_section";
    private final SearchMetricsService searchMetricsService;

    /* JADX INFO: compiled from: SearchResultConverterBrushV2.kt */
    @Metadata(k = 3, mv = {1, 6, 0}, xi = 48)
    public /* synthetic */ class WhenMappings {
        public static final /* synthetic */ int[] $EnumSwitchMapping$0;

        static {
            int[] iArr = new int[SectionType.values().length];
            iArr[SectionType.ALBUMS_SECTION.ordinal()] = 1;
            iArr[SectionType.TRACKS_SECTION.ordinal()] = 2;
            iArr[SectionType.ARTISTS_SECTION.ordinal()] = 3;
            iArr[SectionType.STATIONS_SECTION.ordinal()] = 4;
            iArr[SectionType.PLAYLISTS_SECTION.ordinal()] = 5;
            iArr[SectionType.COMMUNITY_PLAYLIST_SECTION.ordinal()] = 6;
            iArr[SectionType.PODCAST_EPISODE_SECTION.ordinal()] = 7;
            iArr[SectionType.PODCAST_SHOW_SECTION.ordinal()] = 8;
            iArr[SectionType.VIDEO_PLAYLIST_SECTION.ordinal()] = 9;
            iArr[SectionType.VIDEO_SECTION.ordinal()] = 10;
            iArr[SectionType.SOCCER_SECTION.ordinal()] = 11;
            iArr[SectionType.TOP_HIT_SECTION.ordinal()] = 12;
            iArr[SectionType.SPELL_CORRECTION_SECTION.ordinal()] = 13;
            $EnumSwitchMapping$0 = iArr;
        }
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public SearchResultConverterBrushV2(Context context, CacheImageProvider cacheImageProvider, SearchFeaturesProvider searchFeaturesProvider, SearchMetricsService searchMetricsService) {
        super(context, cacheImageProvider, searchFeaturesProvider);
        Intrinsics.checkNotNullParameter(context, "context");
        Intrinsics.checkNotNullParameter(cacheImageProvider, "cacheImageProvider");
        Intrinsics.checkNotNullParameter(searchFeaturesProvider, "searchFeaturesProvider");
        Intrinsics.checkNotNullParameter(searchMetricsService, "searchMetricsService");
        this.searchMetricsService = searchMetricsService;
    }

    /* JADX INFO: compiled from: SearchResultConverterBrushV2.kt */
    @Metadata(d1 = {"\u0000(\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0006\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u0010\u0010\u0006\u001a\u00020\u00042\u0006\u0010\u0007\u001a\u00020\bH\u0002J!\u0010\t\u001a\u0004\u0018\u00010\n2\b\u0010\u0007\u001a\u0004\u0018\u00010\b2\u0006\u0010\u000b\u001a\u00020\fH\u0002¢\u0006\u0002\u0010\rJ\u0012\u0010\u000e\u001a\u0004\u0018\u00010\b2\u0006\u0010\u000f\u001a\u00020\u0004H\u0002J\u0015\u0010\u0010\u001a\u0004\u0018\u00010\n2\u0006\u0010\u000b\u001a\u00020\f¢\u0006\u0002\u0010\u0011R\u000e\u0010\u0003\u001a\u00020\u0004X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0004X\u0082T¢\u0006\u0002\n\u0000¨\u0006\u0012"}, d2 = {"Lcom/amazon/music/find/converter/SearchResultConverterBrushV2$Companion;", "", "()V", "CUSTOM_COUNT_TAG", "", "TOP_RESULTS_SECTION_TAG", "constructDisplayCountTag", "sectionType", "Lcom/amazon/music/search/SectionType;", "getOverrideDisplayCount", "", "block", "Lcom/amazon/musicensembleservice/brush/Block;", "(Lcom/amazon/music/search/SectionType;Lcom/amazon/musicensembleservice/brush/Block;)Ljava/lang/Integer;", "getSectionTypeFromTag", ParserUtil.TAG_QUERY_PARAM_NAME, "overrideDisplayCount", "(Lcom/amazon/musicensembleservice/brush/Block;)Ljava/lang/Integer;", "DMMFindExperience_release"}, k = 1, mv = {1, 6, 0}, xi = 48)
    public static final class Companion {

        /* JADX INFO: compiled from: SearchResultConverterBrushV2.kt */
        @Metadata(k = 3, mv = {1, 6, 0}, xi = 48)
        public /* synthetic */ class WhenMappings {
            public static final /* synthetic */ int[] $EnumSwitchMapping$0;

            static {
                int[] iArr = new int[SectionType.values().length];
                iArr[SectionType.TOP_HIT_SECTION.ordinal()] = 1;
                $EnumSwitchMapping$0 = iArr;
            }
        }

        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }

        /* JADX INFO: Access modifiers changed from: private */
        public final String constructDisplayCountTag(SectionType sectionType) {
            return Intrinsics.stringPlus(SearchResultConverterBrushV2.CUSTOM_COUNT_TAG, sectionType);
        }

        private final SectionType getSectionTypeFromTag(String tag) {
            try {
                return SectionType.valueOf(StringsKt__StringsKt.substringAfter$default(tag, SearchResultConverterBrushV2.CUSTOM_COUNT_TAG, (String) null, 2, (Object) null));
            } catch (Exception unused) {
                return null;
            }
        }

        private final Integer getOverrideDisplayCount(SectionType sectionType, Block block) {
            if ((sectionType == null ? -1 : WhenMappings.$EnumSwitchMapping$0[sectionType.ordinal()]) == 1) {
                return Integer.valueOf(block.getContent().getEntities().size());
            }
            return null;
        }

        public final Integer overrideDisplayCount(Block block) {
            Intrinsics.checkNotNullParameter(block, "block");
            List<String> tags = block.getTags();
            if (tags != null) {
                for (String tag : tags) {
                    Intrinsics.checkNotNullExpressionValue(tag, "tag");
                    if (StringsKt__StringsKt.contains$default((CharSequence) tag, (CharSequence) SearchResultConverterBrushV2.CUSTOM_COUNT_TAG, false, 2, (Object) null)) {
                        Companion companion = SearchResultConverterBrushV2.INSTANCE;
                        return companion.getOverrideDisplayCount(companion.getSectionTypeFromTag(tag), block);
                    }
                }
            }
            return null;
        }
    }

    @Override // com.amazon.music.find.converter.BrushV2Converter
    public PageResponse convert(SearchResponse data, boolean showOrdinal, boolean showSpellCorrections) {
        List<SectionType> listDistinct;
        Intrinsics.checkNotNullParameter(data, "data");
        PageResponse pageResponse = new PageResponse();
        pageResponse.setTitle("");
        pageResponse.setContent(new Content());
        Content content = pageResponse.getContent();
        List<SectionType> sectionOrdering = data.getSectionOrdering();
        ArrayList arrayList = null;
        if (sectionOrdering != null && (listDistinct = CollectionsKt___CollectionsKt.distinct(sectionOrdering)) != null) {
            ArrayList arrayList2 = new ArrayList(CollectionsKt__IterablesKt.collectionSizeOrDefault(listDistinct, 10));
            for (SectionType sectionType : listDistinct) {
                Block block = new Block();
                block.setContent(new Content());
                block.setMoreText(getContext().getResources().getString(R.string.see_more));
                EntityType entityTypeConvertFromSectionType = EntityTypeUtils.INSTANCE.convertFromSectionType(sectionType);
                if (entityTypeConvertFromSectionType != null) {
                    block.setUri(entityTypeConvertFromSectionType.toString());
                }
                block.setRenderingType("DESCRIPTIVE_SHOWCASE");
                switch (WhenMappings.$EnumSwitchMapping$0[sectionType.ordinal()]) {
                    case 1:
                        block.setTitle(getContext().getResources().getString(R.string.albums));
                        block.getContent().setEntities(getMusicEntitiesForSearchAlbums(data.getAlbums(), false, showOrdinal));
                        block.setBlockRef(data.getAlbumsBlockRef());
                        break;
                    case 2:
                        block.setTitle(getContext().getResources().getString(R.string.tracks));
                        block.getContent().setEntities(getMusicEntitiesForSearchTracks(data.getTracks(), false, showOrdinal));
                        block.setBlockRef(data.getTracksBlockRef());
                        break;
                    case 3:
                        block.setTitle(getContext().getResources().getString(R.string.artists));
                        block.getContent().setEntities(getMusicEntitiesForSearchArtists(data.getArtists(), false, showOrdinal));
                        block.setBlockRef(data.getArtistsBlockRef());
                        break;
                    case 4:
                        block.setTitle(getContext().getResources().getString(R.string.stations));
                        block.getContent().setEntities(getMusicEntitiesForSearchStations(data.getStations(), false, showOrdinal));
                        block.setBlockRef(data.getStationsBlockRef());
                        break;
                    case 5:
                        block.setTitle(getContext().getResources().getString(R.string.playlists));
                        block.getContent().setEntities(getMusicEntitiesForSearchPlaylists(data.getPlaylists(), false, showOrdinal));
                        block.setBlockRef(data.getPlaylistsBlockRef());
                        break;
                    case 6:
                        block.setTitle(getContext().getResources().getString(R.string.community_playlists));
                        block.getContent().setEntities(getMusicEntitiesForSearchPlaylists(data.getCommunityPlaylists(), false, showOrdinal));
                        block.setBlockRef(data.getCommunityPlaylistsBlockRef());
                        break;
                    case 7:
                        block.setTitle(getContext().getResources().getString(R.string.podcast_episodes));
                        block.getContent().setEntities(getMusicEntitiesForSearchPodcastEpisodes(data.getPodcastEpisodes(), false, showOrdinal));
                        block.setBlockRef(data.getPodcastEpisodesBlockRef());
                        break;
                    case 8:
                        block.setTitle(getContext().getResources().getString(R.string.podcasts));
                        block.getContent().setEntities(getMusicEntitiesForSearchPodcastShows(data.getPodcastShows(), false, showOrdinal));
                        block.setBlockRef(data.getPodcastShowsBlockRef());
                        break;
                    case 9:
                        block.setTitle(getContext().getResources().getString(R.string.video_playlists));
                        block.getContent().setEntities(getMusicEntitiesForSearchPlaylists(data.getVideoPlaylists(), false, showOrdinal));
                        block.setBlockRef(data.getVideoPlaylistsBlockRef());
                        break;
                    case 10:
                        block.setTitle(getContext().getResources().getString(R.string.videos));
                        block.getContent().setEntities(getMusicEntitiesForSearchTracks(data.getVideos(), false, showOrdinal));
                        block.setBlockRef(data.getVideosBlockRef());
                        break;
                    case 11:
                        block.setTitle(getContext().getResources().getString(R.string.dmusic_amp_browse_poldi_header));
                        block.getContent().setEntities(getMusicEntitiesForSportsItems(data.getSportsItems(), false));
                        block.setBlockRef(data.getSportsBlockRef());
                        break;
                    case 12:
                        block.setTitle(getContext().getResources().getString(R.string.top_results));
                        block.getContent().setEntities(convertTopHitItems(data.getTopHitItems(), true, showOrdinal));
                        block.setBlockRef(data.getTopHitsBlockRef());
                        block.setTags(CollectionsKt__CollectionsKt.listOf((Object[]) new String[]{INSTANCE.constructDisplayCountTag(sectionType), TOP_RESULTS_SECTION_TAG}));
                        break;
                    case 13:
                        SpellCorrections spellCorrections = data.getSpellCorrections();
                        String executedTerm = data.getExecutedTerm();
                        String originalQuery = data.getOriginalQuery();
                        if (spellCorrections != null && executedTerm != null && originalQuery != null) {
                            block.setRenderingType(FindRenderingType.LINK_TEXT_DESCRIPTION.name());
                            block.getContent().setEntities(showSpellCorrections ? CollectionsKt__CollectionsJVMKt.listOf(BrushV2Converter.getMusicEntityForSpellCorrections$default(this, spellCorrections, executedTerm, originalQuery, false, 8, null)) : CollectionsKt__CollectionsKt.emptyList());
                        }
                        break;
                }
                arrayList2.add(block);
            }
            arrayList = arrayList2;
        }
        content.setBlocks(arrayList);
        return pageResponse;
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
        if (spellCorrections != null) {
            arrayList.add(getMusicEntityForSpellCorrections(spellCorrections, null, query, true));
        }
        block.getContent().setEntities(arrayList);
        content.setBlocks(CollectionsKt__CollectionsJVMKt.listOf(block));
        return pageResponse;
    }
}
