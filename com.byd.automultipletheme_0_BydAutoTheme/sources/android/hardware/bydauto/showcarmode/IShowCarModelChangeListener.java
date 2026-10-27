package android.hardware.bydauto.showcarmode;

import android.hardware.IBYDAutoEvent;
import android.hardware.IBYDAutoListener;
import android.hardware.bydauto.BYDAutoEventValue;

/* JADX INFO: loaded from: classes.dex */
public interface IShowCarModelChangeListener extends IBYDAutoListener {
    public static final String TAG = "IShowCarModelChangeListener";

    void onAirCtrlChangeEvent(int i);

    void onAirCtrlPressEvent(int i);

    @Override // android.hardware.IBYDAutoListener
    void onDataChanged(IBYDAutoEvent iBYDAutoEvent);

    @Override // android.hardware.IBYDAutoListener
    void onDataEventChanged(int i, BYDAutoEventValue bYDAutoEventValue);

    @Override // android.hardware.IBYDAutoListener
    void onError(int i, String str);

    @Deprecated
    void showCarGearChange(int i);

    void showCarModelChange(int i);

    /* JADX INFO: renamed from: android.hardware.bydauto.showcarmode.IShowCarModelChangeListener$-CC, reason: invalid class name */
    public final /* synthetic */ class CC {
        public static void $default$onDataChanged(IShowCarModelChangeListener _this, IBYDAutoEvent iBYDAutoEvent) {
        }

        public static void $default$onDataEventChanged(IShowCarModelChangeListener _this, int i, BYDAutoEventValue bYDAutoEventValue) {
        }

        public static void $default$onError(IShowCarModelChangeListener _this, int i, String str) {
        }
    }
}
