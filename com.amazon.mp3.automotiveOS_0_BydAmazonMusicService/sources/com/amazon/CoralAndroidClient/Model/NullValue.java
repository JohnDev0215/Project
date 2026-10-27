package com.amazon.CoralAndroidClient.Model;

import org.json.JSONObject;

/* JADX INFO: loaded from: classes2.dex */
public class NullValue implements Value {
    @Override // com.amazon.CoralAndroidClient.Model.Value
    public Object toJsonInternal() {
        return JSONObject.NULL;
    }
}
