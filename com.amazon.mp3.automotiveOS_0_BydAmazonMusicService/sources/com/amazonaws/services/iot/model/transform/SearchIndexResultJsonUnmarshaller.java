package com.amazonaws.services.iot.model.transform;

import com.amazonaws.services.iot.model.SearchIndexResult;
import com.amazonaws.transform.JsonUnmarshallerContext;
import com.amazonaws.transform.ListUnmarshaller;
import com.amazonaws.transform.SimpleTypeJsonUnmarshallers;
import com.amazonaws.transform.Unmarshaller;
import com.amazonaws.util.json.AwsJsonReader;

/* JADX INFO: loaded from: classes5.dex */
public class SearchIndexResultJsonUnmarshaller implements Unmarshaller<SearchIndexResult, JsonUnmarshallerContext> {
    private static SearchIndexResultJsonUnmarshaller instance;

    @Override // com.amazonaws.transform.Unmarshaller
    public SearchIndexResult unmarshall(JsonUnmarshallerContext jsonUnmarshallerContext) throws Exception {
        SearchIndexResult searchIndexResult = new SearchIndexResult();
        AwsJsonReader reader = jsonUnmarshallerContext.getReader();
        reader.beginObject();
        while (reader.hasNext()) {
            String strNextName = reader.nextName();
            if (strNextName.equals("nextToken")) {
                searchIndexResult.setNextToken(SimpleTypeJsonUnmarshallers.StringJsonUnmarshaller.getInstance().unmarshall(jsonUnmarshallerContext));
            } else if (strNextName.equals("things")) {
                searchIndexResult.setThings(new ListUnmarshaller(ThingDocumentJsonUnmarshaller.getInstance()).unmarshall(jsonUnmarshallerContext));
            } else if (strNextName.equals("thingGroups")) {
                searchIndexResult.setThingGroups(new ListUnmarshaller(ThingGroupDocumentJsonUnmarshaller.getInstance()).unmarshall(jsonUnmarshallerContext));
            } else {
                reader.skipValue();
            }
        }
        reader.endObject();
        return searchIndexResult;
    }

    public static SearchIndexResultJsonUnmarshaller getInstance() {
        if (instance == null) {
            instance = new SearchIndexResultJsonUnmarshaller();
        }
        return instance;
    }
}
