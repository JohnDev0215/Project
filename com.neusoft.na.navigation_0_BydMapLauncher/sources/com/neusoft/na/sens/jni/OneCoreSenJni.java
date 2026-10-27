package com.neusoft.na.sens.jni;

import android.util.Log;
import com.neusoft.na.sens.OneCoreSenRegister;
import com.neusoft.na.sens.VisionLaneLineInfo;

/* JADX INFO: loaded from: classes2.dex */
public class OneCoreSenJni {
    public static OneCoreSenJni m_instance;

    public native void notifyChangeAcc(float[] fArr, long j);

    public native void notifyChangeAtmosphericPressure(float[] fArr, long j);

    public native void notifyChangeGyro(float[] fArr, long j);

    public native void notifyChangeMagnetic(float[] fArr, long j);

    public native void notifyChangeNMEA(String str, long j);

    public native void notifyChangeRotationVector(float[] fArr, long j);

    public native void notifyChangeSpeed(float f, long j);

    public native void notifyChangeTemperature(float[] fArr, long j);

    public native void notifyChangeVisionLaneLine(VisionLaneLineInfo visionLaneLineInfo, long j);

    public native void startSens(String str);

    public native void stopSens();

    public static synchronized OneCoreSenJni getInstance() {
        if (m_instance == null) {
            m_instance = new OneCoreSenJni();
        }
        return m_instance;
    }

    public String GetGnssDataString() {
        return OneCoreSenRegister.getInstance().getGnssString();
    }

    public float[] GetNaviAccelerSensor() {
        return OneCoreSenRegister.getInstance().getNaviAccelerSensor();
    }

    public float[] GetNaviGyroSensor() {
        return OneCoreSenRegister.getInstance().getNaviGyroSensor();
    }

    public float[] GetNaviMagneticSensor() {
        return OneCoreSenRegister.getInstance().getNaviMagneticSensor();
    }

    public float[] GetNaviRotationVectorSensor() {
        return OneCoreSenRegister.getInstance().getNaviRotationVectorSensor();
    }

    public float[] GetNaviAtmosphericPressureSensor() {
        return OneCoreSenRegister.getInstance().getNaviAtmosphericPressureSensor();
    }

    public float[] GetNaviTemperatureSensor() {
        return OneCoreSenRegister.getInstance().getNaviTemperatureSensor();
    }

    public float GetNaviSpeed() {
        return OneCoreSenRegister.getInstance().getNaviSpeed();
    }

    public boolean GetReverseSignal() {
        return OneCoreSenRegister.getInstance().getReverseSignal();
    }

    public int[] GetVehicleSpeedPulseArry() {
        return OneCoreSenRegister.getInstance().getVehicleSpeedPulseArry();
    }

    public int GetVehicleSpeedPulse() {
        return OneCoreSenRegister.getInstance().getVehicleSpeedPulse();
    }

    static {
        try {
            System.loadLibrary("na_sens_jni");
            Log.d("NA_SENS", "SensJni load");
        } catch (UnsatisfiedLinkError unused) {
            Log.d("NA_SENS", "SensJni load failed");
        }
    }
}
