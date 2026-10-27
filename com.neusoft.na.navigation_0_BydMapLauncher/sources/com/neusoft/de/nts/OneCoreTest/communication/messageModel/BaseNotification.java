package com.neusoft.de.nts.OneCoreTest.communication.messageModel;

import com.google.gson.Gson;
import java.util.Objects;

/* JADX INFO: loaded from: classes2.dex */
public class BaseNotification extends BaseCommand {
    private static final long serialVersionUID = -1;
    private transient BaseRequest initialRequest;
    private Boolean invalid;

    public BaseNotification(String str) {
        super(str);
    }

    public BaseNotification(String str, String str2) {
        super(str, str2);
    }

    public BaseNotification(EMessageType eMessageType, String str, String str2) {
        super(eMessageType, str, str2);
    }

    public BaseRequest getInitialRequest() {
        return this.initialRequest;
    }

    public void setInitialRequest(BaseRequest baseRequest) {
        this.initialRequest = baseRequest;
    }

    public Boolean getInvalid() {
        return this.invalid;
    }

    public void setInvalid(Boolean bool) {
        this.invalid = bool;
    }

    @Override // com.neusoft.de.nts.OneCoreTest.communication.messageModel.BaseCommand
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj instanceof BaseNotification) {
            return super.equals(obj);
        }
        return false;
    }

    @Override // com.neusoft.de.nts.OneCoreTest.communication.messageModel.BaseCommand
    public int hashCode() {
        return Objects.hash(Integer.valueOf(super.hashCode()));
    }

    @Override // com.neusoft.de.nts.OneCoreTest.communication.messageModel.BaseCommand
    public BaseNotification shallowCopy() {
        return deepCopy();
    }

    @Override // com.neusoft.de.nts.OneCoreTest.communication.messageModel.BaseCommand
    public BaseNotification deepCopy() {
        Gson gson = new Gson();
        return (BaseNotification) gson.fromJson(gson.toJson(this), BaseNotification.class);
    }
}
