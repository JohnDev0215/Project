package com.byd.overseaappstore.module.ability.util.notify.router.net;

import android.content.Context;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.BuildersKt__BuildersKt;
import kotlinx.coroutines.flow.MutableStateFlow;
import kotlinx.coroutines.flow.StateFlow;
import kotlinx.coroutines.flow.StateFlowKt;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import spi.a2;
import spi.qj;
import spi.z3;

/* JADX INFO: loaded from: classes.dex */
@Metadata(bv = {}, d1 = {"\u0000D\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0004\b\u0007\u0018\u00002\u00020\u0001B\u001b\b\u0007\u0012\u0006\u0010\u001d\u001a\u00020\u001c\u0012\b\b\u0001\u0010\u0010\u001a\u00020\u000f¢\u0006\u0004\b\u001e\u0010\u001fJ\u000e\u0010\u0005\u001a\u00020\u00042\u0006\u0010\u0003\u001a\u00020\u0002J\u0006\u0010\u0006\u001a\u00020\u0002J\u0006\u0010\b\u001a\u00020\u0007J\u0017\u0010\u000b\u001a\u00020\u00042\b\u0010\n\u001a\u0004\u0018\u00010\t¢\u0006\u0004\b\u000b\u0010\fJ\u0006\u0010\r\u001a\u00020\u0004J\u0006\u0010\u000e\u001a\u00020\u0004R\u0014\u0010\u0010\u001a\u00020\u000f8\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b\u0010\u0010\u0011R\u0014\u0010\u0012\u001a\u00020\u00078\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b\u0012\u0010\u0013R\u001a\u0010\u0015\u001a\b\u0012\u0004\u0012\u00020\u00020\u00148\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b\u0015\u0010\u0016R\u001d\u0010\u0018\u001a\b\u0012\u0004\u0012\u00020\u00020\u00178\u0006¢\u0006\f\n\u0004\b\u0018\u0010\u0019\u001a\u0004\b\u001a\u0010\u001b¨\u0006 "}, d2 = {"Lcom/byd/overseaappstore/module/ability/util/notify/router/net/ExecutorNetContextState;", "", "Lcom/byd/overseaappstore/module/ability/util/notify/router/net/NetContextState;", "netContextState", "", "notifyNetContextStateData", "initNetContextState", "Lcom/byd/overseaappstore/module/ability/util/notify/router/net/WifiChangedState;", "getWifiChangedState", "", "currentWifiConnectState", "updateWifiChangedState", "(Ljava/lang/Boolean;)V", "initWifiChangedState", "clearWifiChangedState", "Landroid/content/Context;", "context", "Landroid/content/Context;", "wifiChangedState", "Lcom/byd/overseaappstore/module/ability/util/notify/router/net/WifiChangedState;", "Lkotlinx/coroutines/flow/MutableStateFlow;", "_wifiDownloadEnableStateDataFlow", "Lkotlinx/coroutines/flow/MutableStateFlow;", "Lkotlinx/coroutines/flow/StateFlow;", "wifiDownloadEnableStateDataFlow", "Lkotlinx/coroutines/flow/StateFlow;", "getWifiDownloadEnableStateDataFlow", "()Lkotlinx/coroutines/flow/StateFlow;", "Lspi/z3;", "configRepository", "<init>", "(Lspi/z3;Landroid/content/Context;)V", "module_ability_flavor_8155f_proRelease"}, k = 1, mv = {1, 6, 0})
public final class ExecutorNetContextState {

    @NotNull
    private final MutableStateFlow<NetContextState> _wifiDownloadEnableStateDataFlow;

    @NotNull
    private final z3 configRepository;

    @NotNull
    private final Context context;

    @NotNull
    private final WifiChangedState wifiChangedState;

    @NotNull
    private final StateFlow<NetContextState> wifiDownloadEnableStateDataFlow;

    public ExecutorNetContextState(@NotNull z3 configRepository, @NotNull Context context) {
        Intrinsics.checkNotNullParameter(configRepository, "configRepository");
        Intrinsics.checkNotNullParameter(context, "context");
        this.configRepository = configRepository;
        this.context = context;
        this.wifiChangedState = new WifiChangedState(null, null, 3, null);
        MutableStateFlow<NetContextState> MutableStateFlow = StateFlowKt.MutableStateFlow(initNetContextState());
        this._wifiDownloadEnableStateDataFlow = MutableStateFlow;
        this.wifiDownloadEnableStateDataFlow = MutableStateFlow;
    }

    public final void clearWifiChangedState() {
        this.wifiChangedState.setPreState(null);
        this.wifiChangedState.setNowState(null);
    }

    @NotNull
    public final WifiChangedState getWifiChangedState() {
        return this.wifiChangedState;
    }

    @NotNull
    public final StateFlow<NetContextState> getWifiDownloadEnableStateDataFlow() {
        return this.wifiDownloadEnableStateDataFlow;
    }

    @NotNull
    public final NetContextState initNetContextState() {
        boolean zBooleanValue = ((Boolean) BuildersKt__BuildersKt.runBlocking$default(null, new ExecutorNetContextState$initNetContextState$wifiOnlyState$1(this, null), 1, null)).booleanValue();
        qj qjVar = qj.a;
        return new NetContextState(qjVar.i(this.context), qjVar.c(this.context), zBooleanValue, null, new a2((byte) 0, (byte) 0, (byte) 0, 7, null), true, 8, null);
    }

    public final void initWifiChangedState() {
        boolean zI = qj.a.i(this.context);
        this.wifiChangedState.setPreState(Boolean.valueOf(zI));
        this.wifiChangedState.setNowState(Boolean.valueOf(zI));
    }

    public final void notifyNetContextStateData(@NotNull NetContextState netContextState) {
        Intrinsics.checkNotNullParameter(netContextState, "netContextState");
        this._wifiDownloadEnableStateDataFlow.setValue(netContextState);
    }

    public final void updateWifiChangedState(@Nullable Boolean currentWifiConnectState) {
        WifiChangedState wifiChangedState = this.wifiChangedState;
        wifiChangedState.setPreState(wifiChangedState.getNowState());
        this.wifiChangedState.setNowState(currentWifiConnectState);
    }
}
