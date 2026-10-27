package com.android.launcher3.model;

import android.content.Context;
import android.util.Log;
import com.android.launcher3.AllAppsList;
import com.android.launcher3.LauncherAppState;
import com.android.launcher3.LauncherModel;
import java.util.concurrent.Executor;

/* JADX INFO: loaded from: classes4.dex */
public class ModelPreload implements LauncherModel.ModelUpdateTask {
    private static final String TAG = "ModelPreload";
    private AllAppsList mAllAppsList;
    private LauncherAppState mApp;
    private BgDataModel mBgDataModel;
    private LauncherModel mModel;

    public void onComplete(boolean isSuccess) {
    }

    @Override // com.android.launcher3.LauncherModel.ModelUpdateTask
    public final void init(LauncherAppState app, LauncherModel model, BgDataModel dataModel, AllAppsList allAppsList, Executor uiExecutor) {
        this.mApp = app;
        this.mModel = model;
        this.mBgDataModel = dataModel;
        this.mAllAppsList = allAppsList;
    }

    @Override // java.lang.Runnable
    public final void run() {
        this.mModel.startLoaderForResultsIfNotLoaded(new LoaderResults(this.mApp, this.mBgDataModel, this.mAllAppsList, 0, null));
        Log.d(TAG, "Preload completed : " + this.mModel.isModelLoaded());
        onComplete(this.mModel.isModelLoaded());
    }

    public void start(Context context) {
        LauncherAppState.getInstance(context).getModel().enqueueModelUpdateTask(this);
    }
}
