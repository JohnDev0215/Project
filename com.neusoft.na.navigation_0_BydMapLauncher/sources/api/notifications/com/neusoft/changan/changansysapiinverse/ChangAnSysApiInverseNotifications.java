package api.notifications.com.neusoft.changan.changansysapiinverse;

import com.neusoft.de.nts.OneCoreTest.communication.attribute.ESubjectType;
import com.neusoft.de.nts.OneCoreTest.communication.attribute.OneCoreWSNotification;
import com.neusoft.de.nts.OneCoreTest.interfaces.IAttribute;
import com.neusoft.de.nts.OneCoreTest.interfaces.ICommunicationContextUtil;
import com.neusoft.de.nts.OneCoreTest.interfaces.ITransport;
import com.neusoft.de.nts.OneCoreTest.utils.EProjectInterfaceType;
import java.util.concurrent.Executor;

/* JADX INFO: loaded from: classes.dex */
public class ChangAnSysApiInverseNotifications {
    private final Executor executor;
    private final EProjectInterfaceType project = EProjectInterfaceType.OneCore;
    private final ITransport<String, String> transport;

    @Deprecated
    public ChangAnSysApiInverseNotifications(ITransport<String, String> iTransport, Executor executor) {
        this.transport = iTransport;
        this.executor = executor;
    }

    public ChangAnSysApiInverseNotifications(ICommunicationContextUtil<?, String, String> iCommunicationContextUtil) {
        this.transport = iCommunicationContextUtil.getDefaultTransport();
        this.executor = iCommunicationContextUtil.getDefaultExecutor();
    }

    public IAttribute<NotifySoundTypeChanged> soundTypeChangedAttribute() {
        return new OneCoreWSNotification(this.project, this.transport, NotifySoundTypeChanged.class, ESubjectType.Behaviour, null, this.executor);
    }
}
