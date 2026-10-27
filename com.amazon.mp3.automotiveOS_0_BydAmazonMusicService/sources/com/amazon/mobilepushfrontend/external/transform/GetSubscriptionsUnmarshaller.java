package com.amazon.mobilepushfrontend.external.transform;

import com.amazon.CoralAndroidClient.ClientBase.ClientOutput;
import com.amazon.CoralAndroidClient.ClientBase.Unmarshaller;
import com.amazon.CoralAndroidClient.Exception.CoralException;
import com.amazon.CoralAndroidClient.Exception.CoralUnknownException;
import com.amazon.CoralAndroidClient.Exception.NativeException;
import com.amazon.mobilepushfrontend.DependencyException;
import com.amazon.mobilepushfrontend.GetNotificationSubscriptionsResponse;
import com.amazon.mobilepushfrontend.InvalidApplicationIdentification;
import com.amazon.mobilepushfrontend.InvalidRequest;
import com.google.gson.Gson;
import org.json.JSONException;
import org.json.JSONObject;

/* JADX INFO: loaded from: classes3.dex */
public class GetSubscriptionsUnmarshaller implements Unmarshaller {
    private final Gson gson;

    private GetSubscriptionsUnmarshaller() {
        this.gson = null;
    }

    public GetSubscriptionsUnmarshaller(Gson gson) {
        this.gson = gson;
    }

    @Override // com.amazon.CoralAndroidClient.ClientBase.Unmarshaller
    public ClientOutput UnmarshalOutput(String str) throws NativeException {
        try {
            return (GetNotificationSubscriptionsResponse) this.gson.fromJson(str, GetNotificationSubscriptionsResponse.class);
        } catch (Exception e) {
            throw new NativeException(e);
        }
    }

    @Override // com.amazon.CoralAndroidClient.ClientBase.Unmarshaller
    public CoralException UnmarshalException(String str) throws NativeException {
        try {
            JSONObject jSONObject = new JSONObject(str);
            String string = jSONObject.getString("__type");
            String string2 = jSONObject.has("message") ? jSONObject.getString("message") : "";
            if (string.endsWith("InvalidRequest")) {
                return new InvalidRequest(string2);
            }
            if (string.endsWith("InvalidApplicationIdentification")) {
                return new InvalidApplicationIdentification(string2);
            }
            if (string.endsWith("DependencyException")) {
                return new DependencyException(string2);
            }
            return new CoralUnknownException(string, str);
        } catch (JSONException e) {
            throw new NativeException(e);
        }
    }
}
