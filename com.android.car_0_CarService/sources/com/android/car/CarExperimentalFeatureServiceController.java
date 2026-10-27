package com.android.car;

import android.car.IExperimentalCar;
import android.car.IExperimentalCarHelper;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.IBinder;
import android.os.Process;
import android.os.RemoteException;
import android.os.UserHandle;
import android.util.ArrayMap;
import android.util.Log;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

/* JADX INFO: loaded from: classes3.dex */
public final class CarExperimentalFeatureServiceController implements CarServiceBase {
    private static final String TAG = "CAR.EXPERIMENTAL";
    private boolean mBound;
    private final Context mContext;
    private IExperimentalCar mExperimentalCar;
    private final ServiceConnection mServiceConnection = new ServiceConnection() { // from class: com.android.car.CarExperimentalFeatureServiceController.1
        @Override // android.content.ServiceConnection
        public void onServiceConnected(ComponentName name, IBinder service) {
            IExperimentalCar experimentalCar;
            synchronized (CarExperimentalFeatureServiceController.this.mLock) {
                experimentalCar = IExperimentalCar.Stub.asInterface(service);
                CarExperimentalFeatureServiceController.this.mExperimentalCar = experimentalCar;
            }
            if (experimentalCar == null) {
                Log.e(CarExperimentalFeatureServiceController.TAG, "Experimental car returned null binder");
                return;
            }
            CarFeatureController featureController = (CarFeatureController) CarLocalServices.getService(CarFeatureController.class);
            List<String> enabledExperimentalFeatures = featureController.getEnabledExperimentalFeatures();
            try {
                experimentalCar.init(CarExperimentalFeatureServiceController.this.mHelper, enabledExperimentalFeatures);
            } catch (RemoteException e) {
                Log.e(CarExperimentalFeatureServiceController.TAG, "Experimental car service crashed", e);
            }
        }

        @Override // android.content.ServiceConnection
        public void onServiceDisconnected(ComponentName name) {
            CarExperimentalFeatureServiceController.this.resetFeatures();
        }
    };
    private final IExperimentalCarHelper mHelper = new IExperimentalCarHelper.Stub() { // from class: com.android.car.CarExperimentalFeatureServiceController.2
        public void onInitComplete(List<String> allAvailableFeatures, List<String> startedFeatures, List<String> classNames, List<IBinder> binders) {
            if (allAvailableFeatures == null) {
                Log.e(CarExperimentalFeatureServiceController.TAG, "Experimental car passed null allAvailableFeatures");
                return;
            }
            if (startedFeatures == null || classNames == null || binders == null) {
                Log.i(CarExperimentalFeatureServiceController.TAG, "Nothing enabled in Experimental car");
                return;
            }
            int sizeOfStartedFeatures = startedFeatures.size();
            if (sizeOfStartedFeatures != classNames.size() || sizeOfStartedFeatures != binders.size()) {
                Log.e(CarExperimentalFeatureServiceController.TAG, "Experimental car passed wrong lists of enabled features, startedFeatures:" + startedFeatures + " classNames:" + classNames + " binders:" + binders);
            }
            ArrayList<String> classNamesInArray = new ArrayList<>(classNames);
            ArrayList<IBinder> bindersInArray = new ArrayList<>(binders);
            synchronized (CarExperimentalFeatureServiceController.this.mLock) {
                for (int i = 0; i < startedFeatures.size(); i++) {
                    CarExperimentalFeatureServiceController.this.mEnabledFeatures.put(startedFeatures.get(i), new FeatureInfo(classNamesInArray.get(i), bindersInArray.get(i)));
                }
            }
            CarFeatureController featureController = (CarFeatureController) CarLocalServices.getService(CarFeatureController.class);
            featureController.setAvailableExperimentalFeatureList(allAvailableFeatures);
            Log.i(CarExperimentalFeatureServiceController.TAG, "Available experimental features:" + allAvailableFeatures);
            Log.i(CarExperimentalFeatureServiceController.TAG, "Started experimental features:" + startedFeatures);
        }
    };
    private final Object mLock = new Object();
    private final ArrayMap<String, FeatureInfo> mEnabledFeatures = new ArrayMap<>();

    private static class FeatureInfo {
        public final IBinder binder;
        public final String className;

        FeatureInfo(String className, IBinder binder) {
            this.className = className;
            this.binder = binder;
        }
    }

    public CarExperimentalFeatureServiceController(Context context) {
        this.mContext = context;
    }

    @Override // com.android.car.CarServiceBase
    public void init() {
        Intent intent = new Intent();
        intent.setComponent(new ComponentName("com.android.experimentalcar", "com.android.experimentalcar.ExperimentalCarService"));
        boolean bound = bindService(intent);
        if (!bound) {
            Log.e(TAG, "Cannot bind to experimental car service, intent:" + intent);
        }
        synchronized (this.mLock) {
            this.mBound = bound;
        }
    }

    public boolean bindService(Intent intent) {
        int myUid = Process.myUid();
        if (myUid != 1000) {
            Log.w(TAG, "Binding experimental service skipped as this may be test env, uid:" + myUid);
            return false;
        }
        try {
            return this.mContext.bindServiceAsUser(intent, this.mServiceConnection, 1, UserHandle.SYSTEM);
        } catch (Exception e) {
            Log.e(TAG, "Cannot bind to experimental car service", e);
            return false;
        }
    }

    @Override // com.android.car.CarServiceBase
    public void release() {
        synchronized (this.mLock) {
            if (this.mBound) {
                this.mContext.unbindService(this.mServiceConnection);
            }
            this.mBound = false;
            resetFeatures();
        }
    }

    @Override // com.android.car.CarServiceBase
    public void dump(PrintWriter writer) {
        writer.println("*CarExperimentalFeatureServiceController*");
        synchronized (this.mLock) {
            writer.println(" mEnabledFeatures, number of features:" + this.mEnabledFeatures.size() + ", format: (feature, class)");
            for (int i = 0; i < this.mEnabledFeatures.size(); i++) {
                String feature = this.mEnabledFeatures.keyAt(i);
                FeatureInfo info = this.mEnabledFeatures.valueAt(i);
                writer.println(feature + "," + info.className);
            }
            writer.println("mBound:" + this.mBound);
        }
    }

    public String getCarManagerClassForFeature(String featureName) {
        FeatureInfo info;
        synchronized (this.mLock) {
            info = this.mEnabledFeatures.get(featureName);
        }
        if (info == null) {
            return null;
        }
        return info.className;
    }

    public IBinder getCarService(String serviceName) {
        FeatureInfo info;
        synchronized (this.mLock) {
            info = this.mEnabledFeatures.get(serviceName);
        }
        if (info == null) {
            return null;
        }
        return info.binder;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void resetFeatures() {
        synchronized (this.mLock) {
            this.mExperimentalCar = null;
            this.mEnabledFeatures.clear();
        }
    }
}
