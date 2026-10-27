package p1;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.net.Uri;
import android.os.IBinder;
import android.os.Parcel;
import android.os.RemoteException;
import android.os.UserHandle;
import android.provider.Settings;
import android.text.TextUtils;
import android.util.Log;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import p1.b;

/* JADX INFO: compiled from: BydSettingsApi.java */
/* JADX INFO: loaded from: classes.dex */
public final class a {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static final String[] f4511a = {"com.byd.carsettings", "com.byd.bluetoothcall", "com.android.systemui", "com.byd.localmusic"};

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public static final String[] f4512b = {"screen_saver", "finish_screen_saver", "Bluetooth", "dismiss_bt_settings", "WiFi", "dismiss_wifi_settings", "Hotspot", "dismiss_hotspot_settings", "car_play", "dismiss_carplay", "cpaa", "dismiss_cpaa_settings", "privacy_manage", "dismiss_all_dialog"};

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public static volatile a f4513c = null;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public static volatile boolean f4514d = false;

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    public p1.b f4518h;

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public c f4519i;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public volatile boolean f4515e = false;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public int f4516f = 0;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public Context f4517g = null;

    /* JADX INFO: renamed from: j, reason: collision with root package name */
    public LinkedList<r1.a> f4520j = new LinkedList<>();

    /* JADX INFO: renamed from: k, reason: collision with root package name */
    public q1.a f4521k = new q1.a();

    /* JADX INFO: renamed from: l, reason: collision with root package name */
    public ServiceConnection f4522l = new ServiceConnectionC0050a();

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public ServiceConnection f4523m = new b();

    /* JADX INFO: renamed from: p1.a$a, reason: collision with other inner class name */
    /* JADX INFO: compiled from: BydSettingsApi.java */
    public class ServiceConnectionC0050a implements ServiceConnection {
        public ServiceConnectionC0050a() {
        }

        @Override // android.content.ServiceConnection
        public void onServiceConnected(ComponentName componentName, IBinder iBinder) {
            boolean unused = a.f4514d = true;
            Log.i("BydSettingsApi", "---onServiceConnected---name: " + componentName + ", service: " + iBinder);
            a.this.f4518h = b.a.F(iBinder);
            if (a.this.f4519i != null) {
                a.this.f4519i.a(true);
            }
            if (a.this.f4520j != null && !a.this.f4520j.isEmpty()) {
                Log.w("BydSettingsApi", "---onServiceConnected---callsDisconnected.size: " + a.this.f4520j.size());
                for (int i3 = 0; i3 < 10; i3++) {
                    r1.a aVar = (r1.a) a.this.f4520j.poll();
                    Log.w("BydSettingsApi", "---onServiceConnected---call: " + aVar);
                    if (aVar == null) {
                        break;
                    }
                    a.this.p(aVar);
                }
                Log.w("BydSettingsApi", "---onServiceConnected--recall completed. callsDisconnected.size: " + a.this.f4520j.size());
            }
            try {
                a.this.f4521k.a(componentName.toString());
                iBinder.linkToDeath(a.this.f4521k, 0);
            } catch (RemoteException e3) {
                e3.printStackTrace();
                Log.e("BydSettingsApi", "---onServiceConnected---e: " + e3.toString());
            }
        }

        @Override // android.content.ServiceConnection
        public void onServiceDisconnected(ComponentName componentName) {
            boolean unused = a.f4514d = false;
            Log.e("BydSettingsApi", "---onServiceDisconnected---name: " + componentName);
            if (a.this.f4519i != null) {
                a.this.f4519i.a(false);
            }
        }
    }

    /* JADX INFO: compiled from: BydSettingsApi.java */
    public class b implements ServiceConnection {
        public b() {
        }

        @Override // android.content.ServiceConnection
        public void onServiceConnected(ComponentName componentName, IBinder iBinder) {
            Log.i("BydSettingsApi", "---onServiceConnected---service: " + iBinder);
            try {
                a.this.f4521k.a(componentName.toString());
                iBinder.linkToDeath(a.this.f4521k, 0);
            } catch (RemoteException e3) {
                e3.printStackTrace();
                Log.e("BydSettingsApi", "---onServiceConnected---e: " + e3.toString());
            }
        }

        @Override // android.content.ServiceConnection
        public void onServiceDisconnected(ComponentName componentName) {
            Log.i("BydSettingsApi", "---onServiceDisconnected---name: " + componentName);
        }
    }

    /* JADX INFO: compiled from: BydSettingsApi.java */
    public interface c {
        void a(boolean z3);
    }

    public static a l(Context context, c cVar) {
        Context applicationContext = context.getApplicationContext();
        if (f4513c == null) {
            synchronized (a.class) {
                if (f4513c == null) {
                    f4513c = new a();
                    f4513c.g(applicationContext);
                }
            }
        }
        if (!f4514d) {
            f4513c.g(applicationContext);
        }
        f4513c.f4519i = cVar;
        return f4513c;
    }

    public static Object o(Object obj, String str, Class<?>[] clsArr, Object[] objArr) {
        try {
            Method method = obj.getClass().getMethod(str, clsArr);
            method.setAccessible(true);
            return method.invoke(obj, objArr);
        } catch (IllegalAccessException | NoSuchMethodException | InvocationTargetException e3) {
            Log.e("BydSettingsApi", ", Invoke param arr failed : " + e3.toString());
            return null;
        }
    }

    public void g(Context context) {
        Log.i("BydSettingsApi", "---bindService---isServiceConnected: " + f4514d);
        if (context == null || f4514d) {
            return;
        }
        this.f4517g = context.getApplicationContext();
        Intent intent = new Intent();
        intent.setAction("com.byd.action.ACTION_BYD_SYSTEM_SETTING");
        intent.setComponent(new ComponentName("com.byd.uereport", "com.byd.uereport.MultiModuleService"));
        boolean zI = i(context);
        Log.i("BydSettingsApi", "---bindService---systemApp: " + zI);
        if (zI) {
            Parcel parcelObtain = Parcel.obtain();
            Class<?>[] clsArr = {Intent.class, ServiceConnection.class, Integer.TYPE, UserHandle.class};
            Object[] objArr = {intent, this.f4522l, 1, UserHandle.readFromParcel(parcelObtain)};
            try {
                Method method = context.getClass().getMethod("bindServiceAsUser", clsArr);
                method.setAccessible(true);
                method.invoke(context, objArr);
            } catch (IllegalAccessException | NoSuchMethodException | InvocationTargetException e3) {
                Log.e("BydSettingsApi", ", Invoke param arr failed : " + e3.toString());
                Log.i("BydSettingsApi", "---do as normal bindService---");
                context.bindService(intent, this.f4522l, 1);
            }
            parcelObtain.recycle();
        } else {
            context.bindService(intent, this.f4522l, 1);
        }
        this.f4516f = m(context);
        Log.i("BydSettingsApi", "---bindService---ueApiVersion: " + this.f4516f);
    }

    public final boolean h() {
        if (this.f4518h != null && f4514d) {
            return true;
        }
        Log.e("BydSettingsApi", "BydSettingsApi service is disconnected ,and doing reconnect.");
        g(this.f4517g);
        return false;
    }

    public final boolean i(Context context) {
        if (context == null) {
            return false;
        }
        for (String str : f4511a) {
            if (str.equals(context.getPackageName())) {
                return true;
            }
        }
        return false;
    }

    public void j() {
        if (!t()) {
            k();
            return;
        }
        if (!h()) {
            this.f4520j.offer(new r1.a(this, "dismissAllDialog", null));
        } else {
            try {
                this.f4518h.t();
            } catch (RemoteException e3) {
                e3.printStackTrace();
            }
        }
    }

    public final int k() {
        Log.i("BydSettingsApi", "---dismissAllDialogOnOldApi---");
        r("dismiss_all_dialog");
        return 0;
    }

    public int m(Context context) {
        if (context == null) {
            return 0;
        }
        return Settings.System.getInt(context.getContentResolver(), "UE_API_VERSION", 0);
    }

    public final Object n(Object obj, String str) {
        try {
            Method method = obj.getClass().getMethod(str, new Class[0]);
            method.setAccessible(true);
            return method.invoke(obj, new Object[0]);
        } catch (Exception e3) {
            Log.e("BydSettingsApi", ", invoke e: " + e3.toString());
            return null;
        }
    }

    public final void p(r1.a aVar) {
        if (aVar == null || aVar.f4834a == null || TextUtils.isEmpty(aVar.f4835b)) {
            return;
        }
        Object obj = aVar.f4834a;
        String str = aVar.f4835b;
        Class<?>[] clsArr = aVar.f4837d;
        Object[] objArr = aVar.f4836c;
        if (clsArr == null || clsArr.length <= 0 || objArr == null || objArr.length != clsArr.length) {
            o(obj, str, clsArr, objArr);
        } else {
            n(obj, str);
        }
    }

    public void q(boolean z3) {
        this.f4515e = z3;
    }

    public final int r(String str) {
        return s(str, null);
    }

    public final int s(String str, HashMap<String, String> map) {
        Context context = this.f4517g;
        if (context == null) {
            return -1;
        }
        PackageManager packageManager = context.getPackageManager();
        StringBuffer stringBuffer = new StringBuffer("byd_pad://byd_car_settings:2933/launcher");
        stringBuffer.append("?");
        stringBuffer.append("action");
        stringBuffer.append("=");
        stringBuffer.append(str);
        if (map != null && map.size() > 0) {
            for (String str2 : map.keySet()) {
                stringBuffer.append("&");
                stringBuffer.append(str2);
                stringBuffer.append("=");
                stringBuffer.append(map.get(str2));
            }
        }
        Log.d("BydSettingsApi", "----startServiceApi----scheme = " + stringBuffer.toString());
        Intent intent = new Intent("android.intent.action.VIEW", Uri.parse(stringBuffer.toString()));
        intent.setComponent(new ComponentName("com.byd.carsettings", "com.byd.dialogservice.DialogService"));
        List<ResolveInfo> listQueryIntentServices = packageManager.queryIntentServices(intent, 0);
        Log.d("BydSettingsApi", "----startServiceApi----resolveInfos = " + listQueryIntentServices);
        if (listQueryIntentServices.isEmpty()) {
            return -1;
        }
        this.f4517g.startService(intent);
        return 0;
    }

    public boolean t() {
        return this.f4516f >= 1;
    }
}
