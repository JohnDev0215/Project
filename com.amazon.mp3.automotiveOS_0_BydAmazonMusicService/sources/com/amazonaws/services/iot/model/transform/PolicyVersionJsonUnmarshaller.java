package com.amazonaws.services.iot.model.transform;

import com.amazonaws.mobile.client.internal.oauth2.OAuth2Client;
import com.amazonaws.services.iot.model.PolicyVersion;
import com.amazonaws.transform.JsonUnmarshallerContext;
import com.amazonaws.transform.SimpleTypeJsonUnmarshallers;
import com.amazonaws.transform.Unmarshaller;
import com.amazonaws.util.json.AwsJsonReader;

/* JADX INFO: loaded from: classes5.dex */
class PolicyVersionJsonUnmarshaller implements Unmarshaller<PolicyVersion, JsonUnmarshallerContext> {
    private static PolicyVersionJsonUnmarshaller instance;

    PolicyVersionJsonUnmarshaller() {
    }

    @Override // com.amazonaws.transform.Unmarshaller
    public PolicyVersion unmarshall(JsonUnmarshallerContext jsonUnmarshallerContext) throws Exception {
        AwsJsonReader reader = jsonUnmarshallerContext.getReader();
        if (!reader.isContainer()) {
            reader.skipValue();
            return null;
        }
        PolicyVersion policyVersion = new PolicyVersion();
        reader.beginObject();
        while (reader.hasNext()) {
            String strNextName = reader.nextName();
            if (strNextName.equals("versionId")) {
                policyVersion.setVersionId(SimpleTypeJsonUnmarshallers.StringJsonUnmarshaller.getInstance().unmarshall(jsonUnmarshallerContext));
            } else if (strNextName.equals("isDefaultVersion")) {
                policyVersion.setIsDefaultVersion(SimpleTypeJsonUnmarshallers.BooleanJsonUnmarshaller.getInstance().unmarshall(jsonUnmarshallerContext));
            } else if (strNextName.equals(OAuth2Client.CREATE_DATE)) {
                policyVersion.setCreateDate(SimpleTypeJsonUnmarshallers.DateJsonUnmarshaller.getInstance().unmarshall(jsonUnmarshallerContext));
            } else {
                reader.skipValue();
            }
        }
        reader.endObject();
        return policyVersion;
    }

    public static PolicyVersionJsonUnmarshaller getInstance() {
        if (instance == null) {
            instance = new PolicyVersionJsonUnmarshaller();
        }
        return instance;
    }
}
