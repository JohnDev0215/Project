package cerence.ark.assistant.controller.domain.ude.param;

import cerence.ark.assistant.core.JsonCompliant;
import org.json.JSONException;
import org.json.JSONObject;

/* JADX INFO: loaded from: classes2.dex */
public class GetAddressByTypeParam implements JsonCompliant {
    private String addressType;

    public void setAddressType(String str) {
        this.addressType = str;
    }

    public String getAddressType() {
        return this.addressType;
    }

    public static GetAddressByTypeParam fromJson(String str) throws JSONException {
        GetAddressByTypeParam getAddressByTypeParam = new GetAddressByTypeParam();
        getAddressByTypeParam.addressType = new JSONObject(str).optString("address_type");
        return getAddressByTypeParam;
    }

    @Override // cerence.ark.assistant.core.JsonCompliant
    public String toJsonString() throws JSONException {
        JSONObject jSONObject = new JSONObject();
        jSONObject.put("address_type", this.addressType);
        return jSONObject.toString();
    }
}
