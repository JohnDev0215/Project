package com.android.permissioncontroller.permission.data;

import android.os.Binder;
import com.android.permissioncontroller.permission.utils.AndroidUtilsKt;
import java.util.concurrent.CancellationException;
import kotlin.Metadata;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.BuildersKt__Builders_commonKt;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.Dispatchers;
import kotlinx.coroutines.GlobalScope;
import kotlinx.coroutines.Job;

/* JADX INFO: compiled from: SmartAsyncMediatorLiveData.kt */
/* JADX INFO: loaded from: classes8.dex */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000$\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0002\b\u0007\b&\u0018\u0000*\u0004\b\u0000\u0010\u00012\b\u0012\u0004\u0012\u0002H\u00010\u0002B\u0005¢\u0006\u0002\u0010\u0003J\b\u0010\t\u001a\u00020\nH\u0002J\u0019\u0010\u000b\u001a\u00020\n2\u0006\u0010\f\u001a\u00020\u0005H¦@ø\u0001\u0000¢\u0006\u0002\u0010\rJ\b\u0010\u000e\u001a\u00020\nH\u0014J\b\u0010\u000f\u001a\u00020\nH\u0014J\b\u0010\u0010\u001a\u00020\nH\u0016R\u0010\u0010\u0004\u001a\u0004\u0018\u00010\u0005X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0007X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\b\u001a\u00020\u0007X\u0082\u000e¢\u0006\u0002\n\u0000\u0082\u0002\u0004\n\u0002\b\u0019¨\u0006\u0011"}, d2 = {"Lcom/android/permissioncontroller/permission/data/SmartAsyncMediatorLiveData;", "T", "Lcom/android/permissioncontroller/permission/data/SmartUpdateMediatorLiveData;", "()V", "currentJob", "Lkotlinx/coroutines/Job;", "jobQueued", "", "jobRunning", "cancelJobIfRunning", "", "loadDataAndPostValue", "job", "(Lkotlinx/coroutines/Job;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "onInactive", "onUpdate", "updateAsync", "vendor__ts__proprietary__CarPackageManager__CarPermissionController__android_common__ts-permission-controller"}, k = 1, mv = {1, 1, 16})
public abstract class SmartAsyncMediatorLiveData<T> extends SmartUpdateMediatorLiveData<T> {
    private Job currentJob;
    private volatile boolean jobQueued;
    private volatile boolean jobRunning;

    public abstract Object loadDataAndPostValue(Job job, Continuation<? super Unit> continuation);

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.android.permissioncontroller.permission.data.SmartUpdateMediatorLiveData
    public void onUpdate() {
        updateAsync();
    }

    public void updateAsync() {
        if (this.jobRunning) {
            this.jobQueued = true;
        } else {
            this.jobRunning = true;
            BuildersKt__Builders_commonKt.launch$default(GlobalScope.INSTANCE, AndroidUtilsKt.getIPC(), null, new AnonymousClass1(null), 2, null);
        }
    }

    /* JADX INFO: renamed from: com.android.permissioncontroller.permission.data.SmartAsyncMediatorLiveData$updateAsync$1, reason: invalid class name */
    /* JADX INFO: compiled from: SmartAsyncMediatorLiveData.kt */
    @Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0010\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0000\u001a\u00020\u0001\"\u0004\b\u0000\u0010\u0002*\u00020\u0003H\u008a@¢\u0006\u0004\b\u0004\u0010\u0005"}, d2 = {"<anonymous>", "", "T", "Lkotlinx/coroutines/CoroutineScope;", "invoke", "(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;"}, k = 3, mv = {1, 1, 16})
    @DebugMetadata(c = "com.android.permissioncontroller.permission.data.SmartAsyncMediatorLiveData$updateAsync$1", f = "SmartAsyncMediatorLiveData.kt", i = {0}, l = {58}, m = "invokeSuspend", n = {"$this$launch"}, s = {"L$0"})
    static final class AnonymousClass1 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
        Object L$0;
        int label;
        private CoroutineScope p$;

        AnonymousClass1(Continuation continuation) {
            super(2, continuation);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Continuation<Unit> create(Object obj, Continuation<?> completion) {
            Intrinsics.checkParameterIsNotNull(completion, "completion");
            AnonymousClass1 anonymousClass1 = new AnonymousClass1(completion);
            anonymousClass1.p$ = (CoroutineScope) obj;
            return anonymousClass1;
        }

        @Override // kotlin.jvm.functions.Function2
        public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
            return ((AnonymousClass1) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object $result) {
            Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
            int i = this.label;
            if (i == 0) {
                ResultKt.throwOnFailure($result);
                CoroutineScope $this$launch = this.p$;
                SmartAsyncMediatorLiveData.this.currentJob = (Job) $this$launch.getCoroutineContext().get(Job.INSTANCE);
                SmartAsyncMediatorLiveData smartAsyncMediatorLiveData = SmartAsyncMediatorLiveData.this;
                Job job = smartAsyncMediatorLiveData.currentJob;
                if (job == null) {
                    Intrinsics.throwNpe();
                }
                this.L$0 = $this$launch;
                this.label = 1;
                if (smartAsyncMediatorLiveData.loadDataAndPostValue(job, this) == coroutine_suspended) {
                    return coroutine_suspended;
                }
            } else {
                if (i != 1) {
                    throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                }
                ResultKt.throwOnFailure($result);
            }
            Binder.flushPendingCommands();
            SmartAsyncMediatorLiveData.this.jobRunning = false;
            if (SmartAsyncMediatorLiveData.this.jobQueued) {
                SmartAsyncMediatorLiveData.this.jobQueued = false;
                BuildersKt__Builders_commonKt.launch$default(GlobalScope.INSTANCE, Dispatchers.getMain().getImmediate(), null, new C00101(null), 2, null);
            }
            return Unit.INSTANCE;
        }

        /* JADX INFO: renamed from: com.android.permissioncontroller.permission.data.SmartAsyncMediatorLiveData$updateAsync$1$1, reason: invalid class name and collision with other inner class name */
        /* JADX INFO: compiled from: SmartAsyncMediatorLiveData.kt */
        @Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0010\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0000\u001a\u00020\u0001\"\u0004\b\u0000\u0010\u0002*\u00020\u0003H\u008a@¢\u0006\u0004\b\u0004\u0010\u0005"}, d2 = {"<anonymous>", "", "T", "Lkotlinx/coroutines/CoroutineScope;", "invoke", "(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;"}, k = 3, mv = {1, 1, 16})
        @DebugMetadata(c = "com.android.permissioncontroller.permission.data.SmartAsyncMediatorLiveData$updateAsync$1$1", f = "SmartAsyncMediatorLiveData.kt", i = {}, l = {}, m = "invokeSuspend", n = {}, s = {})
        static final class C00101 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
            int label;
            private CoroutineScope p$;

            C00101(Continuation continuation) {
                super(2, continuation);
            }

            @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
            public final Continuation<Unit> create(Object obj, Continuation<?> completion) {
                Intrinsics.checkParameterIsNotNull(completion, "completion");
                C00101 c00101 = AnonymousClass1.this.new C00101(completion);
                c00101.p$ = (CoroutineScope) obj;
                return c00101;
            }

            @Override // kotlin.jvm.functions.Function2
            public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
                return ((C00101) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
            }

            @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
            public final Object invokeSuspend(Object $result) {
                IntrinsicsKt.getCOROUTINE_SUSPENDED();
                if (this.label != 0) {
                    throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                }
                ResultKt.throwOnFailure($result);
                CoroutineScope coroutineScope = this.p$;
                SmartAsyncMediatorLiveData.this.updateAsync();
                return Unit.INSTANCE;
            }
        }
    }

    @Override // com.android.permissioncontroller.permission.data.SmartUpdateMediatorLiveData, androidx.lifecycle.MediatorLiveData, androidx.lifecycle.LiveData
    protected void onInactive() {
        cancelJobIfRunning();
        this.jobQueued = false;
        super.onInactive();
    }

    private final void cancelJobIfRunning() {
        Job job = this.currentJob;
        if (job != null && job.isActive()) {
            Job.DefaultImpls.cancel$default(job, (CancellationException) null, 1, (Object) null);
        }
    }
}
