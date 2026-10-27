package com.byd.car.internal.appfocus;

import android.content.Context;
import android.os.RemoteException;
import com.byd.car.IAppFocusManager;
import com.byd.car.IFocusChangeListener;
import com.byd.car.appfocus.IAppFocusCallback;
import com.byd.car.appfocus.IAppFocusService;
import com.byd.spi.Spi;
import spi.dh;
import spi.xi;
import spi.zo;

/* JADX INFO: loaded from: classes.dex */
@zo(singleton = true)
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
            String str2 = AppFocusManager.TAG;
            StringBuilder sbH = xi.h("onFocusChange currentFocusAppCode:", i2, ", currentFocusPackageName:", str, ", resourceType:");
            sbH.append(i);
            dh.c(str2, sbH.toString());
            this.mCallback.onFocusChange(i2, str);
        }
    }

    public AppFocusManager(Context context) {
        this.mContext = context;
    }

    @Override // com.byd.car.IAppFocusManager
    public int getCurrentResourceFocusAppCode(int i) {
        dh.c(TAG, "getCurrentResourceFocusAppCode resourceType:" + i);
        try {
            return ((IAppFocusService) Spi.getService(this.mContext, IAppFocusService.class)).getCurrentResourceFocusAppCode(i);
        } catch (RemoteException e) {
            dh.e(TAG, "getCurrentResourceFocusAppCode remote exception:" + e);
            return -1;
        }
    }

    @Override // com.byd.car.IAppFocusManager
    public String getCurrentResourceFocusPackageName(int i) {
        dh.c(TAG, "getCurrentResourceFocusPackageName resourceType:" + i);
        try {
            return ((IAppFocusService) Spi.getService(this.mContext, IAppFocusService.class)).getCurrentResourceFocusPackageName(i);
        } catch (RemoteException e) {
            dh.e(TAG, "getCurrentResourceFocusPackageName remote exception:" + e);
            return null;
        }
    }

    @Override // com.byd.car.IAppFocusManager
    public void registerResourceFocusChanged(int i, int i2, IFocusChangeListener iFocusChangeListener) {
        dh.c(TAG, "registerResourceFocusChanged resourceType:" + i + ", appCode:" + i2);
        try {
            ((IAppFocusService) Spi.getService(this.mContext, IAppFocusService.class)).registerResourceFocusChanged(i, i2, this.mContext.getPackageName(), new AppFocusCallbackImpl(iFocusChangeListener));
        } catch (RemoteException e) {
            dh.e(TAG, "registerResourceFocusChanged remote exception:" + e);
        }
    }

    @Override // com.byd.car.IAppFocusManager
    public int releaseResourceFocus(int i, int i2) {
        dh.c(TAG, "releaseResourceFocus resourceType:" + i + ", appCode:" + i2);
        try {
            return ((IAppFocusService) Spi.getService(this.mContext, IAppFocusService.class)).releaseResourceFocus(i, i2, this.mContext.getPackageName());
        } catch (RemoteException e) {
            dh.e(TAG, "releaseResourceFocus remote exception:" + e);
            return 0;
        }
    }

    @Override // com.byd.car.IAppFocusManager
    public int requestResourceFocus(int i, int i2) {
        dh.c(TAG, "requestResourceFocus resourceType:" + i + ", appCode:" + i2);
        try {
            return ((IAppFocusService) Spi.getService(this.mContext, IAppFocusService.class)).requestResourceFocus(i, i2, this.mContext.getPackageName());
        } catch (RemoteException e) {
            dh.e(TAG, "requestResourceFocus remote exception:" + e);
            return 0;
        }
    }

    @Override // com.byd.car.IAppFocusManager
    public void unregisterResourceFocusChanged(int i, int i2) {
        dh.c(TAG, "unregisterResourceFocusChanged resourceType:" + i + ", appCode:" + i2);
        try {
            ((IAppFocusService) Spi.getService(this.mContext, IAppFocusService.class)).unregisterResourceFocusChanged(i, i2, this.mContext.getPackageName());
        } catch (RemoteException e) {
            dh.e(TAG, "unregisterResourceFocusChanged remote exception:" + e);
        }
    }
}
