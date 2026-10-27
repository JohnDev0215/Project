package com.byd.music.utils.task;

import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import com.byd.music.utils.LogUtil;
import com.byd.music.utils.task.Task;
import com.byd.music.utils.task.pool.TaskPriority;
import com.byd.music.utils.task.pool.TaskPriorityExecutor;
import com.byd.music.utils.task.pool.TaskPriorityRunnable;
import com.byd.music.utils.task.pool.TaskThreadFactory;
import java.util.Timer;
import java.util.concurrent.Executor;

/* JADX INFO: loaded from: classes5.dex */
final class TaskProxy<ResultType> extends Task<ResultType> {
    private static final int MSG_WHAT_ON_CANCEL = 5;
    private static final int MSG_WHAT_ON_ERROR = 3;
    private static final int MSG_WHAT_ON_FINISH = 2;
    private static final int MSG_WHAT_ON_START = 1;
    private static final int MSG_WHAT_ON_UPDATE = 4;
    private Task.CancelledException cancelledException;
    private Throwable exception;
    private Executor executor;
    private ResultType result;
    private final Task<ResultType> task;
    static final InternalHandler INTERNAL_HANDLER = new InternalHandler();
    static final Timer TIMER = new Timer("byd-common-timer");
    static TaskPriorityExecutor sDefaultExecutor = new TaskPriorityExecutor();
    static TaskPriorityExecutor sSearchExecutor = null;
    static TaskPriorityExecutor sNetExecutor = null;
    static TaskPriorityExecutor sAE8EngineExecutor = null;
    static TaskPriorityExecutor sUDiskExecutor = null;
    static TaskPriorityExecutor sTtsInitializeExecutor = null;
    static TaskPriorityExecutor sSyncSdkIOHandleExecutor = null;
    static TaskPriorityExecutor sAdapterExecutor = null;
    static TaskPriorityExecutor sLogUtilExecutor = null;
    static TaskPriorityExecutor sActivateLogExecutor = null;

    TaskProxy(Task<ResultType> task) {
        if (task == null) {
            throw new IllegalArgumentException("task must not be null");
        }
        this.task = task;
        Executor executor = task.getExecutor();
        this.executor = executor;
        if (executor == null) {
            this.executor = sDefaultExecutor;
        }
    }

    /* JADX WARN: In static synchronized method top region not synchronized by class const: (wrap:java.lang.Class:0x0000: CONST_CLASS  A[WRAPPED] com.byd.music.utils.task.TaskProxy.class) */
    public static synchronized TaskPriorityExecutor getExecute(TaskExector taskExector) {
        synchronized (TaskProxy.class) {
            if (TaskExector.DEFALUT.equals(taskExector)) {
                if (sDefaultExecutor == null || sDefaultExecutor.isShutdown()) {
                    sDefaultExecutor = new TaskPriorityExecutor();
                }
                return sDefaultExecutor;
            }
            if (TaskExector.SEARCH.equals(taskExector)) {
                if (sSearchExecutor == null || sSearchExecutor.isShutdown()) {
                    sSearchExecutor = new TaskPriorityExecutor(2, new TaskThreadFactory("Search"));
                }
                return sSearchExecutor;
            }
            if (TaskExector.NET_WORK.equals(taskExector)) {
                if (sNetExecutor == null || sNetExecutor.isShutdown()) {
                    sNetExecutor = new TaskPriorityExecutor(1, new TaskThreadFactory("Net"));
                }
                return sNetExecutor;
            }
            if (TaskExector.AE8_ENGINE.equals(taskExector)) {
                if (sAE8EngineExecutor == null || sAE8EngineExecutor.isShutdown()) {
                    sAE8EngineExecutor = new TaskPriorityExecutor(1, new TaskThreadFactory("AE8_Engine"));
                }
                return sAE8EngineExecutor;
            }
            if (TaskExector.UDISK_DOWNLOAD.equals(taskExector)) {
                if (sUDiskExecutor == null || sUDiskExecutor.isShutdown()) {
                    sUDiskExecutor = new TaskPriorityExecutor(1, new TaskThreadFactory("UDISK_DOWNLOAD"));
                }
                return sUDiskExecutor;
            }
            if (TaskExector.SYNC_SDK_IO.equals(taskExector)) {
                if (sSyncSdkIOHandleExecutor == null || sSyncSdkIOHandleExecutor.isShutdown()) {
                    sSyncSdkIOHandleExecutor = new TaskPriorityExecutor(1, new TaskThreadFactory("SYNC_SDK_IO_HANDLE"));
                }
                return sSyncSdkIOHandleExecutor;
            }
            if (TaskExector.USER_BL.equals(taskExector)) {
                if (sSyncSdkIOHandleExecutor == null || sSyncSdkIOHandleExecutor.isShutdown()) {
                    sSyncSdkIOHandleExecutor = new TaskPriorityExecutor(1, new TaskThreadFactory("USER_BL"));
                }
                return sSyncSdkIOHandleExecutor;
            }
            if (TaskExector.ADAPTER.equals(taskExector)) {
                if (sAdapterExecutor == null || sAdapterExecutor.isShutdown()) {
                    sAdapterExecutor = new TaskPriorityExecutor(1, new TaskThreadFactory("ADAPTER"));
                }
                return sAdapterExecutor;
            }
            if (TaskExector.ACTIVATE_LOG.equals(taskExector)) {
                if (sActivateLogExecutor == null || sActivateLogExecutor.isShutdown()) {
                    sActivateLogExecutor = new TaskPriorityExecutor(new TaskThreadFactory("ACTIVATE_LOG"));
                }
                return sActivateLogExecutor;
            }
            return sDefaultExecutor;
        }
    }

    /* JADX WARN: In static synchronized method top region not synchronized by class const: (wrap:java.lang.Class:0x0000: CONST_CLASS  A[WRAPPED] com.byd.music.utils.task.TaskProxy.class) */
    public static synchronized void shutDown(TaskExector taskExector) {
        synchronized (TaskProxy.class) {
            LogUtil.d("byron", "[TaskProxy] shutDown:= " + taskExector);
            if (TaskExector.SEARCH.equals(taskExector)) {
                if (sSearchExecutor != null) {
                    sSearchExecutor.getThreadPoolExecutor().shutdown();
                    sSearchExecutor = null;
                }
            } else if (TaskExector.NET_WORK.equals(taskExector)) {
                if (sNetExecutor != null) {
                    sNetExecutor.getThreadPoolExecutor().shutdown();
                    sNetExecutor = null;
                }
            } else if (TaskExector.AE8_ENGINE.equals(taskExector)) {
                if (sAE8EngineExecutor != null) {
                    sAE8EngineExecutor.getThreadPoolExecutor().shutdown();
                    sAE8EngineExecutor = null;
                }
            } else if (TaskExector.UDISK_DOWNLOAD.equals(taskExector)) {
                if (sUDiskExecutor != null) {
                    sUDiskExecutor.getThreadPoolExecutor().shutdown();
                    sUDiskExecutor = null;
                }
            } else if (TaskExector.SYNC_SDK_IO.equals(taskExector)) {
                if (sSyncSdkIOHandleExecutor != null) {
                    sSyncSdkIOHandleExecutor.getThreadPoolExecutor().shutdown();
                    sSyncSdkIOHandleExecutor = null;
                }
            } else if (TaskExector.USER_BL.equals(taskExector)) {
                if (sSyncSdkIOHandleExecutor != null) {
                    sSyncSdkIOHandleExecutor.getThreadPoolExecutor().shutdown();
                    sSyncSdkIOHandleExecutor = null;
                }
            } else if (TaskExector.ADAPTER.equals(taskExector)) {
                if (sAdapterExecutor != null) {
                    sAdapterExecutor.getThreadPoolExecutor().shutdown();
                    sAdapterExecutor = null;
                }
            } else if (TaskExector.ACTIVATE_LOG.equals(taskExector) && sActivateLogExecutor != null) {
                sActivateLogExecutor.getThreadPoolExecutor().shutdown();
                sActivateLogExecutor = null;
            }
        }
    }

    public static void onDestory() {
        LogUtil.d("byron", "[TaskProxy] onDestory");
        InternalHandler internalHandler = INTERNAL_HANDLER;
        if (internalHandler != null) {
            internalHandler.removeCallbacksAndMessages(null);
        }
    }

    @Override // com.byd.music.utils.task.Task
    protected ResultType doBackground() throws Exception {
        setState(Task.State.Waiting);
        this.executor.execute(new TaskPriorityRunnable(this.task.getPriority(), null, new Runnable() { // from class: com.byd.music.utils.task.TaskProxy.1
            /* JADX WARN: Multi-variable type inference failed */
            @Override // java.lang.Runnable
            public void run() {
                try {
                    LogUtil.d("proxy", "taskProxy 1");
                    TaskProxy.this.setState(Task.State.Running);
                    TaskProxy.this.onStart();
                    TaskProxy.this.result = TaskProxy.this.task.doBackground();
                    if (TaskProxy.this.state != Task.State.Cancelled) {
                        TaskProxy.this.setState(Task.State.Finished);
                        TaskProxy.this.onFinished(TaskProxy.this.result);
                    } else {
                        LogUtil.d("proxy", "taskProxy 1 cancelled");
                        throw new Task.CancelledException("");
                    }
                } catch (Task.CancelledException e) {
                    LogUtil.d("proxy", "taskProxy e1 = {?}" + e);
                    TaskProxy.this.setState(Task.State.Cancelled);
                    TaskProxy.this.onCancelled(e);
                } catch (Throwable th) {
                    LogUtil.d("proxy", "taskProxy e2 = {?}" + th);
                    TaskProxy.this.setState(Task.State.Error);
                    TaskProxy.this.onError(th, false);
                }
            }
        }));
        return null;
    }

    @Override // com.byd.music.utils.task.Task
    protected void onFinished(ResultType resulttype) {
        INTERNAL_HANDLER.obtainMessage(2, this).sendToTarget();
    }

    @Override // com.byd.music.utils.task.Task
    protected void onError(Throwable th, boolean z) {
        this.exception = th;
        INTERNAL_HANDLER.obtainMessage(3, this).sendToTarget();
    }

    @Override // com.byd.music.utils.task.Task
    protected void onStart() {
        INTERNAL_HANDLER.obtainMessage(1, this).sendToTarget();
    }

    @Override // com.byd.music.utils.task.Task
    protected void onUpdate(int i, Object... objArr) {
        INTERNAL_HANDLER.obtainMessage(4, i, 0, new ArgsObj(this, objArr)).sendToTarget();
    }

    @Override // com.byd.music.utils.task.Task
    protected void onCancelled(Task.CancelledException cancelledException) {
        this.cancelledException = cancelledException;
        INTERNAL_HANDLER.obtainMessage(5, this).sendToTarget();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void setState(Task.State state) {
        this.state = state;
        this.task.state = state;
    }

    @Override // com.byd.music.utils.task.Task
    public TaskPriority getPriority() {
        return this.task.getPriority();
    }

    @Override // com.byd.music.utils.task.Task
    public Executor getExecutor() {
        return this.task.getExecutor();
    }

    private static class ArgsObj {
        Object[] args;
        TaskProxy taskProxy;

        public ArgsObj(TaskProxy taskProxy, Object[] objArr) {
            this.taskProxy = taskProxy;
            this.args = objArr;
        }
    }

    static final class InternalHandler extends Handler {
        private InternalHandler() {
            super(Looper.getMainLooper());
        }

        /* JADX WARN: Multi-variable type inference failed */
        @Override // android.os.Handler
        public void handleMessage(Message message) {
            Object[] objArr;
            if (message.obj == null) {
                throw new IllegalArgumentException("msg must not be null");
            }
            TaskProxy taskProxy = null;
            if (message.obj instanceof TaskProxy) {
                taskProxy = (TaskProxy) message.obj;
                objArr = null;
            } else if (message.obj instanceof ArgsObj) {
                ArgsObj argsObj = (ArgsObj) message.obj;
                taskProxy = argsObj.taskProxy;
                objArr = argsObj.args;
            } else {
                objArr = null;
            }
            if (taskProxy == null) {
                throw new RuntimeException("msg.obj not instanceof TaskProxy");
            }
            try {
                int i = message.what;
                if (i == 1) {
                    taskProxy.task.onStart();
                    message = message;
                } else if (i == 2) {
                    taskProxy.task.onFinished(taskProxy.result);
                    message = message;
                } else if (i == 3) {
                    try {
                        Task task = taskProxy.task;
                        Throwable th = taskProxy.exception;
                        taskProxy = null;
                        task.onError(th, false);
                        message = task;
                    } catch (Throwable unused) {
                    }
                } else if (i == 4) {
                    taskProxy.task.onUpdate(message.arg1, objArr);
                    message = message;
                } else if (i != 5) {
                    message = message;
                } else {
                    taskProxy.task.onCancelled(taskProxy.cancelledException);
                    message = message;
                }
            } catch (Throwable th2) {
                taskProxy.setState(Task.State.Error);
                if (message.what != 3) {
                    taskProxy.task.onError(th2, true);
                } else {
                    th2.printStackTrace();
                }
            }
        }
    }
}
