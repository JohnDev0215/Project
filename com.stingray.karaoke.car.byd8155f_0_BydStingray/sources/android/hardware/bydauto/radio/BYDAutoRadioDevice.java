package android.hardware.bydauto.radio;

import android.content.Context;
import android.hardware.bydauto.AbsBYDAutoDevice;
import android.hardware.bydauto.radio.tsimpl.RadioManagerImpl;

/* JADX INFO: loaded from: classes.dex */
public final class BYDAutoRadioDevice extends AbsBYDAutoDevice {
    public static final int AM_AUTO_SCAN_MARK = 558;
    public static final int AM_LOWER_LIMIT = 153;
    public static final int AM_STEP_10KHZ = 10;
    public static final int AM_STEP_9KHZ = 9;
    public static final int AM_UPPER_LIMIT = 282;
    public static final int FM_AUTO_SCAN_MARK = 88000;
    public static final int FM_LOWER_LIMIT = 87500;
    public static final int FM_STEP_100KHZ = 100;
    public static final int FM_STEP_50KHZ = 50;
    public static final int FM_UPPER_LIMIT = 108000;
    public static final int RADIO_BAND_AM = 0;
    public static final int RADIO_BAND_FM = 1;
    public static final int RADIO_COMMAND_BUSY = -2147482647;
    public static final int RADIO_COMMAND_FAILED = -2147482648;
    public static final int RADIO_COMMAND_INVALID_VALUE = -2147482645;
    public static final int RADIO_COMMAND_SUCCESS = 0;
    public static final int RADIO_COMMAND_TIMEOUT = -2147482646;
    public static final int RADIO_STATE_OFF = 0;
    public static final int RADIO_STATE_PLAY = 1;
    public static final int RADIO_STATE_SEARCH_ALL = 3;
    public static final int RADIO_STATE_SEARCH_NEXT_PREV = 2;
    public static final int SEARCH_FREQUENCY_EFFECTIVE = 1;
    public static final int SEARCH_FREQUENCY_INVALID = 0;
    public static final int SEARCH_PROCESS_STATE_FINISH = 0;
    public static final int SEARCH_PROCESS_STATE_UNFINISH = 1;
    protected static final String TAG = "BYDAutoRadioDevice";
    private static BYDAutoRadioDevice sBYDAutoRadioDevice;
    private RadioManagerImpl mRadioManagerImpl;

    public BYDAutoRadioDevice(Context context) {
        super(context);
        this.mRadioManagerImpl = RadioManagerImpl.getInstance(context);
    }

    public static synchronized BYDAutoRadioDevice getInstance(Context context) {
        try {
            if (sBYDAutoRadioDevice == null) {
                sBYDAutoRadioDevice = new BYDAutoRadioDevice(context);
            }
        } catch (Throwable th) {
            throw th;
        }
        return sBYDAutoRadioDevice;
    }

    public int autoSearch() {
        return this.mRadioManagerImpl.autoSearch();
    }

    public int cancelAutoSearch() {
        return this.mRadioManagerImpl.cancelAutoSearch();
    }

    public int getCurFreq() {
        return this.mRadioManagerImpl.getCurFreq();
    }

    @Override // android.hardware.bydauto.AbsBYDAutoDevice
    public int getDevicetype() {
        return this.mRadioManagerImpl.getDeviceType();
    }

    @Override // android.hardware.bydauto.AbsBYDAutoDevice
    public int[] getFeatureList() {
        return this.mRadioManagerImpl.getFeatureList();
    }

    @Override // android.hardware.bydauto.AbsBYDAutoDevice
    public String getGetPermission() {
        return this.mRadioManagerImpl.getGetPermission();
    }

    public int getRadioBand() {
        return this.mRadioManagerImpl.getRadioBand();
    }

    public int[] getRadioParam(int i7) {
        return this.mRadioManagerImpl.getRadioParam(i7);
    }

    public int getRadioState() {
        return this.mRadioManagerImpl.getRadioState();
    }

    public int getSearchProcessState() {
        return this.mRadioManagerImpl.getSearchProcessState();
    }

    @Override // android.hardware.bydauto.AbsBYDAutoDevice
    public String getSetPermission() {
        return this.mRadioManagerImpl.getSetPermission();
    }

    @Override // android.hardware.bydauto.AbsBYDAutoDevice
    public int getType() {
        return this.mRadioManagerImpl.getType();
    }

    @Override // android.hardware.bydauto.AbsBYDAutoDevice
    public boolean postEvent(int i7, int i10, int i11, Object obj) {
        return this.mRadioManagerImpl.postEvent(i7, i10, i11, obj);
    }

    public int radioOff() {
        return this.mRadioManagerImpl.radioOff();
    }

    public int radioOn() {
        return this.mRadioManagerImpl.radioOn();
    }

    public void registerListener(AbsBYDAutoRadioListener absBYDAutoRadioListener) {
        this.mRadioManagerImpl.registerListener(absBYDAutoRadioListener);
    }

    public int seek(boolean z6) {
        return this.mRadioManagerImpl.seek(z6);
    }

    public int setCurFreq(int i7, int i10) {
        return this.mRadioManagerImpl.setCurFreq(i7, i10);
    }

    public int setRadioParam(int i7, int i10, int i11, int i12) {
        return this.mRadioManagerImpl.setRadioParam(i7, i10, i11, i12);
    }

    public void unregisterListener(AbsBYDAutoRadioListener absBYDAutoRadioListener) {
        this.mRadioManagerImpl.unregisterListener(absBYDAutoRadioListener);
    }

    @Override // android.hardware.bydauto.AbsBYDAutoDevice
    public boolean postEvent(int i7, int i10, byte[] bArr, Object obj) {
        return this.mRadioManagerImpl.postEvent(i7, i10, bArr, obj);
    }

    public void registerListener(AbsBYDAutoRadioListener absBYDAutoRadioListener, int[] iArr) {
        this.mRadioManagerImpl.registerListener(absBYDAutoRadioListener, iArr);
    }
}
