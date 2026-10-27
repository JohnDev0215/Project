package android.car.drivingstate;

import android.car.utils.Logs;
import android.content.Context;
import com.byd.car.DiCar;
import com.byd.car.ICarInfoManager;
import java.lang.reflect.Method;
import spi.c;

/* JADX INFO: loaded from: classes.dex */
public class BydCarDriverType {
    private static final String TAG = "BydCarDriverType";
    private static Boolean mIsRightDriver;
    private static final Object mLock = new Object();

    private static boolean getSteeringWheelPosition() {
        Object objInvoke;
        try {
            Class<?> cls = Class.forName("android.os.SystemProperties");
            Method method = cls.getMethod("getInt", String.class, Integer.TYPE);
            method.setAccessible(true);
            objInvoke = method.invoke(cls, "persist.vendor.byd.cfg.huc.steering.wheel.position", 0);
        } catch (Exception unused) {
            StringBuilder sbK = c.k("isRightDriver error: ");
            sbK.append(mIsRightDriver);
            Logs.e(TAG, sbK.toString());
        }
        int iIntValue = objInvoke instanceof Integer ? ((Integer) objInvoke).intValue() : 0;
        return iIntValue == 1;
    }

    private static boolean getSteeringWheelPositionBySdk(Context context) {
        try {
            int driverSeat = ((ICarInfoManager) DiCar.getCarManager(context.getApplicationContext(), ICarInfoManager.class)).getDriverSeat();
            Logs.e(TAG, "getDriverSeat driverSeat: " + driverSeat);
            return driverSeat == 4;
        } catch (Exception e) {
            StringBuilder sbK = c.k("getDriverSeat error: ");
            sbK.append(mIsRightDriver);
            Logs.e(TAG, sbK.toString());
            e.printStackTrace();
            Logs.e(TAG, "getDriverSeat by old fun ");
            boolean steeringWheelPosition = getSteeringWheelPosition();
            Logs.e(TAG, "getDriverSeat by old fun isRight:" + steeringWheelPosition);
            return steeringWheelPosition;
        }
    }

    public static boolean isRightDriver(Context context) {
        boolean zBooleanValue;
        synchronized (mLock) {
            if (mIsRightDriver == null) {
                mIsRightDriver = Boolean.valueOf(getSteeringWheelPositionBySdk(context));
            }
            Logs.w(TAG, "isRightDriver: " + mIsRightDriver);
            zBooleanValue = mIsRightDriver.booleanValue();
        }
        return zBooleanValue;
    }
}
