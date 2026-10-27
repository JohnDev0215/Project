package com.byd.carsettings.ui.activity;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.hardware.bydauto.bodywork.AbsBYDAutoBodyworkListener;
import android.hardware.bydauto.charging.BYDAutoChargingDevice;
import android.hardware.bydauto.charging.ChargingTimerInfo;
import android.hardware.bydauto.gearbox.BYDAutoGearboxDevice;
import android.hardware.bydauto.speed.AbsBYDAutoSpeedListener;
import android.hardware.bydauto.uistyleconfig.BYDAutoStudy;
import android.hardware.bydauto.vehiclehealth.BYDAutoVehicleHealthDevice;
import android.os.Bundle;
import android.os.Environment;
import android.text.TextUtils;
import android.text.method.ScrollingMovementMethod;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import androidx.annotation.Nullable;
import b.b.g.k.r;
import b.b.g.k.s;
import b.b.g.n.q.u;
import com.byd.carsettings.R;
import com.bydux.api.common.tools.cmd.BydAdbHelper;
import com.bydux.api.common.tools.log.BydLogHelper;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.Locale;

/* JADX INFO: loaded from: classes2.dex */
public class ZKTestActivity extends Activity implements View.OnClickListener {

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public TextView f2947b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public TextView f2948c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public TextView f2949d;
    public s g;
    public b.b.g.k.l h;
    public r i;
    public b.b.g.k.j j;
    public b.b.g.k.k k;
    public b.b.g.k.h l;
    public b.b.g.k.p m;
    public b.b.g.k.n n;
    public b.b.g.k.o o;
    public b.b.g.k.f p;

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public String f2946a = "ZKTestActivity";

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public String f2950e = "";

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public String f2951f = "";
    public int q = 0;
    public int r = 0;
    public int s = 0;
    public int t = 0;

    public class a extends b.b.g.k.o {
        public a() {
        }

        @Override // b.b.g.k.o, android.hardware.bydauto.sensor.AbsBYDAutoSensorListener
        public void onAccSensorDataChanged(byte[] bArr) throws Throwable {
            super.onAccSensorDataChanged(bArr);
            ZKTestActivity zKTestActivity = ZKTestActivity.this;
            zKTestActivity.f(zKTestActivity.f2946a, "onAccSensorDataChanged----" + Arrays.toString(bArr));
        }

        @Override // b.b.g.k.o, android.hardware.bydauto.sensor.AbsBYDAutoSensorListener
        public void onHumiditySensorValueChanged(float f2) throws Throwable {
            super.onHumiditySensorValueChanged(f2);
            ZKTestActivity zKTestActivity = ZKTestActivity.this;
            zKTestActivity.f(zKTestActivity.f2946a, "onHumiditySensorValueChanged----" + f2);
        }

        @Override // b.b.g.k.o, android.hardware.bydauto.sensor.AbsBYDAutoSensorListener
        public void onLightIntensityChanged(int i) throws Throwable {
            super.onLightIntensityChanged(i);
            ZKTestActivity zKTestActivity = ZKTestActivity.this;
            zKTestActivity.f(zKTestActivity.f2946a, "onLightIntensityChanged----" + i);
        }

        @Override // b.b.g.k.o, android.hardware.bydauto.sensor.AbsBYDAutoSensorListener
        public void onSlopeValueChanged(int i) throws Throwable {
            super.onSlopeValueChanged(i);
            ZKTestActivity zKTestActivity = ZKTestActivity.this;
            zKTestActivity.f(zKTestActivity.f2946a, "onSlopeValueChanged----" + i);
        }

        @Override // b.b.g.k.o, android.hardware.bydauto.sensor.AbsBYDAutoSensorListener
        public void onTemperatureSensorValueChanged(float f2) throws Throwable {
            super.onTemperatureSensorValueChanged(f2);
            ZKTestActivity zKTestActivity = ZKTestActivity.this;
            zKTestActivity.f(zKTestActivity.f2946a, "onTemperatureSensorValueChanged----" + f2);
        }
    }

    public class b extends AbsBYDAutoBodyworkListener {
        public b() {
        }

        @Override // android.hardware.bydauto.bodywork.AbsBYDAutoBodyworkListener
        public void onHasMessageChanged(int i, int i2) throws Throwable {
            super.onHasMessageChanged(i, i2);
            ZKTestActivity zKTestActivity = ZKTestActivity.this;
            zKTestActivity.f(zKTestActivity.f2946a, System.currentTimeMillis() + "----onHasMessageChanged----" + i + "----" + i2);
        }

        @Override // android.hardware.bydauto.bodywork.AbsBYDAutoBodyworkListener
        public void onMessage5sOnlineStateChanged(int i, int i2) throws Throwable {
            super.onMessage5sOnlineStateChanged(i, i2);
            ZKTestActivity zKTestActivity = ZKTestActivity.this;
            zKTestActivity.f(zKTestActivity.f2946a, System.currentTimeMillis() + "----onMessage5sOnlineStateChanged----" + i + "----" + i2);
        }
    }

    public class c extends b.b.g.k.j {
        public c() {
        }

        @Override // b.b.g.k.j, android.hardware.bydauto.energy.AbsBYDAutoEnergyListener
        public void onElectricPointFlagChanged(int i) throws Throwable {
            super.onElectricPointFlagChanged(i);
            ZKTestActivity zKTestActivity = ZKTestActivity.this;
            zKTestActivity.f(zKTestActivity.f2946a, "[能耗曲线]----onElectricPointFlagChanged：" + i);
        }

        @Override // b.b.g.k.j, android.hardware.bydauto.energy.AbsBYDAutoEnergyListener
        public void onLast200MElectricConChanged(float f2) throws Throwable {
            super.onLast200MElectricConChanged(f2);
            ZKTestActivity zKTestActivity = ZKTestActivity.this;
            zKTestActivity.f(zKTestActivity.f2946a, "[能耗曲线]----onLast200MElectricConChanged：" + f2);
        }
    }

    public class d extends b.b.g.k.g {
        public d() {
        }

        @Override // b.b.g.k.g, android.hardware.bydauto.setting.AbsBYDAutoSettingListener
        public void onChargingPortSwitchChanged(int i) throws Throwable {
            super.onChargingPortSwitchChanged(i);
            ZKTestActivity zKTestActivity = ZKTestActivity.this;
            zKTestActivity.f(zKTestActivity.f2946a, "[充电电锁]----onChargingPortSwitchChanged：" + i);
        }

        @Override // b.b.g.k.g, android.hardware.bydauto.setting.AbsBYDAutoSettingListener
        public void onEnergyFeedbackStrengthChanged(int i) {
            super.onEnergyFeedbackStrengthChanged(i);
        }
    }

    public class e extends b.b.g.k.d {
        public e() {
        }

        @Override // b.b.g.k.d, android.hardware.bydauto.gearbox.AbsBYDAutoGearboxListener
        public void onGearboxAutoModeTypeChanged(int i) throws Throwable {
            super.onGearboxAutoModeTypeChanged(i);
            ZKTestActivity zKTestActivity = ZKTestActivity.this;
            zKTestActivity.f(zKTestActivity.f2946a, System.currentTimeMillis() + "----onGearboxAutoModeTypeChanged----" + i);
        }
    }

    public class f extends AbsBYDAutoSpeedListener {
        public f() {
        }

        @Override // android.hardware.bydauto.speed.AbsBYDAutoSpeedListener
        public void onSpeedChanged(double d2) throws Throwable {
            super.onSpeedChanged(d2);
            ZKTestActivity zKTestActivity = ZKTestActivity.this;
            zKTestActivity.f(zKTestActivity.f2946a, System.currentTimeMillis() + "----onSpeedChanged----" + d2);
        }
    }

    public class g implements Runnable {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public final /* synthetic */ String f2955a;

        public g(String str) {
            this.f2955a = str;
        }

        @Override // java.lang.Runnable
        public void run() {
            ZKTestActivity.this.f2949d.setText(ZKTestActivity.this.f2949d.getText().toString() + BydAdbHelper.COMMAND_LINE_END + this.f2955a);
        }
    }

    public class h extends s {
        public h() {
        }

        @Override // b.b.g.k.s, android.hardware.bydauto.vehiclehealth.AbsBYDAutoVehicleHealthListener
        public void onEPBStateChanged(int i) throws Throwable {
            super.onEPBStateChanged(i);
            ZKTestActivity zKTestActivity = ZKTestActivity.this;
            zKTestActivity.f(zKTestActivity.f2946a, hashCode() + "----onEPBStateChanged----" + i);
        }

        @Override // b.b.g.k.s, android.hardware.bydauto.vehiclehealth.AbsBYDAutoVehicleHealthListener
        public void onFrontWiperCheckStateChanged(int i) throws Throwable {
            super.onFrontWiperCheckStateChanged(i);
            ZKTestActivity zKTestActivity = ZKTestActivity.this;
            zKTestActivity.f(zKTestActivity.f2946a, "onFrontWiperCheckStateChanged----" + i);
        }

        @Override // b.b.g.k.s, android.hardware.bydauto.vehiclehealth.AbsBYDAutoVehicleHealthListener
        public void onOilLevelChanged(int i) throws Throwable {
            super.onOilLevelChanged(i);
            ZKTestActivity zKTestActivity = ZKTestActivity.this;
            zKTestActivity.f(zKTestActivity.f2946a, "onOilLevelChanged----" + i);
        }

        @Override // b.b.g.k.s, android.hardware.bydauto.vehiclehealth.AbsBYDAutoVehicleHealthListener
        public void onOilLifetimeChanged(int i, int i2) throws Throwable {
            super.onOilLifetimeChanged(i, i2);
            ZKTestActivity zKTestActivity = ZKTestActivity.this;
            zKTestActivity.f(zKTestActivity.f2946a, "onOilLifetimeChanged----" + i + "----" + i2);
        }

        @Override // b.b.g.k.s, android.hardware.bydauto.vehiclehealth.AbsBYDAutoVehicleHealthListener
        public void onOilMaintainanceHintChanged(int i, int i2) throws Throwable {
            super.onOilMaintainanceHintChanged(i, i2);
            ZKTestActivity zKTestActivity = ZKTestActivity.this;
            zKTestActivity.f(zKTestActivity.f2946a, "onOilMaintainanceHintChanged----type：" + i + ":" + i2);
        }

        @Override // b.b.g.k.s, android.hardware.bydauto.vehiclehealth.AbsBYDAutoVehicleHealthListener
        public void onOilUpdateStateChanged(int i) throws Throwable {
            super.onOilUpdateStateChanged(i);
            ZKTestActivity zKTestActivity = ZKTestActivity.this;
            zKTestActivity.f(zKTestActivity.f2946a, "onOilUpdateStateChanged----" + i);
        }

        @Override // b.b.g.k.s, android.hardware.bydauto.vehiclehealth.AbsBYDAutoVehicleHealthListener
        public void onRearWiperCheckStateChanged(int i) throws Throwable {
            super.onRearWiperCheckStateChanged(i);
            ZKTestActivity zKTestActivity = ZKTestActivity.this;
            zKTestActivity.f(zKTestActivity.f2946a, "onRearWiperCheckStateChanged----" + i);
        }

        @Override // b.b.g.k.s, android.hardware.bydauto.vehiclehealth.AbsBYDAutoVehicleHealthListener
        public void onResetOilLifetimeChanged(int i, int i2) throws Throwable {
            super.onResetOilLifetimeChanged(i, i2);
            ZKTestActivity zKTestActivity = ZKTestActivity.this;
            zKTestActivity.f(zKTestActivity.f2946a, "onResetOilLifetimeChanged----" + i + "----" + i2);
        }

        @Override // b.b.g.k.s, android.hardware.bydauto.vehiclehealth.AbsBYDAutoVehicleHealthListener
        public void onTransportModeChanged(int i) throws Throwable {
            super.onTransportModeChanged(i);
            ZKTestActivity zKTestActivity = ZKTestActivity.this;
            zKTestActivity.f(zKTestActivity.f2946a, "onTransportModeChanged----" + i);
        }

        @Override // b.b.g.k.s, android.hardware.bydauto.vehiclehealth.AbsBYDAutoVehicleHealthListener
        public void onTransportModeStateHintChanged(int i) throws Throwable {
            super.onTransportModeStateHintChanged(i);
            ZKTestActivity zKTestActivity = ZKTestActivity.this;
            zKTestActivity.f(zKTestActivity.f2946a, "onOilMaintainanceHintChanged----" + i);
        }
    }

    public class i extends b.b.g.k.j {
        public i() {
        }

        @Override // b.b.g.k.j, android.hardware.bydauto.energy.AbsBYDAutoEnergyListener
        public void onBCMStateChanged(int i) throws Throwable {
            super.onBCMStateChanged(i);
            ZKTestActivity zKTestActivity = ZKTestActivity.this;
            zKTestActivity.f(zKTestActivity.f2946a, "onBCMStateChanged----" + i);
        }

        @Override // b.b.g.k.j, android.hardware.bydauto.energy.AbsBYDAutoEnergyListener
        public void onDCWorkModeChanged(int i) throws Throwable {
            super.onDCWorkModeChanged(i);
            ZKTestActivity zKTestActivity = ZKTestActivity.this;
            zKTestActivity.f(zKTestActivity.f2946a, "onDCWorkModeChanged----" + i);
        }

        @Override // b.b.g.k.j, android.hardware.bydauto.energy.AbsBYDAutoEnergyListener
        public void onDriveDistanceShowModeChanged(int i) throws Throwable {
            super.onDriveDistanceShowModeChanged(i);
            ZKTestActivity zKTestActivity = ZKTestActivity.this;
            zKTestActivity.f(zKTestActivity.f2946a, "onDriveDistanceShowModeChanged----" + i);
        }

        @Override // b.b.g.k.j, android.hardware.bydauto.energy.AbsBYDAutoEnergyListener
        public void onDriveDistanceShowStandardChanged(int i) throws Throwable {
            super.onDriveDistanceShowStandardChanged(i);
            ZKTestActivity zKTestActivity = ZKTestActivity.this;
            zKTestActivity.f(zKTestActivity.f2946a, "onDriveDistanceShowStandardChanged----" + i);
        }

        @Override // b.b.g.k.j, android.hardware.bydauto.energy.AbsBYDAutoEnergyListener
        public void onEPedalStateChanged(int i) throws Throwable {
            super.onEPedalStateChanged(i);
            ZKTestActivity zKTestActivity = ZKTestActivity.this;
            zKTestActivity.f(zKTestActivity.f2946a, "onEPedalStateChanged----" + i);
        }

        @Override // b.b.g.k.j, android.hardware.bydauto.energy.AbsBYDAutoEnergyListener
        public void onElectricPointFlagChanged(int i) throws Throwable {
            super.onElectricPointFlagChanged(i);
            ZKTestActivity zKTestActivity = ZKTestActivity.this;
            zKTestActivity.f(zKTestActivity.f2946a, "onElectricPointFlagChanged----" + i);
        }

        @Override // b.b.g.k.j, android.hardware.bydauto.energy.AbsBYDAutoEnergyListener
        public void onElectricShowModeChanged(int i) throws Throwable {
            super.onElectricShowModeChanged(i);
            ZKTestActivity zKTestActivity = ZKTestActivity.this;
            zKTestActivity.f(zKTestActivity.f2946a, "onElectricShowModeChanged----" + i);
        }

        @Override // android.hardware.bydauto.energy.AbsBYDAutoEnergyListener
        public void onEnergyFeedbackLevelChanged(int i) throws Throwable {
            super.onEnergyFeedbackLevelChanged(i);
            ZKTestActivity zKTestActivity = ZKTestActivity.this;
            zKTestActivity.f(zKTestActivity.f2946a, "onEnergyFeedbackLevelChanged----" + i);
        }

        @Override // b.b.g.k.j, android.hardware.bydauto.energy.AbsBYDAutoEnergyListener
        public void onEnergyModeChanged(int i) throws Throwable {
            super.onEnergyModeChanged(i);
            ZKTestActivity zKTestActivity = ZKTestActivity.this;
            zKTestActivity.f(zKTestActivity.f2946a, "onEnergyModeChanged----" + i);
        }

        @Override // b.b.g.k.j, android.hardware.bydauto.energy.AbsBYDAutoEnergyListener
        public void onEnergyModelChanged(int i) throws Throwable {
            super.onEnergyModelChanged(i);
            ZKTestActivity zKTestActivity = ZKTestActivity.this;
            zKTestActivity.f(zKTestActivity.f2946a, "onEnergyModelChanged----" + i);
        }

        @Override // b.b.g.k.j, android.hardware.bydauto.energy.AbsBYDAutoEnergyListener
        public void onEnergyStateChanged(int i) throws Throwable {
            super.onEnergyStateChanged(i);
            ZKTestActivity zKTestActivity = ZKTestActivity.this;
            zKTestActivity.f(zKTestActivity.f2946a, "onEnergyStateChanged----" + i);
        }

        @Override // b.b.g.k.j, android.hardware.bydauto.energy.AbsBYDAutoEnergyListener
        public void onHEVElectricPointFlagChanged(int i) throws Throwable {
            super.onHEVElectricPointFlagChanged(i);
            ZKTestActivity zKTestActivity = ZKTestActivity.this;
            zKTestActivity.f(zKTestActivity.f2946a, "onHEVElectricPointFlagChanged----" + i);
        }

        @Override // b.b.g.k.j, android.hardware.bydauto.energy.AbsBYDAutoEnergyListener
        public void onHEVLast200MElectricConChanged(float f2) throws Throwable {
            super.onHEVLast200MElectricConChanged(f2);
            ZKTestActivity zKTestActivity = ZKTestActivity.this;
            zKTestActivity.f(zKTestActivity.f2946a, "onHEVLast200MElectricConChanged----" + f2);
        }

        @Override // b.b.g.k.j, android.hardware.bydauto.energy.AbsBYDAutoEnergyListener
        public void onLast200MElectricConChanged(float f2) throws Throwable {
            super.onLast200MElectricConChanged(f2);
            ZKTestActivity zKTestActivity = ZKTestActivity.this;
            zKTestActivity.f(zKTestActivity.f2946a, "onLast200MElectricConChanged----" + f2);
        }

        @Override // b.b.g.k.j, android.hardware.bydauto.energy.AbsBYDAutoEnergyListener
        public void onLast200MOilConChanged(float f2) throws Throwable {
            super.onLast200MOilConChanged(f2);
            ZKTestActivity zKTestActivity = ZKTestActivity.this;
            zKTestActivity.f(zKTestActivity.f2946a, "onLast200MOilConChanged----" + f2);
        }

        @Override // b.b.g.k.j, android.hardware.bydauto.energy.AbsBYDAutoEnergyListener
        public void onLast50KMElectricConAvgChanged(float f2) throws Throwable {
            super.onLast50KMElectricConAvgChanged(f2);
            ZKTestActivity zKTestActivity = ZKTestActivity.this;
            zKTestActivity.f(zKTestActivity.f2946a, "onLast50KMElectricConAvgChanged----" + f2);
        }

        @Override // b.b.g.k.j, android.hardware.bydauto.energy.AbsBYDAutoEnergyListener
        public void onLast50KMOilConAvgChanged(float f2) throws Throwable {
            super.onLast50KMOilConAvgChanged(f2);
            ZKTestActivity zKTestActivity = ZKTestActivity.this;
            zKTestActivity.f(zKTestActivity.f2946a, "onLast50KMOilConAvgChanged----" + f2);
        }

        @Override // b.b.g.k.j, android.hardware.bydauto.energy.AbsBYDAutoEnergyListener
        public void onMandatoryElectricStateChanged(int i) throws Throwable {
            super.onMandatoryElectricStateChanged(i);
            ZKTestActivity zKTestActivity = ZKTestActivity.this;
            zKTestActivity.f(zKTestActivity.f2946a, "onMandatoryElectricStateChanged----" + i);
        }

        @Override // b.b.g.k.j, android.hardware.bydauto.energy.AbsBYDAutoEnergyListener
        public void onMemoryElectricStateChanged(int i) throws Throwable {
            super.onMemoryElectricStateChanged(i);
            ZKTestActivity zKTestActivity = ZKTestActivity.this;
            zKTestActivity.f(zKTestActivity.f2946a, "onMemoryElectricStateChanged----" + i);
        }

        @Override // b.b.g.k.j, android.hardware.bydauto.energy.AbsBYDAutoEnergyListener
        public void onMemoryMaxEvStateChanged(int i) throws Throwable {
            super.onMemoryMaxEvStateChanged(i);
            ZKTestActivity zKTestActivity = ZKTestActivity.this;
            zKTestActivity.f(zKTestActivity.f2946a, "onMemoryMaxEvStateChanged----" + i);
        }

        @Override // b.b.g.k.j, android.hardware.bydauto.energy.AbsBYDAutoEnergyListener
        public void onMemorySportStateChanged(int i) throws Throwable {
            super.onMemorySportStateChanged(i);
            ZKTestActivity zKTestActivity = ZKTestActivity.this;
            zKTestActivity.f(zKTestActivity.f2946a, "onMemorySportStateChanged----" + i);
        }

        @Override // b.b.g.k.j, android.hardware.bydauto.energy.AbsBYDAutoEnergyListener
        public void onOilPointFlagChanged(int i) throws Throwable {
            super.onOilPointFlagChanged(i);
            ZKTestActivity zKTestActivity = ZKTestActivity.this;
            zKTestActivity.f(zKTestActivity.f2946a, "onOilPointFlagChanged----" + i);
        }

        @Override // b.b.g.k.j, android.hardware.bydauto.energy.AbsBYDAutoEnergyListener
        public void onOperationModeChanged(int i) throws Throwable {
            super.onOperationModeChanged(i);
            ZKTestActivity zKTestActivity = ZKTestActivity.this;
            zKTestActivity.f(zKTestActivity.f2946a, "onOperationModeChanged----" + i);
        }

        @Override // b.b.g.k.j, android.hardware.bydauto.energy.AbsBYDAutoEnergyListener
        public void onPowerGenerationStateChanged(int i) throws Throwable {
            super.onPowerGenerationStateChanged(i);
            ZKTestActivity zKTestActivity = ZKTestActivity.this;
            zKTestActivity.f(zKTestActivity.f2946a, "onPowerGenerationStateChanged----" + i);
        }

        @Override // b.b.g.k.j, android.hardware.bydauto.energy.AbsBYDAutoEnergyListener
        public void onPowerGenerationValueChanged(int i) throws Throwable {
            super.onPowerGenerationValueChanged(i);
            ZKTestActivity zKTestActivity = ZKTestActivity.this;
            zKTestActivity.f(zKTestActivity.f2946a, "onPowerGenerationValueChanged----" + i);
        }

        @Override // b.b.g.k.j, android.hardware.bydauto.energy.AbsBYDAutoEnergyListener
        public void onPreSaleModelStateChanged(int i) throws Throwable {
            super.onPreSaleModelStateChanged(i);
            ZKTestActivity zKTestActivity = ZKTestActivity.this;
            zKTestActivity.f(zKTestActivity.f2946a, "onPreSaleModelStateChanged----" + i);
        }

        @Override // b.b.g.k.j, android.hardware.bydauto.energy.AbsBYDAutoEnergyListener
        public void onResetCurveDataChanged(int i, int i2) throws Throwable {
            super.onResetCurveDataChanged(i, i2);
            ZKTestActivity zKTestActivity = ZKTestActivity.this;
            zKTestActivity.f(zKTestActivity.f2946a, "onResetCurveDataChanged----" + i + "----" + i2);
        }

        @Override // b.b.g.k.j, android.hardware.bydauto.energy.AbsBYDAutoEnergyListener
        public void onRoadSurfaceChanged(int i) throws Throwable {
            super.onRoadSurfaceChanged(i);
            ZKTestActivity zKTestActivity = ZKTestActivity.this;
            zKTestActivity.f(zKTestActivity.f2946a, "onRoadSurfaceChanged----" + i);
        }

        @Override // b.b.g.k.j, android.hardware.bydauto.energy.AbsBYDAutoEnergyListener
        public void onRoadSurfaceKindChanged(int i) throws Throwable {
            super.onRoadSurfaceKindChanged(i);
            ZKTestActivity zKTestActivity = ZKTestActivity.this;
            zKTestActivity.f(zKTestActivity.f2946a, "onRoadSurfaceKindChanged----" + i);
        }

        @Override // android.hardware.bydauto.energy.AbsBYDAutoEnergyListener
        public void onSOCTargetChanged(int i) throws Throwable {
            super.onSOCTargetChanged(i);
            ZKTestActivity zKTestActivity = ZKTestActivity.this;
            zKTestActivity.f(zKTestActivity.f2946a, "onSOCTargetChanged----" + i);
        }
    }

    public class j extends b.b.g.k.k {
        public j() {
        }

        @Override // b.b.g.k.k, android.hardware.bydauto.engine.AbsBYDAutoEngineListener
        public void onEngineConditionChanged(int i) throws Throwable {
            super.onEngineConditionChanged(i);
            ZKTestActivity zKTestActivity = ZKTestActivity.this;
            zKTestActivity.f(zKTestActivity.f2946a, "onEngineConditionChanged----" + i);
        }

        @Override // b.b.g.k.k, android.hardware.bydauto.engine.AbsBYDAutoEngineListener
        public void onEngineCoolantLevelChanged(int i) throws Throwable {
            super.onEngineCoolantLevelChanged(i);
            ZKTestActivity zKTestActivity = ZKTestActivity.this;
            zKTestActivity.f(zKTestActivity.f2946a, "onEngineCoolantLevelChanged----" + i);
        }

        @Override // b.b.g.k.k, android.hardware.bydauto.engine.AbsBYDAutoEngineListener
        public void onEngineSimulatorVoiceSourceChanged(int i) throws Throwable {
            super.onEngineSimulatorVoiceSourceChanged(i);
            ZKTestActivity zKTestActivity = ZKTestActivity.this;
            zKTestActivity.f(zKTestActivity.f2946a, "onEngineSimulatorVoiceSourceChanged----" + i);
        }

        @Override // b.b.g.k.k, android.hardware.bydauto.engine.AbsBYDAutoEngineListener
        public void onEngineSpeedChanged(int i) throws Throwable {
            super.onEngineSpeedChanged(i);
            ZKTestActivity zKTestActivity = ZKTestActivity.this;
            zKTestActivity.f(zKTestActivity.f2946a, "onEngineSpeedChanged----" + i);
        }

        @Override // b.b.g.k.k, android.hardware.bydauto.engine.AbsBYDAutoEngineListener
        public void onEngineSpeedWarningChanged(int i) throws Throwable {
            super.onEngineSpeedWarningChanged(i);
            ZKTestActivity zKTestActivity = ZKTestActivity.this;
            zKTestActivity.f(zKTestActivity.f2946a, "onEngineSpeedWarningChanged----" + i);
        }

        @Override // b.b.g.k.k, android.hardware.bydauto.engine.AbsBYDAutoEngineListener
        public void onEngineStateChanged(byte[] bArr) throws Throwable {
            super.onEngineStateChanged(bArr);
            ZKTestActivity zKTestActivity = ZKTestActivity.this;
            zKTestActivity.f(zKTestActivity.f2946a, "onEngineStateChanged----" + Arrays.toString(bArr));
        }

        @Override // b.b.g.k.k, android.hardware.bydauto.engine.AbsBYDAutoEngineListener
        public void onEngineVoiceSimulatorStateChanged(int i) throws Throwable {
            super.onEngineVoiceSimulatorStateChanged(i);
            ZKTestActivity zKTestActivity = ZKTestActivity.this;
            zKTestActivity.f(zKTestActivity.f2946a, "onEngineVoiceSimulatorStateChanged----" + i);
        }

        @Override // b.b.g.k.k, android.hardware.bydauto.engine.AbsBYDAutoEngineListener
        public void onOilLevelChanged(int i) throws Throwable {
            super.onOilLevelChanged(i);
            ZKTestActivity zKTestActivity = ZKTestActivity.this;
            zKTestActivity.f(zKTestActivity.f2946a, "onOilLevelChanged----" + i);
        }
    }

    public class k extends b.b.g.k.h {
        public k() {
        }

        @Override // b.b.g.k.h, android.hardware.bydauto.charging.AbsBYDAutoChargingListener
        public void onACChargingLimitChanged(int i) throws Throwable {
            super.onACChargingLimitChanged(i);
            ZKTestActivity zKTestActivity = ZKTestActivity.this;
            zKTestActivity.f(zKTestActivity.f2946a, "onACChargingLimitChanged----" + i);
        }

        @Override // b.b.g.k.h, android.hardware.bydauto.charging.AbsBYDAutoChargingListener
        public void onBMSWakeNoSenseChargeChanged(byte[] bArr) throws Throwable {
            super.onBMSWakeNoSenseChargeChanged(bArr);
            ZKTestActivity zKTestActivity = ZKTestActivity.this;
            zKTestActivity.f(zKTestActivity.f2946a, "onBMSWakeNoSenseChargeChanged----");
        }

        @Override // b.b.g.k.h, android.hardware.bydauto.charging.AbsBYDAutoChargingListener
        public void onBatteryManagementDeviceStateChanged(int i) throws Throwable {
            super.onBatteryManagementDeviceStateChanged(i);
            ZKTestActivity zKTestActivity = ZKTestActivity.this;
            zKTestActivity.f(zKTestActivity.f2946a, "onBatteryManagementDeviceStateChanged----" + i);
        }

        @Override // b.b.g.k.h, android.hardware.bydauto.charging.AbsBYDAutoChargingListener
        public void onBatteryTypeChanged(int i) throws Throwable {
            super.onBatteryTypeChanged(i);
            ZKTestActivity zKTestActivity = ZKTestActivity.this;
            zKTestActivity.f(zKTestActivity.f2946a, "onBatteryTypeChanged----" + i);
        }

        @Override // b.b.g.k.h, android.hardware.bydauto.charging.AbsBYDAutoChargingListener
        public void onCapStateChanged(int i) throws Throwable {
            super.onCapStateChanged(i);
            ZKTestActivity zKTestActivity = ZKTestActivity.this;
            zKTestActivity.f(zKTestActivity.f2946a, "onCapStateChanged----" + i);
        }

        @Override // b.b.g.k.h, android.hardware.bydauto.charging.AbsBYDAutoChargingListener
        public void onCarDischargeLowWarnChanged(int i) throws Throwable {
            super.onCarDischargeLowWarnChanged(i);
            ZKTestActivity zKTestActivity = ZKTestActivity.this;
            zKTestActivity.f(zKTestActivity.f2946a, "onCarDischargeLowWarnChanged----" + i);
        }

        @Override // b.b.g.k.h, android.hardware.bydauto.charging.AbsBYDAutoChargingListener
        public void onCarDischargeStateChanged(int i) throws Throwable {
            super.onCarDischargeStateChanged(i);
            ZKTestActivity zKTestActivity = ZKTestActivity.this;
            zKTestActivity.f(zKTestActivity.f2946a, "onCarDischargeStateChanged----" + i);
        }

        @Override // b.b.g.k.h, android.hardware.bydauto.charging.AbsBYDAutoChargingListener
        public void onCarPlanAccOnStateChanged(int i) throws Throwable {
            super.onCarPlanAccOnStateChanged(i);
            ZKTestActivity zKTestActivity = ZKTestActivity.this;
            zKTestActivity.f(zKTestActivity.f2946a, "onCarPlanAccOnStateChanged----");
        }

        @Override // b.b.g.k.h, android.hardware.bydauto.charging.AbsBYDAutoChargingListener
        public void onCarPlanBatteryHeatStateChanged(int i) throws Throwable {
            super.onCarPlanBatteryHeatStateChanged(i);
            ZKTestActivity zKTestActivity = ZKTestActivity.this;
            zKTestActivity.f(zKTestActivity.f2946a, "onCarPlanBatteryHeatStateChanged----");
        }

        @Override // b.b.g.k.h, android.hardware.bydauto.charging.AbsBYDAutoChargingListener
        public void onCarPlanStateChanged(int i) throws Throwable {
            super.onCarPlanStateChanged(i);
            ZKTestActivity zKTestActivity = ZKTestActivity.this;
            zKTestActivity.f(zKTestActivity.f2946a, "onCarPlanStateChanged----");
        }

        @Override // b.b.g.k.h, android.hardware.bydauto.charging.AbsBYDAutoChargingListener
        public void onChargeLimitChanged(float f2) throws Throwable {
            super.onChargeLimitChanged(f2);
            ZKTestActivity zKTestActivity = ZKTestActivity.this;
            zKTestActivity.f(zKTestActivity.f2946a, "onChargeLimitChanged----" + f2);
        }

        @Override // b.b.g.k.h, android.hardware.bydauto.charging.AbsBYDAutoChargingListener
        public void onChargePreheatStateChanged(int i) throws Throwable {
            super.onChargePreheatStateChanged(i);
            ZKTestActivity zKTestActivity = ZKTestActivity.this;
            zKTestActivity.f(zKTestActivity.f2946a, "onChargePreheatStateChanged----");
        }

        @Override // b.b.g.k.h, android.hardware.bydauto.charging.AbsBYDAutoChargingListener
        public void onChargeStopCapacityStateChanged(int i) throws Throwable {
            super.onChargeStopCapacityStateChanged(i);
            ZKTestActivity zKTestActivity = ZKTestActivity.this;
            zKTestActivity.f(zKTestActivity.f2946a, "onChargeStopCapacityStateChanged----" + i);
        }

        @Override // b.b.g.k.h, android.hardware.bydauto.charging.AbsBYDAutoChargingListener
        public void onChargeStopSwitchStateChanged(int i) throws Throwable {
            super.onChargeStopSwitchStateChanged(i);
            ZKTestActivity zKTestActivity = ZKTestActivity.this;
            zKTestActivity.f(zKTestActivity.f2946a, "onChargeStopSwitchStateChanged----" + i);
        }

        @Override // b.b.g.k.h, android.hardware.bydauto.charging.AbsBYDAutoChargingListener
        public void onChargeTempCtlStateChanged(int i) throws Throwable {
            super.onChargeTempCtlStateChanged(i);
            ZKTestActivity zKTestActivity = ZKTestActivity.this;
            zKTestActivity.f(zKTestActivity.f2946a, "onChargeTempCtlStateChanged----" + i);
        }

        @Override // android.hardware.bydauto.charging.AbsBYDAutoChargingListener
        public void onChargeToggleGuardChanged(int i) throws Throwable {
            super.onChargeToggleGuardChanged(i);
            ZKTestActivity zKTestActivity = ZKTestActivity.this;
            zKTestActivity.f(zKTestActivity.f2946a, "onChargeToggleGuardChanged----" + i);
        }

        @Override // b.b.g.k.h, android.hardware.bydauto.charging.AbsBYDAutoChargingListener
        public void onChargerFaultStateChanged(int i) throws Throwable {
            super.onChargerFaultStateChanged(i);
            ZKTestActivity zKTestActivity = ZKTestActivity.this;
            zKTestActivity.f(zKTestActivity.f2946a, "onChargerFaultStateChanged----" + i);
        }

        @Override // b.b.g.k.h, android.hardware.bydauto.charging.AbsBYDAutoChargingListener
        public void onChargerStateChanged(int i) throws Throwable {
            super.onChargerStateChanged(i);
            ZKTestActivity zKTestActivity = ZKTestActivity.this;
            zKTestActivity.f(zKTestActivity.f2946a, "onChargerStateChanged----" + i);
        }

        @Override // b.b.g.k.h, android.hardware.bydauto.charging.AbsBYDAutoChargingListener
        public void onChargerWorkStateChanged(int i) throws Throwable {
            super.onChargerWorkStateChanged(i);
            ZKTestActivity zKTestActivity = ZKTestActivity.this;
            zKTestActivity.f(zKTestActivity.f2946a, "onChargerWorkStateChanged----" + i);
        }

        @Override // b.b.g.k.h, android.hardware.bydauto.charging.AbsBYDAutoChargingListener
        public void onChargingCapStateChanged(int i, int i2) throws Throwable {
            super.onChargingCapStateChanged(i, i2);
            ZKTestActivity zKTestActivity = ZKTestActivity.this;
            zKTestActivity.f(zKTestActivity.f2946a, "onChargingCapStateChanged----" + i + "----" + i2);
        }

        @Override // b.b.g.k.h, android.hardware.bydauto.charging.AbsBYDAutoChargingListener
        public void onChargingGunStateChanged(int i) throws Throwable {
            super.onChargingGunStateChanged(i);
            ZKTestActivity zKTestActivity = ZKTestActivity.this;
            zKTestActivity.f(zKTestActivity.f2946a, "onChargingGunStateChanged----" + i);
        }

        @Override // b.b.g.k.h, android.hardware.bydauto.charging.AbsBYDAutoChargingListener
        public void onChargingModeChanged(int i) throws Throwable {
            super.onChargingModeChanged(i);
            ZKTestActivity zKTestActivity = ZKTestActivity.this;
            zKTestActivity.f(zKTestActivity.f2946a, "onChargingModeChanged----" + i);
        }

        @Override // b.b.g.k.h, android.hardware.bydauto.charging.AbsBYDAutoChargingListener
        public void onChargingPortLockRebackStateChanged(int i) throws Throwable {
            super.onChargingPortLockRebackStateChanged(i);
            ZKTestActivity zKTestActivity = ZKTestActivity.this;
            zKTestActivity.f(zKTestActivity.f2946a, "onChargingPortLockRebackStateChanged----" + i);
        }

        @Override // b.b.g.k.h, android.hardware.bydauto.charging.AbsBYDAutoChargingListener
        public void onChargingPowerChanged(float f2) throws Throwable {
            super.onChargingPowerChanged(f2);
            ZKTestActivity zKTestActivity = ZKTestActivity.this;
            zKTestActivity.f(zKTestActivity.f2946a, "onChargingPowerChanged----" + f2);
        }

        @Override // b.b.g.k.h, android.hardware.bydauto.charging.AbsBYDAutoChargingListener
        public void onChargingRestTimeChanged(int i, int i2) throws Throwable {
            super.onChargingRestTimeChanged(i, i2);
            ZKTestActivity zKTestActivity = ZKTestActivity.this;
            zKTestActivity.f(zKTestActivity.f2946a, "onChargingRestTimeChanged----" + i + "----" + i2);
        }

        @Override // b.b.g.k.h, android.hardware.bydauto.charging.AbsBYDAutoChargingListener
        public void onChargingScheduleEnableStateChanged(int i) throws Throwable {
            super.onChargingScheduleEnableStateChanged(i);
            ZKTestActivity zKTestActivity = ZKTestActivity.this;
            zKTestActivity.f(zKTestActivity.f2946a, "onChargingScheduleEnableStateChanged----" + i);
        }

        @Override // b.b.g.k.h, android.hardware.bydauto.charging.AbsBYDAutoChargingListener
        public void onChargingScheduleStateChanged(int i) throws Throwable {
            super.onChargingScheduleStateChanged(i);
            ZKTestActivity zKTestActivity = ZKTestActivity.this;
            zKTestActivity.f(zKTestActivity.f2946a, "onChargingScheduleStateChanged----" + i);
        }

        @Override // b.b.g.k.h, android.hardware.bydauto.charging.AbsBYDAutoChargingListener
        public void onChargingScheduleTimeCountDownChanged(int i, int i2) throws Throwable {
            super.onChargingScheduleTimeCountDownChanged(i, i2);
            ZKTestActivity zKTestActivity = ZKTestActivity.this;
            zKTestActivity.f(zKTestActivity.f2946a, "onChargingScheduleTimeCountDownChanged----" + i + "----" + i2);
        }

        @Override // b.b.g.k.h, android.hardware.bydauto.charging.AbsBYDAutoChargingListener
        public void onChargingStateChanged(int i) throws Throwable {
            super.onChargingStateChanged(i);
            ZKTestActivity zKTestActivity = ZKTestActivity.this;
            zKTestActivity.f(zKTestActivity.f2946a, "onChargingStateChanged----" + i);
        }

        @Override // b.b.g.k.h, android.hardware.bydauto.charging.AbsBYDAutoChargingListener
        public void onChargingTimerInfoChanged(ChargingTimerInfo chargingTimerInfo) throws Throwable {
            super.onChargingTimerInfoChanged(chargingTimerInfo);
            ZKTestActivity zKTestActivity = ZKTestActivity.this;
            zKTestActivity.f(zKTestActivity.f2946a, "onChargingTimerInfoChanged----" + chargingTimerInfo.toString());
        }

        @Override // b.b.g.k.h, android.hardware.bydauto.charging.AbsBYDAutoChargingListener
        public void onChargingTypeChanged(int i) throws Throwable {
            super.onChargingTypeChanged(i);
            ZKTestActivity zKTestActivity = ZKTestActivity.this;
            zKTestActivity.f(zKTestActivity.f2946a, "onChargingTypeChanged----" + i);
        }

        @Override // b.b.g.k.h, android.hardware.bydauto.charging.AbsBYDAutoChargingListener
        public void onDisChargeWarningStateChanged(int i) throws Throwable {
            super.onDisChargeWarningStateChanged(i);
            ZKTestActivity zKTestActivity = ZKTestActivity.this;
            zKTestActivity.f(zKTestActivity.f2946a, "onDisChargeWarningStateChanged----" + i);
        }

        @Override // b.b.g.k.h, android.hardware.bydauto.charging.AbsBYDAutoChargingListener
        public void onDischargeConnecting() throws Throwable {
            super.onDischargeConnecting();
            ZKTestActivity zKTestActivity = ZKTestActivity.this;
            zKTestActivity.f(zKTestActivity.f2946a, "onDischargeConnecting----");
        }

        @Override // b.b.g.k.h, android.hardware.bydauto.charging.AbsBYDAutoChargingListener
        public void onDischargeGunConnectCountDown(int i) throws Throwable {
            super.onDischargeGunConnectCountDown(i);
            ZKTestActivity zKTestActivity = ZKTestActivity.this;
            zKTestActivity.f(zKTestActivity.f2946a, "onDischargeGunConnectCountDown----" + i);
        }

        @Override // b.b.g.k.h, android.hardware.bydauto.charging.AbsBYDAutoChargingListener
        public void onDischargeLimitChanged(float f2) throws Throwable {
            super.onDischargeLimitChanged(f2);
            ZKTestActivity zKTestActivity = ZKTestActivity.this;
            zKTestActivity.f(zKTestActivity.f2946a, "onDischargeLimitChanged----" + f2);
        }

        @Override // b.b.g.k.h, android.hardware.bydauto.charging.AbsBYDAutoChargingListener
        public void onDischargeRequestStateChanged(int i) throws Throwable {
            super.onDischargeRequestStateChanged(i);
            ZKTestActivity zKTestActivity = ZKTestActivity.this;
            zKTestActivity.f(zKTestActivity.f2946a, "onDischargeRequestStateChanged----" + i);
        }

        @Override // b.b.g.k.h, android.hardware.bydauto.charging.AbsBYDAutoChargingListener
        public void onDischargeStateChanged(int i, int i2) throws Throwable {
            super.onDischargeStateChanged(i, i2);
            ZKTestActivity zKTestActivity = ZKTestActivity.this;
            zKTestActivity.f(zKTestActivity.f2946a, "onDischargeStateChanged----" + i + "----" + i2);
        }

        @Override // b.b.g.k.h, android.hardware.bydauto.charging.AbsBYDAutoChargingListener
        public void onDischargeTimeChanged(long j) throws Throwable {
            super.onDischargeTimeChanged(j);
            ZKTestActivity zKTestActivity = ZKTestActivity.this;
            zKTestActivity.f(zKTestActivity.f2946a, "onDischargeTimeChanged----" + j);
        }

        @Override // b.b.g.k.h, android.hardware.bydauto.charging.AbsBYDAutoChargingListener
        public void onDischargeToggleChanged(int i) throws Throwable {
            super.onDischargeToggleChanged(i);
            ZKTestActivity zKTestActivity = ZKTestActivity.this;
            zKTestActivity.f(zKTestActivity.f2946a, "onDischargeToggleChanged----" + i);
        }

        @Override // b.b.g.k.h, android.hardware.bydauto.charging.AbsBYDAutoChargingListener
        public void onIntelligentKeepTemModeChanged(int i) throws Throwable {
            super.onIntelligentKeepTemModeChanged(i);
            ZKTestActivity zKTestActivity = ZKTestActivity.this;
            zKTestActivity.f(zKTestActivity.f2946a, "onIntelligentKeepTemModeChanged----" + i);
        }

        @Override // b.b.g.k.h, android.hardware.bydauto.charging.AbsBYDAutoChargingListener
        public void onMovePowerStationToggleChanged(int i) throws Throwable {
            super.onMovePowerStationToggleChanged(i);
            ZKTestActivity zKTestActivity = ZKTestActivity.this;
            zKTestActivity.f(zKTestActivity.f2946a, "onMovePowerStationToggleChanged----" + i);
        }

        @Override // b.b.g.k.h, android.hardware.bydauto.charging.AbsBYDAutoChargingListener
        public void onNoSenseChargeStateChanged(int i) throws Throwable {
            super.onNoSenseChargeStateChanged(i);
            ZKTestActivity zKTestActivity = ZKTestActivity.this;
            zKTestActivity.f(zKTestActivity.f2946a, "onNoSenseChargeStateChanged----");
        }

        @Override // b.b.g.k.h, android.hardware.bydauto.charging.AbsBYDAutoChargingListener
        public void onOBCWakeNoSenseChargeChanged(byte[] bArr) throws Throwable {
            super.onOBCWakeNoSenseChargeChanged(bArr);
            ZKTestActivity zKTestActivity = ZKTestActivity.this;
            zKTestActivity.f(zKTestActivity.f2946a, "onOBCWakeNoSenseChargeChanged----");
        }

        @Override // b.b.g.k.h, android.hardware.bydauto.charging.AbsBYDAutoChargingListener
        public void onPorducePowerWhenLowPowerChanged(int i) throws Throwable {
            super.onPorducePowerWhenLowPowerChanged(i);
            ZKTestActivity zKTestActivity = ZKTestActivity.this;
            zKTestActivity.f(zKTestActivity.f2946a, "onPorducePowerWhenLowPowerChanged----" + i);
        }

        @Override // b.b.g.k.h, android.hardware.bydauto.charging.AbsBYDAutoChargingListener
        public void onSituPowerGenerationStateChanged(int i) throws Throwable {
            super.onSituPowerGenerationStateChanged(i);
            ZKTestActivity zKTestActivity = ZKTestActivity.this;
            zKTestActivity.f(zKTestActivity.f2946a, "onSituPowerGenerationStateChanged----");
        }

        @Override // b.b.g.k.h, android.hardware.bydauto.charging.AbsBYDAutoChargingListener
        public void onSituPowerGenerationSupportChanged(int i) throws Throwable {
            super.onSituPowerGenerationSupportChanged(i);
            ZKTestActivity zKTestActivity = ZKTestActivity.this;
            zKTestActivity.f(zKTestActivity.f2946a, "onSituPowerGenerationSupportChanged----");
        }

        @Override // b.b.g.k.h, android.hardware.bydauto.charging.AbsBYDAutoChargingListener
        public void onSmartChargingStateChanged(int i) throws Throwable {
            super.onSmartChargingStateChanged(i);
            ZKTestActivity zKTestActivity = ZKTestActivity.this;
            zKTestActivity.f(zKTestActivity.f2946a, "onSmartChargingStateChanged----" + i);
        }

        @Override // b.b.g.k.h, android.hardware.bydauto.charging.AbsBYDAutoChargingListener
        public void onSocSaveSwitchChanged(int i) throws Throwable {
            super.onSocSaveSwitchChanged(i);
            ZKTestActivity zKTestActivity = ZKTestActivity.this;
            zKTestActivity.f(zKTestActivity.f2946a, "onSocSaveSwitchChanged----" + i);
        }

        @Override // b.b.g.k.h, android.hardware.bydauto.charging.AbsBYDAutoChargingListener
        public void onVtovChargeBatteryLevelNumChanged(int i) throws Throwable {
            super.onVtovChargeBatteryLevelNumChanged(i);
            ZKTestActivity zKTestActivity = ZKTestActivity.this;
            zKTestActivity.f(zKTestActivity.f2946a, "onVtovChargeBatteryLevelNumChanged----" + i);
        }

        @Override // b.b.g.k.h, android.hardware.bydauto.charging.AbsBYDAutoChargingListener
        public void onVtovDischargeConnectStateChanged(int i) throws Throwable {
            super.onVtovDischargeConnectStateChanged(i);
            ZKTestActivity zKTestActivity = ZKTestActivity.this;
            zKTestActivity.f(zKTestActivity.f2946a, "onVtovDischargeConnectStateChanged----" + i);
        }

        @Override // b.b.g.k.h, android.hardware.bydauto.charging.AbsBYDAutoChargingListener
        public void onVtovDischargeLimitValChanged(int i) throws Throwable {
            super.onVtovDischargeLimitValChanged(i);
            ZKTestActivity zKTestActivity = ZKTestActivity.this;
            zKTestActivity.f(zKTestActivity.f2946a, "onVtovDischargeLimitValChanged----" + i);
        }

        @Override // b.b.g.k.h, android.hardware.bydauto.charging.AbsBYDAutoChargingListener
        public void onVtovDischargeLowestValChanged(int i) throws Throwable {
            super.onVtovDischargeLowestValChanged(i);
            ZKTestActivity zKTestActivity = ZKTestActivity.this;
            zKTestActivity.f(zKTestActivity.f2946a, "onVtovDischargeLowestValChanged----" + i);
        }

        @Override // b.b.g.k.h, android.hardware.bydauto.charging.AbsBYDAutoChargingListener
        public void onVtovDischargeQuantityChanged(float f2) throws Throwable {
            super.onVtovDischargeQuantityChanged(f2);
            ZKTestActivity zKTestActivity = ZKTestActivity.this;
            zKTestActivity.f(zKTestActivity.f2946a, "onVtovDischargeQuantityChanged----" + f2);
        }

        @Override // b.b.g.k.h, android.hardware.bydauto.charging.AbsBYDAutoChargingListener
        public void onWeatherAndTimeRequestChanged(int i) throws Throwable {
            super.onWeatherAndTimeRequestChanged(i);
            ZKTestActivity zKTestActivity = ZKTestActivity.this;
            zKTestActivity.f(zKTestActivity.f2946a, "onWeatherAndTimeRequestChanged----" + i);
        }

        @Override // b.b.g.k.h, android.hardware.bydauto.charging.AbsBYDAutoChargingListener
        public void onWirelessChargingFRStateChanged(int i) throws Throwable {
            super.onWirelessChargingFRStateChanged(i);
            ZKTestActivity zKTestActivity = ZKTestActivity.this;
            zKTestActivity.f(zKTestActivity.f2946a, "onWirelessChargingFRStateChanged----" + i);
        }

        @Override // b.b.g.k.h, android.hardware.bydauto.charging.AbsBYDAutoChargingListener
        public void onWirelessChargingFRSwitchStateChanged(int i) throws Throwable {
            super.onWirelessChargingFRSwitchStateChanged(i);
            ZKTestActivity zKTestActivity = ZKTestActivity.this;
            zKTestActivity.f(zKTestActivity.f2946a, "onWirelessChargingFRSwitchStateChanged----" + i);
        }

        @Override // b.b.g.k.h, android.hardware.bydauto.charging.AbsBYDAutoChargingListener
        public void onWirelessChargingPowerFLStateChanged(int i) throws Throwable {
            super.onWirelessChargingPowerFLStateChanged(i);
            ZKTestActivity zKTestActivity = ZKTestActivity.this;
            zKTestActivity.f(zKTestActivity.f2946a, "onWirelessChargingPowerFLStateChanged----" + i);
        }

        @Override // b.b.g.k.h, android.hardware.bydauto.charging.AbsBYDAutoChargingListener
        public void onWirelessChargingPowerFRStateChanged(int i) throws Throwable {
            super.onWirelessChargingPowerFRStateChanged(i);
            ZKTestActivity zKTestActivity = ZKTestActivity.this;
            zKTestActivity.f(zKTestActivity.f2946a, "onWirelessChargingPowerFRStateChanged----" + i);
        }

        @Override // b.b.g.k.h, android.hardware.bydauto.charging.AbsBYDAutoChargingListener
        public void onWirelessChargingStateChanged(int i) throws Throwable {
            super.onWirelessChargingStateChanged(i);
            ZKTestActivity zKTestActivity = ZKTestActivity.this;
            zKTestActivity.f(zKTestActivity.f2946a, "onWirelessChargingStateChanged----" + i);
        }

        @Override // b.b.g.k.h, android.hardware.bydauto.charging.AbsBYDAutoChargingListener
        public void onWirelessChargingSwitchStateChanged(int i) throws Throwable {
            super.onWirelessChargingSwitchStateChanged(i);
            ZKTestActivity zKTestActivity = ZKTestActivity.this;
            zKTestActivity.f(zKTestActivity.f2946a, "onWirelessChargingSwitchStateChanged----" + i);
        }

        @Override // b.b.g.k.h, android.hardware.bydauto.charging.AbsBYDAutoChargingListener
        public void onWirlessChargingStateChanged(int i) throws Throwable {
            super.onWirlessChargingStateChanged(i);
            ZKTestActivity zKTestActivity = ZKTestActivity.this;
            zKTestActivity.f(zKTestActivity.f2946a, "onWirlessChargingStateChanged----" + i);
        }
    }

    public class l extends b.b.g.k.l {
        public l() {
        }

        @Override // b.b.g.k.l, android.hardware.bydauto.instrument.AbsBYDAutoInstrumentListener
        public void onAlarmBuzzleStateChange(int i) throws Throwable {
            super.onAlarmBuzzleStateChange(i);
            ZKTestActivity zKTestActivity = ZKTestActivity.this;
            zKTestActivity.f(zKTestActivity.f2946a, "onAlarmBuzzleStateChange----" + i);
        }

        @Override // android.hardware.bydauto.instrument.AbsBYDAutoInstrumentListener
        public void onAreaInfoChanged(int i) throws Throwable {
            super.onAreaInfoChanged(i);
            ZKTestActivity zKTestActivity = ZKTestActivity.this;
            zKTestActivity.f(zKTestActivity.f2946a, "onAreaInfoChanged----" + i);
        }

        @Override // b.b.g.k.l, android.hardware.bydauto.instrument.AbsBYDAutoInstrumentListener
        public void onBacklightBrightnessChanged(int i) throws Throwable {
            super.onBacklightBrightnessChanged(i);
            ZKTestActivity zKTestActivity = ZKTestActivity.this;
            zKTestActivity.f(zKTestActivity.f2946a, "onBacklightBrightnessChanged----" + i);
        }

        @Override // b.b.g.k.l, android.hardware.bydauto.instrument.AbsBYDAutoInstrumentListener
        public void onChargeRestTimeChanged(int[] iArr) throws Throwable {
            super.onChargeRestTimeChanged(iArr);
            ZKTestActivity zKTestActivity = ZKTestActivity.this;
            zKTestActivity.f(zKTestActivity.f2946a, "onChargeRestTimeChanged----" + Arrays.toString(iArr));
        }

        @Override // b.b.g.k.l, android.hardware.bydauto.instrument.AbsBYDAutoInstrumentListener
        public void onDayNightModeChanged(int i) throws Throwable {
            super.onDayNightModeChanged(i);
            ZKTestActivity zKTestActivity = ZKTestActivity.this;
            zKTestActivity.f(zKTestActivity.f2946a, "onDayNightModeChanged----" + i);
        }

        @Override // b.b.g.k.l, android.hardware.bydauto.instrument.AbsBYDAutoInstrumentListener
        public void onExternalChargingPowerChanged(float f2) throws Throwable {
            super.onExternalChargingPowerChanged(f2);
            ZKTestActivity zKTestActivity = ZKTestActivity.this;
            zKTestActivity.f(zKTestActivity.f2946a, "onExternalChargingPowerChanged----" + f2);
        }

        @Override // b.b.g.k.l, android.hardware.bydauto.instrument.AbsBYDAutoInstrumentListener
        public void onINSAdasCenterDisplayChanged(int i) throws Throwable {
            super.onINSAdasCenterDisplayChanged(i);
            ZKTestActivity zKTestActivity = ZKTestActivity.this;
            zKTestActivity.f(zKTestActivity.f2946a, "onINSAdasCenterDisplayChanged----" + i);
        }

        @Override // b.b.g.k.l, android.hardware.bydauto.instrument.AbsBYDAutoInstrumentListener
        public void onINSCenterDisplay(int i) throws Throwable {
            super.onINSCenterDisplay(i);
            ZKTestActivity zKTestActivity = ZKTestActivity.this;
            zKTestActivity.f(zKTestActivity.f2946a, "onINSCenterDisplay----" + i);
        }

        @Override // b.b.g.k.l, android.hardware.bydauto.instrument.AbsBYDAutoInstrumentListener
        public void onINSNavDisplayMode(int i) throws Throwable {
            super.onINSNavDisplayMode(i);
            ZKTestActivity zKTestActivity = ZKTestActivity.this;
            zKTestActivity.f(zKTestActivity.f2946a, "onINSNavDisplayMode----" + i);
        }

        @Override // b.b.g.k.l, android.hardware.bydauto.instrument.AbsBYDAutoInstrumentListener
        public void onInstrumentAutoDimmStateChange(int i) throws Throwable {
            super.onInstrumentAutoDimmStateChange(i);
            ZKTestActivity zKTestActivity = ZKTestActivity.this;
            zKTestActivity.f(zKTestActivity.f2946a, "onInstrumentAutoDimmStateChange----" + i);
        }

        @Override // b.b.g.k.l, android.hardware.bydauto.instrument.AbsBYDAutoInstrumentListener
        public void onKeyDetectionReminderChanged(int i) throws Throwable {
            super.onKeyDetectionReminderChanged(i);
            ZKTestActivity zKTestActivity = ZKTestActivity.this;
            zKTestActivity.f(zKTestActivity.f2946a, "onKeyDetectionReminderChanged----" + i);
        }

        @Override // b.b.g.k.l, android.hardware.bydauto.instrument.AbsBYDAutoInstrumentListener
        public void onLowFuelWarnLightColorChanged(int i) throws Throwable {
            super.onLowFuelWarnLightColorChanged(i);
            ZKTestActivity zKTestActivity = ZKTestActivity.this;
            zKTestActivity.f(zKTestActivity.f2946a, "onLowFuelWarnLightColorChanged----" + i);
        }

        @Override // b.b.g.k.l, android.hardware.bydauto.instrument.AbsBYDAutoInstrumentListener
        public void onLowPowerBatteryWarnLightColorChanged(int i) throws Throwable {
            super.onLowPowerBatteryWarnLightColorChanged(i);
            ZKTestActivity zKTestActivity = ZKTestActivity.this;
            zKTestActivity.f(zKTestActivity.f2946a, "onLowPowerBatteryWarnLightColorChanged----" + i);
        }

        @Override // b.b.g.k.l, android.hardware.bydauto.instrument.AbsBYDAutoInstrumentListener
        public void onMaintenanceInfoChanged(int i, int i2) throws Throwable {
            super.onMaintenanceInfoChanged(i, i2);
            ZKTestActivity zKTestActivity = ZKTestActivity.this;
            zKTestActivity.f(zKTestActivity.f2946a, "onMaintenanceInfoChanged----" + i + "----" + i2);
        }

        @Override // b.b.g.k.l, android.hardware.bydauto.instrument.AbsBYDAutoInstrumentListener
        public void onMalfunctionInfoChanged(int i, int i2) throws Throwable {
            super.onMalfunctionInfoChanged(i, i2);
            ZKTestActivity zKTestActivity = ZKTestActivity.this;
            zKTestActivity.f(zKTestActivity.f2946a, "onMalfunctionInfoChanged----" + i + "----" + i2);
        }

        @Override // b.b.g.k.l, android.hardware.bydauto.instrument.AbsBYDAutoInstrumentListener
        public void onMalfunctionInfoChanged2(int i, int i2) throws Throwable {
            super.onMalfunctionInfoChanged2(i, i2);
            ZKTestActivity zKTestActivity = ZKTestActivity.this;
            zKTestActivity.f(zKTestActivity.f2946a, "onMalfunctionInfoChanged2----" + i + "----" + i2);
        }

        @Override // b.b.g.k.l, android.hardware.bydauto.instrument.AbsBYDAutoInstrumentListener
        public void onMileageUnitChanged(int i) throws Throwable {
            super.onMileageUnitChanged(i);
            ZKTestActivity zKTestActivity = ZKTestActivity.this;
            zKTestActivity.f(zKTestActivity.f2946a, "onMileageUnitChanged----" + i);
        }

        @Override // b.b.g.k.l, android.hardware.bydauto.instrument.AbsBYDAutoInstrumentListener
        public void onMilesTypeChanged(int i) throws Throwable {
            super.onMilesTypeChanged(i);
            ZKTestActivity zKTestActivity = ZKTestActivity.this;
            zKTestActivity.f(zKTestActivity.f2946a, "onMilesTypeChanged----" + i);
        }

        @Override // android.hardware.bydauto.instrument.AbsBYDAutoInstrumentListener
        public void onMusicInfoUrlChange(String str, String str2, int i, int i2, String str3) throws Throwable {
            super.onMusicInfoUrlChange(str, str2, i, i2, str3);
            ZKTestActivity zKTestActivity = ZKTestActivity.this;
            zKTestActivity.f(zKTestActivity.f2946a, "onMusicInfoUrlChange----musicName:" + str + ",artistName:" + str2 + ",playState:" + i + ",musicSource:" + i + ",musicSource:" + i2 + ",imgUrl:" + str3);
        }

        @Override // b.b.g.k.l, android.hardware.bydauto.instrument.AbsBYDAutoInstrumentListener
        public void onSpeedUnitChanged(int i) throws Throwable {
            super.onSpeedUnitChanged(i);
            ZKTestActivity zKTestActivity = ZKTestActivity.this;
            zKTestActivity.f(zKTestActivity.f2946a, "onSpeedUnitChanged----" + i);
        }

        @Override // b.b.g.k.l, android.hardware.bydauto.instrument.AbsBYDAutoInstrumentListener
        public void onSpeedUpCountStateChanged(int i) throws Throwable {
            super.onSpeedUpCountStateChanged(i);
            ZKTestActivity zKTestActivity = ZKTestActivity.this;
            zKTestActivity.f(zKTestActivity.f2946a, "onSpeedUpCountStateChanged----" + i);
        }

        @Override // android.hardware.bydauto.instrument.AbsBYDAutoInstrumentListener
        public void onSpeedUpFormInsChange(int i) throws Throwable {
            super.onSpeedUpFormInsChange(i);
            ZKTestActivity zKTestActivity = ZKTestActivity.this;
            zKTestActivity.f(zKTestActivity.f2946a, "onSpeedUpFormInsChange----" + i);
        }

        @Override // b.b.g.k.l, android.hardware.bydauto.instrument.AbsBYDAutoInstrumentListener
        public void onTotalMileageChanged(int i) throws Throwable {
            super.onTotalMileageChanged(i);
            ZKTestActivity zKTestActivity = ZKTestActivity.this;
            zKTestActivity.f(zKTestActivity.f2946a, "onTotalMileageChanged----" + i);
        }

        @Override // b.b.g.k.l, android.hardware.bydauto.instrument.AbsBYDAutoInstrumentListener
        public void onUnitChanged(int i, int i2) throws Throwable {
            super.onUnitChanged(i, i2);
            ZKTestActivity zKTestActivity = ZKTestActivity.this;
            zKTestActivity.f(zKTestActivity.f2946a, "onUnitChanged----" + i + "----" + i2);
        }
    }

    public class m extends b.b.g.k.p {
        public m() {
        }

        @Override // b.b.g.k.p, android.hardware.bydauto.statistic.AbsBYDAutoStatisticListener
        public void onAverageElectricConsumptionChanged(int i, float f2) throws Throwable {
            super.onAverageElectricConsumptionChanged(i, f2);
            ZKTestActivity zKTestActivity = ZKTestActivity.this;
            zKTestActivity.f(zKTestActivity.f2946a, "onAverageElectricConsumptionChanged----" + f2);
        }

        @Override // b.b.g.k.p, android.hardware.bydauto.statistic.AbsBYDAutoStatisticListener
        public void onAverageFuelConsumptionChanged(int i, float f2) throws Throwable {
            super.onAverageFuelConsumptionChanged(i, f2);
            ZKTestActivity zKTestActivity = ZKTestActivity.this;
            zKTestActivity.f(zKTestActivity.f2946a, "onAverageFuelConsumptionChanged----" + f2);
        }

        @Override // b.b.g.k.p, android.hardware.bydauto.statistic.AbsBYDAutoStatisticListener
        public void onAverageSpeedChanged(int i) throws Throwable {
            super.onAverageSpeedChanged(i);
            ZKTestActivity zKTestActivity = ZKTestActivity.this;
            zKTestActivity.f(zKTestActivity.f2946a, "onAverageSpeedChanged----" + i);
        }

        @Override // b.b.g.k.p, android.hardware.bydauto.statistic.AbsBYDAutoStatisticListener
        public void onDrivingRangeValueChanged(int i) throws Throwable {
            super.onDrivingRangeValueChanged(i);
            ZKTestActivity zKTestActivity = ZKTestActivity.this;
            zKTestActivity.f(zKTestActivity.f2946a, "onDrivingRangeValueChanged----" + i);
        }

        @Override // b.b.g.k.p, android.hardware.bydauto.statistic.AbsBYDAutoStatisticListener
        public void onDrivingTimeChanged(double d2) throws Throwable {
            super.onDrivingTimeChanged(d2);
            ZKTestActivity zKTestActivity = ZKTestActivity.this;
            zKTestActivity.f(zKTestActivity.f2946a, "onDrivingTimeChanged----" + d2);
        }

        @Override // b.b.g.k.p, android.hardware.bydauto.statistic.AbsBYDAutoStatisticListener
        public void onEVDrivingMileageConfigChanged(int i) throws Throwable {
            super.onEVDrivingMileageConfigChanged(i);
            ZKTestActivity zKTestActivity = ZKTestActivity.this;
            zKTestActivity.f(zKTestActivity.f2946a, "onEVDrivingMileageConfigChanged----" + i);
        }

        @Override // b.b.g.k.p, android.hardware.bydauto.statistic.AbsBYDAutoStatisticListener
        public void onEVDrivingMileageModeChanged(int i) throws Throwable {
            super.onEVDrivingMileageModeChanged(i);
            ZKTestActivity zKTestActivity = ZKTestActivity.this;
            zKTestActivity.f(zKTestActivity.f2946a, "onEVDrivingMileageModeChanged----" + i);
        }

        @Override // b.b.g.k.p, android.hardware.bydauto.statistic.AbsBYDAutoStatisticListener
        public void onEVMileageValueChanged(int i) throws Throwable {
            super.onEVMileageValueChanged(i);
            ZKTestActivity zKTestActivity = ZKTestActivity.this;
            zKTestActivity.f(zKTestActivity.f2946a, "onEVMileageValueChanged----" + i);
        }

        @Override // android.hardware.bydauto.statistic.AbsBYDAutoStatisticListener
        public void onEVRemainingBatteryPowerChanged(float f2) throws Throwable {
            super.onEVRemainingBatteryPowerChanged(f2);
            ZKTestActivity zKTestActivity = ZKTestActivity.this;
            zKTestActivity.f(zKTestActivity.f2946a, "onEVRemainingBatteryPowerChanged----" + f2);
        }

        @Override // b.b.g.k.p, android.hardware.bydauto.statistic.AbsBYDAutoStatisticListener
        public void onElecDrivingRangeChanged(int i) throws Throwable {
            super.onElecDrivingRangeChanged(i);
            ZKTestActivity zKTestActivity = ZKTestActivity.this;
            zKTestActivity.f(zKTestActivity.f2946a, "onElecDrivingRangeChanged----" + i);
        }

        @Override // b.b.g.k.p, android.hardware.bydauto.statistic.AbsBYDAutoStatisticListener
        public void onElecPercentageChanged(double d2) throws Throwable {
            super.onElecPercentageChanged(d2);
            ZKTestActivity zKTestActivity = ZKTestActivity.this;
            zKTestActivity.f(zKTestActivity.f2946a, "onElecPercentageChanged----" + d2);
        }

        @Override // b.b.g.k.p, android.hardware.bydauto.statistic.AbsBYDAutoStatisticListener
        public void onElectricConsumptionChanged(int i, float f2) throws Throwable {
            super.onElectricConsumptionChanged(i, f2);
            ZKTestActivity zKTestActivity = ZKTestActivity.this;
            zKTestActivity.f(zKTestActivity.f2946a, "onElectricConsumptionChanged----" + f2);
        }

        @Override // b.b.g.k.p, android.hardware.bydauto.statistic.AbsBYDAutoStatisticListener
        public void onEvCurveFlagChanged(int i) throws Throwable {
            super.onEvCurveFlagChanged(i);
            ZKTestActivity zKTestActivity = ZKTestActivity.this;
            zKTestActivity.f(zKTestActivity.f2946a, "onEvCurveFlagChanged----" + i);
        }

        @Override // b.b.g.k.p, android.hardware.bydauto.statistic.AbsBYDAutoStatisticListener
        public void onEvLast200MChanged(int i) throws Throwable {
            super.onEvLast200MChanged(i);
            ZKTestActivity zKTestActivity = ZKTestActivity.this;
            zKTestActivity.f(zKTestActivity.f2946a, "onEvLast200MChanged----" + i);
        }

        @Override // b.b.g.k.p, android.hardware.bydauto.statistic.AbsBYDAutoStatisticListener
        public void onFuelADValueChanged(int i) throws Throwable {
            super.onFuelADValueChanged(i);
            ZKTestActivity zKTestActivity = ZKTestActivity.this;
            zKTestActivity.f(zKTestActivity.f2946a, "onFuelADValueChanged----" + i);
        }

        @Override // b.b.g.k.p, android.hardware.bydauto.statistic.AbsBYDAutoStatisticListener
        public void onFuelConsumptionChanged(int i, float f2) throws Throwable {
            super.onFuelConsumptionChanged(i, f2);
            ZKTestActivity zKTestActivity = ZKTestActivity.this;
            zKTestActivity.f(zKTestActivity.f2946a, "onFuelConsumptionChanged----" + f2);
        }

        @Override // b.b.g.k.p, android.hardware.bydauto.statistic.AbsBYDAutoStatisticListener
        public void onFuelCurveFlagChanged(int i) throws Throwable {
            super.onFuelCurveFlagChanged(i);
            ZKTestActivity zKTestActivity = ZKTestActivity.this;
            zKTestActivity.f(zKTestActivity.f2946a, "onFuelCurveFlagChanged----" + i);
        }

        @Override // b.b.g.k.p, android.hardware.bydauto.statistic.AbsBYDAutoStatisticListener
        public void onFuelDrivingRangeChanged(int i) throws Throwable {
            super.onFuelDrivingRangeChanged(i);
            ZKTestActivity zKTestActivity = ZKTestActivity.this;
            zKTestActivity.f(zKTestActivity.f2946a, "onFuelDrivingRangeChanged----" + i);
        }

        @Override // b.b.g.k.p, android.hardware.bydauto.statistic.AbsBYDAutoStatisticListener
        public void onFuelLast200MChanged(int i) throws Throwable {
            super.onFuelLast200MChanged(i);
            ZKTestActivity zKTestActivity = ZKTestActivity.this;
            zKTestActivity.f(zKTestActivity.f2946a, "onFuelLast200MChanged----" + i);
        }

        @Override // b.b.g.k.p, android.hardware.bydauto.statistic.AbsBYDAutoStatisticListener
        public void onFuelPercentageChanged(int i) throws Throwable {
            super.onFuelPercentageChanged(i);
            ZKTestActivity zKTestActivity = ZKTestActivity.this;
            zKTestActivity.f(zKTestActivity.f2946a, "onFuelPercentageChanged----" + i);
        }

        @Override // b.b.g.k.p, android.hardware.bydauto.statistic.AbsBYDAutoStatisticListener
        public void onHEVMileageValueChanged(int i) throws Throwable {
            super.onHEVMileageValueChanged(i);
            ZKTestActivity zKTestActivity = ZKTestActivity.this;
            zKTestActivity.f(zKTestActivity.f2946a, "onHEVMileageValueChanged----" + i);
        }

        @Override // b.b.g.k.p, android.hardware.bydauto.statistic.AbsBYDAutoStatisticListener
        public void onInstantElecConChanged(float f2) throws Throwable {
            super.onInstantElecConChanged(f2);
            ZKTestActivity zKTestActivity = ZKTestActivity.this;
            zKTestActivity.f(zKTestActivity.f2946a, "onInstantElecConChanged----" + f2);
        }

        @Override // b.b.g.k.p, android.hardware.bydauto.statistic.AbsBYDAutoStatisticListener
        public void onInstantFuelConChanged(float f2) throws Throwable {
            super.onInstantFuelConChanged(f2);
            ZKTestActivity zKTestActivity = ZKTestActivity.this;
            zKTestActivity.f(zKTestActivity.f2946a, "onInstantFuelConChanged----" + f2);
        }

        @Override // b.b.g.k.p, android.hardware.bydauto.statistic.AbsBYDAutoStatisticListener
        public void onKeyBatteryLevelChanged(int i) throws Throwable {
            super.onKeyBatteryLevelChanged(i);
            ZKTestActivity zKTestActivity = ZKTestActivity.this;
            zKTestActivity.f(zKTestActivity.f2946a, "onKeyBatteryLevelChanged----" + i);
        }

        @Override // b.b.g.k.p, android.hardware.bydauto.statistic.AbsBYDAutoStatisticListener
        public void onLast50KmEqualFuelConValueChanged(float f2) throws Throwable {
            super.onLast50KmEqualFuelConValueChanged(f2);
            ZKTestActivity zKTestActivity = ZKTestActivity.this;
            zKTestActivity.f(zKTestActivity.f2946a, "onLast50KmEqualFuelConValueChanged----" + f2);
        }

        @Override // b.b.g.k.p, android.hardware.bydauto.statistic.AbsBYDAutoStatisticListener
        public void onLastElecConPHMChanged(double d2) throws Throwable {
            super.onLastElecConPHMChanged(d2);
            ZKTestActivity zKTestActivity = ZKTestActivity.this;
            zKTestActivity.f(zKTestActivity.f2946a, "onLastElecConPHMChanged----" + d2);
        }

        @Override // b.b.g.k.p, android.hardware.bydauto.statistic.AbsBYDAutoStatisticListener
        public void onLastFuelConPHMChanged(double d2) throws Throwable {
            super.onLastFuelConPHMChanged(d2);
            ZKTestActivity zKTestActivity = ZKTestActivity.this;
            zKTestActivity.f(zKTestActivity.f2946a, "onLastFuelConPHMChanged----" + d2);
        }

        @Override // b.b.g.k.p, android.hardware.bydauto.statistic.AbsBYDAutoStatisticListener
        public void onMessage5sOnlineStateChanged(int i, int i2) throws Throwable {
            super.onMessage5sOnlineStateChanged(i, i2);
            ZKTestActivity zKTestActivity = ZKTestActivity.this;
            zKTestActivity.f(zKTestActivity.f2946a, "onMessage5sOnlineStateChanged----" + i + "----" + i2);
        }

        @Override // b.b.g.k.p, android.hardware.bydauto.statistic.AbsBYDAutoStatisticListener
        public void onMileageNumberChanged(int i, float f2) throws Throwable {
            super.onMileageNumberChanged(i, f2);
            ZKTestActivity zKTestActivity = ZKTestActivity.this;
            zKTestActivity.f(zKTestActivity.f2946a, "onMileageNumberChanged----" + f2);
        }

        @Override // android.hardware.bydauto.statistic.AbsBYDAutoStatisticListener
        public void onRemainingBatteryPowerChanged(float f2) throws Throwable {
            super.onRemainingBatteryPowerChanged(f2);
            ZKTestActivity zKTestActivity = ZKTestActivity.this;
            zKTestActivity.f(zKTestActivity.f2946a, "onRemainingBatteryPowerChanged----" + f2);
        }

        @Override // b.b.g.k.p, android.hardware.bydauto.statistic.AbsBYDAutoStatisticListener
        public void onSOCBatteryPercentageChanged(int i) throws Throwable {
            super.onSOCBatteryPercentageChanged(i);
            ZKTestActivity zKTestActivity = ZKTestActivity.this;
            zKTestActivity.f(zKTestActivity.f2946a, "onSOCBatteryPercentageChanged----" + i);
        }

        @Override // b.b.g.k.p, android.hardware.bydauto.statistic.AbsBYDAutoStatisticListener
        public void onSpeedSignalVDisValueChanged(int i) throws Throwable {
            super.onSpeedSignalVDisValueChanged(i);
            ZKTestActivity zKTestActivity = ZKTestActivity.this;
            zKTestActivity.f(zKTestActivity.f2946a, "onSpeedSignalVDisValueChanged----" + i);
        }

        @Override // b.b.g.k.p, android.hardware.bydauto.statistic.AbsBYDAutoStatisticListener
        public void onTotalElecConChanged(double d2) throws Throwable {
            super.onTotalElecConChanged(d2);
            ZKTestActivity zKTestActivity = ZKTestActivity.this;
            zKTestActivity.f(zKTestActivity.f2946a, "onTotalElecConChanged----" + d2);
        }

        @Override // b.b.g.k.p, android.hardware.bydauto.statistic.AbsBYDAutoStatisticListener
        public void onTotalElecConPHMChanged(double d2) throws Throwable {
            super.onTotalElecConPHMChanged(d2);
            ZKTestActivity zKTestActivity = ZKTestActivity.this;
            zKTestActivity.f(zKTestActivity.f2946a, "onTotalElecConPHMChanged----" + d2);
        }

        @Override // b.b.g.k.p, android.hardware.bydauto.statistic.AbsBYDAutoStatisticListener
        public void onTotalElecConPhmUnitChanged(int i) throws Throwable {
            super.onTotalElecConPhmUnitChanged(i);
            ZKTestActivity zKTestActivity = ZKTestActivity.this;
            zKTestActivity.f(zKTestActivity.f2946a, "onTotalElecConPhmUnitChanged----" + i);
        }

        @Override // b.b.g.k.p, android.hardware.bydauto.statistic.AbsBYDAutoStatisticListener
        public void onTotalFuelConChanged(double d2) throws Throwable {
            super.onTotalFuelConChanged(d2);
            ZKTestActivity zKTestActivity = ZKTestActivity.this;
            zKTestActivity.f(zKTestActivity.f2946a, "onTotalFuelConChanged----" + d2);
        }

        @Override // b.b.g.k.p, android.hardware.bydauto.statistic.AbsBYDAutoStatisticListener
        public void onTotalFuelConPHMChanged(double d2) throws Throwable {
            super.onTotalFuelConPHMChanged(d2);
            ZKTestActivity zKTestActivity = ZKTestActivity.this;
            zKTestActivity.f(zKTestActivity.f2946a, "onTotalFuelConPHMChanged----" + d2);
        }

        @Override // b.b.g.k.p, android.hardware.bydauto.statistic.AbsBYDAutoStatisticListener
        public void onTotalFuelConsumptionUnitChanged(int i) throws Throwable {
            super.onTotalFuelConsumptionUnitChanged(i);
            ZKTestActivity zKTestActivity = ZKTestActivity.this;
            zKTestActivity.f(zKTestActivity.f2946a, "onTotalFuelConsumptionUnitChanged----" + i);
        }

        @Override // b.b.g.k.p, android.hardware.bydauto.statistic.AbsBYDAutoStatisticListener
        public void onTotalMileageValueChanged(float f2) throws Throwable {
            super.onTotalMileageValueChanged(f2);
            ZKTestActivity zKTestActivity = ZKTestActivity.this;
            zKTestActivity.f(zKTestActivity.f2946a, "onTotalMileageValueChanged----" + f2);
        }

        @Override // b.b.g.k.p, android.hardware.bydauto.statistic.AbsBYDAutoStatisticListener
        public void onTravelTimeChanged(int i, float f2) throws Throwable {
            super.onTravelTimeChanged(i, f2);
            ZKTestActivity zKTestActivity = ZKTestActivity.this;
            zKTestActivity.f(zKTestActivity.f2946a, "onTravelTimeChanged----" + f2);
        }

        @Override // b.b.g.k.p, android.hardware.bydauto.statistic.AbsBYDAutoStatisticListener
        public void onWaterTemperatureChanged(int i) throws Throwable {
            super.onWaterTemperatureChanged(i);
            ZKTestActivity zKTestActivity = ZKTestActivity.this;
            zKTestActivity.f(zKTestActivity.f2946a, "onWaterTemperatureChanged----" + i);
        }

        @Override // b.b.g.k.p, android.hardware.bydauto.statistic.AbsBYDAutoStatisticListener
        public void ontKeyBatteryLevelChanged(int i) throws Throwable {
            super.ontKeyBatteryLevelChanged(i);
            ZKTestActivity zKTestActivity = ZKTestActivity.this;
            zKTestActivity.f(zKTestActivity.f2946a, "ontKeyBatteryLevelChanged----" + i);
        }
    }

    public class o extends r {
        public o() {
        }

        @Override // b.b.g.k.r, android.hardware.bydauto.tyre.AbsBYDAutoTyreListener
        public void onIndirectTyreSystemStateChanged(int i) throws Throwable {
            super.onIndirectTyreSystemStateChanged(i);
            ZKTestActivity zKTestActivity = ZKTestActivity.this;
            zKTestActivity.f(zKTestActivity.f2946a, "onIndirectTyreSystemStateChanged----" + i);
        }

        @Override // b.b.g.k.r, android.hardware.bydauto.tyre.AbsBYDAutoTyreListener
        public void onTyreAirLeakStateChanged(int i, int i2) throws Throwable {
            super.onTyreAirLeakStateChanged(i, i2);
            ZKTestActivity zKTestActivity = ZKTestActivity.this;
            zKTestActivity.f(zKTestActivity.f2946a, "onTyreAirLeakStateChanged----" + i + "----" + i2);
        }

        @Override // b.b.g.k.r, android.hardware.bydauto.tyre.AbsBYDAutoTyreListener
        public void onTyreBatteryStateChanged(int i) throws Throwable {
            super.onTyreBatteryStateChanged(i);
            ZKTestActivity zKTestActivity = ZKTestActivity.this;
            zKTestActivity.f(zKTestActivity.f2946a, "onTyreBatteryStateChanged----" + i);
        }

        @Override // b.b.g.k.r, android.hardware.bydauto.tyre.AbsBYDAutoTyreListener
        public void onTyreBatteryValueChanged(int i, float f2) throws Throwable {
            super.onTyreBatteryValueChanged(i, f2);
            ZKTestActivity zKTestActivity = ZKTestActivity.this;
            zKTestActivity.f(zKTestActivity.f2946a, "onTyreBatteryValueChanged----" + i + "----" + f2);
        }

        @Override // b.b.g.k.r, android.hardware.bydauto.tyre.AbsBYDAutoTyreListener
        public void onTyrePressureStateChanged(int i, int i2) throws Throwable {
            super.onTyrePressureStateChanged(i, i2);
            ZKTestActivity zKTestActivity = ZKTestActivity.this;
            zKTestActivity.f(zKTestActivity.f2946a, "onTyrePressureStateChanged----" + i + "----" + i2);
        }

        @Override // b.b.g.k.r, android.hardware.bydauto.tyre.AbsBYDAutoTyreListener
        public void onTyrePressureValueChanged(int i, int i2) throws Throwable {
            super.onTyrePressureValueChanged(i, i2);
            ZKTestActivity zKTestActivity = ZKTestActivity.this;
            zKTestActivity.f(zKTestActivity.f2946a, "onTyrePressureValueChanged----" + i + "----" + i2);
        }

        @Override // android.hardware.bydauto.tyre.AbsBYDAutoTyreListener
        public void onTyrePressureWarnChanged(int i, int i2) throws Throwable {
            super.onTyrePressureWarnChanged(i, i2);
            ZKTestActivity zKTestActivity = ZKTestActivity.this;
            zKTestActivity.f(zKTestActivity.f2946a, "onTyrePressureWarnChanged----" + i + "----" + i2);
        }

        @Override // b.b.g.k.r, android.hardware.bydauto.tyre.AbsBYDAutoTyreListener
        public void onTyreSignalStateChanged(int i, int i2) throws Throwable {
            super.onTyreSignalStateChanged(i, i2);
            ZKTestActivity zKTestActivity = ZKTestActivity.this;
            zKTestActivity.f(zKTestActivity.f2946a, "onTyreSignalStateChanged----" + i + "----" + i2);
        }

        @Override // b.b.g.k.r, android.hardware.bydauto.tyre.AbsBYDAutoTyreListener
        public void onTyreSystemStateChanged(int i) throws Throwable {
            super.onTyreSystemStateChanged(i);
            ZKTestActivity zKTestActivity = ZKTestActivity.this;
            zKTestActivity.f(zKTestActivity.f2946a, "onTyreSystemStateChanged----" + i);
        }

        @Override // b.b.g.k.r, android.hardware.bydauto.tyre.AbsBYDAutoTyreListener
        public void onTyreTemperatureStateChanged(int i) throws Throwable {
            super.onTyreTemperatureStateChanged(i);
            ZKTestActivity zKTestActivity = ZKTestActivity.this;
            zKTestActivity.f(zKTestActivity.f2946a, "onTyreTemperatureStateChanged----" + i);
        }

        @Override // b.b.g.k.r, android.hardware.bydauto.tyre.AbsBYDAutoTyreListener
        public void onTyreTurnStateChanged(int i) throws Throwable {
            super.onTyreTurnStateChanged(i);
            ZKTestActivity zKTestActivity = ZKTestActivity.this;
            zKTestActivity.f(zKTestActivity.f2946a, "onTyreTurnStateChanged----" + i);
        }
    }

    public class p extends b.b.g.k.n {
        public p() {
        }

        @Override // b.b.g.k.n, android.hardware.bydauto.radar.AbsBYDAutoRadarListener
        public void onRadarObstacleDistanceChanged(int i, int i2) throws Throwable {
            super.onRadarObstacleDistanceChanged(i, i2);
            ZKTestActivity zKTestActivity = ZKTestActivity.this;
            zKTestActivity.f(zKTestActivity.f2946a, "onRadarObstacleDistanceChanged----" + i + "----" + i2);
        }

        @Override // b.b.g.k.n, android.hardware.bydauto.radar.AbsBYDAutoRadarListener
        public void onRadarProbeStateChanged(int i, int i2) throws Throwable {
            super.onRadarProbeStateChanged(i, i2);
            ZKTestActivity zKTestActivity = ZKTestActivity.this;
            zKTestActivity.f(zKTestActivity.f2946a, "onRadarProbeStateChanged----" + i + "----" + i2);
        }

        @Override // b.b.g.k.n, android.hardware.bydauto.radar.AbsBYDAutoRadarListener
        public void onReverseRadarSwitchStateChanged(int i) throws Throwable {
            super.onReverseRadarSwitchStateChanged(i);
            ZKTestActivity zKTestActivity = ZKTestActivity.this;
            zKTestActivity.f(zKTestActivity.f2946a, "onReverseRadarSwitchStateChanged----" + i);
        }
    }

    public final void d(Context context) {
        Intent intent = new Intent("com.byd.smartcharge.plan");
        intent.putExtra("plan_use_type", 1);
        intent.putExtra("plan_switch", 1);
        intent.putExtra("goout_switch", 2);
        intent.putExtra("time_start_hour", 23);
        intent.putExtra("time_start_min", 1);
        intent.putExtra("time_end_hour", 2);
        intent.putExtra("time_end_min", 2);
        intent.putExtra("repeat_mode", (byte) Integer.parseInt("0|1|0|1|0|0|0".replace("|", ""), 2));
        intent.putExtra("time_goout_hour_min", 2);
        intent.putExtra("time_goout_min", 3);
        intent.putExtra("goout_repeat_mode", (byte) Integer.parseInt("1|1|0|1|0|1|1".replace("|", ""), 2));
        intent.putExtra("time_eprice_start_hour", 1);
        intent.putExtra("time_eprice_start_min", 2);
        intent.putExtra("time_eprice_end_hour", 3);
        intent.putExtra("time_eprice_end_min", 4);
        intent.putExtra("time_zone", "Europe/London");
        context.sendBroadcast(intent);
    }

    public final void e() {
        ((TextView) findViewById(R.id.tv_energy_get)).setText("1--getEnergyMode()\t获取整车工作模式（仪表显示）\n1--getOperationMode()\t获取整车运行模式\n1--getRoadSurfaceMode()\t获取路面模式命令\n2--getPowerGenerationState()\t获取原地踩油门发电状态\n2--getPowerGenerationValue()\t获取原地踩油门发电功率\n2--getEPedalState()\t E-Pedal模式\n3--getElectricShowMode()\t能量显示方式\n4--getMandatoryElectricState()\t智能保电/强制保电\n5--getElectricPointFlag()\t获取纯电车型电耗曲线打点标志位\n5--getLast200MElectricCon()\t获取纯电车型最近200m电耗\n");
        ((TextView) findViewById(R.id.tv_energy_get2)).setText("6--getLast50KMOilConAvg()\t最近50km平均油耗\n6--getLast50KMElectricConAvg()\t最近50km平均电耗\n7--getDriveDistanceShowMode()\t续驶里程显示模式\n8--getDriveDistanceShowStandard()\t 续里程显示标准\n8--getPreSaleModelState()\t售前模式状态\n9--getMemorySportState()\tSPORT模式记忆（打开/关闭）\n9--getMemoryMaxEvState()\tMAXEV模式记忆（打开/关闭）\n9--getMemoryElectricState()\t强制保电记忆开关（打开/关闭）\n9--getOilPointFlag()\t获取油耗曲线打点标志位\n9--getLast200MOilCon()\t获取最近200m油耗\n10--getEnergyState()\t整车能量状态");
        ((TextView) findViewById(R.id.tv_energy_set)).setText("1--setEPedalState(int value)\n2--setMemorySportState(int value)\n3--setMemoryElectricState(int value)\n4--setMemoryMaxEvState(int value)\n5--setElectricShowMode(int value)\n6--resetCurveData(int type)\t曲线数据清零\n7--setDriveDistanceShowMode(int value)\n8--setMandatoryElectricState(int value)\n9--setDriveDistanceShowStandard(int value)\n10--setPreSaleModelState(int state)");
        ((TextView) findViewById(R.id.tv_charge_get)).setText("1--getChargerFaultState()\t获取车载充电器故障状态\n5--getChargingRestTime()\t获取当前充满电剩余时间小时，分\n6--getChargingCapState(int type)\t获取充电口盖开关状态\n7--getChargingPortLockRebackState()\t获取交流充电口电锁执行反馈\n8--getDischargeRequestState()\t获取放电请求状态\n8--getChargerState()\t获取充电器状态\n8--getChargingScheduleEnableState()\t获取定时充电功能状态\n8--getChargingScheduleState()\t获取预约充电状态\n9--getChargingGunState()\t获取充电枪连接状态\n9--getBatteryManagementDeviceState()\t获取动力电池管理器与外接充电设备当前状态\n10--getChargingPower()\t获取充电功率\n11--getChargeTempCtlState()\t获取充电智能维温模式\n13--getChargeStopCapacityState()\t获取充电限制值\n14--getMovePowerStationToggle()\t获取移动电站开启/关闭状态\n14--getVtovDischargeConnectState()\t获取放电枪的连接状态\n14--getVtovDischargeQuantity()\t获取VTOV已放电量\n15--getDischargeToggle()\t获取对外放电开关状态\n16--getDischargeTime()\t获取单次放电时长设置\n");
        ((TextView) findViewById(R.id.tv_charge_get2)).setText("17--getPorducePowerWhenLowPower()\t获取电量过低时，启动发动机发电开关\n18--getWirelessChargingSwitchState()\t获取手机无线充电开关状态\n18--getWirelessChargingState()\t获取手机无线充电状态\n19--getVtovChargeBatteryLevelNum()\t获取当前充电车辆电量百分比\n19--getVtovChargeBatteryLevelNumLimit()\t获取当前充电车辆最高截止电量百分比\n19--getDischargeRemainTime()\t预计放电结束剩余时间-min\n19--getChargeVoltageMax()\t单体动力蓄电池最高允许充电电压\n19--getVoltageMax()\t最高单体动力蓄电池电压\n20--getDischargeVoltage()\t获取放电车输出电压\n20--getDischargeElectric()\t获取放电车输出电流\n20--getDischargeGunTimer()\t获取放电枪连接倒计时分钟\n20--getDischargeTotalTime()\t获取放电车放电时长\n20--getDischargeOverReason()\t获取放电结束具体原因\n21--getChargeVoltage()\t获取充电车需求电压\n21--getChargeElectric()\t获取充电车需求电流\n21--getChargeBatteryLevelBegin()\t获取充电起始SOC\n21--getChargeBatteryLimitTemp()\t获取充电车最高允许温度\n21--getChargeBatteryTemp()\t获取充电车最高动力蓄电池温度\n21--getBatteryType()\t获取充电车电池类型\n22--getVtovDischargeLimitVal()\t获取VTOV放电限值\n22-getVtovDischargeLowestVal()\t获取VTOV最低放电限值");
        ((TextView) findViewById(R.id.tv_charge_set)).setText("1--setChargeStopCapacityState(int state)\t设置充电限制值\n2--sendVtovDischargeLimitVal(int value)\t设置VTOV放电限值\n3--setMovePowerStationToggle(int mode)\t设置移动电站开启/关闭状态\n4--setHEVChargingType()\t设置混动车充电模式\n5--setChargeTempCtlState(int mode)\t设置充电智能维温模式\n6--setDischargeToggle(int state)\t设置对外放电开关状态\n7--setPorducePowerWhenLowPower(int state)\t设置电量过低时，启动发动机发电开关\n8--setDischargeTime(long time)\t设置单次放电时长设置\n9--setWirelessChargingSwitchState(int state)\t设置手机无线充电开关状态\n");
        ((TextView) findViewById(R.id.tv_health_get)).setText("1--getOilHint()\t获取机油提示\n1--getOilUpdateState()\t获取机油更新操作允许标志(不允许更新/允许更新)\n2--getOilLifetime(int type)\t获取各类型油液寿命百分比\n2--getOilMaintainanceHint(int type)\t获取各类型机油立即保养提醒\n3--getOilLevel()\t获取机油油位\n4--getFrontWiperCheckState()\t获取前雨刮检修\n4--getRearWiperCheckState()\t获取后雨刮检修\n");
        ((TextView) findViewById(R.id.tv_health_get2)).setText("5--getMileageValid()\t总里程有效位(有效/无效)\n6--getTyreFaultLocation()\t是否能指示气压异常轮胎位置\n7--getEPBConfig()\t获取EPB开关配置\n7--getEPBType()\t获取EPB类型\n7--getEPBState()\t获取EPB 拖车模式开关\ngetTransportMode(int state)\t获取运输模式\ngetTransportModeStateHint(int state)\t获取运输模式状态提醒");
        ((TextView) findViewById(R.id.tv_health_set)).setText("1-- setFrontWiperCheckState( int state)设置前雨刮检修\n2-- setRearWiperCheckState( int state)设置后雨刮检修\n3-- resetOilLifetime( int type)重置各类型油液寿命百分比（重置到100 %）\n4-- setOilUpdateTag( int value)设置请求机油更新标志\n5-- setEPBState( int state)设置EPB 拖车模式开关\n6--setTransportMode( int state)设置运输模式\n7--setPowerOffChargedState( int state)设置断电节点荷电状态");
        ((TextView) findViewById(R.id.tv_ins_get)).setText("1--getBacklightBrightness()\t整车/仪表亮度调节（背光手动调光共11档，0-10裆）\n2--getInstrumentAutoDimmState()\t整车/仪表背光自动调光（打开（自动）/关闭（手动））\n3--getMilesType()\t获取当前显示的行程数类型\n3--getSpeedUpCountState()\t获取加速器记时器状态\n4--getExternalChargingPower()\t获取外接充电量\ngetMaintenanceInfo(int typeName)\t获取保养时间,保养里程\n6--getUnit(int unitName)\t获取温度单位\n7--getAlarmBuzzleState()\t获取蜂鸣器状态\n8--getMalfunctionInfo(int typeName)\t获取故障提示信息\n8--getMalfunctionInfos()\t获取所有设备功能模块");
        ((TextView) findViewById(R.id.tv_ins_get2)).setText("9--getTotalMileage()\t获取总里程\n9--getMileageUnit()\t获取里程单位\ngetINSNavDisplayMode()\t获取仪表导航显示\ngetINSAdasCenterDisplay()\t获取仪表中心显示ADAS\ngetINSCenterDisplay()\t获取仪表中心显示的内容\ngetDayNightMode()\t获取仪表屏深浅模式（白天黑夜模式）\ngetInstrumentScreenType()\t获取屏幕类型\ngetInstrumentConfigure()\t获取仪表屏幕配置\n11--getSportModeState()\t获取运动状态开关是否已经打开\n12--getSpeedUnit()\t获取速度单位");
        ((TextView) findViewById(R.id.tv_ins_set)).setText("1--setBacklightBrightness(int brightness)\n2--setInstrumentAutoDimmState(int value)\n3--setMilesType(int type)\t设置当前显示的行程数类型\n4--setMaintenanceInfo(int typeName,int infoValue)\t设置保养时间,保养里程\n5--setUnit(int unitName,int unitValue)\t设置温度，气压，油耗距离，功率单位\n6--setINSNavDisplayMode(int value)\t设置仪表导航显示\n7--setINSAdasCenterDisplay(int value)\t设置仪表中心是显示ADAS\n8--setINSCenterDisplay(int value )\t设置仪表中心显示的内容\n9--setSpeedUpCountState(int state)\t仪表显示加速计时器\n10--setDayNightMode(int mode)\t设置仪表屏深浅模式（白天黑夜模式）\n");
        ((TextView) findViewById(R.id.tv_static_get)).setText("1--getTotalElecConPHMValue()\t获取累计平均电耗\n1--getTotalFuelConValue()\t获取燃油消耗总量\n2--getElecDrivingRangeValue()\t获取电续驶里程\n2--getTotalMileageValue()\t获取总里程\n3--getFuelDrivingRangeValue()\t获取燃油续驶里程\n3--getHEVMileageValue()\t获取HEV里程\n3--getEVMileageValue()\t获取EV里程\n4--getElecPercentageValue()\t获取电量百分比\n4--getAverageSpeedValue()\t获取平均车速");
        ((TextView) findViewById(R.id.tv_static_get2)).setText("5--getFuelPercentageValue()\t获取燃油百分比\n5--getTotalFuelConPHMValue()\t获取累计平均油耗\n6--getDrivingTimeValue()\t获取行驶时间\n9--getTotalElecConValue()\t获取电消耗总量\n10--getKeyBatteryLevel()\t获取钥匙电量\n11-getMileageNumber(int status)\t获取不同类型的行程数\ngetLastElecConPHMValue()\t获取最近100公里电耗\ngetLastFuelConPHMValue()\t获取最近100公里油耗");
        ((TextView) findViewById(R.id.tv_static_set)).setText("1--resetAverageSpeed(int value)\t清零平均车速\n2--resetDrivingTimeValue(int value)\t清零行驶时间\n3--resetExtChargeCapacity(int value)\t清零外接充电电量\n4--resetTotalFuelConValue(int value)\t清零燃油消耗量");
        ((TextView) findViewById(R.id.tv_engine_get)).setText("1--getEngineCondition()\t获取发动机状态\n2--getEngineCode()\t获取发动机型号\n3--getEnginePower()\t获取功率（发动机和电机）\n4--getEngineSpeed()\t获取发动机转速\n5--getEngineDisplacement\t获取发动机排量\n6--getEngineCoolantLevel()\t获取冷却液位\n7--getOilLevel()\t获取燃油油位信号");
        ((TextView) findViewById(R.id.tv_radar_get)).setText("1--getRadarProbeState(int area)\t获取各位置探头状态\n2--getAllRadarProbeStates()\t获取所有位置的探头状态\n3--getRadarObstacleDistance(int area)\t获取各位置探头离障碍物距离\n4--getAllRadarDistance()\t获取所有位置的探头离障碍物距离\n5--getReverseRadarSwitchState\t获取倒车雷达开关状态");
        ((TextView) findViewById(R.id.tv_radar_set)).setText("setReverseRadarSwitchState\t设置倒车雷达开关状态");
        ((TextView) findViewById(R.id.tv_tyre_get)).setText("1--getTyreAirLeakState(int area)\t获取各轮胎漏气状态\n2--getTyreBatteryState()\t获取胎压系统电池电量状态\n3--getTyrePressureState(int area)\t获取各轮胎压力状态\n4--getTyrePressureValue(int area)\t获取各轮胎压力值\n5--getTyreSignalState(int area)\t获取各轮胎信号状态\n");
        ((TextView) findViewById(R.id.tv_tyre_get2)).setText("6--getTyreSystemState()\t获取胎压系统状态\n7--getTyreTemperatureState()\t获取胎压系统温度状态\n8--getIndirectTyreSystemState()\t获取胎压复位状态\n9--getTyreBatteryValue(int area)\t获取胎压系统电池电量值\n10--getTyreTurnState()\t获取车轮转向状态");
        ((TextView) findViewById(R.id.tv_tyre_set)).setText("setIndirectTyreConfirm(int value)\t胎压复位");
        ((TextView) findViewById(R.id.tv_sensor_get)).setText("1--getLightIntensity() 获取光照（环境光）强度等级\n2--getTemperatureSensorValue() 获取温度传感器值\n3--getHumiditySensorValue() 获取湿度传感器值\n4--getSlope() 获取斜率\n5--getSensorAxValue() 获取传感器轴值");
        ((TextView) findViewById(R.id.tv_sensor_set)).setText("setOrientationAngle( int value)设置方向角度");
        ((TextView) findViewById(R.id.tv_gearbox_get)).setText("1--getGearboxAutoModeType() 获取自动变速箱档位(0x242)\n2--getGear()获取自动变速箱档位(0x342) ");
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Removed duplicated region for block: B:44:0x0099 A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /* JADX WARN: Type inference failed for: r2v0, types: [java.lang.String] */
    /* JADX WARN: Type inference failed for: r2v1 */
    /* JADX WARN: Type inference failed for: r2v12 */
    /* JADX WARN: Type inference failed for: r2v13 */
    /* JADX WARN: Type inference failed for: r2v14 */
    /* JADX WARN: Type inference failed for: r2v15 */
    /* JADX WARN: Type inference failed for: r2v16 */
    /* JADX WARN: Type inference failed for: r2v17 */
    /* JADX WARN: Type inference failed for: r2v2, types: [java.io.BufferedWriter] */
    /* JADX WARN: Type inference failed for: r2v3 */
    /* JADX WARN: Type inference failed for: r2v4 */
    /* JADX WARN: Type inference failed for: r2v5 */
    /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:32:0x008e -> B:41:0x0095). Please report as a decompilation issue!!! */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final void f(java.lang.String r5, java.lang.String r6) throws java.lang.Throwable {
        /*
            r4 = this;
            com.byd.carsettings.ui.activity.ZKTestActivity$g r0 = new com.byd.carsettings.ui.activity.ZKTestActivity$g
            r0.<init>(r6)
            r4.runOnUiThread(r0)
            java.text.SimpleDateFormat r0 = new java.text.SimpleDateFormat
            java.util.Locale r1 = java.util.Locale.US
            java.lang.String r2 = "yyyy-MM-dd_HH-mm-ss SSS"
            r0.<init>(r2, r1)
            java.util.Date r1 = new java.util.Date
            r1.<init>()
            java.lang.String r0 = r0.format(r1)
            com.bydux.api.common.tools.log.BydLogHelper.logD(r5, r6)
            java.lang.String r5 = r4.f2950e
            if (r5 != 0) goto L22
            return
        L22:
            java.io.File r5 = new java.io.File
            java.lang.String r1 = r4.f2950e
            r5.<init>(r1)
            boolean r1 = r5.exists()
            if (r1 != 0) goto L32
            r5.mkdirs()
        L32:
            r5 = 0
            java.io.FileOutputStream r1 = new java.io.FileOutputStream     // Catch: java.lang.Throwable -> L69 java.io.IOException -> L6d java.io.FileNotFoundException -> L7d
            java.lang.String r2 = r4.f2951f     // Catch: java.lang.Throwable -> L69 java.io.IOException -> L6d java.io.FileNotFoundException -> L7d
            r3 = 1
            r1.<init>(r2, r3)     // Catch: java.lang.Throwable -> L69 java.io.IOException -> L6d java.io.FileNotFoundException -> L7d
            java.io.BufferedWriter r2 = new java.io.BufferedWriter     // Catch: java.lang.Throwable -> L69 java.io.IOException -> L6d java.io.FileNotFoundException -> L7d
            java.io.OutputStreamWriter r3 = new java.io.OutputStreamWriter     // Catch: java.lang.Throwable -> L69 java.io.IOException -> L6d java.io.FileNotFoundException -> L7d
            r3.<init>(r1)     // Catch: java.lang.Throwable -> L69 java.io.IOException -> L6d java.io.FileNotFoundException -> L7d
            r2.<init>(r3)     // Catch: java.lang.Throwable -> L69 java.io.IOException -> L6d java.io.FileNotFoundException -> L7d
            java.lang.StringBuilder r5 = new java.lang.StringBuilder     // Catch: java.io.IOException -> L65 java.io.FileNotFoundException -> L67 java.lang.Throwable -> L96
            r5.<init>()     // Catch: java.io.IOException -> L65 java.io.FileNotFoundException -> L67 java.lang.Throwable -> L96
            r5.append(r0)     // Catch: java.io.IOException -> L65 java.io.FileNotFoundException -> L67 java.lang.Throwable -> L96
            java.lang.String r0 = ":"
            r5.append(r0)     // Catch: java.io.IOException -> L65 java.io.FileNotFoundException -> L67 java.lang.Throwable -> L96
            r5.append(r6)     // Catch: java.io.IOException -> L65 java.io.FileNotFoundException -> L67 java.lang.Throwable -> L96
            java.lang.String r6 = "\n"
            r5.append(r6)     // Catch: java.io.IOException -> L65 java.io.FileNotFoundException -> L67 java.lang.Throwable -> L96
            java.lang.String r5 = r5.toString()     // Catch: java.io.IOException -> L65 java.io.FileNotFoundException -> L67 java.lang.Throwable -> L96
            r2.write(r5)     // Catch: java.io.IOException -> L65 java.io.FileNotFoundException -> L67 java.lang.Throwable -> L96
            r2.close()     // Catch: java.io.IOException -> L8d
            goto L95
        L65:
            r5 = move-exception
            goto L70
        L67:
            r5 = move-exception
            goto L80
        L69:
            r6 = move-exception
            r2 = r5
            r5 = r6
            goto L97
        L6d:
            r6 = move-exception
            r2 = r5
            r5 = r6
        L70:
            java.lang.String r5 = r5.getMessage()     // Catch: java.lang.Throwable -> L96
            com.bydux.api.common.tools.log.BydLogHelper.logE(r5)     // Catch: java.lang.Throwable -> L96
            if (r2 == 0) goto L95
            r2.close()     // Catch: java.io.IOException -> L8d
            goto L95
        L7d:
            r6 = move-exception
            r2 = r5
            r5 = r6
        L80:
            java.lang.String r5 = r5.getMessage()     // Catch: java.lang.Throwable -> L96
            com.bydux.api.common.tools.log.BydLogHelper.logE(r5)     // Catch: java.lang.Throwable -> L96
            if (r2 == 0) goto L95
            r2.close()     // Catch: java.io.IOException -> L8d
            goto L95
        L8d:
            r5 = move-exception
            java.lang.String r5 = r5.getMessage()
            com.bydux.api.common.tools.log.BydLogHelper.logE(r5)
        L95:
            return
        L96:
            r5 = move-exception
        L97:
            if (r2 == 0) goto La5
            r2.close()     // Catch: java.io.IOException -> L9d
            goto La5
        L9d:
            r6 = move-exception
            java.lang.String r6 = r6.getMessage()
            com.bydux.api.common.tools.log.BydLogHelper.logE(r6)
        La5:
            throw r5
        */
        throw new UnsupportedOperationException("Method not decompiled: com.byd.carsettings.ui.activity.ZKTestActivity.f(java.lang.String, java.lang.String):void");
    }

    /* JADX WARN: Unexpected iteration count in SwitchBreakVisitor. Please report as an issue */
    @Override // android.view.View.OnClickListener
    public void onClick(View view) throws Throwable {
        String string = ((EditText) findViewById(R.id.et_params_1)).getText().toString();
        String string2 = ((EditText) findViewById(R.id.et_params_2)).getText().toString();
        String string3 = ((EditText) findViewById(R.id.et_params_set)).getText().toString();
        String string4 = ((EditText) findViewById(R.id.et_params_get)).getText().toString();
        try {
            if (!TextUtils.isEmpty(string)) {
                this.s = Integer.parseInt(string);
            }
            if (!TextUtils.isEmpty(string2)) {
                this.t = Integer.parseInt(string2);
            }
            if (!TextUtils.isEmpty(string3)) {
                this.r = Integer.parseInt(string3);
            }
            if (!TextUtils.isEmpty(string4)) {
                this.q = Integer.parseInt(string4);
            }
        } catch (Exception e2) {
            BydLogHelper.logE(e2.getMessage());
        }
        int id = view.getId();
        switch (id) {
            case R.id.btn_add /* 2131296453 */:
                this.q++;
                ((EditText) findViewById(R.id.et_params_get)).setText(this.q + "");
                break;
            case R.id.btn_add0 /* 2131296454 */:
                this.r++;
                ((EditText) findViewById(R.id.et_params_set)).setText(this.r + "");
                break;
            case R.id.btn_add3 /* 2131296455 */:
                this.s++;
                ((EditText) findViewById(R.id.et_params_1)).setText(this.s + "");
                break;
            case R.id.btn_add4 /* 2131296456 */:
                this.t++;
                ((EditText) findViewById(R.id.et_params_2)).setText(this.t + "");
                break;
            default:
                switch (id) {
                    case R.id.btn_autostudy /* 2131296458 */:
                        d(getApplicationContext());
                        String str = (((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((("[关键配置]\n[车型代号]--[getAutoType]:" + b.b.g.n.q.b.c().a()) + "\n[DM平台]--[getDMType]:" + u.I().r()) + "\n[整车驱动]--[getDriveConfig]:" + u.I().x()) + "\n[域控类型]--[getCarType]:" + u.I().i()) + "\n[智能维温]--[IntelligentKeepTem]:" + u.I().L()) + "\n[满电解锁]--[ChargeToaggleGuard]:" + u.I().m()) + "\n[充电限值]--[getChargeLimitSupport]:" + u.I().j()) + "\n[OBC类型]--[getOBCType]:" + u.I().S()) + "\n[车外放电]--[getDischargeSupport]:" + u.I().w()) + "\n[续驶标准]--[DriveDistanceShowStandard]:" + u.I().w()) + "\n[EPEDAL]--[getEPedalStateSupport]:" + u.I().C()) + "\n[续驶模式]--[DriveDistanceShowMode]:" + u.I().z()) + "\n[智慧充电]--[SmartChargingPHEV]:" + u.I().l0()) + "\n[极限续航]--[getSupportExtreme]:" + u.I().f0()) + "\n[预约用车]--[getCarPlanSupport]:" + u.I().h()) + "\n[无感充电]--[getNoSenseChargeSupport]:" + u.I().R()) + "\n[充电预热]--[getChargePreheatSupport]:" + u.I().l()) + "\n[EPB开关]--[getEPBConfig]:" + b.b.g.n.q.s.g().b()) + "\n[EPB类型]--[getEPBType]:" + b.b.g.n.q.s.g().e()) + "\n[机油更新]--[getOilUpdateState]:" + b.b.g.n.q.s.g().l()) + "\n[EPB提醒]--[getEPBHint]:" + b.b.g.n.q.s.g().c()) + "\n[整车能量]--[getEnergyState]:" + b.b.g.n.q.e.j().g()) + "\n[车轮转向]--[getTyreTurnState]:" + b.b.g.n.q.r.b().o()) + "\n[当前电量]--[getElecPercentageValue]:" + b.b.g.n.q.o.m().i()) + "\n[电续驶里程]--[ElecDrivingRangeByStandard]:" + b.b.g.n.q.o.m().g()) + "\n[HEV里程]--[getHEVMileageValue]:" + b.b.g.n.q.o.m().l()) + "\n[EV里程]--[getEVMileageValue]:" + b.b.g.n.q.o.m().e()) + "\n[充电枪]--[getChargingGunState]:" + b.b.g.n.q.c.K().v()) + "\n[电池管理]--[BatteryManagement]:" + b.b.g.n.q.c.K().c()) + "\n[定时充电]--[ChargingScheduleEnable]:" + b.b.g.n.q.c.K().z()) + "\n[放电枪]--[VtovDischargeConnect]:" + b.b.g.n.q.c.K().U()) + "\n[原地补电]--[SituPowerGeneration]:" + b.b.g.n.q.c.K().Q()) + "\n[充电预热允许]--[getChargePreheatAllow]:" + b.b.g.n.q.c.K().l()) + "\n[电量低提示]--[VtovDischargeLowPowerHint]:" + b.b.g.n.q.c.K().W()) + "\n[纯电电打点]--[getElectricPointFlag]:" + b.b.g.n.q.e.j().d()) + "\n[混动电打点]--[getHEVElectricPointFlag]:" + b.b.g.n.q.e.j().h()) + "\n[油耗打点]--[getOilPointFlag]:" + b.b.g.n.q.e.j().s()) + "\n[混动电锁]--[getHevChargingPort]:" + b.b.g.n.q.m.p0().e0()) + "\n[纯电电锁]--[getChargingPort]:" + b.b.g.n.q.m.p0().u()) + "\n[前雨刮]--[getFrontWiperCheckState]:" + b.b.g.n.q.s.g().f()) + "\n[后雨刮]--[getRearWiperCheckState]:" + b.b.g.n.q.s.g().m()) + "\n[机油油位]--[getOilLevel]:" + b.b.g.n.q.s.g().j()) + "\n[总电量值]--[getBatteryPackEnergy]:" + b.b.g.n.q.o.m().c()) + "\n[回馈强度]--[getEnergyFeedback]:" + b.b.g.n.q.m.p0().L()) + "\n[里程单位]--[getMileageUnit]:" + b.b.g.n.q.h.m().v()) + "\n[速度单位]--[getSpeedUnit]:" + b.b.g.n.q.h.m().x()) + "\n[功率]--[POWER_UNIT]:" + b.b.g.n.q.h.m().C(3)) + "\n[油耗单位]--[FUEL_CONSUMPTION_AND_DISTANCE_UNIT]:" + b.b.g.n.q.h.m().C(2)) + "\n[累计平均电耗]--[getTotalElecConPHMValue]:" + b.b.g.n.q.o.m().q()) + "\n[累计平均油耗]--[getTotalFuelConPHMValue]:" + b.b.g.n.q.o.m().s()) + "\n[50KM电耗]--[getLast50KMElectricConAvg]:" + b.b.g.n.q.e.j().m()) + "\n[50KM油耗]--[getLast50KMOilConAvg]:" + b.b.g.n.q.e.j().n()) + "\n[EV里程]--[getEVMileageValue]:" + b.b.g.n.q.o.m().e()) + "\n[平均车速]--[getAverageSpeedValue]:" + b.b.g.n.q.o.m().b()) + "\n[总里程]--[getTotalMileageValue]:" + b.b.g.n.q.o.m().u()) + "\n[总油耗]--[getTotalFuelConValue]:" + b.b.g.n.q.o.m().t()) + "\n[电续驶里程]--[getElecDrivingRangeValue]:" + b.b.g.n.q.o.m().h()) + "\n[行驶时间]--[getDrivingTimeValue]:" + b.b.g.n.q.o.m().d()) + "\n[纯电P档]--[getGear]:" + b.b.g.n.q.g.g().b()) + "\n[混动P档]--[getGearboxAutoModeType]:" + b.b.g.n.q.g.g().d()) + "\n[剩余电量值]--[getEVRemainingBatteryPower]:" + b.b.g.n.q.o.m().f()) + "\n[左域平台]--[getLeftPlatformConfigs]:" + u.I().M()) + "\n[充电限值]--[getSupportACChargingLimit]:" + u.I().d0()) + "\n[SLW]--[getSupportSLW]:" + BYDAutoStudy.getInstance(this).getSupportSLW()) + "\n[CAN在线]--[HAS_0x010D00]:" + b.b.g.n.q.b.c().e(49)) + "\n[CAN在线]--[HAS_0x043800]:" + b.b.g.n.q.b.c().e(32)) + "\n[CAN在线]--[HAS_0x024A00]:" + b.b.g.n.q.b.c().e(53)) + "\n[CAN在线]--[HAS_0x034200]:" + b.b.g.n.q.b.c().e(52)) + "\n[CAN在线]--[HAS_0X034700]:" + b.b.g.n.q.b.c().e(151)) + "\n[CAN在线]--[HAS_0x030D00]:" + b.b.g.n.q.b.c().e(46)) + "\n[CAN在线]--[HAS_0x03CD00]:" + b.b.g.n.q.b.c().e(47)) + "\n[CAN在线]--[HAS_0x021800]:" + b.b.g.n.q.b.c().e(43)) + "\n[CAN在线]--[HAS_0X033400]:" + b.b.g.n.q.b.c().e(51)) + "\n[CAN在线]--[HAS_0X04A710]:" + b.b.g.n.q.b.c().e(198)) + "\n[CAN在线]--[HAS_0X034400]:" + b.b.g.n.q.b.c().e(208);
                        f(this.f2946a, "[关键配置]----" + str);
                        break;
                    case R.id.btn_clear_listener /* 2131296485 */:
                        b.b.g.r.e.l(this.g, this.h, this.i, this.j, this.k, this.l, this.m, this.n, this.o, this.p);
                        f(this.f2946a, "清空所有监听----");
                        break;
                    case R.id.btn_curve_energy /* 2131296488 */:
                        b.b.g.n.q.e.j().D(new c());
                        break;
                    case R.id.btn_div /* 2131296490 */:
                        this.q--;
                        ((EditText) findViewById(R.id.et_params_get)).setText(this.q + "");
                        break;
                    case R.id.btn_div0 /* 2131296491 */:
                        this.r--;
                        ((EditText) findViewById(R.id.et_params_set)).setText(this.r + "");
                        break;
                    case R.id.btn_div3 /* 2131296492 */:
                        this.s--;
                        ((EditText) findViewById(R.id.et_params_1)).setText(this.s + "");
                        break;
                    case R.id.btn_div4 /* 2131296493 */:
                        this.t--;
                        ((EditText) findViewById(R.id.et_params_2)).setText(this.t + "");
                        break;
                    case R.id.btn_energy_get /* 2131296494 */:
                        int i2 = this.q;
                        if (i2 == 1) {
                            f(this.f2946a, "getEnergyMode----" + b.b.g.n.q.e.j().f());
                            f(this.f2946a, "getOperationMode----" + b.b.g.n.q.e.j().t());
                            f(this.f2946a, "getRoadSurfaceMode----" + b.b.g.n.q.e.j().x());
                            break;
                        } else if (i2 == 2) {
                            f(this.f2946a, "getPowerGenerationState----" + b.b.g.n.q.e.j().u());
                            f(this.f2946a, "getPowerGenerationValue----" + b.b.g.n.q.e.j().v());
                            f(this.f2946a, "getEPedalState----" + b.b.g.n.q.e.j().c());
                            break;
                        } else if (i2 == 3) {
                            f(this.f2946a, "getElectricShowMode----" + b.b.g.n.q.e.j().e());
                            break;
                        } else if (i2 == 4) {
                            f(this.f2946a, "getMandatoryElectricState----" + b.b.g.n.q.e.j().o());
                            break;
                        } else if (i2 == 5) {
                            f(this.f2946a, "getElectricPointFlag----" + b.b.g.n.q.e.j().d());
                            f(this.f2946a, "getLast200MElectricCon----" + b.b.g.n.q.e.j().k());
                            break;
                        } else if (i2 == 6) {
                            f(this.f2946a, "getLast50KMOilConAvg----" + b.b.g.n.q.e.j().n());
                            f(this.f2946a, "getLast50KMElectricConAvg----" + b.b.g.n.q.e.j().m());
                            break;
                        } else if (i2 == 7) {
                            f(this.f2946a, "getDriveDistanceShowMode----" + b.b.g.n.q.e.j().a());
                            break;
                        } else if (i2 == 8) {
                            f(this.f2946a, "getDriveDistanceShowStandard----" + b.b.g.n.q.e.j().b());
                            f(this.f2946a, "getPreSaleModelState----" + b.b.g.n.q.e.j().w());
                            break;
                        } else if (i2 == 9) {
                            f(this.f2946a, "getMemorySportState----" + b.b.g.n.q.e.j().r());
                            f(this.f2946a, "getMemoryElectricState----" + b.b.g.n.q.e.j().p());
                            f(this.f2946a, "getMemoryMaxEvState----" + b.b.g.n.q.e.j().q());
                            f(this.f2946a, "getOilPointFlag----" + b.b.g.n.q.e.j().s());
                            f(this.f2946a, "getLast200MOilCon----" + b.b.g.n.q.e.j().l());
                            f(this.f2946a, "getHEVElectricPointFlag----" + b.b.g.n.q.e.j().h());
                            f(this.f2946a, "getHEVLast200MElectricCon----" + b.b.g.n.q.e.j().i());
                            break;
                        } else if (i2 == 10) {
                            f(this.f2946a, "getEnergyState----" + b.b.g.n.q.e.j().g());
                            f(this.f2946a, "getEnergyFeedback----" + b.b.g.n.q.m.p0().L());
                            break;
                        } else if (i2 != 11) {
                            break;
                        }
                        break;
                    case R.id.btn_energy_listener /* 2131296495 */:
                        f(this.f2946a, "----BydEnergyListener----");
                        i iVar = new i();
                        this.j = iVar;
                        b.b.g.r.e.f(iVar);
                        break;
                    case R.id.btn_energy_set /* 2131296496 */:
                        this.f2947b.setText("");
                        this.f2949d.setText("");
                        int i3 = this.r;
                        if (i3 == 1) {
                            f(this.f2946a, "setEPedalState----" + this.s);
                            b.b.g.n.q.e.j().H(this.s);
                            this.f2947b.setText("setEPedalState:" + this.s);
                        } else if (i3 == 2) {
                            f(this.f2946a, "setMemorySportState----" + this.s);
                            b.b.g.n.q.e.j().M(this.s);
                            this.f2947b.setText("setMemorySportState:" + this.s);
                        } else if (i3 == 3) {
                            f(this.f2946a, "setMemoryElectricState----" + this.s);
                            b.b.g.n.q.e.j().K(this.s);
                            this.f2947b.setText("setMemoryElectricState:" + this.s);
                        } else if (i3 == 4) {
                            f(this.f2946a, "setMemoryMaxEvState----" + this.s);
                            b.b.g.n.q.e.j().L(this.s);
                            this.f2947b.setText("setMemoryMaxEvState:" + this.s);
                        } else if (i3 == 5) {
                            f(this.f2946a, "setElectricShowMode----" + this.s);
                            b.b.g.n.q.e.j().I(this.s);
                            this.f2947b.setText("setElectricShowMode:" + this.s);
                        } else if (i3 == 6) {
                            f(this.f2946a, "resetCurveData----" + this.s);
                            b.b.g.n.q.e.j().E(this.s);
                            this.f2947b.setText("resetCurveData:" + this.s);
                        } else if (i3 == 7) {
                            f(this.f2946a, "setDriveDistanceShowMode----" + this.s);
                            b.b.g.n.q.e.j().F(this.s);
                            this.f2947b.setText("setDriveDistanceShowMode:" + this.s);
                        } else if (i3 == 8) {
                            f(this.f2946a, "setMandatoryElectricState----" + this.s);
                            b.b.g.n.q.e.j().J(this.s);
                            this.f2947b.setText("setMandatoryElectricState:" + this.s);
                        } else if (i3 == 9) {
                            f(this.f2946a, "setDriveDistanceShowStandard----" + this.s);
                            b.b.g.n.q.e.j().G(this.s);
                            this.f2947b.setText("setDriveDistanceShowStandard:" + this.s);
                        } else if (i3 == 10) {
                            f(this.f2946a, "setPreSaleModelState----" + this.s);
                            b.b.g.n.q.e.j().N(this.s);
                            this.f2947b.setText("setPreSaleModelState:" + this.s);
                        } else if (i3 == 11) {
                            f(this.f2946a, "setEnergyFeedback----" + this.s);
                            b.b.g.n.q.m.p0().q2(this.s);
                            this.f2947b.setText("setEnergyFeedback:" + this.s);
                        }
                        break;
                    case R.id.btn_energy_set_value /* 2131296497 */:
                        f(this.f2946a, "getEPedalState----" + b.b.g.n.q.e.j().c());
                        f(this.f2946a, "getMemorySportState----" + b.b.g.n.q.e.j().r());
                        f(this.f2946a, "getMemoryElectricState----" + b.b.g.n.q.e.j().p());
                        f(this.f2946a, "getMemoryMaxEvState----" + b.b.g.n.q.e.j().q());
                        f(this.f2946a, "getElectricShowMode----" + b.b.g.n.q.e.j().e());
                        f(this.f2946a, "getDriveDistanceShowMode----" + b.b.g.n.q.e.j().a());
                        f(this.f2946a, "getMandatoryElectricState----" + b.b.g.n.q.e.j().o());
                        f(this.f2946a, "getDriveDistanceShowStandard----" + b.b.g.n.q.e.j().b());
                        f(this.f2946a, "getPreSaleModelState----" + b.b.g.n.q.e.j().w());
                        break;
                    case R.id.btn_engine_get /* 2131296498 */:
                        int i4 = this.q;
                        if (i4 == 1) {
                            f(this.f2946a, "getEngineCondition----" + b.b.g.n.q.f.g().b());
                            break;
                        } else if (i4 == 2) {
                            f(this.f2946a, "getEngineCode----" + b.b.g.n.q.f.g().a());
                            break;
                        } else if (i4 == 3) {
                            f(this.f2946a, "getEnginePower----" + b.b.g.n.q.f.g().e());
                            break;
                        } else if (i4 == 4) {
                            f(this.f2946a, "getEngineSpeed----" + b.b.g.n.q.f.g().f());
                            break;
                        } else if (i4 == 5) {
                            f(this.f2946a, "getEngineDisplacement----" + b.b.g.n.q.f.g().d());
                            break;
                        } else if (i4 == 6) {
                            f(this.f2946a, "getEngineCoolantLevel----" + b.b.g.n.q.f.g().c());
                            break;
                        } else if (i4 == 7) {
                            f(this.f2946a, "getOilLevel----" + b.b.g.n.q.f.g().h());
                            break;
                        } else if (i4 == 8) {
                        }
                        break;
                    case R.id.btn_engine_listener /* 2131296499 */:
                        j jVar = new j();
                        this.k = jVar;
                        b.b.g.r.e.f(jVar);
                        f(this.f2946a, "----BydEngineListener----" + this.k.hashCode());
                        break;
                    case R.id.btn_method /* 2131296515 */:
                        f(this.f2946a, "----hasMessage----" + b.b.g.n.q.b.c().e(53));
                        b.b.g.n.q.b.c().h(new b());
                        break;
                    default:
                        switch (id) {
                            case R.id.btn_charge_get /* 2131296475 */:
                                int i5 = this.q;
                                if (i5 == 1) {
                                    f(this.f2946a, "getChargerFaultState----" + b.b.g.n.q.c.K().s());
                                    break;
                                } else {
                                    if (i5 != 2 && i5 != 3 && i5 != 4) {
                                        if (i5 == 5) {
                                            f(this.f2946a, "getChargingRestTime----" + Arrays.toString(b.b.g.n.q.c.K().y()));
                                        } else if (i5 == 6) {
                                            f(this.f2946a, "getChargingCapState----" + b.b.g.n.q.c.K().u(this.s));
                                        } else if (i5 == 7) {
                                            f(this.f2946a, "getChargingPortLockRebackState----" + b.b.g.n.q.c.K().w());
                                        } else if (i5 == 8) {
                                            f(this.f2946a, "getDischargeRequestState----" + b.b.g.n.q.c.K().F());
                                            f(this.f2946a, "getChargerState----" + b.b.g.n.q.c.K().t());
                                            f(this.f2946a, "getChargingScheduleEnableState----" + b.b.g.n.q.c.K().z());
                                            f(this.f2946a, "getChargingScheduleState----" + b.b.g.n.q.c.K().A());
                                        } else if (i5 == 9) {
                                            f(this.f2946a, "getChargingGunState----" + b.b.g.n.q.c.K().v());
                                            f(this.f2946a, "getBatteryManagementDeviceState----" + b.b.g.n.q.c.K().c());
                                        } else if (i5 == 10) {
                                            f(this.f2946a, "getChargingPower----" + b.b.g.n.q.c.K().x());
                                        } else if (i5 != 11 && i5 != 12) {
                                            if (i5 == 13) {
                                                f(this.f2946a, "getChargeTempCtlState----" + b.b.g.n.q.c.K().p());
                                                f(this.f2946a, "getChargeStopCapacityState----" + b.b.g.n.q.c.K().n());
                                                f(this.f2946a, "getVtovDischargeLowPowerHint----" + b.b.g.n.q.c.K().W());
                                            } else if (i5 == 14) {
                                                f(this.f2946a, "getMovePowerStationToggle----" + b.b.g.n.q.c.K().L());
                                                f(this.f2946a, "getVtovDischargeConnectState----" + b.b.g.n.q.c.K().U());
                                                f(this.f2946a, "getVtovDischargeQuantity----" + b.b.g.n.q.c.K().Y());
                                            } else if (i5 == 15) {
                                                f(this.f2946a, "getDischargeToggle----" + b.b.g.n.q.c.K().H());
                                            } else if (i5 == 16) {
                                                f(this.f2946a, "getDischargeTime----" + b.b.g.n.q.c.K().G());
                                            } else if (i5 == 17) {
                                                f(this.f2946a, "getPorducePowerWhenLowPower----" + b.b.g.n.q.c.K().N());
                                            } else if (i5 == 18) {
                                                f(this.f2946a, "getWirelessChargingSwitchState----" + b.b.g.n.q.c.K().e0());
                                                f(this.f2946a, "getWirelessChargingState----" + b.b.g.n.q.c.K().d0());
                                                f(this.f2946a, "getWirelessChargingFRSwitchState----" + b.b.g.n.q.c.K().a0());
                                                f(this.f2946a, "getWirelessChargingFRState----" + b.b.g.n.q.c.K().Z());
                                                f(this.f2946a, "getWirelessChargingPowerFLState----" + b.b.g.n.q.c.K().b0());
                                                f(this.f2946a, "getWirelessChargingPowerFRState----" + b.b.g.n.q.c.K().c0());
                                            } else if (i5 == 19) {
                                                f(this.f2946a, "getVtovChargeBatteryLevelNum----" + b.b.g.n.q.c.K().S());
                                                f(this.f2946a, "getVtovChargeBatteryLevelNumLimit----" + b.b.g.n.q.c.K().T());
                                                f(this.f2946a, "getDischargeRemainTime----" + b.b.g.n.q.c.K().E());
                                                f(this.f2946a, "getChargeVoltageMax----" + b.b.g.n.q.c.K().r());
                                                f(this.f2946a, "getVoltageMax----" + b.b.g.n.q.c.K().R());
                                            } else if (i5 == 20) {
                                                f(this.f2946a, "getDischargeVoltage----" + b.b.g.n.q.c.K().J());
                                                f(this.f2946a, "getDischargeElectric----" + b.b.g.n.q.c.K().B());
                                                f(this.f2946a, "getDischargeGunTimer----" + b.b.g.n.q.c.K().C());
                                                f(this.f2946a, "getDischargeTotalTime----" + b.b.g.n.q.c.K().I());
                                                f(this.f2946a, "getDischargeOverReason----" + b.b.g.n.q.c.K().D());
                                            } else if (i5 == 21) {
                                                f(this.f2946a, "getChargeVoltage----" + b.b.g.n.q.c.K().q());
                                                f(this.f2946a, "getChargeElectric----" + b.b.g.n.q.c.K().k());
                                                f(this.f2946a, "getChargeBatteryLevelBegin----" + b.b.g.n.q.c.K().h());
                                                f(this.f2946a, "getChargeBatteryLimitTemp----" + b.b.g.n.q.c.K().i());
                                                f(this.f2946a, "getChargeBatteryTemp----" + b.b.g.n.q.c.K().j());
                                                f(this.f2946a, "getBatteryType----" + b.b.g.n.q.c.K().d());
                                            } else if (i5 == 22) {
                                                f(this.f2946a, "getVtovDischargeLimitVal----" + b.b.g.n.q.c.K().V());
                                                f(this.f2946a, "getVtovDischargeLowestVal----" + b.b.g.n.q.c.K().X());
                                            } else if (i5 == 23) {
                                                f(this.f2946a, "getSituPowerGenerationState----" + b.b.g.n.q.c.K().P());
                                                f(this.f2946a, "getSituPowerGenerationSupport----" + b.b.g.n.q.c.K().Q());
                                            } else if (i5 == 24) {
                                                f(this.f2946a, "getChargePreheatState----" + b.b.g.n.q.c.K().m());
                                                f(this.f2946a, "getCarPlanState----" + b.b.g.n.q.c.K().g());
                                                f(this.f2946a, "getCarPlanAccOnState----" + b.b.g.n.q.c.K().e());
                                                f(this.f2946a, "getCarPlanBatteryHeatState----" + b.b.g.n.q.c.K().f());
                                                f(this.f2946a, "getNoSenseChargeState----" + b.b.g.n.q.c.K().M());
                                            } else if (i5 == 25) {
                                                f(this.f2946a, "getBMSWakeNoSenseCharge----" + b.b.g.n.q.c.K().b());
                                            } else if (i5 == 26) {
                                                f(this.f2946a, "getChargePreheatAllow----" + b.b.g.n.q.c.K().l());
                                            } else if (i5 == 27) {
                                                f(this.f2946a, "getPortCapState----" + b.b.g.n.q.c.K().O(this.s));
                                            } else if (i5 == 28) {
                                                f(this.f2946a, "getHEVChargePlanEndTime----" + Arrays.toString(BYDAutoChargingDevice.getInstance(this).getHEVChargePlaneEndTime()));
                                            } else if (i5 == 29) {
                                                f(this.f2946a, "getACChargingLimit----" + b.b.g.n.q.c.K().a());
                                            }
                                        }
                                    }
                                    break;
                                }
                                break;
                            case R.id.btn_charge_listener /* 2131296476 */:
                                k kVar = new k();
                                this.l = kVar;
                                b.b.g.r.e.f(kVar);
                                f(this.f2946a, "----BydChargingListener----" + this.l.hashCode());
                                break;
                            case R.id.btn_charge_port /* 2131296477 */:
                                b.b.g.n.q.m.p0().K1(new d());
                                break;
                            case R.id.btn_charge_set /* 2131296478 */:
                                this.f2947b.setText("");
                                this.f2949d.setText("");
                                try {
                                    int i6 = this.r;
                                    if (i6 == 1) {
                                        f(this.f2946a, "setChargeStopCapacityState----" + this.s);
                                        this.f2947b.setText("setChargeStopCapacityState:" + this.s);
                                        b.b.g.n.q.c.K().n0(this.s, 0);
                                    } else if (i6 == 2) {
                                        f(this.f2946a, "sendVtovDischargeLimitVal----" + this.s);
                                        this.f2947b.setText("sendVtovDischargeLimitVal:" + this.s);
                                        b.b.g.n.q.c.K().h0(this.s, 0);
                                    } else if (i6 == 3) {
                                        f(this.f2946a, "setMovePowerStationToggle----" + this.s);
                                        this.f2947b.setText("setMovePowerStationToggle:" + this.s);
                                        b.b.g.n.q.c.K().y0(this.s);
                                    } else if (i6 == 4) {
                                        f(this.f2946a, "setHEVChargingType----" + this.s);
                                        this.f2947b.setText("setHEVChargingType:" + this.s);
                                        b.b.g.n.q.c.K().x0(this.s);
                                    } else if (i6 == 5) {
                                        f(this.f2946a, "setChargeTempCtlState----" + this.s);
                                        this.f2947b.setText("setChargeTempCtlState:" + this.s);
                                        b.b.g.n.q.c.K().p0(this.s);
                                        f(this.f2946a, "getChargeTempCtlState----" + b.b.g.n.q.c.K().p());
                                    } else if (i6 == 6) {
                                        f(this.f2946a, "setDischargeToggle----" + this.s);
                                        this.f2947b.setText("setDischargeToggle:" + this.s);
                                        b.b.g.n.q.c.K().r0(this.s);
                                    } else if (i6 == 7) {
                                        f(this.f2946a, "setPorducePowerWhenLowPower----" + this.s);
                                        this.f2947b.setText("setPorducePowerWhenLowPower:" + this.s);
                                        b.b.g.n.q.c.K().A0(this.s);
                                    } else if (i6 == 8) {
                                        f(this.f2946a, "setDischargeTime----" + this.s);
                                        this.f2947b.setText("setDischargeTime:" + this.s);
                                        b.b.g.n.q.c.K().q0((long) this.s);
                                    } else if (i6 == 9) {
                                        f(this.f2946a, "setWirelessChargingSwitchState----" + this.s);
                                        this.f2947b.setText("setWirelessChargingSwitchState:" + this.s);
                                        b.b.g.n.q.c.K().E0(this.s);
                                    } else if (i6 == 10) {
                                        f(this.f2946a, "setWirelessChargingSwitchState----" + this.s);
                                        this.f2947b.setText("setWirelessChargingSwitchState:" + this.s);
                                        b.b.g.n.q.c.K().B0(this.s);
                                    } else if (i6 == 11) {
                                        f(this.f2946a, "setChargePreheatState----" + this.s);
                                        this.f2947b.setText("setChargePreheatState:" + this.s);
                                        b.b.g.n.q.c.K().m0(this.s);
                                    } else if (i6 == 12) {
                                        f(this.f2946a, "setCarPlan----" + this.s);
                                        this.f2947b.setText("setCarPlan:" + this.s);
                                        b.b.g.n.q.c.K().j0(1, 1, 1, 22, 11, 16, 14, 21);
                                    } else if (i6 == 13) {
                                        f(this.f2946a, "setElectricityPriceLevel----" + this.s);
                                        this.f2947b.setText("setElectricityPriceLevel");
                                        byte[] bArr = new byte[24];
                                        bArr[1] = 1;
                                        bArr[5] = 1;
                                        b.b.g.n.q.c.K().u0(bArr);
                                    } else if (i6 == 14) {
                                        f(this.f2946a, "setCarPlanState----" + this.s);
                                        this.f2947b.setText("setCarPlanState");
                                        b.b.g.n.q.c.K().k0(this.s);
                                    } else if (i6 == 15) {
                                        f(this.f2946a, "setHEVChargePlanStartTime----" + this.s);
                                        this.f2947b.setText("setHEVChargePlanStartTime");
                                        b.b.g.n.q.c.K().w0(22, 11, 16, 14, this.s);
                                    } else if (i6 == 16) {
                                        f(this.f2946a, "setHEVChargePlanEndTime----" + this.s);
                                        this.f2947b.setText("setHEVChargePlanEndTime");
                                        b.b.g.n.q.c.K().v0(20, 1, 9, 9, this.s);
                                    } else if (i6 == 17) {
                                        f(this.f2946a, "setEVChargePlanStartTime----" + this.s);
                                        this.f2947b.setText("setEVChargePlanStartTime");
                                        b.b.g.n.q.c.K().t0(18, 8, 8, 8, this.s);
                                    } else if (i6 == 18) {
                                        f(this.f2946a, "setEVChargePlanEndTime----" + this.s);
                                        this.f2947b.setText("setEVChargePlanEndTime");
                                        b.b.g.n.q.c.K().s0(14, 4, 4, 4, this.s);
                                    } else if (i6 == 19) {
                                        f(this.f2946a, "setChargePlanState----" + this.s);
                                        this.f2947b.setText("setChargePlanState");
                                        b.b.g.n.q.c.K().l0(this.s);
                                    } else if (i6 == 20) {
                                        f(this.f2946a, "setSyncTime----" + this.s);
                                        this.f2947b.setText("setSyncTime");
                                        b.b.g.n.q.c.K().C0(13, 3, 3, 3, this.s);
                                    } else if (i6 == 21) {
                                        f(this.f2946a, "setNoSenseChargeState----" + this.s);
                                        this.f2947b.setText("setNoSenseChargeState");
                                        b.b.g.n.q.c.K().z0(this.s);
                                    } else if (i6 == 22) {
                                        f(this.f2946a, "setWirelessChargingFRSwitchState----" + this.s);
                                        this.f2947b.setText("setWirelessChargingFRSwitchState");
                                        b.b.g.n.q.c.K().D0(this.s);
                                    } else if (i6 == 23) {
                                        f(this.f2946a, "setACChargingLimit----" + this.s);
                                        this.f2947b.setText("setACChargingLimit");
                                        b.b.g.n.q.c.K().i0(this.s);
                                    }
                                } catch (Exception e3) {
                                    BydLogHelper.logE(e3.getMessage());
                                    return;
                                }
                                break;
                            case R.id.btn_charge_set_value /* 2131296479 */:
                                f(this.f2946a, "setChargeStopCapacityState----" + b.b.g.n.q.c.K().n());
                                f(this.f2946a, "getVtovDischargeLimitVal----" + b.b.g.n.q.c.K().V());
                                f(this.f2946a, "getMovePowerStationToggle----" + b.b.g.n.q.c.K().L());
                                f(this.f2946a, "getDischargeToggle----" + b.b.g.n.q.c.K().H());
                                f(this.f2946a, "getPorducePowerWhenLowPower----" + b.b.g.n.q.c.K().N());
                                f(this.f2946a, "getDischargeTime----" + b.b.g.n.q.c.K().G());
                                f(this.f2946a, "getWirelessChargingSwitchState----" + b.b.g.n.q.c.K().e0());
                                f(this.f2946a, "getSituPowerGenerationState----" + b.b.g.n.q.c.K().P());
                                f(this.f2946a, "getChargePreheatState----" + b.b.g.n.q.c.K().m());
                                f(this.f2946a, "getCarPlanState----" + b.b.g.n.q.c.K().g());
                                f(this.f2946a, "getNoSenseChargeState----" + b.b.g.n.q.c.K().M());
                                break;
                            case R.id.btn_clear /* 2131296480 */:
                                this.f2949d.setText("");
                                break;
                            default:
                                switch (id) {
                                    case R.id.btn_gearbox_get /* 2131296502 */:
                                        int i7 = this.q;
                                        if (i7 == 1) {
                                            f(this.f2946a, "getGearboxAutoModeType : " + b.b.g.n.q.g.g().d());
                                        } else if (i7 == 2) {
                                            f(this.f2946a, "getGear : " + BYDAutoGearboxDevice.getInstance(b.b.a.a.a.b().a()).getGear());
                                        } else if (i7 == 3) {
                                            f(this.f2946a, "getCurrentSpeed : " + b.b.g.n.q.n.b().a());
                                        }
                                        break;
                                    case R.id.btn_gearbox_listener /* 2131296503 */:
                                        b.b.g.n.q.g.g().i(new e());
                                        b.b.g.n.q.n.b().e(new f());
                                        break;
                                    default:
                                        switch (id) {
                                            case R.id.btn_instrument_get /* 2131296507 */:
                                                int i8 = this.q;
                                                if (i8 == 1) {
                                                    f(this.f2946a, "getBacklightBrightness----" + b.b.g.n.q.h.m().d());
                                                    break;
                                                } else if (i8 == 2) {
                                                    f(this.f2946a, "getInstrumentAutoDimmState----" + b.b.g.n.q.h.m().n());
                                                    break;
                                                } else if (i8 == 3) {
                                                    f(this.f2946a, "getMilesType----" + b.b.g.n.q.h.m().w());
                                                    f(this.f2946a, "getSpeedUpCountState----" + b.b.g.n.q.h.m().y());
                                                    break;
                                                } else if (i8 == 4) {
                                                    f(this.f2946a, "getExternalChargingPower----" + b.b.g.n.q.h.m().h());
                                                    break;
                                                } else if (i8 != 5) {
                                                    if (i8 == 6) {
                                                        f(this.f2946a, "getUnit----" + b.b.g.n.q.h.m().C(0));
                                                        f(this.f2946a, "getUnit----" + b.b.g.n.q.h.m().C(1));
                                                        f(this.f2946a, "getUnit----" + b.b.g.n.q.h.m().C(2));
                                                        f(this.f2946a, "getUnit----" + b.b.g.n.q.h.m().C(3));
                                                    } else if (i8 == 7) {
                                                        f(this.f2946a, "getAlarmBuzzleState----" + b.b.g.n.q.h.m().b());
                                                    } else if (i8 == 8) {
                                                        f(this.f2946a, "getMalfunctionInfo----" + b.b.g.n.q.h.m().t(this.s));
                                                        f(this.f2946a, "getMalfunctionInfos----" + Arrays.toString(b.b.g.n.q.h.m().u()));
                                                    } else if (i8 == 9) {
                                                        f(this.f2946a, "getTotalMileage----" + b.b.g.n.q.h.m().B());
                                                        f(this.f2946a, "getMileageUnit----" + b.b.g.n.q.h.m().v());
                                                    } else if (i8 == 10) {
                                                        f(this.f2946a, "getINSNavDisplayMode----" + b.b.g.n.q.h.m().l());
                                                        f(this.f2946a, "getINSAdasCenterDisplay----" + b.b.g.n.q.h.m().j());
                                                        f(this.f2946a, "getINSCenterDisplay----" + b.b.g.n.q.h.m().k());
                                                        f(this.f2946a, "getDayNightMode----" + b.b.g.n.q.h.m().e());
                                                        f(this.f2946a, "getInstrumentScreenType----" + b.b.g.n.q.h.m().p());
                                                        f(this.f2946a, "getInstrumentConfigure----" + b.b.g.n.q.h.m().o());
                                                    } else if (i8 == 11) {
                                                        f(this.f2946a, "getSportModeState----" + b.b.g.n.q.h.m().A());
                                                    } else if (i8 == 12) {
                                                        f(this.f2946a, "getSpeedUnit----" + b.b.g.n.q.h.m().x());
                                                    } else if (i8 == 13) {
                                                        f(this.f2946a, "getSpeedUpFormIns----" + b.b.g.n.q.h.m().z());
                                                    } else if (i8 == 14) {
                                                        f(this.f2946a, "getAreaInfo----" + b.b.g.n.q.h.m().c());
                                                    }
                                                    break;
                                                }
                                                break;
                                            case R.id.btn_instrument_listener /* 2131296508 */:
                                                l lVar = new l();
                                                this.h = lVar;
                                                b.b.g.r.e.f(lVar);
                                                f(this.f2946a, "----BydInstrumentListener----" + this.h.hashCode());
                                                break;
                                            case R.id.btn_instrument_set /* 2131296509 */:
                                                this.f2947b.setText("");
                                                this.f2949d.setText("");
                                                int i9 = this.r;
                                                if (i9 == 1) {
                                                    f(this.f2946a, "setBacklightBrightness----" + this.s);
                                                    this.f2947b.setText("setBacklightBrightness:" + this.s);
                                                    b.b.g.n.q.h.m().N(this.s);
                                                    break;
                                                } else if (i9 == 2) {
                                                    f(this.f2946a, "setInstrumentAutoDimmState----" + this.s);
                                                    this.f2947b.setText("setInstrumentAutoDimmState:" + this.s);
                                                    b.b.g.n.q.h.m().S(this.s);
                                                    break;
                                                } else if (i9 == 3) {
                                                    f(this.f2946a, "setMilesType----" + this.s);
                                                    this.f2947b.setText("setMilesType:" + this.s);
                                                    b.b.g.n.q.h.m().U(this.s);
                                                    break;
                                                } else if (i9 == 4) {
                                                    f(this.f2946a, "setMaintenanceInfo----" + this.s);
                                                    this.f2947b.setText("setMaintenanceInfo:" + this.s + "----" + this.t);
                                                    b.b.g.n.q.h.m().T(this.s, this.t);
                                                    break;
                                                } else if (i9 == 5) {
                                                    f(this.f2946a, "setUnit----" + this.s + "----" + this.t);
                                                    this.f2947b.setText("setUnit:" + this.s + "----" + this.t);
                                                    b.b.g.n.q.h.m().W(this.s, this.t);
                                                    break;
                                                } else if (i9 == 6) {
                                                    f(this.f2946a, "setINSCenterDisplay----" + this.s);
                                                    this.f2947b.setText("setINSCenterDisplay:" + this.s);
                                                    b.b.g.n.q.h.m().Q(this.s);
                                                    break;
                                                } else if (i9 == 7) {
                                                    f(this.f2946a, "setINSNavDisplayMode----" + this.s);
                                                    this.f2947b.setText("setINSNavDisplayMode:" + this.s);
                                                    b.b.g.n.q.h.m().R(this.s);
                                                    break;
                                                } else if (i9 == 8) {
                                                    f(this.f2946a, "setINSAdasCenterDisplay----" + this.s);
                                                    this.f2947b.setText("setINSAdasCenterDisplay:" + this.s);
                                                    b.b.g.n.q.h.m().P(this.s);
                                                    break;
                                                } else if (i9 == 9) {
                                                    f(this.f2946a, "setSpeedUpCountState----" + this.s);
                                                    this.f2947b.setText("setSpeedUpCountState:" + this.s);
                                                    b.b.g.n.q.h.m().V(this.s);
                                                    break;
                                                } else if (i9 == 10) {
                                                    f(this.f2946a, "setDayNightMode----" + this.s);
                                                    this.f2947b.setText("setDayNightMode:" + this.s);
                                                    b.b.g.n.q.h.m().O(this.s);
                                                    break;
                                                } else if (i9 != 11) {
                                                    if (i9 == 12) {
                                                        f(this.f2946a, "sendMusicInfoUrl----" + this.s);
                                                        b.b.g.n.q.h.m().I("海阔天空", "Byd", this.s, this.t, "111/222");
                                                    } else if (i9 == 13) {
                                                        f(this.f2946a, "sendMusicState----" + this.s);
                                                        b.b.g.n.q.h.m().K(this.s);
                                                    } else if (i9 == 14) {
                                                        f(this.f2946a, "sendMusicProgress----" + this.s);
                                                        b.b.g.n.q.h.m().J(this.s, 100);
                                                    } else if (i9 == 15) {
                                                        f(this.f2946a, "sendCallInfo----" + this.s);
                                                        b.b.g.n.q.h.m().H("15989255708", "wlb", this.s, 999);
                                                    } else if (i9 == 16) {
                                                        f(this.f2946a, "setAreaInfo----" + this.s);
                                                        b.b.g.n.q.h.m().M(this.s);
                                                    } else if (i9 == 17) {
                                                        f(this.f2946a, "notifyScreenStatus----" + this.s);
                                                        b.b.g.n.q.h.m().F(this.s);
                                                    } else if (i9 == 18) {
                                                        f(this.f2946a, "notifyMapState----" + this.s);
                                                        b.b.g.n.q.h.m().E(this.s);
                                                    }
                                                    break;
                                                }
                                                break;
                                            case R.id.btn_instrument_set_value /* 2131296510 */:
                                                f(this.f2946a, "getBacklightBrightness----" + b.b.g.n.q.h.m().d());
                                                f(this.f2946a, "setInstrumentAutoDimmState----" + b.b.g.n.q.h.m().n());
                                                f(this.f2946a, "getMilesType----" + b.b.g.n.q.h.m().w());
                                                f(this.f2946a, "setMaintenanceInfo----" + b.b.g.n.q.h.m().s(this.s));
                                                f(this.f2946a, "getUnit----" + b.b.g.n.q.h.m().C(this.s));
                                                f(this.f2946a, "getINSCenterDisplay----" + b.b.g.n.q.h.m().k());
                                                f(this.f2946a, "getINSNavDisplayMode----" + b.b.g.n.q.h.m().l());
                                                f(this.f2946a, "setINSAdasCenterDisplay----" + b.b.g.n.q.h.m().j());
                                                f(this.f2946a, "getSpeedUpCountState----" + b.b.g.n.q.h.m().y());
                                                f(this.f2946a, "getDayNightMode----" + b.b.g.n.q.h.m().e());
                                                f(this.f2946a, "getLowFuelWarnLightColor----" + b.b.g.n.q.h.m().q());
                                                f(this.f2946a, "getLowPowerBatteryWarnLightColor----" + b.b.g.n.q.h.m().r());
                                                break;
                                            default:
                                                switch (id) {
                                                    case R.id.btn_radar_get /* 2131296528 */:
                                                        int i10 = this.q;
                                                        if (i10 == 1) {
                                                            f(this.f2946a, "getRadarProbeState----5:" + b.b.g.n.q.k.c().e(5));
                                                            f(this.f2946a, "getRadarProbeState----1:" + b.b.g.n.q.k.c().e(1));
                                                            f(this.f2946a, "getRadarProbeState----2:" + b.b.g.n.q.k.c().e(2));
                                                            f(this.f2946a, "getRadarProbeState----6:" + b.b.g.n.q.k.c().e(6));
                                                            f(this.f2946a, "getRadarProbeState----3:" + b.b.g.n.q.k.c().e(3));
                                                            f(this.f2946a, "getRadarProbeState----7:" + b.b.g.n.q.k.c().e(7));
                                                            f(this.f2946a, "getRadarProbeState----8:" + b.b.g.n.q.k.c().e(8));
                                                            f(this.f2946a, "getRadarProbeState----4:" + b.b.g.n.q.k.c().e(4));
                                                        } else if (i10 == 2) {
                                                            f(this.f2946a, "getAllRadarProbeStates----" + Arrays.toString(b.b.g.n.q.k.c().b()));
                                                        } else if (i10 == 3) {
                                                            f(this.f2946a, "getRadarObstacleDistance----5:" + b.b.g.n.q.k.c().d(5));
                                                            f(this.f2946a, "getRadarObstacleDistance----1:" + b.b.g.n.q.k.c().d(1));
                                                            f(this.f2946a, "getRadarObstacleDistance----2:" + b.b.g.n.q.k.c().d(2));
                                                            f(this.f2946a, "getRadarObstacleDistance----6:" + b.b.g.n.q.k.c().d(6));
                                                            f(this.f2946a, "getRadarObstacleDistance----3:" + b.b.g.n.q.k.c().d(3));
                                                            f(this.f2946a, "getRadarObstacleDistance----7:" + b.b.g.n.q.k.c().d(7));
                                                            f(this.f2946a, "getRadarObstacleDistance----8:" + b.b.g.n.q.k.c().d(8));
                                                            f(this.f2946a, "getRadarObstacleDistance----4:" + b.b.g.n.q.k.c().d(4));
                                                        } else if (i10 == 4) {
                                                            f(this.f2946a, "getAllRadarDistance----" + Arrays.toString(b.b.g.n.q.k.c().a()));
                                                        } else if (i10 == 5) {
                                                            f(this.f2946a, "getReverseRadarSwitchState----" + b.b.g.n.q.k.c().g());
                                                        } else if (i10 == 6) {
                                                            f(this.f2946a, "getReverseRadarKeyState----" + b.b.g.n.q.k.c().f());
                                                        }
                                                        break;
                                                    case R.id.btn_radar_listener /* 2131296529 */:
                                                        p pVar = new p();
                                                        this.n = pVar;
                                                        b.b.g.r.e.f(pVar);
                                                        f(this.f2946a, "----BydRadarListener----" + this.n.hashCode());
                                                        break;
                                                    case R.id.btn_radar_set /* 2131296530 */:
                                                        this.f2947b.setText("setReverseRadarSwitchState:" + this.s);
                                                        this.f2949d.setText("");
                                                        f(this.f2946a, "setReverseRadarSwitchState----" + this.s);
                                                        b.b.g.n.q.k.c().j(this.s);
                                                        break;
                                                    case R.id.btn_radar_set_value /* 2131296531 */:
                                                        f(this.f2946a, "getReverseRadarSwitchState----" + b.b.g.n.q.k.c().g());
                                                        break;
                                                    default:
                                                        switch (id) {
                                                            case R.id.btn_sensor_get /* 2131296539 */:
                                                                int i11 = this.q;
                                                                if (i11 == 1) {
                                                                    f(this.f2946a, "getLightIntensity----" + b.b.g.n.q.l.b().c());
                                                                } else if (i11 == 2) {
                                                                    f(this.f2946a, "getTemperatureSensorValue----" + b.b.g.n.q.l.b().f());
                                                                } else if (i11 == 3) {
                                                                    f(this.f2946a, "getHumiditySensorValue----" + b.b.g.n.q.l.b().a());
                                                                } else if (i11 == 4) {
                                                                    f(this.f2946a, "getSlope----" + b.b.g.n.q.l.b().e());
                                                                } else if (i11 == 5) {
                                                                    f(this.f2946a, "getSensorAxValue----" + b.b.g.n.q.l.b().d());
                                                                }
                                                                break;
                                                            case R.id.btn_sensor_listener /* 2131296540 */:
                                                                a aVar = new a();
                                                                this.o = aVar;
                                                                b.b.g.r.e.f(aVar);
                                                                f(this.f2946a, "----mSensorListener----" + this.o.hashCode());
                                                                break;
                                                            case R.id.btn_sensor_set /* 2131296541 */:
                                                                this.f2947b.setText("");
                                                                if (this.r == 1) {
                                                                    this.f2947b.setText("setOrientationAngle:" + this.s);
                                                                    this.f2949d.setText("");
                                                                    f(this.f2946a, "setOrientationAngle----" + this.s);
                                                                    b.b.g.n.q.l.b().i(this.s);
                                                                }
                                                                break;
                                                            default:
                                                                switch (id) {
                                                                    case R.id.btn_statistic_get /* 2131296544 */:
                                                                        int i12 = this.q;
                                                                        if (i12 == 1) {
                                                                            f(this.f2946a, "getTotalElecConPHMValue----" + b.b.g.n.q.o.m().q());
                                                                            f(this.f2946a, "getTotalFuelConValue----" + b.b.g.n.q.o.m().t());
                                                                        } else if (i12 == 2) {
                                                                            f(this.f2946a, "getElecDrivingRangeValue----" + b.b.g.n.q.o.m().h());
                                                                            f(this.f2946a, "getTotalMileageValue----" + b.b.g.n.q.o.m().u());
                                                                        } else if (i12 == 3) {
                                                                            f(this.f2946a, "getFuelDrivingRangeValue----" + b.b.g.n.q.o.m().j());
                                                                            f(this.f2946a, "getHEVMileageValue----" + b.b.g.n.q.o.m().l());
                                                                            f(this.f2946a, "getEVMileageValue----" + b.b.g.n.q.o.m().e());
                                                                        } else if (i12 == 4) {
                                                                            f(this.f2946a, "getElecPercentageValue----" + b.b.g.n.q.o.m().i());
                                                                            f(this.f2946a, "getAverageSpeedValue----" + b.b.g.n.q.o.m().b());
                                                                        } else if (i12 == 5) {
                                                                            f(this.f2946a, "getFuelPercentageValue----" + b.b.g.n.q.o.m().k());
                                                                            f(this.f2946a, "getTotalFuelConPHMValue----" + b.b.g.n.q.o.m().s());
                                                                        } else if (i12 == 6) {
                                                                            f(this.f2946a, "getDrivingTimeValue----" + b.b.g.n.q.o.m().d());
                                                                        } else if (i12 == 9) {
                                                                            f(this.f2946a, "getTotalElecConValue----" + b.b.g.n.q.o.m().r());
                                                                        } else if (i12 == 10) {
                                                                            f(this.f2946a, "getKeyBatteryLevel----" + b.b.g.n.q.o.m().n());
                                                                        } else if (i12 == 11) {
                                                                            f(this.f2946a, "getMileageNumber----" + b.b.g.n.q.o.m().o(0));
                                                                            f(this.f2946a, "getMileageNumber----" + b.b.g.n.q.o.m().o(1));
                                                                            f(this.f2946a, "getMileageNumber----" + b.b.g.n.q.o.m().o(2));
                                                                        } else if (i12 == 12) {
                                                                            f(this.f2946a, "getElecDrivingRangeByStandard----" + b.b.g.n.q.o.m().g());
                                                                        } else if (i12 == 13) {
                                                                            f(this.f2946a, "getBatteryPackEnergy----" + b.b.g.n.q.o.m().c());
                                                                        } else if (i12 == 14) {
                                                                            f(this.f2946a, "getRemainingBatteryPower----" + b.b.g.n.q.o.m().p());
                                                                        } else if (i12 == 15) {
                                                                            f(this.f2946a, "getEVRemainingBatteryPower----" + b.b.g.n.q.o.m().f());
                                                                        }
                                                                        break;
                                                                    case R.id.btn_statistic_listener /* 2131296545 */:
                                                                        m mVar = new m();
                                                                        this.m = mVar;
                                                                        b.b.g.r.e.f(mVar);
                                                                        f(this.f2946a, "----mStatisticListener----" + this.m.hashCode());
                                                                        b.b.g.n.q.n nVarB = b.b.g.n.q.n.b();
                                                                        n nVar = new n();
                                                                        this.p = nVar;
                                                                        nVarB.e(nVar);
                                                                        break;
                                                                    case R.id.btn_statistic_set /* 2131296546 */:
                                                                        this.f2947b.setText("");
                                                                        this.f2949d.setText("");
                                                                        int i13 = this.r;
                                                                        if (i13 == 1) {
                                                                            f(this.f2946a, "resetAverageSpeed----" + this.s);
                                                                            this.f2947b.setText("resetAverageSpeed:" + this.s);
                                                                            b.b.g.n.q.o.m().x(1, 1, 1, 1);
                                                                        } else if (i13 == 2) {
                                                                            f(this.f2946a, "resetDrivingTimeValue----" + this.s);
                                                                            this.f2947b.setText("resetDrivingTimeValue:" + this.s);
                                                                            b.b.g.n.q.o.m().x(0, 0, 0, 1);
                                                                        } else if (i13 == 3) {
                                                                            f(this.f2946a, "resetExtChargeCapacity----" + this.s);
                                                                            this.f2947b.setText("resetExtChargeCapacity:" + this.s);
                                                                            b.b.g.n.q.o.m().x(0, 1, 0, 0);
                                                                        } else if (i13 == 4) {
                                                                            f(this.f2946a, "resetTotalFuelConValue----" + this.s);
                                                                            this.f2947b.setText("resetTotalFuelConValue:" + this.s);
                                                                            b.b.g.n.q.o.m().x(1, 0, 0, 0);
                                                                        } else if (i13 == 5) {
                                                                            f(this.f2946a, "sendIsaDataToAdas----" + b.b.g.n.q.o.m().y(new int[]{120, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 0, 0, 1, 0, 0, 1}));
                                                                        } else if (i13 == 6) {
                                                                            f(this.f2946a, "setRoadCongested----" + b.b.g.n.q.o.m().A(this.s));
                                                                        } else if (i13 == 7) {
                                                                            f(this.f2946a, "setRoadConstruction----" + b.b.g.n.q.o.m().B(this.s));
                                                                        } else if (i13 == 8) {
                                                                            f(this.f2946a, "setRoadAccident----" + b.b.g.n.q.o.m().z(this.s));
                                                                        } else if (i13 == 9) {
                                                                            f(this.f2946a, "setRoadImpassable----" + b.b.g.n.q.o.m().C(this.s));
                                                                        } else if (i13 == 10) {
                                                                            f(this.f2946a, "setTrafficSignType----" + b.b.g.n.q.o.m().D(this.s));
                                                                        }
                                                                        break;
                                                                    case R.id.btn_statistic_set_value /* 2131296547 */:
                                                                        f(this.f2946a, "getAverageSpeed----" + b.b.g.n.q.o.m().a(this.s));
                                                                        f(this.f2946a, "getDrivingTimeValue----" + b.b.g.n.q.o.m().d());
                                                                        f(this.f2946a, "getExternalChargePower----" + b.b.g.n.q.h.m().g());
                                                                        f(this.f2946a, "getTotalFuelConValue----" + b.b.g.n.q.o.m().t());
                                                                        break;
                                                                    default:
                                                                        switch (id) {
                                                                            case R.id.btn_tyre_get /* 2131296552 */:
                                                                                this.f2948c.setText("");
                                                                                int i14 = this.q;
                                                                                if (i14 == 1) {
                                                                                    this.f2948c.setText("getTyreAirLeakState:" + this.s);
                                                                                    f(this.f2946a, "getTyreAirLeakState----" + b.b.g.n.q.r.b().c(this.s));
                                                                                } else if (i14 == 2) {
                                                                                    this.f2948c.setText("getTyreBatteryState:" + this.s);
                                                                                    f(this.f2946a, "getTyreBatteryState----" + b.b.g.n.q.r.b().d());
                                                                                } else if (i14 == 3) {
                                                                                    this.f2948c.setText("getTyrePressureState:" + this.s);
                                                                                    f(this.f2946a, "getTyrePressureState----" + b.b.g.n.q.r.b().g(this.s));
                                                                                } else if (i14 == 4) {
                                                                                    this.f2948c.setText("getTyrePressureValue:" + this.s);
                                                                                    f(this.f2946a, "getTyrePressureValue----" + b.b.g.n.q.r.b().i(this.s));
                                                                                } else if (i14 == 5) {
                                                                                    this.f2948c.setText("getTyreSignalState:" + this.s);
                                                                                    f(this.f2946a, "getTyreSignalState----" + b.b.g.n.q.r.b().l(this.s));
                                                                                } else if (i14 == 6) {
                                                                                    this.f2948c.setText("getTyreSystemState:" + this.s);
                                                                                    f(this.f2946a, "getTyreSystemState----" + b.b.g.n.q.r.b().m());
                                                                                } else if (i14 == 7) {
                                                                                    this.f2948c.setText("getTyreTemperatureState:" + this.s);
                                                                                    f(this.f2946a, "getTyreTemperatureState----" + b.b.g.n.q.r.b().n());
                                                                                } else if (i14 == 8) {
                                                                                    this.f2948c.setText("getIndirectTyreSystemState:" + this.s);
                                                                                    f(this.f2946a, "getIndirectTyreSystemState----" + b.b.g.n.q.r.b().a());
                                                                                } else if (i14 == 9) {
                                                                                    this.f2948c.setText("getTyreBatteryValue:" + this.s);
                                                                                    f(this.f2946a, "getTyreBatteryValue----" + b.b.g.n.q.r.b().e(this.s));
                                                                                } else if (i14 == 10) {
                                                                                    this.f2948c.setText("getTyreTurnState:" + this.s);
                                                                                    f(this.f2946a, "getTyreTurnState----" + b.b.g.n.q.r.b().o());
                                                                                } else if (i14 == 11) {
                                                                                    this.f2948c.setText("getTyreBodyType:" + this.s);
                                                                                    f(this.f2946a, "getTyreTurnState----" + b.b.g.n.q.r.b().f());
                                                                                } else if (i14 == 12) {
                                                                                    this.f2948c.setText("getTyrePressureWarn:" + this.s);
                                                                                    f(this.f2946a, "getTyrePressureWarn----" + b.b.g.n.q.r.b().j(this.s));
                                                                                    f(this.f2946a, "getTyrePressureSysFailHint----" + b.b.g.n.q.r.b().h());
                                                                                    f(this.f2946a, "getTyrePressureWarnHint----" + b.b.g.n.q.r.b().k());
                                                                                }
                                                                                break;
                                                                            case R.id.btn_tyre_listener /* 2131296553 */:
                                                                                o oVar = new o();
                                                                                this.i = oVar;
                                                                                b.b.g.r.e.f(oVar);
                                                                                f(this.f2946a, "----BydTyreListener----" + this.i.hashCode());
                                                                                break;
                                                                            case R.id.btn_tyre_set /* 2131296554 */:
                                                                                this.f2947b.setText("setIndirectTyreConfirm:" + this.s);
                                                                                this.f2949d.setText("");
                                                                                f(this.f2946a, "setIndirectTyreConfirm----" + this.s);
                                                                                b.b.g.n.q.r.b().r(this.s);
                                                                                break;
                                                                            default:
                                                                                switch (id) {
                                                                                    case R.id.btn_vehicle_health_get /* 2131296557 */:
                                                                                        int i15 = this.q;
                                                                                        if (i15 == 1) {
                                                                                            f(this.f2946a, "getOilHint----" + b.b.g.n.q.s.g().i());
                                                                                            f(this.f2946a, "getOilUpdateState----" + b.b.g.n.q.s.g().l());
                                                                                        } else if (i15 == 2) {
                                                                                            f(this.f2946a, "getOilLifetime----0----" + BYDAutoVehicleHealthDevice.getInstance(getApplicationContext()).getOilLifetime(0));
                                                                                            f(this.f2946a, "getOilLifetime----1----" + BYDAutoVehicleHealthDevice.getInstance(getApplicationContext()).getOilLifetime(1));
                                                                                            f(this.f2946a, "getOilLifetime----2----" + BYDAutoVehicleHealthDevice.getInstance(getApplicationContext()).getOilLifetime(2));
                                                                                            f(this.f2946a, "getOilLifetime----3----" + BYDAutoVehicleHealthDevice.getInstance(getApplicationContext()).getOilLifetime(3));
                                                                                            f(this.f2946a, "getOilLifetime----4----" + BYDAutoVehicleHealthDevice.getInstance(getApplicationContext()).getOilLifetime(4));
                                                                                            f(this.f2946a, "getOilMaintainanceHint----0----" + BYDAutoVehicleHealthDevice.getInstance(getApplicationContext()).getOilMaintainanceHint(0));
                                                                                            f(this.f2946a, "getOilMaintainanceHint----1----" + BYDAutoVehicleHealthDevice.getInstance(getApplicationContext()).getOilMaintainanceHint(1));
                                                                                            f(this.f2946a, "getOilMaintainanceHint----2----" + BYDAutoVehicleHealthDevice.getInstance(getApplicationContext()).getOilMaintainanceHint(2));
                                                                                            f(this.f2946a, "getOilMaintainanceHint----3----" + BYDAutoVehicleHealthDevice.getInstance(getApplicationContext()).getOilMaintainanceHint(3));
                                                                                            f(this.f2946a, "getOilMaintainanceHint----4----" + BYDAutoVehicleHealthDevice.getInstance(getApplicationContext()).getOilMaintainanceHint(4));
                                                                                        } else if (i15 == 3) {
                                                                                            f(this.f2946a, "getOilLevel----" + b.b.g.n.q.s.g().j());
                                                                                        } else if (i15 == 4) {
                                                                                            f(this.f2946a, "getFrontWiperCheckState----" + b.b.g.n.q.s.g().f());
                                                                                            f(this.f2946a, "getRearWiperCheckState----" + b.b.g.n.q.s.g().m());
                                                                                        } else if (i15 == 5) {
                                                                                            f(this.f2946a, "getMileageValid----" + b.b.g.n.q.s.g().h());
                                                                                        } else if (i15 == 6) {
                                                                                            f(this.f2946a, "getTyreFaultLocation----" + b.b.g.n.q.s.g().p());
                                                                                        } else if (i15 == 7) {
                                                                                            f(this.f2946a, "getEPBConfig----" + b.b.g.n.q.s.g().b());
                                                                                            f(this.f2946a, "getEPBType----" + b.b.g.n.q.s.g().e());
                                                                                            f(this.f2946a, "getEPBState----" + b.b.g.n.q.s.g().d());
                                                                                        } else if (i15 == 8) {
                                                                                            f(this.f2946a, "getTransportMode----" + b.b.g.n.q.s.g().n());
                                                                                            f(this.f2946a, "getTransportModeStateHint----" + b.b.g.n.q.s.g().o());
                                                                                        } else if (i15 == 9) {
                                                                                            f(this.f2946a, "getEPBHint----" + b.b.g.n.q.s.g().c());
                                                                                        }
                                                                                        break;
                                                                                    case R.id.btn_vehicle_health_listener /* 2131296558 */:
                                                                                        h hVar = new h();
                                                                                        this.g = hVar;
                                                                                        b.b.g.r.e.f(hVar);
                                                                                        f(this.f2946a, "mHealthListener----" + this.g.hashCode());
                                                                                        break;
                                                                                    case R.id.btn_vehicle_health_set /* 2131296559 */:
                                                                                        this.f2947b.setText("");
                                                                                        this.f2949d.setText("");
                                                                                        int i16 = this.r;
                                                                                        if (i16 == 1) {
                                                                                            f(this.f2946a, "setFrontWiperCheckState----" + this.s);
                                                                                            this.f2947b.setText("setFrontWiperCheckState:" + this.s);
                                                                                            b.b.g.n.q.s.g().u(this.s);
                                                                                        } else if (i16 == 2) {
                                                                                            f(this.f2946a, "setRearWiperCheckState----" + this.s);
                                                                                            this.f2947b.setText("setRearWiperCheckState:" + this.s);
                                                                                            b.b.g.n.q.s.g().x(this.s);
                                                                                        } else if (i16 == 3) {
                                                                                            f(this.f2946a, "resetOilLifetime----" + this.s);
                                                                                            this.f2947b.setText("resetOilLifetime:" + this.s);
                                                                                            b.b.g.n.q.s.g().s(this.s);
                                                                                        } else if (i16 == 4) {
                                                                                            f(this.f2946a, "setOilUpdateTag----" + this.s);
                                                                                            this.f2947b.setText("setOilUpdateTag:" + this.s);
                                                                                            b.b.g.n.q.s.g().v(this.s);
                                                                                        } else if (i16 == 5) {
                                                                                            f(this.f2946a, "setEPBState----" + this.s);
                                                                                            this.f2947b.setText("setEPBState:" + this.s);
                                                                                            b.b.g.n.q.s.g().t(this.s, 2000);
                                                                                        } else if (i16 == 6) {
                                                                                            f(this.f2946a, "setTransportMode----" + this.s);
                                                                                            this.f2947b.setText("setTransportMode:" + this.s);
                                                                                            b.b.g.n.q.s.g().y(this.s);
                                                                                        } else if (i16 == 7) {
                                                                                            f(this.f2946a, "setPowerOffChargedState----" + this.s);
                                                                                            this.f2947b.setText("setPowerOffChargedState:" + this.s);
                                                                                            b.b.g.n.q.s.g().w(this.s);
                                                                                        }
                                                                                        break;
                                                                                    case R.id.btn_vehicle_health_set_value /* 2131296560 */:
                                                                                        f(this.f2946a, "getFrontWiperCheckState----" + b.b.g.n.q.s.g().f());
                                                                                        f(this.f2946a, "getRearWiperCheckState----" + b.b.g.n.q.s.g().m());
                                                                                        f(this.f2946a, "getOilLifetime----" + b.b.g.n.q.s.g().k(this.s));
                                                                                        f(this.f2946a, "getEPBState----" + b.b.g.n.q.s.g().d());
                                                                                        f(this.f2946a, "getTransportMode----" + b.b.g.n.q.s.g().n());
                                                                                        break;
                                                                                }
                                                                                break;
                                                                        }
                                                                        break;
                                                                }
                                                                break;
                                                        }
                                                        break;
                                                }
                                                break;
                                        }
                                        break;
                                }
                                break;
                        }
                        break;
                }
                break;
        }
    }

    @Override // android.app.Activity
    public void onCreate(@Nullable Bundle bundle) {
        super.onCreate(bundle);
        setContentView(R.layout.activity_zk_test);
        this.f2950e = Environment.getExternalStorageDirectory() + "/CarsettingsLogs/";
        this.f2951f = this.f2950e + "log_" + new SimpleDateFormat("yyyy-MM-dd_HH-mm-ss", Locale.US).format(new Date()) + ".log";
        this.f2947b = (TextView) findViewById(R.id.tv_set_funname);
        this.f2948c = (TextView) findViewById(R.id.tv_get_funname);
        this.f2949d = (TextView) findViewById(R.id.tv_callback);
        findViewById(R.id.btn_vehicle_health_set).setOnClickListener(this);
        findViewById(R.id.btn_vehicle_health_get).setOnClickListener(this);
        findViewById(R.id.btn_vehicle_health_listener).setOnClickListener(this);
        findViewById(R.id.btn_vehicle_health_set_value).setOnClickListener(this);
        findViewById(R.id.btn_energy_set).setOnClickListener(this);
        findViewById(R.id.btn_energy_get).setOnClickListener(this);
        findViewById(R.id.btn_energy_set_value).setOnClickListener(this);
        findViewById(R.id.btn_engine_set).setOnClickListener(this);
        findViewById(R.id.btn_engine_get).setOnClickListener(this);
        findViewById(R.id.btn_engine_set_value).setOnClickListener(this);
        findViewById(R.id.btn_charge_get).setOnClickListener(this);
        findViewById(R.id.btn_charge_set_value).setOnClickListener(this);
        findViewById(R.id.btn_instrument_get).setOnClickListener(this);
        findViewById(R.id.btn_instrument_set_value).setOnClickListener(this);
        findViewById(R.id.btn_statistic_get).setOnClickListener(this);
        findViewById(R.id.btn_statistic_set_value).setOnClickListener(this);
        findViewById(R.id.btn_charge_set).setOnClickListener(this);
        findViewById(R.id.btn_instrument_set).setOnClickListener(this);
        findViewById(R.id.btn_statistic_set).setOnClickListener(this);
        findViewById(R.id.btn_energy_listener).setOnClickListener(this);
        findViewById(R.id.btn_engine_listener).setOnClickListener(this);
        findViewById(R.id.btn_charge_listener).setOnClickListener(this);
        findViewById(R.id.btn_instrument_listener).setOnClickListener(this);
        findViewById(R.id.btn_statistic_listener).setOnClickListener(this);
        findViewById(R.id.btn_tyre_get).setOnClickListener(this);
        findViewById(R.id.btn_tyre_set).setOnClickListener(this);
        findViewById(R.id.btn_tyre_set_value).setOnClickListener(this);
        findViewById(R.id.btn_tyre_listener).setOnClickListener(this);
        findViewById(R.id.btn_radar_get).setOnClickListener(this);
        findViewById(R.id.btn_radar_set).setOnClickListener(this);
        findViewById(R.id.btn_radar_set_value).setOnClickListener(this);
        findViewById(R.id.btn_radar_listener).setOnClickListener(this);
        findViewById(R.id.btn_sensor_get).setOnClickListener(this);
        findViewById(R.id.btn_sensor_set).setOnClickListener(this);
        findViewById(R.id.btn_sensor_set_value).setOnClickListener(this);
        findViewById(R.id.btn_sensor_listener).setOnClickListener(this);
        findViewById(R.id.btn_clear_listener).setOnClickListener(this);
        findViewById(R.id.btn_add).setOnClickListener(this);
        findViewById(R.id.btn_add0).setOnClickListener(this);
        findViewById(R.id.btn_add3).setOnClickListener(this);
        findViewById(R.id.btn_add4).setOnClickListener(this);
        findViewById(R.id.btn_div).setOnClickListener(this);
        findViewById(R.id.btn_div0).setOnClickListener(this);
        findViewById(R.id.btn_div3).setOnClickListener(this);
        findViewById(R.id.btn_div4).setOnClickListener(this);
        findViewById(R.id.btn_method).setOnClickListener(this);
        findViewById(R.id.btn_clear).setOnClickListener(this);
        findViewById(R.id.btn_autostudy).setOnClickListener(this);
        findViewById(R.id.btn_log_start).setOnClickListener(this);
        findViewById(R.id.btn_log_end).setOnClickListener(this);
        findViewById(R.id.btn_curve_energy).setOnClickListener(this);
        findViewById(R.id.btn_charge_port).setOnClickListener(this);
        findViewById(R.id.btn_gearbox_set).setOnClickListener(this);
        findViewById(R.id.btn_gearbox_get).setOnClickListener(this);
        findViewById(R.id.btn_gearbox_listener).setOnClickListener(this);
        findViewById(R.id.btn_gearbox_set_value).setOnClickListener(this);
        ((TextView) findViewById(R.id.tv_callback)).setMovementMethod(ScrollingMovementMethod.getInstance());
        e();
    }

    @Override // android.app.Activity
    public void onDestroy() {
        super.onDestroy();
        b.b.g.r.e.l(this.g, this.h, this.i, this.j, this.k, this.l, this.m, this.n, this.o);
    }

    public class n extends b.b.g.k.f {
        public n() {
        }

        @Override // android.hardware.bydauto.speed.AbsBYDAutoSpeedListener
        public void onSpeedChanged(double d2) throws Throwable {
            super.onSpeedChanged(d2);
            ZKTestActivity zKTestActivity = ZKTestActivity.this;
            zKTestActivity.f(zKTestActivity.f2946a, "onSpeedChanged----" + d2);
        }

        @Override // android.hardware.bydauto.speed.AbsBYDAutoSpeedListener
        public void onSpeedChanged(float f2, int i) throws Throwable {
            super.onSpeedChanged(f2, i);
            ZKTestActivity zKTestActivity = ZKTestActivity.this;
            zKTestActivity.f(zKTestActivity.f2946a, "onSpeedChanged----" + f2 + "----" + i);
        }
    }
}
