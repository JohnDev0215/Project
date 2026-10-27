package com.android.proxyhandler;

import android.os.RemoteException;
import android.util.Log;
import com.android.net.IProxyPortListener;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketException;
import java.net.URI;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/* JADX INFO: loaded from: classes.dex */
public class ProxyServer extends Thread {
    private ServerSocket serverSocket;
    public boolean mIsRunning = false;
    private ExecutorService threadExecutor = Executors.newCachedThreadPool();
    private int mPort = -1;
    private IProxyPortListener mCallback = null;

    private class ProxyConnection implements Runnable {
        private Socket connection;

        private ProxyConnection(ProxyServer proxyServer, Socket socket) {
            this.connection = socket;
        }

        /* JADX WARN: Removed duplicated region for block: B:101:0x012a A[SYNTHETIC] */
        /* JADX WARN: Removed duplicated region for block: B:102:? A[LOOP:0: B:26:0x0097->B:102:?, LOOP_END, SYNTHETIC] */
        /* JADX WARN: Removed duplicated region for block: B:58:0x0114 A[Catch: Exception -> 0x0168, TryCatch #6 {Exception -> 0x0168, blocks: (B:3:0x0008, B:5:0x001c, B:7:0x0022, B:9:0x0032, B:13:0x0044, B:20:0x007b, B:21:0x007f, B:25:0x0091, B:26:0x0097, B:29:0x009f, B:30:0x00a6, B:32:0x00ae, B:33:0x00c1, B:62:0x012f, B:64:0x0135, B:66:0x0140, B:67:0x014d, B:70:0x015c, B:56:0x010e, B:58:0x0114, B:37:0x00ca, B:38:0x00cf, B:40:0x00d5, B:44:0x00e7, B:46:0x00f6, B:24:0x008e, B:12:0x003e, B:14:0x0060, B:16:0x0066, B:71:0x0162), top: B:90:0x0008, inners: #2, #4, #8 }] */
        /* JADX WARN: Removed duplicated region for block: B:98:0x012c A[SYNTHETIC] */
        @Override // java.lang.Runnable
        /*
            Code decompiled incorrectly, please refer to instructions dump.
            To view partially-correct code enable 'Show inconsistent code' option in preferences
        */
        public void run() {
            /*
                Method dump skipped, instruction units count: 372
                To view this dump change 'Code comments level' option to 'DEBUG'
            */
            throw new UnsupportedOperationException("Method not decompiled: com.android.proxyhandler.ProxyServer.ProxyConnection.run():void");
        }

        private void sendRequestLineWithPath(Socket socket, String str, URI uri, String str2) throws IOException {
            sendLine(socket, String.format("%s %s %s", str, getAbsolutePathFromAbsoluteURI(uri), str2));
        }

        private String getAbsolutePathFromAbsoluteURI(URI uri) {
            String rawPath = uri.getRawPath();
            String rawQuery = uri.getRawQuery();
            String rawFragment = uri.getRawFragment();
            StringBuilder sb = new StringBuilder();
            if (rawPath != null) {
                sb.append(rawPath);
            } else {
                sb.append("/");
            }
            if (rawQuery != null) {
                sb.append("?");
                sb.append(rawQuery);
            }
            if (rawFragment != null) {
                sb.append("#");
                sb.append(rawFragment);
            }
            return sb.toString();
        }

        private String getLine(InputStream inputStream) throws IOException {
            StringBuilder sb = new StringBuilder();
            int i = inputStream.read();
            if (i < 0) {
                return "";
            }
            do {
                if (i != 13) {
                    sb.append((char) i);
                }
                i = inputStream.read();
                if (i == 10) {
                    break;
                }
            } while (i >= 0);
            return sb.toString();
        }

        private void sendLine(Socket socket, String str) throws IOException {
            OutputStream outputStream = socket.getOutputStream();
            outputStream.write(str.getBytes());
            outputStream.write(13);
            outputStream.write(10);
            outputStream.flush();
        }

        private void skipToRequestBody(Socket socket) throws IOException {
            while (getLine(socket.getInputStream()).length() != 0) {
            }
        }

        private void sendAugmentedRequestToHost(Socket socket, Socket socket2, String str, URI uri, String str2) throws IOException {
            sendRequestLineWithPath(socket2, str, uri, str2);
            filterAndForwardRequestHeaders(socket, socket2);
            sendLine(socket2, "Connection: close");
            sendLine(socket2, "");
        }

        private void filterAndForwardRequestHeaders(Socket socket, Socket socket2) throws IOException {
            String line;
            do {
                line = getLine(socket.getInputStream());
                if (line.length() > 0 && !shouldRemoveHeaderLine(line)) {
                    sendLine(socket2, line);
                }
            } while (line.length() > 0);
        }

        private boolean shouldRemoveHeaderLine(String str) {
            int iIndexOf = str.indexOf(":");
            if (iIndexOf != -1) {
                String strTrim = str.substring(0, iIndexOf).trim();
                if (strTrim.regionMatches(true, 0, "connection", 0, 10) || strTrim.regionMatches(true, 0, "proxy-connection", 0, 16)) {
                    return true;
                }
            }
            return false;
        }
    }

    @Override // java.lang.Thread, java.lang.Runnable
    public void run() {
        try {
            ServerSocket serverSocket = new ServerSocket(0);
            this.serverSocket = serverSocket;
            setPort(serverSocket.getLocalPort());
            while (this.mIsRunning) {
                try {
                    Socket socketAccept = this.serverSocket.accept();
                    if (socketAccept.getInetAddress().isLoopbackAddress()) {
                        this.threadExecutor.execute(new ProxyConnection(socketAccept));
                    } else {
                        socketAccept.close();
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        } catch (SocketException e2) {
            Log.e("ProxyServer", "Failed to start proxy server", e2);
        } catch (IOException e3) {
            Log.e("ProxyServer", "Failed to start proxy server", e3);
        }
        this.mIsRunning = false;
    }

    public synchronized void setPort(int i) {
        if (this.mCallback != null) {
            try {
                this.mCallback.setProxyPort(i);
            } catch (RemoteException e) {
                Log.w("ProxyServer", "Proxy failed to report port to PacManager", e);
            }
            this.mPort = i;
        } else {
            this.mPort = i;
        }
    }

    public synchronized void setCallback(IProxyPortListener iProxyPortListener) {
        if (this.mPort != -1) {
            try {
                iProxyPortListener.setProxyPort(this.mPort);
            } catch (RemoteException e) {
                Log.w("ProxyServer", "Proxy failed to report port to PacManager", e);
            }
            this.mCallback = iProxyPortListener;
        } else {
            this.mCallback = iProxyPortListener;
        }
    }

    public synchronized void startServer() {
        this.mIsRunning = true;
        start();
    }

    public synchronized void stopServer() {
        this.mIsRunning = false;
        if (this.serverSocket != null) {
            try {
                this.serverSocket.close();
                this.serverSocket = null;
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
