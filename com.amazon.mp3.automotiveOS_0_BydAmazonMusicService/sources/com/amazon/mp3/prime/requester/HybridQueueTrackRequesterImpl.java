package com.amazon.mp3.prime.requester;

import android.annotation.SuppressLint;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabaseLockedException;
import android.net.Uri;
import com.amazon.digitalmusicxp.models.QueueEntityMetadata;
import com.amazon.digitalmusicxp.models.QueueSequenceSlice;
import com.amazon.digitalmusicxp.types.EntityReference;
import com.amazon.mp3.AmazonApplication;
import com.amazon.mp3.catalog.fragment.datasource.dao.AmplifyPageModelDAO;
import com.amazon.mp3.cloudqueue.sequencer.CloudQueueSequencer;
import com.amazon.mp3.external.api.uri.QuerySongByAsin;
import com.amazon.mp3.library.provider.MediaProvider;
import com.amazon.mp3.library.provider.source.nowplaying.PlayQueueTrack;
import com.amazon.mp3.playback.harley.PlayQueueSequencer;
import com.amazon.mp3.prime.CTAPrimeCache;
import com.amazon.mp3.prime.browse.metadata.PrimeTrack;
import com.amazon.mp3.starlight.provider.HybridQueueStateProvider;
import com.amazon.mp3.util.Log;
import com.amazon.mp3.util.extensions.ContextKt;
import com.amazon.music.media.playback.ControlSource;
import com.amazon.music.media.playback.Playback;
import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import kotlin.Lazy;
import kotlin.LazyKt__LazyJVMKt;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.collections.ArraysKt___ArraysKt;
import kotlin.collections.CollectionsKt__CollectionsJVMKt;
import kotlin.collections.CollectionsKt__CollectionsKt;
import kotlin.collections.CollectionsKt__IterablesKt;
import kotlin.collections.CollectionsKt__MutableCollectionsKt;
import kotlin.collections.CollectionsKt___CollectionsKt;
import kotlin.collections.MapsKt__MapsKt;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Ref;
import kotlin.jvm.internal.Reflection;
import kotlin.reflect.KClass;
import kotlin.sequences.SequencesKt__SequencesKt;
import kotlin.sequences.SequencesKt___SequencesKt;
import rx.Completable;
import rx.CompletableSubscriber;
import rx.Observable;
import rx.Subscriber;
import rx.functions.Action0;
import rx.functions.Action1;

/* JADX INFO: compiled from: HybridQueueTrackRequesterImpl.kt */
/* JADX INFO: loaded from: classes4.dex */
@Metadata(d1 = {"\u0000~\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010 \n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010%\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0010$\n\u0002\u0010!\n\u0002\b\u0002\u0018\u0000 32\u00020\u0001:\u00013B\u0015\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005¢\u0006\u0002\u0010\u0006J.\u0010\u0007\u001a\u0004\u0018\u00010\b\"\u0004\b\u0000\u0010\t2\f\u0010\n\u001a\b\u0012\u0004\u0012\u0002H\t0\u000b2\u0006\u0010\f\u001a\u00020\r2\u0006\u0010\u000e\u001a\u00020\u000fH\u0002J:\u0010\u0010\u001a\u00020\u0011\"\u0004\b\u0000\u0010\t2\f\u0010\n\u001a\b\u0012\u0004\u0012\u0002H\t0\u000b2\u0006\u0010\f\u001a\u00020\r2\u0006\u0010\u000e\u001a\u00020\u000f2\f\u0010\u0012\u001a\b\u0012\u0004\u0012\u00020\u00110\u0013H\u0002J0\u0010\u0014\u001a\u000e\u0012\u0004\u0012\u00020\u000f\u0012\u0004\u0012\u00020\u000f0\u00152\f\u0010\u0016\u001a\b\u0012\u0004\u0012\u00020\u000f0\u000b2\f\u0010\u0017\u001a\b\u0012\u0004\u0012\u00020\u00180\u000bH\u0002J\u0012\u0010\u0019\u001a\u00020\u001a2\b\u0010\u001b\u001a\u0004\u0018\u00010\u001cH\u0002J\u001d\u0010\u001d\u001a\u0004\u0018\u00010\u000f\"\u0004\b\u0000\u0010\t2\u0006\u0010\u001e\u001a\u0002H\tH\u0002¢\u0006\u0002\u0010\u001fJ.\u0010 \u001a\u0004\u0018\u00010\b\"\u0004\b\u0000\u0010\t2\f\u0010\n\u001a\b\u0012\u0004\u0012\u0002H\t0\u000b2\u0006\u0010\f\u001a\u00020\r2\u0006\u0010\u000e\u001a\u00020\u000fH\u0002J*\u0010!\u001a\b\u0012\u0004\u0012\u0002H\t0\u000b\"\u0004\b\u0000\u0010\t2\f\u0010\n\u001a\b\u0012\u0004\u0012\u0002H\t0\u000b2\u0006\u0010\"\u001a\u00020\u001aH\u0002J\u001c\u0010#\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020%0\u000b0$2\u0006\u0010&\u001a\u00020\u0003H\u0016J\u001c\u0010'\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020)0\u000b0(2\u0006\u0010&\u001a\u00020\u0003H\u0016J%\u0010*\u001a\u00020\u0011\"\u0004\b\u0000\u0010\t2\b\u0010+\u001a\u0004\u0018\u0001H\t2\u0006\u0010,\u001a\u00020\u000fH\u0002¢\u0006\u0002\u0010-J2\u0010.\u001a\u00020\u00112\b\u0010&\u001a\u0004\u0018\u00010\u00032\u0006\u0010\f\u001a\u00020\r2\u0006\u0010\u000e\u001a\u00020\u000f2\u000e\u0010/\u001a\n\u0012\u0004\u0012\u00020\u0011\u0018\u00010\u0013H\u0017J\"\u00100\u001a\u000e\u0012\u0004\u0012\u00020\u000f\u0012\u0004\u0012\u00020\u000f012\f\u0010\u0016\u001a\b\u0012\u0004\u0012\u00020\u000f02H\u0002R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000¨\u00064"}, d2 = {"Lcom/amazon/mp3/prime/requester/HybridQueueTrackRequesterImpl;", "Lcom/amazon/mp3/prime/requester/HybridQueueTrackRequester;", "mContext", "Landroid/content/Context;", "hybridQueueStateProvider", "Lcom/amazon/mp3/starlight/provider/HybridQueueStateProvider;", "(Landroid/content/Context;Lcom/amazon/mp3/starlight/provider/HybridQueueStateProvider;)V", "batchAndloadHybridQueueTracksFromAsins", "Lrx/Completable;", "T", "playQueueTracks", "", "doToggle", "", "asinToSkip", "", "batchAndloadHybridQueueTracksFromAsinsWithCallback", "", "callback", "Lkotlin/Function0;", "createToggleMapForRecentlyCachedTracks", "", "currentPlayQueueTrackAsins", "currentPlayQueueAudioVideoList", "Lcom/amazon/mp3/prime/requester/AudioVideo;", "doInsertIntoCtaPrimeCache", "", "primeTrack", "Lcom/amazon/mp3/prime/browse/metadata/PrimeTrack;", "getAsin", "playQueueTrack", "(Ljava/lang/Object;)Ljava/lang/String;", "loadPlayQueueTracksFromAsins", "prepareBatchForMuseLookup", "currentIndex", "queryCloudQueueTracks", "Lio/reactivex/Observable;", "Lcom/amazon/digitalmusicxp/models/QueueEntityMetadata;", "context", "queryCurrentPlayQueueTracks", "Lrx/Observable;", "Lcom/amazon/mp3/library/provider/source/nowplaying/PlayQueueTrack;", "replaceTrack", "currentPlayQueueTrack", "newTrackAsin", "(Ljava/lang/Object;Ljava/lang/String;)V", "requestHybridQueueAndUpdateDb", "onFinished", "requestToggleMap", "", "", "Companion", "DigitalMusicAndroid3P_automotiveProdRelease"}, k = 1, mv = {1, 6, 0}, xi = 48)
public final class HybridQueueTrackRequesterImpl implements HybridQueueTrackRequester {
    private final HybridQueueStateProvider hybridQueueStateProvider;
    private final Context mContext;

    /* JADX INFO: renamed from: Companion, reason: from kotlin metadata */
    public static final Companion INSTANCE = new Companion(null);
    private static final String TAG = HybridQueueTrackRequester.class.getSimpleName();
    private static final Lazy<Integer> BATCH_SIZE$delegate = LazyKt__LazyJVMKt.lazy(new Function0<Integer>() { // from class: com.amazon.mp3.prime.requester.HybridQueueTrackRequesterImpl$Companion$BATCH_SIZE$2
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // kotlin.jvm.functions.Function0
        public final Integer invoke() {
            return Integer.valueOf(AmazonApplication.getCapabilities().getHybridQueueBatchSize());
        }
    });
    private static final Lazy<Integer> LOOKAHEAD_WINDOW_SIZE$delegate = LazyKt__LazyJVMKt.lazy(new Function0<Integer>() { // from class: com.amazon.mp3.prime.requester.HybridQueueTrackRequesterImpl$Companion$LOOKAHEAD_WINDOW_SIZE$2
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // kotlin.jvm.functions.Function0
        public final Integer invoke() {
            return Integer.valueOf(AmazonApplication.getCapabilities().getHybridQueueLookaheadWindowSize());
        }
    });
    private static final String[] NOW_PLAYING_PROJECTION = {"_id", "uri", "udo", "shuffle", "luid", "asin", "catalog_playlist_id"};

    public HybridQueueTrackRequesterImpl(Context mContext, HybridQueueStateProvider hybridQueueStateProvider) {
        Intrinsics.checkNotNullParameter(mContext, "mContext");
        Intrinsics.checkNotNullParameter(hybridQueueStateProvider, "hybridQueueStateProvider");
        this.mContext = mContext;
        this.hybridQueueStateProvider = hybridQueueStateProvider;
    }

    @Override // com.amazon.mp3.prime.requester.HybridQueueTrackRequester
    @SuppressLint({"CheckResult"})
    public void requestHybridQueueAndUpdateDb(final Context context, final boolean doToggle, final String asinToSkip, final Function0<Unit> onFinished) {
        Intrinsics.checkNotNullParameter(asinToSkip, "asinToSkip");
        if (context == null) {
            return;
        }
        if (!CloudQueueSequencer.INSTANCE.isCloudQueuePlaying()) {
            queryCurrentPlayQueueTracks(context).subscribe(new Action1() { // from class: com.amazon.mp3.prime.requester.HybridQueueTrackRequesterImpl$$ExternalSyntheticLambda0
                @Override // rx.functions.Action1
                public final void call(Object obj) {
                    HybridQueueTrackRequesterImpl.m1587requestHybridQueueAndUpdateDb$lambda0(this.f$0, doToggle, asinToSkip, context, onFinished, (List) obj);
                }
            }, new Action1() { // from class: com.amazon.mp3.prime.requester.HybridQueueTrackRequesterImpl$$ExternalSyntheticLambda1
                @Override // rx.functions.Action1
                public final void call(Object obj) {
                    HybridQueueTrackRequesterImpl.m1588requestHybridQueueAndUpdateDb$lambda1((Throwable) obj);
                }
            });
        } else {
            queryCloudQueueTracks(context).subscribeOn(Schedulers.computation()).observeOn(AndroidSchedulers.mainThread()).singleElement().subscribe(new Consumer() { // from class: com.amazon.mp3.prime.requester.HybridQueueTrackRequesterImpl$$ExternalSyntheticLambda2
                @Override // io.reactivex.functions.Consumer
                public final void accept(Object obj) {
                    HybridQueueTrackRequesterImpl.m1589requestHybridQueueAndUpdateDb$lambda2(this.f$0, doToggle, asinToSkip, context, onFinished, (List) obj);
                }
            }, new Consumer() { // from class: com.amazon.mp3.prime.requester.HybridQueueTrackRequesterImpl$$ExternalSyntheticLambda3
                @Override // io.reactivex.functions.Consumer
                public final void accept(Object obj) {
                    HybridQueueTrackRequesterImpl.m1590requestHybridQueueAndUpdateDb$lambda3((Throwable) obj);
                }
            });
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: requestHybridQueueAndUpdateDb$lambda-0, reason: not valid java name */
    public static final void m1587requestHybridQueueAndUpdateDb$lambda0(HybridQueueTrackRequesterImpl this$0, boolean z, String asinToSkip, final Context context, final Function0 function0, List currentPlayQueueTracks) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        Intrinsics.checkNotNullParameter(asinToSkip, "$asinToSkip");
        Intrinsics.checkNotNullExpressionValue(currentPlayQueueTracks, "currentPlayQueueTracks");
        this$0.batchAndloadHybridQueueTracksFromAsinsWithCallback(currentPlayQueueTracks, z, asinToSkip, new Function0<Unit>() { // from class: com.amazon.mp3.prime.requester.HybridQueueTrackRequesterImpl$requestHybridQueueAndUpdateDb$1$1
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
                Context context2 = context;
                final Function0<Unit> function02 = function0;
                ContextKt.runOnUiThread$default(context2, 0L, new Function1<Context, Unit>() { // from class: com.amazon.mp3.prime.requester.HybridQueueTrackRequesterImpl$requestHybridQueueAndUpdateDb$1$1.1
                    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                    {
                        super(1);
                    }

                    @Override // kotlin.jvm.functions.Function1
                    public /* bridge */ /* synthetic */ Unit invoke(Context context3) {
                        invoke2(context3);
                        return Unit.INSTANCE;
                    }

                    /* JADX INFO: renamed from: invoke, reason: avoid collision after fix types in other method */
                    public final void invoke2(Context it) {
                        Intrinsics.checkNotNullParameter(it, "it");
                        Function0<Unit> function03 = function02;
                        if (function03 == null) {
                            return;
                        }
                        function03.invoke();
                    }
                }, 1, null);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: requestHybridQueueAndUpdateDb$lambda-1, reason: not valid java name */
    public static final void m1588requestHybridQueueAndUpdateDb$lambda1(Throwable th) {
        Log.error(TAG, "Error when requestHybridQueueAndUpdateDb:queryCurrentPlayQueueTracks", th);
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: requestHybridQueueAndUpdateDb$lambda-2, reason: not valid java name */
    public static final void m1589requestHybridQueueAndUpdateDb$lambda2(HybridQueueTrackRequesterImpl this$0, boolean z, String asinToSkip, final Context context, final Function0 function0, List list) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        Intrinsics.checkNotNullParameter(asinToSkip, "$asinToSkip");
        Intrinsics.checkNotNullExpressionValue(list, "list");
        this$0.batchAndloadHybridQueueTracksFromAsinsWithCallback(list, z, asinToSkip, new Function0<Unit>() { // from class: com.amazon.mp3.prime.requester.HybridQueueTrackRequesterImpl$requestHybridQueueAndUpdateDb$3$1
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
                Context context2 = context;
                final Function0<Unit> function02 = function0;
                ContextKt.runOnUiThread$default(context2, 0L, new Function1<Context, Unit>() { // from class: com.amazon.mp3.prime.requester.HybridQueueTrackRequesterImpl$requestHybridQueueAndUpdateDb$3$1.1
                    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                    {
                        super(1);
                    }

                    @Override // kotlin.jvm.functions.Function1
                    public /* bridge */ /* synthetic */ Unit invoke(Context context3) {
                        invoke2(context3);
                        return Unit.INSTANCE;
                    }

                    /* JADX INFO: renamed from: invoke, reason: avoid collision after fix types in other method */
                    public final void invoke2(Context it) {
                        Intrinsics.checkNotNullParameter(it, "it");
                        Function0<Unit> function03 = function02;
                        if (function03 == null) {
                            return;
                        }
                        function03.invoke();
                    }
                }, 1, null);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: requestHybridQueueAndUpdateDb$lambda-3, reason: not valid java name */
    public static final void m1590requestHybridQueueAndUpdateDb$lambda3(Throwable th) {
        Log.error(TAG, "Error when requestHybridQueueAndUpdateDb:queryCloudQueueTracks", th);
    }

    public synchronized Observable<List<QueueEntityMetadata>> queryCloudQueueTracks(Context context) {
        AmplifyPageModelDAO amplifyDao;
        Intrinsics.checkNotNullParameter(context, "context");
        CloudQueueSequencer cloudQueueSequencer = CloudQueueSequencer.INSTANCE.getCloudQueueSequencer();
        Observable<List<QueueEntityMetadata>> observableFetchItemsObservable = null;
        QueueSequenceSlice localQueueSequenceSlice = cloudQueueSequencer == null ? null : cloudQueueSequencer.getLocalQueueSequenceSlice();
        if (localQueueSequenceSlice == null) {
            Observable<List<QueueEntityMetadata>> observableEmpty = Observable.empty();
            Intrinsics.checkNotNullExpressionValue(observableEmpty, "empty()");
            return observableEmpty;
        }
        if (cloudQueueSequencer != null && (amplifyDao = cloudQueueSequencer.getAmplifyDao()) != null) {
            List<EntityReference> entityReferences = localQueueSequenceSlice.getEntityReferences();
            ArrayList arrayList = new ArrayList(CollectionsKt__IterablesKt.collectionSizeOrDefault(entityReferences, 10));
            Iterator<T> it = entityReferences.iterator();
            while (it.hasNext()) {
                arrayList.add(((EntityReference) it.next()).getIdentifier());
            }
            observableFetchItemsObservable = amplifyDao.fetchItemsObservable(arrayList);
        }
        return observableFetchItemsObservable;
    }

    public synchronized rx.Observable<List<PlayQueueTrack>> queryCurrentPlayQueueTracks(final Context context) {
        rx.Observable<List<PlayQueueTrack>> observableObserveOn;
        Intrinsics.checkNotNullParameter(context, "context");
        observableObserveOn = rx.Observable.create(new Observable.OnSubscribe() { // from class: com.amazon.mp3.prime.requester.HybridQueueTrackRequesterImpl$$ExternalSyntheticLambda4
            @Override // rx.functions.Action1
            public final void call(Object obj) {
                HybridQueueTrackRequesterImpl.m1586queryCurrentPlayQueueTracks$lambda6(context, (Subscriber) obj);
            }
        }).subscribeOn(rx.schedulers.Schedulers.io()).observeOn(rx.android.schedulers.AndroidSchedulers.mainThread());
        Intrinsics.checkNotNullExpressionValue(observableObserveOn, "create<List<PlayQueueTra…dSchedulers.mainThread())");
        return observableObserveOn;
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: queryCurrentPlayQueueTracks$lambda-6, reason: not valid java name */
    public static final void m1586queryCurrentPlayQueueTracks$lambda6(Context context, Subscriber subscriber) {
        Intrinsics.checkNotNullParameter(context, "$context");
        final Cursor cursorQuery = context.getContentResolver().query(MediaProvider.NowPlaying.CONTENT_URI, NOW_PLAYING_PROJECTION, null, null, null);
        subscriber.onNext(SequencesKt___SequencesKt.toList(SequencesKt___SequencesKt.map(SequencesKt___SequencesKt.filterNotNull(SequencesKt__SequencesKt.generateSequence(new Function0<Cursor>() { // from class: com.amazon.mp3.prime.requester.HybridQueueTrackRequesterImpl$queryCurrentPlayQueueTracks$1$playQueueTracks$1
            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super(0);
            }

            /* JADX WARN: Can't rename method to resolve collision */
            @Override // kotlin.jvm.functions.Function0
            public final Cursor invoke() {
                Cursor cursor = cursorQuery;
                boolean z = false;
                if (cursor != null && cursor.moveToNext()) {
                    z = true;
                }
                if (z) {
                    return cursorQuery;
                }
                return null;
            }
        })), new Function1<Cursor, PlayQueueTrack>() { // from class: com.amazon.mp3.prime.requester.HybridQueueTrackRequesterImpl$queryCurrentPlayQueueTracks$1$playQueueTracks$2
            @Override // kotlin.jvm.functions.Function1
            public final PlayQueueTrack invoke(Cursor it) {
                Intrinsics.checkNotNullParameter(it, "it");
                return new PlayQueueTrack(it.getString(it.getColumnIndex("uri")), it.getString(it.getColumnIndex("udo")), it.getString(it.getColumnIndex("shuffle")), it.getString(it.getColumnIndex("luid")), it.getString(it.getColumnIndex("asin")), "");
            }
        })));
        if (cursorQuery != null) {
            cursorQuery.close();
        }
        subscriber.onCompleted();
    }

    private final <T> Completable batchAndloadHybridQueueTracksFromAsins(List<? extends T> playQueueTracks, boolean doToggle, String asinToSkip) {
        if (playQueueTracks.isEmpty()) {
            return null;
        }
        if (doToggle) {
            return loadPlayQueueTracksFromAsins(playQueueTracks, doToggle, asinToSkip);
        }
        Log.debug(TAG, "Batching can be applied, Prepare a batch of only the tracks to send to Muse lookup");
        int currentIndex = Playback.getInstance().getPlaybackController(ControlSource.APP_UI).getCurrentIndex();
        List<? extends T> mutableList = CollectionsKt___CollectionsKt.toMutableList((Collection) prepareBatchForMuseLookup(playQueueTracks, currentIndex));
        Companion companion = INSTANCE;
        if (currentIndex - (currentIndex % companion.getBATCH_SIZE()) != (companion.getLOOKAHEAD_WINDOW_SIZE() + currentIndex) - ((companion.getLOOKAHEAD_WINDOW_SIZE() + currentIndex) % companion.getBATCH_SIZE())) {
            CollectionsKt__MutableCollectionsKt.addAll(mutableList, prepareBatchForMuseLookup(playQueueTracks, currentIndex + companion.getLOOKAHEAD_WINDOW_SIZE()));
        }
        return loadPlayQueueTracksFromAsins(mutableList, doToggle, asinToSkip);
    }

    private final <T> void batchAndloadHybridQueueTracksFromAsinsWithCallback(List<? extends T> playQueueTracks, boolean doToggle, String asinToSkip, final Function0<Unit> callback) {
        Completable completableBatchAndloadHybridQueueTracksFromAsins = batchAndloadHybridQueueTracksFromAsins(playQueueTracks, doToggle, asinToSkip);
        if ((completableBatchAndloadHybridQueueTracksFromAsins == null ? null : completableBatchAndloadHybridQueueTracksFromAsins.subscribe(new Action0() { // from class: com.amazon.mp3.prime.requester.HybridQueueTrackRequesterImpl$$ExternalSyntheticLambda5
            @Override // rx.functions.Action0
            public final void call() {
                HybridQueueTrackRequesterImpl.m1583batchAndloadHybridQueueTracksFromAsinsWithCallback$lambda7(callback);
            }
        }, new Action1() { // from class: com.amazon.mp3.prime.requester.HybridQueueTrackRequesterImpl$$ExternalSyntheticLambda6
            @Override // rx.functions.Action1
            public final void call(Object obj) {
                HybridQueueTrackRequesterImpl.m1584batchAndloadHybridQueueTracksFromAsinsWithCallback$lambda8((Throwable) obj);
            }
        })) == null) {
            callback.invoke();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: batchAndloadHybridQueueTracksFromAsinsWithCallback$lambda-7, reason: not valid java name */
    public static final void m1583batchAndloadHybridQueueTracksFromAsinsWithCallback$lambda7(Function0 callback) {
        Intrinsics.checkNotNullParameter(callback, "$callback");
        callback.invoke();
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: batchAndloadHybridQueueTracksFromAsinsWithCallback$lambda-8, reason: not valid java name */
    public static final void m1584batchAndloadHybridQueueTracksFromAsinsWithCallback$lambda8(Throwable th) {
        Log.error(TAG, Intrinsics.stringPlus("Error when loadPlayQueueTracksFromAsins: ", th));
    }

    private final <T> Completable loadPlayQueueTracksFromAsins(final List<? extends T> playQueueTracks, final boolean doToggle, final String asinToSkip) {
        final HashMap map = new HashMap();
        for (T t : playQueueTracks) {
            String asin = getAsin(t);
            if (!(asin == null || asin.length() == 0)) {
                if (map.containsKey(asin)) {
                    List list = (List) map.get(asin);
                    if (list != null) {
                        list.add(t);
                    }
                    List list2 = (List) map.get(asin);
                    if (list2 != null) {
                        CollectionsKt___CollectionsKt.distinct(list2);
                    }
                } else {
                    map.put(asin, CollectionsKt__CollectionsKt.mutableListOf(t));
                }
            }
        }
        final Ref.IntRef intRef = new Ref.IntRef();
        final Ref.IntRef intRef2 = new Ref.IntRef();
        intRef2.element = 50;
        final int i = 50;
        return Completable.create(new Completable.OnSubscribe() { // from class: com.amazon.mp3.prime.requester.HybridQueueTrackRequesterImpl$$ExternalSyntheticLambda7
            @Override // rx.functions.Action1
            public final void call(CompletableSubscriber completableSubscriber) {
                HybridQueueTrackRequesterImpl.m1585loadPlayQueueTracksFromAsins$lambda9(playQueueTracks, i, this, intRef, intRef2, doToggle, asinToSkip, map, completableSubscriber);
            }
        }).subscribeOn(rx.schedulers.Schedulers.io()).observeOn(rx.schedulers.Schedulers.io());
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: loadPlayQueueTracksFromAsins$lambda-9, reason: not valid java name */
    public static final void m1585loadPlayQueueTracksFromAsins$lambda9(List playQueueTracks, final int i, final HybridQueueTrackRequesterImpl this$0, final Ref.IntRef startIndex, final Ref.IntRef endIndex, final boolean z, final String asinToSkip, final HashMap playQueueTrackLookupMap, final CompletableSubscriber completableSubscriber) {
        Intrinsics.checkNotNullParameter(playQueueTracks, "$playQueueTracks");
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        Intrinsics.checkNotNullParameter(startIndex, "$startIndex");
        Intrinsics.checkNotNullParameter(endIndex, "$endIndex");
        Intrinsics.checkNotNullParameter(asinToSkip, "$asinToSkip");
        Intrinsics.checkNotNullParameter(playQueueTrackLookupMap, "$playQueueTrackLookupMap");
        try {
            CollectionsKt___CollectionsKt.chunked(playQueueTracks, i, new Function1() { // from class: com.amazon.mp3.prime.requester.HybridQueueTrackRequesterImpl$loadPlayQueueTracksFromAsins$1$1
                /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                {
                    super(1);
                }

                @Override // kotlin.jvm.functions.Function1
                public /* bridge */ /* synthetic */ Object invoke(Object obj) {
                    invoke((List) obj);
                    return Unit.INSTANCE;
                }

                public final void invoke(List sublist) {
                    List list;
                    Intrinsics.checkNotNullParameter(sublist, "sublist");
                    HybridQueueTrackRequesterImpl hybridQueueTrackRequesterImpl = this.this$0;
                    ArrayList arrayList = new ArrayList();
                    Iterator it = sublist.iterator();
                    while (it.hasNext()) {
                        String asin = hybridQueueTrackRequesterImpl.getAsin(it.next());
                        if (asin != null) {
                            arrayList.add(asin);
                        }
                    }
                    Map mapRequestToggleMap = hybridQueueTrackRequesterImpl.requestToggleMap(CollectionsKt___CollectionsKt.toMutableList((Collection) arrayList));
                    boolean z2 = z;
                    String str = asinToSkip;
                    HashMap map = playQueueTrackLookupMap;
                    HybridQueueTrackRequesterImpl hybridQueueTrackRequesterImpl2 = this.this$0;
                    for (Map.Entry entry : mapRequestToggleMap.entrySet()) {
                        if (z2 && !str.equals(entry.getKey()) && (list = (List) map.get(entry.getKey())) != null) {
                            Iterator it2 = list.iterator();
                            while (it2.hasNext()) {
                                hybridQueueTrackRequesterImpl2.replaceTrack(it2.next(), (String) entry.getValue());
                            }
                        }
                    }
                    completableSubscriber.onCompleted();
                    Ref.IntRef intRef = startIndex;
                    int i2 = intRef.element;
                    int i3 = i;
                    intRef.element = i2 + i3;
                    endIndex.element += i3;
                }
            });
            completableSubscriber.onCompleted();
        } catch (Exception e) {
            completableSubscriber.onError(e);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Multi-variable type inference failed */
    public final <T> String getAsin(T playQueueTrack) {
        String identifier;
        if (playQueueTrack == 0) {
            return null;
        }
        KClass orCreateKotlinClass = Reflection.getOrCreateKotlinClass(playQueueTrack.getClass());
        if (Intrinsics.areEqual(orCreateKotlinClass, Reflection.getOrCreateKotlinClass(PlayQueueTrack.class))) {
            identifier = ((PlayQueueTrack) playQueueTrack).getAsin();
        } else {
            if (!Intrinsics.areEqual(orCreateKotlinClass, Reflection.getOrCreateKotlinClass(QueueEntityMetadata.class))) {
                return null;
            }
            identifier = ((QueueEntityMetadata) playQueueTrack).getIdentifier();
        }
        return identifier;
    }

    private final <T> List<T> prepareBatchForMuseLookup(List<? extends T> playQueueTracks, int currentIndex) {
        int iMin = Math.min(currentIndex - (currentIndex % INSTANCE.getBATCH_SIZE()), playQueueTracks.size() - 1);
        int iMin2 = Math.min((r0.getBATCH_SIZE() + iMin) - 1, playQueueTracks.size() - 1);
        Log.debug(TAG, "Batch - [ " + iMin + " - " + iMin2 + " ]");
        return playQueueTracks.subList(iMin, iMin2 + 1);
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Multi-variable type inference failed */
    public final <T> void replaceTrack(T currentPlayQueueTrack, String newTrackAsin) {
        if (currentPlayQueueTrack == 0) {
            return;
        }
        Class<?> cls = currentPlayQueueTrack.getClass();
        if (Intrinsics.areEqual(cls, PlayQueueTrack.class)) {
            String udo = ((PlayQueueTrack) currentPlayQueueTrack).getUdo();
            if (udo == null) {
                return;
            }
            int i = Integer.parseInt(udo);
            PlayQueueSequencer playQueueSequencer = PlayQueueSequencer.getInstance();
            if (playQueueSequencer == null) {
                return;
            }
            playQueueSequencer.replace(new QuerySongByAsin(this.mContext).findCollectionUriByAsin(newTrackAsin), i);
            return;
        }
        if (Intrinsics.areEqual(cls, QueueEntityMetadata.class)) {
            Uri uriFindCollectionUriByAsin = new QuerySongByAsin(this.mContext).findCollectionUriByAsin(newTrackAsin);
            CloudQueueSequencer.Companion companion = CloudQueueSequencer.INSTANCE;
            CloudQueueSequencer cloudQueueSequencer = companion.getCloudQueueSequencer();
            Uri contentUri = cloudQueueSequencer == null ? null : cloudQueueSequencer.getContentUri();
            if (contentUri == null || uriFindCollectionUriByAsin == null) {
                return;
            }
            companion.replaceTrack((QueueEntityMetadata) currentPlayQueueTrack, uriFindCollectionUriByAsin, contentUri);
            return;
        }
        Log.error(TAG, Intrinsics.stringPlus("Unrecognized track type: ", currentPlayQueueTrack));
    }

    /* JADX WARN: Removed duplicated region for block: B:105:0x005a A[EDGE_INSN: B:105:0x005a->B:29:0x005a BREAK  A[LOOP:1: B:9:0x0020->B:108:?], SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:108:? A[LOOP:1: B:9:0x0020->B:108:?, LOOP_END, SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:25:0x0055  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    private final java.util.Map<java.lang.String, java.lang.String> createToggleMapForRecentlyCachedTracks(java.util.List<java.lang.String> r9, java.util.List<com.amazon.mp3.prime.requester.AudioVideo> r10) {
        /*
            Method dump skipped, instruction units count: 255
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.amazon.mp3.prime.requester.HybridQueueTrackRequesterImpl.createToggleMapForRecentlyCachedTracks(java.util.List, java.util.List):java.util.Map");
    }

    private final int doInsertIntoCtaPrimeCache(PrimeTrack primeTrack) {
        if (primeTrack == null) {
            return 0;
        }
        return new CTAPrimeCache(this.mContext).insertPrimeTracks(primeTrack.getAlbumAsin(), primeTrack.getAlbum(), primeTrack.getAlbumArtist(), CollectionsKt__CollectionsJVMKt.listOf(primeTrack));
    }

    /* JADX INFO: compiled from: HybridQueueTrackRequesterImpl.kt */
    @Metadata(d1 = {"\u0000 \n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\b\n\u0002\u0010\u0011\n\u0002\u0010\u000e\n\u0002\b\u0004\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002R\u001b\u0010\u0003\u001a\u00020\u00048BX\u0082\u0084\u0002¢\u0006\f\n\u0004\b\u0007\u0010\b\u001a\u0004\b\u0005\u0010\u0006R\u001b\u0010\t\u001a\u00020\u00048BX\u0082\u0084\u0002¢\u0006\f\n\u0004\b\u000b\u0010\b\u001a\u0004\b\n\u0010\u0006R\u0016\u0010\f\u001a\b\u0012\u0004\u0012\u00020\u000e0\rX\u0082\u0004¢\u0006\u0004\n\u0002\u0010\u000fR\u0016\u0010\u0010\u001a\n \u0011*\u0004\u0018\u00010\u000e0\u000eX\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u0012"}, d2 = {"Lcom/amazon/mp3/prime/requester/HybridQueueTrackRequesterImpl$Companion;", "", "()V", "BATCH_SIZE", "", "getBATCH_SIZE", "()I", "BATCH_SIZE$delegate", "Lkotlin/Lazy;", "LOOKAHEAD_WINDOW_SIZE", "getLOOKAHEAD_WINDOW_SIZE", "LOOKAHEAD_WINDOW_SIZE$delegate", "NOW_PLAYING_PROJECTION", "", "", "[Ljava/lang/String;", "TAG", "kotlin.jvm.PlatformType", "DigitalMusicAndroid3P_automotiveProdRelease"}, k = 1, mv = {1, 6, 0}, xi = 48)
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }

        /* JADX INFO: Access modifiers changed from: private */
        public final int getBATCH_SIZE() {
            return ((Number) HybridQueueTrackRequesterImpl.BATCH_SIZE$delegate.getValue()).intValue();
        }

        /* JADX INFO: Access modifiers changed from: private */
        public final int getLOOKAHEAD_WINDOW_SIZE() {
            return ((Number) HybridQueueTrackRequesterImpl.LOOKAHEAD_WINDOW_SIZE$delegate.getValue()).intValue();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final Map<String, String> requestToggleMap(List<String> currentPlayQueueTrackAsins) {
        String str;
        ArrayList arrayList = new ArrayList();
        for (Object obj : currentPlayQueueTrackAsins) {
            if (this.hybridQueueStateProvider.getAsinsAlreadyRequested().contains((String) obj)) {
                arrayList.add(obj);
            }
        }
        List<String> mutableList = CollectionsKt___CollectionsKt.toMutableList((Collection) arrayList);
        CollectionsKt___CollectionsKt.toMutableList((Collection) CollectionsKt___CollectionsKt.minus((Iterable) currentPlayQueueTrackAsins, (Iterable) mutableList));
        List<AudioVideo> listEmptyList = CollectionsKt__CollectionsKt.emptyList();
        int iDoInsertIntoCtaPrimeCache = 0;
        try {
            for (AudioVideo audioVideo : listEmptyList) {
                iDoInsertIntoCtaPrimeCache = iDoInsertIntoCtaPrimeCache + doInsertIntoCtaPrimeCache(audioVideo.getAudio()) + doInsertIntoCtaPrimeCache(audioVideo.getVideo());
            }
            Log.debug(TAG, Intrinsics.stringPlus("numberOfTracksInserted = ", Integer.valueOf(iDoInsertIntoCtaPrimeCache)));
            Map<String, String> mapCreateToggleMapForRecentlyCachedTracks = createToggleMapForRecentlyCachedTracks(currentPlayQueueTrackAsins, listEmptyList);
            for (String str2 : mutableList) {
                if (this.hybridQueueStateProvider.isVideo()) {
                    str = this.hybridQueueStateProvider.getAudioToVideoMap().get(str2);
                } else {
                    str = (this.hybridQueueStateProvider.isAudio() || this.hybridQueueStateProvider.isBackgroundAudio()) ? this.hybridQueueStateProvider.getVideoToAudioMap().get(str2) : null;
                }
                if (str != null) {
                    mapCreateToggleMapForRecentlyCachedTracks.put(str2, str);
                }
            }
            return mapCreateToggleMapForRecentlyCachedTracks;
        } catch (SQLiteDatabaseLockedException e) {
            String str3 = TAG;
            StackTraceElement[] stackTrace = e.getStackTrace();
            Intrinsics.checkNotNullExpressionValue(stackTrace, "exception.stackTrace");
            Log.error(str3, ArraysKt___ArraysKt.joinToString$default(stackTrace, "\n", (CharSequence) null, (CharSequence) null, 0, (CharSequence) null, (Function1) null, 62, (Object) null));
            return MapsKt__MapsKt.emptyMap();
        }
    }
}
