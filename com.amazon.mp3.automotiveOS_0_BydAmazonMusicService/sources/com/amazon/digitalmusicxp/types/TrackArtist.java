package com.amazon.digitalmusicxp.types;

import com.amazon.digitalmusicxp.ExpectedFunctionsKt;
import java.util.List;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: TrackArtist.kt */
/* JADX INFO: loaded from: classes2.dex */
@Metadata(bv = {}, d1 = {"\u0000*\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\t\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0007\b\u0086\b\u0018\u00002\u00020\u0001B1\u0012\u0006\u0010\t\u001a\u00020\u0002\u0012\n\b\u0002\u0010\r\u001a\u0004\u0018\u00010\u0002\u0012\u0006\u0010\u000f\u001a\u00020\u0002\u0012\f\u0010\u0013\u001a\b\u0012\u0004\u0012\u00020\u00120\u0011¢\u0006\u0004\b\u0017\u0010\u0018J\t\u0010\u0003\u001a\u00020\u0002HÖ\u0001J\t\u0010\u0005\u001a\u00020\u0004HÖ\u0001J\u0013\u0010\b\u001a\u00020\u00072\b\u0010\u0006\u001a\u0004\u0018\u00010\u0001HÖ\u0003R\u0017\u0010\t\u001a\u00020\u00028\u0006¢\u0006\f\n\u0004\b\t\u0010\n\u001a\u0004\b\u000b\u0010\fR\u0019\u0010\r\u001a\u0004\u0018\u00010\u00028\u0006¢\u0006\f\n\u0004\b\r\u0010\n\u001a\u0004\b\u000e\u0010\fR\u0017\u0010\u000f\u001a\u00020\u00028\u0006¢\u0006\f\n\u0004\b\u000f\u0010\n\u001a\u0004\b\u0010\u0010\fR\u001d\u0010\u0013\u001a\b\u0012\u0004\u0012\u00020\u00120\u00118\u0006¢\u0006\f\n\u0004\b\u0013\u0010\u0014\u001a\u0004\b\u0015\u0010\u0016¨\u0006\u0019"}, d2 = {"Lcom/amazon/digitalmusicxp/types/TrackArtist;", "", "", "toString", "", "hashCode", "other", "", "equals", "asin", "Ljava/lang/String;", "getAsin", "()Ljava/lang/String;", "contributorAsin", "getContributorAsin", "name", "getName", "", "Lcom/amazon/digitalmusicxp/types/ArtistRole;", "roles", "Ljava/util/List;", "getRoles", "()Ljava/util/List;", "<init>", "(Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/util/List;)V", "DigitalMusicCloudQueueXP_release"}, k = 1, mv = {1, 7, 1})
public final /* data */ class TrackArtist {
    private final String asin;
    private final String contributorAsin;
    private final String name;
    private final List<ArtistRole> roles;

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof TrackArtist)) {
            return false;
        }
        TrackArtist trackArtist = (TrackArtist) other;
        return Intrinsics.areEqual(this.asin, trackArtist.asin) && Intrinsics.areEqual(this.contributorAsin, trackArtist.contributorAsin) && Intrinsics.areEqual(this.name, trackArtist.name) && Intrinsics.areEqual(this.roles, trackArtist.roles);
    }

    public int hashCode() {
        int iHashCode = this.asin.hashCode() * 31;
        String str = this.contributorAsin;
        return ((((iHashCode + (str == null ? 0 : str.hashCode())) * 31) + this.name.hashCode()) * 31) + this.roles.hashCode();
    }

    public String toString() {
        return "TrackArtist(asin=" + this.asin + ", contributorAsin=" + this.contributorAsin + ", name=" + this.name + ", roles=" + this.roles + ')';
    }

    public TrackArtist(String asin, String str, String name, List<ArtistRole> roles) {
        Intrinsics.checkNotNullParameter(asin, "asin");
        Intrinsics.checkNotNullParameter(name, "name");
        Intrinsics.checkNotNullParameter(roles, "roles");
        this.asin = asin;
        this.contributorAsin = str;
        this.name = name;
        this.roles = roles;
        ExpectedFunctionsKt.freeze(this);
    }

    public /* synthetic */ TrackArtist(String str, String str2, String str3, List list, int i, DefaultConstructorMarker defaultConstructorMarker) {
        this(str, (i & 2) != 0 ? null : str2, str3, list);
    }

    public final String getAsin() {
        return this.asin;
    }

    public final String getName() {
        return this.name;
    }
}
