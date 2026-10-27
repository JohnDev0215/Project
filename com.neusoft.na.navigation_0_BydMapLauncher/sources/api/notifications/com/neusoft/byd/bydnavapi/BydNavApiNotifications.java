package api.notifications.com.neusoft.byd.bydnavapi;

import com.neusoft.de.nts.OneCoreTest.communication.attribute.ESubjectType;
import com.neusoft.de.nts.OneCoreTest.communication.attribute.OneCoreWSNotification;
import com.neusoft.de.nts.OneCoreTest.interfaces.IAttribute;
import com.neusoft.de.nts.OneCoreTest.interfaces.ICommunicationContextUtil;
import com.neusoft.de.nts.OneCoreTest.interfaces.ITransport;
import com.neusoft.de.nts.OneCoreTest.utils.EProjectInterfaceType;
import java.util.concurrent.Executor;

/* JADX INFO: loaded from: classes.dex */
public class BydNavApiNotifications {
    private final Executor executor;
    private final EProjectInterfaceType project = EProjectInterfaceType.OneCore;
    private final ITransport<String, String> transport;

    @Deprecated
    public BydNavApiNotifications(ITransport<String, String> iTransport, Executor executor) {
        this.transport = iTransport;
        this.executor = executor;
    }

    public BydNavApiNotifications(ICommunicationContextUtil<?, String, String> iCommunicationContextUtil) {
        this.transport = iCommunicationContextUtil.getDefaultTransport();
        this.executor = iCommunicationContextUtil.getDefaultExecutor();
    }

    public IAttribute<NotifyGuidanceInfoUpdate> guidanceInfoUpdateAttribute() {
        return new OneCoreWSNotification(this.project, this.transport, NotifyGuidanceInfoUpdate.class, ESubjectType.Behaviour, null, this.executor);
    }

    public IAttribute<NotifyGuidanceStatus> guidanceStatusAttribute() {
        return new OneCoreWSNotification(this.project, this.transport, NotifyGuidanceStatus.class, ESubjectType.Behaviour, null, this.executor);
    }

    public IAttribute<NotifyReportLocationInfo> reportLocationInfoAttribute() {
        return new OneCoreWSNotification(this.project, this.transport, NotifyReportLocationInfo.class, ESubjectType.Behaviour, null, this.executor);
    }

    public IAttribute<NotifyReqGPSInfo> reqGPSInfoAttribute() {
        return new OneCoreWSNotification(this.project, this.transport, NotifyReqGPSInfo.class, ESubjectType.Behaviour, null, this.executor);
    }

    public IAttribute<NotifyDelPrivacyPolicyInfo> delPrivacyPolicyInfoAttribute() {
        return new OneCoreWSNotification(this.project, this.transport, NotifyDelPrivacyPolicyInfo.class, ESubjectType.Behaviour, null, this.executor);
    }

    public IAttribute<NotifyUpdatePrivacyPolicySyncStatus> updatePrivacyPolicySyncStatusAttribute() {
        return new OneCoreWSNotification(this.project, this.transport, NotifyUpdatePrivacyPolicySyncStatus.class, ESubjectType.Behaviour, null, this.executor);
    }

    public IAttribute<NotifyRevokePrivacyPolicy> revokePrivacyPolicyAttribute() {
        return new OneCoreWSNotification(this.project, this.transport, NotifyRevokePrivacyPolicy.class, ESubjectType.Behaviour, null, this.executor);
    }

    public IAttribute<NotifyClusterViewMode> clusterViewModeAttribute() {
        return new OneCoreWSNotification(this.project, this.transport, NotifyClusterViewMode.class, ESubjectType.Behaviour, null, this.executor);
    }

    public IAttribute<NotifySwitchDesktopMode> switchDesktopModeAttribute() {
        return new OneCoreWSNotification(this.project, this.transport, NotifySwitchDesktopMode.class, ESubjectType.Behaviour, null, this.executor);
    }

    public IAttribute<NotifyIsNaviHMIFullScreenPage> isNaviHMIFullScreenPageAttribute() {
        return new OneCoreWSNotification(this.project, this.transport, NotifyIsNaviHMIFullScreenPage.class, ESubjectType.Behaviour, null, this.executor);
    }

    public IAttribute<NotifyAuthInfo> authInfoAttribute() {
        return new OneCoreWSNotification(this.project, this.transport, NotifyAuthInfo.class, ESubjectType.Behaviour, null, this.executor);
    }

    public IAttribute<NotifyVerifyInfo> verifyInfoAttribute() {
        return new OneCoreWSNotification(this.project, this.transport, NotifyVerifyInfo.class, ESubjectType.Behaviour, null, this.executor);
    }
}
