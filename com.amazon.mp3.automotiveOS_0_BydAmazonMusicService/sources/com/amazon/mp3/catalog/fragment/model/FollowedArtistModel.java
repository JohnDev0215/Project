package com.amazon.mp3.catalog.fragment.model;

import com.amazon.music.views.library.metadata.ArtistMetadata;
import java.util.List;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: FollowedArtistModel.kt */
/* JADX INFO: loaded from: classes3.dex */
@Metadata(bv = {}, d1 = {"\u0000*\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\u0005\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0007\b\u0086\b\u0018\u00002\u00020\u0001B!\u0012\n\b\u0002\u0010\t\u001a\u0004\u0018\u00010\u0002\u0012\f\u0010\u000f\u001a\b\u0012\u0004\u0012\u00020\u000e0\r¢\u0006\u0004\b\u0013\u0010\u0014J\t\u0010\u0003\u001a\u00020\u0002HÖ\u0001J\t\u0010\u0005\u001a\u00020\u0004HÖ\u0001J\u0013\u0010\b\u001a\u00020\u00072\b\u0010\u0006\u001a\u0004\u0018\u00010\u0001HÖ\u0003R\u0019\u0010\t\u001a\u0004\u0018\u00010\u00028\u0006¢\u0006\f\n\u0004\b\t\u0010\n\u001a\u0004\b\u000b\u0010\fR\u001d\u0010\u000f\u001a\b\u0012\u0004\u0012\u00020\u000e0\r8\u0006¢\u0006\f\n\u0004\b\u000f\u0010\u0010\u001a\u0004\b\u0011\u0010\u0012¨\u0006\u0015"}, d2 = {"Lcom/amazon/mp3/catalog/fragment/model/FollowedArtistModel;", "", "", "toString", "", "hashCode", "other", "", "equals", "nextPageToken", "Ljava/lang/String;", "getNextPageToken", "()Ljava/lang/String;", "", "Lcom/amazon/music/views/library/metadata/ArtistMetadata;", "followedArtists", "Ljava/util/List;", "getFollowedArtists", "()Ljava/util/List;", "<init>", "(Ljava/lang/String;Ljava/util/List;)V", "DigitalMusicAndroid3P_automotiveProdRelease"}, k = 1, mv = {1, 6, 0})
public final /* data */ class FollowedArtistModel {
    private final List<ArtistMetadata> followedArtists;
    private final String nextPageToken;

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof FollowedArtistModel)) {
            return false;
        }
        FollowedArtistModel followedArtistModel = (FollowedArtistModel) other;
        return Intrinsics.areEqual(this.nextPageToken, followedArtistModel.nextPageToken) && Intrinsics.areEqual(this.followedArtists, followedArtistModel.followedArtists);
    }

    public int hashCode() {
        String str = this.nextPageToken;
        return ((str == null ? 0 : str.hashCode()) * 31) + this.followedArtists.hashCode();
    }

    public String toString() {
        return "FollowedArtistModel(nextPageToken=" + ((Object) this.nextPageToken) + ", followedArtists=" + this.followedArtists + ')';
    }

    public FollowedArtistModel(String str, List<ArtistMetadata> followedArtists) {
        Intrinsics.checkNotNullParameter(followedArtists, "followedArtists");
        this.nextPageToken = str;
        this.followedArtists = followedArtists;
    }

    public /* synthetic */ FollowedArtistModel(String str, List list, int i, DefaultConstructorMarker defaultConstructorMarker) {
        this((i & 1) != 0 ? null : str, list);
    }

    public final String getNextPageToken() {
        return this.nextPageToken;
    }

    public final List<ArtistMetadata> getFollowedArtists() {
        return this.followedArtists;
    }
}
