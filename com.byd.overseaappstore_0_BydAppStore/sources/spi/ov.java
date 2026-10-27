package spi;

import android.content.ContentResolver;
import android.content.Context;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.os.IBinder;
import android.os.IInterface;
import androidx.annotation.NonNull;
import com.byd.spi.Spi;
import com.byd.spi.ipc.cursor.BinderCursor;
import com.byd.spi.service.AssetsServiceLoader;
import com.byd.spi.service.ServiceMatcher;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/* JADX INFO: loaded from: classes2.dex */
public final class ov {
    public static op<ov> e = new a();
    public ReentrantReadWriteLock a = new ReentrantReadWriteLock();
    public ReentrantReadWriteLock b = new ReentrantReadWriteLock();
    public Map<Class, ArrayList<nv>> c = new HashMap();
    public Map<Class, LinkedHashSet<Object>> d = new HashMap();

    public static class a extends op<ov> {
        @Override // spi.op
        public final ov a() {
            return new ov();
        }
    }

    public static ov b() {
        return e.b();
    }

    /* JADX WARN: Type inference failed for: r3v10, types: [java.util.HashMap, java.util.Map<java.lang.Class, java.util.ArrayList<spi.nv>>] */
    /* JADX WARN: Type inference failed for: r3v5, types: [java.util.HashMap, java.util.Map<java.lang.Class, java.util.LinkedHashSet<java.lang.Object>>] */
    public final <T> T a(@NonNull Context context, @NonNull Class<T> cls) throws IllegalAccessException, InstantiationException, InvocationTargetException {
        String str;
        Uri uri;
        StringBuilder sb;
        String str2;
        if (!cls.isInterface()) {
            throw new RuntimeException(cls.getCanonicalName() + "is not a interface");
        }
        Object objA = (T) ServiceMatcher.getService(context, cls);
        if (objA != null) {
            sb = new StringBuilder();
            str2 = "spi get service from annotation: ";
        } else {
            this.b.readLock().lock();
            try {
                LinkedHashSet linkedHashSet = (LinkedHashSet) this.d.get(cls);
                this.b.readLock().unlock();
                T tCast = null;
                if (linkedHashSet == null || linkedHashSet.size() <= 0) {
                    this.a.readLock().lock();
                    try {
                        List list = (List) this.c.get(cls);
                        if (list == null || list.size() <= 0) {
                            objA = null;
                        } else {
                            nv nvVar = (nv) list.get(0);
                            Objects.requireNonNull(nvVar);
                            objA = pv.b().a(context, nvVar.a);
                            zo zoVar = (zo) objA.getClass().getAnnotation(zo.class);
                            if (!xj.a(zoVar) && zoVar.singleton()) {
                                c(cls, objA);
                            }
                        }
                    } finally {
                        this.a.readLock().unlock();
                    }
                } else {
                    objA = (T) linkedHashSet.iterator().next();
                }
                if (objA != null) {
                    sb = new StringBuilder();
                    str2 = "spi get service from registry: ";
                } else {
                    objA = (T) AssetsServiceLoader.with().loadService(context, cls);
                    if (objA == null) {
                        if (!IInterface.class.isAssignableFrom(cls)) {
                            StringBuilder sbK = e.k("spi get service failed: ");
                            sbK.append(cls.getCanonicalName());
                            dh.c("foundation.spi", sbK.toString());
                            return null;
                        }
                        StringBuilder sbK2 = e.k("spi get service from remote: ");
                        sbK2.append(cls.getCanonicalName());
                        dh.c("foundation.spi", sbK2.toString());
                        qv qvVarB = qv.b.b();
                        IInterface iInterface = qvVarB.a.get(cls.getCanonicalName());
                        if (iInterface != null) {
                            if (iInterface.asBinder().isBinderAlive()) {
                                StringBuilder sbK3 = e.k("spi get service from remote alive cache: ");
                                sbK3.append(cls.getCanonicalName());
                                sbK3.append(" instance:");
                                sbK3.append(iInterface);
                                dh.c("foundation.spi", sbK3.toString());
                                return cls.cast(iInterface);
                            }
                            qvVarB.a.remove(cls.getCanonicalName());
                        }
                        ContentResolver contentResolver = context.getContentResolver();
                        cg cgVar = Spi.getConfig().a;
                        if (cgVar == null || (uri = cgVar.a) == null) {
                            str = "spi get service from remote failed: ipc uri is null!";
                        } else {
                            Cursor cursorQuery = contentResolver.query(uri, null, null, new String[]{cls.getCanonicalName()}, null);
                            if (cursorQuery != null) {
                                Bundle extras = cursorQuery.getExtras();
                                extras.setClassLoader(BinderCursor.BinderParcelable.class.getClassLoader());
                                try {
                                    IBinder binder = ((BinderCursor.BinderParcelable) extras.getParcelable(BinderCursor.KEY_BINDER)).getBinder();
                                    IInterface iInterface2 = (IInterface) Class.forName(cls.getName() + "$Stub").getDeclaredMethod("asInterface", IBinder.class).invoke(null, binder);
                                    binder.linkToDeath(new rv(qvVarB, cls), 0);
                                    qvVarB.a.put(cls.getCanonicalName(), iInterface2);
                                    dh.c("foundation.spi", "spi get service from remote: " + cls.getCanonicalName() + " instance:" + iInterface2);
                                    tCast = cls.cast(iInterface2);
                                } finally {
                                    try {
                                    } finally {
                                        try {
                                            cursorQuery.close();
                                        } catch (Throwable unused) {
                                        }
                                    }
                                }
                                try {
                                    return tCast;
                                } catch (Throwable unused2) {
                                    return tCast;
                                }
                            }
                            str = "spi get service from remote failed: cannot find car service!";
                        }
                        dh.c("foundation.spi", str);
                        return null;
                    }
                    sb = new StringBuilder();
                    str2 = "spi get service from config: ";
                }
            } catch (Throwable th) {
                this.b.readLock().unlock();
                throw th;
            }
        }
        sb.append(str2);
        sb.append(cls.getCanonicalName());
        sb.append(" instance:");
        sb.append(objA);
        dh.c("foundation.spi", sb.toString());
        return (T) objA;
    }

    /* JADX WARN: Type inference incomplete: some casts might be missing */
    public final void c(@NonNull Class cls, @NonNull Object obj) {
        this.b.writeLock().lock();
        try {
            LinkedHashSet<Object> linkedHashSet = this.d.get(cls);
            if (linkedHashSet == null) {
                linkedHashSet = new LinkedHashSet<>();
                this.d.put(cls, linkedHashSet);
            }
            linkedHashSet.add(obj);
            dh.c("foundation.spi", "spi add instance: " + cls.getCanonicalName() + " size:" + linkedHashSet.size());
        } finally {
            this.b.writeLock().unlock();
        }
    }
}
