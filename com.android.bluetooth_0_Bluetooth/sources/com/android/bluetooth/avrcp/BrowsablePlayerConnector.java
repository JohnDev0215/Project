package com.android.bluetooth.avrcp;

import android.content.Context;
import android.content.pm.ResolveInfo;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.util.Log;
import com.android.bluetooth.Utils;
import com.android.bluetooth.avrcp.BrowsedPlayerWrapper;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/* JADX INFO: loaded from: classes.dex */
public class BrowsablePlayerConnector {
    private static final long CONNECT_TIMEOUT_MS = 10000;
    private static final boolean DEBUG = true;
    private static final int MSG_CONNECT_CB = 1;
    private static final int MSG_GET_FOLDER_ITEMS_CB = 0;
    private static final int MSG_TIMEOUT = 2;
    private static final String TAG = "AvrcpBrowsablePlayerConnector";
    private static BrowsablePlayerConnector sInjectConnector;
    private PlayerListCallback mCallback;
    private Context mContext;
    private Handler mHandler;
    private List<BrowsedPlayerWrapper> mResults = new ArrayList();
    private Set<BrowsedPlayerWrapper> mPendingPlayers = new HashSet();

    interface PlayerListCallback {
        void run(List<BrowsedPlayerWrapper> list);
    }

    private static void setInstanceForTesting(BrowsablePlayerConnector connector) {
        Utils.enforceInstrumentationTestMode();
        sInjectConnector = connector;
    }

    static BrowsablePlayerConnector connectToPlayers(Context context, Looper looper, List<ResolveInfo> players, PlayerListCallback cb) {
        BrowsablePlayerConnector browsablePlayerConnector = sInjectConnector;
        if (browsablePlayerConnector != null) {
            return browsablePlayerConnector;
        }
        if (cb == null) {
            Log.wtf(TAG, "Null callback passed");
            return null;
        }
        final BrowsablePlayerConnector newWrapper = new BrowsablePlayerConnector(context, looper, cb);
        for (final ResolveInfo info : players) {
            BrowsedPlayerWrapper player = BrowsedPlayerWrapper.wrap(context, looper, info.serviceInfo.packageName, info.serviceInfo.name);
            newWrapper.mPendingPlayers.add(player);
            player.connect(new BrowsedPlayerWrapper.ConnectionCallback() { // from class: com.android.bluetooth.avrcp.-$$Lambda$BrowsablePlayerConnector$fdgNUAOz5VZJmQj_Mu8ExJ_72XM
                @Override // com.android.bluetooth.avrcp.BrowsedPlayerWrapper.ConnectionCallback
                public final void run(int i, BrowsedPlayerWrapper browsedPlayerWrapper) {
                    BrowsablePlayerConnector.lambda$connectToPlayers$0(info, newWrapper, i, browsedPlayerWrapper);
                }
            });
        }
        Message msg = newWrapper.mHandler.obtainMessage(2);
        newWrapper.mHandler.sendMessageDelayed(msg, CONNECT_TIMEOUT_MS);
        return newWrapper;
    }

    static /* synthetic */ void lambda$connectToPlayers$0(ResolveInfo info, BrowsablePlayerConnector newWrapper, int status, BrowsedPlayerWrapper wrapper) {
        Log.d(TAG, "Browse player callback called: package=" + info.serviceInfo.packageName + " : status=" + status);
        Message msg = newWrapper.mHandler.obtainMessage(1);
        msg.arg1 = status;
        msg.obj = wrapper;
        newWrapper.mHandler.sendMessage(msg);
    }

    private BrowsablePlayerConnector(Context context, Looper looper, PlayerListCallback cb) {
        this.mContext = context;
        this.mCallback = cb;
        this.mHandler = new AnonymousClass1(looper);
    }

    /* JADX INFO: renamed from: com.android.bluetooth.avrcp.BrowsablePlayerConnector$1, reason: invalid class name */
    class AnonymousClass1 extends Handler {
        AnonymousClass1(Looper looper) {
            super(looper);
        }

        @Override // android.os.Handler
        public void handleMessage(Message msg) {
            Log.d(BrowsablePlayerConnector.TAG, "Received a message: msg.what=" + msg.what);
            int i = msg.what;
            if (i == 0) {
                BrowsedPlayerWrapper wrapper = (BrowsedPlayerWrapper) msg.obj;
                if (!BrowsablePlayerConnector.this.mPendingPlayers.remove(wrapper)) {
                    return;
                }
                Log.i(BrowsablePlayerConnector.TAG, "Successfully added package to results: " + wrapper.getPackageName());
                BrowsablePlayerConnector.this.mResults.add(wrapper);
            } else if (i == 1) {
                final BrowsedPlayerWrapper wrapper2 = (BrowsedPlayerWrapper) msg.obj;
                if (msg.arg1 != 0) {
                    Log.i(BrowsablePlayerConnector.TAG, wrapper2.getPackageName() + " is not browsable");
                    BrowsablePlayerConnector.this.mPendingPlayers.remove(wrapper2);
                    return;
                }
                Log.i(BrowsablePlayerConnector.TAG, "Checking root contents for " + wrapper2.getPackageName());
                wrapper2.getFolderItems(wrapper2.getRootId(), new BrowsedPlayerWrapper.BrowseCallback() { // from class: com.android.bluetooth.avrcp.-$$Lambda$BrowsablePlayerConnector$1$i5E7glYRZ-XLqbD778dPZvR6B8I
                    @Override // com.android.bluetooth.avrcp.BrowsedPlayerWrapper.BrowseCallback
                    public final void run(int i2, String str, List list) {
                        this.f$0.lambda$handleMessage$0$BrowsablePlayerConnector$1(wrapper2, i2, str, list);
                    }
                });
            } else if (i == 2) {
                Log.v(BrowsablePlayerConnector.TAG, "Timed out waiting for players");
                BrowsablePlayerConnector.this.removePendingPlayers();
            }
            if (BrowsablePlayerConnector.this.mPendingPlayers.size() == 0) {
                Log.i(BrowsablePlayerConnector.TAG, "Successfully connected to " + BrowsablePlayerConnector.this.mResults.size() + " browsable players.");
                removeMessages(2);
                BrowsablePlayerConnector.this.mCallback.run(BrowsablePlayerConnector.this.mResults);
            }
        }

        public /* synthetic */ void lambda$handleMessage$0$BrowsablePlayerConnector$1(BrowsedPlayerWrapper wrapper, int status, String mediaId, List results) {
            if (status != 0) {
                BrowsablePlayerConnector.this.mPendingPlayers.remove(wrapper);
            } else {
                if (results.size() == 0) {
                    BrowsablePlayerConnector.this.mPendingPlayers.remove(wrapper);
                    return;
                }
                Message success = BrowsablePlayerConnector.this.mHandler.obtainMessage(0);
                success.obj = wrapper;
                BrowsablePlayerConnector.this.mHandler.sendMessage(success);
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void removePendingPlayers() {
        for (BrowsedPlayerWrapper wrapper : this.mPendingPlayers) {
            Log.d(TAG, "Disconnecting " + wrapper.getPackageName());
            wrapper.disconnect();
        }
        this.mPendingPlayers.clear();
    }

    void cleanup() {
        if (this.mPendingPlayers.size() != 0) {
            Log.i(TAG, "Bluetooth turn off with " + this.mPendingPlayers.size() + " pending player(s)");
            this.mHandler.removeMessages(2);
            removePendingPlayers();
            this.mHandler = null;
        }
    }
}
