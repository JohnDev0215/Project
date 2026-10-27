package com.byd.spi.factory;

import android.content.Context;
import androidx.annotation.NonNull;
import com.byd.foundation.annotation.ServiceImpl;
import com.byd.foundation.utils.LogUtils;
import com.byd.foundation.utils.ObjectUtils;
import com.byd.foundation.utils.ReflectUtil;
import com.byd.foundation.utils.Singleton;
import com.byd.spi.BuildConfig;
import java.lang.reflect.Constructor;
import java.util.HashMap;
import java.util.Map;

/* JADX INFO: loaded from: classes2.dex */
public class ServiceFactory {
    private static Singleton<ServiceFactory> gDefault = new Singleton<ServiceFactory>() { // from class: com.byd.spi.factory.ServiceFactory.1
        /* JADX INFO: Access modifiers changed from: protected */
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // com.byd.foundation.utils.Singleton
        public ServiceFactory create() {
            return new ServiceFactory();
        }
    };
    private final Map<String, Object> mProviderCache;

    private ServiceFactory() {
        this.mProviderCache = new HashMap();
    }

    private Constructor<?> getConstructorWithContext(Class<?> cls) {
        try {
            return cls.getDeclaredConstructor(Context.class);
        } catch (Throwable unused) {
            LogUtils.w(BuildConfig.TAG, "类：" + cls.getCanonicalName() + "无带Context参数的构造函数");
            return null;
        }
    }

    private boolean isSingleton(Class<?> cls) {
        ServiceImpl serviceImpl = (ServiceImpl) cls.getAnnotation(ServiceImpl.class);
        return serviceImpl != null && serviceImpl.singleton();
    }

    public static ServiceFactory with() {
        return gDefault.get();
    }

    public Object createService(Context context, @NonNull Class<?> cls) {
        String canonicalName = cls.getCanonicalName();
        boolean zIsSingleton = isSingleton(cls);
        synchronized (this) {
            Object objNewInstance = this.mProviderCache.get(canonicalName);
            if (zIsSingleton && objNewInstance != null) {
                return objNewInstance;
            }
            try {
                Constructor<?> constructorWithContext = getConstructorWithContext(cls);
                objNewInstance = !ObjectUtils.isEmpty(constructorWithContext) ? constructorWithContext.newInstance(context) : ReflectUtil.newInstance(cls);
                if (zIsSingleton) {
                    this.mProviderCache.put(canonicalName, objNewInstance);
                }
            } catch (Throwable th) {
                LogUtils.w(BuildConfig.TAG, "创建服务实例发生异常:", th);
            }
            return objNewInstance;
        }
    }
}
