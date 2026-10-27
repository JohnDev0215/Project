package com.amazonaws.services.iot.model.transform;

import com.amazonaws.services.iot.model.GetPolicyResult;
import com.amazonaws.transform.JsonUnmarshallerContext;
import com.amazonaws.transform.SimpleTypeJsonUnmarshallers;
import com.amazonaws.transform.Unmarshaller;
import com.amazonaws.util.json.AwsJsonReader;

/* JADX INFO: loaded from: classes5.dex */
public class GetPolicyResultJsonUnmarshaller implements Unmarshaller<GetPolicyResult, JsonUnmarshallerContext> {
    private static GetPolicyResultJsonUnmarshaller instance;

    @Override // com.amazonaws.transform.Unmarshaller
    public GetPolicyResult unmarshall(JsonUnmarshallerContext jsonUnmarshallerContext) throws Exception {
        GetPolicyResult getPolicyResult = new GetPolicyResult();
        AwsJsonReader reader = jsonUnmarshallerContext.getReader();
        reader.beginObject();
        while (reader.hasNext()) {
            String strNextName = reader.nextName();
            if (strNextName.equals("policyName")) {
                getPolicyResult.setPolicyName(SimpleTypeJsonUnmarshallers.StringJsonUnmarshaller.getInstance().unmarshall(jsonUnmarshallerContext));
            } else if (strNextName.equals("policyArn")) {
                getPolicyResult.setPolicyArn(SimpleTypeJsonUnmarshallers.StringJsonUnmarshaller.getInstance().unmarshall(jsonUnmarshallerContext));
            } else if (strNextName.equals("policyDocument")) {
                getPolicyResult.setPolicyDocument(SimpleTypeJsonUnmarshallers.StringJsonUnmarshaller.getInstance().unmarshall(jsonUnmarshallerContext));
            } else if (strNextName.equals("defaultVersionId")) {
                getPolicyResult.setDefaultVersionId(SimpleTypeJsonUnmarshallers.StringJsonUnmarshaller.getInstance().unmarshall(jsonUnmarshallerContext));
            } else if (strNextName.equals("creationDate")) {
                getPolicyResult.setCreationDate(SimpleTypeJsonUnmarshallers.DateJsonUnmarshaller.getInstance().unmarshall(jsonUnmarshallerContext));
            } else if (strNextName.equals("lastModifiedDate")) {
                getPolicyResult.setLastModifiedDate(SimpleTypeJsonUnmarshallers.DateJsonUnmarshaller.getInstance().unmarshall(jsonUnmarshallerContext));
            } else if (strNextName.equals("generationId")) {
                getPolicyResult.setGenerationId(SimpleTypeJsonUnmarshallers.StringJsonUnmarshaller.getInstance().unmarshall(jsonUnmarshallerContext));
            } else {
                reader.skipValue();
            }
        }
        reader.endObject();
        return getPolicyResult;
    }

    public static GetPolicyResultJsonUnmarshaller getInstance() {
        if (instance == null) {
            instance = new GetPolicyResultJsonUnmarshaller();
        }
        return instance;
    }
}
