package android.hardware.bydauto.phone;

import android.content.Context;
import android.hardware.bydauto.AbsBYDAutoDevice;
import android.hardware.bydauto.phone.tsimpl.PhoneManagerImpl;

/* JADX INFO: loaded from: classes.dex */
public final class BYDAutoPhoneDevice extends AbsBYDAutoDevice {
    public static final int PHONE_COMMAND_BUSY = -2147482647;
    public static final int PHONE_COMMAND_FAILED = -2147482648;
    public static final int PHONE_COMMAND_INVALID = -2147482645;
    public static final int PHONE_COMMAND_SUCCESS = 0;
    public static final int PHONE_COMMAND_TIMEOUT = -2147482646;
    public static final int PHONE_EVENT_ANSWER = 1;
    public static final int PHONE_EVENT_HANGUP = 3;
    public static final int PHONE_EVENT_REJECT = 2;
    public static final int PHONE_INFO_CALLING = 2;
    public static final int PHONE_INFO_DAIL = 1;
    public static final int PHONE_INFO_HANGUP = 4;
    public static final int PHONE_INFO_ONLINE = 3;
    public static final int PHONE_MUTE_OFF = 1;
    public static final int PHONE_MUTE_ON = 0;
    public static final int PHONE_NAME_LEN_MAX = 160;
    public static final int PHONE_NAME_LEN_MIN = 1;
    public static final int PHONE_NUMBER_LEN_MAX = 20;
    public static final int PHONE_NUMBER_LEN_MIN = 1;
    protected static final String TAG = "BYDAutoPhoneDevice";
    private static BYDAutoPhoneDevice sBYDAutoPhoneDevice;
    private PhoneManagerImpl mImpl;

    BYDAutoPhoneDevice(Context con) {
        super(con.getApplicationContext());
        this.mImpl = PhoneManagerImpl.getsInstance(con.getApplicationContext());
    }

    public static synchronized BYDAutoPhoneDevice getInstance(Context con) {
        if (sBYDAutoPhoneDevice == null) {
            sBYDAutoPhoneDevice = new BYDAutoPhoneDevice(con.getApplicationContext());
        }
        return sBYDAutoPhoneDevice;
    }

    @Override // android.hardware.bydauto.AbsBYDAutoDevice
    public int getDevicetype() {
        return this.mImpl.getDevicetype();
    }

    @Override // android.hardware.bydauto.AbsBYDAutoDevice
    public String getSetPermission() {
        return this.mImpl.getSetPermission();
    }

    @Override // android.hardware.bydauto.AbsBYDAutoDevice
    public String getGetPermission() {
        return this.mImpl.getSetPermission();
    }

    public int setPhoneInfoState(int value) {
        return this.mImpl.setPhoneInfoState(value);
    }

    public int getPhoneEvent() {
        return this.mImpl.getPhoneEvent();
    }

    public int setPhoneMute(int value) {
        return this.mImpl.setPhoneMute(value);
    }

    public int getPhoneMute() {
        return this.mImpl.getPhoneMute();
    }

    public int[] setPhoneNameNumber(String name, String number) {
        return this.mImpl.setPhoneNameNumber(name, number);
    }

    public void setAllStatus() {
        this.mImpl.setAllStatus();
    }

    public void getAllStatus() {
        this.mImpl.getAllStatus();
    }

    @Override // android.hardware.bydauto.AbsBYDAutoDevice, android.hardware.IBYDAutoDevice
    public boolean postEvent(int deviceType, int eventType, int value, Object data) {
        return this.mImpl.postEvent(deviceType, eventType, value, data);
    }

    @Override // android.hardware.IBYDAutoDevice
    public int getType() {
        return this.mImpl.getType();
    }

    @Override // android.hardware.IBYDAutoDevice
    public int[] getFeatureList() {
        return this.mImpl.getFeatureList();
    }

    public void registerListener(AbsBYDAutoPhoneListener l) {
        this.mImpl.registerListener(l);
    }

    public void registerListener(AbsBYDAutoPhoneListener l, int[] featureIDs) {
        this.mImpl.registerListener(l, featureIDs);
    }

    public void unregisterListener(AbsBYDAutoPhoneListener l) {
        this.mImpl.unregisterListener(l);
    }
}
