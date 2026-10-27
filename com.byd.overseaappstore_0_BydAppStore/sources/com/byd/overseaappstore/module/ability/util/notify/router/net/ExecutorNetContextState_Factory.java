package com.byd.overseaappstore.module.ability.util.notify.router.net;

import android.content.Context;
import spi.ll;
import spi.z3;

/* JADX INFO: loaded from: classes.dex */
public final class ExecutorNetContextState_Factory implements ll {
    private final ll<z3> configRepositoryProvider;
    private final ll<Context> contextProvider;

    public ExecutorNetContextState_Factory(ll<z3> llVar, ll<Context> llVar2) {
        this.configRepositoryProvider = llVar;
        this.contextProvider = llVar2;
    }

    public static ExecutorNetContextState_Factory create(ll<z3> llVar, ll<Context> llVar2) {
        return new ExecutorNetContextState_Factory(llVar, llVar2);
    }

    public static ExecutorNetContextState newInstance(z3 z3Var, Context context) {
        return new ExecutorNetContextState(z3Var, context);
    }

    @Override // spi.ll
    public ExecutorNetContextState get() {
        return newInstance(this.configRepositoryProvider.get(), this.contextProvider.get());
    }
}
