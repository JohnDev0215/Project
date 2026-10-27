package com.google.android.projection.transport;

import android.os.SystemClock;
import android.util.Log;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketTimeoutException;
import java.util.HashSet;
import java.util.Set;

/* JADX INFO: loaded from: classes.dex */
public class TcpServer {
    private static final int PORT = 9998;
    private static final int RETRY_WAIT_TIME = 100;
    private static final String TAG = "AAPTcpServer";
    private ServerSocket mServerSocket;
    private TcpServerThread mTcpServerThread = null;
    private Set<Socket> mConnectSocketSet = new HashSet();
    private boolean mRunTcpServer = false;

    private static class TcpServerInner {
        private static TcpServer tcpServer = new TcpServer();

        private TcpServerInner() {
        }
    }

    public void startTcpServerThread() {
        Log.v(TAG, "startTcpServerThread is invoke!");
        if (this.mTcpServerThread != null) {
            stopTcpServerThread();
        }
        this.mRunTcpServer = true;
        TcpServerThread tcpServerThread = new TcpServerThread();
        this.mTcpServerThread = tcpServerThread;
        tcpServerThread.start();
    }

    public void stopTcpServerThread() {
        Log.v(TAG, "stopTcpServerThread is invoke!");
        this.mRunTcpServer = false;
        if (this.mTcpServerThread != null) {
            TcpServerThread.interrupted();
            this.mTcpServerThread.stopThread();
            this.mTcpServerThread = null;
        }
    }

    private class TcpServerThread extends Thread {
        private TcpServerThread() {
        }

        public void stopThread() {
            TcpServer.this.closeServerSocket();
        }

        private void createServerSocket() {
            try {
                if (TcpServer.this.mServerSocket == null) {
                    TcpServer.this.mServerSocket = new ServerSocket(9998);
                    TcpServer.this.mServerSocket.setReuseAddress(true);
                }
            } catch (IOException exception) {
                Log.e(TcpServer.TAG, "Failed to start tcp " + exception.toString());
            }
        }

        public boolean checkServerSocket() {
            return TcpServer.this.mServerSocket != null;
        }

        @Override // java.lang.Thread, java.lang.Runnable
        public void run() {
            Log.v(TcpServer.TAG, "TcpServerThread start");
            createServerSocket();
            while (TcpServer.this.mRunTcpServer && !isInterrupted()) {
                if (TcpServer.this.mServerSocket != null) {
                    try {
                        if (TcpServer.this.mServerSocket != null) {
                            Log.d(TcpServer.TAG, "Socket start accept");
                            Socket socket = TcpServer.this.mServerSocket.accept();
                            TcpServer.this.receiveNewClient(socket);
                        } else {
                            Log.v(TcpServer.TAG, "mServerSocket is null need recreate again");
                        }
                    } catch (SocketTimeoutException e) {
                        if (!isInterrupted()) {
                            Log.d(TcpServer.TAG, "Socket accept timed out; retrying");
                        }
                    } catch (IOException ex) {
                        if (!isInterrupted()) {
                            Log.e(TcpServer.TAG, "Can't listen to socket: " + ex);
                        }
                    }
                } else {
                    Log.v(TcpServer.TAG, "mServerSocket is null retry");
                    SystemClock.sleep(100L);
                    createServerSocket();
                }
            }
        }
    }

    public static TcpServer getInstance() {
        return TcpServerInner.tcpServer;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public synchronized void closeServerSocket() {
        if (this.mServerSocket != null) {
            try {
                Log.d(TAG, "Tcp close server socket");
                this.mServerSocket.close();
            } catch (IOException ex) {
                Log.d(TAG, "Can't close server socket: " + ex);
            }
            this.mServerSocket = null;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void receiveNewClient(Socket socket) {
        Log.d(TAG, "receiveNewClient socket:" + socket);
        Set<Socket> set = this.mConnectSocketSet;
        if (set != null) {
            set.add(socket);
        }
    }

    public Socket getSocketConnection() {
        Socket socket = null;
        for (Socket socket2 : this.mConnectSocketSet) {
            socket = socket2;
        }
        if (socket != null) {
            this.mConnectSocketSet.remove(socket);
        }
        Log.d(TAG, "getSocketConnection socket:" + socket);
        return socket;
    }

    public void clearSocketConnection() {
        Log.d(TAG, "clearSocketConnection is invoke!");
        Set<Socket> set = this.mConnectSocketSet;
        if (set != null) {
            for (Socket socket : set) {
                if (socket != null) {
                    try {
                        Log.d(TAG, "close socket:" + socket);
                        socket.close();
                    } catch (IOException ioEx) {
                        Log.d(TAG, "clear close ioEx:" + ioEx);
                    }
                }
            }
            this.mConnectSocketSet.clear();
        }
    }
}
