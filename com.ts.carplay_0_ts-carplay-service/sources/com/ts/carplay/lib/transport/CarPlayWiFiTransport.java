package com.ts.carplay.lib.transport;

import android.net.LocalServerSocket;
import android.net.LocalSocket;
import com.ts.android.carplay.CarPlayClient;
import com.ts.carplay.common.util.LogUtil;
import com.ts.carplay.lib.ts.CarPlayStack;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

/* JADX INFO: loaded from: classes.dex */
public class CarPlayWiFiTransport {
    private static final int BUFFER_SIZE = 3072;
    private static final int NUMBER_0XFF = 255;
    private static final int NUMBER_BYTE_LEN = 8;
    private static final String TAG = CarPlayWiFiTransport.class.getSimpleName();
    private static final String WIFI_SOCKET_ADDRESS = "WIFI_SOCKET_ADDRESS";
    static final int WIFI_TRANSPORT_STATE_CLOSED = 2;
    static final int WIFI_TRANSPORT_STATE_IDLE = 0;
    static final int WIFI_TRANSPORT_STATE_OPEN = 1;
    private TransportThread mTransportThread;
    private InputStream mTransportInput = null;
    private OutputStream mTransportOutput = null;
    private LocalServerSocket mTransportSocketServer = null;
    private int mWiFiTransportState = 0;
    private CarPlayClient mtsCarPlayClient = null;
    private LocalSocket mTransportSocket = null;
    private boolean mIsWifiTheadRuning = false;

    public CarPlayWiFiTransport(CarPlayTransport manager) {
    }

    public int init() {
        LogUtil.debug(TAG, "init");
        try {
            if (this.mTransportSocketServer == null) {
                this.mTransportSocketServer = new LocalServerSocket(WIFI_SOCKET_ADDRESS);
                LogUtil.debug(TAG, "wifiLocalServerSocket Address:WIFI_SOCKET_ADDRESSand wait for native until success");
                return 0;
            }
            return 0;
        } catch (IOException ex) {
            LogUtil.warning(TAG, "create socket failed");
            ex.printStackTrace();
            return -1;
        }
    }

    public int open() {
        this.mTransportOutput = null;
        this.mtsCarPlayClient = CarPlayStack.getInstance().getCarPlayClient();
        if (!this.mIsWifiTheadRuning) {
            TransportThread transportThread = new TransportThread();
            this.mTransportThread = transportThread;
            transportThread.startThread();
            return 0;
        }
        return 0;
    }

    public synchronized int close() {
        LogUtil.debug(TAG, "close");
        if (this.mTransportThread != null && this.mIsWifiTheadRuning) {
            this.mTransportThread.stopThread();
            this.mTransportThread = null;
            this.mIsWifiTheadRuning = false;
        }
        try {
            if (this.mTransportSocket != null) {
                this.mTransportSocket.shutdownOutput();
                this.mTransportSocket.shutdownInput();
                this.mTransportSocket.close();
                this.mTransportSocket = null;
            }
        } catch (IOException io) {
            LogUtil.warning(TAG, "close socket fail:" + io.getMessage());
        }
        return 0;
    }

    public int deinit() {
        try {
            if (this.mTransportSocketServer != null) {
                this.mTransportSocketServer.close();
                this.mTransportSocketServer = null;
                return 0;
            }
            return 0;
        } catch (IOException io) {
            LogUtil.warning(TAG, "close socket fail:" + io.getMessage());
            return 0;
        }
    }

    public int sendMsgToNative(byte[] msg) {
        OutputStream outputStream = this.mTransportOutput;
        if (outputStream != null) {
            try {
                outputStream.write(msg);
                return 0;
            } catch (IOException ex) {
                ex.printStackTrace();
                return 0;
            }
        }
        return -1;
    }

    class TransportThread extends Thread {
        private boolean mIsRunning;

        public TransportThread() {
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
            LogUtil.debug(CarPlayWiFiTransport.TAG, "WiFiServerThread run and btServer: " + CarPlayWiFiTransport.this.mTransportSocketServer.toString());
            try {
                CarPlayWiFiTransport.this.mTransportSocket = CarPlayWiFiTransport.this.mTransportSocketServer.accept();
                LogUtil.debug(CarPlayWiFiTransport.TAG, "Java: socket connect success!");
                CarPlayWiFiTransport.this.mTransportOutput = CarPlayWiFiTransport.this.mTransportSocket.getOutputStream();
                CarPlayWiFiTransport.this.mTransportInput = CarPlayWiFiTransport.this.mTransportSocket.getInputStream();
                CarPlayWiFiTransport.this.mIsWifiTheadRuning = true;
                this.mIsRunning = true;
            } catch (IOException ex) {
                ex.printStackTrace();
            }
            byte[] bufferRead = new byte[CarPlayWiFiTransport.BUFFER_SIZE];
            while (this.mIsRunning) {
                try {
                    int len = CarPlayWiFiTransport.this.mTransportInput.read(bufferRead);
                    if (len < 0) {
                        LogUtil.info(CarPlayWiFiTransport.TAG, "waiting");
                        return;
                    }
                    int index = 0;
                    byte[] buffer = new byte[len];
                    System.arraycopy(bufferRead, 0, buffer, 0, len);
                    int length = 0;
                    for (int i = 0; i < 2; i++) {
                        length += (buffer[i] & 255) << (i * 8);
                    }
                    while (len != index) {
                        int index2 = index + 2;
                        if (length > len - index2) {
                            break;
                        }
                        byte[] write_to_ts = new byte[length];
                        System.arraycopy(buffer, index2, write_to_ts, 0, length);
                        if (CarPlayWiFiTransport.this.mtsCarPlayClient != null) {
                            CarPlayWiFiTransport.this.mtsCarPlayClient.sendIap2Message(write_to_ts, length);
                        } else {
                            LogUtil.info(CarPlayWiFiTransport.TAG, "CarPlayClient session is not connected!");
                        }
                        index = index2 + length;
                        if (index != len) {
                            length = 0;
                            for (int i2 = index; i2 < index + 2; i2++) {
                                length += (buffer[i2] & 255) << ((i2 - index) * 8);
                            }
                        }
                    }
                } catch (IOException ex2) {
                    LogUtil.warning(CarPlayWiFiTransport.TAG, "wifi disconnect");
                    ex2.printStackTrace();
                    this.mIsRunning = false;
                    CarPlayWiFiTransport.this.close();
                }
            }
        }
    }
}
