package com.byd.spi;

import android.content.Context;
import androidx.annotation.NonNull;
import com.byd.spi.service.AssetsServiceLoader;
import com.byd.spi.service.ServiceMatcher;
import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Objects;
import spi.c;
import spi.ca;
import spi.d8;
import spi.ga;
import spi.i6;
import spi.id;
import spi.jd;
import spi.kd;

/* JADX INFO: loaded from: classes2.dex */
public class Spi {
    private static ga sConfig;

    /* JADX WARN: Type inference incomplete: some casts might be missing */
    public static void addService(@NonNull Class cls, @NonNull Class cls2) {
        jd jdVarB = jd.b();
        jdVarB.a.writeLock().lock();
        try {
            ArrayList<id> arrayList = jdVarB.c.get(cls);
            if (arrayList == null) {
                arrayList = new ArrayList<>();
                jdVarB.c.put(cls, arrayList);
            }
            arrayList.add(new id(cls, cls2));
            i6.c("foundation.spi", "spi add service: " + cls.getCanonicalName() + " size:" + arrayList.size());
        } finally {
            jdVarB.a.writeLock().unlock();
        }
    }

    public static ga getConfig() {
        ga gaVar = sConfig;
        if (gaVar != null) {
            return gaVar;
        }
        throw new IllegalArgumentException("Spi config can not be null!");
    }

    public static <T> T getService(@NonNull Context context, @NonNull Class<T> cls) {
        return (T) jd.b().a(context, cls);
    }

    /* JADX WARN: Type inference failed for: r1v4, types: [java.util.HashMap, java.util.Map<java.lang.Class, java.util.LinkedHashSet<java.lang.Object>>] */
    /* JADX WARN: Type inference failed for: r1v9, types: [java.util.HashMap, java.util.Map<java.lang.Class, java.util.ArrayList<spi.id>>] */
    public static <T> List<T> getServices(@NonNull Context context, @NonNull Class<T> cls) {
        ArrayList arrayList;
        jd jdVarB = jd.b();
        Objects.requireNonNull(jdVarB);
        if (!cls.isInterface()) {
            throw new RuntimeException(cls.getCanonicalName() + "is not a interface");
        }
        List<T> services = ServiceMatcher.getServices(context, cls);
        if (!services.isEmpty()) {
            StringBuilder sbK = c.k("spi get services from annotation: ");
            sbK.append(cls.getCanonicalName());
            sbK.append(" size:");
            sbK.append(services.size());
            i6.c("foundation.spi", sbK.toString());
            return services;
        }
        jdVarB.b.readLock().lock();
        try {
            LinkedHashSet linkedHashSet = (LinkedHashSet) jdVarB.d.get(cls);
            jdVarB.b.readLock().unlock();
            if (linkedHashSet == null || linkedHashSet.size() <= 0) {
                jdVarB.a.readLock().lock();
                try {
                    List<id> list = (List) jdVarB.c.get(cls);
                    jdVarB.a.readLock().unlock();
                    ArrayList arrayList2 = new ArrayList();
                    if (list != null && !list.isEmpty()) {
                        for (id idVar : list) {
                            try {
                                Objects.requireNonNull(idVar);
                                Object objA = kd.b().a(context, idVar.a);
                                ca caVar = (ca) objA.getClass().getAnnotation(ca.class);
                                if (!d8.a(caVar) && caVar.singleton()) {
                                    jdVarB.c(cls, objA);
                                }
                                arrayList2.add(objA);
                            } catch (Throwable th) {
                                StringBuilder sbK2 = c.k("spi load services from registry failed:");
                                sbK2.append(cls.getName());
                                i6.b("foundation.spi", sbK2.toString(), th);
                            }
                        }
                    }
                    arrayList = !arrayList2.isEmpty() ? arrayList2 : null;
                } catch (Throwable th2) {
                    jdVarB.a.readLock().unlock();
                    throw th2;
                }
            } else {
                arrayList = new ArrayList(linkedHashSet);
            }
            if (arrayList != null && !arrayList.isEmpty()) {
                StringBuilder sbK3 = c.k("spi get services from registry: ");
                sbK3.append(cls.getCanonicalName());
                sbK3.append(" size:");
                sbK3.append(arrayList.size());
                i6.c("foundation.spi", sbK3.toString());
                return arrayList;
            }
            List<T> listLoadServices = AssetsServiceLoader.with().loadServices(context, cls);
            if (listLoadServices == null || listLoadServices.isEmpty()) {
                StringBuilder sbK4 = c.k("spi get services failed: ");
                sbK4.append(cls.getCanonicalName());
                i6.c("foundation.spi", sbK4.toString());
                return null;
            }
            StringBuilder sbK5 = c.k("spi get services from config: ");
            sbK5.append(cls.getCanonicalName());
            sbK5.append(" size:");
            sbK5.append(listLoadServices.size());
            i6.c("foundation.spi", sbK5.toString());
            return listLoadServices;
        } catch (Throwable th3) {
            jdVarB.b.readLock().unlock();
            throw th3;
        }
    }

    public static void init(ga gaVar) {
        if (gaVar == null) {
            throw new IllegalArgumentException("Spi config can not be null!");
        }
        sConfig = gaVar;
        i6.c("foundation.spi", "spi init success, version:1.2.1");
    }

    public static void addService(@NonNull Class cls, @NonNull Object obj) {
        jd.b().c(cls, obj);
    }
}
