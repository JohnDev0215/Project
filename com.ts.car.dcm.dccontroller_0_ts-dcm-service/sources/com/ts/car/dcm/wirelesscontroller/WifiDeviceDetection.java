package com.ts.car.dcm.wirelesscontroller;

import android.os.Handler;
import android.os.HandlerThread;
import android.os.Looper;
import android.os.Message;
import android.util.Log;
import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetSocketAddress;
import java.net.SocketAddress;
import java.util.HashMap;

/* JADX INFO: loaded from: classes.dex */
public class WifiDeviceDetection extends WirelessDeviceDetection {
    private static final String TAG = WifiDeviceDetection.class.getSimpleName();
    private static WifiDeviceDetection mWifiDeviceDetection;
    private Thread mCarlifeDetectThread;
    private DetectHandler mDetectHandler;
    private HandlerThread mHandlerThread;
    private WifiDeviceMonitor mWifiDeviceMonitor;
    private HashMap<String, WifiConnection> mDetectWifiConnections = new HashMap<>();
    private boolean mIsThreadWhile = true;

    private WifiDeviceDetection(WifiDeviceMonitor monitor) {
        Log.d(TAG, "WifiDeviceDetection, create.");
        this.mWifiDeviceMonitor = monitor;
    }

    private class DetectHandler extends Handler {
        public DetectHandler(Looper looper) {
            super(looper);
        }

        private WifiConnection getWifiConnectionByIpaddr(String ipAddress) {
            for (WifiConnection connection : WifiDeviceDetection.this.mDetectWifiConnections.values()) {
                String ipAddr = connection.getIpAddress();
                if (ipAddr != null && ipAddr.equals(ipAddress)) {
                    return connection;
                }
            }
            return null;
        }

        private void processCarlifeDeviceFound(String ipAddr, int capability) {
            WifiConnection wifiConnection = getWifiConnectionByIpaddr(ipAddr);
            if (wifiConnection == null) {
                Message msg = WifiDeviceDetection.this.mWifiDeviceMonitor.obtainMessage(805);
                msg.obj = ipAddr;
                msg.arg1 = capability;
                msg.sendToTarget();
                return;
            }
            if ((wifiConnection.mLinkDevice.getCapability() & capability) == 0) {
                wifiConnection.sendMessage(12, capability);
            }
        }

        @Override // android.os.Handler
        public void handleMessage(Message msg) {
            Log.d(WifiDeviceDetection.TAG, "DetectHandler msg.what:" + msg.what);
            if (msg.what == 804) {
                String ipAddr = (String) msg.obj;
                int capability = msg.arg1;
                processCarlifeDeviceFound(ipAddr, capability);
            }
            super.handleMessage(msg);
        }
    }

    public static synchronized WifiDeviceDetection getInstance(WifiDeviceMonitor monitor) {
        if (mWifiDeviceDetection == null) {
            mWifiDeviceDetection = new WifiDeviceDetection(monitor);
        }
        return mWifiDeviceDetection;
    }

    @Override // com.ts.car.dcm.wirelesscontroller.WirelessDeviceDetection
    public void detectWirelessDevice(String mac, WirelessConnection conn) {
        synchronized (this.mDetectWifiConnections) {
            this.mDetectWifiConnections.put(mac, (WifiConnection) conn);
        }
    }

    @Override // com.ts.car.dcm.wirelesscontroller.WirelessDeviceDetection
    public void removeDetectDevice(String mac) {
        synchronized (this.mDetectWifiConnections) {
            this.mDetectWifiConnections.remove(mac);
        }
    }

    @Override // com.ts.car.dcm.wirelesscontroller.WirelessDeviceDetection
    public void startDetectDevice() {
        if (this.mHandlerThread == null) {
            HandlerThread handlerThread = new HandlerThread(TAG);
            this.mHandlerThread = handlerThread;
            handlerThread.start();
        }
        this.mDetectHandler = new DetectHandler(this.mHandlerThread.getLooper());
        this.mIsThreadWhile = true;
        Thread thread = new Thread() { // from class: com.ts.car.dcm.wirelesscontroller.WifiDeviceDetection.1
            @Override // java.lang.Thread, java.lang.Runnable
            public void run() {
                Log.d(WifiDeviceDetection.TAG, "startDetectDevice, CarlifeDetectThread start run.");
                DatagramSocket socket = null;
                try {
                    socket = new DatagramSocket((SocketAddress) null);
                    socket.setReuseAddress(true);
                    socket.bind(new InetSocketAddress(WirelessMonitorConstants.CARLIFE_BROADCAST_PORT));
                    socket.setBroadcast(true);
                    byte[] rev = new byte[512];
                    DatagramPacket packet = new DatagramPacket(rev, rev.length);
                    while (WifiDeviceDetection.this.mIsThreadWhile) {
                        try {
                            socket.receive(packet);
                            String receiver = new String(packet.getData()).trim();
                            if (receiver.equals(WirelessMonitorConstants.CARLIFE_BROADCAST_FLAG)) {
                                Message msg = WifiDeviceDetection.this.mDetectHandler.obtainMessage(804);
                                msg.obj = packet.getAddress().getHostAddress();
                                msg.arg1 = 2048;
                                msg.sendToTarget();
                            }
                            packet.setLength(512);
                        } catch (Exception ex) {
                            Log.e(WifiDeviceDetection.TAG, "startDetectDevice, CarlifeDetectThread exception stop.");
                            ex.printStackTrace();
                        }
                    }
                    socket.close();
                } catch (IOException ex2) {
                    ex2.printStackTrace();
                    Log.e(WifiDeviceDetection.TAG, "startDetectDevice, CarlifeDetectThread socket create failed.");
                    if (socket != null) {
                        socket.close();
                    }
                }
            }
        };
        this.mCarlifeDetectThread = thread;
        thread.start();
    }

    @Override // com.ts.car.dcm.wirelesscontroller.WirelessDeviceDetection
    public synchronized void stopDetectDevice() {
        synchronized (this.mDetectWifiConnections) {
            this.mDetectWifiConnections.clear();
        }
        this.mDetectHandler.getLooper().quit();
        mWifiDeviceDetection = null;
        this.mIsThreadWhile = false;
        if (this.mCarlifeDetectThread != null) {
            this.mCarlifeDetectThread.interrupt();
            this.mCarlifeDetectThread = null;
        }
    }
}
