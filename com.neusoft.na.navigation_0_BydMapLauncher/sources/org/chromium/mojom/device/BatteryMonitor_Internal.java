package org.chromium.mojom.device;

import org.chromium.mojo.bindings.BindingsHelper;
import org.chromium.mojo.bindings.DataHeader;
import org.chromium.mojo.bindings.Decoder;
import org.chromium.mojo.bindings.DeserializationException;
import org.chromium.mojo.bindings.Encoder;
import org.chromium.mojo.bindings.Interface;
import org.chromium.mojo.bindings.InterfaceControlMessagesHelper;
import org.chromium.mojo.bindings.Message;
import org.chromium.mojo.bindings.MessageHeader;
import org.chromium.mojo.bindings.MessageReceiver;
import org.chromium.mojo.bindings.MessageReceiverWithResponder;
import org.chromium.mojo.bindings.ServiceMessage;
import org.chromium.mojo.bindings.SideEffectFreeCloseable;
import org.chromium.mojo.bindings.Struct;
import org.chromium.mojo.system.Core;
import org.chromium.mojom.device.BatteryMonitor;

/* JADX INFO: loaded from: classes3.dex */
class BatteryMonitor_Internal {
    public static final Interface.Manager<BatteryMonitor, BatteryMonitor.Proxy> MANAGER = new Interface.Manager<BatteryMonitor, BatteryMonitor.Proxy>() { // from class: org.chromium.mojom.device.BatteryMonitor_Internal.1
        @Override // org.chromium.mojo.bindings.Interface.Manager
        public String getName() {
            return "device::BatteryMonitor";
        }

        @Override // org.chromium.mojo.bindings.Interface.Manager
        public int getVersion() {
            return 0;
        }

        @Override // org.chromium.mojo.bindings.Interface.Manager
        public Proxy buildProxy(Core core, MessageReceiverWithResponder messageReceiverWithResponder) {
            return new Proxy(core, messageReceiverWithResponder);
        }

        @Override // org.chromium.mojo.bindings.Interface.Manager
        public Stub buildStub(Core core, BatteryMonitor batteryMonitor) {
            return new Stub(core, batteryMonitor);
        }

        @Override // org.chromium.mojo.bindings.Interface.Manager
        public BatteryMonitor[] buildArray(int i) {
            return new BatteryMonitor[i];
        }
    };
    private static final int QUERY_NEXT_STATUS_ORDINAL = 0;

    BatteryMonitor_Internal() {
    }

    static final class Proxy extends Interface.AbstractProxy implements BatteryMonitor.Proxy {
        Proxy(Core core, MessageReceiverWithResponder messageReceiverWithResponder) {
            super(core, messageReceiverWithResponder);
        }

        @Override // org.chromium.mojom.device.BatteryMonitor
        public void queryNextStatus(BatteryMonitor.QueryNextStatusResponse queryNextStatusResponse) {
            getProxyHandler().getMessageReceiver().acceptWithResponder(new BatteryMonitorQueryNextStatusParams().serializeWithHeader(getProxyHandler().getCore(), new MessageHeader(0, 1, 0L)), new BatteryMonitorQueryNextStatusResponseParamsForwardToCallback(queryNextStatusResponse));
        }
    }

    static final class Stub extends Interface.Stub<BatteryMonitor> {
        Stub(Core core, BatteryMonitor batteryMonitor) {
            super(core, batteryMonitor);
        }

        @Override // org.chromium.mojo.bindings.MessageReceiver
        public boolean accept(Message message) {
            try {
                ServiceMessage serviceMessageAsServiceMessage = message.asServiceMessage();
                MessageHeader header = serviceMessageAsServiceMessage.getHeader();
                if (header.validateHeader(0) && header.getType() == -2) {
                    return InterfaceControlMessagesHelper.handleRunOrClosePipe(BatteryMonitor_Internal.MANAGER, serviceMessageAsServiceMessage);
                }
                return false;
            } catch (DeserializationException e) {
                System.err.println(e.toString());
                return false;
            }
        }

        @Override // org.chromium.mojo.bindings.MessageReceiverWithResponder
        public boolean acceptWithResponder(Message message, MessageReceiver messageReceiver) {
            try {
                ServiceMessage serviceMessageAsServiceMessage = message.asServiceMessage();
                MessageHeader header = serviceMessageAsServiceMessage.getHeader();
                if (!header.validateHeader(1)) {
                    return false;
                }
                int type = header.getType();
                if (type == -1) {
                    return InterfaceControlMessagesHelper.handleRun(getCore(), BatteryMonitor_Internal.MANAGER, serviceMessageAsServiceMessage, messageReceiver);
                }
                if (type != 0) {
                    return false;
                }
                BatteryMonitorQueryNextStatusParams.deserialize(serviceMessageAsServiceMessage.getPayload());
                getImpl().queryNextStatus(new BatteryMonitorQueryNextStatusResponseParamsProxyToResponder(getCore(), messageReceiver, header.getRequestId()));
                return true;
            } catch (DeserializationException e) {
                System.err.println(e.toString());
                return false;
            }
        }
    }

    static final class BatteryMonitorQueryNextStatusParams extends Struct {
        private static final DataHeader DEFAULT_STRUCT_INFO;
        private static final int STRUCT_SIZE = 8;
        private static final DataHeader[] VERSION_ARRAY;

        static {
            DataHeader dataHeader = new DataHeader(8, 0);
            VERSION_ARRAY = new DataHeader[]{dataHeader};
            DEFAULT_STRUCT_INFO = dataHeader;
        }

        private BatteryMonitorQueryNextStatusParams(int i) {
            super(8, i);
        }

        public BatteryMonitorQueryNextStatusParams() {
            this(0);
        }

        public static BatteryMonitorQueryNextStatusParams deserialize(Message message) {
            return decode(new Decoder(message));
        }

        public static BatteryMonitorQueryNextStatusParams decode(Decoder decoder) {
            if (decoder == null) {
                return null;
            }
            return new BatteryMonitorQueryNextStatusParams(decoder.readAndValidateDataHeader(VERSION_ARRAY).elementsOrVersion);
        }

        @Override // org.chromium.mojo.bindings.Struct
        protected final void encode(Encoder encoder) {
            encoder.getEncoderAtDataOffset(DEFAULT_STRUCT_INFO);
        }

        public boolean equals(Object obj) {
            if (obj == this) {
                return true;
            }
            return obj != null && getClass() == obj.getClass();
        }

        public int hashCode() {
            return getClass().hashCode() + 31;
        }
    }

    static final class BatteryMonitorQueryNextStatusResponseParams extends Struct {
        private static final DataHeader DEFAULT_STRUCT_INFO;
        private static final int STRUCT_SIZE = 16;
        private static final DataHeader[] VERSION_ARRAY;
        public BatteryStatus status;

        static {
            DataHeader dataHeader = new DataHeader(16, 0);
            VERSION_ARRAY = new DataHeader[]{dataHeader};
            DEFAULT_STRUCT_INFO = dataHeader;
        }

        private BatteryMonitorQueryNextStatusResponseParams(int i) {
            super(16, i);
        }

        public BatteryMonitorQueryNextStatusResponseParams() {
            this(0);
        }

        public static BatteryMonitorQueryNextStatusResponseParams deserialize(Message message) {
            return decode(new Decoder(message));
        }

        public static BatteryMonitorQueryNextStatusResponseParams decode(Decoder decoder) {
            if (decoder == null) {
                return null;
            }
            DataHeader andValidateDataHeader = decoder.readAndValidateDataHeader(VERSION_ARRAY);
            BatteryMonitorQueryNextStatusResponseParams batteryMonitorQueryNextStatusResponseParams = new BatteryMonitorQueryNextStatusResponseParams(andValidateDataHeader.elementsOrVersion);
            if (andValidateDataHeader.elementsOrVersion >= 0) {
                batteryMonitorQueryNextStatusResponseParams.status = BatteryStatus.decode(decoder.readPointer(8, false));
            }
            return batteryMonitorQueryNextStatusResponseParams;
        }

        @Override // org.chromium.mojo.bindings.Struct
        protected final void encode(Encoder encoder) {
            encoder.getEncoderAtDataOffset(DEFAULT_STRUCT_INFO).encode((Struct) this.status, 8, false);
        }

        public boolean equals(Object obj) {
            if (obj == this) {
                return true;
            }
            return obj != null && getClass() == obj.getClass() && BindingsHelper.equals(this.status, ((BatteryMonitorQueryNextStatusResponseParams) obj).status);
        }

        public int hashCode() {
            return ((getClass().hashCode() + 31) * 31) + BindingsHelper.hashCode(this.status);
        }
    }

    static class BatteryMonitorQueryNextStatusResponseParamsForwardToCallback extends SideEffectFreeCloseable implements MessageReceiver {
        private final BatteryMonitor.QueryNextStatusResponse mCallback;

        BatteryMonitorQueryNextStatusResponseParamsForwardToCallback(BatteryMonitor.QueryNextStatusResponse queryNextStatusResponse) {
            this.mCallback = queryNextStatusResponse;
        }

        @Override // org.chromium.mojo.bindings.MessageReceiver
        public boolean accept(Message message) {
            try {
                ServiceMessage serviceMessageAsServiceMessage = message.asServiceMessage();
                if (!serviceMessageAsServiceMessage.getHeader().validateHeader(0, 2)) {
                    return false;
                }
                this.mCallback.call(BatteryMonitorQueryNextStatusResponseParams.deserialize(serviceMessageAsServiceMessage.getPayload()).status);
                return true;
            } catch (DeserializationException unused) {
                return false;
            }
        }
    }

    static class BatteryMonitorQueryNextStatusResponseParamsProxyToResponder implements BatteryMonitor.QueryNextStatusResponse {
        private final Core mCore;
        private final MessageReceiver mMessageReceiver;
        private final long mRequestId;

        BatteryMonitorQueryNextStatusResponseParamsProxyToResponder(Core core, MessageReceiver messageReceiver, long j) {
            this.mCore = core;
            this.mMessageReceiver = messageReceiver;
            this.mRequestId = j;
        }

        @Override // org.chromium.mojo.bindings.Callbacks.Callback1
        public void call(BatteryStatus batteryStatus) {
            BatteryMonitorQueryNextStatusResponseParams batteryMonitorQueryNextStatusResponseParams = new BatteryMonitorQueryNextStatusResponseParams();
            batteryMonitorQueryNextStatusResponseParams.status = batteryStatus;
            this.mMessageReceiver.accept(batteryMonitorQueryNextStatusResponseParams.serializeWithHeader(this.mCore, new MessageHeader(0, 2, this.mRequestId)));
        }
    }
}
