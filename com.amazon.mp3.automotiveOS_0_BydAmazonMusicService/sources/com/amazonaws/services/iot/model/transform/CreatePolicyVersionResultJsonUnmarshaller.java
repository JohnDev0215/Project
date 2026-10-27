package com.amazonaws.services.iot.model.transform;

import com.amazonaws.services.iot.model.CreatePolicyVersionResult;
import com.amazonaws.transform.JsonUnmarshallerContext;
import com.amazonaws.transform.SimpleTypeJsonUnmarshallers;
import com.amazonaws.transform.Unmarshaller;
import com.amazonaws.util.json.AwsJsonReader;

/* JADX INFO: loaded from: classes5.dex */
public class CreatePolicyVersionResultJsonUnmarshaller implements Unmarshaller<CreatePolicyVersionResult, JsonUnmarshallerContext> {
    private static CreatePolicyVersionResultJsonUnmarshaller instance;

    @Override // com.amazonaws.transform.Unmarshaller
    public CreatePolicyVersionResult unmarshall(JsonUnmarshallerContext jsonUnmarshallerContext) throws Exception {
        CreatePolicyVersionResult createPolicyVersionResult = new CreatePolicyVersionResult();
        AwsJsonReader reader = jsonUnmarshallerContext.getReader();
        reader.beginObject();
        while (reader.hasNext()) {
            String strNextName = reader.nextName();
            if (strNextName.equals("policyArn")) {
                createPolicyVersionResult.setPolicyArn(SimpleTypeJsonUnmarshallers.StringJsonUnmarshaller.getInstance().unmarshall(jsonUnmarshallerContext));
            } else if (strNextName.equals("policyDocument")) {
                createPolicyVersionResult.setPolicyDocument(SimpleTypeJsonUnmarshallers.StringJsonUnmarshaller.getInstance().unmarshall(jsonUnmarshallerContext));
            } else if (strNextName.equals("policyVersionId")) {
                createPolicyVersionResult.setPolicyVersionId(SimpleTypeJsonUnmarshallers.StringJsonUnmarshaller.getInstance().unmarshall(jsonUnmarshallerContext));
            } else if (strNextName.equals("isDefaultVersion")) {
                createPolicyVersionResult.setIsDefaultVersion(SimpleTypeJsonUnmarshallers.BooleanJsonUnmarshaller.getInstance().unmarshall(jsonUnmarshallerContext));
            } else {
                reader.skipValue();
            }
        }
        reader.endObject();
        return createPolicyVersionResult;
    }

    public static CreatePolicyVersionResultJsonUnmarshaller getInstance() {
        if (instance == null) {
            instance = new CreatePolicyVersionResultJsonUnmarshaller();
        }
        return instance;
    }
}
