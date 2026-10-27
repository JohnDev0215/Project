package com.amazonaws.services.iot.model.transform;

import com.amazonaws.services.iot.model.Policy;
import com.amazonaws.transform.JsonUnmarshallerContext;
import com.amazonaws.transform.SimpleTypeJsonUnmarshallers;
import com.amazonaws.transform.Unmarshaller;
import com.amazonaws.util.json.AwsJsonReader;

/* JADX INFO: loaded from: classes5.dex */
class PolicyJsonUnmarshaller implements Unmarshaller<Policy, JsonUnmarshallerContext> {
    private static PolicyJsonUnmarshaller instance;

    PolicyJsonUnmarshaller() {
    }

    @Override // com.amazonaws.transform.Unmarshaller
    public Policy unmarshall(JsonUnmarshallerContext jsonUnmarshallerContext) throws Exception {
        AwsJsonReader reader = jsonUnmarshallerContext.getReader();
        if (!reader.isContainer()) {
            reader.skipValue();
            return null;
        }
        Policy policy = new Policy();
        reader.beginObject();
        while (reader.hasNext()) {
            String strNextName = reader.nextName();
            if (strNextName.equals("policyName")) {
                policy.setPolicyName(SimpleTypeJsonUnmarshallers.StringJsonUnmarshaller.getInstance().unmarshall(jsonUnmarshallerContext));
            } else if (strNextName.equals("policyArn")) {
                policy.setPolicyArn(SimpleTypeJsonUnmarshallers.StringJsonUnmarshaller.getInstance().unmarshall(jsonUnmarshallerContext));
            } else {
                reader.skipValue();
            }
        }
        reader.endObject();
        return policy;
    }

    public static PolicyJsonUnmarshaller getInstance() {
        if (instance == null) {
            instance = new PolicyJsonUnmarshaller();
        }
        return instance;
    }
}
