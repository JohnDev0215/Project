package api.notifications.com.neusoft.ecarx.ecarxnavapi;

import com.neusoft.de.nts.OneCoreTest.communication.attribute.ESubjectType;
import com.neusoft.de.nts.OneCoreTest.communication.attribute.OneCoreWSNotification;
import com.neusoft.de.nts.OneCoreTest.interfaces.IAttribute;
import com.neusoft.de.nts.OneCoreTest.interfaces.ICommunicationContextUtil;
import com.neusoft.de.nts.OneCoreTest.interfaces.ITransport;
import com.neusoft.de.nts.OneCoreTest.utils.EProjectInterfaceType;
import java.util.concurrent.Executor;

/* JADX INFO: loaded from: classes.dex */
public class EcarxNavApiNotifications {
    private final Executor executor;
    private final EProjectInterfaceType project = EProjectInterfaceType.OneCore;
    private final ITransport<String, String> transport;

    @Deprecated
    public EcarxNavApiNotifications(ITransport<String, String> iTransport, Executor executor) {
        this.transport = iTransport;
        this.executor = executor;
    }

    public EcarxNavApiNotifications(ICommunicationContextUtil<?, String, String> iCommunicationContextUtil) {
        this.transport = iCommunicationContextUtil.getDefaultTransport();
        this.executor = iCommunicationContextUtil.getDefaultExecutor();
    }

    public IAttribute<NotifyGuidanceInfoUpdate> guidanceInfoUpdateAttribute() {
        return new OneCoreWSNotification(this.project, this.transport, NotifyGuidanceInfoUpdate.class, ESubjectType.Behaviour, null, this.executor);
    }

    public IAttribute<NotifyNaviStatusUpdate> naviStatusUpdateAttribute() {
        return new OneCoreWSNotification(this.project, this.transport, NotifyNaviStatusUpdate.class, ESubjectType.Behaviour, null, this.executor);
    }

    public IAttribute<NotifyMapStatusUpdate> mapStatusUpdateAttribute() {
        return new OneCoreWSNotification(this.project, this.transport, NotifyMapStatusUpdate.class, ESubjectType.Behaviour, null, this.executor);
    }

    public IAttribute<NotifyRouteViewStatusUpdate> routeViewStatusUpdateAttribute() {
        return new OneCoreWSNotification(this.project, this.transport, NotifyRouteViewStatusUpdate.class, ESubjectType.Behaviour, null, this.executor);
    }

    public IAttribute<NotifyTrafficStatusUpdate> trafficStatusUpdateAttribute() {
        return new OneCoreWSNotification(this.project, this.transport, NotifyTrafficStatusUpdate.class, ESubjectType.Behaviour, null, this.executor);
    }

    public IAttribute<NotifyZoomStatusUpdate> zoomStatusUpdateAttribute() {
        return new OneCoreWSNotification(this.project, this.transport, NotifyZoomStatusUpdate.class, ESubjectType.Behaviour, null, this.executor);
    }

    public IAttribute<NotifyDayNightStatusUpdate> dayNightStatusUpdateAttribute() {
        return new OneCoreWSNotification(this.project, this.transport, NotifyDayNightStatusUpdate.class, ESubjectType.Behaviour, null, this.executor);
    }

    public IAttribute<NotifyViewModeUpdate> viewModeUpdateAttribute() {
        return new OneCoreWSNotification(this.project, this.transport, NotifyViewModeUpdate.class, ESubjectType.Behaviour, null, this.executor);
    }

    public IAttribute<NotifyMuteModeUpdate> muteModeUpdateAttribute() {
        return new OneCoreWSNotification(this.project, this.transport, NotifyMuteModeUpdate.class, ESubjectType.Behaviour, null, this.executor);
    }

    public IAttribute<NotifyFavoritesInfoUpdate> favoritesInfoUpdateAttribute() {
        return new OneCoreWSNotification(this.project, this.transport, NotifyFavoritesInfoUpdate.class, ESubjectType.Behaviour, null, this.executor);
    }

    public IAttribute<NotifyRouteInfoUpdate> routeInfoUpdateAttribute() {
        return new OneCoreWSNotification(this.project, this.transport, NotifyRouteInfoUpdate.class, ESubjectType.Behaviour, null, this.executor);
    }

    public IAttribute<NotifyRouteSelectedUpdate> routeSelectedUpdateAttribute() {
        return new OneCoreWSNotification(this.project, this.transport, NotifyRouteSelectedUpdate.class, ESubjectType.Behaviour, null, this.executor);
    }

    public IAttribute<NotifyHighwayInfoUpdate> highwayInfoUpdateAttribute() {
        return new OneCoreWSNotification(this.project, this.transport, NotifyHighwayInfoUpdate.class, ESubjectType.Behaviour, null, this.executor);
    }

    public IAttribute<NotifyServiceStatusUpdate> serviceStatusUpdateAttribute() {
        return new OneCoreWSNotification(this.project, this.transport, NotifyServiceStatusUpdate.class, ESubjectType.Behaviour, null, this.executor);
    }

    public IAttribute<NotifyArrivedDest> arrivedDestAttribute() {
        return new OneCoreWSNotification(this.project, this.transport, NotifyArrivedDest.class, ESubjectType.Behaviour, null, this.executor);
    }

    public IAttribute<NotifyADAreaUpdate> aDAreaUpdateAttribute() {
        return new OneCoreWSNotification(this.project, this.transport, NotifyADAreaUpdate.class, ESubjectType.Behaviour, null, this.executor);
    }

    public IAttribute<NotifyTrafficConditionUpdate> trafficConditionUpdateAttribute() {
        return new OneCoreWSNotification(this.project, this.transport, NotifyTrafficConditionUpdate.class, ESubjectType.Behaviour, null, this.executor);
    }

    public IAttribute<NotifyLanesInfoUpdate> lanesInfoUpdateAttribute() {
        return new OneCoreWSNotification(this.project, this.transport, NotifyLanesInfoUpdate.class, ESubjectType.Behaviour, null, this.executor);
    }

    public IAttribute<NotifyCommutePush> commutePushAttribute() {
        return new OneCoreWSNotification(this.project, this.transport, NotifyCommutePush.class, ESubjectType.Behaviour, null, this.executor);
    }

    public IAttribute<NotifyAvailableRestoreGuidance> availableRestoreGuidanceAttribute() {
        return new OneCoreWSNotification(this.project, this.transport, NotifyAvailableRestoreGuidance.class, ESubjectType.Behaviour, null, this.executor);
    }

    public IAttribute<NotifyRequestSETELServiceStatus> requestSETELServiceStatusAttribute() {
        return new OneCoreWSNotification(this.project, this.transport, NotifyRequestSETELServiceStatus.class, ESubjectType.Behaviour, null, this.executor);
    }

    public IAttribute<NotifyRequestSETELData> requestSETELDataAttribute() {
        return new OneCoreWSNotification(this.project, this.transport, NotifyRequestSETELData.class, ESubjectType.Behaviour, null, this.executor);
    }

    public IAttribute<NotifyRequestSETELDetail> requestSETELDetailAttribute() {
        return new OneCoreWSNotification(this.project, this.transport, NotifyRequestSETELDetail.class, ESubjectType.Behaviour, null, this.executor);
    }

    public IAttribute<NotifySwitchVRCommand> switchVRCommandAttribute() {
        return new OneCoreWSNotification(this.project, this.transport, NotifySwitchVRCommand.class, ESubjectType.Behaviour, null, this.executor);
    }

    public IAttribute<NotifyVRMsgCommandUpdate> vRMsgCommandUpdateAttribute() {
        return new OneCoreWSNotification(this.project, this.transport, NotifyVRMsgCommandUpdate.class, ESubjectType.Behaviour, null, this.executor);
    }

    public IAttribute<NotifyGetLoginStatus> getLoginStatusAttribute() {
        return new OneCoreWSNotification(this.project, this.transport, NotifyGetLoginStatus.class, ESubjectType.Behaviour, null, this.executor);
    }

    public IAttribute<NotifyGetFavoriteList> getFavoriteListAttribute() {
        return new OneCoreWSNotification(this.project, this.transport, NotifyGetFavoriteList.class, ESubjectType.Behaviour, null, this.executor);
    }

    public IAttribute<NotifyRequestAddFavorite> requestAddFavoriteAttribute() {
        return new OneCoreWSNotification(this.project, this.transport, NotifyRequestAddFavorite.class, ESubjectType.Behaviour, null, this.executor);
    }

    public IAttribute<NotifyRequestUpdateFavorite> requestUpdateFavoriteAttribute() {
        return new OneCoreWSNotification(this.project, this.transport, NotifyRequestUpdateFavorite.class, ESubjectType.Behaviour, null, this.executor);
    }

    public IAttribute<NotifyRequestRemoveFavorite> requestRemoveFavoriteAttribute() {
        return new OneCoreWSNotification(this.project, this.transport, NotifyRequestRemoveFavorite.class, ESubjectType.Behaviour, null, this.executor);
    }
}
