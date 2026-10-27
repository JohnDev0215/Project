package androidx.paging;

import androidx.annotation.RestrictTo;
import androidx.annotation.VisibleForTesting;
import androidx.annotation.WorkerThread;
import androidx.arch.core.util.Function;
import androidx.paging.DataSource;
import androidx.paging.PositionalDataSource;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import kotlin.Deprecated;
import kotlin.Metadata;
import kotlin.ReplaceWith;
import kotlin.Result;
import kotlin.collections.CollectionsKt__IterablesKt;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt__IntrinsicsJvmKt;
import kotlin.coroutines.intrinsics.IntrinsicsKt__IntrinsicsKt;
import kotlin.coroutines.jvm.internal.DebugProbesKt;
import kotlin.jvm.JvmField;
import kotlin.jvm.JvmStatic;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.CancellableContinuationImpl;

/* JADX INFO: compiled from: PositionalDataSource.kt */
/* JADX INFO: loaded from: classes2.dex */
@Metadata(d1 = {"\u0000`\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0000\n\u0002\u0018\u0002\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\b\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010 \n\u0002\b\u0006\b'\u0018\u0000 '*\b\b\u0000\u0010\u0001*\u00020\u00022\u000e\u0012\u0004\u0012\u00020\u0004\u0012\u0004\u0012\u0002H\u00010\u0003:\u0005'()*+B\u0005¢\u0006\u0002\u0010\u0005J\u0017\u0010\u000b\u001a\u00020\u00042\u0006\u0010\f\u001a\u00028\u0000H\u0000¢\u0006\u0004\b\r\u0010\u000eJ'\u0010\u000f\u001a\b\u0012\u0004\u0012\u00028\u00000\u00102\f\u0010\u0011\u001a\b\u0012\u0004\u0012\u00020\u00040\u0012H\u0080@ø\u0001\u0000¢\u0006\u0004\b\u0013\u0010\u0014J!\u0010\u0015\u001a\b\u0012\u0004\u0012\u00028\u00000\u00102\u0006\u0010\u0011\u001a\u00020\u0016H\u0081@ø\u0001\u0000¢\u0006\u0004\b\u0017\u0010\u0018J\u001e\u0010\u0015\u001a\u00020\u00192\u0006\u0010\u0011\u001a\u00020\u00162\f\u0010\u001a\u001a\b\u0012\u0004\u0012\u00028\u00000\u001bH'J\u001f\u0010\u001c\u001a\b\u0012\u0004\u0012\u00028\u00000\u00102\u0006\u0010\u0011\u001a\u00020\u001dH\u0082@ø\u0001\u0000¢\u0006\u0002\u0010\u001eJ\u001e\u0010\u001c\u001a\u00020\u00192\u0006\u0010\u0011\u001a\u00020\u001d2\f\u0010\u001a\u001a\b\u0012\u0004\u0012\u00028\u00000\u001fH'J*\u0010 \u001a\b\u0012\u0004\u0012\u0002H!0\u0000\"\b\b\u0001\u0010!*\u00020\u00022\u0012\u0010\"\u001a\u000e\u0012\u0004\u0012\u00028\u0000\u0012\u0004\u0012\u0002H!0#J*\u0010 \u001a\b\u0012\u0004\u0012\u0002H!0\u0000\"\b\b\u0001\u0010!*\u00020\u00022\u0012\u0010\"\u001a\u000e\u0012\u0004\u0012\u00028\u0000\u0012\u0004\u0012\u0002H!0$J6\u0010%\u001a\b\u0012\u0004\u0012\u0002H!0\u0000\"\b\b\u0001\u0010!*\u00020\u00022\u001e\u0010\"\u001a\u001a\u0012\n\u0012\b\u0012\u0004\u0012\u00028\u00000&\u0012\n\u0012\b\u0012\u0004\u0012\u0002H!0&0#J6\u0010%\u001a\b\u0012\u0004\u0012\u0002H!0\u0000\"\b\b\u0001\u0010!*\u00020\u00022\u001e\u0010\"\u001a\u001a\u0012\n\u0012\b\u0012\u0004\u0012\u00028\u00000&\u0012\n\u0012\b\u0012\u0004\u0012\u0002H!0&0$R\u001a\u0010\u0006\u001a\u00020\u0007X\u0090D¢\u0006\u000e\n\u0000\u0012\u0004\b\b\u0010\u0005\u001a\u0004\b\t\u0010\n\u0082\u0002\u0004\n\u0002\b\u0019¨\u0006,"}, d2 = {"Landroidx/paging/PositionalDataSource;", "T", "", "Landroidx/paging/DataSource;", "", "()V", "isContiguous", "", "isContiguous$paging_common$annotations", "isContiguous$paging_common", "()Z", "getKeyInternal", "item", "getKeyInternal$paging_common", "(Ljava/lang/Object;)Ljava/lang/Integer;", "load", "Landroidx/paging/DataSource$BaseResult;", "params", "Landroidx/paging/DataSource$Params;", "load$paging_common", "(Landroidx/paging/DataSource$Params;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "loadInitial", "Landroidx/paging/PositionalDataSource$LoadInitialParams;", "loadInitial$paging_common", "(Landroidx/paging/PositionalDataSource$LoadInitialParams;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "", "callback", "Landroidx/paging/PositionalDataSource$LoadInitialCallback;", "loadRange", "Landroidx/paging/PositionalDataSource$LoadRangeParams;", "(Landroidx/paging/PositionalDataSource$LoadRangeParams;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "Landroidx/paging/PositionalDataSource$LoadRangeCallback;", "map", "V", "function", "Lkotlin/Function1;", "Landroidx/arch/core/util/Function;", "mapByPage", "", "Companion", "LoadInitialCallback", "LoadInitialParams", "LoadRangeCallback", "LoadRangeParams", "paging-common"}, k = 1, mv = {1, 5, 1}, xi = 48)
@Deprecated(message = "PositionalDataSource is deprecated and has been replaced by PagingSource", replaceWith = @ReplaceWith(expression = "PagingSource<Int, T>", imports = {"androidx.paging.PagingSource"}))
public abstract class PositionalDataSource<T> extends DataSource<Integer, T> {

    /* JADX INFO: renamed from: Companion, reason: from kotlin metadata */
    public static final Companion INSTANCE = new Companion(null);
    private final boolean isContiguous;

    /* JADX INFO: compiled from: PositionalDataSource.kt */
    @Metadata(d1 = {"\u0000\"\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010 \n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\b&\u0018\u0000*\u0004\b\u0001\u0010\u00012\u00020\u0002B\u0005¢\u0006\u0002\u0010\u0003J\u001e\u0010\u0004\u001a\u00020\u00052\f\u0010\u0006\u001a\b\u0012\u0004\u0012\u00028\u00010\u00072\u0006\u0010\b\u001a\u00020\tH&J&\u0010\u0004\u001a\u00020\u00052\f\u0010\u0006\u001a\b\u0012\u0004\u0012\u00028\u00010\u00072\u0006\u0010\b\u001a\u00020\t2\u0006\u0010\n\u001a\u00020\tH&¨\u0006\u000b"}, d2 = {"Landroidx/paging/PositionalDataSource$LoadInitialCallback;", "T", "", "()V", "onResult", "", "data", "", "position", "", "totalCount", "paging-common"}, k = 1, mv = {1, 5, 1}, xi = 48)
    public static abstract class LoadInitialCallback<T> {
        public abstract void onResult(List<? extends T> data, int position);

        public abstract void onResult(List<? extends T> data, int position, int totalCount);
    }

    /* JADX INFO: compiled from: PositionalDataSource.kt */
    @Metadata(d1 = {"\u0000\u001a\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010 \n\u0000\b&\u0018\u0000*\u0004\b\u0001\u0010\u00012\u00020\u0002B\u0005¢\u0006\u0002\u0010\u0003J\u0016\u0010\u0004\u001a\u00020\u00052\f\u0010\u0006\u001a\b\u0012\u0004\u0012\u00028\u00010\u0007H&¨\u0006\b"}, d2 = {"Landroidx/paging/PositionalDataSource$LoadRangeCallback;", "T", "", "()V", "onResult", "", "data", "", "paging-common"}, k = 1, mv = {1, 5, 1}, xi = 48)
    public static abstract class LoadRangeCallback<T> {
        public abstract void onResult(List<? extends T> data);
    }

    @JvmStatic
    public static final int computeInitialLoadPosition(LoadInitialParams loadInitialParams, int i) {
        return INSTANCE.computeInitialLoadPosition(loadInitialParams, i);
    }

    @JvmStatic
    public static final int computeInitialLoadSize(LoadInitialParams loadInitialParams, int i, int i2) {
        return INSTANCE.computeInitialLoadSize(loadInitialParams, i, i2);
    }

    public static /* synthetic */ void isContiguous$paging_common$annotations() {
    }

    @WorkerThread
    public abstract void loadInitial(LoadInitialParams params, LoadInitialCallback<T> callback);

    @WorkerThread
    public abstract void loadRange(LoadRangeParams params, LoadRangeCallback<T> callback);

    public PositionalDataSource() {
        super(DataSource.KeyType.POSITIONAL);
    }

    /* JADX INFO: compiled from: PositionalDataSource.kt */
    @Metadata(d1 = {"\u0000\u001a\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0002\b\u0002\b\u0016\u0018\u00002\u00020\u0001B%\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0003\u0012\u0006\u0010\u0005\u001a\u00020\u0003\u0012\u0006\u0010\u0006\u001a\u00020\u0007¢\u0006\u0002\u0010\bR\u0010\u0010\u0005\u001a\u00020\u00038\u0006X\u0087\u0004¢\u0006\u0002\n\u0000R\u0010\u0010\u0006\u001a\u00020\u00078\u0006X\u0087\u0004¢\u0006\u0002\n\u0000R\u0010\u0010\u0004\u001a\u00020\u00038\u0006X\u0087\u0004¢\u0006\u0002\n\u0000R\u0010\u0010\u0002\u001a\u00020\u00038\u0006X\u0087\u0004¢\u0006\u0002\n\u0000¨\u0006\t"}, d2 = {"Landroidx/paging/PositionalDataSource$LoadInitialParams;", "", "requestedStartPosition", "", "requestedLoadSize", "pageSize", "placeholdersEnabled", "", "(IIIZ)V", "paging-common"}, k = 1, mv = {1, 5, 1}, xi = 48)
    public static class LoadInitialParams {

        @JvmField
        public final int pageSize;

        @JvmField
        public final boolean placeholdersEnabled;

        @JvmField
        public final int requestedLoadSize;

        @JvmField
        public final int requestedStartPosition;

        public LoadInitialParams(int i, int i2, int i3, boolean z) {
            this.requestedStartPosition = i;
            this.requestedLoadSize = i2;
            this.pageSize = i3;
            this.placeholdersEnabled = z;
            if (!(i >= 0)) {
                throw new IllegalStateException(Intrinsics.stringPlus("invalid start position: ", Integer.valueOf(i)).toString());
            }
            if (!(i2 >= 0)) {
                throw new IllegalStateException(Intrinsics.stringPlus("invalid load size: ", Integer.valueOf(i2)).toString());
            }
            if (!(i3 >= 0)) {
                throw new IllegalStateException(Intrinsics.stringPlus("invalid page size: ", Integer.valueOf(i3)).toString());
            }
        }
    }

    /* JADX INFO: compiled from: PositionalDataSource.kt */
    @Metadata(d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0002\b\u0003\b\u0016\u0018\u00002\u00020\u0001B\u0015\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0003¢\u0006\u0002\u0010\u0005R\u0010\u0010\u0004\u001a\u00020\u00038\u0006X\u0087\u0004¢\u0006\u0002\n\u0000R\u0010\u0010\u0002\u001a\u00020\u00038\u0006X\u0087\u0004¢\u0006\u0002\n\u0000¨\u0006\u0006"}, d2 = {"Landroidx/paging/PositionalDataSource$LoadRangeParams;", "", "startPosition", "", "loadSize", "(II)V", "paging-common"}, k = 1, mv = {1, 5, 1}, xi = 48)
    public static class LoadRangeParams {

        @JvmField
        public final int loadSize;

        @JvmField
        public final int startPosition;

        public LoadRangeParams(int i, int i2) {
            this.startPosition = i;
            this.loadSize = i2;
        }
    }

    /* JADX INFO: compiled from: PositionalDataSource.kt */
    @Metadata(d1 = {"\u0000\u001a\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\b\u0087\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u0018\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\u0004H\u0007J \u0010\b\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u00062\u0006\u0010\t\u001a\u00020\u00042\u0006\u0010\u0007\u001a\u00020\u0004H\u0007¨\u0006\n"}, d2 = {"Landroidx/paging/PositionalDataSource$Companion;", "", "()V", "computeInitialLoadPosition", "", "params", "Landroidx/paging/PositionalDataSource$LoadInitialParams;", "totalCount", "computeInitialLoadSize", "initialLoadPosition", "paging-common"}, k = 1, mv = {1, 5, 1}, xi = 48)
    @RestrictTo({RestrictTo.Scope.LIBRARY})
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }

        @JvmStatic
        public final int computeInitialLoadPosition(LoadInitialParams params, int totalCount) {
            Intrinsics.checkNotNullParameter(params, "params");
            int i = params.requestedStartPosition;
            int i2 = params.requestedLoadSize;
            int i3 = params.pageSize;
            return Math.max(0, Math.min(((((totalCount - i2) + i3) - 1) / i3) * i3, (i / i3) * i3));
        }

        @JvmStatic
        public final int computeInitialLoadSize(LoadInitialParams params, int initialLoadPosition, int totalCount) {
            Intrinsics.checkNotNullParameter(params, "params");
            return Math.min(totalCount - initialLoadPosition, params.requestedLoadSize);
        }
    }

    @Override // androidx.paging.DataSource
    public final Object load$paging_common(DataSource.Params<Integer> params, Continuation<? super DataSource.BaseResult<T>> continuation) {
        if (params.getType() == LoadType.REFRESH) {
            int initialLoadSize = params.getInitialLoadSize();
            int iMax = 0;
            if (params.getKey() != null) {
                int iIntValue = params.getKey().intValue();
                if (params.getPlaceholdersEnabled()) {
                    initialLoadSize = Math.max(initialLoadSize / params.getPageSize(), 2) * params.getPageSize();
                    iMax = Math.max(0, ((iIntValue - (initialLoadSize / 2)) / params.getPageSize()) * params.getPageSize());
                } else {
                    iMax = Math.max(0, iIntValue - (initialLoadSize / 2));
                }
            }
            return loadInitial$paging_common(new LoadInitialParams(iMax, initialLoadSize, params.getPageSize(), params.getPlaceholdersEnabled()), continuation);
        }
        Integer key = params.getKey();
        Intrinsics.checkNotNull(key);
        int iIntValue2 = key.intValue();
        int pageSize = params.getPageSize();
        if (params.getType() == LoadType.PREPEND) {
            pageSize = Math.min(pageSize, iIntValue2);
            iIntValue2 -= pageSize;
        }
        return loadRange(new LoadRangeParams(iIntValue2, pageSize), continuation);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final Object loadRange(final LoadRangeParams loadRangeParams, Continuation<? super DataSource.BaseResult<T>> continuation) {
        final CancellableContinuationImpl cancellableContinuationImpl = new CancellableContinuationImpl(IntrinsicsKt__IntrinsicsJvmKt.intercepted(continuation), 1);
        cancellableContinuationImpl.initCancellability();
        loadRange(loadRangeParams, new LoadRangeCallback<T>() { // from class: androidx.paging.PositionalDataSource$loadRange$2$1
            @Override // androidx.paging.PositionalDataSource.LoadRangeCallback
            public void onResult(List<? extends T> data) {
                Intrinsics.checkNotNullParameter(data, "data");
                int i = loadRangeParams.startPosition;
                Integer numValueOf = i == 0 ? null : Integer.valueOf(i);
                if (this.isInvalid()) {
                    cancellableContinuationImpl.resumeWith(Result.m2810constructorimpl(DataSource.BaseResult.INSTANCE.empty$paging_common()));
                } else {
                    cancellableContinuationImpl.resumeWith(Result.m2810constructorimpl(new DataSource.BaseResult(data, numValueOf, Integer.valueOf(loadRangeParams.startPosition + data.size()), 0, 0, 24, null)));
                }
            }
        });
        Object result = cancellableContinuationImpl.getResult();
        if (result == IntrinsicsKt__IntrinsicsKt.getCOROUTINE_SUSPENDED()) {
            DebugProbesKt.probeCoroutineSuspended(continuation);
        }
        return result;
    }

    @VisibleForTesting
    public final Object loadInitial$paging_common(final LoadInitialParams loadInitialParams, Continuation<? super DataSource.BaseResult<T>> continuation) {
        final CancellableContinuationImpl cancellableContinuationImpl = new CancellableContinuationImpl(IntrinsicsKt__IntrinsicsJvmKt.intercepted(continuation), 1);
        cancellableContinuationImpl.initCancellability();
        loadInitial(loadInitialParams, new LoadInitialCallback<T>(this) { // from class: androidx.paging.PositionalDataSource$loadInitial$2$1
            final /* synthetic */ PositionalDataSource<T> this$0;

            /* JADX WARN: Multi-variable type inference failed */
            {
                this.this$0 = this;
            }

            @Override // androidx.paging.PositionalDataSource.LoadInitialCallback
            public void onResult(List<? extends T> data, int position, int totalCount) {
                Intrinsics.checkNotNullParameter(data, "data");
                if (this.this$0.isInvalid()) {
                    cancellableContinuationImpl.resumeWith(Result.m2810constructorimpl(DataSource.BaseResult.INSTANCE.empty$paging_common()));
                } else {
                    int size = data.size() + position;
                    resume(loadInitialParams, new DataSource.BaseResult<>(data, position == 0 ? null : Integer.valueOf(position), size == totalCount ? null : Integer.valueOf(size), position, (totalCount - data.size()) - position));
                }
            }

            @Override // androidx.paging.PositionalDataSource.LoadInitialCallback
            public void onResult(List<? extends T> data, int position) {
                Intrinsics.checkNotNullParameter(data, "data");
                if (this.this$0.isInvalid()) {
                    cancellableContinuationImpl.resumeWith(Result.m2810constructorimpl(DataSource.BaseResult.INSTANCE.empty$paging_common()));
                } else {
                    resume(loadInitialParams, new DataSource.BaseResult<>(data, position == 0 ? null : Integer.valueOf(position), Integer.valueOf(data.size() + position), position, Integer.MIN_VALUE));
                }
            }

            private final void resume(PositionalDataSource.LoadInitialParams params, DataSource.BaseResult<T> result) {
                if (params.placeholdersEnabled) {
                    result.validateForInitialTiling$paging_common(params.pageSize);
                }
                cancellableContinuationImpl.resumeWith(Result.m2810constructorimpl(result));
            }
        });
        Object result = cancellableContinuationImpl.getResult();
        if (result == IntrinsicsKt__IntrinsicsKt.getCOROUTINE_SUSPENDED()) {
            DebugProbesKt.probeCoroutineSuspended(continuation);
        }
        return result;
    }

    @Override // androidx.paging.DataSource
    /* JADX INFO: renamed from: isContiguous$paging_common, reason: from getter */
    public boolean getIsContiguous() {
        return this.isContiguous;
    }

    /* JADX WARN: Can't rename method to resolve collision */
    @Override // androidx.paging.DataSource
    public final Integer getKeyInternal$paging_common(T item) {
        Intrinsics.checkNotNullParameter(item, "item");
        throw new IllegalStateException("Cannot get key by item in positionalDataSource");
    }

    @Override // androidx.paging.DataSource
    public final <V> PositionalDataSource<V> mapByPage(Function<List<T>, List<V>> function) {
        Intrinsics.checkNotNullParameter(function, "function");
        return new WrapperPositionalDataSource(this, function);
    }

    @Override // androidx.paging.DataSource
    public final <V> PositionalDataSource<V> mapByPage(final Function1<? super List<? extends T>, ? extends List<? extends V>> function) {
        Intrinsics.checkNotNullParameter(function, "function");
        return mapByPage((Function) new Function() { // from class: androidx.paging.PositionalDataSource.mapByPage.1
            @Override // androidx.arch.core.util.Function
            public final List<V> apply(List<? extends T> it) {
                Function1<List<? extends T>, List<V>> function1 = function;
                Intrinsics.checkNotNullExpressionValue(it, "it");
                return (List) function1.invoke(it);
            }
        });
    }

    @Override // androidx.paging.DataSource
    public final <V> PositionalDataSource<V> map(final Function<T, V> function) {
        Intrinsics.checkNotNullParameter(function, "function");
        return mapByPage((Function) new Function() { // from class: androidx.paging.PositionalDataSource.map.1
            @Override // androidx.arch.core.util.Function
            public final List<V> apply(List<? extends T> list) {
                Intrinsics.checkNotNullExpressionValue(list, "list");
                Function<T, V> function2 = function;
                ArrayList arrayList = new ArrayList(CollectionsKt__IterablesKt.collectionSizeOrDefault(list, 10));
                Iterator<T> it = list.iterator();
                while (it.hasNext()) {
                    arrayList.add(function2.apply(it.next()));
                }
                return arrayList;
            }
        });
    }

    @Override // androidx.paging.DataSource
    public final <V> PositionalDataSource<V> map(final Function1<? super T, ? extends V> function) {
        Intrinsics.checkNotNullParameter(function, "function");
        return mapByPage((Function) new Function() { // from class: androidx.paging.PositionalDataSource.map.2
            @Override // androidx.arch.core.util.Function
            public final List<V> apply(List<? extends T> list) {
                Intrinsics.checkNotNullExpressionValue(list, "list");
                Function1<T, V> function1 = function;
                ArrayList arrayList = new ArrayList(CollectionsKt__IterablesKt.collectionSizeOrDefault(list, 10));
                Iterator<T> it = list.iterator();
                while (it.hasNext()) {
                    arrayList.add(function1.invoke(it.next()));
                }
                return arrayList;
            }
        });
    }
}
