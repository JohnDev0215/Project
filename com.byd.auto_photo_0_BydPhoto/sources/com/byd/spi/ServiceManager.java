package com.byd.spi;

import android.content.Context;
import android.os.IInterface;
import androidx.annotation.NonNull;
import com.byd.foundation.annotation.ServiceImpl;
import com.byd.foundation.utils.LogUtils;
import com.byd.foundation.utils.ObjectUtils;
import com.byd.foundation.utils.Singleton;
import com.byd.spi.ipc.RemoteServiceManager;
import com.byd.spi.service.AssetsServiceLoader;
import com.byd.spi.service.ServiceMatcher;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/* JADX INFO: loaded from: classes2.dex */
public class ServiceManager {
    private static Singleton<ServiceManager> gDefault = new Singleton<ServiceManager>() { // from class: com.byd.spi.ServiceManager.1
        /* JADX INFO: Access modifiers changed from: protected */
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // com.byd.foundation.utils.Singleton
        public ServiceManager create() {
            return new ServiceManager();
        }
    };
    private ReentrantReadWriteLock mLock = new ReentrantReadWriteLock();
    private ReentrantReadWriteLock mInstanceLock = new ReentrantReadWriteLock();
    private Map<Class, ArrayList<ServiceInfo>> mServiceCache = new HashMap();
    private Map<Class, LinkedHashSet<Object>> mRegisterServices = new HashMap();

    private <T> T loadRegisterService(Context context, Class<T> cls) {
        this.mInstanceLock.readLock().lock();
        try {
            LinkedHashSet<Object> linkedHashSet = this.mRegisterServices.get(cls);
            if (linkedHashSet != null && linkedHashSet.size() > 0) {
                return (T) linkedHashSet.iterator().next();
            }
            this.mLock.readLock().lock();
            try {
                ArrayList<ServiceInfo> arrayList = this.mServiceCache.get(cls);
                if (arrayList == null || arrayList.size() <= 0) {
                    return null;
                }
                T t = (T) arrayList.get(0).createService(context);
                ServiceImpl serviceImpl = (ServiceImpl) t.getClass().getAnnotation(ServiceImpl.class);
                if (!ObjectUtils.isEmpty(serviceImpl) && serviceImpl.singleton()) {
                    addService(cls, t);
                }
                return t;
            } finally {
                this.mLock.readLock().unlock();
            }
        } finally {
            this.mInstanceLock.readLock().unlock();
        }
    }

    private <T> List<T> loadRegisterServices(Context context, Class<T> cls) {
        this.mInstanceLock.readLock().lock();
        try {
            LinkedHashSet<Object> linkedHashSet = this.mRegisterServices.get(cls);
            if (linkedHashSet != null && linkedHashSet.size() > 0) {
                return new ArrayList(linkedHashSet);
            }
            this.mLock.readLock().lock();
            try {
                ArrayList<ServiceInfo> arrayList = this.mServiceCache.get(cls);
                this.mLock.readLock().unlock();
                ArrayList arrayList2 = new ArrayList();
                if (arrayList != null && arrayList.size() > 0) {
                    Iterator<ServiceInfo> it = arrayList.iterator();
                    while (it.hasNext()) {
                        try {
                            Object objCreateService = it.next().createService(context);
                            ServiceImpl serviceImpl = (ServiceImpl) objCreateService.getClass().getAnnotation(ServiceImpl.class);
                            if (!ObjectUtils.isEmpty(serviceImpl) && serviceImpl.singleton()) {
                                addService(cls, objCreateService);
                            }
                            arrayList2.add(objCreateService);
                        } catch (Throwable th) {
                            LogUtils.e(BuildConfig.TAG, "加载服务时发生异常:", th);
                        }
                    }
                }
                if (arrayList2.size() > 0) {
                    return arrayList2;
                }
                return null;
            } catch (Throwable th2) {
                this.mLock.readLock().unlock();
                throw th2;
            }
        } finally {
            this.mInstanceLock.readLock().unlock();
        }
    }

    public static ServiceManager with() {
        return gDefault.get();
    }

    public void addService(@NonNull Class cls, @NonNull Class cls2) {
        this.mLock.writeLock().lock();
        try {
            ArrayList<ServiceInfo> arrayList = this.mServiceCache.get(cls);
            if (arrayList == null) {
                arrayList = new ArrayList<>();
                this.mServiceCache.put(cls, arrayList);
            }
            arrayList.add(new ServiceInfo(cls, cls2));
        } finally {
            this.mLock.writeLock().unlock();
        }
    }

    public void addService(@NonNull Class cls, @NonNull Object obj) {
        this.mInstanceLock.writeLock().lock();
        try {
            LinkedHashSet<Object> linkedHashSet = this.mRegisterServices.get(cls);
            if (linkedHashSet == null) {
                linkedHashSet = new LinkedHashSet<>();
                this.mRegisterServices.put(cls, linkedHashSet);
            }
            linkedHashSet.add(obj);
        } finally {
            this.mInstanceLock.writeLock().unlock();
        }
    }

    public <T> T getService(@NonNull Context context, @NonNull Class<T> cls) {
        if (!cls.isInterface()) {
            throw new RuntimeException(cls.getCanonicalName() + "is not a interface");
        }
        T t = (T) ServiceMatcher.getService(context, cls);
        if (t != null) {
            return t;
        }
        T t2 = (T) loadRegisterService(context, cls);
        if (t2 != null) {
            return t2;
        }
        T t3 = (T) AssetsServiceLoader.with().loadService(context, cls);
        if (t3 != null) {
            return t3;
        }
        if (IInterface.class.isAssignableFrom(cls)) {
            return (T) RemoteServiceManager.get().getRemoteService(context, cls);
        }
        return null;
    }

    public <T> List<T> getServices(@NonNull Context context, @NonNull Class<T> cls) {
        if (!cls.isInterface()) {
            throw new RuntimeException(cls.getCanonicalName() + "is not a interface");
        }
        List<T> services = ServiceMatcher.getServices(context, cls);
        if (services.size() > 0) {
            return services;
        }
        List<T> listLoadRegisterServices = loadRegisterServices(context, cls);
        return (listLoadRegisterServices == null || listLoadRegisterServices.size() <= 0) ? AssetsServiceLoader.with().loadServices(context, cls) : listLoadRegisterServices;
    }
}
