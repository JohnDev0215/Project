package com.engine.tbox.netd;

import android.os.Handler;
import android.os.HandlerThread;
import android.os.Looper;
import android.os.Message;
import com.engine.tbox.core.utils.LogUtils;
import java.io.IOException;
import java.io.OutputStream;
import java.net.Socket;
import java.nio.charset.StandardCharsets;

/* JADX INFO: loaded from: classes.dex */
public class DscClientSocket {
    private static final long DELAY_TIME = 1000;
    private static final int SOCKET_ERROR = 2;
    private static final int SOCKET_INIT = 1;
    private static final String TAG = "TBOX_DscClientSocket";
    private Handler mHandler;
    private HandlerThread mHandlerThread;
    private OutputStream mOutputStream;
    private Socket mSocket;

    class DscHandler extends Handler {
        DscHandler(Looper looper) {
            super(looper);
        }

        @Override // android.os.Handler
        public void handleMessage(Message msg) {
            int i = msg.what;
            if (i == 1) {
                DscClientSocket.this.socketInit();
            } else if (i != 2) {
                DscClientSocket.this.socketCommands(msg);
            } else {
                DscClientSocket.this.socketError();
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void socketCommands(Message msg) {
        String str = (String) msg.obj;
        LogUtils.i(TAG, "socketCommands:" + str, new Object[0]);
        OutputStream outputStream = this.mOutputStream;
        if (outputStream != null) {
            try {
                outputStream.write(str.getBytes(StandardCharsets.UTF_8));
                return;
            } catch (IOException exp) {
                LogUtils.e(TAG, "socketCommands socket:", exp.getMessage());
                this.mHandler.sendEmptyMessage(2);
                return;
            }
        }
        LogUtils.w(TAG, "socketCommands ouputstream haven't been created", new Object[0]);
        Message cloneMsg = Message.obtain(msg);
        this.mHandler.sendMessageDelayed(cloneMsg, DELAY_TIME);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void socketError() {
        LogUtils.d(TAG, "socketError", new Object[0]);
        try {
            if (this.mSocket != null) {
                this.mSocket.close();
                this.mSocket = null;
            }
            if (this.mOutputStream != null) {
                this.mOutputStream.close();
                this.mOutputStream = null;
            }
        } catch (IOException exp1) {
            LogUtils.e(TAG, "socketError socket:", exp1.getMessage());
        }
        this.mHandler.sendEmptyMessageDelayed(1, DELAY_TIME);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void socketInit() {
        LogUtils.d(TAG, "socket init", new Object[0]);
        try {
            Socket socket = new Socket("127.0.0.1", 4183);
            this.mSocket = socket;
            this.mOutputStream = socket.getOutputStream();
        } catch (IOException exp) {
            LogUtils.e(TAG, "socket:", exp.getMessage());
            this.mHandler.sendEmptyMessage(2);
        }
    }

    public DscClientSocket() {
        HandlerThread handlerThread = new HandlerThread("DscClientSocket");
        this.mHandlerThread = handlerThread;
        handlerThread.start();
        DscHandler dscHandler = new DscHandler(this.mHandlerThread.getLooper());
        this.mHandler = dscHandler;
        dscHandler.sendEmptyMessage(1);
    }

    public void sendString(int identify, String str) {
        Message message = this.mHandler.obtainMessage();
        message.what = identify;
        message.obj = str;
        if (this.mHandler.hasMessages(identify)) {
            this.mHandler.removeMessages(identify);
            LogUtils.w(TAG, "remove the message:" + identify, new Object[0]);
        }
        this.mHandler.sendMessage(message);
    }
}
