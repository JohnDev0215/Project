package com.engine.tbox.core.conn;

import android.os.Handler;
import com.engine.tbox.core.data.CountSyncMessage;
import com.engine.tbox.core.utils.LogUtils;
import java.nio.ByteBuffer;
import java.util.ArrayList;

/* JADX INFO: loaded from: classes.dex */
public class AbstractConnection implements IConnection {
    private static final int MAX_HEARTBEAT_COUNT = 3;
    public static final String TAG = "TBoxConnection";
    protected Handler mHandler;
    protected ArrayList<ConnectionStateChangeCallback> mConnStateCallbacks = new ArrayList<>();
    protected ConnectionState mState = ConnectionState.IDLE;
    private int heartbeatCount = 0;

    protected void addHandler(Handler handler) {
        this.mHandler = handler;
    }

    @Override // com.engine.tbox.core.conn.IConnection
    public boolean init() {
        return false;
    }

    @Override // com.engine.tbox.core.conn.IConnection
    public boolean deInit() {
        return false;
    }

    @Override // com.engine.tbox.core.conn.IConnection
    public boolean start() {
        return false;
    }

    @Override // com.engine.tbox.core.conn.IConnection
    public boolean stop() {
        return false;
    }

    @Override // com.engine.tbox.core.conn.IConnection
    public boolean sendAsyncMessage(CountSyncMessage msg) {
        return false;
    }

    @Override // com.engine.tbox.core.conn.IConnection
    public boolean sendSyncMessage(CountSyncMessage msg, IResponse response) {
        return false;
    }

    @Override // com.engine.tbox.core.conn.IConnection
    public boolean sendByteBuffer(ByteBuffer buff) {
        return false;
    }

    @Override // com.engine.tbox.core.conn.IConnection
    public boolean registerConnectionStateCallback(ConnectionStateChangeCallback callback) {
        synchronized (this) {
            boolean register = this.mConnStateCallbacks.contains(callback);
            if (register) {
                LogUtils.d(TAG, "the callback:" + callback + " have been registered", new Object[0]);
                return false;
            }
            this.mConnStateCallbacks.add(callback);
            return true;
        }
    }

    @Override // com.engine.tbox.core.conn.IConnection
    public boolean unregisterConnectionStateCallback(ConnectionStateChangeCallback callback) {
        boolean zRemove;
        synchronized (this) {
            zRemove = this.mConnStateCallbacks.remove(callback);
        }
        return zRemove;
    }

    protected void onConnectionStateChanged(final ConnectionState state) {
        if (this.mHandler == null) {
            LogUtils.e(TAG, "mHandler is null, please register handler first", new Object[0]);
            return;
        }
        synchronized (this) {
            if (this.mState == state) {
                LogUtils.d(TAG, "onConnectionStateChanged with same state:" + state, new Object[0]);
                return;
            }
            this.mState = state;
            final ArrayList<ConnectionStateChangeCallback> tmpCallbacks = new ArrayList<>(this.mConnStateCallbacks);
            this.mHandler.post(new Runnable() { // from class: com.engine.tbox.core.conn.-$$Lambda$AbstractConnection$vnmJhKFHTwp5SStKe0JGU-wPghw
                @Override // java.lang.Runnable
                public final void run() {
                    AbstractConnection.lambda$onConnectionStateChanged$0(state, tmpCallbacks);
                }
            });
        }
    }

    static /* synthetic */ void lambda$onConnectionStateChanged$0(ConnectionState state, ArrayList finalTmpCallbacks) {
        LogUtils.i(TAG, "on ConnectionState changed:" + state, new Object[0]);
        for (int i = 0; i < finalTmpCallbacks.size(); i++) {
            ((ConnectionStateChangeCallback) finalTmpCallbacks.get(i)).onConnectionState(state);
        }
    }

    public boolean addHeartbeatCount() {
        LogUtils.d(TAG, "addHeartbeatCount state:" + this.mState + " heartbeat count:" + this.heartbeatCount, new Object[0]);
        if (this.mState != ConnectionState.CONNECTED) {
            return true;
        }
        int i = this.heartbeatCount;
        this.heartbeatCount = i + 1;
        return i < 3;
    }

    public void resetHeartBeatCount() {
        this.heartbeatCount = 0;
    }
}
