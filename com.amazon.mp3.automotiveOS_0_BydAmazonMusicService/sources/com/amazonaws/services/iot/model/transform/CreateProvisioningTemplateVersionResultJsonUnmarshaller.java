package com.amazonaws.services.iot.model.transform;

import com.amazonaws.services.iot.model.CreateProvisioningTemplateVersionResult;
import com.amazonaws.transform.JsonUnmarshallerContext;
import com.amazonaws.transform.SimpleTypeJsonUnmarshallers;
import com.amazonaws.transform.Unmarshaller;
import com.amazonaws.util.json.AwsJsonReader;

/* JADX INFO: loaded from: classes5.dex */
public class CreateProvisioningTemplateVersionResultJsonUnmarshaller implements Unmarshaller<CreateProvisioningTemplateVersionResult, JsonUnmarshallerContext> {
    private static CreateProvisioningTemplateVersionResultJsonUnmarshaller instance;

    @Override // com.amazonaws.transform.Unmarshaller
    public CreateProvisioningTemplateVersionResult unmarshall(JsonUnmarshallerContext jsonUnmarshallerContext) throws Exception {
        CreateProvisioningTemplateVersionResult createProvisioningTemplateVersionResult = new CreateProvisioningTemplateVersionResult();
        AwsJsonReader reader = jsonUnmarshallerContext.getReader();
        reader.beginObject();
        while (reader.hasNext()) {
            String strNextName = reader.nextName();
            if (strNextName.equals("templateArn")) {
                createProvisioningTemplateVersionResult.setTemplateArn(SimpleTypeJsonUnmarshallers.StringJsonUnmarshaller.getInstance().unmarshall(jsonUnmarshallerContext));
            } else if (strNextName.equals("templateName")) {
                createProvisioningTemplateVersionResult.setTemplateName(SimpleTypeJsonUnmarshallers.StringJsonUnmarshaller.getInstance().unmarshall(jsonUnmarshallerContext));
            } else if (strNextName.equals("versionId")) {
                createProvisioningTemplateVersionResult.setVersionId(SimpleTypeJsonUnmarshallers.IntegerJsonUnmarshaller.getInstance().unmarshall(jsonUnmarshallerContext));
            } else if (strNextName.equals("isDefaultVersion")) {
                createProvisioningTemplateVersionResult.setIsDefaultVersion(SimpleTypeJsonUnmarshallers.BooleanJsonUnmarshaller.getInstance().unmarshall(jsonUnmarshallerContext));
            } else {
                reader.skipValue();
            }
        }
        reader.endObject();
        return createProvisioningTemplateVersionResult;
    }

    public static CreateProvisioningTemplateVersionResultJsonUnmarshaller getInstance() {
        if (instance == null) {
            instance = new CreateProvisioningTemplateVersionResultJsonUnmarshaller();
        }
        return instance;
    }
}
