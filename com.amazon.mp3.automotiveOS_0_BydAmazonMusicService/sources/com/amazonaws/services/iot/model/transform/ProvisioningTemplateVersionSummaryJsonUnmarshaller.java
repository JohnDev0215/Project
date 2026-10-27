package com.amazonaws.services.iot.model.transform;

import com.amazonaws.services.iot.model.ProvisioningTemplateVersionSummary;
import com.amazonaws.transform.JsonUnmarshallerContext;
import com.amazonaws.transform.SimpleTypeJsonUnmarshallers;
import com.amazonaws.transform.Unmarshaller;
import com.amazonaws.util.json.AwsJsonReader;

/* JADX INFO: loaded from: classes5.dex */
class ProvisioningTemplateVersionSummaryJsonUnmarshaller implements Unmarshaller<ProvisioningTemplateVersionSummary, JsonUnmarshallerContext> {
    private static ProvisioningTemplateVersionSummaryJsonUnmarshaller instance;

    ProvisioningTemplateVersionSummaryJsonUnmarshaller() {
    }

    @Override // com.amazonaws.transform.Unmarshaller
    public ProvisioningTemplateVersionSummary unmarshall(JsonUnmarshallerContext jsonUnmarshallerContext) throws Exception {
        AwsJsonReader reader = jsonUnmarshallerContext.getReader();
        if (!reader.isContainer()) {
            reader.skipValue();
            return null;
        }
        ProvisioningTemplateVersionSummary provisioningTemplateVersionSummary = new ProvisioningTemplateVersionSummary();
        reader.beginObject();
        while (reader.hasNext()) {
            String strNextName = reader.nextName();
            if (strNextName.equals("versionId")) {
                provisioningTemplateVersionSummary.setVersionId(SimpleTypeJsonUnmarshallers.IntegerJsonUnmarshaller.getInstance().unmarshall(jsonUnmarshallerContext));
            } else if (strNextName.equals("creationDate")) {
                provisioningTemplateVersionSummary.setCreationDate(SimpleTypeJsonUnmarshallers.DateJsonUnmarshaller.getInstance().unmarshall(jsonUnmarshallerContext));
            } else if (strNextName.equals("isDefaultVersion")) {
                provisioningTemplateVersionSummary.setIsDefaultVersion(SimpleTypeJsonUnmarshallers.BooleanJsonUnmarshaller.getInstance().unmarshall(jsonUnmarshallerContext));
            } else {
                reader.skipValue();
            }
        }
        reader.endObject();
        return provisioningTemplateVersionSummary;
    }

    public static ProvisioningTemplateVersionSummaryJsonUnmarshaller getInstance() {
        if (instance == null) {
            instance = new ProvisioningTemplateVersionSummaryJsonUnmarshaller();
        }
        return instance;
    }
}
