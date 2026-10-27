package com.amazonaws.services.iot.model.transform;

import com.amazonaws.services.iot.model.ListManagedJobTemplatesResult;
import com.amazonaws.transform.JsonUnmarshallerContext;
import com.amazonaws.transform.ListUnmarshaller;
import com.amazonaws.transform.SimpleTypeJsonUnmarshallers;
import com.amazonaws.transform.Unmarshaller;
import com.amazonaws.util.json.AwsJsonReader;

/* JADX INFO: loaded from: classes5.dex */
public class ListManagedJobTemplatesResultJsonUnmarshaller implements Unmarshaller<ListManagedJobTemplatesResult, JsonUnmarshallerContext> {
    private static ListManagedJobTemplatesResultJsonUnmarshaller instance;

    @Override // com.amazonaws.transform.Unmarshaller
    public ListManagedJobTemplatesResult unmarshall(JsonUnmarshallerContext jsonUnmarshallerContext) throws Exception {
        ListManagedJobTemplatesResult listManagedJobTemplatesResult = new ListManagedJobTemplatesResult();
        AwsJsonReader reader = jsonUnmarshallerContext.getReader();
        reader.beginObject();
        while (reader.hasNext()) {
            String strNextName = reader.nextName();
            if (strNextName.equals("managedJobTemplates")) {
                listManagedJobTemplatesResult.setManagedJobTemplates(new ListUnmarshaller(ManagedJobTemplateSummaryJsonUnmarshaller.getInstance()).unmarshall(jsonUnmarshallerContext));
            } else if (strNextName.equals("nextToken")) {
                listManagedJobTemplatesResult.setNextToken(SimpleTypeJsonUnmarshallers.StringJsonUnmarshaller.getInstance().unmarshall(jsonUnmarshallerContext));
            } else {
                reader.skipValue();
            }
        }
        reader.endObject();
        return listManagedJobTemplatesResult;
    }

    public static ListManagedJobTemplatesResultJsonUnmarshaller getInstance() {
        if (instance == null) {
            instance = new ListManagedJobTemplatesResultJsonUnmarshaller();
        }
        return instance;
    }
}
