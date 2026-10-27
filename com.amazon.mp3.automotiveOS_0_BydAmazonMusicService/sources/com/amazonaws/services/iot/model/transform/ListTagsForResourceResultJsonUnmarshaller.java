package com.amazonaws.services.iot.model.transform;

import com.amazonaws.services.iot.model.ListTagsForResourceResult;
import com.amazonaws.transform.JsonUnmarshallerContext;
import com.amazonaws.transform.ListUnmarshaller;
import com.amazonaws.transform.SimpleTypeJsonUnmarshallers;
import com.amazonaws.transform.Unmarshaller;
import com.amazonaws.util.json.AwsJsonReader;

/* JADX INFO: loaded from: classes5.dex */
public class ListTagsForResourceResultJsonUnmarshaller implements Unmarshaller<ListTagsForResourceResult, JsonUnmarshallerContext> {
    private static ListTagsForResourceResultJsonUnmarshaller instance;

    @Override // com.amazonaws.transform.Unmarshaller
    public ListTagsForResourceResult unmarshall(JsonUnmarshallerContext jsonUnmarshallerContext) throws Exception {
        ListTagsForResourceResult listTagsForResourceResult = new ListTagsForResourceResult();
        AwsJsonReader reader = jsonUnmarshallerContext.getReader();
        reader.beginObject();
        while (reader.hasNext()) {
            String strNextName = reader.nextName();
            if (strNextName.equals("tags")) {
                listTagsForResourceResult.setTags(new ListUnmarshaller(TagJsonUnmarshaller.getInstance()).unmarshall(jsonUnmarshallerContext));
            } else if (strNextName.equals("nextToken")) {
                listTagsForResourceResult.setNextToken(SimpleTypeJsonUnmarshallers.StringJsonUnmarshaller.getInstance().unmarshall(jsonUnmarshallerContext));
            } else {
                reader.skipValue();
            }
        }
        reader.endObject();
        return listTagsForResourceResult;
    }

    public static ListTagsForResourceResultJsonUnmarshaller getInstance() {
        if (instance == null) {
            instance = new ListTagsForResourceResultJsonUnmarshaller();
        }
        return instance;
    }
}
