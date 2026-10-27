package com.dee.app.contacts.interfaces.models.apis.batchupdatecontacts;

/* JADX INFO: loaded from: classes5.dex */
public class BatchUpdateLABContactsRequest {
    private BatchUpdateLABContactsBody body;
    private BatchUpdateLABContactsQueryConfig queryConfig;
    private String userId;

    public BatchUpdateLABContactsRequest(String str, BatchUpdateLABContactsQueryConfig batchUpdateLABContactsQueryConfig, BatchUpdateLABContactsBody batchUpdateLABContactsBody) {
        this.userId = str;
        this.queryConfig = batchUpdateLABContactsQueryConfig;
        this.body = batchUpdateLABContactsBody;
    }

    public String getUserId() {
        return this.userId;
    }

    public BatchUpdateLABContactsQueryConfig getQueryConfig() {
        return this.queryConfig;
    }

    public BatchUpdateLABContactsBody getBody() {
        return this.body;
    }
}
