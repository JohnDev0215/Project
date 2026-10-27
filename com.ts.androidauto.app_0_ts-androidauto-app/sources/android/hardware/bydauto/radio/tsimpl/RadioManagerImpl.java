package android.hardware.bydauto.radio.tsimpl;

import android.content.ContentResolver;
import android.content.Context;
import android.hardware.bydauto.radio.AbsBYDAutoRadioListener;
import android.hardware.radio.ProgramSelector;
import android.hardware.radio.RadioManager;
import android.hardware.radio.RadioTuner;
import android.provider.Settings;
import android.util.Log;
import com.ts.androidauto.fsdk.common.AndroidAutoConst;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/* JADX INFO: loaded from: classes.dex */
public class RadioManagerImpl {
    private static final int S_FOR_TEST_VALUE = 1;
    private static final String S_KEY_FOR_TEST = "key_for_test_RadioManagerImpl";
    private static final String TAG = "RadioManagerImpl";
    private static RadioManagerImpl sRadioManagerImpl;
    private AbsBYDAutoRadioListener mAbsBydAutoRadioListener;
    private ContentResolver mContentResolver;
    private Context mContext;
    private int mForTest;
    private RadioManager mRadioManager;
    private RadioTuner mRadioTuner;
    private RadioTuner.Callback mTunerCallback = new RadioTuner.Callback() { // from class: android.hardware.bydauto.radio.tsimpl.RadioManagerImpl.1
        public void onConfigurationChanged(RadioManager.BandConfig config) {
            if (RadioManagerImpl.this.mAbsBydAutoRadioListener != null) {
                int bandType = config.getType();
                int lowerLimit = config.getLowerLimit();
                int upperLimit = config.getUpperLimit();
                int step = config.getSpacing();
                RadioManagerImpl.this.mAbsBydAutoRadioListener.onRadioParamChanged(bandType, lowerLimit, upperLimit, step);
            }
        }

        public void onProgramInfoChanged(RadioManager.ProgramInfo info) {
            int state;
            if (RadioManagerImpl.this.mAbsBydAutoRadioListener != null) {
                ProgramSelector programSelector = info.getSelector();
                int band = programSelector.getProgramType();
                int frequency = info.getChannel();
                boolean isMuted = info.isTuned();
                if (isMuted) {
                    state = 0;
                } else {
                    state = 1;
                }
                RadioManagerImpl.this.mAbsBydAutoRadioListener.onCurFreqChanged(state, band, frequency);
            }
        }

        public void onParametersUpdated(Map<String, String> parameters) {
            Log.d(RadioManagerImpl.TAG, "RadioManagerImpl onParametersUpdated is" + parameters.toString());
        }

        public void onError(int status) {
            if (RadioManagerImpl.this.mAbsBydAutoRadioListener != null) {
                Log.d(RadioManagerImpl.TAG, "RadioManagerImpl onError is" + status);
                RadioManagerImpl.this.mAbsBydAutoRadioListener.onError(status, "error status");
            }
        }
    };

    RadioManagerImpl(Context context) {
        this.mForTest = 0;
        this.mContext = context;
        ContentResolver contentResolver = context.getContentResolver();
        this.mContentResolver = contentResolver;
        if (contentResolver != null) {
            int i = Settings.System.getInt(contentResolver, S_KEY_FOR_TEST, 0);
            this.mForTest = i;
            if (1 == i) {
                Log.d(TAG, "RadioManagerImpl mForTestValue is" + this.mForTest + " execute return");
                return;
            }
        }
        this.mRadioManager = (RadioManager) this.mContext.getSystemService("broadcastradio");
    }

    public static synchronized RadioManagerImpl getInstance(Context context) {
        if (sRadioManagerImpl == null) {
            sRadioManagerImpl = new RadioManagerImpl(context);
            Log.d(TAG, "sRadioManagerImpl equal null ");
        }
        return sRadioManagerImpl;
    }

    public int getDeviceType() {
        if (1 == this.mForTest) {
            Log.d(TAG, "getDeviceType mForTestValue is" + this.mForTest + " execute return");
            return 0;
        }
        return 1021;
    }

    public String getSetPermission() {
        return AndroidAutoConst.DEFAULT_SCREEN_NAME;
    }

    public String getGetPermission() {
        return AndroidAutoConst.DEFAULT_SCREEN_NAME;
    }

    public int radioOn() {
        if (1 == this.mForTest) {
            Log.d(TAG, "radioOn mForTestValue is" + this.mForTest + " execute return");
            return 0;
        }
        List<RadioManager.ModuleProperties> moduleList = new ArrayList<>();
        int status = this.mRadioManager.listModules(moduleList);
        if (status == 0) {
        }
        return 0;
    }

    public int radioOff() {
        if (1 == this.mForTest) {
            Log.d(TAG, "radioOff mForTestValue is" + this.mForTest + " execute return");
            return 0;
        }
        RadioTuner radioTuner = this.mRadioTuner;
        if (radioTuner == null) {
            return 0;
        }
        radioTuner.close();
        return -2147482648;
    }

    public int getRadioState() {
        if (1 != this.mForTest) {
            return 0;
        }
        Log.d(TAG, "getRadioState mForTestValue is" + this.mForTest + " execute return");
        return 0;
    }

    public int setRadioParam(int band, int start, int end, int step) {
        int state;
        if (1 == this.mForTest) {
            Log.d(TAG, "setRadioParam mForTestValue is" + this.mForTest + " execute return");
            return 0;
        }
        if (this.mRadioTuner == null) {
            return 0;
        }
        if (band == 0) {
            RadioManager.AmBandDescriptor amBandDescriptor = new RadioManager.AmBandDescriptor(0, band, start, end, step, true);
            RadioManager.AmBandConfig amBandConfig = new RadioManager.AmBandConfig.Builder(amBandDescriptor).setStereo(true).build();
            Log.d(TAG, "setRadioParam AmConfig :" + amBandConfig.toString());
            state = this.mRadioTuner.setConfiguration(amBandConfig);
            Log.d(TAG, "setBydRadioParam RADIO_BAND_AM is : " + state);
        } else {
            if (band != 1) {
                return -2147482648;
            }
            RadioManager.FmBandDescriptor fmBandDescriptor = new RadioManager.FmBandDescriptor(0, band, start, end, step, true, false, false, false, false);
            RadioManager.FmBandConfig fmBandConfig = new RadioManager.FmBandConfig.Builder(fmBandDescriptor).setStereo(true).build();
            Log.d(TAG, "setRadioParam FmConfig :" + fmBandConfig.toString());
            state = this.mRadioTuner.setConfiguration(fmBandConfig);
            Log.d(TAG, "setBydRadioParam RADIO_BAND_FM is : " + state);
        }
        return state == 0 ? 0 : -2147482648;
    }

    public int[] getRadioParam(int band) {
        if (1 == this.mForTest) {
            Log.d(TAG, "getRadioParam mForTestValue is" + this.mForTest + " execute return");
            return new int[0];
        }
        RadioTuner radioTuner = this.mRadioTuner;
        if (radioTuner != null) {
            if (band == 0) {
                RadioManager.BandConfig[] amBandConfigs = new RadioManager.AmBandConfig[1];
                int result = radioTuner.getConfiguration(amBandConfigs);
                if (result == 0) {
                    int lowerLimit = amBandConfigs[0].getLowerLimit();
                    int upperLimit = amBandConfigs[0].getUpperLimit();
                    int step = amBandConfigs[0].getSpacing();
                    int[] resultAm = {lowerLimit, upperLimit, step};
                    return resultAm;
                }
            } else {
                RadioManager.BandConfig[] fmBandConfigs = new RadioManager.FmBandConfig[1];
                int result2 = radioTuner.getConfiguration(fmBandConfigs);
                if (result2 == 0) {
                    int lowerLimit2 = fmBandConfigs[0].getLowerLimit();
                    int upperLimit2 = fmBandConfigs[0].getUpperLimit();
                    int step2 = fmBandConfigs[0].getSpacing();
                    int[] resultFm = {lowerLimit2, upperLimit2, step2};
                    return resultFm;
                }
            }
        }
        return new int[0];
    }

    public int getRadioBand() {
        Log.d(TAG, "getRadioBand is execute");
        if (1 != this.mForTest) {
            return 1;
        }
        Log.d(TAG, "getRadioBand mForTestValue is" + this.mForTest + " execute return");
        return 0;
    }

    public int setCurFreq(int band, int frequency) {
        ProgramSelector programSelector;
        if (1 == this.mForTest) {
            Log.d(TAG, "setCurFreq mForTestValue is" + this.mForTest + " execute return");
            return 0;
        }
        RadioTuner radioTuner = this.mRadioTuner;
        if (radioTuner != null) {
            boolean hasControl = radioTuner.hasControl();
            if (hasControl) {
                if (band == 1) {
                    programSelector = ProgramSelector.createAmFmSelector(0, frequency, 0);
                } else {
                    if (band != 0) {
                        return -2147482648;
                    }
                    programSelector = ProgramSelector.createAmFmSelector(1, frequency, 0);
                }
                try {
                    this.mRadioTuner.tune(programSelector);
                    return 0;
                } catch (IllegalStateException illegalStateException) {
                    Log.e(TAG, "Can't tune", illegalStateException);
                    return -2147482648;
                }
            }
        }
        return 0;
    }

    public int getCurFreq() {
        if (1 != this.mForTest) {
            return 0;
        }
        Log.d(TAG, "getAcStartState mForTestValue is" + this.mForTest + " execute return");
        return 0;
    }

    public int autoSearch() {
        if (1 == this.mForTest) {
            Log.d(TAG, "getAcStartState mForTestValue is" + this.mForTest + " execute return");
            return 0;
        }
        RadioTuner radioTuner = this.mRadioTuner;
        if (radioTuner == null) {
            return 0;
        }
        int status = radioTuner.scan(0, false);
        return status == 0 ? 0 : -2147482648;
    }

    public int cancelAutoSearch() {
        if (1 == this.mForTest) {
            Log.d(TAG, "getAcStartState mForTestValue is" + this.mForTest + " execute return");
            return -2147482648;
        }
        RadioTuner radioTuner = this.mRadioTuner;
        if (radioTuner == null) {
            return -2147482648;
        }
        int state = radioTuner.cancel();
        return state == 0 ? 0 : -2147482648;
    }

    public int seek(boolean z) {
        if (1 == this.mForTest) {
            Log.d(TAG, "getAcStartState mForTestValue is" + this.mForTest + " execute return");
            return 0;
        }
        RadioTuner radioTuner = this.mRadioTuner;
        return (radioTuner == null || radioTuner.scan(!z ? 1 : 0, true) == 0) ? 0 : -2147482648;
    }

    public int getSearchProcessState() {
        if (1 != this.mForTest) {
            return 0;
        }
        Log.d(TAG, "getAcStartState mForTestValue is" + this.mForTest + " execute return");
        return 0;
    }

    public boolean postEvent(int device_type, int event_type, int value, Object data) {
        if (1 != this.mForTest) {
            return false;
        }
        Log.d(TAG, "getAcStartState mForTestValue is" + this.mForTest + " execute return");
        return false;
    }

    public boolean postEvent(int device_type, int event_type, byte[] value, Object data) {
        if (1 != this.mForTest) {
            return false;
        }
        Log.d(TAG, "getAcStartState mForTestValue is" + this.mForTest + " execute return");
        return false;
    }

    public int getType() {
        if (1 != this.mForTest) {
            return 0;
        }
        Log.d(TAG, "getAcStartState mForTestValue is" + this.mForTest + " execute return");
        return 0;
    }

    public int[] getFeatureList() {
        if (1 == this.mForTest) {
            Log.d(TAG, "getAcStartState mForTestValue is" + this.mForTest + " execute return");
            return new int[0];
        }
        return new int[0];
    }

    public void registerListener(AbsBYDAutoRadioListener listener) {
        if (1 == this.mForTest) {
            Log.d(TAG, "unregisterListener mForTestValue is" + this.mForTest + " execute return");
            return;
        }
        this.mAbsBydAutoRadioListener = listener;
    }

    public void registerListener(AbsBYDAutoRadioListener listener, int[] featureIDs) {
        if (1 == this.mForTest) {
            Log.d(TAG, "unregisterListener mForTestValue is" + this.mForTest + " execute return");
            return;
        }
        this.mAbsBydAutoRadioListener = listener;
    }

    public void unregisterListener(AbsBYDAutoRadioListener listener) {
        if (1 == this.mForTest) {
            Log.d(TAG, "unregisterListener mForTestValue is" + this.mForTest + " execute return");
            return;
        }
        this.mAbsBydAutoRadioListener = null;
    }
}
