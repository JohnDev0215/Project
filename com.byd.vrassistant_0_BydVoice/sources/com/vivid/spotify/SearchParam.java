package com.vivid.spotify;

import android.os.Parcel;
import android.os.Parcelable;

/* JADX INFO: loaded from: classes3.dex */
public class SearchParam implements Parcelable {
    public static final Parcelable.Creator<SearchParam> CREATOR = new Parcelable.Creator<SearchParam>() { // from class: com.vivid.spotify.SearchParam.1
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public SearchParam createFromParcel(Parcel parcel) {
            return new SearchParam(parcel);
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public SearchParam[] newArray(int i) {
            return new SearchParam[i];
        }
    };
    public static final String SEARCH_TYPE_ALBUM = "ALBUM";
    public static final String SEARCH_TYPE_ARTIST = "ARTIST";
    public static final String SEARCH_TYPE_EMPTY = "EMPTY";
    public static final String SEARCH_TYPE_GENRE = "GENRE";
    public static final String SEARCH_TYPE_MEDIA = "MEDIA";
    public static final String SEARCH_TYPE_PLAYLIST = "PLAYLIST";
    private String album;
    private String artist;
    private String genre;
    private String playlistName;
    private String searchType;
    private String songName;

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    private SearchParam() {
    }

    private SearchParam(String str, String str2, String str3, String str4, String str5, String str6) {
        this.searchType = str;
        this.songName = str2;
        this.artist = str3;
        this.album = str4;
        this.genre = str5;
        this.playlistName = str6;
    }

    protected SearchParam(Parcel parcel) {
        this.searchType = parcel.readString();
        this.songName = parcel.readString();
        this.artist = parcel.readString();
        this.album = parcel.readString();
        this.genre = parcel.readString();
        this.playlistName = parcel.readString();
    }

    public String getSearchType() {
        return this.searchType;
    }

    public String getSongName() {
        return this.songName;
    }

    public String getArtist() {
        return this.artist;
    }

    public String getAlbum() {
        return this.album;
    }

    public String getGenre() {
        return this.genre;
    }

    public String getPlaylistName() {
        return this.playlistName;
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(this.searchType);
        parcel.writeString(this.songName);
        parcel.writeString(this.artist);
        parcel.writeString(this.album);
        parcel.writeString(this.genre);
        parcel.writeString(this.playlistName);
    }

    public static class Builder {
        private String searchType;
        private String songName = "";
        private String artist = "";
        private String album = "";
        private String genre = "";
        private String playlistName = "";

        private Builder() {
        }

        public static Builder buildEmptyParam() {
            Builder builder = new Builder();
            builder.searchType = SearchParam.SEARCH_TYPE_EMPTY;
            return builder;
        }

        public static Builder buildArtistSearch(String str) {
            Builder builder = new Builder();
            builder.searchType = SearchParam.SEARCH_TYPE_ARTIST;
            builder.artist = str;
            return builder;
        }

        public static Builder buildAlbumSearch(String str) {
            if (str == null) {
                str = "";
            }
            Builder builder = new Builder();
            builder.searchType = "ALBUM";
            builder.album = str;
            return builder;
        }

        public static Builder buildGenreSearch(String str) {
            if (str == null) {
                str = "";
            }
            Builder builder = new Builder();
            builder.searchType = SearchParam.SEARCH_TYPE_GENRE;
            builder.genre = str;
            return builder;
        }

        public static Builder buildPlayListSearch(String str) {
            if (str == null) {
                str = "";
            }
            Builder builder = new Builder();
            builder.searchType = "PLAYLIST";
            builder.playlistName = str;
            return builder;
        }

        public static Builder buildSearch(String str) {
            if (str == null) {
                str = "";
            }
            Builder builder = new Builder();
            builder.searchType = "MEDIA";
            builder.songName = str;
            return builder;
        }

        public Builder setSongName(String str) {
            if (str == null) {
                str = "";
            }
            this.songName = str;
            return this;
        }

        public Builder setArtist(String str) {
            if (str == null) {
                str = "";
            }
            this.artist = str;
            return this;
        }

        public Builder setAlbum(String str) {
            if (str == null) {
                str = "";
            }
            this.album = str;
            return this;
        }

        public Builder setGenre(String str) {
            if (str == null) {
                str = "";
            }
            this.genre = str;
            return this;
        }

        public Builder setPlaylistName(String str) {
            if (str == null) {
                str = "";
            }
            this.playlistName = str;
            return this;
        }

        public SearchParam build() {
            return new SearchParam(this.searchType, this.songName, this.artist, this.album, this.genre, this.playlistName);
        }
    }
}
