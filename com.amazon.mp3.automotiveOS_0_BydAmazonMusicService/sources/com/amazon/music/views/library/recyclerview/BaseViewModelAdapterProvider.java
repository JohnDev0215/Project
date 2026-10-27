package com.amazon.music.views.library.recyclerview;

import android.util.Log;
import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.Observer;
import androidx.recyclerview.widget.DiffUtil;
import com.amazon.music.views.library.binders.UniversalBinder;
import com.amazon.music.views.library.models.base.BaseViewModel;
import com.amazon.music.views.library.recyclerview.adapters.BaseModelChange;
import com.amazon.music.views.library.recyclerview.adapters.UniversalAdapter;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import java.util.concurrent.CancellationException;
import kotlin.Metadata;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.collections.CollectionsKt___CollectionsKt;
import kotlin.collections.SetsKt__SetsKt;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.CoroutineContext;
import kotlin.coroutines.intrinsics.IntrinsicsKt__IntrinsicsKt;
import kotlin.coroutines.jvm.internal.ContinuationImpl;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Ref;
import kotlin.jvm.internal.Reflection;
import kotlinx.coroutines.BuildersKt__Builders_commonKt;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.CoroutineScopeKt;
import kotlinx.coroutines.Dispatchers;
import kotlinx.coroutines.Job;
import kotlinx.coroutines.JobKt__JobKt;
import kotlinx.coroutines.SupervisorKt;
import kotlinx.coroutines.channels.Channel;
import kotlinx.coroutines.channels.ChannelKt;
import kotlinx.coroutines.channels.ReceiveChannel;
import rx.Observable;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;
import rx.schedulers.Schedulers;

/* JADX INFO: compiled from: BaseViewModelAdapterProvider.kt */
/* JADX INFO: loaded from: classes4.dex */
@Metadata(d1 = {"\u0000x\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010 \n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\"\n\u0002\b\u0002\n\u0002\u0010\u0011\n\u0002\u0018\u0002\n\u0002\b\n\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\b\n\u0002\b\u0004\b\u0016\u0018\u0000 22\u00020\u0001:\u00012B\u000f\b\u0016\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004B1\u0012\b\u0010\u0002\u001a\u0004\u0018\u00010\u0003\u0012\n\b\u0002\u0010\u0005\u001a\u0004\u0018\u00010\u0006\u0012\n\b\u0002\u0010\u0007\u001a\u0004\u0018\u00010\b\u0012\b\b\u0002\u0010\t\u001a\u00020\n¢\u0006\u0002\u0010\u000bJ*\u0010\u0012\u001a\u00020\u00132\u0006\u0010\u0014\u001a\u00020\u00152\f\u0010\u0016\u001a\b\u0012\u0004\u0012\u00020\u00110\u00172\n\b\u0002\u0010\u0018\u001a\u0004\u0018\u00010\u0019H\u0016J\u0016\u0010\u001a\u001a\u00020\u00132\f\u0010\u001b\u001a\b\u0012\u0004\u0012\u00020\u00010\u001cH\u0004J=\u0010\u001d\u001a\u00020\u00152\f\u0010\u0016\u001a\b\u0012\u0004\u0012\u00020\u00110\u00172\"\u0010\u001e\u001a\u0012\u0012\u000e\b\u0001\u0012\n\u0012\u0002\b\u0003\u0012\u0002\b\u00030 0\u001f\"\n\u0012\u0002\b\u0003\u0012\u0002\b\u00030 ¢\u0006\u0002\u0010!J\u0018\u0010\"\u001a\u00020\u00132\u0006\u0010\u0014\u001a\u00020\u00152\u0006\u0010#\u001a\u00020\u0011H\u0002J\u0006\u0010$\u001a\u00020\u0013J\u001c\u0010%\u001a\u00020\u00132\u0006\u0010\u0014\u001a\u00020\u00152\f\u0010\u0016\u001a\b\u0012\u0004\u0012\u00020\u00110\u0017J \u0010&\u001a\u00020\u00132\u0006\u0010\u0014\u001a\u00020\u00152\u000e\u0010\u0016\u001a\n\u0012\u0004\u0012\u00020\u0011\u0018\u00010\u0017H\u0004J\u0011\u0010'\u001a\u00020\u0013H\u0082@ø\u0001\u0000¢\u0006\u0002\u0010(J\u001c\u0010)\u001a\u00020\u00132\u0006\u0010\u0014\u001a\u00020\u00152\f\u0010\u0016\u001a\b\u0012\u0004\u0012\u00020\u00110\u0017J.\u0010)\u001a\u00020\u00132\u0006\u0010\u0014\u001a\u00020\u00152\f\u0010\u0016\u001a\b\u0012\u0004\u0012\u00020\u00110\u00172\u0006\u0010*\u001a\u00020+2\b\b\u0002\u0010,\u001a\u00020\nJ.\u0010-\u001a\u00020\u00132\u0006\u0010\u0014\u001a\u00020\u00152\f\u0010\u0016\u001a\b\u0012\u0004\u0012\u00020\u00110\u00172\u0006\u0010.\u001a\u00020/2\u0006\u00100\u001a\u00020/H\u0016J\b\u00101\u001a\u00020\nH\u0004R\u000e\u0010\f\u001a\u00020\rX\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\u0005\u001a\u0004\u0018\u00010\u0006X\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\u0002\u001a\u0004\u0018\u00010\u0003X\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\u0007\u001a\u0004\u0018\u00010\bX\u0082\u0004¢\u0006\u0002\n\u0000R\u001c\u0010\u000e\u001a\u0010\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00110\u0010\u0018\u00010\u000fX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\t\u001a\u00020\nX\u0082\u0004¢\u0006\u0002\n\u0000\u0082\u0002\u0004\n\u0002\b\u0019¨\u00063"}, d2 = {"Lcom/amazon/music/views/library/recyclerview/BaseViewModelAdapterProvider;", "", "lifecycleOwner", "Landroidx/lifecycle/LifecycleOwner;", "(Landroidx/lifecycle/LifecycleOwner;)V", "itemMovedListener", "Lcom/amazon/music/views/library/recyclerview/adapters/UniversalAdapter$ItemMovedListener;", "lifecycleScope", "Lkotlinx/coroutines/CoroutineScope;", "useOptimizedObservable", "", "(Landroidx/lifecycle/LifecycleOwner;Lcom/amazon/music/views/library/recyclerview/adapters/UniversalAdapter$ItemMovedListener;Lkotlinx/coroutines/CoroutineScope;Z)V", "coroutineContext", "Lkotlin/coroutines/CoroutineContext;", "updatedPayloadChannel", "Lkotlinx/coroutines/channels/Channel;", "Lcom/amazon/music/views/library/recyclerview/adapters/BaseModelChange;", "Lcom/amazon/music/views/library/models/base/BaseViewModel;", "addItems", "", "adapter", "Lcom/amazon/music/views/library/recyclerview/adapters/UniversalAdapter;", "models", "", "diffResult", "Landroidx/recyclerview/widget/DiffUtil$DiffResult;", "clearOldObservables", "previousModels", "", "getAdapter", "binders", "", "Lcom/amazon/music/views/library/binders/UniversalBinder;", "(Ljava/util/List;[Lcom/amazon/music/views/library/binders/UniversalBinder;)Lcom/amazon/music/views/library/recyclerview/adapters/UniversalAdapter;", "observeModelChange", "model", "onCleanup", "removeItems", "setupChangeObservableForModels", "startChannelForUpdates", "(Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "updateItems", "diffUtilCallback", "Landroidx/recyclerview/widget/DiffUtil$Callback;", "detectMoves", "updateItemsInRange", "positionStart", "", "itemCount", "useUpdateChannel", "Companion", "DMMViewLibrary_release"}, k = 1, mv = {1, 6, 0}, xi = 48)
public class BaseViewModelAdapterProvider {
    private static final String TAG = Reflection.getOrCreateKotlinClass(BaseViewModelAdapterProvider.class).getSimpleName();
    private CoroutineContext coroutineContext;
    private UniversalAdapter.ItemMovedListener itemMovedListener;
    private LifecycleOwner lifecycleOwner;
    private final CoroutineScope lifecycleScope;
    private Channel<BaseModelChange<BaseViewModel>> updatedPayloadChannel;
    private final boolean useOptimizedObservable;

    /* JADX INFO: renamed from: com.amazon.music.views.library.recyclerview.BaseViewModelAdapterProvider$startChannelForUpdates$1, reason: invalid class name and case insensitive filesystem */
    /* JADX INFO: compiled from: BaseViewModelAdapterProvider.kt */
    @Metadata(k = 3, mv = {1, 6, 0}, xi = 48)
    @DebugMetadata(c = "com.amazon.music.views.library.recyclerview.BaseViewModelAdapterProvider", f = "BaseViewModelAdapterProvider.kt", i = {0}, l = {58}, m = "startChannelForUpdates", n = {"this"}, s = {"L$0"})
    static final class C04561 extends ContinuationImpl {
        Object L$0;
        int label;
        /* synthetic */ Object result;

        C04561(Continuation<? super C04561> continuation) {
            super(continuation);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object obj) {
            this.result = obj;
            this.label |= Integer.MIN_VALUE;
            return BaseViewModelAdapterProvider.this.startChannelForUpdates(this);
        }
    }

    public BaseViewModelAdapterProvider(LifecycleOwner lifecycleOwner, UniversalAdapter.ItemMovedListener itemMovedListener, CoroutineScope coroutineScope, boolean z) {
        this.lifecycleOwner = lifecycleOwner;
        this.itemMovedListener = itemMovedListener;
        this.lifecycleScope = coroutineScope;
        this.useOptimizedObservable = z;
        this.coroutineContext = Dispatchers.getIO().plus(SupervisorKt.SupervisorJob$default((Job) null, 1, (Object) null));
        this.updatedPayloadChannel = ChannelKt.Channel$default(0, null, null, 7, null);
        if (!useUpdateChannel() || coroutineScope == null) {
            return;
        }
        BuildersKt__Builders_commonKt.launch$default(coroutineScope, this.coroutineContext, null, new AnonymousClass1(null), 2, null);
    }

    public /* synthetic */ BaseViewModelAdapterProvider(LifecycleOwner lifecycleOwner, UniversalAdapter.ItemMovedListener itemMovedListener, CoroutineScope coroutineScope, boolean z, int i, DefaultConstructorMarker defaultConstructorMarker) {
        this(lifecycleOwner, (i & 2) != 0 ? null : itemMovedListener, (i & 4) != 0 ? null : coroutineScope, (i & 8) != 0 ? false : z);
    }

    /* JADX WARN: 'this' call moved to the top of the method (can break code semantics) */
    public BaseViewModelAdapterProvider(LifecycleOwner lifecycleOwner) {
        this(lifecycleOwner, null, null, false, 12, null);
        Intrinsics.checkNotNullParameter(lifecycleOwner, "lifecycleOwner");
    }

    /* JADX INFO: renamed from: com.amazon.music.views.library.recyclerview.BaseViewModelAdapterProvider$1, reason: invalid class name */
    /* JADX INFO: compiled from: BaseViewModelAdapterProvider.kt */
    @Metadata(bv = {}, d1 = {"\u0000\n\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0000\u0010\u0002\u001a\u00020\u0001*\u00020\u0000H\u008a@"}, d2 = {"Lkotlinx/coroutines/CoroutineScope;", "", "<anonymous>"}, k = 3, mv = {1, 6, 0})
    @DebugMetadata(c = "com.amazon.music.views.library.recyclerview.BaseViewModelAdapterProvider$1", f = "BaseViewModelAdapterProvider.kt", i = {}, l = {45}, m = "invokeSuspend", n = {}, s = {})
    static final class AnonymousClass1 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
        int label;

        AnonymousClass1(Continuation<? super AnonymousClass1> continuation) {
            super(2, continuation);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
            return BaseViewModelAdapterProvider.this.new AnonymousClass1(continuation);
        }

        @Override // kotlin.jvm.functions.Function2
        /* JADX INFO: renamed from: invoke, reason: avoid collision after fix types in other method and merged with bridge method [inline-methods] */
        public final Object mo4invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
            return ((AnonymousClass1) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object obj) {
            Object coroutine_suspended = IntrinsicsKt__IntrinsicsKt.getCOROUTINE_SUSPENDED();
            int i = this.label;
            if (i == 0) {
                ResultKt.throwOnFailure(obj);
                BaseViewModelAdapterProvider baseViewModelAdapterProvider = BaseViewModelAdapterProvider.this;
                this.label = 1;
                if (baseViewModelAdapterProvider.startChannelForUpdates(this) == coroutine_suspended) {
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

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Removed duplicated region for block: B:22:0x0041  */
    /* JADX WARN: Removed duplicated region for block: B:26:0x004a A[Catch: Exception -> 0x002d, TRY_ENTER, TryCatch #0 {Exception -> 0x002d, blocks: (B:12:0x0029, B:32:0x005a, B:26:0x004a, B:29:0x004f, B:35:0x005f), top: B:40:0x0029 }] */
    /* JADX WARN: Removed duplicated region for block: B:31:0x0059 A[RETURN] */
    /* JADX WARN: Removed duplicated region for block: B:34:0x005e  */
    /* JADX WARN: Removed duplicated region for block: B:35:0x005f A[Catch: Exception -> 0x002d, TRY_LEAVE, TryCatch #0 {Exception -> 0x002d, blocks: (B:12:0x0029, B:32:0x005a, B:26:0x004a, B:29:0x004f, B:35:0x005f), top: B:40:0x0029 }] */
    /* JADX WARN: Removed duplicated region for block: B:42:0x007f A[SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:7:0x0013  */
    /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:30:0x0057 -> B:32:0x005a). Please report as a decompilation issue!!! */
    /*  JADX ERROR: JadxOverflowException in pass: RegionMakerVisitor
        jadx.core.utils.exceptions.JadxOverflowException: Regions count limit reached
        	at jadx.core.utils.ErrorsCounter.addError(ErrorsCounter.java:59)
        	at jadx.core.utils.ErrorsCounter.error(ErrorsCounter.java:31)
        	at jadx.core.dex.attributes.nodes.NotificationAttrNode.addError(NotificationAttrNode.java:19)
        */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final java.lang.Object startChannelForUpdates(kotlin.coroutines.Continuation<? super kotlin.Unit> r7) {
        /*
            r6 = this;
            boolean r0 = r7 instanceof com.amazon.music.views.library.recyclerview.BaseViewModelAdapterProvider.C04561
            if (r0 == 0) goto L13
            r0 = r7
            com.amazon.music.views.library.recyclerview.BaseViewModelAdapterProvider$startChannelForUpdates$1 r0 = (com.amazon.music.views.library.recyclerview.BaseViewModelAdapterProvider.C04561) r0
            int r1 = r0.label
            r2 = -2147483648(0xffffffff80000000, float:-0.0)
            r3 = r1 & r2
            if (r3 == 0) goto L13
            int r1 = r1 - r2
            r0.label = r1
            goto L18
        L13:
            com.amazon.music.views.library.recyclerview.BaseViewModelAdapterProvider$startChannelForUpdates$1 r0 = new com.amazon.music.views.library.recyclerview.BaseViewModelAdapterProvider$startChannelForUpdates$1
            r0.<init>(r7)
        L18:
            java.lang.Object r7 = r0.result
            java.lang.Object r1 = kotlin.coroutines.intrinsics.IntrinsicsKt.getCOROUTINE_SUSPENDED()
            int r2 = r0.label
            r3 = 1
            if (r2 == 0) goto L37
            if (r2 != r3) goto L2f
            java.lang.Object r2 = r0.L$0
            com.amazon.music.views.library.recyclerview.BaseViewModelAdapterProvider r2 = (com.amazon.music.views.library.recyclerview.BaseViewModelAdapterProvider) r2
            kotlin.ResultKt.throwOnFailure(r7)     // Catch: java.lang.Exception -> L2d
            goto L5a
        L2d:
            r7 = move-exception
            goto L6f
        L2f:
            java.lang.IllegalStateException r7 = new java.lang.IllegalStateException
            java.lang.String r0 = "call to 'resume' before 'invoke' with coroutine"
            r7.<init>(r0)
            throw r7
        L37:
            kotlin.ResultKt.throwOnFailure(r7)
            r2 = r6
        L3b:
            kotlinx.coroutines.channels.Channel<com.amazon.music.views.library.recyclerview.adapters.BaseModelChange<com.amazon.music.views.library.models.base.BaseViewModel>> r7 = r2.updatedPayloadChannel
            r4 = 0
            if (r7 != 0) goto L41
            goto L48
        L41:
            boolean r7 = r7.isClosedForReceive()
            if (r7 != 0) goto L48
            r4 = r3
        L48:
            if (r4 == 0) goto L7f
            kotlinx.coroutines.channels.Channel<com.amazon.music.views.library.recyclerview.adapters.BaseModelChange<com.amazon.music.views.library.models.base.BaseViewModel>> r7 = r2.updatedPayloadChannel     // Catch: java.lang.Exception -> L2d
            if (r7 != 0) goto L4f
            goto L3b
        L4f:
            r0.L$0 = r2     // Catch: java.lang.Exception -> L2d
            r0.label = r3     // Catch: java.lang.Exception -> L2d
            java.lang.Object r7 = r7.receive(r0)     // Catch: java.lang.Exception -> L2d
            if (r7 != r1) goto L5a
            return r1
        L5a:
            com.amazon.music.views.library.recyclerview.adapters.BaseModelChange r7 = (com.amazon.music.views.library.recyclerview.adapters.BaseModelChange) r7     // Catch: java.lang.Exception -> L2d
            if (r7 != 0) goto L5f
            goto L3b
        L5f:
            com.amazon.music.views.library.recyclerview.adapters.UniversalAdapter r4 = r7.getAdapter()     // Catch: java.lang.Exception -> L2d
            com.amazon.music.views.library.models.base.BaseViewModel r5 = r7.getModel()     // Catch: java.lang.Exception -> L2d
            int r7 = r7.getPayload()     // Catch: java.lang.Exception -> L2d
            r4.notifyModelChanged$DMMViewLibrary_release(r5, r7)     // Catch: java.lang.Exception -> L2d
            goto L3b
        L6f:
            java.lang.String r4 = com.amazon.music.views.library.recyclerview.BaseViewModelAdapterProvider.TAG
            org.slf4j.Logger r4 = org.slf4j.LoggerFactory.getLogger(r4)
            java.lang.String r5 = "Error in receiving payload change "
            java.lang.String r7 = kotlin.jvm.internal.Intrinsics.stringPlus(r5, r7)
            r4.debug(r7)
            goto L3b
        L7f:
            kotlin.Unit r7 = kotlin.Unit.INSTANCE
            return r7
        */
        throw new UnsupportedOperationException("Method not decompiled: com.amazon.music.views.library.recyclerview.BaseViewModelAdapterProvider.startChannelForUpdates(kotlin.coroutines.Continuation):java.lang.Object");
    }

    public final synchronized UniversalAdapter getAdapter(List<? extends BaseViewModel> models, UniversalBinder<?, ?>... binders) {
        UniversalAdapter universalAdapter;
        Intrinsics.checkNotNullParameter(models, "models");
        Intrinsics.checkNotNullParameter(binders, "binders");
        universalAdapter = new UniversalAdapter((UniversalBinder[]) Arrays.copyOf(binders, binders.length));
        universalAdapter.setItemMovedListener(this.itemMovedListener);
        universalAdapter.allowRebind(true);
        addItems$default(this, universalAdapter, models, null, 4, null);
        return universalAdapter;
    }

    public static /* synthetic */ void addItems$default(BaseViewModelAdapterProvider baseViewModelAdapterProvider, UniversalAdapter universalAdapter, List list, DiffUtil.DiffResult diffResult, int i, Object obj) {
        if (obj != null) {
            throw new UnsupportedOperationException("Super calls with default arguments not supported in this target, function: addItems");
        }
        if ((i & 4) != 0) {
            diffResult = null;
        }
        baseViewModelAdapterProvider.addItems(universalAdapter, list, diffResult);
    }

    public synchronized void addItems(UniversalAdapter adapter, List<? extends BaseViewModel> models, DiffUtil.DiffResult diffResult) {
        Intrinsics.checkNotNullParameter(adapter, "adapter");
        Intrinsics.checkNotNullParameter(models, "models");
        List<? extends BaseViewModel> listAddItems$default = UniversalAdapter.addItems$default(adapter, models, diffResult, false, 4, null);
        if (!(listAddItems$default instanceof List)) {
            listAddItems$default = null;
        }
        setupChangeObservableForModels(adapter, listAddItems$default);
    }

    /* JADX INFO: renamed from: com.amazon.music.views.library.recyclerview.BaseViewModelAdapterProvider$setupChangeObservableForModels$1, reason: invalid class name and case insensitive filesystem */
    /* JADX INFO: compiled from: BaseViewModelAdapterProvider.kt */
    @Metadata(bv = {}, d1 = {"\u0000\n\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0000\u0010\u0002\u001a\u00020\u0001*\u00020\u0000H\u008a@"}, d2 = {"Lkotlinx/coroutines/CoroutineScope;", "", "<anonymous>"}, k = 3, mv = {1, 6, 0})
    @DebugMetadata(c = "com.amazon.music.views.library.recyclerview.BaseViewModelAdapterProvider$setupChangeObservableForModels$1", f = "BaseViewModelAdapterProvider.kt", i = {}, l = {}, m = "invokeSuspend", n = {}, s = {})
    static final class C04551 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
        final /* synthetic */ UniversalAdapter $adapter;
        final /* synthetic */ List<BaseViewModel> $models;
        int label;
        final /* synthetic */ BaseViewModelAdapterProvider this$0;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        /* JADX WARN: Multi-variable type inference failed */
        C04551(List<? extends BaseViewModel> list, BaseViewModelAdapterProvider baseViewModelAdapterProvider, UniversalAdapter universalAdapter, Continuation<? super C04551> continuation) {
            super(2, continuation);
            this.$models = list;
            this.this$0 = baseViewModelAdapterProvider;
            this.$adapter = universalAdapter;
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
            return new C04551(this.$models, this.this$0, this.$adapter, continuation);
        }

        @Override // kotlin.jvm.functions.Function2
        /* JADX INFO: renamed from: invoke, reason: avoid collision after fix types in other method and merged with bridge method [inline-methods] */
        public final Object mo4invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
            return ((C04551) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object obj) {
            IntrinsicsKt__IntrinsicsKt.getCOROUTINE_SUSPENDED();
            if (this.label != 0) {
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
            ResultKt.throwOnFailure(obj);
            List<BaseViewModel> list = this.$models;
            if (list != null) {
                BaseViewModelAdapterProvider baseViewModelAdapterProvider = this.this$0;
                UniversalAdapter universalAdapter = this.$adapter;
                for (BaseViewModel baseViewModel : list) {
                    baseViewModel.setModelChangeChannel$DMMViewLibrary_release(baseViewModelAdapterProvider.updatedPayloadChannel);
                    baseViewModel.setContextScope$DMMViewLibrary_release(baseViewModelAdapterProvider.lifecycleScope);
                    baseViewModel.setAdapterRef$DMMViewLibrary_release(universalAdapter);
                }
            }
            return Unit.INSTANCE;
        }
    }

    protected final void setupChangeObservableForModels(UniversalAdapter adapter, List<? extends BaseViewModel> models) {
        Intrinsics.checkNotNullParameter(adapter, "adapter");
        if (useUpdateChannel()) {
            CoroutineScope coroutineScope = this.lifecycleScope;
            if (coroutineScope == null) {
                return;
            }
            BuildersKt__Builders_commonKt.launch$default(coroutineScope, this.coroutineContext, null, new C04551(models, this, adapter, null), 2, null);
            return;
        }
        if (models == null) {
            return;
        }
        Iterator<T> it = models.iterator();
        while (it.hasNext()) {
            observeModelChange(adapter, (BaseViewModel) it.next());
        }
    }

    public final synchronized void updateItems(UniversalAdapter adapter, List<? extends BaseViewModel> models) {
        Intrinsics.checkNotNullParameter(adapter, "adapter");
        Intrinsics.checkNotNullParameter(models, "models");
        Set<? extends Object> setMutableSetOf = null;
        if (!useUpdateChannel()) {
            setMutableSetOf = SetsKt__SetsKt.mutableSetOf(Integer.valueOf(adapter.getItems().size()));
            setMutableSetOf.addAll(adapter.getItems());
        }
        adapter.clear(false);
        addItems$default(this, adapter, models, null, 4, null);
        if (setMutableSetOf != null) {
            setMutableSetOf.removeAll(adapter.getItems());
            clearOldObservables(setMutableSetOf);
        }
    }

    public final void removeItems(UniversalAdapter adapter, List<? extends BaseViewModel> models) {
        Intrinsics.checkNotNullParameter(adapter, "adapter");
        Intrinsics.checkNotNullParameter(models, "models");
        Set<? extends Object> mutableSet = !useUpdateChannel() ? CollectionsKt___CollectionsKt.toMutableSet(adapter.getItems()) : null;
        adapter.removeItems(models);
        if (mutableSet == null) {
            return;
        }
        mutableSet.removeAll(CollectionsKt___CollectionsKt.toSet(adapter.getItems()));
        clearOldObservables(mutableSet);
    }

    public synchronized void updateItemsInRange(UniversalAdapter adapter, List<? extends BaseViewModel> models, int positionStart, int itemCount) {
        Intrinsics.checkNotNullParameter(adapter, "adapter");
        Intrinsics.checkNotNullParameter(models, "models");
        Set<? extends Object> setMutableSetOf = null;
        if (!useUpdateChannel()) {
            setMutableSetOf = SetsKt__SetsKt.mutableSetOf(Integer.valueOf(adapter.getItems().size()));
            setMutableSetOf.addAll(adapter.getItems());
        }
        adapter.clear(false);
        UniversalAdapter.updateItemsInRange$default(adapter, models, positionStart, itemCount, null, 8, null);
        setupChangeObservableForModels(adapter, models);
        if (setMutableSetOf != null) {
            setMutableSetOf.removeAll(adapter.getItems());
            clearOldObservables(setMutableSetOf);
        }
    }

    /* JADX WARN: Type inference failed for: r1v4, types: [T, java.util.Set] */
    public final synchronized void updateItems(final UniversalAdapter adapter, final List<? extends BaseViewModel> models, final DiffUtil.Callback diffUtilCallback, final boolean detectMoves) {
        Intrinsics.checkNotNullParameter(adapter, "adapter");
        Intrinsics.checkNotNullParameter(models, "models");
        Intrinsics.checkNotNullParameter(diffUtilCallback, "diffUtilCallback");
        final Ref.ObjectRef objectRef = new Ref.ObjectRef();
        if (!useUpdateChannel()) {
            ?? MutableSetOf = SetsKt__SetsKt.mutableSetOf(Integer.valueOf(adapter.getItems().size()));
            objectRef.element = MutableSetOf;
            ((Set) MutableSetOf).addAll(adapter.getItems());
        }
        Observable.create(new Observable.OnSubscribe() { // from class: com.amazon.music.views.library.recyclerview.BaseViewModelAdapterProvider$$ExternalSyntheticLambda1
            @Override // rx.functions.Action1
            public final void call(Object obj) {
                BaseViewModelAdapterProvider.m2378updateItems$lambda6(diffUtilCallback, detectMoves, (Subscriber) obj);
            }
        }).subscribeOn(Schedulers.computation()).observeOn(AndroidSchedulers.mainThread()).subscribe(new Action1() { // from class: com.amazon.music.views.library.recyclerview.BaseViewModelAdapterProvider$$ExternalSyntheticLambda2
            @Override // rx.functions.Action1
            public final void call(Object obj) {
                BaseViewModelAdapterProvider.m2379updateItems$lambda8(adapter, this, models, objectRef, (DiffUtil.DiffResult) obj);
            }
        }, new Action1() { // from class: com.amazon.music.views.library.recyclerview.BaseViewModelAdapterProvider$$ExternalSyntheticLambda3
            @Override // rx.functions.Action1
            public final void call(Object obj) {
                BaseViewModelAdapterProvider.m2380updateItems$lambda9(this.f$0, adapter, models, (Throwable) obj);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: updateItems$lambda-6, reason: not valid java name */
    public static final void m2378updateItems$lambda6(DiffUtil.Callback diffUtilCallback, boolean z, Subscriber subscriber) {
        Intrinsics.checkNotNullParameter(diffUtilCallback, "$diffUtilCallback");
        DiffUtil.DiffResult diffResultCalculateDiff = DiffUtil.calculateDiff(diffUtilCallback, z);
        Intrinsics.checkNotNullExpressionValue(diffResultCalculateDiff, "calculateDiff(diffUtilCallback, detectMoves)");
        subscriber.onNext(diffResultCalculateDiff);
        subscriber.onCompleted();
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: updateItems$lambda-8, reason: not valid java name */
    public static final void m2379updateItems$lambda8(UniversalAdapter adapter, BaseViewModelAdapterProvider this$0, List models, Ref.ObjectRef previousModels, DiffUtil.DiffResult diffResult) {
        Intrinsics.checkNotNullParameter(adapter, "$adapter");
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        Intrinsics.checkNotNullParameter(models, "$models");
        Intrinsics.checkNotNullParameter(previousModels, "$previousModels");
        adapter.clear(false);
        this$0.addItems(adapter, models, diffResult);
        T t = previousModels.element;
        if (((Set) t) == null) {
            return;
        }
        ((Set) t).removeAll(adapter.getItems());
        this$0.clearOldObservables((Set) previousModels.element);
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: updateItems$lambda-9, reason: not valid java name */
    public static final void m2380updateItems$lambda9(BaseViewModelAdapterProvider this$0, UniversalAdapter adapter, List models, Throwable th) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        Intrinsics.checkNotNullParameter(adapter, "$adapter");
        Intrinsics.checkNotNullParameter(models, "$models");
        this$0.updateItems(adapter, models);
        Log.e(TAG, Intrinsics.stringPlus("There is an error occurred during DiffUtil calculation. The message: ", th.getMessage()));
    }

    private final void observeModelChange(final UniversalAdapter adapter, final BaseViewModel model) {
        LifecycleOwner lifecycleOwner = this.lifecycleOwner;
        if (lifecycleOwner == null) {
            return;
        }
        model.getChangedObservable$DMMViewLibrary_release().observe(lifecycleOwner, new Observer() { // from class: com.amazon.music.views.library.recyclerview.BaseViewModelAdapterProvider$$ExternalSyntheticLambda0
            @Override // androidx.lifecycle.Observer
            public final void onChanged(Object obj) {
                BaseViewModelAdapterProvider.m2377observeModelChange$lambda12$lambda11(adapter, model, (Integer) obj);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: observeModelChange$lambda-12$lambda-11, reason: not valid java name */
    public static final void m2377observeModelChange$lambda12$lambda11(UniversalAdapter adapter, BaseViewModel model, Integer num) {
        Intrinsics.checkNotNullParameter(adapter, "$adapter");
        Intrinsics.checkNotNullParameter(model, "$model");
        if (num == null) {
            return;
        }
        adapter.notifyModelChanged$DMMViewLibrary_release(model, num.intValue());
    }

    protected final void clearOldObservables(Set<? extends Object> previousModels) {
        LifecycleOwner lifecycleOwner;
        Intrinsics.checkNotNullParameter(previousModels, "previousModels");
        for (Object obj : previousModels) {
            if ((obj instanceof BaseViewModel) && (lifecycleOwner = this.lifecycleOwner) != null) {
                ((BaseViewModel) obj).getChangedObservable$DMMViewLibrary_release().removeObservers(lifecycleOwner);
            }
        }
    }

    protected final boolean useUpdateChannel() {
        return this.useOptimizedObservable && this.lifecycleScope != null;
    }

    public final void onCleanup() {
        Channel<BaseModelChange<BaseViewModel>> channel = this.updatedPayloadChannel;
        if (channel != null) {
            ReceiveChannel.DefaultImpls.cancel$default((ReceiveChannel) channel, (CancellationException) null, 1, (Object) null);
        }
        JobKt__JobKt.cancelChildren$default(this.coroutineContext, (CancellationException) null, 1, (Object) null);
        CoroutineScope coroutineScope = this.lifecycleScope;
        if (coroutineScope != null) {
            CoroutineScopeKt.cancel$default(coroutineScope, null, 1, null);
        }
        this.updatedPayloadChannel = null;
        this.lifecycleOwner = null;
    }
}
