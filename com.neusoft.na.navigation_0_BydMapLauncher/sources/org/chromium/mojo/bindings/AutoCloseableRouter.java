package org.chromium.mojo.bindings;

import java.util.concurrent.Executor;
import org.chromium.mojo.system.Core;
import org.chromium.mojo.system.MessagePipeHandle;

/* JADX INFO: loaded from: classes3.dex */
class AutoCloseableRouter implements Router {
    private boolean mClosed;
    private final Executor mExecutor;
    private final Router mRouter;

    public AutoCloseableRouter(Core core, Router router) {
        this.mRouter = router;
        this.mExecutor = ExecutorFactory.getExecutorForCurrentThread(core);
    }

    @Override // org.chromium.mojo.bindings.Router
    public void setIncomingMessageReceiver(MessageReceiverWithResponder messageReceiverWithResponder) {
        this.mRouter.setIncomingMessageReceiver(messageReceiverWithResponder);
    }

    @Override // org.chromium.mojo.bindings.HandleOwner
    public MessagePipeHandle passHandle() {
        return this.mRouter.passHandle();
    }

    @Override // org.chromium.mojo.bindings.MessageReceiver
    public boolean accept(Message message) {
        return this.mRouter.accept(message);
    }

    @Override // org.chromium.mojo.bindings.MessageReceiverWithResponder
    public boolean acceptWithResponder(Message message, MessageReceiver messageReceiver) {
        return this.mRouter.acceptWithResponder(message, messageReceiver);
    }

    @Override // org.chromium.mojo.bindings.Router
    public void start() {
        this.mRouter.start();
    }

    @Override // org.chromium.mojo.bindings.Router
    public void setErrorHandler(ConnectionErrorHandler connectionErrorHandler) {
        this.mRouter.setErrorHandler(connectionErrorHandler);
    }

    @Override // org.chromium.mojo.bindings.MessageReceiver, java.io.Closeable, java.lang.AutoCloseable, org.chromium.mojo.bindings.HandleOwner
    public void close() {
        this.mRouter.close();
        this.mClosed = true;
    }

    protected void finalize() throws Throwable {
        if (!this.mClosed) {
            this.mExecutor.execute(new Runnable() { // from class: org.chromium.mojo.bindings.AutoCloseableRouter.1
                @Override // java.lang.Runnable
                public void run() {
                    AutoCloseableRouter.this.close();
                }
            });
            throw new IllegalStateException("Warning: Router objects should be explicitly closed when no longer required otherwise you may leak handles.");
        }
        super.finalize();
    }
}
