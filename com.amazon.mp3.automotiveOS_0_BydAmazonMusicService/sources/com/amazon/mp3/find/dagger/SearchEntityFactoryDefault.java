package com.amazon.mp3.find.dagger;

import com.amazon.mp3.prime.browse.metadata.PrimeAlbum;
import com.amazon.mp3.prime.browse.metadata.PrimeArtist;
import com.amazon.mp3.prime.browse.metadata.PrimePlaylist;
import com.amazon.mp3.prime.browse.metadata.PrimeTrack;
import com.amazon.mp3.search.model.PodcastEpisode;
import com.amazon.mp3.search.model.PodcastShow;
import com.amazon.mp3.search.model.SearchSoccer;
import com.amazon.mp3.search.model.Station;
import com.amazon.music.find.model.search.AlbumSearchItem;
import com.amazon.music.find.model.search.ArtistSearchItem;
import com.amazon.music.find.model.search.PlaylistSearchItem;
import com.amazon.music.find.model.search.PodcastEpisodeSearchItem;
import com.amazon.music.find.model.search.PodcastShowSearchItem;
import com.amazon.music.find.model.search.SportsSearchItem;
import com.amazon.music.find.model.search.StationSearchItem;
import com.amazon.music.find.model.search.TrackSearchItem;
import com.amazon.music.find.providers.SearchEntityFactory;
import kotlin.Metadata;

/* JADX INFO: compiled from: SearchEntityFactoryDefault.kt */
/* JADX INFO: loaded from: classes3.dex */
@Metadata(d1 = {"\u0000>\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\u0018\u00002\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0002J\b\u0010\u0003\u001a\u00020\u0004H\u0016J\b\u0010\u0005\u001a\u00020\u0006H\u0016J\b\u0010\u0007\u001a\u00020\bH\u0016J\b\u0010\t\u001a\u00020\nH\u0016J\b\u0010\u000b\u001a\u00020\fH\u0016J\b\u0010\r\u001a\u00020\u000eH\u0016J\b\u0010\u000f\u001a\u00020\u0010H\u0016J\b\u0010\u0011\u001a\u00020\u0012H\u0016J\b\u0010\u0013\u001a\u00020\bH\u0016J\b\u0010\u0014\u001a\u00020\u0012H\u0016¨\u0006\u0015"}, d2 = {"Lcom/amazon/mp3/find/dagger/SearchEntityFactoryDefault;", "Lcom/amazon/music/find/providers/SearchEntityFactory;", "()V", "createAlbumSearchItem", "Lcom/amazon/music/find/model/search/AlbumSearchItem;", "createArtistSearchItem", "Lcom/amazon/music/find/model/search/ArtistSearchItem;", "createPlaylistSearchItem", "Lcom/amazon/music/find/model/search/PlaylistSearchItem;", "createPodcastEpisodeSearchItem", "Lcom/amazon/music/find/model/search/PodcastEpisodeSearchItem;", "createPodcastShowSearchItem", "Lcom/amazon/music/find/model/search/PodcastShowSearchItem;", "createSoccerSearchItem", "Lcom/amazon/music/find/model/search/SportsSearchItem;", "createStationSearchItem", "Lcom/amazon/music/find/model/search/StationSearchItem;", "createTrackSearchItem", "Lcom/amazon/music/find/model/search/TrackSearchItem;", "createVideoPlaylistSearchItem", "createVideoSearchItem", "DigitalMusicAndroid3P_automotiveProdRelease"}, k = 1, mv = {1, 6, 0}, xi = 48)
public final class SearchEntityFactoryDefault implements SearchEntityFactory {
    @Override // com.amazon.music.find.providers.SearchEntityFactory
    public AlbumSearchItem createAlbumSearchItem() {
        return new PrimeAlbum();
    }

    @Override // com.amazon.music.find.providers.SearchEntityFactory
    public ArtistSearchItem createArtistSearchItem() {
        return new PrimeArtist();
    }

    @Override // com.amazon.music.find.providers.SearchEntityFactory
    public PlaylistSearchItem createPlaylistSearchItem() {
        return new PrimePlaylist();
    }

    @Override // com.amazon.music.find.providers.SearchEntityFactory
    public PodcastEpisodeSearchItem createPodcastEpisodeSearchItem() {
        return new PodcastEpisode();
    }

    @Override // com.amazon.music.find.providers.SearchEntityFactory
    public PodcastShowSearchItem createPodcastShowSearchItem() {
        return new PodcastShow();
    }

    @Override // com.amazon.music.find.providers.SearchEntityFactory
    public SportsSearchItem createSoccerSearchItem() {
        return new SearchSoccer();
    }

    @Override // com.amazon.music.find.providers.SearchEntityFactory
    public StationSearchItem createStationSearchItem() {
        return new Station();
    }

    @Override // com.amazon.music.find.providers.SearchEntityFactory
    public TrackSearchItem createTrackSearchItem() {
        return new PrimeTrack();
    }

    @Override // com.amazon.music.find.providers.SearchEntityFactory
    public TrackSearchItem createVideoSearchItem() {
        return new PrimeTrack();
    }

    @Override // com.amazon.music.find.providers.SearchEntityFactory
    public PlaylistSearchItem createVideoPlaylistSearchItem() {
        return new PrimePlaylist();
    }
}
