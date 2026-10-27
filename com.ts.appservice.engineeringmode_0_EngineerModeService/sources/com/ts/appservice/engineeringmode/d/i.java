package com.ts.appservice.engineeringmode.d;

import android.content.Context;
import android.os.Handler;
import android.os.HandlerThread;
import com.ts.lib.car.Car;
import com.ts.lib.car.CarConnectionCallback;
import com.ts.lib.car.hardware.power.CarVendorPowerManager;
import com.ts.lib.car.hardware.power.PowerEventChangeValue;

/* JADX INFO: compiled from: caradapter-bug */
/* JADX INFO: loaded from: classes.dex */
public class i {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private Car f53a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private HandlerThread f54b;
    private Handler c;
    private Context d;
    private CarVendorPowerManager e;
    private com.ts.appservice.engineeringmode.a.a f;
    private final CarConnectionCallback g = new a();
    private final CarVendorPowerManager.OnCarPowerEventListener h = new b();

    /* JADX INFO: compiled from: caradapter-bug */
    class a extends CarConnectionCallback {
        a() {
        }

        public void onConnected(Car car) {
            com.ts.appservice.engineeringmode.e.a.g("VHalManager", "Car connected in onServiceConnected start");
            try {
                i.this.e = (CarVendorPowerManager) car.getCarManager("power");
                if (i.this.e != null) {
                    i.this.e.addCarPowerEventListener(i.this.h);
                } else {
                    i.this.i();
                    com.ts.appservice.engineeringmode.e.a.c("VHalManager", "mCarVendorPowerManager null return");
                }
            } catch (Exception e) {
                com.ts.appservice.engineeringmode.e.a.c("VHalManager", "CarConnectionCallback error");
                i.this.i();
                e.printStackTrace();
            }
        }

        public void onDisconnected(Car car) {
            com.ts.appservice.engineeringmode.e.a.g("VHalManager", "onDisconnected");
            i.this.f53a = null;
            i.this.e = null;
            i.this.i();
        }
    }

    /* JADX INFO: compiled from: caradapter-bug */
    class b implements CarVendorPowerManager.OnCarPowerEventListener {
        b() {
        }

        public void onPowerErrorEvent(int i, int i2) {
        }

        public void onPowerEventChange(PowerEventChangeValue powerEventChangeValue) {
            if (powerEventChangeValue == null) {
                com.ts.appservice.engineeringmode.e.a.c("VHalManager", "onPowerEventChange powerEventChangeValue is null !");
                return;
            }
            if (powerEventChangeValue.getPropertyId() != 557969428) {
                return;
            }
            int[] getOffScreenFlagResult = powerEventChangeValue.getGetOffScreenFlagResult();
            if (getOffScreenFlagResult == null || getOffScreenFlagResult.length != 2) {
                com.ts.appservice.engineeringmode.e.a.c("VHalManager", "onPowerEventChange getGetOffScreenFlagResult is back");
                return;
            }
            com.ts.appservice.engineeringmode.e.a.g("VHalManager", "onPowerEventChange ID_AP_GET_OFF_SCREEN_FLAG_RES type : " + getOffScreenFlagResult[0] + ", flag : " + getOffScreenFlagResult[1]);
            i.this.f.f(getOffScreenFlagResult[0], getOffScreenFlagResult[1]);
        }
    }

    public i(Context context, com.ts.appservice.engineeringmode.a.a aVar) {
        this.d = context;
        this.f = aVar;
        HandlerThread handlerThread = new HandlerThread("VHalManager");
        this.f54b = handlerThread;
        handlerThread.start();
        this.c = new Handler(this.f54b.getLooper());
        g();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void i() {
        com.ts.appservice.engineeringmode.e.a.g("VHalManager", "reConnectCar!!!");
        this.c.postDelayed(new Runnable() { // from class: com.ts.appservice.engineeringmode.d.a
            @Override // java.lang.Runnable
            public final void run() {
                this.f34a.g();
            }
        }, 5000L);
    }

    public void g() {
        com.ts.appservice.engineeringmode.e.a.e("VHalManager", "initCar start");
        Car carCreateCar = Car.createCar(this.d, this.g);
        this.f53a = carCreateCar;
        if (carCreateCar == null) {
            i();
        } else {
            carCreateCar.connect();
            com.ts.appservice.engineeringmode.e.a.e("VHalManager", "initCar end");
        }
    }

    public int h(int i) {
        int i2;
        com.ts.appservice.engineeringmode.e.a.g("VHalManager", " queryOffScreenFlag.in type : " + i);
        CarVendorPowerManager carVendorPowerManager = this.e;
        if (carVendorPowerManager != null) {
            try {
                carVendorPowerManager.queryOffScreenFlag(i);
                i2 = 0;
            } catch (Exception e) {
                com.ts.appservice.engineeringmode.e.a.c("VHalManager", "queryOffScreenFlag : " + e);
                i2 = -2147482648;
            }
            com.ts.appservice.engineeringmode.e.a.g("VHalManager", " queryOffScreenFlag.out = " + i2);
            return i2;
        }
        com.ts.appservice.engineeringmode.e.a.c("VHalManager", "queryOffScreenFlag mCarVendorPowerManager == null ");
        i2 = -2147482648;
        com.ts.appservice.engineeringmode.e.a.g("VHalManager", " queryOffScreenFlag.out = " + i2);
        return i2;
    }

    public int j(int i, int i2) {
        com.ts.appservice.engineeringmode.e.a.g("VHalManager", " setOffScreenFlag.in type : " + i + ", flag : " + i2);
        CarVendorPowerManager carVendorPowerManager = this.e;
        int i3 = 0;
        if (carVendorPowerManager != null) {
            try {
                carVendorPowerManager.setOffScreenFlag(new int[]{i, i2});
            } catch (Exception e) {
                com.ts.appservice.engineeringmode.e.a.c("VHalManager", "setOffScreenFlag : " + e);
                i3 = -2147482648;
            }
            com.ts.appservice.engineeringmode.e.a.g("VHalManager", " setOffScreenFlag.out = " + i3);
            return i3;
        }
        com.ts.appservice.engineeringmode.e.a.c("VHalManager", "setOffScreenFlag mCarVendorPowerManager == null ");
        i3 = -2147482648;
        com.ts.appservice.engineeringmode.e.a.g("VHalManager", " setOffScreenFlag.out = " + i3);
        return i3;
    }
}
