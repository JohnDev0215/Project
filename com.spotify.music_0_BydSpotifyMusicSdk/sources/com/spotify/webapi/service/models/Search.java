package com.spotify.webapi.service.models;

import com.spotify.webapi.service.models.views.ViewsBestMatch;
import p.c71;
import p.l6;
import p.z61;

/* JADX INFO: loaded from: classes.dex */
@c71(generateAdapter = true)
@l6
public final class Search {
    public Pager<Album> albums;
    public Pager<Artist> artists;
    public Pager<ViewsBestMatch> bestMatch;
    public Pager<Episode> episodes;
    public Pager<Playlist> playlists;
    public Pager<ShowSimple> shows;
    public Pager<Track> tracks;

    public static final class Type {
        public static final String ALBUM = "album";
        public static final String ARTIST = "artist";
        public static final String EPISODE = "episode";
        public static final Type INSTANCE = new Type();
        public static final String LINK = "link";
        public static final String PLAYLIST = "playlist";
        public static final String SHOW = "show";
        public static final String STATION = "station";
        public static final String TRACK = "track";
        public static final String TYPES_ALL = "album,artist,playlist,track,show,episode";
        public static final String VIEW = "view";

        private Type() {
        }
    }

    @z61(name = "albums")
    public static /* synthetic */ void getAlbums$annotations() {
    }

    @z61(name = "artists")
    public static /* synthetic */ void getArtists$annotations() {
    }

    @z61(name = "best_match")
    public static /* synthetic */ void getBestMatch$annotations() {
    }

    @z61(name = "episodes")
    public static /* synthetic */ void getEpisodes$annotations() {
    }

    @z61(name = "playlists")
    public static /* synthetic */ void getPlaylists$annotations() {
    }

    @z61(name = "shows")
    public static /* synthetic */ void getShows$annotations() {
    }

    @z61(name = "tracks")
    public static /* synthetic */ void getTracks$annotations() {
    }
}
