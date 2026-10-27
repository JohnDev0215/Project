package com.ecarx.xui.adaptapi.vehicle.impl;

import android.content.Context;
import com.ecarx.xui.adaptapi.FunctionStatus;
import com.ecarx.xui.adaptapi.vehicle.ISettings;
import com.ecarx.xui.adaptapi.vehicle.SettingImpl;
import com.ecarx.xui.adaptapi.vehicle.Vehicle;
import com.ecarx.xui.adaptapi.vehicle.appearance.AppearanceImpl;
import com.ecarx.xui.adaptapi.vehicle.appearance.IAppearance;
import com.ecarx.xui.adaptapi.vehicle.bcm.BcmImpl;
import com.ecarx.xui.adaptapi.vehicle.bcm.IBcm;
import com.ecarx.xui.adaptapi.vehicle.dashboard.Dashboard;
import com.ecarx.xui.adaptapi.vehicle.dashboard.IDashboard;
import com.ecarx.xui.adaptapi.vehicle.ems.DrivingModeImpl;
import com.ecarx.xui.adaptapi.vehicle.ems.Ems;
import com.ecarx.xui.adaptapi.vehicle.ems.IDrivingMode;
import com.ecarx.xui.adaptapi.vehicle.ems.IEms;
import com.ecarx.xui.adaptapi.vehicle.kidsmode.IKidsMode;
import com.ecarx.xui.adaptapi.vehicle.kidsmode.KidsModeImpl;
import com.ecarx.xui.adaptapi.vehicle.newenergy.INewEnergy;
import com.ecarx.xui.adaptapi.vehicle.newenergy.NewEnergy;
import com.ecarx.xui.adaptapi.vehicle.ota.IOta;
import com.ecarx.xui.adaptapi.vehicle.ota.OtaImpl;
import com.ecarx.xui.adaptapi.vehicle.pdc.IPDC;
import com.ecarx.xui.adaptapi.vehicle.pdc.PDCImpl;
import com.ecarx.xui.adaptapi.vehicle.power.IPowerManager;
import com.ecarx.xui.adaptapi.vehicle.power.PowerManager;
import com.ecarx.xui.adaptapi.vehicle.tcu.ITcu;
import com.ecarx.xui.adaptapi.vehicle.tcu.TcuImpl;
import com.neusoft.optimus.utility.log.LogTool;

/* JADX INFO: loaded from: classes2.dex */
public class VehicleImpl extends Vehicle {
    private Context context;
    private Dashboard dashboard;
    private Ems ems;
    private BcmImpl mBcm;
    private NewEnergy mEnergy;
    private KidsModeImpl mKidsModeImpl;
    private OtaImpl mOta;

    public VehicleImpl(Context context) {
        this.context = context;
    }

    @Override // com.ecarx.xui.adaptapi.vehicle.Vehicle
    public IBcm getBcm() {
        if (this.mBcm == null) {
            this.mBcm = new BcmImpl(this.context);
        }
        return this.mBcm;
    }

    @Override // com.ecarx.xui.adaptapi.vehicle.Vehicle
    public IEms getEngine() {
        LogTool.d("Ems", "getEngine");
        if (this.ems == null) {
            this.ems = new Ems(this.context);
        }
        return this.ems;
    }

    @Override // com.ecarx.xui.adaptapi.vehicle.Vehicle
    public IDashboard getDashboard() {
        if (this.dashboard == null) {
            this.dashboard = new Dashboard();
        }
        return this.dashboard;
    }

    @Override // com.ecarx.xui.adaptapi.vehicle.Vehicle
    public IOta getOta() {
        OtaImpl otaImpl = new OtaImpl(this.context);
        this.mOta = otaImpl;
        return otaImpl.getIOta();
    }

    @Override // com.ecarx.xui.adaptapi.vehicle.Vehicle
    public IKidsMode getKidsMode() {
        KidsModeImpl kidsModeImpl = new KidsModeImpl(this.context);
        this.mKidsModeImpl = kidsModeImpl;
        return kidsModeImpl;
    }

    @Override // com.ecarx.xui.adaptapi.vehicle.Vehicle
    public ISettings getSettings() {
        return new SettingImpl(this.context);
    }

    @Override // com.ecarx.xui.adaptapi.vehicle.Vehicle
    public ITcu getTcu() {
        return new TcuImpl(this.context);
    }

    @Override // com.ecarx.xui.adaptapi.vehicle.Vehicle
    public IPowerManager getPowerManager() {
        return new PowerManager();
    }

    @Override // com.ecarx.xui.adaptapi.vehicle.Vehicle
    public INewEnergy getNewEnergy() {
        if (this.mEnergy == null) {
            this.mEnergy = new NewEnergy(this.context);
        }
        return this.mEnergy;
    }

    @Override // com.ecarx.xui.adaptapi.vehicle.Vehicle
    public IAppearance getAppearance() {
        return new AppearanceImpl();
    }

    @Override // com.ecarx.xui.adaptapi.vehicle.Vehicle
    public IDrivingMode getDrivingMode() {
        return new DrivingModeImpl();
    }

    @Override // com.ecarx.xui.adaptapi.vehicle.Vehicle
    public IPDC getPDC() {
        return new PDCImpl();
    }

    @Override // com.ecarx.xui.adaptapi.vehicle.Vehicle
    public FunctionStatus isEleSeatSupported(int i) {
        return FunctionStatus.notavailable;
    }
}
