package com.dee.app.contacts.interfaces.models.apis.batchupdatecontacts;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.List;

/* JADX INFO: loaded from: classes5.dex */
public class BatchUpdateLABContactsResponse {

    @JsonProperty("failureList")
    private List<Object> failureList;

    @JsonProperty("successList")
    private List<Object> successList;

    private BatchUpdateLABContactsResponse() {
    }

    public List<Object> getSuccessList() {
        return this.successList;
    }

    public List<Object> getFailureList() {
        return this.failureList;
    }
}
