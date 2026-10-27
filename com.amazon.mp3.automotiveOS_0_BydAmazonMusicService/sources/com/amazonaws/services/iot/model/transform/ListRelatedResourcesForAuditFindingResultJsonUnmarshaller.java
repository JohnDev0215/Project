package com.amazonaws.services.iot.model.transform;

import com.amazonaws.services.iot.model.ListRelatedResourcesForAuditFindingResult;
import com.amazonaws.transform.JsonUnmarshallerContext;
import com.amazonaws.transform.ListUnmarshaller;
import com.amazonaws.transform.SimpleTypeJsonUnmarshallers;
import com.amazonaws.transform.Unmarshaller;
import com.amazonaws.util.json.AwsJsonReader;

/* JADX INFO: loaded from: classes5.dex */
public class ListRelatedResourcesForAuditFindingResultJsonUnmarshaller implements Unmarshaller<ListRelatedResourcesForAuditFindingResult, JsonUnmarshallerContext> {
    private static ListRelatedResourcesForAuditFindingResultJsonUnmarshaller instance;

    @Override // com.amazonaws.transform.Unmarshaller
    public ListRelatedResourcesForAuditFindingResult unmarshall(JsonUnmarshallerContext jsonUnmarshallerContext) throws Exception {
        ListRelatedResourcesForAuditFindingResult listRelatedResourcesForAuditFindingResult = new ListRelatedResourcesForAuditFindingResult();
        AwsJsonReader reader = jsonUnmarshallerContext.getReader();
        reader.beginObject();
        while (reader.hasNext()) {
            String strNextName = reader.nextName();
            if (strNextName.equals("relatedResources")) {
                listRelatedResourcesForAuditFindingResult.setRelatedResources(new ListUnmarshaller(RelatedResourceJsonUnmarshaller.getInstance()).unmarshall(jsonUnmarshallerContext));
            } else if (strNextName.equals("nextToken")) {
                listRelatedResourcesForAuditFindingResult.setNextToken(SimpleTypeJsonUnmarshallers.StringJsonUnmarshaller.getInstance().unmarshall(jsonUnmarshallerContext));
            } else {
                reader.skipValue();
            }
        }
        reader.endObject();
        return listRelatedResourcesForAuditFindingResult;
    }

    public static ListRelatedResourcesForAuditFindingResultJsonUnmarshaller getInstance() {
        if (instance == null) {
            instance = new ListRelatedResourcesForAuditFindingResultJsonUnmarshaller();
        }
        return instance;
    }
}
