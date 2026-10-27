package com.bugsnag.android.internal.dag;

import com.bugsnag.android.internal.BackgroundTaskService;
import com.bugsnag.android.internal.TaskType;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import kotlin.Lazy;
import kotlin.LazyKt__LazyJVMKt;
import kotlin.Metadata;
import kotlin.Result;
import kotlin.ResultKt;
import kotlin.jvm.functions.Function0;

/* JADX INFO: compiled from: DependencyModule.kt */
/* JADX INFO: loaded from: classes5.dex */
@Metadata(bv = {1, 0, 3}, d1 = {"\u00000\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010!\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\b \u0018\u00002\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0002J \u0010\u0006\u001a\b\u0012\u0004\u0012\u0002H\u00070\u0005\"\u0004\b\u0000\u0010\u00072\f\u0010\b\u001a\b\u0012\u0004\u0012\u0002H\u00070\tJ\u0016\u0010\n\u001a\u00020\u000b2\u0006\u0010\f\u001a\u00020\r2\u0006\u0010\u000e\u001a\u00020\u000fR\u0018\u0010\u0003\u001a\f\u0012\b\u0012\u0006\u0012\u0002\b\u00030\u00050\u0004X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u0010"}, d2 = {"Lcom/bugsnag/android/internal/dag/DependencyModule;", "", "()V", "properties", "", "Lkotlin/Lazy;", "future", "T", "initializer", "Lkotlin/Function0;", "resolveDependencies", "", "bgTaskService", "Lcom/bugsnag/android/internal/BackgroundTaskService;", "taskType", "Lcom/bugsnag/android/internal/TaskType;", "bugsnag-android-core_release"}, k = 1, mv = {1, 4, 2})
public abstract class DependencyModule {
    private final List<Lazy<?>> properties = new ArrayList();

    public final <T> Lazy<T> future(final Function0<? extends T> initializer) {
        Lazy<T> lazy = LazyKt__LazyJVMKt.lazy(new Function0<T>() { // from class: com.bugsnag.android.internal.dag.DependencyModule$future$lazy$1
            {
                super(0);
            }

            @Override // kotlin.jvm.functions.Function0
            public final T invoke() {
                return (T) initializer.invoke();
            }
        });
        this.properties.add(lazy);
        return lazy;
    }

    public final void resolveDependencies(final BackgroundTaskService bgTaskService, final TaskType taskType) {
        try {
            Result.Companion companion = Result.INSTANCE;
            Result.m2810constructorimpl(bgTaskService.submitTask(taskType, new Runnable() { // from class: com.bugsnag.android.internal.dag.DependencyModule$resolveDependencies$$inlined$runCatching$lambda$1
                @Override // java.lang.Runnable
                public final void run() {
                    Iterator it = this.this$0.properties.iterator();
                    while (it.hasNext()) {
                        ((Lazy) it.next()).getValue();
                    }
                }
            }).get());
        } catch (Throwable th) {
            Result.Companion companion2 = Result.INSTANCE;
            Result.m2810constructorimpl(ResultKt.createFailure(th));
        }
    }
}
