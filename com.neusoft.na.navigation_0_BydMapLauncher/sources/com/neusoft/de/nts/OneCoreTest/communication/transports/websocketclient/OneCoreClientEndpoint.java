package com.neusoft.de.nts.OneCoreTest.communication.transports.websocketclient;

import com.neusoft.de.nts.OneCoreTest.interfaces.ITransport;
import com.neusoft.de.nts.OneCoreTest.utils.Logger;
import java.io.Closeable;
import java.util.concurrent.TimeUnit;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.WebSocket;
import okhttp3.WebSocketListener;
import rx.Observable;
import rx.subjects.BehaviorSubject;
import rx.subjects.PublishSubject;

/* JADX INFO: loaded from: classes2.dex */
public class OneCoreClientEndpoint extends WebSocketListener implements ITransport<String, String>, Closeable {
    private static final int NORMAL_CLOSURE_STATUS = 1000;
    private static final Logger logger = Logger.getLogger((Class<?>) OneCoreClientEndpoint.class);
    private final long connectTimeoutMillis;
    private final BehaviorSubject<ConnectionState> connectionSubject;
    private final PublishSubject<String> oneCoreSubject;
    private final long pingIntervalMillis;
    private final boolean retryOnConnectionFailure;
    private WebSocket webSocket;

    public enum ConnectionState {
        DISCONNECTED,
        CONNECTING,
        CONNECTED,
        DISCONNECTING,
        FAILURE
    }

    @Override // com.neusoft.de.nts.OneCoreTest.interfaces.ITransport
    public String receive() {
        return null;
    }

    public static final class Builder {
        private boolean retryOnConnectionFailure = true;
        private long connectTimeoutMillis = 10000;
        private long pingIntervalMillis = 0;

        public Builder retryOnConnectionFailure(boolean z) {
            this.retryOnConnectionFailure = z;
            return this;
        }

        public Builder connectTimeout(long j) {
            this.connectTimeoutMillis = j;
            return this;
        }

        public Builder pingInterval(long j) {
            this.pingIntervalMillis = j;
            return this;
        }

        public OneCoreClientEndpoint build() {
            return new OneCoreClientEndpoint(this);
        }
    }

    private OneCoreClientEndpoint(Builder builder) throws OneCoreConnectionException {
        this.oneCoreSubject = PublishSubject.create();
        this.connectionSubject = BehaviorSubject.create(ConnectionState.DISCONNECTED);
        this.retryOnConnectionFailure = builder.retryOnConnectionFailure;
        this.connectTimeoutMillis = builder.connectTimeoutMillis;
        this.pingIntervalMillis = builder.pingIntervalMillis;
    }

    public void connectSync(String str) {
        Request requestBuild = new Request.Builder().url(str).build();
        OkHttpClient okHttpClientBuild = new OkHttpClient.Builder().retryOnConnectionFailure(this.retryOnConnectionFailure).connectTimeout(this.connectTimeoutMillis, TimeUnit.MILLISECONDS).pingInterval(this.pingIntervalMillis, TimeUnit.MILLISECONDS).build();
        this.connectionSubject.onNext(ConnectionState.CONNECTING);
        this.webSocket = okHttpClientBuild.newWebSocket(requestBuild, this);
        this.connectionSubject.onNext(ConnectionState.CONNECTED);
    }

    public void connectAsync(final String str) throws OneCoreConnectionException {
        new Thread(new Runnable() { // from class: com.neusoft.de.nts.OneCoreTest.communication.transports.websocketclient.OneCoreClientEndpoint.1
            @Override // java.lang.Runnable
            public void run() {
                Request requestBuild = new Request.Builder().url(str).build();
                OkHttpClient okHttpClientBuild = new OkHttpClient.Builder().retryOnConnectionFailure(OneCoreClientEndpoint.this.retryOnConnectionFailure).connectTimeout(OneCoreClientEndpoint.this.connectTimeoutMillis, TimeUnit.MILLISECONDS).pingInterval(OneCoreClientEndpoint.this.pingIntervalMillis, TimeUnit.MILLISECONDS).build();
                OneCoreClientEndpoint.this.connectionSubject.onNext(ConnectionState.CONNECTING);
                OneCoreClientEndpoint oneCoreClientEndpoint = OneCoreClientEndpoint.this;
                oneCoreClientEndpoint.webSocket = okHttpClientBuild.newWebSocket(requestBuild, oneCoreClientEndpoint);
            }
        }).start();
    }

    @Override // com.neusoft.de.nts.OneCoreTest.interfaces.ITransport
    public Observable<String> getObservable() {
        return this.oneCoreSubject.onBackpressureBuffer();
    }

    public Observable<ConnectionState> getConnectionObservable() {
        return this.connectionSubject;
    }

    @Override // com.neusoft.de.nts.OneCoreTest.interfaces.ITransport
    public void send(String str) {
        if (this.connectionSubject.getValue() == ConnectionState.CONNECTED) {
            Logger logger2 = logger;
            logger2.debug("Send message: " + str);
            if (this.webSocket.send(str)) {
                return;
            }
            logger2.error(new RuntimeException("Message could not be sent!"), "Message could not be sent!", new Object[0]);
            return;
        }
        logger.error(new RuntimeException("Trying to send message, but no WebSocket connection is open!"), "Trying to send message, but no WebSocket connection is open!", new Object[0]);
    }

    @Override // java.io.Closeable, java.lang.AutoCloseable
    public void close() {
        if (this.webSocket == null || this.connectionSubject.getValue() != ConnectionState.CONNECTED) {
            logger.error(new RuntimeException("Trying to close, but no WebSocket connection is open!"), "Trying to close, but no WebSocket connection is open!", new Object[0]);
        } else if (this.webSocket.close(1000, "going away")) {
            logger.info("Closing WebSocket connection to " + this.webSocket.getOriginalRequest().url());
        } else {
            String str = "Trying to close already closed WebSocket to " + this.webSocket.getOriginalRequest().url();
            logger.error(new RuntimeException(str), str, new Object[0]);
        }
    }

    @Override // okhttp3.WebSocketListener
    public void onOpen(WebSocket webSocket, Response response) {
        logger.info("WebSocket connection opened: %s response: %s", this.webSocket.getOriginalRequest().url(), response);
        this.connectionSubject.onNext(ConnectionState.CONNECTED);
    }

    @Override // okhttp3.WebSocketListener
    public void onMessage(WebSocket webSocket, String str) {
        logger.debug("Received message: " + str.replace("\n", ""));
        this.oneCoreSubject.onNext(str);
    }

    @Override // okhttp3.WebSocketListener
    public void onClosing(WebSocket webSocket, int i, String str) {
        logger.info("WebSocket connection closing: %s reason: %s", this.webSocket.getOriginalRequest().url(), str);
        this.connectionSubject.onNext(ConnectionState.DISCONNECTING);
        webSocket.close(1000, null);
    }

    @Override // okhttp3.WebSocketListener
    public void onClosed(WebSocket webSocket, int i, String str) {
        logger.info("WebSocket connection closed: %s reason: %s", this.webSocket.getOriginalRequest().url(), str);
        this.connectionSubject.onNext(ConnectionState.DISCONNECTED);
        this.webSocket = null;
    }

    @Override // okhttp3.WebSocketListener
    public void onFailure(WebSocket webSocket, Throwable th, Response response) {
        logger.error(th, "WebSocket Session error. Session: %s", webSocket);
        this.connectionSubject.onNext(ConnectionState.FAILURE);
        this.webSocket = null;
    }
}
