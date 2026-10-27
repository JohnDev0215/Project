package com.bugsnag.android.internal;

import com.amazon.identity.auth.device.utils.AccountConstants;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: BackgroundTaskService.kt */
/* JADX INFO: loaded from: classes5.dex */
@Metadata(bv = {}, d1 = {"\u0000 \n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\u001a \u0010\u0007\u001a\u00020\u00062\u0006\u0010\u0001\u001a\u00020\u00002\u0006\u0010\u0003\u001a\u00020\u00022\u0006\u0010\u0005\u001a\u00020\u0004H\u0000\"\u001a\u0010\u000b\u001a\u0004\u0018\u00010\u0002*\u00020\b8@X\u0080\u0004¢\u0006\u0006\u001a\u0004\b\t\u0010\n¨\u0006\f"}, d2 = {"", "name", "Lcom/bugsnag/android/internal/TaskType;", AccountConstants.SUB_AUTHENTICATOR_TYPE_ATTRIBUTE, "", "keepAlive", "Ljava/util/concurrent/ExecutorService;", "createExecutor", "Ljava/lang/Thread;", "getTaskType", "(Ljava/lang/Thread;)Lcom/bugsnag/android/internal/TaskType;", "taskType", "bugsnag-android-core_release"}, k = 2, mv = {1, 4, 2})
public final class BackgroundTaskServiceKt {
    public static final TaskType getTaskType(Thread thread) {
        if (!(thread instanceof TaskTypeThread)) {
            thread = null;
        }
        TaskTypeThread taskTypeThread = (TaskTypeThread) thread;
        if (taskTypeThread != null) {
            return taskTypeThread.getTaskType();
        }
        return null;
    }

    public static final ExecutorService createExecutor(final String str, final TaskType taskType, boolean z) {
        LinkedBlockingQueue linkedBlockingQueue = new LinkedBlockingQueue(128);
        ThreadFactory threadFactory = new ThreadFactory() { // from class: com.bugsnag.android.internal.BackgroundTaskServiceKt$createExecutor$threadFactory$1
            @Override // java.util.concurrent.ThreadFactory
            public final Thread newThread(Runnable it) {
                Intrinsics.checkExpressionValueIsNotNull(it, "it");
                return new TaskTypeThread(it, str, taskType);
            }
        };
        return new ThreadPoolExecutor(z ? 1 : 0, 1, 30L, TimeUnit.SECONDS, linkedBlockingQueue, threadFactory);
    }
}
