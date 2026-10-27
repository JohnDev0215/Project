package com.ts.androidauto.app.display;

import android.view.MotionEvent;
import android.view.Surface;
import com.ts.androidauto.app.display.presenter.BasePresenter;
import ts.car.service.androidauto.data.VehicleData;

/* JADX INFO: loaded from: classes2.dex */
public interface DisplayContract {

    public interface DisplayView extends BaseView<Presenter> {
        void requestVideoFocus(int i);

        void showProjectionScreen(int i);
    }

    public interface Presenter extends BasePresenter {
        VehicleData getVehicleInfo();

        boolean hide();

        void notifySurfaceDestoryed();

        void screenTouchEvent(MotionEvent motionEvent);

        void setScreenRotationS(int i);

        boolean show(Surface surface, int i, int i2);

        void subscribeHmiKeys();

        void unsubscribeHmiKeys();
    }
}
