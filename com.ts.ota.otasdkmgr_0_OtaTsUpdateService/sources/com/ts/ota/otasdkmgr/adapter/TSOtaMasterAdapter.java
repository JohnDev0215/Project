package com.ts.ota.otasdkmgr.adapter;

import android.content.Context;
import android.os.Bundle;
import android.os.RemoteException;
import android.util.Log;
import com.byd.ota.master.OtaMasterController;
import com.byd.ota.master.interfaces.IAutoOtaDeviceManager;
import com.byd.ota.master.interfaces.IBaseUpdateManager;
import com.byd.ota.master.interfaces.ITBOXUpdateManager;
import com.byd.ota.master.interfaces.IUpdateListener;
import com.ts.ota.otasdkmgr.IOtaTsUpdateServiceProxy;
import com.ts.ota.otasdkmgr.callback.IUpdateListenerCallback;
import com.ts.ota.otasdkmgr.manager.BaseUpdateManager;
import com.ts.ota.otasdkmgr.manager.OtaAutoOtaDeviceManager;
import com.ts.ota.otasdkmgr.manager.OtaTboxUpdateMananger;

/* JADX INFO: loaded from: classes.dex */
public class TSOtaMasterAdapter {
    private static final String TAG = "dota.TSOtaMasterAdapter";
    private Context mContext;
    private OtaMasterController mOtaMasterController = null;
    private ITBOXUpdateManager mTboxUpdateManager = null;
    private IBaseUpdateManager mBaseUpdateManager = null;
    private IAutoOtaDeviceManager mAutoOtaDeviceManager = null;
    private TSAdapterUpdateListener mTSAdapterUpdateListener = new TSAdapterUpdateListener();
    private IUpdateListenerCallback listener = IUpdateListenerCallback.getInstance();

    public TSOtaMasterAdapter(Context context) {
        this.mContext = context;
    }

    public void init(IOtaTsUpdateServiceProxy proxy) {
        Log.w(TAG, "init");
        initManager(proxy);
        initController();
        initListener();
    }

    private void initManager(IOtaTsUpdateServiceProxy proxy) {
        Log.w(TAG, "initManager");
        if (this.mTboxUpdateManager == null) {
            this.mTboxUpdateManager = OtaTboxUpdateMananger.getInstance(this.mContext, proxy);
        }
        if (this.mBaseUpdateManager == null) {
            this.mBaseUpdateManager = new BaseUpdateManager();
        }
        if (this.mAutoOtaDeviceManager == null) {
            this.mAutoOtaDeviceManager = new OtaAutoOtaDeviceManager(proxy);
        }
    }

    private void initController() {
        Log.w(TAG, "initController");
        if (this.mOtaMasterController == null) {
            this.mOtaMasterController = new OtaMasterController(this.mContext, this.mTboxUpdateManager, this.mBaseUpdateManager, this.mAutoOtaDeviceManager);
        }
    }

    private void initListener() {
        Log.w(TAG, "initListener");
        OtaMasterController otaMasterController = this.mOtaMasterController;
        if (otaMasterController != null) {
            otaMasterController.registerCallback(this.mTSAdapterUpdateListener);
        }
    }

    public void releaseAdapter() {
        Log.w(TAG, "releaseAdapter");
        OtaMasterController otaMasterController = this.mOtaMasterController;
        if (otaMasterController != null) {
            otaMasterController.unregisterCallback(this.mTSAdapterUpdateListener);
        }
    }

    public void startUpdate(String path) throws RemoteException {
        Log.w(TAG, " startUpdate.in");
        OtaMasterController otaMasterController = this.mOtaMasterController;
        if (otaMasterController != null) {
            otaMasterController.startUpdate(path);
        }
        Log.w(TAG, " startUpdate.out");
    }

    public void startDownloadBackup(String path) throws RemoteException {
        Log.w(TAG, " startDownloadBackup.in");
        OtaMasterController otaMasterController = this.mOtaMasterController;
        if (otaMasterController != null) {
            otaMasterController.startDownloadBackup(path);
        }
        Log.w(TAG, " startDownloadBackup.out");
    }

    public void startUpdateTbox(String path) throws RemoteException {
        Log.w(TAG, " startUpdateTbox.in");
        OtaMasterController otaMasterController = this.mOtaMasterController;
        if (otaMasterController != null) {
            otaMasterController.startUpdateTbox(path);
        }
        Log.w(TAG, " startUpdateTbox.out");
    }

    public boolean decodeAndCheckSign(Bundle bundle) {
        boolean result = false;
        Log.w(TAG, "decodeAndCheckSign.in");
        OtaMasterController otaMasterController = this.mOtaMasterController;
        if (otaMasterController != null) {
            result = otaMasterController.decodeAndCheckSign(bundle);
        }
        Log.w(TAG, "decodeAndCheckSign.out");
        return result;
    }

    public boolean executeCMD(Bundle cmd) {
        boolean result = false;
        Log.w(TAG, "executeCMD.in");
        OtaMasterController otaMasterController = this.mOtaMasterController;
        if (otaMasterController != null) {
            result = otaMasterController.executeCMD(cmd);
        }
        Log.w(TAG, "executeCMD.out");
        return result;
    }

    private class TSAdapterUpdateListener implements IUpdateListener {
        private TSAdapterUpdateListener() {
        }

        public void onUpdateSuccess() {
            Log.w(TSOtaMasterAdapter.TAG, "IUpdateListener onUpdateSuccess");
            TSOtaMasterAdapter.this.listener.onUpdateSuccess();
        }

        public void onUpdateProgress(int i) {
            Log.w(TSOtaMasterAdapter.TAG, "IUpdateListener onUpdateProgress param = " + i);
            TSOtaMasterAdapter.this.listener.onUpdateProgress(i);
        }

        public void onUpdateFail(int i) {
            Log.w(TSOtaMasterAdapter.TAG, "IUpdateListener onUpdateFail param = " + i);
            TSOtaMasterAdapter.this.listener.onUpdateFail(i);
        }

        public void onDownloadBackupSuccess() {
            Log.w(TSOtaMasterAdapter.TAG, "IUpdateListener onDownloadBackupSuccess");
            TSOtaMasterAdapter.this.listener.onDownloadBackupSuccess();
        }

        public void onDownloadBackupProgress(int i) {
            Log.w(TSOtaMasterAdapter.TAG, "IUpdateListener onDownloadBackupProgress param = " + i);
            TSOtaMasterAdapter.this.listener.onDownloadBackupProgress(i);
        }

        public void onDownloadBackupFail(int i) {
            Log.w(TSOtaMasterAdapter.TAG, "IUpdateListener onDownloadBackupFail param = " + i);
            TSOtaMasterAdapter.this.listener.onDownloadBackupFail(i);
        }

        public void onTboxUpdateResult(int i) {
            Log.w(TSOtaMasterAdapter.TAG, "IUpdateListener onTboxUpdateResult  param = " + i);
            TSOtaMasterAdapter.this.listener.onTboxUpdateResult(i);
        }

        public void onTboxUpdateProgress(int i) {
            Log.w(TSOtaMasterAdapter.TAG, "IUpdateListener onTboxUpdateProgress  param = " + i);
            TSOtaMasterAdapter.this.listener.onTboxUpdateProgress(i);
        }

        public void onTboxConnectState(int i) {
            Log.w(TSOtaMasterAdapter.TAG, "IUpdateListener onTboxConnectState param = " + i);
            TSOtaMasterAdapter.this.listener.onTboxConnectState(i);
        }

        public void onTboxExportLogProgress(int progress) {
            Log.w(TSOtaMasterAdapter.TAG, "IUpdateListener onTboxExportLogProgress param = " + progress);
            TSOtaMasterAdapter.this.listener.onTboxExportLogProgress(progress);
        }

        public void onTboxExportLogResult(int result) {
            Log.w(TSOtaMasterAdapter.TAG, "IUpdateListener onTboxExportLogResult param = " + result);
            TSOtaMasterAdapter.this.listener.onTboxExportLogResult(result);
        }

        public void onNotify(Bundle notify) {
            Log.w(TSOtaMasterAdapter.TAG, "IUpdateListener onNotify notify = " + notify);
            TSOtaMasterAdapter.this.listener.onNotify(notify);
        }
    }
}
