package com.android.permissioncontroller.permission.data;

import androidx.lifecycle.Lifecycle;
import androidx.lifecycle.LifecycleObserver;
import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MediatorLiveData;
import androidx.lifecycle.Observer;
import com.android.permissioncontroller.permission.data.DataRepository;
import com.android.permissioncontroller.permission.utils.AndroidUtilsKt;
import com.android.permissioncontroller.permission.utils.KotlinUtils;
import com.android.permissioncontroller.permission.utils.KotlinUtilsKt;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.function.Predicate;
import kotlin.Metadata;
import kotlin.Pair;
import kotlin.ResultKt;
import kotlin.Triple;
import kotlin.TuplesKt;
import kotlin.Unit;
import kotlin.collections.CollectionsKt;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.Boxing;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.BuildersKt__Builders_commonKt;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.Dispatchers;
import kotlinx.coroutines.GlobalScope;

/* JADX INFO: compiled from: SmartUpdateMediatorLiveData.kt */
/* JADX INFO: loaded from: classes8.dex */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000n\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010!\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u000b\n\u0002\b\u0006\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\t\n\u0002\b\u0006\n\u0002\u0010\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0015\n\u0002\u0010\u001e\n\u0000\n\u0002\u0010%\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u000f\b&\u0018\u0000 N*\u0004\b\u0000\u0010\u00012\b\u0012\u0004\u0012\u0002H\u00010\u00022\u00020\u0003:\u0002NOB\u0005¢\u0006\u0002\u0010\u0004J4\u0010\u001b\u001a\u00020\u001c\"\u0004\b\u0001\u0010\u001d2\f\u0010\u001e\u001a\b\u0012\u0004\u0012\u0002H\u001d0\u00002\u000e\u0010\u001f\u001a\n\u0012\u0006\b\u0000\u0012\u00028\u00000\b2\u0006\u0010 \u001a\u00020\tH\u0003J,\u0010!\u001a\u00020\u001c\"\u0004\b\u0001\u0010\u001d2\f\u0010\"\u001a\b\u0012\u0004\u0012\u0002H\u001d0#2\u000e\u0010\u001f\u001a\n\u0012\u0006\b\u0000\u0012\u0002H\u001d0\bH\u0016J>\u0010$\u001a\u00020\u001c\"\u0004\b\u0001\u0010\u001d2\f\u0010\"\u001a\b\u0012\u0004\u0012\u0002H\u001d0#2\u000e\u0010\u001f\u001a\n\u0012\u0006\b\u0000\u0012\u0002H\u001d0\b2\u0010\b\u0002\u0010%\u001a\n\u0018\u00010&j\u0004\u0018\u0001`'H\u0002J%\u0010(\u001a\u00028\u00002\b\b\u0002\u0010)\u001a\u00020\t2\b\b\u0002\u0010*\u001a\u00020\tH\u0086@ø\u0001\u0000¢\u0006\u0002\u0010+J\b\u0010,\u001a\u00020\tH\u0002J\u0018\u0010-\u001a\u00020\u001c2\u000e\u0010.\u001a\n\u0012\u0006\b\u0000\u0012\u00028\u00000\bH\u0017J \u0010/\u001a\u00020\u001c2\u0006\u00100\u001a\u00020\u00132\u000e\u0010.\u001a\n\u0012\u0006\b\u0000\u0012\u00028\u00000\bH\u0007J\b\u00101\u001a\u00020\u001cH\u0014J\b\u00102\u001a\u00020\u001cH\u0014J\b\u00103\u001a\u00020\u001cH%J\u001c\u00104\u001a\u00020\u001c\"\u0004\b\u0001\u0010\u001d2\f\u0010\u001e\u001a\b\u0012\u0004\u0012\u0002H\u001d0#H\u0003J\u0018\u00105\u001a\u00020\u001c2\u000e\u0010.\u001a\n\u0012\u0006\b\u0000\u0012\u00028\u00000\bH\u0017J\u0010\u00106\u001a\u00020\u001c2\u0006\u00100\u001a\u00020\u0013H\u0017J\u001c\u00107\u001a\u00020\u001c\"\u0004\b\u0001\u0010\u001d2\f\u00108\u001a\b\u0012\u0004\u0012\u0002H\u001d0#H\u0016Jh\u00109\u001a\u00020\u001c\"\u0004\b\u0001\u0010:\"\f\b\u0002\u0010;*\u0006\u0012\u0002\b\u00030#2\f\u0010<\u001a\b\u0012\u0004\u0012\u0002H:0=2\u0012\u0010>\u001a\u000e\u0012\u0004\u0012\u0002H:\u0012\u0004\u0012\u0002H;0?2\u0012\u0010@\u001a\u000e\u0012\u0004\u0012\u0002H:\u0012\u0004\u0012\u0002H;0A2\u0016\b\u0002\u0010B\u001a\u0010\u0012\u0004\u0012\u0002H:\u0012\u0004\u0012\u00020\u001c\u0018\u00010AJ\u0017\u0010C\u001a\u00020\u001c2\b\u0010D\u001a\u0004\u0018\u00018\u0000H\u0017¢\u0006\u0002\u0010EJ\b\u0010F\u001a\u00020\u001cH\u0007J$\u0010G\u001a\u00020\u001c\"\u0004\b\u0001\u0010\u001d2\f\u0010\u001e\u001a\b\u0012\u0004\u0012\u0002H\u001d0\u00002\u0006\u0010 \u001a\u00020\tH\u0003J\u0018\u0010H\u001a\u00020\u001c2\u0006\u0010I\u001a\u00020\t2\u0006\u0010,\u001a\u00020\tH\u0003J!\u0010J\u001a\u00020\t2\b\u0010K\u001a\u0004\u0018\u00018\u00002\b\u0010L\u001a\u0004\u0018\u00018\u0000H\u0014¢\u0006\u0002\u0010MR2\u0010\u0005\u001a&\u0012\"\u0012 \u0012\b\u0012\u0006\u0012\u0002\b\u00030\u0000\u0012\f\u0012\n\u0012\u0006\b\u0000\u0012\u00028\u00000\b\u0012\u0004\u0012\u00020\t0\u00070\u0006X\u0082\u0004¢\u0006\u0002\n\u0000R\u001e\u0010\u000b\u001a\u00020\t2\u0006\u0010\n\u001a\u00020\t@BX\u0086\u000e¢\u0006\b\n\u0000\u001a\u0004\b\u000b\u0010\fR\u001e\u0010\r\u001a\u00020\t2\u0006\u0010\n\u001a\u00020\t@BX\u0086\u000e¢\u0006\b\n\u0000\u001a\u0004\b\r\u0010\fR\u0018\u0010\u000e\u001a\f\u0012\b\u0012\u0006\u0012\u0002\b\u00030\u00000\u0006X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u000f\u001a\u00020\u0010X\u0082D¢\u0006\u0002\n\u0000R(\u0010\u0011\u001a\u001c\u0012\u0018\u0012\u0016\u0012\u0004\u0012\u00020\u0013\u0012\f\u0012\n\u0012\u0006\b\u0000\u0012\u00028\u00000\b0\u00120\u0006X\u0082\u0004¢\u0006\u0002\n\u0000R\u001e\u0010\u0014\u001a\u0004\u0018\u00010\u0015X\u0096\u000e¢\u0006\u0010\n\u0002\u0010\u001a\u001a\u0004\b\u0016\u0010\u0017\"\u0004\b\u0018\u0010\u0019\u0082\u0002\u0004\n\u0002\b\u0019¨\u0006P"}, d2 = {"Lcom/android/permissioncontroller/permission/data/SmartUpdateMediatorLiveData;", "T", "Landroidx/lifecycle/MediatorLiveData;", "Lcom/android/permissioncontroller/permission/data/DataRepository$InactiveTimekeeper;", "()V", "children", "", "Lkotlin/Triple;", "Landroidx/lifecycle/Observer;", "", "<set-?>", "isInitialized", "()Z", "isStale", "sources", "stacktraceExceptionMessage", "", "staleObservers", "Lkotlin/Pair;", "Landroidx/lifecycle/LifecycleOwner;", "timeWentInactive", "", "getTimeWentInactive", "()Ljava/lang/Long;", "setTimeWentInactive", "(Ljava/lang/Long;)V", "Ljava/lang/Long;", "addChild", "", "S", "liveData", "onChanged", "sendStaleUpdates", "addSource", "source", "Landroidx/lifecycle/LiveData;", "addSourceWithError", "e", "Ljava/lang/IllegalStateException;", "Lkotlin/IllegalStateException;", "getInitializedValue", "staleOk", "forceUpdate", "(ZZLkotlin/coroutines/Continuation;)Ljava/lang/Object;", "hasStaleObserver", "observeForever", "observer", "observeStale", "owner", "onActive", "onInactive", "onUpdate", "removeChild", "removeObserver", "removeObservers", "removeSource", "toRemote", "setSourcesToDifference", "K", "V", "desired", "", "have", "", "getLiveDataFun", "Lkotlin/Function1;", "onUpdateFun", "setValue", "newValue", "(Ljava/lang/Object;)V", "updateIfActive", "updateShouldSendStaleUpdates", "updateSourceStaleObservers", "hadStaleObserver", "valueNotEqual", "valOne", "valTwo", "(Ljava/lang/Object;Ljava/lang/Object;)Z", "Companion", "ForeverActiveLifecycle", "vendor__ts__proprietary__CarPackageManager__CarPermissionController__android_common__ts-permission-controller"}, k = 1, mv = {1, 1, 16})
public abstract class SmartUpdateMediatorLiveData<T> extends MediatorLiveData<T> implements DataRepository.InactiveTimekeeper {
    public static final boolean DEBUG_UPDATES = false;
    private boolean isInitialized;
    private Long timeWentInactive;

    /* JADX INFO: renamed from: Companion, reason: from kotlin metadata */
    public static final Companion INSTANCE = new Companion(null);
    private static final String LOG_TAG = SmartUpdateMediatorLiveData.class.getSimpleName();
    private boolean isStale = true;
    private final List<Pair<LifecycleOwner, Observer<? super T>>> staleObservers = new ArrayList();
    private final List<SmartUpdateMediatorLiveData<?>> sources = new ArrayList();
    private final List<Triple<SmartUpdateMediatorLiveData<?>, Observer<? super T>, Boolean>> children = new ArrayList();
    private final String stacktraceExceptionMessage = "Caller of coroutine";

    protected abstract void onUpdate();

    @Override // com.android.permissioncontroller.permission.data.DataRepository.InactiveTimekeeper
    public Long getTimeInactive() {
        return DataRepository.InactiveTimekeeper.DefaultImpls.getTimeInactive(this);
    }

    /* JADX INFO: compiled from: SmartUpdateMediatorLiveData.kt */
    @Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u001a\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0004\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002R\u000e\u0010\u0003\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u0019\u0010\u0005\u001a\n \u0007*\u0004\u0018\u00010\u00060\u0006¢\u0006\b\n\u0000\u001a\u0004\b\b\u0010\t¨\u0006\n"}, d2 = {"Lcom/android/permissioncontroller/permission/data/SmartUpdateMediatorLiveData$Companion;", "", "()V", "DEBUG_UPDATES", "", "LOG_TAG", "", "kotlin.jvm.PlatformType", "getLOG_TAG", "()Ljava/lang/String;", "vendor__ts__proprietary__CarPackageManager__CarPermissionController__android_common__ts-permission-controller"}, k = 1, mv = {1, 1, 16})
    public static final class Companion {
        private Companion() {
        }

        public /* synthetic */ Companion(DefaultConstructorMarker $constructor_marker) {
            this();
        }

        public final String getLOG_TAG() {
            return SmartUpdateMediatorLiveData.LOG_TAG;
        }
    }

    /* JADX INFO: renamed from: isInitialized, reason: from getter */
    public final boolean getIsInitialized() {
        return this.isInitialized;
    }

    /* JADX INFO: renamed from: isStale, reason: from getter */
    public final boolean getIsStale() {
        return this.isStale;
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Override // androidx.lifecycle.MutableLiveData, androidx.lifecycle.LiveData
    public void setValue(T newValue) {
        AndroidUtilsKt.ensureMainThread();
        if (!this.isInitialized) {
            this.isInitialized = true;
            this.isStale = false;
            if (newValue == null) {
                super.setValue(newValue);
                return;
            }
        }
        if (valueNotEqual(super.getValue(), newValue)) {
            this.isStale = false;
            super.setValue(newValue);
            return;
        }
        if (this.isStale) {
            this.isStale = false;
            Iterable $this$filter$iv = this.staleObservers;
            Collection destination$iv$iv = new ArrayList();
            for (Object element$iv$iv : $this$filter$iv) {
                Pair it = (Pair) element$iv$iv;
                Lifecycle lifecycle = ((LifecycleOwner) it.getFirst()).getLifecycle();
                Intrinsics.checkExpressionValueIsNotNull(lifecycle, "it.first.lifecycle");
                if (lifecycle.getCurrentState().compareTo(Lifecycle.State.STARTED) >= 0) {
                    destination$iv$iv.add(element$iv$iv);
                }
            }
            List liveObservers = (List) destination$iv$iv;
            Iterator it2 = liveObservers.iterator();
            while (it2.hasNext()) {
                Observer observer = (Observer) ((Pair) it2.next()).component2();
                observer.onChanged(newValue);
            }
            for (Triple triple : CollectionsKt.toList(this.children)) {
                SmartUpdateMediatorLiveData liveData = (SmartUpdateMediatorLiveData) triple.component1();
                Observer observer2 = (Observer) triple.component2();
                boolean shouldUpdate = ((Boolean) triple.component3()).booleanValue();
                if (liveData.hasActiveObservers() && shouldUpdate) {
                    observer2.onChanged(newValue);
                }
            }
        }
    }

    public final void updateIfActive() {
        onUpdate();
    }

    @Override // com.android.permissioncontroller.permission.data.DataRepository.InactiveTimekeeper
    public Long getTimeWentInactive() {
        return this.timeWentInactive;
    }

    @Override // com.android.permissioncontroller.permission.data.DataRepository.InactiveTimekeeper
    public void setTimeWentInactive(Long l) {
        this.timeWentInactive = l;
    }

    protected boolean valueNotEqual(T valOne, T valTwo) {
        return !Intrinsics.areEqual(valOne, valTwo);
    }

    public final void observeStale(LifecycleOwner owner, Observer<? super T> observer) {
        Intrinsics.checkParameterIsNotNull(owner, "owner");
        Intrinsics.checkParameterIsNotNull(observer, "observer");
        boolean oldStaleObserver = hasStaleObserver();
        this.staleObservers.add(TuplesKt.to(owner, observer));
        if (Intrinsics.areEqual(owner, ForeverActiveLifecycle.INSTANCE)) {
            observeForever(observer);
        } else {
            observe(owner, observer);
        }
        updateSourceStaleObservers(oldStaleObserver, true);
    }

    @Override // androidx.lifecycle.MediatorLiveData
    public <S> void addSource(LiveData<S> source, Observer<? super S> onChanged) {
        Intrinsics.checkParameterIsNotNull(source, "source");
        Intrinsics.checkParameterIsNotNull(onChanged, "onChanged");
        addSourceWithError$default(this, source, onChanged, null, 4, null);
    }

    static /* synthetic */ void addSourceWithError$default(SmartUpdateMediatorLiveData smartUpdateMediatorLiveData, LiveData liveData, Observer observer, IllegalStateException illegalStateException, int i, Object obj) {
        if (obj != null) {
            throw new UnsupportedOperationException("Super calls with default arguments not supported in this target, function: addSourceWithError");
        }
        if ((i & 4) != 0) {
            illegalStateException = (IllegalStateException) null;
        }
        smartUpdateMediatorLiveData.addSourceWithError(liveData, observer, illegalStateException);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final <S> void addSourceWithError(LiveData<S> source, Observer<? super S> onChanged, IllegalStateException e) {
        IllegalStateException exception = e != null ? e : new IllegalStateException(this.stacktraceExceptionMessage);
        BuildersKt__Builders_commonKt.launch$default(GlobalScope.INSTANCE, Dispatchers.getMain().getImmediate(), null, new AnonymousClass1(source, onChanged, exception, null), 2, null);
    }

    /* JADX INFO: renamed from: com.android.permissioncontroller.permission.data.SmartUpdateMediatorLiveData$addSourceWithError$1, reason: invalid class name */
    /* JADX INFO: compiled from: SmartUpdateMediatorLiveData.kt */
    @Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0012\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0000\u001a\u00020\u0001\"\u0004\b\u0000\u0010\u0002\"\u0004\b\u0001\u0010\u0003*\u00020\u0004H\u008a@¢\u0006\u0004\b\u0005\u0010\u0006"}, d2 = {"<anonymous>", "", "S", "T", "Lkotlinx/coroutines/CoroutineScope;", "invoke", "(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;"}, k = 3, mv = {1, 1, 16})
    @DebugMetadata(c = "com.android.permissioncontroller.permission.data.SmartUpdateMediatorLiveData$addSourceWithError$1", f = "SmartUpdateMediatorLiveData.kt", i = {}, l = {}, m = "invokeSuspend", n = {}, s = {})
    static final class AnonymousClass1 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
        final /* synthetic */ IllegalStateException $exception;
        final /* synthetic */ Observer $onChanged;
        final /* synthetic */ LiveData $source;
        int label;
        private CoroutineScope p$;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        AnonymousClass1(LiveData liveData, Observer observer, IllegalStateException illegalStateException, Continuation continuation) {
            super(2, continuation);
            this.$source = liveData;
            this.$onChanged = observer;
            this.$exception = illegalStateException;
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Continuation<Unit> create(Object obj, Continuation<?> completion) {
            Intrinsics.checkParameterIsNotNull(completion, "completion");
            AnonymousClass1 anonymousClass1 = new AnonymousClass1(this.$source, this.$onChanged, this.$exception, completion);
            anonymousClass1.p$ = (CoroutineScope) obj;
            return anonymousClass1;
        }

        @Override // kotlin.jvm.functions.Function2
        public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
            return ((AnonymousClass1) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object $result) {
            boolean z;
            IntrinsicsKt.getCOROUTINE_SUSPENDED();
            if (this.label == 0) {
                ResultKt.throwOnFailure($result);
                CoroutineScope coroutineScope = this.p$;
                if (this.$source instanceof SmartUpdateMediatorLiveData) {
                    if (SmartUpdateMediatorLiveData.this.sources.contains(this.$source)) {
                        return Unit.INSTANCE;
                    }
                    SmartUpdateMediatorLiveData smartUpdateMediatorLiveData = (SmartUpdateMediatorLiveData) this.$source;
                    SmartUpdateMediatorLiveData smartUpdateMediatorLiveData2 = SmartUpdateMediatorLiveData.this;
                    Observer observer = this.$onChanged;
                    boolean z2 = true;
                    if (!(!smartUpdateMediatorLiveData2.staleObservers.isEmpty())) {
                        Iterable $this$any$iv = SmartUpdateMediatorLiveData.this.children;
                        if (!($this$any$iv instanceof Collection) || !((Collection) $this$any$iv).isEmpty()) {
                            Iterator<T> it = $this$any$iv.iterator();
                            while (true) {
                                if (it.hasNext()) {
                                    Object element$iv = it.next();
                                    Triple it2 = (Triple) element$iv;
                                    if (Boxing.boxBoolean(((Boolean) it2.getThird()).booleanValue()).booleanValue()) {
                                        z = true;
                                        break;
                                    }
                                } else {
                                    z = false;
                                    break;
                                }
                            }
                        } else {
                            z = false;
                        }
                        if (!z) {
                            z2 = false;
                        }
                    }
                    smartUpdateMediatorLiveData.addChild(smartUpdateMediatorLiveData2, observer, z2);
                    SmartUpdateMediatorLiveData.this.sources.add(this.$source);
                }
                try {
                    SmartUpdateMediatorLiveData.super.addSource(this.$source, this.$onChanged);
                    return Unit.INSTANCE;
                } catch (IllegalStateException other) {
                    other.initCause(this.$exception);
                    throw other;
                }
            }
            throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
        }
    }

    /* JADX INFO: renamed from: com.android.permissioncontroller.permission.data.SmartUpdateMediatorLiveData$removeSource$1, reason: invalid class name and case insensitive filesystem */
    /* JADX INFO: compiled from: SmartUpdateMediatorLiveData.kt */
    @Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0012\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0000\u001a\u00020\u0001\"\u0004\b\u0000\u0010\u0002\"\u0004\b\u0001\u0010\u0003*\u00020\u0004H\u008a@¢\u0006\u0004\b\u0005\u0010\u0006"}, d2 = {"<anonymous>", "", "S", "T", "Lkotlinx/coroutines/CoroutineScope;", "invoke", "(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;"}, k = 3, mv = {1, 1, 16})
    @DebugMetadata(c = "com.android.permissioncontroller.permission.data.SmartUpdateMediatorLiveData$removeSource$1", f = "SmartUpdateMediatorLiveData.kt", i = {}, l = {}, m = "invokeSuspend", n = {}, s = {})
    static final class C00411 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
        final /* synthetic */ LiveData $toRemote;
        int label;
        private CoroutineScope p$;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        C00411(LiveData liveData, Continuation continuation) {
            super(2, continuation);
            this.$toRemote = liveData;
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Continuation<Unit> create(Object obj, Continuation<?> completion) {
            Intrinsics.checkParameterIsNotNull(completion, "completion");
            C00411 c00411 = new C00411(this.$toRemote, completion);
            c00411.p$ = (CoroutineScope) obj;
            return c00411;
        }

        @Override // kotlin.jvm.functions.Function2
        public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
            return ((C00411) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object $result) {
            IntrinsicsKt.getCOROUTINE_SUSPENDED();
            if (this.label != 0) {
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
            ResultKt.throwOnFailure($result);
            CoroutineScope coroutineScope = this.p$;
            LiveData liveData = this.$toRemote;
            if (liveData instanceof SmartUpdateMediatorLiveData) {
                ((SmartUpdateMediatorLiveData) liveData).removeChild(SmartUpdateMediatorLiveData.this);
                SmartUpdateMediatorLiveData.this.sources.remove(this.$toRemote);
            }
            SmartUpdateMediatorLiveData.super.removeSource(this.$toRemote);
            return Unit.INSTANCE;
        }
    }

    @Override // androidx.lifecycle.MediatorLiveData
    public <S> void removeSource(LiveData<S> toRemote) {
        Intrinsics.checkParameterIsNotNull(toRemote, "toRemote");
        BuildersKt__Builders_commonKt.launch$default(GlobalScope.INSTANCE, Dispatchers.getMain().getImmediate(), null, new C00411(toRemote, null), 2, null);
    }

    public static /* synthetic */ void setSourcesToDifference$default(SmartUpdateMediatorLiveData smartUpdateMediatorLiveData, Collection collection, Map map, Function1 function1, Function1 function12, int i, Object obj) {
        if (obj != null) {
            throw new UnsupportedOperationException("Super calls with default arguments not supported in this target, function: setSourcesToDifference");
        }
        if ((i & 8) != 0) {
            function12 = (Function1) null;
        }
        smartUpdateMediatorLiveData.setSourcesToDifference(collection, map, function1, function12);
    }

    /* JADX WARN: Multi-variable type inference failed */
    public final <K, V extends LiveData<?>> void setSourcesToDifference(Collection<? extends K> desired, Map<K, V> have, Function1<? super K, ? extends V> getLiveDataFun, Function1<? super K, Unit> onUpdateFun) {
        Intrinsics.checkParameterIsNotNull(desired, "desired");
        Intrinsics.checkParameterIsNotNull(have, "have");
        Intrinsics.checkParameterIsNotNull(getLiveDataFun, "getLiveDataFun");
        Pair<Set<K>, Set<K>> mapAndListDifferences = KotlinUtils.INSTANCE.getMapAndListDifferences(desired, have);
        Set<K> setComponent1 = mapAndListDifferences.component1();
        Set<K> setComponent2 = mapAndListDifferences.component2();
        for (Object key : setComponent1) {
            have.put(key, getLiveDataFun.invoke(key));
        }
        Set<K> $this$map$iv = setComponent2;
        Collection destination$iv$iv = new ArrayList(CollectionsKt.collectionSizeOrDefault($this$map$iv, 10));
        for (Object item$iv$iv : $this$map$iv) {
            destination$iv$iv.add((LiveData) have.remove(item$iv$iv));
        }
        List removed = CollectionsKt.toMutableList((Collection) ((List) destination$iv$iv));
        IllegalStateException stackTraceException = new IllegalStateException(this.stacktraceExceptionMessage);
        BuildersKt__Builders_commonKt.launch$default(GlobalScope.INSTANCE, Dispatchers.getMain().getImmediate(), null, new C00421(setComponent2, removed, have, setComponent1, getLiveDataFun, onUpdateFun, stackTraceException, null), 2, null);
    }

    /* JADX INFO: renamed from: com.android.permissioncontroller.permission.data.SmartUpdateMediatorLiveData$setSourcesToDifference$1, reason: invalid class name and case insensitive filesystem */
    /* JADX INFO: compiled from: SmartUpdateMediatorLiveData.kt */
    @Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0018\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0000\u001a\u00020\u0001\"\u0004\b\u0000\u0010\u0002\"\f\b\u0001\u0010\u0003*\u0006\u0012\u0002\b\u00030\u0004\"\u0004\b\u0002\u0010\u0005*\u00020\u0006H\u008a@¢\u0006\u0004\b\u0007\u0010\b"}, d2 = {"<anonymous>", "", "K", "V", "Landroidx/lifecycle/LiveData;", "T", "Lkotlinx/coroutines/CoroutineScope;", "invoke", "(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;"}, k = 3, mv = {1, 1, 16})
    @DebugMetadata(c = "com.android.permissioncontroller.permission.data.SmartUpdateMediatorLiveData$setSourcesToDifference$1", f = "SmartUpdateMediatorLiveData.kt", i = {}, l = {}, m = "invokeSuspend", n = {}, s = {})
    static final class C00421 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
        final /* synthetic */ Function1 $getLiveDataFun;
        final /* synthetic */ Map $have;
        final /* synthetic */ Function1 $onUpdateFun;
        final /* synthetic */ List $removed;
        final /* synthetic */ IllegalStateException $stackTraceException;
        final /* synthetic */ Set $toAdd;
        final /* synthetic */ Set $toRemove;
        int label;
        private CoroutineScope p$;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        C00421(Set set, List list, Map map, Set set2, Function1 function1, Function1 function12, IllegalStateException illegalStateException, Continuation continuation) {
            super(2, continuation);
            this.$toRemove = set;
            this.$removed = list;
            this.$have = map;
            this.$toAdd = set2;
            this.$getLiveDataFun = function1;
            this.$onUpdateFun = function12;
            this.$stackTraceException = illegalStateException;
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Continuation<Unit> create(Object obj, Continuation<?> completion) {
            Intrinsics.checkParameterIsNotNull(completion, "completion");
            C00421 c00421 = new C00421(this.$toRemove, this.$removed, this.$have, this.$toAdd, this.$getLiveDataFun, this.$onUpdateFun, this.$stackTraceException, completion);
            c00421.p$ = (CoroutineScope) obj;
            return c00421;
        }

        @Override // kotlin.jvm.functions.Function2
        public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
            return ((C00421) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object $result) {
            IntrinsicsKt.getCOROUTINE_SUSPENDED();
            if (this.label != 0) {
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
            ResultKt.throwOnFailure($result);
            CoroutineScope coroutineScope = this.p$;
            for (Object key : this.$toRemove) {
                List list = this.$removed;
                LiveData liveData = (LiveData) this.$have.remove(key);
                if (liveData != null) {
                    list.add(liveData);
                }
            }
            for (LiveData liveData2 : this.$removed) {
                SmartUpdateMediatorLiveData smartUpdateMediatorLiveData = SmartUpdateMediatorLiveData.this;
                if (liveData2 != null) {
                    smartUpdateMediatorLiveData.removeSource(liveData2);
                }
            }
            for (final Object key2 : this.$toAdd) {
                LiveData liveData3 = (LiveData) this.$getLiveDataFun.invoke(key2);
                this.$have.put(key2, liveData3);
                SmartUpdateMediatorLiveData.this.addSourceWithError(liveData3, new Observer<Object>() { // from class: com.android.permissioncontroller.permission.data.SmartUpdateMediatorLiveData$setSourcesToDifference$1$observer$1
                    @Override // androidx.lifecycle.Observer
                    public final void onChanged(Object it) {
                        if (this.this$0.$onUpdateFun != null) {
                            this.this$0.$onUpdateFun.invoke(key2);
                        } else {
                            SmartUpdateMediatorLiveData.this.updateIfActive();
                        }
                    }
                }, this.$stackTraceException);
            }
            return Unit.INSTANCE;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final <S> void removeChild(final LiveData<S> liveData) {
        this.children.removeIf(new Predicate<Triple<? extends SmartUpdateMediatorLiveData<?>, ? extends Observer<? super T>, ? extends Boolean>>() { // from class: com.android.permissioncontroller.permission.data.SmartUpdateMediatorLiveData.removeChild.1
            @Override // java.util.function.Predicate
            public final boolean test(Triple<? extends SmartUpdateMediatorLiveData<?>, ? extends Observer<? super T>, Boolean> it) {
                Intrinsics.checkParameterIsNotNull(it, "it");
                return Intrinsics.areEqual(it.getFirst(), liveData);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final <S> void addChild(SmartUpdateMediatorLiveData<S> liveData, Observer<? super T> onChanged, boolean sendStaleUpdates) {
        this.children.add(new Triple<>(liveData, onChanged, Boolean.valueOf(sendStaleUpdates)));
    }

    private final <S> void updateShouldSendStaleUpdates(SmartUpdateMediatorLiveData<S> liveData, boolean sendStaleUpdates) {
        Iterator<T> it = this.children.iterator();
        int i = 0;
        while (it.hasNext()) {
            Triple triple = (Triple) it.next();
            if (Intrinsics.areEqual((SmartUpdateMediatorLiveData) triple.getFirst(), liveData)) {
                this.children.set(i, new Triple<>(liveData, triple.getSecond(), Boolean.valueOf(sendStaleUpdates)));
            }
            i++;
        }
    }

    @Override // androidx.lifecycle.LiveData
    public void removeObserver(final Observer<? super T> observer) {
        Intrinsics.checkParameterIsNotNull(observer, "observer");
        boolean oldStaleObserver = hasStaleObserver();
        this.staleObservers.removeIf(new Predicate<Pair<? extends LifecycleOwner, ? extends Observer<? super T>>>() { // from class: com.android.permissioncontroller.permission.data.SmartUpdateMediatorLiveData.removeObserver.1
            @Override // java.util.function.Predicate
            public final boolean test(Pair<? extends LifecycleOwner, ? extends Observer<? super T>> it) {
                Intrinsics.checkParameterIsNotNull(it, "it");
                return Intrinsics.areEqual(it.getSecond(), observer);
            }
        });
        super.removeObserver(observer);
        updateSourceStaleObservers(oldStaleObserver, hasStaleObserver());
    }

    @Override // androidx.lifecycle.LiveData
    public void removeObservers(final LifecycleOwner owner) {
        Intrinsics.checkParameterIsNotNull(owner, "owner");
        boolean oldStaleObserver = hasStaleObserver();
        this.staleObservers.removeIf(new Predicate<Pair<? extends LifecycleOwner, ? extends Observer<? super T>>>() { // from class: com.android.permissioncontroller.permission.data.SmartUpdateMediatorLiveData.removeObservers.1
            @Override // java.util.function.Predicate
            public final boolean test(Pair<? extends LifecycleOwner, ? extends Observer<? super T>> it) {
                Intrinsics.checkParameterIsNotNull(it, "it");
                return Intrinsics.areEqual(it.getFirst(), owner);
            }
        });
        super.removeObservers(owner);
        updateSourceStaleObservers(oldStaleObserver, hasStaleObserver());
    }

    @Override // androidx.lifecycle.LiveData
    public void observeForever(Observer<? super T> observer) {
        Intrinsics.checkParameterIsNotNull(observer, "observer");
        super.observeForever(observer);
    }

    private final void updateSourceStaleObservers(boolean hadStaleObserver, boolean hasStaleObserver) {
        if (hadStaleObserver == hasStaleObserver) {
            return;
        }
        Iterator<SmartUpdateMediatorLiveData<?>> it = this.sources.iterator();
        while (it.hasNext()) {
            it.next().updateShouldSendStaleUpdates(this, hasStaleObserver);
        }
        Iterable $this$all$iv = this.sources;
        boolean z = true;
        if (!($this$all$iv instanceof Collection) || !((Collection) $this$all$iv).isEmpty()) {
            Iterator<T> it2 = $this$all$iv.iterator();
            while (true) {
                if (!it2.hasNext()) {
                    break;
                }
                Object element$iv = it2.next();
                SmartUpdateMediatorLiveData it3 = (SmartUpdateMediatorLiveData) element$iv;
                if (!(!it3.isStale)) {
                    z = false;
                    break;
                }
            }
        }
        if (z && hasStaleObserver && this.isStale) {
            updateIfActive();
        }
    }

    private final boolean hasStaleObserver() {
        boolean z;
        if (!this.staleObservers.isEmpty()) {
            return true;
        }
        Iterable $this$any$iv = this.children;
        if (!($this$any$iv instanceof Collection) || !((Collection) $this$any$iv).isEmpty()) {
            Iterator<T> it = $this$any$iv.iterator();
            while (true) {
                if (it.hasNext()) {
                    Object element$iv = it.next();
                    Triple it2 = (Triple) element$iv;
                    if (((Boolean) it2.getThird()).booleanValue()) {
                        z = true;
                        break;
                    }
                } else {
                    z = false;
                    break;
                }
            }
        } else {
            z = false;
        }
        return z;
    }

    @Override // androidx.lifecycle.MediatorLiveData, androidx.lifecycle.LiveData
    protected void onActive() {
        setTimeWentInactive((Long) null);
        super.onActive();
    }

    @Override // androidx.lifecycle.MediatorLiveData, androidx.lifecycle.LiveData
    protected void onInactive() {
        setTimeWentInactive(Long.valueOf(System.nanoTime()));
        this.isStale = true;
        super.onInactive();
    }

    public static /* synthetic */ Object getInitializedValue$default(SmartUpdateMediatorLiveData smartUpdateMediatorLiveData, boolean z, boolean z2, Continuation continuation, int i, Object obj) {
        if (obj != null) {
            throw new UnsupportedOperationException("Super calls with default arguments not supported in this target, function: getInitializedValue");
        }
        if ((i & 1) != 0) {
            z = false;
        }
        if ((i & 2) != 0) {
            z2 = false;
        }
        return smartUpdateMediatorLiveData.getInitializedValue(z, z2, continuation);
    }

    public final Object getInitializedValue(final boolean staleOk, final boolean forceUpdate, Continuation<? super T> continuation) {
        return KotlinUtilsKt.getInitializedValue(this, new Function2<SmartUpdateMediatorLiveData<T>, Observer<T>, Unit>() { // from class: com.android.permissioncontroller.permission.data.SmartUpdateMediatorLiveData.getInitializedValue.2
            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super(2);
            }

            @Override // kotlin.jvm.functions.Function2
            public /* bridge */ /* synthetic */ Unit invoke(Object obj, Object obj2) {
                invoke((SmartUpdateMediatorLiveData) obj, (Observer) obj2);
                return Unit.INSTANCE;
            }

            /* JADX WARN: Multi-variable type inference failed */
            public final void invoke(SmartUpdateMediatorLiveData<T> receiver, Observer<T> observer) {
                Intrinsics.checkParameterIsNotNull(receiver, "$receiver");
                Intrinsics.checkParameterIsNotNull(observer, "observer");
                receiver.observeStale(ForeverActiveLifecycle.INSTANCE, observer);
                if (forceUpdate) {
                    receiver.updateIfActive();
                }
            }
        }, new Function1<SmartUpdateMediatorLiveData<T>, Boolean>() { // from class: com.android.permissioncontroller.permission.data.SmartUpdateMediatorLiveData.getInitializedValue.3
            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super(1);
            }

            @Override // kotlin.jvm.functions.Function1
            public /* bridge */ /* synthetic */ Boolean invoke(Object obj) {
                return Boolean.valueOf(invoke((SmartUpdateMediatorLiveData) obj));
            }

            public final boolean invoke(SmartUpdateMediatorLiveData<T> receiver) {
                Intrinsics.checkParameterIsNotNull(receiver, "$receiver");
                return receiver.getIsInitialized() && (staleOk || !receiver.getIsStale());
            }
        }, continuation);
    }

    /* JADX INFO: compiled from: SmartUpdateMediatorLiveData.kt */
    @Metadata(bv = {1, 0, 3}, d1 = {"\u0000$\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\bÂ\u0002\u0018\u00002\u00020\u00012\u00020\u0002B\u0007\b\u0002¢\u0006\u0002\u0010\u0003J\u0010\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u0007H\u0016J\b\u0010\b\u001a\u00020\tH\u0016J\b\u0010\n\u001a\u00020\u0001H\u0016J\u0010\u0010\u000b\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u0007H\u0016¨\u0006\f"}, d2 = {"Lcom/android/permissioncontroller/permission/data/SmartUpdateMediatorLiveData$ForeverActiveLifecycle;", "Landroidx/lifecycle/Lifecycle;", "Landroidx/lifecycle/LifecycleOwner;", "()V", "addObserver", "", "observer", "Landroidx/lifecycle/LifecycleObserver;", "getCurrentState", "Landroidx/lifecycle/Lifecycle$State;", "getLifecycle", "removeObserver", "vendor__ts__proprietary__CarPackageManager__CarPermissionController__android_common__ts-permission-controller"}, k = 1, mv = {1, 1, 16})
    private static final class ForeverActiveLifecycle extends Lifecycle implements LifecycleOwner {
        public static final ForeverActiveLifecycle INSTANCE = new ForeverActiveLifecycle();

        private ForeverActiveLifecycle() {
        }

        @Override // androidx.lifecycle.LifecycleOwner
        public Lifecycle getLifecycle() {
            return this;
        }

        @Override // androidx.lifecycle.Lifecycle
        public void addObserver(LifecycleObserver observer) {
            Intrinsics.checkParameterIsNotNull(observer, "observer");
        }

        @Override // androidx.lifecycle.Lifecycle
        public void removeObserver(LifecycleObserver observer) {
            Intrinsics.checkParameterIsNotNull(observer, "observer");
        }

        @Override // androidx.lifecycle.Lifecycle
        public Lifecycle.State getCurrentState() {
            return Lifecycle.State.STARTED;
        }
    }
}
