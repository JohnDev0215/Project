package com.neusoft.na.dltdroid.service;

import android.util.Log;
import androidx.core.app.NotificationCompat;
import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: LogSinkTcpIp.kt */
/* JADX INFO: loaded from: classes2.dex */
@Metadata(d1 = {"\u0000D\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u000b\n\u0002\b\u0004\u0018\u0000 \u001c2\u00020\u00012\u00020\u0002:\u0002\u001c\u001dB\r\u0012\u0006\u0010\u0003\u001a\u00020\u0004¢\u0006\u0002\u0010\u0005J\u0014\u0010\u000f\u001a\u00020\u00102\n\u0010\u0011\u001a\u00060\nR\u00020\u0000H\u0002J\u0010\u0010\u0012\u001a\u00020\u00102\u0006\u0010\u0013\u001a\u00020\u0014H\u0016J\u0010\u0010\u0015\u001a\u00020\u00102\u0006\u0010\u0016\u001a\u00020\fH\u0016J\u0014\u0010\u0017\u001a\u00020\u00102\n\u0010\u0011\u001a\u00060\nR\u00020\u0000H\u0002J\b\u0010\u0018\u001a\u00020\u0010H\u0016J\b\u0010\u0019\u001a\u00020\u001aH\u0016J\b\u0010\u001b\u001a\u00020\u001aH\u0016R\u0011\u0010\u0003\u001a\u00020\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0006\u0010\u0007R\u0018\u0010\b\u001a\f\u0012\b\u0012\u00060\nR\u00020\u00000\tX\u0082\u0004¢\u0006\u0002\n\u0000R\u0010\u0010\u000b\u001a\u0004\u0018\u00010\fX\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\r\u001a\u0004\u0018\u00010\u000eX\u0082\u000e¢\u0006\u0002\n\u0000¨\u0006\u001e"}, d2 = {"Lcom/neusoft/na/dltdroid/service/LogSinkTcpIp;", "Lcom/neusoft/na/dltdroid/service/ILogSink;", "Ljava/lang/Runnable;", "mAddress", "Ljava/net/InetSocketAddress;", "(Ljava/net/InetSocketAddress;)V", "getMAddress", "()Ljava/net/InetSocketAddress;", "mConnectedClients", "Ljava/util/ArrayList;", "Lcom/neusoft/na/dltdroid/service/LogSinkTcpIp$Connection;", "mSelector", "Ljava/nio/channels/Selector;", "mServerSocketChannel", "Ljava/nio/channels/ServerSocketChannel;", "addConnection", "", "connection", "logMessage", NotificationCompat.CATEGORY_MESSAGE, "Lcom/neusoft/na/dltdroid/service/DltMessage;", "register", "selector", "removeConnection", "run", "start", "", "stop", "Companion", "Connection", "dltdroid-9.25.21.1a-G_NEU_release"}, k = 1, mv = {1, 7, 1}, xi = 48)
public final class LogSinkTcpIp implements ILogSink, Runnable {

    /* JADX INFO: renamed from: Companion, reason: from kotlin metadata */
    public static final Companion INSTANCE = new Companion(null);
    private static final String LOGTAG = "LogSinkTcpIp";
    private final InetSocketAddress mAddress;
    private final ArrayList<Connection> mConnectedClients;
    private Selector mSelector;
    private ServerSocketChannel mServerSocketChannel;

    public LogSinkTcpIp(InetSocketAddress mAddress) {
        Intrinsics.checkNotNullParameter(mAddress, "mAddress");
        this.mAddress = mAddress;
        this.mConnectedClients = new ArrayList<>();
    }

    public final InetSocketAddress getMAddress() {
        return this.mAddress;
    }

    /* JADX INFO: compiled from: LogSinkTcpIp.kt */
    @Metadata(d1 = {"\u0000:\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0002\b\u0082\u0004\u0018\u00002\u00020\u0001B\u0017\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\b\b\u0002\u0010\u0004\u001a\u00020\u0005¢\u0006\u0002\u0010\u0006J\u0006\u0010\u000f\u001a\u00020\u0010J\u000e\u0010\u0011\u001a\u00020\u00102\u0006\u0010\u0012\u001a\u00020\u000bJ\u0010\u0010\u0013\u001a\u00020\u00102\b\u0010\u0014\u001a\u0004\u0018\u00010\u0015J\b\u0010\u0016\u001a\u00020\u0010H\u0016R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082\u0004¢\u0006\u0002\n\u0000R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0007\u0010\bR\u0014\u0010\t\u001a\b\u0012\u0004\u0012\u00020\u000b0\nX\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\f\u001a\u00020\u0005X\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\r\u001a\u0004\u0018\u00010\u000eX\u0082\u000e¢\u0006\u0002\n\u0000¨\u0006\u0017"}, d2 = {"Lcom/neusoft/na/dltdroid/service/LogSinkTcpIp$Connection;", "Ljava/lang/Runnable;", "mSocket", "Ljava/nio/channels/SocketChannel;", "MAX_QUEUE_SIZE", "", "(Lcom/neusoft/na/dltdroid/service/LogSinkTcpIp;Ljava/nio/channels/SocketChannel;I)V", "getMSocket", "()Ljava/nio/channels/SocketChannel;", "queue", "Ljava/util/LinkedList;", "Ljava/nio/ByteBuffer;", "queueSize", "selectionKey", "Ljava/nio/channels/SelectionKey;", "close", "", "queueMessage", "buffer", "register", "selector", "Ljava/nio/channels/Selector;", "run", "dltdroid-9.25.21.1a-G_NEU_release"}, k = 1, mv = {1, 7, 1}, xi = 48)
    private final class Connection implements Runnable {
        private final int MAX_QUEUE_SIZE;
        private final SocketChannel mSocket;
        private final LinkedList<ByteBuffer> queue;
        private int queueSize;
        private SelectionKey selectionKey;
        final /* synthetic */ LogSinkTcpIp this$0;

        public Connection(LogSinkTcpIp logSinkTcpIp, SocketChannel mSocket, int i) {
            Intrinsics.checkNotNullParameter(mSocket, "mSocket");
            this.this$0 = logSinkTcpIp;
            this.mSocket = mSocket;
            this.MAX_QUEUE_SIZE = i;
            this.queue = new LinkedList<>();
            logSinkTcpIp.addConnection(this);
        }

        public /* synthetic */ Connection(LogSinkTcpIp logSinkTcpIp, SocketChannel socketChannel, int i, int i2, DefaultConstructorMarker defaultConstructorMarker) {
            this(logSinkTcpIp, socketChannel, (i2 & 2) != 0 ? 1073741824 : i);
        }

        public final SocketChannel getMSocket() {
            return this.mSocket;
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
                return;
            }
            if (this.queue.size() > 0) {
                SelectionKey selectionKey = this.selectionKey;
                if (selectionKey != null) {
                    selectionKey.interestOps(4);
                    return;
                }
                return;
            }
            SelectionKey selectionKey2 = this.selectionKey;
            if (selectionKey2 != null) {
                selectionKey2.interestOps(0);
            }
        }

        public final void queueMessage(ByteBuffer buffer) {
            Intrinsics.checkNotNullParameter(buffer, "buffer");
            int iRemaining = buffer.remaining();
            int i = 0;
            while (true) {
                int i2 = this.queueSize;
                if (i2 <= 0 || i2 + iRemaining <= this.MAX_QUEUE_SIZE) {
                    break;
                }
                i++;
                ByteBuffer byteBufferPoll = this.queue.poll();
                if (byteBufferPoll != null) {
                    this.queueSize -= byteBufferPoll.remaining();
                }
            }
            if (i > 0) {
                Log.w(LogSinkTcpIp.INSTANCE.getLOGTAG(), "dropped " + i + " message(s) for client at " + this.mSocket.socket().getInetAddress() + ':' + this.mSocket.socket().getPort());
            }
            boolean zIsEmpty = this.queue.isEmpty();
            this.queue.add(buffer);
            this.queueSize += buffer.remaining();
            if (zIsEmpty) {
                run();
            }
        }

        public final void close() {
            try {
                this.mSocket.close();
                SelectionKey selectionKey = this.selectionKey;
                if (selectionKey != null) {
                    selectionKey.cancel();
                }
                this.this$0.removeConnection(this);
            } catch (IOException unused) {
            }
        }

        public final void register(Selector selector) {
            this.selectionKey = this.mSocket.register(selector, 0, this);
        }
    }

    @Override // java.lang.Runnable
    public void run() {
        ServerSocketChannel serverSocketChannel = this.mServerSocketChannel;
        if (serverSocketChannel != null) {
            try {
                SocketChannel client = serverSocketChannel.accept();
                client.configureBlocking(false);
                Intrinsics.checkNotNullExpressionValue(client, "client");
                new Connection(this, client, 0, 2, null).register(this.mSelector);
                Log.i(DltDaemon.INSTANCE.getLOGTAG(), "Connection Accepted: " + client.socket().getInetAddress());
            } catch (IOException unused) {
                Log.w(DltDaemon.INSTANCE.getLOGTAG(), "failed to open client connection");
            }
        }
    }

    @Override // com.neusoft.na.dltdroid.service.ILogSink
    public void register(Selector selector) {
        Intrinsics.checkNotNullParameter(selector, "selector");
        this.mSelector = selector;
    }

    @Override // com.neusoft.na.dltdroid.service.ILogSink
    public boolean start() throws IOException {
        try {
            ServerSocketChannel serverSocketChannelOpen = ServerSocketChannel.open();
            if (serverSocketChannelOpen != null) {
                Log.d(DltDaemon.INSTANCE.getLOGTAG(), "created server socket");
                serverSocketChannelOpen.configureBlocking(false);
                serverSocketChannelOpen.socket().setReuseAddress(true);
                serverSocketChannelOpen.socket().bind(this.mAddress);
                Log.i(DltDaemon.INSTANCE.getLOGTAG(), "started listening on port " + this.mAddress.getPort());
                serverSocketChannelOpen.register(this.mSelector, serverSocketChannelOpen.validOps(), this);
                this.mServerSocketChannel = serverSocketChannelOpen;
                return true;
            }
        } catch (IOException unused) {
        }
        return false;
    }

    @Override // com.neusoft.na.dltdroid.service.ILogSink
    public boolean stop() {
        Log.d(DltDaemon.INSTANCE.getLOGTAG(), "stopping LogSinkTcpIp");
        ServerSocketChannel serverSocketChannel = this.mServerSocketChannel;
        if (serverSocketChannel != null) {
            serverSocketChannel.close();
        }
        while (this.mConnectedClients.size() > 0) {
            ((Connection) CollectionsKt.first((List) this.mConnectedClients)).close();
        }
        return true;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void addConnection(Connection connection) {
        this.mConnectedClients.add(connection);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void removeConnection(Connection connection) {
        this.mConnectedClients.remove(connection);
    }

    @Override // com.neusoft.na.dltdroid.service.ILogSink
    public void logMessage(DltMessage msg) {
        Intrinsics.checkNotNullParameter(msg, "msg");
        ByteBuffer byteBuffer = msg.toByteBuffer();
        int size = this.mConnectedClients.size() - 1;
        if (size < 0) {
            return;
        }
        while (true) {
            int i = size - 1;
            try {
                Connection connection = this.mConnectedClients.get(size);
                ByteBuffer byteBufferDuplicate = byteBuffer.duplicate();
                Intrinsics.checkNotNullExpressionValue(byteBufferDuplicate, "b.duplicate()");
                connection.queueMessage(byteBufferDuplicate);
            } catch (InterruptedException unused) {
                Log.w(LOGTAG, "failed to queue message!");
            }
            if (i < 0) {
                return;
            } else {
                size = i;
            }
        }
    }

    /* JADX INFO: compiled from: LogSinkTcpIp.kt */
    @Metadata(d1 = {"\u0000\u0014\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0003\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002R\u0014\u0010\u0003\u001a\u00020\u0004X\u0086D¢\u0006\b\n\u0000\u001a\u0004\b\u0005\u0010\u0006¨\u0006\u0007"}, d2 = {"Lcom/neusoft/na/dltdroid/service/LogSinkTcpIp$Companion;", "", "()V", "LOGTAG", "", "getLOGTAG", "()Ljava/lang/String;", "dltdroid-9.25.21.1a-G_NEU_release"}, k = 1, mv = {1, 7, 1}, xi = 48)
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }

        public final String getLOGTAG() {
            return LogSinkTcpIp.LOGTAG;
        }
    }
}
