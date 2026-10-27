package com.amazonaws.services.iotdata.model.transform;

import com.amazonaws.services.iotdata.model.ListRetainedMessagesResult;
import com.amazonaws.transform.JsonUnmarshallerContext;
import com.amazonaws.transform.ListUnmarshaller;
import com.amazonaws.transform.SimpleTypeJsonUnmarshallers;
import com.amazonaws.transform.Unmarshaller;
import com.amazonaws.util.json.AwsJsonReader;

/* JADX INFO: loaded from: classes5.dex */
public class ListRetainedMessagesResultJsonUnmarshaller implements Unmarshaller<ListRetainedMessagesResult, JsonUnmarshallerContext> {
    private static ListRetainedMessagesResultJsonUnmarshaller instance;

    @Override // com.amazonaws.transform.Unmarshaller
    public ListRetainedMessagesResult unmarshall(JsonUnmarshallerContext jsonUnmarshallerContext) throws Exception {
        ListRetainedMessagesResult listRetainedMessagesResult = new ListRetainedMessagesResult();
        AwsJsonReader reader = jsonUnmarshallerContext.getReader();
        reader.beginObject();
        while (reader.hasNext()) {
            String strNextName = reader.nextName();
            if (strNextName.equals("retainedTopics")) {
                listRetainedMessagesResult.setRetainedTopics(new ListUnmarshaller(RetainedMessageSummaryJsonUnmarshaller.getInstance()).unmarshall(jsonUnmarshallerContext));
            } else if (strNextName.equals("nextToken")) {
                listRetainedMessagesResult.setNextToken(SimpleTypeJsonUnmarshallers.StringJsonUnmarshaller.getInstance().unmarshall(jsonUnmarshallerContext));
            } else {
                reader.skipValue();
            }
        }
        reader.endObject();
        return listRetainedMessagesResult;
    }

    public static ListRetainedMessagesResultJsonUnmarshaller getInstance() {
        if (instance == null) {
            instance = new ListRetainedMessagesResultJsonUnmarshaller();
        }
        return instance;
    }
}
