package com.ts.carplay.lib.bluetooth;

import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.bluetooth.BluetoothServerSocket;
import android.bluetooth.BluetoothSocket;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.util.Log;
import com.ts.carplay.common.util.LogEncrypt;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.HashSet;
import java.util.Hashtable;
import java.util.UUID;
import java.util.concurrent.atomic.AtomicInteger;

/* JADX INFO: loaded from: classes.dex */
public class BluetoothSerialPort {
    public static final int INVALID_HANDLE = -1;
    private static final String TAG = "BluetoothSerialPort";
    private static BluetoothSerialPort sInstance = null;
    private BluetoothAdapter mAdapter;
    private HashSet<BluetoothSerialPortCallback> mCallbacks;
    private Context mContext;
    private AtomicInteger mCurrentHandle;
    private Hashtable<UUID, SerialPortServer> mServerSocketMap;
    private Hashtable<Integer, BluetoothSocket> mSocketMap;

    public static class BluetoothSerialPortCallback {
        public void onPeerConnected(String uuid, int handle, String address) {
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void handleBluetoothDisabled() {
        synchronized (this.mServerSocketMap) {
            for (SerialPortServer server : this.mServerSocketMap.values()) {
                server.stopServer();
            }
        }
        this.mServerSocketMap.clear();
        synchronized (this.mSocketMap) {
            for (BluetoothSocket socket : this.mSocketMap.values()) {
                try {
                    socket.close();
                } catch (IOException e) {
                }
            }
        }
        this.mSocketMap.clear();
    }

    private class SppBroadcastReceiver extends BroadcastReceiver {
        private SppBroadcastReceiver() {
        }

        @Override // android.content.BroadcastReceiver
        public void onReceive(Context context, Intent intent) {
            try {
                String action = intent.getAction();
                if (action.equals("android.bluetooth.adapter.action.STATE_CHANGED")) {
                    int state = intent.getIntExtra("android.bluetooth.adapter.extra.STATE", 10);
                    if (state == 10) {
                        BluetoothSerialPort.this.handleBluetoothDisabled();
                    }
                }
            } catch (Exception exception) {
                exception.printStackTrace();
            }
        }
    }

    private class SerialPortServer extends Thread {
        private BluetoothServerSocket mServerSocket;
        private boolean mToExit = false;
        private String mUuid;

        public SerialPortServer(String uuid, BluetoothServerSocket socket) {
            this.mUuid = null;
            this.mServerSocket = null;
            this.mUuid = uuid;
            this.mServerSocket = socket;
        }

        @Override // java.lang.Thread, java.lang.Runnable
        public void run() throws IOException {
            Log.d(BluetoothSerialPort.TAG, "SerialPortServer.run: enter, mUuid=" + LogEncrypt.MacAddressEncrypt(this.mUuid));
            while (true) {
                if (this.mToExit) {
                    break;
                }
                BluetoothSocket btSocket = null;
                try {
                    btSocket = this.mServerSocket.accept();
                } catch (IOException e) {
                }
                if (BluetoothSerialPort.this.mAdapter.getState() != 12) {
                    if (btSocket != null) {
                        try {
                            btSocket.close();
                        } catch (IOException ex) {
                            Log.e(BluetoothSerialPort.TAG, "closeConnection:", ex);
                            return;
                        }
                    }
                } else if (btSocket != null) {
                    int handle = BluetoothSerialPort.this.mCurrentHandle.getAndIncrement();
                    BluetoothSerialPort.this.mSocketMap.put(Integer.valueOf(handle), btSocket);
                    synchronized (BluetoothSerialPort.this.mCallbacks) {
                        for (BluetoothSerialPortCallback callback : BluetoothSerialPort.this.mCallbacks) {
                            BluetoothDevice device = btSocket.getRemoteDevice();
                            if (device != null) {
                                callback.onPeerConnected(this.mUuid, handle, device.getAddress());
                            } else {
                                callback.onPeerConnected(this.mUuid, handle, null);
                            }
                        }
                    }
                }
            }
            Log.d(BluetoothSerialPort.TAG, "SerialPortServer.run: exit");
        }

        public void stopServer() {
            this.mToExit = true;
            try {
                this.mServerSocket.close();
            } catch (IOException e) {
            }
        }
    }

    private BluetoothSerialPort(Context context) {
        this.mContext = null;
        this.mCallbacks = null;
        this.mCurrentHandle = null;
        this.mAdapter = null;
        this.mServerSocketMap = null;
        this.mSocketMap = null;
        this.mContext = context;
        this.mCallbacks = new HashSet<>(1);
        this.mCurrentHandle = new AtomicInteger(0);
        android.bluetooth.BluetoothManager btManager = (android.bluetooth.BluetoothManager) this.mContext.getSystemService("bluetooth");
        this.mAdapter = btManager.getAdapter();
        this.mServerSocketMap = new Hashtable<>();
        this.mSocketMap = new Hashtable<>();
        SppBroadcastReceiver receiver = new SppBroadcastReceiver();
        IntentFilter filter = new IntentFilter();
        filter.addAction("android.bluetooth.adapter.action.STATE_CHANGED");
        this.mContext.registerReceiver(receiver, filter);
    }

    public static synchronized BluetoothSerialPort getInstance(Context context) {
        if (sInstance == null) {
            if (context == null) {
                return null;
            }
            sInstance = new BluetoothSerialPort(context);
        }
        return sInstance;
    }

    public void registerCallback(BluetoothSerialPortCallback callback) {
        if (callback == null) {
            return;
        }
        synchronized (this.mCallbacks) {
            this.mCallbacks.add(callback);
        }
    }

    public void unregisterCallback(BluetoothSerialPortCallback callback) {
        if (callback == null) {
            return;
        }
        synchronized (this.mCallbacks) {
            this.mCallbacks.remove(callback);
        }
    }

    private static UUID getUuidFromString(String uuid) {
        if (uuid == null) {
            return null;
        }
        try {
            UUID theUuid = UUID.fromString(uuid);
            return theUuid;
        } catch (IllegalArgumentException e) {
            return null;
        }
    }

    public boolean startServer(String name, String uuid) {
        Log.d(TAG, "startServer: enter, name=" + name + ", uuid=" + LogEncrypt.MacAddressEncrypt(uuid));
        UUID theUuid = getUuidFromString(uuid);
        if (theUuid == null || !theUuid.toString().equalsIgnoreCase(uuid) || this.mAdapter.getState() != 12) {
            return false;
        }
        synchronized (this.mServerSocketMap) {
            if (this.mServerSocketMap.containsKey(theUuid)) {
                return false;
            }
            try {
                BluetoothServerSocket serverSocket = this.mAdapter.listenUsingRfcommWithServiceRecord(name, theUuid);
                SerialPortServer server = new SerialPortServer(uuid, serverSocket);
                this.mServerSocketMap.put(theUuid, server);
                server.start();
                Log.d(TAG, "startServer: exit");
                return true;
            } catch (IOException ex) {
                Log.e(TAG, "startServer:", ex);
                return false;
            }
        }
    }

    public boolean stopServer(String uuid) {
        SerialPortServer server;
        Log.d(TAG, "stopServer: enter, uuid=" + LogEncrypt.MacAddressEncrypt(uuid));
        UUID theUuid = getUuidFromString(uuid);
        if (theUuid == null || !theUuid.toString().equalsIgnoreCase(uuid) || (server = this.mServerSocketMap.get(theUuid)) == null) {
            return false;
        }
        this.mServerSocketMap.remove(theUuid);
        server.stopServer();
        Log.d(TAG, "stopServer: exit");
        return true;
    }

    public int connectToServer(String address, String uuid) {
        UUID theUuid;
        Log.d(TAG, "connectToServer: enter, address=" + LogEncrypt.MacAddressEncrypt(address) + ", uuid=" + LogEncrypt.MacAddressEncrypt(uuid));
        if (!BluetoothAdapter.checkBluetoothAddress(address) || (theUuid = getUuidFromString(uuid)) == null || !theUuid.toString().equalsIgnoreCase(uuid)) {
            return -1;
        }
        BluetoothDevice btDevice = this.mAdapter.getRemoteDevice(address);
        try {
            BluetoothSocket btSocket = btDevice.createRfcommSocketToServiceRecord(theUuid);
            this.mAdapter.cancelDiscovery();
            btSocket.connect();
            int handle = this.mCurrentHandle.getAndIncrement();
            this.mSocketMap.put(Integer.valueOf(handle), btSocket);
            Log.d(TAG, "connectToServer: exit, handle=" + handle);
            return handle;
        } catch (IOException ex) {
            Log.e(TAG, "connectToServer:", ex);
            return -1;
        }
    }

    public InputStream getInputStream(int handle) {
        Log.d(TAG, "getInputStream: enter, handle=" + handle);
        BluetoothSocket btSocket = this.mSocketMap.get(Integer.valueOf(handle));
        if (btSocket == null) {
            return null;
        }
        try {
            InputStream stream = btSocket.getInputStream();
            Log.d(TAG, "getInputStream: exit, stream=" + stream);
            return stream;
        } catch (IOException ex) {
            Log.e(TAG, "getInputStream:", ex);
            return null;
        }
    }

    public OutputStream getOutputStream(int handle) {
        Log.d(TAG, "getOutputStream: enter, handle=" + handle);
        BluetoothSocket btSocket = this.mSocketMap.get(Integer.valueOf(handle));
        if (btSocket == null) {
            return null;
        }
        try {
            OutputStream stream = btSocket.getOutputStream();
            Log.d(TAG, "getOutputStream: exit, stream=" + stream);
            return stream;
        } catch (IOException ex) {
            Log.e(TAG, "getOutputStream:", ex);
            return null;
        }
    }

    public boolean closeConnection(int handle) {
        BluetoothSocket btSocket = this.mSocketMap.get(Integer.valueOf(handle));
        if (btSocket == null) {
            return false;
        }
        try {
            btSocket.close();
            return true;
        } catch (IOException ex) {
            Log.e(TAG, "closeConnection:", ex);
            return false;
        }
    }
}
