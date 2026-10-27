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
    public IUpdateListener.Stub otaUpgradeListener = new IUpdateListener.Stub() { // from class: android.hardware.bydauto.ota.tsimpl.OtaUpgradeManager.1
        public void onDownloadBackupFail(int i8) {
            if (OtaUpgradeManager.this.updateListener == null) {
                Log.e(OtaUpgradeManager.TAG, "onDownloadBackupFail: updateListener is null");
            } else {
                OtaUpgradeManager.this.updateListener.onDownloadBackupFail(i8);
            }
        }

        public void onDownloadBackupProgress(int i8) {
            if (OtaUpgradeManager.this.updateListener == null) {
                Log.e(OtaUpgradeManager.TAG, "onDownloadBackupProgress: updateListener is null");
            } else {
                OtaUpgradeManager.this.updateListener.onDownloadBackupProgress(i8);
            }
        }

        public void onDownloadBackupSuccess() {
            if (OtaUpgradeManager.this.updateListener == null) {
                Log.e(OtaUpgradeManager.TAG, "onDownloadBackupSuccess: updateListener is null");
            } else {
                OtaUpgradeManager.this.updateListener.onDownloadBackupSuccess();
            }
        }

        public void onNotify(Bundle bundle) {
            if (OtaUpgradeManager.this.updateListener == null) {
                Log.e(OtaUpgradeManager.TAG, "onNotify: updateListener is null");
            } else {
                OtaUpgradeManager.this.updateListener.onNotify(bundle);
            }
        }

        public void onTboxConnectState(int i8) {
            if (OtaUpgradeManager.this.updateListener == null) {
                Log.e(OtaUpgradeManager.TAG, "onTboxConnectState: updateListener is null");
            } else {
                OtaUpgradeManager.this.updateListener.onTBOXConnectState(i8);
            }
        }

        public void onTboxExportLogProgress(int i8) {
            if (OtaUpgradeManager.this.updateListener == null) {
                Log.e(OtaUpgradeManager.TAG, "onTboxExportLogProgress: updateListener is null");
            } else {
                OtaUpgradeManager.this.updateListener.onTboxExportLogProgress(i8);
            }
        }

        public void onTboxExportLogResult(int i8) {
            if (OtaUpgradeManager.this.updateListener == null) {
                Log.e(OtaUpgradeManager.TAG, "onTboxExportLogResult: updateListener is null");
            } else {
                OtaUpgradeManager.this.updateListener.onTboxExportLogResult(i8);
            }
        }

        public void onTboxUpdateProgress(int i8) {
            if (OtaUpgradeManager.this.updateListener == null) {
                Log.e(OtaUpgradeManager.TAG, "onTboxUpdateProgress: updateListener is null");
            } else {
                OtaUpgradeManager.this.updateListener.onTboxUpdateProgress(i8);
            }
        }

        public void onTboxUpdateResult(int i8) {
            if (OtaUpgradeManager.this.updateListener == null) {
                Log.e(OtaUpgradeManager.TAG, "onTboxUpdateResult: updateListener is null");
            } else {
                OtaUpgradeManager.this.updateListener.onTboxUpdateResult(i8);
            }
        }

        public void onUpdateFail(int i8) {
            if (OtaUpgradeManager.this.updateListener == null) {
                Log.e(OtaUpgradeManager.TAG, "onUpdateFail: updateListener is null");
            } else {
                OtaUpgradeManager.this.updateListener.onUpdateFail(i8);
            }
        }

        public void onUpdateProgress(int i8) {
            if (OtaUpgradeManager.this.updateListener == null) {
                Log.e(OtaUpgradeManager.TAG, "onUpdateProgress: updateListener is null");
            } else {
                OtaUpgradeManager.this.updateListener.onUpdateProgress(i8);
            }
        }

        public void onUpdateSuccess() {
            if (OtaUpgradeManager.this.updateListener == null) {
                Log.e(OtaUpgradeManager.TAG, "onUpdateSuccess: updateListener is null");
            } else {
                OtaUpgradeManager.this.updateListener.onUpdateSuccess();
            }
        }
    };

    public OtaUpgradeManager(Context context) {
        this.mManagerImpl = null;
        this.mManagerImpl = OtaManagerImpl.getInstance(context);
    }

    public static synchronized OtaUpgradeManager getInstance(Context context) {
        if (sOtaUpgradeMananger == null) {
            sOtaUpgradeMananger = new OtaUpgradeManager(context);
        }
        return sOtaUpgradeMananger;
    }

    public boolean decodeAndCheckSign(Bundle bundle) {
        return this.mManagerImpl.decodeAndCheckSign(bundle);
    }

    public boolean executeCMD(Bundle bundle) {
        return this.mManagerImpl.executeCMD(bundle);
    }

    public void registerListener(android.hardware.bydauto.ota.IUpdateListener iUpdateListener) {
        this.updateListener = iUpdateListener;
        this.mManagerImpl.registerCallback(this.otaUpgradeListener);
    }

    public void startDownloadBackup(String str) {
        this.mManagerImpl.startDownloadBackup(str);
    }

    public void startUpdate(String str) {
        this.mManagerImpl.startUpdate(str);
    }

    public void startUpdateTbox(String str) {
        this.mManagerImpl.startUpdateTbox(str);
    }

    public void trigerTestOtaUpgradeManagerCallBack(int i8, Object obj) {
        this.mManagerImpl.trigerTestOtaUpgradeManagerCallBack(i8, obj);
    }

    public void unregisterCallback(android.hardware.bydauto.ota.IUpdateListener iUpdateListener) {
        this.updateListener = null;
        this.mManagerImpl.unregisterCallback(this.otaUpgradeListener);
    }
}
