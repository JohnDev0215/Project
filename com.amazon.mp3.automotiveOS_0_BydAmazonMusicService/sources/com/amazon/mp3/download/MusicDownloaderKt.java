package com.amazon.mp3.download;

import android.app.Activity;
import android.database.sqlite.SQLiteDatabase;
import androidx.fragment.app.FragmentActivity;
import com.amazon.digitalmusicplayback.DownloadPriority;
import com.amazon.digitalmusicplayback.DownloadReason;
import com.amazon.digitalmusicplayback.EngineError;
import com.amazon.digitalmusicplayback.ErrorCode;
import com.amazon.identity.auth.device.api.MultipleAccountManager;
import com.amazon.mp3.download.MusicDownloaderKt;
import com.amazon.mp3.download.generator.ItemGenerator;
import com.amazon.mp3.library.item.MusicTrack;
import com.amazon.mp3.library.item.TrackType;
import com.amazon.mp3.library.provider.source.cirrus.CirrusDatabase;
import com.amazon.mp3.library.provider.source.cirrus.dbvalueupdate.TrackDownloadStateUpdater;
import com.amazon.mp3.library.provider.source.local.DeleteUtil;
import com.amazon.mp3.playback.harley.HarleyDownloadCoordinatorListener;
import com.amazon.mp3.prime.TrackRequester;
import com.amazon.mp3.service.metrics.MetricsLogger;
import com.amazon.mp3.util.Log;
import com.amazon.mp3.util.extensions.ActivityKt;
import com.amazon.music.downloads.Item;
import com.amazon.music.downloads.PriorityInfo;
import com.amazon.music.externalstorage.StorageLocation;
import com.amazon.music.externalstorage.StorageLocationPreference;
import com.amazon.music.media.playback.util.AndroidUtils;
import com.amazon.music.metrics.mts.event.definition.flex.FlexEvent;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.Set;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.StringsKt__StringsKt;
import rx.Observable;
import rx.Subscriber;
import rx.functions.Action0;
import rx.functions.Action1;
import rx.functions.Func1;
import rx.schedulers.Schedulers;

/* JADX INFO: compiled from: MusicDownloaderKt.kt */
/* JADX INFO: loaded from: classes3.dex */
@Metadata(bv = {}, d1 = {"\u0000D\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0003\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0010#\n\u0002\b\u0006\bÆ\u0002\u0018\u00002\u00020\u0001:\u0001\u001dB\t\b\u0002¢\u0006\u0004\b\u001b\u0010\u001cJ\u0016\u0010\u0005\u001a\b\u0012\u0004\u0012\u00020\u00020\u00042\u0006\u0010\u0003\u001a\u00020\u0002H\u0002J\u0010\u0010\t\u001a\u00020\b2\u0006\u0010\u0007\u001a\u00020\u0006H\u0002J\u0016\u0010\u000b\u001a\b\u0012\u0004\u0012\u00020\n0\u00042\u0006\u0010\u0003\u001a\u00020\u0002H\u0002J\u0018\u0010\u0010\u001a\u00020\b2\u0006\u0010\r\u001a\u00020\f2\b\u0010\u000f\u001a\u0004\u0018\u00010\u000eJ\u0010\u0010\u0013\u001a\u00020\b2\b\u0010\u0012\u001a\u0004\u0018\u00010\u0011J\u0010\u0010\u0014\u001a\u00020\b2\b\u0010\u0012\u001a\u0004\u0018\u00010\u0011R\u001c\u0010\u0016\u001a\n \u0015*\u0004\u0018\u00010\f0\f8\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b\u0016\u0010\u0017R\u001a\u0010\u0019\u001a\b\u0012\u0004\u0012\u00020\u00110\u00188\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b\u0019\u0010\u001a¨\u0006\u001e"}, d2 = {"Lcom/amazon/mp3/download/MusicDownloaderKt;", "", "Lcom/amazon/mp3/library/item/MusicTrack;", "track", "Lrx/Observable;", "getDeleteObservable", "", "e", "", "onRedownloadTrackError", "Lcom/amazon/music/downloads/Item;", "redownloadTrack", "", "asin", "Landroid/app/Activity;", MultipleAccountManager.SessionPackageMappingType.JSON_KEY_SESSION_PACKAGE_MAPPING_REMOVE_ACTIVITY_CLASS_NAME, "showQueueInvalidTrackRedownloadDialog", "Lcom/amazon/mp3/download/OfflineTrackCorruptedListener;", "offlineTrackCorruptedListener", "registerOfflineTrackCorruptedListener", "unregisterOfflineTrackCorruptedListener", "kotlin.jvm.PlatformType", "TAG", "Ljava/lang/String;", "", "offlineCorruptionListeners", "Ljava/util/Set;", "<init>", "()V", "RedownloadInvalidDownloadListener", "DigitalMusicAndroid3P_automotiveProdRelease"}, k = 1, mv = {1, 6, 0})
public final class MusicDownloaderKt {
    public static final MusicDownloaderKt INSTANCE = new MusicDownloaderKt();
    private static final String TAG = MusicDownloaderKt.class.getSimpleName();
    private static final Set<OfflineTrackCorruptedListener> offlineCorruptionListeners = new LinkedHashSet();

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: showQueueInvalidTrackRedownloadDialog$lambda-3, reason: not valid java name */
    public static final void m930showQueueInvalidTrackRedownloadDialog$lambda3() {
    }

    private MusicDownloaderKt() {
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final Observable<MusicTrack> getDeleteObservable(final MusicTrack track) {
        Observable<MusicTrack> observableCreate = Observable.create(new Observable.OnSubscribe() { // from class: com.amazon.mp3.download.MusicDownloaderKt$$ExternalSyntheticLambda3
            @Override // rx.functions.Action1
            public final void call(Object obj) {
                MusicDownloaderKt.m925getDeleteObservable$lambda0(track, (Subscriber) obj);
            }
        });
        Intrinsics.checkNotNullExpressionValue(observableCreate, "create<MusicTrack> { sub…)\n            }\n        }");
        return observableCreate;
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: getDeleteObservable$lambda-0, reason: not valid java name */
    public static final void m925getDeleteObservable$lambda0(final MusicTrack track, final Subscriber subscriber) {
        Intrinsics.checkNotNullParameter(track, "$track");
        DeleteUtil.deleteTrackByAsin(track.getAsin(), new Function0<Unit>() { // from class: com.amazon.mp3.download.MusicDownloaderKt$getDeleteObservable$1$1
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
                Log.warning(MusicDownloaderKt.TAG, "Deleted Invalid Download: asin = " + track + ".asin");
                subscriber.onNext(track);
                subscriber.onCompleted();
            }
        });
    }

    public final void showQueueInvalidTrackRedownloadDialog(final String asin, final Activity activity) {
        Intrinsics.checkNotNullParameter(asin, "asin");
        final FragmentActivity fragmentActivity = activity instanceof FragmentActivity ? (FragmentActivity) activity : null;
        boolean z = false;
        if (fragmentActivity != null && !ActivityKt.isAvailable(fragmentActivity)) {
            z = true;
        }
        if (z) {
            return;
        }
        Observable.create(new Observable.OnSubscribe() { // from class: com.amazon.mp3.download.MusicDownloaderKt$$ExternalSyntheticLambda0
            @Override // rx.functions.Action1
            public final void call(Object obj) {
                MusicDownloaderKt.m928showQueueInvalidTrackRedownloadDialog$lambda2(activity, asin, fragmentActivity, (Subscriber) obj);
            }
        }).subscribeOn(Schedulers.computation()).toCompletable().subscribe(new Action0() { // from class: com.amazon.mp3.download.MusicDownloaderKt$$ExternalSyntheticLambda1
            @Override // rx.functions.Action0
            public final void call() {
                MusicDownloaderKt.m930showQueueInvalidTrackRedownloadDialog$lambda3();
            }
        }, new Action1() { // from class: com.amazon.mp3.download.MusicDownloaderKt$$ExternalSyntheticLambda2
            @Override // rx.functions.Action1
            public final void call(Object obj) {
                MusicDownloaderKt.m931showQueueInvalidTrackRedownloadDialog$lambda4((Throwable) obj);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: showQueueInvalidTrackRedownloadDialog$lambda-2, reason: not valid java name */
    public static final void m928showQueueInvalidTrackRedownloadDialog$lambda2(Activity activity, String asin, final FragmentActivity fragmentActivity, Subscriber subscriber) {
        Intrinsics.checkNotNullParameter(asin, "$asin");
        MusicTrack trackFromAsin = new TrackRequester(activity).getTrackFromAsin(asin);
        if (trackFromAsin == null) {
            subscriber.onCompleted();
            return;
        }
        final RedownloadCorruptTracksDialog redownloadCorruptTracksDialogNewInstance = RedownloadCorruptTracksDialog.INSTANCE.newInstance(new MusicDownloaderKt$showQueueInvalidTrackRedownloadDialog$1$dialog$1(trackFromAsin), new Function0<Unit>() { // from class: com.amazon.mp3.download.MusicDownloaderKt$showQueueInvalidTrackRedownloadDialog$1$dialog$2
            @Override // kotlin.jvm.functions.Function0
            public /* bridge */ /* synthetic */ Unit invoke() {
                invoke2();
                return Unit.INSTANCE;
            }

            /* JADX INFO: renamed from: invoke, reason: avoid collision after fix types in other method */
            public final void invoke2() {
                Log.verbose(MusicDownloaderKt.TAG, "Dialog Dismissed");
            }
        });
        boolean z = false;
        if (fragmentActivity != null && ActivityKt.isAvailable(fragmentActivity)) {
            z = true;
        }
        if (z && fragmentActivity != null) {
            fragmentActivity.runOnUiThread(new Runnable() { // from class: com.amazon.mp3.download.MusicDownloaderKt$$ExternalSyntheticLambda6
                @Override // java.lang.Runnable
                public final void run() {
                    MusicDownloaderKt.m929showQueueInvalidTrackRedownloadDialog$lambda2$lambda1(redownloadCorruptTracksDialogNewInstance, fragmentActivity);
                }
            });
        }
        subscriber.onCompleted();
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: showQueueInvalidTrackRedownloadDialog$lambda-2$lambda-1, reason: not valid java name */
    public static final void m929showQueueInvalidTrackRedownloadDialog$lambda2$lambda1(RedownloadCorruptTracksDialog dialog, FragmentActivity fragmentActivity) {
        Intrinsics.checkNotNullParameter(dialog, "$dialog");
        dialog.show(fragmentActivity.getSupportFragmentManager(), RedownloadCorruptTracksDialog.class.getSimpleName());
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: showQueueInvalidTrackRedownloadDialog$lambda-4, reason: not valid java name */
    public static final void m931showQueueInvalidTrackRedownloadDialog$lambda4(Throwable th) {
        Log.error(TAG, "Error attempting to show track redownload dialog: ", th);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void onRedownloadTrackError(Throwable e) {
        String str = TAG;
        Log.error(str, "ItemGenerator error occurred on redownloadTrack()");
        if (e instanceof TimeoutException) {
            Log.error(str, "Timeout error occurred, there might be an ANR when it generated the download item.");
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final Observable<Item> redownloadTrack(final MusicTrack track) {
        Observable<Item> observableDoOnNext = Observable.create(new Observable.OnSubscribe() { // from class: com.amazon.mp3.download.MusicDownloaderKt$$ExternalSyntheticLambda4
            @Override // rx.functions.Action1
            public final void call(Object obj) {
                MusicDownloaderKt.m926redownloadTrack$lambda5(track, (Subscriber) obj);
            }
        }).subscribeOn(Schedulers.computation()).timeout(5L, TimeUnit.SECONDS).doOnNext(new Action1() { // from class: com.amazon.mp3.download.MusicDownloaderKt$$ExternalSyntheticLambda5
            @Override // rx.functions.Action1
            public final void call(Object obj) {
                MusicDownloaderKt.m927redownloadTrack$lambda6(track, (Item) obj);
            }
        });
        Intrinsics.checkNotNullExpressionValue(observableDoOnNext, "create<Item>{ subscriber…) }\n                    }");
        return observableDoOnNext;
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: redownloadTrack$lambda-5, reason: not valid java name */
    public static final void m926redownloadTrack$lambda5(MusicTrack track, Subscriber subscriber) {
        Intrinsics.checkNotNullParameter(track, "$track");
        Activity currentActivity = AndroidUtils.getCurrentActivity();
        if ((currentActivity == null || ActivityKt.isAvailable(currentActivity)) ? false : true) {
            Log.warning(TAG, "Activity is null, can't redownload track");
        }
        Log.warning(TAG, Intrinsics.stringPlus("Redownloading Invalid Download: asin = ", track.getAsin()));
        subscriber.onNext(new ItemGenerator(currentActivity, MusicDownloader.getInstance(currentActivity).getStorageLocationFileManager()).generate(track.getContentUri(), track.getType().getTypeId() != TrackType.LIBRARY.getTypeId(), new PriorityInfo(DownloadPriority.BACKFILL.name(), DownloadReason.AUTOMATICDOWNLOAD.name()), null));
        subscriber.onCompleted();
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: redownloadTrack$lambda-6, reason: not valid java name */
    public static final void m927redownloadTrack$lambda6(MusicTrack track, Item item) {
        Intrinsics.checkNotNullParameter(track, "$track");
        Activity currentActivity = AndroidUtils.getCurrentActivity();
        boolean z = false;
        if (currentActivity != null && !ActivityKt.isAvailable(currentActivity)) {
            z = true;
        }
        if (z) {
            Log.warning(TAG, "Activity is null, can't redownload track");
        } else {
            MusicDownloader.getInstance(currentActivity).download(track.getAsin(), item);
        }
    }

    public final void registerOfflineTrackCorruptedListener(OfflineTrackCorruptedListener offlineTrackCorruptedListener) {
        if (offlineTrackCorruptedListener == null) {
            return;
        }
        offlineCorruptionListeners.add(offlineTrackCorruptedListener);
    }

    public final void unregisterOfflineTrackCorruptedListener(OfflineTrackCorruptedListener offlineTrackCorruptedListener) {
        if (offlineTrackCorruptedListener == null) {
            return;
        }
        offlineCorruptionListeners.remove(offlineTrackCorruptedListener);
    }

    /* JADX INFO: compiled from: MusicDownloaderKt.kt */
    @Metadata(d1 = {"\u00000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0000\u0018\u00002\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0002J\u0016\u0010\u0003\u001a\b\u0012\u0004\u0012\u00020\u00050\u00042\u0006\u0010\u0006\u001a\u00020\u0007H\u0002J\u0018\u0010\b\u001a\u00020\t2\u0006\u0010\u0006\u001a\u00020\u00072\u0006\u0010\n\u001a\u00020\u000bH\u0016J\u0018\u0010\f\u001a\u00020\t2\u0006\u0010\u0006\u001a\u00020\u00072\u0006\u0010\r\u001a\u00020\u000eH\u0002¨\u0006\u000f"}, d2 = {"Lcom/amazon/mp3/download/MusicDownloaderKt$RedownloadInvalidDownloadListener;", "Lcom/amazon/mp3/playback/harley/HarleyDownloadCoordinatorListener;", "()V", "getCorruptedTracksObservable", "Lrx/Observable;", "Lcom/amazon/mp3/library/item/MusicTrack;", "id", "", "invalidDownloadDetected", "", "engineError", "Lcom/amazon/digitalmusicplayback/EngineError;", "sendCorruptedDownloadDetectedMetric", "hasSuitableConnection", "", "DigitalMusicAndroid3P_automotiveProdRelease"}, k = 1, mv = {1, 6, 0}, xi = 48)
    public static final class RedownloadInvalidDownloadListener extends HarleyDownloadCoordinatorListener {

        /* JADX INFO: compiled from: MusicDownloaderKt.kt */
        @Metadata(k = 3, mv = {1, 6, 0}, xi = 48)
        public /* synthetic */ class WhenMappings {
            public static final /* synthetic */ int[] $EnumSwitchMapping$0;

            static {
                int[] iArr = new int[ErrorCode.values().length];
                iArr[ErrorCode.FILEFORMAT.ordinal()] = 1;
                $EnumSwitchMapping$0 = iArr;
            }
        }

        /* JADX INFO: Access modifiers changed from: private */
        /* JADX INFO: renamed from: invalidDownloadDetected$lambda-2, reason: not valid java name */
        public static final void m936invalidDownloadDetected$lambda2(Item item) {
        }

        @Override // com.amazon.mp3.playback.harley.HarleyDownloadCoordinatorListener, com.amazon.digitalmusicplayback.DownloadCoordinatorListener
        public void invalidDownloadDetected(String id, EngineError engineError) {
            Intrinsics.checkNotNullParameter(id, "id");
            Intrinsics.checkNotNullParameter(engineError, "engineError");
            Log.warning(MusicDownloaderKt.TAG, "Invalid Download: id = " + id + ", EngineError = " + engineError);
            ErrorCode code = engineError.getCode();
            if ((code == null ? -1 : WhenMappings.$EnumSwitchMapping$0[code.ordinal()]) != 1) {
                Log.warning(MusicDownloaderKt.TAG, Intrinsics.stringPlus("This error type is not handled: ", engineError));
            } else {
                getCorruptedTracksObservable(id).concatMap(new Func1() { // from class: com.amazon.mp3.download.MusicDownloaderKt$RedownloadInvalidDownloadListener$$ExternalSyntheticLambda0
                    @Override // rx.functions.Func1
                    public final Object call(Object obj) {
                        return MusicDownloaderKt.RedownloadInvalidDownloadListener.m934invalidDownloadDetected$lambda0((MusicTrack) obj);
                    }
                }).concatMap(new Func1() { // from class: com.amazon.mp3.download.MusicDownloaderKt$RedownloadInvalidDownloadListener$$ExternalSyntheticLambda1
                    @Override // rx.functions.Func1
                    public final Object call(Object obj) {
                        return MusicDownloaderKt.RedownloadInvalidDownloadListener.m935invalidDownloadDetected$lambda1((MusicTrack) obj);
                    }
                }).subscribe(new Action1() { // from class: com.amazon.mp3.download.MusicDownloaderKt$RedownloadInvalidDownloadListener$$ExternalSyntheticLambda2
                    @Override // rx.functions.Action1
                    public final void call(Object obj) {
                        MusicDownloaderKt.RedownloadInvalidDownloadListener.m936invalidDownloadDetected$lambda2((Item) obj);
                    }
                }, new Action1() { // from class: com.amazon.mp3.download.MusicDownloaderKt$RedownloadInvalidDownloadListener$$ExternalSyntheticLambda3
                    @Override // rx.functions.Action1
                    public final void call(Object obj) {
                        MusicDownloaderKt.RedownloadInvalidDownloadListener.m937invalidDownloadDetected$lambda3((Throwable) obj);
                    }
                });
            }
        }

        /* JADX INFO: Access modifiers changed from: private */
        /* JADX INFO: renamed from: invalidDownloadDetected$lambda-0, reason: not valid java name */
        public static final Observable m934invalidDownloadDetected$lambda0(MusicTrack track) {
            MusicDownloaderKt musicDownloaderKt = MusicDownloaderKt.INSTANCE;
            Intrinsics.checkNotNullExpressionValue(track, "track");
            return musicDownloaderKt.getDeleteObservable(track);
        }

        /* JADX INFO: Access modifiers changed from: private */
        /* JADX INFO: renamed from: invalidDownloadDetected$lambda-1, reason: not valid java name */
        public static final Observable m935invalidDownloadDetected$lambda1(MusicTrack track) {
            MusicDownloaderKt musicDownloaderKt = MusicDownloaderKt.INSTANCE;
            Intrinsics.checkNotNullExpressionValue(track, "track");
            return musicDownloaderKt.redownloadTrack(track);
        }

        /* JADX INFO: Access modifiers changed from: private */
        /* JADX INFO: renamed from: invalidDownloadDetected$lambda-3, reason: not valid java name */
        public static final void m937invalidDownloadDetected$lambda3(Throwable e) {
            MusicDownloaderKt musicDownloaderKt = MusicDownloaderKt.INSTANCE;
            Intrinsics.checkNotNullExpressionValue(e, "e");
            musicDownloaderKt.onRedownloadTrackError(e);
        }

        private final Observable<MusicTrack> getCorruptedTracksObservable(final String id) {
            Observable<MusicTrack> observableSubscribeOn = Observable.create(new Observable.OnSubscribe() { // from class: com.amazon.mp3.download.MusicDownloaderKt$RedownloadInvalidDownloadListener$$ExternalSyntheticLambda4
                @Override // rx.functions.Action1
                public final void call(Object obj) {
                    MusicDownloaderKt.RedownloadInvalidDownloadListener.m933getCorruptedTracksObservable$lambda5(id, this, (Subscriber) obj);
                }
            }).subscribeOn(Schedulers.computation());
            Intrinsics.checkNotNullExpressionValue(observableSubscribeOn, "create<MusicTrack> { sub…Schedulers.computation())");
            return observableSubscribeOn;
        }

        /* JADX INFO: Access modifiers changed from: private */
        /* JADX INFO: renamed from: getCorruptedTracksObservable$lambda-5, reason: not valid java name */
        public static final void m933getCorruptedTracksObservable$lambda5(String id, RedownloadInvalidDownloadListener this$0, Subscriber subscriber) {
            Intrinsics.checkNotNullParameter(id, "$id");
            Intrinsics.checkNotNullParameter(this$0, "this$0");
            Activity currentActivity = AndroidUtils.getCurrentActivity();
            boolean z = false;
            if (currentActivity != null && ActivityKt.isAvailable(currentActivity)) {
                z = true;
            }
            if (z) {
                String strSubstring = id.substring(StringsKt__StringsKt.lastIndexOf$default((CharSequence) id, "/", 0, false, 6, (Object) null) + 1);
                Intrinsics.checkNotNullExpressionValue(strSubstring, "this as java.lang.String).substring(startIndex)");
                MusicTrack trackFromAsin = new TrackRequester(currentActivity).getTrackFromAsin(strSubstring);
                if (trackFromAsin == null) {
                    subscriber.onCompleted();
                    return;
                }
                TrackDownloadStateUpdater trackDownloadStateUpdater = TrackDownloadStateUpdater.INSTANCE;
                SQLiteDatabase writableDatabase = CirrusDatabase.getWritableDatabase(currentActivity);
                Intrinsics.checkNotNullExpressionValue(writableDatabase, "getWritableDatabase(currentActivity)");
                trackDownloadStateUpdater.update(writableDatabase, trackFromAsin, 7);
                Iterator it = MusicDownloaderKt.offlineCorruptionListeners.iterator();
                while (it.hasNext()) {
                    ((OfflineTrackCorruptedListener) it.next()).onOfflineTrackCorrupted(strSubstring);
                }
                this$0.sendCorruptedDownloadDetectedMetric(id, MusicDownloader.getInstance(currentActivity).hasSuitableConnection(true));
                subscriber.onNext(trackFromAsin);
            }
            subscriber.onCompleted();
        }

        private final void sendCorruptedDownloadDetectedMetric(String id, boolean hasSuitableConnection) {
            MetricsLogger.sendEvent(FlexEvent.builder("corruptedDownloadDetected").withFlexStr1(id).withFlexStr2(hasSuitableConnection ? "Online" : "Offline").withFlexStr3(AndroidUtils.isApplicationInForeground() ? "Foreground" : "Background").withFlexStr4(new StorageLocationPreference(AndroidUtils.getCurrentActivity()).getPreferredStorageLocation() == StorageLocation.DEVICE ? "Internal Storage" : "External Storage").build());
        }
    }
}
