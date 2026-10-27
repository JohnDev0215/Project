package com.amazon.avod.media.framework;

import android.content.Context;
import android.hardware.display.DisplayManager;
import android.telephony.CellInfo;
import android.telephony.CellInfoCdma;
import android.telephony.CellInfoGsm;
import android.telephony.CellInfoLte;
import android.telephony.TelephonyManager;
import android.view.Display;
import androidx.autofill.HintConstants;
import com.amazon.avod.media.framework.platform.DeviceConfiguration;
import com.amazon.avod.perf.Profiler;
import com.amazon.avod.playback.capability.DeviceIdentity;
import com.amazon.avod.threading.ScheduledExecutorBuilder;
import com.amazon.avod.util.DLog;
import com.amazon.avod.util.Preconditions2;
import com.google.common.base.Preconditions;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.ScheduledExecutorService;
import javax.annotation.Nonnull;
import javax.annotation.concurrent.NotThreadSafe;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/* JADX INFO: loaded from: classes2.dex */
@NotThreadSafe
public class MediaSystemSharedContext {
    private Context mAppContext;
    private final DeviceConfiguration mDeviceConfig;
    private DeviceIdentity mDeviceIdentity;
    private final ScheduledExecutorService mSharedExecutor;

    public MediaSystemSharedContext() {
        this(new DeviceConfiguration(), ScheduledExecutorBuilder.newBuilderFor(MediaSystemSharedContext.class, new String[0]).withFixedThreadPoolSize(8).withProfilerTraceLevel(Profiler.TraceLevel.DEBUG).build());
    }

    MediaSystemSharedContext(DeviceConfiguration deviceConfiguration, ScheduledExecutorService scheduledExecutorService) {
        this.mDeviceConfig = deviceConfiguration;
        this.mSharedExecutor = scheduledExecutorService;
    }

    public Context getAppContext() {
        Preconditions.checkState(this.mAppContext != null, "Cannot get Context until initialize() completes.");
        return this.mAppContext;
    }

    public DeviceIdentity getDeviceIdentity() {
        Preconditions.checkState(this.mDeviceIdentity != null, "Cannot get DeviceIdentity until initialize() completes.");
        return this.mDeviceIdentity;
    }

    public ExecutorService getExecutorService() {
        Preconditions.checkState(this.mAppContext != null, "Cannot get ExecutorService until initialize() completes.");
        return this.mSharedExecutor;
    }

    public DeviceConfiguration getDeviceConfiguration() {
        Preconditions.checkState(this.mAppContext != null, "Cannot get DeviceConfiguration until initialize() completes.");
        return this.mDeviceConfig;
    }

    public void initialize(@Nonnull Context context, @Nonnull DeviceIdentity deviceIdentity) {
        Preconditions.checkState(this.mAppContext == null, "Initialize should only be called once.");
        this.mAppContext = (Context) Preconditions.checkNotNull(context);
        this.mDeviceIdentity = (DeviceIdentity) Preconditions.checkNotNull(deviceIdentity, "deviceIdentity");
        this.mDeviceConfig.intialize(this.mAppContext);
    }

    @Nonnull
    public String getDisplayInfo() {
        Preconditions.checkState(this.mAppContext != null, "Cannot call getDisplayInfo() until initialize() completes.");
        JSONArray jSONArray = new JSONArray();
        try {
            for (Display display : ((DisplayManager) this.mAppContext.getSystemService("display")).getDisplays()) {
                JSONObject jSONObject = new JSONObject();
                jSONObject.put("id", display.getDisplayId());
                jSONObject.put("name", display.getName());
                jSONObject.put("flags", display.getFlags());
                jSONObject.put("refreshRate", display.getRefreshRate());
                jSONObject.put("isValid", display.isValid());
                jSONArray.put(jSONObject);
            }
        } catch (JSONException unused) {
            DLog.warnf("Unable to format JSON array at MediaSystemSharedContext.getDisplayInfo()");
        }
        return jSONArray.toString();
    }

    private boolean hasLocationPermissions() {
        return this.mAppContext.checkCallingOrSelfPermission("android.permission.ACCESS_COARSE_LOCATION") == 0;
    }

    public int getCellSignalStrength() {
        List<CellInfo> allCellInfo;
        if (!hasLocationPermissions()) {
            return -1;
        }
        try {
            TelephonyManager telephonyManager = (TelephonyManager) this.mAppContext.getSystemService(HintConstants.AUTOFILL_HINT_PHONE);
            if (telephonyManager == null || (allCellInfo = telephonyManager.getAllCellInfo()) == null) {
                return -1;
            }
            for (int i = 0; i < allCellInfo.size(); i++) {
                CellInfo cellInfo = allCellInfo.get(i);
                if (cellInfo.isRegistered()) {
                    if (cellInfo instanceof CellInfoCdma) {
                        return ((CellInfoCdma) telephonyManager.getAllCellInfo().get(0)).getCellSignalStrength().getDbm();
                    }
                    if (cellInfo instanceof CellInfoGsm) {
                        return ((CellInfoGsm) telephonyManager.getAllCellInfo().get(0)).getCellSignalStrength().getDbm();
                    }
                    if (cellInfo instanceof CellInfoLte) {
                        return ((CellInfoLte) telephonyManager.getAllCellInfo().get(0)).getCellSignalStrength().getDbm();
                    }
                }
            }
            return -1;
        } catch (SecurityException e) {
            Preconditions2.failWeakly("Attempted to get location permission but we hit a security exception: %s", e);
            return -1;
        }
    }
}
