package com.engine.tbox.item;

import android.os.Looper;
import android.os.Message;
import android.util.ArrayMap;
import com.engine.tbox.core.conn.MessageHandler;
import com.engine.tbox.core.utils.LogUtils;

/* JADX INFO: loaded from: classes.dex */
public class SendMessageHandler extends MessageHandler {
    private static final long DELAY_TIME = 10000;
    private HeartBeatCallback mCallback;
    private TBoxConnection mConnection;
    private final ArrayMap<Integer, TboxMessage> mMap;

    public SendMessageHandler(Looper looper) {
        super(looper);
        this.mMap = new ArrayMap<>();
    }

    public interface HeartBeatCallback {
        default void heartBeatFailed() {
            LogUtils.d(LogUtils.TAG, "heartbeat failed", new Object[0]);
        }
    }

    public void addTBoxConnection(TBoxConnection conn) {
        this.mConnection = conn;
    }

    public void registerHeartBeatCallback(HeartBeatCallback callback) {
        this.mCallback = callback;
    }

    @Override // com.engine.tbox.core.conn.MessageHandler, android.os.Handler
    public void handleMessage(Message msg) {
        TboxMessage message = (TboxMessage) msg.obj;
        boolean isHeartBeat = message.identify() == 529;
        if (message.needSend() || isHeartBeat) {
            if (this.mConnection.isAuthEnable() && MessageConstants.sendNeedEncryptMessage(message.getCmd())) {
                LogUtils.i(LogUtils.TAG, "befor send authEncrypt message:" + message, new Object[0]);
                if (!this.mConnection.authEncrypt(message)) {
                    LogUtils.e(LogUtils.TAG, "encrypt failed msg:" + msg, new Object[0]);
                    return;
                }
                LogUtils.i(LogUtils.TAG, "after send authEncrypt message:" + message, new Object[0]);
            } else {
                LogUtils.i(LogUtils.TAG, "send message :" + message, new Object[0]);
            }
            if (this.mConn != null) {
                this.mConn.sendByteBuffer(message.toByteBuffer());
            }
        }
        message.addCount();
        if (isHeartBeat) {
            handleHeartbeatMessage(msg);
        }
    }

    private void handleHeartbeatMessage(Message msg) {
        LogUtils.d(LogUtils.TAG, "send repeat heartbeat message", new Object[0]);
        Message newMessage = obtainMessage();
        newMessage.what = msg.what;
        newMessage.obj = msg.obj;
        sendMessageDelayed(newMessage, DELAY_TIME);
        if (!this.mConn.addHeartbeatCount()) {
            HeartBeatCallback heartBeatCallback = this.mCallback;
            if (heartBeatCallback != null) {
                heartBeatCallback.heartBeatFailed();
            }
            LogUtils.d(LogUtils.TAG, "heartbeat failed", new Object[0]);
        }
    }
}
