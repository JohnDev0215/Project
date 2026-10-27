package com.android.car.ui.utils;

import android.car.Car;
import android.car.drivingstate.CarUxRestrictions;
import android.car.drivingstate.CarUxRestrictionsManager;
import android.content.Context;
import android.os.Build;
import android.util.Log;
import androidx.core.app.FrameMetricsAggregator;
import com.android.car.ui.R;
import java.util.Collections;
import java.util.Set;
import java.util.WeakHashMap;

/* JADX INFO: loaded from: classes6.dex */
public class CarUxRestrictionsUtil {
    private static final String TAG = "CarUxRestrictionsUtil";
    private static CarUxRestrictionsUtil sInstance = null;
    private CarUxRestrictions mCarUxRestrictions = getDefaultRestrictions();
    private final Set<OnUxRestrictionsChangedListener> mObservers = Collections.newSetFromMap(new WeakHashMap());

    public interface OnUxRestrictionsChangedListener {
        void onRestrictionsChanged(CarUxRestrictions carUxRestrictions);
    }

    private CarUxRestrictionsUtil(Context context) {
        final CarUxRestrictionsManager.OnUxRestrictionsChangedListener listener = new CarUxRestrictionsManager.OnUxRestrictionsChangedListener() { // from class: com.android.car.ui.utils.-$$Lambda$CarUxRestrictionsUtil$b4kksuDJ2ImLxn6J1BQQlgUD4Tk
            @Override // android.car.drivingstate.CarUxRestrictionsManager.OnUxRestrictionsChangedListener
            public final void onUxRestrictionsChanged(CarUxRestrictions carUxRestrictions) {
                this.f$0.lambda$new$0$CarUxRestrictionsUtil(carUxRestrictions);
            }
        };
        if (Build.VERSION.SDK_INT >= 30) {
            Car.createCar(context.getApplicationContext(), null, 0L, new Car.CarServiceLifecycleListener() { // from class: com.android.car.ui.utils.-$$Lambda$CarUxRestrictionsUtil$AHf3Me05OJaXy6rDPxJ14T3Ju3k
                @Override // android.car.Car.CarServiceLifecycleListener
                public final void onLifecycleChanged(Car car, boolean z) {
                    CarUxRestrictionsUtil.lambda$new$1(listener, car, z);
                }
            });
            return;
        }
        Car carApi = Car.createCar(context.getApplicationContext());
        try {
            CarUxRestrictionsManager carUxRestrictionsManager = (CarUxRestrictionsManager) carApi.getCarManager("uxrestriction");
            carUxRestrictionsManager.registerListener(listener);
            listener.onUxRestrictionsChanged(carUxRestrictionsManager.getCurrentCarUxRestrictions());
        } catch (NullPointerException e) {
            Log.e(TAG, "Car not connected", e);
        }
    }

    public /* synthetic */ void lambda$new$0$CarUxRestrictionsUtil(CarUxRestrictions carUxRestrictions) {
        if (carUxRestrictions == null) {
            this.mCarUxRestrictions = getDefaultRestrictions();
        } else {
            this.mCarUxRestrictions = carUxRestrictions;
        }
        for (OnUxRestrictionsChangedListener observer : this.mObservers) {
            observer.onRestrictionsChanged(this.mCarUxRestrictions);
        }
    }

    static /* synthetic */ void lambda$new$1(CarUxRestrictionsManager.OnUxRestrictionsChangedListener listener, Car car, boolean ready) {
        if (ready) {
            CarUxRestrictionsManager carUxRestrictionsManager = (CarUxRestrictionsManager) car.getCarManager("uxrestriction");
            carUxRestrictionsManager.registerListener(listener);
            listener.onUxRestrictionsChanged(carUxRestrictionsManager.getCurrentCarUxRestrictions());
        } else {
            Log.w(TAG, "Car service disconnected, assuming fully restricted uxr");
            listener.onUxRestrictionsChanged(null);
        }
    }

    private static CarUxRestrictions getDefaultRestrictions() {
        return new CarUxRestrictions.Builder(true, FrameMetricsAggregator.EVERY_DURATION, 0L).build();
    }

    public static CarUxRestrictionsUtil getInstance(Context context) {
        if (sInstance == null) {
            sInstance = new CarUxRestrictionsUtil(context);
        }
        return sInstance;
    }

    public void register(OnUxRestrictionsChangedListener listener) {
        this.mObservers.add(listener);
        listener.onRestrictionsChanged(this.mCarUxRestrictions);
    }

    public void unregister(OnUxRestrictionsChangedListener listener) {
        this.mObservers.remove(listener);
    }

    public CarUxRestrictions getCurrentRestrictions() {
        return this.mCarUxRestrictions;
    }

    public static boolean isRestricted(int restrictionFlags, CarUxRestrictions uxr) {
        return uxr == null || (uxr.getActiveRestrictions() & restrictionFlags) != 0;
    }

    public static String complyString(Context context, String str, CarUxRestrictions uxr) {
        int maxLength;
        if (isRestricted(4, uxr)) {
            if (uxr == null) {
                maxLength = context.getResources().getInteger(R.integer.car_ui_default_max_string_length);
            } else {
                maxLength = uxr.getMaxRestrictedStringLength();
            }
            if (str.length() > maxLength) {
                return str.substring(0, maxLength) + context.getString(R.string.car_ui_ellipsis);
            }
        }
        return str;
    }

    public void setUxRestrictions(CarUxRestrictions carUxRestrictions) {
        this.mCarUxRestrictions = carUxRestrictions;
    }
}
