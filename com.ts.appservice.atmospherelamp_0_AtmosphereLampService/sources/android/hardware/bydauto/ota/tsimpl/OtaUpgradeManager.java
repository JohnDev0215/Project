package android.hardware.bydauto.ota.tsimpl;

import android.content.Context;
import android.hardware.bydauto.ota.IUpdateListener;
import android.os.Bundle;
import android.util.Log;
import com.ts.ota.otasdkmgr.IUpdateListener;

/* JADX INFO: loaded from: classes.dex */
public class OtaUpgradeManager {
    private static final String TAG = "OtaUpgradeManager";
    private static OtaUpgradeManager sOtaUpgradeMananger = null;
    private OtaManagerImpl mManagerImpl;
    private IUpdateListener updateListener = null;
    IUpdateListener.Stub otaUpgradeListener = new IUpdateListener.Stub() { // from class: android.hardware.bydauto.ota.tsimpl.OtaUpgradeManager.1
        public void onUpdateSuccess() {
            if (OtaUpgradeManager.this.updateListener != null) {
                OtaUpgradeManager.this.updateListener.onUpdateSuccess();
            } else {
                Log.e(OtaUpgradeManager.TAG, "onUpdateSuccess: updateListener is null");
            }
        }

        public void onUpdateProgress(int progress) {
            if (OtaUpgradeManager.this.updateListener != null) {
                OtaUpgradeManager.this.updateListener.onUpdateProgress(progress);
            } else {
                Log.e(OtaUpgradeManager.TAG, "onUpdateProgress: updateListener is null");
            }
        }

        public void onUpdateFail(int normalCode) {
            if (OtaUpgradeManager.this.updateListener != null) {
                OtaUpgradeManager.this.updateListener.onUpdateFail(normalCode);
            } else {
                Log.e(OtaUpgradeManager.TAG, "onUpdateFail: updateListener is null");
            }
        }

        public void onDownloadBackupSuccess() {
            if (OtaUpgradeManager.this.updateListener != null) {
                OtaUpgradeManager.this.updateListener.onDownloadBackupSuccess();
            } else {
                Log.e(OtaUpgradeManager.TAG, "onDownloadBackupSuccess: updateListener is null");
            }
        }

        public void onDownloadBackupProgress(int progress) {
            if (OtaUpgradeManager.this.updateListener != null) {
                OtaUpgradeManager.this.updateListener.onDownloadBackupProgress(progress);
            } else {
                Log.e(OtaUpgradeManager.TAG, "onDownloadBackupProgress: updateListener is null");
            }
        }

        public void onDownloadBackupFail(int errorCode) {
            if (OtaUpgradeManager.this.updateListener != null) {
                OtaUpgradeManager.this.updateListener.onDownloadBackupFail(errorCode);
            } else {
                Log.e(OtaUpgradeManager.TAG, "onDownloadBackupFail: updateListener is null");
            }
        }

        public void onTboxUpdateResult(int result) {
            if (OtaUpgradeManager.this.updateListener != null) {
                OtaUpgradeManager.this.updateListener.onTboxUpdateResult(result);
            } else {
                Log.e(OtaUpgradeManager.TAG, "onTboxUpdateResult: updateListener is null");
            }
        }

        public void onTboxUpdateProgress(int progress) {
            if (OtaUpgradeManager.this.updateListener != null) {
                OtaUpgradeManager.this.updateListener.onTboxUpdateProgress(progress);
            } else {
                Log.e(OtaUpgradeManager.TAG, "onTboxUpdateProgress: updateListener is null");
            }
        }

        public void onTboxConnectState(int tboxState) {
            if (OtaUpgradeManager.this.updateListener != null) {
                OtaUpgradeManager.this.updateListener.onTBOXConnectState(tboxState);
            } else {
                Log.e(OtaUpgradeManager.TAG, "onTboxConnectState: updateListener is null");
            }
        }

        public void onTboxExportLogProgress(int progress) {
            if (OtaUpgradeManager.this.updateListener != null) {
                OtaUpgradeManager.this.updateListener.onTboxExportLogProgress(progress);
            } else {
                Log.e(OtaUpgradeManager.TAG, "onTboxExportLogProgress: updateListener is null");
            }
        }

        public void onTboxExportLogResult(int result) {
            if (OtaUpgradeManager.this.updateListener != null) {
                OtaUpgradeManager.this.updateListener.onTboxExportLogResult(result);
            } else {
                Log.e(OtaUpgradeManager.TAG, "onTboxExportLogResult: updateListener is null");
            }
        }

        public void onNotify(Bundle notify) {
            if (OtaUpgradeManager.this.updateListener != null) {
                OtaUpgradeManager.this.updateListener.onNotify(notify);
            } else {
                Log.e(OtaUpgradeManager.TAG, "onNotify: updateListener is null");
            }
        }
    };

    OtaUpgradeManager(Context con) {
        this.mManagerImpl = null;
        this.mManagerImpl = OtaManagerImpl.getInstance(con);
    }

    public void trigerTestOtaUpgradeManagerCallBack(int what, Object para1object) {
        this.mManagerImpl.trigerTestOtaUpgradeManagerCallBack(what, para1object);
    }

    public static synchronized OtaUpgradeManager getInstance(Context con) {
        if (sOtaUpgradeMananger == null) {
            sOtaUpgradeMananger = new OtaUpgradeManager(con);
        }
        return sOtaUpgradeMananger;
    }

    public void registerListener(android.hardware.bydauto.ota.IUpdateListener listener) {
        this.updateListener = listener;
        this.mManagerImpl.registerCallback(this.otaUpgradeListener);
    }

    public void unregisterCallback(android.hardware.bydauto.ota.IUpdateListener listener) {
        this.updateListener = null;
        this.mManagerImpl.unregisterCallback(this.otaUpgradeListener);
    }

    public void startUpdate(String path) {
        this.mManagerImpl.startUpdate(path);
    }

    public void startDownloadBackup(String path) {
        this.mManagerImpl.startDownloadBackup(path);
    }

    public void startUpdateTbox(String path) {
        this.mManagerImpl.startUpdateTbox(path);
    }

    public boolean decodeAndCheckSign(Bundle bundle) {
        return this.mManagerImpl.decodeAndCheckSign(bundle);
    }

    public boolean executeCMD(Bundle cmd) {
        return this.mManagerImpl.executeCMD(cmd);
    }
}
