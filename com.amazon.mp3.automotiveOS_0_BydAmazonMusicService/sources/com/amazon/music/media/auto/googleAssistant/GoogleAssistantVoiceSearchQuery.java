package com.amazon.music.media.auto.googleAssistant;

import com.amazon.mp3.environment.Environment;
import com.amazon.music.destination.parser.ParserUtil;
import com.google.android.gms.actions.SearchIntents;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.scheduling.WorkQueueKt;

/* JADX INFO: compiled from: GoogleAssistantVoiceSearchQuery.kt */
/* JADX INFO: loaded from: classes4.dex */
@Metadata(bv = {}, d1 = {"\u0000 \n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0010\u000e\n\u0002\b\b\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\u0014\b\u0086\b\u0018\u00002\u00020\u0001B[\u0012\n\b\u0002\u0010\u0010\u001a\u0004\u0018\u00010\u0002\u0012\n\b\u0002\u0010\u0014\u001a\u0004\u0018\u00010\u0002\u0012\n\b\u0002\u0010\u0016\u001a\u0004\u0018\u00010\u0002\u0012\n\b\u0002\u0010\u0018\u001a\u0004\u0018\u00010\u0002\u0012\n\b\u0002\u0010\u001a\u001a\u0004\u0018\u00010\u0002\u0012\n\b\u0002\u0010\u001c\u001a\u0004\u0018\u00010\u0002\u0012\n\b\u0002\u0010\u001e\u001a\u0004\u0018\u00010\u0002¢\u0006\u0004\b \u0010!J\b\u0010\u0003\u001a\u00020\u0002H\u0016J\u000b\u0010\u0004\u001a\u0004\u0018\u00010\u0002HÆ\u0003J\u000b\u0010\u0005\u001a\u0004\u0018\u00010\u0002HÆ\u0003J\u000b\u0010\u0006\u001a\u0004\u0018\u00010\u0002HÆ\u0003J\u000b\u0010\u0007\u001a\u0004\u0018\u00010\u0002HÆ\u0003J\u000b\u0010\b\u001a\u0004\u0018\u00010\u0002HÆ\u0003J\u000b\u0010\t\u001a\u0004\u0018\u00010\u0002HÆ\u0003J\u000b\u0010\n\u001a\u0004\u0018\u00010\u0002HÆ\u0003J\t\u0010\f\u001a\u00020\u000bHÖ\u0001J\u0013\u0010\u000f\u001a\u00020\u000e2\b\u0010\r\u001a\u0004\u0018\u00010\u0001HÖ\u0003R\u0019\u0010\u0010\u001a\u0004\u0018\u00010\u00028\u0006¢\u0006\f\n\u0004\b\u0010\u0010\u0011\u001a\u0004\b\u0012\u0010\u0013R\u0019\u0010\u0014\u001a\u0004\u0018\u00010\u00028\u0006¢\u0006\f\n\u0004\b\u0014\u0010\u0011\u001a\u0004\b\u0015\u0010\u0013R\u0019\u0010\u0016\u001a\u0004\u0018\u00010\u00028\u0006¢\u0006\f\n\u0004\b\u0016\u0010\u0011\u001a\u0004\b\u0017\u0010\u0013R\u0019\u0010\u0018\u001a\u0004\u0018\u00010\u00028\u0006¢\u0006\f\n\u0004\b\u0018\u0010\u0011\u001a\u0004\b\u0019\u0010\u0013R\u0019\u0010\u001a\u001a\u0004\u0018\u00010\u00028\u0006¢\u0006\f\n\u0004\b\u001a\u0010\u0011\u001a\u0004\b\u001b\u0010\u0013R\u0019\u0010\u001c\u001a\u0004\u0018\u00010\u00028\u0006¢\u0006\f\n\u0004\b\u001c\u0010\u0011\u001a\u0004\b\u001d\u0010\u0013R\u0019\u0010\u001e\u001a\u0004\u0018\u00010\u00028\u0006¢\u0006\f\n\u0004\b\u001e\u0010\u0011\u001a\u0004\b\u001f\u0010\u0013¨\u0006\""}, d2 = {"Lcom/amazon/music/media/auto/googleAssistant/GoogleAssistantVoiceSearchQuery;", "", "", "toString", "component1", "component2", "component3", "component4", "component5", "component6", "component7", "", "hashCode", "other", "", "equals", SearchIntents.EXTRA_QUERY, "Ljava/lang/String;", "getQuery", "()Ljava/lang/String;", "album", "getAlbum", "artist", "getArtist", ParserUtil.GENRE_SEGMENT_NAME, "getGenre", Environment.PLAYLIST_PATH, "getPlaylist", "title", "getTitle", "focus", "getFocus", "<init>", "(Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;)V", "DMMAutoMediaBrowser_release"}, k = 1, mv = {1, 6, 0})
public final /* data */ class GoogleAssistantVoiceSearchQuery {
    private final String album;
    private final String artist;
    private final String focus;
    private final String genre;
    private final String playlist;
    private final String query;
    private final String title;

    public GoogleAssistantVoiceSearchQuery() {
        this(null, null, null, null, null, null, null, WorkQueueKt.MASK, null);
    }

    /* JADX INFO: renamed from: component1, reason: from getter */
    public final String getQuery() {
        return this.query;
    }

    /* JADX INFO: renamed from: component2, reason: from getter */
    public final String getAlbum() {
        return this.album;
    }

    /* JADX INFO: renamed from: component3, reason: from getter */
    public final String getArtist() {
        return this.artist;
    }

    /* JADX INFO: renamed from: component4, reason: from getter */
    public final String getGenre() {
        return this.genre;
    }

    /* JADX INFO: renamed from: component5, reason: from getter */
    public final String getPlaylist() {
        return this.playlist;
    }

    /* JADX INFO: renamed from: component6, reason: from getter */
    public final String getTitle() {
        return this.title;
    }

    /* JADX INFO: renamed from: component7, reason: from getter */
    public final String getFocus() {
        return this.focus;
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof GoogleAssistantVoiceSearchQuery)) {
            return false;
        }
        GoogleAssistantVoiceSearchQuery googleAssistantVoiceSearchQuery = (GoogleAssistantVoiceSearchQuery) other;
        return Intrinsics.areEqual(this.query, googleAssistantVoiceSearchQuery.query) && Intrinsics.areEqual(this.album, googleAssistantVoiceSearchQuery.album) && Intrinsics.areEqual(this.artist, googleAssistantVoiceSearchQuery.artist) && Intrinsics.areEqual(this.genre, googleAssistantVoiceSearchQuery.genre) && Intrinsics.areEqual(this.playlist, googleAssistantVoiceSearchQuery.playlist) && Intrinsics.areEqual(this.title, googleAssistantVoiceSearchQuery.title) && Intrinsics.areEqual(this.focus, googleAssistantVoiceSearchQuery.focus);
    }

    public int hashCode() {
        String str = this.query;
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
        int iHashCode6 = (iHashCode5 + (str6 == null ? 0 : str6.hashCode())) * 31;
        String str7 = this.focus;
        return iHashCode6 + (str7 != null ? str7.hashCode() : 0);
    }

    public GoogleAssistantVoiceSearchQuery(String str, String str2, String str3, String str4, String str5, String str6, String str7) {
        this.query = str;
        this.album = str2;
        this.artist = str3;
        this.genre = str4;
        this.playlist = str5;
        this.title = str6;
        this.focus = str7;
    }

    public /* synthetic */ GoogleAssistantVoiceSearchQuery(String str, String str2, String str3, String str4, String str5, String str6, String str7, int i, DefaultConstructorMarker defaultConstructorMarker) {
        this((i & 1) != 0 ? null : str, (i & 2) != 0 ? null : str2, (i & 4) != 0 ? null : str3, (i & 8) != 0 ? null : str4, (i & 16) != 0 ? null : str5, (i & 32) != 0 ? null : str6, (i & 64) != 0 ? null : str7);
    }

    public String toString() {
        return "VoiceSearchQuery is \n query=" + ((Object) this.query) + " \n album=" + ((Object) this.album) + " \n artist=" + ((Object) this.artist) + " \n genre=" + ((Object) this.genre) + " \n playlist=" + ((Object) this.playlist) + " \n title=" + ((Object) this.title) + " \n focus=" + ((Object) this.focus) + " \n";
    }
}
