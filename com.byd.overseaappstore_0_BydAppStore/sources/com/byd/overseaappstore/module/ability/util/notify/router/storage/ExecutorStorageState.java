package com.byd.overseaappstore.module.ability.util.notify.router.storage;

import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.flow.MutableStateFlow;
import kotlinx.coroutines.flow.StateFlow;
import kotlinx.coroutines.flow.StateFlowKt;
import org.jetbrains.annotations.NotNull;

/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\u0002\n\u0002\b\u0002\b\u0007\u0018\u00002\u00020\u0001B\u0007\b\u0007¢\u0006\u0002\u0010\u0002J\u0006\u0010\n\u001a\u00020\u0005J\u000e\u0010\u000b\u001a\u00020\f2\u0006\u0010\r\u001a\u00020\u0005R\u0014\u0010\u0003\u001a\b\u0012\u0004\u0012\u00020\u00050\u0004X\u0082\u0004¢\u0006\u0002\n\u0000R\u0017\u0010\u0006\u001a\b\u0012\u0004\u0012\u00020\u00050\u0007¢\u0006\b\n\u0000\u001a\u0004\b\b\u0010\t¨\u0006\u000e"}, d2 = {"Lcom/byd/overseaappstore/module/ability/util/notify/router/storage/ExecutorStorageState;", "", "()V", "_storageStateDataFlow", "Lkotlinx/coroutines/flow/MutableStateFlow;", "Lcom/byd/overseaappstore/module/ability/util/notify/router/storage/StorageState;", "storageStateDataFlow", "Lkotlinx/coroutines/flow/StateFlow;", "getStorageStateDataFlow", "()Lkotlinx/coroutines/flow/StateFlow;", "initSpaceState", "notifyNetContextStateData", "", "storageState", "module_ability_flavor_8155f_proRelease"}, k = 1, mv = {1, 6, 0}, xi = 48)
public final class ExecutorStorageState {

    @NotNull
    private final MutableStateFlow<StorageState> _storageStateDataFlow;

    @NotNull
    private final StateFlow<StorageState> storageStateDataFlow;

    public ExecutorStorageState() {
        MutableStateFlow<StorageState> MutableStateFlow = StateFlowKt.MutableStateFlow(initSpaceState());
        this._storageStateDataFlow = MutableStateFlow;
        this.storageStateDataFlow = MutableStateFlow;
    }

    @NotNull
    public final StateFlow<StorageState> getStorageStateDataFlow() {
        return this.storageStateDataFlow;
    }

    @NotNull
    public final StorageState initSpaceState() {
        return new StorageState((byte) 0);
    }

    public final void notifyNetContextStateData(@NotNull StorageState storageState) {
        Intrinsics.checkNotNullParameter(storageState, "storageState");
        this._storageStateDataFlow.setValue(storageState);
    }
}
