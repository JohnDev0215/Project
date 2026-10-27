package android.hardware.bydauto.motor;

import android.content.Context;
import android.hardware.bydauto.AbsBYDAutoDevice;

/* JADX INFO: loaded from: classes.dex */
public final class BYDAutoMotorDevice extends AbsBYDAutoDevice {
    public static final double MOTOR_ANGLE_MAX = 359.9d;
    public static final double MOTOR_ANGLE_MIN = 0.0d;
    public static final int MOTOR_ANGLE_VALUE = 0;
    public static final int MOTOR_COMMAND_BUSY = -2147482647;
    public static final int MOTOR_COMMAND_FAILED = -2147482648;
    public static final int MOTOR_COMMAND_INVALID = -2147482645;
    public static final int MOTOR_COMMAND_SUCCESS = 0;
    public static final int MOTOR_COMMAND_TIMEOUT = -2147482646;
    public static final int MOTOR_OFF = 0;
    public static final int MOTOR_ON = 1;
    public static final int MOTOR_POS_MAX = 500;
    public static final int MOTOR_POS_MIN = -500;
    public static final int MOTOR_SPEED_VALUE = 0;
    public static final int MOTOR_STATE_ANTICLOCKWISE = 8;
    public static final int MOTOR_STATE_BACKWARD = 6;
    public static final int MOTOR_STATE_CLOCKWISE = 7;
    public static final int MOTOR_STATE_DOWNWARD = 2;
    public static final int MOTOR_STATE_FORWARD = 5;
    public static final int MOTOR_STATE_LEFT = 3;
    public static final int MOTOR_STATE_RIGHT = 4;
    public static final int MOTOR_STATE_STOP = 0;
    public static final int MOTOR_STATE_UPWARD = 1;
    protected static final String TAG = "BYDAutoMotorDevice";

    public BYDAutoMotorDevice(Context context) {
        super(context);
    }

    public static synchronized BYDAutoMotorDevice getInstance(Context context) {
        throw new RuntimeException("Stub!");
    }

    public void getAllStatus() {
        throw new RuntimeException("Stub!");
    }

    @Override // android.hardware.bydauto.AbsBYDAutoDevice
    public int getDevicetype() {
        throw new RuntimeException("Stub!");
    }

    @Override // android.hardware.IBYDAutoDevice
    public int[] getFeatureList() {
        throw new RuntimeException("Stub!");
    }

    @Override // android.hardware.bydauto.AbsBYDAutoDevice
    public String getGetPermission() {
        throw new RuntimeException("Stub!");
    }

    public int getMotorAngle() {
        throw new RuntimeException("Stub!");
    }

    public int getMotorDirection() {
        throw new RuntimeException("Stub!");
    }

    public int getMotorLock() {
        throw new RuntimeException("Stub!");
    }

    public int getMotorPosition() {
        throw new RuntimeException("Stub!");
    }

    public int getMotorPower() {
        throw new RuntimeException("Stub!");
    }

    public int getMotorSpeed() {
        throw new RuntimeException("Stub!");
    }

    @Override // android.hardware.bydauto.AbsBYDAutoDevice
    public String getSetPermission() {
        throw new RuntimeException("Stub!");
    }

    @Override // android.hardware.IBYDAutoDevice
    public int getType() {
        throw new RuntimeException("Stub!");
    }

    @Override // android.hardware.bydauto.AbsBYDAutoDevice, android.hardware.IBYDAutoDevice
    public boolean postEvent(int i, int i2, int i3, Object obj) {
        throw new RuntimeException("Stub!");
    }

    public void registerListener(AbsBYDAutoMotorListener absBYDAutoMotorListener) {
        throw new RuntimeException("Stub!");
    }

    public void setAllStatus() {
        throw new RuntimeException("Stub!");
    }

    public int setMotorDirection(int i) {
        throw new RuntimeException("Stub!");
    }

    public int setMotorLock(int i) {
        throw new RuntimeException("Stub!");
    }

    public int setMotorPower(int i) {
        throw new RuntimeException("Stub!");
    }

    public void unregisterListener(AbsBYDAutoMotorListener absBYDAutoMotorListener) {
        throw new RuntimeException("Stub!");
    }

    public void registerListener(AbsBYDAutoMotorListener absBYDAutoMotorListener, int[] iArr) {
        throw new RuntimeException("Stub!");
    }
}
