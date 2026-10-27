package com.amazonaws.services.iot.model.transform;

import com.amazonaws.services.iot.model.ListDimensionsResult;
import com.amazonaws.transform.JsonUnmarshallerContext;
import com.amazonaws.transform.ListUnmarshaller;
import com.amazonaws.transform.SimpleTypeJsonUnmarshallers;
import com.amazonaws.transform.Unmarshaller;
import com.amazonaws.util.json.AwsJsonReader;

/* JADX INFO: loaded from: classes5.dex */
public class ListDimensionsResultJsonUnmarshaller implements Unmarshaller<ListDimensionsResult, JsonUnmarshallerContext> {
    private static ListDimensionsResultJsonUnmarshaller instance;

    @Override // com.amazonaws.transform.Unmarshaller
    public ListDimensionsResult unmarshall(JsonUnmarshallerContext jsonUnmarshallerContext) throws Exception {
        ListDimensionsResult listDimensionsResult = new ListDimensionsResult();
        AwsJsonReader reader = jsonUnmarshallerContext.getReader();
        reader.beginObject();
        while (reader.hasNext()) {
            String strNextName = reader.nextName();
            if (strNextName.equals("dimensionNames")) {
                listDimensionsResult.setDimensionNames(new ListUnmarshaller(SimpleTypeJsonUnmarshallers.StringJsonUnmarshaller.getInstance()).unmarshall(jsonUnmarshallerContext));
            } else if (strNextName.equals("nextToken")) {
                listDimensionsResult.setNextToken(SimpleTypeJsonUnmarshallers.StringJsonUnmarshaller.getInstance().unmarshall(jsonUnmarshallerContext));
            } else {
                reader.skipValue();
            }
        }
        reader.endObject();
        return listDimensionsResult;
    }

    public static ListDimensionsResultJsonUnmarshaller getInstance() {
        if (instance == null) {
            instance = new ListDimensionsResultJsonUnmarshaller();
        }
        return instance;
    }
}
