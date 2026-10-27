package com.android.bluetooth;

import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.bluetooth.BluetoothServerSocket;
import android.bluetooth.BluetoothSocket;
import android.util.Log;
import java.io.IOException;
import javax.obex.Authenticator;
import javax.obex.ServerSession;

/* JADX INFO: loaded from: classes.dex */
public class ObexServerSockets {
    private static final int CREATE_RETRY_TIME = 10;
    private static final boolean D = true;
    private static final int NUMBER_OF_SOCKET_TYPES = 2;
    private static final String STAG = "ObexServerSockets";
    private static volatile int sInstanceCounter;
    private final IObexConnectionHandler mConHandler;
    private final BluetoothServerSocket mL2capSocket;
    private SocketAcceptThread mL2capThread;
    private final BluetoothServerSocket mRfcommSocket;
    private SocketAcceptThread mRfcommThread;
    private final String mTag;

    private ObexServerSockets(IObexConnectionHandler conHandler, BluetoothServerSocket rfcommSocket, BluetoothServerSocket l2capSocket) {
        this.mConHandler = conHandler;
        this.mRfcommSocket = rfcommSocket;
        this.mL2capSocket = l2capSocket;
        StringBuilder sb = new StringBuilder();
        sb.append(STAG);
        int i = sInstanceCounter;
        sInstanceCounter = i + 1;
        sb.append(i);
        this.mTag = sb.toString();
    }

    public static ObexServerSockets create(IObexConnectionHandler validator) {
        return create(validator, -2, -2, true);
    }

    public static ObexServerSockets createInsecure(IObexConnectionHandler validator) {
        return create(validator, -2, -2, false);
    }

    /* JADX WARN: Removed duplicated region for block: B:15:0x0046  */
    /* JADX WARN: Removed duplicated region for block: B:22:0x005e  */
    /* JADX WARN: Removed duplicated region for block: B:44:0x0084 A[EDGE_INSN: B:44:0x0084->B:32:0x0084 BREAK  A[LOOP:0: B:5:0x002d->B:31:0x0081], SYNTHETIC] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    private static com.android.bluetooth.ObexServerSockets create(com.android.bluetooth.IObexConnectionHandler r9, int r10, int r11, boolean r12) {
        /*
            java.lang.StringBuilder r0 = new java.lang.StringBuilder
            r0.<init>()
            java.lang.String r1 = "create(rfcomm = "
            r0.append(r1)
            r0.append(r10)
            java.lang.String r1 = ", l2capPsm = "
            r0.append(r1)
            r0.append(r11)
            java.lang.String r1 = ")"
            r0.append(r1)
            java.lang.String r0 = r0.toString()
            java.lang.String r1 = "ObexServerSockets"
            android.util.Log.d(r1, r0)
            android.bluetooth.BluetoothAdapter r0 = android.bluetooth.BluetoothAdapter.getDefaultAdapter()
            if (r0 == 0) goto L9b
            r2 = 0
            r3 = 0
            r4 = 0
            r5 = 0
        L2d:
            r6 = 10
            if (r5 >= r6) goto L84
            r4 = 1
            if (r2 != 0) goto L44
            if (r12 == 0) goto L3c
            android.bluetooth.BluetoothServerSocket r6 = r0.listenUsingRfcommOn(r10)     // Catch: java.io.IOException -> L42
            r2 = r6
            goto L44
        L3c:
            android.bluetooth.BluetoothServerSocket r6 = r0.listenUsingInsecureRfcommOn(r10)     // Catch: java.io.IOException -> L42
            r2 = r6
            goto L44
        L42:
            r6 = move-exception
            goto L54
        L44:
            if (r3 != 0) goto L5b
            if (r12 == 0) goto L4e
            android.bluetooth.BluetoothServerSocket r6 = r0.listenUsingL2capOn(r11)     // Catch: java.io.IOException -> L42
            r3 = r6
            goto L5b
        L4e:
            android.bluetooth.BluetoothServerSocket r6 = r0.listenUsingInsecureL2capOn(r11)     // Catch: java.io.IOException -> L42
            r3 = r6
            goto L5b
        L54:
            java.lang.String r7 = "Error create ServerSockets "
            android.util.Log.e(r1, r7, r6)
            r4 = 0
            goto L5c
        L5b:
        L5c:
            if (r4 != 0) goto L84
            int r6 = r0.getState()
            r7 = 11
            if (r6 == r7) goto L70
            r7 = 12
            if (r6 == r7) goto L70
            java.lang.String r7 = "initServerSockets failed as BT is (being) turned off"
            android.util.Log.w(r1, r7)
            goto L84
        L70:
            java.lang.String r7 = "waiting 300 ms..."
            android.util.Log.v(r1, r7)     // Catch: java.lang.InterruptedException -> L7b
            r7 = 300(0x12c, double:1.48E-321)
            java.lang.Thread.sleep(r7)     // Catch: java.lang.InterruptedException -> L7b
            goto L81
        L7b:
            r7 = move-exception
            java.lang.String r8 = "create() was interrupted"
            android.util.Log.e(r1, r8)
        L81:
            int r5 = r5 + 1
            goto L2d
        L84:
            if (r4 == 0) goto L94
            java.lang.String r5 = "Succeed to create listening sockets "
            android.util.Log.d(r1, r5)
            com.android.bluetooth.ObexServerSockets r1 = new com.android.bluetooth.ObexServerSockets
            r1.<init>(r9, r2, r3)
            r1.startAccept()
            return r1
        L94:
            java.lang.String r5 = "Error to create listening socket after 10 try"
            android.util.Log.e(r1, r5)
            r1 = 0
            return r1
        L9b:
            java.lang.RuntimeException r1 = new java.lang.RuntimeException
            java.lang.String r2 = "No bluetooth adapter..."
            r1.<init>(r2)
            throw r1
        */
        throw new UnsupportedOperationException("Method not decompiled: com.android.bluetooth.ObexServerSockets.create(com.android.bluetooth.IObexConnectionHandler, int, int, boolean):com.android.bluetooth.ObexServerSockets");
    }

    public int getRfcommChannel() {
        return this.mRfcommSocket.getChannel();
    }

    public int getL2capPsm() {
        return this.mL2capSocket.getChannel();
    }

    private void startAccept() {
        Log.d(this.mTag, "startAccept()");
        SocketAcceptThread socketAcceptThread = new SocketAcceptThread(this.mRfcommSocket);
        this.mRfcommThread = socketAcceptThread;
        socketAcceptThread.start();
        SocketAcceptThread socketAcceptThread2 = new SocketAcceptThread(this.mL2capSocket);
        this.mL2capThread = socketAcceptThread2;
        socketAcceptThread2.start();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public synchronized boolean onConnect(BluetoothDevice device, BluetoothSocket conSocket) {
        Log.d(this.mTag, "onConnect() socket: " + conSocket);
        return this.mConHandler.onConnect(device, conSocket);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public synchronized void onAcceptFailed() {
        shutdown(false);
        BluetoothAdapter mAdapter = BluetoothAdapter.getDefaultAdapter();
        if (mAdapter != null && mAdapter.getState() == 12) {
            Log.d(this.mTag, "onAcceptFailed() calling shutdown...");
            this.mConHandler.onAcceptFailed();
        }
    }

    public synchronized void shutdown(boolean block) {
        Log.d(this.mTag, "shutdown(block = " + block + ")");
        if (this.mRfcommThread != null) {
            this.mRfcommThread.shutdown();
        }
        if (this.mL2capThread != null) {
            this.mL2capThread.shutdown();
        }
        if (block) {
            while (true) {
                if (this.mRfcommThread == null && this.mL2capThread == null) {
                    break;
                }
                try {
                    if (this.mRfcommThread != null) {
                        this.mRfcommThread.join();
                        this.mRfcommThread = null;
                    }
                    if (this.mL2capThread != null) {
                        this.mL2capThread.join();
                        this.mL2capThread = null;
                    }
                } catch (InterruptedException e) {
                    Log.i(this.mTag, "shutdown() interrupted, continue waiting...", e);
                }
            }
        } else {
            this.mRfcommThread = null;
            this.mL2capThread = null;
        }
    }

    private class SocketAcceptThread extends Thread {
        private final BluetoothServerSocket mServerSocket;
        private boolean mStopped = false;

        SocketAcceptThread(BluetoothServerSocket serverSocket) {
            if (serverSocket == null) {
                throw new IllegalArgumentException("serverSocket cannot be null");
            }
            this.mServerSocket = serverSocket;
        }

        @Override // java.lang.Thread, java.lang.Runnable
        public void run() {
            while (!this.mStopped) {
                try {
                    try {
                        Log.d(ObexServerSockets.this.mTag, "Accepting socket connection...");
                        BluetoothSocket connSocket = this.mServerSocket.accept();
                        Log.d(ObexServerSockets.this.mTag, "Accepted socket connection from: " + this.mServerSocket);
                        if (connSocket == null) {
                            Log.w(ObexServerSockets.this.mTag, "connSocket is null - reattempt accept");
                        } else {
                            BluetoothDevice device = connSocket.getRemoteDevice();
                            if (device == null) {
                                Log.i(ObexServerSockets.this.mTag, "getRemoteDevice() = null - reattempt accept");
                                try {
                                    connSocket.close();
                                } catch (IOException e) {
                                    Log.w(ObexServerSockets.this.mTag, "Error closing the socket. ignoring...", e);
                                }
                            } else {
                                boolean isValid = ObexServerSockets.this.onConnect(device, connSocket);
                                if (!isValid) {
                                    Log.i(ObexServerSockets.this.mTag, "RemoteDevice is invalid - creating ObexRejectServer.");
                                    BluetoothObexTransport obexTrans = new BluetoothObexTransport(connSocket);
                                    new ServerSession(obexTrans, new ObexRejectServer(211, connSocket), (Authenticator) null);
                                }
                            }
                        }
                    } catch (IOException ex) {
                        if (!this.mStopped) {
                            Log.w(ObexServerSockets.this.mTag, "Accept exception for " + this.mServerSocket, ex);
                            ObexServerSockets.this.onAcceptFailed();
                        }
                        this.mStopped = true;
                    }
                } finally {
                    Log.d(ObexServerSockets.this.mTag, "AcceptThread ended for: " + this.mServerSocket);
                }
            }
        }

        public void shutdown() {
            if (!this.mStopped) {
                this.mStopped = true;
                try {
                    this.mServerSocket.close();
                } catch (IOException e) {
                    Log.d(ObexServerSockets.this.mTag, "Exception while thread shutdown:", e);
                }
            }
            if (!Thread.currentThread().equals(this)) {
                Log.d(ObexServerSockets.this.mTag, "shutdown called from another thread - interrupt().");
                interrupt();
            }
        }
    }
}
