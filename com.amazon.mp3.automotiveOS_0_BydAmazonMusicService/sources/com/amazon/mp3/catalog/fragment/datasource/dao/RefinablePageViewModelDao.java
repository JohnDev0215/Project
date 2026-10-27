package com.amazon.mp3.catalog.fragment.datasource.dao;

import com.amazon.mp3.AmazonApplication;
import com.amazon.mp3.catalog.fragment.datasource.dao.PageViewModelDao;
import com.amazon.mp3.catalog.fragment.viewmodel.FetchType;
import com.amazon.mp3.library.refinement.Refinable;
import com.amazon.mp3.library.refinement.datasource.DatabaseSource;
import com.amazon.mp3.library.refinement.datasource.DatabaseSourcePaged;
import com.amazon.mp3.library.refinement.datasource.RefinableDataSource;
import com.amazon.mp3.library.refinement.filter.Filter;
import com.amazon.mp3.library.refinement.sort.Sort;
import com.amazon.mp3.library.refinement.sort.SortOrder;
import com.amazon.mp3.view.refinements.DefaultRefinementDataProvider;
import com.amazon.music.find.utils.PageUriUtils;
import com.amazon.music.metrics.mts.event.definition.latency.LatencyEventDescriptor;
import com.amazon.music.metrics.mts.event.definition.latency.LatencyTracker;
import com.amazon.music.metrics.mts.event.types.pagelatencyinfo.LatencyTrackingLeg;
import com.amazon.music.metrics.mts.event.types.pagelatencyinfo.PageLoadLatencyCode;
import com.amazon.music.metrics.mts.event.types.uiinteraction.PageType;
import com.amazon.music.views.library.models.PageGridViewModel;
import io.reactivex.Single;
import io.reactivex.SingleEmitter;
import io.reactivex.SingleOnSubscribe;
import io.reactivex.schedulers.Schedulers;
import java.util.ArrayList;
import java.util.Collection;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt__MutableCollectionsKt;
import kotlin.collections.CollectionsKt___CollectionsKt;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.jvm.internal.ContinuationImpl;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.BuildersKt__BuildersKt;
import kotlinx.coroutines.Deferred;

/* JADX INFO: compiled from: RefinablePageViewModelDao.kt */
/* JADX INFO: loaded from: classes3.dex */
@Metadata(d1 = {"\u0000~\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0002\u0010 \n\u0002\u0010\b\n\u0002\u0018\u0002\n\u0002\b\f\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\r\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0010$\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0007\b&\u0018\u0000 K*\b\b\u0000\u0010\u0001*\u00020\u0002*\u0012\b\u0001\u0010\u0003*\f\u0012\u0004\u0012\u0002H\u0001\u0012\u0002\b\u00030\u00042\u00020\u0005:\u0001KB#\u0012\b\u0010\u0006\u001a\u0004\u0018\u00010\u0007\u0012\b\b\u0002\u0010\b\u001a\u00020\t\u0012\b\b\u0002\u0010\n\u001a\u00020\t¢\u0006\u0002\u0010\u000bJ3\u0010$\u001a\u00020%\"\u0018\b\u0002\u0010&*\u0012\u0012\u0004\u0012\u00028\u0000\u0012\u0002\b\u0003\u0012\u0004\u0012\u00028\u00010'2\u0006\u0010(\u001a\u0002H&H\u0086@ø\u0001\u0000¢\u0006\u0002\u0010)J/\u0010*\u001a\u00020%\"\u0014\b\u0002\u0010+*\u000e\u0012\u0004\u0012\u00028\u0000\u0012\u0004\u0012\u00028\u00010,2\u0006\u0010-\u001a\u0002H+H\u0086@ø\u0001\u0000¢\u0006\u0002\u0010.J\b\u0010/\u001a\u00020%H\u0016J\u0011\u00100\u001a\u00020%H\u0086@ø\u0001\u0000¢\u0006\u0002\u00101J!\u00100\u001a\u00020%2\u000e\u00102\u001a\n\u0012\u0004\u0012\u00028\u0001\u0018\u00010\rH\u0082@ø\u0001\u0000¢\u0006\u0002\u00103J;\u00104\u001a\b\u0012\u0004\u0012\u00028\u00000\u00162\u0006\u00105\u001a\u00020\t2\u0006\u00106\u001a\u00028\u00012\u0006\u00107\u001a\u00020\u00172\u0006\u00108\u001a\u00020\u00172\u0006\u00109\u001a\u00020:H\u0002¢\u0006\u0002\u0010;J0\u0010<\u001a\b\u0012\u0004\u0012\u00020\u00180=2\u0006\u00107\u001a\u00020\u00172\u0006\u00108\u001a\u00020\u00172\u0006\u00109\u001a\u00020:2\b\u0010>\u001a\u0004\u0018\u00010?H\u0016J\u000e\u0010@\u001a\n\u0012\u0004\u0012\u00028\u0001\u0018\u00010\rJ\u001c\u0010A\u001a\u000e\u0012\u0004\u0012\u00020?\u0012\u0004\u0012\u00020C0B2\u0006\u0010D\u001a\u00020EH\u0004J3\u0010F\u001a\u00020%\"\u0018\b\u0002\u0010&*\u0012\u0012\u0004\u0012\u00028\u0000\u0012\u0002\b\u0003\u0012\u0004\u0012\u00028\u00010'2\u0006\u0010(\u001a\u0002H&H\u0086@ø\u0001\u0000¢\u0006\u0002\u0010)J\u0011\u0010G\u001a\u00020%H\u0086@ø\u0001\u0000¢\u0006\u0002\u00101J!\u0010G\u001a\u00020%2\u000e\u00102\u001a\n\u0012\u0004\u0012\u00028\u0001\u0018\u00010\rH\u0082@ø\u0001\u0000¢\u0006\u0002\u00103J\u0010\u0010H\u001a\u00020\t2\u0006\u00109\u001a\u00020:H\u0002J\b\u0010I\u001a\u00020%H\u0004J\b\u0010J\u001a\u00020%H\u0004R\u001a\u0010\f\u001a\n\u0012\u0004\u0012\u00028\u0001\u0018\u00010\rX¤\u0004¢\u0006\u0006\u001a\u0004\b\u000e\u0010\u000fR\u001a\u0010\u0010\u001a\n\u0012\u0004\u0012\u00028\u0001\u0018\u00010\rX¤\u0004¢\u0006\u0006\u001a\u0004\b\u0011\u0010\u000fR\u0016\u0010\u0006\u001a\u0004\u0018\u00010\u0007X\u0094\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0012\u0010\u0013R*\u0010\u0014\u001a\u001a\u0012\n\u0012\b\u0012\u0004\u0012\u00028\u00000\u0016\u0012\u0004\u0012\u00020\u0017\u0012\u0004\u0012\u00020\u00180\u0015X¤\u0004¢\u0006\u0006\u001a\u0004\b\u0019\u0010\u001aR\u0018\u0010\u001b\u001a\u0004\u0018\u00018\u0000X\u0094\u0004¢\u0006\n\n\u0002\u0010\u001e\u001a\u0004\b\u001c\u0010\u001dR\u000e\u0010\b\u001a\u00020\tX\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\n\u001a\u00020\tX\u0082\u0004¢\u0006\u0002\n\u0000R\u001a\u0010\u001f\u001a\u00020\tX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b \u0010!\"\u0004\b\"\u0010#\u0082\u0002\u0004\n\u0002\b\u0019¨\u0006L"}, d2 = {"Lcom/amazon/mp3/catalog/fragment/datasource/dao/RefinablePageViewModelDao;", "R", "Lcom/amazon/mp3/library/refinement/Refinable;", "D", "Lcom/amazon/mp3/library/refinement/datasource/RefinableDataSource;", "Lcom/amazon/mp3/catalog/fragment/datasource/dao/PageViewModelDao;", "pageLoadLatencyCode", "Lcom/amazon/music/metrics/mts/event/types/pagelatencyinfo/PageLoadLatencyCode;", "shouldAddPlaceHolders", "", "shouldUsePagination", "(Lcom/amazon/music/metrics/mts/event/types/pagelatencyinfo/PageLoadLatencyCode;ZZ)V", "offlineDataSource", "Lkotlinx/coroutines/Deferred;", "getOfflineDataSource", "()Lkotlinx/coroutines/Deferred;", "onlineDataSource", "getOnlineDataSource", "getPageLoadLatencyCode", "()Lcom/amazon/music/metrics/mts/event/types/pagelatencyinfo/PageLoadLatencyCode;", "pageModelConverter", "Lkotlin/Function2;", "", "", "Lcom/amazon/music/views/library/models/PageGridViewModel;", "getPageModelConverter", "()Lkotlin/jvm/functions/Function2;", "placeHolder", "getPlaceHolder", "()Lcom/amazon/mp3/library/refinement/Refinable;", "Lcom/amazon/mp3/library/refinement/Refinable;", "useOnlineSource", "getUseOnlineSource", "()Z", "setUseOnlineSource", "(Z)V", "addFilter", "", "F", "Lcom/amazon/mp3/library/refinement/filter/Filter;", PageUriUtils.FILTER_QUERY_PARAM, "(Lcom/amazon/mp3/library/refinement/filter/Filter;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "addSort", "S", "Lcom/amazon/mp3/library/refinement/sort/Sort;", "sort", "(Lcom/amazon/mp3/library/refinement/sort/Sort;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "clearListeners", "clearSort", "(Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "databaseSource", "(Lkotlinx/coroutines/Deferred;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "createResponseFromDatabase", "usesPagination", "dataSource", "pageIndex", "pageSize", "fetchType", "Lcom/amazon/mp3/catalog/fragment/viewmodel/FetchType;", "(ZLcom/amazon/mp3/library/refinement/datasource/RefinableDataSource;IILcom/amazon/mp3/catalog/fragment/viewmodel/FetchType;)Ljava/util/List;", "fetchViewModelItems", "Lio/reactivex/Single;", "nextPageToken", "", "getDataSource", "getDefaultSortColumn", "", "Lcom/amazon/mp3/library/refinement/sort/SortOrder;", "pageType", "Lcom/amazon/music/metrics/mts/event/types/uiinteraction/PageType;", "removeFilter", "reset", "shouldAddPlaceholders", "startTrackingProcessingLatency", "startTrackingResponseLatency", "Companion", "DigitalMusicAndroid3P_automotiveProdRelease"}, k = 1, mv = {1, 6, 0}, xi = 48)
public abstract class RefinablePageViewModelDao<R extends Refinable, D extends RefinableDataSource<R, ?>> implements PageViewModelDao {

    /* JADX INFO: renamed from: Companion, reason: from kotlin metadata */
    public static final Companion INSTANCE = new Companion(null);
    private static final String TAG = RefinablePageViewModelDao.class.getSimpleName();
    private final PageLoadLatencyCode pageLoadLatencyCode;
    private final R placeHolder;
    private final boolean shouldAddPlaceHolders;
    private final boolean shouldUsePagination;
    private boolean useOnlineSource;

    /* JADX INFO: renamed from: com.amazon.mp3.catalog.fragment.datasource.dao.RefinablePageViewModelDao$addFilter$1, reason: invalid class name */
    /* JADX INFO: compiled from: RefinablePageViewModelDao.kt */
    @Metadata(k = 3, mv = {1, 6, 0}, xi = 48)
    @DebugMetadata(c = "com.amazon.mp3.catalog.fragment.datasource.dao.RefinablePageViewModelDao", f = "RefinablePageViewModelDao.kt", i = {0, 0, 1}, l = {56, 60}, m = "addFilter", n = {"this", PageUriUtils.FILTER_QUERY_PARAM, PageUriUtils.FILTER_QUERY_PARAM}, s = {"L$0", "L$1", "L$0"})
    static final class AnonymousClass1<F extends Filter<R, ?, D>> extends ContinuationImpl {
        Object L$0;
        Object L$1;
        int label;
        /* synthetic */ Object result;
        final /* synthetic */ RefinablePageViewModelDao<R, D> this$0;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        AnonymousClass1(RefinablePageViewModelDao<R, D> refinablePageViewModelDao, Continuation<? super AnonymousClass1> continuation) {
            super(continuation);
            this.this$0 = refinablePageViewModelDao;
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object obj) {
            this.result = obj;
            this.label |= Integer.MIN_VALUE;
            return this.this$0.addFilter(null, this);
        }
    }

    /* JADX INFO: renamed from: com.amazon.mp3.catalog.fragment.datasource.dao.RefinablePageViewModelDao$addSort$1, reason: invalid class name and case insensitive filesystem */
    /* JADX INFO: compiled from: RefinablePageViewModelDao.kt */
    @Metadata(k = 3, mv = {1, 6, 0}, xi = 48)
    @DebugMetadata(c = "com.amazon.mp3.catalog.fragment.datasource.dao.RefinablePageViewModelDao", f = "RefinablePageViewModelDao.kt", i = {0, 0, 1}, l = {78, 82}, m = "addSort", n = {"this", "sort", "sort"}, s = {"L$0", "L$1", "L$0"})
    static final class C03191<S extends Sort<R, D>> extends ContinuationImpl {
        Object L$0;
        Object L$1;
        int label;
        /* synthetic */ Object result;
        final /* synthetic */ RefinablePageViewModelDao<R, D> this$0;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        C03191(RefinablePageViewModelDao<R, D> refinablePageViewModelDao, Continuation<? super C03191> continuation) {
            super(continuation);
            this.this$0 = refinablePageViewModelDao;
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object obj) {
            this.result = obj;
            this.label |= Integer.MIN_VALUE;
            return this.this$0.addSort(null, this);
        }
    }

    /* JADX INFO: renamed from: com.amazon.mp3.catalog.fragment.datasource.dao.RefinablePageViewModelDao$clearSort$1, reason: invalid class name and case insensitive filesystem */
    /* JADX INFO: compiled from: RefinablePageViewModelDao.kt */
    @Metadata(k = 3, mv = {1, 6, 0}, xi = 48)
    @DebugMetadata(c = "com.amazon.mp3.catalog.fragment.datasource.dao.RefinablePageViewModelDao", f = "RefinablePageViewModelDao.kt", i = {0}, l = {89, 90}, m = "clearSort", n = {"this"}, s = {"L$0"})
    static final class C03201 extends ContinuationImpl {
        Object L$0;
        int label;
        /* synthetic */ Object result;
        final /* synthetic */ RefinablePageViewModelDao<R, D> this$0;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        C03201(RefinablePageViewModelDao<R, D> refinablePageViewModelDao, Continuation<? super C03201> continuation) {
            super(continuation);
            this.this$0 = refinablePageViewModelDao;
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object obj) {
            this.result = obj;
            this.label |= Integer.MIN_VALUE;
            return this.this$0.clearSort(this);
        }
    }

    /* JADX INFO: renamed from: com.amazon.mp3.catalog.fragment.datasource.dao.RefinablePageViewModelDao$clearSort$2, reason: invalid class name */
    /* JADX INFO: compiled from: RefinablePageViewModelDao.kt */
    @Metadata(k = 3, mv = {1, 6, 0}, xi = 48)
    @DebugMetadata(c = "com.amazon.mp3.catalog.fragment.datasource.dao.RefinablePageViewModelDao", f = "RefinablePageViewModelDao.kt", i = {}, l = {94}, m = "clearSort", n = {}, s = {})
    static final class AnonymousClass2 extends ContinuationImpl {
        int label;
        /* synthetic */ Object result;
        final /* synthetic */ RefinablePageViewModelDao<R, D> this$0;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        AnonymousClass2(RefinablePageViewModelDao<R, D> refinablePageViewModelDao, Continuation<? super AnonymousClass2> continuation) {
            super(continuation);
            this.this$0 = refinablePageViewModelDao;
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object obj) {
            this.result = obj;
            this.label |= Integer.MIN_VALUE;
            return this.this$0.clearSort(null, this);
        }
    }

    /* JADX INFO: renamed from: com.amazon.mp3.catalog.fragment.datasource.dao.RefinablePageViewModelDao$removeFilter$1, reason: invalid class name and case insensitive filesystem */
    /* JADX INFO: compiled from: RefinablePageViewModelDao.kt */
    @Metadata(k = 3, mv = {1, 6, 0}, xi = 48)
    @DebugMetadata(c = "com.amazon.mp3.catalog.fragment.datasource.dao.RefinablePageViewModelDao", f = "RefinablePageViewModelDao.kt", i = {0, 0, 1}, l = {67, 71}, m = "removeFilter", n = {"this", PageUriUtils.FILTER_QUERY_PARAM, PageUriUtils.FILTER_QUERY_PARAM}, s = {"L$0", "L$1", "L$0"})
    static final class C03211<F extends Filter<R, ?, D>> extends ContinuationImpl {
        Object L$0;
        Object L$1;
        int label;
        /* synthetic */ Object result;
        final /* synthetic */ RefinablePageViewModelDao<R, D> this$0;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        C03211(RefinablePageViewModelDao<R, D> refinablePageViewModelDao, Continuation<? super C03211> continuation) {
            super(continuation);
            this.this$0 = refinablePageViewModelDao;
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object obj) {
            this.result = obj;
            this.label |= Integer.MIN_VALUE;
            return this.this$0.removeFilter(null, this);
        }
    }

    /* JADX INFO: renamed from: com.amazon.mp3.catalog.fragment.datasource.dao.RefinablePageViewModelDao$reset$1, reason: invalid class name and case insensitive filesystem */
    /* JADX INFO: compiled from: RefinablePageViewModelDao.kt */
    @Metadata(k = 3, mv = {1, 6, 0}, xi = 48)
    @DebugMetadata(c = "com.amazon.mp3.catalog.fragment.datasource.dao.RefinablePageViewModelDao", f = "RefinablePageViewModelDao.kt", i = {0}, l = {108, 109}, m = "reset", n = {"this"}, s = {"L$0"})
    static final class C03221 extends ContinuationImpl {
        Object L$0;
        int label;
        /* synthetic */ Object result;
        final /* synthetic */ RefinablePageViewModelDao<R, D> this$0;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        C03221(RefinablePageViewModelDao<R, D> refinablePageViewModelDao, Continuation<? super C03221> continuation) {
            super(continuation);
            this.this$0 = refinablePageViewModelDao;
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object obj) {
            this.result = obj;
            this.label |= Integer.MIN_VALUE;
            return this.this$0.reset(this);
        }
    }

    /* JADX INFO: renamed from: com.amazon.mp3.catalog.fragment.datasource.dao.RefinablePageViewModelDao$reset$2, reason: invalid class name and case insensitive filesystem */
    /* JADX INFO: compiled from: RefinablePageViewModelDao.kt */
    @Metadata(k = 3, mv = {1, 6, 0}, xi = 48)
    @DebugMetadata(c = "com.amazon.mp3.catalog.fragment.datasource.dao.RefinablePageViewModelDao", f = "RefinablePageViewModelDao.kt", i = {}, l = {113}, m = "reset", n = {}, s = {})
    static final class C03232 extends ContinuationImpl {
        int label;
        /* synthetic */ Object result;
        final /* synthetic */ RefinablePageViewModelDao<R, D> this$0;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        C03232(RefinablePageViewModelDao<R, D> refinablePageViewModelDao, Continuation<? super C03232> continuation) {
            super(continuation);
            this.this$0 = refinablePageViewModelDao;
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object obj) {
            this.result = obj;
            this.label |= Integer.MIN_VALUE;
            return this.this$0.reset(null, this);
        }
    }

    public void clearListeners() {
    }

    protected abstract Deferred<D> getOfflineDataSource();

    protected abstract Deferred<D> getOnlineDataSource();

    protected abstract Function2<List<? extends R>, Integer, PageGridViewModel> getPageModelConverter();

    public RefinablePageViewModelDao(PageLoadLatencyCode pageLoadLatencyCode, boolean z, boolean z2) {
        this.pageLoadLatencyCode = pageLoadLatencyCode;
        this.shouldAddPlaceHolders = z;
        this.shouldUsePagination = z2;
        this.useOnlineSource = true;
    }

    @Override // com.amazon.mp3.catalog.fragment.datasource.dao.PageViewModelDao
    public int getDefaultPageSize() {
        return PageViewModelDao.DefaultImpls.getDefaultPageSize(this);
    }

    protected PageLoadLatencyCode getPageLoadLatencyCode() {
        return this.pageLoadLatencyCode;
    }

    public /* synthetic */ RefinablePageViewModelDao(PageLoadLatencyCode pageLoadLatencyCode, boolean z, boolean z2, int i, DefaultConstructorMarker defaultConstructorMarker) {
        this(pageLoadLatencyCode, (i & 2) != 0 ? false : z, (i & 4) != 0 ? AmazonApplication.getCapabilities().shouldEnablePaginationForBauhausPages() : z2);
    }

    /* JADX INFO: compiled from: RefinablePageViewModelDao.kt */
    @Metadata(d1 = {"\u0000\u0014\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0004\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002R\u0019\u0010\u0003\u001a\n \u0005*\u0004\u0018\u00010\u00040\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0006\u0010\u0007¨\u0006\b"}, d2 = {"Lcom/amazon/mp3/catalog/fragment/datasource/dao/RefinablePageViewModelDao$Companion;", "", "()V", "TAG", "", "kotlin.jvm.PlatformType", "getTAG", "()Ljava/lang/String;", "DigitalMusicAndroid3P_automotiveProdRelease"}, k = 1, mv = {1, 6, 0}, xi = 48)
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }

        public final String getTAG() {
            return RefinablePageViewModelDao.TAG;
        }
    }

    public final boolean getUseOnlineSource() {
        return this.useOnlineSource;
    }

    public final void setUseOnlineSource(boolean z) {
        this.useOnlineSource = z;
    }

    public final Deferred<D> getDataSource() {
        return (this.useOnlineSource || getOfflineDataSource() == null) ? getOnlineDataSource() : getOfflineDataSource();
    }

    protected R getPlaceHolder() {
        return this.placeHolder;
    }

    /* JADX WARN: Removed duplicated region for block: B:30:0x0096  */
    /* JADX WARN: Removed duplicated region for block: B:36:0x00a9  */
    /* JADX WARN: Removed duplicated region for block: B:7:0x0013  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final <F extends com.amazon.mp3.library.refinement.filter.Filter<R, ?, D>> java.lang.Object addFilter(F r8, kotlin.coroutines.Continuation<? super kotlin.Unit> r9) {
        /*
            Method dump skipped, instruction units count: 214
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.amazon.mp3.catalog.fragment.datasource.dao.RefinablePageViewModelDao.addFilter(com.amazon.mp3.library.refinement.filter.Filter, kotlin.coroutines.Continuation):java.lang.Object");
    }

    /* JADX WARN: Removed duplicated region for block: B:30:0x0096  */
    /* JADX WARN: Removed duplicated region for block: B:36:0x00a9  */
    /* JADX WARN: Removed duplicated region for block: B:7:0x0013  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final <F extends com.amazon.mp3.library.refinement.filter.Filter<R, ?, D>> java.lang.Object removeFilter(F r8, kotlin.coroutines.Continuation<? super kotlin.Unit> r9) {
        /*
            Method dump skipped, instruction units count: 214
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.amazon.mp3.catalog.fragment.datasource.dao.RefinablePageViewModelDao.removeFilter(com.amazon.mp3.library.refinement.filter.Filter, kotlin.coroutines.Continuation):java.lang.Object");
    }

    /* JADX WARN: Removed duplicated region for block: B:30:0x0096  */
    /* JADX WARN: Removed duplicated region for block: B:36:0x00a9  */
    /* JADX WARN: Removed duplicated region for block: B:7:0x0013  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final <S extends com.amazon.mp3.library.refinement.sort.Sort<R, D>> java.lang.Object addSort(S r8, kotlin.coroutines.Continuation<? super kotlin.Unit> r9) {
        /*
            Method dump skipped, instruction units count: 214
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.amazon.mp3.catalog.fragment.datasource.dao.RefinablePageViewModelDao.addSort(com.amazon.mp3.library.refinement.sort.Sort, kotlin.coroutines.Continuation):java.lang.Object");
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Removed duplicated region for block: B:7:0x0013  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final java.lang.Object clearSort(kotlin.coroutines.Continuation<? super kotlin.Unit> r6) {
        /*
            r5 = this;
            boolean r0 = r6 instanceof com.amazon.mp3.catalog.fragment.datasource.dao.RefinablePageViewModelDao.C03201
            if (r0 == 0) goto L13
            r0 = r6
            com.amazon.mp3.catalog.fragment.datasource.dao.RefinablePageViewModelDao$clearSort$1 r0 = (com.amazon.mp3.catalog.fragment.datasource.dao.RefinablePageViewModelDao.C03201) r0
            int r1 = r0.label
            r2 = -2147483648(0xffffffff80000000, float:-0.0)
            r3 = r1 & r2
            if (r3 == 0) goto L13
            int r1 = r1 - r2
            r0.label = r1
            goto L18
        L13:
            com.amazon.mp3.catalog.fragment.datasource.dao.RefinablePageViewModelDao$clearSort$1 r0 = new com.amazon.mp3.catalog.fragment.datasource.dao.RefinablePageViewModelDao$clearSort$1
            r0.<init>(r5, r6)
        L18:
            java.lang.Object r6 = r0.result
            java.lang.Object r1 = kotlin.coroutines.intrinsics.IntrinsicsKt.getCOROUTINE_SUSPENDED()
            int r2 = r0.label
            r3 = 2
            r4 = 1
            if (r2 == 0) goto L3c
            if (r2 == r4) goto L34
            if (r2 != r3) goto L2c
            kotlin.ResultKt.throwOnFailure(r6)
            goto L5f
        L2c:
            java.lang.IllegalStateException r6 = new java.lang.IllegalStateException
            java.lang.String r0 = "call to 'resume' before 'invoke' with coroutine"
            r6.<init>(r0)
            throw r6
        L34:
            java.lang.Object r2 = r0.L$0
            com.amazon.mp3.catalog.fragment.datasource.dao.RefinablePageViewModelDao r2 = (com.amazon.mp3.catalog.fragment.datasource.dao.RefinablePageViewModelDao) r2
            kotlin.ResultKt.throwOnFailure(r6)
            goto L4f
        L3c:
            kotlin.ResultKt.throwOnFailure(r6)
            kotlinx.coroutines.Deferred r6 = r5.getOnlineDataSource()
            r0.L$0 = r5
            r0.label = r4
            java.lang.Object r6 = r5.clearSort(r6, r0)
            if (r6 != r1) goto L4e
            return r1
        L4e:
            r2 = r5
        L4f:
            kotlinx.coroutines.Deferred r6 = r2.getOfflineDataSource()
            r4 = 0
            r0.L$0 = r4
            r0.label = r3
            java.lang.Object r6 = r2.clearSort(r6, r0)
            if (r6 != r1) goto L5f
            return r1
        L5f:
            kotlin.Unit r6 = kotlin.Unit.INSTANCE
            return r6
        */
        throw new UnsupportedOperationException("Method not decompiled: com.amazon.mp3.catalog.fragment.datasource.dao.RefinablePageViewModelDao.clearSort(kotlin.coroutines.Continuation):java.lang.Object");
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Removed duplicated region for block: B:23:0x004a  */
    /* JADX WARN: Removed duplicated region for block: B:24:0x0064  */
    /* JADX WARN: Removed duplicated region for block: B:7:0x0013  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final java.lang.Object clearSort(kotlinx.coroutines.Deferred<? extends D> r5, kotlin.coroutines.Continuation<? super kotlin.Unit> r6) {
        /*
            r4 = this;
            boolean r0 = r6 instanceof com.amazon.mp3.catalog.fragment.datasource.dao.RefinablePageViewModelDao.AnonymousClass2
            if (r0 == 0) goto L13
            r0 = r6
            com.amazon.mp3.catalog.fragment.datasource.dao.RefinablePageViewModelDao$clearSort$2 r0 = (com.amazon.mp3.catalog.fragment.datasource.dao.RefinablePageViewModelDao.AnonymousClass2) r0
            int r1 = r0.label
            r2 = -2147483648(0xffffffff80000000, float:-0.0)
            r3 = r1 & r2
            if (r3 == 0) goto L13
            int r1 = r1 - r2
            r0.label = r1
            goto L18
        L13:
            com.amazon.mp3.catalog.fragment.datasource.dao.RefinablePageViewModelDao$clearSort$2 r0 = new com.amazon.mp3.catalog.fragment.datasource.dao.RefinablePageViewModelDao$clearSort$2
            r0.<init>(r4, r6)
        L18:
            java.lang.Object r6 = r0.result
            java.lang.Object r1 = kotlin.coroutines.intrinsics.IntrinsicsKt.getCOROUTINE_SUSPENDED()
            int r2 = r0.label
            r3 = 1
            if (r2 == 0) goto L31
            if (r2 != r3) goto L29
            kotlin.ResultKt.throwOnFailure(r6)
            goto L41
        L29:
            java.lang.IllegalStateException r5 = new java.lang.IllegalStateException
            java.lang.String r6 = "call to 'resume' before 'invoke' with coroutine"
            r5.<init>(r6)
            throw r5
        L31:
            kotlin.ResultKt.throwOnFailure(r6)
            if (r5 != 0) goto L38
            r5 = 0
            goto L44
        L38:
            r0.label = r3
            java.lang.Object r6 = r5.await(r0)
            if (r6 != r1) goto L41
            return r1
        L41:
            r5 = r6
            com.amazon.mp3.library.refinement.datasource.RefinableDataSource r5 = (com.amazon.mp3.library.refinement.datasource.RefinableDataSource) r5
        L44:
            boolean r6 = r5 instanceof com.amazon.mp3.library.refinement.datasource.DatabaseSource
            java.lang.String r0 = " -> clear sort"
            if (r6 == 0) goto L64
            r6 = r5
            com.amazon.mp3.library.refinement.datasource.DatabaseSource r6 = (com.amazon.mp3.library.refinement.datasource.DatabaseSource) r6
            com.amazon.mp3.catalog.fragment.datasource.dao.RefinablePageViewModelDao$clearSort$3 r1 = new kotlin.jvm.functions.Function1<com.amazon.mp3.library.refinement.query.DatabaseQuery<R>, com.amazon.mp3.library.refinement.query.DatabaseQuery<R>>() { // from class: com.amazon.mp3.catalog.fragment.datasource.dao.RefinablePageViewModelDao.clearSort.3
                static {
                    /*
                        com.amazon.mp3.catalog.fragment.datasource.dao.RefinablePageViewModelDao$clearSort$3 r0 = new com.amazon.mp3.catalog.fragment.datasource.dao.RefinablePageViewModelDao$clearSort$3
                        r0.<init>()
                        
                        // error: 0x0005: SPUT (r0 I:com.amazon.mp3.catalog.fragment.datasource.dao.RefinablePageViewModelDao$clearSort$3) com.amazon.mp3.catalog.fragment.datasource.dao.RefinablePageViewModelDao.clearSort.3.INSTANCE com.amazon.mp3.catalog.fragment.datasource.dao.RefinablePageViewModelDao$clearSort$3
                        return
                    */
                    throw new UnsupportedOperationException("Method not decompiled: com.amazon.mp3.catalog.fragment.datasource.dao.RefinablePageViewModelDao.AnonymousClass3.<clinit>():void");
                }

                {
                    /*
                        r1 = this;
                        r0 = 1
                        r1.<init>(r0)
                        return
                    */
                    throw new UnsupportedOperationException("Method not decompiled: com.amazon.mp3.catalog.fragment.datasource.dao.RefinablePageViewModelDao.AnonymousClass3.<init>():void");
                }

                @Override // kotlin.jvm.functions.Function1
                public final com.amazon.mp3.library.refinement.query.DatabaseQuery<R> invoke(com.amazon.mp3.library.refinement.query.DatabaseQuery<R> r9) {
                    /*
                        r8 = this;
                        java.lang.String r0 = "it"
                        kotlin.jvm.internal.Intrinsics.checkNotNullParameter(r9, r0)
                        java.util.Map r5 = kotlin.collections.MapsKt.emptyMap()
                        r2 = 0
                        r3 = 0
                        r4 = 0
                        r6 = 7
                        r7 = 0
                        r1 = r9
                        com.amazon.mp3.library.refinement.query.DatabaseQuery r9 = com.amazon.mp3.library.refinement.query.DatabaseQuery.copy$default(r1, r2, r3, r4, r5, r6, r7)
                        return r9
                    */
                    throw new UnsupportedOperationException("Method not decompiled: com.amazon.mp3.catalog.fragment.datasource.dao.RefinablePageViewModelDao.AnonymousClass3.invoke(com.amazon.mp3.library.refinement.query.DatabaseQuery):com.amazon.mp3.library.refinement.query.DatabaseQuery");
                }

                @Override // kotlin.jvm.functions.Function1
                public /* bridge */ /* synthetic */ java.lang.Object invoke(java.lang.Object r1) {
                    /*
                        r0 = this;
                        com.amazon.mp3.library.refinement.query.DatabaseQuery r1 = (com.amazon.mp3.library.refinement.query.DatabaseQuery) r1
                        com.amazon.mp3.library.refinement.query.DatabaseQuery r1 = r0.invoke(r1)
                        return r1
                    */
                    throw new UnsupportedOperationException("Method not decompiled: com.amazon.mp3.catalog.fragment.datasource.dao.RefinablePageViewModelDao.AnonymousClass3.invoke(java.lang.Object):java.lang.Object");
                }
            }
            r6.updateQuery(r1)
            java.lang.String r6 = com.amazon.mp3.catalog.fragment.datasource.dao.RefinablePageViewModelDao.TAG
            java.lang.Class r5 = r5.getClass()
            java.lang.String r5 = r5.getSimpleName()
            java.lang.String r5 = kotlin.jvm.internal.Intrinsics.stringPlus(r5, r0)
            com.amazon.mp3.util.Log.debug(r6, r5)
            goto L7f
        L64:
            boolean r6 = r5 instanceof com.amazon.mp3.library.refinement.datasource.ListSource
            if (r6 == 0) goto L7f
            r6 = r5
            com.amazon.mp3.library.refinement.datasource.ListSource r6 = (com.amazon.mp3.library.refinement.datasource.ListSource) r6
            r6.clearSort()
            java.lang.String r6 = com.amazon.mp3.catalog.fragment.datasource.dao.RefinablePageViewModelDao.TAG
            java.lang.Class r5 = r5.getClass()
            java.lang.String r5 = r5.getSimpleName()
            java.lang.String r5 = kotlin.jvm.internal.Intrinsics.stringPlus(r5, r0)
            com.amazon.mp3.util.Log.debug(r6, r5)
        L7f:
            kotlin.Unit r5 = kotlin.Unit.INSTANCE
            return r5
        */
        throw new UnsupportedOperationException("Method not decompiled: com.amazon.mp3.catalog.fragment.datasource.dao.RefinablePageViewModelDao.clearSort(kotlinx.coroutines.Deferred, kotlin.coroutines.Continuation):java.lang.Object");
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Removed duplicated region for block: B:7:0x0013  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final java.lang.Object reset(kotlin.coroutines.Continuation<? super kotlin.Unit> r6) {
        /*
            r5 = this;
            boolean r0 = r6 instanceof com.amazon.mp3.catalog.fragment.datasource.dao.RefinablePageViewModelDao.C03221
            if (r0 == 0) goto L13
            r0 = r6
            com.amazon.mp3.catalog.fragment.datasource.dao.RefinablePageViewModelDao$reset$1 r0 = (com.amazon.mp3.catalog.fragment.datasource.dao.RefinablePageViewModelDao.C03221) r0
            int r1 = r0.label
            r2 = -2147483648(0xffffffff80000000, float:-0.0)
            r3 = r1 & r2
            if (r3 == 0) goto L13
            int r1 = r1 - r2
            r0.label = r1
            goto L18
        L13:
            com.amazon.mp3.catalog.fragment.datasource.dao.RefinablePageViewModelDao$reset$1 r0 = new com.amazon.mp3.catalog.fragment.datasource.dao.RefinablePageViewModelDao$reset$1
            r0.<init>(r5, r6)
        L18:
            java.lang.Object r6 = r0.result
            java.lang.Object r1 = kotlin.coroutines.intrinsics.IntrinsicsKt.getCOROUTINE_SUSPENDED()
            int r2 = r0.label
            r3 = 2
            r4 = 1
            if (r2 == 0) goto L3c
            if (r2 == r4) goto L34
            if (r2 != r3) goto L2c
            kotlin.ResultKt.throwOnFailure(r6)
            goto L5f
        L2c:
            java.lang.IllegalStateException r6 = new java.lang.IllegalStateException
            java.lang.String r0 = "call to 'resume' before 'invoke' with coroutine"
            r6.<init>(r0)
            throw r6
        L34:
            java.lang.Object r2 = r0.L$0
            com.amazon.mp3.catalog.fragment.datasource.dao.RefinablePageViewModelDao r2 = (com.amazon.mp3.catalog.fragment.datasource.dao.RefinablePageViewModelDao) r2
            kotlin.ResultKt.throwOnFailure(r6)
            goto L4f
        L3c:
            kotlin.ResultKt.throwOnFailure(r6)
            kotlinx.coroutines.Deferred r6 = r5.getOnlineDataSource()
            r0.L$0 = r5
            r0.label = r4
            java.lang.Object r6 = r5.reset(r6, r0)
            if (r6 != r1) goto L4e
            return r1
        L4e:
            r2 = r5
        L4f:
            kotlinx.coroutines.Deferred r6 = r2.getOfflineDataSource()
            r4 = 0
            r0.L$0 = r4
            r0.label = r3
            java.lang.Object r6 = r2.reset(r6, r0)
            if (r6 != r1) goto L5f
            return r1
        L5f:
            kotlin.Unit r6 = kotlin.Unit.INSTANCE
            return r6
        */
        throw new UnsupportedOperationException("Method not decompiled: com.amazon.mp3.catalog.fragment.datasource.dao.RefinablePageViewModelDao.reset(kotlin.coroutines.Continuation):java.lang.Object");
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Removed duplicated region for block: B:23:0x004a  */
    /* JADX WARN: Removed duplicated region for block: B:24:0x0061  */
    /* JADX WARN: Removed duplicated region for block: B:7:0x0013  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final java.lang.Object reset(kotlinx.coroutines.Deferred<? extends D> r5, kotlin.coroutines.Continuation<? super kotlin.Unit> r6) {
        /*
            r4 = this;
            boolean r0 = r6 instanceof com.amazon.mp3.catalog.fragment.datasource.dao.RefinablePageViewModelDao.C03232
            if (r0 == 0) goto L13
            r0 = r6
            com.amazon.mp3.catalog.fragment.datasource.dao.RefinablePageViewModelDao$reset$2 r0 = (com.amazon.mp3.catalog.fragment.datasource.dao.RefinablePageViewModelDao.C03232) r0
            int r1 = r0.label
            r2 = -2147483648(0xffffffff80000000, float:-0.0)
            r3 = r1 & r2
            if (r3 == 0) goto L13
            int r1 = r1 - r2
            r0.label = r1
            goto L18
        L13:
            com.amazon.mp3.catalog.fragment.datasource.dao.RefinablePageViewModelDao$reset$2 r0 = new com.amazon.mp3.catalog.fragment.datasource.dao.RefinablePageViewModelDao$reset$2
            r0.<init>(r4, r6)
        L18:
            java.lang.Object r6 = r0.result
            java.lang.Object r1 = kotlin.coroutines.intrinsics.IntrinsicsKt.getCOROUTINE_SUSPENDED()
            int r2 = r0.label
            r3 = 1
            if (r2 == 0) goto L31
            if (r2 != r3) goto L29
            kotlin.ResultKt.throwOnFailure(r6)
            goto L41
        L29:
            java.lang.IllegalStateException r5 = new java.lang.IllegalStateException
            java.lang.String r6 = "call to 'resume' before 'invoke' with coroutine"
            r5.<init>(r6)
            throw r5
        L31:
            kotlin.ResultKt.throwOnFailure(r6)
            if (r5 != 0) goto L38
            r5 = 0
            goto L44
        L38:
            r0.label = r3
            java.lang.Object r6 = r5.await(r0)
            if (r6 != r1) goto L41
            return r1
        L41:
            r5 = r6
            com.amazon.mp3.library.refinement.datasource.RefinableDataSource r5 = (com.amazon.mp3.library.refinement.datasource.RefinableDataSource) r5
        L44:
            boolean r6 = r5 instanceof com.amazon.mp3.library.refinement.datasource.DatabaseSource
            java.lang.String r0 = " -> reset"
            if (r6 == 0) goto L61
            java.lang.String r6 = com.amazon.mp3.catalog.fragment.datasource.dao.RefinablePageViewModelDao.TAG
            java.lang.Class r1 = r5.getClass()
            java.lang.String r1 = r1.getSimpleName()
            java.lang.String r0 = kotlin.jvm.internal.Intrinsics.stringPlus(r1, r0)
            com.amazon.mp3.util.Log.debug(r6, r0)
            com.amazon.mp3.library.refinement.datasource.DatabaseSource r5 = (com.amazon.mp3.library.refinement.datasource.DatabaseSource) r5
            r5.resetQuery()
            goto L81
        L61:
            boolean r6 = r5 instanceof com.amazon.mp3.library.refinement.datasource.ListSource
            if (r6 == 0) goto L81
            r6 = r5
            com.amazon.mp3.library.refinement.datasource.ListSource r6 = (com.amazon.mp3.library.refinement.datasource.ListSource) r6
            r6.clearFilters()
            java.lang.String r6 = com.amazon.mp3.catalog.fragment.datasource.dao.RefinablePageViewModelDao.TAG
            java.lang.Class r1 = r5.getClass()
            java.lang.String r1 = r1.getSimpleName()
            java.lang.String r0 = kotlin.jvm.internal.Intrinsics.stringPlus(r1, r0)
            com.amazon.mp3.util.Log.debug(r6, r0)
            com.amazon.mp3.library.refinement.datasource.ListSource r5 = (com.amazon.mp3.library.refinement.datasource.ListSource) r5
            r5.clearSort()
        L81:
            kotlin.Unit r5 = kotlin.Unit.INSTANCE
            return r5
        */
        throw new UnsupportedOperationException("Method not decompiled: com.amazon.mp3.catalog.fragment.datasource.dao.RefinablePageViewModelDao.reset(kotlinx.coroutines.Deferred, kotlin.coroutines.Continuation):java.lang.Object");
    }

    @Override // com.amazon.mp3.catalog.fragment.datasource.dao.PageViewModelDao
    public Single<PageGridViewModel> fetchViewModelItems(final int pageIndex, final int pageSize, final FetchType fetchType, String nextPageToken) {
        Intrinsics.checkNotNullParameter(fetchType, "fetchType");
        Single singleCreate = Single.create(new SingleOnSubscribe() { // from class: com.amazon.mp3.catalog.fragment.datasource.dao.RefinablePageViewModelDao$$ExternalSyntheticLambda0
            @Override // io.reactivex.SingleOnSubscribe
            public final void subscribe(SingleEmitter singleEmitter) {
                RefinablePageViewModelDao.m569fetchViewModelItems$lambda6(this.f$0, pageSize, pageIndex, fetchType, singleEmitter);
            }
        });
        Intrinsics.checkNotNullExpressionValue(singleCreate, "create {\n               …      }\n                }");
        Single<PageGridViewModel> singleSubscribeOn = singleCreate.subscribeOn(Schedulers.io());
        Intrinsics.checkNotNullExpressionValue(singleSubscribeOn, "single.subscribeOn(Schedulers.io())");
        return singleSubscribeOn;
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: fetchViewModelItems$lambda-6, reason: not valid java name */
    public static final void m569fetchViewModelItems$lambda6(RefinablePageViewModelDao this$0, int i, int i2, FetchType fetchType, SingleEmitter it) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        Intrinsics.checkNotNullParameter(fetchType, "$fetchType");
        Intrinsics.checkNotNullParameter(it, "it");
        try {
            BuildersKt__BuildersKt.runBlocking$default(null, new RefinablePageViewModelDao$fetchViewModelItems$single$1$1(this$0, i, i2, fetchType, it, null), 1, null);
        } catch (Exception e) {
            if (it.isDisposed()) {
                return;
            }
            it.onError(e);
        }
    }

    private static final <R extends Refinable, D extends RefinableDataSource<R, ?>> List<R> createResponseFromDatabase$handlePaginatedDatabaseResponse(D d, int i, int i2, boolean z, RefinablePageViewModelDao<R, D> refinablePageViewModelDao, FetchType fetchType) {
        Refinable placeHolder;
        List mutableList = CollectionsKt___CollectionsKt.toMutableList((Collection) ((DatabaseSourcePaged) d).query(i, i2));
        int iCount = d.count();
        if (z && refinablePageViewModelDao.shouldAddPlaceholders(fetchType) && refinablePageViewModelDao.getPlaceHolder() != null && i == 0 && iCount > mutableList.size() && (placeHolder = refinablePageViewModelDao.getPlaceHolder()) != null) {
            int size = iCount - mutableList.size();
            ArrayList arrayList = new ArrayList(size);
            int i3 = 0;
            while (i3 < size) {
                i3++;
                arrayList.add(placeHolder);
            }
            CollectionsKt__MutableCollectionsKt.addAll(mutableList, arrayList);
        }
        return CollectionsKt___CollectionsKt.toList(mutableList);
    }

    private static final <R extends Refinable, D extends RefinableDataSource<R, ?>> List<R> createResponseFromDatabase$handleNonPaginatedDatabaseResponse(D d) {
        return ((DatabaseSource) d).query();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final List<R> createResponseFromDatabase(boolean usesPagination, D dataSource, int pageIndex, int pageSize, FetchType fetchType) {
        return usesPagination ? createResponseFromDatabase$handlePaginatedDatabaseResponse(dataSource, pageIndex, pageSize, usesPagination, this, fetchType) : createResponseFromDatabase$handleNonPaginatedDatabaseResponse(dataSource);
    }

    private final boolean shouldAddPlaceholders(FetchType fetchType) {
        return fetchType == FetchType.INIT || fetchType == FetchType.FILTER || fetchType == FetchType.SORT;
    }

    protected final void startTrackingResponseLatency() {
        PageLoadLatencyCode pageLoadLatencyCode = getPageLoadLatencyCode();
        if (pageLoadLatencyCode == null) {
            return;
        }
        LatencyTracker.INSTANCE.track(new LatencyEventDescriptor(LatencyTrackingLeg.RESPONSE, pageLoadLatencyCode));
    }

    protected final void startTrackingProcessingLatency() {
        PageLoadLatencyCode pageLoadLatencyCode = getPageLoadLatencyCode();
        if (pageLoadLatencyCode == null) {
            return;
        }
        LatencyTracker.INSTANCE.track(new LatencyEventDescriptor(LatencyTrackingLeg.PROCESSING, pageLoadLatencyCode));
    }

    protected final Map<String, SortOrder> getDefaultSortColumn(PageType pageType) {
        Intrinsics.checkNotNullParameter(pageType, "pageType");
        LinkedHashMap linkedHashMap = new LinkedHashMap();
        DefaultRefinementDataProvider.DefaultRefinementData defaultRefinementData = DefaultRefinementDataProvider.INSTANCE.getDefaultRefinementData(pageType);
        if (defaultRefinementData != null) {
        }
        return linkedHashMap;
    }
}
