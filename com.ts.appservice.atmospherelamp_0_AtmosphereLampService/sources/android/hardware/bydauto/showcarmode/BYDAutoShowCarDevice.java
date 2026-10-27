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

    BYDAutoShowCarDevice(Context con) {
        this.mShowCarModeImpl = ShowCarModeImpl.getsInstance(con.getApplicationContext());
    }

    public static synchronized BYDAutoShowCarDevice getInstance(Context con) {
        if (sBydShowCarModel == null) {
            sBydShowCarModel = new BYDAutoShowCarDevice(con.getApplicationContext());
        }
        return sBydShowCarModel;
    }

    public int getDeviceType() {
        return this.mShowCarModeImpl.getDeviceType();
    }

    public String getSetPermission() {
        return this.mShowCarModeImpl.getSetPermission();
    }

    public String getGetPermission() {
        return this.mShowCarModeImpl.getGetPermission();
    }

    public void registerListener(IShowCarModelChangeListener listener) {
        this.mShowCarModeImpl.registerListener(listener);
    }

    public void unRegisterListener(IShowCarModelChangeListener listener) {
        this.mShowCarModeImpl.unRegisterListener(listener);
    }

    public int getShowCarModelType() {
        return this.mShowCarModeImpl.getShowCarModelType();
    }

    public int getCarEngineType() {
        return this.mShowCarModeImpl.getCarEngineType();
    }

    @Deprecated
    public void setEngineEnable(boolean allowable) {
        this.mShowCarModeImpl.setEngineEnable(allowable);
    }

    public void setShowCarModelType(int display) {
        this.mShowCarModeImpl.setShowCarModelType(display);
    }

    public BYDAutoEventValue get(int[] eventTypes, Class<?> type) {
        return this.mShowCarModeImpl.get(eventTypes, type);
    }
}
