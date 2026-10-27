package com.amazonaws.services.iot.model.transform;

import com.amazonaws.services.iot.model.ListThingsInBillingGroupResult;
import com.amazonaws.transform.JsonUnmarshallerContext;
import com.amazonaws.transform.ListUnmarshaller;
import com.amazonaws.transform.SimpleTypeJsonUnmarshallers;
import com.amazonaws.transform.Unmarshaller;
import com.amazonaws.util.json.AwsJsonReader;

/* JADX INFO: loaded from: classes5.dex */
public class ListThingsInBillingGroupResultJsonUnmarshaller implements Unmarshaller<ListThingsInBillingGroupResult, JsonUnmarshallerContext> {
    private static ListThingsInBillingGroupResultJsonUnmarshaller instance;

    @Override // com.amazonaws.transform.Unmarshaller
    public ListThingsInBillingGroupResult unmarshall(JsonUnmarshallerContext jsonUnmarshallerContext) throws Exception {
        ListThingsInBillingGroupResult listThingsInBillingGroupResult = new ListThingsInBillingGroupResult();
        AwsJsonReader reader = jsonUnmarshallerContext.getReader();
        reader.beginObject();
        while (reader.hasNext()) {
            String strNextName = reader.nextName();
            if (strNextName.equals("things")) {
                listThingsInBillingGroupResult.setThings(new ListUnmarshaller(SimpleTypeJsonUnmarshallers.StringJsonUnmarshaller.getInstance()).unmarshall(jsonUnmarshallerContext));
            } else if (strNextName.equals("nextToken")) {
                listThingsInBillingGroupResult.setNextToken(SimpleTypeJsonUnmarshallers.StringJsonUnmarshaller.getInstance().unmarshall(jsonUnmarshallerContext));
            } else {
                reader.skipValue();
            }
        }
        reader.endObject();
        return listThingsInBillingGroupResult;
    }

    public static ListThingsInBillingGroupResultJsonUnmarshaller getInstance() {
        if (instance == null) {
            instance = new ListThingsInBillingGroupResultJsonUnmarshaller();
        }
        return instance;
    }
}
