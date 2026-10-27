package com.byd.dilink.app.am;

import android.content.ComponentName;
import android.graphics.ColorSpace;
import android.graphics.Rect;
import android.hardware.HardwareBuffer;

/* JADX INFO: loaded from: classes.dex */
public interface IDiLinkTaskSnapShot {
    int getAppearance();

    ColorSpace getColorSpace();

    HardwareBuffer getHardwareBuffer();

    long getId();

    Rect getLetterboxInsets();

    int getRotation();

    IDiLinkGraphicBuffer getSnapshot();

    ComponentName getTopActivityComponent();

    int getWindowingMode();

    boolean hasImeSurface();

    boolean isTranslucent();
}
