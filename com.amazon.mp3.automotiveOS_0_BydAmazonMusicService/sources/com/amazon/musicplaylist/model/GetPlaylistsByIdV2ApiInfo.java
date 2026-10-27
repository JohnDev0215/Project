package com.amazon.musicplaylist.model;

import com.amazon.hermes.ApiInfo;
import com.amazon.hermes.ServiceInfo;

/* JADX INFO: loaded from: classes5.dex */
public class GetPlaylistsByIdV2ApiInfo implements ApiInfo {
    @Override // com.amazon.hermes.ApiInfo
    public String getApiName() {
        return "getPlaylistsByIdV2";
    }

    @Override // com.amazon.hermes.ApiInfo
    public String getApiVersion() {
        return "";
    }

    @Override // com.amazon.hermes.ApiInfo
    public ServiceInfo getServiceInfo() {
        return new MusicPlaylistServiceServiceInfo();
    }
}
