package com.ecarx.xui.adaptapi.vehicle.bcm;

import android.content.Context;
import android.util.Log;
import com.ecarx.xui.adaptapi.FunctionStatus;
import com.ecarx.xui.adaptapi.Tribool;
import com.ecarx.xui.adaptapi.vehicle.bcm.IBcm;
import com.ecarx.xui.adaptapi.vehicle.bcm.IWasher;

/* JADX INFO: loaded from: classes2.dex */
public class BcmImpl implements IBcm {
    public static final String TAG = "BcmImpl";
    private Context mContext;
    private DoorAndWindowImpl mDoorAndWindow;
    private LightingImpl mLighting;

    @Override // com.ecarx.xui.adaptapi.vehicle.bcm.IBcm
    public void attachWiperStatusListener(IBcm.WiperStatusListener wiperStatusListener) {
    }

    @Override // com.ecarx.xui.adaptapi.vehicle.bcm.IBcm
    public void detachWiperStatusListener(IBcm.WiperStatusListener wiperStatusListener) {
    }

    @Override // com.ecarx.xui.adaptapi.vehicle.bcm.IBcm
    public int getWiperGear(int i) {
        return 0;
    }

    @Override // com.ecarx.xui.adaptapi.vehicle.bcm.IBcm
    public int getWiperStatus(int i) {
        return 0;
    }

    @Override // com.ecarx.xui.adaptapi.vehicle.bcm.IBcm
    public FunctionStatus isWiperOperationSupported(int i, int i2) {
        return null;
    }

    @Override // com.ecarx.xui.adaptapi.vehicle.bcm.IBcm
    public boolean setWiperGear(int i, int i2) {
        return false;
    }

    public BcmImpl(Context context) {
        this.mContext = context;
    }

    @Override // com.ecarx.xui.adaptapi.vehicle.bcm.IBcm
    public ILighting getLightingState() {
        if (this.mLighting == null) {
            this.mLighting = new LightingImpl(this.mContext);
        }
        Log.d(TAG, "getLightingState");
        return this.mLighting;
    }

    @Override // com.ecarx.xui.adaptapi.vehicle.bcm.IBcm
    public IDoorAndWindow getDoorAndWindowState() {
        if (this.mDoorAndWindow == null && this.mContext != null) {
            this.mDoorAndWindow = new DoorAndWindowImpl(this.mContext);
        }
        return this.mDoorAndWindow;
    }

    @Override // com.ecarx.xui.adaptapi.vehicle.bcm.IBcm
    public IWasher getWasherState() {
        return new Washer();
    }

    class Washer implements IWasher {
        @Override // com.ecarx.xui.adaptapi.vehicle.bcm.IWasher
        public void attachWasherStatusListener(IWasher.WasherStatusListener washerStatusListener, int[] iArr) {
        }

        @Override // com.ecarx.xui.adaptapi.vehicle.bcm.IWasher
        public void detachWasherStatusListener(IWasher.WasherStatusListener washerStatusListener) {
        }

        @Override // com.ecarx.xui.adaptapi.vehicle.bcm.IWasher
        public boolean setWasherOnOff(int i, boolean z) {
            return false;
        }

        Washer() {
        }

        @Override // com.ecarx.xui.adaptapi.vehicle.bcm.IWasher
        public Tribool isWasherOpened(int i) {
            return Tribool.indeterminate;
        }
    }
}
