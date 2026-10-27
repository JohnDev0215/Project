package com.bydux.api.common.views.application;

import android.app.Application;
import android.content.Context;
import android.content.res.Configuration;
import com.bydux.api.common.config.ConfigManager;
import com.bydux.api.common.tools.log.BydLogHelper;
import com.bydux.api.common.views.skin.listener.ILoaderListener;
import com.bydux.api.common.views.skin.loader.SkinManager;

/* JADX INFO: loaded from: classes.dex */
public class BydApplication extends Application implements ILoaderListener {
    private static Context mContext;
    private final String TAG = getClass().getSimpleName();
    private int mCurMode = -1;

    protected boolean onSkinEnable() {
        return false;
    }

    public static Context getContext() {
        return mContext;
    }

    @Override // android.app.Application
    public void onCreate() {
        super.onCreate();
        mContext = this;
        ConfigManager.getInstance().getCatchCrashEnable(this);
        BydLogHelper.logI(this.TAG, "onCreate", null);
        BydLogHelper.init(this);
        this.mCurMode = new Configuration(getResources().getConfiguration()).uiMode;
        SkinManager.getInstance().init(this, this, onSkinEnable());
    }

    @Override // android.app.Application
    public void onTerminate() {
        super.onTerminate();
        BydLogHelper.logI(this.TAG, "onTerminate", null);
        SkinManager.getInstance().release();
    }

    @Override // android.app.Application, android.content.ComponentCallbacks
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        if (this.mCurMode != newConfig.uiMode) {
            SkinManager.getInstance().notifyResoureState(SkinManager.getInstance().updateResource(newConfig), this);
            this.mCurMode = newConfig.uiMode;
        }
    }

    @Override // com.bydux.api.common.views.skin.listener.ILoaderListener
    public void onChangeSkinStart() {
        BydLogHelper.logI(this.TAG, "onChangeSkinStart", null);
    }

    @Override // com.bydux.api.common.views.skin.listener.ILoaderListener
    public void onChangeSkinSuccess() {
        BydLogHelper.logI(this.TAG, "onChangeSkinSuccess", null);
    }

    @Override // com.bydux.api.common.views.skin.listener.ILoaderListener
    public void onChangeSkinFail() {
        BydLogHelper.logI(this.TAG, "onChangeSkinFail", null);
    }
}
