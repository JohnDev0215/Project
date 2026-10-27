package com.amazon.mp3.playlist.operation;

import android.net.Uri;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: PlaylistPrivacyOperation.kt */
/* JADX INFO: loaded from: classes3.dex */
@Metadata(bv = {}, d1 = {"\u0000.\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\t\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\t\n\u0002\b\u0007\b\u0086\b\u0018\u00002\u00020\u0001B+\u0012\u0006\u0010\t\u001a\u00020\u0002\u0012\u0006\u0010\r\u001a\u00020\u0004\u0012\b\u0010\u0012\u001a\u0004\u0018\u00010\u0011\u0012\b\u0010\u0017\u001a\u0004\u0018\u00010\u0016¢\u0006\u0004\b\u001b\u0010\u001cJ\t\u0010\u0003\u001a\u00020\u0002HÖ\u0001J\t\u0010\u0005\u001a\u00020\u0004HÖ\u0001J\u0013\u0010\b\u001a\u00020\u00072\b\u0010\u0006\u001a\u0004\u0018\u00010\u0001HÖ\u0003R\u0017\u0010\t\u001a\u00020\u00028\u0006¢\u0006\f\n\u0004\b\t\u0010\n\u001a\u0004\b\u000b\u0010\fR\u0017\u0010\r\u001a\u00020\u00048\u0006¢\u0006\f\n\u0004\b\r\u0010\u000e\u001a\u0004\b\u000f\u0010\u0010R\u0019\u0010\u0012\u001a\u0004\u0018\u00010\u00118\u0006¢\u0006\f\n\u0004\b\u0012\u0010\u0013\u001a\u0004\b\u0014\u0010\u0015R\u0019\u0010\u0017\u001a\u0004\u0018\u00010\u00168\u0006¢\u0006\f\n\u0004\b\u0017\u0010\u0018\u001a\u0004\b\u0019\u0010\u001a¨\u0006\u001d"}, d2 = {"Lcom/amazon/mp3/playlist/operation/PlaylistPrivacyMetadata;", "", "", "toString", "", "hashCode", "other", "", "equals", "title", "Ljava/lang/String;", "getTitle", "()Ljava/lang/String;", "trackCount", "I", "getTrackCount", "()I", "Landroid/net/Uri;", "uri", "Landroid/net/Uri;", "getUri", "()Landroid/net/Uri;", "", "id", "Ljava/lang/Long;", "getId", "()Ljava/lang/Long;", "<init>", "(Ljava/lang/String;ILandroid/net/Uri;Ljava/lang/Long;)V", "DigitalMusicAndroid3P_automotiveProdRelease"}, k = 1, mv = {1, 6, 0})
public final /* data */ class PlaylistPrivacyMetadata {
    private final Long id;
    private final String title;
    private final int trackCount;
    private final Uri uri;

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof PlaylistPrivacyMetadata)) {
            return false;
        }
        PlaylistPrivacyMetadata playlistPrivacyMetadata = (PlaylistPrivacyMetadata) other;
        return Intrinsics.areEqual(this.title, playlistPrivacyMetadata.title) && this.trackCount == playlistPrivacyMetadata.trackCount && Intrinsics.areEqual(this.uri, playlistPrivacyMetadata.uri) && Intrinsics.areEqual(this.id, playlistPrivacyMetadata.id);
    }

    public int hashCode() {
        int iHashCode = ((this.title.hashCode() * 31) + Integer.hashCode(this.trackCount)) * 31;
        Uri uri = this.uri;
        int iHashCode2 = (iHashCode + (uri == null ? 0 : uri.hashCode())) * 31;
        Long l = this.id;
        return iHashCode2 + (l != null ? l.hashCode() : 0);
    }

    public String toString() {
        return "PlaylistPrivacyMetadata(title=" + this.title + ", trackCount=" + this.trackCount + ", uri=" + this.uri + ", id=" + this.id + ')';
    }

    public PlaylistPrivacyMetadata(String title, int i, Uri uri, Long l) {
        Intrinsics.checkNotNullParameter(title, "title");
        this.title = title;
        this.trackCount = i;
        this.uri = uri;
        this.id = l;
    }

    public final String getTitle() {
        return this.title;
    }

    public final int getTrackCount() {
        return this.trackCount;
    }

    public final Uri getUri() {
        return this.uri;
    }

    public final Long getId() {
        return this.id;
    }
}
