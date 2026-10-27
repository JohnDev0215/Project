package com.ts.car.core.provider;

import android.content.Context;
import android.os.IBinder;
import com.ts.car.core.cache.ServiceScanCache;
import java.lang.reflect.InvocationTargetException;
import java.util.List;

/* JADX INFO: loaded from: classes.dex */
public class AnnotationServiceProvider implements IServiceProvider {
    @Override // com.ts.car.core.provider.IServiceProvider
    public <T> T getService(Class<T> clazz, Context context, IBinder binder) {
        List<ServiceScanCache.ClassInfo> infoList;
        ServiceScanCache scanCache = ServiceScanCache.getInstance();
        if (!scanCache.isScanned()) {
            synchronized (ServiceScanCache.class) {
                AnnotationServiceScanner scanner = new AnnotationServiceScanner();
                scanner.doScan(scanCache);
            }
        }
        if (!scanCache.isScanned() || (infoList = scanCache.getServiceClassInfo(clazz)) == null || infoList.isEmpty()) {
            return null;
        }
        for (ServiceScanCache.ClassInfo classInfo : infoList) {
            if (clazz.isAssignableFrom(classInfo.clazz)) {
                if (!isParametersMatched(classInfo)) {
                    return null;
                }
                Object retVal = createInstance(classInfo, context, null);
                if (retVal != null) {
                    return clazz.cast(retVal);
                }
            }
        }
        return null;
    }

    private Object createInstance(ServiceScanCache.ClassInfo classInfo, Context context, IBinder binder) {
        try {
            return classInfo.constructor.newInstance(context, binder);
        } catch (IllegalAccessException | InstantiationException | InvocationTargetException ex) {
            ex.printStackTrace();
            return null;
        }
    }

    private boolean isParametersMatched(ServiceScanCache.ClassInfo classInfo) {
        Class<?>[] param = classInfo.constructor.getParameterTypes();
        return param.length == 2 && param[0].isAssignableFrom(Context.class) && param[1].isAssignableFrom(IBinder.class);
    }
}
