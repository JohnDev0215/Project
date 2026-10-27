package com.amazon.music.voice.googleAssistant;

import android.os.Bundle;
import com.amazon.mp3.environment.Environment;
import com.amazon.music.destination.parser.ParserUtil;
import com.amazon.music.find.utils.ContentUtils;
import com.amazon.music.platform.graphql.mediagateway.generated.type.GoogleNLUInput;
import com.apollographql.apollo3.api.Optional;
import com.google.android.gms.actions.SearchIntents;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Reflection;
import kotlinx.coroutines.scheduling.WorkQueueKt;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/* JADX INFO: compiled from: GoogleAssistantVoiceSearchQuery.kt */
/* JADX INFO: loaded from: classes4.dex */
@Metadata(bv = {}, d1 = {"\u0000,\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\u001b\n\u0002\u0018\u0002\n\u0002\b\u0005\b\u0086\b\u0018\u0000 )2\u00020\u0001:\u0001)B[\u0012\n\b\u0002\u0010\u000b\u001a\u0004\u0018\u00010\u0002\u0012\n\b\u0002\u0010\u0011\u001a\u0004\u0018\u00010\u0002\u0012\n\b\u0002\u0010\u0014\u001a\u0004\u0018\u00010\u0002\u0012\n\b\u0002\u0010\u0017\u001a\u0004\u0018\u00010\u0002\u0012\n\b\u0002\u0010\u001a\u001a\u0004\u0018\u00010\u0002\u0012\n\b\u0002\u0010\u001d\u001a\u0004\u0018\u00010\u0002\u0012\n\b\u0002\u0010 \u001a\u0004\u0018\u00010\u0002¢\u0006\u0004\b#\u0010$B\u001d\b\u0016\u0012\b\u0010&\u001a\u0004\u0018\u00010%\u0012\b\u0010'\u001a\u0004\u0018\u00010\u0002¢\u0006\u0004\b#\u0010(J\b\u0010\u0003\u001a\u00020\u0002H\u0016J\u0006\u0010\u0005\u001a\u00020\u0004J\t\u0010\u0007\u001a\u00020\u0006HÖ\u0001J\u0013\u0010\n\u001a\u00020\t2\b\u0010\b\u001a\u0004\u0018\u00010\u0001HÖ\u0003R$\u0010\u000b\u001a\u0004\u0018\u00010\u00028\u0006@\u0006X\u0086\u000e¢\u0006\u0012\n\u0004\b\u000b\u0010\f\u001a\u0004\b\r\u0010\u000e\"\u0004\b\u000f\u0010\u0010R$\u0010\u0011\u001a\u0004\u0018\u00010\u00028\u0006@\u0006X\u0086\u000e¢\u0006\u0012\n\u0004\b\u0011\u0010\f\u001a\u0004\b\u0012\u0010\u000e\"\u0004\b\u0013\u0010\u0010R$\u0010\u0014\u001a\u0004\u0018\u00010\u00028\u0006@\u0006X\u0086\u000e¢\u0006\u0012\n\u0004\b\u0014\u0010\f\u001a\u0004\b\u0015\u0010\u000e\"\u0004\b\u0016\u0010\u0010R$\u0010\u0017\u001a\u0004\u0018\u00010\u00028\u0006@\u0006X\u0086\u000e¢\u0006\u0012\n\u0004\b\u0017\u0010\f\u001a\u0004\b\u0018\u0010\u000e\"\u0004\b\u0019\u0010\u0010R$\u0010\u001a\u001a\u0004\u0018\u00010\u00028\u0006@\u0006X\u0086\u000e¢\u0006\u0012\n\u0004\b\u001a\u0010\f\u001a\u0004\b\u001b\u0010\u000e\"\u0004\b\u001c\u0010\u0010R$\u0010\u001d\u001a\u0004\u0018\u00010\u00028\u0006@\u0006X\u0086\u000e¢\u0006\u0012\n\u0004\b\u001d\u0010\f\u001a\u0004\b\u001e\u0010\u000e\"\u0004\b\u001f\u0010\u0010R$\u0010 \u001a\u0004\u0018\u00010\u00028\u0006@\u0006X\u0086\u000e¢\u0006\u0012\n\u0004\b \u0010\f\u001a\u0004\b!\u0010\u000e\"\u0004\b\"\u0010\u0010¨\u0006*"}, d2 = {"Lcom/amazon/music/voice/googleAssistant/GoogleAssistantVoiceSearchQuery;", "", "", "toString", "Lcom/amazon/music/platform/graphql/mediagateway/generated/type/GoogleNLUInput;", "convertToGoogleNLUInput", "", "hashCode", "other", "", "equals", SearchIntents.EXTRA_QUERY, "Ljava/lang/String;", "getQuery", "()Ljava/lang/String;", "setQuery", "(Ljava/lang/String;)V", "album", "getAlbum", "setAlbum", "artist", "getArtist", "setArtist", ParserUtil.GENRE_SEGMENT_NAME, "getGenre", "setGenre", Environment.PLAYLIST_PATH, "getPlaylist", "setPlaylist", "title", "getTitle", "setTitle", "focus", "getFocus", "setFocus", "<init>", "(Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;)V", "Landroid/os/Bundle;", "bundle", ContentUtils.KEY_SEARCH_QUERY, "(Landroid/os/Bundle;Ljava/lang/String;)V", "Companion", "DMMVoice_release"}, k = 1, mv = {1, 6, 0})
public final /* data */ class GoogleAssistantVoiceSearchQuery {
    private String album;
    private String artist;
    private String focus;
    private String genre;
    private String playlist;
    private String query;
    private String title;
    private static final String TAG = GoogleAssistantVoiceSearchQuery.class.getSimpleName();
    private static final Logger LOG = LoggerFactory.getLogger(Reflection.getOrCreateKotlinClass(GoogleAssistantVoiceSearchQuery.class).getSimpleName());

    public GoogleAssistantVoiceSearchQuery() {
        this(null, null, null, null, null, null, null, WorkQueueKt.MASK, null);
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

    public final String getQuery() {
        return this.query;
    }

    public final String getAlbum() {
        return this.album;
    }

    public final void setAlbum(String str) {
        this.album = str;
    }

    public final String getArtist() {
        return this.artist;
    }

    public final void setArtist(String str) {
        this.artist = str;
    }

    public final String getGenre() {
        return this.genre;
    }

    public final void setGenre(String str) {
        this.genre = str;
    }

    public final String getPlaylist() {
        return this.playlist;
    }

    public final void setPlaylist(String str) {
        this.playlist = str;
    }

    public final String getTitle() {
        return this.title;
    }

    public final void setTitle(String str) {
        this.title = str;
    }

    public final String getFocus() {
        return this.focus;
    }

    public final void setFocus(String str) {
        this.focus = str;
    }

    public String toString() {
        return "VoiceSearchQuery is \n query=" + ((Object) this.query) + " \n album=" + ((Object) this.album) + " \n artist=" + ((Object) this.artist) + " \n genre=" + ((Object) this.genre) + " \n playlist=" + ((Object) this.playlist) + " \n title=" + ((Object) this.title) + " \n focus=" + ((Object) this.focus) + " \n";
    }

    public final GoogleNLUInput convertToGoogleNLUInput() {
        String str = this.query;
        if (str == null) {
            str = "";
        }
        String str2 = str;
        Optional.Companion companion = Optional.INSTANCE;
        return new GoogleNLUInput(str2, companion.presentIfNotNull(this.focus), companion.presentIfNotNull(this.title), companion.presentIfNotNull(this.album), companion.presentIfNotNull(this.artist), companion.presentIfNotNull(this.genre), companion.presentIfNotNull(this.playlist), Optional.Absent.INSTANCE);
    }

    public GoogleAssistantVoiceSearchQuery(Bundle bundle, String str) {
        this(null, null, null, null, null, null, null, WorkQueueKt.MASK, null);
        if (bundle != null) {
            setAlbum(bundle.getString("android.intent.extra.album"));
            setArtist(bundle.getString("android.intent.extra.artist"));
            setGenre(bundle.getString("android.intent.extra.genre"));
            setPlaylist(bundle.getString("android.intent.extra.playlist"));
            setTitle(bundle.getString("android.intent.extra.title"));
            setFocus(bundle.getString("android.intent.extra.focus"));
        }
        this.query = str;
        LOG.debug(TAG, toString());
    }
}
