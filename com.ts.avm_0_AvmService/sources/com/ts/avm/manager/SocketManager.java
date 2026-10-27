package com.ts.avm.manager;

import com.ts.avm.utils.socket.TcpSocketImpl;
import java.util.ArrayList;

/* JADX INFO: loaded from: classes.dex */
public abstract class SocketManager {
    public static final String TYPE_TCP = "tcp";
    private static volatile SocketManager tcpManager = null;
    protected MessageListener mMessageListener;
    protected final ArrayList<SocketStateListener> mSocketStateListenerList = new ArrayList<>();

    public static class Config {
        public String ip;
        public int port;
        public int receiveDataLength = -1;
    }

    public interface MessageListener {
        void onAckConfirmed();

        void onReceiveMessage(byte[] bArr);
    }

    public interface SocketStateListener {
        void onError();

        void onServerConnected();

        void onServerDisconnect();
    }

    public abstract void initSocket();

    @Deprecated
    public abstract void receiveMessage();

    public abstract void sendMessage(byte[] bArr);

    public abstract void socketConfig(Config config);

    protected SocketManager() {
    }

    public static synchronized SocketManager getInstance(String type) {
        if (tcpManager == null) {
            tcpManager = new TcpSocketImpl();
        }
        return tcpManager;
    }

    public void registerSocketListener(SocketStateListener listener) {
        synchronized (this.mSocketStateListenerList) {
            if (!this.mSocketStateListenerList.contains(listener)) {
                this.mSocketStateListenerList.add(listener);
            }
        }
    }

    public void registerMessageListener(MessageListener listener) {
        this.mMessageListener = listener;
    }
}
