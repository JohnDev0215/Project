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
import org.chromium.mojom.device.VibrationManager;

/* JADX INFO: loaded from: classes3.dex */
class VibrationManager_Internal {
    private static final int CANCEL_ORDINAL = 1;
    public static final Interface.Manager<VibrationManager, VibrationManager.Proxy> MANAGER = new Interface.Manager<VibrationManager, VibrationManager.Proxy>() { // from class: org.chromium.mojom.device.VibrationManager_Internal.1
        @Override // org.chromium.mojo.bindings.Interface.Manager
        public String getName() {
            return "device::VibrationManager";
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
        public Stub buildStub(Core core, VibrationManager vibrationManager) {
            return new Stub(core, vibrationManager);
        }

        @Override // org.chromium.mojo.bindings.Interface.Manager
        public VibrationManager[] buildArray(int i) {
            return new VibrationManager[i];
        }
    };
    private static final int VIBRATE_ORDINAL = 0;

    VibrationManager_Internal() {
    }

    static final class Proxy extends Interface.AbstractProxy implements VibrationManager.Proxy {
        Proxy(Core core, MessageReceiverWithResponder messageReceiverWithResponder) {
            super(core, messageReceiverWithResponder);
        }

        @Override // org.chromium.mojom.device.VibrationManager
        public void vibrate(long j, VibrationManager.VibrateResponse vibrateResponse) {
            VibrationManagerVibrateParams vibrationManagerVibrateParams = new VibrationManagerVibrateParams();
            vibrationManagerVibrateParams.milliseconds = j;
            getProxyHandler().getMessageReceiver().acceptWithResponder(vibrationManagerVibrateParams.serializeWithHeader(getProxyHandler().getCore(), new MessageHeader(0, 1, 0L)), new VibrationManagerVibrateResponseParamsForwardToCallback(vibrateResponse));
        }

        @Override // org.chromium.mojom.device.VibrationManager
        public void cancel(VibrationManager.CancelResponse cancelResponse) {
            getProxyHandler().getMessageReceiver().acceptWithResponder(new VibrationManagerCancelParams().serializeWithHeader(getProxyHandler().getCore(), new MessageHeader(1, 1, 0L)), new VibrationManagerCancelResponseParamsForwardToCallback(cancelResponse));
        }
    }

    static final class Stub extends Interface.Stub<VibrationManager> {
        Stub(Core core, VibrationManager vibrationManager) {
            super(core, vibrationManager);
        }

        @Override // org.chromium.mojo.bindings.MessageReceiver
        public boolean accept(Message message) {
            try {
                ServiceMessage serviceMessageAsServiceMessage = message.asServiceMessage();
                MessageHeader header = serviceMessageAsServiceMessage.getHeader();
                if (header.validateHeader(0) && header.getType() == -2) {
                    return InterfaceControlMessagesHelper.handleRunOrClosePipe(VibrationManager_Internal.MANAGER, serviceMessageAsServiceMessage);
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
                    return InterfaceControlMessagesHelper.handleRun(getCore(), VibrationManager_Internal.MANAGER, serviceMessageAsServiceMessage, messageReceiver);
                }
                if (type == 0) {
                    getImpl().vibrate(VibrationManagerVibrateParams.deserialize(serviceMessageAsServiceMessage.getPayload()).milliseconds, new VibrationManagerVibrateResponseParamsProxyToResponder(getCore(), messageReceiver, header.getRequestId()));
                    return true;
                }
                if (type != 1) {
                    return false;
                }
                VibrationManagerCancelParams.deserialize(serviceMessageAsServiceMessage.getPayload());
                getImpl().cancel(new VibrationManagerCancelResponseParamsProxyToResponder(getCore(), messageReceiver, header.getRequestId()));
                return true;
            } catch (DeserializationException e) {
                System.err.println(e.toString());
                return false;
            }
        }
    }

    static final class VibrationManagerVibrateParams extends Struct {
        private static final DataHeader DEFAULT_STRUCT_INFO;
        private static final int STRUCT_SIZE = 16;
        private static final DataHeader[] VERSION_ARRAY;
        public long milliseconds;

        static {
            DataHeader dataHeader = new DataHeader(16, 0);
            VERSION_ARRAY = new DataHeader[]{dataHeader};
            DEFAULT_STRUCT_INFO = dataHeader;
        }

        private VibrationManagerVibrateParams(int i) {
            super(16, i);
        }

        public VibrationManagerVibrateParams() {
            this(0);
        }

        public static VibrationManagerVibrateParams deserialize(Message message) {
            return decode(new Decoder(message));
        }

        public static VibrationManagerVibrateParams decode(Decoder decoder) {
            if (decoder == null) {
                return null;
            }
            DataHeader andValidateDataHeader = decoder.readAndValidateDataHeader(VERSION_ARRAY);
            VibrationManagerVibrateParams vibrationManagerVibrateParams = new VibrationManagerVibrateParams(andValidateDataHeader.elementsOrVersion);
            if (andValidateDataHeader.elementsOrVersion >= 0) {
                vibrationManagerVibrateParams.milliseconds = decoder.readLong(8);
            }
            return vibrationManagerVibrateParams;
        }

        @Override // org.chromium.mojo.bindings.Struct
        protected final void encode(Encoder encoder) {
            encoder.getEncoderAtDataOffset(DEFAULT_STRUCT_INFO).encode(this.milliseconds, 8);
        }

        public boolean equals(Object obj) {
            if (obj == this) {
                return true;
            }
            return obj != null && getClass() == obj.getClass() && this.milliseconds == ((VibrationManagerVibrateParams) obj).milliseconds;
        }

        public int hashCode() {
            return ((getClass().hashCode() + 31) * 31) + BindingsHelper.hashCode(this.milliseconds);
        }
    }

    static final class VibrationManagerVibrateResponseParams extends Struct {
        private static final DataHeader DEFAULT_STRUCT_INFO;
        private static final int STRUCT_SIZE = 8;
        private static final DataHeader[] VERSION_ARRAY;

        static {
            DataHeader dataHeader = new DataHeader(8, 0);
            VERSION_ARRAY = new DataHeader[]{dataHeader};
            DEFAULT_STRUCT_INFO = dataHeader;
        }

        private VibrationManagerVibrateResponseParams(int i) {
            super(8, i);
        }

        public VibrationManagerVibrateResponseParams() {
            this(0);
        }

        public static VibrationManagerVibrateResponseParams deserialize(Message message) {
            return decode(new Decoder(message));
        }

        public static VibrationManagerVibrateResponseParams decode(Decoder decoder) {
            if (decoder == null) {
                return null;
            }
            return new VibrationManagerVibrateResponseParams(decoder.readAndValidateDataHeader(VERSION_ARRAY).elementsOrVersion);
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

    static class VibrationManagerVibrateResponseParamsForwardToCallback extends SideEffectFreeCloseable implements MessageReceiver {
        private final VibrationManager.VibrateResponse mCallback;

        VibrationManagerVibrateResponseParamsForwardToCallback(VibrationManager.VibrateResponse vibrateResponse) {
            this.mCallback = vibrateResponse;
        }

        @Override // org.chromium.mojo.bindings.MessageReceiver
        public boolean accept(Message message) {
            try {
                if (!message.asServiceMessage().getHeader().validateHeader(0, 2)) {
                    return false;
                }
                this.mCallback.call();
                return true;
            } catch (DeserializationException unused) {
                return false;
            }
        }
    }

    static class VibrationManagerVibrateResponseParamsProxyToResponder implements VibrationManager.VibrateResponse {
        private final Core mCore;
        private final MessageReceiver mMessageReceiver;
        private final long mRequestId;

        VibrationManagerVibrateResponseParamsProxyToResponder(Core core, MessageReceiver messageReceiver, long j) {
            this.mCore = core;
            this.mMessageReceiver = messageReceiver;
            this.mRequestId = j;
        }

        @Override // org.chromium.mojo.bindings.Callbacks.Callback0
        public void call() {
            this.mMessageReceiver.accept(new VibrationManagerVibrateResponseParams().serializeWithHeader(this.mCore, new MessageHeader(0, 2, this.mRequestId)));
        }
    }

    static final class VibrationManagerCancelParams extends Struct {
        private static final DataHeader DEFAULT_STRUCT_INFO;
        private static final int STRUCT_SIZE = 8;
        private static final DataHeader[] VERSION_ARRAY;

        static {
            DataHeader dataHeader = new DataHeader(8, 0);
            VERSION_ARRAY = new DataHeader[]{dataHeader};
            DEFAULT_STRUCT_INFO = dataHeader;
        }

        private VibrationManagerCancelParams(int i) {
            super(8, i);
        }

        public VibrationManagerCancelParams() {
            this(0);
        }

        public static VibrationManagerCancelParams deserialize(Message message) {
            return decode(new Decoder(message));
        }

        public static VibrationManagerCancelParams decode(Decoder decoder) {
            if (decoder == null) {
                return null;
            }
            return new VibrationManagerCancelParams(decoder.readAndValidateDataHeader(VERSION_ARRAY).elementsOrVersion);
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

    static final class VibrationManagerCancelResponseParams extends Struct {
        private static final DataHeader DEFAULT_STRUCT_INFO;
        private static final int STRUCT_SIZE = 8;
        private static final DataHeader[] VERSION_ARRAY;

        static {
            DataHeader dataHeader = new DataHeader(8, 0);
            VERSION_ARRAY = new DataHeader[]{dataHeader};
            DEFAULT_STRUCT_INFO = dataHeader;
        }

        private VibrationManagerCancelResponseParams(int i) {
            super(8, i);
        }

        public VibrationManagerCancelResponseParams() {
            this(0);
        }

        public static VibrationManagerCancelResponseParams deserialize(Message message) {
            return decode(new Decoder(message));
        }

        public static VibrationManagerCancelResponseParams decode(Decoder decoder) {
            if (decoder == null) {
                return null;
            }
            return new VibrationManagerCancelResponseParams(decoder.readAndValidateDataHeader(VERSION_ARRAY).elementsOrVersion);
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

    static class VibrationManagerCancelResponseParamsForwardToCallback extends SideEffectFreeCloseable implements MessageReceiver {
        private final VibrationManager.CancelResponse mCallback;

        VibrationManagerCancelResponseParamsForwardToCallback(VibrationManager.CancelResponse cancelResponse) {
            this.mCallback = cancelResponse;
        }

        @Override // org.chromium.mojo.bindings.MessageReceiver
        public boolean accept(Message message) {
            try {
                if (!message.asServiceMessage().getHeader().validateHeader(1, 2)) {
                    return false;
                }
                this.mCallback.call();
                return true;
            } catch (DeserializationException unused) {
                return false;
            }
        }
    }

    static class VibrationManagerCancelResponseParamsProxyToResponder implements VibrationManager.CancelResponse {
        private final Core mCore;
        private final MessageReceiver mMessageReceiver;
        private final long mRequestId;

        VibrationManagerCancelResponseParamsProxyToResponder(Core core, MessageReceiver messageReceiver, long j) {
            this.mCore = core;
            this.mMessageReceiver = messageReceiver;
            this.mRequestId = j;
        }

        @Override // org.chromium.mojo.bindings.Callbacks.Callback0
        public void call() {
            this.mMessageReceiver.accept(new VibrationManagerCancelResponseParams().serializeWithHeader(this.mCore, new MessageHeader(1, 2, this.mRequestId)));
        }
    }
}
