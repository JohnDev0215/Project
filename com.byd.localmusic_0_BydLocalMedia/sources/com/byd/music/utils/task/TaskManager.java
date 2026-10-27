package com.byd.music.utils.task;

import android.os.Looper;
import com.byd.music.utils.LogUtil;
import com.byd.music.utils.task.pool.TaskPriorityExecutor;
import java.util.TimerTask;

/* JADX INFO: loaded from: classes5.dex */
public class TaskManager {
    private TaskManager() {
    }

    public static <T> Task<T> start(Task<T> task) {
        TaskProxy taskProxy;
        if (task instanceof TaskProxy) {
            taskProxy = (TaskProxy) task;
            LogUtil.d("proxy", "proxy");
        } else {
            TaskProxy taskProxy2 = new TaskProxy(task);
            task.taskProxy = taskProxy2;
            LogUtil.d("proxy", "taskProxy");
            taskProxy = taskProxy2;
        }
        try {
            LogUtil.d("proxy", "proxy.doBackground()");
            taskProxy.doBackground();
        } catch (Throwable th) {
            LogUtil.e("Task", "Task error " + th);
        }
        return taskProxy;
    }

    public static void post(Runnable runnable) {
        if (runnable == null) {
            return;
        }
        Looper mainLooper = Looper.getMainLooper();
        if (mainLooper != null && mainLooper.getThread() == Thread.currentThread()) {
            runnable.run();
        } else {
            TaskProxy.INTERNAL_HANDLER.post(runnable);
        }
    }

    public static void postAfterQueue(Runnable runnable) {
        if (runnable == null) {
            return;
        }
        TaskProxy.INTERNAL_HANDLER.post(runnable);
    }

    public static void postDelayed(Runnable runnable, long j) {
        if (runnable == null) {
            return;
        }
        TaskProxy.INTERNAL_HANDLER.postDelayed(runnable, j);
    }

    public static void removeCallbacks(Runnable runnable) {
        if (runnable == null) {
            return;
        }
        TaskProxy.INTERNAL_HANDLER.removeCallbacks(runnable);
    }

    public static void removeCallbacksBG(Runnable runnable) {
        if (runnable == null) {
            return;
        }
        TaskProxy.sDefaultExecutor.getThreadPoolExecutor().remove(runnable);
    }

    public static void schedule(TimerTask timerTask, long j) {
        if (timerTask == null) {
            return;
        }
        TaskProxy.TIMER.schedule(timerTask, j);
    }

    public static void schedule(TimerTask timerTask, long j, long j2) {
        if (timerTask == null) {
            return;
        }
        TaskProxy.TIMER.schedule(timerTask, j, j2);
    }

    public static void run(Runnable runnable) {
        if (runnable == null) {
            return;
        }
        if (TaskProxy.sDefaultExecutor != null && !TaskProxy.sDefaultExecutor.isFull()) {
            TaskProxy.sDefaultExecutor.execute(runnable);
        } else {
            new Thread(runnable).start();
        }
    }

    public static TaskPriorityExecutor getExecute(TaskExector taskExector) {
        return TaskProxy.getExecute(taskExector);
    }

    public static void shutDown(TaskExector taskExector) {
        TaskProxy.shutDown(taskExector);
    }

    public static void onDestory() {
        TaskProxy.onDestory();
    }
}
