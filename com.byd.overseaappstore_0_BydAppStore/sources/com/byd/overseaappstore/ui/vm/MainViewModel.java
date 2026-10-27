package com.byd.overseaappstore.ui.vm;

import android.content.Context;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelKt;
import com.byd.overseaappstore.module.ability.data.SourceDataBody;
import com.byd.overseaappstore.module.ability.data.generator.DataGenerator;
import com.byd.overseaappstore.module.ability.repo.SoftwareRepository;
import com.byd.overseaappstore.module.ability.util.SettingUtil;
import com.byd.overseaappstore.module.ability.util.SettingUtil$getLocalProtocolVersionCode$1;
import com.byd.overseaappstore.module.ability.util.SettingUtil$getProtocolAgreeState$1;
import com.byd.overseaappstore.module.ability.util.download.data.SoftwareOptParam;
import com.byd.overseaappstore.module.ability.util.notify.alone.SoftwareNeedStartInstallNotify;
import com.byd.overseaappstore.module.ability.util.notify.alone.line.ExecutorHciDispatchMgr;
import com.byd.overseaappstore.module.ability.util.notify.alone.sequence.ExecutorSequenceDispatcherMgr;
import com.byd.overseaappstore.module.ability.util.notify.router.ExecutorDialogRouterMgr;
import com.byd.overseaappstore.module.ability.util.notify.router.gear.ExecutorNotPGearState;
import com.byd.overseaappstore.module.ability.util.notify.router.iuu.IUUState;
import com.byd.overseaappstore.module.ability.util.notify.router.net.NetContextState;
import com.byd.overseaappstore.module.ability.util.notify.router.storage.StorageState;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Objects;
import kotlin.Metadata;
import kotlin.Triple;
import kotlin.Unit;
import kotlin.collections.CollectionsKt;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.ContinuationImpl;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.BuildersKt__BuildersKt;
import kotlinx.coroutines.BuildersKt__Builders_commonKt;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.flow.FlowCollector;
import kotlinx.coroutines.flow.MutableSharedFlow;
import kotlinx.coroutines.flow.MutableStateFlow;
import kotlinx.coroutines.flow.SharedFlowKt;
import kotlinx.coroutines.flow.StateFlowKt;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import spi.aa;
import spi.ct;
import spi.ft;
import spi.i9;
import spi.l9;
import spi.oa;
import spi.qq;
import spi.s9;
import spi.z3;

/* JADX INFO: loaded from: classes.dex */
@Metadata(bv = {}, d1 = {"\u0000b\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\b\u0007\u0018\u00002\u00020\u00012\u00020\u0002By\b\u0007\u0012\u0006\u0010\u0004\u001a\u00020\u0003\u0012\u0006\u0010\u0006\u001a\u00020\u0005\u0012\u0006\u0010\b\u001a\u00020\u0007\u0012\u0006\u0010\n\u001a\u00020\t\u0012\u0006\u0010\f\u001a\u00020\u000b\u0012\u0006\u0010\u000e\u001a\u00020\r\u0012\u0006\u0010\u0010\u001a\u00020\u000f\u0012\u0006\u0010\u0012\u001a\u00020\u0011\u0012\u0006\u0010\u0014\u001a\u00020\u0013\u0012\u0006\u0010\u0016\u001a\u00020\u0015\u0012\u0006\u0010\u0018\u001a\u00020\u0017\u0012\u0006\u0010\u001a\u001a\u00020\u0019\u0012\u0006\u0010\u001c\u001a\u00020\u001b\u0012\u0006\u0010\u001e\u001a\u00020\u001d¢\u0006\u0004\b\u001f\u0010 ¨\u0006!"}, d2 = {"Lcom/byd/overseaappstore/ui/vm/MainViewModel;", "Landroidx/lifecycle/ViewModel;", "", "Lspi/s9;", "executorHciMgr", "Lcom/byd/overseaappstore/module/ability/util/notify/router/ExecutorDialogRouterMgr;", "executorDialogRouterMgr", "Lcom/byd/overseaappstore/module/ability/repo/SoftwareRepository;", "softwareRepository", "Lspi/z3;", "configRepository", "Lspi/aa;", "executorIuuMgr", "Lspi/oa;", "executorSoftwareStateMgr", "Lcom/byd/overseaappstore/module/ability/data/generator/DataGenerator;", "dataGenerator", "Lcom/byd/overseaappstore/module/ability/util/notify/alone/line/ExecutorHciDispatchMgr;", "executorHciDispatchMgr", "Lspi/i9;", "executorDbMgr", "Lspi/l9;", "executorDownloadTaskMgr", "Lcom/byd/overseaappstore/module/ability/util/notify/router/gear/ExecutorNotPGearState;", "executorNotPGearState", "Lcom/byd/overseaappstore/module/ability/util/notify/alone/sequence/ExecutorSequenceDispatcherMgr;", "executorSequenceDispatcherMgr", "Lspi/ft;", "timerWorkMgr", "Lcom/byd/overseaappstore/module/ability/util/notify/alone/SoftwareNeedStartInstallNotify;", "softwareNeedStartInstallNotify", "<init>", "(Lspi/s9;Lcom/byd/overseaappstore/module/ability/util/notify/router/ExecutorDialogRouterMgr;Lcom/byd/overseaappstore/module/ability/repo/SoftwareRepository;Lspi/z3;Lspi/aa;Lspi/oa;Lcom/byd/overseaappstore/module/ability/data/generator/DataGenerator;Lcom/byd/overseaappstore/module/ability/util/notify/alone/line/ExecutorHciDispatchMgr;Lspi/i9;Lspi/l9;Lcom/byd/overseaappstore/module/ability/util/notify/router/gear/ExecutorNotPGearState;Lcom/byd/overseaappstore/module/ability/util/notify/alone/sequence/ExecutorSequenceDispatcherMgr;Lspi/ft;Lcom/byd/overseaappstore/module/ability/util/notify/alone/SoftwareNeedStartInstallNotify;)V", "app_flavor_8155f_proRelease"}, k = 1, mv = {1, 6, 0})
public final class MainViewModel extends ViewModel {

    @NotNull
    public final s9 a;

    @NotNull
    public final ExecutorDialogRouterMgr b;

    @NotNull
    public final SoftwareRepository c;

    @NotNull
    public final z3 d;

    @NotNull
    public final aa e;

    @NotNull
    public final oa f;

    @NotNull
    public final DataGenerator g;

    @NotNull
    public final ExecutorHciDispatchMgr h;

    @NotNull
    public final i9 i;

    @NotNull
    public final l9 j;

    @NotNull
    public final ExecutorNotPGearState k;

    @NotNull
    public final ExecutorSequenceDispatcherMgr l;

    @NotNull
    public final SoftwareNeedStartInstallNotify m;

    @NotNull
    public final MutableStateFlow<NetContextState> n;

    @NotNull
    public final MutableStateFlow<StorageState> o;

    @NotNull
    public final MutableSharedFlow<IUUState> p;

    @DebugMetadata(c = "com.byd.overseaappstore.ui.vm.MainViewModel$onlyWifiOrCellularDialogContinueClick$1", f = "MainViewModel.kt", i = {}, l = {196, 198, 220, 270, 275}, m = "invokeSuspend", n = {}, s = {})
    public static final class a extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
        public int a;
        public final /* synthetic */ Triple<NetContextState, List<SoftwareOptParam>, List<qq>> b;
        public final /* synthetic */ String c;
        public final /* synthetic */ MainViewModel d;
        public final /* synthetic */ Context e;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        /* JADX WARN: Multi-variable type inference failed */
        public a(Triple<NetContextState, ? extends List<SoftwareOptParam>, ? extends List<qq>> triple, String str, MainViewModel mainViewModel, Context context, Continuation<? super a> continuation) {
            super(2, continuation);
            this.b = triple;
            this.c = str;
            this.d = mainViewModel;
            this.e = context;
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        @NotNull
        public final Continuation<Unit> create(@Nullable Object obj, @NotNull Continuation<?> continuation) {
            return new a(this.b, this.c, this.d, this.e, continuation);
        }

        @Override // kotlin.jvm.functions.Function2
        /* JADX INFO: renamed from: invoke */
        public final Object mo6invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
            return ((a) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
        }

        /* JADX WARN: Removed duplicated region for block: B:31:0x00ba  */
        /* JADX WARN: Removed duplicated region for block: B:32:0x00c3  */
        /* JADX WARN: Removed duplicated region for block: B:39:0x00e3  */
        /* JADX WARN: Removed duplicated region for block: B:42:0x00ea  */
        /* JADX WARN: Removed duplicated region for block: B:60:0x01cd  */
        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        @org.jetbrains.annotations.Nullable
        /*
            Code decompiled incorrectly, please refer to instructions dump.
            To view partially-correct code enable 'Show inconsistent code' option in preferences
        */
        public final java.lang.Object invokeSuspend(@org.jetbrains.annotations.NotNull java.lang.Object r17) {
            /*
                Method dump skipped, instruction units count: 758
                To view this dump change 'Code comments level' option to 'DEBUG'
            */
            throw new UnsupportedOperationException("Method not decompiled: com.byd.overseaappstore.ui.vm.MainViewModel.a.invokeSuspend(java.lang.Object):java.lang.Object");
        }
    }

    @DebugMetadata(c = "com.byd.overseaappstore.ui.vm.MainViewModel", f = "MainViewModel.kt", i = {0}, l = {154, 154}, m = "pauseAllDownloadingTasks", n = {"this"}, s = {"L$0"})
    public static final class b extends ContinuationImpl {
        public MainViewModel a;
        public /* synthetic */ Object b;
        public int d;

        public b(Continuation<? super b> continuation) {
            super(continuation);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        @Nullable
        public final Object invokeSuspend(@NotNull Object obj) {
            this.b = obj;
            this.d |= Integer.MIN_VALUE;
            return MainViewModel.this.d(this);
        }
    }

    public static final class c<T> implements FlowCollector {
        public c() {
        }

        @Override // kotlinx.coroutines.flow.FlowCollector
        public final Object emit(Object obj, Continuation continuation) {
            ArrayList arrayList;
            List list = (List) ((SourceDataBody) obj).getApiData();
            if (list != null) {
                MainViewModel mainViewModel = MainViewModel.this;
                arrayList = new ArrayList(CollectionsKt.b(list));
                Iterator<T> it = list.iterator();
                while (it.hasNext()) {
                    arrayList.add(mainViewModel.g.parseToSoftwareOptParam((qq) it.next()));
                }
            } else {
                arrayList = null;
            }
            if (arrayList != null) {
                ct.a(spi.e.c("operate work pauseAllDownloadingTasks optParamList.size:", arrayList.size()), new Object[0]);
                Object objB = s9.b(MainViewModel.this.a, arrayList, continuation);
                if (objB == IntrinsicsKt.getCOROUTINE_SUSPENDED()) {
                    return objB;
                }
            } else {
                ct.a("operate work pauseAllDownloadingTasks optParamList is null", new Object[0]);
            }
            return Unit.INSTANCE;
        }
    }

    @DebugMetadata(c = "com.byd.overseaappstore.ui.vm.MainViewModel", f = "MainViewModel.kt", i = {0}, l = {168, 168}, m = "pauseAllUpdatingTasks", n = {"this"}, s = {"L$0"})
    public static final class d extends ContinuationImpl {
        public MainViewModel a;
        public /* synthetic */ Object b;
        public int d;

        public d(Continuation<? super d> continuation) {
            super(continuation);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        @Nullable
        public final Object invokeSuspend(@NotNull Object obj) {
            this.b = obj;
            this.d |= Integer.MIN_VALUE;
            return MainViewModel.this.e(this);
        }
    }

    public static final class e<T> implements FlowCollector {
        public e() {
        }

        @Override // kotlinx.coroutines.flow.FlowCollector
        public final Object emit(Object obj, Continuation continuation) {
            ArrayList arrayList;
            List list = (List) ((SourceDataBody) obj).getApiData();
            if (list != null) {
                MainViewModel mainViewModel = MainViewModel.this;
                arrayList = new ArrayList(CollectionsKt.b(list));
                Iterator<T> it = list.iterator();
                while (it.hasNext()) {
                    arrayList.add(mainViewModel.g.parseToSoftwareOptParam((qq) it.next()));
                }
            } else {
                arrayList = null;
            }
            if (arrayList != null) {
                ct.a(spi.e.c("operate work pauseAllUpdatingTasks optParamList.size:", arrayList.size()), new Object[0]);
                Object objB = s9.b(MainViewModel.this.a, arrayList, continuation);
                if (objB == IntrinsicsKt.getCOROUTINE_SUSPENDED()) {
                    return objB;
                }
            } else {
                ct.a("operate work pauseAllUpdatingTasks optParamList is null", new Object[0]);
            }
            return Unit.INSTANCE;
        }
    }

    public MainViewModel(@NotNull s9 executorHciMgr, @NotNull ExecutorDialogRouterMgr executorDialogRouterMgr, @NotNull SoftwareRepository softwareRepository, @NotNull z3 configRepository, @NotNull aa executorIuuMgr, @NotNull oa executorSoftwareStateMgr, @NotNull DataGenerator dataGenerator, @NotNull ExecutorHciDispatchMgr executorHciDispatchMgr, @NotNull i9 executorDbMgr, @NotNull l9 executorDownloadTaskMgr, @NotNull ExecutorNotPGearState executorNotPGearState, @NotNull ExecutorSequenceDispatcherMgr executorSequenceDispatcherMgr, @NotNull ft timerWorkMgr, @NotNull SoftwareNeedStartInstallNotify softwareNeedStartInstallNotify) {
        Intrinsics.checkNotNullParameter(executorHciMgr, "executorHciMgr");
        Intrinsics.checkNotNullParameter(executorDialogRouterMgr, "executorDialogRouterMgr");
        Intrinsics.checkNotNullParameter(softwareRepository, "softwareRepository");
        Intrinsics.checkNotNullParameter(configRepository, "configRepository");
        Intrinsics.checkNotNullParameter(executorIuuMgr, "executorIuuMgr");
        Intrinsics.checkNotNullParameter(executorSoftwareStateMgr, "executorSoftwareStateMgr");
        Intrinsics.checkNotNullParameter(dataGenerator, "dataGenerator");
        Intrinsics.checkNotNullParameter(executorHciDispatchMgr, "executorHciDispatchMgr");
        Intrinsics.checkNotNullParameter(executorDbMgr, "executorDbMgr");
        Intrinsics.checkNotNullParameter(executorDownloadTaskMgr, "executorDownloadTaskMgr");
        Intrinsics.checkNotNullParameter(executorNotPGearState, "executorNotPGearState");
        Intrinsics.checkNotNullParameter(executorSequenceDispatcherMgr, "executorSequenceDispatcherMgr");
        Intrinsics.checkNotNullParameter(timerWorkMgr, "timerWorkMgr");
        Intrinsics.checkNotNullParameter(softwareNeedStartInstallNotify, "softwareNeedStartInstallNotify");
        this.a = executorHciMgr;
        this.b = executorDialogRouterMgr;
        this.c = softwareRepository;
        this.d = configRepository;
        this.e = executorIuuMgr;
        this.f = executorSoftwareStateMgr;
        this.g = dataGenerator;
        this.h = executorHciDispatchMgr;
        this.i = executorDbMgr;
        this.j = executorDownloadTaskMgr;
        this.k = executorNotPGearState;
        this.l = executorSequenceDispatcherMgr;
        this.m = softwareNeedStartInstallNotify;
        this.n = StateFlowKt.MutableStateFlow(executorDialogRouterMgr.getExecutorNetContextState().initNetContextState());
        this.o = StateFlowKt.MutableStateFlow(executorDialogRouterMgr.getExecutorStorageState().initSpaceState());
        this.p = SharedFlowKt.MutableSharedFlow$default(0, 0, null, 7, null);
    }

    @NotNull
    public final String a(boolean z, boolean z2) {
        return this.g.parseToUserActInState(z, z2);
    }

    /* JADX WARN: Removed duplicated region for block: B:37:0x00ce  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final void b(@org.jetbrains.annotations.NotNull android.content.Context r8) {
        /*
            Method dump skipped, instruction units count: 310
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.byd.overseaappstore.ui.vm.MainViewModel.b(android.content.Context):void");
    }

    public final void c(@Nullable Triple<NetContextState, ? extends List<SoftwareOptParam>, ? extends List<qq>> triple, @NotNull Context context, @NotNull String dialogType) {
        Intrinsics.checkNotNullParameter(context, "context");
        Intrinsics.checkNotNullParameter(dialogType, "dialogType");
        BuildersKt__Builders_commonKt.launch$default(ViewModelKt.getViewModelScope(this), null, null, new a(triple, dialogType, this, context, null), 3, null);
    }

    /* JADX WARN: Removed duplicated region for block: B:7:0x0013  */
    @org.jetbrains.annotations.Nullable
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final java.lang.Object d(@org.jetbrains.annotations.NotNull kotlin.coroutines.Continuation<? super kotlin.Unit> r6) {
        /*
            r5 = this;
            boolean r0 = r6 instanceof com.byd.overseaappstore.ui.vm.MainViewModel.b
            if (r0 == 0) goto L13
            r0 = r6
            com.byd.overseaappstore.ui.vm.MainViewModel$b r0 = (com.byd.overseaappstore.ui.vm.MainViewModel.b) r0
            int r1 = r0.d
            r2 = -2147483648(0xffffffff80000000, float:-0.0)
            r3 = r1 & r2
            if (r3 == 0) goto L13
            int r1 = r1 - r2
            r0.d = r1
            goto L18
        L13:
            com.byd.overseaappstore.ui.vm.MainViewModel$b r0 = new com.byd.overseaappstore.ui.vm.MainViewModel$b
            r0.<init>(r6)
        L18:
            java.lang.Object r6 = r0.b
            java.lang.Object r1 = kotlin.coroutines.intrinsics.IntrinsicsKt.getCOROUTINE_SUSPENDED()
            int r2 = r0.d
            r3 = 2
            r4 = 1
            if (r2 == 0) goto L3a
            if (r2 == r4) goto L34
            if (r2 != r3) goto L2c
            kotlin.ResultKt.throwOnFailure(r6)
            goto L5e
        L2c:
            java.lang.IllegalStateException r6 = new java.lang.IllegalStateException
            java.lang.String r0 = "call to 'resume' before 'invoke' with coroutine"
            r6.<init>(r0)
            throw r6
        L34:
            com.byd.overseaappstore.ui.vm.MainViewModel r2 = r0.a
            kotlin.ResultKt.throwOnFailure(r6)
            goto L4b
        L3a:
            kotlin.ResultKt.throwOnFailure(r6)
            com.byd.overseaappstore.module.ability.repo.SoftwareRepository r6 = r5.c
            r0.a = r5
            r0.d = r4
            java.lang.Object r6 = r6.c()
            if (r6 != r1) goto L4a
            return r1
        L4a:
            r2 = r5
        L4b:
            kotlinx.coroutines.flow.Flow r6 = (kotlinx.coroutines.flow.Flow) r6
            com.byd.overseaappstore.ui.vm.MainViewModel$c r4 = new com.byd.overseaappstore.ui.vm.MainViewModel$c
            r4.<init>()
            r2 = 0
            r0.a = r2
            r0.d = r3
            java.lang.Object r6 = r6.collect(r4, r0)
            if (r6 != r1) goto L5e
            return r1
        L5e:
            kotlin.Unit r6 = kotlin.Unit.INSTANCE
            return r6
        */
        throw new UnsupportedOperationException("Method not decompiled: com.byd.overseaappstore.ui.vm.MainViewModel.d(kotlin.coroutines.Continuation):java.lang.Object");
    }

    /* JADX WARN: Removed duplicated region for block: B:7:0x0013  */
    @org.jetbrains.annotations.Nullable
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final java.lang.Object e(@org.jetbrains.annotations.NotNull kotlin.coroutines.Continuation<? super kotlin.Unit> r6) {
        /*
            r5 = this;
            boolean r0 = r6 instanceof com.byd.overseaappstore.ui.vm.MainViewModel.d
            if (r0 == 0) goto L13
            r0 = r6
            com.byd.overseaappstore.ui.vm.MainViewModel$d r0 = (com.byd.overseaappstore.ui.vm.MainViewModel.d) r0
            int r1 = r0.d
            r2 = -2147483648(0xffffffff80000000, float:-0.0)
            r3 = r1 & r2
            if (r3 == 0) goto L13
            int r1 = r1 - r2
            r0.d = r1
            goto L18
        L13:
            com.byd.overseaappstore.ui.vm.MainViewModel$d r0 = new com.byd.overseaappstore.ui.vm.MainViewModel$d
            r0.<init>(r6)
        L18:
            java.lang.Object r6 = r0.b
            java.lang.Object r1 = kotlin.coroutines.intrinsics.IntrinsicsKt.getCOROUTINE_SUSPENDED()
            int r2 = r0.d
            r3 = 2
            r4 = 1
            if (r2 == 0) goto L3a
            if (r2 == r4) goto L34
            if (r2 != r3) goto L2c
            kotlin.ResultKt.throwOnFailure(r6)
            goto L5e
        L2c:
            java.lang.IllegalStateException r6 = new java.lang.IllegalStateException
            java.lang.String r0 = "call to 'resume' before 'invoke' with coroutine"
            r6.<init>(r0)
            throw r6
        L34:
            com.byd.overseaappstore.ui.vm.MainViewModel r2 = r0.a
            kotlin.ResultKt.throwOnFailure(r6)
            goto L4b
        L3a:
            kotlin.ResultKt.throwOnFailure(r6)
            com.byd.overseaappstore.module.ability.repo.SoftwareRepository r6 = r5.c
            r0.a = r5
            r0.d = r4
            java.lang.Object r6 = r6.h()
            if (r6 != r1) goto L4a
            return r1
        L4a:
            r2 = r5
        L4b:
            kotlinx.coroutines.flow.Flow r6 = (kotlinx.coroutines.flow.Flow) r6
            com.byd.overseaappstore.ui.vm.MainViewModel$e r4 = new com.byd.overseaappstore.ui.vm.MainViewModel$e
            r4.<init>()
            r2 = 0
            r0.a = r2
            r0.d = r3
            java.lang.Object r6 = r6.collect(r4, r0)
            if (r6 != r1) goto L5e
            return r1
        L5e:
            kotlin.Unit r6 = kotlin.Unit.INSTANCE
            return r6
        */
        throw new UnsupportedOperationException("Method not decompiled: com.byd.overseaappstore.ui.vm.MainViewModel.e(kotlin.coroutines.Continuation):java.lang.Object");
    }

    public final boolean f(@NotNull Context context) {
        Intrinsics.checkNotNullParameter(context, "context");
        SettingUtil settingUtil = SettingUtil.a;
        Objects.requireNonNull(settingUtil);
        Intrinsics.checkNotNullParameter(context, "context");
        int iIntValue = ((Number) BuildersKt__BuildersKt.runBlocking$default(null, new SettingUtil$getLocalProtocolVersionCode$1(context, null), 1, null)).intValue();
        Objects.requireNonNull(settingUtil);
        Objects.requireNonNull(settingUtil);
        Intrinsics.checkNotNullParameter(context, "context");
        boolean zBooleanValue = ((Boolean) BuildersKt__BuildersKt.runBlocking$default(null, new SettingUtil$getProtocolAgreeState$1(context, null), 1, null)).booleanValue();
        if (iIntValue >= 3) {
            StringBuilder sbL = spi.e.l("protocolMixCheck protocolNoChange localCode:", iIntValue, " configCode:", 3, " curAgree:");
            sbL.append(zBooleanValue);
            ct.a(sbL.toString(), new Object[0]);
            return zBooleanValue;
        }
        boolean zB = settingUtil.b(context, false);
        StringBuilder sbL2 = spi.e.l("protocolMixCheck protocolHasChanged curProtocolAgreeChangeToFalse localCode:", iIntValue, " configCode:", 3, " curAgree:");
        sbL2.append(zB);
        ct.a(sbL2.toString(), new Object[0]);
        return zB;
    }
}
