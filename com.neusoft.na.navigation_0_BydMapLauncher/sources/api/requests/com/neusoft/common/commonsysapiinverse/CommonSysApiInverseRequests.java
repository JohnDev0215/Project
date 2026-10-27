package api.requests.com.neusoft.common.commonsysapiinverse;

import api.requests.com.neusoft.common.commonsysapiinverse.ReqAppFocusEventInfo;
import api.requests.com.neusoft.common.commonsysapiinverse.ReqDashboardMirrorEventInfo;
import api.requests.com.neusoft.common.commonsysapiinverse.ReqDataCollectionCheckboxStatus;
import api.requests.com.neusoft.common.commonsysapiinverse.ReqFeedbackActivateResult;
import api.requests.com.neusoft.common.commonsysapiinverse.ReqHMIFocusChanged;
import api.requests.com.neusoft.common.commonsysapiinverse.ReqMapZoomInOut;
import api.requests.com.neusoft.common.commonsysapiinverse.ReqMigrateNaviHMIPage;
import api.requests.com.neusoft.common.commonsysapiinverse.ReqNaviAppExitEventInfo;
import api.requests.com.neusoft.common.commonsysapiinverse.ReqNaviAppStartupEventInfo;
import api.requests.com.neusoft.common.commonsysapiinverse.ReqOnAccessCodingAvailable;
import api.requests.com.neusoft.common.commonsysapiinverse.ReqOnCopyFiles;
import api.requests.com.neusoft.common.commonsysapiinverse.ReqOnDBDataIntegrityCheck;
import api.requests.com.neusoft.common.commonsysapiinverse.ReqOnDeleteAllDBData;
import api.requests.com.neusoft.common.commonsysapiinverse.ReqOnDeleteFile;
import api.requests.com.neusoft.common.commonsysapiinverse.ReqOnExportDirectoryToUDisk;
import api.requests.com.neusoft.common.commonsysapiinverse.ReqOnExtractFiles;
import api.requests.com.neusoft.common.commonsysapiinverse.ReqOnLoadURL;
import api.requests.com.neusoft.common.commonsysapiinverse.ReqOnReadFile;
import api.requests.com.neusoft.common.commonsysapiinverse.ReqOnStopOneCoreStatus;
import api.requests.com.neusoft.common.commonsysapiinverse.ReqOnWriteFile;
import api.requests.com.neusoft.common.commonsysapiinverse.ReqReqNaviGuideStart;
import api.requests.com.neusoft.common.commonsysapiinverse.ReqReqNaviMapViewMode;
import api.requests.com.neusoft.common.commonsysapiinverse.ReqReqNaviSwitchFullView;
import api.requests.com.neusoft.common.commonsysapiinverse.ReqReqNaviZoomIn;
import api.requests.com.neusoft.common.commonsysapiinverse.ReqReqNaviZoomOut;
import api.requests.com.neusoft.common.commonsysapiinverse.ReqReqSwitchDayNightMode;
import api.requests.com.neusoft.common.commonsysapiinverse.ReqSetBatteryCapacityWH;
import api.requests.com.neusoft.common.commonsysapiinverse.ReqSetBrightnessValue;
import api.requests.com.neusoft.common.commonsysapiinverse.ReqSetCarType;
import api.requests.com.neusoft.common.commonsysapiinverse.ReqSetChargingPortType;
import api.requests.com.neusoft.common.commonsysapiinverse.ReqSetClusterSurfaceStatus;
import api.requests.com.neusoft.common.commonsysapiinverse.ReqSetDeviceId;
import api.requests.com.neusoft.common.commonsysapiinverse.ReqSetDownloadPath;
import api.requests.com.neusoft.common.commonsysapiinverse.ReqSetEnduranceMileage;
import api.requests.com.neusoft.common.commonsysapiinverse.ReqSetExpiredDate;
import api.requests.com.neusoft.common.commonsysapiinverse.ReqSetExtCloudServerStatus;
import api.requests.com.neusoft.common.commonsysapiinverse.ReqSetFuelType;
import api.requests.com.neusoft.common.commonsysapiinverse.ReqSetInstallationPath;
import api.requests.com.neusoft.common.commonsysapiinverse.ReqSetInternetSwitchStatus;
import api.requests.com.neusoft.common.commonsysapiinverse.ReqSetLowBatterySignal;
import api.requests.com.neusoft.common.commonsysapiinverse.ReqSetLowFuelSignal;
import api.requests.com.neusoft.common.commonsysapiinverse.ReqSetMapScreenStatus;
import api.requests.com.neusoft.common.commonsysapiinverse.ReqSetMapSurfaceStatus;
import api.requests.com.neusoft.common.commonsysapiinverse.ReqSetMapViewMode;
import api.requests.com.neusoft.common.commonsysapiinverse.ReqSetNaviMuteModeComm;
import api.requests.com.neusoft.common.commonsysapiinverse.ReqSetOnKeyDownValue;
import api.requests.com.neusoft.common.commonsysapiinverse.ReqSetOnlineFeatureAvailableStatus;
import api.requests.com.neusoft.common.commonsysapiinverse.ReqSetPeripheralStatus;
import api.requests.com.neusoft.common.commonsysapiinverse.ReqSetRegionalType;
import api.requests.com.neusoft.common.commonsysapiinverse.ReqSetRemainingElectricQuantity;
import api.requests.com.neusoft.common.commonsysapiinverse.ReqSetRemainingElectricQuantityKWH;
import api.requests.com.neusoft.common.commonsysapiinverse.ReqSetScreenDirection;
import api.requests.com.neusoft.common.commonsysapiinverse.ReqSetScreenMode;
import api.requests.com.neusoft.common.commonsysapiinverse.ReqSetSystemLocale;
import api.requests.com.neusoft.common.commonsysapiinverse.ReqSetSystemTime;
import api.requests.com.neusoft.common.commonsysapiinverse.ReqSetSystemTimeAndWeekDay;
import api.requests.com.neusoft.common.commonsysapiinverse.ReqSetSystemTimeChange;
import api.requests.com.neusoft.common.commonsysapiinverse.ReqSetSystemTimeFormat;
import api.requests.com.neusoft.common.commonsysapiinverse.ReqSetSystemTimeZoneOffset;
import api.requests.com.neusoft.common.commonsysapiinverse.ReqSetTraceLog;
import api.requests.com.neusoft.common.commonsysapiinverse.ReqSetUnitOfMeasurement;
import api.requests.com.neusoft.common.commonsysapiinverse.ReqSetVehicleBrakingStatus;
import api.requests.com.neusoft.common.commonsysapiinverse.ReqSetWidgetSurfaceStatus;
import api.requests.com.neusoft.common.commonsysapiinverse.ReqSignalStrength;
import api.requests.com.neusoft.common.commonsysapiinverse.ReqStartUpState;
import api.requests.com.neusoft.common.commonsysapiinverse.ReqStorageSpace;
import api.requests.com.neusoft.common.commonsysapiinverse.ReqSwitchMapSetting;
import api.requests.com.neusoft.common.commonsysapiinverse.ReqSwitchNaviLayoutDirection;
import api.requests.com.neusoft.common.commonsysapiinverse.ReqSwitchTrafficInfo;
import api.requests.com.neusoft.common.commonsysapiinverse.ReqUpdateDB;
import api.requests.com.neusoft.common.commonsysapiinverse.ReqUpdateExternalStoragePath;
import api.responses.com.neusoft.common.commonsysapiinverse.ResBackToCCP;
import api.responses.com.neusoft.common.commonsysapiinverse.ResCheckNaviGuide;
import api.responses.com.neusoft.common.commonsysapiinverse.ResGetAbandonSpeak;
import api.responses.com.neusoft.common.commonsysapiinverse.ResGetAccessCodingAvailable;
import api.responses.com.neusoft.common.commonsysapiinverse.ResGetAppLocale;
import api.responses.com.neusoft.common.commonsysapiinverse.ResGetApplyUpdateStatus;
import api.responses.com.neusoft.common.commonsysapiinverse.ResGetCallUpStatus;
import api.responses.com.neusoft.common.commonsysapiinverse.ResGetCheckDBAvailable;
import api.responses.com.neusoft.common.commonsysapiinverse.ResGetCloseAndRestartApp;
import api.responses.com.neusoft.common.commonsysapiinverse.ResGetCloseApp;
import api.responses.com.neusoft.common.commonsysapiinverse.ResGetCloseMainActivity;
import api.responses.com.neusoft.common.commonsysapiinverse.ResGetCopyFiles;
import api.responses.com.neusoft.common.commonsysapiinverse.ResGetCurrentLocation;
import api.responses.com.neusoft.common.commonsysapiinverse.ResGetDBDataIntegrityCheck;
import api.responses.com.neusoft.common.commonsysapiinverse.ResGetDayNightMode;
import api.responses.com.neusoft.common.commonsysapiinverse.ResGetDelegateSystemEvent;
import api.responses.com.neusoft.common.commonsysapiinverse.ResGetDeleteAllDBData;
import api.responses.com.neusoft.common.commonsysapiinverse.ResGetDeleteFile;
import api.responses.com.neusoft.common.commonsysapiinverse.ResGetExportDirectoryToUDisk;
import api.responses.com.neusoft.common.commonsysapiinverse.ResGetExtractFiles;
import api.responses.com.neusoft.common.commonsysapiinverse.ResGetGetLowFuelStatus;
import api.responses.com.neusoft.common.commonsysapiinverse.ResGetGuidanceInfoUpdate;
import api.responses.com.neusoft.common.commonsysapiinverse.ResGetGuideInfo;
import api.responses.com.neusoft.common.commonsysapiinverse.ResGetImmersionStatus;
import api.responses.com.neusoft.common.commonsysapiinverse.ResGetLaunchApp;
import api.responses.com.neusoft.common.commonsysapiinverse.ResGetLoadURL;
import api.responses.com.neusoft.common.commonsysapiinverse.ResGetLocalDBVersionInfo;
import api.responses.com.neusoft.common.commonsysapiinverse.ResGetMapAppStatus;
import api.responses.com.neusoft.common.commonsysapiinverse.ResGetMapScaleLevel;
import api.responses.com.neusoft.common.commonsysapiinverse.ResGetMapSettingStatus;
import api.responses.com.neusoft.common.commonsysapiinverse.ResGetMapViewMode;
import api.responses.com.neusoft.common.commonsysapiinverse.ResGetMonitorCOMMStatus;
import api.responses.com.neusoft.common.commonsysapiinverse.ResGetNaviDayNightStatusChange;
import api.responses.com.neusoft.common.commonsysapiinverse.ResGetNaviMapViewMode;
import api.responses.com.neusoft.common.commonsysapiinverse.ResGetNaviMuteModeComm;
import api.responses.com.neusoft.common.commonsysapiinverse.ResGetNaviPresentationInfo;
import api.responses.com.neusoft.common.commonsysapiinverse.ResGetNaviPresentationMgs;
import api.responses.com.neusoft.common.commonsysapiinverse.ResGetNaviSoftware;
import api.responses.com.neusoft.common.commonsysapiinverse.ResGetNaviSoftwareVersionInfo;
import api.responses.com.neusoft.common.commonsysapiinverse.ResGetNaviStatusChanged;
import api.responses.com.neusoft.common.commonsysapiinverse.ResGetNaviSwitchFullView;
import api.responses.com.neusoft.common.commonsysapiinverse.ResGetNavigationActivityStatus;
import api.responses.com.neusoft.common.commonsysapiinverse.ResGetNavigationRunningStatus;
import api.responses.com.neusoft.common.commonsysapiinverse.ResGetNotifyOpenGPS;
import api.responses.com.neusoft.common.commonsysapiinverse.ResGetObserveWeatherInfo2System;
import api.responses.com.neusoft.common.commonsysapiinverse.ResGetOnKeyDown;
import api.responses.com.neusoft.common.commonsysapiinverse.ResGetOnNaviRemainTimeDistance;
import api.responses.com.neusoft.common.commonsysapiinverse.ResGetPeripheralStatus;
import api.responses.com.neusoft.common.commonsysapiinverse.ResGetPlayBeepTone;
import api.responses.com.neusoft.common.commonsysapiinverse.ResGetPlaySoundEffect;
import api.responses.com.neusoft.common.commonsysapiinverse.ResGetPostCustomEvent;
import api.responses.com.neusoft.common.commonsysapiinverse.ResGetReadFile;
import api.responses.com.neusoft.common.commonsysapiinverse.ResGetRequestDeviceId;
import api.responses.com.neusoft.common.commonsysapiinverse.ResGetRequestEnduranceMileage;
import api.responses.com.neusoft.common.commonsysapiinverse.ResGetRequestExpiredDate;
import api.responses.com.neusoft.common.commonsysapiinverse.ResGetRequestInstallationPath;
import api.responses.com.neusoft.common.commonsysapiinverse.ResGetRequestUpdateExternalStoragePath;
import api.responses.com.neusoft.common.commonsysapiinverse.ResGetResponseExpiredDate;
import api.responses.com.neusoft.common.commonsysapiinverse.ResGetShowDiagnoseSetting;
import api.responses.com.neusoft.common.commonsysapiinverse.ResGetShowToastMessage;
import api.responses.com.neusoft.common.commonsysapiinverse.ResGetSoftwareVersionInfo;
import api.responses.com.neusoft.common.commonsysapiinverse.ResGetSpeak;
import api.responses.com.neusoft.common.commonsysapiinverse.ResGetStorageSpaceStatus;
import api.responses.com.neusoft.common.commonsysapiinverse.ResGetSwitchArgumentedReality;
import api.responses.com.neusoft.common.commonsysapiinverse.ResGetSwitchNaviToBackground;
import api.responses.com.neusoft.common.commonsysapiinverse.ResGetSwitchTraceLog;
import api.responses.com.neusoft.common.commonsysapiinverse.ResGetTraceLog;
import api.responses.com.neusoft.common.commonsysapiinverse.ResGetTrafficInfoState;
import api.responses.com.neusoft.common.commonsysapiinverse.ResGetWriteFile;
import api.responses.com.neusoft.common.commonsysapiinverse.ResMapZoomInOut;
import api.responses.com.neusoft.common.commonsysapiinverse.ResReqNaviGuideStart;
import api.responses.com.neusoft.common.commonsysapiinverse.ResReqNaviGuideStop;
import api.responses.com.neusoft.common.commonsysapiinverse.ResReqNaviMapViewMode;
import api.responses.com.neusoft.common.commonsysapiinverse.ResReqNaviRemainTimeDistance;
import api.responses.com.neusoft.common.commonsysapiinverse.ResReqNaviSwitchFullView;
import api.responses.com.neusoft.common.commonsysapiinverse.ResReqNaviZoomIn;
import api.responses.com.neusoft.common.commonsysapiinverse.ResReqNaviZoomOut;
import api.responses.com.neusoft.common.commonsysapiinverse.ResReqSwitchDayNightMode;
import api.responses.com.neusoft.common.commonsysapiinverse.ResRequestRestoreFactorySetting;
import api.responses.com.neusoft.common.commonsysapiinverse.ResSetBatteryCapacityWH;
import api.responses.com.neusoft.common.commonsysapiinverse.ResSetCarType;
import api.responses.com.neusoft.common.commonsysapiinverse.ResSetChargingPortType;
import api.responses.com.neusoft.common.commonsysapiinverse.ResSetEnduranceMileage;
import api.responses.com.neusoft.common.commonsysapiinverse.ResSetFuelType;
import api.responses.com.neusoft.common.commonsysapiinverse.ResSetMapViewMode;
import api.responses.com.neusoft.common.commonsysapiinverse.ResSetNaviMuteModeComm;
import api.responses.com.neusoft.common.commonsysapiinverse.ResSetRegionalType;
import api.responses.com.neusoft.common.commonsysapiinverse.ResSetRemainingElectricQuantity;
import api.responses.com.neusoft.common.commonsysapiinverse.ResSetRemainingElectricQuantityKWH;
import api.responses.com.neusoft.common.commonsysapiinverse.ResSetUnitOfMeasurement;
import api.responses.com.neusoft.common.commonsysapiinverse.ResSwitchTrafficInfo;
import com.neusoft.de.nts.OneCoreTest.communication.Communication;
import com.neusoft.de.nts.OneCoreTest.interfaces.ICommunicationContextUtil;
import com.neusoft.de.nts.OneCoreTest.utils.CommonUtil;
import com.neusoft.de.nts.OneCoreTest.utils.Logger;
import java9.util.concurrent.CompletableFuture;
import java9.util.concurrent.CompletionStage;
import java9.util.function.Function;
import org.threeten.bp.Duration;

/* JADX INFO: loaded from: classes.dex */
public class CommonSysApiInverseRequests implements ICommonSysApiInverseRequests {
    private final String component;
    private final ICommunicationContextUtil<?, ?, ?> contextUtil;
    private static final Logger logger = Logger.getLogger((Class<?>) CommonSysApiInverseRequests.class);
    private static final CompletableFuture<Void> completedVoidFuture = CompletableFuture.completedFuture(null);

    public CommonSysApiInverseRequests(ICommunicationContextUtil<?, ?, ?> iCommunicationContextUtil) {
        this.contextUtil = iCommunicationContextUtil;
        this.component = null;
    }

    public CommonSysApiInverseRequests(ICommunicationContextUtil<?, ?, ?> iCommunicationContextUtil, String str) {
        this.contextUtil = iCommunicationContextUtil;
        this.component = str;
    }

    @Override // api.requests.com.neusoft.common.commonsysapiinverse.ICommonSysApiInverseRequests
    public CompletableFuture<Void> fireStartUpState(ReqStartUpState reqStartUpState) {
        reqStartUpState.setResponse(null);
        String str = this.component;
        if (str != null) {
            reqStartUpState.setComponent(str);
        }
        return Communication.request(this.contextUtil.createFromClass(reqStartUpState)).thenCompose(new Function<Object, CompletableFuture<Void>>() { // from class: api.requests.com.neusoft.common.commonsysapiinverse.CommonSysApiInverseRequests.1
            @Override // java9.util.function.Function
            public CompletableFuture<Void> apply(Object obj) {
                return CommonSysApiInverseRequests.completedVoidFuture;
            }
        });
    }

    @Override // api.requests.com.neusoft.common.commonsysapiinverse.ICommonSysApiInverseRequests
    public CompletableFuture<Void> fireStartUpState(ReqStartUpState reqStartUpState, final Duration duration) {
        return fireStartUpState(reqStartUpState).thenCompose((Function<? super Void, ? extends CompletionStage<U>>) new Function<Void, CompletableFuture<Void>>() { // from class: api.requests.com.neusoft.common.commonsysapiinverse.CommonSysApiInverseRequests.2
            @Override // java9.util.function.Function
            public CompletableFuture<Void> apply(Void r2) {
                return CommonUtil.asyncSleep(duration, CommonSysApiInverseRequests.this.contextUtil.getDefaultExecutor());
            }
        });
    }

    @Override // api.requests.com.neusoft.common.commonsysapiinverse.ICommonSysApiInverseRequests
    public CompletableFuture<Void> fireStartUpState(ReqStartUpState.Arguments arguments) {
        ReqStartUpState reqStartUpState = new ReqStartUpState();
        reqStartUpState.setArguments(arguments);
        reqStartUpState.setResponse(null);
        String str = this.component;
        if (str != null) {
            reqStartUpState.setComponent(str);
        }
        return fireStartUpState(reqStartUpState);
    }

    @Override // api.requests.com.neusoft.common.commonsysapiinverse.ICommonSysApiInverseRequests
    public CompletableFuture<Void> fireStartUpState(ReqStartUpState.Arguments arguments, final Duration duration) {
        return fireStartUpState(arguments).thenCompose((Function<? super Void, ? extends CompletionStage<U>>) new Function<Void, CompletableFuture<Void>>() { // from class: api.requests.com.neusoft.common.commonsysapiinverse.CommonSysApiInverseRequests.3
            @Override // java9.util.function.Function
            public CompletableFuture<Void> apply(Void r2) {
                return CommonUtil.asyncSleep(duration, CommonSysApiInverseRequests.this.contextUtil.getDefaultExecutor());
            }
        });
    }

    @Override // api.requests.com.neusoft.common.commonsysapiinverse.ICommonSysApiInverseRequests
    public CompletableFuture<Void> fireUpdateExternalStoragePath(ReqUpdateExternalStoragePath reqUpdateExternalStoragePath) {
        reqUpdateExternalStoragePath.setResponse(null);
        String str = this.component;
        if (str != null) {
            reqUpdateExternalStoragePath.setComponent(str);
        }
        return Communication.request(this.contextUtil.createFromClass(reqUpdateExternalStoragePath)).thenCompose(new Function<Object, CompletableFuture<Void>>() { // from class: api.requests.com.neusoft.common.commonsysapiinverse.CommonSysApiInverseRequests.4
            @Override // java9.util.function.Function
            public CompletableFuture<Void> apply(Object obj) {
                return CommonSysApiInverseRequests.completedVoidFuture;
            }
        });
    }

    @Override // api.requests.com.neusoft.common.commonsysapiinverse.ICommonSysApiInverseRequests
    public CompletableFuture<Void> fireUpdateExternalStoragePath(ReqUpdateExternalStoragePath reqUpdateExternalStoragePath, final Duration duration) {
        return fireUpdateExternalStoragePath(reqUpdateExternalStoragePath).thenCompose((Function<? super Void, ? extends CompletionStage<U>>) new Function<Void, CompletableFuture<Void>>() { // from class: api.requests.com.neusoft.common.commonsysapiinverse.CommonSysApiInverseRequests.5
            @Override // java9.util.function.Function
            public CompletableFuture<Void> apply(Void r2) {
                return CommonUtil.asyncSleep(duration, CommonSysApiInverseRequests.this.contextUtil.getDefaultExecutor());
            }
        });
    }

    @Override // api.requests.com.neusoft.common.commonsysapiinverse.ICommonSysApiInverseRequests
    public CompletableFuture<Void> fireUpdateExternalStoragePath(ReqUpdateExternalStoragePath.Arguments arguments) {
        ReqUpdateExternalStoragePath reqUpdateExternalStoragePath = new ReqUpdateExternalStoragePath();
        reqUpdateExternalStoragePath.setArguments(arguments);
        reqUpdateExternalStoragePath.setResponse(null);
        String str = this.component;
        if (str != null) {
            reqUpdateExternalStoragePath.setComponent(str);
        }
        return fireUpdateExternalStoragePath(reqUpdateExternalStoragePath);
    }

    @Override // api.requests.com.neusoft.common.commonsysapiinverse.ICommonSysApiInverseRequests
    public CompletableFuture<Void> fireUpdateExternalStoragePath(ReqUpdateExternalStoragePath.Arguments arguments, final Duration duration) {
        return fireUpdateExternalStoragePath(arguments).thenCompose((Function<? super Void, ? extends CompletionStage<U>>) new Function<Void, CompletableFuture<Void>>() { // from class: api.requests.com.neusoft.common.commonsysapiinverse.CommonSysApiInverseRequests.6
            @Override // java9.util.function.Function
            public CompletableFuture<Void> apply(Void r2) {
                return CommonUtil.asyncSleep(duration, CommonSysApiInverseRequests.this.contextUtil.getDefaultExecutor());
            }
        });
    }

    @Override // api.requests.com.neusoft.common.commonsysapiinverse.ICommonSysApiInverseRequests
    public CompletableFuture<Void> fireApplyUpdate(ReqApplyUpdate reqApplyUpdate) {
        reqApplyUpdate.setResponse(null);
        String str = this.component;
        if (str != null) {
            reqApplyUpdate.setComponent(str);
        }
        return Communication.request(this.contextUtil.createFromClass(reqApplyUpdate)).thenCompose(new Function<Object, CompletableFuture<Void>>() { // from class: api.requests.com.neusoft.common.commonsysapiinverse.CommonSysApiInverseRequests.7
            @Override // java9.util.function.Function
            public CompletableFuture<Void> apply(Object obj) {
                return CommonSysApiInverseRequests.completedVoidFuture;
            }
        });
    }

    @Override // api.requests.com.neusoft.common.commonsysapiinverse.ICommonSysApiInverseRequests
    public CompletableFuture<Void> fireApplyUpdate(ReqApplyUpdate reqApplyUpdate, final Duration duration) {
        return fireApplyUpdate(reqApplyUpdate).thenCompose((Function<? super Void, ? extends CompletionStage<U>>) new Function<Void, CompletableFuture<Void>>() { // from class: api.requests.com.neusoft.common.commonsysapiinverse.CommonSysApiInverseRequests.8
            @Override // java9.util.function.Function
            public CompletableFuture<Void> apply(Void r2) {
                return CommonUtil.asyncSleep(duration, CommonSysApiInverseRequests.this.contextUtil.getDefaultExecutor());
            }
        });
    }

    @Override // api.requests.com.neusoft.common.commonsysapiinverse.ICommonSysApiInverseRequests
    public CompletableFuture<Void> fireApplyUpdate() {
        return fireApplyUpdate(new ReqApplyUpdate());
    }

    @Override // api.requests.com.neusoft.common.commonsysapiinverse.ICommonSysApiInverseRequests
    public CompletableFuture<Void> fireApplyUpdate(final Duration duration) {
        return fireApplyUpdate(new ReqApplyUpdate()).thenCompose((Function<? super Void, ? extends CompletionStage<U>>) new Function<Void, CompletableFuture<Void>>() { // from class: api.requests.com.neusoft.common.commonsysapiinverse.CommonSysApiInverseRequests.9
            @Override // java9.util.function.Function
            public CompletableFuture<Void> apply(Void r2) {
                return CommonUtil.asyncSleep(duration, CommonSysApiInverseRequests.this.contextUtil.getDefaultExecutor());
            }
        });
    }

    @Override // api.requests.com.neusoft.common.commonsysapiinverse.ICommonSysApiInverseRequests
    public CompletableFuture<Void> fireUpdateDB(ReqUpdateDB reqUpdateDB) {
        reqUpdateDB.setResponse(null);
        String str = this.component;
        if (str != null) {
            reqUpdateDB.setComponent(str);
        }
        return Communication.request(this.contextUtil.createFromClass(reqUpdateDB)).thenCompose(new Function<Object, CompletableFuture<Void>>() { // from class: api.requests.com.neusoft.common.commonsysapiinverse.CommonSysApiInverseRequests.10
            @Override // java9.util.function.Function
            public CompletableFuture<Void> apply(Object obj) {
                return CommonSysApiInverseRequests.completedVoidFuture;
            }
        });
    }

    @Override // api.requests.com.neusoft.common.commonsysapiinverse.ICommonSysApiInverseRequests
    public CompletableFuture<Void> fireUpdateDB(ReqUpdateDB reqUpdateDB, final Duration duration) {
        return fireUpdateDB(reqUpdateDB).thenCompose((Function<? super Void, ? extends CompletionStage<U>>) new Function<Void, CompletableFuture<Void>>() { // from class: api.requests.com.neusoft.common.commonsysapiinverse.CommonSysApiInverseRequests.11
            @Override // java9.util.function.Function
            public CompletableFuture<Void> apply(Void r2) {
                return CommonUtil.asyncSleep(duration, CommonSysApiInverseRequests.this.contextUtil.getDefaultExecutor());
            }
        });
    }

    @Override // api.requests.com.neusoft.common.commonsysapiinverse.ICommonSysApiInverseRequests
    public CompletableFuture<Void> fireUpdateDB(ReqUpdateDB.Arguments arguments) {
        ReqUpdateDB reqUpdateDB = new ReqUpdateDB();
        reqUpdateDB.setArguments(arguments);
        reqUpdateDB.setResponse(null);
        String str = this.component;
        if (str != null) {
            reqUpdateDB.setComponent(str);
        }
        return fireUpdateDB(reqUpdateDB);
    }

    @Override // api.requests.com.neusoft.common.commonsysapiinverse.ICommonSysApiInverseRequests
    public CompletableFuture<Void> fireUpdateDB(ReqUpdateDB.Arguments arguments, final Duration duration) {
        return fireUpdateDB(arguments).thenCompose((Function<? super Void, ? extends CompletionStage<U>>) new Function<Void, CompletableFuture<Void>>() { // from class: api.requests.com.neusoft.common.commonsysapiinverse.CommonSysApiInverseRequests.12
            @Override // java9.util.function.Function
            public CompletableFuture<Void> apply(Void r2) {
                return CommonUtil.asyncSleep(duration, CommonSysApiInverseRequests.this.contextUtil.getDefaultExecutor());
            }
        });
    }

    @Override // api.requests.com.neusoft.common.commonsysapiinverse.ICommonSysApiInverseRequests
    public CompletableFuture<Void> fireSetDownloadPath(ReqSetDownloadPath reqSetDownloadPath) {
        reqSetDownloadPath.setResponse(null);
        String str = this.component;
        if (str != null) {
            reqSetDownloadPath.setComponent(str);
        }
        return Communication.request(this.contextUtil.createFromClass(reqSetDownloadPath)).thenCompose(new Function<Object, CompletableFuture<Void>>() { // from class: api.requests.com.neusoft.common.commonsysapiinverse.CommonSysApiInverseRequests.13
            @Override // java9.util.function.Function
            public CompletableFuture<Void> apply(Object obj) {
                return CommonSysApiInverseRequests.completedVoidFuture;
            }
        });
    }

    @Override // api.requests.com.neusoft.common.commonsysapiinverse.ICommonSysApiInverseRequests
    public CompletableFuture<Void> fireSetDownloadPath(ReqSetDownloadPath reqSetDownloadPath, final Duration duration) {
        return fireSetDownloadPath(reqSetDownloadPath).thenCompose((Function<? super Void, ? extends CompletionStage<U>>) new Function<Void, CompletableFuture<Void>>() { // from class: api.requests.com.neusoft.common.commonsysapiinverse.CommonSysApiInverseRequests.14
            @Override // java9.util.function.Function
            public CompletableFuture<Void> apply(Void r2) {
                return CommonUtil.asyncSleep(duration, CommonSysApiInverseRequests.this.contextUtil.getDefaultExecutor());
            }
        });
    }

    @Override // api.requests.com.neusoft.common.commonsysapiinverse.ICommonSysApiInverseRequests
    public CompletableFuture<Void> fireSetDownloadPath(ReqSetDownloadPath.Arguments arguments) {
        ReqSetDownloadPath reqSetDownloadPath = new ReqSetDownloadPath();
        reqSetDownloadPath.setArguments(arguments);
        reqSetDownloadPath.setResponse(null);
        String str = this.component;
        if (str != null) {
            reqSetDownloadPath.setComponent(str);
        }
        return fireSetDownloadPath(reqSetDownloadPath);
    }

    @Override // api.requests.com.neusoft.common.commonsysapiinverse.ICommonSysApiInverseRequests
    public CompletableFuture<Void> fireSetDownloadPath(ReqSetDownloadPath.Arguments arguments, final Duration duration) {
        return fireSetDownloadPath(arguments).thenCompose((Function<? super Void, ? extends CompletionStage<U>>) new Function<Void, CompletableFuture<Void>>() { // from class: api.requests.com.neusoft.common.commonsysapiinverse.CommonSysApiInverseRequests.15
            @Override // java9.util.function.Function
            public CompletableFuture<Void> apply(Void r2) {
                return CommonUtil.asyncSleep(duration, CommonSysApiInverseRequests.this.contextUtil.getDefaultExecutor());
            }
        });
    }

    @Override // api.requests.com.neusoft.common.commonsysapiinverse.ICommonSysApiInverseRequests
    public CompletableFuture<Void> fireOnAccessCodingAvailable(ReqOnAccessCodingAvailable reqOnAccessCodingAvailable) {
        reqOnAccessCodingAvailable.setResponse(null);
        String str = this.component;
        if (str != null) {
            reqOnAccessCodingAvailable.setComponent(str);
        }
        return Communication.request(this.contextUtil.createFromClass(reqOnAccessCodingAvailable)).thenCompose(new Function<Object, CompletableFuture<Void>>() { // from class: api.requests.com.neusoft.common.commonsysapiinverse.CommonSysApiInverseRequests.16
            @Override // java9.util.function.Function
            public CompletableFuture<Void> apply(Object obj) {
                return CommonSysApiInverseRequests.completedVoidFuture;
            }
        });
    }

    @Override // api.requests.com.neusoft.common.commonsysapiinverse.ICommonSysApiInverseRequests
    public CompletableFuture<Void> fireOnAccessCodingAvailable(ReqOnAccessCodingAvailable reqOnAccessCodingAvailable, final Duration duration) {
        return fireOnAccessCodingAvailable(reqOnAccessCodingAvailable).thenCompose((Function<? super Void, ? extends CompletionStage<U>>) new Function<Void, CompletableFuture<Void>>() { // from class: api.requests.com.neusoft.common.commonsysapiinverse.CommonSysApiInverseRequests.17
            @Override // java9.util.function.Function
            public CompletableFuture<Void> apply(Void r2) {
                return CommonUtil.asyncSleep(duration, CommonSysApiInverseRequests.this.contextUtil.getDefaultExecutor());
            }
        });
    }

    @Override // api.requests.com.neusoft.common.commonsysapiinverse.ICommonSysApiInverseRequests
    public CompletableFuture<Void> fireOnAccessCodingAvailable(ReqOnAccessCodingAvailable.Arguments arguments) {
        ReqOnAccessCodingAvailable reqOnAccessCodingAvailable = new ReqOnAccessCodingAvailable();
        reqOnAccessCodingAvailable.setArguments(arguments);
        reqOnAccessCodingAvailable.setResponse(null);
        String str = this.component;
        if (str != null) {
            reqOnAccessCodingAvailable.setComponent(str);
        }
        return fireOnAccessCodingAvailable(reqOnAccessCodingAvailable);
    }

    @Override // api.requests.com.neusoft.common.commonsysapiinverse.ICommonSysApiInverseRequests
    public CompletableFuture<Void> fireOnAccessCodingAvailable(ReqOnAccessCodingAvailable.Arguments arguments, final Duration duration) {
        return fireOnAccessCodingAvailable(arguments).thenCompose((Function<? super Void, ? extends CompletionStage<U>>) new Function<Void, CompletableFuture<Void>>() { // from class: api.requests.com.neusoft.common.commonsysapiinverse.CommonSysApiInverseRequests.18
            @Override // java9.util.function.Function
            public CompletableFuture<Void> apply(Void r2) {
                return CommonUtil.asyncSleep(duration, CommonSysApiInverseRequests.this.contextUtil.getDefaultExecutor());
            }
        });
    }

    @Override // api.requests.com.neusoft.common.commonsysapiinverse.ICommonSysApiInverseRequests
    public CompletableFuture<Void> fireSetDeviceId(ReqSetDeviceId reqSetDeviceId) {
        reqSetDeviceId.setResponse(null);
        String str = this.component;
        if (str != null) {
            reqSetDeviceId.setComponent(str);
        }
        return Communication.request(this.contextUtil.createFromClass(reqSetDeviceId)).thenCompose(new Function<Object, CompletableFuture<Void>>() { // from class: api.requests.com.neusoft.common.commonsysapiinverse.CommonSysApiInverseRequests.19
            @Override // java9.util.function.Function
            public CompletableFuture<Void> apply(Object obj) {
                return CommonSysApiInverseRequests.completedVoidFuture;
            }
        });
    }

    @Override // api.requests.com.neusoft.common.commonsysapiinverse.ICommonSysApiInverseRequests
    public CompletableFuture<Void> fireSetDeviceId(ReqSetDeviceId reqSetDeviceId, final Duration duration) {
        return fireSetDeviceId(reqSetDeviceId).thenCompose((Function<? super Void, ? extends CompletionStage<U>>) new Function<Void, CompletableFuture<Void>>() { // from class: api.requests.com.neusoft.common.commonsysapiinverse.CommonSysApiInverseRequests.20
            @Override // java9.util.function.Function
            public CompletableFuture<Void> apply(Void r2) {
                return CommonUtil.asyncSleep(duration, CommonSysApiInverseRequests.this.contextUtil.getDefaultExecutor());
            }
        });
    }

    @Override // api.requests.com.neusoft.common.commonsysapiinverse.ICommonSysApiInverseRequests
    public CompletableFuture<Void> fireSetDeviceId(ReqSetDeviceId.Arguments arguments) {
        ReqSetDeviceId reqSetDeviceId = new ReqSetDeviceId();
        reqSetDeviceId.setArguments(arguments);
        reqSetDeviceId.setResponse(null);
        String str = this.component;
        if (str != null) {
            reqSetDeviceId.setComponent(str);
        }
        return fireSetDeviceId(reqSetDeviceId);
    }

    @Override // api.requests.com.neusoft.common.commonsysapiinverse.ICommonSysApiInverseRequests
    public CompletableFuture<Void> fireSetDeviceId(ReqSetDeviceId.Arguments arguments, final Duration duration) {
        return fireSetDeviceId(arguments).thenCompose((Function<? super Void, ? extends CompletionStage<U>>) new Function<Void, CompletableFuture<Void>>() { // from class: api.requests.com.neusoft.common.commonsysapiinverse.CommonSysApiInverseRequests.21
            @Override // java9.util.function.Function
            public CompletableFuture<Void> apply(Void r2) {
                return CommonUtil.asyncSleep(duration, CommonSysApiInverseRequests.this.contextUtil.getDefaultExecutor());
            }
        });
    }

    @Override // api.requests.com.neusoft.common.commonsysapiinverse.ICommonSysApiInverseRequests
    public CompletableFuture<Void> fireSetExpiredDate(ReqSetExpiredDate reqSetExpiredDate) {
        reqSetExpiredDate.setResponse(null);
        String str = this.component;
        if (str != null) {
            reqSetExpiredDate.setComponent(str);
        }
        return Communication.request(this.contextUtil.createFromClass(reqSetExpiredDate)).thenCompose(new Function<Object, CompletableFuture<Void>>() { // from class: api.requests.com.neusoft.common.commonsysapiinverse.CommonSysApiInverseRequests.22
            @Override // java9.util.function.Function
            public CompletableFuture<Void> apply(Object obj) {
                return CommonSysApiInverseRequests.completedVoidFuture;
            }
        });
    }

    @Override // api.requests.com.neusoft.common.commonsysapiinverse.ICommonSysApiInverseRequests
    public CompletableFuture<Void> fireSetExpiredDate(ReqSetExpiredDate reqSetExpiredDate, final Duration duration) {
        return fireSetExpiredDate(reqSetExpiredDate).thenCompose((Function<? super Void, ? extends CompletionStage<U>>) new Function<Void, CompletableFuture<Void>>() { // from class: api.requests.com.neusoft.common.commonsysapiinverse.CommonSysApiInverseRequests.23
            @Override // java9.util.function.Function
            public CompletableFuture<Void> apply(Void r2) {
                return CommonUtil.asyncSleep(duration, CommonSysApiInverseRequests.this.contextUtil.getDefaultExecutor());
            }
        });
    }

    @Override // api.requests.com.neusoft.common.commonsysapiinverse.ICommonSysApiInverseRequests
    public CompletableFuture<Void> fireSetExpiredDate(ReqSetExpiredDate.Arguments arguments) {
        ReqSetExpiredDate reqSetExpiredDate = new ReqSetExpiredDate();
        reqSetExpiredDate.setArguments(arguments);
        reqSetExpiredDate.setResponse(null);
        String str = this.component;
        if (str != null) {
            reqSetExpiredDate.setComponent(str);
        }
        return fireSetExpiredDate(reqSetExpiredDate);
    }

    @Override // api.requests.com.neusoft.common.commonsysapiinverse.ICommonSysApiInverseRequests
    public CompletableFuture<Void> fireSetExpiredDate(ReqSetExpiredDate.Arguments arguments, final Duration duration) {
        return fireSetExpiredDate(arguments).thenCompose((Function<? super Void, ? extends CompletionStage<U>>) new Function<Void, CompletableFuture<Void>>() { // from class: api.requests.com.neusoft.common.commonsysapiinverse.CommonSysApiInverseRequests.24
            @Override // java9.util.function.Function
            public CompletableFuture<Void> apply(Void r2) {
                return CommonUtil.asyncSleep(duration, CommonSysApiInverseRequests.this.contextUtil.getDefaultExecutor());
            }
        });
    }

    @Override // api.requests.com.neusoft.common.commonsysapiinverse.ICommonSysApiInverseRequests
    public CompletableFuture<Void> fireFeedbackActivateResult(ReqFeedbackActivateResult reqFeedbackActivateResult) {
        reqFeedbackActivateResult.setResponse(null);
        String str = this.component;
        if (str != null) {
            reqFeedbackActivateResult.setComponent(str);
        }
        return Communication.request(this.contextUtil.createFromClass(reqFeedbackActivateResult)).thenCompose(new Function<Object, CompletableFuture<Void>>() { // from class: api.requests.com.neusoft.common.commonsysapiinverse.CommonSysApiInverseRequests.25
            @Override // java9.util.function.Function
            public CompletableFuture<Void> apply(Object obj) {
                return CommonSysApiInverseRequests.completedVoidFuture;
            }
        });
    }

    @Override // api.requests.com.neusoft.common.commonsysapiinverse.ICommonSysApiInverseRequests
    public CompletableFuture<Void> fireFeedbackActivateResult(ReqFeedbackActivateResult reqFeedbackActivateResult, final Duration duration) {
        return fireFeedbackActivateResult(reqFeedbackActivateResult).thenCompose((Function<? super Void, ? extends CompletionStage<U>>) new Function<Void, CompletableFuture<Void>>() { // from class: api.requests.com.neusoft.common.commonsysapiinverse.CommonSysApiInverseRequests.26
            @Override // java9.util.function.Function
            public CompletableFuture<Void> apply(Void r2) {
                return CommonUtil.asyncSleep(duration, CommonSysApiInverseRequests.this.contextUtil.getDefaultExecutor());
            }
        });
    }

    @Override // api.requests.com.neusoft.common.commonsysapiinverse.ICommonSysApiInverseRequests
    public CompletableFuture<Void> fireFeedbackActivateResult(ReqFeedbackActivateResult.Arguments arguments) {
        ReqFeedbackActivateResult reqFeedbackActivateResult = new ReqFeedbackActivateResult();
        reqFeedbackActivateResult.setArguments(arguments);
        reqFeedbackActivateResult.setResponse(null);
        String str = this.component;
        if (str != null) {
            reqFeedbackActivateResult.setComponent(str);
        }
        return fireFeedbackActivateResult(reqFeedbackActivateResult);
    }

    @Override // api.requests.com.neusoft.common.commonsysapiinverse.ICommonSysApiInverseRequests
    public CompletableFuture<Void> fireFeedbackActivateResult(ReqFeedbackActivateResult.Arguments arguments, final Duration duration) {
        return fireFeedbackActivateResult(arguments).thenCompose((Function<? super Void, ? extends CompletionStage<U>>) new Function<Void, CompletableFuture<Void>>() { // from class: api.requests.com.neusoft.common.commonsysapiinverse.CommonSysApiInverseRequests.27
            @Override // java9.util.function.Function
            public CompletableFuture<Void> apply(Void r2) {
                return CommonUtil.asyncSleep(duration, CommonSysApiInverseRequests.this.contextUtil.getDefaultExecutor());
            }
        });
    }

    @Override // api.requests.com.neusoft.common.commonsysapiinverse.ICommonSysApiInverseRequests
    public CompletableFuture<Void> fireSetInstallationPath(ReqSetInstallationPath reqSetInstallationPath) {
        reqSetInstallationPath.setResponse(null);
        String str = this.component;
        if (str != null) {
            reqSetInstallationPath.setComponent(str);
        }
        return Communication.request(this.contextUtil.createFromClass(reqSetInstallationPath)).thenCompose(new Function<Object, CompletableFuture<Void>>() { // from class: api.requests.com.neusoft.common.commonsysapiinverse.CommonSysApiInverseRequests.28
            @Override // java9.util.function.Function
            public CompletableFuture<Void> apply(Object obj) {
                return CommonSysApiInverseRequests.completedVoidFuture;
            }
        });
    }

    @Override // api.requests.com.neusoft.common.commonsysapiinverse.ICommonSysApiInverseRequests
    public CompletableFuture<Void> fireSetInstallationPath(ReqSetInstallationPath reqSetInstallationPath, final Duration duration) {
        return fireSetInstallationPath(reqSetInstallationPath).thenCompose((Function<? super Void, ? extends CompletionStage<U>>) new Function<Void, CompletableFuture<Void>>() { // from class: api.requests.com.neusoft.common.commonsysapiinverse.CommonSysApiInverseRequests.29
            @Override // java9.util.function.Function
            public CompletableFuture<Void> apply(Void r2) {
                return CommonUtil.asyncSleep(duration, CommonSysApiInverseRequests.this.contextUtil.getDefaultExecutor());
            }
        });
    }

    @Override // api.requests.com.neusoft.common.commonsysapiinverse.ICommonSysApiInverseRequests
    public CompletableFuture<Void> fireSetInstallationPath(ReqSetInstallationPath.Arguments arguments) {
        ReqSetInstallationPath reqSetInstallationPath = new ReqSetInstallationPath();
        reqSetInstallationPath.setArguments(arguments);
        reqSetInstallationPath.setResponse(null);
        String str = this.component;
        if (str != null) {
            reqSetInstallationPath.setComponent(str);
        }
        return fireSetInstallationPath(reqSetInstallationPath);
    }

    @Override // api.requests.com.neusoft.common.commonsysapiinverse.ICommonSysApiInverseRequests
    public CompletableFuture<Void> fireSetInstallationPath(ReqSetInstallationPath.Arguments arguments, final Duration duration) {
        return fireSetInstallationPath(arguments).thenCompose((Function<? super Void, ? extends CompletionStage<U>>) new Function<Void, CompletableFuture<Void>>() { // from class: api.requests.com.neusoft.common.commonsysapiinverse.CommonSysApiInverseRequests.30
            @Override // java9.util.function.Function
            public CompletableFuture<Void> apply(Void r2) {
                return CommonUtil.asyncSleep(duration, CommonSysApiInverseRequests.this.contextUtil.getDefaultExecutor());
            }
        });
    }

    @Override // api.requests.com.neusoft.common.commonsysapiinverse.ICommonSysApiInverseRequests
    public CompletableFuture<Void> fireSetPeripheralStatus(ReqSetPeripheralStatus reqSetPeripheralStatus) {
        reqSetPeripheralStatus.setResponse(null);
        String str = this.component;
        if (str != null) {
            reqSetPeripheralStatus.setComponent(str);
        }
        return Communication.request(this.contextUtil.createFromClass(reqSetPeripheralStatus)).thenCompose(new Function<Object, CompletableFuture<Void>>() { // from class: api.requests.com.neusoft.common.commonsysapiinverse.CommonSysApiInverseRequests.31
            @Override // java9.util.function.Function
            public CompletableFuture<Void> apply(Object obj) {
                return CommonSysApiInverseRequests.completedVoidFuture;
            }
        });
    }

    @Override // api.requests.com.neusoft.common.commonsysapiinverse.ICommonSysApiInverseRequests
    public CompletableFuture<Void> fireSetPeripheralStatus(ReqSetPeripheralStatus reqSetPeripheralStatus, final Duration duration) {
        return fireSetPeripheralStatus(reqSetPeripheralStatus).thenCompose((Function<? super Void, ? extends CompletionStage<U>>) new Function<Void, CompletableFuture<Void>>() { // from class: api.requests.com.neusoft.common.commonsysapiinverse.CommonSysApiInverseRequests.32
            @Override // java9.util.function.Function
            public CompletableFuture<Void> apply(Void r2) {
                return CommonUtil.asyncSleep(duration, CommonSysApiInverseRequests.this.contextUtil.getDefaultExecutor());
            }
        });
    }

    @Override // api.requests.com.neusoft.common.commonsysapiinverse.ICommonSysApiInverseRequests
    public CompletableFuture<Void> fireSetPeripheralStatus(ReqSetPeripheralStatus.Arguments arguments) {
        ReqSetPeripheralStatus reqSetPeripheralStatus = new ReqSetPeripheralStatus();
        reqSetPeripheralStatus.setArguments(arguments);
        reqSetPeripheralStatus.setResponse(null);
        String str = this.component;
        if (str != null) {
            reqSetPeripheralStatus.setComponent(str);
        }
        return fireSetPeripheralStatus(reqSetPeripheralStatus);
    }

    @Override // api.requests.com.neusoft.common.commonsysapiinverse.ICommonSysApiInverseRequests
    public CompletableFuture<Void> fireSetPeripheralStatus(ReqSetPeripheralStatus.Arguments arguments, final Duration duration) {
        return fireSetPeripheralStatus(arguments).thenCompose((Function<? super Void, ? extends CompletionStage<U>>) new Function<Void, CompletableFuture<Void>>() { // from class: api.requests.com.neusoft.common.commonsysapiinverse.CommonSysApiInverseRequests.33
            @Override // java9.util.function.Function
            public CompletableFuture<Void> apply(Void r2) {
                return CommonUtil.asyncSleep(duration, CommonSysApiInverseRequests.this.contextUtil.getDefaultExecutor());
            }
        });
    }

    @Override // api.requests.com.neusoft.common.commonsysapiinverse.ICommonSysApiInverseRequests
    public CompletableFuture<Void> fireStorageSpace(ReqStorageSpace reqStorageSpace) {
        reqStorageSpace.setResponse(null);
        String str = this.component;
        if (str != null) {
            reqStorageSpace.setComponent(str);
        }
        return Communication.request(this.contextUtil.createFromClass(reqStorageSpace)).thenCompose(new Function<Object, CompletableFuture<Void>>() { // from class: api.requests.com.neusoft.common.commonsysapiinverse.CommonSysApiInverseRequests.34
            @Override // java9.util.function.Function
            public CompletableFuture<Void> apply(Object obj) {
                return CommonSysApiInverseRequests.completedVoidFuture;
            }
        });
    }

    @Override // api.requests.com.neusoft.common.commonsysapiinverse.ICommonSysApiInverseRequests
    public CompletableFuture<Void> fireStorageSpace(ReqStorageSpace reqStorageSpace, final Duration duration) {
        return fireStorageSpace(reqStorageSpace).thenCompose((Function<? super Void, ? extends CompletionStage<U>>) new Function<Void, CompletableFuture<Void>>() { // from class: api.requests.com.neusoft.common.commonsysapiinverse.CommonSysApiInverseRequests.35
            @Override // java9.util.function.Function
            public CompletableFuture<Void> apply(Void r2) {
                return CommonUtil.asyncSleep(duration, CommonSysApiInverseRequests.this.contextUtil.getDefaultExecutor());
            }
        });
    }

    @Override // api.requests.com.neusoft.common.commonsysapiinverse.ICommonSysApiInverseRequests
    public CompletableFuture<Void> fireStorageSpace(ReqStorageSpace.Arguments arguments) {
        ReqStorageSpace reqStorageSpace = new ReqStorageSpace();
        reqStorageSpace.setArguments(arguments);
        reqStorageSpace.setResponse(null);
        String str = this.component;
        if (str != null) {
            reqStorageSpace.setComponent(str);
        }
        return fireStorageSpace(reqStorageSpace);
    }

    @Override // api.requests.com.neusoft.common.commonsysapiinverse.ICommonSysApiInverseRequests
    public CompletableFuture<Void> fireStorageSpace(ReqStorageSpace.Arguments arguments, final Duration duration) {
        return fireStorageSpace(arguments).thenCompose((Function<? super Void, ? extends CompletionStage<U>>) new Function<Void, CompletableFuture<Void>>() { // from class: api.requests.com.neusoft.common.commonsysapiinverse.CommonSysApiInverseRequests.36
            @Override // java9.util.function.Function
            public CompletableFuture<Void> apply(Void r2) {
                return CommonUtil.asyncSleep(duration, CommonSysApiInverseRequests.this.contextUtil.getDefaultExecutor());
            }
        });
    }

    @Override // api.requests.com.neusoft.common.commonsysapiinverse.ICommonSysApiInverseRequests
    public CompletableFuture<Void> fireOnReadFile(ReqOnReadFile reqOnReadFile) {
        reqOnReadFile.setResponse(null);
        String str = this.component;
        if (str != null) {
            reqOnReadFile.setComponent(str);
        }
        return Communication.request(this.contextUtil.createFromClass(reqOnReadFile)).thenCompose(new Function<Object, CompletableFuture<Void>>() { // from class: api.requests.com.neusoft.common.commonsysapiinverse.CommonSysApiInverseRequests.37
            @Override // java9.util.function.Function
            public CompletableFuture<Void> apply(Object obj) {
                return CommonSysApiInverseRequests.completedVoidFuture;
            }
        });
    }

    @Override // api.requests.com.neusoft.common.commonsysapiinverse.ICommonSysApiInverseRequests
    public CompletableFuture<Void> fireOnReadFile(ReqOnReadFile reqOnReadFile, final Duration duration) {
        return fireOnReadFile(reqOnReadFile).thenCompose((Function<? super Void, ? extends CompletionStage<U>>) new Function<Void, CompletableFuture<Void>>() { // from class: api.requests.com.neusoft.common.commonsysapiinverse.CommonSysApiInverseRequests.38
            @Override // java9.util.function.Function
            public CompletableFuture<Void> apply(Void r2) {
                return CommonUtil.asyncSleep(duration, CommonSysApiInverseRequests.this.contextUtil.getDefaultExecutor());
            }
        });
    }

    @Override // api.requests.com.neusoft.common.commonsysapiinverse.ICommonSysApiInverseRequests
    public CompletableFuture<Void> fireOnReadFile(ReqOnReadFile.Arguments arguments) {
        ReqOnReadFile reqOnReadFile = new ReqOnReadFile();
        reqOnReadFile.setArguments(arguments);
        reqOnReadFile.setResponse(null);
        String str = this.component;
        if (str != null) {
            reqOnReadFile.setComponent(str);
        }
        return fireOnReadFile(reqOnReadFile);
    }

    @Override // api.requests.com.neusoft.common.commonsysapiinverse.ICommonSysApiInverseRequests
    public CompletableFuture<Void> fireOnReadFile(ReqOnReadFile.Arguments arguments, final Duration duration) {
        return fireOnReadFile(arguments).thenCompose((Function<? super Void, ? extends CompletionStage<U>>) new Function<Void, CompletableFuture<Void>>() { // from class: api.requests.com.neusoft.common.commonsysapiinverse.CommonSysApiInverseRequests.39
            @Override // java9.util.function.Function
            public CompletableFuture<Void> apply(Void r2) {
                return CommonUtil.asyncSleep(duration, CommonSysApiInverseRequests.this.contextUtil.getDefaultExecutor());
            }
        });
    }

    @Override // api.requests.com.neusoft.common.commonsysapiinverse.ICommonSysApiInverseRequests
    public CompletableFuture<Void> fireOnWriteFile(ReqOnWriteFile reqOnWriteFile) {
        reqOnWriteFile.setResponse(null);
        String str = this.component;
        if (str != null) {
            reqOnWriteFile.setComponent(str);
        }
        return Communication.request(this.contextUtil.createFromClass(reqOnWriteFile)).thenCompose(new Function<Object, CompletableFuture<Void>>() { // from class: api.requests.com.neusoft.common.commonsysapiinverse.CommonSysApiInverseRequests.40
            @Override // java9.util.function.Function
            public CompletableFuture<Void> apply(Object obj) {
                return CommonSysApiInverseRequests.completedVoidFuture;
            }
        });
    }

    @Override // api.requests.com.neusoft.common.commonsysapiinverse.ICommonSysApiInverseRequests
    public CompletableFuture<Void> fireOnWriteFile(ReqOnWriteFile reqOnWriteFile, final Duration duration) {
        return fireOnWriteFile(reqOnWriteFile).thenCompose((Function<? super Void, ? extends CompletionStage<U>>) new Function<Void, CompletableFuture<Void>>() { // from class: api.requests.com.neusoft.common.commonsysapiinverse.CommonSysApiInverseRequests.41
            @Override // java9.util.function.Function
            public CompletableFuture<Void> apply(Void r2) {
                return CommonUtil.asyncSleep(duration, CommonSysApiInverseRequests.this.contextUtil.getDefaultExecutor());
            }
        });
    }

    @Override // api.requests.com.neusoft.common.commonsysapiinverse.ICommonSysApiInverseRequests
    public CompletableFuture<Void> fireOnWriteFile(ReqOnWriteFile.Arguments arguments) {
        ReqOnWriteFile reqOnWriteFile = new ReqOnWriteFile();
        reqOnWriteFile.setArguments(arguments);
        reqOnWriteFile.setResponse(null);
        String str = this.component;
        if (str != null) {
            reqOnWriteFile.setComponent(str);
        }
        return fireOnWriteFile(reqOnWriteFile);
    }

    @Override // api.requests.com.neusoft.common.commonsysapiinverse.ICommonSysApiInverseRequests
    public CompletableFuture<Void> fireOnWriteFile(ReqOnWriteFile.Arguments arguments, final Duration duration) {
        return fireOnWriteFile(arguments).thenCompose((Function<? super Void, ? extends CompletionStage<U>>) new Function<Void, CompletableFuture<Void>>() { // from class: api.requests.com.neusoft.common.commonsysapiinverse.CommonSysApiInverseRequests.42
            @Override // java9.util.function.Function
            public CompletableFuture<Void> apply(Void r2) {
                return CommonUtil.asyncSleep(duration, CommonSysApiInverseRequests.this.contextUtil.getDefaultExecutor());
            }
        });
    }

    @Override // api.requests.com.neusoft.common.commonsysapiinverse.ICommonSysApiInverseRequests
    public CompletableFuture<Void> fireOnDeleteFile(ReqOnDeleteFile reqOnDeleteFile) {
        reqOnDeleteFile.setResponse(null);
        String str = this.component;
        if (str != null) {
            reqOnDeleteFile.setComponent(str);
        }
        return Communication.request(this.contextUtil.createFromClass(reqOnDeleteFile)).thenCompose(new Function<Object, CompletableFuture<Void>>() { // from class: api.requests.com.neusoft.common.commonsysapiinverse.CommonSysApiInverseRequests.43
            @Override // java9.util.function.Function
            public CompletableFuture<Void> apply(Object obj) {
                return CommonSysApiInverseRequests.completedVoidFuture;
            }
        });
    }

    @Override // api.requests.com.neusoft.common.commonsysapiinverse.ICommonSysApiInverseRequests
    public CompletableFuture<Void> fireOnDeleteFile(ReqOnDeleteFile reqOnDeleteFile, final Duration duration) {
        return fireOnDeleteFile(reqOnDeleteFile).thenCompose((Function<? super Void, ? extends CompletionStage<U>>) new Function<Void, CompletableFuture<Void>>() { // from class: api.requests.com.neusoft.common.commonsysapiinverse.CommonSysApiInverseRequests.44
            @Override // java9.util.function.Function
            public CompletableFuture<Void> apply(Void r2) {
                return CommonUtil.asyncSleep(duration, CommonSysApiInverseRequests.this.contextUtil.getDefaultExecutor());
            }
        });
    }

    @Override // api.requests.com.neusoft.common.commonsysapiinverse.ICommonSysApiInverseRequests
    public CompletableFuture<Void> fireOnDeleteFile(ReqOnDeleteFile.Arguments arguments) {
        ReqOnDeleteFile reqOnDeleteFile = new ReqOnDeleteFile();
        reqOnDeleteFile.setArguments(arguments);
        reqOnDeleteFile.setResponse(null);
        String str = this.component;
        if (str != null) {
            reqOnDeleteFile.setComponent(str);
        }
        return fireOnDeleteFile(reqOnDeleteFile);
    }

    @Override // api.requests.com.neusoft.common.commonsysapiinverse.ICommonSysApiInverseRequests
    public CompletableFuture<Void> fireOnDeleteFile(ReqOnDeleteFile.Arguments arguments, final Duration duration) {
        return fireOnDeleteFile(arguments).thenCompose((Function<? super Void, ? extends CompletionStage<U>>) new Function<Void, CompletableFuture<Void>>() { // from class: api.requests.com.neusoft.common.commonsysapiinverse.CommonSysApiInverseRequests.45
            @Override // java9.util.function.Function
            public CompletableFuture<Void> apply(Void r2) {
                return CommonUtil.asyncSleep(duration, CommonSysApiInverseRequests.this.contextUtil.getDefaultExecutor());
            }
        });
    }

    @Override // api.requests.com.neusoft.common.commonsysapiinverse.ICommonSysApiInverseRequests
    public CompletableFuture<ResSetUnitOfMeasurement> requestSetUnitOfMeasurement(ReqSetUnitOfMeasurement reqSetUnitOfMeasurement) {
        if (reqSetUnitOfMeasurement.getResponse() == null) {
            reqSetUnitOfMeasurement.setResponse(new ResSetUnitOfMeasurement());
        }
        String str = this.component;
        if (str != null) {
            reqSetUnitOfMeasurement.setComponent(str);
            reqSetUnitOfMeasurement.getResponse().setComponent(this.component);
        }
        return Communication.request(this.contextUtil.createFromClass(reqSetUnitOfMeasurement));
    }

    @Override // api.requests.com.neusoft.common.commonsysapiinverse.ICommonSysApiInverseRequests
    public CompletableFuture<ResSetUnitOfMeasurement> requestSetUnitOfMeasurement(ReqSetUnitOfMeasurement reqSetUnitOfMeasurement, final Duration duration) {
        return requestSetUnitOfMeasurement(reqSetUnitOfMeasurement).thenCompose((Function<? super ResSetUnitOfMeasurement, ? extends CompletionStage<U>>) new Function<ResSetUnitOfMeasurement, CompletableFuture<ResSetUnitOfMeasurement>>() { // from class: api.requests.com.neusoft.common.commonsysapiinverse.CommonSysApiInverseRequests.46
            @Override // java9.util.function.Function
            public CompletableFuture<ResSetUnitOfMeasurement> apply(ResSetUnitOfMeasurement resSetUnitOfMeasurement) {
                return CommonUtil.delayAsync(resSetUnitOfMeasurement, duration, CommonSysApiInverseRequests.this.contextUtil.getDefaultExecutor());
            }
        });
    }

    @Override // api.requests.com.neusoft.common.commonsysapiinverse.ICommonSysApiInverseRequests
    public CompletableFuture<ResSetUnitOfMeasurement.Arguments> requestSetUnitOfMeasurement(ReqSetUnitOfMeasurement.Arguments arguments) {
        ReqSetUnitOfMeasurement reqSetUnitOfMeasurement = new ReqSetUnitOfMeasurement();
        reqSetUnitOfMeasurement.setResponse(new ResSetUnitOfMeasurement());
        reqSetUnitOfMeasurement.setArguments(arguments);
        return requestSetUnitOfMeasurement(reqSetUnitOfMeasurement).thenApply((Function<? super ResSetUnitOfMeasurement, ? extends U>) new Function<ResSetUnitOfMeasurement, ResSetUnitOfMeasurement.Arguments>() { // from class: api.requests.com.neusoft.common.commonsysapiinverse.CommonSysApiInverseRequests.47
            @Override // java9.util.function.Function
            public ResSetUnitOfMeasurement.Arguments apply(ResSetUnitOfMeasurement resSetUnitOfMeasurement) {
                return resSetUnitOfMeasurement.getArguments();
            }
        });
    }

    @Override // api.requests.com.neusoft.common.commonsysapiinverse.ICommonSysApiInverseRequests
    public CompletableFuture<ResSetUnitOfMeasurement.Arguments> requestSetUnitOfMeasurement(ReqSetUnitOfMeasurement.Arguments arguments, final Duration duration) {
        return requestSetUnitOfMeasurement(arguments).thenCompose((Function<? super ResSetUnitOfMeasurement.Arguments, ? extends CompletionStage<U>>) new Function<ResSetUnitOfMeasurement.Arguments, CompletableFuture<ResSetUnitOfMeasurement.Arguments>>() { // from class: api.requests.com.neusoft.common.commonsysapiinverse.CommonSysApiInverseRequests.48
            @Override // java9.util.function.Function
            public CompletableFuture<ResSetUnitOfMeasurement.Arguments> apply(ResSetUnitOfMeasurement.Arguments arguments2) {
                return CommonUtil.delayAsync(arguments2, duration, CommonSysApiInverseRequests.this.contextUtil.getDefaultExecutor());
            }
        });
    }

    @Override // api.requests.com.neusoft.common.commonsysapiinverse.ICommonSysApiInverseRequests
    public CompletableFuture<Void> fireSetUnitOfMeasurement(ReqSetUnitOfMeasurement reqSetUnitOfMeasurement) {
        reqSetUnitOfMeasurement.setResponse(null);
        String str = this.component;
        if (str != null) {
            reqSetUnitOfMeasurement.setComponent(str);
        }
        return Communication.request(this.contextUtil.createFromClass(reqSetUnitOfMeasurement)).thenCompose(new Function<Object, CompletableFuture<Void>>() { // from class: api.requests.com.neusoft.common.commonsysapiinverse.CommonSysApiInverseRequests.49
            @Override // java9.util.function.Function
            public CompletableFuture<Void> apply(Object obj) {
                return CommonSysApiInverseRequests.completedVoidFuture;
            }
        });
    }

    @Override // api.requests.com.neusoft.common.commonsysapiinverse.ICommonSysApiInverseRequests
    public CompletableFuture<Void> fireSetUnitOfMeasurement(ReqSetUnitOfMeasurement reqSetUnitOfMeasurement, final Duration duration) {
        return fireSetUnitOfMeasurement(reqSetUnitOfMeasurement).thenCompose((Function<? super Void, ? extends CompletionStage<U>>) new Function<Void, CompletableFuture<Void>>() { // from class: api.requests.com.neusoft.common.commonsysapiinverse.CommonSysApiInverseRequests.50
            @Override // java9.util.function.Function
            public CompletableFuture<Void> apply(Void r2) {
                return CommonUtil.asyncSleep(duration, CommonSysApiInverseRequests.this.contextUtil.getDefaultExecutor());
            }
        });
    }

    @Override // api.requests.com.neusoft.common.commonsysapiinverse.ICommonSysApiInverseRequests
    public CompletableFuture<Void> fireSetUnitOfMeasurement(ReqSetUnitOfMeasurement.Arguments arguments) {
        ReqSetUnitOfMeasurement reqSetUnitOfMeasurement = new ReqSetUnitOfMeasurement();
        reqSetUnitOfMeasurement.setArguments(arguments);
        reqSetUnitOfMeasurement.setResponse(null);
        String str = this.component;
        if (str != null) {
            reqSetUnitOfMeasurement.setComponent(str);
        }
        return fireSetUnitOfMeasurement(reqSetUnitOfMeasurement);
    }

    @Override // api.requests.com.neusoft.common.commonsysapiinverse.ICommonSysApiInverseRequests
    public CompletableFuture<Void> fireSetUnitOfMeasurement(ReqSetUnitOfMeasurement.Arguments arguments, final Duration duration) {
        return fireSetUnitOfMeasurement(arguments).thenCompose((Function<? super Void, ? extends CompletionStage<U>>) new Function<Void, CompletableFuture<Void>>() { // from class: api.requests.com.neusoft.common.commonsysapiinverse.CommonSysApiInverseRequests.51
            @Override // java9.util.function.Function
            public CompletableFuture<Void> apply(Void r2) {
                return CommonUtil.asyncSleep(duration, CommonSysApiInverseRequests.this.contextUtil.getDefaultExecutor());
            }
        });
    }

    @Override // api.requests.com.neusoft.common.commonsysapiinverse.ICommonSysApiInverseRequests
    public CompletableFuture<Void> fireSetTraceLog(ReqSetTraceLog reqSetTraceLog) {
        reqSetTraceLog.setResponse(null);
        String str = this.component;
        if (str != null) {
            reqSetTraceLog.setComponent(str);
        }
        return Communication.request(this.contextUtil.createFromClass(reqSetTraceLog)).thenCompose(new Function<Object, CompletableFuture<Void>>() { // from class: api.requests.com.neusoft.common.commonsysapiinverse.CommonSysApiInverseRequests.52
            @Override // java9.util.function.Function
            public CompletableFuture<Void> apply(Object obj) {
                return CommonSysApiInverseRequests.completedVoidFuture;
            }
        });
    }

    @Override // api.requests.com.neusoft.common.commonsysapiinverse.ICommonSysApiInverseRequests
    public CompletableFuture<Void> fireSetTraceLog(ReqSetTraceLog reqSetTraceLog, final Duration duration) {
        return fireSetTraceLog(reqSetTraceLog).thenCompose((Function<? super Void, ? extends CompletionStage<U>>) new Function<Void, CompletableFuture<Void>>() { // from class: api.requests.com.neusoft.common.commonsysapiinverse.CommonSysApiInverseRequests.53
            @Override // java9.util.function.Function
            public CompletableFuture<Void> apply(Void r2) {
                return CommonUtil.asyncSleep(duration, CommonSysApiInverseRequests.this.contextUtil.getDefaultExecutor());
            }
        });
    }

    @Override // api.requests.com.neusoft.common.commonsysapiinverse.ICommonSysApiInverseRequests
    public CompletableFuture<Void> fireSetTraceLog(ReqSetTraceLog.Arguments arguments) {
        ReqSetTraceLog reqSetTraceLog = new ReqSetTraceLog();
        reqSetTraceLog.setArguments(arguments);
        reqSetTraceLog.setResponse(null);
        String str = this.component;
        if (str != null) {
            reqSetTraceLog.setComponent(str);
        }
        return fireSetTraceLog(reqSetTraceLog);
    }

    @Override // api.requests.com.neusoft.common.commonsysapiinverse.ICommonSysApiInverseRequests
    public CompletableFuture<Void> fireSetTraceLog(ReqSetTraceLog.Arguments arguments, final Duration duration) {
        return fireSetTraceLog(arguments).thenCompose((Function<? super Void, ? extends CompletionStage<U>>) new Function<Void, CompletableFuture<Void>>() { // from class: api.requests.com.neusoft.common.commonsysapiinverse.CommonSysApiInverseRequests.54
            @Override // java9.util.function.Function
            public CompletableFuture<Void> apply(Void r2) {
                return CommonUtil.asyncSleep(duration, CommonSysApiInverseRequests.this.contextUtil.getDefaultExecutor());
            }
        });
    }

    @Override // api.requests.com.neusoft.common.commonsysapiinverse.ICommonSysApiInverseRequests
    public CompletableFuture<ResGetTraceLog> requestGetTraceLog(ReqGetTraceLog reqGetTraceLog) {
        if (reqGetTraceLog.getResponse() == null) {
            reqGetTraceLog.setResponse(new ResGetTraceLog());
        }
        String str = this.component;
        if (str != null) {
            reqGetTraceLog.setComponent(str);
            reqGetTraceLog.getResponse().setComponent(this.component);
        }
        return Communication.request(this.contextUtil.createFromClass(reqGetTraceLog));
    }

    @Override // api.requests.com.neusoft.common.commonsysapiinverse.ICommonSysApiInverseRequests
    public CompletableFuture<ResGetTraceLog> requestGetTraceLog(ReqGetTraceLog reqGetTraceLog, final Duration duration) {
        return requestGetTraceLog(reqGetTraceLog).thenCompose((Function<? super ResGetTraceLog, ? extends CompletionStage<U>>) new Function<ResGetTraceLog, CompletableFuture<ResGetTraceLog>>() { // from class: api.requests.com.neusoft.common.commonsysapiinverse.CommonSysApiInverseRequests.55
            @Override // java9.util.function.Function
            public CompletableFuture<ResGetTraceLog> apply(ResGetTraceLog resGetTraceLog) {
                return CommonUtil.delayAsync(resGetTraceLog, duration, CommonSysApiInverseRequests.this.contextUtil.getDefaultExecutor());
            }
        });
    }

    @Override // api.requests.com.neusoft.common.commonsysapiinverse.ICommonSysApiInverseRequests
    public CompletableFuture<ResGetTraceLog> requestGetTraceLog() {
        return requestGetTraceLog(new ReqGetTraceLog());
    }

    @Override // api.requests.com.neusoft.common.commonsysapiinverse.ICommonSysApiInverseRequests
    public CompletableFuture<ResGetTraceLog> requestGetTraceLog(Duration duration) {
        return requestGetTraceLog(new ReqGetTraceLog(), duration);
    }

    @Override // api.requests.com.neusoft.common.commonsysapiinverse.ICommonSysApiInverseRequests
    public CompletableFuture<Void> fireGetTraceLog(ReqGetTraceLog reqGetTraceLog) {
        reqGetTraceLog.setResponse(null);
        String str = this.component;
        if (str != null) {
            reqGetTraceLog.setComponent(str);
        }
        return Communication.request(this.contextUtil.createFromClass(reqGetTraceLog)).thenCompose(new Function<Object, CompletableFuture<Void>>() { // from class: api.requests.com.neusoft.common.commonsysapiinverse.CommonSysApiInverseRequests.56
            @Override // java9.util.function.Function
            public CompletableFuture<Void> apply(Object obj) {
                return CommonSysApiInverseRequests.completedVoidFuture;
            }
        });
    }

    @Override // api.requests.com.neusoft.common.commonsysapiinverse.ICommonSysApiInverseRequests
    public CompletableFuture<Void> fireGetTraceLog(ReqGetTraceLog reqGetTraceLog, final Duration duration) {
        return fireGetTraceLog(reqGetTraceLog).thenCompose((Function<? super Void, ? extends CompletionStage<U>>) new Function<Void, CompletableFuture<Void>>() { // from class: api.requests.com.neusoft.common.commonsysapiinverse.CommonSysApiInverseRequests.57
            @Override // java9.util.function.Function
            public CompletableFuture<Void> apply(Void r2) {
                return CommonUtil.asyncSleep(duration, CommonSysApiInverseRequests.this.contextUtil.getDefaultExecutor());
            }
        });
    }

    @Override // api.requests.com.neusoft.common.commonsysapiinverse.ICommonSysApiInverseRequests
    public CompletableFuture<Void> fireGetTraceLog() {
        return fireGetTraceLog(new ReqGetTraceLog());
    }

    @Override // api.requests.com.neusoft.common.commonsysapiinverse.ICommonSysApiInverseRequests
    public CompletableFuture<Void> fireGetTraceLog(final Duration duration) {
        return fireGetTraceLog(new ReqGetTraceLog()).thenCompose((Function<? super Void, ? extends CompletionStage<U>>) new Function<Void, CompletableFuture<Void>>() { // from class: api.requests.com.neusoft.common.commonsysapiinverse.CommonSysApiInverseRequests.58
            @Override // java9.util.function.Function
            public CompletableFuture<Void> apply(Void r2) {
                return CommonUtil.asyncSleep(duration, CommonSysApiInverseRequests.this.contextUtil.getDefaultExecutor());
            }
        });
    }

    @Override // api.requests.com.neusoft.common.commonsysapiinverse.ICommonSysApiInverseRequests
    public CompletableFuture<Void> fireSetOnlineFeatureAvailableStatus(ReqSetOnlineFeatureAvailableStatus reqSetOnlineFeatureAvailableStatus) {
        reqSetOnlineFeatureAvailableStatus.setResponse(null);
        String str = this.component;
        if (str != null) {
            reqSetOnlineFeatureAvailableStatus.setComponent(str);
        }
        return Communication.request(this.contextUtil.createFromClass(reqSetOnlineFeatureAvailableStatus)).thenCompose(new Function<Object, CompletableFuture<Void>>() { // from class: api.requests.com.neusoft.common.commonsysapiinverse.CommonSysApiInverseRequests.59
            @Override // java9.util.function.Function
            public CompletableFuture<Void> apply(Object obj) {
                return CommonSysApiInverseRequests.completedVoidFuture;
            }
        });
    }

    @Override // api.requests.com.neusoft.common.commonsysapiinverse.ICommonSysApiInverseRequests
    public CompletableFuture<Void> fireSetOnlineFeatureAvailableStatus(ReqSetOnlineFeatureAvailableStatus reqSetOnlineFeatureAvailableStatus, final Duration duration) {
        return fireSetOnlineFeatureAvailableStatus(reqSetOnlineFeatureAvailableStatus).thenCompose((Function<? super Void, ? extends CompletionStage<U>>) new Function<Void, CompletableFuture<Void>>() { // from class: api.requests.com.neusoft.common.commonsysapiinverse.CommonSysApiInverseRequests.60
            @Override // java9.util.function.Function
            public CompletableFuture<Void> apply(Void r2) {
                return CommonUtil.asyncSleep(duration, CommonSysApiInverseRequests.this.contextUtil.getDefaultExecutor());
            }
        });
    }

    @Override // api.requests.com.neusoft.common.commonsysapiinverse.ICommonSysApiInverseRequests
    public CompletableFuture<Void> fireSetOnlineFeatureAvailableStatus(ReqSetOnlineFeatureAvailableStatus.Arguments arguments) {
        ReqSetOnlineFeatureAvailableStatus reqSetOnlineFeatureAvailableStatus = new ReqSetOnlineFeatureAvailableStatus();
        reqSetOnlineFeatureAvailableStatus.setArguments(arguments);
        reqSetOnlineFeatureAvailableStatus.setResponse(null);
        String str = this.component;
        if (str != null) {
            reqSetOnlineFeatureAvailableStatus.setComponent(str);
        }
        return fireSetOnlineFeatureAvailableStatus(reqSetOnlineFeatureAvailableStatus);
    }

    @Override // api.requests.com.neusoft.common.commonsysapiinverse.ICommonSysApiInverseRequests
    public CompletableFuture<Void> fireSetOnlineFeatureAvailableStatus(ReqSetOnlineFeatureAvailableStatus.Arguments arguments, final Duration duration) {
        return fireSetOnlineFeatureAvailableStatus(arguments).thenCompose((Function<? super Void, ? extends CompletionStage<U>>) new Function<Void, CompletableFuture<Void>>() { // from class: api.requests.com.neusoft.common.commonsysapiinverse.CommonSysApiInverseRequests.61
            @Override // java9.util.function.Function
            public CompletableFuture<Void> apply(Void r2) {
                return CommonUtil.asyncSleep(duration, CommonSysApiInverseRequests.this.contextUtil.getDefaultExecutor());
            }
        });
    }

    @Override // api.requests.com.neusoft.common.commonsysapiinverse.ICommonSysApiInverseRequests
    public CompletableFuture<Void> fireSignalStrength(ReqSignalStrength reqSignalStrength) {
        reqSignalStrength.setResponse(null);
        String str = this.component;
        if (str != null) {
            reqSignalStrength.setComponent(str);
        }
        return Communication.request(this.contextUtil.createFromClass(reqSignalStrength)).thenCompose(new Function<Object, CompletableFuture<Void>>() { // from class: api.requests.com.neusoft.common.commonsysapiinverse.CommonSysApiInverseRequests.62
            @Override // java9.util.function.Function
            public CompletableFuture<Void> apply(Object obj) {
                return CommonSysApiInverseRequests.completedVoidFuture;
            }
        });
    }

    @Override // api.requests.com.neusoft.common.commonsysapiinverse.ICommonSysApiInverseRequests
    public CompletableFuture<Void> fireSignalStrength(ReqSignalStrength reqSignalStrength, final Duration duration) {
        return fireSignalStrength(reqSignalStrength).thenCompose((Function<? super Void, ? extends CompletionStage<U>>) new Function<Void, CompletableFuture<Void>>() { // from class: api.requests.com.neusoft.common.commonsysapiinverse.CommonSysApiInverseRequests.63
            @Override // java9.util.function.Function
            public CompletableFuture<Void> apply(Void r2) {
                return CommonUtil.asyncSleep(duration, CommonSysApiInverseRequests.this.contextUtil.getDefaultExecutor());
            }
        });
    }

    @Override // api.requests.com.neusoft.common.commonsysapiinverse.ICommonSysApiInverseRequests
    public CompletableFuture<Void> fireSignalStrength(ReqSignalStrength.Arguments arguments) {
        ReqSignalStrength reqSignalStrength = new ReqSignalStrength();
        reqSignalStrength.setArguments(arguments);
        reqSignalStrength.setResponse(null);
        String str = this.component;
        if (str != null) {
            reqSignalStrength.setComponent(str);
        }
        return fireSignalStrength(reqSignalStrength);
    }

    @Override // api.requests.com.neusoft.common.commonsysapiinverse.ICommonSysApiInverseRequests
    public CompletableFuture<Void> fireSignalStrength(ReqSignalStrength.Arguments arguments, final Duration duration) {
        return fireSignalStrength(arguments).thenCompose((Function<? super Void, ? extends CompletionStage<U>>) new Function<Void, CompletableFuture<Void>>() { // from class: api.requests.com.neusoft.common.commonsysapiinverse.CommonSysApiInverseRequests.64
            @Override // java9.util.function.Function
            public CompletableFuture<Void> apply(Void r2) {
                return CommonUtil.asyncSleep(duration, CommonSysApiInverseRequests.this.contextUtil.getDefaultExecutor());
            }
        });
    }

    @Override // api.requests.com.neusoft.common.commonsysapiinverse.ICommonSysApiInverseRequests
    public CompletableFuture<ResMapZoomInOut> requestMapZoomInOut(ReqMapZoomInOut reqMapZoomInOut) {
        if (reqMapZoomInOut.getResponse() == null) {
            reqMapZoomInOut.setResponse(new ResMapZoomInOut());
        }
        String str = this.component;
        if (str != null) {
            reqMapZoomInOut.setComponent(str);
            reqMapZoomInOut.getResponse().setComponent(this.component);
        }
        return Communication.request(this.contextUtil.createFromClass(reqMapZoomInOut));
    }

    @Override // api.requests.com.neusoft.common.commonsysapiinverse.ICommonSysApiInverseRequests
    public CompletableFuture<ResMapZoomInOut> requestMapZoomInOut(ReqMapZoomInOut reqMapZoomInOut, final Duration duration) {
        return requestMapZoomInOut(reqMapZoomInOut).thenCompose((Function<? super ResMapZoomInOut, ? extends CompletionStage<U>>) new Function<ResMapZoomInOut, CompletableFuture<ResMapZoomInOut>>() { // from class: api.requests.com.neusoft.common.commonsysapiinverse.CommonSysApiInverseRequests.65
            @Override // java9.util.function.Function
            public CompletableFuture<ResMapZoomInOut> apply(ResMapZoomInOut resMapZoomInOut) {
                return CommonUtil.delayAsync(resMapZoomInOut, duration, CommonSysApiInverseRequests.this.contextUtil.getDefaultExecutor());
            }
        });
    }

    @Override // api.requests.com.neusoft.common.commonsysapiinverse.ICommonSysApiInverseRequests
    public CompletableFuture<ResMapZoomInOut.Arguments> requestMapZoomInOut(ReqMapZoomInOut.Arguments arguments) {
        ReqMapZoomInOut reqMapZoomInOut = new ReqMapZoomInOut();
        reqMapZoomInOut.setResponse(new ResMapZoomInOut());
        reqMapZoomInOut.setArguments(arguments);
        return requestMapZoomInOut(reqMapZoomInOut).thenApply((Function<? super ResMapZoomInOut, ? extends U>) new Function<ResMapZoomInOut, ResMapZoomInOut.Arguments>() { // from class: api.requests.com.neusoft.common.commonsysapiinverse.CommonSysApiInverseRequests.66
            @Override // java9.util.function.Function
            public ResMapZoomInOut.Arguments apply(ResMapZoomInOut resMapZoomInOut) {
                return resMapZoomInOut.getArguments();
            }
        });
    }

    @Override // api.requests.com.neusoft.common.commonsysapiinverse.ICommonSysApiInverseRequests
    public CompletableFuture<ResMapZoomInOut.Arguments> requestMapZoomInOut(ReqMapZoomInOut.Arguments arguments, final Duration duration) {
        return requestMapZoomInOut(arguments).thenCompose((Function<? super ResMapZoomInOut.Arguments, ? extends CompletionStage<U>>) new Function<ResMapZoomInOut.Arguments, CompletableFuture<ResMapZoomInOut.Arguments>>() { // from class: api.requests.com.neusoft.common.commonsysapiinverse.CommonSysApiInverseRequests.67
            @Override // java9.util.function.Function
            public CompletableFuture<ResMapZoomInOut.Arguments> apply(ResMapZoomInOut.Arguments arguments2) {
                return CommonUtil.delayAsync(arguments2, duration, CommonSysApiInverseRequests.this.contextUtil.getDefaultExecutor());
            }
        });
    }

    @Override // api.requests.com.neusoft.common.commonsysapiinverse.ICommonSysApiInverseRequests
    public CompletableFuture<Void> fireMapZoomInOut(ReqMapZoomInOut reqMapZoomInOut) {
        reqMapZoomInOut.setResponse(null);
        String str = this.component;
        if (str != null) {
            reqMapZoomInOut.setComponent(str);
        }
        return Communication.request(this.contextUtil.createFromClass(reqMapZoomInOut)).thenCompose(new Function<Object, CompletableFuture<Void>>() { // from class: api.requests.com.neusoft.common.commonsysapiinverse.CommonSysApiInverseRequests.68
            @Override // java9.util.function.Function
            public CompletableFuture<Void> apply(Object obj) {
                return CommonSysApiInverseRequests.completedVoidFuture;
            }
        });
    }

    @Override // api.requests.com.neusoft.common.commonsysapiinverse.ICommonSysApiInverseRequests
    public CompletableFuture<Void> fireMapZoomInOut(ReqMapZoomInOut reqMapZoomInOut, final Duration duration) {
        return fireMapZoomInOut(reqMapZoomInOut).thenCompose((Function<? super Void, ? extends CompletionStage<U>>) new Function<Void, CompletableFuture<Void>>() { // from class: api.requests.com.neusoft.common.commonsysapiinverse.CommonSysApiInverseRequests.69
            @Override // java9.util.function.Function
            public CompletableFuture<Void> apply(Void r2) {
                return CommonUtil.asyncSleep(duration, CommonSysApiInverseRequests.this.contextUtil.getDefaultExecutor());
            }
        });
    }

    @Override // api.requests.com.neusoft.common.commonsysapiinverse.ICommonSysApiInverseRequests
    public CompletableFuture<Void> fireMapZoomInOut(ReqMapZoomInOut.Arguments arguments) {
        ReqMapZoomInOut reqMapZoomInOut = new ReqMapZoomInOut();
        reqMapZoomInOut.setArguments(arguments);
        reqMapZoomInOut.setResponse(null);
        String str = this.component;
        if (str != null) {
            reqMapZoomInOut.setComponent(str);
        }
        return fireMapZoomInOut(reqMapZoomInOut);
    }

    @Override // api.requests.com.neusoft.common.commonsysapiinverse.ICommonSysApiInverseRequests
    public CompletableFuture<Void> fireMapZoomInOut(ReqMapZoomInOut.Arguments arguments, final Duration duration) {
        return fireMapZoomInOut(arguments).thenCompose((Function<? super Void, ? extends CompletionStage<U>>) new Function<Void, CompletableFuture<Void>>() { // from class: api.requests.com.neusoft.common.commonsysapiinverse.CommonSysApiInverseRequests.70
            @Override // java9.util.function.Function
            public CompletableFuture<Void> apply(Void r2) {
                return CommonUtil.asyncSleep(duration, CommonSysApiInverseRequests.this.contextUtil.getDefaultExecutor());
            }
        });
    }

    @Override // api.requests.com.neusoft.common.commonsysapiinverse.ICommonSysApiInverseRequests
    public CompletableFuture<ResGetMapScaleLevel> requestGetMapScaleLevel(ReqGetMapScaleLevel reqGetMapScaleLevel) {
        if (reqGetMapScaleLevel.getResponse() == null) {
            reqGetMapScaleLevel.setResponse(new ResGetMapScaleLevel());
        }
        String str = this.component;
        if (str != null) {
            reqGetMapScaleLevel.setComponent(str);
            reqGetMapScaleLevel.getResponse().setComponent(this.component);
        }
        return Communication.request(this.contextUtil.createFromClass(reqGetMapScaleLevel));
    }

    @Override // api.requests.com.neusoft.common.commonsysapiinverse.ICommonSysApiInverseRequests
    public CompletableFuture<ResGetMapScaleLevel> requestGetMapScaleLevel(ReqGetMapScaleLevel reqGetMapScaleLevel, final Duration duration) {
        return requestGetMapScaleLevel(reqGetMapScaleLevel).thenCompose((Function<? super ResGetMapScaleLevel, ? extends CompletionStage<U>>) new Function<ResGetMapScaleLevel, CompletableFuture<ResGetMapScaleLevel>>() { // from class: api.requests.com.neusoft.common.commonsysapiinverse.CommonSysApiInverseRequests.71
            @Override // java9.util.function.Function
            public CompletableFuture<ResGetMapScaleLevel> apply(ResGetMapScaleLevel resGetMapScaleLevel) {
                return CommonUtil.delayAsync(resGetMapScaleLevel, duration, CommonSysApiInverseRequests.this.contextUtil.getDefaultExecutor());
            }
        });
    }

    @Override // api.requests.com.neusoft.common.commonsysapiinverse.ICommonSysApiInverseRequests
    public CompletableFuture<ResGetMapScaleLevel> requestGetMapScaleLevel() {
        return requestGetMapScaleLevel(new ReqGetMapScaleLevel());
    }

    @Override // api.requests.com.neusoft.common.commonsysapiinverse.ICommonSysApiInverseRequests
    public CompletableFuture<ResGetMapScaleLevel> requestGetMapScaleLevel(Duration duration) {
        return requestGetMapScaleLevel(new ReqGetMapScaleLevel(), duration);
    }

    @Override // api.requests.com.neusoft.common.commonsysapiinverse.ICommonSysApiInverseRequests
    public CompletableFuture<Void> fireGetMapScaleLevel(ReqGetMapScaleLevel reqGetMapScaleLevel) {
        reqGetMapScaleLevel.setResponse(null);
        String str = this.component;
        if (str != null) {
            reqGetMapScaleLevel.setComponent(str);
        }
        return Communication.request(this.contextUtil.createFromClass(reqGetMapScaleLevel)).thenCompose(new Function<Object, CompletableFuture<Void>>() { // from class: api.requests.com.neusoft.common.commonsysapiinverse.CommonSysApiInverseRequests.72
            @Override // java9.util.function.Function
            public CompletableFuture<Void> apply(Object obj) {
                return CommonSysApiInverseRequests.completedVoidFuture;
            }
        });
    }

    @Override // api.requests.com.neusoft.common.commonsysapiinverse.ICommonSysApiInverseRequests
    public CompletableFuture<Void> fireGetMapScaleLevel(ReqGetMapScaleLevel reqGetMapScaleLevel, final Duration duration) {
        return fireGetMapScaleLevel(reqGetMapScaleLevel).thenCompose((Function<? super Void, ? extends CompletionStage<U>>) new Function<Void, CompletableFuture<Void>>() { // from class: api.requests.com.neusoft.common.commonsysapiinverse.CommonSysApiInverseRequests.73
            @Override // java9.util.function.Function
            public CompletableFuture<Void> apply(Void r2) {
                return CommonUtil.asyncSleep(duration, CommonSysApiInverseRequests.this.contextUtil.getDefaultExecutor());
            }
        });
    }

    @Override // api.requests.com.neusoft.common.commonsysapiinverse.ICommonSysApiInverseRequests
    public CompletableFuture<Void> fireGetMapScaleLevel() {
        return fireGetMapScaleLevel(new ReqGetMapScaleLevel());
    }

    @Override // api.requests.com.neusoft.common.commonsysapiinverse.ICommonSysApiInverseRequests
    public CompletableFuture<Void> fireGetMapScaleLevel(final Duration duration) {
        return fireGetMapScaleLevel(new ReqGetMapScaleLevel()).thenCompose((Function<? super Void, ? extends CompletionStage<U>>) new Function<Void, CompletableFuture<Void>>() { // from class: api.requests.com.neusoft.common.commonsysapiinverse.CommonSysApiInverseRequests.74
            @Override // java9.util.function.Function
            public CompletableFuture<Void> apply(Void r2) {
                return CommonUtil.asyncSleep(duration, CommonSysApiInverseRequests.this.contextUtil.getDefaultExecutor());
            }
        });
    }

    @Override // api.requests.com.neusoft.common.commonsysapiinverse.ICommonSysApiInverseRequests
    public CompletableFuture<ResBackToCCP> requestBackToCCP(ReqBackToCCP reqBackToCCP) {
        if (reqBackToCCP.getResponse() == null) {
            reqBackToCCP.setResponse(new ResBackToCCP());
        }
        String str = this.component;
        if (str != null) {
            reqBackToCCP.setComponent(str);
            reqBackToCCP.getResponse().setComponent(this.component);
        }
        return Communication.request(this.contextUtil.createFromClass(reqBackToCCP));
    }

    @Override // api.requests.com.neusoft.common.commonsysapiinverse.ICommonSysApiInverseRequests
    public CompletableFuture<ResBackToCCP> requestBackToCCP(ReqBackToCCP reqBackToCCP, final Duration duration) {
        return requestBackToCCP(reqBackToCCP).thenCompose((Function<? super ResBackToCCP, ? extends CompletionStage<U>>) new Function<ResBackToCCP, CompletableFuture<ResBackToCCP>>() { // from class: api.requests.com.neusoft.common.commonsysapiinverse.CommonSysApiInverseRequests.75
            @Override // java9.util.function.Function
            public CompletableFuture<ResBackToCCP> apply(ResBackToCCP resBackToCCP) {
                return CommonUtil.delayAsync(resBackToCCP, duration, CommonSysApiInverseRequests.this.contextUtil.getDefaultExecutor());
            }
        });
    }

    @Override // api.requests.com.neusoft.common.commonsysapiinverse.ICommonSysApiInverseRequests
    public CompletableFuture<ResBackToCCP> requestBackToCCP() {
        return requestBackToCCP(new ReqBackToCCP());
    }

    @Override // api.requests.com.neusoft.common.commonsysapiinverse.ICommonSysApiInverseRequests
    public CompletableFuture<ResBackToCCP> requestBackToCCP(Duration duration) {
        return requestBackToCCP(new ReqBackToCCP(), duration);
    }

    @Override // api.requests.com.neusoft.common.commonsysapiinverse.ICommonSysApiInverseRequests
    public CompletableFuture<Void> fireBackToCCP(ReqBackToCCP reqBackToCCP) {
        reqBackToCCP.setResponse(null);
        String str = this.component;
        if (str != null) {
            reqBackToCCP.setComponent(str);
        }
        return Communication.request(this.contextUtil.createFromClass(reqBackToCCP)).thenCompose(new Function<Object, CompletableFuture<Void>>() { // from class: api.requests.com.neusoft.common.commonsysapiinverse.CommonSysApiInverseRequests.76
            @Override // java9.util.function.Function
            public CompletableFuture<Void> apply(Object obj) {
                return CommonSysApiInverseRequests.completedVoidFuture;
            }
        });
    }

    @Override // api.requests.com.neusoft.common.commonsysapiinverse.ICommonSysApiInverseRequests
    public CompletableFuture<Void> fireBackToCCP(ReqBackToCCP reqBackToCCP, final Duration duration) {
        return fireBackToCCP(reqBackToCCP).thenCompose((Function<? super Void, ? extends CompletionStage<U>>) new Function<Void, CompletableFuture<Void>>() { // from class: api.requests.com.neusoft.common.commonsysapiinverse.CommonSysApiInverseRequests.77
            @Override // java9.util.function.Function
            public CompletableFuture<Void> apply(Void r2) {
                return CommonUtil.asyncSleep(duration, CommonSysApiInverseRequests.this.contextUtil.getDefaultExecutor());
            }
        });
    }

    @Override // api.requests.com.neusoft.common.commonsysapiinverse.ICommonSysApiInverseRequests
    public CompletableFuture<Void> fireBackToCCP() {
        return fireBackToCCP(new ReqBackToCCP());
    }

    @Override // api.requests.com.neusoft.common.commonsysapiinverse.ICommonSysApiInverseRequests
    public CompletableFuture<Void> fireBackToCCP(final Duration duration) {
        return fireBackToCCP(new ReqBackToCCP()).thenCompose((Function<? super Void, ? extends CompletionStage<U>>) new Function<Void, CompletableFuture<Void>>() { // from class: api.requests.com.neusoft.common.commonsysapiinverse.CommonSysApiInverseRequests.78
            @Override // java9.util.function.Function
            public CompletableFuture<Void> apply(Void r2) {
                return CommonUtil.asyncSleep(duration, CommonSysApiInverseRequests.this.contextUtil.getDefaultExecutor());
            }
        });
    }

    @Override // api.requests.com.neusoft.common.commonsysapiinverse.ICommonSysApiInverseRequests
    public CompletableFuture<ResSwitchTrafficInfo> requestSwitchTrafficInfo(ReqSwitchTrafficInfo reqSwitchTrafficInfo) {
        if (reqSwitchTrafficInfo.getResponse() == null) {
            reqSwitchTrafficInfo.setResponse(new ResSwitchTrafficInfo());
        }
        String str = this.component;
        if (str != null) {
            reqSwitchTrafficInfo.setComponent(str);
            reqSwitchTrafficInfo.getResponse().setComponent(this.component);
        }
        return Communication.request(this.contextUtil.createFromClass(reqSwitchTrafficInfo));
    }

    @Override // api.requests.com.neusoft.common.commonsysapiinverse.ICommonSysApiInverseRequests
    public CompletableFuture<ResSwitchTrafficInfo> requestSwitchTrafficInfo(ReqSwitchTrafficInfo reqSwitchTrafficInfo, final Duration duration) {
        return requestSwitchTrafficInfo(reqSwitchTrafficInfo).thenCompose((Function<? super ResSwitchTrafficInfo, ? extends CompletionStage<U>>) new Function<ResSwitchTrafficInfo, CompletableFuture<ResSwitchTrafficInfo>>() { // from class: api.requests.com.neusoft.common.commonsysapiinverse.CommonSysApiInverseRequests.79
            @Override // java9.util.function.Function
            public CompletableFuture<ResSwitchTrafficInfo> apply(ResSwitchTrafficInfo resSwitchTrafficInfo) {
                return CommonUtil.delayAsync(resSwitchTrafficInfo, duration, CommonSysApiInverseRequests.this.contextUtil.getDefaultExecutor());
            }
        });
    }

    @Override // api.requests.com.neusoft.common.commonsysapiinverse.ICommonSysApiInverseRequests
    public CompletableFuture<ResSwitchTrafficInfo.Arguments> requestSwitchTrafficInfo(ReqSwitchTrafficInfo.Arguments arguments) {
        ReqSwitchTrafficInfo reqSwitchTrafficInfo = new ReqSwitchTrafficInfo();
        reqSwitchTrafficInfo.setResponse(new ResSwitchTrafficInfo());
        reqSwitchTrafficInfo.setArguments(arguments);
        return requestSwitchTrafficInfo(reqSwitchTrafficInfo).thenApply((Function<? super ResSwitchTrafficInfo, ? extends U>) new Function<ResSwitchTrafficInfo, ResSwitchTrafficInfo.Arguments>() { // from class: api.requests.com.neusoft.common.commonsysapiinverse.CommonSysApiInverseRequests.80
            @Override // java9.util.function.Function
            public ResSwitchTrafficInfo.Arguments apply(ResSwitchTrafficInfo resSwitchTrafficInfo) {
                return resSwitchTrafficInfo.getArguments();
            }
        });
    }

    @Override // api.requests.com.neusoft.common.commonsysapiinverse.ICommonSysApiInverseRequests
    public CompletableFuture<ResSwitchTrafficInfo.Arguments> requestSwitchTrafficInfo(ReqSwitchTrafficInfo.Arguments arguments, final Duration duration) {
        return requestSwitchTrafficInfo(arguments).thenCompose((Function<? super ResSwitchTrafficInfo.Arguments, ? extends CompletionStage<U>>) new Function<ResSwitchTrafficInfo.Arguments, CompletableFuture<ResSwitchTrafficInfo.Arguments>>() { // from class: api.requests.com.neusoft.common.commonsysapiinverse.CommonSysApiInverseRequests.81
            @Override // java9.util.function.Function
            public CompletableFuture<ResSwitchTrafficInfo.Arguments> apply(ResSwitchTrafficInfo.Arguments arguments2) {
                return CommonUtil.delayAsync(arguments2, duration, CommonSysApiInverseRequests.this.contextUtil.getDefaultExecutor());
            }
        });
    }

    @Override // api.requests.com.neusoft.common.commonsysapiinverse.ICommonSysApiInverseRequests
    public CompletableFuture<Void> fireSwitchTrafficInfo(ReqSwitchTrafficInfo reqSwitchTrafficInfo) {
        reqSwitchTrafficInfo.setResponse(null);
        String str = this.component;
        if (str != null) {
            reqSwitchTrafficInfo.setComponent(str);
        }
        return Communication.request(this.contextUtil.createFromClass(reqSwitchTrafficInfo)).thenCompose(new Function<Object, CompletableFuture<Void>>() { // from class: api.requests.com.neusoft.common.commonsysapiinverse.CommonSysApiInverseRequests.82
            @Override // java9.util.function.Function
            public CompletableFuture<Void> apply(Object obj) {
                return CommonSysApiInverseRequests.completedVoidFuture;
            }
        });
    }

    @Override // api.requests.com.neusoft.common.commonsysapiinverse.ICommonSysApiInverseRequests
    public CompletableFuture<Void> fireSwitchTrafficInfo(ReqSwitchTrafficInfo reqSwitchTrafficInfo, final Duration duration) {
        return fireSwitchTrafficInfo(reqSwitchTrafficInfo).thenCompose((Function<? super Void, ? extends CompletionStage<U>>) new Function<Void, CompletableFuture<Void>>() { // from class: api.requests.com.neusoft.common.commonsysapiinverse.CommonSysApiInverseRequests.83
            @Override // java9.util.function.Function
            public CompletableFuture<Void> apply(Void r2) {
                return CommonUtil.asyncSleep(duration, CommonSysApiInverseRequests.this.contextUtil.getDefaultExecutor());
            }
        });
    }

    @Override // api.requests.com.neusoft.common.commonsysapiinverse.ICommonSysApiInverseRequests
    public CompletableFuture<Void> fireSwitchTrafficInfo(ReqSwitchTrafficInfo.Arguments arguments) {
        ReqSwitchTrafficInfo reqSwitchTrafficInfo = new ReqSwitchTrafficInfo();
        reqSwitchTrafficInfo.setArguments(arguments);
        reqSwitchTrafficInfo.setResponse(null);
        String str = this.component;
        if (str != null) {
            reqSwitchTrafficInfo.setComponent(str);
        }
        return fireSwitchTrafficInfo(reqSwitchTrafficInfo);
    }

    @Override // api.requests.com.neusoft.common.commonsysapiinverse.ICommonSysApiInverseRequests
    public CompletableFuture<Void> fireSwitchTrafficInfo(ReqSwitchTrafficInfo.Arguments arguments, final Duration duration) {
        return fireSwitchTrafficInfo(arguments).thenCompose((Function<? super Void, ? extends CompletionStage<U>>) new Function<Void, CompletableFuture<Void>>() { // from class: api.requests.com.neusoft.common.commonsysapiinverse.CommonSysApiInverseRequests.84
            @Override // java9.util.function.Function
            public CompletableFuture<Void> apply(Void r2) {
                return CommonUtil.asyncSleep(duration, CommonSysApiInverseRequests.this.contextUtil.getDefaultExecutor());
            }
        });
    }

    @Override // api.requests.com.neusoft.common.commonsysapiinverse.ICommonSysApiInverseRequests
    public CompletableFuture<ResGetTrafficInfoState> requestGetTrafficInfoState(ReqGetTrafficInfoState reqGetTrafficInfoState) {
        if (reqGetTrafficInfoState.getResponse() == null) {
            reqGetTrafficInfoState.setResponse(new ResGetTrafficInfoState());
        }
        String str = this.component;
        if (str != null) {
            reqGetTrafficInfoState.setComponent(str);
            reqGetTrafficInfoState.getResponse().setComponent(this.component);
        }
        return Communication.request(this.contextUtil.createFromClass(reqGetTrafficInfoState));
    }

    @Override // api.requests.com.neusoft.common.commonsysapiinverse.ICommonSysApiInverseRequests
    public CompletableFuture<ResGetTrafficInfoState> requestGetTrafficInfoState(ReqGetTrafficInfoState reqGetTrafficInfoState, final Duration duration) {
        return requestGetTrafficInfoState(reqGetTrafficInfoState).thenCompose((Function<? super ResGetTrafficInfoState, ? extends CompletionStage<U>>) new Function<ResGetTrafficInfoState, CompletableFuture<ResGetTrafficInfoState>>() { // from class: api.requests.com.neusoft.common.commonsysapiinverse.CommonSysApiInverseRequests.85
            @Override // java9.util.function.Function
            public CompletableFuture<ResGetTrafficInfoState> apply(ResGetTrafficInfoState resGetTrafficInfoState) {
                return CommonUtil.delayAsync(resGetTrafficInfoState, duration, CommonSysApiInverseRequests.this.contextUtil.getDefaultExecutor());
            }
        });
    }

    @Override // api.requests.com.neusoft.common.commonsysapiinverse.ICommonSysApiInverseRequests
    public CompletableFuture<ResGetTrafficInfoState> requestGetTrafficInfoState() {
        return requestGetTrafficInfoState(new ReqGetTrafficInfoState());
    }

    @Override // api.requests.com.neusoft.common.commonsysapiinverse.ICommonSysApiInverseRequests
    public CompletableFuture<ResGetTrafficInfoState> requestGetTrafficInfoState(Duration duration) {
        return requestGetTrafficInfoState(new ReqGetTrafficInfoState(), duration);
    }

    @Override // api.requests.com.neusoft.common.commonsysapiinverse.ICommonSysApiInverseRequests
    public CompletableFuture<Void> fireGetTrafficInfoState(ReqGetTrafficInfoState reqGetTrafficInfoState) {
        reqGetTrafficInfoState.setResponse(null);
        String str = this.component;
        if (str != null) {
            reqGetTrafficInfoState.setComponent(str);
        }
        return Communication.request(this.contextUtil.createFromClass(reqGetTrafficInfoState)).thenCompose(new Function<Object, CompletableFuture<Void>>() { // from class: api.requests.com.neusoft.common.commonsysapiinverse.CommonSysApiInverseRequests.86
            @Override // java9.util.function.Function
            public CompletableFuture<Void> apply(Object obj) {
                return CommonSysApiInverseRequests.completedVoidFuture;
            }
        });
    }

    @Override // api.requests.com.neusoft.common.commonsysapiinverse.ICommonSysApiInverseRequests
    public CompletableFuture<Void> fireGetTrafficInfoState(ReqGetTrafficInfoState reqGetTrafficInfoState, final Duration duration) {
        return fireGetTrafficInfoState(reqGetTrafficInfoState).thenCompose((Function<? super Void, ? extends CompletionStage<U>>) new Function<Void, CompletableFuture<Void>>() { // from class: api.requests.com.neusoft.common.commonsysapiinverse.CommonSysApiInverseRequests.87
            @Override // java9.util.function.Function
            public CompletableFuture<Void> apply(Void r2) {
                return CommonUtil.asyncSleep(duration, CommonSysApiInverseRequests.this.contextUtil.getDefaultExecutor());
            }
        });
    }

    @Override // api.requests.com.neusoft.common.commonsysapiinverse.ICommonSysApiInverseRequests
    public CompletableFuture<Void> fireGetTrafficInfoState() {
        return fireGetTrafficInfoState(new ReqGetTrafficInfoState());
    }

    @Override // api.requests.com.neusoft.common.commonsysapiinverse.ICommonSysApiInverseRequests
    public CompletableFuture<Void> fireGetTrafficInfoState(final Duration duration) {
        return fireGetTrafficInfoState(new ReqGetTrafficInfoState()).thenCompose((Function<? super Void, ? extends CompletionStage<U>>) new Function<Void, CompletableFuture<Void>>() { // from class: api.requests.com.neusoft.common.commonsysapiinverse.CommonSysApiInverseRequests.88
            @Override // java9.util.function.Function
            public CompletableFuture<Void> apply(Void r2) {
                return CommonUtil.asyncSleep(duration, CommonSysApiInverseRequests.this.contextUtil.getDefaultExecutor());
            }
        });
    }

    @Override // api.requests.com.neusoft.common.commonsysapiinverse.ICommonSysApiInverseRequests
    public CompletableFuture<ResGetMapViewMode> requestGetMapViewMode(ReqGetMapViewMode reqGetMapViewMode) {
        if (reqGetMapViewMode.getResponse() == null) {
            reqGetMapViewMode.setResponse(new ResGetMapViewMode());
        }
        String str = this.component;
        if (str != null) {
            reqGetMapViewMode.setComponent(str);
            reqGetMapViewMode.getResponse().setComponent(this.component);
        }
        return Communication.request(this.contextUtil.createFromClass(reqGetMapViewMode));
    }

    @Override // api.requests.com.neusoft.common.commonsysapiinverse.ICommonSysApiInverseRequests
    public CompletableFuture<ResGetMapViewMode> requestGetMapViewMode(ReqGetMapViewMode reqGetMapViewMode, final Duration duration) {
        return requestGetMapViewMode(reqGetMapViewMode).thenCompose((Function<? super ResGetMapViewMode, ? extends CompletionStage<U>>) new Function<ResGetMapViewMode, CompletableFuture<ResGetMapViewMode>>() { // from class: api.requests.com.neusoft.common.commonsysapiinverse.CommonSysApiInverseRequests.89
            @Override // java9.util.function.Function
            public CompletableFuture<ResGetMapViewMode> apply(ResGetMapViewMode resGetMapViewMode) {
                return CommonUtil.delayAsync(resGetMapViewMode, duration, CommonSysApiInverseRequests.this.contextUtil.getDefaultExecutor());
            }
        });
    }

    @Override // api.requests.com.neusoft.common.commonsysapiinverse.ICommonSysApiInverseRequests
    public CompletableFuture<ResGetMapViewMode> requestGetMapViewMode() {
        return requestGetMapViewMode(new ReqGetMapViewMode());
    }

    @Override // api.requests.com.neusoft.common.commonsysapiinverse.ICommonSysApiInverseRequests
    public CompletableFuture<ResGetMapViewMode> requestGetMapViewMode(Duration duration) {
        return requestGetMapViewMode(new ReqGetMapViewMode(), duration);
    }

    @Override // api.requests.com.neusoft.common.commonsysapiinverse.ICommonSysApiInverseRequests
    public CompletableFuture<Void> fireGetMapViewMode(ReqGetMapViewMode reqGetMapViewMode) {
        reqGetMapViewMode.setResponse(null);
        String str = this.component;
        if (str != null) {
            reqGetMapViewMode.setComponent(str);
        }
        return Communication.request(this.contextUtil.createFromClass(reqGetMapViewMode)).thenCompose(new Function<Object, CompletableFuture<Void>>() { // from class: api.requests.com.neusoft.common.commonsysapiinverse.CommonSysApiInverseRequests.90
            @Override // java9.util.function.Function
            public CompletableFuture<Void> apply(Object obj) {
                return CommonSysApiInverseRequests.completedVoidFuture;
            }
        });
    }

    @Override // api.requests.com.neusoft.common.commonsysapiinverse.ICommonSysApiInverseRequests
    public CompletableFuture<Void> fireGetMapViewMode(ReqGetMapViewMode reqGetMapViewMode, final Duration duration) {
        return fireGetMapViewMode(reqGetMapViewMode).thenCompose((Function<? super Void, ? extends CompletionStage<U>>) new Function<Void, CompletableFuture<Void>>() { // from class: api.requests.com.neusoft.common.commonsysapiinverse.CommonSysApiInverseRequests.91
            @Override // java9.util.function.Function
            public CompletableFuture<Void> apply(Void r2) {
                return CommonUtil.asyncSleep(duration, CommonSysApiInverseRequests.this.contextUtil.getDefaultExecutor());
            }
        });
    }

    @Override // api.requests.com.neusoft.common.commonsysapiinverse.ICommonSysApiInverseRequests
    public CompletableFuture<Void> fireGetMapViewMode() {
        return fireGetMapViewMode(new ReqGetMapViewMode());
    }

    @Override // api.requests.com.neusoft.common.commonsysapiinverse.ICommonSysApiInverseRequests
    public CompletableFuture<Void> fireGetMapViewMode(final Duration duration) {
        return fireGetMapViewMode(new ReqGetMapViewMode()).thenCompose((Function<? super Void, ? extends CompletionStage<U>>) new Function<Void, CompletableFuture<Void>>() { // from class: api.requests.com.neusoft.common.commonsysapiinverse.CommonSysApiInverseRequests.92
            @Override // java9.util.function.Function
            public CompletableFuture<Void> apply(Void r2) {
                return CommonUtil.asyncSleep(duration, CommonSysApiInverseRequests.this.contextUtil.getDefaultExecutor());
            }
        });
    }

    @Override // api.requests.com.neusoft.common.commonsysapiinverse.ICommonSysApiInverseRequests
    public CompletableFuture<ResSetMapViewMode> requestSetMapViewMode(ReqSetMapViewMode reqSetMapViewMode) {
        if (reqSetMapViewMode.getResponse() == null) {
            reqSetMapViewMode.setResponse(new ResSetMapViewMode());
        }
        String str = this.component;
        if (str != null) {
            reqSetMapViewMode.setComponent(str);
            reqSetMapViewMode.getResponse().setComponent(this.component);
        }
        return Communication.request(this.contextUtil.createFromClass(reqSetMapViewMode));
    }

    @Override // api.requests.com.neusoft.common.commonsysapiinverse.ICommonSysApiInverseRequests
    public CompletableFuture<ResSetMapViewMode> requestSetMapViewMode(ReqSetMapViewMode reqSetMapViewMode, final Duration duration) {
        return requestSetMapViewMode(reqSetMapViewMode).thenCompose((Function<? super ResSetMapViewMode, ? extends CompletionStage<U>>) new Function<ResSetMapViewMode, CompletableFuture<ResSetMapViewMode>>() { // from class: api.requests.com.neusoft.common.commonsysapiinverse.CommonSysApiInverseRequests.93
            @Override // java9.util.function.Function
            public CompletableFuture<ResSetMapViewMode> apply(ResSetMapViewMode resSetMapViewMode) {
                return CommonUtil.delayAsync(resSetMapViewMode, duration, CommonSysApiInverseRequests.this.contextUtil.getDefaultExecutor());
            }
        });
    }

    @Override // api.requests.com.neusoft.common.commonsysapiinverse.ICommonSysApiInverseRequests
    public CompletableFuture<ResSetMapViewMode.Arguments> requestSetMapViewMode(ReqSetMapViewMode.Arguments arguments) {
        ReqSetMapViewMode reqSetMapViewMode = new ReqSetMapViewMode();
        reqSetMapViewMode.setResponse(new ResSetMapViewMode());
        reqSetMapViewMode.setArguments(arguments);
        return requestSetMapViewMode(reqSetMapViewMode).thenApply((Function<? super ResSetMapViewMode, ? extends U>) new Function<ResSetMapViewMode, ResSetMapViewMode.Arguments>() { // from class: api.requests.com.neusoft.common.commonsysapiinverse.CommonSysApiInverseRequests.94
            @Override // java9.util.function.Function
            public ResSetMapViewMode.Arguments apply(ResSetMapViewMode resSetMapViewMode) {
                return resSetMapViewMode.getArguments();
            }
        });
    }

    @Override // api.requests.com.neusoft.common.commonsysapiinverse.ICommonSysApiInverseRequests
    public CompletableFuture<ResSetMapViewMode.Arguments> requestSetMapViewMode(ReqSetMapViewMode.Arguments arguments, final Duration duration) {
        return requestSetMapViewMode(arguments).thenCompose((Function<? super ResSetMapViewMode.Arguments, ? extends CompletionStage<U>>) new Function<ResSetMapViewMode.Arguments, CompletableFuture<ResSetMapViewMode.Arguments>>() { // from class: api.requests.com.neusoft.common.commonsysapiinverse.CommonSysApiInverseRequests.95
            @Override // java9.util.function.Function
            public CompletableFuture<ResSetMapViewMode.Arguments> apply(ResSetMapViewMode.Arguments arguments2) {
                return CommonUtil.delayAsync(arguments2, duration, CommonSysApiInverseRequests.this.contextUtil.getDefaultExecutor());
            }
        });
    }

    @Override // api.requests.com.neusoft.common.commonsysapiinverse.ICommonSysApiInverseRequests
    public CompletableFuture<Void> fireSetMapViewMode(ReqSetMapViewMode reqSetMapViewMode) {
        reqSetMapViewMode.setResponse(null);
        String str = this.component;
        if (str != null) {
            reqSetMapViewMode.setComponent(str);
        }
        return Communication.request(this.contextUtil.createFromClass(reqSetMapViewMode)).thenCompose(new Function<Object, CompletableFuture<Void>>() { // from class: api.requests.com.neusoft.common.commonsysapiinverse.CommonSysApiInverseRequests.96
            @Override // java9.util.function.Function
            public CompletableFuture<Void> apply(Object obj) {
                return CommonSysApiInverseRequests.completedVoidFuture;
            }
        });
    }

    @Override // api.requests.com.neusoft.common.commonsysapiinverse.ICommonSysApiInverseRequests
    public CompletableFuture<Void> fireSetMapViewMode(ReqSetMapViewMode reqSetMapViewMode, final Duration duration) {
        return fireSetMapViewMode(reqSetMapViewMode).thenCompose((Function<? super Void, ? extends CompletionStage<U>>) new Function<Void, CompletableFuture<Void>>() { // from class: api.requests.com.neusoft.common.commonsysapiinverse.CommonSysApiInverseRequests.97
            @Override // java9.util.function.Function
            public CompletableFuture<Void> apply(Void r2) {
                return CommonUtil.asyncSleep(duration, CommonSysApiInverseRequests.this.contextUtil.getDefaultExecutor());
            }
        });
    }

    @Override // api.requests.com.neusoft.common.commonsysapiinverse.ICommonSysApiInverseRequests
    public CompletableFuture<Void> fireSetMapViewMode(ReqSetMapViewMode.Arguments arguments) {
        ReqSetMapViewMode reqSetMapViewMode = new ReqSetMapViewMode();
        reqSetMapViewMode.setArguments(arguments);
        reqSetMapViewMode.setResponse(null);
        String str = this.component;
        if (str != null) {
            reqSetMapViewMode.setComponent(str);
        }
        return fireSetMapViewMode(reqSetMapViewMode);
    }

    @Override // api.requests.com.neusoft.common.commonsysapiinverse.ICommonSysApiInverseRequests
    public CompletableFuture<Void> fireSetMapViewMode(ReqSetMapViewMode.Arguments arguments, final Duration duration) {
        return fireSetMapViewMode(arguments).thenCompose((Function<? super Void, ? extends CompletionStage<U>>) new Function<Void, CompletableFuture<Void>>() { // from class: api.requests.com.neusoft.common.commonsysapiinverse.CommonSysApiInverseRequests.98
            @Override // java9.util.function.Function
            public CompletableFuture<Void> apply(Void r2) {
                return CommonUtil.asyncSleep(duration, CommonSysApiInverseRequests.this.contextUtil.getDefaultExecutor());
            }
        });
    }

    @Override // api.requests.com.neusoft.common.commonsysapiinverse.ICommonSysApiInverseRequests
    public CompletableFuture<ResRequestRestoreFactorySetting> requestRequestRestoreFactorySetting(ReqRequestRestoreFactorySetting reqRequestRestoreFactorySetting) {
        if (reqRequestRestoreFactorySetting.getResponse() == null) {
            reqRequestRestoreFactorySetting.setResponse(new ResRequestRestoreFactorySetting());
        }
        String str = this.component;
        if (str != null) {
            reqRequestRestoreFactorySetting.setComponent(str);
            reqRequestRestoreFactorySetting.getResponse().setComponent(this.component);
        }
        return Communication.request(this.contextUtil.createFromClass(reqRequestRestoreFactorySetting));
    }

    @Override // api.requests.com.neusoft.common.commonsysapiinverse.ICommonSysApiInverseRequests
    public CompletableFuture<ResRequestRestoreFactorySetting> requestRequestRestoreFactorySetting(ReqRequestRestoreFactorySetting reqRequestRestoreFactorySetting, final Duration duration) {
        return requestRequestRestoreFactorySetting(reqRequestRestoreFactorySetting).thenCompose((Function<? super ResRequestRestoreFactorySetting, ? extends CompletionStage<U>>) new Function<ResRequestRestoreFactorySetting, CompletableFuture<ResRequestRestoreFactorySetting>>() { // from class: api.requests.com.neusoft.common.commonsysapiinverse.CommonSysApiInverseRequests.99
            @Override // java9.util.function.Function
            public CompletableFuture<ResRequestRestoreFactorySetting> apply(ResRequestRestoreFactorySetting resRequestRestoreFactorySetting) {
                return CommonUtil.delayAsync(resRequestRestoreFactorySetting, duration, CommonSysApiInverseRequests.this.contextUtil.getDefaultExecutor());
            }
        });
    }

    @Override // api.requests.com.neusoft.common.commonsysapiinverse.ICommonSysApiInverseRequests
    public CompletableFuture<ResRequestRestoreFactorySetting> requestRequestRestoreFactorySetting() {
        return requestRequestRestoreFactorySetting(new ReqRequestRestoreFactorySetting());
    }

    @Override // api.requests.com.neusoft.common.commonsysapiinverse.ICommonSysApiInverseRequests
    public CompletableFuture<ResRequestRestoreFactorySetting> requestRequestRestoreFactorySetting(Duration duration) {
        return requestRequestRestoreFactorySetting(new ReqRequestRestoreFactorySetting(), duration);
    }

    @Override // api.requests.com.neusoft.common.commonsysapiinverse.ICommonSysApiInverseRequests
    public CompletableFuture<Void> fireRequestRestoreFactorySetting(ReqRequestRestoreFactorySetting reqRequestRestoreFactorySetting) {
        reqRequestRestoreFactorySetting.setResponse(null);
        String str = this.component;
        if (str != null) {
            reqRequestRestoreFactorySetting.setComponent(str);
        }
        return Communication.request(this.contextUtil.createFromClass(reqRequestRestoreFactorySetting)).thenCompose(new Function<Object, CompletableFuture<Void>>() { // from class: api.requests.com.neusoft.common.commonsysapiinverse.CommonSysApiInverseRequests.100
            @Override // java9.util.function.Function
            public CompletableFuture<Void> apply(Object obj) {
                return CommonSysApiInverseRequests.completedVoidFuture;
            }
        });
    }

    @Override // api.requests.com.neusoft.common.commonsysapiinverse.ICommonSysApiInverseRequests
    public CompletableFuture<Void> fireRequestRestoreFactorySetting(ReqRequestRestoreFactorySetting reqRequestRestoreFactorySetting, final Duration duration) {
        return fireRequestRestoreFactorySetting(reqRequestRestoreFactorySetting).thenCompose((Function<? super Void, ? extends CompletionStage<U>>) new Function<Void, CompletableFuture<Void>>() { // from class: api.requests.com.neusoft.common.commonsysapiinverse.CommonSysApiInverseRequests.101
            @Override // java9.util.function.Function
            public CompletableFuture<Void> apply(Void r2) {
                return CommonUtil.asyncSleep(duration, CommonSysApiInverseRequests.this.contextUtil.getDefaultExecutor());
            }
        });
    }

    @Override // api.requests.com.neusoft.common.commonsysapiinverse.ICommonSysApiInverseRequests
    public CompletableFuture<Void> fireRequestRestoreFactorySetting() {
        return fireRequestRestoreFactorySetting(new ReqRequestRestoreFactorySetting());
    }

    @Override // api.requests.com.neusoft.common.commonsysapiinverse.ICommonSysApiInverseRequests
    public CompletableFuture<Void> fireRequestRestoreFactorySetting(final Duration duration) {
        return fireRequestRestoreFactorySetting(new ReqRequestRestoreFactorySetting()).thenCompose((Function<? super Void, ? extends CompletionStage<U>>) new Function<Void, CompletableFuture<Void>>() { // from class: api.requests.com.neusoft.common.commonsysapiinverse.CommonSysApiInverseRequests.102
            @Override // java9.util.function.Function
            public CompletableFuture<Void> apply(Void r2) {
                return CommonUtil.asyncSleep(duration, CommonSysApiInverseRequests.this.contextUtil.getDefaultExecutor());
            }
        });
    }

    @Override // api.requests.com.neusoft.common.commonsysapiinverse.ICommonSysApiInverseRequests
    public CompletableFuture<ResSetEnduranceMileage> requestSetEnduranceMileage(ReqSetEnduranceMileage reqSetEnduranceMileage) {
        if (reqSetEnduranceMileage.getResponse() == null) {
            reqSetEnduranceMileage.setResponse(new ResSetEnduranceMileage());
        }
        String str = this.component;
        if (str != null) {
            reqSetEnduranceMileage.setComponent(str);
            reqSetEnduranceMileage.getResponse().setComponent(this.component);
        }
        return Communication.request(this.contextUtil.createFromClass(reqSetEnduranceMileage));
    }

    @Override // api.requests.com.neusoft.common.commonsysapiinverse.ICommonSysApiInverseRequests
    public CompletableFuture<ResSetEnduranceMileage> requestSetEnduranceMileage(ReqSetEnduranceMileage reqSetEnduranceMileage, final Duration duration) {
        return requestSetEnduranceMileage(reqSetEnduranceMileage).thenCompose((Function<? super ResSetEnduranceMileage, ? extends CompletionStage<U>>) new Function<ResSetEnduranceMileage, CompletableFuture<ResSetEnduranceMileage>>() { // from class: api.requests.com.neusoft.common.commonsysapiinverse.CommonSysApiInverseRequests.103
            @Override // java9.util.function.Function
            public CompletableFuture<ResSetEnduranceMileage> apply(ResSetEnduranceMileage resSetEnduranceMileage) {
                return CommonUtil.delayAsync(resSetEnduranceMileage, duration, CommonSysApiInverseRequests.this.contextUtil.getDefaultExecutor());
            }
        });
    }

    @Override // api.requests.com.neusoft.common.commonsysapiinverse.ICommonSysApiInverseRequests
    public CompletableFuture<ResSetEnduranceMileage.Arguments> requestSetEnduranceMileage(ReqSetEnduranceMileage.Arguments arguments) {
        ReqSetEnduranceMileage reqSetEnduranceMileage = new ReqSetEnduranceMileage();
        reqSetEnduranceMileage.setResponse(new ResSetEnduranceMileage());
        reqSetEnduranceMileage.setArguments(arguments);
        return requestSetEnduranceMileage(reqSetEnduranceMileage).thenApply((Function<? super ResSetEnduranceMileage, ? extends U>) new Function<ResSetEnduranceMileage, ResSetEnduranceMileage.Arguments>() { // from class: api.requests.com.neusoft.common.commonsysapiinverse.CommonSysApiInverseRequests.104
            @Override // java9.util.function.Function
            public ResSetEnduranceMileage.Arguments apply(ResSetEnduranceMileage resSetEnduranceMileage) {
                return resSetEnduranceMileage.getArguments();
            }
        });
    }

    @Override // api.requests.com.neusoft.common.commonsysapiinverse.ICommonSysApiInverseRequests
    public CompletableFuture<ResSetEnduranceMileage.Arguments> requestSetEnduranceMileage(ReqSetEnduranceMileage.Arguments arguments, final Duration duration) {
        return requestSetEnduranceMileage(arguments).thenCompose((Function<? super ResSetEnduranceMileage.Arguments, ? extends CompletionStage<U>>) new Function<ResSetEnduranceMileage.Arguments, CompletableFuture<ResSetEnduranceMileage.Arguments>>() { // from class: api.requests.com.neusoft.common.commonsysapiinverse.CommonSysApiInverseRequests.105
            @Override // java9.util.function.Function
            public CompletableFuture<ResSetEnduranceMileage.Arguments> apply(ResSetEnduranceMileage.Arguments arguments2) {
                return CommonUtil.delayAsync(arguments2, duration, CommonSysApiInverseRequests.this.contextUtil.getDefaultExecutor());
            }
        });
    }

    @Override // api.requests.com.neusoft.common.commonsysapiinverse.ICommonSysApiInverseRequests
    public CompletableFuture<Void> fireSetEnduranceMileage(ReqSetEnduranceMileage reqSetEnduranceMileage) {
        reqSetEnduranceMileage.setResponse(null);
        String str = this.component;
        if (str != null) {
            reqSetEnduranceMileage.setComponent(str);
        }
        return Communication.request(this.contextUtil.createFromClass(reqSetEnduranceMileage)).thenCompose(new Function<Object, CompletableFuture<Void>>() { // from class: api.requests.com.neusoft.common.commonsysapiinverse.CommonSysApiInverseRequests.106
            @Override // java9.util.function.Function
            public CompletableFuture<Void> apply(Object obj) {
                return CommonSysApiInverseRequests.completedVoidFuture;
            }
        });
    }

    @Override // api.requests.com.neusoft.common.commonsysapiinverse.ICommonSysApiInverseRequests
    public CompletableFuture<Void> fireSetEnduranceMileage(ReqSetEnduranceMileage reqSetEnduranceMileage, final Duration duration) {
        return fireSetEnduranceMileage(reqSetEnduranceMileage).thenCompose((Function<? super Void, ? extends CompletionStage<U>>) new Function<Void, CompletableFuture<Void>>() { // from class: api.requests.com.neusoft.common.commonsysapiinverse.CommonSysApiInverseRequests.107
            @Override // java9.util.function.Function
            public CompletableFuture<Void> apply(Void r2) {
                return CommonUtil.asyncSleep(duration, CommonSysApiInverseRequests.this.contextUtil.getDefaultExecutor());
            }
        });
    }

    @Override // api.requests.com.neusoft.common.commonsysapiinverse.ICommonSysApiInverseRequests
    public CompletableFuture<Void> fireSetEnduranceMileage(ReqSetEnduranceMileage.Arguments arguments) {
        ReqSetEnduranceMileage reqSetEnduranceMileage = new ReqSetEnduranceMileage();
        reqSetEnduranceMileage.setArguments(arguments);
        reqSetEnduranceMileage.setResponse(null);
        String str = this.component;
        if (str != null) {
            reqSetEnduranceMileage.setComponent(str);
        }
        return fireSetEnduranceMileage(reqSetEnduranceMileage);
    }

    @Override // api.requests.com.neusoft.common.commonsysapiinverse.ICommonSysApiInverseRequests
    public CompletableFuture<Void> fireSetEnduranceMileage(ReqSetEnduranceMileage.Arguments arguments, final Duration duration) {
        return fireSetEnduranceMileage(arguments).thenCompose((Function<? super Void, ? extends CompletionStage<U>>) new Function<Void, CompletableFuture<Void>>() { // from class: api.requests.com.neusoft.common.commonsysapiinverse.CommonSysApiInverseRequests.108
            @Override // java9.util.function.Function
            public CompletableFuture<Void> apply(Void r2) {
                return CommonUtil.asyncSleep(duration, CommonSysApiInverseRequests.this.contextUtil.getDefaultExecutor());
            }
        });
    }

    @Override // api.requests.com.neusoft.common.commonsysapiinverse.ICommonSysApiInverseRequests
    public CompletableFuture<ResSetRemainingElectricQuantityKWH> requestSetRemainingElectricQuantityKWH(ReqSetRemainingElectricQuantityKWH reqSetRemainingElectricQuantityKWH) {
        if (reqSetRemainingElectricQuantityKWH.getResponse() == null) {
            reqSetRemainingElectricQuantityKWH.setResponse(new ResSetRemainingElectricQuantityKWH());
        }
        String str = this.component;
        if (str != null) {
            reqSetRemainingElectricQuantityKWH.setComponent(str);
            reqSetRemainingElectricQuantityKWH.getResponse().setComponent(this.component);
        }
        return Communication.request(this.contextUtil.createFromClass(reqSetRemainingElectricQuantityKWH));
    }

    @Override // api.requests.com.neusoft.common.commonsysapiinverse.ICommonSysApiInverseRequests
    public CompletableFuture<ResSetRemainingElectricQuantityKWH> requestSetRemainingElectricQuantityKWH(ReqSetRemainingElectricQuantityKWH reqSetRemainingElectricQuantityKWH, final Duration duration) {
        return requestSetRemainingElectricQuantityKWH(reqSetRemainingElectricQuantityKWH).thenCompose((Function<? super ResSetRemainingElectricQuantityKWH, ? extends CompletionStage<U>>) new Function<ResSetRemainingElectricQuantityKWH, CompletableFuture<ResSetRemainingElectricQuantityKWH>>() { // from class: api.requests.com.neusoft.common.commonsysapiinverse.CommonSysApiInverseRequests.109
            @Override // java9.util.function.Function
            public CompletableFuture<ResSetRemainingElectricQuantityKWH> apply(ResSetRemainingElectricQuantityKWH resSetRemainingElectricQuantityKWH) {
                return CommonUtil.delayAsync(resSetRemainingElectricQuantityKWH, duration, CommonSysApiInverseRequests.this.contextUtil.getDefaultExecutor());
            }
        });
    }

    @Override // api.requests.com.neusoft.common.commonsysapiinverse.ICommonSysApiInverseRequests
    public CompletableFuture<ResSetRemainingElectricQuantityKWH.Arguments> requestSetRemainingElectricQuantityKWH(ReqSetRemainingElectricQuantityKWH.Arguments arguments) {
        ReqSetRemainingElectricQuantityKWH reqSetRemainingElectricQuantityKWH = new ReqSetRemainingElectricQuantityKWH();
        reqSetRemainingElectricQuantityKWH.setResponse(new ResSetRemainingElectricQuantityKWH());
        reqSetRemainingElectricQuantityKWH.setArguments(arguments);
        return requestSetRemainingElectricQuantityKWH(reqSetRemainingElectricQuantityKWH).thenApply((Function<? super ResSetRemainingElectricQuantityKWH, ? extends U>) new Function<ResSetRemainingElectricQuantityKWH, ResSetRemainingElectricQuantityKWH.Arguments>() { // from class: api.requests.com.neusoft.common.commonsysapiinverse.CommonSysApiInverseRequests.110
            @Override // java9.util.function.Function
            public ResSetRemainingElectricQuantityKWH.Arguments apply(ResSetRemainingElectricQuantityKWH resSetRemainingElectricQuantityKWH) {
                return resSetRemainingElectricQuantityKWH.getArguments();
            }
        });
    }

    @Override // api.requests.com.neusoft.common.commonsysapiinverse.ICommonSysApiInverseRequests
    public CompletableFuture<ResSetRemainingElectricQuantityKWH.Arguments> requestSetRemainingElectricQuantityKWH(ReqSetRemainingElectricQuantityKWH.Arguments arguments, final Duration duration) {
        return requestSetRemainingElectricQuantityKWH(arguments).thenCompose((Function<? super ResSetRemainingElectricQuantityKWH.Arguments, ? extends CompletionStage<U>>) new Function<ResSetRemainingElectricQuantityKWH.Arguments, CompletableFuture<ResSetRemainingElectricQuantityKWH.Arguments>>() { // from class: api.requests.com.neusoft.common.commonsysapiinverse.CommonSysApiInverseRequests.111
            @Override // java9.util.function.Function
            public CompletableFuture<ResSetRemainingElectricQuantityKWH.Arguments> apply(ResSetRemainingElectricQuantityKWH.Arguments arguments2) {
                return CommonUtil.delayAsync(arguments2, duration, CommonSysApiInverseRequests.this.contextUtil.getDefaultExecutor());
            }
        });
    }

    @Override // api.requests.com.neusoft.common.commonsysapiinverse.ICommonSysApiInverseRequests
    public CompletableFuture<Void> fireSetRemainingElectricQuantityKWH(ReqSetRemainingElectricQuantityKWH reqSetRemainingElectricQuantityKWH) {
        reqSetRemainingElectricQuantityKWH.setResponse(null);
        String str = this.component;
        if (str != null) {
            reqSetRemainingElectricQuantityKWH.setComponent(str);
        }
        return Communication.request(this.contextUtil.createFromClass(reqSetRemainingElectricQuantityKWH)).thenCompose(new Function<Object, CompletableFuture<Void>>() { // from class: api.requests.com.neusoft.common.commonsysapiinverse.CommonSysApiInverseRequests.112
            @Override // java9.util.function.Function
            public CompletableFuture<Void> apply(Object obj) {
                return CommonSysApiInverseRequests.completedVoidFuture;
            }
        });
    }

    @Override // api.requests.com.neusoft.common.commonsysapiinverse.ICommonSysApiInverseRequests
    public CompletableFuture<Void> fireSetRemainingElectricQuantityKWH(ReqSetRemainingElectricQuantityKWH reqSetRemainingElectricQuantityKWH, final Duration duration) {
        return fireSetRemainingElectricQuantityKWH(reqSetRemainingElectricQuantityKWH).thenCompose((Function<? super Void, ? extends CompletionStage<U>>) new Function<Void, CompletableFuture<Void>>() { // from class: api.requests.com.neusoft.common.commonsysapiinverse.CommonSysApiInverseRequests.113
            @Override // java9.util.function.Function
            public CompletableFuture<Void> apply(Void r2) {
                return CommonUtil.asyncSleep(duration, CommonSysApiInverseRequests.this.contextUtil.getDefaultExecutor());
            }
        });
    }

    @Override // api.requests.com.neusoft.common.commonsysapiinverse.ICommonSysApiInverseRequests
    public CompletableFuture<Void> fireSetRemainingElectricQuantityKWH(ReqSetRemainingElectricQuantityKWH.Arguments arguments) {
        ReqSetRemainingElectricQuantityKWH reqSetRemainingElectricQuantityKWH = new ReqSetRemainingElectricQuantityKWH();
        reqSetRemainingElectricQuantityKWH.setArguments(arguments);
        reqSetRemainingElectricQuantityKWH.setResponse(null);
        String str = this.component;
        if (str != null) {
            reqSetRemainingElectricQuantityKWH.setComponent(str);
        }
        return fireSetRemainingElectricQuantityKWH(reqSetRemainingElectricQuantityKWH);
    }

    @Override // api.requests.com.neusoft.common.commonsysapiinverse.ICommonSysApiInverseRequests
    public CompletableFuture<Void> fireSetRemainingElectricQuantityKWH(ReqSetRemainingElectricQuantityKWH.Arguments arguments, final Duration duration) {
        return fireSetRemainingElectricQuantityKWH(arguments).thenCompose((Function<? super Void, ? extends CompletionStage<U>>) new Function<Void, CompletableFuture<Void>>() { // from class: api.requests.com.neusoft.common.commonsysapiinverse.CommonSysApiInverseRequests.114
            @Override // java9.util.function.Function
            public CompletableFuture<Void> apply(Void r2) {
                return CommonUtil.asyncSleep(duration, CommonSysApiInverseRequests.this.contextUtil.getDefaultExecutor());
            }
        });
    }

    @Override // api.requests.com.neusoft.common.commonsysapiinverse.ICommonSysApiInverseRequests
    public CompletableFuture<Void> fireSetLowBatterySignal(ReqSetLowBatterySignal reqSetLowBatterySignal) {
        reqSetLowBatterySignal.setResponse(null);
        String str = this.component;
        if (str != null) {
            reqSetLowBatterySignal.setComponent(str);
        }
        return Communication.request(this.contextUtil.createFromClass(reqSetLowBatterySignal)).thenCompose(new Function<Object, CompletableFuture<Void>>() { // from class: api.requests.com.neusoft.common.commonsysapiinverse.CommonSysApiInverseRequests.115
            @Override // java9.util.function.Function
            public CompletableFuture<Void> apply(Object obj) {
                return CommonSysApiInverseRequests.completedVoidFuture;
            }
        });
    }

    @Override // api.requests.com.neusoft.common.commonsysapiinverse.ICommonSysApiInverseRequests
    public CompletableFuture<Void> fireSetLowBatterySignal(ReqSetLowBatterySignal reqSetLowBatterySignal, final Duration duration) {
        return fireSetLowBatterySignal(reqSetLowBatterySignal).thenCompose((Function<? super Void, ? extends CompletionStage<U>>) new Function<Void, CompletableFuture<Void>>() { // from class: api.requests.com.neusoft.common.commonsysapiinverse.CommonSysApiInverseRequests.116
            @Override // java9.util.function.Function
            public CompletableFuture<Void> apply(Void r2) {
                return CommonUtil.asyncSleep(duration, CommonSysApiInverseRequests.this.contextUtil.getDefaultExecutor());
            }
        });
    }

    @Override // api.requests.com.neusoft.common.commonsysapiinverse.ICommonSysApiInverseRequests
    public CompletableFuture<Void> fireSetLowBatterySignal(ReqSetLowBatterySignal.Arguments arguments) {
        ReqSetLowBatterySignal reqSetLowBatterySignal = new ReqSetLowBatterySignal();
        reqSetLowBatterySignal.setArguments(arguments);
        reqSetLowBatterySignal.setResponse(null);
        String str = this.component;
        if (str != null) {
            reqSetLowBatterySignal.setComponent(str);
        }
        return fireSetLowBatterySignal(reqSetLowBatterySignal);
    }

    @Override // api.requests.com.neusoft.common.commonsysapiinverse.ICommonSysApiInverseRequests
    public CompletableFuture<Void> fireSetLowBatterySignal(ReqSetLowBatterySignal.Arguments arguments, final Duration duration) {
        return fireSetLowBatterySignal(arguments).thenCompose((Function<? super Void, ? extends CompletionStage<U>>) new Function<Void, CompletableFuture<Void>>() { // from class: api.requests.com.neusoft.common.commonsysapiinverse.CommonSysApiInverseRequests.117
            @Override // java9.util.function.Function
            public CompletableFuture<Void> apply(Void r2) {
                return CommonUtil.asyncSleep(duration, CommonSysApiInverseRequests.this.contextUtil.getDefaultExecutor());
            }
        });
    }

    @Override // api.requests.com.neusoft.common.commonsysapiinverse.ICommonSysApiInverseRequests
    public CompletableFuture<ResSetBatteryCapacityWH> requestSetBatteryCapacityWH(ReqSetBatteryCapacityWH reqSetBatteryCapacityWH) {
        if (reqSetBatteryCapacityWH.getResponse() == null) {
            reqSetBatteryCapacityWH.setResponse(new ResSetBatteryCapacityWH());
        }
        String str = this.component;
        if (str != null) {
            reqSetBatteryCapacityWH.setComponent(str);
            reqSetBatteryCapacityWH.getResponse().setComponent(this.component);
        }
        return Communication.request(this.contextUtil.createFromClass(reqSetBatteryCapacityWH));
    }

    @Override // api.requests.com.neusoft.common.commonsysapiinverse.ICommonSysApiInverseRequests
    public CompletableFuture<ResSetBatteryCapacityWH> requestSetBatteryCapacityWH(ReqSetBatteryCapacityWH reqSetBatteryCapacityWH, final Duration duration) {
        return requestSetBatteryCapacityWH(reqSetBatteryCapacityWH).thenCompose((Function<? super ResSetBatteryCapacityWH, ? extends CompletionStage<U>>) new Function<ResSetBatteryCapacityWH, CompletableFuture<ResSetBatteryCapacityWH>>() { // from class: api.requests.com.neusoft.common.commonsysapiinverse.CommonSysApiInverseRequests.118
            @Override // java9.util.function.Function
            public CompletableFuture<ResSetBatteryCapacityWH> apply(ResSetBatteryCapacityWH resSetBatteryCapacityWH) {
                return CommonUtil.delayAsync(resSetBatteryCapacityWH, duration, CommonSysApiInverseRequests.this.contextUtil.getDefaultExecutor());
            }
        });
    }

    @Override // api.requests.com.neusoft.common.commonsysapiinverse.ICommonSysApiInverseRequests
    public CompletableFuture<ResSetBatteryCapacityWH.Arguments> requestSetBatteryCapacityWH(ReqSetBatteryCapacityWH.Arguments arguments) {
        ReqSetBatteryCapacityWH reqSetBatteryCapacityWH = new ReqSetBatteryCapacityWH();
        reqSetBatteryCapacityWH.setResponse(new ResSetBatteryCapacityWH());
        reqSetBatteryCapacityWH.setArguments(arguments);
        return requestSetBatteryCapacityWH(reqSetBatteryCapacityWH).thenApply((Function<? super ResSetBatteryCapacityWH, ? extends U>) new Function<ResSetBatteryCapacityWH, ResSetBatteryCapacityWH.Arguments>() { // from class: api.requests.com.neusoft.common.commonsysapiinverse.CommonSysApiInverseRequests.119
            @Override // java9.util.function.Function
            public ResSetBatteryCapacityWH.Arguments apply(ResSetBatteryCapacityWH resSetBatteryCapacityWH) {
                return resSetBatteryCapacityWH.getArguments();
            }
        });
    }

    @Override // api.requests.com.neusoft.common.commonsysapiinverse.ICommonSysApiInverseRequests
    public CompletableFuture<ResSetBatteryCapacityWH.Arguments> requestSetBatteryCapacityWH(ReqSetBatteryCapacityWH.Arguments arguments, final Duration duration) {
        return requestSetBatteryCapacityWH(arguments).thenCompose((Function<? super ResSetBatteryCapacityWH.Arguments, ? extends CompletionStage<U>>) new Function<ResSetBatteryCapacityWH.Arguments, CompletableFuture<ResSetBatteryCapacityWH.Arguments>>() { // from class: api.requests.com.neusoft.common.commonsysapiinverse.CommonSysApiInverseRequests.120
            @Override // java9.util.function.Function
            public CompletableFuture<ResSetBatteryCapacityWH.Arguments> apply(ResSetBatteryCapacityWH.Arguments arguments2) {
                return CommonUtil.delayAsync(arguments2, duration, CommonSysApiInverseRequests.this.contextUtil.getDefaultExecutor());
            }
        });
    }

    @Override // api.requests.com.neusoft.common.commonsysapiinverse.ICommonSysApiInverseRequests
    public CompletableFuture<Void> fireSetBatteryCapacityWH(ReqSetBatteryCapacityWH reqSetBatteryCapacityWH) {
        reqSetBatteryCapacityWH.setResponse(null);
        String str = this.component;
        if (str != null) {
            reqSetBatteryCapacityWH.setComponent(str);
        }
        return Communication.request(this.contextUtil.createFromClass(reqSetBatteryCapacityWH)).thenCompose(new Function<Object, CompletableFuture<Void>>() { // from class: api.requests.com.neusoft.common.commonsysapiinverse.CommonSysApiInverseRequests.121
            @Override // java9.util.function.Function
            public CompletableFuture<Void> apply(Object obj) {
                return CommonSysApiInverseRequests.completedVoidFuture;
            }
        });
    }

    @Override // api.requests.com.neusoft.common.commonsysapiinverse.ICommonSysApiInverseRequests
    public CompletableFuture<Void> fireSetBatteryCapacityWH(ReqSetBatteryCapacityWH reqSetBatteryCapacityWH, final Duration duration) {
        return fireSetBatteryCapacityWH(reqSetBatteryCapacityWH).thenCompose((Function<? super Void, ? extends CompletionStage<U>>) new Function<Void, CompletableFuture<Void>>() { // from class: api.requests.com.neusoft.common.commonsysapiinverse.CommonSysApiInverseRequests.122
            @Override // java9.util.function.Function
            public CompletableFuture<Void> apply(Void r2) {
                return CommonUtil.asyncSleep(duration, CommonSysApiInverseRequests.this.contextUtil.getDefaultExecutor());
            }
        });
    }

    @Override // api.requests.com.neusoft.common.commonsysapiinverse.ICommonSysApiInverseRequests
    public CompletableFuture<Void> fireSetBatteryCapacityWH(ReqSetBatteryCapacityWH.Arguments arguments) {
        ReqSetBatteryCapacityWH reqSetBatteryCapacityWH = new ReqSetBatteryCapacityWH();
        reqSetBatteryCapacityWH.setArguments(arguments);
        reqSetBatteryCapacityWH.setResponse(null);
        String str = this.component;
        if (str != null) {
            reqSetBatteryCapacityWH.setComponent(str);
        }
        return fireSetBatteryCapacityWH(reqSetBatteryCapacityWH);
    }

    @Override // api.requests.com.neusoft.common.commonsysapiinverse.ICommonSysApiInverseRequests
    public CompletableFuture<Void> fireSetBatteryCapacityWH(ReqSetBatteryCapacityWH.Arguments arguments, final Duration duration) {
        return fireSetBatteryCapacityWH(arguments).thenCompose((Function<? super Void, ? extends CompletionStage<U>>) new Function<Void, CompletableFuture<Void>>() { // from class: api.requests.com.neusoft.common.commonsysapiinverse.CommonSysApiInverseRequests.123
            @Override // java9.util.function.Function
            public CompletableFuture<Void> apply(Void r2) {
                return CommonUtil.asyncSleep(duration, CommonSysApiInverseRequests.this.contextUtil.getDefaultExecutor());
            }
        });
    }

    @Override // api.requests.com.neusoft.common.commonsysapiinverse.ICommonSysApiInverseRequests
    public CompletableFuture<ResSetChargingPortType> requestSetChargingPortType(ReqSetChargingPortType reqSetChargingPortType) {
        if (reqSetChargingPortType.getResponse() == null) {
            reqSetChargingPortType.setResponse(new ResSetChargingPortType());
        }
        String str = this.component;
        if (str != null) {
            reqSetChargingPortType.setComponent(str);
            reqSetChargingPortType.getResponse().setComponent(this.component);
        }
        return Communication.request(this.contextUtil.createFromClass(reqSetChargingPortType));
    }

    @Override // api.requests.com.neusoft.common.commonsysapiinverse.ICommonSysApiInverseRequests
    public CompletableFuture<ResSetChargingPortType> requestSetChargingPortType(ReqSetChargingPortType reqSetChargingPortType, final Duration duration) {
        return requestSetChargingPortType(reqSetChargingPortType).thenCompose((Function<? super ResSetChargingPortType, ? extends CompletionStage<U>>) new Function<ResSetChargingPortType, CompletableFuture<ResSetChargingPortType>>() { // from class: api.requests.com.neusoft.common.commonsysapiinverse.CommonSysApiInverseRequests.124
            @Override // java9.util.function.Function
            public CompletableFuture<ResSetChargingPortType> apply(ResSetChargingPortType resSetChargingPortType) {
                return CommonUtil.delayAsync(resSetChargingPortType, duration, CommonSysApiInverseRequests.this.contextUtil.getDefaultExecutor());
            }
        });
    }

    @Override // api.requests.com.neusoft.common.commonsysapiinverse.ICommonSysApiInverseRequests
    public CompletableFuture<ResSetChargingPortType.Arguments> requestSetChargingPortType(ReqSetChargingPortType.Arguments arguments) {
        ReqSetChargingPortType reqSetChargingPortType = new ReqSetChargingPortType();
        reqSetChargingPortType.setResponse(new ResSetChargingPortType());
        reqSetChargingPortType.setArguments(arguments);
        return requestSetChargingPortType(reqSetChargingPortType).thenApply((Function<? super ResSetChargingPortType, ? extends U>) new Function<ResSetChargingPortType, ResSetChargingPortType.Arguments>() { // from class: api.requests.com.neusoft.common.commonsysapiinverse.CommonSysApiInverseRequests.125
            @Override // java9.util.function.Function
            public ResSetChargingPortType.Arguments apply(ResSetChargingPortType resSetChargingPortType) {
                return resSetChargingPortType.getArguments();
            }
        });
    }

    @Override // api.requests.com.neusoft.common.commonsysapiinverse.ICommonSysApiInverseRequests
    public CompletableFuture<ResSetChargingPortType.Arguments> requestSetChargingPortType(ReqSetChargingPortType.Arguments arguments, final Duration duration) {
        return requestSetChargingPortType(arguments).thenCompose((Function<? super ResSetChargingPortType.Arguments, ? extends CompletionStage<U>>) new Function<ResSetChargingPortType.Arguments, CompletableFuture<ResSetChargingPortType.Arguments>>() { // from class: api.requests.com.neusoft.common.commonsysapiinverse.CommonSysApiInverseRequests.126
            @Override // java9.util.function.Function
            public CompletableFuture<ResSetChargingPortType.Arguments> apply(ResSetChargingPortType.Arguments arguments2) {
                return CommonUtil.delayAsync(arguments2, duration, CommonSysApiInverseRequests.this.contextUtil.getDefaultExecutor());
            }
        });
    }

    @Override // api.requests.com.neusoft.common.commonsysapiinverse.ICommonSysApiInverseRequests
    public CompletableFuture<Void> fireSetChargingPortType(ReqSetChargingPortType reqSetChargingPortType) {
        reqSetChargingPortType.setResponse(null);
        String str = this.component;
        if (str != null) {
            reqSetChargingPortType.setComponent(str);
        }
        return Communication.request(this.contextUtil.createFromClass(reqSetChargingPortType)).thenCompose(new Function<Object, CompletableFuture<Void>>() { // from class: api.requests.com.neusoft.common.commonsysapiinverse.CommonSysApiInverseRequests.127
            @Override // java9.util.function.Function
            public CompletableFuture<Void> apply(Object obj) {
                return CommonSysApiInverseRequests.completedVoidFuture;
            }
        });
    }

    @Override // api.requests.com.neusoft.common.commonsysapiinverse.ICommonSysApiInverseRequests
    public CompletableFuture<Void> fireSetChargingPortType(ReqSetChargingPortType reqSetChargingPortType, final Duration duration) {
        return fireSetChargingPortType(reqSetChargingPortType).thenCompose((Function<? super Void, ? extends CompletionStage<U>>) new Function<Void, CompletableFuture<Void>>() { // from class: api.requests.com.neusoft.common.commonsysapiinverse.CommonSysApiInverseRequests.128
            @Override // java9.util.function.Function
            public CompletableFuture<Void> apply(Void r2) {
                return CommonUtil.asyncSleep(duration, CommonSysApiInverseRequests.this.contextUtil.getDefaultExecutor());
            }
        });
    }

    @Override // api.requests.com.neusoft.common.commonsysapiinverse.ICommonSysApiInverseRequests
    public CompletableFuture<Void> fireSetChargingPortType(ReqSetChargingPortType.Arguments arguments) {
        ReqSetChargingPortType reqSetChargingPortType = new ReqSetChargingPortType();
        reqSetChargingPortType.setArguments(arguments);
        reqSetChargingPortType.setResponse(null);
        String str = this.component;
        if (str != null) {
            reqSetChargingPortType.setComponent(str);
        }
        return fireSetChargingPortType(reqSetChargingPortType);
    }

    @Override // api.requests.com.neusoft.common.commonsysapiinverse.ICommonSysApiInverseRequests
    public CompletableFuture<Void> fireSetChargingPortType(ReqSetChargingPortType.Arguments arguments, final Duration duration) {
        return fireSetChargingPortType(arguments).thenCompose((Function<? super Void, ? extends CompletionStage<U>>) new Function<Void, CompletableFuture<Void>>() { // from class: api.requests.com.neusoft.common.commonsysapiinverse.CommonSysApiInverseRequests.129
            @Override // java9.util.function.Function
            public CompletableFuture<Void> apply(Void r2) {
                return CommonUtil.asyncSleep(duration, CommonSysApiInverseRequests.this.contextUtil.getDefaultExecutor());
            }
        });
    }

    @Override // api.requests.com.neusoft.common.commonsysapiinverse.ICommonSysApiInverseRequests
    public CompletableFuture<ResSetRemainingElectricQuantity> requestSetRemainingElectricQuantity(ReqSetRemainingElectricQuantity reqSetRemainingElectricQuantity) {
        if (reqSetRemainingElectricQuantity.getResponse() == null) {
            reqSetRemainingElectricQuantity.setResponse(new ResSetRemainingElectricQuantity());
        }
        String str = this.component;
        if (str != null) {
            reqSetRemainingElectricQuantity.setComponent(str);
            reqSetRemainingElectricQuantity.getResponse().setComponent(this.component);
        }
        return Communication.request(this.contextUtil.createFromClass(reqSetRemainingElectricQuantity));
    }

    @Override // api.requests.com.neusoft.common.commonsysapiinverse.ICommonSysApiInverseRequests
    public CompletableFuture<ResSetRemainingElectricQuantity> requestSetRemainingElectricQuantity(ReqSetRemainingElectricQuantity reqSetRemainingElectricQuantity, final Duration duration) {
        return requestSetRemainingElectricQuantity(reqSetRemainingElectricQuantity).thenCompose((Function<? super ResSetRemainingElectricQuantity, ? extends CompletionStage<U>>) new Function<ResSetRemainingElectricQuantity, CompletableFuture<ResSetRemainingElectricQuantity>>() { // from class: api.requests.com.neusoft.common.commonsysapiinverse.CommonSysApiInverseRequests.130
            @Override // java9.util.function.Function
            public CompletableFuture<ResSetRemainingElectricQuantity> apply(ResSetRemainingElectricQuantity resSetRemainingElectricQuantity) {
                return CommonUtil.delayAsync(resSetRemainingElectricQuantity, duration, CommonSysApiInverseRequests.this.contextUtil.getDefaultExecutor());
            }
        });
    }

    @Override // api.requests.com.neusoft.common.commonsysapiinverse.ICommonSysApiInverseRequests
    public CompletableFuture<ResSetRemainingElectricQuantity.Arguments> requestSetRemainingElectricQuantity(ReqSetRemainingElectricQuantity.Arguments arguments) {
        ReqSetRemainingElectricQuantity reqSetRemainingElectricQuantity = new ReqSetRemainingElectricQuantity();
        reqSetRemainingElectricQuantity.setResponse(new ResSetRemainingElectricQuantity());
        reqSetRemainingElectricQuantity.setArguments(arguments);
        return requestSetRemainingElectricQuantity(reqSetRemainingElectricQuantity).thenApply((Function<? super ResSetRemainingElectricQuantity, ? extends U>) new Function<ResSetRemainingElectricQuantity, ResSetRemainingElectricQuantity.Arguments>() { // from class: api.requests.com.neusoft.common.commonsysapiinverse.CommonSysApiInverseRequests.131
            @Override // java9.util.function.Function
            public ResSetRemainingElectricQuantity.Arguments apply(ResSetRemainingElectricQuantity resSetRemainingElectricQuantity) {
                return resSetRemainingElectricQuantity.getArguments();
            }
        });
    }

    @Override // api.requests.com.neusoft.common.commonsysapiinverse.ICommonSysApiInverseRequests
    public CompletableFuture<ResSetRemainingElectricQuantity.Arguments> requestSetRemainingElectricQuantity(ReqSetRemainingElectricQuantity.Arguments arguments, final Duration duration) {
        return requestSetRemainingElectricQuantity(arguments).thenCompose((Function<? super ResSetRemainingElectricQuantity.Arguments, ? extends CompletionStage<U>>) new Function<ResSetRemainingElectricQuantity.Arguments, CompletableFuture<ResSetRemainingElectricQuantity.Arguments>>() { // from class: api.requests.com.neusoft.common.commonsysapiinverse.CommonSysApiInverseRequests.132
            @Override // java9.util.function.Function
            public CompletableFuture<ResSetRemainingElectricQuantity.Arguments> apply(ResSetRemainingElectricQuantity.Arguments arguments2) {
                return CommonUtil.delayAsync(arguments2, duration, CommonSysApiInverseRequests.this.contextUtil.getDefaultExecutor());
            }
        });
    }

    @Override // api.requests.com.neusoft.common.commonsysapiinverse.ICommonSysApiInverseRequests
    public CompletableFuture<Void> fireSetRemainingElectricQuantity(ReqSetRemainingElectricQuantity reqSetRemainingElectricQuantity) {
        reqSetRemainingElectricQuantity.setResponse(null);
        String str = this.component;
        if (str != null) {
            reqSetRemainingElectricQuantity.setComponent(str);
        }
        return Communication.request(this.contextUtil.createFromClass(reqSetRemainingElectricQuantity)).thenCompose(new Function<Object, CompletableFuture<Void>>() { // from class: api.requests.com.neusoft.common.commonsysapiinverse.CommonSysApiInverseRequests.133
            @Override // java9.util.function.Function
            public CompletableFuture<Void> apply(Object obj) {
                return CommonSysApiInverseRequests.completedVoidFuture;
            }
        });
    }

    @Override // api.requests.com.neusoft.common.commonsysapiinverse.ICommonSysApiInverseRequests
    public CompletableFuture<Void> fireSetRemainingElectricQuantity(ReqSetRemainingElectricQuantity reqSetRemainingElectricQuantity, final Duration duration) {
        return fireSetRemainingElectricQuantity(reqSetRemainingElectricQuantity).thenCompose((Function<? super Void, ? extends CompletionStage<U>>) new Function<Void, CompletableFuture<Void>>() { // from class: api.requests.com.neusoft.common.commonsysapiinverse.CommonSysApiInverseRequests.134
            @Override // java9.util.function.Function
            public CompletableFuture<Void> apply(Void r2) {
                return CommonUtil.asyncSleep(duration, CommonSysApiInverseRequests.this.contextUtil.getDefaultExecutor());
            }
        });
    }

    @Override // api.requests.com.neusoft.common.commonsysapiinverse.ICommonSysApiInverseRequests
    public CompletableFuture<Void> fireSetRemainingElectricQuantity(ReqSetRemainingElectricQuantity.Arguments arguments) {
        ReqSetRemainingElectricQuantity reqSetRemainingElectricQuantity = new ReqSetRemainingElectricQuantity();
        reqSetRemainingElectricQuantity.setArguments(arguments);
        reqSetRemainingElectricQuantity.setResponse(null);
        String str = this.component;
        if (str != null) {
            reqSetRemainingElectricQuantity.setComponent(str);
        }
        return fireSetRemainingElectricQuantity(reqSetRemainingElectricQuantity);
    }

    @Override // api.requests.com.neusoft.common.commonsysapiinverse.ICommonSysApiInverseRequests
    public CompletableFuture<Void> fireSetRemainingElectricQuantity(ReqSetRemainingElectricQuantity.Arguments arguments, final Duration duration) {
        return fireSetRemainingElectricQuantity(arguments).thenCompose((Function<? super Void, ? extends CompletionStage<U>>) new Function<Void, CompletableFuture<Void>>() { // from class: api.requests.com.neusoft.common.commonsysapiinverse.CommonSysApiInverseRequests.135
            @Override // java9.util.function.Function
            public CompletableFuture<Void> apply(Void r2) {
                return CommonUtil.asyncSleep(duration, CommonSysApiInverseRequests.this.contextUtil.getDefaultExecutor());
            }
        });
    }

    @Override // api.requests.com.neusoft.common.commonsysapiinverse.ICommonSysApiInverseRequests
    public CompletableFuture<Void> fireSetLowFuelSignal(ReqSetLowFuelSignal reqSetLowFuelSignal) {
        reqSetLowFuelSignal.setResponse(null);
        String str = this.component;
        if (str != null) {
            reqSetLowFuelSignal.setComponent(str);
        }
        return Communication.request(this.contextUtil.createFromClass(reqSetLowFuelSignal)).thenCompose(new Function<Object, CompletableFuture<Void>>() { // from class: api.requests.com.neusoft.common.commonsysapiinverse.CommonSysApiInverseRequests.136
            @Override // java9.util.function.Function
            public CompletableFuture<Void> apply(Object obj) {
                return CommonSysApiInverseRequests.completedVoidFuture;
            }
        });
    }

    @Override // api.requests.com.neusoft.common.commonsysapiinverse.ICommonSysApiInverseRequests
    public CompletableFuture<Void> fireSetLowFuelSignal(ReqSetLowFuelSignal reqSetLowFuelSignal, final Duration duration) {
        return fireSetLowFuelSignal(reqSetLowFuelSignal).thenCompose((Function<? super Void, ? extends CompletionStage<U>>) new Function<Void, CompletableFuture<Void>>() { // from class: api.requests.com.neusoft.common.commonsysapiinverse.CommonSysApiInverseRequests.137
            @Override // java9.util.function.Function
            public CompletableFuture<Void> apply(Void r2) {
                return CommonUtil.asyncSleep(duration, CommonSysApiInverseRequests.this.contextUtil.getDefaultExecutor());
            }
        });
    }

    @Override // api.requests.com.neusoft.common.commonsysapiinverse.ICommonSysApiInverseRequests
    public CompletableFuture<Void> fireSetLowFuelSignal(ReqSetLowFuelSignal.Arguments arguments) {
        ReqSetLowFuelSignal reqSetLowFuelSignal = new ReqSetLowFuelSignal();
        reqSetLowFuelSignal.setArguments(arguments);
        reqSetLowFuelSignal.setResponse(null);
        String str = this.component;
        if (str != null) {
            reqSetLowFuelSignal.setComponent(str);
        }
        return fireSetLowFuelSignal(reqSetLowFuelSignal);
    }

    @Override // api.requests.com.neusoft.common.commonsysapiinverse.ICommonSysApiInverseRequests
    public CompletableFuture<Void> fireSetLowFuelSignal(ReqSetLowFuelSignal.Arguments arguments, final Duration duration) {
        return fireSetLowFuelSignal(arguments).thenCompose((Function<? super Void, ? extends CompletionStage<U>>) new Function<Void, CompletableFuture<Void>>() { // from class: api.requests.com.neusoft.common.commonsysapiinverse.CommonSysApiInverseRequests.138
            @Override // java9.util.function.Function
            public CompletableFuture<Void> apply(Void r2) {
                return CommonUtil.asyncSleep(duration, CommonSysApiInverseRequests.this.contextUtil.getDefaultExecutor());
            }
        });
    }

    @Override // api.requests.com.neusoft.common.commonsysapiinverse.ICommonSysApiInverseRequests
    public CompletableFuture<ResSetCarType> requestSetCarType(ReqSetCarType reqSetCarType) {
        if (reqSetCarType.getResponse() == null) {
            reqSetCarType.setResponse(new ResSetCarType());
        }
        String str = this.component;
        if (str != null) {
            reqSetCarType.setComponent(str);
            reqSetCarType.getResponse().setComponent(this.component);
        }
        return Communication.request(this.contextUtil.createFromClass(reqSetCarType));
    }

    @Override // api.requests.com.neusoft.common.commonsysapiinverse.ICommonSysApiInverseRequests
    public CompletableFuture<ResSetCarType> requestSetCarType(ReqSetCarType reqSetCarType, final Duration duration) {
        return requestSetCarType(reqSetCarType).thenCompose((Function<? super ResSetCarType, ? extends CompletionStage<U>>) new Function<ResSetCarType, CompletableFuture<ResSetCarType>>() { // from class: api.requests.com.neusoft.common.commonsysapiinverse.CommonSysApiInverseRequests.139
            @Override // java9.util.function.Function
            public CompletableFuture<ResSetCarType> apply(ResSetCarType resSetCarType) {
                return CommonUtil.delayAsync(resSetCarType, duration, CommonSysApiInverseRequests.this.contextUtil.getDefaultExecutor());
            }
        });
    }

    @Override // api.requests.com.neusoft.common.commonsysapiinverse.ICommonSysApiInverseRequests
    public CompletableFuture<ResSetCarType.Arguments> requestSetCarType(ReqSetCarType.Arguments arguments) {
        ReqSetCarType reqSetCarType = new ReqSetCarType();
        reqSetCarType.setResponse(new ResSetCarType());
        reqSetCarType.setArguments(arguments);
        return requestSetCarType(reqSetCarType).thenApply((Function<? super ResSetCarType, ? extends U>) new Function<ResSetCarType, ResSetCarType.Arguments>() { // from class: api.requests.com.neusoft.common.commonsysapiinverse.CommonSysApiInverseRequests.140
            @Override // java9.util.function.Function
            public ResSetCarType.Arguments apply(ResSetCarType resSetCarType) {
                return resSetCarType.getArguments();
            }
        });
    }

    @Override // api.requests.com.neusoft.common.commonsysapiinverse.ICommonSysApiInverseRequests
    public CompletableFuture<ResSetCarType.Arguments> requestSetCarType(ReqSetCarType.Arguments arguments, final Duration duration) {
        return requestSetCarType(arguments).thenCompose((Function<? super ResSetCarType.Arguments, ? extends CompletionStage<U>>) new Function<ResSetCarType.Arguments, CompletableFuture<ResSetCarType.Arguments>>() { // from class: api.requests.com.neusoft.common.commonsysapiinverse.CommonSysApiInverseRequests.141
            @Override // java9.util.function.Function
            public CompletableFuture<ResSetCarType.Arguments> apply(ResSetCarType.Arguments arguments2) {
                return CommonUtil.delayAsync(arguments2, duration, CommonSysApiInverseRequests.this.contextUtil.getDefaultExecutor());
            }
        });
    }

    @Override // api.requests.com.neusoft.common.commonsysapiinverse.ICommonSysApiInverseRequests
    public CompletableFuture<Void> fireSetCarType(ReqSetCarType reqSetCarType) {
        reqSetCarType.setResponse(null);
        String str = this.component;
        if (str != null) {
            reqSetCarType.setComponent(str);
        }
        return Communication.request(this.contextUtil.createFromClass(reqSetCarType)).thenCompose(new Function<Object, CompletableFuture<Void>>() { // from class: api.requests.com.neusoft.common.commonsysapiinverse.CommonSysApiInverseRequests.142
            @Override // java9.util.function.Function
            public CompletableFuture<Void> apply(Object obj) {
                return CommonSysApiInverseRequests.completedVoidFuture;
            }
        });
    }

    @Override // api.requests.com.neusoft.common.commonsysapiinverse.ICommonSysApiInverseRequests
    public CompletableFuture<Void> fireSetCarType(ReqSetCarType reqSetCarType, final Duration duration) {
        return fireSetCarType(reqSetCarType).thenCompose((Function<? super Void, ? extends CompletionStage<U>>) new Function<Void, CompletableFuture<Void>>() { // from class: api.requests.com.neusoft.common.commonsysapiinverse.CommonSysApiInverseRequests.143
            @Override // java9.util.function.Function
            public CompletableFuture<Void> apply(Void r2) {
                return CommonUtil.asyncSleep(duration, CommonSysApiInverseRequests.this.contextUtil.getDefaultExecutor());
            }
        });
    }

    @Override // api.requests.com.neusoft.common.commonsysapiinverse.ICommonSysApiInverseRequests
    public CompletableFuture<Void> fireSetCarType(ReqSetCarType.Arguments arguments) {
        ReqSetCarType reqSetCarType = new ReqSetCarType();
        reqSetCarType.setArguments(arguments);
        reqSetCarType.setResponse(null);
        String str = this.component;
        if (str != null) {
            reqSetCarType.setComponent(str);
        }
        return fireSetCarType(reqSetCarType);
    }

    @Override // api.requests.com.neusoft.common.commonsysapiinverse.ICommonSysApiInverseRequests
    public CompletableFuture<Void> fireSetCarType(ReqSetCarType.Arguments arguments, final Duration duration) {
        return fireSetCarType(arguments).thenCompose((Function<? super Void, ? extends CompletionStage<U>>) new Function<Void, CompletableFuture<Void>>() { // from class: api.requests.com.neusoft.common.commonsysapiinverse.CommonSysApiInverseRequests.144
            @Override // java9.util.function.Function
            public CompletableFuture<Void> apply(Void r2) {
                return CommonUtil.asyncSleep(duration, CommonSysApiInverseRequests.this.contextUtil.getDefaultExecutor());
            }
        });
    }

    @Override // api.requests.com.neusoft.common.commonsysapiinverse.ICommonSysApiInverseRequests
    public CompletableFuture<ResSetFuelType> requestSetFuelType(ReqSetFuelType reqSetFuelType) {
        if (reqSetFuelType.getResponse() == null) {
            reqSetFuelType.setResponse(new ResSetFuelType());
        }
        String str = this.component;
        if (str != null) {
            reqSetFuelType.setComponent(str);
            reqSetFuelType.getResponse().setComponent(this.component);
        }
        return Communication.request(this.contextUtil.createFromClass(reqSetFuelType));
    }

    @Override // api.requests.com.neusoft.common.commonsysapiinverse.ICommonSysApiInverseRequests
    public CompletableFuture<ResSetFuelType> requestSetFuelType(ReqSetFuelType reqSetFuelType, final Duration duration) {
        return requestSetFuelType(reqSetFuelType).thenCompose((Function<? super ResSetFuelType, ? extends CompletionStage<U>>) new Function<ResSetFuelType, CompletableFuture<ResSetFuelType>>() { // from class: api.requests.com.neusoft.common.commonsysapiinverse.CommonSysApiInverseRequests.145
            @Override // java9.util.function.Function
            public CompletableFuture<ResSetFuelType> apply(ResSetFuelType resSetFuelType) {
                return CommonUtil.delayAsync(resSetFuelType, duration, CommonSysApiInverseRequests.this.contextUtil.getDefaultExecutor());
            }
        });
    }

    @Override // api.requests.com.neusoft.common.commonsysapiinverse.ICommonSysApiInverseRequests
    public CompletableFuture<ResSetFuelType.Arguments> requestSetFuelType(ReqSetFuelType.Arguments arguments) {
        ReqSetFuelType reqSetFuelType = new ReqSetFuelType();
        reqSetFuelType.setResponse(new ResSetFuelType());
        reqSetFuelType.setArguments(arguments);
        return requestSetFuelType(reqSetFuelType).thenApply((Function<? super ResSetFuelType, ? extends U>) new Function<ResSetFuelType, ResSetFuelType.Arguments>() { // from class: api.requests.com.neusoft.common.commonsysapiinverse.CommonSysApiInverseRequests.146
            @Override // java9.util.function.Function
            public ResSetFuelType.Arguments apply(ResSetFuelType resSetFuelType) {
                return resSetFuelType.getArguments();
            }
        });
    }

    @Override // api.requests.com.neusoft.common.commonsysapiinverse.ICommonSysApiInverseRequests
    public CompletableFuture<ResSetFuelType.Arguments> requestSetFuelType(ReqSetFuelType.Arguments arguments, final Duration duration) {
        return requestSetFuelType(arguments).thenCompose((Function<? super ResSetFuelType.Arguments, ? extends CompletionStage<U>>) new Function<ResSetFuelType.Arguments, CompletableFuture<ResSetFuelType.Arguments>>() { // from class: api.requests.com.neusoft.common.commonsysapiinverse.CommonSysApiInverseRequests.147
            @Override // java9.util.function.Function
            public CompletableFuture<ResSetFuelType.Arguments> apply(ResSetFuelType.Arguments arguments2) {
                return CommonUtil.delayAsync(arguments2, duration, CommonSysApiInverseRequests.this.contextUtil.getDefaultExecutor());
            }
        });
    }

    @Override // api.requests.com.neusoft.common.commonsysapiinverse.ICommonSysApiInverseRequests
    public CompletableFuture<Void> fireSetFuelType(ReqSetFuelType reqSetFuelType) {
        reqSetFuelType.setResponse(null);
        String str = this.component;
        if (str != null) {
            reqSetFuelType.setComponent(str);
        }
        return Communication.request(this.contextUtil.createFromClass(reqSetFuelType)).thenCompose(new Function<Object, CompletableFuture<Void>>() { // from class: api.requests.com.neusoft.common.commonsysapiinverse.CommonSysApiInverseRequests.148
            @Override // java9.util.function.Function
            public CompletableFuture<Void> apply(Object obj) {
                return CommonSysApiInverseRequests.completedVoidFuture;
            }
        });
    }

    @Override // api.requests.com.neusoft.common.commonsysapiinverse.ICommonSysApiInverseRequests
    public CompletableFuture<Void> fireSetFuelType(ReqSetFuelType reqSetFuelType, final Duration duration) {
        return fireSetFuelType(reqSetFuelType).thenCompose((Function<? super Void, ? extends CompletionStage<U>>) new Function<Void, CompletableFuture<Void>>() { // from class: api.requests.com.neusoft.common.commonsysapiinverse.CommonSysApiInverseRequests.149
            @Override // java9.util.function.Function
            public CompletableFuture<Void> apply(Void r2) {
                return CommonUtil.asyncSleep(duration, CommonSysApiInverseRequests.this.contextUtil.getDefaultExecutor());
            }
        });
    }

    @Override // api.requests.com.neusoft.common.commonsysapiinverse.ICommonSysApiInverseRequests
    public CompletableFuture<Void> fireSetFuelType(ReqSetFuelType.Arguments arguments) {
        ReqSetFuelType reqSetFuelType = new ReqSetFuelType();
        reqSetFuelType.setArguments(arguments);
        reqSetFuelType.setResponse(null);
        String str = this.component;
        if (str != null) {
            reqSetFuelType.setComponent(str);
        }
        return fireSetFuelType(reqSetFuelType);
    }

    @Override // api.requests.com.neusoft.common.commonsysapiinverse.ICommonSysApiInverseRequests
    public CompletableFuture<Void> fireSetFuelType(ReqSetFuelType.Arguments arguments, final Duration duration) {
        return fireSetFuelType(arguments).thenCompose((Function<? super Void, ? extends CompletionStage<U>>) new Function<Void, CompletableFuture<Void>>() { // from class: api.requests.com.neusoft.common.commonsysapiinverse.CommonSysApiInverseRequests.150
            @Override // java9.util.function.Function
            public CompletableFuture<Void> apply(Void r2) {
                return CommonUtil.asyncSleep(duration, CommonSysApiInverseRequests.this.contextUtil.getDefaultExecutor());
            }
        });
    }

    @Override // api.requests.com.neusoft.common.commonsysapiinverse.ICommonSysApiInverseRequests
    public CompletableFuture<ResSetRegionalType> requestSetRegionalType(ReqSetRegionalType reqSetRegionalType) {
        if (reqSetRegionalType.getResponse() == null) {
            reqSetRegionalType.setResponse(new ResSetRegionalType());
        }
        String str = this.component;
        if (str != null) {
            reqSetRegionalType.setComponent(str);
            reqSetRegionalType.getResponse().setComponent(this.component);
        }
        return Communication.request(this.contextUtil.createFromClass(reqSetRegionalType));
    }

    @Override // api.requests.com.neusoft.common.commonsysapiinverse.ICommonSysApiInverseRequests
    public CompletableFuture<ResSetRegionalType> requestSetRegionalType(ReqSetRegionalType reqSetRegionalType, final Duration duration) {
        return requestSetRegionalType(reqSetRegionalType).thenCompose((Function<? super ResSetRegionalType, ? extends CompletionStage<U>>) new Function<ResSetRegionalType, CompletableFuture<ResSetRegionalType>>() { // from class: api.requests.com.neusoft.common.commonsysapiinverse.CommonSysApiInverseRequests.151
            @Override // java9.util.function.Function
            public CompletableFuture<ResSetRegionalType> apply(ResSetRegionalType resSetRegionalType) {
                return CommonUtil.delayAsync(resSetRegionalType, duration, CommonSysApiInverseRequests.this.contextUtil.getDefaultExecutor());
            }
        });
    }

    @Override // api.requests.com.neusoft.common.commonsysapiinverse.ICommonSysApiInverseRequests
    public CompletableFuture<ResSetRegionalType.Arguments> requestSetRegionalType(ReqSetRegionalType.Arguments arguments) {
        ReqSetRegionalType reqSetRegionalType = new ReqSetRegionalType();
        reqSetRegionalType.setResponse(new ResSetRegionalType());
        reqSetRegionalType.setArguments(arguments);
        return requestSetRegionalType(reqSetRegionalType).thenApply((Function<? super ResSetRegionalType, ? extends U>) new Function<ResSetRegionalType, ResSetRegionalType.Arguments>() { // from class: api.requests.com.neusoft.common.commonsysapiinverse.CommonSysApiInverseRequests.152
            @Override // java9.util.function.Function
            public ResSetRegionalType.Arguments apply(ResSetRegionalType resSetRegionalType) {
                return resSetRegionalType.getArguments();
            }
        });
    }

    @Override // api.requests.com.neusoft.common.commonsysapiinverse.ICommonSysApiInverseRequests
    public CompletableFuture<ResSetRegionalType.Arguments> requestSetRegionalType(ReqSetRegionalType.Arguments arguments, final Duration duration) {
        return requestSetRegionalType(arguments).thenCompose((Function<? super ResSetRegionalType.Arguments, ? extends CompletionStage<U>>) new Function<ResSetRegionalType.Arguments, CompletableFuture<ResSetRegionalType.Arguments>>() { // from class: api.requests.com.neusoft.common.commonsysapiinverse.CommonSysApiInverseRequests.153
            @Override // java9.util.function.Function
            public CompletableFuture<ResSetRegionalType.Arguments> apply(ResSetRegionalType.Arguments arguments2) {
                return CommonUtil.delayAsync(arguments2, duration, CommonSysApiInverseRequests.this.contextUtil.getDefaultExecutor());
            }
        });
    }

    @Override // api.requests.com.neusoft.common.commonsysapiinverse.ICommonSysApiInverseRequests
    public CompletableFuture<Void> fireSetRegionalType(ReqSetRegionalType reqSetRegionalType) {
        reqSetRegionalType.setResponse(null);
        String str = this.component;
        if (str != null) {
            reqSetRegionalType.setComponent(str);
        }
        return Communication.request(this.contextUtil.createFromClass(reqSetRegionalType)).thenCompose(new Function<Object, CompletableFuture<Void>>() { // from class: api.requests.com.neusoft.common.commonsysapiinverse.CommonSysApiInverseRequests.154
            @Override // java9.util.function.Function
            public CompletableFuture<Void> apply(Object obj) {
                return CommonSysApiInverseRequests.completedVoidFuture;
            }
        });
    }

    @Override // api.requests.com.neusoft.common.commonsysapiinverse.ICommonSysApiInverseRequests
    public CompletableFuture<Void> fireSetRegionalType(ReqSetRegionalType reqSetRegionalType, final Duration duration) {
        return fireSetRegionalType(reqSetRegionalType).thenCompose((Function<? super Void, ? extends CompletionStage<U>>) new Function<Void, CompletableFuture<Void>>() { // from class: api.requests.com.neusoft.common.commonsysapiinverse.CommonSysApiInverseRequests.155
            @Override // java9.util.function.Function
            public CompletableFuture<Void> apply(Void r2) {
                return CommonUtil.asyncSleep(duration, CommonSysApiInverseRequests.this.contextUtil.getDefaultExecutor());
            }
        });
    }

    @Override // api.requests.com.neusoft.common.commonsysapiinverse.ICommonSysApiInverseRequests
    public CompletableFuture<Void> fireSetRegionalType(ReqSetRegionalType.Arguments arguments) {
        ReqSetRegionalType reqSetRegionalType = new ReqSetRegionalType();
        reqSetRegionalType.setArguments(arguments);
        reqSetRegionalType.setResponse(null);
        String str = this.component;
        if (str != null) {
            reqSetRegionalType.setComponent(str);
        }
        return fireSetRegionalType(reqSetRegionalType);
    }

    @Override // api.requests.com.neusoft.common.commonsysapiinverse.ICommonSysApiInverseRequests
    public CompletableFuture<Void> fireSetRegionalType(ReqSetRegionalType.Arguments arguments, final Duration duration) {
        return fireSetRegionalType(arguments).thenCompose((Function<? super Void, ? extends CompletionStage<U>>) new Function<Void, CompletableFuture<Void>>() { // from class: api.requests.com.neusoft.common.commonsysapiinverse.CommonSysApiInverseRequests.156
            @Override // java9.util.function.Function
            public CompletableFuture<Void> apply(Void r2) {
                return CommonUtil.asyncSleep(duration, CommonSysApiInverseRequests.this.contextUtil.getDefaultExecutor());
            }
        });
    }

    @Override // api.requests.com.neusoft.common.commonsysapiinverse.ICommonSysApiInverseRequests
    public CompletableFuture<Void> fireOnStopOneCoreStatus(ReqOnStopOneCoreStatus reqOnStopOneCoreStatus) {
        reqOnStopOneCoreStatus.setResponse(null);
        String str = this.component;
        if (str != null) {
            reqOnStopOneCoreStatus.setComponent(str);
        }
        return Communication.request(this.contextUtil.createFromClass(reqOnStopOneCoreStatus)).thenCompose(new Function<Object, CompletableFuture<Void>>() { // from class: api.requests.com.neusoft.common.commonsysapiinverse.CommonSysApiInverseRequests.157
            @Override // java9.util.function.Function
            public CompletableFuture<Void> apply(Object obj) {
                return CommonSysApiInverseRequests.completedVoidFuture;
            }
        });
    }

    @Override // api.requests.com.neusoft.common.commonsysapiinverse.ICommonSysApiInverseRequests
    public CompletableFuture<Void> fireOnStopOneCoreStatus(ReqOnStopOneCoreStatus reqOnStopOneCoreStatus, final Duration duration) {
        return fireOnStopOneCoreStatus(reqOnStopOneCoreStatus).thenCompose((Function<? super Void, ? extends CompletionStage<U>>) new Function<Void, CompletableFuture<Void>>() { // from class: api.requests.com.neusoft.common.commonsysapiinverse.CommonSysApiInverseRequests.158
            @Override // java9.util.function.Function
            public CompletableFuture<Void> apply(Void r2) {
                return CommonUtil.asyncSleep(duration, CommonSysApiInverseRequests.this.contextUtil.getDefaultExecutor());
            }
        });
    }

    @Override // api.requests.com.neusoft.common.commonsysapiinverse.ICommonSysApiInverseRequests
    public CompletableFuture<Void> fireOnStopOneCoreStatus(ReqOnStopOneCoreStatus.Arguments arguments) {
        ReqOnStopOneCoreStatus reqOnStopOneCoreStatus = new ReqOnStopOneCoreStatus();
        reqOnStopOneCoreStatus.setArguments(arguments);
        reqOnStopOneCoreStatus.setResponse(null);
        String str = this.component;
        if (str != null) {
            reqOnStopOneCoreStatus.setComponent(str);
        }
        return fireOnStopOneCoreStatus(reqOnStopOneCoreStatus);
    }

    @Override // api.requests.com.neusoft.common.commonsysapiinverse.ICommonSysApiInverseRequests
    public CompletableFuture<Void> fireOnStopOneCoreStatus(ReqOnStopOneCoreStatus.Arguments arguments, final Duration duration) {
        return fireOnStopOneCoreStatus(arguments).thenCompose((Function<? super Void, ? extends CompletionStage<U>>) new Function<Void, CompletableFuture<Void>>() { // from class: api.requests.com.neusoft.common.commonsysapiinverse.CommonSysApiInverseRequests.159
            @Override // java9.util.function.Function
            public CompletableFuture<Void> apply(Void r2) {
                return CommonUtil.asyncSleep(duration, CommonSysApiInverseRequests.this.contextUtil.getDefaultExecutor());
            }
        });
    }

    @Override // api.requests.com.neusoft.common.commonsysapiinverse.ICommonSysApiInverseRequests
    public CompletableFuture<Void> fireSetScreenDirection(ReqSetScreenDirection reqSetScreenDirection) {
        reqSetScreenDirection.setResponse(null);
        String str = this.component;
        if (str != null) {
            reqSetScreenDirection.setComponent(str);
        }
        return Communication.request(this.contextUtil.createFromClass(reqSetScreenDirection)).thenCompose(new Function<Object, CompletableFuture<Void>>() { // from class: api.requests.com.neusoft.common.commonsysapiinverse.CommonSysApiInverseRequests.160
            @Override // java9.util.function.Function
            public CompletableFuture<Void> apply(Object obj) {
                return CommonSysApiInverseRequests.completedVoidFuture;
            }
        });
    }

    @Override // api.requests.com.neusoft.common.commonsysapiinverse.ICommonSysApiInverseRequests
    public CompletableFuture<Void> fireSetScreenDirection(ReqSetScreenDirection reqSetScreenDirection, final Duration duration) {
        return fireSetScreenDirection(reqSetScreenDirection).thenCompose((Function<? super Void, ? extends CompletionStage<U>>) new Function<Void, CompletableFuture<Void>>() { // from class: api.requests.com.neusoft.common.commonsysapiinverse.CommonSysApiInverseRequests.161
            @Override // java9.util.function.Function
            public CompletableFuture<Void> apply(Void r2) {
                return CommonUtil.asyncSleep(duration, CommonSysApiInverseRequests.this.contextUtil.getDefaultExecutor());
            }
        });
    }

    @Override // api.requests.com.neusoft.common.commonsysapiinverse.ICommonSysApiInverseRequests
    public CompletableFuture<Void> fireSetScreenDirection(ReqSetScreenDirection.Arguments arguments) {
        ReqSetScreenDirection reqSetScreenDirection = new ReqSetScreenDirection();
        reqSetScreenDirection.setArguments(arguments);
        reqSetScreenDirection.setResponse(null);
        String str = this.component;
        if (str != null) {
            reqSetScreenDirection.setComponent(str);
        }
        return fireSetScreenDirection(reqSetScreenDirection);
    }

    @Override // api.requests.com.neusoft.common.commonsysapiinverse.ICommonSysApiInverseRequests
    public CompletableFuture<Void> fireSetScreenDirection(ReqSetScreenDirection.Arguments arguments, final Duration duration) {
        return fireSetScreenDirection(arguments).thenCompose((Function<? super Void, ? extends CompletionStage<U>>) new Function<Void, CompletableFuture<Void>>() { // from class: api.requests.com.neusoft.common.commonsysapiinverse.CommonSysApiInverseRequests.162
            @Override // java9.util.function.Function
            public CompletableFuture<Void> apply(Void r2) {
                return CommonUtil.asyncSleep(duration, CommonSysApiInverseRequests.this.contextUtil.getDefaultExecutor());
            }
        });
    }

    @Override // api.requests.com.neusoft.common.commonsysapiinverse.ICommonSysApiInverseRequests
    public CompletableFuture<Void> fireSetScreenMode(ReqSetScreenMode reqSetScreenMode) {
        reqSetScreenMode.setResponse(null);
        String str = this.component;
        if (str != null) {
            reqSetScreenMode.setComponent(str);
        }
        return Communication.request(this.contextUtil.createFromClass(reqSetScreenMode)).thenCompose(new Function<Object, CompletableFuture<Void>>() { // from class: api.requests.com.neusoft.common.commonsysapiinverse.CommonSysApiInverseRequests.163
            @Override // java9.util.function.Function
            public CompletableFuture<Void> apply(Object obj) {
                return CommonSysApiInverseRequests.completedVoidFuture;
            }
        });
    }

    @Override // api.requests.com.neusoft.common.commonsysapiinverse.ICommonSysApiInverseRequests
    public CompletableFuture<Void> fireSetScreenMode(ReqSetScreenMode reqSetScreenMode, final Duration duration) {
        return fireSetScreenMode(reqSetScreenMode).thenCompose((Function<? super Void, ? extends CompletionStage<U>>) new Function<Void, CompletableFuture<Void>>() { // from class: api.requests.com.neusoft.common.commonsysapiinverse.CommonSysApiInverseRequests.164
            @Override // java9.util.function.Function
            public CompletableFuture<Void> apply(Void r2) {
                return CommonUtil.asyncSleep(duration, CommonSysApiInverseRequests.this.contextUtil.getDefaultExecutor());
            }
        });
    }

    @Override // api.requests.com.neusoft.common.commonsysapiinverse.ICommonSysApiInverseRequests
    public CompletableFuture<Void> fireSetScreenMode(ReqSetScreenMode.Arguments arguments) {
        ReqSetScreenMode reqSetScreenMode = new ReqSetScreenMode();
        reqSetScreenMode.setArguments(arguments);
        reqSetScreenMode.setResponse(null);
        String str = this.component;
        if (str != null) {
            reqSetScreenMode.setComponent(str);
        }
        return fireSetScreenMode(reqSetScreenMode);
    }

    @Override // api.requests.com.neusoft.common.commonsysapiinverse.ICommonSysApiInverseRequests
    public CompletableFuture<Void> fireSetScreenMode(ReqSetScreenMode.Arguments arguments, final Duration duration) {
        return fireSetScreenMode(arguments).thenCompose((Function<? super Void, ? extends CompletionStage<U>>) new Function<Void, CompletableFuture<Void>>() { // from class: api.requests.com.neusoft.common.commonsysapiinverse.CommonSysApiInverseRequests.165
            @Override // java9.util.function.Function
            public CompletableFuture<Void> apply(Void r2) {
                return CommonUtil.asyncSleep(duration, CommonSysApiInverseRequests.this.contextUtil.getDefaultExecutor());
            }
        });
    }

    @Override // api.requests.com.neusoft.common.commonsysapiinverse.ICommonSysApiInverseRequests
    public CompletableFuture<Void> fireOnExportDirectoryToUDisk(ReqOnExportDirectoryToUDisk reqOnExportDirectoryToUDisk) {
        reqOnExportDirectoryToUDisk.setResponse(null);
        String str = this.component;
        if (str != null) {
            reqOnExportDirectoryToUDisk.setComponent(str);
        }
        return Communication.request(this.contextUtil.createFromClass(reqOnExportDirectoryToUDisk)).thenCompose(new Function<Object, CompletableFuture<Void>>() { // from class: api.requests.com.neusoft.common.commonsysapiinverse.CommonSysApiInverseRequests.166
            @Override // java9.util.function.Function
            public CompletableFuture<Void> apply(Object obj) {
                return CommonSysApiInverseRequests.completedVoidFuture;
            }
        });
    }

    @Override // api.requests.com.neusoft.common.commonsysapiinverse.ICommonSysApiInverseRequests
    public CompletableFuture<Void> fireOnExportDirectoryToUDisk(ReqOnExportDirectoryToUDisk reqOnExportDirectoryToUDisk, final Duration duration) {
        return fireOnExportDirectoryToUDisk(reqOnExportDirectoryToUDisk).thenCompose((Function<? super Void, ? extends CompletionStage<U>>) new Function<Void, CompletableFuture<Void>>() { // from class: api.requests.com.neusoft.common.commonsysapiinverse.CommonSysApiInverseRequests.167
            @Override // java9.util.function.Function
            public CompletableFuture<Void> apply(Void r2) {
                return CommonUtil.asyncSleep(duration, CommonSysApiInverseRequests.this.contextUtil.getDefaultExecutor());
            }
        });
    }

    @Override // api.requests.com.neusoft.common.commonsysapiinverse.ICommonSysApiInverseRequests
    public CompletableFuture<Void> fireOnExportDirectoryToUDisk(ReqOnExportDirectoryToUDisk.Arguments arguments) {
        ReqOnExportDirectoryToUDisk reqOnExportDirectoryToUDisk = new ReqOnExportDirectoryToUDisk();
        reqOnExportDirectoryToUDisk.setArguments(arguments);
        reqOnExportDirectoryToUDisk.setResponse(null);
        String str = this.component;
        if (str != null) {
            reqOnExportDirectoryToUDisk.setComponent(str);
        }
        return fireOnExportDirectoryToUDisk(reqOnExportDirectoryToUDisk);
    }

    @Override // api.requests.com.neusoft.common.commonsysapiinverse.ICommonSysApiInverseRequests
    public CompletableFuture<Void> fireOnExportDirectoryToUDisk(ReqOnExportDirectoryToUDisk.Arguments arguments, final Duration duration) {
        return fireOnExportDirectoryToUDisk(arguments).thenCompose((Function<? super Void, ? extends CompletionStage<U>>) new Function<Void, CompletableFuture<Void>>() { // from class: api.requests.com.neusoft.common.commonsysapiinverse.CommonSysApiInverseRequests.168
            @Override // java9.util.function.Function
            public CompletableFuture<Void> apply(Void r2) {
                return CommonUtil.asyncSleep(duration, CommonSysApiInverseRequests.this.contextUtil.getDefaultExecutor());
            }
        });
    }

    @Override // api.requests.com.neusoft.common.commonsysapiinverse.ICommonSysApiInverseRequests
    public CompletableFuture<ResReqNaviSwitchFullView> requestReqNaviSwitchFullView(ReqReqNaviSwitchFullView reqReqNaviSwitchFullView) {
        if (reqReqNaviSwitchFullView.getResponse() == null) {
            reqReqNaviSwitchFullView.setResponse(new ResReqNaviSwitchFullView());
        }
        String str = this.component;
        if (str != null) {
            reqReqNaviSwitchFullView.setComponent(str);
            reqReqNaviSwitchFullView.getResponse().setComponent(this.component);
        }
        return Communication.request(this.contextUtil.createFromClass(reqReqNaviSwitchFullView));
    }

    @Override // api.requests.com.neusoft.common.commonsysapiinverse.ICommonSysApiInverseRequests
    public CompletableFuture<ResReqNaviSwitchFullView> requestReqNaviSwitchFullView(ReqReqNaviSwitchFullView reqReqNaviSwitchFullView, final Duration duration) {
        return requestReqNaviSwitchFullView(reqReqNaviSwitchFullView).thenCompose((Function<? super ResReqNaviSwitchFullView, ? extends CompletionStage<U>>) new Function<ResReqNaviSwitchFullView, CompletableFuture<ResReqNaviSwitchFullView>>() { // from class: api.requests.com.neusoft.common.commonsysapiinverse.CommonSysApiInverseRequests.169
            @Override // java9.util.function.Function
            public CompletableFuture<ResReqNaviSwitchFullView> apply(ResReqNaviSwitchFullView resReqNaviSwitchFullView) {
                return CommonUtil.delayAsync(resReqNaviSwitchFullView, duration, CommonSysApiInverseRequests.this.contextUtil.getDefaultExecutor());
            }
        });
    }

    @Override // api.requests.com.neusoft.common.commonsysapiinverse.ICommonSysApiInverseRequests
    public CompletableFuture<ResReqNaviSwitchFullView.Arguments> requestReqNaviSwitchFullView(ReqReqNaviSwitchFullView.Arguments arguments) {
        ReqReqNaviSwitchFullView reqReqNaviSwitchFullView = new ReqReqNaviSwitchFullView();
        reqReqNaviSwitchFullView.setResponse(new ResReqNaviSwitchFullView());
        reqReqNaviSwitchFullView.setArguments(arguments);
        return requestReqNaviSwitchFullView(reqReqNaviSwitchFullView).thenApply((Function<? super ResReqNaviSwitchFullView, ? extends U>) new Function<ResReqNaviSwitchFullView, ResReqNaviSwitchFullView.Arguments>() { // from class: api.requests.com.neusoft.common.commonsysapiinverse.CommonSysApiInverseRequests.170
            @Override // java9.util.function.Function
            public ResReqNaviSwitchFullView.Arguments apply(ResReqNaviSwitchFullView resReqNaviSwitchFullView) {
                return resReqNaviSwitchFullView.getArguments();
            }
        });
    }

    @Override // api.requests.com.neusoft.common.commonsysapiinverse.ICommonSysApiInverseRequests
    public CompletableFuture<ResReqNaviSwitchFullView.Arguments> requestReqNaviSwitchFullView(ReqReqNaviSwitchFullView.Arguments arguments, final Duration duration) {
        return requestReqNaviSwitchFullView(arguments).thenCompose((Function<? super ResReqNaviSwitchFullView.Arguments, ? extends CompletionStage<U>>) new Function<ResReqNaviSwitchFullView.Arguments, CompletableFuture<ResReqNaviSwitchFullView.Arguments>>() { // from class: api.requests.com.neusoft.common.commonsysapiinverse.CommonSysApiInverseRequests.171
            @Override // java9.util.function.Function
            public CompletableFuture<ResReqNaviSwitchFullView.Arguments> apply(ResReqNaviSwitchFullView.Arguments arguments2) {
                return CommonUtil.delayAsync(arguments2, duration, CommonSysApiInverseRequests.this.contextUtil.getDefaultExecutor());
            }
        });
    }

    @Override // api.requests.com.neusoft.common.commonsysapiinverse.ICommonSysApiInverseRequests
    public CompletableFuture<Void> fireReqNaviSwitchFullView(ReqReqNaviSwitchFullView reqReqNaviSwitchFullView) {
        reqReqNaviSwitchFullView.setResponse(null);
        String str = this.component;
        if (str != null) {
            reqReqNaviSwitchFullView.setComponent(str);
        }
        return Communication.request(this.contextUtil.createFromClass(reqReqNaviSwitchFullView)).thenCompose(new Function<Object, CompletableFuture<Void>>() { // from class: api.requests.com.neusoft.common.commonsysapiinverse.CommonSysApiInverseRequests.172
            @Override // java9.util.function.Function
            public CompletableFuture<Void> apply(Object obj) {
                return CommonSysApiInverseRequests.completedVoidFuture;
            }
        });
    }

    @Override // api.requests.com.neusoft.common.commonsysapiinverse.ICommonSysApiInverseRequests
    public CompletableFuture<Void> fireReqNaviSwitchFullView(ReqReqNaviSwitchFullView reqReqNaviSwitchFullView, final Duration duration) {
        return fireReqNaviSwitchFullView(reqReqNaviSwitchFullView).thenCompose((Function<? super Void, ? extends CompletionStage<U>>) new Function<Void, CompletableFuture<Void>>() { // from class: api.requests.com.neusoft.common.commonsysapiinverse.CommonSysApiInverseRequests.173
            @Override // java9.util.function.Function
            public CompletableFuture<Void> apply(Void r2) {
                return CommonUtil.asyncSleep(duration, CommonSysApiInverseRequests.this.contextUtil.getDefaultExecutor());
            }
        });
    }

    @Override // api.requests.com.neusoft.common.commonsysapiinverse.ICommonSysApiInverseRequests
    public CompletableFuture<Void> fireReqNaviSwitchFullView(ReqReqNaviSwitchFullView.Arguments arguments) {
        ReqReqNaviSwitchFullView reqReqNaviSwitchFullView = new ReqReqNaviSwitchFullView();
        reqReqNaviSwitchFullView.setArguments(arguments);
        reqReqNaviSwitchFullView.setResponse(null);
        String str = this.component;
        if (str != null) {
            reqReqNaviSwitchFullView.setComponent(str);
        }
        return fireReqNaviSwitchFullView(reqReqNaviSwitchFullView);
    }

    @Override // api.requests.com.neusoft.common.commonsysapiinverse.ICommonSysApiInverseRequests
    public CompletableFuture<Void> fireReqNaviSwitchFullView(ReqReqNaviSwitchFullView.Arguments arguments, final Duration duration) {
        return fireReqNaviSwitchFullView(arguments).thenCompose((Function<? super Void, ? extends CompletionStage<U>>) new Function<Void, CompletableFuture<Void>>() { // from class: api.requests.com.neusoft.common.commonsysapiinverse.CommonSysApiInverseRequests.174
            @Override // java9.util.function.Function
            public CompletableFuture<Void> apply(Void r2) {
                return CommonUtil.asyncSleep(duration, CommonSysApiInverseRequests.this.contextUtil.getDefaultExecutor());
            }
        });
    }

    @Override // api.requests.com.neusoft.common.commonsysapiinverse.ICommonSysApiInverseRequests
    public CompletableFuture<ResGetNaviSwitchFullView> requestGetNaviSwitchFullView(ReqGetNaviSwitchFullView reqGetNaviSwitchFullView) {
        if (reqGetNaviSwitchFullView.getResponse() == null) {
            reqGetNaviSwitchFullView.setResponse(new ResGetNaviSwitchFullView());
        }
        String str = this.component;
        if (str != null) {
            reqGetNaviSwitchFullView.setComponent(str);
            reqGetNaviSwitchFullView.getResponse().setComponent(this.component);
        }
        return Communication.request(this.contextUtil.createFromClass(reqGetNaviSwitchFullView));
    }

    @Override // api.requests.com.neusoft.common.commonsysapiinverse.ICommonSysApiInverseRequests
    public CompletableFuture<ResGetNaviSwitchFullView> requestGetNaviSwitchFullView(ReqGetNaviSwitchFullView reqGetNaviSwitchFullView, final Duration duration) {
        return requestGetNaviSwitchFullView(reqGetNaviSwitchFullView).thenCompose((Function<? super ResGetNaviSwitchFullView, ? extends CompletionStage<U>>) new Function<ResGetNaviSwitchFullView, CompletableFuture<ResGetNaviSwitchFullView>>() { // from class: api.requests.com.neusoft.common.commonsysapiinverse.CommonSysApiInverseRequests.175
            @Override // java9.util.function.Function
            public CompletableFuture<ResGetNaviSwitchFullView> apply(ResGetNaviSwitchFullView resGetNaviSwitchFullView) {
                return CommonUtil.delayAsync(resGetNaviSwitchFullView, duration, CommonSysApiInverseRequests.this.contextUtil.getDefaultExecutor());
            }
        });
    }

    @Override // api.requests.com.neusoft.common.commonsysapiinverse.ICommonSysApiInverseRequests
    public CompletableFuture<ResGetNaviSwitchFullView> requestGetNaviSwitchFullView() {
        return requestGetNaviSwitchFullView(new ReqGetNaviSwitchFullView());
    }

    @Override // api.requests.com.neusoft.common.commonsysapiinverse.ICommonSysApiInverseRequests
    public CompletableFuture<ResGetNaviSwitchFullView> requestGetNaviSwitchFullView(Duration duration) {
        return requestGetNaviSwitchFullView(new ReqGetNaviSwitchFullView(), duration);
    }

    @Override // api.requests.com.neusoft.common.commonsysapiinverse.ICommonSysApiInverseRequests
    public CompletableFuture<Void> fireGetNaviSwitchFullView(ReqGetNaviSwitchFullView reqGetNaviSwitchFullView) {
        reqGetNaviSwitchFullView.setResponse(null);
        String str = this.component;
        if (str != null) {
            reqGetNaviSwitchFullView.setComponent(str);
        }
        return Communication.request(this.contextUtil.createFromClass(reqGetNaviSwitchFullView)).thenCompose(new Function<Object, CompletableFuture<Void>>() { // from class: api.requests.com.neusoft.common.commonsysapiinverse.CommonSysApiInverseRequests.176
            @Override // java9.util.function.Function
            public CompletableFuture<Void> apply(Object obj) {
                return CommonSysApiInverseRequests.completedVoidFuture;
            }
        });
    }

    @Override // api.requests.com.neusoft.common.commonsysapiinverse.ICommonSysApiInverseRequests
    public CompletableFuture<Void> fireGetNaviSwitchFullView(ReqGetNaviSwitchFullView reqGetNaviSwitchFullView, final Duration duration) {
        return fireGetNaviSwitchFullView(reqGetNaviSwitchFullView).thenCompose((Function<? super Void, ? extends CompletionStage<U>>) new Function<Void, CompletableFuture<Void>>() { // from class: api.requests.com.neusoft.common.commonsysapiinverse.CommonSysApiInverseRequests.177
            @Override // java9.util.function.Function
            public CompletableFuture<Void> apply(Void r2) {
                return CommonUtil.asyncSleep(duration, CommonSysApiInverseRequests.this.contextUtil.getDefaultExecutor());
            }
        });
    }

    @Override // api.requests.com.neusoft.common.commonsysapiinverse.ICommonSysApiInverseRequests
    public CompletableFuture<Void> fireGetNaviSwitchFullView() {
        return fireGetNaviSwitchFullView(new ReqGetNaviSwitchFullView());
    }

    @Override // api.requests.com.neusoft.common.commonsysapiinverse.ICommonSysApiInverseRequests
    public CompletableFuture<Void> fireGetNaviSwitchFullView(final Duration duration) {
        return fireGetNaviSwitchFullView(new ReqGetNaviSwitchFullView()).thenCompose((Function<? super Void, ? extends CompletionStage<U>>) new Function<Void, CompletableFuture<Void>>() { // from class: api.requests.com.neusoft.common.commonsysapiinverse.CommonSysApiInverseRequests.178
            @Override // java9.util.function.Function
            public CompletableFuture<Void> apply(Void r2) {
                return CommonUtil.asyncSleep(duration, CommonSysApiInverseRequests.this.contextUtil.getDefaultExecutor());
            }
        });
    }

    @Override // api.requests.com.neusoft.common.commonsysapiinverse.ICommonSysApiInverseRequests
    public CompletableFuture<ResReqNaviMapViewMode> requestReqNaviMapViewMode(ReqReqNaviMapViewMode reqReqNaviMapViewMode) {
        if (reqReqNaviMapViewMode.getResponse() == null) {
            reqReqNaviMapViewMode.setResponse(new ResReqNaviMapViewMode());
        }
        String str = this.component;
        if (str != null) {
            reqReqNaviMapViewMode.setComponent(str);
            reqReqNaviMapViewMode.getResponse().setComponent(this.component);
        }
        return Communication.request(this.contextUtil.createFromClass(reqReqNaviMapViewMode));
    }

    @Override // api.requests.com.neusoft.common.commonsysapiinverse.ICommonSysApiInverseRequests
    public CompletableFuture<ResReqNaviMapViewMode> requestReqNaviMapViewMode(ReqReqNaviMapViewMode reqReqNaviMapViewMode, final Duration duration) {
        return requestReqNaviMapViewMode(reqReqNaviMapViewMode).thenCompose((Function<? super ResReqNaviMapViewMode, ? extends CompletionStage<U>>) new Function<ResReqNaviMapViewMode, CompletableFuture<ResReqNaviMapViewMode>>() { // from class: api.requests.com.neusoft.common.commonsysapiinverse.CommonSysApiInverseRequests.179
            @Override // java9.util.function.Function
            public CompletableFuture<ResReqNaviMapViewMode> apply(ResReqNaviMapViewMode resReqNaviMapViewMode) {
                return CommonUtil.delayAsync(resReqNaviMapViewMode, duration, CommonSysApiInverseRequests.this.contextUtil.getDefaultExecutor());
            }
        });
    }

    @Override // api.requests.com.neusoft.common.commonsysapiinverse.ICommonSysApiInverseRequests
    public CompletableFuture<ResReqNaviMapViewMode.Arguments> requestReqNaviMapViewMode(ReqReqNaviMapViewMode.Arguments arguments) {
        ReqReqNaviMapViewMode reqReqNaviMapViewMode = new ReqReqNaviMapViewMode();
        reqReqNaviMapViewMode.setResponse(new ResReqNaviMapViewMode());
        reqReqNaviMapViewMode.setArguments(arguments);
        return requestReqNaviMapViewMode(reqReqNaviMapViewMode).thenApply((Function<? super ResReqNaviMapViewMode, ? extends U>) new Function<ResReqNaviMapViewMode, ResReqNaviMapViewMode.Arguments>() { // from class: api.requests.com.neusoft.common.commonsysapiinverse.CommonSysApiInverseRequests.180
            @Override // java9.util.function.Function
            public ResReqNaviMapViewMode.Arguments apply(ResReqNaviMapViewMode resReqNaviMapViewMode) {
                return resReqNaviMapViewMode.getArguments();
            }
        });
    }

    @Override // api.requests.com.neusoft.common.commonsysapiinverse.ICommonSysApiInverseRequests
    public CompletableFuture<ResReqNaviMapViewMode.Arguments> requestReqNaviMapViewMode(ReqReqNaviMapViewMode.Arguments arguments, final Duration duration) {
        return requestReqNaviMapViewMode(arguments).thenCompose((Function<? super ResReqNaviMapViewMode.Arguments, ? extends CompletionStage<U>>) new Function<ResReqNaviMapViewMode.Arguments, CompletableFuture<ResReqNaviMapViewMode.Arguments>>() { // from class: api.requests.com.neusoft.common.commonsysapiinverse.CommonSysApiInverseRequests.181
            @Override // java9.util.function.Function
            public CompletableFuture<ResReqNaviMapViewMode.Arguments> apply(ResReqNaviMapViewMode.Arguments arguments2) {
                return CommonUtil.delayAsync(arguments2, duration, CommonSysApiInverseRequests.this.contextUtil.getDefaultExecutor());
            }
        });
    }

    @Override // api.requests.com.neusoft.common.commonsysapiinverse.ICommonSysApiInverseRequests
    public CompletableFuture<Void> fireReqNaviMapViewMode(ReqReqNaviMapViewMode reqReqNaviMapViewMode) {
        reqReqNaviMapViewMode.setResponse(null);
        String str = this.component;
        if (str != null) {
            reqReqNaviMapViewMode.setComponent(str);
        }
        return Communication.request(this.contextUtil.createFromClass(reqReqNaviMapViewMode)).thenCompose(new Function<Object, CompletableFuture<Void>>() { // from class: api.requests.com.neusoft.common.commonsysapiinverse.CommonSysApiInverseRequests.182
            @Override // java9.util.function.Function
            public CompletableFuture<Void> apply(Object obj) {
                return CommonSysApiInverseRequests.completedVoidFuture;
            }
        });
    }

    @Override // api.requests.com.neusoft.common.commonsysapiinverse.ICommonSysApiInverseRequests
    public CompletableFuture<Void> fireReqNaviMapViewMode(ReqReqNaviMapViewMode reqReqNaviMapViewMode, final Duration duration) {
        return fireReqNaviMapViewMode(reqReqNaviMapViewMode).thenCompose((Function<? super Void, ? extends CompletionStage<U>>) new Function<Void, CompletableFuture<Void>>() { // from class: api.requests.com.neusoft.common.commonsysapiinverse.CommonSysApiInverseRequests.183
            @Override // java9.util.function.Function
            public CompletableFuture<Void> apply(Void r2) {
                return CommonUtil.asyncSleep(duration, CommonSysApiInverseRequests.this.contextUtil.getDefaultExecutor());
            }
        });
    }

    @Override // api.requests.com.neusoft.common.commonsysapiinverse.ICommonSysApiInverseRequests
    public CompletableFuture<Void> fireReqNaviMapViewMode(ReqReqNaviMapViewMode.Arguments arguments) {
        ReqReqNaviMapViewMode reqReqNaviMapViewMode = new ReqReqNaviMapViewMode();
        reqReqNaviMapViewMode.setArguments(arguments);
        reqReqNaviMapViewMode.setResponse(null);
        String str = this.component;
        if (str != null) {
            reqReqNaviMapViewMode.setComponent(str);
        }
        return fireReqNaviMapViewMode(reqReqNaviMapViewMode);
    }

    @Override // api.requests.com.neusoft.common.commonsysapiinverse.ICommonSysApiInverseRequests
    public CompletableFuture<Void> fireReqNaviMapViewMode(ReqReqNaviMapViewMode.Arguments arguments, final Duration duration) {
        return fireReqNaviMapViewMode(arguments).thenCompose((Function<? super Void, ? extends CompletionStage<U>>) new Function<Void, CompletableFuture<Void>>() { // from class: api.requests.com.neusoft.common.commonsysapiinverse.CommonSysApiInverseRequests.184
            @Override // java9.util.function.Function
            public CompletableFuture<Void> apply(Void r2) {
                return CommonUtil.asyncSleep(duration, CommonSysApiInverseRequests.this.contextUtil.getDefaultExecutor());
            }
        });
    }

    @Override // api.requests.com.neusoft.common.commonsysapiinverse.ICommonSysApiInverseRequests
    public CompletableFuture<ResGetNaviMapViewMode> requestGetNaviMapViewMode(ReqGetNaviMapViewMode reqGetNaviMapViewMode) {
        if (reqGetNaviMapViewMode.getResponse() == null) {
            reqGetNaviMapViewMode.setResponse(new ResGetNaviMapViewMode());
        }
        String str = this.component;
        if (str != null) {
            reqGetNaviMapViewMode.setComponent(str);
            reqGetNaviMapViewMode.getResponse().setComponent(this.component);
        }
        return Communication.request(this.contextUtil.createFromClass(reqGetNaviMapViewMode));
    }

    @Override // api.requests.com.neusoft.common.commonsysapiinverse.ICommonSysApiInverseRequests
    public CompletableFuture<ResGetNaviMapViewMode> requestGetNaviMapViewMode(ReqGetNaviMapViewMode reqGetNaviMapViewMode, final Duration duration) {
        return requestGetNaviMapViewMode(reqGetNaviMapViewMode).thenCompose((Function<? super ResGetNaviMapViewMode, ? extends CompletionStage<U>>) new Function<ResGetNaviMapViewMode, CompletableFuture<ResGetNaviMapViewMode>>() { // from class: api.requests.com.neusoft.common.commonsysapiinverse.CommonSysApiInverseRequests.185
            @Override // java9.util.function.Function
            public CompletableFuture<ResGetNaviMapViewMode> apply(ResGetNaviMapViewMode resGetNaviMapViewMode) {
                return CommonUtil.delayAsync(resGetNaviMapViewMode, duration, CommonSysApiInverseRequests.this.contextUtil.getDefaultExecutor());
            }
        });
    }

    @Override // api.requests.com.neusoft.common.commonsysapiinverse.ICommonSysApiInverseRequests
    public CompletableFuture<ResGetNaviMapViewMode> requestGetNaviMapViewMode() {
        return requestGetNaviMapViewMode(new ReqGetNaviMapViewMode());
    }

    @Override // api.requests.com.neusoft.common.commonsysapiinverse.ICommonSysApiInverseRequests
    public CompletableFuture<ResGetNaviMapViewMode> requestGetNaviMapViewMode(Duration duration) {
        return requestGetNaviMapViewMode(new ReqGetNaviMapViewMode(), duration);
    }

    @Override // api.requests.com.neusoft.common.commonsysapiinverse.ICommonSysApiInverseRequests
    public CompletableFuture<Void> fireGetNaviMapViewMode(ReqGetNaviMapViewMode reqGetNaviMapViewMode) {
        reqGetNaviMapViewMode.setResponse(null);
        String str = this.component;
        if (str != null) {
            reqGetNaviMapViewMode.setComponent(str);
        }
        return Communication.request(this.contextUtil.createFromClass(reqGetNaviMapViewMode)).thenCompose(new Function<Object, CompletableFuture<Void>>() { // from class: api.requests.com.neusoft.common.commonsysapiinverse.CommonSysApiInverseRequests.186
            @Override // java9.util.function.Function
            public CompletableFuture<Void> apply(Object obj) {
                return CommonSysApiInverseRequests.completedVoidFuture;
            }
        });
    }

    @Override // api.requests.com.neusoft.common.commonsysapiinverse.ICommonSysApiInverseRequests
    public CompletableFuture<Void> fireGetNaviMapViewMode(ReqGetNaviMapViewMode reqGetNaviMapViewMode, final Duration duration) {
        return fireGetNaviMapViewMode(reqGetNaviMapViewMode).thenCompose((Function<? super Void, ? extends CompletionStage<U>>) new Function<Void, CompletableFuture<Void>>() { // from class: api.requests.com.neusoft.common.commonsysapiinverse.CommonSysApiInverseRequests.187
            @Override // java9.util.function.Function
            public CompletableFuture<Void> apply(Void r2) {
                return CommonUtil.asyncSleep(duration, CommonSysApiInverseRequests.this.contextUtil.getDefaultExecutor());
            }
        });
    }

    @Override // api.requests.com.neusoft.common.commonsysapiinverse.ICommonSysApiInverseRequests
    public CompletableFuture<Void> fireGetNaviMapViewMode() {
        return fireGetNaviMapViewMode(new ReqGetNaviMapViewMode());
    }

    @Override // api.requests.com.neusoft.common.commonsysapiinverse.ICommonSysApiInverseRequests
    public CompletableFuture<Void> fireGetNaviMapViewMode(final Duration duration) {
        return fireGetNaviMapViewMode(new ReqGetNaviMapViewMode()).thenCompose((Function<? super Void, ? extends CompletionStage<U>>) new Function<Void, CompletableFuture<Void>>() { // from class: api.requests.com.neusoft.common.commonsysapiinverse.CommonSysApiInverseRequests.188
            @Override // java9.util.function.Function
            public CompletableFuture<Void> apply(Void r2) {
                return CommonUtil.asyncSleep(duration, CommonSysApiInverseRequests.this.contextUtil.getDefaultExecutor());
            }
        });
    }

    @Override // api.requests.com.neusoft.common.commonsysapiinverse.ICommonSysApiInverseRequests
    public CompletableFuture<ResReqNaviZoomIn> requestReqNaviZoomIn(ReqReqNaviZoomIn reqReqNaviZoomIn) {
        if (reqReqNaviZoomIn.getResponse() == null) {
            reqReqNaviZoomIn.setResponse(new ResReqNaviZoomIn());
        }
        String str = this.component;
        if (str != null) {
            reqReqNaviZoomIn.setComponent(str);
            reqReqNaviZoomIn.getResponse().setComponent(this.component);
        }
        return Communication.request(this.contextUtil.createFromClass(reqReqNaviZoomIn));
    }

    @Override // api.requests.com.neusoft.common.commonsysapiinverse.ICommonSysApiInverseRequests
    public CompletableFuture<ResReqNaviZoomIn> requestReqNaviZoomIn(ReqReqNaviZoomIn reqReqNaviZoomIn, final Duration duration) {
        return requestReqNaviZoomIn(reqReqNaviZoomIn).thenCompose((Function<? super ResReqNaviZoomIn, ? extends CompletionStage<U>>) new Function<ResReqNaviZoomIn, CompletableFuture<ResReqNaviZoomIn>>() { // from class: api.requests.com.neusoft.common.commonsysapiinverse.CommonSysApiInverseRequests.189
            @Override // java9.util.function.Function
            public CompletableFuture<ResReqNaviZoomIn> apply(ResReqNaviZoomIn resReqNaviZoomIn) {
                return CommonUtil.delayAsync(resReqNaviZoomIn, duration, CommonSysApiInverseRequests.this.contextUtil.getDefaultExecutor());
            }
        });
    }

    @Override // api.requests.com.neusoft.common.commonsysapiinverse.ICommonSysApiInverseRequests
    public CompletableFuture<ResReqNaviZoomIn.Arguments> requestReqNaviZoomIn(ReqReqNaviZoomIn.Arguments arguments) {
        ReqReqNaviZoomIn reqReqNaviZoomIn = new ReqReqNaviZoomIn();
        reqReqNaviZoomIn.setResponse(new ResReqNaviZoomIn());
        reqReqNaviZoomIn.setArguments(arguments);
        return requestReqNaviZoomIn(reqReqNaviZoomIn).thenApply((Function<? super ResReqNaviZoomIn, ? extends U>) new Function<ResReqNaviZoomIn, ResReqNaviZoomIn.Arguments>() { // from class: api.requests.com.neusoft.common.commonsysapiinverse.CommonSysApiInverseRequests.190
            @Override // java9.util.function.Function
            public ResReqNaviZoomIn.Arguments apply(ResReqNaviZoomIn resReqNaviZoomIn) {
                return resReqNaviZoomIn.getArguments();
            }
        });
    }

    @Override // api.requests.com.neusoft.common.commonsysapiinverse.ICommonSysApiInverseRequests
    public CompletableFuture<ResReqNaviZoomIn.Arguments> requestReqNaviZoomIn(ReqReqNaviZoomIn.Arguments arguments, final Duration duration) {
        return requestReqNaviZoomIn(arguments).thenCompose((Function<? super ResReqNaviZoomIn.Arguments, ? extends CompletionStage<U>>) new Function<ResReqNaviZoomIn.Arguments, CompletableFuture<ResReqNaviZoomIn.Arguments>>() { // from class: api.requests.com.neusoft.common.commonsysapiinverse.CommonSysApiInverseRequests.191
            @Override // java9.util.function.Function
            public CompletableFuture<ResReqNaviZoomIn.Arguments> apply(ResReqNaviZoomIn.Arguments arguments2) {
                return CommonUtil.delayAsync(arguments2, duration, CommonSysApiInverseRequests.this.contextUtil.getDefaultExecutor());
            }
        });
    }

    @Override // api.requests.com.neusoft.common.commonsysapiinverse.ICommonSysApiInverseRequests
    public CompletableFuture<Void> fireReqNaviZoomIn(ReqReqNaviZoomIn reqReqNaviZoomIn) {
        reqReqNaviZoomIn.setResponse(null);
        String str = this.component;
        if (str != null) {
            reqReqNaviZoomIn.setComponent(str);
        }
        return Communication.request(this.contextUtil.createFromClass(reqReqNaviZoomIn)).thenCompose(new Function<Object, CompletableFuture<Void>>() { // from class: api.requests.com.neusoft.common.commonsysapiinverse.CommonSysApiInverseRequests.192
            @Override // java9.util.function.Function
            public CompletableFuture<Void> apply(Object obj) {
                return CommonSysApiInverseRequests.completedVoidFuture;
            }
        });
    }

    @Override // api.requests.com.neusoft.common.commonsysapiinverse.ICommonSysApiInverseRequests
    public CompletableFuture<Void> fireReqNaviZoomIn(ReqReqNaviZoomIn reqReqNaviZoomIn, final Duration duration) {
        return fireReqNaviZoomIn(reqReqNaviZoomIn).thenCompose((Function<? super Void, ? extends CompletionStage<U>>) new Function<Void, CompletableFuture<Void>>() { // from class: api.requests.com.neusoft.common.commonsysapiinverse.CommonSysApiInverseRequests.193
            @Override // java9.util.function.Function
            public CompletableFuture<Void> apply(Void r2) {
                return CommonUtil.asyncSleep(duration, CommonSysApiInverseRequests.this.contextUtil.getDefaultExecutor());
            }
        });
    }

    @Override // api.requests.com.neusoft.common.commonsysapiinverse.ICommonSysApiInverseRequests
    public CompletableFuture<Void> fireReqNaviZoomIn(ReqReqNaviZoomIn.Arguments arguments) {
        ReqReqNaviZoomIn reqReqNaviZoomIn = new ReqReqNaviZoomIn();
        reqReqNaviZoomIn.setArguments(arguments);
        reqReqNaviZoomIn.setResponse(null);
        String str = this.component;
        if (str != null) {
            reqReqNaviZoomIn.setComponent(str);
        }
        return fireReqNaviZoomIn(reqReqNaviZoomIn);
    }

    @Override // api.requests.com.neusoft.common.commonsysapiinverse.ICommonSysApiInverseRequests
    public CompletableFuture<Void> fireReqNaviZoomIn(ReqReqNaviZoomIn.Arguments arguments, final Duration duration) {
        return fireReqNaviZoomIn(arguments).thenCompose((Function<? super Void, ? extends CompletionStage<U>>) new Function<Void, CompletableFuture<Void>>() { // from class: api.requests.com.neusoft.common.commonsysapiinverse.CommonSysApiInverseRequests.194
            @Override // java9.util.function.Function
            public CompletableFuture<Void> apply(Void r2) {
                return CommonUtil.asyncSleep(duration, CommonSysApiInverseRequests.this.contextUtil.getDefaultExecutor());
            }
        });
    }

    @Override // api.requests.com.neusoft.common.commonsysapiinverse.ICommonSysApiInverseRequests
    public CompletableFuture<ResReqNaviZoomOut> requestReqNaviZoomOut(ReqReqNaviZoomOut reqReqNaviZoomOut) {
        if (reqReqNaviZoomOut.getResponse() == null) {
            reqReqNaviZoomOut.setResponse(new ResReqNaviZoomOut());
        }
        String str = this.component;
        if (str != null) {
            reqReqNaviZoomOut.setComponent(str);
            reqReqNaviZoomOut.getResponse().setComponent(this.component);
        }
        return Communication.request(this.contextUtil.createFromClass(reqReqNaviZoomOut));
    }

    @Override // api.requests.com.neusoft.common.commonsysapiinverse.ICommonSysApiInverseRequests
    public CompletableFuture<ResReqNaviZoomOut> requestReqNaviZoomOut(ReqReqNaviZoomOut reqReqNaviZoomOut, final Duration duration) {
        return requestReqNaviZoomOut(reqReqNaviZoomOut).thenCompose((Function<? super ResReqNaviZoomOut, ? extends CompletionStage<U>>) new Function<ResReqNaviZoomOut, CompletableFuture<ResReqNaviZoomOut>>() { // from class: api.requests.com.neusoft.common.commonsysapiinverse.CommonSysApiInverseRequests.195
            @Override // java9.util.function.Function
            public CompletableFuture<ResReqNaviZoomOut> apply(ResReqNaviZoomOut resReqNaviZoomOut) {
                return CommonUtil.delayAsync(resReqNaviZoomOut, duration, CommonSysApiInverseRequests.this.contextUtil.getDefaultExecutor());
            }
        });
    }

    @Override // api.requests.com.neusoft.common.commonsysapiinverse.ICommonSysApiInverseRequests
    public CompletableFuture<ResReqNaviZoomOut.Arguments> requestReqNaviZoomOut(ReqReqNaviZoomOut.Arguments arguments) {
        ReqReqNaviZoomOut reqReqNaviZoomOut = new ReqReqNaviZoomOut();
        reqReqNaviZoomOut.setResponse(new ResReqNaviZoomOut());
        reqReqNaviZoomOut.setArguments(arguments);
        return requestReqNaviZoomOut(reqReqNaviZoomOut).thenApply((Function<? super ResReqNaviZoomOut, ? extends U>) new Function<ResReqNaviZoomOut, ResReqNaviZoomOut.Arguments>() { // from class: api.requests.com.neusoft.common.commonsysapiinverse.CommonSysApiInverseRequests.196
            @Override // java9.util.function.Function
            public ResReqNaviZoomOut.Arguments apply(ResReqNaviZoomOut resReqNaviZoomOut) {
                return resReqNaviZoomOut.getArguments();
            }
        });
    }

    @Override // api.requests.com.neusoft.common.commonsysapiinverse.ICommonSysApiInverseRequests
    public CompletableFuture<ResReqNaviZoomOut.Arguments> requestReqNaviZoomOut(ReqReqNaviZoomOut.Arguments arguments, final Duration duration) {
        return requestReqNaviZoomOut(arguments).thenCompose((Function<? super ResReqNaviZoomOut.Arguments, ? extends CompletionStage<U>>) new Function<ResReqNaviZoomOut.Arguments, CompletableFuture<ResReqNaviZoomOut.Arguments>>() { // from class: api.requests.com.neusoft.common.commonsysapiinverse.CommonSysApiInverseRequests.197
            @Override // java9.util.function.Function
            public CompletableFuture<ResReqNaviZoomOut.Arguments> apply(ResReqNaviZoomOut.Arguments arguments2) {
                return CommonUtil.delayAsync(arguments2, duration, CommonSysApiInverseRequests.this.contextUtil.getDefaultExecutor());
            }
        });
    }

    @Override // api.requests.com.neusoft.common.commonsysapiinverse.ICommonSysApiInverseRequests
    public CompletableFuture<Void> fireReqNaviZoomOut(ReqReqNaviZoomOut reqReqNaviZoomOut) {
        reqReqNaviZoomOut.setResponse(null);
        String str = this.component;
        if (str != null) {
            reqReqNaviZoomOut.setComponent(str);
        }
        return Communication.request(this.contextUtil.createFromClass(reqReqNaviZoomOut)).thenCompose(new Function<Object, CompletableFuture<Void>>() { // from class: api.requests.com.neusoft.common.commonsysapiinverse.CommonSysApiInverseRequests.198
            @Override // java9.util.function.Function
            public CompletableFuture<Void> apply(Object obj) {
                return CommonSysApiInverseRequests.completedVoidFuture;
            }
        });
    }

    @Override // api.requests.com.neusoft.common.commonsysapiinverse.ICommonSysApiInverseRequests
    public CompletableFuture<Void> fireReqNaviZoomOut(ReqReqNaviZoomOut reqReqNaviZoomOut, final Duration duration) {
        return fireReqNaviZoomOut(reqReqNaviZoomOut).thenCompose((Function<? super Void, ? extends CompletionStage<U>>) new Function<Void, CompletableFuture<Void>>() { // from class: api.requests.com.neusoft.common.commonsysapiinverse.CommonSysApiInverseRequests.199
            @Override // java9.util.function.Function
            public CompletableFuture<Void> apply(Void r2) {
                return CommonUtil.asyncSleep(duration, CommonSysApiInverseRequests.this.contextUtil.getDefaultExecutor());
            }
        });
    }

    @Override // api.requests.com.neusoft.common.commonsysapiinverse.ICommonSysApiInverseRequests
    public CompletableFuture<Void> fireReqNaviZoomOut(ReqReqNaviZoomOut.Arguments arguments) {
        ReqReqNaviZoomOut reqReqNaviZoomOut = new ReqReqNaviZoomOut();
        reqReqNaviZoomOut.setArguments(arguments);
        reqReqNaviZoomOut.setResponse(null);
        String str = this.component;
        if (str != null) {
            reqReqNaviZoomOut.setComponent(str);
        }
        return fireReqNaviZoomOut(reqReqNaviZoomOut);
    }

    @Override // api.requests.com.neusoft.common.commonsysapiinverse.ICommonSysApiInverseRequests
    public CompletableFuture<Void> fireReqNaviZoomOut(ReqReqNaviZoomOut.Arguments arguments, final Duration duration) {
        return fireReqNaviZoomOut(arguments).thenCompose((Function<? super Void, ? extends CompletionStage<U>>) new Function<Void, CompletableFuture<Void>>() { // from class: api.requests.com.neusoft.common.commonsysapiinverse.CommonSysApiInverseRequests.200
            @Override // java9.util.function.Function
            public CompletableFuture<Void> apply(Void r2) {
                return CommonUtil.asyncSleep(duration, CommonSysApiInverseRequests.this.contextUtil.getDefaultExecutor());
            }
        });
    }

    @Override // api.requests.com.neusoft.common.commonsysapiinverse.ICommonSysApiInverseRequests
    public CompletableFuture<ResReqSwitchDayNightMode> requestReqSwitchDayNightMode(ReqReqSwitchDayNightMode reqReqSwitchDayNightMode) {
        if (reqReqSwitchDayNightMode.getResponse() == null) {
            reqReqSwitchDayNightMode.setResponse(new ResReqSwitchDayNightMode());
        }
        String str = this.component;
        if (str != null) {
            reqReqSwitchDayNightMode.setComponent(str);
            reqReqSwitchDayNightMode.getResponse().setComponent(this.component);
        }
        return Communication.request(this.contextUtil.createFromClass(reqReqSwitchDayNightMode));
    }

    @Override // api.requests.com.neusoft.common.commonsysapiinverse.ICommonSysApiInverseRequests
    public CompletableFuture<ResReqSwitchDayNightMode> requestReqSwitchDayNightMode(ReqReqSwitchDayNightMode reqReqSwitchDayNightMode, final Duration duration) {
        return requestReqSwitchDayNightMode(reqReqSwitchDayNightMode).thenCompose((Function<? super ResReqSwitchDayNightMode, ? extends CompletionStage<U>>) new Function<ResReqSwitchDayNightMode, CompletableFuture<ResReqSwitchDayNightMode>>() { // from class: api.requests.com.neusoft.common.commonsysapiinverse.CommonSysApiInverseRequests.201
            @Override // java9.util.function.Function
            public CompletableFuture<ResReqSwitchDayNightMode> apply(ResReqSwitchDayNightMode resReqSwitchDayNightMode) {
                return CommonUtil.delayAsync(resReqSwitchDayNightMode, duration, CommonSysApiInverseRequests.this.contextUtil.getDefaultExecutor());
            }
        });
    }

    @Override // api.requests.com.neusoft.common.commonsysapiinverse.ICommonSysApiInverseRequests
    public CompletableFuture<ResReqSwitchDayNightMode.Arguments> requestReqSwitchDayNightMode(ReqReqSwitchDayNightMode.Arguments arguments) {
        ReqReqSwitchDayNightMode reqReqSwitchDayNightMode = new ReqReqSwitchDayNightMode();
        reqReqSwitchDayNightMode.setResponse(new ResReqSwitchDayNightMode());
        reqReqSwitchDayNightMode.setArguments(arguments);
        return requestReqSwitchDayNightMode(reqReqSwitchDayNightMode).thenApply((Function<? super ResReqSwitchDayNightMode, ? extends U>) new Function<ResReqSwitchDayNightMode, ResReqSwitchDayNightMode.Arguments>() { // from class: api.requests.com.neusoft.common.commonsysapiinverse.CommonSysApiInverseRequests.202
            @Override // java9.util.function.Function
            public ResReqSwitchDayNightMode.Arguments apply(ResReqSwitchDayNightMode resReqSwitchDayNightMode) {
                return resReqSwitchDayNightMode.getArguments();
            }
        });
    }

    @Override // api.requests.com.neusoft.common.commonsysapiinverse.ICommonSysApiInverseRequests
    public CompletableFuture<ResReqSwitchDayNightMode.Arguments> requestReqSwitchDayNightMode(ReqReqSwitchDayNightMode.Arguments arguments, final Duration duration) {
        return requestReqSwitchDayNightMode(arguments).thenCompose((Function<? super ResReqSwitchDayNightMode.Arguments, ? extends CompletionStage<U>>) new Function<ResReqSwitchDayNightMode.Arguments, CompletableFuture<ResReqSwitchDayNightMode.Arguments>>() { // from class: api.requests.com.neusoft.common.commonsysapiinverse.CommonSysApiInverseRequests.203
            @Override // java9.util.function.Function
            public CompletableFuture<ResReqSwitchDayNightMode.Arguments> apply(ResReqSwitchDayNightMode.Arguments arguments2) {
                return CommonUtil.delayAsync(arguments2, duration, CommonSysApiInverseRequests.this.contextUtil.getDefaultExecutor());
            }
        });
    }

    @Override // api.requests.com.neusoft.common.commonsysapiinverse.ICommonSysApiInverseRequests
    public CompletableFuture<Void> fireReqSwitchDayNightMode(ReqReqSwitchDayNightMode reqReqSwitchDayNightMode) {
        reqReqSwitchDayNightMode.setResponse(null);
        String str = this.component;
        if (str != null) {
            reqReqSwitchDayNightMode.setComponent(str);
        }
        return Communication.request(this.contextUtil.createFromClass(reqReqSwitchDayNightMode)).thenCompose(new Function<Object, CompletableFuture<Void>>() { // from class: api.requests.com.neusoft.common.commonsysapiinverse.CommonSysApiInverseRequests.204
            @Override // java9.util.function.Function
            public CompletableFuture<Void> apply(Object obj) {
                return CommonSysApiInverseRequests.completedVoidFuture;
            }
        });
    }

    @Override // api.requests.com.neusoft.common.commonsysapiinverse.ICommonSysApiInverseRequests
    public CompletableFuture<Void> fireReqSwitchDayNightMode(ReqReqSwitchDayNightMode reqReqSwitchDayNightMode, final Duration duration) {
        return fireReqSwitchDayNightMode(reqReqSwitchDayNightMode).thenCompose((Function<? super Void, ? extends CompletionStage<U>>) new Function<Void, CompletableFuture<Void>>() { // from class: api.requests.com.neusoft.common.commonsysapiinverse.CommonSysApiInverseRequests.205
            @Override // java9.util.function.Function
            public CompletableFuture<Void> apply(Void r2) {
                return CommonUtil.asyncSleep(duration, CommonSysApiInverseRequests.this.contextUtil.getDefaultExecutor());
            }
        });
    }

    @Override // api.requests.com.neusoft.common.commonsysapiinverse.ICommonSysApiInverseRequests
    public CompletableFuture<Void> fireReqSwitchDayNightMode(ReqReqSwitchDayNightMode.Arguments arguments) {
        ReqReqSwitchDayNightMode reqReqSwitchDayNightMode = new ReqReqSwitchDayNightMode();
        reqReqSwitchDayNightMode.setArguments(arguments);
        reqReqSwitchDayNightMode.setResponse(null);
        String str = this.component;
        if (str != null) {
            reqReqSwitchDayNightMode.setComponent(str);
        }
        return fireReqSwitchDayNightMode(reqReqSwitchDayNightMode);
    }

    @Override // api.requests.com.neusoft.common.commonsysapiinverse.ICommonSysApiInverseRequests
    public CompletableFuture<Void> fireReqSwitchDayNightMode(ReqReqSwitchDayNightMode.Arguments arguments, final Duration duration) {
        return fireReqSwitchDayNightMode(arguments).thenCompose((Function<? super Void, ? extends CompletionStage<U>>) new Function<Void, CompletableFuture<Void>>() { // from class: api.requests.com.neusoft.common.commonsysapiinverse.CommonSysApiInverseRequests.206
            @Override // java9.util.function.Function
            public CompletableFuture<Void> apply(Void r2) {
                return CommonUtil.asyncSleep(duration, CommonSysApiInverseRequests.this.contextUtil.getDefaultExecutor());
            }
        });
    }

    @Override // api.requests.com.neusoft.common.commonsysapiinverse.ICommonSysApiInverseRequests
    public CompletableFuture<ResGetDayNightMode> requestGetDayNightMode(ReqGetDayNightMode reqGetDayNightMode) {
        if (reqGetDayNightMode.getResponse() == null) {
            reqGetDayNightMode.setResponse(new ResGetDayNightMode());
        }
        String str = this.component;
        if (str != null) {
            reqGetDayNightMode.setComponent(str);
            reqGetDayNightMode.getResponse().setComponent(this.component);
        }
        return Communication.request(this.contextUtil.createFromClass(reqGetDayNightMode));
    }

    @Override // api.requests.com.neusoft.common.commonsysapiinverse.ICommonSysApiInverseRequests
    public CompletableFuture<ResGetDayNightMode> requestGetDayNightMode(ReqGetDayNightMode reqGetDayNightMode, final Duration duration) {
        return requestGetDayNightMode(reqGetDayNightMode).thenCompose((Function<? super ResGetDayNightMode, ? extends CompletionStage<U>>) new Function<ResGetDayNightMode, CompletableFuture<ResGetDayNightMode>>() { // from class: api.requests.com.neusoft.common.commonsysapiinverse.CommonSysApiInverseRequests.207
            @Override // java9.util.function.Function
            public CompletableFuture<ResGetDayNightMode> apply(ResGetDayNightMode resGetDayNightMode) {
                return CommonUtil.delayAsync(resGetDayNightMode, duration, CommonSysApiInverseRequests.this.contextUtil.getDefaultExecutor());
            }
        });
    }

    @Override // api.requests.com.neusoft.common.commonsysapiinverse.ICommonSysApiInverseRequests
    public CompletableFuture<ResGetDayNightMode> requestGetDayNightMode() {
        return requestGetDayNightMode(new ReqGetDayNightMode());
    }

    @Override // api.requests.com.neusoft.common.commonsysapiinverse.ICommonSysApiInverseRequests
    public CompletableFuture<ResGetDayNightMode> requestGetDayNightMode(Duration duration) {
        return requestGetDayNightMode(new ReqGetDayNightMode(), duration);
    }

    @Override // api.requests.com.neusoft.common.commonsysapiinverse.ICommonSysApiInverseRequests
    public CompletableFuture<Void> fireGetDayNightMode(ReqGetDayNightMode reqGetDayNightMode) {
        reqGetDayNightMode.setResponse(null);
        String str = this.component;
        if (str != null) {
            reqGetDayNightMode.setComponent(str);
        }
        return Communication.request(this.contextUtil.createFromClass(reqGetDayNightMode)).thenCompose(new Function<Object, CompletableFuture<Void>>() { // from class: api.requests.com.neusoft.common.commonsysapiinverse.CommonSysApiInverseRequests.208
            @Override // java9.util.function.Function
            public CompletableFuture<Void> apply(Object obj) {
                return CommonSysApiInverseRequests.completedVoidFuture;
            }
        });
    }

    @Override // api.requests.com.neusoft.common.commonsysapiinverse.ICommonSysApiInverseRequests
    public CompletableFuture<Void> fireGetDayNightMode(ReqGetDayNightMode reqGetDayNightMode, final Duration duration) {
        return fireGetDayNightMode(reqGetDayNightMode).thenCompose((Function<? super Void, ? extends CompletionStage<U>>) new Function<Void, CompletableFuture<Void>>() { // from class: api.requests.com.neusoft.common.commonsysapiinverse.CommonSysApiInverseRequests.209
            @Override // java9.util.function.Function
            public CompletableFuture<Void> apply(Void r2) {
                return CommonUtil.asyncSleep(duration, CommonSysApiInverseRequests.this.contextUtil.getDefaultExecutor());
            }
        });
    }

    @Override // api.requests.com.neusoft.common.commonsysapiinverse.ICommonSysApiInverseRequests
    public CompletableFuture<Void> fireGetDayNightMode() {
        return fireGetDayNightMode(new ReqGetDayNightMode());
    }

    @Override // api.requests.com.neusoft.common.commonsysapiinverse.ICommonSysApiInverseRequests
    public CompletableFuture<Void> fireGetDayNightMode(final Duration duration) {
        return fireGetDayNightMode(new ReqGetDayNightMode()).thenCompose((Function<? super Void, ? extends CompletionStage<U>>) new Function<Void, CompletableFuture<Void>>() { // from class: api.requests.com.neusoft.common.commonsysapiinverse.CommonSysApiInverseRequests.210
            @Override // java9.util.function.Function
            public CompletableFuture<Void> apply(Void r2) {
                return CommonUtil.asyncSleep(duration, CommonSysApiInverseRequests.this.contextUtil.getDefaultExecutor());
            }
        });
    }

    @Override // api.requests.com.neusoft.common.commonsysapiinverse.ICommonSysApiInverseRequests
    public CompletableFuture<ResReqNaviGuideStart> requestReqNaviGuideStart(ReqReqNaviGuideStart reqReqNaviGuideStart) {
        if (reqReqNaviGuideStart.getResponse() == null) {
            reqReqNaviGuideStart.setResponse(new ResReqNaviGuideStart());
        }
        String str = this.component;
        if (str != null) {
            reqReqNaviGuideStart.setComponent(str);
            reqReqNaviGuideStart.getResponse().setComponent(this.component);
        }
        return Communication.request(this.contextUtil.createFromClass(reqReqNaviGuideStart));
    }

    @Override // api.requests.com.neusoft.common.commonsysapiinverse.ICommonSysApiInverseRequests
    public CompletableFuture<ResReqNaviGuideStart> requestReqNaviGuideStart(ReqReqNaviGuideStart reqReqNaviGuideStart, final Duration duration) {
        return requestReqNaviGuideStart(reqReqNaviGuideStart).thenCompose((Function<? super ResReqNaviGuideStart, ? extends CompletionStage<U>>) new Function<ResReqNaviGuideStart, CompletableFuture<ResReqNaviGuideStart>>() { // from class: api.requests.com.neusoft.common.commonsysapiinverse.CommonSysApiInverseRequests.211
            @Override // java9.util.function.Function
            public CompletableFuture<ResReqNaviGuideStart> apply(ResReqNaviGuideStart resReqNaviGuideStart) {
                return CommonUtil.delayAsync(resReqNaviGuideStart, duration, CommonSysApiInverseRequests.this.contextUtil.getDefaultExecutor());
            }
        });
    }

    @Override // api.requests.com.neusoft.common.commonsysapiinverse.ICommonSysApiInverseRequests
    public CompletableFuture<ResReqNaviGuideStart.Arguments> requestReqNaviGuideStart(ReqReqNaviGuideStart.Arguments arguments) {
        ReqReqNaviGuideStart reqReqNaviGuideStart = new ReqReqNaviGuideStart();
        reqReqNaviGuideStart.setResponse(new ResReqNaviGuideStart());
        reqReqNaviGuideStart.setArguments(arguments);
        return requestReqNaviGuideStart(reqReqNaviGuideStart).thenApply((Function<? super ResReqNaviGuideStart, ? extends U>) new Function<ResReqNaviGuideStart, ResReqNaviGuideStart.Arguments>() { // from class: api.requests.com.neusoft.common.commonsysapiinverse.CommonSysApiInverseRequests.212
            @Override // java9.util.function.Function
            public ResReqNaviGuideStart.Arguments apply(ResReqNaviGuideStart resReqNaviGuideStart) {
                return resReqNaviGuideStart.getArguments();
            }
        });
    }

    @Override // api.requests.com.neusoft.common.commonsysapiinverse.ICommonSysApiInverseRequests
    public CompletableFuture<ResReqNaviGuideStart.Arguments> requestReqNaviGuideStart(ReqReqNaviGuideStart.Arguments arguments, final Duration duration) {
        return requestReqNaviGuideStart(arguments).thenCompose((Function<? super ResReqNaviGuideStart.Arguments, ? extends CompletionStage<U>>) new Function<ResReqNaviGuideStart.Arguments, CompletableFuture<ResReqNaviGuideStart.Arguments>>() { // from class: api.requests.com.neusoft.common.commonsysapiinverse.CommonSysApiInverseRequests.213
            @Override // java9.util.function.Function
            public CompletableFuture<ResReqNaviGuideStart.Arguments> apply(ResReqNaviGuideStart.Arguments arguments2) {
                return CommonUtil.delayAsync(arguments2, duration, CommonSysApiInverseRequests.this.contextUtil.getDefaultExecutor());
            }
        });
    }

    @Override // api.requests.com.neusoft.common.commonsysapiinverse.ICommonSysApiInverseRequests
    public CompletableFuture<Void> fireReqNaviGuideStart(ReqReqNaviGuideStart reqReqNaviGuideStart) {
        reqReqNaviGuideStart.setResponse(null);
        String str = this.component;
        if (str != null) {
            reqReqNaviGuideStart.setComponent(str);
        }
        return Communication.request(this.contextUtil.createFromClass(reqReqNaviGuideStart)).thenCompose(new Function<Object, CompletableFuture<Void>>() { // from class: api.requests.com.neusoft.common.commonsysapiinverse.CommonSysApiInverseRequests.214
            @Override // java9.util.function.Function
            public CompletableFuture<Void> apply(Object obj) {
                return CommonSysApiInverseRequests.completedVoidFuture;
            }
        });
    }

    @Override // api.requests.com.neusoft.common.commonsysapiinverse.ICommonSysApiInverseRequests
    public CompletableFuture<Void> fireReqNaviGuideStart(ReqReqNaviGuideStart reqReqNaviGuideStart, final Duration duration) {
        return fireReqNaviGuideStart(reqReqNaviGuideStart).thenCompose((Function<? super Void, ? extends CompletionStage<U>>) new Function<Void, CompletableFuture<Void>>() { // from class: api.requests.com.neusoft.common.commonsysapiinverse.CommonSysApiInverseRequests.215
            @Override // java9.util.function.Function
            public CompletableFuture<Void> apply(Void r2) {
                return CommonUtil.asyncSleep(duration, CommonSysApiInverseRequests.this.contextUtil.getDefaultExecutor());
            }
        });
    }

    @Override // api.requests.com.neusoft.common.commonsysapiinverse.ICommonSysApiInverseRequests
    public CompletableFuture<Void> fireReqNaviGuideStart(ReqReqNaviGuideStart.Arguments arguments) {
        ReqReqNaviGuideStart reqReqNaviGuideStart = new ReqReqNaviGuideStart();
        reqReqNaviGuideStart.setArguments(arguments);
        reqReqNaviGuideStart.setResponse(null);
        String str = this.component;
        if (str != null) {
            reqReqNaviGuideStart.setComponent(str);
        }
        return fireReqNaviGuideStart(reqReqNaviGuideStart);
    }

    @Override // api.requests.com.neusoft.common.commonsysapiinverse.ICommonSysApiInverseRequests
    public CompletableFuture<Void> fireReqNaviGuideStart(ReqReqNaviGuideStart.Arguments arguments, final Duration duration) {
        return fireReqNaviGuideStart(arguments).thenCompose((Function<? super Void, ? extends CompletionStage<U>>) new Function<Void, CompletableFuture<Void>>() { // from class: api.requests.com.neusoft.common.commonsysapiinverse.CommonSysApiInverseRequests.216
            @Override // java9.util.function.Function
            public CompletableFuture<Void> apply(Void r2) {
                return CommonUtil.asyncSleep(duration, CommonSysApiInverseRequests.this.contextUtil.getDefaultExecutor());
            }
        });
    }

    @Override // api.requests.com.neusoft.common.commonsysapiinverse.ICommonSysApiInverseRequests
    public CompletableFuture<ResReqNaviGuideStop> requestReqNaviGuideStop(ReqReqNaviGuideStop reqReqNaviGuideStop) {
        if (reqReqNaviGuideStop.getResponse() == null) {
            reqReqNaviGuideStop.setResponse(new ResReqNaviGuideStop());
        }
        String str = this.component;
        if (str != null) {
            reqReqNaviGuideStop.setComponent(str);
            reqReqNaviGuideStop.getResponse().setComponent(this.component);
        }
        return Communication.request(this.contextUtil.createFromClass(reqReqNaviGuideStop));
    }

    @Override // api.requests.com.neusoft.common.commonsysapiinverse.ICommonSysApiInverseRequests
    public CompletableFuture<ResReqNaviGuideStop> requestReqNaviGuideStop(ReqReqNaviGuideStop reqReqNaviGuideStop, final Duration duration) {
        return requestReqNaviGuideStop(reqReqNaviGuideStop).thenCompose((Function<? super ResReqNaviGuideStop, ? extends CompletionStage<U>>) new Function<ResReqNaviGuideStop, CompletableFuture<ResReqNaviGuideStop>>() { // from class: api.requests.com.neusoft.common.commonsysapiinverse.CommonSysApiInverseRequests.217
            @Override // java9.util.function.Function
            public CompletableFuture<ResReqNaviGuideStop> apply(ResReqNaviGuideStop resReqNaviGuideStop) {
                return CommonUtil.delayAsync(resReqNaviGuideStop, duration, CommonSysApiInverseRequests.this.contextUtil.getDefaultExecutor());
            }
        });
    }

    @Override // api.requests.com.neusoft.common.commonsysapiinverse.ICommonSysApiInverseRequests
    public CompletableFuture<ResReqNaviGuideStop> requestReqNaviGuideStop() {
        return requestReqNaviGuideStop(new ReqReqNaviGuideStop());
    }

    @Override // api.requests.com.neusoft.common.commonsysapiinverse.ICommonSysApiInverseRequests
    public CompletableFuture<ResReqNaviGuideStop> requestReqNaviGuideStop(Duration duration) {
        return requestReqNaviGuideStop(new ReqReqNaviGuideStop(), duration);
    }

    @Override // api.requests.com.neusoft.common.commonsysapiinverse.ICommonSysApiInverseRequests
    public CompletableFuture<Void> fireReqNaviGuideStop(ReqReqNaviGuideStop reqReqNaviGuideStop) {
        reqReqNaviGuideStop.setResponse(null);
        String str = this.component;
        if (str != null) {
            reqReqNaviGuideStop.setComponent(str);
        }
        return Communication.request(this.contextUtil.createFromClass(reqReqNaviGuideStop)).thenCompose(new Function<Object, CompletableFuture<Void>>() { // from class: api.requests.com.neusoft.common.commonsysapiinverse.CommonSysApiInverseRequests.218
            @Override // java9.util.function.Function
            public CompletableFuture<Void> apply(Object obj) {
                return CommonSysApiInverseRequests.completedVoidFuture;
            }
        });
    }

    @Override // api.requests.com.neusoft.common.commonsysapiinverse.ICommonSysApiInverseRequests
    public CompletableFuture<Void> fireReqNaviGuideStop(ReqReqNaviGuideStop reqReqNaviGuideStop, final Duration duration) {
        return fireReqNaviGuideStop(reqReqNaviGuideStop).thenCompose((Function<? super Void, ? extends CompletionStage<U>>) new Function<Void, CompletableFuture<Void>>() { // from class: api.requests.com.neusoft.common.commonsysapiinverse.CommonSysApiInverseRequests.219
            @Override // java9.util.function.Function
            public CompletableFuture<Void> apply(Void r2) {
                return CommonUtil.asyncSleep(duration, CommonSysApiInverseRequests.this.contextUtil.getDefaultExecutor());
            }
        });
    }

    @Override // api.requests.com.neusoft.common.commonsysapiinverse.ICommonSysApiInverseRequests
    public CompletableFuture<Void> fireReqNaviGuideStop() {
        return fireReqNaviGuideStop(new ReqReqNaviGuideStop());
    }

    @Override // api.requests.com.neusoft.common.commonsysapiinverse.ICommonSysApiInverseRequests
    public CompletableFuture<Void> fireReqNaviGuideStop(final Duration duration) {
        return fireReqNaviGuideStop(new ReqReqNaviGuideStop()).thenCompose((Function<? super Void, ? extends CompletionStage<U>>) new Function<Void, CompletableFuture<Void>>() { // from class: api.requests.com.neusoft.common.commonsysapiinverse.CommonSysApiInverseRequests.220
            @Override // java9.util.function.Function
            public CompletableFuture<Void> apply(Void r2) {
                return CommonUtil.asyncSleep(duration, CommonSysApiInverseRequests.this.contextUtil.getDefaultExecutor());
            }
        });
    }

    @Override // api.requests.com.neusoft.common.commonsysapiinverse.ICommonSysApiInverseRequests
    public CompletableFuture<ResCheckNaviGuide> requestCheckNaviGuide(ReqCheckNaviGuide reqCheckNaviGuide) {
        if (reqCheckNaviGuide.getResponse() == null) {
            reqCheckNaviGuide.setResponse(new ResCheckNaviGuide());
        }
        String str = this.component;
        if (str != null) {
            reqCheckNaviGuide.setComponent(str);
            reqCheckNaviGuide.getResponse().setComponent(this.component);
        }
        return Communication.request(this.contextUtil.createFromClass(reqCheckNaviGuide));
    }

    @Override // api.requests.com.neusoft.common.commonsysapiinverse.ICommonSysApiInverseRequests
    public CompletableFuture<ResCheckNaviGuide> requestCheckNaviGuide(ReqCheckNaviGuide reqCheckNaviGuide, final Duration duration) {
        return requestCheckNaviGuide(reqCheckNaviGuide).thenCompose((Function<? super ResCheckNaviGuide, ? extends CompletionStage<U>>) new Function<ResCheckNaviGuide, CompletableFuture<ResCheckNaviGuide>>() { // from class: api.requests.com.neusoft.common.commonsysapiinverse.CommonSysApiInverseRequests.221
            @Override // java9.util.function.Function
            public CompletableFuture<ResCheckNaviGuide> apply(ResCheckNaviGuide resCheckNaviGuide) {
                return CommonUtil.delayAsync(resCheckNaviGuide, duration, CommonSysApiInverseRequests.this.contextUtil.getDefaultExecutor());
            }
        });
    }

    @Override // api.requests.com.neusoft.common.commonsysapiinverse.ICommonSysApiInverseRequests
    public CompletableFuture<ResCheckNaviGuide> requestCheckNaviGuide() {
        return requestCheckNaviGuide(new ReqCheckNaviGuide());
    }

    @Override // api.requests.com.neusoft.common.commonsysapiinverse.ICommonSysApiInverseRequests
    public CompletableFuture<ResCheckNaviGuide> requestCheckNaviGuide(Duration duration) {
        return requestCheckNaviGuide(new ReqCheckNaviGuide(), duration);
    }

    @Override // api.requests.com.neusoft.common.commonsysapiinverse.ICommonSysApiInverseRequests
    public CompletableFuture<Void> fireCheckNaviGuide(ReqCheckNaviGuide reqCheckNaviGuide) {
        reqCheckNaviGuide.setResponse(null);
        String str = this.component;
        if (str != null) {
            reqCheckNaviGuide.setComponent(str);
        }
        return Communication.request(this.contextUtil.createFromClass(reqCheckNaviGuide)).thenCompose(new Function<Object, CompletableFuture<Void>>() { // from class: api.requests.com.neusoft.common.commonsysapiinverse.CommonSysApiInverseRequests.222
            @Override // java9.util.function.Function
            public CompletableFuture<Void> apply(Object obj) {
                return CommonSysApiInverseRequests.completedVoidFuture;
            }
        });
    }

    @Override // api.requests.com.neusoft.common.commonsysapiinverse.ICommonSysApiInverseRequests
    public CompletableFuture<Void> fireCheckNaviGuide(ReqCheckNaviGuide reqCheckNaviGuide, final Duration duration) {
        return fireCheckNaviGuide(reqCheckNaviGuide).thenCompose((Function<? super Void, ? extends CompletionStage<U>>) new Function<Void, CompletableFuture<Void>>() { // from class: api.requests.com.neusoft.common.commonsysapiinverse.CommonSysApiInverseRequests.223
            @Override // java9.util.function.Function
            public CompletableFuture<Void> apply(Void r2) {
                return CommonUtil.asyncSleep(duration, CommonSysApiInverseRequests.this.contextUtil.getDefaultExecutor());
            }
        });
    }

    @Override // api.requests.com.neusoft.common.commonsysapiinverse.ICommonSysApiInverseRequests
    public CompletableFuture<Void> fireCheckNaviGuide() {
        return fireCheckNaviGuide(new ReqCheckNaviGuide());
    }

    @Override // api.requests.com.neusoft.common.commonsysapiinverse.ICommonSysApiInverseRequests
    public CompletableFuture<Void> fireCheckNaviGuide(final Duration duration) {
        return fireCheckNaviGuide(new ReqCheckNaviGuide()).thenCompose((Function<? super Void, ? extends CompletionStage<U>>) new Function<Void, CompletableFuture<Void>>() { // from class: api.requests.com.neusoft.common.commonsysapiinverse.CommonSysApiInverseRequests.224
            @Override // java9.util.function.Function
            public CompletableFuture<Void> apply(Void r2) {
                return CommonUtil.asyncSleep(duration, CommonSysApiInverseRequests.this.contextUtil.getDefaultExecutor());
            }
        });
    }

    @Override // api.requests.com.neusoft.common.commonsysapiinverse.ICommonSysApiInverseRequests
    public CompletableFuture<ResReqNaviRemainTimeDistance> requestReqNaviRemainTimeDistance(ReqReqNaviRemainTimeDistance reqReqNaviRemainTimeDistance) {
        if (reqReqNaviRemainTimeDistance.getResponse() == null) {
            reqReqNaviRemainTimeDistance.setResponse(new ResReqNaviRemainTimeDistance());
        }
        String str = this.component;
        if (str != null) {
            reqReqNaviRemainTimeDistance.setComponent(str);
            reqReqNaviRemainTimeDistance.getResponse().setComponent(this.component);
        }
        return Communication.request(this.contextUtil.createFromClass(reqReqNaviRemainTimeDistance));
    }

    @Override // api.requests.com.neusoft.common.commonsysapiinverse.ICommonSysApiInverseRequests
    public CompletableFuture<ResReqNaviRemainTimeDistance> requestReqNaviRemainTimeDistance(ReqReqNaviRemainTimeDistance reqReqNaviRemainTimeDistance, final Duration duration) {
        return requestReqNaviRemainTimeDistance(reqReqNaviRemainTimeDistance).thenCompose((Function<? super ResReqNaviRemainTimeDistance, ? extends CompletionStage<U>>) new Function<ResReqNaviRemainTimeDistance, CompletableFuture<ResReqNaviRemainTimeDistance>>() { // from class: api.requests.com.neusoft.common.commonsysapiinverse.CommonSysApiInverseRequests.225
            @Override // java9.util.function.Function
            public CompletableFuture<ResReqNaviRemainTimeDistance> apply(ResReqNaviRemainTimeDistance resReqNaviRemainTimeDistance) {
                return CommonUtil.delayAsync(resReqNaviRemainTimeDistance, duration, CommonSysApiInverseRequests.this.contextUtil.getDefaultExecutor());
            }
        });
    }

    @Override // api.requests.com.neusoft.common.commonsysapiinverse.ICommonSysApiInverseRequests
    public CompletableFuture<ResReqNaviRemainTimeDistance> requestReqNaviRemainTimeDistance() {
        return requestReqNaviRemainTimeDistance(new ReqReqNaviRemainTimeDistance());
    }

    @Override // api.requests.com.neusoft.common.commonsysapiinverse.ICommonSysApiInverseRequests
    public CompletableFuture<ResReqNaviRemainTimeDistance> requestReqNaviRemainTimeDistance(Duration duration) {
        return requestReqNaviRemainTimeDistance(new ReqReqNaviRemainTimeDistance(), duration);
    }

    @Override // api.requests.com.neusoft.common.commonsysapiinverse.ICommonSysApiInverseRequests
    public CompletableFuture<Void> fireReqNaviRemainTimeDistance(ReqReqNaviRemainTimeDistance reqReqNaviRemainTimeDistance) {
        reqReqNaviRemainTimeDistance.setResponse(null);
        String str = this.component;
        if (str != null) {
            reqReqNaviRemainTimeDistance.setComponent(str);
        }
        return Communication.request(this.contextUtil.createFromClass(reqReqNaviRemainTimeDistance)).thenCompose(new Function<Object, CompletableFuture<Void>>() { // from class: api.requests.com.neusoft.common.commonsysapiinverse.CommonSysApiInverseRequests.226
            @Override // java9.util.function.Function
            public CompletableFuture<Void> apply(Object obj) {
                return CommonSysApiInverseRequests.completedVoidFuture;
            }
        });
    }

    @Override // api.requests.com.neusoft.common.commonsysapiinverse.ICommonSysApiInverseRequests
    public CompletableFuture<Void> fireReqNaviRemainTimeDistance(ReqReqNaviRemainTimeDistance reqReqNaviRemainTimeDistance, final Duration duration) {
        return fireReqNaviRemainTimeDistance(reqReqNaviRemainTimeDistance).thenCompose((Function<? super Void, ? extends CompletionStage<U>>) new Function<Void, CompletableFuture<Void>>() { // from class: api.requests.com.neusoft.common.commonsysapiinverse.CommonSysApiInverseRequests.227
            @Override // java9.util.function.Function
            public CompletableFuture<Void> apply(Void r2) {
                return CommonUtil.asyncSleep(duration, CommonSysApiInverseRequests.this.contextUtil.getDefaultExecutor());
            }
        });
    }

    @Override // api.requests.com.neusoft.common.commonsysapiinverse.ICommonSysApiInverseRequests
    public CompletableFuture<Void> fireReqNaviRemainTimeDistance() {
        return fireReqNaviRemainTimeDistance(new ReqReqNaviRemainTimeDistance());
    }

    @Override // api.requests.com.neusoft.common.commonsysapiinverse.ICommonSysApiInverseRequests
    public CompletableFuture<Void> fireReqNaviRemainTimeDistance(final Duration duration) {
        return fireReqNaviRemainTimeDistance(new ReqReqNaviRemainTimeDistance()).thenCompose((Function<? super Void, ? extends CompletionStage<U>>) new Function<Void, CompletableFuture<Void>>() { // from class: api.requests.com.neusoft.common.commonsysapiinverse.CommonSysApiInverseRequests.228
            @Override // java9.util.function.Function
            public CompletableFuture<Void> apply(Void r2) {
                return CommonUtil.asyncSleep(duration, CommonSysApiInverseRequests.this.contextUtil.getDefaultExecutor());
            }
        });
    }

    @Override // api.requests.com.neusoft.common.commonsysapiinverse.ICommonSysApiInverseRequests
    public CompletableFuture<ResGetGuideInfo> requestGetGuideInfo(ReqGetGuideInfo reqGetGuideInfo) {
        if (reqGetGuideInfo.getResponse() == null) {
            reqGetGuideInfo.setResponse(new ResGetGuideInfo());
        }
        String str = this.component;
        if (str != null) {
            reqGetGuideInfo.setComponent(str);
            reqGetGuideInfo.getResponse().setComponent(this.component);
        }
        return Communication.request(this.contextUtil.createFromClass(reqGetGuideInfo));
    }

    @Override // api.requests.com.neusoft.common.commonsysapiinverse.ICommonSysApiInverseRequests
    public CompletableFuture<ResGetGuideInfo> requestGetGuideInfo(ReqGetGuideInfo reqGetGuideInfo, final Duration duration) {
        return requestGetGuideInfo(reqGetGuideInfo).thenCompose((Function<? super ResGetGuideInfo, ? extends CompletionStage<U>>) new Function<ResGetGuideInfo, CompletableFuture<ResGetGuideInfo>>() { // from class: api.requests.com.neusoft.common.commonsysapiinverse.CommonSysApiInverseRequests.229
            @Override // java9.util.function.Function
            public CompletableFuture<ResGetGuideInfo> apply(ResGetGuideInfo resGetGuideInfo) {
                return CommonUtil.delayAsync(resGetGuideInfo, duration, CommonSysApiInverseRequests.this.contextUtil.getDefaultExecutor());
            }
        });
    }

    @Override // api.requests.com.neusoft.common.commonsysapiinverse.ICommonSysApiInverseRequests
    public CompletableFuture<ResGetGuideInfo> requestGetGuideInfo() {
        return requestGetGuideInfo(new ReqGetGuideInfo());
    }

    @Override // api.requests.com.neusoft.common.commonsysapiinverse.ICommonSysApiInverseRequests
    public CompletableFuture<ResGetGuideInfo> requestGetGuideInfo(Duration duration) {
        return requestGetGuideInfo(new ReqGetGuideInfo(), duration);
    }

    @Override // api.requests.com.neusoft.common.commonsysapiinverse.ICommonSysApiInverseRequests
    public CompletableFuture<Void> fireGetGuideInfo(ReqGetGuideInfo reqGetGuideInfo) {
        reqGetGuideInfo.setResponse(null);
        String str = this.component;
        if (str != null) {
            reqGetGuideInfo.setComponent(str);
        }
        return Communication.request(this.contextUtil.createFromClass(reqGetGuideInfo)).thenCompose(new Function<Object, CompletableFuture<Void>>() { // from class: api.requests.com.neusoft.common.commonsysapiinverse.CommonSysApiInverseRequests.230
            @Override // java9.util.function.Function
            public CompletableFuture<Void> apply(Object obj) {
                return CommonSysApiInverseRequests.completedVoidFuture;
            }
        });
    }

    @Override // api.requests.com.neusoft.common.commonsysapiinverse.ICommonSysApiInverseRequests
    public CompletableFuture<Void> fireGetGuideInfo(ReqGetGuideInfo reqGetGuideInfo, final Duration duration) {
        return fireGetGuideInfo(reqGetGuideInfo).thenCompose((Function<? super Void, ? extends CompletionStage<U>>) new Function<Void, CompletableFuture<Void>>() { // from class: api.requests.com.neusoft.common.commonsysapiinverse.CommonSysApiInverseRequests.231
            @Override // java9.util.function.Function
            public CompletableFuture<Void> apply(Void r2) {
                return CommonUtil.asyncSleep(duration, CommonSysApiInverseRequests.this.contextUtil.getDefaultExecutor());
            }
        });
    }

    @Override // api.requests.com.neusoft.common.commonsysapiinverse.ICommonSysApiInverseRequests
    public CompletableFuture<Void> fireGetGuideInfo() {
        return fireGetGuideInfo(new ReqGetGuideInfo());
    }

    @Override // api.requests.com.neusoft.common.commonsysapiinverse.ICommonSysApiInverseRequests
    public CompletableFuture<Void> fireGetGuideInfo(final Duration duration) {
        return fireGetGuideInfo(new ReqGetGuideInfo()).thenCompose((Function<? super Void, ? extends CompletionStage<U>>) new Function<Void, CompletableFuture<Void>>() { // from class: api.requests.com.neusoft.common.commonsysapiinverse.CommonSysApiInverseRequests.232
            @Override // java9.util.function.Function
            public CompletableFuture<Void> apply(Void r2) {
                return CommonUtil.asyncSleep(duration, CommonSysApiInverseRequests.this.contextUtil.getDefaultExecutor());
            }
        });
    }

    @Override // api.requests.com.neusoft.common.commonsysapiinverse.ICommonSysApiInverseRequests
    public CompletableFuture<ResGetNaviMuteModeComm> requestGetNaviMuteModeComm(ReqGetNaviMuteModeComm reqGetNaviMuteModeComm) {
        if (reqGetNaviMuteModeComm.getResponse() == null) {
            reqGetNaviMuteModeComm.setResponse(new ResGetNaviMuteModeComm());
        }
        String str = this.component;
        if (str != null) {
            reqGetNaviMuteModeComm.setComponent(str);
            reqGetNaviMuteModeComm.getResponse().setComponent(this.component);
        }
        return Communication.request(this.contextUtil.createFromClass(reqGetNaviMuteModeComm));
    }

    @Override // api.requests.com.neusoft.common.commonsysapiinverse.ICommonSysApiInverseRequests
    public CompletableFuture<ResGetNaviMuteModeComm> requestGetNaviMuteModeComm(ReqGetNaviMuteModeComm reqGetNaviMuteModeComm, final Duration duration) {
        return requestGetNaviMuteModeComm(reqGetNaviMuteModeComm).thenCompose((Function<? super ResGetNaviMuteModeComm, ? extends CompletionStage<U>>) new Function<ResGetNaviMuteModeComm, CompletableFuture<ResGetNaviMuteModeComm>>() { // from class: api.requests.com.neusoft.common.commonsysapiinverse.CommonSysApiInverseRequests.233
            @Override // java9.util.function.Function
            public CompletableFuture<ResGetNaviMuteModeComm> apply(ResGetNaviMuteModeComm resGetNaviMuteModeComm) {
                return CommonUtil.delayAsync(resGetNaviMuteModeComm, duration, CommonSysApiInverseRequests.this.contextUtil.getDefaultExecutor());
            }
        });
    }

    @Override // api.requests.com.neusoft.common.commonsysapiinverse.ICommonSysApiInverseRequests
    public CompletableFuture<ResGetNaviMuteModeComm> requestGetNaviMuteModeComm() {
        return requestGetNaviMuteModeComm(new ReqGetNaviMuteModeComm());
    }

    @Override // api.requests.com.neusoft.common.commonsysapiinverse.ICommonSysApiInverseRequests
    public CompletableFuture<ResGetNaviMuteModeComm> requestGetNaviMuteModeComm(Duration duration) {
        return requestGetNaviMuteModeComm(new ReqGetNaviMuteModeComm(), duration);
    }

    @Override // api.requests.com.neusoft.common.commonsysapiinverse.ICommonSysApiInverseRequests
    public CompletableFuture<Void> fireGetNaviMuteModeComm(ReqGetNaviMuteModeComm reqGetNaviMuteModeComm) {
        reqGetNaviMuteModeComm.setResponse(null);
        String str = this.component;
        if (str != null) {
            reqGetNaviMuteModeComm.setComponent(str);
        }
        return Communication.request(this.contextUtil.createFromClass(reqGetNaviMuteModeComm)).thenCompose(new Function<Object, CompletableFuture<Void>>() { // from class: api.requests.com.neusoft.common.commonsysapiinverse.CommonSysApiInverseRequests.234
            @Override // java9.util.function.Function
            public CompletableFuture<Void> apply(Object obj) {
                return CommonSysApiInverseRequests.completedVoidFuture;
            }
        });
    }

    @Override // api.requests.com.neusoft.common.commonsysapiinverse.ICommonSysApiInverseRequests
    public CompletableFuture<Void> fireGetNaviMuteModeComm(ReqGetNaviMuteModeComm reqGetNaviMuteModeComm, final Duration duration) {
        return fireGetNaviMuteModeComm(reqGetNaviMuteModeComm).thenCompose((Function<? super Void, ? extends CompletionStage<U>>) new Function<Void, CompletableFuture<Void>>() { // from class: api.requests.com.neusoft.common.commonsysapiinverse.CommonSysApiInverseRequests.235
            @Override // java9.util.function.Function
            public CompletableFuture<Void> apply(Void r2) {
                return CommonUtil.asyncSleep(duration, CommonSysApiInverseRequests.this.contextUtil.getDefaultExecutor());
            }
        });
    }

    @Override // api.requests.com.neusoft.common.commonsysapiinverse.ICommonSysApiInverseRequests
    public CompletableFuture<Void> fireGetNaviMuteModeComm() {
        return fireGetNaviMuteModeComm(new ReqGetNaviMuteModeComm());
    }

    @Override // api.requests.com.neusoft.common.commonsysapiinverse.ICommonSysApiInverseRequests
    public CompletableFuture<Void> fireGetNaviMuteModeComm(final Duration duration) {
        return fireGetNaviMuteModeComm(new ReqGetNaviMuteModeComm()).thenCompose((Function<? super Void, ? extends CompletionStage<U>>) new Function<Void, CompletableFuture<Void>>() { // from class: api.requests.com.neusoft.common.commonsysapiinverse.CommonSysApiInverseRequests.236
            @Override // java9.util.function.Function
            public CompletableFuture<Void> apply(Void r2) {
                return CommonUtil.asyncSleep(duration, CommonSysApiInverseRequests.this.contextUtil.getDefaultExecutor());
            }
        });
    }

    @Override // api.requests.com.neusoft.common.commonsysapiinverse.ICommonSysApiInverseRequests
    public CompletableFuture<ResSetNaviMuteModeComm> requestSetNaviMuteModeComm(ReqSetNaviMuteModeComm reqSetNaviMuteModeComm) {
        if (reqSetNaviMuteModeComm.getResponse() == null) {
            reqSetNaviMuteModeComm.setResponse(new ResSetNaviMuteModeComm());
        }
        String str = this.component;
        if (str != null) {
            reqSetNaviMuteModeComm.setComponent(str);
            reqSetNaviMuteModeComm.getResponse().setComponent(this.component);
        }
        return Communication.request(this.contextUtil.createFromClass(reqSetNaviMuteModeComm));
    }

    @Override // api.requests.com.neusoft.common.commonsysapiinverse.ICommonSysApiInverseRequests
    public CompletableFuture<ResSetNaviMuteModeComm> requestSetNaviMuteModeComm(ReqSetNaviMuteModeComm reqSetNaviMuteModeComm, final Duration duration) {
        return requestSetNaviMuteModeComm(reqSetNaviMuteModeComm).thenCompose((Function<? super ResSetNaviMuteModeComm, ? extends CompletionStage<U>>) new Function<ResSetNaviMuteModeComm, CompletableFuture<ResSetNaviMuteModeComm>>() { // from class: api.requests.com.neusoft.common.commonsysapiinverse.CommonSysApiInverseRequests.237
            @Override // java9.util.function.Function
            public CompletableFuture<ResSetNaviMuteModeComm> apply(ResSetNaviMuteModeComm resSetNaviMuteModeComm) {
                return CommonUtil.delayAsync(resSetNaviMuteModeComm, duration, CommonSysApiInverseRequests.this.contextUtil.getDefaultExecutor());
            }
        });
    }

    @Override // api.requests.com.neusoft.common.commonsysapiinverse.ICommonSysApiInverseRequests
    public CompletableFuture<ResSetNaviMuteModeComm.Arguments> requestSetNaviMuteModeComm(ReqSetNaviMuteModeComm.Arguments arguments) {
        ReqSetNaviMuteModeComm reqSetNaviMuteModeComm = new ReqSetNaviMuteModeComm();
        reqSetNaviMuteModeComm.setResponse(new ResSetNaviMuteModeComm());
        reqSetNaviMuteModeComm.setArguments(arguments);
        return requestSetNaviMuteModeComm(reqSetNaviMuteModeComm).thenApply((Function<? super ResSetNaviMuteModeComm, ? extends U>) new Function<ResSetNaviMuteModeComm, ResSetNaviMuteModeComm.Arguments>() { // from class: api.requests.com.neusoft.common.commonsysapiinverse.CommonSysApiInverseRequests.238
            @Override // java9.util.function.Function
            public ResSetNaviMuteModeComm.Arguments apply(ResSetNaviMuteModeComm resSetNaviMuteModeComm) {
                return resSetNaviMuteModeComm.getArguments();
            }
        });
    }

    @Override // api.requests.com.neusoft.common.commonsysapiinverse.ICommonSysApiInverseRequests
    public CompletableFuture<ResSetNaviMuteModeComm.Arguments> requestSetNaviMuteModeComm(ReqSetNaviMuteModeComm.Arguments arguments, final Duration duration) {
        return requestSetNaviMuteModeComm(arguments).thenCompose((Function<? super ResSetNaviMuteModeComm.Arguments, ? extends CompletionStage<U>>) new Function<ResSetNaviMuteModeComm.Arguments, CompletableFuture<ResSetNaviMuteModeComm.Arguments>>() { // from class: api.requests.com.neusoft.common.commonsysapiinverse.CommonSysApiInverseRequests.239
            @Override // java9.util.function.Function
            public CompletableFuture<ResSetNaviMuteModeComm.Arguments> apply(ResSetNaviMuteModeComm.Arguments arguments2) {
                return CommonUtil.delayAsync(arguments2, duration, CommonSysApiInverseRequests.this.contextUtil.getDefaultExecutor());
            }
        });
    }

    @Override // api.requests.com.neusoft.common.commonsysapiinverse.ICommonSysApiInverseRequests
    public CompletableFuture<Void> fireSetNaviMuteModeComm(ReqSetNaviMuteModeComm reqSetNaviMuteModeComm) {
        reqSetNaviMuteModeComm.setResponse(null);
        String str = this.component;
        if (str != null) {
            reqSetNaviMuteModeComm.setComponent(str);
        }
        return Communication.request(this.contextUtil.createFromClass(reqSetNaviMuteModeComm)).thenCompose(new Function<Object, CompletableFuture<Void>>() { // from class: api.requests.com.neusoft.common.commonsysapiinverse.CommonSysApiInverseRequests.240
            @Override // java9.util.function.Function
            public CompletableFuture<Void> apply(Object obj) {
                return CommonSysApiInverseRequests.completedVoidFuture;
            }
        });
    }

    @Override // api.requests.com.neusoft.common.commonsysapiinverse.ICommonSysApiInverseRequests
    public CompletableFuture<Void> fireSetNaviMuteModeComm(ReqSetNaviMuteModeComm reqSetNaviMuteModeComm, final Duration duration) {
        return fireSetNaviMuteModeComm(reqSetNaviMuteModeComm).thenCompose((Function<? super Void, ? extends CompletionStage<U>>) new Function<Void, CompletableFuture<Void>>() { // from class: api.requests.com.neusoft.common.commonsysapiinverse.CommonSysApiInverseRequests.241
            @Override // java9.util.function.Function
            public CompletableFuture<Void> apply(Void r2) {
                return CommonUtil.asyncSleep(duration, CommonSysApiInverseRequests.this.contextUtil.getDefaultExecutor());
            }
        });
    }

    @Override // api.requests.com.neusoft.common.commonsysapiinverse.ICommonSysApiInverseRequests
    public CompletableFuture<Void> fireSetNaviMuteModeComm(ReqSetNaviMuteModeComm.Arguments arguments) {
        ReqSetNaviMuteModeComm reqSetNaviMuteModeComm = new ReqSetNaviMuteModeComm();
        reqSetNaviMuteModeComm.setArguments(arguments);
        reqSetNaviMuteModeComm.setResponse(null);
        String str = this.component;
        if (str != null) {
            reqSetNaviMuteModeComm.setComponent(str);
        }
        return fireSetNaviMuteModeComm(reqSetNaviMuteModeComm);
    }

    @Override // api.requests.com.neusoft.common.commonsysapiinverse.ICommonSysApiInverseRequests
    public CompletableFuture<Void> fireSetNaviMuteModeComm(ReqSetNaviMuteModeComm.Arguments arguments, final Duration duration) {
        return fireSetNaviMuteModeComm(arguments).thenCompose((Function<? super Void, ? extends CompletionStage<U>>) new Function<Void, CompletableFuture<Void>>() { // from class: api.requests.com.neusoft.common.commonsysapiinverse.CommonSysApiInverseRequests.242
            @Override // java9.util.function.Function
            public CompletableFuture<Void> apply(Void r2) {
                return CommonUtil.asyncSleep(duration, CommonSysApiInverseRequests.this.contextUtil.getDefaultExecutor());
            }
        });
    }

    @Override // api.requests.com.neusoft.common.commonsysapiinverse.ICommonSysApiInverseRequests
    public CompletableFuture<Void> fireSwitchMapSetting(ReqSwitchMapSetting reqSwitchMapSetting) {
        reqSwitchMapSetting.setResponse(null);
        String str = this.component;
        if (str != null) {
            reqSwitchMapSetting.setComponent(str);
        }
        return Communication.request(this.contextUtil.createFromClass(reqSwitchMapSetting)).thenCompose(new Function<Object, CompletableFuture<Void>>() { // from class: api.requests.com.neusoft.common.commonsysapiinverse.CommonSysApiInverseRequests.243
            @Override // java9.util.function.Function
            public CompletableFuture<Void> apply(Object obj) {
                return CommonSysApiInverseRequests.completedVoidFuture;
            }
        });
    }

    @Override // api.requests.com.neusoft.common.commonsysapiinverse.ICommonSysApiInverseRequests
    public CompletableFuture<Void> fireSwitchMapSetting(ReqSwitchMapSetting reqSwitchMapSetting, final Duration duration) {
        return fireSwitchMapSetting(reqSwitchMapSetting).thenCompose((Function<? super Void, ? extends CompletionStage<U>>) new Function<Void, CompletableFuture<Void>>() { // from class: api.requests.com.neusoft.common.commonsysapiinverse.CommonSysApiInverseRequests.244
            @Override // java9.util.function.Function
            public CompletableFuture<Void> apply(Void r2) {
                return CommonUtil.asyncSleep(duration, CommonSysApiInverseRequests.this.contextUtil.getDefaultExecutor());
            }
        });
    }

    @Override // api.requests.com.neusoft.common.commonsysapiinverse.ICommonSysApiInverseRequests
    public CompletableFuture<Void> fireSwitchMapSetting(ReqSwitchMapSetting.Arguments arguments) {
        ReqSwitchMapSetting reqSwitchMapSetting = new ReqSwitchMapSetting();
        reqSwitchMapSetting.setArguments(arguments);
        reqSwitchMapSetting.setResponse(null);
        String str = this.component;
        if (str != null) {
            reqSwitchMapSetting.setComponent(str);
        }
        return fireSwitchMapSetting(reqSwitchMapSetting);
    }

    @Override // api.requests.com.neusoft.common.commonsysapiinverse.ICommonSysApiInverseRequests
    public CompletableFuture<Void> fireSwitchMapSetting(ReqSwitchMapSetting.Arguments arguments, final Duration duration) {
        return fireSwitchMapSetting(arguments).thenCompose((Function<? super Void, ? extends CompletionStage<U>>) new Function<Void, CompletableFuture<Void>>() { // from class: api.requests.com.neusoft.common.commonsysapiinverse.CommonSysApiInverseRequests.245
            @Override // java9.util.function.Function
            public CompletableFuture<Void> apply(Void r2) {
                return CommonUtil.asyncSleep(duration, CommonSysApiInverseRequests.this.contextUtil.getDefaultExecutor());
            }
        });
    }

    @Override // api.requests.com.neusoft.common.commonsysapiinverse.ICommonSysApiInverseRequests
    public CompletableFuture<ResGetMapSettingStatus> requestGetMapSettingStatus(ReqGetMapSettingStatus reqGetMapSettingStatus) {
        if (reqGetMapSettingStatus.getResponse() == null) {
            reqGetMapSettingStatus.setResponse(new ResGetMapSettingStatus());
        }
        String str = this.component;
        if (str != null) {
            reqGetMapSettingStatus.setComponent(str);
            reqGetMapSettingStatus.getResponse().setComponent(this.component);
        }
        return Communication.request(this.contextUtil.createFromClass(reqGetMapSettingStatus));
    }

    @Override // api.requests.com.neusoft.common.commonsysapiinverse.ICommonSysApiInverseRequests
    public CompletableFuture<ResGetMapSettingStatus> requestGetMapSettingStatus(ReqGetMapSettingStatus reqGetMapSettingStatus, final Duration duration) {
        return requestGetMapSettingStatus(reqGetMapSettingStatus).thenCompose((Function<? super ResGetMapSettingStatus, ? extends CompletionStage<U>>) new Function<ResGetMapSettingStatus, CompletableFuture<ResGetMapSettingStatus>>() { // from class: api.requests.com.neusoft.common.commonsysapiinverse.CommonSysApiInverseRequests.246
            @Override // java9.util.function.Function
            public CompletableFuture<ResGetMapSettingStatus> apply(ResGetMapSettingStatus resGetMapSettingStatus) {
                return CommonUtil.delayAsync(resGetMapSettingStatus, duration, CommonSysApiInverseRequests.this.contextUtil.getDefaultExecutor());
            }
        });
    }

    @Override // api.requests.com.neusoft.common.commonsysapiinverse.ICommonSysApiInverseRequests
    public CompletableFuture<ResGetMapSettingStatus> requestGetMapSettingStatus() {
        return requestGetMapSettingStatus(new ReqGetMapSettingStatus());
    }

    @Override // api.requests.com.neusoft.common.commonsysapiinverse.ICommonSysApiInverseRequests
    public CompletableFuture<ResGetMapSettingStatus> requestGetMapSettingStatus(Duration duration) {
        return requestGetMapSettingStatus(new ReqGetMapSettingStatus(), duration);
    }

    @Override // api.requests.com.neusoft.common.commonsysapiinverse.ICommonSysApiInverseRequests
    public CompletableFuture<Void> fireGetMapSettingStatus(ReqGetMapSettingStatus reqGetMapSettingStatus) {
        reqGetMapSettingStatus.setResponse(null);
        String str = this.component;
        if (str != null) {
            reqGetMapSettingStatus.setComponent(str);
        }
        return Communication.request(this.contextUtil.createFromClass(reqGetMapSettingStatus)).thenCompose(new Function<Object, CompletableFuture<Void>>() { // from class: api.requests.com.neusoft.common.commonsysapiinverse.CommonSysApiInverseRequests.247
            @Override // java9.util.function.Function
            public CompletableFuture<Void> apply(Object obj) {
                return CommonSysApiInverseRequests.completedVoidFuture;
            }
        });
    }

    @Override // api.requests.com.neusoft.common.commonsysapiinverse.ICommonSysApiInverseRequests
    public CompletableFuture<Void> fireGetMapSettingStatus(ReqGetMapSettingStatus reqGetMapSettingStatus, final Duration duration) {
        return fireGetMapSettingStatus(reqGetMapSettingStatus).thenCompose((Function<? super Void, ? extends CompletionStage<U>>) new Function<Void, CompletableFuture<Void>>() { // from class: api.requests.com.neusoft.common.commonsysapiinverse.CommonSysApiInverseRequests.248
            @Override // java9.util.function.Function
            public CompletableFuture<Void> apply(Void r2) {
                return CommonUtil.asyncSleep(duration, CommonSysApiInverseRequests.this.contextUtil.getDefaultExecutor());
            }
        });
    }

    @Override // api.requests.com.neusoft.common.commonsysapiinverse.ICommonSysApiInverseRequests
    public CompletableFuture<Void> fireGetMapSettingStatus() {
        return fireGetMapSettingStatus(new ReqGetMapSettingStatus());
    }

    @Override // api.requests.com.neusoft.common.commonsysapiinverse.ICommonSysApiInverseRequests
    public CompletableFuture<Void> fireGetMapSettingStatus(final Duration duration) {
        return fireGetMapSettingStatus(new ReqGetMapSettingStatus()).thenCompose((Function<? super Void, ? extends CompletionStage<U>>) new Function<Void, CompletableFuture<Void>>() { // from class: api.requests.com.neusoft.common.commonsysapiinverse.CommonSysApiInverseRequests.249
            @Override // java9.util.function.Function
            public CompletableFuture<Void> apply(Void r2) {
                return CommonUtil.asyncSleep(duration, CommonSysApiInverseRequests.this.contextUtil.getDefaultExecutor());
            }
        });
    }

    @Override // api.requests.com.neusoft.common.commonsysapiinverse.ICommonSysApiInverseRequests
    public CompletableFuture<ResGetMapAppStatus> requestGetMapAppStatus(ReqGetMapAppStatus reqGetMapAppStatus) {
        if (reqGetMapAppStatus.getResponse() == null) {
            reqGetMapAppStatus.setResponse(new ResGetMapAppStatus());
        }
        String str = this.component;
        if (str != null) {
            reqGetMapAppStatus.setComponent(str);
            reqGetMapAppStatus.getResponse().setComponent(this.component);
        }
        return Communication.request(this.contextUtil.createFromClass(reqGetMapAppStatus));
    }

    @Override // api.requests.com.neusoft.common.commonsysapiinverse.ICommonSysApiInverseRequests
    public CompletableFuture<ResGetMapAppStatus> requestGetMapAppStatus(ReqGetMapAppStatus reqGetMapAppStatus, final Duration duration) {
        return requestGetMapAppStatus(reqGetMapAppStatus).thenCompose((Function<? super ResGetMapAppStatus, ? extends CompletionStage<U>>) new Function<ResGetMapAppStatus, CompletableFuture<ResGetMapAppStatus>>() { // from class: api.requests.com.neusoft.common.commonsysapiinverse.CommonSysApiInverseRequests.250
            @Override // java9.util.function.Function
            public CompletableFuture<ResGetMapAppStatus> apply(ResGetMapAppStatus resGetMapAppStatus) {
                return CommonUtil.delayAsync(resGetMapAppStatus, duration, CommonSysApiInverseRequests.this.contextUtil.getDefaultExecutor());
            }
        });
    }

    @Override // api.requests.com.neusoft.common.commonsysapiinverse.ICommonSysApiInverseRequests
    public CompletableFuture<ResGetMapAppStatus> requestGetMapAppStatus() {
        return requestGetMapAppStatus(new ReqGetMapAppStatus());
    }

    @Override // api.requests.com.neusoft.common.commonsysapiinverse.ICommonSysApiInverseRequests
    public CompletableFuture<ResGetMapAppStatus> requestGetMapAppStatus(Duration duration) {
        return requestGetMapAppStatus(new ReqGetMapAppStatus(), duration);
    }

    @Override // api.requests.com.neusoft.common.commonsysapiinverse.ICommonSysApiInverseRequests
    public CompletableFuture<Void> fireGetMapAppStatus(ReqGetMapAppStatus reqGetMapAppStatus) {
        reqGetMapAppStatus.setResponse(null);
        String str = this.component;
        if (str != null) {
            reqGetMapAppStatus.setComponent(str);
        }
        return Communication.request(this.contextUtil.createFromClass(reqGetMapAppStatus)).thenCompose(new Function<Object, CompletableFuture<Void>>() { // from class: api.requests.com.neusoft.common.commonsysapiinverse.CommonSysApiInverseRequests.251
            @Override // java9.util.function.Function
            public CompletableFuture<Void> apply(Object obj) {
                return CommonSysApiInverseRequests.completedVoidFuture;
            }
        });
    }

    @Override // api.requests.com.neusoft.common.commonsysapiinverse.ICommonSysApiInverseRequests
    public CompletableFuture<Void> fireGetMapAppStatus(ReqGetMapAppStatus reqGetMapAppStatus, final Duration duration) {
        return fireGetMapAppStatus(reqGetMapAppStatus).thenCompose((Function<? super Void, ? extends CompletionStage<U>>) new Function<Void, CompletableFuture<Void>>() { // from class: api.requests.com.neusoft.common.commonsysapiinverse.CommonSysApiInverseRequests.252
            @Override // java9.util.function.Function
            public CompletableFuture<Void> apply(Void r2) {
                return CommonUtil.asyncSleep(duration, CommonSysApiInverseRequests.this.contextUtil.getDefaultExecutor());
            }
        });
    }

    @Override // api.requests.com.neusoft.common.commonsysapiinverse.ICommonSysApiInverseRequests
    public CompletableFuture<Void> fireGetMapAppStatus() {
        return fireGetMapAppStatus(new ReqGetMapAppStatus());
    }

    @Override // api.requests.com.neusoft.common.commonsysapiinverse.ICommonSysApiInverseRequests
    public CompletableFuture<Void> fireGetMapAppStatus(final Duration duration) {
        return fireGetMapAppStatus(new ReqGetMapAppStatus()).thenCompose((Function<? super Void, ? extends CompletionStage<U>>) new Function<Void, CompletableFuture<Void>>() { // from class: api.requests.com.neusoft.common.commonsysapiinverse.CommonSysApiInverseRequests.253
            @Override // java9.util.function.Function
            public CompletableFuture<Void> apply(Void r2) {
                return CommonUtil.asyncSleep(duration, CommonSysApiInverseRequests.this.contextUtil.getDefaultExecutor());
            }
        });
    }

    @Override // api.requests.com.neusoft.common.commonsysapiinverse.ICommonSysApiInverseRequests
    public CompletableFuture<Void> fireSetMapSurfaceStatus(ReqSetMapSurfaceStatus reqSetMapSurfaceStatus) {
        reqSetMapSurfaceStatus.setResponse(null);
        String str = this.component;
        if (str != null) {
            reqSetMapSurfaceStatus.setComponent(str);
        }
        return Communication.request(this.contextUtil.createFromClass(reqSetMapSurfaceStatus)).thenCompose(new Function<Object, CompletableFuture<Void>>() { // from class: api.requests.com.neusoft.common.commonsysapiinverse.CommonSysApiInverseRequests.254
            @Override // java9.util.function.Function
            public CompletableFuture<Void> apply(Object obj) {
                return CommonSysApiInverseRequests.completedVoidFuture;
            }
        });
    }

    @Override // api.requests.com.neusoft.common.commonsysapiinverse.ICommonSysApiInverseRequests
    public CompletableFuture<Void> fireSetMapSurfaceStatus(ReqSetMapSurfaceStatus reqSetMapSurfaceStatus, final Duration duration) {
        return fireSetMapSurfaceStatus(reqSetMapSurfaceStatus).thenCompose((Function<? super Void, ? extends CompletionStage<U>>) new Function<Void, CompletableFuture<Void>>() { // from class: api.requests.com.neusoft.common.commonsysapiinverse.CommonSysApiInverseRequests.255
            @Override // java9.util.function.Function
            public CompletableFuture<Void> apply(Void r2) {
                return CommonUtil.asyncSleep(duration, CommonSysApiInverseRequests.this.contextUtil.getDefaultExecutor());
            }
        });
    }

    @Override // api.requests.com.neusoft.common.commonsysapiinverse.ICommonSysApiInverseRequests
    public CompletableFuture<Void> fireSetMapSurfaceStatus(ReqSetMapSurfaceStatus.Arguments arguments) {
        ReqSetMapSurfaceStatus reqSetMapSurfaceStatus = new ReqSetMapSurfaceStatus();
        reqSetMapSurfaceStatus.setArguments(arguments);
        reqSetMapSurfaceStatus.setResponse(null);
        String str = this.component;
        if (str != null) {
            reqSetMapSurfaceStatus.setComponent(str);
        }
        return fireSetMapSurfaceStatus(reqSetMapSurfaceStatus);
    }

    @Override // api.requests.com.neusoft.common.commonsysapiinverse.ICommonSysApiInverseRequests
    public CompletableFuture<Void> fireSetMapSurfaceStatus(ReqSetMapSurfaceStatus.Arguments arguments, final Duration duration) {
        return fireSetMapSurfaceStatus(arguments).thenCompose((Function<? super Void, ? extends CompletionStage<U>>) new Function<Void, CompletableFuture<Void>>() { // from class: api.requests.com.neusoft.common.commonsysapiinverse.CommonSysApiInverseRequests.256
            @Override // java9.util.function.Function
            public CompletableFuture<Void> apply(Void r2) {
                return CommonUtil.asyncSleep(duration, CommonSysApiInverseRequests.this.contextUtil.getDefaultExecutor());
            }
        });
    }

    @Override // api.requests.com.neusoft.common.commonsysapiinverse.ICommonSysApiInverseRequests
    public CompletableFuture<Void> fireSetWidgetSurfaceStatus(ReqSetWidgetSurfaceStatus reqSetWidgetSurfaceStatus) {
        reqSetWidgetSurfaceStatus.setResponse(null);
        String str = this.component;
        if (str != null) {
            reqSetWidgetSurfaceStatus.setComponent(str);
        }
        return Communication.request(this.contextUtil.createFromClass(reqSetWidgetSurfaceStatus)).thenCompose(new Function<Object, CompletableFuture<Void>>() { // from class: api.requests.com.neusoft.common.commonsysapiinverse.CommonSysApiInverseRequests.257
            @Override // java9.util.function.Function
            public CompletableFuture<Void> apply(Object obj) {
                return CommonSysApiInverseRequests.completedVoidFuture;
            }
        });
    }

    @Override // api.requests.com.neusoft.common.commonsysapiinverse.ICommonSysApiInverseRequests
    public CompletableFuture<Void> fireSetWidgetSurfaceStatus(ReqSetWidgetSurfaceStatus reqSetWidgetSurfaceStatus, final Duration duration) {
        return fireSetWidgetSurfaceStatus(reqSetWidgetSurfaceStatus).thenCompose((Function<? super Void, ? extends CompletionStage<U>>) new Function<Void, CompletableFuture<Void>>() { // from class: api.requests.com.neusoft.common.commonsysapiinverse.CommonSysApiInverseRequests.258
            @Override // java9.util.function.Function
            public CompletableFuture<Void> apply(Void r2) {
                return CommonUtil.asyncSleep(duration, CommonSysApiInverseRequests.this.contextUtil.getDefaultExecutor());
            }
        });
    }

    @Override // api.requests.com.neusoft.common.commonsysapiinverse.ICommonSysApiInverseRequests
    public CompletableFuture<Void> fireSetWidgetSurfaceStatus(ReqSetWidgetSurfaceStatus.Arguments arguments) {
        ReqSetWidgetSurfaceStatus reqSetWidgetSurfaceStatus = new ReqSetWidgetSurfaceStatus();
        reqSetWidgetSurfaceStatus.setArguments(arguments);
        reqSetWidgetSurfaceStatus.setResponse(null);
        String str = this.component;
        if (str != null) {
            reqSetWidgetSurfaceStatus.setComponent(str);
        }
        return fireSetWidgetSurfaceStatus(reqSetWidgetSurfaceStatus);
    }

    @Override // api.requests.com.neusoft.common.commonsysapiinverse.ICommonSysApiInverseRequests
    public CompletableFuture<Void> fireSetWidgetSurfaceStatus(ReqSetWidgetSurfaceStatus.Arguments arguments, final Duration duration) {
        return fireSetWidgetSurfaceStatus(arguments).thenCompose((Function<? super Void, ? extends CompletionStage<U>>) new Function<Void, CompletableFuture<Void>>() { // from class: api.requests.com.neusoft.common.commonsysapiinverse.CommonSysApiInverseRequests.259
            @Override // java9.util.function.Function
            public CompletableFuture<Void> apply(Void r2) {
                return CommonUtil.asyncSleep(duration, CommonSysApiInverseRequests.this.contextUtil.getDefaultExecutor());
            }
        });
    }

    @Override // api.requests.com.neusoft.common.commonsysapiinverse.ICommonSysApiInverseRequests
    public CompletableFuture<Void> fireSetClusterSurfaceStatus(ReqSetClusterSurfaceStatus reqSetClusterSurfaceStatus) {
        reqSetClusterSurfaceStatus.setResponse(null);
        String str = this.component;
        if (str != null) {
            reqSetClusterSurfaceStatus.setComponent(str);
        }
        return Communication.request(this.contextUtil.createFromClass(reqSetClusterSurfaceStatus)).thenCompose(new Function<Object, CompletableFuture<Void>>() { // from class: api.requests.com.neusoft.common.commonsysapiinverse.CommonSysApiInverseRequests.260
            @Override // java9.util.function.Function
            public CompletableFuture<Void> apply(Object obj) {
                return CommonSysApiInverseRequests.completedVoidFuture;
            }
        });
    }

    @Override // api.requests.com.neusoft.common.commonsysapiinverse.ICommonSysApiInverseRequests
    public CompletableFuture<Void> fireSetClusterSurfaceStatus(ReqSetClusterSurfaceStatus reqSetClusterSurfaceStatus, final Duration duration) {
        return fireSetClusterSurfaceStatus(reqSetClusterSurfaceStatus).thenCompose((Function<? super Void, ? extends CompletionStage<U>>) new Function<Void, CompletableFuture<Void>>() { // from class: api.requests.com.neusoft.common.commonsysapiinverse.CommonSysApiInverseRequests.261
            @Override // java9.util.function.Function
            public CompletableFuture<Void> apply(Void r2) {
                return CommonUtil.asyncSleep(duration, CommonSysApiInverseRequests.this.contextUtil.getDefaultExecutor());
            }
        });
    }

    @Override // api.requests.com.neusoft.common.commonsysapiinverse.ICommonSysApiInverseRequests
    public CompletableFuture<Void> fireSetClusterSurfaceStatus(ReqSetClusterSurfaceStatus.Arguments arguments) {
        ReqSetClusterSurfaceStatus reqSetClusterSurfaceStatus = new ReqSetClusterSurfaceStatus();
        reqSetClusterSurfaceStatus.setArguments(arguments);
        reqSetClusterSurfaceStatus.setResponse(null);
        String str = this.component;
        if (str != null) {
            reqSetClusterSurfaceStatus.setComponent(str);
        }
        return fireSetClusterSurfaceStatus(reqSetClusterSurfaceStatus);
    }

    @Override // api.requests.com.neusoft.common.commonsysapiinverse.ICommonSysApiInverseRequests
    public CompletableFuture<Void> fireSetClusterSurfaceStatus(ReqSetClusterSurfaceStatus.Arguments arguments, final Duration duration) {
        return fireSetClusterSurfaceStatus(arguments).thenCompose((Function<? super Void, ? extends CompletionStage<U>>) new Function<Void, CompletableFuture<Void>>() { // from class: api.requests.com.neusoft.common.commonsysapiinverse.CommonSysApiInverseRequests.262
            @Override // java9.util.function.Function
            public CompletableFuture<Void> apply(Void r2) {
                return CommonUtil.asyncSleep(duration, CommonSysApiInverseRequests.this.contextUtil.getDefaultExecutor());
            }
        });
    }

    @Override // api.requests.com.neusoft.common.commonsysapiinverse.ICommonSysApiInverseRequests
    public CompletableFuture<Void> fireSetMapScreenStatus(ReqSetMapScreenStatus reqSetMapScreenStatus) {
        reqSetMapScreenStatus.setResponse(null);
        String str = this.component;
        if (str != null) {
            reqSetMapScreenStatus.setComponent(str);
        }
        return Communication.request(this.contextUtil.createFromClass(reqSetMapScreenStatus)).thenCompose(new Function<Object, CompletableFuture<Void>>() { // from class: api.requests.com.neusoft.common.commonsysapiinverse.CommonSysApiInverseRequests.263
            @Override // java9.util.function.Function
            public CompletableFuture<Void> apply(Object obj) {
                return CommonSysApiInverseRequests.completedVoidFuture;
            }
        });
    }

    @Override // api.requests.com.neusoft.common.commonsysapiinverse.ICommonSysApiInverseRequests
    public CompletableFuture<Void> fireSetMapScreenStatus(ReqSetMapScreenStatus reqSetMapScreenStatus, final Duration duration) {
        return fireSetMapScreenStatus(reqSetMapScreenStatus).thenCompose((Function<? super Void, ? extends CompletionStage<U>>) new Function<Void, CompletableFuture<Void>>() { // from class: api.requests.com.neusoft.common.commonsysapiinverse.CommonSysApiInverseRequests.264
            @Override // java9.util.function.Function
            public CompletableFuture<Void> apply(Void r2) {
                return CommonUtil.asyncSleep(duration, CommonSysApiInverseRequests.this.contextUtil.getDefaultExecutor());
            }
        });
    }

    @Override // api.requests.com.neusoft.common.commonsysapiinverse.ICommonSysApiInverseRequests
    public CompletableFuture<Void> fireSetMapScreenStatus(ReqSetMapScreenStatus.Arguments arguments) {
        ReqSetMapScreenStatus reqSetMapScreenStatus = new ReqSetMapScreenStatus();
        reqSetMapScreenStatus.setArguments(arguments);
        reqSetMapScreenStatus.setResponse(null);
        String str = this.component;
        if (str != null) {
            reqSetMapScreenStatus.setComponent(str);
        }
        return fireSetMapScreenStatus(reqSetMapScreenStatus);
    }

    @Override // api.requests.com.neusoft.common.commonsysapiinverse.ICommonSysApiInverseRequests
    public CompletableFuture<Void> fireSetMapScreenStatus(ReqSetMapScreenStatus.Arguments arguments, final Duration duration) {
        return fireSetMapScreenStatus(arguments).thenCompose((Function<? super Void, ? extends CompletionStage<U>>) new Function<Void, CompletableFuture<Void>>() { // from class: api.requests.com.neusoft.common.commonsysapiinverse.CommonSysApiInverseRequests.265
            @Override // java9.util.function.Function
            public CompletableFuture<Void> apply(Void r2) {
                return CommonUtil.asyncSleep(duration, CommonSysApiInverseRequests.this.contextUtil.getDefaultExecutor());
            }
        });
    }

    @Override // api.requests.com.neusoft.common.commonsysapiinverse.ICommonSysApiInverseRequests
    public CompletableFuture<Void> fireSetSystemLocale(ReqSetSystemLocale reqSetSystemLocale) {
        reqSetSystemLocale.setResponse(null);
        String str = this.component;
        if (str != null) {
            reqSetSystemLocale.setComponent(str);
        }
        return Communication.request(this.contextUtil.createFromClass(reqSetSystemLocale)).thenCompose(new Function<Object, CompletableFuture<Void>>() { // from class: api.requests.com.neusoft.common.commonsysapiinverse.CommonSysApiInverseRequests.266
            @Override // java9.util.function.Function
            public CompletableFuture<Void> apply(Object obj) {
                return CommonSysApiInverseRequests.completedVoidFuture;
            }
        });
    }

    @Override // api.requests.com.neusoft.common.commonsysapiinverse.ICommonSysApiInverseRequests
    public CompletableFuture<Void> fireSetSystemLocale(ReqSetSystemLocale reqSetSystemLocale, final Duration duration) {
        return fireSetSystemLocale(reqSetSystemLocale).thenCompose((Function<? super Void, ? extends CompletionStage<U>>) new Function<Void, CompletableFuture<Void>>() { // from class: api.requests.com.neusoft.common.commonsysapiinverse.CommonSysApiInverseRequests.267
            @Override // java9.util.function.Function
            public CompletableFuture<Void> apply(Void r2) {
                return CommonUtil.asyncSleep(duration, CommonSysApiInverseRequests.this.contextUtil.getDefaultExecutor());
            }
        });
    }

    @Override // api.requests.com.neusoft.common.commonsysapiinverse.ICommonSysApiInverseRequests
    public CompletableFuture<Void> fireSetSystemLocale(ReqSetSystemLocale.Arguments arguments) {
        ReqSetSystemLocale reqSetSystemLocale = new ReqSetSystemLocale();
        reqSetSystemLocale.setArguments(arguments);
        reqSetSystemLocale.setResponse(null);
        String str = this.component;
        if (str != null) {
            reqSetSystemLocale.setComponent(str);
        }
        return fireSetSystemLocale(reqSetSystemLocale);
    }

    @Override // api.requests.com.neusoft.common.commonsysapiinverse.ICommonSysApiInverseRequests
    public CompletableFuture<Void> fireSetSystemLocale(ReqSetSystemLocale.Arguments arguments, final Duration duration) {
        return fireSetSystemLocale(arguments).thenCompose((Function<? super Void, ? extends CompletionStage<U>>) new Function<Void, CompletableFuture<Void>>() { // from class: api.requests.com.neusoft.common.commonsysapiinverse.CommonSysApiInverseRequests.268
            @Override // java9.util.function.Function
            public CompletableFuture<Void> apply(Void r2) {
                return CommonUtil.asyncSleep(duration, CommonSysApiInverseRequests.this.contextUtil.getDefaultExecutor());
            }
        });
    }

    @Override // api.requests.com.neusoft.common.commonsysapiinverse.ICommonSysApiInverseRequests
    public CompletableFuture<Void> fireSetSystemTime(ReqSetSystemTime reqSetSystemTime) {
        reqSetSystemTime.setResponse(null);
        String str = this.component;
        if (str != null) {
            reqSetSystemTime.setComponent(str);
        }
        return Communication.request(this.contextUtil.createFromClass(reqSetSystemTime)).thenCompose(new Function<Object, CompletableFuture<Void>>() { // from class: api.requests.com.neusoft.common.commonsysapiinverse.CommonSysApiInverseRequests.269
            @Override // java9.util.function.Function
            public CompletableFuture<Void> apply(Object obj) {
                return CommonSysApiInverseRequests.completedVoidFuture;
            }
        });
    }

    @Override // api.requests.com.neusoft.common.commonsysapiinverse.ICommonSysApiInverseRequests
    public CompletableFuture<Void> fireSetSystemTime(ReqSetSystemTime reqSetSystemTime, final Duration duration) {
        return fireSetSystemTime(reqSetSystemTime).thenCompose((Function<? super Void, ? extends CompletionStage<U>>) new Function<Void, CompletableFuture<Void>>() { // from class: api.requests.com.neusoft.common.commonsysapiinverse.CommonSysApiInverseRequests.270
            @Override // java9.util.function.Function
            public CompletableFuture<Void> apply(Void r2) {
                return CommonUtil.asyncSleep(duration, CommonSysApiInverseRequests.this.contextUtil.getDefaultExecutor());
            }
        });
    }

    @Override // api.requests.com.neusoft.common.commonsysapiinverse.ICommonSysApiInverseRequests
    public CompletableFuture<Void> fireSetSystemTime(ReqSetSystemTime.Arguments arguments) {
        ReqSetSystemTime reqSetSystemTime = new ReqSetSystemTime();
        reqSetSystemTime.setArguments(arguments);
        reqSetSystemTime.setResponse(null);
        String str = this.component;
        if (str != null) {
            reqSetSystemTime.setComponent(str);
        }
        return fireSetSystemTime(reqSetSystemTime);
    }

    @Override // api.requests.com.neusoft.common.commonsysapiinverse.ICommonSysApiInverseRequests
    public CompletableFuture<Void> fireSetSystemTime(ReqSetSystemTime.Arguments arguments, final Duration duration) {
        return fireSetSystemTime(arguments).thenCompose((Function<? super Void, ? extends CompletionStage<U>>) new Function<Void, CompletableFuture<Void>>() { // from class: api.requests.com.neusoft.common.commonsysapiinverse.CommonSysApiInverseRequests.271
            @Override // java9.util.function.Function
            public CompletableFuture<Void> apply(Void r2) {
                return CommonUtil.asyncSleep(duration, CommonSysApiInverseRequests.this.contextUtil.getDefaultExecutor());
            }
        });
    }

    @Override // api.requests.com.neusoft.common.commonsysapiinverse.ICommonSysApiInverseRequests
    public CompletableFuture<Void> fireSetSystemTimeChange(ReqSetSystemTimeChange reqSetSystemTimeChange) {
        reqSetSystemTimeChange.setResponse(null);
        String str = this.component;
        if (str != null) {
            reqSetSystemTimeChange.setComponent(str);
        }
        return Communication.request(this.contextUtil.createFromClass(reqSetSystemTimeChange)).thenCompose(new Function<Object, CompletableFuture<Void>>() { // from class: api.requests.com.neusoft.common.commonsysapiinverse.CommonSysApiInverseRequests.272
            @Override // java9.util.function.Function
            public CompletableFuture<Void> apply(Object obj) {
                return CommonSysApiInverseRequests.completedVoidFuture;
            }
        });
    }

    @Override // api.requests.com.neusoft.common.commonsysapiinverse.ICommonSysApiInverseRequests
    public CompletableFuture<Void> fireSetSystemTimeChange(ReqSetSystemTimeChange reqSetSystemTimeChange, final Duration duration) {
        return fireSetSystemTimeChange(reqSetSystemTimeChange).thenCompose((Function<? super Void, ? extends CompletionStage<U>>) new Function<Void, CompletableFuture<Void>>() { // from class: api.requests.com.neusoft.common.commonsysapiinverse.CommonSysApiInverseRequests.273
            @Override // java9.util.function.Function
            public CompletableFuture<Void> apply(Void r2) {
                return CommonUtil.asyncSleep(duration, CommonSysApiInverseRequests.this.contextUtil.getDefaultExecutor());
            }
        });
    }

    @Override // api.requests.com.neusoft.common.commonsysapiinverse.ICommonSysApiInverseRequests
    public CompletableFuture<Void> fireSetSystemTimeChange(ReqSetSystemTimeChange.Arguments arguments) {
        ReqSetSystemTimeChange reqSetSystemTimeChange = new ReqSetSystemTimeChange();
        reqSetSystemTimeChange.setArguments(arguments);
        reqSetSystemTimeChange.setResponse(null);
        String str = this.component;
        if (str != null) {
            reqSetSystemTimeChange.setComponent(str);
        }
        return fireSetSystemTimeChange(reqSetSystemTimeChange);
    }

    @Override // api.requests.com.neusoft.common.commonsysapiinverse.ICommonSysApiInverseRequests
    public CompletableFuture<Void> fireSetSystemTimeChange(ReqSetSystemTimeChange.Arguments arguments, final Duration duration) {
        return fireSetSystemTimeChange(arguments).thenCompose((Function<? super Void, ? extends CompletionStage<U>>) new Function<Void, CompletableFuture<Void>>() { // from class: api.requests.com.neusoft.common.commonsysapiinverse.CommonSysApiInverseRequests.274
            @Override // java9.util.function.Function
            public CompletableFuture<Void> apply(Void r2) {
                return CommonUtil.asyncSleep(duration, CommonSysApiInverseRequests.this.contextUtil.getDefaultExecutor());
            }
        });
    }

    @Override // api.requests.com.neusoft.common.commonsysapiinverse.ICommonSysApiInverseRequests
    public CompletableFuture<Void> fireSetSystemTimeFormat(ReqSetSystemTimeFormat reqSetSystemTimeFormat) {
        reqSetSystemTimeFormat.setResponse(null);
        String str = this.component;
        if (str != null) {
            reqSetSystemTimeFormat.setComponent(str);
        }
        return Communication.request(this.contextUtil.createFromClass(reqSetSystemTimeFormat)).thenCompose(new Function<Object, CompletableFuture<Void>>() { // from class: api.requests.com.neusoft.common.commonsysapiinverse.CommonSysApiInverseRequests.275
            @Override // java9.util.function.Function
            public CompletableFuture<Void> apply(Object obj) {
                return CommonSysApiInverseRequests.completedVoidFuture;
            }
        });
    }

    @Override // api.requests.com.neusoft.common.commonsysapiinverse.ICommonSysApiInverseRequests
    public CompletableFuture<Void> fireSetSystemTimeFormat(ReqSetSystemTimeFormat reqSetSystemTimeFormat, final Duration duration) {
        return fireSetSystemTimeFormat(reqSetSystemTimeFormat).thenCompose((Function<? super Void, ? extends CompletionStage<U>>) new Function<Void, CompletableFuture<Void>>() { // from class: api.requests.com.neusoft.common.commonsysapiinverse.CommonSysApiInverseRequests.276
            @Override // java9.util.function.Function
            public CompletableFuture<Void> apply(Void r2) {
                return CommonUtil.asyncSleep(duration, CommonSysApiInverseRequests.this.contextUtil.getDefaultExecutor());
            }
        });
    }

    @Override // api.requests.com.neusoft.common.commonsysapiinverse.ICommonSysApiInverseRequests
    public CompletableFuture<Void> fireSetSystemTimeFormat(ReqSetSystemTimeFormat.Arguments arguments) {
        ReqSetSystemTimeFormat reqSetSystemTimeFormat = new ReqSetSystemTimeFormat();
        reqSetSystemTimeFormat.setArguments(arguments);
        reqSetSystemTimeFormat.setResponse(null);
        String str = this.component;
        if (str != null) {
            reqSetSystemTimeFormat.setComponent(str);
        }
        return fireSetSystemTimeFormat(reqSetSystemTimeFormat);
    }

    @Override // api.requests.com.neusoft.common.commonsysapiinverse.ICommonSysApiInverseRequests
    public CompletableFuture<Void> fireSetSystemTimeFormat(ReqSetSystemTimeFormat.Arguments arguments, final Duration duration) {
        return fireSetSystemTimeFormat(arguments).thenCompose((Function<? super Void, ? extends CompletionStage<U>>) new Function<Void, CompletableFuture<Void>>() { // from class: api.requests.com.neusoft.common.commonsysapiinverse.CommonSysApiInverseRequests.277
            @Override // java9.util.function.Function
            public CompletableFuture<Void> apply(Void r2) {
                return CommonUtil.asyncSleep(duration, CommonSysApiInverseRequests.this.contextUtil.getDefaultExecutor());
            }
        });
    }

    @Override // api.requests.com.neusoft.common.commonsysapiinverse.ICommonSysApiInverseRequests
    public CompletableFuture<Void> fireSetSystemTimeZoneOffset(ReqSetSystemTimeZoneOffset reqSetSystemTimeZoneOffset) {
        reqSetSystemTimeZoneOffset.setResponse(null);
        String str = this.component;
        if (str != null) {
            reqSetSystemTimeZoneOffset.setComponent(str);
        }
        return Communication.request(this.contextUtil.createFromClass(reqSetSystemTimeZoneOffset)).thenCompose(new Function<Object, CompletableFuture<Void>>() { // from class: api.requests.com.neusoft.common.commonsysapiinverse.CommonSysApiInverseRequests.278
            @Override // java9.util.function.Function
            public CompletableFuture<Void> apply(Object obj) {
                return CommonSysApiInverseRequests.completedVoidFuture;
            }
        });
    }

    @Override // api.requests.com.neusoft.common.commonsysapiinverse.ICommonSysApiInverseRequests
    public CompletableFuture<Void> fireSetSystemTimeZoneOffset(ReqSetSystemTimeZoneOffset reqSetSystemTimeZoneOffset, final Duration duration) {
        return fireSetSystemTimeZoneOffset(reqSetSystemTimeZoneOffset).thenCompose((Function<? super Void, ? extends CompletionStage<U>>) new Function<Void, CompletableFuture<Void>>() { // from class: api.requests.com.neusoft.common.commonsysapiinverse.CommonSysApiInverseRequests.279
            @Override // java9.util.function.Function
            public CompletableFuture<Void> apply(Void r2) {
                return CommonUtil.asyncSleep(duration, CommonSysApiInverseRequests.this.contextUtil.getDefaultExecutor());
            }
        });
    }

    @Override // api.requests.com.neusoft.common.commonsysapiinverse.ICommonSysApiInverseRequests
    public CompletableFuture<Void> fireSetSystemTimeZoneOffset(ReqSetSystemTimeZoneOffset.Arguments arguments) {
        ReqSetSystemTimeZoneOffset reqSetSystemTimeZoneOffset = new ReqSetSystemTimeZoneOffset();
        reqSetSystemTimeZoneOffset.setArguments(arguments);
        reqSetSystemTimeZoneOffset.setResponse(null);
        String str = this.component;
        if (str != null) {
            reqSetSystemTimeZoneOffset.setComponent(str);
        }
        return fireSetSystemTimeZoneOffset(reqSetSystemTimeZoneOffset);
    }

    @Override // api.requests.com.neusoft.common.commonsysapiinverse.ICommonSysApiInverseRequests
    public CompletableFuture<Void> fireSetSystemTimeZoneOffset(ReqSetSystemTimeZoneOffset.Arguments arguments, final Duration duration) {
        return fireSetSystemTimeZoneOffset(arguments).thenCompose((Function<? super Void, ? extends CompletionStage<U>>) new Function<Void, CompletableFuture<Void>>() { // from class: api.requests.com.neusoft.common.commonsysapiinverse.CommonSysApiInverseRequests.280
            @Override // java9.util.function.Function
            public CompletableFuture<Void> apply(Void r2) {
                return CommonUtil.asyncSleep(duration, CommonSysApiInverseRequests.this.contextUtil.getDefaultExecutor());
            }
        });
    }

    @Override // api.requests.com.neusoft.common.commonsysapiinverse.ICommonSysApiInverseRequests
    public CompletableFuture<Void> fireSetInternetSwitchStatus(ReqSetInternetSwitchStatus reqSetInternetSwitchStatus) {
        reqSetInternetSwitchStatus.setResponse(null);
        String str = this.component;
        if (str != null) {
            reqSetInternetSwitchStatus.setComponent(str);
        }
        return Communication.request(this.contextUtil.createFromClass(reqSetInternetSwitchStatus)).thenCompose(new Function<Object, CompletableFuture<Void>>() { // from class: api.requests.com.neusoft.common.commonsysapiinverse.CommonSysApiInverseRequests.281
            @Override // java9.util.function.Function
            public CompletableFuture<Void> apply(Object obj) {
                return CommonSysApiInverseRequests.completedVoidFuture;
            }
        });
    }

    @Override // api.requests.com.neusoft.common.commonsysapiinverse.ICommonSysApiInverseRequests
    public CompletableFuture<Void> fireSetInternetSwitchStatus(ReqSetInternetSwitchStatus reqSetInternetSwitchStatus, final Duration duration) {
        return fireSetInternetSwitchStatus(reqSetInternetSwitchStatus).thenCompose((Function<? super Void, ? extends CompletionStage<U>>) new Function<Void, CompletableFuture<Void>>() { // from class: api.requests.com.neusoft.common.commonsysapiinverse.CommonSysApiInverseRequests.282
            @Override // java9.util.function.Function
            public CompletableFuture<Void> apply(Void r2) {
                return CommonUtil.asyncSleep(duration, CommonSysApiInverseRequests.this.contextUtil.getDefaultExecutor());
            }
        });
    }

    @Override // api.requests.com.neusoft.common.commonsysapiinverse.ICommonSysApiInverseRequests
    public CompletableFuture<Void> fireSetInternetSwitchStatus(ReqSetInternetSwitchStatus.Arguments arguments) {
        ReqSetInternetSwitchStatus reqSetInternetSwitchStatus = new ReqSetInternetSwitchStatus();
        reqSetInternetSwitchStatus.setArguments(arguments);
        reqSetInternetSwitchStatus.setResponse(null);
        String str = this.component;
        if (str != null) {
            reqSetInternetSwitchStatus.setComponent(str);
        }
        return fireSetInternetSwitchStatus(reqSetInternetSwitchStatus);
    }

    @Override // api.requests.com.neusoft.common.commonsysapiinverse.ICommonSysApiInverseRequests
    public CompletableFuture<Void> fireSetInternetSwitchStatus(ReqSetInternetSwitchStatus.Arguments arguments, final Duration duration) {
        return fireSetInternetSwitchStatus(arguments).thenCompose((Function<? super Void, ? extends CompletionStage<U>>) new Function<Void, CompletableFuture<Void>>() { // from class: api.requests.com.neusoft.common.commonsysapiinverse.CommonSysApiInverseRequests.283
            @Override // java9.util.function.Function
            public CompletableFuture<Void> apply(Void r2) {
                return CommonUtil.asyncSleep(duration, CommonSysApiInverseRequests.this.contextUtil.getDefaultExecutor());
            }
        });
    }

    @Override // api.requests.com.neusoft.common.commonsysapiinverse.ICommonSysApiInverseRequests
    public CompletableFuture<Void> fireSetBrightnessValue(ReqSetBrightnessValue reqSetBrightnessValue) {
        reqSetBrightnessValue.setResponse(null);
        String str = this.component;
        if (str != null) {
            reqSetBrightnessValue.setComponent(str);
        }
        return Communication.request(this.contextUtil.createFromClass(reqSetBrightnessValue)).thenCompose(new Function<Object, CompletableFuture<Void>>() { // from class: api.requests.com.neusoft.common.commonsysapiinverse.CommonSysApiInverseRequests.284
            @Override // java9.util.function.Function
            public CompletableFuture<Void> apply(Object obj) {
                return CommonSysApiInverseRequests.completedVoidFuture;
            }
        });
    }

    @Override // api.requests.com.neusoft.common.commonsysapiinverse.ICommonSysApiInverseRequests
    public CompletableFuture<Void> fireSetBrightnessValue(ReqSetBrightnessValue reqSetBrightnessValue, final Duration duration) {
        return fireSetBrightnessValue(reqSetBrightnessValue).thenCompose((Function<? super Void, ? extends CompletionStage<U>>) new Function<Void, CompletableFuture<Void>>() { // from class: api.requests.com.neusoft.common.commonsysapiinverse.CommonSysApiInverseRequests.285
            @Override // java9.util.function.Function
            public CompletableFuture<Void> apply(Void r2) {
                return CommonUtil.asyncSleep(duration, CommonSysApiInverseRequests.this.contextUtil.getDefaultExecutor());
            }
        });
    }

    @Override // api.requests.com.neusoft.common.commonsysapiinverse.ICommonSysApiInverseRequests
    public CompletableFuture<Void> fireSetBrightnessValue(ReqSetBrightnessValue.Arguments arguments) {
        ReqSetBrightnessValue reqSetBrightnessValue = new ReqSetBrightnessValue();
        reqSetBrightnessValue.setArguments(arguments);
        reqSetBrightnessValue.setResponse(null);
        String str = this.component;
        if (str != null) {
            reqSetBrightnessValue.setComponent(str);
        }
        return fireSetBrightnessValue(reqSetBrightnessValue);
    }

    @Override // api.requests.com.neusoft.common.commonsysapiinverse.ICommonSysApiInverseRequests
    public CompletableFuture<Void> fireSetBrightnessValue(ReqSetBrightnessValue.Arguments arguments, final Duration duration) {
        return fireSetBrightnessValue(arguments).thenCompose((Function<? super Void, ? extends CompletionStage<U>>) new Function<Void, CompletableFuture<Void>>() { // from class: api.requests.com.neusoft.common.commonsysapiinverse.CommonSysApiInverseRequests.286
            @Override // java9.util.function.Function
            public CompletableFuture<Void> apply(Void r2) {
                return CommonUtil.asyncSleep(duration, CommonSysApiInverseRequests.this.contextUtil.getDefaultExecutor());
            }
        });
    }

    @Override // api.requests.com.neusoft.common.commonsysapiinverse.ICommonSysApiInverseRequests
    public CompletableFuture<Void> fireSetOnKeyDownValue(ReqSetOnKeyDownValue reqSetOnKeyDownValue) {
        reqSetOnKeyDownValue.setResponse(null);
        String str = this.component;
        if (str != null) {
            reqSetOnKeyDownValue.setComponent(str);
        }
        return Communication.request(this.contextUtil.createFromClass(reqSetOnKeyDownValue)).thenCompose(new Function<Object, CompletableFuture<Void>>() { // from class: api.requests.com.neusoft.common.commonsysapiinverse.CommonSysApiInverseRequests.287
            @Override // java9.util.function.Function
            public CompletableFuture<Void> apply(Object obj) {
                return CommonSysApiInverseRequests.completedVoidFuture;
            }
        });
    }

    @Override // api.requests.com.neusoft.common.commonsysapiinverse.ICommonSysApiInverseRequests
    public CompletableFuture<Void> fireSetOnKeyDownValue(ReqSetOnKeyDownValue reqSetOnKeyDownValue, final Duration duration) {
        return fireSetOnKeyDownValue(reqSetOnKeyDownValue).thenCompose((Function<? super Void, ? extends CompletionStage<U>>) new Function<Void, CompletableFuture<Void>>() { // from class: api.requests.com.neusoft.common.commonsysapiinverse.CommonSysApiInverseRequests.288
            @Override // java9.util.function.Function
            public CompletableFuture<Void> apply(Void r2) {
                return CommonUtil.asyncSleep(duration, CommonSysApiInverseRequests.this.contextUtil.getDefaultExecutor());
            }
        });
    }

    @Override // api.requests.com.neusoft.common.commonsysapiinverse.ICommonSysApiInverseRequests
    public CompletableFuture<Void> fireSetOnKeyDownValue(ReqSetOnKeyDownValue.Arguments arguments) {
        ReqSetOnKeyDownValue reqSetOnKeyDownValue = new ReqSetOnKeyDownValue();
        reqSetOnKeyDownValue.setArguments(arguments);
        reqSetOnKeyDownValue.setResponse(null);
        String str = this.component;
        if (str != null) {
            reqSetOnKeyDownValue.setComponent(str);
        }
        return fireSetOnKeyDownValue(reqSetOnKeyDownValue);
    }

    @Override // api.requests.com.neusoft.common.commonsysapiinverse.ICommonSysApiInverseRequests
    public CompletableFuture<Void> fireSetOnKeyDownValue(ReqSetOnKeyDownValue.Arguments arguments, final Duration duration) {
        return fireSetOnKeyDownValue(arguments).thenCompose((Function<? super Void, ? extends CompletionStage<U>>) new Function<Void, CompletableFuture<Void>>() { // from class: api.requests.com.neusoft.common.commonsysapiinverse.CommonSysApiInverseRequests.289
            @Override // java9.util.function.Function
            public CompletableFuture<Void> apply(Void r2) {
                return CommonUtil.asyncSleep(duration, CommonSysApiInverseRequests.this.contextUtil.getDefaultExecutor());
            }
        });
    }

    @Override // api.requests.com.neusoft.common.commonsysapiinverse.ICommonSysApiInverseRequests
    public CompletableFuture<Void> fireSetVehicleBrakingStatus(ReqSetVehicleBrakingStatus reqSetVehicleBrakingStatus) {
        reqSetVehicleBrakingStatus.setResponse(null);
        String str = this.component;
        if (str != null) {
            reqSetVehicleBrakingStatus.setComponent(str);
        }
        return Communication.request(this.contextUtil.createFromClass(reqSetVehicleBrakingStatus)).thenCompose(new Function<Object, CompletableFuture<Void>>() { // from class: api.requests.com.neusoft.common.commonsysapiinverse.CommonSysApiInverseRequests.290
            @Override // java9.util.function.Function
            public CompletableFuture<Void> apply(Object obj) {
                return CommonSysApiInverseRequests.completedVoidFuture;
            }
        });
    }

    @Override // api.requests.com.neusoft.common.commonsysapiinverse.ICommonSysApiInverseRequests
    public CompletableFuture<Void> fireSetVehicleBrakingStatus(ReqSetVehicleBrakingStatus reqSetVehicleBrakingStatus, final Duration duration) {
        return fireSetVehicleBrakingStatus(reqSetVehicleBrakingStatus).thenCompose((Function<? super Void, ? extends CompletionStage<U>>) new Function<Void, CompletableFuture<Void>>() { // from class: api.requests.com.neusoft.common.commonsysapiinverse.CommonSysApiInverseRequests.291
            @Override // java9.util.function.Function
            public CompletableFuture<Void> apply(Void r2) {
                return CommonUtil.asyncSleep(duration, CommonSysApiInverseRequests.this.contextUtil.getDefaultExecutor());
            }
        });
    }

    @Override // api.requests.com.neusoft.common.commonsysapiinverse.ICommonSysApiInverseRequests
    public CompletableFuture<Void> fireSetVehicleBrakingStatus(ReqSetVehicleBrakingStatus.Arguments arguments) {
        ReqSetVehicleBrakingStatus reqSetVehicleBrakingStatus = new ReqSetVehicleBrakingStatus();
        reqSetVehicleBrakingStatus.setArguments(arguments);
        reqSetVehicleBrakingStatus.setResponse(null);
        String str = this.component;
        if (str != null) {
            reqSetVehicleBrakingStatus.setComponent(str);
        }
        return fireSetVehicleBrakingStatus(reqSetVehicleBrakingStatus);
    }

    @Override // api.requests.com.neusoft.common.commonsysapiinverse.ICommonSysApiInverseRequests
    public CompletableFuture<Void> fireSetVehicleBrakingStatus(ReqSetVehicleBrakingStatus.Arguments arguments, final Duration duration) {
        return fireSetVehicleBrakingStatus(arguments).thenCompose((Function<? super Void, ? extends CompletionStage<U>>) new Function<Void, CompletableFuture<Void>>() { // from class: api.requests.com.neusoft.common.commonsysapiinverse.CommonSysApiInverseRequests.292
            @Override // java9.util.function.Function
            public CompletableFuture<Void> apply(Void r2) {
                return CommonUtil.asyncSleep(duration, CommonSysApiInverseRequests.this.contextUtil.getDefaultExecutor());
            }
        });
    }

    @Override // api.requests.com.neusoft.common.commonsysapiinverse.ICommonSysApiInverseRequests
    public CompletableFuture<Void> fireSetExtCloudServerStatus(ReqSetExtCloudServerStatus reqSetExtCloudServerStatus) {
        reqSetExtCloudServerStatus.setResponse(null);
        String str = this.component;
        if (str != null) {
            reqSetExtCloudServerStatus.setComponent(str);
        }
        return Communication.request(this.contextUtil.createFromClass(reqSetExtCloudServerStatus)).thenCompose(new Function<Object, CompletableFuture<Void>>() { // from class: api.requests.com.neusoft.common.commonsysapiinverse.CommonSysApiInverseRequests.293
            @Override // java9.util.function.Function
            public CompletableFuture<Void> apply(Object obj) {
                return CommonSysApiInverseRequests.completedVoidFuture;
            }
        });
    }

    @Override // api.requests.com.neusoft.common.commonsysapiinverse.ICommonSysApiInverseRequests
    public CompletableFuture<Void> fireSetExtCloudServerStatus(ReqSetExtCloudServerStatus reqSetExtCloudServerStatus, final Duration duration) {
        return fireSetExtCloudServerStatus(reqSetExtCloudServerStatus).thenCompose((Function<? super Void, ? extends CompletionStage<U>>) new Function<Void, CompletableFuture<Void>>() { // from class: api.requests.com.neusoft.common.commonsysapiinverse.CommonSysApiInverseRequests.294
            @Override // java9.util.function.Function
            public CompletableFuture<Void> apply(Void r2) {
                return CommonUtil.asyncSleep(duration, CommonSysApiInverseRequests.this.contextUtil.getDefaultExecutor());
            }
        });
    }

    @Override // api.requests.com.neusoft.common.commonsysapiinverse.ICommonSysApiInverseRequests
    public CompletableFuture<Void> fireSetExtCloudServerStatus(ReqSetExtCloudServerStatus.Arguments arguments) {
        ReqSetExtCloudServerStatus reqSetExtCloudServerStatus = new ReqSetExtCloudServerStatus();
        reqSetExtCloudServerStatus.setArguments(arguments);
        reqSetExtCloudServerStatus.setResponse(null);
        String str = this.component;
        if (str != null) {
            reqSetExtCloudServerStatus.setComponent(str);
        }
        return fireSetExtCloudServerStatus(reqSetExtCloudServerStatus);
    }

    @Override // api.requests.com.neusoft.common.commonsysapiinverse.ICommonSysApiInverseRequests
    public CompletableFuture<Void> fireSetExtCloudServerStatus(ReqSetExtCloudServerStatus.Arguments arguments, final Duration duration) {
        return fireSetExtCloudServerStatus(arguments).thenCompose((Function<? super Void, ? extends CompletionStage<U>>) new Function<Void, CompletableFuture<Void>>() { // from class: api.requests.com.neusoft.common.commonsysapiinverse.CommonSysApiInverseRequests.295
            @Override // java9.util.function.Function
            public CompletableFuture<Void> apply(Void r2) {
                return CommonUtil.asyncSleep(duration, CommonSysApiInverseRequests.this.contextUtil.getDefaultExecutor());
            }
        });
    }

    @Override // api.requests.com.neusoft.common.commonsysapiinverse.ICommonSysApiInverseRequests
    public CompletableFuture<Void> fireOnDeleteAllDBData(ReqOnDeleteAllDBData reqOnDeleteAllDBData) {
        reqOnDeleteAllDBData.setResponse(null);
        String str = this.component;
        if (str != null) {
            reqOnDeleteAllDBData.setComponent(str);
        }
        return Communication.request(this.contextUtil.createFromClass(reqOnDeleteAllDBData)).thenCompose(new Function<Object, CompletableFuture<Void>>() { // from class: api.requests.com.neusoft.common.commonsysapiinverse.CommonSysApiInverseRequests.296
            @Override // java9.util.function.Function
            public CompletableFuture<Void> apply(Object obj) {
                return CommonSysApiInverseRequests.completedVoidFuture;
            }
        });
    }

    @Override // api.requests.com.neusoft.common.commonsysapiinverse.ICommonSysApiInverseRequests
    public CompletableFuture<Void> fireOnDeleteAllDBData(ReqOnDeleteAllDBData reqOnDeleteAllDBData, final Duration duration) {
        return fireOnDeleteAllDBData(reqOnDeleteAllDBData).thenCompose((Function<? super Void, ? extends CompletionStage<U>>) new Function<Void, CompletableFuture<Void>>() { // from class: api.requests.com.neusoft.common.commonsysapiinverse.CommonSysApiInverseRequests.297
            @Override // java9.util.function.Function
            public CompletableFuture<Void> apply(Void r2) {
                return CommonUtil.asyncSleep(duration, CommonSysApiInverseRequests.this.contextUtil.getDefaultExecutor());
            }
        });
    }

    @Override // api.requests.com.neusoft.common.commonsysapiinverse.ICommonSysApiInverseRequests
    public CompletableFuture<Void> fireOnDeleteAllDBData(ReqOnDeleteAllDBData.Arguments arguments) {
        ReqOnDeleteAllDBData reqOnDeleteAllDBData = new ReqOnDeleteAllDBData();
        reqOnDeleteAllDBData.setArguments(arguments);
        reqOnDeleteAllDBData.setResponse(null);
        String str = this.component;
        if (str != null) {
            reqOnDeleteAllDBData.setComponent(str);
        }
        return fireOnDeleteAllDBData(reqOnDeleteAllDBData);
    }

    @Override // api.requests.com.neusoft.common.commonsysapiinverse.ICommonSysApiInverseRequests
    public CompletableFuture<Void> fireOnDeleteAllDBData(ReqOnDeleteAllDBData.Arguments arguments, final Duration duration) {
        return fireOnDeleteAllDBData(arguments).thenCompose((Function<? super Void, ? extends CompletionStage<U>>) new Function<Void, CompletableFuture<Void>>() { // from class: api.requests.com.neusoft.common.commonsysapiinverse.CommonSysApiInverseRequests.298
            @Override // java9.util.function.Function
            public CompletableFuture<Void> apply(Void r2) {
                return CommonUtil.asyncSleep(duration, CommonSysApiInverseRequests.this.contextUtil.getDefaultExecutor());
            }
        });
    }

    @Override // api.requests.com.neusoft.common.commonsysapiinverse.ICommonSysApiInverseRequests
    public CompletableFuture<Void> fireOnDBDataIntegrityCheck(ReqOnDBDataIntegrityCheck reqOnDBDataIntegrityCheck) {
        reqOnDBDataIntegrityCheck.setResponse(null);
        String str = this.component;
        if (str != null) {
            reqOnDBDataIntegrityCheck.setComponent(str);
        }
        return Communication.request(this.contextUtil.createFromClass(reqOnDBDataIntegrityCheck)).thenCompose(new Function<Object, CompletableFuture<Void>>() { // from class: api.requests.com.neusoft.common.commonsysapiinverse.CommonSysApiInverseRequests.299
            @Override // java9.util.function.Function
            public CompletableFuture<Void> apply(Object obj) {
                return CommonSysApiInverseRequests.completedVoidFuture;
            }
        });
    }

    @Override // api.requests.com.neusoft.common.commonsysapiinverse.ICommonSysApiInverseRequests
    public CompletableFuture<Void> fireOnDBDataIntegrityCheck(ReqOnDBDataIntegrityCheck reqOnDBDataIntegrityCheck, final Duration duration) {
        return fireOnDBDataIntegrityCheck(reqOnDBDataIntegrityCheck).thenCompose((Function<? super Void, ? extends CompletionStage<U>>) new Function<Void, CompletableFuture<Void>>() { // from class: api.requests.com.neusoft.common.commonsysapiinverse.CommonSysApiInverseRequests.300
            @Override // java9.util.function.Function
            public CompletableFuture<Void> apply(Void r2) {
                return CommonUtil.asyncSleep(duration, CommonSysApiInverseRequests.this.contextUtil.getDefaultExecutor());
            }
        });
    }

    @Override // api.requests.com.neusoft.common.commonsysapiinverse.ICommonSysApiInverseRequests
    public CompletableFuture<Void> fireOnDBDataIntegrityCheck(ReqOnDBDataIntegrityCheck.Arguments arguments) {
        ReqOnDBDataIntegrityCheck reqOnDBDataIntegrityCheck = new ReqOnDBDataIntegrityCheck();
        reqOnDBDataIntegrityCheck.setArguments(arguments);
        reqOnDBDataIntegrityCheck.setResponse(null);
        String str = this.component;
        if (str != null) {
            reqOnDBDataIntegrityCheck.setComponent(str);
        }
        return fireOnDBDataIntegrityCheck(reqOnDBDataIntegrityCheck);
    }

    @Override // api.requests.com.neusoft.common.commonsysapiinverse.ICommonSysApiInverseRequests
    public CompletableFuture<Void> fireOnDBDataIntegrityCheck(ReqOnDBDataIntegrityCheck.Arguments arguments, final Duration duration) {
        return fireOnDBDataIntegrityCheck(arguments).thenCompose((Function<? super Void, ? extends CompletionStage<U>>) new Function<Void, CompletableFuture<Void>>() { // from class: api.requests.com.neusoft.common.commonsysapiinverse.CommonSysApiInverseRequests.301
            @Override // java9.util.function.Function
            public CompletableFuture<Void> apply(Void r2) {
                return CommonUtil.asyncSleep(duration, CommonSysApiInverseRequests.this.contextUtil.getDefaultExecutor());
            }
        });
    }

    @Override // api.requests.com.neusoft.common.commonsysapiinverse.ICommonSysApiInverseRequests
    public CompletableFuture<ResGetNaviPresentationMgs> requestGetNaviPresentationMgs(ReqGetNaviPresentationMgs reqGetNaviPresentationMgs) {
        if (reqGetNaviPresentationMgs.getResponse() == null) {
            reqGetNaviPresentationMgs.setResponse(new ResGetNaviPresentationMgs());
        }
        String str = this.component;
        if (str != null) {
            reqGetNaviPresentationMgs.setComponent(str);
            reqGetNaviPresentationMgs.getResponse().setComponent(this.component);
        }
        return Communication.request(this.contextUtil.createFromClass(reqGetNaviPresentationMgs));
    }

    @Override // api.requests.com.neusoft.common.commonsysapiinverse.ICommonSysApiInverseRequests
    public CompletableFuture<ResGetNaviPresentationMgs> requestGetNaviPresentationMgs(ReqGetNaviPresentationMgs reqGetNaviPresentationMgs, final Duration duration) {
        return requestGetNaviPresentationMgs(reqGetNaviPresentationMgs).thenCompose((Function<? super ResGetNaviPresentationMgs, ? extends CompletionStage<U>>) new Function<ResGetNaviPresentationMgs, CompletableFuture<ResGetNaviPresentationMgs>>() { // from class: api.requests.com.neusoft.common.commonsysapiinverse.CommonSysApiInverseRequests.302
            @Override // java9.util.function.Function
            public CompletableFuture<ResGetNaviPresentationMgs> apply(ResGetNaviPresentationMgs resGetNaviPresentationMgs) {
                return CommonUtil.delayAsync(resGetNaviPresentationMgs, duration, CommonSysApiInverseRequests.this.contextUtil.getDefaultExecutor());
            }
        });
    }

    @Override // api.requests.com.neusoft.common.commonsysapiinverse.ICommonSysApiInverseRequests
    public CompletableFuture<ResGetNaviPresentationMgs> requestGetNaviPresentationMgs() {
        return requestGetNaviPresentationMgs(new ReqGetNaviPresentationMgs());
    }

    @Override // api.requests.com.neusoft.common.commonsysapiinverse.ICommonSysApiInverseRequests
    public CompletableFuture<ResGetNaviPresentationMgs> requestGetNaviPresentationMgs(Duration duration) {
        return requestGetNaviPresentationMgs(new ReqGetNaviPresentationMgs(), duration);
    }

    @Override // api.requests.com.neusoft.common.commonsysapiinverse.ICommonSysApiInverseRequests
    public CompletableFuture<Void> fireGetNaviPresentationMgs(ReqGetNaviPresentationMgs reqGetNaviPresentationMgs) {
        reqGetNaviPresentationMgs.setResponse(null);
        String str = this.component;
        if (str != null) {
            reqGetNaviPresentationMgs.setComponent(str);
        }
        return Communication.request(this.contextUtil.createFromClass(reqGetNaviPresentationMgs)).thenCompose(new Function<Object, CompletableFuture<Void>>() { // from class: api.requests.com.neusoft.common.commonsysapiinverse.CommonSysApiInverseRequests.303
            @Override // java9.util.function.Function
            public CompletableFuture<Void> apply(Object obj) {
                return CommonSysApiInverseRequests.completedVoidFuture;
            }
        });
    }

    @Override // api.requests.com.neusoft.common.commonsysapiinverse.ICommonSysApiInverseRequests
    public CompletableFuture<Void> fireGetNaviPresentationMgs(ReqGetNaviPresentationMgs reqGetNaviPresentationMgs, final Duration duration) {
        return fireGetNaviPresentationMgs(reqGetNaviPresentationMgs).thenCompose((Function<? super Void, ? extends CompletionStage<U>>) new Function<Void, CompletableFuture<Void>>() { // from class: api.requests.com.neusoft.common.commonsysapiinverse.CommonSysApiInverseRequests.304
            @Override // java9.util.function.Function
            public CompletableFuture<Void> apply(Void r2) {
                return CommonUtil.asyncSleep(duration, CommonSysApiInverseRequests.this.contextUtil.getDefaultExecutor());
            }
        });
    }

    @Override // api.requests.com.neusoft.common.commonsysapiinverse.ICommonSysApiInverseRequests
    public CompletableFuture<Void> fireGetNaviPresentationMgs() {
        return fireGetNaviPresentationMgs(new ReqGetNaviPresentationMgs());
    }

    @Override // api.requests.com.neusoft.common.commonsysapiinverse.ICommonSysApiInverseRequests
    public CompletableFuture<Void> fireGetNaviPresentationMgs(final Duration duration) {
        return fireGetNaviPresentationMgs(new ReqGetNaviPresentationMgs()).thenCompose((Function<? super Void, ? extends CompletionStage<U>>) new Function<Void, CompletableFuture<Void>>() { // from class: api.requests.com.neusoft.common.commonsysapiinverse.CommonSysApiInverseRequests.305
            @Override // java9.util.function.Function
            public CompletableFuture<Void> apply(Void r2) {
                return CommonUtil.asyncSleep(duration, CommonSysApiInverseRequests.this.contextUtil.getDefaultExecutor());
            }
        });
    }

    @Override // api.requests.com.neusoft.common.commonsysapiinverse.ICommonSysApiInverseRequests
    public CompletableFuture<Void> fireSwitchNaviLayoutDirection(ReqSwitchNaviLayoutDirection reqSwitchNaviLayoutDirection) {
        reqSwitchNaviLayoutDirection.setResponse(null);
        String str = this.component;
        if (str != null) {
            reqSwitchNaviLayoutDirection.setComponent(str);
        }
        return Communication.request(this.contextUtil.createFromClass(reqSwitchNaviLayoutDirection)).thenCompose(new Function<Object, CompletableFuture<Void>>() { // from class: api.requests.com.neusoft.common.commonsysapiinverse.CommonSysApiInverseRequests.306
            @Override // java9.util.function.Function
            public CompletableFuture<Void> apply(Object obj) {
                return CommonSysApiInverseRequests.completedVoidFuture;
            }
        });
    }

    @Override // api.requests.com.neusoft.common.commonsysapiinverse.ICommonSysApiInverseRequests
    public CompletableFuture<Void> fireSwitchNaviLayoutDirection(ReqSwitchNaviLayoutDirection reqSwitchNaviLayoutDirection, final Duration duration) {
        return fireSwitchNaviLayoutDirection(reqSwitchNaviLayoutDirection).thenCompose((Function<? super Void, ? extends CompletionStage<U>>) new Function<Void, CompletableFuture<Void>>() { // from class: api.requests.com.neusoft.common.commonsysapiinverse.CommonSysApiInverseRequests.307
            @Override // java9.util.function.Function
            public CompletableFuture<Void> apply(Void r2) {
                return CommonUtil.asyncSleep(duration, CommonSysApiInverseRequests.this.contextUtil.getDefaultExecutor());
            }
        });
    }

    @Override // api.requests.com.neusoft.common.commonsysapiinverse.ICommonSysApiInverseRequests
    public CompletableFuture<Void> fireSwitchNaviLayoutDirection(ReqSwitchNaviLayoutDirection.Arguments arguments) {
        ReqSwitchNaviLayoutDirection reqSwitchNaviLayoutDirection = new ReqSwitchNaviLayoutDirection();
        reqSwitchNaviLayoutDirection.setArguments(arguments);
        reqSwitchNaviLayoutDirection.setResponse(null);
        String str = this.component;
        if (str != null) {
            reqSwitchNaviLayoutDirection.setComponent(str);
        }
        return fireSwitchNaviLayoutDirection(reqSwitchNaviLayoutDirection);
    }

    @Override // api.requests.com.neusoft.common.commonsysapiinverse.ICommonSysApiInverseRequests
    public CompletableFuture<Void> fireSwitchNaviLayoutDirection(ReqSwitchNaviLayoutDirection.Arguments arguments, final Duration duration) {
        return fireSwitchNaviLayoutDirection(arguments).thenCompose((Function<? super Void, ? extends CompletionStage<U>>) new Function<Void, CompletableFuture<Void>>() { // from class: api.requests.com.neusoft.common.commonsysapiinverse.CommonSysApiInverseRequests.308
            @Override // java9.util.function.Function
            public CompletableFuture<Void> apply(Void r2) {
                return CommonUtil.asyncSleep(duration, CommonSysApiInverseRequests.this.contextUtil.getDefaultExecutor());
            }
        });
    }

    @Override // api.requests.com.neusoft.common.commonsysapiinverse.ICommonSysApiInverseRequests
    public CompletableFuture<Void> fireOnExtractFiles(ReqOnExtractFiles reqOnExtractFiles) {
        reqOnExtractFiles.setResponse(null);
        String str = this.component;
        if (str != null) {
            reqOnExtractFiles.setComponent(str);
        }
        return Communication.request(this.contextUtil.createFromClass(reqOnExtractFiles)).thenCompose(new Function<Object, CompletableFuture<Void>>() { // from class: api.requests.com.neusoft.common.commonsysapiinverse.CommonSysApiInverseRequests.309
            @Override // java9.util.function.Function
            public CompletableFuture<Void> apply(Object obj) {
                return CommonSysApiInverseRequests.completedVoidFuture;
            }
        });
    }

    @Override // api.requests.com.neusoft.common.commonsysapiinverse.ICommonSysApiInverseRequests
    public CompletableFuture<Void> fireOnExtractFiles(ReqOnExtractFiles reqOnExtractFiles, final Duration duration) {
        return fireOnExtractFiles(reqOnExtractFiles).thenCompose((Function<? super Void, ? extends CompletionStage<U>>) new Function<Void, CompletableFuture<Void>>() { // from class: api.requests.com.neusoft.common.commonsysapiinverse.CommonSysApiInverseRequests.310
            @Override // java9.util.function.Function
            public CompletableFuture<Void> apply(Void r2) {
                return CommonUtil.asyncSleep(duration, CommonSysApiInverseRequests.this.contextUtil.getDefaultExecutor());
            }
        });
    }

    @Override // api.requests.com.neusoft.common.commonsysapiinverse.ICommonSysApiInverseRequests
    public CompletableFuture<Void> fireOnExtractFiles(ReqOnExtractFiles.Arguments arguments) {
        ReqOnExtractFiles reqOnExtractFiles = new ReqOnExtractFiles();
        reqOnExtractFiles.setArguments(arguments);
        reqOnExtractFiles.setResponse(null);
        String str = this.component;
        if (str != null) {
            reqOnExtractFiles.setComponent(str);
        }
        return fireOnExtractFiles(reqOnExtractFiles);
    }

    @Override // api.requests.com.neusoft.common.commonsysapiinverse.ICommonSysApiInverseRequests
    public CompletableFuture<Void> fireOnExtractFiles(ReqOnExtractFiles.Arguments arguments, final Duration duration) {
        return fireOnExtractFiles(arguments).thenCompose((Function<? super Void, ? extends CompletionStage<U>>) new Function<Void, CompletableFuture<Void>>() { // from class: api.requests.com.neusoft.common.commonsysapiinverse.CommonSysApiInverseRequests.311
            @Override // java9.util.function.Function
            public CompletableFuture<Void> apply(Void r2) {
                return CommonUtil.asyncSleep(duration, CommonSysApiInverseRequests.this.contextUtil.getDefaultExecutor());
            }
        });
    }

    @Override // api.requests.com.neusoft.common.commonsysapiinverse.ICommonSysApiInverseRequests
    public CompletableFuture<Void> fireOnCopyFiles(ReqOnCopyFiles reqOnCopyFiles) {
        reqOnCopyFiles.setResponse(null);
        String str = this.component;
        if (str != null) {
            reqOnCopyFiles.setComponent(str);
        }
        return Communication.request(this.contextUtil.createFromClass(reqOnCopyFiles)).thenCompose(new Function<Object, CompletableFuture<Void>>() { // from class: api.requests.com.neusoft.common.commonsysapiinverse.CommonSysApiInverseRequests.312
            @Override // java9.util.function.Function
            public CompletableFuture<Void> apply(Object obj) {
                return CommonSysApiInverseRequests.completedVoidFuture;
            }
        });
    }

    @Override // api.requests.com.neusoft.common.commonsysapiinverse.ICommonSysApiInverseRequests
    public CompletableFuture<Void> fireOnCopyFiles(ReqOnCopyFiles reqOnCopyFiles, final Duration duration) {
        return fireOnCopyFiles(reqOnCopyFiles).thenCompose((Function<? super Void, ? extends CompletionStage<U>>) new Function<Void, CompletableFuture<Void>>() { // from class: api.requests.com.neusoft.common.commonsysapiinverse.CommonSysApiInverseRequests.313
            @Override // java9.util.function.Function
            public CompletableFuture<Void> apply(Void r2) {
                return CommonUtil.asyncSleep(duration, CommonSysApiInverseRequests.this.contextUtil.getDefaultExecutor());
            }
        });
    }

    @Override // api.requests.com.neusoft.common.commonsysapiinverse.ICommonSysApiInverseRequests
    public CompletableFuture<Void> fireOnCopyFiles(ReqOnCopyFiles.Arguments arguments) {
        ReqOnCopyFiles reqOnCopyFiles = new ReqOnCopyFiles();
        reqOnCopyFiles.setArguments(arguments);
        reqOnCopyFiles.setResponse(null);
        String str = this.component;
        if (str != null) {
            reqOnCopyFiles.setComponent(str);
        }
        return fireOnCopyFiles(reqOnCopyFiles);
    }

    @Override // api.requests.com.neusoft.common.commonsysapiinverse.ICommonSysApiInverseRequests
    public CompletableFuture<Void> fireOnCopyFiles(ReqOnCopyFiles.Arguments arguments, final Duration duration) {
        return fireOnCopyFiles(arguments).thenCompose((Function<? super Void, ? extends CompletionStage<U>>) new Function<Void, CompletableFuture<Void>>() { // from class: api.requests.com.neusoft.common.commonsysapiinverse.CommonSysApiInverseRequests.314
            @Override // java9.util.function.Function
            public CompletableFuture<Void> apply(Void r2) {
                return CommonUtil.asyncSleep(duration, CommonSysApiInverseRequests.this.contextUtil.getDefaultExecutor());
            }
        });
    }

    @Override // api.requests.com.neusoft.common.commonsysapiinverse.ICommonSysApiInverseRequests
    public CompletableFuture<Void> fireHMIFocusChanged(ReqHMIFocusChanged reqHMIFocusChanged) {
        reqHMIFocusChanged.setResponse(null);
        String str = this.component;
        if (str != null) {
            reqHMIFocusChanged.setComponent(str);
        }
        return Communication.request(this.contextUtil.createFromClass(reqHMIFocusChanged)).thenCompose(new Function<Object, CompletableFuture<Void>>() { // from class: api.requests.com.neusoft.common.commonsysapiinverse.CommonSysApiInverseRequests.315
            @Override // java9.util.function.Function
            public CompletableFuture<Void> apply(Object obj) {
                return CommonSysApiInverseRequests.completedVoidFuture;
            }
        });
    }

    @Override // api.requests.com.neusoft.common.commonsysapiinverse.ICommonSysApiInverseRequests
    public CompletableFuture<Void> fireHMIFocusChanged(ReqHMIFocusChanged reqHMIFocusChanged, final Duration duration) {
        return fireHMIFocusChanged(reqHMIFocusChanged).thenCompose((Function<? super Void, ? extends CompletionStage<U>>) new Function<Void, CompletableFuture<Void>>() { // from class: api.requests.com.neusoft.common.commonsysapiinverse.CommonSysApiInverseRequests.316
            @Override // java9.util.function.Function
            public CompletableFuture<Void> apply(Void r2) {
                return CommonUtil.asyncSleep(duration, CommonSysApiInverseRequests.this.contextUtil.getDefaultExecutor());
            }
        });
    }

    @Override // api.requests.com.neusoft.common.commonsysapiinverse.ICommonSysApiInverseRequests
    public CompletableFuture<Void> fireHMIFocusChanged(ReqHMIFocusChanged.Arguments arguments) {
        ReqHMIFocusChanged reqHMIFocusChanged = new ReqHMIFocusChanged();
        reqHMIFocusChanged.setArguments(arguments);
        reqHMIFocusChanged.setResponse(null);
        String str = this.component;
        if (str != null) {
            reqHMIFocusChanged.setComponent(str);
        }
        return fireHMIFocusChanged(reqHMIFocusChanged);
    }

    @Override // api.requests.com.neusoft.common.commonsysapiinverse.ICommonSysApiInverseRequests
    public CompletableFuture<Void> fireHMIFocusChanged(ReqHMIFocusChanged.Arguments arguments, final Duration duration) {
        return fireHMIFocusChanged(arguments).thenCompose((Function<? super Void, ? extends CompletionStage<U>>) new Function<Void, CompletableFuture<Void>>() { // from class: api.requests.com.neusoft.common.commonsysapiinverse.CommonSysApiInverseRequests.317
            @Override // java9.util.function.Function
            public CompletableFuture<Void> apply(Void r2) {
                return CommonUtil.asyncSleep(duration, CommonSysApiInverseRequests.this.contextUtil.getDefaultExecutor());
            }
        });
    }

    @Override // api.requests.com.neusoft.common.commonsysapiinverse.ICommonSysApiInverseRequests
    public CompletableFuture<ResGetNaviSoftware> requestGetNaviSoftware(ReqGetNaviSoftware reqGetNaviSoftware) {
        if (reqGetNaviSoftware.getResponse() == null) {
            reqGetNaviSoftware.setResponse(new ResGetNaviSoftware());
        }
        String str = this.component;
        if (str != null) {
            reqGetNaviSoftware.setComponent(str);
            reqGetNaviSoftware.getResponse().setComponent(this.component);
        }
        return Communication.request(this.contextUtil.createFromClass(reqGetNaviSoftware));
    }

    @Override // api.requests.com.neusoft.common.commonsysapiinverse.ICommonSysApiInverseRequests
    public CompletableFuture<ResGetNaviSoftware> requestGetNaviSoftware(ReqGetNaviSoftware reqGetNaviSoftware, final Duration duration) {
        return requestGetNaviSoftware(reqGetNaviSoftware).thenCompose((Function<? super ResGetNaviSoftware, ? extends CompletionStage<U>>) new Function<ResGetNaviSoftware, CompletableFuture<ResGetNaviSoftware>>() { // from class: api.requests.com.neusoft.common.commonsysapiinverse.CommonSysApiInverseRequests.318
            @Override // java9.util.function.Function
            public CompletableFuture<ResGetNaviSoftware> apply(ResGetNaviSoftware resGetNaviSoftware) {
                return CommonUtil.delayAsync(resGetNaviSoftware, duration, CommonSysApiInverseRequests.this.contextUtil.getDefaultExecutor());
            }
        });
    }

    @Override // api.requests.com.neusoft.common.commonsysapiinverse.ICommonSysApiInverseRequests
    public CompletableFuture<ResGetNaviSoftware> requestGetNaviSoftware() {
        return requestGetNaviSoftware(new ReqGetNaviSoftware());
    }

    @Override // api.requests.com.neusoft.common.commonsysapiinverse.ICommonSysApiInverseRequests
    public CompletableFuture<ResGetNaviSoftware> requestGetNaviSoftware(Duration duration) {
        return requestGetNaviSoftware(new ReqGetNaviSoftware(), duration);
    }

    @Override // api.requests.com.neusoft.common.commonsysapiinverse.ICommonSysApiInverseRequests
    public CompletableFuture<Void> fireGetNaviSoftware(ReqGetNaviSoftware reqGetNaviSoftware) {
        reqGetNaviSoftware.setResponse(null);
        String str = this.component;
        if (str != null) {
            reqGetNaviSoftware.setComponent(str);
        }
        return Communication.request(this.contextUtil.createFromClass(reqGetNaviSoftware)).thenCompose(new Function<Object, CompletableFuture<Void>>() { // from class: api.requests.com.neusoft.common.commonsysapiinverse.CommonSysApiInverseRequests.319
            @Override // java9.util.function.Function
            public CompletableFuture<Void> apply(Object obj) {
                return CommonSysApiInverseRequests.completedVoidFuture;
            }
        });
    }

    @Override // api.requests.com.neusoft.common.commonsysapiinverse.ICommonSysApiInverseRequests
    public CompletableFuture<Void> fireGetNaviSoftware(ReqGetNaviSoftware reqGetNaviSoftware, final Duration duration) {
        return fireGetNaviSoftware(reqGetNaviSoftware).thenCompose((Function<? super Void, ? extends CompletionStage<U>>) new Function<Void, CompletableFuture<Void>>() { // from class: api.requests.com.neusoft.common.commonsysapiinverse.CommonSysApiInverseRequests.320
            @Override // java9.util.function.Function
            public CompletableFuture<Void> apply(Void r2) {
                return CommonUtil.asyncSleep(duration, CommonSysApiInverseRequests.this.contextUtil.getDefaultExecutor());
            }
        });
    }

    @Override // api.requests.com.neusoft.common.commonsysapiinverse.ICommonSysApiInverseRequests
    public CompletableFuture<Void> fireGetNaviSoftware() {
        return fireGetNaviSoftware(new ReqGetNaviSoftware());
    }

    @Override // api.requests.com.neusoft.common.commonsysapiinverse.ICommonSysApiInverseRequests
    public CompletableFuture<Void> fireGetNaviSoftware(final Duration duration) {
        return fireGetNaviSoftware(new ReqGetNaviSoftware()).thenCompose((Function<? super Void, ? extends CompletionStage<U>>) new Function<Void, CompletableFuture<Void>>() { // from class: api.requests.com.neusoft.common.commonsysapiinverse.CommonSysApiInverseRequests.321
            @Override // java9.util.function.Function
            public CompletableFuture<Void> apply(Void r2) {
                return CommonUtil.asyncSleep(duration, CommonSysApiInverseRequests.this.contextUtil.getDefaultExecutor());
            }
        });
    }

    @Override // api.requests.com.neusoft.common.commonsysapiinverse.ICommonSysApiInverseRequests
    public CompletableFuture<Void> fireSetSystemTimeAndWeekDay(ReqSetSystemTimeAndWeekDay reqSetSystemTimeAndWeekDay) {
        reqSetSystemTimeAndWeekDay.setResponse(null);
        String str = this.component;
        if (str != null) {
            reqSetSystemTimeAndWeekDay.setComponent(str);
        }
        return Communication.request(this.contextUtil.createFromClass(reqSetSystemTimeAndWeekDay)).thenCompose(new Function<Object, CompletableFuture<Void>>() { // from class: api.requests.com.neusoft.common.commonsysapiinverse.CommonSysApiInverseRequests.322
            @Override // java9.util.function.Function
            public CompletableFuture<Void> apply(Object obj) {
                return CommonSysApiInverseRequests.completedVoidFuture;
            }
        });
    }

    @Override // api.requests.com.neusoft.common.commonsysapiinverse.ICommonSysApiInverseRequests
    public CompletableFuture<Void> fireSetSystemTimeAndWeekDay(ReqSetSystemTimeAndWeekDay reqSetSystemTimeAndWeekDay, final Duration duration) {
        return fireSetSystemTimeAndWeekDay(reqSetSystemTimeAndWeekDay).thenCompose((Function<? super Void, ? extends CompletionStage<U>>) new Function<Void, CompletableFuture<Void>>() { // from class: api.requests.com.neusoft.common.commonsysapiinverse.CommonSysApiInverseRequests.323
            @Override // java9.util.function.Function
            public CompletableFuture<Void> apply(Void r2) {
                return CommonUtil.asyncSleep(duration, CommonSysApiInverseRequests.this.contextUtil.getDefaultExecutor());
            }
        });
    }

    @Override // api.requests.com.neusoft.common.commonsysapiinverse.ICommonSysApiInverseRequests
    public CompletableFuture<Void> fireSetSystemTimeAndWeekDay(ReqSetSystemTimeAndWeekDay.Arguments arguments) {
        ReqSetSystemTimeAndWeekDay reqSetSystemTimeAndWeekDay = new ReqSetSystemTimeAndWeekDay();
        reqSetSystemTimeAndWeekDay.setArguments(arguments);
        reqSetSystemTimeAndWeekDay.setResponse(null);
        String str = this.component;
        if (str != null) {
            reqSetSystemTimeAndWeekDay.setComponent(str);
        }
        return fireSetSystemTimeAndWeekDay(reqSetSystemTimeAndWeekDay);
    }

    @Override // api.requests.com.neusoft.common.commonsysapiinverse.ICommonSysApiInverseRequests
    public CompletableFuture<Void> fireSetSystemTimeAndWeekDay(ReqSetSystemTimeAndWeekDay.Arguments arguments, final Duration duration) {
        return fireSetSystemTimeAndWeekDay(arguments).thenCompose((Function<? super Void, ? extends CompletionStage<U>>) new Function<Void, CompletableFuture<Void>>() { // from class: api.requests.com.neusoft.common.commonsysapiinverse.CommonSysApiInverseRequests.324
            @Override // java9.util.function.Function
            public CompletableFuture<Void> apply(Void r2) {
                return CommonUtil.asyncSleep(duration, CommonSysApiInverseRequests.this.contextUtil.getDefaultExecutor());
            }
        });
    }

    @Override // api.requests.com.neusoft.common.commonsysapiinverse.ICommonSysApiInverseRequests
    public CompletableFuture<Void> fireMigrateNaviHMIPage(ReqMigrateNaviHMIPage reqMigrateNaviHMIPage) {
        reqMigrateNaviHMIPage.setResponse(null);
        String str = this.component;
        if (str != null) {
            reqMigrateNaviHMIPage.setComponent(str);
        }
        return Communication.request(this.contextUtil.createFromClass(reqMigrateNaviHMIPage)).thenCompose(new Function<Object, CompletableFuture<Void>>() { // from class: api.requests.com.neusoft.common.commonsysapiinverse.CommonSysApiInverseRequests.325
            @Override // java9.util.function.Function
            public CompletableFuture<Void> apply(Object obj) {
                return CommonSysApiInverseRequests.completedVoidFuture;
            }
        });
    }

    @Override // api.requests.com.neusoft.common.commonsysapiinverse.ICommonSysApiInverseRequests
    public CompletableFuture<Void> fireMigrateNaviHMIPage(ReqMigrateNaviHMIPage reqMigrateNaviHMIPage, final Duration duration) {
        return fireMigrateNaviHMIPage(reqMigrateNaviHMIPage).thenCompose((Function<? super Void, ? extends CompletionStage<U>>) new Function<Void, CompletableFuture<Void>>() { // from class: api.requests.com.neusoft.common.commonsysapiinverse.CommonSysApiInverseRequests.326
            @Override // java9.util.function.Function
            public CompletableFuture<Void> apply(Void r2) {
                return CommonUtil.asyncSleep(duration, CommonSysApiInverseRequests.this.contextUtil.getDefaultExecutor());
            }
        });
    }

    @Override // api.requests.com.neusoft.common.commonsysapiinverse.ICommonSysApiInverseRequests
    public CompletableFuture<Void> fireMigrateNaviHMIPage(ReqMigrateNaviHMIPage.Arguments arguments) {
        ReqMigrateNaviHMIPage reqMigrateNaviHMIPage = new ReqMigrateNaviHMIPage();
        reqMigrateNaviHMIPage.setArguments(arguments);
        reqMigrateNaviHMIPage.setResponse(null);
        String str = this.component;
        if (str != null) {
            reqMigrateNaviHMIPage.setComponent(str);
        }
        return fireMigrateNaviHMIPage(reqMigrateNaviHMIPage);
    }

    @Override // api.requests.com.neusoft.common.commonsysapiinverse.ICommonSysApiInverseRequests
    public CompletableFuture<Void> fireMigrateNaviHMIPage(ReqMigrateNaviHMIPage.Arguments arguments, final Duration duration) {
        return fireMigrateNaviHMIPage(arguments).thenCompose((Function<? super Void, ? extends CompletionStage<U>>) new Function<Void, CompletableFuture<Void>>() { // from class: api.requests.com.neusoft.common.commonsysapiinverse.CommonSysApiInverseRequests.327
            @Override // java9.util.function.Function
            public CompletableFuture<Void> apply(Void r2) {
                return CommonUtil.asyncSleep(duration, CommonSysApiInverseRequests.this.contextUtil.getDefaultExecutor());
            }
        });
    }

    @Override // api.requests.com.neusoft.common.commonsysapiinverse.ICommonSysApiInverseRequests
    public CompletableFuture<Void> fireOnLoadURL(ReqOnLoadURL reqOnLoadURL) {
        reqOnLoadURL.setResponse(null);
        String str = this.component;
        if (str != null) {
            reqOnLoadURL.setComponent(str);
        }
        return Communication.request(this.contextUtil.createFromClass(reqOnLoadURL)).thenCompose(new Function<Object, CompletableFuture<Void>>() { // from class: api.requests.com.neusoft.common.commonsysapiinverse.CommonSysApiInverseRequests.328
            @Override // java9.util.function.Function
            public CompletableFuture<Void> apply(Object obj) {
                return CommonSysApiInverseRequests.completedVoidFuture;
            }
        });
    }

    @Override // api.requests.com.neusoft.common.commonsysapiinverse.ICommonSysApiInverseRequests
    public CompletableFuture<Void> fireOnLoadURL(ReqOnLoadURL reqOnLoadURL, final Duration duration) {
        return fireOnLoadURL(reqOnLoadURL).thenCompose((Function<? super Void, ? extends CompletionStage<U>>) new Function<Void, CompletableFuture<Void>>() { // from class: api.requests.com.neusoft.common.commonsysapiinverse.CommonSysApiInverseRequests.329
            @Override // java9.util.function.Function
            public CompletableFuture<Void> apply(Void r2) {
                return CommonUtil.asyncSleep(duration, CommonSysApiInverseRequests.this.contextUtil.getDefaultExecutor());
            }
        });
    }

    @Override // api.requests.com.neusoft.common.commonsysapiinverse.ICommonSysApiInverseRequests
    public CompletableFuture<Void> fireOnLoadURL(ReqOnLoadURL.Arguments arguments) {
        ReqOnLoadURL reqOnLoadURL = new ReqOnLoadURL();
        reqOnLoadURL.setArguments(arguments);
        reqOnLoadURL.setResponse(null);
        String str = this.component;
        if (str != null) {
            reqOnLoadURL.setComponent(str);
        }
        return fireOnLoadURL(reqOnLoadURL);
    }

    @Override // api.requests.com.neusoft.common.commonsysapiinverse.ICommonSysApiInverseRequests
    public CompletableFuture<Void> fireOnLoadURL(ReqOnLoadURL.Arguments arguments, final Duration duration) {
        return fireOnLoadURL(arguments).thenCompose((Function<? super Void, ? extends CompletionStage<U>>) new Function<Void, CompletableFuture<Void>>() { // from class: api.requests.com.neusoft.common.commonsysapiinverse.CommonSysApiInverseRequests.330
            @Override // java9.util.function.Function
            public CompletableFuture<Void> apply(Void r2) {
                return CommonUtil.asyncSleep(duration, CommonSysApiInverseRequests.this.contextUtil.getDefaultExecutor());
            }
        });
    }

    @Override // api.requests.com.neusoft.common.commonsysapiinverse.ICommonSysApiInverseRequests
    public CompletableFuture<Void> fireNaviAppStartupEventInfo(ReqNaviAppStartupEventInfo reqNaviAppStartupEventInfo) {
        reqNaviAppStartupEventInfo.setResponse(null);
        String str = this.component;
        if (str != null) {
            reqNaviAppStartupEventInfo.setComponent(str);
        }
        return Communication.request(this.contextUtil.createFromClass(reqNaviAppStartupEventInfo)).thenCompose(new Function<Object, CompletableFuture<Void>>() { // from class: api.requests.com.neusoft.common.commonsysapiinverse.CommonSysApiInverseRequests.331
            @Override // java9.util.function.Function
            public CompletableFuture<Void> apply(Object obj) {
                return CommonSysApiInverseRequests.completedVoidFuture;
            }
        });
    }

    @Override // api.requests.com.neusoft.common.commonsysapiinverse.ICommonSysApiInverseRequests
    public CompletableFuture<Void> fireNaviAppStartupEventInfo(ReqNaviAppStartupEventInfo reqNaviAppStartupEventInfo, final Duration duration) {
        return fireNaviAppStartupEventInfo(reqNaviAppStartupEventInfo).thenCompose((Function<? super Void, ? extends CompletionStage<U>>) new Function<Void, CompletableFuture<Void>>() { // from class: api.requests.com.neusoft.common.commonsysapiinverse.CommonSysApiInverseRequests.332
            @Override // java9.util.function.Function
            public CompletableFuture<Void> apply(Void r2) {
                return CommonUtil.asyncSleep(duration, CommonSysApiInverseRequests.this.contextUtil.getDefaultExecutor());
            }
        });
    }

    @Override // api.requests.com.neusoft.common.commonsysapiinverse.ICommonSysApiInverseRequests
    public CompletableFuture<Void> fireNaviAppStartupEventInfo(ReqNaviAppStartupEventInfo.Arguments arguments) {
        ReqNaviAppStartupEventInfo reqNaviAppStartupEventInfo = new ReqNaviAppStartupEventInfo();
        reqNaviAppStartupEventInfo.setArguments(arguments);
        reqNaviAppStartupEventInfo.setResponse(null);
        String str = this.component;
        if (str != null) {
            reqNaviAppStartupEventInfo.setComponent(str);
        }
        return fireNaviAppStartupEventInfo(reqNaviAppStartupEventInfo);
    }

    @Override // api.requests.com.neusoft.common.commonsysapiinverse.ICommonSysApiInverseRequests
    public CompletableFuture<Void> fireNaviAppStartupEventInfo(ReqNaviAppStartupEventInfo.Arguments arguments, final Duration duration) {
        return fireNaviAppStartupEventInfo(arguments).thenCompose((Function<? super Void, ? extends CompletionStage<U>>) new Function<Void, CompletableFuture<Void>>() { // from class: api.requests.com.neusoft.common.commonsysapiinverse.CommonSysApiInverseRequests.333
            @Override // java9.util.function.Function
            public CompletableFuture<Void> apply(Void r2) {
                return CommonUtil.asyncSleep(duration, CommonSysApiInverseRequests.this.contextUtil.getDefaultExecutor());
            }
        });
    }

    @Override // api.requests.com.neusoft.common.commonsysapiinverse.ICommonSysApiInverseRequests
    public CompletableFuture<Void> fireNaviAppExitEventInfo(ReqNaviAppExitEventInfo reqNaviAppExitEventInfo) {
        reqNaviAppExitEventInfo.setResponse(null);
        String str = this.component;
        if (str != null) {
            reqNaviAppExitEventInfo.setComponent(str);
        }
        return Communication.request(this.contextUtil.createFromClass(reqNaviAppExitEventInfo)).thenCompose(new Function<Object, CompletableFuture<Void>>() { // from class: api.requests.com.neusoft.common.commonsysapiinverse.CommonSysApiInverseRequests.334
            @Override // java9.util.function.Function
            public CompletableFuture<Void> apply(Object obj) {
                return CommonSysApiInverseRequests.completedVoidFuture;
            }
        });
    }

    @Override // api.requests.com.neusoft.common.commonsysapiinverse.ICommonSysApiInverseRequests
    public CompletableFuture<Void> fireNaviAppExitEventInfo(ReqNaviAppExitEventInfo reqNaviAppExitEventInfo, final Duration duration) {
        return fireNaviAppExitEventInfo(reqNaviAppExitEventInfo).thenCompose((Function<? super Void, ? extends CompletionStage<U>>) new Function<Void, CompletableFuture<Void>>() { // from class: api.requests.com.neusoft.common.commonsysapiinverse.CommonSysApiInverseRequests.335
            @Override // java9.util.function.Function
            public CompletableFuture<Void> apply(Void r2) {
                return CommonUtil.asyncSleep(duration, CommonSysApiInverseRequests.this.contextUtil.getDefaultExecutor());
            }
        });
    }

    @Override // api.requests.com.neusoft.common.commonsysapiinverse.ICommonSysApiInverseRequests
    public CompletableFuture<Void> fireNaviAppExitEventInfo(ReqNaviAppExitEventInfo.Arguments arguments) {
        ReqNaviAppExitEventInfo reqNaviAppExitEventInfo = new ReqNaviAppExitEventInfo();
        reqNaviAppExitEventInfo.setArguments(arguments);
        reqNaviAppExitEventInfo.setResponse(null);
        String str = this.component;
        if (str != null) {
            reqNaviAppExitEventInfo.setComponent(str);
        }
        return fireNaviAppExitEventInfo(reqNaviAppExitEventInfo);
    }

    @Override // api.requests.com.neusoft.common.commonsysapiinverse.ICommonSysApiInverseRequests
    public CompletableFuture<Void> fireNaviAppExitEventInfo(ReqNaviAppExitEventInfo.Arguments arguments, final Duration duration) {
        return fireNaviAppExitEventInfo(arguments).thenCompose((Function<? super Void, ? extends CompletionStage<U>>) new Function<Void, CompletableFuture<Void>>() { // from class: api.requests.com.neusoft.common.commonsysapiinverse.CommonSysApiInverseRequests.336
            @Override // java9.util.function.Function
            public CompletableFuture<Void> apply(Void r2) {
                return CommonUtil.asyncSleep(duration, CommonSysApiInverseRequests.this.contextUtil.getDefaultExecutor());
            }
        });
    }

    @Override // api.requests.com.neusoft.common.commonsysapiinverse.ICommonSysApiInverseRequests
    public CompletableFuture<Void> fireDashboardMirrorEventInfo(ReqDashboardMirrorEventInfo reqDashboardMirrorEventInfo) {
        reqDashboardMirrorEventInfo.setResponse(null);
        String str = this.component;
        if (str != null) {
            reqDashboardMirrorEventInfo.setComponent(str);
        }
        return Communication.request(this.contextUtil.createFromClass(reqDashboardMirrorEventInfo)).thenCompose(new Function<Object, CompletableFuture<Void>>() { // from class: api.requests.com.neusoft.common.commonsysapiinverse.CommonSysApiInverseRequests.337
            @Override // java9.util.function.Function
            public CompletableFuture<Void> apply(Object obj) {
                return CommonSysApiInverseRequests.completedVoidFuture;
            }
        });
    }

    @Override // api.requests.com.neusoft.common.commonsysapiinverse.ICommonSysApiInverseRequests
    public CompletableFuture<Void> fireDashboardMirrorEventInfo(ReqDashboardMirrorEventInfo reqDashboardMirrorEventInfo, final Duration duration) {
        return fireDashboardMirrorEventInfo(reqDashboardMirrorEventInfo).thenCompose((Function<? super Void, ? extends CompletionStage<U>>) new Function<Void, CompletableFuture<Void>>() { // from class: api.requests.com.neusoft.common.commonsysapiinverse.CommonSysApiInverseRequests.338
            @Override // java9.util.function.Function
            public CompletableFuture<Void> apply(Void r2) {
                return CommonUtil.asyncSleep(duration, CommonSysApiInverseRequests.this.contextUtil.getDefaultExecutor());
            }
        });
    }

    @Override // api.requests.com.neusoft.common.commonsysapiinverse.ICommonSysApiInverseRequests
    public CompletableFuture<Void> fireDashboardMirrorEventInfo(ReqDashboardMirrorEventInfo.Arguments arguments) {
        ReqDashboardMirrorEventInfo reqDashboardMirrorEventInfo = new ReqDashboardMirrorEventInfo();
        reqDashboardMirrorEventInfo.setArguments(arguments);
        reqDashboardMirrorEventInfo.setResponse(null);
        String str = this.component;
        if (str != null) {
            reqDashboardMirrorEventInfo.setComponent(str);
        }
        return fireDashboardMirrorEventInfo(reqDashboardMirrorEventInfo);
    }

    @Override // api.requests.com.neusoft.common.commonsysapiinverse.ICommonSysApiInverseRequests
    public CompletableFuture<Void> fireDashboardMirrorEventInfo(ReqDashboardMirrorEventInfo.Arguments arguments, final Duration duration) {
        return fireDashboardMirrorEventInfo(arguments).thenCompose((Function<? super Void, ? extends CompletionStage<U>>) new Function<Void, CompletableFuture<Void>>() { // from class: api.requests.com.neusoft.common.commonsysapiinverse.CommonSysApiInverseRequests.339
            @Override // java9.util.function.Function
            public CompletableFuture<Void> apply(Void r2) {
                return CommonUtil.asyncSleep(duration, CommonSysApiInverseRequests.this.contextUtil.getDefaultExecutor());
            }
        });
    }

    @Override // api.requests.com.neusoft.common.commonsysapiinverse.ICommonSysApiInverseRequests
    public CompletableFuture<Void> fireAppFocusEventInfo(ReqAppFocusEventInfo reqAppFocusEventInfo) {
        reqAppFocusEventInfo.setResponse(null);
        String str = this.component;
        if (str != null) {
            reqAppFocusEventInfo.setComponent(str);
        }
        return Communication.request(this.contextUtil.createFromClass(reqAppFocusEventInfo)).thenCompose(new Function<Object, CompletableFuture<Void>>() { // from class: api.requests.com.neusoft.common.commonsysapiinverse.CommonSysApiInverseRequests.340
            @Override // java9.util.function.Function
            public CompletableFuture<Void> apply(Object obj) {
                return CommonSysApiInverseRequests.completedVoidFuture;
            }
        });
    }

    @Override // api.requests.com.neusoft.common.commonsysapiinverse.ICommonSysApiInverseRequests
    public CompletableFuture<Void> fireAppFocusEventInfo(ReqAppFocusEventInfo reqAppFocusEventInfo, final Duration duration) {
        return fireAppFocusEventInfo(reqAppFocusEventInfo).thenCompose((Function<? super Void, ? extends CompletionStage<U>>) new Function<Void, CompletableFuture<Void>>() { // from class: api.requests.com.neusoft.common.commonsysapiinverse.CommonSysApiInverseRequests.341
            @Override // java9.util.function.Function
            public CompletableFuture<Void> apply(Void r2) {
                return CommonUtil.asyncSleep(duration, CommonSysApiInverseRequests.this.contextUtil.getDefaultExecutor());
            }
        });
    }

    @Override // api.requests.com.neusoft.common.commonsysapiinverse.ICommonSysApiInverseRequests
    public CompletableFuture<Void> fireAppFocusEventInfo(ReqAppFocusEventInfo.Arguments arguments) {
        ReqAppFocusEventInfo reqAppFocusEventInfo = new ReqAppFocusEventInfo();
        reqAppFocusEventInfo.setArguments(arguments);
        reqAppFocusEventInfo.setResponse(null);
        String str = this.component;
        if (str != null) {
            reqAppFocusEventInfo.setComponent(str);
        }
        return fireAppFocusEventInfo(reqAppFocusEventInfo);
    }

    @Override // api.requests.com.neusoft.common.commonsysapiinverse.ICommonSysApiInverseRequests
    public CompletableFuture<Void> fireAppFocusEventInfo(ReqAppFocusEventInfo.Arguments arguments, final Duration duration) {
        return fireAppFocusEventInfo(arguments).thenCompose((Function<? super Void, ? extends CompletionStage<U>>) new Function<Void, CompletableFuture<Void>>() { // from class: api.requests.com.neusoft.common.commonsysapiinverse.CommonSysApiInverseRequests.342
            @Override // java9.util.function.Function
            public CompletableFuture<Void> apply(Void r2) {
                return CommonUtil.asyncSleep(duration, CommonSysApiInverseRequests.this.contextUtil.getDefaultExecutor());
            }
        });
    }

    @Override // api.requests.com.neusoft.common.commonsysapiinverse.ICommonSysApiInverseRequests
    public CompletableFuture<Void> fireDataCollectionCheckboxStatus(ReqDataCollectionCheckboxStatus reqDataCollectionCheckboxStatus) {
        reqDataCollectionCheckboxStatus.setResponse(null);
        String str = this.component;
        if (str != null) {
            reqDataCollectionCheckboxStatus.setComponent(str);
        }
        return Communication.request(this.contextUtil.createFromClass(reqDataCollectionCheckboxStatus)).thenCompose(new Function<Object, CompletableFuture<Void>>() { // from class: api.requests.com.neusoft.common.commonsysapiinverse.CommonSysApiInverseRequests.343
            @Override // java9.util.function.Function
            public CompletableFuture<Void> apply(Object obj) {
                return CommonSysApiInverseRequests.completedVoidFuture;
            }
        });
    }

    @Override // api.requests.com.neusoft.common.commonsysapiinverse.ICommonSysApiInverseRequests
    public CompletableFuture<Void> fireDataCollectionCheckboxStatus(ReqDataCollectionCheckboxStatus reqDataCollectionCheckboxStatus, final Duration duration) {
        return fireDataCollectionCheckboxStatus(reqDataCollectionCheckboxStatus).thenCompose((Function<? super Void, ? extends CompletionStage<U>>) new Function<Void, CompletableFuture<Void>>() { // from class: api.requests.com.neusoft.common.commonsysapiinverse.CommonSysApiInverseRequests.344
            @Override // java9.util.function.Function
            public CompletableFuture<Void> apply(Void r2) {
                return CommonUtil.asyncSleep(duration, CommonSysApiInverseRequests.this.contextUtil.getDefaultExecutor());
            }
        });
    }

    @Override // api.requests.com.neusoft.common.commonsysapiinverse.ICommonSysApiInverseRequests
    public CompletableFuture<Void> fireDataCollectionCheckboxStatus(ReqDataCollectionCheckboxStatus.Arguments arguments) {
        ReqDataCollectionCheckboxStatus reqDataCollectionCheckboxStatus = new ReqDataCollectionCheckboxStatus();
        reqDataCollectionCheckboxStatus.setArguments(arguments);
        reqDataCollectionCheckboxStatus.setResponse(null);
        String str = this.component;
        if (str != null) {
            reqDataCollectionCheckboxStatus.setComponent(str);
        }
        return fireDataCollectionCheckboxStatus(reqDataCollectionCheckboxStatus);
    }

    @Override // api.requests.com.neusoft.common.commonsysapiinverse.ICommonSysApiInverseRequests
    public CompletableFuture<Void> fireDataCollectionCheckboxStatus(ReqDataCollectionCheckboxStatus.Arguments arguments, final Duration duration) {
        return fireDataCollectionCheckboxStatus(arguments).thenCompose((Function<? super Void, ? extends CompletionStage<U>>) new Function<Void, CompletableFuture<Void>>() { // from class: api.requests.com.neusoft.common.commonsysapiinverse.CommonSysApiInverseRequests.345
            @Override // java9.util.function.Function
            public CompletableFuture<Void> apply(Void r2) {
                return CommonUtil.asyncSleep(duration, CommonSysApiInverseRequests.this.contextUtil.getDefaultExecutor());
            }
        });
    }

    @Override // api.requests.com.neusoft.common.commonsysapiinverse.ICommonSysApiInverseRequests
    public CompletableFuture<ResGetNavigationRunningStatus> requestGetNavigationRunningStatus(ReqGetNavigationRunningStatus reqGetNavigationRunningStatus) {
        if (reqGetNavigationRunningStatus.getResponse() == null) {
            reqGetNavigationRunningStatus.setResponse(new ResGetNavigationRunningStatus());
        }
        String str = this.component;
        if (str != null) {
            reqGetNavigationRunningStatus.setComponent(str);
            reqGetNavigationRunningStatus.getResponse().setComponent(this.component);
        }
        return Communication.request(this.contextUtil.createFromClass(reqGetNavigationRunningStatus));
    }

    @Override // api.requests.com.neusoft.common.commonsysapiinverse.ICommonSysApiInverseRequests
    public CompletableFuture<ResGetNavigationRunningStatus> requestGetNavigationRunningStatus(ReqGetNavigationRunningStatus reqGetNavigationRunningStatus, final Duration duration) {
        return requestGetNavigationRunningStatus(reqGetNavigationRunningStatus).thenCompose((Function<? super ResGetNavigationRunningStatus, ? extends CompletionStage<U>>) new Function<ResGetNavigationRunningStatus, CompletableFuture<ResGetNavigationRunningStatus>>() { // from class: api.requests.com.neusoft.common.commonsysapiinverse.CommonSysApiInverseRequests.346
            @Override // java9.util.function.Function
            public CompletableFuture<ResGetNavigationRunningStatus> apply(ResGetNavigationRunningStatus resGetNavigationRunningStatus) {
                return CommonUtil.delayAsync(resGetNavigationRunningStatus, duration, CommonSysApiInverseRequests.this.contextUtil.getDefaultExecutor());
            }
        });
    }

    @Override // api.requests.com.neusoft.common.commonsysapiinverse.ICommonSysApiInverseRequests
    public CompletableFuture<ResGetNavigationRunningStatus> requestGetNavigationRunningStatus() {
        return requestGetNavigationRunningStatus(new ReqGetNavigationRunningStatus());
    }

    @Override // api.requests.com.neusoft.common.commonsysapiinverse.ICommonSysApiInverseRequests
    public CompletableFuture<ResGetNavigationRunningStatus> requestGetNavigationRunningStatus(Duration duration) {
        return requestGetNavigationRunningStatus(new ReqGetNavigationRunningStatus(), duration);
    }

    @Override // api.requests.com.neusoft.common.commonsysapiinverse.ICommonSysApiInverseRequests
    public CompletableFuture<Void> fireGetNavigationRunningStatus(ReqGetNavigationRunningStatus reqGetNavigationRunningStatus) {
        reqGetNavigationRunningStatus.setResponse(null);
        String str = this.component;
        if (str != null) {
            reqGetNavigationRunningStatus.setComponent(str);
        }
        return Communication.request(this.contextUtil.createFromClass(reqGetNavigationRunningStatus)).thenCompose(new Function<Object, CompletableFuture<Void>>() { // from class: api.requests.com.neusoft.common.commonsysapiinverse.CommonSysApiInverseRequests.347
            @Override // java9.util.function.Function
            public CompletableFuture<Void> apply(Object obj) {
                return CommonSysApiInverseRequests.completedVoidFuture;
            }
        });
    }

    @Override // api.requests.com.neusoft.common.commonsysapiinverse.ICommonSysApiInverseRequests
    public CompletableFuture<Void> fireGetNavigationRunningStatus(ReqGetNavigationRunningStatus reqGetNavigationRunningStatus, final Duration duration) {
        return fireGetNavigationRunningStatus(reqGetNavigationRunningStatus).thenCompose((Function<? super Void, ? extends CompletionStage<U>>) new Function<Void, CompletableFuture<Void>>() { // from class: api.requests.com.neusoft.common.commonsysapiinverse.CommonSysApiInverseRequests.348
            @Override // java9.util.function.Function
            public CompletableFuture<Void> apply(Void r2) {
                return CommonUtil.asyncSleep(duration, CommonSysApiInverseRequests.this.contextUtil.getDefaultExecutor());
            }
        });
    }

    @Override // api.requests.com.neusoft.common.commonsysapiinverse.ICommonSysApiInverseRequests
    public CompletableFuture<Void> fireGetNavigationRunningStatus() {
        return fireGetNavigationRunningStatus(new ReqGetNavigationRunningStatus());
    }

    @Override // api.requests.com.neusoft.common.commonsysapiinverse.ICommonSysApiInverseRequests
    public CompletableFuture<Void> fireGetNavigationRunningStatus(final Duration duration) {
        return fireGetNavigationRunningStatus(new ReqGetNavigationRunningStatus()).thenCompose((Function<? super Void, ? extends CompletionStage<U>>) new Function<Void, CompletableFuture<Void>>() { // from class: api.requests.com.neusoft.common.commonsysapiinverse.CommonSysApiInverseRequests.349
            @Override // java9.util.function.Function
            public CompletableFuture<Void> apply(Void r2) {
                return CommonUtil.asyncSleep(duration, CommonSysApiInverseRequests.this.contextUtil.getDefaultExecutor());
            }
        });
    }

    @Override // api.requests.com.neusoft.common.commonsysapiinverse.ICommonSysApiInverseRequests
    public CompletableFuture<ResGetNavigationActivityStatus> requestGetNavigationActivityStatus(ReqGetNavigationActivityStatus reqGetNavigationActivityStatus) {
        if (reqGetNavigationActivityStatus.getResponse() == null) {
            reqGetNavigationActivityStatus.setResponse(new ResGetNavigationActivityStatus());
        }
        String str = this.component;
        if (str != null) {
            reqGetNavigationActivityStatus.setComponent(str);
            reqGetNavigationActivityStatus.getResponse().setComponent(this.component);
        }
        return Communication.request(this.contextUtil.createFromClass(reqGetNavigationActivityStatus));
    }

    @Override // api.requests.com.neusoft.common.commonsysapiinverse.ICommonSysApiInverseRequests
    public CompletableFuture<ResGetNavigationActivityStatus> requestGetNavigationActivityStatus(ReqGetNavigationActivityStatus reqGetNavigationActivityStatus, final Duration duration) {
        return requestGetNavigationActivityStatus(reqGetNavigationActivityStatus).thenCompose((Function<? super ResGetNavigationActivityStatus, ? extends CompletionStage<U>>) new Function<ResGetNavigationActivityStatus, CompletableFuture<ResGetNavigationActivityStatus>>() { // from class: api.requests.com.neusoft.common.commonsysapiinverse.CommonSysApiInverseRequests.350
            @Override // java9.util.function.Function
            public CompletableFuture<ResGetNavigationActivityStatus> apply(ResGetNavigationActivityStatus resGetNavigationActivityStatus) {
                return CommonUtil.delayAsync(resGetNavigationActivityStatus, duration, CommonSysApiInverseRequests.this.contextUtil.getDefaultExecutor());
            }
        });
    }

    @Override // api.requests.com.neusoft.common.commonsysapiinverse.ICommonSysApiInverseRequests
    public CompletableFuture<ResGetNavigationActivityStatus> requestGetNavigationActivityStatus() {
        return requestGetNavigationActivityStatus(new ReqGetNavigationActivityStatus());
    }

    @Override // api.requests.com.neusoft.common.commonsysapiinverse.ICommonSysApiInverseRequests
    public CompletableFuture<ResGetNavigationActivityStatus> requestGetNavigationActivityStatus(Duration duration) {
        return requestGetNavigationActivityStatus(new ReqGetNavigationActivityStatus(), duration);
    }

    @Override // api.requests.com.neusoft.common.commonsysapiinverse.ICommonSysApiInverseRequests
    public CompletableFuture<Void> fireGetNavigationActivityStatus(ReqGetNavigationActivityStatus reqGetNavigationActivityStatus) {
        reqGetNavigationActivityStatus.setResponse(null);
        String str = this.component;
        if (str != null) {
            reqGetNavigationActivityStatus.setComponent(str);
        }
        return Communication.request(this.contextUtil.createFromClass(reqGetNavigationActivityStatus)).thenCompose(new Function<Object, CompletableFuture<Void>>() { // from class: api.requests.com.neusoft.common.commonsysapiinverse.CommonSysApiInverseRequests.351
            @Override // java9.util.function.Function
            public CompletableFuture<Void> apply(Object obj) {
                return CommonSysApiInverseRequests.completedVoidFuture;
            }
        });
    }

    @Override // api.requests.com.neusoft.common.commonsysapiinverse.ICommonSysApiInverseRequests
    public CompletableFuture<Void> fireGetNavigationActivityStatus(ReqGetNavigationActivityStatus reqGetNavigationActivityStatus, final Duration duration) {
        return fireGetNavigationActivityStatus(reqGetNavigationActivityStatus).thenCompose((Function<? super Void, ? extends CompletionStage<U>>) new Function<Void, CompletableFuture<Void>>() { // from class: api.requests.com.neusoft.common.commonsysapiinverse.CommonSysApiInverseRequests.352
            @Override // java9.util.function.Function
            public CompletableFuture<Void> apply(Void r2) {
                return CommonUtil.asyncSleep(duration, CommonSysApiInverseRequests.this.contextUtil.getDefaultExecutor());
            }
        });
    }

    @Override // api.requests.com.neusoft.common.commonsysapiinverse.ICommonSysApiInverseRequests
    public CompletableFuture<Void> fireGetNavigationActivityStatus() {
        return fireGetNavigationActivityStatus(new ReqGetNavigationActivityStatus());
    }

    @Override // api.requests.com.neusoft.common.commonsysapiinverse.ICommonSysApiInverseRequests
    public CompletableFuture<Void> fireGetNavigationActivityStatus(final Duration duration) {
        return fireGetNavigationActivityStatus(new ReqGetNavigationActivityStatus()).thenCompose((Function<? super Void, ? extends CompletionStage<U>>) new Function<Void, CompletableFuture<Void>>() { // from class: api.requests.com.neusoft.common.commonsysapiinverse.CommonSysApiInverseRequests.353
            @Override // java9.util.function.Function
            public CompletableFuture<Void> apply(Void r2) {
                return CommonUtil.asyncSleep(duration, CommonSysApiInverseRequests.this.contextUtil.getDefaultExecutor());
            }
        });
    }

    @Override // api.requests.com.neusoft.common.commonsysapiinverse.ICommonSysApiInverseRequests
    public CompletableFuture<ResGetRequestUpdateExternalStoragePath> requestGetRequestUpdateExternalStoragePath(ReqGetRequestUpdateExternalStoragePath reqGetRequestUpdateExternalStoragePath) {
        if (reqGetRequestUpdateExternalStoragePath.getResponse() == null) {
            reqGetRequestUpdateExternalStoragePath.setResponse(new ResGetRequestUpdateExternalStoragePath());
        }
        String str = this.component;
        if (str != null) {
            reqGetRequestUpdateExternalStoragePath.setComponent(str);
            reqGetRequestUpdateExternalStoragePath.getResponse().setComponent(this.component);
        }
        return Communication.request(this.contextUtil.createFromClass(reqGetRequestUpdateExternalStoragePath));
    }

    @Override // api.requests.com.neusoft.common.commonsysapiinverse.ICommonSysApiInverseRequests
    public CompletableFuture<ResGetRequestUpdateExternalStoragePath> requestGetRequestUpdateExternalStoragePath(ReqGetRequestUpdateExternalStoragePath reqGetRequestUpdateExternalStoragePath, final Duration duration) {
        return requestGetRequestUpdateExternalStoragePath(reqGetRequestUpdateExternalStoragePath).thenCompose((Function<? super ResGetRequestUpdateExternalStoragePath, ? extends CompletionStage<U>>) new Function<ResGetRequestUpdateExternalStoragePath, CompletableFuture<ResGetRequestUpdateExternalStoragePath>>() { // from class: api.requests.com.neusoft.common.commonsysapiinverse.CommonSysApiInverseRequests.354
            @Override // java9.util.function.Function
            public CompletableFuture<ResGetRequestUpdateExternalStoragePath> apply(ResGetRequestUpdateExternalStoragePath resGetRequestUpdateExternalStoragePath) {
                return CommonUtil.delayAsync(resGetRequestUpdateExternalStoragePath, duration, CommonSysApiInverseRequests.this.contextUtil.getDefaultExecutor());
            }
        });
    }

    @Override // api.requests.com.neusoft.common.commonsysapiinverse.ICommonSysApiInverseRequests
    public CompletableFuture<ResGetRequestUpdateExternalStoragePath> requestGetRequestUpdateExternalStoragePath() {
        return requestGetRequestUpdateExternalStoragePath(new ReqGetRequestUpdateExternalStoragePath());
    }

    @Override // api.requests.com.neusoft.common.commonsysapiinverse.ICommonSysApiInverseRequests
    public CompletableFuture<ResGetRequestUpdateExternalStoragePath> requestGetRequestUpdateExternalStoragePath(Duration duration) {
        return requestGetRequestUpdateExternalStoragePath(new ReqGetRequestUpdateExternalStoragePath(), duration);
    }

    @Override // api.requests.com.neusoft.common.commonsysapiinverse.ICommonSysApiInverseRequests
    public CompletableFuture<Void> fireGetRequestUpdateExternalStoragePath(ReqGetRequestUpdateExternalStoragePath reqGetRequestUpdateExternalStoragePath) {
        reqGetRequestUpdateExternalStoragePath.setResponse(null);
        String str = this.component;
        if (str != null) {
            reqGetRequestUpdateExternalStoragePath.setComponent(str);
        }
        return Communication.request(this.contextUtil.createFromClass(reqGetRequestUpdateExternalStoragePath)).thenCompose(new Function<Object, CompletableFuture<Void>>() { // from class: api.requests.com.neusoft.common.commonsysapiinverse.CommonSysApiInverseRequests.355
            @Override // java9.util.function.Function
            public CompletableFuture<Void> apply(Object obj) {
                return CommonSysApiInverseRequests.completedVoidFuture;
            }
        });
    }

    @Override // api.requests.com.neusoft.common.commonsysapiinverse.ICommonSysApiInverseRequests
    public CompletableFuture<Void> fireGetRequestUpdateExternalStoragePath(ReqGetRequestUpdateExternalStoragePath reqGetRequestUpdateExternalStoragePath, final Duration duration) {
        return fireGetRequestUpdateExternalStoragePath(reqGetRequestUpdateExternalStoragePath).thenCompose((Function<? super Void, ? extends CompletionStage<U>>) new Function<Void, CompletableFuture<Void>>() { // from class: api.requests.com.neusoft.common.commonsysapiinverse.CommonSysApiInverseRequests.356
            @Override // java9.util.function.Function
            public CompletableFuture<Void> apply(Void r2) {
                return CommonUtil.asyncSleep(duration, CommonSysApiInverseRequests.this.contextUtil.getDefaultExecutor());
            }
        });
    }

    @Override // api.requests.com.neusoft.common.commonsysapiinverse.ICommonSysApiInverseRequests
    public CompletableFuture<Void> fireGetRequestUpdateExternalStoragePath() {
        return fireGetRequestUpdateExternalStoragePath(new ReqGetRequestUpdateExternalStoragePath());
    }

    @Override // api.requests.com.neusoft.common.commonsysapiinverse.ICommonSysApiInverseRequests
    public CompletableFuture<Void> fireGetRequestUpdateExternalStoragePath(final Duration duration) {
        return fireGetRequestUpdateExternalStoragePath(new ReqGetRequestUpdateExternalStoragePath()).thenCompose((Function<? super Void, ? extends CompletionStage<U>>) new Function<Void, CompletableFuture<Void>>() { // from class: api.requests.com.neusoft.common.commonsysapiinverse.CommonSysApiInverseRequests.357
            @Override // java9.util.function.Function
            public CompletableFuture<Void> apply(Void r2) {
                return CommonUtil.asyncSleep(duration, CommonSysApiInverseRequests.this.contextUtil.getDefaultExecutor());
            }
        });
    }

    @Override // api.requests.com.neusoft.common.commonsysapiinverse.ICommonSysApiInverseRequests
    public CompletableFuture<ResGetApplyUpdateStatus> requestGetApplyUpdateStatus(ReqGetApplyUpdateStatus reqGetApplyUpdateStatus) {
        if (reqGetApplyUpdateStatus.getResponse() == null) {
            reqGetApplyUpdateStatus.setResponse(new ResGetApplyUpdateStatus());
        }
        String str = this.component;
        if (str != null) {
            reqGetApplyUpdateStatus.setComponent(str);
            reqGetApplyUpdateStatus.getResponse().setComponent(this.component);
        }
        return Communication.request(this.contextUtil.createFromClass(reqGetApplyUpdateStatus));
    }

    @Override // api.requests.com.neusoft.common.commonsysapiinverse.ICommonSysApiInverseRequests
    public CompletableFuture<ResGetApplyUpdateStatus> requestGetApplyUpdateStatus(ReqGetApplyUpdateStatus reqGetApplyUpdateStatus, final Duration duration) {
        return requestGetApplyUpdateStatus(reqGetApplyUpdateStatus).thenCompose((Function<? super ResGetApplyUpdateStatus, ? extends CompletionStage<U>>) new Function<ResGetApplyUpdateStatus, CompletableFuture<ResGetApplyUpdateStatus>>() { // from class: api.requests.com.neusoft.common.commonsysapiinverse.CommonSysApiInverseRequests.358
            @Override // java9.util.function.Function
            public CompletableFuture<ResGetApplyUpdateStatus> apply(ResGetApplyUpdateStatus resGetApplyUpdateStatus) {
                return CommonUtil.delayAsync(resGetApplyUpdateStatus, duration, CommonSysApiInverseRequests.this.contextUtil.getDefaultExecutor());
            }
        });
    }

    @Override // api.requests.com.neusoft.common.commonsysapiinverse.ICommonSysApiInverseRequests
    public CompletableFuture<ResGetApplyUpdateStatus> requestGetApplyUpdateStatus() {
        return requestGetApplyUpdateStatus(new ReqGetApplyUpdateStatus());
    }

    @Override // api.requests.com.neusoft.common.commonsysapiinverse.ICommonSysApiInverseRequests
    public CompletableFuture<ResGetApplyUpdateStatus> requestGetApplyUpdateStatus(Duration duration) {
        return requestGetApplyUpdateStatus(new ReqGetApplyUpdateStatus(), duration);
    }

    @Override // api.requests.com.neusoft.common.commonsysapiinverse.ICommonSysApiInverseRequests
    public CompletableFuture<Void> fireGetApplyUpdateStatus(ReqGetApplyUpdateStatus reqGetApplyUpdateStatus) {
        reqGetApplyUpdateStatus.setResponse(null);
        String str = this.component;
        if (str != null) {
            reqGetApplyUpdateStatus.setComponent(str);
        }
        return Communication.request(this.contextUtil.createFromClass(reqGetApplyUpdateStatus)).thenCompose(new Function<Object, CompletableFuture<Void>>() { // from class: api.requests.com.neusoft.common.commonsysapiinverse.CommonSysApiInverseRequests.359
            @Override // java9.util.function.Function
            public CompletableFuture<Void> apply(Object obj) {
                return CommonSysApiInverseRequests.completedVoidFuture;
            }
        });
    }

    @Override // api.requests.com.neusoft.common.commonsysapiinverse.ICommonSysApiInverseRequests
    public CompletableFuture<Void> fireGetApplyUpdateStatus(ReqGetApplyUpdateStatus reqGetApplyUpdateStatus, final Duration duration) {
        return fireGetApplyUpdateStatus(reqGetApplyUpdateStatus).thenCompose((Function<? super Void, ? extends CompletionStage<U>>) new Function<Void, CompletableFuture<Void>>() { // from class: api.requests.com.neusoft.common.commonsysapiinverse.CommonSysApiInverseRequests.360
            @Override // java9.util.function.Function
            public CompletableFuture<Void> apply(Void r2) {
                return CommonUtil.asyncSleep(duration, CommonSysApiInverseRequests.this.contextUtil.getDefaultExecutor());
            }
        });
    }

    @Override // api.requests.com.neusoft.common.commonsysapiinverse.ICommonSysApiInverseRequests
    public CompletableFuture<Void> fireGetApplyUpdateStatus() {
        return fireGetApplyUpdateStatus(new ReqGetApplyUpdateStatus());
    }

    @Override // api.requests.com.neusoft.common.commonsysapiinverse.ICommonSysApiInverseRequests
    public CompletableFuture<Void> fireGetApplyUpdateStatus(final Duration duration) {
        return fireGetApplyUpdateStatus(new ReqGetApplyUpdateStatus()).thenCompose((Function<? super Void, ? extends CompletionStage<U>>) new Function<Void, CompletableFuture<Void>>() { // from class: api.requests.com.neusoft.common.commonsysapiinverse.CommonSysApiInverseRequests.361
            @Override // java9.util.function.Function
            public CompletableFuture<Void> apply(Void r2) {
                return CommonUtil.asyncSleep(duration, CommonSysApiInverseRequests.this.contextUtil.getDefaultExecutor());
            }
        });
    }

    @Override // api.requests.com.neusoft.common.commonsysapiinverse.ICommonSysApiInverseRequests
    public CompletableFuture<ResGetCheckDBAvailable> requestGetCheckDBAvailable(ReqGetCheckDBAvailable reqGetCheckDBAvailable) {
        if (reqGetCheckDBAvailable.getResponse() == null) {
            reqGetCheckDBAvailable.setResponse(new ResGetCheckDBAvailable());
        }
        String str = this.component;
        if (str != null) {
            reqGetCheckDBAvailable.setComponent(str);
            reqGetCheckDBAvailable.getResponse().setComponent(this.component);
        }
        return Communication.request(this.contextUtil.createFromClass(reqGetCheckDBAvailable));
    }

    @Override // api.requests.com.neusoft.common.commonsysapiinverse.ICommonSysApiInverseRequests
    public CompletableFuture<ResGetCheckDBAvailable> requestGetCheckDBAvailable(ReqGetCheckDBAvailable reqGetCheckDBAvailable, final Duration duration) {
        return requestGetCheckDBAvailable(reqGetCheckDBAvailable).thenCompose((Function<? super ResGetCheckDBAvailable, ? extends CompletionStage<U>>) new Function<ResGetCheckDBAvailable, CompletableFuture<ResGetCheckDBAvailable>>() { // from class: api.requests.com.neusoft.common.commonsysapiinverse.CommonSysApiInverseRequests.362
            @Override // java9.util.function.Function
            public CompletableFuture<ResGetCheckDBAvailable> apply(ResGetCheckDBAvailable resGetCheckDBAvailable) {
                return CommonUtil.delayAsync(resGetCheckDBAvailable, duration, CommonSysApiInverseRequests.this.contextUtil.getDefaultExecutor());
            }
        });
    }

    @Override // api.requests.com.neusoft.common.commonsysapiinverse.ICommonSysApiInverseRequests
    public CompletableFuture<ResGetCheckDBAvailable> requestGetCheckDBAvailable() {
        return requestGetCheckDBAvailable(new ReqGetCheckDBAvailable());
    }

    @Override // api.requests.com.neusoft.common.commonsysapiinverse.ICommonSysApiInverseRequests
    public CompletableFuture<ResGetCheckDBAvailable> requestGetCheckDBAvailable(Duration duration) {
        return requestGetCheckDBAvailable(new ReqGetCheckDBAvailable(), duration);
    }

    @Override // api.requests.com.neusoft.common.commonsysapiinverse.ICommonSysApiInverseRequests
    public CompletableFuture<Void> fireGetCheckDBAvailable(ReqGetCheckDBAvailable reqGetCheckDBAvailable) {
        reqGetCheckDBAvailable.setResponse(null);
        String str = this.component;
        if (str != null) {
            reqGetCheckDBAvailable.setComponent(str);
        }
        return Communication.request(this.contextUtil.createFromClass(reqGetCheckDBAvailable)).thenCompose(new Function<Object, CompletableFuture<Void>>() { // from class: api.requests.com.neusoft.common.commonsysapiinverse.CommonSysApiInverseRequests.363
            @Override // java9.util.function.Function
            public CompletableFuture<Void> apply(Object obj) {
                return CommonSysApiInverseRequests.completedVoidFuture;
            }
        });
    }

    @Override // api.requests.com.neusoft.common.commonsysapiinverse.ICommonSysApiInverseRequests
    public CompletableFuture<Void> fireGetCheckDBAvailable(ReqGetCheckDBAvailable reqGetCheckDBAvailable, final Duration duration) {
        return fireGetCheckDBAvailable(reqGetCheckDBAvailable).thenCompose((Function<? super Void, ? extends CompletionStage<U>>) new Function<Void, CompletableFuture<Void>>() { // from class: api.requests.com.neusoft.common.commonsysapiinverse.CommonSysApiInverseRequests.364
            @Override // java9.util.function.Function
            public CompletableFuture<Void> apply(Void r2) {
                return CommonUtil.asyncSleep(duration, CommonSysApiInverseRequests.this.contextUtil.getDefaultExecutor());
            }
        });
    }

    @Override // api.requests.com.neusoft.common.commonsysapiinverse.ICommonSysApiInverseRequests
    public CompletableFuture<Void> fireGetCheckDBAvailable() {
        return fireGetCheckDBAvailable(new ReqGetCheckDBAvailable());
    }

    @Override // api.requests.com.neusoft.common.commonsysapiinverse.ICommonSysApiInverseRequests
    public CompletableFuture<Void> fireGetCheckDBAvailable(final Duration duration) {
        return fireGetCheckDBAvailable(new ReqGetCheckDBAvailable()).thenCompose((Function<? super Void, ? extends CompletionStage<U>>) new Function<Void, CompletableFuture<Void>>() { // from class: api.requests.com.neusoft.common.commonsysapiinverse.CommonSysApiInverseRequests.365
            @Override // java9.util.function.Function
            public CompletableFuture<Void> apply(Void r2) {
                return CommonUtil.asyncSleep(duration, CommonSysApiInverseRequests.this.contextUtil.getDefaultExecutor());
            }
        });
    }

    @Override // api.requests.com.neusoft.common.commonsysapiinverse.ICommonSysApiInverseRequests
    public CompletableFuture<ResGetAccessCodingAvailable> requestGetAccessCodingAvailable(ReqGetAccessCodingAvailable reqGetAccessCodingAvailable) {
        if (reqGetAccessCodingAvailable.getResponse() == null) {
            reqGetAccessCodingAvailable.setResponse(new ResGetAccessCodingAvailable());
        }
        String str = this.component;
        if (str != null) {
            reqGetAccessCodingAvailable.setComponent(str);
            reqGetAccessCodingAvailable.getResponse().setComponent(this.component);
        }
        return Communication.request(this.contextUtil.createFromClass(reqGetAccessCodingAvailable));
    }

    @Override // api.requests.com.neusoft.common.commonsysapiinverse.ICommonSysApiInverseRequests
    public CompletableFuture<ResGetAccessCodingAvailable> requestGetAccessCodingAvailable(ReqGetAccessCodingAvailable reqGetAccessCodingAvailable, final Duration duration) {
        return requestGetAccessCodingAvailable(reqGetAccessCodingAvailable).thenCompose((Function<? super ResGetAccessCodingAvailable, ? extends CompletionStage<U>>) new Function<ResGetAccessCodingAvailable, CompletableFuture<ResGetAccessCodingAvailable>>() { // from class: api.requests.com.neusoft.common.commonsysapiinverse.CommonSysApiInverseRequests.366
            @Override // java9.util.function.Function
            public CompletableFuture<ResGetAccessCodingAvailable> apply(ResGetAccessCodingAvailable resGetAccessCodingAvailable) {
                return CommonUtil.delayAsync(resGetAccessCodingAvailable, duration, CommonSysApiInverseRequests.this.contextUtil.getDefaultExecutor());
            }
        });
    }

    @Override // api.requests.com.neusoft.common.commonsysapiinverse.ICommonSysApiInverseRequests
    public CompletableFuture<ResGetAccessCodingAvailable> requestGetAccessCodingAvailable() {
        return requestGetAccessCodingAvailable(new ReqGetAccessCodingAvailable());
    }

    @Override // api.requests.com.neusoft.common.commonsysapiinverse.ICommonSysApiInverseRequests
    public CompletableFuture<ResGetAccessCodingAvailable> requestGetAccessCodingAvailable(Duration duration) {
        return requestGetAccessCodingAvailable(new ReqGetAccessCodingAvailable(), duration);
    }

    @Override // api.requests.com.neusoft.common.commonsysapiinverse.ICommonSysApiInverseRequests
    public CompletableFuture<Void> fireGetAccessCodingAvailable(ReqGetAccessCodingAvailable reqGetAccessCodingAvailable) {
        reqGetAccessCodingAvailable.setResponse(null);
        String str = this.component;
        if (str != null) {
            reqGetAccessCodingAvailable.setComponent(str);
        }
        return Communication.request(this.contextUtil.createFromClass(reqGetAccessCodingAvailable)).thenCompose(new Function<Object, CompletableFuture<Void>>() { // from class: api.requests.com.neusoft.common.commonsysapiinverse.CommonSysApiInverseRequests.367
            @Override // java9.util.function.Function
            public CompletableFuture<Void> apply(Object obj) {
                return CommonSysApiInverseRequests.completedVoidFuture;
            }
        });
    }

    @Override // api.requests.com.neusoft.common.commonsysapiinverse.ICommonSysApiInverseRequests
    public CompletableFuture<Void> fireGetAccessCodingAvailable(ReqGetAccessCodingAvailable reqGetAccessCodingAvailable, final Duration duration) {
        return fireGetAccessCodingAvailable(reqGetAccessCodingAvailable).thenCompose((Function<? super Void, ? extends CompletionStage<U>>) new Function<Void, CompletableFuture<Void>>() { // from class: api.requests.com.neusoft.common.commonsysapiinverse.CommonSysApiInverseRequests.368
            @Override // java9.util.function.Function
            public CompletableFuture<Void> apply(Void r2) {
                return CommonUtil.asyncSleep(duration, CommonSysApiInverseRequests.this.contextUtil.getDefaultExecutor());
            }
        });
    }

    @Override // api.requests.com.neusoft.common.commonsysapiinverse.ICommonSysApiInverseRequests
    public CompletableFuture<Void> fireGetAccessCodingAvailable() {
        return fireGetAccessCodingAvailable(new ReqGetAccessCodingAvailable());
    }

    @Override // api.requests.com.neusoft.common.commonsysapiinverse.ICommonSysApiInverseRequests
    public CompletableFuture<Void> fireGetAccessCodingAvailable(final Duration duration) {
        return fireGetAccessCodingAvailable(new ReqGetAccessCodingAvailable()).thenCompose((Function<? super Void, ? extends CompletionStage<U>>) new Function<Void, CompletableFuture<Void>>() { // from class: api.requests.com.neusoft.common.commonsysapiinverse.CommonSysApiInverseRequests.369
            @Override // java9.util.function.Function
            public CompletableFuture<Void> apply(Void r2) {
                return CommonUtil.asyncSleep(duration, CommonSysApiInverseRequests.this.contextUtil.getDefaultExecutor());
            }
        });
    }

    @Override // api.requests.com.neusoft.common.commonsysapiinverse.ICommonSysApiInverseRequests
    public CompletableFuture<ResGetRequestDeviceId> requestGetRequestDeviceId(ReqGetRequestDeviceId reqGetRequestDeviceId) {
        if (reqGetRequestDeviceId.getResponse() == null) {
            reqGetRequestDeviceId.setResponse(new ResGetRequestDeviceId());
        }
        String str = this.component;
        if (str != null) {
            reqGetRequestDeviceId.setComponent(str);
            reqGetRequestDeviceId.getResponse().setComponent(this.component);
        }
        return Communication.request(this.contextUtil.createFromClass(reqGetRequestDeviceId));
    }

    @Override // api.requests.com.neusoft.common.commonsysapiinverse.ICommonSysApiInverseRequests
    public CompletableFuture<ResGetRequestDeviceId> requestGetRequestDeviceId(ReqGetRequestDeviceId reqGetRequestDeviceId, final Duration duration) {
        return requestGetRequestDeviceId(reqGetRequestDeviceId).thenCompose((Function<? super ResGetRequestDeviceId, ? extends CompletionStage<U>>) new Function<ResGetRequestDeviceId, CompletableFuture<ResGetRequestDeviceId>>() { // from class: api.requests.com.neusoft.common.commonsysapiinverse.CommonSysApiInverseRequests.370
            @Override // java9.util.function.Function
            public CompletableFuture<ResGetRequestDeviceId> apply(ResGetRequestDeviceId resGetRequestDeviceId) {
                return CommonUtil.delayAsync(resGetRequestDeviceId, duration, CommonSysApiInverseRequests.this.contextUtil.getDefaultExecutor());
            }
        });
    }

    @Override // api.requests.com.neusoft.common.commonsysapiinverse.ICommonSysApiInverseRequests
    public CompletableFuture<ResGetRequestDeviceId> requestGetRequestDeviceId() {
        return requestGetRequestDeviceId(new ReqGetRequestDeviceId());
    }

    @Override // api.requests.com.neusoft.common.commonsysapiinverse.ICommonSysApiInverseRequests
    public CompletableFuture<ResGetRequestDeviceId> requestGetRequestDeviceId(Duration duration) {
        return requestGetRequestDeviceId(new ReqGetRequestDeviceId(), duration);
    }

    @Override // api.requests.com.neusoft.common.commonsysapiinverse.ICommonSysApiInverseRequests
    public CompletableFuture<Void> fireGetRequestDeviceId(ReqGetRequestDeviceId reqGetRequestDeviceId) {
        reqGetRequestDeviceId.setResponse(null);
        String str = this.component;
        if (str != null) {
            reqGetRequestDeviceId.setComponent(str);
        }
        return Communication.request(this.contextUtil.createFromClass(reqGetRequestDeviceId)).thenCompose(new Function<Object, CompletableFuture<Void>>() { // from class: api.requests.com.neusoft.common.commonsysapiinverse.CommonSysApiInverseRequests.371
            @Override // java9.util.function.Function
            public CompletableFuture<Void> apply(Object obj) {
                return CommonSysApiInverseRequests.completedVoidFuture;
            }
        });
    }

    @Override // api.requests.com.neusoft.common.commonsysapiinverse.ICommonSysApiInverseRequests
    public CompletableFuture<Void> fireGetRequestDeviceId(ReqGetRequestDeviceId reqGetRequestDeviceId, final Duration duration) {
        return fireGetRequestDeviceId(reqGetRequestDeviceId).thenCompose((Function<? super Void, ? extends CompletionStage<U>>) new Function<Void, CompletableFuture<Void>>() { // from class: api.requests.com.neusoft.common.commonsysapiinverse.CommonSysApiInverseRequests.372
            @Override // java9.util.function.Function
            public CompletableFuture<Void> apply(Void r2) {
                return CommonUtil.asyncSleep(duration, CommonSysApiInverseRequests.this.contextUtil.getDefaultExecutor());
            }
        });
    }

    @Override // api.requests.com.neusoft.common.commonsysapiinverse.ICommonSysApiInverseRequests
    public CompletableFuture<Void> fireGetRequestDeviceId() {
        return fireGetRequestDeviceId(new ReqGetRequestDeviceId());
    }

    @Override // api.requests.com.neusoft.common.commonsysapiinverse.ICommonSysApiInverseRequests
    public CompletableFuture<Void> fireGetRequestDeviceId(final Duration duration) {
        return fireGetRequestDeviceId(new ReqGetRequestDeviceId()).thenCompose((Function<? super Void, ? extends CompletionStage<U>>) new Function<Void, CompletableFuture<Void>>() { // from class: api.requests.com.neusoft.common.commonsysapiinverse.CommonSysApiInverseRequests.373
            @Override // java9.util.function.Function
            public CompletableFuture<Void> apply(Void r2) {
                return CommonUtil.asyncSleep(duration, CommonSysApiInverseRequests.this.contextUtil.getDefaultExecutor());
            }
        });
    }

    @Override // api.requests.com.neusoft.common.commonsysapiinverse.ICommonSysApiInverseRequests
    public CompletableFuture<ResGetRequestExpiredDate> requestGetRequestExpiredDate(ReqGetRequestExpiredDate reqGetRequestExpiredDate) {
        if (reqGetRequestExpiredDate.getResponse() == null) {
            reqGetRequestExpiredDate.setResponse(new ResGetRequestExpiredDate());
        }
        String str = this.component;
        if (str != null) {
            reqGetRequestExpiredDate.setComponent(str);
            reqGetRequestExpiredDate.getResponse().setComponent(this.component);
        }
        return Communication.request(this.contextUtil.createFromClass(reqGetRequestExpiredDate));
    }

    @Override // api.requests.com.neusoft.common.commonsysapiinverse.ICommonSysApiInverseRequests
    public CompletableFuture<ResGetRequestExpiredDate> requestGetRequestExpiredDate(ReqGetRequestExpiredDate reqGetRequestExpiredDate, final Duration duration) {
        return requestGetRequestExpiredDate(reqGetRequestExpiredDate).thenCompose((Function<? super ResGetRequestExpiredDate, ? extends CompletionStage<U>>) new Function<ResGetRequestExpiredDate, CompletableFuture<ResGetRequestExpiredDate>>() { // from class: api.requests.com.neusoft.common.commonsysapiinverse.CommonSysApiInverseRequests.374
            @Override // java9.util.function.Function
            public CompletableFuture<ResGetRequestExpiredDate> apply(ResGetRequestExpiredDate resGetRequestExpiredDate) {
                return CommonUtil.delayAsync(resGetRequestExpiredDate, duration, CommonSysApiInverseRequests.this.contextUtil.getDefaultExecutor());
            }
        });
    }

    @Override // api.requests.com.neusoft.common.commonsysapiinverse.ICommonSysApiInverseRequests
    public CompletableFuture<ResGetRequestExpiredDate> requestGetRequestExpiredDate() {
        return requestGetRequestExpiredDate(new ReqGetRequestExpiredDate());
    }

    @Override // api.requests.com.neusoft.common.commonsysapiinverse.ICommonSysApiInverseRequests
    public CompletableFuture<ResGetRequestExpiredDate> requestGetRequestExpiredDate(Duration duration) {
        return requestGetRequestExpiredDate(new ReqGetRequestExpiredDate(), duration);
    }

    @Override // api.requests.com.neusoft.common.commonsysapiinverse.ICommonSysApiInverseRequests
    public CompletableFuture<Void> fireGetRequestExpiredDate(ReqGetRequestExpiredDate reqGetRequestExpiredDate) {
        reqGetRequestExpiredDate.setResponse(null);
        String str = this.component;
        if (str != null) {
            reqGetRequestExpiredDate.setComponent(str);
        }
        return Communication.request(this.contextUtil.createFromClass(reqGetRequestExpiredDate)).thenCompose(new Function<Object, CompletableFuture<Void>>() { // from class: api.requests.com.neusoft.common.commonsysapiinverse.CommonSysApiInverseRequests.375
            @Override // java9.util.function.Function
            public CompletableFuture<Void> apply(Object obj) {
                return CommonSysApiInverseRequests.completedVoidFuture;
            }
        });
    }

    @Override // api.requests.com.neusoft.common.commonsysapiinverse.ICommonSysApiInverseRequests
    public CompletableFuture<Void> fireGetRequestExpiredDate(ReqGetRequestExpiredDate reqGetRequestExpiredDate, final Duration duration) {
        return fireGetRequestExpiredDate(reqGetRequestExpiredDate).thenCompose((Function<? super Void, ? extends CompletionStage<U>>) new Function<Void, CompletableFuture<Void>>() { // from class: api.requests.com.neusoft.common.commonsysapiinverse.CommonSysApiInverseRequests.376
            @Override // java9.util.function.Function
            public CompletableFuture<Void> apply(Void r2) {
                return CommonUtil.asyncSleep(duration, CommonSysApiInverseRequests.this.contextUtil.getDefaultExecutor());
            }
        });
    }

    @Override // api.requests.com.neusoft.common.commonsysapiinverse.ICommonSysApiInverseRequests
    public CompletableFuture<Void> fireGetRequestExpiredDate() {
        return fireGetRequestExpiredDate(new ReqGetRequestExpiredDate());
    }

    @Override // api.requests.com.neusoft.common.commonsysapiinverse.ICommonSysApiInverseRequests
    public CompletableFuture<Void> fireGetRequestExpiredDate(final Duration duration) {
        return fireGetRequestExpiredDate(new ReqGetRequestExpiredDate()).thenCompose((Function<? super Void, ? extends CompletionStage<U>>) new Function<Void, CompletableFuture<Void>>() { // from class: api.requests.com.neusoft.common.commonsysapiinverse.CommonSysApiInverseRequests.377
            @Override // java9.util.function.Function
            public CompletableFuture<Void> apply(Void r2) {
                return CommonUtil.asyncSleep(duration, CommonSysApiInverseRequests.this.contextUtil.getDefaultExecutor());
            }
        });
    }

    @Override // api.requests.com.neusoft.common.commonsysapiinverse.ICommonSysApiInverseRequests
    public CompletableFuture<ResGetResponseExpiredDate> requestGetResponseExpiredDate(ReqGetResponseExpiredDate reqGetResponseExpiredDate) {
        if (reqGetResponseExpiredDate.getResponse() == null) {
            reqGetResponseExpiredDate.setResponse(new ResGetResponseExpiredDate());
        }
        String str = this.component;
        if (str != null) {
            reqGetResponseExpiredDate.setComponent(str);
            reqGetResponseExpiredDate.getResponse().setComponent(this.component);
        }
        return Communication.request(this.contextUtil.createFromClass(reqGetResponseExpiredDate));
    }

    @Override // api.requests.com.neusoft.common.commonsysapiinverse.ICommonSysApiInverseRequests
    public CompletableFuture<ResGetResponseExpiredDate> requestGetResponseExpiredDate(ReqGetResponseExpiredDate reqGetResponseExpiredDate, final Duration duration) {
        return requestGetResponseExpiredDate(reqGetResponseExpiredDate).thenCompose((Function<? super ResGetResponseExpiredDate, ? extends CompletionStage<U>>) new Function<ResGetResponseExpiredDate, CompletableFuture<ResGetResponseExpiredDate>>() { // from class: api.requests.com.neusoft.common.commonsysapiinverse.CommonSysApiInverseRequests.378
            @Override // java9.util.function.Function
            public CompletableFuture<ResGetResponseExpiredDate> apply(ResGetResponseExpiredDate resGetResponseExpiredDate) {
                return CommonUtil.delayAsync(resGetResponseExpiredDate, duration, CommonSysApiInverseRequests.this.contextUtil.getDefaultExecutor());
            }
        });
    }

    @Override // api.requests.com.neusoft.common.commonsysapiinverse.ICommonSysApiInverseRequests
    public CompletableFuture<ResGetResponseExpiredDate> requestGetResponseExpiredDate() {
        return requestGetResponseExpiredDate(new ReqGetResponseExpiredDate());
    }

    @Override // api.requests.com.neusoft.common.commonsysapiinverse.ICommonSysApiInverseRequests
    public CompletableFuture<ResGetResponseExpiredDate> requestGetResponseExpiredDate(Duration duration) {
        return requestGetResponseExpiredDate(new ReqGetResponseExpiredDate(), duration);
    }

    @Override // api.requests.com.neusoft.common.commonsysapiinverse.ICommonSysApiInverseRequests
    public CompletableFuture<Void> fireGetResponseExpiredDate(ReqGetResponseExpiredDate reqGetResponseExpiredDate) {
        reqGetResponseExpiredDate.setResponse(null);
        String str = this.component;
        if (str != null) {
            reqGetResponseExpiredDate.setComponent(str);
        }
        return Communication.request(this.contextUtil.createFromClass(reqGetResponseExpiredDate)).thenCompose(new Function<Object, CompletableFuture<Void>>() { // from class: api.requests.com.neusoft.common.commonsysapiinverse.CommonSysApiInverseRequests.379
            @Override // java9.util.function.Function
            public CompletableFuture<Void> apply(Object obj) {
                return CommonSysApiInverseRequests.completedVoidFuture;
            }
        });
    }

    @Override // api.requests.com.neusoft.common.commonsysapiinverse.ICommonSysApiInverseRequests
    public CompletableFuture<Void> fireGetResponseExpiredDate(ReqGetResponseExpiredDate reqGetResponseExpiredDate, final Duration duration) {
        return fireGetResponseExpiredDate(reqGetResponseExpiredDate).thenCompose((Function<? super Void, ? extends CompletionStage<U>>) new Function<Void, CompletableFuture<Void>>() { // from class: api.requests.com.neusoft.common.commonsysapiinverse.CommonSysApiInverseRequests.380
            @Override // java9.util.function.Function
            public CompletableFuture<Void> apply(Void r2) {
                return CommonUtil.asyncSleep(duration, CommonSysApiInverseRequests.this.contextUtil.getDefaultExecutor());
            }
        });
    }

    @Override // api.requests.com.neusoft.common.commonsysapiinverse.ICommonSysApiInverseRequests
    public CompletableFuture<Void> fireGetResponseExpiredDate() {
        return fireGetResponseExpiredDate(new ReqGetResponseExpiredDate());
    }

    @Override // api.requests.com.neusoft.common.commonsysapiinverse.ICommonSysApiInverseRequests
    public CompletableFuture<Void> fireGetResponseExpiredDate(final Duration duration) {
        return fireGetResponseExpiredDate(new ReqGetResponseExpiredDate()).thenCompose((Function<? super Void, ? extends CompletionStage<U>>) new Function<Void, CompletableFuture<Void>>() { // from class: api.requests.com.neusoft.common.commonsysapiinverse.CommonSysApiInverseRequests.381
            @Override // java9.util.function.Function
            public CompletableFuture<Void> apply(Void r2) {
                return CommonUtil.asyncSleep(duration, CommonSysApiInverseRequests.this.contextUtil.getDefaultExecutor());
            }
        });
    }

    @Override // api.requests.com.neusoft.common.commonsysapiinverse.ICommonSysApiInverseRequests
    public CompletableFuture<ResGetRequestInstallationPath> requestGetRequestInstallationPath(ReqGetRequestInstallationPath reqGetRequestInstallationPath) {
        if (reqGetRequestInstallationPath.getResponse() == null) {
            reqGetRequestInstallationPath.setResponse(new ResGetRequestInstallationPath());
        }
        String str = this.component;
        if (str != null) {
            reqGetRequestInstallationPath.setComponent(str);
            reqGetRequestInstallationPath.getResponse().setComponent(this.component);
        }
        return Communication.request(this.contextUtil.createFromClass(reqGetRequestInstallationPath));
    }

    @Override // api.requests.com.neusoft.common.commonsysapiinverse.ICommonSysApiInverseRequests
    public CompletableFuture<ResGetRequestInstallationPath> requestGetRequestInstallationPath(ReqGetRequestInstallationPath reqGetRequestInstallationPath, final Duration duration) {
        return requestGetRequestInstallationPath(reqGetRequestInstallationPath).thenCompose((Function<? super ResGetRequestInstallationPath, ? extends CompletionStage<U>>) new Function<ResGetRequestInstallationPath, CompletableFuture<ResGetRequestInstallationPath>>() { // from class: api.requests.com.neusoft.common.commonsysapiinverse.CommonSysApiInverseRequests.382
            @Override // java9.util.function.Function
            public CompletableFuture<ResGetRequestInstallationPath> apply(ResGetRequestInstallationPath resGetRequestInstallationPath) {
                return CommonUtil.delayAsync(resGetRequestInstallationPath, duration, CommonSysApiInverseRequests.this.contextUtil.getDefaultExecutor());
            }
        });
    }

    @Override // api.requests.com.neusoft.common.commonsysapiinverse.ICommonSysApiInverseRequests
    public CompletableFuture<ResGetRequestInstallationPath> requestGetRequestInstallationPath() {
        return requestGetRequestInstallationPath(new ReqGetRequestInstallationPath());
    }

    @Override // api.requests.com.neusoft.common.commonsysapiinverse.ICommonSysApiInverseRequests
    public CompletableFuture<ResGetRequestInstallationPath> requestGetRequestInstallationPath(Duration duration) {
        return requestGetRequestInstallationPath(new ReqGetRequestInstallationPath(), duration);
    }

    @Override // api.requests.com.neusoft.common.commonsysapiinverse.ICommonSysApiInverseRequests
    public CompletableFuture<Void> fireGetRequestInstallationPath(ReqGetRequestInstallationPath reqGetRequestInstallationPath) {
        reqGetRequestInstallationPath.setResponse(null);
        String str = this.component;
        if (str != null) {
            reqGetRequestInstallationPath.setComponent(str);
        }
        return Communication.request(this.contextUtil.createFromClass(reqGetRequestInstallationPath)).thenCompose(new Function<Object, CompletableFuture<Void>>() { // from class: api.requests.com.neusoft.common.commonsysapiinverse.CommonSysApiInverseRequests.383
            @Override // java9.util.function.Function
            public CompletableFuture<Void> apply(Object obj) {
                return CommonSysApiInverseRequests.completedVoidFuture;
            }
        });
    }

    @Override // api.requests.com.neusoft.common.commonsysapiinverse.ICommonSysApiInverseRequests
    public CompletableFuture<Void> fireGetRequestInstallationPath(ReqGetRequestInstallationPath reqGetRequestInstallationPath, final Duration duration) {
        return fireGetRequestInstallationPath(reqGetRequestInstallationPath).thenCompose((Function<? super Void, ? extends CompletionStage<U>>) new Function<Void, CompletableFuture<Void>>() { // from class: api.requests.com.neusoft.common.commonsysapiinverse.CommonSysApiInverseRequests.384
            @Override // java9.util.function.Function
            public CompletableFuture<Void> apply(Void r2) {
                return CommonUtil.asyncSleep(duration, CommonSysApiInverseRequests.this.contextUtil.getDefaultExecutor());
            }
        });
    }

    @Override // api.requests.com.neusoft.common.commonsysapiinverse.ICommonSysApiInverseRequests
    public CompletableFuture<Void> fireGetRequestInstallationPath() {
        return fireGetRequestInstallationPath(new ReqGetRequestInstallationPath());
    }

    @Override // api.requests.com.neusoft.common.commonsysapiinverse.ICommonSysApiInverseRequests
    public CompletableFuture<Void> fireGetRequestInstallationPath(final Duration duration) {
        return fireGetRequestInstallationPath(new ReqGetRequestInstallationPath()).thenCompose((Function<? super Void, ? extends CompletionStage<U>>) new Function<Void, CompletableFuture<Void>>() { // from class: api.requests.com.neusoft.common.commonsysapiinverse.CommonSysApiInverseRequests.385
            @Override // java9.util.function.Function
            public CompletableFuture<Void> apply(Void r2) {
                return CommonUtil.asyncSleep(duration, CommonSysApiInverseRequests.this.contextUtil.getDefaultExecutor());
            }
        });
    }

    @Override // api.requests.com.neusoft.common.commonsysapiinverse.ICommonSysApiInverseRequests
    public CompletableFuture<ResGetLaunchApp> requestGetLaunchApp(ReqGetLaunchApp reqGetLaunchApp) {
        if (reqGetLaunchApp.getResponse() == null) {
            reqGetLaunchApp.setResponse(new ResGetLaunchApp());
        }
        String str = this.component;
        if (str != null) {
            reqGetLaunchApp.setComponent(str);
            reqGetLaunchApp.getResponse().setComponent(this.component);
        }
        return Communication.request(this.contextUtil.createFromClass(reqGetLaunchApp));
    }

    @Override // api.requests.com.neusoft.common.commonsysapiinverse.ICommonSysApiInverseRequests
    public CompletableFuture<ResGetLaunchApp> requestGetLaunchApp(ReqGetLaunchApp reqGetLaunchApp, final Duration duration) {
        return requestGetLaunchApp(reqGetLaunchApp).thenCompose((Function<? super ResGetLaunchApp, ? extends CompletionStage<U>>) new Function<ResGetLaunchApp, CompletableFuture<ResGetLaunchApp>>() { // from class: api.requests.com.neusoft.common.commonsysapiinverse.CommonSysApiInverseRequests.386
            @Override // java9.util.function.Function
            public CompletableFuture<ResGetLaunchApp> apply(ResGetLaunchApp resGetLaunchApp) {
                return CommonUtil.delayAsync(resGetLaunchApp, duration, CommonSysApiInverseRequests.this.contextUtil.getDefaultExecutor());
            }
        });
    }

    @Override // api.requests.com.neusoft.common.commonsysapiinverse.ICommonSysApiInverseRequests
    public CompletableFuture<ResGetLaunchApp> requestGetLaunchApp() {
        return requestGetLaunchApp(new ReqGetLaunchApp());
    }

    @Override // api.requests.com.neusoft.common.commonsysapiinverse.ICommonSysApiInverseRequests
    public CompletableFuture<ResGetLaunchApp> requestGetLaunchApp(Duration duration) {
        return requestGetLaunchApp(new ReqGetLaunchApp(), duration);
    }

    @Override // api.requests.com.neusoft.common.commonsysapiinverse.ICommonSysApiInverseRequests
    public CompletableFuture<Void> fireGetLaunchApp(ReqGetLaunchApp reqGetLaunchApp) {
        reqGetLaunchApp.setResponse(null);
        String str = this.component;
        if (str != null) {
            reqGetLaunchApp.setComponent(str);
        }
        return Communication.request(this.contextUtil.createFromClass(reqGetLaunchApp)).thenCompose(new Function<Object, CompletableFuture<Void>>() { // from class: api.requests.com.neusoft.common.commonsysapiinverse.CommonSysApiInverseRequests.387
            @Override // java9.util.function.Function
            public CompletableFuture<Void> apply(Object obj) {
                return CommonSysApiInverseRequests.completedVoidFuture;
            }
        });
    }

    @Override // api.requests.com.neusoft.common.commonsysapiinverse.ICommonSysApiInverseRequests
    public CompletableFuture<Void> fireGetLaunchApp(ReqGetLaunchApp reqGetLaunchApp, final Duration duration) {
        return fireGetLaunchApp(reqGetLaunchApp).thenCompose((Function<? super Void, ? extends CompletionStage<U>>) new Function<Void, CompletableFuture<Void>>() { // from class: api.requests.com.neusoft.common.commonsysapiinverse.CommonSysApiInverseRequests.388
            @Override // java9.util.function.Function
            public CompletableFuture<Void> apply(Void r2) {
                return CommonUtil.asyncSleep(duration, CommonSysApiInverseRequests.this.contextUtil.getDefaultExecutor());
            }
        });
    }

    @Override // api.requests.com.neusoft.common.commonsysapiinverse.ICommonSysApiInverseRequests
    public CompletableFuture<Void> fireGetLaunchApp() {
        return fireGetLaunchApp(new ReqGetLaunchApp());
    }

    @Override // api.requests.com.neusoft.common.commonsysapiinverse.ICommonSysApiInverseRequests
    public CompletableFuture<Void> fireGetLaunchApp(final Duration duration) {
        return fireGetLaunchApp(new ReqGetLaunchApp()).thenCompose((Function<? super Void, ? extends CompletionStage<U>>) new Function<Void, CompletableFuture<Void>>() { // from class: api.requests.com.neusoft.common.commonsysapiinverse.CommonSysApiInverseRequests.389
            @Override // java9.util.function.Function
            public CompletableFuture<Void> apply(Void r2) {
                return CommonUtil.asyncSleep(duration, CommonSysApiInverseRequests.this.contextUtil.getDefaultExecutor());
            }
        });
    }

    @Override // api.requests.com.neusoft.common.commonsysapiinverse.ICommonSysApiInverseRequests
    public CompletableFuture<ResGetCallUpStatus> requestGetCallUpStatus(ReqGetCallUpStatus reqGetCallUpStatus) {
        if (reqGetCallUpStatus.getResponse() == null) {
            reqGetCallUpStatus.setResponse(new ResGetCallUpStatus());
        }
        String str = this.component;
        if (str != null) {
            reqGetCallUpStatus.setComponent(str);
            reqGetCallUpStatus.getResponse().setComponent(this.component);
        }
        return Communication.request(this.contextUtil.createFromClass(reqGetCallUpStatus));
    }

    @Override // api.requests.com.neusoft.common.commonsysapiinverse.ICommonSysApiInverseRequests
    public CompletableFuture<ResGetCallUpStatus> requestGetCallUpStatus(ReqGetCallUpStatus reqGetCallUpStatus, final Duration duration) {
        return requestGetCallUpStatus(reqGetCallUpStatus).thenCompose((Function<? super ResGetCallUpStatus, ? extends CompletionStage<U>>) new Function<ResGetCallUpStatus, CompletableFuture<ResGetCallUpStatus>>() { // from class: api.requests.com.neusoft.common.commonsysapiinverse.CommonSysApiInverseRequests.390
            @Override // java9.util.function.Function
            public CompletableFuture<ResGetCallUpStatus> apply(ResGetCallUpStatus resGetCallUpStatus) {
                return CommonUtil.delayAsync(resGetCallUpStatus, duration, CommonSysApiInverseRequests.this.contextUtil.getDefaultExecutor());
            }
        });
    }

    @Override // api.requests.com.neusoft.common.commonsysapiinverse.ICommonSysApiInverseRequests
    public CompletableFuture<ResGetCallUpStatus> requestGetCallUpStatus() {
        return requestGetCallUpStatus(new ReqGetCallUpStatus());
    }

    @Override // api.requests.com.neusoft.common.commonsysapiinverse.ICommonSysApiInverseRequests
    public CompletableFuture<ResGetCallUpStatus> requestGetCallUpStatus(Duration duration) {
        return requestGetCallUpStatus(new ReqGetCallUpStatus(), duration);
    }

    @Override // api.requests.com.neusoft.common.commonsysapiinverse.ICommonSysApiInverseRequests
    public CompletableFuture<Void> fireGetCallUpStatus(ReqGetCallUpStatus reqGetCallUpStatus) {
        reqGetCallUpStatus.setResponse(null);
        String str = this.component;
        if (str != null) {
            reqGetCallUpStatus.setComponent(str);
        }
        return Communication.request(this.contextUtil.createFromClass(reqGetCallUpStatus)).thenCompose(new Function<Object, CompletableFuture<Void>>() { // from class: api.requests.com.neusoft.common.commonsysapiinverse.CommonSysApiInverseRequests.391
            @Override // java9.util.function.Function
            public CompletableFuture<Void> apply(Object obj) {
                return CommonSysApiInverseRequests.completedVoidFuture;
            }
        });
    }

    @Override // api.requests.com.neusoft.common.commonsysapiinverse.ICommonSysApiInverseRequests
    public CompletableFuture<Void> fireGetCallUpStatus(ReqGetCallUpStatus reqGetCallUpStatus, final Duration duration) {
        return fireGetCallUpStatus(reqGetCallUpStatus).thenCompose((Function<? super Void, ? extends CompletionStage<U>>) new Function<Void, CompletableFuture<Void>>() { // from class: api.requests.com.neusoft.common.commonsysapiinverse.CommonSysApiInverseRequests.392
            @Override // java9.util.function.Function
            public CompletableFuture<Void> apply(Void r2) {
                return CommonUtil.asyncSleep(duration, CommonSysApiInverseRequests.this.contextUtil.getDefaultExecutor());
            }
        });
    }

    @Override // api.requests.com.neusoft.common.commonsysapiinverse.ICommonSysApiInverseRequests
    public CompletableFuture<Void> fireGetCallUpStatus() {
        return fireGetCallUpStatus(new ReqGetCallUpStatus());
    }

    @Override // api.requests.com.neusoft.common.commonsysapiinverse.ICommonSysApiInverseRequests
    public CompletableFuture<Void> fireGetCallUpStatus(final Duration duration) {
        return fireGetCallUpStatus(new ReqGetCallUpStatus()).thenCompose((Function<? super Void, ? extends CompletionStage<U>>) new Function<Void, CompletableFuture<Void>>() { // from class: api.requests.com.neusoft.common.commonsysapiinverse.CommonSysApiInverseRequests.393
            @Override // java9.util.function.Function
            public CompletableFuture<Void> apply(Void r2) {
                return CommonUtil.asyncSleep(duration, CommonSysApiInverseRequests.this.contextUtil.getDefaultExecutor());
            }
        });
    }

    @Override // api.requests.com.neusoft.common.commonsysapiinverse.ICommonSysApiInverseRequests
    public CompletableFuture<ResGetPeripheralStatus> requestGetPeripheralStatus(ReqGetPeripheralStatus reqGetPeripheralStatus) {
        if (reqGetPeripheralStatus.getResponse() == null) {
            reqGetPeripheralStatus.setResponse(new ResGetPeripheralStatus());
        }
        String str = this.component;
        if (str != null) {
            reqGetPeripheralStatus.setComponent(str);
            reqGetPeripheralStatus.getResponse().setComponent(this.component);
        }
        return Communication.request(this.contextUtil.createFromClass(reqGetPeripheralStatus));
    }

    @Override // api.requests.com.neusoft.common.commonsysapiinverse.ICommonSysApiInverseRequests
    public CompletableFuture<ResGetPeripheralStatus> requestGetPeripheralStatus(ReqGetPeripheralStatus reqGetPeripheralStatus, final Duration duration) {
        return requestGetPeripheralStatus(reqGetPeripheralStatus).thenCompose((Function<? super ResGetPeripheralStatus, ? extends CompletionStage<U>>) new Function<ResGetPeripheralStatus, CompletableFuture<ResGetPeripheralStatus>>() { // from class: api.requests.com.neusoft.common.commonsysapiinverse.CommonSysApiInverseRequests.394
            @Override // java9.util.function.Function
            public CompletableFuture<ResGetPeripheralStatus> apply(ResGetPeripheralStatus resGetPeripheralStatus) {
                return CommonUtil.delayAsync(resGetPeripheralStatus, duration, CommonSysApiInverseRequests.this.contextUtil.getDefaultExecutor());
            }
        });
    }

    @Override // api.requests.com.neusoft.common.commonsysapiinverse.ICommonSysApiInverseRequests
    public CompletableFuture<ResGetPeripheralStatus> requestGetPeripheralStatus() {
        return requestGetPeripheralStatus(new ReqGetPeripheralStatus());
    }

    @Override // api.requests.com.neusoft.common.commonsysapiinverse.ICommonSysApiInverseRequests
    public CompletableFuture<ResGetPeripheralStatus> requestGetPeripheralStatus(Duration duration) {
        return requestGetPeripheralStatus(new ReqGetPeripheralStatus(), duration);
    }

    @Override // api.requests.com.neusoft.common.commonsysapiinverse.ICommonSysApiInverseRequests
    public CompletableFuture<Void> fireGetPeripheralStatus(ReqGetPeripheralStatus reqGetPeripheralStatus) {
        reqGetPeripheralStatus.setResponse(null);
        String str = this.component;
        if (str != null) {
            reqGetPeripheralStatus.setComponent(str);
        }
        return Communication.request(this.contextUtil.createFromClass(reqGetPeripheralStatus)).thenCompose(new Function<Object, CompletableFuture<Void>>() { // from class: api.requests.com.neusoft.common.commonsysapiinverse.CommonSysApiInverseRequests.395
            @Override // java9.util.function.Function
            public CompletableFuture<Void> apply(Object obj) {
                return CommonSysApiInverseRequests.completedVoidFuture;
            }
        });
    }

    @Override // api.requests.com.neusoft.common.commonsysapiinverse.ICommonSysApiInverseRequests
    public CompletableFuture<Void> fireGetPeripheralStatus(ReqGetPeripheralStatus reqGetPeripheralStatus, final Duration duration) {
        return fireGetPeripheralStatus(reqGetPeripheralStatus).thenCompose((Function<? super Void, ? extends CompletionStage<U>>) new Function<Void, CompletableFuture<Void>>() { // from class: api.requests.com.neusoft.common.commonsysapiinverse.CommonSysApiInverseRequests.396
            @Override // java9.util.function.Function
            public CompletableFuture<Void> apply(Void r2) {
                return CommonUtil.asyncSleep(duration, CommonSysApiInverseRequests.this.contextUtil.getDefaultExecutor());
            }
        });
    }

    @Override // api.requests.com.neusoft.common.commonsysapiinverse.ICommonSysApiInverseRequests
    public CompletableFuture<Void> fireGetPeripheralStatus() {
        return fireGetPeripheralStatus(new ReqGetPeripheralStatus());
    }

    @Override // api.requests.com.neusoft.common.commonsysapiinverse.ICommonSysApiInverseRequests
    public CompletableFuture<Void> fireGetPeripheralStatus(final Duration duration) {
        return fireGetPeripheralStatus(new ReqGetPeripheralStatus()).thenCompose((Function<? super Void, ? extends CompletionStage<U>>) new Function<Void, CompletableFuture<Void>>() { // from class: api.requests.com.neusoft.common.commonsysapiinverse.CommonSysApiInverseRequests.397
            @Override // java9.util.function.Function
            public CompletableFuture<Void> apply(Void r2) {
                return CommonUtil.asyncSleep(duration, CommonSysApiInverseRequests.this.contextUtil.getDefaultExecutor());
            }
        });
    }

    @Override // api.requests.com.neusoft.common.commonsysapiinverse.ICommonSysApiInverseRequests
    public CompletableFuture<ResGetStorageSpaceStatus> requestGetStorageSpaceStatus(ReqGetStorageSpaceStatus reqGetStorageSpaceStatus) {
        if (reqGetStorageSpaceStatus.getResponse() == null) {
            reqGetStorageSpaceStatus.setResponse(new ResGetStorageSpaceStatus());
        }
        String str = this.component;
        if (str != null) {
            reqGetStorageSpaceStatus.setComponent(str);
            reqGetStorageSpaceStatus.getResponse().setComponent(this.component);
        }
        return Communication.request(this.contextUtil.createFromClass(reqGetStorageSpaceStatus));
    }

    @Override // api.requests.com.neusoft.common.commonsysapiinverse.ICommonSysApiInverseRequests
    public CompletableFuture<ResGetStorageSpaceStatus> requestGetStorageSpaceStatus(ReqGetStorageSpaceStatus reqGetStorageSpaceStatus, final Duration duration) {
        return requestGetStorageSpaceStatus(reqGetStorageSpaceStatus).thenCompose((Function<? super ResGetStorageSpaceStatus, ? extends CompletionStage<U>>) new Function<ResGetStorageSpaceStatus, CompletableFuture<ResGetStorageSpaceStatus>>() { // from class: api.requests.com.neusoft.common.commonsysapiinverse.CommonSysApiInverseRequests.398
            @Override // java9.util.function.Function
            public CompletableFuture<ResGetStorageSpaceStatus> apply(ResGetStorageSpaceStatus resGetStorageSpaceStatus) {
                return CommonUtil.delayAsync(resGetStorageSpaceStatus, duration, CommonSysApiInverseRequests.this.contextUtil.getDefaultExecutor());
            }
        });
    }

    @Override // api.requests.com.neusoft.common.commonsysapiinverse.ICommonSysApiInverseRequests
    public CompletableFuture<ResGetStorageSpaceStatus> requestGetStorageSpaceStatus() {
        return requestGetStorageSpaceStatus(new ReqGetStorageSpaceStatus());
    }

    @Override // api.requests.com.neusoft.common.commonsysapiinverse.ICommonSysApiInverseRequests
    public CompletableFuture<ResGetStorageSpaceStatus> requestGetStorageSpaceStatus(Duration duration) {
        return requestGetStorageSpaceStatus(new ReqGetStorageSpaceStatus(), duration);
    }

    @Override // api.requests.com.neusoft.common.commonsysapiinverse.ICommonSysApiInverseRequests
    public CompletableFuture<Void> fireGetStorageSpaceStatus(ReqGetStorageSpaceStatus reqGetStorageSpaceStatus) {
        reqGetStorageSpaceStatus.setResponse(null);
        String str = this.component;
        if (str != null) {
            reqGetStorageSpaceStatus.setComponent(str);
        }
        return Communication.request(this.contextUtil.createFromClass(reqGetStorageSpaceStatus)).thenCompose(new Function<Object, CompletableFuture<Void>>() { // from class: api.requests.com.neusoft.common.commonsysapiinverse.CommonSysApiInverseRequests.399
            @Override // java9.util.function.Function
            public CompletableFuture<Void> apply(Object obj) {
                return CommonSysApiInverseRequests.completedVoidFuture;
            }
        });
    }

    @Override // api.requests.com.neusoft.common.commonsysapiinverse.ICommonSysApiInverseRequests
    public CompletableFuture<Void> fireGetStorageSpaceStatus(ReqGetStorageSpaceStatus reqGetStorageSpaceStatus, final Duration duration) {
        return fireGetStorageSpaceStatus(reqGetStorageSpaceStatus).thenCompose((Function<? super Void, ? extends CompletionStage<U>>) new Function<Void, CompletableFuture<Void>>() { // from class: api.requests.com.neusoft.common.commonsysapiinverse.CommonSysApiInverseRequests.400
            @Override // java9.util.function.Function
            public CompletableFuture<Void> apply(Void r2) {
                return CommonUtil.asyncSleep(duration, CommonSysApiInverseRequests.this.contextUtil.getDefaultExecutor());
            }
        });
    }

    @Override // api.requests.com.neusoft.common.commonsysapiinverse.ICommonSysApiInverseRequests
    public CompletableFuture<Void> fireGetStorageSpaceStatus() {
        return fireGetStorageSpaceStatus(new ReqGetStorageSpaceStatus());
    }

    @Override // api.requests.com.neusoft.common.commonsysapiinverse.ICommonSysApiInverseRequests
    public CompletableFuture<Void> fireGetStorageSpaceStatus(final Duration duration) {
        return fireGetStorageSpaceStatus(new ReqGetStorageSpaceStatus()).thenCompose((Function<? super Void, ? extends CompletionStage<U>>) new Function<Void, CompletableFuture<Void>>() { // from class: api.requests.com.neusoft.common.commonsysapiinverse.CommonSysApiInverseRequests.401
            @Override // java9.util.function.Function
            public CompletableFuture<Void> apply(Void r2) {
                return CommonUtil.asyncSleep(duration, CommonSysApiInverseRequests.this.contextUtil.getDefaultExecutor());
            }
        });
    }

    @Override // api.requests.com.neusoft.common.commonsysapiinverse.ICommonSysApiInverseRequests
    public CompletableFuture<ResGetReadFile> requestGetReadFile(ReqGetReadFile reqGetReadFile) {
        if (reqGetReadFile.getResponse() == null) {
            reqGetReadFile.setResponse(new ResGetReadFile());
        }
        String str = this.component;
        if (str != null) {
            reqGetReadFile.setComponent(str);
            reqGetReadFile.getResponse().setComponent(this.component);
        }
        return Communication.request(this.contextUtil.createFromClass(reqGetReadFile));
    }

    @Override // api.requests.com.neusoft.common.commonsysapiinverse.ICommonSysApiInverseRequests
    public CompletableFuture<ResGetReadFile> requestGetReadFile(ReqGetReadFile reqGetReadFile, final Duration duration) {
        return requestGetReadFile(reqGetReadFile).thenCompose((Function<? super ResGetReadFile, ? extends CompletionStage<U>>) new Function<ResGetReadFile, CompletableFuture<ResGetReadFile>>() { // from class: api.requests.com.neusoft.common.commonsysapiinverse.CommonSysApiInverseRequests.402
            @Override // java9.util.function.Function
            public CompletableFuture<ResGetReadFile> apply(ResGetReadFile resGetReadFile) {
                return CommonUtil.delayAsync(resGetReadFile, duration, CommonSysApiInverseRequests.this.contextUtil.getDefaultExecutor());
            }
        });
    }

    @Override // api.requests.com.neusoft.common.commonsysapiinverse.ICommonSysApiInverseRequests
    public CompletableFuture<ResGetReadFile> requestGetReadFile() {
        return requestGetReadFile(new ReqGetReadFile());
    }

    @Override // api.requests.com.neusoft.common.commonsysapiinverse.ICommonSysApiInverseRequests
    public CompletableFuture<ResGetReadFile> requestGetReadFile(Duration duration) {
        return requestGetReadFile(new ReqGetReadFile(), duration);
    }

    @Override // api.requests.com.neusoft.common.commonsysapiinverse.ICommonSysApiInverseRequests
    public CompletableFuture<Void> fireGetReadFile(ReqGetReadFile reqGetReadFile) {
        reqGetReadFile.setResponse(null);
        String str = this.component;
        if (str != null) {
            reqGetReadFile.setComponent(str);
        }
        return Communication.request(this.contextUtil.createFromClass(reqGetReadFile)).thenCompose(new Function<Object, CompletableFuture<Void>>() { // from class: api.requests.com.neusoft.common.commonsysapiinverse.CommonSysApiInverseRequests.403
            @Override // java9.util.function.Function
            public CompletableFuture<Void> apply(Object obj) {
                return CommonSysApiInverseRequests.completedVoidFuture;
            }
        });
    }

    @Override // api.requests.com.neusoft.common.commonsysapiinverse.ICommonSysApiInverseRequests
    public CompletableFuture<Void> fireGetReadFile(ReqGetReadFile reqGetReadFile, final Duration duration) {
        return fireGetReadFile(reqGetReadFile).thenCompose((Function<? super Void, ? extends CompletionStage<U>>) new Function<Void, CompletableFuture<Void>>() { // from class: api.requests.com.neusoft.common.commonsysapiinverse.CommonSysApiInverseRequests.404
            @Override // java9.util.function.Function
            public CompletableFuture<Void> apply(Void r2) {
                return CommonUtil.asyncSleep(duration, CommonSysApiInverseRequests.this.contextUtil.getDefaultExecutor());
            }
        });
    }

    @Override // api.requests.com.neusoft.common.commonsysapiinverse.ICommonSysApiInverseRequests
    public CompletableFuture<Void> fireGetReadFile() {
        return fireGetReadFile(new ReqGetReadFile());
    }

    @Override // api.requests.com.neusoft.common.commonsysapiinverse.ICommonSysApiInverseRequests
    public CompletableFuture<Void> fireGetReadFile(final Duration duration) {
        return fireGetReadFile(new ReqGetReadFile()).thenCompose((Function<? super Void, ? extends CompletionStage<U>>) new Function<Void, CompletableFuture<Void>>() { // from class: api.requests.com.neusoft.common.commonsysapiinverse.CommonSysApiInverseRequests.405
            @Override // java9.util.function.Function
            public CompletableFuture<Void> apply(Void r2) {
                return CommonUtil.asyncSleep(duration, CommonSysApiInverseRequests.this.contextUtil.getDefaultExecutor());
            }
        });
    }

    @Override // api.requests.com.neusoft.common.commonsysapiinverse.ICommonSysApiInverseRequests
    public CompletableFuture<ResGetWriteFile> requestGetWriteFile(ReqGetWriteFile reqGetWriteFile) {
        if (reqGetWriteFile.getResponse() == null) {
            reqGetWriteFile.setResponse(new ResGetWriteFile());
        }
        String str = this.component;
        if (str != null) {
            reqGetWriteFile.setComponent(str);
            reqGetWriteFile.getResponse().setComponent(this.component);
        }
        return Communication.request(this.contextUtil.createFromClass(reqGetWriteFile));
    }

    @Override // api.requests.com.neusoft.common.commonsysapiinverse.ICommonSysApiInverseRequests
    public CompletableFuture<ResGetWriteFile> requestGetWriteFile(ReqGetWriteFile reqGetWriteFile, final Duration duration) {
        return requestGetWriteFile(reqGetWriteFile).thenCompose((Function<? super ResGetWriteFile, ? extends CompletionStage<U>>) new Function<ResGetWriteFile, CompletableFuture<ResGetWriteFile>>() { // from class: api.requests.com.neusoft.common.commonsysapiinverse.CommonSysApiInverseRequests.406
            @Override // java9.util.function.Function
            public CompletableFuture<ResGetWriteFile> apply(ResGetWriteFile resGetWriteFile) {
                return CommonUtil.delayAsync(resGetWriteFile, duration, CommonSysApiInverseRequests.this.contextUtil.getDefaultExecutor());
            }
        });
    }

    @Override // api.requests.com.neusoft.common.commonsysapiinverse.ICommonSysApiInverseRequests
    public CompletableFuture<ResGetWriteFile> requestGetWriteFile() {
        return requestGetWriteFile(new ReqGetWriteFile());
    }

    @Override // api.requests.com.neusoft.common.commonsysapiinverse.ICommonSysApiInverseRequests
    public CompletableFuture<ResGetWriteFile> requestGetWriteFile(Duration duration) {
        return requestGetWriteFile(new ReqGetWriteFile(), duration);
    }

    @Override // api.requests.com.neusoft.common.commonsysapiinverse.ICommonSysApiInverseRequests
    public CompletableFuture<Void> fireGetWriteFile(ReqGetWriteFile reqGetWriteFile) {
        reqGetWriteFile.setResponse(null);
        String str = this.component;
        if (str != null) {
            reqGetWriteFile.setComponent(str);
        }
        return Communication.request(this.contextUtil.createFromClass(reqGetWriteFile)).thenCompose(new Function<Object, CompletableFuture<Void>>() { // from class: api.requests.com.neusoft.common.commonsysapiinverse.CommonSysApiInverseRequests.407
            @Override // java9.util.function.Function
            public CompletableFuture<Void> apply(Object obj) {
                return CommonSysApiInverseRequests.completedVoidFuture;
            }
        });
    }

    @Override // api.requests.com.neusoft.common.commonsysapiinverse.ICommonSysApiInverseRequests
    public CompletableFuture<Void> fireGetWriteFile(ReqGetWriteFile reqGetWriteFile, final Duration duration) {
        return fireGetWriteFile(reqGetWriteFile).thenCompose((Function<? super Void, ? extends CompletionStage<U>>) new Function<Void, CompletableFuture<Void>>() { // from class: api.requests.com.neusoft.common.commonsysapiinverse.CommonSysApiInverseRequests.408
            @Override // java9.util.function.Function
            public CompletableFuture<Void> apply(Void r2) {
                return CommonUtil.asyncSleep(duration, CommonSysApiInverseRequests.this.contextUtil.getDefaultExecutor());
            }
        });
    }

    @Override // api.requests.com.neusoft.common.commonsysapiinverse.ICommonSysApiInverseRequests
    public CompletableFuture<Void> fireGetWriteFile() {
        return fireGetWriteFile(new ReqGetWriteFile());
    }

    @Override // api.requests.com.neusoft.common.commonsysapiinverse.ICommonSysApiInverseRequests
    public CompletableFuture<Void> fireGetWriteFile(final Duration duration) {
        return fireGetWriteFile(new ReqGetWriteFile()).thenCompose((Function<? super Void, ? extends CompletionStage<U>>) new Function<Void, CompletableFuture<Void>>() { // from class: api.requests.com.neusoft.common.commonsysapiinverse.CommonSysApiInverseRequests.409
            @Override // java9.util.function.Function
            public CompletableFuture<Void> apply(Void r2) {
                return CommonUtil.asyncSleep(duration, CommonSysApiInverseRequests.this.contextUtil.getDefaultExecutor());
            }
        });
    }

    @Override // api.requests.com.neusoft.common.commonsysapiinverse.ICommonSysApiInverseRequests
    public CompletableFuture<ResGetDeleteFile> requestGetDeleteFile(ReqGetDeleteFile reqGetDeleteFile) {
        if (reqGetDeleteFile.getResponse() == null) {
            reqGetDeleteFile.setResponse(new ResGetDeleteFile());
        }
        String str = this.component;
        if (str != null) {
            reqGetDeleteFile.setComponent(str);
            reqGetDeleteFile.getResponse().setComponent(this.component);
        }
        return Communication.request(this.contextUtil.createFromClass(reqGetDeleteFile));
    }

    @Override // api.requests.com.neusoft.common.commonsysapiinverse.ICommonSysApiInverseRequests
    public CompletableFuture<ResGetDeleteFile> requestGetDeleteFile(ReqGetDeleteFile reqGetDeleteFile, final Duration duration) {
        return requestGetDeleteFile(reqGetDeleteFile).thenCompose((Function<? super ResGetDeleteFile, ? extends CompletionStage<U>>) new Function<ResGetDeleteFile, CompletableFuture<ResGetDeleteFile>>() { // from class: api.requests.com.neusoft.common.commonsysapiinverse.CommonSysApiInverseRequests.410
            @Override // java9.util.function.Function
            public CompletableFuture<ResGetDeleteFile> apply(ResGetDeleteFile resGetDeleteFile) {
                return CommonUtil.delayAsync(resGetDeleteFile, duration, CommonSysApiInverseRequests.this.contextUtil.getDefaultExecutor());
            }
        });
    }

    @Override // api.requests.com.neusoft.common.commonsysapiinverse.ICommonSysApiInverseRequests
    public CompletableFuture<ResGetDeleteFile> requestGetDeleteFile() {
        return requestGetDeleteFile(new ReqGetDeleteFile());
    }

    @Override // api.requests.com.neusoft.common.commonsysapiinverse.ICommonSysApiInverseRequests
    public CompletableFuture<ResGetDeleteFile> requestGetDeleteFile(Duration duration) {
        return requestGetDeleteFile(new ReqGetDeleteFile(), duration);
    }

    @Override // api.requests.com.neusoft.common.commonsysapiinverse.ICommonSysApiInverseRequests
    public CompletableFuture<Void> fireGetDeleteFile(ReqGetDeleteFile reqGetDeleteFile) {
        reqGetDeleteFile.setResponse(null);
        String str = this.component;
        if (str != null) {
            reqGetDeleteFile.setComponent(str);
        }
        return Communication.request(this.contextUtil.createFromClass(reqGetDeleteFile)).thenCompose(new Function<Object, CompletableFuture<Void>>() { // from class: api.requests.com.neusoft.common.commonsysapiinverse.CommonSysApiInverseRequests.411
            @Override // java9.util.function.Function
            public CompletableFuture<Void> apply(Object obj) {
                return CommonSysApiInverseRequests.completedVoidFuture;
            }
        });
    }

    @Override // api.requests.com.neusoft.common.commonsysapiinverse.ICommonSysApiInverseRequests
    public CompletableFuture<Void> fireGetDeleteFile(ReqGetDeleteFile reqGetDeleteFile, final Duration duration) {
        return fireGetDeleteFile(reqGetDeleteFile).thenCompose((Function<? super Void, ? extends CompletionStage<U>>) new Function<Void, CompletableFuture<Void>>() { // from class: api.requests.com.neusoft.common.commonsysapiinverse.CommonSysApiInverseRequests.412
            @Override // java9.util.function.Function
            public CompletableFuture<Void> apply(Void r2) {
                return CommonUtil.asyncSleep(duration, CommonSysApiInverseRequests.this.contextUtil.getDefaultExecutor());
            }
        });
    }

    @Override // api.requests.com.neusoft.common.commonsysapiinverse.ICommonSysApiInverseRequests
    public CompletableFuture<Void> fireGetDeleteFile() {
        return fireGetDeleteFile(new ReqGetDeleteFile());
    }

    @Override // api.requests.com.neusoft.common.commonsysapiinverse.ICommonSysApiInverseRequests
    public CompletableFuture<Void> fireGetDeleteFile(final Duration duration) {
        return fireGetDeleteFile(new ReqGetDeleteFile()).thenCompose((Function<? super Void, ? extends CompletionStage<U>>) new Function<Void, CompletableFuture<Void>>() { // from class: api.requests.com.neusoft.common.commonsysapiinverse.CommonSysApiInverseRequests.413
            @Override // java9.util.function.Function
            public CompletableFuture<Void> apply(Void r2) {
                return CommonUtil.asyncSleep(duration, CommonSysApiInverseRequests.this.contextUtil.getDefaultExecutor());
            }
        });
    }

    @Override // api.requests.com.neusoft.common.commonsysapiinverse.ICommonSysApiInverseRequests
    public CompletableFuture<ResGetNaviDayNightStatusChange> requestGetNaviDayNightStatusChange(ReqGetNaviDayNightStatusChange reqGetNaviDayNightStatusChange) {
        if (reqGetNaviDayNightStatusChange.getResponse() == null) {
            reqGetNaviDayNightStatusChange.setResponse(new ResGetNaviDayNightStatusChange());
        }
        String str = this.component;
        if (str != null) {
            reqGetNaviDayNightStatusChange.setComponent(str);
            reqGetNaviDayNightStatusChange.getResponse().setComponent(this.component);
        }
        return Communication.request(this.contextUtil.createFromClass(reqGetNaviDayNightStatusChange));
    }

    @Override // api.requests.com.neusoft.common.commonsysapiinverse.ICommonSysApiInverseRequests
    public CompletableFuture<ResGetNaviDayNightStatusChange> requestGetNaviDayNightStatusChange(ReqGetNaviDayNightStatusChange reqGetNaviDayNightStatusChange, final Duration duration) {
        return requestGetNaviDayNightStatusChange(reqGetNaviDayNightStatusChange).thenCompose((Function<? super ResGetNaviDayNightStatusChange, ? extends CompletionStage<U>>) new Function<ResGetNaviDayNightStatusChange, CompletableFuture<ResGetNaviDayNightStatusChange>>() { // from class: api.requests.com.neusoft.common.commonsysapiinverse.CommonSysApiInverseRequests.414
            @Override // java9.util.function.Function
            public CompletableFuture<ResGetNaviDayNightStatusChange> apply(ResGetNaviDayNightStatusChange resGetNaviDayNightStatusChange) {
                return CommonUtil.delayAsync(resGetNaviDayNightStatusChange, duration, CommonSysApiInverseRequests.this.contextUtil.getDefaultExecutor());
            }
        });
    }

    @Override // api.requests.com.neusoft.common.commonsysapiinverse.ICommonSysApiInverseRequests
    public CompletableFuture<ResGetNaviDayNightStatusChange> requestGetNaviDayNightStatusChange() {
        return requestGetNaviDayNightStatusChange(new ReqGetNaviDayNightStatusChange());
    }

    @Override // api.requests.com.neusoft.common.commonsysapiinverse.ICommonSysApiInverseRequests
    public CompletableFuture<ResGetNaviDayNightStatusChange> requestGetNaviDayNightStatusChange(Duration duration) {
        return requestGetNaviDayNightStatusChange(new ReqGetNaviDayNightStatusChange(), duration);
    }

    @Override // api.requests.com.neusoft.common.commonsysapiinverse.ICommonSysApiInverseRequests
    public CompletableFuture<Void> fireGetNaviDayNightStatusChange(ReqGetNaviDayNightStatusChange reqGetNaviDayNightStatusChange) {
        reqGetNaviDayNightStatusChange.setResponse(null);
        String str = this.component;
        if (str != null) {
            reqGetNaviDayNightStatusChange.setComponent(str);
        }
        return Communication.request(this.contextUtil.createFromClass(reqGetNaviDayNightStatusChange)).thenCompose(new Function<Object, CompletableFuture<Void>>() { // from class: api.requests.com.neusoft.common.commonsysapiinverse.CommonSysApiInverseRequests.415
            @Override // java9.util.function.Function
            public CompletableFuture<Void> apply(Object obj) {
                return CommonSysApiInverseRequests.completedVoidFuture;
            }
        });
    }

    @Override // api.requests.com.neusoft.common.commonsysapiinverse.ICommonSysApiInverseRequests
    public CompletableFuture<Void> fireGetNaviDayNightStatusChange(ReqGetNaviDayNightStatusChange reqGetNaviDayNightStatusChange, final Duration duration) {
        return fireGetNaviDayNightStatusChange(reqGetNaviDayNightStatusChange).thenCompose((Function<? super Void, ? extends CompletionStage<U>>) new Function<Void, CompletableFuture<Void>>() { // from class: api.requests.com.neusoft.common.commonsysapiinverse.CommonSysApiInverseRequests.416
            @Override // java9.util.function.Function
            public CompletableFuture<Void> apply(Void r2) {
                return CommonUtil.asyncSleep(duration, CommonSysApiInverseRequests.this.contextUtil.getDefaultExecutor());
            }
        });
    }

    @Override // api.requests.com.neusoft.common.commonsysapiinverse.ICommonSysApiInverseRequests
    public CompletableFuture<Void> fireGetNaviDayNightStatusChange() {
        return fireGetNaviDayNightStatusChange(new ReqGetNaviDayNightStatusChange());
    }

    @Override // api.requests.com.neusoft.common.commonsysapiinverse.ICommonSysApiInverseRequests
    public CompletableFuture<Void> fireGetNaviDayNightStatusChange(final Duration duration) {
        return fireGetNaviDayNightStatusChange(new ReqGetNaviDayNightStatusChange()).thenCompose((Function<? super Void, ? extends CompletionStage<U>>) new Function<Void, CompletableFuture<Void>>() { // from class: api.requests.com.neusoft.common.commonsysapiinverse.CommonSysApiInverseRequests.417
            @Override // java9.util.function.Function
            public CompletableFuture<Void> apply(Void r2) {
                return CommonUtil.asyncSleep(duration, CommonSysApiInverseRequests.this.contextUtil.getDefaultExecutor());
            }
        });
    }

    @Override // api.requests.com.neusoft.common.commonsysapiinverse.ICommonSysApiInverseRequests
    public CompletableFuture<ResGetNaviSoftwareVersionInfo> requestGetNaviSoftwareVersionInfo(ReqGetNaviSoftwareVersionInfo reqGetNaviSoftwareVersionInfo) {
        if (reqGetNaviSoftwareVersionInfo.getResponse() == null) {
            reqGetNaviSoftwareVersionInfo.setResponse(new ResGetNaviSoftwareVersionInfo());
        }
        String str = this.component;
        if (str != null) {
            reqGetNaviSoftwareVersionInfo.setComponent(str);
            reqGetNaviSoftwareVersionInfo.getResponse().setComponent(this.component);
        }
        return Communication.request(this.contextUtil.createFromClass(reqGetNaviSoftwareVersionInfo));
    }

    @Override // api.requests.com.neusoft.common.commonsysapiinverse.ICommonSysApiInverseRequests
    public CompletableFuture<ResGetNaviSoftwareVersionInfo> requestGetNaviSoftwareVersionInfo(ReqGetNaviSoftwareVersionInfo reqGetNaviSoftwareVersionInfo, final Duration duration) {
        return requestGetNaviSoftwareVersionInfo(reqGetNaviSoftwareVersionInfo).thenCompose((Function<? super ResGetNaviSoftwareVersionInfo, ? extends CompletionStage<U>>) new Function<ResGetNaviSoftwareVersionInfo, CompletableFuture<ResGetNaviSoftwareVersionInfo>>() { // from class: api.requests.com.neusoft.common.commonsysapiinverse.CommonSysApiInverseRequests.418
            @Override // java9.util.function.Function
            public CompletableFuture<ResGetNaviSoftwareVersionInfo> apply(ResGetNaviSoftwareVersionInfo resGetNaviSoftwareVersionInfo) {
                return CommonUtil.delayAsync(resGetNaviSoftwareVersionInfo, duration, CommonSysApiInverseRequests.this.contextUtil.getDefaultExecutor());
            }
        });
    }

    @Override // api.requests.com.neusoft.common.commonsysapiinverse.ICommonSysApiInverseRequests
    public CompletableFuture<ResGetNaviSoftwareVersionInfo> requestGetNaviSoftwareVersionInfo() {
        return requestGetNaviSoftwareVersionInfo(new ReqGetNaviSoftwareVersionInfo());
    }

    @Override // api.requests.com.neusoft.common.commonsysapiinverse.ICommonSysApiInverseRequests
    public CompletableFuture<ResGetNaviSoftwareVersionInfo> requestGetNaviSoftwareVersionInfo(Duration duration) {
        return requestGetNaviSoftwareVersionInfo(new ReqGetNaviSoftwareVersionInfo(), duration);
    }

    @Override // api.requests.com.neusoft.common.commonsysapiinverse.ICommonSysApiInverseRequests
    public CompletableFuture<Void> fireGetNaviSoftwareVersionInfo(ReqGetNaviSoftwareVersionInfo reqGetNaviSoftwareVersionInfo) {
        reqGetNaviSoftwareVersionInfo.setResponse(null);
        String str = this.component;
        if (str != null) {
            reqGetNaviSoftwareVersionInfo.setComponent(str);
        }
        return Communication.request(this.contextUtil.createFromClass(reqGetNaviSoftwareVersionInfo)).thenCompose(new Function<Object, CompletableFuture<Void>>() { // from class: api.requests.com.neusoft.common.commonsysapiinverse.CommonSysApiInverseRequests.419
            @Override // java9.util.function.Function
            public CompletableFuture<Void> apply(Object obj) {
                return CommonSysApiInverseRequests.completedVoidFuture;
            }
        });
    }

    @Override // api.requests.com.neusoft.common.commonsysapiinverse.ICommonSysApiInverseRequests
    public CompletableFuture<Void> fireGetNaviSoftwareVersionInfo(ReqGetNaviSoftwareVersionInfo reqGetNaviSoftwareVersionInfo, final Duration duration) {
        return fireGetNaviSoftwareVersionInfo(reqGetNaviSoftwareVersionInfo).thenCompose((Function<? super Void, ? extends CompletionStage<U>>) new Function<Void, CompletableFuture<Void>>() { // from class: api.requests.com.neusoft.common.commonsysapiinverse.CommonSysApiInverseRequests.420
            @Override // java9.util.function.Function
            public CompletableFuture<Void> apply(Void r2) {
                return CommonUtil.asyncSleep(duration, CommonSysApiInverseRequests.this.contextUtil.getDefaultExecutor());
            }
        });
    }

    @Override // api.requests.com.neusoft.common.commonsysapiinverse.ICommonSysApiInverseRequests
    public CompletableFuture<Void> fireGetNaviSoftwareVersionInfo() {
        return fireGetNaviSoftwareVersionInfo(new ReqGetNaviSoftwareVersionInfo());
    }

    @Override // api.requests.com.neusoft.common.commonsysapiinverse.ICommonSysApiInverseRequests
    public CompletableFuture<Void> fireGetNaviSoftwareVersionInfo(final Duration duration) {
        return fireGetNaviSoftwareVersionInfo(new ReqGetNaviSoftwareVersionInfo()).thenCompose((Function<? super Void, ? extends CompletionStage<U>>) new Function<Void, CompletableFuture<Void>>() { // from class: api.requests.com.neusoft.common.commonsysapiinverse.CommonSysApiInverseRequests.421
            @Override // java9.util.function.Function
            public CompletableFuture<Void> apply(Void r2) {
                return CommonUtil.asyncSleep(duration, CommonSysApiInverseRequests.this.contextUtil.getDefaultExecutor());
            }
        });
    }

    @Override // api.requests.com.neusoft.common.commonsysapiinverse.ICommonSysApiInverseRequests
    public CompletableFuture<ResGetOnKeyDown> requestGetOnKeyDown(ReqGetOnKeyDown reqGetOnKeyDown) {
        if (reqGetOnKeyDown.getResponse() == null) {
            reqGetOnKeyDown.setResponse(new ResGetOnKeyDown());
        }
        String str = this.component;
        if (str != null) {
            reqGetOnKeyDown.setComponent(str);
            reqGetOnKeyDown.getResponse().setComponent(this.component);
        }
        return Communication.request(this.contextUtil.createFromClass(reqGetOnKeyDown));
    }

    @Override // api.requests.com.neusoft.common.commonsysapiinverse.ICommonSysApiInverseRequests
    public CompletableFuture<ResGetOnKeyDown> requestGetOnKeyDown(ReqGetOnKeyDown reqGetOnKeyDown, final Duration duration) {
        return requestGetOnKeyDown(reqGetOnKeyDown).thenCompose((Function<? super ResGetOnKeyDown, ? extends CompletionStage<U>>) new Function<ResGetOnKeyDown, CompletableFuture<ResGetOnKeyDown>>() { // from class: api.requests.com.neusoft.common.commonsysapiinverse.CommonSysApiInverseRequests.422
            @Override // java9.util.function.Function
            public CompletableFuture<ResGetOnKeyDown> apply(ResGetOnKeyDown resGetOnKeyDown) {
                return CommonUtil.delayAsync(resGetOnKeyDown, duration, CommonSysApiInverseRequests.this.contextUtil.getDefaultExecutor());
            }
        });
    }

    @Override // api.requests.com.neusoft.common.commonsysapiinverse.ICommonSysApiInverseRequests
    public CompletableFuture<ResGetOnKeyDown> requestGetOnKeyDown() {
        return requestGetOnKeyDown(new ReqGetOnKeyDown());
    }

    @Override // api.requests.com.neusoft.common.commonsysapiinverse.ICommonSysApiInverseRequests
    public CompletableFuture<ResGetOnKeyDown> requestGetOnKeyDown(Duration duration) {
        return requestGetOnKeyDown(new ReqGetOnKeyDown(), duration);
    }

    @Override // api.requests.com.neusoft.common.commonsysapiinverse.ICommonSysApiInverseRequests
    public CompletableFuture<Void> fireGetOnKeyDown(ReqGetOnKeyDown reqGetOnKeyDown) {
        reqGetOnKeyDown.setResponse(null);
        String str = this.component;
        if (str != null) {
            reqGetOnKeyDown.setComponent(str);
        }
        return Communication.request(this.contextUtil.createFromClass(reqGetOnKeyDown)).thenCompose(new Function<Object, CompletableFuture<Void>>() { // from class: api.requests.com.neusoft.common.commonsysapiinverse.CommonSysApiInverseRequests.423
            @Override // java9.util.function.Function
            public CompletableFuture<Void> apply(Object obj) {
                return CommonSysApiInverseRequests.completedVoidFuture;
            }
        });
    }

    @Override // api.requests.com.neusoft.common.commonsysapiinverse.ICommonSysApiInverseRequests
    public CompletableFuture<Void> fireGetOnKeyDown(ReqGetOnKeyDown reqGetOnKeyDown, final Duration duration) {
        return fireGetOnKeyDown(reqGetOnKeyDown).thenCompose((Function<? super Void, ? extends CompletionStage<U>>) new Function<Void, CompletableFuture<Void>>() { // from class: api.requests.com.neusoft.common.commonsysapiinverse.CommonSysApiInverseRequests.424
            @Override // java9.util.function.Function
            public CompletableFuture<Void> apply(Void r2) {
                return CommonUtil.asyncSleep(duration, CommonSysApiInverseRequests.this.contextUtil.getDefaultExecutor());
            }
        });
    }

    @Override // api.requests.com.neusoft.common.commonsysapiinverse.ICommonSysApiInverseRequests
    public CompletableFuture<Void> fireGetOnKeyDown() {
        return fireGetOnKeyDown(new ReqGetOnKeyDown());
    }

    @Override // api.requests.com.neusoft.common.commonsysapiinverse.ICommonSysApiInverseRequests
    public CompletableFuture<Void> fireGetOnKeyDown(final Duration duration) {
        return fireGetOnKeyDown(new ReqGetOnKeyDown()).thenCompose((Function<? super Void, ? extends CompletionStage<U>>) new Function<Void, CompletableFuture<Void>>() { // from class: api.requests.com.neusoft.common.commonsysapiinverse.CommonSysApiInverseRequests.425
            @Override // java9.util.function.Function
            public CompletableFuture<Void> apply(Void r2) {
                return CommonUtil.asyncSleep(duration, CommonSysApiInverseRequests.this.contextUtil.getDefaultExecutor());
            }
        });
    }

    @Override // api.requests.com.neusoft.common.commonsysapiinverse.ICommonSysApiInverseRequests
    public CompletableFuture<ResGetSwitchArgumentedReality> requestGetSwitchArgumentedReality(ReqGetSwitchArgumentedReality reqGetSwitchArgumentedReality) {
        if (reqGetSwitchArgumentedReality.getResponse() == null) {
            reqGetSwitchArgumentedReality.setResponse(new ResGetSwitchArgumentedReality());
        }
        String str = this.component;
        if (str != null) {
            reqGetSwitchArgumentedReality.setComponent(str);
            reqGetSwitchArgumentedReality.getResponse().setComponent(this.component);
        }
        return Communication.request(this.contextUtil.createFromClass(reqGetSwitchArgumentedReality));
    }

    @Override // api.requests.com.neusoft.common.commonsysapiinverse.ICommonSysApiInverseRequests
    public CompletableFuture<ResGetSwitchArgumentedReality> requestGetSwitchArgumentedReality(ReqGetSwitchArgumentedReality reqGetSwitchArgumentedReality, final Duration duration) {
        return requestGetSwitchArgumentedReality(reqGetSwitchArgumentedReality).thenCompose((Function<? super ResGetSwitchArgumentedReality, ? extends CompletionStage<U>>) new Function<ResGetSwitchArgumentedReality, CompletableFuture<ResGetSwitchArgumentedReality>>() { // from class: api.requests.com.neusoft.common.commonsysapiinverse.CommonSysApiInverseRequests.426
            @Override // java9.util.function.Function
            public CompletableFuture<ResGetSwitchArgumentedReality> apply(ResGetSwitchArgumentedReality resGetSwitchArgumentedReality) {
                return CommonUtil.delayAsync(resGetSwitchArgumentedReality, duration, CommonSysApiInverseRequests.this.contextUtil.getDefaultExecutor());
            }
        });
    }

    @Override // api.requests.com.neusoft.common.commonsysapiinverse.ICommonSysApiInverseRequests
    public CompletableFuture<ResGetSwitchArgumentedReality> requestGetSwitchArgumentedReality() {
        return requestGetSwitchArgumentedReality(new ReqGetSwitchArgumentedReality());
    }

    @Override // api.requests.com.neusoft.common.commonsysapiinverse.ICommonSysApiInverseRequests
    public CompletableFuture<ResGetSwitchArgumentedReality> requestGetSwitchArgumentedReality(Duration duration) {
        return requestGetSwitchArgumentedReality(new ReqGetSwitchArgumentedReality(), duration);
    }

    @Override // api.requests.com.neusoft.common.commonsysapiinverse.ICommonSysApiInverseRequests
    public CompletableFuture<Void> fireGetSwitchArgumentedReality(ReqGetSwitchArgumentedReality reqGetSwitchArgumentedReality) {
        reqGetSwitchArgumentedReality.setResponse(null);
        String str = this.component;
        if (str != null) {
            reqGetSwitchArgumentedReality.setComponent(str);
        }
        return Communication.request(this.contextUtil.createFromClass(reqGetSwitchArgumentedReality)).thenCompose(new Function<Object, CompletableFuture<Void>>() { // from class: api.requests.com.neusoft.common.commonsysapiinverse.CommonSysApiInverseRequests.427
            @Override // java9.util.function.Function
            public CompletableFuture<Void> apply(Object obj) {
                return CommonSysApiInverseRequests.completedVoidFuture;
            }
        });
    }

    @Override // api.requests.com.neusoft.common.commonsysapiinverse.ICommonSysApiInverseRequests
    public CompletableFuture<Void> fireGetSwitchArgumentedReality(ReqGetSwitchArgumentedReality reqGetSwitchArgumentedReality, final Duration duration) {
        return fireGetSwitchArgumentedReality(reqGetSwitchArgumentedReality).thenCompose((Function<? super Void, ? extends CompletionStage<U>>) new Function<Void, CompletableFuture<Void>>() { // from class: api.requests.com.neusoft.common.commonsysapiinverse.CommonSysApiInverseRequests.428
            @Override // java9.util.function.Function
            public CompletableFuture<Void> apply(Void r2) {
                return CommonUtil.asyncSleep(duration, CommonSysApiInverseRequests.this.contextUtil.getDefaultExecutor());
            }
        });
    }

    @Override // api.requests.com.neusoft.common.commonsysapiinverse.ICommonSysApiInverseRequests
    public CompletableFuture<Void> fireGetSwitchArgumentedReality() {
        return fireGetSwitchArgumentedReality(new ReqGetSwitchArgumentedReality());
    }

    @Override // api.requests.com.neusoft.common.commonsysapiinverse.ICommonSysApiInverseRequests
    public CompletableFuture<Void> fireGetSwitchArgumentedReality(final Duration duration) {
        return fireGetSwitchArgumentedReality(new ReqGetSwitchArgumentedReality()).thenCompose((Function<? super Void, ? extends CompletionStage<U>>) new Function<Void, CompletableFuture<Void>>() { // from class: api.requests.com.neusoft.common.commonsysapiinverse.CommonSysApiInverseRequests.429
            @Override // java9.util.function.Function
            public CompletableFuture<Void> apply(Void r2) {
                return CommonUtil.asyncSleep(duration, CommonSysApiInverseRequests.this.contextUtil.getDefaultExecutor());
            }
        });
    }

    @Override // api.requests.com.neusoft.common.commonsysapiinverse.ICommonSysApiInverseRequests
    public CompletableFuture<ResGetSwitchTraceLog> requestGetSwitchTraceLog(ReqGetSwitchTraceLog reqGetSwitchTraceLog) {
        if (reqGetSwitchTraceLog.getResponse() == null) {
            reqGetSwitchTraceLog.setResponse(new ResGetSwitchTraceLog());
        }
        String str = this.component;
        if (str != null) {
            reqGetSwitchTraceLog.setComponent(str);
            reqGetSwitchTraceLog.getResponse().setComponent(this.component);
        }
        return Communication.request(this.contextUtil.createFromClass(reqGetSwitchTraceLog));
    }

    @Override // api.requests.com.neusoft.common.commonsysapiinverse.ICommonSysApiInverseRequests
    public CompletableFuture<ResGetSwitchTraceLog> requestGetSwitchTraceLog(ReqGetSwitchTraceLog reqGetSwitchTraceLog, final Duration duration) {
        return requestGetSwitchTraceLog(reqGetSwitchTraceLog).thenCompose((Function<? super ResGetSwitchTraceLog, ? extends CompletionStage<U>>) new Function<ResGetSwitchTraceLog, CompletableFuture<ResGetSwitchTraceLog>>() { // from class: api.requests.com.neusoft.common.commonsysapiinverse.CommonSysApiInverseRequests.430
            @Override // java9.util.function.Function
            public CompletableFuture<ResGetSwitchTraceLog> apply(ResGetSwitchTraceLog resGetSwitchTraceLog) {
                return CommonUtil.delayAsync(resGetSwitchTraceLog, duration, CommonSysApiInverseRequests.this.contextUtil.getDefaultExecutor());
            }
        });
    }

    @Override // api.requests.com.neusoft.common.commonsysapiinverse.ICommonSysApiInverseRequests
    public CompletableFuture<ResGetSwitchTraceLog> requestGetSwitchTraceLog() {
        return requestGetSwitchTraceLog(new ReqGetSwitchTraceLog());
    }

    @Override // api.requests.com.neusoft.common.commonsysapiinverse.ICommonSysApiInverseRequests
    public CompletableFuture<ResGetSwitchTraceLog> requestGetSwitchTraceLog(Duration duration) {
        return requestGetSwitchTraceLog(new ReqGetSwitchTraceLog(), duration);
    }

    @Override // api.requests.com.neusoft.common.commonsysapiinverse.ICommonSysApiInverseRequests
    public CompletableFuture<Void> fireGetSwitchTraceLog(ReqGetSwitchTraceLog reqGetSwitchTraceLog) {
        reqGetSwitchTraceLog.setResponse(null);
        String str = this.component;
        if (str != null) {
            reqGetSwitchTraceLog.setComponent(str);
        }
        return Communication.request(this.contextUtil.createFromClass(reqGetSwitchTraceLog)).thenCompose(new Function<Object, CompletableFuture<Void>>() { // from class: api.requests.com.neusoft.common.commonsysapiinverse.CommonSysApiInverseRequests.431
            @Override // java9.util.function.Function
            public CompletableFuture<Void> apply(Object obj) {
                return CommonSysApiInverseRequests.completedVoidFuture;
            }
        });
    }

    @Override // api.requests.com.neusoft.common.commonsysapiinverse.ICommonSysApiInverseRequests
    public CompletableFuture<Void> fireGetSwitchTraceLog(ReqGetSwitchTraceLog reqGetSwitchTraceLog, final Duration duration) {
        return fireGetSwitchTraceLog(reqGetSwitchTraceLog).thenCompose((Function<? super Void, ? extends CompletionStage<U>>) new Function<Void, CompletableFuture<Void>>() { // from class: api.requests.com.neusoft.common.commonsysapiinverse.CommonSysApiInverseRequests.432
            @Override // java9.util.function.Function
            public CompletableFuture<Void> apply(Void r2) {
                return CommonUtil.asyncSleep(duration, CommonSysApiInverseRequests.this.contextUtil.getDefaultExecutor());
            }
        });
    }

    @Override // api.requests.com.neusoft.common.commonsysapiinverse.ICommonSysApiInverseRequests
    public CompletableFuture<Void> fireGetSwitchTraceLog() {
        return fireGetSwitchTraceLog(new ReqGetSwitchTraceLog());
    }

    @Override // api.requests.com.neusoft.common.commonsysapiinverse.ICommonSysApiInverseRequests
    public CompletableFuture<Void> fireGetSwitchTraceLog(final Duration duration) {
        return fireGetSwitchTraceLog(new ReqGetSwitchTraceLog()).thenCompose((Function<? super Void, ? extends CompletionStage<U>>) new Function<Void, CompletableFuture<Void>>() { // from class: api.requests.com.neusoft.common.commonsysapiinverse.CommonSysApiInverseRequests.433
            @Override // java9.util.function.Function
            public CompletableFuture<Void> apply(Void r2) {
                return CommonUtil.asyncSleep(duration, CommonSysApiInverseRequests.this.contextUtil.getDefaultExecutor());
            }
        });
    }

    @Override // api.requests.com.neusoft.common.commonsysapiinverse.ICommonSysApiInverseRequests
    public CompletableFuture<ResGetRequestEnduranceMileage> requestGetRequestEnduranceMileage(ReqGetRequestEnduranceMileage reqGetRequestEnduranceMileage) {
        if (reqGetRequestEnduranceMileage.getResponse() == null) {
            reqGetRequestEnduranceMileage.setResponse(new ResGetRequestEnduranceMileage());
        }
        String str = this.component;
        if (str != null) {
            reqGetRequestEnduranceMileage.setComponent(str);
            reqGetRequestEnduranceMileage.getResponse().setComponent(this.component);
        }
        return Communication.request(this.contextUtil.createFromClass(reqGetRequestEnduranceMileage));
    }

    @Override // api.requests.com.neusoft.common.commonsysapiinverse.ICommonSysApiInverseRequests
    public CompletableFuture<ResGetRequestEnduranceMileage> requestGetRequestEnduranceMileage(ReqGetRequestEnduranceMileage reqGetRequestEnduranceMileage, final Duration duration) {
        return requestGetRequestEnduranceMileage(reqGetRequestEnduranceMileage).thenCompose((Function<? super ResGetRequestEnduranceMileage, ? extends CompletionStage<U>>) new Function<ResGetRequestEnduranceMileage, CompletableFuture<ResGetRequestEnduranceMileage>>() { // from class: api.requests.com.neusoft.common.commonsysapiinverse.CommonSysApiInverseRequests.434
            @Override // java9.util.function.Function
            public CompletableFuture<ResGetRequestEnduranceMileage> apply(ResGetRequestEnduranceMileage resGetRequestEnduranceMileage) {
                return CommonUtil.delayAsync(resGetRequestEnduranceMileage, duration, CommonSysApiInverseRequests.this.contextUtil.getDefaultExecutor());
            }
        });
    }

    @Override // api.requests.com.neusoft.common.commonsysapiinverse.ICommonSysApiInverseRequests
    public CompletableFuture<ResGetRequestEnduranceMileage> requestGetRequestEnduranceMileage() {
        return requestGetRequestEnduranceMileage(new ReqGetRequestEnduranceMileage());
    }

    @Override // api.requests.com.neusoft.common.commonsysapiinverse.ICommonSysApiInverseRequests
    public CompletableFuture<ResGetRequestEnduranceMileage> requestGetRequestEnduranceMileage(Duration duration) {
        return requestGetRequestEnduranceMileage(new ReqGetRequestEnduranceMileage(), duration);
    }

    @Override // api.requests.com.neusoft.common.commonsysapiinverse.ICommonSysApiInverseRequests
    public CompletableFuture<Void> fireGetRequestEnduranceMileage(ReqGetRequestEnduranceMileage reqGetRequestEnduranceMileage) {
        reqGetRequestEnduranceMileage.setResponse(null);
        String str = this.component;
        if (str != null) {
            reqGetRequestEnduranceMileage.setComponent(str);
        }
        return Communication.request(this.contextUtil.createFromClass(reqGetRequestEnduranceMileage)).thenCompose(new Function<Object, CompletableFuture<Void>>() { // from class: api.requests.com.neusoft.common.commonsysapiinverse.CommonSysApiInverseRequests.435
            @Override // java9.util.function.Function
            public CompletableFuture<Void> apply(Object obj) {
                return CommonSysApiInverseRequests.completedVoidFuture;
            }
        });
    }

    @Override // api.requests.com.neusoft.common.commonsysapiinverse.ICommonSysApiInverseRequests
    public CompletableFuture<Void> fireGetRequestEnduranceMileage(ReqGetRequestEnduranceMileage reqGetRequestEnduranceMileage, final Duration duration) {
        return fireGetRequestEnduranceMileage(reqGetRequestEnduranceMileage).thenCompose((Function<? super Void, ? extends CompletionStage<U>>) new Function<Void, CompletableFuture<Void>>() { // from class: api.requests.com.neusoft.common.commonsysapiinverse.CommonSysApiInverseRequests.436
            @Override // java9.util.function.Function
            public CompletableFuture<Void> apply(Void r2) {
                return CommonUtil.asyncSleep(duration, CommonSysApiInverseRequests.this.contextUtil.getDefaultExecutor());
            }
        });
    }

    @Override // api.requests.com.neusoft.common.commonsysapiinverse.ICommonSysApiInverseRequests
    public CompletableFuture<Void> fireGetRequestEnduranceMileage() {
        return fireGetRequestEnduranceMileage(new ReqGetRequestEnduranceMileage());
    }

    @Override // api.requests.com.neusoft.common.commonsysapiinverse.ICommonSysApiInverseRequests
    public CompletableFuture<Void> fireGetRequestEnduranceMileage(final Duration duration) {
        return fireGetRequestEnduranceMileage(new ReqGetRequestEnduranceMileage()).thenCompose((Function<? super Void, ? extends CompletionStage<U>>) new Function<Void, CompletableFuture<Void>>() { // from class: api.requests.com.neusoft.common.commonsysapiinverse.CommonSysApiInverseRequests.437
            @Override // java9.util.function.Function
            public CompletableFuture<Void> apply(Void r2) {
                return CommonUtil.asyncSleep(duration, CommonSysApiInverseRequests.this.contextUtil.getDefaultExecutor());
            }
        });
    }

    @Override // api.requests.com.neusoft.common.commonsysapiinverse.ICommonSysApiInverseRequests
    public CompletableFuture<ResGetShowToastMessage> requestGetShowToastMessage(ReqGetShowToastMessage reqGetShowToastMessage) {
        if (reqGetShowToastMessage.getResponse() == null) {
            reqGetShowToastMessage.setResponse(new ResGetShowToastMessage());
        }
        String str = this.component;
        if (str != null) {
            reqGetShowToastMessage.setComponent(str);
            reqGetShowToastMessage.getResponse().setComponent(this.component);
        }
        return Communication.request(this.contextUtil.createFromClass(reqGetShowToastMessage));
    }

    @Override // api.requests.com.neusoft.common.commonsysapiinverse.ICommonSysApiInverseRequests
    public CompletableFuture<ResGetShowToastMessage> requestGetShowToastMessage(ReqGetShowToastMessage reqGetShowToastMessage, final Duration duration) {
        return requestGetShowToastMessage(reqGetShowToastMessage).thenCompose((Function<? super ResGetShowToastMessage, ? extends CompletionStage<U>>) new Function<ResGetShowToastMessage, CompletableFuture<ResGetShowToastMessage>>() { // from class: api.requests.com.neusoft.common.commonsysapiinverse.CommonSysApiInverseRequests.438
            @Override // java9.util.function.Function
            public CompletableFuture<ResGetShowToastMessage> apply(ResGetShowToastMessage resGetShowToastMessage) {
                return CommonUtil.delayAsync(resGetShowToastMessage, duration, CommonSysApiInverseRequests.this.contextUtil.getDefaultExecutor());
            }
        });
    }

    @Override // api.requests.com.neusoft.common.commonsysapiinverse.ICommonSysApiInverseRequests
    public CompletableFuture<ResGetShowToastMessage> requestGetShowToastMessage() {
        return requestGetShowToastMessage(new ReqGetShowToastMessage());
    }

    @Override // api.requests.com.neusoft.common.commonsysapiinverse.ICommonSysApiInverseRequests
    public CompletableFuture<ResGetShowToastMessage> requestGetShowToastMessage(Duration duration) {
        return requestGetShowToastMessage(new ReqGetShowToastMessage(), duration);
    }

    @Override // api.requests.com.neusoft.common.commonsysapiinverse.ICommonSysApiInverseRequests
    public CompletableFuture<Void> fireGetShowToastMessage(ReqGetShowToastMessage reqGetShowToastMessage) {
        reqGetShowToastMessage.setResponse(null);
        String str = this.component;
        if (str != null) {
            reqGetShowToastMessage.setComponent(str);
        }
        return Communication.request(this.contextUtil.createFromClass(reqGetShowToastMessage)).thenCompose(new Function<Object, CompletableFuture<Void>>() { // from class: api.requests.com.neusoft.common.commonsysapiinverse.CommonSysApiInverseRequests.439
            @Override // java9.util.function.Function
            public CompletableFuture<Void> apply(Object obj) {
                return CommonSysApiInverseRequests.completedVoidFuture;
            }
        });
    }

    @Override // api.requests.com.neusoft.common.commonsysapiinverse.ICommonSysApiInverseRequests
    public CompletableFuture<Void> fireGetShowToastMessage(ReqGetShowToastMessage reqGetShowToastMessage, final Duration duration) {
        return fireGetShowToastMessage(reqGetShowToastMessage).thenCompose((Function<? super Void, ? extends CompletionStage<U>>) new Function<Void, CompletableFuture<Void>>() { // from class: api.requests.com.neusoft.common.commonsysapiinverse.CommonSysApiInverseRequests.440
            @Override // java9.util.function.Function
            public CompletableFuture<Void> apply(Void r2) {
                return CommonUtil.asyncSleep(duration, CommonSysApiInverseRequests.this.contextUtil.getDefaultExecutor());
            }
        });
    }

    @Override // api.requests.com.neusoft.common.commonsysapiinverse.ICommonSysApiInverseRequests
    public CompletableFuture<Void> fireGetShowToastMessage() {
        return fireGetShowToastMessage(new ReqGetShowToastMessage());
    }

    @Override // api.requests.com.neusoft.common.commonsysapiinverse.ICommonSysApiInverseRequests
    public CompletableFuture<Void> fireGetShowToastMessage(final Duration duration) {
        return fireGetShowToastMessage(new ReqGetShowToastMessage()).thenCompose((Function<? super Void, ? extends CompletionStage<U>>) new Function<Void, CompletableFuture<Void>>() { // from class: api.requests.com.neusoft.common.commonsysapiinverse.CommonSysApiInverseRequests.441
            @Override // java9.util.function.Function
            public CompletableFuture<Void> apply(Void r2) {
                return CommonUtil.asyncSleep(duration, CommonSysApiInverseRequests.this.contextUtil.getDefaultExecutor());
            }
        });
    }

    @Override // api.requests.com.neusoft.common.commonsysapiinverse.ICommonSysApiInverseRequests
    public CompletableFuture<ResGetPostCustomEvent> requestGetPostCustomEvent(ReqGetPostCustomEvent reqGetPostCustomEvent) {
        if (reqGetPostCustomEvent.getResponse() == null) {
            reqGetPostCustomEvent.setResponse(new ResGetPostCustomEvent());
        }
        String str = this.component;
        if (str != null) {
            reqGetPostCustomEvent.setComponent(str);
            reqGetPostCustomEvent.getResponse().setComponent(this.component);
        }
        return Communication.request(this.contextUtil.createFromClass(reqGetPostCustomEvent));
    }

    @Override // api.requests.com.neusoft.common.commonsysapiinverse.ICommonSysApiInverseRequests
    public CompletableFuture<ResGetPostCustomEvent> requestGetPostCustomEvent(ReqGetPostCustomEvent reqGetPostCustomEvent, final Duration duration) {
        return requestGetPostCustomEvent(reqGetPostCustomEvent).thenCompose((Function<? super ResGetPostCustomEvent, ? extends CompletionStage<U>>) new Function<ResGetPostCustomEvent, CompletableFuture<ResGetPostCustomEvent>>() { // from class: api.requests.com.neusoft.common.commonsysapiinverse.CommonSysApiInverseRequests.442
            @Override // java9.util.function.Function
            public CompletableFuture<ResGetPostCustomEvent> apply(ResGetPostCustomEvent resGetPostCustomEvent) {
                return CommonUtil.delayAsync(resGetPostCustomEvent, duration, CommonSysApiInverseRequests.this.contextUtil.getDefaultExecutor());
            }
        });
    }

    @Override // api.requests.com.neusoft.common.commonsysapiinverse.ICommonSysApiInverseRequests
    public CompletableFuture<ResGetPostCustomEvent> requestGetPostCustomEvent() {
        return requestGetPostCustomEvent(new ReqGetPostCustomEvent());
    }

    @Override // api.requests.com.neusoft.common.commonsysapiinverse.ICommonSysApiInverseRequests
    public CompletableFuture<ResGetPostCustomEvent> requestGetPostCustomEvent(Duration duration) {
        return requestGetPostCustomEvent(new ReqGetPostCustomEvent(), duration);
    }

    @Override // api.requests.com.neusoft.common.commonsysapiinverse.ICommonSysApiInverseRequests
    public CompletableFuture<Void> fireGetPostCustomEvent(ReqGetPostCustomEvent reqGetPostCustomEvent) {
        reqGetPostCustomEvent.setResponse(null);
        String str = this.component;
        if (str != null) {
            reqGetPostCustomEvent.setComponent(str);
        }
        return Communication.request(this.contextUtil.createFromClass(reqGetPostCustomEvent)).thenCompose(new Function<Object, CompletableFuture<Void>>() { // from class: api.requests.com.neusoft.common.commonsysapiinverse.CommonSysApiInverseRequests.443
            @Override // java9.util.function.Function
            public CompletableFuture<Void> apply(Object obj) {
                return CommonSysApiInverseRequests.completedVoidFuture;
            }
        });
    }

    @Override // api.requests.com.neusoft.common.commonsysapiinverse.ICommonSysApiInverseRequests
    public CompletableFuture<Void> fireGetPostCustomEvent(ReqGetPostCustomEvent reqGetPostCustomEvent, final Duration duration) {
        return fireGetPostCustomEvent(reqGetPostCustomEvent).thenCompose((Function<? super Void, ? extends CompletionStage<U>>) new Function<Void, CompletableFuture<Void>>() { // from class: api.requests.com.neusoft.common.commonsysapiinverse.CommonSysApiInverseRequests.444
            @Override // java9.util.function.Function
            public CompletableFuture<Void> apply(Void r2) {
                return CommonUtil.asyncSleep(duration, CommonSysApiInverseRequests.this.contextUtil.getDefaultExecutor());
            }
        });
    }

    @Override // api.requests.com.neusoft.common.commonsysapiinverse.ICommonSysApiInverseRequests
    public CompletableFuture<Void> fireGetPostCustomEvent() {
        return fireGetPostCustomEvent(new ReqGetPostCustomEvent());
    }

    @Override // api.requests.com.neusoft.common.commonsysapiinverse.ICommonSysApiInverseRequests
    public CompletableFuture<Void> fireGetPostCustomEvent(final Duration duration) {
        return fireGetPostCustomEvent(new ReqGetPostCustomEvent()).thenCompose((Function<? super Void, ? extends CompletionStage<U>>) new Function<Void, CompletableFuture<Void>>() { // from class: api.requests.com.neusoft.common.commonsysapiinverse.CommonSysApiInverseRequests.445
            @Override // java9.util.function.Function
            public CompletableFuture<Void> apply(Void r2) {
                return CommonUtil.asyncSleep(duration, CommonSysApiInverseRequests.this.contextUtil.getDefaultExecutor());
            }
        });
    }

    @Override // api.requests.com.neusoft.common.commonsysapiinverse.ICommonSysApiInverseRequests
    public CompletableFuture<ResGetExportDirectoryToUDisk> requestGetExportDirectoryToUDisk(ReqGetExportDirectoryToUDisk reqGetExportDirectoryToUDisk) {
        if (reqGetExportDirectoryToUDisk.getResponse() == null) {
            reqGetExportDirectoryToUDisk.setResponse(new ResGetExportDirectoryToUDisk());
        }
        String str = this.component;
        if (str != null) {
            reqGetExportDirectoryToUDisk.setComponent(str);
            reqGetExportDirectoryToUDisk.getResponse().setComponent(this.component);
        }
        return Communication.request(this.contextUtil.createFromClass(reqGetExportDirectoryToUDisk));
    }

    @Override // api.requests.com.neusoft.common.commonsysapiinverse.ICommonSysApiInverseRequests
    public CompletableFuture<ResGetExportDirectoryToUDisk> requestGetExportDirectoryToUDisk(ReqGetExportDirectoryToUDisk reqGetExportDirectoryToUDisk, final Duration duration) {
        return requestGetExportDirectoryToUDisk(reqGetExportDirectoryToUDisk).thenCompose((Function<? super ResGetExportDirectoryToUDisk, ? extends CompletionStage<U>>) new Function<ResGetExportDirectoryToUDisk, CompletableFuture<ResGetExportDirectoryToUDisk>>() { // from class: api.requests.com.neusoft.common.commonsysapiinverse.CommonSysApiInverseRequests.446
            @Override // java9.util.function.Function
            public CompletableFuture<ResGetExportDirectoryToUDisk> apply(ResGetExportDirectoryToUDisk resGetExportDirectoryToUDisk) {
                return CommonUtil.delayAsync(resGetExportDirectoryToUDisk, duration, CommonSysApiInverseRequests.this.contextUtil.getDefaultExecutor());
            }
        });
    }

    @Override // api.requests.com.neusoft.common.commonsysapiinverse.ICommonSysApiInverseRequests
    public CompletableFuture<ResGetExportDirectoryToUDisk> requestGetExportDirectoryToUDisk() {
        return requestGetExportDirectoryToUDisk(new ReqGetExportDirectoryToUDisk());
    }

    @Override // api.requests.com.neusoft.common.commonsysapiinverse.ICommonSysApiInverseRequests
    public CompletableFuture<ResGetExportDirectoryToUDisk> requestGetExportDirectoryToUDisk(Duration duration) {
        return requestGetExportDirectoryToUDisk(new ReqGetExportDirectoryToUDisk(), duration);
    }

    @Override // api.requests.com.neusoft.common.commonsysapiinverse.ICommonSysApiInverseRequests
    public CompletableFuture<Void> fireGetExportDirectoryToUDisk(ReqGetExportDirectoryToUDisk reqGetExportDirectoryToUDisk) {
        reqGetExportDirectoryToUDisk.setResponse(null);
        String str = this.component;
        if (str != null) {
            reqGetExportDirectoryToUDisk.setComponent(str);
        }
        return Communication.request(this.contextUtil.createFromClass(reqGetExportDirectoryToUDisk)).thenCompose(new Function<Object, CompletableFuture<Void>>() { // from class: api.requests.com.neusoft.common.commonsysapiinverse.CommonSysApiInverseRequests.447
            @Override // java9.util.function.Function
            public CompletableFuture<Void> apply(Object obj) {
                return CommonSysApiInverseRequests.completedVoidFuture;
            }
        });
    }

    @Override // api.requests.com.neusoft.common.commonsysapiinverse.ICommonSysApiInverseRequests
    public CompletableFuture<Void> fireGetExportDirectoryToUDisk(ReqGetExportDirectoryToUDisk reqGetExportDirectoryToUDisk, final Duration duration) {
        return fireGetExportDirectoryToUDisk(reqGetExportDirectoryToUDisk).thenCompose((Function<? super Void, ? extends CompletionStage<U>>) new Function<Void, CompletableFuture<Void>>() { // from class: api.requests.com.neusoft.common.commonsysapiinverse.CommonSysApiInverseRequests.448
            @Override // java9.util.function.Function
            public CompletableFuture<Void> apply(Void r2) {
                return CommonUtil.asyncSleep(duration, CommonSysApiInverseRequests.this.contextUtil.getDefaultExecutor());
            }
        });
    }

    @Override // api.requests.com.neusoft.common.commonsysapiinverse.ICommonSysApiInverseRequests
    public CompletableFuture<Void> fireGetExportDirectoryToUDisk() {
        return fireGetExportDirectoryToUDisk(new ReqGetExportDirectoryToUDisk());
    }

    @Override // api.requests.com.neusoft.common.commonsysapiinverse.ICommonSysApiInverseRequests
    public CompletableFuture<Void> fireGetExportDirectoryToUDisk(final Duration duration) {
        return fireGetExportDirectoryToUDisk(new ReqGetExportDirectoryToUDisk()).thenCompose((Function<? super Void, ? extends CompletionStage<U>>) new Function<Void, CompletableFuture<Void>>() { // from class: api.requests.com.neusoft.common.commonsysapiinverse.CommonSysApiInverseRequests.449
            @Override // java9.util.function.Function
            public CompletableFuture<Void> apply(Void r2) {
                return CommonUtil.asyncSleep(duration, CommonSysApiInverseRequests.this.contextUtil.getDefaultExecutor());
            }
        });
    }

    @Override // api.requests.com.neusoft.common.commonsysapiinverse.ICommonSysApiInverseRequests
    public CompletableFuture<ResGetNaviStatusChanged> requestGetNaviStatusChanged(ReqGetNaviStatusChanged reqGetNaviStatusChanged) {
        if (reqGetNaviStatusChanged.getResponse() == null) {
            reqGetNaviStatusChanged.setResponse(new ResGetNaviStatusChanged());
        }
        String str = this.component;
        if (str != null) {
            reqGetNaviStatusChanged.setComponent(str);
            reqGetNaviStatusChanged.getResponse().setComponent(this.component);
        }
        return Communication.request(this.contextUtil.createFromClass(reqGetNaviStatusChanged));
    }

    @Override // api.requests.com.neusoft.common.commonsysapiinverse.ICommonSysApiInverseRequests
    public CompletableFuture<ResGetNaviStatusChanged> requestGetNaviStatusChanged(ReqGetNaviStatusChanged reqGetNaviStatusChanged, final Duration duration) {
        return requestGetNaviStatusChanged(reqGetNaviStatusChanged).thenCompose((Function<? super ResGetNaviStatusChanged, ? extends CompletionStage<U>>) new Function<ResGetNaviStatusChanged, CompletableFuture<ResGetNaviStatusChanged>>() { // from class: api.requests.com.neusoft.common.commonsysapiinverse.CommonSysApiInverseRequests.450
            @Override // java9.util.function.Function
            public CompletableFuture<ResGetNaviStatusChanged> apply(ResGetNaviStatusChanged resGetNaviStatusChanged) {
                return CommonUtil.delayAsync(resGetNaviStatusChanged, duration, CommonSysApiInverseRequests.this.contextUtil.getDefaultExecutor());
            }
        });
    }

    @Override // api.requests.com.neusoft.common.commonsysapiinverse.ICommonSysApiInverseRequests
    public CompletableFuture<ResGetNaviStatusChanged> requestGetNaviStatusChanged() {
        return requestGetNaviStatusChanged(new ReqGetNaviStatusChanged());
    }

    @Override // api.requests.com.neusoft.common.commonsysapiinverse.ICommonSysApiInverseRequests
    public CompletableFuture<ResGetNaviStatusChanged> requestGetNaviStatusChanged(Duration duration) {
        return requestGetNaviStatusChanged(new ReqGetNaviStatusChanged(), duration);
    }

    @Override // api.requests.com.neusoft.common.commonsysapiinverse.ICommonSysApiInverseRequests
    public CompletableFuture<Void> fireGetNaviStatusChanged(ReqGetNaviStatusChanged reqGetNaviStatusChanged) {
        reqGetNaviStatusChanged.setResponse(null);
        String str = this.component;
        if (str != null) {
            reqGetNaviStatusChanged.setComponent(str);
        }
        return Communication.request(this.contextUtil.createFromClass(reqGetNaviStatusChanged)).thenCompose(new Function<Object, CompletableFuture<Void>>() { // from class: api.requests.com.neusoft.common.commonsysapiinverse.CommonSysApiInverseRequests.451
            @Override // java9.util.function.Function
            public CompletableFuture<Void> apply(Object obj) {
                return CommonSysApiInverseRequests.completedVoidFuture;
            }
        });
    }

    @Override // api.requests.com.neusoft.common.commonsysapiinverse.ICommonSysApiInverseRequests
    public CompletableFuture<Void> fireGetNaviStatusChanged(ReqGetNaviStatusChanged reqGetNaviStatusChanged, final Duration duration) {
        return fireGetNaviStatusChanged(reqGetNaviStatusChanged).thenCompose((Function<? super Void, ? extends CompletionStage<U>>) new Function<Void, CompletableFuture<Void>>() { // from class: api.requests.com.neusoft.common.commonsysapiinverse.CommonSysApiInverseRequests.452
            @Override // java9.util.function.Function
            public CompletableFuture<Void> apply(Void r2) {
                return CommonUtil.asyncSleep(duration, CommonSysApiInverseRequests.this.contextUtil.getDefaultExecutor());
            }
        });
    }

    @Override // api.requests.com.neusoft.common.commonsysapiinverse.ICommonSysApiInverseRequests
    public CompletableFuture<Void> fireGetNaviStatusChanged() {
        return fireGetNaviStatusChanged(new ReqGetNaviStatusChanged());
    }

    @Override // api.requests.com.neusoft.common.commonsysapiinverse.ICommonSysApiInverseRequests
    public CompletableFuture<Void> fireGetNaviStatusChanged(final Duration duration) {
        return fireGetNaviStatusChanged(new ReqGetNaviStatusChanged()).thenCompose((Function<? super Void, ? extends CompletionStage<U>>) new Function<Void, CompletableFuture<Void>>() { // from class: api.requests.com.neusoft.common.commonsysapiinverse.CommonSysApiInverseRequests.453
            @Override // java9.util.function.Function
            public CompletableFuture<Void> apply(Void r2) {
                return CommonUtil.asyncSleep(duration, CommonSysApiInverseRequests.this.contextUtil.getDefaultExecutor());
            }
        });
    }

    @Override // api.requests.com.neusoft.common.commonsysapiinverse.ICommonSysApiInverseRequests
    public CompletableFuture<ResGetOnNaviRemainTimeDistance> requestGetOnNaviRemainTimeDistance(ReqGetOnNaviRemainTimeDistance reqGetOnNaviRemainTimeDistance) {
        if (reqGetOnNaviRemainTimeDistance.getResponse() == null) {
            reqGetOnNaviRemainTimeDistance.setResponse(new ResGetOnNaviRemainTimeDistance());
        }
        String str = this.component;
        if (str != null) {
            reqGetOnNaviRemainTimeDistance.setComponent(str);
            reqGetOnNaviRemainTimeDistance.getResponse().setComponent(this.component);
        }
        return Communication.request(this.contextUtil.createFromClass(reqGetOnNaviRemainTimeDistance));
    }

    @Override // api.requests.com.neusoft.common.commonsysapiinverse.ICommonSysApiInverseRequests
    public CompletableFuture<ResGetOnNaviRemainTimeDistance> requestGetOnNaviRemainTimeDistance(ReqGetOnNaviRemainTimeDistance reqGetOnNaviRemainTimeDistance, final Duration duration) {
        return requestGetOnNaviRemainTimeDistance(reqGetOnNaviRemainTimeDistance).thenCompose((Function<? super ResGetOnNaviRemainTimeDistance, ? extends CompletionStage<U>>) new Function<ResGetOnNaviRemainTimeDistance, CompletableFuture<ResGetOnNaviRemainTimeDistance>>() { // from class: api.requests.com.neusoft.common.commonsysapiinverse.CommonSysApiInverseRequests.454
            @Override // java9.util.function.Function
            public CompletableFuture<ResGetOnNaviRemainTimeDistance> apply(ResGetOnNaviRemainTimeDistance resGetOnNaviRemainTimeDistance) {
                return CommonUtil.delayAsync(resGetOnNaviRemainTimeDistance, duration, CommonSysApiInverseRequests.this.contextUtil.getDefaultExecutor());
            }
        });
    }

    @Override // api.requests.com.neusoft.common.commonsysapiinverse.ICommonSysApiInverseRequests
    public CompletableFuture<ResGetOnNaviRemainTimeDistance> requestGetOnNaviRemainTimeDistance() {
        return requestGetOnNaviRemainTimeDistance(new ReqGetOnNaviRemainTimeDistance());
    }

    @Override // api.requests.com.neusoft.common.commonsysapiinverse.ICommonSysApiInverseRequests
    public CompletableFuture<ResGetOnNaviRemainTimeDistance> requestGetOnNaviRemainTimeDistance(Duration duration) {
        return requestGetOnNaviRemainTimeDistance(new ReqGetOnNaviRemainTimeDistance(), duration);
    }

    @Override // api.requests.com.neusoft.common.commonsysapiinverse.ICommonSysApiInverseRequests
    public CompletableFuture<Void> fireGetOnNaviRemainTimeDistance(ReqGetOnNaviRemainTimeDistance reqGetOnNaviRemainTimeDistance) {
        reqGetOnNaviRemainTimeDistance.setResponse(null);
        String str = this.component;
        if (str != null) {
            reqGetOnNaviRemainTimeDistance.setComponent(str);
        }
        return Communication.request(this.contextUtil.createFromClass(reqGetOnNaviRemainTimeDistance)).thenCompose(new Function<Object, CompletableFuture<Void>>() { // from class: api.requests.com.neusoft.common.commonsysapiinverse.CommonSysApiInverseRequests.455
            @Override // java9.util.function.Function
            public CompletableFuture<Void> apply(Object obj) {
                return CommonSysApiInverseRequests.completedVoidFuture;
            }
        });
    }

    @Override // api.requests.com.neusoft.common.commonsysapiinverse.ICommonSysApiInverseRequests
    public CompletableFuture<Void> fireGetOnNaviRemainTimeDistance(ReqGetOnNaviRemainTimeDistance reqGetOnNaviRemainTimeDistance, final Duration duration) {
        return fireGetOnNaviRemainTimeDistance(reqGetOnNaviRemainTimeDistance).thenCompose((Function<? super Void, ? extends CompletionStage<U>>) new Function<Void, CompletableFuture<Void>>() { // from class: api.requests.com.neusoft.common.commonsysapiinverse.CommonSysApiInverseRequests.456
            @Override // java9.util.function.Function
            public CompletableFuture<Void> apply(Void r2) {
                return CommonUtil.asyncSleep(duration, CommonSysApiInverseRequests.this.contextUtil.getDefaultExecutor());
            }
        });
    }

    @Override // api.requests.com.neusoft.common.commonsysapiinverse.ICommonSysApiInverseRequests
    public CompletableFuture<Void> fireGetOnNaviRemainTimeDistance() {
        return fireGetOnNaviRemainTimeDistance(new ReqGetOnNaviRemainTimeDistance());
    }

    @Override // api.requests.com.neusoft.common.commonsysapiinverse.ICommonSysApiInverseRequests
    public CompletableFuture<Void> fireGetOnNaviRemainTimeDistance(final Duration duration) {
        return fireGetOnNaviRemainTimeDistance(new ReqGetOnNaviRemainTimeDistance()).thenCompose((Function<? super Void, ? extends CompletionStage<U>>) new Function<Void, CompletableFuture<Void>>() { // from class: api.requests.com.neusoft.common.commonsysapiinverse.CommonSysApiInverseRequests.457
            @Override // java9.util.function.Function
            public CompletableFuture<Void> apply(Void r2) {
                return CommonUtil.asyncSleep(duration, CommonSysApiInverseRequests.this.contextUtil.getDefaultExecutor());
            }
        });
    }

    @Override // api.requests.com.neusoft.common.commonsysapiinverse.ICommonSysApiInverseRequests
    public CompletableFuture<ResGetGuidanceInfoUpdate> requestGetGuidanceInfoUpdate(ReqGetGuidanceInfoUpdate reqGetGuidanceInfoUpdate) {
        if (reqGetGuidanceInfoUpdate.getResponse() == null) {
            reqGetGuidanceInfoUpdate.setResponse(new ResGetGuidanceInfoUpdate());
        }
        String str = this.component;
        if (str != null) {
            reqGetGuidanceInfoUpdate.setComponent(str);
            reqGetGuidanceInfoUpdate.getResponse().setComponent(this.component);
        }
        return Communication.request(this.contextUtil.createFromClass(reqGetGuidanceInfoUpdate));
    }

    @Override // api.requests.com.neusoft.common.commonsysapiinverse.ICommonSysApiInverseRequests
    public CompletableFuture<ResGetGuidanceInfoUpdate> requestGetGuidanceInfoUpdate(ReqGetGuidanceInfoUpdate reqGetGuidanceInfoUpdate, final Duration duration) {
        return requestGetGuidanceInfoUpdate(reqGetGuidanceInfoUpdate).thenCompose((Function<? super ResGetGuidanceInfoUpdate, ? extends CompletionStage<U>>) new Function<ResGetGuidanceInfoUpdate, CompletableFuture<ResGetGuidanceInfoUpdate>>() { // from class: api.requests.com.neusoft.common.commonsysapiinverse.CommonSysApiInverseRequests.458
            @Override // java9.util.function.Function
            public CompletableFuture<ResGetGuidanceInfoUpdate> apply(ResGetGuidanceInfoUpdate resGetGuidanceInfoUpdate) {
                return CommonUtil.delayAsync(resGetGuidanceInfoUpdate, duration, CommonSysApiInverseRequests.this.contextUtil.getDefaultExecutor());
            }
        });
    }

    @Override // api.requests.com.neusoft.common.commonsysapiinverse.ICommonSysApiInverseRequests
    public CompletableFuture<ResGetGuidanceInfoUpdate> requestGetGuidanceInfoUpdate() {
        return requestGetGuidanceInfoUpdate(new ReqGetGuidanceInfoUpdate());
    }

    @Override // api.requests.com.neusoft.common.commonsysapiinverse.ICommonSysApiInverseRequests
    public CompletableFuture<ResGetGuidanceInfoUpdate> requestGetGuidanceInfoUpdate(Duration duration) {
        return requestGetGuidanceInfoUpdate(new ReqGetGuidanceInfoUpdate(), duration);
    }

    @Override // api.requests.com.neusoft.common.commonsysapiinverse.ICommonSysApiInverseRequests
    public CompletableFuture<Void> fireGetGuidanceInfoUpdate(ReqGetGuidanceInfoUpdate reqGetGuidanceInfoUpdate) {
        reqGetGuidanceInfoUpdate.setResponse(null);
        String str = this.component;
        if (str != null) {
            reqGetGuidanceInfoUpdate.setComponent(str);
        }
        return Communication.request(this.contextUtil.createFromClass(reqGetGuidanceInfoUpdate)).thenCompose(new Function<Object, CompletableFuture<Void>>() { // from class: api.requests.com.neusoft.common.commonsysapiinverse.CommonSysApiInverseRequests.459
            @Override // java9.util.function.Function
            public CompletableFuture<Void> apply(Object obj) {
                return CommonSysApiInverseRequests.completedVoidFuture;
            }
        });
    }

    @Override // api.requests.com.neusoft.common.commonsysapiinverse.ICommonSysApiInverseRequests
    public CompletableFuture<Void> fireGetGuidanceInfoUpdate(ReqGetGuidanceInfoUpdate reqGetGuidanceInfoUpdate, final Duration duration) {
        return fireGetGuidanceInfoUpdate(reqGetGuidanceInfoUpdate).thenCompose((Function<? super Void, ? extends CompletionStage<U>>) new Function<Void, CompletableFuture<Void>>() { // from class: api.requests.com.neusoft.common.commonsysapiinverse.CommonSysApiInverseRequests.460
            @Override // java9.util.function.Function
            public CompletableFuture<Void> apply(Void r2) {
                return CommonUtil.asyncSleep(duration, CommonSysApiInverseRequests.this.contextUtil.getDefaultExecutor());
            }
        });
    }

    @Override // api.requests.com.neusoft.common.commonsysapiinverse.ICommonSysApiInverseRequests
    public CompletableFuture<Void> fireGetGuidanceInfoUpdate() {
        return fireGetGuidanceInfoUpdate(new ReqGetGuidanceInfoUpdate());
    }

    @Override // api.requests.com.neusoft.common.commonsysapiinverse.ICommonSysApiInverseRequests
    public CompletableFuture<Void> fireGetGuidanceInfoUpdate(final Duration duration) {
        return fireGetGuidanceInfoUpdate(new ReqGetGuidanceInfoUpdate()).thenCompose((Function<? super Void, ? extends CompletionStage<U>>) new Function<Void, CompletableFuture<Void>>() { // from class: api.requests.com.neusoft.common.commonsysapiinverse.CommonSysApiInverseRequests.461
            @Override // java9.util.function.Function
            public CompletableFuture<Void> apply(Void r2) {
                return CommonUtil.asyncSleep(duration, CommonSysApiInverseRequests.this.contextUtil.getDefaultExecutor());
            }
        });
    }

    @Override // api.requests.com.neusoft.common.commonsysapiinverse.ICommonSysApiInverseRequests
    public CompletableFuture<ResGetMonitorCOMMStatus> requestGetMonitorCOMMStatus(ReqGetMonitorCOMMStatus reqGetMonitorCOMMStatus) {
        if (reqGetMonitorCOMMStatus.getResponse() == null) {
            reqGetMonitorCOMMStatus.setResponse(new ResGetMonitorCOMMStatus());
        }
        String str = this.component;
        if (str != null) {
            reqGetMonitorCOMMStatus.setComponent(str);
            reqGetMonitorCOMMStatus.getResponse().setComponent(this.component);
        }
        return Communication.request(this.contextUtil.createFromClass(reqGetMonitorCOMMStatus));
    }

    @Override // api.requests.com.neusoft.common.commonsysapiinverse.ICommonSysApiInverseRequests
    public CompletableFuture<ResGetMonitorCOMMStatus> requestGetMonitorCOMMStatus(ReqGetMonitorCOMMStatus reqGetMonitorCOMMStatus, final Duration duration) {
        return requestGetMonitorCOMMStatus(reqGetMonitorCOMMStatus).thenCompose((Function<? super ResGetMonitorCOMMStatus, ? extends CompletionStage<U>>) new Function<ResGetMonitorCOMMStatus, CompletableFuture<ResGetMonitorCOMMStatus>>() { // from class: api.requests.com.neusoft.common.commonsysapiinverse.CommonSysApiInverseRequests.462
            @Override // java9.util.function.Function
            public CompletableFuture<ResGetMonitorCOMMStatus> apply(ResGetMonitorCOMMStatus resGetMonitorCOMMStatus) {
                return CommonUtil.delayAsync(resGetMonitorCOMMStatus, duration, CommonSysApiInverseRequests.this.contextUtil.getDefaultExecutor());
            }
        });
    }

    @Override // api.requests.com.neusoft.common.commonsysapiinverse.ICommonSysApiInverseRequests
    public CompletableFuture<ResGetMonitorCOMMStatus> requestGetMonitorCOMMStatus() {
        return requestGetMonitorCOMMStatus(new ReqGetMonitorCOMMStatus());
    }

    @Override // api.requests.com.neusoft.common.commonsysapiinverse.ICommonSysApiInverseRequests
    public CompletableFuture<ResGetMonitorCOMMStatus> requestGetMonitorCOMMStatus(Duration duration) {
        return requestGetMonitorCOMMStatus(new ReqGetMonitorCOMMStatus(), duration);
    }

    @Override // api.requests.com.neusoft.common.commonsysapiinverse.ICommonSysApiInverseRequests
    public CompletableFuture<Void> fireGetMonitorCOMMStatus(ReqGetMonitorCOMMStatus reqGetMonitorCOMMStatus) {
        reqGetMonitorCOMMStatus.setResponse(null);
        String str = this.component;
        if (str != null) {
            reqGetMonitorCOMMStatus.setComponent(str);
        }
        return Communication.request(this.contextUtil.createFromClass(reqGetMonitorCOMMStatus)).thenCompose(new Function<Object, CompletableFuture<Void>>() { // from class: api.requests.com.neusoft.common.commonsysapiinverse.CommonSysApiInverseRequests.463
            @Override // java9.util.function.Function
            public CompletableFuture<Void> apply(Object obj) {
                return CommonSysApiInverseRequests.completedVoidFuture;
            }
        });
    }

    @Override // api.requests.com.neusoft.common.commonsysapiinverse.ICommonSysApiInverseRequests
    public CompletableFuture<Void> fireGetMonitorCOMMStatus(ReqGetMonitorCOMMStatus reqGetMonitorCOMMStatus, final Duration duration) {
        return fireGetMonitorCOMMStatus(reqGetMonitorCOMMStatus).thenCompose((Function<? super Void, ? extends CompletionStage<U>>) new Function<Void, CompletableFuture<Void>>() { // from class: api.requests.com.neusoft.common.commonsysapiinverse.CommonSysApiInverseRequests.464
            @Override // java9.util.function.Function
            public CompletableFuture<Void> apply(Void r2) {
                return CommonUtil.asyncSleep(duration, CommonSysApiInverseRequests.this.contextUtil.getDefaultExecutor());
            }
        });
    }

    @Override // api.requests.com.neusoft.common.commonsysapiinverse.ICommonSysApiInverseRequests
    public CompletableFuture<Void> fireGetMonitorCOMMStatus() {
        return fireGetMonitorCOMMStatus(new ReqGetMonitorCOMMStatus());
    }

    @Override // api.requests.com.neusoft.common.commonsysapiinverse.ICommonSysApiInverseRequests
    public CompletableFuture<Void> fireGetMonitorCOMMStatus(final Duration duration) {
        return fireGetMonitorCOMMStatus(new ReqGetMonitorCOMMStatus()).thenCompose((Function<? super Void, ? extends CompletionStage<U>>) new Function<Void, CompletableFuture<Void>>() { // from class: api.requests.com.neusoft.common.commonsysapiinverse.CommonSysApiInverseRequests.465
            @Override // java9.util.function.Function
            public CompletableFuture<Void> apply(Void r2) {
                return CommonUtil.asyncSleep(duration, CommonSysApiInverseRequests.this.contextUtil.getDefaultExecutor());
            }
        });
    }

    @Override // api.requests.com.neusoft.common.commonsysapiinverse.ICommonSysApiInverseRequests
    public CompletableFuture<ResGetSpeak> requestGetSpeak(ReqGetSpeak reqGetSpeak) {
        if (reqGetSpeak.getResponse() == null) {
            reqGetSpeak.setResponse(new ResGetSpeak());
        }
        String str = this.component;
        if (str != null) {
            reqGetSpeak.setComponent(str);
            reqGetSpeak.getResponse().setComponent(this.component);
        }
        return Communication.request(this.contextUtil.createFromClass(reqGetSpeak));
    }

    @Override // api.requests.com.neusoft.common.commonsysapiinverse.ICommonSysApiInverseRequests
    public CompletableFuture<ResGetSpeak> requestGetSpeak(ReqGetSpeak reqGetSpeak, final Duration duration) {
        return requestGetSpeak(reqGetSpeak).thenCompose((Function<? super ResGetSpeak, ? extends CompletionStage<U>>) new Function<ResGetSpeak, CompletableFuture<ResGetSpeak>>() { // from class: api.requests.com.neusoft.common.commonsysapiinverse.CommonSysApiInverseRequests.466
            @Override // java9.util.function.Function
            public CompletableFuture<ResGetSpeak> apply(ResGetSpeak resGetSpeak) {
                return CommonUtil.delayAsync(resGetSpeak, duration, CommonSysApiInverseRequests.this.contextUtil.getDefaultExecutor());
            }
        });
    }

    @Override // api.requests.com.neusoft.common.commonsysapiinverse.ICommonSysApiInverseRequests
    public CompletableFuture<ResGetSpeak> requestGetSpeak() {
        return requestGetSpeak(new ReqGetSpeak());
    }

    @Override // api.requests.com.neusoft.common.commonsysapiinverse.ICommonSysApiInverseRequests
    public CompletableFuture<ResGetSpeak> requestGetSpeak(Duration duration) {
        return requestGetSpeak(new ReqGetSpeak(), duration);
    }

    @Override // api.requests.com.neusoft.common.commonsysapiinverse.ICommonSysApiInverseRequests
    public CompletableFuture<Void> fireGetSpeak(ReqGetSpeak reqGetSpeak) {
        reqGetSpeak.setResponse(null);
        String str = this.component;
        if (str != null) {
            reqGetSpeak.setComponent(str);
        }
        return Communication.request(this.contextUtil.createFromClass(reqGetSpeak)).thenCompose(new Function<Object, CompletableFuture<Void>>() { // from class: api.requests.com.neusoft.common.commonsysapiinverse.CommonSysApiInverseRequests.467
            @Override // java9.util.function.Function
            public CompletableFuture<Void> apply(Object obj) {
                return CommonSysApiInverseRequests.completedVoidFuture;
            }
        });
    }

    @Override // api.requests.com.neusoft.common.commonsysapiinverse.ICommonSysApiInverseRequests
    public CompletableFuture<Void> fireGetSpeak(ReqGetSpeak reqGetSpeak, final Duration duration) {
        return fireGetSpeak(reqGetSpeak).thenCompose((Function<? super Void, ? extends CompletionStage<U>>) new Function<Void, CompletableFuture<Void>>() { // from class: api.requests.com.neusoft.common.commonsysapiinverse.CommonSysApiInverseRequests.468
            @Override // java9.util.function.Function
            public CompletableFuture<Void> apply(Void r2) {
                return CommonUtil.asyncSleep(duration, CommonSysApiInverseRequests.this.contextUtil.getDefaultExecutor());
            }
        });
    }

    @Override // api.requests.com.neusoft.common.commonsysapiinverse.ICommonSysApiInverseRequests
    public CompletableFuture<Void> fireGetSpeak() {
        return fireGetSpeak(new ReqGetSpeak());
    }

    @Override // api.requests.com.neusoft.common.commonsysapiinverse.ICommonSysApiInverseRequests
    public CompletableFuture<Void> fireGetSpeak(final Duration duration) {
        return fireGetSpeak(new ReqGetSpeak()).thenCompose((Function<? super Void, ? extends CompletionStage<U>>) new Function<Void, CompletableFuture<Void>>() { // from class: api.requests.com.neusoft.common.commonsysapiinverse.CommonSysApiInverseRequests.469
            @Override // java9.util.function.Function
            public CompletableFuture<Void> apply(Void r2) {
                return CommonUtil.asyncSleep(duration, CommonSysApiInverseRequests.this.contextUtil.getDefaultExecutor());
            }
        });
    }

    @Override // api.requests.com.neusoft.common.commonsysapiinverse.ICommonSysApiInverseRequests
    public CompletableFuture<ResGetAbandonSpeak> requestGetAbandonSpeak(ReqGetAbandonSpeak reqGetAbandonSpeak) {
        if (reqGetAbandonSpeak.getResponse() == null) {
            reqGetAbandonSpeak.setResponse(new ResGetAbandonSpeak());
        }
        String str = this.component;
        if (str != null) {
            reqGetAbandonSpeak.setComponent(str);
            reqGetAbandonSpeak.getResponse().setComponent(this.component);
        }
        return Communication.request(this.contextUtil.createFromClass(reqGetAbandonSpeak));
    }

    @Override // api.requests.com.neusoft.common.commonsysapiinverse.ICommonSysApiInverseRequests
    public CompletableFuture<ResGetAbandonSpeak> requestGetAbandonSpeak(ReqGetAbandonSpeak reqGetAbandonSpeak, final Duration duration) {
        return requestGetAbandonSpeak(reqGetAbandonSpeak).thenCompose((Function<? super ResGetAbandonSpeak, ? extends CompletionStage<U>>) new Function<ResGetAbandonSpeak, CompletableFuture<ResGetAbandonSpeak>>() { // from class: api.requests.com.neusoft.common.commonsysapiinverse.CommonSysApiInverseRequests.470
            @Override // java9.util.function.Function
            public CompletableFuture<ResGetAbandonSpeak> apply(ResGetAbandonSpeak resGetAbandonSpeak) {
                return CommonUtil.delayAsync(resGetAbandonSpeak, duration, CommonSysApiInverseRequests.this.contextUtil.getDefaultExecutor());
            }
        });
    }

    @Override // api.requests.com.neusoft.common.commonsysapiinverse.ICommonSysApiInverseRequests
    public CompletableFuture<ResGetAbandonSpeak> requestGetAbandonSpeak() {
        return requestGetAbandonSpeak(new ReqGetAbandonSpeak());
    }

    @Override // api.requests.com.neusoft.common.commonsysapiinverse.ICommonSysApiInverseRequests
    public CompletableFuture<ResGetAbandonSpeak> requestGetAbandonSpeak(Duration duration) {
        return requestGetAbandonSpeak(new ReqGetAbandonSpeak(), duration);
    }

    @Override // api.requests.com.neusoft.common.commonsysapiinverse.ICommonSysApiInverseRequests
    public CompletableFuture<Void> fireGetAbandonSpeak(ReqGetAbandonSpeak reqGetAbandonSpeak) {
        reqGetAbandonSpeak.setResponse(null);
        String str = this.component;
        if (str != null) {
            reqGetAbandonSpeak.setComponent(str);
        }
        return Communication.request(this.contextUtil.createFromClass(reqGetAbandonSpeak)).thenCompose(new Function<Object, CompletableFuture<Void>>() { // from class: api.requests.com.neusoft.common.commonsysapiinverse.CommonSysApiInverseRequests.471
            @Override // java9.util.function.Function
            public CompletableFuture<Void> apply(Object obj) {
                return CommonSysApiInverseRequests.completedVoidFuture;
            }
        });
    }

    @Override // api.requests.com.neusoft.common.commonsysapiinverse.ICommonSysApiInverseRequests
    public CompletableFuture<Void> fireGetAbandonSpeak(ReqGetAbandonSpeak reqGetAbandonSpeak, final Duration duration) {
        return fireGetAbandonSpeak(reqGetAbandonSpeak).thenCompose((Function<? super Void, ? extends CompletionStage<U>>) new Function<Void, CompletableFuture<Void>>() { // from class: api.requests.com.neusoft.common.commonsysapiinverse.CommonSysApiInverseRequests.472
            @Override // java9.util.function.Function
            public CompletableFuture<Void> apply(Void r2) {
                return CommonUtil.asyncSleep(duration, CommonSysApiInverseRequests.this.contextUtil.getDefaultExecutor());
            }
        });
    }

    @Override // api.requests.com.neusoft.common.commonsysapiinverse.ICommonSysApiInverseRequests
    public CompletableFuture<Void> fireGetAbandonSpeak() {
        return fireGetAbandonSpeak(new ReqGetAbandonSpeak());
    }

    @Override // api.requests.com.neusoft.common.commonsysapiinverse.ICommonSysApiInverseRequests
    public CompletableFuture<Void> fireGetAbandonSpeak(final Duration duration) {
        return fireGetAbandonSpeak(new ReqGetAbandonSpeak()).thenCompose((Function<? super Void, ? extends CompletionStage<U>>) new Function<Void, CompletableFuture<Void>>() { // from class: api.requests.com.neusoft.common.commonsysapiinverse.CommonSysApiInverseRequests.473
            @Override // java9.util.function.Function
            public CompletableFuture<Void> apply(Void r2) {
                return CommonUtil.asyncSleep(duration, CommonSysApiInverseRequests.this.contextUtil.getDefaultExecutor());
            }
        });
    }

    @Override // api.requests.com.neusoft.common.commonsysapiinverse.ICommonSysApiInverseRequests
    public CompletableFuture<ResGetPlayBeepTone> requestGetPlayBeepTone(ReqGetPlayBeepTone reqGetPlayBeepTone) {
        if (reqGetPlayBeepTone.getResponse() == null) {
            reqGetPlayBeepTone.setResponse(new ResGetPlayBeepTone());
        }
        String str = this.component;
        if (str != null) {
            reqGetPlayBeepTone.setComponent(str);
            reqGetPlayBeepTone.getResponse().setComponent(this.component);
        }
        return Communication.request(this.contextUtil.createFromClass(reqGetPlayBeepTone));
    }

    @Override // api.requests.com.neusoft.common.commonsysapiinverse.ICommonSysApiInverseRequests
    public CompletableFuture<ResGetPlayBeepTone> requestGetPlayBeepTone(ReqGetPlayBeepTone reqGetPlayBeepTone, final Duration duration) {
        return requestGetPlayBeepTone(reqGetPlayBeepTone).thenCompose((Function<? super ResGetPlayBeepTone, ? extends CompletionStage<U>>) new Function<ResGetPlayBeepTone, CompletableFuture<ResGetPlayBeepTone>>() { // from class: api.requests.com.neusoft.common.commonsysapiinverse.CommonSysApiInverseRequests.474
            @Override // java9.util.function.Function
            public CompletableFuture<ResGetPlayBeepTone> apply(ResGetPlayBeepTone resGetPlayBeepTone) {
                return CommonUtil.delayAsync(resGetPlayBeepTone, duration, CommonSysApiInverseRequests.this.contextUtil.getDefaultExecutor());
            }
        });
    }

    @Override // api.requests.com.neusoft.common.commonsysapiinverse.ICommonSysApiInverseRequests
    public CompletableFuture<ResGetPlayBeepTone> requestGetPlayBeepTone() {
        return requestGetPlayBeepTone(new ReqGetPlayBeepTone());
    }

    @Override // api.requests.com.neusoft.common.commonsysapiinverse.ICommonSysApiInverseRequests
    public CompletableFuture<ResGetPlayBeepTone> requestGetPlayBeepTone(Duration duration) {
        return requestGetPlayBeepTone(new ReqGetPlayBeepTone(), duration);
    }

    @Override // api.requests.com.neusoft.common.commonsysapiinverse.ICommonSysApiInverseRequests
    public CompletableFuture<Void> fireGetPlayBeepTone(ReqGetPlayBeepTone reqGetPlayBeepTone) {
        reqGetPlayBeepTone.setResponse(null);
        String str = this.component;
        if (str != null) {
            reqGetPlayBeepTone.setComponent(str);
        }
        return Communication.request(this.contextUtil.createFromClass(reqGetPlayBeepTone)).thenCompose(new Function<Object, CompletableFuture<Void>>() { // from class: api.requests.com.neusoft.common.commonsysapiinverse.CommonSysApiInverseRequests.475
            @Override // java9.util.function.Function
            public CompletableFuture<Void> apply(Object obj) {
                return CommonSysApiInverseRequests.completedVoidFuture;
            }
        });
    }

    @Override // api.requests.com.neusoft.common.commonsysapiinverse.ICommonSysApiInverseRequests
    public CompletableFuture<Void> fireGetPlayBeepTone(ReqGetPlayBeepTone reqGetPlayBeepTone, final Duration duration) {
        return fireGetPlayBeepTone(reqGetPlayBeepTone).thenCompose((Function<? super Void, ? extends CompletionStage<U>>) new Function<Void, CompletableFuture<Void>>() { // from class: api.requests.com.neusoft.common.commonsysapiinverse.CommonSysApiInverseRequests.476
            @Override // java9.util.function.Function
            public CompletableFuture<Void> apply(Void r2) {
                return CommonUtil.asyncSleep(duration, CommonSysApiInverseRequests.this.contextUtil.getDefaultExecutor());
            }
        });
    }

    @Override // api.requests.com.neusoft.common.commonsysapiinverse.ICommonSysApiInverseRequests
    public CompletableFuture<Void> fireGetPlayBeepTone() {
        return fireGetPlayBeepTone(new ReqGetPlayBeepTone());
    }

    @Override // api.requests.com.neusoft.common.commonsysapiinverse.ICommonSysApiInverseRequests
    public CompletableFuture<Void> fireGetPlayBeepTone(final Duration duration) {
        return fireGetPlayBeepTone(new ReqGetPlayBeepTone()).thenCompose((Function<? super Void, ? extends CompletionStage<U>>) new Function<Void, CompletableFuture<Void>>() { // from class: api.requests.com.neusoft.common.commonsysapiinverse.CommonSysApiInverseRequests.477
            @Override // java9.util.function.Function
            public CompletableFuture<Void> apply(Void r2) {
                return CommonUtil.asyncSleep(duration, CommonSysApiInverseRequests.this.contextUtil.getDefaultExecutor());
            }
        });
    }

    @Override // api.requests.com.neusoft.common.commonsysapiinverse.ICommonSysApiInverseRequests
    public CompletableFuture<ResGetPlaySoundEffect> requestGetPlaySoundEffect(ReqGetPlaySoundEffect reqGetPlaySoundEffect) {
        if (reqGetPlaySoundEffect.getResponse() == null) {
            reqGetPlaySoundEffect.setResponse(new ResGetPlaySoundEffect());
        }
        String str = this.component;
        if (str != null) {
            reqGetPlaySoundEffect.setComponent(str);
            reqGetPlaySoundEffect.getResponse().setComponent(this.component);
        }
        return Communication.request(this.contextUtil.createFromClass(reqGetPlaySoundEffect));
    }

    @Override // api.requests.com.neusoft.common.commonsysapiinverse.ICommonSysApiInverseRequests
    public CompletableFuture<ResGetPlaySoundEffect> requestGetPlaySoundEffect(ReqGetPlaySoundEffect reqGetPlaySoundEffect, final Duration duration) {
        return requestGetPlaySoundEffect(reqGetPlaySoundEffect).thenCompose((Function<? super ResGetPlaySoundEffect, ? extends CompletionStage<U>>) new Function<ResGetPlaySoundEffect, CompletableFuture<ResGetPlaySoundEffect>>() { // from class: api.requests.com.neusoft.common.commonsysapiinverse.CommonSysApiInverseRequests.478
            @Override // java9.util.function.Function
            public CompletableFuture<ResGetPlaySoundEffect> apply(ResGetPlaySoundEffect resGetPlaySoundEffect) {
                return CommonUtil.delayAsync(resGetPlaySoundEffect, duration, CommonSysApiInverseRequests.this.contextUtil.getDefaultExecutor());
            }
        });
    }

    @Override // api.requests.com.neusoft.common.commonsysapiinverse.ICommonSysApiInverseRequests
    public CompletableFuture<ResGetPlaySoundEffect> requestGetPlaySoundEffect() {
        return requestGetPlaySoundEffect(new ReqGetPlaySoundEffect());
    }

    @Override // api.requests.com.neusoft.common.commonsysapiinverse.ICommonSysApiInverseRequests
    public CompletableFuture<ResGetPlaySoundEffect> requestGetPlaySoundEffect(Duration duration) {
        return requestGetPlaySoundEffect(new ReqGetPlaySoundEffect(), duration);
    }

    @Override // api.requests.com.neusoft.common.commonsysapiinverse.ICommonSysApiInverseRequests
    public CompletableFuture<Void> fireGetPlaySoundEffect(ReqGetPlaySoundEffect reqGetPlaySoundEffect) {
        reqGetPlaySoundEffect.setResponse(null);
        String str = this.component;
        if (str != null) {
            reqGetPlaySoundEffect.setComponent(str);
        }
        return Communication.request(this.contextUtil.createFromClass(reqGetPlaySoundEffect)).thenCompose(new Function<Object, CompletableFuture<Void>>() { // from class: api.requests.com.neusoft.common.commonsysapiinverse.CommonSysApiInverseRequests.479
            @Override // java9.util.function.Function
            public CompletableFuture<Void> apply(Object obj) {
                return CommonSysApiInverseRequests.completedVoidFuture;
            }
        });
    }

    @Override // api.requests.com.neusoft.common.commonsysapiinverse.ICommonSysApiInverseRequests
    public CompletableFuture<Void> fireGetPlaySoundEffect(ReqGetPlaySoundEffect reqGetPlaySoundEffect, final Duration duration) {
        return fireGetPlaySoundEffect(reqGetPlaySoundEffect).thenCompose((Function<? super Void, ? extends CompletionStage<U>>) new Function<Void, CompletableFuture<Void>>() { // from class: api.requests.com.neusoft.common.commonsysapiinverse.CommonSysApiInverseRequests.480
            @Override // java9.util.function.Function
            public CompletableFuture<Void> apply(Void r2) {
                return CommonUtil.asyncSleep(duration, CommonSysApiInverseRequests.this.contextUtil.getDefaultExecutor());
            }
        });
    }

    @Override // api.requests.com.neusoft.common.commonsysapiinverse.ICommonSysApiInverseRequests
    public CompletableFuture<Void> fireGetPlaySoundEffect() {
        return fireGetPlaySoundEffect(new ReqGetPlaySoundEffect());
    }

    @Override // api.requests.com.neusoft.common.commonsysapiinverse.ICommonSysApiInverseRequests
    public CompletableFuture<Void> fireGetPlaySoundEffect(final Duration duration) {
        return fireGetPlaySoundEffect(new ReqGetPlaySoundEffect()).thenCompose((Function<? super Void, ? extends CompletionStage<U>>) new Function<Void, CompletableFuture<Void>>() { // from class: api.requests.com.neusoft.common.commonsysapiinverse.CommonSysApiInverseRequests.481
            @Override // java9.util.function.Function
            public CompletableFuture<Void> apply(Void r2) {
                return CommonUtil.asyncSleep(duration, CommonSysApiInverseRequests.this.contextUtil.getDefaultExecutor());
            }
        });
    }

    @Override // api.requests.com.neusoft.common.commonsysapiinverse.ICommonSysApiInverseRequests
    public CompletableFuture<ResGetCurrentLocation> requestGetCurrentLocation(ReqGetCurrentLocation reqGetCurrentLocation) {
        if (reqGetCurrentLocation.getResponse() == null) {
            reqGetCurrentLocation.setResponse(new ResGetCurrentLocation());
        }
        String str = this.component;
        if (str != null) {
            reqGetCurrentLocation.setComponent(str);
            reqGetCurrentLocation.getResponse().setComponent(this.component);
        }
        return Communication.request(this.contextUtil.createFromClass(reqGetCurrentLocation));
    }

    @Override // api.requests.com.neusoft.common.commonsysapiinverse.ICommonSysApiInverseRequests
    public CompletableFuture<ResGetCurrentLocation> requestGetCurrentLocation(ReqGetCurrentLocation reqGetCurrentLocation, final Duration duration) {
        return requestGetCurrentLocation(reqGetCurrentLocation).thenCompose((Function<? super ResGetCurrentLocation, ? extends CompletionStage<U>>) new Function<ResGetCurrentLocation, CompletableFuture<ResGetCurrentLocation>>() { // from class: api.requests.com.neusoft.common.commonsysapiinverse.CommonSysApiInverseRequests.482
            @Override // java9.util.function.Function
            public CompletableFuture<ResGetCurrentLocation> apply(ResGetCurrentLocation resGetCurrentLocation) {
                return CommonUtil.delayAsync(resGetCurrentLocation, duration, CommonSysApiInverseRequests.this.contextUtil.getDefaultExecutor());
            }
        });
    }

    @Override // api.requests.com.neusoft.common.commonsysapiinverse.ICommonSysApiInverseRequests
    public CompletableFuture<ResGetCurrentLocation> requestGetCurrentLocation() {
        return requestGetCurrentLocation(new ReqGetCurrentLocation());
    }

    @Override // api.requests.com.neusoft.common.commonsysapiinverse.ICommonSysApiInverseRequests
    public CompletableFuture<ResGetCurrentLocation> requestGetCurrentLocation(Duration duration) {
        return requestGetCurrentLocation(new ReqGetCurrentLocation(), duration);
    }

    @Override // api.requests.com.neusoft.common.commonsysapiinverse.ICommonSysApiInverseRequests
    public CompletableFuture<Void> fireGetCurrentLocation(ReqGetCurrentLocation reqGetCurrentLocation) {
        reqGetCurrentLocation.setResponse(null);
        String str = this.component;
        if (str != null) {
            reqGetCurrentLocation.setComponent(str);
        }
        return Communication.request(this.contextUtil.createFromClass(reqGetCurrentLocation)).thenCompose(new Function<Object, CompletableFuture<Void>>() { // from class: api.requests.com.neusoft.common.commonsysapiinverse.CommonSysApiInverseRequests.483
            @Override // java9.util.function.Function
            public CompletableFuture<Void> apply(Object obj) {
                return CommonSysApiInverseRequests.completedVoidFuture;
            }
        });
    }

    @Override // api.requests.com.neusoft.common.commonsysapiinverse.ICommonSysApiInverseRequests
    public CompletableFuture<Void> fireGetCurrentLocation(ReqGetCurrentLocation reqGetCurrentLocation, final Duration duration) {
        return fireGetCurrentLocation(reqGetCurrentLocation).thenCompose((Function<? super Void, ? extends CompletionStage<U>>) new Function<Void, CompletableFuture<Void>>() { // from class: api.requests.com.neusoft.common.commonsysapiinverse.CommonSysApiInverseRequests.484
            @Override // java9.util.function.Function
            public CompletableFuture<Void> apply(Void r2) {
                return CommonUtil.asyncSleep(duration, CommonSysApiInverseRequests.this.contextUtil.getDefaultExecutor());
            }
        });
    }

    @Override // api.requests.com.neusoft.common.commonsysapiinverse.ICommonSysApiInverseRequests
    public CompletableFuture<Void> fireGetCurrentLocation() {
        return fireGetCurrentLocation(new ReqGetCurrentLocation());
    }

    @Override // api.requests.com.neusoft.common.commonsysapiinverse.ICommonSysApiInverseRequests
    public CompletableFuture<Void> fireGetCurrentLocation(final Duration duration) {
        return fireGetCurrentLocation(new ReqGetCurrentLocation()).thenCompose((Function<? super Void, ? extends CompletionStage<U>>) new Function<Void, CompletableFuture<Void>>() { // from class: api.requests.com.neusoft.common.commonsysapiinverse.CommonSysApiInverseRequests.485
            @Override // java9.util.function.Function
            public CompletableFuture<Void> apply(Void r2) {
                return CommonUtil.asyncSleep(duration, CommonSysApiInverseRequests.this.contextUtil.getDefaultExecutor());
            }
        });
    }

    @Override // api.requests.com.neusoft.common.commonsysapiinverse.ICommonSysApiInverseRequests
    public CompletableFuture<ResGetAppLocale> requestGetAppLocale(ReqGetAppLocale reqGetAppLocale) {
        if (reqGetAppLocale.getResponse() == null) {
            reqGetAppLocale.setResponse(new ResGetAppLocale());
        }
        String str = this.component;
        if (str != null) {
            reqGetAppLocale.setComponent(str);
            reqGetAppLocale.getResponse().setComponent(this.component);
        }
        return Communication.request(this.contextUtil.createFromClass(reqGetAppLocale));
    }

    @Override // api.requests.com.neusoft.common.commonsysapiinverse.ICommonSysApiInverseRequests
    public CompletableFuture<ResGetAppLocale> requestGetAppLocale(ReqGetAppLocale reqGetAppLocale, final Duration duration) {
        return requestGetAppLocale(reqGetAppLocale).thenCompose((Function<? super ResGetAppLocale, ? extends CompletionStage<U>>) new Function<ResGetAppLocale, CompletableFuture<ResGetAppLocale>>() { // from class: api.requests.com.neusoft.common.commonsysapiinverse.CommonSysApiInverseRequests.486
            @Override // java9.util.function.Function
            public CompletableFuture<ResGetAppLocale> apply(ResGetAppLocale resGetAppLocale) {
                return CommonUtil.delayAsync(resGetAppLocale, duration, CommonSysApiInverseRequests.this.contextUtil.getDefaultExecutor());
            }
        });
    }

    @Override // api.requests.com.neusoft.common.commonsysapiinverse.ICommonSysApiInverseRequests
    public CompletableFuture<ResGetAppLocale> requestGetAppLocale() {
        return requestGetAppLocale(new ReqGetAppLocale());
    }

    @Override // api.requests.com.neusoft.common.commonsysapiinverse.ICommonSysApiInverseRequests
    public CompletableFuture<ResGetAppLocale> requestGetAppLocale(Duration duration) {
        return requestGetAppLocale(new ReqGetAppLocale(), duration);
    }

    @Override // api.requests.com.neusoft.common.commonsysapiinverse.ICommonSysApiInverseRequests
    public CompletableFuture<Void> fireGetAppLocale(ReqGetAppLocale reqGetAppLocale) {
        reqGetAppLocale.setResponse(null);
        String str = this.component;
        if (str != null) {
            reqGetAppLocale.setComponent(str);
        }
        return Communication.request(this.contextUtil.createFromClass(reqGetAppLocale)).thenCompose(new Function<Object, CompletableFuture<Void>>() { // from class: api.requests.com.neusoft.common.commonsysapiinverse.CommonSysApiInverseRequests.487
            @Override // java9.util.function.Function
            public CompletableFuture<Void> apply(Object obj) {
                return CommonSysApiInverseRequests.completedVoidFuture;
            }
        });
    }

    @Override // api.requests.com.neusoft.common.commonsysapiinverse.ICommonSysApiInverseRequests
    public CompletableFuture<Void> fireGetAppLocale(ReqGetAppLocale reqGetAppLocale, final Duration duration) {
        return fireGetAppLocale(reqGetAppLocale).thenCompose((Function<? super Void, ? extends CompletionStage<U>>) new Function<Void, CompletableFuture<Void>>() { // from class: api.requests.com.neusoft.common.commonsysapiinverse.CommonSysApiInverseRequests.488
            @Override // java9.util.function.Function
            public CompletableFuture<Void> apply(Void r2) {
                return CommonUtil.asyncSleep(duration, CommonSysApiInverseRequests.this.contextUtil.getDefaultExecutor());
            }
        });
    }

    @Override // api.requests.com.neusoft.common.commonsysapiinverse.ICommonSysApiInverseRequests
    public CompletableFuture<Void> fireGetAppLocale() {
        return fireGetAppLocale(new ReqGetAppLocale());
    }

    @Override // api.requests.com.neusoft.common.commonsysapiinverse.ICommonSysApiInverseRequests
    public CompletableFuture<Void> fireGetAppLocale(final Duration duration) {
        return fireGetAppLocale(new ReqGetAppLocale()).thenCompose((Function<? super Void, ? extends CompletionStage<U>>) new Function<Void, CompletableFuture<Void>>() { // from class: api.requests.com.neusoft.common.commonsysapiinverse.CommonSysApiInverseRequests.489
            @Override // java9.util.function.Function
            public CompletableFuture<Void> apply(Void r2) {
                return CommonUtil.asyncSleep(duration, CommonSysApiInverseRequests.this.contextUtil.getDefaultExecutor());
            }
        });
    }

    @Override // api.requests.com.neusoft.common.commonsysapiinverse.ICommonSysApiInverseRequests
    public CompletableFuture<ResGetCloseMainActivity> requestGetCloseMainActivity(ReqGetCloseMainActivity reqGetCloseMainActivity) {
        if (reqGetCloseMainActivity.getResponse() == null) {
            reqGetCloseMainActivity.setResponse(new ResGetCloseMainActivity());
        }
        String str = this.component;
        if (str != null) {
            reqGetCloseMainActivity.setComponent(str);
            reqGetCloseMainActivity.getResponse().setComponent(this.component);
        }
        return Communication.request(this.contextUtil.createFromClass(reqGetCloseMainActivity));
    }

    @Override // api.requests.com.neusoft.common.commonsysapiinverse.ICommonSysApiInverseRequests
    public CompletableFuture<ResGetCloseMainActivity> requestGetCloseMainActivity(ReqGetCloseMainActivity reqGetCloseMainActivity, final Duration duration) {
        return requestGetCloseMainActivity(reqGetCloseMainActivity).thenCompose((Function<? super ResGetCloseMainActivity, ? extends CompletionStage<U>>) new Function<ResGetCloseMainActivity, CompletableFuture<ResGetCloseMainActivity>>() { // from class: api.requests.com.neusoft.common.commonsysapiinverse.CommonSysApiInverseRequests.490
            @Override // java9.util.function.Function
            public CompletableFuture<ResGetCloseMainActivity> apply(ResGetCloseMainActivity resGetCloseMainActivity) {
                return CommonUtil.delayAsync(resGetCloseMainActivity, duration, CommonSysApiInverseRequests.this.contextUtil.getDefaultExecutor());
            }
        });
    }

    @Override // api.requests.com.neusoft.common.commonsysapiinverse.ICommonSysApiInverseRequests
    public CompletableFuture<ResGetCloseMainActivity> requestGetCloseMainActivity() {
        return requestGetCloseMainActivity(new ReqGetCloseMainActivity());
    }

    @Override // api.requests.com.neusoft.common.commonsysapiinverse.ICommonSysApiInverseRequests
    public CompletableFuture<ResGetCloseMainActivity> requestGetCloseMainActivity(Duration duration) {
        return requestGetCloseMainActivity(new ReqGetCloseMainActivity(), duration);
    }

    @Override // api.requests.com.neusoft.common.commonsysapiinverse.ICommonSysApiInverseRequests
    public CompletableFuture<Void> fireGetCloseMainActivity(ReqGetCloseMainActivity reqGetCloseMainActivity) {
        reqGetCloseMainActivity.setResponse(null);
        String str = this.component;
        if (str != null) {
            reqGetCloseMainActivity.setComponent(str);
        }
        return Communication.request(this.contextUtil.createFromClass(reqGetCloseMainActivity)).thenCompose(new Function<Object, CompletableFuture<Void>>() { // from class: api.requests.com.neusoft.common.commonsysapiinverse.CommonSysApiInverseRequests.491
            @Override // java9.util.function.Function
            public CompletableFuture<Void> apply(Object obj) {
                return CommonSysApiInverseRequests.completedVoidFuture;
            }
        });
    }

    @Override // api.requests.com.neusoft.common.commonsysapiinverse.ICommonSysApiInverseRequests
    public CompletableFuture<Void> fireGetCloseMainActivity(ReqGetCloseMainActivity reqGetCloseMainActivity, final Duration duration) {
        return fireGetCloseMainActivity(reqGetCloseMainActivity).thenCompose((Function<? super Void, ? extends CompletionStage<U>>) new Function<Void, CompletableFuture<Void>>() { // from class: api.requests.com.neusoft.common.commonsysapiinverse.CommonSysApiInverseRequests.492
            @Override // java9.util.function.Function
            public CompletableFuture<Void> apply(Void r2) {
                return CommonUtil.asyncSleep(duration, CommonSysApiInverseRequests.this.contextUtil.getDefaultExecutor());
            }
        });
    }

    @Override // api.requests.com.neusoft.common.commonsysapiinverse.ICommonSysApiInverseRequests
    public CompletableFuture<Void> fireGetCloseMainActivity() {
        return fireGetCloseMainActivity(new ReqGetCloseMainActivity());
    }

    @Override // api.requests.com.neusoft.common.commonsysapiinverse.ICommonSysApiInverseRequests
    public CompletableFuture<Void> fireGetCloseMainActivity(final Duration duration) {
        return fireGetCloseMainActivity(new ReqGetCloseMainActivity()).thenCompose((Function<? super Void, ? extends CompletionStage<U>>) new Function<Void, CompletableFuture<Void>>() { // from class: api.requests.com.neusoft.common.commonsysapiinverse.CommonSysApiInverseRequests.493
            @Override // java9.util.function.Function
            public CompletableFuture<Void> apply(Void r2) {
                return CommonUtil.asyncSleep(duration, CommonSysApiInverseRequests.this.contextUtil.getDefaultExecutor());
            }
        });
    }

    @Override // api.requests.com.neusoft.common.commonsysapiinverse.ICommonSysApiInverseRequests
    public CompletableFuture<ResGetCloseAndRestartApp> requestGetCloseAndRestartApp(ReqGetCloseAndRestartApp reqGetCloseAndRestartApp) {
        if (reqGetCloseAndRestartApp.getResponse() == null) {
            reqGetCloseAndRestartApp.setResponse(new ResGetCloseAndRestartApp());
        }
        String str = this.component;
        if (str != null) {
            reqGetCloseAndRestartApp.setComponent(str);
            reqGetCloseAndRestartApp.getResponse().setComponent(this.component);
        }
        return Communication.request(this.contextUtil.createFromClass(reqGetCloseAndRestartApp));
    }

    @Override // api.requests.com.neusoft.common.commonsysapiinverse.ICommonSysApiInverseRequests
    public CompletableFuture<ResGetCloseAndRestartApp> requestGetCloseAndRestartApp(ReqGetCloseAndRestartApp reqGetCloseAndRestartApp, final Duration duration) {
        return requestGetCloseAndRestartApp(reqGetCloseAndRestartApp).thenCompose((Function<? super ResGetCloseAndRestartApp, ? extends CompletionStage<U>>) new Function<ResGetCloseAndRestartApp, CompletableFuture<ResGetCloseAndRestartApp>>() { // from class: api.requests.com.neusoft.common.commonsysapiinverse.CommonSysApiInverseRequests.494
            @Override // java9.util.function.Function
            public CompletableFuture<ResGetCloseAndRestartApp> apply(ResGetCloseAndRestartApp resGetCloseAndRestartApp) {
                return CommonUtil.delayAsync(resGetCloseAndRestartApp, duration, CommonSysApiInverseRequests.this.contextUtil.getDefaultExecutor());
            }
        });
    }

    @Override // api.requests.com.neusoft.common.commonsysapiinverse.ICommonSysApiInverseRequests
    public CompletableFuture<ResGetCloseAndRestartApp> requestGetCloseAndRestartApp() {
        return requestGetCloseAndRestartApp(new ReqGetCloseAndRestartApp());
    }

    @Override // api.requests.com.neusoft.common.commonsysapiinverse.ICommonSysApiInverseRequests
    public CompletableFuture<ResGetCloseAndRestartApp> requestGetCloseAndRestartApp(Duration duration) {
        return requestGetCloseAndRestartApp(new ReqGetCloseAndRestartApp(), duration);
    }

    @Override // api.requests.com.neusoft.common.commonsysapiinverse.ICommonSysApiInverseRequests
    public CompletableFuture<Void> fireGetCloseAndRestartApp(ReqGetCloseAndRestartApp reqGetCloseAndRestartApp) {
        reqGetCloseAndRestartApp.setResponse(null);
        String str = this.component;
        if (str != null) {
            reqGetCloseAndRestartApp.setComponent(str);
        }
        return Communication.request(this.contextUtil.createFromClass(reqGetCloseAndRestartApp)).thenCompose(new Function<Object, CompletableFuture<Void>>() { // from class: api.requests.com.neusoft.common.commonsysapiinverse.CommonSysApiInverseRequests.495
            @Override // java9.util.function.Function
            public CompletableFuture<Void> apply(Object obj) {
                return CommonSysApiInverseRequests.completedVoidFuture;
            }
        });
    }

    @Override // api.requests.com.neusoft.common.commonsysapiinverse.ICommonSysApiInverseRequests
    public CompletableFuture<Void> fireGetCloseAndRestartApp(ReqGetCloseAndRestartApp reqGetCloseAndRestartApp, final Duration duration) {
        return fireGetCloseAndRestartApp(reqGetCloseAndRestartApp).thenCompose((Function<? super Void, ? extends CompletionStage<U>>) new Function<Void, CompletableFuture<Void>>() { // from class: api.requests.com.neusoft.common.commonsysapiinverse.CommonSysApiInverseRequests.496
            @Override // java9.util.function.Function
            public CompletableFuture<Void> apply(Void r2) {
                return CommonUtil.asyncSleep(duration, CommonSysApiInverseRequests.this.contextUtil.getDefaultExecutor());
            }
        });
    }

    @Override // api.requests.com.neusoft.common.commonsysapiinverse.ICommonSysApiInverseRequests
    public CompletableFuture<Void> fireGetCloseAndRestartApp() {
        return fireGetCloseAndRestartApp(new ReqGetCloseAndRestartApp());
    }

    @Override // api.requests.com.neusoft.common.commonsysapiinverse.ICommonSysApiInverseRequests
    public CompletableFuture<Void> fireGetCloseAndRestartApp(final Duration duration) {
        return fireGetCloseAndRestartApp(new ReqGetCloseAndRestartApp()).thenCompose((Function<? super Void, ? extends CompletionStage<U>>) new Function<Void, CompletableFuture<Void>>() { // from class: api.requests.com.neusoft.common.commonsysapiinverse.CommonSysApiInverseRequests.497
            @Override // java9.util.function.Function
            public CompletableFuture<Void> apply(Void r2) {
                return CommonUtil.asyncSleep(duration, CommonSysApiInverseRequests.this.contextUtil.getDefaultExecutor());
            }
        });
    }

    @Override // api.requests.com.neusoft.common.commonsysapiinverse.ICommonSysApiInverseRequests
    public CompletableFuture<ResGetCloseApp> requestGetCloseApp(ReqGetCloseApp reqGetCloseApp) {
        if (reqGetCloseApp.getResponse() == null) {
            reqGetCloseApp.setResponse(new ResGetCloseApp());
        }
        String str = this.component;
        if (str != null) {
            reqGetCloseApp.setComponent(str);
            reqGetCloseApp.getResponse().setComponent(this.component);
        }
        return Communication.request(this.contextUtil.createFromClass(reqGetCloseApp));
    }

    @Override // api.requests.com.neusoft.common.commonsysapiinverse.ICommonSysApiInverseRequests
    public CompletableFuture<ResGetCloseApp> requestGetCloseApp(ReqGetCloseApp reqGetCloseApp, final Duration duration) {
        return requestGetCloseApp(reqGetCloseApp).thenCompose((Function<? super ResGetCloseApp, ? extends CompletionStage<U>>) new Function<ResGetCloseApp, CompletableFuture<ResGetCloseApp>>() { // from class: api.requests.com.neusoft.common.commonsysapiinverse.CommonSysApiInverseRequests.498
            @Override // java9.util.function.Function
            public CompletableFuture<ResGetCloseApp> apply(ResGetCloseApp resGetCloseApp) {
                return CommonUtil.delayAsync(resGetCloseApp, duration, CommonSysApiInverseRequests.this.contextUtil.getDefaultExecutor());
            }
        });
    }

    @Override // api.requests.com.neusoft.common.commonsysapiinverse.ICommonSysApiInverseRequests
    public CompletableFuture<ResGetCloseApp> requestGetCloseApp() {
        return requestGetCloseApp(new ReqGetCloseApp());
    }

    @Override // api.requests.com.neusoft.common.commonsysapiinverse.ICommonSysApiInverseRequests
    public CompletableFuture<ResGetCloseApp> requestGetCloseApp(Duration duration) {
        return requestGetCloseApp(new ReqGetCloseApp(), duration);
    }

    @Override // api.requests.com.neusoft.common.commonsysapiinverse.ICommonSysApiInverseRequests
    public CompletableFuture<Void> fireGetCloseApp(ReqGetCloseApp reqGetCloseApp) {
        reqGetCloseApp.setResponse(null);
        String str = this.component;
        if (str != null) {
            reqGetCloseApp.setComponent(str);
        }
        return Communication.request(this.contextUtil.createFromClass(reqGetCloseApp)).thenCompose(new Function<Object, CompletableFuture<Void>>() { // from class: api.requests.com.neusoft.common.commonsysapiinverse.CommonSysApiInverseRequests.499
            @Override // java9.util.function.Function
            public CompletableFuture<Void> apply(Object obj) {
                return CommonSysApiInverseRequests.completedVoidFuture;
            }
        });
    }

    @Override // api.requests.com.neusoft.common.commonsysapiinverse.ICommonSysApiInverseRequests
    public CompletableFuture<Void> fireGetCloseApp(ReqGetCloseApp reqGetCloseApp, final Duration duration) {
        return fireGetCloseApp(reqGetCloseApp).thenCompose((Function<? super Void, ? extends CompletionStage<U>>) new Function<Void, CompletableFuture<Void>>() { // from class: api.requests.com.neusoft.common.commonsysapiinverse.CommonSysApiInverseRequests.500
            @Override // java9.util.function.Function
            public CompletableFuture<Void> apply(Void r2) {
                return CommonUtil.asyncSleep(duration, CommonSysApiInverseRequests.this.contextUtil.getDefaultExecutor());
            }
        });
    }

    @Override // api.requests.com.neusoft.common.commonsysapiinverse.ICommonSysApiInverseRequests
    public CompletableFuture<Void> fireGetCloseApp() {
        return fireGetCloseApp(new ReqGetCloseApp());
    }

    @Override // api.requests.com.neusoft.common.commonsysapiinverse.ICommonSysApiInverseRequests
    public CompletableFuture<Void> fireGetCloseApp(final Duration duration) {
        return fireGetCloseApp(new ReqGetCloseApp()).thenCompose((Function<? super Void, ? extends CompletionStage<U>>) new Function<Void, CompletableFuture<Void>>() { // from class: api.requests.com.neusoft.common.commonsysapiinverse.CommonSysApiInverseRequests.501
            @Override // java9.util.function.Function
            public CompletableFuture<Void> apply(Void r2) {
                return CommonUtil.asyncSleep(duration, CommonSysApiInverseRequests.this.contextUtil.getDefaultExecutor());
            }
        });
    }

    @Override // api.requests.com.neusoft.common.commonsysapiinverse.ICommonSysApiInverseRequests
    public CompletableFuture<ResGetShowDiagnoseSetting> requestGetShowDiagnoseSetting(ReqGetShowDiagnoseSetting reqGetShowDiagnoseSetting) {
        if (reqGetShowDiagnoseSetting.getResponse() == null) {
            reqGetShowDiagnoseSetting.setResponse(new ResGetShowDiagnoseSetting());
        }
        String str = this.component;
        if (str != null) {
            reqGetShowDiagnoseSetting.setComponent(str);
            reqGetShowDiagnoseSetting.getResponse().setComponent(this.component);
        }
        return Communication.request(this.contextUtil.createFromClass(reqGetShowDiagnoseSetting));
    }

    @Override // api.requests.com.neusoft.common.commonsysapiinverse.ICommonSysApiInverseRequests
    public CompletableFuture<ResGetShowDiagnoseSetting> requestGetShowDiagnoseSetting(ReqGetShowDiagnoseSetting reqGetShowDiagnoseSetting, final Duration duration) {
        return requestGetShowDiagnoseSetting(reqGetShowDiagnoseSetting).thenCompose((Function<? super ResGetShowDiagnoseSetting, ? extends CompletionStage<U>>) new Function<ResGetShowDiagnoseSetting, CompletableFuture<ResGetShowDiagnoseSetting>>() { // from class: api.requests.com.neusoft.common.commonsysapiinverse.CommonSysApiInverseRequests.502
            @Override // java9.util.function.Function
            public CompletableFuture<ResGetShowDiagnoseSetting> apply(ResGetShowDiagnoseSetting resGetShowDiagnoseSetting) {
                return CommonUtil.delayAsync(resGetShowDiagnoseSetting, duration, CommonSysApiInverseRequests.this.contextUtil.getDefaultExecutor());
            }
        });
    }

    @Override // api.requests.com.neusoft.common.commonsysapiinverse.ICommonSysApiInverseRequests
    public CompletableFuture<ResGetShowDiagnoseSetting> requestGetShowDiagnoseSetting() {
        return requestGetShowDiagnoseSetting(new ReqGetShowDiagnoseSetting());
    }

    @Override // api.requests.com.neusoft.common.commonsysapiinverse.ICommonSysApiInverseRequests
    public CompletableFuture<ResGetShowDiagnoseSetting> requestGetShowDiagnoseSetting(Duration duration) {
        return requestGetShowDiagnoseSetting(new ReqGetShowDiagnoseSetting(), duration);
    }

    @Override // api.requests.com.neusoft.common.commonsysapiinverse.ICommonSysApiInverseRequests
    public CompletableFuture<Void> fireGetShowDiagnoseSetting(ReqGetShowDiagnoseSetting reqGetShowDiagnoseSetting) {
        reqGetShowDiagnoseSetting.setResponse(null);
        String str = this.component;
        if (str != null) {
            reqGetShowDiagnoseSetting.setComponent(str);
        }
        return Communication.request(this.contextUtil.createFromClass(reqGetShowDiagnoseSetting)).thenCompose(new Function<Object, CompletableFuture<Void>>() { // from class: api.requests.com.neusoft.common.commonsysapiinverse.CommonSysApiInverseRequests.503
            @Override // java9.util.function.Function
            public CompletableFuture<Void> apply(Object obj) {
                return CommonSysApiInverseRequests.completedVoidFuture;
            }
        });
    }

    @Override // api.requests.com.neusoft.common.commonsysapiinverse.ICommonSysApiInverseRequests
    public CompletableFuture<Void> fireGetShowDiagnoseSetting(ReqGetShowDiagnoseSetting reqGetShowDiagnoseSetting, final Duration duration) {
        return fireGetShowDiagnoseSetting(reqGetShowDiagnoseSetting).thenCompose((Function<? super Void, ? extends CompletionStage<U>>) new Function<Void, CompletableFuture<Void>>() { // from class: api.requests.com.neusoft.common.commonsysapiinverse.CommonSysApiInverseRequests.504
            @Override // java9.util.function.Function
            public CompletableFuture<Void> apply(Void r2) {
                return CommonUtil.asyncSleep(duration, CommonSysApiInverseRequests.this.contextUtil.getDefaultExecutor());
            }
        });
    }

    @Override // api.requests.com.neusoft.common.commonsysapiinverse.ICommonSysApiInverseRequests
    public CompletableFuture<Void> fireGetShowDiagnoseSetting() {
        return fireGetShowDiagnoseSetting(new ReqGetShowDiagnoseSetting());
    }

    @Override // api.requests.com.neusoft.common.commonsysapiinverse.ICommonSysApiInverseRequests
    public CompletableFuture<Void> fireGetShowDiagnoseSetting(final Duration duration) {
        return fireGetShowDiagnoseSetting(new ReqGetShowDiagnoseSetting()).thenCompose((Function<? super Void, ? extends CompletionStage<U>>) new Function<Void, CompletableFuture<Void>>() { // from class: api.requests.com.neusoft.common.commonsysapiinverse.CommonSysApiInverseRequests.505
            @Override // java9.util.function.Function
            public CompletableFuture<Void> apply(Void r2) {
                return CommonUtil.asyncSleep(duration, CommonSysApiInverseRequests.this.contextUtil.getDefaultExecutor());
            }
        });
    }

    @Override // api.requests.com.neusoft.common.commonsysapiinverse.ICommonSysApiInverseRequests
    public CompletableFuture<ResGetSwitchNaviToBackground> requestGetSwitchNaviToBackground(ReqGetSwitchNaviToBackground reqGetSwitchNaviToBackground) {
        if (reqGetSwitchNaviToBackground.getResponse() == null) {
            reqGetSwitchNaviToBackground.setResponse(new ResGetSwitchNaviToBackground());
        }
        String str = this.component;
        if (str != null) {
            reqGetSwitchNaviToBackground.setComponent(str);
            reqGetSwitchNaviToBackground.getResponse().setComponent(this.component);
        }
        return Communication.request(this.contextUtil.createFromClass(reqGetSwitchNaviToBackground));
    }

    @Override // api.requests.com.neusoft.common.commonsysapiinverse.ICommonSysApiInverseRequests
    public CompletableFuture<ResGetSwitchNaviToBackground> requestGetSwitchNaviToBackground(ReqGetSwitchNaviToBackground reqGetSwitchNaviToBackground, final Duration duration) {
        return requestGetSwitchNaviToBackground(reqGetSwitchNaviToBackground).thenCompose((Function<? super ResGetSwitchNaviToBackground, ? extends CompletionStage<U>>) new Function<ResGetSwitchNaviToBackground, CompletableFuture<ResGetSwitchNaviToBackground>>() { // from class: api.requests.com.neusoft.common.commonsysapiinverse.CommonSysApiInverseRequests.506
            @Override // java9.util.function.Function
            public CompletableFuture<ResGetSwitchNaviToBackground> apply(ResGetSwitchNaviToBackground resGetSwitchNaviToBackground) {
                return CommonUtil.delayAsync(resGetSwitchNaviToBackground, duration, CommonSysApiInverseRequests.this.contextUtil.getDefaultExecutor());
            }
        });
    }

    @Override // api.requests.com.neusoft.common.commonsysapiinverse.ICommonSysApiInverseRequests
    public CompletableFuture<ResGetSwitchNaviToBackground> requestGetSwitchNaviToBackground() {
        return requestGetSwitchNaviToBackground(new ReqGetSwitchNaviToBackground());
    }

    @Override // api.requests.com.neusoft.common.commonsysapiinverse.ICommonSysApiInverseRequests
    public CompletableFuture<ResGetSwitchNaviToBackground> requestGetSwitchNaviToBackground(Duration duration) {
        return requestGetSwitchNaviToBackground(new ReqGetSwitchNaviToBackground(), duration);
    }

    @Override // api.requests.com.neusoft.common.commonsysapiinverse.ICommonSysApiInverseRequests
    public CompletableFuture<Void> fireGetSwitchNaviToBackground(ReqGetSwitchNaviToBackground reqGetSwitchNaviToBackground) {
        reqGetSwitchNaviToBackground.setResponse(null);
        String str = this.component;
        if (str != null) {
            reqGetSwitchNaviToBackground.setComponent(str);
        }
        return Communication.request(this.contextUtil.createFromClass(reqGetSwitchNaviToBackground)).thenCompose(new Function<Object, CompletableFuture<Void>>() { // from class: api.requests.com.neusoft.common.commonsysapiinverse.CommonSysApiInverseRequests.507
            @Override // java9.util.function.Function
            public CompletableFuture<Void> apply(Object obj) {
                return CommonSysApiInverseRequests.completedVoidFuture;
            }
        });
    }

    @Override // api.requests.com.neusoft.common.commonsysapiinverse.ICommonSysApiInverseRequests
    public CompletableFuture<Void> fireGetSwitchNaviToBackground(ReqGetSwitchNaviToBackground reqGetSwitchNaviToBackground, final Duration duration) {
        return fireGetSwitchNaviToBackground(reqGetSwitchNaviToBackground).thenCompose((Function<? super Void, ? extends CompletionStage<U>>) new Function<Void, CompletableFuture<Void>>() { // from class: api.requests.com.neusoft.common.commonsysapiinverse.CommonSysApiInverseRequests.508
            @Override // java9.util.function.Function
            public CompletableFuture<Void> apply(Void r2) {
                return CommonUtil.asyncSleep(duration, CommonSysApiInverseRequests.this.contextUtil.getDefaultExecutor());
            }
        });
    }

    @Override // api.requests.com.neusoft.common.commonsysapiinverse.ICommonSysApiInverseRequests
    public CompletableFuture<Void> fireGetSwitchNaviToBackground() {
        return fireGetSwitchNaviToBackground(new ReqGetSwitchNaviToBackground());
    }

    @Override // api.requests.com.neusoft.common.commonsysapiinverse.ICommonSysApiInverseRequests
    public CompletableFuture<Void> fireGetSwitchNaviToBackground(final Duration duration) {
        return fireGetSwitchNaviToBackground(new ReqGetSwitchNaviToBackground()).thenCompose((Function<? super Void, ? extends CompletionStage<U>>) new Function<Void, CompletableFuture<Void>>() { // from class: api.requests.com.neusoft.common.commonsysapiinverse.CommonSysApiInverseRequests.509
            @Override // java9.util.function.Function
            public CompletableFuture<Void> apply(Void r2) {
                return CommonUtil.asyncSleep(duration, CommonSysApiInverseRequests.this.contextUtil.getDefaultExecutor());
            }
        });
    }

    @Override // api.requests.com.neusoft.common.commonsysapiinverse.ICommonSysApiInverseRequests
    public CompletableFuture<ResGetImmersionStatus> requestGetImmersionStatus(ReqGetImmersionStatus reqGetImmersionStatus) {
        if (reqGetImmersionStatus.getResponse() == null) {
            reqGetImmersionStatus.setResponse(new ResGetImmersionStatus());
        }
        String str = this.component;
        if (str != null) {
            reqGetImmersionStatus.setComponent(str);
            reqGetImmersionStatus.getResponse().setComponent(this.component);
        }
        return Communication.request(this.contextUtil.createFromClass(reqGetImmersionStatus));
    }

    @Override // api.requests.com.neusoft.common.commonsysapiinverse.ICommonSysApiInverseRequests
    public CompletableFuture<ResGetImmersionStatus> requestGetImmersionStatus(ReqGetImmersionStatus reqGetImmersionStatus, final Duration duration) {
        return requestGetImmersionStatus(reqGetImmersionStatus).thenCompose((Function<? super ResGetImmersionStatus, ? extends CompletionStage<U>>) new Function<ResGetImmersionStatus, CompletableFuture<ResGetImmersionStatus>>() { // from class: api.requests.com.neusoft.common.commonsysapiinverse.CommonSysApiInverseRequests.510
            @Override // java9.util.function.Function
            public CompletableFuture<ResGetImmersionStatus> apply(ResGetImmersionStatus resGetImmersionStatus) {
                return CommonUtil.delayAsync(resGetImmersionStatus, duration, CommonSysApiInverseRequests.this.contextUtil.getDefaultExecutor());
            }
        });
    }

    @Override // api.requests.com.neusoft.common.commonsysapiinverse.ICommonSysApiInverseRequests
    public CompletableFuture<ResGetImmersionStatus> requestGetImmersionStatus() {
        return requestGetImmersionStatus(new ReqGetImmersionStatus());
    }

    @Override // api.requests.com.neusoft.common.commonsysapiinverse.ICommonSysApiInverseRequests
    public CompletableFuture<ResGetImmersionStatus> requestGetImmersionStatus(Duration duration) {
        return requestGetImmersionStatus(new ReqGetImmersionStatus(), duration);
    }

    @Override // api.requests.com.neusoft.common.commonsysapiinverse.ICommonSysApiInverseRequests
    public CompletableFuture<Void> fireGetImmersionStatus(ReqGetImmersionStatus reqGetImmersionStatus) {
        reqGetImmersionStatus.setResponse(null);
        String str = this.component;
        if (str != null) {
            reqGetImmersionStatus.setComponent(str);
        }
        return Communication.request(this.contextUtil.createFromClass(reqGetImmersionStatus)).thenCompose(new Function<Object, CompletableFuture<Void>>() { // from class: api.requests.com.neusoft.common.commonsysapiinverse.CommonSysApiInverseRequests.511
            @Override // java9.util.function.Function
            public CompletableFuture<Void> apply(Object obj) {
                return CommonSysApiInverseRequests.completedVoidFuture;
            }
        });
    }

    @Override // api.requests.com.neusoft.common.commonsysapiinverse.ICommonSysApiInverseRequests
    public CompletableFuture<Void> fireGetImmersionStatus(ReqGetImmersionStatus reqGetImmersionStatus, final Duration duration) {
        return fireGetImmersionStatus(reqGetImmersionStatus).thenCompose((Function<? super Void, ? extends CompletionStage<U>>) new Function<Void, CompletableFuture<Void>>() { // from class: api.requests.com.neusoft.common.commonsysapiinverse.CommonSysApiInverseRequests.512
            @Override // java9.util.function.Function
            public CompletableFuture<Void> apply(Void r2) {
                return CommonUtil.asyncSleep(duration, CommonSysApiInverseRequests.this.contextUtil.getDefaultExecutor());
            }
        });
    }

    @Override // api.requests.com.neusoft.common.commonsysapiinverse.ICommonSysApiInverseRequests
    public CompletableFuture<Void> fireGetImmersionStatus() {
        return fireGetImmersionStatus(new ReqGetImmersionStatus());
    }

    @Override // api.requests.com.neusoft.common.commonsysapiinverse.ICommonSysApiInverseRequests
    public CompletableFuture<Void> fireGetImmersionStatus(final Duration duration) {
        return fireGetImmersionStatus(new ReqGetImmersionStatus()).thenCompose((Function<? super Void, ? extends CompletionStage<U>>) new Function<Void, CompletableFuture<Void>>() { // from class: api.requests.com.neusoft.common.commonsysapiinverse.CommonSysApiInverseRequests.513
            @Override // java9.util.function.Function
            public CompletableFuture<Void> apply(Void r2) {
                return CommonUtil.asyncSleep(duration, CommonSysApiInverseRequests.this.contextUtil.getDefaultExecutor());
            }
        });
    }

    @Override // api.requests.com.neusoft.common.commonsysapiinverse.ICommonSysApiInverseRequests
    public CompletableFuture<ResGetDeleteAllDBData> requestGetDeleteAllDBData(ReqGetDeleteAllDBData reqGetDeleteAllDBData) {
        if (reqGetDeleteAllDBData.getResponse() == null) {
            reqGetDeleteAllDBData.setResponse(new ResGetDeleteAllDBData());
        }
        String str = this.component;
        if (str != null) {
            reqGetDeleteAllDBData.setComponent(str);
            reqGetDeleteAllDBData.getResponse().setComponent(this.component);
        }
        return Communication.request(this.contextUtil.createFromClass(reqGetDeleteAllDBData));
    }

    @Override // api.requests.com.neusoft.common.commonsysapiinverse.ICommonSysApiInverseRequests
    public CompletableFuture<ResGetDeleteAllDBData> requestGetDeleteAllDBData(ReqGetDeleteAllDBData reqGetDeleteAllDBData, final Duration duration) {
        return requestGetDeleteAllDBData(reqGetDeleteAllDBData).thenCompose((Function<? super ResGetDeleteAllDBData, ? extends CompletionStage<U>>) new Function<ResGetDeleteAllDBData, CompletableFuture<ResGetDeleteAllDBData>>() { // from class: api.requests.com.neusoft.common.commonsysapiinverse.CommonSysApiInverseRequests.514
            @Override // java9.util.function.Function
            public CompletableFuture<ResGetDeleteAllDBData> apply(ResGetDeleteAllDBData resGetDeleteAllDBData) {
                return CommonUtil.delayAsync(resGetDeleteAllDBData, duration, CommonSysApiInverseRequests.this.contextUtil.getDefaultExecutor());
            }
        });
    }

    @Override // api.requests.com.neusoft.common.commonsysapiinverse.ICommonSysApiInverseRequests
    public CompletableFuture<ResGetDeleteAllDBData> requestGetDeleteAllDBData() {
        return requestGetDeleteAllDBData(new ReqGetDeleteAllDBData());
    }

    @Override // api.requests.com.neusoft.common.commonsysapiinverse.ICommonSysApiInverseRequests
    public CompletableFuture<ResGetDeleteAllDBData> requestGetDeleteAllDBData(Duration duration) {
        return requestGetDeleteAllDBData(new ReqGetDeleteAllDBData(), duration);
    }

    @Override // api.requests.com.neusoft.common.commonsysapiinverse.ICommonSysApiInverseRequests
    public CompletableFuture<Void> fireGetDeleteAllDBData(ReqGetDeleteAllDBData reqGetDeleteAllDBData) {
        reqGetDeleteAllDBData.setResponse(null);
        String str = this.component;
        if (str != null) {
            reqGetDeleteAllDBData.setComponent(str);
        }
        return Communication.request(this.contextUtil.createFromClass(reqGetDeleteAllDBData)).thenCompose(new Function<Object, CompletableFuture<Void>>() { // from class: api.requests.com.neusoft.common.commonsysapiinverse.CommonSysApiInverseRequests.515
            @Override // java9.util.function.Function
            public CompletableFuture<Void> apply(Object obj) {
                return CommonSysApiInverseRequests.completedVoidFuture;
            }
        });
    }

    @Override // api.requests.com.neusoft.common.commonsysapiinverse.ICommonSysApiInverseRequests
    public CompletableFuture<Void> fireGetDeleteAllDBData(ReqGetDeleteAllDBData reqGetDeleteAllDBData, final Duration duration) {
        return fireGetDeleteAllDBData(reqGetDeleteAllDBData).thenCompose((Function<? super Void, ? extends CompletionStage<U>>) new Function<Void, CompletableFuture<Void>>() { // from class: api.requests.com.neusoft.common.commonsysapiinverse.CommonSysApiInverseRequests.516
            @Override // java9.util.function.Function
            public CompletableFuture<Void> apply(Void r2) {
                return CommonUtil.asyncSleep(duration, CommonSysApiInverseRequests.this.contextUtil.getDefaultExecutor());
            }
        });
    }

    @Override // api.requests.com.neusoft.common.commonsysapiinverse.ICommonSysApiInverseRequests
    public CompletableFuture<Void> fireGetDeleteAllDBData() {
        return fireGetDeleteAllDBData(new ReqGetDeleteAllDBData());
    }

    @Override // api.requests.com.neusoft.common.commonsysapiinverse.ICommonSysApiInverseRequests
    public CompletableFuture<Void> fireGetDeleteAllDBData(final Duration duration) {
        return fireGetDeleteAllDBData(new ReqGetDeleteAllDBData()).thenCompose((Function<? super Void, ? extends CompletionStage<U>>) new Function<Void, CompletableFuture<Void>>() { // from class: api.requests.com.neusoft.common.commonsysapiinverse.CommonSysApiInverseRequests.517
            @Override // java9.util.function.Function
            public CompletableFuture<Void> apply(Void r2) {
                return CommonUtil.asyncSleep(duration, CommonSysApiInverseRequests.this.contextUtil.getDefaultExecutor());
            }
        });
    }

    @Override // api.requests.com.neusoft.common.commonsysapiinverse.ICommonSysApiInverseRequests
    public CompletableFuture<ResGetDBDataIntegrityCheck> requestGetDBDataIntegrityCheck(ReqGetDBDataIntegrityCheck reqGetDBDataIntegrityCheck) {
        if (reqGetDBDataIntegrityCheck.getResponse() == null) {
            reqGetDBDataIntegrityCheck.setResponse(new ResGetDBDataIntegrityCheck());
        }
        String str = this.component;
        if (str != null) {
            reqGetDBDataIntegrityCheck.setComponent(str);
            reqGetDBDataIntegrityCheck.getResponse().setComponent(this.component);
        }
        return Communication.request(this.contextUtil.createFromClass(reqGetDBDataIntegrityCheck));
    }

    @Override // api.requests.com.neusoft.common.commonsysapiinverse.ICommonSysApiInverseRequests
    public CompletableFuture<ResGetDBDataIntegrityCheck> requestGetDBDataIntegrityCheck(ReqGetDBDataIntegrityCheck reqGetDBDataIntegrityCheck, final Duration duration) {
        return requestGetDBDataIntegrityCheck(reqGetDBDataIntegrityCheck).thenCompose((Function<? super ResGetDBDataIntegrityCheck, ? extends CompletionStage<U>>) new Function<ResGetDBDataIntegrityCheck, CompletableFuture<ResGetDBDataIntegrityCheck>>() { // from class: api.requests.com.neusoft.common.commonsysapiinverse.CommonSysApiInverseRequests.518
            @Override // java9.util.function.Function
            public CompletableFuture<ResGetDBDataIntegrityCheck> apply(ResGetDBDataIntegrityCheck resGetDBDataIntegrityCheck) {
                return CommonUtil.delayAsync(resGetDBDataIntegrityCheck, duration, CommonSysApiInverseRequests.this.contextUtil.getDefaultExecutor());
            }
        });
    }

    @Override // api.requests.com.neusoft.common.commonsysapiinverse.ICommonSysApiInverseRequests
    public CompletableFuture<ResGetDBDataIntegrityCheck> requestGetDBDataIntegrityCheck() {
        return requestGetDBDataIntegrityCheck(new ReqGetDBDataIntegrityCheck());
    }

    @Override // api.requests.com.neusoft.common.commonsysapiinverse.ICommonSysApiInverseRequests
    public CompletableFuture<ResGetDBDataIntegrityCheck> requestGetDBDataIntegrityCheck(Duration duration) {
        return requestGetDBDataIntegrityCheck(new ReqGetDBDataIntegrityCheck(), duration);
    }

    @Override // api.requests.com.neusoft.common.commonsysapiinverse.ICommonSysApiInverseRequests
    public CompletableFuture<Void> fireGetDBDataIntegrityCheck(ReqGetDBDataIntegrityCheck reqGetDBDataIntegrityCheck) {
        reqGetDBDataIntegrityCheck.setResponse(null);
        String str = this.component;
        if (str != null) {
            reqGetDBDataIntegrityCheck.setComponent(str);
        }
        return Communication.request(this.contextUtil.createFromClass(reqGetDBDataIntegrityCheck)).thenCompose(new Function<Object, CompletableFuture<Void>>() { // from class: api.requests.com.neusoft.common.commonsysapiinverse.CommonSysApiInverseRequests.519
            @Override // java9.util.function.Function
            public CompletableFuture<Void> apply(Object obj) {
                return CommonSysApiInverseRequests.completedVoidFuture;
            }
        });
    }

    @Override // api.requests.com.neusoft.common.commonsysapiinverse.ICommonSysApiInverseRequests
    public CompletableFuture<Void> fireGetDBDataIntegrityCheck(ReqGetDBDataIntegrityCheck reqGetDBDataIntegrityCheck, final Duration duration) {
        return fireGetDBDataIntegrityCheck(reqGetDBDataIntegrityCheck).thenCompose((Function<? super Void, ? extends CompletionStage<U>>) new Function<Void, CompletableFuture<Void>>() { // from class: api.requests.com.neusoft.common.commonsysapiinverse.CommonSysApiInverseRequests.520
            @Override // java9.util.function.Function
            public CompletableFuture<Void> apply(Void r2) {
                return CommonUtil.asyncSleep(duration, CommonSysApiInverseRequests.this.contextUtil.getDefaultExecutor());
            }
        });
    }

    @Override // api.requests.com.neusoft.common.commonsysapiinverse.ICommonSysApiInverseRequests
    public CompletableFuture<Void> fireGetDBDataIntegrityCheck() {
        return fireGetDBDataIntegrityCheck(new ReqGetDBDataIntegrityCheck());
    }

    @Override // api.requests.com.neusoft.common.commonsysapiinverse.ICommonSysApiInverseRequests
    public CompletableFuture<Void> fireGetDBDataIntegrityCheck(final Duration duration) {
        return fireGetDBDataIntegrityCheck(new ReqGetDBDataIntegrityCheck()).thenCompose((Function<? super Void, ? extends CompletionStage<U>>) new Function<Void, CompletableFuture<Void>>() { // from class: api.requests.com.neusoft.common.commonsysapiinverse.CommonSysApiInverseRequests.521
            @Override // java9.util.function.Function
            public CompletableFuture<Void> apply(Void r2) {
                return CommonUtil.asyncSleep(duration, CommonSysApiInverseRequests.this.contextUtil.getDefaultExecutor());
            }
        });
    }

    @Override // api.requests.com.neusoft.common.commonsysapiinverse.ICommonSysApiInverseRequests
    public CompletableFuture<ResGetNaviPresentationInfo> requestGetNaviPresentationInfo(ReqGetNaviPresentationInfo reqGetNaviPresentationInfo) {
        if (reqGetNaviPresentationInfo.getResponse() == null) {
            reqGetNaviPresentationInfo.setResponse(new ResGetNaviPresentationInfo());
        }
        String str = this.component;
        if (str != null) {
            reqGetNaviPresentationInfo.setComponent(str);
            reqGetNaviPresentationInfo.getResponse().setComponent(this.component);
        }
        return Communication.request(this.contextUtil.createFromClass(reqGetNaviPresentationInfo));
    }

    @Override // api.requests.com.neusoft.common.commonsysapiinverse.ICommonSysApiInverseRequests
    public CompletableFuture<ResGetNaviPresentationInfo> requestGetNaviPresentationInfo(ReqGetNaviPresentationInfo reqGetNaviPresentationInfo, final Duration duration) {
        return requestGetNaviPresentationInfo(reqGetNaviPresentationInfo).thenCompose((Function<? super ResGetNaviPresentationInfo, ? extends CompletionStage<U>>) new Function<ResGetNaviPresentationInfo, CompletableFuture<ResGetNaviPresentationInfo>>() { // from class: api.requests.com.neusoft.common.commonsysapiinverse.CommonSysApiInverseRequests.522
            @Override // java9.util.function.Function
            public CompletableFuture<ResGetNaviPresentationInfo> apply(ResGetNaviPresentationInfo resGetNaviPresentationInfo) {
                return CommonUtil.delayAsync(resGetNaviPresentationInfo, duration, CommonSysApiInverseRequests.this.contextUtil.getDefaultExecutor());
            }
        });
    }

    @Override // api.requests.com.neusoft.common.commonsysapiinverse.ICommonSysApiInverseRequests
    public CompletableFuture<ResGetNaviPresentationInfo> requestGetNaviPresentationInfo() {
        return requestGetNaviPresentationInfo(new ReqGetNaviPresentationInfo());
    }

    @Override // api.requests.com.neusoft.common.commonsysapiinverse.ICommonSysApiInverseRequests
    public CompletableFuture<ResGetNaviPresentationInfo> requestGetNaviPresentationInfo(Duration duration) {
        return requestGetNaviPresentationInfo(new ReqGetNaviPresentationInfo(), duration);
    }

    @Override // api.requests.com.neusoft.common.commonsysapiinverse.ICommonSysApiInverseRequests
    public CompletableFuture<Void> fireGetNaviPresentationInfo(ReqGetNaviPresentationInfo reqGetNaviPresentationInfo) {
        reqGetNaviPresentationInfo.setResponse(null);
        String str = this.component;
        if (str != null) {
            reqGetNaviPresentationInfo.setComponent(str);
        }
        return Communication.request(this.contextUtil.createFromClass(reqGetNaviPresentationInfo)).thenCompose(new Function<Object, CompletableFuture<Void>>() { // from class: api.requests.com.neusoft.common.commonsysapiinverse.CommonSysApiInverseRequests.523
            @Override // java9.util.function.Function
            public CompletableFuture<Void> apply(Object obj) {
                return CommonSysApiInverseRequests.completedVoidFuture;
            }
        });
    }

    @Override // api.requests.com.neusoft.common.commonsysapiinverse.ICommonSysApiInverseRequests
    public CompletableFuture<Void> fireGetNaviPresentationInfo(ReqGetNaviPresentationInfo reqGetNaviPresentationInfo, final Duration duration) {
        return fireGetNaviPresentationInfo(reqGetNaviPresentationInfo).thenCompose((Function<? super Void, ? extends CompletionStage<U>>) new Function<Void, CompletableFuture<Void>>() { // from class: api.requests.com.neusoft.common.commonsysapiinverse.CommonSysApiInverseRequests.524
            @Override // java9.util.function.Function
            public CompletableFuture<Void> apply(Void r2) {
                return CommonUtil.asyncSleep(duration, CommonSysApiInverseRequests.this.contextUtil.getDefaultExecutor());
            }
        });
    }

    @Override // api.requests.com.neusoft.common.commonsysapiinverse.ICommonSysApiInverseRequests
    public CompletableFuture<Void> fireGetNaviPresentationInfo() {
        return fireGetNaviPresentationInfo(new ReqGetNaviPresentationInfo());
    }

    @Override // api.requests.com.neusoft.common.commonsysapiinverse.ICommonSysApiInverseRequests
    public CompletableFuture<Void> fireGetNaviPresentationInfo(final Duration duration) {
        return fireGetNaviPresentationInfo(new ReqGetNaviPresentationInfo()).thenCompose((Function<? super Void, ? extends CompletionStage<U>>) new Function<Void, CompletableFuture<Void>>() { // from class: api.requests.com.neusoft.common.commonsysapiinverse.CommonSysApiInverseRequests.525
            @Override // java9.util.function.Function
            public CompletableFuture<Void> apply(Void r2) {
                return CommonUtil.asyncSleep(duration, CommonSysApiInverseRequests.this.contextUtil.getDefaultExecutor());
            }
        });
    }

    @Override // api.requests.com.neusoft.common.commonsysapiinverse.ICommonSysApiInverseRequests
    public CompletableFuture<ResGetExtractFiles> requestGetExtractFiles(ReqGetExtractFiles reqGetExtractFiles) {
        if (reqGetExtractFiles.getResponse() == null) {
            reqGetExtractFiles.setResponse(new ResGetExtractFiles());
        }
        String str = this.component;
        if (str != null) {
            reqGetExtractFiles.setComponent(str);
            reqGetExtractFiles.getResponse().setComponent(this.component);
        }
        return Communication.request(this.contextUtil.createFromClass(reqGetExtractFiles));
    }

    @Override // api.requests.com.neusoft.common.commonsysapiinverse.ICommonSysApiInverseRequests
    public CompletableFuture<ResGetExtractFiles> requestGetExtractFiles(ReqGetExtractFiles reqGetExtractFiles, final Duration duration) {
        return requestGetExtractFiles(reqGetExtractFiles).thenCompose((Function<? super ResGetExtractFiles, ? extends CompletionStage<U>>) new Function<ResGetExtractFiles, CompletableFuture<ResGetExtractFiles>>() { // from class: api.requests.com.neusoft.common.commonsysapiinverse.CommonSysApiInverseRequests.526
            @Override // java9.util.function.Function
            public CompletableFuture<ResGetExtractFiles> apply(ResGetExtractFiles resGetExtractFiles) {
                return CommonUtil.delayAsync(resGetExtractFiles, duration, CommonSysApiInverseRequests.this.contextUtil.getDefaultExecutor());
            }
        });
    }

    @Override // api.requests.com.neusoft.common.commonsysapiinverse.ICommonSysApiInverseRequests
    public CompletableFuture<ResGetExtractFiles> requestGetExtractFiles() {
        return requestGetExtractFiles(new ReqGetExtractFiles());
    }

    @Override // api.requests.com.neusoft.common.commonsysapiinverse.ICommonSysApiInverseRequests
    public CompletableFuture<ResGetExtractFiles> requestGetExtractFiles(Duration duration) {
        return requestGetExtractFiles(new ReqGetExtractFiles(), duration);
    }

    @Override // api.requests.com.neusoft.common.commonsysapiinverse.ICommonSysApiInverseRequests
    public CompletableFuture<Void> fireGetExtractFiles(ReqGetExtractFiles reqGetExtractFiles) {
        reqGetExtractFiles.setResponse(null);
        String str = this.component;
        if (str != null) {
            reqGetExtractFiles.setComponent(str);
        }
        return Communication.request(this.contextUtil.createFromClass(reqGetExtractFiles)).thenCompose(new Function<Object, CompletableFuture<Void>>() { // from class: api.requests.com.neusoft.common.commonsysapiinverse.CommonSysApiInverseRequests.527
            @Override // java9.util.function.Function
            public CompletableFuture<Void> apply(Object obj) {
                return CommonSysApiInverseRequests.completedVoidFuture;
            }
        });
    }

    @Override // api.requests.com.neusoft.common.commonsysapiinverse.ICommonSysApiInverseRequests
    public CompletableFuture<Void> fireGetExtractFiles(ReqGetExtractFiles reqGetExtractFiles, final Duration duration) {
        return fireGetExtractFiles(reqGetExtractFiles).thenCompose((Function<? super Void, ? extends CompletionStage<U>>) new Function<Void, CompletableFuture<Void>>() { // from class: api.requests.com.neusoft.common.commonsysapiinverse.CommonSysApiInverseRequests.528
            @Override // java9.util.function.Function
            public CompletableFuture<Void> apply(Void r2) {
                return CommonUtil.asyncSleep(duration, CommonSysApiInverseRequests.this.contextUtil.getDefaultExecutor());
            }
        });
    }

    @Override // api.requests.com.neusoft.common.commonsysapiinverse.ICommonSysApiInverseRequests
    public CompletableFuture<Void> fireGetExtractFiles() {
        return fireGetExtractFiles(new ReqGetExtractFiles());
    }

    @Override // api.requests.com.neusoft.common.commonsysapiinverse.ICommonSysApiInverseRequests
    public CompletableFuture<Void> fireGetExtractFiles(final Duration duration) {
        return fireGetExtractFiles(new ReqGetExtractFiles()).thenCompose((Function<? super Void, ? extends CompletionStage<U>>) new Function<Void, CompletableFuture<Void>>() { // from class: api.requests.com.neusoft.common.commonsysapiinverse.CommonSysApiInverseRequests.529
            @Override // java9.util.function.Function
            public CompletableFuture<Void> apply(Void r2) {
                return CommonUtil.asyncSleep(duration, CommonSysApiInverseRequests.this.contextUtil.getDefaultExecutor());
            }
        });
    }

    @Override // api.requests.com.neusoft.common.commonsysapiinverse.ICommonSysApiInverseRequests
    public CompletableFuture<ResGetCopyFiles> requestGetCopyFiles(ReqGetCopyFiles reqGetCopyFiles) {
        if (reqGetCopyFiles.getResponse() == null) {
            reqGetCopyFiles.setResponse(new ResGetCopyFiles());
        }
        String str = this.component;
        if (str != null) {
            reqGetCopyFiles.setComponent(str);
            reqGetCopyFiles.getResponse().setComponent(this.component);
        }
        return Communication.request(this.contextUtil.createFromClass(reqGetCopyFiles));
    }

    @Override // api.requests.com.neusoft.common.commonsysapiinverse.ICommonSysApiInverseRequests
    public CompletableFuture<ResGetCopyFiles> requestGetCopyFiles(ReqGetCopyFiles reqGetCopyFiles, final Duration duration) {
        return requestGetCopyFiles(reqGetCopyFiles).thenCompose((Function<? super ResGetCopyFiles, ? extends CompletionStage<U>>) new Function<ResGetCopyFiles, CompletableFuture<ResGetCopyFiles>>() { // from class: api.requests.com.neusoft.common.commonsysapiinverse.CommonSysApiInverseRequests.530
            @Override // java9.util.function.Function
            public CompletableFuture<ResGetCopyFiles> apply(ResGetCopyFiles resGetCopyFiles) {
                return CommonUtil.delayAsync(resGetCopyFiles, duration, CommonSysApiInverseRequests.this.contextUtil.getDefaultExecutor());
            }
        });
    }

    @Override // api.requests.com.neusoft.common.commonsysapiinverse.ICommonSysApiInverseRequests
    public CompletableFuture<ResGetCopyFiles> requestGetCopyFiles() {
        return requestGetCopyFiles(new ReqGetCopyFiles());
    }

    @Override // api.requests.com.neusoft.common.commonsysapiinverse.ICommonSysApiInverseRequests
    public CompletableFuture<ResGetCopyFiles> requestGetCopyFiles(Duration duration) {
        return requestGetCopyFiles(new ReqGetCopyFiles(), duration);
    }

    @Override // api.requests.com.neusoft.common.commonsysapiinverse.ICommonSysApiInverseRequests
    public CompletableFuture<Void> fireGetCopyFiles(ReqGetCopyFiles reqGetCopyFiles) {
        reqGetCopyFiles.setResponse(null);
        String str = this.component;
        if (str != null) {
            reqGetCopyFiles.setComponent(str);
        }
        return Communication.request(this.contextUtil.createFromClass(reqGetCopyFiles)).thenCompose(new Function<Object, CompletableFuture<Void>>() { // from class: api.requests.com.neusoft.common.commonsysapiinverse.CommonSysApiInverseRequests.531
            @Override // java9.util.function.Function
            public CompletableFuture<Void> apply(Object obj) {
                return CommonSysApiInverseRequests.completedVoidFuture;
            }
        });
    }

    @Override // api.requests.com.neusoft.common.commonsysapiinverse.ICommonSysApiInverseRequests
    public CompletableFuture<Void> fireGetCopyFiles(ReqGetCopyFiles reqGetCopyFiles, final Duration duration) {
        return fireGetCopyFiles(reqGetCopyFiles).thenCompose((Function<? super Void, ? extends CompletionStage<U>>) new Function<Void, CompletableFuture<Void>>() { // from class: api.requests.com.neusoft.common.commonsysapiinverse.CommonSysApiInverseRequests.532
            @Override // java9.util.function.Function
            public CompletableFuture<Void> apply(Void r2) {
                return CommonUtil.asyncSleep(duration, CommonSysApiInverseRequests.this.contextUtil.getDefaultExecutor());
            }
        });
    }

    @Override // api.requests.com.neusoft.common.commonsysapiinverse.ICommonSysApiInverseRequests
    public CompletableFuture<Void> fireGetCopyFiles() {
        return fireGetCopyFiles(new ReqGetCopyFiles());
    }

    @Override // api.requests.com.neusoft.common.commonsysapiinverse.ICommonSysApiInverseRequests
    public CompletableFuture<Void> fireGetCopyFiles(final Duration duration) {
        return fireGetCopyFiles(new ReqGetCopyFiles()).thenCompose((Function<? super Void, ? extends CompletionStage<U>>) new Function<Void, CompletableFuture<Void>>() { // from class: api.requests.com.neusoft.common.commonsysapiinverse.CommonSysApiInverseRequests.533
            @Override // java9.util.function.Function
            public CompletableFuture<Void> apply(Void r2) {
                return CommonUtil.asyncSleep(duration, CommonSysApiInverseRequests.this.contextUtil.getDefaultExecutor());
            }
        });
    }

    @Override // api.requests.com.neusoft.common.commonsysapiinverse.ICommonSysApiInverseRequests
    public CompletableFuture<ResGetLocalDBVersionInfo> requestGetLocalDBVersionInfo(ReqGetLocalDBVersionInfo reqGetLocalDBVersionInfo) {
        if (reqGetLocalDBVersionInfo.getResponse() == null) {
            reqGetLocalDBVersionInfo.setResponse(new ResGetLocalDBVersionInfo());
        }
        String str = this.component;
        if (str != null) {
            reqGetLocalDBVersionInfo.setComponent(str);
            reqGetLocalDBVersionInfo.getResponse().setComponent(this.component);
        }
        return Communication.request(this.contextUtil.createFromClass(reqGetLocalDBVersionInfo));
    }

    @Override // api.requests.com.neusoft.common.commonsysapiinverse.ICommonSysApiInverseRequests
    public CompletableFuture<ResGetLocalDBVersionInfo> requestGetLocalDBVersionInfo(ReqGetLocalDBVersionInfo reqGetLocalDBVersionInfo, final Duration duration) {
        return requestGetLocalDBVersionInfo(reqGetLocalDBVersionInfo).thenCompose((Function<? super ResGetLocalDBVersionInfo, ? extends CompletionStage<U>>) new Function<ResGetLocalDBVersionInfo, CompletableFuture<ResGetLocalDBVersionInfo>>() { // from class: api.requests.com.neusoft.common.commonsysapiinverse.CommonSysApiInverseRequests.534
            @Override // java9.util.function.Function
            public CompletableFuture<ResGetLocalDBVersionInfo> apply(ResGetLocalDBVersionInfo resGetLocalDBVersionInfo) {
                return CommonUtil.delayAsync(resGetLocalDBVersionInfo, duration, CommonSysApiInverseRequests.this.contextUtil.getDefaultExecutor());
            }
        });
    }

    @Override // api.requests.com.neusoft.common.commonsysapiinverse.ICommonSysApiInverseRequests
    public CompletableFuture<ResGetLocalDBVersionInfo> requestGetLocalDBVersionInfo() {
        return requestGetLocalDBVersionInfo(new ReqGetLocalDBVersionInfo());
    }

    @Override // api.requests.com.neusoft.common.commonsysapiinverse.ICommonSysApiInverseRequests
    public CompletableFuture<ResGetLocalDBVersionInfo> requestGetLocalDBVersionInfo(Duration duration) {
        return requestGetLocalDBVersionInfo(new ReqGetLocalDBVersionInfo(), duration);
    }

    @Override // api.requests.com.neusoft.common.commonsysapiinverse.ICommonSysApiInverseRequests
    public CompletableFuture<Void> fireGetLocalDBVersionInfo(ReqGetLocalDBVersionInfo reqGetLocalDBVersionInfo) {
        reqGetLocalDBVersionInfo.setResponse(null);
        String str = this.component;
        if (str != null) {
            reqGetLocalDBVersionInfo.setComponent(str);
        }
        return Communication.request(this.contextUtil.createFromClass(reqGetLocalDBVersionInfo)).thenCompose(new Function<Object, CompletableFuture<Void>>() { // from class: api.requests.com.neusoft.common.commonsysapiinverse.CommonSysApiInverseRequests.535
            @Override // java9.util.function.Function
            public CompletableFuture<Void> apply(Object obj) {
                return CommonSysApiInverseRequests.completedVoidFuture;
            }
        });
    }

    @Override // api.requests.com.neusoft.common.commonsysapiinverse.ICommonSysApiInverseRequests
    public CompletableFuture<Void> fireGetLocalDBVersionInfo(ReqGetLocalDBVersionInfo reqGetLocalDBVersionInfo, final Duration duration) {
        return fireGetLocalDBVersionInfo(reqGetLocalDBVersionInfo).thenCompose((Function<? super Void, ? extends CompletionStage<U>>) new Function<Void, CompletableFuture<Void>>() { // from class: api.requests.com.neusoft.common.commonsysapiinverse.CommonSysApiInverseRequests.536
            @Override // java9.util.function.Function
            public CompletableFuture<Void> apply(Void r2) {
                return CommonUtil.asyncSleep(duration, CommonSysApiInverseRequests.this.contextUtil.getDefaultExecutor());
            }
        });
    }

    @Override // api.requests.com.neusoft.common.commonsysapiinverse.ICommonSysApiInverseRequests
    public CompletableFuture<Void> fireGetLocalDBVersionInfo() {
        return fireGetLocalDBVersionInfo(new ReqGetLocalDBVersionInfo());
    }

    @Override // api.requests.com.neusoft.common.commonsysapiinverse.ICommonSysApiInverseRequests
    public CompletableFuture<Void> fireGetLocalDBVersionInfo(final Duration duration) {
        return fireGetLocalDBVersionInfo(new ReqGetLocalDBVersionInfo()).thenCompose((Function<? super Void, ? extends CompletionStage<U>>) new Function<Void, CompletableFuture<Void>>() { // from class: api.requests.com.neusoft.common.commonsysapiinverse.CommonSysApiInverseRequests.537
            @Override // java9.util.function.Function
            public CompletableFuture<Void> apply(Void r2) {
                return CommonUtil.asyncSleep(duration, CommonSysApiInverseRequests.this.contextUtil.getDefaultExecutor());
            }
        });
    }

    @Override // api.requests.com.neusoft.common.commonsysapiinverse.ICommonSysApiInverseRequests
    public CompletableFuture<ResGetSoftwareVersionInfo> requestGetSoftwareVersionInfo(ReqGetSoftwareVersionInfo reqGetSoftwareVersionInfo) {
        if (reqGetSoftwareVersionInfo.getResponse() == null) {
            reqGetSoftwareVersionInfo.setResponse(new ResGetSoftwareVersionInfo());
        }
        String str = this.component;
        if (str != null) {
            reqGetSoftwareVersionInfo.setComponent(str);
            reqGetSoftwareVersionInfo.getResponse().setComponent(this.component);
        }
        return Communication.request(this.contextUtil.createFromClass(reqGetSoftwareVersionInfo));
    }

    @Override // api.requests.com.neusoft.common.commonsysapiinverse.ICommonSysApiInverseRequests
    public CompletableFuture<ResGetSoftwareVersionInfo> requestGetSoftwareVersionInfo(ReqGetSoftwareVersionInfo reqGetSoftwareVersionInfo, final Duration duration) {
        return requestGetSoftwareVersionInfo(reqGetSoftwareVersionInfo).thenCompose((Function<? super ResGetSoftwareVersionInfo, ? extends CompletionStage<U>>) new Function<ResGetSoftwareVersionInfo, CompletableFuture<ResGetSoftwareVersionInfo>>() { // from class: api.requests.com.neusoft.common.commonsysapiinverse.CommonSysApiInverseRequests.538
            @Override // java9.util.function.Function
            public CompletableFuture<ResGetSoftwareVersionInfo> apply(ResGetSoftwareVersionInfo resGetSoftwareVersionInfo) {
                return CommonUtil.delayAsync(resGetSoftwareVersionInfo, duration, CommonSysApiInverseRequests.this.contextUtil.getDefaultExecutor());
            }
        });
    }

    @Override // api.requests.com.neusoft.common.commonsysapiinverse.ICommonSysApiInverseRequests
    public CompletableFuture<ResGetSoftwareVersionInfo> requestGetSoftwareVersionInfo() {
        return requestGetSoftwareVersionInfo(new ReqGetSoftwareVersionInfo());
    }

    @Override // api.requests.com.neusoft.common.commonsysapiinverse.ICommonSysApiInverseRequests
    public CompletableFuture<ResGetSoftwareVersionInfo> requestGetSoftwareVersionInfo(Duration duration) {
        return requestGetSoftwareVersionInfo(new ReqGetSoftwareVersionInfo(), duration);
    }

    @Override // api.requests.com.neusoft.common.commonsysapiinverse.ICommonSysApiInverseRequests
    public CompletableFuture<Void> fireGetSoftwareVersionInfo(ReqGetSoftwareVersionInfo reqGetSoftwareVersionInfo) {
        reqGetSoftwareVersionInfo.setResponse(null);
        String str = this.component;
        if (str != null) {
            reqGetSoftwareVersionInfo.setComponent(str);
        }
        return Communication.request(this.contextUtil.createFromClass(reqGetSoftwareVersionInfo)).thenCompose(new Function<Object, CompletableFuture<Void>>() { // from class: api.requests.com.neusoft.common.commonsysapiinverse.CommonSysApiInverseRequests.539
            @Override // java9.util.function.Function
            public CompletableFuture<Void> apply(Object obj) {
                return CommonSysApiInverseRequests.completedVoidFuture;
            }
        });
    }

    @Override // api.requests.com.neusoft.common.commonsysapiinverse.ICommonSysApiInverseRequests
    public CompletableFuture<Void> fireGetSoftwareVersionInfo(ReqGetSoftwareVersionInfo reqGetSoftwareVersionInfo, final Duration duration) {
        return fireGetSoftwareVersionInfo(reqGetSoftwareVersionInfo).thenCompose((Function<? super Void, ? extends CompletionStage<U>>) new Function<Void, CompletableFuture<Void>>() { // from class: api.requests.com.neusoft.common.commonsysapiinverse.CommonSysApiInverseRequests.540
            @Override // java9.util.function.Function
            public CompletableFuture<Void> apply(Void r2) {
                return CommonUtil.asyncSleep(duration, CommonSysApiInverseRequests.this.contextUtil.getDefaultExecutor());
            }
        });
    }

    @Override // api.requests.com.neusoft.common.commonsysapiinverse.ICommonSysApiInverseRequests
    public CompletableFuture<Void> fireGetSoftwareVersionInfo() {
        return fireGetSoftwareVersionInfo(new ReqGetSoftwareVersionInfo());
    }

    @Override // api.requests.com.neusoft.common.commonsysapiinverse.ICommonSysApiInverseRequests
    public CompletableFuture<Void> fireGetSoftwareVersionInfo(final Duration duration) {
        return fireGetSoftwareVersionInfo(new ReqGetSoftwareVersionInfo()).thenCompose((Function<? super Void, ? extends CompletionStage<U>>) new Function<Void, CompletableFuture<Void>>() { // from class: api.requests.com.neusoft.common.commonsysapiinverse.CommonSysApiInverseRequests.541
            @Override // java9.util.function.Function
            public CompletableFuture<Void> apply(Void r2) {
                return CommonUtil.asyncSleep(duration, CommonSysApiInverseRequests.this.contextUtil.getDefaultExecutor());
            }
        });
    }

    @Override // api.requests.com.neusoft.common.commonsysapiinverse.ICommonSysApiInverseRequests
    public CompletableFuture<ResGetNotifyOpenGPS> requestGetNotifyOpenGPS(ReqGetNotifyOpenGPS reqGetNotifyOpenGPS) {
        if (reqGetNotifyOpenGPS.getResponse() == null) {
            reqGetNotifyOpenGPS.setResponse(new ResGetNotifyOpenGPS());
        }
        String str = this.component;
        if (str != null) {
            reqGetNotifyOpenGPS.setComponent(str);
            reqGetNotifyOpenGPS.getResponse().setComponent(this.component);
        }
        return Communication.request(this.contextUtil.createFromClass(reqGetNotifyOpenGPS));
    }

    @Override // api.requests.com.neusoft.common.commonsysapiinverse.ICommonSysApiInverseRequests
    public CompletableFuture<ResGetNotifyOpenGPS> requestGetNotifyOpenGPS(ReqGetNotifyOpenGPS reqGetNotifyOpenGPS, final Duration duration) {
        return requestGetNotifyOpenGPS(reqGetNotifyOpenGPS).thenCompose((Function<? super ResGetNotifyOpenGPS, ? extends CompletionStage<U>>) new Function<ResGetNotifyOpenGPS, CompletableFuture<ResGetNotifyOpenGPS>>() { // from class: api.requests.com.neusoft.common.commonsysapiinverse.CommonSysApiInverseRequests.542
            @Override // java9.util.function.Function
            public CompletableFuture<ResGetNotifyOpenGPS> apply(ResGetNotifyOpenGPS resGetNotifyOpenGPS) {
                return CommonUtil.delayAsync(resGetNotifyOpenGPS, duration, CommonSysApiInverseRequests.this.contextUtil.getDefaultExecutor());
            }
        });
    }

    @Override // api.requests.com.neusoft.common.commonsysapiinverse.ICommonSysApiInverseRequests
    public CompletableFuture<ResGetNotifyOpenGPS> requestGetNotifyOpenGPS() {
        return requestGetNotifyOpenGPS(new ReqGetNotifyOpenGPS());
    }

    @Override // api.requests.com.neusoft.common.commonsysapiinverse.ICommonSysApiInverseRequests
    public CompletableFuture<ResGetNotifyOpenGPS> requestGetNotifyOpenGPS(Duration duration) {
        return requestGetNotifyOpenGPS(new ReqGetNotifyOpenGPS(), duration);
    }

    @Override // api.requests.com.neusoft.common.commonsysapiinverse.ICommonSysApiInverseRequests
    public CompletableFuture<Void> fireGetNotifyOpenGPS(ReqGetNotifyOpenGPS reqGetNotifyOpenGPS) {
        reqGetNotifyOpenGPS.setResponse(null);
        String str = this.component;
        if (str != null) {
            reqGetNotifyOpenGPS.setComponent(str);
        }
        return Communication.request(this.contextUtil.createFromClass(reqGetNotifyOpenGPS)).thenCompose(new Function<Object, CompletableFuture<Void>>() { // from class: api.requests.com.neusoft.common.commonsysapiinverse.CommonSysApiInverseRequests.543
            @Override // java9.util.function.Function
            public CompletableFuture<Void> apply(Object obj) {
                return CommonSysApiInverseRequests.completedVoidFuture;
            }
        });
    }

    @Override // api.requests.com.neusoft.common.commonsysapiinverse.ICommonSysApiInverseRequests
    public CompletableFuture<Void> fireGetNotifyOpenGPS(ReqGetNotifyOpenGPS reqGetNotifyOpenGPS, final Duration duration) {
        return fireGetNotifyOpenGPS(reqGetNotifyOpenGPS).thenCompose((Function<? super Void, ? extends CompletionStage<U>>) new Function<Void, CompletableFuture<Void>>() { // from class: api.requests.com.neusoft.common.commonsysapiinverse.CommonSysApiInverseRequests.544
            @Override // java9.util.function.Function
            public CompletableFuture<Void> apply(Void r2) {
                return CommonUtil.asyncSleep(duration, CommonSysApiInverseRequests.this.contextUtil.getDefaultExecutor());
            }
        });
    }

    @Override // api.requests.com.neusoft.common.commonsysapiinverse.ICommonSysApiInverseRequests
    public CompletableFuture<Void> fireGetNotifyOpenGPS() {
        return fireGetNotifyOpenGPS(new ReqGetNotifyOpenGPS());
    }

    @Override // api.requests.com.neusoft.common.commonsysapiinverse.ICommonSysApiInverseRequests
    public CompletableFuture<Void> fireGetNotifyOpenGPS(final Duration duration) {
        return fireGetNotifyOpenGPS(new ReqGetNotifyOpenGPS()).thenCompose((Function<? super Void, ? extends CompletionStage<U>>) new Function<Void, CompletableFuture<Void>>() { // from class: api.requests.com.neusoft.common.commonsysapiinverse.CommonSysApiInverseRequests.545
            @Override // java9.util.function.Function
            public CompletableFuture<Void> apply(Void r2) {
                return CommonUtil.asyncSleep(duration, CommonSysApiInverseRequests.this.contextUtil.getDefaultExecutor());
            }
        });
    }

    @Override // api.requests.com.neusoft.common.commonsysapiinverse.ICommonSysApiInverseRequests
    public CompletableFuture<ResGetObserveWeatherInfo2System> requestGetObserveWeatherInfo2System(ReqGetObserveWeatherInfo2System reqGetObserveWeatherInfo2System) {
        if (reqGetObserveWeatherInfo2System.getResponse() == null) {
            reqGetObserveWeatherInfo2System.setResponse(new ResGetObserveWeatherInfo2System());
        }
        String str = this.component;
        if (str != null) {
            reqGetObserveWeatherInfo2System.setComponent(str);
            reqGetObserveWeatherInfo2System.getResponse().setComponent(this.component);
        }
        return Communication.request(this.contextUtil.createFromClass(reqGetObserveWeatherInfo2System));
    }

    @Override // api.requests.com.neusoft.common.commonsysapiinverse.ICommonSysApiInverseRequests
    public CompletableFuture<ResGetObserveWeatherInfo2System> requestGetObserveWeatherInfo2System(ReqGetObserveWeatherInfo2System reqGetObserveWeatherInfo2System, final Duration duration) {
        return requestGetObserveWeatherInfo2System(reqGetObserveWeatherInfo2System).thenCompose((Function<? super ResGetObserveWeatherInfo2System, ? extends CompletionStage<U>>) new Function<ResGetObserveWeatherInfo2System, CompletableFuture<ResGetObserveWeatherInfo2System>>() { // from class: api.requests.com.neusoft.common.commonsysapiinverse.CommonSysApiInverseRequests.546
            @Override // java9.util.function.Function
            public CompletableFuture<ResGetObserveWeatherInfo2System> apply(ResGetObserveWeatherInfo2System resGetObserveWeatherInfo2System) {
                return CommonUtil.delayAsync(resGetObserveWeatherInfo2System, duration, CommonSysApiInverseRequests.this.contextUtil.getDefaultExecutor());
            }
        });
    }

    @Override // api.requests.com.neusoft.common.commonsysapiinverse.ICommonSysApiInverseRequests
    public CompletableFuture<ResGetObserveWeatherInfo2System> requestGetObserveWeatherInfo2System() {
        return requestGetObserveWeatherInfo2System(new ReqGetObserveWeatherInfo2System());
    }

    @Override // api.requests.com.neusoft.common.commonsysapiinverse.ICommonSysApiInverseRequests
    public CompletableFuture<ResGetObserveWeatherInfo2System> requestGetObserveWeatherInfo2System(Duration duration) {
        return requestGetObserveWeatherInfo2System(new ReqGetObserveWeatherInfo2System(), duration);
    }

    @Override // api.requests.com.neusoft.common.commonsysapiinverse.ICommonSysApiInverseRequests
    public CompletableFuture<Void> fireGetObserveWeatherInfo2System(ReqGetObserveWeatherInfo2System reqGetObserveWeatherInfo2System) {
        reqGetObserveWeatherInfo2System.setResponse(null);
        String str = this.component;
        if (str != null) {
            reqGetObserveWeatherInfo2System.setComponent(str);
        }
        return Communication.request(this.contextUtil.createFromClass(reqGetObserveWeatherInfo2System)).thenCompose(new Function<Object, CompletableFuture<Void>>() { // from class: api.requests.com.neusoft.common.commonsysapiinverse.CommonSysApiInverseRequests.547
            @Override // java9.util.function.Function
            public CompletableFuture<Void> apply(Object obj) {
                return CommonSysApiInverseRequests.completedVoidFuture;
            }
        });
    }

    @Override // api.requests.com.neusoft.common.commonsysapiinverse.ICommonSysApiInverseRequests
    public CompletableFuture<Void> fireGetObserveWeatherInfo2System(ReqGetObserveWeatherInfo2System reqGetObserveWeatherInfo2System, final Duration duration) {
        return fireGetObserveWeatherInfo2System(reqGetObserveWeatherInfo2System).thenCompose((Function<? super Void, ? extends CompletionStage<U>>) new Function<Void, CompletableFuture<Void>>() { // from class: api.requests.com.neusoft.common.commonsysapiinverse.CommonSysApiInverseRequests.548
            @Override // java9.util.function.Function
            public CompletableFuture<Void> apply(Void r2) {
                return CommonUtil.asyncSleep(duration, CommonSysApiInverseRequests.this.contextUtil.getDefaultExecutor());
            }
        });
    }

    @Override // api.requests.com.neusoft.common.commonsysapiinverse.ICommonSysApiInverseRequests
    public CompletableFuture<Void> fireGetObserveWeatherInfo2System() {
        return fireGetObserveWeatherInfo2System(new ReqGetObserveWeatherInfo2System());
    }

    @Override // api.requests.com.neusoft.common.commonsysapiinverse.ICommonSysApiInverseRequests
    public CompletableFuture<Void> fireGetObserveWeatherInfo2System(final Duration duration) {
        return fireGetObserveWeatherInfo2System(new ReqGetObserveWeatherInfo2System()).thenCompose((Function<? super Void, ? extends CompletionStage<U>>) new Function<Void, CompletableFuture<Void>>() { // from class: api.requests.com.neusoft.common.commonsysapiinverse.CommonSysApiInverseRequests.549
            @Override // java9.util.function.Function
            public CompletableFuture<Void> apply(Void r2) {
                return CommonUtil.asyncSleep(duration, CommonSysApiInverseRequests.this.contextUtil.getDefaultExecutor());
            }
        });
    }

    @Override // api.requests.com.neusoft.common.commonsysapiinverse.ICommonSysApiInverseRequests
    public CompletableFuture<ResGetDelegateSystemEvent> requestGetDelegateSystemEvent(ReqGetDelegateSystemEvent reqGetDelegateSystemEvent) {
        if (reqGetDelegateSystemEvent.getResponse() == null) {
            reqGetDelegateSystemEvent.setResponse(new ResGetDelegateSystemEvent());
        }
        String str = this.component;
        if (str != null) {
            reqGetDelegateSystemEvent.setComponent(str);
            reqGetDelegateSystemEvent.getResponse().setComponent(this.component);
        }
        return Communication.request(this.contextUtil.createFromClass(reqGetDelegateSystemEvent));
    }

    @Override // api.requests.com.neusoft.common.commonsysapiinverse.ICommonSysApiInverseRequests
    public CompletableFuture<ResGetDelegateSystemEvent> requestGetDelegateSystemEvent(ReqGetDelegateSystemEvent reqGetDelegateSystemEvent, final Duration duration) {
        return requestGetDelegateSystemEvent(reqGetDelegateSystemEvent).thenCompose((Function<? super ResGetDelegateSystemEvent, ? extends CompletionStage<U>>) new Function<ResGetDelegateSystemEvent, CompletableFuture<ResGetDelegateSystemEvent>>() { // from class: api.requests.com.neusoft.common.commonsysapiinverse.CommonSysApiInverseRequests.550
            @Override // java9.util.function.Function
            public CompletableFuture<ResGetDelegateSystemEvent> apply(ResGetDelegateSystemEvent resGetDelegateSystemEvent) {
                return CommonUtil.delayAsync(resGetDelegateSystemEvent, duration, CommonSysApiInverseRequests.this.contextUtil.getDefaultExecutor());
            }
        });
    }

    @Override // api.requests.com.neusoft.common.commonsysapiinverse.ICommonSysApiInverseRequests
    public CompletableFuture<ResGetDelegateSystemEvent> requestGetDelegateSystemEvent() {
        return requestGetDelegateSystemEvent(new ReqGetDelegateSystemEvent());
    }

    @Override // api.requests.com.neusoft.common.commonsysapiinverse.ICommonSysApiInverseRequests
    public CompletableFuture<ResGetDelegateSystemEvent> requestGetDelegateSystemEvent(Duration duration) {
        return requestGetDelegateSystemEvent(new ReqGetDelegateSystemEvent(), duration);
    }

    @Override // api.requests.com.neusoft.common.commonsysapiinverse.ICommonSysApiInverseRequests
    public CompletableFuture<Void> fireGetDelegateSystemEvent(ReqGetDelegateSystemEvent reqGetDelegateSystemEvent) {
        reqGetDelegateSystemEvent.setResponse(null);
        String str = this.component;
        if (str != null) {
            reqGetDelegateSystemEvent.setComponent(str);
        }
        return Communication.request(this.contextUtil.createFromClass(reqGetDelegateSystemEvent)).thenCompose(new Function<Object, CompletableFuture<Void>>() { // from class: api.requests.com.neusoft.common.commonsysapiinverse.CommonSysApiInverseRequests.551
            @Override // java9.util.function.Function
            public CompletableFuture<Void> apply(Object obj) {
                return CommonSysApiInverseRequests.completedVoidFuture;
            }
        });
    }

    @Override // api.requests.com.neusoft.common.commonsysapiinverse.ICommonSysApiInverseRequests
    public CompletableFuture<Void> fireGetDelegateSystemEvent(ReqGetDelegateSystemEvent reqGetDelegateSystemEvent, final Duration duration) {
        return fireGetDelegateSystemEvent(reqGetDelegateSystemEvent).thenCompose((Function<? super Void, ? extends CompletionStage<U>>) new Function<Void, CompletableFuture<Void>>() { // from class: api.requests.com.neusoft.common.commonsysapiinverse.CommonSysApiInverseRequests.552
            @Override // java9.util.function.Function
            public CompletableFuture<Void> apply(Void r2) {
                return CommonUtil.asyncSleep(duration, CommonSysApiInverseRequests.this.contextUtil.getDefaultExecutor());
            }
        });
    }

    @Override // api.requests.com.neusoft.common.commonsysapiinverse.ICommonSysApiInverseRequests
    public CompletableFuture<Void> fireGetDelegateSystemEvent() {
        return fireGetDelegateSystemEvent(new ReqGetDelegateSystemEvent());
    }

    @Override // api.requests.com.neusoft.common.commonsysapiinverse.ICommonSysApiInverseRequests
    public CompletableFuture<Void> fireGetDelegateSystemEvent(final Duration duration) {
        return fireGetDelegateSystemEvent(new ReqGetDelegateSystemEvent()).thenCompose((Function<? super Void, ? extends CompletionStage<U>>) new Function<Void, CompletableFuture<Void>>() { // from class: api.requests.com.neusoft.common.commonsysapiinverse.CommonSysApiInverseRequests.553
            @Override // java9.util.function.Function
            public CompletableFuture<Void> apply(Void r2) {
                return CommonUtil.asyncSleep(duration, CommonSysApiInverseRequests.this.contextUtil.getDefaultExecutor());
            }
        });
    }

    @Override // api.requests.com.neusoft.common.commonsysapiinverse.ICommonSysApiInverseRequests
    public CompletableFuture<ResGetLoadURL> requestGetLoadURL(ReqGetLoadURL reqGetLoadURL) {
        if (reqGetLoadURL.getResponse() == null) {
            reqGetLoadURL.setResponse(new ResGetLoadURL());
        }
        String str = this.component;
        if (str != null) {
            reqGetLoadURL.setComponent(str);
            reqGetLoadURL.getResponse().setComponent(this.component);
        }
        return Communication.request(this.contextUtil.createFromClass(reqGetLoadURL));
    }

    @Override // api.requests.com.neusoft.common.commonsysapiinverse.ICommonSysApiInverseRequests
    public CompletableFuture<ResGetLoadURL> requestGetLoadURL(ReqGetLoadURL reqGetLoadURL, final Duration duration) {
        return requestGetLoadURL(reqGetLoadURL).thenCompose((Function<? super ResGetLoadURL, ? extends CompletionStage<U>>) new Function<ResGetLoadURL, CompletableFuture<ResGetLoadURL>>() { // from class: api.requests.com.neusoft.common.commonsysapiinverse.CommonSysApiInverseRequests.554
            @Override // java9.util.function.Function
            public CompletableFuture<ResGetLoadURL> apply(ResGetLoadURL resGetLoadURL) {
                return CommonUtil.delayAsync(resGetLoadURL, duration, CommonSysApiInverseRequests.this.contextUtil.getDefaultExecutor());
            }
        });
    }

    @Override // api.requests.com.neusoft.common.commonsysapiinverse.ICommonSysApiInverseRequests
    public CompletableFuture<ResGetLoadURL> requestGetLoadURL() {
        return requestGetLoadURL(new ReqGetLoadURL());
    }

    @Override // api.requests.com.neusoft.common.commonsysapiinverse.ICommonSysApiInverseRequests
    public CompletableFuture<ResGetLoadURL> requestGetLoadURL(Duration duration) {
        return requestGetLoadURL(new ReqGetLoadURL(), duration);
    }

    @Override // api.requests.com.neusoft.common.commonsysapiinverse.ICommonSysApiInverseRequests
    public CompletableFuture<Void> fireGetLoadURL(ReqGetLoadURL reqGetLoadURL) {
        reqGetLoadURL.setResponse(null);
        String str = this.component;
        if (str != null) {
            reqGetLoadURL.setComponent(str);
        }
        return Communication.request(this.contextUtil.createFromClass(reqGetLoadURL)).thenCompose(new Function<Object, CompletableFuture<Void>>() { // from class: api.requests.com.neusoft.common.commonsysapiinverse.CommonSysApiInverseRequests.555
            @Override // java9.util.function.Function
            public CompletableFuture<Void> apply(Object obj) {
                return CommonSysApiInverseRequests.completedVoidFuture;
            }
        });
    }

    @Override // api.requests.com.neusoft.common.commonsysapiinverse.ICommonSysApiInverseRequests
    public CompletableFuture<Void> fireGetLoadURL(ReqGetLoadURL reqGetLoadURL, final Duration duration) {
        return fireGetLoadURL(reqGetLoadURL).thenCompose((Function<? super Void, ? extends CompletionStage<U>>) new Function<Void, CompletableFuture<Void>>() { // from class: api.requests.com.neusoft.common.commonsysapiinverse.CommonSysApiInverseRequests.556
            @Override // java9.util.function.Function
            public CompletableFuture<Void> apply(Void r2) {
                return CommonUtil.asyncSleep(duration, CommonSysApiInverseRequests.this.contextUtil.getDefaultExecutor());
            }
        });
    }

    @Override // api.requests.com.neusoft.common.commonsysapiinverse.ICommonSysApiInverseRequests
    public CompletableFuture<Void> fireGetLoadURL() {
        return fireGetLoadURL(new ReqGetLoadURL());
    }

    @Override // api.requests.com.neusoft.common.commonsysapiinverse.ICommonSysApiInverseRequests
    public CompletableFuture<Void> fireGetLoadURL(final Duration duration) {
        return fireGetLoadURL(new ReqGetLoadURL()).thenCompose((Function<? super Void, ? extends CompletionStage<U>>) new Function<Void, CompletableFuture<Void>>() { // from class: api.requests.com.neusoft.common.commonsysapiinverse.CommonSysApiInverseRequests.557
            @Override // java9.util.function.Function
            public CompletableFuture<Void> apply(Void r2) {
                return CommonUtil.asyncSleep(duration, CommonSysApiInverseRequests.this.contextUtil.getDefaultExecutor());
            }
        });
    }

    @Override // api.requests.com.neusoft.common.commonsysapiinverse.ICommonSysApiInverseRequests
    public CompletableFuture<ResGetGetLowFuelStatus> requestGetGetLowFuelStatus(ReqGetGetLowFuelStatus reqGetGetLowFuelStatus) {
        if (reqGetGetLowFuelStatus.getResponse() == null) {
            reqGetGetLowFuelStatus.setResponse(new ResGetGetLowFuelStatus());
        }
        String str = this.component;
        if (str != null) {
            reqGetGetLowFuelStatus.setComponent(str);
            reqGetGetLowFuelStatus.getResponse().setComponent(this.component);
        }
        return Communication.request(this.contextUtil.createFromClass(reqGetGetLowFuelStatus));
    }

    @Override // api.requests.com.neusoft.common.commonsysapiinverse.ICommonSysApiInverseRequests
    public CompletableFuture<ResGetGetLowFuelStatus> requestGetGetLowFuelStatus(ReqGetGetLowFuelStatus reqGetGetLowFuelStatus, final Duration duration) {
        return requestGetGetLowFuelStatus(reqGetGetLowFuelStatus).thenCompose((Function<? super ResGetGetLowFuelStatus, ? extends CompletionStage<U>>) new Function<ResGetGetLowFuelStatus, CompletableFuture<ResGetGetLowFuelStatus>>() { // from class: api.requests.com.neusoft.common.commonsysapiinverse.CommonSysApiInverseRequests.558
            @Override // java9.util.function.Function
            public CompletableFuture<ResGetGetLowFuelStatus> apply(ResGetGetLowFuelStatus resGetGetLowFuelStatus) {
                return CommonUtil.delayAsync(resGetGetLowFuelStatus, duration, CommonSysApiInverseRequests.this.contextUtil.getDefaultExecutor());
            }
        });
    }

    @Override // api.requests.com.neusoft.common.commonsysapiinverse.ICommonSysApiInverseRequests
    public CompletableFuture<ResGetGetLowFuelStatus> requestGetGetLowFuelStatus() {
        return requestGetGetLowFuelStatus(new ReqGetGetLowFuelStatus());
    }

    @Override // api.requests.com.neusoft.common.commonsysapiinverse.ICommonSysApiInverseRequests
    public CompletableFuture<ResGetGetLowFuelStatus> requestGetGetLowFuelStatus(Duration duration) {
        return requestGetGetLowFuelStatus(new ReqGetGetLowFuelStatus(), duration);
    }

    @Override // api.requests.com.neusoft.common.commonsysapiinverse.ICommonSysApiInverseRequests
    public CompletableFuture<Void> fireGetGetLowFuelStatus(ReqGetGetLowFuelStatus reqGetGetLowFuelStatus) {
        reqGetGetLowFuelStatus.setResponse(null);
        String str = this.component;
        if (str != null) {
            reqGetGetLowFuelStatus.setComponent(str);
        }
        return Communication.request(this.contextUtil.createFromClass(reqGetGetLowFuelStatus)).thenCompose(new Function<Object, CompletableFuture<Void>>() { // from class: api.requests.com.neusoft.common.commonsysapiinverse.CommonSysApiInverseRequests.559
            @Override // java9.util.function.Function
            public CompletableFuture<Void> apply(Object obj) {
                return CommonSysApiInverseRequests.completedVoidFuture;
            }
        });
    }

    @Override // api.requests.com.neusoft.common.commonsysapiinverse.ICommonSysApiInverseRequests
    public CompletableFuture<Void> fireGetGetLowFuelStatus(ReqGetGetLowFuelStatus reqGetGetLowFuelStatus, final Duration duration) {
        return fireGetGetLowFuelStatus(reqGetGetLowFuelStatus).thenCompose((Function<? super Void, ? extends CompletionStage<U>>) new Function<Void, CompletableFuture<Void>>() { // from class: api.requests.com.neusoft.common.commonsysapiinverse.CommonSysApiInverseRequests.560
            @Override // java9.util.function.Function
            public CompletableFuture<Void> apply(Void r2) {
                return CommonUtil.asyncSleep(duration, CommonSysApiInverseRequests.this.contextUtil.getDefaultExecutor());
            }
        });
    }

    @Override // api.requests.com.neusoft.common.commonsysapiinverse.ICommonSysApiInverseRequests
    public CompletableFuture<Void> fireGetGetLowFuelStatus() {
        return fireGetGetLowFuelStatus(new ReqGetGetLowFuelStatus());
    }

    @Override // api.requests.com.neusoft.common.commonsysapiinverse.ICommonSysApiInverseRequests
    public CompletableFuture<Void> fireGetGetLowFuelStatus(final Duration duration) {
        return fireGetGetLowFuelStatus(new ReqGetGetLowFuelStatus()).thenCompose((Function<? super Void, ? extends CompletionStage<U>>) new Function<Void, CompletableFuture<Void>>() { // from class: api.requests.com.neusoft.common.commonsysapiinverse.CommonSysApiInverseRequests.561
            @Override // java9.util.function.Function
            public CompletableFuture<Void> apply(Void r2) {
                return CommonUtil.asyncSleep(duration, CommonSysApiInverseRequests.this.contextUtil.getDefaultExecutor());
            }
        });
    }
}
