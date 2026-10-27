package com.amazonaws.services.iot.model.transform;

import com.amazonaws.services.iot.model.ListThingRegistrationTasksResult;
import com.amazonaws.transform.JsonUnmarshallerContext;
import com.amazonaws.transform.ListUnmarshaller;
import com.amazonaws.transform.SimpleTypeJsonUnmarshallers;
import com.amazonaws.transform.Unmarshaller;
import com.amazonaws.util.json.AwsJsonReader;

/* JADX INFO: loaded from: classes5.dex */
public class ListThingRegistrationTasksResultJsonUnmarshaller implements Unmarshaller<ListThingRegistrationTasksResult, JsonUnmarshallerContext> {
    private static ListThingRegistrationTasksResultJsonUnmarshaller instance;

    @Override // com.amazonaws.transform.Unmarshaller
    public ListThingRegistrationTasksResult unmarshall(JsonUnmarshallerContext jsonUnmarshallerContext) throws Exception {
        ListThingRegistrationTasksResult listThingRegistrationTasksResult = new ListThingRegistrationTasksResult();
        AwsJsonReader reader = jsonUnmarshallerContext.getReader();
        reader.beginObject();
        while (reader.hasNext()) {
            String strNextName = reader.nextName();
            if (strNextName.equals("taskIds")) {
                listThingRegistrationTasksResult.setTaskIds(new ListUnmarshaller(SimpleTypeJsonUnmarshallers.StringJsonUnmarshaller.getInstance()).unmarshall(jsonUnmarshallerContext));
            } else if (strNextName.equals("nextToken")) {
                listThingRegistrationTasksResult.setNextToken(SimpleTypeJsonUnmarshallers.StringJsonUnmarshaller.getInstance().unmarshall(jsonUnmarshallerContext));
            } else {
                reader.skipValue();
            }
        }
        reader.endObject();
        return listThingRegistrationTasksResult;
    }

    public static ListThingRegistrationTasksResultJsonUnmarshaller getInstance() {
        if (instance == null) {
            instance = new ListThingRegistrationTasksResultJsonUnmarshaller();
        }
        return instance;
    }
}
