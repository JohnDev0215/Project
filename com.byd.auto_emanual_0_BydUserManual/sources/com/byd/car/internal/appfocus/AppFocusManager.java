package com.byd.car.internal.appfocus;

import android.content.Context;
import android.os.RemoteException;
import com.byd.auto_emanual.Config;
import com.byd.car.IAppFocusManager;
import com.byd.car.IFocusChangeListener;
import com.byd.car.appfocus.IAppFocusCallback;
import com.byd.car.appfocus.IAppFocusService;
import com.byd.foundation.annotation.ServiceImpl;
import com.byd.foundation.utils.LogUtils;
import com.byd.spi.Spi;

/* JADX INFO: loaded from: classes.dex */
@ServiceImpl(service = IAppFocusManager.class, singleton = Config.USE_PRODUCT_URL)
public class AppFocusManager implements IAppFocusManager {
    private static final String TAG = "AppFocusManager";
    private final Context mContext;

    public AppFocusManager(Context context) {
        this.mContext = context;
    }

    @Override // com.byd.car.IAppFocusManager
    public void registerResourceFocusChanged(int i, int i2, IFocusChangeListener iFocusChangeListener) {
        LogUtils.i(TAG, "registerResourceFocusChanged resourceType:" + i + ", appCode:" + i2);
        try {
            ((IAppFocusService) Spi.getService(this.mContext, IAppFocusService.class)).registerResourceFocusChanged(i, i2, this.mContext.getPackageName(), new AppFocusCallbackImpl(iFocusChangeListener));
        } catch (RemoteException e) {
            LogUtils.w(TAG, "registerResourceFocusChanged remote exception:" + e);
        }
    }

    @Override // com.byd.car.IAppFocusManager
    public int requestResourceFocus(int i, int i2) {
        LogUtils.i(TAG, "requestResourceFocus resourceType:" + i + ", appCode:" + i2);
        try {
            return ((IAppFocusService) Spi.getService(this.mContext, IAppFocusService.class)).requestResourceFocus(i, i2, this.mContext.getPackageName());
        } catch (RemoteException e) {
            LogUtils.w(TAG, "requestResourceFocus remote exception:" + e);
            return 0;
        }
    }

    @Override // com.byd.car.IAppFocusManager
    public int releaseResourceFocus(int i, int i2) {
        LogUtils.i(TAG, "releaseResourceFocus resourceType:" + i + ", appCode:" + i2);
        try {
            return ((IAppFocusService) Spi.getService(this.mContext, IAppFocusService.class)).releaseResourceFocus(i, i2, this.mContext.getPackageName());
        } catch (RemoteException e) {
            LogUtils.w(TAG, "releaseResourceFocus remote exception:" + e);
            return 0;
        }
    }

    @Override // com.byd.car.IAppFocusManager
    public void unregisterResourceFocusChanged(int i, int i2) {
        LogUtils.i(TAG, "unregisterResourceFocusChanged resourceType:" + i + ", appCode:" + i2);
        try {
            ((IAppFocusService) Spi.getService(this.mContext, IAppFocusService.class)).unregisterResourceFocusChanged(i, i2, this.mContext.getPackageName());
        } catch (RemoteException e) {
            LogUtils.w(TAG, "unregisterResourceFocusChanged remote exception:" + e);
        }
    }

    @Override // com.byd.car.IAppFocusManager
    public int getCurrentResourceFocusAppCode(int i) {
        LogUtils.i(TAG, "getCurrentResourceFocusAppCode resourceType:" + i);
        try {
            return ((IAppFocusService) Spi.getService(this.mContext, IAppFocusService.class)).getCurrentResourceFocusAppCode(i);
        } catch (RemoteException e) {
            LogUtils.w(TAG, "getCurrentResourceFocusAppCode remote exception:" + e);
            return -1;
        }
    }

    @Override // com.byd.car.IAppFocusManager
    public String getCurrentResourceFocusPackageName(int i) {
        LogUtils.i(TAG, "getCurrentResourceFocusPackageName resourceType:" + i);
        try {
            return ((IAppFocusService) Spi.getService(this.mContext, IAppFocusService.class)).getCurrentResourceFocusPackageName(i);
        } catch (RemoteException e) {
            LogUtils.w(TAG, "getCurrentResourceFocusPackageName remote exception:" + e);
            return null;
        }
    }

    public class AppFocusCallbackImpl extends IAppFocusCallback.Stub {
        IFocusChangeListener mCallback;

        public AppFocusCallbackImpl(IFocusChangeListener iFocusChangeListener) {
            this.mCallback = iFocusChangeListener;
        }

        @Override // com.byd.car.appfocus.IAppFocusCallback
        public void onFocusChange(int i, int i2, String str) {
            LogUtils.i(AppFocusManager.TAG, "onFocusChange currentFocusAppCode:" + i2 + ", currentFocusPackageName:" + str + ", resourceType:" + i);
            this.mCallback.onFocusChange(i2, str);
        }
    }
}
