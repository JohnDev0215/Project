package com.android.systemui.qs.tileimpl;

import android.content.Context;
import android.os.SystemProperties;
import android.text.TextUtils;
import com.android.systemui.Constants;
import com.android.systemui.R;
import com.android.systemui.qs.QSIconConstants;
import com.android.systemui.qs.tiles.BluetoothTile;
import com.android.systemui.qs.tiles.CellularTile;
import com.android.systemui.qs.tiles.DeviceAttachmentTile;
import com.android.systemui.qs.tiles.MuteTile;
import com.android.systemui.qs.tiles.PrivacyTile;
import com.android.systemui.qs.tiles.RotationLockTile;
import com.android.systemui.qs.tiles.SceneCommonTile;
import com.android.systemui.qs.tiles.ScreenOffTile;
import com.android.systemui.qs.tiles.ScreenshotTile;
import com.android.systemui.qs.tiles.UiModeNightTile;
import com.android.systemui.qs.tiles.WifiTile;
import com.android.systemui.qs.tiles.carcontrol.ActiveSuspensionMode;
import com.android.systemui.qs.tiles.carcontrol.Audio;
import com.android.systemui.qs.tiles.carcontrol.AutoHold;
import com.android.systemui.qs.tiles.carcontrol.AutoParking;
import com.android.systemui.qs.tiles.carcontrol.Avas;
import com.android.systemui.qs.tiles.carcontrol.Bsd;
import com.android.systemui.qs.tiles.carcontrol.Cpd;
import com.android.systemui.qs.tiles.carcontrol.Defrosting;
import com.android.systemui.qs.tiles.carcontrol.DiTrainerMode;
import com.android.systemui.qs.tiles.carcontrol.DoorCtrl;
import com.android.systemui.qs.tiles.carcontrol.DriverMonitor;
import com.android.systemui.qs.tiles.carcontrol.Dvr;
import com.android.systemui.qs.tiles.carcontrol.ElectronicParkingBrake;
import com.android.systemui.qs.tiles.carcontrol.EnergyFeedbackIntensity;
import com.android.systemui.qs.tiles.carcontrol.EspOff;
import com.android.systemui.qs.tiles.carcontrol.FrPassengerGreeting;
import com.android.systemui.qs.tiles.carcontrol.GamesCenter;
import com.android.systemui.qs.tiles.carcontrol.HillDescentControl;
import com.android.systemui.qs.tiles.carcontrol.Hma;
import com.android.systemui.qs.tiles.carcontrol.Hud;
import com.android.systemui.qs.tiles.carcontrol.Icc;
import com.android.systemui.qs.tiles.carcontrol.InteriorLightOff;
import com.android.systemui.qs.tiles.carcontrol.InteriorLightOn;
import com.android.systemui.qs.tiles.carcontrol.Karaoke;
import com.android.systemui.qs.tiles.carcontrol.LDA;
import com.android.systemui.qs.tiles.carcontrol.LeftChildLock;
import com.android.systemui.qs.tiles.carcontrol.Massage;
import com.android.systemui.qs.tiles.carcontrol.MoonRoof;
import com.android.systemui.qs.tiles.carcontrol.NewEnergy;
import com.android.systemui.qs.tiles.carcontrol.RearControlLock;
import com.android.systemui.qs.tiles.carcontrol.ReversingRadar;
import com.android.systemui.qs.tiles.carcontrol.RightChildLock;
import com.android.systemui.qs.tiles.carcontrol.SLW;
import com.android.systemui.qs.tiles.carcontrol.SmartCare;
import com.android.systemui.qs.tiles.carcontrol.SteeringWheelHeating;
import com.android.systemui.qs.tiles.carcontrol.TSR;
import com.android.systemui.qs.tiles.carcontrol.TransportMode;
import com.android.systemui.qs.tiles.carcontrol.Trunk;
import com.android.systemui.qs.tiles.carcontrol.VentilationHeating;
import com.android.systemui.qs.tiles.carcontrol.WindowLock;
import com.android.systemui.qs.tiles.carcontrol.WireLessCharging;
import com.android.systemui.qs.tiles.carcontrol.WireLessChargingLeft;
import com.android.systemui.qs.tiles.carcontrol.WireLessChargingRight;
import com.android.systemui.util.AppUtil;
import com.android.systemui.util.StyleUtil;
import com.byd.card.utils.AppUtils;
import com.byd.profile.ProfileManager;
import com.bydux.api.common.tools.log.BydLogHelper;

/* JADX INFO: loaded from: classes.dex */
public class QSFactoryImpl implements QSFactory {
    public Context mContext;

    public QSFactoryImpl(Context mContext) {
        this.mContext = mContext;
    }

    @Override // com.android.systemui.qs.tileimpl.QSFactory
    public QSTile createTile(String tileSpec, boolean fromSaved) {
        return createTileInternal(tileSpec, fromSaved);
    }

    @Override // com.android.systemui.qs.tileimpl.QSFactory
    public QSTile createCarTile(String tileSpec, boolean fromSaved) {
        if (fromSaved && QSIconConstants.tileName.containsKey(tileSpec)) {
            tileSpec = QSIconConstants.tileName.get(tileSpec);
        }
        boolean zContainsKey = QSIconConstants.icon.containsKey(tileSpec);
        BydLogHelper.logD("createCarTile tileSpec : " + tileSpec + " , containsKey : " + zContainsKey);
        if (!zContainsKey) {
            return null;
        }
        BydLogHelper.logD("createCarTile tileSpec : " + tileSpec);
        if (TextUtils.equals(tileSpec, this.mContext.getString(R.string.car_title_hud))) {
            return new Hud(this.mContext);
        }
        if (TextUtils.equals(tileSpec, this.mContext.getString(R.string.car_title_slw))) {
            return new SLW(this.mContext);
        }
        if (TextUtils.equals(tileSpec, this.mContext.getString(R.string.car_title_active_suspension_mode))) {
            return new ActiveSuspensionMode(this.mContext);
        }
        if (TextUtils.equals(tileSpec, this.mContext.getString(R.string.car_title_massage))) {
            return new Massage(this.mContext);
        }
        if (TextUtils.equals(tileSpec, this.mContext.getString(R.string.car_title_ventilation_heating))) {
            return new VentilationHeating(this.mContext);
        }
        if (TextUtils.equals(tileSpec, this.mContext.getString(R.string.car_title_auto_hold))) {
            return new AutoHold(this.mContext);
        }
        if (TextUtils.equals(tileSpec, this.mContext.getString(R.string.car_title_electric_defrosting)) || TextUtils.equals(tileSpec, this.mContext.getString(R.string.car_title_rear_defrosting))) {
            return new Defrosting(this.mContext);
        }
        if (TextUtils.equals(tileSpec, this.mContext.getString(R.string.car_title_trunk)) || TextUtils.equals(tileSpec, this.mContext.getString(R.string.car_title_trunk_open))) {
            return new Trunk(this.mContext);
        }
        if (TextUtils.equals(tileSpec, this.mContext.getString(R.string.car_title_skylight)) || TextUtils.equals(tileSpec, this.mContext.getString(R.string.car_title_skylight_and_sunshade)) || TextUtils.equals(tileSpec, this.mContext.getString(R.string.car_title_sunshade))) {
            return new MoonRoof(this.mContext);
        }
        if (TextUtils.equals(tileSpec, this.mContext.getString(R.string.car_title_window_lock))) {
            return new WindowLock(this.mContext);
        }
        if (TextUtils.equals(tileSpec, this.mContext.getString(R.string.car_title_left_child_lock))) {
            return new LeftChildLock(this.mContext);
        }
        if (TextUtils.equals(tileSpec, this.mContext.getString(R.string.car_title_right_child_lock))) {
            return new RightChildLock(this.mContext);
        }
        if (TextUtils.equals(tileSpec, this.mContext.getString(R.string.car_title_electronic_parking_brake))) {
            return new ElectronicParkingBrake(this.mContext);
        }
        if (TextUtils.equals(tileSpec, this.mContext.getString(R.string.car_title_energy_feedback))) {
            return new EnergyFeedbackIntensity(this.mContext);
        }
        if (TextUtils.equals(tileSpec, this.mContext.getString(R.string.car_title_steering_wheel_heating))) {
            return new SteeringWheelHeating(this.mContext);
        }
        if (TextUtils.equals(tileSpec, this.mContext.getString(R.string.car_title_wireless_charging))) {
            return new WireLessCharging(this.mContext);
        }
        if (TextUtils.equals(tileSpec, this.mContext.getString(R.string.car_title_wireless_charging_l))) {
            return new WireLessChargingLeft(this.mContext);
        }
        if (TextUtils.equals(tileSpec, this.mContext.getString(R.string.car_title_wireless_charging_r))) {
            return new WireLessChargingRight(this.mContext);
        }
        if (TextUtils.equals(tileSpec, this.mContext.getString(R.string.car_title_door_light_auto))) {
            return new DoorCtrl(this.mContext);
        }
        if (TextUtils.equals(tileSpec, this.mContext.getString(R.string.car_title_hma))) {
            return new Hma(this.mContext);
        }
        if (TextUtils.equals(tileSpec, this.mContext.getString(R.string.car_title_interior_light_on))) {
            return new InteriorLightOn(this.mContext);
        }
        if (TextUtils.equals(tileSpec, this.mContext.getString(R.string.car_title_interior_light_off))) {
            return new InteriorLightOff(this.mContext);
        }
        if (TextUtils.equals(tileSpec, this.mContext.getString(R.string.car_title_esp_off))) {
            return new EspOff(this.mContext);
        }
        if (TextUtils.equals(tileSpec, this.mContext.getString(R.string.car_title_reversing_radar))) {
            return new ReversingRadar(this.mContext);
        }
        if (TextUtils.equals(tileSpec, this.mContext.getString(R.string.car_title_hill_descent_control))) {
            return new HillDescentControl(this.mContext);
        }
        if (TextUtils.equals(tileSpec, this.mContext.getString(R.string.car_title_auto_parking))) {
            return new AutoParking(this.mContext);
        }
        if (TextUtils.equals(tileSpec, this.mContext.getString(R.string.car_title_bsd))) {
            return new Bsd(this.mContext);
        }
        if (TextUtils.equals(tileSpec, this.mContext.getString(R.string.car_title_tsr))) {
            return new TSR(this.mContext);
        }
        if (TextUtils.equals(tileSpec, this.mContext.getString(R.string.car_title_icc))) {
            return new Icc(this.mContext);
        }
        if (TextUtils.equals(tileSpec, this.mContext.getString(R.string.car_title_new_energy))) {
            return new NewEnergy(this.mContext);
        }
        if (TextUtils.equals(tileSpec, this.mContext.getString(R.string.car_title_sound))) {
            return new Audio(this.mContext);
        }
        if (TextUtils.equals(tileSpec, this.mContext.getString(R.string.transport_mode))) {
            return new TransportMode(this.mContext);
        }
        if (TextUtils.equals(tileSpec, this.mContext.getString(R.string.car_title_rear_control_lock))) {
            return new RearControlLock(this.mContext);
        }
        if (TextUtils.equals(tileSpec, this.mContext.getString(R.string.car_title_driver_monitor))) {
            return new DriverMonitor(this.mContext);
        }
        if (TextUtils.equals(tileSpec, this.mContext.getString(R.string.car_title_engine_sound_simulator))) {
            return new Avas(this.mContext);
        }
        if (TextUtils.equals(tileSpec, this.mContext.getString(R.string.car_title_coach_mode))) {
            return new DiTrainerMode(this.mContext);
        }
        if (TextUtils.equals(tileSpec, this.mContext.getString(R.string.car_title_reverse_sensor))) {
            return new Dvr(this.mContext);
        }
        if (TextUtils.equals(tileSpec, this.mContext.getString(R.string.car_title_digital_panel))) {
            return new FrPassengerGreeting(this.mContext);
        }
        if (TextUtils.equals(tileSpec, this.mContext.getString(R.string.car_title_game_center))) {
            return new GamesCenter(this.mContext);
        }
        if (TextUtils.equals(tileSpec, this.mContext.getString(R.string.car_title_karaoke))) {
            return new Karaoke(this.mContext);
        }
        if (TextUtils.equals(tileSpec, this.mContext.getString(R.string.car_title_smart_caring))) {
            return new SmartCare(this.mContext);
        }
        if (TextUtils.equals(tileSpec, this.mContext.getString(R.string.car_title_cpd))) {
            return new Cpd(this.mContext);
        }
        if (TextUtils.equals(tileSpec, this.mContext.getString(R.string.car_title_la))) {
            return new LDA(this.mContext);
        }
        BydLogHelper.logD("createCarTile tileSpec : " + tileSpec + " null ");
        return null;
    }

    @Override // com.android.systemui.qs.tileimpl.QSFactory
    public QSTile createSceneTile(String tileSpec, boolean fromSaved) {
        if (fromSaved && QSIconConstants.tileName.containsKey(tileSpec)) {
            tileSpec = QSIconConstants.tileName.get(tileSpec);
        }
        if (!QSIconConstants.icon.containsKey(tileSpec)) {
            return null;
        }
        if (ProfileManager.getInstance(this.mContext).isAvailable(SceneCommonTile.initModelId(this.mContext, tileSpec))) {
            return new SceneCommonTile(this.mContext, tileSpec, QSIconConstants.icon.get(tileSpec).intValue());
        }
        return null;
    }

    private QSTileImpl createTileInternal(String tileSpec, boolean fromSaved) {
        if (fromSaved && QSIconConstants.tileName.containsKey(tileSpec)) {
            tileSpec = QSIconConstants.tileName.get(tileSpec);
        }
        if (TextUtils.equals(tileSpec, this.mContext.getString(R.string.qs_title_wifi))) {
            return new WifiTile(this.mContext);
        }
        if (TextUtils.equals(tileSpec, this.mContext.getString(R.string.qs_title_bluetooth))) {
            return new BluetoothTile(this.mContext);
        }
        if (TextUtils.equals(tileSpec, this.mContext.getString(R.string.qs_title_device_attachment))) {
            boolean zIsAppInstall = AppUtils.isAppInstall("com.ts.carplay.app");
            boolean zIsAppInstall2 = AppUtils.isAppInstall("com.ts.androidauto.app");
            BydLogHelper.logD("isCarPlayInstall: " + zIsAppInstall + " isAndroidAutoInstall: " + zIsAppInstall2);
            if (zIsAppInstall || zIsAppInstall2) {
                return new DeviceAttachmentTile(this.mContext);
            }
            return null;
        }
        if (TextUtils.equals(tileSpec, this.mContext.getString(R.string.qs_title_dark_mode)) || TextUtils.equals(tileSpec, this.mContext.getString(R.string.qs_title_light_mode)) || TextUtils.equals(tileSpec, this.mContext.getString(R.string.qs_title_auto_mode))) {
            if (StyleUtil.isDenza()) {
                return null;
            }
            return new UiModeNightTile(this.mContext);
        }
        if (TextUtils.equals(tileSpec, this.mContext.getString(R.string.qs_title_rest_screen))) {
            return new ScreenOffTile(this.mContext);
        }
        if (TextUtils.equals(tileSpec, this.mContext.getString(R.string.qs_title_automatic_rotation))) {
            int i = SystemProperties.getInt(Constants.BOSCH_CONFIG_AREA, 49);
            boolean z = (StyleUtil.isDenza() || (i == 102 && AppUtil.isUKE(this.mContext)) || AppUtil.isMC(this.mContext)) ? false : true;
            BydLogHelper.logD("isShowAuto = " + z + " countryName = " + i);
            if (z) {
                return new RotationLockTile(this.mContext);
            }
            return null;
        }
        if (TextUtils.equals(tileSpec, this.mContext.getString(R.string.qs_title_telephone_data))) {
            return new CellularTile(this.mContext);
        }
        if (TextUtils.equals(tileSpec, this.mContext.getString(R.string.qs_title_mute))) {
            return new MuteTile(this.mContext);
        }
        if (TextUtils.equals(tileSpec, this.mContext.getString(R.string.qs_title_screen_shot))) {
            return new ScreenshotTile(this.mContext);
        }
        if (TextUtils.equals(tileSpec, this.mContext.getString(R.string.qs_title_privacy))) {
            return new PrivacyTile(this.mContext);
        }
        return null;
    }
}
