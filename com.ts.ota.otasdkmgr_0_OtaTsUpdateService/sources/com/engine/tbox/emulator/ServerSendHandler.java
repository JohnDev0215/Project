package com.engine.tbox.emulator;

import android.os.Looper;
import android.os.Message;
import com.engine.tbox.core.conn.MessageHandler;
import com.engine.tbox.core.data.CountSyncMessage;
import com.engine.tbox.core.data.IMsgIface;
import com.engine.tbox.core.utils.LogUtils;
import com.engine.tbox.item.MessageConstants;

/* JADX INFO: loaded from: classes.dex */
public class ServerSendHandler extends MessageHandler {
    private static final long DELAY_TIME = 1000;
    private static final String TAG = "TBoxServerSendHandler";

    public ServerSendHandler(Looper looper) {
        super(looper);
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Override // com.engine.tbox.core.conn.MessageHandler, android.os.Handler
    public void handleMessage(Message msg) {
        CountSyncMessage countSyncMessage = (CountSyncMessage) msg.obj;
        boolean isHeartBeat = MessageConstants.isHearBeatMessage(countSyncMessage.identify());
        if (countSyncMessage.needSend() || isHeartBeat) {
            if (countSyncMessage instanceof IMsgIface) {
                IMsgIface iface = (IMsgIface) countSyncMessage;
                if (this.mConn != null) {
                    LogUtils.d(TAG, "send ByteBuffer:" + countSyncMessage, new Object[0]);
                    this.mConn.sendByteBuffer(iface.toByteBuffer());
                }
            } else {
                LogUtils.e(TAG, "error message class:" + countSyncMessage.getClass().getName(), new Object[0]);
            }
        }
        countSyncMessage.addCount();
    }
}
