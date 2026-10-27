package com.byd.vrassistant.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;
import com.byd.vrassistant.R;

/* JADX INFO: loaded from: classes.dex */
public abstract class ActivityAcBinding extends ViewDataBinding {
    public final Button back;
    public final TextView rotate;
    public final TextView tvAcCompressorMode;
    public final TextView tvAcCycleMode;
    public final TextView tvAcDefrostState;
    public final TextView tvAcMaxCoolingState;
    public final TextView tvAcOnlineState;
    public final TextView tvAcRearWindMode;
    public final TextView tvAcStartState;
    public final TextView tvAcTemperatureControlMode;
    public final TextView tvAcType;
    public final TextView tvAcVentilationState;
    public final TextView tvAcWindLevel;
    public final TextView tvAcWindMode;
    public final TextView tvBacklightBrightness;
    public final TextView tvCurrentSystemMode;
    public final TextView tvGetFanModeNum;
    public final TextView tvHasFeatureSeatVentilatingHeating;
    public final TextView tvIalBrightness;
    public final TextView tvIalInfo;
    public final TextView tvIsDivisionSingle;
    public final TextView tvIsSupportEcoAndSport;
    public final TextView tvOpenAndroidauto;
    public final TextView tvOpenBtMusic;
    public final TextView tvOpenBtPhone;
    public final TextView tvOpenCamera;
    public final TextView tvOpenCarplay;
    public final TextView tvOpenCarsettings;
    public final TextView tvOpenFilemanager;
    public final TextView tvOpenNavi;
    public final TextView tvOpenPhoto;
    public final TextView tvOpenSpotify;
    public final TextView tvOpenSystemSetting;
    public final TextView tvOperationMode;
    public final TextView tvPowerLevel;
    public final TextView tvRearAcOnlineState;
    public final TextView tvRearAcStartState;
    public final TextView tvRearAcWindLevel;
    public final TextView tvScreenBrightness;
    public final TextView tvSeatHeatingState;
    public final TextView tvSeatVentilatingState;
    public final TextView tvSetAcCompressorMode;
    public final TextView tvSetAcControlMode;
    public final TextView tvSetAcCycleMode;
    public final TextView tvSetAcDefrostState;
    public final TextView tvSetAcRearWindMode;
    public final TextView tvSetAcTemperature;
    public final TextView tvSetAcTemperatureControlMode;
    public final TextView tvSetAcVentilationState;
    public final TextView tvSetAcWindLevel;
    public final TextView tvSetAcWindMode;
    public final TextView tvSetAllWindowState;
    public final TextView tvSetBacklightBrightness;
    public final TextView tvSetBodyWindowCtrlState;
    public final TextView tvSetCurrentSystemMode;
    public final TextView tvSetIalBrightness;
    public final TextView tvSetIalInfo;
    public final TextView tvSetOperationMode;
    public final TextView tvSetRearAcControlMode;
    public final TextView tvSetRearAcWindLevel;
    public final TextView tvSetScreenBrightness;
    public final TextView tvSetSeatHeatingState;
    public final TextView tvSetSeatVentilatingState;
    public final TextView tvSetStreamMute;
    public final TextView tvSetStreamVolume;
    public final TextView tvStart;
    public final TextView tvStartRearAc;
    public final TextView tvStop;
    public final TextView tvStopRearAc;
    public final TextView tvSupportAmbientLight;
    public final TextView tvSystemInt;
    public final TextView tvTemprature;
    public final TextView tvTvOpenBluetoothSetting;
    public final TextView tvTvOpenHotSpotSetting;
    public final TextView tvTvOpenVehicleImage;
    public final TextView tvTvOpenWifiSetting;
    public final TextView tvValideCamera;
    public final TextView tvVoiceCtlMoonRoof;
    public final TextView tvVoiceCtlSunshadePanel;
    public final TextView tvWarmOnlineState;
    public final TextView tvWindowOpenPercent;
    public final TextView tvWindowState;

    protected ActivityAcBinding(Object obj, View view, int i, Button button, TextView textView, TextView textView2, TextView textView3, TextView textView4, TextView textView5, TextView textView6, TextView textView7, TextView textView8, TextView textView9, TextView textView10, TextView textView11, TextView textView12, TextView textView13, TextView textView14, TextView textView15, TextView textView16, TextView textView17, TextView textView18, TextView textView19, TextView textView20, TextView textView21, TextView textView22, TextView textView23, TextView textView24, TextView textView25, TextView textView26, TextView textView27, TextView textView28, TextView textView29, TextView textView30, TextView textView31, TextView textView32, TextView textView33, TextView textView34, TextView textView35, TextView textView36, TextView textView37, TextView textView38, TextView textView39, TextView textView40, TextView textView41, TextView textView42, TextView textView43, TextView textView44, TextView textView45, TextView textView46, TextView textView47, TextView textView48, TextView textView49, TextView textView50, TextView textView51, TextView textView52, TextView textView53, TextView textView54, TextView textView55, TextView textView56, TextView textView57, TextView textView58, TextView textView59, TextView textView60, TextView textView61, TextView textView62, TextView textView63, TextView textView64, TextView textView65, TextView textView66, TextView textView67, TextView textView68, TextView textView69, TextView textView70, TextView textView71, TextView textView72, TextView textView73, TextView textView74, TextView textView75, TextView textView76, TextView textView77, TextView textView78, TextView textView79, TextView textView80, TextView textView81) {
        super(obj, view, i);
        this.back = button;
        this.rotate = textView;
        this.tvAcCompressorMode = textView2;
        this.tvAcCycleMode = textView3;
        this.tvAcDefrostState = textView4;
        this.tvAcMaxCoolingState = textView5;
        this.tvAcOnlineState = textView6;
        this.tvAcRearWindMode = textView7;
        this.tvAcStartState = textView8;
        this.tvAcTemperatureControlMode = textView9;
        this.tvAcType = textView10;
        this.tvAcVentilationState = textView11;
        this.tvAcWindLevel = textView12;
        this.tvAcWindMode = textView13;
        this.tvBacklightBrightness = textView14;
        this.tvCurrentSystemMode = textView15;
        this.tvGetFanModeNum = textView16;
        this.tvHasFeatureSeatVentilatingHeating = textView17;
        this.tvIalBrightness = textView18;
        this.tvIalInfo = textView19;
        this.tvIsDivisionSingle = textView20;
        this.tvIsSupportEcoAndSport = textView21;
        this.tvOpenAndroidauto = textView22;
        this.tvOpenBtMusic = textView23;
        this.tvOpenBtPhone = textView24;
        this.tvOpenCamera = textView25;
        this.tvOpenCarplay = textView26;
        this.tvOpenCarsettings = textView27;
        this.tvOpenFilemanager = textView28;
        this.tvOpenNavi = textView29;
        this.tvOpenPhoto = textView30;
        this.tvOpenSpotify = textView31;
        this.tvOpenSystemSetting = textView32;
        this.tvOperationMode = textView33;
        this.tvPowerLevel = textView34;
        this.tvRearAcOnlineState = textView35;
        this.tvRearAcStartState = textView36;
        this.tvRearAcWindLevel = textView37;
        this.tvScreenBrightness = textView38;
        this.tvSeatHeatingState = textView39;
        this.tvSeatVentilatingState = textView40;
        this.tvSetAcCompressorMode = textView41;
        this.tvSetAcControlMode = textView42;
        this.tvSetAcCycleMode = textView43;
        this.tvSetAcDefrostState = textView44;
        this.tvSetAcRearWindMode = textView45;
        this.tvSetAcTemperature = textView46;
        this.tvSetAcTemperatureControlMode = textView47;
        this.tvSetAcVentilationState = textView48;
        this.tvSetAcWindLevel = textView49;
        this.tvSetAcWindMode = textView50;
        this.tvSetAllWindowState = textView51;
        this.tvSetBacklightBrightness = textView52;
        this.tvSetBodyWindowCtrlState = textView53;
        this.tvSetCurrentSystemMode = textView54;
        this.tvSetIalBrightness = textView55;
        this.tvSetIalInfo = textView56;
        this.tvSetOperationMode = textView57;
        this.tvSetRearAcControlMode = textView58;
        this.tvSetRearAcWindLevel = textView59;
        this.tvSetScreenBrightness = textView60;
        this.tvSetSeatHeatingState = textView61;
        this.tvSetSeatVentilatingState = textView62;
        this.tvSetStreamMute = textView63;
        this.tvSetStreamVolume = textView64;
        this.tvStart = textView65;
        this.tvStartRearAc = textView66;
        this.tvStop = textView67;
        this.tvStopRearAc = textView68;
        this.tvSupportAmbientLight = textView69;
        this.tvSystemInt = textView70;
        this.tvTemprature = textView71;
        this.tvTvOpenBluetoothSetting = textView72;
        this.tvTvOpenHotSpotSetting = textView73;
        this.tvTvOpenVehicleImage = textView74;
        this.tvTvOpenWifiSetting = textView75;
        this.tvValideCamera = textView76;
        this.tvVoiceCtlMoonRoof = textView77;
        this.tvVoiceCtlSunshadePanel = textView78;
        this.tvWarmOnlineState = textView79;
        this.tvWindowOpenPercent = textView80;
        this.tvWindowState = textView81;
    }

    public static ActivityAcBinding inflate(LayoutInflater layoutInflater, ViewGroup viewGroup, boolean z) {
        return inflate(layoutInflater, viewGroup, z, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static ActivityAcBinding inflate(LayoutInflater layoutInflater, ViewGroup viewGroup, boolean z, Object obj) {
        return (ActivityAcBinding) ViewDataBinding.inflateInternal(layoutInflater, R.layout.activity_ac, viewGroup, z, obj);
    }

    public static ActivityAcBinding inflate(LayoutInflater layoutInflater) {
        return inflate(layoutInflater, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static ActivityAcBinding inflate(LayoutInflater layoutInflater, Object obj) {
        return (ActivityAcBinding) ViewDataBinding.inflateInternal(layoutInflater, R.layout.activity_ac, null, false, obj);
    }

    public static ActivityAcBinding bind(View view) {
        return bind(view, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static ActivityAcBinding bind(View view, Object obj) {
        return (ActivityAcBinding) bind(obj, view, R.layout.activity_ac);
    }
}
