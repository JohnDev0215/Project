package com.neusoft.na.dltdroid.service;

import android.util.Log;
import com.neusoft.na.dltdroid.service.DltMessage;
import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.ArrayList;
import java.util.List;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.internal.Intrinsics;
import rx.Observable;
import rx.subjects.BehaviorSubject;
import rx.subjects.PublishSubject;

/* JADX INFO: compiled from: LogSourceTcpIp.kt */
/* JADX INFO: loaded from: classes2.dex */
@Metadata(d1 = {"\u0000Z\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0010\u000b\n\u0002\b\u0003\u0018\u00002\u00020\u00012\u00020\u0002:\u0001$B\r\u0012\u0006\u0010\u0003\u001a\u00020\u0004¢\u0006\u0002\u0010\u0005J\u0014\u0010\u0016\u001a\u00020\u00172\n\u0010\u0018\u001a\u00060\u000eR\u00020\u0000H\u0002J\b\u0010\u0019\u001a\u00020\nH\u0016J\u000e\u0010\u001a\u001a\b\u0012\u0004\u0012\u00020\n0\u001bH\u0016J\u000e\u0010\u001c\u001a\b\u0012\u0004\u0012\u00020\u00110\u001bH\u0016J\u0010\u0010\u001d\u001a\u00020\u00172\u0006\u0010\u001e\u001a\u00020\u0013H\u0016J\u0014\u0010\u001f\u001a\u00020\u00172\n\u0010\u0018\u001a\u00060\u000eR\u00020\u0000H\u0002J\b\u0010 \u001a\u00020\u0017H\u0016J\b\u0010!\u001a\u00020\"H\u0016J\b\u0010#\u001a\u00020\"H\u0016R\u0011\u0010\u0003\u001a\u00020\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0006\u0010\u0007R2\u0010\b\u001a&\u0012\f\u0012\n \u000b*\u0004\u0018\u00010\n0\n \u000b*\u0012\u0012\f\u0012\n \u000b*\u0004\u0018\u00010\n0\n\u0018\u00010\t0\tX\u0082\u0004¢\u0006\u0002\n\u0000R\u0018\u0010\f\u001a\f\u0012\b\u0012\u00060\u000eR\u00020\u00000\rX\u0082\u0004¢\u0006\u0002\n\u0000R2\u0010\u000f\u001a&\u0012\f\u0012\n \u000b*\u0004\u0018\u00010\u00110\u0011 \u000b*\u0012\u0012\f\u0012\n \u000b*\u0004\u0018\u00010\u00110\u0011\u0018\u00010\u00100\u0010X\u0082\u0004¢\u0006\u0002\n\u0000R\u0010\u0010\u0012\u001a\u0004\u0018\u00010\u0013X\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\u0014\u001a\u0004\u0018\u00010\u0015X\u0082\u000e¢\u0006\u0002\n\u0000¨\u0006%"}, d2 = {"Lcom/neusoft/na/dltdroid/service/LogSourceTcpIp;", "Lcom/neusoft/na/dltdroid/service/ILogSource;", "Ljava/lang/Runnable;", "mAddress", "Ljava/net/InetSocketAddress;", "(Ljava/net/InetSocketAddress;)V", "getMAddress", "()Ljava/net/InetSocketAddress;", "mClientCountSubject", "Lrx/subjects/BehaviorSubject;", "", "kotlin.jvm.PlatformType", "mConnectedClients", "Ljava/util/ArrayList;", "Lcom/neusoft/na/dltdroid/service/LogSourceTcpIp$Connection;", "mMessagesSubject", "Lrx/subjects/PublishSubject;", "Lcom/neusoft/na/dltdroid/service/DltMessage;", "mSelector", "Ljava/nio/channels/Selector;", "mServerSocketChannel", "Ljava/nio/channels/ServerSocketChannel;", "addConnection", "", "conn", "getClientCount", "getClientCountObservable", "Lrx/Observable;", "getMessageObservable", "register", "selector", "removeConnection", "run", "start", "", "stop", "Connection", "dltdroid-9.25.21.1a-G_NEU_release"}, k = 1, mv = {1, 7, 1}, xi = 48)
public final class LogSourceTcpIp implements ILogSource, Runnable {
    private final InetSocketAddress mAddress;
    private final BehaviorSubject<Integer> mClientCountSubject;
    private final ArrayList<Connection> mConnectedClients;
    private final PublishSubject<DltMessage> mMessagesSubject;
    private Selector mSelector;
    private ServerSocketChannel mServerSocketChannel;

    public LogSourceTcpIp(InetSocketAddress mAddress) {
        Intrinsics.checkNotNullParameter(mAddress, "mAddress");
        this.mAddress = mAddress;
        this.mConnectedClients = new ArrayList<>();
        this.mClientCountSubject = BehaviorSubject.create(0);
        this.mMessagesSubject = PublishSubject.create();
    }

    public final InetSocketAddress getMAddress() {
        return this.mAddress;
    }

    @Override // com.neusoft.na.dltdroid.service.ILogSource
    public int getClientCount() {
        Integer value = this.mClientCountSubject.getValue();
        Intrinsics.checkNotNullExpressionValue(value, "mClientCountSubject.value");
        return value.intValue();
    }

    /* JADX INFO: compiled from: LogSourceTcpIp.kt */
    @Metadata(d1 = {"\u00000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\b\u0082\u0004\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004J\u0006\u0010\f\u001a\u00020\rJ\u0010\u0010\u000e\u001a\u00020\r2\b\u0010\u000f\u001a\u0004\u0018\u00010\u0010J\b\u0010\u0011\u001a\u00020\rH\u0016R\u0016\u0010\u0005\u001a\n \u0007*\u0004\u0018\u00010\u00060\u0006X\u0082\u000e¢\u0006\u0002\n\u0000R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\b\u0010\tR\u0010\u0010\n\u001a\u0004\u0018\u00010\u000bX\u0082\u000e¢\u0006\u0002\n\u0000¨\u0006\u0012"}, d2 = {"Lcom/neusoft/na/dltdroid/service/LogSourceTcpIp$Connection;", "Ljava/lang/Runnable;", "mSocket", "Ljava/nio/channels/SocketChannel;", "(Lcom/neusoft/na/dltdroid/service/LogSourceTcpIp;Ljava/nio/channels/SocketChannel;)V", "buffer", "Ljava/nio/ByteBuffer;", "kotlin.jvm.PlatformType", "getMSocket", "()Ljava/nio/channels/SocketChannel;", "selectionKey", "Ljava/nio/channels/SelectionKey;", "close", "", "register", "selector", "Ljava/nio/channels/Selector;", "run", "dltdroid-9.25.21.1a-G_NEU_release"}, k = 1, mv = {1, 7, 1}, xi = 48)
    private final class Connection implements Runnable {
        private ByteBuffer buffer;
        private final SocketChannel mSocket;
        private SelectionKey selectionKey;
        final /* synthetic */ LogSourceTcpIp this$0;

        public Connection(LogSourceTcpIp logSourceTcpIp, SocketChannel mSocket) {
            Intrinsics.checkNotNullParameter(mSocket, "mSocket");
            this.this$0 = logSourceTcpIp;
            this.mSocket = mSocket;
            this.buffer = ByteBuffer.allocateDirect(DltMessage.INSTANCE.getDLT_MAXLENGTH());
            logSourceTcpIp.addConnection(this);
        }

        public final SocketChannel getMSocket() {
            return this.mSocket;
        }

        @Override // java.lang.Runnable
        public void run() throws IOException {
            int i = this.mSocket.read(this.buffer);
            if (i > 0) {
                DltMessage.Companion companion = DltMessage.INSTANCE;
                ByteBuffer buffer = this.buffer;
                Intrinsics.checkNotNullExpressionValue(buffer, "buffer");
                DltMessage dltMessage = companion.read(buffer);
                while (dltMessage != null) {
                    this.this$0.mMessagesSubject.onNext(dltMessage);
                    DltMessage.Companion companion2 = DltMessage.INSTANCE;
                    ByteBuffer buffer2 = this.buffer;
                    Intrinsics.checkNotNullExpressionValue(buffer2, "buffer");
                    dltMessage = companion2.read(buffer2);
                }
            }
            if (i < 0) {
                close();
            }
        }

        public final void close() {
            try {
                this.mSocket.close();
                SelectionKey selectionKey = this.selectionKey;
                if (selectionKey != null) {
                    selectionKey.cancel();
                }
            } catch (IOException unused) {
            }
            this.this$0.removeConnection(this);
        }

        public final void register(Selector selector) {
            this.selectionKey = this.mSocket.register(selector, 1, this);
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
                new Connection(this, client).register(this.mSelector);
                Log.i(DltDaemon.INSTANCE.getLOGTAG(), "Connection Accepted: " + client.socket().getInetAddress());
            } catch (IOException unused) {
                Log.w(DltDaemon.INSTANCE.getLOGTAG(), "failed to open client connection");
            }
        }
    }

    @Override // com.neusoft.na.dltdroid.service.ILogSource
    public void register(Selector selector) {
        Intrinsics.checkNotNullParameter(selector, "selector");
        this.mSelector = selector;
    }

    @Override // com.neusoft.na.dltdroid.service.ILogSource
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

    @Override // com.neusoft.na.dltdroid.service.ILogSource
    public boolean stop() {
        Log.d(DltDaemon.INSTANCE.getLOGTAG(), "stopping logger");
        ServerSocketChannel serverSocketChannel = this.mServerSocketChannel;
        if (serverSocketChannel != null) {
            serverSocketChannel.close();
        }
        while (this.mConnectedClients.size() > 0) {
            ((Connection) CollectionsKt.first((List) this.mConnectedClients)).close();
        }
        return true;
    }

    @Override // com.neusoft.na.dltdroid.service.ILogSource
    public Observable<Integer> getClientCountObservable() {
        Observable<Integer> observableAsObservable = this.mClientCountSubject.asObservable();
        Intrinsics.checkNotNullExpressionValue(observableAsObservable, "mClientCountSubject.asObservable()");
        return observableAsObservable;
    }

    @Override // com.neusoft.na.dltdroid.service.ILogSource
    public Observable<DltMessage> getMessageObservable() {
        Observable<DltMessage> observableAsObservable = this.mMessagesSubject.asObservable();
        Intrinsics.checkNotNullExpressionValue(observableAsObservable, "mMessagesSubject.asObservable()");
        return observableAsObservable;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void addConnection(Connection conn) {
        this.mConnectedClients.add(conn);
        this.mClientCountSubject.onNext(Integer.valueOf(this.mConnectedClients.size()));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void removeConnection(Connection conn) {
        this.mConnectedClients.remove(conn);
        this.mClientCountSubject.onNext(Integer.valueOf(this.mConnectedClients.size()));
    }
}
