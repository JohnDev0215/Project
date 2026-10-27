package api.notifications.com.neusoft.ecarx.ecarxsysapiinverse;

import com.neusoft.de.nts.OneCoreTest.communication.attribute.ESubjectType;
import com.neusoft.de.nts.OneCoreTest.communication.attribute.OneCoreWSNotification;
import com.neusoft.de.nts.OneCoreTest.interfaces.IAttribute;
import com.neusoft.de.nts.OneCoreTest.interfaces.ICommunicationContextUtil;
import com.neusoft.de.nts.OneCoreTest.interfaces.ITransport;
import com.neusoft.de.nts.OneCoreTest.utils.EProjectInterfaceType;
import java.util.concurrent.Executor;

/* JADX INFO: loaded from: classes.dex */
public class EcarxSysApiInverseNotifications {
    private final Executor executor;
    private final EProjectInterfaceType project = EProjectInterfaceType.OneCore;
    private final ITransport<String, String> transport;

    @Deprecated
    public EcarxSysApiInverseNotifications(ITransport<String, String> iTransport, Executor executor) {
        this.transport = iTransport;
        this.executor = executor;
    }

    public EcarxSysApiInverseNotifications(ICommunicationContextUtil<?, String, String> iCommunicationContextUtil) {
        this.transport = iCommunicationContextUtil.getDefaultTransport();
        this.executor = iCommunicationContextUtil.getDefaultExecutor();
    }

    public IAttribute<NotifyTunnelStatus> tunnelStatusAttribute() {
        return new OneCoreWSNotification(this.project, this.transport, NotifyTunnelStatus.class, ESubjectType.Behaviour, null, this.executor);
    }

    public IAttribute<NotifyAuthStatus> authStatusAttribute() {
        return new OneCoreWSNotification(this.project, this.transport, NotifyAuthStatus.class, ESubjectType.Behaviour, null, this.executor);
    }

    public IAttribute<NotifyQueryCurrentWeatherInfo> queryCurrentWeatherInfoAttribute() {
        return new OneCoreWSNotification(this.project, this.transport, NotifyQueryCurrentWeatherInfo.class, ESubjectType.Behaviour, null, this.executor);
    }

    public IAttribute<NotifyQueryFutureWeatherInfo> queryFutureWeatherInfoAttribute() {
        return new OneCoreWSNotification(this.project, this.transport, NotifyQueryFutureWeatherInfo.class, ESubjectType.Behaviour, null, this.executor);
    }
}
