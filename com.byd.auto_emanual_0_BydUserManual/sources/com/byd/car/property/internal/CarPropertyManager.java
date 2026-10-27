package com.byd.car.property.internal;

import android.content.Context;
import android.os.Handler;
import android.os.Looper;
import android.os.RemoteException;
import android.text.TextUtils;
import com.byd.auto_emanual.Config;
import com.byd.car.DiCarLog;
import com.byd.car.property.CarPropertyConfig;
import com.byd.car.property.CarPropertyValue;
import com.byd.car.property.ICarPropertyListener;
import com.byd.car.property.ICarPropertyManager;
import com.byd.car.property.ICarPropertyService;
import com.byd.car.property.internal.CarPropertyManager;
import com.byd.datasource.feature.Response;
import com.byd.datasource.feature.Status;
import com.byd.foundation.annotation.ServiceImpl;
import com.byd.foundation.callback.ValueCallback;
import com.byd.foundation.utils.CollectionUtils;
import com.byd.foundation.utils.LogUtils;
import com.byd.foundation.utils.ObjectUtils;
import com.byd.spi.Spi;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Predicate;

/* JADX INFO: loaded from: classes.dex */
@ServiceImpl(service = ICarPropertyManager.class, singleton = Config.USE_PRODUCT_URL)
public class CarPropertyManager implements ICarPropertyManager {
    private Context mContext;
    protected Map<String, List<CallbackInfo>> mCallbacks = new HashMap();
    private Handler mHandler = new Handler(Looper.getMainLooper());
    private CarPropertyValueCallback mPropertyListener = new CarPropertyValueCallback();

    public CarPropertyManager(Context context) {
        this.mContext = context.getApplicationContext();
    }

    @Override // com.byd.car.property.ICarPropertyManager
    public <T> Status setProperty(String str, T t) {
        if (TextUtils.isEmpty(str)) {
            return new Status(-2147482645, "propertyKey is null");
        }
        try {
            return ((ICarPropertyService) Spi.getService(this.mContext, ICarPropertyService.class)).setProperties(new CarPropertyValue[]{new CarPropertyValue(str, t)});
        } catch (Throwable th) {
            LogUtils.e(DiCarLog.TAG_CAR_PROPERTY, "invoke set property failed:", th);
            return new Status(-2147482648, "invoke setProperty remote failed");
        }
    }

    @Override // com.byd.car.property.ICarPropertyManager
    public <T> Status setProperties(String[] strArr, T[] tArr) {
        if (ObjectUtils.isEmpty(strArr) || ObjectUtils.isEmpty(tArr) || strArr.length != tArr.length) {
            return new Status(-2147482645, "propertyKeys and values must be same length");
        }
        try {
            ICarPropertyService iCarPropertyService = (ICarPropertyService) Spi.getService(this.mContext, ICarPropertyService.class);
            CarPropertyValue[] carPropertyValueArr = new CarPropertyValue[strArr.length];
            for (int i = 0; i < strArr.length; i++) {
                carPropertyValueArr[i] = new CarPropertyValue(strArr[i], tArr[i]);
            }
            return iCarPropertyService.setProperties(carPropertyValueArr);
        } catch (Throwable th) {
            LogUtils.e(DiCarLog.TAG_CAR_PROPERTY, "invoke set properties failed:", th);
            return new Status(-2147482648, "invoke set properties remote failed");
        }
    }

    @Override // com.byd.car.property.ICarPropertyManager
    public <V> Response<CarPropertyValue<V>> getProperty(String str) {
        if (TextUtils.isEmpty(str)) {
            return new Response<>(new Status(-2147482645, "propertyKey is null"), null);
        }
        try {
            return ((ICarPropertyService) Spi.getService(this.mContext, ICarPropertyService.class)).getProperty(str);
        } catch (Throwable th) {
            LogUtils.e(DiCarLog.TAG_CAR_PROPERTY, "invoke get property failed:", th);
            return new Response<>(new Status(-2147482648, "invoke get property remote failed"), null);
        }
    }

    @Override // com.byd.car.property.ICarPropertyManager
    public <V> Response<List<CarPropertyValue<V>>> getProperties(String... strArr) {
        if (ObjectUtils.isEmpty(strArr)) {
            return new Response<>(new Status(-2147482645, "propertiesKeys is null"), null);
        }
        try {
            return ((ICarPropertyService) Spi.getService(this.mContext, ICarPropertyService.class)).getProperties(strArr);
        } catch (Throwable th) {
            LogUtils.e(DiCarLog.TAG_CAR_PROPERTY, "invoke get properties failed:", th);
            return new Response<>(new Status(-2147482648, "invoke get properties remote failed"), null);
        }
    }

    @Override // com.byd.car.property.ICarPropertyManager
    public List<CarPropertyConfig> getPropertyConfigs(String... strArr) {
        if (ObjectUtils.isEmpty(strArr)) {
            return new ArrayList();
        }
        try {
            return ((ICarPropertyService) Spi.getService(this.mContext, ICarPropertyService.class)).getPropertyConfigs(strArr);
        } catch (Throwable th) {
            LogUtils.e(DiCarLog.TAG_CAR_PROPERTY, "invoke get property configs failed:", th);
            return new ArrayList();
        }
    }

    @Override // com.byd.car.property.ICarPropertyManager
    public void registerValueCallback(ValueCallback valueCallback, String... strArr) {
        if (ObjectUtils.isEmpty(strArr)) {
            return;
        }
        boolean z = Looper.myLooper() == Looper.getMainLooper();
        try {
            for (String str : strArr) {
                if (!TextUtils.isEmpty(str)) {
                    List<CallbackInfo> arrayList = this.mCallbacks.get(str);
                    if (CollectionUtils.isEmpty(arrayList)) {
                        arrayList = new ArrayList<>();
                    }
                    arrayList.add(new CallbackInfo(valueCallback, z));
                    this.mCallbacks.put(str, arrayList);
                }
            }
            ((ICarPropertyService) Spi.getService(this.mContext, ICarPropertyService.class)).registerValueCallback(this.mPropertyListener, strArr);
        } catch (Throwable th) {
            LogUtils.e(DiCarLog.TAG_CAR_PROPERTY, "fail to register value callback:", th);
        }
    }

    @Override // com.byd.car.property.ICarPropertyManager
    public void unregisterValueCallback(final ValueCallback valueCallback, String... strArr) {
        if (ObjectUtils.isEmpty(strArr)) {
            return;
        }
        try {
            for (String str : strArr) {
                if (!TextUtils.isEmpty(str)) {
                    List<CallbackInfo> list = this.mCallbacks.get(str);
                    if (!CollectionUtils.isEmpty(list)) {
                        list.removeIf(new Predicate() { // from class: com.byd.car.property.internal.-$$Lambda$CarPropertyManager$o9HtIERSBqMkWTfUQKQqSjvaBgM
                            @Override // java.util.function.Predicate
                            public final boolean test(Object obj) {
                                return CarPropertyManager.lambda$unregisterValueCallback$0(valueCallback, (CarPropertyManager.CallbackInfo) obj);
                            }
                        });
                    } else {
                        this.mCallbacks.remove(str);
                    }
                }
            }
            ((ICarPropertyService) Spi.getService(this.mContext, ICarPropertyService.class)).unregisterValueCallback(this.mPropertyListener, strArr);
        } catch (Throwable th) {
            LogUtils.e(DiCarLog.TAG_CAR_PROPERTY, "fail to unregister value callback:", th);
        }
    }

    static /* synthetic */ boolean lambda$unregisterValueCallback$0(ValueCallback valueCallback, CallbackInfo callbackInfo) {
        return callbackInfo.callback == valueCallback;
    }

    /* JADX INFO: Access modifiers changed from: private */
    class CarPropertyValueCallback extends ICarPropertyListener.Stub {
        private CarPropertyValueCallback() {
        }

        @Override // com.byd.car.property.ICarPropertyListener
        public void onEvent(String str, final Response response) throws RemoteException {
            try {
                for (final CallbackInfo callbackInfo : CarPropertyManager.this.mCallbacks.get(str)) {
                    if (callbackInfo.isMainThread) {
                        CarPropertyManager.this.mHandler.post(new Runnable() { // from class: com.byd.car.property.internal.-$$Lambda$CarPropertyManager$CarPropertyValueCallback$w-QgbqlRZXsYaSKYgSLLxnfPKX0
                            @Override // java.lang.Runnable
                            public final void run() {
                                callbackInfo.callback.onCallback(response);
                            }
                        });
                    } else {
                        callbackInfo.callback.onCallback(response);
                    }
                }
            } catch (Throwable th) {
                LogUtils.e(DiCarLog.TAG_CAR_PROPERTY, "fail to handle value callback:", th);
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    static class CallbackInfo {
        final ValueCallback callback;
        final boolean isMainThread;

        CallbackInfo(ValueCallback valueCallback, boolean z) {
            this.callback = valueCallback;
            this.isMainThread = z;
        }
    }
}
