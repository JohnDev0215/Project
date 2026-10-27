package com.amazon.music.search;

import com.amazon.music.platform.model.CatalogPodcastEpisode;
import com.amazon.music.platform.model.CatalogPodcastShow;
import com.amazon.music.platform.model.CatalogVideo;
import com.amazon.music.platform.model.CatalogVideoPlaylist;
import com.amazon.music.platform.model.Document;
import com.amazon.music.platform.model.LiveSportsCompetition;
import com.amazon.music.platform.model.LiveSportsProgram;
import com.amazon.music.search.AlbumSearchRequest;
import com.amazon.music.search.ArtistSearchRequest;
import com.amazon.music.search.CommunityPlaylistSearchRequest;
import com.amazon.music.search.LiveEventSearchRequest;
import com.amazon.music.search.PlaylistSearchRequest;
import com.amazon.music.search.PodcastEpisodeSearchRequest;
import com.amazon.music.search.PodcastShowSearchRequest;
import com.amazon.music.search.SportsSearchRequest;
import com.amazon.music.search.StationSearchRequest;
import com.amazon.music.search.TopHitsSearchRequest;
import com.amazon.music.search.TrackSearchRequest;
import com.amazon.music.search.VideoPlaylistSearchRequest;
import com.amazon.music.search.VideoSearchRequest;
import com.amazon.tenzing.textsearch.v1_1.FeaturesResponse;
import com.amazon.tenzing.textsearch.v1_1.Hit;
import com.amazon.tenzing.textsearch.v1_1.SearchResponse;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;

/* JADX INFO: loaded from: classes4.dex */
public final class SearchResult {
    private final List<SectionType> catalogSectionOrder;
    private final Map<String, com.amazon.tenzing.textsearch.v1_1.SearchResult> mResults;
    private final Map<String, SpellCorrections> mSpellCorrections;

    private interface ResultCreator<TResultItem> {
        TResultItem create(Hit hit, Map<String, String> map);
    }

    SearchResult() {
        this(null, null);
    }

    SearchResult(List<SearchResponse> list, List<String> list2) {
        this.mResults = new HashMap();
        this.mSpellCorrections = new HashMap();
        ArrayList arrayList = new ArrayList();
        this.catalogSectionOrder = arrayList;
        Set<String> availableCatalogTypes = getAvailableCatalogTypes();
        if (list != null) {
            arrayList.add(SectionType.SPELL_CORRECTION_SECTION);
            for (SearchResponse searchResponse : list) {
                for (com.amazon.tenzing.textsearch.v1_1.SearchResult searchResult : searchResponse.getResults()) {
                    String label = searchResult.getLabel();
                    this.mResults.put(label, searchResult);
                    if (availableCatalogTypes.contains(label)) {
                        this.catalogSectionOrder.add(SectionType.fromResultLabel(label));
                    }
                }
                int iIndexOf = list.indexOf(searchResponse);
                FeaturesResponse features = searchResponse.getFeatures();
                this.mSpellCorrections.put(list2.get(iIndexOf), (features == null || features.getSpellCorrection() == null) ? null : SpellCorrections.create(features.getSpellCorrection()));
            }
        }
    }

    static Set<String> getAvailableCatalogTypes() {
        HashSet hashSet = new HashSet();
        ResultType resultType = ResultType.Catalog;
        hashSet.add(AlbumSearchRequest.Builder.getResultLabel(resultType));
        hashSet.add(ArtistSearchRequest.Builder.getResultLabel(resultType));
        hashSet.add(PlaylistSearchRequest.Builder.getResultLabel(resultType));
        hashSet.add(SportsSearchRequest.Builder.getResultLabel());
        hashSet.add(StationSearchRequest.Builder.getResultLabel());
        hashSet.add(TrackSearchRequest.Builder.getResultLabel(resultType));
        hashSet.add(VideoSearchRequest.Builder.getResultLabel(resultType));
        hashSet.add(VideoPlaylistSearchRequest.Builder.getResultLabel(resultType));
        hashSet.add(TopHitsSearchRequest.Builder.getResultLabel());
        ResultType resultType2 = ResultType.Podcast_Catalog;
        hashSet.add(PodcastShowSearchRequest.Builder.getResultLabel(resultType2));
        hashSet.add(PodcastEpisodeSearchRequest.Builder.getResultLabel(resultType2));
        hashSet.add(CommunityPlaylistSearchRequest.Builder.getResultLabel(ResultType.Community));
        hashSet.add(LiveEventSearchRequest.Builder.getResultLabel());
        return hashSet;
    }

    public List<SectionType> getCatalogSectionOrder() {
        return this.catalogSectionOrder;
    }

    static Map<String, String> getContentInfo(com.amazon.tenzing.textsearch.v1_1.SearchResult searchResult) {
        return new HashMap();
    }

    private <TResultItem extends ResultItem> PageResult<TResultItem> getResults(String str, ResultCreator<TResultItem> resultCreator) {
        if (!this.mResults.containsKey(str)) {
            return null;
        }
        LinkedList linkedList = new LinkedList();
        com.amazon.tenzing.textsearch.v1_1.SearchResult searchResult = this.mResults.get(str);
        Map<String, String> contentInfo = getContentInfo(searchResult);
        Iterator<Hit> it = searchResult.getHits().iterator();
        while (it.hasNext()) {
            linkedList.add(resultCreator.create(it.next(), contentInfo));
        }
        return new PageResult<>(linkedList, searchResult.getTotalHitCount().longValue());
    }

    public SpellCorrections getSpellCorrections(String str) {
        return this.mSpellCorrections.get(str);
    }

    public PageResult<Album> getAlbums(ResultType resultType) {
        return getResults(AlbumSearchRequest.Builder.getResultLabel(resultType), new ResultCreator<Album>() { // from class: com.amazon.music.search.SearchResult.1
            @Override // com.amazon.music.search.SearchResult.ResultCreator
            public /* bridge */ /* synthetic */ Album create(Hit hit, Map map) {
                return create(hit, (Map<String, String>) map);
            }

            /* JADX WARN: Can't rename method to resolve collision */
            @Override // com.amazon.music.search.SearchResult.ResultCreator
            public Album create(Hit hit, Map<String, String> map) {
                return new Album(hit, map);
            }
        });
    }

    public PageResult<Artist> getArtists(ResultType resultType) {
        return getResults(ArtistSearchRequest.Builder.getResultLabel(resultType), new ResultCreator<Artist>() { // from class: com.amazon.music.search.SearchResult.2
            @Override // com.amazon.music.search.SearchResult.ResultCreator
            public /* bridge */ /* synthetic */ Artist create(Hit hit, Map map) {
                return create(hit, (Map<String, String>) map);
            }

            /* JADX WARN: Can't rename method to resolve collision */
            @Override // com.amazon.music.search.SearchResult.ResultCreator
            public Artist create(Hit hit, Map<String, String> map) {
                return new Artist(hit, map);
            }
        });
    }

    public PageResult<Playlist> getPlaylists(ResultType resultType) {
        return getResults(PlaylistSearchRequest.Builder.getResultLabel(resultType), new ResultCreator<Playlist>() { // from class: com.amazon.music.search.SearchResult.3
            @Override // com.amazon.music.search.SearchResult.ResultCreator
            public /* bridge */ /* synthetic */ Playlist create(Hit hit, Map map) {
                return create(hit, (Map<String, String>) map);
            }

            /* JADX WARN: Can't rename method to resolve collision */
            @Override // com.amazon.music.search.SearchResult.ResultCreator
            public Playlist create(Hit hit, Map<String, String> map) {
                return new Playlist(hit, map);
            }
        });
    }

    public PageResult<CommunityPlaylist> getCommunityPlaylists(ResultType resultType) {
        return getResults(CommunityPlaylistSearchRequest.Builder.getResultLabel(resultType), new ResultCreator<CommunityPlaylist>() { // from class: com.amazon.music.search.SearchResult.4
            @Override // com.amazon.music.search.SearchResult.ResultCreator
            public /* bridge */ /* synthetic */ CommunityPlaylist create(Hit hit, Map map) {
                return create(hit, (Map<String, String>) map);
            }

            /* JADX WARN: Can't rename method to resolve collision */
            @Override // com.amazon.music.search.SearchResult.ResultCreator
            public CommunityPlaylist create(Hit hit, Map<String, String> map) {
                return new CommunityPlaylist(hit, map);
            }
        });
    }

    public PageResult<SportsItem> getSports() {
        return getResults(SportsSearchRequest.Builder.getResultLabel(), new ResultCreator<SportsItem>() { // from class: com.amazon.music.search.SearchResult.5
            @Override // com.amazon.music.search.SearchResult.ResultCreator
            public /* bridge */ /* synthetic */ SportsItem create(Hit hit, Map map) {
                return create(hit, (Map<String, String>) map);
            }

            /* JADX WARN: Can't rename method to resolve collision */
            @Override // com.amazon.music.search.SearchResult.ResultCreator
            public SportsItem create(Hit hit, Map<String, String> map) {
                Document document = hit.getDocument();
                if (document instanceof LiveSportsCompetition) {
                    return new Competition(hit, map);
                }
                if (document instanceof LiveSportsProgram) {
                    return new Program(hit, map);
                }
                return null;
            }
        });
    }

    public PageResult<Station> getStations() {
        return getResults(StationSearchRequest.Builder.getResultLabel(), new ResultCreator<Station>() { // from class: com.amazon.music.search.SearchResult.6
            @Override // com.amazon.music.search.SearchResult.ResultCreator
            public /* bridge */ /* synthetic */ Station create(Hit hit, Map map) {
                return create(hit, (Map<String, String>) map);
            }

            /* JADX WARN: Can't rename method to resolve collision */
            @Override // com.amazon.music.search.SearchResult.ResultCreator
            public Station create(Hit hit, Map<String, String> map) {
                return new Station(hit, map);
            }
        });
    }

    public PageResult<Track> getTracks(ResultType resultType) {
        return getResults(TrackSearchRequest.Builder.getResultLabel(resultType), new ResultCreator<Track>() { // from class: com.amazon.music.search.SearchResult.7
            @Override // com.amazon.music.search.SearchResult.ResultCreator
            public /* bridge */ /* synthetic */ Track create(Hit hit, Map map) {
                return create(hit, (Map<String, String>) map);
            }

            /* JADX WARN: Can't rename method to resolve collision */
            @Override // com.amazon.music.search.SearchResult.ResultCreator
            public Track create(Hit hit, Map<String, String> map) {
                return new Track(hit, map);
            }
        });
    }

    public PageResult<Video> getVideos(ResultType resultType) {
        return getResults(VideoSearchRequest.Builder.getResultLabel(resultType), new ResultCreator<Video>() { // from class: com.amazon.music.search.SearchResult.8
            @Override // com.amazon.music.search.SearchResult.ResultCreator
            public /* bridge */ /* synthetic */ Video create(Hit hit, Map map) {
                return create(hit, (Map<String, String>) map);
            }

            /* JADX WARN: Can't rename method to resolve collision */
            @Override // com.amazon.music.search.SearchResult.ResultCreator
            public Video create(Hit hit, Map<String, String> map) {
                return new Video(hit, map);
            }
        });
    }

    public PageResult<VideoPlaylist> getVideoPlaylists(ResultType resultType) {
        return getResults(VideoPlaylistSearchRequest.Builder.getResultLabel(resultType), new ResultCreator<VideoPlaylist>() { // from class: com.amazon.music.search.SearchResult.9
            @Override // com.amazon.music.search.SearchResult.ResultCreator
            public /* bridge */ /* synthetic */ VideoPlaylist create(Hit hit, Map map) {
                return create(hit, (Map<String, String>) map);
            }

            /* JADX WARN: Can't rename method to resolve collision */
            @Override // com.amazon.music.search.SearchResult.ResultCreator
            public VideoPlaylist create(Hit hit, Map<String, String> map) {
                if (hit.getDocument() instanceof CatalogVideoPlaylist) {
                    return new VideoPlaylist(hit, map);
                }
                return null;
            }
        });
    }

    public PageResult<PodcastShow> getPodcastShows(ResultType resultType) {
        return getResults(PodcastShowSearchRequest.Builder.getResultLabel(resultType), new ResultCreator<PodcastShow>() { // from class: com.amazon.music.search.SearchResult.10
            @Override // com.amazon.music.search.SearchResult.ResultCreator
            public /* bridge */ /* synthetic */ PodcastShow create(Hit hit, Map map) {
                return create(hit, (Map<String, String>) map);
            }

            /* JADX WARN: Can't rename method to resolve collision */
            @Override // com.amazon.music.search.SearchResult.ResultCreator
            public PodcastShow create(Hit hit, Map<String, String> map) {
                return new PodcastShow(hit, map);
            }
        });
    }

    public PageResult<PodcastEpisode> getPodcastEpisodes(ResultType resultType) {
        return getResults(PodcastEpisodeSearchRequest.Builder.getResultLabel(resultType), new ResultCreator<PodcastEpisode>() { // from class: com.amazon.music.search.SearchResult.11
            @Override // com.amazon.music.search.SearchResult.ResultCreator
            public /* bridge */ /* synthetic */ PodcastEpisode create(Hit hit, Map map) {
                return create(hit, (Map<String, String>) map);
            }

            /* JADX WARN: Can't rename method to resolve collision */
            @Override // com.amazon.music.search.SearchResult.ResultCreator
            public PodcastEpisode create(Hit hit, Map<String, String> map) {
                return new PodcastEpisode(hit, map);
            }
        });
    }

    public PageResult<ResultItem> getTopHits() {
        return getResults(TopHitsSearchRequest.Builder.getResultLabel(), new ResultCreator<ResultItem>() { // from class: com.amazon.music.search.SearchResult.12
            @Override // com.amazon.music.search.SearchResult.ResultCreator
            public /* bridge */ /* synthetic */ ResultItem create(Hit hit, Map map) {
                return create(hit, (Map<String, String>) map);
            }

            /* JADX WARN: Can't rename method to resolve collision */
            @Override // com.amazon.music.search.SearchResult.ResultCreator
            public ResultItem create(Hit hit, Map<String, String> map) {
                if (hit.getDocument() instanceof com.amazon.music.platform.model.Album) {
                    return new Album(hit, map);
                }
                if (hit.getDocument() instanceof com.amazon.music.platform.model.Artist) {
                    return new Artist(hit, map);
                }
                if ((hit.getDocument() instanceof com.amazon.music.platform.model.Playlist) && !(hit.getDocument() instanceof CatalogVideoPlaylist)) {
                    return new Playlist(hit, map);
                }
                if (hit.getDocument() instanceof com.amazon.music.platform.model.Station) {
                    return new Station(hit, map);
                }
                if (hit.getDocument() instanceof com.amazon.music.platform.model.Track) {
                    return new Track(hit, map);
                }
                if (hit.getDocument() instanceof LiveSportsProgram) {
                    return new Program(hit, map);
                }
                if (hit.getDocument() instanceof LiveSportsCompetition) {
                    return new Competition(hit, map);
                }
                if (hit.getDocument() instanceof CatalogVideo) {
                    return new Video(hit, map);
                }
                if (hit.getDocument() instanceof CatalogVideoPlaylist) {
                    return new VideoPlaylist(hit, map);
                }
                if (hit.getDocument() instanceof CatalogPodcastShow) {
                    return new PodcastShow(hit, map);
                }
                if (hit.getDocument() instanceof CatalogPodcastEpisode) {
                    return new PodcastEpisode(hit, map);
                }
                if (hit.getDocument() instanceof com.amazon.music.platform.model.LiveEvent) {
                    return new LiveEvent(hit, map);
                }
                return null;
            }
        });
    }

    public String getArtistContainerBlockRef(ResultType resultType) {
        String resultLabel = ArtistSearchRequest.Builder.getResultLabel(resultType);
        if (this.mResults.containsKey(resultLabel)) {
            return getContainerBlockRef(this.mResults.get(resultLabel));
        }
        return null;
    }

    public String getAlbumContainerBlockRef(ResultType resultType) {
        String resultLabel = AlbumSearchRequest.Builder.getResultLabel(resultType);
        if (this.mResults.containsKey(resultLabel)) {
            return getContainerBlockRef(this.mResults.get(resultLabel));
        }
        return null;
    }

    public String getStationContainerBlockRef() {
        String resultLabel = StationSearchRequest.Builder.getResultLabel();
        if (this.mResults.containsKey(resultLabel)) {
            return getContainerBlockRef(this.mResults.get(resultLabel));
        }
        return null;
    }

    public String getTrackContainerBlockRef(ResultType resultType) {
        String resultLabel = TrackSearchRequest.Builder.getResultLabel(resultType);
        if (this.mResults.containsKey(resultLabel)) {
            return getContainerBlockRef(this.mResults.get(resultLabel));
        }
        return null;
    }

    public String getPlaylistContainerBlockRef(ResultType resultType) {
        String resultLabel = PlaylistSearchRequest.Builder.getResultLabel(resultType);
        if (this.mResults.containsKey(resultLabel)) {
            return getContainerBlockRef(this.mResults.get(resultLabel));
        }
        return null;
    }

    public String getCommunityPlaylistContainerBlockRef(ResultType resultType) {
        String resultLabel = CommunityPlaylistSearchRequest.Builder.getResultLabel(resultType);
        if (this.mResults.containsKey(resultLabel)) {
            return getContainerBlockRef(this.mResults.get(resultLabel));
        }
        return null;
    }

    public String getSportsContainerBlockRef() {
        String resultLabel = SportsSearchRequest.Builder.getResultLabel();
        if (this.mResults.containsKey(resultLabel)) {
            return getContainerBlockRef(this.mResults.get(resultLabel));
        }
        return null;
    }

    public String getTopHitsContainerBlockRef() {
        String resultLabel = TopHitsSearchRequest.Builder.getResultLabel();
        if (this.mResults.containsKey(resultLabel)) {
            return getContainerBlockRef(this.mResults.get(resultLabel));
        }
        return null;
    }

    public String getVideosContainerBlockRef(ResultType resultType) {
        String resultLabel = VideoSearchRequest.Builder.getResultLabel(resultType);
        if (this.mResults.containsKey(resultLabel)) {
            return getContainerBlockRef(this.mResults.get(resultLabel));
        }
        return null;
    }

    public String getVideoPlaylistsContainerBlockRef(ResultType resultType) {
        String resultLabel = VideoPlaylistSearchRequest.Builder.getResultLabel(resultType);
        if (this.mResults.containsKey(resultLabel)) {
            return getContainerBlockRef(this.mResults.get(resultLabel));
        }
        return null;
    }

    public String getPodcastShowsContainerBlockRef(ResultType resultType) {
        String resultLabel = PodcastShowSearchRequest.Builder.getResultLabel(resultType);
        if (this.mResults.containsKey(resultLabel)) {
            return getContainerBlockRef(this.mResults.get(resultLabel));
        }
        return null;
    }

    public String getPodcastEpisodesContainerBlockRef(ResultType resultType) {
        String resultLabel = PodcastEpisodeSearchRequest.Builder.getResultLabel(resultType);
        if (this.mResults.containsKey(resultLabel)) {
            return getContainerBlockRef(this.mResults.get(resultLabel));
        }
        return null;
    }

    private String getContainerBlockRef(com.amazon.tenzing.textsearch.v1_1.SearchResult searchResult) {
        if (searchResult == null) {
            return null;
        }
        return searchResult.getBlockRef();
    }
}
