package com.google.android.projection.protocol;

import android.util.Log;
import com.google.android.projection.proto.Common;
import com.google.android.projection.proto.WifiDiscoveryProtos;
import com.google.android.projection.proto.WifiProjectionProtocol;
import com.google.protobuf.InvalidProtocolBufferException;
import com.google.protobuf.MessageLite;
import com.google.protobuf.Parser;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.InetSocketAddress;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

/* JADX INFO: loaded from: classes.dex */
public class WirelessProjectionProtocol implements NativeObject {
    private static final String TAG = "AHU.WPP";
    private final boolean autoLaunch;
    private final WifiProjectionProtocolCallback callback;
    private final byte[] headUnitInfo;
    private final WifiProjectionProtocolLoggingCallback loggingCallback;
    private long nativeInstance;
    private final int[] supportedWifiFrequenciesMhz;
    private final int wifiChannelType;
    private final WifiProjectionInfoProviderInternal wifiProjectionInfoProvider;
    private final String wppSocketIp;
    private final int wppSocketPort;

    public interface WifiProjectionInfoProvider {
        String getBssid();

        String getPassword();

        InetSocketAddress getProjectionPortInfo();

        String getSsid();
    }

    public interface WifiProjectionProtocolCallback {
        WifiDiscoveryProtos.MessageStatus canStartProjection(ClientInfo clientInfo);

        void onClientLost(ClientInfo clientInfo);

        void onReadyForProjection(ClientInfo clientInfo);

        void onWifiConnectStatus(ClientInfo clientInfo, WifiDiscoveryProtos.MessageStatus messageStatus, String str);

        void onWifiVersionResponseFailure(ClientInfo clientInfo, WifiDiscoveryProtos.MessageStatus messageStatus);
    }

    public interface WifiProjectionProtocolLoggingCallback {
        void onMessage(String str, WifiProjectionProtocol.MessageDirection messageDirection, WifiDiscoveryProtos.WifiMessageType wifiMessageType, MessageLite messageLite);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public native WifiProjectionProtocol.ProjectionStartResult get(StartResultFuture startResultFuture, long j, TimeUnit timeUnit) throws TimeoutException;

    private native void onConnect(String str, int i, InputStream inputStream, OutputStream outputStream);

    private native void onDisconnect(String str);

    private native Future<WifiProjectionProtocol.ProjectionStartResult> startProjection(String str, int i);

    public WirelessProjectionProtocol(WifiProjectionProtocolCallback callback, WifiProjectionInfoProvider wifiProjectionInfoProvider, WifiDiscoveryProtos.WifiChannelType wifiChannelType, int[] supportedWifiFrequenciesMhz, Common.HeadUnitInfo headUnitInfo, InetSocketAddress wppSocketAddress, boolean autoLaunch, WifiProjectionProtocolLoggingCallback loggingCallback) {
        this.callback = callback;
        this.wifiProjectionInfoProvider = new WifiProjectionInfoProviderInternal(wifiProjectionInfoProvider);
        this.wifiChannelType = wifiChannelType.getNumber();
        this.supportedWifiFrequenciesMhz = (int[]) supportedWifiFrequenciesMhz.clone();
        this.headUnitInfo = headUnitInfo.toByteArray();
        this.wppSocketIp = wppSocketAddress.getAddress().getHostAddress();
        this.wppSocketPort = wppSocketAddress.getPort();
        this.autoLaunch = autoLaunch;
        this.loggingCallback = loggingCallback;
    }

    @Override // com.google.android.projection.protocol.NativeObject
    public long getNativeInstance() {
        return this.nativeInstance;
    }

    @Override // com.google.android.projection.protocol.NativeObject
    public void destroy() {
    }

    public void onNewConnection(String id, WifiProjectionProtocol.TransportType transportType, InputStream inputStream, OutputStream outputStream) throws IOException {
        onConnect(id, transportType.getNumber(), inputStream, outputStream);
    }

    public void clientDisconnected(String id) {
        onDisconnect(id);
    }

    public Future<WifiProjectionProtocol.ProjectionStartResult> startProjection(String mobileDeviceId, WifiDiscoveryProtos.StartRequestReason startRequestReason) {
        return startProjection(mobileDeviceId, startRequestReason.getNumber());
    }

    private void logDiscoveryMessage(String ephemeralDeviceId, int direction, int messageType, byte[] message) {
        if (this.loggingCallback != null) {
            WifiDiscoveryProtos.WifiMessageType messageTypeEnum = WifiDiscoveryProtos.WifiMessageType.forNumber(messageType);
            if (messageTypeEnum == null) {
                Log.e("AHU.WPP", "Unrecognized Wi-Fi message type.");
                return;
            }
            Parser<? extends MessageLite> parser = null;
            switch (AnonymousClass1.$SwitchMap$com$google$android$projection$proto$WifiDiscoveryProtos$WifiMessageType[messageTypeEnum.ordinal()]) {
                case 1:
                    parser = WifiDiscoveryProtos.WifiStartRequest.parser();
                    break;
                case 2:
                    parser = WifiDiscoveryProtos.WifiInfoRequest.parser();
                    break;
                case 3:
                    parser = WifiDiscoveryProtos.WifiInfoResponse.parser();
                    break;
                case 4:
                    parser = WifiDiscoveryProtos.WifiVersionRequest.parser();
                    break;
                case 5:
                    parser = WifiDiscoveryProtos.WifiVersionResponse.parser();
                    break;
                case 6:
                    parser = WifiDiscoveryProtos.WifiConnectStatus.parser();
                    break;
                case 7:
                    parser = WifiDiscoveryProtos.WifiStartResponse.parser();
                    break;
                case 8:
                    parser = WifiDiscoveryProtos.WifiPingRequest.parser();
                    break;
                case 9:
                    parser = WifiDiscoveryProtos.WifiPingResponse.parser();
                    break;
            }
            if (parser == null) {
                Log.e("AHU.WPP", "Unrecognized Wi-Fi message type.");
                return;
            }
            try {
                WifiProjectionProtocol.MessageDirection directionEnum = WifiProjectionProtocol.MessageDirection.forNumber(direction);
                this.loggingCallback.onMessage(ephemeralDeviceId, directionEnum == null ? WifiProjectionProtocol.MessageDirection.MESSAGE_DIRECTION_UNKNOWN : directionEnum, messageTypeEnum, (MessageLite) parser.parseFrom(message));
            } catch (InvalidProtocolBufferException e) {
                Log.e("AHU.WPP", "Failed to parse protocol buffer", e);
            }
        }
    }

    /* JADX INFO: renamed from: com.google.android.projection.protocol.WirelessProjectionProtocol$1, reason: invalid class name */
    static /* synthetic */ class AnonymousClass1 {
        static final /* synthetic */ int[] $SwitchMap$com$google$android$projection$proto$WifiDiscoveryProtos$WifiMessageType;

        static {
            int[] iArr = new int[WifiDiscoveryProtos.WifiMessageType.values().length];
            $SwitchMap$com$google$android$projection$proto$WifiDiscoveryProtos$WifiMessageType = iArr;
            try {
                iArr[WifiDiscoveryProtos.WifiMessageType.MESSAGE_WIFI_REQUEST_START_BT.ordinal()] = 1;
            } catch (NoSuchFieldError e) {
            }
            try {
                $SwitchMap$com$google$android$projection$proto$WifiDiscoveryProtos$WifiMessageType[WifiDiscoveryProtos.WifiMessageType.MESSAGE_WIFI_REQUEST_INFO_BT.ordinal()] = 2;
            } catch (NoSuchFieldError e2) {
            }
            try {
                $SwitchMap$com$google$android$projection$proto$WifiDiscoveryProtos$WifiMessageType[WifiDiscoveryProtos.WifiMessageType.MESSAGE_WIFI_RESPONSE_INFO_BT.ordinal()] = 3;
            } catch (NoSuchFieldError e3) {
            }
            try {
                $SwitchMap$com$google$android$projection$proto$WifiDiscoveryProtos$WifiMessageType[WifiDiscoveryProtos.WifiMessageType.MESSAGE_WIFI_VERSION_REQUEST_BT.ordinal()] = 4;
            } catch (NoSuchFieldError e4) {
            }
            try {
                $SwitchMap$com$google$android$projection$proto$WifiDiscoveryProtos$WifiMessageType[WifiDiscoveryProtos.WifiMessageType.MESSAGE_WIFI_VERSION_RESPONSE_BT.ordinal()] = 5;
            } catch (NoSuchFieldError e5) {
            }
            try {
                $SwitchMap$com$google$android$projection$proto$WifiDiscoveryProtos$WifiMessageType[WifiDiscoveryProtos.WifiMessageType.MESSAGE_WIFI_CONNECT_STATUS_BT.ordinal()] = 6;
            } catch (NoSuchFieldError e6) {
            }
            try {
                $SwitchMap$com$google$android$projection$proto$WifiDiscoveryProtos$WifiMessageType[WifiDiscoveryProtos.WifiMessageType.MESSAGE_WIFI_RESPONSE_START_BT.ordinal()] = 7;
            } catch (NoSuchFieldError e7) {
            }
            try {
                $SwitchMap$com$google$android$projection$proto$WifiDiscoveryProtos$WifiMessageType[WifiDiscoveryProtos.WifiMessageType.MESSAGE_WIFI_PING_REQUEST_BT.ordinal()] = 8;
            } catch (NoSuchFieldError e8) {
            }
            try {
                $SwitchMap$com$google$android$projection$proto$WifiDiscoveryProtos$WifiMessageType[WifiDiscoveryProtos.WifiMessageType.MESSAGE_WIFI_PING_RESPONSE_BT.ordinal()] = 9;
            } catch (NoSuchFieldError e9) {
            }
        }
    }

    static {
        ReceiverLibJni.load();
    }

    public static class ClientInfo {
        private final String deviceSerial;
        private final String mobileDeviceId;
        private final Common.PhoneInfo phoneInfo;
        private final Map<WifiProjectionProtocol.TransportType, String> transportToEphemeralId;
        private final WifiProjectionProtocol.TransportType transportType;

        ClientInfo(String mobileDeviceId, WifiProjectionProtocol.TransportType transportType, Map<WifiProjectionProtocol.TransportType, String> transportToEphemeralId, String deviceSerial, Common.PhoneInfo phoneInfo) {
            this.mobileDeviceId = mobileDeviceId;
            this.transportType = transportType;
            this.transportToEphemeralId = Collections.unmodifiableMap(new HashMap(transportToEphemeralId));
            this.deviceSerial = deviceSerial;
            this.phoneInfo = phoneInfo;
        }

        public String getMobileDeviceId() {
            return this.mobileDeviceId;
        }

        public WifiProjectionProtocol.TransportType getTransportType() {
            return this.transportType;
        }

        public Map<WifiProjectionProtocol.TransportType, String> getTransportToEphemeralId() {
            return this.transportToEphemeralId;
        }

        public String getDeviceSerial() {
            return this.deviceSerial;
        }

        public Common.PhoneInfo getPhoneInfo() {
            return this.phoneInfo;
        }

        public boolean equals(Object obj) {
            if (!(obj instanceof ClientInfo)) {
                return false;
            }
            ClientInfo other = (ClientInfo) obj;
            return Objects.equals(this.mobileDeviceId, other.mobileDeviceId) && Objects.equals(this.transportType, other.transportType) && Objects.equals(this.transportToEphemeralId, other.transportToEphemeralId) && Objects.equals(this.deviceSerial, other.deviceSerial) && Objects.equals(this.phoneInfo, other.phoneInfo);
        }

        public int hashCode() {
            return Objects.hash(this.mobileDeviceId, this.transportType, this.transportToEphemeralId, this.deviceSerial, this.phoneInfo);
        }
    }

    private static class WifiProjectionInfoProviderInternal implements WifiProjectionInfoProvider {
        private final WifiProjectionInfoProvider wifiProjectionInfoProvider;

        WifiProjectionInfoProviderInternal(WifiProjectionInfoProvider wifiProjectionInfoProvider) {
            this.wifiProjectionInfoProvider = wifiProjectionInfoProvider;
        }

        @Override // com.google.android.projection.protocol.WirelessProjectionProtocol.WifiProjectionInfoProvider
        public InetSocketAddress getProjectionPortInfo() {
            return this.wifiProjectionInfoProvider.getProjectionPortInfo();
        }

        @Override // com.google.android.projection.protocol.WirelessProjectionProtocol.WifiProjectionInfoProvider
        public String getSsid() {
            return this.wifiProjectionInfoProvider.getSsid();
        }

        @Override // com.google.android.projection.protocol.WirelessProjectionProtocol.WifiProjectionInfoProvider
        public String getPassword() {
            return this.wifiProjectionInfoProvider.getPassword();
        }

        @Override // com.google.android.projection.protocol.WirelessProjectionProtocol.WifiProjectionInfoProvider
        public String getBssid() {
            return this.wifiProjectionInfoProvider.getBssid();
        }

        String getIpAddress() {
            return getProjectionPortInfo().getAddress().getHostAddress();
        }

        int getPort() {
            return getProjectionPortInfo().getPort();
        }
    }

    private final class StartResultFuture implements Future<WifiProjectionProtocol.ProjectionStartResult> {
        volatile long nativeFutureInstance;
        volatile WifiProjectionProtocol.ProjectionStartResult startResult = WifiProjectionProtocol.ProjectionStartResult.PROJECTION_START_RESULT_UNKNOWN;

        StartResultFuture(long nativeFutureInstance) {
            this.nativeFutureInstance = nativeFutureInstance;
        }

        @Override // java.util.concurrent.Future
        public boolean cancel(boolean mayInterruptIfRunning) {
            throw new UnsupportedOperationException("StartResultFuture cannot be cancelled");
        }

        @Override // java.util.concurrent.Future
        public boolean isCancelled() {
            return false;
        }

        @Override // java.util.concurrent.Future
        public boolean isDone() {
            return this.nativeFutureInstance == 0;
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // java.util.concurrent.Future
        public WifiProjectionProtocol.ProjectionStartResult get() {
            if (this.nativeFutureInstance != 0) {
                this.startResult = WirelessProjectionProtocol.this.get(this);
                return this.startResult;
            }
            return this.startResult;
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // java.util.concurrent.Future
        public WifiProjectionProtocol.ProjectionStartResult get(long timeout, TimeUnit unit) throws TimeoutException {
            if (this.nativeFutureInstance != 0) {
                this.startResult = WirelessProjectionProtocol.this.get(this, timeout, unit);
                return this.startResult;
            }
            return this.startResult;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public WifiProjectionProtocol.ProjectionStartResult get(StartResultFuture future) {
        try {
            return get(future, -1L, TimeUnit.SECONDS);
        } catch (TimeoutException e) {
            throw new AssertionError(e);
        }
    }
}
