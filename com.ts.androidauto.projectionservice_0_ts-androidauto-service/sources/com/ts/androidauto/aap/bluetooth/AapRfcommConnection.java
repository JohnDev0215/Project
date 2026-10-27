package com.ts.androidauto.aap.bluetooth;

import android.bluetooth.BluetoothSocket;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.os.SystemClock;
import com.google.android.projection.proto.Common;
import com.google.android.projection.proto.WifiDiscoveryProtos;
import com.google.protobuf.ByteString;
import com.google.protobuf.InvalidProtocolBufferException;
import com.ts.androidauto.aap.AapController;
import com.ts.androidauto.aap.common.VehicleInfo;
import com.ts.androidauto.aap.common.WifiConnectInfo;
import com.ts.androidauto.aap.vehicledata.VehicleDataManager;
import com.ts.androidauto.fsdk.common.AndroidAutoConst;
import com.ts.androidauto.fsdk.common.LogUtil;
import com.ts.androidauto.fsdk.data.AndroidAutoDevice;
import java.io.EOFException;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.ByteBuffer;

/* JADX INFO: loaded from: classes.dex */
public class AapRfcommConnection {
    private static final int HEADER_SIZE = 4;
    private static final int LENGTH_SIZE = 2;
    private static final int MSG_WIFI_CONNECT_STATUS_TIMEOUT = 3;
    private static final int MSG_WIFI_START_TIMEOUT = 2;
    private static final int MSG_WIFI_VERSION_TIMEOUT = 1;
    private static final String TAG = "AAP" + AapRfcommConnection.class.getSimpleName();
    private static final int TIMEOUT_WIFI_CONNECT_STATUS_MS = 45000;
    private static final int TIMEOUT_WIFI_START_MS = 25000;
    private static final int TIMEOUT_WIFI_VERSION_MS = 30000;
    private static final int TYPE_SIZE = 2;
    private BluetoothSocket mBluetoothSocket;
    private InputStream mInputStream;
    private boolean mIsRevWifiStartResponse;
    private boolean mIsRevWifiVersionResponse;
    private boolean mIsRfcommDisconnect;
    private AndroidAutoDevice mMirrorDevice;
    private OutputStream mOutputStream;
    private AapRfcommTimeoutHandler mTimeoutHandler;
    private ReceiveDataThread mReceiveDataThread = null;
    private AapRfcommConnectionCallbacks mRfcommConnectCallbacks = null;
    private WifiConnectInfo mWifiConnectInfo = null;
    private boolean mIsMdRequestWifiStart = false;
    private boolean mIsRfcommReconnect = false;
    private AapController mAapController = AapController.getInstance();

    public AapRfcommConnection(AndroidAutoDevice device, BluetoothSocket bluetoothSocket, Looper looper) {
        this.mInputStream = null;
        this.mOutputStream = null;
        this.mBluetoothSocket = null;
        this.mIsRevWifiVersionResponse = false;
        this.mIsRevWifiStartResponse = false;
        this.mIsRfcommDisconnect = false;
        this.mMirrorDevice = null;
        this.mIsRevWifiVersionResponse = false;
        this.mIsRevWifiStartResponse = false;
        this.mMirrorDevice = device;
        this.mBluetoothSocket = bluetoothSocket;
        this.mIsRfcommDisconnect = false;
        if (looper == null) {
            LogUtil.error(TAG, "AapRfcommConnection constructure looper is null");
        } else {
            this.mTimeoutHandler = new AapRfcommTimeoutHandler(looper);
        }
        try {
            if (this.mBluetoothSocket != null) {
                this.mInputStream = this.mBluetoothSocket.getInputStream();
                this.mOutputStream = this.mBluetoothSocket.getOutputStream();
            }
        } catch (IOException ioException) {
            LogUtil.warning(TAG, "AapRfcommConnection ioException:" + ioException.toString());
        }
    }

    public void updateWirelessSocket(BluetoothSocket bluetoothSocket) {
        LogUtil.verbose(TAG, "update new Socket:" + bluetoothSocket);
        LogUtil.verbose(TAG, "update old Socket:" + this.mBluetoothSocket);
        LogUtil.verbose(TAG, "update mIsRfcommDisconnect:" + this.mIsRfcommDisconnect);
        this.mBluetoothSocket = bluetoothSocket;
        stopRevDataThread();
        this.mIsRfcommReconnect = true;
        try {
            if (this.mBluetoothSocket != null) {
                this.mInputStream = this.mBluetoothSocket.getInputStream();
                this.mOutputStream = this.mBluetoothSocket.getOutputStream();
            }
        } catch (IOException ioException) {
            LogUtil.warning(TAG, "AapRfcommConnection ioException:" + ioException.toString());
        }
    }

    public void registerRfcommConnectCallbacks(AapRfcommConnectionCallbacks rfcommConnectCallbacks) {
        this.mRfcommConnectCallbacks = rfcommConnectCallbacks;
    }

    public void unRegisterRfcommConnectCallbacks() {
        LogUtil.verbose(TAG, "unRegisterRfcommConnectCallbacks is invoke! Will be removed.");
        this.mRfcommConnectCallbacks = null;
        AapRfcommTimeoutHandler aapRfcommTimeoutHandler = this.mTimeoutHandler;
        if (aapRfcommTimeoutHandler != null) {
            aapRfcommTimeoutHandler.removeCallbacksAndMessages(null);
            this.mTimeoutHandler = null;
        }
    }

    public void startReadingMessages() {
        LogUtil.verbose(TAG, "startReadingMessages is invoke!");
        try {
            ByteBuffer header = readHeader();
            ByteBuffer payload = readPayload(header);
            handleMessage(header, payload);
        } catch (IOException exception) {
            LogUtil.warning(TAG, "startReadingMessages exception:" + exception.toString());
            this.mIsRfcommDisconnect = true;
            LogUtil.verbose(TAG, "notifyRfcommDisconnect mMirrorDevice: " + this.mMirrorDevice);
            this.mWifiConnectInfo = null;
            AapRfcommConnectionCallbacks aapRfcommConnectionCallbacks = this.mRfcommConnectCallbacks;
            if (aapRfcommConnectionCallbacks != null) {
                aapRfcommConnectionCallbacks.notifyRfcommDisconnect(this.mMirrorDevice);
            }
            ReceiveDataThread receiveDataThread = this.mReceiveDataThread;
            if (receiveDataThread != null) {
                receiveDataThread.finishReceive();
            }
        }
    }

    private void startRevDataThread() {
        LogUtil.verbose(TAG, "startRevDataThread is invoke!");
        if (this.mReceiveDataThread != null) {
            stopRevDataThread();
        }
        ReceiveDataThread receiveDataThread = new ReceiveDataThread();
        this.mReceiveDataThread = receiveDataThread;
        receiveDataThread.start();
    }

    private void stopRevDataThread() {
        LogUtil.verbose(TAG, "stopRevDataThread is invoke!");
        try {
            if (this.mOutputStream != null) {
                LogUtil.verbose(TAG, "stopRevDataThread mOutputStream close!");
                this.mOutputStream.close();
            }
            if (this.mInputStream != null) {
                LogUtil.verbose(TAG, "stopRevDataThread mInputStream close!");
                this.mInputStream.close();
            }
        } catch (IOException ioException) {
            LogUtil.warning(TAG, "stopRevDataThread ioException:" + ioException.toString());
        }
        ReceiveDataThread receiveDataThread = this.mReceiveDataThread;
        if (receiveDataThread != null) {
            try {
                receiveDataThread.finishReceive();
                this.mReceiveDataThread.join();
            } catch (InterruptedException exception) {
                exception.printStackTrace();
            }
            this.mReceiveDataThread = null;
        }
        this.mOutputStream = null;
        this.mInputStream = null;
    }

    private class ReceiveDataThread extends Thread {
        private boolean revDataThreadFlag;

        private ReceiveDataThread() {
            this.revDataThreadFlag = true;
        }

        public void finishReceive() {
            this.revDataThreadFlag = false;
        }

        @Override // java.lang.Thread, java.lang.Runnable
        public void run() {
            LogUtil.verbose(AapRfcommConnection.TAG, "ReceiveDataThread run");
            while (this.revDataThreadFlag) {
                AapRfcommConnection.this.startReadingMessages();
                SystemClock.sleep(10L);
            }
        }
    }

    private ByteBuffer readHeader() throws IOException {
        return readBytes(4);
    }

    private ByteBuffer readPayload(ByteBuffer header) throws IOException {
        return readBytes(header.getShort(0));
    }

    private ByteBuffer readBytes(int size) throws IOException {
        ByteBuffer buffer = ByteBuffer.allocate(size);
        while (buffer.hasRemaining()) {
            int bytesRead = this.mInputStream.read(buffer.array(), buffer.position(), buffer.remaining());
            if (bytesRead == -1) {
                LogUtil.verbose(TAG, "Read EOF for RFCOMM to ");
                throw new EOFException();
            }
            buffer.position(buffer.position() + bytesRead);
        }
        buffer.position(0);
        return buffer;
    }

    private void write(byte[] bytes) throws IOException {
        try {
            this.mOutputStream.write(bytes);
        } catch (IOException exception) {
            throw exception;
        }
    }

    private void handleMessage(ByteBuffer header, ByteBuffer payload) {
        LogUtil.verbose(TAG, "handleMessage header:" + header.toString());
        if (payload != null) {
            LogUtil.verbose(TAG, "handleMessage payload:" + payload.toString());
        }
        short messageType = header.getShort(2);
        LogUtil.verbose(TAG, "handleMessage messageType:" + ((int) messageType));
        if (messageType != 1) {
            if (messageType == 2) {
                handleRequestInfo();
                return;
            }
            if (messageType == 5) {
                if (payload != null) {
                    handleVersionResponse(payload);
                    return;
                } else {
                    LogUtil.warning(TAG, "handleMessage payload is null.");
                    return;
                }
            }
            if (messageType == 6) {
                if (payload != null) {
                    handleConnectStatus(payload);
                    return;
                } else {
                    LogUtil.warning(TAG, "handleMessage payload is null.");
                    return;
                }
            }
            if (messageType == 7) {
                if (payload != null) {
                    handleStartResponse(payload);
                    return;
                } else {
                    LogUtil.warning(TAG, "handleMessage payload is null.");
                    return;
                }
            }
            LogUtil.warning(TAG, "Received unknown message type=" + ((int) messageType));
            return;
        }
        handleStartRequest();
    }

    private void handleRequestInfo() {
        WifiConnectInfo wifiInfo = null;
        AapController aapController = this.mAapController;
        if (aapController != null) {
            wifiInfo = aapController.getWifiApInfo();
        }
        LogUtil.verbose(TAG, "HU <- MD WifiInfoRequest ok. WiFi info:" + wifiInfo);
        startCheckConnectStatusTimeout();
        if (wifiInfo != null) {
            if (wifiInfo.getHostBssid() == null) {
                LogUtil.verbose(TAG, "handle WifiInfoRequest HostBssid is null, connect failed");
                AapRfcommConnectionCallbacks aapRfcommConnectionCallbacks = this.mRfcommConnectCallbacks;
                if (aapRfcommConnectionCallbacks != null) {
                    aapRfcommConnectionCallbacks.notifyWifiConnectError(this.mMirrorDevice, AndroidAutoConst.LinkErrorCode.ERROR_WIFI_START_FAILED, AndroidAutoConst.DEFAULT_SCREEN_NAME);
                    return;
                }
                return;
            }
            try {
                WifiDiscoveryProtos.WifiInfoResponse wifiResponse = (WifiDiscoveryProtos.WifiInfoResponse) WifiDiscoveryProtos.WifiInfoResponse.newBuilder().setWifiSsid(wifiInfo.getWifiSsid()).setWifiBssid(wifiInfo.getHostBssid()).setWifiPassword(wifiInfo.getWifiPassword()).setWifiSecurityMode(WifiDiscoveryProtos.WifiSecurityMode.WPA2_PERSONAL).setAccessPointType(WifiDiscoveryProtos.AccessPointType.STATIC).build();
                sendMessage(wifiResponse.toByteArray(), 3);
                LogUtil.verbose(TAG, "HU -> MD WifiInfoResponse ok.");
            } catch (IOException exception) {
                LogUtil.warning(TAG, "WifiInfoResponse exception:" + exception.toString());
            }
        }
    }

    private void handleVersionResponse(ByteBuffer message) {
        LogUtil.verbose(TAG, "HU <- MD WifiVersionResponse entry. message=" + message);
        this.mIsRevWifiVersionResponse = true;
        stopCheckWifiVersionThreadTimeout();
        try {
            WifiDiscoveryProtos.WifiVersionResponse response = WifiDiscoveryProtos.WifiVersionResponse.parseFrom(ByteString.copyFrom(message));
            LogUtil.verbose(TAG, "HU <- MD WifiVersionResponse ok. status=" + response.getVersionStatus());
            boolean wirelessSupported = response.getVersionStatus() == WifiDiscoveryProtos.MessageStatus.STATUS_SUCCESS;
            if (!wirelessSupported) {
                if (this.mRfcommConnectCallbacks != null) {
                    this.mRfcommConnectCallbacks.notifyWifiSupportState(this.mMirrorDevice, false, null);
                }
                LogUtil.verbose(TAG, "WifiVersionResponse not support wireless aa.");
                return;
            }
            if (response.hasDeviceSerial()) {
                if (this.mRfcommConnectCallbacks != null) {
                    this.mRfcommConnectCallbacks.notifyWifiSupportState(this.mMirrorDevice, true, response.getDeviceSerial());
                }
                LogUtil.verbose(TAG, "WifiVersionResponse device serial: " + response.getDeviceSerial());
            } else {
                if (this.mRfcommConnectCallbacks != null) {
                    this.mRfcommConnectCallbacks.notifyWifiSupportState(this.mMirrorDevice, true, null);
                }
                LogUtil.warning(TAG, "WifiVersionResponse is missing device serial");
            }
            if (this.mAapController != null) {
                LogUtil.info(TAG, "WifiVersionResponse mIsRfcommReconnect:" + this.mIsRfcommReconnect + " AapController.getLinkStatus():" + this.mAapController.getLinkStatus() + " mWifiConnectInfo:" + this.mWifiConnectInfo);
                if (this.mIsRfcommReconnect && this.mAapController.getLinkStatus() == AndroidAutoConst.LinkStatus.LINK_STATUS_ACTIVATING && this.mWifiConnectInfo != null) {
                    LogUtil.warning(TAG, "Rfcomm reconnect then send wifi start to MD");
                    sendStartRequestMessage(this.mWifiConnectInfo);
                    this.mIsRfcommReconnect = false;
                    this.mWifiConnectInfo = null;
                }
            }
        } catch (InvalidProtocolBufferException exception) {
            LogUtil.warning(TAG, "handleVersionResponse InvalidProtocolBufferException:" + exception.toString());
        }
    }

    private void handleConnectStatus(ByteBuffer message) {
        LogUtil.verbose(TAG, "HU <- MD WifiConnectStatus entry.");
        stopCheckConnectStatusTimeout();
        try {
            WifiDiscoveryProtos.WifiConnectStatus status = WifiDiscoveryProtos.WifiConnectStatus.parseFrom(ByteString.copyFrom(message));
            LogUtil.info(TAG, "HU <- MD WifiConnectStatus ok. status=" + status.getConnectStatus());
            this.mIsMdRequestWifiStart = false;
            if (status.getConnectStatus() == WifiDiscoveryProtos.MessageStatus.STATUS_SUCCESS) {
                LogUtil.verbose(TAG, "WifiConnectStatus = STATUS_SUCCESS!");
                return;
            }
            if (this.mMirrorDevice != null && this.mMirrorDevice.isWireLessLink() && this.mRfcommConnectCallbacks != null) {
                if (status.getConnectStatus() == WifiDiscoveryProtos.MessageStatus.STATUS_UNSOLICITED_MESSAGE) {
                    if (status.hasErrorMessageHint()) {
                        String errorMessage = status.getErrorMessageHint();
                        LogUtil.debug(TAG, "WifiConnectStatus has errorMessageHint : " + errorMessage);
                        if (errorMessage != null) {
                            this.mRfcommConnectCallbacks.notifyWifiConnectError(this.mMirrorDevice, AndroidAutoConst.LinkErrorCode.ERROR_WIFI_CONNECT_UNSOLICITED_MESSAGE, errorMessage);
                        }
                        return;
                    }
                    this.mRfcommConnectCallbacks.notifyWifiConnectError(this.mMirrorDevice, AndroidAutoConst.LinkErrorCode.ERROR_WIFI_START_FAILED, AndroidAutoConst.DEFAULT_SCREEN_NAME);
                    return;
                }
                if (status.getConnectStatus() == WifiDiscoveryProtos.MessageStatus.STATUS_INSTRUCT_USER_TO_CHECK_THE_PHONE) {
                    this.mRfcommConnectCallbacks.notifyWifiConnectError(this.mMirrorDevice, AndroidAutoConst.LinkErrorCode.ERROR_WIFI_CONNECT_INSTRUCT_USER_TO_CHECK_THE_PHONE, AndroidAutoConst.DEFAULT_SCREEN_NAME);
                    return;
                } else {
                    this.mRfcommConnectCallbacks.notifyWifiConnectError(this.mMirrorDevice, AndroidAutoConst.LinkErrorCode.ERROR_WIFI_START_FAILED, AndroidAutoConst.DEFAULT_SCREEN_NAME);
                    return;
                }
            }
            LogUtil.verbose(TAG, "WifiConnectStatus unexpected case! mMirrorDevice=" + this.mMirrorDevice + " mRfcommConnectCallbacks=" + this.mRfcommConnectCallbacks);
        } catch (InvalidProtocolBufferException exception) {
            LogUtil.warning(TAG, "Unable to parse connection status: " + exception.toString());
        }
    }

    private void handleStartRequest() {
        LogUtil.verbose(TAG, "HU <- MD WifiStartRequest entry. alreadyStart:" + this.mIsMdRequestWifiStart);
        AapController aapController = this.mAapController;
        if (aapController != null) {
            if (aapController.isOtherAapBusy(this.mMirrorDevice) || this.mAapController.isSameAapBusy(this.mMirrorDevice) || this.mAapController.getOtherProjectionActive()) {
                LogUtil.info(TAG, "Don't handle WifiStartRequest while AAP is busy");
                sendStartResponse(true);
                this.mIsMdRequestWifiStart = false;
                return;
            }
            if (!this.mIsRevWifiVersionResponse) {
                LogUtil.info(TAG, "MD try connect Android Auto, wait WifiVersionResponse.");
                try {
                    WifiDiscoveryProtos.WifiStartResponse.Builder message = WifiDiscoveryProtos.WifiStartResponse.newBuilder();
                    message.setStatus(WifiDiscoveryProtos.MessageStatus.STATUS_WIFI_NOT_YET_STARTED);
                    sendMessage(((WifiDiscoveryProtos.WifiStartResponse) message.build()).toByteArray(), 7);
                    return;
                } catch (IOException exception) {
                    LogUtil.warning(TAG, "Unable to WifiStartResponse STATUS_WIFI_NOT_YET_STARTED: " + exception.toString());
                    return;
                }
            }
            if (!this.mIsMdRequestWifiStart) {
                LogUtil.info(TAG, "MD try connect Android Auto, start connect projection.");
                this.mIsMdRequestWifiStart = true;
                AapRfcommConnectionCallbacks aapRfcommConnectionCallbacks = this.mRfcommConnectCallbacks;
                if (aapRfcommConnectionCallbacks != null) {
                    aapRfcommConnectionCallbacks.notifyMdRequestWifiStart(this.mMirrorDevice);
                }
                startCheckConnectStatusTimeout();
                try {
                    WifiDiscoveryProtos.WifiStartResponse.Builder message2 = WifiDiscoveryProtos.WifiStartResponse.newBuilder();
                    message2.setStatus(WifiDiscoveryProtos.MessageStatus.STATUS_WIFI_NOT_YET_STARTED);
                    sendMessage(((WifiDiscoveryProtos.WifiStartResponse) message2.build()).toByteArray(), 7);
                    return;
                } catch (IOException exception2) {
                    LogUtil.warning(TAG, "Unable to WifiStartResponse STATUS_WIFI_NOT_YET_STARTED: " + exception2.toString());
                    return;
                }
            }
            LogUtil.info(TAG, "MD try connect Android Auto, response OK.");
            startCheckConnectStatusTimeout();
            sendStartResponse(false);
        }
    }

    private void handleStartResponse(ByteBuffer message) {
        WifiDiscoveryProtos.MessageStatus status;
        LogUtil.verbose(TAG, "HU <- MD WifiStartResponse entry.");
        this.mIsRevWifiStartResponse = true;
        stopCheckWifiStartTimeout();
        startCheckConnectStatusTimeout();
        try {
            WifiDiscoveryProtos.WifiStartResponse response = WifiDiscoveryProtos.WifiStartResponse.parseFrom(ByteString.copyFrom(message));
            if (!response.hasStatus()) {
                LogUtil.info(TAG, "Received WifiStartResponse wait for wifi connect status");
                status = null;
            } else {
                status = response.getStatus();
            }
            LogUtil.verbose(TAG, "HU <- MD WifiStartResponse ok. status=" + status);
        } catch (InvalidProtocolBufferException exception) {
            LogUtil.warning(TAG, "Unable to parse WifiStartResponse: " + exception.toString());
        }
    }

    public void sendStartRequestMessage(WifiConnectInfo wifiConnectInfo) {
        LogUtil.verbose(TAG, "HU -> MD WifiStartRequest entry. WifiInfo=" + wifiConnectInfo);
        if (wifiConnectInfo != null) {
            int tcpPort = 0;
            if (this.mAapController != null) {
                tcpPort = AapController.getCurrWifiPort();
            }
            WifiDiscoveryProtos.StartRequestReason reason = WifiDiscoveryProtos.StartRequestReason.USER_REQUEST;
            AndroidAutoDevice androidAutoDevice = this.mMirrorDevice;
            if (androidAutoDevice != null && androidAutoDevice.isAutoConnect()) {
                reason = this.mMirrorDevice.getErrorCode() >= 0 ? WifiDiscoveryProtos.StartRequestReason.AUTOMATIC_RESTART : WifiDiscoveryProtos.StartRequestReason.AUTO_LAUNCH;
            }
            try {
                WifiDiscoveryProtos.WifiStartRequest request = (WifiDiscoveryProtos.WifiStartRequest) WifiDiscoveryProtos.WifiStartRequest.newBuilder().setIpAddress(wifiConnectInfo.getHostIp()).setPort(tcpPort).setStartRequestReason(reason).build();
                sendMessage(request.toByteArray(), 1);
                LogUtil.verbose(TAG, "HU -> MD WifiStartRequest ok. ip= " + wifiConnectInfo.getHostIp() + " port=" + tcpPort + " reason=" + reason);
            } catch (IOException exception) {
                LogUtil.warning(TAG, "Unable to send WifiStartRequest: " + exception.toString());
            }
        }
    }

    public void sendVersionRequestMessage() {
        LogUtil.verbose(TAG, "HU -> MD WifiVersionRequest entry.");
        this.mIsRfcommDisconnect = false;
        startRevDataThread();
        try {
            WifiDiscoveryProtos.WifiVersionRequest.Builder builder = WifiDiscoveryProtos.WifiVersionRequest.newBuilder();
            builder.setMajorVersion(4);
            builder.setMinorVersion(1);
            builder.setSupportedWifiChannelType(WifiDiscoveryProtos.WifiChannelType.CHANNELS_5GHZ_ONLY);
            int[] supportedChannels = null;
            if (this.mRfcommConnectCallbacks != null) {
                supportedChannels = this.mRfcommConnectCallbacks.getWifiChannels();
            }
            if (supportedChannels != null && supportedChannels.length > 0) {
                for (int channel : supportedChannels) {
                    builder.addSupportedWifiChannels(channel);
                }
            } else {
                for (int channel2 : AapController.DEFAULT_WIFI_5G_CHANNELS) {
                    builder.addSupportedWifiChannels(channel2);
                }
            }
            Common.HeadUnitInfo.Builder huInfoBuilder = Common.HeadUnitInfo.newBuilder();
            VehicleInfo info = VehicleDataManager.getInstance().getVehicleInfo();
            huInfoBuilder.setMake(info.getVehicleMaker());
            huInfoBuilder.setModel(info.getVehicleModel());
            huInfoBuilder.setYear(info.getVehicleYear());
            huInfoBuilder.setVehicleId(info.getVehicleId());
            huInfoBuilder.setHeadUnitMake(info.getHeadUnitMaker());
            huInfoBuilder.setHeadUnitModel(info.getHeadUnitModel());
            huInfoBuilder.setHeadUnitSoftwareBuild(info.getHeadUnitSwBuild());
            huInfoBuilder.setHeadUnitSoftwareVersion(info.getHeadUnitSwVersion());
            builder.setHeadunitInfo(huInfoBuilder);
            WifiDiscoveryProtos.WifiVersionRequest request = (WifiDiscoveryProtos.WifiVersionRequest) builder.build();
            sendMessage(request.toByteArray(), 4);
            startCheckWifiVersionTimeout();
            if (supportedChannels != null && supportedChannels.length > 0) {
                LogUtil.verbose(TAG, "HU -> MD WifiVersionRequest end. channel=" + supportedChannels[0]);
                return;
            }
            LogUtil.verbose(TAG, "HU -> MD WifiVersionRequest end. default channel=5180");
        } catch (IOException exception) {
            LogUtil.warning(TAG, "Unable to send WifiVersionRequest: " + exception.toString());
        }
    }

    private void sendStartResponse(boolean alreadyConnected) {
        WifiConnectInfo wifiInfo = null;
        AapController aapController = this.mAapController;
        if (aapController != null) {
            wifiInfo = aapController.getWifiApInfo();
        }
        LogUtil.verbose(TAG, "HU -> MD WifiStartResponse entry. alreadyConnectedAAP=" + alreadyConnected + " WifiInfo=" + wifiInfo);
        if (alreadyConnected) {
            try {
                WifiDiscoveryProtos.WifiStartResponse.Builder message = WifiDiscoveryProtos.WifiStartResponse.newBuilder();
                message.setStatus(WifiDiscoveryProtos.MessageStatus.STATUS_PROJECTION_ALREADY_STARTED);
                sendMessage(((WifiDiscoveryProtos.WifiStartResponse) message.build()).toByteArray(), 7);
                LogUtil.verbose(TAG, "HU -> MD WifiStartResponse reject, STATUS_PROJECTION_ALREADY_STARTED.");
                return;
            } catch (IOException exception) {
                LogUtil.warning(TAG, "Unable to send WifiStartResponse (alreadyConnected): " + exception.toString());
                return;
            }
        }
        if (wifiInfo != null) {
            int tcpPort = 0;
            try {
                if (this.mAapController != null) {
                    tcpPort = AapController.getCurrWifiPort();
                }
                WifiDiscoveryProtos.WifiStartResponse.Builder message2 = WifiDiscoveryProtos.WifiStartResponse.newBuilder();
                message2.setStatus(WifiDiscoveryProtos.MessageStatus.STATUS_SUCCESS);
                message2.setIpAddress(wifiInfo.getHostIp()).setPort(tcpPort);
                sendMessage(((WifiDiscoveryProtos.WifiStartResponse) message2.build()).toByteArray(), 7);
                LogUtil.verbose(TAG, "HU -> MD WifiStartResponse ok. port:" + tcpPort);
                return;
            } catch (IOException exception2) {
                LogUtil.warning(TAG, "Unable to send WifiStartResponse STATUS_SUCCESS: " + exception2.toString());
                return;
            }
        }
        LogUtil.info(TAG, "HU -> MD WifiStartResponse STATUS_WIFI_NOT_YET_STARTED.");
        try {
            WifiDiscoveryProtos.WifiStartResponse.Builder message3 = WifiDiscoveryProtos.WifiStartResponse.newBuilder();
            message3.setStatus(WifiDiscoveryProtos.MessageStatus.STATUS_WIFI_NOT_YET_STARTED);
            sendMessage(((WifiDiscoveryProtos.WifiStartResponse) message3.build()).toByteArray(), 7);
        } catch (IOException exception3) {
            LogUtil.warning(TAG, "Unable to WifiStartResponse STATUS_WIFI_NOT_YET_STARTED: " + exception3.toString());
        }
    }

    public void sendMdStartFailedResponse() {
        LogUtil.verbose(TAG, "HU -> MD WifiStartResponse entry.");
        try {
            WifiDiscoveryProtos.WifiStartResponse.Builder message = WifiDiscoveryProtos.WifiStartResponse.newBuilder();
            message.setStatus(WifiDiscoveryProtos.MessageStatus.STATUS_WIFI_NOT_YET_STARTED);
            sendMessage(((WifiDiscoveryProtos.WifiStartResponse) message.build()).toByteArray(), 7);
            LogUtil.verbose(TAG, "HU -> MD WifiStartResponse reject, STATUS_WIFI_NOT_YET_STARTED.");
        } catch (IOException exception) {
            LogUtil.warning(TAG, "Unable to send WifiStartResponse STATUS_WIFI_NOT_YET_STARTED: " + exception.toString());
        }
    }

    private void sendMessage(byte[] bytes, int type) throws IOException {
        ByteBuffer message = ByteBuffer.allocate(bytes.length + 4);
        message.putShort((short) bytes.length);
        message.putShort((short) type);
        message.put(bytes);
        write(message.array());
    }

    public void wifiApSetUpFinish(WifiConnectInfo wifiConnectInfo) {
        LogUtil.verbose(TAG, "wifiApSetUp is ok mIsMdRequestWifiStart:" + this.mIsMdRequestWifiStart);
        this.mWifiConnectInfo = wifiConnectInfo;
        this.mIsRevWifiStartResponse = false;
        if (this.mIsMdRequestWifiStart) {
            handleStartRequest();
        } else {
            sendStartRequestMessage(wifiConnectInfo);
            startCheckWifiStartTimeout();
        }
    }

    public void terminateConnectionFromMd() {
        this.mIsMdRequestWifiStart = false;
    }

    private void startCheckWifiStartTimeout() {
        LogUtil.verbose(TAG, "startCheckWifiStartTimeout is invoke!");
        AapRfcommTimeoutHandler aapRfcommTimeoutHandler = this.mTimeoutHandler;
        if (aapRfcommTimeoutHandler != null) {
            aapRfcommTimeoutHandler.removeMessages(2);
            if (!this.mIsRevWifiStartResponse) {
                LogUtil.verbose(TAG, "startCheckWifiStartTimeout send message delay!");
                this.mTimeoutHandler.sendEmptyMessageDelayed(2, 25000L);
            }
        }
    }

    private void stopCheckWifiStartTimeout() {
        LogUtil.verbose(TAG, "stopCheckWifiStartTimeout is invoke!");
        AapRfcommTimeoutHandler aapRfcommTimeoutHandler = this.mTimeoutHandler;
        if (aapRfcommTimeoutHandler != null) {
            aapRfcommTimeoutHandler.removeMessages(2);
        }
    }

    private void startCheckWifiVersionTimeout() {
        LogUtil.verbose(TAG, "startCheckWifiVersionTimeout is invoke!");
        AapRfcommTimeoutHandler aapRfcommTimeoutHandler = this.mTimeoutHandler;
        if (aapRfcommTimeoutHandler != null) {
            aapRfcommTimeoutHandler.removeMessages(1);
            if (!this.mIsRevWifiVersionResponse) {
                LogUtil.verbose(TAG, "startCheckWifiVersionTimeout send message delay!");
                this.mTimeoutHandler.sendEmptyMessageDelayed(1, 30000L);
            }
        }
    }

    private void stopCheckWifiVersionThreadTimeout() {
        LogUtil.verbose(TAG, "stopCheckWifiVersionThreadTimeout is invoke!");
        AapRfcommTimeoutHandler aapRfcommTimeoutHandler = this.mTimeoutHandler;
        if (aapRfcommTimeoutHandler != null) {
            aapRfcommTimeoutHandler.removeMessages(1);
        }
    }

    private void startCheckConnectStatusTimeout() {
        LogUtil.verbose(TAG, "startCheckConnectStatusTimeout is invoke!");
        AapRfcommTimeoutHandler aapRfcommTimeoutHandler = this.mTimeoutHandler;
        if (aapRfcommTimeoutHandler != null) {
            aapRfcommTimeoutHandler.removeMessages(3);
            LogUtil.verbose(TAG, "startCheckConnectStatusTimeout send message delay!");
            this.mTimeoutHandler.sendEmptyMessageDelayed(3, 45000L);
        }
    }

    private void stopCheckConnectStatusTimeout() {
        LogUtil.verbose(TAG, "stopCheckConnectStatusTimeout is invoke!");
        AapRfcommTimeoutHandler aapRfcommTimeoutHandler = this.mTimeoutHandler;
        if (aapRfcommTimeoutHandler != null) {
            aapRfcommTimeoutHandler.removeMessages(3);
        }
    }

    private class AapRfcommTimeoutHandler extends Handler {
        public static final String TAG = "AapRfcommTimeoutHandler";

        public AapRfcommTimeoutHandler(Looper looper) {
            super(looper);
        }

        @Override // android.os.Handler
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            int i = msg.what;
            if (i == 1) {
                LogUtil.verbose(TAG, "MSG_WIFI_VERSION_TIMEOUT 30 seconds reached!");
                if (AapRfcommConnection.this.mRfcommConnectCallbacks != null && !AapRfcommConnection.this.mIsRevWifiVersionResponse) {
                    AapRfcommConnection.this.mRfcommConnectCallbacks.notifyWifiSupportState(AapRfcommConnection.this.mMirrorDevice, false, null);
                    return;
                }
                return;
            }
            if (i == 2) {
                LogUtil.verbose(TAG, "MSG_WIFI_START_TIMEOUT 25 seconds reached!");
                if (AapRfcommConnection.this.mRfcommConnectCallbacks != null && !AapRfcommConnection.this.mIsRevWifiStartResponse) {
                    AapRfcommConnection.this.mRfcommConnectCallbacks.notifyWifiConnectError(AapRfcommConnection.this.mMirrorDevice, AndroidAutoConst.LinkErrorCode.ERROR_WIFI_START_FAILED, AndroidAutoConst.DEFAULT_SCREEN_NAME);
                    return;
                }
                return;
            }
            if (i == 3) {
                LogUtil.verbose(TAG, "MSG_WIFI_CONNECT_STATUS_TIMEOUT 45 seconds reached!");
                if (AapRfcommConnection.this.mRfcommConnectCallbacks != null) {
                    AapRfcommConnection.this.mRfcommConnectCallbacks.notifyWifiConnectError(AapRfcommConnection.this.mMirrorDevice, AndroidAutoConst.LinkErrorCode.ERROR_WIFI_START_FAILED, AndroidAutoConst.DEFAULT_SCREEN_NAME);
                }
            }
        }
    }
}
