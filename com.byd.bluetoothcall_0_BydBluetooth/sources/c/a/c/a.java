package c.a.c;

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
import c.a.c.b;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;

/* JADX INFO: compiled from: BydSettingsApi.java */
/* JADX INFO: loaded from: classes.dex */
public final class a {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private static final String[] f2614a = {"com.byd.carsettings", "com.byd.bluetoothcall", "com.android.systemui", "com.byd.localmusic"};

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public static final String[] f2615b = {"screen_saver", "finish_screen_saver", "Bluetooth", "dismiss_bt_settings", "WiFi", "dismiss_wifi_settings", "Hotspot", "dismiss_hotspot_settings", "car_play", "dismiss_carplay", "cpaa", "dismiss_cpaa_settings", "privacy_manage", "dismiss_all_dialog"};

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    private static volatile a f2616c = null;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    private static volatile boolean f2617d = false;
    private c.a.c.b h;
    private c i;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    private volatile boolean f2618e = false;
    private int f = 0;
    private Context g = null;
    private LinkedList<c.a.c.d.a> j = new LinkedList<>();
    private c.a.c.c.a k = new c.a.c.c.a();
    private ServiceConnection l = new ServiceConnectionC0068a();
    private ServiceConnection m = new b();

    /* JADX INFO: renamed from: c.a.c.a$a, reason: collision with other inner class name */
    /* JADX INFO: compiled from: BydSettingsApi.java */
    class ServiceConnectionC0068a implements ServiceConnection {
        ServiceConnectionC0068a() {
        }

        @Override // android.content.ServiceConnection
        public void onServiceConnected(ComponentName componentName, IBinder iBinder) {
            boolean unused = a.f2617d = true;
            Log.i("BydSettingsApi", "---onServiceConnected---name: " + componentName + ", service: " + iBinder);
            a.this.h = b.a.c0(iBinder);
            if (a.this.i != null) {
                a.this.i.a(true);
            }
            if (a.this.j != null && !a.this.j.isEmpty()) {
                Log.w("BydSettingsApi", "---onServiceConnected---callsDisconnected.size: " + a.this.j.size());
                for (int i = 0; i < 10; i++) {
                    c.a.c.d.a aVar = (c.a.c.d.a) a.this.j.poll();
                    Log.w("BydSettingsApi", "---onServiceConnected---call: " + aVar);
                    if (aVar == null) {
                        break;
                    }
                    a.this.p(aVar);
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
            boolean unused = a.f2617d = false;
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

    private boolean h() {
        if (this.h != null && f2617d) {
            return true;
        }
        Log.e("BydSettingsApi", "BydSettingsApi service is disconnected ,and doing reconnect.");
        g(this.g);
        return false;
    }

    private boolean i(Context context) {
        if (context == null) {
            return false;
        }
        for (String str : f2614a) {
            if (str.equals(context.getPackageName())) {
                return true;
            }
        }
        return false;
    }

    private int k() {
        Log.i("BydSettingsApi", "---dismissAllDialogOnOldApi---");
        t("dismiss_all_dialog");
        return 0;
    }

    public static a l(Context context, c cVar) {
        Context applicationContext = context.getApplicationContext();
        if (f2616c == null) {
            synchronized (a.class) {
                if (f2616c == null) {
                    f2616c = new a();
                    f2616c.g(applicationContext);
                }
            }
        }
        if (!f2617d) {
            f2616c.g(applicationContext);
        }
        f2616c.i = cVar;
        return f2616c;
    }

    private Object n(Object obj, String str) {
        try {
            Method method = obj.getClass().getMethod(str, new Class[0]);
            method.setAccessible(true);
            return method.invoke(obj, new Object[0]);
        } catch (Exception e2) {
            Log.e("BydSettingsApi", ", invoke e: " + e2.toString());
            return null;
        }
    }

    public static Object o(Object obj, String str, Class<?>[] clsArr, Object[] objArr) {
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
    public void p(c.a.c.d.a aVar) {
        if (aVar == null || aVar.f2625a == null || TextUtils.isEmpty(aVar.f2626b)) {
            return;
        }
        Object obj = aVar.f2625a;
        String str = aVar.f2626b;
        Class<?>[] clsArr = aVar.f2628d;
        Object[] objArr = aVar.f2627c;
        if (clsArr == null || clsArr.length <= 0 || objArr == null || objArr.length != clsArr.length) {
            o(obj, str, clsArr, objArr);
        } else {
            n(obj, str);
        }
    }

    private int s() {
        Log.i("BydSettingsApi", "---showBtSettingsOnOldApi---");
        return t("Bluetooth");
    }

    private int t(String str) {
        return u(str, null);
    }

    private int u(String str, HashMap<String, String> map) {
        Context context = this.g;
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
        this.g.startService(intent);
        return 0;
    }

    public void g(Context context) {
        Log.i("BydSettingsApi", "---bindService---isServiceConnected: " + f2617d);
        if (context == null || f2617d) {
            return;
        }
        this.g = context.getApplicationContext();
        Intent intent = new Intent();
        intent.setAction("com.byd.action.ACTION_BYD_SYSTEM_SETTING");
        intent.setComponent(new ComponentName("com.byd.uereport", "com.byd.uereport.MultiModuleService"));
        boolean zI = i(context);
        Log.i("BydSettingsApi", "---bindService---systemApp: " + zI);
        if (zI) {
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
        this.f = m(context);
        Log.i("BydSettingsApi", "---bindService---ueApiVersion: " + this.f);
    }

    public void j() {
        if (!v()) {
            k();
            return;
        }
        if (!h()) {
            this.j.offer(new c.a.c.d.a(this, "dismissAllDialog", null));
        } else {
            try {
                this.h.v();
            } catch (RemoteException e2) {
                e2.printStackTrace();
            }
        }
    }

    public int m(Context context) {
        if (context == null) {
            return 0;
        }
        return Settings.System.getInt(context.getContentResolver(), "UE_API_VERSION", 0);
    }

    public void q(boolean z) {
        this.f2618e = z;
    }

    public void r() {
        Log.i("BydSettingsApi", "---showBtSettings---");
        if (!v()) {
            s();
            return;
        }
        if (!h()) {
            this.j.offer(new c.a.c.d.a(this, "showBtSettings", null));
        } else {
            try {
                this.h.l();
            } catch (Exception e2) {
                e2.printStackTrace();
            }
        }
    }

    public boolean v() {
        return this.f >= 1;
    }
}
