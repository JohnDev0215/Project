package com.neusoft.de.nts.OneCoreTest.communication.messageModel.transportMessages;

import com.neusoft.de.nts.OneCoreTest.communication.messageModel.BaseNotification;
import com.neusoft.de.nts.OneCoreTest.communication.messageModel.EMessageType;

/* JADX INFO: loaded from: classes2.dex */
public class BaseProtocolError extends BaseNotification {
    private String builtinError;
    private String description;

    public BaseProtocolError(String str) {
        super(EMessageType.Error, str, null);
    }

    public String getDescription() {
        return this.description;
    }

    public void setDescription(String str) {
        this.description = str;
    }

    public String getBuiltinError() {
        return this.builtinError;
    }

    public void setBuiltinError(String str) {
        this.builtinError = str;
    }
}
