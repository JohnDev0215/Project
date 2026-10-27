package com.ts.carplay.app.ui.display.model;

import android.content.Context;
import android.os.RemoteException;
import android.view.MotionEvent;
import android.view.Surface;
import com.ts.car.core.ServiceProvider;
import com.ts.carplay.utils.LogUtil;
import ts.car.service.IPlatformService;
import ts.car.service.IServiceConnectionListener;
import ts.car.service.carplay.ICarPlayServiceListener;
import ts.car.service.carplay.ICarPlayServiceManager;
import ts.car.service.carplay.ICarPlayStateListener;
import ts.car.service.carplay.data.config.LogEncrypt;

/* JADX INFO: loaded from: classes.dex */
public class LinkStatusModel {
    private static final String TAG = LinkStatusModel.class.getSimpleName();
    private int launchMode;
    private ICarPlayServiceManager mCarPlayServiceManager;
    private Context mContext;
    private LinkStatusCallback mLinkStatusCallback;
    private boolean isServiceBind = false;
    private boolean needRequestUi = false;
    private final IServiceConnectionListener mServiceConnectionListener = new IServiceConnectionListener() { // from class: com.ts.carplay.app.ui.display.model.LinkStatusModel.1
        public void onServiceConnected(IPlatformService service) {
            LogUtil.debug(LinkStatusModel.TAG, "onServiceConnected.");
            if (LinkStatusModel.this.mCarPlayServiceManager != null) {
                try {
                    LinkStatusModel.this.mCarPlayServiceManager.registerCarPlayStateListener(LinkStatusModel.this.mCarPlayStateListener);
                    LinkStatusModel.this.mCarPlayServiceManager.registerCarPlayServiceListener(LinkStatusModel.this.mCarPlayServiceListener);
                    if (LinkStatusModel.this.mLinkStatusCallback != null && LinkStatusModel.this.mCarPlayServiceManager != null) {
                        LinkStatusModel.this.isServiceBind = true;
                        if (LinkStatusModel.this.needRequestUi) {
                            LinkStatusModel.this.needRequestUi = false;
                            LinkStatusModel.this.requestUi(LinkStatusModel.this.launchMode);
                        }
                        LinkStatusModel.this.mLinkStatusCallback.onStatusChanged(LinkStatusModel.this.mCarPlayServiceManager.getCarPlaySessionStatus());
                    }
                } catch (RemoteException ex) {
                    ex.printStackTrace();
                }
            }
        }

        public void onServiceDisconnected(IPlatformService service) {
            LogUtil.debug(LinkStatusModel.TAG, "onServiceDisconnected.");
            LinkStatusModel.this.isServiceBind = false;
        }
    };
    private ICarPlayServiceListener mCarPlayServiceListener = new ICarPlayServiceListener() { // from class: com.ts.carplay.app.ui.display.model.LinkStatusModel.2
        public void onRequestVideoFocusChanged(int requestType) {
            if (LinkStatusModel.this.mLinkStatusCallback != null) {
                LinkStatusModel.this.mLinkStatusCallback.onRequestVideoFocus(requestType);
            }
        }
    };
    private ICarPlayStateListener mCarPlayStateListener = new ICarPlayStateListener() { // from class: com.ts.carplay.app.ui.display.model.LinkStatusModel.3
        public void onSessionStateChanged(int status) {
            LogUtil.debug(LinkStatusModel.TAG, "onSessionStateChanged status: " + status);
            if (LinkStatusModel.this.mLinkStatusCallback != null) {
                LinkStatusModel.this.mLinkStatusCallback.onStatusChanged(status);
            }
        }

        public void onSessionConnectStateChanged(int status, boolean isOldConnection, String clientAddr) {
            LogUtil.debug(LinkStatusModel.TAG, "onSessionConnectStateChanged status: " + status + " isOldConnection: " + isOldConnection + " clientAddr: " + LogEncrypt.MacAddressEncrypt(clientAddr));
        }

        public void onCarPlaySessionConnectStateChanged(int status, boolean isOldConnection, String clientAddr, boolean isUserConnect) {
            LogUtil.debug(LinkStatusModel.TAG, "onCarPlaySessionConnectStateChanged status: " + status + " isOldConnection: " + isOldConnection + " clientAddr: " + LogEncrypt.MacAddressEncrypt(clientAddr) + " isUserConnect: " + isUserConnect);
        }
    };

    public LinkStatusModel(Context context) {
        this.mContext = null;
        this.mContext = context;
    }

    public void registerCallback(LinkStatusCallback linkStatusCallback) {
        this.mLinkStatusCallback = linkStatusCallback;
    }

    public void unRegisterCallback() {
        this.mLinkStatusCallback = null;
    }

    public void initManager(Context context) {
        LogUtil.debug(TAG, "initManager enter");
        ICarPlayServiceManager iCarPlayServiceManager = (ICarPlayServiceManager) ServiceProvider.get(ICarPlayServiceManager.class, context);
        this.mCarPlayServiceManager = iCarPlayServiceManager;
        if (iCarPlayServiceManager != null) {
            iCarPlayServiceManager.registerConnectionListener(this.mServiceConnectionListener);
        }
    }

    public void release(Context context) {
        ICarPlayServiceManager iCarPlayServiceManager = this.mCarPlayServiceManager;
        if (iCarPlayServiceManager != null) {
            iCarPlayServiceManager.unregisterConnectionListener(this.mServiceConnectionListener);
            try {
                this.mCarPlayServiceManager.unregisterCarPlayStateListener(this.mCarPlayStateListener);
                this.mCarPlayServiceManager.unregisterCarPlayServiceListener(this.mCarPlayServiceListener);
                this.mCarPlayServiceManager = null;
            } catch (RemoteException remoteException) {
                throw new RuntimeException(remoteException);
            }
        }
    }

    public boolean show(Surface surface, int width, int height) {
        return show(surface, 0);
    }

    public boolean show(Surface surface, int screenType) {
        LogUtil.debug(TAG, "show surface screenType:" + screenType);
        ICarPlayServiceManager iCarPlayServiceManager = this.mCarPlayServiceManager;
        if (iCarPlayServiceManager != null) {
            try {
                iCarPlayServiceManager.setSurface(surface, screenType);
                return true;
            } catch (RemoteException ex) {
                ex.printStackTrace();
                return true;
            }
        }
        return false;
    }

    public void showUi(int screenType, int url) {
        LogUtil.debug(TAG, "showUi screenType:" + screenType + " url:" + url);
        ICarPlayServiceManager iCarPlayServiceManager = this.mCarPlayServiceManager;
        if (iCarPlayServiceManager != null) {
            try {
                iCarPlayServiceManager.showUi(screenType, url);
                return;
            } catch (RemoteException ex) {
                ex.printStackTrace();
                return;
            }
        }
        LogUtil.info(TAG, "showUi mCarPlayServiceManager is null");
    }

    public void stopUi(int screenType) {
        LogUtil.debug(TAG, "stopUi screenType:" + screenType);
        ICarPlayServiceManager iCarPlayServiceManager = this.mCarPlayServiceManager;
        if (iCarPlayServiceManager != null) {
            try {
                iCarPlayServiceManager.stopUi(screenType);
                return;
            } catch (RemoteException ex) {
                ex.printStackTrace();
                return;
            }
        }
        LogUtil.info(TAG, "stopUi mCarPlayServiceManager is null");
    }

    public void hide() {
        hide(0);
    }

    public void hide(int screenType) {
        LogUtil.debug(TAG, "hide view");
        ICarPlayServiceManager iCarPlayServiceManager = this.mCarPlayServiceManager;
        if (iCarPlayServiceManager != null) {
            try {
                iCarPlayServiceManager.setSurface((Surface) null, screenType);
            } catch (RemoteException ex) {
                ex.printStackTrace();
            }
        }
    }

    public void screenTouchEvent(MotionEvent event) {
        LogUtil.verbose(TAG, "screenTouchEvent " + event);
        ICarPlayServiceManager iCarPlayServiceManager = this.mCarPlayServiceManager;
        if (iCarPlayServiceManager != null) {
            try {
                iCarPlayServiceManager.screenTouchEvent(event);
            } catch (RemoteException ex) {
                ex.printStackTrace();
            }
        }
    }

    public void requestUi(int launchMode) {
        LogUtil.info(TAG, "requestUi launchMode:" + launchMode + " isServiceBind:" + this.isServiceBind);
        ICarPlayServiceManager iCarPlayServiceManager = this.mCarPlayServiceManager;
        if (iCarPlayServiceManager != null) {
            try {
                if (this.isServiceBind) {
                    this.needRequestUi = false;
                    iCarPlayServiceManager.requestUi(launchMode);
                } else {
                    this.needRequestUi = true;
                    this.launchMode = launchMode;
                }
            } catch (RemoteException ex) {
                ex.printStackTrace();
            }
        }
    }
}
