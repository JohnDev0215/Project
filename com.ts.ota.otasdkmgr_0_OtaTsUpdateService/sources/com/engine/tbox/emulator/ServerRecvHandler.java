package com.engine.tbox.emulator;

import android.os.Looper;
import android.os.Message;
import com.engine.tbox.core.conn.MessageHandler;
import com.engine.tbox.core.data.CountSyncMessage;
import com.engine.tbox.core.data.fsm.Fsm;
import com.engine.tbox.core.utils.LogUtils;
import com.engine.tbox.item.MessageConstants;
import com.engine.tbox.item.MessageUtils;
import com.engine.tbox.item.TboxMessage;
import com.engine.tbox.item.TboxParser;
import com.engine.tbox.utils.RSAUtils;
import com.engine.tbox.utils.RsaKeyCallback;
import java.nio.ByteBuffer;
import java.nio.charset.StandardCharsets;
import java.security.NoSuchAlgorithmException;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.util.Arrays;
import java.util.Base64;

/* JADX INFO: loaded from: classes.dex */
public class ServerRecvHandler extends MessageHandler {
    private static final long DELAY_TIME = 1000;
    private static final String END_STRING = "-----END PUBLIC KEY-----";
    private static final int INT_TRANS = 255;
    private static final int MAX_KEY_LENGTH = 24;
    private static final int MAX_PROCESS = 100;
    private static final byte[] PACKAGE_01 = {5, 1, 2, 3, 1, 98, 5, 1, 95, 0, 1, 5, 8, 0, 1, 2, 4, 1, 3, 3, 0, 1, 85, 88, 51, 50, 74, 101, 88, 65, 117, 70, 89, 87, 53, 66, 53, 90, 73, 110, 65, 65, 119, 90, 112, 77, 30, 111, 76, 65, 66, 108, 102, 71, 54, 75, 114, 80, 105, 115, 75, 106, 103, 73, 120, 56, 57, 87, 74, 89, 118, 117, 114, 55, 86, 98, 70, 10, 49, 118, 74, 56, 115, 67, 116, 86, 48, 108, 1, 99, 12, 31, 1, 1, 1, 51, 52, 53, 54, 55, 56, 57, 58, 1};
    private static final byte[] PACKAGE_02 = {5, 2, 2, 3, 1, 97, 2, 95, 1, 1, 5, 8, 0, 1, 2, 4, 1, 3, 3, 1, 2, 85, 88, 51, 50, 74, 101, 88, 65, 117, 70, 89, 87, 53, 66, 53, 90, 73, 110, 65, 65, 119, 90, 112, 77, 30, 117, 89, 87, 53, 53, 86, 79, 77, 100, 55, 48, 85, 78, 79, 99, 121, 49, 108, 86, 115, 66, 70, 110, 77, 85, 74, 80, 108, 56, 78, 10, 104, 100, 122, 70, 98, 68, 104, 76, 72, 50, 1, 99, 12, 31, 2, 1, 1, 51, 52, 53, 54, 55, 56, 57, 58, 1};
    private static final byte[] PACKAGE_03 = {5, 3, 2, 3, 1, 97, 3, 95, 2, 1, 5, 8, 0, 1, 2, 4, 1, 3, 3, 2, 3, 85, 88, 51, 50, 74, 101, 88, 65, 117, 70, 89, 87, 53, 66, 53, 90, 73, 110, 65, 65, 119, 90, 112, 77, 30, 89, 106, 118, 103, 119, 72, 57, 49, 73, 74, 90, 97, 120, 68, 83, 79, 110, 109, 81, 88, 54, 112, 101, 53, 77, 115, 50, 99, 100, 102, 10, 100, 88, 57, 112, 101, 102, 66, 104, 115, 120, 1, 99, 12, 31, 3, 1, 1, 51, 52, 53, 54, 55, 56, 57, 58, 1};
    private static final byte[] PACKAGE_04 = {5, 4, 2, 3, 1, 97, 4, 95, 3, 1, 5, 8, 0, 1, 2, 4, 1, 3, 3, 3, 4, 85, 88, 51, 50, 74, 101, 88, 65, 117, 70, 89, 87, 53, 66, 53, 90, 73, 110, 65, 65, 119, 90, 112, 77, 30, 98, 80, 75, 83, 51, 65, 67, 103, 115, 113, 85, 121, 106, 73, 89, 82, 120, 119, 52, 69, 79, 90, 109, 84, 122, 72, 102, 76, 101, 88, 10, 98, 115, 102, 79, 86, 87, 89, 79, 55, 114, 1, 99, 12, 31, 4, 1, 1, 51, 52, 53, 54, 55, 56, 57, 58, 1};
    private static final byte[] PACKAGE_05 = {5, 5, 2, 3, 1, 97, 5, 95, 4, 1, 5, 8, 0, 1, 2, 4, 1, 3, 3, 4, 5, 85, 88, 51, 50, 74, 101, 88, 65, 117, 70, 89, 87, 53, 66, 53, 90, 73, 110, 65, 65, 119, 90, 112, 77, 30, 110, 67, 108, 76, 65, 80, 68, 50, 83, 119, 69, 48, 52, 51, 49, 105, 85, 98, 73, 113, 106, 75, 107, 109, 121, 54, 57, 111, 84, 82, 10, 119, 90, 81, 97, 55, 74, 109, 117, 57, 57, 1, 99, 12, 31, 1, 1, 1, 51, 52, 53, 54, 55, 56, 57, 58, 1};
    public static final int RECVE_BYTE_BUFF = 1;
    private static final int START_RECV = 2;
    private static final String START_STRING = "-----BEGIN PUBLIC KEY-----";
    private static final int STOP_RECV = 3;
    private static final String TAG = "TBoxServerRecvHandler";
    public static final String TBOX_STRING = "NEUSOFT-TBOX-BYD";
    private static final int TEMP_SLEEP_TIME = 10;
    private boolean isAuthed;
    private PrivateKey mRsaKey;
    private final TboxParser parser;

    public ServerRecvHandler(Looper looper) {
        super(looper);
        TboxParser tboxParser = new TboxParser("ServerRecvHandler");
        this.parser = tboxParser;
        this.mRsaKey = null;
        this.isAuthed = false;
        tboxParser.registerMessageReady(new Fsm.OnMessageReady() { // from class: com.engine.tbox.emulator.ServerRecvHandler.1
            @Override // com.engine.tbox.core.data.fsm.Fsm.OnMessageReady
            public void onMessageReady(CountSyncMessage message) {
                LogUtils.d(ServerRecvHandler.TAG, "server onMessageReady message:" + message, new Object[0]);
                if (!MessageConstants.isResAckMessage(message.identify())) {
                    LogUtils.d(ServerRecvHandler.TAG, "server send ACK message:" + message.identify(), new Object[0]);
                    ServerRecvHandler.this.mConn.sendAsyncMessage(MessageUtils.buildResAckMessage());
                    Message message1 = ServerRecvHandler.this.obtainMessage();
                    message1.what = message.identify();
                    message1.obj = message;
                    ServerRecvHandler.this.sendMessage(message1);
                    return;
                }
                LogUtils.d(ServerRecvHandler.TAG, "server recv the res ack message", new Object[0]);
            }

            @Override // com.engine.tbox.core.data.fsm.Fsm.OnMessageReady
            public void onErrorCmdMessage(int cmd) {
                LogUtils.d(ServerRecvHandler.TAG, "server onError cmd message:" + Integer.toHexString(cmd), new Object[0]);
                byte[] data = {2};
                ServerRecvHandler.this.mConn.sendAsyncMessage(MessageUtils.buildResNAckMessage(ServerRecvHandler.this.encrypted(data)));
            }

            @Override // com.engine.tbox.core.data.fsm.Fsm.OnMessageReady
            public void onErrorCrcMessage(int crc) {
                LogUtils.d(ServerRecvHandler.TAG, "server onError crc message:" + Integer.toHexString(crc), new Object[0]);
                byte[] data = {1};
                ServerRecvHandler.this.mConn.sendAsyncMessage(MessageUtils.buildResNAckMessage(ServerRecvHandler.this.encrypted(data)));
            }
        });
    }

    public void start() {
        sendEmptyMessage(2);
    }

    public void stop() {
        sendEmptyMessage(3);
    }

    private boolean handleOtherMessage(Message msg) {
        int i = msg.what;
        if (i == 1) {
            ByteBuffer buff = (ByteBuffer) msg.obj;
            this.parser.parseMsgFromBuffer(buff);
        } else if (i == 2) {
            this.parser.start(TAG);
        } else if (i == 3) {
            this.parser.stop();
        } else {
            return false;
        }
        return true;
    }

    private boolean handleMessagePart01(Message msg) {
        int i = msg.what;
        if (i == 273) {
            LogUtils.d(TAG, "IVI_TBOX_RES_ACK receive", new Object[0]);
            return true;
        }
        if (i == 274) {
            LogUtils.d(TAG, "IVI_TBOX_RES_NACK receive", new Object[0]);
            return true;
        }
        if (i == 529) {
            sendHeartBeat();
            return true;
        }
        if (i == 1041) {
            sendStaticTboxInfo();
            return true;
        }
        if (i == 3345) {
            handleVirtualVinReq();
            return true;
        }
        if (i == 3601) {
            handleActiveCodeRsp();
            return true;
        }
        if (i == 3857) {
            handleTboxVersionRsp();
            return true;
        }
        if (i == 4113) {
            handleCloudConnectRsp();
            return true;
        }
        if (i == 4369) {
            handleSwitchEnvRsp();
            return true;
        }
        if (i == 4625) {
            handleGetEnvRsp();
            return true;
        }
        if (i == 7441) {
            handleNfcReq((TboxMessage) msg.obj);
            return true;
        }
        if (i == 1809) {
            sendUpgradeRsp();
            return true;
        }
        if (i == 1810) {
            startUpgrade((TboxMessage) msg.obj);
            return true;
        }
        if (i == 2065) {
            sendExportLogRsp();
            return true;
        }
        if (i == 2066) {
            handleLogExportResult((TboxMessage) msg.obj);
            return true;
        }
        switch (i) {
            case MessageConstants.IVI_TBOX_EXCHANGE_OFFER /* 785 */:
                encryptHello(msg);
                return true;
            case MessageConstants.IVI_TBOX_EXCHANGE_REQ /* 786 */:
                exchangeInit();
                return true;
            case MessageConstants.IVI_TBOX_EXCHANGE_RESULT /* 787 */:
                handleExchangeResult((TboxMessage) msg.obj);
                return true;
            default:
                LogUtils.e(TAG, "invalid message :" + msg.what, new Object[0]);
                return false;
        }
    }

    private void handleVirtualVinReq() {
        byte[] data = "test00000000000001".getBytes(StandardCharsets.UTF_8);
        this.mConn.sendAsyncMessage(MessageUtils.buildServeGetVirtualVinReq(data));
    }

    private void handleNfcReq(TboxMessage obj) {
        byte[] data = obj.getData();
        if (data.length < 1) {
            LogUtils.e(TAG, "error msg length:" + data.length, new Object[0]);
        }
        int cmd = data[0] & 255;
        switch (cmd) {
            case 1:
                handleDeleteSignalKey(data);
                break;
            case 2:
                handleDeleteAllIphoneKey(data);
                break;
            case 3:
                handleQueryAllKey(data);
                break;
            case 4:
                handleChangeIphoneDevice(data);
                break;
            case 5:
                handleRegisterKey(data);
                break;
            case 6:
                handleDeleteAllKey(data);
                break;
        }
    }

    private void handleDeleteAllKey(byte[] data) {
        LogUtils.e(TAG, "handleDeleteAllKey:", new Object[0]);
    }

    private void handleRegisterKey(byte[] data) {
        LogUtils.i(TAG, "handleRegisterKey", new Object[0]);
    }

    private void handleChangeIphoneDevice(byte[] data) {
        LogUtils.i(TAG, "handleChangeIphoneDevice:" + Arrays.toString(data), new Object[0]);
        byte[] key = new byte[data.length - 1];
        System.arraycopy(data, 1, key, 0, key.length);
        if (key.length > 24 || key.length == 0) {
            LogUtils.e(TAG, "Delete the key error", new Object[0]);
            return;
        }
        LogUtils.i(TAG, "change the iphoneKey:" + new String(key, StandardCharsets.UTF_8).trim() + " array:" + Arrays.toString(key), new Object[0]);
        byte[] rsp = new byte[key.length + 2 + 1];
        rsp[0] = 4;
        rsp[1] = (byte) (key.length + 1);
        System.arraycopy(key, 0, rsp, 2, key.length);
        rsp[rsp.length - 1] = 1;
        this.mConn.sendAsyncMessage(MessageUtils.buildNfcDeleteRsp(rsp));
    }

    private void handleQueryAllKey(byte[] data) {
        LogUtils.i(TAG, "handleQueryAllKey:" + data, new Object[0]);
        this.mConn.sendAsyncMessage(MessageUtils.buildNfcRsp(PACKAGE_01));
        this.mConn.sendAsyncMessage(MessageUtils.buildNfcRsp(PACKAGE_02));
        this.mConn.sendAsyncMessage(MessageUtils.buildNfcRsp(PACKAGE_03));
        this.mConn.sendAsyncMessage(MessageUtils.buildNfcRsp(PACKAGE_04));
        this.mConn.sendAsyncMessage(MessageUtils.buildNfcRsp(PACKAGE_05));
    }

    private void handleDeleteAllIphoneKey(byte[] data) {
        LogUtils.i(TAG, "data:" + Arrays.toString(data), new Object[0]);
        byte[] rsp = new byte[3];
        int pos = 0 + 1;
        rsp[0] = 2;
        rsp[pos] = 1;
        rsp[pos + 1] = 1;
        this.mConn.sendAsyncMessage(MessageUtils.buildNfcDeleteRsp(rsp));
    }

    private void handleDeleteSignalKey(byte[] data) {
        LogUtils.i(TAG, "handleDeleteSignalKey:" + Arrays.toString(data), new Object[0]);
        byte[] key = new byte[data.length - 1];
        System.arraycopy(data, 1, key, 0, key.length);
        if (key.length > 24 || key.length == 0) {
            LogUtils.e(TAG, "Delete the key error", new Object[0]);
            return;
        }
        LogUtils.d(TAG, "delete single iphoneKey:" + new String(key, StandardCharsets.UTF_8).trim() + " array:" + Arrays.toString(key), new Object[0]);
        byte[] rsp = new byte[key.length + 2 + 1];
        rsp[0] = 1;
        rsp[1] = (byte) (key.length + 1);
        System.arraycopy(key, 0, rsp, 2, key.length);
        rsp[rsp.length - 1] = 1;
        this.mConn.sendAsyncMessage(MessageUtils.buildNfcDeleteRsp(rsp));
    }

    private boolean handleMessagePart02(Message msg) {
        if (msg.what != 4881) {
            LogUtils.e(TAG, "invalid message :" + msg.what, new Object[0]);
            return false;
        }
        return true;
    }

    private void handleGetEnvRsp() {
        LogUtils.d(TAG, "get tbox cloud switch rsp", new Object[0]);
        byte[] data = {1};
        this.mConn.sendAsyncMessage(MessageUtils.buildServiceCloudEnvRsp(data));
    }

    private void handleSwitchEnvRsp() {
        LogUtils.d(TAG, "send tbox cloud switch rsp", new Object[0]);
        byte[] data = {1};
        this.mConn.sendAsyncMessage(MessageUtils.buildServiceSwitchEnvRsp(data));
    }

    private void handleCloudConnectRsp() {
        LogUtils.d(TAG, "send tbox cloud connect rsp", new Object[0]);
        byte[] data = {1};
        this.mConn.sendAsyncMessage(MessageUtils.buildServiceConnectRsp(data));
    }

    private void handleTboxVersionRsp() {
        LogUtils.d(TAG, "send tbox version rsp", new Object[0]);
        this.mConn.sendAsyncMessage(MessageUtils.buildServerTBoxVersionRsp("TBOXV1.1.2".getBytes()));
    }

    private void handleActiveCodeRsp() {
        sendActiveCodeRsp();
    }

    private void handleExchangeResult(TboxMessage msg) {
        byte[] data = msg.getData();
        if (data.length == 1) {
            this.isAuthed = data[0] == 0;
        }
        sendExchangeAck();
    }

    @Override // com.engine.tbox.core.conn.MessageHandler, android.os.Handler
    public void handleMessage(Message msg) {
        LogUtils.d(TAG, "ServerRecvHandler handle message what:0x" + Integer.toHexString(msg.what), new Object[0]);
        boolean handle = handleOtherMessage(msg) || handleMessagePart01(msg) || handleMessagePart02(msg);
        LogUtils.d(TAG, "message have been handle:" + handle, new Object[0]);
    }

    private void encryptHello(Message msg) {
        TboxMessage message = (TboxMessage) msg.obj;
        byte[] aesEncryptKey = message.getData();
        LogUtils.d(TAG, "encryptHello aesEncryptKey:" + Arrays.toString(aesEncryptKey), new Object[0]);
    }

    private void exchangeInit() {
        try {
            RSAUtils.generatorKeyPair(new RsaKeyCallback() { // from class: com.engine.tbox.emulator.ServerRecvHandler.2
                @Override // com.engine.tbox.utils.RsaKeyCallback
                public void generatorKey(PublicKey publicKey, PrivateKey privateKey) {
                    LogUtils.d(ServerRecvHandler.TAG, "genaratorKey:" + publicKey + " private:" + privateKey, new Object[0]);
                    ServerRecvHandler.this.mRsaKey = privateKey;
                    byte[] encoded = publicKey.getEncoded();
                    String str = ServerRecvHandler.START_STRING + new String(Base64.getEncoder().encode(encoded), StandardCharsets.UTF_8).trim() + ServerRecvHandler.END_STRING;
                    byte[] encoded2 = str.getBytes(StandardCharsets.UTF_8);
                    LogUtils.d(ServerRecvHandler.TAG, "key:" + encoded2.length, new Object[0]);
                    ServerRecvHandler.this.sendExchangeInit(encoded2);
                }
            });
        } catch (NoSuchAlgorithmException exp) {
            exp.printStackTrace();
        }
    }

    private void handleLogExportResult(TboxMessage msg) {
        LogUtils.d(TAG, "export nty result", new Object[0]);
        byte[] data = msg.getData();
        StringBuilder sb = new StringBuilder();
        sb.append("export nty result:");
        sb.append(data[0] == 0);
        LogUtils.d(TAG, sb.toString(), new Object[0]);
    }

    private void sendExportLogRsp() {
        LogUtils.d(TAG, "sendExportLogRsp", new Object[0]);
        byte[] data = MessageUtils.prepareServerUpgradeResponseData();
        this.mConn.sendAsyncMessage(MessageUtils.buildServerExportLogResponseMessage(encrypted(data)));
    }

    /* JADX WARN: Type inference failed for: r0v2, types: [com.engine.tbox.emulator.ServerRecvHandler$3] */
    private void startUpgrade(TboxMessage message) {
        LogUtils.d(TAG, "startUpgrade", new Object[0]);
        byte[] data = message.getData();
        boolean upgrade = data[0] == 0;
        LogUtils.d(TAG, "server startUpgrade:" + upgrade, new Object[0]);
        if (upgrade) {
            new Thread() { // from class: com.engine.tbox.emulator.ServerRecvHandler.3
                @Override // java.lang.Thread, java.lang.Runnable
                public void run() {
                    try {
                        Thread.sleep(ServerRecvHandler.DELAY_TIME);
                    } catch (InterruptedException exp) {
                        exp.printStackTrace();
                    }
                    for (int i = 0; i <= 100; i++) {
                        try {
                            Thread.sleep(10L);
                        } catch (InterruptedException exp2) {
                            exp2.printStackTrace();
                        }
                        byte[] data2 = {(byte) i};
                        ServerRecvHandler.this.mConn.sendAsyncMessage(MessageUtils.buildServerUpgradeProcessMessage(ServerRecvHandler.this.encrypted(data2)));
                    }
                    byte[] data3 = {0};
                    ServerRecvHandler.this.mConn.sendAsyncMessage(MessageUtils.buildServerUpgradeResult(ServerRecvHandler.this.encrypted(data3)));
                }
            }.start();
        }
    }

    private void sendUpgradeRsp() {
        LogUtils.d(TAG, "send upgrade response", new Object[0]);
        byte[] bytes = MessageUtils.prepareServerUpgradeResponseData();
        this.mConn.sendAsyncMessage(MessageUtils.buildServerUpgradeResponseMessage(encrypted(bytes)));
    }

    public void sendHeartBeat() {
        LogUtils.d(TAG, "send heart beat", new Object[0]);
        TboxMessage message = MessageUtils.buildLServerHeartbeatMessage();
        this.mConn.sendAsyncMessage(message);
    }

    public void sendStaticTboxInfo() {
        LogUtils.d(TAG, "send static info", new Object[0]);
        byte[] bytes = MessageUtils.prepareServerTBoxStaticInfo();
        TboxMessage message = MessageUtils.buildServerTBoxStaticInfoResponseMessage(encrypted(bytes));
        this.mConn.sendAsyncMessage(message);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void sendExchangeInit(byte[] rsaPublicKey) {
        LogUtils.d(TAG, "sendExchangeInit" + Arrays.toString(rsaPublicKey), new Object[0]);
        TboxMessage message = MessageUtils.buildTBoxMessageExChangeInit(rsaPublicKey);
        LogUtils.d(TAG, "send tbox message:" + message, new Object[0]);
        this.mConn.sendAsyncMessage(message);
    }

    private void sendExchangeRes(byte[] data) {
        LogUtils.d(TAG, "sendExchangeRes" + Arrays.toString(data), new Object[0]);
        TboxMessage message = MessageUtils.buildTBoxMessageExChangeRes(data);
        LogUtils.d(TAG, "send tbox message:" + message, new Object[0]);
        this.mConn.sendAsyncMessage(message);
    }

    private void sendExchangeAck() {
        LogUtils.d(TAG, "sendExchangeAck", new Object[0]);
        TboxMessage message = MessageUtils.buildTBoxMessageExChangeAck();
        LogUtils.d(TAG, "send tbox message:" + message, new Object[0]);
        this.mConn.sendAsyncMessage(message);
    }

    private void sendActiveCodeRsp() {
        LogUtils.d(TAG, "sendActiveCodeRsp", new Object[0]);
        byte[] data = {1, 2};
        TboxMessage message = MessageUtils.buildServeActiveCodeRsp(data);
        this.mConn.sendAsyncMessage(message);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public byte[] encrypted(byte[] data) {
        return data;
    }

    private byte[] decrypted(byte[] data) {
        return data;
    }
}
