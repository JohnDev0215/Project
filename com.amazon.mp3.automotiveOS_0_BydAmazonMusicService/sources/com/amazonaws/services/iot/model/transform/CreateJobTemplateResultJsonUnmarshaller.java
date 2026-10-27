package com.amazonaws.services.iot.model.transform;

import com.amazonaws.services.iot.model.CreateJobTemplateResult;
import com.amazonaws.transform.JsonUnmarshallerContext;
import com.amazonaws.transform.SimpleTypeJsonUnmarshallers;
import com.amazonaws.transform.Unmarshaller;
import com.amazonaws.util.json.AwsJsonReader;

/* JADX INFO: loaded from: classes5.dex */
public class CreateJobTemplateResultJsonUnmarshaller implements Unmarshaller<CreateJobTemplateResult, JsonUnmarshallerContext> {
    private static CreateJobTemplateResultJsonUnmarshaller instance;

    @Override // com.amazonaws.transform.Unmarshaller
    public CreateJobTemplateResult unmarshall(JsonUnmarshallerContext jsonUnmarshallerContext) throws Exception {
        CreateJobTemplateResult createJobTemplateResult = new CreateJobTemplateResult();
        AwsJsonReader reader = jsonUnmarshallerContext.getReader();
        reader.beginObject();
        while (reader.hasNext()) {
            String strNextName = reader.nextName();
            if (strNextName.equals("jobTemplateArn")) {
                createJobTemplateResult.setJobTemplateArn(SimpleTypeJsonUnmarshallers.StringJsonUnmarshaller.getInstance().unmarshall(jsonUnmarshallerContext));
            } else if (strNextName.equals("jobTemplateId")) {
                createJobTemplateResult.setJobTemplateId(SimpleTypeJsonUnmarshallers.StringJsonUnmarshaller.getInstance().unmarshall(jsonUnmarshallerContext));
            } else {
                reader.skipValue();
            }
        }
        reader.endObject();
        return createJobTemplateResult;
    }

    public static CreateJobTemplateResultJsonUnmarshaller getInstance() {
        if (instance == null) {
            instance = new CreateJobTemplateResultJsonUnmarshaller();
        }
        return instance;
    }
}
