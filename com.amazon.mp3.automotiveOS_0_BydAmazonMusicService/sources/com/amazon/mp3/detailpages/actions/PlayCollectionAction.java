package com.amazon.mp3.detailpages.actions;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.net.Uri;
import androidx.work.Data;
import com.amazon.identity.auth.device.api.MultipleAccountManager;
import com.amazon.mp3.automotiveOS.R;
import com.amazon.mp3.cloudqueue.NoOpCloudQueueClientHandler;
import com.amazon.mp3.detailpages.actions.PlayCollectionAction;
import com.amazon.mp3.featuregate.Feature;
import com.amazon.mp3.library.activity.LibraryActivityFactory;
import com.amazon.mp3.library.item.MusicTrack;
import com.amazon.mp3.library.provider.MediaProvider;
import com.amazon.mp3.playback.harley.PlayQueueSequencer;
import com.amazon.mp3.playback.service.metrics.PlaybackMetricInformation;
import com.amazon.mp3.playback.start.StartPlaybackUtil;
import com.amazon.mp3.util.ConnectivityUtil;
import com.amazon.mp3.util.DbUtil;
import com.amazon.mp3.util.Log;
import com.amazon.mp3.util.UserSubscriptionUtil;
import com.amazon.music.media.playback.ControlSource;
import com.amazon.music.media.playback.util.Clock;
import com.amazon.music.metrics.mts.event.types.DirectedPlayStatus;
import com.amazon.music.metrics.mts.event.types.PlaybackPageType;
import com.amazon.music.platform.playback.augmentation.PlaybackAugmentation;
import com.amazon.music.views.library.models.base.BaseViewModel;
import java.util.List;
import kotlin.Metadata;
import kotlin.Pair;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import rx.Observable;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;
import rx.functions.Func2;
import rx.schedulers.Schedulers;

/* JADX INFO: compiled from: PlayCollectionAction.kt */
/* JADX INFO: loaded from: classes3.dex */
@Metadata(bv = {}, d1 = {"\u0000j\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\b\n\u0002\u0018\u0002\n\u0002\b\u0007\b\u0016\u0018\u0000 52\u00020\u0001:\u000256B\u0017\u0012\u0006\u0010#\u001a\u00020\"\u0012\u0006\u0010(\u001a\u00020'¢\u0006\u0004\b3\u00104Jz\u0010\u0017\u001a\u00020\u00162\u0006\u0010\u0003\u001a\u00020\u00022\u0006\u0010\u0005\u001a\u00020\u00042\u0006\u0010\u0007\u001a\u00020\u00062\u0006\u0010\t\u001a\u00020\b2\b\u0010\u000b\u001a\u0004\u0018\u00010\n2\u0006\u0010\f\u001a\u00020\b2\u000e\u0010\u000f\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\u000e0\r2\b\u0010\u0010\u001a\u0004\u0018\u00010\u00022\b\b\u0002\u0010\u0011\u001a\u00020\b2\b\b\u0002\u0010\u0012\u001a\u00020\b2\u0010\b\u0002\u0010\u0015\u001a\n\u0012\u0004\u0012\u00020\u0014\u0018\u00010\u0013H\u0002J8\u0010\u001b\u001a\u0018\u0012\u0014\u0012\u0012\u0012\u0006\u0012\u0004\u0018\u00010\u000e\u0012\u0006\u0012\u0004\u0018\u00010\u001a0\u00190\r2\u0010\b\u0002\u0010\u000f\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\u000e0\r2\u0006\u0010\u0018\u001a\u00020\u0006H\u0002J\u0018\u0010\u001c\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\u001a0\r2\u0006\u0010\u0018\u001a\u00020\u0006H\u0002J\u008e\u0001\u0010 \u001a\u00020\u00162\u0006\u0010\u0005\u001a\u00020\u00042\u0006\u0010\u001d\u001a\u00020\b2\b\u0010\u001e\u001a\u0004\u0018\u00010\u00022\u0006\u0010\u0003\u001a\u00020\u00022\b\u0010\u000b\u001a\u0004\u0018\u00010\n2\u0006\u0010\u0007\u001a\u00020\u00062\u0006\u0010\t\u001a\u00020\b2\u0006\u0010\u001f\u001a\u00020\b2\u000e\u0010\u000f\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\u000e0\r2\n\b\u0002\u0010\u0010\u001a\u0004\u0018\u00010\u00022\b\b\u0002\u0010\u0011\u001a\u00020\b2\b\b\u0002\u0010\u0012\u001a\u00020\b2\u0010\b\u0002\u0010\u0015\u001a\n\u0012\u0004\u0012\u00020\u0014\u0018\u00010\u0013H\u0007JN\u0010!\u001a\u00020\u00162\u0006\u0010\u0005\u001a\u00020\u00042\u0006\u0010\u0007\u001a\u00020\u00062\u0006\u0010\t\u001a\u00020\b2\b\u0010\u000b\u001a\u0004\u0018\u00010\n2\u0010\b\u0002\u0010\u000f\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\u000e0\r2\u0006\u0010\u001f\u001a\u00020\b2\n\b\u0002\u0010\u0010\u001a\u0004\u0018\u00010\u0002R\u0017\u0010#\u001a\u00020\"8\u0006¢\u0006\f\n\u0004\b#\u0010$\u001a\u0004\b%\u0010&R\u0017\u0010(\u001a\u00020'8\u0006¢\u0006\f\n\u0004\b(\u0010)\u001a\u0004\b*\u0010+R\u001c\u0010/\u001a\n ,*\u0004\u0018\u00010\u00020\u00028\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b-\u0010.R\u0014\u00101\u001a\u0002008\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b1\u00102¨\u00067"}, d2 = {"Lcom/amazon/mp3/detailpages/actions/PlayCollectionAction;", "", "", "collectionName", "Landroid/app/Activity;", MultipleAccountManager.SessionPackageMappingType.JSON_KEY_SESSION_PACKAGE_MAPPING_REMOVE_ACTIVITY_CLASS_NAME, "", "trackPosition", "", "shuffleOn", "Landroid/net/Uri;", "collectionUri", "isFullScreen", "Lrx/Observable;", "Lcom/amazon/mp3/util/DbUtil$WhereClause;", "whereClause", "sortOrder", "isCustomerSelectedEntity", "isRefinementFilterApplied", "", "Lcom/amazon/music/platform/playback/augmentation/PlaybackAugmentation;", "augmentations", "", "beginPlayback", "position", "Lkotlin/Pair;", "Lcom/amazon/mp3/library/item/MusicTrack;", "fetchPlaybackDependencies", "fetchTrack", "isDownloaded", "collectionId", "showNowPlaying", "startNowPlayingActivity", "beginPlayQueuePlayback", "Lcom/amazon/mp3/detailpages/actions/PlayCollectionAction$TrackInformationFetcher;", "trackInfoFetcher", "Lcom/amazon/mp3/detailpages/actions/PlayCollectionAction$TrackInformationFetcher;", "getTrackInfoFetcher", "()Lcom/amazon/mp3/detailpages/actions/PlayCollectionAction$TrackInformationFetcher;", "Lcom/amazon/music/metrics/mts/event/types/PlaybackPageType;", "playbackPageType", "Lcom/amazon/music/metrics/mts/event/types/PlaybackPageType;", "getPlaybackPageType", "()Lcom/amazon/music/metrics/mts/event/types/PlaybackPageType;", "kotlin.jvm.PlatformType", "TAG$1", "Ljava/lang/String;", "TAG", "Lcom/amazon/mp3/playback/service/metrics/PlaybackMetricInformation;", "playbackMetricInfo", "Lcom/amazon/mp3/playback/service/metrics/PlaybackMetricInformation;", "<init>", "(Lcom/amazon/mp3/detailpages/actions/PlayCollectionAction$TrackInformationFetcher;Lcom/amazon/music/metrics/mts/event/types/PlaybackPageType;)V", "Companion", "TrackInformationFetcher", "DigitalMusicAndroid3P_automotiveProdRelease"}, k = 1, mv = {1, 6, 0})
public class PlayCollectionAction {

    /* JADX INFO: renamed from: Companion, reason: from kotlin metadata */
    public static final Companion INSTANCE = new Companion(null);
    private static final String TAG = PlayCollectionAction.class.getSimpleName();

    /* JADX INFO: renamed from: TAG$1, reason: from kotlin metadata */
    private final String TAG;
    private final PlaybackMetricInformation playbackMetricInfo;
    private final PlaybackPageType playbackPageType;
    private final TrackInformationFetcher trackInfoFetcher;

    public PlayCollectionAction(TrackInformationFetcher trackInfoFetcher, PlaybackPageType playbackPageType) {
        Intrinsics.checkNotNullParameter(trackInfoFetcher, "trackInfoFetcher");
        Intrinsics.checkNotNullParameter(playbackPageType, "playbackPageType");
        this.trackInfoFetcher = trackInfoFetcher;
        this.playbackPageType = playbackPageType;
        this.TAG = PlayCollectionAction.class.getSimpleName();
        this.playbackMetricInfo = new PlaybackMetricInformation(DirectedPlayStatus.NOT_DIRECTED, playbackPageType);
    }

    public final TrackInformationFetcher getTrackInfoFetcher() {
        return this.trackInfoFetcher;
    }

    /* JADX INFO: compiled from: PlayCollectionAction.kt */
    @Metadata(d1 = {"\u0000\u0014\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0005\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002R\u0019\u0010\u0003\u001a\n \u0005*\u0004\u0018\u00010\u00040\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0006\u0010\u0007R\u000e\u0010\b\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000¨\u0006\t"}, d2 = {"Lcom/amazon/mp3/detailpages/actions/PlayCollectionAction$Companion;", "", "()V", "TAG", "", "kotlin.jvm.PlatformType", "getTAG", "()Ljava/lang/String;", "recentlyDownloadedSmartPlaylistType", "DigitalMusicAndroid3P_automotiveProdRelease"}, k = 1, mv = {1, 6, 0}, xi = 48)
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }

        public final String getTAG() {
            return PlayCollectionAction.TAG;
        }
    }

    /* JADX INFO: compiled from: PlayCollectionAction.kt */
    @Metadata(d1 = {"\u0000F\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010 \n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010!\n\u0002\b\u0003\n\u0002\u0010\b\n\u0002\b\u0002\bf\u0018\u00002\u00020\u0001J3\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u00032#\u0010\u0005\u001a\u001f\u0012\u0013\u0012\u00110\u0007¢\u0006\f\b\b\u0012\b\b\t\u0012\u0004\b\b(\n\u0012\u0006\u0012\u0004\u0018\u00010\u00040\u0006H&J9\u0010\u000b\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00040\u00030\f2#\u0010\u0005\u001a\u001f\u0012\u0013\u0012\u00110\u0007¢\u0006\f\b\b\u0012\b\b\t\u0012\u0004\b\b(\n\u0012\u0006\u0012\u0004\u0018\u00010\u00040\u0006H\u0016J\u0014\u0010\r\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u000e0\u00030\fH&J \u0010\u000f\u001a\u00020\u00102\f\u0010\u0011\u001a\b\u0012\u0004\u0012\u00020\u00040\u00122\b\b\u0002\u0010\u0013\u001a\u00020\u0004H\u0016J\u0018\u0010\u0014\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\u000e0\f2\u0006\u0010\u0015\u001a\u00020\u0016H&J\b\u0010\u0017\u001a\u00020\u0016H&¨\u0006\u0018"}, d2 = {"Lcom/amazon/mp3/detailpages/actions/PlayCollectionAction$TrackInformationFetcher;", "", "allIds", "", "", "idRetriever", "Lkotlin/Function1;", "Lcom/amazon/music/views/library/models/base/BaseViewModel;", "Lkotlin/ParameterName;", "name", "currentModel", "fetchAllIds", "Lrx/Observable;", "getAllTracks", "Lcom/amazon/mp3/library/item/MusicTrack;", "getFilterByIds", "Lcom/amazon/mp3/util/DbUtil$WhereClause;", "idList", "", "field", "getTrack", "position", "", "getTrackTotalCount", "DigitalMusicAndroid3P_automotiveProdRelease"}, k = 1, mv = {1, 6, 0}, xi = 48)
    public interface TrackInformationFetcher {
        List<String> allIds(Function1<? super BaseViewModel, String> idRetriever);

        Observable<List<String>> fetchAllIds(Function1<? super BaseViewModel, String> idRetriever);

        Observable<List<MusicTrack>> getAllTracks();

        DbUtil.WhereClause getFilterByIds(List<String> idList, String field);

        Observable<MusicTrack> getTrack(int position);

        int getTrackTotalCount();

        /* JADX INFO: compiled from: PlayCollectionAction.kt */
        @Metadata(k = 3, mv = {1, 6, 0}, xi = 48)
        public static final class DefaultImpls {
            public static /* synthetic */ DbUtil.WhereClause getFilterByIds$default(TrackInformationFetcher trackInformationFetcher, List list, String str, int i, Object obj) {
                if (obj != null) {
                    throw new UnsupportedOperationException("Super calls with default arguments not supported in this target, function: getFilterByIds");
                }
                if ((i & 2) != 0) {
                    str = "asin";
                }
                return trackInformationFetcher.getFilterByIds(list, str);
            }

            public static DbUtil.WhereClause getFilterByIds(TrackInformationFetcher trackInformationFetcher, List<String> idList, String field) {
                Intrinsics.checkNotNullParameter(trackInformationFetcher, "this");
                Intrinsics.checkNotNullParameter(idList, "idList");
                Intrinsics.checkNotNullParameter(field, "field");
                return new DbUtil.WhereClause(field, idList);
            }

            public static Observable<List<String>> fetchAllIds(final TrackInformationFetcher trackInformationFetcher, final Function1<? super BaseViewModel, String> idRetriever) {
                Intrinsics.checkNotNullParameter(trackInformationFetcher, "this");
                Intrinsics.checkNotNullParameter(idRetriever, "idRetriever");
                Observable<List<String>> observableCreate = Observable.create(new Observable.OnSubscribe() { // from class: com.amazon.mp3.detailpages.actions.PlayCollectionAction$TrackInformationFetcher$DefaultImpls$$ExternalSyntheticLambda0
                    @Override // rx.functions.Action1
                    public final void call(Object obj) {
                        PlayCollectionAction.TrackInformationFetcher.DefaultImpls.m808fetchAllIds$lambda0(trackInformationFetcher, idRetriever, (Subscriber) obj);
                    }
                });
                Intrinsics.checkNotNullExpressionValue(observableCreate, "create {\n               …Completed()\n            }");
                return observableCreate;
            }

            /* JADX INFO: Access modifiers changed from: private */
            /* JADX INFO: renamed from: fetchAllIds$lambda-0, reason: not valid java name */
            public static void m808fetchAllIds$lambda0(TrackInformationFetcher this$0, Function1 idRetriever, Subscriber subscriber) {
                Intrinsics.checkNotNullParameter(this$0, "this$0");
                Intrinsics.checkNotNullParameter(idRetriever, "$idRetriever");
                subscriber.onNext(this$0.allIds(idRetriever));
                subscriber.onCompleted();
            }
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    public static /* synthetic */ void startNowPlayingActivity$default(PlayCollectionAction playCollectionAction, Activity activity, boolean z, String str, String str2, Uri uri, int i, boolean z2, boolean z3, Observable observable, String str3, boolean z4, boolean z5, List list, int i2, Object obj) {
        if (obj != null) {
            throw new UnsupportedOperationException("Super calls with default arguments not supported in this target, function: startNowPlayingActivity");
        }
        playCollectionAction.startNowPlayingActivity(activity, z, str, str2, uri, i, z2, z3, observable, (i2 & 512) != 0 ? null : str3, (i2 & 1024) != 0 ? false : z4, (i2 & 2048) != 0 ? false : z5, (i2 & 4096) != 0 ? null : list);
    }

    @SuppressLint({"CheckResult"})
    public final void startNowPlayingActivity(Activity activity, boolean isDownloaded, String collectionId, String collectionName, Uri collectionUri, int trackPosition, boolean shuffleOn, boolean showNowPlaying, Observable<DbUtil.WhereClause> whereClause, String sortOrder, boolean isCustomerSelectedEntity, boolean isRefinementFilterApplied, List<? extends PlaybackAugmentation> augmentations) {
        Intrinsics.checkNotNullParameter(activity, "activity");
        Intrinsics.checkNotNullParameter(collectionName, "collectionName");
        Intrinsics.checkNotNullParameter(whereClause, "whereClause");
        boolean z = false;
        if (collectionUri != null && MediaProvider.isSmartPlaylist(collectionUri) && Intrinsics.areEqual(collectionUri.getPathSegments().get(4), "2")) {
            z = true;
        }
        if (!z && !isDownloaded && !ConnectivityUtil.hasAnyInternetConnection(activity)) {
            ConnectivityUtil.checkConnectivityAndShowDialog(activity);
        } else {
            beginPlayback(collectionName, activity, trackPosition, shuffleOn, collectionUri, showNowPlaying, whereClause, sortOrder, isCustomerSelectedEntity, isRefinementFilterApplied, augmentations);
        }
    }

    private final void beginPlayback(final String collectionName, final Activity activity, final int trackPosition, final boolean shuffleOn, final Uri collectionUri, final boolean isFullScreen, final Observable<DbUtil.WhereClause> whereClause, final String sortOrder, final boolean isCustomerSelectedEntity, final boolean isRefinementFilterApplied, final List<? extends PlaybackAugmentation> augmentations) {
        this.trackInfoFetcher.getAllTracks().subscribeOn(Schedulers.io()).observeOn(Schedulers.computation()).subscribe(new Action1() { // from class: com.amazon.mp3.detailpages.actions.PlayCollectionAction$$ExternalSyntheticLambda0
            @Override // rx.functions.Action1
            public final void call(Object obj) {
                PlayCollectionAction.m805beginPlayback$lambda2(collectionUri, this, sortOrder, isRefinementFilterApplied, activity, collectionName, trackPosition, shuffleOn, isFullScreen, isCustomerSelectedEntity, augmentations, whereClause, (List) obj);
            }
        }, new Action1() { // from class: com.amazon.mp3.detailpages.actions.PlayCollectionAction$$ExternalSyntheticLambda1
            @Override // rx.functions.Action1
            public final void call(Object obj) {
                PlayCollectionAction.m806beginPlayback$lambda3(this.f$0, (Throwable) obj);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: beginPlayback$lambda-2, reason: not valid java name */
    public static final void m805beginPlayback$lambda2(final Uri uri, final PlayCollectionAction this$0, final String str, boolean z, final Activity activity, String collectionName, final int i, final boolean z2, final boolean z3, boolean z4, List list, final Observable whereClause, List list2) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        Intrinsics.checkNotNullParameter(activity, "$activity");
        Intrinsics.checkNotNullParameter(collectionName, "$collectionName");
        Intrinsics.checkNotNullParameter(whereClause, "$whereClause");
        if (uri == null) {
            return;
        }
        StartPlaybackUtil startPlaybackUtil = StartPlaybackUtil.INSTANCE;
        PlaybackPageType pageType = this$0.playbackMetricInfo.getPageType();
        ControlSource APP_UI = ControlSource.APP_UI;
        boolean z5 = str != null || (list2.size() < this$0.getTrackInfoFetcher().getTrackTotalCount() && z);
        boolean z6 = UserSubscriptionUtil.isNightwingOnly() && !Feature.should_migrate_free_to_cq_for_non_station.isEnabled();
        NoOpCloudQueueClientHandler noOpCloudQueueClientHandler = new NoOpCloudQueueClientHandler() { // from class: com.amazon.mp3.detailpages.actions.PlayCollectionAction$beginPlayback$1$1$1
            @Override // com.amazon.mp3.cloudqueue.NoOpCloudQueueClientHandler
            public void handlePlayback() {
                this.this$0.beginPlayQueuePlayback(activity, i, z2, uri, whereClause, z3, str);
            }
        };
        Intrinsics.checkNotNullExpressionValue(pageType, "pageType");
        Intrinsics.checkNotNullExpressionValue(APP_UI, "APP_UI");
        StartPlaybackUtil.startCollectionPlayback(activity, (Data.MAX_DATA_BYTES & 2) != 0 ? null : collectionName, uri, list2, (Data.MAX_DATA_BYTES & 16) != 0 ? 0 : i, (Data.MAX_DATA_BYTES & 32) != 0 ? false : z2, (Data.MAX_DATA_BYTES & 64) != 0 ? true : z3, (Data.MAX_DATA_BYTES & 128) != 0 ? null : str, pageType, (Data.MAX_DATA_BYTES & 512) != 0 ? false : z4, APP_UI, (Data.MAX_DATA_BYTES & 2048) != 0 ? null : null, (Data.MAX_DATA_BYTES & 4096) != 0 ? false : z5, (Data.MAX_DATA_BYTES & 8192) != 0 ? null : null, (Data.MAX_DATA_BYTES & 16384) != 0 ? null : list, (Data.MAX_DATA_BYTES & 32768) != 0 ? false : z6, true, noOpCloudQueueClientHandler);
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: beginPlayback$lambda-3, reason: not valid java name */
    public static final void m806beginPlayback$lambda3(PlayCollectionAction this$0, Throwable th) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        Log.error(this$0.TAG, "beginPlayback failed", th);
    }

    public final void beginPlayQueuePlayback(final Activity activity, final int trackPosition, final boolean shuffleOn, final Uri collectionUri, Observable<DbUtil.WhereClause> whereClause, final boolean showNowPlaying, final String sortOrder) {
        Intrinsics.checkNotNullParameter(activity, "activity");
        Intrinsics.checkNotNullParameter(whereClause, "whereClause");
        final long jNow = Clock.now();
        fetchPlaybackDependencies(whereClause, trackPosition).observeOn(AndroidSchedulers.mainThread()).subscribe(new Action1() { // from class: com.amazon.mp3.detailpages.actions.PlayCollectionAction$$ExternalSyntheticLambda2
            @Override // rx.functions.Action1
            public final void call(Object obj) {
                PlayCollectionAction.m803beginPlayQueuePlayback$lambda4(collectionUri, sortOrder, trackPosition, shuffleOn, this, jNow, showNowPlaying, activity, (Pair) obj);
            }
        }, new Action1() { // from class: com.amazon.mp3.detailpages.actions.PlayCollectionAction$$ExternalSyntheticLambda3
            @Override // rx.functions.Action1
            public final void call(Object obj) {
                PlayCollectionAction.m804beginPlayQueuePlayback$lambda5(this.f$0, (Throwable) obj);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: beginPlayQueuePlayback$lambda-4, reason: not valid java name */
    public static final void m803beginPlayQueuePlayback$lambda4(Uri uri, String str, int i, boolean z, PlayCollectionAction this$0, long j, boolean z2, Activity activity, Pair pair) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        Intrinsics.checkNotNullParameter(activity, "$activity");
        DbUtil.WhereClause whereClause = (DbUtil.WhereClause) pair.component1();
        PlayQueueSequencer.getInstance().start(ControlSource.APP_UI, uri, whereClause == null ? null : whereClause.getClause(), whereClause == null ? null : whereClause.getArgs(), str, i, z, this$0.playbackMetricInfo, (MusicTrack) pair.component2(), j);
        if (z2) {
            activity.startActivity(LibraryActivityFactory.getIntentForContentUri(activity, MediaProvider.NowPlaying.CONTENT_URI));
            activity.overridePendingTransition(R.anim.slide_in_up, R.anim.fade_out);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: beginPlayQueuePlayback$lambda-5, reason: not valid java name */
    public static final void m804beginPlayQueuePlayback$lambda5(PlayCollectionAction this$0, Throwable th) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        Log.error(this$0.TAG, "beginPlayQueuePlayback failed", th);
    }

    private final Observable<Pair<DbUtil.WhereClause, MusicTrack>> fetchPlaybackDependencies(Observable<DbUtil.WhereClause> whereClause, int position) {
        Observable<Pair<DbUtil.WhereClause, MusicTrack>> observableCombineLatest = Observable.combineLatest(whereClause, fetchTrack(position), new Func2() { // from class: com.amazon.mp3.detailpages.actions.PlayCollectionAction$$ExternalSyntheticLambda4
            @Override // rx.functions.Func2
            public final Object call(Object obj, Object obj2) {
                return PlayCollectionAction.m807fetchPlaybackDependencies$lambda6((DbUtil.WhereClause) obj, (MusicTrack) obj2);
            }
        });
        Intrinsics.checkNotNullExpressionValue(observableCombineLatest, "combineLatest(whereClaus…r, musicTrack)\n        })");
        return observableCombineLatest;
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: fetchPlaybackDependencies$lambda-6, reason: not valid java name */
    public static final Pair m807fetchPlaybackDependencies$lambda6(DbUtil.WhereClause whereClause, MusicTrack musicTrack) {
        return new Pair(whereClause, musicTrack);
    }

    private final Observable<MusicTrack> fetchTrack(int position) {
        Observable<MusicTrack> observableObserveOn = this.trackInfoFetcher.getTrack(position).observeOn(Schedulers.io());
        Intrinsics.checkNotNullExpressionValue(observableObserveOn, "trackInfoFetcher\n       …bserveOn(Schedulers.io())");
        return observableObserveOn;
    }
}
