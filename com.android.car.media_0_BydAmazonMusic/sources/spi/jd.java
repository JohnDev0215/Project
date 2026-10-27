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

/* JADX INFO: compiled from: ServiceManager.java */
/* JADX INFO: loaded from: classes2.dex */
public final class jd {
    public static final fa<jd> e = new a();
    public ReentrantReadWriteLock a = new ReentrantReadWriteLock();
    public ReentrantReadWriteLock b = new ReentrantReadWriteLock();
    public Map<Class, ArrayList<id>> c = new HashMap();
    public Map<Class, LinkedHashSet<Object>> d = new HashMap();

    /* JADX INFO: compiled from: ServiceManager.java */
    public static class a extends fa<jd> {
        @Override // spi.fa
        public final jd a() {
            return new jd();
        }
    }

    public static jd b() {
        return e.b();
    }

    /* JADX WARN: Type inference failed for: r0v4, types: [java.util.HashMap, java.util.Map<java.lang.Class, java.util.LinkedHashSet<java.lang.Object>>] */
    /* JADX WARN: Type inference failed for: r0v9, types: [java.util.HashMap, java.util.Map<java.lang.Class, java.util.ArrayList<spi.id>>] */
    public final <T> T a(@NonNull Context context, @NonNull Class<T> cls) throws IllegalAccessException, InstantiationException, InvocationTargetException {
        T t;
        Uri uri;
        if (!cls.isInterface()) {
            throw new RuntimeException(cls.getCanonicalName() + "is not a interface");
        }
        T t2 = (T) ServiceMatcher.getService(context, cls);
        if (t2 != null) {
            StringBuilder sbK = c.k("spi get service from annotation: ");
            sbK.append(cls.getCanonicalName());
            sbK.append(" instance:");
            sbK.append(t2);
            i6.c("foundation.spi", sbK.toString());
            return t2;
        }
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
                        t = null;
                    } else {
                        id idVar = (id) list.get(0);
                        Objects.requireNonNull(idVar);
                        Object objA = kd.b().a(context, idVar.a);
                        ca caVar = (ca) objA.getClass().getAnnotation(ca.class);
                        if (!d8.a(caVar) && caVar.singleton()) {
                            c(cls, objA);
                        }
                        t = (T) objA;
                    }
                } finally {
                    this.a.readLock().unlock();
                }
            } else {
                t = (T) linkedHashSet.iterator().next();
            }
            if (t != null) {
                StringBuilder sbK2 = c.k("spi get service from registry: ");
                sbK2.append(cls.getCanonicalName());
                sbK2.append(" instance:");
                sbK2.append(t);
                i6.c("foundation.spi", sbK2.toString());
                return t;
            }
            T t3 = (T) AssetsServiceLoader.with().loadService(context, cls);
            if (t3 != null) {
                StringBuilder sbK3 = c.k("spi get service from config: ");
                sbK3.append(cls.getCanonicalName());
                sbK3.append(" instance:");
                sbK3.append(t3);
                i6.c("foundation.spi", sbK3.toString());
                return t3;
            }
            if (!IInterface.class.isAssignableFrom(cls)) {
                StringBuilder sbK4 = c.k("spi get service failed: ");
                sbK4.append(cls.getCanonicalName());
                i6.c("foundation.spi", sbK4.toString());
                return null;
            }
            StringBuilder sbK5 = c.k("spi get service from remote: ");
            sbK5.append(cls.getCanonicalName());
            i6.c("foundation.spi", sbK5.toString());
            ld ldVarB = ld.b.b();
            IInterface iInterface = ldVarB.a.get(cls.getCanonicalName());
            if (iInterface != null) {
                if (iInterface.asBinder().isBinderAlive()) {
                    StringBuilder sbK6 = c.k("spi get service from remote alive cache: ");
                    sbK6.append(cls.getCanonicalName());
                    sbK6.append(" instance:");
                    sbK6.append(iInterface);
                    i6.c("foundation.spi", sbK6.toString());
                    return cls.cast(iInterface);
                }
                ldVarB.a.remove(cls.getCanonicalName());
            }
            ContentResolver contentResolver = context.getContentResolver();
            c5 c5Var = Spi.getConfig().a;
            if (c5Var == null || (uri = c5Var.a) == null) {
                i6.c("foundation.spi", "spi get service from remote failed: ipc uri is null!");
                return null;
            }
            Cursor cursorQuery = contentResolver.query(uri, null, null, new String[]{cls.getCanonicalName()}, null);
            if (cursorQuery == null) {
                i6.c("foundation.spi", "spi get service from remote failed: cannot find car service!");
                return null;
            }
            Bundle extras = cursorQuery.getExtras();
            extras.setClassLoader(BinderCursor.BinderParcelable.class.getClassLoader());
            try {
                try {
                    IBinder binder = ((BinderCursor.BinderParcelable) extras.getParcelable(BinderCursor.KEY_BINDER)).getBinder();
                    IInterface iInterface2 = (IInterface) Class.forName(cls.getName() + "$Stub").getDeclaredMethod("asInterface", IBinder.class).invoke(null, binder);
                    binder.linkToDeath(new md(ldVarB, cls), 0);
                    ldVarB.a.put(cls.getCanonicalName(), iInterface2);
                    i6.c("foundation.spi", "spi get service from remote: " + cls.getCanonicalName() + " instance:" + iInterface2);
                    tCast = cls.cast(iInterface2);
                } catch (Throwable th) {
                    try {
                        i6.b("foundation.spi", "spi get service from remote failed:", th);
                        cursorQuery.close();
                    } finally {
                        try {
                            cursorQuery.close();
                        } catch (Throwable unused) {
                        }
                    }
                }
                return tCast;
            } catch (Throwable unused2) {
                return tCast;
            }
        } catch (Throwable th2) {
            this.b.readLock().unlock();
            throw th2;
        }
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
            i6.c("foundation.spi", "spi add instance: " + cls.getCanonicalName() + " size:" + linkedHashSet.size());
        } finally {
            this.b.writeLock().unlock();
        }
    }
}
