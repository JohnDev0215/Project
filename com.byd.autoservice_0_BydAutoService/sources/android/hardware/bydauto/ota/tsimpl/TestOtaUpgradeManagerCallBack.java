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
    public static final String TAG = "TS.TestOtaUpgradeManagerCallBack";
    private Handler mHandler;
    private IUpdateListener mUpdateListener;

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

    private void enqueueMessage(int i3, int i4, int i5, Object obj) {
        StringBuilder sb = new StringBuilder();
        sb.append("enqueueMessage:");
        sb.append(i3);
        sb.append("/");
        sb.append(i4);
        sb.append("/");
        sb.append(i5);
        sb.append("/");
        sb.append(obj);
        sb.append("/");
        sb.append(this.mHandler == null);
        Log.d(TAG, sb.toString());
        if (this.mHandler != null) {
            Message messageObtain = Message.obtain();
            messageObtain.what = i3;
            messageObtain.arg1 = i4;
            messageObtain.arg2 = i5;
            messageObtain.obj = obj;
            this.mHandler.sendMessage(messageObtain);
        }
    }

    public void dispatchMessageCallBack(Message message) {
        StringBuilder sb = new StringBuilder();
        sb.append("dispatchMessageCallBack:");
        sb.append(this.mUpdateListener == null);
        Log.d(TAG, sb.toString());
        IUpdateListener iUpdateListener = this.mUpdateListener;
        if (iUpdateListener != null) {
            int i3 = message.what;
            Object obj = message.obj;
            switch (i3) {
                case 1073741854:
                    try {
                        iUpdateListener.onUpdateSuccess();
                    } catch (RemoteException e3) {
                        e3.printStackTrace();
                    }
                    break;
                case ONUPDATEPROGRESS /* 1073741855 */:
                    try {
                        iUpdateListener.onUpdateProgress(((Integer) obj).intValue());
                    } catch (RemoteException e4) {
                        e4.printStackTrace();
                        return;
                    }
                    break;
                case ONUPDATEFAIL /* 1073741856 */:
                    try {
                        iUpdateListener.onUpdateFail(((Integer) obj).intValue());
                    } catch (RemoteException e5) {
                        e5.printStackTrace();
                        return;
                    }
                    break;
                case ONDOWNLOADBACKUPSUCCESS /* 1073741857 */:
                    try {
                        iUpdateListener.onDownloadBackupSuccess();
                    } catch (RemoteException e6) {
                        e6.printStackTrace();
                        return;
                    }
                    break;
                case ONDOWNLOADBACKUPPROGRESS /* 1073741858 */:
                    try {
                        iUpdateListener.onDownloadBackupProgress(((Integer) obj).intValue());
                    } catch (RemoteException e7) {
                        e7.printStackTrace();
                        return;
                    }
                    break;
                case ONDOWNLOADBACKUPFAIL /* 1073741859 */:
                    try {
                        iUpdateListener.onDownloadBackupFail(((Integer) obj).intValue());
                    } catch (RemoteException e8) {
                        e8.printStackTrace();
                        return;
                    }
                    break;
                case ONTBOXUPDATERESULT /* 1073741860 */:
                    try {
                        iUpdateListener.onTboxUpdateResult(((Integer) obj).intValue());
                    } catch (RemoteException e9) {
                        e9.printStackTrace();
                        return;
                    }
                    break;
                case ONTBOXCONNECTSTATE /* 1073741861 */:
                    try {
                        iUpdateListener.onTboxConnectState(((Integer) obj).intValue());
                    } catch (RemoteException e10) {
                        e10.printStackTrace();
                        return;
                    }
                    break;
                case ONTBOXUPDATEPROGRESS /* 1073741862 */:
                    try {
                        iUpdateListener.onTboxUpdateProgress(((Integer) obj).intValue());
                    } catch (RemoteException e11) {
                        e11.printStackTrace();
                        return;
                    }
                    break;
            }
        }
    }

    public void setUpdateListener(IUpdateListener iUpdateListener) {
        Log.e(TAG, "setAbsBYDAutoOtaListener:" + iUpdateListener);
        this.mUpdateListener = iUpdateListener;
    }

    public void enqueueMessage(int i3, Object obj) {
        enqueueMessage(i3, -1, -1, obj);
    }

    public void enqueueMessage(int i3, int i4, Object obj) {
        enqueueMessage(i3, i4, -1, obj);
    }
}
