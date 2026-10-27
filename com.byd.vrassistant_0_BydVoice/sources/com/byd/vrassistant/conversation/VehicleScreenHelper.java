package com.byd.vrassistant.conversation;

import android.app.UiModeManager;
import android.hardware.bydauto.instrument.BYDAutoInstrumentDevice;
import android.hardware.bydauto.setting.BYDAutoSettingDevice;
import cerence.ark.assistant.api.BaseResult;
import cerence.ark.assistant.api.IResponseSender;
import cerence.ark.assistant.api.Response;
import cerence.ark.assistant.controller.domain.vehiclecontrol.param.GetScreenDisplayModeParam;
import cerence.ark.assistant.controller.domain.vehiclecontrol.param.SetScreenOnOffParam;
import cerence.ark.assistant.controller.domain.vehiclecontrol.param.SetScreenOrientationParam;
import cerence.ark.assistant.controller.domain.vehiclecontrol.result.CheckCPAAInForegroundResult;
import cerence.ark.assistant.controller.domain.vehiclecontrol.result.CheckScreenStateResult;
import cerence.ark.assistant.controller.domain.vehiclecontrol.result.GetScreenDisplayModeResult;
import cerence.ark.assistant.controller.enumeration.CPAAStateEnum;
import cerence.ark.assistant.controller.enumeration.DisplayModeEnum;
import cerence.ark.assistant.controller.enumeration.ResultCodeEnum;
import cerence.ark.assistant.controller.enumeration.ScreenDeviceEnum;
import cerence.ark.assistant.controller.enumeration.ScreenOrientationEnum;
import com.byd.vrassistant.BydVoiceApplication;
import com.byd.vrassistant.Constant;
import com.byd.vrassistant.NeuVoiceService;
import com.byd.vrassistant.manager.SystemSettingManager;
import com.byd.vrassistant.utils.ForegroundAppUtil;
import com.byd.vrassistant.utils.RLog;
import com.byd.vrassistant.utils.TagUtil;

/* JADX INFO: loaded from: classes.dex */
public class VehicleScreenHelper {
    private static final String TAG = TagUtil.getTagName(VehicleScreenHelper.class);
    private BYDAutoInstrumentDevice instrumentDevice;
    private BYDAutoSettingDevice settingDevice;

    private int getScreenIntValue100(int i) {
        return (int) (((double) i) / 2.55d);
    }

    private static final class SingletonHolder {
        private static final VehicleScreenHelper INSTANCE = new VehicleScreenHelper();

        private SingletonHolder() {
        }
    }

    public static VehicleScreenHelper getInstance() {
        RLog.i(TAG, "getInstance()");
        return SingletonHolder.INSTANCE;
    }

    private VehicleScreenHelper() {
        try {
            RLog.i(TAG, "VehicleScreenHelper()");
            this.instrumentDevice = BYDAutoInstrumentDevice.getInstance(BydVoiceApplication.getContext());
            this.settingDevice = BYDAutoSettingDevice.getInstance(BydVoiceApplication.getContext());
        } catch (Exception e) {
            RLog.i(TAG, "VehicleScreenHelper() Exception ", e);
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:14:0x006e  */
    /* JADX WARN: Removed duplicated region for block: B:17:0x0082  */
    /* JADX WARN: Removed duplicated region for block: B:27:0x00e4 A[Catch: Exception -> 0x0179, TRY_LEAVE, TryCatch #1 {Exception -> 0x0179, blocks: (B:3:0x0023, B:19:0x0085, B:20:0x0095, B:22:0x00ab, B:23:0x00bb, B:25:0x00c1, B:26:0x00d4, B:27:0x00e4, B:8:0x005a, B:11:0x0064), top: B:47:0x0023 }] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public void setScreenBrightness(cerence.ark.assistant.controller.domain.vehiclecontrol.param.SetScreenBrightnessParam r22, cerence.ark.assistant.api.IResponseSender<cerence.ark.assistant.api.BaseResult> r23) {
        /*
            Method dump skipped, instruction units count: 398
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.byd.vrassistant.conversation.VehicleScreenHelper.setScreenBrightness(cerence.ark.assistant.controller.domain.vehiclecontrol.param.SetScreenBrightnessParam, cerence.ark.assistant.api.IResponseSender):void");
    }

    public boolean setBacklightBrightness(int i) {
        String str = TAG;
        RLog.i(str, "setBacklightBrightness() called with: brightness = [" + i + "]");
        if (i < 0) {
            i = 0;
        } else if (i > 100) {
            i = 100;
        }
        int instrumentBrightness = this.instrumentDevice.setInstrumentBrightness((i / 10) + 1);
        RLog.i(str, "setBacklightBrightness: intValue = " + instrumentBrightness);
        return instrumentBrightness == 0;
    }

    public int getBacklightBrightness() {
        String str = TAG;
        RLog.i(str, "getBacklightBrightness() called");
        int instrumentBrightness = this.instrumentDevice.getInstrumentBrightness();
        if (instrumentBrightness > 11) {
            instrumentBrightness -= 11;
        }
        int i = (instrumentBrightness - 1) * 10;
        RLog.i(str, "getBacklightBrightness: brightness = " + i);
        return i;
    }

    private int getScreenIntValue255(int i) {
        return (int) Math.ceil(((double) i) * 2.55d);
    }

    /* JADX WARN: Removed duplicated region for block: B:14:0x005a  */
    /* JADX WARN: Removed duplicated region for block: B:17:0x006b  */
    /* JADX WARN: Removed duplicated region for block: B:30:0x00d4 A[Catch: Exception -> 0x0152, TryCatch #1 {Exception -> 0x0152, blocks: (B:3:0x0016, B:18:0x006d, B:23:0x0095, B:24:0x00a5, B:26:0x00af, B:28:0x00be, B:30:0x00d4, B:32:0x00f7, B:36:0x0119, B:35:0x0109, B:37:0x0143, B:8:0x0046, B:11:0x0050), top: B:44:0x0016 }] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public void increaseScreenBrightness(cerence.ark.assistant.controller.domain.vehiclecontrol.param.IncreaseScreenBrightnessParam r22, cerence.ark.assistant.api.IResponseSender<cerence.ark.assistant.api.BaseResult> r23) {
        /*
            Method dump skipped, instruction units count: 355
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.byd.vrassistant.conversation.VehicleScreenHelper.increaseScreenBrightness(cerence.ark.assistant.controller.domain.vehiclecontrol.param.IncreaseScreenBrightnessParam, cerence.ark.assistant.api.IResponseSender):void");
    }

    /* JADX WARN: Removed duplicated region for block: B:14:0x005a  */
    /* JADX WARN: Removed duplicated region for block: B:17:0x006b  */
    /* JADX WARN: Removed duplicated region for block: B:29:0x00d2 A[Catch: Exception -> 0x0148, TryCatch #1 {Exception -> 0x0148, blocks: (B:3:0x0016, B:18:0x006d, B:22:0x0093, B:23:0x00a3, B:25:0x00ad, B:27:0x00bc, B:29:0x00d2, B:31:0x00f3, B:35:0x010f, B:34:0x0100, B:36:0x0139, B:8:0x0046, B:11:0x0050), top: B:43:0x0016 }] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public void decreaseScreenBrightness(cerence.ark.assistant.controller.domain.vehiclecontrol.param.DecreaseScreenBrightnessParam r18, cerence.ark.assistant.api.IResponseSender<cerence.ark.assistant.api.BaseResult> r19) {
        /*
            Method dump skipped, instruction units count: 345
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.byd.vrassistant.conversation.VehicleScreenHelper.decreaseScreenBrightness(cerence.ark.assistant.controller.domain.vehiclecontrol.param.DecreaseScreenBrightnessParam, cerence.ark.assistant.api.IResponseSender):void");
    }

    /* JADX WARN: Removed duplicated region for block: B:14:0x005c  */
    /* JADX WARN: Removed duplicated region for block: B:17:0x0068  */
    /* JADX WARN: Removed duplicated region for block: B:21:0x00a9 A[Catch: Exception -> 0x00f8, TRY_LEAVE, TryCatch #1 {Exception -> 0x00f8, blocks: (B:3:0x0013, B:18:0x006a, B:20:0x007c, B:21:0x00a9, B:8:0x0048, B:11:0x0052), top: B:31:0x0013 }] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public void getScreenBrightness(cerence.ark.assistant.controller.domain.vehiclecontrol.param.GetScreenBrightnessParam r17, cerence.ark.assistant.api.IResponseSender<cerence.ark.assistant.controller.domain.vehiclecontrol.result.GetScreenBrightnessResult> r18) {
        /*
            Method dump skipped, instruction units count: 267
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.byd.vrassistant.conversation.VehicleScreenHelper.getScreenBrightness(cerence.ark.assistant.controller.domain.vehiclecontrol.param.GetScreenBrightnessParam, cerence.ark.assistant.api.IResponseSender):void");
    }

    /* JADX WARN: Removed duplicated region for block: B:14:0x005a  */
    /* JADX WARN: Removed duplicated region for block: B:17:0x0065  */
    /* JADX WARN: Removed duplicated region for block: B:20:0x0087 A[Catch: Exception -> 0x01a1, TryCatch #1 {Exception -> 0x01a1, blocks: (B:3:0x0012, B:18:0x0067, B:19:0x0077, B:20:0x0087, B:27:0x00bc, B:30:0x00c6, B:33:0x00d0, B:8:0x0046, B:11:0x0050), top: B:61:0x0012 }] */
    /* JADX WARN: Removed duplicated region for block: B:36:0x00da  */
    /* JADX WARN: Removed duplicated region for block: B:39:0x00f6  */
    /* JADX WARN: Removed duplicated region for block: B:52:0x0172  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public void setScreenDisplayMode(cerence.ark.assistant.controller.domain.vehiclecontrol.param.SetScreenDisplayModeParam r21, cerence.ark.assistant.api.IResponseSender<cerence.ark.assistant.api.BaseResult> r22) {
        /*
            Method dump skipped, instruction units count: 446
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.byd.vrassistant.conversation.VehicleScreenHelper.setScreenDisplayMode(cerence.ark.assistant.controller.domain.vehiclecontrol.param.SetScreenDisplayModeParam, cerence.ark.assistant.api.IResponseSender):void");
    }

    public void getScreenDisplayMode(GetScreenDisplayModeParam getScreenDisplayModeParam, IResponseSender<GetScreenDisplayModeResult> iResponseSender) {
        try {
            String str = TAG;
            RLog.i(str, "getScreenDisplayMode: json = " + getScreenDisplayModeParam.toJsonString());
            RLog.i(Constant.TAG_FORTEST, "request domain【CustomVehicleControlDomain】,api【getScreenDisplayMode】");
            GetScreenDisplayModeResult getScreenDisplayModeResult = new GetScreenDisplayModeResult();
            if (ScreenDeviceEnum.IVI.equals(getScreenDisplayModeParam.getScreenDevice())) {
                int nightMode = ((UiModeManager) BydVoiceApplication.getContext().getSystemService("uimode")).getNightMode();
                RLog.i(str, "getScreenDisplayMode: sysScreenMode = " + nightMode);
                if (nightMode == 0) {
                    getScreenDisplayModeResult.setScreenDisplayMode(DisplayModeEnum.AUTO_MODE);
                } else if (nightMode == 1) {
                    getScreenDisplayModeResult.setScreenDisplayMode(DisplayModeEnum.DAY_MODE);
                } else if (nightMode == 2) {
                    getScreenDisplayModeResult.setScreenDisplayMode(DisplayModeEnum.NIGHT_MODE);
                } else {
                    getScreenDisplayModeResult.setScreenDisplayMode("UNSUPPORTED");
                    RLog.i(str, "getScreenDisplayMode: UNSUPPORTED");
                    RLog.i(Constant.TAG_FORTEST, "request domain【CustomVehicleControlDomain】,api【getScreenDisplayMode】,result【UNSUPPORTED】");
                    iResponseSender.send(new Response("UNSUPPORTED"));
                    return;
                }
                RLog.i(str, "getScreenDisplayMode: OK");
                RLog.i(Constant.TAG_FORTEST, "request domain【CustomVehicleControlDomain】,api【getScreenDisplayMode】,result【OK】");
                iResponseSender.send(new Response("OK", getScreenDisplayModeResult));
                return;
            }
            RLog.i(str, "getScreenDisplayMode: UNSUPPORTED");
            RLog.i(Constant.TAG_FORTEST, "request domain【CustomVehicleControlDomain】,api【getScreenDisplayMode】,result【UNSUPPORTED】");
            iResponseSender.send(new Response("UNSUPPORTED"));
        } catch (Exception unused) {
            RLog.i(TAG, "getScreenDisplayMode: ERROR");
            RLog.i(Constant.TAG_FORTEST, "request domain【CustomVehicleControlDomain】,api【getScreenDisplayMode】,result【ERROR】");
            iResponseSender.send(new Response(ResultCodeEnum.ERROR));
        }
    }

    public void rotateScreen(IResponseSender<BaseResult> iResponseSender) {
        try {
            String str = TAG;
            RLog.i(str, "rotateScreen: json = ");
            RLog.i(Constant.TAG_FORTEST, "request domain【CustomVehicleControlDomain】,api【rotateScreen】");
            int i = 1;
            if (BydVoiceApplication.getContext().getResources().getConfiguration().orientation == 1) {
                RLog.i(str, "controlSunroof: 竖屏 ==> 横屏");
            } else {
                RLog.i(str, "controlSunroof: 横屏 ==> 竖屏");
                i = 2;
            }
            int screenRotation = this.settingDevice.setScreenRotation(i);
            if (screenRotation != 0) {
                RLog.i(str, "rotateScreen: ERROR");
                RLog.i(Constant.TAG_FORTEST, "request domain【CustomVehicleControlDomain】,api【rotateScreen】,result【ERROR】");
                iResponseSender.send(new Response(ResultCodeEnum.ERROR));
            } else {
                RLog.i(str, "rotateScreen: OK   intValue  = " + screenRotation);
                RLog.i(Constant.TAG_FORTEST, "request domain【CustomVehicleControlDomain】,api【rotateScreen】,result【OK】");
                iResponseSender.send(new Response("OK"));
            }
        } catch (Exception unused) {
            RLog.i(TAG, "rotateScreen: ERROR");
            RLog.i(Constant.TAG_FORTEST, "request domain【CustomVehicleControlDomain】,api【rotateScreen】,result【ERROR】");
            iResponseSender.send(new Response(ResultCodeEnum.ERROR));
        }
    }

    public void setScreenOrientation(SetScreenOrientationParam setScreenOrientationParam, IResponseSender<BaseResult> iResponseSender) {
        try {
            String str = TAG;
            RLog.i(str, "setScreenOrientation: json = ");
            RLog.i(Constant.TAG_FORTEST, "request domain【CustomVehicleControlDomain】,api【setScreenOrientation】");
            String screenDevice = setScreenOrientationParam.getScreenDevice();
            String orientation = setScreenOrientationParam.getOrientation();
            if (ScreenDeviceEnum.IVI.equals(screenDevice)) {
                RLog.i(str, "setScreenOrientation: IVI");
                int i = BydVoiceApplication.getContext().getResources().getConfiguration().orientation;
                int i2 = 1;
                if (ScreenOrientationEnum.SCREEN_HORIZONTAL.equals(orientation)) {
                    RLog.i(str, "setScreenOrientation: SCREEN_HORIZONTAL");
                    if (i != 1) {
                        RLog.i(str, "setScreenOrientation: ALREADY_EXPECTED");
                        RLog.i(Constant.TAG_FORTEST, "request domain【CustomVehicleControlDomain】,api【setScreenOrientation】,result【ALREADY_EXPECTED】");
                        iResponseSender.send(new Response("ALREADY_EXPECTED"));
                        return;
                    }
                } else {
                    RLog.i(str, "setScreenOrientation: SCREEN_VERTICAL");
                    if (i == 1) {
                        RLog.i(str, "setScreenOrientation: ALREADY_EXPECTED");
                        RLog.i(Constant.TAG_FORTEST, "request domain【CustomVehicleControlDomain】,api【setScreenOrientation】,result【ALREADY_EXPECTED】");
                        iResponseSender.send(new Response("ALREADY_EXPECTED"));
                        return;
                    }
                    i2 = 2;
                }
                int screenRotation = this.settingDevice.setScreenRotation(i2);
                if (screenRotation != 0) {
                    RLog.i(str, "setScreenOrientation: ERROR");
                    RLog.i(Constant.TAG_FORTEST, "request domain【CustomVehicleControlDomain】,api【setScreenOrientation】,result【ERROR】");
                    iResponseSender.send(new Response(ResultCodeEnum.ERROR));
                    return;
                } else {
                    RLog.i(str, "setScreenOrientation: OK   intValue  = " + screenRotation);
                    RLog.i(Constant.TAG_FORTEST, "request domain【CustomVehicleControlDomain】,api【setScreenOrientation】,result【OK】");
                    iResponseSender.send(new Response("OK"));
                }
            } else {
                RLog.i(str, "setScreenOrientation: other UNSUPPORTED");
                RLog.i(Constant.TAG_FORTEST, "request domain【CustomVehicleControlDomain】,api【setScreenOrientation】,result【UNSUPPORTED】");
                iResponseSender.send(new Response("UNSUPPORTED"));
            }
            RLog.i(Constant.TAG_FORTEST, "request domain【CustomVehicleControlDomain】,api【setScreenOrientation】,result【OK】");
            iResponseSender.send(new Response("OK"));
        } catch (Exception unused) {
            RLog.i(TAG, "setScreenOrientation: ERROR");
            RLog.i(Constant.TAG_FORTEST, "request domain【CustomVehicleControlDomain】,api【setScreenOrientation】,result【ERROR】");
            iResponseSender.send(new Response(ResultCodeEnum.ERROR));
        }
    }

    public void checkScreenState(IResponseSender<CheckScreenStateResult> iResponseSender) {
        try {
            String str = TAG;
            RLog.i(str, "checkScreenState: json = ");
            RLog.i(Constant.TAG_FORTEST, "request domain【CustomVehicleControlDomain】,api【checkScreenState】");
            int i = BydVoiceApplication.getContext().getResources().getConfiguration().orientation;
            CheckScreenStateResult checkScreenStateResult = new CheckScreenStateResult();
            if (i == 1) {
                checkScreenStateResult.setState(ScreenOrientationEnum.SCREEN_VERTICAL);
            } else {
                checkScreenStateResult.setState(ScreenOrientationEnum.SCREEN_HORIZONTAL);
            }
            RLog.i(str, "checkScreenState: OK result = " + checkScreenStateResult.toJsonString());
            RLog.i(Constant.TAG_FORTEST, "request domain【CustomVehicleControlDomain】,api【checkScreenState】,result【OK】");
            iResponseSender.send(new Response("OK", checkScreenStateResult));
        } catch (Exception unused) {
            RLog.i(TAG, "checkScreenState: ERROR");
            RLog.i(Constant.TAG_FORTEST, "request domain【CustomVehicleControlDomain】,api【checkScreenState】,result【ERROR】");
            iResponseSender.send(new Response(ResultCodeEnum.ERROR));
        }
    }

    public void checkCPAAInForeground(IResponseSender<CheckCPAAInForegroundResult> iResponseSender) {
        try {
            String str = TAG;
            RLog.i(str, "checkCPAAInForeground: json = ");
            RLog.i(Constant.TAG_FORTEST, "request domain【CustomVehicleControlDomain】,api【checkCPAAInForeground】");
            String str2 = NeuVoiceService.topAppPkg;
            String topActivityPackage = ForegroundAppUtil.getTopActivityPackage();
            boolean carplayConnectStatus = SystemSettingManager.get().getCarplayConnectStatus();
            RLog.i(str, "controlSunroof: carplayConnected = " + carplayConnectStatus);
            boolean androidAutoConnectStatus = SystemSettingManager.get().getAndroidAutoConnectStatus();
            RLog.i(str, "controlSunroof: aaConnected = " + androidAutoConnectStatus);
            RLog.i(str, "controlSunroof: topAppPkg = " + str2);
            CheckCPAAInForegroundResult checkCPAAInForegroundResult = new CheckCPAAInForegroundResult();
            if ((VehicleSettingsHelper.PACKAGE_ANDROID_AUTO.equals(str2) || VehicleSettingsHelper.PACKAGE_ANDROID_AUTO.equals(topActivityPackage)) && androidAutoConnectStatus) {
                checkCPAAInForegroundResult.setState(CPAAStateEnum.AA_FOREGROUND);
            } else if ((VehicleSettingsHelper.PACKAGE_CAR_PLAY.equals(str2) || VehicleSettingsHelper.PACKAGE_CAR_PLAY.equals(topActivityPackage)) && carplayConnectStatus) {
                checkCPAAInForegroundResult.setState(CPAAStateEnum.CP_FOREGROUND);
            } else if (androidAutoConnectStatus) {
                checkCPAAInForegroundResult.setState(CPAAStateEnum.AA_BACKGROUND);
            } else if (carplayConnectStatus) {
                checkCPAAInForegroundResult.setState(CPAAStateEnum.CP_BACKGROUND);
            } else {
                checkCPAAInForegroundResult.setState("UNKNOWN");
            }
            RLog.i(str, "checkCPAAInForeground: OK result = " + checkCPAAInForegroundResult.toJsonString());
            RLog.i(Constant.TAG_FORTEST, "request domain【CustomVehicleControlDomain】,api【checkCPAAInForeground】,result【OK】");
            iResponseSender.send(new Response("OK", checkCPAAInForegroundResult));
        } catch (Exception unused) {
            RLog.i(TAG, "checkCPAAInForeground: ERROR");
            RLog.i(Constant.TAG_FORTEST, "request domain【CustomVehicleControlDomain】,api【checkCPAAInForeground】,result【ERROR】");
            iResponseSender.send(new Response(ResultCodeEnum.ERROR));
        }
    }

    public void setScreenOnOff(SetScreenOnOffParam setScreenOnOffParam, IResponseSender<BaseResult> iResponseSender) {
        try {
            String str = TAG;
            RLog.i(str, "setScreenOnOff: json = " + setScreenOnOffParam.toJsonString());
            RLog.i(Constant.TAG_FORTEST, "request domain【CustomVehicleControlDomain】,api【setScreenOnOff】");
            String screenDevice = setScreenOnOffParam.getScreenDevice();
            String state = setScreenOnOffParam.getState();
            if (ScreenDeviceEnum.IVI.equals(screenDevice)) {
                RLog.i(str, "setScreenOnOff: IVI");
                int powerScreenStatus = SystemSettingManager.get().getPowerScreenStatus();
                RLog.i(str, "setScreenOnOff: powerScreenStatus = " + powerScreenStatus);
                if ("ON".equals(state)) {
                    RLog.i(str, "setScreenOnOff: SwitchEnum.ON");
                    if (powerScreenStatus == 255) {
                        RLog.i(str, "setScreenOnOff: ALREADY_EXPECTED");
                        RLog.i(Constant.TAG_FORTEST, "request domain【CustomVehicleControlDomain】,api【setScreenOnOff】,result【ALREADY_EXPECTED】");
                        iResponseSender.send(new Response("ALREADY_EXPECTED"));
                    } else {
                        SystemSettingManager.get().turnBackLightOn();
                        RLog.i(str, "setScreenOnOff: OK");
                        RLog.i(Constant.TAG_FORTEST, "request domain【CustomVehicleControlDomain】,api【setScreenOnOff】,result【OK】");
                        iResponseSender.send(new Response("OK"));
                    }
                } else {
                    RLog.i(str, "setScreenOnOff: SwitchEnum.OFF");
                    if (powerScreenStatus == 0) {
                        RLog.i(str, "setScreenOnOff: ALREADY_EXPECTED");
                        RLog.i(Constant.TAG_FORTEST, "request domain【CustomVehicleControlDomain】,api【setScreenOnOff】,result【ALREADY_EXPECTED】");
                        iResponseSender.send(new Response("ALREADY_EXPECTED"));
                    } else {
                        SystemSettingManager.get().turnBackLightOff();
                        RLog.i(str, "setScreenOnOff: OK");
                        RLog.i(Constant.TAG_FORTEST, "request domain【CustomVehicleControlDomain】,api【setScreenOnOff】,result【OK】");
                        iResponseSender.send(new Response("OK"));
                    }
                }
            } else {
                RLog.i(str, "setScreenOnOff: UNSUPPORTED");
                RLog.i(Constant.TAG_FORTEST, "request domain【CustomVehicleControlDomain】,api【setScreenOnOff】,result【UNSUPPORTED】");
                iResponseSender.send(new Response("UNSUPPORTED"));
            }
        } catch (Exception unused) {
            RLog.i(TAG, "setScreenOnOff: ERROR");
            RLog.i(Constant.TAG_FORTEST, "request domain【CustomVehicleControlDomain】,api【setScreenOnOff】,result【ERROR】");
            iResponseSender.send(new Response(ResultCodeEnum.ERROR));
        }
    }
}
