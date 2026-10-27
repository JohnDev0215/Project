package kotlinx.coroutines.rx3;

import com.facebook.react.modules.appstate.AppStateModule;
import io.reactivex.rxjava3.core.Scheduler;
import io.reactivex.rxjava3.disposables.Disposable;
import java.util.concurrent.CancellationException;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicLongFieldUpdater;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.JvmField;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlinx.coroutines.BuildersKt__Builders_commonKt;
import kotlinx.coroutines.CompletableJob;
import kotlinx.coroutines.CoroutineDispatcher;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.CoroutineScopeKt;
import kotlinx.coroutines.Job;
import kotlinx.coroutines.SupervisorKt;
import kotlinx.coroutines.channels.Channel;
import kotlinx.coroutines.channels.ChannelKt;
import kotlinx.coroutines.channels.SendChannel;
import kotlinx.coroutines.rx3.DispatcherScheduler;

/* JADX INFO: compiled from: RxScheduler.kt */
/* JADX INFO: loaded from: classes7.dex */
@Metadata(d1 = {"\u0000V\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\t\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\b\u0002\u0018\u00002\u00020!:\u0001\u001fB\u000f\u0012\u0006\u0010\u0002\u001a\u00020\u0001¢\u0006\u0004\b\u0003\u0010\u0004J\u000f\u0010\u0006\u001a\u00020\u0005H\u0016¢\u0006\u0004\b\u0006\u0010\u0007J+\u0010\u0010\u001a\u00020\u000f2\n\u0010\n\u001a\u00060\bj\u0002`\t2\u0006\u0010\f\u001a\u00020\u000b2\u0006\u0010\u000e\u001a\u00020\rH\u0016¢\u0006\u0004\b\u0010\u0010\u0011J\u000f\u0010\u0013\u001a\u00020\u0012H\u0016¢\u0006\u0004\b\u0013\u0010\u0014J\u000f\u0010\u0016\u001a\u00020\u0015H\u0016¢\u0006\u0004\b\u0016\u0010\u0017R\u0014\u0010\u0002\u001a\u00020\u00018\u0006X\u0087\u0004¢\u0006\u0006\n\u0004\b\u0002\u0010\u0018R\u0014\u0010\u001a\u001a\u00020\u00198\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b\u001a\u0010\u001bR\u0014\u0010\u001d\u001a\u00020\u001c8\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b\u001d\u0010\u001e¨\u0006 "}, d2 = {"Lkotlinx/coroutines/rx3/DispatcherScheduler;", "Lkotlinx/coroutines/CoroutineDispatcher;", "dispatcher", "<init>", "(Lkotlinx/coroutines/CoroutineDispatcher;)V", "Lio/reactivex/rxjava3/core/Scheduler$Worker;", "createWorker", "()Lio/reactivex/rxjava3/core/Scheduler$Worker;", "Ljava/lang/Runnable;", "Lkotlinx/coroutines/Runnable;", "block", "", "delay", "Ljava/util/concurrent/TimeUnit;", "unit", "Lio/reactivex/rxjava3/disposables/Disposable;", "scheduleDirect", "(Ljava/lang/Runnable;JLjava/util/concurrent/TimeUnit;)Lio/reactivex/rxjava3/disposables/Disposable;", "", "shutdown", "()V", "", "toString", "()Ljava/lang/String;", "Lkotlinx/coroutines/CoroutineDispatcher;", "Lkotlinx/coroutines/CompletableJob;", "schedulerJob", "Lkotlinx/coroutines/CompletableJob;", "Lkotlinx/coroutines/CoroutineScope;", "scope", "Lkotlinx/coroutines/CoroutineScope;", "DispatcherWorker", "kotlinx-coroutines-rx3", "Lio/reactivex/rxjava3/core/Scheduler;"}, k = 1, mv = {1, 6, 0}, xi = 48)
final class DispatcherScheduler extends Scheduler {
    private static final /* synthetic */ AtomicLongFieldUpdater workerCounter$FU = AtomicLongFieldUpdater.newUpdater(DispatcherScheduler.class, "workerCounter");

    @JvmField
    public final CoroutineDispatcher dispatcher;
    private final CompletableJob schedulerJob;
    private final CoroutineScope scope;
    private volatile /* synthetic */ long workerCounter;

    public DispatcherScheduler(CoroutineDispatcher coroutineDispatcher) {
        this.dispatcher = coroutineDispatcher;
        CompletableJob completableJobSupervisorJob$default = SupervisorKt.SupervisorJob$default((Job) null, 1, (Object) null);
        this.schedulerJob = completableJobSupervisorJob$default;
        this.scope = CoroutineScopeKt.CoroutineScope(completableJobSupervisorJob$default.plus(coroutineDispatcher));
        this.workerCounter = 1L;
    }

    @Override // io.reactivex.rxjava3.core.Scheduler
    public Disposable scheduleDirect(Runnable block, long delay, TimeUnit unit) {
        return RxSchedulerKt.scheduleTask(this.scope, block, unit.toMillis(delay), new Function1<Function1<? super Continuation<? super Unit>, ? extends Object>, Runnable>() { // from class: kotlinx.coroutines.rx3.DispatcherScheduler.scheduleDirect.1
            {
                super(1);
            }

            @Override // kotlin.jvm.functions.Function1
            public final Runnable invoke(final Function1<? super Continuation<? super Unit>, ? extends Object> function1) {
                final DispatcherScheduler dispatcherScheduler = DispatcherScheduler.this;
                return new Runnable() { // from class: kotlinx.coroutines.rx3.DispatcherScheduler$scheduleDirect$1$invoke$$inlined$Runnable$1
                    @Override // java.lang.Runnable
                    public final void run() {
                        BuildersKt__Builders_commonKt.launch$default(dispatcherScheduler.scope, null, null, new DispatcherScheduler$scheduleDirect$1$1$1(function1, null), 3, null);
                    }
                };
            }
        });
    }

    @Override // io.reactivex.rxjava3.core.Scheduler
    public Scheduler.Worker createWorker() {
        return new DispatcherWorker(workerCounter$FU.getAndIncrement(this), this.dispatcher, this.schedulerJob);
    }

    @Override // io.reactivex.rxjava3.core.Scheduler
    public void shutdown() {
        Job.DefaultImpls.cancel$default((Job) this.schedulerJob, (CancellationException) null, 1, (Object) null);
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: compiled from: RxScheduler.kt */
    @Metadata(d1 = {"\u0000f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\t\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\b\u0002\u0018\u00002\u00020\u0001B\u001d\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0007¢\u0006\u0002\u0010\bJ\b\u0010\u0013\u001a\u00020\rH\u0016J\b\u0010\u0014\u001a\u00020\u0015H\u0016J$\u0010\u0016\u001a\u00020\u00172\n\u0010\u0018\u001a\u00060\u0019j\u0002`\u001a2\u0006\u0010\u001b\u001a\u00020\u00032\u0006\u0010\u001c\u001a\u00020\u001dH\u0016J\b\u0010\u001e\u001a\u00020\u001fH\u0016R-\u0010\t\u001a\u001e\u0012\u001a\u0012\u0018\b\u0001\u0012\n\u0012\b\u0012\u0004\u0012\u00020\r0\f\u0012\u0006\u0012\u0004\u0018\u00010\u000e0\u000b0\nX\u0082\u0004ø\u0001\u0000¢\u0006\u0002\n\u0000R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u000f\u001a\u00020\u0010X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0011\u001a\u00020\u0012X\u0082\u0004¢\u0006\u0002\n\u0000\u0082\u0002\u0004\n\u0002\b\u0019¨\u0006 "}, d2 = {"Lkotlinx/coroutines/rx3/DispatcherScheduler$DispatcherWorker;", "Lio/reactivex/rxjava3/core/Scheduler$Worker;", "counter", "", "dispatcher", "Lkotlinx/coroutines/CoroutineDispatcher;", "parentJob", "Lkotlinx/coroutines/Job;", "(JLkotlinx/coroutines/CoroutineDispatcher;Lkotlinx/coroutines/Job;)V", "blockChannel", "Lkotlinx/coroutines/channels/Channel;", "Lkotlin/Function1;", "Lkotlin/coroutines/Continuation;", "", "", "workerJob", "Lkotlinx/coroutines/CompletableJob;", "workerScope", "Lkotlinx/coroutines/CoroutineScope;", "dispose", "isDisposed", "", "schedule", "Lio/reactivex/rxjava3/disposables/Disposable;", "block", "Ljava/lang/Runnable;", "Lkotlinx/coroutines/Runnable;", "delay", "unit", "Ljava/util/concurrent/TimeUnit;", "toString", "", "kotlinx-coroutines-rx3"}, k = 1, mv = {1, 6, 0}, xi = 48)
    static final class DispatcherWorker extends Scheduler.Worker {
        private final Channel<Function1<Continuation<? super Unit>, Object>> blockChannel;
        private final long counter;
        private final CoroutineDispatcher dispatcher;
        private final CompletableJob workerJob;
        private final CoroutineScope workerScope;

        public DispatcherWorker(long j, CoroutineDispatcher coroutineDispatcher, Job job) {
            this.counter = j;
            this.dispatcher = coroutineDispatcher;
            CompletableJob completableJobSupervisorJob = SupervisorKt.SupervisorJob(job);
            this.workerJob = completableJobSupervisorJob;
            CoroutineScope CoroutineScope = CoroutineScopeKt.CoroutineScope(completableJobSupervisorJob.plus(coroutineDispatcher));
            this.workerScope = CoroutineScope;
            this.blockChannel = ChannelKt.Channel$default(Integer.MAX_VALUE, null, null, 6, null);
            BuildersKt__Builders_commonKt.launch$default(CoroutineScope, null, null, new AnonymousClass1(null), 3, null);
        }

        /* JADX INFO: renamed from: kotlinx.coroutines.rx3.DispatcherScheduler$DispatcherWorker$1, reason: invalid class name */
        /* JADX INFO: compiled from: RxScheduler.kt */
        @Metadata(bv = {}, d1 = {"\u0000\n\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0000\u0010\u0002\u001a\u00020\u0001*\u00020\u0000H\u008a@"}, d2 = {"Lkotlinx/coroutines/CoroutineScope;", "", "<anonymous>"}, k = 3, mv = {1, 6, 0})
        @DebugMetadata(c = "kotlinx.coroutines.rx3.DispatcherScheduler$DispatcherWorker$1", f = "RxScheduler.kt", i = {0, 1}, l = {191, 83}, m = "invokeSuspend", n = {"$this$consume$iv$iv", "$this$consume$iv$iv"}, s = {"L$0", "L$0"})
        static final class AnonymousClass1 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
            Object L$0;
            Object L$1;
            int label;

            AnonymousClass1(Continuation<? super AnonymousClass1> continuation) {
                super(2, continuation);
            }

            @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
            public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
                return DispatcherWorker.this.new AnonymousClass1(continuation);
            }

            @Override // kotlin.jvm.functions.Function2
            /* JADX INFO: renamed from: invoke, reason: avoid collision after fix types in other method and merged with bridge method [inline-methods] */
            public final Object mo4invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
                return ((AnonymousClass1) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
            }

            /* JADX WARN: Removed duplicated region for block: B:19:0x004b A[RETURN] */
            /* JADX WARN: Removed duplicated region for block: B:20:0x004c  */
            /* JADX WARN: Removed duplicated region for block: B:24:0x005a A[Catch: all -> 0x0077, TRY_LEAVE, TryCatch #0 {all -> 0x0077, blocks: (B:22:0x0052, B:24:0x005a), top: B:37:0x0052 }] */
            /* JADX WARN: Removed duplicated region for block: B:28:0x0071  */
            /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:27:0x006d -> B:17:0x003f). Please report as a decompilation issue!!! */
            @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
            /*
                Code decompiled incorrectly, please refer to instructions dump.
                To view partially-correct code enable 'Show inconsistent code' option in preferences
            */
            public final java.lang.Object invokeSuspend(java.lang.Object r9) {
                /*
                    r8 = this;
                    java.lang.Object r0 = kotlin.coroutines.intrinsics.IntrinsicsKt.getCOROUTINE_SUSPENDED()
                    int r1 = r8.label
                    r2 = 2
                    r3 = 1
                    if (r1 == 0) goto L31
                    if (r1 == r3) goto L23
                    if (r1 != r2) goto L1b
                    java.lang.Object r1 = r8.L$1
                    kotlinx.coroutines.channels.ChannelIterator r1 = (kotlinx.coroutines.channels.ChannelIterator) r1
                    java.lang.Object r4 = r8.L$0
                    kotlinx.coroutines.channels.ReceiveChannel r4 = (kotlinx.coroutines.channels.ReceiveChannel) r4
                    kotlin.ResultKt.throwOnFailure(r9)     // Catch: java.lang.Throwable -> L7a
                    r9 = r1
                    goto L3e
                L1b:
                    java.lang.IllegalStateException r9 = new java.lang.IllegalStateException
                    java.lang.String r0 = "call to 'resume' before 'invoke' with coroutine"
                    r9.<init>(r0)
                    throw r9
                L23:
                    java.lang.Object r1 = r8.L$1
                    kotlinx.coroutines.channels.ChannelIterator r1 = (kotlinx.coroutines.channels.ChannelIterator) r1
                    java.lang.Object r4 = r8.L$0
                    kotlinx.coroutines.channels.ReceiveChannel r4 = (kotlinx.coroutines.channels.ReceiveChannel) r4
                    kotlin.ResultKt.throwOnFailure(r9)     // Catch: java.lang.Throwable -> L7a
                    r5 = r4
                    r4 = r8
                    goto L51
                L31:
                    kotlin.ResultKt.throwOnFailure(r9)
                    kotlinx.coroutines.rx3.DispatcherScheduler$DispatcherWorker r9 = kotlinx.coroutines.rx3.DispatcherScheduler.DispatcherWorker.this
                    kotlinx.coroutines.channels.Channel r4 = kotlinx.coroutines.rx3.DispatcherScheduler.DispatcherWorker.access$getBlockChannel$p(r9)
                    kotlinx.coroutines.channels.ChannelIterator r9 = r4.iterator()     // Catch: java.lang.Throwable -> L7a
                L3e:
                    r1 = r8
                L3f:
                    r1.L$0 = r4     // Catch: java.lang.Throwable -> L7a
                    r1.L$1 = r9     // Catch: java.lang.Throwable -> L7a
                    r1.label = r3     // Catch: java.lang.Throwable -> L7a
                    java.lang.Object r5 = r9.hasNext(r1)     // Catch: java.lang.Throwable -> L7a
                    if (r5 != r0) goto L4c
                    return r0
                L4c:
                    r7 = r1
                    r1 = r9
                    r9 = r5
                    r5 = r4
                    r4 = r7
                L51:
                    r6 = 0
                    java.lang.Boolean r9 = (java.lang.Boolean) r9     // Catch: java.lang.Throwable -> L77
                    boolean r9 = r9.booleanValue()     // Catch: java.lang.Throwable -> L77
                    if (r9 == 0) goto L71
                    java.lang.Object r9 = r1.next()     // Catch: java.lang.Throwable -> L77
                    kotlin.jvm.functions.Function1 r9 = (kotlin.jvm.functions.Function1) r9     // Catch: java.lang.Throwable -> L77
                    r4.L$0 = r5     // Catch: java.lang.Throwable -> L77
                    r4.L$1 = r1     // Catch: java.lang.Throwable -> L77
                    r4.label = r2     // Catch: java.lang.Throwable -> L77
                    java.lang.Object r9 = r9.invoke(r4)     // Catch: java.lang.Throwable -> L77
                    if (r9 != r0) goto L6d
                    return r0
                L6d:
                    r9 = r1
                    r1 = r4
                    r4 = r5
                    goto L3f
                L71:
                    kotlinx.coroutines.channels.ChannelsKt.cancelConsumed(r5, r6)
                    kotlin.Unit r9 = kotlin.Unit.INSTANCE
                    return r9
                L77:
                    r9 = move-exception
                    r4 = r5
                    goto L7b
                L7a:
                    r9 = move-exception
                L7b:
                    throw r9     // Catch: java.lang.Throwable -> L7c
                L7c:
                    r0 = move-exception
                    kotlinx.coroutines.channels.ChannelsKt.cancelConsumed(r4, r9)
                    throw r0
                */
                throw new UnsupportedOperationException("Method not decompiled: kotlinx.coroutines.rx3.DispatcherScheduler.DispatcherWorker.AnonymousClass1.invokeSuspend(java.lang.Object):java.lang.Object");
            }
        }

        @Override // io.reactivex.rxjava3.core.Scheduler.Worker
        public Disposable schedule(Runnable block, long delay, TimeUnit unit) {
            return RxSchedulerKt.scheduleTask(this.workerScope, block, unit.toMillis(delay), new Function1<Function1<? super Continuation<? super Unit>, ? extends Object>, Runnable>() { // from class: kotlinx.coroutines.rx3.DispatcherScheduler$DispatcherWorker$schedule$1
                {
                    super(1);
                }

                @Override // kotlin.jvm.functions.Function1
                public final Runnable invoke(final Function1<? super Continuation<? super Unit>, ? extends Object> function1) {
                    final DispatcherScheduler.DispatcherWorker dispatcherWorker = this.this$0;
                    return new Runnable() { // from class: kotlinx.coroutines.rx3.DispatcherScheduler$DispatcherWorker$schedule$1$invoke$$inlined$Runnable$1
                        @Override // java.lang.Runnable
                        public final void run() {
                            dispatcherWorker.blockChannel.mo55trySendJP2dKIU(function1);
                        }
                    };
                }
            });
        }

        @Override // io.reactivex.rxjava3.disposables.Disposable
        public boolean isDisposed() {
            return !CoroutineScopeKt.isActive(this.workerScope);
        }

        @Override // io.reactivex.rxjava3.disposables.Disposable
        public void dispose() {
            SendChannel.DefaultImpls.close$default(this.blockChannel, null, 1, null);
            Job.DefaultImpls.cancel$default((Job) this.workerJob, (CancellationException) null, 1, (Object) null);
        }

        public String toString() {
            StringBuilder sb = new StringBuilder();
            sb.append(this.dispatcher);
            sb.append(" (worker ");
            sb.append(this.counter);
            sb.append(", ");
            sb.append(isDisposed() ? "disposed" : AppStateModule.APP_STATE_ACTIVE);
            sb.append(')');
            return sb.toString();
        }
    }

    public String toString() {
        return this.dispatcher.toString();
    }
}
