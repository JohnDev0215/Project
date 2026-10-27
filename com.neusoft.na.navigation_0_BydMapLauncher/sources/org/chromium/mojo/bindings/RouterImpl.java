package org.chromium.mojo.bindings;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.Executor;
import org.chromium.mojo.system.AsyncWaiter;
import org.chromium.mojo.system.Core;
import org.chromium.mojo.system.MessagePipeHandle;

/* JADX INFO: loaded from: classes3.dex */
public class RouterImpl implements Router {
    static final /* synthetic */ boolean $assertionsDisabled = false;
    private final Connector mConnector;
    private final Executor mExecutor;
    private MessageReceiverWithResponder mIncomingMessageReceiver;
    private long mNextRequestId;
    private Map<Long, MessageReceiver> mResponders;

    private class HandleIncomingMessageThunk implements MessageReceiver {
        private HandleIncomingMessageThunk() {
        }

        @Override // org.chromium.mojo.bindings.MessageReceiver
        public boolean accept(Message message) {
            return RouterImpl.this.handleIncomingMessage(message);
        }

        @Override // org.chromium.mojo.bindings.MessageReceiver, java.io.Closeable, java.lang.AutoCloseable, org.chromium.mojo.bindings.HandleOwner
        public void close() {
            RouterImpl.this.handleConnectorClose();
        }
    }

    class ResponderThunk implements MessageReceiver {
        private boolean mAcceptWasInvoked = false;

        ResponderThunk() {
        }

        @Override // org.chromium.mojo.bindings.MessageReceiver
        public boolean accept(Message message) {
            this.mAcceptWasInvoked = true;
            return RouterImpl.this.accept(message);
        }

        @Override // org.chromium.mojo.bindings.MessageReceiver, java.io.Closeable, java.lang.AutoCloseable, org.chromium.mojo.bindings.HandleOwner
        public void close() {
            RouterImpl.this.close();
        }

        protected void finalize() throws Throwable {
            if (!this.mAcceptWasInvoked) {
                RouterImpl.this.closeOnHandleThread();
            }
            super.finalize();
        }
    }

    public RouterImpl(MessagePipeHandle messagePipeHandle) {
        this(messagePipeHandle, BindingsHelper.getDefaultAsyncWaiterForHandle(messagePipeHandle));
    }

    public RouterImpl(MessagePipeHandle messagePipeHandle, AsyncWaiter asyncWaiter) {
        this.mNextRequestId = 1L;
        this.mResponders = new HashMap();
        Connector connector = new Connector(messagePipeHandle, asyncWaiter);
        this.mConnector = connector;
        connector.setIncomingMessageReceiver(new HandleIncomingMessageThunk());
        Core core = messagePipeHandle.getCore();
        if (core != null) {
            this.mExecutor = ExecutorFactory.getExecutorForCurrentThread(core);
        } else {
            this.mExecutor = null;
        }
    }

    @Override // org.chromium.mojo.bindings.Router
    public void start() {
        this.mConnector.start();
    }

    @Override // org.chromium.mojo.bindings.Router
    public void setIncomingMessageReceiver(MessageReceiverWithResponder messageReceiverWithResponder) {
        this.mIncomingMessageReceiver = messageReceiverWithResponder;
    }

    @Override // org.chromium.mojo.bindings.MessageReceiver
    public boolean accept(Message message) {
        return this.mConnector.accept(message);
    }

    @Override // org.chromium.mojo.bindings.MessageReceiverWithResponder
    public boolean acceptWithResponder(Message message, MessageReceiver messageReceiver) {
        ServiceMessage serviceMessageAsServiceMessage = message.asServiceMessage();
        long j = this.mNextRequestId;
        long j2 = j + 1;
        this.mNextRequestId = j2;
        if (j == 0) {
            this.mNextRequestId = 1 + j2;
            j = j2;
        }
        if (this.mResponders.containsKey(Long.valueOf(j))) {
            throw new IllegalStateException("Unable to find a new request identifier.");
        }
        serviceMessageAsServiceMessage.setRequestId(j);
        if (!this.mConnector.accept(serviceMessageAsServiceMessage)) {
            return false;
        }
        this.mResponders.put(Long.valueOf(j), messageReceiver);
        return true;
    }

    @Override // org.chromium.mojo.bindings.HandleOwner
    public MessagePipeHandle passHandle() {
        return this.mConnector.passHandle();
    }

    @Override // org.chromium.mojo.bindings.MessageReceiver, java.io.Closeable, java.lang.AutoCloseable, org.chromium.mojo.bindings.HandleOwner
    public void close() {
        this.mConnector.close();
    }

    @Override // org.chromium.mojo.bindings.Router
    public void setErrorHandler(ConnectionErrorHandler connectionErrorHandler) {
        this.mConnector.setErrorHandler(connectionErrorHandler);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public boolean handleIncomingMessage(Message message) {
        MessageHeader header = message.asServiceMessage().getHeader();
        if (header.hasFlag(1)) {
            MessageReceiverWithResponder messageReceiverWithResponder = this.mIncomingMessageReceiver;
            if (messageReceiverWithResponder != null) {
                return messageReceiverWithResponder.acceptWithResponder(message, new ResponderThunk());
            }
            close();
            return false;
        }
        if (header.hasFlag(2)) {
            long requestId = header.getRequestId();
            MessageReceiver messageReceiver = this.mResponders.get(Long.valueOf(requestId));
            if (messageReceiver == null) {
                return false;
            }
            this.mResponders.remove(Long.valueOf(requestId));
            return messageReceiver.accept(message);
        }
        MessageReceiverWithResponder messageReceiverWithResponder2 = this.mIncomingMessageReceiver;
        if (messageReceiverWithResponder2 != null) {
            return messageReceiverWithResponder2.accept(message);
        }
        return false;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void handleConnectorClose() {
        MessageReceiverWithResponder messageReceiverWithResponder = this.mIncomingMessageReceiver;
        if (messageReceiverWithResponder != null) {
            messageReceiverWithResponder.close();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void closeOnHandleThread() {
        Executor executor = this.mExecutor;
        if (executor != null) {
            executor.execute(new Runnable() { // from class: org.chromium.mojo.bindings.RouterImpl.1
                @Override // java.lang.Runnable
                public void run() {
                    RouterImpl.this.close();
                }
            });
        }
    }
}
