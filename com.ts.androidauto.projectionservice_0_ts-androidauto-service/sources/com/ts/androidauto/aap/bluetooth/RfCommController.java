package com.ts.androidauto.aap.bluetooth;

import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.bluetooth.BluetoothServerSocket;
import android.bluetooth.BluetoothSocket;
import android.content.Context;
import android.os.SystemClock;
import android.text.TextUtils;
import com.google.common.util.concurrent.ListeningExecutorService;
import com.google.common.util.concurrent.MoreExecutors;
import com.ts.androidauto.fsdk.common.LogEncrypt;
import com.ts.androidauto.fsdk.common.LogUtil;
import com.ts.androidauto.fsdk.data.AndroidAutoDevice;
import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.Executors;

/* JADX INFO: loaded from: classes.dex */
public class RfCommController {
    private static final int DEFAULT_LOAD_FACTOR = 16;
    public static final int MAX_DEVICE_SIZE = 21;
    private static final int RFCOMM_SOCKET_SLEEP_1000 = 1000;
    private static final String SDP_NAME = "Android Auto Bluetooth RFCOMM Device";
    private static final String TAG = "AAPRfCommController";
    private BluetoothAdapter mBluetoothAdapter;
    private ListeningExecutorService mExecutorService;
    private RfCommConnectionListener mRfCommConnectionListener;
    private BluetoothServerSocket mServerSocket;
    private static final UUID KNOWN_UUID = UUID.fromString("4de17a00-52cb-11e6-bdf4-0800200c9a66");
    private static final UUID PHONE_UUID = UUID.fromString("c26cf572-3369-4cf2-b5cc-d2cd130f5b2c");
    private RfcommServerThread mRfcommServerThread = null;
    private Map<BluetoothDevice, BluetoothSocket> mRemoteDevices = new LinkedHashMap(21, 16.0f, true);
    private boolean mRunRfcommServer = false;

    private static class RfCommControllerInner {
        private static RfCommController rfCommController = new RfCommController();

        private RfCommControllerInner() {
        }
    }

    public void startRfcommServerThread() {
        LogUtil.verbose(TAG, "startRfcommServerThread is invoke!");
        if (this.mRfcommServerThread != null) {
            stopRfcommServerThread();
        }
        this.mRunRfcommServer = true;
        RfcommServerThread rfcommServerThread = new RfcommServerThread(this.mBluetoothAdapter);
        this.mRfcommServerThread = rfcommServerThread;
        rfcommServerThread.start();
    }

    public void stopRfcommServerThread() {
        LogUtil.verbose(TAG, "stopRfcommServerThread is invoke!");
        this.mRunRfcommServer = false;
        RfcommServerThread rfcommServerThread = this.mRfcommServerThread;
        if (rfcommServerThread != null) {
            rfcommServerThread.stopThread();
            RfcommServerThread.interrupted();
            this.mRfcommServerThread = null;
        }
    }

    private class RfcommServerThread extends Thread {
        private BluetoothAdapter mAdapter;
        private BluetoothServerSocket mSocket = null;

        public RfcommServerThread(BluetoothAdapter adapter) {
            this.mAdapter = null;
            this.mAdapter = adapter;
        }

        public void stopThread() {
            if (this.mSocket != null) {
                LogUtil.debug(RfCommController.TAG, "Force close server socket to interrupt accept() method");
                try {
                    this.mSocket.close();
                } catch (IOException ex) {
                    LogUtil.error(RfCommController.TAG, "mSocket close error: " + ex.toString());
                }
            }
        }

        @Override // java.lang.Thread, java.lang.Runnable
        public void run() {
            LogUtil.verbose(RfCommController.TAG, "RfcommServerThread start, adapter " + this.mAdapter);
            while (RfCommController.this.mRunRfcommServer) {
                BluetoothAdapter bluetoothAdapter = this.mAdapter;
                if (bluetoothAdapter != null) {
                    if (this.mSocket == null) {
                        try {
                            if (!bluetoothAdapter.isEnabled()) {
                                LogUtil.info(RfCommController.TAG, "Bluetooth is close continue");
                                SystemClock.sleep(1000L);
                            } else {
                                this.mSocket = this.mAdapter.listenUsingRfcommWithServiceRecord(RfCommController.SDP_NAME, RfCommController.KNOWN_UUID);
                                LogUtil.info(RfCommController.TAG, "Get BT server socket: " + this.mSocket);
                            }
                        } catch (IOException excep) {
                            LogUtil.error(RfCommController.TAG, "listen error: " + excep.toString());
                            SystemClock.sleep(1000L);
                        }
                    }
                    if (this.mSocket != null) {
                        LogUtil.verbose(RfCommController.TAG, "Try to accepting connection request...");
                        try {
                            BluetoothSocket clientSocket = this.mSocket.accept();
                            LogUtil.info(RfCommController.TAG, "Get BT client socket: " + clientSocket);
                            if (clientSocket == null) {
                                continue;
                            } else {
                                if (!RfCommController.this.mRunRfcommServer) {
                                    LogUtil.debug(RfCommController.TAG, "Should stop rfcomm server here, close client socket");
                                    RfCommController.this.closeSocket(clientSocket);
                                    return;
                                }
                                RfCommController.this.receiveNewClientSocket(clientSocket, true);
                            }
                        } catch (IOException excep2) {
                            LogUtil.error(RfCommController.TAG, "accept error: " + excep2.toString());
                            try {
                                this.mSocket.close();
                                this.mSocket = null;
                            } catch (IOException ex) {
                                LogUtil.error(RfCommController.TAG, "mSocket close error: " + ex.toString());
                            }
                            SystemClock.sleep(1000L);
                        }
                    } else {
                        continue;
                    }
                }
            }
        }
    }

    public void startRfcommClientThread(BluetoothDevice device) {
        LogUtil.verbose(TAG, "startRfcommClientThread is invoke!");
        new RfcommClientThread(device).start();
    }

    private class RfcommClientThread extends Thread {
        private BluetoothDevice mDevice;
        private BluetoothSocket mSocket = null;

        public RfcommClientThread(BluetoothDevice device) {
            this.mDevice = device;
        }

        @Override // java.lang.Thread, java.lang.Runnable
        public void run() {
            BluetoothDevice bluetoothDevice;
            LogUtil.verbose(RfCommController.TAG, "RfcommClientThread start, device: " + this.mDevice);
            if (this.mSocket == null && (bluetoothDevice = this.mDevice) != null) {
                try {
                    this.mSocket = bluetoothDevice.createRfcommSocketToServiceRecord(RfCommController.PHONE_UUID);
                } catch (IOException ex) {
                    LogUtil.error(RfCommController.TAG, "create error: " + ex.toString());
                }
            }
            if (this.mSocket != null) {
                LogUtil.verbose(RfCommController.TAG, "Try to send connection request...");
                try {
                    this.mSocket.connect();
                    LogUtil.info(RfCommController.TAG, "Socket connect success: " + this.mSocket);
                    RfCommController.this.receiveNewClientSocket(this.mSocket, false);
                } catch (IOException ex2) {
                    LogUtil.error(RfCommController.TAG, "Connect error: " + ex2.toString());
                    try {
                        this.mSocket.close();
                        this.mSocket = null;
                    } catch (IOException exception) {
                        LogUtil.error(RfCommController.TAG, "unable to close socket during connection failure: " + exception.toString());
                    }
                }
            }
        }
    }

    public static RfCommController getInstance() {
        return RfCommControllerInner.rfCommController;
    }

    public void receiveNewClientSocket(BluetoothSocket client, boolean isServer) {
        if (client != null) {
            BluetoothDevice remoteDevice = client.getRemoteDevice();
            LogUtil.verbose(TAG, "new client: " + client + " device: " + remoteDevice);
            Map<BluetoothDevice, BluetoothSocket> map = this.mRemoteDevices;
            if (map != null) {
                int size = map.size();
                LogUtil.info(TAG, "receiveNewClientSocket remote device size:" + size);
                if (size >= 21) {
                    removeBluetoothSocket();
                }
                BluetoothSocket oldSocket = this.mRemoteDevices.get(remoteDevice);
                LogUtil.info(TAG, "receiveNewClientSocket oldSocket:" + oldSocket);
                if (oldSocket != null) {
                    closeSocket(oldSocket);
                }
                this.mRemoteDevices.put(remoteDevice, client);
            }
            LogUtil.info(TAG, "receiveNewClientSocket notify new socket");
            RfCommConnectionListener rfCommConnectionListener = this.mRfCommConnectionListener;
            if (rfCommConnectionListener != null) {
                rfCommConnectionListener.onConnectedTo(client, isServer);
            }
        }
    }

    private void removeBluetoothSocket() {
        Map<BluetoothDevice, BluetoothSocket> map = this.mRemoteDevices;
        if (map != null) {
            for (BluetoothDevice device : map.keySet()) {
                if (device != null) {
                    BluetoothSocket socket = this.mRemoteDevices.get(device);
                    String address = device.getAddress();
                    boolean isConnected = isConnectedDevice(address);
                    LogUtil.debug(TAG, "address:" + LogEncrypt.MacAddressEncrypt(address) + " isConnected:" + isConnected);
                    if (!isConnected) {
                        closeSocket(socket);
                        this.mRemoteDevices.remove(device);
                        return;
                    }
                }
            }
        }
    }

    private boolean isConnectedDevice(String address) {
        List<AndroidAutoDevice> deviceList = new ArrayList<>();
        RfCommConnectionListener rfCommConnectionListener = this.mRfCommConnectionListener;
        if (rfCommConnectionListener != null) {
            deviceList = rfCommConnectionListener.getCurrConnectedDevice();
        }
        if (deviceList == null) {
            return false;
        }
        for (AndroidAutoDevice device : deviceList) {
            if (device != null && device.isWireLessLink() && device.isAapMode() && device.getBluetoothDevice() != null) {
                String btAddress = device.getBluetoothDevice().getAddress();
                if (!TextUtils.isEmpty(address) && address.equals(btAddress)) {
                    return true;
                }
            }
        }
        return false;
    }

    public void removeRfcommMap(BluetoothDevice device) {
        Map<BluetoothDevice, BluetoothSocket> map = this.mRemoteDevices;
        if (map != null && device != null && map.containsKey(device)) {
            BluetoothSocket bluetoothSocket = this.mRemoteDevices.get(device);
            if (bluetoothSocket != null) {
                try {
                    LogUtil.info(TAG, "bluetoothSocket close start:" + bluetoothSocket.isConnected());
                    bluetoothSocket.close();
                    LogUtil.info(TAG, "bluetoothSocket close end:" + bluetoothSocket.isConnected());
                } catch (IOException exception) {
                    LogUtil.info(TAG, "removeRfcommMap close exception:" + exception);
                    exception.printStackTrace();
                }
            }
            this.mRemoteDevices.remove(device);
        }
    }

    public boolean isRfcommConnected(BluetoothDevice device) {
        LogUtil.debug(TAG, "isRfcommConnected device :" + device);
        Map<BluetoothDevice, BluetoothSocket> map = this.mRemoteDevices;
        if (map != null && device != null && map.containsKey(device)) {
            BluetoothSocket bluetoothSocket = this.mRemoteDevices.get(device);
            if (bluetoothSocket != null && bluetoothSocket.isConnected()) {
                LogUtil.info(TAG, "bluetoothSocket is connected");
                return true;
            }
            LogUtil.info(TAG, "bluetoothSocket is not connected");
            closeSocket(bluetoothSocket);
            this.mRemoteDevices.remove(device);
            return false;
        }
        LogUtil.info(TAG, "bluetoothSocket is not in rfcomm list");
        return false;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void closeSocket(BluetoothSocket socket) {
        if (socket != null) {
            try {
                LogUtil.info(TAG, "closeSocket start close:" + socket.isConnected());
                socket.close();
                LogUtil.info(TAG, "closeSocket finish close:" + socket.isConnected());
            } catch (IOException ex) {
                LogUtil.info(TAG, "closeSocket Exception");
                ex.printStackTrace();
            }
        }
    }

    public void init(Context context) {
        this.mExecutorService = MoreExecutors.listeningDecorator(Executors.newCachedThreadPool());
        this.mBluetoothAdapter = BluetoothAdapter.getDefaultAdapter();
    }

    public void startRfCommServer() {
        LogUtil.info(TAG, "startRfCommServer mServerSocket:" + this.mServerSocket);
        startRfcommServerThread();
    }

    private void shutdownRfcommServer() {
        BluetoothServerSocket serverSocket = this.mServerSocket;
        if (serverSocket == null) {
            return;
        }
        this.mServerSocket = null;
        try {
            serverSocket.close();
        } catch (IOException ex) {
            LogUtil.error(TAG, "Error closing RFCOMM server, nothing to do", ex);
        }
    }

    public void registRfCommListener(RfCommConnectionListener rfCommConnectionListener) {
        this.mRfCommConnectionListener = rfCommConnectionListener;
    }

    public void unRegistRfCommListener() {
        this.mRfCommConnectionListener = null;
    }
}
