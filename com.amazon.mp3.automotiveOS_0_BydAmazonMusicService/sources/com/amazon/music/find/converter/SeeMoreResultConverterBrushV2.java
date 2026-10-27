package com.amazon.music.find.converter;

import android.content.Context;
import com.amazon.music.find.api.util.SpellCorrectionsUtil;
import com.amazon.music.find.model.FindRenderingType;
import com.amazon.music.find.model.search.SeeMoreResponse;
import com.amazon.music.find.providers.CacheImageProvider;
import com.amazon.music.find.providers.SearchFeaturesProvider;
import com.amazon.music.search.SectionType;
import com.amazon.music.search.SpellCorrections;
import com.amazon.musicensembleservice.brush.Block;
import com.amazon.musicensembleservice.brush.Content;
import com.amazon.musicensembleservice.brush.PageResponse;
import com.google.android.gms.actions.SearchIntents;
import java.util.ArrayList;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt__CollectionsJVMKt;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: SeeMoreResultConverterBrushV2.kt */
/* JADX INFO: loaded from: classes4.dex */
@Metadata(d1 = {"\u0000>\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0000\b\u0016\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u0001B\u001d\u0012\u0006\u0010\u0003\u001a\u00020\u0004\u0012\u0006\u0010\u0005\u001a\u00020\u0006\u0012\u0006\u0010\u0007\u001a\u00020\b¢\u0006\u0002\u0010\tJ \u0010\n\u001a\u00020\u000b2\u0006\u0010\f\u001a\u00020\u00022\u0006\u0010\r\u001a\u00020\u000e2\u0006\u0010\u000f\u001a\u00020\u000eH\u0016J\u001a\u0010\u0010\u001a\u00020\u000b2\u0006\u0010\u0011\u001a\u00020\u00122\b\u0010\u0013\u001a\u0004\u0018\u00010\u0014H\u0016¨\u0006\u0015"}, d2 = {"Lcom/amazon/music/find/converter/SeeMoreResultConverterBrushV2;", "Lcom/amazon/music/find/converter/BrushV2Converter;", "Lcom/amazon/music/find/model/search/SeeMoreResponse;", "context", "Landroid/content/Context;", "cacheImageProvider", "Lcom/amazon/music/find/providers/CacheImageProvider;", "searchFeaturesProvider", "Lcom/amazon/music/find/providers/SearchFeaturesProvider;", "(Landroid/content/Context;Lcom/amazon/music/find/providers/CacheImageProvider;Lcom/amazon/music/find/providers/SearchFeaturesProvider;)V", "convert", "Lcom/amazon/musicensembleservice/brush/PageResponse;", "data", "showOrdinal", "", "showSpellCorrections", "convertEmptyResponse", SearchIntents.EXTRA_QUERY, "", "spellCorrections", "Lcom/amazon/music/search/SpellCorrections;", "DMMFindExperience_release"}, k = 1, mv = {1, 6, 0}, xi = 48)
public class SeeMoreResultConverterBrushV2 extends BrushV2Converter<SeeMoreResponse> {

    /* JADX INFO: compiled from: SeeMoreResultConverterBrushV2.kt */
    @Metadata(k = 3, mv = {1, 6, 0}, xi = 48)
    public /* synthetic */ class WhenMappings {
        public static final /* synthetic */ int[] $EnumSwitchMapping$0;

        static {
            int[] iArr = new int[SectionType.values().length];
            iArr[SectionType.ALBUMS_SECTION.ordinal()] = 1;
            iArr[SectionType.ARTISTS_SECTION.ordinal()] = 2;
            iArr[SectionType.PLAYLISTS_SECTION.ordinal()] = 3;
            iArr[SectionType.COMMUNITY_PLAYLIST_SECTION.ordinal()] = 4;
            iArr[SectionType.PODCAST_EPISODE_SECTION.ordinal()] = 5;
            iArr[SectionType.PODCAST_SHOW_SECTION.ordinal()] = 6;
            iArr[SectionType.SOCCER_SECTION.ordinal()] = 7;
            iArr[SectionType.STATIONS_SECTION.ordinal()] = 8;
            iArr[SectionType.TRACKS_SECTION.ordinal()] = 9;
            iArr[SectionType.VIDEO_SECTION.ordinal()] = 10;
            iArr[SectionType.VIDEO_PLAYLIST_SECTION.ordinal()] = 11;
            $EnumSwitchMapping$0 = iArr;
        }
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public SeeMoreResultConverterBrushV2(Context context, CacheImageProvider cacheImageProvider, SearchFeaturesProvider searchFeaturesProvider) {
        super(context, cacheImageProvider, searchFeaturesProvider);
        Intrinsics.checkNotNullParameter(context, "context");
        Intrinsics.checkNotNullParameter(cacheImageProvider, "cacheImageProvider");
        Intrinsics.checkNotNullParameter(searchFeaturesProvider, "searchFeaturesProvider");
    }

    @Override // com.amazon.music.find.converter.BrushV2Converter
    public PageResponse convert(SeeMoreResponse data, boolean showOrdinal, boolean showSpellCorrections) {
        Intrinsics.checkNotNullParameter(data, "data");
        PageResponse pageResponse = new PageResponse();
        pageResponse.setContent(new Content());
        Content content = pageResponse.getContent();
        Block block = new Block();
        block.setContent(new Content());
        block.getContent().setNextToken(data.getPageToken());
        block.setRenderingType("DESCRIPTIVE_LISTING");
        SectionType sectionType = data.getSectionType();
        switch (sectionType == null ? -1 : WhenMappings.$EnumSwitchMapping$0[sectionType.ordinal()]) {
            case 1:
                block.getContent().setEntities(getMusicEntitiesForSearchAlbums(data.getSearchItems(), false, showOrdinal));
                break;
            case 2:
                block.getContent().setEntities(getMusicEntitiesForSearchArtists(data.getSearchItems(), false, showOrdinal));
                break;
            case 3:
                block.getContent().setEntities(getMusicEntitiesForSearchPlaylists(data.getSearchItems(), false, showOrdinal));
                break;
            case 4:
                block.getContent().setEntities(getMusicEntitiesForSearchPlaylists(data.getSearchItems(), false, showOrdinal));
                break;
            case 5:
                block.getContent().setEntities(getMusicEntitiesForSearchPodcastEpisodes(data.getSearchItems(), false, showOrdinal));
                break;
            case 6:
                block.getContent().setEntities(getMusicEntitiesForSearchPodcastShows(data.getSearchItems(), false, showOrdinal));
                break;
            case 7:
                block.getContent().setEntities(getMusicEntitiesForSportsItems(data.getSearchItems(), false));
                break;
            case 8:
                block.getContent().setEntities(getMusicEntitiesForSearchStations(data.getSearchItems(), false, showOrdinal));
                break;
            case 9:
                block.getContent().setEntities(getMusicEntitiesForSearchTracks(data.getSearchItems(), false, showOrdinal));
                break;
            case 10:
                block.getContent().setEntities(getMusicEntitiesForSearchTracks(data.getSearchItems(), false, showOrdinal));
                break;
            case 11:
                block.getContent().setEntities(getMusicEntitiesForSearchPlaylists(data.getSearchItems(), false, showOrdinal));
                break;
        }
        content.setBlocks(CollectionsKt__CollectionsJVMKt.listOf(block));
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
