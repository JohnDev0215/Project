package android.hardware.bydauto.cputemprature.tsimpl;

import android.content.Context;

/* JADX INFO: loaded from: classes.dex */
public class CPUTempratureManagerImpl {
    protected static final String TAG = "CPUTempratureManagerImpl";
    private static CPUTempratureManagerImpl sInstance;
    private Context mContext;

    public CPUTempratureManagerImpl(Context context) {
        this.mContext = context;
    }

    public static CPUTempratureManagerImpl getsInstance(Context context) {
        if (sInstance == null) {
            synchronized (CPUTempratureManagerImpl.class) {
                if (sInstance == null) {
                    sInstance = new CPUTempratureManagerImpl(context);
                }
            }
        }
        return sInstance;
    }

    public int getDevicetype() {
        return 0;
    }

    public int[] getFeatureList() {
        return null;
    }

    public String getGetPermission() {
        return null;
    }

    public String getSetPermission() {
        return null;
    }

    public int getType() {
        return 0;
    }

    public int setCpuTemprature(int i) {
        return 0;
    }

    public int setPmicTemprature(int i) {
        return 0;
    }

    public int setTecCtrlLevel(int i) {
        return 0;
    }

    public int setTemprature(int i, int i2) {
        return 0;
    }

    public void setAllStatus() {
    }
}
