package com.amazon.mp3.playlist.operation;

import android.net.Uri;
import com.amazon.mp3.library.provider.MediaProvider;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: PlaylistPrivacyOperation.kt */
/* JADX INFO: loaded from: classes3.dex */
@Metadata(bv = {}, d1 = {"\u0000:\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0013\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\t\n\u0002\b\u0007\b\u0086\b\u0018\u00002\u00020\u0001BW\u0012\u0006\u0010\n\u001a\u00020\u0002\u0012\b\u0010\u000e\u001a\u0004\u0018\u00010\u0002\u0012\b\u0010\u0010\u001a\u0004\u0018\u00010\u0002\u0012\u0006\u0010\u0012\u001a\u00020\u0004\u0012\u0006\u0010\u0016\u001a\u00020\u0002\u0012\u0006\u0010\u0018\u001a\u00020\u0002\u0012\u0006\u0010\u001d\u001a\u00020\u001c\u0012\b\u0010$\u001a\u0004\u0018\u00010#\u0012\b\u0010)\u001a\u0004\u0018\u00010(¢\u0006\u0004\b-\u0010.J\t\u0010\u0003\u001a\u00020\u0002HÖ\u0001J\t\u0010\u0005\u001a\u00020\u0004HÖ\u0001J\u0013\u0010\t\u001a\u00020\b2\b\u0010\u0007\u001a\u0004\u0018\u00010\u0006HÖ\u0003R\u001a\u0010\n\u001a\u00020\u00028\u0016X\u0096\u0004¢\u0006\f\n\u0004\b\n\u0010\u000b\u001a\u0004\b\f\u0010\rR\u001c\u0010\u000e\u001a\u0004\u0018\u00010\u00028\u0016X\u0096\u0004¢\u0006\f\n\u0004\b\u000e\u0010\u000b\u001a\u0004\b\u000f\u0010\rR\u001c\u0010\u0010\u001a\u0004\u0018\u00010\u00028\u0016X\u0096\u0004¢\u0006\f\n\u0004\b\u0010\u0010\u000b\u001a\u0004\b\u0011\u0010\rR\u0017\u0010\u0012\u001a\u00020\u00048\u0006¢\u0006\f\n\u0004\b\u0012\u0010\u0013\u001a\u0004\b\u0014\u0010\u0015R\u0017\u0010\u0016\u001a\u00020\u00028\u0006¢\u0006\f\n\u0004\b\u0016\u0010\u000b\u001a\u0004\b\u0017\u0010\rR\"\u0010\u0018\u001a\u00020\u00028\u0006@\u0006X\u0086\u000e¢\u0006\u0012\n\u0004\b\u0018\u0010\u000b\u001a\u0004\b\u0019\u0010\r\"\u0004\b\u001a\u0010\u001bR\"\u0010\u001d\u001a\u00020\u001c8\u0006@\u0006X\u0086\u000e¢\u0006\u0012\n\u0004\b\u001d\u0010\u001e\u001a\u0004\b\u001f\u0010 \"\u0004\b!\u0010\"R\u0019\u0010$\u001a\u0004\u0018\u00010#8\u0006¢\u0006\f\n\u0004\b$\u0010%\u001a\u0004\b&\u0010'R\u0019\u0010)\u001a\u0004\u0018\u00010(8\u0006¢\u0006\f\n\u0004\b)\u0010*\u001a\u0004\b+\u0010,¨\u0006/"}, d2 = {"Lcom/amazon/mp3/playlist/operation/UserPlaylistShareMetadata;", "Lcom/amazon/mp3/playlist/operation/PlaylistShareMetadata;", "", "toString", "", "hashCode", "", "other", "", "equals", "title", "Ljava/lang/String;", "getTitle", "()Ljava/lang/String;", "asin", "getAsin", "artworkUrl", "getArtworkUrl", "trackCount", "I", "getTrackCount", "()I", "luid", "getLuid", "version", "getVersion", "setVersion", "(Ljava/lang/String;)V", "Lcom/amazon/mp3/library/provider/MediaProvider$Playlists$VisibilityState;", "visibility", "Lcom/amazon/mp3/library/provider/MediaProvider$Playlists$VisibilityState;", "getVisibility", "()Lcom/amazon/mp3/library/provider/MediaProvider$Playlists$VisibilityState;", "setVisibility", "(Lcom/amazon/mp3/library/provider/MediaProvider$Playlists$VisibilityState;)V", "Landroid/net/Uri;", "uri", "Landroid/net/Uri;", "getUri", "()Landroid/net/Uri;", "", "id", "Ljava/lang/Long;", "getId", "()Ljava/lang/Long;", "<init>", "(Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;ILjava/lang/String;Ljava/lang/String;Lcom/amazon/mp3/library/provider/MediaProvider$Playlists$VisibilityState;Landroid/net/Uri;Ljava/lang/Long;)V", "DigitalMusicAndroid3P_automotiveProdRelease"}, k = 1, mv = {1, 6, 0})
public final /* data */ class UserPlaylistShareMetadata extends PlaylistShareMetadata {
    private final String artworkUrl;
    private final String asin;
    private final Long id;
    private final String luid;
    private final String title;
    private final int trackCount;
    private final Uri uri;
    private String version;
    private MediaProvider.Playlists.VisibilityState visibility;

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof UserPlaylistShareMetadata)) {
            return false;
        }
        UserPlaylistShareMetadata userPlaylistShareMetadata = (UserPlaylistShareMetadata) other;
        return Intrinsics.areEqual(getTitle(), userPlaylistShareMetadata.getTitle()) && Intrinsics.areEqual(getAsin(), userPlaylistShareMetadata.getAsin()) && Intrinsics.areEqual(getArtworkUrl(), userPlaylistShareMetadata.getArtworkUrl()) && this.trackCount == userPlaylistShareMetadata.trackCount && Intrinsics.areEqual(this.luid, userPlaylistShareMetadata.luid) && Intrinsics.areEqual(this.version, userPlaylistShareMetadata.version) && this.visibility == userPlaylistShareMetadata.visibility && Intrinsics.areEqual(this.uri, userPlaylistShareMetadata.uri) && Intrinsics.areEqual(this.id, userPlaylistShareMetadata.id);
    }

    public int hashCode() {
        int iHashCode = ((((((((((((getTitle().hashCode() * 31) + (getAsin() == null ? 0 : getAsin().hashCode())) * 31) + (getArtworkUrl() == null ? 0 : getArtworkUrl().hashCode())) * 31) + Integer.hashCode(this.trackCount)) * 31) + this.luid.hashCode()) * 31) + this.version.hashCode()) * 31) + this.visibility.hashCode()) * 31;
        Uri uri = this.uri;
        int iHashCode2 = (iHashCode + (uri == null ? 0 : uri.hashCode())) * 31;
        Long l = this.id;
        return iHashCode2 + (l != null ? l.hashCode() : 0);
    }

    public String toString() {
        return "UserPlaylistShareMetadata(title=" + getTitle() + ", asin=" + ((Object) getAsin()) + ", artworkUrl=" + ((Object) getArtworkUrl()) + ", trackCount=" + this.trackCount + ", luid=" + this.luid + ", version=" + this.version + ", visibility=" + this.visibility + ", uri=" + this.uri + ", id=" + this.id + ')';
    }

    @Override // com.amazon.mp3.playlist.operation.PlaylistShareMetadata
    public String getTitle() {
        return this.title;
    }

    @Override // com.amazon.mp3.playlist.operation.PlaylistShareMetadata
    public String getAsin() {
        return this.asin;
    }

    @Override // com.amazon.mp3.playlist.operation.PlaylistShareMetadata
    public String getArtworkUrl() {
        return this.artworkUrl;
    }

    public final int getTrackCount() {
        return this.trackCount;
    }

    public final String getLuid() {
        return this.luid;
    }

    public final String getVersion() {
        return this.version;
    }

    public final void setVersion(String str) {
        Intrinsics.checkNotNullParameter(str, "<set-?>");
        this.version = str;
    }

    public final MediaProvider.Playlists.VisibilityState getVisibility() {
        return this.visibility;
    }

    public final Uri getUri() {
        return this.uri;
    }

    public final Long getId() {
        return this.id;
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public UserPlaylistShareMetadata(String title, String str, String str2, int i, String luid, String version, MediaProvider.Playlists.VisibilityState visibility, Uri uri, Long l) {
        super(title, str, str2);
        Intrinsics.checkNotNullParameter(title, "title");
        Intrinsics.checkNotNullParameter(luid, "luid");
        Intrinsics.checkNotNullParameter(version, "version");
        Intrinsics.checkNotNullParameter(visibility, "visibility");
        this.title = title;
        this.asin = str;
        this.artworkUrl = str2;
        this.trackCount = i;
        this.luid = luid;
        this.version = version;
        this.visibility = visibility;
        this.uri = uri;
        this.id = l;
    }
}
