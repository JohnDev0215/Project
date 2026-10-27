package com.google.firebase.concurrent;

import android.os.Handler;
import android.os.Looper;
import java.util.concurrent.Executor;

/* JADX WARN: Failed to restore enum class, 'enum' modifier and super class removed */
/* JADX WARN: Unknown enum class pattern. Please report as an issue! */
/* JADX INFO: loaded from: classes.dex */
public final class UiExecutor implements Executor {
    public static final UiExecutor c;
    public static final Handler e;
    public static final /* synthetic */ UiExecutor[] k;

    static {
        UiExecutor uiExecutor = new UiExecutor("INSTANCE", 0);
        c = uiExecutor;
        k = new UiExecutor[]{uiExecutor};
        e = new Handler(Looper.getMainLooper());
    }

    public static UiExecutor valueOf(String str) {
        return (UiExecutor) Enum.valueOf(UiExecutor.class, str);
    }

    public static UiExecutor[] values() {
        return (UiExecutor[]) k.clone();
    }

    @Override // java.util.concurrent.Executor
    public final void execute(Runnable runnable) {
        e.post(runnable);
    }
}
