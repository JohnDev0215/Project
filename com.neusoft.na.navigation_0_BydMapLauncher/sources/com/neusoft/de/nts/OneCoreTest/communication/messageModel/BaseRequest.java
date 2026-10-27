package com.neusoft.de.nts.OneCoreTest.communication.messageModel;

import com.google.gson.Gson;
import java.util.Objects;

/* JADX INFO: loaded from: classes2.dex */
public class BaseRequest extends BaseCommand {
    private static final long serialVersionUID = -1;
    private transient BaseResponse response;
    private String senderRoutingKey;

    public BaseRequest(String str) {
        super(str);
    }

    public BaseRequest(String str, String str2) {
        super(str, str2);
    }

    public BaseRequest(EMessageType eMessageType, String str, String str2) {
        super(eMessageType, str, str2);
    }

    public BaseResponse getResponse() {
        return this.response;
    }

    public void setResponse(BaseResponse baseResponse) {
        this.response = baseResponse;
    }

    public String getSenderRoutingKey() {
        return this.senderRoutingKey;
    }

    public void setSenderRoutingKey(String str) {
        this.senderRoutingKey = str;
    }

    @Override // com.neusoft.de.nts.OneCoreTest.communication.messageModel.BaseCommand
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj instanceof BaseRequest) {
            return super.equals(obj) && ((BaseRequest) obj).senderRoutingKey.equals(this.senderRoutingKey);
        }
        return false;
    }

    @Override // com.neusoft.de.nts.OneCoreTest.communication.messageModel.BaseCommand
    public int hashCode() {
        return Objects.hash(Integer.valueOf(super.hashCode()), this.senderRoutingKey);
    }

    @Override // com.neusoft.de.nts.OneCoreTest.communication.messageModel.BaseCommand
    public BaseRequest shallowCopy() {
        return deepCopy();
    }

    @Override // com.neusoft.de.nts.OneCoreTest.communication.messageModel.BaseCommand
    public BaseRequest deepCopy() {
        Gson gson = new Gson();
        return (BaseRequest) gson.fromJson(gson.toJson(this), BaseRequest.class);
    }
}
