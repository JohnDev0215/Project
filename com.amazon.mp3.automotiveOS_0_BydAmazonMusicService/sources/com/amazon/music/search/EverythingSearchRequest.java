package com.amazon.music.search;

import com.amazon.mp3.environment.Environment;
import com.amazon.music.pager.PagerIterator;
import com.amazon.music.search.Album;
import com.amazon.music.search.Artist;
import com.amazon.music.search.CommunityPlaylist;
import com.amazon.music.search.Competition;
import com.amazon.music.search.MultiSearchRequest;
import com.amazon.music.search.Playlist;
import com.amazon.music.search.PodcastEpisode;
import com.amazon.music.search.PodcastShow;
import com.amazon.music.search.Program;
import com.amazon.music.search.SearchRequest;
import com.amazon.music.search.Station;
import com.amazon.music.search.TopHitsSearchRequest;
import com.amazon.music.search.Track;
import com.amazon.music.search.Video;
import com.amazon.music.search.VideoPlaylist;
import com.amazon.tenzing.textsearch.v1_1.ResultSpecification;
import com.visualon.OSMPUtils.voMimeTypes;
import java.util.EnumSet;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;

/* JADX INFO: loaded from: classes4.dex */
public final class EverythingSearchRequest extends MultiSearchRequest {

    public static final class Builder extends MultiSearchRequest.Builder<Builder, EverythingSearchRequest> {
        @Override // com.amazon.music.search.SearchRequest.Builder
        protected /* bridge */ /* synthetic */ SearchRequest create(List list, SearchRequest.Builder.PagerCreator pagerCreator, String str) {
            return create((List<com.amazon.tenzing.textsearch.v1_1.SearchRequest>) list, (SearchRequest.Builder.PagerCreator<ResultItem>) pagerCreator, str);
        }

        public Builder(String str) {
            super(str);
            enableSpellCorrections();
        }

        @Override // com.amazon.music.search.SearchRequest.Builder
        public Builder withSpellCorrections(SpellCorrections spellCorrections) {
            super.withSpellCorrections(spellCorrections);
            return this;
        }

        /* JADX WARN: Multi-variable type inference failed */
        private void buildCatalogSpecs(List<ResultSpecification> list) {
            TopHitsSearchRequest.Builder builderWithAssetQualitiesProvider = ((TopHitsSearchRequest.Builder) ((TopHitsSearchRequest.Builder) ((TopHitsSearchRequest.Builder) ((TopHitsSearchRequest.Builder) ((TopHitsSearchRequest.Builder) ((TopHitsSearchRequest.Builder) ((TopHitsSearchRequest.Builder) ((TopHitsSearchRequest.Builder) ((TopHitsSearchRequest.Builder) ((TopHitsSearchRequest.Builder) ((TopHitsSearchRequest.Builder) ((TopHitsSearchRequest.Builder) ((TopHitsSearchRequest.Builder) ((TopHitsSearchRequest.Builder) ((TopHitsSearchRequest.Builder) new TopHitsSearchRequest.Builder(null).withSearchContentTier(getSearchContentTier())).withUpsellToUnlimitedOnTopHitsTurnedOn(isUpsellToUnlimitedOnTopHitsTurnedOn())).withAlbums(isWithAlbums())).withArtists(isWithArtists())).withPlaylists(isWithPlaylists())).withStations(isWithStations())).withTracks(isWithTracks())).withSports(isWithSports())).withVideos(isWithVideos())).withVideoPlaylists(isWithVideoPlaylists())).withPodcasts(isWithPodcasts())).withLiveEvents(isWithLiveEvents())).setIsExplicitFilterEnabled(isExplicitFilterEnabled())).withExplicitLanguageFilter(isWithExplicitLanguageFilter())).withChildFriendlyContentFilter(isWithChildFriendlyContentFilter())).withAssetQualitiesProvider(getAssetQualitiesProvider());
            if (isWithAlbums()) {
                buildAlbumSpec(list, ResultType.Catalog, builderWithAssetQualitiesProvider);
            }
            if (isWithArtists()) {
                buildArtistSpec(list, ResultType.Catalog, builderWithAssetQualitiesProvider);
            }
            if (isWithPlaylists()) {
                buildPlaylistSpec(list, ResultType.Catalog, builderWithAssetQualitiesProvider);
            }
            if (isWithCommunityPlaylists()) {
                buildCommunityPlaylistSpec(list, ResultType.Community, null);
            }
            if (isWithStations()) {
                buildStationSpec(list, builderWithAssetQualitiesProvider);
            }
            if (isWithTracks()) {
                buildTrackSpec(list, ResultType.Catalog, builderWithAssetQualitiesProvider);
            }
            if (isWithSports()) {
                buildSoccerSpec(list, ResultType.Catalog, builderWithAssetQualitiesProvider);
            }
            if (isWithVideos()) {
                buildVideoSpec(list, ResultType.Catalog, builderWithAssetQualitiesProvider);
            }
            if (isWithVideoPlaylists()) {
                buildVideoPlaylistSpec(list, ResultType.Catalog, builderWithAssetQualitiesProvider);
            }
            if (isWithPodcasts()) {
                ResultType resultType = ResultType.Podcast_Catalog;
                buildPodcastShowSpec(list, resultType, builderWithAssetQualitiesProvider);
                buildPodcastEpisodeSpec(list, resultType, builderWithAssetQualitiesProvider);
            }
            if (isWithTopHits()) {
                list.add(builderWithAssetQualitiesProvider.buildSpecs(ResultType.Catalog).get(0));
            }
        }

        private void buildLibrarySpecs(List<ResultSpecification> list) {
            ResultType resultType = ResultType.Library;
            buildAlbumSpec(list, resultType, null);
            buildArtistSpec(list, resultType, null);
            buildPlaylistSpec(list, resultType, null);
            buildTrackSpec(list, resultType, null);
        }

        private void buildSoccerSpecs(List<ResultSpecification> list) {
            buildSoccerSpec(list, ResultType.Sports, null);
        }

        private void buildPodcastCatalogSpec(List<ResultSpecification> list) {
            ResultType resultType = ResultType.Podcast_Catalog;
            buildPodcastShowSpec(list, resultType, null);
            buildPodcastEpisodeSpec(list, resultType, null);
        }

        private void buildAlbumSpec(List<ResultSpecification> list, ResultType resultType, TopHitsSearchRequest.Builder builder) {
            Album.AdditionalFields[] additionalFieldsArr = (Album.AdditionalFields[]) getAlbumAdditionals().toArray(new Album.AdditionalFields[0]);
            list.add(new SpecBuilder(SpecBuilder.DEFAULT_FIELDS_LIST).withSearchType("album").withPageSize(getPageSize()).withResultTypes(EnumSet.of(resultType)).withAdditionalFields(additionalFieldsArr).withAssetQualities(getAssetQualitiesForSearchType(resultType, "album")).withSearchContentTier(getSearchContentTier()).withExplicitFilterEnabled(isExplicitFilterEnabled()).withExplicitLanguageFilter(isWithExplicitLanguageFilter()).withChildFriendlyContentFilter(isWithChildFriendlyContentFilter()).build(resultType));
            if (builder != null) {
                builder.withAdditionalFields(additionalFieldsArr);
            }
        }

        private void buildArtistSpec(List<ResultSpecification> list, ResultType resultType, TopHitsSearchRequest.Builder builder) {
            Artist.AdditionalFields[] additionalFieldsArr = (Artist.AdditionalFields[]) getArtistAdditionals().toArray(new Artist.AdditionalFields[0]);
            list.add(new SpecBuilder(SpecBuilder.DEFAULT_FIELDS_LIST).withSearchType("artist").withPageSize(getPageSize()).withResultTypes(EnumSet.of(resultType)).withAdditionalFields(additionalFieldsArr).withAssetQualities(getAssetQualitiesForSearchType(resultType, "artist")).withSearchContentTier(getSearchContentTier()).withExplicitFilterEnabled(isExplicitFilterEnabled()).build(resultType));
            if (builder != null) {
                builder.withAdditionalFields(additionalFieldsArr);
            }
        }

        private void buildPlaylistSpec(List<ResultSpecification> list, ResultType resultType, TopHitsSearchRequest.Builder builder) {
            Playlist.AdditionalFields[] additionalFieldsArr = (Playlist.AdditionalFields[]) getPlaylistAdditionals().toArray(new Playlist.AdditionalFields[0]);
            list.add(new SpecBuilder(SpecBuilder.DEFAULT_FIELDS_LIST).withSearchType(Environment.PLAYLIST_PATH).withPageSize(getPageSize()).withResultTypes(EnumSet.of(resultType)).withAdditionalFields(additionalFieldsArr).withSearchContentTier(getSearchContentTier()).withAssetQualities(getAssetQualitiesForSearchType(resultType, Environment.PLAYLIST_PATH)).withExplicitFilterEnabled(isExplicitFilterEnabled()).build(resultType));
            if (builder != null) {
                builder.withAdditionalFields(additionalFieldsArr);
            }
        }

        private void buildCommunityPlaylistSpec(List<ResultSpecification> list, ResultType resultType, TopHitsSearchRequest.Builder builder) {
            CommunityPlaylist.AdditionalFields[] additionalFieldsArr = (CommunityPlaylist.AdditionalFields[]) getCommunityPlaylistAdditionals().toArray(new CommunityPlaylist.AdditionalFields[0]);
            list.add(new SpecBuilder().withSearchType(Environment.PLAYLIST_PATH).withPageSize(getPageSize()).withResultTypes(EnumSet.of(resultType)).withAdditionalFields(additionalFieldsArr).withSearchContentTier(getSearchContentTier()).withExplicitFilterEnabled(isExplicitFilterEnabled()).build(resultType));
            if (builder != null) {
                builder.withAdditionalFields(additionalFieldsArr);
            }
        }

        private void buildStationSpec(List<ResultSpecification> list, TopHitsSearchRequest.Builder builder) {
            Station.AdditionalFields[] additionalFieldsArr = (Station.AdditionalFields[]) getStationAdditionals().toArray(new Station.AdditionalFields[0]);
            SpecBuilder specBuilderWithPageSize = new SpecBuilder(SpecBuilder.DEFAULT_FIELDS_LIST).withSearchType("station").withPageSize(getPageSize());
            ResultType resultType = ResultType.Catalog;
            list.add(specBuilderWithPageSize.withResultTypes(EnumSet.of(resultType)).withAdditionalFields(additionalFieldsArr).withAssetQualities(getAssetQualitiesForSearchType(resultType, "station")).withSearchContentTier(getSearchContentTier()).withExplicitFilterEnabled(isExplicitFilterEnabled()).build(resultType));
            if (builder != null) {
                builder.withAdditionalFields(additionalFieldsArr);
            }
        }

        private void buildTrackSpec(List<ResultSpecification> list, ResultType resultType, TopHitsSearchRequest.Builder builder) {
            Track.AdditionalFields[] additionalFieldsArr = (Track.AdditionalFields[]) getTrackAdditionals().toArray(new Track.AdditionalFields[0]);
            list.add(new SpecBuilder(SpecBuilder.DEFAULT_FIELDS_LIST).withSearchType("track").withPageSize(getPageSize()).withResultTypes(EnumSet.of(resultType)).withAdditionalFields(additionalFieldsArr).withAssetQualities(getAssetQualitiesForSearchType(resultType, "track")).withSearchContentTier(getSearchContentTier()).withExplicitFilterEnabled(isExplicitFilterEnabled()).withExplicitLanguageFilter(isWithExplicitLanguageFilter()).withChildFriendlyContentFilter(isWithChildFriendlyContentFilter()).build(resultType));
            if (builder != null) {
                builder.withAdditionalFields(additionalFieldsArr);
            }
        }

        private void buildSoccerSpec(List<ResultSpecification> list, ResultType resultType, TopHitsSearchRequest.Builder builder) {
            if (ResultType.Catalog == resultType || ResultType.Sports == resultType) {
                Program.AdditionalFields[] additionalFieldsArr = (Program.AdditionalFields[]) getProgramAdditionals().toArray(new Program.AdditionalFields[0]);
                Competition.AdditionalFields[] additionalFieldsArr2 = (Competition.AdditionalFields[]) getCompetitionAdditionals().toArray(new Competition.AdditionalFields[0]);
                if (ResultType.Sports == resultType) {
                    list.add(new SpecBuilder(SpecBuilder.DEFAULT_FIELDS_LIST).withSearchType("sports").withPageSize(getPageSize()).withResultTypes(EnumSet.of(resultType)).withAssetQualities(getAssetQualitiesForSearchType(resultType, "sports")).withSearchContentTier(getSearchContentTier()).withExplicitFilterEnabled(isExplicitFilterEnabled()).withDocumentTypes(new HashMap<String, AdditionalFields[]>(additionalFieldsArr, additionalFieldsArr2) { // from class: com.amazon.music.search.EverythingSearchRequest.Builder.1
                        final /* synthetic */ Competition.AdditionalFields[] val$competitionAdditionals;
                        final /* synthetic */ Program.AdditionalFields[] val$programAdditionals;

                        {
                            this.val$programAdditionals = additionalFieldsArr;
                            this.val$competitionAdditionals = additionalFieldsArr2;
                            put("program", additionalFieldsArr);
                            put("competition", additionalFieldsArr2);
                        }
                    }).build(resultType));
                }
                if (builder != null) {
                    builder.withAdditionalFields(additionalFieldsArr);
                    builder.withAdditionalFields(additionalFieldsArr2);
                }
            }
        }

        private void buildVideoSpec(List<ResultSpecification> list, ResultType resultType, TopHitsSearchRequest.Builder builder) {
            Video.AdditionalFields[] additionalFieldsArr = (Video.AdditionalFields[]) getVideoAdditionals().toArray(new Video.AdditionalFields[0]);
            list.add(new SpecBuilder(SpecBuilder.DEFAULT_FIELDS_LIST).withSearchType(voMimeTypes.VOBASE_TYPE_VIDEO).withPageSize(getPageSize()).withResultTypes(EnumSet.of(resultType)).withAdditionalFields(additionalFieldsArr).withAssetQualities(getAssetQualitiesForSearchType(resultType, voMimeTypes.VOBASE_TYPE_VIDEO)).withSearchContentTier(getSearchContentTier()).withExplicitFilterEnabled(isExplicitFilterEnabled()).build(resultType));
            if (builder != null) {
                builder.withAdditionalFields(additionalFieldsArr);
            }
        }

        private void buildVideoPlaylistSpec(List<ResultSpecification> list, ResultType resultType, TopHitsSearchRequest.Builder builder) {
            VideoPlaylist.AdditionalFields[] additionalFieldsArr = (VideoPlaylist.AdditionalFields[]) getVideoPlaylistAdditionals().toArray(new VideoPlaylist.AdditionalFields[0]);
            list.add(new SpecBuilder(SpecBuilder.DEFAULT_FIELDS_LIST).withSearchType("video_playlist").withPageSize(getPageSize()).withResultTypes(EnumSet.of(resultType)).withAdditionalFields(additionalFieldsArr).withAssetQualities(getAssetQualitiesForSearchType(resultType, "video_playlist")).withSearchContentTier(getSearchContentTier()).withExplicitFilterEnabled(isExplicitFilterEnabled()).build(resultType));
            if (builder != null) {
                builder.withAdditionalFields(additionalFieldsArr);
            }
        }

        private void buildPodcastShowSpec(List<ResultSpecification> list, ResultType resultType, TopHitsSearchRequest.Builder builder) {
            PodcastShow.AdditionalFields[] additionalFieldsArr = (PodcastShow.AdditionalFields[]) getPodcastShowAdditionals().toArray(new PodcastShow.AdditionalFields[0]);
            list.add(new SpecBuilder(SpecBuilder.DEFAULT_FIELDS_LIST).withSearchType("show").withPageSize(getPageSize()).withResultTypes(EnumSet.of(resultType)).withAdditionalFields(additionalFieldsArr).withSearchContentTier(getSearchContentTier()).withAssetQualities(getAssetQualitiesForSearchType(resultType, "show")).withExplicitFilterEnabled(isExplicitFilterEnabled()).build(resultType));
            if (builder != null) {
                builder.withAdditionalFields(additionalFieldsArr);
            }
        }

        private void buildPodcastEpisodeSpec(List<ResultSpecification> list, ResultType resultType, TopHitsSearchRequest.Builder builder) {
            PodcastEpisode.AdditionalFields[] additionalFieldsArr = (PodcastEpisode.AdditionalFields[]) getPodcastEpisodeAdditionals().toArray(new PodcastEpisode.AdditionalFields[0]);
            list.add(new SpecBuilder(SpecBuilder.DEFAULT_FIELDS_LIST).withSearchType("episode").withPageSize(getPageSize()).withResultTypes(EnumSet.of(resultType)).withAdditionalFields(additionalFieldsArr).withSearchContentTier(getSearchContentTier()).withAssetQualities(getAssetQualitiesForSearchType(resultType, "episode")).withExplicitFilterEnabled(isExplicitFilterEnabled()).build(resultType));
            if (builder != null) {
                builder.withAdditionalFields(additionalFieldsArr);
            }
        }

        private void buildLiveEventSpec(List<ResultSpecification> list) {
            SpecBuilder specBuilderWithPageSize = new SpecBuilder(LiveEvent.DEFAULT_FIELDS).withSearchType("live_event").withPageSize(getPageSize());
            ResultType resultType = ResultType.LiveEvent;
            list.add(specBuilderWithPageSize.withResultTypes(EnumSet.of(resultType)).withSearchContentTier(getSearchContentTier()).withExplicitFilterEnabled(isExplicitFilterEnabled()).build(resultType));
        }

        @Override // com.amazon.music.search.SearchRequest.Builder
        protected List<ResultSpecification> buildSpecs(ResultType resultType) {
            LinkedList linkedList = new LinkedList();
            if (ResultType.Catalog == resultType) {
                buildCatalogSpecs(linkedList);
            } else if (ResultType.Library == resultType) {
                buildLibrarySpecs(linkedList);
            } else if (ResultType.Sports == resultType) {
                buildSoccerSpecs(linkedList);
            } else if (ResultType.Podcast_Catalog == resultType) {
                buildPodcastCatalogSpec(linkedList);
            } else if (ResultType.LiveEvent == resultType) {
                buildLiveEventSpec(linkedList);
            } else {
                throw new UnsupportedOperationException("Unsupported ResultType: " + resultType);
            }
            return linkedList;
        }

        @Override // com.amazon.music.search.SearchRequest.Builder
        protected SearchRequest.Builder.PagerCreator<ResultItem> getPagerCreator() {
            return new SearchRequest.Builder.PagerCreator<ResultItem>() { // from class: com.amazon.music.search.EverythingSearchRequest.Builder.2
                @Override // com.amazon.music.search.SearchRequest.Builder.PagerCreator
                public PagerIterator<PageResult<ResultItem>> create(SearchService searchService, com.amazon.tenzing.textsearch.v1_1.SearchRequest searchRequest, ResultSpecification resultSpecification) {
                    throw new UnsupportedOperationException();
                }
            };
        }

        @Override // com.amazon.music.search.SearchRequest.Builder
        protected EverythingSearchRequest create(List<com.amazon.tenzing.textsearch.v1_1.SearchRequest> list, SearchRequest.Builder.PagerCreator<ResultItem> pagerCreator, String str) {
            return new EverythingSearchRequest(list, pagerCreator, str);
        }
    }

    EverythingSearchRequest(List<com.amazon.tenzing.textsearch.v1_1.SearchRequest> list, SearchRequest.Builder.PagerCreator<ResultItem> pagerCreator, String str) {
        super(list, pagerCreator, str);
    }
}
