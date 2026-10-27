package com.amazon.music.views.library.models.base;

import androidx.lifecycle.MutableLiveData;
import com.amazon.music.find.utils.ContentUtils;
import com.amazon.music.views.library.recyclerview.adapters.BaseModelChange;
import com.amazon.music.views.library.recyclerview.adapters.UniversalAdapter;
import java.lang.ref.WeakReference;
import java.util.Collections;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;
import kotlin.Lazy;
import kotlin.LazyKt__LazyJVMKt;
import kotlin.Metadata;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.CoroutineContext;
import kotlin.coroutines.intrinsics.IntrinsicsKt__IntrinsicsKt;
import kotlin.coroutines.jvm.internal.ContinuationImpl;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.BuildersKt__Builders_commonKt;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.Dispatchers;
import kotlinx.coroutines.Job;
import kotlinx.coroutines.SupervisorKt;
import kotlinx.coroutines.channels.Channel;

/* JADX INFO: compiled from: BaseViewModel.kt */
/* JADX INFO: loaded from: classes4.dex */
@Metadata(d1 = {"\u0000f\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010 \n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\b\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010#\n\u0000\n\u0002\u0010\"\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0002\b\u0011\b&\u0018\u00002\u00020\u0001B#\b\u0004\u0012\b\u0010\u0002\u001a\u0004\u0018\u00010\u0003\u0012\u0010\b\u0002\u0010\u0004\u001a\n\u0012\u0004\u0012\u00020\u0003\u0018\u00010\u0005¢\u0006\u0002\u0010\u0006J\u0012\u0010(\u001a\u00020)2\b\u0010*\u001a\u0004\u0018\u00010\u0001H\u0016J\u0010\u0010+\u001a\u00020,2\u0006\u0010-\u001a\u00020\u0000H\u0016J\u0013\u0010.\u001a\u00020)2\b\u0010*\u001a\u0004\u0018\u00010\u0001H\u0096\u0002J\u0013\u0010/\u001a\b\u0012\u0004\u0012\u00020\f0\u000bH\u0000¢\u0006\u0002\b0J\b\u00101\u001a\u00020\fH\u0016J\u0010\u00102\u001a\u00020,2\u0006\u00103\u001a\u00020\fH\u0014J\u0011\u00104\u001a\u00020,H\u0082@ø\u0001\u0000¢\u0006\u0002\u00105J\u0017\u00106\u001a\u00020,2\b\u00107\u001a\u0004\u0018\u00010\tH\u0000¢\u0006\u0002\b8J\u0017\u00109\u001a\u00020,2\b\u0010:\u001a\u0004\u0018\u00010\u0012H\u0000¢\u0006\u0002\b\u0015J#\u0010;\u001a\u00020,2\u0014\u0010#\u001a\u0010\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00000%\u0018\u00010$H\u0000¢\u0006\u0002\b<R\u0016\u0010\u0007\u001a\n\u0012\u0004\u0012\u00020\t\u0018\u00010\bX\u0082\u000e¢\u0006\u0002\n\u0000R!\u0010\n\u001a\b\u0012\u0004\u0012\u00020\f0\u000b8BX\u0082\u0084\u0002¢\u0006\f\n\u0004\b\u000f\u0010\u0010\u001a\u0004\b\r\u0010\u000eR\"\u0010\u0011\u001a\n\u0012\u0004\u0012\u00020\u0012\u0018\u00010\bX\u0080\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0013\u0010\u0014\"\u0004\b\u0015\u0010\u0016R\u000e\u0010\u0017\u001a\u00020\u0018X\u0082\u0004¢\u0006\u0002\n\u0000R8\u0010\u0019\u001a&\u0012\f\u0012\n \u001b*\u0004\u0018\u00010\f0\f \u001b*\u0012\u0012\f\u0012\n \u001b*\u0004\u0018\u00010\f0\f\u0018\u00010\u001c0\u001aX\u0080\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u001d\u0010\u001eR\"\u0010\u0004\u001a\n\u0012\u0004\u0012\u00020\u0003\u0018\u00010\u0005X\u0096\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u001f\u0010 \"\u0004\b!\u0010\"R\"\u0010#\u001a\u0016\u0012\u0010\u0012\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00000%0$\u0018\u00010\bX\u0082\u000e¢\u0006\u0002\n\u0000R\u0013\u0010\u0002\u001a\u0004\u0018\u00010\u0003¢\u0006\b\n\u0000\u001a\u0004\b&\u0010'\u0082\u0002\u0004\n\u0002\b\u0019¨\u0006="}, d2 = {"Lcom/amazon/music/views/library/models/base/BaseViewModel;", "", ContentUtils.KEY_VIEW_REFERENCE_ID, "", "tags", "", "(Ljava/lang/String;Ljava/util/List;)V", "adapterRef", "Ljava/lang/ref/WeakReference;", "Lcom/amazon/music/views/library/recyclerview/adapters/UniversalAdapter;", "changeObservable", "Landroidx/lifecycle/MutableLiveData;", "", "getChangeObservable", "()Landroidx/lifecycle/MutableLiveData;", "changeObservable$delegate", "Lkotlin/Lazy;", "contextScope", "Lkotlinx/coroutines/CoroutineScope;", "getContextScope$DMMViewLibrary_release", "()Ljava/lang/ref/WeakReference;", "setContextScope$DMMViewLibrary_release", "(Ljava/lang/ref/WeakReference;)V", "coroutineContext", "Lkotlin/coroutines/CoroutineContext;", "payloads", "", "kotlin.jvm.PlatformType", "", "getPayloads$DMMViewLibrary_release", "()Ljava/util/Set;", "getTags", "()Ljava/util/List;", "setTags", "(Ljava/util/List;)V", "updateChannel", "Lkotlinx/coroutines/channels/Channel;", "Lcom/amazon/music/views/library/recyclerview/adapters/BaseModelChange;", "getViewReferenceId", "()Ljava/lang/String;", "areItemsTheSame", "", "other", "checkForChanges", "", "newModelState", "equals", "getChangedObservable", "getChangedObservable$DMMViewLibrary_release", "hashCode", "notifyChanged", "payload", "sendBaseModelChange", "(Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "setAdapterRef", "adapter", "setAdapterRef$DMMViewLibrary_release", "setContextScope", "scope", "setModelChangeChannel", "setModelChangeChannel$DMMViewLibrary_release", "DMMViewLibrary_release"}, k = 1, mv = {1, 6, 0}, xi = 48)
public abstract class BaseViewModel {
    private transient WeakReference<UniversalAdapter> adapterRef;

    /* JADX INFO: renamed from: changeObservable$delegate, reason: from kotlin metadata */
    private final transient Lazy changeObservable;
    private transient WeakReference<CoroutineScope> contextScope;
    private final transient CoroutineContext coroutineContext;
    private final Set<Integer> payloads;
    private List<String> tags;
    private transient WeakReference<Channel<BaseModelChange<BaseViewModel>>> updateChannel;
    private final String viewReferenceId;

    /* JADX INFO: renamed from: com.amazon.music.views.library.models.base.BaseViewModel$sendBaseModelChange$1, reason: invalid class name and case insensitive filesystem */
    /* JADX INFO: compiled from: BaseViewModel.kt */
    @Metadata(k = 3, mv = {1, 6, 0}, xi = 48)
    @DebugMetadata(c = "com.amazon.music.views.library.models.base.BaseViewModel", f = "BaseViewModel.kt", i = {}, l = {76}, m = "sendBaseModelChange", n = {}, s = {})
    static final class C04541 extends ContinuationImpl {
        int label;
        /* synthetic */ Object result;

        C04541(Continuation<? super C04541> continuation) {
            super(continuation);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object obj) {
            this.result = obj;
            this.label |= Integer.MIN_VALUE;
            return BaseViewModel.this.sendBaseModelChange(this);
        }
    }

    public void checkForChanges(BaseViewModel newModelState) {
        Intrinsics.checkNotNullParameter(newModelState, "newModelState");
    }

    protected BaseViewModel(String str, List<String> list) {
        this.viewReferenceId = str;
        this.tags = list;
        this.changeObservable = LazyKt__LazyJVMKt.lazy(new Function0<MutableLiveData<Integer>>() { // from class: com.amazon.music.views.library.models.base.BaseViewModel$changeObservable$2
            /* JADX WARN: Can't rename method to resolve collision */
            @Override // kotlin.jvm.functions.Function0
            public final MutableLiveData<Integer> invoke() {
                return new MutableLiveData<>();
            }
        });
        this.coroutineContext = Dispatchers.getIO().plus(SupervisorKt.SupervisorJob$default((Job) null, 1, (Object) null));
        this.payloads = Collections.synchronizedSet(new LinkedHashSet());
    }

    public /* synthetic */ BaseViewModel(String str, List list, int i, DefaultConstructorMarker defaultConstructorMarker) {
        this(str, (i & 2) != 0 ? null : list);
    }

    public final String getViewReferenceId() {
        return this.viewReferenceId;
    }

    public List<String> getTags() {
        return this.tags;
    }

    public void setTags(List<String> list) {
        this.tags = list;
    }

    private final MutableLiveData<Integer> getChangeObservable() {
        return (MutableLiveData) this.changeObservable.getValue();
    }

    public final WeakReference<CoroutineScope> getContextScope$DMMViewLibrary_release() {
        return this.contextScope;
    }

    public final void setContextScope$DMMViewLibrary_release(WeakReference<CoroutineScope> weakReference) {
        this.contextScope = weakReference;
    }

    public final Set<Integer> getPayloads$DMMViewLibrary_release() {
        return this.payloads;
    }

    public final MutableLiveData<Integer> getChangedObservable$DMMViewLibrary_release() {
        return getChangeObservable();
    }

    public final void setModelChangeChannel$DMMViewLibrary_release(Channel<BaseModelChange<BaseViewModel>> updateChannel) {
        this.updateChannel = new WeakReference<>(updateChannel);
    }

    public final void setAdapterRef$DMMViewLibrary_release(UniversalAdapter adapter) {
        this.adapterRef = new WeakReference<>(adapter);
    }

    public final void setContextScope$DMMViewLibrary_release(CoroutineScope scope) {
        this.contextScope = new WeakReference<>(scope);
    }

    protected void notifyChanged(int payload) {
        CoroutineScope coroutineScope;
        Set<Integer> payloads = this.payloads;
        Intrinsics.checkNotNullExpressionValue(payloads, "payloads");
        payloads.add(Integer.valueOf(payload));
        WeakReference<Channel<BaseModelChange<BaseViewModel>>> weakReference = this.updateChannel;
        if ((weakReference == null ? null : weakReference.get()) != null) {
            WeakReference<CoroutineScope> weakReference2 = this.contextScope;
            if ((weakReference2 == null ? null : weakReference2.get()) != null) {
                WeakReference<CoroutineScope> weakReference3 = this.contextScope;
                if (weakReference3 == null || (coroutineScope = weakReference3.get()) == null) {
                    return;
                }
                BuildersKt__Builders_commonKt.launch$default(coroutineScope, this.coroutineContext, null, new AnonymousClass1(null), 2, null);
                return;
            }
        }
        getChangeObservable().postValue(-1);
    }

    /* JADX INFO: renamed from: com.amazon.music.views.library.models.base.BaseViewModel$notifyChanged$1, reason: invalid class name */
    /* JADX INFO: compiled from: BaseViewModel.kt */
    @Metadata(bv = {}, d1 = {"\u0000\n\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0000\u0010\u0002\u001a\u00020\u0001*\u00020\u0000H\u008a@"}, d2 = {"Lkotlinx/coroutines/CoroutineScope;", "", "<anonymous>"}, k = 3, mv = {1, 6, 0})
    @DebugMetadata(c = "com.amazon.music.views.library.models.base.BaseViewModel$notifyChanged$1", f = "BaseViewModel.kt", i = {}, l = {65}, m = "invokeSuspend", n = {}, s = {})
    static final class AnonymousClass1 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
        int label;

        AnonymousClass1(Continuation<? super AnonymousClass1> continuation) {
            super(2, continuation);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
            return BaseViewModel.this.new AnonymousClass1(continuation);
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
                BaseViewModel baseViewModel = BaseViewModel.this;
                this.label = 1;
                if (baseViewModel.sendBaseModelChange(this) == coroutine_suspended) {
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
    /* JADX WARN: Removed duplicated region for block: B:7:0x0013  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final java.lang.Object sendBaseModelChange(kotlin.coroutines.Continuation<? super kotlin.Unit> r7) {
        /*
            r6 = this;
            boolean r0 = r7 instanceof com.amazon.music.views.library.models.base.BaseViewModel.C04541
            if (r0 == 0) goto L13
            r0 = r7
            com.amazon.music.views.library.models.base.BaseViewModel$sendBaseModelChange$1 r0 = (com.amazon.music.views.library.models.base.BaseViewModel.C04541) r0
            int r1 = r0.label
            r2 = -2147483648(0xffffffff80000000, float:-0.0)
            r3 = r1 & r2
            if (r3 == 0) goto L13
            int r1 = r1 - r2
            r0.label = r1
            goto L18
        L13:
            com.amazon.music.views.library.models.base.BaseViewModel$sendBaseModelChange$1 r0 = new com.amazon.music.views.library.models.base.BaseViewModel$sendBaseModelChange$1
            r0.<init>(r7)
        L18:
            java.lang.Object r7 = r0.result
            java.lang.Object r1 = kotlin.coroutines.intrinsics.IntrinsicsKt.getCOROUTINE_SUSPENDED()
            int r2 = r0.label
            r3 = 1
            if (r2 == 0) goto L31
            if (r2 != r3) goto L29
            kotlin.ResultKt.throwOnFailure(r7)
            goto L68
        L29:
            java.lang.IllegalStateException r7 = new java.lang.IllegalStateException
            java.lang.String r0 = "call to 'resume' before 'invoke' with coroutine"
            r7.<init>(r0)
            throw r7
        L31:
            kotlin.ResultKt.throwOnFailure(r7)
            java.lang.ref.WeakReference<kotlinx.coroutines.channels.Channel<com.amazon.music.views.library.recyclerview.adapters.BaseModelChange<com.amazon.music.views.library.models.base.BaseViewModel>>> r7 = r6.updateChannel
            if (r7 != 0) goto L39
            goto L68
        L39:
            java.lang.Object r7 = r7.get()
            kotlinx.coroutines.channels.Channel r7 = (kotlinx.coroutines.channels.Channel) r7
            if (r7 != 0) goto L42
            goto L68
        L42:
            boolean r2 = r7.isClosedForSend()
            if (r2 == 0) goto L4b
            kotlin.Unit r7 = kotlin.Unit.INSTANCE
            return r7
        L4b:
            java.lang.ref.WeakReference<com.amazon.music.views.library.recyclerview.adapters.UniversalAdapter> r2 = r6.adapterRef
            if (r2 != 0) goto L50
            goto L68
        L50:
            java.lang.Object r2 = r2.get()
            com.amazon.music.views.library.recyclerview.adapters.UniversalAdapter r2 = (com.amazon.music.views.library.recyclerview.adapters.UniversalAdapter) r2
            if (r2 != 0) goto L59
            goto L68
        L59:
            com.amazon.music.views.library.recyclerview.adapters.BaseModelChange r4 = new com.amazon.music.views.library.recyclerview.adapters.BaseModelChange
            r5 = -1
            r4.<init>(r6, r2, r5)
            r0.label = r3
            java.lang.Object r7 = r7.send(r4, r0)
            if (r7 != r1) goto L68
            return r1
        L68:
            kotlin.Unit r7 = kotlin.Unit.INSTANCE
            return r7
        */
        throw new UnsupportedOperationException("Method not decompiled: com.amazon.music.views.library.models.base.BaseViewModel.sendBaseModelChange(kotlin.coroutines.Continuation):java.lang.Object");
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!Intrinsics.areEqual(getClass(), other == null ? null : other.getClass())) {
            return false;
        }
        if (other != null) {
            return Intrinsics.areEqual(this.viewReferenceId, ((BaseViewModel) other).viewReferenceId);
        }
        throw new NullPointerException("null cannot be cast to non-null type com.amazon.music.views.library.models.base.BaseViewModel");
    }

    public int hashCode() {
        String str = this.viewReferenceId;
        if (str == null) {
            return 0;
        }
        return str.hashCode();
    }

    public boolean areItemsTheSame(Object other) {
        return equals(other);
    }
}
