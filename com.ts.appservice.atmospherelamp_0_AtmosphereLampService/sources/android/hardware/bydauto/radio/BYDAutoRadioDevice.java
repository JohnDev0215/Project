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

    BYDAutoRadioDevice(Context con) {
        super(con);
        this.mRadioManagerImpl = RadioManagerImpl.getInstance(con);
    }

    public static synchronized BYDAutoRadioDevice getInstance(Context context) {
        if (sBYDAutoRadioDevice == null) {
            sBYDAutoRadioDevice = new BYDAutoRadioDevice(context);
        }
        return sBYDAutoRadioDevice;
    }

    @Override // android.hardware.bydauto.AbsBYDAutoDevice
    public int getDevicetype() {
        return this.mRadioManagerImpl.getDeviceType();
    }

    @Override // android.hardware.bydauto.AbsBYDAutoDevice
    public String getSetPermission() {
        return this.mRadioManagerImpl.getSetPermission();
    }

    @Override // android.hardware.bydauto.AbsBYDAutoDevice
    public String getGetPermission() {
        return this.mRadioManagerImpl.getGetPermission();
    }

    public int radioOn() {
        return this.mRadioManagerImpl.radioOn();
    }

    public int radioOff() {
        return this.mRadioManagerImpl.radioOff();
    }

    public int getRadioState() {
        return this.mRadioManagerImpl.getRadioState();
    }

    public int setRadioParam(int band, int start, int end, int step) {
        return this.mRadioManagerImpl.setRadioParam(band, start, end, step);
    }

    public int[] getRadioParam(int band) {
        return this.mRadioManagerImpl.getRadioParam(band);
    }

    public int getRadioBand() {
        return this.mRadioManagerImpl.getRadioBand();
    }

    public int setCurFreq(int band, int frequency) {
        return this.mRadioManagerImpl.setCurFreq(band, frequency);
    }

    public int getCurFreq() {
        return this.mRadioManagerImpl.getCurFreq();
    }

    public int autoSearch() {
        return this.mRadioManagerImpl.autoSearch();
    }

    public int cancelAutoSearch() {
        return this.mRadioManagerImpl.cancelAutoSearch();
    }

    public int seek(boolean next) {
        return this.mRadioManagerImpl.seek(next);
    }

    public int getSearchProcessState() {
        return this.mRadioManagerImpl.getSearchProcessState();
    }

    @Override // android.hardware.bydauto.AbsBYDAutoDevice, android.hardware.IBYDAutoDevice
    public boolean postEvent(int device_type, int event_type, int value, Object data) {
        return this.mRadioManagerImpl.postEvent(device_type, event_type, value, data);
    }

    @Override // android.hardware.bydauto.AbsBYDAutoDevice, android.hardware.IBYDAutoDevice
    public boolean postEvent(int device_type, int event_type, byte[] value, Object data) {
        return this.mRadioManagerImpl.postEvent(device_type, event_type, value, data);
    }

    @Override // android.hardware.IBYDAutoDevice
    public int getType() {
        return this.mRadioManagerImpl.getType();
    }

    @Override // android.hardware.IBYDAutoDevice
    public int[] getFeatureList() {
        return this.mRadioManagerImpl.getFeatureList();
    }

    public void registerListener(AbsBYDAutoRadioListener listener) {
        this.mRadioManagerImpl.registerListener(listener);
    }

    public void registerListener(AbsBYDAutoRadioListener listener, int[] featureIDs) {
        this.mRadioManagerImpl.registerListener(listener, featureIDs);
    }

    public void unregisterListener(AbsBYDAutoRadioListener listener) {
        this.mRadioManagerImpl.unregisterListener(listener);
    }
}
