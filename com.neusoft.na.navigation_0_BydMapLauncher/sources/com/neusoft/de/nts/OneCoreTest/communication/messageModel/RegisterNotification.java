package com.neusoft.de.nts.OneCoreTest.communication.messageModel;

/* JADX INFO: loaded from: classes2.dex */
public class RegisterNotification extends BaseNotification {
    private Boolean forceSend;

    public RegisterNotification(EMessageType eMessageType, String str, String str2) {
        super(eMessageType, str, str2);
        this.forceSend = null;
    }

    public RegisterNotification(EMessageType eMessageType, String str, String str2, boolean z) {
        super(eMessageType, str, str2);
        this.forceSend = Boolean.valueOf(z);
    }

    public Boolean getForceSend() {
        return this.forceSend;
    }

    public void setForceSend(Boolean bool) {
        this.forceSend = bool;
    }
}
