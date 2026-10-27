package com.ts.qnxapp;

import android.os.Bundle;
import android.os.Handler;
import android.os.HandlerThread;
import android.os.Looper;
import android.os.Message;
import com.ts.qnxapp.fdbusClientForAndroid.FdbusUtil;
import com.ts.qnxapp.util.LogUtil;
import ipc.fdbus.FdbusMessage;
import java.util.concurrent.ArrayBlockingQueue;

/* JADX INFO: loaded from: classes.dex */
public class MessageReceiveHandler {
    private static final int QNX_BROADCAST_FLAG = 1;
    private static final int QNX_REPLY_FLAG = 0;
    private static final int QUEUE_MAX_SIZE = 100;
    private static String TAG = "MessageReceiveHandler";
    private QnxMessageListener mListener;
    private ReceiveHandler mReceiveHandler;
    public ArrayBlockingQueue<FdbusMessage> mQueue = new ArrayBlockingQueue<>(QUEUE_MAX_SIZE);
    private HandlerThread mReveiveMessageThread = new HandlerThread(TAG);

    public MessageReceiveHandler(QnxMessageListener messageListener) {
        this.mListener = null;
        LogUtil.logI(TAG, "MessageReceiveHandler construct method");
        this.mListener = messageListener;
    }

    protected void pushMessage(FdbusMessage receiveMessage, int flag, int msgType) {
        this.mQueue.offer(receiveMessage);
        if (flag == 0) {
            Message message = Message.obtain(this.mReceiveHandler, 0, msgType, 0);
            message.sendToTarget();
        } else if (1 == flag) {
            Message message2 = Message.obtain(this.mReceiveHandler, 2, msgType, 0);
            message2.sendToTarget();
        }
    }

    protected void startHandleMessage() {
        this.mReveiveMessageThread.start();
        this.mReceiveHandler = new ReceiveHandler(this.mReveiveMessageThread.getLooper());
    }

    private final class ReceiveHandler extends Handler {
        public ReceiveHandler(Looper looper) {
            super(looper);
        }

        @Override // android.os.Handler
        public void handleMessage(Message msg) {
            Bundle bundle;
            int i = msg.what;
            if (i != 0 && i != 2) {
                LogUtil.logI(MessageReceiveHandler.TAG, "Could not handleMessage: " + msg.what);
                return;
            }
            FdbusMessage msgOut = MessageReceiveHandler.this.mQueue.poll();
            if (msgOut != null && (bundle = FdbusUtil.getInstance().messageToBundle(msgOut, msg.what, msg.arg1)) != null) {
                MessageReceiveHandler.this.mListener.notifyListener(bundle);
            }
        }
    }
}
