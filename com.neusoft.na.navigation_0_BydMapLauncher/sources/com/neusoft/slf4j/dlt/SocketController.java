package com.neusoft.slf4j.dlt;

import android.support.v4.media.session.PlaybackStateCompat;
import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.net.SocketTimeoutException;
import java.nio.channels.IllegalBlockingModeException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.atomic.AtomicInteger;
import rx.BackpressureOverflow;
import rx.Observable;
import rx.Subscription;
import rx.functions.Action0;
import rx.functions.Action1;
import rx.functions.Func1;
import rx.schedulers.Schedulers;
import rx.subjects.BehaviorSubject;
import rx.subjects.PublishSubject;

/* JADX INFO: loaded from: classes.dex */
class SocketController {
    private static final int DEFAULT_CONNECTION_TIMEOUT_MILLIS = 5000;
    private static final AtomicInteger threadCount = new AtomicInteger(0);
    private final String host;
    private final int port;
    private final PublishSubject<String> errorMessage = PublishSubject.create();
    private final PublishSubject<byte[]> sendData = PublishSubject.create();
    private boolean running = false;
    private final BehaviorSubject<ConnectionStatus> connectionStatus = BehaviorSubject.create(ConnectionStatus.Disconnected);
    private final ExecutorService executor = Executors.newSingleThreadExecutor(new ThreadFactory() { // from class: com.neusoft.slf4j.dlt.SocketController.1
        @Override // java.util.concurrent.ThreadFactory
        public Thread newThread(Runnable runnable) {
            Thread threadNewThread = Executors.defaultThreadFactory().newThread(runnable);
            threadNewThread.setName("SocketController-" + SocketController.threadCount.getAndIncrement());
            threadNewThread.setDaemon(true);
            return threadNewThread;
        }
    });

    public enum ConnectionStatus {
        Connecting,
        Connected,
        Disconnected
    }

    SocketController(String str, int i) {
        this.host = str;
        this.port = i;
    }

    Observable<ConnectionStatus> getConnectionStatus() {
        return this.connectionStatus;
    }

    Observable<String> getErrorMessage() {
        return this.errorMessage;
    }

    synchronized void start() {
        if (this.running) {
            throw new IllegalStateException("start() not allowed - SocketController already running");
        }
        this.running = true;
        this.executor.submit(new Runnable() { // from class: com.neusoft.slf4j.dlt.SocketController.2
            @Override // java.lang.Runnable
            public void run() {
                SocketController.this.connectLoop();
            }
        });
    }

    synchronized void stop() {
        if (!this.running) {
            throw new IllegalStateException("stop() not allowed - SocketController not running");
        }
        this.running = false;
        this.connectionStatus.onNext(ConnectionStatus.Disconnected);
    }

    void send(byte[] bArr) {
        this.sendData.onNext(bArr);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void connectLoop() {
        InetSocketAddress inetSocketAddress = new InetSocketAddress(this.host, this.port);
        while (this.running) {
            long jCurrentTimeMillis = System.currentTimeMillis();
            this.connectionStatus.onNext(ConnectionStatus.Connecting);
            try {
                final Socket socket = new Socket();
                socket.setSoTimeout(5000);
                socket.setKeepAlive(true);
                socket.connect(inetSocketAddress, 5000);
                this.errorMessage.onNext("Info: SocketController socket open");
                this.connectionStatus.onNext(ConnectionStatus.Connected);
                final Subscription subscriptionSubscribe = this.sendData.observeOn(Schedulers.io()).onBackpressureBuffer(PlaybackStateCompat.ACTION_SKIP_TO_QUEUE_ITEM, new Action0() { // from class: com.neusoft.slf4j.dlt.SocketController.6
                    @Override // rx.functions.Action0
                    public void call() {
                        SocketController.this.errorMessage.onNext("DLT buffer is full, dropping oldest message");
                    }
                }, BackpressureOverflow.ON_OVERFLOW_DROP_OLDEST).subscribe(new Action1<byte[]>() { // from class: com.neusoft.slf4j.dlt.SocketController.3
                    @Override // rx.functions.Action1
                    public void call(byte[] bArr) {
                        try {
                            socket.getOutputStream().write(bArr);
                        } catch (IOException e) {
                            throw new RuntimeException(e);
                        }
                    }
                }, new Action1<Throwable>() { // from class: com.neusoft.slf4j.dlt.SocketController.4
                    @Override // rx.functions.Action1
                    public void call(Throwable th) {
                        SocketController.this.errorMessage.onNext(th.getMessage());
                        SocketController.this.connectionStatus.onNext(ConnectionStatus.Disconnected);
                    }
                }, new Action0() { // from class: com.neusoft.slf4j.dlt.SocketController.5
                    @Override // rx.functions.Action0
                    public void call() {
                    }
                });
                this.connectionStatus.observeOn(Schedulers.io()).filter(new Func1<ConnectionStatus, Boolean>() { // from class: com.neusoft.slf4j.dlt.SocketController.9
                    @Override // rx.functions.Func1
                    public Boolean call(ConnectionStatus connectionStatus) {
                        return Boolean.valueOf(connectionStatus == ConnectionStatus.Disconnected);
                    }
                }).take(1).toBlocking().subscribe(new Action1<ConnectionStatus>() { // from class: com.neusoft.slf4j.dlt.SocketController.7
                    @Override // rx.functions.Action1
                    public void call(ConnectionStatus connectionStatus) {
                        subscriptionSubscribe.unsubscribe();
                        try {
                            socket.close();
                        } catch (IOException unused) {
                        }
                    }
                }, new Action1<Throwable>() { // from class: com.neusoft.slf4j.dlt.SocketController.8
                    @Override // rx.functions.Action1
                    public void call(Throwable th) {
                        SocketController.this.errorMessage.onNext("Error in connectionStatus blocking subscribe:  " + th);
                    }
                });
            } catch (Exception e) {
                this.errorMessage.onNext("Error when connecting DLTSocket: " + e.getMessage());
                if ((e instanceof IllegalArgumentException) || (e instanceof IllegalBlockingModeException)) {
                    this.running = false;
                } else if (!(e instanceof SocketTimeoutException) && !(e instanceof IOException)) {
                    this.running = false;
                }
                long jCurrentTimeMillis2 = (System.currentTimeMillis() - jCurrentTimeMillis) - 5000;
                if (jCurrentTimeMillis2 < 0) {
                    try {
                        Thread.sleep(-jCurrentTimeMillis2);
                    } catch (InterruptedException unused) {
                        this.errorMessage.onNext(e.getMessage());
                        this.running = false;
                    }
                }
            }
        }
    }
}
