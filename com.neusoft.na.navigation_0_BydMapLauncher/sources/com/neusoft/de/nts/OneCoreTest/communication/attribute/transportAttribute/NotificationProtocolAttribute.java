package com.neusoft.de.nts.OneCoreTest.communication.attribute.transportAttribute;

import com.neusoft.de.nts.OneCoreTest.communication.attribute.WSNotification;
import com.neusoft.de.nts.OneCoreTest.communication.messageModel.transportMessages.BaseProtocolError;
import com.neusoft.de.nts.OneCoreTest.interfaces.ITransport;
import com.neusoft.de.nts.OneCoreTest.utils.CommandMatchUtil;
import com.neusoft.de.nts.OneCoreTest.utils.EProjectInterfaceType;
import java.util.concurrent.Executor;

/* JADX INFO: loaded from: classes2.dex */
public class NotificationProtocolAttribute<A extends BaseProtocolError> extends WSNotification<A> {
    public NotificationProtocolAttribute(EProjectInterfaceType eProjectInterfaceType, ITransport<String, String> iTransport, Class<A> cls) {
        super(eProjectInterfaceType, iTransport, cls);
    }

    public NotificationProtocolAttribute(EProjectInterfaceType eProjectInterfaceType, ITransport<String, String> iTransport, Class<A> cls, Executor executor) {
        super(eProjectInterfaceType, iTransport, cls, executor);
    }

    @Override // com.neusoft.de.nts.OneCoreTest.communication.attribute.WSNotification, com.neusoft.de.nts.OneCoreTest.interfaces.IValueUpdater
    public boolean match(String str) {
        return CommandMatchUtil.matchTransportError((BaseProtocolError) getProject().getProtocol().from(str, this.notifyClass));
    }
}
