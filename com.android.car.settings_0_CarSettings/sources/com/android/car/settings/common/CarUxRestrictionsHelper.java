package com.android.car.settings.common;

import android.car.Car;
import android.car.CarNotConnectedException;
import android.car.drivingstate.CarUxRestrictions;
import android.car.drivingstate.CarUxRestrictionsManager;
import android.content.ComponentName;
import android.content.Context;
import android.content.ServiceConnection;
import android.os.IBinder;

/* JADX INFO: loaded from: classes5.dex */
public class CarUxRestrictionsHelper {
    private static final Logger LOG = new Logger((Class<?>) CarUxRestrictionsHelper.class);
    private final Car mCar;
    private CarUxRestrictionsManager mCarUxRestrictionsManager;
    private final CarUxRestrictionsManager.OnUxRestrictionsChangedListener mListener;
    private final ServiceConnection mServiceConnection;

    public CarUxRestrictionsHelper(Context context, CarUxRestrictionsManager.OnUxRestrictionsChangedListener listener) {
        ServiceConnection serviceConnection = new ServiceConnection() { // from class: com.android.car.settings.common.CarUxRestrictionsHelper.1
            @Override // android.content.ServiceConnection
            public void onServiceConnected(ComponentName name, IBinder service) {
                try {
                    CarUxRestrictionsHelper.this.mCarUxRestrictionsManager = (CarUxRestrictionsManager) CarUxRestrictionsHelper.this.mCar.getCarManager("uxrestriction");
                    CarUxRestrictionsHelper.this.mCarUxRestrictionsManager.registerListener(CarUxRestrictionsHelper.this.mListener);
                    CarUxRestrictionsHelper.this.mListener.onUxRestrictionsChanged(CarUxRestrictionsHelper.this.mCarUxRestrictionsManager.getCurrentCarUxRestrictions());
                } catch (CarNotConnectedException e) {
                    e.printStackTrace();
                }
            }

            @Override // android.content.ServiceConnection
            public void onServiceDisconnected(ComponentName name) {
                try {
                    CarUxRestrictionsHelper.this.mCarUxRestrictionsManager.unregisterListener();
                    CarUxRestrictionsHelper.this.mCarUxRestrictionsManager = null;
                } catch (CarNotConnectedException e) {
                    e.printStackTrace();
                }
            }
        };
        this.mServiceConnection = serviceConnection;
        if (listener == null) {
            throw new IllegalArgumentException("Listener cannot be null.");
        }
        this.mListener = listener;
        this.mCar = Car.createCar(context, serviceConnection);
    }

    public void start() {
        try {
            if (this.mCar != null && !this.mCar.isConnected()) {
                this.mCar.connect();
            }
        } catch (IllegalStateException e) {
            LOG.w("start(); cannot connect to Car");
        }
    }

    public void stop() {
        try {
            if (this.mCar != null && this.mCar.isConnected()) {
                this.mCar.disconnect();
            }
        } catch (IllegalStateException e) {
            LOG.w("stop(); cannot disconnect from Car");
        }
    }

    public static boolean isNoSetup(CarUxRestrictions carUxRestrictions) {
        return (carUxRestrictions.getActiveRestrictions() & 64) == 64;
    }
}
