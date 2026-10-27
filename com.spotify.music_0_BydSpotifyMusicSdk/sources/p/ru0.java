package p;

import android.app.Application;
import android.content.ComponentName;
import android.content.Context;
import android.content.IntentFilter;
import android.content.pm.PackageManager;
import android.content.pm.ServiceInfo;
import android.os.Bundle;
import android.os.Trace;
import android.text.TextUtils;
import android.util.Base64;
import com.google.firebase.FirebaseCommonRegistrar;
import com.google.firebase.concurrent.ExecutorsRegistrar;
import com.google.firebase.concurrent.UiExecutor;
import com.google.firebase.provider.FirebaseInitProvider;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Objects;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicReference;

/* JADX INFO: loaded from: classes.dex */
public final class ru0 {
    public static final Object j = new Object();
    public static final nb k = new nb(0);
    public final Context a;
    public final String b;
    public final xu0 c;
    public final gw d;
    public final w81 g;
    public final yy1 h;
    public final AtomicBoolean e = new AtomicBoolean(false);
    public final AtomicBoolean f = new AtomicBoolean();
    public final CopyOnWriteArrayList i = new CopyOnWriteArrayList();

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r0v29, types: [java.util.List] */
    /* JADX WARN: Type inference failed for: r0v8, types: [java.util.ArrayList] */
    /* JADX WARN: Type inference failed for: r0v9, types: [java.util.List] */
    public ru0(Context context, xu0 xu0Var, String str) {
        ?? arrayList;
        int i = 0;
        new CopyOnWriteArrayList();
        this.a = context;
        if (TextUtils.isEmpty(str)) {
            throw new IllegalArgumentException("Given String is empty or null");
        }
        this.b = str;
        this.c = xu0Var;
        qi qiVar = FirebaseInitProvider.c;
        Trace.beginSection("Firebase");
        Trace.beginSection("ComponentDiscovery");
        hk0 hk0Var = new hk0();
        ArrayList arrayList2 = new ArrayList();
        Bundle bundle = null;
        try {
            PackageManager packageManager = context.getPackageManager();
            if (packageManager != null) {
                ServiceInfo serviceInfo = packageManager.getServiceInfo(new ComponentName(context, (Class<?>) hk0Var.e), 128);
                if (serviceInfo == null) {
                    Objects.toString((Class) hk0Var.e);
                } else {
                    bundle = serviceInfo.metaData;
                }
            }
        } catch (PackageManager.NameNotFoundException unused) {
        }
        if (bundle == null) {
            arrayList = Collections.emptyList();
        } else {
            arrayList = new ArrayList();
            for (String str2 : bundle.keySet()) {
                if ("com.google.firebase.components.ComponentRegistrar".equals(bundle.get(str2)) && str2.startsWith("com.google.firebase.components:")) {
                    arrayList.add(str2.substring(31));
                }
            }
        }
        Iterator it = arrayList.iterator();
        while (it.hasNext()) {
            arrayList2.add(new zv(i, (String) it.next()));
        }
        Trace.endSection();
        Trace.beginSection("Runtime");
        gh2 gh2Var = new gh2(UiExecutor.c, 9);
        ((List) gh2Var.k).addAll(arrayList2);
        int i2 = 1;
        ((List) gh2Var.k).add(new zv(i2, new FirebaseCommonRegistrar()));
        ((List) gh2Var.k).add(new zv(i2, new ExecutorsRegistrar()));
        ((List) gh2Var.l).add(mv.a(context, Context.class, new Class[0]));
        ((List) gh2Var.l).add(mv.a(this, ru0.class, new Class[0]));
        ((List) gh2Var.l).add(mv.a(xu0Var, xu0.class, new Class[0]));
        gh2Var.m = new yn(21);
        if (zr2.a(context) && FirebaseInitProvider.e.get()) {
            ((List) gh2Var.l).add(mv.a(qiVar, qi.class, new Class[0]));
        }
        gw gwVar = new gw((List) gh2Var.k, (List) gh2Var.l, (cw) gh2Var.m);
        this.d = gwVar;
        Trace.endSection();
        this.g = new w81(new nu0(this, i, context));
        this.h = gwVar.d(y60.class);
        ou0 ou0Var = new ou0(this);
        a();
        if (this.e.get()) {
            yk.m.c.get();
        }
        this.i.add(ou0Var);
        Trace.endSection();
    }

    public static ru0 b() {
        ru0 ru0Var;
        synchronized (j) {
            try {
                ru0Var = (ru0) k.get("[DEFAULT]");
                if (ru0Var == null) {
                    throw new IllegalStateException("Default FirebaseApp is not initialized in this process " + v03.r() + ". Make sure to call FirebaseApp.initializeApp(Context) first.");
                }
            } catch (Throwable th) {
                throw th;
            }
        }
        return ru0Var;
    }

    public static ru0 e(Context context, xu0 xu0Var) {
        ru0 ru0Var;
        AtomicReference atomicReference = pu0.a;
        if (context.getApplicationContext() instanceof Application) {
            Application application = (Application) context.getApplicationContext();
            AtomicReference atomicReference2 = pu0.a;
            if (atomicReference2.get() == null) {
                pu0 pu0Var = new pu0();
                while (true) {
                    if (atomicReference2.compareAndSet(null, pu0Var)) {
                        yk ykVar = yk.m;
                        synchronized (ykVar) {
                            try {
                                if (!ykVar.l) {
                                    application.registerActivityLifecycleCallbacks(ykVar);
                                    application.registerComponentCallbacks(ykVar);
                                    ykVar.l = true;
                                }
                            } finally {
                            }
                        }
                        synchronized (ykVar) {
                            ykVar.k.add(pu0Var);
                        }
                    } else if (atomicReference2.get() != null) {
                        break;
                    }
                }
            }
        }
        if (context.getApplicationContext() != null) {
            context = context.getApplicationContext();
        }
        synchronized (j) {
            nb nbVar = k;
            String str = "FirebaseApp name [DEFAULT] already exists!";
            if (!(true ^ nbVar.containsKey("[DEFAULT]"))) {
                throw new IllegalStateException(String.valueOf(str));
            }
            he1.h(context, "Application context cannot be null.");
            ru0Var = new ru0(context, xu0Var, "[DEFAULT]");
            nbVar.put("[DEFAULT]", ru0Var);
        }
        ru0Var.d();
        return ru0Var;
    }

    public final void a() {
        if (!(!this.f.get())) {
            throw new IllegalStateException("FirebaseApp was deleted");
        }
    }

    public final String c() {
        StringBuilder sb = new StringBuilder();
        a();
        byte[] bytes = this.b.getBytes(Charset.defaultCharset());
        sb.append(bytes == null ? null : Base64.encodeToString(bytes, 11));
        sb.append("+");
        a();
        byte[] bytes2 = this.c.b.getBytes(Charset.defaultCharset());
        sb.append(bytes2 != null ? Base64.encodeToString(bytes2, 11) : null);
        return sb.toString();
    }

    public final void d() {
        HashMap map;
        if (!zr2.a(this.a)) {
            a();
            Context context = this.a;
            AtomicReference atomicReference = qu0.b;
            if (atomicReference.get() == null) {
                qu0 qu0Var = new qu0(context);
                while (!atomicReference.compareAndSet(null, qu0Var)) {
                    if (atomicReference.get() != null) {
                        return;
                    }
                }
                context.registerReceiver(qu0Var, new IntentFilter("android.intent.action.USER_UNLOCKED"));
                return;
            }
            return;
        }
        a();
        gw gwVar = this.d;
        a();
        boolean zEquals = "[DEFAULT]".equals(this.b);
        AtomicReference atomicReference2 = gwVar.e;
        Boolean boolValueOf = Boolean.valueOf(zEquals);
        while (true) {
            if (atomicReference2.compareAndSet(null, boolValueOf)) {
                synchronized (gwVar) {
                    map = new HashMap(gwVar.a);
                }
                gwVar.h(map, zEquals);
                break;
            } else if (atomicReference2.get() != null) {
                break;
            }
        }
        ((y60) this.h.get()).b();
    }

    public final boolean equals(Object obj) {
        if (!(obj instanceof ru0)) {
            return false;
        }
        ru0 ru0Var = (ru0) obj;
        ru0Var.a();
        return this.b.equals(ru0Var.b);
    }

    public final int hashCode() {
        return this.b.hashCode();
    }

    public final String toString() {
        g90 g90Var = new g90((Object) this);
        g90Var.a(this.b, "name");
        g90Var.a(this.c, "options");
        return g90Var.toString();
    }
}
