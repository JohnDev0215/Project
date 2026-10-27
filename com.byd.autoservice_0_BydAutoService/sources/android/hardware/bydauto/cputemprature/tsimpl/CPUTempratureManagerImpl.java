package android.hardware.bydauto.cputemprature.tsimpl;

import android.content.Context;
import android.util.Log;

/* JADX INFO: loaded from: classes.dex */
public class CPUTempratureManagerImpl {
    public static final String TAG = "CPUTempratureManagerImpl";
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
        Log.d(TAG, "getDevicetype is empty implementation. ");
        return 0;
    }

    public int[] getFeatureList() {
        Log.d(TAG, "getFeatureList is empty implementation. ");
        return null;
    }

    public String getGetPermission() {
        Log.d(TAG, "getGetPermission is empty implementation. ");
        return null;
    }

    public String getSetPermission() {
        Log.d(TAG, "getSetPermission is empty implementation. ");
        return null;
    }

    public int getType() {
        Log.d(TAG, "getType is empty implementation. ");
        return 0;
    }

    public void setAllStatus() {
        Log.d(TAG, "setAllStatus is empty implementation. ");
    }

    public int setCpuTemprature(int i3) {
        Log.d(TAG, "setCpuTemprature is empty implementation. ");
        return 0;
    }

    public int setPmicTemprature(int i3) {
        Log.d(TAG, "setPmicTemprature is empty implementation. ");
        return 0;
    }

    public int setTecCtrlLevel(int i3) {
        Log.d(TAG, "setTecCtrlLevel is empty implementation. ");
        return 0;
    }

    public int setTemprature(int i3, int i4) {
        Log.d(TAG, "setTemprature is empty implementation. ");
        return 0;
    }
}
