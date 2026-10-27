package com.byd.spi;

import android.content.Context;
import androidx.annotation.NonNull;
import com.byd.spi.service.AssetsServiceLoader;
import com.byd.spi.service.ServiceMatcher;
import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Objects;
import spi.dh;
import spi.e;
import spi.nv;
import spi.ov;
import spi.pv;
import spi.xj;
import spi.yr;
import spi.zo;

/* JADX INFO: loaded from: classes.dex */
public class Spi {
    private static yr sConfig;

    /* JADX WARN: Type inference incomplete: some casts might be missing */
    public static void addService(@NonNull Class cls, @NonNull Class cls2) {
        ov ovVarB = ov.b();
        ovVarB.a.writeLock().lock();
        try {
            ArrayList<nv> arrayList = ovVarB.c.get(cls);
            if (arrayList == null) {
                arrayList = new ArrayList<>();
                ovVarB.c.put(cls, arrayList);
            }
            arrayList.add(new nv(cls, cls2));
            dh.c("foundation.spi", "spi add service: " + cls.getCanonicalName() + " size:" + arrayList.size());
        } finally {
            ovVarB.a.writeLock().unlock();
        }
    }

    public static void addService(@NonNull Class cls, @NonNull Object obj) {
        ov.b().c(cls, obj);
    }

    public static yr getConfig() {
        yr yrVar = sConfig;
        if (yrVar != null) {
            return yrVar;
        }
        throw new IllegalArgumentException("Spi config can not be null!");
    }

    public static <T> T getService(@NonNull Context context, @NonNull Class<T> cls) {
        return (T) ov.b().a(context, cls);
    }

    /* JADX WARN: Type inference failed for: r1v4, types: [java.util.HashMap, java.util.Map<java.lang.Class, java.util.LinkedHashSet<java.lang.Object>>] */
    /* JADX WARN: Type inference failed for: r1v9, types: [java.util.HashMap, java.util.Map<java.lang.Class, java.util.ArrayList<spi.nv>>] */
    public static <T> List<T> getServices(@NonNull Context context, @NonNull Class<T> cls) {
        StringBuilder sb;
        String str;
        ov ovVarB = ov.b();
        Objects.requireNonNull(ovVarB);
        if (!cls.isInterface()) {
            throw new RuntimeException(cls.getCanonicalName() + "is not a interface");
        }
        List<T> services = ServiceMatcher.getServices(context, cls);
        if (services.size() > 0) {
            sb = new StringBuilder();
            str = "spi get services from annotation: ";
        } else {
            ovVarB.b.readLock().lock();
            try {
                LinkedHashSet linkedHashSet = (LinkedHashSet) ovVarB.d.get(cls);
                ovVarB.b.readLock().unlock();
                if (linkedHashSet == null || linkedHashSet.size() <= 0) {
                    ovVarB.a.readLock().lock();
                    try {
                        List<nv> list = (List) ovVarB.c.get(cls);
                        ovVarB.a.readLock().unlock();
                        ArrayList arrayList = new ArrayList();
                        if (list != null && list.size() > 0) {
                            for (nv nvVar : list) {
                                try {
                                    Objects.requireNonNull(nvVar);
                                    Object objA = pv.b().a(context, nvVar.a);
                                    zo zoVar = (zo) objA.getClass().getAnnotation(zo.class);
                                    if (!xj.a(zoVar) && zoVar.singleton()) {
                                        ovVarB.c(cls, objA);
                                    }
                                    arrayList.add(objA);
                                } catch (Throwable th) {
                                    StringBuilder sbK = e.k("spi load services from registry failed:");
                                    sbK.append(cls.getName());
                                    dh.b("foundation.spi", sbK.toString(), th);
                                }
                            }
                        }
                        services = arrayList.size() > 0 ? arrayList : null;
                    } catch (Throwable th2) {
                        ovVarB.a.readLock().unlock();
                        throw th2;
                    }
                } else {
                    services = new ArrayList(linkedHashSet);
                }
                if (services == null || services.size() <= 0) {
                    services = AssetsServiceLoader.with().loadServices(context, cls);
                    if (services == null || services.size() <= 0) {
                        StringBuilder sbK2 = e.k("spi get services failed: ");
                        sbK2.append(cls.getCanonicalName());
                        dh.c("foundation.spi", sbK2.toString());
                        return null;
                    }
                    sb = new StringBuilder();
                    str = "spi get services from config: ";
                } else {
                    sb = new StringBuilder();
                    str = "spi get services from registry: ";
                }
            } catch (Throwable th3) {
                ovVarB.b.readLock().unlock();
                throw th3;
            }
        }
        sb.append(str);
        sb.append(cls.getCanonicalName());
        sb.append(" size:");
        sb.append(services.size());
        dh.c("foundation.spi", sb.toString());
        return services;
    }

    public static void init(yr yrVar) {
        if (yrVar == null) {
            throw new IllegalArgumentException("Spi config can not be null!");
        }
        sConfig = yrVar;
        dh.c("foundation.spi", "spi init success, version:1.1.1");
    }
}
