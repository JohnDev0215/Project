package com.byd.overseaappstore.module.ability.util.notify.alone.sequence;

import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;

/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0006\b\u0007\u0018\u00002\u00020\u0001B\u0017\b\u0007\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005¢\u0006\u0002\u0010\u0006R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0007\u0010\bR\u0011\u0010\u0004\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\t\u0010\n¨\u0006\u000b"}, d2 = {"Lcom/byd/overseaappstore/module/ability/util/notify/alone/sequence/ExecutorSequenceDispatcherMgr;", "", "initSyncFinishDispatcher", "Lcom/byd/overseaappstore/module/ability/util/notify/alone/sequence/ExecutorInitSyncFinishDispatcher;", "updateFetchFinishDispatcher", "Lcom/byd/overseaappstore/module/ability/util/notify/alone/sequence/ExecutorUpdateFetchFinishDispatcher;", "(Lcom/byd/overseaappstore/module/ability/util/notify/alone/sequence/ExecutorInitSyncFinishDispatcher;Lcom/byd/overseaappstore/module/ability/util/notify/alone/sequence/ExecutorUpdateFetchFinishDispatcher;)V", "getInitSyncFinishDispatcher", "()Lcom/byd/overseaappstore/module/ability/util/notify/alone/sequence/ExecutorInitSyncFinishDispatcher;", "getUpdateFetchFinishDispatcher", "()Lcom/byd/overseaappstore/module/ability/util/notify/alone/sequence/ExecutorUpdateFetchFinishDispatcher;", "module_ability_flavor_8155f_proRelease"}, k = 1, mv = {1, 6, 0}, xi = 48)
public final class ExecutorSequenceDispatcherMgr {

    @NotNull
    private final ExecutorInitSyncFinishDispatcher initSyncFinishDispatcher;

    @NotNull
    private final ExecutorUpdateFetchFinishDispatcher updateFetchFinishDispatcher;

    public ExecutorSequenceDispatcherMgr(@NotNull ExecutorInitSyncFinishDispatcher initSyncFinishDispatcher, @NotNull ExecutorUpdateFetchFinishDispatcher updateFetchFinishDispatcher) {
        Intrinsics.checkNotNullParameter(initSyncFinishDispatcher, "initSyncFinishDispatcher");
        Intrinsics.checkNotNullParameter(updateFetchFinishDispatcher, "updateFetchFinishDispatcher");
        this.initSyncFinishDispatcher = initSyncFinishDispatcher;
        this.updateFetchFinishDispatcher = updateFetchFinishDispatcher;
    }

    @NotNull
    public final ExecutorInitSyncFinishDispatcher getInitSyncFinishDispatcher() {
        return this.initSyncFinishDispatcher;
    }

    @NotNull
    public final ExecutorUpdateFetchFinishDispatcher getUpdateFetchFinishDispatcher() {
        return this.updateFetchFinishDispatcher;
    }
}
