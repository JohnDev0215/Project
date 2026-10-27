package android.hardware.bydauto.radio.tsimpl;

import android.content.ContentResolver;
import android.content.Context;
import android.hardware.bydauto.radio.AbsBYDAutoRadioListener;
import android.hardware.radio.ProgramSelector;
import android.hardware.radio.RadioManager;
import android.hardware.radio.RadioTuner;
import android.provider.Settings;
import android.util.Log;
import java.util.ArrayList;
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
        public void onConfigurationChanged(RadioManager.BandConfig bandConfig) {
            if (RadioManagerImpl.this.mAbsBydAutoRadioListener != null) {
                RadioManagerImpl.this.mAbsBydAutoRadioListener.onRadioParamChanged(bandConfig.getType(), bandConfig.getLowerLimit(), bandConfig.getUpperLimit(), bandConfig.getSpacing());
            }
        }

        public void onError(int i) {
            if (RadioManagerImpl.this.mAbsBydAutoRadioListener != null) {
                Log.d(RadioManagerImpl.TAG, "RadioManagerImpl onError is" + i);
                RadioManagerImpl.this.mAbsBydAutoRadioListener.onError(i, "error status");
            }
        }

        public void onParametersUpdated(Map<String, String> map) {
            Log.d(RadioManagerImpl.TAG, "RadioManagerImpl onParametersUpdated is" + map.toString());
        }

        public void onProgramInfoChanged(RadioManager.ProgramInfo programInfo) {
            if (RadioManagerImpl.this.mAbsBydAutoRadioListener != null) {
                int programType = programInfo.getSelector().getProgramType();
                int channel = programInfo.getChannel();
                RadioManagerImpl.this.mAbsBydAutoRadioListener.onCurFreqChanged(!programInfo.isTuned() ? 1 : 0, programType, channel);
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

    public int autoSearch() {
        if (1 != this.mForTest) {
            RadioTuner radioTuner = this.mRadioTuner;
            return (radioTuner == null || radioTuner.scan(0, false) == 0) ? 0 : -2147482648;
        }
        Log.d(TAG, "getAcStartState mForTestValue is" + this.mForTest + " execute return");
        return 0;
    }

    public int cancelAutoSearch() {
        if (1 != this.mForTest) {
            RadioTuner radioTuner = this.mRadioTuner;
            return (radioTuner == null || radioTuner.cancel() != 0) ? -2147482648 : 0;
        }
        Log.d(TAG, "getAcStartState mForTestValue is" + this.mForTest + " execute return");
        return -2147482648;
    }

    public int getCurFreq() {
        if (1 == this.mForTest) {
            Log.d(TAG, "getAcStartState mForTestValue is" + this.mForTest + " execute return");
        }
        return 0;
    }

    public int getDeviceType() {
        if (1 != this.mForTest) {
            return 1021;
        }
        Log.d(TAG, "getDeviceType mForTestValue is" + this.mForTest + " execute return");
        return 0;
    }

    public int[] getFeatureList() {
        if (1 != this.mForTest) {
            return new int[0];
        }
        Log.d(TAG, "getAcStartState mForTestValue is" + this.mForTest + " execute return");
        return new int[0];
    }

    public String getGetPermission() {
        return "";
    }

    public int getRadioBand() {
        Log.d(TAG, "getRadioBand is execute");
        if (1 != this.mForTest) {
            return 1;
        }
        Log.d(TAG, "getRadioBand mForTestValue is" + this.mForTest + " execute return");
        return 0;
    }

    public int[] getRadioParam(int i) {
        if (1 == this.mForTest) {
            Log.d(TAG, "getRadioParam mForTestValue is" + this.mForTest + " execute return");
            return new int[0];
        }
        RadioTuner radioTuner = this.mRadioTuner;
        if (radioTuner != null) {
            if (i == 0) {
                RadioManager.BandConfig[] bandConfigArr = new RadioManager.AmBandConfig[1];
                if (radioTuner.getConfiguration(bandConfigArr) == 0) {
                    return new int[]{bandConfigArr[0].getLowerLimit(), bandConfigArr[0].getUpperLimit(), bandConfigArr[0].getSpacing()};
                }
            } else {
                RadioManager.BandConfig[] bandConfigArr2 = new RadioManager.FmBandConfig[1];
                if (radioTuner.getConfiguration(bandConfigArr2) == 0) {
                    return new int[]{bandConfigArr2[0].getLowerLimit(), bandConfigArr2[0].getUpperLimit(), bandConfigArr2[0].getSpacing()};
                }
            }
        }
        return new int[0];
    }

    public int getRadioState() {
        if (1 == this.mForTest) {
            Log.d(TAG, "getRadioState mForTestValue is" + this.mForTest + " execute return");
        }
        return 0;
    }

    public int getSearchProcessState() {
        if (1 == this.mForTest) {
            Log.d(TAG, "getAcStartState mForTestValue is" + this.mForTest + " execute return");
        }
        return 0;
    }

    public String getSetPermission() {
        return "";
    }

    public int getType() {
        if (1 == this.mForTest) {
            Log.d(TAG, "getAcStartState mForTestValue is" + this.mForTest + " execute return");
        }
        return 0;
    }

    public boolean postEvent(int i, int i2, int i3, Object obj) {
        if (1 == this.mForTest) {
            Log.d(TAG, "getAcStartState mForTestValue is" + this.mForTest + " execute return");
        }
        return false;
    }

    public int radioOff() {
        if (1 != this.mForTest) {
            RadioTuner radioTuner = this.mRadioTuner;
            if (radioTuner == null) {
                return 0;
            }
            radioTuner.close();
            return -2147482648;
        }
        Log.d(TAG, "radioOff mForTestValue is" + this.mForTest + " execute return");
        return 0;
    }

    public int radioOn() {
        if (1 != this.mForTest) {
            this.mRadioManager.listModules(new ArrayList());
            return 0;
        }
        Log.d(TAG, "radioOn mForTestValue is" + this.mForTest + " execute return");
        return 0;
    }

    public void registerListener(AbsBYDAutoRadioListener absBYDAutoRadioListener) {
        if (1 != this.mForTest) {
            this.mAbsBydAutoRadioListener = absBYDAutoRadioListener;
            return;
        }
        Log.d(TAG, "unregisterListener mForTestValue is" + this.mForTest + " execute return");
    }

    public int seek(boolean z) {
        if (1 != this.mForTest) {
            RadioTuner radioTuner = this.mRadioTuner;
            return (radioTuner == null || radioTuner.scan(!z ? 1 : 0, true) == 0) ? 0 : -2147482648;
        }
        Log.d(TAG, "getAcStartState mForTestValue is" + this.mForTest + " execute return");
        return 0;
    }

    public int setCurFreq(int i, int i2) {
        ProgramSelector programSelectorCreateAmFmSelector;
        if (1 == this.mForTest) {
            Log.d(TAG, "setCurFreq mForTestValue is" + this.mForTest + " execute return");
            return 0;
        }
        RadioTuner radioTuner = this.mRadioTuner;
        if (radioTuner == null || !radioTuner.hasControl()) {
            return 0;
        }
        if (i != 1) {
            if (i == 0) {
                programSelectorCreateAmFmSelector = ProgramSelector.createAmFmSelector(1, i2, 0);
            }
            return -2147482648;
        }
        programSelectorCreateAmFmSelector = ProgramSelector.createAmFmSelector(0, i2, 0);
        try {
            this.mRadioTuner.tune(programSelectorCreateAmFmSelector);
            return 0;
        } catch (IllegalStateException e) {
            Log.e(TAG, "Can't tune", e);
        }
    }

    public int setRadioParam(int i, int i2, int i3, int i4) {
        int configuration;
        if (1 == this.mForTest) {
            Log.d(TAG, "setRadioParam mForTestValue is" + this.mForTest + " execute return");
            return 0;
        }
        if (this.mRadioTuner == null) {
            return 0;
        }
        if (i == 0) {
            RadioManager.AmBandConfig amBandConfigBuild = new RadioManager.AmBandConfig.Builder(new RadioManager.AmBandDescriptor(0, i, i2, i3, i4, true)).setStereo(true).build();
            Log.d(TAG, "setRadioParam AmConfig :" + amBandConfigBuild.toString());
            configuration = this.mRadioTuner.setConfiguration(amBandConfigBuild);
            Log.d(TAG, "setBydRadioParam RADIO_BAND_AM is : " + configuration);
        } else {
            if (i != 1) {
                return -2147482648;
            }
            RadioManager.FmBandConfig fmBandConfigBuild = new RadioManager.FmBandConfig.Builder(new RadioManager.FmBandDescriptor(0, i, i2, i3, i4, true, false, false, false, false)).setStereo(true).build();
            Log.d(TAG, "setRadioParam FmConfig :" + fmBandConfigBuild.toString());
            configuration = this.mRadioTuner.setConfiguration(fmBandConfigBuild);
            Log.d(TAG, "setBydRadioParam RADIO_BAND_FM is : " + configuration);
        }
        return configuration == 0 ? 0 : -2147482648;
    }

    public void unregisterListener(AbsBYDAutoRadioListener absBYDAutoRadioListener) {
        if (1 != this.mForTest) {
            this.mAbsBydAutoRadioListener = null;
            return;
        }
        Log.d(TAG, "unregisterListener mForTestValue is" + this.mForTest + " execute return");
    }

    public boolean postEvent(int i, int i2, byte[] bArr, Object obj) {
        if (1 == this.mForTest) {
            Log.d(TAG, "getAcStartState mForTestValue is" + this.mForTest + " execute return");
        }
        return false;
    }

    public void registerListener(AbsBYDAutoRadioListener absBYDAutoRadioListener, int[] iArr) {
        if (1 == this.mForTest) {
            Log.d(TAG, "unregisterListener mForTestValue is" + this.mForTest + " execute return");
            return;
        }
        this.mAbsBydAutoRadioListener = absBYDAutoRadioListener;
    }
}
