package android.hardware.bydauto.adas.tsimpl;

import android.content.Context;
import android.hardware.bydauto.adas.AbsBYDAutoADASListener;

/* JADX INFO: compiled from: caradapter-bug */
/* JADX INFO: loaded from: classes.dex */
public class AdasManagerImpl {
    private static final int S_FOR_TEST_VALUE = 0;
    private static final String S_KEY_FOR_TEST = "key_for_test_AdasManagerImpl";
    private static final String TAG = "AdasManagerImpl";
    private static AdasManagerImpl sInstance;
    private Context mContext;

    public AdasManagerImpl(Context context) {
        this.mContext = context;
    }

    public static synchronized AdasManagerImpl getsInstance(Context context) {
        if (sInstance == null) {
            sInstance = new AdasManagerImpl(context);
        }
        return sInstance;
    }

    public int get123State() {
        return 0;
    }

    public int getAEBState() {
        return 0;
    }

    public int getAVHState() {
        return 0;
    }

    public int getAVMSwitchState() {
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

    public int getAutoSearchState() {
        return 0;
    }

    public int getBSDState() {
        return 0;
    }

    public int getBrakeFootSenseState() {
        return 0;
    }

    public int getCSTData() {
        return 0;
    }

    public int getCSTState() {
        return 0;
    }

    public int getDevicetype() {
        return 0;
    }

    public int getESPOnlineState() {
        return 0;
    }

    public int getESPState() {
        return 0;
    }

    public int[] getFeatureList() {
        return new int[1];
    }

    public String getGetPermission() {
        return "";
    }

    public int getHDCKeyState() {
        return 0;
    }

    public int getHDCState() {
        return 0;
    }

    public int getHMAState() {
        return 0;
    }

    public int getIboosterState() {
        return 0;
    }

    public int getInterActiveHint() {
        return 0;
    }

    public int getLDSWType() {
        return 0;
    }

    public int getLKSMode() {
        return 0;
    }

    public int getLKSSensitivity() {
        return 0;
    }

    public int getLaneOffsetState() {
        return 0;
    }

    public int getPCWState() {
        return 0;
    }

    public int getSLAState() {
        return 0;
    }

    public String getSetPermission() {
        return "";
    }

    public int getSuspen() {
        return 0;
    }

    public int getTJAState() {
        return 0;
    }

    public int getType() {
        return 0;
    }

    public int hasFeature(String str) {
        return 0;
    }

    public int hasFeature2(String str) {
        return 0;
    }

    public boolean postEvent(int i, int i2, int i3, Object obj) {
        return false;
    }

    public void registerListener(AbsBYDAutoADASListener absBYDAutoADASListener) {
    }

    public void registerListener(AbsBYDAutoADASListener absBYDAutoADASListener, int[] iArr) {
    }

    public int reset(int i, int i2) {
        return 0;
    }

    public int setAEBState(int i) {
        return 0;
    }

    public int setAVHState(int i) {
        return 0;
    }

    public int setAVMSwitchState(int i) {
        return 0;
    }

    public int setAdasReset(int i, int i2, int i3) {
        return 0;
    }

    public int setBSDState(int i) {
        return 0;
    }

    public int setBSDStaticCalibration(int i) {
        return 0;
    }

    public int setBrakeFootSenseState(int i) {
        return 0;
    }

    public int setCSTState(int i) {
        return 0;
    }

    public int setESPState(int i) {
        return 0;
    }

    public int setHDCState(int i) {
        return 0;
    }

    public int setHMAState(int i) {
        return 0;
    }

    public int setIboosterState(int i) {
        return 0;
    }

    public int setLDSWType(int i) {
        return 0;
    }

    public int setLDWStaticCalibration(int i) {
        return 0;
    }

    public int setLKSMode(int i) {
        return 0;
    }

    public int setLKSSensitivity(int i) {
        return 0;
    }

    public int setPCWState(int i) {
        return 0;
    }

    public int setSLAState(int i) {
        return 0;
    }

    public int setTJAState(int i) {
        return 0;
    }

    public void unregisterListener(AbsBYDAutoADASListener absBYDAutoADASListener) {
    }
}
