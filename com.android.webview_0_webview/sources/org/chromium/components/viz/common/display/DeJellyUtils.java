package org.chromium.components.viz.common.display;

import android.content.ComponentCallbacks;
import android.content.res.Configuration;
import android.graphics.Point;
import android.hardware.display.DisplayManager;
import android.provider.Settings;
import android.view.Display;
import defpackage.Is;
import java.lang.reflect.Field;

/* JADX INFO: compiled from: chromium-SystemWebView.apk-default-410412053 */
/* JADX INFO: loaded from: classes.dex */
public class DeJellyUtils implements DisplayManager.DisplayListener, ComponentCallbacks {
    public static DeJellyUtils x;
    public boolean s;
    public boolean t;
    public float u;
    public DisplayManager v;
    public Field w;

    public DeJellyUtils() {
        DisplayManager displayManager = (DisplayManager) Is.a.getSystemService("display");
        this.v = displayManager;
        displayManager.registerDisplayListener(this, null);
        Is.a.registerComponentCallbacks(this);
        onDisplayChanged(0);
        onConfigurationChanged(Is.a.getResources().getConfiguration());
        try {
            this.w = Configuration.class.getDeclaredField("semDisplayDeviceType");
        } catch (Exception unused) {
        }
    }

    public static float screenWidth() {
        if (x == null) {
            x = new DeJellyUtils();
        }
        return x.u;
    }

    public static boolean useDeJelly() {
        if (x == null) {
            x = new DeJellyUtils();
        }
        DeJellyUtils deJellyUtils = x;
        return Settings.Global.getInt(Is.a.getContentResolver(), "sem_support_scroll_filter", 1) != 0 && deJellyUtils.s && deJellyUtils.t;
    }

    @Override // android.content.ComponentCallbacks
    public void onConfigurationChanged(Configuration configuration) {
        Field field = this.w;
        if (field != null) {
            try {
                this.t = field.getInt(configuration) != 5;
                return;
            } catch (Exception unused) {
            }
        }
        this.t = true;
    }

    @Override // android.hardware.display.DisplayManager.DisplayListener
    public void onDisplayAdded(int i) {
    }

    @Override // android.hardware.display.DisplayManager.DisplayListener
    public void onDisplayChanged(int i) {
        if (i != 0) {
            return;
        }
        Display display = this.v.getDisplay(i);
        this.s = display.getRotation() == 0;
        display.getRealSize(new Point());
        this.u = r0.x;
    }

    @Override // android.hardware.display.DisplayManager.DisplayListener
    public void onDisplayRemoved(int i) {
    }

    @Override // android.content.ComponentCallbacks
    public void onLowMemory() {
    }
}
