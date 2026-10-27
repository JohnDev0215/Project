package com.amazon.CoralAndroidClient.ClientBase;

import com.amazon.CoralAndroidClient.Exception.NativeException;
import com.amazon.CoralAndroidClient.Model.StructureValue;
import java.util.HashMap;
import java.util.Map;

/* JADX INFO: loaded from: classes2.dex */
public class ClientRequest {
    private String mOperationName;
    private StructureValue mRequestParameter;
    private String mRequestParameterString;
    private Map<String, String> mAdditionalHeaders = new HashMap();
    private String mResAddress = "";

    public ClientRequest(String str, StructureValue structureValue) {
        setOperationName(str);
        setRequestParameter(structureValue);
    }

    public ClientRequest(String str, String str2) {
        setOperationName(str);
        this.mRequestParameterString = str2;
        this.mRequestParameter = null;
    }

    public Map<String, String> getHeaders() {
        return new HashMap(this.mAdditionalHeaders);
    }

    public void setRequestParameter(StructureValue structureValue) {
        if (structureValue == null) {
            this.mRequestParameter = new StructureValue();
        } else {
            this.mRequestParameter = structureValue;
            this.mRequestParameterString = null;
        }
    }

    public String getOperationName() {
        return this.mOperationName;
    }

    public void setOperationName(String str) {
        if (Helper.isStringNullOrEmpty(str)) {
            str = "AnonymousOperation";
        }
        this.mOperationName = str;
    }

    public String toJson() throws NativeException {
        StructureValue structureValue = this.mRequestParameter;
        if (structureValue == null) {
            String str = this.mRequestParameterString;
            return str != null ? str : "{}";
        }
        return structureValue.toJsonObject().toString();
    }
}
