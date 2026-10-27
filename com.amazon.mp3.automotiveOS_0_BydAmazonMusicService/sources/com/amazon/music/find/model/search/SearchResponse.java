package com.amazon.music.find.model.search;

import com.amazon.music.find.model.filter.FilterMetadata;
import com.amazon.music.search.SectionType;
import com.amazon.music.search.SpellCorrections;
import java.util.List;
import kotlin.Metadata;

/* JADX INFO: compiled from: SearchResponse.kt */
/* JADX INFO: loaded from: classes4.dex */
@Metadata(d1 = {"\u0000\u0086\u0001\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\b\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u001c\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\f\bf\u0018\u00002\u00020\u0001R\u0018\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003X¦\u0004¢\u0006\u0006\u001a\u0004\b\u0005\u0010\u0006R\u0014\u0010\u0007\u001a\u0004\u0018\u00010\bX¦\u0004¢\u0006\u0006\u001a\u0004\b\t\u0010\nR\u0018\u0010\u000b\u001a\b\u0012\u0004\u0012\u00020\f0\u0003X¦\u0004¢\u0006\u0006\u001a\u0004\b\r\u0010\u0006R\u0014\u0010\u000e\u001a\u0004\u0018\u00010\bX¦\u0004¢\u0006\u0006\u001a\u0004\b\u000f\u0010\nR\u0018\u0010\u0010\u001a\b\u0012\u0004\u0012\u00020\u00110\u0003X¦\u0004¢\u0006\u0006\u001a\u0004\b\u0012\u0010\u0006R\u0014\u0010\u0013\u001a\u0004\u0018\u00010\bX¦\u0004¢\u0006\u0006\u001a\u0004\b\u0014\u0010\nR\u001a\u0010\u0015\u001a\u0004\u0018\u00010\bX¦\u000e¢\u0006\f\u001a\u0004\b\u0016\u0010\n\"\u0004\b\u0017\u0010\u0018R\u001a\u0010\u0019\u001a\u0004\u0018\u00010\u001aX¦\u000e¢\u0006\f\u001a\u0004\b\u001b\u0010\u001c\"\u0004\b\u001d\u0010\u001eR\u0012\u0010\u001f\u001a\u00020 X¦\u0004¢\u0006\u0006\u001a\u0004\b\u001f\u0010!R\u0012\u0010\"\u001a\u00020#X¦\u0004¢\u0006\u0006\u001a\u0004\b$\u0010%R\u0012\u0010&\u001a\u00020#X¦\u0004¢\u0006\u0006\u001a\u0004\b'\u0010%R\u0012\u0010(\u001a\u00020#X¦\u0004¢\u0006\u0006\u001a\u0004\b)\u0010%R\u0012\u0010*\u001a\u00020#X¦\u0004¢\u0006\u0006\u001a\u0004\b+\u0010%R\u0012\u0010,\u001a\u00020#X¦\u0004¢\u0006\u0006\u001a\u0004\b-\u0010%R\u0012\u0010.\u001a\u00020#X¦\u0004¢\u0006\u0006\u001a\u0004\b/\u0010%R\u0012\u00100\u001a\u00020#X¦\u0004¢\u0006\u0006\u001a\u0004\b1\u0010%R\u0012\u00102\u001a\u00020#X¦\u0004¢\u0006\u0006\u001a\u0004\b3\u0010%R\u0012\u00104\u001a\u00020#X¦\u0004¢\u0006\u0006\u001a\u0004\b5\u0010%R\u0012\u00106\u001a\u00020#X¦\u0004¢\u0006\u0006\u001a\u0004\b7\u0010%R\u0018\u00108\u001a\u00020\bX¦\u000e¢\u0006\f\u001a\u0004\b9\u0010\n\"\u0004\b:\u0010\u0018R\u0018\u0010;\u001a\b\u0012\u0004\u0012\u00020\u00110\u0003X¦\u0004¢\u0006\u0006\u001a\u0004\b<\u0010\u0006R\u0014\u0010=\u001a\u0004\u0018\u00010\bX¦\u0004¢\u0006\u0006\u001a\u0004\b>\u0010\nR\u0018\u0010?\u001a\b\u0012\u0004\u0012\u00020@0\u0003X¦\u0004¢\u0006\u0006\u001a\u0004\bA\u0010\u0006R\u0014\u0010B\u001a\u0004\u0018\u00010\bX¦\u0004¢\u0006\u0006\u001a\u0004\bC\u0010\nR\u0018\u0010D\u001a\b\u0012\u0004\u0012\u00020E0\u0003X¦\u0004¢\u0006\u0006\u001a\u0004\bF\u0010\u0006R\u0014\u0010G\u001a\u0004\u0018\u00010\bX¦\u0004¢\u0006\u0006\u001a\u0004\bH\u0010\nR\u001a\u0010I\u001a\n\u0012\u0004\u0012\u00020J\u0018\u00010\u0003X¦\u0004¢\u0006\u0006\u001a\u0004\bK\u0010\u0006R\u001a\u0010L\u001a\u0004\u0018\u00010MX¦\u000e¢\u0006\f\u001a\u0004\bN\u0010O\"\u0004\bP\u0010QR\u0014\u0010R\u001a\u0004\u0018\u00010\bX¦\u0004¢\u0006\u0006\u001a\u0004\bS\u0010\nR\u0018\u0010T\u001a\b\u0012\u0004\u0012\u00020U0\u0003X¦\u0004¢\u0006\u0006\u001a\u0004\bV\u0010\u0006R\u0018\u0010W\u001a\b\u0012\u0004\u0012\u00020X0\u0003X¦\u0004¢\u0006\u0006\u001a\u0004\bY\u0010\u0006R\u0014\u0010Z\u001a\u0004\u0018\u00010\bX¦\u0004¢\u0006\u0006\u001a\u0004\b[\u0010\nR\u0018\u0010\\\u001a\b\u0012\u0004\u0012\u00020]0\u0003X¦\u0004¢\u0006\u0006\u001a\u0004\b^\u0010\u0006R\u0014\u0010_\u001a\u0004\u0018\u00010\bX¦\u0004¢\u0006\u0006\u001a\u0004\b`\u0010\nR\u0018\u0010a\u001a\b\u0012\u0004\u0012\u00020b0\u0003X¦\u0004¢\u0006\u0006\u001a\u0004\bc\u0010\u0006R\u0014\u0010d\u001a\u0004\u0018\u00010\bX¦\u0004¢\u0006\u0006\u001a\u0004\be\u0010\nR\u0018\u0010f\u001a\b\u0012\u0004\u0012\u00020\u00110\u0003X¦\u0004¢\u0006\u0006\u001a\u0004\bg\u0010\u0006R\u0014\u0010h\u001a\u0004\u0018\u00010\bX¦\u0004¢\u0006\u0006\u001a\u0004\bi\u0010\nR\u0018\u0010j\u001a\b\u0012\u0004\u0012\u00020b0\u0003X¦\u0004¢\u0006\u0006\u001a\u0004\bk\u0010\u0006R\u0014\u0010l\u001a\u0004\u0018\u00010\bX¦\u0004¢\u0006\u0006\u001a\u0004\bm\u0010\n¨\u0006n"}, d2 = {"Lcom/amazon/music/find/model/search/SearchResponse;", "", "albums", "", "Lcom/amazon/music/find/model/search/AlbumSearchItem;", "getAlbums", "()Ljava/util/List;", "albumsBlockRef", "", "getAlbumsBlockRef", "()Ljava/lang/String;", "artists", "Lcom/amazon/music/find/model/search/ArtistSearchItem;", "getArtists", "artistsBlockRef", "getArtistsBlockRef", "communityPlaylists", "Lcom/amazon/music/find/model/search/PlaylistSearchItem;", "getCommunityPlaylists", "communityPlaylistsBlockRef", "getCommunityPlaylistsBlockRef", "executedTerm", "getExecutedTerm", "setExecutedTerm", "(Ljava/lang/String;)V", "filterMetadata", "Lcom/amazon/music/find/model/filter/FilterMetadata;", "getFilterMetadata", "()Lcom/amazon/music/find/model/filter/FilterMetadata;", "setFilterMetadata", "(Lcom/amazon/music/find/model/filter/FilterMetadata;)V", "isNothingFound", "", "()Z", "numberOfAlbumsAvailable", "", "getNumberOfAlbumsAvailable", "()I", "numberOfArtistsAvailable", "getNumberOfArtistsAvailable", "numberOfCommunityPlaylistsAvailable", "getNumberOfCommunityPlaylistsAvailable", "numberOfPlaylistsAvailable", "getNumberOfPlaylistsAvailable", "numberOfPodcastEpisodesAvailable", "getNumberOfPodcastEpisodesAvailable", "numberOfPodcastShowsAvailable", "getNumberOfPodcastShowsAvailable", "numberOfStationsAvailable", "getNumberOfStationsAvailable", "numberOfTracksAvailable", "getNumberOfTracksAvailable", "numberOfVideoPlaylistsAvailable", "getNumberOfVideoPlaylistsAvailable", "numberOfVideosAvailable", "getNumberOfVideosAvailable", "originalQuery", "getOriginalQuery", "setOriginalQuery", "playlists", "getPlaylists", "playlistsBlockRef", "getPlaylistsBlockRef", "podcastEpisodes", "Lcom/amazon/music/find/model/search/PodcastEpisodeSearchItem;", "getPodcastEpisodes", "podcastEpisodesBlockRef", "getPodcastEpisodesBlockRef", "podcastShows", "Lcom/amazon/music/find/model/search/PodcastShowSearchItem;", "getPodcastShows", "podcastShowsBlockRef", "getPodcastShowsBlockRef", "sectionOrdering", "Lcom/amazon/music/search/SectionType;", "getSectionOrdering", "spellCorrections", "Lcom/amazon/music/search/SpellCorrections;", "getSpellCorrections", "()Lcom/amazon/music/search/SpellCorrections;", "setSpellCorrections", "(Lcom/amazon/music/search/SpellCorrections;)V", "sportsBlockRef", "getSportsBlockRef", "sportsItems", "Lcom/amazon/music/find/model/search/SportsSearchItem;", "getSportsItems", "stations", "Lcom/amazon/music/find/model/search/StationSearchItem;", "getStations", "stationsBlockRef", "getStationsBlockRef", "topHitItems", "Lcom/amazon/music/find/model/search/SearchItem;", "getTopHitItems", "topHitsBlockRef", "getTopHitsBlockRef", "tracks", "Lcom/amazon/music/find/model/search/TrackSearchItem;", "getTracks", "tracksBlockRef", "getTracksBlockRef", "videoPlaylists", "getVideoPlaylists", "videoPlaylistsBlockRef", "getVideoPlaylistsBlockRef", "videos", "getVideos", "videosBlockRef", "getVideosBlockRef", "DMMFindExperience_release"}, k = 1, mv = {1, 6, 0}, xi = 48)
public interface SearchResponse {
    List<AlbumSearchItem> getAlbums();

    String getAlbumsBlockRef();

    List<ArtistSearchItem> getArtists();

    String getArtistsBlockRef();

    List<PlaylistSearchItem> getCommunityPlaylists();

    String getCommunityPlaylistsBlockRef();

    String getExecutedTerm();

    FilterMetadata getFilterMetadata();

    int getNumberOfAlbumsAvailable();

    int getNumberOfArtistsAvailable();

    int getNumberOfCommunityPlaylistsAvailable();

    int getNumberOfPlaylistsAvailable();

    int getNumberOfPodcastEpisodesAvailable();

    int getNumberOfPodcastShowsAvailable();

    int getNumberOfStationsAvailable();

    int getNumberOfTracksAvailable();

    int getNumberOfVideoPlaylistsAvailable();

    int getNumberOfVideosAvailable();

    String getOriginalQuery();

    List<PlaylistSearchItem> getPlaylists();

    String getPlaylistsBlockRef();

    List<PodcastEpisodeSearchItem> getPodcastEpisodes();

    String getPodcastEpisodesBlockRef();

    List<PodcastShowSearchItem> getPodcastShows();

    String getPodcastShowsBlockRef();

    List<SectionType> getSectionOrdering();

    SpellCorrections getSpellCorrections();

    String getSportsBlockRef();

    List<SportsSearchItem> getSportsItems();

    List<StationSearchItem> getStations();

    String getStationsBlockRef();

    List<SearchItem> getTopHitItems();

    String getTopHitsBlockRef();

    List<TrackSearchItem> getTracks();

    String getTracksBlockRef();

    List<PlaylistSearchItem> getVideoPlaylists();

    String getVideoPlaylistsBlockRef();

    List<TrackSearchItem> getVideos();

    String getVideosBlockRef();

    boolean isNothingFound();

    void setExecutedTerm(String str);

    void setFilterMetadata(FilterMetadata filterMetadata);

    void setOriginalQuery(String str);

    void setSpellCorrections(SpellCorrections spellCorrections);
}
