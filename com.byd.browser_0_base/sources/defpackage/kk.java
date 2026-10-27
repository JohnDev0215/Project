package defpackage;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;
import android.os.UserHandle;
import android.provider.Settings;
import android.text.TextUtils;
import android.util.Log;
import defpackage.lw0;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.LinkedList;

/* JADX INFO: compiled from: BydSettingsApi.java */
/* JADX INFO: loaded from: classes.dex */
public final class kk {
    public static final String[] g = {"com.byd.carsettings", "com.byd.bluetoothcall", "com.android.systemui", "com.byd.localmusic"};
    public static volatile kk h = null;
    public static volatile boolean i = false;
    public lw0 c;
    public int a = 0;
    public Context b = null;
    public final LinkedList<h6> d = new LinkedList<>();
    public final a70 e = new a70();
    public final a f = new a();

    /* JADX INFO: compiled from: BydSettingsApi.java */
    public class a implements ServiceConnection {
        public a() {
        }

        @Override // android.content.ServiceConnection
        public final void onServiceConnected(ComponentName componentName, IBinder iBinder) {
            lw0 c0065a;
            kk.i = true;
            Log.i("BydSettingsApi", "---onServiceConnected---name: " + componentName + ", service: " + iBinder);
            kk kkVar = kk.this;
            int i = lw0.a.e;
            if (iBinder == null) {
                c0065a = null;
            } else {
                IInterface iInterfaceQueryLocalInterface = iBinder.queryLocalInterface("com.byd.systemsettings.ISystemSettings");
                c0065a = (iInterfaceQueryLocalInterface == null || !(iInterfaceQueryLocalInterface instanceof lw0)) ? new lw0.a.C0065a(iBinder) : (lw0) iInterfaceQueryLocalInterface;
            }
            kkVar.c = c0065a;
            kk.this.getClass();
            LinkedList<h6> linkedList = kk.this.d;
            if (linkedList != null && !linkedList.isEmpty()) {
                Log.w("BydSettingsApi", "---onServiceConnected---callsDisconnected.size: " + kk.this.d.size());
                for (int i2 = 0; i2 < 10; i2++) {
                    h6 h6VarPoll = kk.this.d.poll();
                    Log.w("BydSettingsApi", "---onServiceConnected---call: " + h6VarPoll);
                    if (h6VarPoll == null) {
                        break;
                    }
                    kk.this.getClass();
                    Object obj = h6VarPoll.a;
                    if (obj != null) {
                        String str = h6VarPoll.b;
                        if (!TextUtils.isEmpty(str)) {
                            h6VarPoll.getClass();
                            h6VarPoll.getClass();
                            try {
                                Method method = obj.getClass().getMethod(str, null);
                                method.setAccessible(true);
                                method.invoke(obj, null);
                            } catch (IllegalAccessException | NoSuchMethodException | InvocationTargetException e) {
                                Log.e("BydSettingsApi", ", Invoke param arr failed : " + e.toString());
                            }
                        }
                    }
                }
                Log.w("BydSettingsApi", "---onServiceConnected--recall completed. callsDisconnected.size: " + kk.this.d.size());
            }
            try {
                a70 a70Var = kk.this.e;
                componentName.toString();
                a70Var.getClass();
                iBinder.linkToDeath(kk.this.e, 0);
            } catch (RemoteException e2) {
                e2.printStackTrace();
                Log.e("BydSettingsApi", "---onServiceConnected---e: " + e2.toString());
            }
        }

        @Override // android.content.ServiceConnection
        public final void onServiceDisconnected(ComponentName componentName) {
            kk.i = false;
            Log.e("BydSettingsApi", "---onServiceDisconnected---name: " + componentName);
            kk.this.getClass();
        }
    }

    public static kk b(Context context) {
        Context applicationContext = context.getApplicationContext();
        if (h == null) {
            synchronized (kk.class) {
                if (h == null) {
                    h = new kk();
                    h.a(applicationContext);
                }
            }
        }
        if (!i) {
            h.a(applicationContext);
        }
        h.getClass();
        return h;
    }

    public final void a(Context context) {
        boolean z;
        Log.i("BydSettingsApi", "---bindService---isServiceConnected: " + i);
        if (context == null || i) {
            return;
        }
        this.b = context.getApplicationContext();
        Intent intent = new Intent();
        intent.setAction("com.byd.action.ACTION_BYD_SYSTEM_SETTING");
        intent.setComponent(new ComponentName("com.byd.uereport", "com.byd.uereport.MultiModuleService"));
        String[] strArr = g;
        int i2 = 0;
        while (true) {
            if (i2 >= 4) {
                z = false;
                break;
            } else {
                if (strArr[i2].equals(context.getPackageName())) {
                    z = true;
                    break;
                }
                i2++;
            }
        }
        Log.i("BydSettingsApi", "---bindService---systemApp: " + z);
        if (z) {
            Parcel parcelObtain = Parcel.obtain();
            Class<?>[] clsArr = {Intent.class, ServiceConnection.class, Integer.TYPE, UserHandle.class};
            Object[] objArr = {intent, this.f, 1, UserHandle.readFromParcel(parcelObtain)};
            try {
                Method method = context.getClass().getMethod("bindServiceAsUser", clsArr);
                method.setAccessible(true);
                method.invoke(context, objArr);
            } catch (IllegalAccessException | NoSuchMethodException | InvocationTargetException e) {
                Log.e("BydSettingsApi", ", Invoke param arr failed : " + e.toString());
                Log.i("BydSettingsApi", "---do as normal bindService---");
                context.bindService(intent, this.f, 1);
            }
            parcelObtain.recycle();
        } else {
            context.bindService(intent, this.f, 1);
        }
        this.a = Settings.System.getInt(context.getContentResolver(), "UE_API_VERSION", 0);
        Log.i("BydSettingsApi", "---bindService---ueApiVersion: " + this.a);
    }
}
