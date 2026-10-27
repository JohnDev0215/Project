package com.ts.androidauto.aap.navigation;

import android.car.Car;
import android.car.CarAppFocusManager;
import android.car.CarNotConnectedException;
import android.content.ComponentName;
import android.content.Context;
import android.content.ServiceConnection;
import android.os.IBinder;
import com.google.android.projection.protocol.GalReceiver;
import com.ts.androidauto.aap.common.VehicleInfo;
import com.ts.androidauto.aap.sink.GalIntegration;
import com.ts.androidauto.aap.vehicledata.VehicleDataManager;
import com.ts.androidauto.fsdk.common.LogUtil;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

/* JADX INFO: loaded from: classes.dex */
public class NavigationFocusManager {
    private static final String TAG = "AAP" + NavigationFocusManager.class.getSimpleName();
    private static NavigationFocusManager mNavigationFocusManager;
    private Car mCarApi;
    private CarAppFocusManager mCarAppFocusManager;
    private Context mContext;
    private final GalIntegration mGal;
    private final List<NavigationFocusListener> mListeners = new CopyOnWriteArrayList();
    private boolean mUseNativeNavigation = true;
    private boolean mIsTruck = false;
    private boolean mAbandonFocus = false;
    private boolean mIsSupportNativNavigation = true;
    private final ServiceConnection mServiceConnection = new ServiceConnection() { // from class: com.ts.androidauto.aap.navigation.NavigationFocusManager.1
        @Override // android.content.ServiceConnection
        public void onServiceConnected(ComponentName name, IBinder service) {
            LogUtil.debug(NavigationFocusManager.TAG, "Navi Connected to Car Service");
            try {
                NavigationFocusManager.this.mCarAppFocusManager = (CarAppFocusManager) NavigationFocusManager.this.mCarApi.getCarManager("app_focus");
                try {
                    NavigationFocusManager.this.mCarAppFocusManager.addFocusListener(NavigationFocusManager.this.mListenerNav, 1);
                } catch (CarNotConnectedException exception) {
                    LogUtil.error(NavigationFocusManager.TAG, "Failed to register focus listener" + exception);
                }
            } catch (CarNotConnectedException exception2) {
                throw new RuntimeException("Failed to create CarAppFocusManager" + exception2);
            }
        }

        @Override // android.content.ServiceConnection
        public void onServiceDisconnected(ComponentName name) {
            LogUtil.debug(NavigationFocusManager.TAG, "Navi Disconnect from Car Service");
        }
    };
    private final CarAppFocusManager.OnAppFocusOwnershipCallback mOwnershipNavCallbacks = new CarAppFocusManager.OnAppFocusOwnershipCallback() { // from class: com.ts.androidauto.aap.navigation.NavigationFocusManager.2
        @Override // android.car.CarAppFocusManager.OnAppFocusOwnershipCallback
        public void onAppFocusOwnershipLost(int focus) {
            LogUtil.debug(NavigationFocusManager.TAG, "onAppFocusOwnershipLost():" + focus + " mUseNativeNavigation:" + NavigationFocusManager.this.mUseNativeNavigation);
            if (!NavigationFocusManager.this.mUseNativeNavigation) {
                NavigationFocusManager.this.enforceNavigationFocus(true);
            }
        }

        @Override // android.car.CarAppFocusManager.OnAppFocusOwnershipCallback
        public void onAppFocusOwnershipGranted(int focus) {
            LogUtil.debug(NavigationFocusManager.TAG, "onAppFocusOwnershipGranted():" + focus);
            NavigationFocusManager.this.enforceNavigationFocus(false);
        }
    };
    private final CarAppFocusManager.OnAppFocusChangedListener mListenerNav = new CarAppFocusManager.OnAppFocusChangedListener() { // from class: com.ts.androidauto.aap.navigation.NavigationFocusManager.3
        @Override // android.car.CarAppFocusManager.OnAppFocusChangedListener
        public void onAppFocusChanged(int appType, boolean active) {
            LogUtil.debug(NavigationFocusManager.TAG, "onAppFocusChanged():" + appType + " " + active + " mAbandonFocus:" + NavigationFocusManager.this.mAbandonFocus);
            if (appType == 1 && NavigationFocusManager.this.mAbandonFocus) {
                NavigationFocusManager.this.mAbandonFocus = false;
                NavigationFocusManager.this.enforceNavigationFocus(true);
            }
        }
    };

    public interface NavigationFocusListener {
        void onNavigationFocusChanged(boolean z);
    }

    public static synchronized NavigationFocusManager getInstance(GalIntegration gal, Context context) {
        if (mNavigationFocusManager == null) {
            mNavigationFocusManager = new NavigationFocusManager(gal, context);
        }
        return mNavigationFocusManager;
    }

    private NavigationFocusManager(GalIntegration gal, Context context) {
        this.mContext = null;
        this.mContext = context;
        this.mGal = gal;
        initVehicleInfo();
        initCarApi();
    }

    public void handleNavigationFocusRequest(int focusType) {
        LogUtil.info(TAG, "MD sends request focusType:" + focusType);
        if (!this.mIsTruck) {
            if (focusType == GalReceiver.NAVIGATION_FOCUS_STATE_PROJECTED) {
                if (isOwningFocus()) {
                    enforceNavigationFocus(false);
                    return;
                }
                boolean ret = requestNaviFocus();
                if (!ret) {
                    LogUtil.info(TAG, "request navi focus failed");
                    enforceNavigationFocus(false);
                    return;
                }
                return;
            }
            if (!this.mIsSupportNativNavigation) {
                enforceNavigationFocus(false);
                return;
            }
            if (isOwningFocus()) {
                boolean ret2 = abandonNaviFocus();
                if (!ret2) {
                    LogUtil.info(TAG, "abandon navi focus failed");
                    enforceNavigationFocus(true);
                    return;
                }
                return;
            }
            enforceNavigationFocus(true);
        }
    }

    private void initVehicleInfo() {
        VehicleInfo info = VehicleDataManager.getInstance().getVehicleInfo();
        if (info != null) {
            this.mIsTruck = info.mIsTruck;
            this.mIsSupportNativNavigation = info.mIsSupportNativeNavigation;
        }
    }

    public void enforceNavigationFocus(boolean useNativeNavigation) {
        int i;
        LogUtil.info(TAG, "enforceNavigationFocus useNativeNavigation:" + useNativeNavigation);
        dispatchNavigationFocus(useNativeNavigation);
        GalIntegration galIntegration = this.mGal;
        if (galIntegration != null && galIntegration.galReceiver != null) {
            GalReceiver galReceiver = this.mGal.galReceiver;
            if (useNativeNavigation) {
                i = GalReceiver.NAVIGATION_FOCUS_STATE_NATIVE;
            } else {
                i = GalReceiver.NAVIGATION_FOCUS_STATE_PROJECTED;
            }
            galReceiver.sendNavigationFocusState(i);
        }
    }

    public void sendNativeNaviStatus(boolean useNativeNavigation) {
        int i;
        LogUtil.info(TAG, "sendNativeNaviStatus useNativeNavigation:" + useNativeNavigation);
        GalIntegration galIntegration = this.mGal;
        if (galIntegration != null && galIntegration.galReceiver != null) {
            GalReceiver galReceiver = this.mGal.galReceiver;
            if (useNativeNavigation) {
                i = GalReceiver.NAVIGATION_FOCUS_STATE_NATIVE;
            } else {
                i = GalReceiver.NAVIGATION_FOCUS_STATE_PROJECTED;
            }
            galReceiver.sendNavigationFocusState(i);
            this.mUseNativeNavigation = useNativeNavigation;
        }
    }

    public synchronized void registerListener(NavigationFocusListener listener) {
        if (this.mListeners.add(listener)) {
            listener.onNavigationFocusChanged(this.mUseNativeNavigation);
        }
    }

    public synchronized void unregisterListener(NavigationFocusListener listener) {
        this.mListeners.remove(listener);
    }

    public synchronized void resetNaviStatus() {
        this.mUseNativeNavigation = true;
        for (NavigationFocusListener listener : this.mListeners) {
            listener.onNavigationFocusChanged(this.mUseNativeNavigation);
        }
    }

    private synchronized void dispatchNavigationFocus(boolean useNativeNavigation) {
        LogUtil.info(TAG, "dispatchNavigationFocus useNativeNavigation:" + useNativeNavigation);
        if (this.mUseNativeNavigation != useNativeNavigation) {
            this.mUseNativeNavigation = useNativeNavigation;
            for (NavigationFocusListener listener : this.mListeners) {
                listener.onNavigationFocusChanged(this.mUseNativeNavigation);
            }
        }
    }

    private void initCarApi() {
        Context context = this.mContext;
        if (context != null && context.getPackageManager() != null && this.mContext.getPackageManager().hasSystemFeature("android.hardware.type.automotive")) {
            Car carCreateCar = Car.createCar(this.mContext, this.mServiceConnection);
            this.mCarApi = carCreateCar;
            carCreateCar.connect();
        }
    }

    private boolean requestNaviFocus() {
        CarAppFocusManager carAppFocusManager = this.mCarAppFocusManager;
        if (carAppFocusManager == null) {
            return false;
        }
        int ret = 0;
        try {
            ret = carAppFocusManager.requestAppFocus(1, this.mOwnershipNavCallbacks);
        } catch (CarNotConnectedException exception) {
            LogUtil.error(TAG, "Failed to set active focus " + exception);
        }
        return ret == 1;
    }

    private boolean abandonNaviFocus() {
        LogUtil.debug(TAG, "abandonNaviFocus");
        CarAppFocusManager carAppFocusManager = this.mCarAppFocusManager;
        if (carAppFocusManager == null) {
            return false;
        }
        try {
            this.mAbandonFocus = true;
            carAppFocusManager.abandonAppFocus(this.mOwnershipNavCallbacks, 1);
            return true;
        } catch (IllegalArgumentException exception) {
            LogUtil.error(TAG, "Failed to abandon focus " + exception);
            return false;
        }
    }

    private boolean isOwningFocus() {
        boolean focus = false;
        CarAppFocusManager carAppFocusManager = this.mCarAppFocusManager;
        if (carAppFocusManager == null) {
            return false;
        }
        try {
            focus = carAppFocusManager.isOwningFocus(this.mOwnershipNavCallbacks, 1);
            LogUtil.debug(TAG, "isOwningFocus=" + focus);
            return focus;
        } catch (CarNotConnectedException exception) {
            LogUtil.error(TAG, "isOwningFocus failed " + exception);
            return focus;
        }
    }
}
