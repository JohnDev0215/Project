package com.amazon.music.find.converter.model;

import com.amazon.musicensembleservice.brush.Entity;
import com.google.android.gms.actions.SearchIntents;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: MusicSearchQuery.kt */
/* JADX INFO: loaded from: classes4.dex */
@Metadata(d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\b\n\u0002\b\u0012\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0000\n\u0002\b\u0003\b\u0086\b\u0018\u00002\u00020\u0001B)\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\b\u0010\u0006\u001a\u0004\u0018\u00010\u0003\u0012\b\u0010\u0007\u001a\u0004\u0018\u00010\u0005¢\u0006\u0002\u0010\bJ\t\u0010\u0011\u001a\u00020\u0003HÆ\u0003J\t\u0010\u0012\u001a\u00020\u0005HÆ\u0003J\u000b\u0010\u0013\u001a\u0004\u0018\u00010\u0003HÆ\u0003J\u0010\u0010\u0014\u001a\u0004\u0018\u00010\u0005HÆ\u0003¢\u0006\u0002\u0010\nJ:\u0010\u0015\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00052\n\b\u0002\u0010\u0006\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010\u0007\u001a\u0004\u0018\u00010\u0005HÆ\u0001¢\u0006\u0002\u0010\u0016J\u0013\u0010\u0017\u001a\u00020\u00182\b\u0010\u0019\u001a\u0004\u0018\u00010\u001aHÖ\u0003J\t\u0010\u001b\u001a\u00020\u0005HÖ\u0001J\t\u0010\u001c\u001a\u00020\u0003HÖ\u0001R\u0015\u0010\u0007\u001a\u0004\u0018\u00010\u0005¢\u0006\n\n\u0002\u0010\u000b\u001a\u0004\b\t\u0010\nR\u0011\u0010\u0004\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\f\u0010\rR\u0013\u0010\u0006\u001a\u0004\u0018\u00010\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u000e\u0010\u000fR\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0010\u0010\u000f¨\u0006\u001d"}, d2 = {"Lcom/amazon/music/find/converter/model/MusicSearchQuery;", "Lcom/amazon/musicensembleservice/brush/Entity;", SearchIntents.EXTRA_QUERY, "", "imageSize", "", "imageUrl", "imageResource", "(Ljava/lang/String;ILjava/lang/String;Ljava/lang/Integer;)V", "getImageResource", "()Ljava/lang/Integer;", "Ljava/lang/Integer;", "getImageSize", "()I", "getImageUrl", "()Ljava/lang/String;", "getQuery", "component1", "component2", "component3", "component4", "copy", "(Ljava/lang/String;ILjava/lang/String;Ljava/lang/Integer;)Lcom/amazon/music/find/converter/model/MusicSearchQuery;", "equals", "", "other", "", "hashCode", "toString", "DMMFindExperience_release"}, k = 1, mv = {1, 6, 0}, xi = 48)
public final /* data */ class MusicSearchQuery extends Entity {
    private final Integer imageResource;
    private final int imageSize;
    private final String imageUrl;
    private final String query;

    public static /* synthetic */ MusicSearchQuery copy$default(MusicSearchQuery musicSearchQuery, String str, int i, String str2, Integer num, int i2, Object obj) {
        if ((i2 & 1) != 0) {
            str = musicSearchQuery.query;
        }
        if ((i2 & 2) != 0) {
            i = musicSearchQuery.imageSize;
        }
        if ((i2 & 4) != 0) {
            str2 = musicSearchQuery.imageUrl;
        }
        if ((i2 & 8) != 0) {
            num = musicSearchQuery.imageResource;
        }
        return musicSearchQuery.copy(str, i, str2, num);
    }

    /* JADX INFO: renamed from: component1, reason: from getter */
    public final String getQuery() {
        return this.query;
    }

    /* JADX INFO: renamed from: component2, reason: from getter */
    public final int getImageSize() {
        return this.imageSize;
    }

    /* JADX INFO: renamed from: component3, reason: from getter */
    public final String getImageUrl() {
        return this.imageUrl;
    }

    /* JADX INFO: renamed from: component4, reason: from getter */
    public final Integer getImageResource() {
        return this.imageResource;
    }

    public final MusicSearchQuery copy(String query, int imageSize, String imageUrl, Integer imageResource) {
        Intrinsics.checkNotNullParameter(query, "query");
        return new MusicSearchQuery(query, imageSize, imageUrl, imageResource);
    }

    @Override // com.amazon.musicensembleservice.brush.Entity
    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof MusicSearchQuery)) {
            return false;
        }
        MusicSearchQuery musicSearchQuery = (MusicSearchQuery) other;
        return Intrinsics.areEqual(this.query, musicSearchQuery.query) && this.imageSize == musicSearchQuery.imageSize && Intrinsics.areEqual(this.imageUrl, musicSearchQuery.imageUrl) && Intrinsics.areEqual(this.imageResource, musicSearchQuery.imageResource);
    }

    @Override // com.amazon.musicensembleservice.brush.Entity
    public int hashCode() {
        int iHashCode = ((this.query.hashCode() * 31) + Integer.hashCode(this.imageSize)) * 31;
        String str = this.imageUrl;
        int iHashCode2 = (iHashCode + (str == null ? 0 : str.hashCode())) * 31;
        Integer num = this.imageResource;
        return iHashCode2 + (num != null ? num.hashCode() : 0);
    }

    public String toString() {
        return "MusicSearchQuery(query=" + this.query + ", imageSize=" + this.imageSize + ", imageUrl=" + ((Object) this.imageUrl) + ", imageResource=" + this.imageResource + ')';
    }

    public final String getQuery() {
        return this.query;
    }

    public final int getImageSize() {
        return this.imageSize;
    }

    public final String getImageUrl() {
        return this.imageUrl;
    }

    public final Integer getImageResource() {
        return this.imageResource;
    }

    public MusicSearchQuery(String query, int i, String str, Integer num) {
        Intrinsics.checkNotNullParameter(query, "query");
        this.query = query;
        this.imageSize = i;
        this.imageUrl = str;
        this.imageResource = num;
    }
}
