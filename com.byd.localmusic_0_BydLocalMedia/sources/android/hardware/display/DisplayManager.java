package android.hardware.display;

import android.annotation.SystemApi;
import android.content.Context;
import android.graphics.Point;
import android.hardware.display.VirtualDisplay;
import android.hardware.display.VirtualDisplayConfig;
import android.media.projection.MediaProjection;
import android.os.Handler;
import android.util.Pair;
import android.util.SparseArray;
import android.view.Display;
import android.view.Surface;
import java.util.ArrayList;
import java.util.List;

/* JADX INFO: loaded from: classes2.dex */
public final class DisplayManager {
    public static final String ACTION_WIFI_DISPLAY_STATUS_CHANGED = "android.hardware.display.action.WIFI_DISPLAY_STATUS_CHANGED";
    private static final boolean DEBUG = false;
    public static final String DISPLAY_CATEGORY_PRESENTATION = "android.hardware.display.category.PRESENTATION";
    public static final String EXTRA_WIFI_DISPLAY_STATUS = "android.hardware.display.extra.WIFI_DISPLAY_STATUS";
    private static final String TAG = "DisplayManager";
    public static final int VIRTUAL_DISPLAY_FLAG_AUTO_MIRROR = 16;
    public static final int VIRTUAL_DISPLAY_FLAG_CAN_SHOW_WITH_INSECURE_KEYGUARD = 32;
    public static final int VIRTUAL_DISPLAY_FLAG_DESTROY_CONTENT_ON_REMOVAL = 256;
    public static final int VIRTUAL_DISPLAY_FLAG_OWN_CONTENT_ONLY = 8;
    public static final int VIRTUAL_DISPLAY_FLAG_PRESENTATION = 2;
    public static final int VIRTUAL_DISPLAY_FLAG_PUBLIC = 1;
    public static final int VIRTUAL_DISPLAY_FLAG_ROTATES_WITH_CONTENT = 128;
    public static final int VIRTUAL_DISPLAY_FLAG_SECURE = 4;
    public static final int VIRTUAL_DISPLAY_FLAG_SHOULD_SHOW_SYSTEM_DECORATIONS = 512;
    public static final int VIRTUAL_DISPLAY_FLAG_SUPPORTS_TOUCH = 64;
    public static final int VIRTUAL_DISPLAY_FLAG_TRUSTED = 1024;
    private final Context mContext;
    private final Object mLock = new Object();
    private final SparseArray<Display> mDisplays = new SparseArray<>();
    private final ArrayList<Display> mTempDisplays = new ArrayList<>();
    private final DisplayManagerGlobal mGlobal = DisplayManagerGlobal.getInstance();

    public interface DeviceConfig {
        public static final String KEY_FIXED_REFRESH_RATE_HIGH_AMBIENT_BRIGHTNESS_THRESHOLDS = "fixed_refresh_rate_high_ambient_brightness_thresholds";
        public static final String KEY_FIXED_REFRESH_RATE_HIGH_DISPLAY_BRIGHTNESS_THRESHOLDS = "fixed_refresh_rate_high_display_brightness_thresholds";
        public static final String KEY_FIXED_REFRESH_RATE_LOW_AMBIENT_BRIGHTNESS_THRESHOLDS = "peak_refresh_rate_ambient_thresholds";
        public static final String KEY_FIXED_REFRESH_RATE_LOW_DISPLAY_BRIGHTNESS_THRESHOLDS = "peak_refresh_rate_brightness_thresholds";
        public static final String KEY_HIGH_REFRESH_RATE_BLACKLIST = "high_refresh_rate_blacklist";
        public static final String KEY_PEAK_REFRESH_RATE_DEFAULT = "peak_refresh_rate_default";
        public static final String KEY_REFRESH_RATE_IN_HIGH_ZONE = "refresh_rate_in_high_zone";
        public static final String KEY_REFRESH_RATE_IN_LOW_ZONE = "refresh_rate_in_zone";
    }

    public interface DisplayListener {
        void onDisplayAdded(int i);

        void onDisplayChanged(int i);

        void onDisplayRemoved(int i);
    }

    public DisplayManager(Context context) {
        this.mContext = context;
    }

    public Display getDisplay(int i) {
        Display orCreateDisplayLocked;
        synchronized (this.mLock) {
            orCreateDisplayLocked = getOrCreateDisplayLocked(i, false);
        }
        return orCreateDisplayLocked;
    }

    public Display[] getDisplays() {
        return getDisplays(null);
    }

    public Display[] getDisplays(String str) {
        Display[] displayArr;
        int[] displayIds = this.mGlobal.getDisplayIds();
        synchronized (this.mLock) {
            try {
                if (str == null) {
                    addAllDisplaysLocked(this.mTempDisplays, displayIds);
                } else if (str.equals("android.hardware.display.category.PRESENTATION")) {
                    addPresentationDisplaysLocked(this.mTempDisplays, displayIds, 3);
                    addPresentationDisplaysLocked(this.mTempDisplays, displayIds, 2);
                    addPresentationDisplaysLocked(this.mTempDisplays, displayIds, 4);
                    addPresentationDisplaysLocked(this.mTempDisplays, displayIds, 5);
                }
                displayArr = (Display[]) this.mTempDisplays.toArray(new Display[this.mTempDisplays.size()]);
            } finally {
                this.mTempDisplays.clear();
            }
        }
        return displayArr;
    }

    private void addAllDisplaysLocked(ArrayList<Display> arrayList, int[] iArr) {
        for (int i : iArr) {
            Display orCreateDisplayLocked = getOrCreateDisplayLocked(i, true);
            if (orCreateDisplayLocked != null) {
                arrayList.add(orCreateDisplayLocked);
            }
        }
    }

    private void addPresentationDisplaysLocked(ArrayList<Display> arrayList, int[] iArr, int i) {
        for (int i2 : iArr) {
            Display orCreateDisplayLocked = getOrCreateDisplayLocked(i2, true);
            if (orCreateDisplayLocked != null && (orCreateDisplayLocked.getFlags() & 8) != 0 && orCreateDisplayLocked.getType() == i) {
                arrayList.add(orCreateDisplayLocked);
            }
        }
    }

    private Display getOrCreateDisplayLocked(int i, boolean z) {
        Display display = this.mDisplays.get(i);
        if (display == null) {
            Display compatibleDisplay = this.mGlobal.getCompatibleDisplay(i, this.mContext.getDisplayId() == i ? this.mContext.getResources() : null);
            if (compatibleDisplay == null) {
                return compatibleDisplay;
            }
            this.mDisplays.put(i, compatibleDisplay);
            return compatibleDisplay;
        }
        if (z || display.isValid()) {
            return display;
        }
        return null;
    }

    public void registerDisplayListener(DisplayListener displayListener, Handler handler) {
        this.mGlobal.registerDisplayListener(displayListener, handler);
    }

    public void unregisterDisplayListener(DisplayListener displayListener) {
        this.mGlobal.unregisterDisplayListener(displayListener);
    }

    public void startWifiDisplayScan() {
        this.mGlobal.startWifiDisplayScan();
    }

    public void stopWifiDisplayScan() {
        this.mGlobal.stopWifiDisplayScan();
    }

    public void connectWifiDisplay(String str) {
        this.mGlobal.connectWifiDisplay(str);
    }

    public void pauseWifiDisplay() {
        this.mGlobal.pauseWifiDisplay();
    }

    public void resumeWifiDisplay() {
        this.mGlobal.resumeWifiDisplay();
    }

    public void disconnectWifiDisplay() {
        this.mGlobal.disconnectWifiDisplay();
    }

    public void renameWifiDisplay(String str, String str2) {
        this.mGlobal.renameWifiDisplay(str, str2);
    }

    public void forgetWifiDisplay(String str) {
        this.mGlobal.forgetWifiDisplay(str);
    }

    public WifiDisplayStatus getWifiDisplayStatus() {
        return this.mGlobal.getWifiDisplayStatus();
    }

    @SystemApi
    public void setSaturationLevel(float f) {
        if (f < 0.0f || f > 1.0f) {
            throw new IllegalArgumentException("Saturation level must be between 0 and 1");
        }
        ((ColorDisplayManager) this.mContext.getSystemService(ColorDisplayManager.class)).setSaturationLevel(Math.round(f * 100.0f));
    }

    public VirtualDisplay createVirtualDisplay(String str, int i, int i2, int i3, Surface surface, int i4) {
        return createVirtualDisplay(str, i, i2, i3, surface, i4, null, null);
    }

    public VirtualDisplay createVirtualDisplay(String str, int i, int i2, int i3, Surface surface, int i4, VirtualDisplay.Callback callback, Handler handler) {
        VirtualDisplayConfig.Builder builder = new VirtualDisplayConfig.Builder(str, i, i2, i3);
        builder.setFlags(i4);
        if (surface != null) {
            builder.setSurface(surface);
        }
        return createVirtualDisplay(null, builder.build(), callback, handler);
    }

    public VirtualDisplay createVirtualDisplay(MediaProjection mediaProjection, String str, int i, int i2, int i3, Surface surface, int i4, VirtualDisplay.Callback callback, Handler handler, String str2) {
        VirtualDisplayConfig.Builder builder = new VirtualDisplayConfig.Builder(str, i, i2, i3);
        builder.setFlags(i4);
        if (str2 != null) {
            builder.setUniqueId(str2);
        }
        if (surface != null) {
            builder.setSurface(surface);
        }
        return createVirtualDisplay(mediaProjection, builder.build(), callback, handler);
    }

    public VirtualDisplay createVirtualDisplay(MediaProjection mediaProjection, VirtualDisplayConfig virtualDisplayConfig, VirtualDisplay.Callback callback, Handler handler) {
        return this.mGlobal.createVirtualDisplay(this.mContext, mediaProjection, virtualDisplayConfig, callback, handler);
    }

    @SystemApi
    public Point getStableDisplaySize() {
        return this.mGlobal.getStableDisplaySize();
    }

    @SystemApi
    public List<BrightnessChangeEvent> getBrightnessEvents() {
        return this.mGlobal.getBrightnessEvents(this.mContext.getOpPackageName());
    }

    @SystemApi
    public List<AmbientBrightnessDayStats> getAmbientBrightnessStats() {
        return this.mGlobal.getAmbientBrightnessStats();
    }

    @SystemApi
    public void setBrightnessConfiguration(BrightnessConfiguration brightnessConfiguration) {
        setBrightnessConfigurationForUser(brightnessConfiguration, this.mContext.getUserId(), this.mContext.getPackageName());
    }

    public void setBrightnessConfigurationForUser(BrightnessConfiguration brightnessConfiguration, int i, String str) {
        this.mGlobal.setBrightnessConfigurationForUser(brightnessConfiguration, i, str);
    }

    @SystemApi
    public BrightnessConfiguration getBrightnessConfiguration() {
        return getBrightnessConfigurationForUser(this.mContext.getUserId());
    }

    public BrightnessConfiguration getBrightnessConfigurationForUser(int i) {
        return this.mGlobal.getBrightnessConfigurationForUser(i);
    }

    @SystemApi
    public BrightnessConfiguration getDefaultBrightnessConfiguration() {
        return this.mGlobal.getDefaultBrightnessConfiguration();
    }

    public boolean isMinimalPostProcessingRequested(int i) {
        return this.mGlobal.isMinimalPostProcessingRequested(i);
    }

    public void setTemporaryBrightness(float f) {
        this.mGlobal.setTemporaryBrightness(f);
    }

    public void setTemporaryAutoBrightnessAdjustment(float f) {
        this.mGlobal.setTemporaryAutoBrightnessAdjustment(f);
    }

    @SystemApi
    public Pair<float[], float[]> getMinimumBrightnessCurve() {
        return this.mGlobal.getMinimumBrightnessCurve();
    }
}
