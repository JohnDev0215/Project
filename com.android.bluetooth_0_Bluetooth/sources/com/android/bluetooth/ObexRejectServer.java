package com.android.bluetooth;

import android.bluetooth.BluetoothSocket;
import android.os.Handler;
import android.os.HandlerThread;
import android.os.Looper;
import android.os.Message;
import android.util.Log;
import java.io.IOException;
import javax.obex.HeaderSet;
import javax.obex.ServerRequestHandler;

/* JADX INFO: loaded from: classes.dex */
public class ObexRejectServer extends ServerRequestHandler implements Handler.Callback {
    private static final int MSG_ID_TIMEOUT = 1;
    private static final String TAG = "ObexRejectServer";
    private static final int TIMEOUT_VALUE = 5000;
    private static final boolean V = true;
    private final HandlerThread mHandlerThread;
    private final Handler mMessageHandler;
    private final int mResult;
    private final BluetoothSocket mSocket;

    public ObexRejectServer(int result, BluetoothSocket socket) {
        this.mResult = result;
        this.mSocket = socket;
        HandlerThread handlerThread = new HandlerThread("TestTimeoutHandler", 10);
        this.mHandlerThread = handlerThread;
        handlerThread.start();
        Looper timeoutLooper = this.mHandlerThread.getLooper();
        Handler handler = new Handler(timeoutLooper, this);
        this.mMessageHandler = handler;
        handler.sendEmptyMessageDelayed(1, 5000L);
    }

    public int onConnect(HeaderSet request, HeaderSet reply) {
        Log.i(TAG, "onConnect() returning error");
        return this.mResult;
    }

    public void shutdown() {
        this.mMessageHandler.removeCallbacksAndMessages(null);
        this.mHandlerThread.quit();
        try {
            this.mSocket.close();
        } catch (IOException e) {
            Log.w(TAG, "Unable to close socket - ignoring", e);
        }
    }

    @Override // android.os.Handler.Callback
    public boolean handleMessage(Message msg) {
        Log.i(TAG, "Handling message ID: " + msg.what);
        if (msg.what == 1) {
            shutdown();
            return true;
        }
        return false;
    }
}
