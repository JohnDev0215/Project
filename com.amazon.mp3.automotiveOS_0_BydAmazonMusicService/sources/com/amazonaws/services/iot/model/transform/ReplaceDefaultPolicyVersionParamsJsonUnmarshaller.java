package com.amazonaws.services.iot.model.transform;

import com.amazonaws.services.iot.model.ReplaceDefaultPolicyVersionParams;
import com.amazonaws.transform.JsonUnmarshallerContext;
import com.amazonaws.transform.SimpleTypeJsonUnmarshallers;
import com.amazonaws.transform.Unmarshaller;
import com.amazonaws.util.json.AwsJsonReader;

/* JADX INFO: loaded from: classes5.dex */
class ReplaceDefaultPolicyVersionParamsJsonUnmarshaller implements Unmarshaller<ReplaceDefaultPolicyVersionParams, JsonUnmarshallerContext> {
    private static ReplaceDefaultPolicyVersionParamsJsonUnmarshaller instance;

    ReplaceDefaultPolicyVersionParamsJsonUnmarshaller() {
    }

    @Override // com.amazonaws.transform.Unmarshaller
    public ReplaceDefaultPolicyVersionParams unmarshall(JsonUnmarshallerContext jsonUnmarshallerContext) throws Exception {
        AwsJsonReader reader = jsonUnmarshallerContext.getReader();
        if (!reader.isContainer()) {
            reader.skipValue();
            return null;
        }
        ReplaceDefaultPolicyVersionParams replaceDefaultPolicyVersionParams = new ReplaceDefaultPolicyVersionParams();
        reader.beginObject();
        while (reader.hasNext()) {
            if (reader.nextName().equals("templateName")) {
                replaceDefaultPolicyVersionParams.setTemplateName(SimpleTypeJsonUnmarshallers.StringJsonUnmarshaller.getInstance().unmarshall(jsonUnmarshallerContext));
            } else {
                reader.skipValue();
            }
        }
        reader.endObject();
        return replaceDefaultPolicyVersionParams;
    }

    public static ReplaceDefaultPolicyVersionParamsJsonUnmarshaller getInstance() {
        if (instance == null) {
            instance = new ReplaceDefaultPolicyVersionParamsJsonUnmarshaller();
        }
        return instance;
    }
}
