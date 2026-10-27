package com.amazonaws.services.iotdata.model.transform;

import com.amazon.music.skyfire.models.TetheredMessageKey;
import com.amazonaws.services.iotdata.model.ListNamedShadowsForThingResult;
import com.amazonaws.transform.JsonUnmarshallerContext;
import com.amazonaws.transform.ListUnmarshaller;
import com.amazonaws.transform.SimpleTypeJsonUnmarshallers;
import com.amazonaws.transform.Unmarshaller;
import com.amazonaws.util.json.AwsJsonReader;

/* JADX INFO: loaded from: classes5.dex */
public class ListNamedShadowsForThingResultJsonUnmarshaller implements Unmarshaller<ListNamedShadowsForThingResult, JsonUnmarshallerContext> {
    private static ListNamedShadowsForThingResultJsonUnmarshaller instance;

    @Override // com.amazonaws.transform.Unmarshaller
    public ListNamedShadowsForThingResult unmarshall(JsonUnmarshallerContext jsonUnmarshallerContext) throws Exception {
        ListNamedShadowsForThingResult listNamedShadowsForThingResult = new ListNamedShadowsForThingResult();
        AwsJsonReader reader = jsonUnmarshallerContext.getReader();
        reader.beginObject();
        while (reader.hasNext()) {
            String strNextName = reader.nextName();
            if (strNextName.equals("results")) {
                listNamedShadowsForThingResult.setResults(new ListUnmarshaller(SimpleTypeJsonUnmarshallers.StringJsonUnmarshaller.getInstance()).unmarshall(jsonUnmarshallerContext));
            } else if (strNextName.equals("nextToken")) {
                listNamedShadowsForThingResult.setNextToken(SimpleTypeJsonUnmarshallers.StringJsonUnmarshaller.getInstance().unmarshall(jsonUnmarshallerContext));
            } else if (strNextName.equals(TetheredMessageKey.timestamp)) {
                listNamedShadowsForThingResult.setTimestamp(SimpleTypeJsonUnmarshallers.LongJsonUnmarshaller.getInstance().unmarshall(jsonUnmarshallerContext));
            } else {
                reader.skipValue();
            }
        }
        reader.endObject();
        return listNamedShadowsForThingResult;
    }

    public static ListNamedShadowsForThingResultJsonUnmarshaller getInstance() {
        if (instance == null) {
            instance = new ListNamedShadowsForThingResultJsonUnmarshaller();
        }
        return instance;
    }
}
