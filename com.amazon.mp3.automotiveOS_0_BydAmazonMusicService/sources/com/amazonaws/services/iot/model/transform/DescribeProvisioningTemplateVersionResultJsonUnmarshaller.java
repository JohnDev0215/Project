package com.amazonaws.services.iot.model.transform;

import com.amazonaws.services.iot.model.DescribeProvisioningTemplateVersionResult;
import com.amazonaws.transform.JsonUnmarshallerContext;
import com.amazonaws.transform.SimpleTypeJsonUnmarshallers;
import com.amazonaws.transform.Unmarshaller;
import com.amazonaws.util.json.AwsJsonReader;

/* JADX INFO: loaded from: classes5.dex */
public class DescribeProvisioningTemplateVersionResultJsonUnmarshaller implements Unmarshaller<DescribeProvisioningTemplateVersionResult, JsonUnmarshallerContext> {
    private static DescribeProvisioningTemplateVersionResultJsonUnmarshaller instance;

    @Override // com.amazonaws.transform.Unmarshaller
    public DescribeProvisioningTemplateVersionResult unmarshall(JsonUnmarshallerContext jsonUnmarshallerContext) throws Exception {
        DescribeProvisioningTemplateVersionResult describeProvisioningTemplateVersionResult = new DescribeProvisioningTemplateVersionResult();
        AwsJsonReader reader = jsonUnmarshallerContext.getReader();
        reader.beginObject();
        while (reader.hasNext()) {
            String strNextName = reader.nextName();
            if (strNextName.equals("versionId")) {
                describeProvisioningTemplateVersionResult.setVersionId(SimpleTypeJsonUnmarshallers.IntegerJsonUnmarshaller.getInstance().unmarshall(jsonUnmarshallerContext));
            } else if (strNextName.equals("creationDate")) {
                describeProvisioningTemplateVersionResult.setCreationDate(SimpleTypeJsonUnmarshallers.DateJsonUnmarshaller.getInstance().unmarshall(jsonUnmarshallerContext));
            } else if (strNextName.equals("templateBody")) {
                describeProvisioningTemplateVersionResult.setTemplateBody(SimpleTypeJsonUnmarshallers.StringJsonUnmarshaller.getInstance().unmarshall(jsonUnmarshallerContext));
            } else if (strNextName.equals("isDefaultVersion")) {
                describeProvisioningTemplateVersionResult.setIsDefaultVersion(SimpleTypeJsonUnmarshallers.BooleanJsonUnmarshaller.getInstance().unmarshall(jsonUnmarshallerContext));
            } else {
                reader.skipValue();
            }
        }
        reader.endObject();
        return describeProvisioningTemplateVersionResult;
    }

    public static DescribeProvisioningTemplateVersionResultJsonUnmarshaller getInstance() {
        if (instance == null) {
            instance = new DescribeProvisioningTemplateVersionResultJsonUnmarshaller();
        }
        return instance;
    }
}
