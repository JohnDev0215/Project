package com.amazon.music.platform.graphql.mediagateway.generated.type;

import com.apollographql.apollo3.api.Optional;
import com.google.android.gms.actions.SearchIntents;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: GoogleNLUInput.kt */
/* JADX INFO: loaded from: classes4.dex */
@Metadata(bv = {}, d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0013\b\u0086\b\u0018\u00002\u00020\u0001B\u008d\u0001\u0012\u0006\u0010\t\u001a\u00020\u0002\u0012\u0010\b\u0002\u0010\u000e\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\u00020\r\u0012\u0010\b\u0002\u0010\u0012\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\u00020\r\u0012\u0010\b\u0002\u0010\u0014\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\u00020\r\u0012\u0010\b\u0002\u0010\u0016\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\u00020\r\u0012\u0010\b\u0002\u0010\u0018\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\u00020\r\u0012\u0010\b\u0002\u0010\u001a\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\u00020\r\u0012\u0010\b\u0002\u0010\u001c\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\u00020\r¢\u0006\u0004\b\u001e\u0010\u001fJ\t\u0010\u0003\u001a\u00020\u0002HÖ\u0001J\t\u0010\u0005\u001a\u00020\u0004HÖ\u0001J\u0013\u0010\b\u001a\u00020\u00072\b\u0010\u0006\u001a\u0004\u0018\u00010\u0001HÖ\u0003R\u0017\u0010\t\u001a\u00020\u00028\u0006¢\u0006\f\n\u0004\b\t\u0010\n\u001a\u0004\b\u000b\u0010\fR\u001f\u0010\u000e\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\u00020\r8\u0006¢\u0006\f\n\u0004\b\u000e\u0010\u000f\u001a\u0004\b\u0010\u0010\u0011R\u001f\u0010\u0012\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\u00020\r8\u0006¢\u0006\f\n\u0004\b\u0012\u0010\u000f\u001a\u0004\b\u0013\u0010\u0011R\u001f\u0010\u0014\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\u00020\r8\u0006¢\u0006\f\n\u0004\b\u0014\u0010\u000f\u001a\u0004\b\u0015\u0010\u0011R\u001f\u0010\u0016\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\u00020\r8\u0006¢\u0006\f\n\u0004\b\u0016\u0010\u000f\u001a\u0004\b\u0017\u0010\u0011R\u001f\u0010\u0018\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\u00020\r8\u0006¢\u0006\f\n\u0004\b\u0018\u0010\u000f\u001a\u0004\b\u0019\u0010\u0011R\u001f\u0010\u001a\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\u00020\r8\u0006¢\u0006\f\n\u0004\b\u001a\u0010\u000f\u001a\u0004\b\u001b\u0010\u0011R\u001f\u0010\u001c\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\u00020\r8\u0006¢\u0006\f\n\u0004\b\u001c\u0010\u000f\u001a\u0004\b\u001d\u0010\u0011¨\u0006 "}, d2 = {"Lcom/amazon/music/platform/graphql/mediagateway/generated/type/GoogleNLUInput;", "", "", "toString", "", "hashCode", "other", "", "equals", SearchIntents.EXTRA_QUERY, "Ljava/lang/String;", "getQuery", "()Ljava/lang/String;", "Lcom/apollographql/apollo3/api/Optional;", "mediaFocus", "Lcom/apollographql/apollo3/api/Optional;", "getMediaFocus", "()Lcom/apollographql/apollo3/api/Optional;", "mediaTitle", "getMediaTitle", "mediaAlbum", "getMediaAlbum", "mediaArtist", "getMediaArtist", "mediaGenre", "getMediaGenre", "mediaPlaylist", "getMediaPlaylist", "mediaRadioChannel", "getMediaRadioChannel", "<init>", "(Ljava/lang/String;Lcom/apollographql/apollo3/api/Optional;Lcom/apollographql/apollo3/api/Optional;Lcom/apollographql/apollo3/api/Optional;Lcom/apollographql/apollo3/api/Optional;Lcom/apollographql/apollo3/api/Optional;Lcom/apollographql/apollo3/api/Optional;Lcom/apollographql/apollo3/api/Optional;)V", "MusicPlatformCore_release"}, k = 1, mv = {1, 7, 1})
public final /* data */ class GoogleNLUInput {
    private final Optional<String> mediaAlbum;
    private final Optional<String> mediaArtist;
    private final Optional<String> mediaFocus;
    private final Optional<String> mediaGenre;
    private final Optional<String> mediaPlaylist;
    private final Optional<String> mediaRadioChannel;
    private final Optional<String> mediaTitle;
    private final String query;

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof GoogleNLUInput)) {
            return false;
        }
        GoogleNLUInput googleNLUInput = (GoogleNLUInput) other;
        return Intrinsics.areEqual(this.query, googleNLUInput.query) && Intrinsics.areEqual(this.mediaFocus, googleNLUInput.mediaFocus) && Intrinsics.areEqual(this.mediaTitle, googleNLUInput.mediaTitle) && Intrinsics.areEqual(this.mediaAlbum, googleNLUInput.mediaAlbum) && Intrinsics.areEqual(this.mediaArtist, googleNLUInput.mediaArtist) && Intrinsics.areEqual(this.mediaGenre, googleNLUInput.mediaGenre) && Intrinsics.areEqual(this.mediaPlaylist, googleNLUInput.mediaPlaylist) && Intrinsics.areEqual(this.mediaRadioChannel, googleNLUInput.mediaRadioChannel);
    }

    public int hashCode() {
        return (((((((((((((this.query.hashCode() * 31) + this.mediaFocus.hashCode()) * 31) + this.mediaTitle.hashCode()) * 31) + this.mediaAlbum.hashCode()) * 31) + this.mediaArtist.hashCode()) * 31) + this.mediaGenre.hashCode()) * 31) + this.mediaPlaylist.hashCode()) * 31) + this.mediaRadioChannel.hashCode();
    }

    public String toString() {
        return "GoogleNLUInput(query=" + this.query + ", mediaFocus=" + this.mediaFocus + ", mediaTitle=" + this.mediaTitle + ", mediaAlbum=" + this.mediaAlbum + ", mediaArtist=" + this.mediaArtist + ", mediaGenre=" + this.mediaGenre + ", mediaPlaylist=" + this.mediaPlaylist + ", mediaRadioChannel=" + this.mediaRadioChannel + ')';
    }

    public GoogleNLUInput(String query, Optional<String> mediaFocus, Optional<String> mediaTitle, Optional<String> mediaAlbum, Optional<String> mediaArtist, Optional<String> mediaGenre, Optional<String> mediaPlaylist, Optional<String> mediaRadioChannel) {
        Intrinsics.checkNotNullParameter(query, "query");
        Intrinsics.checkNotNullParameter(mediaFocus, "mediaFocus");
        Intrinsics.checkNotNullParameter(mediaTitle, "mediaTitle");
        Intrinsics.checkNotNullParameter(mediaAlbum, "mediaAlbum");
        Intrinsics.checkNotNullParameter(mediaArtist, "mediaArtist");
        Intrinsics.checkNotNullParameter(mediaGenre, "mediaGenre");
        Intrinsics.checkNotNullParameter(mediaPlaylist, "mediaPlaylist");
        Intrinsics.checkNotNullParameter(mediaRadioChannel, "mediaRadioChannel");
        this.query = query;
        this.mediaFocus = mediaFocus;
        this.mediaTitle = mediaTitle;
        this.mediaAlbum = mediaAlbum;
        this.mediaArtist = mediaArtist;
        this.mediaGenre = mediaGenre;
        this.mediaPlaylist = mediaPlaylist;
        this.mediaRadioChannel = mediaRadioChannel;
    }

    public final String getQuery() {
        return this.query;
    }

    public final Optional<String> getMediaFocus() {
        return this.mediaFocus;
    }

    public final Optional<String> getMediaTitle() {
        return this.mediaTitle;
    }

    public final Optional<String> getMediaAlbum() {
        return this.mediaAlbum;
    }

    public final Optional<String> getMediaArtist() {
        return this.mediaArtist;
    }

    public final Optional<String> getMediaGenre() {
        return this.mediaGenre;
    }

    public final Optional<String> getMediaPlaylist() {
        return this.mediaPlaylist;
    }

    public final Optional<String> getMediaRadioChannel() {
        return this.mediaRadioChannel;
    }
}
