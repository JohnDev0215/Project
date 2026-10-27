package android.hardware.bydauto.showcarmode;

import android.content.Context;
import android.hardware.bydauto.BYDAutoEventValue;
import android.hardware.bydauto.showcarmode.tsimpl.ShowCarModeImpl;

/* JADX INFO: loaded from: classes.dex */
public class BYDAutoShowCarDevice {
    public static final int DISPLAY_EXIT = 3;
    public static final int DISPLAY_INNER_MODEL = 1;
    public static final int DISPLAY_INVALID = 0;
    public static final int DISPLAY_OUTER_MODEL = 2;
    private static BYDAutoShowCarDevice sBydShowCarModel;
    private final ShowCarModeImpl mShowCarModeImpl;

    public BYDAutoShowCarDevice(Context context) {
        this.mShowCarModeImpl = ShowCarModeImpl.getsInstance(context.getApplicationContext());
    }

    public static synchronized BYDAutoShowCarDevice getInstance(Context context) {
        if (sBydShowCarModel == null) {
            sBydShowCarModel = new BYDAutoShowCarDevice(context.getApplicationContext());
        }
        return sBydShowCarModel;
    }

    public BYDAutoEventValue get(int[] iArr, Class<?> cls) {
        return this.mShowCarModeImpl.get(iArr, cls);
    }

    public int getCarEngineType() {
        return this.mShowCarModeImpl.getCarEngineType();
    }

    public int getDeviceType() {
        return this.mShowCarModeImpl.getDeviceType();
    }

    public String getGetPermission() {
        return this.mShowCarModeImpl.getGetPermission();
    }

    public String getSetPermission() {
        return this.mShowCarModeImpl.getSetPermission();
    }

    public int getShowCarModelType() {
        return this.mShowCarModeImpl.getShowCarModelType();
    }

    public void registerListener(IShowCarModelChangeListener iShowCarModelChangeListener) {
        this.mShowCarModeImpl.registerListener(iShowCarModelChangeListener);
    }

    @Deprecated
    public void setEngineEnable(boolean z) {
        this.mShowCarModeImpl.setEngineEnable(z);
    }

    public void setShowCarModelType(int i) {
        this.mShowCarModeImpl.setShowCarModelType(i);
    }

    public void unRegisterListener(IShowCarModelChangeListener iShowCarModelChangeListener) {
        this.mShowCarModeImpl.unRegisterListener(iShowCarModelChangeListener);
    }
}
