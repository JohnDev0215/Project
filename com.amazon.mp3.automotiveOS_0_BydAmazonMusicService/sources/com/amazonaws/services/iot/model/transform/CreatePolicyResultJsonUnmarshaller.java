package com.amazonaws.services.iot.model.transform;

import com.amazonaws.services.iot.model.CreatePolicyResult;
import com.amazonaws.transform.JsonUnmarshallerContext;
import com.amazonaws.transform.SimpleTypeJsonUnmarshallers;
import com.amazonaws.transform.Unmarshaller;
import com.amazonaws.util.json.AwsJsonReader;

/* JADX INFO: loaded from: classes5.dex */
public class CreatePolicyResultJsonUnmarshaller implements Unmarshaller<CreatePolicyResult, JsonUnmarshallerContext> {
    private static CreatePolicyResultJsonUnmarshaller instance;

    @Override // com.amazonaws.transform.Unmarshaller
    public CreatePolicyResult unmarshall(JsonUnmarshallerContext jsonUnmarshallerContext) throws Exception {
        CreatePolicyResult createPolicyResult = new CreatePolicyResult();
        AwsJsonReader reader = jsonUnmarshallerContext.getReader();
        reader.beginObject();
        while (reader.hasNext()) {
            String strNextName = reader.nextName();
            if (strNextName.equals("policyName")) {
                createPolicyResult.setPolicyName(SimpleTypeJsonUnmarshallers.StringJsonUnmarshaller.getInstance().unmarshall(jsonUnmarshallerContext));
            } else if (strNextName.equals("policyArn")) {
                createPolicyResult.setPolicyArn(SimpleTypeJsonUnmarshallers.StringJsonUnmarshaller.getInstance().unmarshall(jsonUnmarshallerContext));
            } else if (strNextName.equals("policyDocument")) {
                createPolicyResult.setPolicyDocument(SimpleTypeJsonUnmarshallers.StringJsonUnmarshaller.getInstance().unmarshall(jsonUnmarshallerContext));
            } else if (strNextName.equals("policyVersionId")) {
                createPolicyResult.setPolicyVersionId(SimpleTypeJsonUnmarshallers.StringJsonUnmarshaller.getInstance().unmarshall(jsonUnmarshallerContext));
            } else {
                reader.skipValue();
            }
        }
        reader.endObject();
        return createPolicyResult;
    }

    public static CreatePolicyResultJsonUnmarshaller getInstance() {
        if (instance == null) {
            instance = new CreatePolicyResultJsonUnmarshaller();
        }
        return instance;
    }
}
