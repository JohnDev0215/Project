package d.a.a;

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
import d.a.a.b;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.LinkedList;

/* JADX INFO: compiled from: BydSettingsApi.java */
/* JADX INFO: loaded from: classes.dex */
public final class a {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private static final String[] f4663a = {"com.byd.carsettings", "com.byd.bluetoothcall", "com.android.systemui", "com.byd.localmusic"};

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public static final String[] f4664b = {"screen_saver", "finish_screen_saver", "Bluetooth", "dismiss_bt_settings", "WiFi", "dismiss_wifi_settings", "Hotspot", "dismiss_hotspot_settings", "car_play", "dismiss_carplay", "cpaa", "dismiss_cpaa_settings", "privacy_manage", "dismiss_all_dialog"};

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    private static volatile a f4665c = null;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    private static volatile boolean f4666d = false;

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    private d.a.a.b f4670h;
    private c i;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    private volatile boolean f4667e = false;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    private int f4668f = 0;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    private Context f4669g = null;
    private LinkedList<d.a.a.d.a> j = new LinkedList<>();
    private d.a.a.c.a k = new d.a.a.c.a();
    private ServiceConnection l = new ServiceConnectionC0082a();
    private ServiceConnection m = new b();

    /* JADX INFO: renamed from: d.a.a.a$a, reason: collision with other inner class name */
    /* JADX INFO: compiled from: BydSettingsApi.java */
    class ServiceConnectionC0082a implements ServiceConnection {
        ServiceConnectionC0082a() {
        }

        @Override // android.content.ServiceConnection
        public void onServiceConnected(ComponentName componentName, IBinder iBinder) {
            boolean unused = a.f4666d = true;
            Log.i("BydSettingsApi", "---onServiceConnected---name: " + componentName + ", service: " + iBinder);
            a.this.f4670h = b.a.h(iBinder);
            if (a.this.i != null) {
                a.this.i.a(true);
            }
            if (a.this.j != null && !a.this.j.isEmpty()) {
                Log.w("BydSettingsApi", "---onServiceConnected---callsDisconnected.size: " + a.this.j.size());
                for (int i = 0; i < 10; i++) {
                    d.a.a.d.a aVar = (d.a.a.d.a) a.this.j.poll();
                    Log.w("BydSettingsApi", "---onServiceConnected---call: " + aVar);
                    if (aVar == null) {
                        break;
                    }
                    a.this.n(aVar);
                }
                Log.w("BydSettingsApi", "---onServiceConnected--recall completed. callsDisconnected.size: " + a.this.j.size());
            }
            try {
                a.this.k.a(componentName.toString());
                iBinder.linkToDeath(a.this.k, 0);
            } catch (RemoteException e2) {
                e2.printStackTrace();
                Log.e("BydSettingsApi", "---onServiceConnected---e: " + e2.toString());
            }
        }

        @Override // android.content.ServiceConnection
        public void onServiceDisconnected(ComponentName componentName) {
            boolean unused = a.f4666d = false;
            Log.e("BydSettingsApi", "---onServiceDisconnected---name: " + componentName);
            if (a.this.i != null) {
                a.this.i.a(false);
            }
        }
    }

    /* JADX INFO: compiled from: BydSettingsApi.java */
    class b implements ServiceConnection {
        b() {
        }

        @Override // android.content.ServiceConnection
        public void onServiceConnected(ComponentName componentName, IBinder iBinder) {
            Log.i("BydSettingsApi", "---onServiceConnected---service: " + iBinder);
            try {
                a.this.k.a(componentName.toString());
                iBinder.linkToDeath(a.this.k, 0);
            } catch (RemoteException e2) {
                e2.printStackTrace();
                Log.e("BydSettingsApi", "---onServiceConnected---e: " + e2.toString());
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

    private a() {
    }

    private boolean h(Context context) {
        if (context == null) {
            return false;
        }
        for (String str : f4663a) {
            if (str.equals(context.getPackageName())) {
                return true;
            }
        }
        return false;
    }

    public static a i(Context context, c cVar) {
        Context applicationContext = context.getApplicationContext();
        if (f4665c == null) {
            synchronized (a.class) {
                if (f4665c == null) {
                    f4665c = new a();
                    f4665c.g(applicationContext);
                }
            }
        }
        if (!f4666d) {
            f4665c.g(applicationContext);
        }
        f4665c.i = cVar;
        return f4665c;
    }

    private Object l(Object obj, String str) {
        try {
            Method method = obj.getClass().getMethod(str, new Class[0]);
            method.setAccessible(true);
            return method.invoke(obj, new Object[0]);
        } catch (Exception e2) {
            Log.e("BydSettingsApi", ", invoke e: " + e2.toString());
            return null;
        }
    }

    public static Object m(Object obj, String str, Class<?>[] clsArr, Object[] objArr) {
        try {
            Method method = obj.getClass().getMethod(str, clsArr);
            method.setAccessible(true);
            return method.invoke(obj, objArr);
        } catch (IllegalAccessException | NoSuchMethodException | InvocationTargetException e2) {
            Log.e("BydSettingsApi", ", Invoke param arr failed : " + e2.toString());
            return null;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void n(d.a.a.d.a aVar) {
        if (aVar == null || aVar.f4676a == null || TextUtils.isEmpty(aVar.f4677b)) {
            return;
        }
        Object obj = aVar.f4676a;
        String str = aVar.f4677b;
        Class<?>[] clsArr = aVar.f4679d;
        Object[] objArr = aVar.f4678c;
        if (clsArr == null || clsArr.length <= 0 || objArr == null || objArr.length != clsArr.length) {
            m(obj, str, clsArr, objArr);
        } else {
            l(obj, str);
        }
    }

    public void g(Context context) {
        Log.i("BydSettingsApi", "---bindService---isServiceConnected: " + f4666d);
        if (context == null || f4666d) {
            return;
        }
        this.f4669g = context.getApplicationContext();
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
            } catch (IllegalAccessException | NoSuchMethodException | InvocationTargetException e2) {
                Log.e("BydSettingsApi", ", Invoke param arr failed : " + e2.toString());
                Log.i("BydSettingsApi", "---do as normal bindService---");
                context.bindService(intent, this.l, 1);
            }
            parcelObtain.recycle();
        } else {
            context.bindService(intent, this.l, 1);
        }
        this.f4668f = j(context);
        Log.i("BydSettingsApi", "---bindService---ueApiVersion: " + this.f4668f);
    }

    public int j(Context context) {
        if (context == null) {
            return 0;
        }
        return Settings.System.getInt(context.getContentResolver(), "UE_API_VERSION", 0);
    }

    public int k() {
        Context context = this.f4669g;
        if (context != null) {
            return Settings.Global.getInt(context.getContentResolver(), "navibar_mode_on_screensaver", -1);
        }
        Log.e("BydSettingsApi", "---getScreensaverState---Context is null.");
        return -1;
    }

    public void o(boolean z) {
        this.f4667e = z;
    }
}
