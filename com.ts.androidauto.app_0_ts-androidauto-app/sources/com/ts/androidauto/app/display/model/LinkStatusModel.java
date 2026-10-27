package com.ts.androidauto.app.display.model;

import android.content.Context;
import android.os.RemoteException;
import android.view.Surface;
import com.ts.androidauto.app.common.LogUtil;
import com.ts.androidauto.app.common.VehicleInfoLoader;
import com.ts.car.core.ServiceProvider;
import ts.car.service.IPlatformService;
import ts.car.service.IServiceConnectionListener;
import ts.car.service.androidauto.IAndroidAutoServiceListener;
import ts.car.service.androidauto.IAndroidAutoServiceManager;
import ts.car.service.androidauto.IAndroidAutoSessionStatusListener;
import ts.car.service.androidauto.data.VehicleData;

/* JADX INFO: loaded from: classes2.dex */
public class LinkStatusModel {
    private static final String TAG = "AAP" + LinkStatusModel.class.getSimpleName();
    private IAndroidAutoServiceManager mAndroidAutoServiceManager;
    private Context mContext;
    private int mLastStatus;
    private LinkStatusCallback mLinkStatusCallback;
    private Object mLock = new Object();
    private Object mServiceManagerLock = new Object();
    private boolean mRegisterKey = false;
    private boolean mIsAndroidAutoServiceConnected = false;
    private final IServiceConnectionListener mServiceConnectionListener = new IServiceConnectionListener() { // from class: com.ts.androidauto.app.display.model.LinkStatusModel.1
        public void onServiceConnected(IPlatformService service) {
            synchronized (LinkStatusModel.this.mServiceManagerLock) {
                LogUtil.debug(LinkStatusModel.TAG, "mServiceConnectionListener onServiceConnected.");
                if (LinkStatusModel.this.mAndroidAutoServiceManager == null) {
                    LogUtil.debug(LinkStatusModel.TAG, "mAndroidAutoServiceManager is null.");
                } else {
                    try {
                        LinkStatusModel.this.mAndroidAutoServiceManager.registerAndroidAutoServiceListener(LinkStatusModel.this.mAndroidAutoServiceListener);
                        LinkStatusModel.this.mAndroidAutoServiceManager.registerAndroidAutoSessionStatusListener(LinkStatusModel.this.mAndroidAutoSessionStatusListener);
                    } catch (RemoteException ex) {
                        ex.printStackTrace();
                    }
                    if (LinkStatusModel.this.mLinkStatusCallback != null) {
                        try {
                            LogUtil.debug(LinkStatusModel.TAG, "init SessionStatus" + LinkStatusModel.this.mAndroidAutoServiceManager.getAndroidAutoSessionStatus());
                            if (LinkStatusModel.this.mLinkStatusCallback != null && LinkStatusModel.this.mAndroidAutoServiceManager != null) {
                                LinkStatusModel.this.mLinkStatusCallback.onStatusChanged(LinkStatusModel.this.mAndroidAutoServiceManager.getAndroidAutoSessionStatus(), 0);
                            }
                        } catch (RemoteException ex2) {
                            ex2.printStackTrace();
                        }
                    }
                }
                LinkStatusModel.this.mIsAndroidAutoServiceConnected = true;
            }
        }

        public void onServiceDisconnected(IPlatformService service) {
            LogUtil.debug(LinkStatusModel.TAG, "mServiceConnectionListener onServiceDisconnected.");
            LinkStatusModel.this.mIsAndroidAutoServiceConnected = false;
        }
    };
    private IAndroidAutoServiceListener mAndroidAutoServiceListener = new IAndroidAutoServiceListener() { // from class: com.ts.androidauto.app.display.model.LinkStatusModel.2
        public void onRequestVideoFocus(int requestType) {
            LogUtil.info(LinkStatusModel.TAG, "onRequestVideoFocus requestType:" + requestType);
            if (LinkStatusModel.this.mLinkStatusCallback != null) {
                LinkStatusModel.this.mLinkStatusCallback.onRequestVideoFocus(requestType);
            }
        }
    };
    private IAndroidAutoSessionStatusListener mAndroidAutoSessionStatusListener = new IAndroidAutoSessionStatusListener() { // from class: com.ts.androidauto.app.display.model.LinkStatusModel.3
        public void onSessionStatusChanged(int linkStatus, int errorCode, String message) {
            synchronized (LinkStatusModel.this.mLock) {
                LogUtil.info(LinkStatusModel.TAG, "onStatusChanged mLastStatus:" + LinkStatusModel.this.mLastStatus + "," + linkStatus);
                if (LinkStatusModel.this.mLastStatus == 9 && (linkStatus == 2 || linkStatus == 1 || linkStatus == 4)) {
                    return;
                }
                if (LinkStatusModel.this.mLinkStatusCallback != null) {
                    LinkStatusModel.this.mLinkStatusCallback.onStatusChanged(linkStatus, errorCode);
                }
                LinkStatusModel.this.mLastStatus = linkStatus;
            }
        }
    };

    public LinkStatusModel(Context context) {
        this.mContext = null;
        this.mContext = context;
    }

    public synchronized void init() {
        synchronized (this.mServiceManagerLock) {
            try {
                LogUtil.debug(TAG, "init.");
                IAndroidAutoServiceManager iAndroidAutoServiceManager = (IAndroidAutoServiceManager) ServiceProvider.get(IAndroidAutoServiceManager.class, this.mContext);
                this.mAndroidAutoServiceManager = iAndroidAutoServiceManager;
                if (iAndroidAutoServiceManager != null) {
                    iAndroidAutoServiceManager.registerConnectionListener(this.mServiceConnectionListener);
                }
            } finally {
                th = th;
                while (true) {
                    try {
                    } catch (Throwable th) {
                        th = th;
                    }
                }
            }
        }
    }

    public synchronized void registerCallback(LinkStatusCallback linkStatusCallback) {
        this.mLinkStatusCallback = linkStatusCallback;
    }

    public synchronized void unRegisterCallback() {
        this.mLinkStatusCallback = null;
    }

    public synchronized void release() {
        synchronized (this.mServiceManagerLock) {
            try {
                LogUtil.info(TAG, " release");
                if (this.mAndroidAutoServiceManager != null) {
                    if (this.mIsAndroidAutoServiceConnected) {
                        try {
                            this.mAndroidAutoServiceManager.unregisterAndroidAutoServiceListener(this.mAndroidAutoServiceListener);
                            this.mAndroidAutoServiceManager.unregisterAndroidAutoSessionStatusListener(this.mAndroidAutoSessionStatusListener);
                        } catch (RemoteException ex) {
                            ex.printStackTrace();
                        }
                    }
                    this.mAndroidAutoServiceManager.unregisterConnectionListener(this.mServiceConnectionListener);
                    this.mAndroidAutoServiceManager = null;
                }
            } finally {
                th = th;
                while (true) {
                    try {
                    } catch (Throwable th) {
                        th = th;
                    }
                }
            }
        }
    }

    public synchronized void notifySurfaceDestoryed() {
        LogUtil.info(TAG, "notifySurfaceDestoryed");
        if (this.mAndroidAutoServiceManager != null) {
            try {
                this.mAndroidAutoServiceManager.setSurface((Surface) null, 0, 0);
            } catch (RemoteException ex) {
                ex.printStackTrace();
            }
        }
    }

    public synchronized boolean show(Surface surface, int width, int height) {
        LogUtil.info(TAG, "show surface");
        if (this.mAndroidAutoServiceManager != null && this.mIsAndroidAutoServiceConnected) {
            LogUtil.info(TAG, "HMI show surface success");
            try {
                this.mAndroidAutoServiceManager.setSurface(surface, width, height);
            } catch (RemoteException ex) {
                ex.printStackTrace();
            }
            subscribeHmiKeys();
            return true;
        }
        return false;
    }

    public synchronized boolean hide() {
        LogUtil.debug(TAG, "hide view");
        if (this.mAndroidAutoServiceManager == null || !this.mIsAndroidAutoServiceConnected) {
            return false;
        }
        try {
            this.mAndroidAutoServiceManager.setSurface((Surface) null, 0, 0);
        } catch (RemoteException ex) {
            ex.printStackTrace();
        }
        return true;
    }

    /* JADX WARN: Removed duplicated region for block: B:16:0x0038 A[Catch: all -> 0x0066, TRY_LEAVE, TryCatch #1 {, blocks: (B:3:0x0001, B:5:0x0005, B:7:0x0009, B:10:0x0010, B:12:0x0015, B:14:0x001c, B:16:0x0038), top: B:24:0x0001, inners: #0 }] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public synchronized void screenTouchEvent(android.view.MotionEvent r6) {
        /*
            r5 = this;
            monitor-enter(r5)
            ts.car.service.androidauto.IAndroidAutoServiceManager r0 = r5.mAndroidAutoServiceManager     // Catch: java.lang.Throwable -> L66
            if (r0 == 0) goto L13
            boolean r0 = r5.mIsAndroidAutoServiceConnected     // Catch: java.lang.Throwable -> L66
            if (r0 == 0) goto L13
            ts.car.service.androidauto.IAndroidAutoServiceManager r0 = r5.mAndroidAutoServiceManager     // Catch: android.os.RemoteException -> Lf java.lang.Throwable -> L66
            r0.screenTouchEvent(r6)     // Catch: android.os.RemoteException -> Lf java.lang.Throwable -> L66
            goto L13
        Lf:
            r0 = move-exception
            r0.printStackTrace()     // Catch: java.lang.Throwable -> L66
        L13:
            if (r6 == 0) goto L64
            int r0 = r6.getAction()     // Catch: java.lang.Throwable -> L66
            r1 = 1
            if (r0 != r1) goto L64
            int r0 = r6.getPointerCount()     // Catch: java.lang.Throwable -> L66
            java.lang.String r1 = com.ts.androidauto.app.display.model.LinkStatusModel.TAG     // Catch: java.lang.Throwable -> L66
            java.lang.StringBuilder r2 = new java.lang.StringBuilder     // Catch: java.lang.Throwable -> L66
            r2.<init>()     // Catch: java.lang.Throwable -> L66
            java.lang.String r3 = "screenTouchEvent UP pointer count: "
            r2.append(r3)     // Catch: java.lang.Throwable -> L66
            r2.append(r0)     // Catch: java.lang.Throwable -> L66
            java.lang.String r2 = r2.toString()     // Catch: java.lang.Throwable -> L66
            com.ts.androidauto.app.common.LogUtil.debug(r1, r2)     // Catch: java.lang.Throwable -> L66
            if (r0 <= 0) goto L64
            java.lang.String r1 = com.ts.androidauto.app.display.model.LinkStatusModel.TAG     // Catch: java.lang.Throwable -> L66
            java.lang.StringBuilder r2 = new java.lang.StringBuilder     // Catch: java.lang.Throwable -> L66
            r2.<init>()     // Catch: java.lang.Throwable -> L66
            java.lang.String r3 = "screenTouchEvent ("
            r2.append(r3)     // Catch: java.lang.Throwable -> L66
            r3 = 0
            float r4 = r6.getX(r3)     // Catch: java.lang.Throwable -> L66
            r2.append(r4)     // Catch: java.lang.Throwable -> L66
            java.lang.String r4 = ","
            r2.append(r4)     // Catch: java.lang.Throwable -> L66
            float r3 = r6.getY(r3)     // Catch: java.lang.Throwable -> L66
            r2.append(r3)     // Catch: java.lang.Throwable -> L66
            java.lang.String r3 = ")"
            r2.append(r3)     // Catch: java.lang.Throwable -> L66
            java.lang.String r2 = r2.toString()     // Catch: java.lang.Throwable -> L66
            com.ts.androidauto.app.common.LogUtil.debug(r1, r2)     // Catch: java.lang.Throwable -> L66
        L64:
            monitor-exit(r5)
            return
        L66:
            r6 = move-exception
            monitor-exit(r5)
            throw r6
        */
        throw new UnsupportedOperationException("Method not decompiled: com.ts.androidauto.app.display.model.LinkStatusModel.screenTouchEvent(android.view.MotionEvent):void");
    }

    public synchronized void unsubscribeHmiKeys() {
        LogUtil.debug(TAG, "Unsubscribe HMI Keys.");
        if (this.mAndroidAutoServiceManager != null && this.mRegisterKey) {
            this.mRegisterKey = false;
        }
    }

    public synchronized void subscribeHmiKeys() {
        LogUtil.debug(TAG, "Subscribe HMI Keys.");
        if (this.mAndroidAutoServiceManager != null && !this.mRegisterKey) {
            this.mRegisterKey = true;
        }
    }

    public VehicleData getVehicleInfo() {
        LogUtil.debug(TAG, "getVehicleInfo.");
        return VehicleInfoLoader.getInstance().getVehicleInfo(this.mContext);
    }
}
