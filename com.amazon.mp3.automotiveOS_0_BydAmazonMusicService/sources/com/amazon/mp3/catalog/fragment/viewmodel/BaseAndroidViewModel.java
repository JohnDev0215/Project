package com.amazon.mp3.catalog.fragment.viewmodel;

import android.app.Application;
import android.content.Context;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import com.amazon.mp3.catalog.fragment.adapter.BaseViewsDiffUtilFactory;
import com.amazon.mp3.catalog.fragment.datasource.repository.PageDataRepository;
import com.amazon.mp3.fragment.viewmodel.LibrarySyncStateUpdater;
import com.amazon.mp3.library.provider.source.cirrus.dbutils.LocalRepositoryEvents;
import com.amazon.mp3.library.provider.source.cirrus.dbutils.RepositoryEvent;
import com.amazon.mp3.library.provider.source.cirrus.dbutils.RepositoryEventType;
import com.amazon.mp3.platform.providers.LibrarySyncStateProvider;
import com.amazon.mp3.playback.state.LibraryEqualizerStateManager;
import com.amazon.mp3.playback.state.PageStateManager;
import com.amazon.mp3.util.Log;
import com.amazon.music.media.playback.ControlSource;
import com.amazon.music.media.playback.Playback;
import com.amazon.music.media.playback.PlaybackController;
import com.amazon.music.platform.AtomicMap;
import com.amazon.music.platform.data.NetworkInfo;
import com.amazon.music.platform.locator.Providers;
import com.amazon.music.platform.locator.ProvidersKt;
import com.amazon.music.platform.providers.InterfaceProvider;
import com.amazon.music.platform.providers.NetworkInfoProvider;
import com.amazon.music.views.library.models.ErrorModel;
import com.amazon.music.views.library.models.PageGridViewModel;
import com.amazon.music.views.library.models.base.BaseViewModel;
import io.reactivex.Observable;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Action;
import io.reactivex.functions.BiConsumer;
import io.reactivex.functions.Consumer;
import io.reactivex.functions.Function;
import io.reactivex.schedulers.Schedulers;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicBoolean;
import kotlin.Lazy;
import kotlin.LazyKt__LazyJVMKt;
import kotlin.Metadata;
import kotlin.Pair;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.collections.CollectionsKt__CollectionsKt;
import kotlin.collections.CollectionsKt__IterablesKt;
import kotlin.collections.CollectionsKt___CollectionsKt;
import kotlin.collections.IntIterator;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt__IntrinsicsKt;
import kotlin.coroutines.jvm.internal.ContinuationImpl;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Reflection;
import kotlin.ranges.IntRange;
import kotlin.ranges.RangesKt___RangesKt;
import kotlinx.coroutines.BuildersKt__Builders_commonKt;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.GlobalScope;
import rx.Subscription;
import rx.functions.Action1;

/* JADX INFO: compiled from: BaseAndroidViewModel.kt */
/* JADX INFO: loaded from: classes3.dex */
@Metadata(bv = {}, d1 = {"\u0000à\u0001\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\b\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u000f\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0003\n\u0002\b\n\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\r\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\t\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\b\u0016\u0018\u00002\u00020\u00012\u00020\u0002B0\u0012\b\u0010\u008a\u0001\u001a\u00030\u0089\u0001\u0012\u0006\u0010K\u001a\u00020J\u0012\b\b\u0002\u0010O\u001a\u00020\u0005\u0012\t\b\u0002\u0010\u008b\u0001\u001a\u00020\u0005¢\u0006\u0006\b\u008c\u0001\u0010\u008d\u0001J\b\u0010\u0004\u001a\u00020\u0003H\u0002J\u0010\u0010\u0007\u001a\u00020\u00032\u0006\u0010\u0006\u001a\u00020\u0005H\u0002J\b\u0010\b\u001a\u00020\u0005H\u0016J\u0006\u0010\t\u001a\u00020\u0003J\u000e\u0010\f\u001a\b\u0012\u0004\u0012\u00020\u000b0\nH\u0016J\u000e\u0010\u000e\u001a\b\u0012\u0004\u0012\u00020\r0\nH\u0016J\b\u0010\u0010\u001a\u00020\u000fH\u0016J\b\u0010\u0011\u001a\u00020\u0003H\u0016J\b\u0010\u0012\u001a\u00020\u0003H\u0016J\u0010\u0010\u0015\u001a\u00020\u00032\u0006\u0010\u0014\u001a\u00020\u0013H\u0016J\u000e\u0010\u0016\u001a\b\u0012\u0004\u0012\u00020\u00050\nH\u0016J\b\u0010\u0017\u001a\u00020\u0003H\u0016J\u0010\u0010\u001a\u001a\u00020\u00032\u0006\u0010\u0019\u001a\u00020\u0018H\u0016J\u0012\u0010\u001c\u001a\u00020\u00032\b\u0010\u001b\u001a\u0004\u0018\u00010\u000bH\u0014J\"\u0010!\u001a\u00020\u00032\u0006\u0010\u0019\u001a\u00020\u00182\u0006\u0010\u001e\u001a\u00020\u001d2\b\u0010 \u001a\u0004\u0018\u00010\u001fH\u0016J$\u0010\"\u001a\u00020\u00032\b\u0010\u001b\u001a\u0004\u0018\u00010\u000b2\u0006\u0010\u001e\u001a\u00020\u001d2\b\u0010 \u001a\u0004\u0018\u00010\u001fH\u0014J(\u0010&\u001a\u00020\u00032\b\u0010\u001b\u001a\u0004\u0018\u00010\u000b2\f\u0010%\u001a\b\u0012\u0004\u0012\u00020$0#2\u0006\u0010\u001e\u001a\u00020\u001dH\u0004J\u0012\u0010'\u001a\u00020\u00032\b\u0010\u001b\u001a\u0004\u0018\u00010\u000bH\u0014J/\u0010)\u001a\u00020\u00032\f\u0010(\u001a\b\u0012\u0004\u0012\u00020$0#2\f\u0010%\u001a\b\u0012\u0004\u0012\u00020$0#H\u0094@ø\u0001\u0000¢\u0006\u0004\b)\u0010*J\u0018\u0010,\u001a\u00020\u00032\u0006\u0010+\u001a\u00020\u00182\u0006\u0010\u0019\u001a\u00020\u0018H\u0016J(\u00100\u001a\u00020\u00032\u0006\u0010-\u001a\u00020\u00182\u0006\u0010\u0019\u001a\u00020\u00182\u0006\u0010.\u001a\u00020\u00182\u0006\u0010/\u001a\u00020\u0005H\u0016J\u0012\u00101\u001a\u00020\u00032\b\u0010\u001b\u001a\u0004\u0018\u00010\u000bH\u0014J\b\u00102\u001a\u00020\u0003H\u0014J\u0012\u00103\u001a\u00020\u00032\b\u0010\u001b\u001a\u0004\u0018\u00010\u000bH\u0014J\u001a\u00106\u001a\u00020\u00032\b\u0010\u001b\u001a\u0004\u0018\u00010\u000b2\u0006\u00105\u001a\u000204H\u0014J \u00107\u001a\u00020\u00032\u0006\u0010-\u001a\u00020\u00182\u0006\u0010\u0019\u001a\u00020\u00182\u0006\u0010.\u001a\u00020\u0018H\u0014J\u0018\u0010:\u001a\u00020\u00032\u0006\u00109\u001a\u0002082\u0006\u0010+\u001a\u00020\u0018H\u0014J \u0010=\u001a\u00020\u000b2\u0006\u0010;\u001a\u00020\u000b2\u0006\u0010<\u001a\u00020\u000b2\u0006\u0010+\u001a\u00020\u0018H\u0014J(\u0010@\u001a\u00020\u000b2\u0006\u0010;\u001a\u00020\u000b2\u0006\u0010<\u001a\u00020\u000b2\u0006\u0010>\u001a\u00020\u00182\u0006\u0010?\u001a\u00020\u0018H\u0014J\b\u0010A\u001a\u00020\u0003H\u0016J\b\u0010B\u001a\u00020\u0003H\u0016J\u000e\u0010D\u001a\b\u0012\u0004\u0012\u00020C0\nH\u0016J\u000e\u0010F\u001a\b\u0012\u0004\u0012\u00020E0\nH\u0016J\u000e\u0010I\u001a\b\u0012\u0004\u0012\u00020H0GH\u0016R\u001a\u0010K\u001a\u00020J8\u0004X\u0084\u0004¢\u0006\f\n\u0004\bK\u0010L\u001a\u0004\bM\u0010NR\u0014\u0010O\u001a\u00020\u00058\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\bO\u0010PR \u0010Q\u001a\b\u0012\u0004\u0012\u00020\u000b0\n8\u0004X\u0084\u0004¢\u0006\f\n\u0004\bQ\u0010R\u001a\u0004\bS\u0010TR\u001a\u0010U\u001a\b\u0012\u0004\u0012\u00020\r0\n8\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\bU\u0010RR\u0014\u0010V\u001a\u00020\u000f8\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\bV\u0010WR\u001a\u0010Y\u001a\u00020X8\u0004X\u0084\u0004¢\u0006\f\n\u0004\bY\u0010Z\u001a\u0004\b[\u0010\\R\u001a\u0010^\u001a\u00020]8\u0004X\u0084\u0004¢\u0006\f\n\u0004\b^\u0010_\u001a\u0004\b`\u0010aR\u0016\u0010c\u001a\u0004\u0018\u00010b8\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\bc\u0010dR\u001a\u0010e\u001a\b\u0012\u0004\u0012\u00020\u00130\n8\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\be\u0010RR\u001a\u0010f\u001a\b\u0012\u0004\u0012\u00020\u00050\n8\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\bf\u0010RR\u0014\u0010h\u001a\u00020g8\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\bh\u0010iR\u001a\u0010j\u001a\b\u0012\u0004\u0012\u00020C0\n8\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\bj\u0010RR\u001a\u0010k\u001a\b\u0012\u0004\u0012\u00020E0\n8\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\bk\u0010RR\u0018\u0010m\u001a\u0004\u0018\u00010l8\u0002@\u0002X\u0082\u000e¢\u0006\u0006\n\u0004\bm\u0010nR\u0018\u0010o\u001a\u0004\u0018\u00010l8\u0002@\u0002X\u0082\u000e¢\u0006\u0006\n\u0004\bo\u0010nR \u0010q\u001a\b\u0012\u0004\u0012\u00020p0#8\u0010X\u0090\u0004¢\u0006\f\n\u0004\bq\u0010r\u001a\u0004\bs\u0010tR!\u0010y\u001a\b\u0012\u0004\u0012\u00020H0G8BX\u0082\u0084\u0002¢\u0006\f\n\u0004\bu\u0010v\u001a\u0004\bw\u0010xR\u0016\u0010{\u001a\u0004\u0018\u00010z8\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b{\u0010|R(\u0010~\u001a\u0004\u0018\u00010}8\u0004@\u0004X\u0084\u000e¢\u0006\u0016\n\u0004\b~\u0010\u007f\u001a\u0006\b\u0080\u0001\u0010\u0081\u0001\"\u0006\b\u0082\u0001\u0010\u0083\u0001R\u0018\u0010\u0006\u001a\u00030\u0084\u00018\u0002@\u0002X\u0082\u000e¢\u0006\u0007\n\u0005\b\u0006\u0010\u0085\u0001R\u001c\u0010\u0087\u0001\u001a\u0005\u0018\u00010\u0086\u00018\u0002@\u0002X\u0082\u000e¢\u0006\b\n\u0006\b\u0087\u0001\u0010\u0088\u0001\u0082\u0002\u0004\n\u0002\b\u0019¨\u0006\u008e\u0001"}, d2 = {"Lcom/amazon/mp3/catalog/fragment/viewmodel/BaseAndroidViewModel;", "Landroidx/lifecycle/AndroidViewModel;", "Lcom/amazon/mp3/catalog/fragment/viewmodel/PageViewModelProvider;", "", "refreshModelStateProviders", "", "isFetching", "setIsFetching", "hasPageData", "updatePlayState", "Landroidx/lifecycle/MutableLiveData;", "Lcom/amazon/music/views/library/models/PageGridViewModel;", "getPageModelLiveData", "Lcom/amazon/music/views/library/models/ErrorModel;", "getErrorLiveData", "Lcom/amazon/mp3/playback/state/PageStateManager;", "getPageStateManager", "startObservingNetworkEvents", "stopObservingNetworkEvents", "Lcom/amazon/music/platform/data/NetworkInfo;", "value", "onEvent", "getHasConnectionLiveData", "refresh", "", "pageSize", "fetchInitPage", "pageModel", "handleInitPageResponse", "Lcom/amazon/mp3/catalog/fragment/adapter/BaseViewsDiffUtilFactory;", "diffUtilFactory", "Lcom/amazon/mp3/catalog/fragment/viewmodel/FetchType;", "fetchType", "fetchUpdates", "handleUpdatedPageResponse", "", "Lcom/amazon/music/views/library/models/base/BaseViewModel;", "newModels", "compareModelsForUpdates", "postUpdatedModels", "oldModels", "checkModelsForUpdates", "(Ljava/util/List;Ljava/util/List;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "pageIndex", "fetchMorePage", "startPageIndex", "pageCount", "shouldCancelPreviousRequest", "fetchPageAndReplace", "handlePaginatedResponse", "onCleared", "onPageResult", "Lkotlin/ranges/IntRange;", "range", "didReplaceItemsAtRange", "didCancelPageRequest", "", "error", "onPageError", "originalPageData", "newPageData", "mergePageData", "startPosition", "endPosition", "replacePageData", "startObservingLibrarySyncEvents", "stopObservingLibrarySyncEvents", "Lcom/amazon/mp3/platform/providers/LibrarySyncStateProvider$LibrarySyncState;", "getLibrarySyncStateLiveData", "Lcom/amazon/mp3/fragment/viewmodel/LibrarySyncStateUpdater$LibrarySyncEvent;", "getLibrarySyncEventLiveData", "Landroidx/lifecycle/LiveData;", "Lcom/amazon/mp3/library/provider/source/cirrus/dbutils/RepositoryEvent;", "getLocalRepositoryUpdateEventLiveData", "Lcom/amazon/mp3/catalog/fragment/datasource/repository/PageDataRepository;", "pageDataRepository", "Lcom/amazon/mp3/catalog/fragment/datasource/repository/PageDataRepository;", "getPageDataRepository", "()Lcom/amazon/mp3/catalog/fragment/datasource/repository/PageDataRepository;", "usePagination", "Z", "pageData", "Landroidx/lifecycle/MutableLiveData;", "getPageData", "()Landroidx/lifecycle/MutableLiveData;", "pageErrorData", "pageStateManager", "Lcom/amazon/mp3/playback/state/PageStateManager;", "Lio/reactivex/disposables/CompositeDisposable;", "compositeDisposable", "Lio/reactivex/disposables/CompositeDisposable;", "getCompositeDisposable", "()Lio/reactivex/disposables/CompositeDisposable;", "Ljava/util/concurrent/ExecutorService;", "executorService", "Ljava/util/concurrent/ExecutorService;", "getExecutorService", "()Ljava/util/concurrent/ExecutorService;", "Lcom/amazon/music/platform/providers/NetworkInfoProvider;", "networkInfoProvider", "Lcom/amazon/music/platform/providers/NetworkInfoProvider;", "networkLiveData", "connectedLiveData", "Lcom/amazon/mp3/fragment/viewmodel/LibrarySyncStateUpdater;", "libraryStateUpdater", "Lcom/amazon/mp3/fragment/viewmodel/LibrarySyncStateUpdater;", "librarySyncStateLiveData", "librarySyncEventLiveData", "Lrx/Subscription;", "librarySyncStateSubscription", "Lrx/Subscription;", "librarySyncEventSubscription", "Lcom/amazon/mp3/library/provider/source/cirrus/dbutils/RepositoryEventType;", "repositoryUpdatesEventTypeAllowList", "Ljava/util/List;", "getRepositoryUpdatesEventTypeAllowList$DigitalMusicAndroid3P_automotiveProdRelease", "()Ljava/util/List;", "localRepositoryUpdateLiveData$delegate", "Lkotlin/Lazy;", "getLocalRepositoryUpdateLiveData", "()Landroidx/lifecycle/LiveData;", "localRepositoryUpdateLiveData", "Lcom/amazon/mp3/playback/state/LibraryEqualizerStateManager;", "equalizerStateManager", "Lcom/amazon/mp3/playback/state/LibraryEqualizerStateManager;", "Lio/reactivex/disposables/Disposable;", "fetchPageAndReplaceDisposable", "Lio/reactivex/disposables/Disposable;", "getFetchPageAndReplaceDisposable", "()Lio/reactivex/disposables/Disposable;", "setFetchPageAndReplaceDisposable", "(Lio/reactivex/disposables/Disposable;)V", "Ljava/util/concurrent/atomic/AtomicBoolean;", "Ljava/util/concurrent/atomic/AtomicBoolean;", "Lcom/amazon/mp3/catalog/fragment/viewmodel/PageRequest;", "latestReplaceablePageRequest", "Lcom/amazon/mp3/catalog/fragment/viewmodel/PageRequest;", "Landroid/app/Application;", "application", "initEqualizer", "<init>", "(Landroid/app/Application;Lcom/amazon/mp3/catalog/fragment/datasource/repository/PageDataRepository;ZZ)V", "DigitalMusicAndroid3P_automotiveProdRelease"}, k = 1, mv = {1, 6, 0})
public class BaseAndroidViewModel extends AndroidViewModel implements PageViewModelProvider {
    private final CompositeDisposable compositeDisposable;
    private final MutableLiveData<Boolean> connectedLiveData;
    private final LibraryEqualizerStateManager equalizerStateManager;
    private final ExecutorService executorService;
    private Disposable fetchPageAndReplaceDisposable;
    private AtomicBoolean isFetching;
    private PageRequest latestReplaceablePageRequest;
    private final LibrarySyncStateUpdater libraryStateUpdater;
    private final MutableLiveData<LibrarySyncStateUpdater.LibrarySyncEvent> librarySyncEventLiveData;
    private Subscription librarySyncEventSubscription;
    private final MutableLiveData<LibrarySyncStateProvider.LibrarySyncState> librarySyncStateLiveData;
    private Subscription librarySyncStateSubscription;

    /* JADX INFO: renamed from: localRepositoryUpdateLiveData$delegate, reason: from kotlin metadata */
    private final Lazy localRepositoryUpdateLiveData;
    private final NetworkInfoProvider networkInfoProvider;
    private final MutableLiveData<NetworkInfo> networkLiveData;
    private final MutableLiveData<PageGridViewModel> pageData;
    private final PageDataRepository pageDataRepository;
    private final MutableLiveData<ErrorModel> pageErrorData;
    private final PageStateManager pageStateManager;
    private final List<RepositoryEventType> repositoryUpdatesEventTypeAllowList;
    private final boolean usePagination;

    /* JADX INFO: compiled from: BaseAndroidViewModel.kt */
    @Metadata(k = 3, mv = {1, 6, 0}, xi = 48)
    public /* synthetic */ class WhenMappings {
        public static final /* synthetic */ int[] $EnumSwitchMapping$0;

        static {
            int[] iArr = new int[LibrarySyncStateUpdater.LibrarySyncEvent.values().length];
            iArr[LibrarySyncStateUpdater.LibrarySyncEvent.INITIAL_LIBRARY_SYNC_COMPLETED.ordinal()] = 1;
            iArr[LibrarySyncStateUpdater.LibrarySyncEvent.SYNC_COMPLETED.ordinal()] = 2;
            iArr[LibrarySyncStateUpdater.LibrarySyncEvent.CLEAR_CACHE_COMPLETED.ordinal()] = 3;
            $EnumSwitchMapping$0 = iArr;
        }
    }

    /* JADX INFO: renamed from: com.amazon.mp3.catalog.fragment.viewmodel.BaseAndroidViewModel$checkModelsForUpdates$1, reason: invalid class name */
    /* JADX INFO: compiled from: BaseAndroidViewModel.kt */
    @Metadata(k = 3, mv = {1, 6, 0}, xi = 48)
    @DebugMetadata(c = "com.amazon.mp3.catalog.fragment.viewmodel.BaseAndroidViewModel", f = "BaseAndroidViewModel.kt", i = {0, 0, 0}, l = {252}, m = "checkModelsForUpdates$suspendImpl", n = {"this", "oldModels", "newModels"}, s = {"L$0", "L$1", "L$2"})
    static final class AnonymousClass1 extends ContinuationImpl {
        int I$0;
        int I$1;
        Object L$0;
        Object L$1;
        Object L$2;
        int label;
        /* synthetic */ Object result;

        AnonymousClass1(Continuation<? super AnonymousClass1> continuation) {
            super(continuation);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object obj) {
            this.result = obj;
            this.label |= Integer.MIN_VALUE;
            return BaseAndroidViewModel.checkModelsForUpdates$suspendImpl(BaseAndroidViewModel.this, null, null, this);
        }
    }

    protected Object checkModelsForUpdates(List<? extends BaseViewModel> list, List<? extends BaseViewModel> list2, Continuation<? super Unit> continuation) {
        return checkModelsForUpdates$suspendImpl(this, list, list2, continuation);
    }

    protected void didCancelPageRequest(int startPageIndex, int pageSize, int pageCount) {
    }

    protected void didReplaceItemsAtRange(PageGridViewModel pageModel, IntRange range) {
        Intrinsics.checkNotNullParameter(range, "range");
    }

    public /* synthetic */ BaseAndroidViewModel(Application application, PageDataRepository pageDataRepository, boolean z, boolean z2, int i, DefaultConstructorMarker defaultConstructorMarker) {
        this(application, pageDataRepository, (i & 4) != 0 ? false : z, (i & 8) != 0 ? true : z2);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public final PageDataRepository getPageDataRepository() {
        return this.pageDataRepository;
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public BaseAndroidViewModel(Application application, PageDataRepository pageDataRepository, boolean z, boolean z2) {
        super(application);
        Intrinsics.checkNotNullParameter(application, "application");
        Intrinsics.checkNotNullParameter(pageDataRepository, "pageDataRepository");
        this.pageDataRepository = pageDataRepository;
        this.usePagination = z;
        MutableLiveData<PageGridViewModel> mutableLiveData = new MutableLiveData<>();
        this.pageData = mutableLiveData;
        this.pageErrorData = new MutableLiveData<>();
        PageStateManager pageStateManager = new PageStateManager();
        this.pageStateManager = pageStateManager;
        this.compositeDisposable = new CompositeDisposable();
        ExecutorService executorServiceNewSingleThreadExecutor = Executors.newSingleThreadExecutor();
        Intrinsics.checkNotNullExpressionValue(executorServiceNewSingleThreadExecutor, "newSingleThreadExecutor()");
        this.executorService = executorServiceNewSingleThreadExecutor;
        AtomicMap<String, InterfaceProvider> providerInstances = Providers.INSTANCE.getProviderInstances();
        String originalClassName = ProvidersKt.getOriginalClassName(Reflection.getOrCreateKotlinClass(NetworkInfoProvider.class));
        Intrinsics.checkNotNull(originalClassName);
        InterfaceProvider interfaceProvider = providerInstances.get(originalClassName);
        LibraryEqualizerStateManager libraryEqualizerStateManager = null;
        this.networkInfoProvider = (NetworkInfoProvider) (interfaceProvider instanceof NetworkInfoProvider ? interfaceProvider : null);
        this.networkLiveData = new MutableLiveData<>();
        this.connectedLiveData = new MutableLiveData<>();
        Application application2 = getApplication();
        Intrinsics.checkNotNullExpressionValue(application2, "getApplication()");
        this.libraryStateUpdater = new LibrarySyncStateUpdater(application2);
        this.librarySyncStateLiveData = new MutableLiveData<>();
        this.librarySyncEventLiveData = new MutableLiveData<>();
        this.repositoryUpdatesEventTypeAllowList = CollectionsKt__CollectionsKt.emptyList();
        this.localRepositoryUpdateLiveData = LazyKt__LazyJVMKt.lazy(new Function0<LiveData<RepositoryEvent>>() { // from class: com.amazon.mp3.catalog.fragment.viewmodel.BaseAndroidViewModel$localRepositoryUpdateLiveData$2
            {
                super(0);
            }

            /* JADX WARN: Can't rename method to resolve collision */
            @Override // kotlin.jvm.functions.Function0
            public final LiveData<RepositoryEvent> invoke() {
                return LocalRepositoryEvents.INSTANCE.getFilteredRepositoryUpdateLiveData(this.this$0.getRepositoryUpdatesEventTypeAllowList$DigitalMusicAndroid3P_automotiveProdRelease());
            }
        });
        this.isFetching = new AtomicBoolean(false);
        pageStateManager.observePageData(mutableLiveData);
        if (z2) {
            Context applicationContext = application.getApplicationContext();
            Intrinsics.checkNotNullExpressionValue(applicationContext, "application.applicationContext");
            PlaybackController playbackController = Playback.getInstance().getPlaybackController(ControlSource.APP_UI);
            Intrinsics.checkNotNullExpressionValue(playbackController, "getInstance().getPlaybac…ler(ControlSource.APP_UI)");
            libraryEqualizerStateManager = new LibraryEqualizerStateManager(applicationContext, pageStateManager, playbackController);
        }
        this.equalizerStateManager = libraryEqualizerStateManager;
        startObservingNetworkEvents();
        startObservingLibrarySyncEvents();
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public final MutableLiveData<PageGridViewModel> getPageData() {
        return this.pageData;
    }

    protected final CompositeDisposable getCompositeDisposable() {
        return this.compositeDisposable;
    }

    protected final ExecutorService getExecutorService() {
        return this.executorService;
    }

    public List<RepositoryEventType> getRepositoryUpdatesEventTypeAllowList$DigitalMusicAndroid3P_automotiveProdRelease() {
        return this.repositoryUpdatesEventTypeAllowList;
    }

    private final LiveData<RepositoryEvent> getLocalRepositoryUpdateLiveData() {
        return (LiveData) this.localRepositoryUpdateLiveData.getValue();
    }

    @Override // com.amazon.mp3.catalog.fragment.viewmodel.PageViewModelProvider
    public boolean hasPageData() {
        return this.pageData.getValue() != null;
    }

    public final void updatePlayState() {
        LibraryEqualizerStateManager libraryEqualizerStateManager = this.equalizerStateManager;
        if (libraryEqualizerStateManager == null) {
            return;
        }
        libraryEqualizerStateManager.onPlayStateChanged();
    }

    @Override // com.amazon.mp3.catalog.fragment.viewmodel.PageViewModelProvider
    public MutableLiveData<PageGridViewModel> getPageModelLiveData() {
        return this.pageData;
    }

    @Override // com.amazon.mp3.catalog.fragment.viewmodel.PageViewModelProvider
    public MutableLiveData<ErrorModel> getErrorLiveData() {
        return this.pageErrorData;
    }

    @Override // com.amazon.mp3.catalog.fragment.viewmodel.PageViewModelProvider
    public PageStateManager getPageStateManager() {
        return this.pageStateManager;
    }

    public void startObservingNetworkEvents() {
        NetworkInfoProvider networkInfoProvider = this.networkInfoProvider;
        if (networkInfoProvider == null) {
            return;
        }
        networkInfoProvider.addNetworkChangeListener(this);
    }

    public void stopObservingNetworkEvents() {
        NetworkInfoProvider networkInfoProvider = this.networkInfoProvider;
        if (networkInfoProvider == null) {
            return;
        }
        networkInfoProvider.removeNetworkChangeListener(this);
    }

    @Override // com.amazon.music.platform.data.Listener
    public void onEvent(NetworkInfo value) {
        Intrinsics.checkNotNullParameter(value, "value");
        if (!Intrinsics.areEqual(value, this.networkLiveData.getValue())) {
            this.networkLiveData.postValue(value);
        }
        if (Intrinsics.areEqual(Boolean.valueOf(value.getIsConnected()), this.connectedLiveData.getValue())) {
            return;
        }
        this.connectedLiveData.postValue(Boolean.valueOf(value.getIsConnected()));
    }

    @Override // com.amazon.mp3.catalog.fragment.viewmodel.NetworkStateProvider
    public MutableLiveData<Boolean> getHasConnectionLiveData() {
        return this.connectedLiveData;
    }

    @Override // com.amazon.mp3.catalog.fragment.viewmodel.PageViewModelProvider
    public void refresh() {
        refreshModelStateProviders();
    }

    private final void refreshModelStateProviders() {
        LibraryEqualizerStateManager libraryEqualizerStateManager = this.equalizerStateManager;
        if (libraryEqualizerStateManager == null) {
            return;
        }
        libraryEqualizerStateManager.onRefresh();
    }

    @Override // com.amazon.mp3.catalog.fragment.viewmodel.PageViewModelProvider
    public synchronized void fetchInitPage(int pageSize) {
        if (this.isFetching.get()) {
            return;
        }
        setIsFetching(true);
        Disposable disposableSubscribe = PageDataRepository.fetchPageViewModels$default(this.pageDataRepository, 0, pageSize, FetchType.INIT, null, 8, null).subscribeOn(Schedulers.from(this.executorService)).observeOn(Schedulers.io()).subscribe(new BiConsumer() { // from class: com.amazon.mp3.catalog.fragment.viewmodel.BaseAndroidViewModel$$ExternalSyntheticLambda3
            @Override // io.reactivex.functions.BiConsumer
            public final void accept(Object obj, Object obj2) {
                BaseAndroidViewModel.m609fetchInitPage$lambda0(this.f$0, (PageGridViewModel) obj, (Throwable) obj2);
            }
        });
        Intrinsics.checkNotNullExpressionValue(disposableSubscribe, "pageDataRepository.fetch…      }\n                }");
        this.compositeDisposable.add(disposableSubscribe);
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: fetchInitPage$lambda-0, reason: not valid java name */
    public static final void m609fetchInitPage$lambda0(BaseAndroidViewModel this$0, PageGridViewModel pageGridViewModel, Throwable th) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        this$0.setIsFetching(false);
        if (th == null) {
            this$0.handleInitPageResponse(pageGridViewModel);
        } else {
            this$0.pageData.postValue(null);
            this$0.onPageError(th, 0);
        }
    }

    protected void handleInitPageResponse(PageGridViewModel pageModel) {
        onPageResult(pageModel);
    }

    @Override // com.amazon.mp3.catalog.fragment.viewmodel.PageViewModelProvider
    public synchronized void fetchUpdates(int pageSize, final BaseViewsDiffUtilFactory diffUtilFactory, final FetchType fetchType) {
        Intrinsics.checkNotNullParameter(diffUtilFactory, "diffUtilFactory");
        if (!this.isFetching.get() && this.pageData.getValue() != null) {
            setIsFetching(true);
            Disposable disposableSubscribe = PageDataRepository.fetchPageViewModels$default(this.pageDataRepository, 0, pageSize, fetchType == null ? FetchType.UPDATE : fetchType, null, 8, null).subscribeOn(Schedulers.from(this.executorService)).observeOn(Schedulers.io()).subscribe(new BiConsumer() { // from class: com.amazon.mp3.catalog.fragment.viewmodel.BaseAndroidViewModel$$ExternalSyntheticLambda2
                @Override // io.reactivex.functions.BiConsumer
                public final void accept(Object obj, Object obj2) {
                    BaseAndroidViewModel.m618fetchUpdates$lambda1(this.f$0, diffUtilFactory, fetchType, (PageGridViewModel) obj, (Throwable) obj2);
                }
            });
            Intrinsics.checkNotNullExpressionValue(disposableSubscribe, "pageDataRepository.fetch…      }\n                }");
            this.compositeDisposable.add(disposableSubscribe);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: fetchUpdates$lambda-1, reason: not valid java name */
    public static final void m618fetchUpdates$lambda1(BaseAndroidViewModel this$0, BaseViewsDiffUtilFactory diffUtilFactory, FetchType fetchType, PageGridViewModel pageGridViewModel, Throwable th) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        Intrinsics.checkNotNullParameter(diffUtilFactory, "$diffUtilFactory");
        this$0.setIsFetching(false);
        if (th == null) {
            this$0.handleUpdatedPageResponse(pageGridViewModel, diffUtilFactory, fetchType);
        } else {
            this$0.onPageError(th, 0);
        }
    }

    protected synchronized void handleUpdatedPageResponse(PageGridViewModel pageModel, BaseViewsDiffUtilFactory diffUtilFactory, FetchType fetchType) {
        Intrinsics.checkNotNullParameter(diffUtilFactory, "diffUtilFactory");
        List<BaseViewModel> models = pageModel == null ? null : pageModel.getModels();
        if (models == null) {
            models = CollectionsKt__CollectionsKt.emptyList();
        }
        compareModelsForUpdates(pageModel, models, diffUtilFactory);
    }

    protected final void compareModelsForUpdates(PageGridViewModel pageModel, List<? extends BaseViewModel> newModels, BaseViewsDiffUtilFactory diffUtilFactory) {
        Intrinsics.checkNotNullParameter(newModels, "newModels");
        Intrinsics.checkNotNullParameter(diffUtilFactory, "diffUtilFactory");
        PageGridViewModel value = this.pageData.getValue();
        List<BaseViewModel> models = value == null ? null : value.getModels();
        if (models == null) {
            models = CollectionsKt__CollectionsKt.emptyList();
        }
        if (diffUtilFactory.getBaseViewsDiffUtil(models, newModels).areListsTheSame()) {
            BuildersKt__Builders_commonKt.launch$default(GlobalScope.INSTANCE, null, null, new C03261(models, newModels, null), 3, null);
        } else {
            postUpdatedModels(pageModel);
        }
    }

    /* JADX INFO: renamed from: com.amazon.mp3.catalog.fragment.viewmodel.BaseAndroidViewModel$compareModelsForUpdates$1, reason: invalid class name and case insensitive filesystem */
    /* JADX INFO: compiled from: BaseAndroidViewModel.kt */
    @Metadata(bv = {}, d1 = {"\u0000\n\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0000\u0010\u0002\u001a\u00020\u0001*\u00020\u0000H\u008a@"}, d2 = {"Lkotlinx/coroutines/CoroutineScope;", "", "<anonymous>"}, k = 3, mv = {1, 6, 0})
    @DebugMetadata(c = "com.amazon.mp3.catalog.fragment.viewmodel.BaseAndroidViewModel$compareModelsForUpdates$1", f = "BaseAndroidViewModel.kt", i = {}, l = {239}, m = "invokeSuspend", n = {}, s = {})
    static final class C03261 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
        final /* synthetic */ List<BaseViewModel> $newModels;
        final /* synthetic */ List<BaseViewModel> $oldModels;
        int label;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        /* JADX WARN: Multi-variable type inference failed */
        C03261(List<? extends BaseViewModel> list, List<? extends BaseViewModel> list2, Continuation<? super C03261> continuation) {
            super(2, continuation);
            this.$oldModels = list;
            this.$newModels = list2;
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
            return BaseAndroidViewModel.this.new C03261(this.$oldModels, this.$newModels, continuation);
        }

        @Override // kotlin.jvm.functions.Function2
        /* JADX INFO: renamed from: invoke, reason: avoid collision after fix types in other method and merged with bridge method [inline-methods] */
        public final Object mo4invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
            return ((C03261) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object obj) {
            Object coroutine_suspended = IntrinsicsKt__IntrinsicsKt.getCOROUTINE_SUSPENDED();
            int i = this.label;
            if (i == 0) {
                ResultKt.throwOnFailure(obj);
                BaseAndroidViewModel baseAndroidViewModel = BaseAndroidViewModel.this;
                List<BaseViewModel> list = this.$oldModels;
                List<BaseViewModel> list2 = this.$newModels;
                this.label = 1;
                if (baseAndroidViewModel.checkModelsForUpdates(list, list2, this) == coroutine_suspended) {
                    return coroutine_suspended;
                }
            } else {
                if (i != 1) {
                    throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                }
                ResultKt.throwOnFailure(obj);
            }
            return Unit.INSTANCE;
        }
    }

    protected void postUpdatedModels(PageGridViewModel pageModel) {
        onPageResult(pageModel);
    }

    /* JADX WARN: Removed duplicated region for block: B:7:0x0013  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    static /* synthetic */ java.lang.Object checkModelsForUpdates$suspendImpl(com.amazon.mp3.catalog.fragment.viewmodel.BaseAndroidViewModel r8, java.util.List r9, java.util.List r10, kotlin.coroutines.Continuation r11) {
        /*
            boolean r0 = r11 instanceof com.amazon.mp3.catalog.fragment.viewmodel.BaseAndroidViewModel.AnonymousClass1
            if (r0 == 0) goto L13
            r0 = r11
            com.amazon.mp3.catalog.fragment.viewmodel.BaseAndroidViewModel$checkModelsForUpdates$1 r0 = (com.amazon.mp3.catalog.fragment.viewmodel.BaseAndroidViewModel.AnonymousClass1) r0
            int r1 = r0.label
            r2 = -2147483648(0xffffffff80000000, float:-0.0)
            r3 = r1 & r2
            if (r3 == 0) goto L13
            int r1 = r1 - r2
            r0.label = r1
            goto L18
        L13:
            com.amazon.mp3.catalog.fragment.viewmodel.BaseAndroidViewModel$checkModelsForUpdates$1 r0 = new com.amazon.mp3.catalog.fragment.viewmodel.BaseAndroidViewModel$checkModelsForUpdates$1
            r0.<init>(r11)
        L18:
            java.lang.Object r11 = r0.result
            java.lang.Object r1 = kotlin.coroutines.intrinsics.IntrinsicsKt.getCOROUTINE_SUSPENDED()
            int r2 = r0.label
            r3 = 1
            if (r2 == 0) goto L47
            if (r2 != r3) goto L3f
            int r8 = r0.I$1
            int r9 = r0.I$0
            java.lang.Object r10 = r0.L$2
            java.util.List r10 = (java.util.List) r10
            java.lang.Object r2 = r0.L$1
            java.util.List r2 = (java.util.List) r2
            java.lang.Object r4 = r0.L$0
            com.amazon.mp3.catalog.fragment.viewmodel.BaseAndroidViewModel r4 = (com.amazon.mp3.catalog.fragment.viewmodel.BaseAndroidViewModel) r4
            kotlin.ResultKt.throwOnFailure(r11)
            r11 = r10
            r10 = r2
            r2 = r1
            r1 = r0
            r0 = r9
            r9 = r4
            goto L57
        L3f:
            java.lang.IllegalStateException r8 = new java.lang.IllegalStateException
            java.lang.String r9 = "call to 'resume' before 'invoke' with coroutine"
            r8.<init>(r9)
            throw r8
        L47:
            kotlin.ResultKt.throwOnFailure(r11)
            r11 = 0
            int r2 = r9.size()
            r7 = r9
            r9 = r8
            r8 = r2
            r2 = r1
            r1 = r0
            r0 = r11
            r11 = r10
            r10 = r7
        L57:
            if (r0 >= r8) goto L93
            int r4 = r0 + 1
            java.lang.Object r5 = r10.get(r0)
            com.amazon.music.views.library.models.base.BaseViewModel r5 = (com.amazon.music.views.library.models.base.BaseViewModel) r5
            java.lang.Object r0 = r11.get(r0)
            com.amazon.music.views.library.models.base.BaseViewModel r0 = (com.amazon.music.views.library.models.base.BaseViewModel) r0
            boolean r6 = r5 instanceof com.amazon.music.views.library.models.base.BaseViewContainerModel
            if (r6 == 0) goto L8e
            boolean r6 = r0 instanceof com.amazon.music.views.library.models.base.BaseViewContainerModel
            if (r6 == 0) goto L8e
            com.amazon.music.views.library.models.base.BaseViewContainerModel r5 = (com.amazon.music.views.library.models.base.BaseViewContainerModel) r5
            java.util.List r5 = r5.getModels()
            com.amazon.music.views.library.models.base.BaseViewContainerModel r0 = (com.amazon.music.views.library.models.base.BaseViewContainerModel) r0
            java.util.List r0 = r0.getModels()
            r1.L$0 = r9
            r1.L$1 = r10
            r1.L$2 = r11
            r1.I$0 = r4
            r1.I$1 = r8
            r1.label = r3
            java.lang.Object r0 = r9.checkModelsForUpdates(r5, r0, r1)
            if (r0 != r2) goto L91
            return r2
        L8e:
            r5.checkForChanges(r0)
        L91:
            r0 = r4
            goto L57
        L93:
            kotlin.Unit r8 = kotlin.Unit.INSTANCE
            return r8
        */
        throw new UnsupportedOperationException("Method not decompiled: com.amazon.mp3.catalog.fragment.viewmodel.BaseAndroidViewModel.checkModelsForUpdates$suspendImpl(com.amazon.mp3.catalog.fragment.viewmodel.BaseAndroidViewModel, java.util.List, java.util.List, kotlin.coroutines.Continuation):java.lang.Object");
    }

    @Override // com.amazon.mp3.catalog.fragment.viewmodel.PageViewModelProvider
    public synchronized void fetchMorePage(final int pageIndex, int pageSize) {
        if (this.usePagination) {
            final PageGridViewModel value = this.pageData.getValue();
            String nextPageToken = null;
            Integer numValueOf = value == null ? null : Integer.valueOf(value.getPageIndex());
            if (value != null) {
                nextPageToken = value.getNextPageToken();
            }
            if (numValueOf == null) {
                fetchInitPage(pageSize);
                return;
            }
            if (!this.isFetching.get() && pageIndex > numValueOf.intValue()) {
                setIsFetching(true);
                Disposable disposableSubscribe = this.pageDataRepository.fetchPageViewModels(pageIndex, pageSize, FetchType.MORE, nextPageToken).subscribeOn(Schedulers.from(this.executorService)).observeOn(Schedulers.io()).map(new Function() { // from class: com.amazon.mp3.catalog.fragment.viewmodel.BaseAndroidViewModel$$ExternalSyntheticLambda10
                    @Override // io.reactivex.functions.Function
                    public final Object apply(Object obj) {
                        return BaseAndroidViewModel.m610fetchMorePage$lambda2(this.f$0, value, pageIndex, (PageGridViewModel) obj);
                    }
                }).subscribe((BiConsumer<? super R, ? super Throwable>) new BiConsumer() { // from class: com.amazon.mp3.catalog.fragment.viewmodel.BaseAndroidViewModel$$ExternalSyntheticLambda11
                    @Override // io.reactivex.functions.BiConsumer
                    public final void accept(Object obj, Object obj2) {
                        BaseAndroidViewModel.m611fetchMorePage$lambda3(this.f$0, pageIndex, (PageGridViewModel) obj, (Throwable) obj2);
                    }
                });
                Intrinsics.checkNotNullExpressionValue(disposableSubscribe, "pageDataRepository.fetch…eIndex)\n                }");
                this.compositeDisposable.add(disposableSubscribe);
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: fetchMorePage$lambda-2, reason: not valid java name */
    public static final PageGridViewModel m610fetchMorePage$lambda2(BaseAndroidViewModel this$0, PageGridViewModel pageGridViewModel, int i, PageGridViewModel newPageData) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        Intrinsics.checkNotNullParameter(newPageData, "newPageData");
        return this$0.mergePageData(pageGridViewModel, newPageData, i);
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: fetchMorePage$lambda-3, reason: not valid java name */
    public static final void m611fetchMorePage$lambda3(BaseAndroidViewModel this$0, int i, PageGridViewModel pageGridViewModel, Throwable th) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        this$0.setIsFetching(false);
        if (th == null) {
            this$0.handlePaginatedResponse(pageGridViewModel);
        } else {
            this$0.onPageError(th, i);
        }
    }

    public synchronized void fetchPageAndReplace(final int startPageIndex, final int pageSize, final int pageCount, final boolean shouldCancelPreviousRequest) {
        final PageGridViewModel value = this.pageData.getValue();
        if (value == null) {
            return;
        }
        if (shouldCancelPreviousRequest) {
            Disposable disposable = this.fetchPageAndReplaceDisposable;
            if (disposable != null) {
                disposable.dispose();
            }
            this.fetchPageAndReplaceDisposable = null;
        }
        IntRange intRangeUntil = RangesKt___RangesKt.until(0, pageCount);
        ArrayList arrayList = new ArrayList(CollectionsKt__IterablesKt.collectionSizeOrDefault(intRangeUntil, 10));
        Iterator<Integer> it = intRangeUntil.iterator();
        while (it.hasNext()) {
            arrayList.add(PageDataRepository.fetchPageViewModels$default(getPageDataRepository(), startPageIndex + ((IntIterator) it).nextInt(), pageSize, FetchType.FETCH_AND_REPLACE, null, 8, null).toObservable());
        }
        Disposable disposableSubscribe = Observable.zip(arrayList, new Function() { // from class: com.amazon.mp3.catalog.fragment.viewmodel.BaseAndroidViewModel$$ExternalSyntheticLambda4
            @Override // io.reactivex.functions.Function
            public final Object apply(Object obj) {
                return BaseAndroidViewModel.m615fetchPageAndReplace$lambda6((Object[]) obj);
            }
        }).observeOn(Schedulers.io()).map(new Function() { // from class: com.amazon.mp3.catalog.fragment.viewmodel.BaseAndroidViewModel$$ExternalSyntheticLambda5
            @Override // io.reactivex.functions.Function
            public final Object apply(Object obj) {
                return BaseAndroidViewModel.m616fetchPageAndReplace$lambda7(startPageIndex, pageSize, pageCount, value, this, (PageGridViewModel) obj);
            }
        }).doOnSubscribe(new Consumer() { // from class: com.amazon.mp3.catalog.fragment.viewmodel.BaseAndroidViewModel$$ExternalSyntheticLambda6
            @Override // io.reactivex.functions.Consumer
            public final void accept(Object obj) {
                BaseAndroidViewModel.m617fetchPageAndReplace$lambda8(shouldCancelPreviousRequest, this, startPageIndex, pageSize, pageCount, (Disposable) obj);
            }
        }).doOnDispose(new Action() { // from class: com.amazon.mp3.catalog.fragment.viewmodel.BaseAndroidViewModel$$ExternalSyntheticLambda7
            @Override // io.reactivex.functions.Action
            public final void run() {
                BaseAndroidViewModel.m612fetchPageAndReplace$lambda10(shouldCancelPreviousRequest, this);
            }
        }).subscribe(new Consumer() { // from class: com.amazon.mp3.catalog.fragment.viewmodel.BaseAndroidViewModel$$ExternalSyntheticLambda8
            @Override // io.reactivex.functions.Consumer
            public final void accept(Object obj) {
                BaseAndroidViewModel.m613fetchPageAndReplace$lambda11(shouldCancelPreviousRequest, this, (Pair) obj);
            }
        }, new Consumer() { // from class: com.amazon.mp3.catalog.fragment.viewmodel.BaseAndroidViewModel$$ExternalSyntheticLambda9
            @Override // io.reactivex.functions.Consumer
            public final void accept(Object obj) {
                BaseAndroidViewModel.m614fetchPageAndReplace$lambda12(this.f$0, startPageIndex, (Throwable) obj);
            }
        });
        if (shouldCancelPreviousRequest) {
            this.fetchPageAndReplaceDisposable = disposableSubscribe;
        } else {
            this.compositeDisposable.add(disposableSubscribe);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: fetchPageAndReplace$lambda-7, reason: not valid java name */
    public static final Pair m616fetchPageAndReplace$lambda7(int i, int i2, int i3, PageGridViewModel pageGridViewModel, BaseAndroidViewModel this$0, PageGridViewModel newPageData) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        Intrinsics.checkNotNullParameter(newPageData, "newPageData");
        int iMax = Math.max(i * i2, 0);
        int iMin = Math.min((i + i3) * i2, pageGridViewModel.getModels().size());
        PageGridViewModel pageGridViewModelReplacePageData = this$0.replacePageData(pageGridViewModel, newPageData, iMax, iMin);
        pageGridViewModelReplacePageData.setPageIndex(pageGridViewModel.getPageIndex() + 1);
        return new Pair(pageGridViewModelReplacePageData, new IntRange(iMax, Math.min(iMin, pageGridViewModelReplacePageData.getModels().size())));
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: fetchPageAndReplace$lambda-8, reason: not valid java name */
    public static final void m617fetchPageAndReplace$lambda8(boolean z, BaseAndroidViewModel this$0, int i, int i2, int i3, Disposable disposable) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        if (z) {
            this$0.latestReplaceablePageRequest = new PageRequest(i, i2, i3);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: fetchPageAndReplace$lambda-10, reason: not valid java name */
    public static final void m612fetchPageAndReplace$lambda10(boolean z, BaseAndroidViewModel this$0) {
        PageRequest pageRequest;
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        if (!z || (pageRequest = this$0.latestReplaceablePageRequest) == null) {
            return;
        }
        this$0.didCancelPageRequest(pageRequest.getIndex(), pageRequest.getSize(), pageRequest.getCount());
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: fetchPageAndReplace$lambda-11, reason: not valid java name */
    public static final void m613fetchPageAndReplace$lambda11(boolean z, BaseAndroidViewModel this$0, Pair pair) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        if (z) {
            this$0.latestReplaceablePageRequest = null;
        }
        this$0.didReplaceItemsAtRange((PageGridViewModel) pair.getFirst(), (IntRange) pair.getSecond());
        this$0.handlePaginatedResponse((PageGridViewModel) pair.getFirst());
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: fetchPageAndReplace$lambda-12, reason: not valid java name */
    public static final void m614fetchPageAndReplace$lambda12(BaseAndroidViewModel this$0, int i, Throwable error) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        Intrinsics.checkNotNullExpressionValue(error, "error");
        this$0.onPageError(error, i);
    }

    protected void handlePaginatedResponse(PageGridViewModel pageModel) {
        onPageResult(pageModel);
    }

    @Override // androidx.lifecycle.ViewModel
    protected void onCleared() {
        super.onCleared();
        setIsFetching(false);
        this.compositeDisposable.clear();
        this.pageStateManager.onCleared();
        LibraryEqualizerStateManager libraryEqualizerStateManager = this.equalizerStateManager;
        if (libraryEqualizerStateManager != null) {
            libraryEqualizerStateManager.onDestroy();
        }
        stopObservingNetworkEvents();
        stopObservingLibrarySyncEvents();
        Disposable disposable = this.fetchPageAndReplaceDisposable;
        if (disposable != null) {
            disposable.dispose();
        }
        this.fetchPageAndReplaceDisposable = null;
    }

    private final synchronized void setIsFetching(boolean isFetching) {
        this.isFetching.compareAndSet(!isFetching, isFetching);
    }

    protected void onPageResult(PageGridViewModel pageModel) {
        this.pageData.postValue(pageModel);
        refreshModelStateProviders();
    }

    protected void onPageError(Throwable error, int pageIndex) {
        Intrinsics.checkNotNullParameter(error, "error");
        this.pageErrorData.postValue(new ErrorModel(error, pageIndex));
        Log.error(BaseAndroidViewModel.class.getSimpleName(), "Error trying to load page at index " + pageIndex + ": " + ((Object) error.getMessage()));
    }

    protected PageGridViewModel mergePageData(PageGridViewModel originalPageData, PageGridViewModel newPageData, int pageIndex) {
        Intrinsics.checkNotNullParameter(originalPageData, "originalPageData");
        Intrinsics.checkNotNullParameter(newPageData, "newPageData");
        return originalPageData.copy((1839 & 1) != 0 ? originalPageData.viewId : null, (1839 & 2) != 0 ? originalPageData.nestedModelViewId : null, (1839 & 4) != 0 ? originalPageData.title : null, (1839 & 8) != 0 ? originalPageData.subTitle : null, (1839 & 16) != 0 ? originalPageData.getModels() : CollectionsKt___CollectionsKt.plus((Collection) originalPageData.getModels(), (Iterable) newPageData.getModels()), (1839 & 32) != 0 ? originalPageData.getTileType() : 0, (1839 & 64) != 0 ? originalPageData.pageIndex : pageIndex, (1839 & 128) != 0 ? originalPageData.nextPageToken : newPageData.getNextPageToken(), (1839 & 256) != 0 ? originalPageData.filters : null, (1839 & 512) != 0 ? originalPageData.context : null, (1839 & 1024) != 0 ? originalPageData.header : null);
    }

    protected PageGridViewModel replacePageData(PageGridViewModel originalPageData, PageGridViewModel newPageData, int startPosition, int endPosition) {
        List<BaseViewModel> listEmptyList;
        Intrinsics.checkNotNullParameter(originalPageData, "originalPageData");
        Intrinsics.checkNotNullParameter(newPageData, "newPageData");
        List<BaseViewModel> models = originalPageData.getModels();
        List listPlus = CollectionsKt___CollectionsKt.plus((Collection) models.subList(0, startPosition), (Iterable) newPageData.getModels());
        if (endPosition < models.size()) {
            listEmptyList = models.subList(endPosition, models.size());
        } else {
            listEmptyList = CollectionsKt__CollectionsKt.emptyList();
        }
        return originalPageData.copy((1839 & 1) != 0 ? originalPageData.viewId : null, (1839 & 2) != 0 ? originalPageData.nestedModelViewId : null, (1839 & 4) != 0 ? originalPageData.title : null, (1839 & 8) != 0 ? originalPageData.subTitle : null, (1839 & 16) != 0 ? originalPageData.getModels() : CollectionsKt___CollectionsKt.plus((Collection) listPlus, (Iterable) listEmptyList), (1839 & 32) != 0 ? originalPageData.getTileType() : 0, (1839 & 64) != 0 ? originalPageData.pageIndex : 0, (1839 & 128) != 0 ? originalPageData.nextPageToken : newPageData.getNextPageToken(), (1839 & 256) != 0 ? originalPageData.filters : null, (1839 & 512) != 0 ? originalPageData.context : null, (1839 & 1024) != 0 ? originalPageData.header : null);
    }

    public void startObservingLibrarySyncEvents() {
        this.libraryStateUpdater.startObservingLibraryStateEvents();
        this.librarySyncStateSubscription = this.libraryStateUpdater.getLibraryStateObserver().subscribe(new Action1() { // from class: com.amazon.mp3.catalog.fragment.viewmodel.BaseAndroidViewModel$$ExternalSyntheticLambda0
            @Override // rx.functions.Action1
            public final void call(Object obj) {
                BaseAndroidViewModel.m619startObservingLibrarySyncEvents$lambda13(this.f$0, (LibrarySyncStateProvider.LibrarySyncState) obj);
            }
        });
        this.librarySyncEventSubscription = this.libraryStateUpdater.getLibraryEventObserver().subscribe(new Action1() { // from class: com.amazon.mp3.catalog.fragment.viewmodel.BaseAndroidViewModel$$ExternalSyntheticLambda1
            @Override // rx.functions.Action1
            public final void call(Object obj) {
                BaseAndroidViewModel.m620startObservingLibrarySyncEvents$lambda14(this.f$0, (LibrarySyncStateUpdater.LibrarySyncEvent) obj);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: startObservingLibrarySyncEvents$lambda-13, reason: not valid java name */
    public static final void m619startObservingLibrarySyncEvents$lambda13(BaseAndroidViewModel this$0, LibrarySyncStateProvider.LibrarySyncState librarySyncState) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        this$0.librarySyncStateLiveData.postValue(librarySyncState);
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: startObservingLibrarySyncEvents$lambda-14, reason: not valid java name */
    public static final void m620startObservingLibrarySyncEvents$lambda14(BaseAndroidViewModel this$0, LibrarySyncStateUpdater.LibrarySyncEvent librarySyncEvent) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        int i = librarySyncEvent == null ? -1 : WhenMappings.$EnumSwitchMapping$0[librarySyncEvent.ordinal()];
        if (i == 1 || i == 2 || i == 3) {
            this$0.librarySyncEventLiveData.postValue(librarySyncEvent);
        }
    }

    public void stopObservingLibrarySyncEvents() {
        this.libraryStateUpdater.stopObservingLibraryStateEvents();
        Subscription subscription = this.librarySyncStateSubscription;
        if (subscription != null) {
            subscription.unsubscribe();
        }
        this.librarySyncStateSubscription = null;
        Subscription subscription2 = this.librarySyncEventSubscription;
        if (subscription2 != null) {
            subscription2.unsubscribe();
        }
        this.librarySyncEventSubscription = null;
    }

    @Override // com.amazon.mp3.catalog.fragment.viewmodel.LibraryStateObserver
    public MutableLiveData<LibrarySyncStateProvider.LibrarySyncState> getLibrarySyncStateLiveData() {
        return this.librarySyncStateLiveData;
    }

    @Override // com.amazon.mp3.catalog.fragment.viewmodel.LibraryStateObserver
    public MutableLiveData<LibrarySyncStateUpdater.LibrarySyncEvent> getLibrarySyncEventLiveData() {
        return this.librarySyncEventLiveData;
    }

    @Override // com.amazon.mp3.catalog.fragment.viewmodel.LibraryStateObserver
    public LiveData<RepositoryEvent> getLocalRepositoryUpdateEventLiveData() {
        return getLocalRepositoryUpdateLiveData();
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: fetchPageAndReplace$lambda-6, reason: not valid java name */
    public static final PageGridViewModel m615fetchPageAndReplace$lambda6(Object[] it) {
        Intrinsics.checkNotNullParameter(it, "it");
        ArrayList arrayList = new ArrayList();
        int length = it.length;
        int i = 0;
        while (i < length) {
            Object obj = it[i];
            i++;
            if (obj instanceof PageGridViewModel) {
                arrayList.add(obj);
            }
        }
        Iterator it2 = arrayList.iterator();
        if (!it2.hasNext()) {
            throw new UnsupportedOperationException("Empty collection can't be reduced.");
        }
        Object next = it2.next();
        while (it2.hasNext()) {
            PageGridViewModel pageGridViewModel = (PageGridViewModel) it2.next();
            PageGridViewModel pageGridViewModel2 = (PageGridViewModel) next;
            next = pageGridViewModel2.copy((1839 & 1) != 0 ? pageGridViewModel2.viewId : null, (1839 & 2) != 0 ? pageGridViewModel2.nestedModelViewId : null, (1839 & 4) != 0 ? pageGridViewModel2.title : null, (1839 & 8) != 0 ? pageGridViewModel2.subTitle : null, (1839 & 16) != 0 ? pageGridViewModel2.getModels() : CollectionsKt___CollectionsKt.plus((Collection) pageGridViewModel2.getModels(), (Iterable) pageGridViewModel.getModels()), (1839 & 32) != 0 ? pageGridViewModel2.getTileType() : 0, (1839 & 64) != 0 ? pageGridViewModel2.pageIndex : 0, (1839 & 128) != 0 ? pageGridViewModel2.nextPageToken : pageGridViewModel.getNextPageToken(), (1839 & 256) != 0 ? pageGridViewModel2.filters : null, (1839 & 512) != 0 ? pageGridViewModel2.context : null, (1839 & 1024) != 0 ? pageGridViewModel2.header : null);
        }
        return (PageGridViewModel) next;
    }
}
