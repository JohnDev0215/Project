package com.android.bluetooth.avrcpcontroller;

import android.bluetooth.BluetoothDevice;
import android.bluetooth.BluetoothSocket;
import android.os.Handler;
import android.os.HandlerThread;
import android.os.Looper;
import android.os.Message;
import android.util.Log;
import com.android.bluetooth.BluetoothObexTransport;
import java.io.IOException;
import java.lang.ref.WeakReference;
import javax.obex.ClientSession;
import javax.obex.HeaderSet;

/* JADX INFO: loaded from: classes.dex */
public class AvrcpBipClient {
    private static final int CONNECT = 0;
    private static final int DISCONNECT = 1;
    private static final int REFRESH_OBEX_SESSION = 3;
    private static final int REQUEST = 2;
    private final Callback mCallback;
    private final BluetoothDevice mDevice;
    private final Handler mHandler;
    private final int mPsm;
    private ClientSession mSession;
    private BluetoothSocket mSocket;
    private int mState = 0;
    private final HandlerThread mThread;
    private BluetoothObexTransport mTransport;
    private static final String TAG = "AvrcpBipClient";
    private static final boolean DBG = Log.isLoggable(TAG, 3);
    private static final byte[] BLUETOOTH_UUID_AVRCP_COVER_ART = {113, 99, -35, 84, 74, 126, 17, -30, -76, 124, 0, 80, -62, 73, 0, 72};

    interface Callback {
        void onConnectionStateChanged(int i, int i2);

        void onGetImageComplete(int i, String str, BipImage bipImage);

        void onGetImagePropertiesComplete(int i, String str, BipImageProperties bipImageProperties);
    }

    public AvrcpBipClient(BluetoothDevice remoteDevice, int psm, Callback callback) {
        if (remoteDevice == null) {
            throw new NullPointerException("Remote device is null");
        }
        if (callback == null) {
            throw new NullPointerException("Callback is null");
        }
        this.mDevice = remoteDevice;
        this.mPsm = psm;
        this.mCallback = callback;
        HandlerThread handlerThread = new HandlerThread(TAG);
        this.mThread = handlerThread;
        handlerThread.start();
        Looper looper = this.mThread.getLooper();
        AvrcpBipClientHandler avrcpBipClientHandler = new AvrcpBipClientHandler(looper, this);
        this.mHandler = avrcpBipClientHandler;
        avrcpBipClientHandler.obtainMessage(0).sendToTarget();
    }

    public void refreshSession() {
        debug("Refresh client session");
        if (!isConnected()) {
            error("Tried to do a reconnect operation on a client that is not connected");
        } else {
            try {
                this.mHandler.obtainMessage(3).sendToTarget();
            } catch (IllegalStateException e) {
            }
        }
    }

    public void shutdown() {
        debug("Shutdown client");
        try {
            this.mHandler.obtainMessage(1).sendToTarget();
            this.mThread.quitSafely();
        } catch (IllegalStateException e) {
        }
    }

    public synchronized int getState() {
        return this.mState;
    }

    public boolean isConnected() {
        return getState() == 2;
    }

    public int getL2capPsm() {
        return this.mPsm;
    }

    public boolean getImageProperties(String imageHandle) {
        RequestGetImageProperties request = new RequestGetImageProperties(imageHandle);
        Handler handler = this.mHandler;
        boolean status = handler.sendMessage(handler.obtainMessage(2, request));
        if (!status) {
            error("Adding messages failed, connection state: " + isConnected());
            return false;
        }
        return true;
    }

    public boolean getImage(String imageHandle, BipImageDescriptor descriptor) {
        RequestGetImage request = new RequestGetImage(imageHandle, descriptor);
        Handler handler = this.mHandler;
        boolean status = handler.sendMessage(handler.obtainMessage(2, request));
        if (!status) {
            error("Adding messages failed, connection state: " + isConnected());
            return false;
        }
        return true;
    }

    public boolean getLinkedThumbnail(String imageHandle) {
        RequestGetLinkedThumbnail request = new RequestGetLinkedThumbnail(imageHandle);
        Handler handler = this.mHandler;
        boolean status = handler.sendMessage(handler.obtainMessage(2, request));
        if (!status) {
            error("Adding messages failed, connection state: " + isConnected());
            return false;
        }
        return true;
    }

    private void setConnectionState(int state) {
        int oldState;
        synchronized (this) {
            oldState = this.mState;
            this.mState = state;
        }
        if (oldState != state) {
            this.mCallback.onConnectionStateChanged(oldState, state);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public synchronized void connect() {
        debug("Connect using psm: " + this.mPsm);
        if (isConnected()) {
            warn("Already connected");
            return;
        }
        try {
            setConnectionState(1);
            BluetoothSocket bluetoothSocketCreateL2capSocket = this.mDevice.createL2capSocket(this.mPsm);
            this.mSocket = bluetoothSocketCreateL2capSocket;
            bluetoothSocketCreateL2capSocket.connect();
            this.mTransport = new BluetoothObexTransport(this.mSocket);
            this.mSession = new ClientSession(this.mTransport);
            HeaderSet headerSet = new HeaderSet();
            headerSet.setHeader(70, BLUETOOTH_UUID_AVRCP_COVER_ART);
            int responseCode = this.mSession.connect(headerSet).getResponseCode();
            if (responseCode == 160) {
                setConnectionState(2);
                debug("Connection established");
            } else {
                error("Error connecting, code: " + responseCode);
                disconnect();
            }
        } catch (IOException e) {
            error("Exception while connecting to AVRCP BIP server", e);
            disconnect();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public synchronized void refreshObexSession() {
        if (this.mSession == null) {
            return;
        }
        try {
            setConnectionState(3);
            this.mSession.disconnect((HeaderSet) null);
            debug("Disconnected from OBEX session");
            try {
                setConnectionState(1);
                HeaderSet headerSet = new HeaderSet();
                headerSet.setHeader(70, BLUETOOTH_UUID_AVRCP_COVER_ART);
                int responseCode = this.mSession.connect(headerSet).getResponseCode();
                if (responseCode == 160) {
                    setConnectionState(2);
                    debug("Reconnection established");
                } else {
                    error("Error reconnecting, code: " + responseCode);
                    disconnect();
                }
            } catch (IOException e) {
                error("Exception while reconnecting to AVRCP BIP server", e);
                disconnect();
            }
        } catch (IOException e2) {
            error("Exception while disconnecting from AVRCP BIP server", e2);
            disconnect();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public synchronized void disconnect() {
        if (this.mSession != null) {
            setConnectionState(3);
            try {
                this.mSession.disconnect((HeaderSet) null);
                debug("Disconnected from OBEX session");
            } catch (IOException e) {
                error("Exception while disconnecting from AVRCP BIP server: " + e.toString());
            }
            try {
                this.mSession.close();
                this.mTransport.close();
                this.mSocket.close();
                debug("Closed underlying session, transport and socket");
            } catch (IOException e2) {
                error("Exception while closing AVRCP BIP session: ", e2);
            }
            this.mSession = null;
            this.mTransport = null;
            this.mSocket = null;
            setConnectionState(0);
        } else {
            setConnectionState(0);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void executeRequest(BipRequest request) {
        if (!isConnected()) {
            error("Cannot execute request " + request.toString() + ", we're not connected");
            notifyCaller(request);
            return;
        }
        try {
            request.execute(this.mSession);
            notifyCaller(request);
            debug("Completed request - " + request.toString());
        } catch (IOException e) {
            error("Request failed: " + request.toString());
            notifyCaller(request);
            disconnect();
        }
    }

    private void notifyCaller(BipRequest request) {
        int type = request.getType();
        int responseCode = request.getResponseCode();
        debug("Notifying caller of request complete - " + request.toString());
        if (type == 0) {
            String imageHandle = ((RequestGetImageProperties) request).getImageHandle();
            BipImageProperties properties = ((RequestGetImageProperties) request).getImageProperties();
            this.mCallback.onGetImagePropertiesComplete(responseCode, imageHandle, properties);
        } else if (type == 1) {
            String imageHandle2 = ((RequestGetImage) request).getImageHandle();
            BipImage image = ((RequestGetImage) request).getImage();
            this.mCallback.onGetImageComplete(responseCode, imageHandle2, image);
        } else if (type == 2) {
            String imageHandle3 = ((RequestGetLinkedThumbnail) request).getImageHandle();
            BipImage image2 = ((RequestGetLinkedThumbnail) request).getImage();
            this.mCallback.onGetImageComplete(responseCode, imageHandle3, image2);
        }
    }

    private static class AvrcpBipClientHandler extends Handler {
        WeakReference<AvrcpBipClient> mInst;

        AvrcpBipClientHandler(Looper looper, AvrcpBipClient inst) {
            super(looper);
            this.mInst = new WeakReference<>(inst);
        }

        @Override // android.os.Handler
        public void handleMessage(Message msg) {
            AvrcpBipClient inst = this.mInst.get();
            int i = msg.what;
            if (i == 0) {
                if (!inst.isConnected()) {
                    inst.connect();
                }
            } else if (i == 1) {
                if (inst.isConnected()) {
                    inst.disconnect();
                }
            } else {
                if (i != 2) {
                    if (i == 3 && inst.isConnected()) {
                        inst.refreshObexSession();
                        return;
                    }
                    return;
                }
                if (inst.isConnected()) {
                    inst.executeRequest((BipRequest) msg.obj);
                }
            }
        }
    }

    private String getStateName() {
        int state = getState();
        if (state == 0) {
            return "Disconnected";
        }
        if (state == 1) {
            return "Connecting";
        }
        if (state == 2) {
            return "Connected";
        }
        if (state == 3) {
            return "Disconnecting";
        }
        return "Unknown";
    }

    public String toString() {
        return "<AvrcpBipClient device=" + this.mDevice.getAddressOnlyforLog() + " psm=" + this.mPsm + " state=" + getStateName() + ">";
    }

    private void debug(String msg) {
        if (DBG) {
            Log.d(TAG, "[" + this.mDevice.getAddressOnlyforLog() + "] " + msg);
        }
    }

    private void warn(String msg) {
        Log.w(TAG, "[" + this.mDevice.getAddressOnlyforLog() + "] " + msg);
    }

    private void error(String msg) {
        Log.e(TAG, "[" + this.mDevice.getAddressOnlyforLog() + "] " + msg);
    }

    private void error(String msg, Throwable e) {
        Log.e(TAG, "[" + this.mDevice.getAddressOnlyforLog() + "] " + msg, e);
    }
}
