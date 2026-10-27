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

    BYDAutoSeatDevice(Context con) {
        super(con.getApplicationContext());
        this.mSeatManagerImpl = SeatManagerImpl.getInstance(con.getApplicationContext());
    }

    public static synchronized BYDAutoSeatDevice getInstance(Context con) {
        if (sDevice == null) {
            sDevice = new BYDAutoSeatDevice(con);
        }
        return sDevice;
    }

    @Override // android.hardware.IBYDAutoDevice
    public int getType() {
        return 0;
    }

    @Override // android.hardware.IBYDAutoDevice
    public int[] getFeatureList() {
        return new int[0];
    }

    @Override // android.hardware.bydauto.AbsBYDAutoDevice
    public int getDevicetype() {
        return 0;
    }

    @Override // android.hardware.bydauto.AbsBYDAutoDevice
    public String getSetPermission() {
        return "";
    }

    @Override // android.hardware.bydauto.AbsBYDAutoDevice
    public String getGetPermission() {
        return "";
    }

    public int getSteeringWheelWarmState() {
        return this.mSeatManagerImpl.getSteeringWheelWarmState();
    }

    public int setSteeringWheelWarmState(int state) {
        return this.mSeatManagerImpl.setSteeringWheelWarmState(state);
    }

    public int getSeatVentilationState(int area) {
        return this.mSeatManagerImpl.getSeatVentilationState(area);
    }

    public int setSeatVentilationState(int area, int value) {
        return this.mSeatManagerImpl.setSeatVentilationState(area, value);
    }

    public int getSeatWarmState(int area) {
        return this.mSeatManagerImpl.getSeatWarmState(area);
    }

    public int setSeatWarmState(int area, int state) {
        return this.mSeatManagerImpl.setSeatWarmState(area, state);
    }

    public int getSeatMassageLevel(int area) {
        return this.mSeatManagerImpl.getSeatMassageLevel(area);
    }

    public int setSeatMassageLevel(int area, int value) {
        return this.mSeatManagerImpl.setSeatMassageLevel(area, value);
    }

    public int getSeatMassageType(int area) {
        return this.mSeatManagerImpl.getSeatMassageType(area);
    }

    public int setSeatMassageType(int area, int state) {
        return this.mSeatManagerImpl.setSeatMassageType(area, state);
    }

    public void registerListener(AbsBYDAutoSeatListener listener) {
        this.mSeatManagerImpl.registerListener(listener);
    }

    public void unregisterListener(AbsBYDAutoSeatListener listener) {
        this.mSeatManagerImpl.unregisterListener(listener);
    }
}
