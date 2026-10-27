package spi;

import android.content.Context;
import com.byd.overseaappstore.module.ability.data.generator.DataGenerator;
import com.byd.overseaappstore.module.ability.util.download.data.SoftwareOptParam;
import com.byd.overseaappstore.module.ability.util.download.data.SoftwareOptParamWrap;
import com.byd.overseaappstore.module.ability.util.download.data.SoftwareStateData;
import com.byd.overseaappstore.module.ability.util.download.task.DownloadTaskPauseToResume;
import com.byd.overseaappstore.module.ability.util.download.task.DownloadTaskStart;
import com.byd.overseaappstore.module.ability.util.download.task.DownloadTaskUpdatePauseToResume;
import com.byd.overseaappstore.module.ability.util.download.task.DownloadTaskUpdateStart;
import com.byd.overseaappstore.module.ability.util.notify.alone.SoftwareNeedStartInstallNotify;
import com.byd.overseaappstore.module.ability.util.notify.alone.line.ExecutorHciDispatchMgr;
import com.byd.overseaappstore.module.ability.util.notify.alone.task.ExecutorTaskSchedule;
import com.byd.overseaappstore.module.ability.util.notify.alone.trigger.ExecutorSoftwareNewTaskInTrigger;
import com.byd.overseaappstore.module.ability.util.notify.router.ExecutorDialogRouterMgr;
import java.util.Objects;
import kotlin.Pair;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.ContinuationImpl;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.Dispatchers;
import kotlinx.coroutines.flow.Flow;
import kotlinx.coroutines.flow.FlowCollector;
import kotlinx.coroutines.flow.FlowKt;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/* JADX INFO: loaded from: classes.dex */
public final class u9 extends t9<mv> {

    @NotNull
    public final i9 b;

    @NotNull
    public final h7 c;

    @NotNull
    public final DataGenerator d;

    @NotNull
    public final oa e;

    @NotNull
    public final ExecutorTaskSchedule f;

    @NotNull
    public final ExecutorSoftwareNewTaskInTrigger g;

    @NotNull
    public final Context h;

    @NotNull
    public final l9 i;

    @NotNull
    public final ExecutorDialogRouterMgr j;

    @NotNull
    public final aa k;

    @NotNull
    public final ExecutorHciDispatchMgr l;

    @NotNull
    public final SoftwareNeedStartInstallNotify m;

    @DebugMetadata(c = "com.byd.overseaappstore.module.ability.util.operate.factory.linea.ExecutorHciWorkExecute", f = "ExecutorHciWorkExecute.kt", i = {}, l = {381}, m = "handleDeleteApk", n = {}, s = {})
    public static final class a extends ContinuationImpl {
        public /* synthetic */ Object a;
        public int c;

        public a(Continuation<? super a> continuation) {
            super(continuation);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        @Nullable
        public final Object invokeSuspend(@NotNull Object obj) {
            this.a = obj;
            this.c |= Integer.MIN_VALUE;
            return u9.this.a(null, null, this);
        }
    }

    @DebugMetadata(c = "com.byd.overseaappstore.module.ability.util.operate.factory.linea.ExecutorHciWorkExecute", f = "ExecutorHciWorkExecute.kt", i = {0, 0, 0, 0, 1, 1, 1, 1}, l = {359, 360}, m = "handleDeleteTask", n = {"this", "bizSrc", "packageName", "state", "this", "bizSrc", "packageName", "state"}, s = {"L$0", "L$1", "L$2", "I$0", "L$0", "L$1", "L$2", "I$0"})
    public static final class b extends ContinuationImpl {
        public u9 a;
        public a2 b;
        public String c;
        public int d;
        public /* synthetic */ Object e;
        public int g;

        public b(Continuation<? super b> continuation) {
            super(continuation);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        @Nullable
        public final Object invokeSuspend(@NotNull Object obj) {
            this.e = obj;
            this.g |= Integer.MIN_VALUE;
            return u9.this.b(null, null, this);
        }
    }

    public static final class c<T> implements FlowCollector {
        public static final c<T> a = new c<>();

        @Override // kotlinx.coroutines.flow.FlowCollector
        public final Object emit(Object obj, Continuation continuation) {
            SoftwareOptParam optParam = ((mv) obj).getOptParam();
            ct.a(spi.e.d("executorHciWorkExecute handleInitSync all software activeCollect pkn:", optParam != null ? optParam.getPackageName() : null, " "), new Object[0]);
            return Unit.INSTANCE;
        }
    }

    public static final class d<T> implements FlowCollector {
        public final /* synthetic */ a2 a;
        public final /* synthetic */ u9 b;

        public d(a2 a2Var, u9 u9Var) {
            this.a = a2Var;
            this.b = u9Var;
        }

        @Override // kotlinx.coroutines.flow.FlowCollector
        public final Object emit(Object obj, Continuation continuation) {
            l9 l9Var;
            String packageName;
            i7 downloadTaskPauseToResume;
            SoftwareOptParamWrap optParamWrap = ((mv) obj).getOptParamWrap();
            if (optParamWrap == null) {
                ct.b(spi.e.e("executorHciWorkExecute handleResumeActing optParamWrap is null bizSrc:", this.a), new Object[0]);
            } else {
                SoftwareOptParam softwareOptParam = optParamWrap.getSoftwareOptParam();
                Pair<SoftwareStateData, SoftwareStateData> pair = optParamWrap.getPair();
                if (pair == null) {
                    ct.b(spi.e.d("executorHciWorkExecute handleResumeActing pairStateData is null pkn:", softwareOptParam.getPackageName(), " "), new Object[0]);
                } else {
                    int state = pair.getFirst().getState();
                    if (state == 4) {
                        ct.a("executorHciWorkExecute handleResumeActing prev PAUSE prevState:" + state + " pkn:" + softwareOptParam.getPackageName() + " ", new Object[0]);
                        l9Var = this.b.i;
                        packageName = softwareOptParam.getPackageName();
                        u9 u9Var = this.b;
                        downloadTaskPauseToResume = new DownloadTaskPauseToResume(u9Var.b, u9Var.c, u9Var.d, u9Var.e, u9Var.f, softwareOptParam, this.a);
                    } else if (state != 11) {
                        ct.b("executorHciWorkExecute handleResumeActing prev unKnown prevState:" + state + " pkn:" + softwareOptParam.getPackageName() + "  ", new Object[0]);
                    } else {
                        ct.a("executorHciWorkExecute handleResumeActing prev UPDATING_PAUSE prevState:" + state + " pkn:" + softwareOptParam.getPackageName() + " ", new Object[0]);
                        l9Var = this.b.i;
                        packageName = softwareOptParam.getPackageName();
                        u9 u9Var2 = this.b;
                        downloadTaskPauseToResume = new DownloadTaskUpdatePauseToResume(u9Var2.b, u9Var2.c, u9Var2.d, u9Var2.e, u9Var2.f, softwareOptParam, this.a);
                    }
                    l9Var.b(packageName, downloadTaskPauseToResume);
                    l9Var.d(softwareOptParam.getPackageName());
                }
            }
            return Unit.INSTANCE;
        }
    }

    public static final class e<T> implements FlowCollector {
        public final /* synthetic */ a2 a;
        public final /* synthetic */ u9 b;

        public e(a2 a2Var, u9 u9Var) {
            this.a = a2Var;
            this.b = u9Var;
        }

        @Override // kotlinx.coroutines.flow.FlowCollector
        public final Object emit(Object obj, Continuation continuation) {
            l9 l9Var;
            String packageName;
            i7 downloadTaskPauseToResume;
            DataGenerator dataGenerator;
            SoftwareOptParamWrap optParamWrap = ((mv) obj).getOptParamWrap();
            int i = 0;
            if (optParamWrap != null) {
                SoftwareOptParam softwareOptParam = optParamWrap.getSoftwareOptParam();
                Pair<SoftwareStateData, SoftwareStateData> pair = optParamWrap.getPair();
                int iB = this.b.e.b(softwareOptParam.getPackageName());
                int iY = this.b.b.y();
                String packageName2 = softwareOptParam.getPackageName();
                a2 a2Var = this.a;
                StringBuilder sbL = spi.e.l("executorHciWorkExecute handleWork state:", iB, " networkCount：", iY, " pkn:");
                sbL.append(packageName2);
                sbL.append(" bizSrc:");
                sbL.append(a2Var);
                ct.a(sbL.toString(), new Object[0]);
                switch (iB) {
                    case 2:
                        if (pair != null) {
                            int state = pair.getFirst().getState();
                            if (state == 0) {
                                String packageName3 = softwareOptParam.getPackageName();
                                a2 a2Var2 = this.a;
                                StringBuilder sbL2 = spi.e.l("executorHciWorkExecute handleWork pairStateData SoftwareState.UN_DOWNLOAD state:", iB, " prevState:", state, " networkCount：");
                                spi.d.t(sbL2, iY, " pkn:", packageName3, " bizSrc:");
                                sbL2.append(a2Var2);
                                ct.a(sbL2.toString(), new Object[0]);
                                l9Var = this.b.i;
                                String packageName4 = softwareOptParam.getPackageName();
                                u9 u9Var = this.b;
                                l9Var.b(packageName4, new DownloadTaskStart(u9Var.b, u9Var.c, u9Var.d, u9Var.e, u9Var.f, u9Var.g, u9Var.h, softwareOptParam, this.a));
                                l9Var.d(softwareOptParam.getPackageName());
                            } else if (state == 4) {
                                String packageName5 = softwareOptParam.getPackageName();
                                a2 a2Var3 = this.a;
                                StringBuilder sbL3 = spi.e.l("executorHciWorkExecute handleWork pairStateData SoftwareState.PAUSE state:", iB, " prevState:", state, " networkCount：");
                                spi.d.t(sbL3, iY, " pkn:", packageName5, " bizSrc:");
                                sbL3.append(a2Var3);
                                ct.a(sbL3.toString(), new Object[0]);
                                l9Var = this.b.i;
                                packageName = softwareOptParam.getPackageName();
                                u9 u9Var2 = this.b;
                                downloadTaskPauseToResume = new DownloadTaskPauseToResume(u9Var2.b, u9Var2.c, u9Var2.d, u9Var2.e, u9Var2.f, softwareOptParam, this.a);
                                l9Var.b(packageName, downloadTaskPauseToResume);
                                l9Var.d(softwareOptParam.getPackageName());
                            } else {
                                String packageName6 = softwareOptParam.getPackageName();
                                a2 a2Var4 = this.a;
                                StringBuilder sbL4 = spi.e.l("executorHciWorkExecute handleWork pairStateData SoftwareState.DOWNLOAD_CONNECTING otherState state:", iB, " networkCount：", iY, " pkn:");
                                sbL4.append(packageName6);
                                sbL4.append(" bizSrc:");
                                sbL4.append(a2Var4);
                                ct.b(sbL4.toString(), new Object[0]);
                            }
                        } else {
                            String packageName7 = softwareOptParam.getPackageName();
                            a2 a2Var5 = this.a;
                            StringBuilder sbL5 = spi.e.l("executorHciWorkExecute handleWork pairStateData is null SoftwareState.DOWNLOAD_CONNECTING state:", iB, " networkCount：", iY, " pkn:");
                            sbL5.append(packageName7);
                            sbL5.append(" bizSrc:");
                            sbL5.append(a2Var5);
                            ct.b(sbL5.toString(), new Object[0]);
                        }
                        break;
                    case 3:
                    case 10:
                        this.b.i.e(softwareOptParam.getPackageName());
                        break;
                    case 4:
                        l9Var = this.b.i;
                        packageName = softwareOptParam.getPackageName();
                        u9 u9Var3 = this.b;
                        downloadTaskPauseToResume = new DownloadTaskPauseToResume(u9Var3.b, u9Var3.c, u9Var3.d, u9Var3.e, u9Var3.f, softwareOptParam, this.a);
                        l9Var.b(packageName, downloadTaskPauseToResume);
                        l9Var.d(softwareOptParam.getPackageName());
                        break;
                    case 5:
                        r3 r3Var = r3.a;
                        if (r3Var.a() || r3Var.h() || r3Var.g() || r3Var.i()) {
                            dataGenerator = this.b.d;
                            this.b.j.addBizTypeAndData(3, dataGenerator.parseToIUUState(softwareOptParam, i)).workNotify();
                        }
                        this.b.m.notifyStartInstall(softwareOptParam);
                        break;
                    case 6:
                        u9 u9Var4 = this.b;
                        aa aaVar = u9Var4.k;
                        Context context = u9Var4.h;
                        Objects.requireNonNull(aaVar);
                        Intrinsics.checkNotNullParameter(softwareOptParam, "softwareOptParam");
                        Intrinsics.checkNotNullParameter(context, "context");
                        aaVar.a(softwareOptParam.getSourceType()).d(softwareOptParam, context);
                        break;
                    case 9:
                        if (pair != null) {
                            int state2 = pair.getFirst().getState();
                            if (state2 == 7) {
                                String packageName8 = softwareOptParam.getPackageName();
                                a2 a2Var6 = this.a;
                                StringBuilder sbL6 = spi.e.l("executorHciWorkExecute handleWork pairStateData SoftwareState.TO_UPDATE state:", iB, " prevState:", state2, " networkCount：");
                                spi.d.t(sbL6, iY, " pkn:", packageName8, " bizSrc:");
                                sbL6.append(a2Var6);
                                ct.a(sbL6.toString(), new Object[0]);
                                l9Var = this.b.i;
                                String packageName9 = softwareOptParam.getPackageName();
                                u9 u9Var5 = this.b;
                                l9Var.b(packageName9, new DownloadTaskUpdateStart(u9Var5.b, u9Var5.c, u9Var5.d, u9Var5.e, u9Var5.f, u9Var5.h, softwareOptParam, this.a));
                                l9Var.d(softwareOptParam.getPackageName());
                            } else if (state2 == 11) {
                                String packageName10 = softwareOptParam.getPackageName();
                                a2 a2Var7 = this.a;
                                StringBuilder sbL7 = spi.e.l("executorHciWorkExecute handleWork pairStateData SoftwareState.UPDATING_PAUSE state:", iB, " prevState:", state2, " networkCount：");
                                spi.d.t(sbL7, iY, " pkn:", packageName10, " bizSrc:");
                                sbL7.append(a2Var7);
                                ct.a(sbL7.toString(), new Object[0]);
                                l9Var = this.b.i;
                                packageName = softwareOptParam.getPackageName();
                                u9 u9Var6 = this.b;
                                downloadTaskPauseToResume = new DownloadTaskUpdatePauseToResume(u9Var6.b, u9Var6.c, u9Var6.d, u9Var6.e, u9Var6.f, softwareOptParam, this.a);
                                l9Var.b(packageName, downloadTaskPauseToResume);
                                l9Var.d(softwareOptParam.getPackageName());
                            } else {
                                String packageName11 = softwareOptParam.getPackageName();
                                a2 a2Var8 = this.a;
                                StringBuilder sbL8 = spi.e.l("executorHciWorkExecute handleWork pairStateData SoftwareState.UPDATE_CONNECTING otherState state:", iB, " networkCount：", iY, " pkn:");
                                sbL8.append(packageName11);
                                sbL8.append(" bizSrc:");
                                sbL8.append(a2Var8);
                                ct.b(sbL8.toString(), new Object[0]);
                            }
                        } else {
                            String packageName12 = softwareOptParam.getPackageName();
                            a2 a2Var9 = this.a;
                            StringBuilder sbL9 = spi.e.l("executorHciWorkExecute handleWork pairStateData is null SoftwareState.UPDATE_CONNECTING state:", iB, " networkCount：", iY, " pkn:");
                            sbL9.append(packageName12);
                            sbL9.append(" bizSrc:");
                            sbL9.append(a2Var9);
                            ct.b(sbL9.toString(), new Object[0]);
                        }
                        break;
                    case 11:
                        l9Var = this.b.i;
                        packageName = softwareOptParam.getPackageName();
                        u9 u9Var7 = this.b;
                        downloadTaskPauseToResume = new DownloadTaskUpdatePauseToResume(u9Var7.b, u9Var7.c, u9Var7.d, u9Var7.e, u9Var7.f, softwareOptParam, this.a);
                        l9Var.b(packageName, downloadTaskPauseToResume);
                        l9Var.d(softwareOptParam.getPackageName());
                        break;
                    case 12:
                        if (r3.a.a()) {
                            dataGenerator = this.b.d;
                            i = 1;
                            this.b.j.addBizTypeAndData(3, dataGenerator.parseToIUUState(softwareOptParam, i)).workNotify();
                        }
                        this.b.m.notifyStartInstall(softwareOptParam);
                        break;
                }
            } else {
                ct.b(spi.e.e("executorHciWorkExecute handleWork softwareOptParamWrap is null bizSrc:", this.a), new Object[0]);
            }
            return Unit.INSTANCE;
        }
    }

    public u9(@NotNull i9 executorDbMgr, @NotNull h7 downloadRepository, @NotNull DataGenerator dataGenerator, @NotNull oa executorSoftwareStateMgr, @NotNull ExecutorTaskSchedule executorTaskSchedule, @NotNull ExecutorSoftwareNewTaskInTrigger executorSoftwareNewTaskInTrigger, @NotNull Context context, @NotNull l9 executorDownloadTaskMgr, @NotNull ExecutorDialogRouterMgr executorDialogRouterMgr, @NotNull aa executorIuuWorkMgr, @NotNull ExecutorHciDispatchMgr executorHciDispatchMgr, @NotNull SoftwareNeedStartInstallNotify softwareNeedStartInstallNotify) {
        Intrinsics.checkNotNullParameter(executorDbMgr, "executorDbMgr");
        Intrinsics.checkNotNullParameter(downloadRepository, "downloadRepository");
        Intrinsics.checkNotNullParameter(dataGenerator, "dataGenerator");
        Intrinsics.checkNotNullParameter(executorSoftwareStateMgr, "executorSoftwareStateMgr");
        Intrinsics.checkNotNullParameter(executorTaskSchedule, "executorTaskSchedule");
        Intrinsics.checkNotNullParameter(executorSoftwareNewTaskInTrigger, "executorSoftwareNewTaskInTrigger");
        Intrinsics.checkNotNullParameter(context, "context");
        Intrinsics.checkNotNullParameter(executorDownloadTaskMgr, "executorDownloadTaskMgr");
        Intrinsics.checkNotNullParameter(executorDialogRouterMgr, "executorDialogRouterMgr");
        Intrinsics.checkNotNullParameter(executorIuuWorkMgr, "executorIuuWorkMgr");
        Intrinsics.checkNotNullParameter(executorHciDispatchMgr, "executorHciDispatchMgr");
        Intrinsics.checkNotNullParameter(softwareNeedStartInstallNotify, "softwareNeedStartInstallNotify");
        this.b = executorDbMgr;
        this.c = downloadRepository;
        this.d = dataGenerator;
        this.e = executorSoftwareStateMgr;
        this.f = executorTaskSchedule;
        this.g = executorSoftwareNewTaskInTrigger;
        this.h = context;
        this.i = executorDownloadTaskMgr;
        this.j = executorDialogRouterMgr;
        this.k = executorIuuWorkMgr;
        this.l = executorHciDispatchMgr;
        this.m = softwareNeedStartInstallNotify;
    }

    /* JADX WARN: Removed duplicated region for block: B:7:0x0013  */
    @Override // spi.t9
    @org.jetbrains.annotations.Nullable
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final java.lang.Object a(@org.jetbrains.annotations.NotNull spi.a2 r6, @org.jetbrains.annotations.NotNull java.lang.String r7, @org.jetbrains.annotations.NotNull kotlin.coroutines.Continuation<? super kotlin.Unit> r8) {
        /*
            r5 = this;
            boolean r0 = r8 instanceof spi.u9.a
            if (r0 == 0) goto L13
            r0 = r8
            spi.u9$a r0 = (spi.u9.a) r0
            int r1 = r0.c
            r2 = -2147483648(0xffffffff80000000, float:-0.0)
            r3 = r1 & r2
            if (r3 == 0) goto L13
            int r1 = r1 - r2
            r0.c = r1
            goto L18
        L13:
            spi.u9$a r0 = new spi.u9$a
            r0.<init>(r8)
        L18:
            java.lang.Object r8 = r0.a
            java.lang.Object r1 = kotlin.coroutines.intrinsics.IntrinsicsKt.getCOROUTINE_SUSPENDED()
            int r2 = r0.c
            r3 = 1
            r4 = 0
            if (r2 == 0) goto L32
            if (r2 != r3) goto L2a
            kotlin.ResultKt.throwOnFailure(r8)
            goto L5e
        L2a:
            java.lang.IllegalStateException r6 = new java.lang.IllegalStateException
            java.lang.String r7 = "call to 'resume' before 'invoke' with coroutine"
            r6.<init>(r7)
            throw r6
        L32:
            kotlin.ResultKt.throwOnFailure(r8)
            java.lang.StringBuilder r8 = new java.lang.StringBuilder
            r8.<init>()
            java.lang.String r2 = "executorHciWorkExecute handleDeleteApk pkn:"
            r8.append(r2)
            r8.append(r7)
            java.lang.String r2 = " bizSrc:"
            r8.append(r2)
            r8.append(r6)
            java.lang.String r6 = r8.toString()
            java.lang.Object[] r8 = new java.lang.Object[r4]
            spi.ct.a(r6, r8)
            spi.i9 r6 = r5.b
            r0.c = r3
            java.lang.Object r8 = r6.z(r7, r0)
            if (r8 != r1) goto L5e
            return r1
        L5e:
            spi.qq r8 = (spi.qq) r8
            if (r8 != 0) goto L6c
            java.lang.Object[] r6 = new java.lang.Object[r4]
            java.lang.String r7 = "executorHciWorkExecute handleDeleteApk softwareLocal is null"
            spi.ct.b(r7, r6)
        L69:
            kotlin.Unit r6 = kotlin.Unit.INSTANCE
            return r6
        L6c:
            spi.zb r6 = spi.zb.a
            spi.pq r7 = r8.a
            java.lang.String r7 = r7.d
            boolean r6 = r6.b(r7)
            spi.pq r7 = r8.a
            java.lang.String r7 = r7.d
            java.lang.StringBuilder r8 = new java.lang.StringBuilder
            r8.<init>()
            java.lang.String r0 = "executorHciWorkExecute handleDeleteApk delete file ret "
            r8.append(r0)
            r8.append(r6)
            java.lang.String r6 = " sourceDir:"
            r8.append(r6)
            r8.append(r7)
            java.lang.String r6 = r8.toString()
            java.lang.Object[] r7 = new java.lang.Object[r4]
            spi.ct.a(r6, r7)
            goto L69
        */
        throw new UnsupportedOperationException("Method not decompiled: spi.u9.a(spi.a2, java.lang.String, kotlin.coroutines.Continuation):java.lang.Object");
    }

    /* JADX WARN: Removed duplicated region for block: B:32:0x00eb  */
    /* JADX WARN: Removed duplicated region for block: B:7:0x001b  */
    @Override // spi.t9
    @org.jetbrains.annotations.Nullable
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final java.lang.Object b(@org.jetbrains.annotations.NotNull spi.a2 r21, @org.jetbrains.annotations.NotNull java.lang.String r22, @org.jetbrains.annotations.NotNull kotlin.coroutines.Continuation<? super kotlin.Unit> r23) {
        /*
            Method dump skipped, instruction units count: 261
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: spi.u9.b(spi.a2, java.lang.String, kotlin.coroutines.Continuation):java.lang.Object");
    }

    @Override // spi.t9
    @Nullable
    public final Object c(@NotNull a2 a2Var, @NotNull Flow<? extends mv> flow, @NotNull Continuation<? super Unit> continuation) {
        ct.a(spi.e.e("executorHciWorkExecute handleInitSync bizSrc:", a2Var), new Object[0]);
        Object objCollect = FlowKt.flowOn(flow, Dispatchers.getIO()).collect(c.a, continuation);
        return objCollect == IntrinsicsKt.getCOROUTINE_SUSPENDED() ? objCollect : Unit.INSTANCE;
    }

    @Override // spi.t9
    @Nullable
    public final Object d(@NotNull a2 a2Var, @NotNull Flow<? extends mv> flow, @NotNull Continuation<? super Unit> continuation) {
        ct.a(spi.e.e("executorHciWorkExecute handlePauseTasks bizSrc:", a2Var), new Object[0]);
        Object objJ = j(a2Var, flow, continuation);
        return objJ == IntrinsicsKt.getCOROUTINE_SUSPENDED() ? objJ : Unit.INSTANCE;
    }

    @Override // spi.t9
    @Nullable
    public final Object e(@NotNull a2 a2Var, @NotNull Flow<? extends mv> flow, @NotNull Continuation<? super Unit> continuation) {
        ct.a(spi.e.e("executorHciWorkExecute handleResumeActing bizSrc:", a2Var), new Object[0]);
        Object objCollect = flow.collect(new d(a2Var, this), continuation);
        return objCollect == IntrinsicsKt.getCOROUTINE_SUSPENDED() ? objCollect : Unit.INSTANCE;
    }

    @Override // spi.t9
    @Nullable
    public final Object f(@NotNull a2 a2Var, @NotNull Flow<? extends mv> flow, @NotNull Continuation<? super Unit> continuation) {
        ct.a(spi.e.e("executorHciWorkExecute handleStartDownloadOnlyTasks bizSrc:", a2Var), new Object[0]);
        Object objJ = j(a2Var, flow, continuation);
        return objJ == IntrinsicsKt.getCOROUTINE_SUSPENDED() ? objJ : Unit.INSTANCE;
    }

    @Override // spi.t9
    @Nullable
    public final Object g(@NotNull a2 a2Var, @NotNull Flow<? extends mv> flow, @NotNull Continuation<? super Unit> continuation) {
        ct.a(spi.e.e("executorHciWorkExecute handleStartNetErrorTotalOnlyTasks bizSrc:", a2Var), new Object[0]);
        Object objJ = j(a2Var, flow, continuation);
        return objJ == IntrinsicsKt.getCOROUTINE_SUSPENDED() ? objJ : Unit.INSTANCE;
    }

    @Override // spi.t9
    @Nullable
    public final Object h(@NotNull a2 a2Var, @NotNull Flow<? extends mv> flow, @NotNull Continuation<? super Unit> continuation) {
        ct.a(spi.e.e("executorHciWorkExecute handleStartUpdateOnlyTasks bizSrc:", a2Var), new Object[0]);
        Object objJ = j(a2Var, flow, continuation);
        return objJ == IntrinsicsKt.getCOROUTINE_SUSPENDED() ? objJ : Unit.INSTANCE;
    }

    @Override // spi.t9
    public final void i(a2 bizSrc, mv mvVar) {
        mv carrier = mvVar;
        Intrinsics.checkNotNullParameter(bizSrc, "bizSrc");
        Intrinsics.checkNotNullParameter(carrier, "carrier");
        SoftwareOptParam optParam = carrier.getOptParam();
        SoftwareOptParam optParam2 = carrier.getOptParam();
        ct.a("executorHciWorkExecute handleUninstall pkn:" + (optParam2 != null ? optParam2.getPackageName() : null) + " bizSrc:" + bizSrc, new Object[0]);
        if (optParam == null) {
            ct.b("executorHciWorkExecute handleUninstall optParam is null ", new Object[0]);
        } else {
            this.j.addBizTypeAndData(3, this.d.parseToIUUState(optParam, 2)).workNotify();
        }
    }

    @Override // spi.t9
    @Nullable
    public final Object j(@NotNull a2 a2Var, @NotNull Flow<? extends mv> flow, @NotNull Continuation<? super Unit> continuation) {
        ct.a(spi.e.e("executorHciWorkExecute handleWork bizSrc:", a2Var), new Object[0]);
        Object objCollect = flow.collect(new e(a2Var, this), continuation);
        return objCollect == IntrinsicsKt.getCOROUTINE_SUSPENDED() ? objCollect : Unit.INSTANCE;
    }
}
