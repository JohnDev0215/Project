package com.amazon.music.curate.data.database;

import androidx.core.app.NotificationCompat;
import androidx.lifecycle.LiveData;
import androidx.paging.DataSource;
import com.amazon.music.curate.data.GetUserPlaylistResponse;
import com.amazon.music.curate.data.PlaylistService;
import com.amazon.music.curate.data.ServiceException;
import com.amazon.music.curate.model.Playlist;
import com.amazon.music.curate.model.PlaylistTrack;
import com.amazon.music.curate.model.Track;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.collections.CollectionsKt__CollectionsJVMKt;
import kotlin.collections.CollectionsKt__CollectionsKt;
import kotlin.collections.CollectionsKt__IterablesKt;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Intrinsics;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import rx.Observable;
import rx.Scheduler;
import rx.Subscriber;
import rx.functions.Action1;
import rx.schedulers.Schedulers;

/* JADX INFO: compiled from: PlaylistDB.kt */
/* JADX INFO: loaded from: classes4.dex */
@Metadata(d1 = {"\u0000n\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\b\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\b\n\u0002\u0018\u0002\n\u0002\b\u0007\u0018\u0000 /2\u00020\u0001:\u0001/B-\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0007\u0012\u0006\u0010\b\u001a\u00020\t\u0012\u0006\u0010\n\u001a\u00020\u000b¢\u0006\u0002\u0010\fJ\u001c\u0010\u0011\u001a\u00020\u00122\u0006\u0010\u0013\u001a\u00020\u00142\f\u0010\u0015\u001a\b\u0012\u0004\u0012\u00020\u00170\u0016J\f\u0010\u0018\u001a\b\u0012\u0004\u0012\u00020\u00120\u0019J\u0012\u0010\u001a\u001a\u000e\u0012\u0004\u0012\u00020\u001c\u0012\u0004\u0012\u00020\u001d0\u001bJ\u000e\u0010\u001e\u001a\u00020\u001d2\u0006\u0010\u0013\u001a\u00020\u0014J\u0014\u0010\u001f\u001a\b\u0012\u0004\u0012\u00020\u001d0 2\u0006\u0010\u0013\u001a\u00020\u0014J\u001a\u0010!\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00170\u00160 2\u0006\u0010\u0013\u001a\u00020\u0014J\u0012\u0010\"\u001a\u000e\u0012\u0004\u0012\u00020\u001c\u0012\u0004\u0012\u00020\u001d0\u001bJ$\u0010#\u001a\b\u0012\u0004\u0012\u00020\u00170\u00162\u0006\u0010\u0013\u001a\u00020\u00142\u0006\u0010$\u001a\u00020\u001c2\u0006\u0010%\u001a\u00020\u001cJ&\u0010&\u001a\u00020\u00122\f\u0010'\u001a\b\u0012\u0004\u0012\u00020\u001d0\u00162\u0010\b\u0002\u0010(\u001a\n\u0012\u0004\u0012\u00020\u0012\u0018\u00010)J\"\u0010*\u001a\b\u0012\u0004\u0012\u00020\u00120\u00192\f\u0010\u0015\u001a\b\u0012\u0004\u0012\u00020\u00170\u00162\u0006\u0010\u0013\u001a\u00020\u0014J\u0014\u0010+\u001a\b\u0012\u0004\u0012\u00020\u00120\u00192\u0006\u0010\u0013\u001a\u00020\u0014J\u001e\u0010,\u001a\u00020\u00122\u0006\u0010\u0013\u001a\u00020\u00142\u0006\u0010-\u001a\u00020\u00142\u0006\u0010.\u001a\u00020\u001cR\u000e\u0010\b\u001a\u00020\tX\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0007X\u0082\u0004¢\u0006\u0002\n\u0000R\u0016\u0010\r\u001a\n \u000f*\u0004\u0018\u00010\u000e0\u000eX\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\n\u001a\u00020\u000bX\u0082\u0004¢\u0006\u0002\n\u0000R\u0016\u0010\u0010\u001a\n \u000f*\u0004\u0018\u00010\u000e0\u000eX\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000¨\u00060"}, d2 = {"Lcom/amazon/music/curate/data/database/PlaylistDB;", "", "trackDao", "Lcom/amazon/music/curate/data/database/TrackDao;", "playlistDao", "Lcom/amazon/music/curate/data/database/PlaylistDao;", "playlistTrackDao", "Lcom/amazon/music/curate/data/database/PlaylistTrackDao;", "libraryContextMappingDao", "Lcom/amazon/music/curate/data/database/LibraryContextMappingDao;", NotificationCompat.CATEGORY_SERVICE, "Lcom/amazon/music/curate/data/PlaylistService;", "(Lcom/amazon/music/curate/data/database/TrackDao;Lcom/amazon/music/curate/data/database/PlaylistDao;Lcom/amazon/music/curate/data/database/PlaylistTrackDao;Lcom/amazon/music/curate/data/database/LibraryContextMappingDao;Lcom/amazon/music/curate/data/PlaylistService;)V", "scheduler", "Lrx/Scheduler;", "kotlin.jvm.PlatformType", "serviceScheduler", "appendTracksToPlaylist", "", "playlistId", "", "tracks", "", "Lcom/amazon/music/curate/model/Track;", "deletePersonalizedPlaylists", "Lrx/Observable;", "getPersonalizedPlaylists", "Landroidx/paging/DataSource$Factory;", "", "Lcom/amazon/music/curate/model/Playlist;", "getPlaylist", "getPlaylistLiveData", "Landroidx/lifecycle/LiveData;", "getPlaylistTracksLiveData", "getPlaylists", "getTracksForPlaylist", "limit", "offset", "putPlaylists", "playlists", "insertFinished", "Lkotlin/Function0;", "putTracksForPlaylist", "removePlaylist", "removeTrackFromPlaylist", "trackId", "index", "Companion", "DMMCurateExperience_release"}, k = 1, mv = {1, 6, 0}, xi = 48)
public final class PlaylistDB {
    private static final Logger LOG = LoggerFactory.getLogger(PlaylistDB.class.getSimpleName());
    private final LibraryContextMappingDao libraryContextMappingDao;
    private final PlaylistDao playlistDao;
    private final PlaylistTrackDao playlistTrackDao;
    private final Scheduler scheduler;
    private final PlaylistService service;
    private final Scheduler serviceScheduler;
    private final TrackDao trackDao;

    public PlaylistDB(TrackDao trackDao, PlaylistDao playlistDao, PlaylistTrackDao playlistTrackDao, LibraryContextMappingDao libraryContextMappingDao, PlaylistService service) {
        Intrinsics.checkNotNullParameter(trackDao, "trackDao");
        Intrinsics.checkNotNullParameter(playlistDao, "playlistDao");
        Intrinsics.checkNotNullParameter(playlistTrackDao, "playlistTrackDao");
        Intrinsics.checkNotNullParameter(libraryContextMappingDao, "libraryContextMappingDao");
        Intrinsics.checkNotNullParameter(service, "service");
        this.trackDao = trackDao;
        this.playlistDao = playlistDao;
        this.playlistTrackDao = playlistTrackDao;
        this.libraryContextMappingDao = libraryContextMappingDao;
        this.service = service;
        this.scheduler = Schedulers.from(Executors.newSingleThreadExecutor());
        this.serviceScheduler = Schedulers.from(Executors.newSingleThreadExecutor());
    }

    public final Observable<Unit> putTracksForPlaylist(final List<Track> tracks, final String playlistId) {
        Intrinsics.checkNotNullParameter(tracks, "tracks");
        Intrinsics.checkNotNullParameter(playlistId, "playlistId");
        Observable<Unit> observableSubscribeOn = Observable.create(new Observable.OnSubscribe() { // from class: com.amazon.music.curate.data.database.PlaylistDB$$ExternalSyntheticLambda4
            @Override // rx.functions.Action1
            public final void call(Object obj) {
                PlaylistDB.m1939putTracksForPlaylist$lambda1(this.f$0, tracks, playlistId, (Subscriber) obj);
            }
        }).subscribeOn(this.scheduler);
        Intrinsics.checkNotNullExpressionValue(observableSubscribeOn, "create(Observable.OnSubs…  .subscribeOn(scheduler)");
        return observableSubscribeOn;
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: putTracksForPlaylist$lambda-1, reason: not valid java name */
    public static final void m1939putTracksForPlaylist$lambda1(PlaylistDB this$0, List tracks, String playlistId, Subscriber subscriber) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        Intrinsics.checkNotNullParameter(tracks, "$tracks");
        Intrinsics.checkNotNullParameter(playlistId, "$playlistId");
        this$0.trackDao.putTracks(tracks);
        PlaylistTrackDao playlistTrackDao = this$0.playlistTrackDao;
        ArrayList arrayList = new ArrayList(CollectionsKt__IterablesKt.collectionSizeOrDefault(tracks, 10));
        int i = 0;
        for (Object obj : tracks) {
            int i2 = i + 1;
            if (i < 0) {
                CollectionsKt__CollectionsKt.throwIndexOverflow();
            }
            arrayList.add(new PlaylistTrack(playlistId, ((Track) obj).getAsin(), i));
            i = i2;
        }
        playlistTrackDao.putPlaylistTracks(arrayList);
        if (subscriber != null) {
            subscriber.onNext(null);
        }
        if (subscriber == null) {
            return;
        }
        subscriber.onCompleted();
    }

    public final void removeTrackFromPlaylist(String playlistId, String trackId, int index) {
        Intrinsics.checkNotNullParameter(playlistId, "playlistId");
        Intrinsics.checkNotNullParameter(trackId, "trackId");
        this.playlistTrackDao.removePlaylistTrackAndUpdateIndices(playlistId, trackId, index);
        Playlist playlist = this.playlistDao.getPlaylist(playlistId);
        if (playlist == null) {
            return;
        }
        this.playlistDao.putPlaylists(CollectionsKt__CollectionsJVMKt.listOf(new Playlist(playlist.getId(), playlist.getTitle(), playlist.getArtworkUrl(), playlist.getBannerArtworkUrl(), playlist.getNumSongs() - 1, playlist.getDurationSeconds(), playlist.getVersion(), playlist.getExpiryTime(), playlist.getIsPersonalized(), playlist.getIsFollowed(), playlist.getIsShareable(), playlist.getIsPrime(), playlist.getIsMusicUnlimited(), playlist.getIsNightwing(), playlist.getIsNightwingOnDemand(), playlist.getCanRename(), playlist.getCanEditContent(), playlist.getCanRemove())));
    }

    public final Observable<Unit> removePlaylist(final String playlistId) {
        Intrinsics.checkNotNullParameter(playlistId, "playlistId");
        Observable<Unit> observableSubscribeOn = Observable.create(new Observable.OnSubscribe() { // from class: com.amazon.music.curate.data.database.PlaylistDB$$ExternalSyntheticLambda0
            @Override // rx.functions.Action1
            public final void call(Object obj) {
                PlaylistDB.m1940removePlaylist$lambda3(this.f$0, playlistId, (Subscriber) obj);
            }
        }).subscribeOn(this.scheduler);
        Intrinsics.checkNotNullExpressionValue(observableSubscribeOn, "create(Observable.OnSubs…  .subscribeOn(scheduler)");
        return observableSubscribeOn;
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: removePlaylist$lambda-3, reason: not valid java name */
    public static final void m1940removePlaylist$lambda3(PlaylistDB this$0, String playlistId, Subscriber subscriber) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        Intrinsics.checkNotNullParameter(playlistId, "$playlistId");
        this$0.playlistTrackDao.deletePlaylistTracks(playlistId);
        this$0.playlistDao.deletePlaylist(playlistId);
        this$0.libraryContextMappingDao.deleteByEntityId(playlistId);
        subscriber.onNext(null);
        subscriber.onCompleted();
    }

    public final void appendTracksToPlaylist(String playlistId, final List<Track> tracks) {
        Intrinsics.checkNotNullParameter(playlistId, "playlistId");
        Intrinsics.checkNotNullParameter(tracks, "tracks");
        final Playlist playlist = this.playlistDao.getPlaylist(playlistId);
        Intrinsics.checkNotNull(playlist);
        LOG.debug("appendTracksToPlaylist(numSongs = " + playlist.getNumSongs() + ')');
        Iterator<T> it = tracks.iterator();
        while (it.hasNext()) {
            LOG.debug(((Track) it.next()).getTitle());
        }
        this.trackDao.putTracks(tracks);
        PlaylistTrackDao playlistTrackDao = this.playlistTrackDao;
        ArrayList arrayList = new ArrayList(CollectionsKt__IterablesKt.collectionSizeOrDefault(tracks, 10));
        int i = 0;
        for (Object obj : tracks) {
            int i2 = i + 1;
            if (i < 0) {
                CollectionsKt__CollectionsKt.throwIndexOverflow();
            }
            arrayList.add(new PlaylistTrack(playlist.getId(), ((Track) obj).getAsin(), playlist.getNumSongs() + i));
            i = i2;
        }
        playlistTrackDao.putPlaylistTracks(arrayList);
        Integer durationSeconds = playlist.getDurationSeconds();
        int iIntValue = durationSeconds != null ? durationSeconds.intValue() : 0;
        Iterator<T> it2 = tracks.iterator();
        while (it2.hasNext()) {
            iIntValue += ((Track) it2.next()).getDuration();
        }
        this.playlistDao.putPlaylists(CollectionsKt__CollectionsJVMKt.listOf(new Playlist(playlist.getId(), playlist.getTitle(), playlist.getArtworkUrl(), playlist.getBannerArtworkUrl(), tracks.size() + playlist.getNumSongs(), Integer.valueOf(iIntValue), playlist.getVersion(), playlist.getExpiryTime(), playlist.getIsPersonalized(), playlist.getIsFollowed(), playlist.getIsShareable(), playlist.getIsPrime(), playlist.getIsMusicUnlimited(), playlist.getIsNightwing(), playlist.getIsNightwingOnDemand(), playlist.getCanRename(), playlist.getCanEditContent(), playlist.getCanRemove())));
        Observable.create(new Observable.OnSubscribe() { // from class: com.amazon.music.curate.data.database.PlaylistDB$$ExternalSyntheticLambda5
            @Override // rx.functions.Action1
            public final void call(Object obj2) throws ServiceException {
                PlaylistDB.m1933appendTracksToPlaylist$lambda7(this.f$0, playlist, tracks, (Subscriber) obj2);
            }
        }).subscribeOn(this.serviceScheduler).subscribe(new Action1() { // from class: com.amazon.music.curate.data.database.PlaylistDB$$ExternalSyntheticLambda6
            @Override // rx.functions.Action1
            public final void call(Object obj2) {
                PlaylistDB.m1934appendTracksToPlaylist$lambda8((Unit) obj2);
            }
        }, new Action1() { // from class: com.amazon.music.curate.data.database.PlaylistDB$$ExternalSyntheticLambda7
            @Override // rx.functions.Action1
            public final void call(Object obj2) {
                PlaylistDB.m1935appendTracksToPlaylist$lambda9((Throwable) obj2);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: appendTracksToPlaylist$lambda-7, reason: not valid java name */
    public static final void m1933appendTracksToPlaylist$lambda7(PlaylistDB this$0, Playlist playlist, List tracks, Subscriber subscriber) throws ServiceException {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        Intrinsics.checkNotNullParameter(playlist, "$playlist");
        Intrinsics.checkNotNullParameter(tracks, "$tracks");
        this$0.playlistDao.putPlaylists(CollectionsKt__CollectionsJVMKt.listOf(this$0.service.appendTrackToPlaylist(playlist, tracks)));
        subscriber.onCompleted();
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: appendTracksToPlaylist$lambda-8, reason: not valid java name */
    public static final void m1934appendTracksToPlaylist$lambda8(Unit unit) {
        LOG.debug("Append successful");
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: appendTracksToPlaylist$lambda-9, reason: not valid java name */
    public static final void m1935appendTracksToPlaylist$lambda9(Throwable th) {
        LOG.error("appendTracks call failed", th);
    }

    /* JADX WARN: Multi-variable type inference failed */
    public static /* synthetic */ void putPlaylists$default(PlaylistDB playlistDB, List list, Function0 function0, int i, Object obj) {
        if ((i & 2) != 0) {
            function0 = null;
        }
        playlistDB.putPlaylists(list, function0);
    }

    public final void putPlaylists(final List<? extends Playlist> playlists, final Function0<Unit> insertFinished) {
        Intrinsics.checkNotNullParameter(playlists, "playlists");
        Observable.create(new Observable.OnSubscribe() { // from class: com.amazon.music.curate.data.database.PlaylistDB$$ExternalSyntheticLambda1
            @Override // rx.functions.Action1
            public final void call(Object obj) {
                PlaylistDB.m1937putPlaylists$lambda10(this.f$0, playlists, (Subscriber) obj);
            }
        }).subscribeOn(this.scheduler).subscribe(new Action1() { // from class: com.amazon.music.curate.data.database.PlaylistDB$$ExternalSyntheticLambda2
            @Override // rx.functions.Action1
            public final void call(Object obj) {
                PlaylistDB.m1938putPlaylists$lambda11(insertFinished, (Unit) obj);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: putPlaylists$lambda-10, reason: not valid java name */
    public static final void m1937putPlaylists$lambda10(PlaylistDB this$0, List playlists, Subscriber subscriber) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        Intrinsics.checkNotNullParameter(playlists, "$playlists");
        this$0.playlistDao.putPlaylists(playlists);
        subscriber.onNext(null);
        subscriber.onCompleted();
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: putPlaylists$lambda-11, reason: not valid java name */
    public static final void m1938putPlaylists$lambda11(Function0 function0, Unit unit) {
        if (function0 == null) {
            return;
        }
        function0.invoke();
    }

    public final List<Track> getTracksForPlaylist(String playlistId, int limit, int offset) {
        Intrinsics.checkNotNullParameter(playlistId, "playlistId");
        return this.playlistTrackDao.getTracksForPlaylist(playlistId, limit, offset);
    }

    public final Playlist getPlaylist(String playlistId) {
        Intrinsics.checkNotNullParameter(playlistId, "playlistId");
        Playlist playlist = this.playlistDao.getPlaylist(playlistId);
        if (playlist != null && playlist.getExpiryTime() >= TimeUnit.MILLISECONDS.toSeconds(System.currentTimeMillis())) {
            return playlist;
        }
        GetUserPlaylistResponse userPlaylist$default = PlaylistService.getUserPlaylist$default(this.service, playlistId, null, 2, null);
        Playlist playlist2 = userPlaylist$default.getPlaylist();
        putPlaylists$default(this, CollectionsKt__CollectionsJVMKt.listOf(playlist2), null, 2, null);
        putTracksForPlaylist(userPlaylist$default.getTracks(), playlistId);
        return playlist2;
    }

    public final LiveData<Playlist> getPlaylistLiveData(String playlistId) {
        Intrinsics.checkNotNullParameter(playlistId, "playlistId");
        return this.playlistDao.getPlaylistLiveData(playlistId);
    }

    public final LiveData<List<Track>> getPlaylistTracksLiveData(String playlistId) {
        Intrinsics.checkNotNullParameter(playlistId, "playlistId");
        return this.playlistTrackDao.getPlaylistTracksLiveData(playlistId);
    }

    public final DataSource.Factory<Integer, Playlist> getPlaylists() {
        return this.playlistDao.getPlaylists();
    }

    public final DataSource.Factory<Integer, Playlist> getPersonalizedPlaylists() {
        return this.playlistDao.getPersonalizedPlaylists();
    }

    public final Observable<Unit> deletePersonalizedPlaylists() {
        Observable<Unit> observableSubscribeOn = Observable.create(new Observable.OnSubscribe() { // from class: com.amazon.music.curate.data.database.PlaylistDB$$ExternalSyntheticLambda3
            @Override // rx.functions.Action1
            public final void call(Object obj) {
                PlaylistDB.m1936deletePersonalizedPlaylists$lambda12(this.f$0, (Subscriber) obj);
            }
        }).subscribeOn(this.scheduler);
        Intrinsics.checkNotNullExpressionValue(observableSubscribeOn, "create(Observable.OnSubs…  .subscribeOn(scheduler)");
        return observableSubscribeOn;
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: deletePersonalizedPlaylists$lambda-12, reason: not valid java name */
    public static final void m1936deletePersonalizedPlaylists$lambda12(PlaylistDB this$0, Subscriber subscriber) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        this$0.playlistDao.deletePersonalizedPlaylists();
        subscriber.onCompleted();
    }
}
