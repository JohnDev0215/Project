package com.amazon.music.alps.liveevent;

import androidx.core.app.NotificationCompat;
import com.amazon.music.alps.liveevent.converters.LineupGroupsPageGridConverter;
import com.amazon.music.alps.liveevent.converters.LiveEventConverterFactory;
import com.amazon.music.alps.liveevent.converters.LiveEventDetailPageGridConverter;
import com.amazon.music.alps.liveevent.data.ArtistMerchDataSource;
import com.amazon.music.alps.liveevent.data.LiveEventDataSource;
import com.amazon.music.alps.liveevent.ktx.LiveEventKt;
import com.amazon.music.find.utils.ContentUtils;
import com.amazon.music.platform.graphql.generated.ArtistMerchQuery;
import com.amazon.music.platform.graphql.generated.fragment.ArtistEmbedded;
import com.amazon.music.platform.graphql.generated.fragment.Lineup;
import com.amazon.music.platform.graphql.generated.fragment.LiveEvent;
import com.amazon.music.views.library.models.base.BaseViewContainerModel;
import com.amazon.music.views.library.models.base.BaseViewModel;
import io.reactivex.rxjava3.core.Observable;
import java.util.List;
import java.util.Optional;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt___CollectionsKt;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: LiveEventModelUseCase.kt */
/* JADX INFO: loaded from: classes4.dex */
@Metadata(d1 = {"\u0000H\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0002\b\u0002\u0018\u00002\u00020\u0001B\u001d\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0007¢\u0006\u0002\u0010\bJ\"\u0010\t\u001a\f\u0012\u0006\b\u0001\u0012\u00020\u000b\u0018\u00010\n2\u0006\u0010\f\u001a\u00020\r2\b\u0010\u000e\u001a\u0004\u0018\u00010\u000fJ\u001c\u0010\u0010\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u000f0\u00120\u00112\b\u0010\u0013\u001a\u0004\u0018\u00010\rJ\u0014\u0010\u0014\u001a\b\u0012\u0004\u0012\u00020\r0\u00112\u0006\u0010\u0015\u001a\u00020\u0016J\u0018\u0010\u0017\u001a\f\u0012\u0006\b\u0001\u0012\u00020\u000b\u0018\u00010\n2\u0006\u0010\f\u001a\u00020\rR\u000e\u0010\u0004\u001a\u00020\u0005X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0007X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u0018"}, d2 = {"Lcom/amazon/music/alps/liveevent/LiveEventModelUseCase;", "", "liveEventDataSource", "Lcom/amazon/music/alps/liveevent/data/LiveEventDataSource;", "artistMerchDataSource", "Lcom/amazon/music/alps/liveevent/data/ArtistMerchDataSource;", "liveEventConverterFactory", "Lcom/amazon/music/alps/liveevent/converters/LiveEventConverterFactory;", "(Lcom/amazon/music/alps/liveevent/data/LiveEventDataSource;Lcom/amazon/music/alps/liveevent/data/ArtistMerchDataSource;Lcom/amazon/music/alps/liveevent/converters/LiveEventConverterFactory;)V", "detail", "Lcom/amazon/music/views/library/models/base/BaseViewContainerModel;", "Lcom/amazon/music/views/library/models/base/BaseViewModel;", NotificationCompat.CATEGORY_EVENT, "Lcom/amazon/music/platform/graphql/generated/fragment/LiveEvent;", "artist", "Lcom/amazon/music/platform/graphql/generated/ArtistMerchQuery$Artist;", "getArtist", "Lio/reactivex/rxjava3/core/Observable;", "Ljava/util/Optional;", "liveEvent", "getLiveEventDetail", ContentUtils.KEY_EVENT_ID, "", "lineups", "DMMALPS_release"}, k = 1, mv = {1, 6, 0}, xi = 48)
public final class LiveEventModelUseCase {
    private ArtistMerchDataSource artistMerchDataSource;
    private final LiveEventConverterFactory liveEventConverterFactory;
    private final LiveEventDataSource liveEventDataSource;

    public LiveEventModelUseCase(LiveEventDataSource liveEventDataSource, ArtistMerchDataSource artistMerchDataSource, LiveEventConverterFactory liveEventConverterFactory) {
        Intrinsics.checkNotNullParameter(liveEventDataSource, "liveEventDataSource");
        Intrinsics.checkNotNullParameter(artistMerchDataSource, "artistMerchDataSource");
        Intrinsics.checkNotNullParameter(liveEventConverterFactory, "liveEventConverterFactory");
        this.liveEventDataSource = liveEventDataSource;
        this.artistMerchDataSource = artistMerchDataSource;
        this.liveEventConverterFactory = liveEventConverterFactory;
    }

    public final Observable<LiveEvent> getLiveEventDetail(String eventId) {
        Intrinsics.checkNotNullParameter(eventId, "eventId");
        return this.liveEventDataSource.getEvent(eventId);
    }

    public final Observable<Optional<ArtistMerchQuery.Artist>> getArtist(LiveEvent liveEvent) {
        Lineup lineup;
        List<Lineup.Artist> artists;
        Lineup.Artist artist;
        Lineup.Artist1 artist2;
        ArtistEmbedded artistEmbedded;
        if (liveEvent == null || LiveEventKt.isFestival(liveEvent)) {
            Observable<Optional<ArtistMerchQuery.Artist>> observableJust = Observable.just(Optional.empty());
            Intrinsics.checkNotNullExpressionValue(observableJust, "just(Optional.empty())");
            return observableJust;
        }
        List<Lineup> lineups = LiveEventKt.getLineups(liveEvent);
        String id = null;
        if (lineups != null && (lineup = (Lineup) CollectionsKt___CollectionsKt.getOrNull(lineups, 0)) != null && (artists = lineup.getArtists()) != null && (artist = (Lineup.Artist) CollectionsKt___CollectionsKt.getOrNull(artists, 0)) != null && (artist2 = artist.getArtist()) != null && (artistEmbedded = artist2.getArtistEmbedded()) != null) {
            id = artistEmbedded.getId();
        }
        if (!(id == null || id.length() == 0)) {
            return this.artistMerchDataSource.getArtist(id);
        }
        Observable<Optional<ArtistMerchQuery.Artist>> observableJust2 = Observable.just(Optional.empty());
        Intrinsics.checkNotNullExpressionValue(observableJust2, "just(Optional.empty())");
        return observableJust2;
    }

    public final BaseViewContainerModel<? extends BaseViewModel> detail(LiveEvent event, ArtistMerchQuery.Artist artist) {
        Intrinsics.checkNotNullParameter(event, "event");
        LiveEventDetailPageGridConverter liveEventDetailPageGridConverter = this.liveEventConverterFactory.getLiveEventDetailPageGridConverter();
        if (artist != null) {
            liveEventDetailPageGridConverter.setArtistMerch(artist);
        }
        return liveEventDetailPageGridConverter.convert2(event, (List<? extends BaseViewModel>) liveEventDetailPageGridConverter.getChildrenModels(event));
    }

    public final BaseViewContainerModel<? extends BaseViewModel> lineups(LiveEvent event) {
        Intrinsics.checkNotNullParameter(event, "event");
        LineupGroupsPageGridConverter lineupsPageGridConverter = this.liveEventConverterFactory.getLineupsPageGridConverter();
        return lineupsPageGridConverter.convert2(event, (List<? extends BaseViewModel>) lineupsPageGridConverter.getChildrenModels(event));
    }
}
