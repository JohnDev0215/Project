package com.amazonaws.services.iot.model.transform;

import com.amazonaws.services.iot.model.CreateProvisioningTemplateResult;
import com.amazonaws.transform.JsonUnmarshallerContext;
import com.amazonaws.transform.SimpleTypeJsonUnmarshallers;
import com.amazonaws.transform.Unmarshaller;
import com.amazonaws.util.json.AwsJsonReader;

/* JADX INFO: loaded from: classes5.dex */
public class CreateProvisioningTemplateResultJsonUnmarshaller implements Unmarshaller<CreateProvisioningTemplateResult, JsonUnmarshallerContext> {
    private static CreateProvisioningTemplateResultJsonUnmarshaller instance;

    @Override // com.amazonaws.transform.Unmarshaller
    public CreateProvisioningTemplateResult unmarshall(JsonUnmarshallerContext jsonUnmarshallerContext) throws Exception {
        CreateProvisioningTemplateResult createProvisioningTemplateResult = new CreateProvisioningTemplateResult();
        AwsJsonReader reader = jsonUnmarshallerContext.getReader();
        reader.beginObject();
        while (reader.hasNext()) {
            String strNextName = reader.nextName();
            if (strNextName.equals("templateArn")) {
                createProvisioningTemplateResult.setTemplateArn(SimpleTypeJsonUnmarshallers.StringJsonUnmarshaller.getInstance().unmarshall(jsonUnmarshallerContext));
            } else if (strNextName.equals("templateName")) {
                createProvisioningTemplateResult.setTemplateName(SimpleTypeJsonUnmarshallers.StringJsonUnmarshaller.getInstance().unmarshall(jsonUnmarshallerContext));
            } else if (strNextName.equals("defaultVersionId")) {
                createProvisioningTemplateResult.setDefaultVersionId(SimpleTypeJsonUnmarshallers.IntegerJsonUnmarshaller.getInstance().unmarshall(jsonUnmarshallerContext));
            } else {
                reader.skipValue();
            }
        }
        reader.endObject();
        return createProvisioningTemplateResult;
    }

    public static CreateProvisioningTemplateResultJsonUnmarshaller getInstance() {
        if (instance == null) {
            instance = new CreateProvisioningTemplateResultJsonUnmarshaller();
        }
        return instance;
    }
}
