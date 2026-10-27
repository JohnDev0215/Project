package com.ts.androidauto.app.model.common;

import android.os.HandlerThread;
import android.os.Looper;
import com.ts.androidauto.app.common.IModel;
import com.ts.androidauto.app.common.IModelFactory;
import com.ts.androidauto.app.displaycluster.ClusterModel;
import com.ts.androidauto.app.model.application.ApplicationModel;
import com.ts.androidauto.app.model.appstate.AppStateModel;
import com.ts.androidauto.app.model.hardkey.HardKeyModel;
import com.ts.androidauto.app.model.phone.NativePhoneCallModel;
import com.ts.androidauto.app.model.vehicle.VehicleModel;

/* JADX INFO: loaded from: classes2.dex */
public class ModelFactory implements IModelFactory {
    private static final String THREAD_NAME = "ModelFactory";
    private HandlerThread mHandlerThread;
    private Looper mLooper;
    private IModel[] mModels = {new HardKeyModel(getLooper()), new ApplicationModel(getLooper()), new VehicleModel(getLooper()), new AppStateModel(getLooper()), new ClusterModel(getLooper()), new NativePhoneCallModel(getLooper())};

    @Override // com.ts.androidauto.app.common.IModelFactory
    public IModel[] createModes() {
        return this.mModels;
    }

    private synchronized Looper getLooper() {
        if (this.mHandlerThread == null) {
            HandlerThread handlerThread = new HandlerThread(THREAD_NAME);
            this.mHandlerThread = handlerThread;
            handlerThread.start();
            this.mLooper = this.mHandlerThread.getLooper();
        }
        return this.mLooper;
    }
}
