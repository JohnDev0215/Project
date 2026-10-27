package org.chromium.mojo.bindings;

import org.chromium.mojo.bindings.Callbacks;
import org.chromium.mojo.bindings.Interface;
import org.chromium.mojo.system.Core;

/* JADX INFO: loaded from: classes3.dex */
public class InterfaceControlMessagesHelper {

    private static class RunResponseForwardToCallback extends SideEffectFreeCloseable implements MessageReceiver {
        private final Callbacks.Callback1<RunResponseMessageParams> mCallback;

        RunResponseForwardToCallback(Callbacks.Callback1<RunResponseMessageParams> callback1) {
            this.mCallback = callback1;
        }

        @Override // org.chromium.mojo.bindings.MessageReceiver
        public boolean accept(Message message) {
            this.mCallback.call(RunResponseMessageParams.deserialize(message.asServiceMessage().getPayload()));
            return true;
        }
    }

    public static void sendRunMessage(Core core, MessageReceiverWithResponder messageReceiverWithResponder, RunMessageParams runMessageParams, Callbacks.Callback1<RunResponseMessageParams> callback1) {
        messageReceiverWithResponder.acceptWithResponder(runMessageParams.serializeWithHeader(core, new MessageHeader(-1, 1, 0L)), new RunResponseForwardToCallback(callback1));
    }

    public static void sendRunOrClosePipeMessage(Core core, MessageReceiverWithResponder messageReceiverWithResponder, RunOrClosePipeMessageParams runOrClosePipeMessageParams) {
        messageReceiverWithResponder.accept(runOrClosePipeMessageParams.serializeWithHeader(core, new MessageHeader(-2)));
    }

    public static <I extends Interface, P extends Interface.Proxy> boolean handleRun(Core core, Interface.Manager<I, P> manager, ServiceMessage serviceMessage, MessageReceiver messageReceiver) {
        RunResponseMessageParams runResponseMessageParams = new RunResponseMessageParams();
        runResponseMessageParams.reserved0 = 16;
        runResponseMessageParams.reserved1 = 0;
        runResponseMessageParams.queryVersionResult = new QueryVersionResult();
        runResponseMessageParams.queryVersionResult.version = manager.getVersion();
        return messageReceiver.accept(runResponseMessageParams.serializeWithHeader(core, new MessageHeader(-1, 2, serviceMessage.getHeader().getRequestId())));
    }

    public static <I extends Interface, P extends Interface.Proxy> boolean handleRunOrClosePipe(Interface.Manager<I, P> manager, ServiceMessage serviceMessage) {
        return RunOrClosePipeMessageParams.deserialize(serviceMessage.getPayload()).requireVersion.version <= manager.getVersion();
    }
}
