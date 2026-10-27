package com.amazon.music.alps.liveevent.data;

import com.amazon.music.platform.graphql.generated.ArtistMerchQuery;
import io.reactivex.rxjava3.core.Observable;
import java.util.Optional;
import kotlin.Metadata;

/* JADX INFO: compiled from: ArtistMerchDataSource.kt */
/* JADX INFO: loaded from: classes4.dex */
@Metadata(d1 = {"\u0000\u001e\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\bf\u0018\u00002\u00020\u0001J\u001c\u0010\u0002\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00050\u00040\u00032\u0006\u0010\u0006\u001a\u00020\u0007H&¨\u0006\b"}, d2 = {"Lcom/amazon/music/alps/liveevent/data/ArtistMerchDataSource;", "", "getArtist", "Lio/reactivex/rxjava3/core/Observable;", "Ljava/util/Optional;", "Lcom/amazon/music/platform/graphql/generated/ArtistMerchQuery$Artist;", "artistId", "", "DMMALPS_release"}, k = 1, mv = {1, 6, 0}, xi = 48)
public interface ArtistMerchDataSource {
    Observable<Optional<ArtistMerchQuery.Artist>> getArtist(String artistId);
}
