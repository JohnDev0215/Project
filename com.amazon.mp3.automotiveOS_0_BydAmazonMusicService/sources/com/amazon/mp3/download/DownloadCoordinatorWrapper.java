package com.amazon.mp3.download;

import com.amazon.digitalmusicplayback.DownloadCoordinator;
import com.amazon.digitalmusicplayback.DownloadCoordinatorListener;
import com.amazon.digitalmusicplayback.DownloadEntry;
import com.amazon.digitalmusicplayback.DownloadPriority;
import com.amazon.digitalmusicplayback.DownloadReason;
import com.amazon.digitalmusicplayback.EngineError;
import com.amazon.digitalmusicplayback.ErrorCode;
import com.amazon.digitalmusicplayback.PlayerAudioQuality;
import com.amazon.digitalmusicplayback.StorageInitError;
import com.amazon.digitalmusicplayback.TrackQualityEntry;
import com.amazon.mp3.download.DownloadCoordinatorWrapper;
import com.amazon.mp3.download.MusicDownloaderKt;
import com.amazon.mp3.library.provider.source.local.DeleteListener;
import com.amazon.mp3.playback.harley.DownloadCoordinatorConfigConverter;
import com.amazon.mp3.playback.harley.DownloadProperties;
import com.amazon.mp3.playback.harley.HarleyDownloadCoordinatorListener;
import com.amazon.mp3.playback.harley.HarleyDownloadCoordinatorListenerConverter;
import com.amazon.mp3.playback.harley.HarleyDownloadCoordinatorListenerDispatcher;
import com.amazon.mp3.playback.harley.HarleyDownloadProperties;
import com.amazon.mp3.util.Log;
import com.amazon.mp3.util.extensions.CollectionKt;
import com.amazon.music.downloads.DMMDownloadCoordinator;
import com.amazon.music.downloads.DownloadItemInfo;
import com.google.android.gms.common.internal.ServiceSpecificExtraArgs;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.concurrent.CountDownLatch;
import kotlin.Deprecated;
import kotlin.Metadata;
import kotlin.ReplaceWith;
import kotlin.TuplesKt;
import kotlin.Unit;
import kotlin.collections.CollectionsKt__CollectionsKt;
import kotlin.collections.CollectionsKt___CollectionsKt;
import kotlin.collections.MapsKt__MapsKt;
import kotlin.comparisons.ComparisonsKt__ComparisonsKt;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Ref;
import kotlin.text.StringsKt__StringsJVMKt;
import kotlin.text.StringsKt__StringsKt;

/* JADX INFO: compiled from: DownloadCoordinatorWrapper.kt */
/* JADX INFO: loaded from: classes3.dex */
@Metadata(bv = {}, d1 = {"\u0000\\\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\t\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u000b\b\u0016\u0018\u0000 ,2\u00020\u0001:\u0004,-./B\u0017\u0012\u0006\u0010&\u001a\u00020%\u0012\u0006\u0010\"\u001a\u00020\u0007¢\u0006\u0004\b*\u0010+J\u0018\u0010\u0005\u001a\u0004\u0018\u00010\u00032\f\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00030\u0002H\u0002J\n\u0010\u0006\u001a\u0004\u0018\u00010\u0003H\u0016J\n\u0010\b\u001a\u0004\u0018\u00010\u0007H\u0016J\u0010\u0010\f\u001a\u00020\u000b2\u0006\u0010\n\u001a\u00020\tH\u0016J\u0010\u0010\r\u001a\u00020\u000b2\u0006\u0010\n\u001a\u00020\tH\u0016J\u0010\u0010\u000e\u001a\u00020\u000b2\u0006\u0010\n\u001a\u00020\tH\u0016J\u0010\u0010\u000f\u001a\u00020\u000b2\u0006\u0010\n\u001a\u00020\tH\u0016J\u0010\u0010\u0012\u001a\u00020\u000b2\u0006\u0010\u0011\u001a\u00020\u0010H\u0016J\u001a\u0010\u0017\u001a\u00020\u000b2\u0006\u0010\u0014\u001a\u00020\u00132\b\u0010\u0016\u001a\u0004\u0018\u00010\u0015H\u0016J\b\u0010\u0019\u001a\u00020\u0018H\u0016J\u0018\u0010\u001c\u001a\u00020\u00182\u0006\u0010\u001a\u001a\u00020\u00132\u0006\u0010\u001b\u001a\u00020\u0018H\u0017J\u0018\u0010\u001e\u001a\u00020\u001d2\u0006\u0010\u001a\u001a\u00020\u00132\u0006\u0010\u001b\u001a\u00020\u0018H\u0016J\b\u0010\u001f\u001a\u00020\u0018H\u0016J\u0010\u0010!\u001a\u00020\u000b2\u0006\u0010 \u001a\u00020\u0018H\u0016J\u0010\u0010$\u001a\u00020#2\u0006\u0010\"\u001a\u00020\u0007H\u0016R\u0014\u0010&\u001a\u00020%8\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b&\u0010'R\u0018\u0010(\u001a\u0004\u0018\u00010\u00078\u0002@\u0002X\u0082\u000e¢\u0006\u0006\n\u0004\b(\u0010)¨\u00060"}, d2 = {"Lcom/amazon/mp3/download/DownloadCoordinatorWrapper;", "Lcom/amazon/mp3/download/DownloadCoordinatorWrapperInterface;", "Ljava/util/ArrayList;", "Lcom/amazon/digitalmusicplayback/StorageInitError;", "storageInitErrorList", "pickHighestPriorityError", "getInitError", "Lcom/amazon/mp3/playback/harley/DownloadProperties;", "getCurrentDownloadProperties", "Lcom/amazon/music/downloads/DownloadItemInfo;", "downloadItemInfo", "", "download", "pause", "resume", "cancelDownload", "Lcom/amazon/music/downloads/DMMDownloadCoordinator$DMMDownloadListener;", ServiceSpecificExtraArgs.CastExtraArgs.LISTENER, "registerListener", "", "asin", "Lcom/amazon/mp3/library/provider/source/local/DeleteListener;", "deleteListener", "removeDownload", "", "removeAllDRMDownloads", "identifier", "isDrm", "isTrackAvailableOffline", "Lcom/amazon/mp3/download/HarleyDownloadedItem;", "getDownloadedItem", "isOfflineDRMLicenseAvailable", "value", "setDownloadOnWifiOnly", "downloadProperties", "", "updateConfigOnCurrentThread", "Lcom/amazon/digitalmusicplayback/DownloadCoordinator;", "harleyDownloadCoordinator", "Lcom/amazon/digitalmusicplayback/DownloadCoordinator;", "mCurrentProperties", "Lcom/amazon/mp3/playback/harley/DownloadProperties;", "<init>", "(Lcom/amazon/digitalmusicplayback/DownloadCoordinator;Lcom/amazon/mp3/playback/harley/DownloadProperties;)V", "Companion", "ConfigUpdateListener", "DeleteSuccessfullListener", "RemoveAllListener", "DigitalMusicAndroid3P_automotiveProdRelease"}, k = 1, mv = {1, 6, 0})
public class DownloadCoordinatorWrapper implements DownloadCoordinatorWrapperInterface {
    private static final String TAG;
    private static final Map<ErrorCode, Integer> error2Priority;
    private final DownloadCoordinator harleyDownloadCoordinator;
    private DownloadProperties mCurrentProperties;

    public DownloadCoordinatorWrapper(DownloadCoordinator harleyDownloadCoordinator, DownloadProperties downloadProperties) {
        Intrinsics.checkNotNullParameter(harleyDownloadCoordinator, "harleyDownloadCoordinator");
        Intrinsics.checkNotNullParameter(downloadProperties, "downloadProperties");
        this.harleyDownloadCoordinator = harleyDownloadCoordinator;
        this.mCurrentProperties = downloadProperties;
        HarleyDownloadCoordinatorListenerDispatcher.INSTANCE.registerListener(new MusicDownloaderKt.RedownloadInvalidDownloadListener());
    }

    static {
        String simpleName = DownloadCoordinatorWrapper.class.getSimpleName();
        Intrinsics.checkNotNullExpressionValue(simpleName, "DownloadCoordinatorWrapper::class.java.simpleName");
        TAG = simpleName;
        error2Priority = MapsKt__MapsKt.mapOf(TuplesKt.to(ErrorCode.SYSTEMOTHERERROR, 1), TuplesKt.to(ErrorCode.ENGINENOTINITIALIZED, 2), TuplesKt.to(ErrorCode.BADREQUEST, 3), TuplesKt.to(ErrorCode.CONTENTNOTAVAILABLE, 4), TuplesKt.to(ErrorCode.STORAGEFULL, 5), TuplesKt.to(ErrorCode.FILEOPENFAILED, 6), TuplesKt.to(ErrorCode.CONTENTNOTVALID, 7), TuplesKt.to(ErrorCode.FILEEXCEPTION, 8));
    }

    @Override // com.amazon.mp3.download.DownloadCoordinatorWrapperInterface
    public StorageInitError getInitError() {
        ArrayList<StorageInitError> initErrors = this.harleyDownloadCoordinator.getInitErrors();
        if (initErrors == null || initErrors.isEmpty()) {
            return null;
        }
        Log.info(TAG, Intrinsics.stringPlus("StorageInitErrors occurred on DownloadCoordinator:\n", CollectionKt.toString(initErrors, "\n")));
        return pickHighestPriorityError(initErrors);
    }

    private final StorageInitError pickHighestPriorityError(ArrayList<StorageInitError> storageInitErrorList) {
        final Comparator comparatorNullsLast = ComparisonsKt__ComparisonsKt.nullsLast(ComparisonsKt__ComparisonsKt.reverseOrder());
        return (StorageInitError) CollectionsKt___CollectionsKt.first(CollectionsKt___CollectionsKt.sortedWith(storageInitErrorList, new Comparator() { // from class: com.amazon.mp3.download.DownloadCoordinatorWrapper$pickHighestPriorityError$$inlined$compareBy$1
            /* JADX WARN: Multi-variable type inference failed */
            @Override // java.util.Comparator
            public final int compare(T t, T t2) {
                return comparatorNullsLast.compare((Integer) DownloadCoordinatorWrapper.error2Priority.get(((StorageInitError) t).getStorageError().getCode()), (Integer) DownloadCoordinatorWrapper.error2Priority.get(((StorageInitError) t2).getStorageError().getCode()));
            }
        }));
    }

    @Override // com.amazon.mp3.download.DownloadCoordinatorWrapperInterface
    public DownloadProperties getCurrentDownloadProperties() {
        Log.info(TAG, Intrinsics.stringPlus("Current download properties: ", this.mCurrentProperties));
        return this.mCurrentProperties;
    }

    @Override // com.amazon.music.downloads.DMMDownloadCoordinator
    public void download(DownloadItemInfo downloadItemInfo) {
        Intrinsics.checkNotNullParameter(downloadItemInfo, "downloadItemInfo");
        String purchasedContentDownloadDirectory = null;
        if (StringsKt__StringsJVMKt.startsWith$default(downloadItemInfo.getId(), "asin://", false, 2, null)) {
            DownloadProperties downloadProperties = this.mCurrentProperties;
            if (downloadProperties != null) {
                purchasedContentDownloadDirectory = downloadProperties.getCatalogContentDownloadDirectory();
            }
        } else {
            DownloadProperties downloadProperties2 = this.mCurrentProperties;
            if (downloadProperties2 != null) {
                purchasedContentDownloadDirectory = downloadProperties2.getPurchasedContentDownloadDirectory();
            }
        }
        Log.info(TAG, "Download initiated for " + downloadItemInfo + ", destination directory " + ((Object) purchasedContentDownloadDirectory) + '.');
        this.harleyDownloadCoordinator.download(CollectionsKt__CollectionsKt.arrayListOf(new DownloadEntry(downloadItemInfo.getId(), DownloadPriority.valueOf(downloadItemInfo.getPriorityInfo().getPriority()), DownloadReason.valueOf(downloadItemInfo.getPriorityInfo().getReason()))));
    }

    @Override // com.amazon.music.downloads.DMMDownloadCoordinator
    public void pause(DownloadItemInfo downloadItemInfo) {
        Intrinsics.checkNotNullParameter(downloadItemInfo, "downloadItemInfo");
        Log.info(TAG, Intrinsics.stringPlus("Pause issued with ", downloadItemInfo));
        this.harleyDownloadCoordinator.pauseDownloads(CollectionsKt__CollectionsKt.arrayListOf(downloadItemInfo.getId()));
    }

    @Override // com.amazon.music.downloads.DMMDownloadCoordinator
    public void resume(DownloadItemInfo downloadItemInfo) {
        Intrinsics.checkNotNullParameter(downloadItemInfo, "downloadItemInfo");
        Log.info(TAG, Intrinsics.stringPlus("Resume issued with ", downloadItemInfo));
        this.harleyDownloadCoordinator.resumeDownload(downloadItemInfo.getId());
    }

    @Override // com.amazon.music.downloads.DMMDownloadCoordinator
    public void cancelDownload(DownloadItemInfo downloadItemInfo) {
        Intrinsics.checkNotNullParameter(downloadItemInfo, "downloadItemInfo");
        Log.info(TAG, Intrinsics.stringPlus("Cancel issued with ", downloadItemInfo));
        this.harleyDownloadCoordinator.cancelDownloads(CollectionsKt__CollectionsKt.arrayListOf(downloadItemInfo.getId()));
    }

    @Override // com.amazon.music.downloads.DMMDownloadCoordinator
    public void registerListener(DMMDownloadCoordinator.DMMDownloadListener listener) {
        Intrinsics.checkNotNullParameter(listener, "listener");
        Log.info(TAG, "Registering listener");
        HarleyDownloadCoordinatorListenerDispatcher.INSTANCE.registerListener(HarleyDownloadCoordinatorListenerConverter.INSTANCE.convert(listener));
    }

    /* JADX WARN: Type inference failed for: r1v2, types: [T, com.amazon.mp3.download.DownloadCoordinatorWrapper$DeleteSuccessfullListener] */
    @Override // com.amazon.mp3.download.DownloadCoordinatorWrapperInterface
    public void removeDownload(String asin, final DeleteListener deleteListener) {
        Intrinsics.checkNotNullParameter(asin, "asin");
        Log.info(TAG, Intrinsics.stringPlus("Remove download issued for ASIN ", asin));
        if (deleteListener != null) {
            final Ref.ObjectRef objectRef = new Ref.ObjectRef();
            ?? deleteSuccessfullListener = new DeleteSuccessfullListener(asin, new Function1<Boolean, Unit>() { // from class: com.amazon.mp3.download.DownloadCoordinatorWrapper$removeDownload$1$1
                /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                {
                    super(1);
                }

                @Override // kotlin.jvm.functions.Function1
                public /* bridge */ /* synthetic */ Unit invoke(Boolean bool) {
                    invoke(bool.booleanValue());
                    return Unit.INSTANCE;
                }

                public final void invoke(boolean z) {
                    if (z) {
                        deleteListener.onComplete(DeleteListener.DeletionResult.INSTANCE.buildDeletionSuccess(true, 1, 1));
                    }
                    DownloadCoordinatorWrapper.DeleteSuccessfullListener deleteSuccessfullListener2 = objectRef.element;
                    if (deleteSuccessfullListener2 == null) {
                        return;
                    }
                    HarleyDownloadCoordinatorListenerDispatcher.INSTANCE.unregisterListener(deleteSuccessfullListener2);
                }
            });
            objectRef.element = deleteSuccessfullListener;
            HarleyDownloadCoordinatorListenerDispatcher.INSTANCE.registerListener((DownloadCoordinatorListener) deleteSuccessfullListener);
        }
        this.harleyDownloadCoordinator.removeDownload(Intrinsics.stringPlus("asin://", asin), DownloadReason.USERINITIATED);
    }

    @Override // com.amazon.mp3.download.DownloadCoordinatorWrapperInterface
    public boolean removeAllDRMDownloads() throws InterruptedException {
        HarleyDownloadProperties.Companion companion = HarleyDownloadProperties.INSTANCE;
        ArrayList<String> arrayListArrayListOf = CollectionsKt__CollectionsKt.arrayListOf(HarleyDownloadProperties.Companion.getCatalogDownloadDirectory$default(companion, null, false, 3, null), HarleyDownloadProperties.Companion.getAdditionalDownloadStoragePathForCatalog$default(companion, null, false, 3, null));
        String str = TAG;
        Log.info(str, Intrinsics.stringPlus("removeAllDRMDownloads to ", arrayListArrayListOf));
        final Ref.BooleanRef booleanRef = new Ref.BooleanRef();
        final CountDownLatch countDownLatch = new CountDownLatch(1);
        RemoveAllListener removeAllListener = new RemoveAllListener(this, arrayListArrayListOf, new Function1<Boolean, Unit>() { // from class: com.amazon.mp3.download.DownloadCoordinatorWrapper$removeAllDRMDownloads$callback$1
            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super(1);
            }

            @Override // kotlin.jvm.functions.Function1
            public /* bridge */ /* synthetic */ Unit invoke(Boolean bool) {
                invoke(bool.booleanValue());
                return Unit.INSTANCE;
            }

            public final void invoke(boolean z) {
                Ref.BooleanRef booleanRef2 = booleanRef;
                CountDownLatch countDownLatch2 = countDownLatch;
                booleanRef2.element = z;
                Log.info(DownloadCoordinatorWrapper.TAG, Intrinsics.stringPlus("removeAllDRMDownloads returned wih result ", Boolean.valueOf(booleanRef2.element)));
                countDownLatch2.countDown();
            }
        });
        HarleyDownloadCoordinatorListenerDispatcher harleyDownloadCoordinatorListenerDispatcher = HarleyDownloadCoordinatorListenerDispatcher.INSTANCE;
        harleyDownloadCoordinatorListenerDispatcher.registerListener(removeAllListener);
        this.harleyDownloadCoordinator.removeAllDownloads(arrayListArrayListOf);
        Log.info(str, "Waiting for removeAllDRMDownloads");
        countDownLatch.await();
        harleyDownloadCoordinatorListenerDispatcher.unregisterListener(removeAllListener);
        return booleanRef.element;
    }

    @Override // com.amazon.mp3.download.DownloadCoordinatorWrapperInterface
    @Deprecated(message = "Don't use this", replaceWith = @ReplaceWith(expression = "getDownloadedItem(identifier, isDrm).isAvailableOffline()", imports = {}))
    public boolean isTrackAvailableOffline(String identifier, boolean isDrm) {
        Intrinsics.checkNotNullParameter(identifier, "identifier");
        return getDownloadedItem(identifier, isDrm).isAvailableOffline();
    }

    @Override // com.amazon.mp3.download.DownloadCoordinatorWrapperInterface
    public HarleyDownloadedItem getDownloadedItem(String identifier, boolean isDrm) {
        Intrinsics.checkNotNullParameter(identifier, "identifier");
        if (isDrm) {
            long jCurrentTimeMillis = System.currentTimeMillis();
            TrackQualityEntry trackQualityEntryIsAvailableOffline = this.harleyDownloadCoordinator.isAvailableOffline(Intrinsics.stringPlus("asin://", identifier));
            long jCurrentTimeMillis2 = System.currentTimeMillis() - jCurrentTimeMillis;
            Log.debug(TAG, "getDownloadedItem for " + identifier + " returned " + trackQualityEntryIsAvailableOffline + " taking " + jCurrentTimeMillis2 + " ms");
            return new HarleyDownloadedItem(trackQualityEntryIsAvailableOffline, isDrm);
        }
        return new HarleyDownloadedItem(new TrackQualityEntry(PlayerAudioQuality.UNKNOWN, identifier), isDrm);
    }

    @Override // com.amazon.mp3.download.DownloadCoordinatorWrapperInterface
    public boolean isOfflineDRMLicenseAvailable() {
        return this.harleyDownloadCoordinator.isOfflineDRMLicenseAvailable();
    }

    @Override // com.amazon.mp3.download.DownloadCoordinatorWrapperInterface
    public void setDownloadOnWifiOnly(boolean value) {
        Log.info(TAG, Intrinsics.stringPlus("Set DownloadOnWifiOnly to ", Boolean.valueOf(value)));
        this.harleyDownloadCoordinator.setDownloadOnWifiOnly(value);
    }

    @Override // com.amazon.mp3.download.DownloadCoordinatorWrapperInterface
    public long updateConfigOnCurrentThread(final DownloadProperties downloadProperties) throws InterruptedException {
        Intrinsics.checkNotNullParameter(downloadProperties, "downloadProperties");
        String str = TAG;
        Log.warning(str, Intrinsics.stringPlus("updateConfig to ", downloadProperties));
        final Ref.LongRef longRef = new Ref.LongRef();
        longRef.element = 1L;
        final CountDownLatch countDownLatch = new CountDownLatch(1);
        ConfigUpdateListener configUpdateListener = new ConfigUpdateListener(new Function1<Long, Unit>() { // from class: com.amazon.mp3.download.DownloadCoordinatorWrapper$updateConfigOnCurrentThread$callback$1
            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super(1);
            }

            @Override // kotlin.jvm.functions.Function1
            public /* bridge */ /* synthetic */ Unit invoke(Long l) {
                invoke(l.longValue());
                return Unit.INSTANCE;
            }

            public final void invoke(long j) {
                DownloadCoordinatorWrapper downloadCoordinatorWrapper = this.this$0;
                Ref.LongRef longRef2 = longRef;
                DownloadProperties downloadProperties2 = downloadProperties;
                CountDownLatch countDownLatch2 = countDownLatch;
                longRef2.element = j;
                if (j == 0) {
                    downloadCoordinatorWrapper.mCurrentProperties = downloadProperties2;
                }
                countDownLatch2.countDown();
            }
        });
        HarleyDownloadCoordinatorListenerDispatcher harleyDownloadCoordinatorListenerDispatcher = HarleyDownloadCoordinatorListenerDispatcher.INSTANCE;
        harleyDownloadCoordinatorListenerDispatcher.registerListener(configUpdateListener);
        this.harleyDownloadCoordinator.updateConfig(DownloadCoordinatorConfigConverter.INSTANCE.getConfigFromDownloadProperties(downloadProperties));
        Log.info(str, "Waiting for updateConfigOnCurrentThread");
        countDownLatch.await();
        harleyDownloadCoordinatorListenerDispatcher.unregisterListener(configUpdateListener);
        return longRef.element;
    }

    /* JADX INFO: compiled from: DownloadCoordinatorWrapper.kt */
    @Metadata(d1 = {"\u00002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010 \n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u000b\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0010!\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\b\u0086\u0004\u0018\u00002\u00020\u0001B'\u0012\f\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003\u0012\u0012\u0010\u0005\u001a\u000e\u0012\u0004\u0012\u00020\u0007\u0012\u0004\u0012\u00020\b0\u0006¢\u0006\u0002\u0010\tJ\u0018\u0010\f\u001a\u00020\b2\u0006\u0010\r\u001a\u00020\u00042\u0006\u0010\u000e\u001a\u00020\u000fH\u0016R\u001a\u0010\u0005\u001a\u000e\u0012\u0004\u0012\u00020\u0007\u0012\u0004\u0012\u00020\b0\u0006X\u0082\u0004¢\u0006\u0002\n\u0000R\u0014\u0010\n\u001a\b\u0012\u0004\u0012\u00020\u00040\u000bX\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u0010"}, d2 = {"Lcom/amazon/mp3/download/DownloadCoordinatorWrapper$RemoveAllListener;", "Lcom/amazon/mp3/playback/harley/HarleyDownloadCoordinatorListener;", "directoriesToClear", "", "", "callback", "Lkotlin/Function1;", "", "", "(Lcom/amazon/mp3/download/DownloadCoordinatorWrapper;Ljava/util/List;Lkotlin/jvm/functions/Function1;)V", "remainingDirectories", "", "downloadRemoved", "ID", "engineError", "Lcom/amazon/digitalmusicplayback/EngineError;", "DigitalMusicAndroid3P_automotiveProdRelease"}, k = 1, mv = {1, 6, 0}, xi = 48)
    public final class RemoveAllListener extends HarleyDownloadCoordinatorListener {
        private final Function1<Boolean, Unit> callback;
        private final List<String> remainingDirectories;
        final /* synthetic */ DownloadCoordinatorWrapper this$0;

        /* JADX INFO: compiled from: DownloadCoordinatorWrapper.kt */
        @Metadata(k = 3, mv = {1, 6, 0}, xi = 48)
        public /* synthetic */ class WhenMappings {
            public static final /* synthetic */ int[] $EnumSwitchMapping$0;

            static {
                int[] iArr = new int[ErrorCode.values().length];
                iArr[ErrorCode.NOERROR.ordinal()] = 1;
                $EnumSwitchMapping$0 = iArr;
            }
        }

        /* JADX WARN: Multi-variable type inference failed */
        public RemoveAllListener(DownloadCoordinatorWrapper this$0, List<String> directoriesToClear, Function1<? super Boolean, Unit> callback) {
            Intrinsics.checkNotNullParameter(this$0, "this$0");
            Intrinsics.checkNotNullParameter(directoriesToClear, "directoriesToClear");
            Intrinsics.checkNotNullParameter(callback, "callback");
            this.this$0 = this$0;
            this.callback = callback;
            this.remainingDirectories = CollectionsKt___CollectionsKt.toMutableList((Collection) directoriesToClear);
        }

        @Override // com.amazon.mp3.playback.harley.HarleyDownloadCoordinatorListener, com.amazon.digitalmusicplayback.DownloadCoordinatorListener
        public void downloadRemoved(String ID, EngineError engineError) {
            Intrinsics.checkNotNullParameter(ID, "ID");
            Intrinsics.checkNotNullParameter(engineError, "engineError");
            if (this.remainingDirectories.remove(ID)) {
                ErrorCode code = engineError.getCode();
                if ((code == null ? -1 : WhenMappings.$EnumSwitchMapping$0[code.ordinal()]) == 1) {
                    Log.debug(DownloadCoordinatorWrapper.TAG, Intrinsics.stringPlus("All downloads removed from ", ID));
                } else {
                    Log.debug(DownloadCoordinatorWrapper.TAG, "Failed to remove drm downloads from " + ID + ", error " + engineError.getCode());
                    this.callback.invoke(Boolean.FALSE);
                }
                if (this.remainingDirectories.isEmpty()) {
                    this.callback.invoke(Boolean.TRUE);
                }
            }
        }
    }

    /* JADX INFO: compiled from: DownloadCoordinatorWrapper.kt */
    @Metadata(d1 = {"\u0000 \n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\t\n\u0002\u0010\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\u0018\u00002\u00020\u0001B\u0019\u0012\u0012\u0010\u0002\u001a\u000e\u0012\u0004\u0012\u00020\u0004\u0012\u0004\u0012\u00020\u00050\u0003¢\u0006\u0002\u0010\u0006J\u0010\u0010\u0007\u001a\u00020\u00052\u0006\u0010\b\u001a\u00020\tH\u0016R\u001a\u0010\u0002\u001a\u000e\u0012\u0004\u0012\u00020\u0004\u0012\u0004\u0012\u00020\u00050\u0003X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\n"}, d2 = {"Lcom/amazon/mp3/download/DownloadCoordinatorWrapper$ConfigUpdateListener;", "Lcom/amazon/mp3/playback/harley/HarleyDownloadCoordinatorListener;", "callback", "Lkotlin/Function1;", "", "", "(Lkotlin/jvm/functions/Function1;)V", "configUpdated", "engineError", "Lcom/amazon/digitalmusicplayback/EngineError;", "DigitalMusicAndroid3P_automotiveProdRelease"}, k = 1, mv = {1, 6, 0}, xi = 48)
    public static final class ConfigUpdateListener extends HarleyDownloadCoordinatorListener {
        private final Function1<Long, Unit> callback;

        /* JADX INFO: compiled from: DownloadCoordinatorWrapper.kt */
        @Metadata(k = 3, mv = {1, 6, 0}, xi = 48)
        public /* synthetic */ class WhenMappings {
            public static final /* synthetic */ int[] $EnumSwitchMapping$0;

            static {
                int[] iArr = new int[ErrorCode.values().length];
                iArr[ErrorCode.NOERROR.ordinal()] = 1;
                iArr[ErrorCode.DOWNLOADINPROGRESS.ordinal()] = 2;
                $EnumSwitchMapping$0 = iArr;
            }
        }

        /* JADX WARN: Multi-variable type inference failed */
        public ConfigUpdateListener(Function1<? super Long, Unit> callback) {
            Intrinsics.checkNotNullParameter(callback, "callback");
            this.callback = callback;
        }

        @Override // com.amazon.mp3.playback.harley.HarleyDownloadCoordinatorListener, com.amazon.digitalmusicplayback.DownloadCoordinatorListener
        public void configUpdated(EngineError engineError) {
            Intrinsics.checkNotNullParameter(engineError, "engineError");
            Log.debug(DownloadCoordinatorWrapper.TAG, Intrinsics.stringPlus("Config updated, error code: ", engineError));
            ErrorCode code = engineError.getCode();
            int i = code == null ? -1 : WhenMappings.$EnumSwitchMapping$0[code.ordinal()];
            if (i == 1) {
                this.callback.invoke(0L);
            } else if (i == 2) {
                this.callback.invoke(2L);
            } else {
                this.callback.invoke(1L);
            }
        }
    }

    /* JADX INFO: compiled from: DownloadCoordinatorWrapper.kt */
    @Metadata(d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u000b\n\u0002\u0010\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0000\u0018\u00002\u00020\u0001B!\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0012\u0010\u0004\u001a\u000e\u0012\u0004\u0012\u00020\u0006\u0012\u0004\u0012\u00020\u00070\u0005¢\u0006\u0002\u0010\bJ\u0018\u0010\t\u001a\u00020\u00072\u0006\u0010\n\u001a\u00020\u00032\u0006\u0010\u000b\u001a\u00020\fH\u0016R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000R\u001a\u0010\u0004\u001a\u000e\u0012\u0004\u0012\u00020\u0006\u0012\u0004\u0012\u00020\u00070\u0005X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\r"}, d2 = {"Lcom/amazon/mp3/download/DownloadCoordinatorWrapper$DeleteSuccessfullListener;", "Lcom/amazon/mp3/playback/harley/HarleyDownloadCoordinatorListener;", "asin", "", "callback", "Lkotlin/Function1;", "", "", "(Ljava/lang/String;Lkotlin/jvm/functions/Function1;)V", "downloadRemoved", "ID", "engineError", "Lcom/amazon/digitalmusicplayback/EngineError;", "DigitalMusicAndroid3P_automotiveProdRelease"}, k = 1, mv = {1, 6, 0}, xi = 48)
    public static final class DeleteSuccessfullListener extends HarleyDownloadCoordinatorListener {
        private final String asin;
        private final Function1<Boolean, Unit> callback;

        /* JADX INFO: compiled from: DownloadCoordinatorWrapper.kt */
        @Metadata(k = 3, mv = {1, 6, 0}, xi = 48)
        public /* synthetic */ class WhenMappings {
            public static final /* synthetic */ int[] $EnumSwitchMapping$0;

            static {
                int[] iArr = new int[ErrorCode.values().length];
                iArr[ErrorCode.NOERROR.ordinal()] = 1;
                $EnumSwitchMapping$0 = iArr;
            }
        }

        /* JADX WARN: Multi-variable type inference failed */
        public DeleteSuccessfullListener(String asin, Function1<? super Boolean, Unit> callback) {
            Intrinsics.checkNotNullParameter(asin, "asin");
            Intrinsics.checkNotNullParameter(callback, "callback");
            this.asin = asin;
            this.callback = callback;
        }

        @Override // com.amazon.mp3.playback.harley.HarleyDownloadCoordinatorListener, com.amazon.digitalmusicplayback.DownloadCoordinatorListener
        public void downloadRemoved(String ID, EngineError engineError) {
            Intrinsics.checkNotNullParameter(ID, "ID");
            Intrinsics.checkNotNullParameter(engineError, "engineError");
            ErrorCode code = engineError.getCode();
            if ((code == null ? -1 : WhenMappings.$EnumSwitchMapping$0[code.ordinal()]) == 1) {
                String strSubstring = ID.substring(StringsKt__StringsKt.lastIndexOf$default((CharSequence) ID, "/", 0, false, 6, (Object) null) + 1);
                Intrinsics.checkNotNullExpressionValue(strSubstring, "this as java.lang.String).substring(startIndex)");
                this.callback.invoke(Boolean.valueOf(Intrinsics.areEqual(strSubstring, this.asin)));
                return;
            }
            this.callback.invoke(Boolean.FALSE);
        }
    }
}
