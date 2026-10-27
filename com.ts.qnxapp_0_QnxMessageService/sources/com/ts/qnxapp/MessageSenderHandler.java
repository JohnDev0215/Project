package com.ts.qnxapp;

import android.os.Handler;
import android.os.HandlerThread;
import android.os.Looper;
import android.os.Message;
import com.ts.qnxapp.fdbusClientForAndroid.AndroidFdbusClientManager;
import com.ts.qnxapp.fdbusClientForAndroid.AndroidFdbusServerManager;
import com.ts.qnxapp.util.LogUtil;
import java.util.concurrent.ArrayBlockingQueue;

/* JADX INFO: loaded from: classes.dex */
public class MessageSenderHandler {
    private static final int QUEUE_MAX_SIZE = 100;
    private static String TAG = "MessageSenderHandler";
    private AndroidFdbusClientManager mClientManager;
    public ArrayBlockingQueue<IpcMessage> mQueue = new ArrayBlockingQueue<>(QUEUE_MAX_SIZE);
    private HandlerThread mSendMessageThread = new HandlerThread(TAG);
    private SenderHandler mSenderHandler;
    private AndroidFdbusServerManager mServerManager;

    public MessageSenderHandler(AndroidFdbusClientManager clientManager, AndroidFdbusServerManager serverManager) {
        this.mClientManager = null;
        this.mServerManager = null;
        LogUtil.logI(TAG, "MessageSenderHandler construct method");
        this.mClientManager = clientManager;
        this.mServerManager = serverManager;
    }

    protected void pushMessage(IpcMessage ipcMessage) {
        this.mQueue.offer(ipcMessage);
        Message message = Message.obtain(this.mSenderHandler, 1);
        message.sendToTarget();
    }

    protected void startHandleMessage() {
        this.mSendMessageThread.start();
        this.mSenderHandler = new SenderHandler(this.mSendMessageThread.getLooper());
    }

    private final class SenderHandler extends Handler {
        public SenderHandler(Looper looper) {
            super(looper);
        }

        /* JADX WARN: Removed duplicated region for block: B:26:0x0056  */
        /* JADX WARN: Removed duplicated region for block: B:27:0x0068  */
        @Override // android.os.Handler
        /*
            Code decompiled incorrectly, please refer to instructions dump.
            To view partially-correct code enable 'Show inconsistent code' option in preferences
        */
        public void handleMessage(android.os.Message r8) {
            /*
                r7 = this;
                int r0 = r8.what
                r1 = 1
                if (r0 == r1) goto L21
                java.lang.String r0 = com.ts.qnxapp.MessageSenderHandler.access$200()
                java.lang.StringBuilder r1 = new java.lang.StringBuilder
                r1.<init>()
                java.lang.String r2 = "Could not handleMessage: "
                r1.append(r2)
                int r2 = r8.what
                r1.append(r2)
                java.lang.String r1 = r1.toString()
                com.ts.qnxapp.util.LogUtil.logI(r0, r1)
                goto L8e
            L21:
                com.ts.qnxapp.MessageSenderHandler r0 = com.ts.qnxapp.MessageSenderHandler.this
                java.util.concurrent.ArrayBlockingQueue<com.ts.qnxapp.IpcMessage> r0 = r0.mQueue
                if (r0 == 0) goto L8e
                com.ts.qnxapp.MessageSenderHandler r0 = com.ts.qnxapp.MessageSenderHandler.this
                java.util.concurrent.ArrayBlockingQueue<com.ts.qnxapp.IpcMessage> r0 = r0.mQueue
                int r0 = r0.size()
                if (r0 == 0) goto L8e
                com.ts.qnxapp.MessageSenderHandler r0 = com.ts.qnxapp.MessageSenderHandler.this
                java.util.concurrent.ArrayBlockingQueue<com.ts.qnxapp.IpcMessage> r0 = r0.mQueue
                java.lang.Object r0 = r0.poll()
                com.ts.qnxapp.IpcMessage r0 = (com.ts.qnxapp.IpcMessage) r0
                if (r0 == 0) goto L79
                int r2 = r0.msgType
                if (r2 == r1) goto L68
                r3 = 2
                if (r2 == r3) goto L68
                r3 = 3
                if (r2 == r3) goto L68
                r3 = 4
                if (r2 == r3) goto L56
                r3 = 6
                if (r2 == r3) goto L68
                r3 = 8
                if (r2 == r3) goto L56
                r3 = 9
                if (r2 == r3) goto L68
                goto L79
            L56:
                com.ts.qnxapp.MessageSenderHandler r2 = com.ts.qnxapp.MessageSenderHandler.this
                com.ts.qnxapp.fdbusClientForAndroid.AndroidFdbusClientManager r2 = com.ts.qnxapp.MessageSenderHandler.access$000(r2)
                int r3 = r0.msgType
                int r4 = r0.subType
                java.lang.String r5 = r0.content
                android.os.Bundle r6 = r0.bundle
                r2.sendMessageToQnx(r3, r4, r5, r6)
                goto L79
            L68:
                com.ts.qnxapp.MessageSenderHandler r2 = com.ts.qnxapp.MessageSenderHandler.this
                com.ts.qnxapp.fdbusClientForAndroid.AndroidFdbusServerManager r2 = com.ts.qnxapp.MessageSenderHandler.access$100(r2)
                int r3 = r0.msgType
                int r4 = r0.subType
                java.lang.String r5 = r0.content
                android.os.Bundle r6 = r0.bundle
                r2.sendMessageToQnx(r3, r4, r5, r6)
            L79:
                com.ts.qnxapp.MessageSenderHandler r2 = com.ts.qnxapp.MessageSenderHandler.this
                java.util.concurrent.ArrayBlockingQueue<com.ts.qnxapp.IpcMessage> r2 = r2.mQueue
                boolean r2 = r2.isEmpty()
                if (r2 == 0) goto L8d
                java.lang.String r1 = com.ts.qnxapp.MessageSenderHandler.access$200()
                java.lang.String r2 = "now the queue is null"
                com.ts.qnxapp.util.LogUtil.logI(r1, r2)
                goto L8e
            L8d:
                goto L21
            L8e:
                return
            */
            throw new UnsupportedOperationException("Method not decompiled: com.ts.qnxapp.MessageSenderHandler.SenderHandler.handleMessage(android.os.Message):void");
        }
    }
}
