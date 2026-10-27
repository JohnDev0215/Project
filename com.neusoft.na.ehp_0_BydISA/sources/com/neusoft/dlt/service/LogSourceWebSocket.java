package com.neusoft.dlt.service;

import android.util.Log;
import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.ClosedChannelException;
import java.nio.channels.Pipe;
import java.nio.channels.Selector;
import java.util.ArrayList;
import org.java_websocket.WebSocket;
import org.java_websocket.handshake.ClientHandshake;
import org.java_websocket.server.WebSocketServer;
import rx.Observable;
import rx.subjects.BehaviorSubject;
import rx.subjects.PublishSubject;

/* JADX INFO: loaded from: classes.dex */
public class LogSourceWebSocket implements ILogSource, Runnable {
    public static final String LOGTAG = "LogSourceWebSocket";
    private InetSocketAddress mAddress;
    private Pipe.SinkChannel mPipeSink;
    private Pipe.SourceChannel mPipeSource;
    private WebSocketServer mWebSocketServer;
    private BehaviorSubject<Integer> mClientCountSubject = BehaviorSubject.create(0);
    private ByteBuffer buffer = ByteBuffer.allocateDirect(DltMessage.DLT_MAXLENGTH);
    private ArrayList<WebSocket> mConnectedClients = new ArrayList<>();
    private PublishSubject<DltMessage> mMessagesSubject = PublishSubject.create();

    public LogSourceWebSocket(InetSocketAddress inetSocketAddress) {
        this.mAddress = inetSocketAddress;
        try {
            Pipe pipeOpen = Pipe.open();
            this.mPipeSource = pipeOpen.source();
            Pipe.SinkChannel sinkChannelSink = pipeOpen.sink();
            this.mPipeSink = sinkChannelSink;
            sinkChannelSink.configureBlocking(true);
            this.mPipeSource.configureBlocking(false);
            this.mWebSocketServer = new MyWebSocketServer(this.mAddress);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private class MyWebSocketServer extends WebSocketServer {
        public MyWebSocketServer(InetSocketAddress inetSocketAddress) {
            super(inetSocketAddress);
        }

        @Override // org.java_websocket.server.WebSocketServer
        public void onOpen(WebSocket webSocket, ClientHandshake clientHandshake) {
            Log.d(LogSourceWebSocket.LOGTAG, "onOpen");
            addConnection(webSocket);
        }

        @Override // org.java_websocket.server.WebSocketServer
        public void onClose(WebSocket webSocket, int i, String str, boolean z) {
            Log.d(LogSourceWebSocket.LOGTAG, "onClose");
            removeConnection(webSocket);
        }

        @Override // org.java_websocket.server.WebSocketServer
        public synchronized void onMessage(WebSocket webSocket, ByteBuffer byteBuffer) {
            int iWrite;
            try {
                iWrite = LogSourceWebSocket.this.mPipeSink.write(byteBuffer);
            } catch (IOException e) {
                e.printStackTrace();
                iWrite = 0;
            }
            Log.d(LogSourceWebSocket.LOGTAG, "res: " + iWrite);
        }

        @Override // org.java_websocket.server.WebSocketServer
        public void onMessage(WebSocket webSocket, String str) {
            Log.i(LogSourceWebSocket.LOGTAG, "got String message: " + str);
        }

        @Override // org.java_websocket.server.WebSocketServer
        public void onStart() {
            Log.d(LogSourceWebSocket.LOGTAG, "onStart");
        }

        @Override // org.java_websocket.server.WebSocketServer
        public void onError(WebSocket webSocket, Exception exc) {
            Log.w(LogSourceWebSocket.LOGTAG, "onError");
        }
    }

    @Override // java.lang.Runnable
    public void run() {
        try {
            if (this.mPipeSource.read(this.buffer) > 0) {
                DltMessage dltMessage = DltMessage.read(this.buffer);
                while (dltMessage != null) {
                    this.mMessagesSubject.onNext(dltMessage);
                    dltMessage = DltMessage.read(this.buffer);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override // com.neusoft.dlt.service.ILogSource
    public int getClientCount() {
        return this.mClientCountSubject.getValue().intValue();
    }

    @Override // com.neusoft.dlt.service.ILogSource
    public Observable<Integer> getClientCountObservable() {
        return this.mClientCountSubject.asObservable();
    }

    @Override // com.neusoft.dlt.service.ILogSource
    public Observable<DltMessage> getMessageObservable() {
        return this.mMessagesSubject.asObservable();
    }

    @Override // com.neusoft.dlt.service.ILogSource
    public void register(Selector selector) {
        try {
            this.mPipeSource.register(selector, 1, this);
        } catch (ClosedChannelException e) {
            e.printStackTrace();
        }
    }

    @Override // com.neusoft.dlt.service.ILogSource
    public boolean start() {
        this.mWebSocketServer.start();
        return true;
    }

    @Override // com.neusoft.dlt.service.ILogSource
    public boolean stop() {
        try {
            this.mWebSocketServer.stop();
            return true;
        } catch (InterruptedException e) {
            e.printStackTrace();
            return true;
        }
    }

    private synchronized void addConnection(WebSocket webSocket) {
        this.mConnectedClients.add(webSocket);
        this.mClientCountSubject.onNext(Integer.valueOf(this.mConnectedClients.size()));
    }

    private synchronized void removeConnection(WebSocket webSocket) {
        this.mConnectedClients.remove(webSocket);
        this.mClientCountSubject.onNext(Integer.valueOf(this.mConnectedClients.size()));
    }
}
