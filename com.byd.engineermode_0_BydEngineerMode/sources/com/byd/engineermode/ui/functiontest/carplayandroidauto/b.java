package com.byd.engineermode.ui.functiontest.carplayandroidauto;

import android.content.Context;
import android.os.Handler;
import android.os.Message;
import android.os.RemoteException;
import android.view.textclassifier.Log;
import com.ts.car.core.ServiceProvider;
import ts.car.service.IPlatformService;
import ts.car.service.IServiceConnectionListener;
import ts.car.service.carplay.ICarPlayAppManager;
import ts.car.service.carplay.ICarPlayStateListener;

/* JADX INFO: compiled from: CarPlayAppManager.java */
/* JADX INFO: loaded from: classes.dex */
public class b implements ICarPlayStateListener {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private Context f2443a;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    private Handler f2446d;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private ICarPlayAppManager f2444b = null;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    private boolean f2445c = false;
    private final IServiceConnectionListener e = new a();

    /* JADX INFO: compiled from: CarPlayAppManager.java */
    class a implements IServiceConnectionListener {
        a() {
        }

        public void onServiceConnected(IPlatformService iPlatformService) {
            b.this.f2445c = true;
            try {
                b.this.f2444b.registerCarPlayStateListener(b.this);
                Message message = new Message();
                message.what = 1001;
                message.obj = Integer.valueOf(b.this.f2444b.getCarPlaySessionStatus());
                b.this.f2446d.sendMessage(message);
            } catch (RemoteException e) {
                e.printStackTrace();
            }
        }

        public void onServiceDisconnected(IPlatformService iPlatformService) {
            b.this.f2445c = false;
            try {
                b.this.f2444b.unregisterCarPlayStateListener(b.this);
            } catch (RemoteException e) {
                e.printStackTrace();
            }
        }
    }

    public b(Context context, Handler handler) {
        this.f2443a = context;
        this.f2446d = handler;
        d();
    }

    private void d() {
        ICarPlayAppManager iCarPlayAppManager = (ICarPlayAppManager) ServiceProvider.get(ICarPlayAppManager.class, this.f2443a);
        this.f2444b = iCarPlayAppManager;
        if (iCarPlayAppManager != null) {
            iCarPlayAppManager.registerConnectionListener(this.e);
        }
    }

    public void onSessionStateChanged(int i) {
        Log.d("CarPlayAppManager", "onSessionStateChanged status = " + i);
        Message message = new Message();
        message.what = 1001;
        message.obj = Integer.valueOf(i);
        this.f2446d.sendMessage(message);
    }
}
