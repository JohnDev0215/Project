package org.chromium.mojo.bindings;

import java.io.Closeable;
import org.chromium.mojo.bindings.Callbacks;
import org.chromium.mojo.system.Core;
import org.chromium.mojo.system.MessagePipeHandle;
import org.chromium.mojo.system.MojoException;
import org.chromium.mojo.system.Pair;

/* JADX INFO: loaded from: classes3.dex */
public interface Interface extends ConnectionErrorHandler, Closeable {

    public interface Proxy extends Interface {

        public interface Handler extends Closeable {
            int getVersion();

            MessagePipeHandle passHandle();

            void queryVersion(Callbacks.Callback1<Integer> callback1);

            void requireVersion(int i);

            void setErrorHandler(ConnectionErrorHandler connectionErrorHandler);
        }

        Handler getProxyHandler();
    }

    void close();

    public static abstract class AbstractProxy implements Proxy {
        private final HandlerImpl mHandler;

        /* JADX INFO: Access modifiers changed from: protected */
        public static class HandlerImpl implements Proxy.Handler, ConnectionErrorHandler {
            private final Core mCore;
            private final MessageReceiverWithResponder mMessageReceiver;
            private ConnectionErrorHandler mErrorHandler = null;
            private int mVersion = 0;

            protected HandlerImpl(Core core, MessageReceiverWithResponder messageReceiverWithResponder) {
                this.mCore = core;
                this.mMessageReceiver = messageReceiverWithResponder;
            }

            void setVersion(int i) {
                this.mVersion = i;
            }

            public MessageReceiverWithResponder getMessageReceiver() {
                return this.mMessageReceiver;
            }

            public Core getCore() {
                return this.mCore;
            }

            @Override // org.chromium.mojo.bindings.Interface.Proxy.Handler
            public void setErrorHandler(ConnectionErrorHandler connectionErrorHandler) {
                this.mErrorHandler = connectionErrorHandler;
            }

            @Override // org.chromium.mojo.bindings.ConnectionErrorHandler
            public void onConnectionError(MojoException mojoException) {
                ConnectionErrorHandler connectionErrorHandler = this.mErrorHandler;
                if (connectionErrorHandler != null) {
                    connectionErrorHandler.onConnectionError(mojoException);
                }
            }

            @Override // java.io.Closeable, java.lang.AutoCloseable
            public void close() {
                this.mMessageReceiver.close();
            }

            @Override // org.chromium.mojo.bindings.Interface.Proxy.Handler
            public MessagePipeHandle passHandle() {
                return (MessagePipeHandle) ((HandleOwner) this.mMessageReceiver).passHandle();
            }

            @Override // org.chromium.mojo.bindings.Interface.Proxy.Handler
            public int getVersion() {
                return this.mVersion;
            }

            @Override // org.chromium.mojo.bindings.Interface.Proxy.Handler
            public void queryVersion(final Callbacks.Callback1<Integer> callback1) {
                RunMessageParams runMessageParams = new RunMessageParams();
                runMessageParams.reserved0 = 16;
                runMessageParams.reserved1 = 0;
                runMessageParams.queryVersion = new QueryVersion();
                InterfaceControlMessagesHelper.sendRunMessage(getCore(), this.mMessageReceiver, runMessageParams, new Callbacks.Callback1<RunResponseMessageParams>() { // from class: org.chromium.mojo.bindings.Interface.AbstractProxy.HandlerImpl.1
                    @Override // org.chromium.mojo.bindings.Callbacks.Callback1
                    public void call(RunResponseMessageParams runResponseMessageParams) {
                        HandlerImpl.this.mVersion = runResponseMessageParams.queryVersionResult.version;
                        callback1.call(Integer.valueOf(HandlerImpl.this.mVersion));
                    }
                });
            }

            @Override // org.chromium.mojo.bindings.Interface.Proxy.Handler
            public void requireVersion(int i) {
                if (this.mVersion >= i) {
                    return;
                }
                this.mVersion = i;
                RunOrClosePipeMessageParams runOrClosePipeMessageParams = new RunOrClosePipeMessageParams();
                runOrClosePipeMessageParams.reserved0 = 16;
                runOrClosePipeMessageParams.reserved1 = 0;
                runOrClosePipeMessageParams.requireVersion = new RequireVersion();
                runOrClosePipeMessageParams.requireVersion.version = i;
                InterfaceControlMessagesHelper.sendRunOrClosePipeMessage(getCore(), this.mMessageReceiver, runOrClosePipeMessageParams);
            }
        }

        protected AbstractProxy(Core core, MessageReceiverWithResponder messageReceiverWithResponder) {
            this.mHandler = new HandlerImpl(core, messageReceiverWithResponder);
        }

        @Override // org.chromium.mojo.bindings.Interface, java.io.Closeable, java.lang.AutoCloseable
        public void close() {
            this.mHandler.close();
        }

        @Override // org.chromium.mojo.bindings.Interface.Proxy
        public HandlerImpl getProxyHandler() {
            return this.mHandler;
        }

        @Override // org.chromium.mojo.bindings.ConnectionErrorHandler
        public void onConnectionError(MojoException mojoException) {
            this.mHandler.onConnectionError(mojoException);
        }
    }

    public static abstract class Stub<I extends Interface> implements MessageReceiverWithResponder {
        private final Core mCore;
        private final I mImpl;

        public Stub(Core core, I i) {
            this.mCore = core;
            this.mImpl = i;
        }

        protected Core getCore() {
            return this.mCore;
        }

        protected I getImpl() {
            return this.mImpl;
        }

        @Override // org.chromium.mojo.bindings.MessageReceiver, java.io.Closeable, java.lang.AutoCloseable, org.chromium.mojo.bindings.HandleOwner
        public void close() {
            this.mImpl.close();
        }
    }

    public static abstract class Manager<I extends Interface, P extends Proxy> {
        protected abstract I[] buildArray(int i);

        protected abstract P buildProxy(Core core, MessageReceiverWithResponder messageReceiverWithResponder);

        protected abstract Stub<I> buildStub(Core core, I i);

        public abstract String getName();

        public abstract int getVersion();

        public void bind(I i, MessagePipeHandle messagePipeHandle) {
            RouterImpl routerImpl = new RouterImpl(messagePipeHandle);
            bind(messagePipeHandle.getCore(), i, routerImpl);
            routerImpl.start();
        }

        public final void bind(I i, InterfaceRequest<I> interfaceRequest) {
            bind(i, interfaceRequest.passHandle());
        }

        public final P attachProxy(MessagePipeHandle messagePipeHandle, int i) {
            RouterImpl routerImpl = new RouterImpl(messagePipeHandle);
            P p = (P) attachProxy(messagePipeHandle.getCore(), routerImpl);
            DelegatingConnectionErrorHandler delegatingConnectionErrorHandler = new DelegatingConnectionErrorHandler();
            delegatingConnectionErrorHandler.addConnectionErrorHandler(p);
            routerImpl.setErrorHandler(delegatingConnectionErrorHandler);
            routerImpl.start();
            ((AbstractProxy.HandlerImpl) p.getProxyHandler()).setVersion(i);
            return p;
        }

        public final Pair<P, InterfaceRequest<I>> getInterfaceRequest(Core core) {
            Pair<MessagePipeHandle, MessagePipeHandle> pairCreateMessagePipe = core.createMessagePipe(null);
            return Pair.create(attachProxy(pairCreateMessagePipe.first, 0), new InterfaceRequest(pairCreateMessagePipe.second));
        }

        public final InterfaceRequest<I> asInterfaceRequest(MessagePipeHandle messagePipeHandle) {
            return new InterfaceRequest<>(messagePipeHandle);
        }

        final void bind(Core core, I i, Router router) {
            router.setErrorHandler(i);
            router.setIncomingMessageReceiver(buildStub(core, i));
        }

        final P attachProxy(Core core, Router router) {
            return (P) buildProxy(core, new AutoCloseableRouter(core, router));
        }
    }
}
