package com.byd.systemsettings.system.unit;

import android.content.Context;
import android.view.View;
import android.widget.TextView;
import b.b.m.e.a;
import b.b.m.e.g;
import com.byd.base.view.segmentedbutton.SegmentedButtonGroup;
import com.byd.carsettings.R;
import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

/* JADX INFO: loaded from: classes.dex */
public class UnitSettingsPager extends a<UnitSettingsPresenter> implements SegmentedButtonGroup.f {
    public View ivBack;
    private UnitSettingsDialog mDialog;
    public SegmentedButtonGroup rgDistance;
    public SegmentedButtonGroup rgOil;
    public SegmentedButtonGroup rgPower;
    public SegmentedButtonGroup rgPressure;
    public SegmentedButtonGroup rgTemp;
    public TextView tvDistance;
    public TextView tvOil;
    public TextView tvPower;
    public TextView tvPress;
    public TextView tvTemp;
    public TextView tvTitle;
    public UnitSetting unitSetting;

    public UnitSettingsPager(Context context) {
        super(context);
        b.b.m.c.a.a(this.TAG, "----UnitSettingsPager() -------");
    }

    private void setDefaultUnitPosition(UnitSetting unitSetting) {
        this.rgDistance.setPosition(unitSetting.defDisUnit);
        this.rgOil.setPosition(unitSetting.defOilUnit);
        this.rgPressure.setPosition(unitSetting.defPressUnit);
        this.rgPower.setPosition(unitSetting.defPowerUnit);
        this.rgTemp.setPosition(unitSetting.defTempUnit);
    }

    private void unitLearnSelf(UnitSetting unitSetting) {
        this.tvOil.setVisibility(unitSetting.enableOilUnitSet ? 0 : 8);
        this.rgOil.setVisibility(unitSetting.enableOilUnitSet ? 0 : 8);
        this.tvDistance.setVisibility(unitSetting.enableDisUnitSet ? 0 : 8);
        this.rgDistance.setVisibility(unitSetting.enableDisUnitSet ? 0 : 8);
        this.tvPress.setVisibility(unitSetting.enablePressUnitSet ? 0 : 8);
        this.rgPressure.setVisibility(unitSetting.enablePressUnitSet ? 0 : 8);
        this.tvPower.setVisibility(unitSetting.enablePowerUnitSet ? 0 : 8);
        this.rgPower.setVisibility(unitSetting.enablePowerUnitSet ? 0 : 8);
        this.tvTemp.setVisibility(unitSetting.enableTempUnitSet ? 0 : 8);
        this.rgTemp.setVisibility(unitSetting.enableTempUnitSet ? 0 : 8);
    }

    @Override // b.b.m.e.h
    public /* bridge */ /* synthetic */ void bindEvent() {
        super.bindEvent();
    }

    @Override // b.b.m.e.h
    public int getLayoutId() {
        return b.b.o.b.b.a.c() == 2 ? R.layout.dialog_unit_settings_r : R.layout.dialog_unit_settings;
    }

    /* JADX WARN: Incorrect return type in method signature: ()TP; */
    @Override // b.b.m.e.h
    /* JADX INFO: renamed from: getP */
    public /* bridge */ /* synthetic */ g mo7getP() {
        return super.mo7getP();
    }

    @Override // b.b.m.e.h
    /* JADX INFO: renamed from: getP */
    public /* bridge */ /* synthetic */ Object mo7getP() {
        return super.mo7getP();
    }

    @Override // b.b.m.e.f
    public void initData(Context context) {
        UnitSetting defUnit = ((UnitSettingsPresenter) mo7getP()).getDefUnit();
        this.unitSetting = defUnit;
        unitLearnSelf(defUnit);
        setDefaultUnitPosition(this.unitSetting);
    }

    @Override // b.b.m.e.f
    public void initViews(Context context) {
        TextView textView = (TextView) this.mRootView.findViewById(R.id.tv_title_dialog_common_titles);
        this.tvTitle = textView;
        textView.setText(R.string.unit_setting_system);
        View viewFindViewById = this.mRootView.findViewById(R.id.iv_back_dialog_common_title);
        this.ivBack = viewFindViewById;
        viewFindViewById.setOnClickListener(this);
        this.tvDistance = (TextView) this.mRootView.findViewById(R.id.tv_distance_dialog_uint_settings);
        SegmentedButtonGroup segmentedButtonGroup = (SegmentedButtonGroup) this.mRootView.findViewById(R.id.srg_ev_distance_dialog_uint_settings);
        this.rgDistance = segmentedButtonGroup;
        segmentedButtonGroup.setOnPositionChangedListener(this);
        this.tvOil = (TextView) this.mRootView.findViewById(R.id.tv_oil_dialog_uint_settings);
        SegmentedButtonGroup segmentedButtonGroup2 = (SegmentedButtonGroup) this.mRootView.findViewById(R.id.srg_dm_distance_oil_dialog_uint_settings);
        this.rgOil = segmentedButtonGroup2;
        segmentedButtonGroup2.setOnPositionChangedListener(this);
        this.tvPress = (TextView) this.mRootView.findViewById(R.id.tv_press);
        SegmentedButtonGroup segmentedButtonGroup3 = (SegmentedButtonGroup) this.mRootView.findViewById(R.id.srg_pressure_dialog_uint_settings);
        this.rgPressure = segmentedButtonGroup3;
        segmentedButtonGroup3.setOnPositionChangedListener(this);
        this.tvPower = (TextView) this.mRootView.findViewById(R.id.tv_power);
        SegmentedButtonGroup segmentedButtonGroup4 = (SegmentedButtonGroup) this.mRootView.findViewById(R.id.srg_power_dialog_uint_settings);
        this.rgPower = segmentedButtonGroup4;
        segmentedButtonGroup4.setOnPositionChangedListener(this);
        this.tvTemp = (TextView) this.mRootView.findViewById(R.id.tv_temp);
        SegmentedButtonGroup segmentedButtonGroup5 = (SegmentedButtonGroup) this.mRootView.findViewById(R.id.srg_temperature_dialog_uint_settings);
        this.rgTemp = segmentedButtonGroup5;
        segmentedButtonGroup5.setOnPositionChangedListener(this);
        EventBus.getDefault().register(this);
        b.b.m.c.a.a(this.TAG, "----initViews -------context:" + context);
    }

    @Override // android.view.View.OnClickListener
    public void onClick(View view) {
        UnitSettingsDialog unitSettingsDialog;
        if (view.getId() != R.id.iv_back_dialog_common_title || (unitSettingsDialog = this.mDialog) == null) {
            return;
        }
        unitSettingsDialog.dismiss();
    }

    @Override // b.b.m.e.a, b.b.m.e.f
    public void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onGetMessage(b.b.a.c.a aVar) {
        int iH = aVar.h();
        if (iH == 30013) {
            int temperatureUnitIndex = ((UnitSettingsPresenter) mo7getP()).getTemperatureUnitIndex();
            this.rgTemp.setPosition(temperatureUnitIndex);
            b.b.m.c.a.a(this.TAG, "温度单位变化,index=" + temperatureUnitIndex);
        }
        if (iH == 70013) {
            int distanceUnitIndex = ((UnitSettingsPresenter) mo7getP()).getDistanceUnitIndex();
            this.rgDistance.setPosition(distanceUnitIndex);
            b.b.m.c.a.a(this.TAG, "距离单位变化,index=" + distanceUnitIndex);
            return;
        }
        switch (iH) {
            case 70015:
                int pressureUnitIndex = ((UnitSettingsPresenter) mo7getP()).getPressureUnitIndex();
                b.b.m.c.a.a(this.TAG, "气压单位变化,index=" + pressureUnitIndex);
                this.rgPressure.setPosition(pressureUnitIndex);
                break;
            case 70016:
                int oliUnitIndex = ((UnitSettingsPresenter) mo7getP()).getOliUnitIndex();
                b.b.m.c.a.a(this.TAG, "油耗单位变化,index=" + oliUnitIndex);
                this.rgOil.setPosition(oliUnitIndex);
                break;
            case 70017:
                int powerUnitIndex = ((UnitSettingsPresenter) mo7getP()).getPowerUnitIndex();
                b.b.m.c.a.a(this.TAG, "功率单位变化,index=" + powerUnitIndex);
                this.rgPower.setPosition(powerUnitIndex);
                break;
        }
    }

    @Override // com.byd.base.view.segmentedbutton.SegmentedButtonGroup.f
    public void onPositionChanged(View view, View view2, int i) {
        int i2;
        switch (view.getId()) {
            case R.id.srg_dm_distance_oil_dialog_uint_settings /* 2131298182 */:
                i2 = 1;
                break;
            case R.id.srg_ev_distance_dialog_uint_settings /* 2131298183 */:
                i2 = 0;
                break;
            case R.id.srg_power_dialog_uint_settings /* 2131298187 */:
                i2 = 3;
                break;
            case R.id.srg_pressure_dialog_uint_settings /* 2131298188 */:
                i2 = 2;
                break;
            case R.id.srg_temperature_dialog_uint_settings /* 2131298195 */:
                i2 = 4;
                break;
            default:
                i2 = -1;
                break;
        }
        if (i2 > -1) {
            ((UnitSettingsPresenter) mo7getP()).setUnit(i2, i);
        } else {
            b.b.m.c.a.c(this.TAG, "unitType error!!!");
        }
    }

    public void setFragment(UnitSettingsDialog unitSettingsDialog) {
        this.mDialog = unitSettingsDialog;
    }

    @Override // b.b.m.e.h
    public /* bridge */ /* synthetic */ void unbindP() {
        super.unbindP();
    }

    @Override // b.b.m.e.h
    public UnitSettingsPresenter newP() {
        return new UnitSettingsPresenter();
    }
}
