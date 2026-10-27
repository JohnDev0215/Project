package com.android.car;

import android.car.Car;
import android.car.hardware.power.CarPowerManager;
import android.car.hardware.power.ICarPower;
import android.content.Context;
import android.util.ArrayMap;
import android.util.Log;

/* JADX INFO: loaded from: classes3.dex */
public class CarLocalServices {
    private static final ArrayMap<Class<?>, Object> sLocalServiceObjects = new ArrayMap<>();

    private CarLocalServices() {
    }

    public static <T> T getService(Class<T> cls) {
        T t;
        Log.d("CarLocalServices", " getService " + cls.getSimpleName());
        synchronized (sLocalServiceObjects) {
            t = (T) sLocalServiceObjects.get(cls);
        }
        return t;
    }

    public static <T> void addService(Class<T> type, T service) {
        synchronized (sLocalServiceObjects) {
            if (sLocalServiceObjects.containsKey(type)) {
                throw new IllegalStateException("Overriding service registration");
            }
            Log.d("CarLocalServices", " Adding " + type.getSimpleName());
            sLocalServiceObjects.put(type, service);
        }
    }

    public static <T> void removeServiceForTest(Class<T> type) {
        Log.d("CarLocalServices", " Removing " + type.getSimpleName());
        synchronized (sLocalServiceObjects) {
            sLocalServiceObjects.remove(type);
        }
    }

    public static void removeAllServices() {
        Log.d("CarLocalServices", " removeAllServices");
        synchronized (sLocalServiceObjects) {
            sLocalServiceObjects.clear();
        }
    }

    public static CarPowerManager createCarPowerManager(Context context) {
        Car car = new Car(context, null, null);
        ICarPower.Stub stub = (CarPowerManagementService) getService(CarPowerManagementService.class);
        if (stub == null) {
            return null;
        }
        return new CarPowerManager(car, stub);
    }
}
