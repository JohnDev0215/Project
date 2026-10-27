package com.amazon.digitalmusicxp.inputs;

import com.amazon.digitalmusicxp.ExpectedFunctionsKt;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: LibraryAlbumSeedContextInput.kt */
/* JADX INFO: loaded from: classes2.dex */
@Metadata(bv = {}, d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0002\b\u0007\b\u0086\b\u0018\u00002\u00020\u0001B#\u0012\u0006\u0010\t\u001a\u00020\u0002\u0012\u0006\u0010\r\u001a\u00020\u0002\u0012\n\b\u0002\u0010\u0010\u001a\u0004\u0018\u00010\u000f¢\u0006\u0004\b\u0014\u0010\u0015J\t\u0010\u0003\u001a\u00020\u0002HÖ\u0001J\t\u0010\u0005\u001a\u00020\u0004HÖ\u0001J\u0013\u0010\b\u001a\u00020\u00072\b\u0010\u0006\u001a\u0004\u0018\u00010\u0001HÖ\u0003R\u0017\u0010\t\u001a\u00020\u00028\u0006¢\u0006\f\n\u0004\b\t\u0010\n\u001a\u0004\b\u000b\u0010\fR\u0017\u0010\r\u001a\u00020\u00028\u0006¢\u0006\f\n\u0004\b\r\u0010\n\u001a\u0004\b\u000e\u0010\fR\u0019\u0010\u0010\u001a\u0004\u0018\u00010\u000f8\u0006¢\u0006\f\n\u0004\b\u0010\u0010\u0011\u001a\u0004\b\u0012\u0010\u0013¨\u0006\u0016"}, d2 = {"Lcom/amazon/digitalmusicxp/inputs/LibraryAlbumSeedContextInput;", "", "", "toString", "", "hashCode", "other", "", "equals", "albumName", "Ljava/lang/String;", "getAlbumName", "()Ljava/lang/String;", "artistName", "getArtistName", "Lcom/amazon/digitalmusicxp/inputs/MetricsContextInput;", "metricsContext", "Lcom/amazon/digitalmusicxp/inputs/MetricsContextInput;", "getMetricsContext", "()Lcom/amazon/digitalmusicxp/inputs/MetricsContextInput;", "<init>", "(Ljava/lang/String;Ljava/lang/String;Lcom/amazon/digitalmusicxp/inputs/MetricsContextInput;)V", "DigitalMusicCloudQueueXP_release"}, k = 1, mv = {1, 7, 1})
public final /* data */ class LibraryAlbumSeedContextInput {
    private final String albumName;
    private final String artistName;
    private final MetricsContextInput metricsContext;

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof LibraryAlbumSeedContextInput)) {
            return false;
        }
        LibraryAlbumSeedContextInput libraryAlbumSeedContextInput = (LibraryAlbumSeedContextInput) other;
        return Intrinsics.areEqual(this.albumName, libraryAlbumSeedContextInput.albumName) && Intrinsics.areEqual(this.artistName, libraryAlbumSeedContextInput.artistName) && Intrinsics.areEqual(this.metricsContext, libraryAlbumSeedContextInput.metricsContext);
    }

    public int hashCode() {
        int iHashCode = ((this.albumName.hashCode() * 31) + this.artistName.hashCode()) * 31;
        MetricsContextInput metricsContextInput = this.metricsContext;
        return iHashCode + (metricsContextInput == null ? 0 : metricsContextInput.hashCode());
    }

    public String toString() {
        return "LibraryAlbumSeedContextInput(albumName=" + this.albumName + ", artistName=" + this.artistName + ", metricsContext=" + this.metricsContext + ')';
    }

    public LibraryAlbumSeedContextInput(String albumName, String artistName, MetricsContextInput metricsContextInput) {
        Intrinsics.checkNotNullParameter(albumName, "albumName");
        Intrinsics.checkNotNullParameter(artistName, "artistName");
        this.albumName = albumName;
        this.artistName = artistName;
        this.metricsContext = metricsContextInput;
        ExpectedFunctionsKt.freeze(this);
    }

    public /* synthetic */ LibraryAlbumSeedContextInput(String str, String str2, MetricsContextInput metricsContextInput, int i, DefaultConstructorMarker defaultConstructorMarker) {
        this(str, str2, (i & 4) != 0 ? null : metricsContextInput);
    }

    public final String getAlbumName() {
        return this.albumName;
    }

    public final String getArtistName() {
        return this.artistName;
    }
}
