package com.amazon.music.alps.liveevent.model;

import com.amazon.music.platform.graphql.generated.fragment.ArtistEmbedded;
import com.amazon.music.platform.graphql.generated.fragment.Merch;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: ArtistMerch.kt */
/* JADX INFO: loaded from: classes4.dex */
@Metadata(bv = {}, d1 = {"\u0000,\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0007\b\u0086\b\u0018\u00002\u00020\u0001B\u0017\u0012\u0006\u0010\n\u001a\u00020\t\u0012\u0006\u0010\u000f\u001a\u00020\u000e¢\u0006\u0004\b\u0013\u0010\u0014J\t\u0010\u0003\u001a\u00020\u0002HÖ\u0001J\t\u0010\u0005\u001a\u00020\u0004HÖ\u0001J\u0013\u0010\b\u001a\u00020\u00072\b\u0010\u0006\u001a\u0004\u0018\u00010\u0001HÖ\u0003R\u0017\u0010\n\u001a\u00020\t8\u0006¢\u0006\f\n\u0004\b\n\u0010\u000b\u001a\u0004\b\f\u0010\rR\u0017\u0010\u000f\u001a\u00020\u000e8\u0006¢\u0006\f\n\u0004\b\u000f\u0010\u0010\u001a\u0004\b\u0011\u0010\u0012¨\u0006\u0015"}, d2 = {"Lcom/amazon/music/alps/liveevent/model/ArtistMerch;", "", "", "toString", "", "hashCode", "other", "", "equals", "Lcom/amazon/music/platform/graphql/generated/fragment/ArtistEmbedded;", "artist", "Lcom/amazon/music/platform/graphql/generated/fragment/ArtistEmbedded;", "getArtist", "()Lcom/amazon/music/platform/graphql/generated/fragment/ArtistEmbedded;", "Lcom/amazon/music/platform/graphql/generated/fragment/Merch;", "merch", "Lcom/amazon/music/platform/graphql/generated/fragment/Merch;", "getMerch", "()Lcom/amazon/music/platform/graphql/generated/fragment/Merch;", "<init>", "(Lcom/amazon/music/platform/graphql/generated/fragment/ArtistEmbedded;Lcom/amazon/music/platform/graphql/generated/fragment/Merch;)V", "DMMALPS_release"}, k = 1, mv = {1, 6, 0})
public final /* data */ class ArtistMerch {
    private final ArtistEmbedded artist;
    private final Merch merch;

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof ArtistMerch)) {
            return false;
        }
        ArtistMerch artistMerch = (ArtistMerch) other;
        return Intrinsics.areEqual(this.artist, artistMerch.artist) && Intrinsics.areEqual(this.merch, artistMerch.merch);
    }

    public int hashCode() {
        return (this.artist.hashCode() * 31) + this.merch.hashCode();
    }

    public String toString() {
        return "ArtistMerch(artist=" + this.artist + ", merch=" + this.merch + ')';
    }

    public ArtistMerch(ArtistEmbedded artist, Merch merch) {
        Intrinsics.checkNotNullParameter(artist, "artist");
        Intrinsics.checkNotNullParameter(merch, "merch");
        this.artist = artist;
        this.merch = merch;
    }

    public final ArtistEmbedded getArtist() {
        return this.artist;
    }

    public final Merch getMerch() {
        return this.merch;
    }
}
