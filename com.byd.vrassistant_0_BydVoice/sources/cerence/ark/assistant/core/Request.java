package cerence.ark.assistant.core;

import cerence.ark.assistant.api.IRequest;

/* JADX INFO: loaded from: classes.dex */
class Request implements IRequest {
    private String mDomain;
    private String mName;
    private String mParams;
    private String mRequestJson;

    Request() {
    }

    Request setName(String str) {
        this.mName = str;
        return this;
    }

    Request setDomain(String str) {
        this.mDomain = str;
        return this;
    }

    Request setParams(String str) {
        this.mParams = str;
        return this;
    }

    Request setRequestJson(String str) {
        this.mRequestJson = str;
        return this;
    }

    @Override // cerence.ark.assistant.api.IRequest
    public String getName() {
        return this.mName;
    }

    @Override // cerence.ark.assistant.api.IRequest
    public String getDomainName() {
        return this.mDomain;
    }

    @Override // cerence.ark.assistant.api.IRequest
    public String getParams() {
        return this.mParams;
    }

    @Override // cerence.ark.assistant.api.IRequest, cerence.ark.assistant.core.JsonCompliant
    public String toJsonString() {
        return this.mRequestJson;
    }
}
