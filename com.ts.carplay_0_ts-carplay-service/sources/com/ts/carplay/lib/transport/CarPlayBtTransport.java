package com.ts.carplay.lib.transport;

import android.net.LocalServerSocket;
import android.net.LocalSocket;
import com.ts.carplay.common.util.LogEncrypt;
import com.ts.carplay.common.util.LogUtil;
import com.ts.carplay.lib.CarPlayManager;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Arrays;
import java.util.UUID;

/* JADX INFO: loaded from: classes.dex */
public class CarPlayBtTransport {
    private static final String BT_SOCKET_ADDRESS = "BT_SOCKET_ADDRESS";
    private static final int BT_TRANSPORT_STATE_CLOSED = 2;
    private static final int BT_TRANSPORT_STATE_IDLE = 0;
    private static final int BT_TRANSPORT_STATE_OPEN = 1;
    private static final int BUFFER_SIZE = 2048;
    private static final int NUMBER_0XFF = 255;
    private static final int NUMBER_BYTE_LEN = 8;
    private static final String TAG = "CarPlayBtTransport";
    private static final UUID UUID_IAP = UUID.fromString("00000000-deca-fade-deca-deafdecacafe");
    static CarPlayTransport sCarPlayTransport = null;
    private CarPlayManager mCarPlayManager;
    private RfcommReadThread mRfcommReadThread;
    private TransportReadThread mTransportReadThread;
    private volatile InputStream mTransportInput = null;
    private volatile OutputStream mTransportOutput = null;
    private LocalServerSocket mTransportSocketServer = null;
    private InputStream mRfcommInput = null;
    private OutputStream mRfcommOutput = null;
    private String mRfcommMac = null;
    private LocalSocket mTransportSocket = null;
    private boolean mTransportReadRuning = false;

    public CarPlayBtTransport(CarPlayTransport manager) {
    }

    public void setmRfcommInOutStream(InputStream input, OutputStream output, String rfcommMac) {
        this.mRfcommInput = input;
        this.mRfcommOutput = output;
        this.mRfcommMac = rfcommMac;
    }

    public int init(CarPlayManager manager) {
        LogUtil.debug(TAG, "0214 init");
        this.mCarPlayManager = manager;
        return 0;
    }

    public int open() {
        LogUtil.debug(TAG, "CarPlayBtTransport open");
        if (this.mTransportSocketServer == null) {
            try {
                this.mTransportSocketServer = new LocalServerSocket(BT_SOCKET_ADDRESS);
                LogUtil.debug(TAG, "btLocalServerSocketAddress open:BT_SOCKET_ADDRESS");
            } catch (IOException ex) {
                LogUtil.warning(TAG, "create socket failed");
                ex.printStackTrace();
                return -1;
            }
        }
        if (!this.mTransportReadRuning) {
            TransportReadThread transportReadThread = new TransportReadThread();
            this.mTransportReadThread = transportReadThread;
            transportReadThread.startThread();
            return 0;
        }
        return 0;
    }

    public synchronized int close() {
        LogUtil.debug(TAG, "close");
        if (this.mTransportReadThread != null && this.mTransportReadRuning) {
            this.mTransportReadThread.stopThread();
            this.mTransportReadThread = null;
            this.mTransportReadRuning = false;
        }
        if (this.mRfcommReadThread != null) {
            this.mRfcommReadThread.stopThread();
            this.mRfcommReadThread = null;
        }
        closeSocket();
        if (this.mCarPlayManager != null) {
            LogUtil.debug(TAG, "onRfCommDisconnected mRfcommMac: " + LogEncrypt.MacAddressEncrypt(this.mRfcommMac));
            if (this.mRfcommMac != null) {
                this.mCarPlayManager.onRfCommDisconnected(this.mRfcommMac);
                this.mRfcommMac = null;
            }
        }
        return 0;
    }

    public int deinit() {
        return 0;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public synchronized void closeSocket() {
        this.mTransportInput = null;
        this.mTransportOutput = null;
        try {
            if (this.mTransportSocket != null) {
                this.mTransportSocket.shutdownOutput();
                this.mTransportSocket.shutdownInput();
                this.mTransportSocket.close();
                this.mTransportSocket = null;
            }
        } catch (IOException io) {
            LogUtil.warning(TAG, "close mTransportSocket fail:" + io.getMessage());
        }
    }

    class TransportReadThread extends Thread {
        private boolean mIsRunning;

        public TransportReadThread() {
        }

        public void startThread() {
            this.mIsRunning = true;
            start();
        }

        public void stopThread() {
            this.mIsRunning = false;
        }

        @Override // java.lang.Thread, java.lang.Runnable
        public void run() {
            LogUtil.debug(CarPlayBtTransport.TAG, "BtServerThread run and btServer: " + CarPlayBtTransport.this.mTransportSocketServer);
            try {
            } catch (IOException ex) {
                ex.printStackTrace();
            }
            if (CarPlayBtTransport.this.mTransportSocketServer != null) {
                CarPlayBtTransport.this.mTransportReadRuning = true;
                CarPlayBtTransport.this.mRfcommReadThread = new RfcommReadThread();
                CarPlayBtTransport.this.mRfcommReadThread.startThread();
                CarPlayBtTransport.this.mTransportSocket = CarPlayBtTransport.this.mTransportSocketServer.accept();
                LogUtil.debug(CarPlayBtTransport.TAG, "Java: socket connect success!");
                CarPlayBtTransport.this.mTransportOutput = CarPlayBtTransport.this.mTransportSocket.getOutputStream();
                CarPlayBtTransport.this.mTransportInput = CarPlayBtTransport.this.mTransportSocket.getInputStream();
                this.mIsRunning = true;
                byte[] bufferRead = new byte[2048];
                while (this.mIsRunning) {
                    try {
                        LogUtil.info(CarPlayBtTransport.TAG, "TransportReadThread reading");
                        if (CarPlayBtTransport.this.mTransportInput != null) {
                            int len = CarPlayBtTransport.this.mTransportInput.read(bufferRead);
                            if (len > 0) {
                                int index = 0;
                                byte[] buffer = new byte[len];
                                System.arraycopy(bufferRead, 0, buffer, 0, len);
                                LogUtil.info(CarPlayBtTransport.TAG, "localsocket read size:" + len + " data : " + Arrays.toString(buffer));
                                int length = 0;
                                for (int i = 0; i < 2; i++) {
                                    length += (buffer[i] & 255) << (i * 8);
                                }
                                LogUtil.info(CarPlayBtTransport.TAG, "head length:" + length + ",and index :0");
                                while (len != index) {
                                    int index2 = index + 2;
                                    LogUtil.info(CarPlayBtTransport.TAG, "index :" + index2);
                                    byte[] write_to_phone = new byte[length];
                                    System.arraycopy(buffer, index2, write_to_phone, 0, length);
                                    if (CarPlayBtTransport.this.mRfcommOutput != null) {
                                        LogUtil.info(CarPlayBtTransport.TAG, "socket is connected and ready to write!");
                                        CarPlayBtTransport.this.mRfcommOutput.write(write_to_phone);
                                    }
                                    LogUtil.info(CarPlayBtTransport.TAG, "localsocket write to iphone data : " + Arrays.toString(write_to_phone));
                                    index = index2 + length;
                                    if (index != len) {
                                        length = 0;
                                        for (int i2 = index; i2 < index + 2; i2++) {
                                            length += (buffer[i2] & 255) << ((i2 - index) * 8);
                                        }
                                    }
                                    LogUtil.info(CarPlayBtTransport.TAG, "next length:" + length);
                                }
                            } else {
                                LogUtil.info(CarPlayBtTransport.TAG, "waiting");
                                stopThread();
                                CarPlayBtTransport.this.closeSocket();
                                CarPlayBtTransport.this.mTransportReadRuning = false;
                                break;
                            }
                        } else {
                            LogUtil.warning(CarPlayBtTransport.TAG, "mTransportInput is null.");
                        }
                    } catch (IOException ex2) {
                        ex2.printStackTrace();
                        stopThread();
                        CarPlayBtTransport.this.close();
                    }
                }
                LogUtil.debug(CarPlayBtTransport.TAG, "TransportReadThread exit");
                return;
            }
            LogUtil.warning(CarPlayBtTransport.TAG, "TransportSocketServer create error! exit");
        }
    }

    private class RfcommReadThread extends Thread {
        private boolean mIsRunning;

        private RfcommReadThread() {
        }

        public void startThread() {
            this.mIsRunning = true;
            start();
        }

        public void stopThread() {
            this.mIsRunning = false;
        }

        /* JADX WARN: Code restructure failed: missing block: B:18:0x0086, code lost:
        
            stopThread();
            com.ts.carplay.common.util.LogUtil.info(com.ts.carplay.lib.transport.CarPlayBtTransport.TAG, "mRfcommInput is null");
            r6.this$0.close();
         */
        @Override // java.lang.Thread, java.lang.Runnable
        /*
            Code decompiled incorrectly, please refer to instructions dump.
            To view partially-correct code enable 'Show inconsistent code' option in preferences
        */
        public void run() {
            /*
                r6 = this;
                java.lang.String r0 = "CarPlayBtTransport"
                java.lang.String r1 = "RfcommReadThread ConnectThread run!!!!!!!!!"
                com.ts.carplay.common.util.LogUtil.debug(r0, r1)
                com.ts.carplay.lib.transport.CarPlayBtTransport r1 = com.ts.carplay.lib.transport.CarPlayBtTransport.this     // Catch: java.io.IOException -> L9a java.lang.NullPointerException -> Lac
                java.io.InputStream r1 = com.ts.carplay.lib.transport.CarPlayBtTransport.access$900(r1)     // Catch: java.io.IOException -> L9a java.lang.NullPointerException -> Lac
                if (r1 == 0) goto L93
                r1 = 2048(0x800, float:2.87E-42)
                byte[] r1 = new byte[r1]     // Catch: java.io.IOException -> L9a java.lang.NullPointerException -> Lac
                r2 = 0
            L14:
                boolean r3 = r6.mIsRunning     // Catch: java.io.IOException -> L9a java.lang.NullPointerException -> Lac
                if (r3 == 0) goto L93
                java.lang.String r3 = "bluetooth socket read start"
                com.ts.carplay.common.util.LogUtil.info(r0, r3)     // Catch: java.io.IOException -> L9a java.lang.NullPointerException -> Lac
                com.ts.carplay.lib.transport.CarPlayBtTransport r3 = com.ts.carplay.lib.transport.CarPlayBtTransport.this     // Catch: java.io.IOException -> L9a java.lang.NullPointerException -> Lac
                java.io.InputStream r3 = com.ts.carplay.lib.transport.CarPlayBtTransport.access$900(r3)     // Catch: java.io.IOException -> L9a java.lang.NullPointerException -> Lac
                if (r3 == 0) goto L86
                com.ts.carplay.lib.transport.CarPlayBtTransport r3 = com.ts.carplay.lib.transport.CarPlayBtTransport.this     // Catch: java.io.IOException -> L9a java.lang.NullPointerException -> Lac
                java.io.InputStream r3 = com.ts.carplay.lib.transport.CarPlayBtTransport.access$900(r3)     // Catch: java.io.IOException -> L9a java.lang.NullPointerException -> Lac
                int r3 = r3.read(r1)     // Catch: java.io.IOException -> L9a java.lang.NullPointerException -> Lac
                r2 = r3
                if (r2 <= 0) goto L80
                byte[] r3 = new byte[r2]     // Catch: java.io.IOException -> L9a java.lang.NullPointerException -> Lac
                r4 = 0
                java.lang.System.arraycopy(r1, r4, r3, r4, r2)     // Catch: java.io.IOException -> L9a java.lang.NullPointerException -> Lac
                java.lang.StringBuilder r4 = new java.lang.StringBuilder     // Catch: java.io.IOException -> L9a java.lang.NullPointerException -> Lac
                r4.<init>()     // Catch: java.io.IOException -> L9a java.lang.NullPointerException -> Lac
                java.lang.String r5 = "btsocket read string byte bluetoothsocket : "
                r4.append(r5)     // Catch: java.io.IOException -> L9a java.lang.NullPointerException -> Lac
                java.lang.String r5 = java.util.Arrays.toString(r3)     // Catch: java.io.IOException -> L9a java.lang.NullPointerException -> Lac
                r4.append(r5)     // Catch: java.io.IOException -> L9a java.lang.NullPointerException -> Lac
                java.lang.String r4 = r4.toString()     // Catch: java.io.IOException -> L9a java.lang.NullPointerException -> Lac
                com.ts.carplay.common.util.LogUtil.info(r0, r4)     // Catch: java.io.IOException -> L9a java.lang.NullPointerException -> Lac
                com.ts.carplay.lib.transport.CarPlayBtTransport r4 = com.ts.carplay.lib.transport.CarPlayBtTransport.this     // Catch: java.io.IOException -> L9a java.lang.NullPointerException -> Lac
                java.io.OutputStream r4 = com.ts.carplay.lib.transport.CarPlayBtTransport.access$500(r4)     // Catch: java.io.IOException -> L9a java.lang.NullPointerException -> Lac
                if (r4 == 0) goto L7a
                com.ts.carplay.lib.transport.CarPlayBtTransport r4 = com.ts.carplay.lib.transport.CarPlayBtTransport.this     // Catch: java.io.IOException -> L9a java.lang.NullPointerException -> Lac
                java.io.OutputStream r4 = com.ts.carplay.lib.transport.CarPlayBtTransport.access$500(r4)     // Catch: java.io.IOException -> L9a java.lang.NullPointerException -> Lac
                r4.write(r3)     // Catch: java.io.IOException -> L9a java.lang.NullPointerException -> Lac
                java.lang.StringBuilder r4 = new java.lang.StringBuilder     // Catch: java.io.IOException -> L9a java.lang.NullPointerException -> Lac
                r4.<init>()     // Catch: java.io.IOException -> L9a java.lang.NullPointerException -> Lac
                java.lang.String r5 = "localsocket write: "
                r4.append(r5)     // Catch: java.io.IOException -> L9a java.lang.NullPointerException -> Lac
                java.lang.String r5 = java.util.Arrays.toString(r3)     // Catch: java.io.IOException -> L9a java.lang.NullPointerException -> Lac
                r4.append(r5)     // Catch: java.io.IOException -> L9a java.lang.NullPointerException -> Lac
                java.lang.String r4 = r4.toString()     // Catch: java.io.IOException -> L9a java.lang.NullPointerException -> Lac
                com.ts.carplay.common.util.LogUtil.info(r0, r4)     // Catch: java.io.IOException -> L9a java.lang.NullPointerException -> Lac
                goto L7f
            L7a:
                java.lang.String r4 = "mTransportOutput is null."
                com.ts.carplay.common.util.LogUtil.warning(r0, r4)     // Catch: java.io.IOException -> L9a java.lang.NullPointerException -> Lac
            L7f:
                goto L14
            L80:
                java.lang.String r3 = "rfcomm socket disconnect"
                com.ts.carplay.common.util.LogUtil.info(r0, r3)     // Catch: java.io.IOException -> L9a java.lang.NullPointerException -> Lac
                goto L14
            L86:
                r6.stopThread()     // Catch: java.io.IOException -> L9a java.lang.NullPointerException -> Lac
                java.lang.String r3 = "mRfcommInput is null"
                com.ts.carplay.common.util.LogUtil.info(r0, r3)     // Catch: java.io.IOException -> L9a java.lang.NullPointerException -> Lac
                com.ts.carplay.lib.transport.CarPlayBtTransport r3 = com.ts.carplay.lib.transport.CarPlayBtTransport.this     // Catch: java.io.IOException -> L9a java.lang.NullPointerException -> Lac
                r3.close()     // Catch: java.io.IOException -> L9a java.lang.NullPointerException -> Lac
            L93:
                java.lang.String r1 = "RfcommReadThread exit"
                com.ts.carplay.common.util.LogUtil.debug(r0, r1)
                return
            L9a:
                r1 = move-exception
                r1.printStackTrace()
                java.lang.String r2 = "ConnectThread connect error occur"
                com.ts.carplay.common.util.LogUtil.warning(r0, r2)
                r6.stopThread()
                com.ts.carplay.lib.transport.CarPlayBtTransport r0 = com.ts.carplay.lib.transport.CarPlayBtTransport.this
                r0.close()
                return
            Lac:
                r1 = move-exception
                java.lang.String r2 = "ConnectThread connect error occur : nullExcep"
                com.ts.carplay.common.util.LogUtil.warning(r0, r2)
                r6.stopThread()
                com.ts.carplay.lib.transport.CarPlayBtTransport r0 = com.ts.carplay.lib.transport.CarPlayBtTransport.this
                r0.close()
                return
            */
            throw new UnsupportedOperationException("Method not decompiled: com.ts.carplay.lib.transport.CarPlayBtTransport.RfcommReadThread.run():void");
        }
    }
}
