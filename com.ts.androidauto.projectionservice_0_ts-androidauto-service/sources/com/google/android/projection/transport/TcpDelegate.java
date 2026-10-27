package com.google.android.projection.transport;

import android.os.Parcelable;
import com.google.android.projection.logging.Log;
import com.google.android.projection.transport.TransportFactory;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketException;

/* JADX INFO: loaded from: classes.dex */
class TcpDelegate implements TransportFactory.TransportFactoryDelegate {
    private static final int RETRY_COUNT = 45;
    private static final int SOCKET_ACCEPT_TIMEOUT_MILLIS = 1000;
    private static final String TAG = "AHU.TCP_CONNECTION";
    private final TransportFactory.TcpConnectionSocketOptions mConnectionSocketOptions;
    private int mFactoryId;
    private ServerSocket mServerSocket;
    private final ServerSocketProvider mServerSocketProvider;
    private TcpServer mTcpServer;
    private boolean mUserDisconnect = false;

    TcpDelegate(ServerSocketProvider serverSocketProvider, int factoryId, TransportFactory.TcpConnectionSocketOptions options) {
        this.mFactoryId = -1;
        this.mTcpServer = null;
        this.mServerSocketProvider = serverSocketProvider;
        this.mConnectionSocketOptions = options;
        this.mFactoryId = factoryId;
        Log.d("AHU.TCP_CONNECTION", "TcpDelegate  mFactoryId:" + this.mFactoryId);
        this.mTcpServer = TcpServer.getInstance();
    }

    @Override // com.google.android.projection.transport.TransportFactory.TransportFactoryDelegate
    public void shutdown() {
    }

    @Override // com.google.android.projection.transport.TransportFactory.TransportFactoryDelegate
    public TransportFactory.FactoryHandle tryOpen(Parcelable device, final TransportFactory.Callback callback) throws IOException {
        if (device != null) {
            return null;
        }
        this.mUserDisconnect = false;
        final Thread thread = new Thread("TCP server") { // from class: com.google.android.projection.transport.TcpDelegate.1
            /* JADX WARN: Code restructure failed: missing block: B:11:0x0027, code lost:
            
                r7.this$0.applySocketOptions(r3);
                r4 = new com.google.android.projection.transport.net.TcpTransport(r3);
                r3.onConnected(r7.this$0.mFactoryId, r4);
             */
            @Override // java.lang.Thread, java.lang.Runnable
            /*
                Code decompiled incorrectly, please refer to instructions dump.
                To view partially-correct code enable 'Show inconsistent code' option in preferences
            */
            public void run() {
                /*
                    r7 = this;
                    java.lang.String r0 = "AHU.TCP_CONNECTION"
                    java.lang.String r1 = "Start try to get socket "
                    com.google.android.projection.logging.Log.d(r0, r1)
                    r1 = 0
                    r1 = 0
                L9:
                    r2 = 45
                    if (r1 >= r2) goto L65
                    boolean r3 = r7.isInterrupted()
                    if (r3 != 0) goto L65
                    com.google.android.projection.transport.TcpDelegate r3 = com.google.android.projection.transport.TcpDelegate.this     // Catch: java.io.IOException -> L46
                    com.google.android.projection.transport.TcpServer r3 = com.google.android.projection.transport.TcpDelegate.access$000(r3)     // Catch: java.io.IOException -> L46
                    if (r3 == 0) goto L3d
                    com.google.android.projection.transport.TcpDelegate r3 = com.google.android.projection.transport.TcpDelegate.this     // Catch: java.io.IOException -> L46
                    com.google.android.projection.transport.TcpServer r3 = com.google.android.projection.transport.TcpDelegate.access$000(r3)     // Catch: java.io.IOException -> L46
                    java.net.Socket r3 = r3.getSocketConnection()     // Catch: java.io.IOException -> L46
                    if (r3 == 0) goto L3d
                    com.google.android.projection.transport.TcpDelegate r4 = com.google.android.projection.transport.TcpDelegate.this     // Catch: java.io.IOException -> L46
                    com.google.android.projection.transport.TcpDelegate.access$100(r4, r3)     // Catch: java.io.IOException -> L46
                    com.google.android.projection.transport.net.TcpTransport r4 = new com.google.android.projection.transport.net.TcpTransport     // Catch: java.io.IOException -> L46
                    r4.<init>(r3)     // Catch: java.io.IOException -> L46
                    com.google.android.projection.transport.TransportFactory$Callback r5 = r3     // Catch: java.io.IOException -> L46
                    com.google.android.projection.transport.TcpDelegate r6 = com.google.android.projection.transport.TcpDelegate.this     // Catch: java.io.IOException -> L46
                    int r6 = com.google.android.projection.transport.TcpDelegate.access$200(r6)     // Catch: java.io.IOException -> L46
                    r5.onConnected(r6, r4)     // Catch: java.io.IOException -> L46
                    goto L65
                L3d:
                    r3 = 1000(0x3e8, double:4.94E-321)
                    android.os.SystemClock.sleep(r3)     // Catch: java.io.IOException -> L46
                    int r1 = r1 + 1
                    goto L9
                L46:
                    r3 = move-exception
                    boolean r4 = r7.isInterrupted()
                    if (r4 != 0) goto L65
                    java.lang.StringBuilder r4 = new java.lang.StringBuilder
                    r4.<init>()
                    java.lang.String r5 = "Can't get socket: "
                    r4.append(r5)
                    java.lang.String r5 = r3.toString()
                    r4.append(r5)
                    java.lang.String r4 = r4.toString()
                    com.google.android.projection.logging.Log.e(r0, r4)
                L65:
                    if (r1 == r2) goto L6d
                    boolean r2 = r7.isInterrupted()
                    if (r2 == 0) goto L7d
                L6d:
                    java.lang.String r2 = "Get socket timeout or interrupted, transport onDisconnected"
                    com.google.android.projection.logging.Log.d(r0, r2)
                    com.google.android.projection.transport.TransportFactory$Callback r0 = r3
                    com.google.android.projection.transport.TcpDelegate r2 = com.google.android.projection.transport.TcpDelegate.this
                    int r2 = com.google.android.projection.transport.TcpDelegate.access$200(r2)
                    r0.onDisconnected(r2)
                L7d:
                    return
                */
                throw new UnsupportedOperationException("Method not decompiled: com.google.android.projection.transport.TcpDelegate.AnonymousClass1.run():void");
            }
        };
        thread.start();
        return new TransportFactory.FactoryHandle() { // from class: com.google.android.projection.transport.TcpDelegate.2
            @Override // com.google.android.projection.transport.TransportFactory.FactoryHandle
            public void interrupt() throws InterruptedException {
                Log.d("AHU.TCP_CONNECTION", "interrupt thread: " + thread);
                thread.interrupt();
                thread.join();
            }
        };
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void applySocketOptions(Socket socket) throws SocketException {
        if (this.mConnectionSocketOptions.readTimeoutInMillis > 0) {
            Log.d("AHU.TCP_CONNECTION", "Set read timeout " + this.mConnectionSocketOptions.readTimeoutInMillis);
            socket.setSoTimeout(this.mConnectionSocketOptions.readTimeoutInMillis);
        }
        if (this.mConnectionSocketOptions.readSocketBufferSize > 0) {
            Log.d("AHU.TCP_CONNECTION", "Set receive buffer size " + this.mConnectionSocketOptions.readSocketBufferSize);
            socket.setReceiveBufferSize(this.mConnectionSocketOptions.readSocketBufferSize);
        }
        if (this.mConnectionSocketOptions.enableSendNoDelay) {
            Log.d("AHU.TCP_CONNECTION", "Enable tcp no delay");
            socket.setTcpNoDelay(this.mConnectionSocketOptions.enableSendNoDelay);
        }
    }
}
