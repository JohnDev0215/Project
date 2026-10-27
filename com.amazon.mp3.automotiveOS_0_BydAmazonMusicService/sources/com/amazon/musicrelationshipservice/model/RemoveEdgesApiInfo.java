package com.amazon.musicrelationshipservice.model;

import com.amazon.hermes.ApiInfo;
import com.amazon.hermes.ServiceInfo;

/* JADX INFO: loaded from: classes5.dex */
public class RemoveEdgesApiInfo implements ApiInfo {
    @Override // com.amazon.hermes.ApiInfo
    public String getApiName() {
        return "RemoveEdges";
    }

    @Override // com.amazon.hermes.ApiInfo
    public String getApiVersion() {
        return "";
    }

    @Override // com.amazon.hermes.ApiInfo
    public ServiceInfo getServiceInfo() {
        return new MusicRelationshipServiceServiceInfo();
    }
}
