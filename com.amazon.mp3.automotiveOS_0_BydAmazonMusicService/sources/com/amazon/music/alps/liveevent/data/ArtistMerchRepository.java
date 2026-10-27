package com.amazon.music.alps.liveevent.data;

import com.amazon.music.platform.graphql.generated.ArtistMerchQuery;
import io.reactivex.rxjava3.core.Observable;
import java.util.Optional;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: ArtistMerchRepository.kt */
/* JADX INFO: loaded from: classes4.dex */
@Metadata(d1 = {"\u0000 \n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0001¢\u0006\u0002\u0010\u0003J\u001c\u0010\u0004\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00070\u00060\u00052\u0006\u0010\b\u001a\u00020\tH\u0016R\u000e\u0010\u0002\u001a\u00020\u0001X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\n"}, d2 = {"Lcom/amazon/music/alps/liveevent/data/ArtistMerchRepository;", "Lcom/amazon/music/alps/liveevent/data/ArtistMerchDataSource;", "remote", "(Lcom/amazon/music/alps/liveevent/data/ArtistMerchDataSource;)V", "getArtist", "Lio/reactivex/rxjava3/core/Observable;", "Ljava/util/Optional;", "Lcom/amazon/music/platform/graphql/generated/ArtistMerchQuery$Artist;", "artistId", "", "DMMALPS_release"}, k = 1, mv = {1, 6, 0}, xi = 48)
public final class ArtistMerchRepository implements ArtistMerchDataSource {
    private final ArtistMerchDataSource remote;

    public ArtistMerchRepository(ArtistMerchDataSource remote) {
        Intrinsics.checkNotNullParameter(remote, "remote");
        this.remote = remote;
    }

    @Override // com.amazon.music.alps.liveevent.data.ArtistMerchDataSource
    public Observable<Optional<ArtistMerchQuery.Artist>> getArtist(String artistId) {
        Intrinsics.checkNotNullParameter(artistId, "artistId");
        return this.remote.getArtist(artistId);
    }
}
