package org.chromium.content.browser;

import android.app.Activity;
import android.content.pm.PackageManager;
import org.chromium.base.ApplicationStatus;
import org.chromium.base.Log;
import org.chromium.base.ThreadUtils;
import org.chromium.base.annotations.JNINamespace;
import org.chromium.content_public.common.ScreenOrientationConstants;
import org.chromium.ui.gfx.DeviceDisplayInfo;

/* JADX INFO: loaded from: classes3.dex */
@JNINamespace("content")
public class ScreenOrientationProvider {
    private static final String TAG = "cr.ScreenOrientation";

    private static int getOrientationFromWebScreenOrientations(byte b, Activity activity) {
        switch (b) {
            case 0:
                return -1;
            case 1:
                return 1;
            case 2:
                return 9;
            case 3:
                return 0;
            case 4:
                return 8;
            case 5:
                return 10;
            case 6:
                return 6;
            case 7:
                return 7;
            case 8:
                DeviceDisplayInfo deviceDisplayInfoCreate = DeviceDisplayInfo.create(activity);
                int rotationDegrees = deviceDisplayInfoCreate.getRotationDegrees();
                return (rotationDegrees == 0 || rotationDegrees == 180) ? deviceDisplayInfoCreate.getDisplayHeight() >= deviceDisplayInfoCreate.getDisplayWidth() ? 1 : 0 : deviceDisplayInfoCreate.getDisplayHeight() < deviceDisplayInfoCreate.getDisplayWidth() ? 1 : 0;
            default:
                Log.w(TAG, "Trying to lock to unsupported orientation!", new Object[0]);
                return -1;
        }
    }

    static void lockOrientation(byte b) {
        lockOrientation(b, ApplicationStatus.getLastTrackedFocusedActivity());
    }

    public static void lockOrientation(byte b, Activity activity) {
        int orientationFromWebScreenOrientations;
        if (activity == null || (orientationFromWebScreenOrientations = getOrientationFromWebScreenOrientations(b, activity)) == -1) {
            return;
        }
        activity.setRequestedOrientation(orientationFromWebScreenOrientations);
    }

    static void unlockOrientation() {
        Activity lastTrackedFocusedActivity = ApplicationStatus.getLastTrackedFocusedActivity();
        if (lastTrackedFocusedActivity == null) {
            return;
        }
        int orientationFromWebScreenOrientations = getOrientationFromWebScreenOrientations((byte) lastTrackedFocusedActivity.getIntent().getIntExtra(ScreenOrientationConstants.EXTRA_ORIENTATION, 0), lastTrackedFocusedActivity);
        if (orientationFromWebScreenOrientations == -1) {
            try {
                orientationFromWebScreenOrientations = lastTrackedFocusedActivity.getPackageManager().getActivityInfo(lastTrackedFocusedActivity.getComponentName(), 128).screenOrientation;
            } catch (PackageManager.NameNotFoundException unused) {
            } catch (Throwable th) {
                lastTrackedFocusedActivity.setRequestedOrientation(orientationFromWebScreenOrientations);
                throw th;
            }
        }
        lastTrackedFocusedActivity.setRequestedOrientation(orientationFromWebScreenOrientations);
    }

    static void startAccurateListening() {
        ThreadUtils.runOnUiThread(new Runnable() { // from class: org.chromium.content.browser.ScreenOrientationProvider.1
            @Override // java.lang.Runnable
            public void run() {
                ScreenOrientationListener.getInstance().startAccurateListening();
            }
        });
    }

    static void stopAccurateListening() {
        ThreadUtils.runOnUiThread(new Runnable() { // from class: org.chromium.content.browser.ScreenOrientationProvider.2
            @Override // java.lang.Runnable
            public void run() {
                ScreenOrientationListener.getInstance().stopAccurateListening();
            }
        });
    }

    private ScreenOrientationProvider() {
    }
}
