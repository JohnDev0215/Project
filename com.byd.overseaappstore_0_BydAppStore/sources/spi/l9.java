package spi;

import com.byd.overseaappstore.module.ability.data.generator.DataGenerator;
import com.byd.overseaappstore.module.ability.util.download.data.SoftwareOptParam;
import com.byd.overseaappstore.module.ability.util.notify.alone.SoftwareNeedStartInstallNotify;
import com.byd.overseaappstore.module.ability.util.notify.alone.line.ExecutorHciLineDispatch;
import com.byd.overseaappstore.module.ability.util.notify.alone.task.ExecutorTaskSchedule;
import java.util.LinkedHashMap;
import java.util.Map;
import kotlin.KotlinNothingValueException;
import kotlin.Pair;
import kotlin.ResultKt;
import kotlin.Triple;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.BuildersKt__Builders_commonKt;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.CoroutineScopeKt;
import kotlinx.coroutines.Dispatchers;
import kotlinx.coroutines.Job;
import kotlinx.coroutines.JobKt__JobKt;
import kotlinx.coroutines.flow.FlowCollector;
import kotlinx.coroutines.flow.StateFlow;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/* JADX INFO: loaded from: classes.dex */
public final class l9 {

    @NotNull
    public final i9 a;

    @NotNull
    public final ExecutorHciLineDispatch b;

    @NotNull
    public final DataGenerator c;

    @NotNull
    public final ExecutorTaskSchedule d;

    @NotNull
    public final SoftwareNeedStartInstallNotify e;

    @NotNull
    public final Map<String, i7> f;

    @NotNull
    public final Map<String, SoftwareOptParam> g;

    @DebugMetadata(c = "com.byd.overseaappstore.module.ability.util.download.task.ExecutorDownloadTaskMgr$1", f = "ExecutorDownloadTaskMgr.kt", i = {}, l = {53}, m = "invokeSuspend", n = {}, s = {})
    public static final class a extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
        public int a;

        /* JADX INFO: renamed from: spi.l9$a$a, reason: collision with other inner class name */
        public static final class C0132a<T> implements FlowCollector {
            public final /* synthetic */ l9 a;

            public C0132a(l9 l9Var) {
                this.a = l9Var;
            }

            /* JADX WARN: Type inference failed for: r13v19, types: [java.util.LinkedHashMap, java.util.Map<java.lang.String, spi.i7>] */
            @Override // kotlinx.coroutines.flow.FlowCollector
            public final Object emit(Object obj, Continuation continuation) {
                Pair pair = (Pair) obj;
                a2 a2Var = (a2) pair.getFirst();
                Triple triple = (Triple) pair.getSecond();
                String str = (String) triple.getFirst();
                int iIntValue = ((Number) triple.getSecond()).intValue();
                SoftwareOptParam softwareOptParam = (SoftwareOptParam) triple.getThird();
                if (iIntValue == 0) {
                    ct.a("dTaskMgr taskFlow no intervene taskTypeTip:" + str + " taskProcessType:" + iIntValue, new Object[0]);
                } else if (iIntValue != 1) {
                    if (iIntValue != 2) {
                        if (iIntValue != 3) {
                            ct.b("dTaskMgr taskFlow unKnown taskTypeTip:" + str + " taskProcessType:" + iIntValue, new Object[0]);
                        } else if (softwareOptParam == null) {
                            ct.b("dTaskMgr taskFlow interrupt taskTypeTip:" + str + " taskProcessType:" + iIntValue + " optParam is null", new Object[0]);
                        } else {
                            String packageName = softwareOptParam.getPackageName();
                            int size = this.a.g.size();
                            StringBuilder sbK = d.k("dTaskMgr taskFlow interrupt taskTypeTip:", str, " taskProcessType:", iIntValue, " pkn:");
                            sbK.append(packageName);
                            sbK.append(" netInterruptOptParamMap.size:");
                            sbK.append(size);
                            sbK.append(" checkToAddIt");
                            ct.a(sbK.toString(), new Object[0]);
                            i7 i7Var = (i7) this.a.f.get(packageName);
                            if (i7Var == null) {
                                ct.b(d.f("dTaskMgr stopTaskOnly downloadTask is null pkn:", packageName), new Object[0]);
                            } else {
                                i7Var.cancel();
                            }
                            l9.a(this.a, packageName);
                            SoftwareOptParam softwareOptParamPutIfAbsent = this.a.g.putIfAbsent(packageName, softwareOptParam);
                            if (softwareOptParamPutIfAbsent == IntrinsicsKt.getCOROUTINE_SUSPENDED()) {
                                return softwareOptParamPutIfAbsent;
                            }
                        }
                    } else if (softwareOptParam == null) {
                        ct.b("dTaskMgr taskFlow cancel taskTypeTip:" + str + " taskProcessType:" + iIntValue + " optParam is null", new Object[0]);
                    } else {
                        String packageName2 = softwareOptParam.getPackageName();
                        StringBuilder sbK2 = d.k("dTaskMgr taskFlow cancel taskTypeTip:", str, " taskProcessType:", iIntValue, " pkn:");
                        sbK2.append(packageName2);
                        ct.a(sbK2.toString(), new Object[0]);
                        l9.a(this.a, packageName2);
                    }
                } else if (softwareOptParam == null) {
                    ct.b("dTaskMgr taskFlow finish taskTypeTip:" + str + " taskProcessType:" + iIntValue + " optParam is null", new Object[0]);
                } else {
                    String packageName3 = softwareOptParam.getPackageName();
                    l9.a(this.a, packageName3);
                    int iY = this.a.a.y();
                    ct.a(hi.g(d.k("dTaskMgr taskFlow finish taskTypeTip:", str, " taskProcessType:", iIntValue, " pkn:"), packageName3, " networkCount:", iY), new Object[0]);
                    if (iY < 3) {
                        ct.a(hi.g(d.k("dTaskMgr taskFlow finish autoStart making wait->work taskTypeTip:", str, " taskProcessType:", iIntValue, " pkn:"), packageName3, " networkCount:", iY), new Object[0]);
                        this.a.b.notifyLineDispatch(this.a.c.parseToDispatchCacheCarrierPair(a2Var, (byte) 2, null, null, null));
                    }
                    this.a.e.notifyStartInstall(softwareOptParam);
                }
                return Unit.INSTANCE;
            }
        }

        public a(Continuation<? super a> continuation) {
            super(2, continuation);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        @NotNull
        public final Continuation<Unit> create(@Nullable Object obj, @NotNull Continuation<?> continuation) {
            return l9.this.new a(continuation);
        }

        @Override // kotlin.jvm.functions.Function2
        /* JADX INFO: renamed from: invoke */
        public final Object mo6invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
            return ((a) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        @Nullable
        public final Object invokeSuspend(@NotNull Object obj) {
            Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
            int i = this.a;
            if (i == 0) {
                ResultKt.throwOnFailure(obj);
                StateFlow<Pair<a2, Triple<String, Integer, SoftwareOptParam>>> taskFlow = l9.this.d.getTaskFlow();
                C0132a c0132a = new C0132a(l9.this);
                this.a = 1;
                if (taskFlow.collect(c0132a, this) == coroutine_suspended) {
                    return coroutine_suspended;
                }
            } else {
                if (i != 1) {
                    throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                }
                ResultKt.throwOnFailure(obj);
            }
            throw new KotlinNothingValueException();
        }
    }

    public l9(@NotNull i9 executorDbMgr, @NotNull ExecutorHciLineDispatch executorHciLineDispatch, @NotNull DataGenerator dataGenerator, @NotNull ExecutorTaskSchedule executorTaskSchedule, @NotNull SoftwareNeedStartInstallNotify softwareNeedStartInstallNotify) {
        Intrinsics.checkNotNullParameter(executorDbMgr, "executorDbMgr");
        Intrinsics.checkNotNullParameter(executorHciLineDispatch, "executorHciLineDispatch");
        Intrinsics.checkNotNullParameter(dataGenerator, "dataGenerator");
        Intrinsics.checkNotNullParameter(executorTaskSchedule, "executorTaskSchedule");
        Intrinsics.checkNotNullParameter(softwareNeedStartInstallNotify, "softwareNeedStartInstallNotify");
        this.a = executorDbMgr;
        this.b = executorHciLineDispatch;
        this.c = dataGenerator;
        this.d = executorTaskSchedule;
        this.e = softwareNeedStartInstallNotify;
        this.f = new LinkedHashMap();
        this.g = new LinkedHashMap();
        BuildersKt__Builders_commonKt.launch$default(CoroutineScopeKt.CoroutineScope(Dispatchers.getIO().plus(JobKt__JobKt.Job$default((Job) null, 1, (Object) null))), null, null, new a(null), 3, null);
    }

    public static final void a(l9 l9Var, String str) {
        l9Var.f.remove(str);
    }

    @NotNull
    public final l9 b(@NotNull String packageName, @NotNull i7 task) {
        Intrinsics.checkNotNullParameter(packageName, "packageName");
        Intrinsics.checkNotNullParameter(task, "task");
        this.f.put(packageName, task);
        return this;
    }

    /* JADX WARN: Type inference failed for: r0v2, types: [java.util.LinkedHashMap, java.util.Map<java.lang.String, com.byd.overseaappstore.module.ability.util.download.data.SoftwareOptParam>] */
    public final void c() {
        ct.a("dTaskMgr clearNetErrorInterruptOptParams", new Object[0]);
        this.g.clear();
    }

    /* JADX WARN: Type inference failed for: r0v1, types: [java.util.LinkedHashMap, java.util.Map<java.lang.String, spi.i7>] */
    public final void d(@NotNull String packageName) {
        Intrinsics.checkNotNullParameter(packageName, "packageName");
        i7 i7Var = (i7) this.f.get(packageName);
        if (i7Var == null) {
            ct.b(d.f("dTaskMgr startTask downloadTask is null pkn:", packageName), new Object[0]);
        } else {
            i7Var.start();
        }
    }

    /* JADX WARN: Type inference failed for: r0v1, types: [java.util.LinkedHashMap, java.util.Map<java.lang.String, spi.i7>] */
    public final void e(@NotNull String packageName) {
        Intrinsics.checkNotNullParameter(packageName, "packageName");
        i7 i7Var = (i7) this.f.get(packageName);
        if (i7Var == null) {
            ct.b(d.f("dTaskMgr stopTask downloadTask is null pkn:", packageName), new Object[0]);
        } else {
            i7Var.cancel();
            i7Var.a(2);
        }
    }
}
