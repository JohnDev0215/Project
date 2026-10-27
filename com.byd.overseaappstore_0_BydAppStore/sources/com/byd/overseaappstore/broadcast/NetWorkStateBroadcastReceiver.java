package com.byd.overseaappstore.broadcast;

import android.content.Context;
import android.content.Intent;
import com.byd.overseaappstore.module.ability.util.SettingUtil;
import com.byd.overseaappstore.module.ability.util.notify.router.ExecutorDialogRouterMgr;
import com.byd.overseaappstore.module.ability.util.notify.router.net.ExecutorNetContextState;
import com.byd.overseaappstore.module.ability.util.notify.router.net.NetContextState;
import com.byd.overseaappstore.module.ability.util.notify.router.net.WifiChangedState;
import kotlin.Metadata;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.Boxing;
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
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import spi.a2;
import spi.ct;
import spi.e;
import spi.l9;
import spi.qj;

/* JADX INFO: loaded from: classes.dex */
@Metadata(bv = {}, d1 = {"\u0000\u001c\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\b\u0007\u0018\u00002\u00020\u0001B!\b\u0007\u0012\u0006\u0010\u0003\u001a\u00020\u0002\u0012\u0006\u0010\u0005\u001a\u00020\u0004\u0012\u0006\u0010\u0007\u001a\u00020\u0006¢\u0006\u0004\b\b\u0010\t¨\u0006\n"}, d2 = {"Lcom/byd/overseaappstore/broadcast/NetWorkStateBroadcastReceiver;", "Landroid/content/BroadcastReceiver;", "Lcom/byd/overseaappstore/module/ability/util/notify/router/ExecutorDialogRouterMgr;", "executorDialogRouterMgr", "Lspi/l9;", "executorDownloadTaskMgr", "Lcom/byd/overseaappstore/module/ability/util/notify/router/net/ExecutorNetContextState;", "executorNetContextState", "<init>", "(Lcom/byd/overseaappstore/module/ability/util/notify/router/ExecutorDialogRouterMgr;Lspi/l9;Lcom/byd/overseaappstore/module/ability/util/notify/router/net/ExecutorNetContextState;)V", "app_flavor_8155f_proRelease"}, k = 1, mv = {1, 6, 0})
public final class NetWorkStateBroadcastReceiver extends Hilt_NetWorkStateBroadcastReceiver {

    @NotNull
    public final ExecutorDialogRouterMgr c;

    @NotNull
    public final l9 d;

    @NotNull
    public final ExecutorNetContextState e;

    @DebugMetadata(c = "com.byd.overseaappstore.broadcast.NetWorkStateBroadcastReceiver$onReceive$1", f = "NetWorkStateBroadcastReceiver.kt", i = {0, 0}, l = {42}, m = "invokeSuspend", n = {"wifiConnected", "cellularConnected"}, s = {"Z$0", "Z$1"})
    public static final class a extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
        public boolean a;
        public boolean b;
        public int c;
        public final /* synthetic */ Context d;
        public final /* synthetic */ Intent e;
        public final /* synthetic */ NetWorkStateBroadcastReceiver f;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public a(Context context, Intent intent, NetWorkStateBroadcastReceiver netWorkStateBroadcastReceiver, Continuation<? super a> continuation) {
            super(2, continuation);
            this.d = context;
            this.e = intent;
            this.f = netWorkStateBroadcastReceiver;
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        @NotNull
        public final Continuation<Unit> create(@Nullable Object obj, @NotNull Continuation<?> continuation) {
            return new a(this.d, this.e, this.f, continuation);
        }

        @Override // kotlin.jvm.functions.Function2
        /* JADX INFO: renamed from: invoke */
        public final Object mo6invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
            return ((a) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        @Nullable
        public final Object invokeSuspend(@NotNull Object obj) {
            boolean zI;
            Object objA;
            boolean z;
            Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
            int i = this.c;
            if (i == 0) {
                ResultKt.throwOnFailure(obj);
                Context context = this.d;
                if (context == null) {
                    Intent intent = this.e;
                    ct.a(e.d("broadcast networkCheck action:", intent != null ? intent.getAction() : null, " context is null"), new Object[0]);
                    return Unit.INSTANCE;
                }
                qj qjVar = qj.a;
                zI = qjVar.i(context);
                boolean zC = qjVar.c(this.d);
                SettingUtil settingUtil = SettingUtil.a;
                Context context2 = this.d;
                this.a = zI;
                this.b = zC;
                this.c = 1;
                objA = settingUtil.a(context2, this);
                if (objA == coroutine_suspended) {
                    return coroutine_suspended;
                }
                z = zC;
            } else {
                if (i != 1) {
                    throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                }
                boolean z2 = this.b;
                zI = this.a;
                ResultKt.throwOnFailure(obj);
                objA = obj;
                z = z2;
            }
            NetContextState netContextState = new NetContextState(zI, z, ((Boolean) objA).booleanValue(), null, new a2((byte) 0, (byte) 0, (byte) 0, 7, null), false, 40, null);
            if (zI) {
                this.f.d.c();
            }
            this.f.e.updateWifiChangedState(Boxing.boxBoolean(zI));
            WifiChangedState wifiChangedState = this.f.e.getWifiChangedState();
            if (Intrinsics.areEqual(wifiChangedState.getPreState(), Boxing.boxBoolean(true)) && Intrinsics.areEqual(wifiChangedState.getNowState(), Boxing.boxBoolean(false))) {
                boolean workWifiOnly = netContextState.getWorkWifiOnly();
                Boolean preState = wifiChangedState.getPreState();
                Boolean nowState = wifiChangedState.getNowState();
                boolean cellularConnected = netContextState.getCellularConnected();
                Intent intent2 = this.e;
                ct.a("broadcast networkCheck workNotify workWifiOnly:" + workWifiOnly + " wifiPreState :" + preState + " wifiNowState :" + nowState + " cellular:" + cellularConnected + ",action:" + (intent2 != null ? intent2.getAction() : null), new Object[0]);
                this.f.c.addBizTypeAndData(1, netContextState).workNotify();
            } else {
                boolean workWifiOnly2 = netContextState.getWorkWifiOnly();
                Boolean preState2 = wifiChangedState.getPreState();
                Boolean nowState2 = wifiChangedState.getNowState();
                boolean cellularConnected2 = netContextState.getCellularConnected();
                Intent intent3 = this.e;
                ct.a("broadcast networkCheck no intervene workWifiOnly:" + workWifiOnly2 + " wifiPreState :" + preState2 + " wifiNowState :" + nowState2 + " cellular:" + cellularConnected2 + ",action:" + (intent3 != null ? intent3.getAction() : null), new Object[0]);
            }
            return Unit.INSTANCE;
        }
    }

    public NetWorkStateBroadcastReceiver(@NotNull ExecutorDialogRouterMgr executorDialogRouterMgr, @NotNull l9 executorDownloadTaskMgr, @NotNull ExecutorNetContextState executorNetContextState) {
        Intrinsics.checkNotNullParameter(executorDialogRouterMgr, "executorDialogRouterMgr");
        Intrinsics.checkNotNullParameter(executorDownloadTaskMgr, "executorDownloadTaskMgr");
        Intrinsics.checkNotNullParameter(executorNetContextState, "executorNetContextState");
        this.c = executorDialogRouterMgr;
        this.d = executorDownloadTaskMgr;
        this.e = executorNetContextState;
    }

    @Override // com.byd.overseaappstore.broadcast.Hilt_NetWorkStateBroadcastReceiver, android.content.BroadcastReceiver
    public final void onReceive(@Nullable Context context, @Nullable Intent intent) {
        super.onReceive(context, intent);
        BuildersKt__Builders_commonKt.launch$default(CoroutineScopeKt.CoroutineScope(JobKt__JobKt.Job$default((Job) null, 1, (Object) null).plus(Dispatchers.getMain())), null, null, new a(context, intent, this, null), 3, null);
    }
}
