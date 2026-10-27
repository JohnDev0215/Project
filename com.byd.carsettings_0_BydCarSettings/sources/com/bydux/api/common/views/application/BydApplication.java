package com.bydux.api.common.views.application;

import android.app.Application;
import android.content.Context;
import android.content.res.Configuration;
import androidx.annotation.NonNull;
import com.bydux.api.common.config.ConfigManager;
import com.bydux.api.common.tools.log.BydLogHelper;
import com.bydux.api.common.views.skin.listener.ILoaderListener;
import com.bydux.api.common.views.skin.loader.SkinManager;

/* JADX INFO: loaded from: classes3.dex */
public class BydApplication extends Application implements ILoaderListener {
    private static Context mContext;
    private final String TAG = getClass().getSimpleName();
    private int mCurMode = -1;

    public static Context getContext() {
        return mContext;
    }

    @Override // com.bydux.api.common.views.skin.listener.ILoaderListener
    public void onChangeSkinFail() {
        BydLogHelper.logI(this.TAG, "onChangeSkinFail", null);
    }

    @Override // com.bydux.api.common.views.skin.listener.ILoaderListener
    public void onChangeSkinStart() {
        BydLogHelper.logI(this.TAG, "onChangeSkinStart", null);
    }

    @Override // com.bydux.api.common.views.skin.listener.ILoaderListener
    public void onChangeSkinSuccess() {
        BydLogHelper.logI(this.TAG, "onChangeSkinSuccess", null);
    }

    @Override // android.app.Application, android.content.ComponentCallbacks
    public void onConfigurationChanged(@NonNull Configuration configuration) {
        super.onConfigurationChanged(configuration);
        if (this.mCurMode != configuration.uiMode) {
            SkinManager.getInstance().notifyResoureState(SkinManager.getInstance().updateResource(configuration), this);
            this.mCurMode = configuration.uiMode;
        }
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

    public boolean onSkinEnable() {
        return false;
    }

    @Override // android.app.Application
    public void onTerminate() {
        super.onTerminate();
        BydLogHelper.logI(this.TAG, "onTerminate", null);
        SkinManager.getInstance().release();
    }
}
