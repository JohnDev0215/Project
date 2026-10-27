package api.notifications.com.neusoft.greatwall.greatwallsysapiinverse;

import com.neusoft.de.nts.OneCoreTest.communication.attribute.ESubjectType;
import com.neusoft.de.nts.OneCoreTest.communication.attribute.OneCoreWSNotification;
import com.neusoft.de.nts.OneCoreTest.interfaces.IAttribute;
import com.neusoft.de.nts.OneCoreTest.interfaces.ICommunicationContextUtil;
import com.neusoft.de.nts.OneCoreTest.interfaces.ITransport;
import com.neusoft.de.nts.OneCoreTest.utils.EProjectInterfaceType;
import java.util.concurrent.Executor;

/* JADX INFO: loaded from: classes.dex */
public class GreatWallSysApiInverseNotifications {
    private final Executor executor;
    private final EProjectInterfaceType project = EProjectInterfaceType.OneCore;
    private final ITransport<String, String> transport;

    @Deprecated
    public GreatWallSysApiInverseNotifications(ITransport<String, String> iTransport, Executor executor) {
        this.transport = iTransport;
        this.executor = executor;
    }

    public GreatWallSysApiInverseNotifications(ICommunicationContextUtil<?, String, String> iCommunicationContextUtil) {
        this.transport = iCommunicationContextUtil.getDefaultTransport();
        this.executor = iCommunicationContextUtil.getDefaultExecutor();
    }

    public IAttribute<NotifyDayNightStatusUpdate> dayNightStatusUpdateAttribute() {
        return new OneCoreWSNotification(this.project, this.transport, NotifyDayNightStatusUpdate.class, ESubjectType.Behaviour, null, this.executor);
    }

    public IAttribute<NotifyTunnelStatus> tunnelStatusAttribute() {
        return new OneCoreWSNotification(this.project, this.transport, NotifyTunnelStatus.class, ESubjectType.Behaviour, null, this.executor);
    }

    public IAttribute<NotifyCongestedRoadStatus> congestedRoadStatusAttribute() {
        return new OneCoreWSNotification(this.project, this.transport, NotifyCongestedRoadStatus.class, ESubjectType.Behaviour, null, this.executor);
    }

    public IAttribute<NotifyGwCurrentWTInfo> gwCurrentWTInfoAttribute() {
        return new OneCoreWSNotification(this.project, this.transport, NotifyGwCurrentWTInfo.class, ESubjectType.Behaviour, null, this.executor);
    }
}
