package android.hardware.bydauto.ota.tsimpl;

import android.content.Context;
import android.hardware.bydauto.ota.IUpdateListener;
import android.os.Bundle;
import android.util.Log;
import com.ts.ota.otasdkmgr.IUpdateListener;

/* JADX INFO: loaded from: classes.dex */
public class OtaUpgradeManager {
    private static final String TAG = "OtaUpgradeManager";
    private static OtaUpgradeManager sOtaUpgradeMananger;
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

        public void onUpdateProgress(int i) {
            if (OtaUpgradeManager.this.updateListener != null) {
                OtaUpgradeManager.this.updateListener.onUpdateProgress(i);
            } else {
                Log.e(OtaUpgradeManager.TAG, "onUpdateProgress: updateListener is null");
            }
        }

        public void onUpdateFail(int i) {
            if (OtaUpgradeManager.this.updateListener != null) {
                OtaUpgradeManager.this.updateListener.onUpdateFail(i);
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

        public void onDownloadBackupProgress(int i) {
            if (OtaUpgradeManager.this.updateListener != null) {
                OtaUpgradeManager.this.updateListener.onDownloadBackupProgress(i);
            } else {
                Log.e(OtaUpgradeManager.TAG, "onDownloadBackupProgress: updateListener is null");
            }
        }

        public void onDownloadBackupFail(int i) {
            if (OtaUpgradeManager.this.updateListener != null) {
                OtaUpgradeManager.this.updateListener.onDownloadBackupFail(i);
            } else {
                Log.e(OtaUpgradeManager.TAG, "onDownloadBackupFail: updateListener is null");
            }
        }

        public void onTboxUpdateResult(int i) {
            if (OtaUpgradeManager.this.updateListener != null) {
                OtaUpgradeManager.this.updateListener.onTboxUpdateResult(i);
            } else {
                Log.e(OtaUpgradeManager.TAG, "onTboxUpdateResult: updateListener is null");
            }
        }

        public void onTboxUpdateProgress(int i) {
            if (OtaUpgradeManager.this.updateListener != null) {
                OtaUpgradeManager.this.updateListener.onTboxUpdateProgress(i);
            } else {
                Log.e(OtaUpgradeManager.TAG, "onTboxUpdateProgress: updateListener is null");
            }
        }

        public void onTboxConnectState(int i) {
            if (OtaUpgradeManager.this.updateListener != null) {
                OtaUpgradeManager.this.updateListener.onTBOXConnectState(i);
            } else {
                Log.e(OtaUpgradeManager.TAG, "onTboxConnectState: updateListener is null");
            }
        }

        public void onTboxExportLogProgress(int i) {
            if (OtaUpgradeManager.this.updateListener != null) {
                OtaUpgradeManager.this.updateListener.onTboxExportLogProgress(i);
            } else {
                Log.e(OtaUpgradeManager.TAG, "onTboxExportLogProgress: updateListener is null");
            }
        }

        public void onTboxExportLogResult(int i) {
            if (OtaUpgradeManager.this.updateListener != null) {
                OtaUpgradeManager.this.updateListener.onTboxExportLogResult(i);
            } else {
                Log.e(OtaUpgradeManager.TAG, "onTboxExportLogResult: updateListener is null");
            }
        }

        public void onNotify(Bundle bundle) {
            if (OtaUpgradeManager.this.updateListener != null) {
                OtaUpgradeManager.this.updateListener.onNotify(bundle);
            } else {
                Log.e(OtaUpgradeManager.TAG, "onNotify: updateListener is null");
            }
        }
    };

    OtaUpgradeManager(Context context) {
        this.mManagerImpl = null;
        this.mManagerImpl = OtaManagerImpl.getInstance(context);
    }

    public void trigerTestOtaUpgradeManagerCallBack(int i, Object obj) {
        this.mManagerImpl.trigerTestOtaUpgradeManagerCallBack(i, obj);
    }

    public static synchronized OtaUpgradeManager getInstance(Context context) {
        if (sOtaUpgradeMananger == null) {
            sOtaUpgradeMananger = new OtaUpgradeManager(context);
        }
        return sOtaUpgradeMananger;
    }

    public void registerListener(android.hardware.bydauto.ota.IUpdateListener iUpdateListener) {
        this.updateListener = iUpdateListener;
        this.mManagerImpl.registerCallback(this.otaUpgradeListener);
    }

    public void unregisterCallback(android.hardware.bydauto.ota.IUpdateListener iUpdateListener) {
        this.updateListener = null;
        this.mManagerImpl.unregisterCallback(this.otaUpgradeListener);
    }

    public void startUpdate(String str) {
        this.mManagerImpl.startUpdate(str);
    }

    public void startDownloadBackup(String str) {
        this.mManagerImpl.startDownloadBackup(str);
    }

    public void startUpdateTbox(String str) {
        this.mManagerImpl.startUpdateTbox(str);
    }

    public boolean decodeAndCheckSign(Bundle bundle) {
        return this.mManagerImpl.decodeAndCheckSign(bundle);
    }

    public boolean executeCMD(Bundle bundle) {
        return this.mManagerImpl.executeCMD(bundle);
    }
}
