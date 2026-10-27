package com.amazon.music.destination;

import com.amazon.music.destination.core.Destination;
import com.amazon.music.destination.parser.ParserUtil;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: ArtistDetailDestination.kt */
/* JADX INFO: loaded from: classes4.dex */
@Metadata(d1 = {"\u0000\u001a\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0005\n\u0002\u0010\u000b\n\u0002\b\b\u0018\u00002\u00020\u0001B?\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\b\u0010\u0004\u001a\u0004\u0018\u00010\u0003\u0012\b\u0010\u0005\u001a\u0004\u0018\u00010\u0003\u0012\b\u0010\u0006\u001a\u0004\u0018\u00010\u0003\u0012\b\u0010\u0007\u001a\u0004\u0018\u00010\u0003\u0012\b\b\u0002\u0010\b\u001a\u00020\t¢\u0006\u0002\u0010\nR\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u000b\u0010\fR\u0013\u0010\u0004\u001a\u0004\u0018\u00010\u0003¢\u0006\b\n\u0000\u001a\u0004\b\r\u0010\fR\u0011\u0010\b\u001a\u00020\t¢\u0006\b\n\u0000\u001a\u0004\b\u000e\u0010\u000fR\u0013\u0010\u0005\u001a\u0004\u0018\u00010\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0010\u0010\f¨\u0006\u0011"}, d2 = {"Lcom/amazon/music/destination/ArtistDetailDestination;", "Lcom/amazon/music/destination/core/Destination;", "artistAsin", "", "artistId", "storyId", "ref", ParserUtil.TAG_QUERY_PARAM_NAME, "showReleases", "", "(Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Z)V", "getArtistAsin", "()Ljava/lang/String;", "getArtistId", "getShowReleases", "()Z", "getStoryId", "DMMExplore_release"}, k = 1, mv = {1, 6, 0}, xi = 48)
public final class ArtistDetailDestination extends Destination {
    private final String artistAsin;
    private final String artistId;
    private final boolean showReleases;
    private final String storyId;

    public /* synthetic */ ArtistDetailDestination(String str, String str2, String str3, String str4, String str5, boolean z, int i, DefaultConstructorMarker defaultConstructorMarker) {
        this(str, str2, str3, str4, str5, (i & 32) != 0 ? false : z);
    }

    public final String getArtistAsin() {
        return this.artistAsin;
    }

    public final String getArtistId() {
        return this.artistId;
    }

    public final String getStoryId() {
        return this.storyId;
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public ArtistDetailDestination(String artistAsin, String str, String str2, String str3, String str4, boolean z) {
        super(str3, str4);
        Intrinsics.checkNotNullParameter(artistAsin, "artistAsin");
        this.artistAsin = artistAsin;
        this.artistId = str;
        this.storyId = str2;
        this.showReleases = z;
    }

    public final boolean getShowReleases() {
        return this.showReleases;
    }
}
