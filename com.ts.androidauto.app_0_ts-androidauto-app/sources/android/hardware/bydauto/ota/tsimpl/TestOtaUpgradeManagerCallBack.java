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

    public void setUpdateListener(IUpdateListener listener) {
        Log.e(TAG, "setAbsBYDAutoOtaListener:" + listener);
        this.mUpdateListener = listener;
    }

    public TestOtaUpgradeManagerCallBack() {
        Log.d(TAG, "TestOtaUpgradeManagerCallBack:");
        HandlerThread handlerThread = new HandlerThread("TestOtaUpgradeManagerCallBack");
        handlerThread.start();
        this.mHandler = new Handler(handlerThread.getLooper()) { // from class: android.hardware.bydauto.ota.tsimpl.TestOtaUpgradeManagerCallBack.1
            @Override // android.os.Handler
            public void handleMessage(Message msg) {
                super.handleMessage(msg);
                TestOtaUpgradeManagerCallBack.this.dispatchMessageCallBack(msg);
            }
        };
    }

    private void enqueueMessage(int what, int arg1, int arg2, Object para1object) {
        StringBuilder sb = new StringBuilder();
        sb.append("enqueueMessage:");
        sb.append(what);
        sb.append("/");
        sb.append(arg1);
        sb.append("/");
        sb.append(arg2);
        sb.append("/");
        sb.append(para1object);
        sb.append("/");
        sb.append(this.mHandler == null);
        Log.d(TAG, sb.toString());
        if (this.mHandler != null) {
            Message message = Message.obtain();
            message.what = what;
            message.arg1 = arg1;
            message.arg2 = arg2;
            message.obj = para1object;
            this.mHandler.sendMessage(message);
        }
    }

    public void enqueueMessage(int what, Object para1object) {
        enqueueMessage(what, -1, -1, para1object);
    }

    public void enqueueMessage(int what, int arg1, Object para1object) {
        enqueueMessage(what, arg1, -1, para1object);
    }

    public void dispatchMessageCallBack(Message msg) {
        StringBuilder sb = new StringBuilder();
        sb.append("dispatchMessageCallBack:");
        sb.append(this.mUpdateListener == null);
        Log.d(TAG, sb.toString());
        if (this.mUpdateListener != null) {
            int what = msg.what;
            Object para1object = msg.obj;
            switch (what) {
                case 1073741854:
                    try {
                        this.mUpdateListener.onUpdateSuccess();
                    } catch (RemoteException e) {
                        e.printStackTrace();
                    }
                    break;
                case ONUPDATEPROGRESS /* 1073741855 */:
                    try {
                        this.mUpdateListener.onUpdateProgress(((Integer) para1object).intValue());
                    } catch (RemoteException e2) {
                        e2.printStackTrace();
                        return;
                    }
                    break;
                case ONUPDATEFAIL /* 1073741856 */:
                    try {
                        this.mUpdateListener.onUpdateFail(((Integer) para1object).intValue());
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
                        this.mUpdateListener.onDownloadBackupProgress(((Integer) para1object).intValue());
                    } catch (RemoteException e5) {
                        e5.printStackTrace();
                        return;
                    }
                    break;
                case ONDOWNLOADBACKUPFAIL /* 1073741859 */:
                    try {
                        this.mUpdateListener.onDownloadBackupFail(((Integer) para1object).intValue());
                    } catch (RemoteException e6) {
                        e6.printStackTrace();
                        return;
                    }
                    break;
                case ONTBOXUPDATERESULT /* 1073741860 */:
                    try {
                        this.mUpdateListener.onTboxUpdateResult(((Integer) para1object).intValue());
                    } catch (RemoteException e7) {
                        e7.printStackTrace();
                        return;
                    }
                    break;
                case ONTBOXCONNECTSTATE /* 1073741861 */:
                    try {
                        this.mUpdateListener.onTboxConnectState(((Integer) para1object).intValue());
                    } catch (RemoteException e8) {
                        e8.printStackTrace();
                        return;
                    }
                    break;
                case ONTBOXUPDATEPROGRESS /* 1073741862 */:
                    try {
                        this.mUpdateListener.onTboxUpdateProgress(((Integer) para1object).intValue());
                    } catch (RemoteException e9) {
                        e9.printStackTrace();
                        return;
                    }
                    break;
            }
        }
    }
}
