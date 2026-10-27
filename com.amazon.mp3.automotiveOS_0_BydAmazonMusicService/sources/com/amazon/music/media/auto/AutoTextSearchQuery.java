package com.amazon.music.media.auto;

import com.amazon.mp3.environment.Environment;
import com.amazon.music.destination.parser.ParserUtil;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: AutoTextSearchQuery.kt */
/* JADX INFO: loaded from: classes4.dex */
@Metadata(bv = {}, d1 = {"\u0000\u001e\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\u0012\b\u0086\b\u0018\u00002\u00020\u0001BM\u0012\b\u0010\t\u001a\u0004\u0018\u00010\u0002\u0012\n\b\u0002\u0010\r\u001a\u0004\u0018\u00010\u0002\u0012\n\b\u0002\u0010\u000f\u001a\u0004\u0018\u00010\u0002\u0012\n\b\u0002\u0010\u0011\u001a\u0004\u0018\u00010\u0002\u0012\n\b\u0002\u0010\u0013\u001a\u0004\u0018\u00010\u0002\u0012\n\b\u0002\u0010\u0015\u001a\u0004\u0018\u00010\u0002¢\u0006\u0004\b\u0017\u0010\u0018J\t\u0010\u0003\u001a\u00020\u0002HÖ\u0001J\t\u0010\u0005\u001a\u00020\u0004HÖ\u0001J\u0013\u0010\b\u001a\u00020\u00072\b\u0010\u0006\u001a\u0004\u0018\u00010\u0001HÖ\u0003R\u0019\u0010\t\u001a\u0004\u0018\u00010\u00028\u0006¢\u0006\f\n\u0004\b\t\u0010\n\u001a\u0004\b\u000b\u0010\fR\u0019\u0010\r\u001a\u0004\u0018\u00010\u00028\u0006¢\u0006\f\n\u0004\b\r\u0010\n\u001a\u0004\b\u000e\u0010\fR\u0019\u0010\u000f\u001a\u0004\u0018\u00010\u00028\u0006¢\u0006\f\n\u0004\b\u000f\u0010\n\u001a\u0004\b\u0010\u0010\fR\u0019\u0010\u0011\u001a\u0004\u0018\u00010\u00028\u0006¢\u0006\f\n\u0004\b\u0011\u0010\n\u001a\u0004\b\u0012\u0010\fR\u0019\u0010\u0013\u001a\u0004\u0018\u00010\u00028\u0006¢\u0006\f\n\u0004\b\u0013\u0010\n\u001a\u0004\b\u0014\u0010\fR\u0019\u0010\u0015\u001a\u0004\u0018\u00010\u00028\u0006¢\u0006\f\n\u0004\b\u0015\u0010\n\u001a\u0004\b\u0016\u0010\f¨\u0006\u0019"}, d2 = {"Lcom/amazon/music/media/auto/AutoTextSearchQuery;", "", "", "toString", "", "hashCode", "other", "", "equals", "searchTerm", "Ljava/lang/String;", "getSearchTerm", "()Ljava/lang/String;", "album", "getAlbum", "artist", "getArtist", ParserUtil.GENRE_SEGMENT_NAME, "getGenre", Environment.PLAYLIST_PATH, "getPlaylist", "title", "getTitle", "<init>", "(Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;)V", "DMMAutoMediaBrowser_release"}, k = 1, mv = {1, 6, 0})
public final /* data */ class AutoTextSearchQuery {
    private final String album;
    private final String artist;
    private final String genre;
    private final String playlist;
    private final String searchTerm;
    private final String title;

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof AutoTextSearchQuery)) {
            return false;
        }
        AutoTextSearchQuery autoTextSearchQuery = (AutoTextSearchQuery) other;
        return Intrinsics.areEqual(this.searchTerm, autoTextSearchQuery.searchTerm) && Intrinsics.areEqual(this.album, autoTextSearchQuery.album) && Intrinsics.areEqual(this.artist, autoTextSearchQuery.artist) && Intrinsics.areEqual(this.genre, autoTextSearchQuery.genre) && Intrinsics.areEqual(this.playlist, autoTextSearchQuery.playlist) && Intrinsics.areEqual(this.title, autoTextSearchQuery.title);
    }

    public int hashCode() {
        String str = this.searchTerm;
        int iHashCode = (str == null ? 0 : str.hashCode()) * 31;
        String str2 = this.album;
        int iHashCode2 = (iHashCode + (str2 == null ? 0 : str2.hashCode())) * 31;
        String str3 = this.artist;
        int iHashCode3 = (iHashCode2 + (str3 == null ? 0 : str3.hashCode())) * 31;
        String str4 = this.genre;
        int iHashCode4 = (iHashCode3 + (str4 == null ? 0 : str4.hashCode())) * 31;
        String str5 = this.playlist;
        int iHashCode5 = (iHashCode4 + (str5 == null ? 0 : str5.hashCode())) * 31;
        String str6 = this.title;
        return iHashCode5 + (str6 != null ? str6.hashCode() : 0);
    }

    public String toString() {
        return "AutoTextSearchQuery(searchTerm=" + ((Object) this.searchTerm) + ", album=" + ((Object) this.album) + ", artist=" + ((Object) this.artist) + ", genre=" + ((Object) this.genre) + ", playlist=" + ((Object) this.playlist) + ", title=" + ((Object) this.title) + ')';
    }

    public AutoTextSearchQuery(String str, String str2, String str3, String str4, String str5, String str6) {
        this.searchTerm = str;
        this.album = str2;
        this.artist = str3;
        this.genre = str4;
        this.playlist = str5;
        this.title = str6;
    }

    public final String getSearchTerm() {
        return this.searchTerm;
    }
}
