package com.amazonaws.services.iot.model.transform;

import com.amazonaws.services.iot.model.ListProvisioningTemplateVersionsResult;
import com.amazonaws.transform.JsonUnmarshallerContext;
import com.amazonaws.transform.ListUnmarshaller;
import com.amazonaws.transform.SimpleTypeJsonUnmarshallers;
import com.amazonaws.transform.Unmarshaller;
import com.amazonaws.util.json.AwsJsonReader;

/* JADX INFO: loaded from: classes5.dex */
public class ListProvisioningTemplateVersionsResultJsonUnmarshaller implements Unmarshaller<ListProvisioningTemplateVersionsResult, JsonUnmarshallerContext> {
    private static ListProvisioningTemplateVersionsResultJsonUnmarshaller instance;

    @Override // com.amazonaws.transform.Unmarshaller
    public ListProvisioningTemplateVersionsResult unmarshall(JsonUnmarshallerContext jsonUnmarshallerContext) throws Exception {
        ListProvisioningTemplateVersionsResult listProvisioningTemplateVersionsResult = new ListProvisioningTemplateVersionsResult();
        AwsJsonReader reader = jsonUnmarshallerContext.getReader();
        reader.beginObject();
        while (reader.hasNext()) {
            String strNextName = reader.nextName();
            if (strNextName.equals("versions")) {
                listProvisioningTemplateVersionsResult.setVersions(new ListUnmarshaller(ProvisioningTemplateVersionSummaryJsonUnmarshaller.getInstance()).unmarshall(jsonUnmarshallerContext));
            } else if (strNextName.equals("nextToken")) {
                listProvisioningTemplateVersionsResult.setNextToken(SimpleTypeJsonUnmarshallers.StringJsonUnmarshaller.getInstance().unmarshall(jsonUnmarshallerContext));
            } else {
                reader.skipValue();
            }
        }
        reader.endObject();
        return listProvisioningTemplateVersionsResult;
    }

    public static ListProvisioningTemplateVersionsResultJsonUnmarshaller getInstance() {
        if (instance == null) {
            instance = new ListProvisioningTemplateVersionsResultJsonUnmarshaller();
        }
        return instance;
    }
}
