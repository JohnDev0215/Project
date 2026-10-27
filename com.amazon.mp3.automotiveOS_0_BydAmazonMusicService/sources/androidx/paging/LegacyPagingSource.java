package androidx.paging;

import androidx.annotation.RestrictTo;
import androidx.paging.DataSource;
import androidx.paging.PagingSource;
import java.util.List;
import kotlin.Function;
import kotlin.Metadata;
import kotlin.NoWhenBranchMatchedException;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.collections.CollectionsKt__CollectionsKt;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt__IntrinsicsKt;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.FunctionAdapter;
import kotlin.jvm.internal.FunctionReferenceImpl;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.BuildersKt;
import kotlinx.coroutines.CoroutineDispatcher;
import kotlinx.coroutines.CoroutineScope;

/* JADX INFO: compiled from: LegacyPagingSource.kt */
/* JADX INFO: loaded from: classes2.dex */
@Metadata(d1 = {"\u0000N\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0002\b\u0002\b\u0007\u0018\u0000 \u001e*\b\b\u0000\u0010\u0001*\u00020\u0002*\b\b\u0001\u0010\u0003*\u00020\u00022\u000e\u0012\u0004\u0012\u0002H\u0001\u0012\u0004\u0012\u0002H\u00030\u0004:\u0001\u001eB!\u0012\u0006\u0010\u0005\u001a\u00020\u0006\u0012\u0012\u0010\u0007\u001a\u000e\u0012\u0004\u0012\u00028\u0000\u0012\u0004\u0012\u00028\u00010\b¢\u0006\u0002\u0010\tJ#\u0010\u0012\u001a\u0004\u0018\u00018\u00002\u0012\u0010\u0013\u001a\u000e\u0012\u0004\u0012\u00028\u0000\u0012\u0004\u0012\u00028\u00010\u0014H\u0016¢\u0006\u0002\u0010\u0015J\u0016\u0010\u0016\u001a\u00020\u00112\f\u0010\u0017\u001a\b\u0012\u0004\u0012\u00028\u00000\u0018H\u0002J+\u0010\u0019\u001a\u000e\u0012\u0004\u0012\u00028\u0000\u0012\u0004\u0012\u00028\u00010\u001a2\f\u0010\u0017\u001a\b\u0012\u0004\u0012\u00028\u00000\u0018H\u0096@ø\u0001\u0000¢\u0006\u0002\u0010\u001bJ\u0010\u0010\u001c\u001a\u00020\u001d2\u0006\u0010\u0010\u001a\u00020\u0011H\u0007R \u0010\u0007\u001a\u000e\u0012\u0004\u0012\u00028\u0000\u0012\u0004\u0012\u00028\u00010\bX\u0080\u0004¢\u0006\b\n\u0000\u001a\u0004\b\n\u0010\u000bR\u000e\u0010\u0005\u001a\u00020\u0006X\u0082\u0004¢\u0006\u0002\n\u0000R\u0014\u0010\f\u001a\u00020\r8VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b\u000e\u0010\u000fR\u000e\u0010\u0010\u001a\u00020\u0011X\u0082\u000e¢\u0006\u0002\n\u0000\u0082\u0002\u0004\n\u0002\b\u0019¨\u0006\u001f"}, d2 = {"Landroidx/paging/LegacyPagingSource;", "Key", "", "Value", "Landroidx/paging/PagingSource;", "fetchDispatcher", "Lkotlinx/coroutines/CoroutineDispatcher;", "dataSource", "Landroidx/paging/DataSource;", "(Lkotlinx/coroutines/CoroutineDispatcher;Landroidx/paging/DataSource;)V", "getDataSource$paging_common", "()Landroidx/paging/DataSource;", "jumpingSupported", "", "getJumpingSupported", "()Z", "pageSize", "", "getRefreshKey", "state", "Landroidx/paging/PagingState;", "(Landroidx/paging/PagingState;)Ljava/lang/Object;", "guessPageSize", "params", "Landroidx/paging/PagingSource$LoadParams;", "load", "Landroidx/paging/PagingSource$LoadResult;", "(Landroidx/paging/PagingSource$LoadParams;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "setPageSize", "", "Companion", "paging-common"}, k = 1, mv = {1, 5, 1}, xi = 48)
@RestrictTo({RestrictTo.Scope.LIBRARY_GROUP})
public final class LegacyPagingSource<Key, Value> extends PagingSource<Key, Value> {
    private static final Companion Companion = new Companion(null);

    @Deprecated
    private static final int PAGE_SIZE_NOT_SET = Integer.MIN_VALUE;
    private final DataSource<Key, Value> dataSource;
    private final CoroutineDispatcher fetchDispatcher;
    private int pageSize;

    /* JADX INFO: compiled from: LegacyPagingSource.kt */
    @Metadata(k = 3, mv = {1, 5, 1}, xi = 48)
    public /* synthetic */ class WhenMappings {
        public static final /* synthetic */ int[] $EnumSwitchMapping$0;

        static {
            int[] iArr = new int[DataSource.KeyType.values().length];
            iArr[DataSource.KeyType.POSITIONAL.ordinal()] = 1;
            iArr[DataSource.KeyType.PAGE_KEYED.ordinal()] = 2;
            iArr[DataSource.KeyType.ITEM_KEYED.ordinal()] = 3;
            $EnumSwitchMapping$0 = iArr;
        }
    }

    public final DataSource<Key, Value> getDataSource$paging_common() {
        return this.dataSource;
    }

    public LegacyPagingSource(CoroutineDispatcher fetchDispatcher, DataSource<Key, Value> dataSource) {
        Intrinsics.checkNotNullParameter(fetchDispatcher, "fetchDispatcher");
        Intrinsics.checkNotNullParameter(dataSource, "dataSource");
        this.fetchDispatcher = fetchDispatcher;
        this.dataSource = dataSource;
        this.pageSize = Integer.MIN_VALUE;
        dataSource.addInvalidatedCallback(new AnonymousClass1(this));
        registerInvalidatedCallback(new Function0<Unit>(this) { // from class: androidx.paging.LegacyPagingSource.2
            final /* synthetic */ LegacyPagingSource<Key, Value> this$0;

            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super(0);
                this.this$0 = this;
            }

            /* JADX INFO: renamed from: androidx.paging.LegacyPagingSource$2$1, reason: invalid class name */
            /* JADX INFO: compiled from: LegacyPagingSource.kt */
            @Metadata(k = 3, mv = {1, 5, 1}, xi = 48)
            /* synthetic */ class AnonymousClass1 implements DataSource.InvalidatedCallback, FunctionAdapter {
                final /* synthetic */ LegacyPagingSource<Key, Value> $tmp0;

                AnonymousClass1(LegacyPagingSource<Key, Value> legacyPagingSource) {
                    this.$tmp0 = legacyPagingSource;
                }

                public final boolean equals(Object obj) {
                    if ((obj instanceof DataSource.InvalidatedCallback) && (obj instanceof FunctionAdapter)) {
                        return Intrinsics.areEqual(getFunctionDelegate(), ((FunctionAdapter) obj).getFunctionDelegate());
                    }
                    return false;
                }

                @Override // kotlin.jvm.internal.FunctionAdapter
                public final Function<?> getFunctionDelegate() {
                    return new FunctionReferenceImpl(0, this.$tmp0, LegacyPagingSource.class, "invalidate", "invalidate()V", 0);
                }

                public final int hashCode() {
                    return getFunctionDelegate().hashCode();
                }

                @Override // androidx.paging.DataSource.InvalidatedCallback
                public final void onInvalidated() {
                    this.$tmp0.invalidate();
                }
            }

            @Override // kotlin.jvm.functions.Function0
            public /* bridge */ /* synthetic */ Unit invoke() {
                invoke2();
                return Unit.INSTANCE;
            }

            /* JADX INFO: renamed from: invoke, reason: avoid collision after fix types in other method */
            public final void invoke2() {
                this.this$0.getDataSource$paging_common().removeInvalidatedCallback(new AnonymousClass1(this.this$0));
                this.this$0.getDataSource$paging_common().invalidate();
            }
        });
    }

    /* JADX INFO: renamed from: androidx.paging.LegacyPagingSource$1, reason: invalid class name */
    /* JADX INFO: compiled from: LegacyPagingSource.kt */
    @Metadata(k = 3, mv = {1, 5, 1}, xi = 48)
    /* synthetic */ class AnonymousClass1 implements DataSource.InvalidatedCallback, FunctionAdapter {
        final /* synthetic */ LegacyPagingSource<Key, Value> $tmp0;

        AnonymousClass1(LegacyPagingSource<Key, Value> legacyPagingSource) {
            this.$tmp0 = legacyPagingSource;
        }

        public final boolean equals(Object obj) {
            if ((obj instanceof DataSource.InvalidatedCallback) && (obj instanceof FunctionAdapter)) {
                return Intrinsics.areEqual(getFunctionDelegate(), ((FunctionAdapter) obj).getFunctionDelegate());
            }
            return false;
        }

        @Override // kotlin.jvm.internal.FunctionAdapter
        public final Function<?> getFunctionDelegate() {
            return new FunctionReferenceImpl(0, this.$tmp0, LegacyPagingSource.class, "invalidate", "invalidate()V", 0);
        }

        public final int hashCode() {
            return getFunctionDelegate().hashCode();
        }

        @Override // androidx.paging.DataSource.InvalidatedCallback
        public final void onInvalidated() {
            this.$tmp0.invalidate();
        }
    }

    @RestrictTo({RestrictTo.Scope.LIBRARY_GROUP})
    public final void setPageSize(int pageSize) {
        int i = this.pageSize;
        if (!(i == Integer.MIN_VALUE || pageSize == i)) {
            throw new IllegalStateException(("Page size is already set to " + this.pageSize + '.').toString());
        }
        this.pageSize = pageSize;
    }

    private final int guessPageSize(PagingSource.LoadParams<Key> params) {
        if ((params instanceof PagingSource.LoadParams.Refresh) && params.getLoadSize() % 3 == 0) {
            return params.getLoadSize() / 3;
        }
        return params.getLoadSize();
    }

    @Override // androidx.paging.PagingSource
    public Object load(PagingSource.LoadParams<Key> loadParams, Continuation<? super PagingSource.LoadResult<Key, Value>> continuation) {
        LoadType loadType;
        if (loadParams instanceof PagingSource.LoadParams.Refresh) {
            loadType = LoadType.REFRESH;
        } else if (loadParams instanceof PagingSource.LoadParams.Append) {
            loadType = LoadType.APPEND;
        } else {
            if (!(loadParams instanceof PagingSource.LoadParams.Prepend)) {
                throw new NoWhenBranchMatchedException();
            }
            loadType = LoadType.PREPEND;
        }
        LoadType loadType2 = loadType;
        if (this.pageSize == Integer.MIN_VALUE) {
            System.out.println((Object) "WARNING: pageSize on the LegacyPagingSource is not set.\nWhen using legacy DataSource / DataSourceFactory with Paging3, page size\nshould've been set by the paging library but it is not set yet.\n\nIf you are seeing this message in tests where you are testing DataSource\nin isolation (without a Pager), it is expected and page size will be estimated\nbased on parameters.\n\nIf you are seeing this message despite using a Pager, please file a bug:\nhttps://issuetracker.google.com/issues/new?component=413106");
            this.pageSize = guessPageSize(loadParams);
        }
        return BuildersKt.withContext(this.fetchDispatcher, new C02272(this, new DataSource.Params(loadType2, loadParams.getKey(), loadParams.getLoadSize(), loadParams.getPlaceholdersEnabled(), this.pageSize), loadParams, null), continuation);
    }

    /* JADX INFO: renamed from: androidx.paging.LegacyPagingSource$load$2, reason: invalid class name and case insensitive filesystem */
    /* JADX INFO: compiled from: LegacyPagingSource.kt */
    @Metadata(bv = {}, d1 = {"\u0000\u0012\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\u0010\u0005\u001a\u000e\u0012\u0004\u0012\u00028\u0000\u0012\u0004\u0012\u00028\u00010\u0004\"\b\b\u0000\u0010\u0001*\u00020\u0000\"\b\b\u0001\u0010\u0002*\u00020\u0000*\u00020\u0003H\u008a@"}, d2 = {"", "Key", "Value", "Lkotlinx/coroutines/CoroutineScope;", "Landroidx/paging/PagingSource$LoadResult$Page;", "<anonymous>"}, k = 3, mv = {1, 5, 1})
    @DebugMetadata(c = "androidx.paging.LegacyPagingSource$load$2", f = "LegacyPagingSource.kt", i = {}, l = {111}, m = "invokeSuspend", n = {}, s = {})
    static final class C02272 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super PagingSource.LoadResult.Page<Key, Value>>, Object> {
        final /* synthetic */ DataSource.Params<Key> $dataSourceParams;
        final /* synthetic */ PagingSource.LoadParams<Key> $params;
        int label;
        final /* synthetic */ LegacyPagingSource<Key, Value> this$0;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        C02272(LegacyPagingSource<Key, Value> legacyPagingSource, DataSource.Params<Key> params, PagingSource.LoadParams<Key> loadParams, Continuation<? super C02272> continuation) {
            super(2, continuation);
            this.this$0 = legacyPagingSource;
            this.$dataSourceParams = params;
            this.$params = loadParams;
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
            return new C02272(this.this$0, this.$dataSourceParams, this.$params, continuation);
        }

        @Override // kotlin.jvm.functions.Function2
        /* JADX INFO: renamed from: invoke, reason: merged with bridge method [inline-methods] */
        public final Object mo4invoke(CoroutineScope coroutineScope, Continuation<? super PagingSource.LoadResult.Page<Key, Value>> continuation) {
            return ((C02272) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object obj) {
            Object coroutine_suspended = IntrinsicsKt__IntrinsicsKt.getCOROUTINE_SUSPENDED();
            int i = this.label;
            if (i == 0) {
                ResultKt.throwOnFailure(obj);
                DataSource<Key, Value> dataSource$paging_common = this.this$0.getDataSource$paging_common();
                DataSource.Params<Key> params = this.$dataSourceParams;
                this.label = 1;
                obj = dataSource$paging_common.load$paging_common(params, this);
                if (obj == coroutine_suspended) {
                    return coroutine_suspended;
                }
            } else {
                if (i != 1) {
                    throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                }
                ResultKt.throwOnFailure(obj);
            }
            PagingSource.LoadParams<Key> loadParams = this.$params;
            DataSource.BaseResult baseResult = (DataSource.BaseResult) obj;
            List<Value> list = baseResult.data;
            return new PagingSource.LoadResult.Page(list, (list.isEmpty() && (loadParams instanceof PagingSource.LoadParams.Prepend)) ? null : baseResult.getPrevKey(), (baseResult.data.isEmpty() && (loadParams instanceof PagingSource.LoadParams.Append)) ? null : baseResult.getNextKey(), baseResult.getItemsBefore(), baseResult.getItemsAfter());
        }
    }

    @Override // androidx.paging.PagingSource
    public Key getRefreshKey(PagingState<Key, Value> state) {
        Key prevKey;
        Value valueClosestItemToPosition;
        Intrinsics.checkNotNullParameter(state, "state");
        int i = WhenMappings.$EnumSwitchMapping$0[this.dataSource.getType().ordinal()];
        if (i != 1) {
            if (i == 2) {
                return null;
            }
            if (i != 3) {
                throw new NoWhenBranchMatchedException();
            }
            Integer anchorPosition = state.getAnchorPosition();
            if (anchorPosition == null || (valueClosestItemToPosition = state.closestItemToPosition(anchorPosition.intValue())) == null) {
                return null;
            }
            return getDataSource$paging_common().getKeyInternal$paging_common(valueClosestItemToPosition);
        }
        Integer anchorPosition2 = state.getAnchorPosition();
        if (anchorPosition2 == null) {
            return null;
        }
        int iIntValue = anchorPosition2.intValue();
        int size = iIntValue - ((PagingState) state).leadingPlaceholderCount;
        for (int i2 = 0; i2 < CollectionsKt__CollectionsKt.getLastIndex(state.getPages()) && size > CollectionsKt__CollectionsKt.getLastIndex(state.getPages().get(i2).getData()); i2++) {
            size -= state.getPages().get(i2).getData().size();
        }
        PagingSource.LoadResult.Page<Key, Value> pageClosestPageToPosition = state.closestPageToPosition(iIntValue);
        if (pageClosestPageToPosition == null || (prevKey = pageClosestPageToPosition.getPrevKey()) == null) {
            prevKey = (Key) 0;
        }
        return (Key) Integer.valueOf(prevKey.intValue() + size);
    }

    @Override // androidx.paging.PagingSource
    public boolean getJumpingSupported() {
        return this.dataSource.getType() == DataSource.KeyType.POSITIONAL;
    }

    /* JADX INFO: compiled from: LegacyPagingSource.kt */
    @Metadata(d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\b\n\u0000\b\u0082\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002R\u000e\u0010\u0003\u001a\u00020\u0004X\u0082T¢\u0006\u0002\n\u0000¨\u0006\u0005"}, d2 = {"Landroidx/paging/LegacyPagingSource$Companion;", "", "()V", "PAGE_SIZE_NOT_SET", "", "paging-common"}, k = 1, mv = {1, 5, 1}, xi = 48)
    private static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }
    }
}
