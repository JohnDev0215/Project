package com.ecarx.xui.adaptapi.vehicle;

import android.content.Context;
import com.ecarx.xui.adaptapi.AdaptAPI;
import com.ecarx.xui.adaptapi.FunctionStatus;
import com.ecarx.xui.adaptapi.sign.SignVerify;
import com.ecarx.xui.adaptapi.vehicle.appearance.IAppearance;
import com.ecarx.xui.adaptapi.vehicle.bcm.IBcm;
import com.ecarx.xui.adaptapi.vehicle.dashboard.IDashboard;
import com.ecarx.xui.adaptapi.vehicle.ems.IDrivingMode;
import com.ecarx.xui.adaptapi.vehicle.ems.IEms;
import com.ecarx.xui.adaptapi.vehicle.impl.VehicleImpl;
import com.ecarx.xui.adaptapi.vehicle.kidsmode.IKidsMode;
import com.ecarx.xui.adaptapi.vehicle.newenergy.INewEnergy;
import com.ecarx.xui.adaptapi.vehicle.ota.IOta;
import com.ecarx.xui.adaptapi.vehicle.pdc.IPDC;
import com.ecarx.xui.adaptapi.vehicle.power.IPowerManager;
import com.ecarx.xui.adaptapi.vehicle.tcu.ITcu;

/* JADX INFO: loaded from: classes2.dex */
public abstract class Vehicle extends AdaptAPI {
    public abstract IAppearance getAppearance();

    public abstract IBcm getBcm();

    public abstract IDashboard getDashboard();

    public abstract IDrivingMode getDrivingMode();

    public abstract IEms getEngine();

    public abstract IKidsMode getKidsMode();

    public abstract INewEnergy getNewEnergy();

    public abstract IOta getOta();

    public abstract IPDC getPDC();

    public abstract IPowerManager getPowerManager();

    public abstract ISettings getSettings();

    public abstract ITcu getTcu();

    public abstract FunctionStatus isEleSeatSupported(int i);

    public static Vehicle create(Context context) {
        if (SignVerify.instance().appVerify(context)) {
            return new VehicleImpl(context);
        }
        return null;
    }
}
