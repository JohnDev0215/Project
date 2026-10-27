package com.ts.carplay.app.service.model.common;

import android.os.HandlerThread;
import android.os.Looper;
import com.ts.carplay.app.service.common.IModel;
import com.ts.carplay.app.service.common.IModelFactory;
import com.ts.carplay.app.service.model.appstate.AppStateManager;
import com.ts.carplay.app.service.model.config.ConfigModel;
import com.ts.carplay.app.service.model.hardkey.HardkeyModel;
import com.ts.carplay.app.service.model.video.VideoModel;

/* JADX INFO: loaded from: classes.dex */
public class ModelFactory implements IModelFactory {
    private static final String THREAD_NAME = "ModelFactory";
    private HandlerThread mHandlerThread;
    private Looper mLooper;
    private IModel[] mModels = {new HardkeyModel(getLooper()), new AppStateManager(getLooper()), new VideoModel(getLooper()), new ConfigModel(getLooper())};

    @Override // com.ts.carplay.app.service.common.IModelFactory
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
