package com.engine.tbox.item;

import android.os.Looper;
import android.os.Message;
import android.os.SystemProperties;
import android.text.TextUtils;
import com.engine.tbox.core.conn.MessageHandler;
import com.engine.tbox.core.data.CountSyncMessage;
import com.engine.tbox.core.data.fsm.Fsm;
import com.engine.tbox.core.utils.LogUtils;
import com.engine.tbox.core.utils.TypeUtils;
import com.engine.tbox.data.ActiveCode;
import com.engine.tbox.data.ActiveCodeV2;
import com.engine.tbox.data.ApnArgs;
import com.engine.tbox.data.ApnInfo;
import com.engine.tbox.data.DigitalKeyBean;
import com.engine.tbox.data.NetworkFlow;
import com.engine.tbox.data.NetworkOperator;
import com.engine.tbox.data.OtaNotification;
import com.engine.tbox.data.ReservationCharging;
import java.nio.ByteBuffer;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Arrays;

/* JADX INFO: loaded from: classes.dex */
public class RecvMessageHandler extends MessageHandler {
    private static final String COUNTRY_PROP = "sys.byd.countrycode";
    private static final String DATA_CENTER_SIMPLE_CODE = "persist.sys.area_prefix";
    private static final int DEFAULT_COUNTRY_CODE = 47;
    private static final int DELAYE_TIME = 5000;
    public static final int DELETE_742_MIN_LENGTH = 2;
    private static final int FTP_FAILED = 1;
    private static final int FTP_FILE_CHECK_FAILED = 2;
    public static final int KEY_BIND_LENGTH = 1;
    public static final int KEY_DATA_LENGTH = 1;
    public static final int KEY_DEADLINE_LENGTH = 3;
    public static final int KEY_DEVICE_LEN = 1;
    public static final int KEY_DEVICE_MAX_LENGTH = 10;
    public static final int KEY_ENTITY_TYPE_LENGTH = 1;
    public static final int KEY_ID_LENGTH = 8;
    public static final int KEY_NAME_LEN = 1;
    public static final int KEY_NAME_MAX_LENGTH = 30;
    public static final int KEY_PASS_ID_LENGTH = 24;
    public static final int KEY_PERMISSION_LENGTH = 1;
    public static final int KEY_SEQ_LENGTH = 1;
    public static final int KEY_STATUS_LENGTH = 1;
    public static final int KEY_TYPE_LENGTH = 1;
    public static final int KEY_UWB_LENGTH = 1;
    private static final int MAX_COUSTOM = 255;
    private static final int MAX_RESTART_RESEND_TIMES = 2;
    private static final int MCCMNC_LENGTH = 5;
    private static final int MEM_K = 1024;
    private static final int MIN_NFC_LENGTH = 7;
    public static final int MIN_NITZ_DATA_LEN = 9;
    public static final int MSG_ACTIVE_CODE2_TIMEOUT = 8;
    public static final int MSG_ACTIVE_CODE_TIMEOUT = 4;
    public static final int MSG_APN_RESULT = 15;
    public static final int MSG_CLOUD_CONNECT_STATE = 14;
    public static final int MSG_NFC_DELETE_ALL = 10;
    public static final int MSG_NFC_DELETE_CHANGE_IPHONE = 13;
    public static final int MSG_NFC_DELETE_IPHONE_KEY = 11;
    public static final int MSG_NFC_DELETE_SINGLE_KEY = 12;
    public static final int MSG_NITZ_TIMEOUT = 9;
    public static final int MSG_QUERY_TRAFFIC = 6;
    public static final int MSG_RECV_BYTE_BUFF = 1;
    public static final int MSG_RESET_TRAFFIC = 7;
    public static final int MSG_RESTART_TIMEOUT = 5;
    private static final int MSG_START_RECV = 2;
    private static final int MSG_STOP_RECV = 3;
    private static final int NETWORK_OP_LENGTH = 8;
    private static final int NFC_BUFFER_SIZE = 8192;
    public static final int NFC_REMOTE_TIMEOUT = 5000;
    public static final int NITZ_TIMEOUT = 5000;
    public static final int RESTART_TIMEOUT = 3000;
    private static final String RUDDER_PROP = "persist.vendor.byd.cfg.huc.steering.wheel.position";
    private static final String SN_PROP = "persist.vendor.bosch.cfg.diag.sn";
    private static final String TAG = "TBoxRecvMessageHandler";
    private static final int UPGRADE_FAILED = 3;
    private static final int UPGRADE_SUCCESS = 0;
    public static final int USER_ACCOUNT_LENGTH = 11;
    public static final int USER_PROP_LENGTH = 1;
    private static final String VERSION_PROP = "ro.product.version.soc.name";
    private static final String VIRTUAL_VIN_PROP = "persist.sys.cloud_virtual_vin";
    private MessageCache mCache;
    private TBoxConnection mConnection;
    private ByteBuffer mNfcRspBuffer;
    private final TboxParser mParser;
    private int mRecvPackageCount;

    public void addTBoxConnection(TBoxConnection connection) {
        this.mConnection = connection;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void handleAuthFailedAck() {
        LogUtils.e(TAG, "handleAuthFailedAck", new Object[0]);
        byte[] data = {3};
        this.mConnection.sendAsyncMessage(MessageUtils.buildResNAckMessage(data));
    }

    public RecvMessageHandler(Looper looper) {
        super(looper);
        this.mNfcRspBuffer = ByteBuffer.allocate(NFC_BUFFER_SIZE);
        this.mRecvPackageCount = 0;
        TboxParser tboxParser = new TboxParser("TBoxClientParese");
        this.mParser = tboxParser;
        tboxParser.registerMessageReady(new Fsm.OnMessageReady() { // from class: com.engine.tbox.item.RecvMessageHandler.1
            @Override // com.engine.tbox.core.data.fsm.Fsm.OnMessageReady
            public void onMessageReady(CountSyncMessage message) {
                LogUtils.i(RecvMessageHandler.TAG, "onMessageReady message:" + message, new Object[0]);
                if (RecvMessageHandler.this.mConnection.isAuthEnable() && MessageConstants.recvNeedDeCrypt(message.identify())) {
                    if (!RecvMessageHandler.this.mConnection.authDecrypt((TboxMessage) message)) {
                        LogUtils.e(RecvMessageHandler.TAG, "decrypt failed msg:" + message, new Object[0]);
                        RecvMessageHandler.this.handleAuthFailedAck();
                        RecvMessageHandler.this.mConnection.recvMessageDescryptedFailed();
                        return;
                    }
                    LogUtils.d(RecvMessageHandler.TAG, "after authDecrypt message:" + message, new Object[0]);
                }
                if (!MessageConstants.isResAckMessage(message.identify())) {
                    LogUtils.d(RecvMessageHandler.TAG, "client send ACK message:0x" + Integer.toHexString(message.identify()), new Object[0]);
                    RecvMessageHandler.this.mConnection.sendAsyncMessage(MessageUtils.buildResAckMessage());
                    int msg = MessageConstants.isSyncMessage(message.identify());
                    if (msg != 0) {
                        if (RecvMessageHandler.this.mCache != null) {
                            LogUtils.d(RecvMessageHandler.TAG, "send and notify the msg:0x" + Integer.toHexString(msg), new Object[0]);
                            TboxMessage request = RecvMessageHandler.this.mCache.removeMessage(msg);
                            if (request != null) {
                                LogUtils.d(RecvMessageHandler.TAG, "request:" + request, new Object[0]);
                                request.addResponse(message);
                                request.notifyTheSignal();
                                return;
                            }
                            LogUtils.e(RecvMessageHandler.TAG, "request is null for cmd:0x" + Integer.toHexString(msg), new Object[0]);
                            return;
                        }
                        return;
                    }
                    Message message1 = RecvMessageHandler.this.obtainMessage();
                    message1.what = message.identify();
                    message1.obj = message;
                    RecvMessageHandler.this.sendMessage(message1);
                    return;
                }
                LogUtils.d(RecvMessageHandler.TAG, "client recv the res ack message", new Object[0]);
                RecvMessageHandler.this.mConn.resetHeartBeatCount();
            }

            @Override // com.engine.tbox.core.data.fsm.Fsm.OnMessageReady
            public void onErrorCmdMessage(int cmd) {
                LogUtils.d(RecvMessageHandler.TAG, "error cmd message:" + Integer.toHexString(cmd), new Object[0]);
                byte[] data = {1};
                RecvMessageHandler.this.mConnection.sendAsyncMessage(MessageUtils.buildResNAckMessage(data));
            }

            @Override // com.engine.tbox.core.data.fsm.Fsm.OnMessageReady
            public void onErrorCrcMessage(int crc) {
                LogUtils.d(RecvMessageHandler.TAG, "error cmd message:" + Integer.toHexString(crc), new Object[0]);
                byte[] data = {1};
                RecvMessageHandler.this.mConnection.sendAsyncMessage(MessageUtils.buildResNAckMessage(data));
            }
        });
    }

    public void addMessageCache(MessageCache cache) {
        this.mCache = cache;
    }

    public void handleStreamMessage(Message msg) {
        LogUtils.v(TAG, "handleStreamMessage message", new Object[0]);
        ByteBuffer buffer = (ByteBuffer) msg.obj;
        this.mParser.parseMsgFromBuffer(buffer);
    }

    public void start() {
        sendEmptyMessage(2);
    }

    public void stop() {
        sendEmptyMessage(3);
    }

    @Override // com.engine.tbox.core.conn.MessageHandler, android.os.Handler
    public void handleMessage(Message msg) {
        LogUtils.d(TAG, "RecvMessageHandler handleMessage:0x" + Integer.toHexString(msg.what), new Object[0]);
        if (!handleControlMessage(msg)) {
            if (handleTboxRsp(msg) || handleTboxNotify(msg) || handleAuthMessage(msg) || handleChargeMessage(msg) || handleNfcMessage(msg) || handleTboxReq(msg) || handleBindCloudMsg(msg) || handleNetworkMsg(msg) || handleApnMessage(msg)) {
                LogUtils.d(TAG, "handleMessage:0x" + Integer.toHexString(msg.what), new Object[0]);
                return;
            }
            LogUtils.e(TAG, "invalid message:0x" + Integer.toHexString(msg.what), new Object[0]);
        }
    }

    private boolean handleNetworkMsg(Message msg) {
        int i = msg.what;
        if (i == 6658) {
            handleModeNotify((TboxMessage) msg.obj);
            return true;
        }
        if (i == 6659) {
            handleAutoNetSetNty((TboxMessage) msg.obj);
            return true;
        }
        if (i == 6913) {
            handleSelectManualNetNty((TboxMessage) msg.obj);
            return true;
        }
        if (i == 7939) {
            handleSimNty((TboxMessage) msg.obj);
            return true;
        }
        return false;
    }

    private void handleApnArgsResult(TboxMessage msg) {
        LogUtils.d(TAG, "handleApnArgsResult:" + msg, new Object[0]);
        byte[] data = msg.getData();
        if (data.length > 1) {
            int result = data[0];
            removeMessages(15);
            this.mConnection.onSendApnArgsResult(result);
            return;
        }
        LogUtils.e(TAG, "handleApnArgsResult with error message", new Object[0]);
    }

    private boolean handleApnMessage(Message msg) {
        int i = msg.what;
        if (i == 1284) {
            handleApnArgsResult((TboxMessage) msg.obj);
            return true;
        }
        if (i == 1285) {
            handleApnArgRsp((TboxMessage) msg.obj);
            return true;
        }
        if (i == 1287) {
            handleNetworkFlow((TboxMessage) msg.obj);
            return true;
        }
        if (i == 8198) {
            handleApnStateRsp((TboxMessage) msg.obj);
            return true;
        }
        return false;
    }

    private void handleApnStateRsp(TboxMessage obj) {
        LogUtils.d(TAG, "handleApnStateRsp:" + obj, new Object[0]);
        byte[] data = obj.getData();
        if (data.length < 17) {
            LogUtils.e(TAG, "error message data length:" + data.length + " data:" + Arrays.toString(data), new Object[0]);
            return;
        }
        ByteBuffer buf = ByteBuffer.wrap(data);
        ApnInfo apnInfo = new ApnInfo();
        int apn = buf.get();
        apnInfo.setApn(apn);
        int state = buf.get();
        apnInfo.setApConnectState(state);
        int result = buf.get();
        apnInfo.setResult(result);
        int ip = buf.getInt();
        apnInfo.setIp(ip);
        buf.getInt();
        buf.getInt();
        int code = buf.get();
        apnInfo.setErrorCode(code);
        String uuid = new String(data, 17, data.length - 17, StandardCharsets.UTF_8).trim();
        apnInfo.setUuid(uuid);
        LogUtils.i(TAG, "getApn1Info:" + apnInfo, new Object[0]);
        this.mConnection.onApnState(apnInfo);
    }

    private void handleApnArgRsp(TboxMessage obj) {
        LogUtils.d(TAG, "handleApnArgRsp:" + obj, new Object[0]);
        byte[] data = obj.getData();
        ApnArgs args = ApnArgs.bytesToApnArgs(data);
        if (args == null) {
            LogUtils.e(TAG, "handleApnArgRsp error:" + obj, new Object[0]);
            return;
        }
        this.mConnection.onApnArgsNty(args);
    }

    private void handleNetworkFlow(TboxMessage obj) {
        LogUtils.d(TAG, "handleNetworkFlow:" + obj, new Object[0]);
        byte[] data = obj.getData();
        NetworkFlow flow = NetworkFlow.fromByteArray(data);
        if (flow == null) {
            LogUtils.e(TAG, "handleNetworkFlow error:" + obj, new Object[0]);
            return;
        }
        this.mConnection.onNetworkFlowNty(flow);
    }

    private boolean handleModeNotify(TboxMessage msg) {
        LogUtils.d(TAG, "handleModeNotify:" + msg, new Object[0]);
        byte[] bytes = msg.getData();
        if (bytes.length != 1) {
            LogUtils.e(TAG, "handleModeNotify length error:" + msg, new Object[0]);
            return false;
        }
        int mode = bytes[0];
        this.mConnection.notifyAutoModeChanged(mode);
        return true;
    }

    private boolean handleSelectManualNetNty(TboxMessage msg) {
        LogUtils.d(TAG, "handleSelectManualNetNty:" + msg, new Object[0]);
        byte[] bytes = msg.getData();
        if (bytes.length < 15) {
            LogUtils.e(TAG, "handleSelectManualNetNty msg:" + msg + " length less than 15", new Object[0]);
            return false;
        }
        byte[] data = new byte[6];
        System.arraycopy(bytes, 0, data, 0, 6);
        String mccMnc = new String(data, StandardCharsets.UTF_8).trim();
        int pos = 0 + 6;
        int pos2 = pos + 1;
        int curNetType = bytes[pos];
        int pos3 = pos2 + 1;
        int state = bytes[pos2];
        System.arraycopy(bytes, pos3, data, 0, 6);
        int pos4 = pos3 + 6;
        String lastMccMnc = new String(data, StandardCharsets.UTF_8).trim();
        int i = pos4 + 1;
        int lastNetType = bytes[pos4];
        this.mConnection.onManuallyStationedNetworkChange(mccMnc, curNetType, state, lastMccMnc, lastNetType);
        return true;
    }

    private boolean handleAutoNetSetNty(TboxMessage msg) {
        LogUtils.d(TAG, "handleAutoNetSetNty:" + msg, new Object[0]);
        byte[] bytes = msg.getData();
        if (bytes.length < 1) {
            LogUtils.e(TAG, "handleAutoNetSetNty msg:" + msg + " length less than 1", new Object[0]);
            return false;
        }
        int result = bytes[0];
        this.mConnection.onAutomaticNetworkChange(result);
        return true;
    }

    private boolean handleSimNty(TboxMessage msg) {
        LogUtils.d(TAG, "handleSimNty:" + msg, new Object[0]);
        byte[] bytes = msg.getData();
        if (bytes.length < 1) {
            LogUtils.e(TAG, "handleSimNty msg:" + msg + " length less than 1", new Object[0]);
            return false;
        }
        int sim = bytes[0];
        this.mConnection.onSimCardStateChange(sim);
        return true;
    }

    private boolean handleBindCloudMsg(Message msg) {
        if (msg.what == 8199) {
            handleBindRsp((TboxMessage) msg.obj);
            return true;
        }
        if (msg.what == 8200) {
            handleBindInfo((TboxMessage) msg.obj);
            return true;
        }
        return false;
    }

    private void handleBindRsp(TboxMessage msg) {
        byte[] data = msg.getData();
        if (data.length < 3) {
            LogUtils.e(TAG, "handleBindRsp with error len msg:" + Arrays.toString(data), new Object[0]);
            return;
        }
        String url = "";
        int pos = 0 + 1;
        int bindStatus = data[0];
        int len = TypeUtils.byteArrayPosToShortInt(data, pos);
        int pos2 = pos + 2;
        if (len != 0) {
            url = new String(data, pos2, data.length - pos2, StandardCharsets.UTF_8);
        }
        LogUtils.i(TAG, "bind_status:" + bindStatus + " url:" + url, new Object[0]);
        this.mConnection.sendBindCloudRsp(bindStatus, url);
    }

    private void handleBindInfo(TboxMessage msg) {
        byte[] data = msg.getData();
        if (data.length != 1) {
            LogUtils.e(TAG, "handleBindInfo with error len msg:" + Arrays.toString(data), new Object[0]);
            return;
        }
        int bindStatus = data[0];
        this.mConnection.sendBindCloudInfo(bindStatus);
    }

    private boolean handleNfcMessage(Message msg) {
        if (msg.what == 7425) {
            handleNfcRspMessage((TboxMessage) msg.obj);
            return true;
        }
        if (msg.what == 7938) {
            handleDeleteRsp((TboxMessage) msg.obj);
            return true;
        }
        return false;
    }

    private void handleDeleteRsp(TboxMessage msg) {
        LogUtils.i(TAG, "handleDeleteRsp:" + msg, new Object[0]);
        byte[] data = msg.getData();
        if (data.length < 2) {
            LogUtils.e(TAG, "error message for handleDeleteRsp length" + data.length, new Object[0]);
            return;
        }
        int pos = 0 + 1;
        int op = data[0];
        int pos2 = pos + 1;
        int dataLen = data[pos] & 255;
        if (data.length < dataLen + 2) {
            LogUtils.e(TAG, "error message for handleDeleteRsp data length" + data.length, new Object[0]);
            return;
        }
        if (op != 1) {
            if (op == 2) {
                handleDeleteAllIphoneKey(data, pos2, dataLen);
                return;
            }
            if (op != 4) {
                if (op == 5) {
                    handleUpdateKey(data, pos2, dataLen);
                    return;
                }
                LogUtils.e(TAG, "handleDeleteRsp error for op:" + op, new Object[0]);
                return;
            }
        }
        handleDeleteSingleOrChangeIphoneKey(op, data, pos2, dataLen);
    }

    private void handleUpdateKey(byte[] data, int pos, int dataLen) {
        LogUtils.d(TAG, "handleUpdateKey ", new Object[0]);
        if (data[pos] == 0) {
            LogUtils.d(TAG, "update the key is 00", new Object[0]);
        }
        this.mConnection.onKeyListPageRefresh();
    }

    private void handleDeleteAllIphoneKey(byte[] data, int pos, int dataLen) {
        int result = data[pos];
        LogUtils.i(TAG, "handleDeleteAllIphoneKey result:" + result, new Object[0]);
        this.mConnection.onDeleteNfcCallback(2, result);
    }

    private void handleDeleteSingleOrChangeIphoneKey(int op, byte[] data, int pos, int dataLen) {
        byte[] key = new byte[dataLen - 1];
        System.arraycopy(data, pos, key, 0, dataLen - 1);
        int result = data[pos + (dataLen - 1)];
        LogUtils.i(TAG, "handleDeleteSingleOrChangeIphoneKey key:" + Arrays.toString(key) + " keyString:" + new String(key, StandardCharsets.UTF_8).trim() + " result:" + result, new Object[0]);
        this.mConnection.onDeleteNfcCallback(op, result);
    }

    private void handleNfcRspMessage(TboxMessage msg) {
        boolean handle;
        LogUtils.i(TAG, "handleNfcRspMessage:" + msg, new Object[0]);
        byte[] data = msg.getData();
        if (data.length < 7) {
            LogUtils.d(TAG, "handleNfcRspMessage error length for nfc response:" + Arrays.toString(data), new Object[0]);
            return;
        }
        int pos = 0 + 1;
        int totalPackageCount = data[0];
        int pos2 = pos + 1;
        int curPackageCount = data[pos];
        int pos3 = pos2 + 1;
        int protoVersion = data[pos2];
        int pos4 = pos3 + 1;
        int op = data[pos3];
        int pos5 = pos4 + 1;
        int result = data[pos4];
        int dataLen = TypeUtils.byteArrayPosToShortInt(data, pos5);
        int i = pos5 + 2;
        if (dataLen != data.length - 7) {
            LogUtils.e(TAG, "handleNfcRspMessage dataLens error:" + dataLen + " expect length:" + (data.length - 7), new Object[0]);
            return;
        }
        LogUtils.i(TAG, "handleNfcRspMessage total:" + totalPackageCount + " cur:" + curPackageCount + " proto:" + protoVersion + " op:" + op + " result:" + result + " dataLen:" + dataLen, new Object[0]);
        switch (op) {
            case 1:
            case 2:
            case 4:
            case 5:
            case 6:
                handle = handleOpNoData(op, result);
                break;
            case 3:
                handle = handleQueryAllKeyRsp(totalPackageCount, curPackageCount, dataLen, data);
                break;
            default:
                LogUtils.e(TAG, "handleNfcRspMessage error op type:" + op, new Object[0]);
                return;
        }
        if (handle) {
            this.mConnection.sendAsyncMessage(MessageUtils.buildNfcRecvPackage(totalPackageCount, curPackageCount));
            return;
        }
        LogUtils.e(TAG, "handleNfcRspMessage handle op:" + op + " failed data:" + Arrays.toString(data), new Object[0]);
    }

    private boolean handleOpNoData(int op, int result) {
        this.mConnection.onDeleteNfcCallback(op, result);
        return true;
    }

    private boolean handleQueryAllKeyRsp(int totalPackageCount, int curPackageCount, int dataLen, byte[] data) {
        int i = this.mRecvPackageCount;
        if (i + 1 != curPackageCount) {
            LogUtils.e(TAG, "handleQueryAllKeyRsp error package count, mRecvPackageCount:" + this.mRecvPackageCount + " curPackageCount:" + curPackageCount, new Object[0]);
            resetNfcRspBuffer();
            return false;
        }
        if (dataLen > data.length - 7) {
            resetNfcRspBuffer();
            LogUtils.e(TAG, "handleQueryAllKeyRsp dateLen:" + dataLen + " data.length:" + data.length, new Object[0]);
            return false;
        }
        this.mRecvPackageCount = i + 1;
        this.mNfcRspBuffer.put(data, 7, dataLen);
        LogUtils.i(TAG, "handleQueryAllKeyRsp recv package count:" + curPackageCount + " totalPackageCount:" + totalPackageCount + " currentDataLength:" + dataLen, new Object[0]);
        if (curPackageCount == totalPackageCount) {
            handleAllKey();
            resetNfcRspBuffer();
        }
        return true;
    }

    private void handleAllKey() {
        int keyCount;
        int keyCount2;
        int pos;
        this.mNfcRspBuffer.flip();
        int len = this.mNfcRspBuffer.limit();
        byte[] array = new byte[len];
        int i = 0;
        System.arraycopy(this.mNfcRspBuffer.array(), 0, array, 0, len);
        this.mNfcRspBuffer.clear();
        int keyCount3 = 0;
        int pos2 = 0;
        if (array.length > 1) {
            int pos3 = 0 + 1;
            keyCount3 = array[0];
            pos2 = pos3;
        }
        LogUtils.d(TAG, "KeyCount:" + keyCount3 + " len:" + len + " data:" + Arrays.toString(array), new Object[0]);
        ArrayList<DigitalKeyBean> beans = new ArrayList<>();
        int i2 = 0;
        while (true) {
            if (i2 >= keyCount3) {
                keyCount = keyCount3;
                break;
            }
            if (array.length < pos2 + 1) {
                LogUtils.e(TAG, "invalid length for " + i2 + " key seq length:" + array.length + " pos:" + pos2, new Object[i]);
                keyCount = keyCount3;
                break;
            }
            int pos4 = pos2 + 1;
            int keySeq = array[pos2];
            if (keySeq != i2 + 1) {
                LogUtils.e(TAG, "receive the error key seq:" + keySeq, new Object[i]);
                break;
            }
            LogUtils.d(TAG, "keySeq:" + keySeq, new Object[i]);
            if (array.length < pos4 + 1) {
                LogUtils.e(TAG, "invalid length for " + i2 + " key data length:" + array.length + " pos:" + pos4, new Object[i]);
                break;
            }
            int pos5 = pos4 + 1;
            int dataLen = array[pos4] & 255;
            int nextPacketLen = pos5 + dataLen;
            int len2 = len;
            if (array.length < pos5 + dataLen) {
                LogUtils.e(TAG, "invalid length for " + i2 + " key data length:" + array.length + " pos:" + pos5 + " dataLen:" + dataLen, new Object[0]);
                pos2 = nextPacketLen;
                keyCount2 = keyCount3;
                pos = 0;
            } else {
                LogUtils.d(TAG, "nextPacketLen:" + nextPacketLen + " dataLen:" + dataLen, new Object[0]);
                if (array.length < pos5 + 1) {
                    LogUtils.e(TAG, "invalid length for " + i2 + " user prop length:" + array.length + " pos:" + pos5 + " dataLen:" + dataLen, new Object[0]);
                    pos2 = nextPacketLen;
                    keyCount2 = keyCount3;
                    pos = 0;
                } else {
                    int pos6 = pos5 + 1;
                    int userProp = array[pos5];
                    if (!DigitalKeyBean.isValidUserProp(userProp)) {
                        LogUtils.e(TAG, "invalid for " + i2 + " user prop:" + userProp, new Object[0]);
                        pos2 = nextPacketLen;
                        keyCount2 = keyCount3;
                        pos = 0;
                    } else {
                        LogUtils.d(TAG, "userProp:" + userProp, new Object[0]);
                        DigitalKeyBean bean = new DigitalKeyBean();
                        bean.setUserPro(userProp);
                        if (array.length < pos6 + 1) {
                            LogUtils.e(TAG, "invalid length for " + i2 + " account len length:" + array.length + " pos:" + pos6, new Object[0]);
                            pos2 = nextPacketLen;
                            keyCount2 = keyCount3;
                            pos = 0;
                        } else {
                            int pos7 = pos6 + 1;
                            int acountLen = array[pos6];
                            if (array.length < pos7 + acountLen) {
                                LogUtils.e(TAG, "invalid length for " + i2 + " account data length:" + array.length + " pos:" + pos7, new Object[0]);
                                keyCount2 = keyCount3;
                                pos2 = nextPacketLen;
                                pos = 0;
                            } else {
                                byte[] tmpData = new byte[acountLen + 1];
                                System.arraycopy(array, pos7, tmpData, 0, acountLen);
                                String phoneNumber = new String(tmpData, StandardCharsets.UTF_8).trim();
                                bean.setUserPhone(phoneNumber);
                                int pos8 = pos7 + acountLen;
                                LogUtils.d(TAG, "accountLen:" + acountLen + " acount:" + phoneNumber, new Object[0]);
                                if (array.length < pos8 + 1) {
                                    LogUtils.e(TAG, "invalid length for " + i2 + " keyType length:" + array.length + " pos:" + pos8, new Object[0]);
                                    pos2 = nextPacketLen;
                                    keyCount2 = keyCount3;
                                    pos = 0;
                                } else {
                                    int pos9 = pos8 + 1;
                                    int keyType = array[pos8];
                                    if (!DigitalKeyBean.isValidKeyType(keyType)) {
                                        LogUtils.e(TAG, "invalid for " + i2 + " key type:" + keyType, new Object[0]);
                                        pos2 = nextPacketLen;
                                        keyCount2 = keyCount3;
                                        pos = 0;
                                    } else {
                                        LogUtils.d(TAG, "keyType:" + keyType, new Object[0]);
                                        bean.setKeyType(keyType);
                                        if (array.length < pos9 + 24) {
                                            LogUtils.e(TAG, "invalid length for " + i2 + " pass id length:" + array.length + " pos:" + pos9, new Object[0]);
                                            pos2 = nextPacketLen;
                                            keyCount2 = keyCount3;
                                            pos = 0;
                                        } else {
                                            byte[] tmpData2 = new byte[25];
                                            System.arraycopy(array, pos9, tmpData2, 0, 24);
                                            String keyNo = new String(tmpData2, StandardCharsets.UTF_8).trim();
                                            bean.setKeyNo(keyNo);
                                            int pos10 = pos9 + 24;
                                            LogUtils.d(TAG, "keyNo:" + keyNo, new Object[0]);
                                            if (array.length < pos10 + 1) {
                                                LogUtils.e(TAG, "invalid length for " + i2 + " key name length:" + array.length + " pos:" + pos10, new Object[0]);
                                                pos2 = nextPacketLen;
                                                keyCount2 = keyCount3;
                                                pos = 0;
                                            } else {
                                                int pos11 = pos10 + 1;
                                                int nameLen = Math.min(array[pos10] & 255, 30);
                                                bean.setKeyNameLen(nameLen);
                                                if (array.length < pos11 + nameLen) {
                                                    LogUtils.e(TAG, "invalid length for " + i2 + " key name length:" + array.length + " pos:" + pos11 + " nameLen:" + nameLen, new Object[0]);
                                                    keyCount2 = keyCount3;
                                                    pos2 = nextPacketLen;
                                                    pos = 0;
                                                } else {
                                                    byte[] tmpData3 = new byte[nameLen + 1];
                                                    System.arraycopy(array, pos11, tmpData3, 0, nameLen);
                                                    String keyName = new String(tmpData3, StandardCharsets.UTF_8).trim();
                                                    bean.setKeyNameStr(keyName);
                                                    int pos12 = pos11 + nameLen;
                                                    LogUtils.d(TAG, "nameLen:" + nameLen + " keyName:" + keyName, new Object[0]);
                                                    if (array.length < pos12 + 1) {
                                                        LogUtils.e(TAG, "invalid length for " + i2 + " device length:" + array.length + " pos:" + pos12, new Object[0]);
                                                        pos2 = nextPacketLen;
                                                        keyCount2 = keyCount3;
                                                        pos = 0;
                                                    } else {
                                                        int pos13 = pos12 + 1;
                                                        int deviceLen = array[pos12];
                                                        bean.setDeviceModelLen(deviceLen);
                                                        if (array.length < pos13 + deviceLen) {
                                                            LogUtils.e(TAG, "invalid length for " + i2 + " device length:" + array.length + " pos:" + pos13 + " deviceLen:" + deviceLen, new Object[0]);
                                                            pos2 = nextPacketLen;
                                                            keyCount2 = keyCount3;
                                                            pos = 0;
                                                        } else {
                                                            byte[] tmpData4 = new byte[deviceLen + 1];
                                                            System.arraycopy(array, pos13, tmpData4, 0, deviceLen);
                                                            String deviceName = new String(tmpData4, StandardCharsets.UTF_8).trim();
                                                            bean.setDeviceModelStr(deviceName);
                                                            int pos14 = pos13 + deviceLen;
                                                            LogUtils.d(TAG, "deviceLen:" + deviceLen + " deviceName:" + deviceName, new Object[0]);
                                                            if (array.length < pos14 + 1) {
                                                                LogUtils.e(TAG, "invalid length for " + i2 + " key status length:" + array.length + " pos:" + pos14, new Object[0]);
                                                                pos2 = nextPacketLen;
                                                                keyCount2 = keyCount3;
                                                                pos = 0;
                                                            } else {
                                                                int pos15 = pos14 + 1;
                                                                int keyState = array[pos14];
                                                                bean.setKeyState(keyState);
                                                                LogUtils.d(TAG, "keyState:" + keyState, new Object[0]);
                                                                if (array.length < pos15 + 3) {
                                                                    LogUtils.e(TAG, "invalid length for " + i2 + " key year month day length:" + array.length + " pos:" + pos15, new Object[0]);
                                                                    pos2 = nextPacketLen;
                                                                    keyCount2 = keyCount3;
                                                                    pos = 0;
                                                                } else {
                                                                    int pos16 = pos15 + 1;
                                                                    int year = array[pos15];
                                                                    int pos17 = pos16 + 1;
                                                                    int month = array[pos16];
                                                                    int pos18 = pos17 + 1;
                                                                    int day = array[pos17];
                                                                    if (!DigitalKeyBean.isValidDate(year, month, day)) {
                                                                        LogUtils.e(TAG, "invalid for " + i2 + " key " + year + " year " + month + " month " + day + " day", new Object[0]);
                                                                        keyCount2 = keyCount3;
                                                                        pos2 = nextPacketLen;
                                                                        pos = 0;
                                                                    } else {
                                                                        StringBuilder sb = new StringBuilder();
                                                                        keyCount2 = keyCount3;
                                                                        sb.append("");
                                                                        sb.append(year);
                                                                        sb.append(month);
                                                                        sb.append(day);
                                                                        bean.setExpireTime(sb.toString());
                                                                        LogUtils.d(TAG, "time:" + year + month + day, new Object[0]);
                                                                        if (array.length < pos18 + 1) {
                                                                            LogUtils.e(TAG, "invalid length for " + i2 + " key permission length:" + array.length + " pos:" + pos18, new Object[0]);
                                                                            pos2 = nextPacketLen;
                                                                            pos = 0;
                                                                        } else {
                                                                            int pos19 = pos18 + 1;
                                                                            int keyPermission = array[pos18];
                                                                            if (!DigitalKeyBean.isValidKeyPermission(keyPermission)) {
                                                                                LogUtils.e(TAG, "invalid for " + i2 + " key keyPermission:" + keyPermission, new Object[0]);
                                                                                pos2 = nextPacketLen;
                                                                                pos = 0;
                                                                            } else {
                                                                                bean.setKeyPermission(keyPermission);
                                                                                LogUtils.d(TAG, "keyPermission:" + keyPermission, new Object[0]);
                                                                                if (array.length < pos19 + 1) {
                                                                                    LogUtils.e(TAG, "invalid length for " + i2 + " key entity type length:" + array.length + " pos:" + pos19, new Object[0]);
                                                                                    pos2 = nextPacketLen;
                                                                                    pos = 0;
                                                                                } else {
                                                                                    int pos20 = pos19 + 1;
                                                                                    int keyEntityType = array[pos19];
                                                                                    if (!DigitalKeyBean.isValidKeyEntityType(keyEntityType)) {
                                                                                        LogUtils.e(TAG, "invalid for " + i2 + " key keyEntityType:" + keyEntityType, new Object[0]);
                                                                                        pos2 = nextPacketLen;
                                                                                        pos = 0;
                                                                                    } else {
                                                                                        bean.setKeyLoadType(keyEntityType);
                                                                                        LogUtils.d(TAG, "keyEntityType:" + keyEntityType, new Object[0]);
                                                                                        if (array.length < pos20 + 1) {
                                                                                            LogUtils.e(TAG, "invalid length for " + i2 + " key bind length:" + array.length + " pos:" + pos20, new Object[0]);
                                                                                            pos2 = nextPacketLen;
                                                                                            pos = 0;
                                                                                        } else {
                                                                                            int pos21 = pos20 + 1;
                                                                                            int keyBind = array[pos20];
                                                                                            if (!DigitalKeyBean.isValidKeyBindType(keyBind)) {
                                                                                                LogUtils.e(TAG, "invalid for " + i2 + " key keyBind:" + keyBind, new Object[0]);
                                                                                                pos2 = nextPacketLen;
                                                                                                pos = 0;
                                                                                            } else {
                                                                                                bean.setAccountTag(keyBind);
                                                                                                LogUtils.d(TAG, "keyBind:" + keyBind, new Object[0]);
                                                                                                if (array.length < pos21 + 8) {
                                                                                                    LogUtils.e(TAG, "invalid length for " + i2 + " key bind length:" + array.length + " pos:" + pos21, new Object[0]);
                                                                                                    pos2 = nextPacketLen;
                                                                                                    pos = 0;
                                                                                                } else {
                                                                                                    byte[] tmpData5 = new byte[8];
                                                                                                    System.arraycopy(array, pos21, tmpData5, 0, 8);
                                                                                                    bean.setKeyIdArray(tmpData5);
                                                                                                    int pos22 = pos21 + 8;
                                                                                                    LogUtils.d(TAG, "keyId:" + Arrays.toString(bean.getKeyIdArray()), new Object[0]);
                                                                                                    if (array.length < pos22 + 1) {
                                                                                                        LogUtils.e(TAG, "invalid length for " + i2 + " key uwb length:" + array.length + " pos:" + pos22, new Object[0]);
                                                                                                        pos2 = nextPacketLen;
                                                                                                        pos = 0;
                                                                                                    } else {
                                                                                                        int pos23 = pos22 + 1;
                                                                                                        int uwb = array[pos22];
                                                                                                        if (!DigitalKeyBean.isValidUwb(uwb)) {
                                                                                                            LogUtils.e(TAG, "invalid for " + i2 + " key uwb:" + uwb, new Object[0]);
                                                                                                            pos2 = nextPacketLen;
                                                                                                            pos = 0;
                                                                                                        } else {
                                                                                                            pos = 0;
                                                                                                            LogUtils.d(TAG, "uwb:" + uwb, new Object[0]);
                                                                                                            bean.setUwbTag(uwb);
                                                                                                            beans.add(bean);
                                                                                                            pos2 = pos23;
                                                                                                        }
                                                                                                    }
                                                                                                }
                                                                                            }
                                                                                        }
                                                                                    }
                                                                                }
                                                                            }
                                                                        }
                                                                    }
                                                                }
                                                            }
                                                        }
                                                    }
                                                }
                                            }
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
            }
            i2++;
            i = pos;
            len = len2;
            keyCount3 = keyCount2;
        }
        keyCount = keyCount3;
        this.mConnection.onNfcKeyList(keyCount, beans);
    }

    private void resetNfcRspBuffer() {
        LogUtils.d(TAG, "resetNfcRspBuffer", new Object[0]);
        this.mRecvPackageCount = 0;
        this.mNfcRspBuffer.clear();
    }

    private void handleSendApnTimeout(Message msg) {
        LogUtils.e(TAG, "handleSendApnTimeout", new Object[0]);
        this.mConnection.onSendApnArgsResult(msg.arg1);
    }

    boolean handleControlMessage(Message msg) {
        switch (msg.what) {
            case 1:
                handleStreamMessage(msg);
                return true;
            case 2:
                this.mParser.start(TAG);
                return true;
            case 3:
                this.mParser.stop();
                return true;
            case 4:
                ActiveCode code = (ActiveCode) msg.obj;
                this.mConnection.releaseActiveSemaphoreAndNotify(code.getProviderType(), 2);
                return true;
            case 5:
                int resultFailed = msg.arg2;
                if (resultFailed == 2) {
                    this.mConnection.onTboxRestartChanged(-1);
                } else {
                    LogUtils.d(TAG, "resend restart TBox times:" + msg.arg2, new Object[0]);
                    this.mConnection.sendRestartTbox(msg.arg1, msg.arg2 + 1);
                }
                return true;
            case 6:
                this.mConnection.sendTrafficeQueryResult(3, 1);
                return true;
            case 7:
                this.mConnection.sendTrafficeQueryResult(0, "");
                return true;
            case 8:
                ActiveCodeV2 code2 = (ActiveCodeV2) msg.obj;
                this.mConnection.releaseActiveCodeV2SemaphoreAndNotify(code2.getProviderType(), 2);
                return true;
            case 9:
                handleNitzTimeout();
                return true;
            case 10:
                this.mConnection.onDeleteNfcCallback(6, 0);
                return true;
            case 11:
                this.mConnection.onDeleteNfcCallback(2, 0);
                return true;
            case 12:
                this.mConnection.onDeleteNfcCallback(1, 0);
                return true;
            case 13:
                this.mConnection.onDeleteNfcCallback(4, 0);
                return true;
            case 14:
                handleCloudStateChangedInternal(msg);
                return true;
            case 15:
                handleSendApnTimeout(msg);
                return true;
            default:
                return false;
        }
    }

    private void handleNitzTimeout() {
        LogUtils.i(TAG, "nitz resend req", new Object[0]);
        new Thread(new Runnable() { // from class: com.engine.tbox.item.-$$Lambda$RecvMessageHandler$0jNrwyRu-dKqGaXZCWm3jGgkBKY
            @Override // java.lang.Runnable
            public final void run() {
                this.f$0.lambda$handleNitzTimeout$0$RecvMessageHandler();
            }
        }).start();
    }

    public /* synthetic */ void lambda$handleNitzTimeout$0$RecvMessageHandler() {
        this.mConnection.getNitzData();
    }

    private boolean handleAuthMessage(Message msg) {
        LogUtils.d(TAG, "handleAuthMessage:" + msg.what, new Object[0]);
        switch (msg.what) {
            case MessageConstants.TBOX_IVI_EXCHANGE_INIT /* 769 */:
                parsePublicKey((CountSyncMessage) msg.obj);
                return true;
            case MessageConstants.TBOX_IVI_EXCHANGE_RES /* 770 */:
                parseHelloData((CountSyncMessage) msg.obj);
                return true;
            case MessageConstants.TBOX_IVI_EXCHANGE_ACK /* 771 */:
                this.mConnection.recvAuthAck();
                return true;
            default:
                return false;
        }
    }

    private boolean handleTboxReq(Message msg) {
        LogUtils.d(TAG, "handleTBoxReq:" + msg.what, new Object[0]);
        int i = msg.what;
        if (i == 2561) {
            notifyNetworkTrafficQuery((CountSyncMessage) msg.obj);
            return true;
        }
        if (i == 2817) {
            sendVersionMessage();
            return true;
        }
        if (i == 3073) {
            sendSnMessage();
            return true;
        }
        if (i != 8202) {
            return false;
        }
        sendSupportMessage();
        return true;
    }

    private void sendSupportMessage() {
        LogUtils.d(TAG, "sendSupportMessage", new Object[0]);
        this.mConnection.sendOtaSupport(true);
    }

    private boolean handleChargeMessage(Message msg) {
        LogUtils.d(TAG, "handleChargeMessage:" + msg.what, new Object[0]);
        int i = msg.what;
        if (i == 5377) {
            notifyChargePlaneDown((CountSyncMessage) msg.obj);
            return true;
        }
        if (i == 5633) {
            notifyUserChargeChangeDown((CountSyncMessage) msg.obj);
            return true;
        }
        if (i == 6147) {
            handleV2GReq(msg.obj);
            return true;
        }
        if (i != 7169) {
            return false;
        }
        handleNationalReq();
        return true;
    }

    private boolean isValidV2GMode(int mode) {
        if (mode == 3 || mode == 4) {
            return true;
        }
        return false;
    }

    private void handleV2GReq(Object obj) {
        LogUtils.d(TAG, "handleV2GReq:" + obj, new Object[0]);
        TboxMessage msg = (TboxMessage) obj;
        byte[] data = msg.getData();
        if (data.length < 3) {
            LogUtils.d(TAG, "error mesageLen for v2g req:" + Arrays.toString(data), new Object[0]);
            return;
        }
        if (!isValidV2GMode(data[0])) {
            LogUtils.e(TAG, "unexpected v2gMode:" + ((int) data[0]), new Object[0]);
            return;
        }
        int len = TypeUtils.byteArrayPosToShort(data, 1);
        String json = new String(data, 3, Math.min(len, data.length - 3), StandardCharsets.UTF_8);
        this.mConnection.send539Resp(json);
    }

    private boolean handleTboxRsp(Message msg) {
        int i = msg.what;
        if (i == 3585) {
            notifyActiveResult((CountSyncMessage) msg.obj);
            return true;
        }
        if (i == 6401) {
            notifyTBoxRestartRsp((CountSyncMessage) msg.obj);
            return true;
        }
        if (i == 6657) {
            notifyOperationList((CountSyncMessage) msg.obj);
            return true;
        }
        return false;
    }

    private boolean handleTboxNotify(Message msg) {
        int i = msg.what;
        if (i == 513) {
            LogUtils.e(TAG, "recv the server heartbeat", new Object[0]);
            return true;
        }
        if (i == 1299) {
            notifyApnState((CountSyncMessage) msg.obj);
            return true;
        }
        if (i == 4098) {
            handleCloudStateChanged((CountSyncMessage) msg.obj);
            return true;
        }
        if (i == 4865) {
            notifyOtaInfo((CountSyncMessage) msg.obj);
            return true;
        }
        if (i == 8194) {
            handleNitzData((CountSyncMessage) msg.obj);
            return true;
        }
        if (i == 1795) {
            upgradeProcess((CountSyncMessage) msg.obj);
            return true;
        }
        if (i != 1796) {
            return false;
        }
        updateResultNotification((CountSyncMessage) msg.obj);
        return true;
    }

    private boolean checkValidTBoxRestartDate(byte date) {
        if (date == 0 || date == 1) {
            return true;
        }
        return false;
    }

    private void handleNationalReq() {
        String rudder = SystemProperties.get(RUDDER_PROP, "");
        int intRundder = 0;
        if (!TextUtils.isEmpty(rudder)) {
            if (rudder.equals("0")) {
                intRundder = 1;
            } else {
                intRundder = 2;
            }
        }
        String simpleCode = SystemProperties.get(DATA_CENTER_SIMPLE_CODE, "");
        int code = this.mConnection.getCountryCode();
        LogUtils.i(TAG, "handleNationalReq rudder:" + intRundder + " code:" + code + " simpleCode:" + simpleCode, new Object[0]);
        byte[] data = MessageUtils.buildNationData(intRundder, code, simpleCode);
        this.mConn.sendAsyncMessage(MessageUtils.buildNationMessage(data));
    }

    private boolean isValidConnectedState(int state) {
        return state == 1 || state == 2;
    }

    private void handleCloudStateChanged(CountSyncMessage obj) {
        LogUtils.d(TAG, "handleCloudStateChanged:" + obj, new Object[0]);
        TboxMessage msg = (TboxMessage) obj;
        byte[] bytes = msg.getData();
        if (bytes.length < 1) {
            LogUtils.e(TAG, "handleCloudStateChanged error bytes length:" + bytes.length, new Object[0]);
            return;
        }
        int connected = bytes[0];
        if (!isValidConnectedState(connected)) {
            LogUtils.e(TAG, "handleCloudStateChanged with error connected state:" + connected, new Object[0]);
            return;
        }
        this.mConnection.notifyTboxCloudConnected(connected);
    }

    private void handleCloudStateChangedInternal(Message msg) {
        LogUtils.d(TAG, "handleCloudStateChanged:" + msg.arg1, new Object[0]);
        int connected = msg.arg1;
        if (!isValidConnectedState(connected)) {
            LogUtils.e(TAG, "handleCloudStateChanged with error connected state:" + connected, new Object[0]);
            return;
        }
        this.mConnection.notifyTboxCloudConnected(connected);
    }

    private void handleNitzData(CountSyncMessage obj) {
        LogUtils.d(TAG, "handleNitzData:" + obj, new Object[0]);
        TboxMessage msg = (TboxMessage) obj;
        byte[] bytes = msg.getData();
        if (bytes.length < 9) {
            LogUtils.e(TAG, "handleNitzData error length:" + obj, new Object[0]);
            return;
        }
        String nitz = new String(bytes, 0, bytes.length - 9, StandardCharsets.UTF_8).trim();
        this.mConnection.updateNitz(nitz);
    }

    private void notifyOperationList(CountSyncMessage obj) {
        LogUtils.d(TAG, "notifyOperationList:" + obj, new Object[0]);
        TboxMessage msg = (TboxMessage) obj;
        byte[] bytes = msg.getData();
        if (bytes.length < 1) {
            LogUtils.e(TAG, "notifyOperationList error bytes length " + bytes.length, new Object[0]);
            return;
        }
        int count = TypeUtils.byteToInt(bytes[0]);
        LogUtils.v(TAG, "notifyOperationList count:" + count, new Object[0]);
        if (bytes.length == (count * 8) + 1) {
            handleOpListVersion2(bytes, bytes.length / 8);
        } else if (bytes.length == (count * 5) + 1) {
            handleOpListVersion1(bytes, bytes.length / 5);
        } else {
            handleOpListVersion2(bytes, bytes.length / 8);
        }
    }

    private void handleOpListVersion2(byte[] bytes, int count) {
        LogUtils.d(TAG, "handleOpListVersion1 bytes:" + Arrays.toString(bytes) + " count:" + count, new Object[0]);
        int pos = 1;
        ArrayList<NetworkOperator> opList = new ArrayList<>();
        for (int i = 0; i < count; i++) {
            byte[] data = new byte[6];
            System.arraycopy(bytes, pos, data, 0, 6);
            int pos2 = pos + 6;
            NetworkOperator op = new NetworkOperator();
            op.setMccMnc(new String(data, StandardCharsets.UTF_8).trim());
            int pos3 = pos2 + 1;
            op.setEnable(bytes[pos2]);
            pos = pos3 + 1;
            op.setSigLevel(bytes[pos3]);
            LogUtils.d(TAG, "op[" + i + "]:" + op, new Object[0]);
            opList.add(op);
        }
        this.mConnection.notifyNetworkOperationList(opList);
    }

    private void handleOpListVersion1(byte[] bytes, int count) {
        LogUtils.d(TAG, "handleOpListVersion1 bytes:" + Arrays.toString(bytes) + " count:" + count, new Object[0]);
        ArrayList<String> codeList = new ArrayList<>();
        for (int i = 0; i < count; i++) {
            byte[] data = new byte[5];
            System.arraycopy(bytes, (i * 5) + 1, data, 0, 5);
            codeList.add(new String(data, StandardCharsets.UTF_8).trim());
        }
        this.mConnection.notifyOperatorList(codeList);
    }

    private void notifyTBoxRestartRsp(CountSyncMessage obj) {
        removeMessages(5);
        TboxMessage msg = (TboxMessage) obj;
        byte[] bytes = msg.getData();
        if (bytes.length != 1) {
            LogUtils.d(TAG, "invalid TBox message date length:" + bytes.length, new Object[0]);
            this.mConnection.onTboxRestartChanged(-1);
            return;
        }
        if (!checkValidTBoxRestartDate(bytes[0])) {
            LogUtils.e(TAG, "checkValidTBoxRestartDate:" + ((int) bytes[0]), new Object[0]);
            this.mConnection.onTboxRestartChanged(-1);
            return;
        }
        this.mConnection.onTboxRestartChanged(bytes[0]);
    }

    private void notifyActiveResult(CountSyncMessage obj) {
        removeMessages(4);
        removeMessages(8);
        this.mConnection.notifyTheActiveResult((TboxMessage) obj);
    }

    private void notifyUserChargeChangeDown(CountSyncMessage obj) {
        LogUtils.i(TAG, "notifyUserChargeChangeDown", new Object[0]);
        TboxMessage msg = (TboxMessage) obj;
        byte[] bytes = msg.getData();
        if (bytes != null && bytes.length == 1) {
            this.mConnection.userChangeChargePlane(bytes[0]);
        } else {
            LogUtils.d(TAG, "notifyUserChargeChangeDown parse the message failed", new Object[0]);
        }
    }

    private void notifyChargePlaneDown(CountSyncMessage obj) {
        LogUtils.i(TAG, "notifyChargePlaneDown", new Object[0]);
        TboxMessage msg = (TboxMessage) obj;
        ReservationCharging charge = ReservationCharging.bytesToReservationCharging(msg.getData());
        if (charge != null) {
            boolean result = this.mConnection.storeCharge(charge);
            this.mConnection.tboxChargePlaneCallback(charge);
            this.mConnection.sendPlaneRes(result ? 1 : 2);
        } else {
            LogUtils.d(TAG, "notifyChargePlaneDown parse the message failed", new Object[0]);
            this.mConnection.sendPlaneRes(0);
        }
    }

    private void notifyOtaInfo(CountSyncMessage obj) {
        LogUtils.w(TAG, "notifyOtaInfo:" + obj, new Object[0]);
        TboxMessage msg = (TboxMessage) obj;
        OtaNotification nty = null;
        try {
            nty = OtaNotification.bytesOtaNotification(msg.getData());
        } catch (Exception exp) {
            exp.printStackTrace();
        }
        if (nty != null) {
            this.mConnection.otaNotificationCallback(nty);
            this.mConnection.sendOtsRes(1);
            return;
        }
        LogUtils.e(TAG, "notifyOtaInfo parse the message failed:" + Arrays.toString(msg.getData()), new Object[0]);
        this.mConnection.sendOtsRes(2);
    }

    private void sendSnMessage() {
        String sn = SystemProperties.get(SN_PROP);
        if (TextUtils.isEmpty(sn)) {
            LogUtils.e(TAG, "get the sn failed", new Object[0]);
            return;
        }
        LogUtils.i(TAG, "sendSnMessage:" + sn, new Object[0]);
        byte[] data = sn.getBytes(StandardCharsets.UTF_8);
        this.mConnection.sendAsyncMessage(MessageUtils.buildSnRsp(data));
    }

    private void sendVersionMessage() {
        LogUtils.i(TAG, "sendVersionMessage", new Object[0]);
        String version = SystemProperties.get(VERSION_PROP);
        if (TextUtils.isEmpty(version)) {
            LogUtils.e(TAG, "get the version info failed", new Object[0]);
            return;
        }
        LogUtils.i(TAG, "sendVersionMessage:" + version, new Object[0]);
        byte[] data = version.getBytes(StandardCharsets.UTF_8);
        this.mConnection.sendAsyncMessage(MessageUtils.buildVersionRsp(data));
    }

    private void notifyNetworkTrafficQuery(CountSyncMessage countMessage) {
        int msg;
        LogUtils.i(TAG, "notifyNetworkTrafficQuery", new Object[0]);
        TboxMessage message = (TboxMessage) countMessage;
        byte[] result = message.getData();
        if (result.length != 1) {
            LogUtils.e(TAG, "notifyNetworkTrafficQuery error message len:" + result.length, new Object[0]);
            return;
        }
        if (result[0] == 1) {
            msg = 7;
        } else {
            msg = 6;
        }
        removeMessages(msg);
        sendEmptyMessageDelayed(msg, 5000L);
        this.mConnection.networkTrafficReqCallback(result[0]);
    }

    private void notifyApnState(CountSyncMessage countMessage) {
        TboxMessage message = (TboxMessage) countMessage;
        byte[] result = message.getData();
        if (result.length != 1) {
            LogUtils.e(TAG, "notifyApnState error message len:" + result.length, new Object[0]);
            return;
        }
        this.mConnection.apn2StateChanged(result[0] == 0);
    }

    private void parsePublicKey(CountSyncMessage countMessage) {
        LogUtils.i(TAG, "parsePublicKey", new Object[0]);
        TboxMessage message = (TboxMessage) countMessage;
        byte[] data = message.getData();
        if (data.length < 128) {
            LogUtils.e(TAG, "parsePublicKey error message len:" + data.length + " data:" + Arrays.toString(data), new Object[0]);
            this.mConnection.sendPublicKeyFailed();
            return;
        }
        this.mConnection.sendRsaPublicKey(data);
    }

    private void parseHelloData(CountSyncMessage msg) {
        LogUtils.i(TAG, "parse exchange response Data" + msg, new Object[0]);
        TboxMessage message = (TboxMessage) msg;
        byte[] data = message.getData();
        this.mConnection.checkIsHello(data);
    }

    public void updateResultNotification(CountSyncMessage data) {
        LogUtils.i(TAG, "updateResultNotification", new Object[0]);
        TboxMessage message = (TboxMessage) data;
        byte[] result = message.getData();
        if (result.length != 1) {
            LogUtils.e(TAG, "updateResultNotification error len:" + result.length, new Object[0]);
            return;
        }
        int resultInt = result[0];
        LogUtils.d(TAG, "updateResultNotification result:" + resultInt, new Object[0]);
        if (resultInt == 0) {
            this.mConnection.sendUpgradeResult(true);
        } else if (resultInt == 2) {
            this.mConnection.upgradeFileCheck(false);
        } else if (resultInt == 3) {
            this.mConnection.sendUpgradeResult(false);
        }
    }

    public void upgradeProcess(CountSyncMessage data) {
        TboxMessage message = (TboxMessage) data;
        byte[] process = message.getData();
        if (process.length != 1) {
            LogUtils.e(TAG, "error upgradeProcess length:" + process.length, new Object[0]);
        }
        int processInt = process[0];
        LogUtils.i(TAG, "processNotification process:" + processInt, new Object[0]);
        this.mConnection.sendUpgradeProcess(processInt);
    }
}
