package com.neusoft.na.dltdroid.service;

import android.util.Log;
import com.neusoft.na.dltdroid.service.DltMessage;
import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.Pipe;
import java.nio.channels.Selector;
import java.util.ArrayList;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import org.java_websocket.WebSocket;
import org.java_websocket.handshake.ClientHandshake;
import org.java_websocket.server.WebSocketServer;
import rx.Observable;
import rx.subjects.BehaviorSubject;
import rx.subjects.PublishSubject;

/* JADX INFO: compiled from: LogSourceWebSocket.kt */
/* JADX INFO: loaded from: classes2.dex */
@Metadata(d1 = {"\u0000|\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0002\b\u0003\u0018\u0000 32\u00020\u00012\u00020\u0002:\u00013B\r\u0012\u0006\u0010\u0003\u001a\u00020\u0004¢\u0006\u0002\u0010\u0005J\u0010\u0010$\u001a\u00020%2\u0006\u0010&\u001a\u00020\u0010H\u0002J\b\u0010'\u001a\u00020\rH\u0016J\u000e\u0010(\u001a\b\u0012\u0004\u0012\u00020\r0)H\u0016J\u000e\u0010*\u001a\b\u0012\u0004\u0012\u00020\u00130)H\u0016J\u0010\u0010+\u001a\u00020%2\u0006\u0010,\u001a\u00020-H\u0016J\u0010\u0010.\u001a\u00020%2\u0006\u0010&\u001a\u00020\u0010H\u0002J\b\u0010/\u001a\u00020%H\u0016J\b\u00100\u001a\u000201H\u0016J\b\u00102\u001a\u000201H\u0016R\u0016\u0010\u0006\u001a\n \b*\u0004\u0018\u00010\u00070\u0007X\u0082\u0004¢\u0006\u0002\n\u0000R\u0011\u0010\u0003\u001a\u00020\u0004¢\u0006\b\n\u0000\u001a\u0004\b\t\u0010\nR2\u0010\u000b\u001a&\u0012\f\u0012\n \b*\u0004\u0018\u00010\r0\r \b*\u0012\u0012\f\u0012\n \b*\u0004\u0018\u00010\r0\r\u0018\u00010\f0\fX\u0082\u0004¢\u0006\u0002\n\u0000R\u0014\u0010\u000e\u001a\b\u0012\u0004\u0012\u00020\u00100\u000fX\u0082\u0004¢\u0006\u0002\n\u0000R2\u0010\u0011\u001a&\u0012\f\u0012\n \b*\u0004\u0018\u00010\u00130\u0013 \b*\u0012\u0012\f\u0012\n \b*\u0004\u0018\u00010\u00130\u0013\u0018\u00010\u00120\u0012X\u0082\u0004¢\u0006\u0002\n\u0000R\u0019\u0010\u0014\u001a\n \b*\u0004\u0018\u00010\u00150\u0015¢\u0006\b\n\u0000\u001a\u0004\b\u0016\u0010\u0017R\u0019\u0010\u0018\u001a\n \b*\u0004\u0018\u00010\u00190\u0019¢\u0006\b\n\u0000\u001a\u0004\b\u001a\u0010\u001bR\u0019\u0010\u001c\u001a\n \b*\u0004\u0018\u00010\u001d0\u001d¢\u0006\b\n\u0000\u001a\u0004\b\u001e\u0010\u001fR\u0011\u0010 \u001a\u00020!¢\u0006\b\n\u0000\u001a\u0004\b\"\u0010#¨\u00064"}, d2 = {"Lcom/neusoft/na/dltdroid/service/LogSourceWebSocket;", "Lcom/neusoft/na/dltdroid/service/ILogSource;", "Ljava/lang/Runnable;", "mAddress", "Ljava/net/InetSocketAddress;", "(Ljava/net/InetSocketAddress;)V", "buffer", "Ljava/nio/ByteBuffer;", "kotlin.jvm.PlatformType", "getMAddress", "()Ljava/net/InetSocketAddress;", "mClientCountSubject", "Lrx/subjects/BehaviorSubject;", "", "mConnectedClients", "Ljava/util/ArrayList;", "Lorg/java_websocket/WebSocket;", "mMessagesSubject", "Lrx/subjects/PublishSubject;", "Lcom/neusoft/na/dltdroid/service/DltMessage;", "mPipe", "Ljava/nio/channels/Pipe;", "getMPipe", "()Ljava/nio/channels/Pipe;", "mPipeSink", "Ljava/nio/channels/Pipe$SinkChannel;", "getMPipeSink", "()Ljava/nio/channels/Pipe$SinkChannel;", "mPipeSource", "Ljava/nio/channels/Pipe$SourceChannel;", "getMPipeSource", "()Ljava/nio/channels/Pipe$SourceChannel;", "mWebSocketServer", "Lorg/java_websocket/server/WebSocketServer;", "getMWebSocketServer", "()Lorg/java_websocket/server/WebSocketServer;", "addConnection", "", "conn", "getClientCount", "getClientCountObservable", "Lrx/Observable;", "getMessageObservable", "register", "selector", "Ljava/nio/channels/Selector;", "removeConnection", "run", "start", "", "stop", "Companion", "dltdroid-9.25.21.1a-G_NEU_release"}, k = 1, mv = {1, 7, 1}, xi = 48)
public final class LogSourceWebSocket implements ILogSource, Runnable {

    /* JADX INFO: renamed from: Companion, reason: from kotlin metadata */
    public static final Companion INSTANCE = new Companion(null);
    private static final String LOGTAG = "LogSourceWebSocket";
    private final ByteBuffer buffer;
    private final InetSocketAddress mAddress;
    private final BehaviorSubject<Integer> mClientCountSubject;
    private final ArrayList<WebSocket> mConnectedClients;
    private final PublishSubject<DltMessage> mMessagesSubject;
    private final Pipe mPipe;
    private final Pipe.SinkChannel mPipeSink;
    private final Pipe.SourceChannel mPipeSource;
    private final WebSocketServer mWebSocketServer;

    public LogSourceWebSocket(final InetSocketAddress mAddress) throws IOException {
        Intrinsics.checkNotNullParameter(mAddress, "mAddress");
        this.mAddress = mAddress;
        Pipe pipeOpen = Pipe.open();
        this.mPipe = pipeOpen;
        Pipe.SourceChannel sourceChannelSource = pipeOpen.source();
        this.mPipeSource = sourceChannelSource;
        Pipe.SinkChannel sinkChannelSink = pipeOpen.sink();
        this.mPipeSink = sinkChannelSink;
        this.mWebSocketServer = new WebSocketServer(mAddress) { // from class: com.neusoft.na.dltdroid.service.LogSourceWebSocket$mWebSocketServer$1
            {
                setReuseAddr(true);
            }

            @Override // org.java_websocket.server.WebSocketServer
            public void onOpen(WebSocket conn, ClientHandshake handshake) {
                Log.i(LogSourceWebSocket.INSTANCE.getLOGTAG(), "onOpen");
                if (conn != null) {
                    this.this$0.addConnection(conn);
                }
            }

            @Override // org.java_websocket.server.WebSocketServer
            public void onClose(WebSocket conn, int code, String reason, boolean remote) {
                Log.i(LogSourceWebSocket.INSTANCE.getLOGTAG(), "onClose");
                if (conn != null) {
                    this.this$0.removeConnection(conn);
                }
            }

            @Override // org.java_websocket.server.WebSocketServer
            public synchronized void onMessage(WebSocket conn, ByteBuffer message) {
                if (message != null) {
                    this.this$0.getMPipeSink().write(message);
                }
            }

            @Override // org.java_websocket.server.WebSocketServer
            public void onMessage(WebSocket conn, String message) {
                Log.i(LogSourceWebSocket.INSTANCE.getLOGTAG(), "got String message: " + message);
            }

            @Override // org.java_websocket.server.WebSocketServer
            public void onStart() {
                Log.i(LogSourceWebSocket.INSTANCE.getLOGTAG(), "onStart");
            }

            @Override // org.java_websocket.server.WebSocketServer
            public void onError(WebSocket conn, Exception ex) {
                Log.w(LogSourceWebSocket.INSTANCE.getLOGTAG(), "onError");
            }
        };
        this.mClientCountSubject = BehaviorSubject.create(0);
        this.buffer = ByteBuffer.allocateDirect(DltMessage.INSTANCE.getDLT_MAXLENGTH());
        sinkChannelSink.configureBlocking(true);
        sourceChannelSource.configureBlocking(false);
        this.mConnectedClients = new ArrayList<>();
        this.mMessagesSubject = PublishSubject.create();
    }

    public final InetSocketAddress getMAddress() {
        return this.mAddress;
    }

    public final Pipe getMPipe() {
        return this.mPipe;
    }

    public final Pipe.SourceChannel getMPipeSource() {
        return this.mPipeSource;
    }

    public final Pipe.SinkChannel getMPipeSink() {
        return this.mPipeSink;
    }

    public final WebSocketServer getMWebSocketServer() {
        return this.mWebSocketServer;
    }

    @Override // java.lang.Runnable
    public void run() {
        if (this.mPipeSource.read(this.buffer) > 0) {
            DltMessage.Companion companion = DltMessage.INSTANCE;
            ByteBuffer buffer = this.buffer;
            Intrinsics.checkNotNullExpressionValue(buffer, "buffer");
            DltMessage dltMessage = companion.read(buffer);
            while (dltMessage != null) {
                this.mMessagesSubject.onNext(dltMessage);
                DltMessage.Companion companion2 = DltMessage.INSTANCE;
                ByteBuffer buffer2 = this.buffer;
                Intrinsics.checkNotNullExpressionValue(buffer2, "buffer");
                dltMessage = companion2.read(buffer2);
            }
        }
    }

    @Override // com.neusoft.na.dltdroid.service.ILogSource
    public int getClientCount() {
        Integer value = this.mClientCountSubject.getValue();
        Intrinsics.checkNotNullExpressionValue(value, "mClientCountSubject.value");
        return value.intValue();
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

    @Override // com.neusoft.na.dltdroid.service.ILogSource
    public void register(Selector selector) {
        Intrinsics.checkNotNullParameter(selector, "selector");
        this.mPipeSource.register(selector, 1, this);
    }

    @Override // com.neusoft.na.dltdroid.service.ILogSource
    public boolean start() {
        this.mWebSocketServer.start();
        return true;
    }

    @Override // com.neusoft.na.dltdroid.service.ILogSource
    public boolean stop() throws InterruptedException, IOException {
        this.mWebSocketServer.stop();
        return true;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final synchronized void addConnection(WebSocket conn) {
        this.mConnectedClients.add(conn);
        this.mClientCountSubject.onNext(Integer.valueOf(this.mConnectedClients.size()));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final synchronized void removeConnection(WebSocket conn) {
        this.mConnectedClients.remove(conn);
        this.mClientCountSubject.onNext(Integer.valueOf(this.mConnectedClients.size()));
    }

    /* JADX INFO: compiled from: LogSourceWebSocket.kt */
    @Metadata(d1 = {"\u0000\u0014\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0003\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002R\u0014\u0010\u0003\u001a\u00020\u0004X\u0086D¢\u0006\b\n\u0000\u001a\u0004\b\u0005\u0010\u0006¨\u0006\u0007"}, d2 = {"Lcom/neusoft/na/dltdroid/service/LogSourceWebSocket$Companion;", "", "()V", "LOGTAG", "", "getLOGTAG", "()Ljava/lang/String;", "dltdroid-9.25.21.1a-G_NEU_release"}, k = 1, mv = {1, 7, 1}, xi = 48)
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }

        public final String getLOGTAG() {
            return LogSourceWebSocket.LOGTAG;
        }
    }
}
