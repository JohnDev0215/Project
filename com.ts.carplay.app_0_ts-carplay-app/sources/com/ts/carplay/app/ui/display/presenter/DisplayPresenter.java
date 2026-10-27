package com.ts.carplay.app.ui.display.presenter;

import android.content.Context;
import android.view.MotionEvent;
import android.view.Surface;
import com.ts.carplay.app.ui.display.model.LinkStatusCallback;
import com.ts.carplay.app.ui.display.model.LinkStatusModel;
import com.ts.carplay.app.ui.display.view.DisplayContract;
import com.ts.carplay.utils.LogUtil;

/* JADX INFO: loaded from: classes.dex */
public class DisplayPresenter implements DisplayContract.Presenter {
    private static final String TAG = DisplayPresenter.class.getSimpleName();
    private int mLinkStatus;
    LinkStatusCallback mLinkStatusCallback;
    LinkStatusModel mLinkStatusModel;
    DisplayContract.DisplayView mView;

    public DisplayPresenter(Context context) {
        this.mLinkStatus = -1;
        this.mLinkStatusCallback = new LinkStatusCallback() { // from class: com.ts.carplay.app.ui.display.presenter.DisplayPresenter.1
            @Override // com.ts.carplay.app.ui.display.model.LinkStatusCallback
            public void onStatusChanged(int linkStatus) {
                LogUtil.debug(DisplayPresenter.TAG, "mLinkStatusCallback --- onStatusChanged --- linkStatus " + linkStatus);
                DisplayPresenter.this.mLinkStatus = linkStatus;
                if (DisplayPresenter.this.mView != null) {
                    DisplayPresenter.this.mView.onStatusChanged(linkStatus);
                }
            }

            @Override // com.ts.carplay.app.ui.display.model.LinkStatusCallback
            public void onRequestVideoFocus(int requestType) {
                LogUtil.debug(DisplayPresenter.TAG, "onRequestVideoFocus --- requestType: " + requestType);
                DisplayPresenter.this.mView.requestVideoFocus(requestType);
            }
        };
        this.mLinkStatusModel = new LinkStatusModel(context);
    }

    public DisplayPresenter(Context context, DisplayContract.DisplayView view) {
        this(context);
        this.mView = view;
    }

    @Override // com.ts.carplay.app.ui.display.view.DisplayContract.Presenter
    public void release(Context context) {
        LogUtil.debug(TAG, " release() ");
        LinkStatusModel linkStatusModel = this.mLinkStatusModel;
        if (linkStatusModel != null) {
            linkStatusModel.unRegisterCallback();
            this.mLinkStatusModel.release(context);
        }
    }

    @Override // com.ts.carplay.app.ui.display.view.DisplayContract.Presenter
    public boolean show(Surface surface, int width, int height) {
        return show(surface, 0);
    }

    @Override // com.ts.carplay.app.ui.display.view.DisplayContract.Presenter
    public boolean show(Surface surface, int screenType) {
        LogUtil.debug(TAG, " show ");
        if (this.mLinkStatus != 2) {
            LogUtil.debug(TAG, "Projection device not activated and do nothing");
            return false;
        }
        LinkStatusModel linkStatusModel = this.mLinkStatusModel;
        if (linkStatusModel != null) {
            return linkStatusModel.show(surface, screenType);
        }
        return false;
    }

    @Override // com.ts.carplay.app.ui.display.view.DisplayContract.Presenter
    public void hide() {
        LogUtil.debug(TAG, " hide ");
        LinkStatusModel linkStatusModel = this.mLinkStatusModel;
        if (linkStatusModel != null) {
            linkStatusModel.hide();
        }
    }

    @Override // com.ts.carplay.app.ui.display.view.DisplayContract.Presenter
    public void hide(int screenType) {
        LogUtil.debug(TAG, " hide ");
        LinkStatusModel linkStatusModel = this.mLinkStatusModel;
        if (linkStatusModel != null) {
            linkStatusModel.hide(0);
        }
    }

    @Override // com.ts.carplay.app.ui.display.view.DisplayContract.Presenter
    public void screenTouchEvent(MotionEvent event) {
        LinkStatusModel linkStatusModel = this.mLinkStatusModel;
        if (linkStatusModel != null) {
            linkStatusModel.screenTouchEvent(event);
        }
    }

    @Override // com.ts.carplay.app.ui.display.view.DisplayContract.Presenter
    public void initManager(Context context) {
        LinkStatusModel linkStatusModel = this.mLinkStatusModel;
        if (linkStatusModel != null) {
            linkStatusModel.registerCallback(this.mLinkStatusCallback);
            this.mLinkStatusModel.initManager(context);
        }
    }

    @Override // com.ts.carplay.app.ui.display.view.DisplayContract.Presenter
    public void requestUi(int launchMode) {
        LinkStatusModel linkStatusModel = this.mLinkStatusModel;
        if (linkStatusModel != null) {
            linkStatusModel.requestUi(launchMode);
        }
    }
}
