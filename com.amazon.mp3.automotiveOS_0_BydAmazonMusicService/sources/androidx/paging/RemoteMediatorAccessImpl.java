package androidx.paging;

import androidx.paging.AccessorState;
import androidx.paging.LoadState;
import androidx.paging.RemoteMediator;
import com.visualon.OSMPUtils.voOSType;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import kotlin.Metadata;
import kotlin.NoWhenBranchMatchedException;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt__IntrinsicsKt;
import kotlin.coroutines.jvm.internal.ContinuationImpl;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Ref;
import kotlinx.coroutines.BuildersKt__Builders_commonKt;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.flow.StateFlow;

/* JADX INFO: compiled from: RemoteMediatorAccessor.kt */
/* JADX INFO: loaded from: classes2.dex */
@Metadata(d1 = {"\u0000V\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\b\u0002\u0018\u0000 \u001f*\b\b\u0000\u0010\u0001*\u00020\u0002*\b\b\u0001\u0010\u0003*\u00020\u00022\u000e\u0012\u0004\u0012\u0002H\u0001\u0012\u0004\u0012\u0002H\u00030\u0004:\u0001\u001fB!\u0012\u0006\u0010\u0005\u001a\u00020\u0006\u0012\u0012\u0010\u0007\u001a\u000e\u0012\u0004\u0012\u00028\u0000\u0012\u0004\u0012\u00028\u00010\b¢\u0006\u0002\u0010\tJ\u0011\u0010\u0013\u001a\u00020\u0014H\u0096@ø\u0001\u0000¢\u0006\u0002\u0010\u0015J\b\u0010\u0016\u001a\u00020\u0017H\u0002J\b\u0010\u0018\u001a\u00020\u0017H\u0002J$\u0010\u0019\u001a\u00020\u00172\u0006\u0010\u001a\u001a\u00020\u001b2\u0012\u0010\u001c\u001a\u000e\u0012\u0004\u0012\u00028\u0000\u0012\u0004\u0012\u00028\u00010\u001dH\u0016J\u001c\u0010\u001e\u001a\u00020\u00172\u0012\u0010\u001c\u001a\u000e\u0012\u0004\u0012\u00028\u0000\u0012\u0004\u0012\u00028\u00010\u001dH\u0016R\u001a\u0010\n\u001a\u000e\u0012\u0004\u0012\u00028\u0000\u0012\u0004\u0012\u00028\u00010\u000bX\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\f\u001a\u00020\rX\u0082\u0004¢\u0006\u0002\n\u0000R\u001a\u0010\u0007\u001a\u000e\u0012\u0004\u0012\u00028\u0000\u0012\u0004\u0012\u00028\u00010\bX\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0006X\u0082\u0004¢\u0006\u0002\n\u0000R\u001a\u0010\u000e\u001a\b\u0012\u0004\u0012\u00020\u00100\u000f8VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b\u0011\u0010\u0012\u0082\u0002\u0004\n\u0002\b\u0019¨\u0006 "}, d2 = {"Landroidx/paging/RemoteMediatorAccessImpl;", "Key", "", "Value", "Landroidx/paging/RemoteMediatorAccessor;", "scope", "Lkotlinx/coroutines/CoroutineScope;", "remoteMediator", "Landroidx/paging/RemoteMediator;", "(Lkotlinx/coroutines/CoroutineScope;Landroidx/paging/RemoteMediator;)V", "accessorState", "Landroidx/paging/AccessorStateHolder;", "isolationRunner", "Landroidx/paging/SingleRunner;", "state", "Lkotlinx/coroutines/flow/StateFlow;", "Landroidx/paging/LoadStates;", "getState", "()Lkotlinx/coroutines/flow/StateFlow;", "initialize", "Landroidx/paging/RemoteMediator$InitializeAction;", "(Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "launchBoundary", "", "launchRefresh", "requestLoad", "loadType", "Landroidx/paging/LoadType;", "pagingState", "Landroidx/paging/PagingState;", "retryFailed", "Companion", "paging-common"}, k = 1, mv = {1, 5, 1}, xi = 48)
final class RemoteMediatorAccessImpl<Key, Value> implements RemoteMediatorAccessor<Key, Value> {
    private static final int PRIORITY_APPEND_PREPEND = 1;
    private static final int PRIORITY_REFRESH = 2;
    private final AccessorStateHolder<Key, Value> accessorState;
    private final SingleRunner isolationRunner;
    private final RemoteMediator<Key, Value> remoteMediator;
    private final CoroutineScope scope;

    /* JADX INFO: compiled from: RemoteMediatorAccessor.kt */
    @Metadata(k = 3, mv = {1, 5, 1}, xi = 48)
    public /* synthetic */ class WhenMappings {
        public static final /* synthetic */ int[] $EnumSwitchMapping$0;

        static {
            int[] iArr = new int[LoadType.values().length];
            iArr[LoadType.REFRESH.ordinal()] = 1;
            $EnumSwitchMapping$0 = iArr;
        }
    }

    /* JADX INFO: renamed from: androidx.paging.RemoteMediatorAccessImpl$initialize$1, reason: invalid class name */
    /* JADX INFO: compiled from: RemoteMediatorAccessor.kt */
    @Metadata(k = 3, mv = {1, 5, 1}, xi = 48)
    @DebugMetadata(c = "androidx.paging.RemoteMediatorAccessImpl", f = "RemoteMediatorAccessor.kt", i = {0}, l = {397}, m = "initialize", n = {"this"}, s = {"L$0"})
    static final class AnonymousClass1 extends ContinuationImpl {
        Object L$0;
        int label;
        /* synthetic */ Object result;
        final /* synthetic */ RemoteMediatorAccessImpl<Key, Value> this$0;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        AnonymousClass1(RemoteMediatorAccessImpl<Key, Value> remoteMediatorAccessImpl, Continuation<? super AnonymousClass1> continuation) {
            super(continuation);
            this.this$0 = remoteMediatorAccessImpl;
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object obj) {
            this.result = obj;
            this.label |= Integer.MIN_VALUE;
            return this.this$0.initialize(this);
        }
    }

    public RemoteMediatorAccessImpl(CoroutineScope scope, RemoteMediator<Key, Value> remoteMediator) {
        Intrinsics.checkNotNullParameter(scope, "scope");
        Intrinsics.checkNotNullParameter(remoteMediator, "remoteMediator");
        this.scope = scope;
        this.remoteMediator = remoteMediator;
        this.accessorState = new AccessorStateHolder<>();
        this.isolationRunner = new SingleRunner(false);
    }

    @Override // androidx.paging.RemoteMediatorAccessor
    public StateFlow<LoadStates> getState() {
        return this.accessorState.getLoadStates();
    }

    @Override // androidx.paging.RemoteMediatorConnection
    public void requestLoad(final LoadType loadType, final PagingState<Key, Value> pagingState) {
        Intrinsics.checkNotNullParameter(loadType, "loadType");
        Intrinsics.checkNotNullParameter(pagingState, "pagingState");
        if (((Boolean) this.accessorState.use(new Function1<AccessorState<Key, Value>, Boolean>() { // from class: androidx.paging.RemoteMediatorAccessImpl$requestLoad$newRequest$1
            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super(1);
            }

            @Override // kotlin.jvm.functions.Function1
            public final Boolean invoke(AccessorState<Key, Value> it) {
                Intrinsics.checkNotNullParameter(it, "it");
                return Boolean.valueOf(it.add(loadType, pagingState));
            }
        })).booleanValue()) {
            if (WhenMappings.$EnumSwitchMapping$0[loadType.ordinal()] == 1) {
                launchRefresh();
            } else {
                launchBoundary();
            }
        }
    }

    /* JADX INFO: renamed from: androidx.paging.RemoteMediatorAccessImpl$launchRefresh$1, reason: invalid class name and case insensitive filesystem */
    /* JADX INFO: compiled from: RemoteMediatorAccessor.kt */
    @Metadata(bv = {}, d1 = {"\u0000\u0012\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0000\u0010\u0005\u001a\u00020\u0004\"\b\b\u0000\u0010\u0001*\u00020\u0000\"\b\b\u0001\u0010\u0002*\u00020\u0000*\u00020\u0003H\u008a@"}, d2 = {"", "Key", "Value", "Lkotlinx/coroutines/CoroutineScope;", "", "<anonymous>"}, k = 3, mv = {1, 5, 1})
    @DebugMetadata(c = "androidx.paging.RemoteMediatorAccessImpl$launchRefresh$1", f = "RemoteMediatorAccessor.kt", i = {0}, l = {266}, m = "invokeSuspend", n = {"launchAppendPrepend"}, s = {"L$0"})
    static final class C02481 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
        Object L$0;
        int label;
        final /* synthetic */ RemoteMediatorAccessImpl<Key, Value> this$0;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        C02481(RemoteMediatorAccessImpl<Key, Value> remoteMediatorAccessImpl, Continuation<? super C02481> continuation) {
            super(2, continuation);
            this.this$0 = remoteMediatorAccessImpl;
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
            return new C02481(this.this$0, continuation);
        }

        @Override // kotlin.jvm.functions.Function2
        /* JADX INFO: renamed from: invoke, reason: avoid collision after fix types in other method and merged with bridge method [inline-methods] */
        public final Object mo4invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
            return ((C02481) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object obj) {
            Ref.BooleanRef booleanRef;
            Object coroutine_suspended = IntrinsicsKt__IntrinsicsKt.getCOROUTINE_SUSPENDED();
            int i = this.label;
            if (i == 0) {
                ResultKt.throwOnFailure(obj);
                Ref.BooleanRef booleanRef2 = new Ref.BooleanRef();
                SingleRunner singleRunner = ((RemoteMediatorAccessImpl) this.this$0).isolationRunner;
                C00381 c00381 = new C00381(this.this$0, booleanRef2, null);
                this.L$0 = booleanRef2;
                this.label = 1;
                if (singleRunner.runInIsolation(2, c00381, this) == coroutine_suspended) {
                    return coroutine_suspended;
                }
                booleanRef = booleanRef2;
            } else {
                if (i != 1) {
                    throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                }
                booleanRef = (Ref.BooleanRef) this.L$0;
                ResultKt.throwOnFailure(obj);
            }
            if (booleanRef.element) {
                this.this$0.launchBoundary();
            }
            return Unit.INSTANCE;
        }

        /* JADX INFO: renamed from: androidx.paging.RemoteMediatorAccessImpl$launchRefresh$1$1, reason: invalid class name and collision with other inner class name */
        /* JADX INFO: compiled from: RemoteMediatorAccessor.kt */
        @Metadata(bv = {}, d1 = {"\u0000\u000e\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\u0010\u0004\u001a\u00020\u0003\"\b\b\u0000\u0010\u0001*\u00020\u0000\"\b\b\u0001\u0010\u0002*\u00020\u0000H\u008a@"}, d2 = {"", "Key", "Value", "", "<anonymous>"}, k = 3, mv = {1, 5, 1})
        @DebugMetadata(c = "androidx.paging.RemoteMediatorAccessImpl$launchRefresh$1$1", f = "RemoteMediatorAccessor.kt", i = {}, l = {voOSType.VOOSMP_PID_DOLBY_CERTIFICATION_AUDIO_PROGRAM}, m = "invokeSuspend", n = {}, s = {})
        static final class C00381 extends SuspendLambda implements Function1<Continuation<? super Unit>, Object> {
            final /* synthetic */ Ref.BooleanRef $launchAppendPrepend;
            Object L$0;
            Object L$1;
            int label;
            final /* synthetic */ RemoteMediatorAccessImpl<Key, Value> this$0;

            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            C00381(RemoteMediatorAccessImpl<Key, Value> remoteMediatorAccessImpl, Ref.BooleanRef booleanRef, Continuation<? super C00381> continuation) {
                super(1, continuation);
                this.this$0 = remoteMediatorAccessImpl;
                this.$launchAppendPrepend = booleanRef;
            }

            @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
            public final Continuation<Unit> create(Continuation<?> continuation) {
                return new C00381(this.this$0, this.$launchAppendPrepend, continuation);
            }

            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Continuation<? super Unit> continuation) {
                return ((C00381) create(continuation)).invokeSuspend(Unit.INSTANCE);
            }

            @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
            public final Object invokeSuspend(Object obj) {
                RemoteMediatorAccessImpl<Key, Value> remoteMediatorAccessImpl;
                Ref.BooleanRef booleanRef;
                boolean zBooleanValue;
                Object coroutine_suspended = IntrinsicsKt__IntrinsicsKt.getCOROUTINE_SUSPENDED();
                int i = this.label;
                if (i == 0) {
                    ResultKt.throwOnFailure(obj);
                    PagingState<Key, Value> pagingState = (PagingState) ((RemoteMediatorAccessImpl) this.this$0).accessorState.use(new Function1<AccessorState<Key, Value>, PagingState<Key, Value>>() { // from class: androidx.paging.RemoteMediatorAccessImpl$launchRefresh$1$1$pendingPagingState$1
                        @Override // kotlin.jvm.functions.Function1
                        public final PagingState<Key, Value> invoke(AccessorState<Key, Value> it) {
                            Intrinsics.checkNotNullParameter(it, "it");
                            return it.getPendingRefresh();
                        }
                    });
                    if (pagingState != null) {
                        remoteMediatorAccessImpl = this.this$0;
                        Ref.BooleanRef booleanRef2 = this.$launchAppendPrepend;
                        RemoteMediator remoteMediator = ((RemoteMediatorAccessImpl) remoteMediatorAccessImpl).remoteMediator;
                        LoadType loadType = LoadType.REFRESH;
                        this.L$0 = remoteMediatorAccessImpl;
                        this.L$1 = booleanRef2;
                        this.label = 1;
                        obj = remoteMediator.load(loadType, pagingState, this);
                        if (obj == coroutine_suspended) {
                            return coroutine_suspended;
                        }
                        booleanRef = booleanRef2;
                    }
                    return Unit.INSTANCE;
                }
                if (i != 1) {
                    throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                }
                booleanRef = (Ref.BooleanRef) this.L$1;
                remoteMediatorAccessImpl = (RemoteMediatorAccessImpl) this.L$0;
                ResultKt.throwOnFailure(obj);
                final RemoteMediator.MediatorResult mediatorResult = (RemoteMediator.MediatorResult) obj;
                if (mediatorResult instanceof RemoteMediator.MediatorResult.Success) {
                    zBooleanValue = ((Boolean) ((RemoteMediatorAccessImpl) remoteMediatorAccessImpl).accessorState.use(new Function1<AccessorState<Key, Value>, Boolean>() { // from class: androidx.paging.RemoteMediatorAccessImpl$launchRefresh$1$1$1$1
                        {
                            super(1);
                        }

                        @Override // kotlin.jvm.functions.Function1
                        public final Boolean invoke(AccessorState<Key, Value> it) {
                            Intrinsics.checkNotNullParameter(it, "it");
                            LoadType loadType2 = LoadType.REFRESH;
                            it.clearPendingRequest(loadType2);
                            if (((RemoteMediator.MediatorResult.Success) mediatorResult).getEndOfPaginationReached()) {
                                AccessorState.BlockState blockState = AccessorState.BlockState.COMPLETED;
                                it.setBlockState(loadType2, blockState);
                                it.setBlockState(LoadType.PREPEND, blockState);
                                it.setBlockState(LoadType.APPEND, blockState);
                                it.clearPendingRequests();
                            } else {
                                LoadType loadType3 = LoadType.PREPEND;
                                AccessorState.BlockState blockState2 = AccessorState.BlockState.UNBLOCKED;
                                it.setBlockState(loadType3, blockState2);
                                it.setBlockState(LoadType.APPEND, blockState2);
                            }
                            it.setError(LoadType.PREPEND, null);
                            it.setError(LoadType.APPEND, null);
                            return Boolean.valueOf(it.getPendingBoundary() != null);
                        }
                    })).booleanValue();
                } else if (mediatorResult instanceof RemoteMediator.MediatorResult.Error) {
                    zBooleanValue = ((Boolean) ((RemoteMediatorAccessImpl) remoteMediatorAccessImpl).accessorState.use(new Function1<AccessorState<Key, Value>, Boolean>() { // from class: androidx.paging.RemoteMediatorAccessImpl$launchRefresh$1$1$1$2
                        {
                            super(1);
                        }

                        @Override // kotlin.jvm.functions.Function1
                        public final Boolean invoke(AccessorState<Key, Value> it) {
                            Intrinsics.checkNotNullParameter(it, "it");
                            LoadType loadType2 = LoadType.REFRESH;
                            it.clearPendingRequest(loadType2);
                            it.setError(loadType2, new LoadState.Error(((RemoteMediator.MediatorResult.Error) mediatorResult).getThrowable()));
                            return Boolean.valueOf(it.getPendingBoundary() != null);
                        }
                    })).booleanValue();
                } else {
                    throw new NoWhenBranchMatchedException();
                }
                booleanRef.element = zBooleanValue;
                return Unit.INSTANCE;
            }
        }
    }

    private final void launchRefresh() {
        BuildersKt__Builders_commonKt.launch$default(this.scope, null, null, new C02481(this, null), 3, null);
    }

    /* JADX INFO: renamed from: androidx.paging.RemoteMediatorAccessImpl$launchBoundary$1, reason: invalid class name and case insensitive filesystem */
    /* JADX INFO: compiled from: RemoteMediatorAccessor.kt */
    @Metadata(bv = {}, d1 = {"\u0000\u0012\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0000\u0010\u0005\u001a\u00020\u0004\"\b\b\u0000\u0010\u0001*\u00020\u0000\"\b\b\u0001\u0010\u0002*\u00020\u0000*\u00020\u0003H\u008a@"}, d2 = {"", "Key", "Value", "Lkotlinx/coroutines/CoroutineScope;", "", "<anonymous>"}, k = 3, mv = {1, 5, 1})
    @DebugMetadata(c = "androidx.paging.RemoteMediatorAccessImpl$launchBoundary$1", f = "RemoteMediatorAccessor.kt", i = {}, l = {338}, m = "invokeSuspend", n = {}, s = {})
    static final class C02471 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
        int label;
        final /* synthetic */ RemoteMediatorAccessImpl<Key, Value> this$0;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        C02471(RemoteMediatorAccessImpl<Key, Value> remoteMediatorAccessImpl, Continuation<? super C02471> continuation) {
            super(2, continuation);
            this.this$0 = remoteMediatorAccessImpl;
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
            return new C02471(this.this$0, continuation);
        }

        @Override // kotlin.jvm.functions.Function2
        /* JADX INFO: renamed from: invoke, reason: avoid collision after fix types in other method and merged with bridge method [inline-methods] */
        public final Object mo4invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
            return ((C02471) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object obj) {
            Object coroutine_suspended = IntrinsicsKt__IntrinsicsKt.getCOROUTINE_SUSPENDED();
            int i = this.label;
            if (i == 0) {
                ResultKt.throwOnFailure(obj);
                SingleRunner singleRunner = ((RemoteMediatorAccessImpl) this.this$0).isolationRunner;
                C00361 c00361 = new C00361(this.this$0, null);
                this.label = 1;
                if (singleRunner.runInIsolation(1, c00361, this) == coroutine_suspended) {
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

        /* JADX INFO: renamed from: androidx.paging.RemoteMediatorAccessImpl$launchBoundary$1$1, reason: invalid class name and collision with other inner class name */
        /* JADX INFO: compiled from: RemoteMediatorAccessor.kt */
        @Metadata(bv = {}, d1 = {"\u0000\u000e\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\u0010\u0004\u001a\u00020\u0003\"\b\b\u0000\u0010\u0001*\u00020\u0000\"\b\b\u0001\u0010\u0002*\u00020\u0000H\u008a@"}, d2 = {"", "Key", "Value", "", "<anonymous>"}, k = 3, mv = {1, 5, 1})
        @DebugMetadata(c = "androidx.paging.RemoteMediatorAccessImpl$launchBoundary$1$1", f = "RemoteMediatorAccessor.kt", i = {0}, l = {345}, m = "invokeSuspend", n = {"loadType"}, s = {"L$0"})
        static final class C00361 extends SuspendLambda implements Function1<Continuation<? super Unit>, Object> {
            Object L$0;
            int label;
            final /* synthetic */ RemoteMediatorAccessImpl<Key, Value> this$0;

            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            C00361(RemoteMediatorAccessImpl<Key, Value> remoteMediatorAccessImpl, Continuation<? super C00361> continuation) {
                super(1, continuation);
                this.this$0 = remoteMediatorAccessImpl;
            }

            @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
            public final Continuation<Unit> create(Continuation<?> continuation) {
                return new C00361(this.this$0, continuation);
            }

            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Continuation<? super Unit> continuation) {
                return ((C00361) create(continuation)).invokeSuspend(Unit.INSTANCE);
            }

            /* JADX WARN: Removed duplicated region for block: B:11:0x0032  */
            /* JADX WARN: Removed duplicated region for block: B:13:0x0035  */
            /* JADX WARN: Removed duplicated region for block: B:19:0x005c  */
            /* JADX WARN: Removed duplicated region for block: B:20:0x006b  */
            /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:16:0x0052 -> B:17:0x0056). Please report as a decompilation issue!!! */
            @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
            /*
                Code decompiled incorrectly, please refer to instructions dump.
                To view partially-correct code enable 'Show inconsistent code' option in preferences
            */
            public final java.lang.Object invokeSuspend(java.lang.Object r8) {
                /*
                    r7 = this;
                    java.lang.Object r0 = kotlin.coroutines.intrinsics.IntrinsicsKt.getCOROUTINE_SUSPENDED()
                    int r1 = r7.label
                    r2 = 1
                    if (r1 == 0) goto L1e
                    if (r1 != r2) goto L16
                    java.lang.Object r1 = r7.L$0
                    androidx.paging.LoadType r1 = (androidx.paging.LoadType) r1
                    kotlin.ResultKt.throwOnFailure(r8)
                    r3 = r1
                    r1 = r0
                    r0 = r7
                    goto L56
                L16:
                    java.lang.IllegalStateException r8 = new java.lang.IllegalStateException
                    java.lang.String r0 = "call to 'resume' before 'invoke' with coroutine"
                    r8.<init>(r0)
                    throw r8
                L1e:
                    kotlin.ResultKt.throwOnFailure(r8)
                    r8 = r7
                L22:
                    androidx.paging.RemoteMediatorAccessImpl<Key, Value> r1 = r8.this$0
                    androidx.paging.AccessorStateHolder r1 = androidx.paging.RemoteMediatorAccessImpl.access$getAccessorState$p(r1)
                    androidx.paging.RemoteMediatorAccessImpl$launchBoundary$1$1$1 r3 = new kotlin.jvm.functions.Function1<androidx.paging.AccessorState<Key, Value>, kotlin.Pair<? extends androidx.paging.LoadType, ? extends androidx.paging.PagingState<Key, Value>>>() { // from class: androidx.paging.RemoteMediatorAccessImpl.launchBoundary.1.1.1
                        static {
                            /*
                                androidx.paging.RemoteMediatorAccessImpl$launchBoundary$1$1$1 r0 = new androidx.paging.RemoteMediatorAccessImpl$launchBoundary$1$1$1
                                r0.<init>()
                                
                                // error: 0x0005: SPUT (r0 I:androidx.paging.RemoteMediatorAccessImpl$launchBoundary$1$1$1) androidx.paging.RemoteMediatorAccessImpl.launchBoundary.1.1.1.INSTANCE androidx.paging.RemoteMediatorAccessImpl$launchBoundary$1$1$1
                                return
                            */
                            throw new UnsupportedOperationException("Method not decompiled: androidx.paging.RemoteMediatorAccessImpl.C02471.C00361.C00371.<clinit>():void");
                        }

                        {
                            /*
                                r1 = this;
                                r0 = 1
                                r1.<init>(r0)
                                return
                            */
                            throw new UnsupportedOperationException("Method not decompiled: androidx.paging.RemoteMediatorAccessImpl.C02471.C00361.C00371.<init>():void");
                        }

                        @Override // kotlin.jvm.functions.Function1
                        public /* bridge */ /* synthetic */ java.lang.Object invoke(java.lang.Object r1) {
                            /*
                                r0 = this;
                                androidx.paging.AccessorState r1 = (androidx.paging.AccessorState) r1
                                kotlin.Pair r1 = r0.invoke(r1)
                                return r1
                            */
                            throw new UnsupportedOperationException("Method not decompiled: androidx.paging.RemoteMediatorAccessImpl.C02471.C00361.C00371.invoke(java.lang.Object):java.lang.Object");
                        }

                        @Override // kotlin.jvm.functions.Function1
                        public final kotlin.Pair<androidx.paging.LoadType, androidx.paging.PagingState<Key, Value>> invoke(androidx.paging.AccessorState<Key, Value> r2) {
                            /*
                                r1 = this;
                                java.lang.String r0 = "it"
                                kotlin.jvm.internal.Intrinsics.checkNotNullParameter(r2, r0)
                                kotlin.Pair r2 = r2.getPendingBoundary()
                                return r2
                            */
                            throw new UnsupportedOperationException("Method not decompiled: androidx.paging.RemoteMediatorAccessImpl.C02471.C00361.C00371.invoke(androidx.paging.AccessorState):kotlin.Pair");
                        }
                    }
                    java.lang.Object r1 = r1.use(r3)
                    kotlin.Pair r1 = (kotlin.Pair) r1
                    if (r1 != 0) goto L35
                    kotlin.Unit r8 = kotlin.Unit.INSTANCE
                    return r8
                L35:
                    java.lang.Object r3 = r1.component1()
                    androidx.paging.LoadType r3 = (androidx.paging.LoadType) r3
                    java.lang.Object r1 = r1.component2()
                    androidx.paging.PagingState r1 = (androidx.paging.PagingState) r1
                    androidx.paging.RemoteMediatorAccessImpl<Key, Value> r4 = r8.this$0
                    androidx.paging.RemoteMediator r4 = androidx.paging.RemoteMediatorAccessImpl.access$getRemoteMediator$p(r4)
                    r8.L$0 = r3
                    r8.label = r2
                    java.lang.Object r1 = r4.load(r3, r1, r8)
                    if (r1 != r0) goto L52
                    return r0
                L52:
                    r6 = r0
                    r0 = r8
                    r8 = r1
                    r1 = r6
                L56:
                    androidx.paging.RemoteMediator$MediatorResult r8 = (androidx.paging.RemoteMediator.MediatorResult) r8
                    boolean r4 = r8 instanceof androidx.paging.RemoteMediator.MediatorResult.Success
                    if (r4 == 0) goto L6b
                    androidx.paging.RemoteMediatorAccessImpl<Key, Value> r4 = r0.this$0
                    androidx.paging.AccessorStateHolder r4 = androidx.paging.RemoteMediatorAccessImpl.access$getAccessorState$p(r4)
                    androidx.paging.RemoteMediatorAccessImpl$launchBoundary$1$1$2 r5 = new androidx.paging.RemoteMediatorAccessImpl$launchBoundary$1$1$2
                    r5.<init>()
                    r4.use(r5)
                    goto L7d
                L6b:
                    boolean r4 = r8 instanceof androidx.paging.RemoteMediator.MediatorResult.Error
                    if (r4 == 0) goto L7d
                    androidx.paging.RemoteMediatorAccessImpl<Key, Value> r4 = r0.this$0
                    androidx.paging.AccessorStateHolder r4 = androidx.paging.RemoteMediatorAccessImpl.access$getAccessorState$p(r4)
                    androidx.paging.RemoteMediatorAccessImpl$launchBoundary$1$1$3 r5 = new androidx.paging.RemoteMediatorAccessImpl$launchBoundary$1$1$3
                    r5.<init>()
                    r4.use(r5)
                L7d:
                    r8 = r0
                    r0 = r1
                    goto L22
                */
                throw new UnsupportedOperationException("Method not decompiled: androidx.paging.RemoteMediatorAccessImpl.C02471.C00361.invokeSuspend(java.lang.Object):java.lang.Object");
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void launchBoundary() {
        BuildersKt__Builders_commonKt.launch$default(this.scope, null, null, new C02471(this, null), 3, null);
    }

    @Override // androidx.paging.RemoteMediatorConnection
    public void retryFailed(PagingState<Key, Value> pagingState) {
        Intrinsics.checkNotNullParameter(pagingState, "pagingState");
        final ArrayList arrayList = new ArrayList();
        this.accessorState.use(new Function1<AccessorState<Key, Value>, Unit>() { // from class: androidx.paging.RemoteMediatorAccessImpl.retryFailed.1
            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super(1);
            }

            @Override // kotlin.jvm.functions.Function1
            public /* bridge */ /* synthetic */ Unit invoke(Object obj) {
                invoke((AccessorState) obj);
                return Unit.INSTANCE;
            }

            public final void invoke(AccessorState<Key, Value> accessorState) {
                Intrinsics.checkNotNullParameter(accessorState, "accessorState");
                LoadStates loadStatesComputeLoadStates = accessorState.computeLoadStates();
                boolean z = loadStatesComputeLoadStates.getRefresh() instanceof LoadState.Error;
                accessorState.clearErrors();
                if (z) {
                    List<LoadType> list = arrayList;
                    LoadType loadType = LoadType.REFRESH;
                    list.add(loadType);
                    accessorState.setBlockState(loadType, AccessorState.BlockState.UNBLOCKED);
                }
                if (loadStatesComputeLoadStates.getAppend() instanceof LoadState.Error) {
                    if (!z) {
                        arrayList.add(LoadType.APPEND);
                    }
                    accessorState.clearPendingRequest(LoadType.APPEND);
                }
                if (loadStatesComputeLoadStates.getPrepend() instanceof LoadState.Error) {
                    if (!z) {
                        arrayList.add(LoadType.PREPEND);
                    }
                    accessorState.clearPendingRequest(LoadType.PREPEND);
                }
            }
        });
        Iterator it = arrayList.iterator();
        while (it.hasNext()) {
            requestLoad((LoadType) it.next(), pagingState);
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:7:0x0013  */
    @Override // androidx.paging.RemoteMediatorAccessor
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public java.lang.Object initialize(kotlin.coroutines.Continuation<? super androidx.paging.RemoteMediator.InitializeAction> r5) {
        /*
            r4 = this;
            boolean r0 = r5 instanceof androidx.paging.RemoteMediatorAccessImpl.AnonymousClass1
            if (r0 == 0) goto L13
            r0 = r5
            androidx.paging.RemoteMediatorAccessImpl$initialize$1 r0 = (androidx.paging.RemoteMediatorAccessImpl.AnonymousClass1) r0
            int r1 = r0.label
            r2 = -2147483648(0xffffffff80000000, float:-0.0)
            r3 = r1 & r2
            if (r3 == 0) goto L13
            int r1 = r1 - r2
            r0.label = r1
            goto L18
        L13:
            androidx.paging.RemoteMediatorAccessImpl$initialize$1 r0 = new androidx.paging.RemoteMediatorAccessImpl$initialize$1
            r0.<init>(r4, r5)
        L18:
            java.lang.Object r5 = r0.result
            java.lang.Object r1 = kotlin.coroutines.intrinsics.IntrinsicsKt.getCOROUTINE_SUSPENDED()
            int r2 = r0.label
            r3 = 1
            if (r2 == 0) goto L35
            if (r2 != r3) goto L2d
            java.lang.Object r0 = r0.L$0
            androidx.paging.RemoteMediatorAccessImpl r0 = (androidx.paging.RemoteMediatorAccessImpl) r0
            kotlin.ResultKt.throwOnFailure(r5)
            goto L46
        L2d:
            java.lang.IllegalStateException r5 = new java.lang.IllegalStateException
            java.lang.String r0 = "call to 'resume' before 'invoke' with coroutine"
            r5.<init>(r0)
            throw r5
        L35:
            kotlin.ResultKt.throwOnFailure(r5)
            androidx.paging.RemoteMediator<Key, Value> r5 = r4.remoteMediator
            r0.L$0 = r4
            r0.label = r3
            java.lang.Object r5 = r5.initialize(r0)
            if (r5 != r1) goto L45
            return r1
        L45:
            r0 = r4
        L46:
            r1 = r5
            androidx.paging.RemoteMediator$InitializeAction r1 = (androidx.paging.RemoteMediator.InitializeAction) r1
            androidx.paging.RemoteMediator$InitializeAction r2 = androidx.paging.RemoteMediator.InitializeAction.LAUNCH_INITIAL_REFRESH
            if (r1 != r2) goto L54
            androidx.paging.AccessorStateHolder<Key, Value> r0 = r0.accessorState
            androidx.paging.RemoteMediatorAccessImpl$initialize$2$1 r1 = new kotlin.jvm.functions.Function1<androidx.paging.AccessorState<Key, Value>, kotlin.Unit>() { // from class: androidx.paging.RemoteMediatorAccessImpl$initialize$2$1
                static {
                    /*
                        androidx.paging.RemoteMediatorAccessImpl$initialize$2$1 r0 = new androidx.paging.RemoteMediatorAccessImpl$initialize$2$1
                        r0.<init>()
                        
                        // error: 0x0005: SPUT (r0 I:androidx.paging.RemoteMediatorAccessImpl$initialize$2$1) androidx.paging.RemoteMediatorAccessImpl$initialize$2$1.INSTANCE androidx.paging.RemoteMediatorAccessImpl$initialize$2$1
                        return
                    */
                    throw new UnsupportedOperationException("Method not decompiled: androidx.paging.RemoteMediatorAccessImpl$initialize$2$1.<clinit>():void");
                }

                {
                    /*
                        r1 = this;
                        r0 = 1
                        r1.<init>(r0)
                        return
                    */
                    throw new UnsupportedOperationException("Method not decompiled: androidx.paging.RemoteMediatorAccessImpl$initialize$2$1.<init>():void");
                }

                @Override // kotlin.jvm.functions.Function1
                public /* bridge */ /* synthetic */ kotlin.Unit invoke(java.lang.Object r1) {
                    /*
                        r0 = this;
                        androidx.paging.AccessorState r1 = (androidx.paging.AccessorState) r1
                        r0.invoke(r1)
                        kotlin.Unit r1 = kotlin.Unit.INSTANCE
                        return r1
                    */
                    throw new UnsupportedOperationException("Method not decompiled: androidx.paging.RemoteMediatorAccessImpl$initialize$2$1.invoke(java.lang.Object):java.lang.Object");
                }

                public final void invoke(androidx.paging.AccessorState<Key, Value> r3) {
                    /*
                        r2 = this;
                        java.lang.String r0 = "it"
                        kotlin.jvm.internal.Intrinsics.checkNotNullParameter(r3, r0)
                        androidx.paging.LoadType r0 = androidx.paging.LoadType.APPEND
                        androidx.paging.AccessorState$BlockState r1 = androidx.paging.AccessorState.BlockState.REQUIRES_REFRESH
                        r3.setBlockState(r0, r1)
                        androidx.paging.LoadType r0 = androidx.paging.LoadType.PREPEND
                        r3.setBlockState(r0, r1)
                        return
                    */
                    throw new UnsupportedOperationException("Method not decompiled: androidx.paging.RemoteMediatorAccessImpl$initialize$2$1.invoke(androidx.paging.AccessorState):void");
                }
            }
            r0.use(r1)
        L54:
            return r5
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.paging.RemoteMediatorAccessImpl.initialize(kotlin.coroutines.Continuation):java.lang.Object");
    }
}
