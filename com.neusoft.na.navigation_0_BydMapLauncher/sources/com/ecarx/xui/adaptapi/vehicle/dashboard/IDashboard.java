package com.ecarx.xui.adaptapi.vehicle.dashboard;

import com.ecarx.xui.adaptapi.Tribool;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

/* JADX INFO: loaded from: classes2.dex */
public interface IDashboard {
    public static final int CAR_KEY_STATUS_ACC = 2;
    public static final int CAR_KEY_STATUS_ACC_LIMITED = 6;
    public static final int CAR_KEY_STATUS_CRANK = 3;
    public static final int CAR_KEY_STATUS_OFF = 1;
    public static final int CAR_KEY_STATUS_REMOVE = 5;
    public static final int CAR_KEY_STATUS_RUN = 4;
    public static final int CAR_KEY_STATUS_UNKNOWN = Integer.MIN_VALUE;
    public static final int ID_HANDBREAK_HINT = 1;

    @Retention(RetentionPolicy.SOURCE)
    public @interface CarKeyStatus {
    }

    @Retention(RetentionPolicy.SOURCE)
    public @interface DashboardHintID {
    }

    public interface IDashboardHintObserver {
        void onDashboardHintChanged(int i, int i2);
    }

    public interface IVehicleACCStatusObserver {
        void onVehicleACCStatusChanged(int i);
    }

    public interface IVehicleFuelLevelObserver {
        void onVehicleFuelLevelChanged(double d);
    }

    public interface IVehicleMileageObserver {
        void onMaintenanceReminder(int i);

        void onVehicleMileageChanged(IMileageInfo iMileageInfo);
    }

    public interface IVehicleSpeedObserver {
        void onVehicleSpeedChanged(double d);

        void onVehicleSpeedChanged(double d, int i);

        @Deprecated
        void onVehicleSpeedChanged(int i);
    }

    public interface IVehicleWarningObserver {
        void onVehicleWarningChanged(IWarningInfo[] iWarningInfoArr);
    }

    boolean attachDashboardHintChangeObserver(IDashboardHintObserver iDashboardHintObserver);

    boolean attachVehicleACCStatusObserver(IVehicleACCStatusObserver iVehicleACCStatusObserver);

    boolean attachVehicleFuelLevelObserver(IVehicleFuelLevelObserver iVehicleFuelLevelObserver);

    boolean attachVehicleMileageObserver(IVehicleMileageObserver iVehicleMileageObserver);

    boolean attachVehicleSpeedObserver(IVehicleSpeedObserver iVehicleSpeedObserver);

    boolean attachVehicleSpeedObserver(IVehicleSpeedObserver iVehicleSpeedObserver, double[] dArr, int i, int i2);

    boolean attachVehicleWarningObserver(IVehicleWarningObserver iVehicleWarningObserver);

    @Deprecated
    boolean clearMaintenanceWarning();

    void detachDashboardHintChangeObserver(IDashboardHintObserver iDashboardHintObserver);

    void detachVehicleACCStatusObserver(IVehicleACCStatusObserver iVehicleACCStatusObserver);

    void detachVehicleFuelLevelObserver(IVehicleFuelLevelObserver iVehicleFuelLevelObserver);

    void detachVehicleMileageObserver(IVehicleMileageObserver iVehicleMileageObserver);

    void detachVehicleSpeedObserver(IVehicleSpeedObserver iVehicleSpeedObserver);

    void detachVehicleWarningObserver(IVehicleWarningObserver iVehicleWarningObserver);

    int getAccStatus();

    double getAverageFuelConsumptionLevel();

    double getCurrentTripFuelConsumptionLevel();

    double getFuelLevel();

    double getFullFuelLevel();

    int getHandBrakeStatus();

    double getInstantaneousFuelConsumptionLevel();

    IMileageInfo getMileageInfo();

    @Deprecated
    int getMileageToNextMaintenance();

    ITpmsInfo getTpmsInfo();

    @Deprecated
    int getVehicleSpeed();

    double getVehicleSpeedInDouble();

    IWarningInfo[] getWanringInfo();

    @Deprecated
    Tribool hasMaintenanceWarning();
}
