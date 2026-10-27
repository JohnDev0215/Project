package androidx.mediarouter.media;

import android.content.BroadcastReceiver;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.content.pm.ServiceInfo;
import android.os.Build;
import android.os.Handler;
import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import com.byd.mediacenter.api.media.MediaControl;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

/* JADX INFO: compiled from: caradapter-bug */
/* JADX INFO: loaded from: classes2.dex */
final class B {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private final Context f918a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private final c f919b;
    private final PackageManager d;
    private boolean f;
    private final ArrayList<A> e = new ArrayList<>();
    private final BroadcastReceiver g = new a();
    private final Runnable h = new b();

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    private final Handler f920c = new Handler();

    /* JADX INFO: compiled from: caradapter-bug */
    class a extends BroadcastReceiver {
        a() {
        }

        @Override // android.content.BroadcastReceiver
        public void onReceive(Context context, Intent intent) {
            B.this.e();
        }
    }

    /* JADX INFO: compiled from: caradapter-bug */
    class b implements Runnable {
        b() {
        }

        @Override // java.lang.Runnable
        public void run() {
            B.this.e();
        }
    }

    /* JADX INFO: compiled from: caradapter-bug */
    public interface c {
        void a(MediaRouteProvider mediaRouteProvider);

        void b(MediaRouteProvider mediaRouteProvider);
    }

    B(Context context, c cVar) {
        this.f918a = context;
        this.f919b = cVar;
        this.d = context.getPackageManager();
    }

    private int a(String str, String str2) {
        int size = this.e.size();
        for (int i = 0; i < size; i++) {
            if (this.e.get(i).G(str, str2)) {
                return i;
            }
        }
        return -1;
    }

    static boolean d(List<ServiceInfo> list, ServiceInfo serviceInfo) {
        if (serviceInfo != null && list != null && !list.isEmpty()) {
            for (ServiceInfo serviceInfo2 : list) {
                if (serviceInfo.packageName.equals(serviceInfo2.packageName) && serviceInfo.name.equals(serviceInfo2.name)) {
                    return true;
                }
            }
        }
        return false;
    }

    @NonNull
    @RequiresApi(30)
    List<ServiceInfo> b() {
        return (List) this.d.queryIntentServices(new Intent("android.media.MediaRoute2ProviderService"), 0).stream().map(new Function() { // from class: androidx.mediarouter.media.j
            @Override // java.util.function.Function
            public final Object apply(Object obj) {
                return ((ResolveInfo) obj).serviceInfo;
            }
        }).collect(Collectors.toList());
    }

    void e() {
        int i;
        if (this.f) {
            List<ServiceInfo> arrayList = new ArrayList<>();
            if (Build.VERSION.SDK_INT >= 30) {
                arrayList = b();
            }
            int i2 = 0;
            Iterator<ResolveInfo> it = this.d.queryIntentServices(new Intent("android.media.MediaRouteProviderService"), 0).iterator();
            while (it.hasNext()) {
                ServiceInfo serviceInfo = it.next().serviceInfo;
                if (serviceInfo != null && (!u.l() || !d(arrayList, serviceInfo))) {
                    int iA = a(serviceInfo.packageName, serviceInfo.name);
                    if (iA < 0) {
                        A a2 = new A(this.f918a, new ComponentName(serviceInfo.packageName, serviceInfo.name));
                        a2.P();
                        i = i2 + 1;
                        this.e.add(i2, a2);
                        this.f919b.b(a2);
                    } else if (iA >= i2) {
                        A a3 = this.e.get(iA);
                        a3.P();
                        a3.N();
                        i = i2 + 1;
                        Collections.swap(this.e, iA, i2);
                    }
                    i2 = i;
                }
            }
            if (i2 < this.e.size()) {
                for (int size = this.e.size() - 1; size >= i2; size--) {
                    A a4 = this.e.get(size);
                    this.f919b.a(a4);
                    this.e.remove(a4);
                    a4.Q();
                }
            }
        }
    }

    public void f() {
        if (this.f) {
            return;
        }
        this.f = true;
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction("android.intent.action.PACKAGE_ADDED");
        intentFilter.addAction("android.intent.action.PACKAGE_REMOVED");
        intentFilter.addAction("android.intent.action.PACKAGE_CHANGED");
        intentFilter.addAction("android.intent.action.PACKAGE_REPLACED");
        intentFilter.addAction("android.intent.action.PACKAGE_RESTARTED");
        intentFilter.addDataScheme(MediaControl.PARAM_PACKAGE);
        this.f918a.registerReceiver(this.g, intentFilter, null, this.f920c);
        this.f920c.post(this.h);
    }
}
