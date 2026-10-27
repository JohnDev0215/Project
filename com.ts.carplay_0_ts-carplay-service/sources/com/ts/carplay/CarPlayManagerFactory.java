package com.ts.carplay;

import com.ts.carplay.lib.CarPlayManager;
import com.ts.carplay.lib.interfaces.ICarPlayManager;

/* JADX INFO: loaded from: classes.dex */
public class CarPlayManagerFactory {
    private static volatile ICarPlayManager mCarPlayManager;

    public static ICarPlayManager getCarPlayManager() {
        if (mCarPlayManager == null) {
            synchronized (CarPlayManagerFactory.class) {
                if (mCarPlayManager == null) {
                    mCarPlayManager = new CarPlayManager();
                }
            }
        }
        return mCarPlayManager;
    }
}
