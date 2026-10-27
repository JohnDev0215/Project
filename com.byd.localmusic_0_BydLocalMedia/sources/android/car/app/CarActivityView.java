package android.car.app;

import android.app.ActivityView;
import android.car.Car;
import android.car.drivingstate.CarUxRestrictionsManager;
import android.content.ComponentName;
import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;

/* JADX INFO: loaded from: classes.dex */
public final class CarActivityView extends ActivityView {
    private static final String TAG = CarActivityView.class.getSimpleName();
    private Car mCar;
    private volatile ActivityView.StateCallback mUserActivityViewCallback;
    private CarUxRestrictionsManager mUxRestrictionsManager;
    private int mVirtualDisplayId;

    public CarActivityView(Context context) {
        this(context, null);
    }

    public CarActivityView(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 0);
    }

    public CarActivityView(Context context, AttributeSet attributeSet, int i) {
        this(context, attributeSet, i, false);
    }

    public CarActivityView(Context context, AttributeSet attributeSet, int i, boolean z) {
        super(context, attributeSet, i, z, true);
        this.mVirtualDisplayId = -1;
        super.setCallback(new CarActivityViewCallback());
    }

    @Override // android.app.ActivityView
    public void setCallback(ActivityView.StateCallback stateCallback) {
        this.mUserActivityViewCallback = stateCallback;
        if (getVirtualDisplayId() == -1 || stateCallback == null) {
            return;
        }
        stateCallback.onActivityViewReady(this);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static void reportPhysicalDisplayId(CarUxRestrictionsManager carUxRestrictionsManager, int i, int i2) {
        Log.d(TAG, "reportPhysicalDisplayId: virtualDisplayId=" + i + ", physicalDisplayId=" + i2);
        if (i == -1) {
            Log.w(TAG, "No virtual display to report");
        } else if (carUxRestrictionsManager == null) {
            Log.w(TAG, "CarUxRestrictionsManager is not ready yet");
        } else {
            carUxRestrictionsManager.reportVirtualDisplayToPhysicalDisplay(i, i2);
        }
    }

    private class CarActivityViewCallback extends ActivityView.StateCallback {
        private CarActivityViewCallback() {
        }

        @Override // android.app.ActivityView.StateCallback
        public void onActivityViewReady(ActivityView activityView) {
            CarActivityView carActivityView = CarActivityView.this;
            carActivityView.mVirtualDisplayId = carActivityView.getVirtualDisplayId();
            CarActivityView.reportPhysicalDisplayId(CarActivityView.this.mUxRestrictionsManager, CarActivityView.this.mVirtualDisplayId, CarActivityView.this.mContext.getDisplayId());
            ActivityView.StateCallback stateCallback = CarActivityView.this.mUserActivityViewCallback;
            if (stateCallback != null) {
                stateCallback.onActivityViewReady(activityView);
            }
        }

        @Override // android.app.ActivityView.StateCallback
        public void onActivityViewDestroyed(ActivityView activityView) {
            int i = CarActivityView.this.mVirtualDisplayId;
            CarActivityView.this.mVirtualDisplayId = -1;
            CarActivityView.reportPhysicalDisplayId(CarActivityView.this.mUxRestrictionsManager, i, -1);
            ActivityView.StateCallback stateCallback = CarActivityView.this.mUserActivityViewCallback;
            if (stateCallback != null) {
                stateCallback.onActivityViewDestroyed(activityView);
            }
        }

        @Override // android.app.ActivityView.StateCallback
        public void onTaskCreated(int i, ComponentName componentName) {
            ActivityView.StateCallback stateCallback = CarActivityView.this.mUserActivityViewCallback;
            if (stateCallback != null) {
                stateCallback.onTaskCreated(i, componentName);
            }
        }

        @Override // android.app.ActivityView.StateCallback
        public void onTaskMovedToFront(int i) {
            ActivityView.StateCallback stateCallback = CarActivityView.this.mUserActivityViewCallback;
            if (stateCallback != null) {
                stateCallback.onTaskMovedToFront(i);
            }
        }

        @Override // android.app.ActivityView.StateCallback
        public void onTaskRemovalStarted(int i) {
            ActivityView.StateCallback stateCallback = CarActivityView.this.mUserActivityViewCallback;
            if (stateCallback != null) {
                stateCallback.onTaskRemovalStarted(i);
            }
        }
    }

    @Override // android.view.ViewGroup, android.view.View
    protected void onAttachedToWindow() {
        super.onAttachedToWindow();
        this.mCar = Car.createCar(this.mContext, null, 0L, new Car.CarServiceLifecycleListener() { // from class: android.car.app.-$$Lambda$CarActivityView$MxqQl3q8OCsOuebHhBjc2WecV6g
            @Override // android.car.Car.CarServiceLifecycleListener
            public final void onLifecycleChanged(Car car, boolean z) {
                this.f$0.lambda$onAttachedToWindow$0$CarActivityView(car, z);
            }
        });
    }

    public /* synthetic */ void lambda$onAttachedToWindow$0$CarActivityView(Car car, boolean z) {
        if (z) {
            CarUxRestrictionsManager carUxRestrictionsManager = (CarUxRestrictionsManager) car.getCarManager(Car.CAR_UX_RESTRICTION_SERVICE);
            this.mUxRestrictionsManager = carUxRestrictionsManager;
            int i = this.mVirtualDisplayId;
            if (i != -1) {
                reportPhysicalDisplayId(carUxRestrictionsManager, i, this.mContext.getDisplayId());
            }
        }
    }

    @Override // android.view.ViewGroup, android.view.View
    protected void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        Car car = this.mCar;
        if (car != null) {
            car.disconnect();
        }
    }
}
