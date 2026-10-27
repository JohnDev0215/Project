package com.ts.lib.car.hardware.rearviewmirror;

import com.ts.lib.car.CarNotConnectedException;

/* JADX INFO: loaded from: classes2.dex */
public interface CarVendorRearviewmirrorManager {
    public static final int ID_AUTO_EXTERNAL_REAR_MIRROR_FOLDED_STATER = 557858049;
    public static final int ID_AUTO_EXTERNAL_REAR_MIRROR_FOLD_ACTION_STATER = 557858048;
    public static final int ID_AUTO_EXTERNAL_REAR_MIRROR_FOLD_STATES = 557858050;
    public static final String PERSIST_DEBUG_LOGGER = "persist.car.vendor.log.rearviewmirror";
    public static final String TAG = "CAR.VENDOR.REARVIEWMIRROR";

    public interface OnCarRearviewmirrorEventListener {
        void onRearviewmirrorErrorEvent(int i, int i2);

        void onRearviewmirrorEventChange(RearviewmirrorEventChangeValue rearviewmirrorEventChangeValue);
    }

    void addCarRearviewmirrorEventListener(OnCarRearviewmirrorEventListener onCarRearviewmirrorEventListener) throws CarNotConnectedException;

    int getAutoExternalRearMirrorFoldActionStateR() throws CarNotConnectedException;

    int getAutoExternalRearMirrorFoldedStateR() throws CarNotConnectedException;

    void removeCarRearviewmirrorEventListener(OnCarRearviewmirrorEventListener onCarRearviewmirrorEventListener) throws CarNotConnectedException;

    void setAutoExternalRearMirrorFoldStateS(int i) throws CarNotConnectedException;
}
