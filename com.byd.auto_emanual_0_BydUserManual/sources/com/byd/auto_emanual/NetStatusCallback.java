package com.byd.auto_emanual;

import android.net.ConnectivityManager;
import android.net.Network;
import android.net.NetworkCapabilities;
import android.os.Handler;
import android.os.Message;
import com.byd.auto_emanual.fragment.BaseFragment;
import com.byd.auto_emanual.util.MLog;
import com.byd.auto_emanual.util.NetUtil;
import java.lang.ref.WeakReference;
import java.net.ConnectException;

/* JADX INFO: loaded from: classes.dex */
public class NetStatusCallback extends ConnectivityManager.NetworkCallback {
    private static final int MSG_LOAD_DATA = 1;
    private static final String TAG = "NetStatusCallback";
    private static NetStatusCallback instance = new NetStatusCallback();
    public static volatile boolean oldNetAvailable = true;
    private MediaActivity mediaActivity;
    private UIHandler handler = new UIHandler(this);
    private Runnable pingRunnable = $$Lambda$NetStatusCallback$40sLPsGPxchFERKSmW_60kIJFWc.INSTANCE;

    public void pingNet() {
    }

    public static NetStatusCallback getInstance() {
        return instance;
    }

    public static NetStatusCallback getInstanceWithMediaActivity(MediaActivity mediaActivity) {
        NetStatusCallback netStatusCallback = instance;
        netStatusCallback.mediaActivity = mediaActivity;
        return netStatusCallback;
    }

    public static NetStatusCallback getInstanceNoMediaActivity() {
        NetStatusCallback netStatusCallback = instance;
        netStatusCallback.mediaActivity = null;
        return netStatusCallback;
    }

    private NetStatusCallback() {
    }

    public NetStatusCallback(MediaActivity mediaActivity) {
        this.mediaActivity = mediaActivity;
    }

    @Override // android.net.ConnectivityManager.NetworkCallback
    public void onAvailable(Network network) {
        super.onAvailable(network);
    }

    @Override // android.net.ConnectivityManager.NetworkCallback
    public void onCapabilitiesChanged(Network network, NetworkCapabilities networkCapabilities) {
        super.onCapabilitiesChanged(network, networkCapabilities);
        boolean zHasCapability = networkCapabilities.hasCapability(16);
        MLog.d(TAG, "netAvailable = " + zHasCapability + " " + oldNetAvailable);
        if (!zHasCapability && !oldNetAvailable) {
            pingNet();
        }
        if (zHasCapability && !oldNetAvailable) {
            loadDataByNetChange();
        }
        oldNetAvailable = zHasCapability;
    }

    @Override // android.net.ConnectivityManager.NetworkCallback
    public void onLost(Network network) {
        super.onLost(network);
        oldNetAvailable = false;
        MLog.d(TAG, "onLost oldNetAvailable = " + oldNetAvailable);
    }

    public static void onConnectException(String path, Exception e) {
        ExecutorManager.removeTask(path);
        if (e instanceof ConnectException) {
            MLog.i(TAG, " onConnectException ");
            oldNetAvailable = false;
        }
    }

    static class UIHandler extends Handler {
        private WeakReference<NetStatusCallback> netStatusCallback;

        public UIHandler(NetStatusCallback netStatusCallback) {
            this.netStatusCallback = new WeakReference<>(netStatusCallback);
        }

        @Override // android.os.Handler
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            if (msg.what == 1) {
                this.netStatusCallback.get().updateContentForMainActivity();
                MediaActivity mediaActivity = this.netStatusCallback.get().mediaActivity;
                if (mediaActivity == null || !mediaActivity.isReloadDataByNetChanged()) {
                    return;
                }
                mediaActivity.updateContentImpl(false);
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void updateContentForMainActivity() {
        ExecutorManager.clearRecords();
        for (BaseFragment baseFragment : FragmentHolder.getAllFragments()) {
            if (!baseFragment.notLoadWhenNotShowVideo()) {
                MLog.d(TAG, " updateContentForMainActivity " + baseFragment.getClass().getSimpleName() + " " + baseFragment.isAdded() + ", " + baseFragment.isReloadDataByNetChanged());
                if (baseFragment != null && baseFragment.isAdded() && baseFragment.isReloadDataByNetChanged()) {
                    baseFragment.loadData();
                }
            }
        }
    }

    static /* synthetic */ void lambda$new$0() {
        MLog.d(TAG, "pingNet oldNetAvailable = " + oldNetAvailable);
        if (oldNetAvailable) {
            return;
        }
        NetUtil.pingSuccess();
    }

    public void loadDataByNetChange() {
        MLog.i(TAG, "loadDataByNetChange");
        this.handler.removeMessages(1);
        this.handler.sendEmptyMessage(1);
    }
}
