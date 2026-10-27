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
    protected static final String TAG = "BYDAutoCPUTEMPATUREDevice";
    public static final int TEC_CTL_MAX = 100;
    public static final int TEC_CTL_MIN = 0;
    private static BYDAutoCpuTempratureDevice sBYDAutoCpuTempratureDevice;
    private CPUTempratureManagerImpl mImpl;

    BYDAutoCpuTempratureDevice(Context con) {
        super(con.getApplicationContext());
        this.mImpl = CPUTempratureManagerImpl.getsInstance(con.getApplicationContext());
    }

    public static synchronized BYDAutoCpuTempratureDevice getInstance(Context con) {
        if (sBYDAutoCpuTempratureDevice == null) {
            sBYDAutoCpuTempratureDevice = new BYDAutoCpuTempratureDevice(con.getApplicationContext());
        }
        return sBYDAutoCpuTempratureDevice;
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
        return this.mImpl.getGetPermission();
    }

    public int setCpuTemprature(int temprature) {
        return this.mImpl.setCpuTemprature(temprature);
    }

    public int setTecCtrlLevel(int level) {
        return this.mImpl.setTecCtrlLevel(level);
    }

    public int setPmicTemprature(int temprature) {
        return this.mImpl.setPmicTemprature(temprature);
    }

    public int setTemprature(int cpuTemprature, int pmicTemprature) {
        return this.mImpl.setTemprature(cpuTemprature, pmicTemprature);
    }

    public void setAllStatus() {
        this.mImpl.setAllStatus();
    }

    @Override // android.hardware.IBYDAutoDevice
    public int getType() {
        return this.mImpl.getType();
    }

    @Override // android.hardware.IBYDAutoDevice
    public int[] getFeatureList() {
        return this.mImpl.getFeatureList();
    }
}
