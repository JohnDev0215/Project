package com.qtxa.service;

import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;
import android.os.RemoteException;
import android.os.SystemClock;
import com.qtxa.qtxadefend.w;
import com.qtxa.service.b;
import com.qtxa.service.c;
import java.util.HashMap;

/* JADX INFO: loaded from: classes.dex */
public class HandlerRequestService extends Service {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private HashMap<String, IHandlerRequest> f162a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private final c.a f163b = new a();

    class a extends c.a {

        /* JADX INFO: renamed from: com.qtxa.service.HandlerRequestService$a$a, reason: collision with other inner class name */
        class C0020a extends b.AbstractRunnableC0021b<String, e, d> {
            final /* synthetic */ e d;

            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            C0020a(String str, e eVar, d dVar, e eVar2) {
                super(str, eVar, dVar);
                this.d = eVar2;
            }

            /* JADX WARN: Multi-variable type inference failed */
            @Override // java.lang.Runnable
            public void run() {
                if (!HandlerRequestService.this.f162a.containsKey(((e) this.f171b).b())) {
                    w.d("exec sync call " + this.d.b() + " failed, impl not found");
                    return;
                }
                try {
                    long jElapsedRealtime = SystemClock.elapsedRealtime();
                    String strOnCall = ((IHandlerRequest) HandlerRequestService.this.f162a.get(((e) this.f171b).b())).onCall((String) this.f170a, (e) this.f171b);
                    if (SystemClock.elapsedRealtime() - jElapsedRealtime > 1000) {
                        w.d("exec async call " + this.d.b() + ":" + this.d.a() + " timeout");
                    }
                    if (this.c != 0) {
                        ((d) this.c).i(strOnCall);
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }

        class b extends b.a<String, d> {
            b(String str, d dVar) {
                super(str, dVar);
            }

            /* JADX WARN: Multi-variable type inference failed */
            @Override // java.lang.Runnable
            public void run() {
                if (!HandlerRequestService.this.f162a.containsKey(this.f168a)) {
                    w.d("exec sync call " + ((String) this.f168a) + " failed, impl not found");
                    return;
                }
                long jElapsedRealtime = SystemClock.elapsedRealtime();
                ((IHandlerRequest) HandlerRequestService.this.f162a.get(this.f168a)).addCommCallback((d) this.f169b);
                if (SystemClock.elapsedRealtime() - jElapsedRealtime > 1000) {
                    w.d("exec async call " + ((String) this.f168a) + ":add callback timeout");
                }
            }
        }

        a() {
        }

        @Override // com.qtxa.service.c
        public void d(String str, d dVar) throws RemoteException {
            w.d("async call data::" + str);
            try {
                e eVar = new e(str);
                HandlerRequestService.this.d(HandlerRequestService.this.e(eVar.b()));
                com.qtxa.service.b.a().post(new C0020a(str, eVar, dVar, eVar));
            } catch (Exception e) {
                throw new RemoteException(e.getMessage());
            }
        }

        @Override // com.qtxa.service.c
        public String t(String str) throws RemoteException {
            w.d("sync call data::" + str);
            try {
                e eVar = new e(str);
                HandlerRequestService.this.d(HandlerRequestService.this.e(eVar.b()));
                if (!HandlerRequestService.this.f162a.containsKey(eVar.b())) {
                    w.d("exec sync call " + eVar.b() + " failed, impl not found");
                    return null;
                }
                long jElapsedRealtime = SystemClock.elapsedRealtime();
                String strOnCall = ((IHandlerRequest) HandlerRequestService.this.f162a.get(eVar.b())).onCall(str, eVar);
                if (SystemClock.elapsedRealtime() - jElapsedRealtime > 1000) {
                    w.d("exec sync call " + eVar.b() + ":" + eVar.a() + " timeout");
                }
                return strOnCall;
            } catch (Exception e) {
                throw new RemoteException(e.getMessage());
            }
        }

        @Override // com.qtxa.service.c
        public void v(String str, d dVar) throws RemoteException {
            try {
                HandlerRequestService.this.d(HandlerRequestService.this.e(str));
                com.qtxa.service.b.a().post(new b(str, dVar));
            } catch (Exception e) {
                throw new RemoteException(e.getMessage());
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public boolean d(String str) {
        int callingUid = Binder.getCallingUid();
        w.d("calling uid::" + callingUid + " package::" + getPackageManager().getNameForUid(callingUid));
        return true;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public String e(String str) {
        if ("wifi".equals(str)) {
            return "com.qtxa.hidps.permission.WIFI_MONITOR";
        }
        if ("bluetooth".equals(str)) {
            return "com.qtxa.hidps.permission.BLUETOOTH_MONITOR";
        }
        if ("location".equals(str)) {
            return "com.qtxa.hidps.permission.LOCATION_MONITOR";
        }
        if ("usb".equals(str)) {
            return "com.qtxa.hidps.permission.USB_MONITOR";
        }
        if ("mobile".equals(str)) {
            return "com.qtxa.hidps.permission.MOBILE_NETWORK_MONITOR";
        }
        return null;
    }

    @Override // android.app.Service
    public IBinder onBind(Intent intent) {
        return this.f163b;
    }

    @Override // android.app.Service
    public void onCreate() {
        super.onCreate();
        w.d("HandlerRequestService");
        this.f162a = com.qtxa.annotation.c.a(IHandlerRequest.class);
    }

    @Override // android.app.Service
    public void onDestroy() {
        super.onDestroy();
    }

    @Override // android.app.Service
    public int onStartCommand(Intent intent, int i, int i2) {
        return 1;
    }
}
