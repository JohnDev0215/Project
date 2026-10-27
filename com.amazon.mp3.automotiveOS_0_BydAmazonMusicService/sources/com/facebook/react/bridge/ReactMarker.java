package com.facebook.react.bridge;

import android.os.SystemClock;
import androidx.annotation.Nullable;
import com.facebook.proguard.annotations.DoNotStrip;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

/* JADX INFO: loaded from: classes6.dex */
@DoNotStrip
public class ReactMarker {
    private static final List<MarkerListener> sListeners = new CopyOnWriteArrayList();
    private static final List<FabricMarkerListener> sFabricMarkerListeners = new CopyOnWriteArrayList();

    public interface FabricMarkerListener {
        void logFabricMarker(ReactMarkerConstants reactMarkerConstants, @Nullable String str, int i, long j);
    }

    public interface MarkerListener {
        void logMarker(ReactMarkerConstants reactMarkerConstants, @Nullable String str, int i);
    }

    @DoNotStrip
    public static void addListener(MarkerListener markerListener) {
        List<MarkerListener> list = sListeners;
        if (list.contains(markerListener)) {
            return;
        }
        list.add(markerListener);
    }

    @DoNotStrip
    public static void removeListener(MarkerListener markerListener) {
        sListeners.remove(markerListener);
    }

    @DoNotStrip
    public static void clearMarkerListeners() {
        sListeners.clear();
    }

    @DoNotStrip
    public static void addFabricListener(FabricMarkerListener fabricMarkerListener) {
        List<FabricMarkerListener> list = sFabricMarkerListeners;
        if (list.contains(fabricMarkerListener)) {
            return;
        }
        list.add(fabricMarkerListener);
    }

    @DoNotStrip
    public static void removeFabricListener(FabricMarkerListener fabricMarkerListener) {
        sFabricMarkerListeners.remove(fabricMarkerListener);
    }

    @DoNotStrip
    public static void clearFabricMarkerListeners() {
        sFabricMarkerListeners.clear();
    }

    @DoNotStrip
    public static void logFabricMarker(ReactMarkerConstants reactMarkerConstants, @Nullable String str, int i, long j) {
        Iterator<FabricMarkerListener> it = sFabricMarkerListeners.iterator();
        while (it.hasNext()) {
            it.next().logFabricMarker(reactMarkerConstants, str, i, j);
        }
    }

    @DoNotStrip
    public static void logFabricMarker(ReactMarkerConstants reactMarkerConstants, @Nullable String str, int i) {
        logFabricMarker(reactMarkerConstants, str, i, SystemClock.uptimeMillis());
    }

    @DoNotStrip
    public static void logMarker(String str) {
        logMarker(str, (String) null);
    }

    @DoNotStrip
    public static void logMarker(String str, int i) {
        logMarker(str, (String) null, i);
    }

    @DoNotStrip
    public static void logMarker(String str, @Nullable String str2) {
        logMarker(str, str2, 0);
    }

    @DoNotStrip
    public static void logMarker(String str, @Nullable String str2, int i) {
        logMarker(ReactMarkerConstants.valueOf(str), str2, i);
    }

    @DoNotStrip
    public static void logMarker(ReactMarkerConstants reactMarkerConstants) {
        logMarker(reactMarkerConstants, (String) null, 0);
    }

    @DoNotStrip
    public static void logMarker(ReactMarkerConstants reactMarkerConstants, int i) {
        logMarker(reactMarkerConstants, (String) null, i);
    }

    @DoNotStrip
    public static void logMarker(ReactMarkerConstants reactMarkerConstants, @Nullable String str) {
        logMarker(reactMarkerConstants, str, 0);
    }

    @DoNotStrip
    public static void logMarker(ReactMarkerConstants reactMarkerConstants, @Nullable String str, int i) {
        logFabricMarker(reactMarkerConstants, str, i);
        Iterator<MarkerListener> it = sListeners.iterator();
        while (it.hasNext()) {
            it.next().logMarker(reactMarkerConstants, str, i);
        }
    }
}
