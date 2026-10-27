package com.byd.vrassistant;

import android.app.UiModeManager;
import android.app.UiStyleConfig;
import android.content.ComponentName;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.content.res.Configuration;
import android.database.Cursor;
import android.hardware.bydauto.ac.BYDAutoAcDevice;
import android.hardware.bydauto.bodywork.BYDAutoBodyworkDevice;
import android.hardware.bydauto.instrument.BYDAutoInstrumentDevice;
import android.hardware.bydauto.panorama.BYDAutoPanoramaDevice;
import android.hardware.bydauto.properties.BYDAutoSysPropertiesDevice;
import android.hardware.bydauto.setting.BYDAutoSettingDevice;
import android.media.AudioManager;
import android.net.Uri;
import android.os.Bundle;
import android.provider.Settings;
import android.view.View;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import com.byd.common.bean.WuwInfoBean;
import com.byd.vrassistant.conversation.PhoneHelper;
import com.byd.vrassistant.conversation.VehicleSettingsHelper;
import com.byd.vrassistant.databinding.ActivityAcBinding;
import com.byd.vrassistant.manager.LocalMediaManager;
import com.byd.vrassistant.utils.MyToastUtil;
import com.byd.vrassistant.utils.RLog;
import java.util.List;

/* JADX INFO: loaded from: classes.dex */
public class AcActivity extends AppCompatActivity {
    private static final String SERVICE_CLASS = "com.byd.dialogservice.DialogService";
    private static final String SERVICE_PKG = "com.byd.carsettings";
    private static final String TAG = "NEU_AcActivity";
    private BYDAutoAcDevice acDevice;
    private ActivityAcBinding binding;
    private BYDAutoBodyworkDevice bodyworkDevice;
    private BYDAutoInstrumentDevice instrumentDevice;
    private AudioManager mAudioManager;
    private BYDAutoPanoramaDevice panoramaDevice;
    private PackageManager pm;
    private BYDAutoSettingDevice settingDevice;
    private UiStyleConfig uiStyleConfig;
    String schemeWifi = "byd_pad://byd_car_settings:2933/launcher?activity=WiFi";
    String schemeHotSpot = "byd_pad://byd_car_settings:2933/launcher?activity=Hotspot";
    String schemeBluetooth = "byd_pad://byd_car_settings:2933/launcher?activity=Bluetooth";

    private void getAcCompressorMode() {
    }

    private void getAcCycleMode() {
    }

    private void getAcDefrostState() {
    }

    private void getAcStartState() {
    }

    private void getAcWindMode() {
    }

    private void getIALBrightness() {
    }

    private void getIALInfo() {
    }

    private void getOperationMode() {
    }

    private void getRearAcStartState() {
    }

    private void getRearAcWindMode() {
    }

    private void getSeatHeatingState() {
    }

    private void getSeatVentilatingState() {
    }

    private boolean isDivisionSingle() {
        return false;
    }

    private void rotate() {
    }

    private void setAcCompressorMode() {
    }

    private void setAcControlMode() {
    }

    private void setAcCycleMode() {
    }

    private void setAcDefrostState() {
    }

    private void setAcTemperatureControlMode() {
    }

    private void setAcVentilationState() {
    }

    private void setAcWindMode() {
    }

    private void setBodyWindowCtrlState() {
    }

    private void setIALBrightness() {
    }

    private void setIALInfo() {
    }

    private void setOperationMode() {
    }

    private void setRearAcControlMode() {
    }

    private void setRearAcWindMode() {
    }

    private void setSeatHeatingState() {
    }

    private void setSeatVentilatingState() {
    }

    private void start() {
    }

    private void startRearAc() {
    }

    private void stop() {
    }

    private void stopRearAc() {
    }

    private void valideCamera() {
    }

    private void voiceCtlMoonRoof() {
    }

    private void voiceCtlSunshadePanel() {
    }

    public boolean hasFeatureIAL(String str) {
        return false;
    }

    public boolean hasFeatureSeatVentilatingHeating(String str) {
        return false;
    }

    public boolean isSupportEcoAndSport() {
        return false;
    }

    @Override // androidx.appcompat.app.AppCompatActivity, androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, androidx.core.app.ComponentActivity, android.app.Activity
    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        this.binding = (ActivityAcBinding) DataBindingUtil.setContentView(this, R.layout.activity_ac);
        initView();
        initOnClick();
    }

    private void initView() {
        this.bodyworkDevice = BYDAutoBodyworkDevice.getInstance(this);
        this.acDevice = BYDAutoAcDevice.getInstance(this);
        this.uiStyleConfig = UiStyleConfig.getInstance(this);
        this.settingDevice = BYDAutoSettingDevice.getInstance(this);
        this.instrumentDevice = BYDAutoInstrumentDevice.getInstance(this);
        this.panoramaDevice = BYDAutoPanoramaDevice.getInstance(this);
        this.mAudioManager = (AudioManager) getSystemService("audio");
        this.pm = getPackageManager();
    }

    private void initOnClick() {
        this.binding.back.setOnClickListener(new View.OnClickListener() { // from class: com.byd.vrassistant.AcActivity$$ExternalSyntheticLambda0
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                this.f$0.m67lambda$initOnClick$0$combydvrassistantAcActivity(view);
            }
        });
        this.binding.tvPowerLevel.setOnClickListener(new View.OnClickListener() { // from class: com.byd.vrassistant.AcActivity$$ExternalSyntheticLambda2
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                this.f$0.m68lambda$initOnClick$1$combydvrassistantAcActivity(view);
            }
        });
        this.binding.tvAcOnlineState.setOnClickListener(new View.OnClickListener() { // from class: com.byd.vrassistant.AcActivity$$ExternalSyntheticLambda14
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                this.f$0.m79lambda$initOnClick$2$combydvrassistantAcActivity(view);
            }
        });
        this.binding.tvAcTemperatureControlMode.setOnClickListener(new View.OnClickListener() { // from class: com.byd.vrassistant.AcActivity$$ExternalSyntheticLambda26
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                this.f$0.m90lambda$initOnClick$3$combydvrassistantAcActivity(view);
            }
        });
        this.binding.tvRearAcOnlineState.setOnClickListener(new View.OnClickListener() { // from class: com.byd.vrassistant.AcActivity$$ExternalSyntheticLambda27
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                this.f$0.m101lambda$initOnClick$4$combydvrassistantAcActivity(view);
            }
        });
        this.binding.tvTemprature.setOnClickListener(new View.OnClickListener() { // from class: com.byd.vrassistant.AcActivity$$ExternalSyntheticLambda28
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                this.f$0.m102lambda$initOnClick$5$combydvrassistantAcActivity(view);
            }
        });
        this.binding.tvSetAcTemperature.setOnClickListener(new View.OnClickListener() { // from class: com.byd.vrassistant.AcActivity$$ExternalSyntheticLambda29
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                this.f$0.m103lambda$initOnClick$6$combydvrassistantAcActivity(view);
            }
        });
        this.binding.tvAcWindLevel.setOnClickListener(new View.OnClickListener() { // from class: com.byd.vrassistant.AcActivity$$ExternalSyntheticLambda30
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                this.f$0.m104lambda$initOnClick$7$combydvrassistantAcActivity(view);
            }
        });
        this.binding.tvRearAcWindLevel.setOnClickListener(new View.OnClickListener() { // from class: com.byd.vrassistant.AcActivity$$ExternalSyntheticLambda31
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                this.f$0.m105lambda$initOnClick$8$combydvrassistantAcActivity(view);
            }
        });
        this.binding.tvSetAcWindLevel.setOnClickListener(new View.OnClickListener() { // from class: com.byd.vrassistant.AcActivity$$ExternalSyntheticLambda32
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                this.f$0.m106lambda$initOnClick$9$combydvrassistantAcActivity(view);
            }
        });
        this.binding.tvSetRearAcWindLevel.setOnClickListener(new View.OnClickListener() { // from class: com.byd.vrassistant.AcActivity$$ExternalSyntheticLambda11
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                this.f$0.m69lambda$initOnClick$10$combydvrassistantAcActivity(view);
            }
        });
        this.binding.tvGetFanModeNum.setOnClickListener(new View.OnClickListener() { // from class: com.byd.vrassistant.AcActivity$$ExternalSyntheticLambda22
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                this.f$0.m70lambda$initOnClick$11$combydvrassistantAcActivity(view);
            }
        });
        this.binding.tvAcVentilationState.setOnClickListener(new View.OnClickListener() { // from class: com.byd.vrassistant.AcActivity$$ExternalSyntheticLambda33
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                this.f$0.m71lambda$initOnClick$12$combydvrassistantAcActivity(view);
            }
        });
        this.binding.tvAcType.setOnClickListener(new View.OnClickListener() { // from class: com.byd.vrassistant.AcActivity$$ExternalSyntheticLambda34
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                this.f$0.m72lambda$initOnClick$13$combydvrassistantAcActivity(view);
            }
        });
        this.binding.tvWarmOnlineState.setOnClickListener(new View.OnClickListener() { // from class: com.byd.vrassistant.AcActivity$$ExternalSyntheticLambda35
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                this.f$0.m73lambda$initOnClick$14$combydvrassistantAcActivity(view);
            }
        });
        this.binding.tvWindowState.setOnClickListener(new View.OnClickListener() { // from class: com.byd.vrassistant.AcActivity$$ExternalSyntheticLambda36
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                this.f$0.m74lambda$initOnClick$15$combydvrassistantAcActivity(view);
            }
        });
        this.binding.tvWindowOpenPercent.setOnClickListener(new View.OnClickListener() { // from class: com.byd.vrassistant.AcActivity$$ExternalSyntheticLambda37
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                this.f$0.m75lambda$initOnClick$16$combydvrassistantAcActivity(view);
            }
        });
        this.binding.tvSetAllWindowState.setOnClickListener(new View.OnClickListener() { // from class: com.byd.vrassistant.AcActivity$$ExternalSyntheticLambda38
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                this.f$0.m76lambda$initOnClick$17$combydvrassistantAcActivity(view);
            }
        });
        this.binding.tvSetBodyWindowCtrlState.setOnClickListener(new View.OnClickListener() { // from class: com.byd.vrassistant.AcActivity$$ExternalSyntheticLambda39
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                this.f$0.m77lambda$initOnClick$18$combydvrassistantAcActivity(view);
            }
        });
        this.binding.tvSupportAmbientLight.setOnClickListener(new View.OnClickListener() { // from class: com.byd.vrassistant.AcActivity$$ExternalSyntheticLambda1
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                this.f$0.m78lambda$initOnClick$19$combydvrassistantAcActivity(view);
            }
        });
        this.binding.tvBacklightBrightness.setOnClickListener(new View.OnClickListener() { // from class: com.byd.vrassistant.AcActivity$$ExternalSyntheticLambda3
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                this.f$0.m80lambda$initOnClick$20$combydvrassistantAcActivity(view);
            }
        });
        this.binding.tvSetBacklightBrightness.setOnClickListener(new View.OnClickListener() { // from class: com.byd.vrassistant.AcActivity$$ExternalSyntheticLambda4
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                this.f$0.m81lambda$initOnClick$21$combydvrassistantAcActivity(view);
            }
        });
        this.binding.tvCurrentSystemMode.setOnClickListener(new View.OnClickListener() { // from class: com.byd.vrassistant.AcActivity$$ExternalSyntheticLambda5
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                this.f$0.m82lambda$initOnClick$22$combydvrassistantAcActivity(view);
            }
        });
        this.binding.tvSetCurrentSystemMode.setOnClickListener(new View.OnClickListener() { // from class: com.byd.vrassistant.AcActivity$$ExternalSyntheticLambda6
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                this.f$0.m83lambda$initOnClick$23$combydvrassistantAcActivity(view);
            }
        });
        this.binding.tvAcMaxCoolingState.setOnClickListener(new View.OnClickListener() { // from class: com.byd.vrassistant.AcActivity$$ExternalSyntheticLambda7
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                this.f$0.m84lambda$initOnClick$24$combydvrassistantAcActivity(view);
            }
        });
        this.binding.tvTvOpenWifiSetting.setOnClickListener(new View.OnClickListener() { // from class: com.byd.vrassistant.AcActivity$$ExternalSyntheticLambda8
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                this.f$0.m85lambda$initOnClick$25$combydvrassistantAcActivity(view);
            }
        });
        this.binding.tvTvOpenHotSpotSetting.setOnClickListener(new View.OnClickListener() { // from class: com.byd.vrassistant.AcActivity$$ExternalSyntheticLambda9
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                this.f$0.m86lambda$initOnClick$26$combydvrassistantAcActivity(view);
            }
        });
        this.binding.tvTvOpenBluetoothSetting.setOnClickListener(new View.OnClickListener() { // from class: com.byd.vrassistant.AcActivity$$ExternalSyntheticLambda10
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                this.f$0.m87lambda$initOnClick$27$combydvrassistantAcActivity(view);
            }
        });
        this.binding.tvTvOpenVehicleImage.setOnClickListener(new View.OnClickListener() { // from class: com.byd.vrassistant.AcActivity$$ExternalSyntheticLambda12
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                this.f$0.m88lambda$initOnClick$28$combydvrassistantAcActivity(view);
            }
        });
        this.binding.tvOpenBtPhone.setOnClickListener(new View.OnClickListener() { // from class: com.byd.vrassistant.AcActivity$$ExternalSyntheticLambda13
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                this.f$0.m89lambda$initOnClick$29$combydvrassistantAcActivity(view);
            }
        });
        this.binding.tvOpenBtMusic.setOnClickListener(new View.OnClickListener() { // from class: com.byd.vrassistant.AcActivity$$ExternalSyntheticLambda15
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                this.f$0.m91lambda$initOnClick$30$combydvrassistantAcActivity(view);
            }
        });
        this.binding.tvOpenCamera.setOnClickListener(new View.OnClickListener() { // from class: com.byd.vrassistant.AcActivity$$ExternalSyntheticLambda16
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                this.f$0.m92lambda$initOnClick$31$combydvrassistantAcActivity(view);
            }
        });
        this.binding.tvOpenPhoto.setOnClickListener(new View.OnClickListener() { // from class: com.byd.vrassistant.AcActivity$$ExternalSyntheticLambda17
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                this.f$0.m93lambda$initOnClick$32$combydvrassistantAcActivity(view);
            }
        });
        this.binding.tvOpenCarsettings.setOnClickListener(new View.OnClickListener() { // from class: com.byd.vrassistant.AcActivity$$ExternalSyntheticLambda18
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                this.f$0.m94lambda$initOnClick$33$combydvrassistantAcActivity(view);
            }
        });
        this.binding.tvOpenFilemanager.setOnClickListener(new View.OnClickListener() { // from class: com.byd.vrassistant.AcActivity$$ExternalSyntheticLambda19
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                this.f$0.m95lambda$initOnClick$34$combydvrassistantAcActivity(view);
            }
        });
        this.binding.tvOpenAndroidauto.setOnClickListener(new View.OnClickListener() { // from class: com.byd.vrassistant.AcActivity$$ExternalSyntheticLambda20
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                this.f$0.m96lambda$initOnClick$35$combydvrassistantAcActivity(view);
            }
        });
        this.binding.tvOpenCarplay.setOnClickListener(new View.OnClickListener() { // from class: com.byd.vrassistant.AcActivity$$ExternalSyntheticLambda21
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                this.f$0.m97lambda$initOnClick$36$combydvrassistantAcActivity(view);
            }
        });
        this.binding.tvOpenSpotify.setOnClickListener(new View.OnClickListener() { // from class: com.byd.vrassistant.AcActivity$$ExternalSyntheticLambda23
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                this.f$0.m98lambda$initOnClick$37$combydvrassistantAcActivity(view);
            }
        });
        this.binding.tvOpenNavi.setOnClickListener(new View.OnClickListener() { // from class: com.byd.vrassistant.AcActivity$$ExternalSyntheticLambda24
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                this.f$0.m99lambda$initOnClick$38$combydvrassistantAcActivity(view);
            }
        });
        this.binding.tvValideCamera.setOnClickListener(new View.OnClickListener() { // from class: com.byd.vrassistant.AcActivity$$ExternalSyntheticLambda25
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                this.f$0.m100lambda$initOnClick$39$combydvrassistantAcActivity(view);
            }
        });
    }

    /* JADX INFO: renamed from: lambda$initOnClick$0$com-byd-vrassistant-AcActivity, reason: not valid java name */
    /* synthetic */ void m67lambda$initOnClick$0$combydvrassistantAcActivity(View view) {
        finish();
    }

    /* JADX INFO: renamed from: lambda$initOnClick$1$com-byd-vrassistant-AcActivity, reason: not valid java name */
    /* synthetic */ void m68lambda$initOnClick$1$combydvrassistantAcActivity(View view) {
        getPowerLevel();
    }

    /* JADX INFO: renamed from: lambda$initOnClick$2$com-byd-vrassistant-AcActivity, reason: not valid java name */
    /* synthetic */ void m79lambda$initOnClick$2$combydvrassistantAcActivity(View view) {
        getAcOnlineState();
    }

    /* JADX INFO: renamed from: lambda$initOnClick$3$com-byd-vrassistant-AcActivity, reason: not valid java name */
    /* synthetic */ void m90lambda$initOnClick$3$combydvrassistantAcActivity(View view) {
        getAcTemperatureControlMode();
    }

    /* JADX INFO: renamed from: lambda$initOnClick$4$com-byd-vrassistant-AcActivity, reason: not valid java name */
    /* synthetic */ void m101lambda$initOnClick$4$combydvrassistantAcActivity(View view) {
        getRearAcOnlineState();
    }

    /* JADX INFO: renamed from: lambda$initOnClick$5$com-byd-vrassistant-AcActivity, reason: not valid java name */
    /* synthetic */ void m102lambda$initOnClick$5$combydvrassistantAcActivity(View view) {
        getTemprature();
    }

    /* JADX INFO: renamed from: lambda$initOnClick$6$com-byd-vrassistant-AcActivity, reason: not valid java name */
    /* synthetic */ void m103lambda$initOnClick$6$combydvrassistantAcActivity(View view) {
        setAcTemperature();
    }

    /* JADX INFO: renamed from: lambda$initOnClick$7$com-byd-vrassistant-AcActivity, reason: not valid java name */
    /* synthetic */ void m104lambda$initOnClick$7$combydvrassistantAcActivity(View view) {
        getAcWindLevel();
    }

    /* JADX INFO: renamed from: lambda$initOnClick$8$com-byd-vrassistant-AcActivity, reason: not valid java name */
    /* synthetic */ void m105lambda$initOnClick$8$combydvrassistantAcActivity(View view) {
        getRearAcWindLevel();
    }

    /* JADX INFO: renamed from: lambda$initOnClick$9$com-byd-vrassistant-AcActivity, reason: not valid java name */
    /* synthetic */ void m106lambda$initOnClick$9$combydvrassistantAcActivity(View view) {
        setAcWindLevel();
    }

    /* JADX INFO: renamed from: lambda$initOnClick$10$com-byd-vrassistant-AcActivity, reason: not valid java name */
    /* synthetic */ void m69lambda$initOnClick$10$combydvrassistantAcActivity(View view) {
        setRearAcWindLevel();
    }

    /* JADX INFO: renamed from: lambda$initOnClick$11$com-byd-vrassistant-AcActivity, reason: not valid java name */
    /* synthetic */ void m70lambda$initOnClick$11$combydvrassistantAcActivity(View view) {
        getAcWindModeNum();
    }

    /* JADX INFO: renamed from: lambda$initOnClick$12$com-byd-vrassistant-AcActivity, reason: not valid java name */
    /* synthetic */ void m71lambda$initOnClick$12$combydvrassistantAcActivity(View view) {
        getAcVentilationState();
    }

    /* JADX INFO: renamed from: lambda$initOnClick$13$com-byd-vrassistant-AcActivity, reason: not valid java name */
    /* synthetic */ void m72lambda$initOnClick$13$combydvrassistantAcActivity(View view) {
        getAcType();
    }

    /* JADX INFO: renamed from: lambda$initOnClick$14$com-byd-vrassistant-AcActivity, reason: not valid java name */
    /* synthetic */ void m73lambda$initOnClick$14$combydvrassistantAcActivity(View view) {
        warmOnlineState();
    }

    /* JADX INFO: renamed from: lambda$initOnClick$15$com-byd-vrassistant-AcActivity, reason: not valid java name */
    /* synthetic */ void m74lambda$initOnClick$15$combydvrassistantAcActivity(View view) {
        getWindowState();
    }

    /* JADX INFO: renamed from: lambda$initOnClick$16$com-byd-vrassistant-AcActivity, reason: not valid java name */
    /* synthetic */ void m75lambda$initOnClick$16$combydvrassistantAcActivity(View view) {
        getWindowOpenPercent();
    }

    /* JADX INFO: renamed from: lambda$initOnClick$17$com-byd-vrassistant-AcActivity, reason: not valid java name */
    /* synthetic */ void m76lambda$initOnClick$17$combydvrassistantAcActivity(View view) {
        setAllWindowState();
    }

    /* JADX INFO: renamed from: lambda$initOnClick$18$com-byd-vrassistant-AcActivity, reason: not valid java name */
    /* synthetic */ void m77lambda$initOnClick$18$combydvrassistantAcActivity(View view) {
        setBodyWindowCtrlState();
    }

    /* JADX INFO: renamed from: lambda$initOnClick$19$com-byd-vrassistant-AcActivity, reason: not valid java name */
    /* synthetic */ void m78lambda$initOnClick$19$combydvrassistantAcActivity(View view) {
        supportAmbientLight();
    }

    /* JADX INFO: renamed from: lambda$initOnClick$20$com-byd-vrassistant-AcActivity, reason: not valid java name */
    /* synthetic */ void m80lambda$initOnClick$20$combydvrassistantAcActivity(View view) {
        getBacklightBrightness();
    }

    /* JADX INFO: renamed from: lambda$initOnClick$21$com-byd-vrassistant-AcActivity, reason: not valid java name */
    /* synthetic */ void m81lambda$initOnClick$21$combydvrassistantAcActivity(View view) {
        setBacklightBrightness();
    }

    /* JADX INFO: renamed from: lambda$initOnClick$22$com-byd-vrassistant-AcActivity, reason: not valid java name */
    /* synthetic */ void m82lambda$initOnClick$22$combydvrassistantAcActivity(View view) {
        currentSystemMode();
    }

    /* JADX INFO: renamed from: lambda$initOnClick$23$com-byd-vrassistant-AcActivity, reason: not valid java name */
    /* synthetic */ void m83lambda$initOnClick$23$combydvrassistantAcActivity(View view) {
        setCurrentSystemMode();
    }

    /* JADX INFO: renamed from: lambda$initOnClick$24$com-byd-vrassistant-AcActivity, reason: not valid java name */
    /* synthetic */ void m84lambda$initOnClick$24$combydvrassistantAcActivity(View view) {
        acMaxCoolingState();
    }

    /* JADX INFO: renamed from: lambda$initOnClick$25$com-byd-vrassistant-AcActivity, reason: not valid java name */
    /* synthetic */ void m85lambda$initOnClick$25$combydvrassistantAcActivity(View view) {
        openWifiSetting();
    }

    /* JADX INFO: renamed from: lambda$initOnClick$26$com-byd-vrassistant-AcActivity, reason: not valid java name */
    /* synthetic */ void m86lambda$initOnClick$26$combydvrassistantAcActivity(View view) {
        openHotSpotSetting();
    }

    /* JADX INFO: renamed from: lambda$initOnClick$27$com-byd-vrassistant-AcActivity, reason: not valid java name */
    /* synthetic */ void m87lambda$initOnClick$27$combydvrassistantAcActivity(View view) {
        openBluetoothSetting();
    }

    /* JADX INFO: renamed from: lambda$initOnClick$28$com-byd-vrassistant-AcActivity, reason: not valid java name */
    /* synthetic */ void m88lambda$initOnClick$28$combydvrassistantAcActivity(View view) {
        openVehicleImage();
    }

    /* JADX INFO: renamed from: lambda$initOnClick$29$com-byd-vrassistant-AcActivity, reason: not valid java name */
    /* synthetic */ void m89lambda$initOnClick$29$combydvrassistantAcActivity(View view) {
        openApp(PhoneHelper.BTPHONE_PACKAGE);
    }

    /* JADX INFO: renamed from: lambda$initOnClick$30$com-byd-vrassistant-AcActivity, reason: not valid java name */
    /* synthetic */ void m91lambda$initOnClick$30$combydvrassistantAcActivity(View view) {
        openApp2(LocalMediaManager.LOCALMEDIA_PACKAGE_NAME, "com.byd.localmusic.music.view.BTMusicActivity");
    }

    /* JADX INFO: renamed from: lambda$initOnClick$31$com-byd-vrassistant-AcActivity, reason: not valid java name */
    /* synthetic */ void m92lambda$initOnClick$31$combydvrassistantAcActivity(View view) {
        openApp("com.byd.auto_camera");
    }

    /* JADX INFO: renamed from: lambda$initOnClick$32$com-byd-vrassistant-AcActivity, reason: not valid java name */
    /* synthetic */ void m93lambda$initOnClick$32$combydvrassistantAcActivity(View view) {
        openApp("com.byd.auto_photo");
    }

    /* JADX INFO: renamed from: lambda$initOnClick$33$com-byd-vrassistant-AcActivity, reason: not valid java name */
    /* synthetic */ void m94lambda$initOnClick$33$combydvrassistantAcActivity(View view) {
        openApp(SERVICE_PKG);
    }

    /* JADX INFO: renamed from: lambda$initOnClick$34$com-byd-vrassistant-AcActivity, reason: not valid java name */
    /* synthetic */ void m95lambda$initOnClick$34$combydvrassistantAcActivity(View view) {
        openApp("com.byd.filemanager");
    }

    /* JADX INFO: renamed from: lambda$initOnClick$35$com-byd-vrassistant-AcActivity, reason: not valid java name */
    /* synthetic */ void m96lambda$initOnClick$35$combydvrassistantAcActivity(View view) {
        openApp(VehicleSettingsHelper.PACKAGE_ANDROID_AUTO);
    }

    /* JADX INFO: renamed from: lambda$initOnClick$36$com-byd-vrassistant-AcActivity, reason: not valid java name */
    /* synthetic */ void m97lambda$initOnClick$36$combydvrassistantAcActivity(View view) {
        openApp(VehicleSettingsHelper.PACKAGE_CAR_PLAY);
    }

    /* JADX INFO: renamed from: lambda$initOnClick$37$com-byd-vrassistant-AcActivity, reason: not valid java name */
    /* synthetic */ void m98lambda$initOnClick$37$combydvrassistantAcActivity(View view) {
        openApp("com.vivid.music.byd");
    }

    /* JADX INFO: renamed from: lambda$initOnClick$38$com-byd-vrassistant-AcActivity, reason: not valid java name */
    /* synthetic */ void m99lambda$initOnClick$38$combydvrassistantAcActivity(View view) {
        openApp(Constant.NEU_NAVIPACKAGE);
    }

    /* JADX INFO: renamed from: lambda$initOnClick$39$com-byd-vrassistant-AcActivity, reason: not valid java name */
    /* synthetic */ void m100lambda$initOnClick$39$combydvrassistantAcActivity(View view) {
        valideCamera();
    }

    @Override // androidx.appcompat.app.AppCompatActivity, androidx.fragment.app.FragmentActivity, android.app.Activity, android.content.ComponentCallbacks
    public void onConfigurationChanged(Configuration configuration) {
        super.onConfigurationChanged(configuration);
        RLog.d(TAG, "onConfigurationChanged: " + ((UiModeManager) getSystemService("uimode")).getNightMode());
    }

    private void openVehicleImage() {
        RLog.i(TAG, "openVehicleImage");
        RLog.d(TAG, "openVehicleImage: " + this.uiStyleConfig.getSupportPanoramaImage());
    }

    private void getPowerLevel() {
        RLog.d(TAG, "获取车辆档位: " + this.bodyworkDevice.getPowerLevel());
    }

    private void getAcOnlineState() {
        RLog.d(TAG, "检查空调开关功能是否可用: " + this.acDevice.getAcOnlineState());
    }

    private void getAcTemperatureControlMode() {
        int acTemperatureControlMode = this.acDevice.getAcTemperatureControlMode();
        MyToastUtil.show(this, "检查空调分控功能是否可用 = " + acTemperatureControlMode);
        RLog.d(TAG, "getAcTemperatureControlMode: " + acTemperatureControlMode);
    }

    private void getRearAcOnlineState() {
        int supportRearAc = this.uiStyleConfig.getSupportRearAc();
        MyToastUtil.show(this, "检查空调后排开功能是否可用 == " + supportRearAc);
        RLog.d(TAG, "检查空调后排开功能是否可用: " + supportRearAc);
    }

    private void getSystemInt() {
        MyToastUtil.show(this, "检查空调温度功能是否可用（AC_TEMP） = ");
    }

    private void getTemprature() {
        int temprature = this.acDevice.getTemprature(1);
        int temprature2 = this.acDevice.getTemprature(2);
        int temprature3 = this.acDevice.getTemprature(3);
        MyToastUtil.show(this, "获取空调温度（主、副驾、后排） = " + temprature + "  " + temprature2 + " " + temprature3);
        RLog.d(TAG, "获取空调温度（主、副驾、后排） = " + temprature + "  " + temprature2 + " " + temprature3);
    }

    private void setAcTemperature() {
        MyToastUtil.show(this, "设置空调温度（主、副驾、后排） = " + this.acDevice.setAcTemperature(1, 20, 1, 1));
    }

    private void getAcWindLevel() {
        RLog.d(TAG, "获取前排空调风量: " + this.acDevice.getAcWindLevel());
    }

    private void getRearAcWindLevel() {
        RLog.d(TAG, "获取后排空调风量: " + this.acDevice.getRearAcWindLevel());
    }

    private void setAcWindLevel() {
        MyToastUtil.show(this, "设置前排空调风量 = " + this.acDevice.setAcWindLevel(1, 2));
    }

    private void setRearAcWindLevel() {
        MyToastUtil.show(this, "设置后排空调风量 = " + this.acDevice.setRearAcWindLevel(1, 2));
    }

    private void getAcWindModeNum() {
        RLog.d(TAG, "空调风向功能是否可用: " + this.acDevice.getAcWindModeNum());
    }

    private void getAcVentilationState() {
        RLog.d(TAG, "空调通风状态（通风模式） = " + this.acDevice.getAcVentilationState());
    }

    private void getAcType() {
        RLog.d(TAG, "获取空调类型（自动|手动）: " + this.acDevice.getAcControlMode());
    }

    private void warmOnlineState() {
        RLog.d(TAG, "热泵空调自学习: " + this.uiStyleConfig.getAcWarmTypeOnlineState());
    }

    private void getWindowState() {
        MyToastUtil.show(this, "获取车窗状态接口 = " + this.bodyworkDevice.getWindowState(2));
    }

    private void getWindowOpenPercent() {
        MyToastUtil.show(this, "获取车窗打开百分比接口 = " + this.bodyworkDevice.getWindowOpenPercent(2));
    }

    private void setAllWindowState() {
        MyToastUtil.show(this, "全部车窗控制 = " + this.bodyworkDevice.setAllWindowState(1, 1, 1, 1));
    }

    private void supportAmbientLight() {
        RLog.d(TAG, "是否支持颜色调节: " + this.uiStyleConfig.getAmbientLightColorSupport());
        RLog.d(TAG, "是否支持亮度调节: " + this.settingDevice.getIALBrightness(1));
        RLog.d(TAG, "是否支持区域调节: " + this.settingDevice.getIALArea());
    }

    private void openSystemSetting() {
        Intent intent = new Intent("android.intent.action.MAIN");
        intent.setFlags(268435456);
        intent.addCategory("android.intent.category.LAUNCHER");
        intent.setComponent(new ComponentName(SERVICE_PKG, "com.byd.carsettings.MainActivity"));
        startActivity(intent);
    }

    private void getBacklightBrightness() {
        int backlightBrightness = this.instrumentDevice.getBacklightBrightness();
        MyToastUtil.show(BydVoiceApplication.getContext(), "取得仪表屏幕亮度 = " + backlightBrightness);
        RLog.d(TAG, "getBacklightBrightness: " + backlightBrightness);
    }

    private void setBacklightBrightness() {
        int backlightBrightness = this.instrumentDevice.setBacklightBrightness(5);
        MyToastUtil.show(this, "设置仪表屏幕亮度 = " + backlightBrightness);
        RLog.d(TAG, "setBacklightBrightness: " + backlightBrightness);
    }

    private void screenBrightness() {
        MyToastUtil.show(this, "取得屏幕亮度 = " + Settings.System.getInt(getContentResolver(), BYDAutoSysPropertiesDevice.SCREEN_BRIGHTNESS, 0));
    }

    private void setScreenBrightness() {
        Settings.System.putInt(getContentResolver(), BYDAutoSysPropertiesDevice.SCREEN_BRIGHTNESS, 50);
    }

    private void setStreamMute() {
        this.mAudioManager.setStreamMute(3, true);
    }

    private void setStreamVolume() {
        this.mAudioManager.setStreamVolume(3, 8, 0);
    }

    private void currentSystemMode() {
        RLog.d(TAG, "currentSystemMode: " + ((BydVoiceApplication.getContext().getResources().getConfiguration().uiMode & 48) == 2));
    }

    private void setCurrentSystemMode() {
        getDelegate().setLocalNightMode(2);
    }

    private void acMaxCoolingState() {
        RLog.d(TAG, "最大制冷功能是否可用: " + this.acDevice.getAcMaxCoolingState());
    }

    private void carWindowSelfLearning() {
        try {
            Cursor cursorQuery = getContentResolver().query(Uri.parse("content://carsettings/global"), null, "key='voice_control_window_visible'", null, null);
            MyToastUtil.show(this, "车窗自学习 = " + cursorQuery.getInt(cursorQuery.getColumnIndex(WuwInfoBean.ValueKey)));
        } catch (Exception e) {
            MyToastUtil.show(this, "车窗自学习 = " + e.getCause());
        }
    }

    private void openWifiSetting() {
        RLog.d(TAG, "openWifiSetting: ");
        Intent intent = new Intent("android.intent.action.VIEW", Uri.parse(this.schemeWifi));
        intent.setComponent(new ComponentName(SERVICE_PKG, SERVICE_CLASS));
        List<ResolveInfo> listQueryIntentServices = getPackageManager().queryIntentServices(intent, 0);
        RLog.i(TAG, "----startIntentByPath----resolveInfos = " + listQueryIntentServices);
        if (listQueryIntentServices.isEmpty()) {
            return;
        }
        startService(intent);
    }

    private void openHotSpotSetting() {
        RLog.d(TAG, "openHotSpotSetting: ");
        Intent intent = new Intent("android.intent.action.VIEW", Uri.parse(this.schemeHotSpot));
        intent.setComponent(new ComponentName(SERVICE_PKG, SERVICE_CLASS));
        List<ResolveInfo> listQueryIntentServices = getPackageManager().queryIntentServices(intent, 0);
        RLog.i(TAG, "----startIntentByPath----resolveInfos = " + listQueryIntentServices);
        if (listQueryIntentServices.isEmpty()) {
            return;
        }
        startService(intent);
    }

    private void openBluetoothSetting() {
        RLog.d(TAG, "openBluetoothSetting: ");
        Intent intent = new Intent("android.intent.action.VIEW", Uri.parse(this.schemeBluetooth));
        intent.setComponent(new ComponentName(SERVICE_PKG, SERVICE_CLASS));
        List<ResolveInfo> listQueryIntentServices = getPackageManager().queryIntentServices(intent, 0);
        RLog.i(TAG, "----startIntentByPath----resolveInfos = " + listQueryIntentServices);
        if (listQueryIntentServices.isEmpty()) {
            return;
        }
        startService(intent);
    }

    private void openApp(String str) {
        PackageManager packageManager = BydVoiceApplication.getContext().getPackageManager();
        Intent intent = new Intent("android.intent.action.MAIN");
        intent.addCategory("android.intent.category.LAUNCHER");
        intent.setPackage(str);
        List<ResolveInfo> listQueryIntentActivities = packageManager.queryIntentActivities(intent, 0);
        if (listQueryIntentActivities != null) {
            RLog.i(TAG, "queryIntentAcitivites=>" + listQueryIntentActivities.size());
            for (ResolveInfo resolveInfo : listQueryIntentActivities) {
                String str2 = resolveInfo.activityInfo.packageName;
                String str3 = resolveInfo.activityInfo.name;
                RLog.i(TAG, "className=>" + str3);
                RLog.i(TAG, "openApp: home is Top,start activity=>" + str2 + "," + str3);
                Intent intent2 = new Intent("android.intent.action.MAIN");
                intent2.addCategory("android.intent.category.LAUNCHER");
                intent2.setFlags(272629760);
                intent2.setComponent(new ComponentName(str2, str3));
            }
        }
    }

    private void openApp2(String str, String str2) {
        Intent intent = new Intent("android.intent.action.MAIN");
        intent.setFlags(268435456);
        intent.addCategory("android.intent.category.LAUNCHER");
        intent.setComponent(new ComponentName(str, str2));
        startActivity(intent);
    }
}
