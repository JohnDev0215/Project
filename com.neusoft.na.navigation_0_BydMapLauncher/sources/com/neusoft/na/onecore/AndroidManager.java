package com.neusoft.na.onecore;

import android.location.Location;
import android.util.Log;
import android.view.Surface;
import java.util.HashMap;
import java.util.Map;

/* JADX INFO: loaded from: classes2.dex */
public final class AndroidManager {
    public static final String LOGTAG = "AndroidManager";
    private final long mNativeInstance;
    private Map<String, Surface> mSurfaces = new HashMap();

    private native void notifySurfaceSizeChanged(long j);

    private native void setPosition(long j, long j2, double d, double d2, Double d3, Float f, Float f2);

    private native void setPosition(long j, long j2, double d, double d2, Double d3, Float f, Float f2, int i);

    private native void setSurface(long j, String str, Surface surface);

    private native void setVehicleSpeed(long j, long j2, double d);

    AndroidManager(long j) {
        this.mNativeInstance = j;
    }

    public void setSurfaces(Map<String, Surface> map) {
        for (Map.Entry<String, Surface> entry : map.entrySet()) {
            String key = entry.getKey();
            Surface value = entry.getValue();
            if (value != this.mSurfaces.get(key)) {
                Log.i("AndroidManager", "Setting surface: " + value);
                setSurface(this.mNativeInstance, key, value);
                this.mSurfaces.put(key, value);
            } else {
                Log.d("AndroidManager", "Ignoring setting of identical surface for window '" + key + "': " + value);
            }
        }
    }

    @Deprecated
    public void setSurface(Surface surface) {
        HashMap map = new HashMap();
        map.put("main", surface);
        setSurfaces(map);
    }

    public void setPosition(long j, double d, double d2, Double d3, Float f, Float f2) {
        setPosition(this.mNativeInstance, j, d, d2, d3, f, f2);
    }

    public void setPosition(long j, double d, double d2, Double d3, Float f, Float f2, int i) {
        setPosition(this.mNativeInstance, j, d, d2, d3, f, f2, i);
    }

    public void setVehicleSpeed(long j, double d) {
        setVehicleSpeed(this.mNativeInstance, j, d);
    }

    public void setLocation(Location location) {
        setPosition(this.mNativeInstance, location.getTime(), location.getLongitude(), location.getLatitude(), location.hasAltitude() ? Double.valueOf(location.getAltitude()) : null, location.hasBearing() ? Float.valueOf(location.getBearing()) : null, location.hasSpeed() ? Float.valueOf(location.getSpeed()) : null);
    }

    public void notifySurfaceSizeChanged() {
        Log.i("AndroidManager", "notifySurfaceSizeChanged");
        notifySurfaceSizeChanged(this.mNativeInstance);
    }
}
