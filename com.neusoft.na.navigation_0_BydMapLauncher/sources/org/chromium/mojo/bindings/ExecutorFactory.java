package org.chromium.mojo.bindings;

import java.nio.ByteBuffer;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Executor;
import org.chromium.mojo.system.AsyncWaiter;
import org.chromium.mojo.system.Core;
import org.chromium.mojo.system.MessagePipeHandle;
import org.chromium.mojo.system.MojoException;
import org.chromium.mojo.system.Pair;

/* JADX INFO: loaded from: classes3.dex */
class ExecutorFactory {
    private static final ThreadLocal<Executor> EXECUTORS = new ThreadLocal<>();
    private static final ByteBuffer NOTIFY_BUFFER = null;

    ExecutorFactory() {
    }

    private static class PipedExecutor implements Executor, AsyncWaiter.Callback {
        static final /* synthetic */ boolean $assertionsDisabled = false;
        private final Object mLock = new Object();
        private final List<Runnable> mPendingActions;
        private final MessagePipeHandle mReadHandle;
        private final AsyncWaiter mWaiter;
        private final MessagePipeHandle mWriteHandle;

        public PipedExecutor(Core core) {
            this.mWaiter = core.getDefaultAsyncWaiter();
            Pair<MessagePipeHandle, MessagePipeHandle> pairCreateMessagePipe = core.createMessagePipe(new MessagePipeHandle.CreateOptions());
            this.mReadHandle = pairCreateMessagePipe.first;
            this.mWriteHandle = pairCreateMessagePipe.second;
            this.mPendingActions = new ArrayList();
            asyncWait();
        }

        private void asyncWait() {
            this.mWaiter.asyncWait(this.mReadHandle, Core.HandleSignals.READABLE, -1L, this);
        }

        @Override // org.chromium.mojo.system.AsyncWaiter.Callback
        public void onResult(int i) {
            if (i == 0 && readNotifyBufferMessage()) {
                runNextAction();
            } else {
                close();
            }
        }

        @Override // org.chromium.mojo.system.AsyncWaiter.Callback
        public void onError(MojoException mojoException) {
            close();
        }

        private void close() {
            synchronized (this.mLock) {
                this.mWriteHandle.close();
                this.mPendingActions.clear();
            }
            this.mReadHandle.close();
        }

        private boolean readNotifyBufferMessage() {
            try {
                if (this.mReadHandle.readMessage(ExecutorFactory.NOTIFY_BUFFER, 0, MessagePipeHandle.ReadFlags.NONE).getMojoResult() != 0) {
                    return false;
                }
                asyncWait();
                return true;
            } catch (MojoException unused) {
                return false;
            }
        }

        private void runNextAction() {
            Runnable runnableRemove;
            synchronized (this.mLock) {
                runnableRemove = this.mPendingActions.remove(0);
            }
            runnableRemove.run();
        }

        @Override // java.util.concurrent.Executor
        public void execute(Runnable runnable) {
            synchronized (this.mLock) {
                if (!this.mWriteHandle.isValid()) {
                    throw new IllegalStateException("Trying to execute an action on a closed executor.");
                }
                this.mPendingActions.add(runnable);
                this.mWriteHandle.writeMessage(ExecutorFactory.NOTIFY_BUFFER, null, MessagePipeHandle.WriteFlags.NONE);
            }
        }
    }

    public static Executor getExecutorForCurrentThread(Core core) {
        ThreadLocal<Executor> threadLocal = EXECUTORS;
        Executor executor = threadLocal.get();
        if (executor != null) {
            return executor;
        }
        PipedExecutor pipedExecutor = new PipedExecutor(core);
        threadLocal.set(pipedExecutor);
        return pipedExecutor;
    }
}
