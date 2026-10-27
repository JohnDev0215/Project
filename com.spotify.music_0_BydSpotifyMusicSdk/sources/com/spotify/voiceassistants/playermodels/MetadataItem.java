package com.spotify.voiceassistants.playermodels;

import java.util.List;
import kotlin.collections.EmptyList;
import kotlin.jvm.internal.DefaultConstructorMarker;
import p.c71;
import p.hf2;
import p.v03;
import p.z61;

/* JADX INFO: loaded from: classes.dex */
public abstract class MetadataItem {
    private final List<Image> artwork;
    private final String displayName;
    private final String uri;

    @c71(generateAdapter = true)
    public static final class Album extends MetadataItem {
        private final String albumName;
        private final String artistName;
        private final List<Image> artwork;
        private final String uri;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public Album(String str, List<Image> list, @z61(name = "album_name") String str2, @z61(name = "artist_name") String str3) {
            super(str, list, str2, null);
            v03.j(str, "uri");
            v03.j(list, "artwork");
            v03.j(str2, "albumName");
            v03.j(str3, "artistName");
            this.uri = str;
            this.artwork = list;
            this.albumName = str2;
            this.artistName = str3;
        }

        /* JADX WARN: Multi-variable type inference failed */
        public static /* synthetic */ Album copy$default(Album album, String str, List list, String str2, String str3, int i, Object obj) {
            if ((i & 1) != 0) {
                str = album.uri;
            }
            if ((i & 2) != 0) {
                list = album.artwork;
            }
            if ((i & 4) != 0) {
                str2 = album.albumName;
            }
            if ((i & 8) != 0) {
                str3 = album.artistName;
            }
            return album.copy(str, list, str2, str3);
        }

        public final String component1() {
            return this.uri;
        }

        public final List<Image> component2() {
            return this.artwork;
        }

        public final String component3() {
            return this.albumName;
        }

        public final String component4() {
            return this.artistName;
        }

        public final Album copy(String str, List<Image> list, @z61(name = "album_name") String str2, @z61(name = "artist_name") String str3) {
            v03.j(str, "uri");
            v03.j(list, "artwork");
            v03.j(str2, "albumName");
            v03.j(str3, "artistName");
            return new Album(str, list, str2, str3);
        }

        public boolean equals(Object obj) {
            if (this == obj) {
                return true;
            }
            if (!(obj instanceof Album)) {
                return false;
            }
            Album album = (Album) obj;
            return v03.b(this.uri, album.uri) && v03.b(this.artwork, album.artwork) && v03.b(this.albumName, album.albumName) && v03.b(this.artistName, album.artistName);
        }

        public final String getAlbumName() {
            return this.albumName;
        }

        public final String getArtistName() {
            return this.artistName;
        }

        @Override // com.spotify.voiceassistants.playermodels.MetadataItem
        public List<Image> getArtwork() {
            return this.artwork;
        }

        @Override // com.spotify.voiceassistants.playermodels.MetadataItem
        public String getSubtitle() {
            return this.artistName;
        }

        @Override // com.spotify.voiceassistants.playermodels.MetadataItem
        public String getUri() {
            return this.uri;
        }

        public int hashCode() {
            return this.artistName.hashCode() + hf2.b(this.albumName, (this.artwork.hashCode() + (this.uri.hashCode() * 31)) * 31, 31);
        }

        public String toString() {
            StringBuilder sb = new StringBuilder("Album(uri=");
            sb.append(this.uri);
            sb.append(", artwork=");
            sb.append(this.artwork);
            sb.append(", albumName=");
            sb.append(this.albumName);
            sb.append(", artistName=");
            return hf2.d(sb, this.artistName, ')');
        }
    }

    @c71(generateAdapter = true)
    public static final class Artist extends MetadataItem {
        private final String artistName;
        private final List<Image> artwork;
        private final String uri;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public Artist(String str, List<Image> list, @z61(name = "artist_name") String str2) {
            super(str, list, str2, null);
            v03.j(str, "uri");
            v03.j(list, "artwork");
            v03.j(str2, "artistName");
            this.uri = str;
            this.artwork = list;
            this.artistName = str2;
        }

        /* JADX WARN: Multi-variable type inference failed */
        public static /* synthetic */ Artist copy$default(Artist artist, String str, List list, String str2, int i, Object obj) {
            if ((i & 1) != 0) {
                str = artist.uri;
            }
            if ((i & 2) != 0) {
                list = artist.artwork;
            }
            if ((i & 4) != 0) {
                str2 = artist.artistName;
            }
            return artist.copy(str, list, str2);
        }

        public final String component1() {
            return this.uri;
        }

        public final List<Image> component2() {
            return this.artwork;
        }

        public final String component3() {
            return this.artistName;
        }

        public final Artist copy(String str, List<Image> list, @z61(name = "artist_name") String str2) {
            v03.j(str, "uri");
            v03.j(list, "artwork");
            v03.j(str2, "artistName");
            return new Artist(str, list, str2);
        }

        public boolean equals(Object obj) {
            if (this == obj) {
                return true;
            }
            if (!(obj instanceof Artist)) {
                return false;
            }
            Artist artist = (Artist) obj;
            return v03.b(this.uri, artist.uri) && v03.b(this.artwork, artist.artwork) && v03.b(this.artistName, artist.artistName);
        }

        public final String getArtistName() {
            return this.artistName;
        }

        @Override // com.spotify.voiceassistants.playermodels.MetadataItem
        public List<Image> getArtwork() {
            return this.artwork;
        }

        @Override // com.spotify.voiceassistants.playermodels.MetadataItem
        public String getUri() {
            return this.uri;
        }

        public int hashCode() {
            return this.artistName.hashCode() + ((this.artwork.hashCode() + (this.uri.hashCode() * 31)) * 31);
        }

        public String toString() {
            StringBuilder sb = new StringBuilder("Artist(uri=");
            sb.append(this.uri);
            sb.append(", artwork=");
            sb.append(this.artwork);
            sb.append(", artistName=");
            return hf2.d(sb, this.artistName, ')');
        }
    }

    @c71(generateAdapter = true)
    public static final class Episode extends MetadataItem {
        private final List<Image> artwork;
        private final String episodeName;
        private final String uri;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public Episode(String str, List<Image> list, @z61(name = "episode_name") String str2) {
            super(str, list, str2, null);
            v03.j(str, "uri");
            v03.j(list, "artwork");
            v03.j(str2, "episodeName");
            this.uri = str;
            this.artwork = list;
            this.episodeName = str2;
        }

        /* JADX WARN: Multi-variable type inference failed */
        public static /* synthetic */ Episode copy$default(Episode episode, String str, List list, String str2, int i, Object obj) {
            if ((i & 1) != 0) {
                str = episode.uri;
            }
            if ((i & 2) != 0) {
                list = episode.artwork;
            }
            if ((i & 4) != 0) {
                str2 = episode.episodeName;
            }
            return episode.copy(str, list, str2);
        }

        public final String component1() {
            return this.uri;
        }

        public final List<Image> component2() {
            return this.artwork;
        }

        public final String component3() {
            return this.episodeName;
        }

        public final Episode copy(String str, List<Image> list, @z61(name = "episode_name") String str2) {
            v03.j(str, "uri");
            v03.j(list, "artwork");
            v03.j(str2, "episodeName");
            return new Episode(str, list, str2);
        }

        public boolean equals(Object obj) {
            if (this == obj) {
                return true;
            }
            if (!(obj instanceof Episode)) {
                return false;
            }
            Episode episode = (Episode) obj;
            return v03.b(this.uri, episode.uri) && v03.b(this.artwork, episode.artwork) && v03.b(this.episodeName, episode.episodeName);
        }

        @Override // com.spotify.voiceassistants.playermodels.MetadataItem
        public List<Image> getArtwork() {
            return this.artwork;
        }

        public final String getEpisodeName() {
            return this.episodeName;
        }

        @Override // com.spotify.voiceassistants.playermodels.MetadataItem
        public String getUri() {
            return this.uri;
        }

        public int hashCode() {
            return this.episodeName.hashCode() + ((this.artwork.hashCode() + (this.uri.hashCode() * 31)) * 31);
        }

        public String toString() {
            StringBuilder sb = new StringBuilder("Episode(uri=");
            sb.append(this.uri);
            sb.append(", artwork=");
            sb.append(this.artwork);
            sb.append(", episodeName=");
            return hf2.d(sb, this.episodeName, ')');
        }
    }

    @c71(generateAdapter = true)
    public static final class Error extends MetadataItem {
        private final String error;

        public Error() {
            this(null, 1, 0 == true ? 1 : 0);
        }

        public static /* synthetic */ Error copy$default(Error error, String str, int i, Object obj) {
            if ((i & 1) != 0) {
                str = error.error;
            }
            return error.copy(str);
        }

        public final String component1() {
            return this.error;
        }

        public final Error copy(String str) {
            return new Error(str);
        }

        public boolean equals(Object obj) {
            if (this == obj) {
                return true;
            }
            return (obj instanceof Error) && v03.b(this.error, ((Error) obj).error);
        }

        public final String getError() {
            return this.error;
        }

        public int hashCode() {
            String str = this.error;
            if (str == null) {
                return 0;
            }
            return str.hashCode();
        }

        public String toString() {
            return hf2.d(new StringBuilder("Error(error="), this.error, ')');
        }

        /* JADX WARN: Illegal instructions before constructor call */
        public Error(String str) {
            String str2 = "";
            super(str2, EmptyList.c, str2, null);
            this.error = str;
        }

        public /* synthetic */ Error(String str, int i, DefaultConstructorMarker defaultConstructorMarker) {
            this((i & 1) != 0 ? null : str);
        }
    }

    @c71(generateAdapter = true)
    public static final class Playlist extends MetadataItem {
        private final List<Image> artwork;
        private final String playlistName;
        private final String uri;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public Playlist(String str, List<Image> list, @z61(name = "playlist_name") String str2) {
            super(str, list, str2, null);
            v03.j(str, "uri");
            v03.j(list, "artwork");
            v03.j(str2, "playlistName");
            this.uri = str;
            this.artwork = list;
            this.playlistName = str2;
        }

        /* JADX WARN: Multi-variable type inference failed */
        public static /* synthetic */ Playlist copy$default(Playlist playlist, String str, List list, String str2, int i, Object obj) {
            if ((i & 1) != 0) {
                str = playlist.uri;
            }
            if ((i & 2) != 0) {
                list = playlist.artwork;
            }
            if ((i & 4) != 0) {
                str2 = playlist.playlistName;
            }
            return playlist.copy(str, list, str2);
        }

        public final String component1() {
            return this.uri;
        }

        public final List<Image> component2() {
            return this.artwork;
        }

        public final String component3() {
            return this.playlistName;
        }

        public final Playlist copy(String str, List<Image> list, @z61(name = "playlist_name") String str2) {
            v03.j(str, "uri");
            v03.j(list, "artwork");
            v03.j(str2, "playlistName");
            return new Playlist(str, list, str2);
        }

        public boolean equals(Object obj) {
            if (this == obj) {
                return true;
            }
            if (!(obj instanceof Playlist)) {
                return false;
            }
            Playlist playlist = (Playlist) obj;
            return v03.b(this.uri, playlist.uri) && v03.b(this.artwork, playlist.artwork) && v03.b(this.playlistName, playlist.playlistName);
        }

        @Override // com.spotify.voiceassistants.playermodels.MetadataItem
        public List<Image> getArtwork() {
            return this.artwork;
        }

        public final String getPlaylistName() {
            return this.playlistName;
        }

        @Override // com.spotify.voiceassistants.playermodels.MetadataItem
        public String getUri() {
            return this.uri;
        }

        public int hashCode() {
            return this.playlistName.hashCode() + ((this.artwork.hashCode() + (this.uri.hashCode() * 31)) * 31);
        }

        public String toString() {
            StringBuilder sb = new StringBuilder("Playlist(uri=");
            sb.append(this.uri);
            sb.append(", artwork=");
            sb.append(this.artwork);
            sb.append(", playlistName=");
            return hf2.d(sb, this.playlistName, ')');
        }
    }

    @c71(generateAdapter = true)
    public static final class Show extends MetadataItem {
        private final List<Image> artwork;
        private final String publisherName;
        private final String showName;
        private final String uri;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public Show(String str, List<Image> list, @z61(name = "show_name") String str2, @z61(name = "publisher_name") String str3) {
            super(str, list, str2, null);
            v03.j(str, "uri");
            v03.j(list, "artwork");
            v03.j(str2, "showName");
            v03.j(str3, "publisherName");
            this.uri = str;
            this.artwork = list;
            this.showName = str2;
            this.publisherName = str3;
        }

        /* JADX WARN: Multi-variable type inference failed */
        public static /* synthetic */ Show copy$default(Show show, String str, List list, String str2, String str3, int i, Object obj) {
            if ((i & 1) != 0) {
                str = show.uri;
            }
            if ((i & 2) != 0) {
                list = show.artwork;
            }
            if ((i & 4) != 0) {
                str2 = show.showName;
            }
            if ((i & 8) != 0) {
                str3 = show.publisherName;
            }
            return show.copy(str, list, str2, str3);
        }

        public final String component1() {
            return this.uri;
        }

        public final List<Image> component2() {
            return this.artwork;
        }

        public final String component3() {
            return this.showName;
        }

        public final String component4() {
            return this.publisherName;
        }

        public final Show copy(String str, List<Image> list, @z61(name = "show_name") String str2, @z61(name = "publisher_name") String str3) {
            v03.j(str, "uri");
            v03.j(list, "artwork");
            v03.j(str2, "showName");
            v03.j(str3, "publisherName");
            return new Show(str, list, str2, str3);
        }

        public boolean equals(Object obj) {
            if (this == obj) {
                return true;
            }
            if (!(obj instanceof Show)) {
                return false;
            }
            Show show = (Show) obj;
            return v03.b(this.uri, show.uri) && v03.b(this.artwork, show.artwork) && v03.b(this.showName, show.showName) && v03.b(this.publisherName, show.publisherName);
        }

        @Override // com.spotify.voiceassistants.playermodels.MetadataItem
        public List<Image> getArtwork() {
            return this.artwork;
        }

        public final String getPublisherName() {
            return this.publisherName;
        }

        public final String getShowName() {
            return this.showName;
        }

        @Override // com.spotify.voiceassistants.playermodels.MetadataItem
        public String getSubtitle() {
            return this.publisherName;
        }

        @Override // com.spotify.voiceassistants.playermodels.MetadataItem
        public String getUri() {
            return this.uri;
        }

        public int hashCode() {
            return this.publisherName.hashCode() + hf2.b(this.showName, (this.artwork.hashCode() + (this.uri.hashCode() * 31)) * 31, 31);
        }

        public String toString() {
            StringBuilder sb = new StringBuilder("Show(uri=");
            sb.append(this.uri);
            sb.append(", artwork=");
            sb.append(this.artwork);
            sb.append(", showName=");
            sb.append(this.showName);
            sb.append(", publisherName=");
            return hf2.d(sb, this.publisherName, ')');
        }
    }

    @c71(generateAdapter = true)
    public static final class Track extends MetadataItem {
        private final String artistName;
        private final List<Image> artwork;
        private final Boolean explicit;
        private final String trackName;
        private final String uri;

        public /* synthetic */ Track(String str, List list, String str2, Boolean bool, String str3, int i, DefaultConstructorMarker defaultConstructorMarker) {
            this(str, list, str2, (i & 8) != 0 ? null : bool, (i & 16) != 0 ? null : str3);
        }

        /* JADX WARN: Multi-variable type inference failed */
        public static /* synthetic */ Track copy$default(Track track, String str, List list, String str2, Boolean bool, String str3, int i, Object obj) {
            if ((i & 1) != 0) {
                str = track.uri;
            }
            if ((i & 2) != 0) {
                list = track.artwork;
            }
            List list2 = list;
            if ((i & 4) != 0) {
                str2 = track.trackName;
            }
            String str4 = str2;
            if ((i & 8) != 0) {
                bool = track.explicit;
            }
            Boolean bool2 = bool;
            if ((i & 16) != 0) {
                str3 = track.artistName;
            }
            return track.copy(str, list2, str4, bool2, str3);
        }

        public final String component1() {
            return this.uri;
        }

        public final List<Image> component2() {
            return this.artwork;
        }

        public final String component3() {
            return this.trackName;
        }

        public final Boolean component4() {
            return this.explicit;
        }

        public final String component5() {
            return this.artistName;
        }

        public final Track copy(String str, List<Image> list, @z61(name = "track_name") String str2, @z61(name = "explicit") Boolean bool, @z61(name = "artist_name") String str3) {
            v03.j(str, "uri");
            v03.j(list, "artwork");
            v03.j(str2, "trackName");
            return new Track(str, list, str2, bool, str3);
        }

        public boolean equals(Object obj) {
            if (this == obj) {
                return true;
            }
            if (!(obj instanceof Track)) {
                return false;
            }
            Track track = (Track) obj;
            return v03.b(this.uri, track.uri) && v03.b(this.artwork, track.artwork) && v03.b(this.trackName, track.trackName) && v03.b(this.explicit, track.explicit) && v03.b(this.artistName, track.artistName);
        }

        public final String getArtistName() {
            return this.artistName;
        }

        @Override // com.spotify.voiceassistants.playermodels.MetadataItem
        public List<Image> getArtwork() {
            return this.artwork;
        }

        public final Boolean getExplicit() {
            return this.explicit;
        }

        @Override // com.spotify.voiceassistants.playermodels.MetadataItem
        public String getSubtitle() {
            return this.artistName;
        }

        public final String getTrackName() {
            return this.trackName;
        }

        @Override // com.spotify.voiceassistants.playermodels.MetadataItem
        public String getUri() {
            return this.uri;
        }

        public int hashCode() {
            int iB = hf2.b(this.trackName, (this.artwork.hashCode() + (this.uri.hashCode() * 31)) * 31, 31);
            Boolean bool = this.explicit;
            int iHashCode = (iB + (bool == null ? 0 : bool.hashCode())) * 31;
            String str = this.artistName;
            return iHashCode + (str != null ? str.hashCode() : 0);
        }

        public String toString() {
            StringBuilder sb = new StringBuilder("Track(uri=");
            sb.append(this.uri);
            sb.append(", artwork=");
            sb.append(this.artwork);
            sb.append(", trackName=");
            sb.append(this.trackName);
            sb.append(", explicit=");
            sb.append(this.explicit);
            sb.append(", artistName=");
            return hf2.d(sb, this.artistName, ')');
        }

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public Track(String str, List<Image> list, @z61(name = "track_name") String str2, @z61(name = "explicit") Boolean bool, @z61(name = "artist_name") String str3) {
            super(str, list, str2, null);
            v03.j(str, "uri");
            v03.j(list, "artwork");
            v03.j(str2, "trackName");
            this.uri = str;
            this.artwork = list;
            this.trackName = str2;
            this.explicit = bool;
            this.artistName = str3;
        }
    }

    public /* synthetic */ MetadataItem(String str, List list, String str2, DefaultConstructorMarker defaultConstructorMarker) {
        this(str, list, str2);
    }

    public List<Image> getArtwork() {
        return this.artwork;
    }

    public final String getDisplayName() {
        return this.displayName;
    }

    public String getSubtitle() {
        return null;
    }

    public String getUri() {
        return this.uri;
    }

    private MetadataItem(String str, List<Image> list, String str2) {
        this.uri = str;
        this.artwork = list;
        this.displayName = str2;
    }
}
