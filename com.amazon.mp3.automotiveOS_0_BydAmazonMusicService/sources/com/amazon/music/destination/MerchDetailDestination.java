package com.amazon.music.destination;

import com.amazon.music.destination.core.Destination;
import com.amazon.music.destination.parser.ParserUtil;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: MerchDetailDestination.kt */
/* JADX INFO: loaded from: classes4.dex */
@Metadata(d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\b\u0018\u00002\u00020\u0001B)\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0003\u0012\b\u0010\u0005\u001a\u0004\u0018\u00010\u0003\u0012\b\u0010\u0006\u001a\u0004\u0018\u00010\u0003¢\u0006\u0002\u0010\u0007R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\b\u0010\tR\u0011\u0010\u0004\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\n\u0010\t¨\u0006\u000b"}, d2 = {"Lcom/amazon/music/destination/MerchDetailDestination;", "Lcom/amazon/music/destination/core/Destination;", "artistAsin", "", "merchAsin", "ref", ParserUtil.TAG_QUERY_PARAM_NAME, "(Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;)V", "getArtistAsin", "()Ljava/lang/String;", "getMerchAsin", "DMMExplore_release"}, k = 1, mv = {1, 6, 0}, xi = 48)
public final class MerchDetailDestination extends Destination {
    private final String artistAsin;
    private final String merchAsin;

    public final String getArtistAsin() {
        return this.artistAsin;
    }

    public final String getMerchAsin() {
        return this.merchAsin;
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public MerchDetailDestination(String artistAsin, String merchAsin, String str, String str2) {
        super(str, str2);
        Intrinsics.checkNotNullParameter(artistAsin, "artistAsin");
        Intrinsics.checkNotNullParameter(merchAsin, "merchAsin");
        this.artistAsin = artistAsin;
        this.merchAsin = merchAsin;
    }
}
