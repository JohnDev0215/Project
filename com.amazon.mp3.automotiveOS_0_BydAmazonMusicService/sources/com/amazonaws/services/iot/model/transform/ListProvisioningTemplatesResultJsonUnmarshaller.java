package com.amazonaws.services.iot.model.transform;

import com.amazonaws.services.iot.model.ListProvisioningTemplatesResult;
import com.amazonaws.transform.JsonUnmarshallerContext;
import com.amazonaws.transform.ListUnmarshaller;
import com.amazonaws.transform.SimpleTypeJsonUnmarshallers;
import com.amazonaws.transform.Unmarshaller;
import com.amazonaws.util.json.AwsJsonReader;

/* JADX INFO: loaded from: classes5.dex */
public class ListProvisioningTemplatesResultJsonUnmarshaller implements Unmarshaller<ListProvisioningTemplatesResult, JsonUnmarshallerContext> {
    private static ListProvisioningTemplatesResultJsonUnmarshaller instance;

    @Override // com.amazonaws.transform.Unmarshaller
    public ListProvisioningTemplatesResult unmarshall(JsonUnmarshallerContext jsonUnmarshallerContext) throws Exception {
        ListProvisioningTemplatesResult listProvisioningTemplatesResult = new ListProvisioningTemplatesResult();
        AwsJsonReader reader = jsonUnmarshallerContext.getReader();
        reader.beginObject();
        while (reader.hasNext()) {
            String strNextName = reader.nextName();
            if (strNextName.equals("templates")) {
                listProvisioningTemplatesResult.setTemplates(new ListUnmarshaller(ProvisioningTemplateSummaryJsonUnmarshaller.getInstance()).unmarshall(jsonUnmarshallerContext));
            } else if (strNextName.equals("nextToken")) {
                listProvisioningTemplatesResult.setNextToken(SimpleTypeJsonUnmarshallers.StringJsonUnmarshaller.getInstance().unmarshall(jsonUnmarshallerContext));
            } else {
                reader.skipValue();
            }
        }
        reader.endObject();
        return listProvisioningTemplatesResult;
    }

    public static ListProvisioningTemplatesResultJsonUnmarshaller getInstance() {
        if (instance == null) {
            instance = new ListProvisioningTemplatesResultJsonUnmarshaller();
        }
        return instance;
    }
}
