package api.notifications.com.neusoft.common.commonsysapiinverse;

import com.neusoft.de.nts.OneCoreTest.communication.attribute.ESubjectType;
import com.neusoft.de.nts.OneCoreTest.communication.attribute.OneCoreWSNotification;
import com.neusoft.de.nts.OneCoreTest.interfaces.IAttribute;
import com.neusoft.de.nts.OneCoreTest.interfaces.ICommunicationContextUtil;
import com.neusoft.de.nts.OneCoreTest.interfaces.ITransport;
import com.neusoft.de.nts.OneCoreTest.utils.EProjectInterfaceType;
import java.util.concurrent.Executor;

/* JADX INFO: loaded from: classes.dex */
public class CommonSysApiInverseNotifications {
    private final Executor executor;
    private final EProjectInterfaceType project = EProjectInterfaceType.OneCore;
    private final ITransport<String, String> transport;

    @Deprecated
    public CommonSysApiInverseNotifications(ITransport<String, String> iTransport, Executor executor) {
        this.transport = iTransport;
        this.executor = executor;
    }

    public CommonSysApiInverseNotifications(ICommunicationContextUtil<?, String, String> iCommunicationContextUtil) {
        this.transport = iCommunicationContextUtil.getDefaultTransport();
        this.executor = iCommunicationContextUtil.getDefaultExecutor();
    }

    public IAttribute<NotifyNavigationRunningStatus> navigationRunningStatusAttribute() {
        return new OneCoreWSNotification(this.project, this.transport, NotifyNavigationRunningStatus.class, ESubjectType.Behaviour, null, this.executor);
    }

    public IAttribute<NotifyNavigationActivityStatus> navigationActivityStatusAttribute() {
        return new OneCoreWSNotification(this.project, this.transport, NotifyNavigationActivityStatus.class, ESubjectType.Behaviour, null, this.executor);
    }

    public IAttribute<NotifyRequestUpdateExternalStoragePath> requestUpdateExternalStoragePathAttribute() {
        return new OneCoreWSNotification(this.project, this.transport, NotifyRequestUpdateExternalStoragePath.class, ESubjectType.Behaviour, null, this.executor);
    }

    public IAttribute<NotifyApplyUpdateStatus> applyUpdateStatusAttribute() {
        return new OneCoreWSNotification(this.project, this.transport, NotifyApplyUpdateStatus.class, ESubjectType.Behaviour, null, this.executor);
    }

    public IAttribute<NotifyCheckDBAvailable> checkDBAvailableAttribute() {
        return new OneCoreWSNotification(this.project, this.transport, NotifyCheckDBAvailable.class, ESubjectType.Behaviour, null, this.executor);
    }

    public IAttribute<NotifyAccessCodingAvailable> accessCodingAvailableAttribute() {
        return new OneCoreWSNotification(this.project, this.transport, NotifyAccessCodingAvailable.class, ESubjectType.Behaviour, null, this.executor);
    }

    public IAttribute<NotifyRequestDeviceId> requestDeviceIdAttribute() {
        return new OneCoreWSNotification(this.project, this.transport, NotifyRequestDeviceId.class, ESubjectType.Behaviour, null, this.executor);
    }

    public IAttribute<NotifyRequestExpiredDate> requestExpiredDateAttribute() {
        return new OneCoreWSNotification(this.project, this.transport, NotifyRequestExpiredDate.class, ESubjectType.Behaviour, null, this.executor);
    }

    public IAttribute<NotifyResponseExpiredDate> responseExpiredDateAttribute() {
        return new OneCoreWSNotification(this.project, this.transport, NotifyResponseExpiredDate.class, ESubjectType.Behaviour, null, this.executor);
    }

    public IAttribute<NotifyRequestInstallationPath> requestInstallationPathAttribute() {
        return new OneCoreWSNotification(this.project, this.transport, NotifyRequestInstallationPath.class, ESubjectType.Behaviour, null, this.executor);
    }

    public IAttribute<NotifyLaunchApp> launchAppAttribute() {
        return new OneCoreWSNotification(this.project, this.transport, NotifyLaunchApp.class, ESubjectType.Behaviour, null, this.executor);
    }

    public IAttribute<NotifyCallUpStatus> callUpStatusAttribute() {
        return new OneCoreWSNotification(this.project, this.transport, NotifyCallUpStatus.class, ESubjectType.Behaviour, null, this.executor);
    }

    public IAttribute<NotifyPeripheralStatus> peripheralStatusAttribute() {
        return new OneCoreWSNotification(this.project, this.transport, NotifyPeripheralStatus.class, ESubjectType.Behaviour, null, this.executor);
    }

    public IAttribute<NotifyStorageSpaceStatus> storageSpaceStatusAttribute() {
        return new OneCoreWSNotification(this.project, this.transport, NotifyStorageSpaceStatus.class, ESubjectType.Behaviour, null, this.executor);
    }

    public IAttribute<NotifyReadFile> readFileAttribute() {
        return new OneCoreWSNotification(this.project, this.transport, NotifyReadFile.class, ESubjectType.Behaviour, null, this.executor);
    }

    public IAttribute<NotifyWriteFile> writeFileAttribute() {
        return new OneCoreWSNotification(this.project, this.transport, NotifyWriteFile.class, ESubjectType.Behaviour, null, this.executor);
    }

    public IAttribute<NotifyDeleteFile> deleteFileAttribute() {
        return new OneCoreWSNotification(this.project, this.transport, NotifyDeleteFile.class, ESubjectType.Behaviour, null, this.executor);
    }

    public IAttribute<NotifyNaviDayNightStatusChange> naviDayNightStatusChangeAttribute() {
        return new OneCoreWSNotification(this.project, this.transport, NotifyNaviDayNightStatusChange.class, ESubjectType.Behaviour, null, this.executor);
    }

    public IAttribute<NotifyNaviSoftwareVersionInfo> naviSoftwareVersionInfoAttribute() {
        return new OneCoreWSNotification(this.project, this.transport, NotifyNaviSoftwareVersionInfo.class, ESubjectType.Behaviour, null, this.executor);
    }

    public IAttribute<NotifyOnKeyDown> onKeyDownAttribute() {
        return new OneCoreWSNotification(this.project, this.transport, NotifyOnKeyDown.class, ESubjectType.Behaviour, null, this.executor);
    }

    public IAttribute<NotifySwitchArgumentedReality> switchArgumentedRealityAttribute() {
        return new OneCoreWSNotification(this.project, this.transport, NotifySwitchArgumentedReality.class, ESubjectType.Behaviour, null, this.executor);
    }

    public IAttribute<NotifySwitchTraceLog> switchTraceLogAttribute() {
        return new OneCoreWSNotification(this.project, this.transport, NotifySwitchTraceLog.class, ESubjectType.Behaviour, null, this.executor);
    }

    public IAttribute<NotifyRequestEnduranceMileage> requestEnduranceMileageAttribute() {
        return new OneCoreWSNotification(this.project, this.transport, NotifyRequestEnduranceMileage.class, ESubjectType.Behaviour, null, this.executor);
    }

    public IAttribute<NotifyShowToastMessage> showToastMessageAttribute() {
        return new OneCoreWSNotification(this.project, this.transport, NotifyShowToastMessage.class, ESubjectType.Behaviour, null, this.executor);
    }

    public IAttribute<NotifyPostCustomEvent> postCustomEventAttribute() {
        return new OneCoreWSNotification(this.project, this.transport, NotifyPostCustomEvent.class, ESubjectType.Behaviour, null, this.executor);
    }

    public IAttribute<NotifyExportDirectoryToUDisk> exportDirectoryToUDiskAttribute() {
        return new OneCoreWSNotification(this.project, this.transport, NotifyExportDirectoryToUDisk.class, ESubjectType.Behaviour, null, this.executor);
    }

    public IAttribute<NotifyNaviStatusChanged> naviStatusChangedAttribute() {
        return new OneCoreWSNotification(this.project, this.transport, NotifyNaviStatusChanged.class, ESubjectType.Behaviour, null, this.executor);
    }

    public IAttribute<NotifyOnNaviRemainTimeDistance> onNaviRemainTimeDistanceAttribute() {
        return new OneCoreWSNotification(this.project, this.transport, NotifyOnNaviRemainTimeDistance.class, ESubjectType.Behaviour, null, this.executor);
    }

    public IAttribute<NotifyGuidanceInfoUpdate> guidanceInfoUpdateAttribute() {
        return new OneCoreWSNotification(this.project, this.transport, NotifyGuidanceInfoUpdate.class, ESubjectType.Behaviour, null, this.executor);
    }

    public IAttribute<NotifyMonitorCOMMStatus> monitorCOMMStatusAttribute() {
        return new OneCoreWSNotification(this.project, this.transport, NotifyMonitorCOMMStatus.class, ESubjectType.Behaviour, null, this.executor);
    }

    public IAttribute<NotifySpeak> speakAttribute() {
        return new OneCoreWSNotification(this.project, this.transport, NotifySpeak.class, ESubjectType.Behaviour, null, this.executor);
    }

    public IAttribute<NotifyAbandonSpeak> abandonSpeakAttribute() {
        return new OneCoreWSNotification(this.project, this.transport, NotifyAbandonSpeak.class, ESubjectType.Behaviour, null, this.executor);
    }

    public IAttribute<NotifyPlayBeepTone> playBeepToneAttribute() {
        return new OneCoreWSNotification(this.project, this.transport, NotifyPlayBeepTone.class, ESubjectType.Behaviour, null, this.executor);
    }

    public IAttribute<NotifyPlaySoundEffect> playSoundEffectAttribute() {
        return new OneCoreWSNotification(this.project, this.transport, NotifyPlaySoundEffect.class, ESubjectType.Behaviour, null, this.executor);
    }

    public IAttribute<NotifyCurrentLocation> currentLocationAttribute() {
        return new OneCoreWSNotification(this.project, this.transport, NotifyCurrentLocation.class, ESubjectType.Behaviour, null, this.executor);
    }

    public IAttribute<NotifyAppLocale> appLocaleAttribute() {
        return new OneCoreWSNotification(this.project, this.transport, NotifyAppLocale.class, ESubjectType.Behaviour, null, this.executor);
    }

    public IAttribute<NotifyCloseMainActivity> closeMainActivityAttribute() {
        return new OneCoreWSNotification(this.project, this.transport, NotifyCloseMainActivity.class, ESubjectType.Behaviour, null, this.executor);
    }

    public IAttribute<NotifyCloseAndRestartApp> closeAndRestartAppAttribute() {
        return new OneCoreWSNotification(this.project, this.transport, NotifyCloseAndRestartApp.class, ESubjectType.Behaviour, null, this.executor);
    }

    public IAttribute<NotifyCloseApp> closeAppAttribute() {
        return new OneCoreWSNotification(this.project, this.transport, NotifyCloseApp.class, ESubjectType.Behaviour, null, this.executor);
    }

    public IAttribute<NotifyShowDiagnoseSetting> showDiagnoseSettingAttribute() {
        return new OneCoreWSNotification(this.project, this.transport, NotifyShowDiagnoseSetting.class, ESubjectType.Behaviour, null, this.executor);
    }

    public IAttribute<NotifySwitchNaviToBackground> switchNaviToBackgroundAttribute() {
        return new OneCoreWSNotification(this.project, this.transport, NotifySwitchNaviToBackground.class, ESubjectType.Behaviour, null, this.executor);
    }

    public IAttribute<NotifyImmersionStatus> immersionStatusAttribute() {
        return new OneCoreWSNotification(this.project, this.transport, NotifyImmersionStatus.class, ESubjectType.Behaviour, null, this.executor);
    }

    public IAttribute<NotifyDeleteAllDBData> deleteAllDBDataAttribute() {
        return new OneCoreWSNotification(this.project, this.transport, NotifyDeleteAllDBData.class, ESubjectType.Behaviour, null, this.executor);
    }

    public IAttribute<NotifyDBDataIntegrityCheck> dBDataIntegrityCheckAttribute() {
        return new OneCoreWSNotification(this.project, this.transport, NotifyDBDataIntegrityCheck.class, ESubjectType.Behaviour, null, this.executor);
    }

    public IAttribute<NotifyNaviPresentationInfo> naviPresentationInfoAttribute() {
        return new OneCoreWSNotification(this.project, this.transport, NotifyNaviPresentationInfo.class, ESubjectType.Behaviour, null, this.executor);
    }

    public IAttribute<NotifyExtractFiles> extractFilesAttribute() {
        return new OneCoreWSNotification(this.project, this.transport, NotifyExtractFiles.class, ESubjectType.Behaviour, null, this.executor);
    }

    public IAttribute<NotifyCopyFiles> copyFilesAttribute() {
        return new OneCoreWSNotification(this.project, this.transport, NotifyCopyFiles.class, ESubjectType.Behaviour, null, this.executor);
    }

    public IAttribute<NotifyLocalDBVersionInfo> localDBVersionInfoAttribute() {
        return new OneCoreWSNotification(this.project, this.transport, NotifyLocalDBVersionInfo.class, ESubjectType.Behaviour, null, this.executor);
    }

    public IAttribute<NotifySoftwareVersionInfo> softwareVersionInfoAttribute() {
        return new OneCoreWSNotification(this.project, this.transport, NotifySoftwareVersionInfo.class, ESubjectType.Behaviour, null, this.executor);
    }

    public IAttribute<NotifyNotifyOpenGPS> notifyOpenGPSAttribute() {
        return new OneCoreWSNotification(this.project, this.transport, NotifyNotifyOpenGPS.class, ESubjectType.Behaviour, null, this.executor);
    }

    public IAttribute<NotifyObserveWeatherInfo2System> observeWeatherInfo2SystemAttribute() {
        return new OneCoreWSNotification(this.project, this.transport, NotifyObserveWeatherInfo2System.class, ESubjectType.Behaviour, null, this.executor);
    }

    public IAttribute<NotifyDelegateSystemEvent> delegateSystemEventAttribute() {
        return new OneCoreWSNotification(this.project, this.transport, NotifyDelegateSystemEvent.class, ESubjectType.Behaviour, null, this.executor);
    }

    public IAttribute<NotifyLoadURL> loadURLAttribute() {
        return new OneCoreWSNotification(this.project, this.transport, NotifyLoadURL.class, ESubjectType.Behaviour, null, this.executor);
    }

    public IAttribute<NotifyGetLowFuelStatus> getLowFuelStatusAttribute() {
        return new OneCoreWSNotification(this.project, this.transport, NotifyGetLowFuelStatus.class, ESubjectType.Behaviour, null, this.executor);
    }
}
