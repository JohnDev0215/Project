package android.hardware.bydauto.cputemprature;

import android.content.Context;
import android.hardware.bydauto.AbsBYDAutoDevice;
import android.hardware.bydauto.cputemprature.tsimpl.CPUTempratureManagerImpl;

/* JADX INFO: loaded from: classes.dex */
public final class BYDAutoCpuTempratureDevice extends AbsBYDAutoDevice {
    public static final int CPUTEMPRATURE_COMMAND_BUSY = -2147482647;
    public static final int CPUTEMPRATURE_COMMAND_FAILED = -2147482648;
    public static final int CPUTEMPRATURE_COMMAND_INVALID_VALUE = -2147482645;
    public static final int CPUTEMPRATURE_COMMAND_SUCCESS = 0;
    public static final int CPUTEMPRATURE_COMMAND_TIMEOUT = -2147482646;
    public static final int CPU_TEMPRATURE_MAX = 155;
    public static final int CPU_TEMPRATURE_MIN = -100;
    public static final int PMIC_TEMPRATURE_MAX = 155;
    public static final int PMIC_TEMPRATURE_MIN = -100;
    public static final String TAG = "BYDAutoCPUTEMPATUREDevice";
    public static final int TEC_CTL_MAX = 100;
    public static final int TEC_CTL_MIN = 0;
    private static BYDAutoCpuTempratureDevice sBYDAutoCpuTempratureDevice;
    private CPUTempratureManagerImpl mImpl;

    public BYDAutoCpuTempratureDevice(Context context) {
        super(context.getApplicationContext());
        this.mImpl = CPUTempratureManagerImpl.getsInstance(context.getApplicationContext());
    }

    public static synchronized BYDAutoCpuTempratureDevice getInstance(Context context) {
        if (sBYDAutoCpuTempratureDevice == null) {
            sBYDAutoCpuTempratureDevice = new BYDAutoCpuTempratureDevice(context.getApplicationContext());
        }
        return sBYDAutoCpuTempratureDevice;
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
        return this.mImpl.getGetPermission();
    }

    @Override // android.hardware.bydauto.AbsBYDAutoDevice
    public String getSetPermission() {
        return this.mImpl.getSetPermission();
    }

    @Override // android.hardware.bydauto.AbsBYDAutoDevice
    public int getType() {
        return this.mImpl.getType();
    }

    public void setAllStatus() {
        this.mImpl.setAllStatus();
    }

    public int setCpuTemprature(int i3) {
        return this.mImpl.setCpuTemprature(i3);
    }

    public int setPmicTemprature(int i3) {
        return this.mImpl.setPmicTemprature(i3);
    }

    public int setTecCtrlLevel(int i3) {
        return this.mImpl.setTecCtrlLevel(i3);
    }

    public int setTemprature(int i3, int i4) {
        return this.mImpl.setTemprature(i3, i4);
    }
}
