package api.notifications.com.neusoft.greatwall.greatwallnavapi;

import com.neusoft.de.nts.OneCoreTest.communication.attribute.ESubjectType;
import com.neusoft.de.nts.OneCoreTest.communication.attribute.OneCoreWSNotification;
import com.neusoft.de.nts.OneCoreTest.interfaces.IAttribute;
import com.neusoft.de.nts.OneCoreTest.interfaces.ICommunicationContextUtil;
import com.neusoft.de.nts.OneCoreTest.interfaces.ITransport;
import com.neusoft.de.nts.OneCoreTest.utils.EProjectInterfaceType;
import java.util.concurrent.Executor;

/* JADX INFO: loaded from: classes.dex */
public class GreatWallNavApiNotifications {
    private final Executor executor;
    private final EProjectInterfaceType project = EProjectInterfaceType.OneCore;
    private final ITransport<String, String> transport;

    @Deprecated
    public GreatWallNavApiNotifications(ITransport<String, String> iTransport, Executor executor) {
        this.transport = iTransport;
        this.executor = executor;
    }

    public GreatWallNavApiNotifications(ICommunicationContextUtil<?, String, String> iCommunicationContextUtil) {
        this.transport = iCommunicationContextUtil.getDefaultTransport();
        this.executor = iCommunicationContextUtil.getDefaultExecutor();
    }

    public IAttribute<NotifySwitchNavigationLanguageSetting> switchNavigationLanguageSettingAttribute() {
        return new OneCoreWSNotification(this.project, this.transport, NotifySwitchNavigationLanguageSetting.class, ESubjectType.Behaviour, null, this.executor);
    }

    public IAttribute<NotifyNaviMuteStatus> naviMuteStatusAttribute() {
        return new OneCoreWSNotification(this.project, this.transport, NotifyNaviMuteStatus.class, ESubjectType.Behaviour, null, this.executor);
    }

    public IAttribute<NotifyReportLocationInfo> reportLocationInfoAttribute() {
        return new OneCoreWSNotification(this.project, this.transport, NotifyReportLocationInfo.class, ESubjectType.Behaviour, null, this.executor);
    }

    public IAttribute<NotifyResponseDirectNavigation> responseDirectNavigationAttribute() {
        return new OneCoreWSNotification(this.project, this.transport, NotifyResponseDirectNavigation.class, ESubjectType.Behaviour, null, this.executor);
    }

    public IAttribute<NotifyNaviSettingStateStatus> naviSettingStateStatusAttribute() {
        return new OneCoreWSNotification(this.project, this.transport, NotifyNaviSettingStateStatus.class, ESubjectType.Behaviour, null, this.executor);
    }

    public IAttribute<NotifyGuidanceStatus> guidanceStatusAttribute() {
        return new OneCoreWSNotification(this.project, this.transport, NotifyGuidanceStatus.class, ESubjectType.Behaviour, null, this.executor);
    }

    public IAttribute<NotifyGuidanceInfoStatus> guidanceInfoStatusAttribute() {
        return new OneCoreWSNotification(this.project, this.transport, NotifyGuidanceInfoStatus.class, ESubjectType.Behaviour, null, this.executor);
    }

    public IAttribute<NotifyNotifyHeadingStatus> notifyHeadingStatusAttribute() {
        return new OneCoreWSNotification(this.project, this.transport, NotifyNotifyHeadingStatus.class, ESubjectType.Behaviour, null, this.executor);
    }

    public IAttribute<NotifyDestinationHistoryListStatus> destinationHistoryListStatusAttribute() {
        return new OneCoreWSNotification(this.project, this.transport, NotifyDestinationHistoryListStatus.class, ESubjectType.Behaviour, null, this.executor);
    }

    public IAttribute<NotifyRequestPrivacyMode> requestPrivacyModeAttribute() {
        return new OneCoreWSNotification(this.project, this.transport, NotifyRequestPrivacyMode.class, ESubjectType.Behaviour, null, this.executor);
    }

    public IAttribute<NotifyCalcPlanningRouteStatus> calcPlanningRouteStatusAttribute() {
        return new OneCoreWSNotification(this.project, this.transport, NotifyCalcPlanningRouteStatus.class, ESubjectType.Behaviour, null, this.executor);
    }

    public IAttribute<NotifyCurrentViewInfoStatus> currentViewInfoStatusAttribute() {
        return new OneCoreWSNotification(this.project, this.transport, NotifyCurrentViewInfoStatus.class, ESubjectType.Behaviour, null, this.executor);
    }

    public IAttribute<NotifyLaneInfoStatus> laneInfoStatusAttribute() {
        return new OneCoreWSNotification(this.project, this.transport, NotifyLaneInfoStatus.class, ESubjectType.Behaviour, null, this.executor);
    }

    public IAttribute<NotifySpeedLimitStatus> speedLimitStatusAttribute() {
        return new OneCoreWSNotification(this.project, this.transport, NotifySpeedLimitStatus.class, ESubjectType.Behaviour, null, this.executor);
    }

    public IAttribute<NotifyNaviJumpToSystemPage> naviJumpToSystemPageAttribute() {
        return new OneCoreWSNotification(this.project, this.transport, NotifyNaviJumpToSystemPage.class, ESubjectType.Behaviour, null, this.executor);
    }

    public IAttribute<NotifyCountryChangedStatus> countryChangedStatusAttribute() {
        return new OneCoreWSNotification(this.project, this.transport, NotifyCountryChangedStatus.class, ESubjectType.Behaviour, null, this.executor);
    }

    public IAttribute<NotifyPostPageTransitionEvent> postPageTransitionEventAttribute() {
        return new OneCoreWSNotification(this.project, this.transport, NotifyPostPageTransitionEvent.class, ESubjectType.Behaviour, null, this.executor);
    }

    public IAttribute<NotifyPostUITouchEvent> postUITouchEventAttribute() {
        return new OneCoreWSNotification(this.project, this.transport, NotifyPostUITouchEvent.class, ESubjectType.Behaviour, null, this.executor);
    }

    public IAttribute<NotifyPostServiceEvent> postServiceEventAttribute() {
        return new OneCoreWSNotification(this.project, this.transport, NotifyPostServiceEvent.class, ESubjectType.Behaviour, null, this.executor);
    }

    public IAttribute<NotifyPostCustomEvent> postCustomEventAttribute() {
        return new OneCoreWSNotification(this.project, this.transport, NotifyPostCustomEvent.class, ESubjectType.Behaviour, null, this.executor);
    }

    public IAttribute<NotifyDeadReckoningExtendStatus> deadReckoningExtendStatusAttribute() {
        return new OneCoreWSNotification(this.project, this.transport, NotifyDeadReckoningExtendStatus.class, ESubjectType.Behaviour, null, this.executor);
    }
}
