package com.amazon.mp3.download;

import android.content.Context;
import android.net.Uri;
import com.amazon.mp3.download.helper.PrimePlaylistDownloadHelper;
import com.amazon.mp3.download.helper.PrimePlaylistTrackDownloadHelper;
import com.amazon.mp3.download.helper.TrackDownloadHelper;
import com.amazon.mp3.download.helper.UdoPlaylistDownloadHelper;
import com.amazon.mp3.service.ServiceUtil;
import com.amazon.mp3.util.Log;
import com.amazon.mp3.util.extensions.DownloadKt;
import com.amazon.mp3.util.extensions.JavaConcurrentKt;
import com.amazon.music.downloads.DownloadState;
import com.amazon.music.downloads.Group;
import com.amazon.music.downloads.Item;
import com.amazon.music.downloads.notification.DownloadListener;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.collections.CollectionsKt___CollectionsKt;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.StringsKt__StringsJVMKt;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;
import rx.schedulers.Schedulers;
import rx.subjects.PublishSubject;

/* JADX INFO: compiled from: MusicDownloadListener.kt */
/* JADX INFO: loaded from: classes3.dex */
@Metadata(d1 = {"\u0000z\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0007\n\u0002\b\u0003\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004J\u000e\u0010\u0019\u001a\u00020\u001a2\u0006\u0010\u001b\u001a\u00020\u000bJ\b\u0010\u001c\u001a\u00020\u001aH\u0002J\u0018\u0010\u001d\u001a\u00020\u001e2\u0006\u0010\u001f\u001a\u00020\u00182\u0006\u0010 \u001a\u00020!H\u0002J\u0018\u0010\"\u001a\u00020\u001a2\u0006\u0010#\u001a\u00020$2\u0006\u0010\u001f\u001a\u00020\u0018H\u0002J$\u0010%\u001a\u00020\u001a2\b\u0010&\u001a\u0004\u0018\u00010\u00062\b\u0010'\u001a\u0004\u0018\u00010(2\u0006\u0010)\u001a\u00020*H\u0016J$\u0010%\u001a\u00020\u001a2\b\u0010&\u001a\u0004\u0018\u00010\u00062\b\u0010 \u001a\u0004\u0018\u00010!2\u0006\u0010)\u001a\u00020*H\u0016J&\u0010+\u001a\u00020\u001a2\b\u0010\u001f\u001a\u0004\u0018\u00010\u00182\b\u0010&\u001a\u0004\u0018\u00010\u00062\b\u0010'\u001a\u0004\u0018\u00010(H\u0016J&\u0010+\u001a\u00020\u001a2\b\u0010\u001f\u001a\u0004\u0018\u00010\u00182\b\u0010&\u001a\u0004\u0018\u00010\u00062\b\u0010 \u001a\u0004\u0018\u00010!H\u0016J\u0018\u0010,\u001a\u00020\u001a2\u0006\u0010#\u001a\u00020$2\u0006\u0010\u001f\u001a\u00020\u0018H\u0002R\u0016\u0010\u0005\u001a\n \u0007*\u0004\u0018\u00010\u00060\u0006X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\b\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000R\u0014\u0010\t\u001a\b\u0012\u0004\u0012\u00020\u000b0\nX\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\f\u001a\u00020\rX\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u000e\u001a\u00020\u000fX\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0010\u001a\u00020\u0011X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0012\u001a\u00020\u0013X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0014\u001a\u00020\u0015X\u0082\u0004¢\u0006\u0002\n\u0000R\u0014\u0010\u0016\u001a\b\u0012\u0004\u0012\u00020\u00180\u0017X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006-"}, d2 = {"Lcom/amazon/mp3/download/MusicDownloadListener;", "Lcom/amazon/music/downloads/notification/DownloadListener;", "context", "Landroid/content/Context;", "(Landroid/content/Context;)V", "TAG", "", "kotlin.jvm.PlatformType", "mAppContext", "mEndOfCollectionTasks", "Ljava/util/Queue;", "Ljava/lang/Runnable;", "mExecutorService", "Ljava/util/concurrent/ExecutorService;", "mPrimePlaylistDownloadHelper", "Lcom/amazon/mp3/download/helper/PrimePlaylistDownloadHelper;", "mPrimePlaylistTrackDownloadHelper", "Lcom/amazon/mp3/download/helper/PrimePlaylistTrackDownloadHelper;", "mTrackDownloadHelper", "Lcom/amazon/mp3/download/helper/TrackDownloadHelper;", "mUdoPlaylistDownloadHelper", "Lcom/amazon/mp3/download/helper/UdoPlaylistDownloadHelper;", "updateNotificationSubject", "Lrx/subjects/PublishSubject;", "Lcom/amazon/music/downloads/DownloadState;", "enqueueEndOfCollectionTask", "", "runnable", "executeEndOfCollectionTasks", "isEndOfCollection", "", "state", "item", "Lcom/amazon/music/downloads/Item;", "onPrimePlaylistUpdate", "playlistUri", "Landroid/net/Uri;", "onProgressUpdate", "id", "group", "Lcom/amazon/music/downloads/Group;", "percentageDownloaded", "", "onStatusUpdate", "onUdoPlaylistUpdate", "DigitalMusicAndroid3P_automotiveProdRelease"}, k = 1, mv = {1, 6, 0}, xi = 48)
public final class MusicDownloadListener extends DownloadListener {
    private final String TAG;
    private final Context mAppContext;
    private final Queue<Runnable> mEndOfCollectionTasks;
    private final ExecutorService mExecutorService;
    private final PrimePlaylistDownloadHelper mPrimePlaylistDownloadHelper;
    private final PrimePlaylistTrackDownloadHelper mPrimePlaylistTrackDownloadHelper;
    private final TrackDownloadHelper mTrackDownloadHelper;
    private final UdoPlaylistDownloadHelper mUdoPlaylistDownloadHelper;
    private final PublishSubject<DownloadState> updateNotificationSubject;

    /* JADX INFO: compiled from: MusicDownloadListener.kt */
    @Metadata(k = 3, mv = {1, 6, 0}, xi = 48)
    public /* synthetic */ class WhenMappings {
        public static final /* synthetic */ int[] $EnumSwitchMapping$0;

        static {
            int[] iArr = new int[DownloadState.values().length];
            iArr[DownloadState.DOWNLOADED.ordinal()] = 1;
            iArr[DownloadState.ERROR.ordinal()] = 2;
            $EnumSwitchMapping$0 = iArr;
        }
    }

    private final boolean isEndOfCollection(DownloadState state, Item item) {
        return false;
    }

    @Override // com.amazon.music.downloads.notification.DownloadListener
    public void onProgressUpdate(String id, Group group, float percentageDownloaded) {
    }

    @Override // com.amazon.music.downloads.notification.DownloadListener
    public void onProgressUpdate(String id, Item item, float percentageDownloaded) {
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public MusicDownloadListener(Context context) {
        super(true);
        Intrinsics.checkNotNullParameter(context, "context");
        this.TAG = MusicDownloadListener.class.getSimpleName();
        Context applicationContext = context.getApplicationContext();
        Intrinsics.checkNotNullExpressionValue(applicationContext, "context.applicationContext");
        this.mAppContext = applicationContext;
        PublishSubject<DownloadState> publishSubjectCreate = PublishSubject.create();
        Intrinsics.checkNotNullExpressionValue(publishSubjectCreate, "create()");
        this.updateNotificationSubject = publishSubjectCreate;
        this.mPrimePlaylistDownloadHelper = new PrimePlaylistDownloadHelper(applicationContext);
        this.mUdoPlaylistDownloadHelper = new UdoPlaylistDownloadHelper(applicationContext);
        this.mTrackDownloadHelper = new TrackDownloadHelper(applicationContext);
        this.mPrimePlaylistTrackDownloadHelper = new PrimePlaylistTrackDownloadHelper(applicationContext);
        this.mEndOfCollectionTasks = new LinkedList();
        ExecutorService executorServiceNewSingleThreadExecutor = Executors.newSingleThreadExecutor();
        Intrinsics.checkNotNullExpressionValue(executorServiceNewSingleThreadExecutor, "newSingleThreadExecutor()");
        this.mExecutorService = executorServiceNewSingleThreadExecutor;
        publishSubjectCreate.sample(1L, TimeUnit.SECONDS).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe(new Action1() { // from class: com.amazon.mp3.download.MusicDownloadListener$$ExternalSyntheticLambda0
            @Override // rx.functions.Action1
            public final void call(Object obj) {
                MusicDownloadListener.m920_init_$lambda0(this.f$0, (DownloadState) obj);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: _init_$lambda-0, reason: not valid java name */
    public static final void m920_init_$lambda0(MusicDownloadListener this$0, DownloadState downloadState) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        Log.debug(this$0.TAG, "Calling wakeUpDownloadNotificationService");
        ServiceUtil.wakeUpDownloadNotificationService(this$0.mAppContext);
    }

    public final void enqueueEndOfCollectionTask(Runnable runnable) {
        Intrinsics.checkNotNullParameter(runnable, "runnable");
        this.mEndOfCollectionTasks.add(runnable);
    }

    /* JADX WARN: Removed duplicated region for block: B:18:0x004f  */
    @Override // com.amazon.music.downloads.notification.DownloadListener
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public void onStatusUpdate(com.amazon.music.downloads.DownloadState r6, java.lang.String r7, com.amazon.music.downloads.Group r8) {
        /*
            r5 = this;
            if (r6 == 0) goto Lc4
            if (r7 == 0) goto Lc4
            if (r8 != 0) goto L8
            goto Lc4
        L8:
            android.net.Uri r7 = android.net.Uri.parse(r7)
            boolean r8 = com.amazon.mp3.library.provider.MediaProvider.PrimePlaylists.isPrimePlaylist(r7)
            java.lang.String r0 = "uri"
            if (r8 != 0) goto L61
            boolean r8 = com.amazon.mp3.library.provider.MediaProvider.SharedUserPlaylists.isSharedUserPlaylist(r7)
            if (r8 == 0) goto L1c
            goto L61
        L1c:
            boolean r8 = com.amazon.mp3.library.provider.MediaProvider.UdoPlaylists.isUdoPlaylist(r7)
            if (r8 == 0) goto L76
            long r1 = com.amazon.mp3.library.provider.MediaProvider.Playlists.getPlaylistId(r7)
            boolean r8 = com.amazon.mp3.library.util.PlaylistUtil.isUserPlaylistDownloaded(r1)
            if (r8 == 0) goto L4f
            r8 = 0
            java.lang.Integer r3 = java.lang.Integer.valueOf(r8)
            boolean r3 = r3.equals(r6)
            if (r3 != 0) goto L4f
            java.lang.String r0 = r5.TAG
            r3 = 2
            java.lang.Object[] r3 = new java.lang.Object[r3]
            java.lang.Long r4 = java.lang.Long.valueOf(r1)
            r3[r8] = r4
            r8 = 1
            java.lang.String r4 = r6.name()
            r3[r8] = r4
            java.lang.String r8 = "Playlist [%d] has been unified, new state = [%s] keep unified as is"
            com.amazon.mp3.util.Log.debug(r0, r8, r3)
            goto L55
        L4f:
            kotlin.jvm.internal.Intrinsics.checkNotNullExpressionValue(r7, r0)
            r5.onUdoPlaylistUpdate(r7, r6)
        L55:
            com.amazon.music.curate.data.PlaylistIntentReceiverService$Companion r8 = com.amazon.music.curate.data.PlaylistIntentReceiverService.INSTANCE
            android.content.Context r0 = r5.mAppContext
            java.lang.String r1 = java.lang.String.valueOf(r1)
            r8.processUpdatePlaylist(r0, r1)
            goto L76
        L61:
            kotlin.jvm.internal.Intrinsics.checkNotNullExpressionValue(r7, r0)
            r5.onPrimePlaylistUpdate(r7, r6)
            java.lang.String r8 = com.amazon.mp3.library.provider.MediaProvider.PrimePlaylists.getPlaylistAsin(r7)
            java.lang.String r8 = r8.toString()
            com.amazon.music.curate.data.PlaylistIntentReceiverService$Companion r0 = com.amazon.music.curate.data.PlaylistIntentReceiverService.INSTANCE
            android.content.Context r1 = r5.mAppContext
            r0.processUpdatePlaylist(r1, r8)
        L76:
            com.amazon.music.downloads.DownloadState r8 = com.amazon.music.downloads.DownloadState.QUEUED
            if (r6 != r8) goto Lb2
            boolean r8 = com.amazon.mp3.library.provider.MediaProvider.Artists.isArtist(r7)
            if (r8 == 0) goto Lb2
            long r7 = com.amazon.mp3.library.provider.MediaProvider.Artists.getArtistId(r7)
            java.lang.String r7 = java.lang.String.valueOf(r7)
            com.amazon.mp3.library.cache.image.loader.ImageLoaderFactory r8 = com.amazon.mp3.library.cache.image.loader.ImageLoaderFactory.getInstance()
            com.amazon.mp3.library.cache.image.loader.ImageLoaderFactory$ItemType r0 = com.amazon.mp3.library.cache.image.loader.ImageLoaderFactory.ItemType.ARTIST_HERO
            com.amazon.mp3.library.cache.image.loader.ImageLoaderFactory$ImageLoader r8 = r8.getImageLoader(r0)
            if (r8 == 0) goto Laa
            com.amazon.mp3.library.cache.image.loader.ArtistHeroImageLoader r8 = (com.amazon.mp3.library.cache.image.loader.ArtistHeroImageLoader) r8
            boolean r8 = r8.has16x9HeroImage(r7)
            if (r8 == 0) goto Lb2
            com.amazon.mp3.library.cache.image.CacheManager r8 = com.amazon.mp3.library.cache.image.CacheManager.getInstance()
            int r1 = com.amazon.mp3.util.ScreenUtil.getDeviceWidth()
            java.lang.String r2 = "cirrus-local"
            r8.get(r0, r2, r1, r7)
            goto Lb2
        Laa:
            java.lang.NullPointerException r6 = new java.lang.NullPointerException
            java.lang.String r7 = "null cannot be cast to non-null type com.amazon.mp3.library.cache.image.loader.ArtistHeroImageLoader"
            r6.<init>(r7)
            throw r6
        Lb2:
            com.amazon.music.downloads.DownloadState r7 = com.amazon.music.downloads.DownloadState.DOWNLOADING
            if (r6 != r7) goto Lbb
            rx.subjects.PublishSubject<com.amazon.music.downloads.DownloadState> r7 = r5.updateNotificationSubject
            r7.onNext(r6)
        Lbb:
            boolean r6 = com.amazon.mp3.util.extensions.DownloadKt.isTerminal(r6)
            if (r6 == 0) goto Lc4
            r5.executeEndOfCollectionTasks()
        Lc4:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.amazon.mp3.download.MusicDownloadListener.onStatusUpdate(com.amazon.music.downloads.DownloadState, java.lang.String, com.amazon.music.downloads.Group):void");
    }

    private final void executeEndOfCollectionTasks() {
        if (this.mEndOfCollectionTasks.isEmpty()) {
            return;
        }
        synchronized (this.mEndOfCollectionTasks) {
            if (this.mEndOfCollectionTasks.isEmpty()) {
                return;
            }
            List list = CollectionsKt___CollectionsKt.toList(this.mEndOfCollectionTasks);
            this.mEndOfCollectionTasks.clear();
            JavaConcurrentKt.invokeAll(this.mExecutorService, list);
            Unit unit = Unit.INSTANCE;
        }
    }

    @Override // com.amazon.music.downloads.notification.DownloadListener
    public void onStatusUpdate(DownloadState state, String id, Item item) {
        if (state == null || id == null || item == null) {
            return;
        }
        if (state == DownloadState.DOWNLOADING) {
            this.updateNotificationSubject.onNext(state);
        }
        if (StringsKt__StringsJVMKt.startsWith$default(id, "primeplaylist:", false, 2, null)) {
            this.mPrimePlaylistTrackDownloadHelper.updateDownloadState(id, item, state);
        } else {
            this.mTrackDownloadHelper.updateDownloadState(id, item, state);
        }
        if (isEndOfCollection(state, item)) {
            executeEndOfCollectionTasks();
        }
    }

    private final void onPrimePlaylistUpdate(Uri playlistUri, DownloadState state) {
        this.mPrimePlaylistDownloadHelper.updateDownloadState(playlistUri, DownloadKt.toAppDownloadState(state));
        int i = WhenMappings.$EnumSwitchMapping$0[state.ordinal()];
        if (i == 1) {
            this.mPrimePlaylistDownloadHelper.createLocalPlaylist(playlistUri);
        } else {
            if (i != 2) {
                return;
            }
            this.mPrimePlaylistDownloadHelper.createLocalPlaylist(playlistUri);
            this.mPrimePlaylistDownloadHelper.setPendingTracksToFailed(playlistUri);
        }
    }

    private final void onUdoPlaylistUpdate(Uri playlistUri, DownloadState state) {
        this.mUdoPlaylistDownloadHelper.updateDownloadState(playlistUri, DownloadKt.toAppDownloadState(state));
    }
}
