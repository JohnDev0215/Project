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

    BYDAutoPhoneDevice(Context context) {
        super(context.getApplicationContext());
        this.mImpl = PhoneManagerImpl.getsInstance(context.getApplicationContext());
    }

    public static synchronized BYDAutoPhoneDevice getInstance(Context context) {
        if (sBYDAutoPhoneDevice == null) {
            sBYDAutoPhoneDevice = new BYDAutoPhoneDevice(context.getApplicationContext());
        }
        return sBYDAutoPhoneDevice;
    }

    public void getAllStatus() {
        this.mImpl.getAllStatus();
    }

    @Override // android.hardware.bydauto.AbsBYDAutoDevice
    public int getDevicetype() {
        return this.mImpl.getDevicetype();
    }

    @Override // android.hardware.bydauto.AbsBYDAutoDevice
    public int[] getFeatureList() {
        return this.mImpl.getFeatureList();
    }

    @Override // android.hardware.bydauto.AbsBYDAutoDevice
    public String getGetPermission() {
        return this.mImpl.getSetPermission();
    }

    public int getPhoneEvent() {
        return this.mImpl.getPhoneEvent();
    }

    public int getPhoneMute() {
        return this.mImpl.getPhoneMute();
    }

    @Override // android.hardware.bydauto.AbsBYDAutoDevice
    public String getSetPermission() {
        return this.mImpl.getSetPermission();
    }

    @Override // android.hardware.bydauto.AbsBYDAutoDevice
    public int getType() {
        return this.mImpl.getType();
    }

    @Override // android.hardware.bydauto.AbsBYDAutoDevice
    public boolean postEvent(int i7, int i8, int i9, Object obj) {
        return this.mImpl.postEvent(i7, i8, i9, obj);
    }

    public void registerListener(AbsBYDAutoPhoneListener absBYDAutoPhoneListener) {
        this.mImpl.registerListener(absBYDAutoPhoneListener);
    }

    public void setAllStatus() {
        this.mImpl.setAllStatus();
    }

    public int setPhoneInfoState(int i7) {
        return this.mImpl.setPhoneInfoState(i7);
    }

    public int setPhoneMute(int i7) {
        return this.mImpl.setPhoneMute(i7);
    }

    public int[] setPhoneNameNumber(String str, String str2) {
        return this.mImpl.setPhoneNameNumber(str, str2);
    }

    public void unregisterListener(AbsBYDAutoPhoneListener absBYDAutoPhoneListener) {
        this.mImpl.unregisterListener(absBYDAutoPhoneListener);
    }

    public void registerListener(AbsBYDAutoPhoneListener absBYDAutoPhoneListener, int[] iArr) {
        this.mImpl.registerListener(absBYDAutoPhoneListener, iArr);
    }
}
