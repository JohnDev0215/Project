package android.hardware.bydauto.seat;

import android.content.Context;
import android.hardware.bydauto.AbsBYDAutoDevice;
import android.hardware.bydauto.seat.tsimpl.SeatManagerImpl;

/* JADX INFO: loaded from: classes.dex */
public class BYDAutoSeatDevice extends AbsBYDAutoDevice {
    public static final int INVALID = 0;
    public static final int SEAT_COMMAND_BUSY = -2147482647;
    public static final int SEAT_COMMAND_FAILED = -2147482648;
    public static final int SEAT_COMMAND_INVALID_VALUE = -2147482645;
    public static final int SEAT_COMMAND_SUCCESS = 0;
    public static final int SEAT_COMMAND_TIMEOUT = -2147482646;
    public static final int SEAT_DEPUTY = 2;
    public static final int SEAT_LEFT_REAR = 3;
    public static final int SEAT_MAIN = 1;
    public static final int SEAT_MASSAGE_CLOSE = 1;
    public static final int SEAT_MASSAGE_LEVEL1 = 1;
    public static final int SEAT_MASSAGE_LEVEL2 = 2;
    public static final int SEAT_MASSAGE_LEVEL3 = 3;
    public static final int SEAT_MASSAGE_LOIN = 5;
    public static final int SEAT_MASSAGE_OPEN = 7;
    public static final int SEAT_MASSAGE_PULSE = 3;
    public static final int SEAT_MASSAGE_SHOULDERS = 6;
    public static final int SEAT_MASSAGE_STRETCH = 4;
    public static final int SEAT_MASSAGE_WAVE = 2;
    public static final int SEAT_RIGHT_REAR = 4;
    public static final int SEAT_VENTILATION_HIGH = 1;
    public static final int SEAT_VENTILATION_LOW = 2;
    public static final int SEAT_VENTILATION_OFF = 3;
    public static final int SEAT_WARM_HIGH = 1;
    public static final int SEAT_WARM_LOW = 2;
    public static final int SEAT_WARM_OFF = 3;
    public static final int STEERING_WHEEL_WARM_STATE_OFF = 1;
    public static final int STEERING_WHEEL_WARM_STATE_ON = 2;
    private static BYDAutoSeatDevice sDevice;
    private final SeatManagerImpl mSeatManagerImpl;

    public BYDAutoSeatDevice(Context context) {
        super(context.getApplicationContext());
        this.mSeatManagerImpl = SeatManagerImpl.getInstance(context.getApplicationContext());
    }

    public static synchronized BYDAutoSeatDevice getInstance(Context context) {
        if (sDevice == null) {
            sDevice = new BYDAutoSeatDevice(context);
        }
        return sDevice;
    }

    @Override // android.hardware.bydauto.AbsBYDAutoDevice
    public int getDevicetype() {
        return 0;
    }

    @Override // android.hardware.bydauto.AbsBYDAutoDevice
    public int[] getFeatureList() {
        return new int[0];
    }

    @Override // android.hardware.bydauto.AbsBYDAutoDevice
    public String getGetPermission() {
        return "";
    }

    public int getSeatMassageLevel(int i2) {
        return this.mSeatManagerImpl.getSeatMassageLevel(i2);
    }

    public int getSeatMassageType(int i2) {
        return this.mSeatManagerImpl.getSeatMassageType(i2);
    }

    public int getSeatVentilationState(int i2) {
        return this.mSeatManagerImpl.getSeatVentilationState(i2);
    }

    public int getSeatWarmState(int i2) {
        return this.mSeatManagerImpl.getSeatWarmState(i2);
    }

    @Override // android.hardware.bydauto.AbsBYDAutoDevice
    public String getSetPermission() {
        return "";
    }

    public int getSteeringWheelWarmState() {
        return this.mSeatManagerImpl.getSteeringWheelWarmState();
    }

    @Override // android.hardware.bydauto.AbsBYDAutoDevice
    public int getType() {
        return 0;
    }

    public void registerListener(AbsBYDAutoSeatListener absBYDAutoSeatListener) {
        this.mSeatManagerImpl.registerListener(absBYDAutoSeatListener);
    }

    public int setSeatMassageLevel(int i2, int i3) {
        return this.mSeatManagerImpl.setSeatMassageLevel(i2, i3);
    }

    public int setSeatMassageType(int i2, int i3) {
        return this.mSeatManagerImpl.setSeatMassageType(i2, i3);
    }

    public int setSeatVentilationState(int i2, int i3) {
        return this.mSeatManagerImpl.setSeatVentilationState(i2, i3);
    }

    public int setSeatWarmState(int i2, int i3) {
        return this.mSeatManagerImpl.setSeatWarmState(i2, i3);
    }

    public int setSteeringWheelWarmState(int i2) {
        return this.mSeatManagerImpl.setSteeringWheelWarmState(i2);
    }

    public void unregisterListener(AbsBYDAutoSeatListener absBYDAutoSeatListener) {
        this.mSeatManagerImpl.unregisterListener(absBYDAutoSeatListener);
    }
}
