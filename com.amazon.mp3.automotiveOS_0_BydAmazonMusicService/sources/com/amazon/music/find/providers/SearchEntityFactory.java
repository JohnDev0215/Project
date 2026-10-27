package com.amazon.music.find.providers;

import com.amazon.music.find.model.search.AlbumSearchItem;
import com.amazon.music.find.model.search.ArtistSearchItem;
import com.amazon.music.find.model.search.PlaylistSearchItem;
import com.amazon.music.find.model.search.PodcastEpisodeSearchItem;
import com.amazon.music.find.model.search.PodcastShowSearchItem;
import com.amazon.music.find.model.search.SportsSearchItem;
import com.amazon.music.find.model.search.StationSearchItem;
import com.amazon.music.find.model.search.TrackSearchItem;
import kotlin.Metadata;

/* JADX INFO: compiled from: SearchEntityFactory.kt */
/* JADX INFO: loaded from: classes4.dex */
@Metadata(d1 = {"\u0000<\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\bf\u0018\u00002\u00020\u0001J\b\u0010\u0002\u001a\u00020\u0003H&J\b\u0010\u0004\u001a\u00020\u0005H&J\b\u0010\u0006\u001a\u00020\u0007H&J\b\u0010\b\u001a\u00020\tH&J\b\u0010\n\u001a\u00020\u000bH&J\b\u0010\f\u001a\u00020\rH&J\b\u0010\u000e\u001a\u00020\u000fH&J\b\u0010\u0010\u001a\u00020\u0011H&J\b\u0010\u0012\u001a\u00020\u0007H&J\b\u0010\u0013\u001a\u00020\u0011H&¨\u0006\u0014"}, d2 = {"Lcom/amazon/music/find/providers/SearchEntityFactory;", "", "createAlbumSearchItem", "Lcom/amazon/music/find/model/search/AlbumSearchItem;", "createArtistSearchItem", "Lcom/amazon/music/find/model/search/ArtistSearchItem;", "createPlaylistSearchItem", "Lcom/amazon/music/find/model/search/PlaylistSearchItem;", "createPodcastEpisodeSearchItem", "Lcom/amazon/music/find/model/search/PodcastEpisodeSearchItem;", "createPodcastShowSearchItem", "Lcom/amazon/music/find/model/search/PodcastShowSearchItem;", "createSoccerSearchItem", "Lcom/amazon/music/find/model/search/SportsSearchItem;", "createStationSearchItem", "Lcom/amazon/music/find/model/search/StationSearchItem;", "createTrackSearchItem", "Lcom/amazon/music/find/model/search/TrackSearchItem;", "createVideoPlaylistSearchItem", "createVideoSearchItem", "DMMFindExperience_release"}, k = 1, mv = {1, 6, 0}, xi = 48)
public interface SearchEntityFactory {
    AlbumSearchItem createAlbumSearchItem();

    ArtistSearchItem createArtistSearchItem();

    PlaylistSearchItem createPlaylistSearchItem();

    PodcastEpisodeSearchItem createPodcastEpisodeSearchItem();

    PodcastShowSearchItem createPodcastShowSearchItem();

    SportsSearchItem createSoccerSearchItem();

    StationSearchItem createStationSearchItem();

    TrackSearchItem createTrackSearchItem();

    PlaylistSearchItem createVideoPlaylistSearchItem();

    TrackSearchItem createVideoSearchItem();
}
