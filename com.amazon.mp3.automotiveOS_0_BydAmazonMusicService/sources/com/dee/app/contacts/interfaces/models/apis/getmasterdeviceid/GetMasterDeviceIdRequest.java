package com.dee.app.contacts.interfaces.models.apis.getmasterdeviceid;

import com.dee.app.contacts.interfaces.models.data.enums.SyncType;

/* JADX INFO: loaded from: classes5.dex */
public class GetMasterDeviceIdRequest {
    private GetMasterDeviceIdQueryConfig queryConfig;
    private String userId;

    public GetMasterDeviceIdRequest() {
        this.queryConfig = new GetMasterDeviceIdQueryConfig(SyncType.MASTER);
    }

    public GetMasterDeviceIdRequest(String str, GetMasterDeviceIdQueryConfig getMasterDeviceIdQueryConfig) {
        this.userId = str;
        this.queryConfig = getMasterDeviceIdQueryConfig;
    }

    public GetMasterDeviceIdQueryConfig getQueryConfig() {
        return this.queryConfig;
    }

    public String getUserId() {
        return this.userId;
    }
}
