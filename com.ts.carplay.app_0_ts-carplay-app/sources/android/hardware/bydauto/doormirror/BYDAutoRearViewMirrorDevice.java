package android.hardware.bydauto.doormirror;

import android.content.Context;
import android.hardware.bydauto.AbsBYDAutoDevice;
import android.hardware.bydauto.BYDAutoEventValue;
import android.hardware.bydauto.doormirror.tsimpl.DoormirrorManagerImpl;

/* JADX INFO: loaded from: classes.dex */
public final class BYDAutoRearViewMirrorDevice extends AbsBYDAutoDevice {
    public static final String PERMISSION_BYDAUTO_REAR_VIEW_MIRROR_COMMON = "android.permission.BYDAUTO_REAR_VIEW_MIRROR_COMMON";
    public static final String PERMISSION_BYDAUTO_REAR_VIEW_MIRROR_GET = "android.permission.BYDAUTO_REAR_VIEW_MIRROR_GET";
    public static final String PERMISSION_BYDAUTO_REAR_VIEW_MIRROR_SET = "android.permission.BYDAUTO_REAR_VIEW_MIRROR_SET";
    public static final int REAR_VIEW_MIRROR_ANTIGLARE_STATE_INVALID = 0;
    public static final int REAR_VIEW_MIRROR_ANTIGLARE_STATE_MILD = 1;
    public static final int REAR_VIEW_MIRROR_ANTIGLARE_STATE_MODERATE = 2;
    public static final int REAR_VIEW_MIRROR_ANTIGLARE_STATE_SERIOUS = 3;
    public static final int REAR_VIEW_MIRROR_COMMAND_BUSY = -2147482647;
    public static final int REAR_VIEW_MIRROR_COMMAND_FAILED = -2147482648;
    public static final int REAR_VIEW_MIRROR_COMMAND_INVALID = -2147482645;
    public static final int REAR_VIEW_MIRROR_COMMAND_SUCCESS = 0;
    public static final int REAR_VIEW_MIRROR_COMMAND_TIMEOUT = -2147482646;
    public static final int REAR_VIEW_MIRROR_FOLD = 1;
    public static final int REAR_VIEW_MIRROR_NO_ACTION = 0;
    public static final int REAR_VIEW_MIRROR_UNFOLD = 2;
    protected static final String TAG = "BYDAutoRearViewMirrorDevice";
    private static BYDAutoRearViewMirrorDevice sDevice;
    private final DoormirrorManagerImpl mDoormirrorManagerImpl;

    BYDAutoRearViewMirrorDevice(Context con) {
        super(con);
        this.mDoormirrorManagerImpl = DoormirrorManagerImpl.getInstance(con);
    }

    public static synchronized BYDAutoRearViewMirrorDevice getInstance(Context con) {
        if (sDevice == null) {
            sDevice = new BYDAutoRearViewMirrorDevice(con);
        }
        return sDevice;
    }

    @Override // android.hardware.bydauto.AbsBYDAutoDevice
    public int getDevicetype() {
        return this.mDoormirrorManagerImpl.getDevicetype();
    }

    @Override // android.hardware.bydauto.AbsBYDAutoDevice
    public String getSetPermission() {
        return PERMISSION_BYDAUTO_REAR_VIEW_MIRROR_SET;
    }

    @Override // android.hardware.bydauto.AbsBYDAutoDevice
    public String getGetPermission() {
        return PERMISSION_BYDAUTO_REAR_VIEW_MIRROR_GET;
    }

    public void registerListener(AbsBYDAutoRearViewMirrorListener listener) {
        this.mDoormirrorManagerImpl.registerListener(listener);
    }

    public void registerListener(AbsBYDAutoRearViewMirrorListener listener, int[] featureIDs) {
        this.mDoormirrorManagerImpl.registerListener(listener, featureIDs);
    }

    @Override // android.hardware.bydauto.AbsBYDAutoDevice, android.hardware.IBYDAutoDevice
    public boolean postEvent(int device_type, int event_type, int value, Object data) {
        return this.mDoormirrorManagerImpl.postEvent(device_type, event_type, value, data);
    }

    @Override // android.hardware.IBYDAutoDevice
    public int getType() {
        return this.mDoormirrorManagerImpl.getType();
    }

    @Override // android.hardware.IBYDAutoDevice
    public int[] getFeatureList() {
        return this.mDoormirrorManagerImpl.getFeatureList();
    }

    public void unregisterListener(AbsBYDAutoRearViewMirrorListener listener) {
        this.mDoormirrorManagerImpl.unregisterListener(listener);
    }

    public int getAutoExternalRearMirrorState() {
        return this.mDoormirrorManagerImpl.getAutoExternalRearMirrorState();
    }

    public int getAutoExternalRearMirrorAntiglareState() {
        return this.mDoormirrorManagerImpl.getAutoExternalRearMirrorAntiglareState();
    }

    public int getAutoExternalRearMirrorFoldActionState() {
        return this.mDoormirrorManagerImpl.getAutoExternalRearMirrorFoldActionState();
    }

    @Deprecated
    public int getAutoExternalRearMirrorFoldedState() {
        return this.mDoormirrorManagerImpl.getAutoExternalRearMirrorFoldedState();
    }

    public int setAutoExternalRearMirrorFoldState(int state) {
        return this.mDoormirrorManagerImpl.setAutoExternalRearMirrorFoldState(state);
    }

    @Override // android.hardware.bydauto.AbsBYDAutoDevice
    public BYDAutoEventValue get(int[] eventTypes, Class<?> type) {
        return this.mDoormirrorManagerImpl.get(eventTypes, type);
    }

    @Override // android.hardware.bydauto.AbsBYDAutoDevice
    public int set(int[] eventTypes, BYDAutoEventValue eventValue) {
        return this.mDoormirrorManagerImpl.set(eventTypes, eventValue);
    }
}
