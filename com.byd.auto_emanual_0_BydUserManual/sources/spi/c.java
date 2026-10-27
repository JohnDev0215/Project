package spi;

import android.content.Context;
import android.os.IInterface;
import com.byd.foundation.annotation.ServiceImpl;
import com.byd.foundation.utils.LogUtils;
import com.byd.foundation.utils.ObjectUtils;
import com.byd.foundation.utils.Singleton;
import com.byd.spi.service.AssetsServiceLoader;
import com.byd.spi.service.ServiceMatcher;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/* JADX INFO: compiled from: ServiceManager.java */
/* JADX INFO: loaded from: classes.dex */
public class c {
    private static Singleton<c> e = new a();
    private ReentrantReadWriteLock a = new ReentrantReadWriteLock();
    private ReentrantReadWriteLock b = new ReentrantReadWriteLock();
    private Map<Class, ArrayList<b>> c = new HashMap();
    private Map<Class, LinkedHashSet<Object>> d = new HashMap();

    /* JADX INFO: compiled from: ServiceManager.java */
    public static class a extends Singleton<c> {
        @Override // com.byd.foundation.utils.Singleton
        /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
        public c create() {
            return new c();
        }
    }

    public static c a() {
        return e.get();
    }

    private <T> T c(Context context, Class<T> cls) {
        this.b.readLock().lock();
        try {
            LinkedHashSet<Object> linkedHashSet = this.d.get(cls);
            if (linkedHashSet != null && linkedHashSet.size() > 0) {
                return (T) linkedHashSet.iterator().next();
            }
            this.a.readLock().lock();
            try {
                ArrayList<b> arrayList = this.c.get(cls);
                if (arrayList == null || arrayList.size() <= 0) {
                    return null;
                }
                T t = (T) arrayList.get(0).a(context);
                ServiceImpl serviceImpl = (ServiceImpl) t.getClass().getAnnotation(ServiceImpl.class);
                if (!ObjectUtils.isEmpty(serviceImpl) && serviceImpl.singleton()) {
                    a(cls, t);
                }
                return t;
            } finally {
                this.a.readLock().unlock();
            }
        } finally {
            this.b.readLock().unlock();
        }
    }

    private <T> List<T> d(Context context, Class<T> cls) {
        this.b.readLock().lock();
        try {
            LinkedHashSet<Object> linkedHashSet = this.d.get(cls);
            if (linkedHashSet != null && linkedHashSet.size() > 0) {
                return new ArrayList(linkedHashSet);
            }
            this.a.readLock().lock();
            try {
                ArrayList<b> arrayList = this.c.get(cls);
                this.a.readLock().unlock();
                ArrayList arrayList2 = new ArrayList();
                if (arrayList != null && arrayList.size() > 0) {
                    Iterator<b> it = arrayList.iterator();
                    while (it.hasNext()) {
                        try {
                            Object objA = it.next().a(context);
                            ServiceImpl serviceImpl = (ServiceImpl) objA.getClass().getAnnotation(ServiceImpl.class);
                            if (!ObjectUtils.isEmpty(serviceImpl) && serviceImpl.singleton()) {
                                a(cls, objA);
                            }
                            arrayList2.add(objA);
                        } catch (Throwable th) {
                            LogUtils.e(spi.a.e, "spi load services from registry failed:" + cls.getName(), th);
                        }
                    }
                }
                if (arrayList2.size() > 0) {
                    return arrayList2;
                }
                return null;
            } catch (Throwable th2) {
                this.a.readLock().unlock();
                throw th2;
            }
        } finally {
            this.b.readLock().unlock();
        }
    }

    public <T> List<T> b(Context context, Class<T> cls) {
        if (!cls.isInterface()) {
            throw new RuntimeException(cls.getCanonicalName() + "is not a interface");
        }
        List<T> services = ServiceMatcher.getServices(context, cls);
        if (services.size() > 0) {
            LogUtils.i(spi.a.e, "spi get services from annotation: " + cls.getCanonicalName() + " size:" + services.size());
            return services;
        }
        List<T> listD = d(context, cls);
        if (listD != null && listD.size() > 0) {
            LogUtils.i(spi.a.e, "spi get services from registry: " + cls.getCanonicalName() + " size:" + listD.size());
            return listD;
        }
        List<T> listLoadServices = AssetsServiceLoader.with().loadServices(context, cls);
        if (listLoadServices == null || listLoadServices.size() <= 0) {
            LogUtils.i(spi.a.e, "spi get services failed: " + cls.getCanonicalName());
            return null;
        }
        LogUtils.i(spi.a.e, "spi get services from config: " + cls.getCanonicalName() + " size:" + listLoadServices.size());
        return listLoadServices;
    }

    public <T> T a(Context context, Class<T> cls) {
        if (!cls.isInterface()) {
            throw new RuntimeException(cls.getCanonicalName() + "is not a interface");
        }
        T t = (T) ServiceMatcher.getService(context, cls);
        if (t != null) {
            LogUtils.i(spi.a.e, "spi get service from annotation: " + cls.getCanonicalName() + " instance:" + t);
            return t;
        }
        T t2 = (T) c(context, cls);
        if (t2 != null) {
            LogUtils.i(spi.a.e, "spi get service from registry: " + cls.getCanonicalName() + " instance:" + t2);
            return t2;
        }
        T t3 = (T) AssetsServiceLoader.with().loadService(context, cls);
        if (t3 != null) {
            LogUtils.i(spi.a.e, "spi get service from config: " + cls.getCanonicalName() + " instance:" + t3);
            return t3;
        }
        if (IInterface.class.isAssignableFrom(cls)) {
            LogUtils.i(spi.a.e, "spi get service from remote: " + cls.getCanonicalName());
            return (T) e.a().a(context, cls);
        }
        LogUtils.i(spi.a.e, "spi get service failed: " + cls.getCanonicalName());
        return null;
    }

    public void a(Class cls, Class cls2) {
        this.a.writeLock().lock();
        try {
            ArrayList<b> arrayList = this.c.get(cls);
            if (arrayList == null) {
                arrayList = new ArrayList<>();
                this.c.put(cls, arrayList);
            }
            arrayList.add(new b(cls, cls2));
            LogUtils.i(spi.a.e, "spi add service: " + cls.getCanonicalName() + " size:" + arrayList.size());
        } finally {
            this.a.writeLock().unlock();
        }
    }

    public void a(Class cls, Object obj) {
        this.b.writeLock().lock();
        try {
            LinkedHashSet<Object> linkedHashSet = this.d.get(cls);
            if (linkedHashSet == null) {
                linkedHashSet = new LinkedHashSet<>();
                this.d.put(cls, linkedHashSet);
            }
            linkedHashSet.add(obj);
            LogUtils.i(spi.a.e, "spi add instance: " + cls.getCanonicalName() + " size:" + linkedHashSet.size());
        } finally {
            this.b.writeLock().unlock();
        }
    }
}
