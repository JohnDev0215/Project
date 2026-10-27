package com.amazon.music.curate.data;

import android.view.ContextMenu;
import android.view.View;
import androidx.fragment.app.FragmentActivity;
import androidx.paging.PageKeyedDataSource;
import com.amazon.mp3.environment.Environment;
import com.amazon.music.curate.R;
import com.amazon.music.curate.data.database.PlaylistDB;
import com.amazon.music.curate.data.database.RecsCache;
import com.amazon.music.curate.model.AddButtonStyle;
import com.amazon.music.curate.model.DetailPageHeader;
import com.amazon.music.curate.model.EmptyDetailPageHeader;
import com.amazon.music.curate.model.Playlist;
import com.amazon.music.curate.model.Track;
import com.amazon.music.curate.model.VisualModel;
import com.amazon.music.curate.model.VisualRowHeader;
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
import kotlin.collections.CollectionsKt__CollectionsJVMKt;
import kotlin.collections.CollectionsKt__CollectionsKt;
import kotlin.collections.CollectionsKt__IterablesKt;
import kotlin.collections.CollectionsKt___CollectionsKt;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;
import rx.Observable;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;
import rx.functions.Func1;
import rx.schedulers.Schedulers;

/* JADX INFO: compiled from: PlaylistDetailDataSourceFactory.kt */
/* JADX INFO: loaded from: classes4.dex */
@Metadata(d1 = {"\u0000_\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\b\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010 \n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003*\u0001\u0000\b\n\u0018\u00002\u000e\u0012\u0004\u0012\u00020\u0002\u0012\u0004\u0012\u00020\u00030\u0001J,\u0010\u000f\u001a\b\u0012\u0004\u0012\u00020\u00030\u00102\u0006\u0010\r\u001a\u00020\u000e2\f\u0010\u0011\u001a\b\u0012\u0004\u0012\u00020\u00060\u00102\u0006\u0010\u0012\u001a\u00020\u0002H\u0002J\n\u0010\u0013\u001a\u0004\u0018\u00010\u0014H\u0002J*\u0010\u0015\u001a\u00020\u00162\f\u0010\u0017\u001a\b\u0012\u0004\u0012\u00020\u00020\u00182\u0012\u0010\u0019\u001a\u000e\u0012\u0004\u0012\u00020\u0002\u0012\u0004\u0012\u00020\u00030\u001aH\u0016J*\u0010\u001b\u001a\u00020\u00162\f\u0010\u0017\u001a\b\u0012\u0004\u0012\u00020\u00020\u00182\u0012\u0010\u0019\u001a\u000e\u0012\u0004\u0012\u00020\u0002\u0012\u0004\u0012\u00020\u00030\u001aH\u0016J*\u0010\u001c\u001a\u00020\u00162\f\u0010\u0017\u001a\b\u0012\u0004\u0012\u00020\u00020\u001d2\u0012\u0010\u0019\u001a\u000e\u0012\u0004\u0012\u00020\u0002\u0012\u0004\u0012\u00020\u00030\u001eH\u0016J\u0010\u0010\u001f\u001a\u00020\u00162\u0006\u0010 \u001a\u00020\fH\u0002R\u0016\u0010\u0004\u001a\n\u0012\u0006\b\u0000\u0012\u00020\u00060\u0005X\u0082.¢\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\bX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\t\u001a\u00020\bX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\n\u001a\u00020\u0002X\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\u000b\u001a\u0004\u0018\u00010\fX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\r\u001a\u00020\u000eX\u0082.¢\u0006\u0002\n\u0000¨\u0006!"}, d2 = {"com/amazon/music/curate/data/PlaylistDetailDataSourceFactory$create$dataSource$1", "Landroidx/paging/PageKeyedDataSource;", "", "Lcom/amazon/music/curate/model/VisualModel;", "appendSubscriber", "Lrx/Subscriber;", "Lcom/amazon/music/curate/model/Track;", "hasMorePages", "", "isRequestInProgress", "numTracksLoaded", "pageToken", "", Environment.PLAYLIST_PATH, "Lcom/amazon/music/curate/model/Playlist;", "createVisualItemsList", "", "tracks", "startIdx", "getRecommendedTracks", "Lcom/amazon/music/curate/data/database/RecsCache$Entry;", "loadAfter", "", "params", "Landroidx/paging/PageKeyedDataSource$LoadParams;", "callback", "Landroidx/paging/PageKeyedDataSource$LoadCallback;", "loadBefore", "loadInitial", "Landroidx/paging/PageKeyedDataSource$LoadInitialParams;", "Landroidx/paging/PageKeyedDataSource$LoadInitialCallback;", "requestAndSaveData", "playlistId", "DMMCurateExperience_release"}, k = 1, mv = {1, 6, 0}, xi = 48)
public final class PlaylistDetailDataSourceFactory$create$dataSource$1 extends PageKeyedDataSource<Integer, VisualModel> {
    private Subscriber<? super Track> appendSubscriber;
    private boolean hasMorePages;
    private boolean isRequestInProgress;
    private int numTracksLoaded;
    private String pageToken;
    private Playlist playlist;
    final /* synthetic */ PlaylistDetailDataSourceFactory this$0;

    PlaylistDetailDataSourceFactory$create$dataSource$1(final PlaylistDetailDataSourceFactory playlistDetailDataSourceFactory) {
        this.this$0 = playlistDetailDataSourceFactory;
        Observable.create(new Observable.OnSubscribe() { // from class: com.amazon.music.curate.data.PlaylistDetailDataSourceFactory$create$dataSource$1$$ExternalSyntheticLambda0
            @Override // rx.functions.Action1
            public final void call(Object obj) {
                PlaylistDetailDataSourceFactory$create$dataSource$1.m1912_init_$lambda0(this.f$0, playlistDetailDataSourceFactory, (Subscriber) obj);
            }
        }).buffer(1L, TimeUnit.SECONDS).filter(new Func1() { // from class: com.amazon.music.curate.data.PlaylistDetailDataSourceFactory$create$dataSource$1$$ExternalSyntheticLambda1
            @Override // rx.functions.Func1
            public final Object call(Object obj) {
                return PlaylistDetailDataSourceFactory$create$dataSource$1.m1913_init_$lambda1((List) obj);
            }
        }).map(new Func1() { // from class: com.amazon.music.curate.data.PlaylistDetailDataSourceFactory$create$dataSource$1$$ExternalSyntheticLambda2
            @Override // rx.functions.Func1
            public final Object call(Object obj) {
                return PlaylistDetailDataSourceFactory$create$dataSource$1.m1914_init_$lambda2(playlistDetailDataSourceFactory, this, (List) obj);
            }
        }).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe(new Action1() { // from class: com.amazon.music.curate.data.PlaylistDetailDataSourceFactory$create$dataSource$1$$ExternalSyntheticLambda3
            @Override // rx.functions.Action1
            public final void call(Object obj) {
                PlaylistDetailDataSourceFactory$create$dataSource$1.m1915_init_$lambda3(this.f$0, playlistDetailDataSourceFactory, (List) obj);
            }
        }, new Action1() { // from class: com.amazon.music.curate.data.PlaylistDetailDataSourceFactory$create$dataSource$1$$ExternalSyntheticLambda4
            @Override // rx.functions.Action1
            public final void call(Object obj) {
                PlaylistDetailDataSourceFactory$create$dataSource$1.m1916_init_$lambda4((Throwable) obj);
            }
        });
        this.hasMorePages = true;
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: _init_$lambda-0, reason: not valid java name */
    public static final void m1912_init_$lambda0(PlaylistDetailDataSourceFactory$create$dataSource$1 this$0, PlaylistDetailDataSourceFactory this$1, Subscriber it) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        Intrinsics.checkNotNullParameter(this$1, "this$1");
        this$0.playlist = this$1.cache.getPlaylist(this$1.playlistId);
        Intrinsics.checkNotNullExpressionValue(it, "it");
        this$0.appendSubscriber = it;
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: _init_$lambda-1, reason: not valid java name */
    public static final Boolean m1913_init_$lambda1(List list) {
        Intrinsics.checkNotNullExpressionValue(list, "list");
        return Boolean.valueOf(!list.isEmpty());
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: _init_$lambda-2, reason: not valid java name */
    public static final List m1914_init_$lambda2(PlaylistDetailDataSourceFactory this$0, PlaylistDetailDataSourceFactory$create$dataSource$1 this$1, List tracks) {
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
    public static final void m1915_init_$lambda3(PlaylistDetailDataSourceFactory$create$dataSource$1 this$0, PlaylistDetailDataSourceFactory this$1, List tracks) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        Intrinsics.checkNotNullParameter(this$1, "this$1");
        Subscriber<? super Track> subscriber = this$0.appendSubscriber;
        if (subscriber == null) {
            Intrinsics.throwUninitializedPropertyAccessException("appendSubscriber");
            subscriber = null;
        }
        subscriber.onCompleted();
        RecsCache recsCache = this$1.recsCache;
        Intrinsics.checkNotNull(recsCache);
        String str = this$1.playlistId;
        Intrinsics.checkNotNullExpressionValue(tracks, "tracks");
        recsCache.removeRecs(str, tracks);
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: _init_$lambda-4, reason: not valid java name */
    public static final void m1916_init_$lambda4(Throwable th) {
        PlaylistDetailDataSourceFactory.LOG.error("Failed to insert track");
    }

    @Override // androidx.paging.PageKeyedDataSource
    public void loadInitial(PageKeyedDataSource.LoadInitialParams<Integer> params, PageKeyedDataSource.LoadInitialCallback<Integer, VisualModel> callback) {
        Intrinsics.checkNotNullParameter(params, "params");
        Intrinsics.checkNotNullParameter(callback, "callback");
        PlaylistDetailDataSourceFactory.LOG.debug("loadInitial()");
        this.playlist = this.this$0.cache.getPlaylist(this.this$0.playlistId);
        Playlist playlist = null;
        if (this.this$0.isEmptyPlaylist == null) {
            PlaylistDetailDataSourceFactory playlistDetailDataSourceFactory = this.this$0;
            Playlist playlist2 = this.playlist;
            if (playlist2 == null) {
                Intrinsics.throwUninitializedPropertyAccessException(Environment.PLAYLIST_PATH);
                playlist2 = null;
            }
            playlistDetailDataSourceFactory.isEmptyPlaylist = Boolean.valueOf(playlist2.getNumSongs() == 0);
        }
        Boolean bool = this.this$0.isEmptyPlaylist;
        Intrinsics.checkNotNull(bool);
        if (bool.booleanValue()) {
            Playlist playlist3 = this.playlist;
            if (playlist3 == null) {
                Intrinsics.throwUninitializedPropertyAccessException(Environment.PLAYLIST_PATH);
                playlist3 = null;
            }
            if (playlist3.getNumSongs() > 0 && !Intrinsics.areEqual(this.this$0.fragmentSessionId, this.this$0.onGetFragmentSessionId.invoke())) {
                this.this$0.isEmptyPlaylist = Boolean.FALSE;
                RecsCache recsCache = this.this$0.recsCache;
                if (recsCache != null) {
                    recsCache.removeAllRecs(this.this$0.playlistId);
                }
            }
        }
        PlaylistDetailDataSourceFactory playlistDetailDataSourceFactory2 = this.this$0;
        playlistDetailDataSourceFactory2.fragmentSessionId = (String) playlistDetailDataSourceFactory2.onGetFragmentSessionId.invoke();
        List<Track> tracksForPlaylist = this.this$0.cache.getTracksForPlaylist(this.this$0.playlistId, params.requestedLoadSize, 0);
        this.numTracksLoaded += tracksForPlaylist.size();
        PlaylistDetailDataSourceFactory.LOG.debug("loadInitial(): Got " + tracksForPlaylist.size() + " tracks, requested " + params.requestedLoadSize);
        Playlist playlist4 = this.playlist;
        if (playlist4 == null) {
            Intrinsics.throwUninitializedPropertyAccessException(Environment.PLAYLIST_PATH);
            playlist4 = null;
        }
        List<VisualModel> listCreateVisualItemsList = createVisualItemsList(playlist4, tracksForPlaylist, 0);
        int i = params.requestedLoadSize;
        Playlist playlist5 = this.playlist;
        if (playlist5 == null) {
            Intrinsics.throwUninitializedPropertyAccessException(Environment.PLAYLIST_PATH);
            playlist5 = null;
        }
        int iMin = Math.min(i, playlist5.getNumSongs());
        if (tracksForPlaylist.size() < iMin) {
            PlaylistDetailDataSourceFactory.LOG.debug("Requesting more data. tracks size is " + tracksForPlaylist.size() + ", expected result size " + iMin);
            requestAndSaveData(this.this$0.playlistId);
            return;
        }
        Playlist playlist6 = this.playlist;
        if (playlist6 == null) {
            Intrinsics.throwUninitializedPropertyAccessException(Environment.PLAYLIST_PATH);
        } else {
            playlist = playlist6;
        }
        int numSongs = playlist.getNumSongs() + 1;
        if (this.this$0.recsCache != null) {
            RecsCache.Entry recommendedTracks = getRecommendedTracks();
            if (recommendedTracks == null) {
                Boolean bool2 = this.this$0.isEmptyPlaylist;
                Intrinsics.checkNotNull(bool2);
                if (bool2.booleanValue()) {
                    numSongs++;
                }
            }
            if (recommendedTracks != null && (!recommendedTracks.getTracks().isEmpty())) {
                numSongs += Math.min(recommendedTracks.getTracks().size(), 15) + 1;
                this.this$0.recsLoadedLiveData.postValue(Boolean.valueOf(recommendedTracks.getType() == RecsCache.Type.RECOMMENDATIONS));
            }
        }
        int size = tracksForPlaylist.size() < params.requestedLoadSize ? -1 : tracksForPlaylist.size();
        PlaylistDetailDataSourceFactory.LOG.debug("loadInitial(): Returning expected size " + numSongs + ", next token " + size);
        callback.onResult(listCreateVisualItemsList, 0, Math.max(listCreateVisualItemsList.size(), numSongs), null, Integer.valueOf(size));
    }

    private final RecsCache.Entry getRecommendedTracks() {
        Boolean bool;
        RecsCache.Entry entry;
        if (this.this$0.recsCache == null) {
            return null;
        }
        RecsCache.Entry recs = this.this$0.recsCache.getRecs(this.this$0.playlistId);
        if (recs != null && !recs.getTracks().isEmpty()) {
            return recs;
        }
        try {
            bool = this.this$0.isEmptyPlaylist;
            Intrinsics.checkNotNull(bool);
        } catch (ServiceException e) {
            e = e;
        }
        try {
            if (!bool.booleanValue()) {
                if (!this.this$0.featureFlagProvider.isPlaylistDetailRecommendationsEnabled()) {
                    return recs;
                }
                List<Track> tracks = this.this$0.service.getUserPlaylistRecs(this.this$0.playlistId, this.this$0.subscriptionProvider.getContentSubscriptionMode()).getTracks();
                HashSet hashSet = new HashSet();
                ArrayList arrayList = new ArrayList();
                for (Object obj : tracks) {
                    if (hashSet.add(((Track) obj).getAsin())) {
                        arrayList.add(obj);
                    }
                }
                entry = new RecsCache.Entry(CollectionsKt___CollectionsKt.toMutableList((Collection) arrayList), RecsCache.Type.RECOMMENDATIONS);
                this.this$0.recsCache.putRecs(this.this$0.playlistId, entry);
            } else {
                if (recs != null) {
                    return recs;
                }
                List<Track> tracks2 = RecentsService.getRecents$default(this.this$0.recentsService, this.this$0.deviceInfoProvider.getDeviceIdentity().getDeviceTypeId(), this.this$0.deviceInfoProvider.getDeviceIdentity().getDeviceId(), null, 4, null).getTracks();
                HashSet hashSet2 = new HashSet();
                ArrayList arrayList2 = new ArrayList();
                for (Object obj2 : tracks2) {
                    if (hashSet2.add(((Track) obj2).getAsin())) {
                        arrayList2.add(obj2);
                    }
                }
                entry = new RecsCache.Entry(CollectionsKt___CollectionsKt.toMutableList((Collection) arrayList2), RecsCache.Type.RECENTLY_PLAYED);
                this.this$0.recsCache.putRecs(this.this$0.playlistId, entry);
            }
            return entry;
        } catch (ServiceException e2) {
            e = e2;
            recs = entry;
            PlaylistDetailDataSourceFactory.LOG.error("Failed to get recs from service", (Throwable) e);
            return recs;
        }
    }

    private final void requestAndSaveData(String playlistId) {
        if (!this.hasMorePages || this.isRequestInProgress) {
            return;
        }
        this.isRequestInProgress = true;
        try {
            final GetUserPlaylistResponse userPlaylist = this.this$0.service.getUserPlaylist(playlistId, this.pageToken);
            this.this$0.cache.putTracksForPlaylist(userPlaylist.getTracks(), playlistId).subscribe(new Action1() { // from class: com.amazon.music.curate.data.PlaylistDetailDataSourceFactory$create$dataSource$1$$ExternalSyntheticLambda5
                @Override // rx.functions.Action1
                public final void call(Object obj) {
                    PlaylistDetailDataSourceFactory$create$dataSource$1.m1917requestAndSaveData$lambda7(this.f$0, userPlaylist, (Unit) obj);
                }
            });
            PlaylistDB.putPlaylists$default(this.this$0.cache, CollectionsKt__CollectionsJVMKt.listOf(userPlaylist.getPlaylist()), null, 2, null);
        } catch (ServiceException e) {
            PlaylistDetailDataSourceFactory.LOG.warn("Failed to get playlist tracks from service", (Throwable) e);
            this.isRequestInProgress = false;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: requestAndSaveData$lambda-7, reason: not valid java name */
    public static final void m1917requestAndSaveData$lambda7(PlaylistDetailDataSourceFactory$create$dataSource$1 this$0, GetUserPlaylistResponse response, Unit unit) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        Intrinsics.checkNotNullParameter(response, "$response");
        this$0.hasMorePages = response.getToken() != null;
        this$0.pageToken = response.getToken();
        this$0.isRequestInProgress = false;
    }

    @Override // androidx.paging.PageKeyedDataSource
    public void loadAfter(PageKeyedDataSource.LoadParams<Integer> params, PageKeyedDataSource.LoadCallback<Integer, VisualModel> callback) {
        VisualRowHeader visualRowHeader;
        Intrinsics.checkNotNullParameter(params, "params");
        Intrinsics.checkNotNullParameter(callback, "callback");
        PlaylistDetailDataSourceFactory.LOG.debug("loadAfter()");
        int iIntValue = -1;
        if (!Intrinsics.areEqual((Object) params.key, (Object) (-1))) {
            Playlist playlist = this.this$0.cache.getPlaylist(this.this$0.playlistId);
            List<Track> tracksForPlaylist = this.this$0.cache.getTracksForPlaylist(this.this$0.playlistId, params.requestedLoadSize, params.key.intValue());
            int iMin = Math.min(params.requestedLoadSize, playlist.getNumSongs() - this.numTracksLoaded);
            this.numTracksLoaded += tracksForPlaylist.size();
            PlaylistDetailDataSourceFactory.LOG.debug("loadAfter(): Got " + tracksForPlaylist.size() + " tracks at " + params.key + ", requested " + params.requestedLoadSize);
            if (tracksForPlaylist.size() < iMin) {
                requestAndSaveData(this.this$0.playlistId);
                return;
            }
            List<? extends VisualModel> listCreateVisualItemsList = createVisualItemsList(playlist, tracksForPlaylist, params.key.intValue());
            if (tracksForPlaylist.size() >= params.requestedLoadSize && this.numTracksLoaded != playlist.getNumSongs()) {
                iIntValue = params.key.intValue() + tracksForPlaylist.size();
            }
            callback.onResult(listCreateVisualItemsList, Integer.valueOf(iIntValue));
            return;
        }
        PlaylistDetailDataSourceFactory.LOG.debug("loadAfter(): Loading recs");
        if (this.this$0.recsCache == null) {
            return;
        }
        try {
            RecsCache.Entry recommendedTracks = getRecommendedTracks();
            if (recommendedTracks != null && !recommendedTracks.getTracks().isEmpty()) {
                RecsCache.Type type = recommendedTracks.getType();
                RecsCache.Type type2 = RecsCache.Type.RECOMMENDATIONS;
                if (type != type2 || this.this$0.featureFlagProvider.isPlaylistDetailRecommendationsEnabled()) {
                    if (recommendedTracks.getType() == RecsCache.Type.RECENTLY_PLAYED) {
                        Boolean bool = this.this$0.isEmptyPlaylist;
                        Intrinsics.checkNotNull(bool);
                        if (!bool.booleanValue()) {
                        }
                    }
                    List<Track> tracks = recommendedTracks.getTracks();
                    ArrayList arrayList = new ArrayList();
                    if (recommendedTracks.getType() == type2) {
                        String string = this.this$0.context.getResources().getString(R.string.see_more_button_text);
                        Intrinsics.checkNotNullExpressionValue(string, "context.resources.getStr…ing.see_more_button_text)");
                        final PlaylistDetailDataSourceFactory playlistDetailDataSourceFactory = this.this$0;
                        VisualRowHeader.Button button = new VisualRowHeader.Button(string, new Function0<Unit>() { // from class: com.amazon.music.curate.data.PlaylistDetailDataSourceFactory$create$dataSource$1$loadAfter$visualRowHeader$button$1
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
                                MetricsHolder.getManager().handleEvent(UiClickEvent.builder(ActionType.DETAIL_USER_PLAYLIST_SEE_MORE_RECS).withPageType(PageType.DETAIL_USER_PLAYLIST).withEntityId(playlistDetailDataSourceFactory.playlistId).withEntityIdType(EntityIdType.PLAYLIST_ID).withInteractionType(InteractionType.TAP).withEntityPos(-1).build());
                                playlistDetailDataSourceFactory.onSeeMoreClickAction.invoke(playlistDetailDataSourceFactory.playlistId);
                            }
                        });
                        String string2 = this.this$0.context.getResources().getString(R.string.suggested_songs_title);
                        Intrinsics.checkNotNullExpressionValue(string2, "context.resources.getStr…ng.suggested_songs_title)");
                        visualRowHeader = new VisualRowHeader(string2, this.this$0.context.getResources().getString(R.string.suggested_songs_sub_title), button);
                    } else {
                        String string3 = this.this$0.context.getResources().getString(R.string.recently_played_title);
                        Intrinsics.checkNotNullExpressionValue(string3, "context.resources.getStr…ng.recently_played_title)");
                        visualRowHeader = new VisualRowHeader(string3, null, null);
                    }
                    arrayList.add(visualRowHeader);
                    final int i = 0;
                    List<Track> listSubList = tracks.subList(0, Math.min(tracks.size(), 15));
                    ArrayList arrayList2 = new ArrayList(CollectionsKt__IterablesKt.collectionSizeOrDefault(listSubList, 10));
                    for (Object obj : listSubList) {
                        int i2 = i + 1;
                        if (i < 0) {
                            CollectionsKt__CollectionsKt.throwIndexOverflow();
                        }
                        final Track track = (Track) obj;
                        arrayList2.add(new VisualRowItem.Builder(track.getAsin(), track.getAlbumImageUrl(), track.getTitle()).secondaryText(track.getArtistName()).showDownloadStatus(true).addButton(new VisualRowItem.AddButton(AddButtonStyle.ADD_INLINE, new Function0<Unit>() { // from class: com.amazon.music.curate.data.PlaylistDetailDataSourceFactory$create$dataSource$1$loadAfter$1$1
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
                                MetricsHolder.getManager().handleEvent(UiClickEvent.builder(ActionType.ADD_TO_PLAYLIST).withPageType(PageType.DETAIL_USER_PLAYLIST).withEntityId(track.getAsin()).withEntityIdType(EntityIdType.ASIN).withInteractionType(InteractionType.TAP).withEntityPos(i).build());
                                if (this.isInvalid()) {
                                    return;
                                }
                                Subscriber subscriber = this.appendSubscriber;
                                if (subscriber == null) {
                                    Intrinsics.throwUninitializedPropertyAccessException("appendSubscriber");
                                    subscriber = null;
                                }
                                subscriber.onNext(track);
                            }
                        })).build());
                        i = i2;
                    }
                    arrayList.addAll(arrayList2);
                    PlaylistDetailDataSourceFactory.LOG.debug("loadAfter(): Adding " + arrayList.size() + " recs");
                    callback.onResult(arrayList, null);
                    return;
                }
            }
            callback.onResult(CollectionsKt__CollectionsKt.emptyList(), null);
        } catch (ServiceException e) {
            PlaylistDetailDataSourceFactory.LOG.error(Intrinsics.stringPlus("failed to get recs for playlist ", this.this$0.playlistId), (Throwable) e);
        }
    }

    @Override // androidx.paging.PageKeyedDataSource
    public void loadBefore(PageKeyedDataSource.LoadParams<Integer> params, PageKeyedDataSource.LoadCallback<Integer, VisualModel> callback) {
        Intrinsics.checkNotNullParameter(params, "params");
        Intrinsics.checkNotNullParameter(callback, "callback");
        throw new NotImplementedError(Intrinsics.stringPlus("An operation is not implemented: ", "not implemented"));
    }

    private final List<VisualModel> createVisualItemsList(final Playlist playlist, final List<Track> tracks, final int startIdx) {
        ArrayList arrayList = new ArrayList();
        if (startIdx == 0) {
            String id = playlist.getId();
            String string = this.this$0.context.getResources().getString(R.string.playlist_label);
            Intrinsics.checkNotNullExpressionValue(string, "context.resources.getStr…(R.string.playlist_label)");
            DetailPageHeader.Builder builder = new DetailPageHeader.Builder(id, string, playlist.getTitle());
            final PlaylistDetailDataSourceFactory playlistDetailDataSourceFactory = this.this$0;
            DetailPageHeader.Builder builderOnEditButtonClick = builder.onEditButtonClick(new Function0<Unit>() { // from class: com.amazon.music.curate.data.PlaylistDetailDataSourceFactory$create$dataSource$1$createVisualItemsList$builder$1
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
                    MetricsHolder.getManager().handleEvent(UiClickEvent.builder(ActionType.EDIT_PLAYLIST).withPageType(PageType.DETAIL_USER_PLAYLIST).withEntityId(playlistDetailDataSourceFactory.playlistId).withEntityIdType(EntityIdType.PLAYLIST_ID).withInteractionType(InteractionType.TAP).withEntityPos(-1).build());
                    Function1 function1 = playlistDetailDataSourceFactory.onEditButtonClick;
                    if (function1 == null) {
                        return;
                    }
                    function1.invoke(playlist);
                }
            });
            if (!tracks.isEmpty()) {
                final PlaylistDetailDataSourceFactory playlistDetailDataSourceFactory2 = this.this$0;
                DetailPageHeader.Builder builderOnPlayButtonClick = builderOnEditButtonClick.onPlayButtonClick(new Function0<Unit>() { // from class: com.amazon.music.curate.data.PlaylistDetailDataSourceFactory$create$dataSource$1$createVisualItemsList$1
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
                        MetricsHolder.getManager().handleEvent(UiClickEvent.builder(ActionType.PLAY_PLAYLIST).withPageType(PageType.DETAIL_USER_PLAYLIST).withEntityId(playlistDetailDataSourceFactory2.playlistId).withEntityIdType(EntityIdType.PLAYLIST_ID).withInteractionType(InteractionType.TAP).withEntityPos(-1).build());
                        playlistDetailDataSourceFactory2.onPlayButtonClick.invoke(playlist, tracks, 0);
                    }
                });
                final PlaylistDetailDataSourceFactory playlistDetailDataSourceFactory3 = this.this$0;
                DetailPageHeader.Builder builderOnShuffleButtonClick = builderOnPlayButtonClick.onShuffleButtonClick(new Function0<Unit>() { // from class: com.amazon.music.curate.data.PlaylistDetailDataSourceFactory$create$dataSource$1$createVisualItemsList$2
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
                        MetricsHolder.getManager().handleEvent(UiClickEvent.builder(ActionType.PLAY_SHUFFLE_ALL).withPageType(PageType.DETAIL_USER_PLAYLIST).withEntityId(playlistDetailDataSourceFactory3.playlistId).withEntityIdType(EntityIdType.PLAYLIST_ID).withInteractionType(InteractionType.TAP).withEntityPos(-1).build());
                        playlistDetailDataSourceFactory3.onShuffleButtonClick.mo4invoke(playlist, tracks);
                    }
                });
                final PlaylistDetailDataSourceFactory playlistDetailDataSourceFactory4 = this.this$0;
                DetailPageHeader.Builder builderOnDownloadButtonClick = builderOnShuffleButtonClick.onDownloadButtonClick(new Function0<Unit>() { // from class: com.amazon.music.curate.data.PlaylistDetailDataSourceFactory$create$dataSource$1$createVisualItemsList$3
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
                        MetricsHolder.getManager().handleEvent(UiClickEvent.builder(ActionType.DOWNLOAD_ALL).withPageType(PageType.DETAIL_USER_PLAYLIST).withEntityId(playlistDetailDataSourceFactory4.playlistId).withEntityIdType(EntityIdType.PLAYLIST_ID).withInteractionType(InteractionType.TAP).withEntityPos(-1).build());
                        playlistDetailDataSourceFactory4.onDownloadButtonClick.invoke(playlist);
                    }
                });
                final PlaylistDetailDataSourceFactory playlistDetailDataSourceFactory5 = this.this$0;
                builderOnDownloadButtonClick.onStationButtonClick(new Function0<Unit>() { // from class: com.amazon.music.curate.data.PlaylistDetailDataSourceFactory$create$dataSource$1$createVisualItemsList$4
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
                        playlistDetailDataSourceFactory5.onStationButtonClick.invoke(tracks);
                    }
                }).imageUrl(playlist.getArtworkUrl()).tertiaryText(this.this$0.getCountAndDurationText(playlist));
                if (this.this$0.featureFlagProvider.isUserPlaylistSharingEnabled()) {
                    final PlaylistDetailDataSourceFactory playlistDetailDataSourceFactory6 = this.this$0;
                    builderOnEditButtonClick.onShareButtonClick(new Function0<Unit>() { // from class: com.amazon.music.curate.data.PlaylistDetailDataSourceFactory$create$dataSource$1$createVisualItemsList$5
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
                            MetricsHolder.getManager().handleEvent(UiClickEvent.builder(ActionType.SHARE).withPageType(PageType.DETAIL_USER_PLAYLIST).withEntityId(playlistDetailDataSourceFactory6.playlistId).withEntityIdType(EntityIdType.PLAYLIST_ID).withInteractionType(InteractionType.TAP).withEntityPos(-1).build());
                            playlistDetailDataSourceFactory6.onShareButtonClick.invoke(playlist);
                        }
                    });
                }
            }
            arrayList.add(builderOnEditButtonClick.build());
            if (tracks.isEmpty()) {
                String string2 = this.this$0.context.getResources().getString(R.string.no_songs_added_header);
                Intrinsics.checkNotNullExpressionValue(string2, "context.resources.getStr…ng.no_songs_added_header)");
                String string3 = this.this$0.context.getResources().getString(R.string.no_songs_added_sub_text);
                Intrinsics.checkNotNullExpressionValue(string3, "context.resources.getStr….no_songs_added_sub_text)");
                arrayList.add(new EmptyDetailPageHeader(string2, string3));
            }
        }
        final PlaylistDetailDataSourceFactory playlistDetailDataSourceFactory7 = this.this$0;
        ArrayList arrayList2 = new ArrayList(CollectionsKt__IterablesKt.collectionSizeOrDefault(tracks, 10));
        final int i = 0;
        for (Object obj : tracks) {
            int i2 = i + 1;
            if (i < 0) {
                CollectionsKt__CollectionsKt.throwIndexOverflow();
            }
            final Track track = (Track) obj;
            final int i3 = i;
            arrayList2.add(new VisualRowItem.Builder(track.getAsin(), track.getAlbumImageUrl(), track.getTitle()).onClick(new Function0<Unit>() { // from class: com.amazon.music.curate.data.PlaylistDetailDataSourceFactory$create$dataSource$1$createVisualItemsList$6$1
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
                    MetricsHolder.getManager().handleEvent(UiClickEvent.builder(ActionType.PLAY_SONG).withPageType(PageType.DETAIL_USER_PLAYLIST).withEntityId(playlistDetailDataSourceFactory7.playlistId).withEntityIdType(EntityIdType.PLAYLIST_ID).withInteractionType(InteractionType.TAP).withEntityPos(-1).build());
                    playlistDetailDataSourceFactory7.onPlayButtonClick.invoke(playlist, tracks, Integer.valueOf(startIdx + i3));
                }
            }).secondaryText(track.getArtistName()).showDownloadStatus(true).onShowContextMenu(new Function2<ContextMenu, FragmentActivity, Unit>() { // from class: com.amazon.music.curate.data.PlaylistDetailDataSourceFactory$create$dataSource$1$createVisualItemsList$6$2
                /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                {
                    super(2);
                }

                @Override // kotlin.jvm.functions.Function2
                /* JADX INFO: renamed from: invoke */
                public /* bridge */ /* synthetic */ Unit mo4invoke(ContextMenu contextMenu, FragmentActivity fragmentActivity) {
                    invoke2(contextMenu, fragmentActivity);
                    return Unit.INSTANCE;
                }

                /* JADX INFO: renamed from: invoke, reason: avoid collision after fix types in other method */
                public final void invoke2(ContextMenu menu, FragmentActivity activity) {
                    Intrinsics.checkNotNullParameter(menu, "menu");
                    Intrinsics.checkNotNullParameter(activity, "activity");
                    playlistDetailDataSourceFactory7.overflowMenuProvider.showTrackOverflowMenu(playlistDetailDataSourceFactory7.playlistId, track, i, PageType.DETAIL_USER_PLAYLIST, menu, activity);
                }
            }).onMoreClick(new Function1<View, Unit>() { // from class: com.amazon.music.curate.data.PlaylistDetailDataSourceFactory$create$dataSource$1$createVisualItemsList$6$3
                {
                    super(1);
                }

                @Override // kotlin.jvm.functions.Function1
                public /* bridge */ /* synthetic */ Unit invoke(View view) {
                    invoke2(view);
                    return Unit.INSTANCE;
                }

                /* JADX INFO: renamed from: invoke, reason: avoid collision after fix types in other method */
                public final void invoke2(View it) {
                    Intrinsics.checkNotNullParameter(it, "it");
                    playlistDetailDataSourceFactory7.onMoreClickAction.invoke(it);
                }
            }).indexText(String.valueOf(startIdx + i + 1)).build());
            i = i2;
        }
        arrayList.addAll(arrayList2);
        return arrayList;
    }
}
