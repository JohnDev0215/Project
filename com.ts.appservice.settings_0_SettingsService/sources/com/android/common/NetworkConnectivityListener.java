package com.android.common;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.net.NetworkInfo;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import java.util.HashMap;

/* JADX INFO: loaded from: classes.dex */
@Deprecated
public class NetworkConnectivityListener {
    private static final boolean DBG = false;
    private static final String TAG = "NetworkConnectivityListener";
    private Context mContext;
    private boolean mIsFailover;
    private boolean mListening;
    private NetworkInfo mNetworkInfo;
    private NetworkInfo mOtherNetworkInfo;
    private String mReason;
    private HashMap<Handler, Integer> mHandlers = new HashMap<>();
    private State mState = State.UNKNOWN;
    private ConnectivityBroadcastReceiver mReceiver = new ConnectivityBroadcastReceiver();

    public enum State {
        UNKNOWN,
        CONNECTED,
        NOT_CONNECTED
    }

    private class ConnectivityBroadcastReceiver extends BroadcastReceiver {
        private ConnectivityBroadcastReceiver() {
        }

        @Override // android.content.BroadcastReceiver
        public void onReceive(Context context, Intent intent) {
            String action = intent.getAction();
            if (!action.equals("android.net.conn.CONNECTIVITY_CHANGE") || !NetworkConnectivityListener.this.mListening) {
                Log.w(NetworkConnectivityListener.TAG, "onReceived() called with " + NetworkConnectivityListener.this.mState.toString() + " and " + intent);
                return;
            }
            boolean noConnectivity = intent.getBooleanExtra("noConnectivity", NetworkConnectivityListener.DBG);
            if (noConnectivity) {
                NetworkConnectivityListener.this.mState = State.NOT_CONNECTED;
            } else {
                NetworkConnectivityListener.this.mState = State.CONNECTED;
            }
            NetworkConnectivityListener.this.mNetworkInfo = (NetworkInfo) intent.getParcelableExtra("networkInfo");
            NetworkConnectivityListener.this.mOtherNetworkInfo = (NetworkInfo) intent.getParcelableExtra("otherNetwork");
            NetworkConnectivityListener.this.mReason = intent.getStringExtra("reason");
            NetworkConnectivityListener.this.mIsFailover = intent.getBooleanExtra("isFailover", NetworkConnectivityListener.DBG);
            for (Handler target : NetworkConnectivityListener.this.mHandlers.keySet()) {
                Message message = Message.obtain(target, ((Integer) NetworkConnectivityListener.this.mHandlers.get(target)).intValue());
                target.sendMessage(message);
            }
        }
    }

    public synchronized void startListening(Context context) {
        if (!this.mListening) {
            this.mContext = context;
            IntentFilter filter = new IntentFilter();
            filter.addAction("android.net.conn.CONNECTIVITY_CHANGE");
            context.registerReceiver(this.mReceiver, filter);
            this.mListening = true;
        }
    }

    public synchronized void stopListening() {
        if (this.mListening) {
            this.mContext.unregisterReceiver(this.mReceiver);
            this.mContext = null;
            this.mNetworkInfo = null;
            this.mOtherNetworkInfo = null;
            this.mIsFailover = DBG;
            this.mReason = null;
            this.mListening = DBG;
        }
    }

    public void registerHandler(Handler target, int what) {
        this.mHandlers.put(target, Integer.valueOf(what));
    }

    public void unregisterHandler(Handler target) {
        this.mHandlers.remove(target);
    }

    public State getState() {
        return this.mState;
    }

    public NetworkInfo getNetworkInfo() {
        return this.mNetworkInfo;
    }

    public NetworkInfo getOtherNetworkInfo() {
        return this.mOtherNetworkInfo;
    }

    public boolean isFailover() {
        return this.mIsFailover;
    }

    public String getReason() {
        return this.mReason;
    }
}
