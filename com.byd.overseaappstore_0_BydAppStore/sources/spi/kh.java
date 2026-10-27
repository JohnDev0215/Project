package spi;

import com.byd.overseaappstore.module.ability.util.download.data.SoftwareOptParam;
import com.byd.overseaappstore.module.ability.util.notify.router.net.NetContextState;
import com.byd.overseaappstore.ui.MainActivity;
import java.util.List;
import kotlin.ResultKt;
import kotlin.Triple;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Lambda;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.flow.FlowKt;
import kotlinx.coroutines.flow.MutableStateFlow;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/* JADX INFO: loaded from: classes.dex */
@DebugMetadata(c = "com.byd.overseaappstore.ui.MainActivity$comprehensiveWork$2", f = "MainActivity.kt", i = {}, l = {525}, m = "invokeSuspend", n = {}, s = {})
public final class kh extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
    public int a;
    public final /* synthetic */ MainActivity b;

    @DebugMetadata(c = "com.byd.overseaappstore.ui.MainActivity$comprehensiveWork$2$1", f = "MainActivity.kt", i = {0, 0, 1, 1, 1, 1, 2, 2, 2, 3, 3, 3}, l = {533, 565, 628, 629}, m = "invokeSuspend", n = {"netContextState", "wifiConnected", "netContextState", "optParamList", "netErrorInterruptList", "workWifiOnly", "netContextState", "continueDataSource", "dialogType", "netContextState", "continueDataSource", "dialogType"}, s = {"L$0", "Z$0", "L$0", "L$1", "L$2", "Z$0", "L$0", "L$1", "L$2", "L$0", "L$1", "L$2"})
    public static final class a extends SuspendLambda implements Function2<NetContextState, Continuation<? super Unit>, Object> {
        public Object a;
        public Object b;
        public boolean c;
        public int d;
        public /* synthetic */ Object e;
        public final /* synthetic */ MainActivity f;

        /* JADX INFO: renamed from: spi.kh$a$a, reason: collision with other inner class name */
        public static final class C0130a extends Lambda implements Function1<Triple<? extends NetContextState, ? extends List<? extends SoftwareOptParam>, ? extends List<? extends qq>>, Unit> {
            public final /* synthetic */ MainActivity a;

            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            public C0130a(MainActivity mainActivity) {
                super(1);
                this.a = mainActivity;
            }

            @Override // kotlin.jvm.functions.Function1
            public final Unit invoke(Triple<? extends NetContextState, ? extends List<? extends SoftwareOptParam>, ? extends List<? extends qq>> triple) {
                ct.a("OnlyWifiOrCellularDialog Click CancelButton", new Object[0]);
                this.a.q().getExecutorNetContextState().notifyNetContextStateData(this.a.q().getExecutorNetContextState().initNetContextState());
                this.a.r().c();
                return Unit.INSTANCE;
            }
        }

        public static final class b extends Lambda implements Function1<Triple<? extends NetContextState, ? extends List<? extends SoftwareOptParam>, ? extends List<? extends qq>>, Unit> {
            public final /* synthetic */ MainActivity a;
            public final /* synthetic */ String b;

            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            public b(MainActivity mainActivity, String str) {
                super(1);
                this.a = mainActivity;
                this.b = str;
            }

            /* JADX WARN: Multi-variable type inference failed */
            @Override // kotlin.jvm.functions.Function1
            public final Unit invoke(Triple<? extends NetContextState, ? extends List<? extends SoftwareOptParam>, ? extends List<? extends qq>> triple) {
                ct.a("OnlyWifiOrCellularDialog Click ContinueButton", new Object[0]);
                this.a.q().getExecutorNetContextState().notifyNetContextStateData(this.a.q().getExecutorNetContextState().initNetContextState());
                this.a.s().c(triple, this.a, this.b);
                return Unit.INSTANCE;
            }
        }

        public static final class c extends Lambda implements Function1<Triple<? extends NetContextState, ? extends List<? extends SoftwareOptParam>, ? extends List<? extends qq>>, Unit> {
            public final /* synthetic */ MainActivity a;

            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            public c(MainActivity mainActivity) {
                super(1);
                this.a = mainActivity;
            }

            @Override // kotlin.jvm.functions.Function1
            public final Unit invoke(Triple<? extends NetContextState, ? extends List<? extends SoftwareOptParam>, ? extends List<? extends qq>> triple) {
                ct.a("OnlyWifiOrCellularDialog Click CancelButton", new Object[0]);
                this.a.q().getExecutorNetContextState().notifyNetContextStateData(this.a.q().getExecutorNetContextState().initNetContextState());
                this.a.r().c();
                return Unit.INSTANCE;
            }
        }

        public static final class d extends Lambda implements Function1<Triple<? extends NetContextState, ? extends List<? extends SoftwareOptParam>, ? extends List<? extends qq>>, Unit> {
            public final /* synthetic */ MainActivity a;
            public final /* synthetic */ String b;
            public final /* synthetic */ NetContextState c;
            public final /* synthetic */ boolean d;

            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            public d(MainActivity mainActivity, String str, NetContextState netContextState, boolean z) {
                super(1);
                this.a = mainActivity;
                this.b = str;
                this.c = netContextState;
                this.d = z;
            }

            /* JADX WARN: Multi-variable type inference failed */
            @Override // kotlin.jvm.functions.Function1
            public final Unit invoke(Triple<? extends NetContextState, ? extends List<? extends SoftwareOptParam>, ? extends List<? extends qq>> triple) {
                ct.a("OnlyWifiOrCellularDialog Click ContinueButton", new Object[0]);
                this.a.q().getExecutorNetContextState().notifyNetContextStateData(this.a.q().getExecutorNetContextState().initNetContextState());
                ct.a("networkCheck collectLatest dialogType:" + this.b + " setOnContinue:" + this.c.getWorkWifiOnly() + ",wifi:" + this.c.getWifiConnected() + ",cellular:" + this.c.getCellularConnected() + ",isAdded:" + this.d + ",PauseAll,WorkWifiOnly,NoWifiConnect,HasContainSoftware showDialog", new Object[0]);
                this.a.s().c(triple, this.a, this.b);
                return Unit.INSTANCE;
            }
        }

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public a(MainActivity mainActivity, Continuation<? super a> continuation) {
            super(2, continuation);
            this.f = mainActivity;
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        @NotNull
        public final Continuation<Unit> create(@Nullable Object obj, @NotNull Continuation<?> continuation) {
            a aVar = new a(this.f, continuation);
            aVar.e = obj;
            return aVar;
        }

        @Override // kotlin.jvm.functions.Function2
        /* JADX INFO: renamed from: invoke */
        public final Object mo6invoke(NetContextState netContextState, Continuation<? super Unit> continuation) {
            return ((a) create(netContextState, continuation)).invokeSuspend(Unit.INSTANCE);
        }

        /* JADX WARN: Removed duplicated region for block: B:107:0x0476  */
        /* JADX WARN: Removed duplicated region for block: B:41:0x018d  */
        /* JADX WARN: Removed duplicated region for block: B:42:0x018f  */
        /* JADX WARN: Removed duplicated region for block: B:45:0x0197  */
        /* JADX WARN: Removed duplicated region for block: B:46:0x01a0  */
        /* JADX WARN: Removed duplicated region for block: B:49:0x01ae  */
        /* JADX WARN: Removed duplicated region for block: B:50:0x01b7  */
        /* JADX WARN: Removed duplicated region for block: B:57:0x01fb  */
        /* JADX WARN: Removed duplicated region for block: B:59:0x01fe  */
        /* JADX WARN: Removed duplicated region for block: B:70:0x032a  */
        /* JADX WARN: Removed duplicated region for block: B:95:0x03d3 A[RETURN] */
        /* JADX WARN: Removed duplicated region for block: B:96:0x03d4  */
        /* JADX WARN: Removed duplicated region for block: B:99:0x03e5  */
        /* JADX WARN: Type inference failed for: r2v7, types: [java.util.LinkedHashMap, java.util.Map, java.util.Map<java.lang.String, com.byd.overseaappstore.module.ability.util.download.data.SoftwareOptParam>] */
        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        @org.jetbrains.annotations.Nullable
        /*
            Code decompiled incorrectly, please refer to instructions dump.
            To view partially-correct code enable 'Show inconsistent code' option in preferences
        */
        public final java.lang.Object invokeSuspend(@org.jetbrains.annotations.NotNull java.lang.Object r22) {
            /*
                Method dump skipped, instruction units count: 1288
                To view this dump change 'Code comments level' option to 'DEBUG'
            */
            throw new UnsupportedOperationException("Method not decompiled: spi.kh.a.invokeSuspend(java.lang.Object):java.lang.Object");
        }
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public kh(MainActivity mainActivity, Continuation<? super kh> continuation) {
        super(2, continuation);
        this.b = mainActivity;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    @NotNull
    public final Continuation<Unit> create(@Nullable Object obj, @NotNull Continuation<?> continuation) {
        return new kh(this.b, continuation);
    }

    @Override // kotlin.jvm.functions.Function2
    /* JADX INFO: renamed from: invoke */
    public final Object mo6invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
        return ((kh) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    @Nullable
    public final Object invokeSuspend(@NotNull Object obj) {
        Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        int i = this.a;
        if (i == 0) {
            ResultKt.throwOnFailure(obj);
            MainActivity mainActivity = this.b;
            int i2 = MainActivity.s;
            MutableStateFlow<NetContextState> mutableStateFlow = mainActivity.s().n;
            a aVar = new a(this.b, null);
            this.a = 1;
            if (FlowKt.collectLatest(mutableStateFlow, aVar, this) == coroutine_suspended) {
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
