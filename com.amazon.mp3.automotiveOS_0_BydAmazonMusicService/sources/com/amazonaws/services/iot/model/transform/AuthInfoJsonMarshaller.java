package com.amazonaws.services.iot.model.transform;

import com.amazonaws.services.iot.model.AuthInfo;
import com.amazonaws.util.json.AwsJsonWriter;
import java.util.List;

/* JADX INFO: loaded from: classes5.dex */
class AuthInfoJsonMarshaller {
    private static AuthInfoJsonMarshaller instance;

    AuthInfoJsonMarshaller() {
    }

    public void marshall(AuthInfo authInfo, AwsJsonWriter awsJsonWriter) throws Exception {
        awsJsonWriter.beginObject();
        if (authInfo.getActionType() != null) {
            String actionType = authInfo.getActionType();
            awsJsonWriter.name("actionType");
            awsJsonWriter.value(actionType);
        }
        if (authInfo.getResources() != null) {
            List<String> resources = authInfo.getResources();
            awsJsonWriter.name("resources");
            awsJsonWriter.beginArray();
            for (String str : resources) {
                if (str != null) {
                    awsJsonWriter.value(str);
                }
            }
            awsJsonWriter.endArray();
        }
        awsJsonWriter.endObject();
    }

    public static AuthInfoJsonMarshaller getInstance() {
        if (instance == null) {
            instance = new AuthInfoJsonMarshaller();
        }
        return instance;
    }
}
