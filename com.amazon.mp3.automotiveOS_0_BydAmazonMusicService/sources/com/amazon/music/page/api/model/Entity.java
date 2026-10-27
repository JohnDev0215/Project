package com.amazon.music.page.api.model;

import com.amazon.mp3.environment.Environment;
import com.amazon.music.destination.parser.ParserUtil;
import com.google.gson.annotations.SerializedName;
import java.io.Serializable;

/* JADX INFO: loaded from: classes4.dex */
public class Entity implements Serializable {

    @SerializedName("button")
    private Button button = null;

    @SerializedName("barker")
    private Barker barker = null;

    @SerializedName(Environment.PLAYLIST_PATH)
    private Playlist playlist = null;

    @SerializedName("artist")
    private Artist artist = null;

    @SerializedName("album")
    private Album album = null;

    @SerializedName("station")
    private Station station = null;

    @SerializedName(ParserUtil.GENRE_SEGMENT_NAME)
    private Genre genre = null;

    @SerializedName("track")
    private Track track = null;

    @SerializedName("message")
    private Message message = null;

    @SerializedName("podcastShow")
    private PodcastShow podcastShow = null;

    @SerializedName("podcastEpisode")
    private PodcastEpisode podcastEpisode = null;

    @SerializedName("videoStory")
    private VideoStory videoStory = null;

    public Button getButton() {
        return this.button;
    }

    public Barker getBarker() {
        return this.barker;
    }

    public Playlist getPlaylist() {
        return this.playlist;
    }

    public Artist getArtist() {
        return this.artist;
    }

    public Album getAlbum() {
        return this.album;
    }

    public Station getStation() {
        return this.station;
    }

    public Genre getGenre() {
        return this.genre;
    }

    public Track getTrack() {
        return this.track;
    }

    public Message getMessage() {
        return this.message;
    }

    public PodcastShow getPodcastShow() {
        return this.podcastShow;
    }

    public PodcastEpisode getPodcastEpisode() {
        return this.podcastEpisode;
    }

    public VideoStory getVideoStory() {
        return this.videoStory;
    }
}
