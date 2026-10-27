package com.byd.car.internal.appfocus;

import android.content.Context;
import android.os.RemoteException;
import com.byd.car.IAppFocusManager;
import com.byd.car.IFocusChangeListener;
import com.byd.car.appfocus.IAppFocusCallback;
import com.byd.car.appfocus.IAppFocusService;
import com.byd.spi.Spi;
import spi.ca;
import spi.i6;

/* JADX INFO: loaded from: classes2.dex */
@ca(singleton = true)
public class AppFocusManager implements IAppFocusManager {
    private static final String TAG = "AppFocusManager";
    private final Context mContext;

    public class AppFocusCallbackImpl extends IAppFocusCallback.Stub {
        public IFocusChangeListener mCallback;

        public AppFocusCallbackImpl(IFocusChangeListener iFocusChangeListener) {
            this.mCallback = iFocusChangeListener;
        }

        @Override // com.byd.car.appfocus.IAppFocusCallback
        public void onFocusChange(int i, int i2, String str) {
            i6.c(AppFocusManager.TAG, "onFocusChange currentFocusAppCode:" + i2 + ", currentFocusPackageName:" + str + ", resourceType:" + i);
            this.mCallback.onFocusChange(i2, str);
        }
    }

    public AppFocusManager(Context context) {
        this.mContext = context;
    }

    @Override // com.byd.car.IAppFocusManager
    public int getCurrentResourceFocusAppCode(int i) {
        i6.c(TAG, "getCurrentResourceFocusAppCode resourceType:" + i);
        try {
            return ((IAppFocusService) Spi.getService(this.mContext, IAppFocusService.class)).getCurrentResourceFocusAppCode(i);
        } catch (RemoteException e) {
            i6.e(TAG, "getCurrentResourceFocusAppCode remote exception:" + e);
            return -1;
        }
    }

    @Override // com.byd.car.IAppFocusManager
    public String getCurrentResourceFocusPackageName(int i) {
        i6.c(TAG, "getCurrentResourceFocusPackageName resourceType:" + i);
        try {
            return ((IAppFocusService) Spi.getService(this.mContext, IAppFocusService.class)).getCurrentResourceFocusPackageName(i);
        } catch (RemoteException e) {
            i6.e(TAG, "getCurrentResourceFocusPackageName remote exception:" + e);
            return null;
        }
    }

    @Override // com.byd.car.IAppFocusManager
    public void registerResourceFocusChanged(int i, int i2, IFocusChangeListener iFocusChangeListener) {
        i6.c(TAG, "registerResourceFocusChanged resourceType:" + i + ", appCode:" + i2);
        try {
            ((IAppFocusService) Spi.getService(this.mContext, IAppFocusService.class)).registerResourceFocusChanged(i, i2, this.mContext.getPackageName(), new AppFocusCallbackImpl(iFocusChangeListener));
        } catch (RemoteException e) {
            i6.e(TAG, "registerResourceFocusChanged remote exception:" + e);
        }
    }

    @Override // com.byd.car.IAppFocusManager
    public int releaseResourceFocus(int i, int i2) {
        i6.c(TAG, "releaseResourceFocus resourceType:" + i + ", appCode:" + i2);
        try {
            return ((IAppFocusService) Spi.getService(this.mContext, IAppFocusService.class)).releaseResourceFocus(i, i2, this.mContext.getPackageName());
        } catch (RemoteException e) {
            i6.e(TAG, "releaseResourceFocus remote exception:" + e);
            return 0;
        }
    }

    @Override // com.byd.car.IAppFocusManager
    public int requestResourceFocus(int i, int i2) {
        i6.c(TAG, "requestResourceFocus resourceType:" + i + ", appCode:" + i2);
        try {
            return ((IAppFocusService) Spi.getService(this.mContext, IAppFocusService.class)).requestResourceFocus(i, i2, this.mContext.getPackageName());
        } catch (RemoteException e) {
            i6.e(TAG, "requestResourceFocus remote exception:" + e);
            return 0;
        }
    }

    @Override // com.byd.car.IAppFocusManager
    public void unregisterResourceFocusChanged(int i, int i2) {
        i6.c(TAG, "unregisterResourceFocusChanged resourceType:" + i + ", appCode:" + i2);
        try {
            ((IAppFocusService) Spi.getService(this.mContext, IAppFocusService.class)).unregisterResourceFocusChanged(i, i2, this.mContext.getPackageName());
        } catch (RemoteException e) {
            i6.e(TAG, "unregisterResourceFocusChanged remote exception:" + e);
        }
    }
}
