package android.hardware.bydauto.adas.tsimpl;

import android.content.Context;
import android.hardware.bydauto.adas.AbsBYDAutoADASListener;

/* JADX INFO: loaded from: classes.dex */
public class AdasManagerImpl {
    private static final int S_FOR_TEST_VALUE = 0;
    private static final String S_KEY_FOR_TEST = "key_for_test_AdasManagerImpl";
    private static final String TAG = "AdasManagerImpl";
    private static AdasManagerImpl sInstance;
    private Context mContext;

    AdasManagerImpl(Context context) {
        this.mContext = context;
    }

    public static synchronized AdasManagerImpl getsInstance(Context context) {
        if (sInstance == null) {
            sInstance = new AdasManagerImpl(context);
        }
        return sInstance;
    }

    public int getDevicetype() {
        return 0;
    }

    public String getSetPermission() {
        return "";
    }

    public String getGetPermission() {
        return "";
    }

    public int hasFeature(String feature) {
        return 0;
    }

    public int hasFeature2(String feature) {
        return 0;
    }

    public int setHMAState(int state) {
        return 0;
    }

    public int getHMAState() {
        return 0;
    }

    public int getLaneOffsetState() {
        return 0;
    }

    public int setPCWState(int state) {
        return 0;
    }

    public int getPCWState() {
        return 0;
    }

    public int setAEBState(int state) {
        return 0;
    }

    public int getAEBState() {
        return 0;
    }

    public int setESPState(int state) {
        return 0;
    }

    public int getESPState() {
        return 0;
    }

    public int setLKSMode(int mode) {
        return 0;
    }

    public int getLKSMode() {
        return 0;
    }

    public int setLKSSensitivity(int sensitivity) {
        return 0;
    }

    public int getLKSSensitivity() {
        return 0;
    }

    public int setLDSWType(int type) {
        return 0;
    }

    public int getLDSWType() {
        return 0;
    }

    public int setBSDState(int state) {
        return 0;
    }

    public int getBSDState() {
        return 0;
    }

    public int setSLAState(int state) {
        return 0;
    }

    public int getSLAState() {
        return 0;
    }

    public int setAVHState(int state) {
        return 0;
    }

    public int getAVHState() {
        return 0;
    }

    public int setIboosterState(int state) {
        return 0;
    }

    public int getIboosterState() {
        return 0;
    }

    public int setBSDStaticCalibration(int value) {
        return 0;
    }

    public int setLDWStaticCalibration(int value) {
        return 0;
    }

    public int setTJAState(int state) {
        return 0;
    }

    public int getTJAState() {
        return 0;
    }

    public int reset(int item, int value) {
        return 0;
    }

    public int getESPOnlineState() {
        return 0;
    }

    public int setHDCState(int state) {
        return 0;
    }

    public int getHDCState() {
        return 0;
    }

    public int get123State() {
        return 0;
    }

    public int setBrakeFootSenseState(int state) {
        return 0;
    }

    public int getBrakeFootSenseState() {
        return 0;
    }

    public int setCSTState(int state) {
        return 0;
    }

    public int getCSTState() {
        return 0;
    }

    public int getCSTData() {
        return 0;
    }

    public int getSuspen() {
        return 0;
    }

    public int getInterActiveHint() {
        return 0;
    }

    public int getAutoSearchState() {
        return 0;
    }

    public int getAbortReason() {
        return 0;
    }

    public int getActive() {
        return 0;
    }

    public int getAutoParkButtonState() {
        return 0;
    }

    public int setAVMSwitchState(int state) {
        return 0;
    }

    public int getAVMSwitchState() {
        return 0;
    }

    public int setAdasReset(int safetyResetState, int lksLdwsResetState, int driveResetState) {
        return 0;
    }

    public int getHDCKeyState() {
        return 0;
    }

    public boolean postEvent(int device_type, int event_type, int value, Object data) {
        return false;
    }

    public int getType() {
        return 0;
    }

    public int[] getFeatureList() {
        return new int[1];
    }

    public void registerListener(AbsBYDAutoADASListener listener) {
    }

    public void registerListener(AbsBYDAutoADASListener listener, int[] featureIDs) {
    }

    public void unregisterListener(AbsBYDAutoADASListener listener) {
    }
}
