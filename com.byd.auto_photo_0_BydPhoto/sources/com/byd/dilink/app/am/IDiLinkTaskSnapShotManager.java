package com.byd.dilink.app.am;

import android.content.ComponentName;
import android.graphics.ColorSpace;
import android.graphics.Point;
import android.graphics.Rect;
import android.hardware.HardwareBuffer;

/* JADX INFO: loaded from: classes.dex */
public interface IDiLinkTaskSnapShotManager {
    public static final String TAG = "IDiLinkTaskSnapShotMana";

    IDiLinkTaskSnapShot create(long j, ComponentName componentName, HardwareBuffer hardwareBuffer, ColorSpace colorSpace, int i, int i2, Point point, Rect rect, Rect rect2, boolean z, boolean z2, int i3, int i4, boolean z3, boolean z4, boolean z5);

    IDiLinkTaskSnapShot create(ComponentName componentName, IDiLinkGraphicBuffer iDiLinkGraphicBuffer, ColorSpace colorSpace, int i, Rect rect, boolean z, float f, boolean z2, int i2, int i3, boolean z3);
}
