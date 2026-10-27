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
    public static final String TAG = "BYDAutoRearViewMirrorDevice";
    private static BYDAutoRearViewMirrorDevice sDevice;
    private final DoormirrorManagerImpl mDoormirrorManagerImpl;

    public BYDAutoRearViewMirrorDevice(Context context) {
        super(context);
        this.mDoormirrorManagerImpl = DoormirrorManagerImpl.getInstance(context);
    }

    public static synchronized BYDAutoRearViewMirrorDevice getInstance(Context context) {
        if (sDevice == null) {
            sDevice = new BYDAutoRearViewMirrorDevice(context);
        }
        return sDevice;
    }

    @Override // android.hardware.bydauto.AbsBYDAutoDevice
    public BYDAutoEventValue get(int[] iArr, Class<?> cls) {
        return this.mDoormirrorManagerImpl.get(iArr, cls);
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

    public int getAutoExternalRearMirrorState() {
        return this.mDoormirrorManagerImpl.getAutoExternalRearMirrorState();
    }

    @Override // android.hardware.bydauto.AbsBYDAutoDevice
    public int getDevicetype() {
        return this.mDoormirrorManagerImpl.getDevicetype();
    }

    @Override // android.hardware.bydauto.AbsBYDAutoDevice
    public int[] getFeatureList() {
        return this.mDoormirrorManagerImpl.getFeatureList();
    }

    @Override // android.hardware.bydauto.AbsBYDAutoDevice
    public String getGetPermission() {
        return PERMISSION_BYDAUTO_REAR_VIEW_MIRROR_GET;
    }

    @Override // android.hardware.bydauto.AbsBYDAutoDevice
    public String getSetPermission() {
        return PERMISSION_BYDAUTO_REAR_VIEW_MIRROR_SET;
    }

    @Override // android.hardware.bydauto.AbsBYDAutoDevice
    public int getType() {
        return this.mDoormirrorManagerImpl.getType();
    }

    @Override // android.hardware.bydauto.AbsBYDAutoDevice
    public boolean postEvent(int i3, int i4, int i5, Object obj) {
        return this.mDoormirrorManagerImpl.postEvent(i3, i4, i5, obj);
    }

    public void registerListener(AbsBYDAutoRearViewMirrorListener absBYDAutoRearViewMirrorListener) {
        this.mDoormirrorManagerImpl.registerListener(absBYDAutoRearViewMirrorListener);
    }

    @Override // android.hardware.bydauto.AbsBYDAutoDevice
    public int set(int[] iArr, BYDAutoEventValue bYDAutoEventValue) {
        return this.mDoormirrorManagerImpl.set(iArr, bYDAutoEventValue);
    }

    public int setAutoExternalRearMirrorFoldState(int i3) {
        return this.mDoormirrorManagerImpl.setAutoExternalRearMirrorFoldState(i3);
    }

    public void unregisterListener(AbsBYDAutoRearViewMirrorListener absBYDAutoRearViewMirrorListener) {
        this.mDoormirrorManagerImpl.unregisterListener(absBYDAutoRearViewMirrorListener);
    }

    public void registerListener(AbsBYDAutoRearViewMirrorListener absBYDAutoRearViewMirrorListener, int[] iArr) {
        this.mDoormirrorManagerImpl.registerListener(absBYDAutoRearViewMirrorListener, iArr);
    }
}
