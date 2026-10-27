package com.amazon.mp3.playlist.operation;

import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: PlaylistPrivacyOperation.kt */
/* JADX INFO: loaded from: classes3.dex */
@Metadata(bv = {}, d1 = {"\u0000\"\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000b\n\u0002\b\f\b\u0086\b\u0018\u00002\u00020\u0001B#\u0012\u0006\u0010\n\u001a\u00020\u0002\u0012\b\u0010\u000e\u001a\u0004\u0018\u00010\u0002\u0012\b\u0010\u0010\u001a\u0004\u0018\u00010\u0002¢\u0006\u0004\b\u0012\u0010\u0013J\t\u0010\u0003\u001a\u00020\u0002HÖ\u0001J\t\u0010\u0005\u001a\u00020\u0004HÖ\u0001J\u0013\u0010\t\u001a\u00020\b2\b\u0010\u0007\u001a\u0004\u0018\u00010\u0006HÖ\u0003R\u001a\u0010\n\u001a\u00020\u00028\u0016X\u0096\u0004¢\u0006\f\n\u0004\b\n\u0010\u000b\u001a\u0004\b\f\u0010\rR\u001c\u0010\u000e\u001a\u0004\u0018\u00010\u00028\u0016X\u0096\u0004¢\u0006\f\n\u0004\b\u000e\u0010\u000b\u001a\u0004\b\u000f\u0010\rR\u001c\u0010\u0010\u001a\u0004\u0018\u00010\u00028\u0016X\u0096\u0004¢\u0006\f\n\u0004\b\u0010\u0010\u000b\u001a\u0004\b\u0011\u0010\r¨\u0006\u0014"}, d2 = {"Lcom/amazon/mp3/playlist/operation/CatalogPlaylistShareMetadata;", "Lcom/amazon/mp3/playlist/operation/PlaylistShareMetadata;", "", "toString", "", "hashCode", "", "other", "", "equals", "title", "Ljava/lang/String;", "getTitle", "()Ljava/lang/String;", "asin", "getAsin", "artworkUrl", "getArtworkUrl", "<init>", "(Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;)V", "DigitalMusicAndroid3P_automotiveProdRelease"}, k = 1, mv = {1, 6, 0})
public final /* data */ class CatalogPlaylistShareMetadata extends PlaylistShareMetadata {
    private final String artworkUrl;
    private final String asin;
    private final String title;

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof CatalogPlaylistShareMetadata)) {
            return false;
        }
        CatalogPlaylistShareMetadata catalogPlaylistShareMetadata = (CatalogPlaylistShareMetadata) other;
        return Intrinsics.areEqual(getTitle(), catalogPlaylistShareMetadata.getTitle()) && Intrinsics.areEqual(getAsin(), catalogPlaylistShareMetadata.getAsin()) && Intrinsics.areEqual(getArtworkUrl(), catalogPlaylistShareMetadata.getArtworkUrl());
    }

    public int hashCode() {
        return (((getTitle().hashCode() * 31) + (getAsin() == null ? 0 : getAsin().hashCode())) * 31) + (getArtworkUrl() != null ? getArtworkUrl().hashCode() : 0);
    }

    public String toString() {
        return "CatalogPlaylistShareMetadata(title=" + getTitle() + ", asin=" + ((Object) getAsin()) + ", artworkUrl=" + ((Object) getArtworkUrl()) + ')';
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

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public CatalogPlaylistShareMetadata(String title, String str, String str2) {
        super(title, str, str2);
        Intrinsics.checkNotNullParameter(title, "title");
        this.title = title;
        this.asin = str;
        this.artworkUrl = str2;
    }
}
