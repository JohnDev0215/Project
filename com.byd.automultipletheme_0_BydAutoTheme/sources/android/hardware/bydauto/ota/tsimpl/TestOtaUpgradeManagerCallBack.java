package android.hardware.bydauto.ota.tsimpl;

import android.os.Handler;
import android.os.HandlerThread;
import android.os.Message;
import android.os.RemoteException;
import android.util.Log;
import com.ts.ota.otasdkmgr.IUpdateListener;

/* JADX INFO: loaded from: classes.dex */
public class TestOtaUpgradeManagerCallBack {
    private static final int INT_BASE = 1073741823;
    public static final int ONDOWNLOADBACKUPFAIL = 1073741859;
    public static final int ONDOWNLOADBACKUPPROGRESS = 1073741858;
    public static final int ONDOWNLOADBACKUPSUCCESS = 1073741857;
    public static final int ONTBOXCONNECTSTATE = 1073741861;
    public static final int ONTBOXUPDATEPROGRESS = 1073741862;
    public static final int ONTBOXUPDATERESULT = 1073741860;
    public static final int ONUPDATEFAIL = 1073741856;
    public static final int ONUPDATEPROGRESS = 1073741855;
    public static final int ONUPDATESUCCESS = 1073741854;
    protected static final String TAG = "TS.TestOtaUpgradeManagerCallBack";
    private Handler mHandler;
    private IUpdateListener mUpdateListener;

    public void setUpdateListener(IUpdateListener iUpdateListener) {
        Log.e(TAG, "setAbsBYDAutoOtaListener:" + iUpdateListener);
        this.mUpdateListener = iUpdateListener;
    }

    public TestOtaUpgradeManagerCallBack() {
        Log.d(TAG, "TestOtaUpgradeManagerCallBack:");
        HandlerThread handlerThread = new HandlerThread("TestOtaUpgradeManagerCallBack");
        handlerThread.start();
        this.mHandler = new Handler(handlerThread.getLooper()) { // from class: android.hardware.bydauto.ota.tsimpl.TestOtaUpgradeManagerCallBack.1
            @Override // android.os.Handler
            public void handleMessage(Message message) {
                super.handleMessage(message);
                TestOtaUpgradeManagerCallBack.this.dispatchMessageCallBack(message);
            }
        };
    }

    private void enqueueMessage(int i, int i2, int i3, Object obj) {
        StringBuilder sb = new StringBuilder();
        sb.append("enqueueMessage:");
        sb.append(i);
        sb.append("/");
        sb.append(i2);
        sb.append("/");
        sb.append(i3);
        sb.append("/");
        sb.append(obj);
        sb.append("/");
        sb.append(this.mHandler == null);
        Log.d(TAG, sb.toString());
        if (this.mHandler != null) {
            Message messageObtain = Message.obtain();
            messageObtain.what = i;
            messageObtain.arg1 = i2;
            messageObtain.arg2 = i3;
            messageObtain.obj = obj;
            this.mHandler.sendMessage(messageObtain);
        }
    }

    public void enqueueMessage(int i, Object obj) {
        enqueueMessage(i, -1, -1, obj);
    }

    public void enqueueMessage(int i, int i2, Object obj) {
        enqueueMessage(i, i2, -1, obj);
    }

    public void dispatchMessageCallBack(Message message) {
        StringBuilder sb = new StringBuilder();
        sb.append("dispatchMessageCallBack:");
        sb.append(this.mUpdateListener == null);
        Log.d(TAG, sb.toString());
        if (this.mUpdateListener != null) {
            int i = message.what;
            Object obj = message.obj;
            switch (i) {
                case 1073741854:
                    try {
                        this.mUpdateListener.onUpdateSuccess();
                    } catch (RemoteException e) {
                        e.printStackTrace();
                    }
                    break;
                case ONUPDATEPROGRESS /* 1073741855 */:
                    try {
                        this.mUpdateListener.onUpdateProgress(((Integer) obj).intValue());
                    } catch (RemoteException e2) {
                        e2.printStackTrace();
                        return;
                    }
                    break;
                case ONUPDATEFAIL /* 1073741856 */:
                    try {
                        this.mUpdateListener.onUpdateFail(((Integer) obj).intValue());
                    } catch (RemoteException e3) {
                        e3.printStackTrace();
                        return;
                    }
                    break;
                case ONDOWNLOADBACKUPSUCCESS /* 1073741857 */:
                    try {
                        this.mUpdateListener.onDownloadBackupSuccess();
                    } catch (RemoteException e4) {
                        e4.printStackTrace();
                        return;
                    }
                    break;
                case ONDOWNLOADBACKUPPROGRESS /* 1073741858 */:
                    try {
                        this.mUpdateListener.onDownloadBackupProgress(((Integer) obj).intValue());
                    } catch (RemoteException e5) {
                        e5.printStackTrace();
                        return;
                    }
                    break;
                case ONDOWNLOADBACKUPFAIL /* 1073741859 */:
                    try {
                        this.mUpdateListener.onDownloadBackupFail(((Integer) obj).intValue());
                    } catch (RemoteException e6) {
                        e6.printStackTrace();
                        return;
                    }
                    break;
                case ONTBOXUPDATERESULT /* 1073741860 */:
                    try {
                        this.mUpdateListener.onTboxUpdateResult(((Integer) obj).intValue());
                    } catch (RemoteException e7) {
                        e7.printStackTrace();
                        return;
                    }
                    break;
                case ONTBOXCONNECTSTATE /* 1073741861 */:
                    try {
                        this.mUpdateListener.onTboxConnectState(((Integer) obj).intValue());
                    } catch (RemoteException e8) {
                        e8.printStackTrace();
                        return;
                    }
                    break;
                case ONTBOXUPDATEPROGRESS /* 1073741862 */:
                    try {
                        this.mUpdateListener.onTboxUpdateProgress(((Integer) obj).intValue());
                    } catch (RemoteException e9) {
                        e9.printStackTrace();
                        return;
                    }
                    break;
            }
        }
    }
}
