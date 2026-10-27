package com.byd.engineermode.ui.functiontest.carplayandroidauto;

import android.content.Context;
import android.os.Handler;
import android.os.Message;
import android.os.RemoteException;
import android.view.textclassifier.Log;
import com.ts.car.core.ServiceProvider;
import ts.car.service.IPlatformService;
import ts.car.service.IServiceConnectionListener;
import ts.car.service.androidauto.IAndroidAutoAppManager;
import ts.car.service.androidauto.IAndroidAutoSessionStatusListener;

/* JADX INFO: compiled from: AndroidAutoAppManager.java */
/* JADX INFO: loaded from: classes.dex */
public class a implements IAndroidAutoSessionStatusListener {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private Context f2438a;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    private Handler f2441d;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private IAndroidAutoAppManager f2439b = null;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    private boolean f2440c = false;
    private final IServiceConnectionListener e = new C0071a();

    /* JADX INFO: renamed from: com.byd.engineermode.ui.functiontest.carplayandroidauto.a$a, reason: collision with other inner class name */
    /* JADX INFO: compiled from: AndroidAutoAppManager.java */
    class C0071a implements IServiceConnectionListener {
        C0071a() {
        }

        public void onServiceConnected(IPlatformService iPlatformService) {
            a.this.f2440c = true;
            Log.d("AndroidAutoAppManager", "mAndroidAutoServiceConnectionListener mIsConnected is true");
            try {
                a.this.f2439b.registerAndroidAutoSessionStatusListener(a.this);
                Message message = new Message();
                message.what = 1002;
                message.obj = Integer.valueOf(a.this.f2439b.getAndroidAutoSessionStatus());
                a.this.f2441d.sendMessage(message);
            } catch (RemoteException e) {
                e.printStackTrace();
            }
        }

        public void onServiceDisconnected(IPlatformService iPlatformService) {
            a.this.f2440c = false;
            try {
                a.this.f2439b.registerAndroidAutoSessionStatusListener(a.this);
            } catch (RemoteException e) {
                e.printStackTrace();
            }
        }
    }

    public a(Context context, Handler handler) {
        this.f2438a = context;
        this.f2441d = handler;
        d();
    }

    private void d() {
        IAndroidAutoAppManager iAndroidAutoAppManager = (IAndroidAutoAppManager) ServiceProvider.get(IAndroidAutoAppManager.class, this.f2438a);
        this.f2439b = iAndroidAutoAppManager;
        if (iAndroidAutoAppManager != null) {
            iAndroidAutoAppManager.registerConnectionListener(this.e);
        }
    }

    public void onSessionStatusChanged(int i, int i2, String str) {
        Log.d("AndroidAutoAppManager", "onSessionStatusChanged: status=" + i + " errorCode=" + i2 + " message=" + str);
        Message message = new Message();
        message.what = 1002;
        message.obj = Integer.valueOf(i);
        this.f2441d.sendMessage(message);
    }
}
