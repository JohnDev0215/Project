package com.byd.vrassistant.conversation;

import android.content.ComponentName;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.ServiceConnection;
import android.os.IBinder;
import com.byd.vrassistant.BydVoiceApplication;
import com.byd.vrassistant.receiver.ZoomCarReceiver;
import com.byd.vrassistant.utils.ForegroundAppUtil;
import com.byd.vrassistant.utils.RLog;
import com.byd.zoomcar.ipc.IZoomAidlInterface;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

/* JADX INFO: loaded from: classes.dex */
public class ZoomCarHelper {
    private static ZoomCarHelper instance = null;
    public static final String packageName = "com.byd.zoomcar";
    private ZoomCarReceiver mReceiver;
    private IZoomAidlInterface mService;
    private String TAG = getClass().getSimpleName();
    private boolean serviceConnect = false;
    private String CLS = "com.byd.zoomcar.ipc.RemoteZoomService";
    CountDownLatch countDownLatch = null;
    ServiceConnection connection = new ServiceConnection() { // from class: com.byd.vrassistant.conversation.ZoomCarHelper.1
        @Override // android.content.ServiceConnection
        public void onServiceConnected(ComponentName componentName, IBinder iBinder) {
            RLog.i(ZoomCarHelper.this.TAG, "Zoom onServiceConnected");
            ZoomCarHelper.this.mService = IZoomAidlInterface.Stub.asInterface(iBinder);
            ZoomCarHelper.this.serviceConnect = true;
            if (ZoomCarHelper.this.countDownLatch != null) {
                ZoomCarHelper.this.countDownLatch.countDown();
                ZoomCarHelper.this.countDownLatch = null;
            }
        }

        @Override // android.content.ServiceConnection
        public void onServiceDisconnected(ComponentName componentName) {
            RLog.i(ZoomCarHelper.this.TAG, "Zoom onServiceDisconnected");
            ZoomCarHelper.this.mService = null;
            ZoomCarHelper.this.serviceConnect = false;
            if (ZoomCarHelper.this.countDownLatch != null) {
                ZoomCarHelper.this.countDownLatch.countDown();
                ZoomCarHelper.this.countDownLatch = null;
            }
        }
    };

    public boolean isServiceConnect() {
        RLog.i(this.TAG, "isServiceConnect:" + this.serviceConnect);
        return this.serviceConnect;
    }

    public void setServiceConnect(boolean z) {
        this.serviceConnect = z;
    }

    public static ZoomCarHelper getInstance() {
        if (instance == null) {
            synchronized (ZoomCarHelper.class) {
                if (instance == null) {
                    instance = new ZoomCarHelper();
                }
            }
        }
        return instance;
    }

    public void bindAidlServer() {
        if (this.serviceConnect) {
            RLog.i(this.TAG, "bindAidlServer service is connect");
            return;
        }
        if (this.countDownLatch != null) {
            RLog.i(this.TAG, "bindAidlServer countDownLatch != null");
            return;
        }
        boolean zIsAppInstalled = ForegroundAppUtil.isAppInstalled(BydVoiceApplication.getContext(), packageName);
        boolean zIsStartedApp = ForegroundAppUtil.isStartedApp(packageName);
        RLog.i(this.TAG, "isZoomInstall：" + zIsAppInstalled + ",isZoomStarted:" + zIsStartedApp);
        if (zIsAppInstalled && zIsStartedApp) {
            this.countDownLatch = new CountDownLatch(1);
            RLog.i(this.TAG, "bindAidlServer");
            Intent intent = new Intent();
            intent.setComponent(new ComponentName(packageName, this.CLS));
            BydVoiceApplication.getContext().bindService(intent, this.connection, 1);
        }
    }

    public void unBindAidlServer() {
        RLog.i(this.TAG, "ZoomCarHelper unBindAidlServer");
        this.serviceConnect = false;
        BydVoiceApplication.getContext().unbindService(this.connection);
    }

    public int getMeetingStatus() {
        RLog.i(this.TAG, "ZoomCarHelper meetingStaus start：");
        if (this.serviceConnect) {
            try {
                int zoomMeetingStatus = this.mService.getZoomMeetingStatus();
                RLog.i(this.TAG, "ZoomCarHelper meetingStaus ：" + zoomMeetingStatus);
                return zoomMeetingStatus;
            } catch (Exception e) {
                RLog.i(this.TAG, "ZoomCarHelper meetingStaus ex：" + e);
                return -1;
            }
        }
        RLog.i(this.TAG, "ZoomCarHelper meetingStaus servie is not connect");
        return -1;
    }

    public void registerZoomReceiver() {
        RLog.i(this.TAG, "ZoomCarHelper registerZoomReceiver");
        this.mReceiver = new ZoomCarReceiver();
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction(ZoomCarReceiver.ZOOM_MEETING_ACTION);
        BydVoiceApplication.getContext().registerReceiver(this.mReceiver, intentFilter);
    }

    public void unRegisterZoomReceiver() {
        RLog.i(this.TAG, "ZoomCarHelper unRegisterZoomReceiver");
        BydVoiceApplication.getContext().unregisterReceiver(this.mReceiver);
    }

    public void waitServiceRes() {
        RLog.i(this.TAG, "waitServiceRes");
        CountDownLatch countDownLatch = this.countDownLatch;
        if (countDownLatch != null && countDownLatch.getCount() == 1) {
            try {
                this.countDownLatch.await(2L, TimeUnit.SECONDS);
                RLog.i(this.TAG, " countDownLatch.await");
            } catch (InterruptedException e) {
                RLog.i(this.TAG, new StringBuilder("getHUForbidStatus Zoom Exce:").append(e).toString() != null ? e.getMessage() : "null");
            }
        }
        this.countDownLatch = null;
    }
}
