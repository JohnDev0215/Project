package com.amazon.music.search;

import UpsellInterface.v1_0.UpsellType;
import com.amazon.mp3.environment.Environment;
import com.amazon.music.pager.PagerIterator;
import com.amazon.music.platform.model.ParentalControls;
import com.amazon.music.search.MultiSearchRequest;
import com.amazon.music.search.SearchRequest;
import com.amazon.tenzing.textsearch.v1_1.AssetQuality;
import com.amazon.tenzing.textsearch.v1_1.ContentRestrictions;
import com.amazon.tenzing.textsearch.v1_1.ResultSpecification;
import com.amazon.tenzing.textsearch.v1_1.TopHitSpecification;
import com.visualon.OSMPUtils.voMimeTypes;
import java.util.EnumSet;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;

/* JADX INFO: loaded from: classes4.dex */
public final class TopHitsSearchRequest extends MultiSearchRequest {
    private static final String[] ARTIST_TOP_HIT_DEFAULT_FIELDS = {"__default", "contentTier", UpsellType.PRIME, UpsellType.UNLIMITED};

    public static final class Builder extends MultiSearchRequest.Builder<Builder, TopHitsSearchRequest> {
        @Override // com.amazon.music.search.SearchRequest.Builder
        protected /* bridge */ /* synthetic */ SearchRequest create(List list, SearchRequest.Builder.PagerCreator pagerCreator, String str) {
            return create((List<com.amazon.tenzing.textsearch.v1_1.SearchRequest>) list, (SearchRequest.Builder.PagerCreator<ResultItem>) pagerCreator, str);
        }

        public Builder(String str) {
            super(str);
        }

        static String getResultLabel() {
            return SpecBuilder.getResultLabel("top_hits", ResultType.Catalog);
        }

        @Override // com.amazon.music.search.SearchRequest.Builder
        protected List<ResultSpecification> buildSpecs(ResultType resultType) {
            LinkedList linkedList = new LinkedList();
            ResultType resultType2 = ResultType.Catalog;
            if (resultType2 == resultType) {
                LinkedList linkedList2 = new LinkedList();
                if (isWithAlbums()) {
                    linkedList2.addAll(new SpecBuilder(SpecBuilder.DEFAULT_FIELDS_LIST).withSearchType("album").withResultTypes(EnumSet.of(resultType)).withSearchContentTier(getSearchContentTier()).withExplicitFilterEnabled(isExplicitFilterEnabled()).withExplicitLanguageFilter(isWithExplicitLanguageFilter()).withChildFriendlyContentFilter(isWithChildFriendlyContentFilter()).withAdditionalFields((AdditionalFields[]) getAlbumAdditionals().toArray(new AdditionalFields[0])).buildDocumentSpecifications(resultType));
                }
                if (isWithArtists()) {
                    linkedList2.addAll(new SpecBuilder(TopHitsSearchRequest.ARTIST_TOP_HIT_DEFAULT_FIELDS).withSearchType("artist").withResultTypes(EnumSet.of(resultType)).withSearchContentTier(getSearchContentTier()).withExplicitFilterEnabled(isExplicitFilterEnabled()).withAdditionalFields((AdditionalFields[]) getArtistAdditionals().toArray(new AdditionalFields[0])).buildDocumentSpecifications(resultType));
                }
                if (isWithPlaylists()) {
                    linkedList2.addAll(new SpecBuilder(SpecBuilder.DEFAULT_FIELDS_LIST).withSearchType(Environment.PLAYLIST_PATH).withResultTypes(EnumSet.of(resultType)).withSearchContentTier(getSearchContentTier()).withExplicitFilterEnabled(isExplicitFilterEnabled()).withAdditionalFields((AdditionalFields[]) getPlaylistAdditionals().toArray(new AdditionalFields[0])).buildDocumentSpecifications(resultType));
                }
                if (isWithStations()) {
                    linkedList2.addAll(new SpecBuilder(SpecBuilder.DEFAULT_FIELDS_LIST).withSearchType("station").withResultTypes(EnumSet.of(resultType)).withSearchContentTier(getSearchContentTier()).withExplicitFilterEnabled(isExplicitFilterEnabled()).withAdditionalFields((AdditionalFields[]) getStationAdditionals().toArray(new AdditionalFields[0])).buildDocumentSpecifications(resultType));
                }
                if (isWithTracks()) {
                    linkedList2.addAll(new SpecBuilder(SpecBuilder.DEFAULT_FIELDS_LIST).withSearchType("track").withResultTypes(EnumSet.of(resultType)).withSearchContentTier(getSearchContentTier()).withExplicitFilterEnabled(isExplicitFilterEnabled()).withExplicitLanguageFilter(isWithExplicitLanguageFilter()).withChildFriendlyContentFilter(isWithChildFriendlyContentFilter()).withAdditionalFields((AdditionalFields[]) getTrackAdditionals().toArray(new AdditionalFields[0])).buildDocumentSpecifications(resultType));
                }
                if (isWithSports()) {
                    HashMap map = new HashMap();
                    map.put("program", (AdditionalFields[]) getProgramAdditionals().toArray(new AdditionalFields[0]));
                    map.put("competition", (AdditionalFields[]) getCompetitionAdditionals().toArray(new AdditionalFields[0]));
                    SpecBuilder specBuilderWithSearchType = new SpecBuilder(SpecBuilder.DEFAULT_FIELDS_LIST).withSearchType("sports");
                    ResultType resultType3 = ResultType.Sports;
                    linkedList2.addAll(specBuilderWithSearchType.withResultTypes(EnumSet.of(resultType3)).withDocumentTypes(map).withSearchContentTier(getSearchContentTier()).withExplicitFilterEnabled(isExplicitFilterEnabled()).buildDocumentSpecifications(resultType3));
                }
                if (isWithVideos()) {
                    linkedList2.addAll(new SpecBuilder(SpecBuilder.DEFAULT_FIELDS_LIST).withSearchType(voMimeTypes.VOBASE_TYPE_VIDEO).withResultTypes(EnumSet.of(resultType)).withSearchContentTier(getSearchContentTier()).withExplicitFilterEnabled(isExplicitFilterEnabled()).withAdditionalFields((AdditionalFields[]) getVideoAdditionals().toArray(new AdditionalFields[0])).buildDocumentSpecifications(resultType));
                }
                if (isWithVideoPlaylists()) {
                    linkedList2.addAll(new SpecBuilder(SpecBuilder.DEFAULT_FIELDS_LIST).withSearchType("video_playlist").withResultTypes(EnumSet.of(resultType)).withSearchContentTier(getSearchContentTier()).withExplicitFilterEnabled(isExplicitFilterEnabled()).withAdditionalFields((AdditionalFields[]) getVideoPlaylistAdditionals().toArray(new AdditionalFields[0])).buildDocumentSpecifications(resultType));
                }
                if (isWithPodcasts()) {
                    HashMap map2 = new HashMap();
                    map2.put("show", (AdditionalFields[]) getPodcastShowAdditionals().toArray(new AdditionalFields[0]));
                    map2.put("episode", (AdditionalFields[]) getPodcastEpisodeAdditionals().toArray(new AdditionalFields[0]));
                    SpecBuilder specBuilder = new SpecBuilder(SpecBuilder.DEFAULT_FIELDS_LIST);
                    ResultType resultType4 = ResultType.Podcast_Catalog;
                    linkedList2.addAll(specBuilder.withResultTypes(EnumSet.of(resultType4)).withDocumentTypes(map2).withSearchContentTier(getSearchContentTier()).withExplicitFilterEnabled(isExplicitFilterEnabled()).buildDocumentSpecifications(resultType4));
                }
                if (isWithLiveEvents()) {
                    SpecBuilder specBuilderWithSearchType2 = new SpecBuilder(LiveEvent.DEFAULT_FIELDS).withSearchType("live_event");
                    ResultType resultType5 = ResultType.LiveEvent;
                    linkedList2.addAll(specBuilderWithSearchType2.withResultTypes(EnumSet.of(resultType5)).withSearchContentTier(getSearchContentTier()).withExplicitFilterEnabled(isExplicitFilterEnabled()).withAdditionalFields((AdditionalFields[]) getLiveEventAdditionals().toArray(new AdditionalFields[0])).buildDocumentSpecifications(resultType5));
                }
                ContentRestrictions.Builder builderWithAllowedParentalControls = ContentRestrictions.builder().withAllowedParentalControls(ParentalControls.builder().withHasExplicitLanguage(Boolean.valueOf(!isExplicitFilterEnabled())).build());
                if (getSearchContentTier() == null) {
                    throw new RuntimeException("searchContentTier should not be null");
                }
                builderWithAllowedParentalControls.withContentTier(getSearchContentTier().toString());
                builderWithAllowedParentalControls.withAssetQuality(AssetQuality.builder().withQuality(getAssetQualitiesForSearchType(resultType, "top_hits")).build());
                linkedList.add(ResultSpecification.builder().withLabel(SpecBuilder.getResultLabel("top_hits", resultType2)).withMaxResults(7).withContentRestrictions(builderWithAllowedParentalControls.build()).withDocumentSpecs(linkedList2).withTopHitSpec(TopHitSpecification.builder().withShouldUpsellMusicSubscription(Boolean.valueOf(isUpsellToUnlimitedOnTopHitsTurnedOn())).build()).build());
            }
            return linkedList;
        }

        @Override // com.amazon.music.search.SearchRequest.Builder
        protected SearchRequest.Builder.PagerCreator<ResultItem> getPagerCreator() {
            return new SearchRequest.Builder.PagerCreator<ResultItem>() { // from class: com.amazon.music.search.TopHitsSearchRequest.Builder.1
                @Override // com.amazon.music.search.SearchRequest.Builder.PagerCreator
                public PagerIterator<PageResult<ResultItem>> create(SearchService searchService, com.amazon.tenzing.textsearch.v1_1.SearchRequest searchRequest, ResultSpecification resultSpecification) {
                    return new TopHitsPagerIterator(searchService, searchRequest, resultSpecification);
                }
            };
        }

        @Override // com.amazon.music.search.SearchRequest.Builder
        protected TopHitsSearchRequest create(List<com.amazon.tenzing.textsearch.v1_1.SearchRequest> list, SearchRequest.Builder.PagerCreator<ResultItem> pagerCreator, String str) {
            return new TopHitsSearchRequest(list, pagerCreator, str);
        }
    }

    TopHitsSearchRequest(List<com.amazon.tenzing.textsearch.v1_1.SearchRequest> list, SearchRequest.Builder.PagerCreator<ResultItem> pagerCreator, String str) {
        super(list, pagerCreator, str);
    }
}
