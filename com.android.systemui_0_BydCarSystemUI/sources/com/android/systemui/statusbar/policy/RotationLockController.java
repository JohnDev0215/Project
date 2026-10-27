package com.android.systemui.statusbar.policy;

/* JADX INFO: loaded from: classes.dex */
public interface RotationLockController extends Listenable, CallbackController<RotationLockControllerCallback> {

    public interface RotationLockControllerCallback {
        void onRotationLockStateChanged(boolean rotationLocked, boolean affordanceVisible);
    }

    int getRotationLockOrientation();

    boolean isRotationLockAffordanceVisible();

    boolean isRotationLocked();

    void setRotationLocked(boolean locked);

    void setRotationLockedAtAngle(boolean locked, int rotation);
}
