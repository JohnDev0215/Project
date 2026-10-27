package com.amazon.mobilepushfrontend.appstate.external.transform;

import com.amazon.CoralAndroidClient.ClientBase.ClientOutput;
import com.amazon.CoralAndroidClient.ClientBase.Unmarshaller;
import com.amazon.CoralAndroidClient.Exception.CoralException;
import com.amazon.CoralAndroidClient.Exception.CoralUnknownException;
import com.amazon.CoralAndroidClient.Exception.NativeException;
import com.amazon.KiangService.RegisterApplicationInstallResponse;
import com.amazon.mobilepushfrontend.DependencyException;
import org.json.JSONException;
import org.json.JSONObject;

/* JADX INFO: loaded from: classes3.dex */
public class RegisterApplicationInstallUnmarshaller implements Unmarshaller {
    @Override // com.amazon.CoralAndroidClient.ClientBase.Unmarshaller
    public ClientOutput UnmarshalOutput(String str) throws NativeException {
        try {
            JSONObject jSONObject = new JSONObject(str);
            RegisterApplicationInstallResponse registerApplicationInstallResponse = new RegisterApplicationInstallResponse();
            if (jSONObject.has("applicationInstallId")) {
                registerApplicationInstallResponse.setApplicationInstallId(jSONObject.getString("applicationInstallId"));
            }
            return registerApplicationInstallResponse;
        } catch (JSONException e) {
            throw new NativeException(e);
        }
    }

    @Override // com.amazon.CoralAndroidClient.ClientBase.Unmarshaller
    public CoralException UnmarshalException(String str) throws NativeException {
        try {
            JSONObject jSONObject = new JSONObject(str);
            String string = jSONObject.getString("__type");
            String string2 = jSONObject.has("message") ? jSONObject.getString("message") : "";
            if (string.endsWith("DependencyException")) {
                return new DependencyException(string2);
            }
            return new CoralUnknownException(string, str);
        } catch (JSONException e) {
            throw new NativeException(e);
        }
    }
}
