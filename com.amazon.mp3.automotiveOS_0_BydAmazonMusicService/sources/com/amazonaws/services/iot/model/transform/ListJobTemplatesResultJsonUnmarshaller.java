package com.amazonaws.services.iot.model.transform;

import com.amazonaws.services.iot.model.ListJobTemplatesResult;
import com.amazonaws.transform.JsonUnmarshallerContext;
import com.amazonaws.transform.ListUnmarshaller;
import com.amazonaws.transform.SimpleTypeJsonUnmarshallers;
import com.amazonaws.transform.Unmarshaller;
import com.amazonaws.util.json.AwsJsonReader;

/* JADX INFO: loaded from: classes5.dex */
public class ListJobTemplatesResultJsonUnmarshaller implements Unmarshaller<ListJobTemplatesResult, JsonUnmarshallerContext> {
    private static ListJobTemplatesResultJsonUnmarshaller instance;

    @Override // com.amazonaws.transform.Unmarshaller
    public ListJobTemplatesResult unmarshall(JsonUnmarshallerContext jsonUnmarshallerContext) throws Exception {
        ListJobTemplatesResult listJobTemplatesResult = new ListJobTemplatesResult();
        AwsJsonReader reader = jsonUnmarshallerContext.getReader();
        reader.beginObject();
        while (reader.hasNext()) {
            String strNextName = reader.nextName();
            if (strNextName.equals("jobTemplates")) {
                listJobTemplatesResult.setJobTemplates(new ListUnmarshaller(JobTemplateSummaryJsonUnmarshaller.getInstance()).unmarshall(jsonUnmarshallerContext));
            } else if (strNextName.equals("nextToken")) {
                listJobTemplatesResult.setNextToken(SimpleTypeJsonUnmarshallers.StringJsonUnmarshaller.getInstance().unmarshall(jsonUnmarshallerContext));
            } else {
                reader.skipValue();
            }
        }
        reader.endObject();
        return listJobTemplatesResult;
    }

    public static ListJobTemplatesResultJsonUnmarshaller getInstance() {
        if (instance == null) {
            instance = new ListJobTemplatesResultJsonUnmarshaller();
        }
        return instance;
    }
}
