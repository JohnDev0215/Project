package com.byd.overseaappstore.ui.vm;

import android.content.Context;
import androidx.lifecycle.ViewModel;
import com.byd.overseaappstore.module.ability.data.SourceDataBody;
import com.byd.overseaappstore.module.ability.data.generator.DataGenerator;
import com.byd.overseaappstore.module.ability.repo.SoftwareRepository;
import com.byd.overseaappstore.module.ability.util.notify.router.ExecutorDialogRouterMgr;
import com.byd.overseaappstore.module.ability.util.notify.router.net.NetContextState;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.collections.CollectionsKt;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.jvm.internal.Boxing;
import kotlin.coroutines.jvm.internal.ContinuationImpl;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.flow.FlowCollector;
import kotlinx.coroutines.flow.MutableStateFlow;
import kotlinx.coroutines.flow.StateFlow;
import kotlinx.coroutines.flow.StateFlowKt;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import spi.ct;
import spi.oa;
import spi.pq;
import spi.qq;
import spi.s9;
import spi.z3;
import spi.zb;

/* JADX INFO: loaded from: classes.dex */
@Metadata(bv = {}, d1 = {"\u00002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\b\u0007\u0018\u00002\u00020\u00012\u00020\u0002B9\b\u0007\u0012\u0006\u0010\u0004\u001a\u00020\u0003\u0012\u0006\u0010\u0006\u001a\u00020\u0005\u0012\u0006\u0010\b\u001a\u00020\u0007\u0012\u0006\u0010\n\u001a\u00020\t\u0012\u0006\u0010\f\u001a\u00020\u000b\u0012\u0006\u0010\u000e\u001a\u00020\r¢\u0006\u0004\b\u000f\u0010\u0010¨\u0006\u0011"}, d2 = {"Lcom/byd/overseaappstore/ui/vm/SettingViewModel;", "Landroidx/lifecycle/ViewModel;", "", "Lspi/z3;", "configRepository", "Lcom/byd/overseaappstore/module/ability/repo/SoftwareRepository;", "softwareRepository", "Lspi/s9;", "executorHciMgr", "Lcom/byd/overseaappstore/module/ability/util/notify/router/ExecutorDialogRouterMgr;", "executorDialogRouterMgr", "Lspi/oa;", "executorSoftwareStateMgr", "Lcom/byd/overseaappstore/module/ability/data/generator/DataGenerator;", "dataGenerator", "<init>", "(Lspi/z3;Lcom/byd/overseaappstore/module/ability/repo/SoftwareRepository;Lspi/s9;Lcom/byd/overseaappstore/module/ability/util/notify/router/ExecutorDialogRouterMgr;Lspi/oa;Lcom/byd/overseaappstore/module/ability/data/generator/DataGenerator;)V", "app_flavor_8155f_proRelease"}, k = 1, mv = {1, 6, 0})
public final class SettingViewModel extends ViewModel {

    @NotNull
    public final z3 a;

    @NotNull
    public final SoftwareRepository b;

    @NotNull
    public final s9 c;

    @NotNull
    public final oa d;

    @NotNull
    public final DataGenerator e;

    @NotNull
    public final StateFlow<NetContextState> f;

    @NotNull
    public final MutableStateFlow<Boolean> g;

    @NotNull
    public final StateFlow<Boolean> h;

    @NotNull
    public final MutableStateFlow<Long> i;

    @NotNull
    public final StateFlow<Long> j;

    @DebugMetadata(c = "com.byd.overseaappstore.ui.vm.SettingViewModel", f = "SettingViewModel.kt", i = {0}, l = {122, 122}, m = "clearUnInstalledSoftware", n = {"this"}, s = {"L$0"})
    public static final class a extends ContinuationImpl {
        public SettingViewModel a;
        public /* synthetic */ Object b;
        public int d;

        public a(Continuation<? super a> continuation) {
            super(continuation);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        @Nullable
        public final Object invokeSuspend(@NotNull Object obj) {
            this.b = obj;
            this.d |= Integer.MIN_VALUE;
            return SettingViewModel.this.a(this);
        }
    }

    public static final class b<T> implements FlowCollector {

        @DebugMetadata(c = "com.byd.overseaappstore.ui.vm.SettingViewModel$clearUnInstalledSoftware$2", f = "SettingViewModel.kt", i = {0}, l = {131, 132}, m = "emit", n = {"softwareLocalWrap"}, s = {"L$2"})
        public static final class a extends ContinuationImpl {
            public SettingViewModel a;
            public Iterator b;
            public qq c;
            public /* synthetic */ Object d;
            public final /* synthetic */ b<T> e;
            public int f;

            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            /* JADX WARN: Multi-variable type inference failed */
            public a(b<? super T> bVar, Continuation<? super a> continuation) {
                super(continuation);
                this.e = bVar;
            }

            @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
            @Nullable
            public final Object invokeSuspend(@NotNull Object obj) {
                this.d = obj;
                this.f |= Integer.MIN_VALUE;
                return this.e.emit(null, this);
            }
        }

        public b() {
        }

        /* JADX WARN: Removed duplicated region for block: B:39:0x010e  */
        /* JADX WARN: Removed duplicated region for block: B:43:0x0117  */
        /* JADX WARN: Removed duplicated region for block: B:47:0x0120  */
        /* JADX WARN: Removed duplicated region for block: B:49:0x0124 A[RETURN] */
        /* JADX WARN: Removed duplicated region for block: B:50:0x0125  */
        /* JADX WARN: Removed duplicated region for block: B:7:0x0017  */
        /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:50:0x0125 -> B:30:0x008e). Please report as a decompilation issue!!! */
        @Override // kotlinx.coroutines.flow.FlowCollector
        @org.jetbrains.annotations.Nullable
        /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
        /*
            Code decompiled incorrectly, please refer to instructions dump.
            To view partially-correct code enable 'Show inconsistent code' option in preferences
        */
        public final java.lang.Object emit(@org.jetbrains.annotations.NotNull com.byd.overseaappstore.module.ability.data.SourceDataBody<java.util.List<spi.qq>> r18, @org.jetbrains.annotations.NotNull kotlin.coroutines.Continuation<? super kotlin.Unit> r19) {
            /*
                Method dump skipped, instruction units count: 300
                To view this dump change 'Code comments level' option to 'DEBUG'
            */
            throw new UnsupportedOperationException("Method not decompiled: com.byd.overseaappstore.ui.vm.SettingViewModel.b.emit(com.byd.overseaappstore.module.ability.data.SourceDataBody, kotlin.coroutines.Continuation):java.lang.Object");
        }
    }

    @DebugMetadata(c = "com.byd.overseaappstore.ui.vm.SettingViewModel", f = "SettingViewModel.kt", i = {0}, l = {142, 142}, m = "getUnInstalledSoftwareSize", n = {"this"}, s = {"L$0"})
    public static final class c extends ContinuationImpl {
        public SettingViewModel a;
        public /* synthetic */ Object b;
        public int d;

        public c(Continuation<? super c> continuation) {
            super(continuation);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        @Nullable
        public final Object invokeSuspend(@NotNull Object obj) {
            this.b = obj;
            this.d |= Integer.MIN_VALUE;
            return SettingViewModel.this.b(this);
        }
    }

    public static final class d<T> implements FlowCollector {
        public d() {
        }

        @Override // kotlinx.coroutines.flow.FlowCollector
        public final Object emit(Object obj, Continuation continuation) {
            List list;
            SourceDataBody sourceDataBody = (SourceDataBody) obj;
            long jG = 0;
            if ((sourceDataBody.getRet() == 0) && (list = (List) sourceDataBody.getApiData()) != null) {
                ArrayList arrayList = new ArrayList();
                for (T t : list) {
                    pq pqVar = ((qq) t).a;
                    if (pqVar.e == zb.a.g(pqVar.d)) {
                        arrayList.add(t);
                    }
                }
                ArrayList arrayList2 = new ArrayList(CollectionsKt.b(arrayList));
                Iterator it = arrayList.iterator();
                while (it.hasNext()) {
                    jG += zb.a.g(((qq) it.next()).a.d);
                    arrayList2.add(Unit.INSTANCE);
                }
            }
            ct.a(spi.d.d("getUnInstalledSoftwareSize size:", jG), new Object[0]);
            SettingViewModel.this.i.setValue(Boxing.boxLong(jG));
            return Unit.INSTANCE;
        }
    }

    @DebugMetadata(c = "com.byd.overseaappstore.ui.vm.SettingViewModel", f = "SettingViewModel.kt", i = {0}, l = {167}, m = "getWifiDownloadOnlyState", n = {"this"}, s = {"L$0"})
    public static final class e extends ContinuationImpl {
        public SettingViewModel a;
        public /* synthetic */ Object b;
        public int d;

        public e(Continuation<? super e> continuation) {
            super(continuation);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        @Nullable
        public final Object invokeSuspend(@NotNull Object obj) {
            this.b = obj;
            this.d |= Integer.MIN_VALUE;
            return SettingViewModel.this.c(this);
        }
    }

    @DebugMetadata(c = "com.byd.overseaappstore.ui.vm.SettingViewModel", f = "SettingViewModel.kt", i = {0, 0, 1, 1, 2, 2, 3, 3, 4, 4, 5, 5}, l = {55, 59, 62, 64, 69, 71}, m = "toggleUseByWifiState", n = {"this", "context", "this", "state", "this", "state", "this", "state", "this", "state", "this", "state"}, s = {"L$0", "L$1", "L$0", "Z$0", "L$0", "Z$0", "L$0", "Z$0", "L$0", "Z$0", "L$0", "Z$0"})
    public static final class f extends ContinuationImpl {
        public SettingViewModel a;
        public Context b;
        public boolean c;
        public /* synthetic */ Object d;
        public int f;

        public f(Continuation<? super f> continuation) {
            super(continuation);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        @Nullable
        public final Object invokeSuspend(@NotNull Object obj) {
            this.d = obj;
            this.f |= Integer.MIN_VALUE;
            return SettingViewModel.this.d(null, this);
        }
    }

    public SettingViewModel(@NotNull z3 configRepository, @NotNull SoftwareRepository softwareRepository, @NotNull s9 executorHciMgr, @NotNull ExecutorDialogRouterMgr executorDialogRouterMgr, @NotNull oa executorSoftwareStateMgr, @NotNull DataGenerator dataGenerator) {
        Intrinsics.checkNotNullParameter(configRepository, "configRepository");
        Intrinsics.checkNotNullParameter(softwareRepository, "softwareRepository");
        Intrinsics.checkNotNullParameter(executorHciMgr, "executorHciMgr");
        Intrinsics.checkNotNullParameter(executorDialogRouterMgr, "executorDialogRouterMgr");
        Intrinsics.checkNotNullParameter(executorSoftwareStateMgr, "executorSoftwareStateMgr");
        Intrinsics.checkNotNullParameter(dataGenerator, "dataGenerator");
        this.a = configRepository;
        this.b = softwareRepository;
        this.c = executorHciMgr;
        this.d = executorSoftwareStateMgr;
        this.e = dataGenerator;
        StateFlow<NetContextState> wifiDownloadEnableStateDataFlow = executorDialogRouterMgr.getExecutorNetContextState().getWifiDownloadEnableStateDataFlow();
        this.f = wifiDownloadEnableStateDataFlow;
        MutableStateFlow<Boolean> MutableStateFlow = StateFlowKt.MutableStateFlow(Boolean.valueOf(wifiDownloadEnableStateDataFlow.getValue().getWorkWifiOnly()));
        this.g = MutableStateFlow;
        this.h = MutableStateFlow;
        MutableStateFlow<Long> MutableStateFlow2 = StateFlowKt.MutableStateFlow(0L);
        this.i = MutableStateFlow2;
        this.j = MutableStateFlow2;
    }

    /* JADX WARN: Removed duplicated region for block: B:7:0x0013  */
    @org.jetbrains.annotations.Nullable
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final java.lang.Object a(@org.jetbrains.annotations.NotNull kotlin.coroutines.Continuation<? super kotlin.Unit> r6) {
        /*
            r5 = this;
            boolean r0 = r6 instanceof com.byd.overseaappstore.ui.vm.SettingViewModel.a
            if (r0 == 0) goto L13
            r0 = r6
            com.byd.overseaappstore.ui.vm.SettingViewModel$a r0 = (com.byd.overseaappstore.ui.vm.SettingViewModel.a) r0
            int r1 = r0.d
            r2 = -2147483648(0xffffffff80000000, float:-0.0)
            r3 = r1 & r2
            if (r3 == 0) goto L13
            int r1 = r1 - r2
            r0.d = r1
            goto L18
        L13:
            com.byd.overseaappstore.ui.vm.SettingViewModel$a r0 = new com.byd.overseaappstore.ui.vm.SettingViewModel$a
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
            com.byd.overseaappstore.ui.vm.SettingViewModel r2 = r0.a
            kotlin.ResultKt.throwOnFailure(r6)
            goto L4b
        L3a:
            kotlin.ResultKt.throwOnFailure(r6)
            com.byd.overseaappstore.module.ability.repo.SoftwareRepository r6 = r5.b
            r0.a = r5
            r0.d = r4
            java.lang.Object r6 = r6.b()
            if (r6 != r1) goto L4a
            return r1
        L4a:
            r2 = r5
        L4b:
            kotlinx.coroutines.flow.Flow r6 = (kotlinx.coroutines.flow.Flow) r6
            com.byd.overseaappstore.ui.vm.SettingViewModel$b r4 = new com.byd.overseaappstore.ui.vm.SettingViewModel$b
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
        throw new UnsupportedOperationException("Method not decompiled: com.byd.overseaappstore.ui.vm.SettingViewModel.a(kotlin.coroutines.Continuation):java.lang.Object");
    }

    /* JADX WARN: Removed duplicated region for block: B:7:0x0013  */
    @org.jetbrains.annotations.Nullable
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final java.lang.Object b(@org.jetbrains.annotations.NotNull kotlin.coroutines.Continuation<? super kotlin.Unit> r6) {
        /*
            r5 = this;
            boolean r0 = r6 instanceof com.byd.overseaappstore.ui.vm.SettingViewModel.c
            if (r0 == 0) goto L13
            r0 = r6
            com.byd.overseaappstore.ui.vm.SettingViewModel$c r0 = (com.byd.overseaappstore.ui.vm.SettingViewModel.c) r0
            int r1 = r0.d
            r2 = -2147483648(0xffffffff80000000, float:-0.0)
            r3 = r1 & r2
            if (r3 == 0) goto L13
            int r1 = r1 - r2
            r0.d = r1
            goto L18
        L13:
            com.byd.overseaappstore.ui.vm.SettingViewModel$c r0 = new com.byd.overseaappstore.ui.vm.SettingViewModel$c
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
            com.byd.overseaappstore.ui.vm.SettingViewModel r2 = r0.a
            kotlin.ResultKt.throwOnFailure(r6)
            goto L4b
        L3a:
            kotlin.ResultKt.throwOnFailure(r6)
            com.byd.overseaappstore.module.ability.repo.SoftwareRepository r6 = r5.b
            r0.a = r5
            r0.d = r4
            java.lang.Object r6 = r6.b()
            if (r6 != r1) goto L4a
            return r1
        L4a:
            r2 = r5
        L4b:
            kotlinx.coroutines.flow.Flow r6 = (kotlinx.coroutines.flow.Flow) r6
            com.byd.overseaappstore.ui.vm.SettingViewModel$d r4 = new com.byd.overseaappstore.ui.vm.SettingViewModel$d
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
        throw new UnsupportedOperationException("Method not decompiled: com.byd.overseaappstore.ui.vm.SettingViewModel.b(kotlin.coroutines.Continuation):java.lang.Object");
    }

    /* JADX WARN: Removed duplicated region for block: B:7:0x0013  */
    @org.jetbrains.annotations.Nullable
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final java.lang.Object c(@org.jetbrains.annotations.NotNull kotlin.coroutines.Continuation<? super kotlin.Unit> r5) {
        /*
            r4 = this;
            boolean r0 = r5 instanceof com.byd.overseaappstore.ui.vm.SettingViewModel.e
            if (r0 == 0) goto L13
            r0 = r5
            com.byd.overseaappstore.ui.vm.SettingViewModel$e r0 = (com.byd.overseaappstore.ui.vm.SettingViewModel.e) r0
            int r1 = r0.d
            r2 = -2147483648(0xffffffff80000000, float:-0.0)
            r3 = r1 & r2
            if (r3 == 0) goto L13
            int r1 = r1 - r2
            r0.d = r1
            goto L18
        L13:
            com.byd.overseaappstore.ui.vm.SettingViewModel$e r0 = new com.byd.overseaappstore.ui.vm.SettingViewModel$e
            r0.<init>(r5)
        L18:
            java.lang.Object r5 = r0.b
            java.lang.Object r1 = kotlin.coroutines.intrinsics.IntrinsicsKt.getCOROUTINE_SUSPENDED()
            int r2 = r0.d
            r3 = 1
            if (r2 == 0) goto L33
            if (r2 != r3) goto L2b
            com.byd.overseaappstore.ui.vm.SettingViewModel r0 = r0.a
            kotlin.ResultKt.throwOnFailure(r5)
            goto L44
        L2b:
            java.lang.IllegalStateException r5 = new java.lang.IllegalStateException
            java.lang.String r0 = "call to 'resume' before 'invoke' with coroutine"
            r5.<init>(r0)
            throw r5
        L33:
            kotlin.ResultKt.throwOnFailure(r5)
            spi.z3 r5 = r4.a
            r0.a = r4
            r0.d = r3
            java.lang.Object r5 = r5.e(r0)
            if (r5 != r1) goto L43
            return r1
        L43:
            r0 = r4
        L44:
            java.lang.Boolean r5 = (java.lang.Boolean) r5
            boolean r5 = r5.booleanValue()
            java.lang.String r1 = "getWifiDownloadOnlyState workWifiOnly"
            java.lang.String r1 = spi.hi.e(r1, r5)
            r2 = 0
            java.lang.Object[] r2 = new java.lang.Object[r2]
            spi.ct.a(r1, r2)
            kotlinx.coroutines.flow.MutableStateFlow<java.lang.Boolean> r0 = r0.g
            java.lang.Boolean r5 = kotlin.coroutines.jvm.internal.Boxing.boxBoolean(r5)
            r0.setValue(r5)
            kotlin.Unit r5 = kotlin.Unit.INSTANCE
            return r5
        */
        throw new UnsupportedOperationException("Method not decompiled: com.byd.overseaappstore.ui.vm.SettingViewModel.c(kotlin.coroutines.Continuation):java.lang.Object");
    }

    /* JADX WARN: Removed duplicated region for block: B:21:0x006f  */
    /* JADX WARN: Removed duplicated region for block: B:24:0x0081  */
    /* JADX WARN: Removed duplicated region for block: B:36:0x00b8  */
    /* JADX WARN: Removed duplicated region for block: B:39:0x00f1  */
    /* JADX WARN: Removed duplicated region for block: B:7:0x0017  */
    @org.jetbrains.annotations.Nullable
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final java.lang.Object d(@org.jetbrains.annotations.NotNull android.content.Context r23, @org.jetbrains.annotations.NotNull kotlin.coroutines.Continuation<? super kotlin.Unit> r24) {
        /*
            Method dump skipped, instruction units count: 326
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.byd.overseaappstore.ui.vm.SettingViewModel.d(android.content.Context, kotlin.coroutines.Continuation):java.lang.Object");
    }
}
