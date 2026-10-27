package com.byd.car.property.internal;

import android.content.Context;
import android.text.TextUtils;
import com.byd.car.DiCarLog;
import com.byd.car.property.CarPropertyConfig;
import com.byd.car.property.CarPropertyValue;
import com.byd.car.property.ICarPropertyListener;
import com.byd.car.property.ICarPropertyManager;
import com.byd.car.property.ICarPropertyService;
import com.byd.datasource.feature.Response;
import com.byd.datasource.feature.Status;
import com.byd.spi.Spi;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import spi.ca;
import spi.d8;
import spi.i6;
import spi.nc;

/* JADX INFO: loaded from: classes2.dex */
@ca(singleton = true)
public class CarPropertyManager implements ICarPropertyManager {
    private Context mContext;
    public Map<String, List<nc>> mCallbacks = new HashMap();
    private CarPropertyValueCallback mPropertyListener = new CarPropertyValueCallback();

    public class CarPropertyValueCallback extends ICarPropertyListener.Stub {
        private CarPropertyValueCallback() {
        }

        @Override // com.byd.car.property.ICarPropertyListener
        public void onEvent(String str, Response response) {
            try {
                Iterator<nc> it = CarPropertyManager.this.mCallbacks.get(str).iterator();
                while (it.hasNext()) {
                    it.next().a();
                }
            } catch (Throwable th) {
                i6.b(DiCarLog.TAG_CAR_PROPERTY, "fail to handle value callback:", th);
            }
        }
    }

    public CarPropertyManager(Context context) {
        this.mContext = context.getApplicationContext();
    }

    @Override // com.byd.car.property.ICarPropertyManager
    public <V> Response<List<CarPropertyValue<V>>> getProperties(String... strArr) {
        if (d8.a(strArr)) {
            return new Response<>(new Status(-2147482645, "propertiesKeys is null"), null);
        }
        try {
            return ((ICarPropertyService) Spi.getService(this.mContext, ICarPropertyService.class)).getProperties(strArr);
        } catch (Throwable th) {
            i6.b(DiCarLog.TAG_CAR_PROPERTY, "invoke get properties failed:", th);
            return new Response<>(new Status(-2147482648, "invoke get properties remote failed"), null);
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
            i6.b(DiCarLog.TAG_CAR_PROPERTY, "invoke get property failed:", th);
            return new Response<>(new Status(-2147482648, "invoke get property remote failed"), null);
        }
    }

    @Override // com.byd.car.property.ICarPropertyManager
    public List<CarPropertyConfig> getPropertyConfigs(String... strArr) {
        if (d8.a(strArr)) {
            return new ArrayList();
        }
        try {
            return ((ICarPropertyService) Spi.getService(this.mContext, ICarPropertyService.class)).getPropertyConfigs(strArr);
        } catch (Throwable th) {
            i6.b(DiCarLog.TAG_CAR_PROPERTY, "invoke get property configs failed:", th);
            return new ArrayList();
        }
    }

    @Override // com.byd.car.property.ICarPropertyManager
    public void registerValueCallback(nc ncVar, String... strArr) {
        if (d8.a(strArr)) {
            return;
        }
        try {
            for (String str : strArr) {
                if (!TextUtils.isEmpty(str)) {
                    List<nc> arrayList = this.mCallbacks.get(str);
                    if (arrayList == null || arrayList.size() == 0) {
                        arrayList = new ArrayList<>();
                    }
                    arrayList.add(ncVar);
                    this.mCallbacks.put(str, arrayList);
                }
            }
            ((ICarPropertyService) Spi.getService(this.mContext, ICarPropertyService.class)).registerValueCallback(this.mPropertyListener, strArr);
        } catch (Throwable th) {
            i6.b(DiCarLog.TAG_CAR_PROPERTY, "fail to register value callback:", th);
        }
    }

    @Override // com.byd.car.property.ICarPropertyManager
    public <T> Status setProperties(String[] strArr, T[] tArr) {
        if (d8.a(strArr) || d8.a(tArr) || strArr.length != tArr.length) {
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
            i6.b(DiCarLog.TAG_CAR_PROPERTY, "invoke set properties failed:", th);
            return new Status(-2147482648, "invoke set properties remote failed");
        }
    }

    @Override // com.byd.car.property.ICarPropertyManager
    public <T> Status setProperty(String str, T t) {
        if (TextUtils.isEmpty(str)) {
            return new Status(-2147482645, "propertyKey is null");
        }
        try {
            return ((ICarPropertyService) Spi.getService(this.mContext, ICarPropertyService.class)).setProperties(new CarPropertyValue[]{new CarPropertyValue(str, t)});
        } catch (Throwable th) {
            i6.b(DiCarLog.TAG_CAR_PROPERTY, "invoke set property failed:", th);
            return new Status(-2147482648, "invoke setProperty remote failed");
        }
    }

    @Override // com.byd.car.property.ICarPropertyManager
    public void unregisterValueCallback(nc ncVar, String... strArr) {
        if (d8.a(strArr)) {
            return;
        }
        try {
            for (String str : strArr) {
                if (!TextUtils.isEmpty(str)) {
                    List<nc> list = this.mCallbacks.get(str);
                    if (list == null || list.size() == 0) {
                        this.mCallbacks.remove(str);
                    } else {
                        list.remove(ncVar);
                    }
                }
            }
            ((ICarPropertyService) Spi.getService(this.mContext, ICarPropertyService.class)).unregisterValueCallback(this.mPropertyListener, strArr);
        } catch (Throwable th) {
            i6.b(DiCarLog.TAG_CAR_PROPERTY, "fail to unregister value callback:", th);
        }
    }
}
