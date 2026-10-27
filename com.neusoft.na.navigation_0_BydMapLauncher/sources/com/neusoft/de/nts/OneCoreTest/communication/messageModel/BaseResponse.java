package com.neusoft.de.nts.OneCoreTest.communication.messageModel;

import com.google.gson.Gson;
import java.util.Objects;

/* JADX INFO: loaded from: classes2.dex */
public class BaseResponse extends BaseCommand {
    private static final long serialVersionUID = -1;
    private BaseRequest originalRequest;
    private String senderRoutingKey;

    public BaseResponse() {
        super("empty");
    }

    public BaseResponse(String str) {
        super(str);
    }

    public BaseResponse(String str, String str2) {
        super(str, str2);
    }

    public BaseResponse(EMessageType eMessageType, String str, String str2) {
        super(eMessageType, str, str2);
    }

    public BaseRequest getOriginalRequest() {
        return this.originalRequest;
    }

    public void setOriginalRequest(BaseRequest baseRequest) {
        this.originalRequest = baseRequest;
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
        if (!(obj instanceof BaseResponse)) {
            return false;
        }
        BaseResponse baseResponse = (BaseResponse) obj;
        return super.equals(obj) && baseResponse.originalRequest.equals(this.originalRequest) && baseResponse.senderRoutingKey.equals(this.senderRoutingKey);
    }

    @Override // com.neusoft.de.nts.OneCoreTest.communication.messageModel.BaseCommand
    public int hashCode() {
        return Objects.hash(Integer.valueOf(super.hashCode()), this.originalRequest, this.senderRoutingKey);
    }

    @Override // com.neusoft.de.nts.OneCoreTest.communication.messageModel.BaseCommand
    public BaseResponse shallowCopy() {
        return deepCopy();
    }

    @Override // com.neusoft.de.nts.OneCoreTest.communication.messageModel.BaseCommand
    public BaseResponse deepCopy() {
        Gson gson = new Gson();
        return (BaseResponse) gson.fromJson(gson.toJson(this), BaseResponse.class);
    }
}
