package android.car.test;

import android.car.Car;
import android.car.CarManagerBase;
import android.os.IBinder;

/* JADX INFO: loaded from: classes.dex */
public class CarTestManagerBinderWrapper extends CarManagerBase {
    public final IBinder binder;

    @Override // android.car.CarManagerBase
    public void onCarDisconnected() {
    }

    public CarTestManagerBinderWrapper(IBinder iBinder) {
        super(null);
        this.binder = iBinder;
    }

    public CarTestManagerBinderWrapper(Car car, IBinder iBinder) {
        super(car);
        this.binder = iBinder;
    }
}
