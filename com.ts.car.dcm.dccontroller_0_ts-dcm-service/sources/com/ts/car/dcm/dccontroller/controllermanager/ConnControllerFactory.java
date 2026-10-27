package com.ts.car.dcm.dccontroller.controllermanager;

import android.content.Context;
import android.util.Log;
import com.ts.car.dcm.common.controller.ConnectionController;

/* JADX INFO: loaded from: classes.dex */
public class ConnControllerFactory {
    private static final int BUF_SIZE = 1024;
    private static final String TAG = "ConnControllerFactory";

    static ConnectionController getConnController(Context context, ControllerInformation jarInfo) {
        ConnectionController controller = null;
        try {
            Class<?> controllerClass = Class.forName(jarInfo.getClassName());
            controller = (ConnectionController) controllerClass.newInstance();
            Log.d(TAG, "getConnController, className:" + jarInfo.getClassName());
            return controller;
        } catch (Exception ex) {
            ex.printStackTrace();
            Log.e(TAG, "getConnController, load className:" + jarInfo.getClassName() + " failed.");
            return controller;
        }
    }
}
