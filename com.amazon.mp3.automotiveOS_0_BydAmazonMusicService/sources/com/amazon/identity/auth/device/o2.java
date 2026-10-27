package com.amazon.identity.auth.device;

import android.content.Context;
import android.content.pm.ResolveInfo;
import android.content.pm.ServiceInfo;
import androidx.lifecycle.CoroutineLiveDataKt;
import com.amazon.identity.auth.device.api.MAPAccountManager;
import com.amazon.identity.auth.device.n2.b;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

/* JADX INFO: compiled from: DCP */
/* JADX INFO: loaded from: classes3.dex */
public class o2 implements Runnable {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final /* synthetic */ n2 f470a;

    public o2(n2 n2Var) {
        this.f470a = n2Var;
    }

    @Override // java.lang.Runnable
    public void run() {
        Set<String> setA;
        List<ResolveInfo> listQueryIntentServices = this.f470a.f447a.getPackageManager().queryIntentServices(this.f470a.c, 64);
        String str = n2.k;
        ga.a(str);
        if (listQueryIntentServices.isEmpty()) {
            ga.a(str);
            this.f470a.a(MAPAccountManager.BootstrapError.NO_SERVICE_AVAILABLE, "No service was found");
            return;
        }
        n2 n2Var = this.f470a;
        Context context = n2Var.f447a;
        synchronized (j2.class) {
            if (l2.a(context)) {
                setA = j2.a(context);
            } else {
                synchronized (j2.class) {
                    HashSet hashSet = new HashSet();
                    f8 f8Var = new f8(context, "bootstrap.sso.authority.signature.store", 0);
                    int i = f8Var.f313a.getInt("bootstrap.sso.authority.signature.array.size", 0);
                    for (int i2 = 0; i2 < i; i2++) {
                        hashSet.add(f8Var.b("bootstrap.sso.authority.signature.key." + i2));
                    }
                    setA = hashSet;
                }
            }
        }
        ServiceInfo serviceInfo = null;
        if (!setA.isEmpty()) {
            Iterator<ResolveInfo> it = listQueryIntentServices.iterator();
            loop0: while (true) {
                if (!it.hasNext()) {
                    break;
                }
                ResolveInfo next = it.next();
                if (!n2Var.f447a.getPackageName().equals(next.serviceInfo.applicationInfo.packageName) && (e.a(n2Var.i) || n2Var.i.contains(next.serviceInfo.applicationInfo.packageName))) {
                    Iterator<String> it2 = q2.a(n2Var.f447a, next.serviceInfo.applicationInfo.packageName).iterator();
                    while (it2.hasNext()) {
                        if (setA.contains(it2.next())) {
                            serviceInfo = next.serviceInfo;
                            break loop0;
                        }
                    }
                }
            }
        } else {
            ga.a(n2.k);
        }
        if (serviceInfo == null) {
            ga.a(n2.k);
            this.f470a.a(MAPAccountManager.BootstrapError.NO_SIGNATURE, "No app found with valid signature");
            return;
        }
        this.f470a.c.setClassName(serviceInfo.applicationInfo.packageName, serviceInfo.name);
        ga.a(n2.k, "Service found. Starting service with package %s and class %s", serviceInfo.applicationInfo.packageName, serviceInfo.name);
        this.f470a.d.schedule(this.f470a.new b(), CoroutineLiveDataKt.DEFAULT_TIMEOUT);
        n2 n2Var2 = this.f470a;
        n2Var2.f447a.bindService(n2Var2.c, n2Var2.j, 1);
    }
}
