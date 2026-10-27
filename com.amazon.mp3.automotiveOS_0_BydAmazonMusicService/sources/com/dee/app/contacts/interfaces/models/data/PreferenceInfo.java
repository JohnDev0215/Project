package com.dee.app.contacts.interfaces.models.data;

import com.fasterxml.jackson.annotation.JsonProperty;

/* JADX INFO: loaded from: classes5.dex */
public class PreferenceInfo {

    @JsonProperty("key")
    private String key;

    @JsonProperty("value")
    private String value;

    public String getKey() {
        return this.key;
    }

    public String getValue() {
        return this.value;
    }
}
