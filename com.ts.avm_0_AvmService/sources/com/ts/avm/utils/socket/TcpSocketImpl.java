package com.ts.avm.utils.socket;

import android.os.Handler;
import android.os.HandlerThread;
import android.os.Looper;
import android.os.Message;
import com.ts.avm.manager.SocketManager;
import com.ts.avm.utils.LogManager;
import com.ts.avm.utils.wrappers.VideoCatCmd;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.util.concurrent.atomic.AtomicBoolean;

/* JADX INFO: loaded from: classes.dex */
public class TcpSocketImpl extends SocketManager {
    private static final long READ_SLEEP_TIME = 1000;
    private static final long READ_SOCKET_SLEEP_TIME = 50;
    private static final int SEND_MESSAGE = 2;
    private static final int SOCKET_CONNECT_TIMEOUT = 10000;
    private static final long SOCKET_RECONNECT_DELAYED = 10000;
    private static final int SOCKET_TRY_CONNECT = 1;
    private static final String TAG = TcpSocketImpl.class.getSimpleName();
    private final AtomicBoolean initFlag = new AtomicBoolean(false);
    private HandlerThread mHandlerSendThread;
    private HandlerThread mHandlerThread;
    private InputStream mInputStream;
    private OutputStream mOutputStream;
    private Socket mSocket;
    private SocketManager.Config mSocketConfig;
    private Handler mSocketHandler;
    private Handler mSocketSendHandler;

    public TcpSocketImpl() {
        LogManager.logw(TAG, "TcpSocketImpl construct TIME");
        HandlerThread handlerThread = new HandlerThread(TcpSocketImpl.class.getSimpleName());
        this.mHandlerThread = handlerThread;
        handlerThread.start();
        this.mSocketHandler = new LocalMessageHandler(this.mHandlerThread.getLooper());
        HandlerThread handlerThread2 = new HandlerThread(TcpSocketImpl.class.getSimpleName() + "_send");
        this.mHandlerSendThread = handlerThread2;
        handlerThread2.start();
        this.mSocketSendHandler = new LocalMessageHandler(this.mHandlerSendThread.getLooper());
    }

    @Override // com.ts.avm.manager.SocketManager
    public void initSocket() {
        if (this.initFlag.get()) {
            LogManager.loge(TAG, "initSocket: HAS bean initialized.");
        } else {
            this.initFlag.set(true);
            this.mSocketHandler.sendEmptyMessage(1);
        }
    }

    public class LocalMessageHandler extends Handler {
        public LocalMessageHandler(Looper looper) {
            super(looper);
        }

        @Override // android.os.Handler
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            int i = msg.what;
            if (i == 1) {
                LogManager.logd(TcpSocketImpl.TAG, "SOCKET_TRY_CONNECT");
                TcpSocketImpl.this.connectService();
            } else if (i == 2) {
                TcpSocketImpl.this.send((byte[]) msg.obj);
            }
        }
    }

    @Override // com.ts.avm.manager.SocketManager
    public void socketConfig(SocketManager.Config config) {
        this.mSocketConfig = config;
    }

    @Override // com.ts.avm.manager.SocketManager
    public void sendMessage(byte[] data) {
        LogManager.logd(TAG, "sendMessage TIME");
        synchronized (this) {
            Message massage = Message.obtain();
            massage.what = 2;
            massage.obj = data;
            this.mSocketSendHandler.sendMessage(massage);
        }
    }

    public void send(byte[] data) {
        LogManager.logd(TAG, "sendMessage: start");
        synchronized (this) {
            if (this.mOutputStream == null) {
                try {
                    if (this.mSocket == null) {
                        LogManager.logw(TAG, "sendMessage: mSocket is null");
                        return;
                    }
                    this.mOutputStream = this.mSocket.getOutputStream();
                    try {
                        this.mOutputStream.write(data);
                        this.mOutputStream.flush();
                        LogManager.logw(TAG, "sendMessage: end byte send length = " + data.length + "rLen:" + this.mSocketConfig.receiveDataLength);
                    } catch (IOException e) {
                        reconnectSocket();
                    }
                    return;
                } catch (IOException ex) {
                    LogManager.logw(TAG, "sendMessage: " + ex);
                    reconnectSocket();
                    return;
                }
            }
            this.mOutputStream.write(data);
            this.mOutputStream.flush();
            LogManager.logw(TAG, "sendMessage: end byte send length = " + data.length + "rLen:" + this.mSocketConfig.receiveDataLength);
            return;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public int connectService() {
        LogManager.logd(TAG, "try connectService: rLen = " + this.mSocketConfig.receiveDataLength);
        if (this.mSocketConfig == null) {
            LogManager.loge(TAG, "connectService: without socket config, please called socketConfig function first.");
            releaseSocket();
            socketStatePostError();
            return -1;
        }
        this.mSocket = new Socket();
        try {
            InetSocketAddress socketAddress = new InetSocketAddress(this.mSocketConfig.ip, this.mSocketConfig.port);
            try {
                this.mSocket.setKeepAlive(true);
                this.mSocket.setTcpNoDelay(true);
                LogManager.logd(TAG, "mSocket setTcpNoDelay true");
                try {
                    this.mSocket.setSoTimeout(Integer.MAX_VALUE);
                    try {
                        this.mSocket.connect(socketAddress, SOCKET_CONNECT_TIMEOUT);
                        LogManager.logw(TAG, "connectService end to startReceiveMessage");
                        socketStatePostConnected();
                        startReceiveMessage();
                        return 0;
                    } catch (Exception e) {
                        LogManager.logw(TAG, "connectService: connect failed msg " + e.getMessage());
                        releaseSocket();
                        socketStatePostError();
                        if (this.initFlag.get()) {
                            this.mSocketHandler.sendEmptyMessageDelayed(1, SOCKET_RECONNECT_DELAYED);
                        }
                        return -1;
                    }
                } catch (Exception e2) {
                    LogManager.logw(TAG, "connectService: setSoTimeout illegal");
                    releaseSocket();
                    socketStatePostError();
                    return -1;
                }
            } catch (Exception e3) {
                LogManager.logw(TAG, "connectService: setKeepAlive illegal");
                releaseSocket();
                socketStatePostError();
                return -1;
            }
        } catch (IllegalArgumentException e4) {
            LogManager.logw(TAG, "connectService: ip or port or both are illegal.");
            releaseSocket();
            socketStatePostError();
            return -1;
        }
    }

    @Override // com.ts.avm.manager.SocketManager
    public void receiveMessage() {
    }

    private void startReceiveMessage() {
        byte[] dataArr;
        if (this.mSocketConfig.receiveDataLength != -1) {
            dataArr = new byte[this.mSocketConfig.receiveDataLength];
        } else {
            dataArr = new byte[64];
        }
        if (this.initFlag.get()) {
            try {
                if (this.mSocket == null) {
                    LogManager.loge(TAG, "startReceiveMessage: startReceiveMessage: mSocket is null");
                    return;
                }
                InputStream socketInputStream = this.mSocket.getInputStream();
                int readErrorLengthCount = 0;
                int readIoErrorCount = 0;
                while (this.initFlag.get()) {
                    try {
                        int len = socketInputStream.read(dataArr);
                        LogManager.logw(TAG, "receivData, len:" + len + " rLen:" + this.mSocketConfig.receiveDataLength);
                        if (len == 64) {
                            if (this.mMessageListener != null) {
                                this.mMessageListener.onReceiveMessage(dataArr);
                            }
                        } else if (len == -1) {
                            readErrorLengthCount++;
                            if (readErrorLengthCount >= 100) {
                                break;
                            } else {
                                Thread.sleep(READ_SOCKET_SLEEP_TIME);
                            }
                        } else if (len == 4) {
                            int ack = VideoCatCmd.bytes2int(dataArr, 0);
                            if (ack == 11) {
                                this.mMessageListener.onAckConfirmed();
                            }
                        } else if (len == this.mSocketConfig.receiveDataLength) {
                            LogManager.logd(TAG, "receiveAvmActionCallBack:" + this.mMessageListener);
                            this.mMessageListener.onReceiveMessage(dataArr);
                        } else {
                            LogManager.loge(TAG, "startReceiveMessage: receiveData len error, len:" + len);
                        }
                        Thread.sleep(READ_SOCKET_SLEEP_TIME);
                    } catch (IOException ex) {
                        LogManager.logw(TAG, "startReceiveMessage: " + ex);
                        readIoErrorCount++;
                        if (readIoErrorCount <= 100) {
                            Thread.sleep(READ_SLEEP_TIME);
                        } else {
                            LogManager.loge(TAG, "Socket Error do reconnectSocket");
                            reconnectSocket();
                        }
                    }
                }
            } catch (Throwable th) {
                LogManager.logw(TAG, "getInputStream error, should reconnect to server.");
                try {
                    Thread.sleep(READ_SLEEP_TIME);
                } catch (InterruptedException e) {
                    LogManager.logd(TAG, "sleep InterruptedException.");
                }
            }
        }
        LogManager.loge(TAG, "Socket Error do reconnectSocket");
        reconnectSocket();
    }

    private void releaseSocket() {
        this.initFlag.set(false);
        LogManager.logi(TAG, "releaseSocket: releaseSocketResource start");
        OutputStream outputStream = this.mOutputStream;
        if (outputStream != null) {
            try {
                outputStream.close();
                this.mOutputStream = null;
            } catch (IOException e) {
            }
        }
        InputStream inputStream = this.mInputStream;
        if (inputStream != null) {
            try {
                inputStream.close();
                this.mInputStream = null;
            } catch (IOException e2) {
            }
        }
        Socket socket = this.mSocket;
        if (socket != null) {
            try {
                socket.close();
                this.mSocket = null;
            } catch (IOException e3) {
            }
        }
        LogManager.logd(TAG, "releaseSocket: releaseSocketResource end");
    }

    private void reconnectSocket() {
        LogManager.logw(TAG, "reconnectSocket: Socket error, should reconnect server");
        if (this.initFlag.get()) {
            this.initFlag.set(false);
            releaseSocket();
            socketStatePostError();
        }
    }

    private void socketStatePostConnected() {
        synchronized (this.mSocketStateListenerList) {
            for (SocketManager.SocketStateListener listener : this.mSocketStateListenerList) {
                if (listener != null) {
                    listener.onServerConnected();
                }
            }
        }
    }

    private void socketStatePostError() {
        synchronized (this.mSocketStateListenerList) {
            for (SocketManager.SocketStateListener listener : this.mSocketStateListenerList) {
                if (listener != null) {
                    listener.onError();
                }
            }
        }
    }

    private void socketStatePostDisconnected() {
        synchronized (this.mSocketStateListenerList) {
            for (SocketManager.SocketStateListener listener : this.mSocketStateListenerList) {
                if (listener != null) {
                    listener.onServerDisconnect();
                }
            }
        }
    }
}
