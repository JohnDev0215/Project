package com.byd.shareddata;

import android.content.ComponentName;
import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Looper;
import android.os.Process;
import android.util.AndroidRuntimeException;
import android.util.Log;
import b.b.l.d;
import b.b.l.u.a;
import b.b.l.u.b;
import b.b.l.u.d;
import b.b.l.u.g;
import b.b.l.u.i;
import b.b.l.u.j;
import b.b.l.u.l;
import com.byd.mvcadapter.Reply;
import com.byd.mvcadapter.refect.RefUtils;
import external.org.apache.commons.lang3.ObjectUtils;
import java.lang.annotation.Annotation;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.ParameterizedType;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.locks.ReentrantLock;

/* JADX INFO: loaded from: classes2.dex */
public class SharedDataManager {
    private static final String TAG = "SharedDataManager";
    private static AtomicInteger connectCount;
    private static String defaultPackage;
    private static boolean isUseSystemService;
    private static ArrayList<DataMonitorInterfaceWrapper> mDataMonitorInterfaces;
    private static ArrayList<DataMonitorImpl<?, ?>> mDataMonitors;
    private static j mRpcRequest;
    public static b sInterceptInvoker;
    private static HashMap<Class<?>, String> sInterfacePackageMap = new HashMap<>();
    private static HashMap<Class<?>, Object> sInterfaceInstanceMap = new HashMap<>();
    private static HashMap<Object, d> sInstanceConnecterMap = new HashMap<>();
    private static HashMap<String, d> sPackageConnecterMap = new HashMap<>();
    private static ReentrantLock connectLock = new ReentrantLock();

    public static class DataMonitorImpl<T, K> implements DataMonitor, l {
        private boolean alwaysNotify;
        private ClassLoader classLoader;
        private Handler defaultHandler;
        private Method method;
        private K[] monitorArgs;
        private T oldValue;
        private HashMap<K, T> oldValues;
        private Object proxy;
        private DataUpdateListener<T> updateListener;

        public class InnerRunnable implements Runnable {
            private K arg;
            private Method method;
            private T newValue;
            private T oldValue;

            public InnerRunnable(Method method, T t, T t2, K k) {
                this.method = method;
                this.oldValue = t;
                this.newValue = t2;
                this.arg = k;
            }

            @Override // java.lang.Runnable
            public void run() {
                if (DataMonitorImpl.this.updateListener instanceof DataWithArgUpdateListener) {
                    ((DataWithArgUpdateListener) DataMonitorImpl.this.updateListener).dataValueChanged(this.method.getName(), this.arg, this.oldValue, this.newValue);
                } else {
                    DataMonitorImpl.this.updateListener.dataValueChanged(this.method.getName(), this.oldValue, this.newValue);
                }
            }
        }

        public DataMonitorImpl(Object obj, Method method, DataUpdateListener<T> dataUpdateListener, boolean z) {
            if (Looper.myLooper() == null) {
                Looper.prepare();
            }
            this.defaultHandler = new Handler();
            this.proxy = obj;
            this.method = method;
            this.updateListener = dataUpdateListener;
            this.classLoader = dataUpdateListener.getClass().getClassLoader();
            this.alwaysNotify = z;
            if (dataUpdateListener instanceof DataWithArgUpdateListener) {
                this.oldValues = new HashMap<>();
                this.monitorArgs = (K[]) ((DataWithArgUpdateListener) dataUpdateListener).getMonitorArgs();
            }
            dataValueChanged();
        }

        private Object getDataValue(Object... objArr) {
            try {
                return this.method.invoke(this.proxy, objArr);
            } catch (Throwable th) {
                Log.w(SharedDataManager.TAG, Log.getStackTraceString(th));
                return RefUtils.v(this.method.getReturnType());
            }
        }

        private boolean isMonitorArg(K k) {
            K[] kArr = this.monitorArgs;
            if (kArr.length == 0) {
                return true;
            }
            for (K k2 : kArr) {
                if (ObjectUtils.a(k2, k)) {
                    return true;
                }
            }
            return false;
        }

        @Override // com.byd.shareddata.DataMonitor
        public void dataValueChanged() {
            if (this.monitorArgs == null) {
                dataValueChangedWithValue(getDataValue(new Object[0]), new Object[0]);
                return;
            }
            int i = 0;
            while (true) {
                K[] kArr = this.monitorArgs;
                if (i >= kArr.length) {
                    return;
                }
                dataValueChangedWithValue(getDataValue(kArr[i]), this.monitorArgs[i]);
                i++;
            }
        }

        /* JADX WARN: Multi-variable type inference failed */
        @Override // com.byd.shareddata.DataMonitor
        public void dataValueChangedWithValue(Object obj, Object... objArr) {
            Object obj2;
            if (this.oldValues != null) {
                obj2 = objArr[0];
                if (!isMonitorArg(obj2)) {
                    return;
                } else {
                    this.oldValue = this.oldValues.get(obj2);
                }
            } else {
                obj2 = null;
            }
            if (this.alwaysNotify || !ObjectUtils.a(obj, this.oldValue)) {
                Handler handler = this.updateListener.getHandler();
                if (handler == null) {
                    handler = this.defaultHandler;
                }
                handler.post(new InnerRunnable(this.method, this.oldValue, obj, obj2));
            }
            this.oldValue = obj;
            HashMap<K, T> map = this.oldValues;
            if (map != null) {
                map.put((K) obj2, (T) obj);
            }
        }

        public ClassLoader getClassLoader() {
            return this.classLoader;
        }
    }

    public interface DataMonitorInterface {
        void addDataMonitor(int i, String str, String str2, DataMonitor dataMonitor);

        void removeDataMonitor(int i, String str, String str2, DataMonitor dataMonitor);
    }

    public static class DataMonitorInterfaceWrapper {
        public DataMonitorInterface dataMonitorInterface;
        public Object proxy;

        public DataMonitorInterfaceWrapper(Object obj, DataMonitorInterface dataMonitorInterface) {
            this.proxy = obj;
            this.dataMonitorInterface = dataMonitorInterface;
        }
    }

    static {
        defaultPackage = "com.byd.datashared";
        isUseSystemService = false;
        if (((IBinder) RefUtils.b(RefUtils.c("android.os.ServiceManager", null), "getService", "shared_data")) != null) {
            isUseSystemService = true;
        }
        if (isUseSystemService) {
            defaultPackage = "SystemService_shared_data";
        }
        synchronized (sInterfacePackageMap) {
        }
        sInterceptInvoker = new b() { // from class: com.byd.shareddata.SharedDataManager.3
            @Override // b.b.l.u.b
            public Reply invoke(i iVar, g gVar) {
                int length;
                String strC = gVar.c();
                Object[] objArrD = gVar.d();
                if (RefUtils.k(DataUpdateInterface.class, strC, objArrD) != null) {
                    Object objA = gVar.a();
                    Object[] objArr = new Object[objArrD.length + 1];
                    objArr[0] = objA;
                    System.arraycopy(objArrD, 0, objArr, 1, objArrD.length);
                    Reply reply = new Reply();
                    reply.i(RefUtils.b(SharedDataManager.class, strC, objArr));
                    return reply;
                }
                Annotation[] declaredAnnotations = gVar.b().getDeclaredAnnotations();
                if (declaredAnnotations == null || (length = declaredAnnotations.length) <= 0) {
                    return null;
                }
                Bundle[] bundleArr = new Bundle[length];
                for (int i = 0; i < length; i++) {
                    bundleArr[i] = SharedDataManager.AnnotationToBundle(declaredAnnotations[i]);
                }
                iVar.n(bundleArr);
                return null;
            }
        };
        mDataMonitors = new ArrayList<>();
        mDataMonitorInterfaces = new ArrayList<>();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static Bundle AnnotationToBundle(Annotation annotation) {
        Bundle bundle = new Bundle();
        for (Method method : annotation.getClass().getDeclaredMethods()) {
            String name = method.getName();
            if (!"equals".equals(name) && !"hashCode".equals(name) && !"toString".equals(name)) {
                try {
                    bundle.putString(name, method.invoke(annotation, new Object[0]).toString());
                } catch (IllegalAccessException | InvocationTargetException unused) {
                }
            }
        }
        return bundle;
    }

    public static <T> void addDataUpdateListener(Object obj, String str, DataUpdateListener<T> dataUpdateListener) {
        addDataUpdateListener(obj, str, (DataUpdateListener) dataUpdateListener, false);
    }

    private static void addInstanceConnecter(Object obj, d dVar) {
        synchronized (sInstanceConnecterMap) {
            sInstanceConnecterMap.put(obj, dVar);
        }
    }

    public static void addInterface(Class<?> cls) {
        addInterface(cls, defaultPackage);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static void addPackageConnecter(String str, d dVar) {
        synchronized (sPackageConnecterMap) {
            sPackageConnecterMap.put(str, dVar);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static void decConnectCount(AtomicInteger atomicInteger, final InitCallback initCallback) {
        if (atomicInteger.decrementAndGet() == 0) {
            final AtomicInteger atomicInteger2 = new AtomicInteger(sInterfacePackageMap.size());
            for (final Map.Entry<Class<?>, String> entry : sInterfacePackageMap.entrySet()) {
                if (sInterfaceInstanceMap.get(entry.getKey()) != null) {
                    decInitCount(atomicInteger2, initCallback);
                } else {
                    getInstance(new InstanceCreator() { // from class: com.byd.shareddata.SharedDataManager.2
                        @Override // com.byd.shareddata.InstanceCreator
                        public void create(Object obj) {
                            SharedDataManager.sInterfaceInstanceMap.put(entry.getKey(), obj);
                            SharedDataManager.decInitCount(atomicInteger2, initCallback);
                        }
                    }, entry.getKey());
                }
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static void decInitCount(AtomicInteger atomicInteger, InitCallback initCallback) {
        if (atomicInteger.decrementAndGet() == 0) {
            if (initCallback != null) {
                initCallback.init();
            }
            connectLock.unlock();
        }
    }

    public static Method findMethod(Class<?> cls, String str, Class<?>... clsArr) {
        return RefUtils.j(cls, str, clsArr);
    }

    private static d getConnecter(Object obj) {
        d dVar;
        synchronized (sInstanceConnecterMap) {
            dVar = sInstanceConnecterMap.get(obj);
        }
        return dVar;
    }

    private static DataMonitorImpl<?, ?> getDataMonitor(Object obj, Method method, DataUpdateListener<?> dataUpdateListener, boolean z) {
        synchronized (mDataMonitors) {
            for (DataMonitorImpl<?, ?> dataMonitorImpl : mDataMonitors) {
                if (((DataMonitorImpl) dataMonitorImpl).proxy == obj && ((DataMonitorImpl) dataMonitorImpl).method == method && ((DataMonitorImpl) dataMonitorImpl).updateListener == dataUpdateListener && ((DataMonitorImpl) dataMonitorImpl).alwaysNotify == z) {
                    return dataMonitorImpl;
                }
            }
            return null;
        }
    }

    private static DataMonitorInterface getDataMonitorInterface(Object obj) {
        synchronized (mDataMonitorInterfaces) {
            for (DataMonitorInterfaceWrapper dataMonitorInterfaceWrapper : mDataMonitorInterfaces) {
                if (dataMonitorInterfaceWrapper.proxy == obj) {
                    return dataMonitorInterfaceWrapper.dataMonitorInterface;
                }
            }
            d connecter = getConnecter(obj);
            if (connecter == null) {
                return null;
            }
            DataMonitorInterface dataMonitorInterface = (DataMonitorInterface) connecter.l(DataMonitorInterface.class, "com.byd.shareddata.SharedDataProvider$DataMonitorManager", new Object[0]);
            synchronized (mDataMonitorInterfaces) {
                mDataMonitorInterfaces.add(new DataMonitorInterfaceWrapper(obj, dataMonitorInterface));
            }
            return dataMonitorInterface;
        }
    }

    public static <T> T getInstance(Class<T> cls) {
        T t;
        synchronized (sInterfaceInstanceMap) {
            t = (T) sInterfaceInstanceMap.get(cls);
        }
        return t == null ? (T) getInstance(getConnecter(getPackageName(cls)), cls) : t;
    }

    private static String getPackageName(Class<?> cls) {
        synchronized (sInterfacePackageMap) {
            for (Map.Entry<Class<?>, String> entry : sInterfacePackageMap.entrySet()) {
                if (entry.getKey() == cls) {
                    return entry.getValue();
                }
            }
            Class<?>[] interfaces = cls.getInterfaces();
            if (interfaces.length <= 0) {
                return null;
            }
            return getPackageName(interfaces[0]);
        }
    }

    public static synchronized void init(Context context) {
        init(context, null);
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Multi-variable type inference failed */
    public static <T> void instanceCreate(d dVar, Class<T> cls, InstanceCreator<T> instanceCreator) {
        instanceCreator.create(getInstance(dVar, cls));
        connectLock.unlock();
    }

    public static void release() {
        if (isUseSystemService || mRpcRequest == null) {
            return;
        }
        synchronized (sInstanceConnecterMap) {
            Iterator<Map.Entry<Object, d>> it = sInstanceConnecterMap.entrySet().iterator();
            while (it.hasNext()) {
                removeDataUpdateListener(it.next().getKey());
            }
        }
        synchronized (sPackageConnecterMap) {
            Iterator<Map.Entry<String, d>> it2 = sPackageConnecterMap.entrySet().iterator();
            while (it2.hasNext()) {
                String key = it2.next().getKey();
                if (mRpcRequest.G(key)) {
                    mRpcRequest.E(key);
                    sPackageConnecterMap.remove(key);
                }
            }
        }
        mRpcRequest = null;
        sInterfaceInstanceMap.clear();
        sInstanceConnecterMap.clear();
        sPackageConnecterMap.clear();
    }

    public static void removeDataUpdateListener(Object obj, String str) {
        removeDataUpdateListener(obj, RefUtils.j(obj.getClass(), str, new Class[0]));
    }

    public static <T> void addDataUpdateListener(Object obj, String str, DataUpdateListener<T> dataUpdateListener, boolean z) {
        Method methodJ;
        if (dataUpdateListener instanceof DataWithArgUpdateListener) {
            Object[] monitorArgs = ((DataWithArgUpdateListener) dataUpdateListener).getMonitorArgs();
            methodJ = (monitorArgs == null || monitorArgs.length == 0) ? RefUtils.n(obj.getClass(), str) : RefUtils.k(obj.getClass(), str, monitorArgs[0]);
        } else {
            methodJ = RefUtils.j(obj.getClass(), str, new Class[0]);
        }
        if (methodJ != null) {
            addDataUpdateListener(obj, methodJ, dataUpdateListener, z);
            return;
        }
        throw new AndroidRuntimeException("Can't find method " + str + " in " + obj);
    }

    public static void addInterface(Class<?> cls, String str) {
        synchronized (sInterfacePackageMap) {
            sInterfacePackageMap.put(cls, str);
        }
    }

    public static synchronized void init(Context context, final InitCallback initCallback) {
        final d dVarC;
        if (mRpcRequest == null) {
            mRpcRequest = new j(context);
        }
        HashSet<String> hashSet = new HashSet();
        if (isUseSystemService) {
            hashSet.add("SystemService_shared_data");
        } else {
            Iterator<Map.Entry<Class<?>, String>> it = sInterfacePackageMap.entrySet().iterator();
            while (it.hasNext()) {
                hashSet.add(it.next().getValue());
            }
        }
        connectCount = new AtomicInteger(hashSet.size());
        connectLock.lock();
        for (final String str : hashSet) {
            if (getConnecter(str) != null) {
                decConnectCount(connectCount, initCallback);
            } else {
                if (isUseSystemService) {
                    dVarC = mRpcRequest.D("shared_data", null);
                } else {
                    dVarC = mRpcRequest.C(str, null);
                }
                final AtomicInteger atomicInteger = connectCount;
                dVarC.p("SharedData").d(new d.f() { // from class: com.byd.shareddata.SharedDataManager.1
                    @Override // b.b.l.d.f
                    public void connected(ComponentName componentName, IInterface iInterface) {
                        SharedDataManager.addPackageConnecter(str, dVarC);
                        SharedDataManager.decConnectCount(atomicInteger, initCallback);
                    }

                    @Override // b.b.l.d.f
                    public void disconnected(ComponentName componentName, IInterface iInterface) {
                    }
                });
                if (isUseSystemService) {
                    dVarC.i();
                }
            }
        }
    }

    public static void removeDataUpdateListener(Object obj, Method method) {
        for (DataMonitorImpl<?, ?> dataMonitorImpl : getDataMonitor(obj, method)) {
            synchronized (mDataMonitors) {
                mDataMonitors.remove(dataMonitorImpl);
            }
            getDataMonitorInterface(((DataMonitorImpl) dataMonitorImpl).proxy).removeDataMonitor(Process.myPid(), ((DataMonitorImpl) dataMonitorImpl).method.getName(), ((DataMonitorImpl) dataMonitorImpl).method.getReturnType().getName(), dataMonitorImpl);
        }
    }

    private static b.b.l.u.d getConnecter(String str) {
        b.b.l.u.d dVar;
        synchronized (sPackageConnecterMap) {
            dVar = sPackageConnecterMap.get(str);
        }
        return dVar;
    }

    private static ArrayList<DataMonitorImpl<?, ?>> getDataMonitor(DataUpdateListener<?> dataUpdateListener) {
        ArrayList<DataMonitorImpl<?, ?>> arrayList = new ArrayList<>();
        synchronized (mDataMonitors) {
            for (DataMonitorImpl<?, ?> dataMonitorImpl : mDataMonitors) {
                if (((DataMonitorImpl) dataMonitorImpl).updateListener == dataUpdateListener) {
                    arrayList.add(dataMonitorImpl);
                }
            }
        }
        return arrayList;
    }

    public static <T> void getInstance(final InstanceCreator<T> instanceCreator) {
        final Class cls = (Class) ((ParameterizedType) instanceCreator.getClass().getGenericInterfaces()[0]).getActualTypeArguments()[0];
        addInterface(cls);
        Thread thread = new Thread() { // from class: com.byd.shareddata.SharedDataManager.4
            @Override // java.lang.Thread, java.lang.Runnable
            public void run() {
                SharedDataManager.getInstance(instanceCreator, cls);
            }
        };
        AtomicInteger atomicInteger = connectCount;
        if (atomicInteger != null && atomicInteger.get() != 0) {
            thread.start();
        } else {
            thread.run();
        }
    }

    public static <T> void addDataUpdateListener(Object obj, Method method, DataUpdateListener<T> dataUpdateListener, boolean z) {
        DataMonitorInterface dataMonitorInterface = getDataMonitorInterface(obj);
        DataMonitorImpl<?, ?> dataMonitor = getDataMonitor(obj, method, dataUpdateListener, z);
        if (dataMonitor == null) {
            dataMonitor = new DataMonitorImpl<>(obj, method, dataUpdateListener, z);
            synchronized (mDataMonitors) {
                mDataMonitors.add(dataMonitor);
            }
        }
        dataMonitorInterface.addDataMonitor(Process.myPid(), method.getName(), method.getReturnType().getName(), dataMonitor);
    }

    private static ArrayList<DataMonitorImpl<?, ?>> getDataMonitor(Object obj, Method method) {
        ArrayList<DataMonitorImpl<?, ?>> arrayList = new ArrayList<>();
        synchronized (mDataMonitors) {
            for (DataMonitorImpl<?, ?> dataMonitorImpl : mDataMonitors) {
                if (((DataMonitorImpl) dataMonitorImpl).proxy == obj && ((DataMonitorImpl) dataMonitorImpl).method == method) {
                    arrayList.add(dataMonitorImpl);
                }
            }
        }
        return arrayList;
    }

    public static <T> void removeDataUpdateListener(Object obj, DataUpdateListener<T> dataUpdateListener) {
        for (DataMonitorImpl<?, ?> dataMonitorImpl : getDataMonitor((DataUpdateListener<?>) dataUpdateListener)) {
            synchronized (mDataMonitors) {
                mDataMonitors.remove(dataMonitorImpl);
            }
            getDataMonitorInterface(((DataMonitorImpl) dataMonitorImpl).proxy).removeDataMonitor(Process.myPid(), ((DataMonitorImpl) dataMonitorImpl).method.getName(), ((DataMonitorImpl) dataMonitorImpl).method.getReturnType().getName(), dataMonitorImpl);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static <T> void getInstance(final InstanceCreator<T> instanceCreator, final Class<T> cls) {
        connectLock.lock();
        final String packageName = getPackageName(cls);
        if (packageName != null) {
            b.b.l.u.d connecter = getConnecter(packageName);
            if (connecter != null) {
                instanceCreate(connecter, cls, instanceCreator);
            } else {
                final b.b.l.u.d dVarC = mRpcRequest.C(packageName, null);
                dVarC.p("SharedData").d(new d.f() { // from class: com.byd.shareddata.SharedDataManager.5
                    @Override // b.b.l.d.f
                    public void connected(ComponentName componentName, IInterface iInterface) {
                        SharedDataManager.addPackageConnecter(packageName, dVarC);
                        SharedDataManager.instanceCreate(dVarC, cls, instanceCreator);
                    }

                    @Override // b.b.l.d.f
                    public void disconnected(ComponentName componentName, IInterface iInterface) {
                    }
                });
            }
        }
    }

    private static ArrayList<DataMonitorImpl<?, ?>> getDataMonitor(Object obj) {
        ArrayList<DataMonitorImpl<?, ?>> arrayList = new ArrayList<>();
        synchronized (mDataMonitors) {
            for (DataMonitorImpl<?, ?> dataMonitorImpl : mDataMonitors) {
                if (((DataMonitorImpl) dataMonitorImpl).proxy == obj) {
                    arrayList.add(dataMonitorImpl);
                }
            }
        }
        return arrayList;
    }

    private static <T> T getInstance(b.b.l.u.d dVar, Class<T> cls) {
        if (dVar == null) {
            return null;
        }
        a aVar = new a(dVar);
        aVar.i(cls);
        aVar.h(sInterceptInvoker);
        T t = (T) aVar.a();
        addInstanceConnecter(t, dVar);
        return t;
    }

    private static void removeDataUpdateListener(Object obj) {
        for (DataMonitorImpl<?, ?> dataMonitorImpl : getDataMonitor(obj)) {
            synchronized (mDataMonitors) {
                mDataMonitors.remove(dataMonitorImpl);
            }
            getDataMonitorInterface(((DataMonitorImpl) dataMonitorImpl).proxy).removeDataMonitor(Process.myPid(), ((DataMonitorImpl) dataMonitorImpl).method.getName(), ((DataMonitorImpl) dataMonitorImpl).method.getReturnType().getName(), dataMonitorImpl);
        }
    }
}
