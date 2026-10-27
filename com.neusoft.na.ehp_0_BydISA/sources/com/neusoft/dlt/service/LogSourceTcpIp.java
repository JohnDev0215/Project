package com.neusoft.dlt.service;

import android.util.Log;
import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.ClosedChannelException;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.ArrayList;
import rx.Observable;
import rx.subjects.BehaviorSubject;
import rx.subjects.PublishSubject;

/* JADX INFO: loaded from: classes.dex */
public class LogSourceTcpIp implements ILogSource, Runnable {
    private InetSocketAddress mAddress;
    private Selector mSelector = null;
    private ServerSocketChannel mServerSocketChannel = null;
    private ArrayList<Connection> mConnectedClients = new ArrayList<>();
    private BehaviorSubject<Integer> mClientCountSubject = BehaviorSubject.create(0);
    private PublishSubject<DltMessage> mMessagesSubject = PublishSubject.create();

    public LogSourceTcpIp(InetSocketAddress inetSocketAddress) {
        this.mAddress = inetSocketAddress;
    }

    @Override // com.neusoft.dlt.service.ILogSource
    public int getClientCount() {
        return this.mClientCountSubject.getValue().intValue();
    }

    private class Connection implements Runnable {
        SocketChannel mSocket;
        private ByteBuffer buffer = ByteBuffer.allocateDirect(DltMessage.DLT_MAXLENGTH);
        private SelectionKey selectionKey = null;

        public Connection(SocketChannel socketChannel) {
            this.mSocket = socketChannel;
            LogSourceTcpIp.this.addConnection(this);
        }

        @Override // java.lang.Runnable
        public void run() {
            try {
                int i = this.mSocket.read(this.buffer);
                if (i > 0) {
                    DltMessage dltMessage = DltMessage.read(this.buffer);
                    while (dltMessage != null) {
                        LogSourceTcpIp.this.mMessagesSubject.onNext(dltMessage);
                        dltMessage = DltMessage.read(this.buffer);
                    }
                }
                if (i < 0) {
                    close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        public void close() {
            try {
                this.mSocket.close();
                this.selectionKey.cancel();
            } catch (IOException unused) {
            }
            LogSourceTcpIp.this.removeConnection(this);
        }

        public void register(Selector selector) {
            try {
                this.selectionKey = this.mSocket.register(selector, 1, this);
            } catch (ClosedChannelException e) {
                e.printStackTrace();
            }
        }
    }

    @Override // java.lang.Runnable
    public void run() {
        try {
            SocketChannel socketChannelAccept = this.mServerSocketChannel.accept();
            socketChannelAccept.configureBlocking(false);
            new Connection(socketChannelAccept).register(this.mSelector);
            Log.i(DltDaemon.LOGTAG, "Connection Accepted: " + socketChannelAccept.socket().getInetAddress());
        } catch (IOException unused) {
            Log.w(DltDaemon.LOGTAG, "failed to open client connection");
        }
    }

    @Override // com.neusoft.dlt.service.ILogSource
    public void register(Selector selector) {
        this.mSelector = selector;
    }

    @Override // com.neusoft.dlt.service.ILogSource
    public boolean start() {
        try {
            ServerSocketChannel serverSocketChannelOpen = ServerSocketChannel.open();
            Log.d(DltDaemon.LOGTAG, "created server socket");
            serverSocketChannelOpen.configureBlocking(false);
            serverSocketChannelOpen.socket().setReuseAddress(true);
            serverSocketChannelOpen.socket().bind(this.mAddress);
            Log.i(DltDaemon.LOGTAG, "started listening on port " + this.mAddress.getPort());
            serverSocketChannelOpen.register(this.mSelector, serverSocketChannelOpen.validOps(), this);
            this.mServerSocketChannel = serverSocketChannelOpen;
            return true;
        } catch (IOException unused) {
            return false;
        }
    }

    @Override // com.neusoft.dlt.service.ILogSource
    public boolean stop() {
        Log.d(DltDaemon.LOGTAG, "stopping logger");
        try {
            this.mServerSocketChannel.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        while (this.mConnectedClients.size() > 0) {
            this.mConnectedClients.get(0).close();
        }
        return true;
    }

    @Override // com.neusoft.dlt.service.ILogSource
    public Observable<Integer> getClientCountObservable() {
        return this.mClientCountSubject.asObservable();
    }

    @Override // com.neusoft.dlt.service.ILogSource
    public Observable<DltMessage> getMessageObservable() {
        return this.mMessagesSubject.asObservable();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void addConnection(Connection connection) {
        this.mConnectedClients.add(connection);
        this.mClientCountSubject.onNext(Integer.valueOf(this.mConnectedClients.size()));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void removeConnection(Connection connection) {
        this.mConnectedClients.remove(connection);
        this.mClientCountSubject.onNext(Integer.valueOf(this.mConnectedClients.size()));
    }
}
