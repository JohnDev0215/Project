package api.notifications.com.neusoft.onecore.pos.sensordata;

import com.neusoft.de.nts.OneCoreTest.communication.attribute.ESubjectType;
import com.neusoft.de.nts.OneCoreTest.communication.attribute.OneCoreWSNotification;
import com.neusoft.de.nts.OneCoreTest.interfaces.IBroadcast;
import com.neusoft.de.nts.OneCoreTest.interfaces.ICommunicationContextUtil;
import com.neusoft.de.nts.OneCoreTest.interfaces.IGettableAttribute;
import com.neusoft.de.nts.OneCoreTest.interfaces.ITransport;
import com.neusoft.de.nts.OneCoreTest.utils.EProjectInterfaceType;
import java.util.concurrent.Executor;

/* JADX INFO: loaded from: classes.dex */
public class SensorDataNotifications {
    private final Executor executor;
    private final EProjectInterfaceType project = EProjectInterfaceType.OneCore;
    private final ITransport<String, String> transport;

    @Deprecated
    public SensorDataNotifications(ITransport<String, String> iTransport, Executor executor) {
        this.transport = iTransport;
        this.executor = executor;
    }

    public SensorDataNotifications(ICommunicationContextUtil<?, String, String> iCommunicationContextUtil) {
        this.transport = iCommunicationContextUtil.getDefaultTransport();
        this.executor = iCommunicationContextUtil.getDefaultExecutor();
    }

    public IGettableAttribute<NotifyMapMatchFeedback> mapMatchFeedbackAttribute() {
        return new OneCoreWSNotification(this.project, this.transport, NotifyMapMatchFeedback.class, ESubjectType.Behaviour, null, this.executor);
    }

    public IBroadcast<NotifyMapMatchFeedback> mapMatchFeedbackBroadcast() {
        return new OneCoreWSNotification(this.project, this.transport, NotifyMapMatchFeedback.class, ESubjectType.Publish, null, this.executor);
    }
}
