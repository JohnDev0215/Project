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
import java9.util.concurrent.CompletableFuture;
import org.threeten.bp.Duration;

/* JADX INFO: loaded from: classes.dex */
public interface ICommonSysApiInverseRequests {
    CompletableFuture<Void> fireAppFocusEventInfo(ReqAppFocusEventInfo.Arguments arguments);

    CompletableFuture<Void> fireAppFocusEventInfo(ReqAppFocusEventInfo.Arguments arguments, Duration duration);

    CompletableFuture<Void> fireAppFocusEventInfo(ReqAppFocusEventInfo reqAppFocusEventInfo);

    CompletableFuture<Void> fireAppFocusEventInfo(ReqAppFocusEventInfo reqAppFocusEventInfo, Duration duration);

    CompletableFuture<Void> fireApplyUpdate();

    CompletableFuture<Void> fireApplyUpdate(ReqApplyUpdate reqApplyUpdate);

    CompletableFuture<Void> fireApplyUpdate(ReqApplyUpdate reqApplyUpdate, Duration duration);

    CompletableFuture<Void> fireApplyUpdate(Duration duration);

    CompletableFuture<Void> fireBackToCCP();

    CompletableFuture<Void> fireBackToCCP(ReqBackToCCP reqBackToCCP);

    CompletableFuture<Void> fireBackToCCP(ReqBackToCCP reqBackToCCP, Duration duration);

    CompletableFuture<Void> fireBackToCCP(Duration duration);

    CompletableFuture<Void> fireCheckNaviGuide();

    CompletableFuture<Void> fireCheckNaviGuide(ReqCheckNaviGuide reqCheckNaviGuide);

    CompletableFuture<Void> fireCheckNaviGuide(ReqCheckNaviGuide reqCheckNaviGuide, Duration duration);

    CompletableFuture<Void> fireCheckNaviGuide(Duration duration);

    CompletableFuture<Void> fireDashboardMirrorEventInfo(ReqDashboardMirrorEventInfo.Arguments arguments);

    CompletableFuture<Void> fireDashboardMirrorEventInfo(ReqDashboardMirrorEventInfo.Arguments arguments, Duration duration);

    CompletableFuture<Void> fireDashboardMirrorEventInfo(ReqDashboardMirrorEventInfo reqDashboardMirrorEventInfo);

    CompletableFuture<Void> fireDashboardMirrorEventInfo(ReqDashboardMirrorEventInfo reqDashboardMirrorEventInfo, Duration duration);

    CompletableFuture<Void> fireDataCollectionCheckboxStatus(ReqDataCollectionCheckboxStatus.Arguments arguments);

    CompletableFuture<Void> fireDataCollectionCheckboxStatus(ReqDataCollectionCheckboxStatus.Arguments arguments, Duration duration);

    CompletableFuture<Void> fireDataCollectionCheckboxStatus(ReqDataCollectionCheckboxStatus reqDataCollectionCheckboxStatus);

    CompletableFuture<Void> fireDataCollectionCheckboxStatus(ReqDataCollectionCheckboxStatus reqDataCollectionCheckboxStatus, Duration duration);

    CompletableFuture<Void> fireFeedbackActivateResult(ReqFeedbackActivateResult.Arguments arguments);

    CompletableFuture<Void> fireFeedbackActivateResult(ReqFeedbackActivateResult.Arguments arguments, Duration duration);

    CompletableFuture<Void> fireFeedbackActivateResult(ReqFeedbackActivateResult reqFeedbackActivateResult);

    CompletableFuture<Void> fireFeedbackActivateResult(ReqFeedbackActivateResult reqFeedbackActivateResult, Duration duration);

    CompletableFuture<Void> fireGetAbandonSpeak();

    CompletableFuture<Void> fireGetAbandonSpeak(ReqGetAbandonSpeak reqGetAbandonSpeak);

    CompletableFuture<Void> fireGetAbandonSpeak(ReqGetAbandonSpeak reqGetAbandonSpeak, Duration duration);

    CompletableFuture<Void> fireGetAbandonSpeak(Duration duration);

    CompletableFuture<Void> fireGetAccessCodingAvailable();

    CompletableFuture<Void> fireGetAccessCodingAvailable(ReqGetAccessCodingAvailable reqGetAccessCodingAvailable);

    CompletableFuture<Void> fireGetAccessCodingAvailable(ReqGetAccessCodingAvailable reqGetAccessCodingAvailable, Duration duration);

    CompletableFuture<Void> fireGetAccessCodingAvailable(Duration duration);

    CompletableFuture<Void> fireGetAppLocale();

    CompletableFuture<Void> fireGetAppLocale(ReqGetAppLocale reqGetAppLocale);

    CompletableFuture<Void> fireGetAppLocale(ReqGetAppLocale reqGetAppLocale, Duration duration);

    CompletableFuture<Void> fireGetAppLocale(Duration duration);

    CompletableFuture<Void> fireGetApplyUpdateStatus();

    CompletableFuture<Void> fireGetApplyUpdateStatus(ReqGetApplyUpdateStatus reqGetApplyUpdateStatus);

    CompletableFuture<Void> fireGetApplyUpdateStatus(ReqGetApplyUpdateStatus reqGetApplyUpdateStatus, Duration duration);

    CompletableFuture<Void> fireGetApplyUpdateStatus(Duration duration);

    CompletableFuture<Void> fireGetCallUpStatus();

    CompletableFuture<Void> fireGetCallUpStatus(ReqGetCallUpStatus reqGetCallUpStatus);

    CompletableFuture<Void> fireGetCallUpStatus(ReqGetCallUpStatus reqGetCallUpStatus, Duration duration);

    CompletableFuture<Void> fireGetCallUpStatus(Duration duration);

    CompletableFuture<Void> fireGetCheckDBAvailable();

    CompletableFuture<Void> fireGetCheckDBAvailable(ReqGetCheckDBAvailable reqGetCheckDBAvailable);

    CompletableFuture<Void> fireGetCheckDBAvailable(ReqGetCheckDBAvailable reqGetCheckDBAvailable, Duration duration);

    CompletableFuture<Void> fireGetCheckDBAvailable(Duration duration);

    CompletableFuture<Void> fireGetCloseAndRestartApp();

    CompletableFuture<Void> fireGetCloseAndRestartApp(ReqGetCloseAndRestartApp reqGetCloseAndRestartApp);

    CompletableFuture<Void> fireGetCloseAndRestartApp(ReqGetCloseAndRestartApp reqGetCloseAndRestartApp, Duration duration);

    CompletableFuture<Void> fireGetCloseAndRestartApp(Duration duration);

    CompletableFuture<Void> fireGetCloseApp();

    CompletableFuture<Void> fireGetCloseApp(ReqGetCloseApp reqGetCloseApp);

    CompletableFuture<Void> fireGetCloseApp(ReqGetCloseApp reqGetCloseApp, Duration duration);

    CompletableFuture<Void> fireGetCloseApp(Duration duration);

    CompletableFuture<Void> fireGetCloseMainActivity();

    CompletableFuture<Void> fireGetCloseMainActivity(ReqGetCloseMainActivity reqGetCloseMainActivity);

    CompletableFuture<Void> fireGetCloseMainActivity(ReqGetCloseMainActivity reqGetCloseMainActivity, Duration duration);

    CompletableFuture<Void> fireGetCloseMainActivity(Duration duration);

    CompletableFuture<Void> fireGetCopyFiles();

    CompletableFuture<Void> fireGetCopyFiles(ReqGetCopyFiles reqGetCopyFiles);

    CompletableFuture<Void> fireGetCopyFiles(ReqGetCopyFiles reqGetCopyFiles, Duration duration);

    CompletableFuture<Void> fireGetCopyFiles(Duration duration);

    CompletableFuture<Void> fireGetCurrentLocation();

    CompletableFuture<Void> fireGetCurrentLocation(ReqGetCurrentLocation reqGetCurrentLocation);

    CompletableFuture<Void> fireGetCurrentLocation(ReqGetCurrentLocation reqGetCurrentLocation, Duration duration);

    CompletableFuture<Void> fireGetCurrentLocation(Duration duration);

    CompletableFuture<Void> fireGetDBDataIntegrityCheck();

    CompletableFuture<Void> fireGetDBDataIntegrityCheck(ReqGetDBDataIntegrityCheck reqGetDBDataIntegrityCheck);

    CompletableFuture<Void> fireGetDBDataIntegrityCheck(ReqGetDBDataIntegrityCheck reqGetDBDataIntegrityCheck, Duration duration);

    CompletableFuture<Void> fireGetDBDataIntegrityCheck(Duration duration);

    CompletableFuture<Void> fireGetDayNightMode();

    CompletableFuture<Void> fireGetDayNightMode(ReqGetDayNightMode reqGetDayNightMode);

    CompletableFuture<Void> fireGetDayNightMode(ReqGetDayNightMode reqGetDayNightMode, Duration duration);

    CompletableFuture<Void> fireGetDayNightMode(Duration duration);

    CompletableFuture<Void> fireGetDelegateSystemEvent();

    CompletableFuture<Void> fireGetDelegateSystemEvent(ReqGetDelegateSystemEvent reqGetDelegateSystemEvent);

    CompletableFuture<Void> fireGetDelegateSystemEvent(ReqGetDelegateSystemEvent reqGetDelegateSystemEvent, Duration duration);

    CompletableFuture<Void> fireGetDelegateSystemEvent(Duration duration);

    CompletableFuture<Void> fireGetDeleteAllDBData();

    CompletableFuture<Void> fireGetDeleteAllDBData(ReqGetDeleteAllDBData reqGetDeleteAllDBData);

    CompletableFuture<Void> fireGetDeleteAllDBData(ReqGetDeleteAllDBData reqGetDeleteAllDBData, Duration duration);

    CompletableFuture<Void> fireGetDeleteAllDBData(Duration duration);

    CompletableFuture<Void> fireGetDeleteFile();

    CompletableFuture<Void> fireGetDeleteFile(ReqGetDeleteFile reqGetDeleteFile);

    CompletableFuture<Void> fireGetDeleteFile(ReqGetDeleteFile reqGetDeleteFile, Duration duration);

    CompletableFuture<Void> fireGetDeleteFile(Duration duration);

    CompletableFuture<Void> fireGetExportDirectoryToUDisk();

    CompletableFuture<Void> fireGetExportDirectoryToUDisk(ReqGetExportDirectoryToUDisk reqGetExportDirectoryToUDisk);

    CompletableFuture<Void> fireGetExportDirectoryToUDisk(ReqGetExportDirectoryToUDisk reqGetExportDirectoryToUDisk, Duration duration);

    CompletableFuture<Void> fireGetExportDirectoryToUDisk(Duration duration);

    CompletableFuture<Void> fireGetExtractFiles();

    CompletableFuture<Void> fireGetExtractFiles(ReqGetExtractFiles reqGetExtractFiles);

    CompletableFuture<Void> fireGetExtractFiles(ReqGetExtractFiles reqGetExtractFiles, Duration duration);

    CompletableFuture<Void> fireGetExtractFiles(Duration duration);

    CompletableFuture<Void> fireGetGetLowFuelStatus();

    CompletableFuture<Void> fireGetGetLowFuelStatus(ReqGetGetLowFuelStatus reqGetGetLowFuelStatus);

    CompletableFuture<Void> fireGetGetLowFuelStatus(ReqGetGetLowFuelStatus reqGetGetLowFuelStatus, Duration duration);

    CompletableFuture<Void> fireGetGetLowFuelStatus(Duration duration);

    CompletableFuture<Void> fireGetGuidanceInfoUpdate();

    CompletableFuture<Void> fireGetGuidanceInfoUpdate(ReqGetGuidanceInfoUpdate reqGetGuidanceInfoUpdate);

    CompletableFuture<Void> fireGetGuidanceInfoUpdate(ReqGetGuidanceInfoUpdate reqGetGuidanceInfoUpdate, Duration duration);

    CompletableFuture<Void> fireGetGuidanceInfoUpdate(Duration duration);

    CompletableFuture<Void> fireGetGuideInfo();

    CompletableFuture<Void> fireGetGuideInfo(ReqGetGuideInfo reqGetGuideInfo);

    CompletableFuture<Void> fireGetGuideInfo(ReqGetGuideInfo reqGetGuideInfo, Duration duration);

    CompletableFuture<Void> fireGetGuideInfo(Duration duration);

    CompletableFuture<Void> fireGetImmersionStatus();

    CompletableFuture<Void> fireGetImmersionStatus(ReqGetImmersionStatus reqGetImmersionStatus);

    CompletableFuture<Void> fireGetImmersionStatus(ReqGetImmersionStatus reqGetImmersionStatus, Duration duration);

    CompletableFuture<Void> fireGetImmersionStatus(Duration duration);

    CompletableFuture<Void> fireGetLaunchApp();

    CompletableFuture<Void> fireGetLaunchApp(ReqGetLaunchApp reqGetLaunchApp);

    CompletableFuture<Void> fireGetLaunchApp(ReqGetLaunchApp reqGetLaunchApp, Duration duration);

    CompletableFuture<Void> fireGetLaunchApp(Duration duration);

    CompletableFuture<Void> fireGetLoadURL();

    CompletableFuture<Void> fireGetLoadURL(ReqGetLoadURL reqGetLoadURL);

    CompletableFuture<Void> fireGetLoadURL(ReqGetLoadURL reqGetLoadURL, Duration duration);

    CompletableFuture<Void> fireGetLoadURL(Duration duration);

    CompletableFuture<Void> fireGetLocalDBVersionInfo();

    CompletableFuture<Void> fireGetLocalDBVersionInfo(ReqGetLocalDBVersionInfo reqGetLocalDBVersionInfo);

    CompletableFuture<Void> fireGetLocalDBVersionInfo(ReqGetLocalDBVersionInfo reqGetLocalDBVersionInfo, Duration duration);

    CompletableFuture<Void> fireGetLocalDBVersionInfo(Duration duration);

    CompletableFuture<Void> fireGetMapAppStatus();

    CompletableFuture<Void> fireGetMapAppStatus(ReqGetMapAppStatus reqGetMapAppStatus);

    CompletableFuture<Void> fireGetMapAppStatus(ReqGetMapAppStatus reqGetMapAppStatus, Duration duration);

    CompletableFuture<Void> fireGetMapAppStatus(Duration duration);

    CompletableFuture<Void> fireGetMapScaleLevel();

    CompletableFuture<Void> fireGetMapScaleLevel(ReqGetMapScaleLevel reqGetMapScaleLevel);

    CompletableFuture<Void> fireGetMapScaleLevel(ReqGetMapScaleLevel reqGetMapScaleLevel, Duration duration);

    CompletableFuture<Void> fireGetMapScaleLevel(Duration duration);

    CompletableFuture<Void> fireGetMapSettingStatus();

    CompletableFuture<Void> fireGetMapSettingStatus(ReqGetMapSettingStatus reqGetMapSettingStatus);

    CompletableFuture<Void> fireGetMapSettingStatus(ReqGetMapSettingStatus reqGetMapSettingStatus, Duration duration);

    CompletableFuture<Void> fireGetMapSettingStatus(Duration duration);

    CompletableFuture<Void> fireGetMapViewMode();

    CompletableFuture<Void> fireGetMapViewMode(ReqGetMapViewMode reqGetMapViewMode);

    CompletableFuture<Void> fireGetMapViewMode(ReqGetMapViewMode reqGetMapViewMode, Duration duration);

    CompletableFuture<Void> fireGetMapViewMode(Duration duration);

    CompletableFuture<Void> fireGetMonitorCOMMStatus();

    CompletableFuture<Void> fireGetMonitorCOMMStatus(ReqGetMonitorCOMMStatus reqGetMonitorCOMMStatus);

    CompletableFuture<Void> fireGetMonitorCOMMStatus(ReqGetMonitorCOMMStatus reqGetMonitorCOMMStatus, Duration duration);

    CompletableFuture<Void> fireGetMonitorCOMMStatus(Duration duration);

    CompletableFuture<Void> fireGetNaviDayNightStatusChange();

    CompletableFuture<Void> fireGetNaviDayNightStatusChange(ReqGetNaviDayNightStatusChange reqGetNaviDayNightStatusChange);

    CompletableFuture<Void> fireGetNaviDayNightStatusChange(ReqGetNaviDayNightStatusChange reqGetNaviDayNightStatusChange, Duration duration);

    CompletableFuture<Void> fireGetNaviDayNightStatusChange(Duration duration);

    CompletableFuture<Void> fireGetNaviMapViewMode();

    CompletableFuture<Void> fireGetNaviMapViewMode(ReqGetNaviMapViewMode reqGetNaviMapViewMode);

    CompletableFuture<Void> fireGetNaviMapViewMode(ReqGetNaviMapViewMode reqGetNaviMapViewMode, Duration duration);

    CompletableFuture<Void> fireGetNaviMapViewMode(Duration duration);

    CompletableFuture<Void> fireGetNaviMuteModeComm();

    CompletableFuture<Void> fireGetNaviMuteModeComm(ReqGetNaviMuteModeComm reqGetNaviMuteModeComm);

    CompletableFuture<Void> fireGetNaviMuteModeComm(ReqGetNaviMuteModeComm reqGetNaviMuteModeComm, Duration duration);

    CompletableFuture<Void> fireGetNaviMuteModeComm(Duration duration);

    CompletableFuture<Void> fireGetNaviPresentationInfo();

    CompletableFuture<Void> fireGetNaviPresentationInfo(ReqGetNaviPresentationInfo reqGetNaviPresentationInfo);

    CompletableFuture<Void> fireGetNaviPresentationInfo(ReqGetNaviPresentationInfo reqGetNaviPresentationInfo, Duration duration);

    CompletableFuture<Void> fireGetNaviPresentationInfo(Duration duration);

    CompletableFuture<Void> fireGetNaviPresentationMgs();

    CompletableFuture<Void> fireGetNaviPresentationMgs(ReqGetNaviPresentationMgs reqGetNaviPresentationMgs);

    CompletableFuture<Void> fireGetNaviPresentationMgs(ReqGetNaviPresentationMgs reqGetNaviPresentationMgs, Duration duration);

    CompletableFuture<Void> fireGetNaviPresentationMgs(Duration duration);

    CompletableFuture<Void> fireGetNaviSoftware();

    CompletableFuture<Void> fireGetNaviSoftware(ReqGetNaviSoftware reqGetNaviSoftware);

    CompletableFuture<Void> fireGetNaviSoftware(ReqGetNaviSoftware reqGetNaviSoftware, Duration duration);

    CompletableFuture<Void> fireGetNaviSoftware(Duration duration);

    CompletableFuture<Void> fireGetNaviSoftwareVersionInfo();

    CompletableFuture<Void> fireGetNaviSoftwareVersionInfo(ReqGetNaviSoftwareVersionInfo reqGetNaviSoftwareVersionInfo);

    CompletableFuture<Void> fireGetNaviSoftwareVersionInfo(ReqGetNaviSoftwareVersionInfo reqGetNaviSoftwareVersionInfo, Duration duration);

    CompletableFuture<Void> fireGetNaviSoftwareVersionInfo(Duration duration);

    CompletableFuture<Void> fireGetNaviStatusChanged();

    CompletableFuture<Void> fireGetNaviStatusChanged(ReqGetNaviStatusChanged reqGetNaviStatusChanged);

    CompletableFuture<Void> fireGetNaviStatusChanged(ReqGetNaviStatusChanged reqGetNaviStatusChanged, Duration duration);

    CompletableFuture<Void> fireGetNaviStatusChanged(Duration duration);

    CompletableFuture<Void> fireGetNaviSwitchFullView();

    CompletableFuture<Void> fireGetNaviSwitchFullView(ReqGetNaviSwitchFullView reqGetNaviSwitchFullView);

    CompletableFuture<Void> fireGetNaviSwitchFullView(ReqGetNaviSwitchFullView reqGetNaviSwitchFullView, Duration duration);

    CompletableFuture<Void> fireGetNaviSwitchFullView(Duration duration);

    CompletableFuture<Void> fireGetNavigationActivityStatus();

    CompletableFuture<Void> fireGetNavigationActivityStatus(ReqGetNavigationActivityStatus reqGetNavigationActivityStatus);

    CompletableFuture<Void> fireGetNavigationActivityStatus(ReqGetNavigationActivityStatus reqGetNavigationActivityStatus, Duration duration);

    CompletableFuture<Void> fireGetNavigationActivityStatus(Duration duration);

    CompletableFuture<Void> fireGetNavigationRunningStatus();

    CompletableFuture<Void> fireGetNavigationRunningStatus(ReqGetNavigationRunningStatus reqGetNavigationRunningStatus);

    CompletableFuture<Void> fireGetNavigationRunningStatus(ReqGetNavigationRunningStatus reqGetNavigationRunningStatus, Duration duration);

    CompletableFuture<Void> fireGetNavigationRunningStatus(Duration duration);

    CompletableFuture<Void> fireGetNotifyOpenGPS();

    CompletableFuture<Void> fireGetNotifyOpenGPS(ReqGetNotifyOpenGPS reqGetNotifyOpenGPS);

    CompletableFuture<Void> fireGetNotifyOpenGPS(ReqGetNotifyOpenGPS reqGetNotifyOpenGPS, Duration duration);

    CompletableFuture<Void> fireGetNotifyOpenGPS(Duration duration);

    CompletableFuture<Void> fireGetObserveWeatherInfo2System();

    CompletableFuture<Void> fireGetObserveWeatherInfo2System(ReqGetObserveWeatherInfo2System reqGetObserveWeatherInfo2System);

    CompletableFuture<Void> fireGetObserveWeatherInfo2System(ReqGetObserveWeatherInfo2System reqGetObserveWeatherInfo2System, Duration duration);

    CompletableFuture<Void> fireGetObserveWeatherInfo2System(Duration duration);

    CompletableFuture<Void> fireGetOnKeyDown();

    CompletableFuture<Void> fireGetOnKeyDown(ReqGetOnKeyDown reqGetOnKeyDown);

    CompletableFuture<Void> fireGetOnKeyDown(ReqGetOnKeyDown reqGetOnKeyDown, Duration duration);

    CompletableFuture<Void> fireGetOnKeyDown(Duration duration);

    CompletableFuture<Void> fireGetOnNaviRemainTimeDistance();

    CompletableFuture<Void> fireGetOnNaviRemainTimeDistance(ReqGetOnNaviRemainTimeDistance reqGetOnNaviRemainTimeDistance);

    CompletableFuture<Void> fireGetOnNaviRemainTimeDistance(ReqGetOnNaviRemainTimeDistance reqGetOnNaviRemainTimeDistance, Duration duration);

    CompletableFuture<Void> fireGetOnNaviRemainTimeDistance(Duration duration);

    CompletableFuture<Void> fireGetPeripheralStatus();

    CompletableFuture<Void> fireGetPeripheralStatus(ReqGetPeripheralStatus reqGetPeripheralStatus);

    CompletableFuture<Void> fireGetPeripheralStatus(ReqGetPeripheralStatus reqGetPeripheralStatus, Duration duration);

    CompletableFuture<Void> fireGetPeripheralStatus(Duration duration);

    CompletableFuture<Void> fireGetPlayBeepTone();

    CompletableFuture<Void> fireGetPlayBeepTone(ReqGetPlayBeepTone reqGetPlayBeepTone);

    CompletableFuture<Void> fireGetPlayBeepTone(ReqGetPlayBeepTone reqGetPlayBeepTone, Duration duration);

    CompletableFuture<Void> fireGetPlayBeepTone(Duration duration);

    CompletableFuture<Void> fireGetPlaySoundEffect();

    CompletableFuture<Void> fireGetPlaySoundEffect(ReqGetPlaySoundEffect reqGetPlaySoundEffect);

    CompletableFuture<Void> fireGetPlaySoundEffect(ReqGetPlaySoundEffect reqGetPlaySoundEffect, Duration duration);

    CompletableFuture<Void> fireGetPlaySoundEffect(Duration duration);

    CompletableFuture<Void> fireGetPostCustomEvent();

    CompletableFuture<Void> fireGetPostCustomEvent(ReqGetPostCustomEvent reqGetPostCustomEvent);

    CompletableFuture<Void> fireGetPostCustomEvent(ReqGetPostCustomEvent reqGetPostCustomEvent, Duration duration);

    CompletableFuture<Void> fireGetPostCustomEvent(Duration duration);

    CompletableFuture<Void> fireGetReadFile();

    CompletableFuture<Void> fireGetReadFile(ReqGetReadFile reqGetReadFile);

    CompletableFuture<Void> fireGetReadFile(ReqGetReadFile reqGetReadFile, Duration duration);

    CompletableFuture<Void> fireGetReadFile(Duration duration);

    CompletableFuture<Void> fireGetRequestDeviceId();

    CompletableFuture<Void> fireGetRequestDeviceId(ReqGetRequestDeviceId reqGetRequestDeviceId);

    CompletableFuture<Void> fireGetRequestDeviceId(ReqGetRequestDeviceId reqGetRequestDeviceId, Duration duration);

    CompletableFuture<Void> fireGetRequestDeviceId(Duration duration);

    CompletableFuture<Void> fireGetRequestEnduranceMileage();

    CompletableFuture<Void> fireGetRequestEnduranceMileage(ReqGetRequestEnduranceMileage reqGetRequestEnduranceMileage);

    CompletableFuture<Void> fireGetRequestEnduranceMileage(ReqGetRequestEnduranceMileage reqGetRequestEnduranceMileage, Duration duration);

    CompletableFuture<Void> fireGetRequestEnduranceMileage(Duration duration);

    CompletableFuture<Void> fireGetRequestExpiredDate();

    CompletableFuture<Void> fireGetRequestExpiredDate(ReqGetRequestExpiredDate reqGetRequestExpiredDate);

    CompletableFuture<Void> fireGetRequestExpiredDate(ReqGetRequestExpiredDate reqGetRequestExpiredDate, Duration duration);

    CompletableFuture<Void> fireGetRequestExpiredDate(Duration duration);

    CompletableFuture<Void> fireGetRequestInstallationPath();

    CompletableFuture<Void> fireGetRequestInstallationPath(ReqGetRequestInstallationPath reqGetRequestInstallationPath);

    CompletableFuture<Void> fireGetRequestInstallationPath(ReqGetRequestInstallationPath reqGetRequestInstallationPath, Duration duration);

    CompletableFuture<Void> fireGetRequestInstallationPath(Duration duration);

    CompletableFuture<Void> fireGetRequestUpdateExternalStoragePath();

    CompletableFuture<Void> fireGetRequestUpdateExternalStoragePath(ReqGetRequestUpdateExternalStoragePath reqGetRequestUpdateExternalStoragePath);

    CompletableFuture<Void> fireGetRequestUpdateExternalStoragePath(ReqGetRequestUpdateExternalStoragePath reqGetRequestUpdateExternalStoragePath, Duration duration);

    CompletableFuture<Void> fireGetRequestUpdateExternalStoragePath(Duration duration);

    CompletableFuture<Void> fireGetResponseExpiredDate();

    CompletableFuture<Void> fireGetResponseExpiredDate(ReqGetResponseExpiredDate reqGetResponseExpiredDate);

    CompletableFuture<Void> fireGetResponseExpiredDate(ReqGetResponseExpiredDate reqGetResponseExpiredDate, Duration duration);

    CompletableFuture<Void> fireGetResponseExpiredDate(Duration duration);

    CompletableFuture<Void> fireGetShowDiagnoseSetting();

    CompletableFuture<Void> fireGetShowDiagnoseSetting(ReqGetShowDiagnoseSetting reqGetShowDiagnoseSetting);

    CompletableFuture<Void> fireGetShowDiagnoseSetting(ReqGetShowDiagnoseSetting reqGetShowDiagnoseSetting, Duration duration);

    CompletableFuture<Void> fireGetShowDiagnoseSetting(Duration duration);

    CompletableFuture<Void> fireGetShowToastMessage();

    CompletableFuture<Void> fireGetShowToastMessage(ReqGetShowToastMessage reqGetShowToastMessage);

    CompletableFuture<Void> fireGetShowToastMessage(ReqGetShowToastMessage reqGetShowToastMessage, Duration duration);

    CompletableFuture<Void> fireGetShowToastMessage(Duration duration);

    CompletableFuture<Void> fireGetSoftwareVersionInfo();

    CompletableFuture<Void> fireGetSoftwareVersionInfo(ReqGetSoftwareVersionInfo reqGetSoftwareVersionInfo);

    CompletableFuture<Void> fireGetSoftwareVersionInfo(ReqGetSoftwareVersionInfo reqGetSoftwareVersionInfo, Duration duration);

    CompletableFuture<Void> fireGetSoftwareVersionInfo(Duration duration);

    CompletableFuture<Void> fireGetSpeak();

    CompletableFuture<Void> fireGetSpeak(ReqGetSpeak reqGetSpeak);

    CompletableFuture<Void> fireGetSpeak(ReqGetSpeak reqGetSpeak, Duration duration);

    CompletableFuture<Void> fireGetSpeak(Duration duration);

    CompletableFuture<Void> fireGetStorageSpaceStatus();

    CompletableFuture<Void> fireGetStorageSpaceStatus(ReqGetStorageSpaceStatus reqGetStorageSpaceStatus);

    CompletableFuture<Void> fireGetStorageSpaceStatus(ReqGetStorageSpaceStatus reqGetStorageSpaceStatus, Duration duration);

    CompletableFuture<Void> fireGetStorageSpaceStatus(Duration duration);

    CompletableFuture<Void> fireGetSwitchArgumentedReality();

    CompletableFuture<Void> fireGetSwitchArgumentedReality(ReqGetSwitchArgumentedReality reqGetSwitchArgumentedReality);

    CompletableFuture<Void> fireGetSwitchArgumentedReality(ReqGetSwitchArgumentedReality reqGetSwitchArgumentedReality, Duration duration);

    CompletableFuture<Void> fireGetSwitchArgumentedReality(Duration duration);

    CompletableFuture<Void> fireGetSwitchNaviToBackground();

    CompletableFuture<Void> fireGetSwitchNaviToBackground(ReqGetSwitchNaviToBackground reqGetSwitchNaviToBackground);

    CompletableFuture<Void> fireGetSwitchNaviToBackground(ReqGetSwitchNaviToBackground reqGetSwitchNaviToBackground, Duration duration);

    CompletableFuture<Void> fireGetSwitchNaviToBackground(Duration duration);

    CompletableFuture<Void> fireGetSwitchTraceLog();

    CompletableFuture<Void> fireGetSwitchTraceLog(ReqGetSwitchTraceLog reqGetSwitchTraceLog);

    CompletableFuture<Void> fireGetSwitchTraceLog(ReqGetSwitchTraceLog reqGetSwitchTraceLog, Duration duration);

    CompletableFuture<Void> fireGetSwitchTraceLog(Duration duration);

    CompletableFuture<Void> fireGetTraceLog();

    CompletableFuture<Void> fireGetTraceLog(ReqGetTraceLog reqGetTraceLog);

    CompletableFuture<Void> fireGetTraceLog(ReqGetTraceLog reqGetTraceLog, Duration duration);

    CompletableFuture<Void> fireGetTraceLog(Duration duration);

    CompletableFuture<Void> fireGetTrafficInfoState();

    CompletableFuture<Void> fireGetTrafficInfoState(ReqGetTrafficInfoState reqGetTrafficInfoState);

    CompletableFuture<Void> fireGetTrafficInfoState(ReqGetTrafficInfoState reqGetTrafficInfoState, Duration duration);

    CompletableFuture<Void> fireGetTrafficInfoState(Duration duration);

    CompletableFuture<Void> fireGetWriteFile();

    CompletableFuture<Void> fireGetWriteFile(ReqGetWriteFile reqGetWriteFile);

    CompletableFuture<Void> fireGetWriteFile(ReqGetWriteFile reqGetWriteFile, Duration duration);

    CompletableFuture<Void> fireGetWriteFile(Duration duration);

    CompletableFuture<Void> fireHMIFocusChanged(ReqHMIFocusChanged.Arguments arguments);

    CompletableFuture<Void> fireHMIFocusChanged(ReqHMIFocusChanged.Arguments arguments, Duration duration);

    CompletableFuture<Void> fireHMIFocusChanged(ReqHMIFocusChanged reqHMIFocusChanged);

    CompletableFuture<Void> fireHMIFocusChanged(ReqHMIFocusChanged reqHMIFocusChanged, Duration duration);

    CompletableFuture<Void> fireMapZoomInOut(ReqMapZoomInOut.Arguments arguments);

    CompletableFuture<Void> fireMapZoomInOut(ReqMapZoomInOut.Arguments arguments, Duration duration);

    CompletableFuture<Void> fireMapZoomInOut(ReqMapZoomInOut reqMapZoomInOut);

    CompletableFuture<Void> fireMapZoomInOut(ReqMapZoomInOut reqMapZoomInOut, Duration duration);

    CompletableFuture<Void> fireMigrateNaviHMIPage(ReqMigrateNaviHMIPage.Arguments arguments);

    CompletableFuture<Void> fireMigrateNaviHMIPage(ReqMigrateNaviHMIPage.Arguments arguments, Duration duration);

    CompletableFuture<Void> fireMigrateNaviHMIPage(ReqMigrateNaviHMIPage reqMigrateNaviHMIPage);

    CompletableFuture<Void> fireMigrateNaviHMIPage(ReqMigrateNaviHMIPage reqMigrateNaviHMIPage, Duration duration);

    CompletableFuture<Void> fireNaviAppExitEventInfo(ReqNaviAppExitEventInfo.Arguments arguments);

    CompletableFuture<Void> fireNaviAppExitEventInfo(ReqNaviAppExitEventInfo.Arguments arguments, Duration duration);

    CompletableFuture<Void> fireNaviAppExitEventInfo(ReqNaviAppExitEventInfo reqNaviAppExitEventInfo);

    CompletableFuture<Void> fireNaviAppExitEventInfo(ReqNaviAppExitEventInfo reqNaviAppExitEventInfo, Duration duration);

    CompletableFuture<Void> fireNaviAppStartupEventInfo(ReqNaviAppStartupEventInfo.Arguments arguments);

    CompletableFuture<Void> fireNaviAppStartupEventInfo(ReqNaviAppStartupEventInfo.Arguments arguments, Duration duration);

    CompletableFuture<Void> fireNaviAppStartupEventInfo(ReqNaviAppStartupEventInfo reqNaviAppStartupEventInfo);

    CompletableFuture<Void> fireNaviAppStartupEventInfo(ReqNaviAppStartupEventInfo reqNaviAppStartupEventInfo, Duration duration);

    CompletableFuture<Void> fireOnAccessCodingAvailable(ReqOnAccessCodingAvailable.Arguments arguments);

    CompletableFuture<Void> fireOnAccessCodingAvailable(ReqOnAccessCodingAvailable.Arguments arguments, Duration duration);

    CompletableFuture<Void> fireOnAccessCodingAvailable(ReqOnAccessCodingAvailable reqOnAccessCodingAvailable);

    CompletableFuture<Void> fireOnAccessCodingAvailable(ReqOnAccessCodingAvailable reqOnAccessCodingAvailable, Duration duration);

    CompletableFuture<Void> fireOnCopyFiles(ReqOnCopyFiles.Arguments arguments);

    CompletableFuture<Void> fireOnCopyFiles(ReqOnCopyFiles.Arguments arguments, Duration duration);

    CompletableFuture<Void> fireOnCopyFiles(ReqOnCopyFiles reqOnCopyFiles);

    CompletableFuture<Void> fireOnCopyFiles(ReqOnCopyFiles reqOnCopyFiles, Duration duration);

    CompletableFuture<Void> fireOnDBDataIntegrityCheck(ReqOnDBDataIntegrityCheck.Arguments arguments);

    CompletableFuture<Void> fireOnDBDataIntegrityCheck(ReqOnDBDataIntegrityCheck.Arguments arguments, Duration duration);

    CompletableFuture<Void> fireOnDBDataIntegrityCheck(ReqOnDBDataIntegrityCheck reqOnDBDataIntegrityCheck);

    CompletableFuture<Void> fireOnDBDataIntegrityCheck(ReqOnDBDataIntegrityCheck reqOnDBDataIntegrityCheck, Duration duration);

    CompletableFuture<Void> fireOnDeleteAllDBData(ReqOnDeleteAllDBData.Arguments arguments);

    CompletableFuture<Void> fireOnDeleteAllDBData(ReqOnDeleteAllDBData.Arguments arguments, Duration duration);

    CompletableFuture<Void> fireOnDeleteAllDBData(ReqOnDeleteAllDBData reqOnDeleteAllDBData);

    CompletableFuture<Void> fireOnDeleteAllDBData(ReqOnDeleteAllDBData reqOnDeleteAllDBData, Duration duration);

    CompletableFuture<Void> fireOnDeleteFile(ReqOnDeleteFile.Arguments arguments);

    CompletableFuture<Void> fireOnDeleteFile(ReqOnDeleteFile.Arguments arguments, Duration duration);

    CompletableFuture<Void> fireOnDeleteFile(ReqOnDeleteFile reqOnDeleteFile);

    CompletableFuture<Void> fireOnDeleteFile(ReqOnDeleteFile reqOnDeleteFile, Duration duration);

    CompletableFuture<Void> fireOnExportDirectoryToUDisk(ReqOnExportDirectoryToUDisk.Arguments arguments);

    CompletableFuture<Void> fireOnExportDirectoryToUDisk(ReqOnExportDirectoryToUDisk.Arguments arguments, Duration duration);

    CompletableFuture<Void> fireOnExportDirectoryToUDisk(ReqOnExportDirectoryToUDisk reqOnExportDirectoryToUDisk);

    CompletableFuture<Void> fireOnExportDirectoryToUDisk(ReqOnExportDirectoryToUDisk reqOnExportDirectoryToUDisk, Duration duration);

    CompletableFuture<Void> fireOnExtractFiles(ReqOnExtractFiles.Arguments arguments);

    CompletableFuture<Void> fireOnExtractFiles(ReqOnExtractFiles.Arguments arguments, Duration duration);

    CompletableFuture<Void> fireOnExtractFiles(ReqOnExtractFiles reqOnExtractFiles);

    CompletableFuture<Void> fireOnExtractFiles(ReqOnExtractFiles reqOnExtractFiles, Duration duration);

    CompletableFuture<Void> fireOnLoadURL(ReqOnLoadURL.Arguments arguments);

    CompletableFuture<Void> fireOnLoadURL(ReqOnLoadURL.Arguments arguments, Duration duration);

    CompletableFuture<Void> fireOnLoadURL(ReqOnLoadURL reqOnLoadURL);

    CompletableFuture<Void> fireOnLoadURL(ReqOnLoadURL reqOnLoadURL, Duration duration);

    CompletableFuture<Void> fireOnReadFile(ReqOnReadFile.Arguments arguments);

    CompletableFuture<Void> fireOnReadFile(ReqOnReadFile.Arguments arguments, Duration duration);

    CompletableFuture<Void> fireOnReadFile(ReqOnReadFile reqOnReadFile);

    CompletableFuture<Void> fireOnReadFile(ReqOnReadFile reqOnReadFile, Duration duration);

    CompletableFuture<Void> fireOnStopOneCoreStatus(ReqOnStopOneCoreStatus.Arguments arguments);

    CompletableFuture<Void> fireOnStopOneCoreStatus(ReqOnStopOneCoreStatus.Arguments arguments, Duration duration);

    CompletableFuture<Void> fireOnStopOneCoreStatus(ReqOnStopOneCoreStatus reqOnStopOneCoreStatus);

    CompletableFuture<Void> fireOnStopOneCoreStatus(ReqOnStopOneCoreStatus reqOnStopOneCoreStatus, Duration duration);

    CompletableFuture<Void> fireOnWriteFile(ReqOnWriteFile.Arguments arguments);

    CompletableFuture<Void> fireOnWriteFile(ReqOnWriteFile.Arguments arguments, Duration duration);

    CompletableFuture<Void> fireOnWriteFile(ReqOnWriteFile reqOnWriteFile);

    CompletableFuture<Void> fireOnWriteFile(ReqOnWriteFile reqOnWriteFile, Duration duration);

    CompletableFuture<Void> fireReqNaviGuideStart(ReqReqNaviGuideStart.Arguments arguments);

    CompletableFuture<Void> fireReqNaviGuideStart(ReqReqNaviGuideStart.Arguments arguments, Duration duration);

    CompletableFuture<Void> fireReqNaviGuideStart(ReqReqNaviGuideStart reqReqNaviGuideStart);

    CompletableFuture<Void> fireReqNaviGuideStart(ReqReqNaviGuideStart reqReqNaviGuideStart, Duration duration);

    CompletableFuture<Void> fireReqNaviGuideStop();

    CompletableFuture<Void> fireReqNaviGuideStop(ReqReqNaviGuideStop reqReqNaviGuideStop);

    CompletableFuture<Void> fireReqNaviGuideStop(ReqReqNaviGuideStop reqReqNaviGuideStop, Duration duration);

    CompletableFuture<Void> fireReqNaviGuideStop(Duration duration);

    CompletableFuture<Void> fireReqNaviMapViewMode(ReqReqNaviMapViewMode.Arguments arguments);

    CompletableFuture<Void> fireReqNaviMapViewMode(ReqReqNaviMapViewMode.Arguments arguments, Duration duration);

    CompletableFuture<Void> fireReqNaviMapViewMode(ReqReqNaviMapViewMode reqReqNaviMapViewMode);

    CompletableFuture<Void> fireReqNaviMapViewMode(ReqReqNaviMapViewMode reqReqNaviMapViewMode, Duration duration);

    CompletableFuture<Void> fireReqNaviRemainTimeDistance();

    CompletableFuture<Void> fireReqNaviRemainTimeDistance(ReqReqNaviRemainTimeDistance reqReqNaviRemainTimeDistance);

    CompletableFuture<Void> fireReqNaviRemainTimeDistance(ReqReqNaviRemainTimeDistance reqReqNaviRemainTimeDistance, Duration duration);

    CompletableFuture<Void> fireReqNaviRemainTimeDistance(Duration duration);

    CompletableFuture<Void> fireReqNaviSwitchFullView(ReqReqNaviSwitchFullView.Arguments arguments);

    CompletableFuture<Void> fireReqNaviSwitchFullView(ReqReqNaviSwitchFullView.Arguments arguments, Duration duration);

    CompletableFuture<Void> fireReqNaviSwitchFullView(ReqReqNaviSwitchFullView reqReqNaviSwitchFullView);

    CompletableFuture<Void> fireReqNaviSwitchFullView(ReqReqNaviSwitchFullView reqReqNaviSwitchFullView, Duration duration);

    CompletableFuture<Void> fireReqNaviZoomIn(ReqReqNaviZoomIn.Arguments arguments);

    CompletableFuture<Void> fireReqNaviZoomIn(ReqReqNaviZoomIn.Arguments arguments, Duration duration);

    CompletableFuture<Void> fireReqNaviZoomIn(ReqReqNaviZoomIn reqReqNaviZoomIn);

    CompletableFuture<Void> fireReqNaviZoomIn(ReqReqNaviZoomIn reqReqNaviZoomIn, Duration duration);

    CompletableFuture<Void> fireReqNaviZoomOut(ReqReqNaviZoomOut.Arguments arguments);

    CompletableFuture<Void> fireReqNaviZoomOut(ReqReqNaviZoomOut.Arguments arguments, Duration duration);

    CompletableFuture<Void> fireReqNaviZoomOut(ReqReqNaviZoomOut reqReqNaviZoomOut);

    CompletableFuture<Void> fireReqNaviZoomOut(ReqReqNaviZoomOut reqReqNaviZoomOut, Duration duration);

    CompletableFuture<Void> fireReqSwitchDayNightMode(ReqReqSwitchDayNightMode.Arguments arguments);

    CompletableFuture<Void> fireReqSwitchDayNightMode(ReqReqSwitchDayNightMode.Arguments arguments, Duration duration);

    CompletableFuture<Void> fireReqSwitchDayNightMode(ReqReqSwitchDayNightMode reqReqSwitchDayNightMode);

    CompletableFuture<Void> fireReqSwitchDayNightMode(ReqReqSwitchDayNightMode reqReqSwitchDayNightMode, Duration duration);

    CompletableFuture<Void> fireRequestRestoreFactorySetting();

    CompletableFuture<Void> fireRequestRestoreFactorySetting(ReqRequestRestoreFactorySetting reqRequestRestoreFactorySetting);

    CompletableFuture<Void> fireRequestRestoreFactorySetting(ReqRequestRestoreFactorySetting reqRequestRestoreFactorySetting, Duration duration);

    CompletableFuture<Void> fireRequestRestoreFactorySetting(Duration duration);

    CompletableFuture<Void> fireSetBatteryCapacityWH(ReqSetBatteryCapacityWH.Arguments arguments);

    CompletableFuture<Void> fireSetBatteryCapacityWH(ReqSetBatteryCapacityWH.Arguments arguments, Duration duration);

    CompletableFuture<Void> fireSetBatteryCapacityWH(ReqSetBatteryCapacityWH reqSetBatteryCapacityWH);

    CompletableFuture<Void> fireSetBatteryCapacityWH(ReqSetBatteryCapacityWH reqSetBatteryCapacityWH, Duration duration);

    CompletableFuture<Void> fireSetBrightnessValue(ReqSetBrightnessValue.Arguments arguments);

    CompletableFuture<Void> fireSetBrightnessValue(ReqSetBrightnessValue.Arguments arguments, Duration duration);

    CompletableFuture<Void> fireSetBrightnessValue(ReqSetBrightnessValue reqSetBrightnessValue);

    CompletableFuture<Void> fireSetBrightnessValue(ReqSetBrightnessValue reqSetBrightnessValue, Duration duration);

    CompletableFuture<Void> fireSetCarType(ReqSetCarType.Arguments arguments);

    CompletableFuture<Void> fireSetCarType(ReqSetCarType.Arguments arguments, Duration duration);

    CompletableFuture<Void> fireSetCarType(ReqSetCarType reqSetCarType);

    CompletableFuture<Void> fireSetCarType(ReqSetCarType reqSetCarType, Duration duration);

    CompletableFuture<Void> fireSetChargingPortType(ReqSetChargingPortType.Arguments arguments);

    CompletableFuture<Void> fireSetChargingPortType(ReqSetChargingPortType.Arguments arguments, Duration duration);

    CompletableFuture<Void> fireSetChargingPortType(ReqSetChargingPortType reqSetChargingPortType);

    CompletableFuture<Void> fireSetChargingPortType(ReqSetChargingPortType reqSetChargingPortType, Duration duration);

    CompletableFuture<Void> fireSetClusterSurfaceStatus(ReqSetClusterSurfaceStatus.Arguments arguments);

    CompletableFuture<Void> fireSetClusterSurfaceStatus(ReqSetClusterSurfaceStatus.Arguments arguments, Duration duration);

    CompletableFuture<Void> fireSetClusterSurfaceStatus(ReqSetClusterSurfaceStatus reqSetClusterSurfaceStatus);

    CompletableFuture<Void> fireSetClusterSurfaceStatus(ReqSetClusterSurfaceStatus reqSetClusterSurfaceStatus, Duration duration);

    CompletableFuture<Void> fireSetDeviceId(ReqSetDeviceId.Arguments arguments);

    CompletableFuture<Void> fireSetDeviceId(ReqSetDeviceId.Arguments arguments, Duration duration);

    CompletableFuture<Void> fireSetDeviceId(ReqSetDeviceId reqSetDeviceId);

    CompletableFuture<Void> fireSetDeviceId(ReqSetDeviceId reqSetDeviceId, Duration duration);

    CompletableFuture<Void> fireSetDownloadPath(ReqSetDownloadPath.Arguments arguments);

    CompletableFuture<Void> fireSetDownloadPath(ReqSetDownloadPath.Arguments arguments, Duration duration);

    CompletableFuture<Void> fireSetDownloadPath(ReqSetDownloadPath reqSetDownloadPath);

    CompletableFuture<Void> fireSetDownloadPath(ReqSetDownloadPath reqSetDownloadPath, Duration duration);

    CompletableFuture<Void> fireSetEnduranceMileage(ReqSetEnduranceMileage.Arguments arguments);

    CompletableFuture<Void> fireSetEnduranceMileage(ReqSetEnduranceMileage.Arguments arguments, Duration duration);

    CompletableFuture<Void> fireSetEnduranceMileage(ReqSetEnduranceMileage reqSetEnduranceMileage);

    CompletableFuture<Void> fireSetEnduranceMileage(ReqSetEnduranceMileage reqSetEnduranceMileage, Duration duration);

    CompletableFuture<Void> fireSetExpiredDate(ReqSetExpiredDate.Arguments arguments);

    CompletableFuture<Void> fireSetExpiredDate(ReqSetExpiredDate.Arguments arguments, Duration duration);

    CompletableFuture<Void> fireSetExpiredDate(ReqSetExpiredDate reqSetExpiredDate);

    CompletableFuture<Void> fireSetExpiredDate(ReqSetExpiredDate reqSetExpiredDate, Duration duration);

    CompletableFuture<Void> fireSetExtCloudServerStatus(ReqSetExtCloudServerStatus.Arguments arguments);

    CompletableFuture<Void> fireSetExtCloudServerStatus(ReqSetExtCloudServerStatus.Arguments arguments, Duration duration);

    CompletableFuture<Void> fireSetExtCloudServerStatus(ReqSetExtCloudServerStatus reqSetExtCloudServerStatus);

    CompletableFuture<Void> fireSetExtCloudServerStatus(ReqSetExtCloudServerStatus reqSetExtCloudServerStatus, Duration duration);

    CompletableFuture<Void> fireSetFuelType(ReqSetFuelType.Arguments arguments);

    CompletableFuture<Void> fireSetFuelType(ReqSetFuelType.Arguments arguments, Duration duration);

    CompletableFuture<Void> fireSetFuelType(ReqSetFuelType reqSetFuelType);

    CompletableFuture<Void> fireSetFuelType(ReqSetFuelType reqSetFuelType, Duration duration);

    CompletableFuture<Void> fireSetInstallationPath(ReqSetInstallationPath.Arguments arguments);

    CompletableFuture<Void> fireSetInstallationPath(ReqSetInstallationPath.Arguments arguments, Duration duration);

    CompletableFuture<Void> fireSetInstallationPath(ReqSetInstallationPath reqSetInstallationPath);

    CompletableFuture<Void> fireSetInstallationPath(ReqSetInstallationPath reqSetInstallationPath, Duration duration);

    CompletableFuture<Void> fireSetInternetSwitchStatus(ReqSetInternetSwitchStatus.Arguments arguments);

    CompletableFuture<Void> fireSetInternetSwitchStatus(ReqSetInternetSwitchStatus.Arguments arguments, Duration duration);

    CompletableFuture<Void> fireSetInternetSwitchStatus(ReqSetInternetSwitchStatus reqSetInternetSwitchStatus);

    CompletableFuture<Void> fireSetInternetSwitchStatus(ReqSetInternetSwitchStatus reqSetInternetSwitchStatus, Duration duration);

    CompletableFuture<Void> fireSetLowBatterySignal(ReqSetLowBatterySignal.Arguments arguments);

    CompletableFuture<Void> fireSetLowBatterySignal(ReqSetLowBatterySignal.Arguments arguments, Duration duration);

    CompletableFuture<Void> fireSetLowBatterySignal(ReqSetLowBatterySignal reqSetLowBatterySignal);

    CompletableFuture<Void> fireSetLowBatterySignal(ReqSetLowBatterySignal reqSetLowBatterySignal, Duration duration);

    CompletableFuture<Void> fireSetLowFuelSignal(ReqSetLowFuelSignal.Arguments arguments);

    CompletableFuture<Void> fireSetLowFuelSignal(ReqSetLowFuelSignal.Arguments arguments, Duration duration);

    CompletableFuture<Void> fireSetLowFuelSignal(ReqSetLowFuelSignal reqSetLowFuelSignal);

    CompletableFuture<Void> fireSetLowFuelSignal(ReqSetLowFuelSignal reqSetLowFuelSignal, Duration duration);

    CompletableFuture<Void> fireSetMapScreenStatus(ReqSetMapScreenStatus.Arguments arguments);

    CompletableFuture<Void> fireSetMapScreenStatus(ReqSetMapScreenStatus.Arguments arguments, Duration duration);

    CompletableFuture<Void> fireSetMapScreenStatus(ReqSetMapScreenStatus reqSetMapScreenStatus);

    CompletableFuture<Void> fireSetMapScreenStatus(ReqSetMapScreenStatus reqSetMapScreenStatus, Duration duration);

    CompletableFuture<Void> fireSetMapSurfaceStatus(ReqSetMapSurfaceStatus.Arguments arguments);

    CompletableFuture<Void> fireSetMapSurfaceStatus(ReqSetMapSurfaceStatus.Arguments arguments, Duration duration);

    CompletableFuture<Void> fireSetMapSurfaceStatus(ReqSetMapSurfaceStatus reqSetMapSurfaceStatus);

    CompletableFuture<Void> fireSetMapSurfaceStatus(ReqSetMapSurfaceStatus reqSetMapSurfaceStatus, Duration duration);

    CompletableFuture<Void> fireSetMapViewMode(ReqSetMapViewMode.Arguments arguments);

    CompletableFuture<Void> fireSetMapViewMode(ReqSetMapViewMode.Arguments arguments, Duration duration);

    CompletableFuture<Void> fireSetMapViewMode(ReqSetMapViewMode reqSetMapViewMode);

    CompletableFuture<Void> fireSetMapViewMode(ReqSetMapViewMode reqSetMapViewMode, Duration duration);

    CompletableFuture<Void> fireSetNaviMuteModeComm(ReqSetNaviMuteModeComm.Arguments arguments);

    CompletableFuture<Void> fireSetNaviMuteModeComm(ReqSetNaviMuteModeComm.Arguments arguments, Duration duration);

    CompletableFuture<Void> fireSetNaviMuteModeComm(ReqSetNaviMuteModeComm reqSetNaviMuteModeComm);

    CompletableFuture<Void> fireSetNaviMuteModeComm(ReqSetNaviMuteModeComm reqSetNaviMuteModeComm, Duration duration);

    CompletableFuture<Void> fireSetOnKeyDownValue(ReqSetOnKeyDownValue.Arguments arguments);

    CompletableFuture<Void> fireSetOnKeyDownValue(ReqSetOnKeyDownValue.Arguments arguments, Duration duration);

    CompletableFuture<Void> fireSetOnKeyDownValue(ReqSetOnKeyDownValue reqSetOnKeyDownValue);

    CompletableFuture<Void> fireSetOnKeyDownValue(ReqSetOnKeyDownValue reqSetOnKeyDownValue, Duration duration);

    CompletableFuture<Void> fireSetOnlineFeatureAvailableStatus(ReqSetOnlineFeatureAvailableStatus.Arguments arguments);

    CompletableFuture<Void> fireSetOnlineFeatureAvailableStatus(ReqSetOnlineFeatureAvailableStatus.Arguments arguments, Duration duration);

    CompletableFuture<Void> fireSetOnlineFeatureAvailableStatus(ReqSetOnlineFeatureAvailableStatus reqSetOnlineFeatureAvailableStatus);

    CompletableFuture<Void> fireSetOnlineFeatureAvailableStatus(ReqSetOnlineFeatureAvailableStatus reqSetOnlineFeatureAvailableStatus, Duration duration);

    CompletableFuture<Void> fireSetPeripheralStatus(ReqSetPeripheralStatus.Arguments arguments);

    CompletableFuture<Void> fireSetPeripheralStatus(ReqSetPeripheralStatus.Arguments arguments, Duration duration);

    CompletableFuture<Void> fireSetPeripheralStatus(ReqSetPeripheralStatus reqSetPeripheralStatus);

    CompletableFuture<Void> fireSetPeripheralStatus(ReqSetPeripheralStatus reqSetPeripheralStatus, Duration duration);

    CompletableFuture<Void> fireSetRegionalType(ReqSetRegionalType.Arguments arguments);

    CompletableFuture<Void> fireSetRegionalType(ReqSetRegionalType.Arguments arguments, Duration duration);

    CompletableFuture<Void> fireSetRegionalType(ReqSetRegionalType reqSetRegionalType);

    CompletableFuture<Void> fireSetRegionalType(ReqSetRegionalType reqSetRegionalType, Duration duration);

    CompletableFuture<Void> fireSetRemainingElectricQuantity(ReqSetRemainingElectricQuantity.Arguments arguments);

    CompletableFuture<Void> fireSetRemainingElectricQuantity(ReqSetRemainingElectricQuantity.Arguments arguments, Duration duration);

    CompletableFuture<Void> fireSetRemainingElectricQuantity(ReqSetRemainingElectricQuantity reqSetRemainingElectricQuantity);

    CompletableFuture<Void> fireSetRemainingElectricQuantity(ReqSetRemainingElectricQuantity reqSetRemainingElectricQuantity, Duration duration);

    CompletableFuture<Void> fireSetRemainingElectricQuantityKWH(ReqSetRemainingElectricQuantityKWH.Arguments arguments);

    CompletableFuture<Void> fireSetRemainingElectricQuantityKWH(ReqSetRemainingElectricQuantityKWH.Arguments arguments, Duration duration);

    CompletableFuture<Void> fireSetRemainingElectricQuantityKWH(ReqSetRemainingElectricQuantityKWH reqSetRemainingElectricQuantityKWH);

    CompletableFuture<Void> fireSetRemainingElectricQuantityKWH(ReqSetRemainingElectricQuantityKWH reqSetRemainingElectricQuantityKWH, Duration duration);

    CompletableFuture<Void> fireSetScreenDirection(ReqSetScreenDirection.Arguments arguments);

    CompletableFuture<Void> fireSetScreenDirection(ReqSetScreenDirection.Arguments arguments, Duration duration);

    CompletableFuture<Void> fireSetScreenDirection(ReqSetScreenDirection reqSetScreenDirection);

    CompletableFuture<Void> fireSetScreenDirection(ReqSetScreenDirection reqSetScreenDirection, Duration duration);

    CompletableFuture<Void> fireSetScreenMode(ReqSetScreenMode.Arguments arguments);

    CompletableFuture<Void> fireSetScreenMode(ReqSetScreenMode.Arguments arguments, Duration duration);

    CompletableFuture<Void> fireSetScreenMode(ReqSetScreenMode reqSetScreenMode);

    CompletableFuture<Void> fireSetScreenMode(ReqSetScreenMode reqSetScreenMode, Duration duration);

    CompletableFuture<Void> fireSetSystemLocale(ReqSetSystemLocale.Arguments arguments);

    CompletableFuture<Void> fireSetSystemLocale(ReqSetSystemLocale.Arguments arguments, Duration duration);

    CompletableFuture<Void> fireSetSystemLocale(ReqSetSystemLocale reqSetSystemLocale);

    CompletableFuture<Void> fireSetSystemLocale(ReqSetSystemLocale reqSetSystemLocale, Duration duration);

    CompletableFuture<Void> fireSetSystemTime(ReqSetSystemTime.Arguments arguments);

    CompletableFuture<Void> fireSetSystemTime(ReqSetSystemTime.Arguments arguments, Duration duration);

    CompletableFuture<Void> fireSetSystemTime(ReqSetSystemTime reqSetSystemTime);

    CompletableFuture<Void> fireSetSystemTime(ReqSetSystemTime reqSetSystemTime, Duration duration);

    CompletableFuture<Void> fireSetSystemTimeAndWeekDay(ReqSetSystemTimeAndWeekDay.Arguments arguments);

    CompletableFuture<Void> fireSetSystemTimeAndWeekDay(ReqSetSystemTimeAndWeekDay.Arguments arguments, Duration duration);

    CompletableFuture<Void> fireSetSystemTimeAndWeekDay(ReqSetSystemTimeAndWeekDay reqSetSystemTimeAndWeekDay);

    CompletableFuture<Void> fireSetSystemTimeAndWeekDay(ReqSetSystemTimeAndWeekDay reqSetSystemTimeAndWeekDay, Duration duration);

    CompletableFuture<Void> fireSetSystemTimeChange(ReqSetSystemTimeChange.Arguments arguments);

    CompletableFuture<Void> fireSetSystemTimeChange(ReqSetSystemTimeChange.Arguments arguments, Duration duration);

    CompletableFuture<Void> fireSetSystemTimeChange(ReqSetSystemTimeChange reqSetSystemTimeChange);

    CompletableFuture<Void> fireSetSystemTimeChange(ReqSetSystemTimeChange reqSetSystemTimeChange, Duration duration);

    CompletableFuture<Void> fireSetSystemTimeFormat(ReqSetSystemTimeFormat.Arguments arguments);

    CompletableFuture<Void> fireSetSystemTimeFormat(ReqSetSystemTimeFormat.Arguments arguments, Duration duration);

    CompletableFuture<Void> fireSetSystemTimeFormat(ReqSetSystemTimeFormat reqSetSystemTimeFormat);

    CompletableFuture<Void> fireSetSystemTimeFormat(ReqSetSystemTimeFormat reqSetSystemTimeFormat, Duration duration);

    CompletableFuture<Void> fireSetSystemTimeZoneOffset(ReqSetSystemTimeZoneOffset.Arguments arguments);

    CompletableFuture<Void> fireSetSystemTimeZoneOffset(ReqSetSystemTimeZoneOffset.Arguments arguments, Duration duration);

    CompletableFuture<Void> fireSetSystemTimeZoneOffset(ReqSetSystemTimeZoneOffset reqSetSystemTimeZoneOffset);

    CompletableFuture<Void> fireSetSystemTimeZoneOffset(ReqSetSystemTimeZoneOffset reqSetSystemTimeZoneOffset, Duration duration);

    CompletableFuture<Void> fireSetTraceLog(ReqSetTraceLog.Arguments arguments);

    CompletableFuture<Void> fireSetTraceLog(ReqSetTraceLog.Arguments arguments, Duration duration);

    CompletableFuture<Void> fireSetTraceLog(ReqSetTraceLog reqSetTraceLog);

    CompletableFuture<Void> fireSetTraceLog(ReqSetTraceLog reqSetTraceLog, Duration duration);

    CompletableFuture<Void> fireSetUnitOfMeasurement(ReqSetUnitOfMeasurement.Arguments arguments);

    CompletableFuture<Void> fireSetUnitOfMeasurement(ReqSetUnitOfMeasurement.Arguments arguments, Duration duration);

    CompletableFuture<Void> fireSetUnitOfMeasurement(ReqSetUnitOfMeasurement reqSetUnitOfMeasurement);

    CompletableFuture<Void> fireSetUnitOfMeasurement(ReqSetUnitOfMeasurement reqSetUnitOfMeasurement, Duration duration);

    CompletableFuture<Void> fireSetVehicleBrakingStatus(ReqSetVehicleBrakingStatus.Arguments arguments);

    CompletableFuture<Void> fireSetVehicleBrakingStatus(ReqSetVehicleBrakingStatus.Arguments arguments, Duration duration);

    CompletableFuture<Void> fireSetVehicleBrakingStatus(ReqSetVehicleBrakingStatus reqSetVehicleBrakingStatus);

    CompletableFuture<Void> fireSetVehicleBrakingStatus(ReqSetVehicleBrakingStatus reqSetVehicleBrakingStatus, Duration duration);

    CompletableFuture<Void> fireSetWidgetSurfaceStatus(ReqSetWidgetSurfaceStatus.Arguments arguments);

    CompletableFuture<Void> fireSetWidgetSurfaceStatus(ReqSetWidgetSurfaceStatus.Arguments arguments, Duration duration);

    CompletableFuture<Void> fireSetWidgetSurfaceStatus(ReqSetWidgetSurfaceStatus reqSetWidgetSurfaceStatus);

    CompletableFuture<Void> fireSetWidgetSurfaceStatus(ReqSetWidgetSurfaceStatus reqSetWidgetSurfaceStatus, Duration duration);

    CompletableFuture<Void> fireSignalStrength(ReqSignalStrength.Arguments arguments);

    CompletableFuture<Void> fireSignalStrength(ReqSignalStrength.Arguments arguments, Duration duration);

    CompletableFuture<Void> fireSignalStrength(ReqSignalStrength reqSignalStrength);

    CompletableFuture<Void> fireSignalStrength(ReqSignalStrength reqSignalStrength, Duration duration);

    CompletableFuture<Void> fireStartUpState(ReqStartUpState.Arguments arguments);

    CompletableFuture<Void> fireStartUpState(ReqStartUpState.Arguments arguments, Duration duration);

    CompletableFuture<Void> fireStartUpState(ReqStartUpState reqStartUpState);

    CompletableFuture<Void> fireStartUpState(ReqStartUpState reqStartUpState, Duration duration);

    CompletableFuture<Void> fireStorageSpace(ReqStorageSpace.Arguments arguments);

    CompletableFuture<Void> fireStorageSpace(ReqStorageSpace.Arguments arguments, Duration duration);

    CompletableFuture<Void> fireStorageSpace(ReqStorageSpace reqStorageSpace);

    CompletableFuture<Void> fireStorageSpace(ReqStorageSpace reqStorageSpace, Duration duration);

    CompletableFuture<Void> fireSwitchMapSetting(ReqSwitchMapSetting.Arguments arguments);

    CompletableFuture<Void> fireSwitchMapSetting(ReqSwitchMapSetting.Arguments arguments, Duration duration);

    CompletableFuture<Void> fireSwitchMapSetting(ReqSwitchMapSetting reqSwitchMapSetting);

    CompletableFuture<Void> fireSwitchMapSetting(ReqSwitchMapSetting reqSwitchMapSetting, Duration duration);

    CompletableFuture<Void> fireSwitchNaviLayoutDirection(ReqSwitchNaviLayoutDirection.Arguments arguments);

    CompletableFuture<Void> fireSwitchNaviLayoutDirection(ReqSwitchNaviLayoutDirection.Arguments arguments, Duration duration);

    CompletableFuture<Void> fireSwitchNaviLayoutDirection(ReqSwitchNaviLayoutDirection reqSwitchNaviLayoutDirection);

    CompletableFuture<Void> fireSwitchNaviLayoutDirection(ReqSwitchNaviLayoutDirection reqSwitchNaviLayoutDirection, Duration duration);

    CompletableFuture<Void> fireSwitchTrafficInfo(ReqSwitchTrafficInfo.Arguments arguments);

    CompletableFuture<Void> fireSwitchTrafficInfo(ReqSwitchTrafficInfo.Arguments arguments, Duration duration);

    CompletableFuture<Void> fireSwitchTrafficInfo(ReqSwitchTrafficInfo reqSwitchTrafficInfo);

    CompletableFuture<Void> fireSwitchTrafficInfo(ReqSwitchTrafficInfo reqSwitchTrafficInfo, Duration duration);

    CompletableFuture<Void> fireUpdateDB(ReqUpdateDB.Arguments arguments);

    CompletableFuture<Void> fireUpdateDB(ReqUpdateDB.Arguments arguments, Duration duration);

    CompletableFuture<Void> fireUpdateDB(ReqUpdateDB reqUpdateDB);

    CompletableFuture<Void> fireUpdateDB(ReqUpdateDB reqUpdateDB, Duration duration);

    CompletableFuture<Void> fireUpdateExternalStoragePath(ReqUpdateExternalStoragePath.Arguments arguments);

    CompletableFuture<Void> fireUpdateExternalStoragePath(ReqUpdateExternalStoragePath.Arguments arguments, Duration duration);

    CompletableFuture<Void> fireUpdateExternalStoragePath(ReqUpdateExternalStoragePath reqUpdateExternalStoragePath);

    CompletableFuture<Void> fireUpdateExternalStoragePath(ReqUpdateExternalStoragePath reqUpdateExternalStoragePath, Duration duration);

    CompletableFuture<ResBackToCCP> requestBackToCCP();

    CompletableFuture<ResBackToCCP> requestBackToCCP(ReqBackToCCP reqBackToCCP);

    CompletableFuture<ResBackToCCP> requestBackToCCP(ReqBackToCCP reqBackToCCP, Duration duration);

    CompletableFuture<ResBackToCCP> requestBackToCCP(Duration duration);

    CompletableFuture<ResCheckNaviGuide> requestCheckNaviGuide();

    CompletableFuture<ResCheckNaviGuide> requestCheckNaviGuide(ReqCheckNaviGuide reqCheckNaviGuide);

    CompletableFuture<ResCheckNaviGuide> requestCheckNaviGuide(ReqCheckNaviGuide reqCheckNaviGuide, Duration duration);

    CompletableFuture<ResCheckNaviGuide> requestCheckNaviGuide(Duration duration);

    CompletableFuture<ResGetAbandonSpeak> requestGetAbandonSpeak();

    CompletableFuture<ResGetAbandonSpeak> requestGetAbandonSpeak(ReqGetAbandonSpeak reqGetAbandonSpeak);

    CompletableFuture<ResGetAbandonSpeak> requestGetAbandonSpeak(ReqGetAbandonSpeak reqGetAbandonSpeak, Duration duration);

    CompletableFuture<ResGetAbandonSpeak> requestGetAbandonSpeak(Duration duration);

    CompletableFuture<ResGetAccessCodingAvailable> requestGetAccessCodingAvailable();

    CompletableFuture<ResGetAccessCodingAvailable> requestGetAccessCodingAvailable(ReqGetAccessCodingAvailable reqGetAccessCodingAvailable);

    CompletableFuture<ResGetAccessCodingAvailable> requestGetAccessCodingAvailable(ReqGetAccessCodingAvailable reqGetAccessCodingAvailable, Duration duration);

    CompletableFuture<ResGetAccessCodingAvailable> requestGetAccessCodingAvailable(Duration duration);

    CompletableFuture<ResGetAppLocale> requestGetAppLocale();

    CompletableFuture<ResGetAppLocale> requestGetAppLocale(ReqGetAppLocale reqGetAppLocale);

    CompletableFuture<ResGetAppLocale> requestGetAppLocale(ReqGetAppLocale reqGetAppLocale, Duration duration);

    CompletableFuture<ResGetAppLocale> requestGetAppLocale(Duration duration);

    CompletableFuture<ResGetApplyUpdateStatus> requestGetApplyUpdateStatus();

    CompletableFuture<ResGetApplyUpdateStatus> requestGetApplyUpdateStatus(ReqGetApplyUpdateStatus reqGetApplyUpdateStatus);

    CompletableFuture<ResGetApplyUpdateStatus> requestGetApplyUpdateStatus(ReqGetApplyUpdateStatus reqGetApplyUpdateStatus, Duration duration);

    CompletableFuture<ResGetApplyUpdateStatus> requestGetApplyUpdateStatus(Duration duration);

    CompletableFuture<ResGetCallUpStatus> requestGetCallUpStatus();

    CompletableFuture<ResGetCallUpStatus> requestGetCallUpStatus(ReqGetCallUpStatus reqGetCallUpStatus);

    CompletableFuture<ResGetCallUpStatus> requestGetCallUpStatus(ReqGetCallUpStatus reqGetCallUpStatus, Duration duration);

    CompletableFuture<ResGetCallUpStatus> requestGetCallUpStatus(Duration duration);

    CompletableFuture<ResGetCheckDBAvailable> requestGetCheckDBAvailable();

    CompletableFuture<ResGetCheckDBAvailable> requestGetCheckDBAvailable(ReqGetCheckDBAvailable reqGetCheckDBAvailable);

    CompletableFuture<ResGetCheckDBAvailable> requestGetCheckDBAvailable(ReqGetCheckDBAvailable reqGetCheckDBAvailable, Duration duration);

    CompletableFuture<ResGetCheckDBAvailable> requestGetCheckDBAvailable(Duration duration);

    CompletableFuture<ResGetCloseAndRestartApp> requestGetCloseAndRestartApp();

    CompletableFuture<ResGetCloseAndRestartApp> requestGetCloseAndRestartApp(ReqGetCloseAndRestartApp reqGetCloseAndRestartApp);

    CompletableFuture<ResGetCloseAndRestartApp> requestGetCloseAndRestartApp(ReqGetCloseAndRestartApp reqGetCloseAndRestartApp, Duration duration);

    CompletableFuture<ResGetCloseAndRestartApp> requestGetCloseAndRestartApp(Duration duration);

    CompletableFuture<ResGetCloseApp> requestGetCloseApp();

    CompletableFuture<ResGetCloseApp> requestGetCloseApp(ReqGetCloseApp reqGetCloseApp);

    CompletableFuture<ResGetCloseApp> requestGetCloseApp(ReqGetCloseApp reqGetCloseApp, Duration duration);

    CompletableFuture<ResGetCloseApp> requestGetCloseApp(Duration duration);

    CompletableFuture<ResGetCloseMainActivity> requestGetCloseMainActivity();

    CompletableFuture<ResGetCloseMainActivity> requestGetCloseMainActivity(ReqGetCloseMainActivity reqGetCloseMainActivity);

    CompletableFuture<ResGetCloseMainActivity> requestGetCloseMainActivity(ReqGetCloseMainActivity reqGetCloseMainActivity, Duration duration);

    CompletableFuture<ResGetCloseMainActivity> requestGetCloseMainActivity(Duration duration);

    CompletableFuture<ResGetCopyFiles> requestGetCopyFiles();

    CompletableFuture<ResGetCopyFiles> requestGetCopyFiles(ReqGetCopyFiles reqGetCopyFiles);

    CompletableFuture<ResGetCopyFiles> requestGetCopyFiles(ReqGetCopyFiles reqGetCopyFiles, Duration duration);

    CompletableFuture<ResGetCopyFiles> requestGetCopyFiles(Duration duration);

    CompletableFuture<ResGetCurrentLocation> requestGetCurrentLocation();

    CompletableFuture<ResGetCurrentLocation> requestGetCurrentLocation(ReqGetCurrentLocation reqGetCurrentLocation);

    CompletableFuture<ResGetCurrentLocation> requestGetCurrentLocation(ReqGetCurrentLocation reqGetCurrentLocation, Duration duration);

    CompletableFuture<ResGetCurrentLocation> requestGetCurrentLocation(Duration duration);

    CompletableFuture<ResGetDBDataIntegrityCheck> requestGetDBDataIntegrityCheck();

    CompletableFuture<ResGetDBDataIntegrityCheck> requestGetDBDataIntegrityCheck(ReqGetDBDataIntegrityCheck reqGetDBDataIntegrityCheck);

    CompletableFuture<ResGetDBDataIntegrityCheck> requestGetDBDataIntegrityCheck(ReqGetDBDataIntegrityCheck reqGetDBDataIntegrityCheck, Duration duration);

    CompletableFuture<ResGetDBDataIntegrityCheck> requestGetDBDataIntegrityCheck(Duration duration);

    CompletableFuture<ResGetDayNightMode> requestGetDayNightMode();

    CompletableFuture<ResGetDayNightMode> requestGetDayNightMode(ReqGetDayNightMode reqGetDayNightMode);

    CompletableFuture<ResGetDayNightMode> requestGetDayNightMode(ReqGetDayNightMode reqGetDayNightMode, Duration duration);

    CompletableFuture<ResGetDayNightMode> requestGetDayNightMode(Duration duration);

    CompletableFuture<ResGetDelegateSystemEvent> requestGetDelegateSystemEvent();

    CompletableFuture<ResGetDelegateSystemEvent> requestGetDelegateSystemEvent(ReqGetDelegateSystemEvent reqGetDelegateSystemEvent);

    CompletableFuture<ResGetDelegateSystemEvent> requestGetDelegateSystemEvent(ReqGetDelegateSystemEvent reqGetDelegateSystemEvent, Duration duration);

    CompletableFuture<ResGetDelegateSystemEvent> requestGetDelegateSystemEvent(Duration duration);

    CompletableFuture<ResGetDeleteAllDBData> requestGetDeleteAllDBData();

    CompletableFuture<ResGetDeleteAllDBData> requestGetDeleteAllDBData(ReqGetDeleteAllDBData reqGetDeleteAllDBData);

    CompletableFuture<ResGetDeleteAllDBData> requestGetDeleteAllDBData(ReqGetDeleteAllDBData reqGetDeleteAllDBData, Duration duration);

    CompletableFuture<ResGetDeleteAllDBData> requestGetDeleteAllDBData(Duration duration);

    CompletableFuture<ResGetDeleteFile> requestGetDeleteFile();

    CompletableFuture<ResGetDeleteFile> requestGetDeleteFile(ReqGetDeleteFile reqGetDeleteFile);

    CompletableFuture<ResGetDeleteFile> requestGetDeleteFile(ReqGetDeleteFile reqGetDeleteFile, Duration duration);

    CompletableFuture<ResGetDeleteFile> requestGetDeleteFile(Duration duration);

    CompletableFuture<ResGetExportDirectoryToUDisk> requestGetExportDirectoryToUDisk();

    CompletableFuture<ResGetExportDirectoryToUDisk> requestGetExportDirectoryToUDisk(ReqGetExportDirectoryToUDisk reqGetExportDirectoryToUDisk);

    CompletableFuture<ResGetExportDirectoryToUDisk> requestGetExportDirectoryToUDisk(ReqGetExportDirectoryToUDisk reqGetExportDirectoryToUDisk, Duration duration);

    CompletableFuture<ResGetExportDirectoryToUDisk> requestGetExportDirectoryToUDisk(Duration duration);

    CompletableFuture<ResGetExtractFiles> requestGetExtractFiles();

    CompletableFuture<ResGetExtractFiles> requestGetExtractFiles(ReqGetExtractFiles reqGetExtractFiles);

    CompletableFuture<ResGetExtractFiles> requestGetExtractFiles(ReqGetExtractFiles reqGetExtractFiles, Duration duration);

    CompletableFuture<ResGetExtractFiles> requestGetExtractFiles(Duration duration);

    CompletableFuture<ResGetGetLowFuelStatus> requestGetGetLowFuelStatus();

    CompletableFuture<ResGetGetLowFuelStatus> requestGetGetLowFuelStatus(ReqGetGetLowFuelStatus reqGetGetLowFuelStatus);

    CompletableFuture<ResGetGetLowFuelStatus> requestGetGetLowFuelStatus(ReqGetGetLowFuelStatus reqGetGetLowFuelStatus, Duration duration);

    CompletableFuture<ResGetGetLowFuelStatus> requestGetGetLowFuelStatus(Duration duration);

    CompletableFuture<ResGetGuidanceInfoUpdate> requestGetGuidanceInfoUpdate();

    CompletableFuture<ResGetGuidanceInfoUpdate> requestGetGuidanceInfoUpdate(ReqGetGuidanceInfoUpdate reqGetGuidanceInfoUpdate);

    CompletableFuture<ResGetGuidanceInfoUpdate> requestGetGuidanceInfoUpdate(ReqGetGuidanceInfoUpdate reqGetGuidanceInfoUpdate, Duration duration);

    CompletableFuture<ResGetGuidanceInfoUpdate> requestGetGuidanceInfoUpdate(Duration duration);

    CompletableFuture<ResGetGuideInfo> requestGetGuideInfo();

    CompletableFuture<ResGetGuideInfo> requestGetGuideInfo(ReqGetGuideInfo reqGetGuideInfo);

    CompletableFuture<ResGetGuideInfo> requestGetGuideInfo(ReqGetGuideInfo reqGetGuideInfo, Duration duration);

    CompletableFuture<ResGetGuideInfo> requestGetGuideInfo(Duration duration);

    CompletableFuture<ResGetImmersionStatus> requestGetImmersionStatus();

    CompletableFuture<ResGetImmersionStatus> requestGetImmersionStatus(ReqGetImmersionStatus reqGetImmersionStatus);

    CompletableFuture<ResGetImmersionStatus> requestGetImmersionStatus(ReqGetImmersionStatus reqGetImmersionStatus, Duration duration);

    CompletableFuture<ResGetImmersionStatus> requestGetImmersionStatus(Duration duration);

    CompletableFuture<ResGetLaunchApp> requestGetLaunchApp();

    CompletableFuture<ResGetLaunchApp> requestGetLaunchApp(ReqGetLaunchApp reqGetLaunchApp);

    CompletableFuture<ResGetLaunchApp> requestGetLaunchApp(ReqGetLaunchApp reqGetLaunchApp, Duration duration);

    CompletableFuture<ResGetLaunchApp> requestGetLaunchApp(Duration duration);

    CompletableFuture<ResGetLoadURL> requestGetLoadURL();

    CompletableFuture<ResGetLoadURL> requestGetLoadURL(ReqGetLoadURL reqGetLoadURL);

    CompletableFuture<ResGetLoadURL> requestGetLoadURL(ReqGetLoadURL reqGetLoadURL, Duration duration);

    CompletableFuture<ResGetLoadURL> requestGetLoadURL(Duration duration);

    CompletableFuture<ResGetLocalDBVersionInfo> requestGetLocalDBVersionInfo();

    CompletableFuture<ResGetLocalDBVersionInfo> requestGetLocalDBVersionInfo(ReqGetLocalDBVersionInfo reqGetLocalDBVersionInfo);

    CompletableFuture<ResGetLocalDBVersionInfo> requestGetLocalDBVersionInfo(ReqGetLocalDBVersionInfo reqGetLocalDBVersionInfo, Duration duration);

    CompletableFuture<ResGetLocalDBVersionInfo> requestGetLocalDBVersionInfo(Duration duration);

    CompletableFuture<ResGetMapAppStatus> requestGetMapAppStatus();

    CompletableFuture<ResGetMapAppStatus> requestGetMapAppStatus(ReqGetMapAppStatus reqGetMapAppStatus);

    CompletableFuture<ResGetMapAppStatus> requestGetMapAppStatus(ReqGetMapAppStatus reqGetMapAppStatus, Duration duration);

    CompletableFuture<ResGetMapAppStatus> requestGetMapAppStatus(Duration duration);

    CompletableFuture<ResGetMapScaleLevel> requestGetMapScaleLevel();

    CompletableFuture<ResGetMapScaleLevel> requestGetMapScaleLevel(ReqGetMapScaleLevel reqGetMapScaleLevel);

    CompletableFuture<ResGetMapScaleLevel> requestGetMapScaleLevel(ReqGetMapScaleLevel reqGetMapScaleLevel, Duration duration);

    CompletableFuture<ResGetMapScaleLevel> requestGetMapScaleLevel(Duration duration);

    CompletableFuture<ResGetMapSettingStatus> requestGetMapSettingStatus();

    CompletableFuture<ResGetMapSettingStatus> requestGetMapSettingStatus(ReqGetMapSettingStatus reqGetMapSettingStatus);

    CompletableFuture<ResGetMapSettingStatus> requestGetMapSettingStatus(ReqGetMapSettingStatus reqGetMapSettingStatus, Duration duration);

    CompletableFuture<ResGetMapSettingStatus> requestGetMapSettingStatus(Duration duration);

    CompletableFuture<ResGetMapViewMode> requestGetMapViewMode();

    CompletableFuture<ResGetMapViewMode> requestGetMapViewMode(ReqGetMapViewMode reqGetMapViewMode);

    CompletableFuture<ResGetMapViewMode> requestGetMapViewMode(ReqGetMapViewMode reqGetMapViewMode, Duration duration);

    CompletableFuture<ResGetMapViewMode> requestGetMapViewMode(Duration duration);

    CompletableFuture<ResGetMonitorCOMMStatus> requestGetMonitorCOMMStatus();

    CompletableFuture<ResGetMonitorCOMMStatus> requestGetMonitorCOMMStatus(ReqGetMonitorCOMMStatus reqGetMonitorCOMMStatus);

    CompletableFuture<ResGetMonitorCOMMStatus> requestGetMonitorCOMMStatus(ReqGetMonitorCOMMStatus reqGetMonitorCOMMStatus, Duration duration);

    CompletableFuture<ResGetMonitorCOMMStatus> requestGetMonitorCOMMStatus(Duration duration);

    CompletableFuture<ResGetNaviDayNightStatusChange> requestGetNaviDayNightStatusChange();

    CompletableFuture<ResGetNaviDayNightStatusChange> requestGetNaviDayNightStatusChange(ReqGetNaviDayNightStatusChange reqGetNaviDayNightStatusChange);

    CompletableFuture<ResGetNaviDayNightStatusChange> requestGetNaviDayNightStatusChange(ReqGetNaviDayNightStatusChange reqGetNaviDayNightStatusChange, Duration duration);

    CompletableFuture<ResGetNaviDayNightStatusChange> requestGetNaviDayNightStatusChange(Duration duration);

    CompletableFuture<ResGetNaviMapViewMode> requestGetNaviMapViewMode();

    CompletableFuture<ResGetNaviMapViewMode> requestGetNaviMapViewMode(ReqGetNaviMapViewMode reqGetNaviMapViewMode);

    CompletableFuture<ResGetNaviMapViewMode> requestGetNaviMapViewMode(ReqGetNaviMapViewMode reqGetNaviMapViewMode, Duration duration);

    CompletableFuture<ResGetNaviMapViewMode> requestGetNaviMapViewMode(Duration duration);

    CompletableFuture<ResGetNaviMuteModeComm> requestGetNaviMuteModeComm();

    CompletableFuture<ResGetNaviMuteModeComm> requestGetNaviMuteModeComm(ReqGetNaviMuteModeComm reqGetNaviMuteModeComm);

    CompletableFuture<ResGetNaviMuteModeComm> requestGetNaviMuteModeComm(ReqGetNaviMuteModeComm reqGetNaviMuteModeComm, Duration duration);

    CompletableFuture<ResGetNaviMuteModeComm> requestGetNaviMuteModeComm(Duration duration);

    CompletableFuture<ResGetNaviPresentationInfo> requestGetNaviPresentationInfo();

    CompletableFuture<ResGetNaviPresentationInfo> requestGetNaviPresentationInfo(ReqGetNaviPresentationInfo reqGetNaviPresentationInfo);

    CompletableFuture<ResGetNaviPresentationInfo> requestGetNaviPresentationInfo(ReqGetNaviPresentationInfo reqGetNaviPresentationInfo, Duration duration);

    CompletableFuture<ResGetNaviPresentationInfo> requestGetNaviPresentationInfo(Duration duration);

    CompletableFuture<ResGetNaviPresentationMgs> requestGetNaviPresentationMgs();

    CompletableFuture<ResGetNaviPresentationMgs> requestGetNaviPresentationMgs(ReqGetNaviPresentationMgs reqGetNaviPresentationMgs);

    CompletableFuture<ResGetNaviPresentationMgs> requestGetNaviPresentationMgs(ReqGetNaviPresentationMgs reqGetNaviPresentationMgs, Duration duration);

    CompletableFuture<ResGetNaviPresentationMgs> requestGetNaviPresentationMgs(Duration duration);

    CompletableFuture<ResGetNaviSoftware> requestGetNaviSoftware();

    CompletableFuture<ResGetNaviSoftware> requestGetNaviSoftware(ReqGetNaviSoftware reqGetNaviSoftware);

    CompletableFuture<ResGetNaviSoftware> requestGetNaviSoftware(ReqGetNaviSoftware reqGetNaviSoftware, Duration duration);

    CompletableFuture<ResGetNaviSoftware> requestGetNaviSoftware(Duration duration);

    CompletableFuture<ResGetNaviSoftwareVersionInfo> requestGetNaviSoftwareVersionInfo();

    CompletableFuture<ResGetNaviSoftwareVersionInfo> requestGetNaviSoftwareVersionInfo(ReqGetNaviSoftwareVersionInfo reqGetNaviSoftwareVersionInfo);

    CompletableFuture<ResGetNaviSoftwareVersionInfo> requestGetNaviSoftwareVersionInfo(ReqGetNaviSoftwareVersionInfo reqGetNaviSoftwareVersionInfo, Duration duration);

    CompletableFuture<ResGetNaviSoftwareVersionInfo> requestGetNaviSoftwareVersionInfo(Duration duration);

    CompletableFuture<ResGetNaviStatusChanged> requestGetNaviStatusChanged();

    CompletableFuture<ResGetNaviStatusChanged> requestGetNaviStatusChanged(ReqGetNaviStatusChanged reqGetNaviStatusChanged);

    CompletableFuture<ResGetNaviStatusChanged> requestGetNaviStatusChanged(ReqGetNaviStatusChanged reqGetNaviStatusChanged, Duration duration);

    CompletableFuture<ResGetNaviStatusChanged> requestGetNaviStatusChanged(Duration duration);

    CompletableFuture<ResGetNaviSwitchFullView> requestGetNaviSwitchFullView();

    CompletableFuture<ResGetNaviSwitchFullView> requestGetNaviSwitchFullView(ReqGetNaviSwitchFullView reqGetNaviSwitchFullView);

    CompletableFuture<ResGetNaviSwitchFullView> requestGetNaviSwitchFullView(ReqGetNaviSwitchFullView reqGetNaviSwitchFullView, Duration duration);

    CompletableFuture<ResGetNaviSwitchFullView> requestGetNaviSwitchFullView(Duration duration);

    CompletableFuture<ResGetNavigationActivityStatus> requestGetNavigationActivityStatus();

    CompletableFuture<ResGetNavigationActivityStatus> requestGetNavigationActivityStatus(ReqGetNavigationActivityStatus reqGetNavigationActivityStatus);

    CompletableFuture<ResGetNavigationActivityStatus> requestGetNavigationActivityStatus(ReqGetNavigationActivityStatus reqGetNavigationActivityStatus, Duration duration);

    CompletableFuture<ResGetNavigationActivityStatus> requestGetNavigationActivityStatus(Duration duration);

    CompletableFuture<ResGetNavigationRunningStatus> requestGetNavigationRunningStatus();

    CompletableFuture<ResGetNavigationRunningStatus> requestGetNavigationRunningStatus(ReqGetNavigationRunningStatus reqGetNavigationRunningStatus);

    CompletableFuture<ResGetNavigationRunningStatus> requestGetNavigationRunningStatus(ReqGetNavigationRunningStatus reqGetNavigationRunningStatus, Duration duration);

    CompletableFuture<ResGetNavigationRunningStatus> requestGetNavigationRunningStatus(Duration duration);

    CompletableFuture<ResGetNotifyOpenGPS> requestGetNotifyOpenGPS();

    CompletableFuture<ResGetNotifyOpenGPS> requestGetNotifyOpenGPS(ReqGetNotifyOpenGPS reqGetNotifyOpenGPS);

    CompletableFuture<ResGetNotifyOpenGPS> requestGetNotifyOpenGPS(ReqGetNotifyOpenGPS reqGetNotifyOpenGPS, Duration duration);

    CompletableFuture<ResGetNotifyOpenGPS> requestGetNotifyOpenGPS(Duration duration);

    CompletableFuture<ResGetObserveWeatherInfo2System> requestGetObserveWeatherInfo2System();

    CompletableFuture<ResGetObserveWeatherInfo2System> requestGetObserveWeatherInfo2System(ReqGetObserveWeatherInfo2System reqGetObserveWeatherInfo2System);

    CompletableFuture<ResGetObserveWeatherInfo2System> requestGetObserveWeatherInfo2System(ReqGetObserveWeatherInfo2System reqGetObserveWeatherInfo2System, Duration duration);

    CompletableFuture<ResGetObserveWeatherInfo2System> requestGetObserveWeatherInfo2System(Duration duration);

    CompletableFuture<ResGetOnKeyDown> requestGetOnKeyDown();

    CompletableFuture<ResGetOnKeyDown> requestGetOnKeyDown(ReqGetOnKeyDown reqGetOnKeyDown);

    CompletableFuture<ResGetOnKeyDown> requestGetOnKeyDown(ReqGetOnKeyDown reqGetOnKeyDown, Duration duration);

    CompletableFuture<ResGetOnKeyDown> requestGetOnKeyDown(Duration duration);

    CompletableFuture<ResGetOnNaviRemainTimeDistance> requestGetOnNaviRemainTimeDistance();

    CompletableFuture<ResGetOnNaviRemainTimeDistance> requestGetOnNaviRemainTimeDistance(ReqGetOnNaviRemainTimeDistance reqGetOnNaviRemainTimeDistance);

    CompletableFuture<ResGetOnNaviRemainTimeDistance> requestGetOnNaviRemainTimeDistance(ReqGetOnNaviRemainTimeDistance reqGetOnNaviRemainTimeDistance, Duration duration);

    CompletableFuture<ResGetOnNaviRemainTimeDistance> requestGetOnNaviRemainTimeDistance(Duration duration);

    CompletableFuture<ResGetPeripheralStatus> requestGetPeripheralStatus();

    CompletableFuture<ResGetPeripheralStatus> requestGetPeripheralStatus(ReqGetPeripheralStatus reqGetPeripheralStatus);

    CompletableFuture<ResGetPeripheralStatus> requestGetPeripheralStatus(ReqGetPeripheralStatus reqGetPeripheralStatus, Duration duration);

    CompletableFuture<ResGetPeripheralStatus> requestGetPeripheralStatus(Duration duration);

    CompletableFuture<ResGetPlayBeepTone> requestGetPlayBeepTone();

    CompletableFuture<ResGetPlayBeepTone> requestGetPlayBeepTone(ReqGetPlayBeepTone reqGetPlayBeepTone);

    CompletableFuture<ResGetPlayBeepTone> requestGetPlayBeepTone(ReqGetPlayBeepTone reqGetPlayBeepTone, Duration duration);

    CompletableFuture<ResGetPlayBeepTone> requestGetPlayBeepTone(Duration duration);

    CompletableFuture<ResGetPlaySoundEffect> requestGetPlaySoundEffect();

    CompletableFuture<ResGetPlaySoundEffect> requestGetPlaySoundEffect(ReqGetPlaySoundEffect reqGetPlaySoundEffect);

    CompletableFuture<ResGetPlaySoundEffect> requestGetPlaySoundEffect(ReqGetPlaySoundEffect reqGetPlaySoundEffect, Duration duration);

    CompletableFuture<ResGetPlaySoundEffect> requestGetPlaySoundEffect(Duration duration);

    CompletableFuture<ResGetPostCustomEvent> requestGetPostCustomEvent();

    CompletableFuture<ResGetPostCustomEvent> requestGetPostCustomEvent(ReqGetPostCustomEvent reqGetPostCustomEvent);

    CompletableFuture<ResGetPostCustomEvent> requestGetPostCustomEvent(ReqGetPostCustomEvent reqGetPostCustomEvent, Duration duration);

    CompletableFuture<ResGetPostCustomEvent> requestGetPostCustomEvent(Duration duration);

    CompletableFuture<ResGetReadFile> requestGetReadFile();

    CompletableFuture<ResGetReadFile> requestGetReadFile(ReqGetReadFile reqGetReadFile);

    CompletableFuture<ResGetReadFile> requestGetReadFile(ReqGetReadFile reqGetReadFile, Duration duration);

    CompletableFuture<ResGetReadFile> requestGetReadFile(Duration duration);

    CompletableFuture<ResGetRequestDeviceId> requestGetRequestDeviceId();

    CompletableFuture<ResGetRequestDeviceId> requestGetRequestDeviceId(ReqGetRequestDeviceId reqGetRequestDeviceId);

    CompletableFuture<ResGetRequestDeviceId> requestGetRequestDeviceId(ReqGetRequestDeviceId reqGetRequestDeviceId, Duration duration);

    CompletableFuture<ResGetRequestDeviceId> requestGetRequestDeviceId(Duration duration);

    CompletableFuture<ResGetRequestEnduranceMileage> requestGetRequestEnduranceMileage();

    CompletableFuture<ResGetRequestEnduranceMileage> requestGetRequestEnduranceMileage(ReqGetRequestEnduranceMileage reqGetRequestEnduranceMileage);

    CompletableFuture<ResGetRequestEnduranceMileage> requestGetRequestEnduranceMileage(ReqGetRequestEnduranceMileage reqGetRequestEnduranceMileage, Duration duration);

    CompletableFuture<ResGetRequestEnduranceMileage> requestGetRequestEnduranceMileage(Duration duration);

    CompletableFuture<ResGetRequestExpiredDate> requestGetRequestExpiredDate();

    CompletableFuture<ResGetRequestExpiredDate> requestGetRequestExpiredDate(ReqGetRequestExpiredDate reqGetRequestExpiredDate);

    CompletableFuture<ResGetRequestExpiredDate> requestGetRequestExpiredDate(ReqGetRequestExpiredDate reqGetRequestExpiredDate, Duration duration);

    CompletableFuture<ResGetRequestExpiredDate> requestGetRequestExpiredDate(Duration duration);

    CompletableFuture<ResGetRequestInstallationPath> requestGetRequestInstallationPath();

    CompletableFuture<ResGetRequestInstallationPath> requestGetRequestInstallationPath(ReqGetRequestInstallationPath reqGetRequestInstallationPath);

    CompletableFuture<ResGetRequestInstallationPath> requestGetRequestInstallationPath(ReqGetRequestInstallationPath reqGetRequestInstallationPath, Duration duration);

    CompletableFuture<ResGetRequestInstallationPath> requestGetRequestInstallationPath(Duration duration);

    CompletableFuture<ResGetRequestUpdateExternalStoragePath> requestGetRequestUpdateExternalStoragePath();

    CompletableFuture<ResGetRequestUpdateExternalStoragePath> requestGetRequestUpdateExternalStoragePath(ReqGetRequestUpdateExternalStoragePath reqGetRequestUpdateExternalStoragePath);

    CompletableFuture<ResGetRequestUpdateExternalStoragePath> requestGetRequestUpdateExternalStoragePath(ReqGetRequestUpdateExternalStoragePath reqGetRequestUpdateExternalStoragePath, Duration duration);

    CompletableFuture<ResGetRequestUpdateExternalStoragePath> requestGetRequestUpdateExternalStoragePath(Duration duration);

    CompletableFuture<ResGetResponseExpiredDate> requestGetResponseExpiredDate();

    CompletableFuture<ResGetResponseExpiredDate> requestGetResponseExpiredDate(ReqGetResponseExpiredDate reqGetResponseExpiredDate);

    CompletableFuture<ResGetResponseExpiredDate> requestGetResponseExpiredDate(ReqGetResponseExpiredDate reqGetResponseExpiredDate, Duration duration);

    CompletableFuture<ResGetResponseExpiredDate> requestGetResponseExpiredDate(Duration duration);

    CompletableFuture<ResGetShowDiagnoseSetting> requestGetShowDiagnoseSetting();

    CompletableFuture<ResGetShowDiagnoseSetting> requestGetShowDiagnoseSetting(ReqGetShowDiagnoseSetting reqGetShowDiagnoseSetting);

    CompletableFuture<ResGetShowDiagnoseSetting> requestGetShowDiagnoseSetting(ReqGetShowDiagnoseSetting reqGetShowDiagnoseSetting, Duration duration);

    CompletableFuture<ResGetShowDiagnoseSetting> requestGetShowDiagnoseSetting(Duration duration);

    CompletableFuture<ResGetShowToastMessage> requestGetShowToastMessage();

    CompletableFuture<ResGetShowToastMessage> requestGetShowToastMessage(ReqGetShowToastMessage reqGetShowToastMessage);

    CompletableFuture<ResGetShowToastMessage> requestGetShowToastMessage(ReqGetShowToastMessage reqGetShowToastMessage, Duration duration);

    CompletableFuture<ResGetShowToastMessage> requestGetShowToastMessage(Duration duration);

    CompletableFuture<ResGetSoftwareVersionInfo> requestGetSoftwareVersionInfo();

    CompletableFuture<ResGetSoftwareVersionInfo> requestGetSoftwareVersionInfo(ReqGetSoftwareVersionInfo reqGetSoftwareVersionInfo);

    CompletableFuture<ResGetSoftwareVersionInfo> requestGetSoftwareVersionInfo(ReqGetSoftwareVersionInfo reqGetSoftwareVersionInfo, Duration duration);

    CompletableFuture<ResGetSoftwareVersionInfo> requestGetSoftwareVersionInfo(Duration duration);

    CompletableFuture<ResGetSpeak> requestGetSpeak();

    CompletableFuture<ResGetSpeak> requestGetSpeak(ReqGetSpeak reqGetSpeak);

    CompletableFuture<ResGetSpeak> requestGetSpeak(ReqGetSpeak reqGetSpeak, Duration duration);

    CompletableFuture<ResGetSpeak> requestGetSpeak(Duration duration);

    CompletableFuture<ResGetStorageSpaceStatus> requestGetStorageSpaceStatus();

    CompletableFuture<ResGetStorageSpaceStatus> requestGetStorageSpaceStatus(ReqGetStorageSpaceStatus reqGetStorageSpaceStatus);

    CompletableFuture<ResGetStorageSpaceStatus> requestGetStorageSpaceStatus(ReqGetStorageSpaceStatus reqGetStorageSpaceStatus, Duration duration);

    CompletableFuture<ResGetStorageSpaceStatus> requestGetStorageSpaceStatus(Duration duration);

    CompletableFuture<ResGetSwitchArgumentedReality> requestGetSwitchArgumentedReality();

    CompletableFuture<ResGetSwitchArgumentedReality> requestGetSwitchArgumentedReality(ReqGetSwitchArgumentedReality reqGetSwitchArgumentedReality);

    CompletableFuture<ResGetSwitchArgumentedReality> requestGetSwitchArgumentedReality(ReqGetSwitchArgumentedReality reqGetSwitchArgumentedReality, Duration duration);

    CompletableFuture<ResGetSwitchArgumentedReality> requestGetSwitchArgumentedReality(Duration duration);

    CompletableFuture<ResGetSwitchNaviToBackground> requestGetSwitchNaviToBackground();

    CompletableFuture<ResGetSwitchNaviToBackground> requestGetSwitchNaviToBackground(ReqGetSwitchNaviToBackground reqGetSwitchNaviToBackground);

    CompletableFuture<ResGetSwitchNaviToBackground> requestGetSwitchNaviToBackground(ReqGetSwitchNaviToBackground reqGetSwitchNaviToBackground, Duration duration);

    CompletableFuture<ResGetSwitchNaviToBackground> requestGetSwitchNaviToBackground(Duration duration);

    CompletableFuture<ResGetSwitchTraceLog> requestGetSwitchTraceLog();

    CompletableFuture<ResGetSwitchTraceLog> requestGetSwitchTraceLog(ReqGetSwitchTraceLog reqGetSwitchTraceLog);

    CompletableFuture<ResGetSwitchTraceLog> requestGetSwitchTraceLog(ReqGetSwitchTraceLog reqGetSwitchTraceLog, Duration duration);

    CompletableFuture<ResGetSwitchTraceLog> requestGetSwitchTraceLog(Duration duration);

    CompletableFuture<ResGetTraceLog> requestGetTraceLog();

    CompletableFuture<ResGetTraceLog> requestGetTraceLog(ReqGetTraceLog reqGetTraceLog);

    CompletableFuture<ResGetTraceLog> requestGetTraceLog(ReqGetTraceLog reqGetTraceLog, Duration duration);

    CompletableFuture<ResGetTraceLog> requestGetTraceLog(Duration duration);

    CompletableFuture<ResGetTrafficInfoState> requestGetTrafficInfoState();

    CompletableFuture<ResGetTrafficInfoState> requestGetTrafficInfoState(ReqGetTrafficInfoState reqGetTrafficInfoState);

    CompletableFuture<ResGetTrafficInfoState> requestGetTrafficInfoState(ReqGetTrafficInfoState reqGetTrafficInfoState, Duration duration);

    CompletableFuture<ResGetTrafficInfoState> requestGetTrafficInfoState(Duration duration);

    CompletableFuture<ResGetWriteFile> requestGetWriteFile();

    CompletableFuture<ResGetWriteFile> requestGetWriteFile(ReqGetWriteFile reqGetWriteFile);

    CompletableFuture<ResGetWriteFile> requestGetWriteFile(ReqGetWriteFile reqGetWriteFile, Duration duration);

    CompletableFuture<ResGetWriteFile> requestGetWriteFile(Duration duration);

    CompletableFuture<ResMapZoomInOut.Arguments> requestMapZoomInOut(ReqMapZoomInOut.Arguments arguments);

    CompletableFuture<ResMapZoomInOut.Arguments> requestMapZoomInOut(ReqMapZoomInOut.Arguments arguments, Duration duration);

    CompletableFuture<ResMapZoomInOut> requestMapZoomInOut(ReqMapZoomInOut reqMapZoomInOut);

    CompletableFuture<ResMapZoomInOut> requestMapZoomInOut(ReqMapZoomInOut reqMapZoomInOut, Duration duration);

    CompletableFuture<ResReqNaviGuideStart.Arguments> requestReqNaviGuideStart(ReqReqNaviGuideStart.Arguments arguments);

    CompletableFuture<ResReqNaviGuideStart.Arguments> requestReqNaviGuideStart(ReqReqNaviGuideStart.Arguments arguments, Duration duration);

    CompletableFuture<ResReqNaviGuideStart> requestReqNaviGuideStart(ReqReqNaviGuideStart reqReqNaviGuideStart);

    CompletableFuture<ResReqNaviGuideStart> requestReqNaviGuideStart(ReqReqNaviGuideStart reqReqNaviGuideStart, Duration duration);

    CompletableFuture<ResReqNaviGuideStop> requestReqNaviGuideStop();

    CompletableFuture<ResReqNaviGuideStop> requestReqNaviGuideStop(ReqReqNaviGuideStop reqReqNaviGuideStop);

    CompletableFuture<ResReqNaviGuideStop> requestReqNaviGuideStop(ReqReqNaviGuideStop reqReqNaviGuideStop, Duration duration);

    CompletableFuture<ResReqNaviGuideStop> requestReqNaviGuideStop(Duration duration);

    CompletableFuture<ResReqNaviMapViewMode.Arguments> requestReqNaviMapViewMode(ReqReqNaviMapViewMode.Arguments arguments);

    CompletableFuture<ResReqNaviMapViewMode.Arguments> requestReqNaviMapViewMode(ReqReqNaviMapViewMode.Arguments arguments, Duration duration);

    CompletableFuture<ResReqNaviMapViewMode> requestReqNaviMapViewMode(ReqReqNaviMapViewMode reqReqNaviMapViewMode);

    CompletableFuture<ResReqNaviMapViewMode> requestReqNaviMapViewMode(ReqReqNaviMapViewMode reqReqNaviMapViewMode, Duration duration);

    CompletableFuture<ResReqNaviRemainTimeDistance> requestReqNaviRemainTimeDistance();

    CompletableFuture<ResReqNaviRemainTimeDistance> requestReqNaviRemainTimeDistance(ReqReqNaviRemainTimeDistance reqReqNaviRemainTimeDistance);

    CompletableFuture<ResReqNaviRemainTimeDistance> requestReqNaviRemainTimeDistance(ReqReqNaviRemainTimeDistance reqReqNaviRemainTimeDistance, Duration duration);

    CompletableFuture<ResReqNaviRemainTimeDistance> requestReqNaviRemainTimeDistance(Duration duration);

    CompletableFuture<ResReqNaviSwitchFullView.Arguments> requestReqNaviSwitchFullView(ReqReqNaviSwitchFullView.Arguments arguments);

    CompletableFuture<ResReqNaviSwitchFullView.Arguments> requestReqNaviSwitchFullView(ReqReqNaviSwitchFullView.Arguments arguments, Duration duration);

    CompletableFuture<ResReqNaviSwitchFullView> requestReqNaviSwitchFullView(ReqReqNaviSwitchFullView reqReqNaviSwitchFullView);

    CompletableFuture<ResReqNaviSwitchFullView> requestReqNaviSwitchFullView(ReqReqNaviSwitchFullView reqReqNaviSwitchFullView, Duration duration);

    CompletableFuture<ResReqNaviZoomIn.Arguments> requestReqNaviZoomIn(ReqReqNaviZoomIn.Arguments arguments);

    CompletableFuture<ResReqNaviZoomIn.Arguments> requestReqNaviZoomIn(ReqReqNaviZoomIn.Arguments arguments, Duration duration);

    CompletableFuture<ResReqNaviZoomIn> requestReqNaviZoomIn(ReqReqNaviZoomIn reqReqNaviZoomIn);

    CompletableFuture<ResReqNaviZoomIn> requestReqNaviZoomIn(ReqReqNaviZoomIn reqReqNaviZoomIn, Duration duration);

    CompletableFuture<ResReqNaviZoomOut.Arguments> requestReqNaviZoomOut(ReqReqNaviZoomOut.Arguments arguments);

    CompletableFuture<ResReqNaviZoomOut.Arguments> requestReqNaviZoomOut(ReqReqNaviZoomOut.Arguments arguments, Duration duration);

    CompletableFuture<ResReqNaviZoomOut> requestReqNaviZoomOut(ReqReqNaviZoomOut reqReqNaviZoomOut);

    CompletableFuture<ResReqNaviZoomOut> requestReqNaviZoomOut(ReqReqNaviZoomOut reqReqNaviZoomOut, Duration duration);

    CompletableFuture<ResReqSwitchDayNightMode.Arguments> requestReqSwitchDayNightMode(ReqReqSwitchDayNightMode.Arguments arguments);

    CompletableFuture<ResReqSwitchDayNightMode.Arguments> requestReqSwitchDayNightMode(ReqReqSwitchDayNightMode.Arguments arguments, Duration duration);

    CompletableFuture<ResReqSwitchDayNightMode> requestReqSwitchDayNightMode(ReqReqSwitchDayNightMode reqReqSwitchDayNightMode);

    CompletableFuture<ResReqSwitchDayNightMode> requestReqSwitchDayNightMode(ReqReqSwitchDayNightMode reqReqSwitchDayNightMode, Duration duration);

    CompletableFuture<ResRequestRestoreFactorySetting> requestRequestRestoreFactorySetting();

    CompletableFuture<ResRequestRestoreFactorySetting> requestRequestRestoreFactorySetting(ReqRequestRestoreFactorySetting reqRequestRestoreFactorySetting);

    CompletableFuture<ResRequestRestoreFactorySetting> requestRequestRestoreFactorySetting(ReqRequestRestoreFactorySetting reqRequestRestoreFactorySetting, Duration duration);

    CompletableFuture<ResRequestRestoreFactorySetting> requestRequestRestoreFactorySetting(Duration duration);

    CompletableFuture<ResSetBatteryCapacityWH.Arguments> requestSetBatteryCapacityWH(ReqSetBatteryCapacityWH.Arguments arguments);

    CompletableFuture<ResSetBatteryCapacityWH.Arguments> requestSetBatteryCapacityWH(ReqSetBatteryCapacityWH.Arguments arguments, Duration duration);

    CompletableFuture<ResSetBatteryCapacityWH> requestSetBatteryCapacityWH(ReqSetBatteryCapacityWH reqSetBatteryCapacityWH);

    CompletableFuture<ResSetBatteryCapacityWH> requestSetBatteryCapacityWH(ReqSetBatteryCapacityWH reqSetBatteryCapacityWH, Duration duration);

    CompletableFuture<ResSetCarType.Arguments> requestSetCarType(ReqSetCarType.Arguments arguments);

    CompletableFuture<ResSetCarType.Arguments> requestSetCarType(ReqSetCarType.Arguments arguments, Duration duration);

    CompletableFuture<ResSetCarType> requestSetCarType(ReqSetCarType reqSetCarType);

    CompletableFuture<ResSetCarType> requestSetCarType(ReqSetCarType reqSetCarType, Duration duration);

    CompletableFuture<ResSetChargingPortType.Arguments> requestSetChargingPortType(ReqSetChargingPortType.Arguments arguments);

    CompletableFuture<ResSetChargingPortType.Arguments> requestSetChargingPortType(ReqSetChargingPortType.Arguments arguments, Duration duration);

    CompletableFuture<ResSetChargingPortType> requestSetChargingPortType(ReqSetChargingPortType reqSetChargingPortType);

    CompletableFuture<ResSetChargingPortType> requestSetChargingPortType(ReqSetChargingPortType reqSetChargingPortType, Duration duration);

    CompletableFuture<ResSetEnduranceMileage.Arguments> requestSetEnduranceMileage(ReqSetEnduranceMileage.Arguments arguments);

    CompletableFuture<ResSetEnduranceMileage.Arguments> requestSetEnduranceMileage(ReqSetEnduranceMileage.Arguments arguments, Duration duration);

    CompletableFuture<ResSetEnduranceMileage> requestSetEnduranceMileage(ReqSetEnduranceMileage reqSetEnduranceMileage);

    CompletableFuture<ResSetEnduranceMileage> requestSetEnduranceMileage(ReqSetEnduranceMileage reqSetEnduranceMileage, Duration duration);

    CompletableFuture<ResSetFuelType.Arguments> requestSetFuelType(ReqSetFuelType.Arguments arguments);

    CompletableFuture<ResSetFuelType.Arguments> requestSetFuelType(ReqSetFuelType.Arguments arguments, Duration duration);

    CompletableFuture<ResSetFuelType> requestSetFuelType(ReqSetFuelType reqSetFuelType);

    CompletableFuture<ResSetFuelType> requestSetFuelType(ReqSetFuelType reqSetFuelType, Duration duration);

    CompletableFuture<ResSetMapViewMode.Arguments> requestSetMapViewMode(ReqSetMapViewMode.Arguments arguments);

    CompletableFuture<ResSetMapViewMode.Arguments> requestSetMapViewMode(ReqSetMapViewMode.Arguments arguments, Duration duration);

    CompletableFuture<ResSetMapViewMode> requestSetMapViewMode(ReqSetMapViewMode reqSetMapViewMode);

    CompletableFuture<ResSetMapViewMode> requestSetMapViewMode(ReqSetMapViewMode reqSetMapViewMode, Duration duration);

    CompletableFuture<ResSetNaviMuteModeComm.Arguments> requestSetNaviMuteModeComm(ReqSetNaviMuteModeComm.Arguments arguments);

    CompletableFuture<ResSetNaviMuteModeComm.Arguments> requestSetNaviMuteModeComm(ReqSetNaviMuteModeComm.Arguments arguments, Duration duration);

    CompletableFuture<ResSetNaviMuteModeComm> requestSetNaviMuteModeComm(ReqSetNaviMuteModeComm reqSetNaviMuteModeComm);

    CompletableFuture<ResSetNaviMuteModeComm> requestSetNaviMuteModeComm(ReqSetNaviMuteModeComm reqSetNaviMuteModeComm, Duration duration);

    CompletableFuture<ResSetRegionalType.Arguments> requestSetRegionalType(ReqSetRegionalType.Arguments arguments);

    CompletableFuture<ResSetRegionalType.Arguments> requestSetRegionalType(ReqSetRegionalType.Arguments arguments, Duration duration);

    CompletableFuture<ResSetRegionalType> requestSetRegionalType(ReqSetRegionalType reqSetRegionalType);

    CompletableFuture<ResSetRegionalType> requestSetRegionalType(ReqSetRegionalType reqSetRegionalType, Duration duration);

    CompletableFuture<ResSetRemainingElectricQuantity.Arguments> requestSetRemainingElectricQuantity(ReqSetRemainingElectricQuantity.Arguments arguments);

    CompletableFuture<ResSetRemainingElectricQuantity.Arguments> requestSetRemainingElectricQuantity(ReqSetRemainingElectricQuantity.Arguments arguments, Duration duration);

    CompletableFuture<ResSetRemainingElectricQuantity> requestSetRemainingElectricQuantity(ReqSetRemainingElectricQuantity reqSetRemainingElectricQuantity);

    CompletableFuture<ResSetRemainingElectricQuantity> requestSetRemainingElectricQuantity(ReqSetRemainingElectricQuantity reqSetRemainingElectricQuantity, Duration duration);

    CompletableFuture<ResSetRemainingElectricQuantityKWH.Arguments> requestSetRemainingElectricQuantityKWH(ReqSetRemainingElectricQuantityKWH.Arguments arguments);

    CompletableFuture<ResSetRemainingElectricQuantityKWH.Arguments> requestSetRemainingElectricQuantityKWH(ReqSetRemainingElectricQuantityKWH.Arguments arguments, Duration duration);

    CompletableFuture<ResSetRemainingElectricQuantityKWH> requestSetRemainingElectricQuantityKWH(ReqSetRemainingElectricQuantityKWH reqSetRemainingElectricQuantityKWH);

    CompletableFuture<ResSetRemainingElectricQuantityKWH> requestSetRemainingElectricQuantityKWH(ReqSetRemainingElectricQuantityKWH reqSetRemainingElectricQuantityKWH, Duration duration);

    CompletableFuture<ResSetUnitOfMeasurement.Arguments> requestSetUnitOfMeasurement(ReqSetUnitOfMeasurement.Arguments arguments);

    CompletableFuture<ResSetUnitOfMeasurement.Arguments> requestSetUnitOfMeasurement(ReqSetUnitOfMeasurement.Arguments arguments, Duration duration);

    CompletableFuture<ResSetUnitOfMeasurement> requestSetUnitOfMeasurement(ReqSetUnitOfMeasurement reqSetUnitOfMeasurement);

    CompletableFuture<ResSetUnitOfMeasurement> requestSetUnitOfMeasurement(ReqSetUnitOfMeasurement reqSetUnitOfMeasurement, Duration duration);

    CompletableFuture<ResSwitchTrafficInfo.Arguments> requestSwitchTrafficInfo(ReqSwitchTrafficInfo.Arguments arguments);

    CompletableFuture<ResSwitchTrafficInfo.Arguments> requestSwitchTrafficInfo(ReqSwitchTrafficInfo.Arguments arguments, Duration duration);

    CompletableFuture<ResSwitchTrafficInfo> requestSwitchTrafficInfo(ReqSwitchTrafficInfo reqSwitchTrafficInfo);

    CompletableFuture<ResSwitchTrafficInfo> requestSwitchTrafficInfo(ReqSwitchTrafficInfo reqSwitchTrafficInfo, Duration duration);
}
