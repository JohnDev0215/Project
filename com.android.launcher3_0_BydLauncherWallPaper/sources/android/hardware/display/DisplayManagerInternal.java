package android.hardware.display;

import android.graphics.Point;
import android.hardware.SensorManager;
import android.os.Handler;
import android.util.IntArray;
import android.util.SparseArray;
import android.view.Display;
import android.view.DisplayInfo;
import android.view.SurfaceControl;

/* JADX INFO: loaded from: classes2.dex */
public abstract class DisplayManagerInternal {

    public interface DisplayPowerCallbacks {
        void acquireSuspendBlocker();

        void onDisplayStateChange(int i);

        void onProximityNegative();

        void onProximityPositive();

        void onStateChanged();

        void releaseSuspendBlocker();
    }

    public interface DisplayTransactionListener {
        void onDisplayTransaction(SurfaceControl.Transaction transaction);
    }

    public abstract DisplayInfo getDisplayInfo(int i);

    public abstract Point getDisplayPosition(int i);

    public abstract DisplayedContentSample getDisplayedContentSample(int i, long j, long j2);

    public abstract DisplayedContentSamplingAttributes getDisplayedContentSamplingAttributes(int i);

    public abstract void getNonOverrideDisplayInfo(int i, DisplayInfo displayInfo);

    public abstract void ignoreProximitySensorUntilChanged();

    public abstract void initPowerManagement(DisplayPowerCallbacks displayPowerCallbacks, Handler handler, SensorManager sensorManager);

    public abstract boolean isProximitySensorAvailable();

    public abstract void onOverlayChanged();

    public abstract void performTraversal(SurfaceControl.Transaction transaction);

    public abstract void persistBrightnessTrackerState();

    public abstract void registerDisplayTransactionListener(DisplayTransactionListener displayTransactionListener);

    public abstract boolean requestPowerState(DisplayPowerRequest displayPowerRequest, boolean z);

    public abstract void setDisplayAccessUIDs(SparseArray<IntArray> sparseArray);

    public abstract void setDisplayInfoOverrideFromWindowManager(int i, DisplayInfo displayInfo);

    public abstract void setDisplayOffsets(int i, int i2, int i3);

    public abstract void setDisplayProperties(int i, boolean z, float f, int i2, boolean z2, boolean z3);

    public abstract void setDisplayScalingDisabled(int i, boolean z);

    public abstract boolean setDisplayedContentSamplingEnabled(int i, boolean z, int i2, int i3);

    public abstract SurfaceControl.ScreenshotGraphicBuffer systemScreenshot(int i);

    public abstract void unregisterDisplayTransactionListener(DisplayTransactionListener displayTransactionListener);

    public abstract SurfaceControl.ScreenshotGraphicBuffer userScreenshot(int i);

    public static final class DisplayPowerRequest {
        public static final int POLICY_BRIGHT = 3;
        public static final int POLICY_DIM = 2;
        public static final int POLICY_DOZE = 1;
        public static final int POLICY_OFF = 0;
        public static final int POLICY_VR = 4;
        public boolean blockScreenOn;
        public boolean boostScreenBrightness;
        public float dozeScreenBrightness;
        public int dozeScreenState;
        public boolean lowPowerMode;
        public int policy;
        public float screenAutoBrightnessAdjustmentOverride;
        public float screenBrightnessOverride;
        public float screenLowPowerBrightnessFactor;
        public boolean useAutoBrightness;
        public boolean useProximitySensor;

        public int hashCode() {
            return 0;
        }

        public DisplayPowerRequest() {
            this.policy = 3;
            this.useProximitySensor = false;
            this.screenBrightnessOverride = Float.NaN;
            this.useAutoBrightness = false;
            this.screenAutoBrightnessAdjustmentOverride = Float.NaN;
            this.screenLowPowerBrightnessFactor = 0.5f;
            this.blockScreenOn = false;
            this.dozeScreenBrightness = Float.NaN;
            this.dozeScreenState = 0;
        }

        public DisplayPowerRequest(DisplayPowerRequest displayPowerRequest) {
            copyFrom(displayPowerRequest);
        }

        public boolean isBrightOrDim() {
            int i = this.policy;
            return i == 3 || i == 2;
        }

        public boolean isVr() {
            return this.policy == 4;
        }

        public void copyFrom(DisplayPowerRequest displayPowerRequest) {
            this.policy = displayPowerRequest.policy;
            this.useProximitySensor = displayPowerRequest.useProximitySensor;
            this.screenBrightnessOverride = displayPowerRequest.screenBrightnessOverride;
            this.useAutoBrightness = displayPowerRequest.useAutoBrightness;
            this.screenAutoBrightnessAdjustmentOverride = displayPowerRequest.screenAutoBrightnessAdjustmentOverride;
            this.screenLowPowerBrightnessFactor = displayPowerRequest.screenLowPowerBrightnessFactor;
            this.blockScreenOn = displayPowerRequest.blockScreenOn;
            this.lowPowerMode = displayPowerRequest.lowPowerMode;
            this.boostScreenBrightness = displayPowerRequest.boostScreenBrightness;
            this.dozeScreenBrightness = displayPowerRequest.dozeScreenBrightness;
            this.dozeScreenState = displayPowerRequest.dozeScreenState;
        }

        public boolean equals(Object obj) {
            return (obj instanceof DisplayPowerRequest) && equals((DisplayPowerRequest) obj);
        }

        public boolean equals(DisplayPowerRequest displayPowerRequest) {
            return displayPowerRequest != null && this.policy == displayPowerRequest.policy && this.useProximitySensor == displayPowerRequest.useProximitySensor && floatEquals(this.screenBrightnessOverride, displayPowerRequest.screenBrightnessOverride) && this.useAutoBrightness == displayPowerRequest.useAutoBrightness && floatEquals(this.screenAutoBrightnessAdjustmentOverride, displayPowerRequest.screenAutoBrightnessAdjustmentOverride) && this.screenLowPowerBrightnessFactor == displayPowerRequest.screenLowPowerBrightnessFactor && this.blockScreenOn == displayPowerRequest.blockScreenOn && this.lowPowerMode == displayPowerRequest.lowPowerMode && this.boostScreenBrightness == displayPowerRequest.boostScreenBrightness && floatEquals(this.dozeScreenBrightness, displayPowerRequest.dozeScreenBrightness) && this.dozeScreenState == displayPowerRequest.dozeScreenState;
        }

        private boolean floatEquals(float f, float f2) {
            return f == f2 || (Float.isNaN(f) && Float.isNaN(f2));
        }

        public String toString() {
            return "policy=" + policyToString(this.policy) + ", useProximitySensor=" + this.useProximitySensor + ", screenBrightnessOverride=" + this.screenBrightnessOverride + ", useAutoBrightness=" + this.useAutoBrightness + ", screenAutoBrightnessAdjustmentOverride=" + this.screenAutoBrightnessAdjustmentOverride + ", screenLowPowerBrightnessFactor=" + this.screenLowPowerBrightnessFactor + ", blockScreenOn=" + this.blockScreenOn + ", lowPowerMode=" + this.lowPowerMode + ", boostScreenBrightness=" + this.boostScreenBrightness + ", dozeScreenBrightness=" + this.dozeScreenBrightness + ", dozeScreenState=" + Display.stateToString(this.dozeScreenState);
        }

        public static String policyToString(int i) {
            return i != 0 ? i != 1 ? i != 2 ? i != 3 ? i != 4 ? Integer.toString(i) : "VR" : "BRIGHT" : "DIM" : "DOZE" : "OFF";
        }
    }
}
