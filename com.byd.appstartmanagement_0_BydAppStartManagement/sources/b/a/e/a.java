package b.a.e;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.IBinder;
import android.os.Parcel;
import android.os.RemoteException;
import android.os.UserHandle;
import android.provider.Settings;
import android.text.TextUtils;
import android.util.Log;
import b.a.e.b;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.LinkedList;

/* JADX INFO: compiled from: BydSettingsApi.java */
/* JADX INFO: loaded from: classes2.dex */
public final class a {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static final String[] f363a = {"com.byd.carsettings", "com.byd.bluetoothcall", "com.android.systemui", "com.byd.localmusic"};

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public static final String[] f364b = {"screen_saver", "finish_screen_saver", "Bluetooth", "dismiss_bt_settings", "WiFi", "dismiss_wifi_settings", "Hotspot", "dismiss_hotspot_settings", "car_play", "dismiss_carplay", "cpaa", "dismiss_cpaa_settings", "privacy_manage", "dismiss_all_dialog"};

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public static volatile a f365c = null;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public static volatile boolean f366d = false;
    public b.a.e.b h;
    public c i;
    public volatile boolean e = false;
    public int f = 0;
    public Context g = null;
    public LinkedList<b.a.e.d.a> j = new LinkedList<>();
    public b.a.e.c.a k = new b.a.e.c.a();
    public ServiceConnection l = new ServiceConnectionC0025a();
    public ServiceConnection m = new b();

    /* JADX INFO: renamed from: b.a.e.a$a, reason: collision with other inner class name */
    /* JADX INFO: compiled from: BydSettingsApi.java */
    public class ServiceConnectionC0025a implements ServiceConnection {
        public ServiceConnectionC0025a() {
        }

        @Override // android.content.ServiceConnection
        public void onServiceConnected(ComponentName componentName, IBinder iBinder) {
            boolean unused = a.f366d = true;
            Log.i("BydSettingsApi", "---onServiceConnected---name: " + componentName + ", service: " + iBinder);
            a.this.h = b.a.B(iBinder);
            if (a.this.i != null) {
                a.this.i.a(true);
            }
            if (a.this.j != null && !a.this.j.isEmpty()) {
                Log.w("BydSettingsApi", "---onServiceConnected---callsDisconnected.size: " + a.this.j.size());
                for (int i = 0; i < 10; i++) {
                    b.a.e.d.a aVar = (b.a.e.d.a) a.this.j.poll();
                    Log.w("BydSettingsApi", "---onServiceConnected---call: " + aVar);
                    if (aVar == null) {
                        break;
                    }
                    a.this.m(aVar);
                }
                Log.w("BydSettingsApi", "---onServiceConnected--recall completed. callsDisconnected.size: " + a.this.j.size());
            }
            try {
                a.this.k.a(componentName.toString());
                iBinder.linkToDeath(a.this.k, 0);
            } catch (RemoteException e) {
                e.printStackTrace();
                Log.e("BydSettingsApi", "---onServiceConnected---e: " + e.toString());
            }
        }

        @Override // android.content.ServiceConnection
        public void onServiceDisconnected(ComponentName componentName) {
            boolean unused = a.f366d = false;
            Log.e("BydSettingsApi", "---onServiceDisconnected---name: " + componentName);
            if (a.this.i != null) {
                a.this.i.a(false);
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
                a.this.k.a(componentName.toString());
                iBinder.linkToDeath(a.this.k, 0);
            } catch (RemoteException e) {
                e.printStackTrace();
                Log.e("BydSettingsApi", "---onServiceConnected---e: " + e.toString());
            }
        }

        @Override // android.content.ServiceConnection
        public void onServiceDisconnected(ComponentName componentName) {
            Log.i("BydSettingsApi", "---onServiceDisconnected---name: " + componentName);
        }
    }

    /* JADX INFO: compiled from: BydSettingsApi.java */
    public interface c {
        void a(boolean z);
    }

    public static a i(Context context, c cVar) {
        Context applicationContext = context.getApplicationContext();
        if (f365c == null) {
            synchronized (a.class) {
                if (f365c == null) {
                    f365c = new a();
                    f365c.g(applicationContext);
                }
            }
        }
        if (!f366d) {
            f365c.g(applicationContext);
        }
        f365c.i = cVar;
        return f365c;
    }

    public static Object l(Object obj, String str, Class<?>[] clsArr, Object[] objArr) {
        try {
            Method method = obj.getClass().getMethod(str, clsArr);
            method.setAccessible(true);
            return method.invoke(obj, objArr);
        } catch (IllegalAccessException | NoSuchMethodException | InvocationTargetException e) {
            Log.e("BydSettingsApi", ", Invoke param arr failed : " + e.toString());
            return null;
        }
    }

    public void g(Context context) {
        Log.i("BydSettingsApi", "---bindService---isServiceConnected: " + f366d);
        if (context == null || f366d) {
            return;
        }
        this.g = context.getApplicationContext();
        Intent intent = new Intent();
        intent.setAction("com.byd.action.ACTION_BYD_SYSTEM_SETTING");
        intent.setComponent(new ComponentName("com.byd.uereport", "com.byd.uereport.MultiModuleService"));
        boolean zH = h(context);
        Log.i("BydSettingsApi", "---bindService---systemApp: " + zH);
        if (zH) {
            Parcel parcelObtain = Parcel.obtain();
            Class<?>[] clsArr = {Intent.class, ServiceConnection.class, Integer.TYPE, UserHandle.class};
            Object[] objArr = {intent, this.l, 1, UserHandle.readFromParcel(parcelObtain)};
            try {
                Method method = context.getClass().getMethod("bindServiceAsUser", clsArr);
                method.setAccessible(true);
                method.invoke(context, objArr);
            } catch (IllegalAccessException | NoSuchMethodException | InvocationTargetException e) {
                Log.e("BydSettingsApi", ", Invoke param arr failed : " + e.toString());
                Log.i("BydSettingsApi", "---do as normal bindService---");
                context.bindService(intent, this.l, 1);
            }
            parcelObtain.recycle();
        } else {
            context.bindService(intent, this.l, 1);
        }
        this.f = j(context);
        Log.i("BydSettingsApi", "---bindService---ueApiVersion: " + this.f);
    }

    public final boolean h(Context context) {
        if (context == null) {
            return false;
        }
        for (String str : f363a) {
            if (str.equals(context.getPackageName())) {
                return true;
            }
        }
        return false;
    }

    public int j(Context context) {
        if (context == null) {
            return 0;
        }
        return Settings.System.getInt(context.getContentResolver(), "UE_API_VERSION", 0);
    }

    public final Object k(Object obj, String str) {
        try {
            Method method = obj.getClass().getMethod(str, new Class[0]);
            method.setAccessible(true);
            return method.invoke(obj, new Object[0]);
        } catch (Exception e) {
            Log.e("BydSettingsApi", ", invoke e: " + e.toString());
            return null;
        }
    }

    public final void m(b.a.e.d.a aVar) {
        if (aVar == null || aVar.f372a == null || TextUtils.isEmpty(aVar.f373b)) {
            return;
        }
        Object obj = aVar.f372a;
        String str = aVar.f373b;
        Class<?>[] clsArr = aVar.f375d;
        Object[] objArr = aVar.f374c;
        if (clsArr == null || clsArr.length <= 0 || objArr == null || objArr.length != clsArr.length) {
            l(obj, str, clsArr, objArr);
        } else {
            k(obj, str);
        }
    }

    public void n(boolean z) {
        this.e = z;
    }
}
