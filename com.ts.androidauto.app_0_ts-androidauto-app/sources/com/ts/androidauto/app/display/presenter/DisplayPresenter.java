package com.ts.androidauto.app.display.presenter;

import android.content.Context;
import android.view.MotionEvent;
import android.view.Surface;
import com.ts.androidauto.app.common.LogUtil;
import com.ts.androidauto.app.display.DisplayContract;
import com.ts.androidauto.app.display.model.LinkStatusCallback;
import com.ts.androidauto.app.display.model.LinkStatusModel;
import com.ts.androidauto.app.manager.AndroidAutoRemoteUiManager;
import ts.car.service.androidauto.data.VehicleData;

/* JADX INFO: loaded from: classes2.dex */
public class DisplayPresenter implements DisplayContract.Presenter {
    private static final String TAG = "AAP" + DisplayPresenter.class.getSimpleName();
    private AndroidAutoRemoteUiManager mAndroidAutoRemoteUiManager;
    private int mLinkStatus;
    LinkStatusCallback mLinkStatusCallback;
    LinkStatusModel mLinkStatusModel;
    private int mReqVideoType;
    DisplayContract.DisplayView mView;

    public DisplayPresenter(Context context) {
        this.mReqVideoType = -1;
        this.mLinkStatusCallback = new LinkStatusCallback() { // from class: com.ts.androidauto.app.display.presenter.DisplayPresenter.1
            @Override // com.ts.androidauto.app.display.model.LinkStatusCallback
            public void onStatusChanged(int linkStatus, int errorCode) {
                LogUtil.debug(DisplayPresenter.TAG, "mLinkStatusCallback --- onStatusChanged --- linkStatus " + linkStatus);
                DisplayPresenter.this.mLinkStatus = linkStatus;
                if (DisplayPresenter.this.mView != null && linkStatus != 3) {
                    DisplayPresenter.this.mView.showProjectionScreen(linkStatus);
                }
            }

            @Override // com.ts.androidauto.app.display.model.LinkStatusCallback
            public void onRequestVideoFocus(int requestType) {
                LogUtil.debug(DisplayPresenter.TAG, "onVideoFocusStatusChanged --- requestType: " + requestType);
                DisplayPresenter.this.mReqVideoType = requestType;
                DisplayPresenter.this.mView.requestVideoFocus(requestType);
            }
        };
        this.mLinkStatusModel = new LinkStatusModel(context);
    }

    public DisplayPresenter(Context context, DisplayContract.DisplayView view) {
        this(context);
        this.mView = view;
    }

    @Override // com.ts.androidauto.app.display.presenter.BasePresenter
    public void init() {
        LogUtil.debug(TAG, " init() ");
        this.mReqVideoType = -1;
        LinkStatusModel linkStatusModel = this.mLinkStatusModel;
        if (linkStatusModel != null) {
            linkStatusModel.registerCallback(this.mLinkStatusCallback);
            this.mLinkStatusModel.init();
        }
        this.mAndroidAutoRemoteUiManager = AndroidAutoRemoteUiManager.getInstance();
    }

    @Override // com.ts.androidauto.app.display.presenter.BasePresenter
    public void release() {
        LogUtil.debug(TAG, " release() ");
        LinkStatusModel linkStatusModel = this.mLinkStatusModel;
        if (linkStatusModel != null) {
            linkStatusModel.unRegisterCallback();
            this.mLinkStatusModel.release();
        }
    }

    @Override // com.ts.androidauto.app.display.DisplayContract.Presenter
    public void setScreenRotationS(int orientation) {
        String str = TAG;
        StringBuilder sb = new StringBuilder();
        sb.append("setScreenRotationS mAndroidAutoRemoteUiManager");
        sb.append(this.mAndroidAutoRemoteUiManager == null);
        LogUtil.debug(str, sb.toString());
        AndroidAutoRemoteUiManager androidAutoRemoteUiManager = this.mAndroidAutoRemoteUiManager;
        if (androidAutoRemoteUiManager != null) {
            androidAutoRemoteUiManager.setScreenRotationS(orientation);
            LogUtil.debug(TAG, " setScreenRotationS()" + orientation);
        }
    }

    @Override // com.ts.androidauto.app.display.DisplayContract.Presenter
    public void notifySurfaceDestoryed() {
        LogUtil.debug(TAG, " notifySurfaceDestoryed");
        LinkStatusModel linkStatusModel = this.mLinkStatusModel;
        if (linkStatusModel != null) {
            linkStatusModel.notifySurfaceDestoryed();
        } else {
            LogUtil.debug(TAG, "mLinkStatusModel is null");
        }
    }

    @Override // com.ts.androidauto.app.display.DisplayContract.Presenter
    public boolean show(Surface surface, int width, int height) {
        LogUtil.debug(TAG, " show ");
        if (this.mReqVideoType == 0) {
            LogUtil.info(TAG, "Show native screen and do nothing");
            return false;
        }
        if (this.mLinkStatus != 4) {
            LogUtil.info(TAG, "Projection device not activated and do nothing");
            return false;
        }
        LinkStatusModel linkStatusModel = this.mLinkStatusModel;
        if (linkStatusModel != null) {
            return linkStatusModel.show(surface, width, height);
        }
        return false;
    }

    @Override // com.ts.androidauto.app.display.DisplayContract.Presenter
    public boolean hide() {
        LogUtil.debug(TAG, " hide ");
        LinkStatusModel linkStatusModel = this.mLinkStatusModel;
        if (linkStatusModel != null) {
            return linkStatusModel.hide();
        }
        return false;
    }

    @Override // com.ts.androidauto.app.display.DisplayContract.Presenter
    public void screenTouchEvent(MotionEvent event) {
        LinkStatusModel linkStatusModel = this.mLinkStatusModel;
        if (linkStatusModel != null) {
            linkStatusModel.screenTouchEvent(event);
        }
    }

    @Override // com.ts.androidauto.app.display.DisplayContract.Presenter
    public void unsubscribeHmiKeys() {
        LogUtil.debug(TAG, "Unsubscribe HMI Keys.");
        LinkStatusModel linkStatusModel = this.mLinkStatusModel;
        if (linkStatusModel != null) {
            linkStatusModel.unsubscribeHmiKeys();
        }
    }

    @Override // com.ts.androidauto.app.display.DisplayContract.Presenter
    public void subscribeHmiKeys() {
        LogUtil.debug(TAG, "Subscribe HMI Keys.");
        LinkStatusModel linkStatusModel = this.mLinkStatusModel;
        if (linkStatusModel != null) {
            linkStatusModel.subscribeHmiKeys();
        }
    }

    @Override // com.ts.androidauto.app.display.DisplayContract.Presenter
    public VehicleData getVehicleInfo() {
        if (this.mLinkStatusModel != null) {
            LogUtil.debug(TAG, "getVehicleInfo.");
            return this.mLinkStatusModel.getVehicleInfo();
        }
        return null;
    }
}
