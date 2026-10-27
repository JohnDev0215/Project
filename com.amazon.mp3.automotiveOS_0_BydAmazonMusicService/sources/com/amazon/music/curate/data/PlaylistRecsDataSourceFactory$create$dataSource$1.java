package com.amazon.music.curate.data;

import androidx.paging.PageKeyedDataSource;
import com.amazon.mp3.environment.Environment;
import com.amazon.music.curate.data.database.PlaylistDB;
import com.amazon.music.curate.data.database.RecsCache;
import com.amazon.music.curate.model.AddButtonStyle;
import com.amazon.music.curate.model.Playlist;
import com.amazon.music.curate.model.Track;
import com.amazon.music.curate.model.VisualModel;
import com.amazon.music.curate.model.VisualRowItem;
import com.amazon.music.metrics.MetricsHolder;
import com.amazon.music.metrics.mts.event.definition.uiinteraction.UiClickEvent;
import com.amazon.music.metrics.mts.event.types.uiinteraction.ActionType;
import com.amazon.music.metrics.mts.event.types.uiinteraction.EntityIdType;
import com.amazon.music.metrics.mts.event.types.uiinteraction.InteractionType;
import com.amazon.music.metrics.mts.event.types.uiinteraction.PageType;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.concurrent.TimeUnit;
import kotlin.Metadata;
import kotlin.NotImplementedError;
import kotlin.Unit;
import kotlin.collections.CollectionsKt__CollectionsKt;
import kotlin.collections.CollectionsKt__IterablesKt;
import kotlin.collections.CollectionsKt___CollectionsKt;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Intrinsics;
import rx.Observable;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;
import rx.functions.Func1;
import rx.schedulers.Schedulers;

/* JADX INFO: compiled from: PlaylistRecsDataSourceFactory.kt */
/* JADX INFO: loaded from: classes4.dex */
@Metadata(d1 = {"\u0000C\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u000e\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000*\u0001\u0000\b\n\u0018\u00002\u000e\u0012\u0004\u0012\u00020\u0002\u0012\u0004\u0012\u00020\u00030\u0001J*\u0010\t\u001a\u00020\n2\f\u0010\u000b\u001a\b\u0012\u0004\u0012\u00020\u00020\f2\u0012\u0010\r\u001a\u000e\u0012\u0004\u0012\u00020\u0002\u0012\u0004\u0012\u00020\u00030\u000eH\u0016J*\u0010\u000f\u001a\u00020\n2\f\u0010\u000b\u001a\b\u0012\u0004\u0012\u00020\u00020\f2\u0012\u0010\r\u001a\u000e\u0012\u0004\u0012\u00020\u0002\u0012\u0004\u0012\u00020\u00030\u000eH\u0016J*\u0010\u0010\u001a\u00020\n2\f\u0010\u0011\u001a\b\u0012\u0004\u0012\u00020\u00020\u00122\u0012\u0010\u0013\u001a\u000e\u0012\u0004\u0012\u00020\u0002\u0012\u0004\u0012\u00020\u00030\u0014H\u0016R\u0018\u0010\u0004\u001a\f\u0012\u0006\b\u0000\u0012\u00020\u0006\u0018\u00010\u0005X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\bX\u0082.¢\u0006\u0002\n\u0000¨\u0006\u0015"}, d2 = {"com/amazon/music/curate/data/PlaylistRecsDataSourceFactory$create$dataSource$1", "Landroidx/paging/PageKeyedDataSource;", "", "Lcom/amazon/music/curate/model/VisualModel;", "appendSubscriber", "Lrx/Subscriber;", "Lcom/amazon/music/curate/model/Track;", Environment.PLAYLIST_PATH, "Lcom/amazon/music/curate/model/Playlist;", "loadAfter", "", "p0", "Landroidx/paging/PageKeyedDataSource$LoadParams;", "p1", "Landroidx/paging/PageKeyedDataSource$LoadCallback;", "loadBefore", "loadInitial", "params", "Landroidx/paging/PageKeyedDataSource$LoadInitialParams;", "callback", "Landroidx/paging/PageKeyedDataSource$LoadInitialCallback;", "DMMCurateExperience_release"}, k = 1, mv = {1, 6, 0}, xi = 48)
public final class PlaylistRecsDataSourceFactory$create$dataSource$1 extends PageKeyedDataSource<String, VisualModel> {
    private Subscriber<? super Track> appendSubscriber;
    private Playlist playlist;
    final /* synthetic */ PlaylistRecsDataSourceFactory this$0;

    PlaylistRecsDataSourceFactory$create$dataSource$1(final PlaylistRecsDataSourceFactory playlistRecsDataSourceFactory) {
        this.this$0 = playlistRecsDataSourceFactory;
        Observable.create(new Observable.OnSubscribe() { // from class: com.amazon.music.curate.data.PlaylistRecsDataSourceFactory$create$dataSource$1$$ExternalSyntheticLambda0
            @Override // rx.functions.Action1
            public final void call(Object obj) {
                PlaylistRecsDataSourceFactory$create$dataSource$1.m1925_init_$lambda0(this.f$0, playlistRecsDataSourceFactory, (Subscriber) obj);
            }
        }).buffer(1L, TimeUnit.SECONDS).filter(new Func1() { // from class: com.amazon.music.curate.data.PlaylistRecsDataSourceFactory$create$dataSource$1$$ExternalSyntheticLambda1
            @Override // rx.functions.Func1
            public final Object call(Object obj) {
                return PlaylistRecsDataSourceFactory$create$dataSource$1.m1926_init_$lambda1((List) obj);
            }
        }).map(new Func1() { // from class: com.amazon.music.curate.data.PlaylistRecsDataSourceFactory$create$dataSource$1$$ExternalSyntheticLambda2
            @Override // rx.functions.Func1
            public final Object call(Object obj) {
                return PlaylistRecsDataSourceFactory$create$dataSource$1.m1927_init_$lambda2(playlistRecsDataSourceFactory, this, (List) obj);
            }
        }).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe(new Action1() { // from class: com.amazon.music.curate.data.PlaylistRecsDataSourceFactory$create$dataSource$1$$ExternalSyntheticLambda3
            @Override // rx.functions.Action1
            public final void call(Object obj) {
                PlaylistRecsDataSourceFactory$create$dataSource$1.m1928_init_$lambda3(this.f$0, playlistRecsDataSourceFactory, (List) obj);
            }
        }, new Action1() { // from class: com.amazon.music.curate.data.PlaylistRecsDataSourceFactory$create$dataSource$1$$ExternalSyntheticLambda4
            @Override // rx.functions.Action1
            public final void call(Object obj) {
                PlaylistRecsDataSourceFactory$create$dataSource$1.m1929_init_$lambda4((Throwable) obj);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: _init_$lambda-0, reason: not valid java name */
    public static final void m1925_init_$lambda0(PlaylistRecsDataSourceFactory$create$dataSource$1 this$0, PlaylistRecsDataSourceFactory this$1, Subscriber subscriber) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        Intrinsics.checkNotNullParameter(this$1, "this$1");
        this$0.playlist = this$1.cache.getPlaylist(this$1.playlistId);
        this$0.appendSubscriber = subscriber;
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: _init_$lambda-1, reason: not valid java name */
    public static final Boolean m1926_init_$lambda1(List list) {
        Intrinsics.checkNotNullExpressionValue(list, "list");
        return Boolean.valueOf(!list.isEmpty());
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: _init_$lambda-2, reason: not valid java name */
    public static final List m1927_init_$lambda2(PlaylistRecsDataSourceFactory this$0, PlaylistRecsDataSourceFactory$create$dataSource$1 this$1, List tracks) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        Intrinsics.checkNotNullParameter(this$1, "this$1");
        PlaylistDB playlistDB = this$0.cache;
        Playlist playlist = this$1.playlist;
        if (playlist == null) {
            Intrinsics.throwUninitializedPropertyAccessException(Environment.PLAYLIST_PATH);
            playlist = null;
        }
        String id = playlist.getId();
        Intrinsics.checkNotNullExpressionValue(tracks, "tracks");
        playlistDB.appendTracksToPlaylist(id, tracks);
        return tracks;
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: _init_$lambda-3, reason: not valid java name */
    public static final void m1928_init_$lambda3(PlaylistRecsDataSourceFactory$create$dataSource$1 this$0, PlaylistRecsDataSourceFactory this$1, List tracks) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        Intrinsics.checkNotNullParameter(this$1, "this$1");
        Subscriber<? super Track> subscriber = this$0.appendSubscriber;
        if (subscriber != null) {
            subscriber.onCompleted();
        }
        RecsCache recsCache = this$1.recsCache;
        String str = this$1.playlistId;
        Intrinsics.checkNotNullExpressionValue(tracks, "tracks");
        recsCache.removeRecs(str, tracks);
        PlaylistRecsDataSourceFactory.INSTANCE.getLOG().debug("Invalidating data source");
        this$0.invalidate();
        this$1.onRecAdded.invoke();
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: _init_$lambda-4, reason: not valid java name */
    public static final void m1929_init_$lambda4(Throwable th) {
        PlaylistRecsDataSourceFactory.INSTANCE.getLOG().error("Failed to insert track");
    }

    @Override // androidx.paging.PageKeyedDataSource
    public void loadInitial(PageKeyedDataSource.LoadInitialParams<String> params, PageKeyedDataSource.LoadInitialCallback<String, VisualModel> callback) {
        List<Track> tracks;
        List<? extends VisualModel> arrayList;
        Intrinsics.checkNotNullParameter(params, "params");
        Intrinsics.checkNotNullParameter(callback, "callback");
        try {
            RecsCache.Entry recs = this.this$0.recsCache.getRecs(this.this$0.playlistId);
            if (recs == null || recs.getTracks().isEmpty()) {
                try {
                    List<Track> tracks2 = this.this$0.service.getUserPlaylistRecs(this.this$0.playlistId, this.this$0.subscriptionProvider.getContentSubscriptionMode()).getTracks();
                    HashSet hashSet = new HashSet();
                    ArrayList arrayList2 = new ArrayList();
                    for (Object obj : tracks2) {
                        if (hashSet.add(((Track) obj).getAsin())) {
                            arrayList2.add(obj);
                        }
                    }
                    RecsCache.Entry entry = new RecsCache.Entry(CollectionsKt___CollectionsKt.toMutableList((Collection) arrayList2), RecsCache.Type.RECOMMENDATIONS);
                    try {
                        this.this$0.recsCache.putRecs(this.this$0.playlistId, entry);
                        recs = entry;
                    } catch (ServiceException e) {
                        e = e;
                        recs = entry;
                        PlaylistRecsDataSourceFactory.INSTANCE.getLOG().error("Failed to get recs from service", (Throwable) e);
                    }
                } catch (ServiceException e2) {
                    e = e2;
                }
            }
            if (recs == null || (tracks = recs.getTracks()) == null) {
                arrayList = null;
            } else {
                arrayList = new ArrayList<>(CollectionsKt__IterablesKt.collectionSizeOrDefault(tracks, 10));
                final int i = 0;
                for (Object obj2 : tracks) {
                    int i2 = i + 1;
                    if (i < 0) {
                        CollectionsKt__CollectionsKt.throwIndexOverflow();
                    }
                    final Track track = (Track) obj2;
                    arrayList.add(new VisualRowItem.Builder(track.getAsin(), track.getAlbumImageUrl(), track.getTitle()).secondaryText(track.getArtistName()).addButton(new VisualRowItem.AddButton(AddButtonStyle.ADD_INLINE, new Function0<Unit>() { // from class: com.amazon.music.curate.data.PlaylistRecsDataSourceFactory$create$dataSource$1$loadInitial$list$1$1
                        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                        {
                            super(0);
                        }

                        @Override // kotlin.jvm.functions.Function0
                        public /* bridge */ /* synthetic */ Unit invoke() {
                            invoke2();
                            return Unit.INSTANCE;
                        }

                        /* JADX INFO: renamed from: invoke, reason: avoid collision after fix types in other method */
                        public final void invoke2() {
                            Subscriber subscriber;
                            MetricsHolder.getManager().handleEvent(UiClickEvent.builder(ActionType.ADD_TO_PLAYLIST).withPageType(PageType.DETAIL_USER_PLAYLIST_SEE_MORE_RECS).withEntityId(track.getAsin()).withEntityIdType(EntityIdType.ASIN).withInteractionType(InteractionType.TAP).withEntityPos(i).build());
                            if (this.isInvalid() || (subscriber = this.appendSubscriber) == null) {
                                return;
                            }
                            subscriber.onNext(track);
                        }
                    })).build());
                    i = i2;
                }
            }
            if (arrayList == null) {
                arrayList = CollectionsKt__CollectionsKt.emptyList();
            }
            callback.onResult(arrayList, null, null);
        } catch (ServiceException e3) {
            PlaylistRecsDataSourceFactory.INSTANCE.getLOG().error(Intrinsics.stringPlus("Failed to load recs for playlist ", this.this$0.playlistId), (Throwable) e3);
        }
    }

    @Override // androidx.paging.PageKeyedDataSource
    public void loadAfter(PageKeyedDataSource.LoadParams<String> p0, PageKeyedDataSource.LoadCallback<String, VisualModel> p1) {
        Intrinsics.checkNotNullParameter(p0, "p0");
        Intrinsics.checkNotNullParameter(p1, "p1");
        throw new NotImplementedError(Intrinsics.stringPlus("An operation is not implemented: ", "not implemented"));
    }

    @Override // androidx.paging.PageKeyedDataSource
    public void loadBefore(PageKeyedDataSource.LoadParams<String> p0, PageKeyedDataSource.LoadCallback<String, VisualModel> p1) {
        Intrinsics.checkNotNullParameter(p0, "p0");
        Intrinsics.checkNotNullParameter(p1, "p1");
        throw new NotImplementedError(Intrinsics.stringPlus("An operation is not implemented: ", "not implemented"));
    }
}
