package com.amazon.mp3.library.fragment;

import android.app.Application;
import android.content.Context;
import android.net.Uri;
import androidx.fragment.app.DialogFragment;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.MutableLiveData;
import com.amazon.mp3.AmazonApplication;
import com.amazon.mp3.artist.view.PrimeItemsTransformationUtil;
import com.amazon.mp3.environment.Environment;
import com.amazon.mp3.library.provider.MediaProvider;
import com.amazon.mp3.library.util.PlaylistUtil;
import com.amazon.mp3.playback.video.VideoUtil;
import com.amazon.mp3.playlist.db.PrimePlaylistDatabaseManager;
import com.amazon.mp3.playlist.job.GetPrimePlaylistJob;
import com.amazon.mp3.prime.browse.metadata.PrimeTrack;
import com.amazon.mp3.util.MetricsUtil;
import com.amazon.mp3.util.coroutines.LookupError;
import com.amazon.music.account.AccountManagerSingleton;
import com.amazon.music.account.AccountStateChangeListener;
import com.amazon.music.account.AccountStateField;
import com.amazon.music.destination.parser.UpsellDeeplinkParser;
import com.amazon.music.find.utils.PageUriUtils;
import com.amazon.music.metrics.mts.event.types.uiinteraction.ActionType;
import com.amazon.music.metrics.mts.event.types.uiinteraction.EntityIdType;
import com.amazon.music.metrics.mts.event.types.uiinteraction.EntityType;
import com.amazon.music.metrics.mts.event.types.uiinteraction.InteractionType;
import com.amazon.music.metrics.mts.event.types.uiinteraction.PageType;
import com.amazon.music.models.AssetQuality;
import com.amazon.music.storeservice.model.PlaylistItem;
import com.amazon.music.storeservice.model.TrackItem;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import java.util.concurrent.CancellationException;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.collections.CollectionsKt__IterablesKt;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.CompletableJob;
import kotlinx.coroutines.Job;
import kotlinx.coroutines.SupervisorKt;
import rx.Completable;
import rx.CompletableSubscriber;
import rx.functions.Action0;
import rx.schedulers.Schedulers;

/* JADX INFO: compiled from: VideoPlaylistDetailViewModel.kt */
/* JADX INFO: loaded from: classes3.dex */
@Metadata(bv = {}, d1 = {"\u0000x\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0002\b\u000b\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\u0018\u0000 E2\u00020\u0001:\u0001EB\u0017\u0012\u0006\u0010B\u001a\u00020A\u0012\u0006\u0010\u001f\u001a\u00020\u0002¢\u0006\u0004\bC\u0010DJ\u0010\u0010\u0005\u001a\u00020\u00042\u0006\u0010\u0003\u001a\u00020\u0002H\u0002J\u001c\u0010\t\u001a\b\u0012\u0004\u0012\u00020\u00020\u00062\f\u0010\b\u001a\b\u0012\u0004\u0012\u00020\u00070\u0006H\u0002J\u0006\u0010\n\u001a\u00020\u0004J\u0006\u0010\u000b\u001a\u00020\u0004J\b\u0010\f\u001a\u00020\u0004H\u0014J\u000e\u0010\u000f\u001a\u00020\u00042\u0006\u0010\u000e\u001a\u00020\rJ\u000e\u0010\u0010\u001a\u00020\u00022\u0006\u0010\u000e\u001a\u00020\rJ$\u0010\u0016\u001a\u00020\u00042\u0006\u0010\u0012\u001a\u00020\u00112\f\u0010\u0013\u001a\b\u0012\u0004\u0012\u00020\u00070\u00062\u0006\u0010\u0015\u001a\u00020\u0014J\u000e\u0010\u0019\u001a\u00020\u00042\u0006\u0010\u0018\u001a\u00020\u0017J,\u0010\u001e\u001a\u00020\u00042\u0006\u0010\u0012\u001a\u00020\u00112\f\u0010\u001a\u001a\b\u0012\u0004\u0012\u00020\u00070\u00062\u0006\u0010\u001c\u001a\u00020\u001b2\u0006\u0010\u001d\u001a\u00020\u001bR\u0014\u0010\u001f\u001a\u00020\u00028\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b\u001f\u0010 R\u0018\u0010!\u001a\u0004\u0018\u00010\r8\u0002@\u0002X\u0082\u000e¢\u0006\u0006\n\u0004\b!\u0010\"R\u001d\u0010\u000f\u001a\b\u0012\u0004\u0012\u00020\u00040#8\u0006¢\u0006\f\n\u0004\b\u000f\u0010$\u001a\u0004\b%\u0010&R\u001d\u0010'\u001a\b\u0012\u0004\u0012\u00020\u00020#8\u0006¢\u0006\f\n\u0004\b'\u0010$\u001a\u0004\b(\u0010&R\u001d\u0010)\u001a\b\u0012\u0004\u0012\u00020\u00020#8\u0006¢\u0006\f\n\u0004\b)\u0010$\u001a\u0004\b*\u0010&R#\u0010\b\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00070\u00060#8\u0006¢\u0006\f\n\u0004\b\b\u0010$\u001a\u0004\b+\u0010&R\u001d\u0010,\u001a\b\u0012\u0004\u0012\u00020\u00020#8\u0006¢\u0006\f\n\u0004\b,\u0010$\u001a\u0004\b-\u0010&R\u001d\u0010.\u001a\b\u0012\u0004\u0012\u00020\u00020#8\u0006¢\u0006\f\n\u0004\b.\u0010$\u001a\u0004\b\u0010\u0010&R\u001d\u00100\u001a\b\u0012\u0004\u0012\u00020/0#8\u0006¢\u0006\f\n\u0004\b0\u0010$\u001a\u0004\b1\u0010&R\u001d\u00103\u001a\b\u0012\u0004\u0012\u0002020#8\u0006¢\u0006\f\n\u0004\b3\u0010$\u001a\u0004\b4\u0010&R\u001d\u00105\u001a\b\u0012\u0004\u0012\u00020\u00040#8\u0006¢\u0006\f\n\u0004\b5\u0010$\u001a\u0004\b6\u0010&R\u001d\u00107\u001a\b\u0012\u0004\u0012\u00020\u001b0#8\u0006¢\u0006\f\n\u0004\b7\u0010$\u001a\u0004\b8\u0010&R\u0017\u0010:\u001a\u0002098\u0006¢\u0006\f\n\u0004\b:\u0010;\u001a\u0004\b<\u0010=R\u0014\u0010?\u001a\u00020>8\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b?\u0010@¨\u0006F"}, d2 = {"Lcom/amazon/mp3/library/fragment/VideoPlaylistDetailViewModel;", "Landroidx/lifecycle/AndroidViewModel;", "", "videoPlaylistAsin", "", "fetchPlaylist", "", "Lcom/amazon/mp3/prime/browse/metadata/PrimeTrack;", "videos", "getPidList", "loadData", "cancel", "onCleared", "Lcom/amazon/music/storeservice/model/PlaylistItem;", Environment.PLAYLIST_PATH, "onPlaylistFetched", "getLength", "Landroid/content/Context;", "context", "playlistTracks", "", "position", "onVideoClicked", "Lcom/amazon/music/metrics/mts/event/types/uiinteraction/ActionType;", "actionType", "sendVideoClickMetrics", "data", "", "shuffle", "isUserInitiated", PageUriUtils.DO_PLAY_VALUE, "asin", "Ljava/lang/String;", "playlistItem", "Lcom/amazon/music/storeservice/model/PlaylistItem;", "Landroidx/lifecycle/MutableLiveData;", "Landroidx/lifecycle/MutableLiveData;", "getOnPlaylistFetched", "()Landroidx/lifecycle/MutableLiveData;", "artworkUrl", "getArtworkUrl", "playlistDescription", "getPlaylistDescription", "getVideos", "title", "getTitle", "length", "Landroidx/fragment/app/DialogFragment;", UpsellDeeplinkParser.UPSELL_SEGMENT_NAME, "getUpsell", "Landroid/net/Uri;", "uri", "getUri", "onExplicitFilterChanged", "getOnExplicitFilterChanged", "showLoadingState", "getShowLoadingState", "Lkotlinx/coroutines/CompletableJob;", "job", "Lkotlinx/coroutines/CompletableJob;", "getJob", "()Lkotlinx/coroutines/CompletableJob;", "Lcom/amazon/music/account/AccountStateChangeListener;", "accountStateChangeListener", "Lcom/amazon/music/account/AccountStateChangeListener;", "Landroid/app/Application;", "application", "<init>", "(Landroid/app/Application;Ljava/lang/String;)V", "Companion", "DigitalMusicAndroid3P_automotiveProdRelease"}, k = 1, mv = {1, 6, 0})
public final class VideoPlaylistDetailViewModel extends AndroidViewModel {
    private static final String TAG = VideoPlaylistDetailViewModel.class.getSimpleName();
    private final AccountStateChangeListener accountStateChangeListener;
    private final MutableLiveData<String> artworkUrl;
    private final String asin;
    private final CompletableJob job;
    private final MutableLiveData<String> length;
    private final MutableLiveData<Unit> onExplicitFilterChanged;
    private final MutableLiveData<Unit> onPlaylistFetched;
    private final MutableLiveData<String> playlistDescription;
    private PlaylistItem playlistItem;
    private final MutableLiveData<Boolean> showLoadingState;
    private final MutableLiveData<String> title;
    private final MutableLiveData<DialogFragment> upsell;
    private final MutableLiveData<Uri> uri;
    private final MutableLiveData<List<PrimeTrack>> videos;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public VideoPlaylistDetailViewModel(Application application, String asin) {
        super(application);
        Intrinsics.checkNotNullParameter(application, "application");
        Intrinsics.checkNotNullParameter(asin, "asin");
        this.asin = asin;
        this.onPlaylistFetched = new MutableLiveData<>();
        this.artworkUrl = new MutableLiveData<>();
        this.playlistDescription = new MutableLiveData<>();
        this.videos = new MutableLiveData<>();
        this.title = new MutableLiveData<>();
        this.length = new MutableLiveData<>();
        this.upsell = new MutableLiveData<>();
        this.uri = new MutableLiveData<>();
        this.onExplicitFilterChanged = new MutableLiveData<>();
        this.showLoadingState = new MutableLiveData<>();
        this.job = SupervisorKt.SupervisorJob$default((Job) null, 1, (Object) null);
        AccountStateChangeListener accountStateChangeListener = new AccountStateChangeListener() { // from class: com.amazon.mp3.library.fragment.VideoPlaylistDetailViewModel$$ExternalSyntheticLambda2
            @Override // com.amazon.music.account.AccountStateChangeListener
            public final void onAccountStateChange(Set set) {
                VideoPlaylistDetailViewModel.m1292accountStateChangeListener$lambda0(this.f$0, set);
            }
        };
        this.accountStateChangeListener = accountStateChangeListener;
        AccountManagerSingleton.get().registerListener(accountStateChangeListener);
    }

    public final MutableLiveData<Unit> getOnPlaylistFetched() {
        return this.onPlaylistFetched;
    }

    public final MutableLiveData<String> getArtworkUrl() {
        return this.artworkUrl;
    }

    public final MutableLiveData<String> getPlaylistDescription() {
        return this.playlistDescription;
    }

    public final MutableLiveData<List<PrimeTrack>> getVideos() {
        return this.videos;
    }

    public final MutableLiveData<String> getTitle() {
        return this.title;
    }

    public final MutableLiveData<String> getLength() {
        return this.length;
    }

    public final MutableLiveData<DialogFragment> getUpsell() {
        return this.upsell;
    }

    public final MutableLiveData<Unit> getOnExplicitFilterChanged() {
        return this.onExplicitFilterChanged;
    }

    public final MutableLiveData<Boolean> getShowLoadingState() {
        return this.showLoadingState;
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: accountStateChangeListener$lambda-0, reason: not valid java name */
    public static final void m1292accountStateChangeListener$lambda0(VideoPlaylistDetailViewModel this$0, Set set) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        if (set.contains(AccountStateField.EXPLICIT_FILTER_ENABLED_ON_DEVICE)) {
            this$0.onExplicitFilterChanged.postValue(Unit.INSTANCE);
        }
    }

    public final void loadData() {
        this.showLoadingState.setValue(Boolean.TRUE);
        fetchPlaylist(this.asin);
    }

    private final void fetchPlaylist(final String videoPlaylistAsin) {
        Completable.create(new Completable.OnSubscribe() { // from class: com.amazon.mp3.library.fragment.VideoPlaylistDetailViewModel$$ExternalSyntheticLambda0
            @Override // rx.functions.Action1
            public final void call(CompletableSubscriber completableSubscriber) throws Exception {
                VideoPlaylistDetailViewModel.m1293fetchPlaylist$lambda2(videoPlaylistAsin, this, completableSubscriber);
            }
        }).onErrorComplete().doOnCompleted(new Action0() { // from class: com.amazon.mp3.library.fragment.VideoPlaylistDetailViewModel$$ExternalSyntheticLambda1
            @Override // rx.functions.Action0
            public final void call() {
                VideoPlaylistDetailViewModel.m1294fetchPlaylist$lambda3(this.f$0);
            }
        }).subscribeOn(Schedulers.computation()).subscribe();
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: fetchPlaylist$lambda-2, reason: not valid java name */
    public static final void m1293fetchPlaylist$lambda2(String videoPlaylistAsin, final VideoPlaylistDetailViewModel this$0, CompletableSubscriber completableSubscriber) throws Exception {
        Intrinsics.checkNotNullParameter(videoPlaylistAsin, "$videoPlaylistAsin");
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        new GetPrimePlaylistJob(new PrimePlaylistDatabaseManager(AmazonApplication.getApplication()), videoPlaylistAsin, 0, false, false).run();
        PlaylistItem playlistItem = this$0.playlistItem;
        if (playlistItem != null && Intrinsics.areEqual(playlistItem.getAsin(), this$0.asin)) {
            this$0.onPlaylistFetched(playlistItem);
            completableSubscriber.onCompleted();
        } else {
            VideoUtil.INSTANCE.fetchVideoPlaylistItem(this$0.asin, new Function1<PlaylistItem, Unit>() { // from class: com.amazon.mp3.library.fragment.VideoPlaylistDetailViewModel$fetchPlaylist$1$2
                {
                    super(1);
                }

                @Override // kotlin.jvm.functions.Function1
                public /* bridge */ /* synthetic */ Unit invoke(PlaylistItem playlistItem2) {
                    invoke2(playlistItem2);
                    return Unit.INSTANCE;
                }

                /* JADX INFO: renamed from: invoke, reason: avoid collision after fix types in other method */
                public final void invoke2(PlaylistItem item) {
                    Intrinsics.checkNotNullParameter(item, "item");
                    this.this$0.playlistItem = item;
                    this.this$0.onPlaylistFetched(item);
                }
            }, new Function1<LookupError, Unit>() { // from class: com.amazon.mp3.library.fragment.VideoPlaylistDetailViewModel$fetchPlaylist$1$3
                @Override // kotlin.jvm.functions.Function1
                public /* bridge */ /* synthetic */ Unit invoke(LookupError lookupError) {
                    invoke2(lookupError);
                    return Unit.INSTANCE;
                }

                /* JADX INFO: renamed from: invoke, reason: avoid collision after fix types in other method */
                public final void invoke2(LookupError lookupError) {
                    Intrinsics.checkNotNullParameter(lookupError, "lookupError");
                    LookupError.handleError$default(lookupError, null, 1, null);
                }
            });
            completableSubscriber.onCompleted();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: fetchPlaylist$lambda-3, reason: not valid java name */
    public static final void m1294fetchPlaylist$lambda3(VideoPlaylistDetailViewModel this$0) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        this$0.showLoadingState.postValue(Boolean.FALSE);
    }

    public final void cancel() {
        Job.DefaultImpls.cancel$default((Job) this.job, (CancellationException) null, 1, (Object) null);
    }

    @Override // androidx.lifecycle.ViewModel
    protected void onCleared() {
        AccountManagerSingleton.get().deregisterListener(this.accountStateChangeListener);
        super.onCleared();
    }

    public final void onPlaylistFetched(PlaylistItem playlist) {
        Intrinsics.checkNotNullParameter(playlist, "playlist");
        this.artworkUrl.postValue(playlist.getBannerImage());
        this.playlistDescription.postValue(playlist.getDescription());
        List<TrackItem> tracks = playlist.getTracks();
        Intrinsics.checkNotNullExpressionValue(tracks, "playlist.tracks");
        ArrayList arrayList = new ArrayList(CollectionsKt__IterablesKt.collectionSizeOrDefault(tracks, 10));
        Iterator<T> it = tracks.iterator();
        while (it.hasNext()) {
            arrayList.add(PrimeItemsTransformationUtil.toPrimeTrack((TrackItem) it.next()));
        }
        this.videos.postValue(arrayList);
        this.title.postValue(playlist.getTitle());
        this.length.postValue(getLength(playlist));
        this.uri.postValue(MediaProvider.PrimePlaylists.getContentUri("cirrus", playlist.getAsin()));
        this.onPlaylistFetched.postValue(Unit.INSTANCE);
        VideoUtil.INSTANCE.cacheVideos(getPidList(arrayList));
    }

    public final String getLength(PlaylistItem playlist) {
        Intrinsics.checkNotNullParameter(playlist, "playlist");
        Application application = getApplication();
        Integer trackCount = playlist.getTrackCount();
        Intrinsics.checkNotNullExpressionValue(trackCount, "playlist.trackCount");
        int iIntValue = trackCount.intValue();
        Integer duration = playlist.getDuration();
        Intrinsics.checkNotNullExpressionValue(duration, "playlist.duration");
        String strCalculateVideosDuration = PlaylistUtil.calculateVideosDuration(application, iIntValue, duration.intValue());
        Intrinsics.checkNotNullExpressionValue(strCalculateVideosDuration, "calculateVideosDuration(…Count, playlist.duration)");
        return strCalculateVideosDuration;
    }

    public final void onVideoClicked(Context context, List<? extends PrimeTrack> playlistTracks, int position) {
        Intrinsics.checkNotNullParameter(context, "context");
        Intrinsics.checkNotNullParameter(playlistTracks, "playlistTracks");
        VideoUtil videoUtil = VideoUtil.INSTANCE;
        videoUtil.sendUiClickMetric(playlistTracks.get(position), ActionType.PLAY_VIDEO, PageType.DETAIL_PLAYLIST, InteractionType.TAP);
        Uri value = this.uri.getValue();
        if (value != null) {
            VideoUtil.playVideoPlaylist$default(videoUtil, context, value, playlistTracks, position, false, false, 32, null);
        }
    }

    public final void sendVideoClickMetrics(ActionType actionType) {
        Intrinsics.checkNotNullParameter(actionType, "actionType");
        MetricsUtil.sendUiClickMetric(actionType, this.asin, EntityIdType.ASIN, EntityType.VIDEO_PLAYLIST, -1, PageType.DETAIL_PLAYLIST, InteractionType.TAP);
    }

    public final void play(Context context, List<? extends PrimeTrack> data, boolean shuffle, boolean isUserInitiated) {
        Intrinsics.checkNotNullParameter(context, "context");
        Intrinsics.checkNotNullParameter(data, "data");
        Uri value = this.uri.getValue();
        if (value != null) {
            VideoUtil.INSTANCE.playVideoPlaylist(context, value, data, 0, shuffle, isUserInitiated);
        }
    }

    private final List<String> getPidList(List<? extends PrimeTrack> videos) {
        ArrayList<PrimeTrack> arrayList = new ArrayList();
        for (Object obj : videos) {
            List<AssetQuality> assetQualities = ((PrimeTrack) obj).getAssetQualities();
            if (!(assetQualities == null || assetQualities.isEmpty())) {
                arrayList.add(obj);
            }
        }
        ArrayList arrayList2 = new ArrayList(CollectionsKt__IterablesKt.collectionSizeOrDefault(arrayList, 10));
        for (PrimeTrack primeTrack : arrayList) {
            VideoUtil videoUtil = VideoUtil.INSTANCE;
            List<AssetQuality> assetQualities2 = primeTrack.getAssetQualities();
            Intrinsics.checkNotNull(assetQualities2);
            arrayList2.add(videoUtil.getDefaultOrFirstAvailablePID(assetQualities2));
        }
        return arrayList2;
    }
}
