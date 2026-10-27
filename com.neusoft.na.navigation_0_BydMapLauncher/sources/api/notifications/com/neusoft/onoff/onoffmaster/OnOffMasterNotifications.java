package api.notifications.com.neusoft.onoff.onoffmaster;

import com.neusoft.de.nts.OneCoreTest.communication.attribute.ESubjectType;
import com.neusoft.de.nts.OneCoreTest.communication.attribute.OneCoreWSNotification;
import com.neusoft.de.nts.OneCoreTest.interfaces.IBroadcast;
import com.neusoft.de.nts.OneCoreTest.interfaces.ICommunicationContextUtil;
import com.neusoft.de.nts.OneCoreTest.interfaces.IGettableAttribute;
import com.neusoft.de.nts.OneCoreTest.interfaces.ITransport;
import com.neusoft.de.nts.OneCoreTest.utils.EProjectInterfaceType;
import java.util.concurrent.Executor;

/* JADX INFO: loaded from: classes.dex */
public class OnOffMasterNotifications {
    private final Executor executor;
    private final EProjectInterfaceType project = EProjectInterfaceType.OCTA;
    private final ITransport<String, String> transport;

    @Deprecated
    public OnOffMasterNotifications(ITransport<String, String> iTransport, Executor executor) {
        this.transport = iTransport;
        this.executor = executor;
    }

    public OnOffMasterNotifications(ICommunicationContextUtil<?, String, String> iCommunicationContextUtil) {
        this.transport = iCommunicationContextUtil.getDefaultTransport();
        this.executor = iCommunicationContextUtil.getDefaultExecutor();
    }

    public IGettableAttribute<NotifyStatusChanged> statusChangedAttribute() {
        return new OneCoreWSNotification(this.project, this.transport, NotifyStatusChanged.class, ESubjectType.Behaviour, null, this.executor);
    }

    public IBroadcast<NotifyStatusChanged> statusChangedBroadcast() {
        return new OneCoreWSNotification(this.project, this.transport, NotifyStatusChanged.class, ESubjectType.Publish, null, this.executor);
    }
}
