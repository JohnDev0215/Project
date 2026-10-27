package com.ts.avm.manager;

import android.os.Handler;
import android.os.HandlerThread;
import android.os.Message;
import android.text.TextUtils;
import com.ts.avm.bean.AvmCallInfoBean;
import com.ts.avm.manager.SocketManager;
import com.ts.avm.utils.LogManager;
import com.ts.avm.utils.socket.TcpSocketImpl;
import java.io.UnsupportedEncodingException;
import java.util.Arrays;
import java.util.List;

/* JADX INFO: loaded from: classes.dex */
public class AvmCallManager implements SocketManager.MessageListener, SocketManager.SocketStateListener {
    private static final int ADDRESS_INDEX = 40;
    private static final int AVM_CALL_RECEIVE_DATA_LENGTH = 28;
    private static final int AVM_CALL_SEND_DATA_LENGTH = 80;
    private static final String AVM_CALL_SOCKET_IP = "192.168.118.2";
    private static final int AVM_CALL_SOCKET_PORT = 52052;
    private static final int BYTE_ARRAY_INDEX0 = 0;
    private static final int BYTE_ARRAY_INDEX1 = 1;
    private static final int BYTE_ARRAY_INDEX2 = 2;
    private static final int BYTE_ARRAY_INDEX3 = 3;
    private static final int CALL_AUDIO_SOURCE_INDEX = 76;
    private static final int CALL_BACK_CALL_ACTION = 3;
    private static final int CALL_STATE_INDEX = 68;
    private static final int CALL_TIME_INDEX = 60;
    private static final int CONNECT_SOCKET = 2;
    private static final int CONTACT_NAME_INDEX = 20;
    private static final int INTEGER_INDEX16 = 16;
    private static final int INTEGER_INDEX24 = 24;
    private static final int INTEGER_INDEX8 = 8;
    private static final int IS_MUTE_INDEXT = 72;
    private static final int MAX_BYTE = 255;
    private static final int MAX_CALL_INFO_SIZE = 2;
    private static final int MAX_STR_LENGTH = 19;
    private static final int MUTE = 1;
    private static final int PHONE_MUM_LENGTH = 20;
    private static final int PHONE_NUM_INDEX = 0;
    private static final int PHONE_NUM_MIN_LENGTH = 4;
    private static final int QNX_ACTION_INDEX = 24;
    private static final int REC_CALL_TYPE_INDEX = 20;
    private static final int SEND_CALL_INFO = 1;
    private static final int SEND_CALL_TYPE_INDEX = 64;
    private static final int UN_MUTE = 0;
    private boolean isAvmCallSocketConnect = false;
    private Handler mHandle;
    private HandlerThread mHandlerThread;
    private IAvmCallActionListener mListener;
    private TcpSocketImpl mTcpSocket;
    private static final String TAG = AvmCallManager.class.getSimpleName();
    private static volatile AvmCallManager instance = null;

    public interface IAvmCallActionListener {
        void onAvmActionCallback(String str, int i, int i2);
    }

    private AvmCallManager() {
        init();
        initHandler();
        this.mHandle.sendEmptyMessage(2);
    }

    public void registerAvmCallActionListener(IAvmCallActionListener listener) {
        LogManager.logd(TAG, "registerAvmCallActionListener");
        this.mListener = listener;
    }

    private void initHandler() {
        HandlerThread handlerThread = new HandlerThread("avmCallSend");
        this.mHandlerThread = handlerThread;
        handlerThread.start();
        this.mHandle = new Handler(this.mHandlerThread.getLooper()) { // from class: com.ts.avm.manager.AvmCallManager.1
            @Override // android.os.Handler
            public void handleMessage(Message msg) {
                super.handleMessage(msg);
                if (msg.what == 2) {
                    AvmCallManager.this.connectSocket();
                }
            }
        };
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void connectSocket() {
        LogManager.logi(TAG, "connectSocket called");
        TcpSocketImpl tcpSocketImpl = this.mTcpSocket;
        if (tcpSocketImpl != null) {
            tcpSocketImpl.initSocket();
        } else {
            LogManager.loge(TAG, "mTcpSocket is null");
        }
    }

    public void sendCallInfo(String phoneNum, String contactName, String address, int callTime, int callType, int callState, boolean isMute) {
        String phoneNumStr;
        if (phoneNum != null) {
            if (phoneNum.length() > 4) {
                String phoneNumStr2 = phoneNum.substring(phoneNum.length() - 4, phoneNum.length());
                phoneNumStr = phoneNumStr2;
            } else {
                phoneNumStr = phoneNum;
            }
        } else {
            phoneNumStr = "";
        }
        String phoneNumStr3 = TAG;
        LogManager.logd(phoneNumStr3, "sendCallInfo: called success phoneNum:" + phoneNumStr + " callTime:" + callTime + " callType:" + callType + " callState:" + callState + " isMute:" + isMute);
        byte[] data = buildDataByCallInfo(phoneNum, contactName, address, callTime, callType, callState, isMute, -1);
        byte[] dataResult = addBytes(new byte[4], data);
        byte[] msgLengthSize = intToByteArray(dataResult.length);
        byte[] dataResult2 = buildItemData(dataResult, msgLengthSize, 0);
        if (dataResult2 != null) {
            this.mTcpSocket.sendMessage(dataResult2);
        } else {
            LogManager.logi(TAG, "sendCallInfo is : null");
        }
    }

    public void sendMultiCallInfo(List<AvmCallInfoBean> list) {
        if (list.size() > 2) {
            LogManager.logi(TAG, "the callInfo send more than 2, not support");
            return;
        }
        byte[] bytesResult = new byte[4];
        for (int i = 0; i < list.size(); i++) {
            AvmCallInfoBean callInfoBean = list.get(i);
            if (callInfoBean == null) {
                LogManager.logd(TAG, "callInfoBean: " + i + " is null continue");
            } else {
                byte[] bytesData = buildDataByCallInfo(callInfoBean);
                bytesResult = addBytes(bytesResult, bytesData);
                LogManager.logd(TAG, "sendMultiCallInfo:" + callInfoBean.toString());
            }
        }
        int i2 = bytesResult.length;
        byte[] msgLengthSize = intToByteArray(i2);
        byte[] bytesResult2 = buildItemData(bytesResult, msgLengthSize, 0);
        if (bytesResult2 != null) {
            this.mTcpSocket.sendMessage(bytesResult2);
        } else {
            LogManager.logi(TAG, "sendCallInfo is : null");
        }
    }

    private byte[] buildDataByCallInfo(AvmCallInfoBean avmCallInfoBean) {
        return buildDataByCallInfo(avmCallInfoBean.getPhoneNum(), avmCallInfoBean.getContactName(), avmCallInfoBean.getAddress(), avmCallInfoBean.getCallTime(), avmCallInfoBean.getCallType(), avmCallInfoBean.getCallState(), avmCallInfoBean.isMute(), avmCallInfoBean.getAudioSource());
    }

    public byte[] addBytes(byte[] data1, byte[] data2) {
        byte[] dateResult = new byte[data1.length + data2.length];
        System.arraycopy(data1, 0, dateResult, 0, data1.length);
        System.arraycopy(data2, 0, dateResult, data1.length, data2.length);
        return dateResult;
    }

    private byte[] buildDataByCallInfo(String str, String str2, String str3, int i, int i2, int i3, boolean z, int i4) {
        byte[] bArr = new byte[AVM_CALL_SEND_DATA_LENGTH];
        byte[] bArrString2ByteArray = string2ByteArray(str);
        byte[] bArrString2ByteArray2 = string2ByteArray(str2);
        byte[] bArrString2ByteArray3 = string2ByteArray(str3);
        byte[] bArrIntToByteArray = intToByteArray(i);
        byte[] bArrIntToByteArray2 = intToByteArray(i2);
        byte[] bArrIntToByteArray3 = intToByteArray(i3);
        byte[] bArrIntToByteArray4 = intToByteArray(z ? 1 : 0);
        return buildItemData(buildItemData(buildItemData(buildItemData(buildItemData(buildItemData(buildItemData(buildItemData(bArr, bArrString2ByteArray, 0), bArrString2ByteArray2, 20), bArrString2ByteArray3, ADDRESS_INDEX), bArrIntToByteArray, CALL_TIME_INDEX), bArrIntToByteArray2, 64), bArrIntToByteArray3, CALL_STATE_INDEX), bArrIntToByteArray4, IS_MUTE_INDEXT), intToByteArray(i4), CALL_AUDIO_SOURCE_INDEX);
    }

    private byte[] buildItemData(byte[] sendData, byte[] partData, int startIndex) {
        if (partData != null) {
            for (int i = 0; i < partData.length; i++) {
                sendData[startIndex + i] = partData[i];
            }
        }
        return sendData;
    }

    public static byte[] intToByteArray(int data) {
        byte[] array = {(byte) (data & MAX_BYTE), (byte) ((data >> 8) & MAX_BYTE), (byte) ((data >> INTEGER_INDEX16) & MAX_BYTE), (byte) ((data >> 24) & MAX_BYTE)};
        return array;
    }

    public int bytes2int(byte[] bytes, int startIndex) {
        int tempA = bytes[startIndex] & 255;
        int tempB = (bytes[startIndex + 1] & 255) << 8;
        int tempC = (bytes[startIndex + 2] & 255) << INTEGER_INDEX16;
        int tempD = (bytes[startIndex + 3] & 255) << 24;
        return tempA | tempB | tempC | tempD;
    }

    public String bytes2String(byte[] bytes, int startIndex, int length) {
        if (bytes == null || startIndex < 0 || length <= 0) {
            return "";
        }
        byte[] strByte = new byte[length];
        for (int i = 0; i < length; i++) {
            strByte[i] = bytes[startIndex + i];
        }
        try {
            String transferResult = new String(strByte, "utf-8");
            if (TextUtils.isEmpty(transferResult)) {
                return transferResult;
            }
            int index = transferResult.indexOf("\u0000");
            return transferResult.substring(0, index);
        } catch (UnsupportedEncodingException e) {
            throw new RuntimeException(e);
        }
    }

    private byte[] string2ByteArray(String str) {
        if (str == null) {
            str = "";
        }
        try {
            byte[] dataStr = str.getBytes("UTF-8");
            if (dataStr.length > MAX_STR_LENGTH) {
                byte[] dataIgnore = "...\u0000".getBytes("UTF-8");
                byte[] dataStr2 = addBytes(Arrays.copyOf(dataStr, MAX_STR_LENGTH - dataIgnore.length), dataIgnore);
                LogManager.logd(TAG, "string2ByteArray: remakeStrLength:" + dataStr2.length + "dataIgnoreL:" + dataIgnore.length);
                return dataStr2;
            }
            return dataStr;
        } catch (UnsupportedEncodingException ex) {
            ex.printStackTrace();
            return null;
        }
    }

    private void init() {
        if (this.mTcpSocket == null) {
            this.mTcpSocket = new TcpSocketImpl();
            SocketManager.Config config = new SocketManager.Config();
            config.ip = AVM_CALL_SOCKET_IP;
            config.port = AVM_CALL_SOCKET_PORT;
            config.receiveDataLength = AVM_CALL_RECEIVE_DATA_LENGTH;
            this.mTcpSocket.socketConfig(config);
            this.mTcpSocket.registerMessageListener(this);
            this.mTcpSocket.registerSocketListener(this);
        }
    }

    public static synchronized AvmCallManager getInstance() {
        if (instance == null) {
            instance = new AvmCallManager();
        }
        return instance;
    }

    @Override // com.ts.avm.manager.SocketManager.MessageListener
    public void onReceiveMessage(byte[] dataArr) {
        LogManager.logd(TAG, "onReceiveMessage called");
        String callNum = bytes2String(dataArr, 0, 20);
        int callType = bytes2int(dataArr, 20);
        int action = bytes2int(dataArr, 24);
        String phoneNumStr = "";
        if (callNum != null) {
            if (callNum.length() > 4) {
                phoneNumStr = callNum.substring(callNum.length() - 4, callNum.length());
            } else {
                phoneNumStr = callNum;
            }
        }
        LogManager.logd(TAG, " onAvmCallStatusChanged: phonenum:" + phoneNumStr + " calltype:" + callType + " action:" + action);
        this.mListener.onAvmActionCallback(callNum, callType, action);
    }

    @Override // com.ts.avm.manager.SocketManager.MessageListener
    public void onAckConfirmed() {
        LogManager.logi(TAG, "onAckConfirmed");
    }

    @Override // com.ts.avm.manager.SocketManager.SocketStateListener
    public void onServerConnected() {
        LogManager.logi(TAG, "onServerConnected");
        if (!this.isAvmCallSocketConnect) {
            this.isAvmCallSocketConnect = true;
        }
    }

    @Override // com.ts.avm.manager.SocketManager.SocketStateListener
    public void onServerDisconnect() {
        LogManager.logi(TAG, "onServerDisconnect");
        reConnectCallSocket();
    }

    @Override // com.ts.avm.manager.SocketManager.SocketStateListener
    public void onError() {
        LogManager.logi(TAG, "onError");
        reConnectCallSocket();
    }

    private void reConnectCallSocket() {
        LogManager.logi(TAG, "reConnectCallSocket  called");
        if (this.isAvmCallSocketConnect) {
            this.isAvmCallSocketConnect = false;
        }
        if (this.mHandle.hasMessages(2)) {
            this.mHandle.removeMessages(2);
        }
        this.mHandle.sendEmptyMessageDelayed(2, 5000L);
    }
}
