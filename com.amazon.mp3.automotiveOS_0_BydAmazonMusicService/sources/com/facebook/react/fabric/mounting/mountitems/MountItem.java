package com.facebook.react.fabric.mounting.mountitems;

import androidx.annotation.AnyThread;
import androidx.annotation.NonNull;
import androidx.annotation.UiThread;
import com.facebook.proguard.annotations.DoNotStrip;
import com.facebook.react.fabric.mounting.MountingManager;

/* JADX INFO: loaded from: classes6.dex */
@DoNotStrip
public interface MountItem {
    @UiThread
    void execute(@NonNull MountingManager mountingManager);

    @AnyThread
    int getSurfaceId();
}
