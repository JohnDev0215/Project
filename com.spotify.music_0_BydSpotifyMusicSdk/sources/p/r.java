package p;

import android.app.Application;
import android.car.Car;
import android.car.hardware.CarPropertyValue;
import android.car.hardware.property.CarPropertyManager;
import android.os.Build;
import com.spotify.connectivity.MobileDeviceInfo;
import com.spotify.connectivity.systeminfo.MobileDeviceInfoDefaults;
import com.spotify.core.core.DeviceType;

/* JADX INFO: loaded from: classes.dex */
public abstract class r {
    public static MobileDeviceInfo a() {
        MobileDeviceInfo mobileDeviceInfoDefaults = MobileDeviceInfoDefaults.defaults();
        String str = Build.MODEL;
        mobileDeviceInfoDefaults.setName(str);
        mobileDeviceInfoDefaults.setModel(str);
        mobileDeviceInfoDefaults.setBrand(Build.BRAND);
        mobileDeviceInfoDefaults.setManufacturer(Build.MANUFACTURER);
        mobileDeviceInfoDefaults.setDeviceType(DeviceType.AUTOMOBILE.getValue());
        return mobileDeviceInfoDefaults;
    }

    public static MobileDeviceInfo b(Application application) {
        v03.j(application, "context");
        if (Build.VERSION.SDK_INT <= 29) {
            return a();
        }
        try {
            Object carManager = Car.createCar(application).getCarManager("property");
            v03.g(carManager, "null cannot be cast to non-null type android.car.hardware.property.CarPropertyManager");
            return c((CarPropertyManager) carManager);
        } catch (Throwable th) {
            lc.o("Failed to get device info from car API", th);
            return a();
        }
    }

    public static MobileDeviceInfo c(CarPropertyManager carPropertyManager) {
        CarPropertyValue property = carPropertyManager.getProperty(286261505, 0);
        CarPropertyValue property2 = carPropertyManager.getProperty(286261506, 0);
        CarPropertyValue property3 = carPropertyManager.getProperty(289407235, 0);
        MobileDeviceInfo mobileDeviceInfoA = a();
        if (property2 != null) {
            mobileDeviceInfoA.setName(property2.getValue().toString());
        }
        if (property != null) {
            mobileDeviceInfoA.setManufacturer(property.getValue().toString());
        }
        if (property2 != null && property3 != null) {
            StringBuilder sb = new StringBuilder();
            sb.append(property2.getValue());
            sb.append('_');
            sb.append(property3.getValue());
            mobileDeviceInfoA.setModel(sb.toString());
        }
        return mobileDeviceInfoA;
    }
}
