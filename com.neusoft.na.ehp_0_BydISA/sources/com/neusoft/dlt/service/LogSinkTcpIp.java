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
import java.util.LinkedList;
import java.util.List;

/* JADX INFO: loaded from: classes.dex */
public class LogSinkTcpIp implements ILogSink, Runnable {
    public static final String LOGTAG = "LogSinkTcpIp";
    private InetSocketAddress mAddress;
    private Selector mSelector = null;
    private ServerSocketChannel mServerSocketChannel = null;
    private List<Connection> mConnectedClients = new ArrayList();

    public LogSinkTcpIp(InetSocketAddress inetSocketAddress) {
        this.mAddress = inetSocketAddress;
    }

    private class Connection implements Runnable {
        private int MAX_QUEUE_SIZE;
        SocketChannel mSocket;
        private LinkedList<ByteBuffer> queue;
        private int queueSize;
        private SelectionKey selectionKey;

        public Connection(SocketChannel socketChannel) {
            this.MAX_QUEUE_SIZE = 1073741824;
            this.queue = new LinkedList<>();
            this.queueSize = 0;
            this.selectionKey = null;
            this.mSocket = socketChannel;
        }

        public Connection(SocketChannel socketChannel, int i) {
            this.MAX_QUEUE_SIZE = 1073741824;
            this.queue = new LinkedList<>();
            this.queueSize = 0;
            this.selectionKey = null;
            this.mSocket = socketChannel;
            this.MAX_QUEUE_SIZE = i;
            LogSinkTcpIp.this.addConnection(this);
        }

        @Override // java.lang.Runnable
        public void run() {
            boolean z;
            boolean z2 = false;
            loop0: while (true) {
                z = z2;
                while (!z2) {
                    try {
                        if (this.queue.isEmpty()) {
                            break loop0;
                        }
                        ByteBuffer byteBufferPeek = this.queue.peek();
                        if (byteBufferPeek != null) {
                            int iRemaining = byteBufferPeek.remaining();
                            int iWrite = this.mSocket.write(byteBufferPeek);
                            if (iWrite >= 0 && iWrite <= iRemaining) {
                                this.queueSize -= iWrite;
                                if (iWrite >= iRemaining) {
                                    this.queue.poll();
                                }
                            }
                            z2 = true;
                        }
                        z2 = true;
                    } catch (Exception unused) {
                        z = true;
                    }
                }
                break loop0;
            }
            if (z) {
                close();
            } else if (this.queue.size() > 0) {
                this.selectionKey.interestOps(4);
            } else {
                this.selectionKey.interestOps(0);
            }
        }

        public void queueMessage(ByteBuffer byteBuffer) {
            int iRemaining = byteBuffer.remaining();
            int i = 0;
            while (true) {
                int i2 = this.queueSize;
                if (i2 <= 0 || i2 + iRemaining <= this.MAX_QUEUE_SIZE) {
                    break;
                }
                i++;
                this.queueSize -= this.queue.poll().remaining();
            }
            if (i > 0) {
                Log.w(LogSinkTcpIp.LOGTAG, "dropped " + i + " message(s) for client at " + this.mSocket.socket().getInetAddress() + ":" + this.mSocket.socket().getPort());
            }
            boolean zIsEmpty = this.queue.isEmpty();
            this.queue.add(byteBuffer);
            this.queueSize += byteBuffer.remaining();
            if (zIsEmpty) {
                run();
            }
        }

        public void close() {
            try {
                this.mSocket.close();
                this.selectionKey.cancel();
                LogSinkTcpIp.this.removeConnection(this);
            } catch (IOException unused) {
            }
        }

        public void register(Selector selector) {
            try {
                this.selectionKey = this.mSocket.register(selector, 0, this);
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

    @Override // com.neusoft.dlt.service.ILogSink
    public void register(Selector selector) {
        this.mSelector = selector;
    }

    @Override // com.neusoft.dlt.service.ILogSink
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

    @Override // com.neusoft.dlt.service.ILogSink
    public boolean stop() {
        Log.d(DltDaemon.LOGTAG, "stopping LogSinkTcpIp");
        try {
            ServerSocketChannel serverSocketChannel = this.mServerSocketChannel;
            if (serverSocketChannel != null) {
                serverSocketChannel.close();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        while (this.mConnectedClients.size() > 0) {
            this.mConnectedClients.get(0).close();
        }
        return true;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void addConnection(Connection connection) {
        this.mConnectedClients.add(connection);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void removeConnection(Connection connection) {
        this.mConnectedClients.remove(connection);
    }

    @Override // com.neusoft.dlt.service.ILogSink
    public void logMessage(DltMessage dltMessage) {
        ByteBuffer byteBuffer = dltMessage.toByteBuffer();
        for (int size = this.mConnectedClients.size() - 1; size >= 0; size--) {
            this.mConnectedClients.get(size).queueMessage(byteBuffer.duplicate());
        }
    }
}
