package com.ts.lib.car;

import android.content.ComponentName;
import android.content.Context;
import android.content.ServiceConnection;
import android.os.Handler;
import android.os.IBinder;
import com.ts.lib.car.CarServiceLoader;

/* JADX INFO: loaded from: classes.dex */
public class CarServiceLoaderEmbedded extends CarServiceLoader {
    private final android.car.Car mEmbeddedCar;
    private final ServiceConnection mServiceConnection;

    CarServiceLoaderEmbedded(Context context, CarServiceLoader.CarConnectionCallbackProxy carConnectionCallback, Handler handler) {
        super(context, carConnectionCallback, handler);
        ServiceConnection serviceConnection = new ServiceConnection() { // from class: com.ts.lib.car.CarServiceLoaderEmbedded.1
            @Override // android.content.ServiceConnection
            public void onServiceConnected(ComponentName name, IBinder service) {
                CarServiceLoaderEmbedded.this.getConnectionCallback().onConnected();
            }

            @Override // android.content.ServiceConnection
            public void onServiceDisconnected(ComponentName name) {
                CarServiceLoaderEmbedded.this.getConnectionCallback().onDisconnected();
            }
        };
        this.mServiceConnection = serviceConnection;
        this.mEmbeddedCar = android.car.Car.createCar(context, serviceConnection, handler);
    }

    @Override // com.ts.lib.car.CarServiceLoader
    public void connect() throws IllegalStateException {
        this.mEmbeddedCar.connect();
    }

    @Override // com.ts.lib.car.CarServiceLoader
    public void disconnect() {
        this.mEmbeddedCar.disconnect();
    }

    @Override // com.ts.lib.car.CarServiceLoader
    public boolean isConnected() {
        return this.mEmbeddedCar.isConnected();
    }

    @Override // com.ts.lib.car.CarServiceLoader
    public int getCarConnectionType() throws CarNotConnectedException {
        int carConnectionType = this.mEmbeddedCar.getCarConnectionType();
        return carConnectionType;
    }

    /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
    /* JADX WARN: Removed duplicated region for block: B:107:0x018f  */
    @Override // com.ts.lib.car.CarServiceLoader
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public java.lang.Object getCarManager(java.lang.String r3) {
        /*
            Method dump skipped, instruction units count: 892
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.ts.lib.car.CarServiceLoaderEmbedded.getCarManager(java.lang.String):java.lang.Object");
    }
}
