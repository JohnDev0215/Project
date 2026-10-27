package com.android.bluetooth.sap;

import android.hardware.radio.V1_0.ISap;
import android.hardware.radio.V1_0.ISapCallback;
import android.os.Handler;
import android.os.IHwBinder;
import android.os.Message;
import android.os.RemoteException;
import android.util.Log;
import com.google.common.primitives.UnsignedBytes;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicLong;

/* JADX INFO: loaded from: classes.dex */
public class SapRilReceiver {
    public static final boolean DEBUG = true;
    public static final int RIL_MAX_COMMAND_BYTES = 8192;
    private static final String SERVICE_NAME_RIL_BT = "slot1";
    private static final int SOCKET_OPEN_RETRY_MILLIS = 4000;
    private static final String TAG = "SapRilReceiver";
    public static final boolean VERBOSE = true;
    volatile ISap mSapProxy;
    private Handler mSapServerMsgHandler;
    private Handler mSapServiceHandler;
    Object mSapProxyLock = new Object();
    final AtomicLong mSapProxyCookie = new AtomicLong(0);
    public byte[] buffer = new byte[8192];
    SapCallback mSapCallback = new SapCallback();
    final SapProxyDeathRecipient mSapProxyDeathRecipient = new SapProxyDeathRecipient();

    final class SapProxyDeathRecipient implements IHwBinder.DeathRecipient {
        SapProxyDeathRecipient() {
        }

        public void serviceDied(long cookie) {
            Log.d(SapRilReceiver.TAG, "serviceDied");
            SapRilReceiver.this.mSapServerMsgHandler.sendMessageDelayed(SapRilReceiver.this.mSapServerMsgHandler.obtainMessage(5, Long.valueOf(cookie)), 3000L);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void sendSapMessage(SapMessage sapMessage) {
        if (sapMessage.getMsgType() < 256) {
            sendClientMessage(sapMessage);
        } else {
            sendRilIndMessage(sapMessage);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void removeOngoingReqAndSendMessage(int token, SapMessage sapMessage) {
        Integer reqType = SapMessage.sOngoingRequests.remove(Integer.valueOf(token));
        StringBuilder sb = new StringBuilder();
        sb.append("removeOngoingReqAndSendMessage: token ");
        sb.append(token);
        sb.append(" reqType ");
        sb.append(reqType == null ? "null" : SapMessage.getMsgTypeName(reqType.intValue()));
        Log.d(TAG, sb.toString());
        sendSapMessage(sapMessage);
    }

    class SapCallback extends ISapCallback.Stub {
        SapCallback() {
        }

        public void connectResponse(int token, int sapConnectRsp, int maxMsgSize) {
            Log.d(SapRilReceiver.TAG, "connectResponse: token " + token + " sapConnectRsp " + sapConnectRsp + " maxMsgSize " + maxMsgSize);
            SapService.notifyUpdateWakeLock(SapRilReceiver.this.mSapServiceHandler);
            SapMessage sapMessage = new SapMessage(1);
            sapMessage.setConnectionStatus(sapConnectRsp);
            if (sapConnectRsp == 2) {
                sapMessage.setMaxMsgSize(maxMsgSize);
            }
            sapMessage.setResultCode(-1);
            SapRilReceiver.this.removeOngoingReqAndSendMessage(token, sapMessage);
        }

        public void disconnectResponse(int token) {
            Log.d(SapRilReceiver.TAG, "disconnectResponse: token " + token);
            SapService.notifyUpdateWakeLock(SapRilReceiver.this.mSapServiceHandler);
            SapMessage sapMessage = new SapMessage(3);
            sapMessage.setResultCode(-1);
            SapRilReceiver.this.removeOngoingReqAndSendMessage(token, sapMessage);
        }

        public void disconnectIndication(int token, int disconnectType) {
            Log.d(SapRilReceiver.TAG, "disconnectIndication: token " + token + " disconnectType " + disconnectType);
            SapService.notifyUpdateWakeLock(SapRilReceiver.this.mSapServiceHandler);
            SapMessage sapMessage = new SapMessage(258);
            sapMessage.setDisconnectionType(disconnectType);
            SapRilReceiver.this.sendSapMessage(sapMessage);
        }

        public void apduResponse(int token, int resultCode, ArrayList<Byte> apduRsp) {
            Log.d(SapRilReceiver.TAG, "apduResponse: token " + token);
            SapService.notifyUpdateWakeLock(SapRilReceiver.this.mSapServiceHandler);
            SapMessage sapMessage = new SapMessage(6);
            sapMessage.setResultCode(resultCode);
            if (resultCode == 0) {
                sapMessage.setApduResp(SapRilReceiver.arrayListToPrimitiveArray(apduRsp));
            }
            SapRilReceiver.this.removeOngoingReqAndSendMessage(token, sapMessage);
        }

        public void transferAtrResponse(int token, int resultCode, ArrayList<Byte> atr) {
            Log.d(SapRilReceiver.TAG, "transferAtrResponse: token " + token + " resultCode " + resultCode);
            SapService.notifyUpdateWakeLock(SapRilReceiver.this.mSapServiceHandler);
            SapMessage sapMessage = new SapMessage(8);
            sapMessage.setResultCode(resultCode);
            if (resultCode == 0) {
                sapMessage.setAtr(SapRilReceiver.arrayListToPrimitiveArray(atr));
            }
            SapRilReceiver.this.removeOngoingReqAndSendMessage(token, sapMessage);
        }

        public void powerResponse(int token, int resultCode) {
            SapMessage sapMessage;
            Log.d(SapRilReceiver.TAG, "powerResponse: token " + token + " resultCode " + resultCode);
            SapService.notifyUpdateWakeLock(SapRilReceiver.this.mSapServiceHandler);
            Integer reqType = SapMessage.sOngoingRequests.remove(Integer.valueOf(token));
            StringBuilder sb = new StringBuilder();
            sb.append("powerResponse: reqType ");
            sb.append(reqType == null ? "null" : SapMessage.getMsgTypeName(reqType.intValue()));
            Log.d(SapRilReceiver.TAG, sb.toString());
            if (reqType.intValue() == 9) {
                sapMessage = new SapMessage(10);
            } else if (reqType.intValue() == 11) {
                sapMessage = new SapMessage(12);
            } else {
                return;
            }
            sapMessage.setResultCode(resultCode);
            SapRilReceiver.this.sendSapMessage(sapMessage);
        }

        public void resetSimResponse(int token, int resultCode) {
            Log.d(SapRilReceiver.TAG, "resetSimResponse: token " + token + " resultCode " + resultCode);
            SapService.notifyUpdateWakeLock(SapRilReceiver.this.mSapServiceHandler);
            SapMessage sapMessage = new SapMessage(14);
            sapMessage.setResultCode(resultCode);
            SapRilReceiver.this.removeOngoingReqAndSendMessage(token, sapMessage);
        }

        public void statusIndication(int token, int status) {
            Log.d(SapRilReceiver.TAG, "statusIndication: token " + token + " status " + status);
            SapService.notifyUpdateWakeLock(SapRilReceiver.this.mSapServiceHandler);
            SapMessage sapMessage = new SapMessage(17);
            sapMessage.setStatusChange(status);
            SapRilReceiver.this.sendSapMessage(sapMessage);
        }

        public void transferCardReaderStatusResponse(int token, int resultCode, int cardReaderStatus) {
            Log.d(SapRilReceiver.TAG, "transferCardReaderStatusResponse: token " + token + " resultCode " + resultCode + " cardReaderStatus " + cardReaderStatus);
            SapService.notifyUpdateWakeLock(SapRilReceiver.this.mSapServiceHandler);
            SapMessage sapMessage = new SapMessage(16);
            sapMessage.setResultCode(resultCode);
            if (resultCode == 0) {
                sapMessage.setCardReaderStatus(cardReaderStatus);
            }
            SapRilReceiver.this.removeOngoingReqAndSendMessage(token, sapMessage);
        }

        public void errorResponse(int token) {
            Log.d(SapRilReceiver.TAG, "errorResponse: token " + token);
            SapService.notifyUpdateWakeLock(SapRilReceiver.this.mSapServiceHandler);
            SapMessage sapMessage = new SapMessage(511);
            SapRilReceiver.this.sendSapMessage(sapMessage);
        }

        public void transferProtocolResponse(int token, int resultCode) {
            Log.d(SapRilReceiver.TAG, "transferProtocolResponse: token " + token + " resultCode " + resultCode);
            SapService.notifyUpdateWakeLock(SapRilReceiver.this.mSapServiceHandler);
            SapMessage sapMessage = new SapMessage(20);
            sapMessage.setResultCode(resultCode);
            SapRilReceiver.this.removeOngoingReqAndSendMessage(token, sapMessage);
        }
    }

    public static byte[] arrayListToPrimitiveArray(List<Byte> bytes) {
        byte[] ret = new byte[bytes.size()];
        for (int i = 0; i < ret.length; i++) {
            ret[i] = bytes.get(i).byteValue();
        }
        return ret;
    }

    public Object getSapProxyLock() {
        return this.mSapProxyLock;
    }

    public ISap getSapProxy() {
        synchronized (this.mSapProxyLock) {
            if (this.mSapProxy != null) {
                return this.mSapProxy;
            }
            try {
                this.mSapProxy = ISap.getService(SERVICE_NAME_RIL_BT);
                if (this.mSapProxy != null) {
                    this.mSapProxy.linkToDeath(this.mSapProxyDeathRecipient, this.mSapProxyCookie.incrementAndGet());
                    this.mSapProxy.setCallback(this.mSapCallback);
                } else {
                    Log.e(TAG, "getSapProxy: mSapProxy == null");
                }
            } catch (RemoteException | RuntimeException e) {
                this.mSapProxy = null;
                Log.e(TAG, "getSapProxy: exception: " + e);
            }
            if (this.mSapProxy == null) {
                this.mSapServerMsgHandler.sendMessageDelayed(this.mSapServerMsgHandler.obtainMessage(5, Long.valueOf(this.mSapProxyCookie.get())), 3000L);
            }
            return this.mSapProxy;
        }
    }

    public void resetSapProxy() {
        synchronized (this.mSapProxyLock) {
            Log.d(TAG, "resetSapProxy :" + this.mSapProxy);
            try {
            } catch (RemoteException | RuntimeException e) {
                Log.e(TAG, "resetSapProxy: exception: " + e);
            }
            if (this.mSapProxy != null) {
                this.mSapProxy.unlinkToDeath(this.mSapProxyDeathRecipient);
                this.mSapProxy = null;
            } else {
                this.mSapProxy = null;
            }
        }
    }

    public SapRilReceiver(Handler sapServerMsgHandler, Handler sapServiceHandler) {
        this.mSapProxy = null;
        this.mSapServerMsgHandler = null;
        this.mSapServiceHandler = null;
        this.mSapServerMsgHandler = sapServerMsgHandler;
        this.mSapServiceHandler = sapServiceHandler;
        synchronized (this.mSapProxyLock) {
            this.mSapProxy = getSapProxy();
        }
    }

    void notifyShutdown() {
        Log.i(TAG, "notifyShutdown()");
        synchronized (this.mSapProxyLock) {
            if (this.mSapProxy != null) {
                sendShutdownMessage();
            }
        }
    }

    private static int readMessage(InputStream is, byte[] buffer) throws IOException {
        int offset = 0;
        int remaining = 4;
        do {
            int countRead = is.read(buffer, offset, remaining);
            if (countRead < 0) {
                Log.e(TAG, "Hit EOS reading message length");
                return -1;
            }
            offset += countRead;
            remaining -= countRead;
        } while (remaining > 0);
        int messageLength = ((buffer[0] & UnsignedBytes.MAX_VALUE) << 24) | ((buffer[1] & UnsignedBytes.MAX_VALUE) << 16) | ((buffer[2] & UnsignedBytes.MAX_VALUE) << 8) | (buffer[3] & UnsignedBytes.MAX_VALUE);
        Log.e(TAG, "Message length found to be: " + messageLength);
        int offset2 = 0;
        int remaining2 = messageLength;
        do {
            int countRead2 = is.read(buffer, offset2, remaining2);
            if (countRead2 < 0) {
                Log.e(TAG, "Hit EOS reading message.  messageLength=" + messageLength + " remaining=" + remaining2);
                return -1;
            }
            offset2 += countRead2;
            remaining2 -= countRead2;
        } while (remaining2 > 0);
        return messageLength;
    }

    void sendRilConnectMessage() {
        Handler handler = this.mSapServerMsgHandler;
        if (handler != null) {
            handler.sendEmptyMessage(1);
        }
    }

    private void sendClientMessage(SapMessage sapMsg) {
        Message newMsg = this.mSapServerMsgHandler.obtainMessage(0, sapMsg);
        this.mSapServerMsgHandler.sendMessage(newMsg);
    }

    private void sendShutdownMessage() {
        Handler handler = this.mSapServerMsgHandler;
        if (handler != null) {
            handler.sendEmptyMessage(4);
        }
    }

    private void sendRilIndMessage(SapMessage sapMsg) {
        Message newMsg = this.mSapServerMsgHandler.obtainMessage(3, sapMsg);
        this.mSapServerMsgHandler.sendMessage(newMsg);
    }
}
